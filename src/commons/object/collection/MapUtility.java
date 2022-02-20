/*
 * File:    MapUtility.java
 * Package: commons.object.collection
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import commons.object.string.StringUtility;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides additional map functionality.
 */
public final class MapUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MapUtility.class);
    
    
    //Constants
    
    /**
     * The default map class to use when one is not specified.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static final Class<? extends Map<?, ?>> DEFAULT_MAP_CLASS = (Class) HashMap.class;
    
    
    //Static Methods
    
    /**
     * Creates a new map of a certain class.
     *
     * @param clazz The class of the map.
     * @param <M>   The class of the map.
     * @return The created map.
     */
    private static <M extends Map<?, ?>> Map<?, ?> create(Class<M> clazz) {
        switch (clazz.getSimpleName()) {
            case "HashMap":
            default:
                return new HashMap<>();
            case "LinkedHashMap":
                return new LinkedHashMap<>();
            case "TreeMap":
                return new TreeMap<>();
        }
    }
    
    /**
     * Creates a new map of a certain class, key type, and value type.
     *
     * @param clazz     The class of the map.
     * @param keyType   The type of the keys of the map.
     * @param valueType The type of the values of the map.
     * @param <K>       The type of the keys of the map.
     * @param <V>       The type of the values of the map.
     * @param <M>       The class of the map.
     * @return The created map.
     * @throws ClassCastException When attempting to create a TreeMap with a key type that is not Comparable.
     * @see #create(Class)
     */
    @SuppressWarnings("unchecked")
    public static <K, V, M extends Map<?, ?>> Map<K, V> create(Class<M> clazz, Class<K> keyType, Class<V> valueType) throws ClassCastException {
        if (clazz.equals(TreeMap.class) && !ArrayUtility.contains(keyType.getInterfaces(), Comparable.class)) {
            throw new ClassCastException("class " + keyType.getCanonicalName() + " cannot be cast to class " + Comparable.class.getCanonicalName());
        }
        
        return (Map<K, V>) create(clazz);
    }
    
    /**
     * Creates a new map of a certain key type and value type.
     *
     * @param keyType   The type of the keys of the map.
     * @param valueType The type of the values of the map.
     * @param <K>       The type of the keys of the map.
     * @param <V>       The type of the values of the map.
     * @return The created map.
     * @throws ClassCastException When attempting to create a TreeMap with a key type that is not Comparable.
     */
    public static <K, V> Map<K, V> create(Class<K> keyType, Class<V> valueType) throws ClassCastException {
        return create(DEFAULT_MAP_CLASS, keyType, valueType);
    }
    
    /**
     * Creates and populates a new map of a certain class.
     *
     * @param clazz   The class of the map.
     * @param entries The entries to populate the map with.
     * @param <K>     The type of the keys of the map.
     * @param <V>     The type of the values of the map.
     * @param <M>     The class of the map.
     * @return The created and populated map.
     * @throws ClassCastException When attempting to create a TreeMap with a key type that is not Comparable.
     */
    @SuppressWarnings("unchecked")
    public static <K, V, M extends Map<?, ?>> Map<K, V> mapOf(Class<M> clazz, Pair<K, V>... entries) throws ClassCastException {
        Map<K, V> map = (Map<K, V>) create(clazz);
        Arrays.stream(entries).forEachOrdered(e -> map.put(e.getKey(), e.getValue()));
        return map;
    }
    
    /**
     * Creates and populates a new map of a certain class.
     *
     * @param clazz   The class of the map.
     * @param entries The entries to populate the map with.
     * @param <K>     The type of the keys of the map.
     * @param <V>     The type of the values of the map.
     * @param <M>     The class of the map.
     * @return The created and populated map.
     * @throws ClassCastException When attempting to create a TreeMap with a key type that is not Comparable.
     * @see #mapOf(Class, Pair[])
     */
    @SuppressWarnings("unchecked")
    public static <K, V, M extends Map<?, ?>> Map<K, V> mapOf(Class<M> clazz, List<Pair<K, V>> entries) throws ClassCastException {
        return mapOf(clazz, entries.toArray(Pair[]::new));
    }
    
    /**
     * Creates and populates a new map of a certain class.
     *
     * @param clazz  The class of the map.
     * @param keys   The keys to populate the map with.
     * @param values The values to populate the map with.
     * @param <K>    The type of the keys of the map.
     * @param <V>    The type of the values of the map.
     * @param <M>    The class of the map.
     * @return The created and populated map.
     * @throws IndexOutOfBoundsException When the array of keys and the array of values are not the same length.
     * @throws ClassCastException        When attempting to create a TreeMap with a key type that is not Comparable.
     * @see #mapOf(Class, Pair[])
     */
    @SuppressWarnings("unchecked")
    public static <K, V, M extends Map<?, ?>> Map<K, V> mapOf(Class<M> clazz, K[] keys, V[] values) throws IndexOutOfBoundsException, ClassCastException {
        if (keys.length != values.length) {
            throw new IndexOutOfBoundsException();
        }
        
        return mapOf(clazz, IntStream.range(0, keys.length).boxed().map(i -> new ImmutablePair<>(keys[i], values[i])).toArray(Pair[]::new));
    }
    
    /**
     * Creates and populates a new map of a certain class.
     *
     * @param clazz  The class of the map.
     * @param keys   The keys to populate the map with.
     * @param values The values to populate the map with.
     * @param <K>    The type of the keys of the map.
     * @param <V>    The type of the values of the map.
     * @param <M>    The class of the map.
     * @return The created and populated map.
     * @throws IndexOutOfBoundsException When the list of keys and the list values are not the same size.
     * @throws ClassCastException        When attempting to create a TreeMap with a key type that is not Comparable.
     * @see #mapOf(Class, List)
     */
    public static <K, V, M extends Map<?, ?>> Map<K, V> mapOf(Class<M> clazz, List<K> keys, List<V> values) throws IndexOutOfBoundsException, ClassCastException {
        if (keys.size() != values.size()) {
            throw new IndexOutOfBoundsException();
        }
        
        return mapOf(clazz, IntStream.range(0, keys.size()).boxed().map(i -> new ImmutablePair<>(keys.get(i), values.get(i))).collect(Collectors.toList()));
    }
    
    /**
     * Creates and populates a new map.
     *
     * @param entries The entries to populate the map with.
     * @param <K>     The type of the keys of the map.
     * @param <V>     The type of the values of the map.
     * @return The created and populated map.
     * @see #mapOf(Class, Pair[])
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> mapOf(Pair<K, V>... entries) {
        return mapOf(DEFAULT_MAP_CLASS, entries);
    }
    
    /**
     * Creates and populates a new map.
     *
     * @param entries The entries to populate the map with.
     * @param <K>     The type of the keys of the map.
     * @param <V>     The type of the values of the map.
     * @return The created and populated map.
     * @see #mapOf(Class, List)
     */
    public static <K, V> Map<K, V> mapOf(List<Pair<K, V>> entries) {
        return mapOf(DEFAULT_MAP_CLASS, entries);
    }
    
    /**
     * Creates and populates a new map.
     *
     * @param keys   The keys to populate the map with.
     * @param values The values to populate the map with.
     * @param <K>    The type of the keys of the map.
     * @param <V>    The type of the values of the map.
     * @return The created and populated map.
     * @throws IndexOutOfBoundsException When the array of keys and the array of values are not the same length.
     * @see #mapOf(Class, Object[], Object[])
     */
    public static <K, V> Map<K, V> mapOf(K[] keys, V[] values) throws IndexOutOfBoundsException {
        return mapOf(DEFAULT_MAP_CLASS, keys, values);
    }
    
    /**
     * Creates and populates a new map.
     *
     * @param keys   The keys to populate the map with.
     * @param values The values to populate the map with.
     * @param <K>    The type of the keys of the map.
     * @param <V>    The type of the values of the map.
     * @return The created and populated map.
     * @throws IndexOutOfBoundsException When the list of keys and the list of values are not the same size.
     * @see #mapOf(Class, List, List)
     */
    public static <K, V> Map<K, V> mapOf(List<K> keys, List<V> values) throws IndexOutOfBoundsException {
        return mapOf(DEFAULT_MAP_CLASS, keys, values);
    }
    
    /**
     * Clones a map.
     *
     * @param map The map.
     * @param <K> The type of the keys of the map.
     * @param <V> The type of the values of the map.
     * @return The clone of the map.
     */
    public static <K, V> Map<K, V> clone(Map<K, V> map) {
        switch (map.getClass().getSimpleName()) {
            case "HashMap":
            default:
                return new HashMap<>(map);
            case "LinkedHashMap":
                return new LinkedHashMap<>(map);
            case "TreeMap":
                return new TreeMap<>(map);
        }
    }
    
    /**
     * Casts a map to a map of a specific class.
     *
     * @param map   The map.
     * @param clazz The map class to cast to.
     * @param <K>   The type of the keys of the map.
     * @param <V>   The type of the values of the map.
     * @param <M>   The map class to cast to.
     * @return The casted map, or the same map if the class is the same as specified.
     * @throws ClassCastException When attempting to cast to a TreeMap with a key type that is not Comparable.
     */
    public static <K, V, M extends Map<?, ?>> Map<K, V> cast(Map<K, V> map, Class<M> clazz) throws ClassCastException {
        if (map.getClass().equals(clazz)) {
            return map;
        }
        
        switch (clazz.getSimpleName()) {
            case "HashMap":
            default:
                return new HashMap<>(map);
            case "LinkedHashMap":
                return new LinkedHashMap<>(map);
            case "TreeMap":
                return new TreeMap<>(map);
        }
    }
    
    /**
     * Merges two maps.
     *
     * @param map1 The first map.
     * @param map2 The second map.
     * @param <K>  The type of the keys of the maps.
     * @param <V>  The type of the values of the maps.
     * @return The merged map.
     */
    public static <K, V> Map<K, V> merge(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> result = clone(map1);
        result.putAll(map2);
        return result;
    }
    
    /**
     * Determines if a map is null or empty.
     *
     * @param map The map.
     * @param <K> The type of the keys of the map.
     * @param <V> The type of the values of the map.
     * @return Whether the map is null or empty.
     */
    public static <K, V> boolean isNullOrEmpty(Map<K, V> map) {
        return (map == null) || map.isEmpty();
    }
    
    /**
     * Determines if a map equals another map.
     *
     * @param map1 The first map.
     * @param map2 The second map.
     * @param <K>  The type of the keys of the maps.
     * @param <V>  The type of the values of the maps.
     * @return Whether the maps are equal or not.
     */
    public static <K, V> boolean equals(Map<K, V> map1, Map<K, V> map2) {
        return ((map1 == null) || (map2 == null)) ? ((map1 == null) && (map2 == null)) : ((map1.size() == map2.size()) &&
                map1.keySet().stream().allMatch(e -> map2.containsKey(e) && Objects.equals(map1.get(e), map2.get(e))));
    }
    
    /**
     * Determines if a key exists in a map.
     *
     * @param map The map.
     * @param key The key.
     * @param <K> The type of the keys of the map.
     * @param <V> The type of the values of the map.
     * @return Whether the map contains the specified key or not.
     */
    public static <K, V> boolean contains(Map<K, V> map, K key) {
        return (map != null) && map.containsKey(key);
    }
    
    /**
     * Determines if a string key exists in a map, regardless of case.
     *
     * @param map The map.
     * @param key The key.
     * @param <V> The type of the values of the map.
     * @return Whether the map contains the specified string key or not, regardless of case.
     */
    public static <V> boolean containsIgnoreCase(Map<String, V> map, String key) {
        return (map != null) && map.keySet().stream().anyMatch(e -> StringUtility.equalsIgnoreCase(e, key));
    }
    
    /**
     * Returns a value from a map with a specified key, or a default value if the key is not present.
     *
     * @param map          The map.
     * @param key          The key.
     * @param defaultValue The default value.
     * @param <K>          The type of the keys of the map.
     * @param <V>          The type of the values of the map.
     * @return The value in the map with the specified key, or a default value if the key is not present.
     */
    public static <K, V> V getOrDefault(Map<K, V> map, K key, V defaultValue) {
        return (map == null) ? defaultValue :
               map.getOrDefault(key, defaultValue);
    }
    
    /**
     * Returns a value from a map with a specified string key, regardless of case, or a default value if the key is not present.
     *
     * @param map          The map.
     * @param key          The key.
     * @param defaultValue The default value.
     * @param <V>          The type of the values of the map.
     * @return The value in the map with the specified string key, regardless of case, or a default value if the key is not present.
     */
    public static <V> V getOrDefaultIgnoreCase(Map<String, V> map, String key, V defaultValue) {
        return (map == null) ? defaultValue :
               map.keySet().stream().filter(e -> StringUtility.equalsIgnoreCase(e, key)).findFirst().map(map::get).orElse(defaultValue);
    }
    
    /**
     * Returns a value from a map with a specified key, or null if the key is not present.
     *
     * @param map The map.
     * @param key The key.
     * @param <K> The type of the keys of the map.
     * @param <V> The type of the values of the map.
     * @return The value in the map with the specified key, or null if the key is not present.
     * @see #getOrDefault(Map, Object, Object)
     */
    public static <K, V> V getOrNull(Map<K, V> map, K key) {
        return getOrDefault(map, key, null);
    }
    
}
