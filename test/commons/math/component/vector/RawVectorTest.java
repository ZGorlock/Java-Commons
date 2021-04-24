/*
 * File:    RawVectorTest.java
 * Package: commons.math.component.vector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.vector;

import java.util.List;

import commons.math.component.BaseComponent;
import commons.math.component.Component;
import commons.math.component.ComponentInterface;
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
 * JUnit test of RawVector.
 *
 * @see RawVector
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({RawVector.class})
public class RawVectorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RawVectorTest.class);
    
    
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
     * @see RawVector#RAW_PRECISION
     * @see RawVector#PRECISION
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(0.000000000001, RawVector.RAW_PRECISION.doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(0.000000000001, RawVector.PRECISION.doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#RawVector(Number...)
     * @see RawVector#RawVector(List)
     * @see RawVector#RawVector(RawVector)
     * @see RawVector#RawVector(Vector)
     * @see RawVector#RawVector(RawVector, Number...)
     * @see RawVector#RawVector(Vector, Number...)
     * @see RawVector#RawVector(int)
     * @see RawVector#RawVector()
     */
    @Test
    public void testConstructors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of vectorString.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#vectorString()
     */
    @Test
    public void testVectorString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#toString()
     */
    @Test
    public void testToString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#equals(Object)
     */
    @Test
    public void testEquals() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#average(List)
     * @see RawVector#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#sum()
     */
    @Test
    public void testSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#times(BaseComponent)
     */
    @Test
    public void testTimes() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#round()
     */
    @Test
    public void testRound() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#dot(VectorInterface)
     */
    @Test
    public void testDot() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#copyMeta(Component)
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
     * @see RawVector#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of subRawVector.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#subVector(int, int)
     * @see RawVector#subVector(int)
     */
    @Test
    public void testSubRawVector() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#dimensionalityToLength(int)
     * @see RawVector#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#lengthToDimensionality(int)
     * @see RawVector#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of calculateDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getX()
     */
    @Test
    public void testGetX() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getY()
     */
    @Test
    public void testGetY() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getW()
     */
    @Test
    public void testGetW() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getType()
     */
    @Test
    public void testGetType() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getErrorHandler()
     */
    @Test
    public void testetErrorHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getName()
     */
    @Test
    public void testGetName() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setX(Number)
     */
    @Test
    public void testSetX() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setY(Number)
     */
    @Test
    public void testSetY() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setZ(Number)
     */
    @Test
    public void testSetZ() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#setW(Number)
     */
    @Test
    public void testSetW() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#createInstance(int)
     * @see RawVector#createInstance(int, Class)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#identity(int)
     * @see RawVector#identity(int, Class)
     */
    @Test
    public void testIdentity() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see RawVector#origin(int)
     * @see RawVector#origin(int, Class)
     */
    @Test
    public void testOrigin() throws Exception {
        //TODO
    }
    
}
