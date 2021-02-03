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
        ConsoleProgressBar sut;
        
        //standard
        sut = new ConsoleProgressBar("Test", 1000, 50, "B", false);
        Assert.assertEquals("Test", sut.getTitle());
        Assert.assertEquals(1000, sut.getTotal());
        Assert.assertEquals(50, sut.getWidth());
        Assert.assertEquals("B", sut.getUnits());
        Assert.assertFalse(sut.getAutoPrint());
        
        //default auto print
        sut = new ConsoleProgressBar("Test2", 5000, 55, "kb");
        Assert.assertEquals("Test2", sut.getTitle());
        Assert.assertEquals(5000, sut.getTotal());
        Assert.assertEquals(55, sut.getWidth());
        Assert.assertEquals("kb", sut.getUnits());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, sut.getAutoPrint());
        
        //default width
        sut = new ConsoleProgressBar("Test3", 51000, "MB");
        Assert.assertEquals("Test3", sut.getTitle());
        Assert.assertEquals(51000, sut.getTotal());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, sut.getWidth());
        Assert.assertEquals("MB", sut.getUnits());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, sut.getAutoPrint());
        
        //no units
        sut = new ConsoleProgressBar("Test4", 581000, 61);
        Assert.assertEquals("Test4", sut.getTitle());
        Assert.assertEquals(581000, sut.getTotal());
        Assert.assertEquals(61, sut.getWidth());
        Assert.assertEquals("", sut.getUnits());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, sut.getAutoPrint());
        
        //default width, no units
        sut = new ConsoleProgressBar("Test5", 1581000);
        Assert.assertEquals("Test5", sut.getTitle());
        Assert.assertEquals(1581000, sut.getTotal());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, sut.getWidth());
        Assert.assertEquals("", sut.getUnits());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, sut.getAutoPrint());
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
