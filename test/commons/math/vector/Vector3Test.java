/*
 * File:    Vector3Test.java
 * Package: commons.math.vector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
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
 * JUnit test of Vector3.
 *
 * @see Vector3
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Vector3.class})
public class Vector3Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Vector3Test.class);
    
    
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
     */
    @Test
    public void testConstructor() throws Exception {
        Vector testVector;
        
        //components
        Vector3 vector = new Vector3(8.6, 4.11, 1.87);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87}, vector.components, TestUtils.DELTA);
        
        //vector 1D
        testVector = new Vector(8.6);
        Vector3 vector1 = new Vector3(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 0, 0}, vector1.components, TestUtils.DELTA);
        
        //vector 2D
        testVector = new Vector(8.6, 4.11);
        Vector3 vector2 = new Vector3(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 0}, vector2.components, TestUtils.DELTA);
        
        //vector 3D
        testVector = new Vector(8.6, 4.11, 1.87);
        Vector3 vector3 = new Vector3(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87}, vector3.components, TestUtils.DELTA);
        
        //vector 4D
        testVector = new Vector(8.6, 4.11, 1.87, 0.4497);
        Vector3 vector4 = new Vector3(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87}, vector4.components, TestUtils.DELTA);
        
        //vector 5D
        testVector = new Vector(8.6, 4.11, 1.87, 0.4497, 5.73);
        Vector3 vector5 = new Vector3(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87}, vector5.components, TestUtils.DELTA);
        
        //vector 2D and component
        Vector3 vector2x = new Vector3(new Vector2(8.6, 4.11), 1.87);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87}, vector2x.components, TestUtils.DELTA);
        
        //equality
        Assert.assertEquals(vector, vector3);
        Assert.assertEquals(vector3, vector4);
        Assert.assertEquals(vector4, vector5);
        Assert.assertEquals(vector5, vector2x);
    }
    
    /**
     * JUnit test of cross.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#cross(Vector)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testCross() throws Exception {
        Vector3 vector1 = new Vector3(8.6, -4.11, 1.87);
        Vector3 vector2 = new Vector3(-0.187463, 18.2, -0.9784);
        Vector3 vector3 = new Vector3(0, 0, 14);
        Vector3 vector4 = new Vector3(0, 14, 0);
        Vector3 vector5 = new Vector3(14, 0, 0);
        List<Vector3> vectorList = Arrays.asList(vector1, vector2, vector3, vector4, vector5);
        
        //standard
        Assert.assertArrayEquals(new double[] {-30.012776, 8.06368419, 155.74952707}, vector1.cross(vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-57.54, -120.4, 0}, vector1.cross(vector3).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-26.18, 0, 120.4}, vector1.cross(vector4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, 26.18, 57.54}, vector1.cross(vector5).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {30.012776, -8.06368419, -155.74952707}, vector2.cross(vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {254.8, 2.624482, 0}, vector2.cross(vector3).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {13.6976, 0, -2.624482}, vector2.cross(vector4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, -13.6976, -254.8}, vector2.cross(vector5).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {57.54, 120.4, 0}, vector3.cross(vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-254.8, -2.624482, 0}, vector3.cross(vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-196, 0, 0}, vector3.cross(vector4).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, 196, 0}, vector3.cross(vector5).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {26.18, 0, -120.4}, vector4.cross(vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {-13.6976, 0, 2.624482}, vector4.cross(vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {196, 0, 0}, vector4.cross(vector3).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, 0, -196}, vector4.cross(vector5).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, -26.18, -57.54}, vector5.cross(vector1).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, 13.6976, 254.8}, vector5.cross(vector2).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, -196, 0}, vector5.cross(vector3).getComponents(), TestUtils.DELTA);
        Assert.assertArrayEquals(new double[] {0, 0, 196}, vector5.cross(vector4).getComponents(), TestUtils.DELTA);
        
        //equality
        for (int i = 0; i < vectorList.size(); i++) {
            for (int j = vectorList.size() - 1; j >= 0; j--) {
                Assert.assertEquals(
                        vectorList.get(i).cross(vectorList.get(j)),
                        vectorList.get(i).cross(new Vector(vectorList.get(j)))
                );
                Assert.assertEquals(
                        vectorList.get(i).cross(vectorList.get(j)),
                        vectorList.get(j).cross(vectorList.get(i)).times(new Vector(-1, -1, -1))
                );
            }
        }
        
        //invalid
        
        try {
            new Vector3(1, 1, 1).cross(new Vector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector3(1, 1, 1).cross(new Vector(1, 1, 1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new Vector3(1, 1, 1).cross(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Vector3#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        Vector3 vector = new Vector3(8, 4, 2);
        Assert.assertEquals(3, vector.getDimensionality());
        Assert.assertEquals(3, vector.getDimensionality());
    }
    
}
