/*
 * File:    RotationUtilityTest.java
 * Package: commons.math
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math;

import commons.math.component.matrix.Matrix3;
import commons.math.component.vector.Vector;
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
 * JUnit test of RotationUtility.
 *
 * @see RotationUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({RotationUtility.class})
public class RotationUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RotationUtilityTest.class);
    
    
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
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of getRotationMatrix.
     *
     * @throws Exception When there is an exception.
     * @see RotationUtility#getRotationMatrix(double, double, double)
     */
    @Test
    public void testGetRotationMatrix() throws Exception {
        Assert.assertEquals(
                new Matrix3(
                        1, 0, 0,
                        0, 1, 0,
                        0, 0, 1
                ), RotationUtility.getRotationMatrix(0, 0, 0)
        );
        Assert.assertEquals(
                new Matrix3(
                        1, 0, 0,
                        0, -1, 0,
                        0, 0, -1
                ), RotationUtility.getRotationMatrix(Math.PI, 0, 0)
        );
        Assert.assertEquals(
                new Matrix3(
                        -1, 0, 0,
                        0, 1, 0,
                        0, 0, -1
                ), RotationUtility.getRotationMatrix(0, Math.PI, 0)
        );
        Assert.assertEquals(
                new Matrix3(
                        -1, 0, 0,
                        0, -1, 0,
                        0, 0, 1
                ), RotationUtility.getRotationMatrix(0, 0, Math.PI)
        );
        Assert.assertEquals(
                new Matrix3(
                        1, 0, 0,
                        0, -1, 0,
                        0, 0, -1
                ), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI)
        );
        Assert.assertEquals(
                new Matrix3(
                        -1, 0, 0,
                        0, -1, 0,
                        0, 0, 1
                ), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0)
        );
        Assert.assertEquals(
                new Matrix3(
                        -1, 0, 0,
                        0, 1, 0,
                        0, 0, -1
                ), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI)
        );
        Assert.assertEquals(
                new Matrix3(
                        1, 0, 0,
                        0, 1, 0,
                        0, 0, 1
                ), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI)
        );
        Assert.assertEquals(
                new Matrix3(
                        1, 0, 0,
                        0, 1, 0,
                        0, 0, 1
                ), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI)
        );
        Assert.assertEquals(
                new Matrix3(
                        1, 0, 0,
                        0, 0, -1,
                        0, 1, 0
                ), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0)
        );
        Assert.assertEquals(
                new Matrix3(
                        0, 0, 1,
                        0, 1, 0,
                        -1, 0, 0
                ), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0)
        );
        Assert.assertEquals(
                new Matrix3(
                        0, -1, 0,
                        1, 0, 0,
                        0, 0, 1
                ), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2)
        );
        Assert.assertEquals(
                new Matrix3(
                        0, -1, 0,
                        0, 0, 1,
                        -1, 0, 0
                ), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2)
        );
        Assert.assertEquals(
                new Matrix3(
                        -0.07755203967697322, 0.4192832826803205, 0.904537014171756,
                        0.9091279311540784, -0.34266633146647285, 0.23678300208168407,
                        0.4092335347175823, 0.8407028691185273, -0.3546076704167996
                ), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879)
        );
        Assert.assertEquals(
                new Matrix3(
                        -0.07755203967697322, -0.4192832826803205, -0.904537014171756,
                        -0.7264123201285896, 0.6451813319452019, -0.23678300208168407,
                        0.6828695499927792, 0.6387038263344206, -0.3546076704167996
                ), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879)
        );
    }
    
    /**
     * JUnit test of performRotation.
     *
     * @throws Exception When there is an exception.
     * @see RotationUtility#performRotation(Vector, Matrix3, Vector)
     * @see #testPerformRotaionSingleCoordinate()
     * @see #testPerformRotaionMultipleCoordinate()
     * @see #testPerformRotaionSpecial()
     */
    @Test
    public void testPerformRotation() throws Exception {
        testPerformRotaionSingleCoordinate();
        testPerformRotaionMultipleCoordinate();
        testPerformRotaionSpecial();
    }
    
    /**
     * Helper method for JUnit test of performRotation for single coordinate cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testPerformRotaionSingleCoordinate() throws Exception {
        //x
        
        Assert.assertEquals(new Vector(1, 0, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 0), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 0), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0.07755203967697322, -0.4192832826803205, -0.904537014171756), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-0.07755203967697322, -0.4192832826803205, -0.904537014171756), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(0, 0, 0)));
        
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 2, 2), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 0, 2), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 2, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-1, 2, 2), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 2, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 0, 2), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 0, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 0, 2), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 0, -1), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 3, 0), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-0.3183614658716607, 0.5019634623479455, 1.1178246683351154), RotationUtility.performRotation(new Vector(1, 0, 0), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1.1986468494897569, 0.5546814070810185, 3.4004647008419955), RotationUtility.performRotation(new Vector(-1, 0, 0), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(1, 1, 1)));
        
        //y
        
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, 0), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-0.9091279311540784, 0.34266633146647285, -0.23678300208168407), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-0.7264123201285896, 0.6451813319452019, -0.23678300208168407), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(0, 0, 0)));
        
        Assert.assertEquals(new Vector(0, -1, 0), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, 2), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, -1, 2), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 1, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 3, 2), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 1, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, -1, 2), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, -1, 0), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 0, 1), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, -1, 0), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 2, 0), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 2, -1), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0.6683185049593909, -0.25998615179884776, 0.4500706562450436), RotationUtility.performRotation(new Vector(0, 1, 0), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1.8475071299413734, -0.5097832075445039, 2.732710688751924), RotationUtility.performRotation(new Vector(0, -1, 0), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(1, 1, 1)));
        
        //z
        
        Assert.assertEquals(new Vector(0, 0, 1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, 0), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-0.4092335347175823, -0.8407028691185273, 0.3546076704167996), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0.6828695499927792, 0.6387038263344206, -0.3546076704167996), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(0, 0, 0)));
        
        Assert.assertEquals(new Vector(0, 0, -1), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 2, 1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 0, 3), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 2, 1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 2, 3), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 2, 1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 0, 3), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 0, 1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 0, -1), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, 2), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 0, 0), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 2, 1), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 2, 0), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0.1684241085228948, 0.9233830487861523, -0.14132001625344004), RotationUtility.performRotation(new Vector(0, 0, 1), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0.4382252598200045, -0.5033057019337226, 2.8505353570870393), RotationUtility.performRotation(new Vector(0, 0, -1), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(1, 1, 1)));
    }
    
    /**
     * Helper method for JUnit test of performRotation for multiple coordinate cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testPerformRotaionMultipleCoordinate() throws Exception {
        //xy
        
        Assert.assertEquals(new Vector(1, 1, 0), RotationUtility.performRotation(new Vector(1, 1, 0), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 1, 0), RotationUtility.performRotation(new Vector(1, -1, 0), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 1, 0), RotationUtility.performRotation(new Vector(-1, 1, 0), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 1, 0), RotationUtility.performRotation(new Vector(-1, -1, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, -1, 0), RotationUtility.performRotation(new Vector(1, 1, 0), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 1, 0), RotationUtility.performRotation(new Vector(1, -1, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 1, 0), RotationUtility.performRotation(new Vector(-1, 1, 0), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, -1, 0), RotationUtility.performRotation(new Vector(-1, -1, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 1, 0), RotationUtility.performRotation(new Vector(1, 1, 0), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 1), RotationUtility.performRotation(new Vector(1, -1, 0), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, -1), RotationUtility.performRotation(new Vector(-1, 1, 0), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 1, 0), RotationUtility.performRotation(new Vector(-1, -1, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, 1), RotationUtility.performRotation(new Vector(1, 1, 0), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-0.9866799708310516, 0.7619496141467934, 0.667754012090072), RotationUtility.performRotation(new Vector(1, -1, 0), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-0.6488602804516164, 1.0644646146255223, 0.667754012090072), RotationUtility.performRotation(new Vector(-1, 1, 0), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(0, 0, 0)));
        
        Assert.assertEquals(new Vector(-1, -1, 0), RotationUtility.performRotation(new Vector(-1, -1, 0), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 1, 2), RotationUtility.performRotation(new Vector(1, 1, 0), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, -1, 2), RotationUtility.performRotation(new Vector(-1, -1, 0), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 1, 0), RotationUtility.performRotation(new Vector(-1, 1, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 3, 2), RotationUtility.performRotation(new Vector(1, -1, 0), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 1, 0), RotationUtility.performRotation(new Vector(1, 1, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, -1, 2), RotationUtility.performRotation(new Vector(-1, -1, 0), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-1, 1, 0), RotationUtility.performRotation(new Vector(-1, 1, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, -1, 0), RotationUtility.performRotation(new Vector(1, -1, 0), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 0, 1), RotationUtility.performRotation(new Vector(1, 1, 0), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, -1, -1), RotationUtility.performRotation(new Vector(-1, -1, 0), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 3, 0), RotationUtility.performRotation(new Vector(-1, 1, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 1, -1), RotationUtility.performRotation(new Vector(1, -1, 0), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0.5907664652824177, 0.15929713088147268, 1.3546076704167995), RotationUtility.performRotation(new Vector(1, 1, 0), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1.9250591696183466, -0.09049992486418335, 3.6372477029236796), RotationUtility.performRotation(new Vector(-1, -1, 0), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(1, 1, 1)));
        
        //xz
        
        Assert.assertEquals(new Vector(1, 0, 1), RotationUtility.performRotation(new Vector(1, 0, 1), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 1), RotationUtility.performRotation(new Vector(1, 0, -1), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, -1), RotationUtility.performRotation(new Vector(-1, 0, 1), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, -1), RotationUtility.performRotation(new Vector(-1, 0, -1), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, -1), RotationUtility.performRotation(new Vector(1, 0, 1), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, -1), RotationUtility.performRotation(new Vector(1, 0, -1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, -1), RotationUtility.performRotation(new Vector(-1, 0, 1), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, -1), RotationUtility.performRotation(new Vector(-1, 0, -1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 1), RotationUtility.performRotation(new Vector(1, 0, 1), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, -1, 0), RotationUtility.performRotation(new Vector(1, 0, -1), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, -1), RotationUtility.performRotation(new Vector(-1, 0, 1), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, -1), RotationUtility.performRotation(new Vector(-1, 0, -1), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, -1, 0), RotationUtility.performRotation(new Vector(1, 0, 1), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-0.4867855743945555, -0.4214195864382068, 1.2591446845885557), RotationUtility.performRotation(new Vector(1, 0, -1), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0.7604215896697524, 1.057987109014741, 0.5499293437549564), RotationUtility.performRotation(new Vector(-1, 0, 1), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(0, 0, 0)));
        
        Assert.assertEquals(new Vector(-1, 0, -1), RotationUtility.performRotation(new Vector(-1, 0, -1), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 2, 1), RotationUtility.performRotation(new Vector(1, 0, 1), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 0, 3), RotationUtility.performRotation(new Vector(-1, 0, -1), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 2, 1), RotationUtility.performRotation(new Vector(-1, 0, 1), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 2, 3), RotationUtility.performRotation(new Vector(1, 0, -1), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 2, 1), RotationUtility.performRotation(new Vector(1, 0, 1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 0, 3), RotationUtility.performRotation(new Vector(-1, 0, -1), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-1, 0, 1), RotationUtility.performRotation(new Vector(-1, 0, 1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 0, -1), RotationUtility.performRotation(new Vector(1, 0, -1), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 1, 2), RotationUtility.performRotation(new Vector(1, 0, 1), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 0, -1), RotationUtility.performRotation(new Vector(-1, 0, -1), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 3, 1), RotationUtility.performRotation(new Vector(-1, 0, 1), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 1, 0), RotationUtility.performRotation(new Vector(1, 0, -1), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0.09087206884592158, 1.3426663314664729, 0.7632169979183159), RotationUtility.performRotation(new Vector(1, 0, 1), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0.5157772994969777, -0.08402241925340204, 3.755072371258795), RotationUtility.performRotation(new Vector(-1, 0, -1), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(1, 1, 1)));
        
        //yz
        
        Assert.assertEquals(new Vector(0, 1, 1), RotationUtility.performRotation(new Vector(0, 1, 1), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, 1), RotationUtility.performRotation(new Vector(0, 1, -1), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, -1), RotationUtility.performRotation(new Vector(0, -1, 1), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, -1), RotationUtility.performRotation(new Vector(0, -1, -1), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, -1), RotationUtility.performRotation(new Vector(0, 1, 1), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, -1), RotationUtility.performRotation(new Vector(0, 1, -1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, -1), RotationUtility.performRotation(new Vector(0, -1, 1), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, -1), RotationUtility.performRotation(new Vector(0, -1, -1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 1, 1), RotationUtility.performRotation(new Vector(0, 1, 1), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, -1, -1), RotationUtility.performRotation(new Vector(0, 1, -1), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, -1, 0), RotationUtility.performRotation(new Vector(0, -1, 1), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, -1), RotationUtility.performRotation(new Vector(0, -1, -1), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 1), RotationUtility.performRotation(new Vector(0, 1, 1), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0.49989439643649614, -1.183369200585, 0.5913906724984836), RotationUtility.performRotation(new Vector(0, 1, -1), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1.4092818701213687, -0.006477505610781309, -0.11782466833511551), RotationUtility.performRotation(new Vector(0, -1, 1), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(0, 0, 0)));
        
        Assert.assertEquals(new Vector(0, -1, -1), RotationUtility.performRotation(new Vector(0, -1, -1), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, 1), RotationUtility.performRotation(new Vector(0, 1, 1), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, -1, 3), RotationUtility.performRotation(new Vector(0, -1, -1), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 3, 1), RotationUtility.performRotation(new Vector(0, -1, 1), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, 3), RotationUtility.performRotation(new Vector(0, 1, -1), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 1, 1), RotationUtility.performRotation(new Vector(0, 1, 1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, -1, 3), RotationUtility.performRotation(new Vector(0, -1, -1), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, -1, 1), RotationUtility.performRotation(new Vector(0, -1, 1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, -1), RotationUtility.performRotation(new Vector(0, 1, -1), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, 1), RotationUtility.performRotation(new Vector(0, 1, 1), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, -1, 0), RotationUtility.performRotation(new Vector(0, -1, -1), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-1, 2, 1), RotationUtility.performRotation(new Vector(0, -1, 1), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 2, 1), RotationUtility.performRotation(new Vector(0, 1, -1), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1.077552039676973, 0.5807167173196794, 0.09546298582824397), RotationUtility.performRotation(new Vector(0, 1, 1), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1.1646375799485942, -1.1484870338789244, 3.0873183591687234), RotationUtility.performRotation(new Vector(0, -1, -1), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(1, 1, 1)));
        
        //xyz
        
        Assert.assertEquals(new Vector(1, 1, 1), RotationUtility.performRotation(new Vector(1, 1, 1), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, -1, 1), RotationUtility.performRotation(new Vector(1, 1, -1), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, -1, -1), RotationUtility.performRotation(new Vector(1, -1, 1), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 1, -1), RotationUtility.performRotation(new Vector(1, -1, -1), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, -1, -1), RotationUtility.performRotation(new Vector(-1, 1, 1), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, -1, -1), RotationUtility.performRotation(new Vector(-1, 1, -1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, -1, -1), RotationUtility.performRotation(new Vector(-1, -1, 1), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, -1, -1), RotationUtility.performRotation(new Vector(-1, -1, -1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 1, 1), RotationUtility.performRotation(new Vector(1, 1, 1), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, -1, -1), RotationUtility.performRotation(new Vector(1, 1, -1), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, -1, 1), RotationUtility.performRotation(new Vector(1, -1, 1), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, -1, -1), RotationUtility.performRotation(new Vector(1, -1, -1), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-1, 1, 1), RotationUtility.performRotation(new Vector(-1, 1, 1), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0.5774464361134694, -1.6026524832653206, -0.3131463416732724), RotationUtility.performRotation(new Vector(-1, 1, -1), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1.4868339097983418, 0.4128057770695392, 0.7867123458366405), RotationUtility.performRotation(new Vector(-1, -1, 1), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(0, 0, 0)));
        
        Assert.assertEquals(new Vector(-1, -1, -1), RotationUtility.performRotation(new Vector(-1, -1, -1), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 1, 1), RotationUtility.performRotation(new Vector(1, 1, 1), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, -1, 3), RotationUtility.performRotation(new Vector(1, -1, -1), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 3, 1), RotationUtility.performRotation(new Vector(1, -1, 1), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 1, 3), RotationUtility.performRotation(new Vector(1, 1, -1), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 1, 1), RotationUtility.performRotation(new Vector(-1, 1, 1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, -1, 3), RotationUtility.performRotation(new Vector(-1, -1, -1), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-1, -1, 1), RotationUtility.performRotation(new Vector(-1, -1, 1), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-1, 1, -1), RotationUtility.performRotation(new Vector(-1, 1, -1), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 1, 1), RotationUtility.performRotation(new Vector(1, 1, 1), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, -1, 1), RotationUtility.performRotation(new Vector(1, -1, -1), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-1, 1, 1), RotationUtility.performRotation(new Vector(1, -1, 1), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(3, 1, 1), RotationUtility.performRotation(new Vector(1, 1, -1), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1.1551040793539464, 0.161433434639359, -0.809074028343512), RotationUtility.performRotation(new Vector(-1, 1, 1), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1.2421896196255673, -0.7292037511986038, 3.991855373340479), RotationUtility.performRotation(new Vector(-1, -1, -1), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(1, 1, 1)));
    }
    
    /**
     * Helper method for JUnit test of performRotation for special cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testPerformRotaionSpecial() throws Exception {
        //zero
        
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(0, 0, 0)));
        
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 2, 2), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 0, 2), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 2, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 2, 2), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 2, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 0, 2), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 0, 2), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 0, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(0, 2, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(2, 2, 0), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-0.2408094261946876, 0.08268017966762509, 0.21328765416335949), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1.1210948098127838, 0.1353981244006981, 2.49592768667024), RotationUtility.performRotation(new Vector(0, 0, 0), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(1, 1, 1)));
        
        //random
        
        Assert.assertEquals(new Vector(-51.637, -68.516, -57.285), RotationUtility.performRotation(new Vector(-51.637, -68.516, -57.285), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-81.973, -46.076, 17.3), RotationUtility.performRotation(new Vector(-81.973, 46.076, -17.3), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(77.206, -49.136, 23.592), RotationUtility.performRotation(new Vector(-77.206, -49.136, -23.592), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-63.719, -48.843, 9.97), RotationUtility.performRotation(new Vector(63.719, 48.843, 9.97), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(10.585, 55.935, -33.118), RotationUtility.performRotation(new Vector(10.585, -55.935, 33.118), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(60.926, 57.454, -78.297), RotationUtility.performRotation(new Vector(-60.926, -57.454, -78.297), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(57.359, -58.815, -69.826), RotationUtility.performRotation(new Vector(-57.359, -58.815, 69.826), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-18.58, -91.678, -86.37), RotationUtility.performRotation(new Vector(-18.58, -91.678, -86.37), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-89.636, 59.671, -3.263), RotationUtility.performRotation(new Vector(-89.636, 59.671, -3.263), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(77.152, 47.758, 27.178), RotationUtility.performRotation(new Vector(77.152, -27.178, 47.758), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-19.972, 38.797, -96.396), RotationUtility.performRotation(new Vector(-96.396, 38.797, 19.972), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-46.795, -82.547, -26.106), RotationUtility.performRotation(new Vector(82.547, -46.795, -26.106), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-69.417, -43.115, 97.585), RotationUtility.performRotation(new Vector(43.115, 97.585, 69.417), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-22.843441182549874, -67.71881193382441, -10.264024074928177), RotationUtility.performRotation(new Vector(-35.906, 0.007, -62.64), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(-16.077927638211364, 126.72480403046467, 29.168827629864797), RotationUtility.performRotation(new Vector(-78.271, 86.533, 59.617), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(0, 0, 0)));
        
        Assert.assertEquals(new Vector(-26.41, -56.411, 13.379), RotationUtility.performRotation(new Vector(-26.41, -56.411, 13.379), RotationUtility.getRotationMatrix(0, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(44.101, 97.244, 31.446), RotationUtility.performRotation(new Vector(44.101, -95.244, -29.446), RotationUtility.getRotationMatrix(Math.PI, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-49.573, -38.298, -66.831), RotationUtility.performRotation(new Vector(51.573, -38.298, 68.831), RotationUtility.getRotationMatrix(0, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-50.246, 72.707, -16.15), RotationUtility.performRotation(new Vector(52.246, -70.707, -16.15), RotationUtility.getRotationMatrix(0, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-46.095, 47.252, -16.719), RotationUtility.performRotation(new Vector(-46.095, -45.252, 18.719), RotationUtility.getRotationMatrix(0, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(66.44, -50.685, -10.95), RotationUtility.performRotation(new Vector(-64.44, 52.685, -10.95), RotationUtility.getRotationMatrix(Math.PI, Math.PI, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-71.89, -59.696, 27.919), RotationUtility.performRotation(new Vector(73.89, -59.696, -25.919), RotationUtility.getRotationMatrix(Math.PI, 0, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(76.328, -42.003, 88.836), RotationUtility.performRotation(new Vector(76.328, -42.003, 88.836), RotationUtility.getRotationMatrix(Math.PI, Math.PI, Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-1.127, -7.676, 36.189), RotationUtility.performRotation(new Vector(-1.127, -7.676, 36.189), RotationUtility.getRotationMatrix(-Math.PI, -Math.PI, -Math.PI), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(86.026, -79.259, -44.195), RotationUtility.performRotation(new Vector(86.026, 46.195, -79.259), RotationUtility.getRotationMatrix(Math.PI / 2, 0, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(98.174, -29.397, -82.043), RotationUtility.performRotation(new Vector(-82.043, -29.397, -96.174), RotationUtility.getRotationMatrix(0, Math.PI / 2, 0), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(47.482, 12.448, -0.272), RotationUtility.performRotation(new Vector(-10.448, 47.482, -0.272), RotationUtility.getRotationMatrix(0, 0, Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-58.753, 34.434, -35.717), RotationUtility.performRotation(new Vector(-32.434, -35.717, 60.753), RotationUtility.getRotationMatrix(Math.PI / 2, Math.PI, 3 * Math.PI / 2), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-2.1424312792947333, -52.98011408563357, 72.9464836246838), RotationUtility.performRotation(new Vector(43.689, 33.676, -71.18), RotationUtility.getRotationMatrix(0.58874, 2.0113, 1.3879), new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(100.04858838208814, 1.4181444261088174, -67.51985054533165), RotationUtility.performRotation(new Vector(55.122, -54.456, 93.202), RotationUtility.getRotationMatrix(-0.58874, -2.0113, -1.3879), new Vector(1, 1, 1)));
    }
    
}
