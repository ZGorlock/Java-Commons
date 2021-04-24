/*
 * File:    RawComponentMathHandlerTest.java
 * Package: commons.math.component.handler.error
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.math;

import commons.math.component.RawComponent;
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
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({RawComponentMathHandler.class})
public class RawComponentMathHandlerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RawComponentMathHandlerTest.class);
    
    
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
     * @see RawComponentMathHandler#PRECISION
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(RawComponent.RAW_PRECISION, RawComponentMathHandler.PRECISION);
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
    }
    
    /**
     * JUnit test of zero.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#zero()
     */
    @Test
    public void testZero() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of one.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#one()
     */
    @Test
    public void testOne() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of negativeOne.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#negativeOne()
     */
    @Test
    public void testNegativeOne() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of valueOf.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#valueOf(Number)
     */
    @Test
    public void testValueOf() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of array.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#array(int)
     */
    @Test
    public void testArray() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#add(Number, Number)
     */
    @Test
    public void testAdd() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of subtract.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#subtract(Number, Number)
     */
    @Test
    public void testSubtract() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#multiply(Number, Number)
     */
    @Test
    public void testMultiply() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of divide.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#divide(Number, Number)
     */
    @Test
    public void testDivide() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of power.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#power(Number, Number)
     */
    @Test
    public void testPower() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#root(Number, Number)
     */
    @Test
    public void testRoot() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of sqrt.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#sqrt(Number)
     */
    @Test
    public void testSqrt() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of reciprocal.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#reciprocal(Number)
     */
    @Test
    public void testReciprocal() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of abs.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#abs(Number)
     */
    @Test
    public void testAbs() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of negate.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#negate(Number)
     */
    @Test
    public void testNegate() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#round(Number)
     */
    @Test
    public void testRound() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of compare.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#compare(Number, Number)
     */
    @Test
    public void testCompare() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#isEqual(Number, Number)
     */
    @Test
    public void testIsEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isZero.
     *
     * @throws Exception When there is an exception.
     * @see RawComponentMathHandler#isZero(Number)
     */
    @Test
    public void testIsZero() throws Exception {
        //TODO
    }
    
}
