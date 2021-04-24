/*
 * File:    RawMatrixTest.java
 * Package: commons.math.component.matrix
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.matrix;

import java.util.List;

import commons.math.component.BaseComponent;
import commons.math.component.Component;
import commons.math.component.ComponentInterface;
import commons.math.component.vector.VectorInterface;
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
 * JUnit test of RawMatrix.
 *
 * @see RawMatrix
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({RawMatrix.class})
public class RawMatrixTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RawMatrixTest.class);
    
    
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
     * @see RawMatrix#RAW_PRECISION
     * @see RawMatrix#PRECISION
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(0.000000000001, RawMatrix.RAW_PRECISION.floatValue(), TestUtils.DELTA);
        Assert.assertEquals(0.000000000001, RawMatrix.PRECISION.doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#RawMatrix(Number...)
     * @see RawMatrix#RawMatrix(List)
     * @see RawMatrix#RawMatrix(RawMatrix)
     * @see RawMatrix#RawMatrix(Matrix)
     * @see RawMatrix#RawMatrix(int)
     * @see RawMatrix#RawMatrix()
     */
    @Test
    public void testConstructors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of matrixString.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#matrixString()
     */
    @Test
    public void testMatrixString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#toString()
     */
    @Test
    public void testToString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#equals(Object)
     */
    @Test
    public void testEquals() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#average(List)
     * @see RawMatrix#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#sum()
     */
    @Test
    public void testSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#times(RawMatrix)
     */
    @Test
    public void testTimes() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#scale(MatrixInterface)
     */
    @Test
    public void testRawMatrixScale() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#round()
     */
    @Test
    public void testRound() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#minor(int, int)
     * @see RawMatrix#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minors.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#minors()
     */
    @Test
    public void testMinors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cofactorScalar.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#cofactorScalar(int, int)
     * @see RawMatrix#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cofactor.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of adjoint.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of transform.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#copyMeta(Component)
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
     * @see RawMatrix#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of subRawMatrix.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#subMatrix(int, int, int, int)
     */
    @Test
    public void testSubRawMatrix() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#dimensionalityToLength(int)
     * @see RawMatrix#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#lengthToDimensionality(int)
     * @see RawMatrix#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of calculateDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of prettyPrint.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#get(int)
     * @see RawMatrix#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getType()
     */
    @Test
    public void testGetType() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getErrorHandler()
     */
    @Test
    public void testetErrorHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getName()
     */
    @Test
    public void testGetName() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#set(int, Number)
     * @see RawMatrix#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#createInstance(int)
     * @see RawMatrix#createInstance(int, Class)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#identity(int)
     * @see RawMatrix#identity(int, Class)
     */
    @Test
    public void testIdentity() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#origin(int)
     * @see RawMatrix#origin(int, Class)
     */
    @Test
    public void testOrigin() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see RawMatrix#origin(int)
     * @see RawMatrix#origin(int, Class)
     */
    @Test
    public void testSignChart() throws Exception {
        //TODO
    }
    
}
