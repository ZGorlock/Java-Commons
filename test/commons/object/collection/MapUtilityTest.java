/*
 * File:    MapUtilityTest.java
 * Package: commons.object.collection
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import commons.test.TestUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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
 * JUnit test of MapUtility.
 *
 * @see MapUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({MapUtility.class})
public class MapUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MapUtilityTest.class);
    
    
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
     * @see MapUtility#DEFAULT_MAP_CLASS
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(HashMap.class, TestUtils.getField(MapUtility.class, "DEFAULT_MAP_CLASS"));
    }
    
    /**
     * JUnit test of create.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#create(Class)
     * @see MapUtility#create(Class, Class, Class)
     * @see MapUtility#create(Class, Class)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate() throws Exception {
        //int, string
        Map<Integer, String> integerStringMap = MapUtility.create(Integer.class, String.class);
        Assert.assertNotNull(integerStringMap);
        Assert.assertTrue(integerStringMap instanceof HashMap);
        Assert.assertTrue(integerStringMap.isEmpty());
        
        //string, boolean
        Map<String, Boolean> stringBooleanMap = MapUtility.create(HashMap.class, String.class, Boolean.class);
        Assert.assertNotNull(stringBooleanMap);
        Assert.assertTrue(stringBooleanMap instanceof HashMap);
        Assert.assertTrue(stringBooleanMap.isEmpty());
        
        //string, string
        Map<String, String> stringStringMap = MapUtility.create(LinkedHashMap.class, String.class, String.class);
        Assert.assertNotNull(stringStringMap);
        Assert.assertTrue(stringStringMap instanceof LinkedHashMap);
        Assert.assertTrue(stringStringMap.isEmpty());
        
        //long, object
        Map<Long, Object> longObjectMap = MapUtility.create(TreeMap.class, Long.class, Object.class);
        Assert.assertNotNull(longObjectMap);
        Assert.assertTrue(longObjectMap instanceof TreeMap);
        Assert.assertTrue(longObjectMap.isEmpty());
        
        //invalid
        TestUtils.assertException(ClassCastException.class, "class java.awt.Color cannot be cast to class java.lang.Comparable", () ->
                MapUtility.create(TreeMap.class, Color.class, String.class));
        TestUtils.assertNoException(() ->
                MapUtility.create(HashMap.class, null, null));
        TestUtils.assertNoException(() ->
                MapUtility.create(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.create(null, Object.class, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.create(null, null, null));
    }
    
    /**
     * JUnit test of mapOf.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#mapOf(Class, Pair[])
     * @see MapUtility#mapOf(Class, List)
     * @see MapUtility#mapOf(Class, Object[], Object[])
     * @see MapUtility#mapOf(Class, List, List)
     * @see MapUtility#mapOf(Pair[])
     * @see MapUtility#mapOf(List)
     * @see MapUtility#mapOf(Object[], Object[])
     * @see MapUtility#mapOf(List, List)
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testMapOf() throws Exception {
        final String[] keysArray1 = new String[] {"string", "test", "key"};
        final String[] valuesArray1 = new String[] {"other", "test", "value"};
        final Integer[] keysArray2 = new Integer[] {1, 3, 9, 15};
        final Boolean[] valuesArray2 = new Boolean[] {false, false, true, false};
        final List<String> keysList1 = ListUtility.toList(keysArray1);
        final List<String> valuesList1 = ListUtility.toList(valuesArray1);
        final List<Integer> keysList2 = ListUtility.toList(keysArray2);
        final List<Boolean> valuesList2 = ListUtility.toList(valuesArray2);
        final List<Pair<String, String>> pairList1 = IntStream.range(0, keysList1.size()).boxed().map(i -> new ImmutablePair<>(keysList1.get(i), valuesList1.get(i))).collect(Collectors.toList());
        final List<Pair<Integer, Boolean>> pairList2 = IntStream.range(0, keysList2.size()).boxed().map(i -> new ImmutablePair<>(keysList2.get(i), valuesList2.get(i))).collect(Collectors.toList());
        final Pair<String, String>[] pairArray1 = pairList1.toArray(Pair[]::new);
        final Pair<Integer, Boolean>[] pairArray2 = pairList2.toArray(Pair[]::new);
        Map<String, String> map1;
        Map<Integer, Boolean> map2;
        
        //pair array
        map1 = MapUtility.mapOf(pairArray1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(HashMap.class, pairArray1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(LinkedHashMap.class, pairArray1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(TreeMap.class, pairArray1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof TreeMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map2 = MapUtility.mapOf(pairArray2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(HashMap.class, pairArray2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(LinkedHashMap.class, pairArray2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(TreeMap.class, pairArray2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof TreeMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        
        //pair list
        map1 = MapUtility.mapOf(pairList1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(HashMap.class, pairList1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(LinkedHashMap.class, pairList1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(TreeMap.class, pairList1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof TreeMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map2 = MapUtility.mapOf(pairList2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(HashMap.class, pairList2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(LinkedHashMap.class, pairList2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(TreeMap.class, pairList2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof TreeMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        
        //key/value array
        map1 = MapUtility.mapOf(keysArray1, valuesArray1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(HashMap.class, keysArray1, valuesArray1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(LinkedHashMap.class, keysArray1, valuesArray1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(TreeMap.class, keysArray1, valuesArray1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof TreeMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map2 = MapUtility.mapOf(keysArray2, valuesArray2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(HashMap.class, keysArray2, valuesArray2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(LinkedHashMap.class, keysArray2, valuesArray2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(TreeMap.class, keysArray2, valuesArray2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof TreeMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        
        //key/value list
        map1 = MapUtility.mapOf(keysList1, valuesList1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(HashMap.class, keysList1, valuesList1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof HashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(LinkedHashMap.class, keysList1, valuesList1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map1 = MapUtility.mapOf(TreeMap.class, keysList1, valuesList1);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof TreeMap);
        Assert.assertEquals(3, map1.size());
        Assert.assertEquals("other", map1.get("string"));
        Assert.assertEquals("test", map1.get("test"));
        Assert.assertEquals("value", map1.get("key"));
        map2 = MapUtility.mapOf(keysList2, valuesList2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(HashMap.class, keysList2, valuesList2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof HashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(LinkedHashMap.class, keysList2, valuesList2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        map2 = MapUtility.mapOf(TreeMap.class, keysList2, valuesList2);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof TreeMap);
        Assert.assertEquals(4, map2.size());
        Assert.assertFalse(map2.get(1));
        Assert.assertFalse(map2.get(3));
        Assert.assertTrue(map2.get(9));
        Assert.assertFalse(map2.get(15));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((Class<Map>) null, new Pair[] {new ImmutablePair<>(1, 1), new ImmutablePair<>(2, 2), new ImmutablePair<>(3, 3)}));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf(LinkedHashMap.class, (Pair[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((Class<Map>) null, Collections.emptyList()));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((Class) null, (Pair[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((Pair[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((Class<Map>) null, Arrays.asList(new ImmutablePair<>(1, 1), new ImmutablePair<>(2, 2), new ImmutablePair<>(3, 3))));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf(LinkedHashMap.class, (List) null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((Class<Map>) null, Collections.emptyList()));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((Class) null, (List) null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((List) null));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(LinkedHashMap.class, new Integer[] {1, 2, 3}, new Integer[] {1, 2}));
        TestUtils.assertException(ClassCastException.class, "class java.awt.Color cannot be cast to class java.lang.Comparable (java.awt.Color is in module java.desktop of loader 'bootstrap'; java.lang.Comparable is in module java.base of loader 'bootstrap')", () ->
                MapUtility.mapOf(TreeMap.class, new Color[] {Color.RED, Color.BLACK}, new Integer[] {1, 2}));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf(null, new Integer[] {1, 2, 3}, new Integer[] {1, 2, 3}));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(new Integer[] {1, 2, 3}, new Integer[] {1, 2}));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(new Integer[] {1, 2, 3}, new Integer[] {1, 2, 3, 4}));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(new Integer[] {1, 2, 3}, new Integer[] {}));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(new Integer[] {}, new Integer[] {1, 2, 3, 4}));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf(new Integer[] {}, null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf(null, new Integer[] {}));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((Object[]) null, null));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(HashMap.class, Arrays.asList(1, 2, 3), Arrays.asList(1, 2)));
        TestUtils.assertException(ClassCastException.class, "class java.awt.Color cannot be cast to class java.lang.Comparable (java.awt.Color is in module java.desktop of loader 'bootstrap'; java.lang.Comparable is in module java.base of loader 'bootstrap')", () ->
                MapUtility.mapOf(TreeMap.class, Arrays.asList(Color.RED, Color.BLACK), Arrays.asList(1, 2)));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf(null, Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3)));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(Arrays.asList(1, 2, 3), Arrays.asList(1, 2)));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3, 4)));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(Arrays.asList(1, 2, 3), Collections.emptyList()));
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                MapUtility.mapOf(Collections.emptyList(), Arrays.asList(1, 2, 3, 4)));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf(Collections.emptyList(), null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((List) null, Collections.emptyList()));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.mapOf((List) null, null));
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#clone(Map)
     */
    @Test
    public void testClone() throws Exception {
        //int, string
        Map<Integer, String> integerStringMap = MapUtility.mapOf(
                Arrays.asList(1, 3, 9),
                Arrays.asList("t1", "t2", "t3"));
        Map<Integer, String> integerStringClone = MapUtility.clone(integerStringMap);
        Assert.assertNotNull(integerStringClone);
        Assert.assertTrue(integerStringClone instanceof HashMap);
        Assert.assertEquals(3, integerStringClone.size());
        Assert.assertEquals("t1", integerStringClone.get(1));
        Assert.assertEquals("t2", integerStringClone.get(3));
        Assert.assertEquals("t3", integerStringClone.get(9));
        
        //string, boolean
        Map<String, Boolean> stringBooleanMap = MapUtility.mapOf(HashMap.class,
                new String[] {"a", "b"},
                new Boolean[] {true, false});
        Map<String, Boolean> stringBooleanClone = MapUtility.clone(stringBooleanMap);
        Assert.assertNotNull(stringBooleanClone);
        Assert.assertTrue(stringBooleanClone instanceof HashMap);
        Assert.assertEquals(2, stringBooleanClone.size());
        Assert.assertTrue(stringBooleanClone.get("a"));
        Assert.assertFalse(stringBooleanClone.get("b"));
        
        //string, string
        Map<String, String> stringStringMap = MapUtility.mapOf(LinkedHashMap.class,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("I", "J", "K"));
        Map<String, String> stringStringClone = MapUtility.clone(stringStringMap);
        Assert.assertNotNull(stringStringClone);
        Assert.assertTrue(stringStringClone instanceof LinkedHashMap);
        Assert.assertEquals(3, stringStringClone.size());
        Assert.assertEquals("I", stringStringClone.get("A"));
        Assert.assertEquals("J", stringStringClone.get("B"));
        Assert.assertEquals("K", stringStringClone.get("C"));
        
        //long, object
        Map<Long, Object> longObjectMap = MapUtility.mapOf(TreeMap.class,
                new Long[] {189456L, 8756156033L},
                new Object[] {34, ""});
        Map<Long, Object> longObjectClone = MapUtility.clone(longObjectMap);
        Assert.assertNotNull(longObjectClone);
        Assert.assertTrue(longObjectClone instanceof TreeMap);
        Assert.assertEquals(2, longObjectClone.size());
        Assert.assertEquals(34, longObjectClone.get(189456L));
        Assert.assertEquals("", longObjectClone.get(8756156033L));
    }
    
    /**
     * JUnit test of cast.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#cast(Map, Class)
     */
    @Test
    public void testCast() throws Exception {
        //int, string
        Map<Integer, String> integerStringMap = MapUtility.mapOf(
                Arrays.asList(1, 3, 9),
                Arrays.asList("t1", "t2", "t3"));
        Map<Integer, String> integerStringClone = MapUtility.cast(integerStringMap, HashMap.class);
        Assert.assertNotNull(integerStringClone);
        Assert.assertTrue(integerStringClone instanceof HashMap);
        Assert.assertEquals(3, integerStringClone.size());
        Assert.assertEquals("t1", integerStringClone.get(1));
        Assert.assertEquals("t2", integerStringClone.get(3));
        Assert.assertEquals("t3", integerStringClone.get(9));
        
        //string, boolean
        Map<String, Boolean> stringBooleanMap = MapUtility.mapOf(HashMap.class,
                new String[] {"a", "b"},
                new Boolean[] {true, false});
        Map<String, Boolean> stringBooleanClone = MapUtility.cast(stringBooleanMap, LinkedHashMap.class);
        Assert.assertNotNull(stringBooleanClone);
        Assert.assertTrue(stringBooleanClone instanceof LinkedHashMap);
        Assert.assertEquals(2, stringBooleanClone.size());
        Assert.assertTrue(stringBooleanClone.get("a"));
        Assert.assertFalse(stringBooleanClone.get("b"));
        
        //string, string
        Map<String, String> stringStringMap = MapUtility.mapOf(LinkedHashMap.class,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("I", "J", "K"));
        Map<String, String> stringStringClone = MapUtility.cast(stringStringMap, TreeMap.class);
        Assert.assertNotNull(stringStringClone);
        Assert.assertTrue(stringStringClone instanceof TreeMap);
        Assert.assertEquals(3, stringStringClone.size());
        Assert.assertEquals("I", stringStringClone.get("A"));
        Assert.assertEquals("J", stringStringClone.get("B"));
        Assert.assertEquals("K", stringStringClone.get("C"));
        
        //long, object
        Map<Long, Object> longObjectMap = MapUtility.mapOf(TreeMap.class,
                new Long[] {189456L, 8756156033L},
                new Object[] {34, ""});
        Map<Long, Object> longObjectClone = MapUtility.cast(longObjectMap, HashMap.class);
        Assert.assertNotNull(longObjectClone);
        Assert.assertTrue(longObjectClone instanceof HashMap);
        Assert.assertEquals(2, longObjectClone.size());
        Assert.assertEquals(34, longObjectClone.get(189456L));
        Assert.assertEquals("", longObjectClone.get(8756156033L));
        
        //invalid
        TestUtils.assertException(ClassCastException.class, "class java.awt.Color cannot be cast to class java.lang.Comparable (java.awt.Color is in module java.desktop of loader 'bootstrap'; java.lang.Comparable is in module java.base of loader 'bootstrap')", () ->
                MapUtility.cast(MapUtility.mapOf(Arrays.asList(Color.RED, Color.BLACK), Arrays.asList(1, 2)), TreeMap.class));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.cast(longObjectMap, null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.cast(null, HashMap.class));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.cast(null, null));
    }
    
    /**
     * JUnit test of merge.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#merge(Map, Map)
     */
    @Test
    public void testMerge() throws Exception {
        //int, string
        Map<Integer, String> integerStringMap = MapUtility.mapOf(
                Arrays.asList(1, 3, 9),
                Arrays.asList("t1", "t2", "t3"));
        Map<Integer, String> integerStringMap2 = MapUtility.mapOf(
                Arrays.asList(9, 15),
                Arrays.asList("t3.5", "t4"));
        Map<Integer, String> integerStringMergeMap = MapUtility.merge(integerStringMap, integerStringMap2);
        Assert.assertNotNull(integerStringMergeMap);
        Assert.assertTrue(integerStringMergeMap instanceof HashMap);
        Assert.assertEquals(4, integerStringMergeMap.size());
        Assert.assertEquals("t1", integerStringMergeMap.get(1));
        Assert.assertEquals("t2", integerStringMergeMap.get(3));
        Assert.assertEquals("t3.5", integerStringMergeMap.get(9));
        Assert.assertEquals("t4", integerStringMergeMap.get(15));
        integerStringMergeMap = MapUtility.merge(integerStringMap2, integerStringMap);
        Assert.assertEquals("t3", integerStringMergeMap.get(9));
        
        //string, boolean
        Map<String, Boolean> stringBooleanMap = MapUtility.mapOf(LinkedHashMap.class,
                new String[] {"a", "b"},
                new Boolean[] {true, false});
        Map<String, Boolean> stringBooleanMap2 = MapUtility.mapOf(LinkedHashMap.class,
                new String[] {"c", "d"},
                new Boolean[] {false, true});
        Map<String, Boolean> stringBooleanMergeMap = MapUtility.merge(stringBooleanMap, stringBooleanMap2);
        Assert.assertNotNull(stringBooleanMergeMap);
        Assert.assertTrue(stringBooleanMergeMap instanceof LinkedHashMap);
        Assert.assertEquals(4, stringBooleanMergeMap.size());
        Assert.assertTrue(stringBooleanMergeMap.get("a"));
        Assert.assertFalse(stringBooleanMergeMap.get("b"));
        Assert.assertFalse(stringBooleanMergeMap.get("c"));
        Assert.assertTrue(stringBooleanMergeMap.get("d"));
        
        //string, string
        Map<String, String> stringStringMap = MapUtility.mapOf(TreeMap.class,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("I", "J", "K"));
        Map<String, String> stringStringMap2 = MapUtility.mapOf(LinkedHashMap.class,
                Arrays.asList("D", "C", "B", "A"),
                Arrays.asList("O", "N", "M", "L"));
        Map<String, String> stringStringMergeMap = MapUtility.merge(stringStringMap, stringStringMap2);
        Assert.assertNotNull(stringStringMergeMap);
        Assert.assertTrue(stringStringMergeMap instanceof TreeMap);
        Assert.assertEquals(4, stringStringMergeMap.size());
        Assert.assertEquals("L", stringStringMergeMap.get("A"));
        Assert.assertEquals("M", stringStringMergeMap.get("B"));
        Assert.assertEquals("N", stringStringMergeMap.get("C"));
        Assert.assertEquals("O", stringStringMergeMap.get("D"));
        
        //long, object
        Map<Long, Object> longObjectMap = MapUtility.mapOf(
                new Long[] {189456L, 8756156033L},
                new Object[] {34, ""});
        Map<Long, Object> longObjectMap2 = MapUtility.mapOf(TreeMap.class,
                new Long[] {1894560L, 87561560330L},
                new Object[] {340, "0"});
        Map<Long, Object> longObjectMergeMap = MapUtility.merge(longObjectMap, longObjectMap2);
        Assert.assertNotNull(longObjectMergeMap);
        Assert.assertTrue(longObjectMergeMap instanceof HashMap);
        Assert.assertEquals(4, longObjectMergeMap.size());
        Assert.assertEquals(34, longObjectMergeMap.get(189456L));
        Assert.assertEquals("", longObjectMergeMap.get(8756156033L));
        Assert.assertEquals(340, longObjectMergeMap.get(1894560L));
        Assert.assertEquals("0", longObjectMergeMap.get(87561560330L));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.merge(longObjectMap, null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.merge(null, longObjectMap2));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.merge(null, null));
    }
    
    /**
     * JUnit test of isNullOrEmpty.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#isNullOrEmpty(Map)
     */
    @Test
    public void testIsNullOrEmpty() throws Exception {
        //standard
        Assert.assertFalse(MapUtility.isNullOrEmpty(MapUtility.mapOf(
                new String[] {"test", "key"},
                new String[] {"map", "value"})));
        
        //empty
        Assert.assertTrue(MapUtility.isNullOrEmpty(new HashMap<>()));
        
        //null
        Assert.assertTrue(MapUtility.isNullOrEmpty(null));
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#equals(Map, Map)
     */
    @Test
    public void testEquals() throws Exception {
        //int, string
        Map<Integer, String> integerStringMap1 = MapUtility.mapOf(
                Arrays.asList(1, 3, 9),
                Arrays.asList("t1", "t2", "t3"));
        Map<Integer, String> integerStringMap2 = MapUtility.mapOf(
                Arrays.asList(1, 3, 9),
                Arrays.asList("t1", "t2", "t3"));
        Assert.assertTrue(MapUtility.equals(integerStringMap1, integerStringMap2));
        Assert.assertTrue(MapUtility.equals(integerStringMap2, integerStringMap1));
        integerStringMap1 = MapUtility.mapOf(
                Arrays.asList(1, 3, 9),
                Arrays.asList("t1", "t2", "t3"));
        integerStringMap2 = MapUtility.mapOf(
                Arrays.asList(3, 9, 1),
                Arrays.asList("t2", "t3", "t1"));
        Assert.assertTrue(MapUtility.equals(integerStringMap1, integerStringMap2));
        Assert.assertTrue(MapUtility.equals(integerStringMap2, integerStringMap1));
        
        //string, boolean
        Map<String, Boolean> stringBooleanMap1 = MapUtility.mapOf(LinkedHashMap.class,
                new String[] {"a", "b"},
                new Boolean[] {true, false});
        Map<String, Boolean> stringBooleanMap2 = MapUtility.mapOf(LinkedHashMap.class,
                new String[] {"a", "b"},
                new Boolean[] {false, true});
        Assert.assertFalse(MapUtility.equals(stringBooleanMap1, stringBooleanMap2));
        Assert.assertFalse(MapUtility.equals(stringBooleanMap2, stringBooleanMap1));
        
        //string, string
        Map<String, String> stringStringMap1 = MapUtility.mapOf(TreeMap.class,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("I", "J", "K"));
        Map<String, String> stringStringMap2 = MapUtility.mapOf(LinkedHashMap.class,
                Arrays.asList("D", "C", "B", "A"),
                Arrays.asList("L", "K", "J", "I"));
        Assert.assertFalse(MapUtility.equals(stringStringMap1, stringStringMap2));
        Assert.assertFalse(MapUtility.equals(stringStringMap2, stringStringMap1));
        
        //long, object
        Map<Long, Object> longObjectMap1 = MapUtility.mapOf(
                new Long[] {189456L, 8756156033L},
                new Object[] {34, ""});
        Map<Long, Object> longObjectMap2 = MapUtility.mapOf(TreeMap.class,
                new Long[] {189456L, 8756156033L},
                new Object[] {34, ""});
        Assert.assertTrue(MapUtility.equals(longObjectMap1, longObjectMap2));
        Assert.assertTrue(MapUtility.equals(longObjectMap2, longObjectMap1));
        
        //invalid
        Assert.assertTrue(MapUtility.equals(new HashMap<>(), new HashMap<>()));
        Assert.assertFalse(MapUtility.equals(longObjectMap1, null));
        Assert.assertFalse(MapUtility.equals(null, longObjectMap2));
        Assert.assertTrue(MapUtility.equals(null, null));
    }
    
    /**
     * JUnit test of contains.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#contains(Map, Object)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testContains() throws Exception {
        //int, string
        Map<Integer, String> integerStringMap = MapUtility.mapOf(
                Arrays.asList(1, 3, 9),
                Arrays.asList("t1", "t2", "t3"));
        Assert.assertTrue(MapUtility.contains(integerStringMap, 1));
        Assert.assertTrue(MapUtility.contains(integerStringMap, 3));
        Assert.assertTrue(MapUtility.contains(integerStringMap, 9));
        Assert.assertFalse(MapUtility.contains(integerStringMap, 15));
        
        //string, boolean
        Map<String, Boolean> stringBooleanMap = MapUtility.mapOf(HashMap.class,
                new String[] {"a", "b"},
                new Boolean[] {true, false});
        Assert.assertTrue(MapUtility.contains(stringBooleanMap, "a"));
        Assert.assertTrue(MapUtility.contains(stringBooleanMap, "b"));
        Assert.assertFalse(MapUtility.contains(stringBooleanMap, ""));
        
        //string, string
        Map<String, String> stringStringMap = MapUtility.mapOf(LinkedHashMap.class,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("I", "J", "K"));
        Assert.assertTrue(MapUtility.contains(stringStringMap, "A"));
        Assert.assertTrue(MapUtility.contains(stringStringMap, "B"));
        Assert.assertTrue(MapUtility.contains(stringStringMap, "C"));
        Assert.assertFalse(MapUtility.contains(stringStringMap, "a"));
        
        //long, object
        Map<Long, Object> longObjectMap = MapUtility.mapOf(TreeMap.class,
                new Long[] {189456L, 8756156033L},
                new Object[] {34, ""});
        Assert.assertTrue(MapUtility.contains(longObjectMap, 189456L));
        Assert.assertTrue(MapUtility.contains(longObjectMap, 8756156033L));
        Assert.assertFalse(MapUtility.contains(longObjectMap, 10L));
        
        //invalid
        Assert.assertFalse(MapUtility.contains(stringStringMap, null));
        Assert.assertFalse(MapUtility.contains(new HashMap<>(), null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.contains(new TreeMap<>(), null));
        Assert.assertFalse(MapUtility.contains(null, "test"));
        Assert.assertFalse(MapUtility.contains(null, null));
    }
    
    /**
     * JUnit test of containsIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#containsIgnoreCase(Map, String)
     */
    @Test
    public void testContainsIgnoreCase() throws Exception {
        final Map<String, String> stringStringMap = MapUtility.mapOf(LinkedHashMap.class,
                Arrays.asList("cat", "dog", "bird", "lizard", "fish"),
                Arrays.asList("mammal", "mammal", "bird", "reptile", "fish"));
        
        //standard
        Assert.assertTrue(MapUtility.containsIgnoreCase(stringStringMap, "cat"));
        Assert.assertTrue(MapUtility.containsIgnoreCase(stringStringMap, "lizard"));
        Assert.assertTrue(MapUtility.containsIgnoreCase(stringStringMap, "dog"));
        Assert.assertFalse(MapUtility.containsIgnoreCase(stringStringMap, "rat"));
        
        //case
        Assert.assertTrue(MapUtility.containsIgnoreCase(stringStringMap, "CAT"));
        Assert.assertTrue(MapUtility.containsIgnoreCase(stringStringMap, "LIzArD"));
        Assert.assertTrue(MapUtility.containsIgnoreCase(stringStringMap, "doG"));
        Assert.assertFalse(MapUtility.containsIgnoreCase(stringStringMap, "rAt"));
        
        //invalid
        Assert.assertFalse(MapUtility.containsIgnoreCase(stringStringMap, null));
        Assert.assertFalse(MapUtility.containsIgnoreCase(new HashMap<>(), null));
        Assert.assertFalse(MapUtility.containsIgnoreCase(new TreeMap<>(), null));
        Assert.assertFalse(MapUtility.containsIgnoreCase(null, ""));
        Assert.assertFalse(MapUtility.containsIgnoreCase(null, null));
    }
    
    /**
     * JUnit test of getOrDefault.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#getOrDefault(Map, Object, Object)
     */
    @Test
    public void testGetOrDefault() throws Exception {
        //int, string
        Map<Integer, String> integerStringMap = MapUtility.mapOf(
                Arrays.asList(1, 3, 9),
                Arrays.asList("t1", "t2", "t3"));
        Assert.assertEquals("t1", MapUtility.getOrDefault(integerStringMap, 1, "N/A"));
        Assert.assertEquals("t2", MapUtility.getOrDefault(integerStringMap, 3, "N/A"));
        Assert.assertEquals("t3", MapUtility.getOrDefault(integerStringMap, 9, "N/A"));
        Assert.assertEquals("N/A", MapUtility.getOrDefault(integerStringMap, 15, "N/A"));
        
        //string, boolean
        Map<String, Boolean> stringBooleanMap = MapUtility.mapOf(HashMap.class,
                new String[] {"a", "b"},
                new Boolean[] {true, false});
        Assert.assertTrue(MapUtility.getOrDefault(stringBooleanMap, "a", false));
        Assert.assertFalse(MapUtility.getOrDefault(stringBooleanMap, "b", false));
        Assert.assertFalse(MapUtility.getOrDefault(stringBooleanMap, "", false));
        
        //string, string
        Map<String, String> stringStringMap = MapUtility.mapOf(LinkedHashMap.class,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("I", "J", "K"));
        Assert.assertEquals("I", MapUtility.getOrDefault(stringStringMap, "A", "null"));
        Assert.assertEquals("J", MapUtility.getOrDefault(stringStringMap, "B", "null"));
        Assert.assertEquals("K", MapUtility.getOrDefault(stringStringMap, "C", "null"));
        Assert.assertEquals("null", MapUtility.getOrDefault(stringStringMap, "a", "null"));
        
        //long, object
        Map<Long, Object> longObjectMap = MapUtility.mapOf(TreeMap.class,
                new Long[] {189456L, 8756156033L},
                new Object[] {34, ""});
        Assert.assertEquals(34, MapUtility.getOrDefault(longObjectMap, 189456L, null));
        Assert.assertEquals("", MapUtility.getOrDefault(longObjectMap, 8756156033L, null));
        Assert.assertNull(MapUtility.getOrDefault(longObjectMap, 10L, null));
        
        //invalid
        Assert.assertEquals("null", MapUtility.getOrDefault(stringStringMap, null, "null"));
        Assert.assertEquals("null", MapUtility.getOrDefault(new HashMap<>(), null, "null"));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.getOrDefault(new TreeMap<>(), null, "null"));
        Assert.assertEquals("null", MapUtility.getOrDefault(null, "test", "null"));
        Assert.assertEquals("null", MapUtility.getOrDefault(null, null, "null"));
    }
    
    /**
     * JUnit test of getOrDefaultIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#getOrDefaultIgnoreCase(Map, String, Object)
     */
    @Test
    public void testGetOrDefaultIgnoreCase() throws Exception {
        final Map<String, String> stringStringMap = MapUtility.mapOf(LinkedHashMap.class,
                Arrays.asList("cat", "dog", "bird", "lizard", "fish"),
                Arrays.asList("mammal", "mammal", "bird", "reptile", "fish"));
        
        //standard
        Assert.assertEquals("mammal", MapUtility.getOrDefaultIgnoreCase(stringStringMap, "cat", "null"));
        Assert.assertEquals("reptile", MapUtility.getOrDefaultIgnoreCase(stringStringMap, "lizard", "null"));
        Assert.assertEquals("mammal", MapUtility.getOrDefaultIgnoreCase(stringStringMap, "dog", "null"));
        Assert.assertEquals("null", MapUtility.getOrDefaultIgnoreCase(stringStringMap, "rat", "null"));
        
        //case
        Assert.assertEquals("mammal", MapUtility.getOrDefaultIgnoreCase(stringStringMap, "CAT", "null"));
        Assert.assertEquals("reptile", MapUtility.getOrDefaultIgnoreCase(stringStringMap, "LIzArD", "null"));
        Assert.assertEquals("mammal", MapUtility.getOrDefaultIgnoreCase(stringStringMap, "doG", "null"));
        Assert.assertEquals("null", MapUtility.getOrDefaultIgnoreCase(stringStringMap, "rAt", "null"));
        
        //invalid
        Assert.assertEquals("null", MapUtility.getOrDefaultIgnoreCase(stringStringMap, null, "null"));
        Assert.assertEquals("null", MapUtility.getOrDefaultIgnoreCase(new HashMap<>(), null, "null"));
        Assert.assertEquals("null", MapUtility.getOrDefaultIgnoreCase(new TreeMap<>(), null, "null"));
        Assert.assertEquals("null", MapUtility.getOrDefaultIgnoreCase(null, "", "null"));
        Assert.assertEquals("null", MapUtility.getOrDefaultIgnoreCase(null, null, "null"));
    }
    
    /**
     * JUnit test of getOrNull.
     *
     * @throws Exception When there is an exception.
     * @see MapUtility#getOrNull(Map, Object)
     */
    @Test
    public void testGetOrNull() throws Exception {
        //int, string
        Map<Integer, String> integerStringMap = MapUtility.mapOf(
                Arrays.asList(1, 3, 9),
                Arrays.asList("t1", "t2", "t3"));
        Assert.assertEquals("t1", MapUtility.getOrNull(integerStringMap, 1));
        Assert.assertEquals("t2", MapUtility.getOrNull(integerStringMap, 3));
        Assert.assertEquals("t3", MapUtility.getOrNull(integerStringMap, 9));
        Assert.assertNull(MapUtility.getOrNull(integerStringMap, 15));
        
        //string, boolean
        Map<String, Boolean> stringBooleanMap = MapUtility.mapOf(HashMap.class,
                new String[] {"a", "b"},
                new Boolean[] {true, false});
        Assert.assertTrue(MapUtility.getOrNull(stringBooleanMap, "a"));
        Assert.assertFalse(MapUtility.getOrNull(stringBooleanMap, "b"));
        Assert.assertNull(MapUtility.getOrNull(stringBooleanMap, ""));
        
        //string, string
        Map<String, String> stringStringMap = MapUtility.mapOf(LinkedHashMap.class,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("I", "J", "K"));
        Assert.assertEquals("I", MapUtility.getOrNull(stringStringMap, "A"));
        Assert.assertEquals("J", MapUtility.getOrNull(stringStringMap, "B"));
        Assert.assertEquals("K", MapUtility.getOrNull(stringStringMap, "C"));
        Assert.assertNull(MapUtility.getOrNull(stringStringMap, "a"));
        
        //long, object
        Map<Long, Object> longObjectMap = MapUtility.mapOf(TreeMap.class,
                new Long[] {189456L, 8756156033L},
                new Object[] {34, ""});
        Assert.assertEquals(34, MapUtility.getOrNull(longObjectMap, 189456L));
        Assert.assertEquals("", MapUtility.getOrNull(longObjectMap, 8756156033L));
        Assert.assertNull(MapUtility.getOrNull(longObjectMap, 10L));
        
        //invalid
        Assert.assertNull(MapUtility.getOrNull(stringStringMap, null));
        Assert.assertNull(MapUtility.getOrNull(new HashMap<>(), null));
        TestUtils.assertException(NullPointerException.class, () ->
                MapUtility.getOrNull(new TreeMap<>(), null));
        Assert.assertNull(MapUtility.getOrNull(null, "test"));
        Assert.assertNull(MapUtility.getOrNull(null, null));
    }
    
}
