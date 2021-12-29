/*
 * File:    Vector3Test.java
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Vector3.
 *
 * @see Vector3
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Vector3.class})
public class Vector3Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Vector3Test.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private Vector3 sut;
    
    
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
     * @see Vector3#DIMENSIONALITY
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(3, Vector3.DIMENSIONALITY);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#Vector3(double, double, double)
     * @see Vector3#Vector3(Vector)
     * @see Vector3#Vector3(Vector2, double)
     * @see Vector3#Vector3()
     */
    @Test
    public void testConstructors() throws Exception {
        //components
        Vector3 vector = new Vector3(0.884, 2, 1.1);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1}, vector.getRawComponents());
        Assert.assertEquals(3, vector.getDimensionality());
        
        //vector
        Vector3 vector2 = new Vector3(new Vector(0.884, 2, 1.1, 8.6, 9.0));
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1}, vector2.getRawComponents());
        Assert.assertEquals(3, vector2.getDimensionality());
        
        //2D vector and component
        Vector3 vector3 = new Vector3(new Vector2(0.884, 2), 1.1);
        Assert.assertArrayEquals(new Double[] {0.884, 2.0, 1.1}, vector3.getRawComponents());
        Assert.assertEquals(3, vector3.getDimensionality());
        
        //empty
        Vector3 vectorDefault = new Vector3();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0}, vectorDefault.getRawComponents());
        Assert.assertEquals(3, vectorDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(vector, vector2);
        Assert.assertEquals(vector2, vector3);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new Vector3(null));
        TestUtils.assertException(NullPointerException.class, () ->
                new Vector3(null, 1.1));
    }
    
    /**
     * JUnit test of vectorString.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#vectorString()
     */
    @Test
    public void testVectorString() throws Exception {
        //standard
        
        sut = new Vector3(1, 2, 3);
        Assert.assertEquals("<1.0, 2.0, 3.0>", sut.vectorString());
        
        sut = new Vector3(1.54877, -2.316487, 77.1654);
        Assert.assertEquals("<1.54877, -2.316487, 77.1654>", sut.vectorString());
        
        sut = new Vector3(new Vector(1.0, 2.0, 3.0, 4.0, 5.0));
        Assert.assertEquals("<1.0, 2.0, 3.0>", sut.vectorString());
        
        //empty
        
        sut = new Vector3();
        Assert.assertEquals("<0.0, 0.0, 0.0>", sut.vectorString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new Vector3(1, 2, 3);
        Assert.assertEquals("<1.0, 2.0, 3.0>", sut.toString());
        
        sut = new Vector3(1.54877, -2.316487, 77.1654);
        Assert.assertEquals("<1.54877, -2.316487, 77.1654>", sut.toString());
        
        sut = new Vector3(new Vector(1.0, 2.0, 3.0, 4.0, 5.0));
        Assert.assertEquals("<1.0, 2.0, 3.0>", sut.toString());
        
        //empty
        
        sut = new Vector3();
        Assert.assertEquals("<0.0, 0.0, 0.0>", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector3(8.5, -1.944, 2.67);
        
        //standard
        
        other = new Vector3(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector3(0, -1.944, 2.67);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector3(8.5, -1.944, 0);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector3(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 8.33);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawVector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1, 3);
        Assert.assertFalse(sut.equals(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new Vector3().equals(new Vector3()));
        
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
     * @see Vector3#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector3(8.5, -1.944, 2.67);
        
        //standard
        
        other = new Vector3(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector3(0, -1.944, 2.67);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector3(8.5, -1.944, 0);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector3(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(8.5, -1.944);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawVector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(8, -1, 3);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        Assert.assertTrue(new Vector3().dimensionalityEqual(new Vector3()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector3(8.5, -1.944, 2.67);
        
        //standard
        
        other = new Vector3(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector3(0, -1.944, 2.67);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector3(8.5, -1.944, 0);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector3(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(8.5, -1.944);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new RawVector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(8, -1, 3);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        Assert.assertTrue(new Vector3().lengthEqual(new Vector3()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new Vector3(8.5, -1.944, 2.67);
        
        //standard
        
        other = new Vector3(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector3(0, -1.944, 2.67);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector3(8.5, -1.944, 0);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector3(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944, 2.67);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.5, -1.944, 8.33);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawVector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigVector(8.5, -1.944, 2.67);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntVector(8, -1, 3);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.5, -1.944, 2.67, 8);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigMatrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new Vector3().componentTypeEqual(new Vector3()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new Vector3(8.1, 6.6, 2.9);
        Vector3 clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotSame(sut, clone);
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new Vector3(8.1, 6.6, 2.9);
        Vector3 emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(Vector3.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotSame(sut, emptyCopy);
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new Vector3();
        
        //standard
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.createNewInstance(0));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.createNewInstance(1));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.createNewInstance(2));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.createNewInstance(3));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        Vector reversed;
        
        //standard
        
        sut = new Vector3(8.5, -1.944, 2.67);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {2.67, -1.944, 8.5}, reversed.getRawComponents());
        
        sut = new Vector3(5.501, 8, 2.67);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {2.67, 8.0, 5.501}, reversed.getRawComponents());
        
        sut = new Vector3(0, 1, 0);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {0.0, 1.0, 0.0}, reversed.getRawComponents());
        
        sut = new Vector3();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(9.9, 6, 0.514);
        Assert.assertEquals(7.258126480022238, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector3(-8.5, 1.5, -5.006);
        other = new Vector3(9.9, -6, 0.514);
        Assert.assertEquals(20.62232770566892, sut.distance(other), TestUtils.DELTA);
        
        sut = new Vector3();
        other = new Vector3();
        Assert.assertEquals(0.0, sut.distance(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(9.2, 3.75, -2.246), sut.midpoint(other));
        
        sut = new Vector3(-8.5, 1.5, -5.006);
        other = new Vector3(9.9, -6, 0.514);
        Assert.assertEquals(
                new Vector3(0.7, -2.25, -2.246), sut.midpoint(other));
        
        sut = new Vector3();
        other = new Vector3();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.midpoint(other));
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#average(List)
     * @see Vector3#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        Vector other1;
        Vector other2;
        Vector other3;
        Vector other4;
        
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other1 = new Vector3(9.9, 6, 0.514);
        other2 = new Vector3(1.8, 4.77, 0.514);
        Assert.assertEquals(
                new Vector3(6.733333333333, 4.09, -1.326), sut.average(other1, other2));
        Assert.assertEquals(
                new Vector3(6.733333333333, 4.09, -1.326), sut.average(Arrays.asList(other1, other2)));
        
        sut = new Vector3(-8.5, 1.5, -5.006);
        other1 = new Vector3(9.9, -6, 0.514);
        other2 = new Vector3(-1.8, 4.77, 0.514);
        Assert.assertEquals(
                new Vector3(-0.133333333333, 0.09, -1.326), sut.average(other1, other2));
        Assert.assertEquals(
                new Vector3(-0.133333333333, 0.09, -1.326), sut.average(Arrays.asList(other1, other2)));
        
        sut = new Vector3();
        other1 = new Vector3();
        other2 = new Vector3();
        other3 = new Vector3();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector otherF11 = new Vector3(9.9, 6, 0.514);
        final Vector otherF12 = new Vector(1.8, 4.77);
        final Vector otherF13 = new Vector3(11.7, 0.447, 7.16);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF12), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF12), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector otherF31 = new Vector3(9.9, 6, 0.514);
        final Vector otherF33 = new Vector3(11.7, 0.447, 7.16);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertEquals(4.994, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector3(8.5, -1.5, -5.006);
        Assert.assertEquals(1.9939999999999998, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector3(-8.5, -1.5, 5.006);
        Assert.assertEquals(-4.994, sut.sum(), TestUtils.DELTA);
        
        sut = new Vector3();
        Assert.assertEquals(0, sut.sum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertEquals(99.560036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector3(8.5, -1.5, -5.006);
        Assert.assertEquals(99.560036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector3(-8.5, -1.5, 5.006);
        Assert.assertEquals(99.560036, sut.squareSum(), TestUtils.DELTA);
        
        sut = new Vector3();
        Assert.assertEquals(0, sut.squareSum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(18.4, 7.5, -4.492), sut.plus(other));
        
        sut = new Vector3(8.5, -1.5, -5.006);
        other = new Vector3(-9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(-1.4, 4.5, -4.492), sut.plus(other));
        
        sut = new Vector3(-8.5, 1.5, -5.006);
        other = new Vector3(9.9, -6, 0.514);
        Assert.assertEquals(
                new Vector3(1.4, -4.5, -4.492), sut.plus(other));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(1, 1, 1);
        Assert.assertEquals(
                new Vector3(9.5, 2.5, -4.006), sut.plus(other));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(0, 0, 0);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), sut.plus(other));
        
        sut = new Vector3();
        other = new Vector3();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.plus(other));
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(-1.4, -4.5, -5.52), sut.minus(other));
        
        sut = new Vector3(8.5, -1.5, -5.006);
        other = new Vector3(-9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(18.4, -7.5, -5.52), sut.minus(other));
        
        sut = new Vector3(-8.5, 1.5, -5.006);
        other = new Vector3(9.9, -6, 0.514);
        Assert.assertEquals(
                new Vector3(-18.4, 7.5, -5.52), sut.minus(other));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(1, 1, 1);
        Assert.assertEquals(
                new Vector3(7.5, 0.5, -6.006), sut.minus(other));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(0, 0, 0);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), sut.minus(other));
        
        sut = new Vector3();
        other = new Vector3();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.minus(other));
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#times(BaseComponent)
     */
    @Test
    public void testTimes() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(84.15, 9.0, -2.573084), sut.times(other));
        
        sut = new Vector3(8.5, -1.5, -5.006);
        other = new Vector3(-9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(-84.15, -9.0, -2.573084), sut.times(other));
        
        sut = new Vector3(-8.5, 1.5, -5.006);
        other = new Vector3(9.9, -6, 0.514);
        Assert.assertEquals(
                new Vector3(-84.15, -9.0, -2.573084), sut.times(other));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(1, 1, 1);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), sut.times(other));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(0, 0, 0);
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.times(other));
        
        sut = new Vector3();
        other = new Vector3();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.times(other));
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times(null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new Vector3(42.5, 7.5, -25.03), sut.scale(5));
        
        sut = new Vector3(8.5, -1.5, -5.006);
        Assert.assertEquals(
                new Vector3(8.5799, -1.5141, -5.0530564), sut.scale(1.0094));
        
        sut = new Vector3(-8.5, 1.5, -5.006);
        Assert.assertEquals(
                new Vector3(-139.57, 24.63, -82.19852), sut.scale(16.42));
        
        sut = new Vector3(-8.5, 1.5, -5.006);
        Assert.assertEquals(
                new Vector3(22.695, -4.005, 13.36602), sut.scale(-2.67));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), sut.scale(1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new Vector3(-8.5, -1.5, 5.006), sut.scale(-1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.scale(0));
        
        sut = new Vector3();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.scale(7.1));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(0.858585858586, 0.25, -9.739299610895), sut.dividedBy(other));
        
        sut = new Vector3(8.5, -1.5, -5.006);
        other = new Vector3(-9.9, 6, 0.514);
        Assert.assertEquals(
                new Vector3(-0.858585858586, -0.25, -9.739299610895), sut.dividedBy(other));
        
        sut = new Vector3(-8.5, 1.5, -5.006);
        other = new Vector3(9.9, -6, 0.514);
        Assert.assertEquals(
                new Vector3(-0.858585858586, -0.25, -9.739299610895), sut.dividedBy(other));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        other = new Vector3(1, 1, 1);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), sut.dividedBy(other));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector other0 = new Vector3(0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new Vector3();
        final Vector other02 = new Vector3();
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other02));
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector other1 = new Vector(9.9, 6);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new Vector3(9.0, 2.0, -5.0), sut.round());
        
        sut = new Vector3(8.56501201650, -1.589075460001, 5.006498505404);
        Assert.assertEquals(
                new Vector3(9.0, -2.0, 5.0), sut.round());
        
        sut = new Vector3();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.round());
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#dot(VectorInterface)
     */
    @Test
    public void testDot() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(31.23598538692, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector3(18.1644, 9.12154, -7.7741);
        other = new Vector3(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(631.6597407041199, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(210.2965501416, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(-210.2965501416, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(0.02102965501416, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector3(1, -1, 3);
        other = new Vector3(3, 3, 0);
        Assert.assertEquals(0.0, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = Vector3.origin();
        Assert.assertEquals(0.0, sut.dot(other), TestUtils.DELTA);
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = Vector3.identity();
        Assert.assertEquals(25.06004, sut.dot(other), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        final Vector other1 = new Vector(1.0481561, 1.655742);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dot(other1));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dot(null));
    }
    
    /**
     * JUnit test of cross.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#cross(Vector)
     */
    @Test
    public void testCross() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(
                new Vector3(-3.98338274304, 0.19263809941, 3.957342192406), sut.cross(other));
        
        sut = new Vector3(18.1644, 9.12154, -7.7741);
        other = new Vector3(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(
                new Vector3(839.59362502136, -656.29594257461, 1191.685822192406), sut.cross(other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.cross(other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.cross(other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.cross(other));
        
        sut = new Vector3(1, -1, 3);
        other = new Vector3(3, 3, 0);
        Assert.assertEquals(
                new Vector3(-9.0, 9.0, 6.0), sut.cross(other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = Vector3.origin();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.cross(other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = Vector3.identity();
        Assert.assertEquals(
                new Vector3(1.34744, -0.3903, -0.95714), sut.cross(other));
        
        //invalid
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        final Vector other1 = new Vector(1.0481561, 1.655742);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(other1, 3), () ->
                sut.cross(other1));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.cross(null));
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(
                new Vector3(0.562999747283, 0.629002096276, 0.536085485198), sut.normalize());
        
        sut = new Vector3(-8.1644, 9.12154, -7.7741);
        Assert.assertEquals(
                new Vector3(-0.562999747283, 0.629002096276, -0.536085485198), sut.normalize());
        
        sut = new Vector3(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(
                new Vector3(-0.562999747283, -0.629002096276, -0.536085485198), sut.normalize());
        
        sut = new Vector3(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(
                new Vector3(0.834684394816, 0.419149935846, -0.357232826503), sut.normalize());
        
        sut = new Vector3(1, -1, 3);
        Assert.assertEquals(
                new Vector3(0.301511344578, -0.301511344578, 0.904534033733), sut.normalize());
        
        sut = Vector3.origin();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), sut.normalize());
        
        sut = Vector3.identity();
        Assert.assertEquals(
                new Vector3(0.57735026919, 0.57735026919, 0.57735026919), sut.normalize());
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(14.501605088458312, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector3(-8.1644, 9.12154, -7.7741);
        Assert.assertEquals(14.501605088458312, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector3(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(14.501605088458312, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector3(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(21.761997843525304, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = new Vector3(1, -1, 3);
        Assert.assertEquals(3.3166247903554, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = Vector3.origin();
        Assert.assertEquals(0.0, sut.hypotenuse(), TestUtils.DELTA);
        
        sut = Vector3.identity();
        Assert.assertEquals(1.7320508075688772, sut.hypotenuse(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        Vector copy;
        
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        copy = new Vector3();
        sut.copy(copy);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), copy);
        
        sut = new Vector3();
        copy = new Vector3();
        sut.copy(copy);
        Assert.assertEquals(
                new Vector3(), copy);
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector copy1 = new Vector(4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#copyMeta(Component)
     */
    @Test
    public void testCopyMeta() throws Exception {
        Vector component1 = new Vector3(8.1, 6.6, 7.0);
        Assert.assertEquals(3, component1.getDimensionality());
        Assert.assertArrayEquals(new Double[] {8.1, 6.6, 7.0}, component1.getRawComponents());
        
        Vector component2 = new Vector3(9.1, 6.3, 1.7);
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        Assert.assertArrayEquals(new Double[] {9.1, 6.3, 1.7}, component2.getRawComponents());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new Vector3(1.0, 2.0, 3.0);
        Assert.assertFalse(sut.isResizeable());
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        sut.redim(4);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), sut);
        
        sut = new Vector3(8.5, 1.5, -5.006);
        sut.redim(1);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), sut);
        
        sut = new Vector3(8.5, 1.5, -5.006);
        sut.redim(0);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), sut);
        
        sut = new Vector3(8.5, 1.5, -5.006);
        sut.redim(-1);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), sut);
    }
    
    /**
     * JUnit test of subVector3.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#subVector(int, int)
     * @see Vector3#subVector(int)
     */
    @Test
    public void testSubVector() throws Exception {
        //standard
        
        Vector subVector;
        sut = new Vector3(1.0, 2.0, 3.0);
        Assert.assertFalse(sut.isResizeable());
        
        subVector = sut.subVector(0, 3);
        Assert.assertEquals(
                new Vector3(1.0, 2.0, 3.0), subVector);
        
        //invalid
        
        sut = new Vector3(1.0, 2.0, 3.0);
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Vector3.DIMENSIONALITY), () ->
                sut.subVector(0, 0));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(sut, Vector3.DIMENSIONALITY), () ->
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
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 15, 3), () ->
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
     * @see Vector3#dimensionalityToLength(int)
     * @see Vector3#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new Vector3(8.5, 1.5, -5.006);
        
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
     * @see Vector3#lengthToDimensionality(int)
     * @see Vector3#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new Vector3(8.5, 1.5, -5.006);
        
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
     * @see Vector3#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new Vector3(8.5, 1.5, -5.006);
        
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
     * @see Vector3#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        Vector component;
        
        //standard
        component = new Vector3(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054);
        Assert.assertArrayEquals(new Double[] {8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054}, component.getRawComponents());
        Assert.assertArrayEquals(new Double[] {8.160456540859, 6.649084984108, 7.048089710591}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        Vector component;
        
        //standard
        
        component = new Vector3(8.5, 1.5, -5.006);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006}, component.getComponents());
        
        component = new Vector3();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0}, component.getComponents());
        
        component = new Vector3(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999}, component.getComponents());
        
        component = new Vector3(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        Vector component;
        
        //standard
        component = new Vector3(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054);
        Assert.assertArrayEquals(new double[] {8.160456540859, 6.649084984108, 7.04808971059084054054}, component.getPrimitiveComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new Double[] {8.160456540859, 6.649084984108, 7.048089710591}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getRaw(int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertEquals(8.5, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.getRaw(1), TestUtils.DELTA);
        
        sut = new Vector3(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(8.500000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.getRaw(1), TestUtils.DELTA);
        
        sut = new Vector3(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0), TestUtils.DELTA);
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
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
     * @see Vector3#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        
        sut = new Vector3(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(8.500000000001, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.499999999996, sut.get(1), TestUtils.DELTA);
        
        sut = new Vector3(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(8.5, sut.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.5, sut.get(1), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
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
     * @see Vector3#getRawX()
     */
    @Test
    public void testGetRawX() throws Exception {
        //standard
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertEquals(8.15, sut.getRawX(), TestUtils.DELTA);
        
        sut = new Vector3(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001);
        Assert.assertEquals(8.15000000000000000025, sut.getRawX(), TestUtils.DELTA);
        
        sut = new Vector3();
        Assert.assertEquals(0, sut.getRawX(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawX(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getX()
     */
    @Test
    public void testGetX() throws Exception {
        //standard
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertEquals(8.15, sut.getX(), TestUtils.DELTA);
        
        sut = new Vector3(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001);
        Assert.assertEquals(8.15, sut.getX(), TestUtils.DELTA);
        
        sut = new Vector3();
        Assert.assertEquals(0, sut.getX(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getX(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawY.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getRawY()
     */
    @Test
    public void testGetRawY() throws Exception {
        //standard
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertEquals(77.1654, sut.getRawY(), TestUtils.DELTA);
        
        sut = new Vector3(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001);
        Assert.assertEquals(77.16549999999999999999996, sut.getRawY(), TestUtils.DELTA);
        
        sut = new Vector3();
        Assert.assertEquals(0, sut.getRawY(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawY(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getY()
     */
    @Test
    public void testGetY() throws Exception {
        //standard
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertEquals(77.1654, sut.getY(), TestUtils.DELTA);
        
        sut = new Vector3(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001);
        Assert.assertEquals(77.1655, sut.getY(), TestUtils.DELTA);
        
        sut = new Vector3();
        Assert.assertEquals(0, sut.getY(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getY(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getRawZ()
     */
    @Test
    public void testGetRawZ() throws Exception {
        //standard
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertEquals(0.79455, sut.getRawZ(), TestUtils.DELTA);
        
        sut = new Vector3(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001);
        Assert.assertEquals(0.794550000000000000000001, sut.getRawZ(), TestUtils.DELTA);
        
        sut = new Vector3();
        Assert.assertEquals(0, sut.getRawZ(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        //standard
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertEquals(0.79455, sut.getZ(), TestUtils.DELTA);
        
        sut = new Vector3(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001);
        Assert.assertEquals(0.79455, sut.getZ(), TestUtils.DELTA);
        
        sut = new Vector3();
        Assert.assertEquals(0, sut.getZ(), TestUtils.DELTA);
        
        //invalid
        
        sut = new Vector3();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getRawW.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getRawW()
     */
    @Test
    public void testGetRawW() throws Exception {
        //invalid
        sut = new Vector3(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getW()
     */
    @Test
    public void testGetW() throws Exception {
        //invalid
        sut = new Vector3(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW(), TestUtils.DELTA));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertEquals(3, sut.getDimensionality());
        
        sut = new Vector3();
        Assert.assertEquals(3, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertEquals(3, sut.getLength());
        
        sut = new Vector3();
        Assert.assertEquals(3, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new Vector3();
        Assert.assertEquals(Vector3.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new Vector3();
        Assert.assertEquals(Double.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new Vector3();
        Assert.assertTrue(sut.getHandler() instanceof DoubleComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new Vector3();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new Vector3();
        Assert.assertEquals("3D Vector", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new Vector3();
        Assert.assertEquals("3D Vectors", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new Vector3();
        Assert.assertEquals(1E-12, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new Vector3();
        Assert.assertFalse(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        Vector component;
        Double[] newComponents;
        
        //standard
        
        component = new Vector3(5.501, 2.67, -1.944);
        newComponents = new Double[] {5.6, 6.7, 1.1};
        Assert.assertEquals(3, component.getDimensionality());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Double[] {5.6, 6.7, 1.1}, component.getRawComponents());
        Assert.assertEquals(3, component.getDimensionality());
        
        //invalid
        
        final Vector component1 = new Vector3(5.501, 2.67, -1.944);
        final Double[] newComponents1 = new Double[] {5.6, 6.7};
        Assert.assertFalse(component1.isResizeable());
        TestUtils.assertException(IndexOutOfBoundsException.class, component1.getErrorHandler().componentLengthNotEqualErrorMessage(newComponents1, component1.getLength()), () ->
                component1.setComponents(newComponents1));
        
        final Vector component2 = new Vector3(5.501, 2.67, -1.944);
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006}, sut.getRawComponents());
        sut.set(0, 5.77);
        sut.set(1, 3.0);
        sut.set(2, 0.997);
        Assert.assertArrayEquals(new Double[] {5.77, 3.0, 0.997}, sut.getRawComponents());
        
        sut = new Vector3();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.set(0, 8.5);
        sut.set(1, 1.5);
        sut.set(2, -5.006);
        Assert.assertArrayEquals(new Double[] {8.5, 1.5, -5.006}, sut.getRawComponents());
        
        sut = new Vector3(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertArrayEquals(new Double[] {8.500000000001, 1.499999999996, -5.005999999999}, sut.getRawComponents());
        sut.set(0, 5.769999999996);
        sut.set(1, 3.000000000001);
        sut.set(2, 8.110000000001);
        Assert.assertArrayEquals(new Double[] {5.769999999996, 3.000000000001, 8.110000000001}, sut.getRawComponents());
        
        sut = new Vector3(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertArrayEquals(new Double[] {8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999}, sut.getRawComponents());
        sut.set(0, 6.5000000000000000000000001);
        sut.set(1, -1.49999999999999999999996);
        sut.set(2, 3.0059999999999999999999);
        Assert.assertArrayEquals(new Double[] {6.5, -1.5, 3.006}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
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
     * @see Vector3#setX(Number)
     */
    @Test
    public void testSetX() throws Exception {
        //standard
        
        sut = new Vector3();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Double[] {5.555, 0.0, 0.0}, sut.getRawComponents());
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        sut.setX(5.555);
        Assert.assertArrayEquals(new Double[] {5.555, 77.1654, 0.79455}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setX(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#setY(Number)
     */
    @Test
    public void testSetY() throws Exception {
        //standard
        
        sut = new Vector3();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {0.0, 5.555, 0.0}, sut.getRawComponents());
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        sut.setY(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 5.555, 0.79455}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setY(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#setZ(Number)
     */
    @Test
    public void testSetZ() throws Exception {
        //standard
        
        sut = new Vector3();
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 0.0}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {0.0, 0.0, 5.555}, sut.getRawComponents());
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        sut.setZ(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 5.555}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setZ(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#setW(Number)
     */
    @Test
    public void testSetW() throws Exception {
        //standard
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        sut.setW(5.555);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        
        //invalid
        
        sut = new Vector3(8.15, 77.1654, 0.79455);
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setW(null));
        Assert.assertArrayEquals(new Double[] {8.15, 77.1654, 0.79455}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        Vector copy;
        
        //standard
        
        sut = new Vector3(8.5, 1.5, -5.006);
        copy = new Vector3();
        Vector3.copy(sut, copy);
        Assert.assertEquals(
                new Vector3(8.5, 1.5, -5.006), copy);
        
        sut = new Vector3();
        copy = new Vector3();
        Vector3.copy(sut, copy);
        Assert.assertEquals(
                new Vector3(), copy);
        
        //invalid
        
        sut = new Vector3(8.5, 1.5, -5.006);
        final Vector copy1 = new Vector(4);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                Vector3.copy(sut, copy1));
        
        sut = new Vector3(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector3.copy(sut, null));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#createInstance()
     * @see Vector3#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.createInstance());
        
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.createInstance(0));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.createInstance(1));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.createInstance(2));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.createInstance(3));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.createInstance(4));
        
        //invalid
        
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#identity()
     * @see Vector3#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        
        Assert.assertEquals(
                new Vector3(1.0, 1.0, 1.0), Vector3.identity());
        
        Assert.assertEquals(
                new Vector3(1.0, 1.0, 1.0), Vector3.identity(0));
        Assert.assertEquals(
                new Vector3(1.0, 1.0, 1.0), Vector3.identity(1));
        Assert.assertEquals(
                new Vector3(1.0, 1.0, 1.0), Vector3.identity(2));
        Assert.assertEquals(
                new Vector3(1.0, 1.0, 1.0), Vector3.identity(3));
        Assert.assertEquals(
                new Vector3(1.0, 1.0, 1.0), Vector3.identity(4));
        
        //invalid
        
        Assert.assertEquals(
                new Vector3(1.0, 1.0, 1.0), Vector3.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#origin()
     * @see Vector3#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.origin());
        
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.origin(0));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.origin(1));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.origin(2));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.origin(3));
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.origin(4));
        
        //invalid
        
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.origin(-1));
    }
    
    /**
     * JUnit test of cross.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#cross(Vector, Vector)
     */
    @Test
    public void testStaticCross() throws Exception {
        Vector other;
        
        //standard
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(
                new Vector3(-3.98338274304, 0.19263809941, 3.957342192406), Vector3.cross(sut, other));
        
        sut = new Vector3(18.1644, 9.12154, -7.7741);
        other = new Vector3(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(
                new Vector3(839.59362502136, -656.29594257461, 1191.685822192406), Vector3.cross(sut, other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.cross(sut, other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.cross(sut, other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = new Vector3(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.cross(sut, other));
        
        sut = new Vector3(1, -1, 3);
        other = new Vector3(3, 3, 0);
        Assert.assertEquals(
                new Vector3(-9.0, 9.0, 6.0), Vector3.cross(sut, other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = Vector3.origin();
        Assert.assertEquals(
                new Vector3(0.0, 0.0, 0.0), Vector3.cross(sut, other));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        other = Vector3.identity();
        Assert.assertEquals(
                new Vector3(1.34744, -0.3903, -0.95714), Vector3.cross(sut, other));
        
        //invalid
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        final Vector other1 = new Vector(1.0481561, 1.655742);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(other1, 3), () ->
                Vector3.cross(sut, other1));
        
        sut = new Vector3(8.1644, 9.12154, 7.7741);
        TestUtils.assertException(NullPointerException.class, () ->
                Vector3.cross(sut, null));
    }
    
}
