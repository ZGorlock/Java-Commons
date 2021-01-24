/*
 * File:    SphericalCoordinateUtilityTest.java
 * Package: commons.math
 * Author:  Zachary Gill
 */

package commons.math;

import commons.math.vector.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of SphericalCoordinateUtility.
 *
 * @see SphericalCoordinateUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({SphericalCoordinateUtility.class})
public class SphericalCoordinateUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SphericalCoordinateUtilityTest.class);
    
    
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
     * JUnit test of sphericalToCartesian.
     *
     * @throws Exception When there is an exception.
     * @see SphericalCoordinateUtility#sphericalToCartesian(Vector)
     * @see SphericalCoordinateUtility#sphericalToCartesian(double, double, double)
     */
    @Test
    public void testSphericalToCartesian() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cartesianToSpherical.
     *
     * @throws Exception When there is an exception.
     * @see SphericalCoordinateUtility#cartesianToSpherical(Vector)
     * @see SphericalCoordinateUtility#cartesianToSpherical(double, double, double)
     */
    @Test
    public void testCartesianToSpherical() throws Exception {
        //TODO
    }
    
}
