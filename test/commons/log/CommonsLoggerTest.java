/*
 * File:    CommonsLoggerTest.java
 * Package: commons.log
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.log;

import commons.test.TestUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of CommonsLogger.
 *
 * @see CommonsLogger
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CommonsLogger.class})
public class CommonsLoggerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CommonsLoggerTest.class);
    
    
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
     * JUnit test of logFilesystem.
     *
     * @throws Exception When there is an exception.
     * @see CommonsLogger#logFilesystem()
     */
    @Test
    public void testLogFilesystem() throws Exception {
        TestUtils.assertMethodExists(
                CommonsLogger.class, "logFilesystem");
    }
    
    /**
     * JUnit test of logClipboard.
     *
     * @throws Exception When there is an exception.
     * @see CommonsLogger#logClipboard()
     */
    @Test
    public void testLogClipboard() throws Exception {
        TestUtils.assertMethodExists(
                CommonsLogger.class, "logClipboard");
    }
    
    /**
     * JUnit test of logInternet.
     *
     * @throws Exception When there is an exception.
     * @see CommonsLogger#logInternet()
     */
    @Test
    public void testLogInternet() throws Exception {
        TestUtils.assertMethodExists(
                CommonsLogger.class, "logInternet");
    }
    
    /**
     * JUnit test of showFfmpegProgressBarsByDefault.
     *
     * @throws Exception When there is an exception.
     * @see CommonsLogger#showFfmpegProgressBarsByDefault()
     */
    @Test
    public void testShowFfmpegProgressBarsByDefault() throws Exception {
        TestUtils.assertMethodExists(
                CommonsLogger.class, "showFfmpegProgressBarsByDefault");
    }
    
}
