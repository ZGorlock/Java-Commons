/*
 * File:    CoordinateUtilityTest.java
 * Package: commons.math.coordinate
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.coordinate;

import commons.math.component.vector.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of CoordinateUtility.
 *
 * @see CoordinateUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CoordinateUtility.class})
public class CoordinateUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CoordinateUtilityTest.class);
    
    
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
     * JUnit test of CoordinateSystem.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility.CoordinateSystem
     */
    @Test
    public void testCoordinateSystem() throws Exception {
        Assert.assertEquals(4, CoordinateUtility.CoordinateSystem.values().length);
        Assert.assertEquals(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.values()[0]);
        Assert.assertEquals(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.values()[1]);
        Assert.assertEquals(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.values()[2]);
        Assert.assertEquals(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.values()[3]);
    }
    
    /**
     * JUnit test of cartesianToSpherical.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#cartesianToSpherical(double, double, double)
     * @see CoordinateUtility#cartesianToSpherical(Vector)
     */
    @SuppressWarnings("DuplicateExpressions")
    @Test
    public void testCartesianToSpherical() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cartesianToSpherical(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.cartesianToSpherical(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(1, 0, Math.PI), CoordinateUtility.cartesianToSpherical(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(1, Math.PI / 2, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 2, Math.PI / 4), CoordinateUtility.cartesianToSpherical(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 2, 3 * Math.PI / 4), CoordinateUtility.cartesianToSpherical(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 2, Math.PI / 4), CoordinateUtility.cartesianToSpherical(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 2, 3 * Math.PI / 4), CoordinateUtility.cartesianToSpherical(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(1, Math.PI, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 0, Math.PI / 4), CoordinateUtility.cartesianToSpherical(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 0, 3 * Math.PI / 4), CoordinateUtility.cartesianToSpherical(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI, Math.PI / 4), CoordinateUtility.cartesianToSpherical(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI, 3 * Math.PI / 4), CoordinateUtility.cartesianToSpherical(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 5 * Math.PI / 4, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(3), Math.PI / 4, Math.acos(Math.sqrt(3) / 3)), CoordinateUtility.cartesianToSpherical(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(3), Math.PI / 4, Math.PI - Math.acos(Math.sqrt(3) / 3)), CoordinateUtility.cartesianToSpherical(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(3), 7 * Math.PI / 4, Math.acos(Math.sqrt(3) / 3)), CoordinateUtility.cartesianToSpherical(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(3), 7 * Math.PI / 4, Math.PI - Math.acos(Math.sqrt(3) / 3)), CoordinateUtility.cartesianToSpherical(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(3), 3 * Math.PI / 4, Math.acos(Math.sqrt(3) / 3)), CoordinateUtility.cartesianToSpherical(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(3), 3 * Math.PI / 4, Math.PI - Math.acos(Math.sqrt(3) / 3)), CoordinateUtility.cartesianToSpherical(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(3), 5 * Math.PI / 4, Math.PI - Math.acos(Math.sqrt(3) / 3)), CoordinateUtility.cartesianToSpherical(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(12.68857754044952, 0.41822432957922906, 0.8886141521914033), CoordinateUtility.cartesianToSpherical(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(1.1406136602919062E-4, 6.186131378970239E-5, 0.5011599109451838), CoordinateUtility.cartesianToSpherical(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(1.1406136602919062E-4, 3.1415307922760034, 2.6404327426446095), CoordinateUtility.cartesianToSpherical(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(2.4295998516042205, 0.8170374004982911, 1.198309541346806), CoordinateUtility.cartesianToSpherical(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(2.4295998516042205, 5.466147906681295, 1.198309541346806), CoordinateUtility.cartesianToSpherical(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(10.058004032170892, 0.4636476090008061, 0.3567333885140938), CoordinateUtility.cartesianToSpherical(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cartesianToSpherical(new Vector(0, 0)));
        Assert.assertEquals(new Vector(1, Math.PI / 2, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(0, 1)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(0, -1)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 5 * Math.PI / 4, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(9.848857801796104, 0.41822432957922906, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(9, 4)));
        Assert.assertEquals(new Vector(5.48E-5, 6.186131378970239E-5, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415307922760034, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(2.2629905367791623, 0.8170374004982911, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(2.2629905367791623, 5.466147906681295, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(3.5124073655203634, 0.4636476090008061, Math.PI / 2), CoordinateUtility.cartesianToSpherical(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector cartesianToSpherical = CoordinateUtility.cartesianToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianToCylindrical = CoordinateUtility.cartesianToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianToPolar = CoordinateUtility.cartesianToPolar(new Vector(8.1560, 0.1123, -1.7988));
        Vector sphericalTest = CoordinateUtility.cartesianToSpherical(CoordinateUtility.sphericalToCartesian(cartesianToSpherical));
        Vector cylindricalTest = CoordinateUtility.cylindricalToSpherical(cartesianToCylindrical);
        Vector polarTest = CoordinateUtility.polarToSpherical(cartesianToPolar);
        polarTest = new Vector(cartesianToSpherical.getRawX(), polarTest.getRawY(), cartesianToSpherical.getRawZ()); //adjust for x, z
        Assert.assertEquals(cartesianToSpherical, sphericalTest);
        Assert.assertEquals(cartesianToSpherical, cylindricalTest);
        Assert.assertEquals(cartesianToSpherical, polarTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "cartesianToSpherical", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.cartesianToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.cartesianToSpherical(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToSpherical(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(-1.7988));
        CoordinateUtility.cartesianToSpherical(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(0));
    }
    
    /**
     * JUnit test of cartesianToCylindrical.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#cartesianToCylindrical(double, double, double)
     * @see CoordinateUtility#cartesianToCylindrical(Vector)
     */
    @Test
    public void testCartesianToCylindrical() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cartesianToCylindrical(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.cartesianToCylindrical(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.cartesianToCylindrical(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(1, Math.PI / 2, 0), CoordinateUtility.cartesianToCylindrical(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2, 0), CoordinateUtility.cartesianToCylindrical(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI / 2, 1), CoordinateUtility.cartesianToCylindrical(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(1, Math.PI / 2, -1), CoordinateUtility.cartesianToCylindrical(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2, 1), CoordinateUtility.cartesianToCylindrical(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2, -1), CoordinateUtility.cartesianToCylindrical(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.cartesianToCylindrical(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(1, Math.PI, 0), CoordinateUtility.cartesianToCylindrical(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 1), CoordinateUtility.cartesianToCylindrical(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(1, 0, -1), CoordinateUtility.cartesianToCylindrical(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(1, Math.PI, 1), CoordinateUtility.cartesianToCylindrical(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(1, Math.PI, -1), CoordinateUtility.cartesianToCylindrical(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4, 0), CoordinateUtility.cartesianToCylindrical(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4, 0), CoordinateUtility.cartesianToCylindrical(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4, 0), CoordinateUtility.cartesianToCylindrical(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 5 * Math.PI / 4, 0), CoordinateUtility.cartesianToCylindrical(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4, 1), CoordinateUtility.cartesianToCylindrical(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4, -1), CoordinateUtility.cartesianToCylindrical(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4, 1), CoordinateUtility.cartesianToCylindrical(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4, -1), CoordinateUtility.cartesianToCylindrical(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4, 1), CoordinateUtility.cartesianToCylindrical(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4, -1), CoordinateUtility.cartesianToCylindrical(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 5 * Math.PI / 4, -1), CoordinateUtility.cartesianToCylindrical(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(9.848857801796104, 0.41822432957922906, 8.0), CoordinateUtility.cartesianToCylindrical(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.48E-5, 6.186131378970239E-5, 1.0003477E-4), CoordinateUtility.cartesianToCylindrical(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415307922760034, -1.0003477E-4), CoordinateUtility.cartesianToCylindrical(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(2.2629905367791623, 0.8170374004982911, 0.8842111), CoordinateUtility.cartesianToCylindrical(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(2.2629905367791623, 5.466147906681295, 0.8842111), CoordinateUtility.cartesianToCylindrical(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(3.5124073655203634, 0.4636476090008061, 9.42477796076938), CoordinateUtility.cartesianToCylindrical(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cartesianToCylindrical(new Vector(0, 0)));
        Assert.assertEquals(new Vector(1, Math.PI / 2, 0), CoordinateUtility.cartesianToCylindrical(new Vector(0, 1)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2, 0), CoordinateUtility.cartesianToCylindrical(new Vector(0, -1)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.cartesianToCylindrical(new Vector(1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI, 0), CoordinateUtility.cartesianToCylindrical(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4, 0), CoordinateUtility.cartesianToCylindrical(new Vector(1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4, 0), CoordinateUtility.cartesianToCylindrical(new Vector(1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4, 0), CoordinateUtility.cartesianToCylindrical(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 5 * Math.PI / 4, 0), CoordinateUtility.cartesianToCylindrical(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(9.848857801796104, 0.41822432957922906, 0), CoordinateUtility.cartesianToCylindrical(new Vector(9, 4)));
        Assert.assertEquals(new Vector(5.48E-5, 6.186131378970239E-5, 0), CoordinateUtility.cartesianToCylindrical(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415307922760034, 0), CoordinateUtility.cartesianToCylindrical(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(2.2629905367791623, 0.8170374004982911, 0), CoordinateUtility.cartesianToCylindrical(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(2.2629905367791623, 5.466147906681295, 0), CoordinateUtility.cartesianToCylindrical(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(3.5124073655203634, 0.4636476090008061, 0), CoordinateUtility.cartesianToCylindrical(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector cartesianToSpherical = CoordinateUtility.cartesianToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianToCylindrical = CoordinateUtility.cartesianToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianToPolar = CoordinateUtility.cartesianToPolar(new Vector(8.1560, 0.1123, -1.7988));
        Vector sphericalTest = CoordinateUtility.sphericalToCylindrical(cartesianToSpherical);
        Vector cylindricalTest = CoordinateUtility.cartesianToCylindrical(CoordinateUtility.cylindricalToCartesian(cartesianToCylindrical));
        Vector polarTest = CoordinateUtility.polarToCylindrical(cartesianToPolar);
        polarTest = new Vector(polarTest.getRawX(), polarTest.getRawY(), cartesianToCylindrical.getRawZ()); //adjust for z
        Assert.assertEquals(cartesianToCylindrical, sphericalTest);
        Assert.assertEquals(cartesianToCylindrical, cylindricalTest);
        Assert.assertEquals(cartesianToCylindrical, polarTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "cartesianToCylindrical", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.cartesianToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.cartesianToCylindrical(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToCylindrical(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(-1.7988));
        CoordinateUtility.cartesianToCylindrical(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(0));
    }
    
    /**
     * JUnit test of cartesianToPolar.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#cartesianToPolar(double, double)
     * @see CoordinateUtility#cartesianToPolar(Vector)
     */
    @Test
    public void testCartesianToPolar() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.cartesianToPolar(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.cartesianToPolar(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.cartesianToPolar(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(1, Math.PI / 2), CoordinateUtility.cartesianToPolar(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2), CoordinateUtility.cartesianToPolar(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI / 2), CoordinateUtility.cartesianToPolar(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(1, Math.PI / 2), CoordinateUtility.cartesianToPolar(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2), CoordinateUtility.cartesianToPolar(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2), CoordinateUtility.cartesianToPolar(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.cartesianToPolar(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(1, Math.PI), CoordinateUtility.cartesianToPolar(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.cartesianToPolar(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.cartesianToPolar(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(1, Math.PI), CoordinateUtility.cartesianToPolar(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(1, Math.PI), CoordinateUtility.cartesianToPolar(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 5 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 5 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(9.848857801796104, 0.41822432957922906), CoordinateUtility.cartesianToPolar(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.48E-5, 6.186131378970239E-5), CoordinateUtility.cartesianToPolar(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415307922760034), CoordinateUtility.cartesianToPolar(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(2.2629905367791623, 0.8170374004982911), CoordinateUtility.cartesianToPolar(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(2.2629905367791623, 5.466147906681295), CoordinateUtility.cartesianToPolar(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(3.5124073655203634, 0.4636476090008061), CoordinateUtility.cartesianToPolar(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.cartesianToPolar(new Vector(0, 0)));
        Assert.assertEquals(new Vector(1, Math.PI / 2), CoordinateUtility.cartesianToPolar(new Vector(0, 1)));
        Assert.assertEquals(new Vector(1, 3 * Math.PI / 2), CoordinateUtility.cartesianToPolar(new Vector(0, -1)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.cartesianToPolar(new Vector(1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI), CoordinateUtility.cartesianToPolar(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 7 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 3 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 5 * Math.PI / 4), CoordinateUtility.cartesianToPolar(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(9.848857801796104, 0.41822432957922906), CoordinateUtility.cartesianToPolar(new Vector(9, 4)));
        Assert.assertEquals(new Vector(5.48E-5, 6.186131378970239E-5), CoordinateUtility.cartesianToPolar(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415307922760034), CoordinateUtility.cartesianToPolar(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(2.2629905367791623, 0.8170374004982911), CoordinateUtility.cartesianToPolar(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(2.2629905367791623, 5.466147906681295), CoordinateUtility.cartesianToPolar(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(3.5124073655203634, 0.4636476090008061), CoordinateUtility.cartesianToPolar(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector cartesianToSpherical = CoordinateUtility.cartesianToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianToCylindrical = CoordinateUtility.cartesianToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianToPolar = CoordinateUtility.cartesianToPolar(new Vector(8.1560, 0.1123, -1.7988));
        Vector sphericalTest = CoordinateUtility.sphericalToPolar(cartesianToSpherical);
        Vector cylindricalTest = CoordinateUtility.cylindricalToPolar(cartesianToCylindrical);
        Vector polarTest = CoordinateUtility.cartesianToPolar(CoordinateUtility.polarToCartesian(cartesianToPolar));
        Assert.assertEquals(cartesianToPolar, sphericalTest);
        Assert.assertEquals(cartesianToPolar, cylindricalTest);
        Assert.assertEquals(cartesianToPolar, polarTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "cartesianToPolar", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.cartesianToPolar(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.cartesianToPolar(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToPolar(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123));
        CoordinateUtility.cartesianToPolar(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123));
    }
    
    /**
     * JUnit test of sphericalToCartesian.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#sphericalToCartesian(double, double, double)
     * @see CoordinateUtility#sphericalToCartesian(Vector)
     */
    @Test
    public void testSphericalToCartesian() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.sphericalToCartesian(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.sphericalToCartesian(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(Math.sin(1), 0, Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(-Math.sin(1), 0, Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(-Math.sin(1), 0, -Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), 0, -Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.sphericalToCartesian(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.sphericalToCartesian(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.sphericalToCartesian(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.sphericalToCartesian(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(0.4546487134128409, 0.7080734182735712, Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(-0.4546487134128409, -0.7080734182735712, Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(0.4546487134128409, -0.7080734182735712, Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(-0.4546487134128409, 0.7080734182735712, Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(-0.4546487134128409, -0.7080734182735712, -Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(0.4546487134128409, 0.7080734182735712, -Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(0.4546487134128409, -0.7080734182735712, -Math.cos(1)), CoordinateUtility.sphericalToCartesian(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(-5.820189359887634, -6.738739108182468, -1.3095003042775217), CoordinateUtility.sphericalToCartesian(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.481905386857136E-9, 0, 5.479999972580942E-5), CoordinateUtility.sphericalToCartesian(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(5.481905386857136E-9, 0, -5.479999972580942E-5), CoordinateUtility.sphericalToCartesian(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(-0.09476746133702522, 1.1940760350486674, 0.9817556826019541), CoordinateUtility.sphericalToCartesian(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(-0.09476746133702522, -1.1940760350486674, 0.9817556826019541), CoordinateUtility.sphericalToCartesian(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(0, 0, -Math.PI), CoordinateUtility.sphericalToCartesian(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, 1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCartesian(new Vector(0, -1)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.sphericalToCartesian(new Vector(1, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.sphericalToCartesian(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.sphericalToCartesian(new Vector(1, 1)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.sphericalToCartesian(new Vector(1, -1)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.sphericalToCartesian(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.sphericalToCartesian(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(0, 0, 9.0), CoordinateUtility.sphericalToCartesian(new Vector(9, 4)));
        Assert.assertEquals(new Vector(0, 0, 5.48E-5), CoordinateUtility.sphericalToCartesian(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(0, 0, -5.48E-5), CoordinateUtility.sphericalToCartesian(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(0, 0, 1.5487552), CoordinateUtility.sphericalToCartesian(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(0, 0, 1.5487552), CoordinateUtility.sphericalToCartesian(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(0, 0, Math.PI), CoordinateUtility.sphericalToCartesian(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector sphericalToCartesian = CoordinateUtility.sphericalToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        Vector sphericalToCylindrical = CoordinateUtility.sphericalToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        Vector sphericalToPolar = CoordinateUtility.sphericalToPolar(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianTest = CoordinateUtility.sphericalToCartesian(CoordinateUtility.cartesianToSpherical(sphericalToCartesian));
        Vector cylindricalTest = CoordinateUtility.cylindricalToCartesian(sphericalToCylindrical);
        Vector polarTest = CoordinateUtility.polarToCartesian(sphericalToPolar);
        polarTest = new Vector(polarTest.getRawX(), polarTest.getRawY(), sphericalToCartesian.getRawZ()); //adjust for z
        Assert.assertEquals(sphericalToCartesian, cartesianTest);
        Assert.assertEquals(sphericalToCartesian, cylindricalTest);
        Assert.assertEquals(sphericalToCartesian, polarTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "sphericalToCartesian", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.sphericalToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.sphericalToCartesian(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToCartesian(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(-1.7988));
        CoordinateUtility.sphericalToCartesian(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(0));
    }
    
    /**
     * JUnit test of sphericalToCylindrical.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#sphericalToCylindrical(double, double, double)
     * @see CoordinateUtility#sphericalToCylindrical(Vector)
     */
    @Test
    public void testSphericalToCylindrical() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.sphericalToCylindrical(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.sphericalToCylindrical(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(Math.sin(1), 0, Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI, Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI, -Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), 0, -Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(0, 1, 1), CoordinateUtility.sphericalToCylindrical(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 1), CoordinateUtility.sphericalToCylindrical(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(0, 1, -1), CoordinateUtility.sphericalToCylindrical(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, -1), CoordinateUtility.sphericalToCylindrical(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(Math.sin(1), 1, Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI + 1, Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sin(1), 2 * Math.PI - 1, Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI - 1, Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI + 1, -Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), 1, -Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sin(1), 2 * Math.PI - 1, -Math.cos(1)), CoordinateUtility.sphericalToCylindrical(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(8.904224219610436, 4.0, -1.3095003042775217), CoordinateUtility.sphericalToCylindrical(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.481905386857136E-9, 3.39E-9, 5.479999972580942E-5), CoordinateUtility.sphericalToCylindrical(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(5.481905386857136E-9, 3.39E-9, -5.479999972580942E-5), CoordinateUtility.sphericalToCylindrical(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(1.1978307264408485, 1.649995, 0.9817556826019541), CoordinateUtility.sphericalToCylindrical(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(1.1978307264408485, 4.633190307179586, 0.9817556826019541), CoordinateUtility.sphericalToCylindrical(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(0, Math.PI / 2, -Math.PI), CoordinateUtility.sphericalToCylindrical(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.sphericalToCylindrical(new Vector(0, -1)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.sphericalToCylindrical(new Vector(1, 0)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.sphericalToCylindrical(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(0, 1, 1), CoordinateUtility.sphericalToCylindrical(new Vector(1, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 1), CoordinateUtility.sphericalToCylindrical(new Vector(1, -1)));
        Assert.assertEquals(new Vector(0, 1, -1), CoordinateUtility.sphericalToCylindrical(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, -1), CoordinateUtility.sphericalToCylindrical(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(0, 4.0, 9.0), CoordinateUtility.sphericalToCylindrical(new Vector(9, 4)));
        Assert.assertEquals(new Vector(0, 3.39E-9, 5.48E-5), CoordinateUtility.sphericalToCylindrical(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(0, 3.39E-9, -5.48E-5), CoordinateUtility.sphericalToCylindrical(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(0, 1.649995, 1.5487552), CoordinateUtility.sphericalToCylindrical(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(0, 4.633190307179586, 1.5487552), CoordinateUtility.sphericalToCylindrical(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(0, Math.PI / 2, Math.PI), CoordinateUtility.sphericalToCylindrical(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector sphericalToCartesian = CoordinateUtility.sphericalToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        Vector sphericalToCylindrical = CoordinateUtility.sphericalToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        Vector sphericalToPolar = CoordinateUtility.sphericalToPolar(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianTest = CoordinateUtility.cartesianToCylindrical(sphericalToCartesian);
        Vector cylindricalTest = CoordinateUtility.sphericalToCylindrical(CoordinateUtility.cylindricalToSpherical(sphericalToCylindrical));
        Vector polarTest = CoordinateUtility.polarToCylindrical(sphericalToPolar);
        polarTest = new Vector(polarTest.getRawX(), polarTest.getRawY(), sphericalToCylindrical.getRawZ()); //adjust for z
        Assert.assertEquals(sphericalToCylindrical, cartesianTest);
        Assert.assertEquals(sphericalToCylindrical, cylindricalTest);
        Assert.assertEquals(sphericalToCylindrical, polarTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "sphericalToCylindrical", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.sphericalToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.sphericalToCylindrical(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToCylindrical(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(-1.7988));
        CoordinateUtility.sphericalToCylindrical(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(0));
    }
    
    /**
     * JUnit test of sphericalToPolar.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#sphericalToPolar(double, double, double)
     * @see CoordinateUtility#sphericalToPolar(Vector)
     */
    @Test
    public void testSphericalToPolar() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.sphericalToPolar(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.sphericalToPolar(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.sphericalToPolar(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.sphericalToPolar(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.sphericalToPolar(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.sphericalToPolar(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.sphericalToPolar(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.sphericalToPolar(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(Math.sin(1), 0), CoordinateUtility.sphericalToPolar(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI), CoordinateUtility.sphericalToPolar(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI), CoordinateUtility.sphericalToPolar(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), 0), CoordinateUtility.sphericalToPolar(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.sphericalToPolar(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.sphericalToPolar(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(Math.sin(1), 1), CoordinateUtility.sphericalToPolar(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI + 1), CoordinateUtility.sphericalToPolar(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sin(1), 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(Math.sin(1), Math.PI + 1), CoordinateUtility.sphericalToPolar(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sin(1), 1), CoordinateUtility.sphericalToPolar(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sin(1), 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(8.904224219610436, 4.0), CoordinateUtility.sphericalToPolar(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.481905386857136E-9, 3.39E-9), CoordinateUtility.sphericalToPolar(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(5.481905386857136E-9, 3.39E-9), CoordinateUtility.sphericalToPolar(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(1.1978307264408485, 1.649995), CoordinateUtility.sphericalToPolar(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(1.1978307264408485, 4.633190307179586), CoordinateUtility.sphericalToPolar(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(0, Math.PI / 2), CoordinateUtility.sphericalToPolar(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.sphericalToPolar(new Vector(0, 0)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.sphericalToPolar(new Vector(0, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(0, -1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.sphericalToPolar(new Vector(1, 0)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.sphericalToPolar(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.sphericalToPolar(new Vector(1, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(1, -1)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.sphericalToPolar(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.sphericalToPolar(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(0, 4.0), CoordinateUtility.sphericalToPolar(new Vector(9, 4)));
        Assert.assertEquals(new Vector(0, 3.39E-9), CoordinateUtility.sphericalToPolar(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(0, 3.39E-9), CoordinateUtility.sphericalToPolar(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(0, 1.649995), CoordinateUtility.sphericalToPolar(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(0, 4.633190307179586), CoordinateUtility.sphericalToPolar(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(0, Math.PI / 2), CoordinateUtility.sphericalToPolar(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector sphericalToCartesian = CoordinateUtility.sphericalToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        Vector sphericalToCylindrical = CoordinateUtility.sphericalToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        Vector sphericalToPolar = CoordinateUtility.sphericalToPolar(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianTest = CoordinateUtility.cartesianToPolar(sphericalToCartesian);
        Vector cylindricalTest = CoordinateUtility.cylindricalToPolar(sphericalToCylindrical);
        Vector polarTest = CoordinateUtility.sphericalToPolar(CoordinateUtility.polarToSpherical(sphericalToPolar));
        Assert.assertEquals(sphericalToPolar, cartesianTest);
        Assert.assertEquals(sphericalToPolar, cylindricalTest);
        Assert.assertEquals(sphericalToPolar, polarTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "sphericalToPolar", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.sphericalToPolar(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.sphericalToPolar(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToPolar(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(-1.7988));
        CoordinateUtility.sphericalToPolar(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(0));
    }
    
    /**
     * JUnit test of polarToCartesian.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#polarToCartesian(double, double)
     * @see CoordinateUtility#polarToCartesian(Vector)
     */
    @Test
    public void testPolarToCartesian() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.polarToCartesian(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0), CoordinateUtility.polarToCartesian(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.polarToCartesian(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.polarToCartesian(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(-1, 0), CoordinateUtility.polarToCartesian(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(-1, 0), CoordinateUtility.polarToCartesian(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(Math.cos(1), Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(Math.cos(1), -Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(-Math.cos(1), -Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(-Math.cos(1), Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(Math.cos(1), Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(Math.cos(1), Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(Math.cos(1), -Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(Math.cos(1), -Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(-Math.cos(1), -Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(-Math.cos(1), -Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(-Math.cos(1), Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(-5.882792587772507, -6.811222457771354), CoordinateUtility.polarToCartesian(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.48E-5, 0), CoordinateUtility.polarToCartesian(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(-5.48E-5, 0), CoordinateUtility.polarToCartesian(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(-0.12253116846703688, 1.5439005091912958), CoordinateUtility.polarToCartesian(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(-0.12253116846703688, -1.5439005091912958), CoordinateUtility.polarToCartesian(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(0, Math.PI), CoordinateUtility.polarToCartesian(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, 0)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, 1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.polarToCartesian(new Vector(0, -1)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.polarToCartesian(new Vector(1, 0)));
        Assert.assertEquals(new Vector(-1, 0), CoordinateUtility.polarToCartesian(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(Math.cos(1), Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(1, 1)));
        Assert.assertEquals(new Vector(Math.cos(1), -Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(1, -1)));
        Assert.assertEquals(new Vector(-Math.cos(1), -Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(-Math.cos(1), Math.sin(1)), CoordinateUtility.polarToCartesian(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(-5.882792587772507, -6.811222457771354), CoordinateUtility.polarToCartesian(new Vector(9, 4)));
        Assert.assertEquals(new Vector(5.48E-5, 0), CoordinateUtility.polarToCartesian(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(-5.48E-5, 0), CoordinateUtility.polarToCartesian(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(-0.12253116846703688, 1.5439005091912958), CoordinateUtility.polarToCartesian(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(-0.12253116846703688, -1.5439005091912958), CoordinateUtility.polarToCartesian(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(0, Math.PI), CoordinateUtility.polarToCartesian(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector polarToCartesian = CoordinateUtility.polarToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        Vector polarToSpherical = CoordinateUtility.polarToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        Vector polarToCylindrical = CoordinateUtility.polarToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianTest = CoordinateUtility.polarToCartesian(CoordinateUtility.cartesianToPolar(polarToCartesian));
        Vector sphericalTest = CoordinateUtility.sphericalToCartesian(polarToSpherical);
        Vector cylindricalTest = CoordinateUtility.cylindricalToCartesian(polarToCylindrical);
        sphericalTest = new Vector(sphericalTest.getRawX(), sphericalTest.getRawY()); // adjust for z
        cylindricalTest = new Vector(cylindricalTest.getRawX(), cylindricalTest.getRawY()); // adjust for z
        Assert.assertEquals(polarToCartesian, cartesianTest);
        Assert.assertEquals(polarToCartesian, sphericalTest);
        Assert.assertEquals(polarToCartesian, cylindricalTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "polarToCartesian", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.polarToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.polarToCartesian(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToCartesian(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123));
        CoordinateUtility.polarToCartesian(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123));
    }
    
    /**
     * JUnit test of polarToSpherical.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#polarToSpherical(double, double)
     * @see CoordinateUtility#polarToSpherical(Vector)
     */
    @Test
    public void testPolarToSpherical() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(0, 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(0, 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(1, Math.PI, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(1, Math.PI, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(1, Math.PI, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(1, 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI + 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(1, 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(1, Math.PI + 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(1, Math.PI + 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(1, Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(9.0, 4.0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.48E-5, 3.39E-9, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415926569797925, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(1.5487552, 1.649995, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(1.5487552, 4.633190307179586, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(Math.PI, Math.PI / 2, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, 0)));
        Assert.assertEquals(new Vector(0, 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0, -1)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(1, 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, 1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1, -1)));
        Assert.assertEquals(new Vector(1, Math.PI + 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(1, Math.PI - 1, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(9.0, 4.0, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(9, 4)));
        Assert.assertEquals(new Vector(5.48E-5, 3.39E-9, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415926569797925, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(1.5487552, 1.649995, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(1.5487552, 4.633190307179586, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(Math.PI, Math.PI / 2, Math.PI / 2), CoordinateUtility.polarToSpherical(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector polarToCartesian = CoordinateUtility.polarToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        Vector polarToSpherical = CoordinateUtility.polarToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        Vector polarToCylindrical = CoordinateUtility.polarToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianTest = CoordinateUtility.cartesianToSpherical(polarToCartesian);
        Vector sphericalTest = CoordinateUtility.polarToSpherical(CoordinateUtility.sphericalToPolar(polarToSpherical));
        Vector cylindricalTest = CoordinateUtility.cylindricalToSpherical(polarToCylindrical);
        Assert.assertEquals(polarToSpherical, cartesianTest);
        Assert.assertEquals(polarToSpherical, sphericalTest);
        Assert.assertEquals(polarToSpherical, cylindricalTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "polarToSpherical", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.polarToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.polarToSpherical(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToSpherical(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123));
        CoordinateUtility.polarToSpherical(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123));
    }
    
    /**
     * JUnit test of polarToCylindrical.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#polarToCylindrical(double, double)
     * @see CoordinateUtility#polarToCylindrical(Vector)
     */
    @Test
    public void testPolarToCylindrical() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.polarToCylindrical(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.polarToCylindrical(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.polarToCylindrical(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.polarToCylindrical(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.polarToCylindrical(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.polarToCylindrical(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.polarToCylindrical(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(1, Math.PI, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.polarToCylindrical(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.polarToCylindrical(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(1, Math.PI, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(1, Math.PI, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(1, 1, 0), CoordinateUtility.polarToCylindrical(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI + 1, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(1, 1, 0), CoordinateUtility.polarToCylindrical(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 1, 0), CoordinateUtility.polarToCylindrical(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(1, Math.PI + 1, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(1, Math.PI + 1, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(1, Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(9.0, 4.0, 0), CoordinateUtility.polarToCylindrical(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.48E-5, 3.39E-9, 0), CoordinateUtility.polarToCylindrical(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415926569797925, 0), CoordinateUtility.polarToCylindrical(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(1.5487552, 1.649995, 0), CoordinateUtility.polarToCylindrical(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(1.5487552, 4.633190307179586, 0), CoordinateUtility.polarToCylindrical(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(Math.PI, Math.PI / 2, 0), CoordinateUtility.polarToCylindrical(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.polarToCylindrical(new Vector(0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.polarToCylindrical(new Vector(0, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(0, -1)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.polarToCylindrical(new Vector(1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(1, 1, 0), CoordinateUtility.polarToCylindrical(new Vector(1, 1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(1, -1)));
        Assert.assertEquals(new Vector(1, Math.PI + 1, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(1, Math.PI - 1, 0), CoordinateUtility.polarToCylindrical(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(9.0, 4.0, 0), CoordinateUtility.polarToCylindrical(new Vector(9, 4)));
        Assert.assertEquals(new Vector(5.48E-5, 3.39E-9, 0), CoordinateUtility.polarToCylindrical(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415926569797925, 0), CoordinateUtility.polarToCylindrical(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(1.5487552, 1.649995, 0), CoordinateUtility.polarToCylindrical(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(1.5487552, 4.633190307179586, 0), CoordinateUtility.polarToCylindrical(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(Math.PI, Math.PI / 2, 0), CoordinateUtility.polarToCylindrical(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector polarToCartesian = CoordinateUtility.polarToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        Vector polarToSpherical = CoordinateUtility.polarToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        Vector polarToCylindrical = CoordinateUtility.polarToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianTest = CoordinateUtility.cartesianToCylindrical(polarToCartesian);
        Vector sphericalTest = CoordinateUtility.sphericalToCylindrical(polarToSpherical);
        Vector cylindricalTest = CoordinateUtility.polarToCylindrical(CoordinateUtility.cylindricalToPolar(polarToCylindrical));
        Assert.assertEquals(polarToCylindrical, cartesianTest);
        Assert.assertEquals(polarToCylindrical, sphericalTest);
        Assert.assertEquals(polarToCylindrical, cylindricalTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "polarToCylindrical", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.polarToCylindrical(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.polarToCylindrical(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToCylindrical(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123));
        CoordinateUtility.polarToCylindrical(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123));
    }
    
    /**
     * JUnit test of cylindricalToCartesian.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#cylindricalToCartesian(double, double, double)
     * @see CoordinateUtility#cylindricalToCartesian(Vector)
     */
    @Test
    public void testCylindricalToCartesian() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.cylindricalToCartesian(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.cylindricalToCartesian(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.cylindricalToCartesian(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.cylindricalToCartesian(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(0, 0, 1), CoordinateUtility.cylindricalToCartesian(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(0, 0, -1), CoordinateUtility.cylindricalToCartesian(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 1), CoordinateUtility.cylindricalToCartesian(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(1, 0, -1), CoordinateUtility.cylindricalToCartesian(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(-1, 0, 1), CoordinateUtility.cylindricalToCartesian(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(-1, 0, -1), CoordinateUtility.cylindricalToCartesian(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(Math.cos(1), Math.sin(1), 0), CoordinateUtility.cylindricalToCartesian(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(Math.cos(1), -Math.sin(1), 0), CoordinateUtility.cylindricalToCartesian(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(-Math.cos(1), -Math.sin(1), 0), CoordinateUtility.cylindricalToCartesian(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(-Math.cos(1), Math.sin(1), 0), CoordinateUtility.cylindricalToCartesian(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(Math.cos(1), Math.sin(1), 1), CoordinateUtility.cylindricalToCartesian(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(Math.cos(1), Math.sin(1), -1), CoordinateUtility.cylindricalToCartesian(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(Math.cos(1), -Math.sin(1), 1), CoordinateUtility.cylindricalToCartesian(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(Math.cos(1), -Math.sin(1), -1), CoordinateUtility.cylindricalToCartesian(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(-Math.cos(1), -Math.sin(1), 1), CoordinateUtility.cylindricalToCartesian(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(-Math.cos(1), -Math.sin(1), -1), CoordinateUtility.cylindricalToCartesian(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(-Math.cos(1), Math.sin(1), -1), CoordinateUtility.cylindricalToCartesian(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(-5.882792587772507, -6.811222457771354, 8.0), CoordinateUtility.cylindricalToCartesian(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.48E-5, 0, 1.0003477E-4), CoordinateUtility.cylindricalToCartesian(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(-5.48E-5, 0, -1.0003477E-4), CoordinateUtility.cylindricalToCartesian(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(-0.12253116846703688, 1.5439005091912958, 0.8842111), CoordinateUtility.cylindricalToCartesian(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(-0.12253116846703688, -1.5439005091912958, 0.8842111), CoordinateUtility.cylindricalToCartesian(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(0, Math.PI, 9.42477796076938), CoordinateUtility.cylindricalToCartesian(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(0, 0)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(0, 1)));
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(0, -1)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(1, 0)));
        Assert.assertEquals(new Vector(-1, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(Math.cos(1), Math.sin(1), 0), CoordinateUtility.cylindricalToCartesian(new Vector(1, 1)));
        Assert.assertEquals(new Vector(Math.cos(1), -Math.sin(1), 0), CoordinateUtility.cylindricalToCartesian(new Vector(1, -1)));
        Assert.assertEquals(new Vector(-Math.cos(1), -Math.sin(1), 0), CoordinateUtility.cylindricalToCartesian(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(-Math.cos(1), Math.sin(1), 0), CoordinateUtility.cylindricalToCartesian(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(-5.882792587772507, -6.811222457771354, 0), CoordinateUtility.cylindricalToCartesian(new Vector(9, 4)));
        Assert.assertEquals(new Vector(5.48E-5, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(-5.48E-5, 0, 0), CoordinateUtility.cylindricalToCartesian(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(-0.12253116846703688, 1.5439005091912958, 0), CoordinateUtility.cylindricalToCartesian(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(-0.12253116846703688, -1.5439005091912958, 0), CoordinateUtility.cylindricalToCartesian(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(0, Math.PI, 0), CoordinateUtility.cylindricalToCartesian(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector cylindricalToCartesian = CoordinateUtility.cylindricalToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        Vector cylindricalToSpherical = CoordinateUtility.cylindricalToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cylindricalToPolar = CoordinateUtility.cylindricalToPolar(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianTest = CoordinateUtility.cylindricalToCartesian(CoordinateUtility.cartesianToCylindrical(cylindricalToCartesian));
        Vector sphericalTest = CoordinateUtility.sphericalToCartesian(cylindricalToSpherical);
        Vector polarTest = CoordinateUtility.polarToCartesian(cylindricalToPolar);
        polarTest = new Vector(polarTest.getRawX(), polarTest.getRawY(), cylindricalToCartesian.getRawZ()); //adjust for z
        Assert.assertEquals(cylindricalToCartesian, cartesianTest);
        Assert.assertEquals(cylindricalToCartesian, sphericalTest);
        Assert.assertEquals(cylindricalToCartesian, polarTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "cylindricalToCartesian", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.cylindricalToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.cylindricalToCartesian(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToCartesian(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(-1.7988));
        CoordinateUtility.cylindricalToCartesian(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(0));
    }
    
    /**
     * JUnit test of cylindricalToSpherical.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#cylindricalToSpherical(double, double, double)
     * @see CoordinateUtility#cylindricalToSpherical(Vector)
     */
    @Test
    public void testCylindricalToSpherical() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cylindricalToSpherical(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, 0), CoordinateUtility.cylindricalToSpherical(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(1, 0, Math.PI), CoordinateUtility.cylindricalToSpherical(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.cylindricalToSpherical(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.cylindricalToSpherical(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(1, 1, 0), CoordinateUtility.cylindricalToSpherical(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(1, 1, Math.PI), CoordinateUtility.cylindricalToSpherical(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, 0), CoordinateUtility.cylindricalToSpherical(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, Math.PI), CoordinateUtility.cylindricalToSpherical(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 0, Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 0, 3 * Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 0, Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 0, 3 * Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(1, 1, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(1, 1, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 1, Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 1, 3 * Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 2 * Math.PI - 1, Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 2 * Math.PI - 1, 3 * Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 1, Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 1, 3 * Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(Math.sqrt(2), 2 * Math.PI - 1, 3 * Math.PI / 4), CoordinateUtility.cylindricalToSpherical(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(12.041594578792296, 4.0, 0.8441539861131709), CoordinateUtility.cylindricalToSpherical(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(1.1406136597881379E-4, 3.39E-9, 0.5011599101389467), CoordinateUtility.cylindricalToSpherical(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(1.1406136597881379E-4, 3.39E-9, 2.6404327434508463), CoordinateUtility.cylindricalToSpherical(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(1.7833877701975671, 1.649995, 1.0520357355208994), CoordinateUtility.cylindricalToSpherical(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(1.7833877701975671, 4.633190307179586, 1.0520357355208994), CoordinateUtility.cylindricalToSpherical(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(9.934588265796101, Math.PI / 2, 0.3217505543966423), CoordinateUtility.cylindricalToSpherical(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0, 0), CoordinateUtility.cylindricalToSpherical(new Vector(0, 0)));
        Assert.assertEquals(new Vector(0, 1, 0), CoordinateUtility.cylindricalToSpherical(new Vector(0, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1, 0), CoordinateUtility.cylindricalToSpherical(new Vector(0, -1)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(1, 0)));
        Assert.assertEquals(new Vector(1, 0, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(1, 1, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(1, 1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(1, -1)));
        Assert.assertEquals(new Vector(1, 1, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(9.0, 4.0, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(9, 4)));
        Assert.assertEquals(new Vector(5.48E-5, 3.39E-9, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(5.48E-5, 3.39E-9, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(1.5487552, 1.649995, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(1.5487552, 4.633190307179586, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(Math.PI, Math.PI / 2, Math.PI / 2), CoordinateUtility.cylindricalToSpherical(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector cylindricalToCartesian = CoordinateUtility.cylindricalToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        Vector cylindricalToSpherical = CoordinateUtility.cylindricalToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cylindricalToPolar = CoordinateUtility.cylindricalToPolar(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianTest = CoordinateUtility.cartesianToSpherical(cylindricalToCartesian);
        Vector sphericalTest = CoordinateUtility.cylindricalToSpherical(CoordinateUtility.sphericalToCylindrical(cylindricalToSpherical));
        Vector polarTest = CoordinateUtility.polarToSpherical(cylindricalToPolar);
        polarTest = new Vector(cylindricalToSpherical.getRawX(), polarTest.getRawY(), cylindricalToSpherical.getRawZ()); //adjust for x, z
        Assert.assertEquals(cylindricalToSpherical, cartesianTest);
        Assert.assertEquals(cylindricalToSpherical, sphericalTest);
        Assert.assertEquals(cylindricalToSpherical, polarTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "cylindricalToSpherical", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.cylindricalToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.cylindricalToSpherical(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToSpherical(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(-1.7988));
        CoordinateUtility.cylindricalToSpherical(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(0));
    }
    
    /**
     * JUnit test of cylindricalToPolar.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#cylindricalToPolar(double, double, double)
     * @see CoordinateUtility#cylindricalToPolar(Vector)
     */
    @Test
    public void testCylindricalToPolar() throws Exception {
        //3D vector
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.cylindricalToPolar(new Vector(0, 0, 0)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.cylindricalToPolar(new Vector(0, 0, 1)));
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.cylindricalToPolar(new Vector(0, 0, -1)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.cylindricalToPolar(new Vector(0, 1, 0)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(0, -1, 0)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.cylindricalToPolar(new Vector(0, 1, 1)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.cylindricalToPolar(new Vector(0, 1, -1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(0, -1, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(0, -1, -1)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.cylindricalToPolar(new Vector(1, 0, 0)));
        Assert.assertEquals(new Vector(1, Math.PI), CoordinateUtility.cylindricalToPolar(new Vector(-1, 0, 0)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.cylindricalToPolar(new Vector(1, 0, 1)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.cylindricalToPolar(new Vector(1, 0, -1)));
        Assert.assertEquals(new Vector(1, Math.PI), CoordinateUtility.cylindricalToPolar(new Vector(-1, 0, 1)));
        Assert.assertEquals(new Vector(1, Math.PI), CoordinateUtility.cylindricalToPolar(new Vector(-1, 0, -1)));
        Assert.assertEquals(new Vector(1, 1), CoordinateUtility.cylindricalToPolar(new Vector(1, 1, 0)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(1, -1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI + 1), CoordinateUtility.cylindricalToPolar(new Vector(-1, 1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(-1, -1, 0)));
        Assert.assertEquals(new Vector(1, 1), CoordinateUtility.cylindricalToPolar(new Vector(1, 1, 1)));
        Assert.assertEquals(new Vector(1, 1), CoordinateUtility.cylindricalToPolar(new Vector(1, 1, -1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(1, -1, 1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(1, -1, -1)));
        Assert.assertEquals(new Vector(1, Math.PI + 1), CoordinateUtility.cylindricalToPolar(new Vector(-1, 1, 1)));
        Assert.assertEquals(new Vector(1, Math.PI + 1), CoordinateUtility.cylindricalToPolar(new Vector(-1, 1, -1)));
        Assert.assertEquals(new Vector(1, Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(-1, -1, -1)));
        Assert.assertEquals(new Vector(9.0, 4.0), CoordinateUtility.cylindricalToPolar(new Vector(9, 4, 8)));
        Assert.assertEquals(new Vector(5.48E-5, 3.39E-9), CoordinateUtility.cylindricalToPolar(new Vector(0.0000548, 0.00000000339, 0.00010003477)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415926569797925), CoordinateUtility.cylindricalToPolar(new Vector(-0.0000548, 0.00000000339, -0.00010003477)));
        Assert.assertEquals(new Vector(1.5487552, 1.649995), CoordinateUtility.cylindricalToPolar(new Vector(1.5487552, 1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(1.5487552, 4.633190307179586), CoordinateUtility.cylindricalToPolar(new Vector(1.5487552, -1.649995, 0.8842111)));
        Assert.assertEquals(new Vector(Math.PI, Math.PI / 2), CoordinateUtility.cylindricalToPolar(new Vector(Math.PI, Math.PI / 2, 3 * Math.PI)));
        
        //2D vector
        Assert.assertEquals(new Vector(0, 0), CoordinateUtility.cylindricalToPolar(new Vector(0, 0)));
        Assert.assertEquals(new Vector(0, 1), CoordinateUtility.cylindricalToPolar(new Vector(0, 1)));
        Assert.assertEquals(new Vector(0, 2 * Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(0, -1)));
        Assert.assertEquals(new Vector(1, 0), CoordinateUtility.cylindricalToPolar(new Vector(1, 0)));
        Assert.assertEquals(new Vector(1, Math.PI), CoordinateUtility.cylindricalToPolar(new Vector(-1, 0)));
        Assert.assertEquals(new Vector(1, 1), CoordinateUtility.cylindricalToPolar(new Vector(1, 1)));
        Assert.assertEquals(new Vector(1, 2 * Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(1, -1)));
        Assert.assertEquals(new Vector(1, Math.PI + 1), CoordinateUtility.cylindricalToPolar(new Vector(-1, 1)));
        Assert.assertEquals(new Vector(1, Math.PI - 1), CoordinateUtility.cylindricalToPolar(new Vector(-1, -1)));
        Assert.assertEquals(new Vector(9.0, 4.0), CoordinateUtility.cylindricalToPolar(new Vector(9, 4)));
        Assert.assertEquals(new Vector(5.48E-5, 3.39E-9), CoordinateUtility.cylindricalToPolar(new Vector(0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(5.48E-5, 3.1415926569797925), CoordinateUtility.cylindricalToPolar(new Vector(-0.0000548, 0.00000000339)));
        Assert.assertEquals(new Vector(1.5487552, 1.649995), CoordinateUtility.cylindricalToPolar(new Vector(1.5487552, 1.649995)));
        Assert.assertEquals(new Vector(1.5487552, 4.633190307179586), CoordinateUtility.cylindricalToPolar(new Vector(1.5487552, -1.649995)));
        Assert.assertEquals(new Vector(Math.PI, Math.PI / 2), CoordinateUtility.cylindricalToPolar(new Vector(Math.PI, Math.PI / 2)));
        
        //conversion
        Vector cylindricalToCartesian = CoordinateUtility.cylindricalToCartesian(new Vector(8.1560, 0.1123, -1.7988));
        Vector cylindricalToSpherical = CoordinateUtility.cylindricalToSpherical(new Vector(8.1560, 0.1123, -1.7988));
        Vector cylindricalToPolar = CoordinateUtility.cylindricalToPolar(new Vector(8.1560, 0.1123, -1.7988));
        Vector cartesianTest = CoordinateUtility.cartesianToPolar(cylindricalToCartesian);
        Vector sphericalTest = CoordinateUtility.sphericalToPolar(cylindricalToSpherical);
        Vector polarTest = CoordinateUtility.cylindricalToPolar(CoordinateUtility.polarToCylindrical(cylindricalToPolar));
        Assert.assertEquals(cylindricalToPolar, cartesianTest);
        Assert.assertEquals(cylindricalToPolar, sphericalTest);
        Assert.assertEquals(cylindricalToPolar, polarTest);
        
        //coordinates        
        PowerMockito.mockStatic(CoordinateUtility.class);
        PowerMockito.when(CoordinateUtility.class, "cylindricalToPolar", ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        CoordinateUtility.cylindricalToPolar(new Vector(8.1560, 0.1123, -1.7988));
        CoordinateUtility.cylindricalToPolar(new Vector(-1.7988, 0.1123));
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToPolar(ArgumentMatchers.eq(8.1560), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(-1.7988));
        CoordinateUtility.cylindricalToPolar(ArgumentMatchers.eq(-1.7988), ArgumentMatchers.eq(0.1123), ArgumentMatchers.eq(0));
    }
    
    /**
     * JUnit test of convert.
     *
     * @throws Exception When there is an exception.
     * @see CoordinateUtility#convert(CoordinateUtility.CoordinateSystem, CoordinateUtility.CoordinateSystem, Vector)
     * @see CoordinateUtility#convert(CoordinateUtility.CoordinateSystem, CoordinateUtility.CoordinateSystem, double, double, double)
     * @see CoordinateUtility#convert(CoordinateUtility.CoordinateSystem, CoordinateUtility.CoordinateSystem, double, double)
     */
    @Test
    public void testConvert() throws Exception {
        PowerMockito.mockStatic(CoordinateUtility.class);
        
        PowerMockito.when(CoordinateUtility.class, "convert", ArgumentMatchers.any(CoordinateUtility.CoordinateSystem.class), ArgumentMatchers.any(CoordinateUtility.CoordinateSystem.class), ArgumentMatchers.any(Vector.class)).thenCallRealMethod();
        PowerMockito.when(CoordinateUtility.class, "convert", ArgumentMatchers.any(CoordinateUtility.CoordinateSystem.class), ArgumentMatchers.any(CoordinateUtility.CoordinateSystem.class), ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()).thenCallRealMethod();
        PowerMockito.when(CoordinateUtility.class, "convert", ArgumentMatchers.any(CoordinateUtility.CoordinateSystem.class), ArgumentMatchers.any(CoordinateUtility.CoordinateSystem.class), ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()).thenCallRealMethod();
        
        Vector testCoordinates1 = new Vector(1, 1, 1);
        Vector testCoordinates2 = new Vector(1, 1);
        Vector testCoordinates3 = new Vector(1, 1, 0);
        Vector coordinates;
        
        //3D vector
        
        //cartesian to cartesian
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates1, coordinates);
        
        //catesian to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToSpherical(ArgumentMatchers.eq(testCoordinates1));
        
        //catesian to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToPolar(ArgumentMatchers.eq(testCoordinates1));
        
        //cartesian to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToCylindrical(ArgumentMatchers.eq(testCoordinates1));
        
        //cartesian to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, null, testCoordinates1);
        Assert.assertNull(coordinates);
        
        //spherical to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToCartesian(ArgumentMatchers.eq(testCoordinates1));
        
        //spherical to spherical
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates1, coordinates);
        
        //spherical to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToPolar(ArgumentMatchers.eq(testCoordinates1));
        
        //spherical to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToCylindrical(ArgumentMatchers.eq(testCoordinates1));
        
        //spherical to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, null, testCoordinates1);
        Assert.assertNull(coordinates);
        
        //polar to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToCartesian(ArgumentMatchers.eq(testCoordinates1));
        
        //polar to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToSpherical(ArgumentMatchers.eq(testCoordinates1));
        
        //polar to polar
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates1, coordinates);
        
        //polar to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToCylindrical(ArgumentMatchers.eq(testCoordinates1));
        
        //polar to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, null, testCoordinates1);
        Assert.assertNull(coordinates);
        
        //cylindrical to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToCartesian(ArgumentMatchers.eq(testCoordinates1));
        
        //cylindrical to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToSpherical(ArgumentMatchers.eq(testCoordinates1));
        
        //cylindrical to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToPolar(ArgumentMatchers.eq(testCoordinates1));
        
        //cylindrical to cylindrical
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates1, coordinates);
        
        //cylindrical to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, null, testCoordinates1);
        Assert.assertNull(coordinates);
        
        //null to cartesian
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates1);
        Assert.assertNull(coordinates);
        
        //null to spherical
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates1);
        Assert.assertNull(coordinates);
        
        //null to polar
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates1);
        Assert.assertNull(coordinates);
        
        //null to cylindrical
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates1);
        Assert.assertNull(coordinates);
        
        //null to null
        coordinates = CoordinateUtility.convert(null, null, testCoordinates1);
        Assert.assertNull(coordinates);
        
        //2D vector
        
        //cartesian to cartesian
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates2);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates2, coordinates);
        
        //catesian to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToSpherical(ArgumentMatchers.eq(testCoordinates2));
        
        //catesian to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToPolar(ArgumentMatchers.eq(testCoordinates2));
        
        //cartesian to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToCylindrical(ArgumentMatchers.eq(testCoordinates2));
        
        //cartesian to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, null, testCoordinates2);
        Assert.assertNull(coordinates);
        
        //spherical to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToCartesian(ArgumentMatchers.eq(testCoordinates2));
        
        //spherical to spherical
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates2);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates2, coordinates);
        
        //spherical to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToPolar(ArgumentMatchers.eq(testCoordinates2));
        
        //spherical to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToCylindrical(ArgumentMatchers.eq(testCoordinates2));
        
        //spherical to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, null, testCoordinates2);
        Assert.assertNull(coordinates);
        
        //polar to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToCartesian(ArgumentMatchers.eq(testCoordinates2));
        
        //polar to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToSpherical(ArgumentMatchers.eq(testCoordinates2));
        
        //polar to polar
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates2);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates2, coordinates);
        
        //polar to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToCylindrical(ArgumentMatchers.eq(testCoordinates2));
        
        //polar to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, null, testCoordinates2);
        Assert.assertNull(coordinates);
        
        //cylindrical to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToCartesian(ArgumentMatchers.eq(testCoordinates2));
        
        //cylindrical to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToSpherical(ArgumentMatchers.eq(testCoordinates2));
        
        //cylindrical to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates2);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToPolar(ArgumentMatchers.eq(testCoordinates2));
        
        //cylindrical to cylindrical
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates2);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates2, coordinates);
        
        //cylindrical to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, null, testCoordinates2);
        Assert.assertNull(coordinates);
        
        //null to cartesian
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.CARTESIAN, testCoordinates2);
        Assert.assertNull(coordinates);
        
        //null to spherical
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.SPHERICAL, testCoordinates2);
        Assert.assertNull(coordinates);
        
        //null to polar
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.POLAR, testCoordinates2);
        Assert.assertNull(coordinates);
        
        //null to cylindrical
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.CYLINDRICAL, testCoordinates2);
        Assert.assertNull(coordinates);
        
        //null to null
        coordinates = CoordinateUtility.convert(null, null, testCoordinates2);
        Assert.assertNull(coordinates);
        
        //3 coordinates
        
        //cartesian to cartesian
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1, 1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates1, coordinates);
        
        //catesian to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.cartesianToSpherical(ArgumentMatchers.eq(testCoordinates1));
        
        //catesian to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.POLAR, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.cartesianToPolar(ArgumentMatchers.eq(testCoordinates1));
        
        //cartesian to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.cartesianToCylindrical(ArgumentMatchers.eq(testCoordinates1));
        
        //cartesian to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, null, 1, 1, 1);
        Assert.assertNull(coordinates);
        
        //spherical to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.sphericalToCartesian(ArgumentMatchers.eq(testCoordinates1));
        
        //spherical to spherical
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1, 1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates1, coordinates);
        
        //spherical to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.POLAR, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.sphericalToPolar(ArgumentMatchers.eq(testCoordinates1));
        
        //spherical to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.sphericalToCylindrical(ArgumentMatchers.eq(testCoordinates1));
        
        //spherical to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, null, 1, 1, 1);
        Assert.assertNull(coordinates);
        
        //polar to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.polarToCartesian(ArgumentMatchers.eq(testCoordinates1));
        
        //polar to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.polarToSpherical(ArgumentMatchers.eq(testCoordinates1));
        
        //polar to polar
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.POLAR, 1, 1, 1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates1, coordinates);
        
        //polar to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.polarToCylindrical(ArgumentMatchers.eq(testCoordinates1));
        
        //polar to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, null, 1, 1, 1);
        Assert.assertNull(coordinates);
        
        //cylindrical to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.cylindricalToCartesian(ArgumentMatchers.eq(testCoordinates1));
        
        //cylindrical to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.cylindricalToSpherical(ArgumentMatchers.eq(testCoordinates1));
        
        //cylindrical to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.POLAR, 1, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class, VerificationModeFactory.times(2));
        CoordinateUtility.cylindricalToPolar(ArgumentMatchers.eq(testCoordinates1));
        
        //cylindrical to cylindrical
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1, 1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates1, coordinates);
        
        //cylindrical to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, null, 1, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to cartesian
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to spherical
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to polar
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.POLAR, 1, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to cylindrical
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to null
        coordinates = CoordinateUtility.convert(null, null, 1, 1, 1);
        Assert.assertNull(coordinates);
        
        //2 coordinates
        
        //cartesian to cartesian
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates3, coordinates);
        
        //catesian to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToSpherical(ArgumentMatchers.eq(testCoordinates3));
        
        //catesian to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.POLAR, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToPolar(ArgumentMatchers.eq(testCoordinates3));
        
        //cartesian to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cartesianToCylindrical(ArgumentMatchers.eq(testCoordinates3));
        
        //cartesian to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CARTESIAN, null, 1, 1);
        Assert.assertNull(coordinates);
        
        //spherical to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToCartesian(ArgumentMatchers.eq(testCoordinates3));
        
        //spherical to spherical
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates3, coordinates);
        
        //spherical to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.POLAR, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToPolar(ArgumentMatchers.eq(testCoordinates3));
        
        //spherical to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.sphericalToCylindrical(ArgumentMatchers.eq(testCoordinates3));
        
        //spherical to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.SPHERICAL, null, 1, 1);
        Assert.assertNull(coordinates);
        
        //polar to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToCartesian(ArgumentMatchers.eq(testCoordinates3));
        
        //polar to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToSpherical(ArgumentMatchers.eq(testCoordinates3));
        
        //polar to polar
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.POLAR, 1, 1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates3, coordinates);
        
        //polar to cylindrical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.polarToCylindrical(ArgumentMatchers.eq(testCoordinates3));
        
        //polar to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.POLAR, null, 1, 1);
        Assert.assertNull(coordinates);
        
        //cylindrical to cartesian
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToCartesian(ArgumentMatchers.eq(testCoordinates3));
        
        //cylindrical to spherical
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToSpherical(ArgumentMatchers.eq(testCoordinates3));
        
        //cylindrical to polar
        CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.POLAR, 1, 1);
        PowerMockito.verifyStatic(CoordinateUtility.class);
        CoordinateUtility.cylindricalToPolar(ArgumentMatchers.eq(testCoordinates3));
        
        //cylindrical to cylindrical
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1);
        Assert.assertNotNull(coordinates);
        Assert.assertEquals(testCoordinates3, coordinates);
        
        //cylindrical to null
        coordinates = CoordinateUtility.convert(CoordinateUtility.CoordinateSystem.CYLINDRICAL, null, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to cartesian
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.CARTESIAN, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to spherical
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.SPHERICAL, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to polar
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.POLAR, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to cylindrical
        coordinates = CoordinateUtility.convert(null, CoordinateUtility.CoordinateSystem.CYLINDRICAL, 1, 1);
        Assert.assertNull(coordinates);
        
        //null to null
        coordinates = CoordinateUtility.convert(null, null, 1, 1);
        Assert.assertNull(coordinates);
    }
    
}
