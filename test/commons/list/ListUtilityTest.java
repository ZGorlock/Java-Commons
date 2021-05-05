/*
 * File:    ListUtilityTest.java
 * Package: commons.list
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
 * JUnit test of ListUtility.
 *
 * @see ListUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ListUtility.class})
public class ListUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ListUtilityTest.class);
    
    
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
     * JUnit test of toList.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#toList(Object[])
     */
    @Test
    public void testToList() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Assert.assertEquals(booleanArray.length, booleanList.size());
        Assert.assertArrayEquals(booleanArray, booleanList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Assert.assertEquals(integerArray.length, integerList.size());
        Assert.assertArrayEquals(integerArray, integerList.toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Assert.assertEquals(floatArray.length, floatList.size());
        Assert.assertArrayEquals(floatArray, floatList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Assert.assertEquals(doubleArray.length, doubleList.size());
        Assert.assertArrayEquals(doubleArray, doubleList.toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Assert.assertEquals(longArray.length, longList.size());
        Assert.assertArrayEquals(longArray, longList.toArray());
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        Assert.assertEquals(objectArray.length, objectList.size());
        Assert.assertArrayEquals(objectArray, objectList.toArray());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList(null));
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#clone(List)
     */
    @Test
    public void testClone() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanClone = ListUtility.clone(booleanList);
        Assert.assertEquals(booleanList.size(), booleanClone.size());
        Assert.assertArrayEquals(booleanList.toArray(), booleanClone.toArray());
        Assert.assertEquals(booleanList, booleanClone);
        Assert.assertNotEquals(System.identityHashCode(booleanList), System.identityHashCode(booleanClone));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<Integer> integerClone = ListUtility.clone(integerList);
        Assert.assertEquals(integerList.size(), integerClone.size());
        Assert.assertArrayEquals(integerList.toArray(), integerClone.toArray());
        Assert.assertEquals(integerList, integerClone);
        Assert.assertNotEquals(System.identityHashCode(integerList), System.identityHashCode(integerClone));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<Float> floatClone = ListUtility.clone(floatList);
        Assert.assertEquals(floatList.size(), floatClone.size());
        Assert.assertArrayEquals(floatList.toArray(), floatClone.toArray());
        Assert.assertEquals(floatList, floatClone);
        Assert.assertNotEquals(System.identityHashCode(floatList), System.identityHashCode(floatClone));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<Double> doubleClone = ListUtility.clone(doubleList);
        Assert.assertEquals(doubleList.size(), doubleClone.size());
        Assert.assertArrayEquals(doubleList.toArray(), doubleClone.toArray());
        Assert.assertEquals(doubleList, doubleClone);
        Assert.assertNotEquals(System.identityHashCode(doubleList), System.identityHashCode(doubleClone));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        List<Long> longClone = ListUtility.clone(longList);
        Assert.assertEquals(longList.size(), longClone.size());
        Assert.assertArrayEquals(longList.toArray(), longClone.toArray());
        Assert.assertEquals(longList, longClone);
        Assert.assertNotEquals(System.identityHashCode(longList), System.identityHashCode(longClone));
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectClone = ListUtility.clone(objectList);
        Assert.assertEquals(objectList.size(), objectClone.size());
        Assert.assertArrayEquals(objectList.toArray(), objectClone.toArray());
        Assert.assertEquals(objectList, objectClone);
        Assert.assertNotEquals(System.identityHashCode(objectList), System.identityHashCode(objectClone));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.clone(null));
    }
    
    /**
     * JUnit test of subList.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#subList(List, int, int)
     * @see ListUtility#subList(List, int)
     */
    @Test
    public void testSubList() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanSubList = ListUtility.subList(booleanList, 2, 4);
        Assert.assertEquals(2, booleanSubList.size());
        Assert.assertArrayEquals(new Boolean[] {false, true}, booleanSubList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<Integer> integerSubList = ListUtility.subList(integerList, 2);
        Assert.assertEquals(5, integerSubList.size());
        Assert.assertArrayEquals(new Integer[] {48, 5, -4, -9, 6}, integerSubList.toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<Float> floatSubList = ListUtility.subList(floatList, 0, 4);
        Assert.assertEquals(4, floatSubList.size());
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f}, floatSubList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<Double> doubleSubList = ListUtility.subList(doubleList, 0);
        Assert.assertEquals(7, doubleSubList.size());
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleSubList.toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        List<Long> longSubList = ListUtility.subList(longList, 6, 7);
        Assert.assertEquals(1, longSubList.size());
        Assert.assertArrayEquals(new Long[] {699546101L}, longSubList.toArray());
        
        //object
        Object b = new ArithmeticException();
        Object[] objectArray = new Object[] {"", 54, b, new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectSubList = ListUtility.subList(objectList, 1, 3);
        Assert.assertEquals(2, objectSubList.size());
        Assert.assertArrayEquals(new Object[] {54, b}, objectSubList.toArray());
        
        //empty
        List<Object> subList = ListUtility.subList(objectList, 0, 0);
        Assert.assertEquals(0, subList.size());
        
        //invalid
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [0,6) is out of bounds of the list", () ->
                ListUtility.subList(objectList, 0, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [-1,5) is out of bounds of the list", () ->
                ListUtility.subList(objectList, -1, 5));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [4,2) is out of bounds of the list", () ->
                ListUtility.subList(objectList, 4, 2));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [6,5) is out of bounds of the list", () ->
                ListUtility.subList(objectList, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [-1,5) is out of bounds of the list", () ->
                ListUtility.subList(objectList, -1));
        
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.subList(null, 2, 4));
    }
    
    /**
     * JUnit test of merge.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#merge(List, List)
     */
    @Test
    public void testMerge() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanArray2 = new Boolean[] {true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanList2 = ListUtility.toList(booleanArray2);
        List<Boolean> booleanMergeList = ListUtility.merge(booleanList, booleanList2);
        Assert.assertEquals(7, booleanMergeList.size());
        Assert.assertArrayEquals(new Boolean[] {true, false, false, true, false, true, false}, booleanMergeList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerArray2 = new Integer[] {15, 312, 48};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<Integer> integerList2 = ListUtility.toList(integerArray2);
        List<Integer> integerMergeList = ListUtility.merge(integerList, integerList2);
        Assert.assertEquals(10, integerMergeList.size());
        Assert.assertArrayEquals(new Integer[] {15, 312, 48, 5, -4, -9, 6, 15, 312, 48}, integerMergeList.toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatArray2 = new Float[] {15.1f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<Float> floatList2 = ListUtility.toList(floatArray2);
        List<Float> floatMergeList = ListUtility.merge(floatList, floatList2);
        Assert.assertEquals(9, floatMergeList.size());
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f, 15.1f}, floatMergeList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleArray2 = new Double[] {-4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<Double> doubleList2 = ListUtility.toList(doubleArray2);
        List<Double> doubleMergeList = ListUtility.merge(doubleList, doubleList2);
        Assert.assertEquals(10, doubleMergeList.size());
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleMergeList.toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longArray2 = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        List<Long> longList2 = ListUtility.toList(longArray2);
        List<Long> longMergeList = ListUtility.merge(longList, longList2);
        Assert.assertEquals(14, longMergeList.size());
        Assert.assertArrayEquals(new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L, 15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L}, longMergeList.toArray());
        
        //object
        Object a = "";
        Object b = 54;
        Object c = new ArithmeticException();
        Object d = new HashMap<>();
        Object e = new Object();
        Object[] objectArray = new Object[] {a, b, c, d, e};
        Object[] objectArray2 = new Object[] {b, c, e};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectList2 = ListUtility.toList(objectArray2);
        List<Object> objectMergeList = ListUtility.merge(objectList, objectList2);
        Assert.assertEquals(8, objectMergeList.size());
        Assert.assertArrayEquals(new Object[] {a, b, c, d, e, b, c, e}, objectMergeList.toArray());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.merge(objectList, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.merge(null, objectList2));
    }
    
    /**
     * JUnit test of split.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#split(List, int)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSplit() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<List<Boolean>> booleanSplitList = ListUtility.split(booleanList, 3);
        Assert.assertEquals(2, booleanSplitList.size());
        Assert.assertArrayEquals(new Boolean[] {true, false, false}, booleanSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Boolean[] {true, false, null}, booleanSplitList.get(1).toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<List<Integer>> integerSplitList = ListUtility.split(integerList, 2);
        Assert.assertEquals(4, integerSplitList.size());
        Assert.assertArrayEquals(new Integer[] {15, 312}, integerSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Integer[] {48, 5}, integerSplitList.get(1).toArray());
        Assert.assertArrayEquals(new Integer[] {-4, -9}, integerSplitList.get(2).toArray());
        Assert.assertArrayEquals(new Integer[] {6, null}, integerSplitList.get(3).toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<List<Float>> floatSplitList = ListUtility.split(floatList, 4);
        Assert.assertEquals(2, floatSplitList.size());
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f}, floatSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Float[] {-4.006f, -9.7f, 6.99f, 19776.4f}, floatSplitList.get(1).toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<List<Double>> doubleSplitList = ListUtility.split(doubleList, 7);
        Assert.assertEquals(1, doubleSplitList.size());
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleSplitList.get(0).toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        List<List<Long>> longSplitList = ListUtility.split(longList, 1);
        Assert.assertEquals(7, longSplitList.size());
        Assert.assertArrayEquals(new Long[] {15104564L}, longSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Long[] {3129113874L}, longSplitList.get(1).toArray());
        Assert.assertArrayEquals(new Long[] {4800000015L}, longSplitList.get(2).toArray());
        Assert.assertArrayEquals(new Long[] {5457894511L}, longSplitList.get(3).toArray());
        Assert.assertArrayEquals(new Long[] {-4006005001L}, longSplitList.get(4).toArray());
        Assert.assertArrayEquals(new Long[] {-970487745L}, longSplitList.get(5).toArray());
        Assert.assertArrayEquals(new Long[] {699546101L}, longSplitList.get(6).toArray());
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<List<Object>> objectSplitList = ListUtility.split(objectList, 3);
        Assert.assertEquals(2, objectSplitList.size());
        Assert.assertArrayEquals(new Object[] {"", 54, objectArray[2]}, objectSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Object[] {objectArray[3], objectArray[4], null}, objectSplitList.get(1).toArray());
        
        //invalid
        
        objectSplitList = ListUtility.split(objectList, 0);
        Assert.assertEquals(objectArray.length, objectSplitList.size());
        
        objectSplitList = ListUtility.split(objectList, -1);
        Assert.assertEquals(objectArray.length, objectSplitList.size());
        
        objectSplitList = ListUtility.split(objectList, 15613);
        Assert.assertEquals(1, objectSplitList.size());
        
        objectSplitList = ListUtility.split(Collections.emptyList(), 3);
        Assert.assertEquals(0, objectSplitList.size());
        
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.split(null, 3));
    }
    
    /**
     * JUnit test of anyNull.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#anyNull(List)
     * @see ListUtility#anyNull(Object...)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testAnyNull() throws Exception {
        Boolean a = true;
        Boolean b = null;
        Integer c = 5;
        Integer d = null;
        Float e = 3.6f;
        Float f = null;
        Double g = 48.56423004d;
        Double h = null;
        Long i = 1579843046840984L;
        Long j = null;
        String k = "something";
        String l = null;
        Object m = new Object();
        Object n = null;
        List<Object> list;
        Object[] array;
        
        //all
        array = new Object[] {a, b, c, d, e, f, g, h, i, j, k, l, m, n};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, b, c, d, e, f, g, h, i, j, k, l, m, n));
        
        //all not null
        array = new Object[] {a, c, e, g, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertFalse(ListUtility.anyNull(list));
        Assert.assertFalse(ListUtility.anyNull(array));
        Assert.assertFalse(ListUtility.anyNull(a, c, e, g, i, k, m));
        
        //all null
        array = new Object[] {b, d, f, h, j, l, n};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(b, d, f, h, j, l, n));
        
        //none
        array = new Object[] {};
        list = Collections.emptyList();
        Assert.assertFalse(ListUtility.anyNull(list));
        Assert.assertFalse(ListUtility.anyNull(array));
        Assert.assertFalse(ListUtility.anyNull());
        
        //boolean
        array = new Object[] {a, b, c, e, g, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, b, c, e, g, i, k, m));
        
        //int
        array = new Object[] {a, c, d, e, g, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, b, c, d, e, g, i, k, m));
        
        //float
        array = new Object[] {a, c, e, f, g, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, f, g, i, k, m));
        
        //double
        array = new Object[] {a, c, e, g, h, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, h, i, k, m));
        
        //long
        array = new Object[] {a, c, e, g, i, j, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, i, j, k, m));
        
        //string
        array = new Object[] {a, c, e, g, i, k, l, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, i, k, l, m));
        
        //object
        array = new Object[] {a, c, e, g, i, k, m, n};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, i, k, m, n));
        
        //invalid
        //noinspection ResultOfMethodCallIgnored
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.anyNull((List<Object>) null));
    }
    
    /**
     * JUnit test of removeDuplicates.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#removeDuplicates(List)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testRemoveDuplicates() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanCleanList = ListUtility.removeDuplicates(booleanList);
        Assert.assertEquals(2, booleanCleanList.size());
        
        //int
        Integer[] integerArray = new Integer[] {15, 15, 312, 48, 5, 5, -4, -9, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<Integer> integerCleanList = ListUtility.removeDuplicates(integerList);
        Assert.assertEquals(7, integerCleanList.size());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 15.1f, 15.1f, 312.91f, 312.91f, 48.0f, 5.45f, -4.006f, -4.006f, -9.7f, 6.99f, 6.99f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<Float> floatCleanList = ListUtility.removeDuplicates(floatList);
        Assert.assertEquals(7, floatCleanList.size());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 15.104564d, 15.104564d, 312.9113874d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -4.006005001d, -9.70487745d, 6.99546101d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<Double> doubleCleanList = ListUtility.removeDuplicates(doubleList);
        Assert.assertEquals(7, doubleCleanList.size());
        
        //long
        Long[] longArray = new Long[] {15104564L, 15104564L, 15104564L, 3129113874L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -4006005001L, -970487745L, 699546101L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        List<Long> longCleanList = ListUtility.removeDuplicates(longList);
        Assert.assertEquals(7, longCleanList.size());
        
        //object
        String s = "";
        ArithmeticException ae = new ArithmeticException();
        HashMap<Object, Object> hm = new HashMap<>();
        Object o = new Object();
        Object[] objectArray = new Object[] {s, s, s, 54, 54, ae, ae, hm, hm, hm, o, o};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectCleanList = ListUtility.removeDuplicates(objectList);
        Assert.assertEquals(5, objectCleanList.size());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.removeDuplicates(null));
    }
    
    /**
     * JUnit test of selectRandom.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#selectRandom(List)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSelectRandom() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        for (int i = 0; i < 100; i++) {
            Boolean booleanSelected = ListUtility.selectRandom(booleanList);
            Assert.assertNotNull(booleanSelected);
            Assert.assertTrue(booleanList.contains(booleanSelected));
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        for (int i = 0; i < 100; i++) {
            Integer integerSelected = ListUtility.selectRandom(integerList);
            Assert.assertNotNull(integerSelected);
            Assert.assertTrue(integerList.contains(integerSelected));
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        for (int i = 0; i < 100; i++) {
            Float floatSelected = ListUtility.selectRandom(floatList);
            Assert.assertNotNull(floatSelected);
            Assert.assertTrue(floatList.contains(floatSelected));
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        for (int i = 0; i < 100; i++) {
            Double doubleSelected = ListUtility.selectRandom(doubleList);
            Assert.assertNotNull(doubleSelected);
            Assert.assertTrue(doubleList.contains(doubleSelected));
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        for (int i = 0; i < 100; i++) {
            Long longSelected = ListUtility.selectRandom(longList);
            Assert.assertNotNull(longSelected);
            Assert.assertTrue(longList.contains(longSelected));
        }
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        for (int i = 0; i < 100; i++) {
            Object objectSelected = ListUtility.selectRandom(objectList);
            Assert.assertNotNull(objectSelected);
            Assert.assertTrue(objectList.contains(objectSelected));
        }
        
        //empty list
        Assert.assertNull(ListUtility.selectRandom(new ArrayList<>()));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.selectRandom(null));
    }
    
    /**
     * JUnit test of selectN.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#selectN(List, int)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSelectN() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanSelected = ListUtility.selectN(booleanList, 1);
        Assert.assertEquals(1, booleanSelected.size());
        for (Boolean b : booleanSelected) {
            Assert.assertTrue(booleanList.contains(b));
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<Integer> integerSelected = ListUtility.selectN(integerList, 5);
        Assert.assertEquals(5, integerSelected.size());
        for (Integer i : integerSelected) {
            Assert.assertTrue(integerList.contains(i));
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<Float> floatSelected = ListUtility.selectN(floatList, 7);
        Assert.assertEquals(7, floatSelected.size());
        for (Float f : floatSelected) {
            Assert.assertTrue(floatList.contains(f));
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<Double> doubleSelected = ListUtility.selectN(doubleList, 3);
        Assert.assertEquals(3, doubleSelected.size());
        for (Double d : doubleSelected) {
            Assert.assertTrue(doubleList.contains(d));
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        List<Long> longSelected = ListUtility.selectN(longList, 4);
        Assert.assertEquals(4, longSelected.size());
        for (Long l : longSelected) {
            Assert.assertTrue(longList.contains(l));
        }
        
        //object
        
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectSelected = ListUtility.selectN(objectList, 3);
        Assert.assertEquals(3, objectSelected.size());
        for (Object o : objectSelected) {
            Assert.assertTrue(objectList.contains(o));
        }
        
        List<Object> overSizeObjectSelected = ListUtility.selectN(objectList, 999);
        Assert.assertEquals(objectList.size(), overSizeObjectSelected.size());
        for (Object o : overSizeObjectSelected) {
            Assert.assertTrue(objectList.contains(o));
        }
        
        //invalid
        
        List<Object> underSizeObjectSelected = ListUtility.selectN(objectList, -1);
        Assert.assertEquals(0, underSizeObjectSelected.size());
        
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.selectN(null, 4));
    }
    
    /**
     * JUnit test of duplicateInOrder.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#duplicateInOrder(List, int)
     * @see ListUtility#duplicateInOrder(List)
     */
    @Test
    public void testDuplicateInOrder() throws Exception {
        int x;
        
        //standard
        Object[] standardArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> standardList = ListUtility.toList(standardArray);
        List<Object> duplicatedStandardList = ListUtility.duplicateInOrder(standardList);
        Assert.assertEquals(standardList.size() * 2, duplicatedStandardList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Object o : standardList) {
                Assert.assertEquals(o, duplicatedStandardList.get(x));
                x++;
            }
        }
        duplicatedStandardList = ListUtility.duplicateInOrder(standardList, 3);
        Assert.assertEquals(standardList.size() * 3, duplicatedStandardList.size());
        x = 0;
        for (int y = 0; y < 3; y++) {
            for (Object o : standardList) {
                Assert.assertEquals(o, duplicatedStandardList.get(x));
                x++;
            }
        }
        
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> duplicatedBooleanList = ListUtility.duplicateInOrder(booleanList);
        Assert.assertEquals(booleanList.size() * 2, duplicatedBooleanList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Boolean b : booleanList) {
                Assert.assertEquals(b, duplicatedBooleanList.get(x));
                x++;
            }
        }
        duplicatedBooleanList = ListUtility.duplicateInOrder(booleanList, 6);
        Assert.assertEquals(booleanList.size() * 6, duplicatedBooleanList.size());
        x = 0;
        for (int y = 0; y < 6; y++) {
            for (Boolean b : booleanList) {
                Assert.assertEquals(b, duplicatedBooleanList.get(x));
                x++;
            }
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<Integer> duplicatedIntegerList = ListUtility.duplicateInOrder(integerList);
        Assert.assertEquals(integerList.size() * 2, duplicatedIntegerList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Integer i : integerList) {
                Assert.assertEquals(i, duplicatedIntegerList.get(x));
                x++;
            }
        }
        duplicatedIntegerList = ListUtility.duplicateInOrder(integerList, 150);
        Assert.assertEquals(integerList.size() * 150, duplicatedIntegerList.size());
        x = 0;
        for (int y = 0; y < 150; y++) {
            for (Integer i : integerList) {
                Assert.assertEquals(i, duplicatedIntegerList.get(x));
                x++;
            }
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<Float> duplicatedFloatList = ListUtility.duplicateInOrder(floatList);
        Assert.assertEquals(floatList.size() * 2, duplicatedFloatList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Float f : floatList) {
                Assert.assertEquals(f, duplicatedFloatList.get(x));
                x++;
            }
        }
        duplicatedFloatList = ListUtility.duplicateInOrder(floatList, 150);
        Assert.assertEquals(floatList.size() * 150, duplicatedFloatList.size());
        x = 0;
        for (int y = 0; y < 150; y++) {
            for (Float f : floatList) {
                Assert.assertEquals(f, duplicatedFloatList.get(x));
                x++;
            }
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<Double> duplicatedDoubleList = ListUtility.duplicateInOrder(doubleList);
        Assert.assertEquals(doubleList.size() * 2, duplicatedDoubleList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Double d : doubleList) {
                Assert.assertEquals(d, duplicatedDoubleList.get(x));
                x++;
            }
        }
        duplicatedDoubleList = ListUtility.duplicateInOrder(doubleList, 8);
        Assert.assertEquals(doubleList.size() * 8, duplicatedDoubleList.size());
        x = 0;
        for (int y = 0; y < 8; y++) {
            for (Double d : doubleList) {
                Assert.assertEquals(d, duplicatedDoubleList.get(x));
                x++;
            }
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        List<Long> duplicatedLongList = ListUtility.duplicateInOrder(longList);
        Assert.assertEquals(longList.size() * 2, duplicatedLongList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Long l : longList) {
                Assert.assertEquals(l, duplicatedLongList.get(x));
                x++;
            }
        }
        duplicatedLongList = ListUtility.duplicateInOrder(longList, 10);
        Assert.assertEquals(longList.size() * 10, duplicatedLongList.size());
        x = 0;
        for (int y = 0; y < 10; y++) {
            for (Long l : longList) {
                Assert.assertEquals(l, duplicatedLongList.get(x));
                x++;
            }
        }
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> duplicatedObjectList = ListUtility.duplicateInOrder(objectList);
        Assert.assertEquals(objectList.size() * 2, duplicatedObjectList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Object o : objectList) {
                Assert.assertEquals(o, duplicatedObjectList.get(x));
                x++;
            }
        }
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, 10);
        Assert.assertEquals(objectList.size() * 10, duplicatedObjectList.size());
        x = 0;
        for (int y = 0; y < 10; y++) {
            for (Object o : objectList) {
                Assert.assertEquals(o, duplicatedObjectList.get(x));
                x++;
            }
        }
        
        //edge case
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, 1);
        Assert.assertEquals(objectList.size(), duplicatedObjectList.size());
        x = 0;
        for (int y = 0; y < 1; y++) {
            for (Object o : objectList) {
                Assert.assertEquals(o, duplicatedObjectList.get(x));
                x++;
            }
        }
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, 0);
        Assert.assertEquals(0, duplicatedObjectList.size());
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, -1);
        Assert.assertEquals(0, duplicatedObjectList.size());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.duplicateInOrder(null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.duplicateInOrder(null, 4));
    }
    
    /**
     * JUnit test of sortByNumberOfOccurrences.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#sortByNumberOfOccurrences(List, boolean)
     * @see ListUtility#sortByNumberOfOccurrences(List)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSortByNumberOfOccurrences() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> sortedBooleanList = ListUtility.sortByNumberOfOccurrences(booleanList);
        Assert.assertArrayEquals(new Boolean[] {false, false, false, true, true}, sortedBooleanList.toArray());
        sortedBooleanList = ListUtility.sortByNumberOfOccurrences(booleanList, false);
        Assert.assertArrayEquals(new Boolean[] {false, false, false, true, true}, sortedBooleanList.toArray());
        sortedBooleanList = ListUtility.sortByNumberOfOccurrences(booleanList, true);
        Assert.assertArrayEquals(new Boolean[] {true, true, false, false, false}, sortedBooleanList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {1, 3, -5, 8, 4, 1, 1, 1, 3, 3, 1, 1, 4};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<Integer> sortedIntegerList = ListUtility.sortByNumberOfOccurrences(integerList);
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8}, sortedIntegerList.toArray());
        sortedIntegerList = ListUtility.sortByNumberOfOccurrences(integerList, false);
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8}, sortedIntegerList.toArray());
        sortedIntegerList = ListUtility.sortByNumberOfOccurrences(integerList, true);
        Assert.assertArrayEquals(new Integer[] {-5, 8, 4, 4, 3, 3, 3, 1, 1, 1, 1, 1, 1}, sortedIntegerList.toArray());
        
        //float
        Float[] floatArray = new Float[] {1.1f, 3.9f, -5.0f, 8.44f, 4.7f, 1.1f, 1.1f, 1.1f, 3.8f, 3.8f, 1.1f, 1.1f, 4.7f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<Float> sortedFloatList = ListUtility.sortByNumberOfOccurrences(floatList);
        Assert.assertArrayEquals(new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f}, sortedFloatList.toArray());
        sortedFloatList = ListUtility.sortByNumberOfOccurrences(floatList, false);
        Assert.assertArrayEquals(new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f}, sortedFloatList.toArray());
        sortedFloatList = ListUtility.sortByNumberOfOccurrences(floatList, true);
        Assert.assertArrayEquals(new Float[] {3.9f, -5.0f, 8.44f, 4.7f, 4.7f, 3.8f, 3.8f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f}, sortedFloatList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {1.1d, 3.9d, -5.0d, 8.44d, 4.7d, 1.1d, 1.1d, 1.1d, 3.8d, 3.8d, 1.1d, 1.1d, 4.7d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<Double> sortedDoubleList = ListUtility.sortByNumberOfOccurrences(doubleList);
        Assert.assertArrayEquals(new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d}, sortedDoubleList.toArray());
        sortedDoubleList = ListUtility.sortByNumberOfOccurrences(doubleList, false);
        Assert.assertArrayEquals(new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d}, sortedDoubleList.toArray());
        sortedDoubleList = ListUtility.sortByNumberOfOccurrences(doubleList, true);
        Assert.assertArrayEquals(new Double[] {3.9d, -5.0d, 8.44d, 4.7d, 4.7d, 3.8d, 3.8d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d}, sortedDoubleList.toArray());
        
        //long
        Long[] longArray = new Long[] {1000L, 3000L, -5000L, 8000L, 4000L, 1000L, 1000L, 1000L, 3000L, 3000L, 1000L, 1000L, 4000L};
        List<Long> longList = ListUtility.toList(longArray);
        List<Long> sortedLongList = ListUtility.sortByNumberOfOccurrences(longList);
        Assert.assertArrayEquals(new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L}, sortedLongList.toArray());
        sortedLongList = ListUtility.sortByNumberOfOccurrences(longList, false);
        Assert.assertArrayEquals(new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L}, sortedLongList.toArray());
        sortedLongList = ListUtility.sortByNumberOfOccurrences(longList, true);
        Assert.assertArrayEquals(new Long[] {-5000L, 8000L, 4000L, 4000L, 3000L, 3000L, 3000L, 1000L, 1000L, 1000L, 1000L, 1000L, 1000L}, sortedLongList.toArray());
        
        //object
        Object a = new Object();
        Object b = "";
        Object c = new ArithmeticException();
        Object d = new HashMap<>();
        Object[] objectArray = new Object[] {a, b, a, a, a, c, d, c, d, c};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> sortedObjectList = ListUtility.sortByNumberOfOccurrences(objectList);
        Assert.assertArrayEquals(new Object[] {a, a, a, a, c, c, c, d, d, b}, sortedObjectList.toArray());
        sortedObjectList = ListUtility.sortByNumberOfOccurrences(objectList, false);
        Assert.assertArrayEquals(new Object[] {a, a, a, a, c, c, c, d, d, b}, sortedObjectList.toArray());
        sortedObjectList = ListUtility.sortByNumberOfOccurrences(objectList, true);
        Assert.assertArrayEquals(new Object[] {b, d, d, c, c, c, a, a, a, a}, sortedObjectList.toArray());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.sortByNumberOfOccurrences(null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.sortByNumberOfOccurrences(null, true));
    }
    
}
