/*
 * File:    MapCollectorsTest.java
 * Package: commons.lambda.stream.collector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.collector;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import commons.object.collection.MapUtility;
import commons.object.string.StringUtility;
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
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({MapCollectors.class})
public class MapCollectorsTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MapCollectorsTest.class);
    
    
    //Fields
    
    /**
     * A set of streams to use for testing.
     */
    private Stream<?>[] testStreams;
    
    /**
     * A set of maps corresponding to the streams to use for testing.
     */
    private Map<?, ?>[] testMaps;
    
    
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
    @Before
    public void setup() throws Exception {
        testStreams = new Stream<?>[] {
                Stream.of("hello", "world", "test"),
                Stream.of("test:test", "key:value", "other:another", "test:else"),
                Stream.of(1, 4, 11),
                Stream.of(List.of(0, "test"), List.of(9, "value"), List.of(-4, "another"), List.of(10, "else"))};
        testMaps = new Map<?, ?>[] {
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
     * JUnit test of toMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toMap(Supplier, Function, Function)
     * @see MapCollectors#toMap(Supplier)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToMap() throws Exception {
        final Stream<String> testStream0 = (Stream<String>) testStreams[0];
        final Stream<String> testStream1 = (Stream<String>) testStreams[1];
        final Stream<Integer> testStream2 = (Stream<Integer>) testStreams[2];
        final Stream<List<Object>> testStream3 = (Stream<List<Object>>) testStreams[3];
        final Map<String, String> testMap0 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap2 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap3 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map0;
        Map<String, String> map1;
        Map<Integer, Boolean> map2;
        Map<Integer, String> map3;
        
        //standard
        map0 = testStream0.collect(MapCollectors.toMap(HashMap::new,
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map0);
        Assert.assertTrue(map0 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap0, map0));
        map1 = testStream1.collect(MapCollectors.toMap(HashMap::new,
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap1, map1));
        map2 = testStream2.collect(MapCollectors.toMap(LinkedHashMap::new,
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap2, map2));
        map3 = testStream3.collect(MapCollectors.toMap(TreeMap::new,
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof TreeMap);
        Assert.assertTrue(MapUtility.equals(testMap3, map3));
        
        //map enties
        map0 = testMap0.entrySet().stream().collect(MapCollectors.toMap(HashMap::new));
        Assert.assertNotNull(map0);
        Assert.assertTrue(map0 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap0, map0));
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toMap(HashMap::new));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap1, map1));
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toMap(LinkedHashMap::new));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap2, map2));
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toMap(TreeMap::new));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof TreeMap);
        Assert.assertTrue(MapUtility.equals(testMap3, map3));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(null, Function.identity(), Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap::new, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(HashMap::new, Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toMap(null, null, null)));
    }
    
    /**
     * JUnit test of toHashMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toHashMap(Function, Function)
     * @see MapCollectors#toHashMap()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToHashMap() throws Exception {
        final Stream<String> testStream0 = (Stream<String>) testStreams[0];
        final Stream<String> testStream1 = (Stream<String>) testStreams[1];
        final Stream<Integer> testStream2 = (Stream<Integer>) testStreams[2];
        final Stream<List<Object>> testStream3 = (Stream<List<Object>>) testStreams[3];
        final Map<String, String> testMap0 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap2 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap3 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map0;
        Map<String, String> map1;
        Map<Integer, Boolean> map2;
        Map<Integer, String> map3;
        
        //standard
        map0 = testStream0.collect(MapCollectors.toHashMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map0);
        Assert.assertTrue(map0 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap0, map0));
        map1 = testStream1.collect(MapCollectors.toHashMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap1, map1));
        map2 = testStream2.collect(MapCollectors.toHashMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap2, map2));
        map3 = testStream3.collect(MapCollectors.toHashMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap3, map3));
        
        //map enties
        map0 = testMap0.entrySet().stream().collect(MapCollectors.toHashMap());
        Assert.assertNotNull(map0);
        Assert.assertTrue(map0 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap0, map0));
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toHashMap());
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap1, map1));
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toHashMap());
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap2, map2));
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toHashMap());
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap3, map3));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toHashMap(null, null)));
    }
    
    /**
     * JUnit test of toLinkedHashMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toLinkedHashMap(Function, Function)
     * @see MapCollectors#toLinkedHashMap()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToLinkedHashMap() throws Exception {
        final Stream<String> testStream0 = (Stream<String>) testStreams[0];
        final Stream<String> testStream1 = (Stream<String>) testStreams[1];
        final Stream<Integer> testStream2 = (Stream<Integer>) testStreams[2];
        final Stream<List<Object>> testStream3 = (Stream<List<Object>>) testStreams[3];
        final Map<String, String> testMap0 = (Map<String, String>) testMaps[0];
        final Map<String, String> testMap1 = (Map<String, String>) testMaps[1];
        final Map<Integer, Boolean> testMap2 = (Map<Integer, Boolean>) testMaps[2];
        final Map<Integer, String> testMap3 = (Map<Integer, String>) testMaps[3];
        Map<String, String> map0;
        Map<String, String> map1;
        Map<Integer, Boolean> map2;
        Map<Integer, String> map3;
        
        //standard
        map0 = testStream0.collect(MapCollectors.toLinkedHashMap(
                Function.identity(), Function.identity()));
        Assert.assertNotNull(map0);
        Assert.assertTrue(map0 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap0, map0));
        map1 = testStream1.collect(MapCollectors.toLinkedHashMap(
                (e -> StringUtility.lSnip(e, e.indexOf(':'))), (e -> StringUtility.rSnip(e, (e.length() - e.indexOf(':') - 1)))));
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap1, map1));
        map2 = testStream2.collect(MapCollectors.toLinkedHashMap(
                Function.identity(), (e -> (e > 3))));
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap2, map2));
        map3 = testStream3.collect(MapCollectors.toLinkedHashMap(
                (e -> (int) e.get(0)), (e -> (String) e.get(1))));
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap3, map3));
        
        //map enties
        map0 = testMap0.entrySet().stream().collect(MapCollectors.toLinkedHashMap());
        Assert.assertNotNull(map0);
        Assert.assertTrue(map0 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap0, map0));
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toLinkedHashMap());
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap1, map1));
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toLinkedHashMap());
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap2, map2));
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toLinkedHashMap());
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof LinkedHashMap);
        Assert.assertTrue(MapUtility.equals(testMap3, map3));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(Function.identity(), null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(MapCollectors.toLinkedHashMap(null, null)));
    }
    
    /**
     * JUnit test of toStringMap.
     *
     * @throws Exception When there is an exception.
     * @see MapCollectors#toStringMap()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToStringMap() throws Exception {
        final Stream<String> testStream0 = (Stream<String>) testStreams[0];
        final Stream<String> testStream1 = (Stream<String>) testStreams[1];
        final Stream<Integer> testStream2 = (Stream<Integer>) testStreams[2];
        final Stream<List<Object>> testStream3 = (Stream<List<Object>>) testStreams[3];
        final Map<String, String> testMap0 = testMaps[0].entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
        final Map<String, String> testMap1 = testMaps[1].entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
        final Map<String, String> testMap2 = testMaps[2].entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
        final Map<String, String> testMap3 = testMaps[3].entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
        Map<String, String> map0;
        Map<String, String> map1;
        Map<String, String> map2;
        Map<String, String> map3;
        
        //map enties
        map0 = testMap0.entrySet().stream().collect(MapCollectors.toStringMap());
        Assert.assertNotNull(map0);
        Assert.assertTrue(map0 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap0, map0));
        map1 = testMap1.entrySet().stream().collect(MapCollectors.toStringMap());
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap1, map1));
        map2 = testMap2.entrySet().stream().collect(MapCollectors.toStringMap());
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap2, map2));
        map3 = testMap3.entrySet().stream().collect(MapCollectors.toStringMap());
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof HashMap);
        Assert.assertTrue(MapUtility.equals(testMap3, map3));
    }
    
}
