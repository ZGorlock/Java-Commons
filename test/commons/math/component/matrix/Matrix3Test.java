/*
 * File:    Matrix3Test.java
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
import commons.math.component.vector.Vector3;
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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Matrix3.
 *
 * @see Matrix3
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Matrix3.class})
public class Matrix3Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Matrix3Test.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private Matrix3 sut;
    
    
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
     * @see Matrix3#DIMENSIONALITY
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(3, Matrix3.DIMENSIONALITY);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#Matrix3(double...)
     * @see Matrix3#Matrix3()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        Matrix3 matrix = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        TestUtils.assertArrayEquals(
                matrix.getRawComponents(),
                new Double[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06, 4.4});
        Assert.assertEquals(3, matrix.getDimensionality());
        
        //empty
        Matrix3 matrixDefault = new Matrix3();
        TestUtils.assertArrayEquals(
                matrixDefault.getRawComponents(),
                new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        Assert.assertEquals(3, matrixDefault.getDimensionality());
        
        //dimensionality (ignored)
        Matrix3 matrixDimensionality = new Matrix3(8);
        TestUtils.assertArrayEquals(
                matrixDimensionality.getRawComponents(),
                new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        Assert.assertEquals(3, matrixDimensionality.getDimensionality());
        
        //equality
        Assert.assertEquals(matrixDefault, matrixDimensionality);
        
        //invalid
        TestUtils.assertException(ArithmeticException.class, new Matrix3().getErrorHandler().componentLengthNotSquareErrorMessage(new Double[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06}), () ->
                new Matrix3(0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06));
        TestUtils.assertException(ArithmeticException.class, "The 3D Matrix: [<0.884, 2.0>, <1.1, -9.3>] does not have the expected dimensionality of: 3", () ->
                new Matrix3(0.884, 2.0, 1.1, -9.3));
    }
    
    /**
     * JUnit test of matrixString.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#matrixString()
     */
    @Test
    public void testMatrixString() throws Exception {
        //standard
        
        sut = new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("[<1.0, 2.0, 3.0>, <4.0, 5.0, 6.0>, <7.0, 8.0, 9.0>]", sut.matrixString());
        
        sut = new Matrix3(8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455);
        Assert.assertEquals("[<8.15, 77.1654, 3.0>, <3.66, 7.15, 890.1>, <11.0, 7.9888, 0.79455>]", sut.matrixString());
        
        //empty
        
        sut = new Matrix3();
        Assert.assertEquals("[<0.0, 0.0, 0.0>, <0.0, 0.0, 0.0>, <0.0, 0.0, 0.0>]", sut.matrixString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("[<1.0, 2.0, 3.0>, <4.0, 5.0, 6.0>, <7.0, 8.0, 9.0>]", sut.toString());
        
        sut = new Matrix3(8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455);
        Assert.assertEquals("[<8.15, 77.1654, 3.0>, <3.66, 7.15, 890.1>, <11.0, 7.9888, 0.79455>]", sut.toString());
        
        //empty
        
        sut = new Matrix3();
        Assert.assertEquals("[<0.0, 0.0, 0.0>, <0.0, 0.0, 0.0>, <0.0, 0.0, 0.0>]", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertTrue(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.equals(other));
        
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
     * @see Matrix3#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
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
     * @see Matrix3#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertFalse(sut.lengthEqual(other));
        
        Assert.assertTrue(new Matrix().lengthEqual(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
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
     * @see Matrix3#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new Matrix3(8.1, 6.6, 5, 1.09, 1.7, 8.54, 0.4, 6.2, 4.7);
        Matrix clone = sut.cloned();
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
     * @see Matrix3#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new Matrix3(8.1, 6.6, 5, 1.09, 1.7, 8.54, 0.4, 6.2, 4.7);
        Matrix emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        TestUtils.assertArrayEquals(
                emptyCopy.getRawComponents(),
                Matrix.origin(sut.getDimensionality()).getRawComponents());
        Assert.assertNotSame(sut, emptyCopy);
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
        //standard
        
        sut = new Matrix3();
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0), sut.newVector());
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0), sut.newVector());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new Matrix3();
        
        //standard
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(0));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
        //standard
        
        sut = new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        Assert.assertEquals(1, sut.toIndex(1, 0));
        Assert.assertEquals(4, sut.toIndex(1, 1));
        
        //invalid
        
        sut = new Matrix3();
        Assert.assertEquals(36, sut.toIndex(9, 9));
        
        sut = new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(6, sut.toIndex(3, 1));
        Assert.assertEquals(36, sut.toIndex(9, 9));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        Matrix reversed;
        
        //standard
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Double[] {4.4, 5.06, -0.77, 8.0, 1.61, -9.3, 1.1, 2.0, 0.884});
        
        sut = new Matrix3();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        other = new Matrix3(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(19.187033434066873, sut.distance(other), TestUtils.DELTA);
        
        sut = new Matrix3();
        other = new Matrix3();
        Assert.assertEquals(0.0, sut.distance(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        final Matrix other2 = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        other = new Matrix3(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new Matrix3(5.392, 4.0, 0.807, -2.2, 1.705, 5.5, 2.365, 6.08, 4.7), sut.midpoint(other));
        
        sut = new Matrix3();
        other = new Matrix3();
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.midpoint(other));
        
        //invalid
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        final Matrix other2 = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#average(List)
     * @see Matrix3#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        Matrix other1;
        Matrix other2;
        Matrix other3;
        Matrix other4;
        
        //standard
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        other1 = new Matrix3(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        other2 = new Matrix3(1.8, 4.77, 0.514, 2.895, 3, 4.9, 6, 0.8, 7.1);
        other3 = new Matrix3(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5);
        Assert.assertEquals(
                new Matrix3(6.071, 3.30425, 2.322, 1.62375, 2.3025, 6.975, 2.875, 4.89, 5.375), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Matrix3(6.071, 3.30425, 2.322, 1.62375, 2.3025, 6.975, 2.875, 4.89, 5.375), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new Matrix3();
        other1 = new Matrix3();
        other2 = new Matrix3();
        other3 = new Matrix3();
        Assert.assertEquals(
                new Matrix3(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Matrix3(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        final Matrix otherF11 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1);
        final Matrix otherF12 = new Matrix(1.8, 4.77, 0.514, 2.895, 3, 4.9, 6, 0.8, 7.1);
        final Matrix otherF13 = new Matrix(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        final Matrix otherF21 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        final Matrix otherF22 = new Matrix(1.8, 4.77, 0.514, 2.895);
        final Matrix otherF23 = new Matrix(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        final Matrix otherF31 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        final Matrix otherF33 = new Matrix(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new Matrix3(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(46.494, sut.sum(), TestUtils.DELTA);
        
        sut = new Matrix3();
        Assert.assertEquals(0, sut.sum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(445.35003600000005, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Matrix3();
        Assert.assertEquals(0, sut.squareSum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new Matrix3(18.4, 7.5, -4.492, 16.0, 6.1, 12.0, 7.1, 14.4, 13.2), sut.plus(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix3(9.5, 2.5, -4.006, 12.1, 5.3, 10.0, 2.6, 8.3, 9.2), sut.plus(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2), sut.plus(other));
        
        sut = new Matrix3();
        other = new Matrix3();
        Assert.assertEquals(
                new Matrix3(), sut.plus(other));
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new Matrix3(-1.4, -4.5, -5.52, 6.2, 2.5, 6.0, -3.9, 0.2, 3.2), sut.minus(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix3(7.5, 0.5, -6.006, 10.1, 3.3, 8.0, 0.6, 6.3, 7.2), sut.minus(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), sut.minus(other));
        
        sut = new Matrix3();
        other = new Matrix3();
        Assert.assertEquals(
                new Matrix3(), sut.minus(other));
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#times(Matrix)
     */
    @Test
    public void testTimes() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new Matrix3(63.967, 18.1574, -16.161, 180.46, 138.24, 63.6054, 96.71, 80.96, 63.7224), sut.times(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix3(4.994, 4.994, 4.994, 24.4, 24.4, 24.4, 17.1, 17.1, 17.1), sut.times(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.times(other));
        
        sut = new Matrix3();
        other = new Matrix3();
        Assert.assertEquals(
                new Matrix3(), sut.times(other));
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((Matrix) null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Vector3(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(90.576916, 140.316, 63.8548), sut.times(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Vector3(1, 1, 1);
        Assert.assertEquals(
                new Vector3(4.994, 24.4, 17.1), sut.times(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Vector3(0, 0, 0);
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.times(other));
        
        sut = new Matrix3();
        other = new Vector3();
        Assert.assertEquals(
                new Vector3(), sut.times(other));
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Vector other1 = new Vector(9.9, 6, 0.514, 4.4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Vector other2 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Vector other3 = new Vector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((Vector) null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(34.0, 6.0, -20.024, 44.4, 17.2, 36.0, 6.4, 29.2, 32.8), sut.scale(4));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(138.04, 24.36, -81.29744, 180.264, 69.832, 146.16, 25.984, 118.552, 133.168), sut.scale(16.24));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(8.5391, 1.5069, -5.0290276, 11.15106, 4.31978, 9.0414, 1.60736, 7.33358, 8.23772), sut.scale(1.0046));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), sut.scale(1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.scale(0));
        
        sut = new Matrix3();
        Assert.assertEquals(
                new Matrix3(), sut.scale(7.1));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#scale(MatrixInterface)
     */
    @Test
    public void testMatrixScale() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new Matrix3(84.15, 9.0, -2.573084, 54.39, 7.74, 27.0, 8.8, 51.83, 41.0), sut.scale(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2), sut.scale(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.scale(other));
        
        sut = new Matrix3();
        other = new Matrix3();
        Assert.assertEquals(
                new Matrix3(), sut.scale(other));
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.scale(other1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.scale(other2));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.scale(other3));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.scale((Matrix) null));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new Matrix3(0.858585858586, 0.25, -9.739299610895, 2.265306122449, 2.388888888889, 3.0, 0.290909090909, 1.028169014085, 1.64), sut.dividedBy(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new Matrix3(1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), sut.dividedBy(other));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix3 other0 = new Matrix3(0, 0, 0, 0, 0, 0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new Matrix3();
        final Matrix3 other02 = new Matrix3();
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other02));
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(9.0, 2.0, -5.0, 11.0, 4.0, 9.0, 2.0, 7.0, 8.0), sut.round());
        
        sut = new Matrix3();
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.round());
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(-744.8648999999999, sut.determinant(), TestUtils.DELTA);
        
        sut = new Matrix3();
        Assert.assertEquals(0.0, sut.determinant(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#minor(int, int)
     * @see Matrix3#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(-30.44, sut.minor(0, 0), TestUtils.DELTA);
        Assert.assertEquals(76.62, sut.minor(1, 0), TestUtils.DELTA);
        Assert.assertEquals(48.8438, sut.minor(0, 1), TestUtils.DELTA);
        Assert.assertEquals(77.7096, sut.minor(1, 1), TestUtils.DELTA);
        Assert.assertEquals(132.0666, sut.minor(1, 2), TestUtils.DELTA);
        Assert.assertEquals(19.9, sut.minor(2, 2), TestUtils.DELTA);
        Assert.assertEquals(-30.44, sut.minor(0), TestUtils.DELTA);
        Assert.assertEquals(76.62, sut.minor(1), TestUtils.DELTA);
        Assert.assertEquals(132.0666, sut.minor(7), TestUtils.DELTA);
        Assert.assertEquals(19.9, sut.minor(8), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
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
     * @see Matrix3#minors()
     */
    @Test
    public void testMinors() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(-30.44, 76.62, 74.15, 48.8438, 77.7096, 59.65, 35.0258, 132.0666, 19.9), sut.minors());
        
        sut = new Matrix3();
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.minors());
    }
    
    /**
     * JUnit test of cofactorScalar.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#cofactorScalar(int, int)
     * @see Matrix3#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1, 0), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(0, 1), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(1, 1), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1, 2), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(2, 2), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(0), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(7), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(8), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
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
     * @see Matrix3#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(8.5, -1.5, -5.006, -11.1, 4.3, -9.0, 1.6, -7.3, 8.2), sut.cofactor());
        
        sut = new Matrix3();
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.cofactor());
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(8.5, 11.1, 1.6, 1.5, 4.3, 7.3, -5.006, 9.0, 8.2), sut.transpose());
        
        sut = new Matrix3();
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.transpose());
    }
    
    /**
     * JUnit test of adjoint.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(-30.44, -48.8438, 35.0258, -76.62, 77.7096, -132.0666, 74.15, -59.65, 19.9), sut.adjoint());
        
        sut = new Matrix3();
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.adjoint());
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new Matrix3(0.040866471222, 0.065574038997, -0.047023023907, 0.102864291229, -0.104327106835, 0.177302756513, -0.09954825365, 0.080081636281, -0.026716254182), sut.inverse());
        
        //invalid
        
        sut = new Matrix3();
        TestUtils.assertException(ArithmeticException.class, "The 3D Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
        
        sut = new Matrix3(3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0);
        TestUtils.assertException(ArithmeticException.class, "The 3D Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        Vector solution;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        solution = new Vector3(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new Vector3(1.365298204815, -1.318819038996, 1.124012421581), sut.solveSystem(solution));
        
        //invalid
        
        sut = new Matrix3();
        final Vector solution1 = new Vector3();
        TestUtils.assertException(ArithmeticException.class, "The 3D Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution1));
        
        sut = new Matrix3(3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0);
        final Vector solution2 = new Vector3(4.0, 19.6, 1.774);
        TestUtils.assertException(ArithmeticException.class, "The 3D Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution2));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Vector solution3 = new Vector(4.0, 19.6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution3), () ->
                sut.solveSystem(solution3));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Vector solution4 = new Vector(4.0, 19.6, 1.774, 1.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution4), () ->
                sut.solveSystem(solution4));
    }
    
    /**
     * JUnit test of transform.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        Vector solution;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        solution = new Vector3(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new Vector3(254.3984, 103.2302, 170.9228), sut.transform(solution));
        
        //invalid
        
        sut = new Matrix3();
        final Vector solution1 = new Vector3();
        TestUtils.assertNoException(() ->
                sut.transform(solution1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Vector solution3 = new Vector(4.0, 19.6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution3), () ->
                sut.transform(solution3));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Vector solution4 = new Vector(4.0, 19.6, 1.774, 1.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution4), () ->
                sut.transform(solution4));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        Matrix copy;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        copy = new Matrix3();
        sut.copy(copy);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), copy);
        
        sut = new Matrix3();
        copy = new Matrix3();
        sut.copy(copy);
        Assert.assertEquals(
                new Matrix3(), copy);
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix copy1 = new Matrix(0.0, 0.0, 0.0, 0.0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#copyMeta(Component)
     */
    @Test
    public void testCopyMeta() throws Exception {
        Matrix component1 = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(3, component1.getDimensionality());
        TestUtils.assertArrayEquals(
                component1.getRawComponents(),
                new Double[] {8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2});
        
        Matrix component2 = new Matrix3(9.1, 6.3, 1.7, 0.3, 6.9, 0.2, 3.0, 11.1, 4.3);
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        TestUtils.assertArrayEquals(
                component2.getRawComponents(),
                new Double[] {9.1, 6.3, 1.7, 0.3, 6.9, 0.2, 3.0, 11.1, 4.3});
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertFalse(sut.isResizeable());
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        sut.redim(4);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), sut);
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        sut.redim(1);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), sut);
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        sut.redim(0);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), sut);
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        sut.redim(-1);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), sut);
    }
    
    /**
     * JUnit test of subMatrix.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#subMatrix(int, int, int, int)
     */
    @Test
    public void testSubMatrix() throws Exception {
        //standard
        
        Matrix subMatrix;
        sut = new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertFalse(sut.isResizeable());
        
        subMatrix = sut.subMatrix(0, 0, 2, 2);
        Assert.assertEquals(
                new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9), subMatrix);
        
        //dimensionality
        
        sut = new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        subMatrix = sut.subMatrix(0, 0, 3);
        Assert.assertEquals(
                new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9), subMatrix);
        
        //invalid
        
        sut = new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Matrix3.DIMENSIONALITY), () ->
                sut.subMatrix(0, 0, 1, 1));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Matrix3.DIMENSIONALITY), () ->
                sut.subMatrix(1, 1, 2, 2));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Matrix3.DIMENSIONALITY), () ->
                sut.subMatrix(0, 0, 2));
        
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
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0}), () ->
                sut.subMatrix(0, 0, 2, 1));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Double[] {1.0, 2.0, 4.0, 5.0, 7.0, 8.0}), () ->
                sut.subMatrix(0, 0, 1, 2));
        
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
     * @see Matrix3#dimensionalityToLength(int)
     * @see Matrix3#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        
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
     * @see Matrix3#dimensionalityToWidth(int)
     * @see Matrix3#dimensionalityToWidth()
     */
    @Test
    public void testDimensionalityToWidth() throws Exception {
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        
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
     * @see Matrix3#dimensionalityToHeight(int)
     * @see Matrix3#dimensionalityToHeight()
     */
    @Test
    public void testDimensionalityToHeight() throws Exception {
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        
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
     * @see Matrix3#lengthToDimensionality(int)
     * @see Matrix3#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        
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
     * @see Matrix3#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        
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
     * @see Matrix3#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
        //standard
        
        List<String> print;
        
        sut = new Matrix3();
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(3, print.size());
        Assert.assertEquals("┌  0.0   0.0   0.0  ┐", print.get(0));
        Assert.assertEquals("│  0.0   0.0   0.0  │", print.get(1));
        Assert.assertEquals("└  0.0   0.0   0.0  ┘", print.get(2));
        
        sut = new Matrix3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(3, print.size());
        Assert.assertEquals("┌  1.0   2.0   3.0  ┐", print.get(0));
        Assert.assertEquals("│  4.0   5.0   6.0  │", print.get(1));
        Assert.assertEquals("└  7.0   8.0   9.0  ┘", print.get(2));
        
        sut = new Matrix3(8.154119, 3, 1.1033, 8.5, 4.4465740112, 9.915422012, 16.112, 45.2947, 7);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(3, print.size());
        Assert.assertEquals("┌  8.154119   3.0            1.1033       ┐", print.get(0));
        Assert.assertEquals("│  8.5        4.4465740112   9.915422012  │", print.get(1));
        Assert.assertEquals("└  16.112     45.2947        7.0          ┘", print.get(2));
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        Matrix component;
        
        //standard
        component = new Matrix3(8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782, 4.350949549874897161, 9.0215015065454, 1.690489487428742884, 7.39684561235412, 8.256089809484540121);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Double[] {8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782, 4.350949549874897161, 9.0215015065454, 1.690489487428742884, 7.39684561235412, 8.256089809484540121});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Double[] {8.516540894156, 1.505684814185, -5.006549845043, 11.152604098725, 4.350949549875, 9.021501506545, 1.690489487429, 7.396845612354, 8.256089809485});
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        Matrix component;
        
        //standard
        
        component = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Double[] {8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2});
        
        component = new Matrix3();
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        
        component = new Matrix3(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Double[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999});
        
        component = new Matrix3(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Double[] {8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2});
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        Matrix component;
        
        //standard
        component = new Matrix3(8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782, 4.350949549874897161, 9.0215015065454, 1.690489487428742884, 7.39684561235412, 8.256089809484540121);
        TestUtils.assertArrayEquals(
                Arrays.stream(component.getPrimitiveComponents()).boxed().toArray(),
                new Double[] {8.516540894156, 1.505684814185, -5.006549845043, 11.152604098725, 4.350949549875, 9.021501506545, 1.690489487429, 7.396845612354, 8.256089809485});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Double[] {8.516540894156, 1.505684814185, -5.006549845043, 11.152604098725, 4.350949549875, 9.021501506545, 1.690489487429, 7.396845612354, 8.256089809485});
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getRaw(int)
     * @see Matrix3#getRaw(int, int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(8.5, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(4.3, sut.getRaw(4), TestUtils.DELTA);
        Assert.assertEquals(8.2, sut.getRaw(8), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1, 0), TestUtils.DELTA);
        Assert.assertEquals(4.3, sut.getRaw(1, 1), TestUtils.DELTA);
        Assert.assertEquals(7.3, sut.getRaw(1, 2), TestUtils.DELTA);
        Assert.assertEquals(8.2, sut.getRaw(2, 2), TestUtils.DELTA);
        
        sut = new Matrix3(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999);
        Assert.assertEquals(8.500000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(4.299999999999, sut.getRaw(4), TestUtils.DELTA);
        Assert.assertEquals(8.199999999999, sut.getRaw(8), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1, 0), TestUtils.DELTA);
        Assert.assertEquals(4.299999999999, sut.getRaw(1, 1), TestUtils.DELTA);
        Assert.assertEquals(7.299999999996, sut.getRaw(1, 2), TestUtils.DELTA);
        Assert.assertEquals(8.199999999999, sut.getRaw(2, 2), TestUtils.DELTA);
        
        sut = new Matrix3(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(4.29999999999999999999996, sut.getRaw(4), TestUtils.DELTA);
        Assert.assertEquals(8.1999999999999999999999, sut.getRaw(8), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1, 0), TestUtils.DELTA);
        Assert.assertEquals(4.29999999999999999999996, sut.getRaw(1, 1), TestUtils.DELTA);
        Assert.assertEquals(7.29999999999999999999996, sut.getRaw(1, 2), TestUtils.DELTA);
        Assert.assertEquals(8.1999999999999999999999, sut.getRaw(2, 2), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 9), () ->
                sut.getRaw(9));
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
     * @see Matrix3#get(int)
     * @see Matrix3#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(4.3, sut.get(4), TestUtils.DELTA);
        Assert.assertEquals(8.2, sut.get(8), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1, 0), TestUtils.DELTA);
        Assert.assertEquals(4.3, sut.get(1, 1), TestUtils.DELTA);
        Assert.assertEquals(7.3, sut.get(1, 2), TestUtils.DELTA);
        Assert.assertEquals(8.2, sut.get(2, 2), TestUtils.DELTA);
        
        sut = new Matrix3(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999);
        Assert.assertEquals(8.500000000001, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(4.299999999999, sut.get(4), TestUtils.DELTA);
        Assert.assertEquals(8.199999999999, sut.get(8), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1, 0), TestUtils.DELTA);
        Assert.assertEquals(4.299999999999, sut.get(1, 1), TestUtils.DELTA);
        Assert.assertEquals(7.299999999996, sut.get(1, 2), TestUtils.DELTA);
        Assert.assertEquals(8.199999999999, sut.get(2, 2), TestUtils.DELTA);
        
        sut = new Matrix3(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(4.3, sut.get(4), TestUtils.DELTA);
        Assert.assertEquals(8.2, sut.get(8), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1, 0), TestUtils.DELTA);
        Assert.assertEquals(4.3, sut.get(1, 1), TestUtils.DELTA);
        Assert.assertEquals(7.3, sut.get(1, 2), TestUtils.DELTA);
        Assert.assertEquals(8.2, sut.get(2, 2), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 9), () ->
                sut.get(9));
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
     * @see Matrix3#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(3, sut.getDimensionality());
        
        sut = new Matrix3();
        Assert.assertEquals(3, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(3, sut.getHeight());
        
        sut = new Matrix3();
        Assert.assertEquals(3, sut.getHeight());
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(3, sut.getWidth());
        
        sut = new Matrix3();
        Assert.assertEquals(3, sut.getWidth());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(9, sut.getLength());
        
        sut = new Matrix3();
        Assert.assertEquals(9, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new Matrix3();
        Assert.assertEquals(Matrix3.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new Matrix3();
        Assert.assertEquals(Double.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new Matrix3();
        Assert.assertTrue(sut.getHandler() instanceof DoubleComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new Matrix3();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new Matrix3();
        Assert.assertEquals("3D Matrix", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new Matrix3();
        Assert.assertEquals("3D Matrices", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new Matrix3();
        Assert.assertEquals(1E-12, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new Matrix3();
        Assert.assertFalse(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        Matrix component;
        Double[] newComponents;
        
        //standard
        
        component = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        newComponents = new Double[] {5.6, 6.7, 1.1, 7.2, 8.1, 0.7, 5.6, 7.23, 3.1};
        Assert.assertEquals(3, component.getDimensionality());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Double[] {5.6, 6.7, 1.1, 7.2, 8.1, 0.7, 5.6, 7.23, 3.1});
        Assert.assertEquals(3, component.getDimensionality());
        
        //invalid
        
        final Matrix component1 = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Double[] newComponents1 = new Double[] {8.5, 1.5, -5.006, 11.1};
        Assert.assertFalse(component1.isResizeable());
        TestUtils.assertException(IndexOutOfBoundsException.class, component1.getErrorHandler().componentLengthNotEqualErrorMessage(newComponents1, component1.getLength()), () ->
                component1.setComponents(newComponents1));
        
        final Matrix component2 = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#set(int, Number)
     * @see Matrix3#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Double[] {8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2});
        sut.set(0, 5.77);
        sut.set(3, 3.0);
        sut.set(4, 0.997);
        sut.set(8, 6.01);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Double[] {5.77, 1.5, -5.006, 3.0, 0.997, 9.0, 1.6, 7.3, 6.01});
        
        sut = new Matrix3();
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        sut.set(0, 5.77);
        sut.set(3, 3.0);
        sut.set(4, 0.997);
        sut.set(8, 6.01);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Double[] {5.77, 0.0, 0.0, 3.0, 0.997, 0.0, 0.0, 0.0, 6.01});
        
        sut = new Matrix3(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Double[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999});
        sut.set(0, 5.769999999996);
        sut.set(3, 3.000000000001);
        sut.set(4, 8.110000000001);
        sut.set(8, 5.800000000001);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Double[] {5.769999999996, 1.499999999996, -5.005999999999, 3.000000000001, 8.110000000001, 9.000000000001, 1.599999999996, 7.299999999996, 5.800000000001});
        
        sut = new Matrix3(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Double[] {8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999});
        sut.set(0, 6.5000000000000000000000001);
        sut.set(3, -1.49999999999999999999996);
        sut.set(4, 3.0059999999999999999999);
        sut.set(8, 5.7999999999999999999999);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Double[] {6.5, 1.5, -5.006, -1.5, 3.006, 9.0, 1.6, 7.3, 5.8});
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 9), () ->
                sut.set(9, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 25), () ->
                sut.set(25, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 5.5));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        Matrix copy;
        
        //standard
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        copy = new Matrix3();
        Matrix3.copy(sut, copy);
        Assert.assertEquals(
                new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), copy);
        
        sut = new Matrix3();
        copy = new Matrix3();
        Matrix3.copy(sut, copy);
        Assert.assertEquals(
                new Matrix3(), copy);
        
        //invalid
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final Matrix copy1 = new Matrix(0.0, 0.0, 0.0, 0.0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                Matrix3.copy(sut, copy1));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                Matrix3.copy(sut, null));
        
        sut = new Matrix3(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        TestUtils.assertException(NullPointerException.class, () ->
                Matrix3.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#createInstance()
     * @see Matrix3#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.createInstance(0));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.createInstance(1));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.createInstance(2));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.createInstance(3));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#identity()
     * @see Matrix3#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0), Matrix3.identity(0));
        Assert.assertEquals(
                new Matrix3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0), Matrix3.identity(1));
        Assert.assertEquals(
                new Matrix3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0), Matrix3.identity(2));
        Assert.assertEquals(
                new Matrix3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0), Matrix3.identity(3));
        Assert.assertEquals(
                new Matrix3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0), Matrix3.identity(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0), Matrix3.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#origin()
     * @see Matrix3#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.origin(0));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.origin(1));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.origin(2));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.origin(3));
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.origin(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix3(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix3.origin(-1));
    }
    
    /**
     * JUnit test of signChart.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#signChart()
     * @see Matrix3#signChart(int)
     */
    @Test
    public void testSignChart() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix3(1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0), Matrix3.signChart(0));
        Assert.assertEquals(
                new Matrix3(1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0), Matrix3.signChart(1));
        Assert.assertEquals(
                new Matrix3(1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0), Matrix3.signChart(2));
        Assert.assertEquals(
                new Matrix3(1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0), Matrix3.signChart(3));
        Assert.assertEquals(
                new Matrix3(1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0), Matrix3.signChart(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix3(1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0), Matrix3.signChart(-1));
    }
    
}
