/*
 * File:    Vector2Test.java
 * Package: commons.math.vector
 * Author:  Zachary Gill
 */

package commons.math.vector;

import java.util.Arrays;
import java.util.List;

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
 * JUnit test of Vector2.
 *
 * @see Vector2
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Vector2.class})
public class Vector2Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Vector2Test.class);
    
    
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
     */
    @Test
    public void testConstructor() throws Exception {
        Vector testVector;
        
        //components
        Vector2 vector = new Vector2(8.6, 4.11);
        Assert.assertArrayEquals(new double[] {8.6, 4.11}, vector.components, TestUtils.DELTA);
        
        //vector 1D
        testVector = new Vector(8.6);
        Vector2 vector1 = new Vector2(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 0}, vector1.components, TestUtils.DELTA);
        
        //vector 2D
        testVector = new Vector(8.6, 4.11);
        Vector2 vector2 = new Vector2(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11}, vector2.components, TestUtils.DELTA);
        
        //vector 3D
        testVector = new Vector(8.6, 4.11, 0.4497);
        Vector2 vector3 = new Vector2(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11}, vector3.components, TestUtils.DELTA);
        
        //vector 4D
        testVector = new Vector(8.6, 4.11, 0.4497, 11.0791);
        Vector2 vector4 = new Vector2(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11}, vector4.components, TestUtils.DELTA);
        
        //vector 5D
        testVector = new Vector(8.6, 4.11, 1.87, 0.4497, 5.73);
        Vector2 vector5 = new Vector2(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11}, vector5.components, TestUtils.DELTA);
        
        //equality
        Assert.assertEquals(vector, vector2);
        Assert.assertEquals(vector2, vector3);
        Assert.assertEquals(vector3, vector4);
        Assert.assertEquals(vector4, vector5);
    }
    
    /**
     * JUnit test of dotFlop.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#dotFlop(Vector, Vector)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDotFlop() throws Exception {
        Vector2 vector1 = new Vector2(8.6, -4.11);
        Vector2 vector2 = new Vector2(-0.187463, 18.2);
        Vector vector3 = new Vector(0, 14);
        Vector vector4 = new Vector(14, 0);
        List<Vector> vectorList = Arrays.asList(vector1, vector2, vector3, vector4);
        
        //standard
        Assert.assertArrayEquals(new double[] {73.1898182, 157.29047293}, Vector2.dotFlop(vector1, vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {57.54, 120.4}, Vector2.dotFlop(vector1, vector3).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {120.4, -57.54}, Vector2.dotFlop(vector1, vector4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {73.1898182, 157.29047293}, Vector2.dotFlop(vector2, vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-254.8, -2.624482}, Vector2.dotFlop(vector2, vector3).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-2.624482, 254.8}, Vector2.dotFlop(vector2, vector4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {57.54, 120.4}, Vector2.dotFlop(vector3, vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-254.8, -2.624482}, Vector2.dotFlop(vector3, vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, 196}, Vector2.dotFlop(vector3, vector4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {120.4, -57.54}, Vector2.dotFlop(vector4, vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-2.624482, 254.8}, Vector2.dotFlop(vector4, vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, 196}, Vector2.dotFlop(vector4, vector3).getComponents(), TestUtils.DELTA);
        
        //equality
        for (int i = 0; i < vectorList.size(); i++) {
            for (int j = vectorList.size() - 1; j >= 0; j--) {
                Assert.assertEquals(
                        Vector2.dotFlop(vectorList.get(i), vectorList.get(j)),
                        Vector2.dotFlop(vectorList.get(j), vectorList.get(i))
                );
            }
        }
        
        //invalid
        
        try {
            Vector2.dotFlop(new Vector(1, 1), new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.dotFlop(new Vector(1), new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.dotFlop(new Vector(1, 1, 1), new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.dotFlop(new Vector(1, 1), new Vector(1, 1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.dotFlop(new Vector(1, 1), null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            Vector2.dotFlop(null, new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            Vector2.dotFlop(null, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of dotFlopNegative.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#dotFlopNegative(Vector, Vector)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDotFlopNegative() throws Exception {
        Vector2 vector1 = new Vector2(8.6, -4.11);
        Vector2 vector2 = new Vector2(-0.187463, 18.2);
        Vector vector3 = new Vector(0, 14);
        Vector vector4 = new Vector(14, 0);
        List<Vector> vectorList = Arrays.asList(vector1, vector2, vector3, vector4);
        
        //standard
        Assert.assertArrayEquals(new double[] {-76.4141818, 155.74952707}, Vector2.dotFlopNegative(vector1, vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-57.54, 120.4}, Vector2.dotFlopNegative(vector1, vector3).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {120.4, 57.54}, Vector2.dotFlopNegative(vector1, vector4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-76.4141818, -155.74952707}, Vector2.dotFlopNegative(vector2, vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {254.8, -2.624482}, Vector2.dotFlopNegative(vector2, vector3).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-2.624482, -254.8}, Vector2.dotFlopNegative(vector2, vector4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-57.54, -120.4}, Vector2.dotFlopNegative(vector3, vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {254.8, 2.624482}, Vector2.dotFlopNegative(vector3, vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, -196}, Vector2.dotFlopNegative(vector3, vector4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {120.4, -57.54}, Vector2.dotFlopNegative(vector4, vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-2.624482, 254.8}, Vector2.dotFlopNegative(vector4, vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, 196}, Vector2.dotFlopNegative(vector4, vector3).getComponents(), TestUtils.DELTA);
        
        //equality
        for (int i = 0; i < vectorList.size(); i++) {
            for (int j = vectorList.size() - 1; j >= 0; j--) {
                Assert.assertEquals(
                        Vector2.dotFlopNegative(vectorList.get(i), vectorList.get(j)),
                        Vector2.dotFlopNegative(vectorList.get(j), vectorList.get(i)).times(new Vector(1, -1))
                );
            }
        }
        
        //invalid
        
        try {
            Vector2.dotFlopNegative(new Vector(1, 1), new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.dotFlopNegative(new Vector(1), new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.dotFlopNegative(new Vector(1, 1, 1), new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.dotFlopNegative(new Vector(1, 1), new Vector(1, 1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.dotFlopNegative(new Vector(1, 1), null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            Vector2.dotFlopNegative(null, new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            Vector2.dotFlopNegative(null, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#squareSum(Vector)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSquareSum() throws Exception {
        Vector2 vector1 = new Vector2(8.6, 4.11);
        Vector2 vector2 = new Vector2(0.187463, 18.2);
        Vector2 vector3 = new Vector2(0, 14);
        Vector2 vector4 = new Vector2(14, 0);
        Vector2 vector5 = new Vector2(0, 0);
        
        //standard
        Assert.assertEquals(90.8521, Vector2.squareSum(vector1), TestUtils.DELTA);
        Assert.assertEquals(331.27514237636893, Vector2.squareSum(vector2), TestUtils.DELTA);
        Assert.assertEquals(196, Vector2.squareSum(vector3), TestUtils.DELTA);
        Assert.assertEquals(196, Vector2.squareSum(vector4), TestUtils.DELTA);
        Assert.assertEquals(0, Vector2.squareSum(vector5), TestUtils.DELTA);
        
        //equality
        Assert.assertEquals(Vector2.squareSum(vector3), Vector2.squareSum(vector4), TestUtils.DELTA);
        
        //invalid
        
        try {
            Vector2.squareSum(new Vector(1, 1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.squareSum(new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.squareSum(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of squareDifference.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#squareDifference(Vector)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSquareDifference() throws Exception {
        Vector2 vector1 = new Vector2(8.6, 4.11);
        Vector2 vector2 = new Vector2(0.187463, 18.2);
        Vector2 vector3 = new Vector2(0, 14);
        Vector2 vector4 = new Vector2(14, 0);
        Vector2 vector5 = new Vector2(0, 0);
        
        //standard
        Assert.assertEquals(57.0679, Vector2.squareDifference(vector1), TestUtils.DELTA);
        Assert.assertEquals(-331.20485762363097, Vector2.squareDifference(vector2), TestUtils.DELTA);
        Assert.assertEquals(-196, Vector2.squareDifference(vector3), TestUtils.DELTA);
        Assert.assertEquals(196, Vector2.squareDifference(vector4), TestUtils.DELTA);
        Assert.assertEquals(0, Vector2.squareDifference(vector5), TestUtils.DELTA);
        
        //equality
        Assert.assertEquals(Vector2.squareDifference(vector3), Vector2.squareDifference(vector4) * -1, TestUtils.DELTA);
        
        //invalid
        
        try {
            Vector2.squareDifference(new Vector(1, 1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.squareDifference(new Vector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            Vector2.squareDifference(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Vector2#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        Vector2 vector = new Vector2(8, 4);
        Assert.assertEquals(2, vector.getDimensionality());
        Assert.assertEquals(2, vector.getDimensionality());
    }
    
}
