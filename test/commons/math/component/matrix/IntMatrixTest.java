/*
 * File:    IntMatrixTest.java
 * Package: commons.math.component.matrix
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.matrix;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import commons.math.component.BaseComponent;
import commons.math.component.ComponentInterface;
import commons.math.component.handler.error.ComponentErrorHandlerInterface;
import commons.math.component.handler.error.ComponentErrorHandlerProvider;
import commons.math.component.handler.math.IntComponentMathHandler;
import commons.math.component.vector.BigVector;
import commons.math.component.vector.IntVector;
import commons.math.component.vector.Vector;
import commons.math.component.vector.VectorInterface;
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
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of IntMatrix.
 *
 * @see IntMatrix
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({IntMatrix.class})
public class IntMatrixTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(IntMatrixTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private IntMatrix sut;
    
    
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
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#IntMatrix(int...)
     * @see IntMatrix#IntMatrix(List)
     * @see IntMatrix#IntMatrix(IntMatrix)
     * @see IntMatrix#IntMatrix(Matrix)
     * @see IntMatrix#IntMatrix(int)
     * @see IntMatrix#IntMatrix()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        IntMatrix matrix = new IntMatrix(0, 2, 1, -9, 1, 8, -0, 5, 4);
        TestUtils.assertArrayEquals(
                matrix.getRawComponents(),
                new Number[] {0, 2, 1, -9, 1, 8, -0, 5, 4});
        Assert.assertEquals(3, matrix.getDimensionality());
        
        //list of components
        List<Number> values = Arrays.asList(0, 2, 1, -9, 1, 8, -0, 5, 4);
        IntMatrix matrix2 = new IntMatrix(values);
        TestUtils.assertArrayEquals(
                matrix2.getRawComponents(),
                new Number[] {0, 2, 1, -9, 1, 8, -0, 5, 4});
        Assert.assertEquals(3, matrix2.getDimensionality());
        
        //raw matrix
        IntMatrix matrix3 = new IntMatrix(new IntMatrix(0, 2, 1, -9, 1, 8, -0, 5, 4));
        TestUtils.assertArrayEquals(
                matrix3.getRawComponents(),
                new Number[] {0, 2, 1, -9, 1, 8, -0, 5, 4});
        Assert.assertEquals(3, matrix3.getDimensionality());
        
        //matrix
        IntMatrix matrix4 = new IntMatrix(new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4));
        TestUtils.assertArrayEquals(
                matrix4.getRawComponents(),
                new Number[] {0, 2, 1, -9, 1, 8, -0, 5, 4});
        Assert.assertEquals(3, matrix4.getDimensionality());
        
        //dimensionality
        IntMatrix matrixDimensionality = new IntMatrix(4);
        TestUtils.assertArrayEquals(
                matrixDimensionality.getRawComponents(),
                new Number[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        Assert.assertEquals(4, matrixDimensionality.getDimensionality());
        
        //empty
        IntMatrix matrixDefault = new IntMatrix();
        TestUtils.assertArrayEquals(
                matrixDefault.getRawComponents(),
                new Number[] {});
        Assert.assertEquals(0, matrixDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(matrix, matrix2);
        Assert.assertEquals(matrix2, matrix3);
        Assert.assertEquals(matrix3, matrix4);
        
        //invalid
        
        TestUtils.assertException(ArithmeticException.class, new IntMatrix().getErrorHandler().componentLengthNotSquareErrorMessage(new Integer[] {0, 2, 1, -9, 1, 8, -0, 5}), () ->
                new IntMatrix(0, 2, 1, -9, 1, 8, -0, 5));
        
        TestUtils.assertException(NullPointerException.class, () ->
                new IntMatrix(Arrays.asList(0.884, null, 1.1, -9.3)));
        TestUtils.assertException(NullPointerException.class, () ->
                new IntMatrix((IntMatrix) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new IntMatrix((Matrix) null));
    }
    
    /**
     * JUnit test of matrixString.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#matrixString()
     */
    @Test
    public void testMatrixString() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {1});
        Assert.assertEquals("[<1>]", sut.matrixString());
        
        sut = new IntMatrix(1, 2, 3, 4);
        Assert.assertEquals("[<1, 2>, <3, 4>]", sut.matrixString());
        
        sut = new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("[<1, 2, 3>, <4, 5, 6>, <7, 8, 9>]", sut.matrixString());
        
        sut = new IntMatrix(8, 77, 3, 3, 7, 890, 11, 7, 0);
        Assert.assertEquals("[<8, 77, 3>, <3, 7, 890>, <11, 7, 0>]", sut.matrixString());
        
        //empty
        
        sut = new IntMatrix();
        Assert.assertEquals("[]", sut.matrixString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {1});
        Assert.assertEquals("[<1>]", sut.toString());
        
        sut = new IntMatrix(1, 2, 3, 4);
        Assert.assertEquals("[<1, 2>, <3, 4>]", sut.toString());
        
        sut = new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("[<1, 2, 3>, <4, 5, 6>, <7, 8, 9>]", sut.toString());
        
        sut = new IntMatrix(8, 77, 3, 3, 7, 890, 11, 7, 0);
        Assert.assertEquals("[<8, 77, 3>, <3, 7, 890>, <11, 7, 0>]", sut.toString());
        
        //empty
        
        sut = new IntMatrix();
        Assert.assertEquals("[]", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new IntMatrix(8, -2, 3, 8);
        
        //standard
        
        other = new Matrix(8, -2, 3, 8);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(1, 2, 1, -9, 2, 8, -1, 5, 4, 0, 2, 4, 9, 7, 1, 7);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntMatrix(8, -2, 3, 8);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new Matrix().equals(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.equals(""));
        Assert.assertFalse(sut.equals(BigDecimal.valueOf(8.5)));
        Assert.assertFalse(sut.equals(new File(".")));
        Assert.assertFalse(sut.equals(null));
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new IntMatrix(8, -2, 3, 8);
        
        //standard
        
        other = new Matrix(8, -2, 3, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(1, 2, 1, -9, 2, 8, -1, 5, 4, 0, 2, 4, 9, 7, 1, 7);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntMatrix(8, -2, 3, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        Assert.assertTrue(new Matrix().dimensionalityEqual(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new IntMatrix(8, -2, 3, 8);
        
        //standard
        
        other = new Matrix(8, -2, 3, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Matrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(1, 2, 1, -9, 2, 8, -1, 5, 4, 0, 2, 4, 9, 7, 1, 7);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntMatrix(8, -2, 3, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        Assert.assertTrue(new Matrix().lengthEqual(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new IntMatrix(8, -2, 3, 8);
        
        //standard
        
        other = new Matrix(8, -2, 3, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(1, 2, 1, -9, 2, 8, -1, 5, 4, 0, 2, 4, 9, 7, 1, 7);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntMatrix(8, -2, 3, 8);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new Matrix().componentTypeEqual(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new IntMatrix(8, 7, 5, 1);
        IntMatrix clone = sut.cloned();
        Assert.assertNotNull(clone);
        TestUtils.assertArrayEquals(
                clone.getRawComponents(),
                sut.getRawComponents());
        Assert.assertNotSame(sut, clone);
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new IntMatrix(8, 7, 5, 1);
        IntMatrix emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        TestUtils.assertArrayEquals(
                emptyCopy.getRawComponents(),
                IntMatrix.origin(sut.getDimensionality()).getRawComponents());
        Assert.assertNotSame(sut, emptyCopy);
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
        //standard
        
        sut = new IntMatrix();
        Assert.assertEquals(
                new IntVector(), sut.newVector());
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(
                new IntVector(new int[] {0}), sut.newVector());
        
        sut = new IntMatrix(8, 7, 5, 1);
        Assert.assertEquals(
                new IntVector(0, 0), sut.newVector());
        
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.newVector());
        
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4, 0, 2, 4, 9, 7, 1, 7);
        Assert.assertEquals(
                new IntVector(0, 0, 0, 0), sut.newVector());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new IntMatrix();
        
        //standard
        Assert.assertEquals(
                new IntMatrix(), sut.createNewInstance(0));
        Assert.assertEquals(
                new IntMatrix(new int[] {0}), sut.createNewInstance(1));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0), sut.createNewInstance(2));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), sut.createNewInstance(3));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new IntMatrix(), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
        //standard
        
        sut = new IntMatrix(1);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        
        sut = new IntMatrix(1, 2, 3, 4);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        Assert.assertEquals(1, sut.toIndex(1, 0));
        Assert.assertEquals(3, sut.toIndex(1, 1));
        
        sut = new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        Assert.assertEquals(1, sut.toIndex(1, 0));
        Assert.assertEquals(4, sut.toIndex(1, 1));
        Assert.assertEquals(7, sut.toIndex(1, 2));
        
        //invalid
        
        sut = new IntMatrix();
        Assert.assertEquals(9, sut.toIndex(9, 9));
        
        sut = new IntMatrix(1);
        Assert.assertEquals(18, sut.toIndex(9, 9));
        
        sut = new IntMatrix(1, 2, 3, 4);
        Assert.assertEquals(27, sut.toIndex(9, 9));
        
        sut = new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(6, sut.toIndex(3, 1));
        Assert.assertEquals(36, sut.toIndex(9, 9));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        IntMatrix reversed;
        
        //standard
        
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Integer[] {4, 5, -1, 8, 2, -9, 1, 2, 1});
        
        sut = new IntMatrix(4, 5, -1, 8, 2, -9, 1, 2, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Integer[] {1, 2, 1, -9, 2, 8, -1, 5, 4});
        
        sut = new IntMatrix(0, 1, 0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Integer[] {1, 0, 1, 0});
        
        sut = new IntMatrix(new int[] {6});
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Integer[] {6});
        
        sut = new IntMatrix();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Integer[] {});
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        IntMatrix other;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        other = new IntMatrix(new int[] {10});
        Assert.assertEquals(1, sut.distance(other).intValue());
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(10, 6, 1, 5);
        Assert.assertEquals(9, sut.distance(other).intValue());
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        other = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        Assert.assertEquals(12, sut.distance(other).intValue());
        
        sut = new IntMatrix();
        other = new IntMatrix();
        Assert.assertEquals(0, sut.distance(other).intValue());
        
        //invalid
        
        sut = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        final IntMatrix other1 = new IntMatrix(10, 6, 1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new IntMatrix(new int[] {9});
        final IntMatrix other2 = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix other3 = new IntMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        IntMatrix other;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        other = new IntMatrix(new int[] {10});
        Assert.assertEquals(
                new IntMatrix(new int[] {9}), sut.midpoint(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(10, 6, 1, 5);
        Assert.assertEquals(
                new IntMatrix(9, 4, -2, 8), sut.midpoint(other));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        other = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        Assert.assertEquals(
                new IntMatrix(9, 4, -2, 8, 3, 6, 4, 7, 6), sut.midpoint(other));
        
        sut = new IntMatrix();
        other = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.midpoint(other));
        
        //invalid
        
        sut = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        final IntMatrix other1 = new IntMatrix(10, 6, 1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new IntMatrix(new int[] {9});
        final IntMatrix other2 = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix other3 = new IntMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#average(List)
     * @see IntMatrix#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        IntMatrix other1;
        IntMatrix other2;
        IntMatrix other3;
        IntMatrix other4;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        other1 = new IntMatrix(new int[] {10});
        other2 = new IntMatrix(new int[] {2});
        other3 = new IntMatrix(new int[] {12});
        Assert.assertEquals(
                new IntMatrix(new int[] {8}), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new IntMatrix(new int[] {8}), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other1 = new IntMatrix(10, 6, 1, 5);
        other2 = new IntMatrix(2, 5, 0, 3);
        other3 = new IntMatrix(12, 0, 7, 8);
        Assert.assertEquals(
                new IntMatrix(8, 3, 0, 6), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new IntMatrix(8, 3, 0, 6), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        other1 = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        other2 = new IntMatrix(2, 5, 0, 3, 3, 5, 6, 1, 7);
        other3 = new IntMatrix(12, 0, 7, 8, 3, 12, 1, 7, 5);
        Assert.assertEquals(
                new IntMatrix(8, 3, 0, 6, 3, 7, 3, 5, 6), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new IntMatrix(8, 3, 0, 6, 3, 7, 3, 5, 6), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new IntMatrix();
        other1 = new IntMatrix();
        other2 = new IntMatrix();
        other3 = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new IntMatrix(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        final IntMatrix otherF11 = new IntMatrix(10, 6, 1, 5);
        final IntMatrix otherF12 = new IntMatrix(2, 5, 0, 3);
        final IntMatrix otherF13 = new IntMatrix(12, 0, 7, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix otherF21 = new IntMatrix(10, 6, 1, 5);
        final IntMatrix otherF22 = new IntMatrix(2, 5, 0, 3, 3, 5, 6, 1, 7);
        final IntMatrix otherF23 = new IntMatrix(12, 0, 7, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix otherF31 = new IntMatrix(10, 6, 1, 5);
        final IntMatrix otherF33 = new IntMatrix(12, 0, 7, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(9, sut.sum().intValue());
        
        sut = new IntMatrix(9, 2, -5, 11);
        Assert.assertEquals(17, sut.sum().intValue());
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        Assert.assertEquals(47, sut.sum().intValue());
        
        sut = new IntMatrix();
        Assert.assertEquals(0, sut.sum().intValue());
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(81, sut.squareSum().intValue());
        
        sut = new IntMatrix(9, 2, -5, 11);
        Assert.assertEquals(231, sut.squareSum().intValue());
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        Assert.assertEquals(445, sut.squareSum().intValue());
        
        sut = new IntMatrix();
        Assert.assertEquals(0, sut.squareSum().intValue());
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        IntMatrix other;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        other = new IntMatrix(new int[] {10});
        Assert.assertEquals(
                new IntMatrix(new int[] {19}), sut.plus(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(10, 6, 1, 5);
        Assert.assertEquals(
                new IntMatrix(19, 8, -4, 16), sut.plus(other));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        other = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        Assert.assertEquals(
                new IntMatrix(19, 8, -4, 16, 6, 12, 8, 14, 13), sut.plus(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new IntMatrix(10, 3, -4, 12), sut.plus(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), sut.plus(other));
        
        sut = new IntMatrix();
        other = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.plus(other));
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix other1 = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        final IntMatrix other2 = new IntMatrix(10, 6, 1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new IntMatrix(new int[] {9});
        final IntMatrix other3 = new IntMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        IntMatrix other;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        other = new IntMatrix(new int[] {10});
        Assert.assertEquals(
                new IntMatrix(new int[] {-1}), sut.minus(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(10, 6, 1, 5);
        Assert.assertEquals(
                new IntMatrix(-1, -4, -6, 6), sut.minus(other));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        other = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        Assert.assertEquals(
                new IntMatrix(-1, -4, -6, 6, 2, 6, -4, 0, 3), sut.minus(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new IntMatrix(8, 1, -6, 10), sut.minus(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), sut.minus(other));
        
        sut = new IntMatrix();
        other = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.minus(other));
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix other1 = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        final IntMatrix other2 = new IntMatrix(10, 6, 1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new IntMatrix(new int[] {9});
        final IntMatrix other3 = new IntMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#times(IntMatrix)
     */
    @Test
    public void testTimes() throws Exception {
        IntMatrix other;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        other = new IntMatrix(new int[] {10});
        Assert.assertEquals(
                new IntMatrix(new int[] {90}), sut.times(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(10, 6, 1, 5);
        Assert.assertEquals(
                new IntMatrix(92, 64, -39, 25), sut.times(other));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        other = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        Assert.assertEquals(
                new IntMatrix(70, 23, -10, 184, 137, 68, 103, 82, 63), sut.times(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new IntMatrix(11, 11, 6, 6), sut.times(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0), sut.times(other));
        
        sut = new IntMatrix();
        other = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.times(other));
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix other1 = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        final IntMatrix other2 = new IntMatrix(10, 6, 1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new IntMatrix(new int[] {9});
        final IntMatrix other3 = new IntMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((IntMatrix) null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        IntVector other;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        other = new IntVector(new int[] {10});
        Assert.assertEquals(
                new IntVector(new int[] {90}), sut.times(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntVector(10, 6);
        Assert.assertEquals(
                new IntVector(102, 16), sut.times(other));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        other = new IntVector(10, 6, 1);
        Assert.assertEquals(
                new IntVector(97, 143, 70), sut.times(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntVector(1, 1);
        Assert.assertEquals(
                new IntVector(11, 6), sut.times(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntVector(0, 0);
        Assert.assertEquals(
                new IntVector(0, 0), sut.times(other));
        
        sut = new IntMatrix();
        other = new IntVector();
        Assert.assertEquals(
                new IntVector(), sut.times(other));
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntVector other1 = new IntVector(10, 6, 1, 5, 2, 3, 6, 7, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        final IntVector other2 = new IntVector(10, 6, 1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new IntMatrix(new int[] {9});
        final IntVector other3 = new IntVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((IntVector) null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(
                new IntMatrix(new int[] {36}), sut.scale(4));
        
        sut = new IntMatrix(9, 2, -5, 11);
        Assert.assertEquals(
                new IntMatrix(144, 32, -80, 176), sut.scale(16.24));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8), sut.scale(1.0046));
        
        sut = new IntMatrix(9, 2, -5, 11);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), sut.scale(1));
        
        sut = new IntMatrix(9, 2, -5, 11);
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0), sut.scale(0));
        
        sut = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.scale(7.1));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#scale(MatrixInterface)
     */
    @Test
    public void testMatrixScale() throws Exception {
        IntMatrix other;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        other = new IntMatrix(new int[] {10});
        Assert.assertEquals(
                new IntMatrix(new int[] {90}), sut.scale(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(10, 6, 1, 5);
        Assert.assertEquals(
                new IntMatrix(90, 12, -5, 55), sut.scale(other));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        other = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        Assert.assertEquals(
                new IntMatrix(90, 12, -5, 55, 8, 27, 12, 49, 40), sut.scale(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), sut.scale(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0), sut.scale(other));
        
        sut = new IntMatrix();
        other = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.scale(other));
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix other1 = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.scale(other1));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        final IntMatrix other2 = new IntMatrix(10, 6, 1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.scale(other2));
        
        sut = new IntMatrix(new int[] {9});
        final IntMatrix other3 = new IntMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.scale(other3));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.scale((IntMatrix) null));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        IntMatrix other;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        other = new IntMatrix(new int[] {10});
        Assert.assertEquals(
                new IntMatrix(new int[] {0}), sut.dividedBy(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(10, 6, 1, 5);
        Assert.assertEquals(
                new IntMatrix(0, 0, -5, 2), sut.dividedBy(other));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        other = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        Assert.assertEquals(
                new IntMatrix(0, 0, -5, 2, 2, 3, 0, 1, 1), sut.dividedBy(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        other = new IntMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), sut.dividedBy(other));
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix other0 = new IntMatrix(0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new IntMatrix();
        other = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.dividedBy(other));
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix other1 = new IntMatrix(10, 6, 1, 5, 2, 3, 6, 7, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        final IntMatrix other2 = new IntMatrix(10, 6, 1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new IntMatrix(new int[] {9});
        final IntMatrix other3 = new IntMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(
                new IntMatrix(new int[] {9}), sut.round());
        
        sut = new IntMatrix(9, 2, -5, 11);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), sut.round());
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8), sut.round());
        
        sut = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.round());
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(9, sut.determinant().intValue());
        
        sut = new IntMatrix(9, 4, -7, 9);
        Assert.assertEquals(109, sut.determinant().intValue());
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        Assert.assertEquals(-303, sut.determinant().intValue());
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        Assert.assertEquals(352, sut.determinant().intValue());
        
        sut = new IntMatrix(9, 2, 5, 11, 4, 9, 2, 7, 8, 1, 6, 7, 7, 0, 3, 8);
        Assert.assertEquals(-192, sut.determinant().intValue());
        
        sut = new IntMatrix();
        Assert.assertEquals(0, sut.determinant().intValue());
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#minor(int, int)
     * @see IntMatrix#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(0, sut.minor(0, 0).intValue());
        Assert.assertEquals(0, sut.minor(0).intValue());
        
        sut = new IntMatrix(9, 4, -7, 9);
        Assert.assertEquals(9, sut.minor(0, 0).intValue());
        Assert.assertEquals(-7, sut.minor(1, 0).intValue());
        Assert.assertEquals(9, sut.minor(1, 1).intValue());
        Assert.assertEquals(9, sut.minor(0).intValue());
        Assert.assertEquals(-7, sut.minor(1).intValue());
        Assert.assertEquals(9, sut.minor(3).intValue());
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        Assert.assertEquals(-18, sut.minor(0, 0).intValue());
        Assert.assertEquals(-19, sut.minor(1, 0).intValue());
        Assert.assertEquals(99, sut.minor(1, 2).intValue());
        Assert.assertEquals(-18, sut.minor(2, 2).intValue());
        Assert.assertEquals(-18, sut.minor(0).intValue());
        Assert.assertEquals(-19, sut.minor(1).intValue());
        Assert.assertEquals(99, sut.minor(7).intValue());
        Assert.assertEquals(-18, sut.minor(8).intValue());
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        Assert.assertEquals(24, sut.minor(0, 0).intValue());
        Assert.assertEquals(22, sut.minor(1, 0).intValue());
        Assert.assertEquals(33, sut.minor(1, 2).intValue());
        Assert.assertEquals(87, sut.minor(2, 2).intValue());
        Assert.assertEquals(24, sut.minor(0).intValue());
        Assert.assertEquals(22, sut.minor(1).intValue());
        Assert.assertEquals(33, sut.minor(7).intValue());
        Assert.assertEquals(87, sut.minor(8).intValue());
        
        //invalid
        
        sut = new IntMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 0, 0), () ->
                sut.minor(0, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.minor(0));
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 51, 6), () ->
                sut.minor(51, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, -1), () ->
                sut.minor(2, -1));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 189), () ->
                sut.minor(189));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.minor(-1));
    }
    
    /**
     * JUnit test of minors.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#minors()
     */
    @Test
    public void testMinors() throws Exception {
        //standard
        
        sut = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.minors());
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(
                new IntMatrix(new int[] {0}), sut.minors());
        
        sut = new IntMatrix(9, 4, -7, 9);
        Assert.assertEquals(
                new IntMatrix(9, -7, 4, 9), sut.minors());
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        Assert.assertEquals(
                new IntMatrix(-18, -19, 31, 39, 58, 17, 30, 99, -18), sut.minors());
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        Assert.assertEquals(
                new IntMatrix(24, 22, 26, 40, 66, 14, -28, 33, 87), sut.minors());
    }
    
    /**
     * JUnit test of cofactorScalar.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#cofactorScalar(int, int)
     * @see IntMatrix#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(1, sut.cofactorScalar(0, 0).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(0).intValue());
        
        sut = new IntMatrix(9, 4, -7, 9);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(1, 0).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(1, 1).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(0).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(1).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(3).intValue());
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(1, 0).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(1, 2).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(2, 2).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(0).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(1).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(7).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(8).intValue());
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(1, 0).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(1, 2).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(2, 2).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(0).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(1).intValue());
        Assert.assertEquals(-1, sut.cofactorScalar(7).intValue());
        Assert.assertEquals(1, sut.cofactorScalar(8).intValue());
        
        //invalid
        
        sut = new IntMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 0, 0), () ->
                sut.cofactorScalar(0, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.cofactorScalar(0));
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 51, 6), () ->
                sut.cofactorScalar(51, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, -1), () ->
                sut.cofactorScalar(2, -1));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 189), () ->
                sut.cofactorScalar(189));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.cofactorScalar(-1));
    }
    
    /**
     * JUnit test of cofactor.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        //standard
        
        sut = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.cofactor());
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(
                new IntMatrix(new int[] {9}), sut.cofactor());
        
        sut = new IntMatrix(9, 4, -7, 9);
        Assert.assertEquals(
                new IntMatrix(9, -4, 7, 9), sut.cofactor());
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        Assert.assertEquals(
                new IntMatrix(9, -4, -7, -9, 2, -4, 7, -5, 1), sut.cofactor());
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        Assert.assertEquals(
                new IntMatrix(15, -11, 9, -3, 8, -4, -4, 2, 2), sut.cofactor());
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        //standard
        
        sut = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.transpose());
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(
                new IntMatrix(new int[] {9}), sut.transpose());
        
        sut = new IntMatrix(9, 4, -7, 9);
        Assert.assertEquals(
                new IntMatrix(9, -7, 4, 9), sut.transpose());
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        Assert.assertEquals(
                new IntMatrix(9, 9, 7, 4, 2, 5, -7, 4, 1), sut.transpose());
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        Assert.assertEquals(
                new IntMatrix(15, 3, -4, 11, 8, -2, 9, 4, 2), sut.transpose());
    }
    
    /**
     * JUnit test of adjoint.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
        //standard
        
        sut = new IntMatrix();
        Assert.assertEquals(
                new IntMatrix(), sut.adjoint());
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(
                new IntMatrix(new int[] {0}), sut.adjoint());
        
        sut = new IntMatrix(9, 4, -7, 9);
        Assert.assertEquals(
                new IntMatrix(9, -4, 7, 9), sut.adjoint());
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        Assert.assertEquals(
                new IntMatrix(-18, -39, 30, 19, 58, -99, 31, -17, -18), sut.adjoint());
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        Assert.assertEquals(
                new IntMatrix(24, -40, -28, -22, 66, -33, 26, -14, 87), sut.adjoint());
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        //standard
        
        sut = new IntMatrix(new int[] {9});
        Assert.assertEquals(
                new IntMatrix(new int[] {0}), sut.inverse());
        
        sut = new IntMatrix(90, 40, -70, 90);
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0), sut.inverse());
        
        sut = new IntMatrix(90, 40, -70, 90, 20, 40, 70, 50, 10);
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), sut.inverse());
        
        sut = new IntMatrix(150, 110, 90, 30, 80, 40, -40, -20, 20);
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), sut.inverse());
        
        //invalid
        
        sut = new IntMatrix();
        TestUtils.assertException(ArithmeticException.class, "The Integer Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
        
        sut = new IntMatrix(3, 2, 1, 1, 1, 1, 1, 2, 3);
        TestUtils.assertException(ArithmeticException.class, "The Integer Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        IntVector solution;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        solution = new IntVector(new int[] {4});
        Assert.assertEquals(
                new IntVector(new int[] {0}), sut.solveSystem(solution));
        
        sut = new IntMatrix(9, 4, -7, 9);
        solution = new IntVector(4, 2);
        Assert.assertEquals(
                new IntVector(0, 0), sut.solveSystem(solution));
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        solution = new IntVector(4, 20, 2);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.solveSystem(solution));
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        solution = new IntVector(4, 20, 2);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.solveSystem(solution));
        
        //invalid
        
        sut = new IntMatrix();
        final IntVector solution1 = new IntVector();
        TestUtils.assertException(ArithmeticException.class, "The Integer Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution1));
        
        sut = new IntMatrix(3, 2, 1, 1, 1, 1, 1, 2, 3);
        final IntVector solution2 = new IntVector(4, 20, 2);
        TestUtils.assertException(ArithmeticException.class, "The Integer Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution2));
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        final IntVector solution3 = new IntVector(4, 20);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution3), () ->
                sut.solveSystem(solution3));
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        final IntVector solution4 = new IntVector(4, 20, 2, 0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution4), () ->
                sut.solveSystem(solution4));
    }
    
    /**
     * JUnit test of transform.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        IntVector vector;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        vector = new IntVector(new int[] {4});
        Assert.assertEquals(
                new IntVector(new int[] {36}), sut.transform(vector));
        
        sut = new IntMatrix(9, 4, -7, 9);
        vector = new IntVector(4, 20);
        Assert.assertEquals(
                new IntVector(-104, 196), sut.transform(vector));
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        vector = new IntVector(4, 20, 2);
        Assert.assertEquals(
                new IntVector(230, 66, 54), sut.transform(vector));
        
        sut = new IntMatrix(15, 11, 9, 3, 8, 4, -4, -2, 2);
        vector = new IntVector(4, 20, 2);
        Assert.assertEquals(
                new IntVector(112, 200, 120), sut.transform(vector));
        
        //invalid
        
        sut = new IntMatrix();
        final IntVector solution1 = new IntVector();
        TestUtils.assertNoException(() ->
                sut.transform(solution1));
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        final IntVector solution3 = new IntVector(4, 20);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution3), () ->
                sut.transform(solution3));
        
        sut = new IntMatrix(9, 4, -7, 9, 2, 4, 7, 5, 1);
        final IntVector solution4 = new IntVector(4, 20, 2, 0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution4), () ->
                sut.transform(solution4));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        IntMatrix copy;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        copy = new IntMatrix(1);
        sut.copy(copy);
        Assert.assertEquals(
                new IntMatrix(new int[] {9}), copy);
        
        sut = new IntMatrix(9, 2, -5, 11);
        copy = new IntMatrix(2);
        sut.copy(copy);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), copy);
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        copy = new IntMatrix(3);
        sut.copy(copy);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8), copy);
        
        sut = new IntMatrix();
        copy = new IntMatrix();
        sut.copy(copy);
        Assert.assertEquals(
                new IntMatrix(), copy);
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix copy1 = new IntMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        final IntMatrix copy2 = new IntMatrix(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                sut.copy(copy2));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#copyMeta(IntMatrix)
     */
    @Test
    public void testCopyMeta() throws Exception {
        IntMatrix component1 = new IntMatrix(8, 7, 7, 3);
        Assert.assertEquals(2, component1.getDimensionality());
        TestUtils.assertArrayEquals(
                component1.getRawComponents(),
                new Integer[] {8, 7, 7, 3});
        
        IntMatrix component2 = new IntMatrix(9, 6, 2, 8);
        component1.copyMeta(component2);
        Assert.assertEquals(2, component2.getDimensionality());
        TestUtils.assertArrayEquals(
                component2.getRawComponents(),
                new Integer[] {9, 6, 2, 8});
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new IntMatrix(9, 2, -5, 11);
        sut.redim(3);
        Assert.assertEquals(
                new IntMatrix(9, 2, 0, -5, 11, 0, 0, 0, 0), sut);
        
        sut = new IntMatrix(9, 2, -5, 11);
        sut.redim(4);
        Assert.assertEquals(
                new IntMatrix(9, 2, 0, 0, -5, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), sut);
        
        sut = new IntMatrix(9, 2, -5, 11);
        sut.redim(2);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), sut);
        
        sut = new IntMatrix(9, 2, -5, 11);
        sut.redim(1);
        Assert.assertEquals(
                new IntMatrix(new int[] {9}), sut);
        
        sut = new IntMatrix(9, 2, -5, 11);
        sut.redim(0);
        Assert.assertEquals(
                new IntMatrix(), sut);
        
        sut = new IntMatrix();
        sut.redim(3);
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), sut);
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        sut.redim(-1);
        Assert.assertEquals(
                new IntMatrix(), sut);
        
        sut = Mockito.spy(IntMatrix.class);
        sut.setComponents(new Integer[] {9, 2, -5, 11});
        Mockito.when(sut.isResizeable()).thenReturn(false);
        Assert.assertFalse(sut.isResizeable());
        sut.redim(3);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), sut);
    }
    
    /**
     * JUnit test of subMatrix.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#subMatrix(int, int, int, int)
     */
    @Test
    public void testSubMatrix() throws Exception {
        //standard
        
        IntMatrix subMatrix;
        sut = new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        
        subMatrix = sut.subMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new IntMatrix(new int[] {1}), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 1, 1);
        Assert.assertEquals(
                new IntMatrix(1, 2, 6, 7), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 2, 2);
        Assert.assertEquals(
                new IntMatrix(1, 2, 3, 6, 7, 8, 11, 12, 13), subMatrix);
        
        subMatrix = sut.subMatrix(2, 1, 4, 3);
        Assert.assertEquals(
                new IntMatrix(8, 9, 10, 13, 14, 15, 18, 19, 20), subMatrix);
        
        subMatrix = sut.subMatrix(1, 1, 4, 4);
        Assert.assertEquals(
                new IntMatrix(7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 4, 4);
        Assert.assertEquals(
                new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25), subMatrix);
        
        //dimensionality
        
        sut = new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        
        subMatrix = sut.subMatrix(0, 0, 1);
        Assert.assertEquals(
                new IntMatrix(new int[] {1}), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 2);
        Assert.assertEquals(
                new IntMatrix(1, 2, 6, 7), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 3);
        Assert.assertEquals(
                new IntMatrix(1, 2, 3, 6, 7, 8, 11, 12, 13), subMatrix);
        
        subMatrix = sut.subMatrix(2, 1, 3);
        Assert.assertEquals(
                new IntMatrix(8, 9, 10, 13, 14, 15, 18, 19, 20), subMatrix);
        
        subMatrix = sut.subMatrix(1, 1, 4);
        Assert.assertEquals(
                new IntMatrix(7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 5);
        Assert.assertEquals(
                new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25), subMatrix);
        
        //invalid
        
        sut = new IntMatrix(4);
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 0, 0), () ->
                sut.subMatrix(1, 1, 0, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 2, 2, 3, 1), () ->
                sut.subMatrix(2, 2, 3, 1));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, -1, -1), () ->
                sut.subMatrix(1, 1, -1, -1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 6, 6), () ->
                sut.subMatrix(1, 1, 6, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, -2, -2, 2, 2), () ->
                sut.subMatrix(-2, -2, 2, 2));
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Integer[] {0, 0, 0, 0, 0, 0}), () ->
                sut.subMatrix(0, 0, 2, 1));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Integer[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}), () ->
                sut.subMatrix(0, 0, 2, 3));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 0, 0), () ->
                sut.subMatrix(1, 1, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 2, 2, 11, 11), () ->
                sut.subMatrix(2, 2, 10));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 3, 3, 2, 2), () ->
                sut.subMatrix(3, 3, -1));
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#dimensionalityToLength(int)
     * @see IntMatrix#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        
        //standard
        Assert.assertEquals(9, sut.dimensionalityToLength());
        Assert.assertEquals(9, sut.dimensionalityToLength(3));
        Assert.assertEquals(25, sut.dimensionalityToLength(5));
        Assert.assertEquals(1, sut.dimensionalityToLength(1));
        Assert.assertEquals(0, sut.dimensionalityToLength(0));
        
        //invalid
        Assert.assertEquals(0, sut.dimensionalityToLength(-1));
        Assert.assertEquals(0, sut.dimensionalityToLength(-3));
    }
    
    /**
     * JUnit test of dimensionalityToWidth.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#dimensionalityToWidth(int)
     * @see IntMatrix#dimensionalityToWidth()
     */
    @Test
    public void testDimensionalityToWidth() throws Exception {
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        
        //standard
        Assert.assertEquals(3, sut.dimensionalityToWidth());
        Assert.assertEquals(3, sut.dimensionalityToWidth(3));
        Assert.assertEquals(5, sut.dimensionalityToWidth(5));
        Assert.assertEquals(1, sut.dimensionalityToWidth(1));
        Assert.assertEquals(0, sut.dimensionalityToWidth(0));
        
        //invalid
        Assert.assertEquals(0, sut.dimensionalityToWidth(-1));
        Assert.assertEquals(0, sut.dimensionalityToWidth(-3));
    }
    
    /**
     * JUnit test of dimensionalityToHeight.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#dimensionalityToHeight(int)
     * @see IntMatrix#dimensionalityToHeight()
     */
    @Test
    public void testDimensionalityToHeight() throws Exception {
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        
        //standard
        Assert.assertEquals(3, sut.dimensionalityToHeight());
        Assert.assertEquals(3, sut.dimensionalityToHeight(3));
        Assert.assertEquals(5, sut.dimensionalityToHeight(5));
        Assert.assertEquals(1, sut.dimensionalityToHeight(1));
        Assert.assertEquals(0, sut.dimensionalityToHeight(0));
        
        //invalid
        Assert.assertEquals(0, sut.dimensionalityToHeight(-1));
        Assert.assertEquals(0, sut.dimensionalityToHeight(-3));
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#lengthToDimensionality(int)
     * @see IntMatrix#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        
        //standard
        Assert.assertEquals(3, sut.lengthToDimensionality());
        Assert.assertEquals(3, sut.lengthToDimensionality(9));
        Assert.assertEquals(5, sut.lengthToDimensionality(25));
        Assert.assertEquals(1, sut.lengthToDimensionality(1));
        Assert.assertEquals(0, sut.lengthToDimensionality(0));
        
        //invalid
        Assert.assertEquals(0, sut.lengthToDimensionality(-1));
        Assert.assertEquals(0, sut.lengthToDimensionality(-3));
    }
    
    /**
     * JUnit test of calculateDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        
        //standard
        Assert.assertEquals(3, sut.getDimensionality());
        TestAccess.setFieldValue(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(3, sut.getDimensionality());
    }
    
    /**
     * JUnit test of prettyPrint.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
        //standard
        
        List<String> print;
        
        sut = new IntMatrix();
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(1, print.size());
        Assert.assertEquals("[]", print.get(0));
        
        sut = new IntMatrix(new int[] {1});
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(1, print.size());
        Assert.assertEquals("[  1  ]", print.get(0));
        
        sut = new IntMatrix(1, 2, 3, 4);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(2, print.size());
        Assert.assertEquals("┌  1   2  ┐", print.get(0));
        Assert.assertEquals("└  3   4  ┘", print.get(1));
        
        sut = new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(3, print.size());
        Assert.assertEquals("┌  1   2   3  ┐", print.get(0));
        Assert.assertEquals("│  4   5   6  │", print.get(1));
        Assert.assertEquals("└  7   8   9  ┘", print.get(2));
        
        sut = new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(4, print.size());
        Assert.assertEquals("┌  1    2    3    4   ┐", print.get(0));
        Assert.assertEquals("│  5    6    7    8   │", print.get(1));
        Assert.assertEquals("│  9    10   11   12  │", print.get(2));
        Assert.assertEquals("└  13   14   15   16  ┘", print.get(3));
        
        sut = new IntMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(5, print.size());
        Assert.assertEquals("┌  1    2    3    4    5   ┐", print.get(0));
        Assert.assertEquals("│  6    7    8    9    10  │", print.get(1));
        Assert.assertEquals("│  11   12   13   14   15  │", print.get(2));
        Assert.assertEquals("│  16   17   18   19   20  │", print.get(3));
        Assert.assertEquals("└  21   22   23   24   25  ┘", print.get(4));
        
        sut = new IntMatrix(8, 3, 1, 9, 4, 10, 16, 45, 7);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(3, print.size());
        Assert.assertEquals("┌  8    3    1   ┐", print.get(0));
        Assert.assertEquals("│  9    4    10  │", print.get(1));
        Assert.assertEquals("└  16   45   7   ┘", print.get(2));
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        IntMatrix component;
        
        //standard
        component = new IntMatrix(8, 7, 7, 3);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Integer[] {8, 7, 7, 3});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Integer[] {8, 7, 7, 3});
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        IntMatrix component;
        
        //standard
        
        component = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Integer[] {9, 2, -5, 11});
        
        component = new IntMatrix(new int[] {9});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Integer[] {9});
        
        component = new IntMatrix();
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Integer[] {});
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        IntMatrix component;
        
        //standard
        component = new IntMatrix(8, 7, 7, 3);
        TestUtils.assertArrayEquals(
                Arrays.stream(component.getPrimitiveComponents()).boxed().toArray(),
                new Integer[] {8, 7, 7, 3});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Integer[] {8, 7, 7, 3});
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getRaw(int)
     * @see IntMatrix#getRaw(int, int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new IntMatrix(9, 2, -5, 11);
        Assert.assertEquals(9, sut.getRaw(0).intValue());
        Assert.assertEquals(2, sut.getRaw(1).intValue());
        Assert.assertEquals(-5, sut.getRaw(2).intValue());
        Assert.assertEquals(11, sut.getRaw(3).intValue());
        Assert.assertEquals(2, sut.getRaw(1, 0).intValue());
        Assert.assertEquals(11, sut.getRaw(1, 1).intValue());
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.getRaw(4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.getRaw(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 3), () ->
                sut.getRaw(1, 3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, 2), () ->
                sut.getRaw(2, 2));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 8, 9), () ->
                sut.getRaw(8, 9));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -1, -2), () ->
                sut.getRaw(-1, -2));
        
        sut = new IntMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.getRaw(0));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#get(int)
     * @see IntMatrix#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new IntMatrix(9, 2, -5, 11);
        Assert.assertEquals(9, sut.get(0).intValue());
        Assert.assertEquals(2, sut.get(1).intValue());
        Assert.assertEquals(-5, sut.get(2).intValue());
        Assert.assertEquals(11, sut.get(3).intValue());
        Assert.assertEquals(2, sut.get(1, 0).intValue());
        Assert.assertEquals(11, sut.get(1, 1).intValue());
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.get(4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.get(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 3), () ->
                sut.get(1, 3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, 2), () ->
                sut.get(2, 2));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 8, 9), () ->
                sut.get(8, 9));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -1, -2), () ->
                sut.get(-1, -2));
        
        sut = new IntMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.get(0));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new IntMatrix(6, 8, 3, -2);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new IntMatrix(new int[] {6});
        Assert.assertEquals(1, sut.getDimensionality());
        
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        Assert.assertEquals(3, sut.getDimensionality());
        
        sut = new IntMatrix();
        Assert.assertEquals(0, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        //standard
        
        sut = new IntMatrix(6, 8, 3, -2);
        Assert.assertEquals(2, sut.getHeight());
        
        sut = new IntMatrix(new int[] {6});
        Assert.assertEquals(1, sut.getHeight());
        
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        Assert.assertEquals(3, sut.getHeight());
        
        sut = new IntMatrix();
        Assert.assertEquals(0, sut.getHeight());
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        //standard
        
        sut = new IntMatrix(6, 8, 3, -2);
        Assert.assertEquals(2, sut.getWidth());
        
        sut = new IntMatrix(new int[] {6});
        Assert.assertEquals(1, sut.getWidth());
        
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        Assert.assertEquals(3, sut.getWidth());
        
        sut = new IntMatrix();
        Assert.assertEquals(0, sut.getWidth());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new IntMatrix(6, 8, 3, -2);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new IntMatrix(new int[] {6});
        Assert.assertEquals(1, sut.getLength());
        
        sut = new IntMatrix(1, 2, 1, -9, 2, 8, -1, 5, 4);
        Assert.assertEquals(9, sut.getLength());
        
        sut = new IntMatrix();
        Assert.assertEquals(0, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new IntMatrix();
        Assert.assertEquals(IntMatrix.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new IntMatrix();
        Assert.assertEquals(Integer.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new IntMatrix();
        Assert.assertTrue(sut.getHandler() instanceof IntComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new IntMatrix();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new IntMatrix();
        Assert.assertEquals("Integer Matrix", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new IntMatrix();
        Assert.assertEquals("Integer Matrices", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new IntMatrix();
        Assert.assertEquals(1, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new IntMatrix();
        Assert.assertTrue(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        IntMatrix component;
        Integer[] newComponents;
        
        //standard
        
        component = new IntMatrix(6, 3, -2, 9);
        newComponents = new Integer[] {6, 7, 8, 9};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Integer[] {6, 7, 8, 9});
        Assert.assertEquals(2, component.getDimensionality());
        
        component = new IntMatrix(6, 3, -2, 9);
        newComponents = new Integer[] {1, 2, 1, -9, 2, 8, -1, 5, 4};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Integer[] {1, 2, 1, -9, 2, 8, -1, 5, 4});
        Assert.assertEquals(3, component.getDimensionality());
        
        component = new IntMatrix(6, 3, -2, 9);
        newComponents = new Integer[] {};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Integer[] {});
        Assert.assertEquals(0, component.getDimensionality());
        
        //invalid
        
        final IntMatrix component2 = new IntMatrix(6, 3, -2, 9);
        Assert.assertTrue(component2.isResizeable());
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#set(int, Number)
     * @see IntMatrix#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Integer[] {9, 2, -5, 11});
        sut.set(0, 6);
        sut.set(1, 3);
        sut.set(2, 1);
        sut.set(3, 5);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Integer[] {6, 3, 1, 5});
        
        sut = new IntMatrix(new int[] {9});
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Integer[] {9});
        sut.set(0, 6);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Integer[] {6});
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Integer[] {9, 2, -5, 11});
        sut.set(0, 0, 6);
        sut.set(1, 0, 3);
        sut.set(0, 1, 1);
        sut.set(1, 1, 5);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Integer[] {6, 3, 1, 5});
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.set(4, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 6));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 3), () ->
                sut.set(1, 3, 5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, 2), () ->
                sut.set(2, 2, 5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 8, 9), () ->
                sut.set(8, 9, 5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -1, -2), () ->
                sut.set(-1, -2, 5));
        
        sut = new IntMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.set(0, 6));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        IntMatrix copy;
        
        //standard
        
        sut = new IntMatrix(new int[] {9});
        copy = new IntMatrix(1);
        IntMatrix.copy(sut, copy);
        Assert.assertEquals(
                new IntMatrix(new int[] {9}), copy);
        
        sut = new IntMatrix(9, 2, -5, 11);
        copy = new IntMatrix(2);
        IntMatrix.copy(sut, copy);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11), copy);
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        copy = new IntMatrix(3);
        IntMatrix.copy(sut, copy);
        Assert.assertEquals(
                new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8), copy);
        
        sut = new IntMatrix();
        copy = new IntMatrix();
        IntMatrix.copy(sut, copy);
        Assert.assertEquals(
                new IntMatrix(), copy);
        
        //invalid
        
        sut = new IntMatrix(9, 2, -5, 11);
        final IntMatrix copy1 = new IntMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                IntMatrix.copy(sut, copy1));
        
        sut = new IntMatrix(9, 2, -5, 11, 4, 9, 2, 7, 8);
        final IntMatrix copy2 = new IntMatrix(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                IntMatrix.copy(sut, copy2));
        
        sut = new IntMatrix(9, 2, -5, 11);
        final BigVector copy3 = new BigVector(4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy3), () ->
                IntMatrix.copy(sut, copy3));
        
        sut = new IntMatrix(9, 2, -5, 11);
        final BigMatrix copy4 = new BigMatrix(4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy4), () ->
                IntMatrix.copy(sut, copy4));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                IntMatrix.copy(sut, null));
        
        sut = new IntMatrix(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                IntMatrix.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new IntMatrix(), IntMatrix.createInstance(0));
        Assert.assertEquals(
                new IntMatrix(new int[] {0}), IntMatrix.createInstance(1));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0), IntMatrix.createInstance(2));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), IntMatrix.createInstance(3));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), IntMatrix.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new IntMatrix(), IntMatrix.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new IntMatrix(), IntMatrix.identity(0));
        Assert.assertEquals(
                new IntMatrix(new int[] {1}), IntMatrix.identity(1));
        Assert.assertEquals(
                new IntMatrix(1, 0, 0, 1), IntMatrix.identity(2));
        Assert.assertEquals(
                new IntMatrix(1, 0, 0, 0, 1, 0, 0, 0, 1), IntMatrix.identity(3));
        Assert.assertEquals(
                new IntMatrix(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1), IntMatrix.identity(4));
        
        //invalid
        Assert.assertEquals(
                new IntMatrix(), IntMatrix.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new IntMatrix(), IntMatrix.origin(0));
        Assert.assertEquals(
                new IntMatrix(new int[] {0}), IntMatrix.origin(1));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0), IntMatrix.origin(2));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), IntMatrix.origin(3));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), IntMatrix.origin(4));
        
        //invalid
        Assert.assertEquals(
                new IntMatrix(), IntMatrix.origin(-1));
    }
    
    /**
     * JUnit test of signChart.
     *
     * @throws Exception When there is an exception.
     * @see IntMatrix#signChart(int)
     */
    @Test
    public void testSignChart() throws Exception {
        //standard
        Assert.assertEquals(
                new IntMatrix(), IntMatrix.signChart(0));
        Assert.assertEquals(
                new IntMatrix(new int[] {1}), IntMatrix.signChart(1));
        Assert.assertEquals(
                new IntMatrix(1, -1, -1, 1), IntMatrix.signChart(2));
        Assert.assertEquals(
                new IntMatrix(1, -1, 1, -1, 1, -1, 1, -1, 1), IntMatrix.signChart(3));
        Assert.assertEquals(
                new IntMatrix(1, -1, 1, -1, -1, 1, -1, 1, 1, -1, 1, -1, -1, 1, -1, 1), IntMatrix.signChart(4));
        
        //invalid
        Assert.assertEquals(
                new IntMatrix(), IntMatrix.signChart(-1));
    }
    
}
