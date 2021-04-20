/*
 * File:    BigComponentInterfaceTest.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import java.math.MathContext;

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
 * JUnit test of BigComponentInterface.
 *
 * @see BigComponentInterface
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigComponentInterface.class})
public class BigComponentInterfaceTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigComponentInterfaceTest.class);
    
    
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
     * JUnit test of movePointLeft.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#movePointLeft(int)
     */
    @Test
    public void testMovePointLeft() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of movePointRight.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#movePointRight(int)
     */
    @Test
    public void testMovePointRight() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of stripTrailingZeros.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#stripTrailingZeros()
     */
    @Test
    public void testStripTrailingZeros() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#getMathContext()
     */
    @Test
    public void testGetMathContext() throws Exception {
    }
    
    /**
     * JUnit test of setMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#setMathContext(MathContext)
     */
    @Test
    public void testSetMathContext() throws Exception {
    }
    
}
