/*
 * File:    Matrix4Test.java
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
import commons.math.component.vector.Vector4;
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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Matrix4.
 *
 * @see Matrix4
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Matrix4.class})
public class Matrix4Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Matrix4Test.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private Matrix4 sut;
    
    
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
     * @see Matrix4#DIMENSIONALITY
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(4, Matrix4.DIMENSIONALITY);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#Matrix4(double...)
     * @see Matrix4#Matrix4()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        Matrix4 matrix = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9.0, 6.6, 1.44, 7.1}, matrix.getRawComponents());
        Assert.assertEquals(4, matrix.getDimensionality());
        
        //empty
        Matrix4 matrixDefault = new Matrix4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, matrixDefault.getRawComponents());
        Assert.assertEquals(4, matrixDefault.getDimensionality());
        
        //dimensionality (ignored)
        Matrix4 matrixDimensionality = new Matrix4(8);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, matrixDimensionality.getRawComponents());
        Assert.assertEquals(4, matrixDimensionality.getDimensionality());
        
        //equality
        Assert.assertEquals(matrixDefault, matrixDimensionality);
        
        //invalid
        TestUtils.assertException(ArithmeticException.class, new Matrix4().getErrorHandler().componentLengthNotSquareErrorMessage(new Double[] {0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06}), () ->
                new Matrix4(0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06));
        TestUtils.assertException(ArithmeticException.class, "The 4D Matrix: [<0.884, 2.0, 1.1>, <-9.3, 1.61, 8.0>, <-0.77, 5.06, 4.4>] does not have the expected dimensionality of: 4", () ->
                new Matrix4(0.884, 2.0, 1.1, -9.3, 1.61, 8.0, -0.77, 5.06, 4.4));
    }
    
    /**
     * JUnit test of matrixString.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#matrixString()
     */
    @Test
    public void testMatrixString() throws Exception {
        //standard
        
        sut = new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Assert.assertEquals("[<1.0, 2.0, 3.0, 4.0>, <5.0, 6.0, 7.0, 8.0>, <9.0, 10.0, 11.0, 12.0>, <13.0, 14.0, 15.0, 16.0>]", sut.matrixString());
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertEquals("[<0.884, 2.0, 1.1, -9.3>, <1.61, 8.0, -0.77, 5.06>, <4.4, 0.18, 2.37, 3.8>, <9.0, 6.6, 1.44, 7.1>]", sut.matrixString());
        
        //empty
        
        sut = new Matrix4();
        Assert.assertEquals("[<0.0, 0.0, 0.0, 0.0>, <0.0, 0.0, 0.0, 0.0>, <0.0, 0.0, 0.0, 0.0>, <0.0, 0.0, 0.0, 0.0>]", sut.matrixString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Assert.assertEquals("[<1.0, 2.0, 3.0, 4.0>, <5.0, 6.0, 7.0, 8.0>, <9.0, 10.0, 11.0, 12.0>, <13.0, 14.0, 15.0, 16.0>]", sut.toString());
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertEquals("[<0.884, 2.0, 1.1, -9.3>, <1.61, 8.0, -0.77, 5.06>, <4.4, 0.18, 2.37, 3.8>, <9.0, 6.6, 1.44, 7.1>]", sut.toString());
        
        //empty
        
        sut = new Matrix4();
        Assert.assertEquals("[<0.0, 0.0, 0.0, 0.0>, <0.0, 0.0, 0.0, 0.0>, <0.0, 0.0, 0.0, 0.0>, <0.0, 0.0, 0.0, 0.0>]", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertTrue(sut.equals(other));
        
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
     * @see Matrix4#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new IntVector(8, -1, 2, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        Assert.assertTrue(new Matrix().dimensionalityEqual(new Matrix()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        
        //standard
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertTrue(sut.lengthEqual(other));
        
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
     * @see Matrix4#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        
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
     * @see Matrix4#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new Matrix4(8.1, 6.6, 5, 1.09, 1.7, 8.54, 0.4, 6.2, 4.7, 2.8, 7.03, 5.5, 9.6, 1, 4.3, 5);
        Matrix clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotSame(sut, clone);
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new Matrix4(8.1, 6.6, 5, 1.09, 1.7, 8.54, 0.4, 6.2, 4.7, 2.8, 7.03, 5.5, 9.6, 1, 4.3, 5);
        Matrix emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(Matrix.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotSame(sut, emptyCopy);
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
        //standard
        
        sut = new Matrix4();
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0, 0.0), sut.newVector());
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0, 0.0), sut.newVector());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new Matrix4();
        
        //standard
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(0));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
        //standard
        
        sut = new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Assert.assertEquals(0, sut.toIndex(0, 0));
        Assert.assertEquals(1, sut.toIndex(1, 0));
        Assert.assertEquals(5, sut.toIndex(1, 1));
        
        //invalid
        
        sut = new Matrix4();
        Assert.assertEquals(45, sut.toIndex(9, 9));
        
        sut = new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Assert.assertEquals(7, sut.toIndex(3, 1));
        Assert.assertEquals(45, sut.toIndex(9, 9));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        Matrix reversed;
        
        //standard
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {7.1, 1.44, 6.6, 9.0, 3.8, 2.37, 0.18, 4.4, 5.06, -0.77, 8.0, 1.61, -9.3, 1.1, 2.0, 0.884}, reversed.getRawComponents());
        
        sut = new Matrix4();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        other = new Matrix4(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        Assert.assertEquals(20.432795990759562, sut.distance(other), TestUtils.DELTA);
        
        sut = new Matrix4();
        other = new Matrix4();
        Assert.assertEquals(0.0, sut.distance(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        final Matrix other2 = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        other = new Matrix4(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        Assert.assertEquals(
                new Matrix4(5.392, 4.0, 0.807, -2.2, 1.705, 5.5, 2.365, 6.08, 4.7, 1.34, 4.235, 3.4, 6.6, 7.75, 0.92, 7.05), sut.midpoint(other));
        
        sut = new Matrix4();
        other = new Matrix4();
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.midpoint(other));
        
        //invalid
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        final Matrix other2 = new Matrix(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#average(List)
     * @see Matrix4#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        Matrix other1;
        Matrix other2;
        Matrix other3;
        Matrix other4;
        
        //standard
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        other1 = new Matrix4(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        other2 = new Matrix4(1.8, 4.77, 0.514, 2.895, 3, 4.9, 6, 0.8, 7.1, 1.1, 4.8, 0.3, 7, 5.2, 6.8, 3.3);
        other3 = new Matrix4(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5, 4.1, 7.04, 9.8, 4, 6.2, 5, 3.8);
        Assert.assertEquals(
                new Matrix4(6.071, 3.30425, 2.322, 1.62375, 2.3025, 6.975, 2.875, 4.89, 5.375, 1.97, 5.0775, 4.225, 6.05, 6.725, 3.41, 5.3), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Matrix4(6.071, 3.30425, 2.322, 1.62375, 2.3025, 6.975, 2.875, 4.89, 5.375, 1.97, 5.0775, 4.225, 6.05, 6.725, 3.41, 5.3), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new Matrix4();
        other1 = new Matrix4();
        other2 = new Matrix4();
        other3 = new Matrix4();
        Assert.assertEquals(
                new Matrix4(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Matrix4(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        final Matrix otherF11 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        final Matrix otherF12 = new Matrix(1.8, 4.77, 0.514, 2.895, 3, 4.9, 6, 0.8, 7.1, 1.1, 4.8, 0.3, 7, 5.2, 6.8, 3.3);
        final Matrix otherF13 = new Matrix(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5, 4.1, 7.04, 9.8, 4, 6.2, 5, 3.8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF11), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        final Matrix otherF21 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        final Matrix otherF22 = new Matrix(1.8, 4.77, 0.514, 2.895, 3, 4.9, 6, 0.8, 7.1);
        final Matrix otherF23 = new Matrix(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5, 4.1, 7.04, 9.8, 4, 6.2, 5, 3.8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        final Matrix otherF31 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        final Matrix otherF33 = new Matrix(11.7, 0.447, 7.16, 8, 2.8, 12, 0.77, 6.6, 5, 4.1, 7.04, 9.8, 4, 6.2, 5, 3.8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new Matrix4(0.884, 2, 1.1, -9.3, 1.61, 8, -0.77, 5.06, 4.4, 0.18, 2.37, 3.8, 9, 6.6, 1.44, 7.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(78.494, sut.sum(), TestUtils.DELTA);
        
        sut = new Matrix4();
        Assert.assertEquals(0, sut.sum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(652.150036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Matrix4();
        Assert.assertEquals(0, sut.squareSum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        Assert.assertEquals(
                new Matrix4(18.4, 7.5, -4.492, 16.0, 6.1, 12.0, 7.1, 14.4, 13.2, 3.5, 11.6, 10.3, 11.1, 9.1, 3.4, 15.1), sut.plus(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix4(9.5, 2.5, -4.006, 12.1, 5.3, 10, 2.6, 8.3, 9.2, 2, 6.5, 8.3, 7.9, 1.2, 4, 9.1), sut.plus(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), sut.plus(other));
        
        sut = new Matrix4();
        other = new Matrix4();
        Assert.assertEquals(
                new Matrix4(), sut.plus(other));
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        Assert.assertEquals(
                new Matrix4(-1.4, -4.5, -5.52, 6.2, 2.5, 6.0, -3.9, 0.2, 3.2, -1.5, -0.6, 4.3, 2.7, -8.7, 2.6, 1.1), sut.minus(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix4(7.5, 0.5, -6.006, 10.1, 3.3, 8.0, 0.6, 6.3, 7.2, 0.0, 4.5, 6.3, 5.9, -0.8, 2.0, 7.1), sut.minus(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), sut.minus(other));
        
        sut = new Matrix4();
        other = new Matrix4();
        Assert.assertEquals(
                new Matrix4(), sut.minus(other));
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#times(Matrix)
     */
    @Test
    public void testTimes() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        Assert.assertEquals(
                new Matrix4(108.44, 141.775, -13.4776, 114.982, 97.43, 121.77, 64.3902, 140.87, 141.14, 130.92, 46.1848, 114.88, 117.69, 121.59, 26.1866, 100.93), sut.times(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix4(16.094, 16.094, 16.094, 16.094, 22.2, 22.2, 22.2, 22.2, 22.0, 22.0, 22.0, 22.0, 18.2, 18.2, 18.2, 18.2), sut.times(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.times(other));
        
        sut = new Matrix4();
        other = new Matrix4();
        Assert.assertEquals(
                new Matrix4(), sut.times(other));
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((Matrix) null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Vector4(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(144.966916, 133.1624, 125.777, 110.742), sut.times(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Vector4(1, 1, 1, 1);
        Assert.assertEquals(
                new Vector4(16.094, 22.2, 22.0, 18.2), sut.times(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Vector4(0, 0, 0, 0);
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.times(other));
        
        sut = new Matrix4();
        other = new Vector4();
        Assert.assertEquals(
                new Vector4(), sut.times(other));
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Vector other2 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Vector other3 = new Vector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times((Vector) null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(34.0, 6.0, -20.024, 44.4, 17.2, 36.0, 6.4, 29.2, 32.8, 4.0, 22.0, 29.2, 27.6, 0.8, 12.0, 32.4), sut.scale(4));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(138.04, 24.36, -81.29744, 180.264, 69.832, 146.16, 25.984, 118.552, 133.168, 16.24, 89.32, 118.552, 112.056, 3.248, 48.72, 131.544), sut.scale(16.24));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(8.5391, 1.5069, -5.0290276, 11.15106, 4.31978, 9.0414, 1.60736, 7.33358, 8.23772, 1.0046, 5.5253, 7.33358, 6.93174, 0.20092, 3.0138, 8.13726), sut.scale(1.0046));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), sut.scale(1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.scale(0));
        
        sut = new Matrix4();
        Assert.assertEquals(
                new Matrix4(), sut.scale(7.1));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#scale(MatrixInterface)
     */
    @Test
    public void testMatrixScale() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        Assert.assertEquals(
                new Matrix4(84.15, 9.0, -2.573084, 54.39, 7.74, 27.0, 8.8, 51.83, 41.0, 2.5, 33.55, 21.9, 28.98, 1.78, 1.2, 56.7), sut.scale(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2, 1.0, 5.5, 7.3, 6.9, 0.2, 3.0, 8.1), sut.scale(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.scale(other));
        
        sut = new Matrix4();
        other = new Matrix4();
        Assert.assertEquals(
                new Matrix4(), sut.scale(other));
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.scale(other1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.scale(other2));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.scale(other3));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.scale((Matrix) null));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        Matrix other;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5, 2.5, 6.1, 3, 4.2, 8.9, 0.4, 7);
        Assert.assertEquals(
                new Matrix4(0.858585858586, 0.25, -9.739299610895, 2.265306122449, 2.388888888889, 3.0, 0.290909090909, 1.028169014085, 1.64, 0.4, 0.901639344262, 2.433333333333, 1.642857142857, 0.022471910112, 7.5, 1.157142857143), sut.dividedBy(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        other = new Matrix4(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), sut.dividedBy(other));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix4 other0 = new Matrix4(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new Matrix4();
        final Matrix4 other02 = new Matrix4();
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other02));
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other1 = new Matrix(9.9, 6, 0.514, 4.9, 1.8, 3, 5.5, 7.1, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other2 = new Matrix(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix other3 = new Matrix();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(9.0, 2.0, -5.0, 11.0, 4.0, 9.0, 2.0, 7.0, 8.0, 1.0, 6.0, 7.0, 7.0, 0.0, 3.0, 8.0), sut.round());
        
        sut = new Matrix4();
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.round());
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(1240.3973760000008, sut.determinant(), TestUtils.DELTA);
        
        sut = new Matrix4();
        Assert.assertEquals(0.0, sut.determinant(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#minor(int, int)
     * @see Matrix4#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(207.096, sut.minor(0, 0), TestUtils.DELTA);
        Assert.assertEquals(-25.74, sut.minor(1, 0), TestUtils.DELTA);
        Assert.assertEquals(88.30484, sut.minor(0, 1), TestUtils.DELTA);
        Assert.assertEquals(124.6863, sut.minor(1, 1), TestUtils.DELTA);
        Assert.assertEquals(-232.345, sut.minor(2, 3), TestUtils.DELTA);
        Assert.assertEquals(739.272, sut.minor(3, 3), TestUtils.DELTA);
        Assert.assertEquals(207.096, sut.minor(0), TestUtils.DELTA);
        Assert.assertEquals(-25.74, sut.minor(1), TestUtils.DELTA);
        Assert.assertEquals(62.50656, sut.minor(7), TestUtils.DELTA);
        Assert.assertEquals(-267.63282, sut.minor(13), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
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
     * @see Matrix4#minors()
     */
    @Test
    public void testMinors() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(207.096, -25.74, -154.296, 119.904, 88.30484, 124.6863, -26.021, 62.50656, 640.36664, -133.13724, -49.214, 530.55744, 781.3354, -267.63282, -232.345, 739.272), sut.minors());
        
        sut = new Matrix4();
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.minors());
    }
    
    /**
     * JUnit test of cofactorScalar.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#cofactorScalar(int, int)
     * @see Matrix4#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(1, sut.cofactorScalar(0, 0), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1, 0), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(0, 1), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(1, 1), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(2, 3), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(3, 3), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(0), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.cofactorScalar(1), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(7), TestUtils.DELTA);
        Assert.assertEquals(1, sut.cofactorScalar(13), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
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
     * @see Matrix4#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(8.5, -1.5, -5.006, -11.1, -4.3, 9.0, -1.6, 7.3, 8.2, -1.0, 5.5, -7.3, -6.9, 0.2, -3.0, 8.1), sut.cofactor());
        
        sut = new Matrix4();
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.cofactor());
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(8.5, 4.3, 8.2, 6.9, 1.5, 9.0, 1.0, 0.2, -5.006, 1.6, 5.5, 3.0, 11.1, 7.3, 7.3, 8.1), sut.transpose());
        
        sut = new Matrix4();
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.transpose());
    }
    
    /**
     * JUnit test of adjoint.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(207.096, -88.30484, 640.36664, -781.3354, 25.74, 124.6863, 133.13724, -267.63282, -154.296, 26.021, -49.214, 232.345, -119.904, 62.50656, -530.55744, 739.272), sut.adjoint());
        
        sut = new Matrix4();
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), sut.adjoint());
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(
                new Matrix4(0.166959398663, -0.071190766531, 0.516259266901, -0.6299073306, 0.020751414424, 0.100521254247, 0.107334345087, -0.215763774721, -0.124392394716, 0.020977954729, -0.039675994929, 0.187314972198, -0.096665796236, 0.050392367163, -0.427731830352, 0.595996101172), sut.inverse());
        
        //invalid
        
        sut = new Matrix4();
        TestUtils.assertException(ArithmeticException.class, "The 4D Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
        
        sut = new Matrix4(4.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 4.0);
        TestUtils.assertException(ArithmeticException.class, "The 4D Matrix: " + sut + " cannot be inverted", () ->
                sut.inverse());
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        Vector solution;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        solution = new Vector4(4.0, 19.6, 1.774, 8.6);
        Assert.assertEquals(
                new Vector4(-5.22886053303, 0.388064906516, 1.454121879729, 4.967797414496), sut.solveSystem(solution));
        
        //invalid
        
        sut = new Matrix4();
        final Vector solution1 = new Vector4();
        TestUtils.assertException(ArithmeticException.class, "The 4D Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution1));
        
        sut = new Matrix4(4.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 4.0);
        final Vector solution2 = new Vector4(4.0, 19.6, 1.774, 8.6);
        TestUtils.assertException(ArithmeticException.class, "The 4D Matrix: " + sut + " cannot be inverted", () ->
                sut.solveSystem(solution2));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Vector solution3 = new Vector(4.0, 19.6, 1.774);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution3), () ->
                sut.solveSystem(solution3));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Vector solution4 = new Vector(4.0, 19.6, 1.774, 8.6, 1.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.inverse(), solution4), () ->
                sut.solveSystem(solution4));
    }
    
    /**
     * JUnit test of transform.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        Vector solution;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        solution = new Vector4(4.0, 19.6, 1.774, 8.6);
        Assert.assertEquals(
                new Vector4(192.1668, 185.894, 46.893, 270.0902), sut.transform(solution));
        
        //invalid
        
        sut = new Matrix4();
        final Vector solution1 = new Vector4();
        TestUtils.assertNoException(() ->
                sut.transform(solution1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Vector solution3 = new Vector(4.0, 19.6, 1.774);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution3), () ->
                sut.transform(solution3));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Vector solution4 = new Vector(4.0, 19.6, 1.774, 8.6, 1.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut.transpose(), solution4), () ->
                sut.transform(solution4));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        Matrix copy;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        copy = new Matrix4();
        sut.copy(copy);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), copy);
        
        sut = new Matrix4();
        copy = new Matrix4();
        sut.copy(copy);
        Assert.assertEquals(
                new Matrix4(), copy);
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix copy1 = new Matrix(0.0, 0.0, 0.0, 0.0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#copyMeta(Component)
     */
    @Test
    public void testCopyMeta() throws Exception {
        Matrix component1 = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(4, component1.getDimensionality());
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2, 1.0, 5.5, 7.3, 6.9, 0.2, 3.0, 8.1}, component1.getRawComponents());
        
        Matrix component2 = new Matrix4(9.1, 6.3, 1.7, 0.3, 6.9, 0.2, 3.0, 11.1, 4.3, 9.0, 1.6, 7.3, 8.5, 6.9, 0.2, 8.1);
        component1.copyMeta(component2);
        Assert.assertEquals(4, component2.getDimensionality());
        Assert.assertArrayEquals(new Double[] {9.1, 6.3, 1.7, 0.3, 6.9, 0.2, 3.0, 11.1, 4.3, 9.0, 1.6, 7.3, 8.5, 6.9, 0.2, 8.1}, component2.getRawComponents());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Assert.assertFalse(sut.isResizeable());
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        sut.redim(4);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), sut);
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        sut.redim(1);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), sut);
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        sut.redim(0);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), sut);
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        sut.redim(-1);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), sut);
    }
    
    /**
     * JUnit test of subMatrix.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#subMatrix(int, int, int, int)
     */
    @Test
    public void testSubMatrix() throws Exception {
        //standard
        
        Matrix subMatrix;
        sut = new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Assert.assertFalse(sut.isResizeable());
        
        subMatrix = sut.subMatrix(0, 0, 3, 3);
        Assert.assertEquals(
                new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16), subMatrix);
        
        //dimensionality
        
        sut = new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        subMatrix = sut.subMatrix(0, 0, 4);
        Assert.assertEquals(
                new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16), subMatrix);
        
        //invalid
        
        sut = new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Matrix4.DIMENSIONALITY), () ->
                sut.subMatrix(1, 1, 2, 2));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Matrix4.DIMENSIONALITY), () ->
                sut.subMatrix(0, 1, 2, 3));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Matrix4.DIMENSIONALITY), () ->
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
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Double[] {1.0, 2.0, 3.0, 5.0, 6.0, 7.0}), () ->
                sut.subMatrix(0, 0, 2, 1));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentLengthNotSquareErrorMessage(new Double[] {1.0, 2.0, 3.0, 5.0, 6.0, 7.0, 9.0, 10.0, 11.0, 13.0, 14.0, 15.0}), () ->
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
     * @see Matrix4#dimensionalityToLength(int)
     * @see Matrix4#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        
        //standard
        Assert.assertEquals(16, sut.dimensionalityToLength());
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
     * @see Matrix4#dimensionalityToWidth(int)
     * @see Matrix4#dimensionalityToWidth()
     */
    @Test
    public void testDimensionalityToWidth() throws Exception {
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        
        //standard
        Assert.assertEquals(4, sut.dimensionalityToWidth());
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
     * @see Matrix4#dimensionalityToHeight(int)
     * @see Matrix4#dimensionalityToHeight()
     */
    @Test
    public void testDimensionalityToHeight() throws Exception {
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        
        //standard
        Assert.assertEquals(4, sut.dimensionalityToHeight());
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
     * @see Matrix4#lengthToDimensionality(int)
     * @see Matrix4#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        
        //standard
        Assert.assertEquals(4, sut.lengthToDimensionality());
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
     * @see Matrix4#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        
        //standard
        Assert.assertEquals(4, sut.getDimensionality());
        TestUtils.setField(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(4, sut.getDimensionality());
    }
    
    /**
     * JUnit test of prettyPrint.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
        //standard
        
        List<String> print;
        
        sut = new Matrix4();
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(4, print.size());
        Assert.assertEquals("┌  0.0   0.0   0.0   0.0  ┐", print.get(0));
        Assert.assertEquals("│  0.0   0.0   0.0   0.0  │", print.get(1));
        Assert.assertEquals("│  0.0   0.0   0.0   0.0  │", print.get(2));
        Assert.assertEquals("└  0.0   0.0   0.0   0.0  ┘", print.get(3));
        
        sut = new Matrix4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(4, print.size());
        Assert.assertEquals("┌  1.0    2.0    3.0    4.0   ┐", print.get(0));
        Assert.assertEquals("│  5.0    6.0    7.0    8.0   │", print.get(1));
        Assert.assertEquals("│  9.0    10.0   11.0   12.0  │", print.get(2));
        Assert.assertEquals("└  13.0   14.0   15.0   16.0  ┘", print.get(3));
        
        sut = new Matrix4(8.154119, 3, 1.1033, 8.5, 4.4465740112, 9.915422012, 16.112, 45.2947, 7, 1.54894121, 0.135678, 19.1, 4.65774, 6.6, 10.74842114, 3.15444);
        print = StringUtility.splitLines(sut.prettyPrint());
        Assert.assertEquals(4, print.size());
        Assert.assertEquals("┌  8.154119       3.0           1.1033        8.5      ┐", print.get(0));
        Assert.assertEquals("│  4.4465740112   9.915422012   16.112        45.2947  │", print.get(1));
        Assert.assertEquals("│  7.0            1.54894121    0.135678      19.1     │", print.get(2));
        Assert.assertEquals("└  4.65774        6.6           10.74842114   3.15444  ┘", print.get(3));
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        Matrix component;
        
        //standard
        component = new Matrix4(8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782, 4.350949549874897161, 9.0215015065454, 1.690489487428742884, 7.39684561235412, 8.256089809484540121, 1.01650945912212001, 5.5504454549824811805, 7.3980456502065151, 6.965049878284522494, 0.2560480984121641064, 3.48908456501210540, 8.15649804051502062);
        Assert.assertArrayEquals(new Double[] {8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782, 4.350949549874897161, 9.0215015065454, 1.690489487428742884, 7.39684561235412, 8.256089809484540121, 1.01650945912212001, 5.5504454549824811805, 7.3980456502065151, 6.965049878284522494, 0.2560480984121641064, 3.48908456501210540, 8.15649804051502062}, component.getRawComponents());
        Assert.assertArrayEquals(new Double[] {8.516540894156, 1.505684814185, -5.006549845043, 11.152604098725, 4.350949549875, 9.021501506545, 1.690489487429, 7.396845612354, 8.256089809485, 1.016509459122, 5.550445454982, 7.398045650207, 6.965049878285, 0.256048098412, 3.489084565012, 8.156498040515}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        Matrix component;
        
        //standard
        
        component = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2, 1.0, 5.5, 7.3, 6.9, 0.2, 3.0, 8.1}, component.getComponents());
        
        component = new Matrix4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, component.getComponents());
        
        component = new Matrix4(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999, 1.000000000001, 5.499999999999, 7.299999999999, 6.900000000001, 0.200000000001, 2.999999999999, 8.099999999999);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999, 1.000000000001, 5.499999999999, 7.299999999999, 6.900000000001, 0.200000000001, 2.999999999999, 8.099999999999}, component.getComponents());
        
        component = new Matrix4(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999, 1.0000000000000000000000001, 5.49999999999999999999996, 7.29999999999999999999996, 6.9000000000000000000000001, 0.2000000000000000000000001, 2.99999999999999999999996, 8.09999999999999999999996);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2, 1.0, 5.5, 7.3, 6.9, 0.2, 3.0, 8.1}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        Matrix component;
        
        //standard
        component = new Matrix4(8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782, 4.350949549874897161, 9.0215015065454, 1.690489487428742884, 7.39684561235412, 8.256089809484540121, 1.01650945912212001, 5.5504454549824811805, 7.3980456502065151, 6.965049878284522494, 0.2560480984121641064, 3.48908456501210540, 8.15649804051502062);
        Assert.assertArrayEquals(new double[] {8.51654089415614591484, 1.505684814184524918, -5.00654984504254129841, 11.15260409872454165782, 4.350949549874897161, 9.0215015065454, 1.690489487428742884, 7.39684561235412, 8.256089809484540121, 1.01650945912212001, 5.5504454549824811805, 7.3980456502065151, 6.965049878284522494, 0.2560480984121641064, 3.48908456501210540, 8.15649804051502062}, component.getPrimitiveComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new Double[] {8.516540894156, 1.505684814185, -5.006549845043, 11.152604098725, 4.350949549875, 9.021501506545, 1.690489487429, 7.396845612354, 8.256089809485, 1.016509459122, 5.550445454982, 7.398045650207, 6.965049878285, 0.256048098412, 3.489084565012, 8.156498040515}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getRaw(int)
     * @see Matrix4#getRaw(int, int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(8.5, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(8.2, sut.getRaw(8), TestUtils.DELTA);
        Assert.assertEquals(0.2, sut.getRaw(13), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1, 0), TestUtils.DELTA);
        Assert.assertEquals(9.0, sut.getRaw(1, 1), TestUtils.DELTA);
        Assert.assertEquals(3.0, sut.getRaw(2, 3), TestUtils.DELTA);
        Assert.assertEquals(8.1, sut.getRaw(3, 3), TestUtils.DELTA);
        
        sut = new Matrix4(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999, 1.000000000001, 5.499999999999, 7.299999999999, 6.900000000001, 0.200000000001, 2.999999999999, 8.099999999999);
        Assert.assertEquals(8.500000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(8.199999999999, sut.getRaw(8), TestUtils.DELTA);
        Assert.assertEquals(0.200000000001, sut.getRaw(13), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1, 0), TestUtils.DELTA);
        Assert.assertEquals(9.000000000001, sut.getRaw(1, 1), TestUtils.DELTA);
        Assert.assertEquals(2.999999999999, sut.getRaw(2, 3), TestUtils.DELTA);
        Assert.assertEquals(8.099999999999, sut.getRaw(3, 3), TestUtils.DELTA);
        
        sut = new Matrix4(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999, 1.0000000000000000000000001, 5.49999999999999999999996, 7.29999999999999999999996, 6.9000000000000000000000001, 0.2000000000000000000000001, 2.99999999999999999999996, 8.09999999999999999999996);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(8.1999999999999999999999, sut.getRaw(8), TestUtils.DELTA);
        Assert.assertEquals(0.2000000000000000000000001, sut.getRaw(13), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1, 0), TestUtils.DELTA);
        Assert.assertEquals(9.0000000000000000000000001, sut.getRaw(1, 1), TestUtils.DELTA);
        Assert.assertEquals(2.99999999999999999999996, sut.getRaw(2, 3), TestUtils.DELTA);
        Assert.assertEquals(8.09999999999999999999996, sut.getRaw(3, 3), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 16), () ->
                sut.getRaw(16));
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
     * @see Matrix4#get(int)
     * @see Matrix4#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(8.2, sut.get(8), TestUtils.DELTA);
        Assert.assertEquals(0.2, sut.get(13), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1, 0), TestUtils.DELTA);
        Assert.assertEquals(9.0, sut.get(1, 1), TestUtils.DELTA);
        Assert.assertEquals(3.0, sut.get(2, 3), TestUtils.DELTA);
        Assert.assertEquals(8.1, sut.get(3, 3), TestUtils.DELTA);
        
        sut = new Matrix4(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999, 1.000000000001, 5.499999999999, 7.299999999999, 6.900000000001, 0.200000000001, 2.999999999999, 8.099999999999);
        Assert.assertEquals(8.500000000001, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(8.199999999999, sut.get(8), TestUtils.DELTA);
        Assert.assertEquals(0.200000000001, sut.get(13), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1, 0), TestUtils.DELTA);
        Assert.assertEquals(9.000000000001, sut.get(1, 1), TestUtils.DELTA);
        Assert.assertEquals(2.999999999999, sut.get(2, 3), TestUtils.DELTA);
        Assert.assertEquals(8.099999999999, sut.get(3, 3), TestUtils.DELTA);
        
        sut = new Matrix4(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999, 1.0000000000000000000000001, 5.49999999999999999999996, 7.29999999999999999999996, 6.9000000000000000000000001, 0.2000000000000000000000001, 2.99999999999999999999996, 8.09999999999999999999996);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(8.2, sut.get(8), TestUtils.DELTA);
        Assert.assertEquals(0.2, sut.get(13), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1, 0), TestUtils.DELTA);
        Assert.assertEquals(9.0, sut.get(1, 1), TestUtils.DELTA);
        Assert.assertEquals(3.0, sut.get(2, 3), TestUtils.DELTA);
        Assert.assertEquals(8.1, sut.get(3, 3), TestUtils.DELTA);
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 16), () ->
                sut.get(16));
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
     * @see Matrix4#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(4, sut.getDimensionality());
        
        sut = new Matrix4();
        Assert.assertEquals(4, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(4, sut.getHeight());
        
        sut = new Matrix4();
        Assert.assertEquals(4, sut.getHeight());
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(4, sut.getWidth());
        
        sut = new Matrix4();
        Assert.assertEquals(4, sut.getWidth());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertEquals(16, sut.getLength());
        
        sut = new Matrix4();
        Assert.assertEquals(16, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new Matrix4();
        Assert.assertEquals(Matrix4.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new Matrix4();
        Assert.assertEquals(Double.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new Matrix4();
        Assert.assertTrue(sut.getHandler() instanceof DoubleComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new Matrix4();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new Matrix4();
        Assert.assertEquals("4D Matrix", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new Matrix4();
        Assert.assertEquals("4D Matrices", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new Matrix4();
        Assert.assertEquals(1E-12, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new Matrix4();
        Assert.assertFalse(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        Matrix component;
        Double[] newComponents;
        
        //standard
        
        component = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        newComponents = new Double[] {5.6, 6.7, 1.1, 7.2, 8.1, 0.7, 5.6, 7.23, 3.1, 9.01, 7.0, 2.8, 4.4, 9.4, 5.3, 2.0};
        Assert.assertEquals(4, component.getDimensionality());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Double[] {5.6, 6.7, 1.1, 7.2, 8.1, 0.7, 5.6, 7.23, 3.1, 9.01, 7.0, 2.8, 4.4, 9.4, 5.3, 2.0}, component.getRawComponents());
        Assert.assertEquals(4, component.getDimensionality());
        
        //invalid
        
        final Matrix component1 = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Double[] newComponents1 = new Double[] {8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2};
        Assert.assertFalse(component1.isResizeable());
        TestUtils.assertException(IndexOutOfBoundsException.class, component1.getErrorHandler().componentLengthNotEqualErrorMessage(newComponents1, component1.getLength()), () ->
                component1.setComponents(newComponents1));
        
        final Matrix component2 = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#set(int, Number)
     * @see Matrix4#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1, 4.3, 9.0, 1.6, 7.3, 8.2, 1.0, 5.5, 7.3, 6.9, 0.2, 3.0, 8.1}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(3, 3.0);
        sut.set(7, 0.997);
        sut.set(14, 6.01);
        Assert.assertArrayEquals(new Double[] {5.77, 1.5, -5.006, 3.0, 4.3, 9.0, 1.6, 0.997, 8.2, 1.0, 5.5, 7.3, 6.9, 0.2, 6.01, 8.1}, sut.getRawComponents());
        
        sut = new Matrix4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(3, 3.0);
        sut.set(7, 0.997);
        sut.set(14, 6.01);
        Assert.assertArrayEquals(new Double[] {5.77, 0.0, 0.0, 3.0, 0.0, 0.0, 0.0, 0.997, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 6.01, 0.0}, sut.getRawComponents());
        
        sut = new Matrix4(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999, 1.000000000001, 5.499999999999, 7.299999999999, 6.900000000001, 0.200000000001, 2.999999999999, 8.099999999999);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999999, 4.299999999999, 9.000000000001, 1.599999999996, 7.299999999996, 8.199999999999, 1.000000000001, 5.499999999999, 7.299999999999, 6.900000000001, 0.200000000001, 2.999999999999, 8.099999999999}, sut.getRawComponents());
        sut.set(0, 5.769999999996);
        sut.set(3, 3.000000000001);
        sut.set(7, 8.110000000001);
        sut.set(14, 5.800000000001);
        Assert.assertArrayEquals(new Double[] {5.769999999996, 1.499999999996, -5.005999999999, 3.000000000001, 4.299999999999, 9.000000000001, 1.599999999996, 8.110000000001, 8.199999999999, 1.000000000001, 5.499999999999, 7.299999999999, 6.900000000001, 0.200000000001, 5.800000000001, 8.099999999999}, sut.getRawComponents());
        
        sut = new Matrix4(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999, 1.0000000000000000000000001, 5.49999999999999999999996, 7.29999999999999999999996, 6.9000000000000000000000001, 0.2000000000000000000000001, 2.99999999999999999999996, 8.09999999999999999999996);
        Assert.assertArrayEquals(new Double[] {8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999999, 4.29999999999999999999996, 9.0000000000000000000000001, 1.59999999999999999999996, 7.29999999999999999999996, 8.1999999999999999999999, 1.0000000000000000000000001, 5.49999999999999999999996, 7.29999999999999999999996, 6.9000000000000000000000001, 0.2000000000000000000000001, 2.99999999999999999999996, 8.09999999999999999999996}, sut.getRawComponents());
        sut.set(0, 6.5000000000000000000000001);
        sut.set(3, -1.49999999999999999999996);
        sut.set(7, 3.0059999999999999999999);
        sut.set(14, 5.7999999999999999999999);
        Assert.assertArrayEquals(new Double[] {6.5, 1.5, -5.006, -1.5, 4.3, 9.0, 1.6, 3.006, 8.2, 1.0, 5.5, 7.3, 6.9, 0.2, 5.8, 8.1}, sut.getRawComponents());
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 16), () ->
                sut.set(16, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 25), () ->
                sut.set(25, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 5.5));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        Matrix copy;
        
        //standard
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        copy = new Matrix4();
        Matrix4.copy(sut, copy);
        Assert.assertEquals(
                new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1), copy);
        
        sut = new Matrix4();
        copy = new Matrix4();
        Matrix4.copy(sut, copy);
        Assert.assertEquals(
                new Matrix4(), copy);
        
        //invalid
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        final Matrix copy1 = new Matrix(0.0, 0.0, 0.0, 0.0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                Matrix4.copy(sut, copy1));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                Matrix4.copy(sut, null));
        
        sut = new Matrix4(8.5, 1.5, -5.006, 11.1, 4.3, 9, 1.6, 7.3, 8.2, 1, 5.5, 7.3, 6.9, 0.2, 3, 8.1);
        TestUtils.assertException(NullPointerException.class, () ->
                Matrix4.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#createInstance()
     * @see Matrix4#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.createInstance(0));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.createInstance(1));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.createInstance(2));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.createInstance(3));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#identity()
     * @see Matrix4#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0), Matrix4.identity(0));
        Assert.assertEquals(
                new Matrix4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0), Matrix4.identity(1));
        Assert.assertEquals(
                new Matrix4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0), Matrix4.identity(2));
        Assert.assertEquals(
                new Matrix4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0), Matrix4.identity(3));
        Assert.assertEquals(
                new Matrix4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0), Matrix4.identity(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0), Matrix4.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#origin()
     * @see Matrix4#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.origin(0));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.origin(1));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.origin(2));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.origin(3));
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.origin(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix4(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), Matrix4.origin(-1));
    }
    
    /**
     * JUnit test of signChart.
     *
     * @throws Exception When there is an exception.
     * @see Matrix4#signChart()
     * @see Matrix4#signChart(int)
     */
    @Test
    public void testSignChart() throws Exception {
        //standard
        Assert.assertEquals(
                new Matrix4(1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0), Matrix4.signChart(0));
        Assert.assertEquals(
                new Matrix4(1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0), Matrix4.signChart(1));
        Assert.assertEquals(
                new Matrix4(1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0), Matrix4.signChart(2));
        Assert.assertEquals(
                new Matrix4(1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0), Matrix4.signChart(3));
        Assert.assertEquals(
                new Matrix4(1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0), Matrix4.signChart(4));
        
        //invalid
        Assert.assertEquals(
                new Matrix4(1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, -1.0, 1.0, -1.0, -1.0, 1.0, -1.0, 1.0), Matrix4.signChart(-1));
    }
    
}
