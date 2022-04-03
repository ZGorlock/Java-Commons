/*
 * File:    RawMatrixTest.java
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
import commons.math.component.handler.math.RawComponentMathHandler;
import commons.math.component.vector.BigVector;
import commons.math.component.vector.IntVector;
import commons.math.component.vector.RawVector;
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
 * JUnit test of RawMatrix.
 *
 * @see RawMatrix
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({RawMatrix.class})
public class RawMatrixTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RawMatrixTest.class);
    
    
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
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#RawMatrix(Number...)
     * @see RawMatrix#RawMatrix(List)
     * @see RawMatrix#RawMatrix(RawMatrix)
     * @see RawMatrix#RawMatrix(RawMatrix)
     * @see RawMatrix#RawMatrix(int)
     * @see RawMatrix#RawMatrix()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        RawMatrix matrix = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        TestUtils.assertArrayEquals(
                matrix.getRawComponents(),
                new Number[] {0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4});
        Assert.assertEquals(3, matrix.getDimensionality());
        
        //list of components
        List<Number> values = Arrays.asList(0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06, 4.4);
        RawMatrix matrix2 = new RawMatrix(values);
        TestUtils.assertArrayEquals(
                matrix2.getRawComponents(),
                new Number[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06, 4.4});
        Assert.assertEquals(3, matrix2.getDimensionality());
        
        //raw matrix
        RawMatrix matrix3 = new RawMatrix(new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4));
        TestUtils.assertArrayEquals(
                matrix3.getRawComponents(),
                new Number[] {0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4});
        Assert.assertEquals(3, matrix3.getDimensionality());
        
        //matrix
        RawMatrix matrix4 = new RawMatrix(new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4));
        TestUtils.assertArrayEquals(
                matrix4.getRawComponents(),
                new Number[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06, 4.4});
        Assert.assertEquals(3, matrix4.getDimensionality());
        
        //dimensionality
        RawMatrix matrixDimensionality = new RawMatrix(4);
        TestUtils.assertArrayEquals(
                matrixDimensionality.getRawComponents(),
                new Number[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        Assert.assertEquals(4, matrixDimensionality.getDimensionality());
        
        //empty
        RawMatrix matrixDefault = new RawMatrix();
        TestUtils.assertArrayEquals(
                matrixDefault.getRawComponents(),
                new Number[] {});
        Assert.assertEquals(0, matrixDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(matrix, matrix2);
        Assert.assertEquals(matrix2, matrix3);
        Assert.assertEquals(matrix3, matrix4);
        
        //invalid
        
        TestUtils.assertException(ArithmeticException.class, new RawMatrix().getErrorHandler().componentLengthNotSquareErrorMessage(new Number[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06}), () ->
                new RawMatrix(0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06));
        
        TestUtils.assertException(NullPointerException.class, () ->
                new RawMatrix(Arrays.asList(0.884, null, 1.1, -9.3)));
        TestUtils.assertException(NullPointerException.class, () ->
                new RawMatrix((RawMatrix) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new RawMatrix((Matrix) null));
    }
    
    /**
     * JUnit test of matrixString.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#matrixString()
     */
    @Test
    public void testMatrixString() throws Exception {
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
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new RawMatrix(1.0);
        Assert.assertEquals("[<1.0>]", sut.toString());
        
        sut = new RawMatrix(1, 2, 3, 4);
        Assert.assertEquals("[<1.0, 2.0>, <3.0, 4.0>]", sut.toString());
        
        sut = new RawMatrix(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("[<1.0, 2.0, 3.0>, <4.0, 5.0, 6.0>, <7.0, 8.0, 9.0>]", sut.toString());
        
        sut = new RawMatrix(8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455);
        Assert.assertEquals("[<8.15, 77.1654, 3.0>, <3.66, 7.15, 890.1>, <11.0, 7.9888, 0.79455>]", sut.toString());
        
        //empty
        
        sut = new RawMatrix();
        Assert.assertEquals("[]", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new RawMatrix(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.equals(other));
        
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
     * @see RawMatrix#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new RawMatrix(8.5, -1.944, 2.67, 8);
        
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
     * @see RawMatrix#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new RawMatrix(8.5, -1.944, 2.67, 8);
        
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
     * @see RawMatrix#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new RawMatrix(8.5, -1.944, 2.67, 8);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
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
     * @see RawMatrix#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new RawMatrix(8.1, 6.6, 5, 1.09);
        RawMatrix clone = sut.cloned();
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
     * @see RawMatrix#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new RawMatrix(8.1, 6.6, 5, 1.09);
        RawMatrix emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        TestUtils.assertArrayEquals(
                emptyCopy.getRawComponents(),
                RawMatrix.origin(sut.getDimensionality()).getRawComponents());
        Assert.assertNotSame(sut, emptyCopy);
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
        //standard
        
        sut = new RawMatrix();
        Assert.assertEquals(
                new RawVector(), sut.newVector());
        
        sut = new RawMatrix(8.1);
        Assert.assertEquals(
                new RawVector(0.0), sut.newVector());
        
        sut = new RawMatrix(8.1, 6.6, 5, 1.09);
        Assert.assertEquals(
                new RawVector(0.0, 0.0), sut.newVector());
        
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(
                new RawVector(0.0, 0.0, 0.0), sut.newVector());
        
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertEquals(
                new RawVector(0.0, 0.0, 0.0, 0.0), sut.newVector());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new RawMatrix();
        
        //standard
        Assert.assertEquals(
                new RawMatrix(), sut.createNewInstance(0));
        Assert.assertEquals(
                new RawMatrix(0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new RawMatrix(), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
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
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        RawMatrix reversed;
        
        //standard
        
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {4.4, 5.06, -0.77, 8, 1.61, -9.3, 1.1, 2, 0.884});
        
        sut = new RawMatrix(4.4, 5.06, -0.77, 8, 1.61, -9.3, 1.1, 2, 0.884);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4});
        
        sut = new RawMatrix(0, 1, 0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {1, 0, 1, 0});
        
        sut = new RawMatrix(5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {5.501});
        
        sut = new RawMatrix();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {});
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        RawMatrix other;
        
        //standard
        
        sut = new RawMatrix(8.5);
        other = new RawMatrix(9.9);
        Assert.assertEquals(1.4, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(9.545700602889239, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(12.603983497291638, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix();
        other = new RawMatrix();
        Assert.assertEquals(0.0, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        //invalid
        
        sut = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        final RawMatrix other1 = new RawMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new RawMatrix(8.5);
        final RawMatrix other2 = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix other3 = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        RawMatrix other;
        
        //standard
        
        sut = new RawMatrix(8.5);
        other = new RawMatrix(9.9);
        Assert.assertEquals(
                new RawMatrix(9.2), sut.midpoint(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawMatrix(9.2, 3.75, -2.246, 8.0), sut.midpoint(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new RawMatrix(9.2, 3.75, -2.246, 8.0, 3.05, 6.0, 3.55, 7.2, 6.6), sut.midpoint(other));
        
        sut = new RawMatrix();
        other = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.midpoint(other));
        
        //invalid
        
        sut = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        final RawMatrix other1 = new RawMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new RawMatrix(8.5);
        final RawMatrix other2 = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix other3 = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#average(List)
     * @see RawMatrix#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        RawMatrix other1;
        RawMatrix other2;
        RawMatrix other3;
        RawMatrix other4;
        
        //standard
        
        sut = new RawMatrix(8.5);
        other1 = new RawMatrix(9.9);
        other2 = new RawMatrix(1.8);
        other3 = new RawMatrix(11.7);
        Assert.assertEquals(
                new RawMatrix(7.975), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new RawMatrix(7.975), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other1 = new RawMatrix(9.9, 6, 0.514, 4.9);
        other2 = new RawMatrix(1.8, 4.77, 0.514, 2.895);
        other3 = new RawMatrix(11.7, 0.447, 7.16, 8);
        Assert.assertEquals(
                new RawMatrix(7.975, 3.17925, 0.7955, 6.72375), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new RawMatrix(7.975, 3.17925, 0.7955, 6.72375), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other1 = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        other2 = new RawMatrix(1.8, 4.77, 0.514, 2.895, 3, 4.9, 6, 0.8, 7.1);
        other3 = new RawMatrix(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5);
        Assert.assertEquals(
                new RawMatrix(7.975, 3.17925, 0.7955, 6.72375, 2.975, 7.225, 3.4675, 5.45, 6.325), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new RawMatrix(7.975, 3.17925, 0.7955, 6.72375, 2.975, 7.225, 3.4675, 5.45, 6.325), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new RawMatrix();
        other1 = new RawMatrix();
        other2 = new RawMatrix();
        other3 = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new RawMatrix(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        final RawMatrix otherF11 = new RawMatrix(9.9, 6, 0.514, 4.9);
        final RawMatrix otherF12 = new RawMatrix(1.8, 4.77, 0.514, 2.895);
        final RawMatrix otherF13 = new RawMatrix(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix otherF21 = new RawMatrix(9.9, 6, 0.514, 4.9);
        final RawMatrix otherF22 = new RawMatrix(1.8, 4.77, 0.514, 2.895, 3, 4.9, 6, 0.8, 7.1);
        final RawMatrix otherF23 = new RawMatrix(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix otherF31 = new RawMatrix(9.9, 6, 0.514, 4.9);
        final RawMatrix otherF33 = new RawMatrix(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new RawMatrix(8.5);
        Assert.assertEquals(8.5, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(16.094, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(46.494, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.sum().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new RawMatrix(8.5);
        Assert.assertEquals(72.25, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(222.770036, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(445.35003600000005, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.squareSum().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        RawMatrix other;
        
        //standard
        
        sut = new RawMatrix(8.5);
        other = new RawMatrix(9.9);
        Assert.assertEquals(
                new RawMatrix(18.4), sut.plus(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawMatrix(18.4, 7.5, -4.492, 16.0), sut.plus(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new RawMatrix(18.4, 7.5, -4.492, 16.0, 6.1, 12.0, 7.1, 14.4, 13.2), sut.plus(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new RawMatrix(9.5, 2.5, -4.006, 12.1), sut.plus(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1), sut.plus(other));
        
        sut = new RawMatrix();
        other = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.plus(other));
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix other1 = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final RawMatrix other2 = new RawMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new RawMatrix(8.5);
        final RawMatrix other3 = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        RawMatrix other;
        
        //standard
        
        sut = new RawMatrix(8.5);
        other = new RawMatrix(9.9);
        Assert.assertEquals(
                new RawMatrix(-1.4), sut.minus(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawMatrix(-1.4, -4.5, -5.52, 6.2), sut.minus(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new RawMatrix(-1.4, -4.5, -5.52, 6.2, 2.5, 6.0, -3.9, 0.2, 3.2), sut.minus(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new RawMatrix(7.5, 0.5, -6.006, 10.1), sut.minus(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1), sut.minus(other));
        
        sut = new RawMatrix();
        other = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.minus(other));
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix other1 = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final RawMatrix other2 = new RawMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new RawMatrix(8.5);
        final RawMatrix other3 = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#times(RawMatrix)
     */
    @Test
    public void testTimes() throws Exception {
        RawMatrix other;
        
        //standard
        
        sut = new RawMatrix(8.5);
        other = new RawMatrix(9.9);
        Assert.assertEquals(
                new RawMatrix(84.15), sut.times(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawMatrix(84.921, 58.35, -43.854, 24.354), sut.times(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new RawMatrix(63.967, 18.1574, -16.161, 180.46, 138.24, 63.6054, 96.71, 80.96, 63.7224), sut.times(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new RawMatrix(10.0, 10.0, 6.094, 6.094), sut.times(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0), sut.times(other));
        
        sut = new RawMatrix();
        other = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.times(other));
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix other1 = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final RawMatrix other2 = new RawMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new RawMatrix(8.5);
        final RawMatrix other3 = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((RawMatrix) null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        RawVector other;
        
        //standard
        
        sut = new RawMatrix(8.5);
        other = new RawVector(9.9);
        Assert.assertEquals(
                new RawVector(84.15), sut.times(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(9.9, 6);
        Assert.assertEquals(
                new RawVector(93.15, 17.0406), sut.times(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new RawVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new RawVector(90.576916, 140.316, 63.8548), sut.times(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(1, 1);
        Assert.assertEquals(
                new RawVector(10.0, 6.094), sut.times(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(0, 0);
        Assert.assertEquals(
                new RawVector(0.0, 0.0), sut.times(other));
        
        sut = new RawMatrix();
        other = new RawVector();
        Assert.assertEquals(
                new RawVector(), sut.times(other));
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawVector other1 = new RawVector(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final RawVector other2 = new RawVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new RawMatrix(8.5);
        final RawVector other3 = new RawVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((RawVector) null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new RawMatrix(8.5);
        Assert.assertEquals(
                new RawMatrix(34.0), sut.scale(4));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new RawMatrix(138.04, 24.36, -81.29744, 180.264), sut.scale(16.24));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new RawMatrix(8.5391, 1.5069, -5.0290276, 11.15106, 4.31978, 9.0414, 1.60736, 7.33358, 8.23772), sut.scale(1.0046));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1), sut.scale(1));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0), sut.scale(0));
        
        sut = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.scale(7.1));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#scale(MatrixInterface)
     */
    @Test
    public void testMatrixScale() throws Exception {
        RawMatrix other;
        
        //standard
        
        sut = new RawMatrix(8.5);
        other = new RawMatrix(9.9);
        Assert.assertEquals(
                new RawMatrix(84.15), sut.scale(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawMatrix(84.15, 9.0, -2.573084, 54.39), sut.scale(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new RawMatrix(84.15, 9.0, -2.573084, 54.39, 7.74, 27.0, 8.8, 51.83, 41.0), sut.scale(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1), sut.scale(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(0, 0, 0, 0);
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0), sut.scale(other));
        
        sut = new RawMatrix();
        other = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.scale(other));
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix other1 = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.scale(other1));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final RawMatrix other2 = new RawMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.scale(other2));
        
        sut = new RawMatrix(8.5);
        final RawMatrix other3 = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.scale(other3));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.scale((RawMatrix) null));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        RawMatrix other;
        
        //standard
        
        sut = new RawMatrix(8.5);
        other = new RawMatrix(9.9);
        Assert.assertEquals(
                new RawMatrix(0.858585858586), sut.dividedBy(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawMatrix(0.858585858586, 0.25, -9.739299610895, 2.265306122449), sut.dividedBy(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        other = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        Assert.assertEquals(
                new RawMatrix(0.858585858586, 0.25, -9.739299610895, 2.265306122449, 2.388888888889, 3.0, 0.290909090909, 1.028169014085, 1.64), sut.dividedBy(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        other = new RawMatrix(1, 1, 1, 1);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1), sut.dividedBy(other));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix other0 = new RawMatrix(0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new RawMatrix();
        other = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.dividedBy(other));
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix other1 = new RawMatrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final RawMatrix other2 = new RawMatrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new RawMatrix(8.5);
        final RawMatrix other3 = new RawMatrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new RawMatrix(8.5);
        Assert.assertEquals(
                new RawMatrix(9.0), sut.round());
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new RawMatrix(9.0, 2.0, -5.0, 11.0), sut.round());
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        Assert.assertEquals(
                new RawMatrix(9.0, 2.0, -5.0, 11.0, 4.0, 9.0, 2.0, 7.0, 8.0), sut.round());
        
        sut = new RawMatrix();
        Assert.assertEquals(
                new RawMatrix(), sut.round());
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
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
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#minor(int, int)
     * @see RawMatrix#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
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
     * @see RawMatrix#minors()
     */
    @Test
    public void testMinors() throws Exception {
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
     * @see RawMatrix#cofactorScalar(int, int)
     * @see RawMatrix#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
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
     * @see RawMatrix#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
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
     * @see RawMatrix#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
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
     * @see RawMatrix#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
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
     * @see RawMatrix#inverse()
     */
    @Test
    public void testInverse() throws Exception {
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
     * @see RawMatrix#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        RawVector solution;
        
        //standard
        
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
     * @see RawMatrix#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        RawVector vector;
        
        //standard
        
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
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        RawMatrix copy;
        
        //standard
        
        sut = new RawMatrix(8.5);
        copy = new RawMatrix(1);
        sut.copy(copy);
        Assert.assertEquals(
                new RawMatrix(8.5), copy);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        copy = new RawMatrix(2);
        sut.copy(copy);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        copy = new RawMatrix(3);
        sut.copy(copy);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), copy);
        
        sut = new RawMatrix();
        copy = new RawMatrix();
        sut.copy(copy);
        Assert.assertEquals(
                new RawMatrix(), copy);
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix copy1 = new RawMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final RawMatrix copy2 = new RawMatrix(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                sut.copy(copy2));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#copyMeta(RawMatrix)
     */
    @Test
    public void testCopyMeta() throws Exception {
        RawMatrix component1 = new RawMatrix(8.1, 6.6, 7.0, 2.6);
        Assert.assertEquals(2, component1.getDimensionality());
        TestUtils.assertArrayEquals(
                component1.getRawComponents(),
                new Number[] {8.1, 6.6, 7.0, 2.6});
        
        RawMatrix component2 = new RawMatrix(9.1, 6.3, 1.7, 8.2);
        component1.copyMeta(component2);
        Assert.assertEquals(2, component2.getDimensionality());
        TestUtils.assertArrayEquals(
                component2.getRawComponents(),
                new Number[] {9.1, 6.3, 1.7, 8.2});
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(3);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, 0, -5.006, 11.1, 0, 0, 0, 0), sut);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(4);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, 0, 0, -5.006, 11.1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0), sut);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(2);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(1);
        Assert.assertEquals(
                new RawMatrix(8.5), sut);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(0);
        Assert.assertEquals(
                new RawMatrix(), sut);
        
        sut = new RawMatrix();
        sut.redim(3);
        Assert.assertEquals(
                new RawMatrix(0, 0, 0, 0, 0, 0, 0, 0, 0), sut);
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        sut.redim(-1);
        Assert.assertEquals(
                new RawMatrix(), sut);
        
        sut = Mockito.spy(RawMatrix.class);
        sut.setComponents(new Number[] {8.5, 1.5, -5.006, 11.1});
        Mockito.when(sut.isResizeable()).thenReturn(false);
        Assert.assertFalse(sut.isResizeable());
        sut.redim(3);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1), sut);
    }
    
    /**
     * JUnit test of subMatrix.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#subMatrix(int, int, int, int)
     */
    @Test
    public void testSubMatrix() throws Exception {
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
     * @see RawMatrix#dimensionalityToLength(int)
     * @see RawMatrix#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
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
     * @see RawMatrix#dimensionalityToWidth(int)
     * @see RawMatrix#dimensionalityToWidth()
     */
    @Test
    public void testDimensionalityToWidth() throws Exception {
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
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
     * @see RawMatrix#dimensionalityToHeight(int)
     * @see RawMatrix#dimensionalityToHeight()
     */
    @Test
    public void testDimensionalityToHeight() throws Exception {
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
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
     * @see RawMatrix#lengthToDimensionality(int)
     * @see RawMatrix#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
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
     * @see RawMatrix#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        
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
     * @see RawMatrix#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
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
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        RawMatrix component;
        
        //standard
        component = new RawMatrix(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Number[] {8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509});
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        RawMatrix component;
        
        //standard
        
        component = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.5, 1.5, -5.006, 11.1});
        
        component = new RawMatrix(8.5);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.5});
        
        component = new RawMatrix();
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {});
        
        component = new RawMatrix(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999});
        
        component = new RawMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.5, 1.5, -5.006, 11.1});
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        RawMatrix component;
        
        //standard
        component = new RawMatrix(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        TestUtils.assertArrayEquals(
                Arrays.stream(component.getPrimitiveComponents()).boxed().toArray(),
                new Double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509});
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getRaw(int)
     * @see RawMatrix#getRaw(int, int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(8.5, sut.getRaw(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-5.006, sut.getRaw(2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.getRaw(3).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.getRaw(1, 1).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertEquals(8.500000000001, sut.getRaw(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-5.005999999999, sut.getRaw(2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.099999999999, sut.getRaw(3).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.099999999999, sut.getRaw(1, 1).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-5.0059999999999999999999, sut.getRaw(2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.0999999999999999999999, sut.getRaw(3).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.0999999999999999999999, sut.getRaw(1, 1).doubleValue(), TestUtils.DELTA);
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
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
        
        sut = new RawMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.getRaw(0));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#get(int)
     * @see RawMatrix#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(8.5, sut.get(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-5.006, sut.get(2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.get(3).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.get(1, 1).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        Assert.assertEquals(8.500000000001, sut.get(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-5.005999999999, sut.get(2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.099999999999, sut.get(3).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.099999999999, sut.get(1, 1).doubleValue(), TestUtils.DELTA);
        
        sut = new RawMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(8.5, sut.get(0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-5.006, sut.get(2).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.get(3).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1, 0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(11.1, sut.get(1, 1).doubleValue(), TestUtils.DELTA);
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
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
        
        sut = new RawMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.get(0));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new RawMatrix(5.501);
        Assert.assertEquals(1, sut.getDimensionality());
        
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(3, sut.getDimensionality());
        
        sut = new RawMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        //standard
        
        sut = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(2, sut.getHeight());
        
        sut = new RawMatrix(5.501);
        Assert.assertEquals(1, sut.getHeight());
        
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(3, sut.getHeight());
        
        sut = new RawMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(2, sut.getHeight());
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.getHeight());
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        //standard
        
        sut = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(2, sut.getWidth());
        
        sut = new RawMatrix(5.501);
        Assert.assertEquals(1, sut.getWidth());
        
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(3, sut.getWidth());
        
        sut = new RawMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(2, sut.getWidth());
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.getWidth());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new RawMatrix(5.501);
        Assert.assertEquals(1, sut.getLength());
        
        sut = new RawMatrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertEquals(9, sut.getLength());
        
        sut = new RawMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new RawMatrix();
        Assert.assertEquals(0, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new RawMatrix();
        Assert.assertEquals(RawMatrix.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new RawMatrix();
        Assert.assertEquals(Number.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new RawMatrix();
        Assert.assertTrue(sut.getHandler() instanceof RawComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new RawMatrix();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new RawMatrix();
        Assert.assertEquals("Raw Matrix", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new RawMatrix();
        Assert.assertEquals("Raw Matrices", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new RawMatrix();
        Assert.assertEquals(1E-12, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new RawMatrix();
        Assert.assertTrue(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        RawMatrix component;
        Number[] newComponents;
        
        //standard
        
        component = new RawMatrix(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {5.6, 6.7, 7.8, 8.9};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Number[] {5.6, 6.7, 7.8, 8.9});
        Assert.assertEquals(2, component.getDimensionality());
        
        component = new RawMatrix(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06, 4.4};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Number[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06, 4.4});
        Assert.assertEquals(3, component.getDimensionality());
        
        component = new RawMatrix(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {};
        Assert.assertEquals(2, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Number[] {});
        Assert.assertEquals(0, component.getDimensionality());
        
        //invalid
        
        final RawMatrix component2 = new RawMatrix(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(component2.isResizeable());
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#set(int, Number)
     * @see RawMatrix#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.5, 1.5, -5.006, 11.1});
        sut.set(0, 5.77);
        sut.set(1, 3.0);
        sut.set(2, 0.997);
        sut.set(3, 4.5);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {5.77, 3.0, 0.997, 4.5});
        
        sut = new RawMatrix(8.5);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.5});
        sut.set(0, 5.77);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {5.77});
        
        sut = new RawMatrix(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999});
        sut.set(0, 5.769999999996);
        sut.set(1, 3.000000000001);
        sut.set(2, 8.300000000001);
        sut.set(3, 5.2269999999996);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {5.769999999996, 3.000000000001, 8.300000000001, 5.2269999999996});
        
        sut = new RawMatrix(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999});
        sut.set(0, 6.5000000000000000000000001);
        sut.set(1, -1.49999999999999999999996);
        sut.set(2, 3.0059999999999999999999);
        sut.set(3, 8.1059999999999999999999);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {6.5, -1.5, 3.006, 8.106});
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.5, 1.5, -5.006, 11.1});
        sut.set(0, 0, 5.77);
        sut.set(1, 0, 3.0);
        sut.set(0, 1, 0.997);
        sut.set(1, 1, 4.5);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {5.77, 3.0, 0.997, 4.5});
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.set(4, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 5.5));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 1, 3), () ->
                sut.set(1, 3, 5.0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 2, 2), () ->
                sut.set(2, 2, 5.0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, 8, 9), () ->
                sut.set(8, 9, 5.0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(sut, -1, -2), () ->
                sut.set(-1, -2, 5.0));
        
        sut = new RawMatrix();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.set(0, 5.5));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        RawMatrix copy;
        
        //standard
        
        sut = new RawMatrix(8.5);
        copy = new RawMatrix(1);
        RawMatrix.copy(sut, copy);
        Assert.assertEquals(
                new RawMatrix(8.5), copy);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        copy = new RawMatrix(2);
        RawMatrix.copy(sut, copy);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        copy = new RawMatrix(3);
        RawMatrix.copy(sut, copy);
        Assert.assertEquals(
                new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2), copy);
        
        sut = new RawMatrix();
        copy = new RawMatrix();
        RawMatrix.copy(sut, copy);
        Assert.assertEquals(
                new RawMatrix(), copy);
        
        //invalid
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final RawMatrix copy1 = new RawMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                RawMatrix.copy(sut, copy1));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2);
        final RawMatrix copy2 = new RawMatrix(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                RawMatrix.copy(sut, copy2));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final BigVector copy3 = new BigVector(4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy3), () ->
                RawMatrix.copy(sut, copy3));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        final BigMatrix copy4 = new BigMatrix(4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy4), () ->
                RawMatrix.copy(sut, copy4));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                RawMatrix.copy(sut, null));
        
        sut = new RawMatrix(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                RawMatrix.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new RawMatrix(), RawMatrix.createInstance(0));
        Assert.assertEquals(
                new RawMatrix(0.0), RawMatrix.createInstance(1));
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0), RawMatrix.createInstance(2));
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), RawMatrix.createInstance(3));
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), RawMatrix.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new RawMatrix(), RawMatrix.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new RawMatrix(), RawMatrix.identity(0));
        Assert.assertEquals(
                new RawMatrix(1.0), RawMatrix.identity(1));
        Assert.assertEquals(
                new RawMatrix(1.0, 0.0, 0.0, 1.0), RawMatrix.identity(2));
        Assert.assertEquals(
                new RawMatrix(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0), RawMatrix.identity(3));
        Assert.assertEquals(
                new RawMatrix(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0), RawMatrix.identity(4));
        
        //invalid
        Assert.assertEquals(
                new RawMatrix(), RawMatrix.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new RawMatrix(), RawMatrix.origin(0));
        Assert.assertEquals(
                new RawMatrix(0.0), RawMatrix.origin(1));
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0), RawMatrix.origin(2));
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), RawMatrix.origin(3));
        Assert.assertEquals(
                new RawMatrix(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), RawMatrix.origin(4));
        
        //invalid
        Assert.assertEquals(
                new RawMatrix(), RawMatrix.origin(-1));
    }
    
    /**
     * JUnit test of signChart.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#signChart(int)
     */
    @Test
    public void testSignChart() throws Exception {
        //standard
        Assert.assertEquals(
                new RawMatrix(), RawMatrix.signChart(0));
        Assert.assertEquals(
                new RawMatrix(1.0), RawMatrix.signChart(1));
        Assert.assertEquals(
                new RawMatrix(1.0, -1.0, -1.0, 1.0), RawMatrix.signChart(2));
        Assert.assertEquals(
                new RawMatrix(1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0), RawMatrix.signChart(3));
        Assert.assertEquals(
                new RawMatrix(1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0), RawMatrix.signChart(4));
        
        //invalid
        Assert.assertEquals(
                new RawMatrix(), RawMatrix.signChart(-1));
    }
    
}
