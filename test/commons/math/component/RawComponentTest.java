/*
 * File:    RawComponentTest.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import java.util.List;

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
 * JUnit test of RawComponent.
 *
 * @see RawComponent
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({RawComponent.class})
public class RawComponentTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RawComponentTest.class);
    
    
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
     * @see RawComponent#RAW_PRECISION
     * @see RawComponent#PRECISION
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(0.000000000001, RawComponent.RAW_PRECISION.doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.000000000001, RawComponent.PRECISION.doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#RawComponent()
     */
    @Test
    public void testConstructors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#toString()
     */
    @Test
    public void testToString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#equals(Object)
     */
    @Test
    public void testEquals() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testRawComponentTypeEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#cloned()
     */
    @Test
    public void testCloned() throws Exception {
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#distance(RawComponent)
     */
    @Test
    public void testDistance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#midpoint(RawComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#average(List)
     * @see RawComponent#average(RawComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#sum()
     */
    @Test
    public void testSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#plus(RawComponent)
     */
    @Test
    public void testPlus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#minus(RawComponent)
     */
    @Test
    public void testMinus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#times(RawComponent)
     */
    @Test
    public void testTimes() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#dividedBy(RawComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#round()
     */
    @Test
    public void testRound() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#copy(RawComponent)
     */
    @Test
    public void testCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#copyMeta(RawComponent)
     */
    @SuppressWarnings("JavadocReference")
    @Test
    public void testCopyMeta() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#dimensionalityToLength(int)
     * @see RawComponent#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#lengthToDimensionality(int)
     * @see RawComponent#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of calculateDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPrimitiveRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveRawComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getRawComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getComponentClass()
     */
    @Test
    public void testGetRawComponentClass() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getType()
     */
    @Test
    public void testGetType() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getErrorHandler()
     */
    @Test
    public void testetErrorHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getName()
     */
    @Test
    public void testGetName() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        //TODO
    }
    
}
