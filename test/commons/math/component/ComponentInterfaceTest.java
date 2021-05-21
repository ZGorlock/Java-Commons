/*
 * File:    ComponentInterfaceTest.java
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
 * JUnit test of ComponentInterface.
 *
 * @see ComponentInterface
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ComponentInterface.class})
public class ComponentInterfaceTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ComponentInterfaceTest.class);
    
    
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
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#toString()
     */
    @Test
    public void testToString() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "toString");
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#equals(Object)
     */
    @Test
    public void testEquals() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "equals", Object.class);
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "dimensionalityEqual", ComponentInterface.class);
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "lengthEqual", ComponentInterface.class);
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "componentTypeEqual", ComponentInterface.class);
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "cloned");
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "emptyCopy");
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "createNewInstance", int.class);
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "reverse");
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#distance(ComponentInterface)
     */
    @Test
    public void testDistance() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "distance", ComponentInterface.class);
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#midpoint(ComponentInterface)
     */
    @Test
    public void testMidpoint() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "midpoint", ComponentInterface.class);
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#average(List)
     * @see ComponentInterface#average(ComponentInterface...)
     */
    @Test
    public void testAverage() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "average", List.class);
        TestUtils.assertMethodExists(
                ComponentInterface.class, "average", ComponentInterface[].class);
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#sum()
     */
    @Test
    public void testSum() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "sum");
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "squareSum");
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#plus(ComponentInterface)
     */
    @Test
    public void testPlus() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "plus", ComponentInterface.class);
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#minus(ComponentInterface)
     */
    @Test
    public void testMinus() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "minus", ComponentInterface.class);
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#times(ComponentInterface)
     */
    @Test
    public void testTimes() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "times", ComponentInterface.class);
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "scale", Number.class);
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#dividedBy(ComponentInterface)
     */
    @Test
    public void testDividedBy() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "dividedBy", ComponentInterface.class);
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#round()
     */
    @Test
    public void testRound() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "round");
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#copy(ComponentInterface)
     */
    @Test
    public void testCopy() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "copy", ComponentInterface.class);
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#copyMeta(ComponentInterface)
     */
    @Test
    public void testCopyMeta() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "copyMeta", ComponentInterface.class);
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "redim", int.class);
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#dimensionalityToLength(int)
     * @see ComponentInterface#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "dimensionalityToLength", int.class);
        TestUtils.assertMethodExists(
                ComponentInterface.class, "dimensionalityToLength");
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#lengthToDimensionality(int)
     * @see ComponentInterface#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "lengthToDimensionality", int.class);
        TestUtils.assertMethodExists(
                ComponentInterface.class, "lengthToDimensionality");
    }
    
    /**
     * JUnit test of calculateDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "calculateDimensionality");
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getRawComponents");
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getComponents");
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getRaw(int)
     */
    @Test
    public void testGetRaw() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getRaw", int.class);
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#get(int)
     */
    @Test
    public void testGet() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "get", int.class);
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getDimensionality");
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getLength");
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getComponentClass");
        
        //default
        Assert.assertEquals(ComponentInterface.class,
                TestUtils.invokeInterfaceDefaultMethod(ComponentInterface.class, "getComponentClass"));
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getType()
     */
    @Test
    public void testGetType() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getType");
        
        //default
        Assert.assertEquals(Number.class,
                TestUtils.invokeInterfaceDefaultMethod(ComponentInterface.class, "getType"));
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getHandler");
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getErrorHandler");
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getName()
     */
    @Test
    public void testGetName() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getName");
        
        //default
        Assert.assertEquals("Component Interface",
                TestUtils.invokeInterfaceDefaultMethod(ComponentInterface.class, "getName"));
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getNamePlural()
     */
    @SuppressWarnings({"rawtypes", "SuspiciousInvocationHandlerImplementation"})
    @Test
    public void testGetNamePlural() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getNamePlural");
        
        //default
        Assert.assertEquals("null" + 's',
                TestUtils.invokeInterfaceDefaultMethod(ComponentInterface.class, "getNamePlural"));
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "getPrecision");
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "isResizeable");
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "setComponents", Number[].class);
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see ComponentInterface#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        TestUtils.assertMethodExists(
                ComponentInterface.class, "set", int.class, Number.class);
    }
    
}
