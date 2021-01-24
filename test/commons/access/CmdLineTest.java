/*
 * File:    CmdLineTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 */

package commons.access;

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
 * JUnit test of CmdLine.
 *
 * @see CmdLine
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({CmdLine.class})
public class CmdLineTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CmdLineTest.class);
    
    
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
     * JUnit test of executeCmd.
     *
     * @throws Exception When there is an exception.
     * @see CmdLine#executeCmd(String, boolean)
     * @see CmdLine#executeCmd(String)
     */
    @Test
    public void testExecuteCmd() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of executeCmdAsThread.
     *
     * @throws Exception When there is an exception.
     * @see CmdLine#executeCmdAsThread(String)
     */
    @Test
    public void testExecuteCmdAsThread() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of buildProcess.
     *
     * @throws Exception When there is an exception.
     * @see CmdLine#buildProcess(String, boolean)
     */
    @Test
    public void testBuildProcess() throws Exception {
        //TODO
    }
    
}
