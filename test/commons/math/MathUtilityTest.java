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
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import commons.lambda.stream.mapper.Mappers;
import commons.math.number.BoundUtility;
import commons.object.collection.set.CounterSet;
import commons.object.collection.set.LazyCounterSet;
import commons.test.TestUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
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
     * @see MathUtility#random(long)
     * @see MathUtility#random(int, int)
     * @see MathUtility#random(int)
     */
    @Test
    public void testRandom() throws Exception {
        //int, max
        List.of(10, 0).forEach(max -> {
            Assert.assertTrue(IntStream.range(0, 1000).map(i -> MathUtility.random(max))
                    .allMatch(value -> BoundUtility.inBounds(value, 0, max)));
            List.of(0, max).forEach(limit ->
                    Assert.assertTrue(IntStream.range(0, Integer.MAX_VALUE).map(i -> MathUtility.random(max))
                            .anyMatch(value -> (value == limit))));
        });
        
        //int, range
        List.of(new ImmutablePair<>(3, 21),
                new ImmutablePair<>(0, 10),
                new ImmutablePair<>(-5, 5),
                new ImmutablePair<>(-10, 0),
                new ImmutablePair<>(-15, -5),
                new ImmutablePair<>(0, 0)
        ).forEach(range -> {
            Assert.assertTrue(IntStream.range(0, 1000).map(i -> MathUtility.random(range.left, range.right))
                    .allMatch(value -> BoundUtility.inBounds(value, range.left, range.right)));
            List.of(range.left, range.right).forEach(limit ->
                    Assert.assertTrue(IntStream.range(0, Integer.MAX_VALUE).map(i -> MathUtility.random(range.left, range.right))
                            .anyMatch(value -> (value == limit))));
        });
        
        //long, max
        List.of(10L, 0L).forEach(max -> {
            Assert.assertTrue(IntStream.range(0, 1000).mapToLong(i -> MathUtility.random(max))
                    .allMatch(value -> BoundUtility.inBounds(value, 0, max)));
            List.of(0L, max).forEach(limit ->
                    Assert.assertTrue(IntStream.range(0, Integer.MAX_VALUE).mapToLong(i -> MathUtility.random(max))
                            .anyMatch(value -> (value == limit))));
        });
        
        //long, range
        List.of(new ImmutablePair<>(1984560454894L, 1984560454906L),
                new ImmutablePair<>(0L, 10L),
                new ImmutablePair<>(-5L, 5L),
                new ImmutablePair<>(-10L, 0L),
                new ImmutablePair<>(-15L, -5L),
                new ImmutablePair<>(0L, 0L)
        ).forEach(range -> {
            Assert.assertTrue(IntStream.range(0, 1000).mapToLong(i -> MathUtility.random(range.left, range.right))
                    .allMatch(value -> BoundUtility.inBounds(value, range.left, range.right)));
            List.of(range.left, range.right).forEach(limit ->
                    Assert.assertTrue(IntStream.range(0, Integer.MAX_VALUE).mapToLong(i -> MathUtility.random(range.left, range.right))
                            .anyMatch(value -> (value == limit))));
        });
        
        //invalid
        TestUtils.assertException(IllegalArgumentException.class, "bound must be positive", () ->
                MathUtility.random(-1));
        TestUtils.assertException(IllegalArgumentException.class, "bound must be positive", () ->
                MathUtility.random(-1L));
        List.of(new ImmutablePair<>(21, 3),
                new ImmutablePair<>(10, 0),
                new ImmutablePair<>(5, -5),
                new ImmutablePair<>(0, -10),
                new ImmutablePair<>(-5, -15)
        ).forEach(invalidRange ->
                TestUtils.assertException(IllegalArgumentException.class, "bound must be positive", () ->
                        MathUtility.random(invalidRange.left, invalidRange.right)));
        List.of(new ImmutablePair<>(1984560454906L, 1984560454894L),
                new ImmutablePair<>(10L, 0L),
                new ImmutablePair<>(5L, -5L),
                new ImmutablePair<>(0L, -10L),
                new ImmutablePair<>(-5L, -15L)
        ).forEach(invalidRange ->
                TestUtils.assertException(IllegalArgumentException.class, "bound must be positive", () ->
                        MathUtility.random(invalidRange.left, invalidRange.right)));
    }
    
    /**
     * JUnit test of randomInt.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#randomInt()
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Test
    public void testRandomInt() throws Exception {
        final CounterSet<Integer> values = new LazyCounterSet<>();
        
        //standard
        Assert.assertTrue(IntStream.range(0, 1000).mapToObj(i -> MathUtility.randomInt())
                .map(Mappers.forEach(values::increment))
                .allMatch(value -> BoundUtility.inBounds(value, Integer.MIN_VALUE, Integer.MAX_VALUE)));
        Assert.assertTrue(values.size() > (1000 * 0.95));
    }
    
    /**
     * JUnit test of randomLong.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#randomLong()
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Test
    public void testRandomLong() throws Exception {
        final CounterSet<Long> values = new LazyCounterSet<>();
        
        //standard
        Assert.assertTrue(IntStream.range(0, 1000).mapToObj(i -> MathUtility.randomLong())
                .map(Mappers.forEach(values::increment))
                .allMatch(value -> BoundUtility.inBounds(value, Long.MIN_VALUE, Long.MAX_VALUE)));
        Assert.assertTrue(values.size() > (1000 * 0.95));
    }
    
    /**
     * JUnit test of randomFloat.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#randomFloat()
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Test
    public void testRandomFloat() throws Exception {
        final CounterSet<Float> values = new LazyCounterSet<>();
        
        //standard
        Assert.assertTrue(IntStream.range(0, 1000).mapToObj(i -> MathUtility.randomFloat())
                .map(Mappers.forEach(values::increment))
                .allMatch(value -> BoundUtility.inBounds(value, Float.MIN_VALUE, Float.MAX_VALUE)));
        Assert.assertTrue(values.size() > (1000 * 0.95));
    }
    
    /**
     * JUnit test of randomDouble.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#randomDouble()
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Test
    public void testRandomDouble() throws Exception {
        final CounterSet<Double> values = new LazyCounterSet<>();
        
        //standard
        Assert.assertTrue(IntStream.range(0, 1000).mapToObj(i -> MathUtility.randomDouble())
                .map(Mappers.forEach(values::increment))
                .allMatch(value -> BoundUtility.inBounds(value, Double.MIN_VALUE, Double.MAX_VALUE)));
        Assert.assertTrue(values.size() > (1000 * 0.95));
    }
    
    /**
     * JUnit test of randomBoolean.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#randomBoolean()
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Test
    public void testRandomBoolean() throws Exception {
        final CounterSet<Boolean> values = new LazyCounterSet<>();
        
        //standard
        IntStream.range(0, 10000).mapToObj(i -> MathUtility.randomBoolean())
                .map(Mappers.forEach(values::increment))
                .allMatch(Objects::nonNull);
        Assert.assertTrue(Math.abs(values.get(true) - values.get(false)) < (10000 * 0.05));
    }
    
    /**
     * JUnit test of dice.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#dice(long, long)
     * @see MathUtility#dice(long)
     * @see MathUtility#dice(int, int)
     * @see MathUtility#dice(int)
     */
    @Test
    public void testDice() throws Exception {
        //int
        List.of(2, 4, 6, 8, 10, 12, 20, 100).forEach(sides -> {
            Assert.assertTrue(IntStream.range(0, 1000).map(i -> MathUtility.dice(sides))
                    .allMatch(roll -> BoundUtility.inBounds(roll, 1, sides)));
            Assert.assertTrue(IntStream.range(0, 1000).map(i -> MathUtility.dice(sides, sides))
                    .allMatch(roll -> BoundUtility.inBounds(roll, sides, (sides * sides))));
            Assert.assertEquals(0, MathUtility.dice(sides, 0));
            Assert.assertEquals(0, MathUtility.dice(sides, -1));
        });
        
        //int
        List.of(2L, 4L, 6L, 8L, 10L, 12L, 20L, 100L).forEach(sides -> {
            Assert.assertTrue(IntStream.range(0, 1000).mapToLong(i -> MathUtility.dice(sides))
                    .allMatch(roll -> BoundUtility.inBounds(roll, 1, sides)));
            Assert.assertTrue(IntStream.range(0, 1000).mapToLong(i -> MathUtility.dice(sides, sides))
                    .allMatch(roll -> BoundUtility.inBounds(roll, sides, (sides * sides))));
            Assert.assertEquals(0L, MathUtility.dice(sides, 0L));
            Assert.assertEquals(0L, MathUtility.dice(sides, -1L));
        });
        
        //invalid
        List.of(0, -1).forEach(invalid -> {
            TestUtils.assertException(IllegalArgumentException.class, "bound must be positive", () ->
                    MathUtility.dice(invalid));
            TestUtils.assertException(IllegalArgumentException.class, "bound must be positive", () ->
                    MathUtility.dice(invalid, 10));
        });
        List.of(0L, -1L).forEach(invalid -> {
            TestUtils.assertException(IllegalArgumentException.class, "bound must be positive", () ->
                    MathUtility.dice(invalid));
            TestUtils.assertException(IllegalArgumentException.class, "bound must be positive", () ->
                    MathUtility.dice(invalid, 10L));
        });
    }
    
    /**
     * JUnit test of coinFlip.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#coinFlip()
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Test
    public void testCoinFlip() throws Exception {
        final CounterSet<Boolean> flips = new LazyCounterSet<>();
        
        //standard
        IntStream.range(0, 10000).mapToObj(i -> MathUtility.randomBoolean())
                .map(Mappers.forEach(flips::increment))
                .allMatch(Objects::nonNull);
        Assert.assertTrue(Math.abs(flips.get(true) - flips.get(false)) < (10000 * 0.05));
    }
    
    /**
     * JUnit test of isSquare.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#isSquare(long)
     */
    @Test
    public void testIsSquare() throws Exception {
        //square
        List.of(0, 1, 4, 9, 25, 100, 10000, 1048576).forEach(num ->
                Assert.assertTrue(MathUtility.isSquare(num)));
        
        //not square
        List.of(2, 3, 8, 99, 10001, 1543211).forEach(num ->
                Assert.assertFalse(MathUtility.isSquare(num)));
        
        //invalid
        Assert.assertFalse(MathUtility.isSquare(-1));
    }
    
    /**
     * JUnit test of isPrime.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#isPrime(long)
     */
    @Test
    public void testIsPrime() throws Exception {
        //prime
        List.of(2, 3, 17, 197, 2731, 16033, 512927377).forEach(num ->
                Assert.assertTrue(MathUtility.isPrime(num)));
        
        //not prime
        List.of(0, 1, 4, 9, 20, 100, 2022, 1048576).forEach(num ->
                Assert.assertFalse(MathUtility.isPrime(num)));
        
        //invalid
        Assert.assertFalse(MathUtility.isPrime(-1));
    }
    
    /**
     * JUnit test of isDivisibleBy.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#isDivisibleBy(long, long)
     */
    @Test
    public void testIsDivisibleBy() throws Exception {
        //divisible by
        List.of(new ImmutablePair<>(10, 1),
                new ImmutablePair<>(10, 2),
                new ImmutablePair<>(10, 5),
                new ImmutablePair<>(10, 10),
                new ImmutablePair<>(10, -2),
                new ImmutablePair<>(1408, 8),
                new ImmutablePair<>(-1408, 8),
                new ImmutablePair<>(-1408, -8),
                new ImmutablePair<>(15000, 15),
                new ImmutablePair<>(264771, 117),
                new ImmutablePair<>(0, 1),
                new ImmutablePair<>(0, -1)
        ).forEach(divisibleBy ->
                Assert.assertTrue(MathUtility.isDivisibleBy(divisibleBy.left, divisibleBy.right)));
        
        //not divisible by
        List.of(new ImmutablePair<>(10, 3),
                new ImmutablePair<>(10, 9),
                new ImmutablePair<>(10, -6),
                new ImmutablePair<>(1408, 9),
                new ImmutablePair<>(264772, 117)
        ).forEach(divisibleBy ->
                Assert.assertFalse(MathUtility.isDivisibleBy(divisibleBy.left, divisibleBy.right)));
        
        //invalid
        List.of(10, -10, 0).forEach(divideByZero ->
                TestUtils.assertNoException(() ->
                        MathUtility.isDivisibleBy(divideByZero, 0)));
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
        List.of(new int[] {6, 3, 3},
                new int[] {5, 3, 2},
                new int[] {4, 3, 1},
                new int[] {3, 3, 3},
                new int[] {2, 3, 2},
                new int[] {1, 3, 1},
                new int[] {0, 3, 3},
                new int[] {-1, 3, -1},
                new int[] {-2, 3, -2},
                new int[] {-3, 3, -3},
                new int[] {-4, 3, -1},
                new int[] {-5, 3, -2},
                new int[] {-6, 3, -3},
                new int[] {6, -3, 3},
                new int[] {5, -3, 2},
                new int[] {4, -3, 1},
                new int[] {3, -3, 3},
                new int[] {2, -3, 2},
                new int[] {1, -3, 1},
                new int[] {0, -3, 3},
                new int[] {-1, -3, -1},
                new int[] {-2, -3, -2},
                new int[] {-3, -3, -3},
                new int[] {-4, -3, -1},
                new int[] {-5, -3, -2},
                new int[] {-6, -3, -3},
                new int[] {154621, 160, 61},
                new int[] {160, 154621, 160},
                new int[] {165431298, 154621, 141449}
        ).forEach(params ->
                Assert.assertEquals(params[2], MathUtility.xmod(params[0], params[1])));
        
        //long
        List.of(new long[] {6L, 3L, 3L},
                new long[] {5L, 3L, 2L},
                new long[] {4L, 3L, 1L},
                new long[] {3L, 3L, 3L},
                new long[] {2L, 3L, 2L},
                new long[] {1L, 3L, 1L},
                new long[] {0L, 3L, 3L},
                new long[] {-1L, 3L, -1L},
                new long[] {-2L, 3L, -2L},
                new long[] {-3L, 3L, -3L},
                new long[] {-4L, 3L, -1L},
                new long[] {-5L, 3L, -2L},
                new long[] {-6L, 3L, -3L},
                new long[] {6L, -3L, 3L},
                new long[] {5L, -3L, 2L},
                new long[] {4L, -3L, 1L},
                new long[] {3L, -3L, 3L},
                new long[] {2L, -3L, 2L},
                new long[] {1L, -3L, 1L},
                new long[] {0L, -3L, 3L},
                new long[] {-1L, -3L, -1L},
                new long[] {-2L, -3L, -2L},
                new long[] {-3L, -3L, -3L},
                new long[] {-4L, -3L, -1L},
                new long[] {-5L, -3L, -2L},
                new long[] {-6L, -3L, -3L},
                new long[] {154621L, 160L, 61L},
                new long[] {160L, 154621L, 160L},
                new long[] {165431298L, 154621L, 141449L},
                new long[] {894654165431298L, 654154621L, 252175269L}
        ).forEach(params ->
                Assert.assertEquals(params[2], MathUtility.xmod(params[0], params[1])));
        
        //invalid
        List.of(10, -10, 0).forEach(modByZero ->
                TestUtils.assertException(ArithmeticException.class, () ->
                        MathUtility.xmod(15, 0)));
    }
    
    /**
     * JUnit test of roundWithPrecision.
     *
     * @throws Exception When there is an exception.
     * @see MathUtility#roundWithPrecision(float, int)
     * @see MathUtility#roundWithPrecision(double, int)
     * @see MathUtility#roundWithPrecision(BigDecimal, int, java.math.RoundingMode)
     * @see MathUtility#roundWithPrecision(BigDecimal, int)
     */
    @Test
    public void testRoundWithPrecision() throws Exception {
        //float
        List.of(new Object[] {111.70400001f, 6, "111.704"},
                new Object[] {111.704000000000001f, 6, "111.704"},
                new Object[] {111.7040001f, 6, "111.704"},
                new Object[] {111.704001f, 6, "111.704"},
                new Object[] {111.70401f, 6, "111.70401"},
                new Object[] {111.70401f, 3, "111.704"},
                new Object[] {0.59329996f, 6, "0.5933"},
                new Object[] {0.59329996f, 4, "0.5933"},
                new Object[] {0.59329996f, 2, "0.59"},
                new Object[] {0.59329996f, 1, "0.6"},
                new Object[] {111.70401f, 0, "112.0"},
                new Object[] {111.70401f, -1, "110.0"},
                new Object[] {111.70401f, -2, "100.0"},
                new Object[] {111.70401f, -3, "0.0"}
        ).forEach(params ->
                Assert.assertEquals(params[2], String.valueOf(MathUtility.roundWithPrecision((float) params[0], (int) params[1]))));
        
        //double
        List.of(new Object[] {111.70400000000001, 12, "111.704"},
                new Object[] {111.704000000000000000001, 12, "111.704"},
                new Object[] {111.7040000000001, 12, "111.704"},
                new Object[] {111.704000000001, 12, "111.704000000001"},
                new Object[] {111.70400000001, 12, "111.70400000001"},
                new Object[] {111.70400000001, 6, "111.704"},
                new Object[] {0.59329999999996, 12, "0.5933"},
                new Object[] {0.59329999999996, 14, "0.59329999999996"},
                new Object[] {0.59329999999996, 3, "0.593"},
                new Object[] {0.59329999999996, 1, "0.6"},
                new Object[] {111.70400000001, 0, "112.0"},
                new Object[] {111.70400000001, -1, "110.0"},
                new Object[] {111.70400000001, -2, "100.0"},
                new Object[] {111.70400000001, -3, "0.0"}
        ).forEach(params ->
                Assert.assertEquals(params[2], String.valueOf(MathUtility.roundWithPrecision((double) params[0], (int) params[1]))));
        
        //big decimal
        List.of(new Object[] {new BigDecimal("111.70400000000001"), 12, "111.704"},
                new Object[] {new BigDecimal("111.704000000000000000001"), 12, "111.704"},
                new Object[] {new BigDecimal("111.7040000000001"), 12, "111.704"},
                new Object[] {new BigDecimal("111.704000000001"), 12, "111.704000000001"},
                new Object[] {new BigDecimal("111.70400000001"), 12, "111.70400000001"},
                new Object[] {new BigDecimal("111.70400000001"), 6, "111.704"},
                new Object[] {new BigDecimal("111.70400000000000000000000000000000000000000000000001"), 50, "111.70400000000000000000000000000000000000000000000001"},
                new Object[] {new BigDecimal("111.704000000000000000000000000000000000000000000000001"), 50, "111.704"},
                new Object[] {new BigDecimal("111.70400000001"), 6, "111.704"},
                new Object[] {new BigDecimal("111.704000000000000000000000000000000000000000000000001"), 50, "111.704"},
                new Object[] {new BigDecimal("0.59329999999996"), 12, "0.5933"},
                new Object[] {new BigDecimal("0.59329999999996"), 14, "0.59329999999996"},
                new Object[] {new BigDecimal("0.59329999999996"), 3, "0.593"},
                new Object[] {new BigDecimal("0.59329999999996"), 1, "0.6"},
                new Object[] {new BigDecimal("0.59329999999999999999999999999999999999999999999996"), 50, "0.59329999999999999999999999999999999999999999999996"},
                new Object[] {new BigDecimal("0.593299999999999999999999999999999999999999999999996"), 50, "0.5933"},
                new Object[] {new BigDecimal("0.59329999999996"), 12, "0.5933"},
                new Object[] {new BigDecimal("0.593299999999999999999999999999999999999999999999996"), 50, "0.5933"},
                new Object[] {new BigDecimal("111.70400000001"), 0, "112"},
                new Object[] {new BigDecimal("111.70400000001"), -1, "110"},
                new Object[] {new BigDecimal("111.70400000001"), -2, "100"},
                new Object[] {new BigDecimal("111.70400000001"), -3, "0"},
                new Object[] {new BigDecimal("111.70400000001"), -1, "110"}
        ).forEach(params ->
                Assert.assertEquals(params[2], MathUtility.roundWithPrecision((BigDecimal) params[0], (int) params[1]).toPlainString()));
        
        //big decimal, rounding mode
        List.of(new Object[] {new BigDecimal("111.70400000000001"), 12, RoundingMode.UP, "111.704000000001"},
                new Object[] {new BigDecimal("111.704000000000000000001"), 12, RoundingMode.DOWN, "111.704"},
                new Object[] {new BigDecimal("111.7040000000001"), 12, RoundingMode.HALF_EVEN, "111.704"},
                new Object[] {new BigDecimal("111.704000000001"), 12, RoundingMode.HALF_DOWN, "111.704000000001"},
                new Object[] {new BigDecimal("111.70400000001"), 12, RoundingMode.UP, "111.70400000001"},
                new Object[] {new BigDecimal("111.70400000001"), 6, RoundingMode.HALF_UP, "111.704"},
                new Object[] {new BigDecimal("111.70400000000000000000000000000000000000000000000001"), 50, RoundingMode.CEILING, "111.70400000000000000000000000000000000000000000000001"},
                new Object[] {new BigDecimal("111.704000000000000000000000000000000000000000000000001"), 50, RoundingMode.HALF_EVEN, "111.704"},
                new Object[] {new BigDecimal("111.70400000001"), 6, RoundingMode.DOWN, "111.704"},
                new Object[] {new BigDecimal("111.704000000000000000000000000000000000000000000000001"), 50, RoundingMode.FLOOR, "111.704"},
                new Object[] {new BigDecimal("0.59329999999996"), 12, RoundingMode.HALF_DOWN, "0.5933"},
                new Object[] {new BigDecimal("0.59329999999996"), 14, RoundingMode.HALF_DOWN, "0.59329999999996"},
                new Object[] {new BigDecimal("0.59329999999996"), 3, RoundingMode.UP, "0.594"},
                new Object[] {new BigDecimal("0.59329999999996"), 1, RoundingMode.CEILING, "0.6"},
                new Object[] {new BigDecimal("0.59329999999999999999999999999999999999999999999996"), 50, RoundingMode.CEILING, "0.59329999999999999999999999999999999999999999999996"},
                new Object[] {new BigDecimal("0.593299999999999999999999999999999999999999999999996"), 50, RoundingMode.HALF_UP, "0.5933"},
                new Object[] {new BigDecimal("0.59329999999996"), 12, RoundingMode.DOWN, "0.593299999999"},
                new Object[] {new BigDecimal("0.593299999999999999999999999999999999999999999999996"), 50, RoundingMode.FLOOR, "0.59329999999999999999999999999999999999999999999999"},
                new Object[] {new BigDecimal("111.70400000001"), 0, RoundingMode.HALF_DOWN, "112"},
                new Object[] {new BigDecimal("111.70400000001"), -1, RoundingMode.HALF_EVEN, "110"},
                new Object[] {new BigDecimal("111.70400000001"), -2, RoundingMode.UP, "200"},
                new Object[] {new BigDecimal("111.70400000001"), -3, RoundingMode.DOWN, "0"},
                new Object[] {new BigDecimal("111.70400000001"), -1, RoundingMode.CEILING, "120"}
        ).forEach(params ->
                Assert.assertEquals(params[3], MathUtility.roundWithPrecision((BigDecimal) params[0], (int) params[1], (RoundingMode) params[2]).toPlainString()));
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
        List.of(new Object[] {(byte) 2, new Byte[] {(byte) 4, (byte) 2, (byte) 6}},
                new Object[] {(byte) 4, new Byte[] {(byte) 4, (byte) 4, (byte) 6}},
                new Object[] {(byte) 2, new Byte[] {(byte) 6, (byte) 2, (byte) 6}},
                new Object[] {(byte) 1, new Byte[] {(byte) 1, (byte) 4, (byte) 6}},
                new Object[] {(byte) 4, new Byte[] {(byte) 7, (byte) 4, (byte) 6}},
                new Object[] {(byte) -4, new Byte[] {(byte) -2, (byte) -4, (byte) 6}},
                new Object[] {(byte) -2, new Byte[] {(byte) -2, (byte) -1, (byte) 6}},
                new Object[] {(byte) -2, new Byte[] {(byte) 4, (byte) 2, (byte) 6, (byte) -2, (byte) -1, (byte) 6}},
                new Object[] {(byte) 6, new Byte[] {(byte) 6}}
        ).forEach(params ->
                Assert.assertEquals((byte) params[0], MathUtility.min((Byte[]) params[1]).byteValue()));
        
        //short
        List.of(new Object[] {(short) 22, new Short[] {(short) 44, (short) 22, (short) 69}},
                new Object[] {(short) 48, new Short[] {(short) 48, (short) 48, (short) 69}},
                new Object[] {(short) 22, new Short[] {(short) 69, (short) 22, (short) 69}},
                new Object[] {(short) 11, new Short[] {(short) 11, (short) 40, (short) 61}},
                new Object[] {(short) 40, new Short[] {(short) 78, (short) 40, (short) 61}},
                new Object[] {(short) -40, new Short[] {(short) -27, (short) -40, (short) 61}},
                new Object[] {(short) -27, new Short[] {(short) -27, (short) -11, (short) 61}},
                new Object[] {(short) -27, new Short[] {(short) 44, (short) 22, (short) 69, (short) -27, (short) -11, (short) 61}},
                new Object[] {(short) 61, new Short[] {(short) 61}}
        ).forEach(params ->
                Assert.assertEquals((short) params[0], MathUtility.min((Short[]) params[1]).shortValue()));
        
        //int
        List.of(new Object[] {222, new Integer[] {446, 222, 699}},
                new Object[] {481, new Integer[] {481, 481, 699}},
                new Object[] {222, new Integer[] {699, 222, 699}},
                new Object[] {11, new Integer[] {11, 400, 617}},
                new Object[] {400, new Integer[] {737, 400, 617}},
                new Object[] {-400, new Integer[] {-278, -400, 617}},
                new Object[] {-278, new Integer[] {-278, -113, 617}},
                new Object[] {-278, new Integer[] {446, 222, 699, -278, -113, 617}},
                new Object[] {617, new Integer[] {617}}
        ).forEach(params ->
                Assert.assertEquals((int) params[0], MathUtility.min((Integer[]) params[1]).intValue()));
        
        //long
        List.of(new Object[] {2228808404L, new Long[] {4469845000L, 2228808404L, 6991987020L}},
                new Object[] {48100154L, new Long[] {48100154L, 48100154L, 6991987020L}},
                new Object[] {2228808404L, new Long[] {6991987020L, 2228808404L, 6991987020L}},
                new Object[] {11L, new Long[] {11L, 40000001L, 617348095L}},
                new Object[] {40000001L, new Long[] {737980798L, 40000001L, 617348095L}},
                new Object[] {-40000001000L, new Long[] {-27858074887L, -40000001000L, 617348095L}},
                new Object[] {-27858074887L, new Long[] {-27858074887L, -11398056057L, 617348095L}},
                new Object[] {-27858074887L, new Long[] {4469845000L, 2228808404L, 6991987020L, -27858074887L, -11398056057L, 617348095L}},
                new Object[] {617348095L, new Long[] {617348095L}}
        ).forEach(params ->
                Assert.assertEquals((long) params[0], MathUtility.min((Long[]) params[1]).longValue()));
        
        //float
        List.of(new Object[] {2.22f, new Float[] {4.46f, 2.22f, 6.99f}},
                new Object[] {4.81f, new Float[] {4.81f, 4.81f, 6.99f}},
                new Object[] {2.22f, new Float[] {6.99f, 2.22f, 6.99f}},
                new Object[] {1.1f, new Float[] {1.1f, 4.00f, 6.17f}},
                new Object[] {4.00f, new Float[] {7.37f, 4.00f, 6.17f}},
                new Object[] {-4.00f, new Float[] {-2.78f, -4.00f, 6.17f}},
                new Object[] {-2.78f, new Float[] {-2.78f, -1.13f, 6.17f}},
                new Object[] {-2.78f, new Float[] {4.46f, 2.22f, 6.99f, -2.78f, -1.13f, 6.17f}},
                new Object[] {6.17f, new Float[] {6.17f}}
        ).forEach(params ->
                Assert.assertEquals((float) params[0], MathUtility.min((Float[]) params[1]), TestUtils.DELTA_FLOAT));
        
        //double
        List.of(new Object[] {2.228808404d, new Double[] {4.469845d, 2.228808404d, 6.99198702d}},
                new Object[] {4.8100154d, new Double[] {4.8100154d, 4.8100154d, 6.99198702d}},
                new Object[] {2.228808404d, new Double[] {6.99198702d, 2.228808404d, 6.99198702d}},
                new Object[] {1.1d, new Double[] {1.1d, 4.0000001d, 6.17348095d}},
                new Object[] {4.0000001d, new Double[] {7.37980798d, 4.0000001d, 6.17348095d}},
                new Object[] {-4.0000001d, new Double[] {-2.7858074887d, -4.0000001d, 6.17348095d}},
                new Object[] {-2.7858074887d, new Double[] {-2.7858074887d, -1.139805605774d, 6.17348095d}},
                new Object[] {-2.7858074887d, new Double[] {4.469845d, 2.228808404d, 6.99198702d, -2.7858074887d, -1.139805605774d, 6.17348095d}},
                new Object[] {6.17348095d, new Double[] {6.17348095d}}
        ).forEach(params ->
                Assert.assertEquals((double) params[0], MathUtility.min((Double[]) params[1]), TestUtils.DELTA_DOUBLE));
        
        //big decimal
        List.of(new Object[] {new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal[] {new BigDecimal("498048940365649408940894156.46989846504649845"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")}},
                new Object[] {new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal[] {new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")}},
                new Object[] {new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal[] {new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")}},
                new Object[] {new BigDecimal("1.1"), new BigDecimal[] {new BigDecimal("1.1"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal[] {new BigDecimal("70000000000490988798465065653212318888.370989889489740546506505156980798"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("-400000000000000000000.000000000000000000000001"), new BigDecimal[] {new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal[] {new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-100870798769504941324.139805605774"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal[] {new BigDecimal("498048940365649408940894156.46989846504649845"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-100870798769504941324.139805605774"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), new BigDecimal[] {new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}}
        ).forEach(params ->
                Assert.assertEquals(params[0], MathUtility.min((BigDecimal[]) params[1])));
        
        //big integer
        List.of(new Object[] {new BigInteger("298766565984054313210050444"), new BigInteger[] {new BigInteger("498048940365649408940894156"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")}},
                new Object[] {new BigInteger("4161561587498065454121635654"), new BigInteger[] {new BigInteger("4161561587498065454121635654"), new BigInteger("4161561587498065454121635654"), new BigInteger("690890450454565456960987894649")}},
                new Object[] {new BigInteger("298766565984054313210050444"), new BigInteger[] {new BigInteger("690890450454565456960987894649"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")}},
                new Object[] {new BigInteger("1"), new BigInteger[] {new BigInteger("1"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("400000000000000000000"), new BigInteger[] {new BigInteger("70000000000490988798465065653212318888"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("-400000000000000000000"), new BigInteger[] {new BigInteger("-234605409480456054264"), new BigInteger("-400000000000000000000"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("-234605409480456054264"), new BigInteger[] {new BigInteger("-234605409480456054264"), new BigInteger("-100870798769504941324"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("-234605409480456054264"), new BigInteger[] {new BigInteger("498048940365649408940894156"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649"), new BigInteger("-234605409480456054264"), new BigInteger("-100870798769504941324"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("6664598409846520651984845149"), new BigInteger[] {new BigInteger("6664598409846520651984845149")}}
        ).forEach(params ->
                Assert.assertEquals(params[0], MathUtility.min((BigInteger[]) params[1])));
        
        //empty
        Assert.assertEquals((byte) 0, MathUtility.min().byteValue());
        Assert.assertEquals((short) 0, MathUtility.min().shortValue());
        Assert.assertEquals(0, MathUtility.min().intValue());
        Assert.assertEquals(0L, MathUtility.min().longValue());
        Assert.assertEquals(0.0f, MathUtility.min().floatValue(), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(0.0d, MathUtility.min().doubleValue(), TestUtils.DELTA_DOUBLE);
        
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
        List.of(new Object[] {(byte) 6, new Byte[] {(byte) 4, (byte) 2, (byte) 6}},
                new Object[] {(byte) 6, new Byte[] {(byte) 4, (byte) 4, (byte) 6}},
                new Object[] {(byte) 6, new Byte[] {(byte) 6, (byte) 2, (byte) 6}},
                new Object[] {(byte) 6, new Byte[] {(byte) 1, (byte) 4, (byte) 6}},
                new Object[] {(byte) 7, new Byte[] {(byte) 7, (byte) 4, (byte) 6}},
                new Object[] {(byte) 6, new Byte[] {(byte) -2, (byte) -4, (byte) 6}},
                new Object[] {(byte) 6, new Byte[] {(byte) -2, (byte) -1, (byte) 6}},
                new Object[] {(byte) 6, new Byte[] {(byte) 4, (byte) 2, (byte) 6, (byte) -2, (byte) -1, (byte) 6}},
                new Object[] {(byte) 6, new Byte[] {(byte) 6}}
        ).forEach(params ->
                Assert.assertEquals((byte) params[0], MathUtility.max((Byte[]) params[1]).byteValue()));
        
        //short
        List.of(new Object[] {(short) 69, new Short[] {(short) 44, (short) 22, (short) 69}},
                new Object[] {(short) 69, new Short[] {(short) 48, (short) 48, (short) 69}},
                new Object[] {(short) 69, new Short[] {(short) 69, (short) 22, (short) 69}},
                new Object[] {(short) 61, new Short[] {(short) 11, (short) 40, (short) 61}},
                new Object[] {(short) 78, new Short[] {(short) 78, (short) 40, (short) 61}},
                new Object[] {(short) 61, new Short[] {(short) -27, (short) -40, (short) 61}},
                new Object[] {(short) 61, new Short[] {(short) -27, (short) -11, (short) 61}},
                new Object[] {(short) 69, new Short[] {(short) 44, (short) 22, (short) 69, (short) -27, (short) -11, (short) 61}},
                new Object[] {(short) 61, new Short[] {(short) 61}}
        ).forEach(params ->
                Assert.assertEquals((short) params[0], MathUtility.max((Short[]) params[1]).shortValue()));
        
        //int
        List.of(new Object[] {699, new Integer[] {446, 222, 699}},
                new Object[] {699, new Integer[] {481, 481, 699}},
                new Object[] {699, new Integer[] {699, 222, 699}},
                new Object[] {617, new Integer[] {11, 400, 617}},
                new Object[] {737, new Integer[] {737, 400, 617}},
                new Object[] {617, new Integer[] {-278, -400, 617}},
                new Object[] {617, new Integer[] {-278, -113, 617}},
                new Object[] {699, new Integer[] {446, 222, 699, -278, -113, 617}},
                new Object[] {617, new Integer[] {617}}
        ).forEach(params ->
                Assert.assertEquals((int) params[0], MathUtility.max((Integer[]) params[1]).intValue()));
        
        //long
        List.of(new Object[] {6991987020L, new Long[] {4469845000L, 2228808404L, 6991987020L}},
                new Object[] {6991987020L, new Long[] {48100154L, 48100154L, 6991987020L}},
                new Object[] {6991987020L, new Long[] {6991987020L, 2228808404L, 6991987020L}},
                new Object[] {617348095L, new Long[] {11L, 40000001L, 617348095L}},
                new Object[] {737980798L, new Long[] {737980798L, 40000001L, 617348095L}},
                new Object[] {617348095L, new Long[] {-27858074887L, -40000001000L, 617348095L}},
                new Object[] {617348095L, new Long[] {-27858074887L, -11398056057L, 617348095L}},
                new Object[] {6991987020L, new Long[] {4469845000L, 2228808404L, 6991987020L, -27858074887L, -11398056057L, 617348095L}},
                new Object[] {617348095L, new Long[] {617348095L}}
        ).forEach(params ->
                Assert.assertEquals((long) params[0], MathUtility.max((Long[]) params[1]).longValue()));
        
        //float
        List.of(new Object[] {6.99f, new Float[] {4.46f, 2.22f, 6.99f}},
                new Object[] {6.99f, new Float[] {4.81f, 4.81f, 6.99f}},
                new Object[] {6.99f, new Float[] {6.99f, 2.22f, 6.99f}},
                new Object[] {6.17f, new Float[] {1.1f, 4.00f, 6.17f}},
                new Object[] {7.37f, new Float[] {7.37f, 4.00f, 6.17f}},
                new Object[] {6.17f, new Float[] {-2.78f, -4.00f, 6.17f}},
                new Object[] {6.17f, new Float[] {-2.78f, -1.13f, 6.17f}},
                new Object[] {6.99f, new Float[] {4.46f, 2.22f, 6.99f, -2.78f, -1.13f, 6.17f}},
                new Object[] {6.17f, new Float[] {6.17f}}
        ).forEach(params ->
                Assert.assertEquals((float) params[0], MathUtility.max((Float[]) params[1]), TestUtils.DELTA_FLOAT));
        
        //double
        List.of(new Object[] {6.99198702d, new Double[] {4.469845d, 2.228808404d, 6.99198702d}},
                new Object[] {6.99198702d, new Double[] {4.8100154d, 4.8100154d, 6.99198702d}},
                new Object[] {6.99198702d, new Double[] {6.99198702d, 2.228808404d, 6.99198702d}},
                new Object[] {6.17348095d, new Double[] {1.1d, 4.0000001d, 6.17348095d}},
                new Object[] {7.37980798d, new Double[] {7.37980798d, 4.0000001d, 6.17348095d}},
                new Object[] {6.17348095d, new Double[] {-2.7858074887d, -4.0000001d, 6.17348095d}},
                new Object[] {6.17348095d, new Double[] {-2.7858074887d, -1.139805605774d, 6.17348095d}},
                new Object[] {6.99198702d, new Double[] {4.469845d, 2.228808404d, 6.99198702d, -2.7858074887d, -1.139805605774d, 6.17348095d}},
                new Object[] {6.17348095d, new Double[] {6.17348095d}}
        ).forEach(params ->
                Assert.assertEquals((double) params[0], MathUtility.max((Double[]) params[1]), TestUtils.DELTA_DOUBLE));
        
        //big decimal
        List.of(new Object[] {new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal[] {new BigDecimal("498048940365649408940894156.46989846504649845"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")}},
                new Object[] {new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal[] {new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")}},
                new Object[] {new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal[] {new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")}},
                new Object[] {new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), new BigDecimal[] {new BigDecimal("1.1"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("70000000000490988798465065653212318888.370989889489740546506505156980798"), new BigDecimal[] {new BigDecimal("70000000000490988798465065653212318888.370989889489740546506505156980798"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), new BigDecimal[] {new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), new BigDecimal[] {new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-100870798769504941324.139805605774"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal[] {new BigDecimal("498048940365649408940894156.46989846504649845"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-100870798769504941324.139805605774"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}},
                new Object[] {new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), new BigDecimal[] {new BigDecimal("6664598409846520651984845149.1734680098980487489408995")}}
        ).forEach(params ->
                Assert.assertEquals(params[0], MathUtility.max((BigDecimal[]) params[1])));
        
        //big integer
        List.of(new Object[] {new BigInteger("690890450454565456960987894649"), new BigInteger[] {new BigInteger("498048940365649408940894156"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")}},
                new Object[] {new BigInteger("690890450454565456960987894649"), new BigInteger[] {new BigInteger("4161561587498065454121635654"), new BigInteger("4161561587498065454121635654"), new BigInteger("690890450454565456960987894649")}},
                new Object[] {new BigInteger("690890450454565456960987894649"), new BigInteger[] {new BigInteger("690890450454565456960987894649"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")}},
                new Object[] {new BigInteger("6664598409846520651984845149"), new BigInteger[] {new BigInteger("1"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("70000000000490988798465065653212318888"), new BigInteger[] {new BigInteger("70000000000490988798465065653212318888"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("6664598409846520651984845149"), new BigInteger[] {new BigInteger("-234605409480456054264"), new BigInteger("-400000000000000000000"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("6664598409846520651984845149"), new BigInteger[] {new BigInteger("-234605409480456054264"), new BigInteger("-100870798769504941324"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("690890450454565456960987894649"), new BigInteger[] {new BigInteger("498048940365649408940894156"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649"), new BigInteger("-234605409480456054264"), new BigInteger("-100870798769504941324"), new BigInteger("6664598409846520651984845149")}},
                new Object[] {new BigInteger("6664598409846520651984845149"), new BigInteger[] {new BigInteger("6664598409846520651984845149")}}
        ).forEach(params ->
                Assert.assertEquals(params[0], MathUtility.max((BigInteger[]) params[1])));
        
        //empty
        Assert.assertEquals((byte) 0, MathUtility.max().byteValue());
        Assert.assertEquals((short) 0, MathUtility.max().shortValue());
        Assert.assertEquals(0, MathUtility.max().intValue());
        Assert.assertEquals(0L, MathUtility.max().longValue());
        Assert.assertEquals(0.0f, MathUtility.max().floatValue(), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(0.0d, MathUtility.max().doubleValue(), TestUtils.DELTA_DOUBLE);
        
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
        List.of(new long[] {0, 0},
                new long[] {1, 1},
                new long[] {11, 2},
                new long[] {123, 6},
                new long[] {-123, 6},
                new long[] {321, 6},
                new long[] {-321, 6},
                new long[] {50000, 5},
                new long[] {842112, 18},
                new long[] {3154201, 16},
                new long[] {-70165483, 34},
                new long[] {123456789, 45},
                new long[] {987654321, 45},
                new long[] {Long.MAX_VALUE, 88},
                new long[] {Long.MIN_VALUE, 89}
        ).forEach(params ->
                Assert.assertEquals((int) params[1], MathUtility.digitSum(params[0])));
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
        List.of(new long[] {0, 0},
                new long[] {1, 1},
                new long[] {11, 0},
                new long[] {123, 2},
                new long[] {-123, 2},
                new long[] {321, 2},
                new long[] {-321, 2},
                new long[] {50000, 5},
                new long[] {842112, 4},
                new long[] {3154201, 6},
                new long[] {-70165483, 8},
                new long[] {123456789, 5},
                new long[] {987654321, 5},
                new long[] {Long.MAX_VALUE, 18},
                new long[] {Long.MIN_VALUE, 19}
        ).forEach(params ->
                Assert.assertEquals((int) params[1], MathUtility.digitSumAlternating(params[0])));
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
        List.of(new long[] {0, 0},
                new long[] {1, 1},
                new long[] {11, 3},
                new long[] {123, 14},
                new long[] {-123, 14},
                new long[] {321, 10},
                new long[] {-321, 10},
                new long[] {50000, 5},
                new long[] {842112, 43},
                new long[] {3154201, 53},
                new long[] {-70165483, 163},
                new long[] {123456789, 285},
                new long[] {987654321, 165},
                new long[] {Long.MAX_VALUE, 941},
                new long[] {Long.MIN_VALUE, 960}
        ).forEach(params ->
                Assert.assertEquals((int) params[1], MathUtility.digitSumWeighted(params[0])));
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
        List.of(new long[] {0, 1, 1},
                new long[] {1, 5, 6},
                new long[] {11, -3, -1},
                new long[] {123, 115, 121},
                new long[] {-123, 0, 6},
                new long[] {321, 0, 6},
                new long[] {-321, -31, -25},
                new long[] {50000, 6, 11},
                new long[] {842112, 13, 31},
                new long[] {3154201, 17, 33},
                new long[] {-70165483, -1, 33},
                new long[] {123456789, 7, 52},
                new long[] {987654321, 99, 144},
                new long[] {Long.MAX_VALUE, 10, 98},
                new long[] {Long.MIN_VALUE, 10, 99}
        ).forEach(params ->
                Assert.assertEquals((int) params[2], MathUtility.digitSumK(params[0], (int) params[1])));
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
