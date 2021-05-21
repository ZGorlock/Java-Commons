/*
 * File:    Vector2Test.java
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
 * JUnit test of Vector2.
 *
 * @see Vector2
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Vector2.class})
public class Vector2Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Vector2Test.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private Vector2 sut;
    
    
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
     * @see Vector2#DIMENSIONALITY
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(2, Vector2.DIMENSIONALITY);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#Vector2(double, double)
     * @see Vector2#Vector2(Vector)
     * @see Vector2#Vector2()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        Vector2 vector = new Vector2(0.884, 2);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0}, vector.getRawComponents());
        Assert.assertEquals(2, vector.getDimensionality());
        
        //vector
        Vector2 vector2 = new Vector2(new Vector(0.884, 2, 1.1, 8.6, 9.0));
        Assert.assertArrayEquals(new Double[] {0.884, 2.0}, vector2.getRawComponents());
        Assert.assertEquals(2, vector2.getDimensionality());
        
        //empty
        Vector2 vectorDefault = new Vector2();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0}, vectorDefault.getRawComponents());
        Assert.assertEquals(2, vectorDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(vector, vector2);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new Vector2(null));
    }
    
    /**
     * JUnit test of vectorString.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#vectorString()
     */
    @Test
    public void testVectorString() throws Exception {
        //standard
        
        sut = new Vector2(1, 2);
        Assert.assertEquals("<1.0, 2.0>", sut.vectorString());
        
        sut = new Vector2(1.54877, -2.316487);
        Assert.assertEquals("<1.54877, -2.316487>", sut.vectorString());
        
        sut = new Vector2(new Vector(1.0, 2.0, 3.0, 4.0, 5.0));
        Assert.assertEquals("<1.0, 2.0>", sut.vectorString());
        
        //empty
        
        sut = new Vector2();
        Assert.assertEquals("<0.0, 0.0>", sut.vectorString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new Vector2(1, 2);
        Assert.assertEquals("<1.0, 2.0>", sut.toString());
        
        sut = new Vector2(1.54877, -2.316487);
        Assert.assertEquals("<1.54877, -2.316487>", sut.toString());
        
        sut = new Vector2(new Vector(1.0, 2.0, 3.0, 4.0, 5.0));
        Assert.assertEquals("<1.0, 2.0>", sut.toString());
        
        //empty
        
        sut = new Vector2();
        Assert.assertEquals("<0.0, 0.0>", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector2(8.5, -1.944);
        
        //standard
        
        other = new Vector2(8.5, -1.944);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector2(0, -1.944);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector2(8.5, 0);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector2(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(8.5, -1.944);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 8.33);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawVector(8.5, -1.944);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new Vector2().equals(new Vector2()));
        
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
     * @see Vector2#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector2(8.5, -1.944);
        
        //standard
        
        other = new Vector2(8.5, -1.944);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector2(0, -1.944);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector2(8.5, 0);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector2(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944, 8.33);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawVector(8.5, -1.944);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(8, -1);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        Assert.assertTrue(new Vector2().dimensionalityEqual(new Vector2()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector2(8.5, -1.944);
        
        //standard
        
        other = new Vector2(8.5, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector2(0, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector2(8.5, 0);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector2(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(8.5, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(8.5, -1.944, 8.33);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawVector(8.5, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(8, -1);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        Assert.assertTrue(new Vector2().lengthEqual(new Vector2()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector2(8.5, -1.944);
        
        //standard
        
        other = new Vector2(8.5, -1.944);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector2(0, -1.944);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector2(8.5, 0);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector2(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944, 8.33);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawVector(8.5, -1.944);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigVector(8.5, -1.944);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntVector(8, -1);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new Vector2().componentTypeEqual(new Vector2()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new Vector2(8.1, 6.6);
        Vector2 clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), clone.hashCode());
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new Vector2(8.1, 6.6);
        Vector2 emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(Vector2.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), emptyCopy.hashCode());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new Vector2();
        
        //standard
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.createNewInstance(0));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        Vector reversed;
        
        //standard
        
        sut = new Vector2(8.5, -1.944);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {-1.944, 8.5}, reversed.getRawComponents());
        
        sut = new Vector2(5.501, 8);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {8.0, 5.501}, reversed.getRawComponents());
        
        sut = new Vector2(0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {1.0, 0.0}, reversed.getRawComponents());
        
        sut = new Vector2();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {0.0, 0.0}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(4.712748667179271, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(9.9, -6);
        Assert.assertEquals(19.86982637065558, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector2();
        other = new Vector2();
        Assert.assertEquals(0.0, sut.distance(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(9.2, 3.75), sut.midpoint(other));
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(9.9, -6);
        Assert.assertEquals(
                new Vector2(0.7, -2.25), sut.midpoint(other));
        
        sut = new Vector2();
        other = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.midpoint(other));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#average(List)
     * @see Vector2#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        Vector other1;
        Vector other2;
        Vector other3;
        Vector other4;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other1 = new Vector2(9.9, 6);
        other2 = new Vector2(1.8, 4.77);
        Assert.assertEquals(
                new Vector2(6.733333333333, 4.09), sut.average(other1, other2));
        Assert.assertEquals(
                new Vector2(6.733333333333, 4.09), sut.average(Arrays.asList(other1, other2)));
        
        sut = new Vector2(-8.5, 1.5);
        other1 = new Vector2(9.9, -6);
        other2 = new Vector2(-1.8, 4.77);
        Assert.assertEquals(
                new Vector2(-0.133333333333, 0.09), sut.average(other1, other2));
        Assert.assertEquals(
                new Vector2(-0.133333333333, 0.09), sut.average(Arrays.asList(other1, other2)));
        
        sut = new Vector2();
        other1 = new Vector2();
        other2 = new Vector2();
        other3 = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector otherF11 = new Vector2(9.9, 6);
        final Vector otherF12 = new Vector(1.8, 4.77, 0.514);
        final Vector otherF13 = new Vector2(11.7, 0.447);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF12), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF12), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new Vector2(8.5, 1.5);
        final Vector otherF31 = new Vector2(9.9, 6);
        final Vector otherF33 = new Vector2(11.7, 0.447);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(10.0, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector2(8.5, -1.5);
        Assert.assertEquals(7.0, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector2(-8.5, -1.5);
        Assert.assertEquals(-10.0, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector2();
        Assert.assertEquals(0, sut.sum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(74.5, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector2(8.5, -1.5);
        Assert.assertEquals(74.5, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector2(-8.5, -1.5);
        Assert.assertEquals(74.5, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector2();
        Assert.assertEquals(0, sut.squareSum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareDifference.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#squareDifference()
     */
    @Test
    public void testSquareDifference() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(70.0, sut.squareDifference(), TestUtils.DELTA);
        
        sut = new Vector2(8.5, -1.5);
        Assert.assertEquals(70.0, sut.squareDifference(), TestUtils.DELTA);
        
        sut = new Vector2(-8.5, -1.5);
        Assert.assertEquals(70.0, sut.squareDifference(), TestUtils.DELTA);
        
        sut = new Vector2();
        Assert.assertEquals(0, sut.squareDifference(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(18.4, 7.5), sut.plus(other));
        
        sut = new Vector2(8.5, -1.5);
        other = new Vector2(-9.9, 6);
        Assert.assertEquals(
                new Vector2(-1.4, 4.5), sut.plus(other));
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(9.9, -6);
        Assert.assertEquals(
                new Vector2(1.4, -4.5), sut.plus(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(1, 1);
        Assert.assertEquals(
                new Vector2(9.5, 2.5), sut.plus(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(0, 0);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), sut.plus(other));
        
        sut = new Vector2();
        other = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.plus(other));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(-1.4, -4.5), sut.minus(other));
        
        sut = new Vector2(8.5, -1.5);
        other = new Vector2(-9.9, 6);
        Assert.assertEquals(
                new Vector2(18.4, -7.5), sut.minus(other));
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(9.9, -6);
        Assert.assertEquals(
                new Vector2(-18.4, 7.5), sut.minus(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(1, 1);
        Assert.assertEquals(
                new Vector2(7.5, 0.5), sut.minus(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(0, 0);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), sut.minus(other));
        
        sut = new Vector2();
        other = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.minus(other));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#times(BaseComponent)
     */
    @Test
    public void testTimes() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(84.15, 9.0), sut.times(other));
        
        sut = new Vector2(8.5, -1.5);
        other = new Vector2(-9.9, 6);
        Assert.assertEquals(
                new Vector2(-84.15, -9.0), sut.times(other));
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(9.9, -6);
        Assert.assertEquals(
                new Vector2(-84.15, -9.0), sut.times(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(1, 1);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), sut.times(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(0, 0);
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.times(other));
        
        sut = new Vector2();
        other = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.times(other));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times(null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(
                new Vector2(42.5, 7.5), sut.scale(5));
        
        sut = new Vector2(8.5, -1.5);
        Assert.assertEquals(
                new Vector2(8.5799, -1.5141), sut.scale(1.0094));
        
        sut = new Vector2(-8.5, 1.5);
        Assert.assertEquals(
                new Vector2(-139.57, 24.63), sut.scale(16.42));
        
        sut = new Vector2(-8.5, 1.5);
        Assert.assertEquals(
                new Vector2(22.695, -4.005), sut.scale(-2.67));
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), sut.scale(1));
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(
                new Vector2(-8.5, -1.5), sut.scale(-1));
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.scale(0));
        
        sut = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.scale(7.1));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(0.858585858586, 0.25), sut.dividedBy(other));
        
        sut = new Vector2(8.5, -1.5);
        other = new Vector2(-9.9, 6);
        Assert.assertEquals(
                new Vector2(-0.858585858586, -0.25), sut.dividedBy(other));
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(9.9, -6);
        Assert.assertEquals(
                new Vector2(-0.858585858586, -0.25), sut.dividedBy(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(1, 1);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), sut.dividedBy(other));
        
        sut = new Vector2(8.5, 1.5);
        final Vector other0 = new Vector2(0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new Vector2();
        final Vector other02 = new Vector2();
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other02));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(
                new Vector2(9.0, 2.0), sut.round());
        
        sut = new Vector2(8.56501201650, -1.589075460001);
        Assert.assertEquals(
                new Vector2(9.0, -2.0), sut.round());
        
        sut = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.round());
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#dot(VectorInterface)
     */
    @Test
    public void testDot() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.1644, 9.12154);
        other = new Vector2(1.0481561, 1.655742);
        Assert.assertEquals(23.660482545519997, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector2(18.1644, 9.12154);
        other = new Vector2(12.0481561, 71.655742);
        Assert.assertEquals(872.45824354552, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector2(8.1644, 9.12154);
        other = new Vector2(8.1644, 9.12154);
        Assert.assertEquals(149.8599193316, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector2(8.1644, 9.12154);
        other = new Vector2(-8.1644, -9.12154);
        Assert.assertEquals(-149.8599193316, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector2(8.1644, 9.12154);
        other = new Vector2(0.00081644, 0.000912154);
        Assert.assertEquals(0.014985991933160001, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector2(1, -1);
        other = new Vector2(2, 2);
        Assert.assertEquals(0.0, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector2(8.1644, 9.12154);
        other = Vector2.origin();
        Assert.assertEquals(0.0, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector2(8.1644, 9.12154);
        other = Vector2.identity();
        Assert.assertEquals(17.28594, sut.dot(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector2(8.1644, 9.12154);
        final Vector other1 = new Vector(1.0481561, 1.655742, 0.974454);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dot(other1));
        
        sut = new Vector2(8.1644, 9.12154);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dot(null));
    }
    
    /**
     * JUnit test of dotFlop.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#dotFlop(Vector)
     */
    @Test
    public void testDotFlop() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(75.15, 65.85), sut.dotFlop(other));
        
        sut = new Vector2(8.5, -1.5);
        other = new Vector2(-9.9, 6);
        Assert.assertEquals(
                new Vector2(-75.15, 65.85), sut.dotFlop(other));
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(75.15, -65.85);
        Assert.assertEquals(
                new Vector2(-540.0, 672.45), sut.dotFlop(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(1, 1);
        Assert.assertEquals(
                new Vector2(7.0, 10.0), sut.dotFlop(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(0, 0);
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.dotFlop(other));
        
        sut = new Vector2();
        other = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.dotFlop(other));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(other1, Vector2.DIMENSIONALITY), () ->
                sut.dotFlop(other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dotFlop(null));
    }
    
    /**
     * JUnit test of dotFlopNegative.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#dotFlopNegative(Vector)
     */
    @Test
    public void testDotFlopNegative() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(93.15, 36.15), sut.dotFlopNegative(other));
        
        sut = new Vector2(8.5, -1.5);
        other = new Vector2(93.15, 36.15);
        Assert.assertEquals(
                new Vector2(737.55, 447.0), sut.dotFlopNegative(other));
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(93.15, 36.15);
        Assert.assertEquals(
                new Vector2(-737.55, -447.0), sut.dotFlopNegative(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(1, 1);
        Assert.assertEquals(
                new Vector2(10.0, 7.0), sut.dotFlopNegative(other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(0, 0);
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.dotFlopNegative(other));
        
        sut = new Vector2();
        other = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.dotFlopNegative(other));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(other1, Vector2.DIMENSIONALITY), () ->
                sut.dotFlopNegative(other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dotFlopNegative(null));
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.1644, 9.12154);
        Assert.assertEquals(
                new Vector2(0.666931955519, 0.74511862593), sut.normalize());
        
        sut = new Vector2(-8.1644, 9.12154);
        Assert.assertEquals(
                new Vector2(-0.666931955519, 0.74511862593), sut.normalize());
        
        sut = new Vector2(-8.1644, -9.12154);
        Assert.assertEquals(
                new Vector2(-0.666931955519, -0.74511862593), sut.normalize());
        
        sut = new Vector2(18.1644, 9.12154);
        Assert.assertEquals(
                new Vector2(0.893651671491, 0.448761284027), sut.normalize());
        
        sut = new Vector2(1, -1);
        Assert.assertEquals(
                new Vector2(0.707106781187, -0.707106781187), sut.normalize());
        
        sut = Vector2.origin();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), sut.normalize());
        
        sut = Vector2.identity();
        Assert.assertEquals(
                new Vector2(0.707106781187, 0.707106781187), sut.normalize());
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.1644, 9.12154);
        Assert.assertEquals(12.241728608803578, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector2(-8.1644, 9.12154);
        Assert.assertEquals(12.241728608803578, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector2(-8.1644, -9.12154);
        Assert.assertEquals(12.241728608803578, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector2(18.1644, 9.12154);
        Assert.assertEquals(20.326040424332525, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector2(1, -1);
        Assert.assertEquals(1.4142135623730951, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = Vector2.origin();
        Assert.assertEquals(0.0, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = Vector2.identity();
        Assert.assertEquals(1.4142135623730951, sut.hypotenuse(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        Vector copy;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        copy = new Vector2();
        sut.copy(copy);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), copy);
        
        sut = new Vector2();
        copy = new Vector2();
        sut.copy(copy);
        Assert.assertEquals(
                new Vector2(), copy);
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector copy1 = new Vector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#copyMeta(Component)
     */
    @Test
    public void testCopyMeta() throws Exception {
        Vector component1 = new Vector2(8.1, 6.6);
        Assert.assertEquals(2, component1.getDimensionality());
        Assert.assertArrayEquals(new Double[] {8.1, 6.6}, component1.getRawComponents());
        
        Vector component2 = new Vector2(9.1, 6.3);
        component1.copyMeta(component2);
        Assert.assertEquals(2, component2.getDimensionality());
        Assert.assertArrayEquals(new Double[] {9.1, 6.3}, component2.getRawComponents());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new Vector2(1.0, 2.0);
        Assert.assertFalse(sut.isResizeable());
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        sut.redim(3);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), sut);
        
        sut = new Vector2(8.5, 1.5);
        sut.redim(1);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), sut);
        
        sut = new Vector2(8.5, 1.5);
        sut.redim(0);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), sut);
        
        sut = new Vector2(8.5, 1.5);
        sut.redim(-1);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), sut);
    }
    
    /**
     * JUnit test of subVector2.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#subVector(int, int)
     * @see Vector2#subVector(int)
     */
    @Test
    public void testSubVector() throws Exception {
        //standard
        
        Vector subVector;
        sut = new Vector2(1.0, 2.0);
        Assert.assertFalse(sut.isResizeable());
        
        subVector = sut.subVector(0, 2);
        Assert.assertEquals(
                new Vector2(1.0, 2.0), subVector);
        
        //invalid
        
        sut = new Vector2(1.0, 2.0);
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Vector2.DIMENSIONALITY), () ->
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
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 15, 2), () ->
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
     * @see Vector2#dimensionalityToLength(int)
     * @see Vector2#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new Vector2(8.5, 1.5);
        
        //standard
        Assert.assertEquals(2, sut.dimensionalityToLength());
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
     * @see Vector2#lengthToDimensionality(int)
     * @see Vector2#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new Vector2(8.5, 1.5);
        
        //standard
        Assert.assertEquals(2, sut.lengthToDimensionality());
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
     * @see Vector2#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new Vector2(8.5, 1.5);
        
        //standard
        Assert.assertEquals(2, sut.getDimensionality());
        Whitebox.setInternalState(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(2, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        Vector component;
        
        //standard
        component = new Vector2(8.160456540859010650161, 6.64908498410841501980404);
        Assert.assertArrayEquals(new Double[] {8.160456540859010650161, 6.64908498410841501980404}, component.getRawComponents());
        Assert.assertArrayEquals(new Double[] {8.160456540859, 6.649084984108}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        Vector component;
        
        //standard
        
        component = new Vector2(8.5, 1.5);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5}, component.getComponents());
        
        component = new Vector2();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0}, component.getComponents());
        
        component = new Vector2(8.500000000001, 1.499999999996);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996}, component.getComponents());
        
        component = new Vector2(8.5000000000000000000000001, 1.49999999999999999999996);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        Vector component;
        
        //standard
        component = new Vector2(8.160456540859010650161, 6.64908498410841501980404);
        Assert.assertArrayEquals(new double[] {8.160456540859, 6.649084984108}, component.getPrimitiveComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new Double[] {8.160456540859, 6.649084984108}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getRaw(int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(8.5, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1), TestUtils.DELTA);
        
        sut = new Vector2(8.500000000001, 1.499999999996);
        Assert.assertEquals(8.500000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1), TestUtils.DELTA);
        
        sut = new Vector2(8.5000000000000000000000001, 1.49999999999999999999996);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.getRaw(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.getRaw(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        
        sut = new Vector2(8.500000000001, 1.499999999996);
        Assert.assertEquals(8.500000000001, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1), TestUtils.DELTA);
        
        sut = new Vector2(8.5000000000000000000000001, 1.49999999999999999999996);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.get(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.get(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
    }
    
    /**
     * JUnit test of getRawX.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getRawX()
     */
    @Test
    public void testGetRawX() throws Exception {
        //standard
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertEquals(8.15, sut.getRawX(), TestUtils.DELTA);
        
        sut = new Vector2(8.15000000000000000025, 77.16549999999999999999996);
        Assert.assertEquals(8.15000000000000000025, sut.getRawX(), TestUtils.DELTA);
        
        sut = new Vector2();
        Assert.assertEquals(0, sut.getRawX(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector2();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawX(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getX()
     */
    @Test
    public void testGetX() throws Exception {
        //standard
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertEquals(8.15, sut.getX(), TestUtils.DELTA);
        
        sut = new Vector2(8.15000000000000000025, 77.16549999999999999999996);
        Assert.assertEquals(8.15, sut.getX(), TestUtils.DELTA);
        
        sut = new Vector2();
        Assert.assertEquals(0, sut.getX(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector2();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getX(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawY.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getRawY()
     */
    @Test
    public void testGetRawY() throws Exception {
        //standard
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertEquals(77.1654, sut.getRawY(), TestUtils.DELTA);
        
        sut = new Vector2(8.15000000000000000025, 77.16549999999999999999996);
        Assert.assertEquals(77.16549999999999999999996, sut.getRawY(), TestUtils.DELTA);
        
        sut = new Vector2();
        Assert.assertEquals(0, sut.getRawY(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector2();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawY(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getY()
     */
    @Test
    public void testGetY() throws Exception {
        //standard
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertEquals(77.1654, sut.getY(), TestUtils.DELTA);
        
        sut = new Vector2(8.15000000000000000025, 77.16549999999999999999996);
        Assert.assertEquals(77.1655, sut.getY(), TestUtils.DELTA);
        
        sut = new Vector2();
        Assert.assertEquals(0, sut.getY(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector2();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getY(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getRawZ()
     */
    @Test
    public void testGetRawZ() throws Exception {
        //invalid
        sut = new Vector2(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        //invalid
        sut = new Vector2(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawW.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getRawW()
     */
    @Test
    public void testGetRawW() throws Exception {
        //invalid
        sut = new Vector2(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getW()
     */
    @Test
    public void testGetW() throws Exception {
        //invalid
        sut = new Vector2(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new Vector2();
        Assert.assertEquals(2, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertEquals(2, sut.getLength());
        
        sut = new Vector2();
        Assert.assertEquals(2, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new Vector2();
        Assert.assertEquals(Vector2.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new Vector2();
        Assert.assertEquals(Double.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new Vector2();
        Assert.assertTrue(sut.getHandler() instanceof DoubleComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new Vector2();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new Vector2();
        Assert.assertEquals("2D Vector", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new Vector2();
        Assert.assertEquals("2D Vectors", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new Vector2();
        Assert.assertEquals(0.000000000001, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new Vector2();
        Assert.assertFalse(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        Vector component;
        Double[] newComponents;
        
        //standard
        
        component = new Vector2(5.501, 2.67);
        newComponents = new Double[] {5.6, 6.7};
        Assert.assertEquals(2, component.getDimensionality());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Double[] {5.6, 6.7}, component.getRawComponents());
        Assert.assertEquals(2, component.getDimensionality());
        
        //invalid
        
        final Vector component1 = new Vector2(5.501, 2.67);
        final Double[] newComponents1 = new Double[] {5.6, 6.7, 7.8};
        Assert.assertFalse(component1.isResizeable());
        TestUtils.assertException(IndexOutOfBoundsException.class, component1.getErrorHandler().componentLengthNotEqualErrorMessage(newComponents1, component1.getLength()), () ->
                component1.setComponents(newComponents1));
        
        final Vector component2 = new Vector2(5.501, 2.67);
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(1, 3.0);
        Assert.assertArrayEquals(new Double[] {5.77, 3.0}, sut.getRawComponents());
        
        sut = new Vector2();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0}, sut.getRawComponents());
        sut.set(0, 8.5);
        sut.set(1, 1.5);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5}, sut.getRawComponents());
        
        sut = new Vector2(8.500000000001, 1.499999999996);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996}, sut.getRawComponents());
        sut.set(0, 5.769999999996);
        sut.set(1, 3.000000000001);
        Assert.assertArrayEquals(new Double[] {5.769999999996, 3.000000000001}, sut.getRawComponents());
        
        sut = new Vector2(8.5000000000000000000000001, 1.49999999999999999999996);
        Assert.assertArrayEquals(new Double[] {8.5000000000000000000000001, 1.49999999999999999999996}, sut.getRawComponents());
        sut.set(0, 6.5000000000000000000000001);
        sut.set(1, -1.49999999999999999999996);
        Assert.assertArrayEquals(new Double[] {6.5, -1.5}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.set(3, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 5.5));
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#setX(Number)
     */
    @Test
    public void testSetX() throws Exception {
        //standard
        
        sut = new Vector2();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Double[] {5.555, 0.0}, sut.getRawComponents());
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Double[] {5.555, 77.1654}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setX(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#setY(Number)
     */
    @Test
    public void testSetY() throws Exception {
        //standard
        
        sut = new Vector2();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {0.0, 5.555}, sut.getRawComponents());
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 5.555}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setY(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#setZ(Number)
     */
    @Test
    public void testSetZ() throws Exception {
        //standard
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setZ(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#setW(Number)
     */
    @Test
    public void testSetW() throws Exception {
        //standard
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector2(8.15, 77.1654);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setW(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        Vector copy;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        copy = new Vector2();
        Vector2.copy(sut, copy);
        Assert.assertEquals(
                new Vector2(8.5, 1.5), copy);
        
        sut = new Vector2();
        copy = new Vector2();
        Vector2.copy(sut, copy);
        Assert.assertEquals(
                new Vector2(), copy);
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector copy1 = new Vector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                Vector2.copy(sut, copy1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector2.copy(sut, null));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#createInstance()
     * @see Vector2#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.createInstance());
        
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.createInstance(0));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.createInstance(1));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.createInstance(2));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.createInstance(3));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.createInstance(4));
        
        //invalid
        
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#identity()
     * @see Vector2#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        
        Assert.assertEquals(
                new Vector2(1.0, 1.0), Vector2.identity());
        
        Assert.assertEquals(
                new Vector2(1.0, 1.0), Vector2.identity(0));
        Assert.assertEquals(
                new Vector2(1.0, 1.0), Vector2.identity(1));
        Assert.assertEquals(
                new Vector2(1.0, 1.0), Vector2.identity(2));
        Assert.assertEquals(
                new Vector2(1.0, 1.0), Vector2.identity(3));
        Assert.assertEquals(
                new Vector2(1.0, 1.0), Vector2.identity(4));
        
        //invalid
        
        Assert.assertEquals(
                new Vector2(1.0, 1.0), Vector2.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#origin()
     * @see Vector2#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.origin());
        
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.origin(0));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.origin(1));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.origin(2));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.origin(3));
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.origin(4));
        
        //invalid
        
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.origin(-1));
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#squareSum(Vector)
     */
    @Test
    public void testStaticSquareSum() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(74.5, Vector2.squareSum(sut), TestUtils.DELTA);
        
        sut = new Vector2(8.5, -1.5);
        Assert.assertEquals(74.5, Vector2.squareSum(sut), TestUtils.DELTA);
        
        sut = new Vector2(-8.5, -1.5);
        Assert.assertEquals(74.5, Vector2.squareSum(sut), TestUtils.DELTA);
        
        sut = new Vector2();
        Assert.assertEquals(0, Vector2.squareSum(sut), TestUtils.DELTA);
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                Vector2.squareSum(null));
    }
    
    /**
     * JUnit test of squareDifference.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#squareDifference(Vector)
     */
    @Test
    public void testStaticSquareDifference() throws Exception {
        //standard
        
        sut = new Vector2(8.5, 1.5);
        Assert.assertEquals(70.0, Vector2.squareDifference(sut), TestUtils.DELTA);
        
        sut = new Vector2(8.5, -1.5);
        Assert.assertEquals(70.0, Vector2.squareDifference(sut), TestUtils.DELTA);
        
        sut = new Vector2(-8.5, -1.5);
        Assert.assertEquals(70.0, Vector2.squareDifference(sut), TestUtils.DELTA);
        
        sut = new Vector2();
        Assert.assertEquals(0, Vector2.squareDifference(sut), TestUtils.DELTA);
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                Vector2.squareDifference(null));
    }
    
    /**
     * JUnit test of dotFlop.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#dotFlop(Vector, Vector)
     */
    @Test
    public void testStaticDotFlop() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(75.15, 65.85), Vector2.dotFlop(sut, other));
        
        sut = new Vector2(8.5, -1.5);
        other = new Vector2(-9.9, 6);
        Assert.assertEquals(
                new Vector2(-75.15, 65.85), Vector2.dotFlop(sut, other));
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(75.15, -65.85);
        Assert.assertEquals(
                new Vector2(-540.0, 672.45), Vector2.dotFlop(sut, other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(1, 1);
        Assert.assertEquals(
                new Vector2(7.0, 10.0), Vector2.dotFlop(sut, other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(0, 0);
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.dotFlop(sut, other));
        
        sut = new Vector2();
        other = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.dotFlop(sut, other));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(other1, Vector2.DIMENSIONALITY), () ->
                Vector2.dotFlop(sut, other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector2.dotFlop(sut, null));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector2.dotFlop(null, sut));
        
        TestUtils.assertException(NullPointerException.class, () ->
                Vector2.dotFlop(null, null));
    }
    
    /**
     * JUnit test of dotFlopNegative.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#dotFlopNegative(Vector, Vector)
     */
    @Test
    public void testStaticDotFlopNegative() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(9.9, 6);
        Assert.assertEquals(
                new Vector2(93.15, 36.15), Vector2.dotFlopNegative(sut, other));
        
        sut = new Vector2(8.5, -1.5);
        other = new Vector2(93.15, 36.15);
        Assert.assertEquals(
                new Vector2(737.55, 447.0), Vector2.dotFlopNegative(sut, other));
        
        sut = new Vector2(-8.5, 1.5);
        other = new Vector2(93.15, 36.15);
        Assert.assertEquals(
                new Vector2(-737.55, -447.0), Vector2.dotFlopNegative(sut, other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(1, 1);
        Assert.assertEquals(
                new Vector2(10.0, 7.0), Vector2.dotFlopNegative(sut, other));
        
        sut = new Vector2(8.5, 1.5);
        other = new Vector2(0, 0);
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.dotFlopNegative(sut, other));
        
        sut = new Vector2();
        other = new Vector2();
        Assert.assertEquals(
                new Vector2(0.0, 0.0), Vector2.dotFlopNegative(sut, other));
        
        //invalid
        
        sut = new Vector2(8.5, 1.5);
        final Vector other1 = new Vector(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(other1, Vector2.DIMENSIONALITY), () ->
                Vector2.dotFlopNegative(sut, other1));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector2.dotFlopNegative(sut, null));
        
        sut = new Vector2(8.5, 1.5);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector2.dotFlopNegative(null, sut));
        
        TestUtils.assertException(NullPointerException.class, () ->
                Vector2.dotFlopNegative(null, null));
    }
    
}
