/*
 * File:    ComponentMathHandlerInterfaceTest.java
 * Package: commons.math.component.handler.math
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.math;

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
 * JUnit test of ComponentMathHandlerInterface.
 *
 * @see ComponentMathHandlerInterface
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ComponentMathHandlerInterface.class})
public class ComponentMathHandlerInterfaceTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ComponentMathHandlerInterfaceTest.class);
    
    
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
     * @see ComponentMathHandlerInterface#DEFAULT_PRECISION
     * @see ComponentMathHandlerInterface#DEFAULT_SIGNIFICANT_FIGURES
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(0.000000000001, ComponentMathHandlerInterface.DEFAULT_PRECISION);
        Assert.assertEquals(12, ComponentMathHandlerInterface.DEFAULT_SIGNIFICANT_FIGURES);
    }
    
    /**
     * JUnit test of zero.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#zero()
     */
    @Test
    public void testZero() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "zero");
    }
    
    /**
     * JUnit test of one.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#one()
     */
    @Test
    public void testOne() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "one");
    }
    
    /**
     * JUnit test of negativeOne.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#negativeOne()
     */
    @Test
    public void testNegativeOne() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "negativeOne");
    }
    
    /**
     * JUnit test of valueOf.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#valueOf(Number)
     */
    @Test
    public void testValueOf() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "valueOf", Number.class);
    }
    
    /**
     * JUnit test of array.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#array(int)
     */
    @Test
    public void testArray() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "array", int.class);
    }
    
    /**
     * JUnit test of arrayGenerator.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#arrayGenerator()
     */
    @Test
    public void testArrayGenerator() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "arrayGenerator");
    }
    
    /**
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#add(Number, Number)
     */
    @Test
    public void testAdd() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "add", Number.class, Number.class);
    }
    
    /**
     * JUnit test of subtract.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#subtract(Number, Number)
     */
    @Test
    public void testSubtract() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "subtract", Number.class, Number.class);
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#multiply(Number, Number)
     */
    @Test
    public void testMultiply() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "multiply", Number.class, Number.class);
    }
    
    /**
     * JUnit test of divide.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#divide(Number, Number)
     */
    @Test
    public void testDivide() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "divide", Number.class, Number.class);
    }
    
    /**
     * JUnit test of power.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#power(Number, Number)
     */
    @Test
    public void testPower() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "power", Number.class, Number.class);
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#root(Number, Number)
     */
    @Test
    public void testRoot() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "root", Number.class, Number.class);
    }
    
    /**
     * JUnit test of sqrt.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#sqrt(Number)
     */
    @Test
    public void testSqrt() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "sqrt", Number.class);
    }
    
    /**
     * JUnit test of reciprocal.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#reciprocal(Number)
     */
    @Test
    public void testReciprocal() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "reciprocal", Number.class);
    }
    
    /**
     * JUnit test of abs.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#abs(Number)
     */
    @Test
    public void testAbs() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "abs", Number.class);
    }
    
    /**
     * JUnit test of negate.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#negate(Number)
     */
    @Test
    public void testNegate() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "negate", Number.class);
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#round(Number)
     */
    @Test
    public void testRound() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "round", Number.class);
    }
    
    /**
     * JUnit test of compare.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#compare(Number, Number)
     */
    @Test
    public void testCompare() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "compare", Number.class, Number.class);
    }
    
    /**
     * JUnit test of isEqual.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#isEqual(Number, Number)
     */
    @Test
    public void testIsEqual() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "isEqual", Number.class, Number.class);
    }
    
    /**
     * JUnit test of isZero.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#isZero(Number)
     */
    @Test
    public void testIsZero() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "isZero", Number.class);
    }
    
    /**
     * JUnit test of clean.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#clean(Number)
     */
    @Test
    public void testClean() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "clean", Number.class);
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "getPrecision");
    }
    
    /**
     * JUnit test of getSignificantFigures.
     *
     * @throws Exception When there is an exception.
     * @see ComponentMathHandlerInterface#getSignificantFigures()
     */
    @Test
    public void testGetSignificantFigures() throws Exception {
        TestUtils.assertMethodExists(
                ComponentMathHandlerInterface.class, "getSignificantFigures");
    }
    
}
