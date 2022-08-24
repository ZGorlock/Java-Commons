/*
 * File:    ProgressBarTest.java
 * Package: commons.io.console
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.console;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import commons.object.string.StringUtility;
import commons.test.TestAccess;
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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ProgressBar.
 *
 * @see ProgressBar
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
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
    private ProgressBar sut;
    
    
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
        sut = PowerMockito.spy(
                new ProgressBar("Test Bar", 10000L, 20, "B", false));
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
     * @see ProgressBar#DEFAULT_AUTO_PRINT
     * @see ProgressBar#MINIMUM_UPDATE_DELAY
     * @see ProgressBar#ROLLING_AVERAGE_UPDATE_COUNT
     * @see ProgressBar#DEFAULT_SHOW_PERCENTAGE
     * @see ProgressBar#DEFAULT_SHOW_BAR
     * @see ProgressBar#DEFAULT_SHOW_RATIO
     * @see ProgressBar#DEFAULT_SHOW_SPEED
     * @see ProgressBar#DEFAULT_SHOW_TIME_REMAINING
     * @see ProgressBar#DEFAULT_USE_COMMAS
     * @see ProgressBar#DEFAULT_COLOR_BASE
     * @see ProgressBar#DEFAULT_COLOR_GOOD
     * @see ProgressBar#DEFAULT_COLOR_BAD
     * @see ProgressBar#INTEGER_FORMAT
     * @see ProgressBar#DECIMAL_FORMAT
     * @see ProgressBar#ENDCAP
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(32, ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH);
        Assert.assertTrue(ProgressBar.DEFAULT_AUTO_PRINT);
        Assert.assertEquals(200, ProgressBar.MINIMUM_UPDATE_DELAY);
        Assert.assertEquals(5, ProgressBar.ROLLING_AVERAGE_UPDATE_COUNT);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_PERCENTAGE);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_BAR);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_RATIO);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_SPEED);
        Assert.assertTrue(ProgressBar.DEFAULT_SHOW_TIME_REMAINING);
        Assert.assertTrue(ProgressBar.DEFAULT_USE_COMMAS);
        Assert.assertEquals(Console.ConsoleEffect.GREEN, ProgressBar.DEFAULT_COLOR_BASE);
        Assert.assertEquals(Console.ConsoleEffect.CYAN, ProgressBar.DEFAULT_COLOR_GOOD);
        Assert.assertEquals(Console.ConsoleEffect.RED, ProgressBar.DEFAULT_COLOR_BAD);
        Assert.assertEquals("#,##0", ProgressBar.INTEGER_FORMAT.toPattern());
        Assert.assertEquals("#,##0.0", ProgressBar.DECIMAL_FORMAT.toPattern());
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
        sut = new ProgressBar("Test", 1000, 50, "B", false);
        Assert.assertEquals("Test", sut.getTitle());
        Assert.assertEquals(1000, sut.getTotal());
        Assert.assertEquals(50, sut.getWidth());
        Assert.assertEquals("B", sut.getUnits());
        Assert.assertFalse(sut.getAutoPrint());
        
        //default auto print
        sut = new ProgressBar("Test2", 5000, 55, "kb");
        Assert.assertEquals("Test2", sut.getTitle());
        Assert.assertEquals(5000, sut.getTotal());
        Assert.assertEquals(55, sut.getWidth());
        Assert.assertEquals("kb", sut.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_AUTO_PRINT, sut.getAutoPrint());
        
        //default width
        sut = new ProgressBar("Test3", 51000, "MB");
        Assert.assertEquals("Test3", sut.getTitle());
        Assert.assertEquals(51000, sut.getTotal());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, sut.getWidth());
        Assert.assertEquals("MB", sut.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_AUTO_PRINT, sut.getAutoPrint());
        
        //no units
        sut = new ProgressBar("Test4", 581000, 61);
        Assert.assertEquals("Test4", sut.getTitle());
        Assert.assertEquals(581000, sut.getTotal());
        Assert.assertEquals(61, sut.getWidth());
        Assert.assertEquals("", sut.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_AUTO_PRINT, sut.getAutoPrint());
        
        //default width, no units
        sut = new ProgressBar("Test5", 1581000);
        Assert.assertEquals("Test5", sut.getTitle());
        Assert.assertEquals(1581000, sut.getTotal());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, sut.getWidth());
        Assert.assertEquals("", sut.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_AUTO_PRINT, sut.getAutoPrint());
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#get()
     */
    @Test
    public void testGet() throws Exception {
        Mockito.when(sut.buildPercentageString()).thenReturn(" 51%");
        Mockito.when(sut.buildBarString()).thenReturn("[==========>         ]");
        Mockito.when(sut.buildRatioString()).thenReturn(" 5,100B/10,000B");
        Mockito.when(sut.buildSpeedString()).thenReturn("at 5.2B/s");
        Mockito.when(sut.buildTimeRemainingString()).thenReturn("ETA: 00:15:43");
        
        //initial
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Assert.assertEquals("", sut.get());
        
        //standard
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43",
                sut.get());
        
        //done
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        PowerMockito.doReturn(true).when(sut, "isDone");
        Assert.assertEquals(
                " 51% [==========>         ]  5,100B/10,000B - ETA: 00:15:43",
                sut.get());
        PowerMockito.doCallRealMethod().when(sut, "isDone");
        
        //indent
        
        TestAccess.setFieldValue(sut, "indent", 4);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                "     51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43",
                sut.get());
        
        TestAccess.setFieldValue(sut, "indent", 0);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43",
                sut.get());
        
        //no update
        
        Mockito.when(sut.buildPercentageString()).thenReturn(" 52%");
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Assert.assertEquals(
                " 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43",
                sut.get());
        Mockito.when(sut.buildPercentageString()).thenReturn(" 51%");
        
        //partial
        
        sut.setShowPercentage(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                "[==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43",
                sut.get());
        sut.setShowPercentage(true);
        
        sut.setShowBar(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51%  5,100B/10,000B at 5.2B/s - ETA: 00:15:43",
                sut.get());
        sut.setShowBar(true);
        
        sut.setShowRatio(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51% [==========>         ] at 5.2B/s - ETA: 00:15:43",
                sut.get());
        sut.setShowRatio(true);
        
        sut.setShowSpeed(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51% [==========>         ]  5,100B/10,000B - ETA: 00:15:43",
                sut.get());
        sut.setShowSpeed(true);
        
        sut.setShowTimeRemaining(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51% [==========>         ]  5,100B/10,000B at 5.2B/s",
                sut.get());
        sut.setShowTimeRemaining(true);
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43",
                sut.get());
        
        sut.setShowSpeed(false);
        sut.setShowTimeRemaining(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51% [==========>         ]  5,100B/10,000B",
                sut.get());
        sut.setShowSpeed(true);
        sut.setShowTimeRemaining(true);
        
        sut.setShowBar(false);
        sut.setShowRatio(false);
        sut.setShowSpeed(false);
        sut.setShowTimeRemaining(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51%",
                sut.get());
        sut.setShowBar(true);
        sut.setShowRatio(true);
        sut.setShowSpeed(true);
        sut.setShowTimeRemaining(true);
        
        sut.setShowPercentage(false);
        sut.setShowSpeed(false);
        sut.setShowTimeRemaining(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                "[==========>         ]  5,100B/10,000B",
                sut.get());
        sut.setShowPercentage(true);
        sut.setShowSpeed(true);
        sut.setShowTimeRemaining(true);
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                " 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43",
                sut.get());
    }
    
    /**
     * JUnit test of getPrintable.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getPrintable()
     */
    @Test
    public void testGetPrintable() throws Exception {
        Mockito.when(sut.buildPercentageString()).thenReturn(" 51%");
        Mockito.when(sut.buildBarString()).thenReturn("[==========>         ]");
        Mockito.when(sut.buildRatioString()).thenReturn(" 5,100B/10,000B");
        Mockito.when(sut.buildSpeedString()).thenReturn("at 5.2B/s");
        Mockito.when(sut.buildTimeRemainingString()).thenReturn("ETA: 00:15:43");
        
        final BiFunction<String, Integer, String> printableBarFormatter = (String bar, Integer spaces) ->
                '\r' + bar + ' ' + StringUtility.spaces(spaces) + ProgressBar.ENDCAP;
        
        //initial
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Assert.assertEquals(
                printableBarFormatter.apply("", 0),
                sut.getPrintable());
        
        //standard
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43", 0),
                sut.getPrintable());
        
        //done
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        PowerMockito.doReturn(true).when(sut, "isDone");
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ]  5,100B/10,000B - ETA: 00:15:43", 9),
                sut.getPrintable());
        PowerMockito.doCallRealMethod().when(sut, "isDone");
        
        //indent
        
        TestAccess.setFieldValue(sut, "indent", 4);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply("     51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43", 0),
                sut.getPrintable());
        
        TestAccess.setFieldValue(sut, "indent", 0);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43", 3),
                sut.getPrintable());
        
        //no update
        
        Mockito.when(sut.buildPercentageString()).thenReturn(" 52%");
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43", 0),
                sut.getPrintable());
        Mockito.when(sut.buildPercentageString()).thenReturn(" 51%");
        
        //partial
        
        sut.setShowPercentage(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply("[==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43", 4),
                sut.getPrintable());
        sut.setShowPercentage(true);
        
        sut.setShowBar(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51%  5,100B/10,000B at 5.2B/s - ETA: 00:15:43", 17),
                sut.getPrintable());
        sut.setShowBar(true);
        
        sut.setShowRatio(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ] at 5.2B/s - ETA: 00:15:43", 0),
                sut.getPrintable());
        sut.setShowRatio(true);
        
        sut.setShowSpeed(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ]  5,100B/10,000B - ETA: 00:15:43", 0),
                sut.getPrintable());
        sut.setShowSpeed(true);
        
        sut.setShowTimeRemaining(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ]  5,100B/10,000B at 5.2B/s", 5),
                sut.getPrintable());
        sut.setShowTimeRemaining(true);
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43", 0),
                sut.getPrintable());
        
        sut.setShowSpeed(false);
        sut.setShowTimeRemaining(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ]  5,100B/10,000B", 25),
                sut.getPrintable());
        sut.setShowSpeed(true);
        sut.setShowTimeRemaining(true);
        
        sut.setShowBar(false);
        sut.setShowRatio(false);
        sut.setShowSpeed(false);
        sut.setShowTimeRemaining(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51%", 38),
                sut.getPrintable());
        sut.setShowBar(true);
        sut.setShowRatio(true);
        sut.setShowSpeed(true);
        sut.setShowTimeRemaining(true);
        
        sut.setShowPercentage(false);
        sut.setShowSpeed(false);
        sut.setShowTimeRemaining(false);
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply("[==========>         ]  5,100B/10,000B", 0),
                sut.getPrintable());
        sut.setShowPercentage(true);
        sut.setShowSpeed(true);
        sut.setShowTimeRemaining(true);
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        Assert.assertEquals(
                printableBarFormatter.apply(" 51% [==========>         ]  5,100B/10,000B at 5.2B/s - ETA: 00:15:43", 0),
                sut.getPrintable());
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
        List<Long> rollingProgress = TestAccess.getFieldValue(sut, List.class, "rollingProgress");
        List<Long> rollingUpdate = TestAccess.getFieldValue(sut, List.class, "rollingUpdate");
        
        //default auto print
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "title", "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.eq(0L), ArgumentMatchers.eq(false));
        
        sut.setAutoPrint(false);
        Assert.assertTrue(sut.update(0L));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("update", ArgumentMatchers.eq(0L), ArgumentMatchers.eq(false));
        
        sut.setAutoPrint(true);
        Assert.assertTrue(sut.update(0L));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("update", ArgumentMatchers.eq(0L), ArgumentMatchers.eq(true));
        
        //first update, no title
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "progress", 0);
        TestAccess.setFieldValue(sut, "previous", 0);
        TestAccess.setFieldValue(sut, "current", 0);
        TestAccess.setFieldValue(sut, "firstUpdate", 0);
        TestAccess.setFieldValue(sut, "previousUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        sut.setAutoPrint(false);
        TestAccess.setFieldValue(sut, "title", "");
        Assert.assertEquals(0L, sut.getFirstUpdate());
        Assert.assertTrue(sut.update(1000));
        Assert.assertNotEquals(0L, sut.getFirstUpdate());
        Mockito.verify(sut, VerificationModeFactory.times(0)).buildTitleString();
        
        //first update
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "progress", 0);
        TestAccess.setFieldValue(sut, "previous", 0);
        TestAccess.setFieldValue(sut, "current", 0);
        TestAccess.setFieldValue(sut, "firstUpdate", 0);
        TestAccess.setFieldValue(sut, "previousUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        sut.setAutoPrint(false);
        TestAccess.setFieldValue(sut, "title", "Test Bar");
        
        Assert.assertEquals(0L, sut.getFirstUpdate());
        Assert.assertTrue(sut.update(2000));
        Assert.assertNotEquals(0L, sut.getFirstUpdate());
        Assert.assertEquals(2000L, sut.getProgress());
        Assert.assertEquals(0L, sut.getPrevious());
        Assert.assertEquals(2000L, sut.getCurrent());
        Assert.assertEquals(0L, sut.getPreviousUpdate());
        Assert.assertNotEquals(0L, sut.getCurrentUpdate());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        
        //multiple updates
        
        TestAccess.setFieldValue(sut, "progress", 0);
        TestAccess.setFieldValue(sut, "previous", 0);
        TestAccess.setFieldValue(sut, "current", 0);
        TestAccess.setFieldValue(sut, "firstUpdate", 0);
        TestAccess.setFieldValue(sut, "previousUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        sut.setAutoPrint(false);
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Assert.assertTrue(sut.update(1000));
        Assert.assertEquals(1000L, sut.getProgress());
        Assert.assertEquals(1000L, sut.getCurrent());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Assert.assertFalse(sut.update(2000));
        Assert.assertEquals(2000L, sut.getProgress());
        Assert.assertEquals(1000L, sut.getCurrent());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Thread.sleep((long) (ProgressBar.MINIMUM_UPDATE_DELAY * 1.2));
        Assert.assertTrue(sut.update(3000));
        Assert.assertEquals(3000L, sut.getProgress());
        Assert.assertEquals(3000L, sut.getCurrent());
        Assert.assertEquals(2, rollingProgress.size());
        Assert.assertEquals(2, rollingUpdate.size());
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Thread.sleep((long) (ProgressBar.MINIMUM_UPDATE_DELAY * 0.2));
        Assert.assertFalse(sut.update(4000));
        Assert.assertEquals(4000L, sut.getProgress());
        Assert.assertEquals(3000L, sut.getCurrent());
        Assert.assertEquals(2, rollingProgress.size());
        Assert.assertEquals(2, rollingUpdate.size());
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Thread.sleep(ProgressBar.MINIMUM_UPDATE_DELAY);
        Assert.assertTrue(sut.update(5000));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertEquals(3, rollingProgress.size());
        Assert.assertEquals(3, rollingUpdate.size());
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Assert.assertTrue(sut.update(20000));
        Assert.assertEquals(10000L, sut.getProgress());
        Assert.assertEquals(10000L, sut.getCurrent());
        Assert.assertEquals(4, rollingProgress.size());
        Assert.assertEquals(4, rollingUpdate.size());
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        Assert.assertFalse(sut.update(21000));
        Assert.assertEquals(10000L, sut.getProgress());
        Assert.assertEquals(10000L, sut.getCurrent());
        Assert.assertEquals(4, rollingProgress.size());
        Assert.assertEquals(4, rollingUpdate.size());
        
        //autoprint
        
        Mockito.verify(sut, VerificationModeFactory.times(1)).print();
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "progress", 0);
        TestAccess.setFieldValue(sut, "previous", 0);
        TestAccess.setFieldValue(sut, "current", 0);
        TestAccess.setFieldValue(sut, "firstUpdate", 0);
        TestAccess.setFieldValue(sut, "previousUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        sut.setAutoPrint(true);
        
        Assert.assertTrue(sut.update(1000));
        Assert.assertEquals(1000L, sut.getProgress());
        Assert.assertEquals(1000L, sut.getCurrent());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Mockito.verify(sut, VerificationModeFactory.times(2)).print();
        
        Assert.assertFalse(sut.update(2000));
        Assert.assertEquals(2000L, sut.getProgress());
        Assert.assertEquals(1000L, sut.getCurrent());
        Assert.assertEquals(1, rollingProgress.size());
        Assert.assertEquals(1, rollingUpdate.size());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Mockito.verify(sut, VerificationModeFactory.times(2)).print();
        
        Thread.sleep((long) (ProgressBar.MINIMUM_UPDATE_DELAY * 1.2));
        Assert.assertTrue(sut.update(3000));
        Assert.assertEquals(3000L, sut.getProgress());
        Assert.assertEquals(3000L, sut.getCurrent());
        Assert.assertEquals(2, rollingProgress.size());
        Assert.assertEquals(2, rollingUpdate.size());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Mockito.verify(sut, VerificationModeFactory.times(3)).print();
        
        Thread.sleep((long) (ProgressBar.MINIMUM_UPDATE_DELAY * 0.2));
        Assert.assertFalse(sut.update(4000));
        Assert.assertEquals(4000L, sut.getProgress());
        Assert.assertEquals(3000L, sut.getCurrent());
        Assert.assertEquals(2, rollingProgress.size());
        Assert.assertEquals(2, rollingUpdate.size());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Mockito.verify(sut, VerificationModeFactory.times(3)).print();
        
        Thread.sleep(ProgressBar.MINIMUM_UPDATE_DELAY);
        Assert.assertTrue(sut.update(5000));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertEquals(3, rollingProgress.size());
        Assert.assertEquals(3, rollingUpdate.size());
        
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Mockito.verify(sut, VerificationModeFactory.times(4)).print();
        Assert.assertTrue(sut.update(20000));
        Assert.assertEquals(10000L, sut.getProgress());
        Assert.assertEquals(10000L, sut.getCurrent());
        Assert.assertEquals(4, rollingProgress.size());
        Assert.assertEquals(4, rollingUpdate.size());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Mockito.verify(sut, VerificationModeFactory.times(5)).print();
        
        Assert.assertFalse(sut.update(21000));
        Assert.assertEquals(10000L, sut.getProgress());
        Assert.assertEquals(10000L, sut.getCurrent());
        Assert.assertEquals(4, rollingProgress.size());
        Assert.assertEquals(4, rollingUpdate.size());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Mockito.verify(sut, VerificationModeFactory.times(5)).print();
        
        //done
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "progress", 0);
        TestAccess.setFieldValue(sut, "previous", 0);
        TestAccess.setFieldValue(sut, "current", 0);
        TestAccess.setFieldValue(sut, "firstUpdate", 0);
        TestAccess.setFieldValue(sut, "previousUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        sut.setAutoPrint(false);
        
        PowerMockito.doReturn(true).when(sut, "progressComplete");
        Assert.assertFalse(sut.update(10000));
        PowerMockito.doCallRealMethod().when(sut, "progressComplete");
        
        //failed
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "progress", 0);
        TestAccess.setFieldValue(sut, "previous", 0);
        TestAccess.setFieldValue(sut, "current", 0);
        TestAccess.setFieldValue(sut, "firstUpdate", 0);
        TestAccess.setFieldValue(sut, "previousUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        sut.setAutoPrint(false);
        
        PowerMockito.doReturn(true).when(sut, "isFailed");
        Assert.assertFalse(sut.update(10000));
        PowerMockito.doCallRealMethod().when(sut, "isFailed");
        
        //no premature complete
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "progress", 0);
        TestAccess.setFieldValue(sut, "previous", 0);
        TestAccess.setFieldValue(sut, "current", 0);
        TestAccess.setFieldValue(sut, "firstUpdate", 0);
        TestAccess.setFieldValue(sut, "previousUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        rollingProgress.clear();
        rollingUpdate.clear();
        sut.setAutoPrint(false);
        
        Assert.assertEquals(0L, sut.getCurrent());
        Assert.assertTrue(sut.update(10000));
        Assert.assertEquals(10000L, sut.getCurrent());
        Assert.assertFalse(sut.update(10001));
        Assert.assertEquals(10000L, sut.getCurrent());
        Assert.assertFalse(sut.update(10002));
        Assert.assertEquals(10000L, sut.getCurrent());
        
        //full example, complete
        
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "progress", 0);
        TestAccess.setFieldValue(sut, "previous", 0);
        TestAccess.setFieldValue(sut, "current", 0);
        TestAccess.setFieldValue(sut, "firstUpdate", 0);
        TestAccess.setFieldValue(sut, "previousUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        rollingProgress.clear();
        rollingUpdate.clear();
        sut.setAutoPrint(true);
        sut.setShowSpeed(false);
        
        for (int i = 0; i <= 10000; i += 512) {
            sut.update(i);
            Thread.sleep(250);
        }
        sut.complete(false);
        
        List<String> completedLines = StringUtility.splitLines(out.toString()
                        .replace("\r", "\r\n").replace("\n\n", "\n"))
                .stream()
                .filter(e -> !e.isEmpty() && !StringUtility.isWhitespace(e))
                .map(StringUtility::removeConsoleEscapeCharacters)
                .map(StringUtility::rTrim)
                .collect(Collectors.toList());
        Assert.assertEquals(22, completedLines.size());
        Assert.assertEquals("Test Bar:", completedLines.get(0));
        Assert.assertEquals("  0% [>                   ]      0B/10,000B - ETA: --:--:--", completedLines.get(1));
        Assert.assertEquals("  5% [=>                  ]    512B/10,000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(2), 1));
        Assert.assertEquals(" 10% [==>                 ]  1,024B/10,000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(3), 1));
        Assert.assertEquals(" 15% [===>                ]  1,536B/10,000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(4), 1));
        Assert.assertEquals(" 20% [====>               ]  2,048B/10,000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(5), 1));
        Assert.assertEquals(" 25% [=====>              ]  2,560B/10,000B - ETA: 00:00:03", completedLines.get(6));
        Assert.assertEquals(" 30% [======>             ]  3,072B/10,000B - ETA: 00:00:03", completedLines.get(7));
        Assert.assertEquals(" 35% [=======>            ]  3,584B/10,000B - ETA: 00:00:03", completedLines.get(8));
        Assert.assertEquals(" 40% [========>           ]  4,096B/10,000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(9), 1));
        Assert.assertEquals(" 46% [=========>          ]  4,608B/10,000B - ETA: 00:00:02", completedLines.get(10));
        Assert.assertEquals(" 51% [==========>         ]  5,120B/10,000B - ETA: 00:00:02", completedLines.get(11));
        Assert.assertEquals(" 56% [===========>        ]  5,632B/10,000B - ETA: 00:00:02", completedLines.get(12));
        Assert.assertEquals(" 61% [============>       ]  6,144B/10,000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(13), 1));
        Assert.assertEquals(" 66% [=============>      ]  6,656B/10,000B - ETA: 00:00:01", completedLines.get(14));
        Assert.assertEquals(" 71% [==============>     ]  7,168B/10,000B - ETA: 00:00:01", completedLines.get(15));
        Assert.assertEquals(" 76% [===============>    ]  7,680B/10,000B - ETA: 00:00:01", completedLines.get(16));
        Assert.assertEquals(" 81% [================>   ]  8,192B/10,000B - ETA: 00:00:0", StringUtility.rShear(completedLines.get(17), 1));
        Assert.assertEquals(" 87% [=================>  ]  8,704B/10,000B - ETA: 00:00:00", completedLines.get(18));
        Assert.assertEquals(" 92% [==================> ]  9,216B/10,000B - ETA: 00:00:00", completedLines.get(19));
        Assert.assertEquals(" 97% [===================>]  9,728B/10,000B - ETA: 00:00:00", completedLines.get(20));
        Assert.assertEquals("100% [====================] 10,000B/10,000B - Complete", completedLines.get(21));
        
        sut.setAutoPrint(false);
        System.setOut(saveOut);
        
        //full, fail
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        
        TestAccess.setFieldValue(sut, "progress", 0);
        TestAccess.setFieldValue(sut, "previous", 0);
        TestAccess.setFieldValue(sut, "current", 0);
        TestAccess.setFieldValue(sut, "firstUpdate", 0);
        TestAccess.setFieldValue(sut, "previousUpdate", 0);
        TestAccess.setFieldValue(sut, "currentUpdate", 0);
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        rollingProgress.clear();
        rollingUpdate.clear();
        sut.setAutoPrint(true);
        sut.setShowSpeed(false);
        
        for (int i = 0; i <= 5000; i += 512) {
            sut.update(i);
            Thread.sleep(250);
        }
        sut.fail(false);
        sut.update(10000);
        
        List<String> failedLines = StringUtility.splitLines(out.toString()
                        .replace("\r", "\r\n").replace("\n\n", "\n"))
                .stream()
                .filter(e -> !e.isEmpty() && !StringUtility.isWhitespace(e))
                .map(StringUtility::removeConsoleEscapeCharacters)
                .map(StringUtility::rTrim)
                .collect(Collectors.toList());
        Assert.assertEquals(12, failedLines.size());
        Assert.assertEquals("Test Bar:", failedLines.get(0));
        Assert.assertEquals("  0% [>                   ]      0B/10,000B - ETA: --:--:--", failedLines.get(1));
        Assert.assertEquals("  5% [=>                  ]    512B/10,000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(2), 1));
        Assert.assertEquals(" 10% [==>                 ]  1,024B/10,000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(3), 1));
        Assert.assertEquals(" 15% [===>                ]  1,536B/10,000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(4), 1));
        Assert.assertEquals(" 20% [====>               ]  2,048B/10,000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(5), 1));
        Assert.assertEquals(" 25% [=====>              ]  2,560B/10,000B - ETA: 00:00:03", failedLines.get(6));
        Assert.assertEquals(" 30% [======>             ]  3,072B/10,000B - ETA: 00:00:03", failedLines.get(7));
        Assert.assertEquals(" 35% [=======>            ]  3,584B/10,000B - ETA: 00:00:03", failedLines.get(8));
        Assert.assertEquals(" 40% [========>           ]  4,096B/10,000B - ETA: 00:00:0", StringUtility.rShear(failedLines.get(9), 1));
        Assert.assertEquals(" 46% [=========>          ]  4,608B/10,000B - ETA: 00:00:02", failedLines.get(10));
        Assert.assertEquals(" 46% [=========           ]  4,608B/10,000B - Failed", failedLines.get(11));
        
        sut.setAutoPrint(false);
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
        Assert.assertEquals(0L, sut.getProgress());
        
        sut.addOne();
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .update(ArgumentMatchers.eq(1L));
        Assert.assertEquals(1L, sut.getProgress());
        
        sut.addOne();
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .update(ArgumentMatchers.eq(2L));
        Assert.assertEquals(2L, sut.getProgress());
        
        sut.addOne();
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .update(ArgumentMatchers.eq(3L));
        Assert.assertEquals(3L, sut.getProgress());
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
        //standard
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog("1"));
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog("1%"));
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog("test"));
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog("test\nlog\n1%\n"));
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog(null));
        Assert.assertEquals(0L, sut.getProgress());
        
        //error flag
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog("1", false));
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog("1%", true));
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog("test", true));
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog("test\nlog\n1%\n", false));
        Assert.assertEquals(0L, sut.getProgress());
        Assert.assertFalse(sut.processLog(null, true));
        Assert.assertEquals(0L, sut.getProgress());
        
        //default error flag
        Mockito.verify(sut, VerificationModeFactory.times(0))
                .processLog(ArgumentMatchers.eq("default"), ArgumentMatchers.eq(false));
        Mockito.doReturn(false).when(sut).processLog(ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        Assert.assertFalse(sut.processLog("default"));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .processLog(ArgumentMatchers.eq("default"), ArgumentMatchers.eq(false));
        Mockito.doCallRealMethod().when(sut).processLog(ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
    }
    
    /**
     * JUnit test of print.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#print(String, boolean)
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
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.invokeMethod(sut, "print", "", false);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .getPrintable();
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "  0% [>                   ]      0B/10,000B at 0.0B/s - ETA: --:--:--",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "")));
        Assert.assertFalse(out.toString().contains(" "));
        Assert.assertFalse(out.toString().endsWith(System.lineSeparator()));
        System.setOut(saveOut);
        
        //no update
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.invokeMethod(sut, "print", "", false);
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .getPrintable();
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "  0% [>                   ]      0B/10,000B at 0.0B/s - ETA: --:--:--",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "")));
        Assert.assertFalse(out.toString().contains(" "));
        Assert.assertFalse(out.toString().endsWith(System.lineSeparator()));
        System.setOut(saveOut);
        
        //first print
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "title", "Test Bar");
        TestAccess.invokeMethod(sut, "print", "", false);
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .getPrintable();
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "Test Bar:   0% [>                   ]      0B/10,000B at 0.0B/s - ETA: --:--:--",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "")));
        Assert.assertFalse(out.toString().contains(" "));
        Assert.assertFalse(out.toString().endsWith(System.lineSeparator()));
        System.setOut(saveOut);
        
        //first print, no title
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "title", "");
        TestAccess.invokeMethod(sut, "print", "", false);
        Mockito.verify(sut, VerificationModeFactory.times(4))
                .getPrintable();
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "  0% [>                   ]      0B/10,000B at 0.0B/s - ETA: --:--:--",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "")));
        Assert.assertFalse(out.toString().contains(" "));
        Assert.assertFalse(out.toString().endsWith(System.lineSeparator()));
        System.setOut(saveOut);
        
        //extras
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.invokeMethod(sut, "print", " - Press any key to continue...", false);
        Mockito.verify(sut, VerificationModeFactory.times(5))
                .getPrintable();
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "  0% [>                   ]      0B/10,000B at 0.0B/s - ETA: --:--:-- - Press any key to continue...",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "")));
        Assert.assertFalse(out.toString().contains(" "));
        Assert.assertFalse(out.toString().endsWith(System.lineSeparator()));
        System.setOut(saveOut);
        
        //new line
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.invokeMethod(sut, "print", " - Press any key to continue...", true);
        Mockito.verify(sut, VerificationModeFactory.times(6))
                .getPrintable();
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "  0% [>                   ]      0B/10,000B at 0.0B/s - ETA: --:--:-- - Press any key to continue...",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "")));
        Assert.assertFalse(out.toString().contains(" "));
        Assert.assertTrue(out.toString().endsWith(System.lineSeparator()));
        System.setOut(saveOut);
        
        //title, extras, new line
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "title", "Test Bar");
        TestAccess.invokeMethod(sut, "print", " - Press any key to continue...", true);
        Mockito.verify(sut, VerificationModeFactory.times(7))
                .getPrintable();
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "Test Bar:   0% [>                   ]      0B/10,000B at 0.0B/s - ETA: --:--:-- - Press any key to continue...",
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "")));
        Assert.assertFalse(out.toString().contains(" "));
        Assert.assertTrue(out.toString().endsWith(System.lineSeparator()));
        System.setOut(saveOut);
        
        //default print
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("print", ArgumentMatchers.isNull(), ArgumentMatchers.eq(false));
        PowerMockito.doAnswer(invocationOnMock -> {
            Assert.assertNull(invocationOnMock.getArgument(0));
            Assert.assertEquals(false, invocationOnMock.getArgument(1));
            return null;
        }).when(sut, "print", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean());
        sut.print();
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("print", ArgumentMatchers.isNull(), ArgumentMatchers.eq(false));
        PowerMockito.doCallRealMethod().when(sut, "print", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean());
    }
    
    /**
     * JUnit test of finish.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#finish(boolean, String)
     */
    @Test
    public void testFinish() throws Exception {
        //standard
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.invokeMethod(sut, "finish", false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("print", ArgumentMatchers.eq(""), ArgumentMatchers.eq(true));
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        
        //print time
        Mockito.when(sut.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toMillis(57653L));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.invokeMethod(sut, "finish", true, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("print", ArgumentMatchers.eq(" (16h 0m 53s)"), ArgumentMatchers.eq(true));
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Mockito.when(sut.getTotalDuration()).thenCallRealMethod();
        
        //additional info
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.invokeMethod(sut, "finish", false, "Press any key to continue...");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("print", ArgumentMatchers.eq(" - Press any key to continue..."), ArgumentMatchers.eq(true));
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        
        //print time and additional info
        Mockito.when(sut.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toMillis(57653L));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.invokeMethod(sut, "finish", true, "Press any key to continue...");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("print", ArgumentMatchers.eq(" (16h 0m 53s) - Press any key to continue..."), ArgumentMatchers.eq(true));
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Mockito.when(sut.getTotalDuration()).thenCallRealMethod();
    }
    
    /**
     * JUnit test of getRatio.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getRatio()
     */
    @Test
    public void testGetRatio() throws Exception {
        TestAccess.setFieldValue(sut, "current", 1L);
        TestAccess.setFieldValue(sut, "total", 10L);
        Assert.assertEquals(0.1, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 1000000000L);
        TestAccess.setFieldValue(sut, "total", 10000000000L);
        Assert.assertEquals(0.1, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 8L);
        TestAccess.setFieldValue(sut, "total", 8914156L);
        Assert.assertEquals(8.974489564687896E-7, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 17456122L);
        TestAccess.setFieldValue(sut, "total", 28462154L);
        Assert.assertEquals(0.6133099413347283, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 7499L);
        TestAccess.setFieldValue(sut, "total", 7500L);
        Assert.assertEquals(0.9998666666666667, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 7500L);
        TestAccess.setFieldValue(sut, "total", 7500L);
        Assert.assertEquals(1.0, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 8000L);
        TestAccess.setFieldValue(sut, "total", 7500L);
        Assert.assertEquals(1.0, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 1000L);
        TestAccess.setFieldValue(sut, "total", 1L);
        Assert.assertEquals(1.0, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", -50L);
        TestAccess.setFieldValue(sut, "total", 100L);
        Assert.assertEquals(0.0, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 50L);
        TestAccess.setFieldValue(sut, "total", -100L);
        Assert.assertEquals(1.0, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", -50L);
        TestAccess.setFieldValue(sut, "total", -100L);
        Assert.assertEquals(1.0, sut.getRatio(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(1.0, sut.getRatio(), 0.0000001));
        
        TestAccess.setFieldValue(sut, "current", 1L);
        TestAccess.setFieldValue(sut, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(1.0, sut.getRatio(), 0.0000001));
    }
    
    /**
     * JUnit test of getPercentage.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getPercentage()
     */
    @Test
    public void testGetPercentage() throws Exception {
        TestAccess.setFieldValue(sut, "current", 1L);
        TestAccess.setFieldValue(sut, "total", 10L);
        Assert.assertEquals(10, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", 1000000000L);
        TestAccess.setFieldValue(sut, "total", 10000000000L);
        Assert.assertEquals(10, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", 8L);
        TestAccess.setFieldValue(sut, "total", 8914156L);
        Assert.assertEquals(0, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", 17456122L);
        TestAccess.setFieldValue(sut, "total", 28462154L);
        Assert.assertEquals(61, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", 7499L);
        TestAccess.setFieldValue(sut, "total", 7500L);
        Assert.assertEquals(99, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", 7500L);
        TestAccess.setFieldValue(sut, "total", 7500L);
        Assert.assertEquals(100, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", 8000L);
        TestAccess.setFieldValue(sut, "total", 7500L);
        Assert.assertEquals(100, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", 1000L);
        TestAccess.setFieldValue(sut, "total", 1L);
        Assert.assertEquals(100, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", -50L);
        TestAccess.setFieldValue(sut, "total", 100L);
        Assert.assertEquals(0, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", 50L);
        TestAccess.setFieldValue(sut, "total", -100L);
        Assert.assertEquals(100, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", -50L);
        TestAccess.setFieldValue(sut, "total", -100L);
        Assert.assertEquals(100, sut.getPercentage());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(100, sut.getPercentage()));
        
        TestAccess.setFieldValue(sut, "current", 1L);
        TestAccess.setFieldValue(sut, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(100, sut.getPercentage()));
    }
    
    /**
     * JUnit test of getLastSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getLastSpeed()
     */
    @Test
    public void testGetLastSpeed() throws Exception {
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(10.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 11L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(1.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 11L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.5, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 21L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19650147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.2619047619047619, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 18500L);
        TestAccess.setFieldValue(sut, "previous", 600L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19650147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(426.1904761904762, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "previous", 20L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 0L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "previousUpdate", 0L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", -50L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 50L);
        TestAccess.setFieldValue(sut, "previous", -10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", -50L);
        TestAccess.setFieldValue(sut, "previous", -10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 50L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", -19608147071900L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 50L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", -19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 50L);
        TestAccess.setFieldValue(sut, "previous", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", -19609147071900L);
        TestAccess.setFieldValue(sut, "previousUpdate", -19608147071900L);
        Assert.assertEquals(0.0, sut.getLastSpeed(), 0.0000001);
    }
    
    /**
     * JUnit test of getAverageSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getAverageSpeed()
     */
    @Test
    public void testGetAverageSpeed() throws Exception {
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(20.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(10.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 1500L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(750.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19708147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0.2, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 8754L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19990147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(22.916230366492147, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(10000.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        Assert.assertEquals(5.099400817003212E-4, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        Assert.assertEquals(0.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", -20L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "currentUpdate", -19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        Assert.assertEquals(0.0, sut.getAverageSpeed(), 0.0000001);
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "currentUpdate", -19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        Assert.assertEquals(0.0, sut.getAverageSpeed(), 0.0000001);
    }
    
    /**
     * JUnit test of getRollingAverageSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getRollingAverageSpeed()
     */
    @Test
    public void testGetRollingAverageSpeed() throws Exception {
        List<Long> rollingProgress = TestAccess.getFieldValue(sut, List.class, "rollingProgress");
        List<Long> rollingUpdate = TestAccess.getFieldValue(sut, List.class, "rollingUpdate");
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(1.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 12500000000L, 15000000000L, 17500000000L, 20000000000L));
        Assert.assertEquals(4.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L));
        rollingUpdate.addAll(Arrays.asList(1000000000L, 1250000000L, 1500000000L, 1750000000L, 2000000000L));
        Assert.assertEquals(40.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(104818L, 200048L, 306842L, 401644L, 509872L));
        rollingUpdate.addAll(Arrays.asList(1546138845L, 1610244875L, 1751366975L, 1780469542L, 1955675425L));
        Assert.assertEquals(989054.5064374958, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(0L, 0L, 0L, 0L, 1L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(0.025, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(0L, 0L, 0L, 0L, 0L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(0.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(0L, 0L, 0L, 0L, 0L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(0.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(50L, 40L, 30L, 20L, 1L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L));
        Assert.assertEquals(0.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L));
        rollingUpdate.addAll(Arrays.asList(50000000000L, 40000000000L, 30000000000L, 20000000000L, 10000000000L));
        Assert.assertEquals(0.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(50L, 40L, 30L, 20L, 1L));
        rollingUpdate.addAll(Arrays.asList(50000000000L, 40000000000L, 30000000000L, 20000000000L, 10000000000L));
        Assert.assertEquals(0.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L));
        Assert.assertEquals(0.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(10L, 20L, 30L, 40L, 50L, 60L));
        rollingUpdate.addAll(Arrays.asList(10000000000L, 20000000000L, 30000000000L, 40000000000L, 50000000000L, 60000000000L));
        Assert.assertEquals(0.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
        
        rollingProgress.clear();
        rollingUpdate.clear();
        rollingProgress.addAll(Arrays.asList(0L, 0L, 0L, 0L, 0L));
        rollingUpdate.addAll(Arrays.asList(0L, 0L, 0L, 0L, 0L));
        Assert.assertEquals(0.0, sut.getRollingAverageSpeed(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of getTotalDuration.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getTotalDuration(boolean)
     * @see ProgressBar#getTotalDuration()
     */
    @Test
    public void testGetTotalDuration() throws Exception {
        //standard
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(1000L, sut.getTotalDuration());
        Assert.assertEquals(1000L, sut.getTotalDuration(false));
        Assert.assertEquals(1000000000L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(276659L, sut.getTotalDuration());
        Assert.assertEquals(276659L, sut.getTotalDuration(false));
        Assert.assertEquals(276659323587L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(276659L, sut.getTotalDuration());
        Assert.assertEquals(276659L, sut.getTotalDuration(false));
        Assert.assertEquals(276659323587L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(19609147L, sut.getTotalDuration());
        Assert.assertEquals(19609147L, sut.getTotalDuration(false));
        Assert.assertEquals(19609147071900L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", -19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", -19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(276659L, sut.getTotalDuration());
        Assert.assertEquals(276659L, sut.getTotalDuration(false));
        Assert.assertEquals(276659323587L, sut.getTotalDuration(true));
        
        //initial duration
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 1L);
        Assert.assertEquals(2000L, sut.getTotalDuration());
        Assert.assertEquals(2000L, sut.getTotalDuration(false));
        Assert.assertEquals(2000000000L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 10L);
        Assert.assertEquals(286659L, sut.getTotalDuration());
        Assert.assertEquals(286659L, sut.getTotalDuration(false));
        Assert.assertEquals(286659323587L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 24L);
        Assert.assertEquals(300659L, sut.getTotalDuration());
        Assert.assertEquals(300659L, sut.getTotalDuration(false));
        Assert.assertEquals(300659323587L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        TestAccess.setFieldValue(sut, "initialDuration", 1L);
        Assert.assertEquals(19610147L, sut.getTotalDuration());
        Assert.assertEquals(19610147L, sut.getTotalDuration(false));
        Assert.assertEquals(19610147071900L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 156311L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        TestAccess.setFieldValue(sut, "initialDuration", 100L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", -19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 20000L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", -1L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", -19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", -88L);
        Assert.assertEquals(0L, sut.getTotalDuration());
        Assert.assertEquals(0L, sut.getTotalDuration(false));
        Assert.assertEquals(0L, sut.getTotalDuration(true));
        
        TestAccess.setFieldValue(sut, "currentUpdate", 19884806395487L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", -456877L);
        Assert.assertEquals(276659L, sut.getTotalDuration());
        Assert.assertEquals(276659L, sut.getTotalDuration(false));
        Assert.assertEquals(276659323587L, sut.getTotalDuration(true));
    }
    
    /**
     * JUnit test of getTimeRemaining.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getTimeRemaining(boolean)
     * @see ProgressBar#getTimeRemaining()
     */
    @Test
    public void testGetTimeRemaining() throws Exception {
        //standard
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(499000L, sut.getTimeRemaining());
        Assert.assertEquals(499000L, sut.getTimeRemaining(false));
        Assert.assertEquals(499000000000L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(998000L, sut.getTimeRemaining());
        Assert.assertEquals(998000L, sut.getTimeRemaining(false));
        Assert.assertEquals(998000000000L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 1500L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(11333L, sut.getTimeRemaining());
        Assert.assertEquals(11333L, sut.getTimeRemaining(false));
        Assert.assertEquals(11333333333L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19708147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(49900000L, sut.getTimeRemaining());
        Assert.assertEquals(49900000L, sut.getTimeRemaining(false));
        Assert.assertEquals(49900000000000L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 8754L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19990147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(54371L, sut.getTimeRemaining());
        Assert.assertEquals(54371L, sut.getTimeRemaining(false));
        Assert.assertEquals(54371944254L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0L, sut.getTimeRemaining());
        Assert.assertEquals(0L, sut.getTimeRemaining(false));
        Assert.assertEquals(0L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining());
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(false));
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        Assert.assertEquals(19590536924L, sut.getTimeRemaining());
        Assert.assertEquals(19590536924L, sut.getTimeRemaining(false));
        Assert.assertEquals(19590536924828100L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining());
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(false));
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining());
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(false));
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", -20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining());
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(false));
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", -19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining());
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(false));
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining());
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(false));
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", -19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining());
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(false));
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(true));
        
        //initial progress
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(998000L, sut.getTimeRemaining());
        Assert.assertEquals(998000L, sut.getTimeRemaining(false));
        Assert.assertEquals(998000000000L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(1996000L, sut.getTimeRemaining());
        Assert.assertEquals(1996000L, sut.getTimeRemaining(false));
        Assert.assertEquals(1996000000000L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 1500L);
        TestAccess.setFieldValue(sut, "initialProgress", 515L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(17258L, sut.getTimeRemaining());
        Assert.assertEquals(17258L, sut.getTimeRemaining(false));
        Assert.assertEquals(17258883248L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 8L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19708147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(83166666L, sut.getTimeRemaining());
        Assert.assertEquals(83166666L, sut.getTimeRemaining(false));
        Assert.assertEquals(83166666666666L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 8754L);
        TestAccess.setFieldValue(sut, "initialProgress", 1000L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19990147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(61384L, sut.getTimeRemaining());
        Assert.assertEquals(61384L, sut.getTimeRemaining(false));
        Assert.assertEquals(61384059840L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "initialProgress", 5000L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0L, sut.getTimeRemaining());
        Assert.assertEquals(0L, sut.getTimeRemaining(false));
        Assert.assertEquals(0L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "initialProgress", 60L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining());
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(false));
        Assert.assertEquals(Long.MAX_VALUE, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "initialProgress", -5L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(1998000L, sut.getTimeRemaining());
        Assert.assertEquals(1998000L, sut.getTimeRemaining(false));
        Assert.assertEquals(1998000000000L, sut.getTimeRemaining(true));
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "initialProgress", -50L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(1998000L, sut.getTimeRemaining());
        Assert.assertEquals(1998000L, sut.getTimeRemaining(false));
        Assert.assertEquals(1998000000000L, sut.getTimeRemaining(true));
    }
    
    /**
     * JUnit test of getStatusColor.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getStatusColor()
     */
    @Test
    public void testGetStatusColor() throws Exception {
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        Assert.assertEquals(sut.getBaseColor(), sut.getStatusColor());
        
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        Assert.assertEquals(sut.getGoodColor(), sut.getStatusColor());
        
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(sut.getBadColor(), sut.getStatusColor());
        
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(sut.getBadColor(), sut.getStatusColor());
    }
    
    /**
     * JUnit test of isDone.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#isDone()
     */
    @Test
    public void testIsDone() throws Exception {
        Mockito.doReturn(false).when(sut).progressComplete();
        Mockito.doReturn(false).when(sut).isCompleted();
        Mockito.doReturn(false).when(sut).isFailed();
        Assert.assertFalse(TestAccess.invokeMethod(sut, boolean.class, "isDone"));
        
        Mockito.doReturn(true).when(sut).progressComplete();
        Mockito.doReturn(false).when(sut).isCompleted();
        Mockito.doReturn(false).when(sut).isFailed();
        Assert.assertTrue(TestAccess.invokeMethod(sut, boolean.class, "isDone"));
        
        Mockito.doReturn(false).when(sut).progressComplete();
        Mockito.doReturn(true).when(sut).isCompleted();
        Mockito.doReturn(false).when(sut).isFailed();
        Assert.assertTrue(TestAccess.invokeMethod(sut, boolean.class, "isDone"));
        
        Mockito.doReturn(false).when(sut).progressComplete();
        Mockito.doReturn(false).when(sut).isCompleted();
        Mockito.doReturn(true).when(sut).isFailed();
        Assert.assertTrue(TestAccess.invokeMethod(sut, boolean.class, "isDone"));
        
        Mockito.doReturn(true).when(sut).progressComplete();
        Mockito.doReturn(true).when(sut).isCompleted();
        Mockito.doReturn(false).when(sut).isFailed();
        Assert.assertTrue(TestAccess.invokeMethod(sut, boolean.class, "isDone"));
        
        Mockito.doReturn(true).when(sut).progressComplete();
        Mockito.doReturn(false).when(sut).isCompleted();
        Mockito.doReturn(true).when(sut).isFailed();
        Assert.assertTrue(TestAccess.invokeMethod(sut, boolean.class, "isDone"));
        
        Mockito.doReturn(false).when(sut).progressComplete();
        Mockito.doReturn(true).when(sut).isCompleted();
        Mockito.doReturn(true).when(sut).isFailed();
        Assert.assertTrue(TestAccess.invokeMethod(sut, boolean.class, "isDone"));
        
        Mockito.doReturn(true).when(sut).progressComplete();
        Mockito.doReturn(true).when(sut).isCompleted();
        Mockito.doReturn(true).when(sut).isFailed();
        Assert.assertTrue(TestAccess.invokeMethod(sut, boolean.class, "isDone"));
    }
    
    /**
     * JUnit test of progressComplete.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#progressComplete()
     */
    @Test
    public void testProgressComplete() throws Exception {
        TestAccess.setFieldValue(sut, "current", 0L);
        Assert.assertFalse(sut.progressComplete());
        
        TestAccess.setFieldValue(sut, "current", 10L);
        Assert.assertFalse(sut.progressComplete());
        
        TestAccess.setFieldValue(sut, "current", 9999L);
        Assert.assertFalse(sut.progressComplete());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        Assert.assertTrue(sut.progressComplete());
        
        TestAccess.setFieldValue(sut, "current", 10001L);
        Assert.assertTrue(sut.progressComplete());
        
        TestAccess.setFieldValue(sut, "current", 20000L);
        Assert.assertTrue(sut.progressComplete());
        
        TestAccess.setFieldValue(sut, "current", -941L);
        Assert.assertFalse(sut.progressComplete());
    }
    
    /**
     * JUnit test of isCompleted.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#isCompleted()
     */
    @Test
    public void testIsCompleted() throws Exception {
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        Assert.assertFalse(sut.isCompleted());
        
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        Assert.assertTrue(sut.isCompleted());
        
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertFalse(sut.isCompleted());
        
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertTrue(sut.isCompleted());
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
        
        final UnaryOperator<String> completedBarUnformatter = (String formattedBar) ->
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(formattedBar)
                        .replaceAll("[\r\n]", ""));
        
        //standard
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.complete(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(sut.getTotal(), sut.getProgress());
        Assert.assertEquals(sut.getTotal(), sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10,000B/10,000B - Complete",
                completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //not updated
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.complete(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(2))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(2))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(sut.getTotal(), sut.getProgress());
        Assert.assertEquals(sut.getTotal(), sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10,000B/10,000B - Complete",
                completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //autoprint off
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", false);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.complete(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(3))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(3))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(sut.getTotal(), sut.getProgress());
        Assert.assertEquals(sut.getTotal(), sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10,000B/10,000B - Complete",
                completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //print time
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(sut.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toMillis(57653L));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "currentUpdate", 50008147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 120L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.complete(true, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(4))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("finish", ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        Assert.assertEquals(sut.getTotal(), sut.getProgress());
        Assert.assertEquals(sut.getTotal(), sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10,000B/10,000B - Complete (16h 0m 53s)",
                completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        Mockito.when(sut.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        //additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.complete(false, "Press any key to continue...");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(5))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq("Press any key to continue..."));
        Assert.assertEquals(sut.getTotal(), sut.getProgress());
        Assert.assertEquals(sut.getTotal(), sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10,000B/10,000B - Complete - Press any key to continue...",
                completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //print time and additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(sut.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toMillis(57653L));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "currentUpdate", 50008147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 120L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.complete(true, "Press any key to continue...");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(6))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("finish", ArgumentMatchers.eq(true), ArgumentMatchers.eq("Press any key to continue..."));
        Assert.assertEquals(sut.getTotal(), sut.getProgress());
        Assert.assertEquals(sut.getTotal(), sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10,000B/10,000B - Complete (16h 0m 53s) - Press any key to continue...",
                completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        Mockito.when(sut.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        //first print
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "title", "Test Bar");
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.complete(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(7))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(4))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(sut.getTotal(), sut.getProgress());
        Assert.assertEquals(sut.getTotal(), sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "Test Bar: 100% [====================] 10,000B/10,000B - Complete",
                completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //first print, no title
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "title", "");
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.complete(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(8))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(5))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(sut.getTotal(), sut.getProgress());
        Assert.assertEquals(sut.getTotal(), sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "100% [====================] 10,000B/10,000B - Complete",
                completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //already completed
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.complete(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(8))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(5))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals("", completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //already failed
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        sut.complete(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(8))
                .invoke("update", ArgumentMatchers.eq(10000L), ArgumentMatchers.eq(false));
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(5))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals("", completedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //default additional info
        Mockito.verify(sut, VerificationModeFactory.times(7))
                .complete(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .complete(ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        final AtomicBoolean printTime = new AtomicBoolean(true);
        Mockito.doAnswer(invocationOnMock -> {
            Assert.assertEquals(printTime.get(), invocationOnMock.getArgument(0));
            Assert.assertEquals("", invocationOnMock.getArgument(1));
            return null;
        }).when(sut).complete(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        printTime.set(false);
        sut.complete(false);
        Mockito.verify(sut, VerificationModeFactory.times(8))
                .complete(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        printTime.set(true);
        sut.complete(true);
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .complete(ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        Mockito.doCallRealMethod().when(sut).complete(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        
        //default print time
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .complete(ArgumentMatchers.eq(true));
        Mockito.doAnswer(invocationOnMock -> {
            Assert.assertTrue(invocationOnMock.getArgument(0));
            return null;
        }).when(sut).complete(ArgumentMatchers.anyBoolean());
        sut.complete();
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .complete(ArgumentMatchers.eq(true));
        Mockito.doCallRealMethod().when(sut).complete(ArgumentMatchers.anyBoolean());
    }
    
    /**
     * JUnit test of isFailed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#isFailed()
     */
    @Test
    public void testIsFailed() throws Exception {
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        Assert.assertFalse(sut.isFailed());
        
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertTrue(sut.isFailed());
        
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        Assert.assertFalse(sut.isFailed());
        
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertTrue(sut.isFailed());
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
        
        final UnaryOperator<String> failedBarUnformatter = (String formattedBar) ->
                StringUtility.rTrim(StringUtility.removeConsoleEscapeCharacters(formattedBar)
                        .replaceAll("[\r\n]", ""));
        
        //standard
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.fail(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5,000B/10,000B - Failed",
                failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //not updated
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.fail(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(2))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5,000B/10,000B - Failed",
                failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //autoprint off
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", false);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.fail(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(3))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5,000B/10,000B - Failed",
                failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //print time
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(sut.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toMillis(57653L));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "currentUpdate", 50008147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 120L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.fail(true, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("finish", ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5,000B/10,000B - Failed (16h 0m 53s)",
                failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        Mockito.when(sut.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        //additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.fail(false, "Press any key to continue...");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq("Press any key to continue..."));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5,000B/10,000B - Failed - Press any key to continue...",
                failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //print time and additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(sut.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toMillis(57653L));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "currentUpdate", 50008147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "initialDuration", 120L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.fail(true, "Press any key to continue...");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("finish", ArgumentMatchers.eq(true), ArgumentMatchers.eq("Press any key to continue..."));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5,000B/10,000B - Failed (16h 0m 53s) - Press any key to continue...",
                failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        Mockito.when(sut.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        //first print
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "title", "Test Bar");
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.fail(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(4))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                "Test Bar:  50% [==========          ]  5,000B/10,000B - Failed",
                failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //first print, no title
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "title", "");
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.fail(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(5))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals(
                " 50% [==========          ]  5,000B/10,000B - Failed",
                failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //already completed
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(false));
        sut.fail(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(5))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals("", failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //already failed
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        TestAccess.setFieldValue(sut, "update", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "autoPrint", true);
        TestAccess.setFieldValue(sut, "progress", 5000L);
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        sut.fail(false, "");
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(0))
                .invoke("update", ArgumentMatchers.anyLong(), ArgumentMatchers.anyBoolean());
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(5))
                .invoke("finish", ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Assert.assertEquals(5000L, sut.getProgress());
        Assert.assertEquals(5000L, sut.getCurrent());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "completed").get());
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "failed").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "update").get());
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        Assert.assertEquals("", failedBarUnformatter.apply(out.toString()));
        Assert.assertFalse(out.toString().contains(" "));
        System.setOut(saveOut);
        
        //default additional info
        Mockito.verify(sut, VerificationModeFactory.times(7))
                .fail(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .fail(ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        final AtomicBoolean printTime = new AtomicBoolean(true);
        Mockito.doAnswer(invocationOnMock -> {
            Assert.assertEquals(printTime.get(), invocationOnMock.getArgument(0));
            Assert.assertEquals("", invocationOnMock.getArgument(1));
            return null;
        }).when(sut).fail(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        printTime.set(false);
        sut.fail(false);
        Mockito.verify(sut, VerificationModeFactory.times(8))
                .fail(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        printTime.set(true);
        sut.fail(true);
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .fail(ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        Mockito.doCallRealMethod().when(sut).fail(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        
        //default print time
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .fail(ArgumentMatchers.eq(true));
        Mockito.doAnswer(invocationOnMock -> {
            Assert.assertTrue(invocationOnMock.getArgument(0));
            return null;
        }).when(sut).fail(ArgumentMatchers.anyBoolean());
        sut.fail();
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .fail(ArgumentMatchers.eq(true));
        Mockito.doCallRealMethod().when(sut).fail(ArgumentMatchers.anyBoolean());
    }
    
    /**
     * JUnit test of buildTitleString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#buildTitleString()
     */
    @Test
    public void testBuildTitleString() throws Exception {
        final UnaryOperator<String> titleFormatter = (String title) ->
                sut.getGoodColor().apply(title + (StringUtility.isNullOrBlank(title) ? "" : ": "));
        
        //standard
        
        TestAccess.setFieldValue(sut, "title", "Test Bar");
        Assert.assertEquals(
                titleFormatter.apply("Test Bar"),
                sut.buildTitleString());
        
        //invalid
        
        TestAccess.setFieldValue(sut, "title", "");
        Assert.assertEquals("", sut.buildTitleString());
        
        TestAccess.setFieldValue(sut, "title", "    ");
        Assert.assertEquals("", sut.buildTitleString());
        
        TestAccess.setFieldValue(sut, "title", null);
        Assert.assertEquals("", sut.buildTitleString());
        
        TestAccess.setFieldValue(sut, "title", "Test Bar");
    }
    
    /**
     * JUnit test of buildPercentageString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#buildPercentageString()
     */
    @Test
    public void testBuildPercentageString() throws Exception {
        final Function<Integer, String> percentageFormatter = (Integer percentage) ->
                sut.getStatusColor().apply(StringUtility.padLeft(String.valueOf(percentage), 3)) + '%';
        
        //standard
        
        TestAccess.setFieldValue(sut, "current", 5000L);
        Assert.assertEquals(
                percentageFormatter.apply(50),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 7784L);
        Assert.assertEquals(
                percentageFormatter.apply(77),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 9999L);
        Assert.assertEquals(
                percentageFormatter.apply(99),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        Assert.assertEquals(
                percentageFormatter.apply(100),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 20000L);
        Assert.assertEquals(
                percentageFormatter.apply(100),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 1L);
        Assert.assertEquals(
                percentageFormatter.apply(0),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        Assert.assertEquals(
                percentageFormatter.apply(0),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", -941L);
        Assert.assertEquals(
                percentageFormatter.apply(0),
                sut.buildPercentageString());
        
        //done
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        Assert.assertEquals(
                percentageFormatter.apply(0),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 7784L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        Assert.assertEquals(
                percentageFormatter.apply(77),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        Assert.assertEquals(
                percentageFormatter.apply(100),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                percentageFormatter.apply(0),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                percentageFormatter.apply(0),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 7784L);
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                percentageFormatter.apply(77),
                sut.buildPercentageString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                percentageFormatter.apply(100),
                sut.buildPercentageString());
    }
    
    /**
     * JUnit test of buildBarString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#buildBarString()
     */
    @Test
    public void testBuildBarString() throws Exception {
        final UnaryOperator<String> barFormatter = (String bar) ->
                '[' + sut.getStatusColor().apply(
                        StringUtility.padRight(bar, sut.getWidth())) + ']';
        
        //standard
        
        TestAccess.setFieldValue(sut, "current", 5000L);
        Assert.assertEquals(
                barFormatter.apply("==========>"),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 7784L);
        Assert.assertEquals(
                barFormatter.apply("===============>"),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 9999L);
        Assert.assertEquals(
                barFormatter.apply("===================>"),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        Assert.assertEquals(
                barFormatter.apply("===================="),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 20000L);
        Assert.assertEquals(
                barFormatter.apply("===================="),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 1L);
        Assert.assertEquals(
                barFormatter.apply(">"),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        Assert.assertEquals(
                barFormatter.apply(">"),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", -941L);
        Assert.assertEquals(
                barFormatter.apply(">"),
                sut.buildBarString());
        
        //done
        
        TestAccess.setFieldValue(sut, "current", 9999L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        Assert.assertEquals(
                barFormatter.apply("==================="),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        Assert.assertEquals(
                barFormatter.apply("===================="),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 20000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        Assert.assertEquals(
                barFormatter.apply("===================="),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                barFormatter.apply(""),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                barFormatter.apply(""),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 7784L);
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                barFormatter.apply("==============="),
                sut.buildBarString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                barFormatter.apply("===================="),
                sut.buildBarString());
    }
    
    /**
     * JUnit test of buildRatioString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#buildRatioString()
     */
    @Test
    public void testBuildRatioString() throws Exception {
        final BiFunction<Long, Boolean, String> ratioFormatter = (Long current, Boolean useCommas) -> {
            final String currentString = useCommas ? ProgressBar.INTEGER_FORMAT.format(current) : String.valueOf(current);
            final String totalString = useCommas ? ProgressBar.INTEGER_FORMAT.format(sut.getTotal()) : String.valueOf(sut.getTotal());
            return sut.getStatusColor().apply(StringUtility.padLeft(currentString, totalString.length())) + sut.getUnits() + '/' +
                    sut.getGoodColor().apply(totalString) + sut.getUnits();
        };
        
        //standard
        
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(5000L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 7784L);
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(7784L, ProgressBar.DEFAULT_USE_COMMAS),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 7784L);
        TestAccess.setFieldValue(sut, "useCommas", false);
        Assert.assertEquals(
                ratioFormatter.apply(7784L, false),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 9999L);
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(9999L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(10000L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "useCommas", false);
        Assert.assertEquals(
                ratioFormatter.apply(10000L, false),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 20000L);
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(10000L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 1L);
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(1L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 1L);
        TestAccess.setFieldValue(sut, "useCommas", false);
        Assert.assertEquals(
                ratioFormatter.apply(1L, false),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(0L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", -941L);
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(0L, true),
                sut.buildRatioString());
        
        //done
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(0L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 7784L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(7784L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(10000L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(0L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(0L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 7784L);
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(7784L, true),
                sut.buildRatioString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                ratioFormatter.apply(10000L, true),
                sut.buildRatioString());
    }
    
    /**
     * JUnit test of buildSpeedString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#buildSpeedString()
     */
    @Test
    public void testBuildSpeedString() throws Exception {
        final BiFunction<Double, Boolean, String> speedFormatter = (Double speed, Boolean useCommas) ->
                "at " + (useCommas ? ProgressBar.DECIMAL_FORMAT.format(speed) : String.format("%.1f", speed)) + sut.getUnits() + "/s";
        
        //standard
        
        Mockito.doReturn(503.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                speedFormatter.apply(503.0, true),
                sut.buildSpeedString());
        
        Mockito.doReturn(9.1219453).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                speedFormatter.apply(9.1, true),
                sut.buildSpeedString());
        
        Mockito.doReturn(107.4945).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                speedFormatter.apply(107.5, true),
                sut.buildSpeedString());
        
        Mockito.doReturn(0.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                speedFormatter.apply(0.0, true),
                sut.buildSpeedString());
        
        Mockito.doReturn(153870.334).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                speedFormatter.apply(153870.3, true),
                sut.buildSpeedString());
        
        Mockito.doReturn(153870.334).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "useCommas", false);
        Assert.assertEquals(
                speedFormatter.apply(153870.3, false),
                sut.buildSpeedString());
        
        Mockito.doReturn(-3870.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals(
                speedFormatter.apply(-3870.0, true),
                sut.buildSpeedString());
        
        Mockito.doReturn(-3870.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "useCommas", false);
        Assert.assertEquals(
                speedFormatter.apply(-3870.0, false),
                sut.buildSpeedString());
        
        //done
        
        Mockito.doReturn(503.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals("", sut.buildSpeedString());
        
        Mockito.doReturn(503.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "current", 7784L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals("", sut.buildSpeedString());
        
        Mockito.doReturn(503.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals("", sut.buildSpeedString());
        
        Mockito.doReturn(503.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals("", sut.buildSpeedString());
        
        Mockito.doReturn(503.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals("", sut.buildSpeedString());
        
        Mockito.doReturn(503.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "current", 7784L);
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals("", sut.buildSpeedString());
        
        Mockito.doReturn(503.0).when(sut).getRollingAverageSpeed();
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertEquals("", sut.buildSpeedString());
    }
    
    /**
     * JUnit test of buildTimeRemainingString.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#buildTimeRemainingString()
     */
    @Test
    public void testBuildTimeRemainingString() throws Exception {
        final UnaryOperator<String> timeRemainingFormatter = (String timeRemaining) ->
                timeRemaining.contains(":") ? ("ETA: " + timeRemaining) :
                sut.getStatusColor().apply(timeRemaining);
        
        //standard
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:08:19"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:16:38"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 1500L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:00:11"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19708147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("13:51:40"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 8754L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19990147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:00:54"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:00:00"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("--:--:--"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("5441:48:56"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("--:--:--"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 0L);
        TestAccess.setFieldValue(sut, "firstUpdate", 0L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("--:--:--"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", -20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("--:--:--"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", -19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("--:--:--"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("--:--:--"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", -19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", -19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("--:--:--"),
                sut.buildTimeRemainingString());
        
        //initial progress
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:16:38"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 10L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:33:16"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 1500L);
        TestAccess.setFieldValue(sut, "initialProgress", 515L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:00:17"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 20L);
        TestAccess.setFieldValue(sut, "initialProgress", 8L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19708147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("23:06:06"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 8754L);
        TestAccess.setFieldValue(sut, "initialProgress", 1000L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19990147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:01:01"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "initialProgress", 5000L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19609147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:00:00"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 0L);
        TestAccess.setFieldValue(sut, "initialProgress", 60L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("--:--:--"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "initialProgress", -5L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:33:18"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 10L);
        TestAccess.setFieldValue(sut, "initialProgress", -50L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        Assert.assertEquals(
                timeRemainingFormatter.apply("00:33:18"),
                sut.buildTimeRemainingString());
        
        //done
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        Assert.assertEquals(
                timeRemainingFormatter.apply("Complete"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(true));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                timeRemainingFormatter.apply("Failed"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 5000L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "completed", new AtomicBoolean(false));
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                timeRemainingFormatter.apply("Failed"),
                sut.buildTimeRemainingString());
        
        TestAccess.setFieldValue(sut, "current", 10000L);
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        TestAccess.setFieldValue(sut, "currentUpdate", 19610147071900L);
        TestAccess.setFieldValue(sut, "firstUpdate", 19608147071900L);
        TestAccess.setFieldValue(sut, "failed", new AtomicBoolean(true));
        Assert.assertEquals(
                timeRemainingFormatter.apply("Failed"),
                sut.buildTimeRemainingString());
    }
    
    /**
     * JUnit test of getTitle.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getTitle()
     */
    @Test
    public void testGetTitle() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals("", sut.getTitle());
        TestAccess.setFieldValue(sut, "title", "test title");
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, sut.getTotal());
        TestAccess.setFieldValue(sut, "total", 87000L);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, sut.getProgress());
        TestAccess.setFieldValue(sut, "progress", 51L);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, sut.getCurrent());
        TestAccess.setFieldValue(sut, "current", 50400L);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, sut.getPrevious());
        TestAccess.setFieldValue(sut, "previous", 11123L);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, sut.getInitialProgress());
        TestAccess.setFieldValue(sut, "initialProgress", 75436L);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, sut.getInitialDuration());
        TestAccess.setFieldValue(sut, "initialDuration", 32554778965L);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, sut.getCurrentUpdate());
        TestAccess.setFieldValue(sut, "currentUpdate", 641L);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, sut.getPreviousUpdate());
        TestAccess.setFieldValue(sut, "previousUpdate", 15462L);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, sut.getFirstUpdate());
        TestAccess.setFieldValue(sut, "firstUpdate", 8745100L);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, sut.getWidth());
        TestAccess.setFieldValue(sut, "width", 163);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals("", sut.getUnits());
        TestAccess.setFieldValue(sut, "units", "test unit");
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_AUTO_PRINT, sut.getAutoPrint());
        TestAccess.setFieldValue(sut, "autoPrint", false);
        Assert.assertFalse(sut.getAutoPrint());
        TestAccess.setFieldValue(sut, "autoPrint", true);
        Assert.assertTrue(sut.getAutoPrint());
    }
    
    /**
     * JUnit test of getUseCommas.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getUseCommas()
     */
    @Test
    public void testGetUseCommas() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_USE_COMMAS, sut.getUseCommas());
        TestAccess.setFieldValue(sut, "useCommas", false);
        Assert.assertFalse(sut.getUseCommas());
        TestAccess.setFieldValue(sut, "useCommas", true);
        Assert.assertTrue(sut.getUseCommas());
    }
    
    /**
     * JUnit test of getIndent.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getIndent()
     */
    @Test
    public void testGetIndent() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0, sut.getIndent());
        TestAccess.setFieldValue(sut, "indent", 40);
        Assert.assertEquals(40, sut.getIndent());
    }
    
    /**
     * JUnit test of getShowPercentage.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getShowPercentage()
     */
    @Test
    public void testGetShowPercentage() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_PERCENTAGE, sut.getShowPercentage());
        TestAccess.setFieldValue(sut, "showPercentage", false);
        Assert.assertFalse(sut.getShowPercentage());
        TestAccess.setFieldValue(sut, "showPercentage", true);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_BAR, sut.getShowBar());
        TestAccess.setFieldValue(sut, "showBar", false);
        Assert.assertFalse(sut.getShowBar());
        TestAccess.setFieldValue(sut, "showBar", true);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_RATIO, sut.getShowRatio());
        TestAccess.setFieldValue(sut, "showRatio", false);
        Assert.assertFalse(sut.getShowRatio());
        TestAccess.setFieldValue(sut, "showRatio", true);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_SPEED, sut.getShowSpeed());
        TestAccess.setFieldValue(sut, "showSpeed", false);
        Assert.assertFalse(sut.getShowSpeed());
        TestAccess.setFieldValue(sut, "showSpeed", true);
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
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_TIME_REMAINING, sut.getShowTimeRemaining());
        TestAccess.setFieldValue(sut, "showTimeRemaining", false);
        Assert.assertFalse(sut.getShowTimeRemaining());
        TestAccess.setFieldValue(sut, "showTimeRemaining", true);
        Assert.assertTrue(sut.getShowTimeRemaining());
    }
    
    /**
     * JUnit test of getBaseColor.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getBaseColor()
     */
    @Test
    public void testGetBaseColor() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_COLOR_BASE, sut.getBaseColor());
        TestAccess.setFieldValue(sut, "baseColor", Console.ConsoleEffect.BLUE);
        Assert.assertEquals(Console.ConsoleEffect.BLUE, sut.getBaseColor());
    }
    
    /**
     * JUnit test of getGoodColor.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getGoodColor()
     */
    @Test
    public void testGetGoodColor() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_COLOR_GOOD, sut.getGoodColor());
        TestAccess.setFieldValue(sut, "goodColor", Console.ConsoleEffect.PURPLE);
        Assert.assertEquals(Console.ConsoleEffect.PURPLE, sut.getGoodColor());
    }
    
    /**
     * JUnit test of getBadColor.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#getBadColor()
     */
    @Test
    public void testGetBadColor() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_COLOR_BAD, sut.getBadColor());
        TestAccess.setFieldValue(sut, "badColor", Console.ConsoleEffect.ORANGE);
        Assert.assertEquals(Console.ConsoleEffect.ORANGE, sut.getBadColor());
    }
    
    /**
     * JUnit test of updateTitle.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#updateTitle(String)
     */
    @Test
    public void testUpdateTitle() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals("", TestAccess.getFieldValue(sut, "title"));
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        
        Assert.assertTrue(sut.updateTitle("Test"));
        Assert.assertEquals("Test", TestAccess.getFieldValue(sut, "title"));
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        
        Assert.assertTrue(sut.updateTitle("Test 2"));
        Assert.assertEquals("Test 2", TestAccess.getFieldValue(sut, "title"));
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(false));
        Assert.assertFalse(sut.updateTitle("Test 3"));
        Assert.assertEquals("Test 2", TestAccess.getFieldValue(sut, "title"));
        Assert.assertFalse(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
        TestAccess.setFieldValue(sut, "firstPrint", new AtomicBoolean(true));
        
        Assert.assertTrue(sut.updateTitle(null));
        Assert.assertEquals("", TestAccess.getFieldValue(sut, "title"));
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "firstPrint").get());
    }
    
    /**
     * JUnit test of updateTotal.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#updateTotal(long)
     */
    @Test
    public void testUpdateTotal() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "total"));
        sut.updateTotal(74461210L);
        Assert.assertEquals(74461210L, TestAccess.getFieldValue(sut, "total"));
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
        sut = new ProgressBar("", 100, "seconds");
        Assert.assertEquals("seconds", TestAccess.getFieldValue(sut, "units"));
        Assert.assertEquals(100L, TestAccess.getFieldValue(sut, "total"));
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "progress"));
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "current"));
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "previous"));
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "initialProgress"));
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(sut, List.class, "rollingProgress"),
                new Long[] {});
        TestAccess.setFieldValue(sut, "progress", 54L);
        TestAccess.setFieldValue(sut, "current", 54L);
        TestAccess.setFieldValue(sut, "previous", 51L);
        TestAccess.setFieldValue(sut, "initialProgress", 10L);
        TestAccess.setFieldValue(sut, "rollingProgress", Arrays.asList(39L, 43L, 46L, 51L, 54L));
        
        sut.updateUnits("s");
        Assert.assertEquals("s", TestAccess.getFieldValue(sut, "units"));
        Assert.assertEquals(100L, TestAccess.getFieldValue(sut, "total"));
        Assert.assertEquals(54L, TestAccess.getFieldValue(sut, "progress"));
        Assert.assertEquals(54L, TestAccess.getFieldValue(sut, "current"));
        Assert.assertEquals(51L, TestAccess.getFieldValue(sut, "previous"));
        Assert.assertEquals(10L, TestAccess.getFieldValue(sut, "initialProgress"));
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(sut, List.class, "rollingProgress"),
                new Long[] {39L, 43L, 46L, 51L, 54L});
        
        sut.updateUnits("ms", 1000);
        Assert.assertEquals("ms", TestAccess.getFieldValue(sut, "units"));
        Assert.assertEquals(100000L, TestAccess.getFieldValue(sut, "total"));
        Assert.assertEquals(54000L, TestAccess.getFieldValue(sut, "progress"));
        Assert.assertEquals(54000L, TestAccess.getFieldValue(sut, "current"));
        Assert.assertEquals(51000L, TestAccess.getFieldValue(sut, "previous"));
        Assert.assertEquals(10000L, TestAccess.getFieldValue(sut, "initialProgress"));
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(sut, List.class, "rollingProgress"),
                new Long[] {39000L, 43000L, 46000L, 51000L, 54000L});
        
        sut.updateUnits(null);
        Assert.assertEquals("", TestAccess.getFieldValue(sut, "units"));
        Assert.assertEquals(100000L, TestAccess.getFieldValue(sut, "total"));
        Assert.assertEquals(54000L, TestAccess.getFieldValue(sut, "progress"));
        Assert.assertEquals(54000L, TestAccess.getFieldValue(sut, "current"));
        Assert.assertEquals(51000L, TestAccess.getFieldValue(sut, "previous"));
        Assert.assertEquals(10000L, TestAccess.getFieldValue(sut, "initialProgress"));
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(sut, List.class, "rollingProgress"),
                new Long[] {39000L, 43000L, 46000L, 51000L, 54000L});
    }
    
    /**
     * JUnit test of defineInitialProgress.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#defineInitialProgress(long)
     */
    @Test
    public void testDefineInitialProgress() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "initialProgress"));
        Assert.assertTrue(sut.defineInitialProgress(74461210L));
        Assert.assertEquals(74461210L, TestAccess.getFieldValue(sut, "initialProgress"));
        Assert.assertFalse(sut.defineInitialProgress(191070334L));
        Assert.assertEquals(74461210L, TestAccess.getFieldValue(sut, "initialProgress"));
        TestAccess.setFieldValue(sut, "initialProgress", 0L);
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "initialProgress"));
        Assert.assertTrue(sut.defineInitialProgress(191070334L));
        Assert.assertEquals(191070334L, TestAccess.getFieldValue(sut, "initialProgress"));
    }
    
    /**
     * JUnit test of defineInitialDuration.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#defineInitialDuration(long)
     */
    @Test
    public void testDefineInitialDuration() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "initialDuration"));
        Assert.assertTrue(sut.defineInitialDuration(158L));
        Assert.assertEquals(158L, TestAccess.getFieldValue(sut, "initialDuration"));
        Assert.assertFalse(sut.defineInitialDuration(3877L));
        Assert.assertEquals(158L, TestAccess.getFieldValue(sut, "initialDuration"));
        TestAccess.setFieldValue(sut, "initialDuration", 0L);
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "initialDuration"));
        Assert.assertTrue(sut.defineInitialDuration(3877L));
        Assert.assertEquals(3877L, TestAccess.getFieldValue(sut, "initialDuration"));
    }
    
    /**
     * JUnit test of setAutoPrint.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setAutoPrint(boolean)
     */
    @Test
    public void testSetAutoPrint() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_AUTO_PRINT, TestAccess.getFieldValue(sut, "autoPrint"));
        sut.setAutoPrint(false);
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "autoPrint"));
        sut.setAutoPrint(true);
        Assert.assertTrue(TestAccess.getFieldValue(sut, boolean.class, "autoPrint"));
    }
    
    /**
     * JUnit test of setUseCommas.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setUseCommas(boolean)
     */
    @Test
    public void testSetUseCommas() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_USE_COMMAS, TestAccess.getFieldValue(sut, "useCommas"));
        sut.setUseCommas(false);
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "useCommas"));
        sut.setUseCommas(true);
        Assert.assertTrue(TestAccess.getFieldValue(sut, boolean.class, "useCommas"));
    }
    
    /**
     * JUnit test of setIndent.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setIndent(int)
     */
    @Test
    public void testSetIndent() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(0, TestAccess.getFieldValue(sut, int.class, "indent").intValue());
        sut.setIndent(40);
        Assert.assertEquals(40, TestAccess.getFieldValue(sut, int.class, "indent").intValue());
        sut.setIndent(0);
        Assert.assertEquals(0, TestAccess.getFieldValue(sut, int.class, "indent").intValue());
    }
    
    /**
     * JUnit test of setShowPercentage.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowPercentage(boolean)
     */
    @Test
    public void testSetShowPercentage() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_PERCENTAGE, TestAccess.getFieldValue(sut, "showPercentage"));
        sut.setShowPercentage(false);
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "showPercentage"));
        sut.setShowPercentage(true);
        Assert.assertTrue(TestAccess.getFieldValue(sut, boolean.class, "showPercentage"));
    }
    
    /**
     * JUnit test of setShowBar.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowBar(boolean)
     */
    @Test
    public void testSetShowBar() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_BAR, TestAccess.getFieldValue(sut, "showBar"));
        sut.setShowBar(false);
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "showBar"));
        sut.setShowBar(true);
        Assert.assertTrue(TestAccess.getFieldValue(sut, boolean.class, "showBar"));
    }
    
    /**
     * JUnit test of setShowRatio.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowRatio(boolean)
     */
    @Test
    public void testSetShowRatio() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_RATIO, TestAccess.getFieldValue(sut, "showRatio"));
        sut.setShowRatio(false);
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "showRatio"));
        sut.setShowRatio(true);
        Assert.assertTrue(TestAccess.getFieldValue(sut, boolean.class, "showRatio"));
    }
    
    /**
     * JUnit test of setShowSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowSpeed(boolean)
     */
    @Test
    public void testSetShowSpeed() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_SPEED, TestAccess.getFieldValue(sut, "showSpeed"));
        sut.setShowSpeed(false);
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "showSpeed"));
        sut.setShowSpeed(true);
        Assert.assertTrue(TestAccess.getFieldValue(sut, boolean.class, "showSpeed"));
    }
    
    /**
     * JUnit test of setShowTimeRemaining.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setShowTimeRemaining(boolean)
     */
    @Test
    public void testSetShowTimeRemaining() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_SHOW_TIME_REMAINING, TestAccess.getFieldValue(sut, "showTimeRemaining"));
        sut.setShowTimeRemaining(false);
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "showTimeRemaining"));
        sut.setShowTimeRemaining(true);
        Assert.assertTrue(TestAccess.getFieldValue(sut, boolean.class, "showTimeRemaining"));
    }
    
    /**
     * JUnit test of setBaseColor.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setBaseColor(Console.ConsoleEffect)
     */
    @Test
    public void testSetBaseColor() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_COLOR_BASE, TestAccess.getFieldValue(sut, "baseColor"));
        sut.setBaseColor(Console.ConsoleEffect.BLUE);
        Assert.assertEquals(Console.ConsoleEffect.BLUE, TestAccess.getFieldValue(sut, "baseColor"));
        sut.setBaseColor(null);
        Assert.assertEquals(Console.ConsoleEffect.BLUE, TestAccess.getFieldValue(sut, "baseColor"));
    }
    
    /**
     * JUnit test of setGoodColor.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setGoodColor(Console.ConsoleEffect)
     */
    @Test
    public void testSetGoodColor() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_COLOR_GOOD, TestAccess.getFieldValue(sut, "goodColor"));
        sut.setGoodColor(Console.ConsoleEffect.PURPLE);
        Assert.assertEquals(Console.ConsoleEffect.PURPLE, TestAccess.getFieldValue(sut, "goodColor"));
        sut.setGoodColor(null);
        Assert.assertEquals(Console.ConsoleEffect.PURPLE, TestAccess.getFieldValue(sut, "goodColor"));
    }
    
    /**
     * JUnit test of setBadColor.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setBadColor(Console.ConsoleEffect)
     */
    @Test
    public void testSetBadColor() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_COLOR_BAD, TestAccess.getFieldValue(sut, "badColor"));
        sut.setBadColor(Console.ConsoleEffect.ORANGE);
        Assert.assertEquals(Console.ConsoleEffect.ORANGE, TestAccess.getFieldValue(sut, "badColor"));
        sut.setBadColor(null);
        Assert.assertEquals(Console.ConsoleEffect.ORANGE, TestAccess.getFieldValue(sut, "badColor"));
    }
    
    /**
     * JUnit test of setColors.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBar#setColors(Console.ConsoleEffect, Console.ConsoleEffect, Console.ConsoleEffect)
     */
    @Test
    public void testSetColors() throws Exception {
        sut = new ProgressBar("", 0);
        Assert.assertEquals(ProgressBar.DEFAULT_COLOR_BASE, TestAccess.getFieldValue(sut, "baseColor"));
        Assert.assertEquals(ProgressBar.DEFAULT_COLOR_GOOD, TestAccess.getFieldValue(sut, "goodColor"));
        Assert.assertEquals(ProgressBar.DEFAULT_COLOR_BAD, TestAccess.getFieldValue(sut, "badColor"));
        sut.setColors(Console.ConsoleEffect.BLUE, Console.ConsoleEffect.PURPLE, Console.ConsoleEffect.ORANGE);
        Assert.assertEquals(Console.ConsoleEffect.BLUE, TestAccess.getFieldValue(sut, "baseColor"));
        Assert.assertEquals(Console.ConsoleEffect.PURPLE, TestAccess.getFieldValue(sut, "goodColor"));
        Assert.assertEquals(Console.ConsoleEffect.ORANGE, TestAccess.getFieldValue(sut, "badColor"));
        sut.setColors(Console.ConsoleEffect.GREEN, null, null);
        Assert.assertEquals(Console.ConsoleEffect.GREEN, TestAccess.getFieldValue(sut, "baseColor"));
        Assert.assertEquals(Console.ConsoleEffect.PURPLE, TestAccess.getFieldValue(sut, "goodColor"));
        Assert.assertEquals(Console.ConsoleEffect.ORANGE, TestAccess.getFieldValue(sut, "badColor"));
        sut.setColors(null, Console.ConsoleEffect.CYAN, null);
        Assert.assertEquals(Console.ConsoleEffect.GREEN, TestAccess.getFieldValue(sut, "baseColor"));
        Assert.assertEquals(Console.ConsoleEffect.CYAN, TestAccess.getFieldValue(sut, "goodColor"));
        Assert.assertEquals(Console.ConsoleEffect.ORANGE, TestAccess.getFieldValue(sut, "badColor"));
        sut.setColors(null, null, Console.ConsoleEffect.RED);
        Assert.assertEquals(Console.ConsoleEffect.GREEN, TestAccess.getFieldValue(sut, "baseColor"));
        Assert.assertEquals(Console.ConsoleEffect.CYAN, TestAccess.getFieldValue(sut, "goodColor"));
        Assert.assertEquals(Console.ConsoleEffect.RED, TestAccess.getFieldValue(sut, "badColor"));
        sut.setColors(null, null, null);
        Assert.assertEquals(Console.ConsoleEffect.GREEN, TestAccess.getFieldValue(sut, "baseColor"));
        Assert.assertEquals(Console.ConsoleEffect.CYAN, TestAccess.getFieldValue(sut, "goodColor"));
        Assert.assertEquals(Console.ConsoleEffect.RED, TestAccess.getFieldValue(sut, "badColor"));
    }
    
}
