/*
 * File:    IntComponentMathHandlerTest.java
 * Package: commons.math.component.handler.error
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.math;

import java.math.BigDecimal;
import java.util.function.IntFunction;

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
 * JUnit test of IntComponentMathHandler.
 *
 * @see IntComponentMathHandler
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({IntComponentMathHandler.class})
public class IntComponentMathHandlerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(IntComponentMathHandlerTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private IntComponentMathHandler sut;
    
    
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
    @Before
    public void setup() throws Exception {
        sut = new IntComponentMathHandler();
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
     * @see IntComponentMathHandler#PRECISION
     * @see IntComponentMathHandler#SIGNIFICANT_FIGURES
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(1, IntComponentMathHandler.PRECISION.intValue());
        Assert.assertEquals(0, IntComponentMathHandler.SIGNIFICANT_FIGURES);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#IntComponentMathHandler()
     */
    @Test
    public void testConstructors() throws Exception {
        IntComponentMathHandler sut = new IntComponentMathHandler();
        Assert.assertNotNull(sut);
        Assert.assertTrue(sut instanceof ComponentMathHandlerInterface);
    }
    
    /**
     * JUnit test of zero.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#zero()
     */
    @Test
    public void testZero() throws Exception {
        Assert.assertEquals(0, sut.zero().intValue());
    }
    
    /**
     * JUnit test of one.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#one()
     */
    @Test
    public void testOne() throws Exception {
        Assert.assertEquals(1, sut.one().intValue());
    }
    
    /**
     * JUnit test of negativeOne.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#negativeOne()
     */
    @Test
    public void testNegativeOne() throws Exception {
        Assert.assertEquals(-1, sut.negativeOne().intValue());
    }
    
    /**
     * JUnit test of valueOf.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#valueOf(Number)
     */
    @Test
    public void testValueOf() throws Exception {
        Assert.assertEquals(1, sut.valueOf(1).intValue());
        Assert.assertEquals(18, sut.valueOf(18).intValue());
        Assert.assertEquals(1616, sut.valueOf(1616.4133).intValue());
        Assert.assertEquals(-6, sut.valueOf(-6.4).intValue());
        Assert.assertEquals(18, sut.valueOf(Double.valueOf("18")).intValue());
        Assert.assertEquals(1616, sut.valueOf(Double.valueOf("1616.4133")).intValue());
        Assert.assertEquals(18, sut.valueOf(new BigDecimal("18.1647506819810510351971084987")).intValue());
    }
    
    /**
     * JUnit test of array.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#array(int)
     */
    @Test
    public void testArray() throws Exception {
        Integer[] array;
        
        //standard
        
        array = sut.array(2);
        Assert.assertNotNull(array);
        Assert.assertEquals(2, array.length);
        
        array = sut.array(8);
        Assert.assertNotNull(array);
        Assert.assertEquals(8, array.length);
        
        array = sut.array(0);
        Assert.assertNotNull(array);
        Assert.assertEquals(0, array.length);
        
        //invalid
        
        TestUtils.assertException(NegativeArraySizeException.class, () ->
                sut.array(-1));
    }
    
    /**
     * JUnit test of arrayGenerator.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#arrayGenerator()
     */
    @Test
    public void testArrayGenerator() throws Exception {
        IntFunction<Integer[]> generator;
        Integer[] array;
        
        //standard
        
        generator = sut.arrayGenerator();
        Assert.assertNotNull(generator);
        array = generator.apply(2);
        Assert.assertNotNull(array);
        Assert.assertEquals(2, array.length);
        
        generator = sut.arrayGenerator();
        Assert.assertNotNull(generator);
        array = generator.apply(8);
        Assert.assertNotNull(array);
        Assert.assertEquals(8, array.length);
        
        generator = sut.arrayGenerator();
        Assert.assertNotNull(generator);
        array = generator.apply(0);
        Assert.assertNotNull(array);
        Assert.assertEquals(0, array.length);
        
        //invalid
        
        TestUtils.assertException(NegativeArraySizeException.class, () ->
                sut.arrayGenerator().apply(-1));
    }
    
    /**
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#add(Integer, Integer)
     */
    @Test
    public void testAdd() throws Exception {
        Assert.assertEquals(8, sut.add(5, 3).intValue());
        Assert.assertEquals(6, sut.add(3, 3).intValue());
        Assert.assertEquals(13, sut.add(6, 7).intValue());
        Assert.assertEquals(-1, sut.add(6, -7).intValue());
        Assert.assertEquals(-13, sut.add(-6, -7).intValue());
        Assert.assertEquals(1, sut.add(1, 0).intValue());
        Assert.assertEquals(6, sut.add(5, 1).intValue());
        Assert.assertEquals(5, sut.add(5, 0).intValue());
    }
    
    /**
     * JUnit test of subtract.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#subtract(Integer, Integer)
     */
    @Test
    public void testSubtract() throws Exception {
        Assert.assertEquals(2, sut.subtract(5, 3).intValue());
        Assert.assertEquals(0, sut.subtract(3, 3).intValue());
        Assert.assertEquals(-1, sut.subtract(6, 7).intValue());
        Assert.assertEquals(13, sut.subtract(6, -7).intValue());
        Assert.assertEquals(1, sut.subtract(-6, -7).intValue());
        Assert.assertEquals(1, sut.subtract(1, 0).intValue());
        Assert.assertEquals(4, sut.subtract(5, 1).intValue());
        Assert.assertEquals(5, sut.subtract(5, 0).intValue());
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#multiply(Integer, Integer)
     */
    @Test
    public void testMultiply() throws Exception {
        Assert.assertEquals(15, sut.multiply(5, 3).intValue());
        Assert.assertEquals(9, sut.multiply(3, 3).intValue());
        Assert.assertEquals(42, sut.multiply(6, 7).intValue());
        Assert.assertEquals(-42, sut.multiply(6, -7).intValue());
        Assert.assertEquals(42, sut.multiply(-6, -7).intValue());
        Assert.assertEquals(0, sut.multiply(1, 0).intValue());
        Assert.assertEquals(5, sut.multiply(5, 1).intValue());
        Assert.assertEquals(0, sut.multiply(5, 0).intValue());
    }
    
    /**
     * JUnit test of divide.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#divide(Integer, Integer)
     */
    @Test
    public void testDivide() throws Exception {
        Assert.assertEquals(1, sut.divide(5, 3).intValue());
        Assert.assertEquals(1, sut.divide(3, 3).intValue());
        Assert.assertEquals(0, sut.divide(6, 7).intValue());
        Assert.assertEquals(0, sut.divide(6, -7).intValue());
        Assert.assertEquals(0, sut.divide(-6, -7).intValue());
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.divide(1, 0));
        Assert.assertEquals(5, sut.divide(5, 1).intValue());
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.divide(5, 0));
    }
    
    /**
     * JUnit test of power.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#power(Integer, Integer)
     */
    @Test
    public void testPower() throws Exception {
        Assert.assertEquals(125, sut.power(5, 3).intValue());
        Assert.assertEquals(27, sut.power(3, 3).intValue());
        Assert.assertEquals(279936, sut.power(6, 7).intValue());
        Assert.assertEquals(0, sut.power(6, -7).intValue());
        Assert.assertEquals(0, sut.power(-6, -7).intValue());
        Assert.assertEquals(1, sut.power(1, 0).intValue());
        Assert.assertEquals(5, sut.power(5, 1).intValue());
        Assert.assertEquals(1, sut.power(5, 0).intValue());
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#root(Integer, Integer)
     */
    @Test
    public void testRoot() throws Exception {
        Assert.assertEquals(1, sut.root(5, 3).intValue());
        Assert.assertEquals(1, sut.root(3, 3).intValue());
        Assert.assertEquals(1, sut.root(6, 7).intValue());
        Assert.assertEquals(1, sut.root(6, -7).intValue());
        TestUtils.assertException(ArithmeticException.class, "Result of root is imaginary", () ->
                sut.root(-6, -7));
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.root(1, 0));
        Assert.assertEquals(5, sut.root(5, 1).intValue());
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.root(5, 0));
    }
    
    /**
     * JUnit test of sqrt.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#sqrt(Integer)
     */
    @Test
    public void testSqrt() throws Exception {
        Assert.assertEquals(2, sut.sqrt(5).intValue());
        Assert.assertEquals(1, sut.sqrt(3).intValue());
        Assert.assertEquals(2, sut.sqrt(6).intValue());
        TestUtils.assertException(ArithmeticException.class, "Result of square root is imaginary", () ->
                sut.sqrt(-6));
        Assert.assertEquals(1, sut.sqrt(1).intValue());
        Assert.assertEquals(0, sut.sqrt(0).intValue());
    }
    
    /**
     * JUnit test of reciprocal.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#reciprocal(Integer)
     */
    @Test
    public void testReciprocal() throws Exception {
        Assert.assertEquals(0, sut.reciprocal(5).intValue());
        Assert.assertEquals(0, sut.reciprocal(3).intValue());
        Assert.assertEquals(0, sut.reciprocal(6).intValue());
        Assert.assertEquals(0, sut.reciprocal(-6).intValue());
        Assert.assertEquals(1, sut.reciprocal(1).intValue());
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.reciprocal(0));
    }
    
    /**
     * JUnit test of abs.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#abs(Integer)
     */
    @Test
    public void testAbs() throws Exception {
        Assert.assertEquals(5, sut.abs(5).intValue());
        Assert.assertEquals(3, sut.abs(3).intValue());
        Assert.assertEquals(6, sut.abs(6).intValue());
        Assert.assertEquals(6, sut.abs(-6).intValue());
        Assert.assertEquals(1, sut.abs(1).intValue());
        Assert.assertEquals(0, sut.abs(0).intValue());
    }
    
    /**
     * JUnit test of negate.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#negate(Integer)
     */
    @Test
    public void testNegate() throws Exception {
        Assert.assertEquals(-5, sut.negate(5).intValue());
        Assert.assertEquals(-3, sut.negate(3).intValue());
        Assert.assertEquals(-6, sut.negate(6).intValue());
        Assert.assertEquals(6, sut.negate(-6).intValue());
        Assert.assertEquals(-1, sut.negate(1).intValue());
        Assert.assertEquals(0, sut.negate(0).intValue());
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#round(Integer)
     */
    @Test
    public void testRound() throws Exception {
        Assert.assertEquals(5, sut.round(5).intValue());
        Assert.assertEquals(3, sut.round(3).intValue());
        Assert.assertEquals(6, sut.round(6).intValue());
        Assert.assertEquals(-6, sut.round(-6).intValue());
        Assert.assertEquals(1, sut.round(1).intValue());
        Assert.assertEquals(0, sut.round(0).intValue());
    }
    
    /**
     * JUnit test of compare.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#compare(Integer, Integer)
     */
    @Test
    public void testCompare() throws Exception {
        Assert.assertEquals(1, sut.compare(5, 3));
        Assert.assertEquals(0, sut.compare(3, 3));
        Assert.assertEquals(-1, sut.compare(6, 7));
        Assert.assertEquals(1, sut.compare(6, -7));
        Assert.assertEquals(1, sut.compare(-6, -7));
        Assert.assertEquals(1, sut.compare(1, 0));
        Assert.assertEquals(1, sut.compare(5, 1));
        Assert.assertEquals(1, sut.compare(5, 0));
    }
    
    /**
     * JUnit test of isEqual.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#isEqual(Integer, Integer)
     */
    @Test
    public void testIsEqual() throws Exception {
        //standard
        Assert.assertFalse(sut.isEqual(5, 3));
        Assert.assertTrue(sut.isEqual(3, 3));
        Assert.assertFalse(sut.isEqual(6, 7));
        Assert.assertFalse(sut.isEqual(6, -7));
        Assert.assertFalse(sut.isEqual(-6, -7));
        Assert.assertFalse(sut.isEqual(1, 0));
        Assert.assertFalse(sut.isEqual(5, 1));
        Assert.assertFalse(sut.isEqual(5, 0));
    }
    
    /**
     * JUnit test of isZero.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#isZero(Integer)
     */
    @Test
    public void testIsZero() throws Exception {
        Assert.assertFalse(sut.isZero(5));
        Assert.assertFalse(sut.isZero(3));
        Assert.assertFalse(sut.isZero(6));
        Assert.assertFalse(sut.isZero(-6));
        Assert.assertFalse(sut.isZero(1));
        Assert.assertTrue(sut.isZero(0));
    }
    
    /**
     * JUnit test of clean.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#clean(Integer)
     */
    @Test
    public void testClean() throws Exception {
        Assert.assertEquals(5, sut.clean(5).intValue());
        Assert.assertEquals(4, sut.clean(4).intValue());
        Assert.assertEquals(6, sut.clean(6).intValue());
        Assert.assertEquals(-6, sut.clean(-6).intValue());
        Assert.assertEquals(2, sut.clean(2).intValue());
        Assert.assertEquals(1, sut.clean(1).intValue());
        Assert.assertEquals(0, sut.clean(0).intValue());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        Assert.assertEquals(1, sut.getPrecision().intValue());
    }
    
    /**
     * JUnit test of getSignificantFigures.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#getSignificantFigures()
     */
    @Test
    public void testGetSignificantFigures() throws Exception {
        Assert.assertEquals(0, sut.getSignificantFigures());
    }
    
}
