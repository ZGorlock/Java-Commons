/*
 * File:    Vector4Test.java
 * Package: commons.math.vector
 * Author:  Zachary Gill
 */

package commons.math.vector;

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
 * JUnit test of Vector4.
 *
 * @see Vector4
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Vector4.class})
public class Vector4Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Vector4Test.class);
    
    
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
     */
    @Test
    public void testConstructor() throws Exception {
        Vector testVector;
        
        //components
        Vector4 vector = new Vector4(8.6, 4.11, 1.87, 0.4497);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87, 0.4497}, vector.components, TestUtils.DELTA);
        
        //vector 1D
        testVector = new Vector(8.6);
        Vector4 vector1 = new Vector4(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 0, 0, 0}, vector1.components, TestUtils.DELTA);
        
        //vector 2D
        testVector = new Vector(8.6, 4.11);
        Vector4 vector2 = new Vector4(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 0, 0}, vector2.components, TestUtils.DELTA);
        
        //vector 3D
        testVector = new Vector(8.6, 4.11, 1.87);
        Vector4 vector3 = new Vector4(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87, 0}, vector3.components, TestUtils.DELTA);
        
        //vector 4D
        testVector = new Vector(8.6, 4.11, 1.87, 0.4497);
        Vector4 vector4 = new Vector4(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87, 0.4497}, vector4.components, TestUtils.DELTA);
        
        //vector 5D
        testVector = new Vector(8.6, 4.11, 1.87, 0.4497, 5.73);
        Vector4 vector5 = new Vector4(testVector);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87, 0.4497}, vector5.components, TestUtils.DELTA);
        
        //vector 3D and component
        Vector4 vector3x = new Vector4(new Vector3(8.6, 4.11, 1.87), 0.4497);
        Assert.assertArrayEquals(new double[] {8.6, 4.11, 1.87, 0.4497}, vector3x.components, TestUtils.DELTA);
        
        //equality
        Assert.assertEquals(vector, vector4);
        ;
        Assert.assertEquals(vector4, vector5);
        Assert.assertEquals(vector5, vector3x);
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Vector4#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        Vector4 vector = new Vector4(8, 4, 2, 6);
        Assert.assertEquals(4, vector.getDimensionality());
        Assert.assertEquals(4, vector.getDimensionality());
    }
    
}
