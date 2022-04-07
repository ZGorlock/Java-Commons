/*
 * File:    ArrayCollectorsTest.java
 * Package: commons.lambda.stream.collector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.collector;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;

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
 * JUnit test of ArrayCollectors.
 *
 * @see ArrayCollectors
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ArrayCollectors.class})
public class ArrayCollectorsTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ArrayCollectorsTest.class);
    
    
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
     * A set of arrays corresponding to the streams to use for testing.
     */
    private static final Object[][] testArrays = new Object[][] {
            new String[] {"hello", "world", "test"},
            new String[] {"test", "key", "another", "test"},
            new Boolean[] {false, true, true},
            new Integer[] {0, 9, -4, 10}};
    
    
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
     * JUnit test of toArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#toArray(IntFunction, Function)
     * @see ArrayCollectors#toArray(Class, Function)
     * @see ArrayCollectors#toArray(IntFunction)
     * @see ArrayCollectors#toArray(Class)
     * @see ArrayCollectors#toArray(Function)
     * @see ArrayCollectors#toArray()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToArray() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final String[] testArray1 = (String[]) testArrays[0];
        final String[] testArray2 = (String[]) testArrays[1];
        final Boolean[] testArray3 = (Boolean[]) testArrays[2];
        final Integer[] testArray4 = (Integer[]) testArrays[3];
        String[] array1;
        String[] array2;
        Boolean[] array3;
        Integer[] array4;
        List<Object>[] array5;
        Object[] array6;
        
        //standard
        array1 = testElements1.stream().collect(ArrayCollectors.toArray(String[]::new,
                Function.identity()));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toArray(String[]::new,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toArray(Boolean[]::new,
                (e -> (e > 3))));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toArray(Integer[]::new,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //class
        array1 = testElements1.stream().collect(ArrayCollectors.toArray(String.class,
                Function.identity()));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toArray(String.class,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toArray(Boolean.class,
                (e -> (e > 3))));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toArray(Integer.class,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //identity
        array1 = testElements1.stream().collect(ArrayCollectors.toArray(String[]::new,
                Function.identity()));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toArray(String[]::new,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toArray(Boolean[]::new,
                (e -> (e > 3))));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toArray(Integer[]::new,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //class, identity
        array1 = testElements1.stream().collect(ArrayCollectors.toArray(String.class));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testElements1);
        array2 = testElements2.stream().collect(ArrayCollectors.toArray(String.class));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testElements2);
        array4 = testElements3.stream().collect(ArrayCollectors.toArray(Integer.class));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testElements3);
        array5 = testElements4.stream().collect(ArrayCollectors.toArray(List.class));
        Assert.assertNotNull(array5);
        TestUtils.assertArrayEquals(array5, testElements4);
        
        //default
        array6 = testElements1.stream().collect(ArrayCollectors.toArray(
                Function.identity()));
        Assert.assertNotNull(array6);
        TestUtils.assertArrayEquals(array6, testArray1);
        array6 = testElements2.stream().collect(ArrayCollectors.toArray(
                (Function<? super String, ?>) (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(array6);
        TestUtils.assertArrayEquals(array6, testArray2);
        array6 = testElements3.stream().collect(ArrayCollectors.toArray(
                (Function<? super Integer, ?>) (e -> (e > 3))));
        Assert.assertNotNull(array6);
        TestUtils.assertArrayEquals(array6, testArray3);
        array6 = testElements4.stream().collect(ArrayCollectors.toArray(
                (Function<? super List<Object>, ?>) (e -> (int) e.get(0))));
        Assert.assertNotNull(array6);
        TestUtils.assertArrayEquals(array6, testArray4);
        
        //default, identity
        array6 = testElements1.stream().collect(ArrayCollectors.toArray(
                Function.identity()));
        Assert.assertNotNull(array6);
        TestUtils.assertArrayEquals(array6, testArray1);
        array6 = testElements2.stream().collect(ArrayCollectors.toArray(
                (Function<? super String, ?>) (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(array6);
        TestUtils.assertArrayEquals(array6, testArray2);
        array6 = testElements3.stream().collect(ArrayCollectors.toArray(
                (Function<? super Integer, ?>) (e -> (e > 3))));
        Assert.assertNotNull(array6);
        TestUtils.assertArrayEquals(array6, testArray3);
        array6 = testElements4.stream().collect(ArrayCollectors.toArray(
                (Function<? super List<Object>, ?>) (e -> (int) e.get(0))));
        Assert.assertNotNull(array6);
        TestUtils.assertArrayEquals(array6, testArray4);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toArray((IntFunction<Integer[]>) null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toArray(Integer[]::new, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toArray((IntFunction<Integer[]>) null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toArray((Class<Integer>) null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toArray(Integer[]::new, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toArray((Class<Integer>) null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toArray((IntFunction<Integer[]>) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toArray((Class<Integer>) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toArray((Function<Integer, Integer>) null)));
    }
    
    /**
     * JUnit test of generator.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#generator(Class)
     * @see ArrayCollectors#generator()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testGenerator() throws Exception {
        Object[] array1;
        String[] array2;
        Boolean[] array3;
        
        //standard
        array1 = ArrayCollectors.generator(Object.class).apply(10);
        Assert.assertNotNull(array1);
        Assert.assertEquals(10, array1.length);
        Assert.assertTrue(Arrays.stream(array1).allMatch(Objects::isNull));
        array2 = ArrayCollectors.generator(String.class).apply(3);
        Assert.assertNotNull(array2);
        Assert.assertEquals(3, array2.length);
        Assert.assertTrue(Arrays.stream(array2).allMatch(Objects::isNull));
        array3 = ArrayCollectors.generator(Boolean.class).apply(0);
        Assert.assertNotNull(array3);
        Assert.assertEquals(0, array3.length);
        Assert.assertTrue(Arrays.stream(array3).allMatch(Objects::isNull));
        array1 = ArrayCollectors.generator().apply(1);
        Assert.assertNotNull(array1);
        Assert.assertEquals(1, array1.length);
        Assert.assertTrue(Arrays.stream(array1).allMatch(Objects::isNull));
        
        //invalid
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayCollectors.generator(Object.class).apply(-1));
        TestUtils.assertNoException(() ->
                ArrayCollectors.generator(null));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayCollectors.generator(null).apply(0));
    }
    
    /**
     * JUnit test of generator2D.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#generator2D(Class)
     * @see ArrayCollectors#generator2D()
     */
    @Test
    public void testGenerator2D() throws Exception {
        Object[][] array1;
        String[][] array2;
        Boolean[][] array3;
        
        //standard
        array1 = ArrayCollectors.generator2D(Object.class).apply(10);
        Assert.assertNotNull(array1);
        Assert.assertEquals(10, array1.length);
        Assert.assertTrue(Arrays.stream(array1).allMatch(Objects::isNull));
        array2 = ArrayCollectors.generator2D(String.class).apply(3);
        Assert.assertNotNull(array2);
        Assert.assertEquals(3, array2.length);
        Assert.assertTrue(Arrays.stream(array2).allMatch(Objects::isNull));
        array3 = ArrayCollectors.generator2D(Boolean.class).apply(0);
        Assert.assertNotNull(array3);
        Assert.assertEquals(0, array3.length);
        Assert.assertTrue(Arrays.stream(array3).allMatch(Objects::isNull));
        array1 = ArrayCollectors.generator2D().apply(1);
        Assert.assertNotNull(array1);
        Assert.assertEquals(1, array1.length);
        Assert.assertTrue(Arrays.stream(array1).allMatch(Objects::isNull));
        
        //invalid
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayCollectors.generator2D(Object.class).apply(-1));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayCollectors.generator2D(null));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayCollectors.generator2D(null).apply(0));
    }
    
    /**
     * JUnit test of generator3D.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#generator3D(Class)
     * @see ArrayCollectors#generator3D()
     */
    @Test
    public void testGenerator3D() throws Exception {
        Object[][][] array1;
        String[][][] array2;
        Boolean[][][] array3;
        
        //standard
        array1 = ArrayCollectors.generator3D(Object.class).apply(10);
        Assert.assertNotNull(array1);
        Assert.assertEquals(10, array1.length);
        Assert.assertTrue(Arrays.stream(array1).allMatch(Objects::isNull));
        array2 = ArrayCollectors.generator3D(String.class).apply(3);
        Assert.assertNotNull(array2);
        Assert.assertEquals(3, array2.length);
        Assert.assertTrue(Arrays.stream(array2).allMatch(Objects::isNull));
        array3 = ArrayCollectors.generator3D(Boolean.class).apply(0);
        Assert.assertNotNull(array3);
        Assert.assertEquals(0, array3.length);
        Assert.assertTrue(Arrays.stream(array3).allMatch(Objects::isNull));
        array1 = ArrayCollectors.generator3D().apply(1);
        Assert.assertNotNull(array1);
        Assert.assertEquals(1, array1.length);
        Assert.assertTrue(Arrays.stream(array1).allMatch(Objects::isNull));
        
        //invalid
        TestUtils.assertException(NegativeArraySizeException.class, "-1", () ->
                ArrayCollectors.generator3D(Object.class).apply(-1));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayCollectors.generator3D(null));
        TestUtils.assertException(NullPointerException.class, () ->
                ArrayCollectors.generator3D(null).apply(0));
    }
    
    /**
     * JUnit test of toByteArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#toByteArray(Function)
     * @see ArrayCollectors#toByteArray()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToByteArray() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Byte[] testArray1 = ((List<String>) testStreamElements[0]).stream().map(String::length).map(Number::byteValue).toArray(Byte[]::new);
        final Byte[] testArray2 = ((List<String>) testStreamElements[1]).stream().map(e -> e.indexOf(':')).map(Number::byteValue).toArray(Byte[]::new);
        final Byte[] testArray3 = ((List<Integer>) testStreamElements[2]).stream().map(Number::byteValue).toArray(Byte[]::new);
        final Byte[] testArray4 = ((List<List<Object>>) testStreamElements[3]).stream().map(e -> ((int) e.get(0) + ((String) e.get(1)).length())).map(Number::byteValue).toArray(Byte[]::new);
        Byte[] array1;
        Byte[] array2;
        Byte[] array3;
        Byte[] array4;
        
        //standard
        array1 = testElements1.stream().collect(ArrayCollectors.toByteArray(
                (e -> (byte) e.length())));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toByteArray(
                (e -> (byte) e.indexOf(':'))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toByteArray(
                Number::byteValue));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toByteArray(
                (e -> (byte) ((int) e.get(0) + ((String) e.get(1)).length()))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //identity
        array1 = Arrays.stream(testArray1).collect(ArrayCollectors.toByteArray());
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = Arrays.stream(testArray2).collect(ArrayCollectors.toByteArray());
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = Arrays.stream(testArray3).collect(ArrayCollectors.toByteArray());
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = Arrays.stream(testArray4).collect(ArrayCollectors.toByteArray());
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toByteArray(null)));
    }
    
    /**
     * JUnit test of toShortArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#toShortArray(Function)
     * @see ArrayCollectors#toShortArray()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToShortArray() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Short[] testArray1 = ((List<String>) testStreamElements[0]).stream().map(String::length).map(Number::shortValue).toArray(Short[]::new);
        final Short[] testArray2 = ((List<String>) testStreamElements[1]).stream().map(e -> e.indexOf(':')).map(Number::shortValue).toArray(Short[]::new);
        final Short[] testArray3 = ((List<Integer>) testStreamElements[2]).stream().map(Number::shortValue).toArray(Short[]::new);
        final Short[] testArray4 = ((List<List<Object>>) testStreamElements[3]).stream().map(e -> ((int) e.get(0) + ((String) e.get(1)).length())).map(Number::shortValue).toArray(Short[]::new);
        Short[] array1;
        Short[] array2;
        Short[] array3;
        Short[] array4;
        
        //standard
        array1 = testElements1.stream().collect(ArrayCollectors.toShortArray(
                (e -> (short) e.length())));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toShortArray(
                (e -> (short) e.indexOf(':'))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toShortArray(
                Number::shortValue));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toShortArray(
                (e -> (short) ((int) e.get(0) + ((String) e.get(1)).length()))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //identity
        array1 = Arrays.stream(testArray1).collect(ArrayCollectors.toShortArray());
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = Arrays.stream(testArray2).collect(ArrayCollectors.toShortArray());
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = Arrays.stream(testArray3).collect(ArrayCollectors.toShortArray());
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = Arrays.stream(testArray4).collect(ArrayCollectors.toShortArray());
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toShortArray(null)));
    }
    
    /**
     * JUnit test of toIntArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#toIntArray(Function)
     * @see ArrayCollectors#toIntArray()
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored", "SimplifyStreamApiCallChains"})
    @Test
    public void testToIntArray() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Integer[] testArray1 = ((List<String>) testStreamElements[0]).stream().map(String::length).map(Number::intValue).toArray(Integer[]::new);
        final Integer[] testArray2 = ((List<String>) testStreamElements[1]).stream().map(e -> e.indexOf(':')).map(Number::intValue).toArray(Integer[]::new);
        final Integer[] testArray3 = ((List<Integer>) testStreamElements[2]).stream().map(Number::intValue).toArray(Integer[]::new);
        final Integer[] testArray4 = ((List<List<Object>>) testStreamElements[3]).stream().map(e -> ((int) e.get(0) + ((String) e.get(1)).length())).map(Number::intValue).toArray(Integer[]::new);
        Integer[] array1;
        Integer[] array2;
        Integer[] array3;
        Integer[] array4;
        
        //standard
        array1 = testElements1.stream().collect(ArrayCollectors.toIntArray(
                String::length));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toIntArray(
                (e -> e.indexOf(':'))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toIntArray(
                Number::intValue));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toIntArray(
                (e -> ((int) e.get(0) + ((String) e.get(1)).length()))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //identity
        array1 = Arrays.stream(testArray1).collect(ArrayCollectors.toIntArray());
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = Arrays.stream(testArray2).collect(ArrayCollectors.toIntArray());
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = Arrays.stream(testArray3).collect(ArrayCollectors.toIntArray());
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = Arrays.stream(testArray4).collect(ArrayCollectors.toIntArray());
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toIntArray(null)));
    }
    
    /**
     * JUnit test of toLongArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#toLongArray(Function)
     * @see ArrayCollectors#toLongArray()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToLongArray() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Long[] testArray1 = ((List<String>) testStreamElements[0]).stream().map(String::length).map(Number::longValue).toArray(Long[]::new);
        final Long[] testArray2 = ((List<String>) testStreamElements[1]).stream().map(e -> e.indexOf(':')).map(Number::longValue).toArray(Long[]::new);
        final Long[] testArray3 = ((List<Integer>) testStreamElements[2]).stream().map(Number::longValue).toArray(Long[]::new);
        final Long[] testArray4 = ((List<List<Object>>) testStreamElements[3]).stream().map(e -> ((int) e.get(0) + ((String) e.get(1)).length())).map(Number::longValue).toArray(Long[]::new);
        Long[] array1;
        Long[] array2;
        Long[] array3;
        Long[] array4;
        
        //standard
        array1 = testElements1.stream().collect(ArrayCollectors.toLongArray(
                (e -> (long) e.length())));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toLongArray(
                (e -> (long) e.indexOf(':'))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toLongArray(
                Number::longValue));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toLongArray(
                (e -> (long) ((int) e.get(0) + ((String) e.get(1)).length()))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //identity
        array1 = Arrays.stream(testArray1).collect(ArrayCollectors.toLongArray());
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = Arrays.stream(testArray2).collect(ArrayCollectors.toLongArray());
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = Arrays.stream(testArray3).collect(ArrayCollectors.toLongArray());
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = Arrays.stream(testArray4).collect(ArrayCollectors.toLongArray());
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toLongArray(null)));
    }
    
    /**
     * JUnit test of toFloatArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#toFloatArray(Function)
     * @see ArrayCollectors#toFloatArray()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToFloatArray() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Float[] testArray1 = ((List<String>) testStreamElements[0]).stream().map(String::length).map(Number::floatValue).toArray(Float[]::new);
        final Float[] testArray2 = ((List<String>) testStreamElements[1]).stream().map(e -> e.indexOf(':')).map(Number::floatValue).toArray(Float[]::new);
        final Float[] testArray3 = ((List<Integer>) testStreamElements[2]).stream().map(Number::floatValue).toArray(Float[]::new);
        final Float[] testArray4 = ((List<List<Object>>) testStreamElements[3]).stream().map(e -> ((int) e.get(0) + ((String) e.get(1)).length())).map(Number::floatValue).toArray(Float[]::new);
        Float[] array1;
        Float[] array2;
        Float[] array3;
        Float[] array4;
        
        //standard
        array1 = testElements1.stream().collect(ArrayCollectors.toFloatArray(
                (e -> (float) e.length())));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toFloatArray(
                (e -> (float) e.indexOf(':'))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toFloatArray(
                Number::floatValue));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toFloatArray(
                (e -> (float) ((int) e.get(0) + ((String) e.get(1)).length()))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //identity
        array1 = Arrays.stream(testArray1).collect(ArrayCollectors.toFloatArray());
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = Arrays.stream(testArray2).collect(ArrayCollectors.toFloatArray());
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = Arrays.stream(testArray3).collect(ArrayCollectors.toFloatArray());
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = Arrays.stream(testArray4).collect(ArrayCollectors.toFloatArray());
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toFloatArray(null)));
    }
    
    /**
     * JUnit test of toDoubleArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#toDoubleArray(Function)
     * @see ArrayCollectors#toDoubleArray()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToDoubleArray() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Double[] testArray1 = ((List<String>) testStreamElements[0]).stream().map(String::length).map(Number::doubleValue).toArray(Double[]::new);
        final Double[] testArray2 = ((List<String>) testStreamElements[1]).stream().map(e -> e.indexOf(':')).map(Number::doubleValue).toArray(Double[]::new);
        final Double[] testArray3 = ((List<Integer>) testStreamElements[2]).stream().map(Number::doubleValue).toArray(Double[]::new);
        final Double[] testArray4 = ((List<List<Object>>) testStreamElements[3]).stream().map(e -> ((int) e.get(0) + ((String) e.get(1)).length())).map(Number::doubleValue).toArray(Double[]::new);
        Double[] array1;
        Double[] array2;
        Double[] array3;
        Double[] array4;
        
        //standard
        array1 = testElements1.stream().collect(ArrayCollectors.toDoubleArray(
                (e -> (double) e.length())));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toDoubleArray(
                (e -> (double) e.indexOf(':'))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toDoubleArray(
                Number::doubleValue));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toDoubleArray(
                (e -> (double) ((int) e.get(0) + ((String) e.get(1)).length()))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //identity
        array1 = Arrays.stream(testArray1).collect(ArrayCollectors.toDoubleArray());
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = Arrays.stream(testArray2).collect(ArrayCollectors.toDoubleArray());
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = Arrays.stream(testArray3).collect(ArrayCollectors.toDoubleArray());
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = Arrays.stream(testArray4).collect(ArrayCollectors.toDoubleArray());
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toDoubleArray(null)));
    }
    
    /**
     * JUnit test of toCharArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#toCharArray(Function)
     * @see ArrayCollectors#toCharArray()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToCharArray() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final Character[] testArray1 = ((List<String>) testStreamElements[0]).stream().map(e -> e.charAt(0)).toArray(Character[]::new);
        final Character[] testArray2 = ((List<String>) testStreamElements[1]).stream().map(e -> e.charAt(e.indexOf(":") + 1)).toArray(Character[]::new);
        final Character[] testArray3 = ((List<Integer>) testStreamElements[2]).stream().map(e -> (char) e.intValue()).toArray(Character[]::new);
        final Character[] testArray4 = ((List<List<Object>>) testStreamElements[3]).stream().map(e -> (char) ((int) e.get(0) + ((String) e.get(1)).length())).toArray(Character[]::new);
        Character[] array1;
        Character[] array2;
        Character[] array3;
        Character[] array4;
        
        //standard
        array1 = testElements1.stream().collect(ArrayCollectors.toCharArray(
                (e -> e.charAt(0))));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toCharArray(
                (e -> e.charAt(e.indexOf(":") + 1))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toCharArray(
                (e -> (char) e.intValue())));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toCharArray(
                (e -> (char) ((int) e.get(0) + ((String) e.get(1)).length()))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //identity
        array1 = Arrays.stream(testArray1).collect(ArrayCollectors.toCharArray());
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = Arrays.stream(testArray2).collect(ArrayCollectors.toCharArray());
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = Arrays.stream(testArray3).collect(ArrayCollectors.toCharArray());
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = Arrays.stream(testArray4).collect(ArrayCollectors.toCharArray());
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toCharArray(null)));
    }
    
    /**
     * JUnit test of toStringArray.
     *
     * @throws Exception When there is an exception.
     * @see ArrayCollectors#toStringArray(Function)
     * @see ArrayCollectors#toStringArray()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testToStringArray() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final String[] testArray1 = ((List<String>) testStreamElements[0]).stream().map(String::toUpperCase).toArray(String[]::new);
        final String[] testArray2 = ((List<String>) testStreamElements[1]).stream().map(e -> e.substring(e.indexOf(':') + 1)).toArray(String[]::new);
        final String[] testArray3 = ((List<Integer>) testStreamElements[2]).stream().map(StringUtility::spaces).toArray(String[]::new);
        final String[] testArray4 = ((List<List<Object>>) testStreamElements[3]).stream().map(e -> (StringUtility.spaces((int) e.get(0)) + e.get(1))).toArray(String[]::new);
        String[] array1;
        String[] array2;
        String[] array3;
        String[] array4;
        
        //standard
        array1 = testElements1.stream().collect(ArrayCollectors.toStringArray(
                String::toUpperCase));
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = testElements2.stream().collect(ArrayCollectors.toStringArray(
                (e -> e.substring(e.indexOf(':') + 1))));
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = testElements3.stream().collect(ArrayCollectors.toStringArray(
                StringUtility::spaces));
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = testElements4.stream().collect(ArrayCollectors.toStringArray(
                (e -> (StringUtility.spaces((int) e.get(0)) + e.get(1)))));
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //identity
        array1 = Arrays.stream(testArray1).collect(ArrayCollectors.toStringArray());
        Assert.assertNotNull(array1);
        TestUtils.assertArrayEquals(array1, testArray1);
        array2 = Arrays.stream(testArray2).collect(ArrayCollectors.toStringArray());
        Assert.assertNotNull(array2);
        TestUtils.assertArrayEquals(array2, testArray2);
        array3 = Arrays.stream(testArray3).collect(ArrayCollectors.toStringArray());
        Assert.assertNotNull(array3);
        TestUtils.assertArrayEquals(array3, testArray3);
        array4 = Arrays.stream(testArray4).collect(ArrayCollectors.toStringArray());
        Assert.assertNotNull(array4);
        TestUtils.assertArrayEquals(array4, testArray4);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ArrayCollectors.toCharArray(null)));
    }
    
}
