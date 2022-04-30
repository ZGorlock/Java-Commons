/*
 * File:    StrictHashMap.java
 * Package: commons.object.collection.map
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.map;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a Strict Hash Map.<br>
 * The views backed by the map are returned as unmodifiable views.
 *
 * @param <K> The type of the keys of the map.
 * @param <V> The type of the values of the map.
 */
public class StrictHashMap<K, V> extends HashMap<K, V> {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(StrictHashMap.class);
    
    
    //Constructors
    
    /**
     * The constructor for an Strict Hash Map from a map.
     *
     * @param map The map.
     * @see #putAll(Map)
     */
    public StrictHashMap(Map<? extends K, ? extends V> map) {
        this.putAll(map);
    }
    
    /**
     * The default no-argument constructor for a Strict Hash Map.
     */
    public StrictHashMap() {
    }
    
    
    //Methods
    
    /**
     * Gets a mutable entry set of the map.
     *
     * @return A mutable entry set.
     * @see HashMap#entrySet()
     */
    protected Set<Map.Entry<K, V>> exposedEntrySet() {
        return super.entrySet();
    }
    
    /**
     * Gets an immutable entry set of the map.
     *
     * @return An immutable entry set.
     * @see #exposedEntrySet()
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return Collections.unmodifiableSet(exposedEntrySet().stream()
                .map(e -> Map.entry(e.getKey(), e.getValue()))
                .collect(Collectors.toSet()));
    }
    
    /**
     * Gets a mutable key set of the map.
     *
     * @return A mutable key set.
     * @see HashMap#keySet()
     */
    protected Set<K> exposedKeySet() {
        return super.keySet();
    }
    
    /**
     * Gets an immutable key set of the map.
     *
     * @return An immutable key set.
     * @see #exposedKeySet()
     */
    @Override
    public Set<K> keySet() {
        return Collections.unmodifiableSet(exposedKeySet());
    }
    
    /**
     * Gets a mutable collection of values of the map.
     *
     * @return A mutable collection of values.
     * @see HashMap#values()
     */
    protected Collection<V> exposedValues() {
        return super.values();
    }
    
    /**
     * Gets an immutable collection of values of the map.
     *
     * @return An immutable collection of values.
     * @see #exposedValues()
     */
    @Override
    public Collection<V> values() {
        return Collections.unmodifiableCollection(exposedValues());
    }
    
}
