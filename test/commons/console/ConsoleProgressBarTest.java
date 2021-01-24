/*
 * File:    ConsoleProgressBarTest.java
 * Package: commons.console
 * Author:  Zachary Gill
 */

package commons.console;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
 * JUnit test of ConsoleProgressBar.
 *
 * @see ConsoleProgressBar
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({ConsoleProgressBar.class})
public class ConsoleProgressBarTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ConsoleProgressBarTest.class);
    
    
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
     * @see ConsoleProgressBar#DEFAULT_PROGRESS_BAR_WIDTH
     * @see ConsoleProgressBar#DEFAULT_PROGRESS_BAR_AUTO_PRINT
     * @see ConsoleProgressBar#PROGRESS_BAR_MINIMUM_UPDATE_DELAY
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(32, ConsoleProgressBar.DEFAULT_PROGRESS_BAR_WIDTH);
        Assert.assertTrue(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT);
        Assert.assertEquals(TimeUnit.MILLISECONDS.toNanos(200), ConsoleProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#ConsoleProgressBar(String, long, int, String, boolean)
     * @see ConsoleProgressBar#ConsoleProgressBar(String, long, int, String)
     * @see ConsoleProgressBar#ConsoleProgressBar(String, long, String)
     * @see ConsoleProgressBar#ConsoleProgressBar(String, long, int)
     * @see ConsoleProgressBar#ConsoleProgressBar(String, long)
     */
    @Test
    public void testConstructor() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#get()
     */
    @Test
    public void testGet() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of update.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#update(long)
     */
    @Test
    public void testUpdate() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of addOne.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#addOne()
     */
    @Test
    public void testAddOne() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of print.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#print()
     */
    @Test
    public void testPrint() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getRatio.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getRatio()
     */
    @Test
    public void testGetRatio() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getPercentage.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getPercentage()
     */
    @Test
    public void testGetPercentage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getLastSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getLastSpeed()
     */
    @Test
    public void testGetLastSpeed() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getAverageSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getAverageSpeed()
     */
    @Test
    public void testGetAverageSpeed() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getTimeRemaining.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getTimeRemaining()
     */
    @Test
    public void testGetTimeRemaining() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isComplete.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#isComplete()
     */
    @Test
    public void testIsComplete() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of complete.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#complete(boolean, String)
     * @see ConsoleProgressBar#complete(boolean)
     * @see ConsoleProgressBar#complete()
     */
    @Test
    public void testComplete() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getBarString.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getBarString()
     */
    @Test
    public void testGetBarString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getRatioString.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getRatioString()
     */
    @Test
    public void testGetRatioString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getTimeRemainingString.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getTimeRemainingString()
     */
    @Test
    public void testGetTimeRemainingString() throws Exception {
        //TODO
    }
    
}
