/*
 * File:    MatrixTest.java
 * Package: commons.math.matrix
 * Author:  Zachary Gill
 */

package commons.math.matrix;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Matrix.
 *
 * @see Matrix
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({Matrix.class})
public class MatrixTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MatrixTest.class);
    
    
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
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#Matrix(double[])
     */
    @Test
    public void testConstructor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#toString()
     */
    @Test
    public void testToString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#equals(Object)
     */
    @Test
    public void testEquals() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#dimensionalityEqual(Matrix)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getValues.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#getValues()
     */
    @Test
    public void testGetValues() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#set(int, double)
     */
    @Test
    public void testSet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#dimensionalityNotEqualErrorMessage(Matrix, Matrix)
     */
    @Test
    public void testDimensionalityNotEqualErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of valueIndexOutOfRangeError.
     *
     * @throws Exception When there is an exception.
     * @see Matrix#valueIndexOutOfRangeError(Matrix, int)
     */
    @Test
    public void testValueIndexOutOfRangeError() throws Exception {
        //TODO
    }
    
}
