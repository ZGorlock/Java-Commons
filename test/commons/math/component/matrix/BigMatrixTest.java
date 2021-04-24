/*
 * File:    BigMatrixTest.java
 * Package: commons.math.component.matrix
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.matrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import commons.math.BigMathUtility;
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
 * JUnit test of BigMatrix.
 *
 * @see BigMatrix
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigMatrix.class})
public class BigMatrixTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigMatrixTest.class);
    
    
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
     * @see BigMatrix#BIG_PRECISION
     * @see BigMatrix#PRECISION
     * @see BigMatrix#DEFAULT_MATH_PRECISION
     * @see BigMatrix#DEFAULT_ROUNDING_MODE
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(new BigDecimal("0.000000000000000000000000000000000001"), BigMatrix.BIG_PRECISION);
        Assert.assertEquals(0.000000000001, BigMatrix.PRECISION.doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(BigMathUtility.DEFAULT_MATH_PRECISION / 2, BigMatrix.DEFAULT_MATH_PRECISION);
        Assert.assertEquals(BigMathUtility.DEFAULT_ROUNDING_MODE, BigMatrix.DEFAULT_ROUNDING_MODE);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#BigMatrix(BigDecimal...)
     * @see BigMatrix#BigMatrix(double...)
     * @see BigMatrix#BigMatrix(String...)
     * @see BigMatrix#BigMatrix(List)
     * @see BigMatrix#BigMatrix(BigMatrix)
     * @see BigMatrix#BigMatrix(Matrix)
     * @see BigMatrix#BigMatrix(int)
     * @see BigMatrix#BigMatrix()
     */
    @Test
    public void testConstructors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of matrixString.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#matrixString()
     */
    @Test
    public void testMatrixString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#toString()
     */
    @Test
    public void testToString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#equals(Object)
     */
    @Test
    public void testEquals() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#average(List)
     * @see BigMatrix#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#sum()
     */
    @Test
    public void testSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#times(BigMatrix)
     */
    @Test
    public void testTimes() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#scale(MatrixInterface)
     */
    @Test
    public void testBigMatrixScale() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#round()
     */
    @Test
    public void testRound() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of movePointLeft.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#movePointLeft(int)
     */
    @Test
    public void testMovePointLeft() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of movePointRight.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#movePointRight(int)
     */
    @Test
    public void testMovePointRight() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of stripTrailingZeros.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#stripTrailingZeros()
     */
    @Test
    public void testStripTrailingZeros() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#minor(int, int)
     * @see BigMatrix#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minors.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#minors()
     */
    @Test
    public void testMinors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cofactorScalar.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#cofactorScalar(int, int)
     * @see BigMatrix#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cofactor.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of adjoint.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of transform.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#copyMeta(Component)
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
     * @see BigMatrix#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of subBigMatrix.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#subMatrix(int, int, int, int)
     */
    @Test
    public void testSubBigMatrix() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#dimensionalityToLength(int)
     * @see BigMatrix#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#lengthToDimensionality(int)
     * @see BigMatrix#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of calculateDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of prettyPrint.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#get(int)
     * @see BigMatrix#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getMathContext()
     */
    @Test
    public void testGetMathContext() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getType()
     */
    @Test
    public void testGetType() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getErrorHandler()
     */
    @Test
    public void testetErrorHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getName()
     */
    @Test
    public void testGetName() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#set(int, Number)
     * @see BigMatrix#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#setMathContext(MathContext)
     */
    @Test
    public void testSetMathContext() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#createInstance(int)
     * @see BigMatrix#createInstance(int, Class)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#identity(int)
     * @see BigMatrix#identity(int, Class)
     */
    @Test
    public void testIdentity() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#origin(int)
     * @see BigMatrix#origin(int, Class)
     */
    @Test
    public void testOrigin() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see BigMatrix#origin(int)
     * @see BigMatrix#origin(int, Class)
     */
    @Test
    public void testSignChart() throws Exception {
        //TODO
    }
    
}
