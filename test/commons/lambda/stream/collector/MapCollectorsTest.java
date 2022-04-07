/*
 * File:    MapCollectorsTest.java
 * Package: commons.lambda.stream.collector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.collector;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import commons.object.collection.MapUtility;
import commons.object.string.StringUtility;
import commons.test.TestAccess;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of MapCollectors.
 *
 * @see MapCollectors
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({MapCollectors.class})
public class MapCollectorsTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MapCollectorsTest.class);
    
    
    //Static Fields
    
    /**
     * A set of lists containing the elements of the streams to use for testing.
     */
    private static final List<?>[] testStreamElements = new List<?>[] {
            List.of("hello", "world", "test"),
            List.of("test:test", "key:value", "other:another", "test:else"),
            List.of(1, 4, 11),
            List.of(List.of(0, "test"), List.of(9, "value"), List.of(-4, "another"), List.of(10, "else"))};
    
    /**
     * A set of maps corresponding to the streams to use for testing.
     */
    private static final Map<?, ?>[] testMaps = new Map<?, ?>[] {
            MapUtility.mapOf(
                    new String[] {"hello", "world", "test"},
                    new String[] {"hello", "world", "test"}),
            MapUtility.mapOf(
                    new String[] {"test", "key", "other"},
                    new String[] {"else", "value", "another"}),
            MapUtility.mapOf(
                    new Integer[] {1, 4, 11},
                    new Boolean[] {false, true, true}),
            MapUtility.mapOf(
                    new Integer[] {0, 9, -4, 10},
                    new String[] {"test", "value", "another", "else"})};
    
    /**
     * The UnmodifiableMap class.
     */
    private static final Class<?> UnmodifiableMap = TestAccess.getClass(Collections.class, "UnmodifiableMap");
    
    /**
     * The SynchronizedMap class.
     */
    private static final Class<?> SynchronizedMap = TestAccess.getClass(Collections.class, "SynchronizedMap");
    
    
    //Initialization
    
    /**
     * The JUnit class setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @BeforeClass
    public static void setupClass() throws Exception {
    }
    
    /**
     * The JUnit class cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @AfterClass
    public static void cleanupClass() throws Exception {
    }
    
    /**
     * The JUnit setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Before
    public void setup() throws Exception {
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @After
    public void cleanup() throws Exception {
    }
    
    
    //Tests
    
    /**
     * JUnit test of constants.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of MapFlavor.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors.MapFlavor
     */
    @Test
    public void testMapFlavor() throws Exception {
        final Class<?> MapFlavor = MapCollectors.MapFlavor.class;
        final Object[] mapFlavors = MapFlavor.getEnumConstants();
        
        //values
        Assert.assertEquals(3, mapFlavors.length);
        Assert.assertEquals(MapCollectors.MapFlavor.STANDARD, mapFlavors[0]);
        Assert.assertEquals(MapCollectors.MapFlavor.UNMODIFIABLE, mapFlavors[1]);
        Assert.assertEquals(MapCollectors.MapFlavor.SYNCHRONIZED, mapFlavors[2]);
        
        //fields
        TestUtils.assertFieldExists(MapFlavor, "styler");
        Assert.assertTrue(Arrays.stream(mapFlavors).map(e -> TestAccess.getFieldValue(e, "styler")).allMatch(Objects::nonNull));
        
        //methods
        TestUtils.assertConstructorExists(MapFlavor, String.class, int.class, Function.class);
        TestUtils.assertMethodExists(MapFlavor, "apply", Map.class);
    }
    
    /**
     * JUnit test of toMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toMap(Supplier, MapCollectors.MapFlavor, Function, Function)
     * @see MapCollectors#toMap(Supplier, Function, Function)
     * @see MapCollectors#toMap(Class, MapCollectors.MapFlavor, Function, Function)
     * @see MapCollectors#toMap(Class, Function, Function)
     * @see MapCollectors#toMap(Supplier, MapCollectors.MapFlavor)
     * @see MapCollectors#toMap(Supplier)
     * @see MapCollectors#toMap(Class, MapCollectors.MapFlavor)
     * @see MapCollectors#toMap(Class)
     */
    @Test
    public void testToMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toMap(HashMap::new,
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toMap(HashMap::new,
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toMap(LinkedHashMap::new,
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toMap(TreeMap::new,
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //flavor
        map1 = testElements1.stream().collect(MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.STANDARD,
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.UNMODIFIABLE,
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toMap(LinkedHashMap::new, MapCollectors.MapFlavor.SYNCHRONIZED,
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toMap(TreeMap::new, MapCollectors.MapFlavor.STANDARD,
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //class
        map1 = testElements1.stream().collect(MapCollectors.toMap(HashMap.class,
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toMap(HashMap.class,
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toMap(LinkedHashMap.class,
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toMap(TreeMap.class,
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //class, flavor
        map1 = testElements1.stream().collect(MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.STANDARD,
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.UNMODIFIABLE,
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toMap(LinkedHashMap.class, MapCollectors.MapFlavor.SYNCHRONIZED,
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toMap(TreeMap.class, MapCollectors.MapFlavor.STANDARD,
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toMap(HashMap::new));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toMap(HashMap::new));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toMap(LinkedHashMap::new));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toMap(TreeMap::new));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity, flavor
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toMap(LinkedHashMap::new, MapCollectors.MapFlavor.SYNCHRONIZED));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toMap(TreeMap::new, MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //class, identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toMap(HashMap.class));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toMap(HashMap.class));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toMap(LinkedHashMap.class));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toMap(TreeMap.class));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //class, identity, flavor
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toMap(LinkedHashMap.class, MapCollectors.MapFlavor.SYNCHRONIZED));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toMap(TreeMap.class, MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()),
                MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toMap(HashMap::new, Function.identity(), Function.identity()),
                MapCollectors.toMap(HashMap::new, Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()),
                MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toMap(HashMap.class, Function.identity(), Function.identity()),
                MapCollectors.toMap(HashMap.class, Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.UNMODIFIABLE),
                MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                MapCollectors.toMap(HashMap::new),
                MapCollectors.toMap(HashMap::new));
        Assert.assertNotSame(
                MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.UNMODIFIABLE),
                MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                MapCollectors.toMap(HashMap.class),
                MapCollectors.toMap(HashMap.class));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap((Supplier<Map<Integer, Integer>>) null, MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap::new, null, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.UNMODIFIABLE, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap::new, MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap((Supplier<Map<Integer, Integer>>) null, null, null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap((Supplier<Map<Integer, Integer>>) null, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap::new, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap::new, Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap((Supplier<Map<Integer, Integer>>) null, null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap((Class<Map<Integer, Integer>>) null, MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap.class, null, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.UNMODIFIABLE, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap.class, MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap((Class<Map<Integer, Integer>>) null, null, null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap((Class<Map<Integer, Integer>>) null, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap.class, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap.class, Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap((Class<Map<Integer, Integer>>) null, null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3)).collect(MapCollectors.toMap((Supplier<Map<Integer, Integer>>) null, MapCollectors.MapFlavor.UNMODIFIABLE)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3)).collect(MapCollectors.toMap(HashMap::new, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3)).collect(MapCollectors.toMap((Supplier<Map<Integer, Integer>>) null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3)).collect(MapCollectors.toMap((Class<Map<Integer, Integer>>) null, MapCollectors.MapFlavor.UNMODIFIABLE)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3)).collect(MapCollectors.toMap(HashMap.class, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3)).collect(MapCollectors.toMap((Class<Map<Integer, Integer>>) null, null)));
    }
    
    /**
     * JUnit test of generator.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#generator(Class)
     * @see MapCollectors#generator(Class, Class, Class)
     * @see MapCollectors#generator()
     */
    @Test
    public void testGenerator() throws Exception {
        Map<Object, Object> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        
        //standard
        map1 = MapCollectors.generator(HashMap.class).get();
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        map1 = MapCollectors.generator(LinkedHashMap.class).get();
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        map1 = MapCollectors.generator(TreeMap.class).get();
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof TreeMap);
        map1 = MapCollectors.generator().get();
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        
        //type
        map2 = MapCollectors.generator(HashMap.class, String.class, String.class).get();
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        map2 = MapCollectors.generator(LinkedHashMap.class, String.class, String.class).get();
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        map3 = MapCollectors.generator(TreeMap.class, Integer.class, Boolean.class).get();
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof TreeMap);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.generator(HashMap.class, String.class, String.class),
                MapCollectors.generator(HashMap.class, String.class, String.class));
        Assert.assertNotSame(
                MapCollectors.generator(HashMap.class),
                MapCollectors.generator(HashMap.class));
        Assert.assertNotSame(
                MapCollectors.generator(),
                MapCollectors.generator());
        
        //invalid
        Assert.assertNotNull(MapCollectors.generator(TreeMap.class, Color.class, String.class).get());
        Assert.assertNotNull(MapCollectors.generator(HashMap.class, null, null).get());
        TestUtils.assertNoException(() ->
                MapCollectors.generator(null, String.class, String.class));
        TestUtils.assertException(NullPointerException.class, () ->
                MapCollectors.generator(null, String.class, String.class).get());
        TestUtils.assertNoException(() ->
                MapCollectors.generator(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapCollectors.generator(null, null, null).get());
        TestUtils.assertNoException(() ->
                MapCollectors.generator(null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapCollectors.generator(null).get());
    }
    
    /**
     * JUnit test of toHashMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toHashMap(MapCollectors.MapFlavor, Function, Function)
     * @see MapCollectors#toHashMap(MapCollectors.MapFlavor)
     * @see MapCollectors#toHashMap(Function, Function)
     * @see MapCollectors#toHashMap()
     */
    @Test
    public void testToHashMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toHashMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toHashMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toHashMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toHashMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof HashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //flavor
        map1 = testElements1.stream().collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.STANDARD,
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.UNMODIFIABLE,
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.SYNCHRONIZED,
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.STANDARD,
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof HashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toHashMap());
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toHashMap());
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toHashMap());
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toHashMap());
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof HashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity, flavor
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.SYNCHRONIZED));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof HashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toHashMap(MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()),
                MapCollectors.toHashMap(MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toHashMap(MapCollectors.MapFlavor.UNMODIFIABLE),
                MapCollectors.toHashMap(MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                MapCollectors.toHashMap(Function.identity(), Function.identity()),
                MapCollectors.toHashMap(Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toHashMap(),
                MapCollectors.toHashMap());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(null, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.UNMODIFIABLE, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(null, null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3)).collect(MapCollectors.toHashMap(null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(null, null)));
    }
    
    /**
     * JUnit test of toUnmodifiableHashMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toUnmodifiableHashMap(Function, Function)
     * @see MapCollectors#toUnmodifiableHashMap()
     */
    @Test
    public void testToUnmodifiableHashMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toUnmodifiableHashMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertEquals(UnmodifiableMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toUnmodifiableHashMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toUnmodifiableHashMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(UnmodifiableMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toUnmodifiableHashMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertEquals(UnmodifiableMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toUnmodifiableHashMap());
        Assert.assertNotNull(map1);
        Assert.assertEquals(UnmodifiableMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toUnmodifiableHashMap());
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toUnmodifiableHashMap());
        Assert.assertNotNull(map3);
        Assert.assertEquals(UnmodifiableMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toUnmodifiableHashMap());
        Assert.assertNotNull(map4);
        Assert.assertEquals(UnmodifiableMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toUnmodifiableHashMap(Function.identity(), Function.identity()),
                MapCollectors.toUnmodifiableHashMap(Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toUnmodifiableHashMap(),
                MapCollectors.toUnmodifiableHashMap());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toUnmodifiableHashMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toUnmodifiableHashMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toUnmodifiableHashMap(null, null)));
    }
    
    /**
     * JUnit test of toSynchronizedHashMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toSynchronizedHashMap(Function, Function)
     * @see MapCollectors#toSynchronizedHashMap()
     */
    @Test
    public void testToSynchronizedHashMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toSynchronizedHashMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertEquals(SynchronizedMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toSynchronizedHashMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(SynchronizedMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toSynchronizedHashMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toSynchronizedHashMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertEquals(SynchronizedMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toSynchronizedHashMap());
        Assert.assertNotNull(map1);
        Assert.assertEquals(SynchronizedMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toSynchronizedHashMap());
        Assert.assertNotNull(map2);
        Assert.assertEquals(SynchronizedMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toSynchronizedHashMap());
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toSynchronizedHashMap());
        Assert.assertNotNull(map4);
        Assert.assertEquals(SynchronizedMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toSynchronizedHashMap(Function.identity(), Function.identity()),
                MapCollectors.toSynchronizedHashMap(Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toSynchronizedHashMap(),
                MapCollectors.toSynchronizedHashMap());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toSynchronizedHashMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toSynchronizedHashMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toSynchronizedHashMap(null, null)));
    }
    
    /**
     * JUnit test of toLinkedHashMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toLinkedHashMap(MapCollectors.MapFlavor, Function, Function)
     * @see MapCollectors#toLinkedHashMap(MapCollectors.MapFlavor)
     * @see MapCollectors#toLinkedHashMap(Function, Function)
     * @see MapCollectors#toLinkedHashMap()
     */
    @Test
    public void testToLinkedHashMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toLinkedHashMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toLinkedHashMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toLinkedHashMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toLinkedHashMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //flavor
        map1 = testElements1.stream().collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.STANDARD,
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.UNMODIFIABLE,
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.SYNCHRONIZED,
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.STANDARD,
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toLinkedHashMap());
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toLinkedHashMap());
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toLinkedHashMap());
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toLinkedHashMap());
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity, flavor
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.SYNCHRONIZED));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()),
                MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.UNMODIFIABLE),
                MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                MapCollectors.toLinkedHashMap(Function.identity(), Function.identity()),
                MapCollectors.toLinkedHashMap(Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toLinkedHashMap(),
                MapCollectors.toLinkedHashMap());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(null, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.UNMODIFIABLE, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(null, null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3)).collect(MapCollectors.toLinkedHashMap(null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(null, null)));
    }
    
    /**
     * JUnit test of toUnmodifiableLinkedHashMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toUnmodifiableLinkedHashMap(Function, Function)
     * @see MapCollectors#toUnmodifiableLinkedHashMap()
     */
    @Test
    public void testToUnmodifiableLinkedHashMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toUnmodifiableLinkedHashMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertEquals(UnmodifiableMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toUnmodifiableLinkedHashMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toUnmodifiableLinkedHashMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(UnmodifiableMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toUnmodifiableLinkedHashMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertEquals(UnmodifiableMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toUnmodifiableLinkedHashMap());
        Assert.assertNotNull(map1);
        Assert.assertEquals(UnmodifiableMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toUnmodifiableLinkedHashMap());
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toUnmodifiableLinkedHashMap());
        Assert.assertNotNull(map3);
        Assert.assertEquals(UnmodifiableMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toUnmodifiableLinkedHashMap());
        Assert.assertNotNull(map4);
        Assert.assertEquals(UnmodifiableMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toUnmodifiableLinkedHashMap(Function.identity(), Function.identity()),
                MapCollectors.toUnmodifiableLinkedHashMap(Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toUnmodifiableLinkedHashMap(),
                MapCollectors.toUnmodifiableLinkedHashMap());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toUnmodifiableLinkedHashMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toUnmodifiableLinkedHashMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toUnmodifiableLinkedHashMap(null, null)));
    }
    
    /**
     * JUnit test of toSynchronizedLinkedHashMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toSynchronizedLinkedHashMap(Function, Function)
     * @see MapCollectors#toSynchronizedLinkedHashMap()
     */
    @Test
    public void testToSynchronizedLinkedHashMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toSynchronizedLinkedHashMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertEquals(SynchronizedMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toSynchronizedLinkedHashMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(SynchronizedMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toSynchronizedLinkedHashMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toSynchronizedLinkedHashMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertEquals(SynchronizedMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toSynchronizedLinkedHashMap());
        Assert.assertNotNull(map1);
        Assert.assertEquals(SynchronizedMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toSynchronizedLinkedHashMap());
        Assert.assertNotNull(map2);
        Assert.assertEquals(SynchronizedMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toSynchronizedLinkedHashMap());
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toSynchronizedLinkedHashMap());
        Assert.assertNotNull(map4);
        Assert.assertEquals(SynchronizedMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toSynchronizedLinkedHashMap(Function.identity(), Function.identity()),
                MapCollectors.toSynchronizedLinkedHashMap(Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toSynchronizedLinkedHashMap(),
                MapCollectors.toSynchronizedLinkedHashMap());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toSynchronizedLinkedHashMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toSynchronizedLinkedHashMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toSynchronizedLinkedHashMap(null, null)));
    }
    
    /**
     * JUnit test of toTreeMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toTreeMap(MapCollectors.MapFlavor, Function, Function)
     * @see MapCollectors#toTreeMap(MapCollectors.MapFlavor)
     * @see MapCollectors#toTreeMap(Function, Function)
     * @see MapCollectors#toTreeMap()
     */
    @Test
    public void testToTreeMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toTreeMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof TreeMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toTreeMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof TreeMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toTreeMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof TreeMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toTreeMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //flavor
        map1 = testElements1.stream().collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.STANDARD,
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof TreeMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.UNMODIFIABLE,
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.SYNCHRONIZED,
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.STANDARD,
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toTreeMap());
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof TreeMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toTreeMap());
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof TreeMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toTreeMap());
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof TreeMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toTreeMap());
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity, flavor
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof TreeMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.SYNCHRONIZED));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.STANDARD));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof TreeMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toTreeMap(MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()),
                MapCollectors.toTreeMap(MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toTreeMap(MapCollectors.MapFlavor.UNMODIFIABLE),
                MapCollectors.toTreeMap(MapCollectors.MapFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                MapCollectors.toTreeMap(Function.identity(), Function.identity()),
                MapCollectors.toTreeMap(Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toTreeMap(),
                MapCollectors.toTreeMap());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toTreeMap(null, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.UNMODIFIABLE, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toTreeMap(MapCollectors.MapFlavor.UNMODIFIABLE, Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toTreeMap(null, null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(Map.entry(1, 1), Map.entry(2, 2), Map.entry(3, 3)).collect(MapCollectors.toTreeMap(null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toTreeMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toTreeMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toTreeMap(null, null)));
    }
    
    /**
     * JUnit test of toUnmodifiableTreeMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toUnmodifiableTreeMap(Function, Function)
     * @see MapCollectors#toUnmodifiableTreeMap()
     */
    @Test
    public void testToUnmodifiableTreeMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toUnmodifiableTreeMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertEquals(UnmodifiableMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toUnmodifiableTreeMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toUnmodifiableTreeMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(UnmodifiableMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toUnmodifiableTreeMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertEquals(UnmodifiableMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toUnmodifiableTreeMap());
        Assert.assertNotNull(map1);
        Assert.assertEquals(UnmodifiableMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toUnmodifiableTreeMap());
        Assert.assertNotNull(map2);
        Assert.assertEquals(UnmodifiableMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toUnmodifiableTreeMap());
        Assert.assertNotNull(map3);
        Assert.assertEquals(UnmodifiableMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toUnmodifiableTreeMap());
        Assert.assertNotNull(map4);
        Assert.assertEquals(UnmodifiableMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toUnmodifiableTreeMap(Function.identity(), Function.identity()),
                MapCollectors.toUnmodifiableTreeMap(Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toUnmodifiableTreeMap(),
                MapCollectors.toUnmodifiableTreeMap());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toUnmodifiableTreeMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toUnmodifiableTreeMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toUnmodifiableTreeMap(null, null)));
    }
    
    /**
     * JUnit test of toSynchronizedTreeMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toSynchronizedTreeMap(Function, Function)
     * @see MapCollectors#toSynchronizedTreeMap()
     */
    @Test
    public void testToSynchronizedTreeMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap2 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap3 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap4 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map1;
        Map<String, String> map2;
        Map<Integer, Boolean> map3;
        Map<Integer, String> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toSynchronizedTreeMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map1);
        Assert.assertEquals(SynchronizedMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toSynchronizedTreeMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map2);
        Assert.assertEquals(SynchronizedMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toSynchronizedTreeMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toSynchronizedTreeMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map4);
        Assert.assertEquals(SynchronizedMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toSynchronizedTreeMap());
        Assert.assertNotNull(map1);
        Assert.assertEquals(SynchronizedMap, map1.getClass());
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toSynchronizedTreeMap());
        Assert.assertNotNull(map2);
        Assert.assertEquals(SynchronizedMap, map2.getClass());
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toSynchronizedTreeMap());
        Assert.assertNotNull(map3);
        Assert.assertEquals(SynchronizedMap, map3.getClass());
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toSynchronizedTreeMap());
        Assert.assertNotNull(map4);
        Assert.assertEquals(SynchronizedMap, map4.getClass());
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toSynchronizedTreeMap(Function.identity(), Function.identity()),
                MapCollectors.toSynchronizedTreeMap(Function.identity(), Function.identity()));
        Assert.assertNotSame(
                MapCollectors.toSynchronizedTreeMap(),
                MapCollectors.toSynchronizedTreeMap());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toSynchronizedTreeMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toSynchronizedTreeMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toSynchronizedTreeMap(null, null)));
    }
    
    /**
     * JUnit test of mapEachTo.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#mapEachTo(Function)
     * @see MapCollectors#mapEachTo(Supplier)
     * @see MapCollectors#mapEachTo(Object)
     */
    @Test
    public void testMapEachTo() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, Integer> testMap1 = ((List<String>) testStreamElements[0]).stream().collect(Collectors.toMap(Function.identity(), (e -> 0)));
        final Map<String, Integer> testMap2 = ((List<String>) testStreamElements[1]).stream().collect(Collectors.toMap(Function.identity(), String::length));
        final Map<Integer, Long> testMap3 = ((List<Integer>) testStreamElements[2]).stream().collect(Collectors.toMap(Function.identity(), Long::valueOf));
        final Map<List<Object>, AtomicInteger> testMap4 = ((List<List<Object>>) testStreamElements[3]).stream().collect(Collectors.toMap(Function.identity(), (e -> new AtomicInteger(0))));
        Map<String, Integer> map1;
        Map<String, Integer> map2;
        Map<Integer, Long> map3;
        Map<List<Object>, AtomicInteger> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.mapEachTo(
                0));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.mapEachTo(
                String::length));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.mapEachTo(
                Long::valueOf));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.mapEachTo(
                () -> new AtomicInteger(0)));
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof HashMap);
        TestUtils.assertArrayEquals(
                map4.keySet().toArray(),
                testMap4.keySet().toArray());
        TestUtils.assertArrayEquals(
                map4.values().stream().map(AtomicInteger::get).toArray(),
                testMap4.values().stream().map(AtomicInteger::get).toArray());
        Assert.assertEquals(map4.size(), map4.values().stream().distinct().count());
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.mapEachTo(e -> 0),
                MapCollectors.mapEachTo(e -> 0));
        Assert.assertNotSame(
                MapCollectors.mapEachTo(() -> 0),
                MapCollectors.mapEachTo(() -> 0));
        Assert.assertNotSame(
                MapCollectors.mapEachTo(0),
                MapCollectors.mapEachTo(0));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.mapEachTo((Function<? super Object, ?>) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.mapEachTo((Supplier<?>) null)));
        TestUtils.assertNoException(() ->
                Stream.of(1, 2, 3).collect(MapCollectors.mapEachTo((Object) null)));
    }
    
    /**
     * JUnit test of toCounterMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toCounterMap()
     */
    @Test
    public void testToCounterMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, Integer> testMap1 = ((List<String>) testStreamElements[0]).stream().collect(Collectors.toMap(Function.identity(), (e -> 0)));
        final Map<String, Integer> testMap2 = ((List<String>) testStreamElements[1]).stream().collect(Collectors.toMap(Function.identity(), (e -> 0)));
        final Map<Integer, Integer> testMap3 = ((List<Integer>) testStreamElements[2]).stream().collect(Collectors.toMap(Function.identity(), (e -> 0)));
        final Map<List<Object>, Integer> testMap4 = ((List<List<Object>>) testStreamElements[3]).stream().collect(Collectors.toMap(Function.identity(), (e -> 0)));
        Map<String, Integer> map1;
        Map<String, Integer> map2;
        Map<Integer, Integer> map3;
        Map<List<Object>, Integer> map4;
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toCounterMap());
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toCounterMap());
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toCounterMap());
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toCounterMap());
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof HashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toCounterMap(),
                MapCollectors.toCounterMap());
    }
    
    /**
     * JUnit test of toAtomicCounterMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toAtomicCounterMap()
     */
    @Test
    public void testToAtomicCounterMap() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Map<String, AtomicInteger> testMap1 = ((List<String>) testStreamElements[0]).stream().collect(Collectors.toMap(Function.identity(), (e -> new AtomicInteger(0))));
        final Map<String, AtomicInteger> testMap2 = ((List<String>) testStreamElements[1]).stream().collect(Collectors.toMap(Function.identity(), (e -> new AtomicInteger(0))));
        final Map<Integer, AtomicInteger> testMap3 = ((List<Integer>) testStreamElements[2]).stream().collect(Collectors.toMap(Function.identity(), (e -> new AtomicInteger(0))));
        final Map<List<Object>, AtomicInteger> testMap4 = ((List<List<Object>>) testStreamElements[3]).stream().collect(Collectors.toMap(Function.identity(), (e -> new AtomicInteger(0))));
        Map<String, AtomicInteger> map1;
        Map<String, AtomicInteger> map2;
        Map<Integer, AtomicInteger> map3;
        Map<List<Object>, AtomicInteger> map4;
        
        final BiConsumer<Map<?, AtomicInteger>, Map<?, AtomicInteger>> atomicCounterMapVerifier = (Map<?, AtomicInteger> map, Map<?, AtomicInteger> testMap) -> {
            TestUtils.assertArrayEquals(
                    map.keySet().toArray(),
                    testMap.keySet().toArray());
            TestUtils.assertArrayEquals(
                    map.values().stream().map(AtomicInteger::get).toArray(),
                    testMap.values().stream().map(AtomicInteger::get).toArray());
            Assert.assertEquals(map.size(), map.values().stream().distinct().count());
        };
        
        //standard
        map1 = testElements1.stream().collect(MapCollectors.toAtomicCounterMap());
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        atomicCounterMapVerifier.accept(map1, testMap1);
        map2 = testElements2.stream().collect(MapCollectors.toAtomicCounterMap());
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        atomicCounterMapVerifier.accept(map2, testMap2);
        map3 = testElements3.stream().collect(MapCollectors.toAtomicCounterMap());
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        atomicCounterMapVerifier.accept(map3, testMap3);
        map4 = testElements4.stream().collect(MapCollectors.toAtomicCounterMap());
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof HashMap);
        atomicCounterMapVerifier.accept(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toAtomicCounterMap(),
                MapCollectors.toAtomicCounterMap());
    }
    
    /**
     * JUnit test of toStringMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toStringMap()
     */
    @Test
    public void testToStringMap() throws Exception {
        final Map<String, String> testMap1 = testMaps[0].entrySet().stream().collect(Collectors.toMap((e -> e.getKey().toString()), (e -> e.getValue().toString())));
        final Map<String, String> testMap2 = testMaps[1].entrySet().stream().collect(Collectors.toMap((e -> e.getKey().toString()), (e -> e.getValue().toString())));
        final Map<String, String> testMap3 = testMaps[2].entrySet().stream().collect(Collectors.toMap((e -> e.getKey().toString()), (e -> e.getValue().toString())));
        final Map<String, String> testMap4 = testMaps[3].entrySet().stream().collect(Collectors.toMap((e -> e.getKey().toString()), (e -> e.getValue().toString())));
        Map<String, String> map1;
        Map<String, String> map2;
        Map<String, String> map3;
        Map<String, String> map4;
        
        //identity
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toStringMap());
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        TestUtils.assertMapEquals(map1, testMap1);
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toStringMap());
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        TestUtils.assertMapEquals(map2, testMap2);
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toStringMap());
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        TestUtils.assertMapEquals(map3, testMap3);
        map4 = testMap4.entrySet().stream().collect(MapCollectors.toStringMap());
        Assert.assertNotNull(map4);
        Assert.assertTrue(map4 instanceof HashMap);
        TestUtils.assertMapEquals(map4, testMap4);
        
        //uniqueness
        Assert.assertNotSame(
                MapCollectors.toStringMap(),
                MapCollectors.toStringMap());
    }
    
}
