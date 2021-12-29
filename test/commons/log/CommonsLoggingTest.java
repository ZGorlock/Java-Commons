/*
 * File:    CommonsLoggingTest.java
 * Package: commons.log
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.log;

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
 * JUnit test of CommonsLogging.
 *
 * @see CommonsLogging
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CommonsLogging.class})
public class CommonsLoggingTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CommonsLoggingTest.class);
    
    
    //Fields
    
    /**
     * The Commons Logger implementation to use for testing.
     */
    private final CommonsLogger commonsLogger = new CommonsLogger() {
        
        @Override
        public boolean logFilesystem() {
            return true;
        }
        
        @Override
        public boolean logClipboard() {
            return true;
        }
        
        @Override
        public boolean logInternet() {
            return true;
        }
        
        @Override
        public boolean showFfmpegProgressBarsByDefault() {
            return true;
        }
        
    };
    
    
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
     * @see CommonsLogging#DEFAULT_LOG_FILESYSTEM
     * @see CommonsLogging#DEFAULT_LOG_CLIPBOARD
     * @see CommonsLogging#DEFAULT_LOG_INTERNET
     * @see CommonsLogging#DEFAULT_SHOW_FFMPEG_PROGRESS_BARS_BY_DEFAULT
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertFalse(CommonsLogging.DEFAULT_LOG_FILESYSTEM);
        Assert.assertFalse(CommonsLogging.DEFAULT_LOG_CLIPBOARD);
        Assert.assertFalse(CommonsLogging.DEFAULT_LOG_INTERNET);
        Assert.assertFalse(CommonsLogging.DEFAULT_SHOW_FFMPEG_PROGRESS_BARS_BY_DEFAULT);
    }
    
    /**
     * JUnit test of logFilesystem.
     *
     * @throws Exception When there is an exception.
     * @see CommonsLogging#logFilesystem()
     */
    @Test
    public void testLogFilesystem() throws Exception {
        CommonsLogging.setCommonsLogger(null);
        Assert.assertFalse(CommonsLogging.DEFAULT_LOG_FILESYSTEM);
        Assert.assertFalse(CommonsLogging.logFilesystem());
        CommonsLogging.setCommonsLogger(commonsLogger);
        Assert.assertTrue(CommonsLogging.logFilesystem());
    }
    
    /**
     * JUnit test of logClipboard.
     *
     * @throws Exception When there is an exception.
     * @see CommonsLogging#logClipboard()
     */
    @Test
    public void testLogClipboard() throws Exception {
        CommonsLogging.setCommonsLogger(null);
        Assert.assertFalse(CommonsLogging.DEFAULT_LOG_CLIPBOARD);
        Assert.assertFalse(CommonsLogging.logClipboard());
        CommonsLogging.setCommonsLogger(commonsLogger);
        Assert.assertTrue(CommonsLogging.logClipboard());
    }
    
    /**
     * JUnit test of logInternet.
     *
     * @throws Exception When there is an exception.
     * @see CommonsLogging#logInternet()
     */
    @Test
    public void testLogInternet() throws Exception {
        CommonsLogging.setCommonsLogger(null);
        Assert.assertFalse(CommonsLogging.DEFAULT_LOG_INTERNET);
        Assert.assertFalse(CommonsLogging.logInternet());
        CommonsLogging.setCommonsLogger(commonsLogger);
        Assert.assertTrue(CommonsLogging.logInternet());
    }
    
    /**
     * JUnit test of getCommonsLogger.
     *
     * @throws Exception When there is an exception.
     * @see CommonsLogging#getCommonsLogger()
     */
    @Test
    public void testGetCommonsLogger() throws Exception {
        CommonsLogging.setCommonsLogger(commonsLogger);
        Assert.assertEquals(commonsLogger, CommonsLogging.getCommonsLogger());
        Assert.assertEquals(commonsLogger, TestUtils.getField(CommonsLogging.class, "commonsLogger"));
        CommonsLogging.setCommonsLogger(null);
        Assert.assertNull(CommonsLogging.getCommonsLogger());
        Assert.assertNull(TestUtils.getField(CommonsLogging.class, "commonsLogger"));
    }
    
    /**
     * JUnit test of setCommonsLogger.
     *
     * @throws Exception When there is an exception.
     * @see CommonsLogging#setCommonsLogger(CommonsLogger)
     */
    @Test
    public void testSetCommonsLogger() throws Exception {
        CommonsLogging.setCommonsLogger(commonsLogger);
        Assert.assertEquals(commonsLogger, CommonsLogging.getCommonsLogger());
        Assert.assertEquals(commonsLogger, TestUtils.getField(CommonsLogging.class, "commonsLogger"));
        CommonsLogging.setCommonsLogger(null);
        Assert.assertNull(CommonsLogging.getCommonsLogger());
        Assert.assertNull(TestUtils.getField(CommonsLogging.class, "commonsLogger"));
    }
    
}
