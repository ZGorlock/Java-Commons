/*
 * File:    MathUtilityTest.java
 * Package: commons.math
 * Author:  Zachary Gill
 */

package commons.math;

import java.util.Arrays;

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
 * JUnit test of MathUtility.
 *
 * @see MathUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({MathUtility.class})
public class MathUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MathUtilityTest.class);
    
    
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
     * JUnit test of random.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#random(int, int)
     * @see MathUtility#random(int)
     */
    @Test
    public void testRandom() throws Exception {
        int value;
        
        //default, min
        do {
            value = MathUtility.random(10);
        } while (value != 0);
        
        //default, max
        do {
            value = MathUtility.random(10);
        } while (value != 10);
        
        //default, range
        for (int i = 0; i < 1000000; i++) {
            value = MathUtility.random(10);
            Assert.assertTrue(value >= 0);
            Assert.assertTrue(value <= 10);
        }
        
        //range, min
        do {
            value = MathUtility.random(3, 21);
        } while (value != 3);
        
        //range, max
        do {
            value = MathUtility.random(3, 21);
        } while (value != 21);
        
        //range, range
        for (int i = 0; i < 1000000; i++) {
            value = MathUtility.random(3, 21);
            Assert.assertTrue(value >= 3);
            Assert.assertTrue(value <= 21);
        }
    }
    
    /**
     * JUnit test of dice.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#dice(int, int)
     * @see MathUtility#dice(int)
     */
    @Test
    public void testDice() throws Exception {
        //standard
        for (int sides : Arrays.asList(2, 4, 6, 8, 10, 12, 20, 100)) {
            for (int i = 0; i < 1000; i++) {
                int roll = MathUtility.dice(sides);
                Assert.assertTrue(roll >= 1);
                Assert.assertTrue(roll <= sides);
            }
        }
        
        //multiple rolls
        for (int sides : Arrays.asList(2, 4, 6, 8, 10, 12, 20, 100)) {
            for (int i = 0; i < 1000; i++) {
                int rolls = MathUtility.random(1, 50);
                int roll = MathUtility.dice(sides, rolls);
                Assert.assertTrue(roll >= rolls);
                Assert.assertTrue(roll <= (sides * rolls));
            }
        }
        
        //invalid
        Assert.assertEquals(0, MathUtility.dice(0));
        Assert.assertEquals(0, MathUtility.dice(-1));
        Assert.assertEquals(0, MathUtility.dice(-999));
        Assert.assertEquals(0, MathUtility.dice(6, 0));
        Assert.assertEquals(0, MathUtility.dice(6, -1));
        Assert.assertEquals(0, MathUtility.dice(6, -999));
        Assert.assertEquals(0, MathUtility.dice(0, 0));
        Assert.assertEquals(0, MathUtility.dice(-1, 0));
        Assert.assertEquals(0, MathUtility.dice(0, -1));
        Assert.assertEquals(0, MathUtility.dice(-999, 0));
        Assert.assertEquals(0, MathUtility.dice(0, -999));
    }
    
    /**
     * JUnit test of mapValue.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#mapValue(double, double, double, double, double)
     */
    @Test
    public void testMapValue() throws Exception {
        //same ranges
        Assert.assertEquals(3, MathUtility.mapValue(3, 2, 8, 2, 8), 0.0000001);
        Assert.assertEquals(3, MathUtility.mapValue(3, 3, 8, 3, 8), 0.0000001);
        Assert.assertEquals(3, MathUtility.mapValue(3, 1, 3, 1, 3), 0.0000001);
        
        //standard
        Assert.assertEquals(10, MathUtility.mapValue(1, 0, 10, 0, 100), 0.0000001);
        Assert.assertEquals(18, MathUtility.mapValue(1.8, 0, 10, 0, 100), 0.0000001);
        Assert.assertEquals(18, MathUtility.mapValue(1.8, 0, 10, 18, 18), 0.0000001);
        Assert.assertEquals(4.3, MathUtility.mapValue(6, 2, 12, 3.5, 5.5), 0.0000001);
        Assert.assertEquals(0.15000249644736335, MathUtility.mapValue(72, 59, 52133, 0.15, 0.16), 0.0000001);
        
        //edge cases
        Assert.assertEquals(6, MathUtility.mapValue(15, 20, 25, 6, 39), 0.0000001);
        Assert.assertEquals(5, MathUtility.mapValue(15, 0, 10, 4, 5), 0.0000001);
    }
    
}
