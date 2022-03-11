/*
 * File:    VectorTest.java
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
import commons.math.component.handler.math.DoubleComponentMathHandler;
import commons.math.component.matrix.BigMatrix;
import commons.math.component.matrix.IntMatrix;
import commons.math.component.matrix.Matrix;
import commons.math.component.matrix.RawMatrix;
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
 * JUnit test of Vector.
 *
 * @see Vector
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Vector.class})
public class VectorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(VectorTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private Vector sut;
    
    
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
     * @see Vector#Vector(double...)
     * @see Vector#Vector(List)
     * @see Vector#Vector(Vector)
     * @see Vector#Vector(Vector, double...)
     * @see Vector#Vector(int)
     * @see Vector#Vector()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        Vector vector = new Vector(0.884, 2, 1.1);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1}, vector.getRawComponents());
        Assert.assertEquals(3, vector.getDimensionality());
        
        //list of components
        List<Double> values = Arrays.asList(0.884, 2.0, 1.1, 8.4, 9.0);
        Vector vector2 = new Vector(values);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1, 8.4, 9.0}, vector2.getRawComponents());
        Assert.assertEquals(5, vector2.getDimensionality());
        
        //vector
        Vector vector3 = new Vector(new Vector(0.884, 2, 1.1));
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1}, vector3.getRawComponents());
        Assert.assertEquals(3, vector3.getDimensionality());
        
        //vector and component
        Vector vector4 = new Vector(new Vector(0.884, 2), 1.1);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1}, vector4.getRawComponents());
        Assert.assertEquals(3, vector4.getDimensionality());
        
        //vector and components
        Vector vector5 = new Vector(new Vector(0.884, 2, 1.1), 8.4, 9);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1, 8.4, 9.0}, vector5.getRawComponents());
        Assert.assertEquals(5, vector5.getDimensionality());
        
        //dimensionality
        Vector vectorDimensionality = new Vector(6);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, vectorDimensionality.getRawComponents());
        Assert.assertEquals(6, vectorDimensionality.getDimensionality());
        
        //empty
        Vector vectorDefault = new Vector();
        Assert.assertArrayEquals(new Double[] {}, vectorDefault.getRawComponents());
        Assert.assertEquals(0, vectorDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(vector, vector3);
        Assert.assertEquals(vector3, vector4);
        Assert.assertEquals(vector2, vector5);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new Vector(Arrays.asList(0.884, null, 1.1)));
        TestUtils.assertException(NullPointerException.class, () ->
                new Vector((Vector) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new Vector(null, 8.4, 9));
    }
    
    /**
     * JUnit test of vectorString.
     *
     * @throws Exception When there is an exception.
     * @see Vector#vectorString()
     */
    @Test
    public void testVectorString() throws Exception {
        //standard
        
        sut = new Vector(1.0);
        Assert.assertEquals("<1.0>", sut.vectorString());
        
        sut = new Vector(1, 2);
        Assert.assertEquals("<1.0, 2.0>", sut.vectorString());
        
        sut = new Vector(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("<1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0>", sut.vectorString());
        
        sut = new Vector(8.15, 77.1654, 3, 3.66, -7.15, 890.1, 11, 7.9888, -0.79455);
        Assert.assertEquals("<8.15, 77.1654, 3.0, 3.66, -7.15, 890.1, 11.0, 7.9888, -0.79455>", sut.vectorString());
        
        //empty
        
        sut = new Vector();
        Assert.assertEquals("<>", sut.vectorString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see Vector#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new Vector(8.5, -1.944, 2.67, 8.13, 5.501);
        Assert.assertEquals("<8.5, -1.944, 2.67, 8.13, 5.501>", sut.toString());
        
        sut = new Vector(0, 8.5, -1.944, 2.67, 8.13, 5.501, 0);
        Assert.assertEquals("<0.0, 8.5, -1.944, 2.67, 8.13, 5.501, 0.0>", sut.toString());
        
        sut = new Vector(8.5);
        Assert.assertEquals("<8.5>", sut.toString());
        
        //empty
        
        sut = new Vector();
        Assert.assertEquals("<>", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see Vector#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(0, 8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67, 8, 5.501, 0);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1, 2, 8, 5);
        Assert.assertFalse(sut.equals(other));
        
        sut = new Vector(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        other = new Matrix(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        Assert.assertFalse(sut.equals(other));
        
        sut = new Vector(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        other = new RawMatrix(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new Vector().equals(new Vector()));
        
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
     * @see Vector#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(5.501, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(5.501);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
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
        
        Assert.assertTrue(new Vector().dimensionalityEqual(new Vector()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see Vector#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(5.501, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Vector(5.501);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
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
        
        sut = new Vector(8.5, -1.944, 2.67, 8.13);
        other = new Matrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        Assert.assertTrue(new Vector().lengthEqual(new Vector()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see Vector#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Vector(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(5.501);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(5, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new Vector().componentTypeEqual(new Vector()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see Vector#cloned()
     */
    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new Vector(8.1, 6.6, 5, 1.09);
        Vector clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotSame(sut, clone);
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see Vector#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new Vector(8.1, 6.6, 5, 1.09);
        Vector emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(Vector.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotSame(sut, emptyCopy);
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see Vector#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new Vector();
        
        //standard
        Assert.assertEquals(
                new Vector(), sut.createNewInstance(0));
        Assert.assertEquals(
                new Vector(0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new Vector(0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Vector(), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see Vector#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        Vector reversed;
        
        //standard
        
        sut = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {5.501, 8.0, 2.67, -1.944, 8.5}, reversed.getRawComponents());
        
        sut = new Vector(5.501, 8, 2.67, -1.944, 8.5);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {8.5, -1.944, 2.67, 8.0, 5.501}, reversed.getRawComponents());
        
        sut = new Vector(0, 1, 0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {1.0, 0.0, 1.0, 0.0}, reversed.getRawComponents());
        
        sut = new Vector(5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {5.501}, reversed.getRawComponents());
        
        sut = new Vector();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see Vector#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector(8.5);
        other = new Vector(9.9);
        Assert.assertEquals(1.4, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5);
        other = new Vector(9.9, 6);
        Assert.assertEquals(4.712748667179271, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5, -5.006);
        other = new Vector(9.9, 6, 0.514);
        Assert.assertEquals(7.258126480022238, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(9.545700602889239, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector();
        other = new Vector();
        Assert.assertEquals(0.0, sut.distance(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final Vector other2 = new Vector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new Vector(8.5, 1.5);
        final Vector other3 = new Vector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see Vector#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector(8.5);
        other = new Vector(9.9);
        Assert.assertEquals(
                new Vector(9.2), sut.midpoint(other));
        
        sut = new Vector(8.5, 1.5);
        other = new Vector(9.9, 6);
        Assert.assertEquals(
                new Vector(9.2, 3.75), sut.midpoint(other));
        
        sut = new Vector(8.5, 1.5, -5.006);
        other = new Vector(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector(9.2, 3.75, -2.246), sut.midpoint(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector(9.2, 3.75, -2.246, 8.0), sut.midpoint(other));
        
        sut = new Vector();
        other = new Vector();
        Assert.assertEquals(
                new Vector(), sut.midpoint(other));
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final Vector other2 = new Vector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new Vector(8.5, 1.5);
        final Vector other3 = new Vector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see Vector#average(List)
     * @see Vector#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        Vector other1;
        Vector other2;
        Vector other3;
        Vector other4;
        
        //standard
        
        sut = new Vector(8.5);
        other1 = new Vector(9.9);
        other2 = new Vector(1.8);
        other3 = new Vector(11.7);
        Assert.assertEquals(
                new Vector(7.975), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Vector(7.975), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new Vector(8.5, 1.5);
        other1 = new Vector(9.9, 6);
        other2 = new Vector(1.8, 4.77);
        Assert.assertEquals(
                new Vector(6.733333333333, 4.09), sut.average(other1, other2));
        Assert.assertEquals(
                new Vector(6.733333333333, 4.09), sut.average(Arrays.asList(other1, other2)));
        
        sut = new Vector(8.5, 1.5, -5.006);
        other1 = new Vector(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector(9.2, 3.75, -2.246), sut.average(other1));
        Assert.assertEquals(
                new Vector(9.2, 3.75, -2.246), sut.average(Collections.singletonList(other1)));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other1 = new Vector(9.9, 6, 0.514, 4.9);
        other2 = new Vector(1.8, 4.77, 0.514, 2.895);
        other3 = new Vector(11.7, 0.447, 7.16, 8);
        Assert.assertEquals(
                new Vector(7.975, 3.17925, 0.7955, 6.72375), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Vector(7.975, 3.17925, 0.7955, 6.72375), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new Vector();
        other1 = new Vector();
        other2 = new Vector();
        other3 = new Vector();
        Assert.assertEquals(
                new Vector(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Vector(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector otherF11 = new Vector(9.9, 6, 0.514, 4.9);
        final Vector otherF12 = new Vector(1.8, 4.77, 0.514, 2.895);
        final Vector otherF13 = new Vector(11.7, 0.447, 7.16);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector otherF21 = new Vector(9.9, 6, 0.514, 4.9);
        final Vector otherF22 = new Vector(1.8, 4.77, 0.514, 2.895, 9.11);
        final Vector otherF23 = new Vector(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector otherF31 = new Vector(9.9, 6, 0.514, 4.9);
        final Vector otherF33 = new Vector(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see Vector#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new Vector(8.5);
        Assert.assertEquals(8.5, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5);
        Assert.assertEquals(10.0, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5, -5.006);
        Assert.assertEquals(4.994, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(16.094, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector();
        Assert.assertEquals(0, sut.sum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see Vector#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new Vector(8.5);
        Assert.assertEquals(72.25, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5);
        Assert.assertEquals(74.5, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5, -5.006);
        Assert.assertEquals(99.560036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(222.770036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector();
        Assert.assertEquals(0, sut.squareSum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see Vector#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector(8.5);
        other = new Vector(9.9);
        Assert.assertEquals(
                new Vector(18.4), sut.plus(other));
        
        sut = new Vector(8.5, 1.5);
        other = new Vector(9.9, 6);
        Assert.assertEquals(
                new Vector(18.4, 7.5), sut.plus(other));
        
        sut = new Vector(8.5, 1.5, -5.006);
        other = new Vector(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector(18.4, 7.5, -4.492), sut.plus(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector(18.4, 7.5, -4.492, 16.0), sut.plus(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(1, 1, 1, 1);
        Assert.assertEquals(
                new Vector(9.5, 2.5, -4.006, 12.1), sut.plus(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(0, 0, 0, 0);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1), sut.plus(other));
        
        sut = new Vector();
        other = new Vector();
        Assert.assertEquals(
                new Vector(), sut.plus(other));
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final Vector other2 = new Vector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new Vector(8.5, 1.5);
        final Vector other3 = new Vector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see Vector#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector(8.5);
        other = new Vector(9.9);
        Assert.assertEquals(
                new Vector(-1.4), sut.minus(other));
        
        sut = new Vector(8.5, 1.5);
        other = new Vector(9.9, 6);
        Assert.assertEquals(
                new Vector(-1.4, -4.5), sut.minus(other));
        
        sut = new Vector(8.5, 1.5, -5.006);
        other = new Vector(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector(-1.4, -4.5, -5.52), sut.minus(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector(-1.4, -4.5, -5.52, 6.2), sut.minus(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(1, 1, 1, 1);
        Assert.assertEquals(
                new Vector(7.5, 0.5, -6.006, 10.1), sut.minus(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(0, 0, 0, 0);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1), sut.minus(other));
        
        sut = new Vector();
        other = new Vector();
        Assert.assertEquals(
                new Vector(), sut.minus(other));
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final Vector other2 = new Vector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new Vector(8.5, 1.5);
        final Vector other3 = new Vector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Vector#times(BaseComponent)
     */
    @Test
    public void testTimes() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector(8.5);
        other = new Vector(9.9);
        Assert.assertEquals(
                new Vector(84.15), sut.times(other));
        
        sut = new Vector(8.5, 1.5);
        other = new Vector(9.9, 6);
        Assert.assertEquals(
                new Vector(84.15, 9.0), sut.times(other));
        
        sut = new Vector(8.5, 1.5, -5.006);
        other = new Vector(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector(84.15, 9.0, -2.573084), sut.times(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector(84.15, 9.0, -2.573084, 54.39), sut.times(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(1, 1, 1, 1);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1), sut.times(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(0, 0, 0, 0);
        Assert.assertEquals(
                new Vector(0, 0, 0, 0), sut.times(other));
        
        sut = new Vector();
        other = new Vector();
        Assert.assertEquals(
                new Vector(), sut.times(other));
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final Vector other2 = new Vector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new Vector(8.5, 1.5);
        final Vector other3 = new Vector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times(null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Vector#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new Vector(8.5);
        Assert.assertEquals(
                new Vector(42.5), sut.scale(5));
        
        sut = new Vector(8.5, 1.5);
        Assert.assertEquals(
                new Vector(8.5799, 1.5141), sut.scale(1.0094));
        
        sut = new Vector(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new Vector(139.57, 24.63, -82.19852), sut.scale(16.42));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector(-22.7545, -4.0155, 13.401062, -29.7147), sut.scale(-2.677));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1), sut.scale(1));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector(-8.5, -1.5, 5.006, -11.1), sut.scale(-1));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector(0, 0, 0, 0), sut.scale(0));
        
        sut = new Vector();
        Assert.assertEquals(
                new Vector(), sut.scale(7.1));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see Vector#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector(8.5);
        other = new Vector(9.9);
        Assert.assertEquals(
                new Vector(0.858585858586), sut.dividedBy(other));
        
        sut = new Vector(8.5, 1.5);
        other = new Vector(9.9, 6);
        Assert.assertEquals(
                new Vector(0.858585858586, 0.25), sut.dividedBy(other));
        
        sut = new Vector(8.5, 1.5, -5.006);
        other = new Vector(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector(0.858585858586, 0.25, -9.739299610895), sut.dividedBy(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector(0.858585858586, 0.25, -9.739299610895, 2.265306122449), sut.dividedBy(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        other = new Vector(1, 1, 1, 1);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1), sut.dividedBy(other));
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector other0 = new Vector(0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new Vector();
        other = new Vector();
        Assert.assertEquals(
                new Vector(), sut.dividedBy(other));
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final Vector other2 = new Vector(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new Vector(8.5, 1.5);
        final Vector other3 = new Vector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see Vector#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new Vector(8.5);
        Assert.assertEquals(
                new Vector(9.0), sut.round());
        
        sut = new Vector(8.5, 1.5);
        Assert.assertEquals(
                new Vector(9.0, 2.0), sut.round());
        
        sut = new Vector(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new Vector(9.0, 2.0, -5.0), sut.round());
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector(9.0, 2.0, -5.0, 11.0), sut.round());
        
        sut = new Vector();
        Assert.assertEquals(
                new Vector(), sut.round());
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see Vector#dot(VectorInterface)
     */
    @Test
    public void testDot() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        other = new Vector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(31.23598538692, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector(18.1644, 9.12154, -7.7741);
        other = new Vector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(631.6597407041199, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        other = new Vector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(210.2965501416, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        other = new Vector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(-210.2965501416, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        other = new Vector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(0.02102965501416, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector(1, -1, 3);
        other = new Vector(3, 3, 0);
        Assert.assertEquals(0.0, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        other = Vector.origin(3);
        Assert.assertEquals(0.0, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        other = Vector.identity(3);
        Assert.assertEquals(25.06004, sut.dot(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        final Vector other1 = new Vector(1.0481561, 1.655742, 0.974454, 1.5541);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dot(other1));
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        final Vector other2 = new Vector(1.0481561, 1.655742);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dot(other2));
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dot(null));
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see Vector#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        //standard
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(
                new Vector(0.562999747283, 0.629002096276, 0.536085485198), sut.normalize());
        
        sut = new Vector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(
                new Vector(-0.562999747283, -0.629002096276, -0.536085485198), sut.normalize());
        
        sut = new Vector(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(
                new Vector(0.834684394816, 0.419149935846, -0.357232826503), sut.normalize());
        
        sut = new Vector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(
                new Vector(0.152531277799, 0.907171337934, 0.392140756521), sut.normalize());
        
        sut = new Vector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(
                new Vector(0.478931075086, 0.756553624145, 0.445254577865), sut.normalize());
        
        sut = new Vector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(
                new Vector(0.562999747283, 0.629002096276, 0.536085485198), sut.normalize());
        
        sut = new Vector(1, -1, 3);
        Assert.assertEquals(
                new Vector(0.301511344578, -0.301511344578, 0.904534033733), sut.normalize());
        
        sut = Vector.origin(3);
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0), sut.normalize());
        
        sut = Vector.identity(3);
        Assert.assertEquals(
                new Vector(0.57735026919, 0.57735026919, 0.57735026919), sut.normalize());
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see Vector#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        //standard
        
        sut = new Vector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(14.501605088458312, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(14.501605088458312, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(21.761997843525304, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(78.9881018102008, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(2.188532242999223, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(0.0014501605088458312, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector(1, -1, 3);
        Assert.assertEquals(3.3166247903554, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = Vector.origin(3);
        Assert.assertEquals(0.0, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = Vector.identity(3);
        Assert.assertEquals(1.7320508075688772, sut.hypotenuse(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Vector#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        Vector copy;
        
        //standard
        
        sut = new Vector(8.5);
        copy = new Vector(1);
        sut.copy(copy);
        Assert.assertEquals(
                new Vector(8.5), copy);
        
        sut = new Vector(8.5, 1.5);
        copy = new Vector(2);
        sut.copy(copy);
        Assert.assertEquals(
                new Vector(8.5, 1.5), copy);
        
        sut = new Vector(8.5, 1.5, -5.006);
        copy = new Vector(3);
        sut.copy(copy);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006), copy);
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        copy = new Vector(4);
        sut.copy(copy);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new Vector();
        copy = new Vector();
        sut.copy(copy);
        Assert.assertEquals(
                new Vector(), copy);
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector copy1 = new Vector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final Vector copy2 = new Vector(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                sut.copy(copy2));
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see Vector#copyMeta(Vector)
     */
    @Test
    public void testCopyMeta() throws Exception {
        Vector component1 = new Vector(8.1, 6.6, 7.0, 2.6);
        Assert.assertEquals(4, component1.getDimensionality());
        Assert.assertArrayEquals(new Double[] {8.1, 6.6, 7.0, 2.6}, component1.getRawComponents());
        
        Vector component2 = new Vector(9.1, 6.3, 1.7);
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        Assert.assertArrayEquals(new Double[] {9.1, 6.3, 1.7}, component2.getRawComponents());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see Vector#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        sut.redim(3);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006), sut);
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        sut.redim(5);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1, 0), sut);
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        sut.redim(4);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        sut.redim(1);
        Assert.assertEquals(
                new Vector(8.5), sut);
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        sut.redim(0);
        Assert.assertEquals(
                new Vector(), sut);
        
        sut = new Vector();
        sut.redim(3);
        Assert.assertEquals(
                new Vector(0, 0, 0), sut);
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        sut.redim(-1);
        Assert.assertEquals(
                new Vector(), sut);
        
        sut = Mockito.spy(Vector.class);
        sut.setComponents(new Double[] {8.5, 1.5, -5.006, 11.1});
        Mockito.when(sut.isResizeable()).thenReturn(false);
        Assert.assertFalse(sut.isResizeable());
        sut.redim(3);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1), sut);
    }
    
    /**
     * JUnit test of subVector.
     *
     * @throws Exception When there is an exception.
     * @see Vector#subVector(int, int)
     * @see Vector#subVector(int)
     */
    @Test
    public void testSubVector() throws Exception {
        //standard
        
        Vector subVector;
        sut = new Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        subVector = sut.subVector(0, 0);
        Assert.assertEquals(
                new Vector(), subVector);
        
        subVector = sut.subVector(0, 1);
        Assert.assertEquals(
                new Vector(1.0), subVector);
        
        subVector = sut.subVector(0, 2);
        Assert.assertEquals(
                new Vector(1, 2), subVector);
        
        subVector = sut.subVector(2, 9);
        Assert.assertEquals(
                new Vector(3, 4, 5, 6, 7, 8, 9), subVector);
        
        subVector = sut.subVector(8, 10);
        Assert.assertEquals(
                new Vector(9, 10), subVector);
        
        subVector = sut.subVector(0, 10);
        Assert.assertEquals(
                new Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(5);
        Assert.assertEquals(
                new Vector(6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(8);
        Assert.assertEquals(
                new Vector(9, 10), subVector);
        
        subVector = sut.subVector(0);
        Assert.assertEquals(
                new Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        //invalid
        
        sut = new Vector(10);
        
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
     * @see Vector#dimensionalityToLength(int)
     * @see Vector#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new Vector(8.5, 1.5, -5.006);
        
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
     * @see Vector#lengthToDimensionality(int)
     * @see Vector#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new Vector(8.5, 1.5, -5.006);
        
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
     * @see Vector#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new Vector(8.5, 1.5, -5.006);
        
        //standard
        Assert.assertEquals(3, sut.getDimensionality());
        TestAccess.setFieldValue(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(3, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        Vector component;
        
        //standard
        component = new Vector(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        Assert.assertArrayEquals(new Double[] {8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828}, component.getRawComponents());
        Assert.assertArrayEquals(new Double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        Vector component;
        
        //standard
        
        component = new Vector(8.5, 1.5, -5.006);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006}, component.getComponents());
        
        component = new Vector(8.5, 1.5);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5}, component.getComponents());
        
        component = new Vector();
        Assert.assertArrayEquals(new Double[] {}, component.getComponents());
        
        component = new Vector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999}, component.getComponents());
        
        component = new Vector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        Vector component;
        
        //standard
        component = new Vector(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        Assert.assertArrayEquals(new double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509}, component.getPrimitiveComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new Double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getRaw(int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new Vector(8.5, 1.5, -5.006);
        Assert.assertEquals(8.5, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(-5.006, sut.getRaw(2), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5);
        Assert.assertEquals(8.5, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1), TestUtils.DELTA);
        
        sut = new Vector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(8.500000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(-5.005999999999, sut.getRaw(2), TestUtils.DELTA);
        
        sut = new Vector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1), TestUtils.DELTA);
        Assert.assertEquals(-5.0059999999999999999999, sut.getRaw(2), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.getRaw(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.getRaw(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
        
        sut = new Vector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.getRaw(0));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see Vector#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new Vector(8.5, 1.5, -5.006);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(-5.006, sut.get(2), TestUtils.DELTA);
        
        sut = new Vector(8.5, 1.5);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        
        sut = new Vector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(8.500000000001, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(-5.005999999999, sut.get(2), TestUtils.DELTA);
        
        sut = new Vector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        Assert.assertEquals(-5.006, sut.get(2), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.get(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.get(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
        
        sut = new Vector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.get(0));
    }
    
    /**
     * JUnit test of getRawX.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getRawX()
     */
    @Test
    public void testGetRawX() throws Exception {
        //standard
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(8.15, sut.getRawX(), TestUtils.DELTA);
        
        sut = new Vector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(8.15000000000000000025, sut.getRawX(), TestUtils.DELTA);
        
        sut = new Vector(4);
        Assert.assertEquals(0, sut.getRawX(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawX(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getX()
     */
    @Test
    public void testGetX() throws Exception {
        //standard
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(8.15, sut.getX(), TestUtils.DELTA);
        
        sut = new Vector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(8.15, sut.getX(), TestUtils.DELTA);
        
        sut = new Vector(4);
        Assert.assertEquals(0.0, sut.getX(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getX(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawY.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getRawY()
     */
    @Test
    public void testGetRawY() throws Exception {
        //standard
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(77.1654, sut.getRawY(), TestUtils.DELTA);
        
        sut = new Vector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(77.16549999999999999999996, sut.getRawY(), TestUtils.DELTA);
        
        sut = new Vector(4);
        Assert.assertEquals(0, sut.getRawY(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawY(), TestUtils.DELTA));
        
        sut = new Vector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawY(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getY()
     */
    @Test
    public void testGetY() throws Exception {
        //standard
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(77.1654, sut.getY(), TestUtils.DELTA);
        
        sut = new Vector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(77.1655, sut.getY(), TestUtils.DELTA);
        
        sut = new Vector(4);
        Assert.assertEquals(0.0, sut.getY(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getY(), TestUtils.DELTA));
        
        sut = new Vector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getY(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getRawZ()
     */
    @Test
    public void testGetRawZ() throws Exception {
        //standard
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(0.79455, sut.getRawZ(), TestUtils.DELTA);
        
        sut = new Vector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(0.794550000000000000000001, sut.getRawZ(), TestUtils.DELTA);
        
        sut = new Vector(4);
        Assert.assertEquals(0, sut.getRawZ(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ(), TestUtils.DELTA));
        
        sut = new Vector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ(), TestUtils.DELTA));
        
        sut = new Vector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        //standard
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(0.79455, sut.getZ(), TestUtils.DELTA);
        
        sut = new Vector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(0.79455, sut.getZ(), TestUtils.DELTA);
        
        sut = new Vector(4);
        Assert.assertEquals(0.0, sut.getZ(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ(), TestUtils.DELTA));
        
        sut = new Vector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ(), TestUtils.DELTA));
        
        sut = new Vector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawW.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getRawW()
     */
    @Test
    public void testGetRawW() throws Exception {
        //standard
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(3.3154, sut.getRawW(), TestUtils.DELTA);
        
        sut = new Vector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(3.315499999999999999999999886, sut.getRawW(), TestUtils.DELTA);
        
        sut = new Vector(4);
        Assert.assertEquals(0, sut.getRawW(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW(), TestUtils.DELTA));
        
        sut = new Vector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW(), TestUtils.DELTA));
        
        sut = new Vector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW(), TestUtils.DELTA));
        
        sut = new Vector(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getW()
     */
    @Test
    public void testGetW() throws Exception {
        //standard
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(3.3154, sut.getW(), TestUtils.DELTA);
        
        sut = new Vector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(3.3155, sut.getW(), TestUtils.DELTA);
        
        sut = new Vector(4);
        Assert.assertEquals(0.0, sut.getW(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW(), TestUtils.DELTA));
        
        sut = new Vector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW(), TestUtils.DELTA));
        
        sut = new Vector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW(), TestUtils.DELTA));
        
        sut = new Vector(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new Vector(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new Vector(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(4, sut.getDimensionality());
        
        sut = new Vector(5.501);
        Assert.assertEquals(1, sut.getDimensionality());
        
        sut = new Vector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(6, sut.getDimensionality());
        
        sut = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new Vector();
        Assert.assertEquals(0, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new Vector(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new Vector(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new Vector(5.501);
        Assert.assertEquals(1, sut.getLength());
        
        sut = new Vector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(6, sut.getLength());
        
        sut = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(2, sut.getLength());
        
        sut = new Vector();
        Assert.assertEquals(0, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new Vector();
        Assert.assertEquals(Vector.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new Vector();
        Assert.assertEquals(Double.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new Vector();
        Assert.assertTrue(sut.getHandler() instanceof DoubleComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new Vector();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new Vector();
        Assert.assertEquals("Vector", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new Vector();
        Assert.assertEquals("Vectors", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new Vector();
        Assert.assertEquals(1E-12, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see Vector#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new Vector();
        Assert.assertTrue(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        Vector component;
        Double[] newComponents;
        
        //standard
        
        component = new Vector(5.501, 2.67, -1.944, 8.5);
        newComponents = new Double[] {5.6, 6.7, 7.8, 8.9};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Double[] {5.6, 6.7, 7.8, 8.9}, component.getRawComponents());
        Assert.assertEquals(4, component.getDimensionality());
        
        component = new Vector(5.501, 2.67, -1.944, 8.5);
        newComponents = new Double[] {5.6, 6.7, 7.8};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Double[] {5.6, 6.7, 7.8}, component.getRawComponents());
        Assert.assertEquals(3, component.getDimensionality());
        
        component = new Vector(5.501, 2.67, -1.944, 8.5);
        newComponents = new Double[] {5.6, 6.7, 7.8, 8.9, 9.0};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Double[] {5.6, 6.7, 7.8, 8.9, 9.0}, component.getRawComponents());
        Assert.assertEquals(5, component.getDimensionality());
        
        component = new Vector(5.501, 2.67, -1.944, 8.5);
        newComponents = new Double[] {};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Double[] {}, component.getRawComponents());
        Assert.assertEquals(0, component.getDimensionality());
        
        //invalid
        
        final Vector3 component1 = new Vector3(5.501, 2.67, -1.944);
        final Double[] newComponents1 = new Double[] {5.6, 6.7};
        Assert.assertFalse(component1.isResizeable());
        TestUtils.assertException(IndexOutOfBoundsException.class, component1.getErrorHandler().componentLengthNotEqualErrorMessage(newComponents1, component1.getLength()), () ->
                component1.setComponents(newComponents1));
        
        final Vector component2 = new Vector(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(component2.isResizeable());
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see Vector#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new Vector(8.5, 1.5, -5.006);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(1, 3.0);
        sut.set(2, 0.997);
        Assert.assertArrayEquals(new Double[] {5.77, 3.0, 0.997}, sut.getRawComponents());
        
        sut = new Vector(8.5, 1.5);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(1, 3.0);
        Assert.assertArrayEquals(new Double[] {5.77, 3.0}, sut.getRawComponents());
        
        sut = new Vector(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999}, sut.getRawComponents());
        sut.set(0, 5.769999999996);
        sut.set(1, 3.000000000001);
        Assert.assertArrayEquals(new Double[] {5.769999999996, 3.000000000001, -5.005999999999}, sut.getRawComponents());
        
        sut = new Vector(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertArrayEquals(new Double[] {8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999}, sut.getRawComponents());
        sut.set(0, 6.5000000000000000000000001);
        sut.set(1, -1.49999999999999999999996);
        sut.set(2, 3.0059999999999999999999);
        Assert.assertArrayEquals(new Double[] {6.5, -1.5, 3.006}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.set(3, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 5.5));
        
        sut = new Vector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.set(0, 5.5));
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setX(Number)
     */
    @Test
    public void testSetX() throws Exception {
        //standard
        
        sut = new Vector(4);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Double[] {5.555, 0.0, 0.0, 0.0}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Double[] {5.555, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector();
        Assert.assertArrayEquals(new Double[] {}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Double[] {}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setX(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setY(Number)
     */
    @Test
    public void testSetY() throws Exception {
        //standard
        
        sut = new Vector(4);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {0.0, 5.555, 0.0, 0.0}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 5.555, 0.79455, 3.3154}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector();
        Assert.assertArrayEquals(new Double[] {}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {}, sut.getRawComponents());
        
        sut = new Vector(8.15);
        Assert.assertArrayEquals(new Double[] {8.15}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {8.15}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setY(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setZ(Number)
     */
    @Test
    public void testSetZ() throws Exception {
        //standard
        
        sut = new Vector(4);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 5.555, 0.0}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 5.555, 3.3154}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector();
        Assert.assertArrayEquals(new Double[] {}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {}, sut.getRawComponents());
        
        sut = new Vector(8.15);
        Assert.assertArrayEquals(new Double[] {8.15}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {8.15}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setZ(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setW(Number)
     */
    @Test
    public void testSetW() throws Exception {
        //standard
        
        sut = new Vector(4);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 5.555}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 5.555}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector();
        Assert.assertArrayEquals(new Double[] {}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {}, sut.getRawComponents());
        
        sut = new Vector(8.15);
        Assert.assertArrayEquals(new Double[] {8.15}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {8.15}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        
        sut = new Vector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setW(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Vector#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        Vector copy;
        
        //standard
        
        sut = new Vector(8.5);
        copy = new Vector(1);
        Vector.copy(sut, copy);
        Assert.assertEquals(
                new Vector(8.5), copy);
        
        sut = new Vector(8.5, 1.5);
        copy = new Vector(2);
        Vector.copy(sut, copy);
        Assert.assertEquals(
                new Vector(8.5, 1.5), copy);
        
        sut = new Vector(8.5, 1.5, -5.006);
        copy = new Vector(3);
        Vector.copy(sut, copy);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006), copy);
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        copy = new Vector(4);
        Vector.copy(sut, copy);
        Assert.assertEquals(
                new Vector(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new Vector();
        copy = new Vector();
        Vector.copy(sut, copy);
        Assert.assertEquals(
                new Vector(), copy);
        
        //invalid
        
        sut = new Vector(8.5, 1.5, -5.006, 11.1);
        final Vector copy1 = new Vector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                Vector.copy(sut, copy1));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final Vector copy2 = new Vector(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                Vector.copy(sut, copy2));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final BigVector copy3 = new BigVector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy3), () ->
                Vector.copy(sut, copy3));
        
        sut = new Vector(8.5, 1.5, -5.006);
        final BigMatrix copy4 = new BigMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy4), () ->
                Vector.copy(sut, copy4));
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector.copy(sut, null));
        
        sut = new Vector(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see Vector#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new Vector(), Vector.createInstance(0));
        Assert.assertEquals(
                new Vector(0.0), Vector.createInstance(1));
        Assert.assertEquals(
                new Vector(0.0, 0.0), Vector.createInstance(2));
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0), Vector.createInstance(3));
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0, 0.0), Vector.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Vector(), Vector.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see Vector#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new Vector(), Vector.identity(0));
        Assert.assertEquals(
                new Vector(1.0), Vector.identity(1));
        Assert.assertEquals(
                new Vector(1.0, 1.0), Vector.identity(2));
        Assert.assertEquals(
                new Vector(1.0, 1.0, 1.0), Vector.identity(3));
        Assert.assertEquals(
                new Vector(1.0, 1.0, 1.0, 1.0), Vector.identity(4));
        
        //invalid
        Assert.assertEquals(
                new Vector(), Vector.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see Vector#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new Vector(), Vector.origin(0));
        Assert.assertEquals(
                new Vector(0.0), Vector.origin(1));
        Assert.assertEquals(
                new Vector(0.0, 0.0), Vector.origin(2));
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0), Vector.origin(3));
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0, 0.0), Vector.origin(4));
        
        //invalid
        Assert.assertEquals(
                new Vector(), Vector.origin(-1));
    }
    
}
