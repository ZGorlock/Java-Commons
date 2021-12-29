/*
 * File:    RawVectorTest.java
 * Package: commons.math.component.vector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.vector;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import commons.math.component.BaseComponent;
import commons.math.component.ComponentInterface;
import commons.math.component.handler.error.ComponentErrorHandlerInterface;
import commons.math.component.handler.error.ComponentErrorHandlerProvider;
import commons.math.component.handler.math.RawComponentMathHandler;
import commons.math.component.matrix.BigMatrix;
import commons.math.component.matrix.IntMatrix;
import commons.math.component.matrix.Matrix;
import commons.math.component.matrix.RawMatrix;
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
 * JUnit test of RawVector.
 *
 * @see RawVector
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({RawVector.class})
public class RawVectorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RawVectorTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private RawVector sut;
    
    
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
     * @see RawVector#RawVector(Number...)
     * @see RawVector#RawVector(List)
     * @see RawVector#RawVector(RawVector)
     * @see RawVector#RawVector(RawVector, Number...)
     * @see RawVector#RawVector(int)
     * @see RawVector#RawVector()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        RawVector vector = new RawVector(0.884, 2, 1.1);
        Assert.assertArrayEquals(new Number[] {0.884, 2, 1.1}, vector.getRawComponents());
        Assert.assertEquals(3, vector.getDimensionality());
        
        //list of components
        List<Number> values = Arrays.asList(0.884, 2, 1.1, 8.4, 9.0);
        RawVector vector2 = new RawVector(values);
        Assert.assertArrayEquals(new Number[] {0.884, 2, 1.1, 8.4, 9.0}, vector2.getRawComponents());
        Assert.assertEquals(5, vector2.getDimensionality());
        
        //raw vector
        RawVector vector3 = new RawVector(new RawVector(0.884, 2, 1.1));
        Assert.assertArrayEquals(new Number[] {0.884, 2, 1.1}, vector3.getRawComponents());
        Assert.assertEquals(3, vector3.getDimensionality());
        
        //vector
        RawVector vector4 = new RawVector(new Vector(0.884, 2, 1.1));
        Assert.assertArrayEquals(new Number[] {0.884, 2.0, 1.1}, vector4.getRawComponents());
        Assert.assertEquals(3, vector4.getDimensionality());
        
        //raw vector and component
        RawVector vector5 = new RawVector(new RawVector(0.884, 2), 1.1);
        Assert.assertArrayEquals(new Number[] {0.884, 2, 1.1}, vector5.getRawComponents());
        Assert.assertEquals(3, vector5.getDimensionality());
        
        //raw vector and components
        RawVector vector6 = new RawVector(new RawVector(0.884, 2, 1.1), 8.4, 9);
        Assert.assertArrayEquals(new Number[] {0.884, 2, 1.1, 8.4, 9}, vector6.getRawComponents());
        Assert.assertEquals(5, vector6.getDimensionality());
        
        //vector and component
        RawVector vector7 = new RawVector(new Vector(0.884, 2), 1.1);
        Assert.assertArrayEquals(new Number[] {0.884, 2.0, 1.1}, vector7.getRawComponents());
        Assert.assertEquals(3, vector7.getDimensionality());
        
        //vector and components
        RawVector vector8 = new RawVector(new Vector(0.884, 2, 1.1), 8.4, 9);
        Assert.assertArrayEquals(new Number[] {0.884, 2.0, 1.1, 8.4, 9}, vector8.getRawComponents());
        Assert.assertEquals(5, vector8.getDimensionality());
        
        //dimensionality
        RawVector vectorDimensionality = new RawVector(6);
        Assert.assertArrayEquals(new Number[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, vectorDimensionality.getRawComponents());
        Assert.assertEquals(6, vectorDimensionality.getDimensionality());
        
        //empty
        RawVector vectorDefault = new RawVector();
        Assert.assertArrayEquals(new Number[] {}, vectorDefault.getRawComponents());
        Assert.assertEquals(0, vectorDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(vector, vector3);
        Assert.assertEquals(vector3, vector4);
        Assert.assertEquals(vector4, vector5);
        Assert.assertEquals(vector5, vector7);
        Assert.assertEquals(vector2, vector6);
        Assert.assertEquals(vector6, vector8);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new RawVector(Arrays.asList(0.884, null, 1.1)));
        TestUtils.assertException(NullPointerException.class, () ->
                new RawVector((RawVector) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new RawVector((RawVector) null, 8.4, 9));
    }
    
    /**
     * JUnit test of vectorString.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#vectorString()
     */
    @Test
    public void testVectorString() throws Exception {
        //standard
        
        sut = new RawVector(1.0);
        Assert.assertEquals("<1.0>", sut.vectorString());
        
        sut = new RawVector(1, 2);
        Assert.assertEquals("<1.0, 2.0>", sut.vectorString());
        
        sut = new RawVector(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("<1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0>", sut.vectorString());
        
        sut = new RawVector(8.15, 77.1654, 3, 3.66, 7.15, 890.1, 11, 7.9888, 0.79455);
        Assert.assertEquals("<8.15, 77.1654, 3.0, 3.66, 7.15, 890.1, 11.0, 7.9888, 0.79455>", sut.vectorString());
        
        //empty
        
        sut = new RawVector();
        Assert.assertEquals("<>", sut.vectorString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new RawVector(8.5, -1.944, 2.67, 8.13, 5.501);
        Assert.assertEquals("<8.5, -1.944, 2.67, 8.13, 5.501>", sut.toString());
        
        sut = new RawVector(0, 8.5, -1.944, 2.67, 8.13, 5.501, 0);
        Assert.assertEquals("<0.0, 8.5, -1.944, 2.67, 8.13, 5.501, 0.0>", sut.toString());
        
        sut = new RawVector(8.5);
        Assert.assertEquals("<8.5>", sut.toString());
        
        //empty
        
        sut = new RawVector();
        Assert.assertEquals("<>", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawVector(0, 8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 8, 5.501, 0);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1, 2, 8, 5);
        Assert.assertFalse(sut.equals(other));
        
        sut = new RawVector(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        other = new Matrix(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        Assert.assertFalse(sut.equals(other));
        
        sut = new RawVector(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        other = new RawMatrix(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new RawVector().equals(new RawVector()));
        
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
     * @see RawVector#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawVector(5.501);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new IntVector(5, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        Assert.assertTrue(new RawVector().dimensionalityEqual(new RawVector()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawVector(5.501);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntVector(5, 8);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.lengthEqual(other));
        
        sut = new RawVector(8.5, -1.944, 2.67, 8.13);
        other = new Matrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        Assert.assertTrue(new RawVector().lengthEqual(new RawVector()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawVector(5.501);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(5, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new RawVector().componentTypeEqual(new RawVector()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#cloned()
     */
    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new RawVector(8.1, 6.6, 5, 1.09);
        RawVector clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotSame(sut, clone);
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new RawVector(8.1, 6.6, 5, 1.09);
        RawVector emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(RawVector.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotSame(sut, emptyCopy);
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new RawVector();
        
        //standard
        Assert.assertEquals(
                new RawVector(), sut.createNewInstance(0));
        Assert.assertEquals(
                new RawVector(0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new RawVector(0, 0), sut.createNewInstance(2));
        Assert.assertEquals(
                new RawVector(0, 0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new RawVector(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new RawVector(), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        RawVector reversed;
        
        //standard
        
        sut = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Number[] {5.501, 8, 2.67, -1.944, 8.5}, reversed.getRawComponents());
        
        sut = new RawVector(5.501, 8, 2.67, -1.944, 8.5);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Number[] {8.5, -1.944, 2.67, 8, 5.501}, reversed.getRawComponents());
        
        sut = new RawVector(0, 1, 0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Number[] {1, 0, 1, 0}, reversed.getRawComponents());
        
        sut = new RawVector(5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Number[] {5.501}, reversed.getRawComponents());
        
        sut = new RawVector();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Number[] {}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        RawVector other;
        
        //standard
        
        sut = new RawVector(8.5);
        other = new RawVector(9.9);
        Assert.assertEquals(1.4, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(8.5, 1.5);
        other = new RawVector(9.9, 6);
        Assert.assertEquals(4.712748667179271, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(8.5, 1.5, -5.006);
        other = new RawVector(9.9, 6, 0.514);
        Assert.assertEquals(7.258126480022238, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(9.545700602889239, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector();
        other = new RawVector();
        Assert.assertEquals(0.0, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector other1 = new RawVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final RawVector other2 = new RawVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new RawVector(8.5, 1.5);
        final RawVector other3 = new RawVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        RawVector other;
        
        //standard
        
        sut = new RawVector(8.5);
        other = new RawVector(9.9);
        Assert.assertEquals(
                new RawVector(9.2), sut.midpoint(other));
        
        sut = new RawVector(8.5, 1.5);
        other = new RawVector(9.9, 6);
        Assert.assertEquals(
                new RawVector(9.2, 3.75), sut.midpoint(other));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        other = new RawVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new RawVector(9.2, 3.75, -2.246), sut.midpoint(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawVector(9.2, 3.75, -2.246, 8.0), sut.midpoint(other));
        
        sut = new RawVector();
        other = new RawVector();
        Assert.assertEquals(
                new RawVector(), sut.midpoint(other));
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector other1 = new RawVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final RawVector other2 = new RawVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new RawVector(8.5, 1.5);
        final RawVector other3 = new RawVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#average(List)
     * @see RawVector#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        RawVector other1;
        RawVector other2;
        RawVector other3;
        RawVector other4;
        
        //standard
        
        sut = new RawVector(8.5);
        other1 = new RawVector(9.9);
        other2 = new RawVector(1.8);
        other3 = new RawVector(11.7);
        Assert.assertEquals(
                new RawVector(7.975), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new RawVector(7.975), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new RawVector(8.5, 1.5);
        other1 = new RawVector(9.9, 6);
        other2 = new RawVector(1.8, 4.77);
        Assert.assertEquals(
                new RawVector(6.733333333333, 4.09), sut.average(other1, other2));
        Assert.assertEquals(
                new RawVector(6.733333333333, 4.09), sut.average(Arrays.asList(other1, other2)));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        other1 = new RawVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new RawVector(9.2, 3.75, -2.246), sut.average(other1));
        Assert.assertEquals(
                new RawVector(9.2, 3.75, -2.246), sut.average(Collections.singletonList(other1)));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other1 = new RawVector(9.9, 6, 0.514, 4.9);
        other2 = new RawVector(1.8, 4.77, 0.514, 2.895);
        other3 = new RawVector(11.7, 0.447, 7.16, 8);
        Assert.assertEquals(
                new RawVector(7.975, 3.17925, 0.7955, 6.72375), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new RawVector(7.975, 3.17925, 0.7955, 6.72375), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new RawVector();
        other1 = new RawVector();
        other2 = new RawVector();
        other3 = new RawVector();
        Assert.assertEquals(
                new RawVector(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new RawVector(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector otherF11 = new RawVector(9.9, 6, 0.514, 4.9);
        final RawVector otherF12 = new RawVector(1.8, 4.77, 0.514, 2.895);
        final RawVector otherF13 = new RawVector(11.7, 0.447, 7.16);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector otherF21 = new RawVector(9.9, 6, 0.514, 4.9);
        final RawVector otherF22 = new RawVector(1.8, 4.77, 0.514, 2.895, 9.11);
        final RawVector otherF23 = new RawVector(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector otherF31 = new RawVector(9.9, 6, 0.514, 4.9);
        final RawVector otherF33 = new RawVector(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new RawVector(8.5);
        Assert.assertEquals(8.5, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(8.5, 1.5);
        Assert.assertEquals(10.0, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(8.5, 1.5, -5.006);
        Assert.assertEquals(4.994, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(16.094, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector();
        Assert.assertEquals(0.0, sut.sum().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new RawVector(8.5);
        Assert.assertEquals(72.25, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(8.5, 1.5);
        Assert.assertEquals(74.5, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(8.5, 1.5, -5.006);
        Assert.assertEquals(99.560036, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(222.770036, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector();
        Assert.assertEquals(0.0, sut.squareSum().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        RawVector other;
        
        //standard
        
        sut = new RawVector(8.5);
        other = new RawVector(9.9);
        Assert.assertEquals(
                new RawVector(18.4), sut.plus(other));
        
        sut = new RawVector(8.5, 1.5);
        other = new RawVector(9.9, 6);
        Assert.assertEquals(
                new RawVector(18.4, 7.5), sut.plus(other));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        other = new RawVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new RawVector(18.4, 7.5, -4.492), sut.plus(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawVector(18.4, 7.5, -4.492, 16.0), sut.plus(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(1, 1, 1, 1);
        Assert.assertEquals(
                new RawVector(9.5, 2.5, -4.006, 12.1), sut.plus(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(0.0, 0.0, 0.0, 0.0);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1), sut.plus(other));
        
        sut = new RawVector();
        other = new RawVector();
        Assert.assertEquals(
                new RawVector(), sut.plus(other));
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector other1 = new RawVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final RawVector other2 = new RawVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new RawVector(8.5, 1.5);
        final RawVector other3 = new RawVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        RawVector other;
        
        //standard
        
        sut = new RawVector(8.5);
        other = new RawVector(9.9);
        Assert.assertEquals(
                new RawVector(-1.4), sut.minus(other));
        
        sut = new RawVector(8.5, 1.5);
        other = new RawVector(9.9, 6);
        Assert.assertEquals(
                new RawVector(-1.4, -4.5), sut.minus(other));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        other = new RawVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new RawVector(-1.4, -4.5, -5.52), sut.minus(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawVector(-1.4, -4.5, -5.52, 6.2), sut.minus(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(1, 1, 1, 1);
        Assert.assertEquals(
                new RawVector(7.5, 0.5, -6.006, 10.1), sut.minus(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(0.0, 0.0, 0.0, 0.0);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1), sut.minus(other));
        
        sut = new RawVector();
        other = new RawVector();
        Assert.assertEquals(
                new RawVector(), sut.minus(other));
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector other1 = new RawVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final RawVector other2 = new RawVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new RawVector(8.5, 1.5);
        final RawVector other3 = new RawVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#times(BaseComponent)
     */
    @Test
    public void testTimes() throws Exception {
        RawVector other;
        
        //standard
        
        sut = new RawVector(8.5);
        other = new RawVector(9.9);
        Assert.assertEquals(
                new RawVector(84.15), sut.times(other));
        
        sut = new RawVector(8.5, 1.5);
        other = new RawVector(9.9, 6);
        Assert.assertEquals(
                new RawVector(84.15, 9.0), sut.times(other));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        other = new RawVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new RawVector(84.15, 9.0, -2.573084), sut.times(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawVector(84.15, 9.0, -2.573084, 54.39), sut.times(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(1, 1, 1, 1);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1), sut.times(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(0.0, 0.0, 0.0, 0.0);
        Assert.assertEquals(
                new RawVector(0.0, 0.0, 0.0, 0.0), sut.times(other));
        
        sut = new RawVector();
        other = new RawVector();
        Assert.assertEquals(
                new RawVector(), sut.times(other));
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector other1 = new RawVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final RawVector other2 = new RawVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new RawVector(8.5, 1.5);
        final RawVector other3 = new RawVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times(null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new RawVector(8.5);
        Assert.assertEquals(
                new RawVector(42.5), sut.scale(5));
        
        sut = new RawVector(8.5, 1.5);
        Assert.assertEquals(
                new RawVector(8.5799, 1.5141), sut.scale(1.0094));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new RawVector(139.57, 24.63, -82.19852), sut.scale(16.42));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new RawVector(-22.7545, -4.0155, 13.401062, -29.7147), sut.scale(-2.677));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1), sut.scale(1));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new RawVector(-8.5, -1.5, 5.006, -11.1), sut.scale(-1));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new RawVector(0.0, 0.0, 0.0, 0.0), sut.scale(0));
        
        sut = new RawVector();
        Assert.assertEquals(
                new RawVector(), sut.scale(7.1));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        RawVector other;
        
        //standard
        
        sut = new RawVector(8.5);
        other = new RawVector(9.9);
        Assert.assertEquals(
                new RawVector(0.858585858586), sut.dividedBy(other));
        
        sut = new RawVector(8.5, 1.5);
        other = new RawVector(9.9, 6);
        Assert.assertEquals(
                new RawVector(0.858585858586, 0.25), sut.dividedBy(other));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        other = new RawVector(9.9, 6, 0.514);
        Assert.assertEquals(
                new RawVector(0.858585858586, 0.25, -9.739299610895), sut.dividedBy(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new RawVector(0.858585858586, 0.25, -9.739299610895, 2.265306122449), sut.dividedBy(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        other = new RawVector(1, 1, 1, 1);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1), sut.dividedBy(other));
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector other0 = new RawVector(0.0, 0.0, 0.0, 0.0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new RawVector();
        other = new RawVector();
        Assert.assertEquals(
                new RawVector(), sut.dividedBy(other));
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector other1 = new RawVector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final RawVector other2 = new RawVector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new RawVector(8.5, 1.5);
        final RawVector other3 = new RawVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new RawVector(8.5);
        Assert.assertEquals(
                new RawVector(9.0), sut.round());
        
        sut = new RawVector(8.5, 1.5);
        Assert.assertEquals(
                new RawVector(9.0, 2.0), sut.round());
        
        sut = new RawVector(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new RawVector(9.0, 2.0, -5.0), sut.round());
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new RawVector(9.0, 2.0, -5.0, 11.0), sut.round());
        
        sut = new RawVector();
        Assert.assertEquals(
                new RawVector(), sut.round());
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#dot(VectorInterface)
     */
    @Test
    public void testDot() throws Exception {
        RawVector other;
        
        //standard
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = new RawVector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(31.23598538692, sut.dot(other));
        
        sut = new RawVector(18.1644, 9.12154, -7.7741);
        other = new RawVector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(631.6597407041199, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = new RawVector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(210.2965501416, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = new RawVector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(-210.2965501416, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = new RawVector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(0.02102965501416, sut.dot(other));
        
        sut = new RawVector(1, -1, 3);
        other = new RawVector(3, 3, 0);
        Assert.assertEquals(0.0, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = RawVector.origin(3);
        Assert.assertEquals(0.0, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = RawVector.identity(3);
        Assert.assertEquals(25.06004, sut.dot(other));
        
        //invalid
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        final RawVector other1 = new RawVector(1.0481561, 1.655742, 0.974454, 1.5541);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dot(other1));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        final RawVector other2 = new RawVector(1.0481561, 1.655742);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dot(other2));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dot(null));
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        //standard
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(
                new RawVector(0.562999747283, 0.629002096276, 0.536085485198), sut.normalize());
        
        sut = new RawVector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(
                new RawVector(-0.562999747283, -0.629002096276, -0.536085485198), sut.normalize());
        
        sut = new RawVector(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(
                new RawVector(0.834684394816, 0.419149935846, -0.357232826503), sut.normalize());
        
        sut = new RawVector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(
                new RawVector(0.152531277799, 0.907171337934, 0.392140756521), sut.normalize());
        
        sut = new RawVector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(
                new RawVector(0.478931075086, 0.756553624145, 0.445254577865), sut.normalize());
        
        sut = new RawVector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(
                new RawVector(0.562999747283, 0.629002096276, 0.536085485198), sut.normalize());
        
        sut = new RawVector(1, -1, 3);
        Assert.assertEquals(
                new RawVector(0.301511344578, -0.301511344578, 0.904534033733), sut.normalize());
        
        sut = RawVector.origin(3);
        Assert.assertEquals(
                new RawVector(0, 0, 0.0), sut.normalize());
        
        sut = RawVector.identity(3);
        Assert.assertEquals(
                new RawVector(0.57735026919, 0.57735026919, 0.57735026919), sut.normalize());
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        //standard
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(14.501605088458312, sut.hypotenuse().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(14.501605088458312, sut.hypotenuse().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(21.761997843525304, sut.hypotenuse().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(78.9881018102008, sut.hypotenuse().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(2.188532242999223, sut.hypotenuse().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(0.0014501605088458312, sut.hypotenuse().doubleValue(), TestUtils.DELTA);
        
        sut = new RawVector(1, -1, 3);
        Assert.assertEquals(3.3166247903554, sut.hypotenuse().doubleValue(), TestUtils.DELTA);
        
        sut = RawVector.origin(3);
        Assert.assertEquals(0.0, sut.hypotenuse().doubleValue(), TestUtils.DELTA);
        
        sut = RawVector.identity(3);
        Assert.assertEquals(1.7320508075688772, sut.hypotenuse().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        RawVector copy;
        
        //standard
        
        sut = new RawVector(8.5);
        copy = new RawVector(1);
        sut.copy(copy);
        Assert.assertEquals(
                new RawVector(8.5), copy);
        
        sut = new RawVector(8.5, 1.5);
        copy = new RawVector(2);
        sut.copy(copy);
        Assert.assertEquals(
                new RawVector(8.5, 1.5), copy);
        
        sut = new RawVector(8.5, 1.5, -5.006);
        copy = new RawVector(3);
        sut.copy(copy);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006), copy);
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        copy = new RawVector(4);
        sut.copy(copy);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new RawVector();
        copy = new RawVector();
        sut.copy(copy);
        Assert.assertEquals(
                new RawVector(), copy);
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector copy1 = new RawVector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final RawVector copy2 = new RawVector(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                sut.copy(copy2));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#copyMeta(RawVector)
     */
    @Test
    public void testCopyMeta() throws Exception {
        RawVector component1 = new RawVector(8.1, 6.6, 7.0, 2.6);
        Assert.assertEquals(4, component1.getDimensionality());
        Assert.assertArrayEquals(new Number[] {8.1, 6.6, 7.0, 2.6}, component1.getRawComponents());
        
        RawVector component2 = new RawVector(9.1, 6.3, 1.7);
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        Assert.assertArrayEquals(new Number[] {9.1, 6.3, 1.7}, component2.getRawComponents());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(3);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006), sut);
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(5);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1, 0), sut);
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(4);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(1);
        Assert.assertEquals(
                new RawVector(8.5), sut);
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(0);
        Assert.assertEquals(
                new RawVector(), sut);
        
        sut = new RawVector();
        sut.redim(3);
        Assert.assertEquals(
                new RawVector(0, 0, 0), sut);
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        sut.redim(-1);
        Assert.assertEquals(
                new RawVector(), sut);
        
        sut = Mockito.spy(RawVector.class);
        sut.setComponents(new Number[] {8.5, 1.5, -5.006, 11.1});
        Mockito.when(sut.isResizeable()).thenReturn(false);
        Assert.assertFalse(sut.isResizeable());
        sut.redim(3);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1), sut);
    }
    
    /**
     * JUnit test of subVector.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#subVector(int, int)
     * @see RawVector#subVector(int)
     */
    @Test
    public void testSubVector() throws Exception {
        //standard
        
        RawVector subVector;
        sut = new RawVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        subVector = sut.subVector(0, 0);
        Assert.assertEquals(
                new RawVector(), subVector);
        
        subVector = sut.subVector(0, 1);
        Assert.assertEquals(
                new RawVector(1.0), subVector);
        
        subVector = sut.subVector(0, 2);
        Assert.assertEquals(
                new RawVector(1, 2), subVector);
        
        subVector = sut.subVector(2, 9);
        Assert.assertEquals(
                new RawVector(3, 4, 5, 6, 7, 8, 9), subVector);
        
        subVector = sut.subVector(8, 10);
        Assert.assertEquals(
                new RawVector(9, 10), subVector);
        
        subVector = sut.subVector(0, 10);
        Assert.assertEquals(
                new RawVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(5);
        Assert.assertEquals(
                new RawVector(6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(8);
        Assert.assertEquals(
                new RawVector(9, 10), subVector);
        
        subVector = sut.subVector(0);
        Assert.assertEquals(
                new RawVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        //invalid
        
        sut = new RawVector(10);
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 1, 0), () ->
                sut.subVector(1, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 3, 1), () ->
                sut.subVector(3, 1));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 9, 0), () ->
                sut.subVector(9, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 4, -2), () ->
                sut.subVector(4, -2));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 5, 11), () ->
                sut.subVector(5, 11));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 9, 21), () ->
                sut.subVector(9, 21));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 12, 16), () ->
                sut.subVector(12, 16));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 21, 33), () ->
                sut.subVector(21, 33));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 15, 10), () ->
                sut.subVector(15));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, -3, 4), () ->
                sut.subVector(-3, 4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, -1, 1), () ->
                sut.subVector(-1, 1));
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#dimensionalityToLength(int)
     * @see RawVector#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new RawVector(8.5, 1.5, -5.006);
        
        //standard
        Assert.assertEquals(3, sut.dimensionalityToLength());
        Assert.assertEquals(3, sut.dimensionalityToLength(3));
        Assert.assertEquals(5, sut.dimensionalityToLength(5));
        Assert.assertEquals(1, sut.dimensionalityToLength(1));
        Assert.assertEquals(0, sut.dimensionalityToLength(0));
        
        //invalid
        Assert.assertEquals(0, sut.dimensionalityToLength(-1));
        Assert.assertEquals(0, sut.dimensionalityToLength(-3));
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#lengthToDimensionality(int)
     * @see RawVector#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new RawVector(8.5, 1.5, -5.006);
        
        //standard
        Assert.assertEquals(3, sut.lengthToDimensionality());
        Assert.assertEquals(3, sut.lengthToDimensionality(3));
        Assert.assertEquals(5, sut.lengthToDimensionality(5));
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
     * @see RawVector#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new RawVector(8.5, 1.5, -5.006);
        
        //standard
        Assert.assertEquals(3, sut.getDimensionality());
        TestUtils.setField(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(3, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        RawVector component;
        
        //standard
        component = new RawVector(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        Assert.assertArrayEquals(new Number[] {8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828}, component.getRawComponents());
        Assert.assertArrayEquals(new Number[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        RawVector component;
        
        //standard
        
        component = new RawVector(8.5, 1.5, -5.006);
        Assert.assertArrayEquals(new Number[] {8.5, 1.5, -5.006}, component.getComponents());
        
        component = new RawVector(8.5, 1.5);
        Assert.assertArrayEquals(new Number[] {8.5, 1.5}, component.getComponents());
        
        component = new RawVector();
        Assert.assertArrayEquals(new Number[] {}, component.getComponents());
        
        component = new RawVector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertArrayEquals(new Number[] {8.500000000001, 1.499999999996, -5.005999999999}, component.getComponents());
        
        component = new RawVector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertArrayEquals(new Number[] {8.5, 1.5, -5.006}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        RawVector component;
        
        //standard
        component = new RawVector(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        Assert.assertArrayEquals(new double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509}, component.getPrimitiveComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new Number[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getRaw(int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new RawVector(8.5, 1.5, -5.006);
        Assert.assertEquals(8.5, sut.getRaw(0));
        Assert.assertEquals(1.5, sut.getRaw(1));
        Assert.assertEquals(-5.006, sut.getRaw(2));
        
        sut = new RawVector(8.5, 1.5);
        Assert.assertEquals(8.5, sut.getRaw(0));
        Assert.assertEquals(1.5, sut.getRaw(1));
        
        sut = new RawVector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(8.500000000001, sut.getRaw(0));
        Assert.assertEquals(1.499999999996, sut.getRaw(1));
        Assert.assertEquals(-5.005999999999, sut.getRaw(2));
        
        sut = new RawVector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0));
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1));
        Assert.assertEquals(-5.0059999999999999999999, sut.getRaw(2));
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.getRaw(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.getRaw(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
        
        sut = new RawVector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.getRaw(0));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new RawVector(8.5, 1.5, -5.006);
        Assert.assertEquals(8.5, sut.get(0));
        Assert.assertEquals(1.5, sut.get(1));
        Assert.assertEquals(-5.006, sut.get(2));
        
        sut = new RawVector(8.5, 1.5);
        Assert.assertEquals(8.5, sut.get(0));
        Assert.assertEquals(1.5, sut.get(1));
        
        sut = new RawVector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(8.500000000001, sut.get(0));
        Assert.assertEquals(1.499999999996, sut.get(1));
        Assert.assertEquals(-5.005999999999, sut.get(2));
        
        sut = new RawVector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(8.5, sut.get(0));
        Assert.assertEquals(1.5, sut.get(1));
        Assert.assertEquals(-5.006, sut.get(2));
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.get(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.get(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
        
        sut = new RawVector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.get(0));
    }
    
    /**
     * JUnit test of getRawX.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getRawX()
     */
    @Test
    public void testGetRawX() throws Exception {
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(8.15, sut.getRawX());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(8.15000000000000000025, sut.getRawX());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getRawX());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawX()));
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getX()
     */
    @Test
    public void testGetX() throws Exception {
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(8.15, sut.getX());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(8.15, sut.getX());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getX());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getX()));
    }
    
    /**
     * JUnit test of getRawY.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getRawY()
     */
    @Test
    public void testGetRawY() throws Exception {
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(77.1654, sut.getRawY());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(77.16549999999999999999996, sut.getRawY());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getRawY());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawY()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawY()));
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getY()
     */
    @Test
    public void testGetY() throws Exception {
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(77.1654, sut.getY());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(77.1655, sut.getY());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getY());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getY()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getY()));
    }
    
    /**
     * JUnit test of getRawZ.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getRawZ()
     */
    @Test
    public void testGetRawZ() throws Exception {
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(0.79455, sut.getRawZ());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(0.794550000000000000000001, sut.getRawZ());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getRawZ());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ()));
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ()));
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(0.79455, sut.getZ());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(0.79455, sut.getZ());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getZ());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ()));
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ()));
    }
    
    /**
     * JUnit test of getRawW.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getRawW()
     */
    @Test
    public void testGetRawW() throws Exception {
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(3.3154, sut.getRawW());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(3.315499999999999999999999886, sut.getRawW());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getRawW());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW()));
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW()));
        
        sut = new RawVector(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW()));
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getW()
     */
    @Test
    public void testGetW() throws Exception {
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(3.3154, sut.getW());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(3.3155, sut.getW());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getW());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW()));
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW()));
        
        sut = new RawVector(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW()));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new RawVector(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new RawVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(4, sut.getDimensionality());
        
        sut = new RawVector(5.501);
        Assert.assertEquals(1, sut.getDimensionality());
        
        sut = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(6, sut.getDimensionality());
        
        sut = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new RawVector();
        Assert.assertEquals(0, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new RawVector(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new RawVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new RawVector(5.501);
        Assert.assertEquals(1, sut.getLength());
        
        sut = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(6, sut.getLength());
        
        sut = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(2, sut.getLength());
        
        sut = new RawVector();
        Assert.assertEquals(0, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new RawVector();
        Assert.assertEquals(RawVector.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new RawVector();
        Assert.assertEquals(Number.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new RawVector();
        Assert.assertTrue(sut.getHandler() instanceof RawComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new RawVector();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new RawVector();
        Assert.assertEquals("Raw Vector", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new RawVector();
        Assert.assertEquals("Raw Vectors", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new RawVector();
        Assert.assertEquals(1E-12, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new RawVector();
        Assert.assertTrue(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        RawVector component;
        Number[] newComponents;
        
        //standard
        
        component = new RawVector(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {5.6, 6.7, 7.8, 8.9};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Number[] {5.6, 6.7, 7.8, 8.9}, component.getRawComponents());
        Assert.assertEquals(4, component.getDimensionality());
        
        component = new RawVector(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {5.6, 6.7, 7.8};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Number[] {5.6, 6.7, 7.8}, component.getRawComponents());
        Assert.assertEquals(3, component.getDimensionality());
        
        component = new RawVector(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {5.6, 6.7, 7.8, 8.9, 9};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Number[] {5.6, 6.7, 7.8, 8.9, 9}, component.getRawComponents());
        Assert.assertEquals(5, component.getDimensionality());
        
        component = new RawVector(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Number[] {}, component.getRawComponents());
        Assert.assertEquals(0, component.getDimensionality());
        
        //invalid
        
        final RawVector component1 = new RawVector(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(component1.isResizeable());
        TestUtils.assertException(NullPointerException.class, () ->
                component1.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new RawVector(8.5, 1.5, -5.006);
        Assert.assertArrayEquals(new Number[] {8.5, 1.5, -5.006}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(1, 3.0);
        sut.set(2, 0.997);
        Assert.assertArrayEquals(new Number[] {5.77, 3.0, 0.997}, sut.getRawComponents());
        
        sut = new RawVector(8.5, 1.5);
        Assert.assertArrayEquals(new Number[] {8.5, 1.5}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(1, 3.0);
        Assert.assertArrayEquals(new Number[] {5.77, 3.0}, sut.getRawComponents());
        
        sut = new RawVector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertArrayEquals(new Number[] {8.500000000001, 1.499999999996, -5.005999999999}, sut.getRawComponents());
        sut.set(0, 5.769999999996);
        sut.set(1, 3.000000000001);
        Assert.assertArrayEquals(new Number[] {5.769999999996, 3.000000000001, -5.005999999999}, sut.getRawComponents());
        
        sut = new RawVector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertArrayEquals(new Number[] {8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999}, sut.getRawComponents());
        sut.set(0, 6.5000000000000000000000001);
        sut.set(1, -1.49999999999999999999996);
        sut.set(2, 3.0059999999999999999999);
        Assert.assertArrayEquals(new Number[] {6.5, -1.5, 3.006}, sut.getRawComponents());
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.set(3, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 5.5));
        
        sut = new RawVector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.set(0, 5.5));
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setX(Number)
     */
    @Test
    public void testSetX() throws Exception {
        //standard
        
        sut = new RawVector(4);
        Assert.assertArrayEquals(new Number[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Number[] {5.555, 0.0, 0.0, 0.0}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Number[] {5.555, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        
        //invalid
        
        sut = new RawVector();
        Assert.assertArrayEquals(new Number[] {}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Number[] {}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setX(null));
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setY(Number)
     */
    @Test
    public void testSetY() throws Exception {
        //standard
        
        sut = new RawVector(4);
        Assert.assertArrayEquals(new Number[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Number[] {0.0, 5.555, 0.0, 0.0}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Number[] {8.15, 5.555, 0.79455, 3.3154}, sut.getRawComponents());
        
        //invalid
        
        sut = new RawVector();
        Assert.assertArrayEquals(new Number[] {}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Number[] {}, sut.getRawComponents());
        
        sut = new RawVector(8.15);
        Assert.assertArrayEquals(new Number[] {8.15}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Number[] {8.15}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setY(null));
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setZ(Number)
     */
    @Test
    public void testSetZ() throws Exception {
        //standard
        
        sut = new RawVector(4);
        Assert.assertArrayEquals(new Number[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Number[] {0.0, 0.0, 5.555, 0.0}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 5.555, 3.3154}, sut.getRawComponents());
        
        //invalid
        
        sut = new RawVector();
        Assert.assertArrayEquals(new Number[] {}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Number[] {}, sut.getRawComponents());
        
        sut = new RawVector(8.15);
        Assert.assertArrayEquals(new Number[] {8.15}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Number[] {8.15}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setZ(null));
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setW(Number)
     */
    @Test
    public void testSetW() throws Exception {
        //standard
        
        sut = new RawVector(4);
        Assert.assertArrayEquals(new Number[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Number[] {0.0, 0.0, 0.0, 5.555}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 5.555}, sut.getRawComponents());
        
        //invalid
        
        sut = new RawVector();
        Assert.assertArrayEquals(new Number[] {}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Number[] {}, sut.getRawComponents());
        
        sut = new RawVector(8.15);
        Assert.assertArrayEquals(new Number[] {8.15}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Number[] {8.15}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setW(null));
        Assert.assertArrayEquals(new Number[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        RawVector copy;
        
        //standard
        
        sut = new RawVector(8.5);
        copy = new RawVector(1);
        RawVector.copy(sut, copy);
        Assert.assertEquals(
                new RawVector(8.5), copy);
        
        sut = new RawVector(8.5, 1.5);
        copy = new RawVector(2);
        RawVector.copy(sut, copy);
        Assert.assertEquals(
                new RawVector(8.5, 1.5), copy);
        
        sut = new RawVector(8.5, 1.5, -5.006);
        copy = new RawVector(3);
        RawVector.copy(sut, copy);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006), copy);
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        copy = new RawVector(4);
        RawVector.copy(sut, copy);
        Assert.assertEquals(
                new RawVector(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new RawVector();
        copy = new RawVector();
        RawVector.copy(sut, copy);
        Assert.assertEquals(
                new RawVector(), copy);
        
        //invalid
        
        sut = new RawVector(8.5, 1.5, -5.006, 11.1);
        final RawVector copy1 = new RawVector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                RawVector.copy(sut, copy1));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final RawVector copy2 = new RawVector(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                RawVector.copy(sut, copy2));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final BigVector copy3 = new BigVector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy3), () ->
                RawVector.copy(sut, copy3));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        final BigMatrix copy4 = new BigMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy4), () ->
                RawVector.copy(sut, copy4));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                RawVector.copy(sut, null));
        
        sut = new RawVector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                RawVector.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new RawVector(), RawVector.createInstance(0));
        Assert.assertEquals(
                new RawVector(0.0), RawVector.createInstance(1));
        Assert.assertEquals(
                new RawVector(0, 0), RawVector.createInstance(2));
        Assert.assertEquals(
                new RawVector(0, 0, 0.0), RawVector.createInstance(3));
        Assert.assertEquals(
                new RawVector(0.0, 0.0, 0.0, 0.0), RawVector.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new RawVector(), RawVector.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new RawVector(), RawVector.identity(0));
        Assert.assertEquals(
                new RawVector(1.0), RawVector.identity(1));
        Assert.assertEquals(
                new RawVector(1.0, 1.0), RawVector.identity(2));
        Assert.assertEquals(
                new RawVector(1.0, 1.0, 1.0), RawVector.identity(3));
        Assert.assertEquals(
                new RawVector(1.0, 1.0, 1.0, 1.0), RawVector.identity(4));
        
        //invalid
        Assert.assertEquals(
                new RawVector(), RawVector.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new RawVector(), RawVector.origin(0));
        Assert.assertEquals(
                new RawVector(0.0), RawVector.origin(1));
        Assert.assertEquals(
                new RawVector(0, 0), RawVector.origin(2));
        Assert.assertEquals(
                new RawVector(0, 0, 0.0), RawVector.origin(3));
        Assert.assertEquals(
                new RawVector(0.0, 0.0, 0.0, 0.0), RawVector.origin(4));
        
        //invalid
        Assert.assertEquals(
                new RawVector(), RawVector.origin(-1));
    }
    
}
