/*
 * File:    VectorTest.java
 * Package: commons.math.vector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import commons.list.ArrayUtility;
import commons.list.ListUtility;
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
 * JUnit test of Vector.
 *
 * @see Vector
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Vector.class})
public class VectorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(VectorTest.class);
    
    
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
     * @see Vector#PRECISION
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(0.000000000000001, Vector.PRECISION, TestUtils.DELTA);
        Assert.assertEquals(new Vector(1, 1, 1), Whitebox.getInternalState(Vector.class, "JUSTIFICATION_VECTOR"));
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see Vector#Vector(double...)
     * @see Vector#Vector(List)
     * @see Vector#Vector(Vector)
     * @see Vector#Vector(Vector, double...)
     */
    @Test
    public void testConstructor() throws Exception {
        //components
        Vector vector = new Vector(0.884, 2, 1.1);
        Assert.assertArrayEquals(new double[] {0.884, 2, 1.1}, vector.components, TestUtils.DELTA);
        
        //list of components
        List<Double> values = Arrays.asList(0.884, 2.0, 1.1);
        Vector vector2 = new Vector(values);
        Assert.assertArrayEquals(new double[] {0.884, 2, 1.1}, vector2.components, TestUtils.DELTA);
        
        //vector
        Vector vector3 = new Vector(vector);
        Assert.assertArrayEquals(new double[] {0.884, 2, 1.1}, vector3.components, TestUtils.DELTA);
        
        //vector and component
        Vector vector4 = new Vector(new Vector(0.884, 2), 1.1);
        Assert.assertArrayEquals(new double[] {0.884, 2, 1.1}, vector4.components, TestUtils.DELTA);
        
        //vector and components
        Vector vector5 = new Vector(new Vector(0.884), 2, 1.1);
        Assert.assertArrayEquals(new double[] {0.884, 2, 1.1}, vector5.components, TestUtils.DELTA);
        
        //equality
        Assert.assertEquals(vector, vector2);
        Assert.assertEquals(vector2, vector3);
        Assert.assertEquals(vector3, vector4);
        Assert.assertEquals(vector4, vector5);
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see Vector#toString()
     */
    @Test
    public void testToString() throws Exception {
        Assert.assertEquals("<8.0, 9.0, 1.0>", new Vector(8, 9, 1).toString());
        Assert.assertEquals("<-8.0, -9.0, -1.0>", new Vector(-8, -9, -1).toString());
        Assert.assertEquals("<0.0, 0.0, 0.0>", new Vector(0, 0, 0).toString());
        Assert.assertEquals("<-1.87, 88.06, -7.4>", new Vector(-1.87, 88.06, -7.4).toString());
        Assert.assertEquals("<1.8187612301, -8.1787546309, 7.4589760359>", new Vector(1.8187612301, -8.1787546309, 7.4589760359).toString());
        Assert.assertEquals("<1.8187612301079148, 8.178754630997467, 7.458976035987448>", new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).toString());
        Assert.assertEquals("<8.4E-10, 2.774E-15, 1.0E-12>", new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).toString());
        Assert.assertEquals("<8.0, 1.0, 1.0, -6.0, 3.0, 4.0, 7.0, 0.0, -6.0, 0.0, 44.0, 9.0, -1.0, -5.0, 8.0, 7.0, 0.0, 3.0>", new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).toString());
        Assert.assertEquals("<3.0, 5.0>", new Vector(3, 5).toString());
        Assert.assertEquals("<1.87>", new Vector(1.87).toString());
        Assert.assertEquals("<0.0>", new Vector(0).toString());
        Assert.assertEquals("<>", new Vector().toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see Vector#equals(Object)
     */
    @SuppressWarnings({"SimplifiableJUnitAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        //standard
        Assert.assertTrue(new Vector(8).equals(new Vector(8)));
        Assert.assertTrue(new Vector(8).equals(new Vector(8.0)));
        Assert.assertTrue(new Vector(8).equals(new Vector(8.00000)));
        Assert.assertTrue(new Vector(1, 2, 3).equals(new Vector(1, 2, 3)));
        Assert.assertTrue(new Vector(1, 2, -3, 4, -5, -6, 7, 8, 9, 10).equals(new Vector(1, 2, -3, 4, -5, -6, 7, 8, 9, 10)));
        Assert.assertTrue(new Vector().equals(new Vector()));
        
        //precision
        Assert.assertFalse(new Vector(1).equals(new Vector(1.1)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.01)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.0001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.00001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.000001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.0000001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.00000001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.000000001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.0000000001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.00000000001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.000000000001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.0000000000001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.00000000000001)));
        Assert.assertFalse(new Vector(1).equals(new Vector(1.000000000000001)));
        Assert.assertTrue(new Vector(1).equals(new Vector(1.0000000000000001)));
        
        //invalid
        Assert.assertFalse(new Vector(1, 1, 0).equals(new Vector(1, 1)));
        Assert.assertFalse(new Vector(1, 1).equals(new Vector(1)));
        Assert.assertFalse(new Vector(1).equals(new Vector()));
        Assert.assertFalse(new Vector(1).equals(1.0));
        Assert.assertFalse(new Vector(1).equals(1));
        Assert.assertFalse(new Vector(1).equals("test"));
        Assert.assertFalse(new Vector(1).equals(null));
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see Vector#dimensionalityEqual(Vector)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        //equal
        Assert.assertTrue(new Vector(8).dimensionalityEqual(new Vector(8)));
        Assert.assertTrue(new Vector(8).dimensionalityEqual(new Vector(8.0)));
        Assert.assertTrue(new Vector(8).dimensionalityEqual(new Vector(8.00000)));
        Assert.assertTrue(new Vector(6).dimensionalityEqual(new Vector(1)));
        Assert.assertTrue(new Vector(6).dimensionalityEqual(new Vector(1.844156187)));
        Assert.assertTrue(new Vector(6).dimensionalityEqual(new Vector(0)));
        Assert.assertTrue(new Vector(6, 5).dimensionalityEqual(new Vector(1, 2)));
        Assert.assertTrue(new Vector(6, 5, 4).dimensionalityEqual(new Vector(1, 2, 3)));
        Assert.assertTrue(new Vector(6.489, -5.01547, 4.3367).dimensionalityEqual(new Vector(1.04001, 2.754, 3.4169)));
        Assert.assertTrue(new Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).dimensionalityEqual(new Vector(11, 12, 13, 14, 15, 16, 17, 18, 19, 20)));
        Assert.assertTrue(new Vector().dimensionalityEqual(new Vector()));
        
        //not equal
        Assert.assertFalse(new Vector(5).dimensionalityEqual(new Vector()));
        Assert.assertFalse(new Vector(5).dimensionalityEqual(new Vector(8.01, -9)));
        Assert.assertFalse(new Vector(5).dimensionalityEqual(new Vector(8.01, -9, 4.7)));
        Assert.assertFalse(new Vector(5).dimensionalityEqual(new Vector(8.01, -9, 4.7, 910)));
        Assert.assertFalse(new Vector(5, 5).dimensionalityEqual(new Vector()));
        Assert.assertFalse(new Vector(5, 5).dimensionalityEqual(new Vector(8)));
        Assert.assertFalse(new Vector(5, 5).dimensionalityEqual(new Vector(8.01, -9, 4.7)));
        Assert.assertFalse(new Vector(5, 5).dimensionalityEqual(new Vector(8.01, -9, 4.7, 910)));
        Assert.assertFalse(new Vector(5, 5, 9.1).dimensionalityEqual(new Vector()));
        Assert.assertFalse(new Vector(5, 5, 9.1).dimensionalityEqual(new Vector(8)));
        Assert.assertFalse(new Vector(5, 5, 9.1).dimensionalityEqual(new Vector(8.01, -9)));
        Assert.assertFalse(new Vector(5, 5, 9.1).dimensionalityEqual(new Vector(8.01, -9, 4.7, 910)));
        
        //invalid
        Assert.assertFalse(new Vector(6).dimensionalityEqual(null));
        Assert.assertFalse(new Vector(6, 5, 4).dimensionalityEqual(null));
        Assert.assertFalse(new Vector().dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see Vector#clone()
     */
    @Test
    public void testClone() throws Exception {
        Vector vector;
        Vector clone;
        
        //standard
        
        vector = new Vector(1, 2, 3);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components, TestUtils.DELTA);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new Vector(8);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components, TestUtils.DELTA);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new Vector(1, 2.1897642106, -3);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components, TestUtils.DELTA);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new Vector(8.4675112, 10.1084423, 0.1455741);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components, TestUtils.DELTA);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new Vector(1, 2, -3, 4, 5, 6, -7, 8, -9, 10);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components, TestUtils.DELTA);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new Vector();
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components, TestUtils.DELTA);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        //independence
        
        vector = new Vector(1, 2, 3);
        clone = vector.clone();
        clone.setX(4);
        vector.setY(5);
        Assert.assertEquals(1, vector.getX(), TestUtils.DELTA);
        Assert.assertEquals(4, clone.getX(), TestUtils.DELTA);
        Assert.assertEquals(5, vector.getY(), TestUtils.DELTA);
        Assert.assertEquals(2, clone.getY(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see Vector#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        Vector vector;
        Vector reverse;
        
        //standard
        
        vector = new Vector(1, 2, 3);
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new double[] {1, 2, 3}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {3, 2, 1}, reverse.components, TestUtils.DELTA);
        
        vector = new Vector(8);
        reverse = vector.reverse();
        Assert.assertEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new double[] {8}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {8}, reverse.components, TestUtils.DELTA);
        
        vector = new Vector(1, 2.1897642106, -3);
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new double[] {1, 2.1897642106, -3}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-3, 2.1897642106, 1}, reverse.components, TestUtils.DELTA);
        
        vector = new Vector(8.4675112, -10.1084423, 0.1455741);
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new double[] {8.4675112, -10.1084423, 0.1455741}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0.1455741, -10.1084423, 8.4675112}, reverse.components, TestUtils.DELTA);
        
        vector = new Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, reverse.components, TestUtils.DELTA);
        
        vector = new Vector();
        reverse = vector.reverse();
        Assert.assertEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new double[] {}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {}, reverse.components, TestUtils.DELTA);
        
        //independence
        
        vector = new Vector(1, 2, 3);
        reverse = vector.reverse();
        reverse.setX(4);
        vector.setY(5);
        Assert.assertEquals(1, vector.getX(), TestUtils.DELTA);
        Assert.assertEquals(4, reverse.getX(), TestUtils.DELTA);
        Assert.assertEquals(5, vector.getY(), TestUtils.DELTA);
        Assert.assertEquals(2, reverse.getY(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of justify.
     *
     * @throws Exception When there is an exception.
     * @see Vector#justify()
     */
    @Test
    public void testJustify() throws Exception {
        Vector vector;
        Vector justified;
        
        //standard
        
        vector = new Vector(1, 2, 3);
        justified = vector.justify();
        Assert.assertEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new double[] {1, 2, 3}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {1, 2, 3}, justified.components, TestUtils.DELTA);
        
        vector = new Vector(1, 2.1897642106, -3);
        justified = vector.justify();
        Assert.assertEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new double[] {1, 2.1897642106, -3}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {1, 2.1897642106, -3}, justified.components, TestUtils.DELTA);
        
        vector = new Vector(8.4675112, -10.1084423, 0.1455741);
        justified = vector.justify();
        Assert.assertEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new double[] {8.4675112, -10.1084423, 0.1455741}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {8.4675112, -10.1084423, 0.1455741}, justified.components, TestUtils.DELTA);
        
        vector = new Vector(1, 2, 3);
        justified = vector.justify();
        justified.setX(4);
        vector.setY(5);
        Assert.assertEquals(1, vector.getX(), TestUtils.DELTA);
        Assert.assertEquals(4, justified.getX(), TestUtils.DELTA);
        Assert.assertEquals(5, vector.getY(), TestUtils.DELTA);
        Assert.assertEquals(2, justified.getY(), TestUtils.DELTA);
        
        //justification
        
        Vector.setJustificationVector(new Vector(4, -2, -3));
        
        vector = new Vector(1, 2, 3);
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new double[] {1, 2, 3}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {4, -4, -9}, justified.components, TestUtils.DELTA);
        
        vector = new Vector(1, 2.1897642106, -3);
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new double[] {1, 2.1897642106, -3}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {4, -4.3795284212, 9}, justified.components, TestUtils.DELTA);
        
        vector = new Vector(8.4675112, -10.1084423, 0.1455741);
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new double[] {8.4675112, -10.1084423, 0.1455741}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {33.8700448, 20.2168846, -0.4367223}, justified.components, TestUtils.DELTA);
        
        vector = new Vector(1, 2, 3);
        justified = vector.justify();
        justified.setX(4);
        vector.setY(5);
        Assert.assertEquals(1, vector.getX(), TestUtils.DELTA);
        Assert.assertEquals(4, justified.getX(), TestUtils.DELTA);
        Assert.assertEquals(5, vector.getY(), TestUtils.DELTA);
        Assert.assertEquals(-4, justified.getY(), TestUtils.DELTA);
        
        Vector.setJustificationVector(new Vector(4, -2));
        
        vector = new Vector(1, 2);
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new double[] {1, 2}, vector.components, TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {4, -4}, justified.components, TestUtils.DELTA);
        
        Vector.setJustificationVector(new Vector(1, 1, 1));
        
        //invalid
        
        try {
            vector = new Vector(8);
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            vector = new Vector(8, 8);
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            vector = new Vector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            vector = new Vector();
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        Vector.setJustificationVector(new Vector(4, -2));
        
        try {
            vector = new Vector(8, 8);
            vector.justify();
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
            vector = new Vector(1, 2, 3);
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        Vector.setJustificationVector(new Vector(1, 1, 1));
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see Vector#distance(Vector)
     */
    @Test
    public void testDistance() throws Exception {
        //origin
        Assert.assertEquals(12.083045973594572, new Vector(8, 9, 1).distance(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(12.083045973594572, new Vector(-8, -9, -1).distance(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0, 0, 0).distance(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(88.39016065151144, new Vector(-1.87, 88.06, -7.4).distance(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(11.217675482412279, new Vector(1.8187612301, -8.1787546309, 7.4589760359).distance(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(11.217675482542772, new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).distance(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(8.400005952424647E-10, new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).distance(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(48.75448697299562, new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).distance(Vector.origin(18)), TestUtils.DELTA);
        Assert.assertEquals(5.830951894845301, new Vector(3, 5).distance(Vector.origin(2)), TestUtils.DELTA);
        Assert.assertEquals(1.87, new Vector(1.87).distance(Vector.origin(1)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0).distance(Vector.origin(1)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector().distance(Vector.origin(0)), TestUtils.DELTA);
        
        //standard
        Assert.assertEquals(0, new Vector(8, 9, 1).distance(new Vector(8, 9, 1)), TestUtils.DELTA);
        Assert.assertEquals(20.024984394500787, new Vector(-8, -9, -1).distance(new Vector(5, 5, 5)), TestUtils.DELTA);
        Assert.assertEquals(13.416407864998739, new Vector(0, 0, 0).distance(new Vector(10, 8, 4)), TestUtils.DELTA);
        Assert.assertEquals(89.44778655339253, new Vector(-1.87, 88.06, -7.4).distance(new Vector(6.54741, 1.254487, 12.471)), TestUtils.DELTA);
        Assert.assertEquals(16.40762064192866, new Vector(1.8187612301, -8.1787546309, 7.4589760359).distance(new Vector(9, 5.16474, 1.16684)), TestUtils.DELTA);
        Assert.assertEquals(10.012264996564955, new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).distance(new Vector(9, 5.16474, 1.16684)), TestUtils.DELTA);
        Assert.assertEquals(92.63908462354924, new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).distance(new Vector(66, 65, 1)), TestUtils.DELTA);
        Assert.assertEquals(54.466600312485994, new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).distance(new Vector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)), TestUtils.DELTA);
        Assert.assertEquals(4.47213595499958, new Vector(3, 5).distance(new Vector(1, 1)), TestUtils.DELTA);
        Assert.assertEquals(0.13, new Vector(1.87).distance(new Vector(2)), TestUtils.DELTA);
        Assert.assertEquals(10, new Vector(0).distance(new Vector(10)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector().distance(new Vector()), TestUtils.DELTA);
        
        //invalid
        
        try {
            new Vector(8, 9, 1).distance(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector(8).distance(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().distance(new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().distance(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see Vector#midpoint(Vector)
     */
    @Test
    public void testMidpoint() throws Exception {
        //origin
        Assert.assertEquals(new Vector(5.333333333333333, 6, 0.6666666666666666), new Vector(8, 9, 1).midpoint(Vector.origin(3)));
        Assert.assertEquals(new Vector(-5.333333333333333, -6, -0.6666666666666666), new Vector(-8, -9, -1).midpoint(Vector.origin(3)));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).midpoint(Vector.origin(3)));
        Assert.assertEquals(new Vector(-1.2466666666666668, 58.70666666666667, -4.933333333333334), new Vector(-1.87, 88.06, -7.4).midpoint(Vector.origin(3)));
        Assert.assertEquals(new Vector(1.2125074867333334, -5.452503087266667, 4.9726506906), new Vector(1.8187612301, -8.1787546309, 7.4589760359).midpoint(Vector.origin(3)));
        Assert.assertEquals(new Vector(1.2125074867386099, 5.452503087331645, 4.972650690658299), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).midpoint(Vector.origin(3)));
        Assert.assertEquals(new Vector(5.6E-10, 1.8493333333333335E-15, 6.666666666666667E-13), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).midpoint(Vector.origin(3)));
        Assert.assertEquals(new Vector(5.333333333333333, 0.6666666666666666, 0.6666666666666666, -4, 2, 2.6666666666666665, 4.666666666666667, 0, -4, 0, 29.333333333333332, 6, -0.6666666666666666, -3.3333333333333335, 5.333333333333333, 4.666666666666667, 0, 2), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).midpoint(Vector.origin(18)));
        Assert.assertEquals(new Vector(2, 3.3333333333333335), new Vector(3, 5).midpoint(Vector.origin(2)));
        Assert.assertEquals(new Vector(1.2466666666666668), new Vector(1.87).midpoint(Vector.origin(1)));
        Assert.assertEquals(new Vector(0), new Vector(0).midpoint(Vector.origin(1)));
        Assert.assertEquals(new Vector(), new Vector().midpoint(Vector.origin(0)));
        
        //standard
        Assert.assertEquals(new Vector(8, 9, 1), new Vector(8, 9, 1).midpoint(new Vector(8, 9, 1)));
        Assert.assertEquals(new Vector(-3.6666666666666665, -4.333333333333333, 1), new Vector(-8, -9, -1).midpoint(new Vector(5, 5, 5)));
        Assert.assertEquals(new Vector(3.3333333333333335, 2.6666666666666665, 1.3333333333333333), new Vector(0, 0, 0).midpoint(new Vector(10, 8, 4)));
        Assert.assertEquals(new Vector(0.9358033333333333, 59.124829000000005, -0.7763333333333335), new Vector(-1.87, 88.06, -7.4).midpoint(new Vector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new Vector(4.212507486733333, -3.730923087266667, 5.361597357266667), new Vector(1.8187612301, -8.1787546309, 7.4589760359).midpoint(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(4.21250748673861, 7.1740830873316455, 5.361597357324965), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).midpoint(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(22.000000000559996, 21.666666666666668, 0.333333333334), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).midpoint(new Vector(66, 65, 1)));
        Assert.assertEquals(new Vector(8, 1, 1, -4, 4.233333333333333, 5, 5.666666666666667, 11, -3.5066633333333335, 2, 31.666666666666668, 7.333333333333333, -0.4666666666666666, -1, 5.666666666666667, 4.666666666666667, 0.9666666666666667, 5.666666666666667), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).midpoint(new Vector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new Vector(2.3333333333333335, 3.6666666666666665), new Vector(3, 5).midpoint(new Vector(1, 1)));
        Assert.assertEquals(new Vector(1.9133333333333333), new Vector(1.87).midpoint(new Vector(2)));
        Assert.assertEquals(new Vector(3.3333333333333335), new Vector(0).midpoint(new Vector(10)));
        Assert.assertEquals(new Vector(), new Vector().midpoint(new Vector()));
        
        //invalid
        
        try {
            new Vector(8, 9, 1).midpoint(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector(8).midpoint(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().midpoint(new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().midpoint(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see Vector#average(List)
     * @see Vector#average(Vector...)
     * @see #testAverageList()
     * @see #testAverageArray()
     */
    @Test
    public void testAverage() throws Exception {
        Vector[] vectorSet3 = new Vector[] {new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)};
        Vector[] vectorSet2 = new Vector[] {new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)};
        Vector[] vectorSet1 = new Vector[] {new Vector(8), new Vector(0.1), new Vector(-5.6)};
        Vector[] vectorSet18 = new Vector[] {new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<Vector> vectorList3 = Arrays.asList(vectorSet3);
        List<Vector> vectorList2 = Arrays.asList(vectorSet2);
        List<Vector> vectorList1 = Arrays.asList(vectorSet1);
        List<Vector> vectorList18 = Arrays.asList(vectorSet18);
        
        //list
        testAverageList();
        
        //array
        testAverageArray();
        
        //invalid
        
        try {
            new Vector(8, 9, 1).average(vectorList2);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector(8).average(vectorSet3);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().average(new Vector(8), new Vector(0.1), new Vector(5.6));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().average(Collections.singletonList(null));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().average(null, null, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * Helper method for JUnit test of average for list cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testAverageList() throws Exception {
        Vector[] vectorSet3 = new Vector[] {new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)};
        Vector[] vectorSet2 = new Vector[] {new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)};
        Vector[] vectorSet1 = new Vector[] {new Vector(8), new Vector(0.1), new Vector(-5.6)};
        Vector[] vectorSet18 = new Vector[] {new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<Vector> vectorList3 = Arrays.asList(vectorSet3);
        List<Vector> vectorList2 = Arrays.asList(vectorSet2);
        List<Vector> vectorList1 = Arrays.asList(vectorSet1);
        List<Vector> vectorList18 = Arrays.asList(vectorSet18);
        
        //origin
        Assert.assertEquals(new Vector(4, 4.5, 0.5), new Vector(8, 9, 1).average(Collections.singletonList(Vector.origin(3))));
        Assert.assertEquals(new Vector(-4, -4.5, -0.5), new Vector(-8, -9, -1).average(Collections.singletonList(Vector.origin(3))));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).average(Collections.singletonList(Vector.origin(3))));
        Assert.assertEquals(new Vector(-0.935, 44.03, -3.7), new Vector(-1.87, 88.06, -7.4).average(Collections.singletonList(Vector.origin(3))));
        Assert.assertEquals(new Vector(0.90938061505, -4.08937731545, 3.72948801795), new Vector(1.8187612301, -8.1787546309, 7.4589760359).average(Collections.singletonList(Vector.origin(3))));
        Assert.assertEquals(new Vector(0.9093806150539574, 4.089377315498734, 3.729488017993724), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).average(Collections.singletonList(Vector.origin(3))));
        Assert.assertEquals(new Vector(4.2E-10, 1.387E-15, 5.0E-13), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).average(Collections.singletonList(Vector.origin(3))));
        Assert.assertEquals(new Vector(4, 0.5, 0.5, -3, 1.5, 2, 3.5, 0, -3, 0, 22, 4.5, -0.5, -2.5, 4, 3.5, 0, 1.5), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).average(Collections.singletonList(Vector.origin(18))));
        Assert.assertEquals(new Vector(1.5, 2.5), new Vector(3, 5).average(Collections.singletonList(Vector.origin(2))));
        Assert.assertEquals(new Vector(0.935), new Vector(1.87).average(Collections.singletonList(Vector.origin(1))));
        Assert.assertEquals(new Vector(0), new Vector(0).average(Collections.singletonList(Vector.origin(1))));
        Assert.assertEquals(new Vector(), new Vector().average(Collections.singletonList(Vector.origin(0))));
        
        //list
        Assert.assertEquals(new Vector(2.6250000000000004, 3.1075, 1.54), new Vector(8, 9, 1).average(vectorList3));
        Assert.assertEquals(new Vector(-1.375, -1.3925, 1.04), new Vector(-8, -9, -1).average(vectorList3));
        Assert.assertEquals(new Vector(0.625, 0.8574999999999999, 1.29), new Vector(0, 0, 0).average(vectorList3));
        Assert.assertEquals(new Vector(0.15749999999999997, 22.8725, -0.56), new Vector(-1.87, 88.06, -7.4).average(vectorList3));
        Assert.assertEquals(new Vector(1.079690307525, -1.1871886577250002, 3.1547440089750003), new Vector(1.8187612301, -8.1787546309, 7.4589760359).average(vectorList3));
        Assert.assertEquals(new Vector(1.0796903075269788, 2.9021886577493667, 3.154744008996862), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).average(vectorList3));
        Assert.assertEquals(new Vector(0.62500000021, 0.8575000000000006, 1.29000000000025), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).average(vectorList3));
        Assert.assertEquals(new Vector(2.6250000000000004, 1.1075, 1.54, 1.25, 6.5, 4.575, 1.375, 4.4425, -2.35, 1.9725, 14.5, 0.7250000000000001, 2.2475, 2.375, 1.75, 1.75, 1.25, -1.025), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).average(vectorList18));
        Assert.assertEquals(new Vector(1.375, 2.1075), new Vector(3, 5).average(vectorList2));
        Assert.assertEquals(new Vector(1.0925000000000002), new Vector(1.87).average(vectorList1));
        Assert.assertEquals(new Vector(0.625), new Vector(0).average(vectorList1));
        Assert.assertEquals(new Vector(), new Vector().average(Collections.emptyList()));
    }
    
    /**
     * Helper method for JUnit test of average for array cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testAverageArray() throws Exception {
        Vector[] vectorSet3 = new Vector[] {new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)};
        Vector[] vectorSet2 = new Vector[] {new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)};
        Vector[] vectorSet1 = new Vector[] {new Vector(8), new Vector(0.1), new Vector(-5.6)};
        Vector[] vectorSet18 = new Vector[] {new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        
        //array
        Assert.assertEquals(new Vector(2.6250000000000004, 3.1075, 1.54), new Vector(8, 9, 1).average(vectorSet3));
        Assert.assertEquals(new Vector(-1.375, -1.3925, 1.04), new Vector(-8, -9, -1).average(vectorSet3));
        Assert.assertEquals(new Vector(0.625, 0.8574999999999999, 1.29), new Vector(0, 0, 0).average(vectorSet3));
        Assert.assertEquals(new Vector(0.15749999999999997, 22.8725, -0.56), new Vector(-1.87, 88.06, -7.4).average(vectorSet3));
        Assert.assertEquals(new Vector(1.079690307525, -1.1871886577250002, 3.1547440089750003), new Vector(1.8187612301, -8.1787546309, 7.4589760359).average(vectorSet3));
        Assert.assertEquals(new Vector(1.0796903075269788, 2.9021886577493667, 3.154744008996862), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).average(vectorSet3));
        Assert.assertEquals(new Vector(0.62500000021, 0.8575000000000006, 1.29000000000025), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).average(vectorSet3));
        Assert.assertEquals(new Vector(2.6250000000000004, 1.1075, 1.54, 1.25, 6.5, 4.575, 1.375, 4.4425, -2.35, 1.9725, 14.5, 0.7250000000000001, 2.2475, 2.375, 1.75, 1.75, 1.25, -1.025), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).average(vectorSet18));
        Assert.assertEquals(new Vector(1.375, 2.1075), new Vector(3, 5).average(vectorSet2));
        Assert.assertEquals(new Vector(1.0925000000000002), new Vector(1.87).average(vectorSet1));
        Assert.assertEquals(new Vector(0.625), new Vector(0).average(vectorSet1));
        Assert.assertEquals(new Vector(), new Vector().average(Collections.emptyList()));
        
        //set
        Assert.assertEquals(new Vector(2.6250000000000004, 3.1075, 1.54), new Vector(8, 9, 1).average(new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(-1.375, -1.3925, 1.04), new Vector(-8, -9, -1).average(new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(0.625, 0.8574999999999999, 1.29), new Vector(0, 0, 0).average(new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(0.15749999999999997, 22.8725, -0.56), new Vector(-1.87, 88.06, -7.4).average(new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(1.079690307525, -1.1871886577250002, 3.1547440089750003), new Vector(1.8187612301, -8.1787546309, 7.4589760359).average(new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(1.0796903075269788, 2.9021886577493667, 3.154744008996862), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).average(new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(0.62500000021, 0.8575000000000006, 1.29000000000025), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).average(new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(2.6250000000000004, 1.1075, 1.54, 1.25, 6.5, 4.575, 1.375, 4.4425, -2.35, 1.9725, 14.5, 0.7250000000000001, 2.2475, 2.375, 1.75, 1.75, 1.25, -1.025), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).average(new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)));
        Assert.assertEquals(new Vector(1.375, 2.1075), new Vector(3, 5).average(new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)));
        Assert.assertEquals(new Vector(1.0925000000000002), new Vector(1.87).average(new Vector(8), new Vector(0.1), new Vector(-5.6)));
        Assert.assertEquals(new Vector(0.625), new Vector(0).average(new Vector(8), new Vector(0.1), new Vector(-5.6)));
        Assert.assertEquals(new Vector(), new Vector().average(Collections.emptyList()));
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see Vector#dot(Vector)
     */
    @Test
    public void testDot() throws Exception {
        //origin
        Assert.assertEquals(0, new Vector(8, 9, 1).dot(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(-8, -9, -1).dot(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0, 0, 0).dot(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(-1.87, 88.06, -7.4).dot(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(1.8187612301, -8.1787546309, 7.4589760359).dot(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).dot(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).dot(Vector.origin(3)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).dot(Vector.origin(18)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(3, 5).dot(Vector.origin(2)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(1.87).dot(Vector.origin(1)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0).dot(Vector.origin(1)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector().dot(Vector.origin(0)), TestUtils.DELTA);
        
        //standard
        Assert.assertEquals(146, new Vector(8, 9, 1).dot(new Vector(8, 9, 1)), TestUtils.DELTA);
        Assert.assertEquals(-90, new Vector(-8, -9, -1).dot(new Vector(5, 5, 5)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0, 0, 0).dot(new Vector(10, 8, 4)), TestUtils.DELTA);
        Assert.assertEquals(5.94106852, new Vector(-1.87, 88.06, -7.4).dot(new Vector(6.54741, 1.254487, 12.471)), TestUtils.DELTA);
        Assert.assertEquals(-17.16885852376491, new Vector(1.8187612301, -8.1787546309, 7.4589760359).dot(new Vector(9, 5.16474, 1.16684)), TestUtils.DELTA);
        Assert.assertEquals(67.31342386170068, new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).dot(new Vector(9, 5.16474, 1.16684)), TestUtils.DELTA);
        Assert.assertEquals(5.544118031E-8, new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).dot(new Vector(66, 65, 1)), TestUtils.DELTA);
        Assert.assertEquals(475.61994, new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).dot(new Vector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)), TestUtils.DELTA);
        Assert.assertEquals(8, new Vector(3, 5).dot(new Vector(1, 1)), TestUtils.DELTA);
        Assert.assertEquals(3.74, new Vector(1.87).dot(new Vector(2)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0).dot(new Vector(10)), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector().dot(new Vector()), TestUtils.DELTA);
        
        //invalid
        
        try {
            new Vector(8, 9, 1).dot(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector(8).dot(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().dot(new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().dot(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
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
        Assert.assertEquals(new Vector(0.6620847108818944, 0.7448452997421311, 0.0827605888602368), new Vector(8, 9, 1).normalize());
        Assert.assertEquals(new Vector(-0.6620847108818944, -0.7448452997421311, -0.0827605888602368), new Vector(-8, -9, -1).normalize());
        Assert.assertEquals(new Vector(-0.02115620094155835, 0.9962647352479295, -0.08371972565108651), new Vector(-1.87, 88.06, -7.4).normalize());
        Assert.assertEquals(new Vector(0.16213352159737185, -0.7290953142407378, 0.6649306309132061), new Vector(1.8187612301, -8.1787546309, 7.4589760359).normalize());
        Assert.assertEquals(new Vector(0.16213352159619135, 0.7290953142409451, 0.6649306309132667), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).normalize());
        Assert.assertEquals(new Vector(0.9999992913785204, 3.302378612242876E-6, 0.0011904753468791908), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).normalize());
        Assert.assertEquals(new Vector(0.16408746141521455, 0.02051093267690182, 0.02051093267690182, -0.1230655960614109, 0.06153279803070545, 0.08204373070760727, 0.14357652873831273, 0, -0.1230655960614109, 0, 0.90248103778368, 0.18459839409211637, -0.02051093267690182, -0.1025546633845091, 0.16408746141521455, 0.14357652873831273, 0, 0.06153279803070545), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).normalize());
        Assert.assertEquals(new Vector(0.5144957554275265, 0.8574929257125441), new Vector(3, 5).normalize());
        Assert.assertEquals(new Vector(), new Vector().normalize());
        
        //simple
        Assert.assertEquals(new Vector(1), new Vector(1.87).normalize());
        Assert.assertEquals(new Vector(1, 0), new Vector(1.87, 0).normalize());
        Assert.assertEquals(new Vector(1, 0, 0), new Vector(1.87, 0, 0).normalize());
        Assert.assertEquals(new Vector(0, 1, 0), new Vector(0, 2.7845, 0).normalize());
        Assert.assertEquals(new Vector(0, 0, 1), new Vector(0, 0, 7.41001).normalize());
        
        //invalid
        
        try {
            Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).normalize());
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
            Assert.assertEquals(new Vector(0), new Vector(0).normalize());
        } catch (Exception e) {
            Assert.fail();
        }
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
        Assert.assertEquals(12.083045973594572, new Vector(8, 9, 1).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(12.083045973594572, new Vector(-8, -9, -1).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(88.39016065151144, new Vector(-1.87, 88.06, -7.4).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(11.217675482412279, new Vector(1.8187612301, -8.1787546309, 7.4589760359).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(11.217675482542772, new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(8.400005952424647E-10, new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(48.75448697299562, new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(5.830951894845301, new Vector(3, 5).hypotenuse(), TestUtils.DELTA);
        
        //simple
        Assert.assertEquals(1.87, new Vector(1.87).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(1.87, new Vector(1.87, 0).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(1.87, new Vector(1.87, 0, 0).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(2.7845, new Vector(0, 2.7845, 0).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(7.41001, new Vector(0, 0, 7.41001).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0, 0, 0).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0).hypotenuse(), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector().hypotenuse(), TestUtils.DELTA);
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
        Assert.assertEquals(18, new Vector(8, 9, 1).sum(), TestUtils.DELTA);
        Assert.assertEquals(-18, new Vector(-8, -9, -1).sum(), TestUtils.DELTA);
        Assert.assertEquals(78.79, new Vector(-1.87, 88.06, -7.4).sum(), TestUtils.DELTA);
        Assert.assertEquals(1.0989826351, new Vector(1.8187612301, -8.1787546309, 7.4589760359).sum(), TestUtils.DELTA);
        Assert.assertEquals(17.456491897092832, new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).sum(), TestUtils.DELTA);
        Assert.assertEquals(8.41002774E-10, new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).sum(), TestUtils.DELTA);
        Assert.assertEquals(77, new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).sum(), TestUtils.DELTA);
        Assert.assertEquals(8, new Vector(3, 5).sum(), TestUtils.DELTA);
        
        //simple
        Assert.assertEquals(1.87, new Vector(1.87).sum(), TestUtils.DELTA);
        Assert.assertEquals(1.87, new Vector(1.87, 0).sum(), TestUtils.DELTA);
        Assert.assertEquals(1.87, new Vector(1.87, 0, 0).sum(), TestUtils.DELTA);
        Assert.assertEquals(2.7845, new Vector(0, 2.7845, 0).sum(), TestUtils.DELTA);
        Assert.assertEquals(7.41001, new Vector(0, 0, 7.41001).sum(), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0, 0, 0).sum(), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0).sum(), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector().sum(), TestUtils.DELTA);
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
        Assert.assertEquals(146, new Vector(8, 9, 1).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(146, new Vector(-8, -9, -1).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(7812.820500000001, new Vector(-1.87, 88.06, -7.4).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(125.83624322871356, new Vector(1.8187612301, -8.1787546309, 7.4589760359).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(125.8362432316412, new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(7.056010000076951E-19, new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(2377, new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(34, new Vector(3, 5).squareSum(), TestUtils.DELTA);
        
        //simple
        Assert.assertEquals(3.4969000000000006, new Vector(1.87).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(3.4969000000000006, new Vector(1.87, 0).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(3.4969000000000006, new Vector(1.87, 0, 0).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(7.75344025, new Vector(0, 2.7845, 0).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(54.9082482001, new Vector(0, 0, 7.41001).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0, 0, 0).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector(0).squareSum(), TestUtils.DELTA);
        Assert.assertEquals(0, new Vector().squareSum(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see Vector#plus(Vector)
     */
    @Test
    public void testPlus() throws Exception {
        //identity
        Assert.assertEquals(new Vector(8, 9, 1), new Vector(8, 9, 1).plus(Vector.origin(3)));
        Assert.assertEquals(new Vector(-8, -9, -1), new Vector(-8, -9, -1).plus(Vector.origin(3)));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).plus(Vector.origin(3)));
        Assert.assertEquals(new Vector(-1.87, 88.06, -7.4), new Vector(-1.87, 88.06, -7.4).plus(Vector.origin(3)));
        Assert.assertEquals(new Vector(1.8187612301, -8.1787546309, 7.4589760359), new Vector(1.8187612301, -8.1787546309, 7.4589760359).plus(Vector.origin(3)));
        Assert.assertEquals(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).plus(Vector.origin(3)));
        Assert.assertEquals(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).plus(Vector.origin(3)));
        Assert.assertEquals(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).plus(Vector.origin(18)));
        Assert.assertEquals(new Vector(3, 5), new Vector(3, 5).plus(Vector.origin(2)));
        Assert.assertEquals(new Vector(1.87), new Vector(1.87).plus(Vector.origin(1)));
        Assert.assertEquals(new Vector(0), new Vector(0).plus(Vector.origin(1)));
        Assert.assertEquals(new Vector(), new Vector().plus(Vector.origin(0)));
        
        //standard
        Assert.assertEquals(new Vector(16, 18, 2), new Vector(8, 9, 1).plus(new Vector(8, 9, 1)));
        Assert.assertEquals(new Vector(-3, -4, 4), new Vector(-8, -9, -1).plus(new Vector(5, 5, 5)));
        Assert.assertEquals(new Vector(10, 8, 4), new Vector(0, 0, 0).plus(new Vector(10, 8, 4)));
        Assert.assertEquals(new Vector(4.67741, 89.314487, 5.071), new Vector(-1.87, 88.06, -7.4).plus(new Vector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new Vector(10.8187612301, -3.0140146309, 8.6258160359), new Vector(1.8187612301, -8.1787546309, 7.4589760359).plus(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(10.818761230107915, 13.343494630997467, 8.625816035987448), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).plus(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(66.00000000084, 65, 1.000000000001), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).plus(new Vector(66, 65, 1)));
        Assert.assertEquals(new Vector(16, 2, 2, -6, 9.7, 11, 10, 33, -4.51999, 6, 51, 13, -0.4, 2, 9, 7, 2.9, 14), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).plus(new Vector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new Vector(4, 6), new Vector(3, 5).plus(new Vector(1, 1)));
        Assert.assertEquals(new Vector(3.87), new Vector(1.87).plus(new Vector(2)));
        Assert.assertEquals(new Vector(10), new Vector(0).plus(new Vector(10)));
        Assert.assertEquals(new Vector(), new Vector().plus(new Vector()));
        
        //invalid
        
        try {
            new Vector(8, 9, 1).plus(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector(8).plus(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().plus(new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().plus(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see Vector#minus(Vector)
     */
    @Test
    public void testMinus() throws Exception {
        //identity
        Assert.assertEquals(new Vector(8, 9, 1), new Vector(8, 9, 1).minus(Vector.origin(3)));
        Assert.assertEquals(new Vector(-8, -9, -1), new Vector(-8, -9, -1).minus(Vector.origin(3)));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).minus(Vector.origin(3)));
        Assert.assertEquals(new Vector(-1.87, 88.06, -7.4), new Vector(-1.87, 88.06, -7.4).minus(Vector.origin(3)));
        Assert.assertEquals(new Vector(1.8187612301, -8.1787546309, 7.4589760359), new Vector(1.8187612301, -8.1787546309, 7.4589760359).minus(Vector.origin(3)));
        Assert.assertEquals(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).minus(Vector.origin(3)));
        Assert.assertEquals(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).minus(Vector.origin(3)));
        Assert.assertEquals(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).minus(Vector.origin(18)));
        Assert.assertEquals(new Vector(3, 5), new Vector(3, 5).minus(Vector.origin(2)));
        Assert.assertEquals(new Vector(1.87), new Vector(1.87).minus(Vector.origin(1)));
        Assert.assertEquals(new Vector(0), new Vector(0).minus(Vector.origin(1)));
        Assert.assertEquals(new Vector(), new Vector().minus(Vector.origin(0)));
        
        //standard
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(8, 9, 1).minus(new Vector(8, 9, 1)));
        Assert.assertEquals(new Vector(-13, -14, -6), new Vector(-8, -9, -1).minus(new Vector(5, 5, 5)));
        Assert.assertEquals(new Vector(-10, -8, -4), new Vector(0, 0, 0).minus(new Vector(10, 8, 4)));
        Assert.assertEquals(new Vector(-8.41741, 86.805513, -19.871000000000002), new Vector(-1.87, 88.06, -7.4).minus(new Vector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new Vector(-7.1812387699, -13.3434946309, 6.2921360359000005), new Vector(1.8187612301, -8.1787546309, 7.4589760359).minus(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(-7.181238769892085, 3.014014630997467, 6.292136035987449), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).minus(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(-65.99999999916, -65, -0.999999999999), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).minus(new Vector(66, 65, 1)));
        Assert.assertEquals(new Vector(0, 0, 0, -6, -3.7, -3, 4, -33, -7.48001, -6, 37, 5, -1.6, -12, 7, 7, -2.9, -8), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).minus(new Vector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new Vector(2, 4), new Vector(3, 5).minus(new Vector(1, 1)));
        Assert.assertEquals(new Vector(-0.1299999999999999), new Vector(1.87).minus(new Vector(2)));
        Assert.assertEquals(new Vector(-10), new Vector(0).minus(new Vector(10)));
        Assert.assertEquals(new Vector(), new Vector().minus(new Vector()));
        
        //invalid
        
        try {
            new Vector(8, 9, 1).minus(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector(8).minus(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().minus(new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().minus(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see Vector#times(Vector)
     */
    @Test
    public void testTimes() throws Exception {
        //identity
        Assert.assertEquals(new Vector(8, 9, 1), new Vector(8, 9, 1).times(Vector.unit(3)));
        Assert.assertEquals(new Vector(-8, -9, -1), new Vector(-8, -9, -1).times(Vector.unit(3)));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).times(Vector.unit(3)));
        Assert.assertEquals(new Vector(-1.87, 88.06, -7.4), new Vector(-1.87, 88.06, -7.4).times(Vector.unit(3)));
        Assert.assertEquals(new Vector(1.8187612301, -8.1787546309, 7.4589760359), new Vector(1.8187612301, -8.1787546309, 7.4589760359).times(Vector.unit(3)));
        Assert.assertEquals(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).times(Vector.unit(3)));
        Assert.assertEquals(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).times(Vector.unit(3)));
        Assert.assertEquals(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).times(Vector.unit(18)));
        Assert.assertEquals(new Vector(3, 5), new Vector(3, 5).times(Vector.unit(2)));
        Assert.assertEquals(new Vector(1.87), new Vector(1.87).times(Vector.unit(1)));
        Assert.assertEquals(new Vector(0), new Vector(0).times(Vector.unit(1)));
        Assert.assertEquals(new Vector(), new Vector().times(Vector.unit(0)));
        
        //standard
        Assert.assertEquals(new Vector(64, 81, 1), new Vector(8, 9, 1).times(new Vector(8, 9, 1)));
        Assert.assertEquals(new Vector(-40, -45, -5), new Vector(-8, -9, -1).times(new Vector(5, 5, 5)));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).times(new Vector(10, 8, 4)));
        Assert.assertEquals(new Vector(-12.2436567, 110.47012522, -92.28540000000001), new Vector(-1.87, 88.06, -7.4).times(new Vector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new Vector(16.3688510709, -42.241141192394466, 8.703431597729557), new Vector(1.8187612301, -8.1787546309, 7.4589760359).times(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(16.368851070971232, 42.24114119289786, 8.703431597831596), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).times(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(5.544E-8, 1.8031000000000002E-13, 1.0E-12), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).times(new Vector(66, 65, 1)));
        Assert.assertEquals(new Vector(64, 1, 1, 0, 20.1, 28, 21, 0, -8.88006, 0, 308, 36, -0.6, -35, 8, 0, 0, 33), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).times(new Vector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new Vector(3, 5), new Vector(3, 5).times(new Vector(1, 1)));
        Assert.assertEquals(new Vector(3.74), new Vector(1.87).times(new Vector(2)));
        Assert.assertEquals(new Vector(0), new Vector(0).times(new Vector(10)));
        Assert.assertEquals(new Vector(), new Vector().times(new Vector()));
        
        //invalid
        
        try {
            new Vector(8, 9, 1).times(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector(8).times(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().times(new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().times(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Vector#scale(double)
     */
    @Test
    public void testScale() throws Exception {
        //identity
        Assert.assertEquals(new Vector(8, 9, 1), new Vector(8, 9, 1).scale(1));
        Assert.assertEquals(new Vector(-8, -9, -1), new Vector(-8, -9, -1).scale(1));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).scale(1));
        Assert.assertEquals(new Vector(-1.87, 88.06, -7.4), new Vector(-1.87, 88.06, -7.4).scale(1));
        Assert.assertEquals(new Vector(1.8187612301, -8.1787546309, 7.4589760359), new Vector(1.8187612301, -8.1787546309, 7.4589760359).scale(1));
        Assert.assertEquals(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).scale(1));
        Assert.assertEquals(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).scale(1));
        Assert.assertEquals(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).scale(1));
        Assert.assertEquals(new Vector(3, 5), new Vector(3, 5).scale(1));
        Assert.assertEquals(new Vector(1.87), new Vector(1.87).scale(1));
        Assert.assertEquals(new Vector(0), new Vector(0).scale(1));
        Assert.assertEquals(new Vector(), new Vector().scale(1));
        
        //standard
        Assert.assertEquals(new Vector(-8, -9, -1), new Vector(8, 9, 1).scale(-1));
        Assert.assertEquals(new Vector(8, 9, 1), new Vector(-8, -9, -1).scale(-1));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).scale(8.46));
        Assert.assertEquals(new Vector(-13.382542800000001, 630.1961064, -52.957656), new Vector(-1.87, 88.06, -7.4).scale(7.15644));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(1.8187612301, -8.1787546309, 7.4589760359).scale(0));
        Assert.assertEquals(new Vector(-8.184425535485616, -36.804395839488606, -33.56539216194352), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).scale(-4.5));
        Assert.assertEquals(new Vector(8.399999999999999E-16, 2.774E-21, 9.999999999999999E-19), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).scale(0.000001));
        Assert.assertEquals(new Vector(80, 10, 10, -60, 30, 40, 70, 0, -60, 0, 440, 90, -10, -50, 80, 70, 0, 30), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).scale(10));
        Assert.assertEquals(new Vector(27.900000000000002, 46.5), new Vector(3, 5).scale(9.3));
        Assert.assertEquals(new Vector(2.0570000000000004), new Vector(1.87).scale(1.1));
        Assert.assertEquals(new Vector(0), new Vector(0).scale(15));
        Assert.assertEquals(new Vector(), new Vector().scale(50));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see Vector#dividedBy(Vector)
     */
    @Test
    public void testDividedBy() throws Exception {
        //identity
        Assert.assertEquals(new Vector(8, 9, 1), new Vector(8, 9, 1).dividedBy(Vector.unit(3)));
        Assert.assertEquals(new Vector(-8, -9, -1), new Vector(-8, -9, -1).dividedBy(Vector.unit(3)));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).dividedBy(Vector.unit(3)));
        Assert.assertEquals(new Vector(-1.87, 88.06, -7.4), new Vector(-1.87, 88.06, -7.4).dividedBy(Vector.unit(3)));
        Assert.assertEquals(new Vector(1.8187612301, -8.1787546309, 7.4589760359), new Vector(1.8187612301, -8.1787546309, 7.4589760359).dividedBy(Vector.unit(3)));
        Assert.assertEquals(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).dividedBy(Vector.unit(3)));
        Assert.assertEquals(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).dividedBy(Vector.unit(3)));
        Assert.assertEquals(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).dividedBy(Vector.unit(18)));
        Assert.assertEquals(new Vector(3, 5), new Vector(3, 5).dividedBy(Vector.unit(2)));
        Assert.assertEquals(new Vector(1.87), new Vector(1.87).dividedBy(Vector.unit(1)));
        Assert.assertEquals(new Vector(0), new Vector(0).dividedBy(Vector.unit(1)));
        Assert.assertEquals(new Vector(), new Vector().dividedBy(Vector.unit(0)));
        
        //standard
        Assert.assertEquals(new Vector(1, 1, 1), new Vector(8, 9, 1).dividedBy(new Vector(8, 9, 1)));
        Assert.assertEquals(new Vector(-1.6, -1.8, -0.2), new Vector(-8, -9, -1).dividedBy(new Vector(5, 5, 5)));
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).dividedBy(new Vector(10, 8, 4)));
        Assert.assertEquals(new Vector(-0.28560911872022676, 70.19602435098969, -0.5933766337903937), new Vector(-1.87, 88.06, -7.4).dividedBy(new Vector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new Vector(0.20208458112222222, -1.5835752876040228, 6.392458294110589), new Vector(1.8187612301, -8.1787546309, 7.4589760359).dividedBy(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(0.20208458112310165, 1.5835752876228943, 6.392458294185533), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).dividedBy(new Vector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new Vector(1.2727272727272727E-11, 4.267692307692308E-17, 1.0E-12), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).dividedBy(new Vector(66, 65, 1)));
        Assert.assertEquals(new Vector(1, 1, 1, -0.7407407407407408, 0.44776119402985076, 0.5714285714285714, 2.3333333333333335, 0, -4.054026661982014, 0, 6.285714285714286, 2.25, -1.6666666666666667, -0.7142857142857143, 8, 1.75, 0, 0.2727272727272727), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).dividedBy(new Vector(8, 1, 1, 8.1, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 4, 2.9, 11)));
        Assert.assertEquals(new Vector(3, 5), new Vector(3, 5).dividedBy(new Vector(1, 1)));
        Assert.assertEquals(new Vector(0.935), new Vector(1.87).dividedBy(new Vector(2)));
        Assert.assertEquals(new Vector(0), new Vector(0).dividedBy(new Vector(10)));
        Assert.assertEquals(new Vector(), new Vector().dividedBy(new Vector()));
        
        //invalid
        
        try {
            new Vector(8, 9, 1).dividedBy(new Vector(1, 1, 0));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector(8, 9, 1).dividedBy(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector(8).dividedBy(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().dividedBy(new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector().dividedBy(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see Vector#round()
     */
    @Test
    public void testRound() throws Exception {
        Assert.assertEquals(new Vector(8, 9, 1), new Vector(8, 9, 1).round());
        Assert.assertEquals(new Vector(-8, -9, -1), new Vector(-8, -9, -1).round());
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).round());
        Assert.assertEquals(new Vector(-2, 88, -7), new Vector(-1.87, 88.06, -7.4).round());
        Assert.assertEquals(new Vector(2, -8, 7), new Vector(1.8187612301, -8.1787546309, 7.4589760359).round());
        Assert.assertEquals(new Vector(2, 8, 7), new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).round());
        Assert.assertEquals(new Vector(0, 0, 0), new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).round());
        Assert.assertEquals(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).round());
        Assert.assertEquals(new Vector(3, 5), new Vector(3, 5).round());
        Assert.assertEquals(new Vector(2), new Vector(1.87).round());
        Assert.assertEquals(new Vector(0), new Vector(0).round());
        Assert.assertEquals(new Vector(), new Vector().round());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see Vector#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        Vector vector = new Vector(5, 1.8744, 11.001, 8);
        
        //standard
        vector.redim(3);
        Assert.assertEquals(new Vector(5, 1.8744, 11.001), vector);
        vector.redim(2);
        Assert.assertEquals(new Vector(5, 1.8744), vector);
        vector.redim(8);
        Assert.assertEquals(new Vector(5, 1.8744, 0, 0, 0, 0, 0, 0), vector);
        vector.redim(1);
        Assert.assertEquals(new Vector(5), vector);
        vector.redim(0);
        Assert.assertEquals(new Vector(), vector);
        vector.redim(1);
        Assert.assertEquals(new Vector(0), vector);
        
        //invalid
        vector.redim(-1);
        Assert.assertEquals(new Vector(), vector);
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
        Vector vector = new Vector(5, 1.8744, 11.001, 8, 1.4, 7.5, 3, 1);
        
        //standard
        Assert.assertEquals(new Vector(11.001), vector.subVector(2, 3));
        Assert.assertEquals(new Vector(11.001, 8, 1.4), vector.subVector(2, 5));
        Assert.assertEquals(new Vector(5, 1.8744), vector.subVector(0, 2));
        Assert.assertEquals(new Vector(1.8744), vector.subVector(1, 2));
        Assert.assertEquals(new Vector(1.4, 7.5, 3), vector.subVector(4, 7));
        Assert.assertEquals(new Vector(7.5, 3, 1), vector.subVector(5, 8));
        Assert.assertEquals(new Vector(5, 1.8744, 11.001, 8, 1.4, 7.5, 3, 1), vector.subVector(0, 8));
        
        //to end
        Assert.assertEquals(new Vector(3, 1), vector.subVector(6));
        Assert.assertEquals(new Vector(7.5, 3, 1), vector.subVector(5));
        Assert.assertEquals(new Vector(11.001, 8, 1.4, 7.5, 3, 1), vector.subVector(2));
        Assert.assertEquals(new Vector(5, 1.8744, 11.001, 8, 1.4, 7.5, 3, 1), vector.subVector(0));
        
        //invalid
        
        try {
            vector.subVector(5, 9);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.subVector(-1, 5);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.subVector(6, 4);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.subVector(-10);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.subVector(84);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        Assert.assertEquals(3, new Vector(8, 9, 1).getDimensionality());
        Assert.assertEquals(3, new Vector(-8, -9, -1).getDimensionality());
        Assert.assertEquals(3, new Vector(0, 0, 0).getDimensionality());
        Assert.assertEquals(3, new Vector(-1.87, 88.06, -7.4).getDimensionality());
        Assert.assertEquals(3, new Vector(1.8187612301, -8.1787546309, 7.4589760359).getDimensionality());
        Assert.assertEquals(3, new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).getDimensionality());
        Assert.assertEquals(3, new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).getDimensionality());
        Assert.assertEquals(18, new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).getDimensionality());
        Assert.assertEquals(2, new Vector(3, 5).getDimensionality());
        Assert.assertEquals(1, new Vector(1.87).getDimensionality());
        Assert.assertEquals(1, new Vector(0).getDimensionality());
        Assert.assertEquals(0, new Vector().getDimensionality());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        Assert.assertArrayEquals(new double[] {8, 9, 1}, new Vector(8, 9, 1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-8, -9, -1}, new Vector(-8, -9, -1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, 0, 0}, new Vector(0, 0, 0).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-1.87, 88.06, -7.4}, new Vector(-1.87, 88.06, -7.4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {1.8187612301, -8.1787546309, 7.4589760359}, new Vector(1.8187612301, -8.1787546309, 7.4589760359).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773}, new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0.00000000084, 0.000000000000002774, 0.000000000001}, new Vector(0.00000000084, 0.000000000000002774, 0.000000000001).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3}, new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {3, 5}, new Vector(3, 5).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {1.87}, new Vector(1.87).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0}, new Vector(0).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {}, new Vector().getComponents(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getX()
     */
    @Test
    public void testGetX() throws Exception {
        Vector vector = new Vector(5, 1.8744, 11.001, 8);
        Vector vector2 = new Vector();
        
        //standard
        Assert.assertEquals(5, vector.getX(), TestUtils.DELTA);
        
        //invalid
        try {
            Assert.assertEquals(0, vector2.getX(), TestUtils.DELTA);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getY()
     */
    @Test
    public void testGetY() throws Exception {
        Vector vector = new Vector(5, 1.8744, 11.001, 8);
        Vector vector2 = new Vector(5);
        
        //standard
        Assert.assertEquals(1.8744, vector.getY(), TestUtils.DELTA);
        
        //invalid
        try {
            Assert.assertEquals(0, vector2.getY(), TestUtils.DELTA);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        Vector vector = new Vector(5, 1.8744, 11.001, 8);
        Vector vector2 = new Vector(5, 1.8744);
        
        //standard
        Assert.assertEquals(11.001, vector.getZ(), TestUtils.DELTA);
        
        //invalid
        try {
            Assert.assertEquals(0, vector2.getZ(), TestUtils.DELTA);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getW()
     */
    @Test
    public void testGetW() throws Exception {
        Vector vector = new Vector(5, 1.8744, 11.001, 8);
        Vector vector2 = new Vector(5, 1.8744, 11.001);
        
        //standard
        Assert.assertEquals(8, vector.getW(), TestUtils.DELTA);
        
        //invalid
        try {
            Assert.assertEquals(0, vector2.getW(), TestUtils.DELTA);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see Vector#get(int)
     */
    @Test
    public void testGet() throws Exception {
        Vector vector = new Vector(5, 1.8744, 11.001, 8, 4.49);
        
        //standard
        Assert.assertEquals(5, vector.get(0), TestUtils.DELTA);
        Assert.assertEquals(1.8744, vector.get(1), TestUtils.DELTA);
        Assert.assertEquals(11.001, vector.get(2), TestUtils.DELTA);
        Assert.assertEquals(8, vector.get(3), TestUtils.DELTA);
        Assert.assertEquals(4.49, vector.get(4), TestUtils.DELTA);
        
        //invalid
        
        try {
            vector.get(5);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.get(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
    
    /**
     * JUnit test of getJustificationVector.
     *
     * @throws Exception When there is an exception.
     * @see Vector#getJustificationVector()
     */
    @Test
    public void testGetJustificationVector() throws Exception {
        Assert.assertEquals(Whitebox.getInternalState(Vector.class, "JUSTIFICATION_VECTOR"), Vector.getJustificationVector());
        Whitebox.setInternalState(Vector.class, "JUSTIFICATION_VECTOR", new Vector(1, -2, -4));
        Assert.assertEquals(new Vector(1, -2, -4), Vector.getJustificationVector());
        Assert.assertEquals(new Vector(1, -2, -4), Vector.getJustificationVector());
        Whitebox.setInternalState(Vector.class, "JUSTIFICATION_VECTOR", new Vector(1, 1, 1));
        Assert.assertEquals(new Vector(1, 1, 1), Vector.getJustificationVector());
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setX(double)
     */
    @Test
    public void testSetX() throws Exception {
        Vector vector = Vector.origin(4);
        Vector vector2 = Vector.origin(0);
        
        //standard
        Assert.assertEquals(new Vector(0, 0, 0, 0), vector);
        vector.setX(5);
        Assert.assertEquals(new Vector(5, 0, 0, 0), vector);
        vector.setX(1.8744);
        Assert.assertEquals(new Vector(1.8744, 0, 0, 0), vector);
        vector.setX(11.001);
        Assert.assertEquals(new Vector(11.001, 0, 0, 0), vector);
        
        //invalid
        Assert.assertEquals(new Vector(), vector2);
        try {
            vector2.setX(5);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(new Vector(), vector2);
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setY(double)
     */
    @Test
    public void testSetY() throws Exception {
        Vector vector = Vector.origin(4);
        Vector vector2 = Vector.origin(1);
        
        //standard
        Assert.assertEquals(new Vector(0, 0, 0, 0), vector);
        vector.setY(5);
        Assert.assertEquals(new Vector(0, 5, 0, 0), vector);
        vector.setY(1.8744);
        Assert.assertEquals(new Vector(0, 1.8744, 0, 0), vector);
        vector.setY(11.001);
        Assert.assertEquals(new Vector(0, 11.001, 0, 0), vector);
        
        //invalid
        Assert.assertEquals(new Vector(0), vector2);
        try {
            vector2.setY(5);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(new Vector(0), vector2);
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setZ(double)
     */
    @Test
    public void testSetZ() throws Exception {
        Vector vector = Vector.origin(4);
        Vector vector2 = Vector.origin(2);
        
        //standard
        Assert.assertEquals(new Vector(0, 0, 0, 0), vector);
        vector.setZ(5);
        Assert.assertEquals(new Vector(0, 0, 5, 0), vector);
        vector.setZ(1.8744);
        Assert.assertEquals(new Vector(0, 0, 1.8744, 0), vector);
        vector.setZ(11.001);
        Assert.assertEquals(new Vector(0, 0, 11.001, 0), vector);
        
        //invalid
        Assert.assertEquals(new Vector(0, 0), vector2);
        try {
            vector2.setZ(5);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(new Vector(0, 0), vector2);
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setW(double)
     */
    @Test
    public void testSetW() throws Exception {
        Vector vector = Vector.origin(4);
        Vector vector2 = Vector.origin(3);
        
        //standard
        Assert.assertEquals(new Vector(0, 0, 0, 0), vector);
        vector.setW(5);
        Assert.assertEquals(new Vector(0, 0, 0, 5), vector);
        vector.setW(1.8744);
        Assert.assertEquals(new Vector(0, 0, 0, 1.8744), vector);
        vector.setW(11.001);
        Assert.assertEquals(new Vector(0, 0, 0, 11.001), vector);
        
        //invalid
        Assert.assertEquals(new Vector(0, 0, 0), vector2);
        try {
            vector2.setW(5);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(new Vector(0, 0, 0), vector2);
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see Vector#set(int, double)
     */
    @Test
    public void testSet() throws Exception {
        Vector vector = Vector.origin(5);
        
        //standard
        Assert.assertEquals(new Vector(0, 0, 0, 0, 0), vector);
        vector.set(0, 5);
        Assert.assertEquals(new Vector(5, 0, 0, 0, 0), vector);
        vector.set(1, 1.8744);
        Assert.assertEquals(new Vector(5, 1.8744, 0, 0, 0), vector);
        vector.set(2, 11.001);
        Assert.assertEquals(new Vector(5, 1.8744, 11.001, 0, 0), vector);
        vector.set(3, 8);
        Assert.assertEquals(new Vector(5, 1.8744, 11.001, 8, 0), vector);
        vector.set(4, 4.49);
        Assert.assertEquals(new Vector(5, 1.8744, 11.001, 8, 4.49), vector);
        
        //invalid
        
        try {
            vector.set(5, 5.01);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.set(-1, 5.01);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
    
    /**
     * JUnit test of setJustificationVector.
     *
     * @throws Exception When there is an exception.
     * @see Vector#setJustificationVector(Vector)
     */
    @Test
    public void testSetJustificationVector() throws Exception {
        Assert.assertEquals(new Vector(1, 1, 1), Whitebox.getInternalState(Vector.class, "JUSTIFICATION_VECTOR"));
        Vector.setJustificationVector(new Vector(1, -2, -4));
        Assert.assertEquals(new Vector(1, -2, -4), Whitebox.getInternalState(Vector.class, "JUSTIFICATION_VECTOR"));
        Assert.assertEquals(new Vector(1, -2, -4), Whitebox.getInternalState(Vector.class, "JUSTIFICATION_VECTOR"));
        Vector.setJustificationVector(new Vector(1, 1, 1));
        Assert.assertEquals(new Vector(1, 1, 1), Whitebox.getInternalState(Vector.class, "JUSTIFICATION_VECTOR"));
    }
    
    /**
     * JUnit test of copyVector.
     *
     * @throws Exception When there is an exception.
     * @see Vector#copyVector(Vector, Vector)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testCopyVector() throws Exception {
        Vector vector;
        Vector copy;
        int hash;
        
        //standard
        
        vector = new Vector(8, 6, 1);
        copy = new Vector(1, 7, 7);
        hash = System.identityHashCode(copy);
        Vector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components, TestUtils.DELTA);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        vector = new Vector(1.654, 8.8778, -5.21);
        copy = new Vector(0, 0, 0);
        hash = System.identityHashCode(copy);
        Vector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components, TestUtils.DELTA);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        vector = new Vector(1.654, 8.8778, -5.21, 4.1, 3.05914, 7.56, -0.54);
        copy = new Vector(1, 1, 1, 1, 1, 1, 1);
        hash = System.identityHashCode(copy);
        Vector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components, TestUtils.DELTA);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        vector = new Vector();
        copy = new Vector();
        hash = System.identityHashCode(copy);
        Vector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components, TestUtils.DELTA);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        //invalid
        
        try {
            Vector.copyVector(new Vector(1, 1, 1), new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector.copyVector(new Vector(1), new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector.copyVector(new Vector(1), new Vector());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector.copyVector(new Vector(1), null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of unit.
     *
     * @throws Exception When there is an exception.
     * @see Vector#unit(int)
     */
    @Test
    public void testUnit() throws Exception {
        //standard
        Assert.assertEquals(new Vector(), Vector.unit(0));
        Assert.assertEquals(new Vector(1), Vector.unit(1));
        Assert.assertEquals(new Vector(1, 1), Vector.unit(2));
        Assert.assertEquals(new Vector(1, 1, 1), Vector.unit(3));
        Assert.assertEquals(new Vector(1, 1, 1, 1), Vector.unit(4));
        Assert.assertEquals(new Vector(1, 1, 1, 1, 1), Vector.unit(5));
        Assert.assertEquals(new Vector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1), Vector.unit(10));
        
        //uniqueness
        Assert.assertEquals(Vector.unit(3), Vector.unit(3));
        Assert.assertNotEquals(System.identityHashCode(Vector.unit(3)), System.identityHashCode(Vector.unit(3)));
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
        Assert.assertEquals(new Vector(), Vector.origin(0));
        Assert.assertEquals(new Vector(0), Vector.origin(1));
        Assert.assertEquals(new Vector(0, 0), Vector.origin(2));
        Assert.assertEquals(new Vector(0, 0, 0), Vector.origin(3));
        Assert.assertEquals(new Vector(0, 0, 0, 0), Vector.origin(4));
        Assert.assertEquals(new Vector(0, 0, 0, 0, 0), Vector.origin(5));
        Assert.assertEquals(new Vector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), Vector.origin(10));
        
        //uniqueness
        Assert.assertEquals(Vector.origin(3), Vector.origin(3));
        Assert.assertNotEquals(System.identityHashCode(Vector.origin(3)), System.identityHashCode(Vector.origin(3)));
    }
    
    /**
     * JUnit test of averageVector.
     *
     * @throws Exception When there is an exception.
     * @see Vector#averageVector(List)
     * @see Vector#averageVector(Vector...)
     * @see #testAverageVectorList()
     * @see #testAverageVectorArray()
     */
    @Test
    public void testAverageVector() throws Exception {
        Vector[] vectorSet3 = new Vector[] {new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)};
        Vector[] vectorSet2 = new Vector[] {new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)};
        Vector[] vectorSet1 = new Vector[] {new Vector(8), new Vector(0.1), new Vector(-5.6)};
        Vector[] vectorSet18 = new Vector[] {new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<Vector> vectorList3 = Arrays.asList(vectorSet3);
        List<Vector> vectorList2 = Arrays.asList(vectorSet2);
        List<Vector> vectorList1 = Arrays.asList(vectorSet1);
        List<Vector> vectorList18 = Arrays.asList(vectorSet18);
        
        //list
        testAverageVectorList();
        
        //array
        testAverageVectorArray();
        
        //empty
        Assert.assertEquals(new Vector(), Vector.averageVector());
        
        //invalid
        
        try {
            List<Vector> list = new ArrayList<>(vectorList2);
            list.add(new Vector(8, 9, 1));
            Vector.averageVector(list);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector[] array = new Vector[4];
            System.arraycopy(vectorSet3, 0, array, 0, 3);
            array[3] = new Vector(8);
            Vector.averageVector(array);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector.averageVector(new Vector(), new Vector(8), new Vector(0.1), new Vector(5.6));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector.averageVector(Collections.singletonList(null));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            Vector.averageVector(null, null, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * Helper method for JUnit test of averageVector for list cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testAverageVectorList() throws Exception {
        Vector[] vectorSet3 = new Vector[] {new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)};
        Vector[] vectorSet2 = new Vector[] {new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)};
        Vector[] vectorSet1 = new Vector[] {new Vector(8), new Vector(0.1), new Vector(-5.6)};
        Vector[] vectorSet18 = new Vector[] {new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<Vector> vectorList3 = Arrays.asList(vectorSet3);
        List<Vector> vectorList2 = Arrays.asList(vectorSet2);
        List<Vector> vectorList1 = Arrays.asList(vectorSet1);
        List<Vector> vectorList18 = Arrays.asList(vectorSet18);
        
        //origin
        Assert.assertEquals(new Vector(4, 4.5, 0.5), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(8, 9, 1)), Collections.singletonList(Vector.origin(3)))));
        Assert.assertEquals(new Vector(-4, -4.5, -0.5), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(-8, -9, -1)), Collections.singletonList(Vector.origin(3)))));
        Assert.assertEquals(new Vector(0, 0, 0), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(0, 0, 0)), Collections.singletonList(Vector.origin(3)))));
        Assert.assertEquals(new Vector(-0.935, 44.03, -3.7), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(-1.87, 88.06, -7.4)), Collections.singletonList(Vector.origin(3)))));
        Assert.assertEquals(new Vector(0.90938061505, -4.08937731545, 3.72948801795), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(1.8187612301, -8.1787546309, 7.4589760359)), Collections.singletonList(Vector.origin(3)))));
        Assert.assertEquals(new Vector(0.9093806150539574, 4.089377315498734, 3.729488017993724), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)), Collections.singletonList(Vector.origin(3)))));
        Assert.assertEquals(new Vector(4.2E-10, 1.387E-15, 5.0E-13), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001)), Collections.singletonList(Vector.origin(3)))));
        Assert.assertEquals(new Vector(4, 0.5, 0.5, -3, 1.5, 2, 3.5, 0, -3, 0, 22, 4.5, -0.5, -2.5, 4, 3.5, 0, 1.5), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)), Collections.singletonList(Vector.origin(18)))));
        Assert.assertEquals(new Vector(1.5, 2.5), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(3, 5)), Collections.singletonList(Vector.origin(2)))));
        Assert.assertEquals(new Vector(0.935), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(1.87)), Collections.singletonList(Vector.origin(1)))));
        Assert.assertEquals(new Vector(0), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(0)), Collections.singletonList(Vector.origin(1)))));
        Assert.assertEquals(new Vector(), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector()), Collections.singletonList(Vector.origin(0)))));
        
        //list
        Assert.assertEquals(new Vector(2.6250000000000004, 3.1075, 1.54), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(8, 9, 1)), vectorList3)));
        Assert.assertEquals(new Vector(-1.375, -1.3925, 1.04), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(-8, -9, -1)), vectorList3)));
        Assert.assertEquals(new Vector(0.625, 0.8574999999999999, 1.29), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(0, 0, 0)), vectorList3)));
        Assert.assertEquals(new Vector(0.15749999999999997, 22.8725, -0.56), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(-1.87, 88.06, -7.4)), vectorList3)));
        Assert.assertEquals(new Vector(1.079690307525, -1.1871886577250002, 3.1547440089750003), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(1.8187612301, -8.1787546309, 7.4589760359)), vectorList3)));
        Assert.assertEquals(new Vector(1.0796903075269788, 2.9021886577493667, 3.154744008996862), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)), vectorList3)));
        Assert.assertEquals(new Vector(0.62500000021, 0.8575000000000006, 1.29000000000025), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001)), vectorList3)));
        Assert.assertEquals(new Vector(2.6250000000000004, 1.1075, 1.54, 1.25, 6.5, 4.575, 1.375, 4.4425, -2.35, 1.9725, 14.5, 0.7250000000000001, 2.2475, 2.375, 1.75, 1.75, 1.25, -1.025), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)), vectorList18)));
        Assert.assertEquals(new Vector(1.375, 2.1075), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(3, 5)), vectorList2)));
        Assert.assertEquals(new Vector(1.0925000000000002), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(1.87)), vectorList1)));
        Assert.assertEquals(new Vector(0.625), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector(0)), vectorList1)));
        Assert.assertEquals(new Vector(), Vector.averageVector(ListUtility.merge(Collections.singletonList(new Vector()), Collections.emptyList())));
    }
    
    /**
     * Helper method for JUnit test of averageVector for array cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testAverageVectorArray() throws Exception {
        Vector[] vectorSet3 = new Vector[] {new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)};
        Vector[] vectorSet2 = new Vector[] {new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)};
        Vector[] vectorSet1 = new Vector[] {new Vector(8), new Vector(0.1), new Vector(-5.6)};
        Vector[] vectorSet18 = new Vector[] {new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        
        //array
        Assert.assertEquals(new Vector(2.6250000000000004, 3.1075, 1.54), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(8, 9, 1)}, vectorSet3, Vector.class)));
        Assert.assertEquals(new Vector(-1.375, -1.3925, 1.04), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(-8, -9, -1)}, vectorSet3, Vector.class)));
        Assert.assertEquals(new Vector(0.625, 0.8574999999999999, 1.29), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(0, 0, 0)}, vectorSet3, Vector.class)));
        Assert.assertEquals(new Vector(0.15749999999999997, 22.8725, -0.56), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(-1.87, 88.06, -7.4)}, vectorSet3, Vector.class)));
        Assert.assertEquals(new Vector(1.079690307525, -1.1871886577250002, 3.1547440089750003), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(1.8187612301, -8.1787546309, 7.4589760359)}, vectorSet3, Vector.class)));
        Assert.assertEquals(new Vector(1.0796903075269788, 2.9021886577493667, 3.154744008996862), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)}, vectorSet3, Vector.class)));
        Assert.assertEquals(new Vector(0.62500000021, 0.8575000000000006, 1.29000000000025), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(0.00000000084, 0.000000000000002774, 0.000000000001)}, vectorSet3, Vector.class)));
        Assert.assertEquals(new Vector(2.6250000000000004, 1.1075, 1.54, 1.25, 6.5, 4.575, 1.375, 4.4425, -2.35, 1.9725, 14.5, 0.7250000000000001, 2.2475, 2.375, 1.75, 1.75, 1.25, -1.025), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)}, vectorSet18, Vector.class)));
        Assert.assertEquals(new Vector(1.375, 2.1075), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(3, 5)}, vectorSet2, Vector.class)));
        Assert.assertEquals(new Vector(1.0925000000000002), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(1.87)}, vectorSet1, Vector.class)));
        Assert.assertEquals(new Vector(0.625), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector(0)}, vectorSet1, Vector.class)));
        Assert.assertEquals(new Vector(), Vector.averageVector(ArrayUtility.merge(new Vector[] {new Vector()}, new Vector[] {}, Vector.class)));
        
        //set
        Assert.assertEquals(new Vector(2.6250000000000004, 3.1075, 1.54), Vector.averageVector(new Vector(8, 9, 1), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(-1.375, -1.3925, 1.04), Vector.averageVector(new Vector(-8, -9, -1), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(0.625, 0.8574999999999999, 1.29), Vector.averageVector(new Vector(0, 0, 0), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(0.15749999999999997, 22.8725, -0.56), Vector.averageVector(new Vector(-1.87, 88.06, -7.4), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(1.079690307525, -1.1871886577250002, 3.1547440089750003), Vector.averageVector(new Vector(1.8187612301, -8.1787546309, 7.4589760359), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(1.0796903075269788, 2.9021886577493667, 3.154744008996862), Vector.averageVector(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(0.62500000021, 0.8575000000000006, 1.29000000000025), Vector.averageVector(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)));
        Assert.assertEquals(new Vector(2.6250000000000004, 1.1075, 1.54, 1.25, 6.5, 4.575, 1.375, 4.4425, -2.35, 1.9725, 14.5, 0.7250000000000001, 2.2475, 2.375, 1.75, 1.75, 1.25, -1.025), Vector.averageVector(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)));
        Assert.assertEquals(new Vector(1.375, 2.1075), Vector.averageVector(new Vector(3, 5), new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)));
        Assert.assertEquals(new Vector(1.0925000000000002), Vector.averageVector(new Vector(1.87), new Vector(8), new Vector(0.1), new Vector(-5.6)));
        Assert.assertEquals(new Vector(0.625), Vector.averageVector(new Vector(0), new Vector(8), new Vector(0.1), new Vector(-5.6)));
        Assert.assertEquals(new Vector(), Vector.averageVector(new Vector()));
    }
    
    /**
     * JUnit test of calculateMinMax.
     *
     * @throws Exception When there is an exception.
     * @see Vector#calculateMinMax(List)
     * @see Vector#calculateMinMax(Vector...)
     * @see #testCalculateMinMaxList()
     * @see #testCalculateMinMaxArray()
     */
    @Test
    public void testCalculateMinMax() throws Exception {
        Vector[] vectorSet3 = new Vector[] {new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)};
        Vector[] vectorSet2 = new Vector[] {new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)};
        Vector[] vectorSet1 = new Vector[] {new Vector(8), new Vector(0.1), new Vector(-5.6)};
        Vector[] vectorSet18 = new Vector[] {new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<Vector> vectorList3 = Arrays.asList(vectorSet3);
        List<Vector> vectorList2 = Arrays.asList(vectorSet2);
        List<Vector> vectorList1 = Arrays.asList(vectorSet1);
        List<Vector> vectorList18 = Arrays.asList(vectorSet18);
        
        //list
        testCalculateMinMaxList();
        
        //array
        testCalculateMinMaxArray();
        
        //empty
        Assert.assertEquals(Collections.emptyList(), Vector.calculateMinMax());
        
        //invalid
        
        try {
            List<Vector> list = new ArrayList<>(vectorList2);
            list.add(new Vector(8, 9, 1));
            Vector.calculateMinMax(list);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector[] array = new Vector[4];
            System.arraycopy(vectorSet3, 0, array, 0, 3);
            array[3] = new Vector(8);
            Vector.calculateMinMax(array);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector.calculateMinMax(new Vector(), new Vector(8), new Vector(0.1), new Vector(5.6));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector.calculateMinMax(Collections.singletonList(null));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            Vector.calculateMinMax(null, null, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * Helper method for JUnit test of calculateMinMax for list cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testCalculateMinMaxList() throws Exception {
        Vector[] vectorSet3 = new Vector[] {new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)};
        Vector[] vectorSet2 = new Vector[] {new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)};
        Vector[] vectorSet1 = new Vector[] {new Vector(8), new Vector(0.1), new Vector(-5.6)};
        Vector[] vectorSet18 = new Vector[] {new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<Vector> vectorList3 = Arrays.asList(vectorSet3);
        List<Vector> vectorList2 = Arrays.asList(vectorSet2);
        List<Vector> vectorList1 = Arrays.asList(vectorSet1);
        List<Vector> vectorList18 = Arrays.asList(vectorSet18);
        
        //list
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 9), new Vector(-3, 9)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(8, 9, 1)), vectorList3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-8, 8), new Vector(-9, 4.1), new Vector(-3, 9)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(-8, -9, -1)), vectorList3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 4.1), new Vector(-3, 9)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(0, 0, 0)), vectorList3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 88.06), new Vector(-7.4, 9)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(-1.87, 88.06, -7.4)), vectorList3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-8.1787546309, 4.1), new Vector(-3, 9)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(1.8187612301, -8.1787546309, 7.4589760359)), vectorList3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 8.178754630997467), new Vector(-3, 9)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)), vectorList3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 4.1), new Vector(-3, 9)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001)), vectorList3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 4.1), new Vector(-3, 9), new Vector(-6, 10), new Vector(3, 9), new Vector(2, 6.3), new Vector(-3, 7), new Vector(0, 14), new Vector(-6, 3.6), new Vector(-4, 7), new Vector(2, 44), new Vector(-11, 9), new Vector(-1, 6), new Vector(-5, 5.5), new Vector(-5, 8), new Vector(-7, 7), new Vector(0, 3), new Vector(-8.1, 3)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)), vectorList18)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 5)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(3, 5)), vectorList2)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(1.87)), vectorList1)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8)}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector(0)), vectorList1)).toArray());
        Assert.assertArrayEquals(new Vector[] {}, Vector.calculateMinMax(ListUtility.merge(Collections.singletonList(new Vector()), Collections.emptyList())).toArray());
    }
    
    /**
     * Helper method for JUnit test of calculateMinMax for array cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testCalculateMinMaxArray() throws Exception {
        Vector[] vectorSet3 = new Vector[] {new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)};
        Vector[] vectorSet2 = new Vector[] {new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)};
        Vector[] vectorSet1 = new Vector[] {new Vector(8), new Vector(0.1), new Vector(-5.6)};
        Vector[] vectorSet18 = new Vector[] {new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        
        //array
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 9), new Vector(-3, 9)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(8, 9, 1)}, vectorSet3, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-8, 8), new Vector(-9, 4.1), new Vector(-3, 9)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(-8, -9, -1)}, vectorSet3, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 4.1), new Vector(-3, 9)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(0, 0, 0)}, vectorSet3, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 88.06), new Vector(-7.4, 9)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(-1.87, 88.06, -7.4)}, vectorSet3, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-8.1787546309, 4.1), new Vector(-3, 9)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(1.8187612301, -8.1787546309, 7.4589760359)}, vectorSet3, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 8.178754630997467), new Vector(-3, 9)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)}, vectorSet3, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 4.1), new Vector(-3, 9)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(0.00000000084, 0.000000000000002774, 0.000000000001)}, vectorSet3, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 4.1), new Vector(-3, 9), new Vector(-6, 10), new Vector(3, 9), new Vector(2, 6.3), new Vector(-3, 7), new Vector(0, 14), new Vector(-6, 3.6), new Vector(-4, 7), new Vector(2, 44), new Vector(-11, 9), new Vector(-1, 6), new Vector(-5, 5.5), new Vector(-5, 8), new Vector(-7, 7), new Vector(0, 3), new Vector(-8.1, 3)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)}, vectorSet18, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8), new Vector(-1.66, 5)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(3, 5)}, vectorSet2, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(1.87)}, vectorSet1, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8)}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector(0)}, vectorSet1, Vector.class)).toArray());
        Assert.assertArrayEquals(new Vector[] {}, Vector.calculateMinMax(ArrayUtility.merge(new Vector[] {new Vector()}, new Vector[] {}, Vector.class)).toArray());
        
        //set
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0), new Vector(-1.66, 9.0), new Vector(-3.0, 9.0)}, Vector.calculateMinMax(new Vector(8, 9, 1), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-8.0, 8.0), new Vector(-9.0, 4.1), new Vector(-3.0, 9.0)}, Vector.calculateMinMax(new Vector(-8, -9, -1), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0), new Vector(-1.66, 4.1), new Vector(-3.0, 9.0)}, Vector.calculateMinMax(new Vector(0, 0, 0), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0), new Vector(-1.66, 88.06), new Vector(-7.4, 9.0)}, Vector.calculateMinMax(new Vector(-1.87, 88.06, -7.4), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0), new Vector(-8.1787546309, 4.1), new Vector(-3.0, 9.0)}, Vector.calculateMinMax(new Vector(1.8187612301, -8.1787546309, 7.4589760359), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0), new Vector(-1.66, 8.178754630997467), new Vector(-3.0, 9.0)}, Vector.calculateMinMax(new Vector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0), new Vector(-1.66, 4.1), new Vector(-3.0, 9.0)}, Vector.calculateMinMax(new Vector(0.00000000084, 0.000000000000002774, 0.000000000001), new Vector(8, -1.66, 9), new Vector(0.1, 0.99, -0.84), new Vector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0), new Vector(-1.66, 4.1), new Vector(-3.0, 9.0), new Vector(-6.0, 10.0), new Vector(3.0, 9.0), new Vector(2.0, 6.3), new Vector(-3.0, 7.0), new Vector(0.0, 14.0), new Vector(-6.0, 3.6), new Vector(-4.0, 7.0), new Vector(2.0, 44.0), new Vector(-11.0, 9.0), new Vector(-1.0, 6.0), new Vector(-5.0, 5.5), new Vector(-5.0, 8.0), new Vector(-7.0, 7.0), new Vector(0.0, 3.0), new Vector(-8.1, 3.0)}, Vector.calculateMinMax(new Vector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new Vector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new Vector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new Vector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0), new Vector(-1.66, 5.0)}, Vector.calculateMinMax(new Vector(3, 5), new Vector(8, -1.66), new Vector(0.1, 0.99), new Vector(-5.6, 4.1)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0)}, Vector.calculateMinMax(new Vector(1.87), new Vector(8), new Vector(0.1), new Vector(-5.6)).toArray());
        Assert.assertArrayEquals(new Vector[] {new Vector(-5.6, 8.0)}, Vector.calculateMinMax(new Vector(0), new Vector(8), new Vector(0.1), new Vector(-5.6)).toArray());
        Assert.assertArrayEquals(new Vector[] {}, Vector.calculateMinMax(new Vector()).toArray());
    }
    
    /**
     * JUnit test of dimensionalityNotSameErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see Vector#dimensionalityNotSameErrorMessage(Vector, Vector)
     */
    @Test
    public void testDimensionalityNotSameErrorMessage() throws Exception {
        Assert.assertEquals(
                "The vectors: <5.0, 11.0, 2.0> and <8.0, 3.0, 2.0, 7.0> do not have the same dimensionality",
                Vector.dimensionalityNotSameErrorMessage(new Vector(5, 11, 2), new Vector(8, 3, 2, 7))
        );
        Assert.assertEquals(
                "The vectors: <0.1, 0.001, 1.0E-4> and <8.0E-9> do not have the same dimensionality",
                Vector.dimensionalityNotSameErrorMessage(new Vector(0.1, 0.001, 0.0001), new Vector(0.000000008))
        );
        Assert.assertEquals(
                "The vectors: <1.54, 7784500.0, 0.0, 3.0, 5.9> and <199.0, 211.0, 187.0> do not have the same dimensionality",
                Vector.dimensionalityNotSameErrorMessage(new Vector(1.54, 7784500, 0, 3, 5.9), new Vector(199, 211, 187))
        );
        Assert.assertEquals(
                "The vectors: <> and <19.0> do not have the same dimensionality",
                Vector.dimensionalityNotSameErrorMessage(new Vector(), new Vector(19))
        );
    }
    
    /**
     * JUnit test of dimensionalityNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see Vector#dimensionalityNotEqualErrorMessage(Vector, int)
     */
    @Test
    public void testDimensionalityNotEqualErrorMessage() throws Exception {
        Assert.assertEquals(
                "The vector: <5.0, 11.0, 2.0> does not have the expected dimensionality of: 2",
                Vector.dimensionalityNotEqualErrorMessage(new Vector(5, 11, 2), 2)
        );
        Assert.assertEquals(
                "The vector: <0.1, 0.001, 1.0E-4> does not have the expected dimensionality of: 8",
                Vector.dimensionalityNotEqualErrorMessage(new Vector(0.1, 0.001, 0.0001), 8)
        );
        Assert.assertEquals(
                "The vector: <1.54, 7784500.0, 0.0, 3.0, 5.9> does not have the expected dimensionality of: 3",
                Vector.dimensionalityNotEqualErrorMessage(new Vector(1.54, 7784500, 0, 3, 5.9), 3)
        );
        Assert.assertEquals(
                "The vector: <> does not have the expected dimensionality of: 1",
                Vector.dimensionalityNotEqualErrorMessage(new Vector(), 1)
        );
    }
    
    /**
     * JUnit test of dimensionalityMinimumNotMetErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see Vector#dimensionalityMinimumNotMetErrorMessage(Vector, int)
     */
    @Test
    public void testDimensionalityMinimumNotMetErrorMessage() throws Exception {
        Assert.assertEquals(
                "The vector: <5.0, 11.0, 2.0> does not have the minimum dimensionality of: 4",
                Vector.dimensionalityMinimumNotMetErrorMessage(new Vector(5, 11, 2), 4)
        );
        Assert.assertEquals(
                "The vector: <0.1, 0.001, 1.0E-4> does not have the minimum dimensionality of: 5",
                Vector.dimensionalityMinimumNotMetErrorMessage(new Vector(0.1, 0.001, 0.0001), 5)
        );
        Assert.assertEquals(
                "The vector: <1.54, 7784500.0, 0.0, 3.0, 5.9> does not have the minimum dimensionality of: 12",
                Vector.dimensionalityMinimumNotMetErrorMessage(new Vector(1.54, 7784500, 0, 3, 5.9), 12)
        );
        Assert.assertEquals(
                "The vector: <> does not have the minimum dimensionality of: 1",
                Vector.dimensionalityMinimumNotMetErrorMessage(new Vector(), 1)
        );
    }
    
    /**
     * JUnit test of componentIndexOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see Vector#componentIndexOutOfBoundsErrorMessage(Vector, int)
     */
    @Test
    public void testComponentIndexOutOfBoundsErrorMessage() throws Exception {
        Assert.assertEquals(
                "The vector: <5.0, 11.0, 2.0> does not have a component at index: 3",
                Vector.componentIndexOutOfBoundsErrorMessage(new Vector(5, 11, 2), 3)
        );
        Assert.assertEquals(
                "The vector: <0.1, 0.001, 1.0E-4> does not have a component at index: 8",
                Vector.componentIndexOutOfBoundsErrorMessage(new Vector(0.1, 0.001, 0.0001), 8)
        );
        Assert.assertEquals(
                "The vector: <1.54, 7784500.0, 0.0, 3.0, 5.9> does not have a component at index: -1",
                Vector.componentIndexOutOfBoundsErrorMessage(new Vector(1.54, 7784500, 0, 3, 5.9), -1)
        );
        Assert.assertEquals(
                "The vector: <> does not have a component at index: 0",
                Vector.componentIndexOutOfBoundsErrorMessage(new Vector(), 0)
        );
    }
    
    /**
     * JUnit test of componentRangeOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see Vector#componentRangeOutOfBoundsErrorMessage(Vector, int, int)
     */
    @Test
    public void testComponentRangeOutOfBoundsErrorMessage() throws Exception {
        Assert.assertEquals(
                "The range: [2,5) is out of bounds of the vector: <5.0, 11.0, 2.0>",
                Vector.componentRangeOutOfBoundsErrorMessage(new Vector(5, 11, 2), 2, 5)
        );
        Assert.assertEquals(
                "The range: [8,5) is out of bounds of the vector: <0.1, 0.001, 1.0E-4>",
                Vector.componentRangeOutOfBoundsErrorMessage(new Vector(0.1, 0.001, 0.0001), 8, 5)
        );
        Assert.assertEquals(
                "The range: [-1,2) is out of bounds of the vector: <1.54, 7784500.0, 0.0, 3.0, 5.9>",
                Vector.componentRangeOutOfBoundsErrorMessage(new Vector(1.54, 7784500, 0, 3, 5.9), -1, 2)
        );
        Assert.assertEquals(
                "The range: [0,0) is out of bounds of the vector: <>",
                Vector.componentRangeOutOfBoundsErrorMessage(new Vector(), 0, 0)
        );
    }
    
}
