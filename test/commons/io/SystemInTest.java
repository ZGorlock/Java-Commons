/*
 * File:    SystemInTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

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
 * JUnit test of SystemIn.
 *
 * @see SystemIn
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({SystemIn.class})
public class SystemInTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SystemInTest.class);
    
    
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
     * JUnit test of startScanner.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#startScanner()
     */
    @Test
    public void testStartScanner() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of interruptScanner.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#interruptScanner()
     */
    @Test
    public void testInterruptScanner() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of nextLine.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#nextLine(Class)
     * @see SystemIn#nextLine(Object)
     */
    @Test
    public void testNextLine() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPassword.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#getPassword(Class)
     * @see SystemIn#getPassword(Object)
     */
    @Test
    public void testGetPassword() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getBuffer.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#getBuffer(Class)
     * @see SystemIn#getBuffer(Object)
     */
    @Test
    public void testGetBuffer() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of owns.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#owns(Class)
     * @see SystemIn#owns(Object)
     */
    @Test
    public void testOwns() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of own.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#own(Class)
     * @see SystemIn#own(Object)
     */
    @Test
    public void testOwn() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of defaultOwn.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#defaultOwn(Class)
     * @see SystemIn#defaultOwn(Object)
     */
    @Test
    public void testDefaultOwn() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of relinquish.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#relinquish(Class)
     * @see SystemIn#relinquish(Object)
     */
    @Test
    public void testRelinquish() throws Exception {
        //TODO
    }
    
}
