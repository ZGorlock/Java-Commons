/*
 * File:    Matrix3Test.java
 * Package: commons.math.matrix
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.matrix;

import commons.math.vector.Vector;
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
 * JUnit test of Matrix3.
 *
 * @see Matrix3
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Matrix3.class})
public class Matrix3Test {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Matrix3Test.class);
    
    
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
     * @see Matrix3#DIMENSIONALITY
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(3, Matrix3.DIMENSIONALITY);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#Matrix3(double[])
     */
    @Test
    public void testConstructor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#multiply(Matrix3)
     * @see Matrix3#multiply(Vector)
     */
    @Test
    public void testMultiply() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#scale(Matrix3)
     * @see Matrix3#scale(double)
     */
    @Test
    public void testScale() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of determinant.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#determinant()
     */
    @Test
    public void testDeterminant() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of minors.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#minors()
     */
    @Test
    public void testMinors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of transpose.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#transpose()
     */
    @Test
    public void testTranspose() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cofactor.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#cofactor()
     */
    @Test
    public void testCofactor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of solveSystem.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#solveSystem(Vector)
     */
    @Test
    public void testSolveSystem() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of inverse.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#inverse()
     */
    @Test
    public void testInverse() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of subMatrix2.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#subMatrix2(int, int, int, int)
     */
    @Test
    public void testSubMatrix2() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Matrix3#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //TODO
    }
    
}
