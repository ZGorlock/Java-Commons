/*
 * File:    ListUtilityTest.java
 * Package: commons.list
 * Author:  Zachary Gill
 */

package commons.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of arrayToList.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#arrayToList(Object[])
     */
    @Test
    public void testArrayToList() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.arrayToList(booleanArray);
        Assert.assertEquals(booleanArray.length, booleanList.size());
        Assert.assertArrayEquals(booleanArray, booleanList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.arrayToList(integerArray);
        Assert.assertEquals(integerArray.length, integerList.size());
        Assert.assertArrayEquals(integerArray, integerList.toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.arrayToList(floatArray);
        Assert.assertEquals(floatArray.length, floatList.size());
        Assert.assertArrayEquals(floatArray, floatList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.arrayToList(doubleArray);
        Assert.assertEquals(doubleArray.length, doubleList.size());
        Assert.assertArrayEquals(doubleArray, doubleList.toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.arrayToList(longArray);
        Assert.assertEquals(longArray.length, longList.size());
        Assert.assertArrayEquals(longArray, longList.toArray());
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.arrayToList(objectArray);
        Assert.assertEquals(objectArray.length, objectList.size());
        Assert.assertArrayEquals(objectArray, objectList.toArray());
    }
    
    /**
     * JUnit test of split.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#split(List, int)
     */
    @Test
    public void testSplit() throws Exception {
        //standard
        
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<List<Boolean>> booleanList = ListUtility.split(Arrays.asList(booleanArray), 3);
        Assert.assertEquals(2, booleanList.size());
        Assert.assertArrayEquals(new Boolean[] {true, false, false}, booleanList.get(0).toArray());
        Assert.assertArrayEquals(new Boolean[] {true, false}, booleanList.get(1).toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<List<Integer>> integerList = ListUtility.split(Arrays.asList(integerArray), 2);
        Assert.assertEquals(4, integerList.size());
        Assert.assertArrayEquals(new Integer[] {15, 312}, integerList.get(0).toArray());
        Assert.assertArrayEquals(new Integer[] {48, 5}, integerList.get(1).toArray());
        Assert.assertArrayEquals(new Integer[] {-4, -9}, integerList.get(2).toArray());
        Assert.assertArrayEquals(new Integer[] {6}, integerList.get(3).toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<List<Float>> floatList = ListUtility.split(Arrays.asList(floatArray), 4);
        Assert.assertEquals(2, floatList.size());
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f}, floatList.get(0).toArray());
        Assert.assertArrayEquals(new Float[] {-4.006f, -9.7f, 6.99f, 19776.4f}, floatList.get(1).toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<List<Double>> doubleList = ListUtility.split(Arrays.asList(doubleArray), 7);
        Assert.assertEquals(1, doubleList.size());
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleList.get(0).toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<List<Long>> longList = ListUtility.split(Arrays.asList(longArray), 1);
        Assert.assertEquals(7, longList.size());
        Assert.assertArrayEquals(new Long[] {15104564L}, longList.get(0).toArray());
        Assert.assertArrayEquals(new Long[] {3129113874L}, longList.get(1).toArray());
        Assert.assertArrayEquals(new Long[] {4800000015L}, longList.get(2).toArray());
        Assert.assertArrayEquals(new Long[] {5457894511L}, longList.get(3).toArray());
        Assert.assertArrayEquals(new Long[] {-4006005001L}, longList.get(4).toArray());
        Assert.assertArrayEquals(new Long[] {-970487745L}, longList.get(5).toArray());
        Assert.assertArrayEquals(new Long[] {699546101L}, longList.get(6).toArray());
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<List<Object>> objectList = ListUtility.split(Arrays.asList(objectArray), 3);
        Assert.assertEquals(2, objectList.size());
        Assert.assertArrayEquals(new Object[] {"", 54, objectArray[2]}, objectList.get(0).toArray());
        Assert.assertArrayEquals(new Object[] {objectArray[3], objectArray[4]}, objectList.get(1).toArray());
        
        //invalid
        
        objectList = ListUtility.split(Arrays.asList(objectArray), 0);
        Assert.assertEquals(objectArray.length, objectList.size());
        
        objectList = ListUtility.split(Arrays.asList(objectArray), -1);
        Assert.assertEquals(objectArray.length, objectList.size());
        
        objectList = ListUtility.split(Arrays.asList(objectArray), 15613);
        Assert.assertEquals(1, objectList.size());
        
        objectList = ListUtility.split(Collections.emptyList(), 3);
        Assert.assertEquals(0, objectList.size());
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
        
        //all
        list = Arrays.asList(a, b, c, d, e, f, g, h, i, j, k, l, m, n);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(a, b, c, d, e, f, g, h, i, j, k, l, m, n));
        
        //all not null
        list = Arrays.asList(a, c, e, g, i, k, m);
        Assert.assertFalse(ListUtility.anyNull(list));
        Assert.assertFalse(ListUtility.anyNull(a, c, e, g, i, k, m));
        
        //all null
        list = Arrays.asList(b, d, f, h, j, l, n);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(b, d, f, h, j, l, n));
        
        //none
        list = Collections.emptyList();
        Assert.assertFalse(ListUtility.anyNull(list));
        Assert.assertFalse(ListUtility.anyNull());
        
        //boolean
        list = Arrays.asList(a, b, c, e, g, i, k, m);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(a, b, c, e, g, i, k, m));
        
        //int
        list = Arrays.asList(a, c, d, e, g, i, k, m);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(a, b, c, d, e, g, i, k, m));
        
        //float
        list = Arrays.asList(a, c, e, f, g, i, k, m);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, f, g, i, k, m));
        
        //double
        list = Arrays.asList(a, c, e, g, h, i, k, m);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, h, i, k, m));
        
        //long
        list = Arrays.asList(a, c, e, g, i, j, k, m);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, i, j, k, m));
        
        //string
        list = Arrays.asList(a, c, e, g, i, k, l, m);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, i, k, l, m));
        
        //object
        list = Arrays.asList(a, c, e, g, i, k, m, n);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, i, k, m, n));
    }
    
    /**
     * JUnit test of removeDuplicates.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#removeDuplicates(List)
     */
    @Test
    public void testRemoveDuplicates() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.removeDuplicates(ListUtility.arrayToList(booleanArray));
        Assert.assertEquals(2, booleanList.size());
        
        //int
        Integer[] integerArray = new Integer[] {15, 15, 312, 48, 5, 5, -4, -9, -9, 6};
        List<Integer> integerList = ListUtility.removeDuplicates(ListUtility.arrayToList(integerArray));
        Assert.assertEquals(7, integerList.size());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 15.1f, 15.1f, 312.91f, 312.91f, 48.0f, 5.45f, -4.006f, -4.006f, -9.7f, 6.99f, 6.99f};
        List<Float> floatList = ListUtility.removeDuplicates(ListUtility.arrayToList(floatArray));
        Assert.assertEquals(7, floatList.size());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 15.104564d, 15.104564d, 312.9113874d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -4.006005001d, -9.70487745d, 6.99546101d, 6.99546101d};
        List<Double> doubleList = ListUtility.removeDuplicates(ListUtility.arrayToList(doubleArray));
        Assert.assertEquals(7, doubleList.size());
        
        //long
        Long[] longArray = new Long[] {15104564L, 15104564L, 15104564L, 3129113874L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -4006005001L, -970487745L, 699546101L, 699546101L};
        List<Long> longList = ListUtility.removeDuplicates(ListUtility.arrayToList(longArray));
        Assert.assertEquals(7, longList.size());
        
        //object
        String s = "";
        ArithmeticException ae = new ArithmeticException();
        HashMap<Object, Object> hm = new HashMap<>();
        Object o = new Object();
        Object[] objectArray = new Object[] {s, s, s, 54, 54, ae, ae, hm, hm, hm, o, o};
        List<Object> objectList = ListUtility.removeDuplicates(ListUtility.arrayToList(objectArray));
        Assert.assertEquals(5, objectList.size());
    }
    
    /**
     * JUnit test of selectRandom.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#selectRandom(List)
     */
    @Test
    public void testSelectRandom() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.arrayToList(booleanArray);
        for (int i = 0; i < 100; i++) {
            Boolean booleanSelected = ListUtility.selectRandom(booleanList);
            Assert.assertNotNull(booleanSelected);
            Assert.assertTrue(booleanList.contains(booleanSelected));
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.arrayToList(integerArray);
        for (int i = 0; i < 100; i++) {
            Integer integerSelected = ListUtility.selectRandom(integerList);
            Assert.assertNotNull(integerSelected);
            Assert.assertTrue(integerList.contains(integerSelected));
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.arrayToList(floatArray);
        for (int i = 0; i < 100; i++) {
            Float floatSelected = ListUtility.selectRandom(floatList);
            Assert.assertNotNull(floatSelected);
            Assert.assertTrue(floatList.contains(floatSelected));
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.arrayToList(doubleArray);
        for (int i = 0; i < 100; i++) {
            Double doubleSelected = ListUtility.selectRandom(doubleList);
            Assert.assertNotNull(doubleSelected);
            Assert.assertTrue(doubleList.contains(doubleSelected));
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.arrayToList(longArray);
        for (int i = 0; i < 100; i++) {
            Long longSelected = ListUtility.selectRandom(longList);
            Assert.assertNotNull(longSelected);
            Assert.assertTrue(longList.contains(longSelected));
        }
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.arrayToList(objectArray);
        for (int i = 0; i < 100; i++) {
            Object objectSelected = ListUtility.selectRandom(objectList);
            Assert.assertNotNull(objectSelected);
            Assert.assertTrue(objectList.contains(objectSelected));
        }
        
        //empty list
        Assert.assertNull(ListUtility.selectRandom(new ArrayList<>()));
    }
    
    /**
     * JUnit test of selectNFromList.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#selectNFromList(List, int)
     */
    @Test
    public void testSelectNFromList() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.arrayToList(booleanArray);
        List<Boolean> booleanSelected = ListUtility.selectNFromList(booleanList, 1);
        Assert.assertEquals(1, booleanSelected.size());
        for (Boolean b : booleanSelected) {
            Assert.assertTrue(booleanList.contains(b));
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.arrayToList(integerArray);
        List<Integer> integerSelected = ListUtility.selectNFromList(integerList, 5);
        Assert.assertEquals(5, integerSelected.size());
        for (Integer i : integerSelected) {
            Assert.assertTrue(integerList.contains(i));
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.arrayToList(floatArray);
        List<Float> floatSelected = ListUtility.selectNFromList(floatList, 7);
        Assert.assertEquals(7, floatSelected.size());
        for (Float f : floatSelected) {
            Assert.assertTrue(floatList.contains(f));
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.arrayToList(doubleArray);
        List<Double> doubleSelected = ListUtility.selectNFromList(doubleList, 3);
        Assert.assertEquals(3, doubleSelected.size());
        for (Double d : doubleSelected) {
            Assert.assertTrue(doubleList.contains(d));
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.arrayToList(longArray);
        List<Long> longSelected = ListUtility.selectNFromList(longList, 4);
        Assert.assertEquals(4, longSelected.size());
        for (Long l : longSelected) {
            Assert.assertTrue(longList.contains(l));
        }
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.arrayToList(objectArray);
        List<Object> objectSelected = ListUtility.selectNFromList(objectList, 3);
        Assert.assertEquals(3, objectSelected.size());
        for (Object o : objectSelected) {
            Assert.assertTrue(objectList.contains(o));
        }
        
        //over size
        List<Object> overSizeObjectSelected = ListUtility.selectNFromList(objectList, 999);
        Assert.assertEquals(objectList.size(), overSizeObjectSelected.size());
        for (Object o : overSizeObjectSelected) {
            Assert.assertTrue(objectList.contains(o));
        }
    }
    
    /**
     * JUnit test of duplicateListInOrder.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#duplicateListInOrder(List, int)
     * @see ListUtility#duplicateListInOrder(List)
     */
    @Test
    public void testDuplicateListInOrder() throws Exception {
        int x;
        
        //standard
        Object[] standardArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> standardList = ListUtility.arrayToList(standardArray);
        List<Object> duplicatedStandardList = ListUtility.duplicateListInOrder(standardList);
        Assert.assertEquals(standardList.size() * 2, duplicatedStandardList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Object o : standardList) {
                Assert.assertEquals(o, duplicatedStandardList.get(x));
                x++;
            }
        }
        duplicatedStandardList = ListUtility.duplicateListInOrder(standardList);
        Assert.assertEquals(standardList.size() * 2, duplicatedStandardList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Object o : standardList) {
                Assert.assertEquals(o, duplicatedStandardList.get(x));
                x++;
            }
        }
        
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.arrayToList(booleanArray);
        List<Boolean> duplicatedBooleanList = ListUtility.duplicateListInOrder(booleanList);
        Assert.assertEquals(booleanList.size() * 2, duplicatedBooleanList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Boolean b : booleanList) {
                Assert.assertEquals(b, duplicatedBooleanList.get(x));
                x++;
            }
        }
        duplicatedBooleanList = ListUtility.duplicateListInOrder(booleanList, 6);
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
        List<Integer> integerList = ListUtility.arrayToList(integerArray);
        List<Integer> duplicatedIntegerList = ListUtility.duplicateListInOrder(integerList);
        Assert.assertEquals(integerList.size() * 2, duplicatedIntegerList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Integer i : integerList) {
                Assert.assertEquals(i, duplicatedIntegerList.get(x));
                x++;
            }
        }
        duplicatedIntegerList = ListUtility.duplicateListInOrder(integerList, 150);
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
        List<Float> floatList = ListUtility.arrayToList(floatArray);
        List<Float> duplicatedFloatList = ListUtility.duplicateListInOrder(floatList);
        Assert.assertEquals(floatList.size() * 2, duplicatedFloatList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Float f : floatList) {
                Assert.assertEquals(f, duplicatedFloatList.get(x));
                x++;
            }
        }
        duplicatedFloatList = ListUtility.duplicateListInOrder(floatList, 150);
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
        List<Double> doubleList = ListUtility.arrayToList(doubleArray);
        List<Double> duplicatedDoubleList = ListUtility.duplicateListInOrder(doubleList);
        Assert.assertEquals(doubleList.size() * 2, duplicatedDoubleList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Double d : doubleList) {
                Assert.assertEquals(d, duplicatedDoubleList.get(x));
                x++;
            }
        }
        duplicatedDoubleList = ListUtility.duplicateListInOrder(doubleList, 8);
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
        List<Long> longList = ListUtility.arrayToList(longArray);
        List<Long> duplicatedLongList = ListUtility.duplicateListInOrder(longList);
        Assert.assertEquals(longList.size() * 2, duplicatedLongList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Long l : longList) {
                Assert.assertEquals(l, duplicatedLongList.get(x));
                x++;
            }
        }
        duplicatedLongList = ListUtility.duplicateListInOrder(longList, 10);
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
        List<Object> objectList = ListUtility.arrayToList(objectArray);
        List<Object> duplicatedObjectList = ListUtility.duplicateListInOrder(objectList);
        Assert.assertEquals(objectList.size() * 2, duplicatedObjectList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Object o : objectList) {
                Assert.assertEquals(o, duplicatedObjectList.get(x));
                x++;
            }
        }
        duplicatedObjectList = ListUtility.duplicateListInOrder(objectList, 10);
        Assert.assertEquals(objectList.size() * 10, duplicatedObjectList.size());
        x = 0;
        for (int y = 0; y < 10; y++) {
            for (Object o : objectList) {
                Assert.assertEquals(o, duplicatedObjectList.get(x));
                x++;
            }
        }
        
        //edge case
        duplicatedObjectList = ListUtility.duplicateListInOrder(objectList, 1);
        Assert.assertEquals(objectList.size(), duplicatedObjectList.size());
        x = 0;
        for (int y = 0; y < 1; y++) {
            for (Object o : objectList) {
                Assert.assertEquals(o, duplicatedObjectList.get(x));
                x++;
            }
        }
        duplicatedObjectList = ListUtility.duplicateListInOrder(objectList, 0);
        Assert.assertEquals(0, duplicatedObjectList.size());
        duplicatedObjectList = ListUtility.duplicateListInOrder(objectList, -1);
        Assert.assertEquals(0, duplicatedObjectList.size());
    }
    
    /**
     * JUnit test of sortListByNumberOfOccurrences.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#sortListByNumberOfOccurrences(List, boolean)
     * @see ListUtility#sortListByNumberOfOccurrences(List)
     */
    @Test
    public void testSortListByNumberOfOccurrences() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.arrayToList(booleanArray);
        List<Boolean> sortedBooleanList = ListUtility.sortListByNumberOfOccurrences(booleanList);
        Assert.assertArrayEquals(new Boolean[] {false, false, false, true, true}, sortedBooleanList.toArray());
        sortedBooleanList = ListUtility.sortListByNumberOfOccurrences(booleanList, false);
        Assert.assertArrayEquals(new Boolean[] {false, false, false, true, true}, sortedBooleanList.toArray());
        sortedBooleanList = ListUtility.sortListByNumberOfOccurrences(booleanList, true);
        Assert.assertArrayEquals(new Boolean[] {true, true, false, false, false}, sortedBooleanList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {1, 3, -5, 8, 4, 1, 1, 1, 3, 3, 1, 1, 4};
        List<Integer> integerList = ListUtility.arrayToList(integerArray);
        List<Integer> sortedIntegerList = ListUtility.sortListByNumberOfOccurrences(integerList);
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8}, sortedIntegerList.toArray());
        sortedIntegerList = ListUtility.sortListByNumberOfOccurrences(integerList, false);
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8}, sortedIntegerList.toArray());
        sortedIntegerList = ListUtility.sortListByNumberOfOccurrences(integerList, true);
        Assert.assertArrayEquals(new Integer[] {-5, 8, 4, 4, 3, 3, 3, 1, 1, 1, 1, 1, 1}, sortedIntegerList.toArray());
        
        //float
        Float[] floatArray = new Float[] {1.1f, 3.9f, -5.0f, 8.44f, 4.7f, 1.1f, 1.1f, 1.1f, 3.8f, 3.8f, 1.1f, 1.1f, 4.7f};
        List<Float> floatList = ListUtility.arrayToList(floatArray);
        List<Float> sortedFloatList = ListUtility.sortListByNumberOfOccurrences(floatList);
        Assert.assertArrayEquals(new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f}, sortedFloatList.toArray());
        sortedFloatList = ListUtility.sortListByNumberOfOccurrences(floatList, false);
        Assert.assertArrayEquals(new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f}, sortedFloatList.toArray());
        sortedFloatList = ListUtility.sortListByNumberOfOccurrences(floatList, true);
        Assert.assertArrayEquals(new Float[] {3.9f, -5.0f, 8.44f, 4.7f, 4.7f, 3.8f, 3.8f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f}, sortedFloatList.toArray());
        
        //float
        Double[] doubleArray = new Double[] {1.1d, 3.9d, -5.0d, 8.44d, 4.7d, 1.1d, 1.1d, 1.1d, 3.8d, 3.8d, 1.1d, 1.1d, 4.7d};
        List<Double> doubleList = ListUtility.arrayToList(doubleArray);
        List<Double> sortedDoubleList = ListUtility.sortListByNumberOfOccurrences(doubleList);
        Assert.assertArrayEquals(new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d}, sortedDoubleList.toArray());
        sortedDoubleList = ListUtility.sortListByNumberOfOccurrences(doubleList, false);
        Assert.assertArrayEquals(new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d}, sortedDoubleList.toArray());
        sortedDoubleList = ListUtility.sortListByNumberOfOccurrences(doubleList, true);
        Assert.assertArrayEquals(new Double[] {3.9d, -5.0d, 8.44d, 4.7d, 4.7d, 3.8d, 3.8d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d}, sortedDoubleList.toArray());
        
        //long
        Long[] longArray = new Long[] {1000L, 3000L, -5000L, 8000L, 4000L, 1000L, 1000L, 1000L, 3000L, 3000L, 1000L, 1000L, 4000L};
        List<Long> longList = ListUtility.arrayToList(longArray);
        List<Long> sortedLongList = ListUtility.sortListByNumberOfOccurrences(longList);
        Assert.assertArrayEquals(new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L}, sortedLongList.toArray());
        sortedLongList = ListUtility.sortListByNumberOfOccurrences(longList, false);
        Assert.assertArrayEquals(new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L}, sortedLongList.toArray());
        sortedLongList = ListUtility.sortListByNumberOfOccurrences(longList, true);
        Assert.assertArrayEquals(new Long[] {-5000L, 8000L, 4000L, 4000L, 3000L, 3000L, 3000L, 1000L, 1000L, 1000L, 1000L, 1000L, 1000L}, sortedLongList.toArray());
        
        //object
        Object a = new Object();
        Object b = "";
        Object c = new ArithmeticException();
        Object d = new HashMap<>();
        Object[] objectArray = new Object[] {a, b, a, a, a, c, d, c, d, c};
        List<Object> objectList = ListUtility.arrayToList(objectArray);
        List<Object> sortedObjectList = ListUtility.sortListByNumberOfOccurrences(objectList);
        Assert.assertArrayEquals(new Object[] {a, a, a, a, c, c, c, d, d, b}, sortedObjectList.toArray());
        sortedObjectList = ListUtility.sortListByNumberOfOccurrences(objectList, false);
        Assert.assertArrayEquals(new Object[] {a, a, a, a, c, c, c, d, d, b}, sortedObjectList.toArray());
        sortedObjectList = ListUtility.sortListByNumberOfOccurrences(objectList, true);
        Assert.assertArrayEquals(new Object[] {b, d, d, c, c, c, a, a, a, a}, sortedObjectList.toArray());
    }
    
}
