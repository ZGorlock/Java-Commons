/*
 * File:    MatrixInterfaceTest.java
 * Package: commons.math.component.matrix
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.matrix;

import commons.math.component.vector.VectorInterface;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of MatrixInterface.
 *
 * @see MatrixInterface
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({MatrixInterface.class})
public class MatrixInterfaceTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MatrixInterfaceTest.class);
    
    
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
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of newVector.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#newVector()
     */
    @Test
    public void testNewVector() throws Exception {
    }
    
    /**
     * JUnit test of toIndex.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#toIndex(int, int)
     */
    @Test
    public void testToIndex() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#times(MatrixInterface)
     */
    @Test
    public void testTimes() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#times(VectorInterface)
     */
    @Test
    public void testVectorTimes() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#scale(MatrixInterface)
     */
    @Test
    public void testScale() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minor.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#minor(int, int)
     * @see MatrixInterface#minor(int)
     */
    @Test
    public void testMinor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minors.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#minors()
     */
    @Test
    public void testMinors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cofactorScalar.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#cofactorScalar(int, int)
     * @see MatrixInterface#cofactorScalar(int)
     */
    @Test
    public void testCofactorScalar() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cofactor.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of adjoint.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#adjoint()
     */
    @Test
    public void testAdjoint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#solveSystem(VectorInterface)
     */
    @Test
    public void testSolveSystem() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of transform.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#transform(VectorInterface)
     */
    @Test
    public void testTransform() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of subMatrix.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#subMatrix(int, int, int, int)
     */
    @Test
    public void testSubMatrix() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#dimensionalityToLength(int)
     * @see MatrixInterface#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#lengthToDimensionality(int)
     * @see MatrixInterface#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of prettyPrint.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#prettyPrint()
     */
    @Test
    public void testPrettyPrint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#get(int, int)
     */
    @Test
    public void testGet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#getHeight()
     */
    @Test
    public void testGetHeight() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#set(int, int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#createInstance(int, Class)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#identity(int, Class)
     */
    @Test
    public void testIdentity() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#origin(int, Class)
     */
    @Test
    public void testOrigin() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see MatrixInterface#origin(int, Class)
     */
    @Test
    public void testSignChart() throws Exception {
        //TODO
    }
    
}
