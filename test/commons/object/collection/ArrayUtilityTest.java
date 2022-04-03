/*
 * File:    ArrayUtilityTest.java
 * Package: commons.object.collection
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import commons.test.TestAccess;
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
     * JUnit test of emptyArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#emptyArray(Class)
     * @see ArrayUtility#emptyArray()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testEmptyArray() throws Exception {
        Object[] array;
        
        //standard
        array = ArrayUtility.emptyArray(String.class);
        Assert.assertNotNull(array);
        Assert.assertEquals(0, array.length);
        array = ArrayUtility.emptyArray(Integer.class);
        Assert.assertNotNull(array);
        Assert.assertEquals(0, array.length);
        array = ArrayUtility.emptyArray();
        Assert.assertNotNull(array);
        Assert.assertEquals(0, array.length);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.emptyArray(null));
    }
    
    /**
     * JUnit test of create.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#create(Class, Object, int)
     * @see ArrayUtility#create(Class, int)
     * @see ArrayUtility#create(Object, int)
     * @see ArrayUtility#create(Class)
     * @see ArrayUtility#create(int)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate() throws Exception {
        //boolean
        Boolean[] booleanArray = TestAccess.invokeMethod(ArrayUtility.class, Boolean[].class, "create", Boolean.class, true, 5);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(5, booleanArray.length);
        Assert.assertTrue(Arrays.stream(booleanArray).allMatch(e -> (Objects.equals(e, true))));
        booleanArray = ArrayUtility.create(Boolean.class, 5);
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
        Integer[] integerArray = TestAccess.invokeMethod(ArrayUtility.class, Integer[].class, "create", Integer.class, 18, 7);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(7, integerArray.length);
        Assert.assertTrue(Arrays.stream(integerArray).allMatch(e -> (Objects.equals(e, 18))));
        integerArray = ArrayUtility.create(Integer.class, 7);
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
        Float[] floatArray = TestAccess.invokeMethod(ArrayUtility.class, Float[].class, "create", Float.class, 6.847f, 8);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(8, floatArray.length);
        Assert.assertTrue(Arrays.stream(floatArray).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatArray = ArrayUtility.create(Float.class, 8);
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
        Double[] doubleArray = TestAccess.invokeMethod(ArrayUtility.class, Double[].class, "create", Double.class, 117.4984560456d, 8);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(8, doubleArray.length);
        Assert.assertTrue(Arrays.stream(doubleArray).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleArray = ArrayUtility.create(Double.class, 8);
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
        Long[] longArray = TestAccess.invokeMethod(ArrayUtility.class, Long[].class, "create", Long.class, 178984654231545L, 7);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(7, longArray.length);
        Assert.assertTrue(Arrays.stream(longArray).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longArray = ArrayUtility.create(Long.class, 7);
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
        Object[] objectArray = TestAccess.invokeMethod(ArrayUtility.class, Object[].class, "create", Object.class, testObject, 5);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(e -> (Objects.equals(e, testObject))));
        objectArray = ArrayUtility.create(Object.class, 5);
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
        objectArray = ArrayUtility.create(5);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(Objects::isNull));
        
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
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create(-1));
    }
    
    /**
     * JUnit test of create2D.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#create2D(Class, Object, int, int)
     * @see ArrayUtility#create2D(Class, int, int)
     * @see ArrayUtility#create2D(Object, int, int)
     * @see ArrayUtility#create2D(Class)
     * @see ArrayUtility#create2D(int, int)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate2D() throws Exception {
        //boolean
        Boolean[][] booleanArray = TestAccess.invokeMethod(ArrayUtility.class, Boolean[][].class, "create2D", Boolean.class, true, 5, 4);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(5, booleanArray.length);
        Assert.assertTrue(Arrays.stream(booleanArray).allMatch(e -> (e.length == 4)));
        Assert.assertTrue(Arrays.stream(booleanArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanArray = ArrayUtility.create2D(Boolean.class, 5, 4);
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
        Integer[][] integerArray = TestAccess.invokeMethod(ArrayUtility.class, Integer[][].class, "create2D", Integer.class, 18, 7, 6);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(7, integerArray.length);
        Assert.assertTrue(Arrays.stream(integerArray).allMatch(e -> (e.length == 6)));
        Assert.assertTrue(Arrays.stream(integerArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerArray = ArrayUtility.create2D(Integer.class, 7, 6);
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
        Float[][] floatArray = TestAccess.invokeMethod(ArrayUtility.class, Float[][].class, "create2D", Float.class, 6.847f, 8, 7);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(8, floatArray.length);
        Assert.assertTrue(Arrays.stream(floatArray).allMatch(e -> (e.length == 7)));
        Assert.assertTrue(Arrays.stream(floatArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatArray = ArrayUtility.create2D(Float.class, 8, 7);
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
        Double[][] doubleArray = TestAccess.invokeMethod(ArrayUtility.class, Double[][].class, "create2D", Double.class, 117.4984560456d, 8, 7);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(8, doubleArray.length);
        Assert.assertTrue(Arrays.stream(doubleArray).allMatch(e -> (e.length == 7)));
        Assert.assertTrue(Arrays.stream(doubleArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleArray = ArrayUtility.create2D(Double.class, 8, 7);
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
        Long[][] longArray = TestAccess.invokeMethod(ArrayUtility.class, Long[][].class, "create2D", Long.class, 178984654231545L, 7, 6);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(7, longArray.length);
        Assert.assertTrue(Arrays.stream(longArray).allMatch(e -> (e.length == 6)));
        Assert.assertTrue(Arrays.stream(longArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longArray = ArrayUtility.create2D(Long.class, 7, 6);
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
        Object[][] objectArray = TestAccess.invokeMethod(ArrayUtility.class, Object[][].class, "create2D", Object.class, testObject, 5, 4);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(e -> (e.length == 4)));
        Assert.assertTrue(Arrays.stream(objectArray).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectArray = ArrayUtility.create2D(Object.class, 5, 4);
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
        objectArray = ArrayUtility.create2D(5, 4);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(e -> (e.length == 4)));
        Assert.assertTrue(Arrays.stream(objectArray).flatMap(Arrays::stream).allMatch(Objects::isNull));
        
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
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create2D(-1, 5));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create2D(5, -1));
    }
    
    /**
     * JUnit test of create3D.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#create3D(Class, Object, int, int, int)
     * @see ArrayUtility#create3D(Class, int, int, int)
     * @see ArrayUtility#create3D(Object, int, int, int)
     * @see ArrayUtility#create3D(Class)
     * @see ArrayUtility#create3D(int, int, int)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate3D() throws Exception {
        //boolean
        Boolean[][][] booleanArray = TestAccess.invokeMethod(ArrayUtility.class, Boolean[][][].class, "create3D", Boolean.class, true, 5, 4, 3);
        Assert.assertNotNull(booleanArray);
        Assert.assertEquals(5, booleanArray.length);
        Assert.assertTrue(Arrays.stream(booleanArray).allMatch(e -> (e.length == 4)));
        Arrays.stream(booleanArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 3));
        Assert.assertTrue(Arrays.stream(booleanArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanArray = ArrayUtility.create3D(Boolean.class, 5, 4, 3);
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
        Integer[][][] integerArray = TestAccess.invokeMethod(ArrayUtility.class, Integer[][][].class, "create3D", Integer.class, 18, 7, 6, 5);
        Assert.assertNotNull(integerArray);
        Assert.assertEquals(7, integerArray.length);
        Assert.assertTrue(Arrays.stream(integerArray).allMatch(e -> (e.length == 6)));
        Arrays.stream(integerArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 5));
        Assert.assertTrue(Arrays.stream(integerArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerArray = ArrayUtility.create3D(Integer.class, 7, 6, 5);
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
        Float[][][] floatArray = TestAccess.invokeMethod(ArrayUtility.class, Float[][][].class, "create3D", Float.class, 6.847f, 8, 7, 6);
        Assert.assertNotNull(floatArray);
        Assert.assertEquals(8, floatArray.length);
        Assert.assertTrue(Arrays.stream(floatArray).allMatch(e -> (e.length == 7)));
        Arrays.stream(floatArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 6));
        Assert.assertTrue(Arrays.stream(floatArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatArray = ArrayUtility.create3D(Float.class, 8, 7, 6);
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
        Double[][][] doubleArray = TestAccess.invokeMethod(ArrayUtility.class, Double[][][].class, "create3D", Double.class, 117.4984560456d, 8, 7, 6);
        Assert.assertNotNull(doubleArray);
        Assert.assertEquals(8, doubleArray.length);
        Assert.assertTrue(Arrays.stream(doubleArray).allMatch(e -> (e.length == 7)));
        Arrays.stream(doubleArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 6));
        Assert.assertTrue(Arrays.stream(doubleArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleArray = ArrayUtility.create3D(Double.class, 8, 7, 6);
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
        Long[][][] longArray = TestAccess.invokeMethod(ArrayUtility.class, Long[][][].class, "create3D", Long.class, 178984654231545L, 7, 6, 5);
        Assert.assertNotNull(longArray);
        Assert.assertEquals(7, longArray.length);
        Assert.assertTrue(Arrays.stream(longArray).allMatch(e -> (e.length == 6)));
        Arrays.stream(longArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 5));
        Assert.assertTrue(Arrays.stream(longArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longArray = ArrayUtility.create3D(Long.class, 7, 6, 5);
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
        Object[][][] objectArray = TestAccess.invokeMethod(ArrayUtility.class, Object[][][].class, "create3D", Object.class, testObject, 5, 4, 3);
        Assert.assertNotNull(objectArray);
        Assert.assertEquals(5, objectArray.length);
        Assert.assertTrue(Arrays.stream(objectArray).allMatch(e -> (e.length == 4)));
        Arrays.stream(objectArray).flatMap(Arrays::stream).allMatch(e -> (e.length == 3));
        Assert.assertTrue(Arrays.stream(objectArray).flatMap(Arrays::stream).flatMap(Arrays::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectArray = ArrayUtility.create3D(Object.class, 5, 4, 3);
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
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create3D(-1, 5, 5));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create3D(5, -1, 5));
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayUtility.create3D(5, 5, -1));
    }
    
    /**
     * JUnit test of arrayOf.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#arrayOf(Class, Object[])
     * @see ArrayUtility#arrayOf(Object[])
     */
    @SuppressWarnings("ThrowableNotThrown")
    @Test
    public void testArrayOf() throws Exception {
        //boolean
        Boolean[] booleanArrayTest = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanArray = ArrayUtility.arrayOf(Boolean.class, true, false, false, true, false);
        TestUtils.assertArrayEquals(booleanArray, booleanArrayTest);
        
        //integer
        Integer[] integerArrayTest = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerArray = ArrayUtility.arrayOf(Integer.class, 15, 312, 48, 5, -4, -9, 6);
        TestUtils.assertArrayEquals(integerArray, integerArrayTest);
        
        //float
        Float[] floatArrayTest = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatArray = ArrayUtility.arrayOf(Float.class, 15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f);
        TestUtils.assertArrayEquals(floatArray, floatArrayTest);
        
        //double
        Double[] doubleArrayTest = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleArray = ArrayUtility.arrayOf(Double.class, 15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d);
        TestUtils.assertArrayEquals(doubleArray, doubleArrayTest);
        
        //long
        Long[] longArrayTest = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longArray = ArrayUtility.arrayOf(Long.class, 15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L);
        TestUtils.assertArrayEquals(longArray, longArrayTest);
        
        //object
        Object[] objectArrayTest = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArray = ArrayUtility.arrayOf(Object.class, "", 54, objectArrayTest[2], objectArrayTest[3], objectArrayTest[4]);
        TestUtils.assertArrayEquals(objectArray, objectArrayTest);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.arrayOf(Object.class, (Object[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.arrayOf(null, (Object[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.arrayOf((Object[]) null));
    }
    
    /**
     * JUnit test of toArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#toArray(Collection, Class)
     * @see ArrayUtility#toArray(Collection)
     */
    @Test
    public void testToArray() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Boolean[] booleanListArray = ArrayUtility.toArray(booleanList, Boolean.class);
        Assert.assertNotNull(booleanListArray);
        TestUtils.assertArrayEquals(booleanListArray, booleanList);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Integer[] integerListArray = ArrayUtility.toArray(integerList, Integer.class);
        Assert.assertNotNull(integerListArray);
        TestUtils.assertArrayEquals(integerListArray, integerList);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Float[] floatListArray = ArrayUtility.toArray(floatList, Float.class);
        Assert.assertNotNull(floatListArray);
        TestUtils.assertArrayEquals(floatListArray, floatList);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Double[] doubleListArray = ArrayUtility.toArray(doubleList, Double.class);
        Assert.assertNotNull(doubleListArray);
        TestUtils.assertArrayEquals(doubleListArray, doubleList);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Long[] longListArray = ArrayUtility.toArray(longList, Long.class);
        Assert.assertNotNull(longListArray);
        TestUtils.assertArrayEquals(longListArray, longList);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        Object[] objectListArray = ArrayUtility.toArray(objectList, Object.class);
        Assert.assertNotNull(objectListArray);
        TestUtils.assertArrayEquals(objectListArray, objectList);
        objectListArray = ArrayUtility.toArray(objectList);
        Assert.assertNotNull(objectListArray);
        TestUtils.assertArrayEquals(objectListArray, objectList);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.toArray(null, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.toArray(objectList, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.toArray(null));
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#clone(Object[])
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testClone() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanClone = ArrayUtility.clone(booleanArray);
        TestUtils.assertArrayEquals(booleanClone, booleanArray);
        Assert.assertNotSame(booleanArray, booleanClone);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerClone = ArrayUtility.clone(integerArray);
        TestUtils.assertArrayEquals(integerClone, integerArray);
        Assert.assertNotSame(integerArray, integerClone);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatClone = ArrayUtility.clone(floatArray);
        TestUtils.assertArrayEquals(floatClone, floatArray);
        Assert.assertNotSame(floatArray, floatClone);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleClone = ArrayUtility.clone(doubleArray);
        TestUtils.assertArrayEquals(doubleClone, doubleArray);
        Assert.assertNotSame(doubleArray, doubleClone);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longClone = ArrayUtility.clone(longArray);
        TestUtils.assertArrayEquals(longClone, longArray);
        Assert.assertNotSame(longArray, longClone);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectClone = ArrayUtility.clone(objectArray);
        TestUtils.assertArrayEquals(objectClone, objectArray);
        Assert.assertNotSame(objectArray, objectClone);
        
        //invalid
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
        TestUtils.assertArrayEquals(
                booleanSubArray,
                new Boolean[] {false, true});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerSubArray = ArrayUtility.subArray(integerArray, 2);
        TestUtils.assertArrayEquals(
                integerSubArray,
                new Integer[] {48, 5, -4, -9, 6});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatSubArray = ArrayUtility.subArray(floatArray, 0, 4);
        TestUtils.assertArrayEquals(
                floatSubArray,
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleSubArray = ArrayUtility.subArray(doubleArray, 0);
        TestUtils.assertArrayEquals(
                doubleSubArray,
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longSubArray = ArrayUtility.subArray(longArray, 6, 7);
        TestUtils.assertArrayEquals(
                longSubArray,
                new Long[] {699546101L});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectSubArray = ArrayUtility.subArray(objectArray, 1, 3);
        TestUtils.assertArrayEquals(
                objectSubArray,
                new Object[] {54, objectArray[2]});
        
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
        TestUtils.assertArrayEquals(
                booleanMergeArray,
                new Boolean[] {true, false, false, true, false, true, false});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerArray2 = new Integer[] {15, 312, 48};
        Integer[] integerMergeArray = ArrayUtility.merge(integerArray, integerArray2, Integer.class);
        TestUtils.assertArrayEquals(
                integerMergeArray,
                new Integer[] {15, 312, 48, 5, -4, -9, 6, 15, 312, 48});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatArray2 = new Float[] {15.1f};
        Float[] floatMergeArray = ArrayUtility.merge(floatArray, floatArray2, Float.class);
        TestUtils.assertArrayEquals(
                floatMergeArray,
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f, 15.1f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleArray2 = new Double[] {-4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleMergeArray = ArrayUtility.merge(doubleArray, doubleArray2, Double.class);
        TestUtils.assertArrayEquals(
                doubleMergeArray,
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longArray2 = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longMergeArray = ArrayUtility.merge(longArray, longArray2, Long.class);
        TestUtils.assertArrayEquals(
                longMergeArray,
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L, 15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArray2 = new Object[] {54, objectArray[2], objectArray[4]};
        Object[] objectMergeArray = ArrayUtility.merge(objectArray, objectArray2, Object.class);
        TestUtils.assertArrayEquals(
                objectMergeArray,
                new Object[] {"", 54, objectArray[2], objectArray[3], objectArray[4], 54, objectArray[2], objectArray[4]});
        
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
        Assert.assertTrue(Arrays.stream(booleanSplitArray).allMatch(e -> (e.length == 3)));
        
        TestUtils.assertArrayEquals(
                booleanSplitArray[0],
                new Boolean[] {true, false, false});
        TestUtils.assertArrayEquals(
                booleanSplitArray[1],
                new Boolean[] {true, false, null});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[][] integerSplitArray = ArrayUtility.split(integerArray, 2, Integer.class);
        Assert.assertEquals(4, integerSplitArray.length);
        Assert.assertTrue(Arrays.stream(integerSplitArray).allMatch(e -> (e.length == 2)));
        TestUtils.assertArrayEquals(
                integerSplitArray[0],
                new Integer[] {15, 312});
        TestUtils.assertArrayEquals(
                integerSplitArray[1],
                new Integer[] {48, 5});
        TestUtils.assertArrayEquals(
                integerSplitArray[2],
                new Integer[] {-4, -9});
        TestUtils.assertArrayEquals(
                integerSplitArray[3],
                new Integer[] {6, null});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[][] floatSplitArray = ArrayUtility.split(floatArray, 4, Float.class);
        Assert.assertEquals(2, floatSplitArray.length);
        Assert.assertTrue(Arrays.stream(floatSplitArray).allMatch(e -> (e.length == 4)));
        TestUtils.assertArrayEquals(
                floatSplitArray[0],
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f});
        TestUtils.assertArrayEquals(
                floatSplitArray[1],
                new Float[] {-4.006f, -9.7f, 6.99f, 19776.4f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[][] doubleSplitArray = ArrayUtility.split(doubleArray, 7, Double.class);
        Assert.assertEquals(1, doubleSplitArray.length);
        Assert.assertTrue(Arrays.stream(doubleSplitArray).allMatch(e -> (e.length == 7)));
        TestUtils.assertArrayEquals(
                doubleSplitArray[0],
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[][] longSplitArray = ArrayUtility.split(longArray, 1, Long.class);
        Assert.assertEquals(7, longSplitArray.length);
        Assert.assertTrue(Arrays.stream(longSplitArray).allMatch(e -> (e.length == 1)));
        TestUtils.assertArrayEquals(
                longSplitArray[0],
                new Long[] {15104564L});
        TestUtils.assertArrayEquals(
                longSplitArray[1],
                new Long[] {3129113874L});
        TestUtils.assertArrayEquals(
                longSplitArray[2],
                new Long[] {4800000015L});
        TestUtils.assertArrayEquals(
                longSplitArray[3],
                new Long[] {5457894511L});
        TestUtils.assertArrayEquals(
                longSplitArray[4],
                new Long[] {-4006005001L});
        TestUtils.assertArrayEquals(
                longSplitArray[5],
                new Long[] {-970487745L});
        TestUtils.assertArrayEquals(
                longSplitArray[6],
                new Long[] {699546101L});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[][] objectSplitArray = ArrayUtility.split(objectArray, 3, Object.class);
        Assert.assertEquals(2, objectSplitArray.length);
        Assert.assertTrue(Arrays.stream(objectSplitArray).allMatch(e -> (e.length == 3)));
        TestUtils.assertArrayEquals(
                objectSplitArray[0],
                new Object[] {"", 54, objectArray[2]});
        TestUtils.assertArrayEquals(
                objectSplitArray[1],
                new Object[] {objectArray[3], objectArray[4], null});
        
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
        TestUtils.assertArrayEquals(
                booleanReverseArray,
                new Boolean[] {false, true, false, false, true});
        Assert.assertNotSame(booleanArray, booleanReverseArray);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerReversedArray = ArrayUtility.reverse(integerArray);
        TestUtils.assertArrayEquals(
                integerReversedArray,
                new Integer[] {6, -9, -4, 5, 48, 312, 15});
        Assert.assertNotSame(integerArray, integerReversedArray);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatReversedArray = ArrayUtility.reverse(floatArray);
        TestUtils.assertArrayEquals(
                floatReversedArray,
                new Float[] {19776.4f, 6.99f, -9.7f, -4.006f, 5.45f, 48.0f, 312.91f, 15.1f});
        Assert.assertNotSame(floatArray, floatReversedArray);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleReversedArray = ArrayUtility.reverse(doubleArray);
        TestUtils.assertArrayEquals(
                doubleReversedArray,
                new Double[] {6.99546101d, -9.70487745d, -4.006005001d, 5.457894511d, 48.00000015d, 312.9113874d, 15.104564d});
        Assert.assertNotSame(doubleArray, doubleReversedArray);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longReversedArray = ArrayUtility.reverse(longArray);
        TestUtils.assertArrayEquals(
                longReversedArray,
                new Long[] {699546101L, -970487745L, -4006005001L, 5457894511L, 4800000015L, 3129113874L, 15104564L});
        Assert.assertNotSame(longArray, longReversedArray);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectReversedArray = ArrayUtility.reverse(objectArray);
        TestUtils.assertArrayEquals(
                objectReversedArray,
                new Object[] {objectArray[4], objectArray[3], objectArray[2], 54, ""});
        Assert.assertNotSame(objectArray, objectReversedArray);
        
        //invalid
        
        Object[] emptyReversedArray = ArrayUtility.reverse(new Object[] {});
        Assert.assertEquals(0, emptyReversedArray.length);
        
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.reverse(null));
    }
    
    /**
     * JUnit test of shuffle.
     *
     * @throws Exception When there is an exception.
     * @see ArrayUtility#shuffle(Object[])
     */
    @Test
    public void testShuffle() throws Exception {
        //boolean
        Boolean[] booleanArray = ArrayUtility.duplicateInOrder(new Boolean[] {true, false, false, true, false}, 10, Boolean.class);
        Boolean[] booleanShuffledArray = ArrayUtility.shuffle(booleanArray);
        TestUtils.assertArrayEquals(booleanShuffledArray, booleanArray, false);
        TestUtils.assertArrayNotEquals(booleanShuffledArray, booleanArray, true);
        
        //int
        Integer[] integerArray = ArrayUtility.duplicateInOrder(new Integer[] {15, 312, 48, 5, -4, -9, 6}, 10, Integer.class);
        Integer[] integerShuffledArray = ArrayUtility.shuffle(integerArray);
        TestUtils.assertArrayEquals(integerShuffledArray, integerArray, false);
        TestUtils.assertArrayNotEquals(integerShuffledArray, integerArray, true);
        
        //float
        Float[] floatArray = ArrayUtility.duplicateInOrder(new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f}, 10, Float.class);
        Float[] floatShuffledArray = ArrayUtility.shuffle(floatArray);
        TestUtils.assertArrayEquals(floatShuffledArray, floatArray, false);
        TestUtils.assertArrayNotEquals(floatShuffledArray, floatArray, true);
        
        //double
        Double[] doubleArray = ArrayUtility.duplicateInOrder(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d}, 10, Double.class);
        Double[] doubleShuffledArray = ArrayUtility.shuffle(doubleArray);
        TestUtils.assertArrayEquals(doubleShuffledArray, doubleArray, false);
        TestUtils.assertArrayNotEquals(doubleShuffledArray, doubleArray, true);
        
        //long
        Long[] longArray = ArrayUtility.duplicateInOrder(new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L}, 10, Long.class);
        Long[] longShuffledArray = ArrayUtility.shuffle(longArray);
        TestUtils.assertArrayEquals(longShuffledArray, longArray, false);
        TestUtils.assertArrayNotEquals(longShuffledArray, longArray, true);
        
        //object
        Object[] objectArray = ArrayUtility.duplicateInOrder(new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()}, 10, Object.class);
        Object[] objectShuffledArray = ArrayUtility.shuffle(objectArray);
        TestUtils.assertArrayEquals(objectShuffledArray, objectArray, false);
        TestUtils.assertArrayNotEquals(objectShuffledArray, objectArray, true);
        
        //invalid
        
        Object[] emptyShuffledArray = ArrayUtility.shuffle(new Object[] {});
        Assert.assertEquals(0, emptyShuffledArray.length);
        
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayUtility.shuffle(null));
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
        Assert.assertFalse(ArrayUtility.isNullOrEmpty(ArrayUtility.arrayOf(String.class, "test", "array")));
        
        //empty
        Assert.assertTrue(ArrayUtility.isNullOrEmpty(ArrayUtility.emptyArray()));
        
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
        TestUtils.assertArrayEquals(
                ArrayUtility.removeNull(array),
                new Object[] {a, c, e, g, i, k, m});
        
        //all not null
        array = new Object[] {a, c, e, g, i, k, m};
        TestUtils.assertArrayEquals(
                ArrayUtility.removeNull(array),
                new Object[] {a, c, e, g, i, k, m});
        
        //all null
        array = new Object[] {b, d, f, h, j, l, n};
        TestUtils.assertArrayEquals(
                ArrayUtility.removeNull(array),
                new Object[] {});
        
        //none
        array = new Object[] {};
        TestUtils.assertArrayEquals(
                ArrayUtility.removeNull(array),
                new Object[] {});
        
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
        TestUtils.assertArrayEquals(
                booleanCleanArray,
                new Boolean[] {true, false});
        
        //int
        Integer[] integerArray = new Integer[] {15, 15, 312, 48, 5, 5, -4, -9, -9, 6};
        Integer[] integerCleanArray = ArrayUtility.removeDuplicates(integerArray, Integer.class);
        TestUtils.assertArrayEquals(
                integerCleanArray,
                new Integer[] {15, 312, 48, 5, -4, -9, 6});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 15.1f, 15.1f, 312.91f, 312.91f, 48.0f, 5.45f, -4.006f, -4.006f, -9.7f, 6.99f, 6.99f};
        Float[] floatCleanArray = ArrayUtility.removeDuplicates(floatArray, Float.class);
        TestUtils.assertArrayEquals(
                floatCleanArray,
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 15.104564d, 15.104564d, 312.9113874d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -4.006005001d, -9.70487745d, 6.99546101d, 6.99546101d};
        Double[] doubleCleanArray = ArrayUtility.removeDuplicates(doubleArray, Double.class);
        TestUtils.assertArrayEquals(
                doubleCleanArray,
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 15104564L, 15104564L, 3129113874L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -4006005001L, -970487745L, 699546101L, 699546101L};
        Long[] longCleanArray = ArrayUtility.removeDuplicates(longArray, Long.class);
        TestUtils.assertArrayEquals(
                longCleanArray,
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L});
        
        //object
        Object[] testObjects = new Object[] {new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArray = new Object[] {"", "", "", 54, 54, testObjects[0], testObjects[0], testObjects[1], testObjects[1], testObjects[1], testObjects[2], testObjects[2]};
        Object[] objectCleanArray = ArrayUtility.removeDuplicates(objectArray, Object.class);
        TestUtils.assertArrayEquals(
                objectCleanArray,
                new Object[] {"", 54, testObjects[0], testObjects[1], testObjects[2]});
        
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
        Assert.assertNull(ArrayUtility.selectRandom(ArrayUtility.emptyArray()));
        
        //invalid
        Assert.assertNull(ArrayUtility.selectRandom(null));
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
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] duplicatedBooleanArray = ArrayUtility.duplicateInOrder(booleanArray, Boolean.class);
        TestUtils.assertArrayEquals(
                duplicatedBooleanArray,
                Collections.nCopies(2, booleanArray).stream().flatMap(Arrays::stream).toArray());
        duplicatedBooleanArray = ArrayUtility.duplicateInOrder(booleanArray, 6, Boolean.class);
        TestUtils.assertArrayEquals(
                duplicatedBooleanArray,
                Collections.nCopies(6, booleanArray).stream().flatMap(Arrays::stream).toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] duplicatedIntegerArray = ArrayUtility.duplicateInOrder(integerArray, Integer.class);
        TestUtils.assertArrayEquals(
                duplicatedIntegerArray,
                Collections.nCopies(2, integerArray).stream().flatMap(Arrays::stream).toArray());
        duplicatedIntegerArray = ArrayUtility.duplicateInOrder(integerArray, 150, Integer.class);
        TestUtils.assertArrayEquals(
                duplicatedIntegerArray,
                Collections.nCopies(150, integerArray).stream().flatMap(Arrays::stream).toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] duplicatedFloatArray = ArrayUtility.duplicateInOrder(floatArray, Float.class);
        TestUtils.assertArrayEquals(
                duplicatedFloatArray,
                Collections.nCopies(2, floatArray).stream().flatMap(Arrays::stream).toArray());
        duplicatedFloatArray = ArrayUtility.duplicateInOrder(floatArray, 51, Float.class);
        TestUtils.assertArrayEquals(
                duplicatedFloatArray,
                Collections.nCopies(51, floatArray).stream().flatMap(Arrays::stream).toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] duplicatedDoubleArray = ArrayUtility.duplicateInOrder(doubleArray, Double.class);
        TestUtils.assertArrayEquals(
                duplicatedDoubleArray,
                Collections.nCopies(2, doubleArray).stream().flatMap(Arrays::stream).toArray());
        duplicatedDoubleArray = ArrayUtility.duplicateInOrder(doubleArray, 8, Double.class);
        TestUtils.assertArrayEquals(
                duplicatedDoubleArray,
                Collections.nCopies(8, doubleArray).stream().flatMap(Arrays::stream).toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] duplicatedLongArray = ArrayUtility.duplicateInOrder(longArray, Long.class);
        TestUtils.assertArrayEquals(
                duplicatedLongArray,
                Collections.nCopies(2, longArray).stream().flatMap(Arrays::stream).toArray());
        duplicatedLongArray = ArrayUtility.duplicateInOrder(longArray, 10, Long.class);
        TestUtils.assertArrayEquals(
                duplicatedLongArray,
                Collections.nCopies(10, longArray).stream().flatMap(Arrays::stream).toArray());
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] duplicatedObjectArray = ArrayUtility.duplicateInOrder(objectArray, Object.class);
        TestUtils.assertArrayEquals(
                duplicatedObjectArray,
                Collections.nCopies(2, objectArray).stream().flatMap(Arrays::stream).toArray());
        duplicatedObjectArray = ArrayUtility.duplicateInOrder(objectArray, 12, Object.class);
        TestUtils.assertArrayEquals(
                duplicatedObjectArray,
                Collections.nCopies(12, objectArray).stream().flatMap(Arrays::stream).toArray());
        
        //edge case
        duplicatedObjectArray = ArrayUtility.duplicateInOrder(objectArray, 1, Object.class);
        TestUtils.assertArrayEquals(duplicatedObjectArray, objectArray);
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
        TestUtils.assertArrayEquals(
                sortedBooleanArray,
                new Boolean[] {false, false, false, true, true});
        sortedBooleanArray = ArrayUtility.sortByNumberOfOccurrences(booleanArray, false, Boolean.class);
        TestUtils.assertArrayEquals(
                sortedBooleanArray,
                new Boolean[] {false, false, false, true, true});
        sortedBooleanArray = ArrayUtility.sortByNumberOfOccurrences(booleanArray, true, Boolean.class);
        TestUtils.assertArrayEquals(
                sortedBooleanArray,
                new Boolean[] {true, true, false, false, false});
        
        //int
        Integer[] integerArray = new Integer[] {1, 3, -5, 8, 4, 1, 1, 1, 3, 3, 1, 1, 4};
        Integer[] sortedIntegerArray = ArrayUtility.sortByNumberOfOccurrences(integerArray, Integer.class);
        TestUtils.assertArrayEquals(
                sortedIntegerArray,
                new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8});
        sortedIntegerArray = ArrayUtility.sortByNumberOfOccurrences(integerArray, false, Integer.class);
        TestUtils.assertArrayEquals(
                sortedIntegerArray,
                new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8});
        sortedIntegerArray = ArrayUtility.sortByNumberOfOccurrences(integerArray, true, Integer.class);
        TestUtils.assertArrayEquals(
                sortedIntegerArray,
                new Integer[] {-5, 8, 4, 4, 3, 3, 3, 1, 1, 1, 1, 1, 1});
        
        //float
        Float[] floatArray = new Float[] {1.1f, 3.9f, -5.0f, 8.44f, 4.7f, 1.1f, 1.1f, 1.1f, 3.8f, 3.8f, 1.1f, 1.1f, 4.7f};
        Float[] sortedFloatArray = ArrayUtility.sortByNumberOfOccurrences(floatArray, Float.class);
        TestUtils.assertArrayEquals(
                sortedFloatArray,
                new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f});
        sortedFloatArray = ArrayUtility.sortByNumberOfOccurrences(floatArray, false, Float.class);
        TestUtils.assertArrayEquals(
                sortedFloatArray,
                new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f});
        sortedFloatArray = ArrayUtility.sortByNumberOfOccurrences(floatArray, true, Float.class);
        TestUtils.assertArrayEquals(
                sortedFloatArray,
                new Float[] {3.9f, -5.0f, 8.44f, 4.7f, 4.7f, 3.8f, 3.8f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f});
        
        //double
        Double[] doubleArray = new Double[] {1.1d, 3.9d, -5.0d, 8.44d, 4.7d, 1.1d, 1.1d, 1.1d, 3.8d, 3.8d, 1.1d, 1.1d, 4.7d};
        Double[] sortedDoubleArray = ArrayUtility.sortByNumberOfOccurrences(doubleArray, Double.class);
        TestUtils.assertArrayEquals(
                sortedDoubleArray,
                new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d});
        sortedDoubleArray = ArrayUtility.sortByNumberOfOccurrences(doubleArray, false, Double.class);
        TestUtils.assertArrayEquals(
                sortedDoubleArray,
                new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d});
        sortedDoubleArray = ArrayUtility.sortByNumberOfOccurrences(doubleArray, true, Double.class);
        TestUtils.assertArrayEquals(
                sortedDoubleArray,
                new Double[] {3.9d, -5.0d, 8.44d, 4.7d, 4.7d, 3.8d, 3.8d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d});
        
        //long
        Long[] longArray = new Long[] {1000L, 3000L, -5000L, 8000L, 4000L, 1000L, 1000L, 1000L, 3000L, 3000L, 1000L, 1000L, 4000L};
        Long[] sortedLongArray = ArrayUtility.sortByNumberOfOccurrences(longArray, Long.class);
        TestUtils.assertArrayEquals(
                sortedLongArray,
                new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L});
        sortedLongArray = ArrayUtility.sortByNumberOfOccurrences(longArray, false, Long.class);
        TestUtils.assertArrayEquals(
                sortedLongArray,
                new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L});
        sortedLongArray = ArrayUtility.sortByNumberOfOccurrences(longArray, true, Long.class);
        TestUtils.assertArrayEquals(
                sortedLongArray,
                new Long[] {-5000L, 8000L, 4000L, 4000L, 3000L, 3000L, 3000L, 1000L, 1000L, 1000L, 1000L, 1000L, 1000L});
        
        //object
        Object a = new Object();
        Object b = "";
        Object c = new ArithmeticException();
        Object d = new HashMap<>();
        Object[] objectArray = new Object[] {a, b, a, a, a, c, d, c, d, c};
        Object[] sortedObjectArray = ArrayUtility.sortByNumberOfOccurrences(objectArray, Object.class);
        TestUtils.assertArrayEquals(
                sortedObjectArray,
                new Object[] {a, a, a, a, c, c, c, d, d, b});
        sortedObjectArray = ArrayUtility.sortByNumberOfOccurrences(objectArray, false, Object.class);
        TestUtils.assertArrayEquals(
                sortedObjectArray,
                new Object[] {a, a, a, a, c, c, c, d, d, b});
        sortedObjectArray = ArrayUtility.sortByNumberOfOccurrences(objectArray, true, Object.class);
        TestUtils.assertArrayEquals(
                sortedObjectArray,
                new Object[] {b, d, d, c, c, c, a, a, a, a});
        
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
