/*
 * File:    BoundUtilityTest.java
 * Package: commons.math
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math;

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
 * JUnit test of BoundUtility.
 *
 * @see BoundUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BoundUtility.class})
public class BoundUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BoundUtilityTest.class);
    
    
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
     * JUnit test of inBounds.
     *
     * @throws Exception When there is an exception.
     * @see BoundUtility#inBounds(Number, Number, Number, boolean, boolean)
     * @see BoundUtility#inBounds(Number, Number, Number)
     */
    @Test
    public void testInBounds() throws Exception {
        //int
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6));
        Assert.assertTrue(BoundUtility.inBounds(4, 4, 6));
        Assert.assertTrue(BoundUtility.inBounds(6, 2, 6));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6));
        
        //float
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.5, 6.4));
        Assert.assertTrue(BoundUtility.inBounds(6.4, 2.4, 6.4));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6));
        Assert.assertTrue(BoundUtility.inBounds(4.4, 4.4, 4.6));
        Assert.assertTrue(BoundUtility.inBounds(4.6, 4.4, 4.6));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6));
        
        //double
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001));
        Assert.assertTrue(BoundUtility.inBounds(4.4999, 4.4999, 4.5001));
        Assert.assertTrue(BoundUtility.inBounds(4.5001, 4.4999, 4.5001));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001));
        
        //long
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L));
        Assert.assertTrue(BoundUtility.inBounds(123456788L, 123456788L, 123456790L));
        Assert.assertTrue(BoundUtility.inBounds(123456790L, 123456788L, 123456790L));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L));
        
        //int, touch upper and lower bounds
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4, 4, 6, true, true));
        Assert.assertTrue(BoundUtility.inBounds(6, 2, 6, true, true));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6, true, true));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6, true, true));
        
        //float, touch upper and lower bounds
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.5, 6.4, true, true));
        Assert.assertTrue(BoundUtility.inBounds(6.4, 2.4, 6.4, true, true));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4, true, true));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.4, 4.4, 4.6, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.6, 4.4, 4.6, true, true));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6, true, true));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6, true, true));
        
        //double, touch upper and lower bounds
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.4999, 4.4999, 4.5001, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5001, 4.4999, 4.5001, true, true));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001, true, true));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001, true, true));
        
        //long, touch upper and lower bounds
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L, true, true));
        Assert.assertTrue(BoundUtility.inBounds(123456788L, 123456788L, 123456790L, true, true));
        Assert.assertTrue(BoundUtility.inBounds(123456790L, 123456788L, 123456790L, true, true));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L, true, true));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L, true, true));
        
        //int, touch upper bound
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4, 4, 6, false, true));
        Assert.assertTrue(BoundUtility.inBounds(6, 2, 6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6, false, true));
        
        //float, touch upper bound
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.5, 4.5, 6.4, false, true));
        Assert.assertTrue(BoundUtility.inBounds(6.4, 2.4, 6.4, false, true));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4, false, true));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4, false, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.4, 4.4, 4.6, false, true));
        Assert.assertTrue(BoundUtility.inBounds(4.6, 4.4, 4.6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6, false, true));
        
        //double, touch upper bound
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.4999, 4.4999, 4.5001, false, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5001, 4.4999, 4.5001, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001, false, true));
        
        //long, touch upper bound
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L, false, true));
        Assert.assertFalse(BoundUtility.inBounds(123456788L, 123456788L, 123456790L, false, true));
        Assert.assertTrue(BoundUtility.inBounds(123456790L, 123456788L, 123456790L, false, true));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L, false, true));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L, false, true));
        
        //int, touch lower bound
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4, 4, 6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(6, 2, 6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6, true, false));
        
        //float, touch lower bound
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.5, 6.4, true, false));
        Assert.assertFalse(BoundUtility.inBounds(6.4, 2.4, 6.4, true, false));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4, true, false));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4.4, 4.4, 4.6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.6, 4.4, 4.6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6, true, false));
        
        //double, touch lower bound
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4.4999, 4.4999, 4.5001, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5001, 4.4999, 4.5001, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001, true, false));
        
        //long, touch lower bound
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L, true, false));
        Assert.assertTrue(BoundUtility.inBounds(123456788L, 123456788L, 123456790L, true, false));
        Assert.assertFalse(BoundUtility.inBounds(123456790L, 123456788L, 123456790L, true, false));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L, true, false));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L, true, false));
        
        //int, no touching bounds
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4, 4, 6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(6, 2, 6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6, false, false));
        
        //float, no touching bounds
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5, 4.5, 6.4, false, false));
        Assert.assertFalse(BoundUtility.inBounds(6.4, 2.4, 6.4, false, false));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4, false, false));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4, false, false));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.4, 4.4, 4.6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.6, 4.4, 4.6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6, false, false));
        
        //double, no touching bounds
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.4999, 4.4999, 4.5001, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5001, 4.4999, 4.5001, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001, false, false));
        
        //long, no touching bounds
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L, false, false));
        Assert.assertFalse(BoundUtility.inBounds(123456788L, 123456788L, 123456790L, false, false));
        Assert.assertFalse(BoundUtility.inBounds(123456790L, 123456788L, 123456790L, false, false));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L, false, false));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L, false, false));
        
        //edge cases
        Assert.assertTrue(BoundUtility.inBounds(0, 0, 0));
        Assert.assertFalse(BoundUtility.inBounds(0, 0, 0, true, false));
        Assert.assertFalse(BoundUtility.inBounds(0, 0, 0, false, true));
        Assert.assertFalse(BoundUtility.inBounds(0, 0, 0, false, false));
    }
    
    /**
     * JUnit test of truncateNum.
     *
     * @throws Exception When there is an exception.
     * @see BoundUtility#truncateNum(Number, Number, Number)
     */
    @Test
    public void testTruncateNum() throws Exception {
        //int
        Assert.assertEquals(4, BoundUtility.truncateNum(4, 2, 6));
        Assert.assertEquals(4, BoundUtility.truncateNum(4, 4, 6));
        Assert.assertEquals(6, BoundUtility.truncateNum(6, 2, 6));
        Assert.assertEquals(4, BoundUtility.truncateNum(1, 4, 6));
        Assert.assertEquals(6, BoundUtility.truncateNum(7, 4, 6));
        
        //float
        Assert.assertEquals(4.5, BoundUtility.truncateNum(4.5, 2.4, 6.4));
        Assert.assertEquals(4.5, BoundUtility.truncateNum(4.5, 4.5, 6.4));
        Assert.assertEquals(6.4, BoundUtility.truncateNum(6.4, 2.4, 6.4));
        Assert.assertEquals(4.5, BoundUtility.truncateNum(1.9, 4.5, 6.4));
        Assert.assertEquals(6.4, BoundUtility.truncateNum(7.1, 4.5, 6.4));
        Assert.assertEquals(4.5, BoundUtility.truncateNum(4.5, 4.4, 4.6));
        Assert.assertEquals(4.4, BoundUtility.truncateNum(4.4, 4.4, 4.6));
        Assert.assertEquals(4.6, BoundUtility.truncateNum(4.6, 4.4, 4.6));
        Assert.assertEquals(4.4, BoundUtility.truncateNum(4.3, 4.4, 4.6));
        Assert.assertEquals(4.6, BoundUtility.truncateNum(4.7, 4.4, 4.6));
        
        //double
        Assert.assertEquals(4.5, BoundUtility.truncateNum(4.5, 4.4999, 4.5001));
        Assert.assertEquals(4.4999, BoundUtility.truncateNum(4.4999, 4.4999, 4.5001));
        Assert.assertEquals(4.5001, BoundUtility.truncateNum(4.5001, 4.4999, 4.5001));
        Assert.assertEquals(4.4999, BoundUtility.truncateNum(4.4998, 4.4999, 4.5001));
        Assert.assertEquals(4.5001, BoundUtility.truncateNum(4.5002, 4.4999, 4.5001));
        
        //long
        Assert.assertEquals(123456789L, BoundUtility.truncateNum(123456789L, 123456788L, 123456790L));
        Assert.assertEquals(123456788L, BoundUtility.truncateNum(123456788L, 123456788L, 123456790L));
        Assert.assertEquals(123456790L, BoundUtility.truncateNum(123456790L, 123456788L, 123456790L));
        Assert.assertEquals(123456788L, BoundUtility.truncateNum(123456787L, 123456788L, 123456790L));
        Assert.assertEquals(123456790L, BoundUtility.truncateNum(123456791L, 123456788L, 123456790L));
    }
    
}
