/*
 * File:    Vector4Test.java
 * Package: commons.math.component.vector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.vector;

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
import commons.math.component.matrix.BigMatrix;
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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Vector4.
 *
 * @see Vector4
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Vector4.class})
public class Vector4Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Vector4Test.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private Vector4 sut;
    
    
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
     * @see Vector4#DIMENSIONALITY
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(4, Vector4.DIMENSIONALITY);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#Vector4(double, double, double, double)
     * @see Vector4#Vector4(Vector)
     * @see Vector4#Vector4(Vector3, double)
     * @see Vector4#Vector4()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        Vector4 vector = new Vector4(0.884, 2, 1.1, 8.6);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1, 8.6}, vector.getRawComponents());
        Assert.assertEquals(4, vector.getDimensionality());
        
        //vector
        Vector4 vector2 = new Vector4(new Vector(0.884, 2, 1.1, 8.6, 9.0));
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1, 8.6}, vector2.getRawComponents());
        Assert.assertEquals(4, vector2.getDimensionality());
        
        //2D vector and component
        Vector4 vector3 = new Vector4(new Vector3(0.884, 2, 1.1), 8.6);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1, 8.6}, vector3.getRawComponents());
        Assert.assertEquals(4, vector3.getDimensionality());
        
        //empty
        Vector4 vectorDefault = new Vector4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, vectorDefault.getRawComponents());
        Assert.assertEquals(4, vectorDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(vector, vector2);
        Assert.assertEquals(vector2, vector3);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new Vector4(null));
        TestUtils.assertException(NullPointerException.class, () ->
                new Vector4(null, 8.6));
    }
    
    /**
     * JUnit test of vectorString.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#vectorString()
     */
    @Test
    public void testVectorString() throws Exception {
        //standard
        
        sut = new Vector4(1, 2, 3, 4);
        Assert.assertEquals("<1.0, 2.0, 3.0, 4.0>", sut.vectorString());
        
        sut = new Vector4(1.54877, -2.316487, 77.1654, 7.9888);
        Assert.assertEquals("<1.54877, -2.316487, 77.1654, 7.9888>", sut.vectorString());
        
        sut = new Vector4(new Vector(1.0, 2.0, 3.0, 4.0, 5.0));
        Assert.assertEquals("<1.0, 2.0, 3.0, 4.0>", sut.vectorString());
        
        //empty
        
        sut = new Vector4();
        Assert.assertEquals("<0.0, 0.0, 0.0, 0.0>", sut.vectorString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new Vector4(1, 2, 3, 4);
        Assert.assertEquals("<1.0, 2.0, 3.0, 4.0>", sut.toString());
        
        sut = new Vector4(1.54877, -2.316487, 77.1654, 7.9888);
        Assert.assertEquals("<1.54877, -2.316487, 77.1654, 7.9888>", sut.toString());
        
        sut = new Vector4(new Vector(1.0, 2.0, 3.0, 4.0, 5.0));
        Assert.assertEquals("<1.0, 2.0, 3.0, 4.0>", sut.toString());
        
        //empty
        
        sut = new Vector4();
        Assert.assertEquals("<0.0, 0.0, 0.0, 0.0>", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector4(8.5, -1.944, 2.67, 5.501);
        
        //standard
        
        other = new Vector4(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector4(0, -1.944, 2.67, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector4(8.5, -1.944, 2.67, 0);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector4(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 8.33);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1, 3);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new Vector4().equals(new Vector4()));
        
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
     * @see Vector4#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector4(8.5, -1.944, 2.67, 5.501);
        
        //standard
        
        other = new Vector4(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector4(0, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector4(8.5, -1.944, 2.67, 0);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector4(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(8, -1, 3);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        Assert.assertTrue(new Vector4().dimensionalityEqual(new Vector4()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector4(8.5, -1.944, 2.67, 5.501);
        
        //standard
        
        other = new Vector4(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector4(0, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector4(8.5, -1.944, 2.67, 0);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector4(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(8.5, -1.944);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(8, -1, 3);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        Assert.assertTrue(new Vector4().lengthEqual(new Vector4()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector4(8.5, -1.944, 2.67, 5.501);
        
        //standard
        
        other = new Vector4(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector4(0, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector4(8.5, -1.944, 2.67, 0);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector4(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67, 5.501);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944, 8.33);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 5.501);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 5.501);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntVector(8, -1, 3);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new Vector4().componentTypeEqual(new Vector4()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new Vector4(8.1, 6.6, 2.9, 5.1);
        Vector4 clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), clone.hashCode());
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new Vector4(8.1, 6.6, 2.9, 5.1);
        Vector4 emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(Vector4.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), emptyCopy.hashCode());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new Vector4();
        
        //standard
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(0));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        Vector reversed;
        
        //standard
        
        sut = new Vector4(8.5, -1.944, 2.67, 5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {5.501, 2.67, -1.944, 8.5}, reversed.getRawComponents());
        
        sut = new Vector4(5.501, 8, 2.67, -1.944);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {-1.944, 2.67, 8.0, 5.501}, reversed.getRawComponents());
        
        sut = new Vector4(0, 1, 0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {1.0, 0.0, 1.0, 0.0}, reversed.getRawComponents());
        
        sut = new Vector4();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(9.545700602889239, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector4(-8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, -6, 0.514, -4.9);
        Assert.assertEquals(26.101348624161165, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector4();
        other = new Vector4();
        Assert.assertEquals(0.0, sut.distance(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(9.2, 3.75, -2.246, 8.0), sut.midpoint(other));
        
        sut = new Vector4(-8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, -6, 0.514, -4.9);
        Assert.assertEquals(
                new Vector4(0.7, -2.25, -2.246, 3.1), sut.midpoint(other));
        
        sut = new Vector4();
        other = new Vector4();
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.midpoint(other));
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#average(List)
     * @see Vector4#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        Vector other1;
        Vector other2;
        Vector other3;
        Vector other4;
        
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other1 = new Vector4(9.9, 6, 0.514, 4.9);
        other2 = new Vector4(1.8, 4.77, 0.514, 2.895);
        Assert.assertEquals(
                new Vector4(6.733333333333, 4.09, -1.326, 6.298333333333), sut.average(other1, other2));
        Assert.assertEquals(
                new Vector4(6.733333333333, 4.09, -1.326, 6.298333333333), sut.average(Arrays.asList(other1, other2)));
        
        sut = new Vector4(-8.5, 1.5, -5.006, 11.1);
        other1 = new Vector4(9.9, -6, 0.514, -4.9);
        other2 = new Vector4(-1.8, 4.77, 0.514, 2.895);
        Assert.assertEquals(
                new Vector4(-0.133333333333, 0.09, -1.326, 3.031666666667), sut.average(other1, other2));
        Assert.assertEquals(
                new Vector4(-0.133333333333, 0.09, -1.326, 3.031666666667), sut.average(Arrays.asList(other1, other2)));
        
        sut = new Vector4();
        other1 = new Vector4();
        other2 = new Vector4();
        other3 = new Vector4();
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector otherF11 = new Vector4(9.9, 6, 0.514, 4.9);
        final Vector otherF12 = new Vector(1.8, 4.77);
        final Vector otherF13 = new Vector4(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF12), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF12), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector otherF31 = new Vector4(9.9, 6, 0.514, 4.9);
        final Vector otherF33 = new Vector4(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(16.094, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector4(8.5, -1.5, -5.006, 11.1);
        Assert.assertEquals(13.094, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector4(-8.5, -1.5, 5.006, -11.1);
        Assert.assertEquals(-16.094, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.sum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(222.770036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector4(8.5, -1.5, -5.006, 11.1);
        Assert.assertEquals(222.770036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector4(-8.5, -1.5, 5.006, -11.1);
        Assert.assertEquals(222.770036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.squareSum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(18.4, 7.5, -4.492, 16.0), sut.plus(other));
        
        sut = new Vector4(8.5, -1.5, -5.006, 11.1);
        other = new Vector4(-9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(-1.4, 4.5, -4.492, 16.0), sut.plus(other));
        
        sut = new Vector4(-8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, -6, 0.514, -4.9);
        Assert.assertEquals(
                new Vector4(1.4, -4.5, -4.492, 6.2), sut.plus(other));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(1, 1, 1, 1);
        Assert.assertEquals(
                new Vector4(9.5, 2.5, -4.006, 12.1), sut.plus(other));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(0, 0, 0, 0);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), sut.plus(other));
        
        sut = new Vector4();
        other = new Vector4();
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.plus(other));
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(-1.4, -4.5, -5.52, 6.2), sut.minus(other));
        
        sut = new Vector4(8.5, -1.5, -5.006, 11.1);
        other = new Vector4(-9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(18.4, -7.5, -5.52, 6.2), sut.minus(other));
        
        sut = new Vector4(-8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, -6, 0.514, -4.9);
        Assert.assertEquals(
                new Vector4(-18.4, 7.5, -5.52, 16), sut.minus(other));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(1, 1, 1, 1);
        Assert.assertEquals(
                new Vector4(7.5, 0.5, -6.006, 10.1), sut.minus(other));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(0, 0, 0, 0);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), sut.minus(other));
        
        sut = new Vector4();
        other = new Vector4();
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.minus(other));
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#times(BaseComponent)
     */
    @Test
    public void testTimes() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(84.15, 9.0, -2.573084, 54.39), sut.times(other));
        
        sut = new Vector4(8.5, -1.5, -5.006, 11.1);
        other = new Vector4(-9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(-84.15, -9.0, -2.573084, 54.39), sut.times(other));
        
        sut = new Vector4(-8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, -6, 0.514, -4.9);
        Assert.assertEquals(
                new Vector4(-84.15, -9.0, -2.573084, -54.39), sut.times(other));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(1, 1, 1, 1);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), sut.times(other));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(0, 0, 0, 0);
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.times(other));
        
        sut = new Vector4();
        other = new Vector4();
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.times(other));
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times(null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector4(42.5, 7.5, -25.03, 55.5), sut.scale(5));
        
        sut = new Vector4(8.5, -1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector4(8.5799, -1.5141, -5.0530564, 11.20434), sut.scale(1.0094));
        
        sut = new Vector4(-8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector4(-139.57, 24.63, -82.19852, 182.262), sut.scale(16.42));
        
        sut = new Vector4(-8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector4(22.695, -4.005, 13.36602, -29.637), sut.scale(-2.67));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), sut.scale(1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector4(-8.5, -1.5, 5.006, -11.1), sut.scale(-1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.scale(0));
        
        sut = new Vector4();
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.scale(7.1));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(0.858585858586, 0.25, -9.739299610895, 2.265306122449), sut.dividedBy(other));
        
        sut = new Vector4(8.5, -1.5, -5.006, 11.1);
        other = new Vector4(-9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new Vector4(-0.858585858586, -0.25, -9.739299610895, 2.265306122449), sut.dividedBy(other));
        
        sut = new Vector4(-8.5, 1.5, -5.006, 11.1);
        other = new Vector4(9.9, -6, 0.514, -4.9);
        Assert.assertEquals(
                new Vector4(-0.858585858586, -0.25, -9.739299610895, -2.265306122449), sut.dividedBy(other));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        other = new Vector4(1, 1, 1, 1);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), sut.dividedBy(other));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector other0 = new Vector4(0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new Vector4();
        final Vector other02 = new Vector4();
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other02));
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new Vector4(9.0, 2.0, -5.0, 11.0), sut.round());
        
        sut = new Vector4(8.56501201650, -1.589075460001, 5.006498505404, 11.118974654321);
        Assert.assertEquals(
                new Vector4(9.0, -2.0, 5.0, 11.0), sut.round());
        
        sut = new Vector4();
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.round());
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#dot(VectorInterface)
     */
    @Test
    public void testDot() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        other = new Vector4(1.0481561, 1.655742, 0.974454, -5.441);
        Assert.assertEquals(-29.333825123080004, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector4(18.1644, 9.12154, -7.7741, 11.13211);
        other = new Vector4(12.0481561, 71.655742, 30.974454, 18.06442);
        Assert.assertEquals(832.7548512303199, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        other = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        Assert.assertEquals(334.2204231937, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        other = new Vector4(-8.1644, -9.12154, -7.7741, -11.13211);
        Assert.assertEquals(-334.2204231937, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        other = new Vector4(0.00081644, 0.000912154, 0.00077741, .0001113211);
        Assert.assertEquals(0.022268893744681002, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector4(1, -1, 3, 3);
        other = new Vector4(3, 3, 0, 0);
        Assert.assertEquals(0.0, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        other = Vector4.origin();
        Assert.assertEquals(0.0, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        other = Vector4.identity();
        Assert.assertEquals(36.19215, sut.dot(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        final Vector other1 = new Vector(1.0481561, 1.655742);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dot(other1));
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dot(null));
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        Assert.assertEquals(
                new Vector4(0.446588753155, 0.498943850798, 0.425239530878, 0.60892106277), sut.normalize());
        
        sut = new Vector4(-8.1644, 9.12154, -7.7741, 11.13211);
        Assert.assertEquals(
                new Vector4(-0.446588753155, 0.498943850798, -0.425239530878, 0.60892106277), sut.normalize());
        
        sut = new Vector4(-8.1644, -9.12154, -7.7741, -11.13211);
        Assert.assertEquals(
                new Vector4(-0.446588753155, -0.498943850798, -0.425239530878, -0.60892106277), sut.normalize());
        
        sut = new Vector4(18.1644, 9.12154, -7.7741, 11.13211);
        Assert.assertEquals(
                new Vector4(0.743103045113, 0.373160916414, -0.318037335833, 0.455413051878), sut.normalize());
        
        sut = new Vector4(1, -1, 3, 3);
        Assert.assertEquals(
                new Vector4(0.22360679775, -0.22360679775, 0.67082039325, 0.67082039325), sut.normalize());
        
        sut = Vector4.origin();
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), sut.normalize());
        
        sut = Vector4.identity();
        Assert.assertEquals(
                new Vector4(0.5, 0.5, 0.5, 0.5), sut.normalize());
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector4(8.1644, 9.12154, 7.7741, 11.13211);
        Assert.assertEquals(18.281696398138223, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector4(-8.1644, 9.12154, -7.7741, 11.13211);
        Assert.assertEquals(18.281696398138223, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector4(-8.1644, -9.12154, -7.7741, -11.13211);
        Assert.assertEquals(18.281696398138223, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector4(18.1644, 9.12154, -7.7741, 11.13211);
        Assert.assertEquals(24.44398541960169, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector4(1, -1, 3, 3);
        Assert.assertEquals(4.47213595499958, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = Vector4.origin();
        Assert.assertEquals(0.0, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = Vector4.identity();
        Assert.assertEquals(2.0, sut.hypotenuse(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        Vector copy;
        
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        copy = new Vector4();
        sut.copy(copy);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new Vector4();
        copy = new Vector4();
        sut.copy(copy);
        Assert.assertEquals(
                new Vector4(), copy);
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector copy1 = new Vector(4.0, 19.6, 1.774, 8.6, 1.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#copyMeta(Component)
     */
    @Test
    public void testCopyMeta() throws Exception {
        Vector component1 = new Vector4(8.1, 6.6, 7.0, 1.7);
        Assert.assertEquals(4, component1.getDimensionality());
        Assert.assertArrayEquals(new Double[] {8.1, 6.6, 7.0, 1.7}, component1.getRawComponents());
        
        Vector component2 = new Vector4(9.1, 6.3, 1.7, 0.3);
        component1.copyMeta(component2);
        Assert.assertEquals(4, component2.getDimensionality());
        Assert.assertArrayEquals(new Double[] {9.1, 6.3, 1.7, 0.3}, component2.getRawComponents());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new Vector4(1.0, 2.0, 3.0, 4.0);
        Assert.assertFalse(sut.isResizeable());
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        sut.redim(4);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        sut.redim(1);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        sut.redim(0);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        sut.redim(-1);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), sut);
    }
    
    /**
     * JUnit test of subVector4.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#subVector(int, int)
     * @see Vector4#subVector(int)
     */
    @Test
    public void testSubVector() throws Exception {
        //standard
        
        Vector subVector;
        sut = new Vector4(1.0, 2.0, 3.0, 4.0);
        Assert.assertFalse(sut.isResizeable());
        
        subVector = sut.subVector(0, 4);
        Assert.assertEquals(
                new Vector4(1.0, 2.0, 3.0, 4.0), subVector);
        
        //invalid
        
        sut = new Vector4(1.0, 2.0, 3.0, 4.0);
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Vector4.DIMENSIONALITY), () ->
                sut.subVector(0, 0));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Vector4.DIMENSIONALITY), () ->
                sut.subVector(0, 1));
        
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
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 15, 4), () ->
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
     * @see Vector4#dimensionalityToLength(int)
     * @see Vector4#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        
        //standard
        Assert.assertEquals(4, sut.dimensionalityToLength());
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
     * @see Vector4#lengthToDimensionality(int)
     * @see Vector4#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        
        //standard
        Assert.assertEquals(4, sut.lengthToDimensionality());
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
     * @see Vector4#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        
        //standard
        Assert.assertEquals(4, sut.getDimensionality());
        Whitebox.setInternalState(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(4, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        Vector component;
        
        //standard
        component = new Vector4(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        Assert.assertArrayEquals(new Double[] {8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828}, component.getRawComponents());
        Assert.assertArrayEquals(new Double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        Vector component;
        
        //standard
        
        component = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1}, component.getComponents());
        
        component = new Vector4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, component.getComponents());
        
        component = new Vector4(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999993);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999993}, component.getComponents());
        
        component = new Vector4(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999993);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        Vector component;
        
        //standard
        component = new Vector4(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        Assert.assertArrayEquals(new double[] {8.160456540859, 6.649084984108, 7.04808971059084054054, 2.6908405165094841828}, component.getPrimitiveComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new Double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getRaw(int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(8.5, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1), TestUtils.DELTA);
        
        sut = new Vector4(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999993);
        Assert.assertEquals(8.500000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1), TestUtils.DELTA);
        
        sut = new Vector4(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999993);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.getRaw(4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.getRaw(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        
        sut = new Vector4(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999993);
        Assert.assertEquals(8.500000000001, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1), TestUtils.DELTA);
        
        sut = new Vector4(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999993);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.get(4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.get(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
    }
    
    /**
     * JUnit test of getRawX.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getRawX()
     */
    @Test
    public void testGetRawX() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(8.15, sut.getRawX(), TestUtils.DELTA);
        
        sut = new Vector4(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(8.15000000000000000025, sut.getRawX(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.getRawX(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawX(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getX()
     */
    @Test
    public void testGetX() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(8.15, sut.getX(), TestUtils.DELTA);
        
        sut = new Vector4(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(8.15, sut.getX(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.getX(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getX(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawY.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getRawY()
     */
    @Test
    public void testGetRawY() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(77.1654, sut.getRawY(), TestUtils.DELTA);
        
        sut = new Vector4(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(77.16549999999999999999996, sut.getRawY(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.getRawY(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawY(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getY()
     */
    @Test
    public void testGetY() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(77.1654, sut.getY(), TestUtils.DELTA);
        
        sut = new Vector4(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(77.1655, sut.getY(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.getY(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getY(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getRawZ()
     */
    @Test
    public void testGetRawZ() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(0.79455, sut.getRawZ(), TestUtils.DELTA);
        
        sut = new Vector4(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(0.794550000000000000000001, sut.getRawZ(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.getRawZ(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(0.79455, sut.getZ(), TestUtils.DELTA);
        
        sut = new Vector4(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(0.79455, sut.getZ(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.getZ(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawW.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getRawW()
     */
    @Test
    public void testGetRawW() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(3.3154, sut.getRawW(), TestUtils.DELTA);
        
        sut = new Vector4(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(3.315499999999999999999999886, sut.getRawW(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.getRawW(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getW()
     */
    @Test
    public void testGetW() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(3.3154, sut.getW(), TestUtils.DELTA);
        
        sut = new Vector4(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(3.3155, sut.getW(), TestUtils.DELTA);
        
        sut = new Vector4();
        Assert.assertEquals(0, sut.getW(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector4();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(4, sut.getDimensionality());
        
        sut = new Vector4();
        Assert.assertEquals(4, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new Vector4();
        Assert.assertEquals(4, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new Vector4();
        Assert.assertEquals(Vector4.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new Vector4();
        Assert.assertEquals(Double.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new Vector4();
        Assert.assertTrue(sut.getHandler() instanceof DoubleComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new Vector4();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new Vector4();
        Assert.assertEquals("4D Vector", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new Vector4();
        Assert.assertEquals("4D Vectors", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new Vector4();
        Assert.assertEquals(0.000000000001, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new Vector4();
        Assert.assertFalse(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        Vector component;
        Double[] newComponents;
        
        //standard
        
        component = new Vector4(5.501, 2.67, -1.944, 8.5);
        newComponents = new Double[] {5.6, 6.7, 1.1, 7.2};
        Assert.assertEquals(4, component.getDimensionality());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Double[] {5.6, 6.7, 1.1, 7.2}, component.getRawComponents());
        Assert.assertEquals(4, component.getDimensionality());
        
        //invalid
        
        final Vector component1 = new Vector4(5.501, 2.67, -1.944, 8.5);
        final Double[] newComponents1 = new Double[] {5.6, 6.7};
        Assert.assertFalse(component1.isResizeable());
        TestUtils.assertException(IndexOutOfBoundsException.class, component1.getErrorHandler().componentLengthNotEqualErrorMessage(newComponents1, component1.getLength()), () ->
                component1.setComponents(newComponents1));
        
        final Vector component2 = new Vector4(5.501, 2.67, -1.944, 8.5);
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(1, 3.0);
        sut.set(2, 0.997);
        sut.set(3, 6.01);
        Assert.assertArrayEquals(new Double[] {5.77, 3.0, 0.997, 6.01}, sut.getRawComponents());
        
        sut = new Vector4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.set(0, 8.5);
        sut.set(1, 1.5);
        sut.set(2, -5.006);
        sut.set(3, 11.1);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006, 11.1}, sut.getRawComponents());
        
        sut = new Vector4(8.500000000001, 1.499999999996, -5.005999999999, 11.099999999993);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999, 11.099999999993}, sut.getRawComponents());
        sut.set(0, 5.769999999996);
        sut.set(1, 3.000000000001);
        sut.set(2, 8.110000000001);
        sut.set(3, 5.800000000001);
        Assert.assertArrayEquals(new Double[] {5.769999999996, 3.000000000001, 8.110000000001, 5.800000000001}, sut.getRawComponents());
        
        sut = new Vector4(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999993);
        Assert.assertArrayEquals(new Double[] {8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999, 11.0999999999999999999993}, sut.getRawComponents());
        sut.set(0, 6.5000000000000000000000001);
        sut.set(1, -1.49999999999999999999996);
        sut.set(2, 3.0059999999999999999999);
        sut.set(3, 5.7999999999999999999999);
        Assert.assertArrayEquals(new Double[] {6.5, -1.5, 3.006, 5.8}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 4), () ->
                sut.set(4, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 5.5));
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#setX(Number)
     */
    @Test
    public void testSetX() throws Exception {
        //standard
        
        sut = new Vector4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Double[] {5.555, 0.0, 0.0, 0.0}, sut.getRawComponents());
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Double[] {5.555, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setX(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#setY(Number)
     */
    @Test
    public void testSetY() throws Exception {
        //standard
        
        sut = new Vector4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {0.0, 5.555, 0.0, 0.0}, sut.getRawComponents());
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 5.555, 0.79455, 3.3154}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setY(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#setZ(Number)
     */
    @Test
    public void testSetZ() throws Exception {
        //standard
        
        sut = new Vector4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 5.555, 0.0}, sut.getRawComponents());
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 5.555, 3.3154}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setZ(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#setW(Number)
     */
    @Test
    public void testSetW() throws Exception {
        //standard
        
        sut = new Vector4();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0, 5.555}, sut.getRawComponents());
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 5.555}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector4(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setW(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455, 3.3154}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        Vector copy;
        
        //standard
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        copy = new Vector4();
        Vector4.copy(sut, copy);
        Assert.assertEquals(
                new Vector4(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new Vector4();
        copy = new Vector4();
        Vector4.copy(sut, copy);
        Assert.assertEquals(
                new Vector4(), copy);
        
        //invalid
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        final Vector copy1 = new Vector(4.0, 19.6, 1.774, 8.6, 1.1);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                Vector4.copy(sut, copy1));
        
        sut = new Vector4(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector4.copy(sut, null));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#createInstance()
     * @see Vector4#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.createInstance());
        
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.createInstance(0));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.createInstance(1));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.createInstance(2));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.createInstance(3));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.createInstance(4));
        
        //invalid
        
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#identity()
     * @see Vector4#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        
        Assert.assertEquals(
                new Vector4(1.0, 1.0, 1.0, 1.0), Vector4.identity());
        
        Assert.assertEquals(
                new Vector4(1.0, 1.0, 1.0, 1.0), Vector4.identity(0));
        Assert.assertEquals(
                new Vector4(1.0, 1.0, 1.0, 1.0), Vector4.identity(1));
        Assert.assertEquals(
                new Vector4(1.0, 1.0, 1.0, 1.0), Vector4.identity(2));
        Assert.assertEquals(
                new Vector4(1.0, 1.0, 1.0, 1.0), Vector4.identity(3));
        Assert.assertEquals(
                new Vector4(1.0, 1.0, 1.0, 1.0), Vector4.identity(4));
        
        //invalid
        
        Assert.assertEquals(
                new Vector4(1.0, 1.0, 1.0, 1.0), Vector4.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#origin()
     * @see Vector4#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.origin());
        
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.origin(0));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.origin(1));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.origin(2));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.origin(3));
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.origin(4));
        
        //invalid
        
        Assert.assertEquals(
                new Vector4(0.0, 0.0, 0.0, 0.0), Vector4.origin(-1));
    }
    
}
