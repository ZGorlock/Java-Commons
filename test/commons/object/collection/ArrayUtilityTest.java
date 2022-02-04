/*
 * File:    ArrayUtilityTest.java
 * Package: commons.object.collection
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import commons.test.TestUtils;
import org.json.simple.JSONObject;
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
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
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
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of create.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#create(Class, Object, int)
     * @see ArrayUtility#create(Class, int)
     * @see ArrayUtility#create(Object, int)
     * @see ArrayUtility#create(Class)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate() throws Exception {
        //boolean
        Boolean[] booleanArray = ArrayUtility.create(Boolean.class, 5);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(5, booleanArray.length);
        Assert.assertTrue(Arrays.stream(booleanArray).allMatch(Objects::isNull));
        booleanArray = ArrayUtility.create(true, 5);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(5, booleanArray.length);
        Assert.assertTrue(Arrays.stream(booleanArray).allMatch(e -> (Objects.equals(e, true))));
        booleanArray = ArrayUtility.create(Boolean.class);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(0, booleanArray.length);
        
        //int
        Integer[] integerArray = ArrayUtility.create(Integer.class, 7);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(7, integerArray.length);
        Assert.assertTrue(Arrays.stream(integerArray).allMatch(Objects::isNull));
        integerArray = ArrayUtility.create(18, 7);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(7, integerArray.length);
        Assert.assertTrue(Arrays.stream(integerArray).allMatch(e -> (Objects.equals(e, 18))));
        integerArray = ArrayUtility.create(Integer.class);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(0, integerArray.length);
        
        //float
        Float[] floatArray = ArrayUtility.create(Float.class, 8);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(8, floatArray.length);
        Assert.assertTrue(Arrays.stream(floatArray).allMatch(Objects::isNull));
        floatArray = ArrayUtility.create(6.847f, 8);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(8, floatArray.length);
        Assert.assertTrue(Arrays.stream(floatArray).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatArray = ArrayUtility.create(Float.class);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(0, floatArray.length);
        
        //double
        Double[] doubleArray = ArrayUtility.create(Double.class, 8);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(8, doubleArray.length);
        Assert.assertTrue(Arrays.stream(doubleArray).allMatch(Objects::isNull));
        doubleArray = ArrayUtility.create(117.4984560456d, 8);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(8, doubleArray.length);
        Assert.assertTrue(Arrays.stream(doubleArray).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleArray = ArrayUtility.create(Double.class);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(0, doubleArray.length);
        
        //long
        Long[] longArray = ArrayUtility.create(Long.class, 7);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(7, longArray.length);
        Assert.assertTrue(Arrays.stream(longArray).allMatch(Objects::isNull));
        longArray = ArrayUtility.create(178984654231545L, 7);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(7, longArray.length);
        Assert.assertTrue(Arrays.stream(longArray).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longArray = ArrayUtility.create(Long.class);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(0, longArray.length);
        
        //object
        final Object testObject = new StringBuilder();
        Object[] objectArray = ArrayUtility.create(Object.class, 5);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(Objects::isNull));
        objectArray = ArrayUtility.create(testObject, 5);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(e -> (Objects.equals(e, testObject))));
        objectArray = ArrayUtility.create(Object.class);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(0, objectArray.length);
        
        //invalid
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create(Object.class, -1));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create(18, -1));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.create((Class<?>) null, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.create((Object) null, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.create(null));
    }
    
    /**
     * JUnit test of create2D.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#create2D(Class, Object, int, int)
     * @see ArrayUtility#create2D(Class, int, int)
     * @see ArrayUtility#create2D(Object, int, int)
     * @see ArrayUtility#create2D(Class)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate2D() throws Exception {
        //boolean
        Boolean[][] booleanArray = ArrayUtility.create2D(Boolean.class, 5, 4);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(5, booleanArray.length);
        Assert.assertTrue(Arrays.stream(booleanArray).allMatch(e -> (e.length == 4)));
        Assert.assertTrue(Arrays.stream(booleanArray).flatMap(Arrays::stream).allMatch(Objects::isNull));
        booleanArray = ArrayUtility.create2D(true, 5, 4);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(5, booleanArray.length);
        Assert.assertTrue(Arrays.stream(booleanArray).allMatch(e -> (e.length == 4)));
        Assert.assertTrue(Arrays.stream(booleanArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanArray = ArrayUtility.create2D(Boolean.class);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(0, booleanArray.length);
        
        //int
        Integer[][] integerArray = ArrayUtility.create2D(Integer.class, 7, 6);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(7, integerArray.length);
        Assert.assertTrue(Arrays.stream(integerArray).allMatch(e -> (e.length == 6)));
        Assert.assertTrue(Arrays.stream(integerArray).flatMap(Arrays::stream).allMatch(Objects::isNull));
        integerArray = ArrayUtility.create2D(18, 7, 6);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(7, integerArray.length);
        Assert.assertTrue(Arrays.stream(integerArray).allMatch(e -> (e.length == 6)));
        Assert.assertTrue(Arrays.stream(integerArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerArray = ArrayUtility.create2D(Integer.class);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(0, integerArray.length);
        
        //float
        Float[][] floatArray = ArrayUtility.create2D(Float.class, 8, 7);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(8, floatArray.length);
        Assert.assertTrue(Arrays.stream(floatArray).allMatch(e -> (e.length == 7)));
        Assert.assertTrue(Arrays.stream(floatArray).flatMap(Arrays::stream).allMatch(Objects::isNull));
        floatArray = ArrayUtility.create2D(6.847f, 8, 7);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(8, floatArray.length);
        Assert.assertTrue(Arrays.stream(floatArray).allMatch(e -> (e.length == 7)));
        Assert.assertTrue(Arrays.stream(floatArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatArray = ArrayUtility.create2D(Float.class);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(0, floatArray.length);
        
        //double
        Double[][] doubleArray = ArrayUtility.create2D(Double.class, 8, 7);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(8, doubleArray.length);
        Assert.assertTrue(Arrays.stream(doubleArray).allMatch(e -> (e.length == 7)));
        Assert.assertTrue(Arrays.stream(doubleArray).flatMap(Arrays::stream).allMatch(Objects::isNull));
        doubleArray = ArrayUtility.create2D(117.4984560456d, 8, 7);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(8, doubleArray.length);
        Assert.assertTrue(Arrays.stream(doubleArray).allMatch(e -> (e.length == 7)));
        Assert.assertTrue(Arrays.stream(doubleArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleArray = ArrayUtility.create2D(Double.class);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(0, doubleArray.length);
        
        //long
        Long[][] longArray = ArrayUtility.create2D(Long.class, 7, 6);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(7, longArray.length);
        Assert.assertTrue(Arrays.stream(longArray).allMatch(e -> (e.length == 6)));
        Assert.assertTrue(Arrays.stream(longArray).flatMap(Arrays::stream).allMatch(Objects::isNull));
        longArray = ArrayUtility.create2D(178984654231545L, 7, 6);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(7, longArray.length);
        Assert.assertTrue(Arrays.stream(longArray).allMatch(e -> (e.length == 6)));
        Assert.assertTrue(Arrays.stream(longArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longArray = ArrayUtility.create2D(Long.class);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(0, longArray.length);
        
        //object
        final Object testObject = new StringBuilder();
        Object[][] objectArray = ArrayUtility.create2D(Object.class, 5, 4);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(e -> (e.length == 4)));
        Assert.assertTrue(Arrays.stream(objectArray).flatMap(Arrays::stream).allMatch(Objects::isNull));
        objectArray = ArrayUtility.create2D(testObject, 5, 4);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(e -> (e.length == 4)));
        Assert.assertTrue(Arrays.stream(objectArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectArray = ArrayUtility.create2D(Object.class);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(0, objectArray.length);
        
        //invalid
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create2D(Object.class, -1, 5));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create2D(Object.class, 5, -1));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create2D(18, -1, 5));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create2D(18, 5, -1));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.create2D((Class<?>) null, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.create2D((Object) null, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.create2D(null));
    }
    
    /**
     * JUnit test of create3D.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#create3D(Class, Object, int, int, int)
     * @see ArrayUtility#create3D(Class, int, int, int)
     * @see ArrayUtility#create3D(Object, int, int, int)
     * @see ArrayUtility#create3D(Class)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate3D() throws Exception {
        //boolean
        Boolean[][][] booleanArray = ArrayUtility.create3D(Boolean.class, 5, 4, 3);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(5, booleanArray.length);
        Assert.assertTrue(Arrays.stream(booleanArray).allMatch(e -> (e.length == 4)));
        Arrays.stream(booleanArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 3));
        Assert.assertTrue(Arrays.stream(booleanArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(Objects::isNull));
        booleanArray = ArrayUtility.create3D(true, 5, 4, 3);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(5, booleanArray.length);
        Assert.assertTrue(Arrays.stream(booleanArray).allMatch(e -> (e.length == 4)));
        Arrays.stream(booleanArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 3));
        Assert.assertTrue(Arrays.stream(booleanArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanArray = ArrayUtility.create3D(Boolean.class);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(0, booleanArray.length);
        
        //int
        Integer[][][] integerArray = ArrayUtility.create3D(Integer.class, 7, 6, 5);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(7, integerArray.length);
        Assert.assertTrue(Arrays.stream(integerArray).allMatch(e -> (e.length == 6)));
        Arrays.stream(integerArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 5));
        Assert.assertTrue(Arrays.stream(integerArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(Objects::isNull));
        integerArray = ArrayUtility.create3D(18, 7, 6, 5);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(7, integerArray.length);
        Assert.assertTrue(Arrays.stream(integerArray).allMatch(e -> (e.length == 6)));
        Arrays.stream(integerArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 5));
        Assert.assertTrue(Arrays.stream(integerArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerArray = ArrayUtility.create3D(Integer.class);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(0, integerArray.length);
        
        //float
        Float[][][] floatArray = ArrayUtility.create3D(Float.class, 8, 7, 6);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(8, floatArray.length);
        Assert.assertTrue(Arrays.stream(floatArray).allMatch(e -> (e.length == 7)));
        Arrays.stream(floatArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 6));
        Assert.assertTrue(Arrays.stream(floatArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(Objects::isNull));
        floatArray = ArrayUtility.create3D(6.847f, 8, 7, 6);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(8, floatArray.length);
        Assert.assertTrue(Arrays.stream(floatArray).allMatch(e -> (e.length == 7)));
        Arrays.stream(floatArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 6));
        Assert.assertTrue(Arrays.stream(floatArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatArray = ArrayUtility.create3D(Float.class);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(0, floatArray.length);
        
        //double
        Double[][][] doubleArray = ArrayUtility.create3D(Double.class, 8, 7, 6);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(8, doubleArray.length);
        Assert.assertTrue(Arrays.stream(doubleArray).allMatch(e -> (e.length == 7)));
        Arrays.stream(doubleArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 6));
        Assert.assertTrue(Arrays.stream(doubleArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(Objects::isNull));
        doubleArray = ArrayUtility.create3D(117.4984560456d, 8, 7, 6);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(8, doubleArray.length);
        Assert.assertTrue(Arrays.stream(doubleArray).allMatch(e -> (e.length == 7)));
        Arrays.stream(doubleArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 6));
        Assert.assertTrue(Arrays.stream(doubleArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleArray = ArrayUtility.create3D(Double.class);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(0, doubleArray.length);
        
        //long
        Long[][][] longArray = ArrayUtility.create3D(Long.class, 7, 6, 5);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(7, longArray.length);
        Assert.assertTrue(Arrays.stream(longArray).allMatch(e -> (e.length == 6)));
        Arrays.stream(longArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 5));
        Assert.assertTrue(Arrays.stream(longArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(Objects::isNull));
        longArray = ArrayUtility.create3D(178984654231545L, 7, 6, 5);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(7, longArray.length);
        Assert.assertTrue(Arrays.stream(longArray).allMatch(e -> (e.length == 6)));
        Arrays.stream(longArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 5));
        Assert.assertTrue(Arrays.stream(longArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longArray = ArrayUtility.create3D(Long.class);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(0, longArray.length);
        
        //object
        final Object testObject = new StringBuilder();
        Object[][][] objectArray = ArrayUtility.create3D(Object.class, 5, 4, 3);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(e -> (e.length == 4)));
        Arrays.stream(objectArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 3));
        Assert.assertTrue(Arrays.stream(objectArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(Objects::isNull));
        objectArray = ArrayUtility.create3D(testObject, 7, 6, 5);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(7, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(e -> (e.length == 6)));
        Arrays.stream(objectArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 5));
        Assert.assertTrue(Arrays.stream(objectArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectArray = ArrayUtility.create3D(Object.class);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(0, objectArray.length);
        
        //invalid
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create3D(Object.class, -1, 5, 5));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create3D(Object.class, 5, -1, 5));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create3D(Object.class, 5, 5, -1));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create3D(18, -1, 5, 5));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create3D(18, 5, -1, 5));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create3D(18, 5, 5, -1));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.create3D((Class<?>) null, 6, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.create3D((Object) null, 6, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.create3D(null));
    }
    
    /**
     * JUnit test of arrayOf.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#arrayOf(Class, Object[])
     */
    @Test
    public void testArrayOf() throws Exception {
        //boolean
        Boolean[] booleanArrayTest = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanArray = ArrayUtility.arrayOf(Boolean.class, true, false, false, true, false);
        Assert.assertEquals(booleanArrayTest.length, booleanArray.length);
        Assert.assertArrayEquals(booleanArrayTest, booleanArray);
        
        //integer
        Integer[] integerArrayTest = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerArray = ArrayUtility.arrayOf(Integer.class, 15, 312, 48, 5, -4, -9, 6);
        Assert.assertEquals(integerArrayTest.length, integerArray.length);
        Assert.assertArrayEquals(integerArrayTest, integerArray);
        
        //float
        Float[] floatArrayTest = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatArray = ArrayUtility.arrayOf(Float.class, 15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f);
        Assert.assertEquals(floatArrayTest.length, floatArray.length);
        Assert.assertArrayEquals(floatArrayTest, floatArray);
        
        //double
        Double[] doubleArrayTest = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleArray = ArrayUtility.arrayOf(Double.class, 15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d);
        Assert.assertEquals(doubleArrayTest.length, doubleArray.length);
        Assert.assertArrayEquals(doubleArrayTest, doubleArray);
        
        //long
        Long[] longArrayTest = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longArray = ArrayUtility.arrayOf(Long.class, 15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L);
        Assert.assertEquals(longArrayTest.length, longArray.length);
        Assert.assertArrayEquals(longArrayTest, longArray);
        
        //object
        Object[] objectArrayTest = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArray = ArrayUtility.arrayOf(Object.class, objectArrayTest[0], objectArrayTest[1], objectArrayTest[2], objectArrayTest[3], objectArrayTest[4]);
        Assert.assertEquals(objectArrayTest.length, objectArray.length);
        Assert.assertArrayEquals(objectArrayTest, objectArray);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.arrayOf(Object.class, (Object[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.arrayOf(null, (Object[]) null));
    }
    
    /**
     * JUnit test of toArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#toArray(List, Class)
     */
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
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.toArray(null, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.toArray(objectList, null));
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
        Assert.assertNotSame(booleanArray, booleanClone);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerClone = ArrayUtility.clone(integerArray);
        Assert.assertEquals(integerArray.length, integerClone.length);
        Assert.assertArrayEquals(integerArray, integerClone);
        Assert.assertNotSame(integerArray, integerClone);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatClone = ArrayUtility.clone(floatArray);
        Assert.assertEquals(floatArray.length, floatClone.length);
        Assert.assertArrayEquals(floatArray, floatClone);
        Assert.assertNotSame(floatArray, floatClone);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleClone = ArrayUtility.clone(doubleArray);
        Assert.assertEquals(doubleArray.length, doubleClone.length);
        Assert.assertArrayEquals(doubleArray, doubleClone);
        Assert.assertNotSame(doubleArray, doubleClone);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longClone = ArrayUtility.clone(longArray);
        Assert.assertEquals(longArray.length, longClone.length);
        Assert.assertArrayEquals(longArray, longClone);
        Assert.assertNotSame(longArray, longClone);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectClone = ArrayUtility.clone(objectArray);
        Assert.assertEquals(objectArray.length, objectClone.length);
        Assert.assertArrayEquals(objectArray, objectClone);
        Assert.assertNotSame(objectArray, objectClone);
        
        //invalid
        //noinspection ResultOfMethodCallIgnored
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.clone(null));
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
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [0,6) is out of bounds of the array", () ->
                ArrayUtility.subArray(objectArray, 0, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [-1,5) is out of bounds of the array", () ->
                ArrayUtility.subArray(objectArray, -1, 5));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [4,2) is out of bounds of the array", () ->
                ArrayUtility.subArray(objectArray, 4, 2));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [6,5) is out of bounds of the array", () ->
                ArrayUtility.subArray(objectArray, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [-1,5) is out of bounds of the array", () ->
                ArrayUtility.subArray(objectArray, -1));
        
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.subArray(null, 2, 4));
    }
    
    /**
     * JUnit test of merge.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#merge(Object[], Object[], Class)
     */
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
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.merge(null, objectArray2, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.merge(objectArray, null, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.merge(objectArray, objectArray2, null));
    }
    
    /**
     * JUnit test of split.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#split(Object[], int, Class)
     */
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
        
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.split(null, 3, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.split(objectArray, 3, null));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#reverse(Object[])
     */
    @Test
    public void testReverse() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanReverseArray = ArrayUtility.reverse(booleanArray);
        Assert.assertArrayEquals(new Boolean[] {false, true, false, false, true}, booleanReverseArray);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerReversedArray = ArrayUtility.reverse(integerArray);
        Assert.assertArrayEquals(new Integer[] {6, -9, -4, 5, 48, 312, 15}, integerReversedArray);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatReversedArray = ArrayUtility.reverse(floatArray);
        Assert.assertArrayEquals(new Float[] {19776.4f, 6.99f, -9.7f, -4.006f, 5.45f, 48.0f, 312.91f, 15.1f}, floatReversedArray);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleReversedArray = ArrayUtility.reverse(doubleArray);
        Assert.assertArrayEquals(new Double[] {6.99546101d, -9.70487745d, -4.006005001d, 5.457894511d, 48.00000015d, 312.9113874d, 15.104564d}, doubleReversedArray);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longReversedArray = ArrayUtility.reverse(longArray);
        Assert.assertArrayEquals(new Long[] {699546101L, -970487745L, -4006005001L, 5457894511L, 4800000015L, 3129113874L, 15104564L}, longReversedArray);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectReversedArray = ArrayUtility.reverse(objectArray);
        Assert.assertArrayEquals(new Object[] {objectArray[4], objectArray[3], objectArray[2], 54, ""}, objectReversedArray);
        
        //invalid
        
        Object[] emptyReversedArray = ArrayUtility.reverse(new Object[] {});
        Assert.assertArrayEquals(new Object[] {}, emptyReversedArray);
        
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.reverse(null));
    }
    
    /**
     * JUnit test of isNullOrEmpty.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#isNullOrEmpty(Object[])
     */
    @Test
    public void testIsNullOrEmpty() throws Exception {
        //standard
        Assert.assertFalse(ArrayUtility.isNullOrEmpty(new Object[] {"test"}));
        
        //empty
        Assert.assertTrue(ArrayUtility.isNullOrEmpty(new Object[] {}));
        
        //null
        Assert.assertTrue(ArrayUtility.isNullOrEmpty(null));
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#equals(Object[], Object[], boolean)
     * @see ArrayUtility#equals(Object[], Object[])
     */
    @Test
    public void testEquals() throws Exception {
        //boolean
        Boolean[] booleanArray1 = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanArray2 = new Boolean[] {true, false, false, true, false};
        Assert.assertTrue(ArrayUtility.equals(booleanArray1, booleanArray2));
        Assert.assertTrue(ArrayUtility.equals(booleanArray1, booleanArray2, true));
        Assert.assertTrue(ArrayUtility.equals(booleanArray1, booleanArray2, false));
        Assert.assertTrue(ArrayUtility.equals(booleanArray2, booleanArray1));
        Assert.assertTrue(ArrayUtility.equals(booleanArray2, booleanArray1, true));
        Assert.assertTrue(ArrayUtility.equals(booleanArray2, booleanArray1, false));
        booleanArray1 = new Boolean[] {true, false, false, true, false};
        booleanArray2 = new Boolean[] {true, false};
        Assert.assertFalse(ArrayUtility.equals(booleanArray1, booleanArray2));
        Assert.assertFalse(ArrayUtility.equals(booleanArray1, booleanArray2, true));
        Assert.assertFalse(ArrayUtility.equals(booleanArray1, booleanArray2, false));
        Assert.assertFalse(ArrayUtility.equals(booleanArray2, booleanArray1));
        Assert.assertFalse(ArrayUtility.equals(booleanArray2, booleanArray1, true));
        Assert.assertFalse(ArrayUtility.equals(booleanArray2, booleanArray1, false));
        booleanArray1 = new Boolean[] {true, false, false, true, false};
        booleanArray2 = new Boolean[] {true, false, false, false, false};
        Assert.assertFalse(ArrayUtility.equals(booleanArray1, booleanArray2));
        Assert.assertFalse(ArrayUtility.equals(booleanArray1, booleanArray2, true));
        Assert.assertFalse(ArrayUtility.equals(booleanArray1, booleanArray2, false));
        Assert.assertFalse(ArrayUtility.equals(booleanArray2, booleanArray1));
        Assert.assertFalse(ArrayUtility.equals(booleanArray2, booleanArray1, true));
        Assert.assertFalse(ArrayUtility.equals(booleanArray2, booleanArray1, false));
        
        //int
        Integer[] integerArray1 = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerArray2 = new Integer[] {312, 48, 5, -4, -9, 6, 15};
        Assert.assertFalse(ArrayUtility.equals(integerArray1, integerArray2));
        Assert.assertFalse(ArrayUtility.equals(integerArray1, integerArray2, true));
        Assert.assertTrue(ArrayUtility.equals(integerArray1, integerArray2, false));
        Assert.assertFalse(ArrayUtility.equals(integerArray2, integerArray1));
        Assert.assertFalse(ArrayUtility.equals(integerArray2, integerArray1, true));
        Assert.assertTrue(ArrayUtility.equals(integerArray2, integerArray1, false));
        
        //float
        Float[] floatArray1 = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatArray2 = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f};
        Assert.assertFalse(ArrayUtility.equals(floatArray1, floatArray2));
        Assert.assertFalse(ArrayUtility.equals(floatArray1, floatArray2, true));
        Assert.assertFalse(ArrayUtility.equals(floatArray1, floatArray2, false));
        Assert.assertFalse(ArrayUtility.equals(floatArray2, floatArray1));
        Assert.assertFalse(ArrayUtility.equals(floatArray2, floatArray1, true));
        Assert.assertFalse(ArrayUtility.equals(floatArray2, floatArray1, false));
        
        //double
        Double[] doubleArray1 = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleArray2 = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Assert.assertTrue(ArrayUtility.equals(doubleArray1, doubleArray2));
        Assert.assertTrue(ArrayUtility.equals(doubleArray1, doubleArray2, true));
        Assert.assertTrue(ArrayUtility.equals(doubleArray1, doubleArray2, false));
        Assert.assertTrue(ArrayUtility.equals(doubleArray2, doubleArray1));
        Assert.assertTrue(ArrayUtility.equals(doubleArray2, doubleArray1, true));
        Assert.assertTrue(ArrayUtility.equals(doubleArray2, doubleArray1, false));
        
        //long
        Long[] longArray1 = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longArray2 = new Long[] {4800000015L, 15104564L, 3129113874L, 699546101L, 5457894511L, -4006005001L, -970487745L};
        Assert.assertFalse(ArrayUtility.equals(longArray1, longArray2));
        Assert.assertFalse(ArrayUtility.equals(longArray1, longArray2, true));
        Assert.assertTrue(ArrayUtility.equals(longArray1, longArray2, false));
        Assert.assertFalse(ArrayUtility.equals(longArray2, longArray1));
        Assert.assertFalse(ArrayUtility.equals(longArray2, longArray1, true));
        Assert.assertTrue(ArrayUtility.equals(longArray2, longArray1, false));
        
        //string
        String[] stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        String[] stringArray2 = new String[] {"Cat", "Dog", "Bird", "Lizard", "Fish"};
        Assert.assertFalse(ArrayUtility.equals(stringArray1, stringArray2));
        Assert.assertFalse(ArrayUtility.equals(stringArray1, stringArray2, true));
        Assert.assertFalse(ArrayUtility.equals(stringArray1, stringArray2, false));
        Assert.assertFalse(ArrayUtility.equals(stringArray2, stringArray1));
        Assert.assertFalse(ArrayUtility.equals(stringArray2, stringArray1, true));
        Assert.assertFalse(ArrayUtility.equals(stringArray2, stringArray1, false));
        
        //object
        Object[] objectArray1 = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArray2 = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Assert.assertFalse(ArrayUtility.equals(objectArray1, objectArray2));
        Assert.assertFalse(ArrayUtility.equals(objectArray1, objectArray2, true));
        Assert.assertFalse(ArrayUtility.equals(objectArray1, objectArray2, false));
        Assert.assertFalse(ArrayUtility.equals(objectArray2, objectArray1));
        Assert.assertFalse(ArrayUtility.equals(objectArray2, objectArray1, true));
        Assert.assertFalse(ArrayUtility.equals(objectArray2, objectArray1, false));
        
        //invalid
        Assert.assertTrue(ArrayUtility.equals(new Object[] {}, new Object[] {}));
        Assert.assertTrue(ArrayUtility.equals(new Object[] {}, new Object[] {}, true));
        Assert.assertTrue(ArrayUtility.equals(new Object[] {}, new Object[] {}, false));
        Assert.assertFalse(ArrayUtility.equals(objectArray1, null));
        Assert.assertFalse(ArrayUtility.equals(objectArray1, null, true));
        Assert.assertFalse(ArrayUtility.equals(objectArray1, null, false));
        Assert.assertFalse(ArrayUtility.equals(null, objectArray2));
        Assert.assertFalse(ArrayUtility.equals(null, objectArray2, true));
        Assert.assertFalse(ArrayUtility.equals(null, objectArray2, false));
        Assert.assertTrue(ArrayUtility.equals(null, null));
        Assert.assertTrue(ArrayUtility.equals(null, null, true));
        Assert.assertTrue(ArrayUtility.equals(null, null, false));
    }
    
    /**
     * JUnit test of equalsIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#equalsIgnoreCase(String[], String[], boolean)
     * @see ArrayUtility#equalsIgnoreCase(String[], String[])
     */
    @Test
    public void testEqualsIgnoreCase() throws Exception {
        //standard
        String[] stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        String[] stringArray2 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, false));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, false));
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"lizard", "dog", "fish", "cat", "bird"};
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, false));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, false));
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"dog", "bird", "lizard"};
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, true));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, false));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, true));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, false));
        
        //case
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"Cat", "DOG", "biRd", "LizARd", "FISh"};
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, false));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, false));
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"LizARd", "DOG", "FISh", "Cat", "biRd"};
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, false));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, false));
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"DOG", "biRd", "LizARd"};
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, true));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, stringArray2, false));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, true));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray2, stringArray1, false));
        
        //invalid
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(new String[] {}, new String[] {}));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(new String[] {}, new String[] {}, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(new String[] {}, new String[] {}, false));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, null));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, null, true));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(stringArray1, null, false));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(null, stringArray2));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(null, stringArray2, true));
        Assert.assertFalse(ArrayUtility.equalsIgnoreCase(null, stringArray2, false));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(null, null));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(null, null, true));
        Assert.assertTrue(ArrayUtility.equalsIgnoreCase(null, null, false));
    }
    
    /**
     * JUnit test of contains.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#contains(Object[], Object)
     */
    @Test
    public void testContains() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Assert.assertTrue(ArrayUtility.contains(booleanArray, true));
        Assert.assertTrue(ArrayUtility.contains(booleanArray, false));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Assert.assertTrue(ArrayUtility.contains(integerArray, 5));
        Assert.assertTrue(ArrayUtility.contains(integerArray, -9));
        Assert.assertFalse(ArrayUtility.contains(integerArray, 10));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Assert.assertTrue(ArrayUtility.contains(floatArray, 312.91f));
        Assert.assertTrue(ArrayUtility.contains(floatArray, -9.7f));
        Assert.assertFalse(ArrayUtility.contains(floatArray, 123.8f));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Assert.assertTrue(ArrayUtility.contains(doubleArray, 15.104564d));
        Assert.assertTrue(ArrayUtility.contains(doubleArray, -4.006005001d));
        Assert.assertFalse(ArrayUtility.contains(doubleArray, 8.6451001211d));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Assert.assertTrue(ArrayUtility.contains(longArray, 3129113874L));
        Assert.assertTrue(ArrayUtility.contains(longArray, 699546101L));
        Assert.assertFalse(ArrayUtility.contains(longArray, 8465115960L));
        
        //string
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        Assert.assertTrue(ArrayUtility.contains(stringArray, "cat"));
        Assert.assertTrue(ArrayUtility.contains(stringArray, "lizard"));
        Assert.assertFalse(ArrayUtility.contains(stringArray, "DOG"));
        Assert.assertFalse(ArrayUtility.contains(stringArray, "rat"));
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArrayWithNull = new Object[] {"", 54, new ArithmeticException(), null};
        Assert.assertTrue(ArrayUtility.contains(objectArray, objectArray[2]));
        Assert.assertTrue(ArrayUtility.contains(objectArray, objectArray[4]));
        Assert.assertFalse(ArrayUtility.contains(objectArray, new ArrayList<>()));
        Assert.assertFalse(ArrayUtility.contains(objectArray, null));
        Assert.assertTrue(ArrayUtility.contains(objectArrayWithNull, null));
        
        //invalid
        Assert.assertFalse(ArrayUtility.contains(null, new Object()));
        Assert.assertFalse(ArrayUtility.contains(null, null));
    }
    
    /**
     * JUnit test of containsIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#containsIgnoreCase(String[], String)
     */
    @Test
    public void testContainsIgnoreCase() throws Exception {
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        String[] stringArrayWithNull = new String[] {"cat", null, "bird"};
        
        //standard
        Assert.assertTrue(ArrayUtility.containsIgnoreCase(stringArray, "cat"));
        Assert.assertTrue(ArrayUtility.containsIgnoreCase(stringArray, "lizard"));
        Assert.assertTrue(ArrayUtility.containsIgnoreCase(stringArray, "dog"));
        Assert.assertFalse(ArrayUtility.containsIgnoreCase(stringArray, "rat"));
        
        //case
        Assert.assertTrue(ArrayUtility.containsIgnoreCase(stringArray, "CAT"));
        Assert.assertTrue(ArrayUtility.containsIgnoreCase(stringArray, "LIzArD"));
        Assert.assertTrue(ArrayUtility.containsIgnoreCase(stringArray, "doG"));
        Assert.assertFalse(ArrayUtility.containsIgnoreCase(stringArray, "rAt"));
        
        //null
        Assert.assertTrue(ArrayUtility.containsIgnoreCase(stringArrayWithNull, "birD"));
        Assert.assertTrue(ArrayUtility.containsIgnoreCase(stringArrayWithNull, null));
        
        //invalid
        Assert.assertFalse(ArrayUtility.containsIgnoreCase(null, ""));
        Assert.assertFalse(ArrayUtility.containsIgnoreCase(null, null));
    }
    
    /**
     * JUnit test of numberOfOccurrences.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#numberOfOccurrences(Object[], Object)
     */
    @Test
    public void testNumberOfOccurrences() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Assert.assertEquals(2, ArrayUtility.numberOfOccurrences(booleanArray, true));
        Assert.assertEquals(3, ArrayUtility.numberOfOccurrences(booleanArray, false));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -4, -4, -4, -9, 6};
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrences(integerArray, 15));
        Assert.assertEquals(4, ArrayUtility.numberOfOccurrences(integerArray, -4));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrences(floatArray, 312.91f));
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrences(floatArray, 6.9999f));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrences(doubleArray, 312.9113874d));
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrences(doubleArray, 6.99d));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrences(longArray, 699546101L));
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrences(longArray, 0L));
        
        //string
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrences(stringArray, "cat"));
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrences(stringArray, "CAT"));
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArrayWithNull = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object(), null, null};
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrences(objectArray, ""));
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrences(objectArray, 54));
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrences(objectArray, new ArithmeticException()));
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrences(objectArray, new HashMap<>()));
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrences(objectArray, new Object()));
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrences(objectArray, null));
        Assert.assertEquals(2, ArrayUtility.numberOfOccurrences(objectArrayWithNull, null));
        
        //invalid
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrences(null, null));
    }
    
    /**
     * JUnit test of numberOfOccurrencesIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#numberOfOccurrencesIgnoreCase(String[], String)
     */
    @Test
    public void testNumberOfOccurrencesIgnoreCase() throws Exception {
        String[] stringArray = new String[] {"cat", "dog", "DOG", "bird", "lizard", "fish", "Fish", "fISh"};
        String[] stringArrayWithNull = new String[] {"cat", null, "bird"};
        
        //standard
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrencesIgnoreCase(stringArray, "cat"));
        Assert.assertEquals(2, ArrayUtility.numberOfOccurrencesIgnoreCase(stringArray, "dog"));
        Assert.assertEquals(3, ArrayUtility.numberOfOccurrencesIgnoreCase(stringArray, "fish"));
        
        //case
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrencesIgnoreCase(stringArray, "CAT"));
        Assert.assertEquals(2, ArrayUtility.numberOfOccurrencesIgnoreCase(stringArray, "dOg"));
        Assert.assertEquals(3, ArrayUtility.numberOfOccurrencesIgnoreCase(stringArray, "fISH"));
        
        //null
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrencesIgnoreCase(stringArrayWithNull, "LizARD"));
        Assert.assertEquals(1, ArrayUtility.numberOfOccurrencesIgnoreCase(stringArrayWithNull, null));
        
        //invalid
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrencesIgnoreCase(null, ""));
        Assert.assertEquals(0, ArrayUtility.numberOfOccurrencesIgnoreCase(null, null));
    }
    
    /**
     * JUnit test of indexOf.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#indexOf(Object[], Object)
     */
    @Test
    public void testIndexOf() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Assert.assertEquals(0, ArrayUtility.indexOf(booleanArray, true));
        Assert.assertEquals(1, ArrayUtility.indexOf(booleanArray, false));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Assert.assertEquals(3, ArrayUtility.indexOf(integerArray, 5));
        Assert.assertEquals(5, ArrayUtility.indexOf(integerArray, -9));
        Assert.assertEquals(-1, ArrayUtility.indexOf(integerArray, 10));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Assert.assertEquals(1, ArrayUtility.indexOf(floatArray, 312.91f));
        Assert.assertEquals(5, ArrayUtility.indexOf(floatArray, -9.7f));
        Assert.assertEquals(-1, ArrayUtility.indexOf(floatArray, 123.8f));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Assert.assertEquals(0, ArrayUtility.indexOf(doubleArray, 15.104564d));
        Assert.assertEquals(4, ArrayUtility.indexOf(doubleArray, -4.006005001d));
        Assert.assertEquals(-1, ArrayUtility.indexOf(doubleArray, 8.6451001211d));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Assert.assertEquals(1, ArrayUtility.indexOf(longArray, 3129113874L));
        Assert.assertEquals(6, ArrayUtility.indexOf(longArray, 699546101L));
        Assert.assertEquals(-1, ArrayUtility.indexOf(longArray, 8465115960L));
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Assert.assertEquals(2, ArrayUtility.indexOf(objectArray, objectArray[2]));
        Assert.assertEquals(4, ArrayUtility.indexOf(objectArray, objectArray[4]));
        Assert.assertEquals(-1, ArrayUtility.indexOf(objectArray, new ArrayList<>()));
        
        //invalid
        Assert.assertEquals(-1, ArrayUtility.indexOf(null, new Object()));
        Assert.assertEquals(-1, ArrayUtility.indexOf(null, null));
    }
    
    /**
     * JUnit test of indexOfIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#indexOfIgnoreCase(String[], String)
     */
    @Test
    public void testIndexOfIgnoreCase() throws Exception {
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        String[] stringArrayWithNull = new String[] {"cat", null, "bird"};
        
        //standard
        Assert.assertEquals(0, ArrayUtility.indexOfIgnoreCase(stringArray, "cat"));
        Assert.assertEquals(3, ArrayUtility.indexOfIgnoreCase(stringArray, "lizard"));
        Assert.assertEquals(1, ArrayUtility.indexOfIgnoreCase(stringArray, "dog"));
        Assert.assertEquals(-1, ArrayUtility.indexOfIgnoreCase(stringArray, "rat"));
        
        //case
        Assert.assertEquals(0, ArrayUtility.indexOfIgnoreCase(stringArray, "CAT"));
        Assert.assertEquals(3, ArrayUtility.indexOfIgnoreCase(stringArray, "LIzArD"));
        Assert.assertEquals(1, ArrayUtility.indexOfIgnoreCase(stringArray, "doG"));
        Assert.assertEquals(-1, ArrayUtility.indexOfIgnoreCase(stringArray, "rAt"));
        
        //null
        Assert.assertEquals(2, ArrayUtility.indexOfIgnoreCase(stringArrayWithNull, "birD"));
        Assert.assertEquals(1, ArrayUtility.indexOfIgnoreCase(stringArrayWithNull, null));
        
        //invalid
        Assert.assertEquals(-1, ArrayUtility.indexOfIgnoreCase(null, ""));
        Assert.assertEquals(-1, ArrayUtility.indexOfIgnoreCase(null, null));
    }
    
    /**
     * JUnit test of getOrDefault.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#getOrDefault(Object[], int, Object)
     */
    @Test
    public void testGetOrDefault() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Assert.assertEquals(false, ArrayUtility.getOrDefault(booleanArray, 1, true));
        Assert.assertEquals(true, ArrayUtility.getOrDefault(booleanArray, 3, false));
        Assert.assertEquals(true, ArrayUtility.getOrDefault(booleanArray, 12, true));
        Assert.assertEquals(true, ArrayUtility.getOrDefault(booleanArray, -1, true));
        Assert.assertEquals(true, ArrayUtility.getOrDefault(booleanArray, 3, null));
        Assert.assertNull(ArrayUtility.getOrDefault(booleanArray, 12, null));
        Assert.assertEquals(true, ArrayUtility.getOrDefault(null, 2, true));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Assert.assertEquals(312, ArrayUtility.getOrDefault(integerArray, 1, 100).intValue());
        Assert.assertEquals(5, ArrayUtility.getOrDefault(integerArray, 3, 100).intValue());
        Assert.assertEquals(100, ArrayUtility.getOrDefault(integerArray, 12, 100).intValue());
        Assert.assertEquals(100, ArrayUtility.getOrDefault(integerArray, -1, 100).intValue());
        Assert.assertEquals(5, ArrayUtility.getOrDefault(integerArray, 3, null).intValue());
        Assert.assertNull(ArrayUtility.getOrDefault(integerArray, 12, null));
        Assert.assertEquals(100, ArrayUtility.getOrDefault(null, 2, 100).intValue());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Assert.assertEquals(312.91f, ArrayUtility.getOrDefault(floatArray, 1, 100.0f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(5.45f, ArrayUtility.getOrDefault(floatArray, 3, 100.0f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(100.0f, ArrayUtility.getOrDefault(floatArray, 12, 100.0f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(100.0f, ArrayUtility.getOrDefault(floatArray, -1, 100.0f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(5.45f, ArrayUtility.getOrDefault(floatArray, 3, null), TestUtils.DELTA_FLOAT);
        Assert.assertNull(ArrayUtility.getOrDefault(floatArray, 12, null));
        Assert.assertEquals(100.0f, ArrayUtility.getOrDefault(null, 2, 100.0f), TestUtils.DELTA_FLOAT);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Assert.assertEquals(312.9113874d, ArrayUtility.getOrDefault(doubleArray, 1, 100.0d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(5.457894511d, ArrayUtility.getOrDefault(doubleArray, 3, 100.0d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(100.0d, ArrayUtility.getOrDefault(doubleArray, 12, 100.0d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(100.0d, ArrayUtility.getOrDefault(doubleArray, -1, 100.0d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(5.457894511d, ArrayUtility.getOrDefault(doubleArray, 3, null), TestUtils.DELTA_DOUBLE);
        Assert.assertNull(ArrayUtility.getOrDefault(doubleArray, 12, null));
        Assert.assertEquals(100.0d, ArrayUtility.getOrDefault(null, 2, 100.0d), TestUtils.DELTA_DOUBLE);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Assert.assertEquals(3129113874L, ArrayUtility.getOrDefault(longArray, 1, 100L).longValue());
        Assert.assertEquals(5457894511L, ArrayUtility.getOrDefault(longArray, 3, 100L).longValue());
        Assert.assertEquals(100L, ArrayUtility.getOrDefault(longArray, 12, 100L).longValue());
        Assert.assertEquals(100L, ArrayUtility.getOrDefault(longArray, -1, 100L).longValue());
        Assert.assertEquals(5457894511L, ArrayUtility.getOrDefault(longArray, 3, null).longValue());
        Assert.assertNull(ArrayUtility.getOrDefault(longArray, 12, null));
        Assert.assertEquals(100L, ArrayUtility.getOrDefault(null, 2, 100L).longValue());
        
        //object
        final Object testObject = new HashMap<>();
        final Object testObject2 = new JSONObject();
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), testObject, new Object()};
        Assert.assertEquals(54, ArrayUtility.getOrDefault(objectArray, 1, testObject2));
        Assert.assertEquals(testObject, ArrayUtility.getOrDefault(objectArray, 3, testObject2));
        Assert.assertEquals(testObject2, ArrayUtility.getOrDefault(objectArray, 12, testObject2));
        Assert.assertEquals(testObject2, ArrayUtility.getOrDefault(objectArray, -1, testObject2));
        Assert.assertEquals(testObject, ArrayUtility.getOrDefault(objectArray, 3, null));
        Assert.assertNull(ArrayUtility.getOrDefault(objectArray, 12, null));
        Assert.assertEquals(testObject2, ArrayUtility.getOrDefault(null, 2, testObject2));
    }
    
    /**
     * JUnit test of getOrNull.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#getOrNull(Object[], int)
     */
    @Test
    public void testGetOrNull() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Assert.assertEquals(false, ArrayUtility.getOrNull(booleanArray, 1));
        Assert.assertEquals(true, ArrayUtility.getOrNull(booleanArray, 3));
        Assert.assertNull(ArrayUtility.getOrNull(booleanArray, 12));
        Assert.assertNull(ArrayUtility.getOrNull(booleanArray, -1));
        Assert.assertNull(ArrayUtility.getOrNull(null, 2));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Assert.assertEquals(312, ArrayUtility.getOrNull(integerArray, 1).intValue());
        Assert.assertEquals(5, ArrayUtility.getOrNull(integerArray, 3).intValue());
        Assert.assertNull(ArrayUtility.getOrNull(integerArray, 12));
        Assert.assertNull(ArrayUtility.getOrNull(integerArray, -1));
        Assert.assertNull(ArrayUtility.getOrNull(null, 2));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Assert.assertEquals(312.91f, ArrayUtility.getOrNull(floatArray, 1), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(5.45f, ArrayUtility.getOrNull(floatArray, 3), TestUtils.DELTA_FLOAT);
        Assert.assertNull(ArrayUtility.getOrNull(floatArray, 12));
        Assert.assertNull(ArrayUtility.getOrNull(floatArray, -1));
        Assert.assertNull(ArrayUtility.getOrNull(null, 2));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Assert.assertEquals(312.9113874d, ArrayUtility.getOrNull(doubleArray, 1), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(5.457894511d, ArrayUtility.getOrNull(doubleArray, 3), TestUtils.DELTA_DOUBLE);
        Assert.assertNull(ArrayUtility.getOrNull(doubleArray, 12));
        Assert.assertNull(ArrayUtility.getOrNull(doubleArray, -1));
        Assert.assertNull(ArrayUtility.getOrNull(null, 2));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Assert.assertEquals(3129113874L, ArrayUtility.getOrNull(longArray, 1).longValue());
        Assert.assertEquals(5457894511L, ArrayUtility.getOrNull(longArray, 3).longValue());
        Assert.assertNull(ArrayUtility.getOrNull(longArray, 12));
        Assert.assertNull(ArrayUtility.getOrNull(longArray, -1));
        Assert.assertNull(ArrayUtility.getOrNull(null, 2));
        
        //object
        final Object testObject = new HashMap<>();
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), testObject, new Object()};
        Assert.assertEquals(54, ArrayUtility.getOrNull(objectArray, 1));
        Assert.assertEquals(testObject, ArrayUtility.getOrNull(objectArray, 3));
        Assert.assertNull(ArrayUtility.getOrNull(objectArray, 12));
        Assert.assertNull(ArrayUtility.getOrNull(objectArray, -1));
        Assert.assertNull(ArrayUtility.getOrNull(null, 2));
    }
    
    /**
     * JUnit test of anyNull.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#anyNull(Object[])
     */
    @SuppressWarnings("WrapperTypeMayBePrimitive")
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
        //noinspection ResultOfMethodCallIgnored
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.anyNull(null));
    }
    
    /**
     * JUnit test of removeNull.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#removeNull(Object[])
     */
    @SuppressWarnings("WrapperTypeMayBePrimitive")
    @Test
    public void testRemoveNull() throws Exception {
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
        Assert.assertArrayEquals(new Object[] {a, c, e, g, i, k, m}, ArrayUtility.removeNull(array));
        
        //all not null
        array = new Object[] {a, c, e, g, i, k, m};
        Assert.assertArrayEquals(new Object[] {a, c, e, g, i, k, m}, ArrayUtility.removeNull(array));
        
        //all null
        array = new Object[] {b, d, f, h, j, l, n};
        Assert.assertArrayEquals(new Object[] {}, ArrayUtility.removeNull(array));
        
        //none
        array = new Object[] {};
        Assert.assertArrayEquals(new Object[] {}, ArrayUtility.removeNull(array));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.removeNull(null));
    }
    
    /**
     * JUnit test of removeDuplicates.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#removeDuplicates(Object[], Class)
     */
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
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.removeDuplicates(null, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.removeDuplicates(objectArray, null));
    }
    
    /**
     * JUnit test of selectRandom.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#selectRandom(Object[])
     */
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
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.selectRandom(null));
    }
    
    /**
     * JUnit test of selectN.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#selectN(Object[], int, Class)
     */
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
        
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.selectN(null, 4, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.selectN(objectArray, 4, null));
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
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.duplicateInOrder(null, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.duplicateInOrder(objectArray, null));
        
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.duplicateInOrder(null, 4, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.duplicateInOrder(objectArray, 4, null));
    }
    
    /**
     * JUnit test of sortByNumberOfOccurrences.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#sortByNumberOfOccurrences(Object[], boolean, Class)
     * @see ArrayUtility#sortByNumberOfOccurrences(Object[], Class)
     */
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
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.sortByNumberOfOccurrences(null, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.sortByNumberOfOccurrences(objectArray, null));
        
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.sortByNumberOfOccurrences(null, true, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.sortByNumberOfOccurrences(objectArray, true, null));
    }
    
}
