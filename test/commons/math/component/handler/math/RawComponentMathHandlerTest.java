/*
 * File:    RawComponentMathHandlerTest.java
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
 * JUnit test of RawComponentMathHandler.
 *
 * @see RawComponentMathHandler
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({RawComponentMathHandler.class})
public class RawComponentMathHandlerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RawComponentMathHandlerTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private RawComponentMathHandler sut;
    
    
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
        sut = new RawComponentMathHandler();
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
     * @see RawComponentMathHandler#PRECISION
     * @see RawComponentMathHandler#SIGNIFICANT_FIGURES
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(1E-12, RawComponentMathHandler.PRECISION);
        Assert.assertEquals(12, RawComponentMathHandler.SIGNIFICANT_FIGURES);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#RawComponentMathHandler()
     */
    @Test
    public void testConstructors() throws Exception {
        RawComponentMathHandler sut = new RawComponentMathHandler();
        Assert.assertNotNull(sut);
        Assert.assertTrue(sut instanceof ComponentMathHandlerInterface);
    }
    
    /**
     * JUnit test of zero.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#zero()
     */
    @Test
    public void testZero() throws Exception {
        Assert.assertEquals(0.0, sut.zero().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of one.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#one()
     */
    @Test
    public void testOne() throws Exception {
        Assert.assertEquals(1.0, sut.one().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of negativeOne.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#negativeOne()
     */
    @Test
    public void testNegativeOne() throws Exception {
        Assert.assertEquals(-1.0, sut.negativeOne().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of valueOf.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#valueOf(Number)
     */
    @Test
    public void testValueOf() throws Exception {
        Assert.assertEquals(1.0, sut.valueOf(1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(18.0, sut.valueOf(18).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1616.4133, sut.valueOf(1616.4133).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-6.4, sut.valueOf(-6.4).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(18.0, sut.valueOf(Integer.valueOf("18")).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1616.4133, sut.valueOf(Double.valueOf("1616.4133")).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(18.16475068198105, sut.valueOf(new BigDecimal("18.1647506819810510351971084987")).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of array.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#array(int)
     */
    @Test
    public void testArray() throws Exception {
        Number[] array;
        
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
     * @see RawComponentMathHandler#arrayGenerator()
     */
    @Test
    public void testArrayGenerator() throws Exception {
        IntFunction<Number[]> generator;
        Number[] array;
        
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
     * @see RawComponentMathHandler#add(Number, Number)
     */
    @Test
    public void testAdd() throws Exception {
        Assert.assertEquals(9.093, sut.add(5.1, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(7.986, sut.add(3.993, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(13, sut.add(6.0, 7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.add(6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-13, sut.add(-6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.add(0.50000001, 0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(6, sut.add(5.0, 1.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(5, sut.add(5.0, 0.0).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of subtract.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#subtract(Number, Number)
     */
    @Test
    public void testSubtract() throws Exception {
        Assert.assertEquals(1.107, sut.subtract(5.1, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0, sut.subtract(3.993, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.subtract(6.0, 7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(13, sut.subtract(6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.subtract(-6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.00000002, sut.subtract(0.50000001, 0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(4, sut.subtract(5.0, 1.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(5, sut.subtract(5.0, 0.0).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#multiply(Number, Number)
     */
    @Test
    public void testMultiply() throws Exception {
        Assert.assertEquals(20.3643, sut.multiply(5.1, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(15.944049, sut.multiply(3.993, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(42, sut.multiply(6.0, 7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-42, sut.multiply(6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(42, sut.multiply(-6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.25, sut.multiply(0.50000001, 0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(5, sut.multiply(5.0, 1.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0, sut.multiply(5.0, 0.0).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of divide.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#divide(Number, Number)
     */
    @Test
    public void testDivide() throws Exception {
        Assert.assertEquals(1.2772351615326822, sut.divide(5.1, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.divide(3.993, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.8571428571428571, sut.divide(6.0, 7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-0.8571428571428571, sut.divide(6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.8571428571428571, sut.divide(-6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.00000004, sut.divide(0.50000001, 0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(5, sut.divide(5.0, 1.0).doubleValue(), TestUtils.DELTA);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.divide(5.0, 0.0));
    }
    
    /**
     * JUnit test of power.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#power(Number, Number)
     */
    @Test
    public void testPower() throws Exception {
        Assert.assertEquals(668.8484318170732, sut.power(5.1, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(251.76082068144012, sut.power(3.993, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(279936, sut.power(6.0, 7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.000003572245085, sut.power(6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-0.000003572245085, sut.power(-6.0, -7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.707106793158906, sut.power(0.50000001, 0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(5, sut.power(5.0, 1.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.power(5.0, 0.0).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#root(Number, Number)
     */
    @Test
    public void testRoot() throws Exception {
        Assert.assertEquals(1.5038435195204094, sut.root(5.1, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.4144524660268805, sut.root(3.993, 3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.2917083420907467, sut.root(6.0, 7.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.7741685699586098, sut.root(6.0, -7.0).doubleValue(), TestUtils.DELTA);
        TestUtils.assertException(ArithmeticException.class, "Result of root is imaginary", () ->
                sut.root(-6.0, -7.0));
        Assert.assertEquals(0.250000003, sut.root(0.50000001, 0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(5, sut.root(5.0, 1.0).doubleValue(), TestUtils.DELTA);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.root(5.0, 0.0));
    }
    
    /**
     * JUnit test of sqrt.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#sqrt(Number)
     */
    @Test
    public void testSqrt() throws Exception {
        Assert.assertEquals(2.258317958127243, sut.sqrt(5.1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.9982492337043445, sut.sqrt(3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(2.449489742783178, sut.sqrt(6.0).doubleValue(), TestUtils.DELTA);
        TestUtils.assertException(ArithmeticException.class, "Result of square root is imaginary", () ->
                sut.sqrt(-6.0));
        Assert.assertEquals(0.7071067882576153, sut.sqrt(0.50000001).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.7071067741154797, sut.sqrt(0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.sqrt(1.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0, sut.sqrt(0.0).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of reciprocal.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#reciprocal(Number)
     */
    @Test
    public void testReciprocal() throws Exception {
        Assert.assertEquals(0.19607843137254904, sut.reciprocal(5.1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.25043826696719257, sut.reciprocal(3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.16666666666666666, sut.reciprocal(6.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-0.16666666666666666, sut.reciprocal(-6.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.99999996, sut.reciprocal(0.50000001).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(2.00000004, sut.reciprocal(0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.reciprocal(1.0).doubleValue(), TestUtils.DELTA);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.reciprocal(0.0));
    }
    
    /**
     * JUnit test of abs.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#abs(Number)
     */
    @Test
    public void testAbs() throws Exception {
        Assert.assertEquals(5.1, sut.abs(5.1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(3.993, sut.abs(3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(6, sut.abs(6.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(6, sut.abs(-6.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.50000001, sut.abs(0.50000001).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.49999999, sut.abs(0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.abs(1.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0, sut.abs(0.0).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of negate.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#negate(Number)
     */
    @Test
    public void testNegate() throws Exception {
        Assert.assertEquals(-5.1, sut.negate(5.1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-3.993, sut.negate(3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-6, sut.negate(6.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(6, sut.negate(-6.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-0.50000001, sut.negate(0.50000001).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-0.49999999, sut.negate(0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1, sut.negate(1.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0, sut.negate(0.0).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#round(Number)
     */
    @Test
    public void testRound() throws Exception {
        Assert.assertEquals(5, sut.round(5.1).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(4, sut.round(3.993).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(6, sut.round(6.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-6, sut.round(-6.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.round(0.50000001).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0, sut.round(0.49999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.round(1.0).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0, sut.round(0.0).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of compare.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#compare(Number, Number)
     */
    @Test
    public void testCompare() throws Exception {
        Assert.assertEquals(1, sut.compare(5.1, 3.993));
        Assert.assertEquals(0, sut.compare(3.993, 3.993));
        Assert.assertEquals(-1, sut.compare(6.0, 7.0));
        Assert.assertEquals(1, sut.compare(6.0, -7.0));
        Assert.assertEquals(1, sut.compare(-6.0, -7.0));
        Assert.assertEquals(1, sut.compare(0.50000001, 0.49999999));
        Assert.assertEquals(1, sut.compare(5.0, 1.0));
        Assert.assertEquals(1, sut.compare(5.0, 0.0));
    }
    
    /**
     * JUnit test of isEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#isEqual(Number, Number)
     */
    @Test
    public void testIsEqual() throws Exception {
        Assert.assertFalse(sut.isEqual(5.1, 3.993));
        Assert.assertTrue(sut.isEqual(3.993, 3.993));
        Assert.assertFalse(sut.isEqual(6.0, 7.0));
        Assert.assertFalse(sut.isEqual(6.0, -7.0));
        Assert.assertFalse(sut.isEqual(-6.0, -7.0));
        Assert.assertFalse(sut.isEqual(0.50000001, 0.49999999));
        Assert.assertFalse(sut.isEqual(5.0, 1.0));
        Assert.assertFalse(sut.isEqual(5.0, 0.0));
    }
    
    /**
     * JUnit test of isZero.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#isZero(Number)
     */
    @Test
    public void testIsZero() throws Exception {
        Assert.assertFalse(sut.isZero(5.1));
        Assert.assertFalse(sut.isZero(3.993));
        Assert.assertFalse(sut.isZero(6.0));
        Assert.assertFalse(sut.isZero(-6.0));
        Assert.assertFalse(sut.isZero(0.50000001));
        Assert.assertFalse(sut.isZero(0.49999999));
        Assert.assertFalse(sut.isZero(1.0));
        Assert.assertTrue(sut.isZero(0.0));
    }
    
    /**
     * JUnit test of clean.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#clean(Number)
     */
    @Test
    public void testClean() throws Exception {
        Assert.assertEquals(5.1, sut.clean(5.100000000000000000000000001).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(3.994, sut.clean(3.9939999999999999999999999996).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(6, sut.clean(6.0000000000000000000000000001123).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-6, sut.clean(-6.00000000000000000000000000009711).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.50000002, sut.clean(0.500000019999999999999999999999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.5, sut.clean(0.49999999999999999999999999999999).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1, sut.clean(1.00000000000000000000000000000001).doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0, sut.clean(0.0000000000000000000000000000001).doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        Assert.assertEquals(1E-12, sut.getPrecision().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of getSignificantFigures.
     *
     * @throws Exception When there is an exception.
     * @see IntComponentMathHandler#getSignificantFigures()
     */
    @Test
    public void testGetSignificantFigures() throws Exception {
        Assert.assertEquals(12, sut.getSignificantFigures());
    }
    
}
