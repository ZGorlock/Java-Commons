/*
 * File:    BigComponentMathHandlerTest.java
 * Package: commons.math.component.handler.error
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.math;

import java.math.BigDecimal;

import commons.math.component.BigComponent;
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
 * JUnit test of BigComponentMathHandler.
 *
 * @see BigComponentMathHandler
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigComponentMathHandler.class})
public class BigComponentMathHandlerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigComponentMathHandlerTest.class);
    
    
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
     * @see BigComponentMathHandler#PRECISION
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(BigComponent.BIG_PRECISION, BigComponentMathHandler.PRECISION);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#BigComponentMathHandler()
     */
    @Test
    public void testConstructor() throws Exception {
        BigComponentMathHandler sut = new BigComponentMathHandler();
        Assert.assertNotNull(sut);
    }
    
    /**
     * JUnit test of zero.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#zero()
     */
    @Test
    public void testZero() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of one.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#one()
     */
    @Test
    public void testOne() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of negativeOne.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#negativeOne()
     */
    @Test
    public void testNegativeOne() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of valueOf.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#valueOf(Number)
     */
    @Test
    public void testValueOf() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of array.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#array(int)
     */
    @Test
    public void testArray() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#add(BigDecimal, BigDecimal)
     */
    @Test
    public void testAdd() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of subtract.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#subtract(BigDecimal, BigDecimal)
     */
    @Test
    public void testSubtract() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#multiply(BigDecimal, BigDecimal)
     */
    @Test
    public void testMultiply() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of divide.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#divide(BigDecimal, BigDecimal)
     */
    @Test
    public void testDivide() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of power.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#power(BigDecimal, BigDecimal)
     */
    @Test
    public void testPower() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#root(BigDecimal, BigDecimal)
     */
    @Test
    public void testRoot() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of sqrt.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#sqrt(BigDecimal)
     */
    @Test
    public void testSqrt() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of reciprocal.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#reciprocal(BigDecimal)
     */
    @Test
    public void testReciprocal() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of abs.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#abs(BigDecimal)
     */
    @Test
    public void testAbs() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of negate.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#negate(BigDecimal)
     */
    @Test
    public void testNegate() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#round(BigDecimal)
     */
    @Test
    public void testRound() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of compare.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#compare(BigDecimal, BigDecimal)
     */
    @Test
    public void testCompare() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#isEqual(BigDecimal, BigDecimal)
     */
    @Test
    public void testIsEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isZero.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentMathHandler#isZero(BigDecimal)
     */
    @Test
    public void testIsZero() throws Exception {
        //TODO
    }
    
}
