/*
 * File:    BigVectorTest.java
 * Package: commons.math.vector
 * Author:  Zachary Gill
 */

package commons.math.vector;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

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
 * JUnit test of BigVector.
 *
 * @see BigVector
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigVector.class})
public class BigVectorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigVectorTest.class);
    
    
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
     * @see BigVector#DEFAULT_PRECISION
     * @see BigVector#DEFAULT_ROUNDING_MODE
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(32, BigVector.DEFAULT_PRECISION);
        Assert.assertEquals(RoundingMode.HALF_UP, BigVector.DEFAULT_ROUNDING_MODE);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#BigVector(BigDecimal...)
     * @see BigVector#BigVector(List)
     * @see BigVector#BigVector(Vector)
     * @see BigVector#BigVector(BigVector)
     * @see BigVector#BigVector(BigVector, BigDecimal...)
     */
    @Test
    public void testConstructor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#toString()
     */
    @Test
    public void testToString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionsEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dimensionalityEqual(BigVector)
     */
    @Test
    public void testDimensionsEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#equals(BigVector)
     */
    @Test
    public void testEquals() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#clone()
     */
    @Test
    public void testClone() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of justify.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#justify()
     */
    @Test
    public void testJustify() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#distance(BigVector)
     */
    @Test
    public void testDistance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#midpoint(BigVector)
     */
    @Test
    public void testMidpoint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#average(List)
     * @see BigVector#average(BigVector...)
     */
    @Test
    public void testAverage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dot(BigVector)
     */
    @Test
    public void testDot() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#sum()
     */
    @Test
    public void testSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of movePointLeft.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#movePointLeft(int)
     */
    @Test
    public void testMovePointLeft() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of movePointRight.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#movePointRight(int)
     */
    @Test
    public void testMovePointRight() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of stripTrailingZeros.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#stripTrailingZeros()
     */
    @Test
    public void testStripTrailingZeros() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#plus(BigVector)
     */
    @Test
    public void testPlus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#minus(BigVector)
     */
    @Test
    public void testMinus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#times(BigVector)
     */
    @Test
    public void testTimes() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#scale(BigDecimal)
     * @see BigVector#scale(double)
     */
    @Test
    public void testScale() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dividedBy(BigVector)
     */
    @Test
    public void testDividedBy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#round()
     */
    @Test
    public void testRound() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getX()
     */
    @Test
    public void testGetX() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getY()
     */
    @Test
    public void testGetY() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getW()
     */
    @Test
    public void testGetW() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getMathContext()
     */
    @Test
    public void testGetMathContext() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getJustificationVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getJustificationVector()
     */
    @Test
    public void testGetJustificationVector() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setX(BigDecimal)
     */
    @Test
    public void testSetX() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setY(BigDecimal)
     */
    @Test
    public void testSetY() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setZ(BigDecimal)
     */
    @Test
    public void testSetZ() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setW(BigDecimal)
     */
    @Test
    public void testSetW() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#set(int, BigDecimal)
     */
    @Test
    public void testSet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setMathContext(MathContext)
     */
    @Test
    public void testSetMathContext() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setJustificationVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setJustificationVector(BigVector)
     */
    @Test
    public void testSetJustificationVector() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copyVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#copyVector(BigVector, BigVector)
     */
    @Test
    public void testCopyVector() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of averageVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#averageVector(List)
     * @see BigVector#averageVector(BigVector...)
     */
    @Test
    public void testAverageVector() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of calculateMinMax.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#calculateMinMax(List)
     * @see BigVector#calculateMinMax(BigVector...)
     */
    @Test
    public void testCalculateMinMax() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dimensionalityNotEqualErrorMessage(BigVector, BigVector)
     */
    @Test
    public void testDimensionalityNotEqualErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityMinimumNotMetErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dimensionalityMinimumNotMetErrorMessage(BigVector, int)
     */
    @Test
    public void testDimensionalityMinimumNotMetErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentIndexOutOfRangeError.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#componentIndexOutOfRangeError(BigVector, int)
     */
    @Test
    public void testComponentIndexOutOfRangeError() throws Exception {
        //TODO
    }
    
}
