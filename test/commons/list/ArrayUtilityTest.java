/*
 * File:    ArrayUtilityTest.java
 * Package: commons.list
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.list;

import java.util.HashMap;
import java.util.List;

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
 * JUnit test of ArrayUtility.
 *
 * @see ArrayUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ArrayUtility.class})
public class ArrayUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ArrayUtilityTest.class);
    
    
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
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of toArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#toArray(List, Class)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testToArray() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Boolean[] booleanListArray = ArrayUtility.toArray(booleanList, Boolean.class);
        Assert.assertNotNull(booleanListArray);
        Assert.assertEquals(booleanList.size(), booleanListArray.length);
        Assert.assertArrayEquals(booleanList.toArray(), booleanListArray);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Integer[] integerListArray = ArrayUtility.toArray(integerList, Integer.class);
        Assert.assertNotNull(integerListArray);
        Assert.assertEquals(integerList.size(), integerListArray.length);
        Assert.assertArrayEquals(integerList.toArray(), integerListArray);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Float[] floatListArray = ArrayUtility.toArray(floatList, Float.class);
        Assert.assertNotNull(floatListArray);
        Assert.assertEquals(floatList.size(), floatListArray.length);
        Assert.assertArrayEquals(floatList.toArray(), floatListArray);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Double[] doubleListArray = ArrayUtility.toArray(doubleList, Double.class);
        Assert.assertNotNull(doubleListArray);
        Assert.assertEquals(doubleList.size(), doubleListArray.length);
        Assert.assertArrayEquals(doubleList.toArray(), doubleListArray);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Long[] longListArray = ArrayUtility.toArray(longList, Long.class);
        Assert.assertNotNull(longListArray);
        Assert.assertEquals(longList.size(), longListArray.length);
        Assert.assertArrayEquals(longList.toArray(), longListArray);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        Object[] objectListArray = ArrayUtility.toArray(objectList, Object.class);
        Assert.assertNotNull(objectListArray);
        Assert.assertEquals(objectList.size(), objectListArray.length);
        Assert.assertArrayEquals(objectList.toArray(), objectListArray);
        
        //invalid
        
        try {
            Object[] invalidArray = ArrayUtility.toArray(null, Object.class);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            Object[] invalidArray = ArrayUtility.toArray(objectList, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#clone(Object[])
     */
    @Test
    public void testClone() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanClone = ArrayUtility.clone(booleanArray);
        Assert.assertEquals(booleanArray.length, booleanClone.length);
        Assert.assertArrayEquals(booleanArray, booleanClone);
        Assert.assertNotEquals(System.identityHashCode(booleanArray), System.identityHashCode(booleanClone));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerClone = ArrayUtility.clone(integerArray);
        Assert.assertEquals(integerArray.length, integerClone.length);
        Assert.assertArrayEquals(integerArray, integerClone);
        Assert.assertNotEquals(System.identityHashCode(integerArray), System.identityHashCode(integerClone));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatClone = ArrayUtility.clone(floatArray);
        Assert.assertEquals(floatArray.length, floatClone.length);
        Assert.assertArrayEquals(floatArray, floatClone);
        Assert.assertNotEquals(System.identityHashCode(floatArray), System.identityHashCode(floatClone));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleClone = ArrayUtility.clone(doubleArray);
        Assert.assertEquals(doubleArray.length, doubleClone.length);
        Assert.assertArrayEquals(doubleArray, doubleClone);
        Assert.assertNotEquals(System.identityHashCode(doubleArray), System.identityHashCode(doubleClone));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longClone = ArrayUtility.clone(longArray);
        Assert.assertEquals(longArray.length, longClone.length);
        Assert.assertArrayEquals(longArray, longClone);
        Assert.assertNotEquals(System.identityHashCode(longArray), System.identityHashCode(longClone));
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectClone = ArrayUtility.clone(objectArray);
        Assert.assertEquals(objectArray.length, objectClone.length);
        Assert.assertArrayEquals(objectArray, objectClone);
        Assert.assertNotEquals(System.identityHashCode(objectArray), System.identityHashCode(objectClone));
        
        //invalid
        try {
            Object[] invalidArray = ArrayUtility.clone(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of subArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#subArray(Object[], int, int)
     * @see ArrayUtility#subArray(Object[], int)
     */
    @Test
    public void testSubArray() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanSubArray = ArrayUtility.subArray(booleanArray, 2, 4);
        Assert.assertEquals(2, booleanSubArray.length);
        Assert.assertArrayEquals(new Boolean[] {false, true}, booleanSubArray);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerSubArray = ArrayUtility.subArray(integerArray, 2);
        Assert.assertEquals(5, integerSubArray.length);
        Assert.assertArrayEquals(new Integer[] {48, 5, -4, -9, 6}, integerSubArray);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatSubArray = ArrayUtility.subArray(floatArray, 0, 4);
        Assert.assertEquals(4, floatSubArray.length);
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f}, floatSubArray);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleSubArray = ArrayUtility.subArray(doubleArray, 0);
        Assert.assertEquals(7, doubleSubArray.length);
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleSubArray);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longSubArray = ArrayUtility.subArray(longArray, 6, 7);
        Assert.assertEquals(1, longSubArray.length);
        Assert.assertArrayEquals(new Long[] {699546101L}, longSubArray);
        
        //object
        Object b = new ArithmeticException();
        Object[] objectArray = new Object[] {"", 54, b, new HashMap<>(), new Object()};
        Object[] objectSubArray = ArrayUtility.subArray(objectArray, 1, 3);
        Assert.assertEquals(2, objectSubArray.length);
        Assert.assertArrayEquals(new Object[] {54, b}, objectSubArray);
        
        //empty
        Object[] subArray = ArrayUtility.subArray(objectArray, 0, 0);
        Assert.assertEquals(0, subArray.length);
        
        //invalid
        
        try {
            Object[] invalidArray = ArrayUtility.subArray(objectArray, 0, 6);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            Object[] invalidArray = ArrayUtility.subArray(objectArray, -1, 5);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            Object[] invalidArray = ArrayUtility.subArray(objectArray, 4, 2);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            Object[] invalidArray = ArrayUtility.subArray(objectArray, 6);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            Object[] invalidArray = ArrayUtility.subArray(objectArray, -1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            Object[] invalidArray = ArrayUtility.subArray(null, 2, 4);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of merge.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#merge(Object[], Object[], Class)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testMerge() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanArray2 = new Boolean[] {true, false};
        Boolean[] booleanMergeArray = ArrayUtility.merge(booleanArray, booleanArray2, Boolean.class);
        Assert.assertEquals(7, booleanMergeArray.length);
        Assert.assertArrayEquals(new Boolean[] {true, false, false, true, false, true, false}, booleanMergeArray);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerArray2 = new Integer[] {15, 312, 48};
        Integer[] integerMergeArray = ArrayUtility.merge(integerArray, integerArray2, Integer.class);
        Assert.assertEquals(10, integerMergeArray.length);
        Assert.assertArrayEquals(new Integer[] {15, 312, 48, 5, -4, -9, 6, 15, 312, 48}, integerMergeArray);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatArray2 = new Float[] {15.1f};
        Float[] floatMergeArray = ArrayUtility.merge(floatArray, floatArray2, Float.class);
        Assert.assertEquals(9, floatMergeArray.length);
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f, 15.1f}, floatMergeArray);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleArray2 = new Double[] {-4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleMergeArray = ArrayUtility.merge(doubleArray, doubleArray2, Double.class);
        Assert.assertEquals(10, doubleMergeArray.length);
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleMergeArray);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longArray2 = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longMergeArray = ArrayUtility.merge(longArray, longArray2, Long.class);
        Assert.assertEquals(14, longMergeArray.length);
        Assert.assertArrayEquals(new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L, 15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L}, longMergeArray);
        
        //object
        Object a = "";
        Object b = 54;
        Object c = new ArithmeticException();
        Object d = new HashMap<>();
        Object e = new Object();
        Object[] objectArray = new Object[] {a, b, c, d, e};
        Object[] objectArray2 = new Object[] {b, c, e};
        Object[] objectMergeArray = ArrayUtility.merge(objectArray, objectArray2, Object.class);
        Assert.assertEquals(8, objectMergeArray.length);
        Assert.assertArrayEquals(new Object[] {a, b, c, d, e, b, c, e}, objectMergeArray);
        
        //invalid
        
        try {
            Object[] invalidList = ArrayUtility.merge(objectArray, null, Object.class);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalidList = ArrayUtility.merge(null, objectArray2, Object.class);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalidList = ArrayUtility.merge(objectArray, objectArray2, null);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of split.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#split(Object[], int, Class)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSplit() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[][] booleanSplitArray = ArrayUtility.split(booleanArray, 3, Boolean.class);
        Assert.assertEquals(2, booleanSplitArray.length);
        Assert.assertArrayEquals(new Boolean[] {true, false, false}, booleanSplitArray[0]);
        Assert.assertArrayEquals(new Boolean[] {true, false, null}, booleanSplitArray[1]);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[][] integerSplitArray = ArrayUtility.split(integerArray, 2, Integer.class);
        Assert.assertEquals(4, integerSplitArray.length);
        Assert.assertArrayEquals(new Integer[] {15, 312}, integerSplitArray[0]);
        Assert.assertArrayEquals(new Integer[] {48, 5}, integerSplitArray[1]);
        Assert.assertArrayEquals(new Integer[] {-4, -9}, integerSplitArray[2]);
        Assert.assertArrayEquals(new Integer[] {6, null}, integerSplitArray[3]);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[][] floatSplitArray = ArrayUtility.split(floatArray, 4, Float.class);
        Assert.assertEquals(2, floatSplitArray.length);
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f}, floatSplitArray[0]);
        Assert.assertArrayEquals(new Float[] {-4.006f, -9.7f, 6.99f, 19776.4f}, floatSplitArray[1]);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[][] doubleSplitArray = ArrayUtility.split(doubleArray, 7, Double.class);
        Assert.assertEquals(1, doubleSplitArray.length);
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleSplitArray[0]);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[][] longSplitArray = ArrayUtility.split(longArray, 1, Long.class);
        Assert.assertEquals(7, longSplitArray.length);
        Assert.assertArrayEquals(new Long[] {15104564L}, longSplitArray[0]);
        Assert.assertArrayEquals(new Long[] {3129113874L}, longSplitArray[1]);
        Assert.assertArrayEquals(new Long[] {4800000015L}, longSplitArray[2]);
        Assert.assertArrayEquals(new Long[] {5457894511L}, longSplitArray[3]);
        Assert.assertArrayEquals(new Long[] {-4006005001L}, longSplitArray[4]);
        Assert.assertArrayEquals(new Long[] {-970487745L}, longSplitArray[5]);
        Assert.assertArrayEquals(new Long[] {699546101L}, longSplitArray[6]);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[][] objectSplitArray = ArrayUtility.split(objectArray, 3, Object.class);
        Assert.assertEquals(2, objectSplitArray.length);
        Assert.assertArrayEquals(new Object[] {"", 54, objectArray[2]}, objectSplitArray[0]);
        Assert.assertArrayEquals(new Object[] {objectArray[3], objectArray[4], null}, objectSplitArray[1]);
        
        //invalid
        
        objectSplitArray = ArrayUtility.split(objectArray, 0, Object.class);
        Assert.assertEquals(objectArray.length, objectSplitArray.length);
        
        objectSplitArray = ArrayUtility.split(objectArray, -1, Object.class);
        Assert.assertEquals(objectArray.length, objectSplitArray.length);
        
        objectSplitArray = ArrayUtility.split(objectArray, 15613, Object.class);
        Assert.assertEquals(1, objectSplitArray.length);
        
        objectSplitArray = ArrayUtility.split(new Object[] {}, 3, Object.class);
        Assert.assertEquals(0, objectSplitArray.length);
        
        try {
            Object[][] invalidArray = ArrayUtility.split(null, 3, Object.class);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            Object[][] invalidArray = ArrayUtility.split(objectArray, 3, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of anyNull.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#anyNull(Object[])
     */
    @SuppressWarnings({"ConstantConditions", "WrapperTypeMayBePrimitive"})
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
        Object[] array;
        
        //all
        array = new Object[] {a, b, c, d, e, f, g, h, i, j, k, l, m, n};
        Assert.assertTrue(ArrayUtility.anyNull(array));
        
        //all not null
        array = new Object[] {a, c, e, g, i, k, m};
        Assert.assertFalse(ArrayUtility.anyNull(array));
        
        //all null
        array = new Object[] {b, d, f, h, j, l, n};
        Assert.assertTrue(ArrayUtility.anyNull(array));
        
        //none
        array = new Object[] {};
        Assert.assertFalse(ArrayUtility.anyNull(array));
        
        //boolean
        array = new Object[] {a, b, c, e, g, i, k, m};
        Assert.assertTrue(ArrayUtility.anyNull(array));
        
        //int
        array = new Object[] {a, c, d, e, g, i, k, m};
        Assert.assertTrue(ArrayUtility.anyNull(array));
        
        //float
        array = new Object[] {a, c, e, f, g, i, k, m};
        Assert.assertTrue(ArrayUtility.anyNull(array));
        
        //double
        array = new Object[] {a, c, e, g, h, i, k, m};
        Assert.assertTrue(ArrayUtility.anyNull(array));
        
        //long
        array = new Object[] {a, c, e, g, i, j, k, m};
        Assert.assertTrue(ArrayUtility.anyNull(array));
        
        //string
        array = new Object[] {a, c, e, g, i, k, l, m};
        Assert.assertTrue(ArrayUtility.anyNull(array));
        
        //object
        array = new Object[] {a, c, e, g, i, k, m, n};
        Assert.assertTrue(ArrayUtility.anyNull(array));
        
        //invalid
        try {
            boolean invalid = ArrayUtility.anyNull(null);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of removeDuplicates.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#removeDuplicates(Object[], Class)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testRemoveDuplicates() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanCleanArray = ArrayUtility.removeDuplicates(booleanArray, Boolean.class);
        Assert.assertEquals(2, booleanCleanArray.length);
        
        //int
        Integer[] integerArray = new Integer[] {15, 15, 312, 48, 5, 5, -4, -9, -9, 6};
        Integer[] integerCleanList = ArrayUtility.removeDuplicates(integerArray, Integer.class);
        Assert.assertEquals(7, integerCleanList.length);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 15.1f, 15.1f, 312.91f, 312.91f, 48.0f, 5.45f, -4.006f, -4.006f, -9.7f, 6.99f, 6.99f};
        Float[] floatCleanList = ArrayUtility.removeDuplicates(floatArray, Float.class);
        Assert.assertEquals(7, floatCleanList.length);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 15.104564d, 15.104564d, 312.9113874d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -4.006005001d, -9.70487745d, 6.99546101d, 6.99546101d};
        Double[] doubleCleanList = ArrayUtility.removeDuplicates(doubleArray, Double.class);
        Assert.assertEquals(7, doubleCleanList.length);
        
        //long
        Long[] longArray = new Long[] {15104564L, 15104564L, 15104564L, 3129113874L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -4006005001L, -970487745L, 699546101L, 699546101L};
        Long[] longCleanList = ArrayUtility.removeDuplicates(longArray, Long.class);
        Assert.assertEquals(7, longCleanList.length);
        
        //object
        String s = "";
        ArithmeticException ae = new ArithmeticException();
        HashMap<Object, Object> hm = new HashMap<>();
        Object o = new Object();
        Object[] objectArray = new Object[] {s, s, s, 54, 54, ae, ae, hm, hm, hm, o, o};
        Object[] objectCleanList = ArrayUtility.removeDuplicates(objectArray, Object.class);
        Assert.assertEquals(5, objectCleanList.length);
        
        //invalid
        
        try {
            Object[] invalid = ArrayUtility.removeDuplicates(null, Object.class);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalid = ArrayUtility.removeDuplicates(objectArray, null);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of selectRandom.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#selectRandom(Object[])
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSelectRandom() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        for (int i = 0; i < 100; i++) {
            Boolean booleanSelected = ArrayUtility.selectRandom(booleanArray);
            Assert.assertNotNull(booleanSelected);
            Assert.assertTrue(booleanList.contains(booleanSelected));
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        for (int i = 0; i < 100; i++) {
            Integer integerSelected = ArrayUtility.selectRandom(integerArray);
            Assert.assertNotNull(integerSelected);
            Assert.assertTrue(integerList.contains(integerSelected));
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        for (int i = 0; i < 100; i++) {
            Float floatSelected = ArrayUtility.selectRandom(floatArray);
            Assert.assertNotNull(floatSelected);
            Assert.assertTrue(floatList.contains(floatSelected));
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        for (int i = 0; i < 100; i++) {
            Double doubleSelected = ArrayUtility.selectRandom(doubleArray);
            Assert.assertNotNull(doubleSelected);
            Assert.assertTrue(doubleList.contains(doubleSelected));
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        for (int i = 0; i < 100; i++) {
            Long longSelected = ArrayUtility.selectRandom(longArray);
            Assert.assertNotNull(longSelected);
            Assert.assertTrue(longList.contains(longSelected));
        }
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        for (int i = 0; i < 100; i++) {
            Object objectSelected = ArrayUtility.selectRandom(objectArray);
            Assert.assertNotNull(objectSelected);
            Assert.assertTrue(objectList.contains(objectSelected));
        }
        
        //empty list
        Assert.assertNull(ArrayUtility.selectRandom(new Object[] {}));
        
        //invalid
        try {
            Object invalid = ArrayUtility.selectRandom(null);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of selectN.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#selectN(Object[], int, Class)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSelectN() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Boolean[] booleanSelected = ArrayUtility.selectN(booleanArray, 1, Boolean.class);
        Assert.assertEquals(1, booleanSelected.length);
        for (Boolean b : booleanSelected) {
            Assert.assertTrue(booleanList.contains(b));
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Integer[] integerSelected = ArrayUtility.selectN(integerArray, 5, Integer.class);
        Assert.assertEquals(5, integerSelected.length);
        for (Integer i : integerSelected) {
            Assert.assertTrue(integerList.contains(i));
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Float[] floatSelected = ArrayUtility.selectN(floatArray, 7, Float.class);
        Assert.assertEquals(7, floatSelected.length);
        for (Float f : floatSelected) {
            Assert.assertTrue(floatList.contains(f));
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Double[] doubleSelected = ArrayUtility.selectN(doubleArray, 3, Double.class);
        Assert.assertEquals(3, doubleSelected.length);
        for (Double d : doubleSelected) {
            Assert.assertTrue(doubleList.contains(d));
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Long[] longSelected = ArrayUtility.selectN(longArray, 4, Long.class);
        Assert.assertEquals(4, longSelected.length);
        for (Long l : longSelected) {
            Assert.assertTrue(longList.contains(l));
        }
        
        //object
        
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        Object[] objectSelected = ArrayUtility.selectN(objectArray, 3, Object.class);
        Assert.assertEquals(3, objectSelected.length);
        for (Object o : objectSelected) {
            Assert.assertTrue(objectList.contains(o));
        }
        
        Object[] overSizeObjectSelected = ArrayUtility.selectN(objectArray, 999, Object.class);
        Assert.assertEquals(objectArray.length, overSizeObjectSelected.length);
        for (Object o : overSizeObjectSelected) {
            Assert.assertTrue(objectList.contains(o));
        }
        
        //invalid
        
        Object[] underSizeObjectSelected = ArrayUtility.selectN(objectArray, -1, Object.class);
        Assert.assertEquals(0, underSizeObjectSelected.length);
        
        try {
            Object[] invalid = ArrayUtility.selectN(null, 4, Object.class);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalid = ArrayUtility.selectN(objectArray, 4, null);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of duplicateInOrder.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#duplicateInOrder(Object[], int, Class)
     * @see ArrayUtility#duplicateInOrder(Object[], Class)
     */
    @Test
    public void testDuplicateInOrder() throws Exception {
        int x;
        
        //standard
        Object[] standardArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] duplicatedStandardArray = ArrayUtility.duplicateInOrder(standardArray, Object.class);
        Assert.assertEquals(standardArray.length * 2, duplicatedStandardArray.length);
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Object o : standardArray) {
                Assert.assertEquals(o, duplicatedStandardArray[x]);
                x++;
            }
        }
        duplicatedStandardArray = ArrayUtility.duplicateInOrder(standardArray, 3, Object.class);
        Assert.assertEquals(standardArray.length * 3, duplicatedStandardArray.length);
        x = 0;
        for (int y = 0; y < 3; y++) {
            for (Object o : standardArray) {
                Assert.assertEquals(o, duplicatedStandardArray[x]);
                x++;
            }
        }
        
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] duplicatedBooleanArray = ArrayUtility.duplicateInOrder(booleanArray, Boolean.class);
        Assert.assertEquals(booleanArray.length * 2, duplicatedBooleanArray.length);
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Boolean b : booleanArray) {
                Assert.assertEquals(b, duplicatedBooleanArray[x]);
                x++;
            }
        }
        duplicatedBooleanArray = ArrayUtility.duplicateInOrder(booleanArray, 6, Boolean.class);
        Assert.assertEquals(booleanArray.length * 6, duplicatedBooleanArray.length);
        x = 0;
        for (int y = 0; y < 6; y++) {
            for (Boolean b : booleanArray) {
                Assert.assertEquals(b, duplicatedBooleanArray[x]);
                x++;
            }
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] duplicatedIntegerArray = ArrayUtility.duplicateInOrder(integerArray, Integer.class);
        Assert.assertEquals(integerArray.length * 2, duplicatedIntegerArray.length);
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Integer i : integerArray) {
                Assert.assertEquals(i, duplicatedIntegerArray[x]);
                x++;
            }
        }
        duplicatedIntegerArray = ArrayUtility.duplicateInOrder(integerArray, 150, Integer.class);
        Assert.assertEquals(integerArray.length * 150, duplicatedIntegerArray.length);
        x = 0;
        for (int y = 0; y < 150; y++) {
            for (Integer i : integerArray) {
                Assert.assertEquals(i, duplicatedIntegerArray[x]);
                x++;
            }
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] duplicatedFloatArray = ArrayUtility.duplicateInOrder(floatArray, Float.class);
        Assert.assertEquals(floatArray.length * 2, duplicatedFloatArray.length);
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Float f : floatArray) {
                Assert.assertEquals(f, duplicatedFloatArray[x]);
                x++;
            }
        }
        duplicatedFloatArray = ArrayUtility.duplicateInOrder(floatArray, 150, Float.class);
        Assert.assertEquals(floatArray.length * 150, duplicatedFloatArray.length);
        x = 0;
        for (int y = 0; y < 150; y++) {
            for (Float f : floatArray) {
                Assert.assertEquals(f, duplicatedFloatArray[x]);
                x++;
            }
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] duplicatedDoubleArray = ArrayUtility.duplicateInOrder(doubleArray, Double.class);
        Assert.assertEquals(doubleArray.length * 2, duplicatedDoubleArray.length);
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Double d : doubleArray) {
                Assert.assertEquals(d, duplicatedDoubleArray[x]);
                x++;
            }
        }
        duplicatedDoubleArray = ArrayUtility.duplicateInOrder(doubleArray, 8, Double.class);
        Assert.assertEquals(doubleArray.length * 8, duplicatedDoubleArray.length);
        x = 0;
        for (int y = 0; y < 8; y++) {
            for (Double d : doubleArray) {
                Assert.assertEquals(d, duplicatedDoubleArray[x]);
                x++;
            }
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] duplicatedLongArray = ArrayUtility.duplicateInOrder(longArray, Long.class);
        Assert.assertEquals(longArray.length * 2, duplicatedLongArray.length);
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Long l : longArray) {
                Assert.assertEquals(l, duplicatedLongArray[x]);
                x++;
            }
        }
        duplicatedLongArray = ArrayUtility.duplicateInOrder(longArray, 10, Long.class);
        Assert.assertEquals(longArray.length * 10, duplicatedLongArray.length);
        x = 0;
        for (int y = 0; y < 10; y++) {
            for (Long l : longArray) {
                Assert.assertEquals(l, duplicatedLongArray[x]);
                x++;
            }
        }
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] duplicatedObjectArray = ArrayUtility.duplicateInOrder(objectArray, Object.class);
        Assert.assertEquals(objectArray.length * 2, duplicatedObjectArray.length);
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Object o : objectArray) {
                Assert.assertEquals(o, duplicatedObjectArray[x]);
                x++;
            }
        }
        duplicatedObjectArray = ArrayUtility.duplicateInOrder(objectArray, 10, Object.class);
        Assert.assertEquals(objectArray.length * 10, duplicatedObjectArray.length);
        x = 0;
        for (int y = 0; y < 10; y++) {
            for (Object o : objectArray) {
                Assert.assertEquals(o, duplicatedObjectArray[x]);
                x++;
            }
        }
        
        //edge case
        duplicatedObjectArray = ArrayUtility.duplicateInOrder(objectArray, 1, Object.class);
        Assert.assertEquals(objectArray.length, duplicatedObjectArray.length);
        x = 0;
        for (int y = 0; y < 1; y++) {
            for (Object o : objectArray) {
                Assert.assertEquals(o, duplicatedObjectArray[x]);
                x++;
            }
        }
        duplicatedObjectArray = ArrayUtility.duplicateInOrder(objectArray, 0, Object.class);
        Assert.assertEquals(0, duplicatedObjectArray.length);
        duplicatedObjectArray = ArrayUtility.duplicateInOrder(objectArray, -1, Object.class);
        Assert.assertEquals(0, duplicatedObjectArray.length);
        
        //invalid
        
        try {
            Object[] invalid = ArrayUtility.duplicateInOrder(null, Object.class);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalid = ArrayUtility.duplicateInOrder(objectArray, null);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalid = ArrayUtility.duplicateInOrder(null, 4, Object.class);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalid = ArrayUtility.duplicateInOrder(objectArray, 4, null);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of sortByNumberOfOccurrences.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#sortByNumberOfOccurrences(Object[], boolean, Class)
     * @see ArrayUtility#sortByNumberOfOccurrences(Object[], Class)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSortByNumberOfOccurrences() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] sortedBooleanArray = ArrayUtility.sortByNumberOfOccurrences(booleanArray, Boolean.class);
        Assert.assertArrayEquals(new Boolean[] {false, false, false, true, true}, sortedBooleanArray);
        sortedBooleanArray = ArrayUtility.sortByNumberOfOccurrences(booleanArray, false, Boolean.class);
        Assert.assertArrayEquals(new Boolean[] {false, false, false, true, true}, sortedBooleanArray);
        sortedBooleanArray = ArrayUtility.sortByNumberOfOccurrences(booleanArray, true, Boolean.class);
        Assert.assertArrayEquals(new Boolean[] {true, true, false, false, false}, sortedBooleanArray);
        
        //int
        Integer[] integerArray = new Integer[] {1, 3, -5, 8, 4, 1, 1, 1, 3, 3, 1, 1, 4};
        Integer[] sortedIntegerArray = ArrayUtility.sortByNumberOfOccurrences(integerArray, Integer.class);
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8}, sortedIntegerArray);
        sortedIntegerArray = ArrayUtility.sortByNumberOfOccurrences(integerArray, false, Integer.class);
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8}, sortedIntegerArray);
        sortedIntegerArray = ArrayUtility.sortByNumberOfOccurrences(integerArray, true, Integer.class);
        Assert.assertArrayEquals(new Integer[] {-5, 8, 4, 4, 3, 3, 3, 1, 1, 1, 1, 1, 1}, sortedIntegerArray);
        
        //float
        Float[] floatArray = new Float[] {1.1f, 3.9f, -5.0f, 8.44f, 4.7f, 1.1f, 1.1f, 1.1f, 3.8f, 3.8f, 1.1f, 1.1f, 4.7f};
        Float[] sortedFloatArray = ArrayUtility.sortByNumberOfOccurrences(floatArray, Float.class);
        Assert.assertArrayEquals(new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f}, sortedFloatArray);
        sortedFloatArray = ArrayUtility.sortByNumberOfOccurrences(floatArray, false, Float.class);
        Assert.assertArrayEquals(new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f}, sortedFloatArray);
        sortedFloatArray = ArrayUtility.sortByNumberOfOccurrences(floatArray, true, Float.class);
        Assert.assertArrayEquals(new Float[] {3.9f, -5.0f, 8.44f, 4.7f, 4.7f, 3.8f, 3.8f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f}, sortedFloatArray);
        
        //double
        Double[] doubleArray = new Double[] {1.1d, 3.9d, -5.0d, 8.44d, 4.7d, 1.1d, 1.1d, 1.1d, 3.8d, 3.8d, 1.1d, 1.1d, 4.7d};
        Double[] sortedDoubleArray = ArrayUtility.sortByNumberOfOccurrences(doubleArray, Double.class);
        Assert.assertArrayEquals(new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d}, sortedDoubleArray);
        sortedDoubleArray = ArrayUtility.sortByNumberOfOccurrences(doubleArray, false, Double.class);
        Assert.assertArrayEquals(new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d}, sortedDoubleArray);
        sortedDoubleArray = ArrayUtility.sortByNumberOfOccurrences(doubleArray, true, Double.class);
        Assert.assertArrayEquals(new Double[] {3.9d, -5.0d, 8.44d, 4.7d, 4.7d, 3.8d, 3.8d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d}, sortedDoubleArray);
        
        //long
        Long[] longArray = new Long[] {1000L, 3000L, -5000L, 8000L, 4000L, 1000L, 1000L, 1000L, 3000L, 3000L, 1000L, 1000L, 4000L};
        Long[] sortedLongArray = ArrayUtility.sortByNumberOfOccurrences(longArray, Long.class);
        Assert.assertArrayEquals(new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L}, sortedLongArray);
        sortedLongArray = ArrayUtility.sortByNumberOfOccurrences(longArray, false, Long.class);
        Assert.assertArrayEquals(new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L}, sortedLongArray);
        sortedLongArray = ArrayUtility.sortByNumberOfOccurrences(longArray, true, Long.class);
        Assert.assertArrayEquals(new Long[] {-5000L, 8000L, 4000L, 4000L, 3000L, 3000L, 3000L, 1000L, 1000L, 1000L, 1000L, 1000L, 1000L}, sortedLongArray);
        
        //object
        Object a = new Object();
        Object b = "";
        Object c = new ArithmeticException();
        Object d = new HashMap<>();
        Object[] objectArray = new Object[] {a, b, a, a, a, c, d, c, d, c};
        Object[] sortedObjectArray = ArrayUtility.sortByNumberOfOccurrences(objectArray, Object.class);
        Assert.assertArrayEquals(new Object[] {a, a, a, a, c, c, c, d, d, b}, sortedObjectArray);
        sortedObjectArray = ArrayUtility.sortByNumberOfOccurrences(objectArray, false, Object.class);
        Assert.assertArrayEquals(new Object[] {a, a, a, a, c, c, c, d, d, b}, sortedObjectArray);
        sortedObjectArray = ArrayUtility.sortByNumberOfOccurrences(objectArray, true, Object.class);
        Assert.assertArrayEquals(new Object[] {b, d, d, c, c, c, a, a, a, a}, sortedObjectArray);
        
        //invalid
        
        try {
            Object[] invalid = ArrayUtility.sortByNumberOfOccurrences(null, Object.class);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalid = ArrayUtility.sortByNumberOfOccurrences(objectArray, null);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalid = ArrayUtility.sortByNumberOfOccurrences(null, true, Object.class);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
        
        try {
            Object[] invalid = ArrayUtility.sortByNumberOfOccurrences(objectArray, true, null);
            Assert.fail();
        } catch (Exception exception) {
            Assert.assertTrue(exception instanceof NullPointerException);
        }
    }
    
}
