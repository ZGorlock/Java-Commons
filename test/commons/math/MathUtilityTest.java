/*
 * File:    MathUtilityTest.java
 * Package: commons.math
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;

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
 * JUnit test of MathUtility.
 *
 * @see MathUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
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
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of random.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#random(long, long)
     * @see MathUtility#random(int, int)
     * @see MathUtility#random(long)
     * @see MathUtility#random(int)
     */
    @Test
    public void testRandom() throws Exception {
        int intValue;
        long longValue;
        
        //int, default, min
        do {
            intValue = MathUtility.random(10);
            Assert.assertTrue(BoundUtility.inBounds(intValue, 0, 10));
        } while (intValue != 0);
        
        //int, default, max
        do {
            intValue = MathUtility.random(10);
            Assert.assertTrue(BoundUtility.inBounds(intValue, 0, 10));
        } while (intValue != 10);
        
        //int, default, range
        for (int i = 0; i < 100000; i++) {
            intValue = MathUtility.random(10);
            Assert.assertTrue(BoundUtility.inBounds(intValue, 0, 10));
        }
        
        //int, range, min
        do {
            intValue = MathUtility.random(3, 21);
            Assert.assertTrue(BoundUtility.inBounds(intValue, 3, 21));
        } while (intValue != 3);
        
        //int, range, max
        do {
            intValue = MathUtility.random(3, 21);
            Assert.assertTrue(BoundUtility.inBounds(intValue, 3, 21));
        } while (intValue != 21);
        
        //int, range, range
        for (int i = 0; i < 100000; i++) {
            intValue = MathUtility.random(3, 21);
            Assert.assertTrue(BoundUtility.inBounds(intValue, 3, 21));
        }
        
        //long, default, min
        do {
            longValue = MathUtility.random(10);
            Assert.assertTrue(BoundUtility.inBounds(longValue, 0, 10));
        } while (longValue != 0);
        
        //long, default, max
        do {
            longValue = MathUtility.random(10);
            Assert.assertTrue(BoundUtility.inBounds(longValue, 0, 10));
        } while (longValue != 10);
        
        //long, default, range
        for (int i = 0; i < 100000; i++) {
            longValue = MathUtility.random(10);
            Assert.assertTrue(BoundUtility.inBounds(longValue, 0, 10));
        }
        
        //long, range, min
        do {
            longValue = MathUtility.random(1984560454894L, 1984560454906L);
            Assert.assertTrue(BoundUtility.inBounds(longValue, 1984560454894L, 1984560454906L));
        } while (longValue != 1984560454894L);
        
        //long, range, max
        do {
            longValue = MathUtility.random(1984560454894L, 1984560454906L);
            Assert.assertTrue(BoundUtility.inBounds(longValue, 1984560454894L, 1984560454906L));
        } while (longValue != 1984560454906L);
        
        //long, range, range
        for (int i = 0; i < 100000; i++) {
            longValue = MathUtility.random(1984560454894L, 1984560454906L);
            Assert.assertTrue(BoundUtility.inBounds(longValue, 1984560454894L, 1984560454906L));
        }
    }
    
    /**
     * JUnit test of dice.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#dice(long, long)
     * @see MathUtility#dice(int, int)
     * @see MathUtility#dice(long)
     * @see MathUtility#dice(int)
     */
    @Test
    public void testDice() throws Exception {
        //int, standard
        for (int sides : Arrays.asList(2, 4, 6, 8, 10, 12, 20, 100)) {
            for (int i = 0; i < 1000; i++) {
                int roll = MathUtility.dice(sides);
                Assert.assertTrue(BoundUtility.inBounds(roll, 1, sides));
            }
        }
        
        //int, multiple rolls
        for (int sides : Arrays.asList(2, 4, 6, 8, 10, 12, 20, 100)) {
            for (int i = 0; i < 1000; i++) {
                int rolls = MathUtility.random(1, 50);
                int roll = MathUtility.dice(sides, rolls);
                Assert.assertTrue(BoundUtility.inBounds(roll, rolls, (sides * rolls)));
            }
        }
        
        //long, standard
        int d = 1;
        for (long sides : Arrays.asList(2L, 4L, 6L, 8L, 10L, 12L, 20L, 165406549187821L)) {
            for (int i = 0; i < 1000; i++) {
                long roll = MathUtility.dice(sides);
                Assert.assertTrue(BoundUtility.inBounds(roll, 1, sides));
            }
        }
        
        //long, multiple rolls
        for (long sides : Arrays.asList(2L, 4L, 6L, 8L, 10L, 12L, 20L, 165406549187821L)) {
            for (int i = 0; i < 1000; i++) {
                long rolls = MathUtility.random(1L, 50L);
                long roll = MathUtility.dice(sides, rolls);
                Assert.assertTrue(BoundUtility.inBounds(roll, rolls, (sides * rolls)));
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
        Assert.assertEquals(0, MathUtility.dice(0L));
        Assert.assertEquals(0, MathUtility.dice(-1L));
        Assert.assertEquals(0, MathUtility.dice(-489460546514987498L));
        Assert.assertEquals(0, MathUtility.dice(6L, 0L));
        Assert.assertEquals(0, MathUtility.dice(6L, -1L));
        Assert.assertEquals(0, MathUtility.dice(6L, -489460546514987498L));
        Assert.assertEquals(0, MathUtility.dice(0L, 0L));
        Assert.assertEquals(0, MathUtility.dice(-1L, 0L));
        Assert.assertEquals(0, MathUtility.dice(0L, -1L));
        Assert.assertEquals(0, MathUtility.dice(-489460546514987498L, 0L));
        Assert.assertEquals(0, MathUtility.dice(0L, -489460546514987498L));
    }
    
    /**
     * JUnit test of coinFlip.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#coinFlip()
     */
    @Test
    public void testCoinFlip() throws Exception {
        //standard
        int heads = 0;
        int tails = 0;
        for (int i = 0; i < 1000000; i++) {
            boolean coinFlip = MathUtility.coinFlip();
            heads += (coinFlip ? 1 : 0);
            tails += (coinFlip ? 0 : 1);
        }
        Assert.assertTrue(Math.abs(heads - tails) < (1000000 * 0.01));
    }
    
    /**
     * JUnit test of isSquare.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#isSquare(long)
     */
    @Test
    public void testIsSquare() throws Exception {
        //standard
        Assert.assertTrue(MathUtility.isSquare(0));
        Assert.assertTrue(MathUtility.isSquare(1));
        Assert.assertTrue(MathUtility.isSquare(4));
        Assert.assertTrue(MathUtility.isSquare(9));
        Assert.assertTrue(MathUtility.isSquare(25));
        Assert.assertTrue(MathUtility.isSquare(100));
        Assert.assertTrue(MathUtility.isSquare(10000));
        Assert.assertTrue(MathUtility.isSquare(1048576));
        Assert.assertFalse(MathUtility.isSquare(2));
        Assert.assertFalse(MathUtility.isSquare(3));
        Assert.assertFalse(MathUtility.isSquare(8));
        Assert.assertFalse(MathUtility.isSquare(99));
        Assert.assertFalse(MathUtility.isSquare(10001));
        Assert.assertFalse(MathUtility.isSquare(1543211));
        
        //invalid
        Assert.assertFalse(MathUtility.isSquare(-1));
        Assert.assertFalse(MathUtility.isSquare(-16));
    }
    
    /**
     * JUnit test of isPrime.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#isPrime(long)
     */
    @Test
    public void testIsPrime() throws Exception {
        //standard
        Assert.assertTrue(MathUtility.isPrime(2));
        Assert.assertTrue(MathUtility.isPrime(3));
        Assert.assertTrue(MathUtility.isPrime(17));
        Assert.assertTrue(MathUtility.isPrime(197));
        Assert.assertTrue(MathUtility.isPrime(2731));
        Assert.assertTrue(MathUtility.isPrime(16033));
        Assert.assertTrue(MathUtility.isPrime(512927377));
        Assert.assertFalse(MathUtility.isPrime(0));
        Assert.assertFalse(MathUtility.isPrime(1));
        Assert.assertFalse(MathUtility.isPrime(4));
        Assert.assertFalse(MathUtility.isPrime(9));
        Assert.assertFalse(MathUtility.isPrime(20));
        Assert.assertFalse(MathUtility.isPrime(100));
        Assert.assertFalse(MathUtility.isPrime(2022));
        Assert.assertFalse(MathUtility.isPrime(1048576));
        
        //invalid
        Assert.assertFalse(MathUtility.isPrime(-1));
        Assert.assertFalse(MathUtility.isPrime(-16));
    }
    
    /**
     * JUnit test of isDivisibleBy.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#isDivisibleBy(long, long)
     */
    @Test
    public void testIsDivisibleBy() throws Exception {
        //standard
        Assert.assertTrue(MathUtility.isDivisibleBy(10, 1));
        Assert.assertTrue(MathUtility.isDivisibleBy(10, 2));
        Assert.assertTrue(MathUtility.isDivisibleBy(10, 5));
        Assert.assertTrue(MathUtility.isDivisibleBy(10, 10));
        Assert.assertFalse(MathUtility.isDivisibleBy(10, 3));
        Assert.assertFalse(MathUtility.isDivisibleBy(10, 9));
        Assert.assertTrue(MathUtility.isDivisibleBy(10, -2));
        Assert.assertFalse(MathUtility.isDivisibleBy(10, -6));
        Assert.assertTrue(MathUtility.isDivisibleBy(1408, 8));
        Assert.assertTrue(MathUtility.isDivisibleBy(-1408, 8));
        Assert.assertTrue(MathUtility.isDivisibleBy(-1408, -8));
        Assert.assertTrue(MathUtility.isDivisibleBy(1408, -8));
        Assert.assertTrue(MathUtility.isDivisibleBy(150000, 15));
        Assert.assertTrue(MathUtility.isDivisibleBy(49024639287L, 117));
        Assert.assertTrue(MathUtility.isDivisibleBy(0, 1));
        Assert.assertTrue(MathUtility.isDivisibleBy(0, -1));
        
        //invalid
        TestUtils.assertNoException(() ->
                Assert.assertFalse(MathUtility.isDivisibleBy(10, 0)));
        TestUtils.assertNoException(() ->
                Assert.assertFalse(MathUtility.isDivisibleBy(0, 0)));
    }
    
    /**
     * JUnit test of xmod.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#xmod(long, long)
     * @see MathUtility#xmod(int, int)
     */
    @Test
    public void testXmod() throws Exception {
        //int
        Assert.assertEquals(3, MathUtility.xmod(6, 3));
        Assert.assertEquals(2, MathUtility.xmod(5, 3));
        Assert.assertEquals(1, MathUtility.xmod(4, 3));
        Assert.assertEquals(3, MathUtility.xmod(3, 3));
        Assert.assertEquals(2, MathUtility.xmod(2, 3));
        Assert.assertEquals(1, MathUtility.xmod(1, 3));
        Assert.assertEquals(3, MathUtility.xmod(0, 3));
        Assert.assertEquals(-1, MathUtility.xmod(-1, 3));
        Assert.assertEquals(-2, MathUtility.xmod(-2, 3));
        Assert.assertEquals(-3, MathUtility.xmod(-3, 3));
        Assert.assertEquals(-1, MathUtility.xmod(-4, 3));
        Assert.assertEquals(-2, MathUtility.xmod(-5, 3));
        Assert.assertEquals(-3, MathUtility.xmod(-6, 3));
        Assert.assertEquals(3, MathUtility.xmod(6, -3));
        Assert.assertEquals(2, MathUtility.xmod(5, -3));
        Assert.assertEquals(1, MathUtility.xmod(4, -3));
        Assert.assertEquals(3, MathUtility.xmod(3, -3));
        Assert.assertEquals(2, MathUtility.xmod(2, -3));
        Assert.assertEquals(1, MathUtility.xmod(1, -3));
        Assert.assertEquals(3, MathUtility.xmod(0, -3));
        Assert.assertEquals(-1, MathUtility.xmod(-1, -3));
        Assert.assertEquals(-2, MathUtility.xmod(-2, -3));
        Assert.assertEquals(-3, MathUtility.xmod(-3, -3));
        Assert.assertEquals(-1, MathUtility.xmod(-4, -3));
        Assert.assertEquals(-2, MathUtility.xmod(-5, -3));
        Assert.assertEquals(-3, MathUtility.xmod(-6, -3));
        Assert.assertEquals(61, MathUtility.xmod(154621, 160));
        Assert.assertEquals(160, MathUtility.xmod(160, 154621));
        Assert.assertEquals(141449, MathUtility.xmod(165431298, 154621));
        
        //long
        Assert.assertEquals(3L, MathUtility.xmod(6L, 3L));
        Assert.assertEquals(2L, MathUtility.xmod(5L, 3L));
        Assert.assertEquals(1L, MathUtility.xmod(4L, 3L));
        Assert.assertEquals(3L, MathUtility.xmod(3L, 3L));
        Assert.assertEquals(2L, MathUtility.xmod(2L, 3L));
        Assert.assertEquals(1L, MathUtility.xmod(1L, 3L));
        Assert.assertEquals(3L, MathUtility.xmod(0L, 3L));
        Assert.assertEquals(-1L, MathUtility.xmod(-1L, 3L));
        Assert.assertEquals(-2L, MathUtility.xmod(-2L, 3L));
        Assert.assertEquals(-3L, MathUtility.xmod(-3L, 3L));
        Assert.assertEquals(-1L, MathUtility.xmod(-4L, 3L));
        Assert.assertEquals(-2L, MathUtility.xmod(-5L, 3L));
        Assert.assertEquals(-3L, MathUtility.xmod(-6L, 3L));
        Assert.assertEquals(3L, MathUtility.xmod(6L, -3L));
        Assert.assertEquals(2L, MathUtility.xmod(5L, -3L));
        Assert.assertEquals(1L, MathUtility.xmod(4L, -3L));
        Assert.assertEquals(3L, MathUtility.xmod(3L, -3L));
        Assert.assertEquals(2L, MathUtility.xmod(2L, -3L));
        Assert.assertEquals(1L, MathUtility.xmod(1L, -3L));
        Assert.assertEquals(3L, MathUtility.xmod(0L, -3L));
        Assert.assertEquals(-1L, MathUtility.xmod(-1L, -3L));
        Assert.assertEquals(-2L, MathUtility.xmod(-2L, -3L));
        Assert.assertEquals(-3L, MathUtility.xmod(-3L, -3L));
        Assert.assertEquals(-1L, MathUtility.xmod(-4L, -3L));
        Assert.assertEquals(-2L, MathUtility.xmod(-5L, -3L));
        Assert.assertEquals(-3L, MathUtility.xmod(-6L, -3L));
        Assert.assertEquals(61L, MathUtility.xmod(154621L, 160L));
        Assert.assertEquals(160L, MathUtility.xmod(160L, 154621L));
        Assert.assertEquals(141449L, MathUtility.xmod(165431298L, 154621L));
        Assert.assertEquals(252175269L, MathUtility.xmod(894654165431298L, 654154621L));
        
        //invalid
        TestUtils.assertException(ArithmeticException.class, () ->
                MathUtility.xmod(15, 0));
    }
    
    /**
     * JUnit test of roundWithPrecision.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#roundWithPrecision(double, int)
     * @see MathUtility#roundWithPrecision(float, int)
     * @see MathUtility#roundWithPrecision(BigDecimal, int, java.math.RoundingMode)
     * @see MathUtility#roundWithPrecision(BigDecimal, int)
     */
    @Test
    public void testRoundWithPrecision() throws Exception {
        //float
        
        Assert.assertEquals("111.704",
                String.valueOf(MathUtility.roundWithPrecision(111.70400001f, 6)));
        Assert.assertEquals("111.704",
                String.valueOf(MathUtility.roundWithPrecision(111.704000000000001f, 6)));
        Assert.assertEquals("111.704",
                String.valueOf(MathUtility.roundWithPrecision(111.7040001f, 6)));
        Assert.assertEquals("111.704",
                String.valueOf(MathUtility.roundWithPrecision(111.704001f, 6)));
        Assert.assertEquals("111.704008",
                String.valueOf(MathUtility.roundWithPrecision(111.70401f, 6)));
        Assert.assertEquals("111.704008",
                String.valueOf(MathUtility.roundWithPrecision(111.70401f, 6)));
        
        Assert.assertEquals("0.5933",
                String.valueOf(MathUtility.roundWithPrecision(0.59329996f, 6)));
        Assert.assertEquals("0.5933",
                String.valueOf(MathUtility.roundWithPrecision(0.59329996f, 4)));
        Assert.assertEquals("0.59",
                String.valueOf(MathUtility.roundWithPrecision(0.59329996f, 2)));
        Assert.assertEquals("0.6",
                String.valueOf(MathUtility.roundWithPrecision(0.59329996f, 1)));
        
        Assert.assertEquals("112.0",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001f, 0)));
        Assert.assertEquals("110.0",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001f, -1)));
        Assert.assertEquals("100.0",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001f, -2)));
        Assert.assertEquals("0.0",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001f, -3)));
        
        //double
        
        Assert.assertEquals("111.704",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000000001, 12)));
        Assert.assertEquals("111.704",
                String.valueOf(MathUtility.roundWithPrecision(111.704000000000000000001, 12)));
        Assert.assertEquals("111.704",
                String.valueOf(MathUtility.roundWithPrecision(111.7040000000001, 12)));
        Assert.assertEquals("111.704000000001",
                String.valueOf(MathUtility.roundWithPrecision(111.704000000001, 12)));
        Assert.assertEquals("111.70400000001",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001, 12)));
        Assert.assertEquals("111.704",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001, 6)));
        
        Assert.assertEquals("0.5933",
                String.valueOf(MathUtility.roundWithPrecision(0.59329999999996, 12)));
        Assert.assertEquals("0.59329999999996",
                String.valueOf(MathUtility.roundWithPrecision(0.59329999999996, 14)));
        Assert.assertEquals("0.593",
                String.valueOf(MathUtility.roundWithPrecision(0.59329999999996, 3)));
        Assert.assertEquals("0.6",
                String.valueOf(MathUtility.roundWithPrecision(0.59329999999996, 1)));
        
        Assert.assertEquals("112.0",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001, 0)));
        Assert.assertEquals("110.0",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001, -1)));
        Assert.assertEquals("100.0",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001, -2)));
        Assert.assertEquals("0.0",
                String.valueOf(MathUtility.roundWithPrecision(111.70400000001, -3)));
        
        //big decimal
        
        Assert.assertEquals("111.704",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000000001"), 12).toPlainString());
        Assert.assertEquals("111.704",
                MathUtility.roundWithPrecision(new BigDecimal("111.704000000000000000001"), 12).toPlainString());
        Assert.assertEquals("111.704",
                MathUtility.roundWithPrecision(new BigDecimal("111.7040000000001"), 12).toPlainString());
        Assert.assertEquals("111.704000000001",
                MathUtility.roundWithPrecision(new BigDecimal("111.704000000001"), 12).toPlainString());
        Assert.assertEquals("111.70400000001",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000001"), 12).toPlainString());
        Assert.assertEquals("111.704",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000001"), 6).toPlainString());
        Assert.assertEquals("111.70400000000000000000000000000000000000000000000001",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000000000000000000000000000000000000000000001"), 50).toPlainString());
        Assert.assertEquals("111.704",
                MathUtility.roundWithPrecision(new BigDecimal("111.704000000000000000000000000000000000000000000000001"), 50).toPlainString());
        Assert.assertEquals("111.704001",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000001"), 6, RoundingMode.CEILING).toPlainString());
        Assert.assertEquals("111.704",
                MathUtility.roundWithPrecision(new BigDecimal("111.704000000000000000000000000000000000000000000000001"), 50, RoundingMode.DOWN).toPlainString());
        
        Assert.assertEquals("0.5933",
                MathUtility.roundWithPrecision(new BigDecimal("0.59329999999996"), 12).toPlainString());
        Assert.assertEquals("0.59329999999996",
                MathUtility.roundWithPrecision(new BigDecimal("0.59329999999996"), 14).toPlainString());
        Assert.assertEquals("0.593",
                MathUtility.roundWithPrecision(new BigDecimal("0.59329999999996"), 3).toPlainString());
        Assert.assertEquals("0.6",
                MathUtility.roundWithPrecision(new BigDecimal("0.59329999999996"), 1).toPlainString());
        Assert.assertEquals("0.59329999999999999999999999999999999999999999999996",
                MathUtility.roundWithPrecision(new BigDecimal("0.59329999999999999999999999999999999999999999999996"), 50).toPlainString());
        Assert.assertEquals("0.5933",
                MathUtility.roundWithPrecision(new BigDecimal("0.593299999999999999999999999999999999999999999999996"), 50).toPlainString());
        Assert.assertEquals("0.5933",
                MathUtility.roundWithPrecision(new BigDecimal("0.59329999999996"), 12, RoundingMode.HALF_DOWN).toPlainString());
        Assert.assertEquals("0.59329999999999999999999999999999999999999999999999",
                MathUtility.roundWithPrecision(new BigDecimal("0.593299999999999999999999999999999999999999999999996"), 50, RoundingMode.FLOOR).toPlainString());
        
        Assert.assertEquals("112",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000001"), 0).toPlainString());
        Assert.assertEquals("110",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000001"), -1).toPlainString());
        Assert.assertEquals("100",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000001"), -2).toPlainString());
        Assert.assertEquals("0",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000001"), -3).toPlainString());
        Assert.assertEquals("120",
                MathUtility.roundWithPrecision(new BigDecimal("111.70400000001"), -1, RoundingMode.UP).toPlainString());
    }
    
    /**
     * JUnit test of min.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#min(Number[])
     */
    @Test
    public void testMin() throws Exception {
        //byte
        Assert.assertEquals((byte) 2, MathUtility.min((byte) 4, (byte) 2, (byte) 6).byteValue());
        Assert.assertEquals((byte) 4, MathUtility.min((byte) 4, (byte) 4, (byte) 6).byteValue());
        Assert.assertEquals((byte) 2, MathUtility.min((byte) 6, (byte) 2, (byte) 6).byteValue());
        Assert.assertEquals((byte) 1, MathUtility.min((byte) 1, (byte) 4, (byte) 6).byteValue());
        Assert.assertEquals((byte) 4, MathUtility.min((byte) 7, (byte) 4, (byte) 6).byteValue());
        Assert.assertEquals((byte) -4, MathUtility.min((byte) -2, (byte) -4, (byte) 6).byteValue());
        Assert.assertEquals((byte) -2, MathUtility.min((byte) -2, (byte) -1, (byte) 6).byteValue());
        Assert.assertEquals((byte) -2, MathUtility.min((byte) 4, (byte) 2, (byte) 6, (byte) -2, (byte) -1, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, MathUtility.min((byte) 6).byteValue());
        
        //short
        Assert.assertEquals((short) 22, MathUtility.min((short) 44, (short) 22, (short) 69).shortValue());
        Assert.assertEquals((short) 48, MathUtility.min((short) 48, (short) 48, (short) 69).shortValue());
        Assert.assertEquals((short) 22, MathUtility.min((short) 69, (short) 22, (short) 69).shortValue());
        Assert.assertEquals((short) 11, MathUtility.min((short) 11, (short) 40, (short) 61).shortValue());
        Assert.assertEquals((short) 40, MathUtility.min((short) 78, (short) 40, (short) 61).shortValue());
        Assert.assertEquals((short) -40, MathUtility.min((short) -27, (short) -40, (short) 61).shortValue());
        Assert.assertEquals((short) -27, MathUtility.min((short) -27, (short) -11, (short) 61).shortValue());
        Assert.assertEquals((short) -27, MathUtility.min((short) 44, (short) 22, (short) 69, (short) -27, (short) -11, (short) 61).shortValue());
        Assert.assertEquals((short) 61, MathUtility.min((short) 61).shortValue());
        
        //int
        Assert.assertEquals(222, MathUtility.min(446, 222, 699).intValue());
        Assert.assertEquals(481, MathUtility.min(481, 481, 699).intValue());
        Assert.assertEquals(222, MathUtility.min(699, 222, 699).intValue());
        Assert.assertEquals(11, MathUtility.min(11, 400, 617).intValue());
        Assert.assertEquals(400, MathUtility.min(737, 400, 617).intValue());
        Assert.assertEquals(-400, MathUtility.min(-278, -400, 617).intValue());
        Assert.assertEquals(-278, MathUtility.min(-278, -113, 617).intValue());
        Assert.assertEquals(-278, MathUtility.min(446, 222, 699, -278, -113, 617).intValue());
        Assert.assertEquals(617, MathUtility.min(617).intValue());
        
        //float
        Assert.assertEquals(2.22f, MathUtility.min(4.46f, 2.22f, 6.99f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(4.81f, MathUtility.min(4.81f, 4.81f, 6.99f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(2.22f, MathUtility.min(6.99f, 2.22f, 6.99f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(1.1f, MathUtility.min(1.1f, 4.00f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(4.00f, MathUtility.min(7.37f, 4.00f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(-4.00f, MathUtility.min(-2.78f, -4.00f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(-2.78f, MathUtility.min(-2.78f, -1.13f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(-2.78f, MathUtility.min(4.46f, 2.22f, 6.99f, -2.78f, -1.13f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.17f, MathUtility.min(6.17f), TestUtils.DELTA_FLOAT);
        
        //double
        Assert.assertEquals(2.228808404d, MathUtility.min(4.469845d, 2.228808404d, 6.99198702d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(4.8100154d, MathUtility.min(4.8100154d, 4.8100154d, 6.99198702d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(2.228808404d, MathUtility.min(6.99198702d, 2.228808404d, 6.99198702d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(1.1d, MathUtility.min(1.1d, 4.0000001d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(4.0000001d, MathUtility.min(7.37980798d, 4.0000001d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(-4.0000001d, MathUtility.min(-2.7858074887d, -4.0000001d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(-2.7858074887d, MathUtility.min(-2.7858074887d, -1.139805605774d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(-2.7858074887d, MathUtility.min(4.469845d, 2.228808404d, 6.99198702d, -2.7858074887d, -1.139805605774d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.17348095d, MathUtility.min(6.17348095d), TestUtils.DELTA_DOUBLE);
        
        //long
        Assert.assertEquals(2228808404L, MathUtility.min(4469845000L, 2228808404L, 6991987020L).longValue());
        Assert.assertEquals(48100154L, MathUtility.min(48100154L, 48100154L, 6991987020L).longValue());
        Assert.assertEquals(2228808404L, MathUtility.min(6991987020L, 2228808404L, 6991987020L).longValue());
        Assert.assertEquals(11L, MathUtility.min(11L, 40000001L, 617348095L).longValue());
        Assert.assertEquals(40000001L, MathUtility.min(737980798L, 40000001L, 617348095L).longValue());
        Assert.assertEquals(-40000001000L, MathUtility.min(-27858074887L, -40000001000L, 617348095L).longValue());
        Assert.assertEquals(-27858074887L, MathUtility.min(-27858074887L, -11398056057L, 617348095L).longValue());
        Assert.assertEquals(-27858074887L, MathUtility.min(4469845000L, 2228808404L, 6991987020L, -27858074887L, -11398056057L, 617348095L).longValue());
        Assert.assertEquals(617348095L, MathUtility.min(617348095L).longValue());
        
        //big decimal
        Assert.assertEquals(new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), MathUtility.min(new BigDecimal("498048940365649408940894156.46989846504649845"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")));
        Assert.assertEquals(new BigDecimal("4161561587498065454121635654.0000000006546508100154"), MathUtility.min(new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")));
        Assert.assertEquals(new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), MathUtility.min(new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")));
        Assert.assertEquals(new BigDecimal("1.1"), MathUtility.min(new BigDecimal("1.1"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("400000000000000000000.000000000000000000000001"), MathUtility.min(new BigDecimal("70000000000490988798465065653212318888.370989889489740546506505156980798"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("-400000000000000000000.000000000000000000000001"), MathUtility.min(new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("-234605409480456054264.785804564655496574887"), MathUtility.min(new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-100870798769504941324.139805605774"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("-234605409480456054264.785804564655496574887"), MathUtility.min(new BigDecimal("498048940365649408940894156.46989846504649845"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-100870798769504941324.139805605774"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), MathUtility.min(new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        
        //big integer
        Assert.assertEquals(new BigInteger("298766565984054313210050444"), MathUtility.min(new BigInteger("498048940365649408940894156"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")));
        Assert.assertEquals(new BigInteger("4161561587498065454121635654"), MathUtility.min(new BigInteger("4161561587498065454121635654"), new BigInteger("4161561587498065454121635654"), new BigInteger("690890450454565456960987894649")));
        Assert.assertEquals(new BigInteger("298766565984054313210050444"), MathUtility.min(new BigInteger("690890450454565456960987894649"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")));
        Assert.assertEquals(new BigInteger("1"), MathUtility.min(new BigInteger("1"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("400000000000000000000"), MathUtility.min(new BigInteger("70000000000490988798465065653212318888"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("-400000000000000000000"), MathUtility.min(new BigInteger("-234605409480456054264"), new BigInteger("-400000000000000000000"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("-234605409480456054264"), MathUtility.min(new BigInteger("-234605409480456054264"), new BigInteger("-100870798769504941324"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("-234605409480456054264"), MathUtility.min(new BigInteger("498048940365649408940894156"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649"), new BigInteger("-234605409480456054264"), new BigInteger("-100870798769504941324"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("6664598409846520651984845149"), MathUtility.min(new BigInteger("6664598409846520651984845149")));
        
        //empty
        Assert.assertEquals((byte) 0, MathUtility.min().byteValue());
        Assert.assertEquals((short) 0, MathUtility.min().shortValue());
        Assert.assertEquals(0, MathUtility.min().intValue());
        Assert.assertEquals(0.0f, MathUtility.min().floatValue(), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(0.0d, MathUtility.min().doubleValue(), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(0L, MathUtility.min().longValue());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                MathUtility.min((Number[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                MathUtility.min((Number) null));
        TestUtils.assertException(NullPointerException.class, () ->
                MathUtility.min(15, 10, null));
    }
    
    /**
     * JUnit test of max.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#max(Number[])
     */
    @Test
    public void testMax() throws Exception {
        //byte
        Assert.assertEquals((byte) 6, MathUtility.max((byte) 4, (byte) 2, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, MathUtility.max((byte) 4, (byte) 4, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, MathUtility.max((byte) 6, (byte) 2, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, MathUtility.max((byte) 1, (byte) 4, (byte) 6).byteValue());
        Assert.assertEquals((byte) 7, MathUtility.max((byte) 7, (byte) 4, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, MathUtility.max((byte) -2, (byte) -4, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, MathUtility.max((byte) -2, (byte) -1, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, MathUtility.max((byte) 4, (byte) 2, (byte) 6, (byte) -2, (byte) -1, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, MathUtility.max((byte) 6).byteValue());
        
        //short
        Assert.assertEquals((short) 69, MathUtility.max((short) 44, (short) 22, (short) 69).shortValue());
        Assert.assertEquals((short) 69, MathUtility.max((short) 48, (short) 48, (short) 69).shortValue());
        Assert.assertEquals((short) 69, MathUtility.max((short) 69, (short) 22, (short) 69).shortValue());
        Assert.assertEquals((short) 61, MathUtility.max((short) 11, (short) 40, (short) 61).shortValue());
        Assert.assertEquals((short) 78, MathUtility.max((short) 78, (short) 40, (short) 61).shortValue());
        Assert.assertEquals((short) 61, MathUtility.max((short) -27, (short) -40, (short) 61).shortValue());
        Assert.assertEquals((short) 61, MathUtility.max((short) -27, (short) -11, (short) 61).shortValue());
        Assert.assertEquals((short) 69, MathUtility.max((short) 44, (short) 22, (short) 69, (short) -27, (short) -11, (short) 61).shortValue());
        Assert.assertEquals((short) 61, MathUtility.max((short) 61).shortValue());
        
        //int
        Assert.assertEquals(699, MathUtility.max(446, 222, 699).intValue());
        Assert.assertEquals(699, MathUtility.max(481, 481, 699).intValue());
        Assert.assertEquals(699, MathUtility.max(699, 222, 699).intValue());
        Assert.assertEquals(617, MathUtility.max(11, 400, 617).intValue());
        Assert.assertEquals(737, MathUtility.max(737, 400, 617).intValue());
        Assert.assertEquals(617, MathUtility.max(-278, -400, 617).intValue());
        Assert.assertEquals(617, MathUtility.max(-278, -113, 617).intValue());
        Assert.assertEquals(699, MathUtility.max(446, 222, 699, -278, -113, 617).intValue());
        Assert.assertEquals(617, MathUtility.max(617).intValue());
        
        //float
        Assert.assertEquals(6.99f, MathUtility.max(4.46f, 2.22f, 6.99f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.99f, MathUtility.max(4.81f, 4.81f, 6.99f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.99f, MathUtility.max(6.99f, 2.22f, 6.99f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.17f, MathUtility.max(1.1f, 4.00f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(7.37f, MathUtility.max(7.37f, 4.00f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.17f, MathUtility.max(-2.78f, -4.00f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.17f, MathUtility.max(-2.78f, -1.13f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.99f, MathUtility.max(4.46f, 2.22f, 6.99f, -2.78f, -1.13f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.17f, MathUtility.max(6.17f), TestUtils.DELTA_FLOAT);
        
        //double
        Assert.assertEquals(6.99198702d, MathUtility.max(4.469845d, 2.228808404d, 6.99198702d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.99198702d, MathUtility.max(4.8100154d, 4.8100154d, 6.99198702d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.99198702d, MathUtility.max(6.99198702d, 2.228808404d, 6.99198702d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.17348095d, MathUtility.max(1.1d, 4.0000001d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(7.37980798d, MathUtility.max(7.37980798d, 4.0000001d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.17348095d, MathUtility.max(-2.7858074887d, -4.0000001d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.17348095d, MathUtility.max(-2.7858074887d, -1.139805605774d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.99198702d, MathUtility.max(4.469845d, 2.228808404d, 6.99198702d, -2.7858074887d, -1.139805605774d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.17348095d, MathUtility.max(6.17348095d), TestUtils.DELTA_DOUBLE);
        
        //long
        Assert.assertEquals(6991987020L, MathUtility.max(4469845000L, 2228808404L, 6991987020L).longValue());
        Assert.assertEquals(6991987020L, MathUtility.max(48100154L, 48100154L, 6991987020L).longValue());
        Assert.assertEquals(6991987020L, MathUtility.max(6991987020L, 2228808404L, 6991987020L).longValue());
        Assert.assertEquals(617348095L, MathUtility.max(11L, 40000001L, 617348095L).longValue());
        Assert.assertEquals(737980798L, MathUtility.max(737980798L, 40000001L, 617348095L).longValue());
        Assert.assertEquals(617348095L, MathUtility.max(-27858074887L, -40000001000L, 617348095L).longValue());
        Assert.assertEquals(617348095L, MathUtility.max(-27858074887L, -11398056057L, 617348095L).longValue());
        Assert.assertEquals(6991987020L, MathUtility.max(4469845000L, 2228808404L, 6991987020L, -27858074887L, -11398056057L, 617348095L).longValue());
        Assert.assertEquals(617348095L, MathUtility.max(617348095L).longValue());
        
        //big decimal
        Assert.assertEquals(new BigDecimal("690890450454565456960987894649.991987020989845645645458"), MathUtility.max(new BigDecimal("498048940365649408940894156.46989846504649845"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")));
        Assert.assertEquals(new BigDecimal("690890450454565456960987894649.991987020989845645645458"), MathUtility.max(new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")));
        Assert.assertEquals(new BigDecimal("690890450454565456960987894649.991987020989845645645458"), MathUtility.max(new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")));
        Assert.assertEquals(new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), MathUtility.max(new BigDecimal("1.1"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("70000000000490988798465065653212318888.370989889489740546506505156980798"), MathUtility.max(new BigDecimal("70000000000490988798465065653212318888.370989889489740546506505156980798"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), MathUtility.max(new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), MathUtility.max(new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-100870798769504941324.139805605774"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("690890450454565456960987894649.991987020989845645645458"), MathUtility.max(new BigDecimal("498048940365649408940894156.46989846504649845"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-100870798769504941324.139805605774"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), MathUtility.max(new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        
        //big integer
        Assert.assertEquals(new BigInteger("690890450454565456960987894649"), MathUtility.max(new BigInteger("498048940365649408940894156"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")));
        Assert.assertEquals(new BigInteger("690890450454565456960987894649"), MathUtility.max(new BigInteger("4161561587498065454121635654"), new BigInteger("4161561587498065454121635654"), new BigInteger("690890450454565456960987894649")));
        Assert.assertEquals(new BigInteger("690890450454565456960987894649"), MathUtility.max(new BigInteger("690890450454565456960987894649"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")));
        Assert.assertEquals(new BigInteger("6664598409846520651984845149"), MathUtility.max(new BigInteger("1"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("70000000000490988798465065653212318888"), MathUtility.max(new BigInteger("70000000000490988798465065653212318888"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("6664598409846520651984845149"), MathUtility.max(new BigInteger("-234605409480456054264"), new BigInteger("-400000000000000000000"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("6664598409846520651984845149"), MathUtility.max(new BigInteger("-234605409480456054264"), new BigInteger("-100870798769504941324"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("690890450454565456960987894649"), MathUtility.max(new BigInteger("498048940365649408940894156"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649"), new BigInteger("-234605409480456054264"), new BigInteger("-100870798769504941324"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("6664598409846520651984845149"), MathUtility.max(new BigInteger("6664598409846520651984845149")));
        
        //empty
        Assert.assertEquals((byte) 0, MathUtility.max().byteValue());
        Assert.assertEquals((short) 0, MathUtility.max().shortValue());
        Assert.assertEquals(0, MathUtility.max().intValue());
        Assert.assertEquals(0.0f, MathUtility.max().floatValue(), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(0.0d, MathUtility.max().doubleValue(), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(0L, MathUtility.max().longValue());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                MathUtility.max((Number[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                MathUtility.max((Number) null));
        TestUtils.assertException(NullPointerException.class, () ->
                MathUtility.max(15, 10, null));
    }
    
    /**
     * JUnit test of digitSum.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#digitSum(long)
     */
    @Test
    public void testDigitSum() throws Exception {
        //standard
        Assert.assertEquals(0, MathUtility.digitSum(0));
        Assert.assertEquals(1, MathUtility.digitSum(1));
        Assert.assertEquals(2, MathUtility.digitSum(11));
        Assert.assertEquals(6, MathUtility.digitSum(123));
        Assert.assertEquals(6, MathUtility.digitSum(-123));
        Assert.assertEquals(6, MathUtility.digitSum(321));
        Assert.assertEquals(6, MathUtility.digitSum(-321));
        Assert.assertEquals(5, MathUtility.digitSum(50000));
        Assert.assertEquals(18, MathUtility.digitSum(842112));
        Assert.assertEquals(16, MathUtility.digitSum(3154201));
        Assert.assertEquals(34, MathUtility.digitSum(-70165483));
        Assert.assertEquals(45, MathUtility.digitSum(123456789));
        Assert.assertEquals(45, MathUtility.digitSum(987654321));
        Assert.assertEquals(88, MathUtility.digitSum(Long.MAX_VALUE));
        Assert.assertEquals(89, MathUtility.digitSum(Long.MIN_VALUE));
    }
    
    /**
     * JUnit test of digitSumAlternating.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#digitSumAlternating(long)
     */
    @Test
    public void testDigitSumAlternating() throws Exception {
        //standard
        Assert.assertEquals(0, MathUtility.digitSumAlternating(0));
        Assert.assertEquals(1, MathUtility.digitSumAlternating(1));
        Assert.assertEquals(0, MathUtility.digitSumAlternating(11));
        Assert.assertEquals(2, MathUtility.digitSumAlternating(123));
        Assert.assertEquals(2, MathUtility.digitSumAlternating(-123));
        Assert.assertEquals(2, MathUtility.digitSumAlternating(321));
        Assert.assertEquals(2, MathUtility.digitSumAlternating(-321));
        Assert.assertEquals(5, MathUtility.digitSumAlternating(50000));
        Assert.assertEquals(4, MathUtility.digitSumAlternating(842112));
        Assert.assertEquals(6, MathUtility.digitSumAlternating(3154201));
        Assert.assertEquals(8, MathUtility.digitSumAlternating(-70165483));
        Assert.assertEquals(5, MathUtility.digitSumAlternating(123456789));
        Assert.assertEquals(5, MathUtility.digitSumAlternating(987654321));
        Assert.assertEquals(18, MathUtility.digitSumAlternating(Long.MAX_VALUE));
        Assert.assertEquals(19, MathUtility.digitSumAlternating(Long.MIN_VALUE));
    }
    
    /**
     * JUnit test of digitSumWeighted.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#digitSumWeighted(long)
     */
    @Test
    public void testDigitSumWeighted() throws Exception {
        //standard
        Assert.assertEquals(0, MathUtility.digitSumWeighted(0));
        Assert.assertEquals(1, MathUtility.digitSumWeighted(1));
        Assert.assertEquals(3, MathUtility.digitSumWeighted(11));
        Assert.assertEquals(14, MathUtility.digitSumWeighted(123));
        Assert.assertEquals(14, MathUtility.digitSumWeighted(-123));
        Assert.assertEquals(10, MathUtility.digitSumWeighted(321));
        Assert.assertEquals(10, MathUtility.digitSumWeighted(-321));
        Assert.assertEquals(5, MathUtility.digitSumWeighted(50000));
        Assert.assertEquals(43, MathUtility.digitSumWeighted(842112));
        Assert.assertEquals(53, MathUtility.digitSumWeighted(3154201));
        Assert.assertEquals(163, MathUtility.digitSumWeighted(-70165483));
        Assert.assertEquals(285, MathUtility.digitSumWeighted(123456789));
        Assert.assertEquals(165, MathUtility.digitSumWeighted(987654321));
        Assert.assertEquals(941, MathUtility.digitSumWeighted(Long.MAX_VALUE));
        Assert.assertEquals(960, MathUtility.digitSumWeighted(Long.MIN_VALUE));
    }
    
    /**
     * JUnit test of digitSumK.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#digitSumK(long, int)
     */
    @Test
    public void testDigitSumK() throws Exception {
        //standard
        Assert.assertEquals(1, MathUtility.digitSumK(0, 1));
        Assert.assertEquals(6, MathUtility.digitSumK(1, 5));
        Assert.assertEquals(-1, MathUtility.digitSumK(11, -3));
        Assert.assertEquals(121, MathUtility.digitSumK(123, 115));
        Assert.assertEquals(6, MathUtility.digitSumK(-123, 0));
        Assert.assertEquals(6, MathUtility.digitSumK(321, 0));
        Assert.assertEquals(-25, MathUtility.digitSumK(-321, -31));
        Assert.assertEquals(11, MathUtility.digitSumK(50000, 6));
        Assert.assertEquals(31, MathUtility.digitSumK(842112, 13));
        Assert.assertEquals(33, MathUtility.digitSumK(3154201, 17));
        Assert.assertEquals(33, MathUtility.digitSumK(-70165483, -1));
        Assert.assertEquals(52, MathUtility.digitSumK(123456789, 7));
        Assert.assertEquals(144, MathUtility.digitSumK(987654321, 99));
        Assert.assertEquals(98, MathUtility.digitSumK(Long.MAX_VALUE, 10));
        Assert.assertEquals(99, MathUtility.digitSumK(Long.MIN_VALUE, 10));
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
        Assert.assertEquals(3, MathUtility.mapValue(3, 2, 8, 2, 8), TestUtils.DELTA);
        Assert.assertEquals(3, MathUtility.mapValue(3, 3, 8, 3, 8), TestUtils.DELTA);
        Assert.assertEquals(3, MathUtility.mapValue(3, 1, 3, 1, 3), TestUtils.DELTA);
        
        //standard
        Assert.assertEquals(10, MathUtility.mapValue(1, 0, 10, 0, 100), TestUtils.DELTA);
        Assert.assertEquals(18, MathUtility.mapValue(1.8, 0, 10, 0, 100), TestUtils.DELTA);
        Assert.assertEquals(18, MathUtility.mapValue(1.8, 0, 10, 18, 18), TestUtils.DELTA);
        Assert.assertEquals(4.3, MathUtility.mapValue(6, 2, 12, 3.5, 5.5), TestUtils.DELTA);
        Assert.assertEquals(0.15000249644736335, MathUtility.mapValue(72, 59, 52133, 0.15, 0.16), TestUtils.DELTA);
        
        //edge cases
        Assert.assertEquals(6, MathUtility.mapValue(15, 20, 25, 6, 39), TestUtils.DELTA);
        Assert.assertEquals(5, MathUtility.mapValue(15, 0, 10, 4, 5), TestUtils.DELTA);
    }
    
}
