/*
 * File:    Matrix2Test.java
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
import commons.math.component.Component;
import commons.math.component.ComponentInterface;
import commons.math.component.handler.error.ComponentErrorHandlerInterface;
import commons.math.component.handler.error.ComponentErrorHandlerProvider;
import commons.math.component.handler.math.DoubleComponentMathHandler;
import commons.math.component.vector.BigVector;
import commons.math.component.vector.IntVector;
import commons.math.component.vector.Vector;
import commons.math.component.vector.Vector2;
import commons.math.component.vector.VectorInterface;
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
 * JUnit test of Matrix2.
 *
 * @see Matrix2
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Matrix2.class})
public class Matrix2Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Matrix2Test.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private Matrix2 sut;
    
    
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
     * @see Matrix2#DIMENSIONALITY
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(2, Matrix2.DIMENSIONALITY);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#Matrix2(double...)
     * @see Matrix2#Matrix2()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        Matrix2 matrix = new Matrix2(0.884, 2, 1.1, -9.3);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1, -9.3}, matrix.getRawComponents());
        Assert.assertEquals(2, matrix.getDimensionality());
        
        //empty
        Matrix2 matrixDefault = new Matrix2();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, matrixDefault.getRawComponents());
        Assert.assertEquals(2, matrixDefault.getDimensionality());
        
        //dimensionality (ignored)
        Matrix2 matrixDimensionality = new Matrix2(8);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, matrixDimensionality.getRawComponents());
        Assert.assertEquals(2, matrixDimensionality.getDimensionality());
        
        //equality
        Assert.assertEquals(matrixDefault, matrixDimensionality);
        
        //invalid
        TestUtils.assertException(ArithmeticException.class, new Matrix2().getErrorHandler().componentLengthNotSquareErrorMessage(new Double[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06}), () ->
                new Matrix2(0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06));
        TestUtils.assertException(ArithmeticException.class, "The 2D Matrix: [<0.884, 2.0, 1.1>, <-9.3, 1.61, 8.0>, <-0.77, 5.06, 4.4>] does not have the expected dimensionality of: 2", () ->
                new Matrix2(0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06, 4.4));
    }
    
    /**
     * JUnit test of matrixString.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#matrixString()
     */
    @Test
    public void testMatrixString() throws Exception {
        //standard
        
        sut = new Matrix2(1, 2, 3, 4);
        Assert.assertEquals("[<1.0, 2.0>, <3.0, 4.0>]", sut.matrixString());
        
        sut = new Matrix2(8.15, 77.1654, 3, 3.66);
        Assert.assertEquals("[<8.15, 77.1654>, <3.0, 3.66>]", sut.matrixString());
        
        //empty
        
        sut = new Matrix2();
        Assert.assertEquals("[<0.0, 0.0>, <0.0, 0.0>]", sut.matrixString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new Matrix2(1, 2, 3, 4);
        Assert.assertEquals("[<1.0, 2.0>, <3.0, 4.0>]", sut.toString());
        
        sut = new Matrix2(8.15, 77.1654, 3, 3.66);
        Assert.assertEquals("[<8.15, 77.1654>, <3.0, 3.66>]", sut.toString());
        
        //empty
        
        sut = new Matrix2();
        Assert.assertEquals("[<0.0, 0.0>, <0.0, 0.0>]", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix2(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.equals(other));
        
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
     * @see Matrix2#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix2(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
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
     * @see Matrix2#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix2(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
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
     * @see Matrix2#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix2(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new Matrix().componentTypeEqual(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new Matrix2(8.1, 6.6, 5, 1.09);
        Matrix clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotSame(sut, clone);
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new Matrix2(8.1, 6.6, 5, 1.09);
        Matrix emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(Matrix.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotSame(sut, emptyCopy);
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
        //standard
        
        sut = new Matrix2();
        Assert.assertEquals(
                new Vector(0.0, 0.0), sut.newVector());
        
        sut = new Matrix2(8.1, 6.6, 5, 1.09);
        Assert.assertEquals(
                new Vector(0.0, 0.0), sut.newVector());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new Matrix2();
        
        //standard
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(0));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
        //standard
        
        sut = new Matrix2(1, 2, 3, 4);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        Assert.assertEquals(1, sut.toIndex(1, 0));
        Assert.assertEquals(3, sut.toIndex(1, 1));
        
        //invalid
        
        sut = new Matrix2();
        Assert.assertEquals(27, sut.toIndex(9, 9));
        
        sut = new Matrix2(1, 2, 3, 4);
        Assert.assertEquals(5, sut.toIndex(3, 1));
        Assert.assertEquals(27, sut.toIndex(9, 9));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        Matrix reversed;
        
        //standard
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {-9.3, 1.1, 2.0, 0.884}, reversed.getRawComponents());
        
        sut = new Matrix2();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        other = new Matrix2(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(17.299469702855056, sut.distance(other), TestUtils.DELTA);
        
        sut = new Matrix2();
        other = new Matrix2();
        Assert.assertEquals(0.0, sut.distance(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        final Matrix other1 = new Matrix(9.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        final Matrix other2 = new Matrix(0.884, 2, 1.1, -9.3, 0.18, 2.37, 3.8, 9, 6.6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        other = new Matrix2(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Matrix2(5.392, 4.0, 0.807, -2.2), sut.midpoint(other));
        
        sut = new Matrix2();
        other = new Matrix2();
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.midpoint(other));
        
        //invalid
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        final Matrix other1 = new Matrix(9.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        final Matrix other2 = new Matrix(0.884, 2, 1.1, -9.3, 0.18, 2.37, 3.8, 9, 6.6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#average(List)
     * @see Matrix2#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        Matrix other1;
        Matrix other2;
        Matrix other3;
        Matrix other4;
        
        //standard
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        other1 = new Matrix2(9.9, 6, 0.514, 4.9);
        other2 = new Matrix2(1.8, 4.77, 0.514, 2.895);
        other3 = new Matrix2(11.7, 0.447, 7.16, 8);
        Assert.assertEquals(
                new Matrix2(6.071, 3.30425, 2.322, 1.62375), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Matrix2(6.071, 3.30425, 2.322, 1.62375), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new Matrix2();
        other1 = new Matrix2();
        other2 = new Matrix2();
        other3 = new Matrix2();
        Assert.assertEquals(
                new Matrix2(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Matrix2(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        final Matrix otherF11 = new Matrix(9.9, 6, 0.514, 4.9, 6, 0.514, 4.9, 1.8, 3);
        final Matrix otherF12 = new Matrix(1.8, 4.77, 0.514, 2.895);
        final Matrix otherF13 = new Matrix(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        final Matrix otherF21 = new Matrix(9.9, 6, 0.514, 4.9);
        final Matrix otherF22 = new Matrix(1.8);
        final Matrix otherF23 = new Matrix(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        final Matrix otherF31 = new Matrix(9.9, 6, 0.514, 4.9);
        final Matrix otherF33 = new Matrix(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new Matrix2(0.884, 2, 1.1, -9.3);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(16.094, sut.sum(), TestUtils.DELTA);
        
        sut = new Matrix2();
        Assert.assertEquals(0, sut.sum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(222.770036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Matrix2();
        Assert.assertEquals(0, sut.squareSum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Matrix2(18.4, 7.5, -4.492, 16.0), sut.plus(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix2(9.5, 2.5, -4.006, 12.1), sut.plus(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), sut.plus(other));
        
        sut = new Matrix2();
        other = new Matrix2();
        Assert.assertEquals(
                new Matrix2(), sut.plus(other));
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 6, 0.514, 4.9, 1.8, 3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other2 = new Matrix(9.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Matrix2(-1.4, -4.5, -5.52, 6.2), sut.minus(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix2(7.5, 0.5, -6.006, 10.1), sut.minus(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), sut.minus(other));
        
        sut = new Matrix2();
        other = new Matrix2();
        Assert.assertEquals(
                new Matrix2(), sut.minus(other));
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 6, 0.514, 4.9, 1.8, 3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other2 = new Matrix(9.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#times(Matrix)
     */
    @Test
    public void testTimes() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Matrix2(84.921, 58.35, -43.854, 24.354), sut.times(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix2(10.0, 10.0, 6.094, 6.094), sut.times(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.times(other));
        
        sut = new Matrix2();
        other = new Matrix2();
        Assert.assertEquals(
                new Matrix2(), sut.times(other));
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 6, 0.514, 4.9, 1.8, 3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other2 = new Matrix(9.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((Matrix) null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(93.15, 17.0406), sut.times(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Vector2(1, 1);
        Assert.assertEquals(
                new Vector2(10.0, 6.094), sut.times(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Vector2(0, 0);
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.times(other));
        
        sut = new Matrix2();
        other = new Vector2();
        Assert.assertEquals(
                new Vector2(), sut.times(other));
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6, 0.514, 4.4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Vector other2 = new Vector(9.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Vector other3 = new Vector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((Vector) null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(34.0, 6.0, -20.024, 44.4), sut.scale(4));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(138.04, 24.36, -81.29744, 180.264), sut.scale(16.24));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(8.5391, 1.5069, -5.0290276, 11.15106), sut.scale(1.0046));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), sut.scale(1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.scale(0));
        
        sut = new Matrix2();
        Assert.assertEquals(
                new Matrix2(), sut.scale(7.1));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#scale(MatrixInterface)
     */
    @Test
    public void testMatrixScale() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Matrix2(84.15, 9.0, -2.573084, 54.39), sut.scale(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), sut.scale(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.scale(other));
        
        sut = new Matrix2();
        other = new Matrix2();
        Assert.assertEquals(
                new Matrix2(), sut.scale(other));
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 6, 0.514, 4.9, 1.8, 3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.scale(other1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other2 = new Matrix(9.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.scale(other2));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.scale(other3));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.scale((Matrix) null));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Matrix2(0.858585858586, 0.25, -9.739299610895, 2.265306122449), sut.dividedBy(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        other = new Matrix2(1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), sut.dividedBy(other));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix2 other0 = new Matrix2(0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new Matrix2();
        final Matrix2 other02 = new Matrix2();
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other02));
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 6, 0.514, 4.9, 1.8, 3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other2 = new Matrix(9.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(9.0, 2.0, -5.0, 11.0), sut.round());
        
        sut = new Matrix2();
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.round());
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(101.859, sut.determinant(), TestUtils.DELTA);
        
        sut = new Matrix2();
        Assert.assertEquals(0.0, sut.determinant(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#minor(int, int)
     * @see Matrix2#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(11.1, sut.minor(0, 0), TestUtils.DELTA);
        Assert.assertEquals(-5.006, sut.minor(1, 0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.minor(0, 1), TestUtils.DELTA);
        Assert.assertEquals(8.5, sut.minor(1, 1), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.minor(0), TestUtils.DELTA);
        Assert.assertEquals(-5.006, sut.minor(1), TestUtils.DELTA);
        Assert.assertEquals(8.5, sut.minor(3), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
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
     * @see Matrix2#minors()
     */
    @Test
    public void testMinors() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(11.1, -5.006, 1.5, 8.5), sut.minors());
        
        sut = new Matrix2();
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.minors());
    }
    
    /**
     * JUnit test of cofactorScalar.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#cofactorScalar(int, int)
     * @see Matrix2#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1, 0), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(0, 1), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(1, 1), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(0), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(3), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
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
     * @see Matrix2#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(8.5, -1.5, 5.006, 11.1), sut.cofactor());
        
        sut = new Matrix2();
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.cofactor());
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(8.5, -5.006, 1.5, 11.1), sut.transpose());
        
        sut = new Matrix2();
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.transpose());
    }
    
    /**
     * JUnit test of adjoint.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(11.1, -1.5, 5.006, 8.5), sut.adjoint());
        
        sut = new Matrix2();
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), sut.adjoint());
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Matrix2(0.108974170176, -0.014726239213, 0.049146369, 0.083448688874), sut.inverse());
        
        //invalid
        
        sut = new Matrix2();
        TestUtils.assertException(ArithmeticException.class, "The 2D Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
        
        sut = new Matrix2(3.0, 1.0, 3.0, 1.0);
        TestUtils.assertException(ArithmeticException.class, "The 2D Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        Vector solution;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        solution = new Vector2(4.0, 19.6);
        Assert.assertEquals(
                new Vector2(0.14726239213, 1.832179777928), sut.solveSystem(solution));
        
        //invalid
        
        sut = new Matrix2();
        final Vector solution1 = new Vector2();
        TestUtils.assertException(ArithmeticException.class, "The 2D Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution1));
        
        sut = new Matrix2(3.0, 1.0, 3.0, 1.0);
        final Vector solution2 = new Vector2(4.0, 19.6);
        TestUtils.assertException(ArithmeticException.class, "The 2D Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution2));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Vector solution3 = new Vector(4.0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution3), () ->
                sut.solveSystem(solution3));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Vector solution4 = new Vector(4.0, 19.6, 1.774, 1.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution4), () ->
                sut.solveSystem(solution4));
    }
    
    /**
     * JUnit test of transform.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        Vector solution;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        solution = new Vector2(4.0, 19.6);
        Assert.assertEquals(
                new Vector2(-64.1176, 223.56), sut.transform(solution));
        
        //invalid
        
        sut = new Matrix2();
        final Vector solution1 = new Vector2();
        TestUtils.assertNoException(() ->
                sut.transform(solution1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Vector solution3 = new Vector(4.0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution3), () ->
                sut.transform(solution3));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Vector solution4 = new Vector(4.0, 19.6, 1.774, 1.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution4), () ->
                sut.transform(solution4));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        Matrix copy;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        copy = new Matrix2();
        sut.copy(copy);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new Matrix2();
        copy = new Matrix2();
        sut.copy(copy);
        Assert.assertEquals(
                new Matrix2(), copy);
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix copy1 = new Matrix(0.0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#copyMeta(Component)
     */
    @Test
    public void testCopyMeta() throws Exception {
        Matrix component1 = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(2, component1.getDimensionality());
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1}, component1.getRawComponents());
        
        Matrix component2 = new Matrix2(9.1, 6.3, 1.7, 0.3);
        component1.copyMeta(component2);
        Assert.assertEquals(2, component2.getDimensionality());
        Assert.assertArrayEquals(new Double[] {9.1, 6.3, 1.7, 0.3}, component2.getRawComponents());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new Matrix2(1, 2, 3, 4);
        Assert.assertFalse(sut.isResizeable());
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        sut.redim(4);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        sut.redim(1);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        sut.redim(0);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        sut.redim(-1);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), sut);
    }
    
    /**
     * JUnit test of subMatrix.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#subMatrix(int, int, int, int)
     */
    @Test
    public void testSubMatrix() throws Exception {
        //standard
        
        Matrix subMatrix;
        sut = new Matrix2(1, 2, 3, 4);
        Assert.assertFalse(sut.isResizeable());
        
        subMatrix = sut.subMatrix(0, 0, 1, 1);
        Assert.assertEquals(
                new Matrix2(1, 2, 3, 4), subMatrix);
        
        //dimensionality
        
        sut = new Matrix2(1, 2, 3, 4);
        subMatrix = sut.subMatrix(0, 0, 2);
        Assert.assertEquals(
                new Matrix2(1, 2, 3, 4), subMatrix);
        
        //invalid
        
        sut = new Matrix2(1, 2, 3, 4);
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Matrix2.DIMENSIONALITY), () ->
                sut.subMatrix(0, 0, 0, 0));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Matrix2.DIMENSIONALITY), () ->
                sut.subMatrix(0, 0, 1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 0, 0), () ->
                sut.subMatrix(1, 1, 0, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, -1, -1), () ->
                sut.subMatrix(1, 1, -1, -1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 6, 6), () ->
                sut.subMatrix(1, 1, 6, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, -2, -2, 2, 2), () ->
                sut.subMatrix(-2, -2, 2, 2));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 0, 0), () ->
                sut.subMatrix(1, 1, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 0, 0, 9, 9), () ->
                sut.subMatrix(0, 0, 10));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateRangeOutOfBoundsErrorMessage(sut, 1, 1, 0, 0), () ->
                sut.subMatrix(1, 1, -1));
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#dimensionalityToLength(int)
     * @see Matrix2#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        
        //standard
        Assert.assertEquals(4, sut.dimensionalityToLength());
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
     * @see Matrix2#dimensionalityToWidth(int)
     * @see Matrix2#dimensionalityToWidth()
     */
    @Test
    public void testDimensionalityToWidth() throws Exception {
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        
        //standard
        Assert.assertEquals(2, sut.dimensionalityToWidth());
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
     * @see Matrix2#dimensionalityToHeight(int)
     * @see Matrix2#dimensionalityToHeight()
     */
    @Test
    public void testDimensionalityToHeight() throws Exception {
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        
        //standard
        Assert.assertEquals(2, sut.dimensionalityToHeight());
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
     * @see Matrix2#lengthToDimensionality(int)
     * @see Matrix2#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        
        //standard
        Assert.assertEquals(2, sut.lengthToDimensionality());
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
     * @see Matrix2#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        
        //standard
        Assert.assertEquals(2, sut.getDimensionality());
        TestUtils.setFieldValue(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(2, sut.getDimensionality());
    }
    
    /**
     * JUnit test of prettyPrint.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
        //standard
        
        List<String> print;
        
        sut = new Matrix2();
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(2, print.size());
        Assert.assertEquals("┌  0.0   0.0  ┐", print.get(0));
        Assert.assertEquals("└  0.0   0.0  ┘", print.get(1));
        
        sut = new Matrix2(1, 2, 3, 4);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(2, print.size());
        Assert.assertEquals("┌  1.0   2.0  ┐", print.get(0));
        Assert.assertEquals("└  3.0   4.0  ┘", print.get(1));
        
        sut = new Matrix2(8.154119, 3, 1.1033, 8.5);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(2, print.size());
        Assert.assertEquals("┌  8.154119   3.0  ┐", print.get(0));
        Assert.assertEquals("└  1.1033     8.5  ┘", print.get(1));
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        Matrix component;
        
        //standard
        component = new Matrix2(8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782);
        Assert.assertArrayEquals(new Double[] {8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782}, component.getRawComponents());
        Assert.assertArrayEquals(new Double[] {8.516540894156, 1.505684814185, -5.006549845043, 11.152604098725,}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        Matrix component;
        
        //standard
        
        component = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1}, component.getComponents());
        
        component = new Matrix2();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, component.getComponents());
        
        component = new Matrix2(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999}, component.getComponents());
        
        component = new Matrix2(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        Matrix component;
        
        //standard
        component = new Matrix2(8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782);
        Assert.assertArrayEquals(new double[] {8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782}, component.getPrimitiveComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new Double[] {8.516540894156, 1.505684814185, -5.006549845043, 11.152604098725,}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getRaw(int)
     * @see Matrix2#getRaw(int, int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(8.5, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.getRaw(3), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1, 0), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.getRaw(1, 1), TestUtils.DELTA);
        
        sut = new Matrix2(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertEquals(8.500000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(11.099999999999, sut.getRaw(3), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1, 0), TestUtils.DELTA);
        Assert.assertEquals(11.099999999999, sut.getRaw(1, 1), TestUtils.DELTA);
        
        sut = new Matrix2(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(11.0999999999999999999999, sut.getRaw(3), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1, 0), TestUtils.DELTA);
        Assert.assertEquals(11.0999999999999999999999, sut.getRaw(1, 1), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.getRaw(4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 25), () ->
                sut.getRaw(25));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 5), () ->
                sut.getRaw(1, 5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 4, 4), () ->
                sut.getRaw(4, 4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 8, 9), () ->
                sut.getRaw(8, 9));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -1, -2), () ->
                sut.getRaw(-1, -2));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#get(int)
     * @see Matrix2#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.get(3), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1, 0), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.get(1, 1), TestUtils.DELTA);
        
        sut = new Matrix2(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertEquals(8.500000000001, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(11.099999999999, sut.get(3), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1, 0), TestUtils.DELTA);
        Assert.assertEquals(11.099999999999, sut.get(1, 1), TestUtils.DELTA);
        
        sut = new Matrix2(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.get(3), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1, 0), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.get(1, 1), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.get(4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 25), () ->
                sut.get(25));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 5), () ->
                sut.get(1, 5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 4, 4), () ->
                sut.get(4, 4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 8, 9), () ->
                sut.get(8, 9));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -1, -2), () ->
                sut.get(-1, -2));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new Matrix2();
        Assert.assertEquals(2, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(2, sut.getHeight());
        
        sut = new Matrix2();
        Assert.assertEquals(2, sut.getHeight());
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(2, sut.getWidth());
        
        sut = new Matrix2();
        Assert.assertEquals(2, sut.getWidth());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new Matrix2();
        Assert.assertEquals(4, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new Matrix2();
        Assert.assertEquals(Matrix2.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new Matrix2();
        Assert.assertEquals(Double.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new Matrix2();
        Assert.assertTrue(sut.getHandler() instanceof DoubleComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new Matrix2();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new Matrix2();
        Assert.assertEquals("2D Matrix", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new Matrix2();
        Assert.assertEquals("2D Matrices", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new Matrix2();
        Assert.assertEquals(1E-12, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new Matrix2();
        Assert.assertFalse(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        Matrix component;
        Double[] newComponents;
        
        //standard
        
        component = new Matrix2(8.5, 1.5, -5.006, 11.1);
        newComponents = new Double[] {5.6, 6.7, 1.1, 7.2};
        Assert.assertEquals(2, component.getDimensionality());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Double[] {5.6, 6.7, 1.1, 7.2}, component.getRawComponents());
        Assert.assertEquals(2, component.getDimensionality());
        
        //invalid
        
        final Matrix component1 = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Double[] newComponents1 = new Double[] {8.5};
        Assert.assertFalse(component1.isResizeable());
        TestUtils.assertException(IndexOutOfBoundsException.class, component1.getErrorHandler().componentLengthNotEqualErrorMessage(newComponents1, component1.getLength()), () ->
                component1.setComponents(newComponents1));
        
        final Matrix component2 = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#set(int, Number)
     * @see Matrix2#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(3, 3.0);
        Assert.assertArrayEquals(new Double[] {5.77, 1.5, -5.006, 3.0}, sut.getRawComponents());
        
        sut = new Matrix2();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(3, 3.0);
        Assert.assertArrayEquals(new Double[] {5.77, 0.0, 0.0, 3.0}, sut.getRawComponents());
        
        sut = new Matrix2(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999}, sut.getRawComponents());
        sut.set(0, 5.769999999996);
        sut.set(3, 3.000000000001);
        Assert.assertArrayEquals(new Double[] {5.769999999996, 1.499999999996, -5.005999999999, 3.000000000001}, sut.getRawComponents());
        
        sut = new Matrix2(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertArrayEquals(new Double[] {8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999}, sut.getRawComponents());
        sut.set(0, 6.5000000000000000000000001);
        sut.set(3, -1.49999999999999999999996);
        Assert.assertArrayEquals(new Double[] {6.5, 1.5, -5.006, -1.5}, sut.getRawComponents());
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.set(4, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 25), () ->
                sut.set(25, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 5.5));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        Matrix copy;
        
        //standard
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        copy = new Matrix2();
        Matrix2.copy(sut, copy);
        Assert.assertEquals(
                new Matrix2(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new Matrix2();
        copy = new Matrix2();
        Matrix2.copy(sut, copy);
        Assert.assertEquals(
                new Matrix2(), copy);
        
        //invalid
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        final Matrix copy1 = new Matrix(0.0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                Matrix2.copy(sut, copy1));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                Matrix2.copy(sut, null));
        
        sut = new Matrix2(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                Matrix2.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#createInstance()
     * @see Matrix2#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.createInstance(0));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.createInstance(1));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.createInstance(2));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.createInstance(3));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#identity()
     * @see Matrix2#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix2(1.0, 0.0, 0.0, 1.0), Matrix2.identity(0));
        Assert.assertEquals(
                new Matrix2(1.0, 0.0, 0.0, 1.0), Matrix2.identity(1));
        Assert.assertEquals(
                new Matrix2(1.0, 0.0, 0.0, 1.0), Matrix2.identity(2));
        Assert.assertEquals(
                new Matrix2(1.0, 0.0, 0.0, 1.0), Matrix2.identity(3));
        Assert.assertEquals(
                new Matrix2(1.0, 0.0, 0.0, 1.0), Matrix2.identity(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix2(1.0, 0.0, 0.0, 1.0), Matrix2.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#origin()
     * @see Matrix2#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.origin(0));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.origin(1));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.origin(2));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.origin(3));
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.origin(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix2(0.0, 0.0, 0.0, 0.0), Matrix2.origin(-1));
    }
    
    /**
     * JUnit test of signChart.
     *
     * @throws Exception When there is an exception.
     * @see Matrix2#signChart()
     * @see Matrix2#signChart(int)
     */
    @Test
    public void testSignChart() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix2(1.0, -1.0, -1.0, 1.0), Matrix2.signChart(0));
        Assert.assertEquals(
                new Matrix2(1.0, -1.0, -1.0, 1.0), Matrix2.signChart(1));
        Assert.assertEquals(
                new Matrix2(1.0, -1.0, -1.0, 1.0), Matrix2.signChart(2));
        Assert.assertEquals(
                new Matrix2(1.0, -1.0, -1.0, 1.0), Matrix2.signChart(3));
        Assert.assertEquals(
                new Matrix2(1.0, -1.0, -1.0, 1.0), Matrix2.signChart(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix2(1.0, -1.0, -1.0, 1.0), Matrix2.signChart(-1));
    }
    
}
