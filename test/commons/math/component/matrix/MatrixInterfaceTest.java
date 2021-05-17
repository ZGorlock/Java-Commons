/*
 * File:    MatrixInterfaceTest.java
 * Package: commons.math.component.matrix
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.matrix;

import java.util.List;

import commons.math.component.vector.RawVector;
import commons.math.component.vector.VectorInterface;
import commons.string.StringUtility;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of MatrixInterface.
 *
 * @see MatrixInterface
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({MatrixInterface.class})
public class MatrixInterfaceTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MatrixInterfaceTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private RawMatrix sut;
    
    
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
     * JUnit test of matrixString.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#matrixString()
     */
    @Test
    public void testMatrixString() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "matrixString");
        
        //standard
        
        sut = new RawMatrix(1.0);
        Assert.assertEquals("[<1.0>]", sut.matrixString());
        
        sut = new RawMatrix(1, 2, 3, 4);
        Assert.assertEquals("[<1.0, 2.0>, <3.0, 4.0>]", sut.matrixString());
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("[<1.0, 2.0, 3.0>, <4.0, 5.0, 6.0>, <7.0, 8.0, 9.0>]", sut.matrixString());
        
        sut = new RawMatrix(8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455);
        Assert.assertEquals("[<8.15, 77.1654, 3.0>, <3.66, 7.15, 890.1>, <11.0, 7.9888, 0.79455>]", sut.matrixString());
        
        //empty
        
        sut = new RawMatrix();
        Assert.assertEquals("[]", sut.matrixString());
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "newVector");
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "toIndex", int.class, int.class);
        
        //standard
        
        sut = new RawMatrix(1.0);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        
        sut = new RawMatrix(1, 2, 3, 4);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        Assert.assertEquals(1, sut.toIndex(1, 0));
        Assert.assertEquals(3, sut.toIndex(1, 1));
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        Assert.assertEquals(1, sut.toIndex(1, 0));
        Assert.assertEquals(4, sut.toIndex(1, 1));
        Assert.assertEquals(7, sut.toIndex(1, 2));
        
        //invalid
        
        sut = new RawMatrix();
        Assert.assertEquals(9, sut.toIndex(9, 9));
        
        sut = new RawMatrix(1.0);
        Assert.assertEquals(18, sut.toIndex(9, 9));
        
        sut = new RawMatrix(1, 2, 3, 4);
        Assert.assertEquals(27, sut.toIndex(9, 9));
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(6, sut.toIndex(3, 1));
        Assert.assertEquals(36, sut.toIndex(9, 9));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#times(MatrixInterface)
     */
    @Test
    public void testTimes() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "times", MatrixInterface.class);
        
        RawMatrix other;
        RawMatrix result;
        
        //standard
        
        sut = new RawMatrix(8.6);
        other = new RawMatrix(15.0);
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawMatrix(129.0), result);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        other = new RawMatrix(15, 11, 8.5, 3);
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawMatrix(163.0, 106.6, -30.015, -51.111), result);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        other = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawMatrix(169.404, 137.5616, 76.3182, 125.4, 108.56, 91.52, 117.74, 116.096, 82.042), result);
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        other = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawMatrix(288.35, 124.5, -55.625, 125.3, 47.8, 13.267, -36.02, -10.2, 23.856), result);
        
        sut = new RawMatrix();
        other = new RawMatrix();
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.getRawComponents().length);
        
        //invalid
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        final RawMatrix other1 = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        final RawMatrix other2 = new RawMatrix(15);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        final RawMatrix other3 = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "times", VectorInterface.class);
        
        RawVector other;
        RawVector result;
        
        //standard
        
        sut = new RawMatrix(8.6);
        other = new RawVector(15.0);
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawVector(129.0), result);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        other = new RawVector(15, 11);
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawVector(173.0, -7.515), result);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        other = new RawVector(15, 11, 8.5);
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawVector(112.6415, 190.15, 169.49), result);
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        other = new RawVector(8.6, 4, 7.101);
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawVector(233.3585, 85.804, -28.0182), result);
        
        sut = new RawMatrix();
        other = new RawVector();
        result = sut.times(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.getRawComponents().length);
        
        //invalid
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        final RawVector other1 = new RawVector(15, 11, 8.5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        final RawVector other2 = new RawVector(15);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        final RawVector other3 = new RawVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#scale(MatrixInterface)
     */
    @Test
    public void testScale() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "scale", MatrixInterface.class);
        
        RawMatrix other;
        RawMatrix result;
        
        //standard
        
        sut = new RawMatrix(8.6);
        other = new RawMatrix(15.0);
        result = sut.scale(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawMatrix(129.0), result);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        other = new RawMatrix(15, 11, 8.5, 3);
        result = sut.scale(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawMatrix(129, 44, -60.3585, 27), result);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        other = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        result = sut.scale(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawMatrix(129, 44, -60.3585, 27, 15.8, 15.6, -28.4, -8, 1.692), result);
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        other = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        result = sut.scale(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(
                new RawMatrix(129, 44, -60.3585, 27, 15.8, 15.6, -28.4, -8, 1.692), result);
        
        sut = new RawMatrix();
        other = new RawMatrix();
        result = sut.scale(other);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.getRawComponents().length);
        
        //invalid
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        final RawMatrix other1 = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.scale(other1));
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        final RawMatrix other2 = new RawMatrix(15);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.scale(other2));
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        final RawMatrix other3 = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.scale(other3));
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "determinant");
        
        //standard
        
        sut = new RawMatrix(8.6);
        Assert.assertEquals(8.6, sut.determinant().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(105.804, sut.determinant().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(-293.3228, sut.determinant().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(301.7, sut.determinant().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(1240.3973760000008, sut.determinant().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.determinant().doubleValue(), TestUtils.DELTA);
        
        //not resizable
        
        Matrix4 matrix4 = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(1240.3973760000008, matrix4.determinant(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#minor(int, int)
     * @see MatrixInterface#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "minor", int.class, int.class);
        TestUtils.assertMethodExists(
                MatrixInterface.class, "minor", int.class);
        
        //standard
        
        sut = new RawMatrix(8.6);
        Assert.assertEquals(0, sut.minor(0, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0, sut.minor(0).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(9, sut.minor(0, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-7.101, sut.minor(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(8.6, sut.minor(1, 1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(9, sut.minor(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-7.101, sut.minor(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(8.6, sut.minor(3).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(-17.62, sut.minor(0, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-19.23, sut.minor(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(97.449, sut.minor(1, 2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-18.8, sut.minor(2, 2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-17.62, sut.minor(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-19.23, sut.minor(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(97.449, sut.minor(7).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-18.8, sut.minor(8).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(20.62, sut.minor(0, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(21.4, sut.minor(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(34.5, sut.minor(1, 2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(85.5, sut.minor(2, 2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(20.62, sut.minor(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(21.4, sut.minor(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(34.5, sut.minor(7).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(85.5, sut.minor(8).doubleValue(), TestUtils.DELTA);
        
        //not resizable
        
        Matrix4 matrix4 = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertNoException(() -> {
            Assert.assertEquals(207.096, matrix4.minor(0, 0), TestUtils.DELTA);
            Assert.assertEquals(-25.74, matrix4.minor(1, 0), TestUtils.DELTA);
            Assert.assertEquals(88.30484, matrix4.minor(0, 1), TestUtils.DELTA);
            Assert.assertEquals(-25.74, matrix4.minor(1), TestUtils.DELTA);
            Assert.assertEquals(62.50656, matrix4.minor(7), TestUtils.DELTA);
            Assert.assertEquals(-267.63282, matrix4.minor(13), TestUtils.DELTA);
        });
        
        //invalid
        
        sut = new RawMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 0, 0), () ->
                sut.minor(0, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.minor(0));
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
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
     * @see MatrixInterface#minors()
     */
    @Test
    public void testMinors() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "minors");
        
        //standard
        
        sut = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.minors());
        
        sut = new RawMatrix(8.6);
        Assert.assertEquals(
                new RawMatrix(0.0), sut.minors());
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(
                new RawMatrix(9, -7.101, 4, 8.6), sut.minors());
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(
                new RawMatrix(-17.62, -19.23, 30.8, 39.265, 58.5011, 14.6, 29.802, 97.449, -18.8), sut.minors());
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(
                new RawMatrix(20.62, 21.4, 26.8, 33.4, 61.0, 20.0, -23.15, 34.5, 85.5), sut.minors());
    }
    
    /**
     * JUnit test of cofactorScalar.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#cofactorScalar(int, int)
     * @see MatrixInterface#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "cofactorScalar", int.class, int.class);
        TestUtils.assertMethodExists(
                MatrixInterface.class, "cofactorScalar", int.class);
        
        //standard
        
        sut = new RawMatrix(8.6);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(0).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(1, 1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(3).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1, 2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(2, 2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(7).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(8).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1, 2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(2, 2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(7).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(8).doubleValue(), TestUtils.DELTA);
        
        //invalid
        
        sut = new RawMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 0, 0), () ->
                sut.cofactorScalar(0, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.cofactorScalar(0));
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
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
     * @see MatrixInterface#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "cofactor");
        
        //standard
        
        sut = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.cofactor());
        
        sut = new RawMatrix(8.6);
        Assert.assertEquals(
                new RawMatrix(8.6), sut.cofactor());
        
        sut = new RawMatrix(8.6, 4, -7.101, 9);
        Assert.assertEquals(
                new RawMatrix(8.6, -4, 7.101, 9), sut.cofactor());
        
        sut = new RawMatrix(8.6, 4, -7.101, 9, 2, 3.9, 7.1, 5, 0.94);
        Assert.assertEquals(
                new RawMatrix(8.6, -4, -7.101, -9, 2, -3.9, 7.1, -5, 0.94), sut.cofactor());
        
        sut = new RawMatrix(15, 11, 8.5, 3, 7.9, 4, -4, -1.6, 1.8);
        Assert.assertEquals(
                new RawMatrix(15, -11, 8.5, -3, 7.9, -4, -4, 1.6, 1.8), sut.cofactor());
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "transpose");
        
        //standard
        
        sut = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.transpose());
        
        sut = new RawMatrix(8.6);
        Assert.assertEquals(
                new RawMatrix(8.6), sut.transpose());
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0);
        Assert.assertEquals(
                new RawMatrix(8.6, -7.101, 4.0, 9.0), sut.transpose());
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        Assert.assertEquals(
                new RawMatrix(8.6, 9.0, 7.1, 4.0, 2.0, 5.0, -7.101, 3.9, 0.94), sut.transpose());
        
        sut = new RawMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        Assert.assertEquals(
                new RawMatrix(15.0, 3.0, -4.0, 11.0, 7.9, -1.6, 8.5, 4.0, 1.8), sut.transpose());
    }
    
    /**
     * JUnit test of adjoint.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "adjoint");
        
        //standard
        
        sut = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.adjoint());
        
        sut = new RawMatrix(8.6);
        Assert.assertEquals(
                new RawMatrix(0.0), sut.adjoint());
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0);
        Assert.assertEquals(
                new RawMatrix(9.0, -4.0, 7.101, 8.6), sut.adjoint());
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        Assert.assertEquals(
                new RawMatrix(-17.62, -39.265, 29.802, 19.23, 58.5011, -97.449, 30.8, -14.6, -18.8), sut.adjoint());
        
        sut = new RawMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        Assert.assertEquals(
                new RawMatrix(20.62, -33.4, -23.15, -21.4, 61.0, -34.5, 26.8, -20.0, 85.5), sut.adjoint());
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "inverse");
        
        //standard
        
        sut = new RawMatrix(8.6);
        Assert.assertEquals(
                new RawMatrix(0.0), sut.inverse());
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0);
        Assert.assertEquals(
                new RawMatrix(0.08506294658046956, -0.03780575403576425, 0.06711466485199048, 0.08128237117689313), sut.inverse());
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        Assert.assertEquals(
                new RawMatrix(0.06007033888944194, 0.13386276143552425, -0.10160137568576325, -0.06555917235209809, -0.19944272998894047, 0.33222442987725465, -0.1050037705899439, 0.04977451463029808, 0.06409321061983589), sut.inverse());
        
        sut = new RawMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        Assert.assertEquals(
                new RawMatrix(0.06834603911170035, -0.11070599933708983, -0.07673185283394102, -0.07093138879681803, 0.20218760357971494, -0.11435200530328139, 0.08882996353994033, -0.06629101756711965, 0.2833941000994365), sut.inverse());
        
        //invalid
        
        sut = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, "The Raw Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
        
        sut = new RawMatrix(3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0);
        TestUtils.assertException(ArithmeticException.class, "The Raw Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "solveSystem", VectorInterface.class);
        
        //standard
        
        RawVector solution;
        
        sut = new RawMatrix(8.6);
        solution = new RawVector(4.0);
        Assert.assertEquals(
                new RawVector(0.0), sut.solveSystem(solution));
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0);
        solution = new RawVector(4.0, 19.6);
        Assert.assertEquals(
                new RawVector(-0.40074099277910114, 1.8615931344750674), sut.solveSystem(solution));
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        solution = new RawVector(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new RawVector(2.6837506392274992, -3.5819480585893766, 0.6692667600336557), sut.solveSystem(solution));
        
        sut = new RawMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        solution = new RawVector(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new RawVector(-2.0325757374875706, 3.47629101756712, -0.4412429565793835), sut.solveSystem(solution));
        
        //invalid
        
        sut = new RawMatrix();
        final RawVector solution1 = new RawVector();
        TestUtils.assertException(ArithmeticException.class, "The Raw Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution1));
        
        sut = new RawMatrix(3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0);
        final RawVector solution2 = new RawVector(4.0, 19.6, 1.774);
        TestUtils.assertException(ArithmeticException.class, "The Raw Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution2));
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        final RawVector solution3 = new RawVector(4.0, 19.6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution3), () ->
                sut.solveSystem(solution3));
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        final RawVector solution4 = new RawVector(4.0, 19.6, 1.774, 0.4951);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution4), () ->
                sut.solveSystem(solution4));
    }
    
    /**
     * JUnit test of transform.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "transform", VectorInterface.class);
        
        //standard
        
        RawVector vector;
        
        sut = new RawMatrix(8.6);
        vector = new RawVector(4.0);
        Assert.assertEquals(
                new RawVector(34.4), sut.transform(vector));
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0);
        vector = new RawVector(4.0, 19.6);
        Assert.assertEquals(
                new RawVector(-104.7796, 192.4), sut.transform(vector));
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        vector = new RawVector(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new RawVector(223.3954, 64.07, 49.70356), sut.transform(vector));
        
        sut = new RawMatrix(15.0, 11.0, 8.5, 3.0, 7.9, 4.0, -4.0, -1.6, 1.8);
        vector = new RawVector(4.0, 19.6, 1.774);
        Assert.assertEquals(
                new RawVector(111.704, 196.0016, 115.5932), sut.transform(vector));
        
        //invalid
        
        sut = new RawMatrix();
        final RawVector solution1 = new RawVector();
        TestUtils.assertNoException(() ->
                sut.transform(solution1));
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        final RawVector solution3 = new RawVector(4.0, 19.6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution3), () ->
                sut.transform(solution3));
        
        sut = new RawMatrix(8.6, 4.0, -7.101, 9.0, 2.0, 3.9, 7.1, 5.0, 0.94);
        final RawVector solution4 = new RawVector(4.0, 19.6, 1.774, 0.4951);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution4), () ->
                sut.transform(solution4));
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "redim", int.class);
        
        //standard
        
        sut = new RawMatrix(3);
        Assert.assertEquals(3, sut.getDimensionality());
        sut.redim(4);
        Assert.assertEquals(4, sut.getDimensionality());
        sut.redim(1);
        Assert.assertEquals(1, sut.getDimensionality());
        sut.redim(0);
        Assert.assertEquals(0, sut.getDimensionality());
        sut.redim(18);
        Assert.assertEquals(18, sut.getDimensionality());
        
        //persistence
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(
                new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9), sut);
        sut.redim(4);
        Assert.assertEquals(
                new RawMatrix(1, 2, 3, 0, 4, 5, 6, 0, 7, 8, 9, 0, 0, 0, 0, 0), sut);
        sut.redim(2);
        Assert.assertEquals(
                new RawMatrix(1, 2, 4, 5), sut);
        sut.redim(3);
        Assert.assertEquals(
                new RawMatrix(1, 2, 0, 4, 5, 0, 0, 0, 0), sut);
        sut.redim(3);
        Assert.assertEquals(
                new RawMatrix(1, 2, 0, 4, 5, 0, 0, 0, 0), sut);
        sut.redim(0);
        Assert.assertEquals(
                new RawMatrix(), sut);
        sut.redim(3);
        Assert.assertEquals(
                new RawMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), sut);
        
        //not resizable
        
        Matrix2 matrix2 = new Matrix2();
        Assert.assertEquals(2, matrix2.getDimensionality());
        Assert.assertFalse(matrix2.isResizeable());
        matrix2.redim(3);
        Assert.assertEquals(2, matrix2.getDimensionality());
        
        //invalid
        
        sut = new RawMatrix(3);
        Assert.assertEquals(3, sut.getDimensionality());
        sut.redim(-1);
        Assert.assertEquals(0, sut.getDimensionality());
        sut.redim(-7);
        Assert.assertEquals(0, sut.getDimensionality());
    }
    
    /**
     * JUnit test of subMatrix.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#subMatrix(int, int, int, int)
     * @see MatrixInterface#subMatrix(int, int, int)
     */
    @Test
    public void testSubMatrix() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "subMatrix", int.class, int.class, int.class, int.class);
        TestUtils.assertMethodExists(
                MatrixInterface.class, "subMatrix", int.class, int.class, int.class);
        
        //standard
        
        RawMatrix subMatrix;
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        
        subMatrix = sut.subMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new RawMatrix(1.0), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 1, 1);
        Assert.assertEquals(
                new RawMatrix(1, 2, 6, 7), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 2, 2);
        Assert.assertEquals(
                new RawMatrix(1, 2, 3, 6, 7, 8, 11, 12, 13), subMatrix);
        
        subMatrix = sut.subMatrix(2, 1, 4, 3);
        Assert.assertEquals(
                new RawMatrix(8, 9, 10, 13, 14, 15, 18, 19, 20), subMatrix);
        
        subMatrix = sut.subMatrix(1, 1, 4, 4);
        Assert.assertEquals(
                new RawMatrix(7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 4, 4);
        Assert.assertEquals(
                new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25), subMatrix);
        
        //dimensionality
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        
        subMatrix = sut.subMatrix(0, 0, 1);
        Assert.assertEquals(
                new RawMatrix(1.0), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 2);
        Assert.assertEquals(
                new RawMatrix(1, 2, 6, 7), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 3);
        Assert.assertEquals(
                new RawMatrix(1, 2, 3, 6, 7, 8, 11, 12, 13), subMatrix);
        
        subMatrix = sut.subMatrix(2, 1, 3);
        Assert.assertEquals(
                new RawMatrix(8, 9, 10, 13, 14, 15, 18, 19, 20), subMatrix);
        
        subMatrix = sut.subMatrix(1, 1, 4);
        Assert.assertEquals(
                new RawMatrix(7, 8, 9, 10, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25), subMatrix);
        
        subMatrix = sut.subMatrix(0, 0, 5);
        Assert.assertEquals(
                new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25), subMatrix);
        
        //not resizable
        
        Matrix4 matrix4 = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        
        Matrix subMatrix4 = matrix4.subMatrix(0, 0, 3, 3);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), subMatrix4);
        
        TestUtils.assertException(ArithmeticException.class, matrix4.getErrorHandler().dimensionalityNotEqualErrorMessage(matrix4, Matrix4.DIMENSIONALITY), () ->
                matrix4.subMatrix(1, 1, 2, 2));
        
        //invalid
        
        sut = new RawMatrix(4);
        
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
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Number[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}), () ->
                sut.subMatrix(0, 0, 2, 1));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Number[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}), () ->
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
     * @see MatrixInterface#dimensionalityToLength(int)
     * @see MatrixInterface#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "dimensionalityToLength", int.class);
        
        //standard
        
        sut = new RawMatrix(3);
        Assert.assertEquals(9, sut.dimensionalityToLength());
        Assert.assertEquals(9, sut.dimensionalityToLength(3));
        Assert.assertEquals(36, sut.dimensionalityToLength(6));
        
        sut = new RawMatrix(2);
        Assert.assertEquals(4, sut.dimensionalityToLength());
        Assert.assertEquals(4, sut.dimensionalityToLength(2));
        Assert.assertEquals(36, sut.dimensionalityToLength(6));
        
        //invalid
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.dimensionalityToLength());
        Assert.assertEquals(0, sut.dimensionalityToLength(0));
        Assert.assertEquals(0, sut.dimensionalityToLength(-1));
        Assert.assertEquals(0, sut.dimensionalityToLength(-84));
    }
    
    /**
     * JUnit test of dimensionalityToWidth.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#dimensionalityToWidth(int)
     * @see MatrixInterface#dimensionalityToWidth()
     */
    @Test
    public void testDimensionalityToWidth() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "dimensionalityToWidth", int.class);
        TestUtils.assertMethodExists(
                MatrixInterface.class, "dimensionalityToWidth");
        
        //standard
        
        sut = new RawMatrix(3);
        Assert.assertEquals(3, sut.dimensionalityToWidth());
        Assert.assertEquals(3, sut.dimensionalityToWidth(3));
        Assert.assertEquals(6, sut.dimensionalityToWidth(6));
        
        sut = new RawMatrix(2);
        Assert.assertEquals(2, sut.dimensionalityToWidth());
        Assert.assertEquals(2, sut.dimensionalityToWidth(2));
        Assert.assertEquals(6, sut.dimensionalityToWidth(6));
        
        //invalid
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.dimensionalityToWidth());
        Assert.assertEquals(0, sut.dimensionalityToWidth(0));
        Assert.assertEquals(0, sut.dimensionalityToWidth(-1));
        Assert.assertEquals(0, sut.dimensionalityToWidth(-84));
    }
    
    /**
     * JUnit test of dimensionalityToHeight.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#dimensionalityToHeight(int)
     * @see MatrixInterface#dimensionalityToHeight()
     */
    @Test
    public void testDimensionalityToHeight() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "dimensionalityToHeight", int.class);
        TestUtils.assertMethodExists(
                MatrixInterface.class, "dimensionalityToHeight");
        
        //standard
        
        sut = new RawMatrix(3);
        Assert.assertEquals(3, sut.dimensionalityToHeight());
        Assert.assertEquals(3, sut.dimensionalityToHeight(3));
        Assert.assertEquals(6, sut.dimensionalityToHeight(6));
        
        sut = new RawMatrix(2);
        Assert.assertEquals(2, sut.dimensionalityToHeight());
        Assert.assertEquals(2, sut.dimensionalityToHeight(2));
        Assert.assertEquals(6, sut.dimensionalityToHeight(6));
        
        //invalid
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.dimensionalityToHeight());
        Assert.assertEquals(0, sut.dimensionalityToHeight(0));
        Assert.assertEquals(0, sut.dimensionalityToHeight(-1));
        Assert.assertEquals(0, sut.dimensionalityToHeight(-84));
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#lengthToDimensionality(int)
     * @see MatrixInterface#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "lengthToDimensionality", int.class);
        
        //standard
        
        sut = new RawMatrix(3);
        Assert.assertEquals(3, sut.lengthToDimensionality());
        Assert.assertEquals(3, sut.lengthToDimensionality(9));
        Assert.assertEquals(6, sut.lengthToDimensionality(36));
        
        sut = new RawMatrix(2);
        Assert.assertEquals(2, sut.lengthToDimensionality());
        Assert.assertEquals(2, sut.lengthToDimensionality(4));
        Assert.assertEquals(6, sut.lengthToDimensionality(36));
        
        //invalid
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.lengthToDimensionality());
        Assert.assertEquals(0, sut.lengthToDimensionality(0));
        Assert.assertEquals(0, sut.lengthToDimensionality(-1));
        Assert.assertEquals(0, sut.lengthToDimensionality(-84));
    }
    
    /**
     * JUnit test of prettyPrint.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "prettyPrint");
        
        //standard
        
        List<String> print;
        
        sut = new RawMatrix();
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(1, print.size());
        Assert.assertEquals("[]", print.get(0));
        
        sut = new RawMatrix(1.0);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(1, print.size());
        Assert.assertEquals("[  1.0  ]", print.get(0));
        
        sut = new RawMatrix(1, 2, 3, 4);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(2, print.size());
        Assert.assertEquals("┌  1.0   2.0  ┐", print.get(0));
        Assert.assertEquals("└  3.0   4.0  ┘", print.get(1));
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(3, print.size());
        Assert.assertEquals("┌  1.0   2.0   3.0  ┐", print.get(0));
        Assert.assertEquals("│  4.0   5.0   6.0  │", print.get(1));
        Assert.assertEquals("└  7.0   8.0   9.0  ┘", print.get(2));
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(4, print.size());
        Assert.assertEquals("┌  1.0    2.0    3.0    4.0   ┐", print.get(0));
        Assert.assertEquals("│  5.0    6.0    7.0    8.0   │", print.get(1));
        Assert.assertEquals("│  9.0    10.0   11.0   12.0  │", print.get(2));
        Assert.assertEquals("└  13.0   14.0   15.0   16.0  ┘", print.get(3));
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(5, print.size());
        Assert.assertEquals("┌  1.0    2.0    3.0    4.0    5.0   ┐", print.get(0));
        Assert.assertEquals("│  6.0    7.0    8.0    9.0    10.0  │", print.get(1));
        Assert.assertEquals("│  11.0   12.0   13.0   14.0   15.0  │", print.get(2));
        Assert.assertEquals("│  16.0   17.0   18.0   19.0   20.0  │", print.get(3));
        Assert.assertEquals("└  21.0   22.0   23.0   24.0   25.0  ┘", print.get(4));
        
        sut = new RawMatrix(8.154119, 3, 1.1033, 8.5, 4.4465740112, 9.915422012, 16.112, 45.2947, 7);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(3, print.size());
        Assert.assertEquals("┌  8.154119   3.0            1.1033       ┐", print.get(0));
        Assert.assertEquals("│  8.5        4.4465740112   9.915422012  │", print.get(1));
        Assert.assertEquals("└  16.112     45.2947        7.0          ┘", print.get(2));
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#getRaw(int, int)
     */
    @Test
    public void testGetRaw() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "getRaw", int.class, int.class);
        
        //standard
        
        sut = Mockito.spy(RawMatrix.class);
        Whitebox.setInternalState(sut, "components", new Number[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        Whitebox.setInternalState(sut, "dimensionality", 3);
        
        sut.getRaw(0, 0);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(0), ArgumentMatchers.eq(0));
        Mockito.verify(sut).getRaw(ArgumentMatchers.eq(0));
        
        sut.getRaw(1, 1);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(1), ArgumentMatchers.eq(1));
        Mockito.verify(sut).getRaw(ArgumentMatchers.eq(4));
        
        sut.getRaw(2, 0);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(2), ArgumentMatchers.eq(0));
        Mockito.verify(sut).getRaw(ArgumentMatchers.eq(2));
        
        sut.getRaw(0, 2);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(0), ArgumentMatchers.eq(2));
        Mockito.verify(sut).getRaw(ArgumentMatchers.eq(6));
        
        //invalid
        
        TestUtils.assertNoException(() ->
                sut.getRaw(3, 1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 8), () ->
                sut.getRaw(1, 8));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -2, 0), () ->
                sut.getRaw(-2, 0));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, -3), () ->
                sut.getRaw(1, -3));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "get", int.class, int.class);
        
        //standard
        
        sut = Mockito.spy(RawMatrix.class);
        Whitebox.setInternalState(sut, "components", new Number[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        Whitebox.setInternalState(sut, "dimensionality", 3);
        
        sut.get(0, 0);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(0), ArgumentMatchers.eq(0));
        Mockito.verify(sut).get(ArgumentMatchers.eq(0));
        
        sut.get(1, 1);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(1), ArgumentMatchers.eq(1));
        Mockito.verify(sut).get(ArgumentMatchers.eq(4));
        
        sut.get(2, 0);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(2), ArgumentMatchers.eq(0));
        Mockito.verify(sut).get(ArgumentMatchers.eq(2));
        
        sut.get(0, 2);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(0), ArgumentMatchers.eq(2));
        Mockito.verify(sut).get(ArgumentMatchers.eq(6));
        
        //invalid
        
        TestUtils.assertNoException(() ->
                sut.get(3, 1));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 8), () ->
                sut.get(1, 8));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -2, 0), () ->
                sut.get(-2, 0));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, -3), () ->
                sut.get(1, -3));
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "getHeight");
        
        //standard
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(3, sut.getDimensionality());
        Assert.assertEquals(3, sut.getHeight());
        Assert.assertEquals(sut.getDimensionality(), sut.getHeight());
        
        sut = new RawMatrix(1, 2, 3, 4);
        Assert.assertEquals(2, sut.getDimensionality());
        Assert.assertEquals(2, sut.getHeight());
        Assert.assertEquals(sut.getDimensionality(), sut.getHeight());
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "getWidth");
        
        //standard
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals(3, sut.getDimensionality());
        Assert.assertEquals(3, sut.getWidth());
        Assert.assertEquals(sut.getDimensionality(), sut.getWidth());
        
        sut = new RawMatrix(1, 2, 3, 4);
        Assert.assertEquals(2, sut.getDimensionality());
        Assert.assertEquals(2, sut.getWidth());
        Assert.assertEquals(sut.getDimensionality(), sut.getWidth());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "getNamePlural");
        
        //standard
        Assert.assertTrue(new RawMatrix().getNamePlural().endsWith("Matrices"));
        Assert.assertTrue(new IntMatrix().getNamePlural().endsWith("Matrices"));
        Assert.assertTrue(new BigMatrix().getNamePlural().endsWith("Matrices"));
        Assert.assertTrue(new Matrix().getNamePlural().endsWith("Matrices"));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        TestUtils.assertMethodExists(
                MatrixInterface.class, "set", int.class, int.class, Number.class);
        
        //standard
        
        sut = Mockito.spy(RawMatrix.class);
        Whitebox.setInternalState(sut, "components", new Number[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        Whitebox.setInternalState(sut, "dimensionality", 3);
        
        sut.set(0, 0, 5);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(0), ArgumentMatchers.eq(0));
        Mockito.verify(sut).set(ArgumentMatchers.eq(0), ArgumentMatchers.eq(5));
        
        sut.set(1, 1, 5);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(1), ArgumentMatchers.eq(1));
        Mockito.verify(sut).set(ArgumentMatchers.eq(4), ArgumentMatchers.eq(5));
        
        sut.set(2, 0, 5);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(2), ArgumentMatchers.eq(0));
        Mockito.verify(sut).set(ArgumentMatchers.eq(2), ArgumentMatchers.eq(5));
        
        sut.set(0, 2, 5);
        Mockito.verify(sut).toIndex(ArgumentMatchers.eq(0), ArgumentMatchers.eq(2));
        Mockito.verify(sut).set(ArgumentMatchers.eq(6), ArgumentMatchers.eq(5));
        
        //invalid
        
        TestUtils.assertNoException(() ->
                sut.set(3, 1, 5));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 8), () ->
                sut.set(1, 8, 5));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -2, 0), () ->
                sut.set(-2, 0, 5));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, -3), () ->
                sut.set(1, -3, 5));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#createInstance(int, Class)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testCreateInstance() throws Exception {
        MatrixInterface<?, ?> instance;
        
        //standard
        
        instance = MatrixInterface.createInstance(3, RawMatrix.class);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance instanceof RawMatrix);
        Assert.assertEquals(3, instance.getDimensionality());
        
        instance = MatrixInterface.createInstance(2, IntMatrix.class);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance instanceof IntMatrix);
        Assert.assertEquals(2, instance.getDimensionality());
        
        instance = MatrixInterface.createInstance(2, BigMatrix.class);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance instanceof BigMatrix);
        Assert.assertEquals(2, instance.getDimensionality());
        
        instance = MatrixInterface.createInstance(4, Matrix.class);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance instanceof Matrix);
        Assert.assertEquals(4, instance.getDimensionality());
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                MatrixInterface.createInstance(3, null));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#identity(int, Class)
     */
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testIdentity() throws Exception {
        //standard
        
        Assert.assertEquals(
                new RawMatrix(1, 0, 0, 0, 1, 0, 0, 0, 1), MatrixInterface.identity(3, RawMatrix.class));
        Assert.assertEquals(
                new IntMatrix(1, 0, 0, 1), MatrixInterface.identity(2, IntMatrix.class));
        Assert.assertEquals(
                new BigMatrix(1, 0, 0, 1), MatrixInterface.identity(2, BigMatrix.class));
        Assert.assertEquals(
                new Matrix(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1), MatrixInterface.identity(4, Matrix.class));
        
        Assert.assertNotEquals(
                new RawMatrix(1, 0, 0, 0, 1, 0, 0, 0, 1), MatrixInterface.identity(3, IntMatrix.class));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                MatrixInterface.identity(3, null));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#origin(int, Class)
     */
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testOrigin() throws Exception {
        //standard
        
        Assert.assertEquals(
                new RawMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), MatrixInterface.origin(3, RawMatrix.class));
        Assert.assertEquals(
                new IntMatrix(0, 0, 0, 0), MatrixInterface.origin(2, IntMatrix.class));
        Assert.assertEquals(
                new BigMatrix(0, 0, 0, 0), MatrixInterface.origin(2, BigMatrix.class));
        Assert.assertEquals(
                new Matrix(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), MatrixInterface.origin(4, Matrix.class));
        
        Assert.assertNotEquals(
                new RawMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), MatrixInterface.origin(3, IntMatrix.class));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                MatrixInterface.origin(3, null));
    }
    
    /**
     * JUnit test of signChart.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#signChart(int, Class)
     */
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testSignChart() throws Exception {
        //standard
        
        Assert.assertEquals(
                new RawMatrix(1, -1, 1, -1, 1, -1, 1, -1, 1), MatrixInterface.signChart(3, RawMatrix.class));
        Assert.assertEquals(
                new IntMatrix(1, -1, -1, 1), MatrixInterface.signChart(2, IntMatrix.class));
        Assert.assertEquals(
                new BigMatrix(1, -1, -1, 1), MatrixInterface.signChart(2, BigMatrix.class));
        Assert.assertEquals(
                new Matrix(1, -1, 1, -1, -1, 1, -1, 1, 1, -1, 1, -1, -1, 1, -1, 1), MatrixInterface.signChart(4, Matrix.class));
        
        Assert.assertNotEquals(
                new RawMatrix(1, -1, 1, -1, 1, -1, 1, -1, 1), MatrixInterface.signChart(3, IntMatrix.class));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                MatrixInterface.signChart(3, null));
    }
    
}
