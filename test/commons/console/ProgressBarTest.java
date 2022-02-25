/*
 * File:    ProgressBarTest.java
 * Package: commons.console
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.console;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import commons.object.string.StringUtility;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ProgressBar.
 *
 * @see ProgressBar
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ProgressBar.class, System.class})
public class ProgressBarTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProgressBarTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    ProgressBar progressBar;
    
    
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
    @Before
    public void setup() throws Exception {
        progressBar = Mockito.spy(ProgressBar.class);
        TestUtils.setFieldValue(progressBar, "title", "Test Bar");
        TestUtils.setFieldValue(progressBar, "total", 10000L);
        TestUtils.setFieldValue(progressBar, "width", 20);
        TestUtils.setFieldValue(progressBar, "units", "B");
        TestUtils.setFieldValue(progressBar, "autoPrint", false);
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "rollingProgress", new ArrayList<>());
        TestUtils.setFieldValue(progressBar, "rollingUpdate", new ArrayList<>());
        TestUtils.setFieldValue(progressBar, "showPercentage", ProgressBar.DEFAULT_SHOW_PERCENTAGE);
        TestUtils.setFieldValue(progressBar, "showBar", ProgressBar.DEFAULT_SHOW_BAR);
        TestUtils.setFieldValue(progressBar, "showRatio", ProgressBar.DEFAULT_SHOW_RATIO);
        TestUtils.setFieldValue(progressBar, "showSpeed", ProgressBar.DEFAULT_SHOW_SPEED);
        TestUtils.setFieldValue(progressBar, "showTimeRemaining", ProgressBar.DEFAULT_SHOW_TIME_REMAINING);
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
     * @see ProgressBar#DEFAULT_PROGRESS_BAR_WIDTH
     * @see ProgressBar#DEFAULT_PROGRESS_BAR_AUTO_PRINT
     * @see ProgressBar#PROGRESS_BAR_MINIMUM_UPDATE_DELAY
     * @see ProgressBar#ROLLING_AVERAGE_UPDATE_COUNT
     * @see ProgressBar#DEFAULT_SHOW_PERCENTAGE
     * @see ProgressBar#DEFAULT_SHOW_BAR
     * @see ProgressBar#DEFAULT_SHOW_RATIO
     * @see ProgressBar#DEFAULT_SHOW_SPEED
     * @see ProgressBar#DEFAULT_SHOW_TIME_REMAINING
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(32, ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH);
        Assert.assertTrue(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT);
        Assert.assertEquals(200, ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY);
        Assert.assertEquals(5, ProgressBar.ROLLING_AVERAGE_UPDATE_COUNT);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_PERCENTAGE);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_BAR);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_RATIO);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_SPEED);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_TIME_REMAINING);
        Assert.assertEquals(Console.ConsoleEffect.BLACK.apply(" "), ProgressBar.ENDCAP);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#ProgressBar(String, long, int, String, boolean)
     * @see ProgressBar#ProgressBar(String, long, int, String)
     * @see ProgressBar#ProgressBar(String, long, String)
     * @see ProgressBar#ProgressBar(String, long, int)
     * @see ProgressBar#ProgressBar(String, long)
     */
    @Test
    public void testConstructors() throws Exception {
        //standard
        progressBar = new ProgressBar("Test", 1000, 50, "B", false);
        Assert.assertEquals("Test", progressBar.getTitle());
        Assert.assertEquals(1000, progressBar.getTotal());
        Assert.assertEquals(50, progressBar.getWidth());
        Assert.assertEquals("B", progressBar.getUnits());
        Assert.assertFalse(progressBar.getAutoPrint());
        
        //default auto print
        progressBar = new ProgressBar("Test2", 5000, 55, "kb");
        Assert.assertEquals("Test2", progressBar.getTitle());
        Assert.assertEquals(5000, progressBar.getTotal());
        Assert.assertEquals(55, progressBar.getWidth());
        Assert.assertEquals("kb", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        
        //default width
        progressBar = new ProgressBar("Test3", 51000, "MB");
        Assert.assertEquals("Test3", progressBar.getTitle());
        Assert.assertEquals(51000, progressBar.getTotal());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("MB", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        
        //no units
        progressBar = new ProgressBar("Test4", 581000, 61);
        Assert.assertEquals("Test4", progressBar.getTitle());
        Assert.assertEquals(581000, progressBar.getTotal());
        Assert.assertEquals(61, progressBar.getWidth());
        Assert.assertEquals("", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        
        //default width, no units
        progressBar = new ProgressBar("Test5", 1581000);
        Assert.assertEquals("Test5", progressBar.getTitle());
        Assert.assertEquals(1581000, progressBar.getTotal());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#get()
     */
    @Test
    public void testGet() throws Exception {
        Mockito.when(progressBar.getPercentageString()).thenReturn(" 51%");
        Mockito.when(progressBar.getBarString()).thenReturn("[==========>         ]");
        Mockito.when(progressBar.getRatioString()).thenReturn(" 5100B/10000B");
        Mockito.when(progressBar.getSpeedString()).thenReturn("at 5.2B/s");
        Mockito.when(progressBar.getTimeRemainingString()).thenReturn("ETA: 00:15:43");
        String expected;
        
        //initial
        
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        expected = "";
        Assert.assertEquals(expected, progressBar.get());
        
        //standard
        
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(expected, progressBar.get());
        
        //no update
        
        Mockito.when(progressBar.getPercentageString()).thenReturn(" 52%");
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(expected, progressBar.get());
        Mockito.when(progressBar.getPercentageString()).thenReturn(" 51%");
        
        //partial
        
        progressBar.setShowPercentage(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = "[==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(expected, progressBar.get());
        progressBar.setShowPercentage(true);
        
        progressBar.setShowBar(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51%  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(expected, progressBar.get());
        progressBar.setShowBar(true);
        
        progressBar.setShowRatio(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ] at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(expected, progressBar.get());
        progressBar.setShowRatio(true);
        
        progressBar.setShowSpeed(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B - ETA: 00:15:43";
        Assert.assertEquals(expected, progressBar.get());
        progressBar.setShowSpeed(true);
        
        progressBar.setShowTimeRemaining(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s";
        Assert.assertEquals(expected, progressBar.get());
        progressBar.setShowTimeRemaining(true);
        
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(expected, progressBar.get());
        
        progressBar.setShowSpeed(false);
        progressBar.setShowTimeRemaining(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B";
        Assert.assertEquals(expected, progressBar.get());
        progressBar.setShowSpeed(true);
        progressBar.setShowTimeRemaining(true);
        
        progressBar.setShowBar(false);
        progressBar.setShowRatio(false);
        progressBar.setShowSpeed(false);
        progressBar.setShowTimeRemaining(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51%";
        Assert.assertEquals(expected, progressBar.get());
        progressBar.setShowBar(true);
        progressBar.setShowRatio(true);
        progressBar.setShowSpeed(true);
        progressBar.setShowTimeRemaining(true);
        
        progressBar.setShowPercentage(false);
        progressBar.setShowSpeed(false);
        progressBar.setShowTimeRemaining(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = "[==========>         ]  5100B/10000B";
        Assert.assertEquals(expected, progressBar.get());
        progressBar.setShowPercentage(true);
        progressBar.setShowSpeed(true);
        progressBar.setShowTimeRemaining(true);
        
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(expected, progressBar.get());
    }
    
    /**
     * JUnit test of getPrintable.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getPrintable()
     */
    @Test
    public void testGetPrintable() throws Exception {
        Mockito.when(progressBar.getPercentageString()).thenReturn(" 51%");
        Mockito.when(progressBar.getBarString()).thenReturn("[==========>         ]");
        Mockito.when(progressBar.getRatioString()).thenReturn(" 5100B/10000B");
        Mockito.when(progressBar.getSpeedString()).thenReturn("at 5.2B/s");
        Mockito.when(progressBar.getTimeRemainingString()).thenReturn("ETA: 00:15:43");
        String expected;
        
        //initial
        
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        expected = "";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(0) + ProgressBar.ENDCAP), progressBar.getPrintable());
        
        //standard
        
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(0) + ProgressBar.ENDCAP), progressBar.getPrintable());
        
        //no update
        
        Mockito.when(progressBar.getPercentageString()).thenReturn(" 52%");
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(0) + ProgressBar.ENDCAP), progressBar.getPrintable());
        Mockito.when(progressBar.getPercentageString()).thenReturn(" 51%");
        
        //partial
        
        progressBar.setShowPercentage(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = "[==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(4) + ProgressBar.ENDCAP), progressBar.getPrintable());
        progressBar.setShowPercentage(true);
        
        progressBar.setShowBar(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51%  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(17) + ProgressBar.ENDCAP), progressBar.getPrintable());
        progressBar.setShowBar(true);
        
        progressBar.setShowRatio(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ] at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(0) + ProgressBar.ENDCAP), progressBar.getPrintable());
        progressBar.setShowRatio(true);
        
        progressBar.setShowSpeed(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B - ETA: 00:15:43";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(0) + ProgressBar.ENDCAP), progressBar.getPrintable());
        progressBar.setShowSpeed(true);
        
        progressBar.setShowTimeRemaining(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(5) + ProgressBar.ENDCAP), progressBar.getPrintable());
        progressBar.setShowTimeRemaining(true);
        
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(0) + ProgressBar.ENDCAP), progressBar.getPrintable());
        
        progressBar.setShowSpeed(false);
        progressBar.setShowTimeRemaining(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(25) + ProgressBar.ENDCAP), progressBar.getPrintable());
        progressBar.setShowSpeed(true);
        progressBar.setShowTimeRemaining(true);
        
        progressBar.setShowBar(false);
        progressBar.setShowRatio(false);
        progressBar.setShowSpeed(false);
        progressBar.setShowTimeRemaining(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51%";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(36) + ProgressBar.ENDCAP), progressBar.getPrintable());
        progressBar.setShowBar(true);
        progressBar.setShowRatio(true);
        progressBar.setShowSpeed(true);
        progressBar.setShowTimeRemaining(true);
        
        progressBar.setShowPercentage(false);
        progressBar.setShowSpeed(false);
        progressBar.setShowTimeRemaining(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = "[==========>         ]  5100B/10000B";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(0) + ProgressBar.ENDCAP), progressBar.getPrintable());
        progressBar.setShowPercentage(true);
        progressBar.setShowSpeed(true);
        progressBar.setShowTimeRemaining(true);
        
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        expected = " 51% [==========>         ]  5100B/10000B at 5.2B/s - ETA: 00:15:43";
        Assert.assertEquals(('\r' + expected + ' ' + StringUtility.spaces(0) + ProgressBar.ENDCAP), progressBar.getPrintable());
    }
    
    /**
     * JUnit test of update.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#update(long, boolean)
     * @see ProgressBar#update(long)
     */
    @Test
    public void testUpdate() throws Exception {
        PrintStream saveOut;
        ByteArrayOutputStream out;
        List<Long> rollingProgress = TestUtils.getFieldValue(progressBar, List.class, "rollingProgress");
        List<Long> rollingUpdate = TestUtils.getFieldValue(progressBar, List.class, "rollingUpdate");
        
        //default auto print
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "title", "");
        progressBar.setAutoPrint(false);
        progressBar.update(1);
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        progressBar.setAutoPrint(true);
        progressBar.update(2);
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).print();
        progressBar.setAutoPrint(false);
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        
        //first update, no title
        TestUtils.setFieldValue(progressBar, "progress", 0);
        TestUtils.setFieldValue(progressBar, "previous", 0);
        TestUtils.setFieldValue(progressBar, "current", 0);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        TestUtils.setFieldValue(progressBar, "title", "");
        Assert.assertEquals(0L, progressBar.getFirstUpdate());
        Assert.assertTrue(progressBar.update(1000));
        Assert.assertNotEquals(0L, progressBar.getFirstUpdate());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).getTitleString();
        
        //first update
        TestUtils.setFieldValue(progressBar, "progress", 0);
        TestUtils.setFieldValue(progressBar, "previous", 0);
        TestUtils.setFieldValue(progressBar, "current", 0);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        TestUtils.setFieldValue(progressBar, "title", "Test Bar");
        Assert.assertEquals(0L, progressBar.getFirstUpdate());
        Assert.assertTrue(progressBar.update(2000));
        Assert.assertNotEquals(0L, progressBar.getFirstUpdate());
        Assert.assertEquals(2000L, progressBar.getProgress());
        Assert.assertEquals(0L, progressBar.getPrevious());
        Assert.assertEquals(2000L, progressBar.getCurrent());
        Assert.assertEquals(0L, progressBar.getPreviousUpdate());
        Assert.assertNotEquals(0L, progressBar.getCurrentUpdate());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        
        //multiple updates
        TestUtils.setFieldValue(progressBar, "progress", 0);
        TestUtils.setFieldValue(progressBar, "previous", 0);
        TestUtils.setFieldValue(progressBar, "current", 0);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        Assert.assertTrue(progressBar.update(1000));
        Assert.assertEquals(1000L, progressBar.getProgress());
        Assert.assertEquals(1000L, progressBar.getCurrent());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        Assert.assertFalse(progressBar.update(2000));
        Assert.assertEquals(2000L, progressBar.getProgress());
        Assert.assertEquals(1000L, progressBar.getCurrent());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        Thread.sleep((long) (ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 1.2));
        Assert.assertTrue(progressBar.update(3000));
        Assert.assertEquals(3000L, progressBar.getProgress());
        Assert.assertEquals(3000L, progressBar.getCurrent());
        Assert.assertEquals(2, rollingProgress.size());
        Assert.assertEquals(2, rollingUpdate.size());
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        Thread.sleep((long) (ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 0.2));
        Assert.assertFalse(progressBar.update(4000));
        Assert.assertEquals(4000L, progressBar.getProgress());
        Assert.assertEquals(3000L, progressBar.getCurrent());
        Assert.assertEquals(2, rollingProgress.size());
        Assert.assertEquals(2, rollingUpdate.size());
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        Thread.sleep(ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY);
        Assert.assertTrue(progressBar.update(5000));
        Assert.assertEquals(5000L, progressBar.getProgress());
        Assert.assertEquals(5000L, progressBar.getCurrent());
        Assert.assertEquals(3, rollingProgress.size());
        Assert.assertEquals(3, rollingUpdate.size());
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        Assert.assertTrue(progressBar.update(20000));
        Assert.assertEquals(10000L, progressBar.getProgress());
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertEquals(4, rollingProgress.size());
        Assert.assertEquals(4, rollingUpdate.size());
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        Assert.assertFalse(progressBar.update(21000));
        Assert.assertEquals(10000L, progressBar.getProgress());
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertEquals(4, rollingProgress.size());
        Assert.assertEquals(4, rollingUpdate.size());
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        
        //autoprint
        progressBar.setAutoPrint(true);
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).print();
        TestUtils.setFieldValue(progressBar, "progress", 0);
        TestUtils.setFieldValue(progressBar, "previous", 0);
        TestUtils.setFieldValue(progressBar, "current", 0);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        Assert.assertTrue(progressBar.update(1000));
        Assert.assertEquals(1000L, progressBar.getProgress());
        Assert.assertEquals(1000L, progressBar.getCurrent());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).print();
        Assert.assertFalse(progressBar.update(2000));
        Assert.assertEquals(2000L, progressBar.getProgress());
        Assert.assertEquals(1000L, progressBar.getCurrent());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).print();
        Thread.sleep((long) (ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 1.2));
        Assert.assertTrue(progressBar.update(3000));
        Assert.assertEquals(3000L, progressBar.getProgress());
        Assert.assertEquals(3000L, progressBar.getCurrent());
        Assert.assertEquals(2, rollingProgress.size());
        Assert.assertEquals(2, rollingUpdate.size());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).print();
        Thread.sleep((long) (ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 0.2));
        Assert.assertFalse(progressBar.update(4000));
        Assert.assertEquals(4000L, progressBar.getProgress());
        Assert.assertEquals(3000L, progressBar.getCurrent());
        Assert.assertEquals(2, rollingProgress.size());
        Assert.assertEquals(2, rollingUpdate.size());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).print();
        Thread.sleep(ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY);
        Assert.assertTrue(progressBar.update(5000));
        Assert.assertEquals(5000L, progressBar.getProgress());
        Assert.assertEquals(5000L, progressBar.getCurrent());
        Assert.assertEquals(3, rollingProgress.size());
        Assert.assertEquals(3, rollingUpdate.size());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Mockito.verify(progressBar, VerificationModeFactory.times(4)).print();
        Assert.assertTrue(progressBar.update(20000));
        Assert.assertEquals(10000L, progressBar.getProgress());
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertEquals(4, rollingProgress.size());
        Assert.assertEquals(4, rollingUpdate.size());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Mockito.verify(progressBar, VerificationModeFactory.times(5)).print();
        Assert.assertFalse(progressBar.update(21000));
        Assert.assertEquals(10000L, progressBar.getProgress());
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertEquals(4, rollingProgress.size());
        Assert.assertEquals(4, rollingUpdate.size());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Mockito.verify(progressBar, VerificationModeFactory.times(5)).print();
        progressBar.setAutoPrint(false);
        
        //complete
        TestUtils.setFieldValue(progressBar, "progress", 0);
        TestUtils.setFieldValue(progressBar, "previous", 0);
        TestUtils.setFieldValue(progressBar, "current", 0);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        Mockito.when(progressBar.isComplete()).thenReturn(true);
        Assert.assertFalse(progressBar.update(10000));
        Mockito.when(progressBar.isComplete()).thenCallRealMethod();
        
        //failed
        TestUtils.setFieldValue(progressBar, "progress", 0);
        TestUtils.setFieldValue(progressBar, "previous", 0);
        TestUtils.setFieldValue(progressBar, "current", 0);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        Mockito.when(progressBar.isFailed()).thenReturn(true);
        Assert.assertFalse(progressBar.update(10000));
        Mockito.when(progressBar.isFailed()).thenCallRealMethod();
        
        //no premature complete
        TestUtils.setFieldValue(progressBar, "progress", 0);
        TestUtils.setFieldValue(progressBar, "previous", 0);
        TestUtils.setFieldValue(progressBar, "current", 0);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        Assert.assertEquals(0L, progressBar.getCurrent());
        Assert.assertTrue(progressBar.update(10000));
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertFalse(progressBar.update(10001));
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertFalse(progressBar.update(10002));
        Assert.assertEquals(10000L, progressBar.getCurrent());
        
        //full, complete
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        progressBar.setAutoPrint(true);
        progressBar.setShowSpeed(false);
        TestUtils.setFieldValue(progressBar, "progress", 0);
        TestUtils.setFieldValue(progressBar, "previous", 0);
        TestUtils.setFieldValue(progressBar, "current", 0);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0);
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(true));
        rollingProgress.clear();
        rollingUpdate.clear();
        for (int i = 0; i <= 10000; i += 512) {
            progressBar.update(i);
            Thread.sleep(250);
        }
        progressBar.complete(false);
        List<String> completedLines = StringUtility.splitLines(out.toString()
                        .replace("\r", "\r\n").replace("\n\n", "\n"))
                .stream()
                .filter(e -> !e.isEmpty() && !StringUtility.isWhitespace(e))
                .map(StringUtility::removeConsoleEscapeCharacters)
                .map(StringUtility::rTrim)
                .collect(Collectors.toList());
        Assert.assertEquals(22, completedLines.size());
        Assert.assertEquals("Test Bar:", completedLines.get(0));
        Assert.assertEquals("  0% [>                   ]     0B/10000B - ETA: --:--:--", completedLines.get(1));
        Assert.assertEquals("  5% [=>                  ]   512B/10000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(2), 1));
        Assert.assertEquals(" 10% [==>                 ]  1024B/10000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(3), 1));
        Assert.assertEquals(" 15% [===>                ]  1536B/10000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(4), 1));
        Assert.assertEquals(" 20% [====>               ]  2048B/10000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(5), 1));
        Assert.assertEquals(" 25% [=====>              ]  2560B/10000B - ETA: 00:00:03", completedLines.get(6));
        Assert.assertEquals(" 30% [======>             ]  3072B/10000B - ETA: 00:00:03", completedLines.get(7));
        Assert.assertEquals(" 35% [=======>            ]  3584B/10000B - ETA: 00:00:03", completedLines.get(8));
        Assert.assertEquals(" 40% [========>           ]  4096B/10000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(9), 1));
        Assert.assertEquals(" 46% [=========>          ]  4608B/10000B - ETA: 00:00:02", completedLines.get(10));
        Assert.assertEquals(" 51% [==========>         ]  5120B/10000B - ETA: 00:00:02", completedLines.get(11));
        Assert.assertEquals(" 56% [===========>        ]  5632B/10000B - ETA: 00:00:02", completedLines.get(12));
        Assert.assertEquals(" 61% [============>       ]  6144B/10000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(13), 1));
        Assert.assertEquals(" 66% [=============>      ]  6656B/10000B - ETA: 00:00:01", completedLines.get(14));
        Assert.assertEquals(" 71% [==============>     ]  7168B/10000B - ETA: 00:00:01", completedLines.get(15));
        Assert.assertEquals(" 76% [===============>    ]  7680B/10000B - ETA: 00:00:01", completedLines.get(16));
        Assert.assertEquals(" 81% [================>   ]  8192B/10000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(17), 1));
        Assert.assertEquals(" 87% [=================>  ]  8704B/10000B - ETA: 00:00:00", completedLines.get(18));
        Assert.assertEquals(" 92% [==================> ]  9216B/10000B - ETA: 00:00:00", completedLines.get(19));
        Assert.assertEquals(" 97% [===================>]  9728B/10000B - ETA: 00:00:00", completedLines.get(20));
        Assert.assertEquals("100% [====================] 10000B/10000B - Complete", completedLines.get(21));
        progressBar.setAutoPrint(false);
        System.setOut(saveOut);
        
        //full, fail
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        progressBar.setAutoPrint(true);
        progressBar.setShowSpeed(false);
        TestUtils.setFieldValue(progressBar, "progress", 0);
        TestUtils.setFieldValue(progressBar, "previous", 0);
        TestUtils.setFieldValue(progressBar, "current", 0);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0);
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(true));
        rollingProgress.clear();
        rollingUpdate.clear();
        for (int i = 0; i <= 5000; i += 512) {
            progressBar.update(i);
            Thread.sleep(250);
        }
        progressBar.fail(false);
        progressBar.update(10000);
        List<String> failedLines = StringUtility.splitLines(out.toString()
                        .replace("\r", "\r\n").replace("\n\n", "\n"))
                .stream()
                .filter(e -> !e.isEmpty() && !StringUtility.isWhitespace(e))
                .map(StringUtility::removeConsoleEscapeCharacters)
                .map(StringUtility::rTrim)
                .collect(Collectors.toList());
        Assert.assertEquals(12, failedLines.size());
        Assert.assertEquals("Test Bar:", failedLines.get(0));
        Assert.assertEquals("  0% [>                   ]     0B/10000B - ETA: --:--:--", failedLines.get(1));
        Assert.assertEquals("  5% [=>                  ]   512B/10000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(2), 1));
        Assert.assertEquals(" 10% [==>                 ]  1024B/10000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(3), 1));
        Assert.assertEquals(" 15% [===>                ]  1536B/10000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(4), 1));
        Assert.assertEquals(" 20% [====>               ]  2048B/10000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(5), 1));
        Assert.assertEquals(" 25% [=====>              ]  2560B/10000B - ETA: 00:00:03", failedLines.get(6));
        Assert.assertEquals(" 30% [======>             ]  3072B/10000B - ETA: 00:00:03", failedLines.get(7));
        Assert.assertEquals(" 35% [=======>            ]  3584B/10000B - ETA: 00:00:03", failedLines.get(8));
        Assert.assertEquals(" 40% [========>           ]  4096B/10000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(9), 1));
        Assert.assertEquals(" 46% [=========>          ]  4608B/10000B - ETA: 00:00:02", failedLines.get(10));
        Assert.assertEquals(" 46% [=========           ]  4608B/10000B - Failed", failedLines.get(11));
        progressBar.setAutoPrint(false);
        System.setOut(saveOut);
    }
    
    /**
     * JUnit test of addOne.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#addOne()
     */
    @Test
    public void testAddOne() throws Exception {
        Assert.assertEquals(0L, progressBar.getProgress());
        progressBar.addOne();
        Mockito.verify(progressBar).update(ArgumentMatchers.eq(1L));
        Assert.assertEquals(1L, progressBar.getProgress());
        progressBar.addOne();
        Mockito.verify(progressBar).update(ArgumentMatchers.eq(2L));
        Assert.assertEquals(2L, progressBar.getProgress());
        progressBar.addOne();
        Mockito.verify(progressBar).update(ArgumentMatchers.eq(3L));
        Assert.assertEquals(3L, progressBar.getProgress());
    }
    
    /**
     * JUnit test of processLog.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#processLog(String, boolean)
     * @see ProgressBar#processLog(String)
     */
    @Test
    public void testProcessLog() throws Exception {
        //default
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog("1"));
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog("1%"));
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog("test"));
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog("test\nlog\n1%\n"));
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog(null));
        Assert.assertEquals(0L, progressBar.getProgress());
        
        //error flag
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog("1", false));
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog("1%", true));
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog("test", true));
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog("test\nlog\n1%\n", false));
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertFalse(progressBar.processLog(null, true));
        Assert.assertEquals(0L, progressBar.getProgress());
        
        //default error flag
        progressBar = Mockito.spy(ProgressBar.class);
        Mockito.doReturn(false).when(progressBar).processLog(ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        Mockito.doCallRealMethod().when(progressBar).processLog(ArgumentMatchers.anyString());
        progressBar.processLog("test");
        Mockito.verify(progressBar).processLog(ArgumentMatchers.eq("test"), ArgumentMatchers.eq(false));
    }
    
    /**
     * JUnit test of print.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#print()
     */
    @Test
    public void testPrint() throws Exception {
        PrintStream saveOut;
        ByteArrayOutputStream out;
        
        //update
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        progressBar.print();
        Assert.assertEquals(progressBar.getPrintable().replace(" ", " "), out.toString());
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        System.setOut(saveOut);
        
        //no update
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        progressBar.print();
        Assert.assertEquals(progressBar.getPrintable().replace(" ", " "), out.toString());
        Mockito.verify(progressBar, VerificationModeFactory.times(4)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        System.setOut(saveOut);
        
        //first print
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(true));
        progressBar.print();
        Assert.assertEquals(Console.ConsoleEffect.CYAN.apply("Test Bar: ") + System.lineSeparator() +
                progressBar.getPrintable().replace(" ", " "), out.toString());
        Mockito.verify(progressBar, VerificationModeFactory.times(6)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        System.setOut(saveOut);
    }
    
    /**
     * JUnit test of getRatio.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getRatio()
     */
    @Test
    public void testGetRatio() throws Exception {
        TestUtils.setFieldValue(progressBar, "current", 1L);
        TestUtils.setFieldValue(progressBar, "total", 10L);
        Assert.assertEquals(0.1, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 1000000000L);
        TestUtils.setFieldValue(progressBar, "total", 10000000000L);
        Assert.assertEquals(0.1, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 8L);
        TestUtils.setFieldValue(progressBar, "total", 8914156L);
        Assert.assertEquals(8.974489564687896E-7, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 17456122L);
        TestUtils.setFieldValue(progressBar, "total", 28462154L);
        Assert.assertEquals(0.6133099413347283, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 7499L);
        TestUtils.setFieldValue(progressBar, "total", 7500L);
        Assert.assertEquals(0.9998666666666667, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 7500L);
        TestUtils.setFieldValue(progressBar, "total", 7500L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 8000L);
        TestUtils.setFieldValue(progressBar, "total", 7500L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 1000L);
        TestUtils.setFieldValue(progressBar, "total", 1L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", -50L);
        TestUtils.setFieldValue(progressBar, "total", 100L);
        Assert.assertEquals(0.0, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 50L);
        TestUtils.setFieldValue(progressBar, "total", -100L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", -50L);
        TestUtils.setFieldValue(progressBar, "total", -100L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001));
        
        TestUtils.setFieldValue(progressBar, "current", 1L);
        TestUtils.setFieldValue(progressBar, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001));
    }
    
    /**
     * JUnit test of getPercentage.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getPercentage()
     */
    @Test
    public void testGetPercentage() throws Exception {
        TestUtils.setFieldValue(progressBar, "current", 1L);
        TestUtils.setFieldValue(progressBar, "total", 10L);
        Assert.assertEquals(10, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", 1000000000L);
        TestUtils.setFieldValue(progressBar, "total", 10000000000L);
        Assert.assertEquals(10, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", 8L);
        TestUtils.setFieldValue(progressBar, "total", 8914156L);
        Assert.assertEquals(0, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", 17456122L);
        TestUtils.setFieldValue(progressBar, "total", 28462154L);
        Assert.assertEquals(61, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", 7499L);
        TestUtils.setFieldValue(progressBar, "total", 7500L);
        Assert.assertEquals(99, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", 7500L);
        TestUtils.setFieldValue(progressBar, "total", 7500L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", 8000L);
        TestUtils.setFieldValue(progressBar, "total", 7500L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", 1000L);
        TestUtils.setFieldValue(progressBar, "total", 1L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", -50L);
        TestUtils.setFieldValue(progressBar, "total", 100L);
        Assert.assertEquals(0, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", 50L);
        TestUtils.setFieldValue(progressBar, "total", -100L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", -50L);
        TestUtils.setFieldValue(progressBar, "total", -100L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(100, progressBar.getPercentage()));
        
        TestUtils.setFieldValue(progressBar, "current", 1L);
        TestUtils.setFieldValue(progressBar, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(100, progressBar.getPercentage()));
    }
    
    /**
     * JUnit test of getLastSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getLastSpeed()
     */
    @Test
    public void testGetLastSpeed() throws Exception {
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(10, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 11L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(1, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 11L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.5, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 21L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19650147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.2619047619047619, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 18500L);
        TestUtils.setFieldValue(progressBar, "previous", 600L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19650147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(426.1904761904762, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "previous", 20L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 0L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", -50L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 50L);
        TestUtils.setFieldValue(progressBar, "previous", -10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", -50L);
        TestUtils.setFieldValue(progressBar, "previous", -10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 50L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", -19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 50L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 50L);
        TestUtils.setFieldValue(progressBar, "previous", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19609147071900L);
        TestUtils.setFieldValue(progressBar, "previousUpdate", -19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
    }
    
    /**
     * JUnit test of getAverageSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getAverageSpeed()
     */
    @Test
    public void testGetAverageSpeed() throws Exception {
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(20, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(10, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 1500L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(750, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19708147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0.2, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 8754L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19990147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(22.916230366492147, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(10000, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0L);
        Assert.assertEquals(5.099400817003212E-4, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", -20L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
    }
    
    /**
     * JUnit test of getRollingAverageSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getRollingAverageSpeed()
     */
    @Test
    public void testGetRollingAverageSpeed() throws Exception {
        List<Long> rollingProgress = TestUtils.getFieldValue(progressBar, List.class, "rollingProgress");
        List<Long> rollingUpdate = TestUtils.getFieldValue(progressBar, List.class, "rollingUpdate");
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(1.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 12500000000L, 15000000000L, 17500000000L, 20000000000L));
        Assert.assertEquals(4.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L));
        rollingUpdate.addAll(Arrays.asList(1000000000L, 1250000000L, 1500000000L, 1750000000L, 2000000000L));
        Assert.assertEquals(40.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(104818L, 200048L, 306842L, 401644L, 509872L));
        rollingUpdate.addAll(Arrays.asList(1546138845L, 1610244875L, 1751366975L, 1780469542L, 1955675425L));
        Assert.assertEquals(989054.5064374958, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(0L, 0L, 0L, 0L, 1L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(0.025, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(0L, 0L, 0L, 0L, 0L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(0.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(0L, 0L, 0L, 0L, 0L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(0.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(50L, 40L, 30L, 20L, 1L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(0.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L));
        rollingUpdate.addAll(Arrays.asList(50000000000L, 40000000000L, 30000000000L, 20000000000L, 10000000000L));
        Assert.assertEquals(0.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(50L, 40L, 30L, 20L, 1L));
        rollingUpdate.addAll(Arrays.asList(50000000000L, 40000000000L, 30000000000L, 20000000000L, 10000000000L));
        Assert.assertEquals(0.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L));
        Assert.assertEquals(0.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L, 60L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L, 60000000000L));
        Assert.assertEquals(0.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(0L, 0L, 0L, 0L, 0L));
        rollingUpdate.addAll(Arrays.asList(0L, 0L, 0L, 0L, 0L));
        Assert.assertEquals(0.0, progressBar.getRollingAverageSpeed(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of getTotalDuration.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getTotalDuration()
     */
    @Test
    public void testGetTotalDuration() throws Exception {
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 0L);
        Assert.assertEquals(1000000000L, progressBar.getTotalDuration());
        
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19884806395487L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 0L);
        Assert.assertEquals(276659323587L, progressBar.getTotalDuration());
        
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19884806395487L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 24L);
        Assert.assertEquals(300659323587L, progressBar.getTotalDuration());
        
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 0L);
        Assert.assertEquals(19609147071900L, progressBar.getTotalDuration());
        
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19884806395487L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19884806395487L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", -19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19884806395487L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", -19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19884806395487L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", -456877L);
        Assert.assertEquals(276659323587L, progressBar.getTotalDuration());
    }
    
    /**
     * JUnit test of getTimeRemaining.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getTimeRemaining()
     */
    @Test
    public void testGetTimeRemaining() throws Exception {
        //standard
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(499, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(998, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 1500L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(11, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19708147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(49900, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 8754L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19990147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(54, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0L);
        Assert.assertEquals(19590536L, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", -20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        //initial progress
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(998, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(1996, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 1500L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 515L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(17, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 8L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19708147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(83166, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 8754L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 1000L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19990147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(61, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 5000L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 60L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "initialProgress", -5L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(1998, progressBar.getTimeRemaining());
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "initialProgress", -50L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(1998, progressBar.getTimeRemaining());
    }
    
    /**
     * JUnit test of isComplete.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#isComplete()
     */
    @Test
    public void testIsComplete() throws Exception {
        TestUtils.setFieldValue(progressBar, "current", 0L);
        Assert.assertFalse(progressBar.isComplete());
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        Assert.assertFalse(progressBar.isComplete());
        
        TestUtils.setFieldValue(progressBar, "current", 9999L);
        Assert.assertFalse(progressBar.isComplete());
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        Assert.assertTrue(progressBar.isComplete());
        
        TestUtils.setFieldValue(progressBar, "current", 10001L);
        Assert.assertTrue(progressBar.isComplete());
        
        TestUtils.setFieldValue(progressBar, "current", 20000L);
        Assert.assertTrue(progressBar.isComplete());
    }
    
    /**
     * JUnit test of complete.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#complete(boolean, String)
     * @see ProgressBar#complete(boolean)
     * @see ProgressBar#complete()
     */
    @Test
    public void testComplete() throws Exception {
        PrintStream saveOut;
        ByteArrayOutputStream out;
        
        TestUtils.setFieldValue(progressBar, "title", "");
        
        //standard
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        progressBar.complete(false, "");
        Assert.assertEquals(progressBar.getTotal(), progressBar.getProgress());
        Assert.assertEquals(progressBar.getTotal(), progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10000B/10000B - Complete",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //print time
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(progressBar.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toNanos(57653L));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 50008147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 120L);
        progressBar.complete(true, "");
        Assert.assertEquals(progressBar.getTotal(), progressBar.getProgress());
        Assert.assertEquals(progressBar.getTotal(), progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10000B/10000B - Complete (16h 0m 53s)",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        Mockito.when(progressBar.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        //additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        progressBar.complete(false, "Press any key to continue...");
        Assert.assertEquals(progressBar.getTotal(), progressBar.getProgress());
        Assert.assertEquals(progressBar.getTotal(), progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10000B/10000B - Complete - Press any key to continue...",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //print time and additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(progressBar.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toNanos(57653L));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 50008147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 120L);
        progressBar.complete(true, "Press any key to continue...");
        Assert.assertEquals(progressBar.getTotal(), progressBar.getProgress());
        Assert.assertEquals(progressBar.getTotal(), progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(4)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10000B/10000B - Complete (16h 0m 53s) - Press any key to continue...",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        Mockito.when(progressBar.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        //first print
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(4)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        TestUtils.setFieldValue(progressBar, "title", "Test Bar");
        progressBar.complete(false, "");
        Assert.assertEquals(progressBar.getTotal(), progressBar.getProgress());
        Assert.assertEquals(progressBar.getTotal(), progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(5)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "Test Bar: " + "100% [====================] 10000B/10000B - Complete",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //default additional info
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).complete(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).complete(ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        final AtomicBoolean printTime = new AtomicBoolean(true);
        Mockito.doAnswer(invocationOnMock -> {
            Assert.assertEquals(printTime.get(), invocationOnMock.getArgument(0));
            Assert.assertEquals("", invocationOnMock.getArgument(1));
            return null;
        }).when(progressBar).complete(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        printTime.set(false);
        progressBar.complete(false);
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).complete(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        printTime.set(true);
        progressBar.complete(true);
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).complete(ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        Mockito.doCallRealMethod().when(progressBar).complete(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        
        //default print time
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).complete(ArgumentMatchers.eq(true));
        Mockito.doAnswer(invocationOnMock -> {
            Assert.assertTrue(invocationOnMock.getArgument(0));
            return null;
        }).when(progressBar).complete(ArgumentMatchers.anyBoolean());
        progressBar.complete();
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).complete(ArgumentMatchers.eq(true));
        Mockito.doCallRealMethod().when(progressBar).complete(ArgumentMatchers.anyBoolean());
    }
    
    /**
     * JUnit test of isFailed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#isFailed()
     */
    @Test
    public void testIsFailed() throws Exception {
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(false));
        Assert.assertFalse(progressBar.isFailed());
        
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertTrue(progressBar.isFailed());
    }
    
    /**
     * JUnit test of fail.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#fail(boolean, String)
     * @see ProgressBar#fail(boolean)
     * @see ProgressBar#fail()
     */
    @Test
    public void testFail() throws Exception {
        PrintStream saveOut;
        ByteArrayOutputStream out;
        
        TestUtils.setFieldValue(progressBar, "title", "");
        
        //standard
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        progressBar.fail(false, "");
        Assert.assertEquals(5000L, progressBar.getProgress());
        Assert.assertEquals(5000L, progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5000B/10000B - Failed",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //print time
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(progressBar.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toNanos(57653L));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 50008147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 120L);
        progressBar.fail(true, "");
        Assert.assertEquals(5000L, progressBar.getProgress());
        Assert.assertEquals(5000L, progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5000B/10000B - Failed (16h 0m 53s)",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        Mockito.when(progressBar.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        //additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        progressBar.fail(false, "Press any key to continue...");
        Assert.assertEquals(5000L, progressBar.getProgress());
        Assert.assertEquals(5000L, progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5000B/10000B - Failed - Press any key to continue...",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //print time and additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(progressBar.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toNanos(57653L));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(false));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 50008147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "initialDuration", 120L);
        progressBar.fail(true, "Press any key to continue...");
        Assert.assertEquals(5000L, progressBar.getProgress());
        Assert.assertEquals(5000L, progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(4)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5000B/10000B - Failed (16h 0m 53s) - Press any key to continue...",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        Mockito.when(progressBar.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        //first print
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(4)).getPrintable();
        TestUtils.setFieldValue(progressBar, "update", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "firstPrint", new AtomicBoolean(true));
        TestUtils.setFieldValue(progressBar, "progress", 5000L);
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        TestUtils.setFieldValue(progressBar, "title", "Test Bar");
        progressBar.fail(false, "");
        Assert.assertEquals(5000L, progressBar.getProgress());
        Assert.assertEquals(5000L, progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(5)).getPrintable();
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestUtils.getFieldValue(progressBar, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "Test Bar:  50% [==========          ]  5000B/10000B - Failed",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll(" ", ""))
        );
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //default additional info
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).fail(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).fail(ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        final AtomicBoolean printTime = new AtomicBoolean(true);
        Mockito.doAnswer(invocationOnMock -> {
            Assert.assertEquals(printTime.get(), invocationOnMock.getArgument(0));
            Assert.assertEquals("", invocationOnMock.getArgument(1));
            return null;
        }).when(progressBar).fail(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        printTime.set(false);
        progressBar.fail(false);
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).fail(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        printTime.set(true);
        progressBar.fail(true);
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).fail(ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        Mockito.doCallRealMethod().when(progressBar).fail(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        
        //default print time
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).fail(ArgumentMatchers.eq(true));
        Mockito.doAnswer(invocationOnMock -> {
            Assert.assertTrue(invocationOnMock.getArgument(0));
            return null;
        }).when(progressBar).fail(ArgumentMatchers.anyBoolean());
        progressBar.fail();
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).fail(ArgumentMatchers.eq(true));
        Mockito.doCallRealMethod().when(progressBar).fail(ArgumentMatchers.anyBoolean());
    }
    
    /**
     * JUnit test of getTitleString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getTitleString()
     */
    @Test
    public void testGetTitleString() throws Exception {
        TestUtils.setFieldValue(progressBar, "title", "Test Bar");
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("Test Bar: "),
                progressBar.getTitleString()
        );
        
        TestUtils.setFieldValue(progressBar, "title", "");
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply(": "),
                progressBar.getTitleString()
        );
        TestUtils.setFieldValue(progressBar, "title", "Test Bar");
    }
    
    /**
     * JUnit test of getPercentageString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getPercentageString()
     */
    @Test
    public void testGetPercentageString() throws Exception {
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 50") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 7784L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 77") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 9999L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 99") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("100") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 20000L);
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("100") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 1L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("  0") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("  0") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", -941L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("  0") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                Console.ConsoleEffect.RED.apply("  0") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 7784L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                Console.ConsoleEffect.RED.apply(" 77") + '%',
                progressBar.getPercentageString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                Console.ConsoleEffect.RED.apply("100") + '%',
                progressBar.getPercentageString()
        );
    }
    
    /**
     * JUnit test of getBarString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getBarString()
     */
    @Test
    public void testGetBarString() throws Exception {
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply("==========>         ") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 7784L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply("===============>    ") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 9999L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply("===================>") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.CYAN.apply("====================") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 20000L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.CYAN.apply("====================") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 1L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply(">                   ") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply(">                   ") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", -941L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply(">                   ") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.RED.apply("                    ") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 7784L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.RED.apply("===============     ") + ']',
                progressBar.getBarString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.RED.apply("====================") + ']',
                progressBar.getBarString()
        );
    }
    
    /**
     * JUnit test of getRatioString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getRatioString()
     */
    @Test
    public void testGetRatioString() throws Exception {
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 5000") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 7784L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 7784") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 9999L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 9999") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("10000") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 20000L);
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("10000") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 1L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("    1") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("    0") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", -941L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("    0") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                Console.ConsoleEffect.RED.apply("    0") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 7784L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                Console.ConsoleEffect.RED.apply(" 7784") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                Console.ConsoleEffect.RED.apply("10000") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
    }
    
    /**
     * JUnit test of getSpeedString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getSpeedString()
     */
    @Test
    public void testGetSpeedString() throws Exception {
        Mockito.doReturn(503.0).when(progressBar).getRollingAverageSpeed();
        Assert.assertEquals(
                "at 503.0B/s",
                progressBar.getSpeedString()
        );
        
        Mockito.doReturn(9.1219453).when(progressBar).getRollingAverageSpeed();
        Assert.assertEquals(
                "at 9.1B/s",
                progressBar.getSpeedString()
        );
        
        Mockito.doReturn(107.4945).when(progressBar).getRollingAverageSpeed();
        Assert.assertEquals(
                "at 107.5B/s",
                progressBar.getSpeedString()
        );
        
        Mockito.doReturn(0.0).when(progressBar).getRollingAverageSpeed();
        Assert.assertEquals(
                "at 0.0B/s",
                progressBar.getSpeedString()
        );
        
        Mockito.doReturn(-3870.0).when(progressBar).getRollingAverageSpeed();
        Assert.assertEquals(
                "at -3870.0B/s",
                progressBar.getSpeedString()
        );
        
        Mockito.doReturn(503.0).when(progressBar).getRollingAverageSpeed();
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(false));
        Assert.assertEquals(
                "",
                progressBar.getSpeedString()
        );
        
        Mockito.doReturn(503.0).when(progressBar).getRollingAverageSpeed();
        TestUtils.setFieldValue(progressBar, "current", 7784L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                "",
                progressBar.getSpeedString()
        );
        
        Mockito.doReturn(503.0).when(progressBar).getRollingAverageSpeed();
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                "",
                progressBar.getSpeedString()
        );
    }
    
    /**
     * JUnit test of getTimeRemainingString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getTimeRemainingString()
     */
    @Test
    public void testGetTimeRemainingString() throws Exception {
        //standard
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:08:19", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:16:38", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 1500L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:00:11", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19708147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 13:51:40", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 8754L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19990147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:00:54", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Console.ConsoleEffect.CYAN.apply("Complete"), progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0L);
        Assert.assertEquals("ETA: 5441:48:56", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 0L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 0L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", -20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", -19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        //initial progress
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:16:38", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 10L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:33:16", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 1500L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 515L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:00:17", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 20L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 8L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19708147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 23:06:06", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 8754L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 1000L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19990147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:01:01", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 5000L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19609147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Console.ConsoleEffect.CYAN.apply("Complete"), progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 0L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 60L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "initialProgress", -5L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:33:18", progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 10L);
        TestUtils.setFieldValue(progressBar, "initialProgress", -50L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:33:18", progressBar.getTimeRemainingString());
        
        //done
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(false));
        Assert.assertEquals(Console.ConsoleEffect.CYAN.apply("Complete"), progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 5000L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(Console.ConsoleEffect.RED.apply("Failed"), progressBar.getTimeRemainingString());
        
        TestUtils.setFieldValue(progressBar, "current", 10000L);
        TestUtils.setFieldValue(progressBar, "initialProgress", 0L);
        TestUtils.setFieldValue(progressBar, "currentUpdate", 19610147071900L);
        TestUtils.setFieldValue(progressBar, "firstUpdate", 19608147071900L);
        TestUtils.setFieldValue(progressBar, "failed", new AtomicBoolean(true));
        Assert.assertEquals(Console.ConsoleEffect.RED.apply("Failed"), progressBar.getTimeRemainingString());
    }
    
    /**
     * JUnit test of getTitle.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getTitle()
     */
    @Test
    public void testGetTitle() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "title", "test title");
        Assert.assertEquals("test title", sut.getTitle());
    }
    
    /**
     * JUnit test of getTotal.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getTotal()
     */
    @Test
    public void testGetTotal() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "total", 87000L);
        Assert.assertEquals(87000L, sut.getTotal());
    }
    
    /**
     * JUnit test of getProgress.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getProgress()
     */
    @Test
    public void testGetProgress() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "progress", 51L);
        Assert.assertEquals(51L, sut.getProgress());
    }
    
    /**
     * JUnit test of getCurrent.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getCurrent()
     */
    @Test
    public void testGetCurrent() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "current", 50400L);
        Assert.assertEquals(50400L, sut.getCurrent());
    }
    
    /**
     * JUnit test of getPrevious.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getPrevious()
     */
    @Test
    public void testGetPrevious() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "previous", 11123L);
        Assert.assertEquals(11123L, sut.getPrevious());
    }
    
    /**
     * JUnit test of getInitialProgress.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getInitialProgress()
     */
    @Test
    public void testGetInitialProgress() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "initialProgress", 75436L);
        Assert.assertEquals(75436L, sut.getInitialProgress());
    }
    
    /**
     * JUnit test of getInitialDuration.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getInitialDuration()
     */
    @Test
    public void testGetInitialDuration() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "initialDuration", 32554778965L);
        Assert.assertEquals(32554778965L, sut.getInitialDuration());
    }
    
    /**
     * JUnit test of getCurrentUpdate.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getCurrentUpdate()
     */
    @Test
    public void testGetCurrentUpdate() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "currentUpdate", 641L);
        Assert.assertEquals(641L, sut.getCurrentUpdate());
    }
    
    /**
     * JUnit test of getPreviousUpdate.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getPreviousUpdate()
     */
    @Test
    public void testGetPreviousUpdate() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "previousUpdate", 15462L);
        Assert.assertEquals(15462L, sut.getPreviousUpdate());
    }
    
    /**
     * JUnit test of getFirstUpdate.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getFirstUpdate()
     */
    @Test
    public void testGetFirstUpdate() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "firstUpdate", 8745100L);
        Assert.assertEquals(8745100L, sut.getFirstUpdate());
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "width", 163);
        Assert.assertEquals(163, sut.getWidth());
    }
    
    /**
     * JUnit test of getUnits.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getUnits()
     */
    @Test
    public void testGetUnits() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "units", "test unit");
        Assert.assertEquals("test unit", sut.getUnits());
    }
    
    /**
     * JUnit test of getAutoPrint.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getAutoPrint()
     */
    @Test
    public void testGetAutoPrint() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        TestUtils.setFieldValue(sut, "autoPrint", false);
        Assert.assertFalse(sut.getAutoPrint());
        TestUtils.setFieldValue(sut, "autoPrint", true);
        Assert.assertTrue(sut.getAutoPrint());
    }
    
    /**
     * JUnit test of getShowPercentage.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getShowPercentage()
     */
    @Test
    public void testGetShowPercentage() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_PERCENTAGE, sut.getShowPercentage());
        TestUtils.setFieldValue(sut, "showPercentage", false);
        Assert.assertFalse(sut.getShowPercentage());
        TestUtils.setFieldValue(sut, "showPercentage", true);
        Assert.assertTrue(sut.getShowPercentage());
    }
    
    /**
     * JUnit test of getShowBar.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getShowBar()
     */
    @Test
    public void testGetShowBar() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_BAR, sut.getShowBar());
        TestUtils.setFieldValue(sut, "showBar", false);
        Assert.assertFalse(sut.getShowBar());
        TestUtils.setFieldValue(sut, "showBar", true);
        Assert.assertTrue(sut.getShowBar());
    }
    
    /**
     * JUnit test of getShowRatio.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getShowRatio()
     */
    @Test
    public void testGetShowRatio() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_RATIO, sut.getShowRatio());
        TestUtils.setFieldValue(sut, "showRatio", false);
        Assert.assertFalse(sut.getShowRatio());
        TestUtils.setFieldValue(sut, "showRatio", true);
        Assert.assertTrue(sut.getShowRatio());
    }
    
    /**
     * JUnit test of getShowSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getShowSpeed()
     */
    @Test
    public void testGetShowSpeed() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_SPEED, sut.getShowSpeed());
        TestUtils.setFieldValue(sut, "showSpeed", false);
        Assert.assertFalse(sut.getShowSpeed());
        TestUtils.setFieldValue(sut, "showSpeed", true);
        Assert.assertTrue(sut.getShowSpeed());
    }
    
    /**
     * JUnit test of getShowTimeRemaining.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getShowTimeRemaining()
     */
    @Test
    public void testGetShowTimeRemaining() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_TIME_REMAINING, sut.getShowTimeRemaining());
        TestUtils.setFieldValue(sut, "showTimeRemaining", false);
        Assert.assertFalse(sut.getShowTimeRemaining());
        TestUtils.setFieldValue(sut, "showTimeRemaining", true);
        Assert.assertTrue(sut.getShowTimeRemaining());
    }
    
    /**
     * JUnit test of updateTitle.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#updateTitle(String)
     */
    @Test
    public void testUpdateTitle() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals("", TestUtils.getFieldValue(sut, "title"));
        Assert.assertTrue(TestUtils.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertTrue(sut.updateTitle("Test"));
        Assert.assertEquals("Test", TestUtils.getFieldValue(sut, "title"));
        Assert.assertTrue(TestUtils.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertTrue(sut.updateTitle("Test 2"));
        Assert.assertEquals("Test 2", TestUtils.getFieldValue(sut, "title"));
        Assert.assertTrue(TestUtils.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        TestUtils.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        Assert.assertFalse(TestUtils.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertFalse(sut.updateTitle("Test 3"));
        Assert.assertEquals("Test 2", TestUtils.getFieldValue(sut, "title"));
        Assert.assertFalse(TestUtils.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
    }
    
    /**
     * JUnit test of updateTotal.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#updateTotal(long)
     */
    @Test
    public void testUpdateTotal() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, TestUtils.getFieldValue(sut, "total"));
        sut.updateTotal(74461210L);
        Assert.assertEquals(74461210L, TestUtils.getFieldValue(sut, "total"));
    }
    
    /**
     * JUnit test of updateUnits.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#updateUnits(String, double)
     * @see ProgressBar#updateUnits(String)
     */
    @Test
    public void testUpdateUnits() throws Exception {
        ProgressBar sut = new ProgressBar("", 100, "seconds");
        Assert.assertEquals("seconds", TestUtils.getFieldValue(sut, "units"));
        Assert.assertEquals(100L, TestUtils.getFieldValue(sut, "total"));
        Assert.assertEquals(0L, TestUtils.getFieldValue(sut, "progress"));
        Assert.assertEquals(0L, TestUtils.getFieldValue(sut, "current"));
        Assert.assertEquals(0L, TestUtils.getFieldValue(sut, "previous"));
        Assert.assertEquals(0L, TestUtils.getFieldValue(sut, "initialProgress"));
        Assert.assertArrayEquals(new Long[] {},
                TestUtils.getFieldValue(sut, List.class, "rollingProgress").toArray());
        TestUtils.setFieldValue(sut, "progress", 54L);
        TestUtils.setFieldValue(sut, "current", 54L);
        TestUtils.setFieldValue(sut, "previous", 51L);
        TestUtils.setFieldValue(sut, "initialProgress", 10L);
        TestUtils.setFieldValue(sut, "rollingProgress", Arrays.asList(39L, 43L, 46L, 51L, 54L));
        sut.updateUnits("s");
        Assert.assertEquals("s", TestUtils.getFieldValue(sut, "units"));
        Assert.assertEquals(100L, TestUtils.getFieldValue(sut, "total"));
        Assert.assertEquals(54L, TestUtils.getFieldValue(sut, "progress"));
        Assert.assertEquals(54L, TestUtils.getFieldValue(sut, "current"));
        Assert.assertEquals(51L, TestUtils.getFieldValue(sut, "previous"));
        Assert.assertEquals(10L, TestUtils.getFieldValue(sut, "initialProgress"));
        Assert.assertArrayEquals(new Long[] {39L, 43L, 46L, 51L, 54L},
                TestUtils.getFieldValue(sut, List.class, "rollingProgress").toArray());
        sut.updateUnits("ms", 1000);
        Assert.assertEquals("ms", TestUtils.getFieldValue(sut, "units"));
        Assert.assertEquals(100000L, TestUtils.getFieldValue(sut, "total"));
        Assert.assertEquals(54000L, TestUtils.getFieldValue(sut, "progress"));
        Assert.assertEquals(54000L, TestUtils.getFieldValue(sut, "current"));
        Assert.assertEquals(51000L, TestUtils.getFieldValue(sut, "previous"));
        Assert.assertEquals(10000L, TestUtils.getFieldValue(sut, "initialProgress"));
        Assert.assertArrayEquals(new Long[] {39000L, 43000L, 46000L, 51000L, 54000L},
                TestUtils.getFieldValue(sut, List.class, "rollingProgress").toArray());
    }
    
    /**
     * JUnit test of defineInitialProgress.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#defineInitialProgress(long)
     */
    @Test
    public void testDefineInitialProgress() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, TestUtils.getFieldValue(sut, "initialProgress"));
        Assert.assertTrue(sut.defineInitialProgress(74461210L));
        Assert.assertEquals(74461210L, TestUtils.getFieldValue(sut, "initialProgress"));
        Assert.assertFalse(sut.defineInitialProgress(191070334L));
        Assert.assertEquals(74461210L, TestUtils.getFieldValue(sut, "initialProgress"));
        TestUtils.setFieldValue(sut, "initialProgress", 0L);
        Assert.assertEquals(0L, TestUtils.getFieldValue(sut, "initialProgress"));
        Assert.assertTrue(sut.defineInitialProgress(191070334L));
        Assert.assertEquals(191070334L, TestUtils.getFieldValue(sut, "initialProgress"));
    }
    
    /**
     * JUnit test of defineInitialDuration.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#defineInitialDuration(long)
     */
    @Test
    public void testDefineInitialDuration() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, TestUtils.getFieldValue(sut, "initialDuration"));
        Assert.assertTrue(sut.defineInitialDuration(158L));
        Assert.assertEquals(158L, TestUtils.getFieldValue(sut, "initialDuration"));
        Assert.assertFalse(sut.defineInitialDuration(3877L));
        Assert.assertEquals(158L, TestUtils.getFieldValue(sut, "initialDuration"));
        TestUtils.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(0L, TestUtils.getFieldValue(sut, "initialDuration"));
        Assert.assertTrue(sut.defineInitialDuration(3877L));
        Assert.assertEquals(3877L, TestUtils.getFieldValue(sut, "initialDuration"));
    }
    
    /**
     * JUnit test of setAutoPrint.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setAutoPrint(boolean)
     */
    @Test
    public void testSetAutoPrint() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        sut.setAutoPrint(false);
        Assert.assertFalse(TestUtils.getFieldValue(sut, boolean.class, "autoPrint"));
        sut.setAutoPrint(true);
        Assert.assertTrue(TestUtils.getFieldValue(sut, boolean.class, "autoPrint"));
    }
    
    /**
     * JUnit test of setShowPercentage.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowPercentage(boolean)
     */
    @Test
    public void testSetShowPercentage() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_PERCENTAGE, TestUtils.getFieldValue(sut, "showPercentage"));
        sut.setShowPercentage(false);
        Assert.assertFalse(TestUtils.getFieldValue(sut, boolean.class, "showPercentage"));
        sut.setShowPercentage(true);
        Assert.assertTrue(TestUtils.getFieldValue(sut, boolean.class, "showPercentage"));
    }
    
    /**
     * JUnit test of setShowBar.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowBar(boolean)
     */
    @Test
    public void testSetShowBar() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_BAR, TestUtils.getFieldValue(sut, "showBar"));
        sut.setShowBar(false);
        Assert.assertFalse(TestUtils.getFieldValue(sut, boolean.class, "showBar"));
        sut.setShowBar(true);
        Assert.assertTrue(TestUtils.getFieldValue(sut, boolean.class, "showBar"));
    }
    
    /**
     * JUnit test of setShowRatio.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowRatio(boolean)
     */
    @Test
    public void testSetShowRatio() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_RATIO, TestUtils.getFieldValue(sut, "showRatio"));
        sut.setShowRatio(false);
        Assert.assertFalse(TestUtils.getFieldValue(sut, boolean.class, "showRatio"));
        sut.setShowRatio(true);
        Assert.assertTrue(TestUtils.getFieldValue(sut, boolean.class, "showRatio"));
    }
    
    /**
     * JUnit test of setShowSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowSpeed(boolean)
     */
    @Test
    public void testSetShowSpeed() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_SPEED, TestUtils.getFieldValue(sut, "showSpeed"));
        sut.setShowSpeed(false);
        Assert.assertFalse(TestUtils.getFieldValue(sut, boolean.class, "showSpeed"));
        sut.setShowSpeed(true);
        Assert.assertTrue(TestUtils.getFieldValue(sut, boolean.class, "showSpeed"));
    }
    
    /**
     * JUnit test of setShowTimeRemaining.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowTimeRemaining(boolean)
     */
    @Test
    public void testSetShowTimeRemaining() throws Exception {
        ProgressBar sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_TIME_REMAINING, TestUtils.getFieldValue(sut, "showTimeRemaining"));
        sut.setShowTimeRemaining(false);
        Assert.assertFalse(TestUtils.getFieldValue(sut, boolean.class, "showTimeRemaining"));
        sut.setShowTimeRemaining(true);
        Assert.assertTrue(TestUtils.getFieldValue(sut, boolean.class, "showTimeRemaining"));
    }
    
}
