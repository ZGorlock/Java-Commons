/*
 * File:    ConsoleProgressBarTest.java
 * Package: commons.console
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.console;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import commons.string.StringUtility;
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
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ConsoleProgressBar.
 *
 * @see ConsoleProgressBar
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ConsoleProgressBar.class, System.class})
public class ConsoleProgressBarTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ConsoleProgressBarTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    ConsoleProgressBar progressBar;
    
    
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
        progressBar = Mockito.spy(ConsoleProgressBar.class);
        Whitebox.setInternalState(progressBar, "title", "Test Bar");
        Whitebox.setInternalState(progressBar, "total", 10000L);
        Whitebox.setInternalState(progressBar, "width", 20);
        Whitebox.setInternalState(progressBar, "units", "B");
        progressBar.setAutoPrint(false);
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
        Assert.assertEquals(200, ConsoleProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY);
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
    public void testConstructors() throws Exception {
        //standard
        progressBar = new ConsoleProgressBar("Test", 1000, 50, "B", false);
        Assert.assertEquals("Test", progressBar.getTitle());
        Assert.assertEquals(1000, progressBar.getTotal());
        Assert.assertEquals(50, progressBar.getWidth());
        Assert.assertEquals("B", progressBar.getUnits());
        Assert.assertFalse(progressBar.getAutoPrint());
        
        //default auto print
        progressBar = new ConsoleProgressBar("Test2", 5000, 55, "kb");
        Assert.assertEquals("Test2", progressBar.getTitle());
        Assert.assertEquals(5000, progressBar.getTotal());
        Assert.assertEquals(55, progressBar.getWidth());
        Assert.assertEquals("kb", progressBar.getUnits());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        
        //default width
        progressBar = new ConsoleProgressBar("Test3", 51000, "MB");
        Assert.assertEquals("Test3", progressBar.getTitle());
        Assert.assertEquals(51000, progressBar.getTotal());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("MB", progressBar.getUnits());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        
        //no units
        progressBar = new ConsoleProgressBar("Test4", 581000, 61);
        Assert.assertEquals("Test4", progressBar.getTitle());
        Assert.assertEquals(581000, progressBar.getTotal());
        Assert.assertEquals(61, progressBar.getWidth());
        Assert.assertEquals("", progressBar.getUnits());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        
        //default width, no units
        progressBar = new ConsoleProgressBar("Test5", 1581000);
        Assert.assertEquals("Test5", progressBar.getTitle());
        Assert.assertEquals(1581000, progressBar.getTotal());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("", progressBar.getUnits());
        Assert.assertEquals(ConsoleProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#get()
     */
    @Test
    public void testGet() throws Exception {
        Mockito.when(progressBar.getPercentageString()).thenReturn(" 51%");
        Mockito.when(progressBar.getBarString()).thenReturn("[==========>         ]");
        Mockito.when(progressBar.getRatioString()).thenReturn(" 5100B/10000B");
        Mockito.when(progressBar.getTimeRemainingString()).thenReturn("ETA: 00:15:43");
        int spaceCount = progressBar.getWidth() + ((progressBar.getTotal() + progressBar.getUnits()).length() * 2) + 30;
        String expected = "\r" + StringUtility.spaces(spaceCount) + "\r" +
                " 51% [==========>         ]  5100B/10000B - ETA: 00:15:43";
        
        //initial
        Whitebox.setInternalState(progressBar, "update", false);
        Assert.assertEquals("", progressBar.get());
        Assert.assertEquals("", Whitebox.getInternalState(progressBar, "progressBar"));
        
        //standard
        Whitebox.setInternalState(progressBar, "update", true);
        Assert.assertEquals(expected, progressBar.get());
        Assert.assertEquals(expected, Whitebox.getInternalState(progressBar, "progressBar"));
        Assert.assertTrue(expected.length() - spaceCount - 2 <= spaceCount);
        
        //no update
        Mockito.when(progressBar.getPercentageString()).thenReturn(" 52%");
        Whitebox.setInternalState(progressBar, "update", false);
        Assert.assertEquals(expected, progressBar.get());
        Assert.assertEquals(expected, Whitebox.getInternalState(progressBar, "progressBar"));
    }
    
    /**
     * JUnit test of update.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#update(long)
     */
    @Test
    public void testUpdate() throws Exception {
        PrintStream saveOut;
        ByteArrayOutputStream out;
        
        //default auto print
        Whitebox.setInternalState(progressBar, "update", true);
        Whitebox.setInternalState(progressBar, "title", "");
        progressBar.setAutoPrint(false);
        progressBar.update(1);
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        progressBar.setAutoPrint(true);
        progressBar.update(2);
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).print();
        progressBar.setAutoPrint(false);
        Whitebox.setInternalState(progressBar, "update", false);
        
        //first update, no title
        Whitebox.setInternalState(progressBar, "progress", 0);
        Whitebox.setInternalState(progressBar, "previous", 0);
        Whitebox.setInternalState(progressBar, "current", 0);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0);
        Whitebox.setInternalState(progressBar, "previousUpdate", 0);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0);
        Whitebox.setInternalState(progressBar, "title", "");
        Assert.assertEquals(0L, progressBar.getFirstUpdate());
        Assert.assertTrue(progressBar.update(1000));
        Assert.assertNotEquals(0L, progressBar.getFirstUpdate());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).getTitleString();
        
        //first update
        Whitebox.setInternalState(progressBar, "progress", 0);
        Whitebox.setInternalState(progressBar, "previous", 0);
        Whitebox.setInternalState(progressBar, "current", 0);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0);
        Whitebox.setInternalState(progressBar, "previousUpdate", 0);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0);
        Whitebox.setInternalState(progressBar, "title", "Test Bar");
        Assert.assertEquals(0L, progressBar.getFirstUpdate());
        Assert.assertTrue(progressBar.update(2000));
        Assert.assertNotEquals(0L, progressBar.getFirstUpdate());
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).getTitleString();
        Assert.assertEquals(2000L, progressBar.getProgress());
        Assert.assertEquals(0L, progressBar.getPrevious());
        Assert.assertEquals(2000L, progressBar.getCurrent());
        Assert.assertEquals(0L, progressBar.getPreviousUpdate());
        Assert.assertNotEquals(0L, progressBar.getCurrentUpdate());
        
        //multiple updates
        Whitebox.setInternalState(progressBar, "progress", 0);
        Whitebox.setInternalState(progressBar, "previous", 0);
        Whitebox.setInternalState(progressBar, "current", 0);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0);
        Whitebox.setInternalState(progressBar, "previousUpdate", 0);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0);
        Assert.assertTrue(progressBar.update(1000));
        Assert.assertEquals(1000L, progressBar.getProgress());
        Assert.assertEquals(1000L, progressBar.getCurrent());
        Whitebox.setInternalState(progressBar, "update", false);
        Assert.assertFalse(progressBar.update(2000));
        Assert.assertEquals(2000L, progressBar.getProgress());
        Assert.assertEquals(1000L, progressBar.getCurrent());
        Whitebox.setInternalState(progressBar, "update", false);
        Thread.sleep((long) (ConsoleProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 1.2));
        Assert.assertTrue(progressBar.update(3000));
        Assert.assertEquals(3000L, progressBar.getProgress());
        Assert.assertEquals(3000L, progressBar.getCurrent());
        Whitebox.setInternalState(progressBar, "update", false);
        Thread.sleep((long) (ConsoleProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 0.2));
        Assert.assertFalse(progressBar.update(4000));
        Assert.assertEquals(4000L, progressBar.getProgress());
        Assert.assertEquals(3000L, progressBar.getCurrent());
        Whitebox.setInternalState(progressBar, "update", false);
        Thread.sleep(ConsoleProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY);
        Assert.assertTrue(progressBar.update(5000));
        Assert.assertEquals(5000L, progressBar.getProgress());
        Assert.assertEquals(5000L, progressBar.getCurrent());
        Whitebox.setInternalState(progressBar, "update", false);
        Assert.assertTrue(progressBar.update(20000));
        Assert.assertEquals(10000L, progressBar.getProgress());
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Whitebox.setInternalState(progressBar, "update", false);
        Assert.assertFalse(progressBar.update(21000));
        Assert.assertEquals(10000L, progressBar.getProgress());
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Whitebox.setInternalState(progressBar, "update", false);
        
        //autoprint
        progressBar.setAutoPrint(true);
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).print();
        Whitebox.setInternalState(progressBar, "progress", 0);
        Whitebox.setInternalState(progressBar, "previous", 0);
        Whitebox.setInternalState(progressBar, "current", 0);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0);
        Whitebox.setInternalState(progressBar, "previousUpdate", 0);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0);
        Assert.assertFalse(progressBar.update(1000));
        Assert.assertEquals(1000L, progressBar.getProgress());
        Assert.assertEquals(1000L, progressBar.getCurrent());
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).print();
        Assert.assertFalse(progressBar.update(2000));
        Assert.assertEquals(2000L, progressBar.getProgress());
        Assert.assertEquals(1000L, progressBar.getCurrent());
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).print();
        Thread.sleep((long) (ConsoleProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 1.2));
        Assert.assertFalse(progressBar.update(3000));
        Assert.assertEquals(3000L, progressBar.getProgress());
        Assert.assertEquals(3000L, progressBar.getCurrent());
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).print();
        Thread.sleep((long) (ConsoleProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 0.2));
        Assert.assertFalse(progressBar.update(4000));
        Assert.assertEquals(4000L, progressBar.getProgress());
        Assert.assertEquals(3000L, progressBar.getCurrent());
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).print();
        Thread.sleep(ConsoleProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY);
        Assert.assertFalse(progressBar.update(5000));
        Assert.assertEquals(5000L, progressBar.getProgress());
        Assert.assertEquals(5000L, progressBar.getCurrent());
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Mockito.verify(progressBar, VerificationModeFactory.times(4)).print();
        Assert.assertFalse(progressBar.update(20000));
        Assert.assertEquals(10000L, progressBar.getProgress());
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Mockito.verify(progressBar, VerificationModeFactory.times(5)).print();
        Assert.assertFalse(progressBar.update(21000));
        Assert.assertEquals(10000L, progressBar.getProgress());
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Mockito.verify(progressBar, VerificationModeFactory.times(5)).print();
        progressBar.setAutoPrint(false);
        
        //complete
        Whitebox.setInternalState(progressBar, "progress", 0);
        Whitebox.setInternalState(progressBar, "previous", 0);
        Whitebox.setInternalState(progressBar, "current", 0);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0);
        Whitebox.setInternalState(progressBar, "previousUpdate", 0);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0);
        Mockito.when(progressBar.isComplete()).thenReturn(true);
        Assert.assertFalse(progressBar.update(10000));
        Mockito.when(progressBar.isComplete()).thenCallRealMethod();
        
        //no premature complete
        Whitebox.setInternalState(progressBar, "progress", 0);
        Whitebox.setInternalState(progressBar, "previous", 0);
        Whitebox.setInternalState(progressBar, "current", 0);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0);
        Whitebox.setInternalState(progressBar, "previousUpdate", 0);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0);
        Assert.assertEquals(0L, progressBar.getCurrent());
        Assert.assertTrue(progressBar.update(10000));
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertFalse(progressBar.update(10001));
        Assert.assertEquals(10000L, progressBar.getCurrent());
        Assert.assertFalse(progressBar.update(10002));
        Assert.assertEquals(10000L, progressBar.getCurrent());
        
        //full
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        progressBar.setAutoPrint(true);
        Whitebox.setInternalState(progressBar, "progress", 0);
        Whitebox.setInternalState(progressBar, "previous", 0);
        Whitebox.setInternalState(progressBar, "current", 0);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0);
        Whitebox.setInternalState(progressBar, "previousUpdate", 0);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0);
        for (int i = 0; i <= 10000; i += 512) {
            progressBar.update(i);
            Thread.sleep(250);
        }
        progressBar.update(10000);
        List<String> lines = StringUtility.splitLines(out.toString()
                .replace("\r", "\r\n").replace("\n\n", "\n"))
                .stream()
                .filter(e -> !e.isEmpty() && !StringUtility.isWhitespace(e))
                .map(StringUtility::removeConsoleEscapeCharacters)
                .collect(Collectors.toList());
        Assert.assertEquals(22, lines.size());
        Assert.assertEquals("Test Bar: ", lines.get(0));
        Assert.assertEquals("  0% [>                   ]     0B/10000B - ETA: --:--:--", lines.get(1));
        Assert.assertEquals("  5% [=>                  ]   512B/10000B - ETA: 00:00:04", lines.get(2));
        Assert.assertEquals(" 10% [==>                 ]  1024B/10000B - ETA: 00:00:04", lines.get(3));
        Assert.assertEquals(" 15% [===>                ]  1536B/10000B - ETA: 00:00:04", lines.get(4));
        Assert.assertEquals(" 20% [====>               ]  2048B/10000B - ETA: 00:00:0", StringUtility.rShear(lines.get(5), 1));
        Assert.assertEquals(" 25% [=====>              ]  2560B/10000B - ETA: 00:00:03", lines.get(6));
        Assert.assertEquals(" 30% [======>             ]  3072B/10000B - ETA: 00:00:03", lines.get(7));
        Assert.assertEquals(" 35% [=======>            ]  3584B/10000B - ETA: 00:00:03", lines.get(8));
        Assert.assertEquals(" 40% [========>           ]  4096B/10000B - ETA: 00:00:0", StringUtility.rShear(lines.get(9), 1));
        Assert.assertEquals(" 46% [=========>          ]  4608B/10000B - ETA: 00:00:02", lines.get(10));
        Assert.assertEquals(" 51% [==========>         ]  5120B/10000B - ETA: 00:00:02", lines.get(11));
        Assert.assertEquals(" 56% [===========>        ]  5632B/10000B - ETA: 00:00:02", lines.get(12));
        Assert.assertEquals(" 61% [============>       ]  6144B/10000B - ETA: 00:00:0", StringUtility.rShear(lines.get(13), 1));
        Assert.assertEquals(" 66% [=============>      ]  6656B/10000B - ETA: 00:00:01", lines.get(14));
        Assert.assertEquals(" 71% [==============>     ]  7168B/10000B - ETA: 00:00:01", lines.get(15));
        Assert.assertEquals(" 76% [===============>    ]  7680B/10000B - ETA: 00:00:01", lines.get(16));
        Assert.assertEquals(" 81% [================>   ]  8192B/10000B - ETA: 00:00:0", StringUtility.rShear(lines.get(17), 1));
        Assert.assertEquals(" 87% [=================>  ]  8704B/10000B - ETA: 00:00:00", lines.get(18));
        Assert.assertEquals(" 92% [==================> ]  9216B/10000B - ETA: 00:00:00", lines.get(19));
        Assert.assertEquals(" 97% [===================>]  9728B/10000B - ETA: 00:00:00", lines.get(20));
        Assert.assertEquals("100% [====================] 10000B/10000B - Complete", lines.get(21));
        progressBar.setAutoPrint(false);
        System.setOut(saveOut);
    }
    
    /**
     * JUnit test of addOne.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#addOne()
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
     * JUnit test of print.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#print()
     */
    @Test
    public void testPrint() throws Exception {
        PrintStream saveOut;
        ByteArrayOutputStream out;
        
        //update
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Whitebox.setInternalState(progressBar, "update", true);
        progressBar.print();
        Assert.assertEquals(progressBar.get(), out.toString());
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).get();
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        System.setOut(saveOut);
        
        //update
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Whitebox.setInternalState(progressBar, "update", false);
        progressBar.print();
        Assert.assertEquals(progressBar.get(), out.toString());
        Mockito.verify(progressBar, VerificationModeFactory.times(4)).get();
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        System.setOut(saveOut);
    }
    
    /**
     * JUnit test of getRatio.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getRatio()
     */
    @Test
    public void testGetRatio() throws Exception {
        Whitebox.setInternalState(progressBar, "current", 1L);
        Whitebox.setInternalState(progressBar, "total", 10L);
        Assert.assertEquals(0.1, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 1000000000L);
        Whitebox.setInternalState(progressBar, "total", 10000000000L);
        Assert.assertEquals(0.1, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 8L);
        Whitebox.setInternalState(progressBar, "total", 8914156L);
        Assert.assertEquals(8.974489564687896E-7, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 17456122L);
        Whitebox.setInternalState(progressBar, "total", 28462154L);
        Assert.assertEquals(0.6133099413347283, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 7499L);
        Whitebox.setInternalState(progressBar, "total", 7500L);
        Assert.assertEquals(0.9998666666666667, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 7500L);
        Whitebox.setInternalState(progressBar, "total", 7500L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 8000L);
        Whitebox.setInternalState(progressBar, "total", 7500L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 1000L);
        Whitebox.setInternalState(progressBar, "total", 1L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", -50L);
        Whitebox.setInternalState(progressBar, "total", 100L);
        Assert.assertEquals(0.0, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 50L);
        Whitebox.setInternalState(progressBar, "total", -100L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", -50L);
        Whitebox.setInternalState(progressBar, "total", -100L);
        Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001));
        
        Whitebox.setInternalState(progressBar, "current", 1L);
        Whitebox.setInternalState(progressBar, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(1.0, progressBar.getRatio(), 0.0000001));
    }
    
    /**
     * JUnit test of getPercentage.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getPercentage()
     */
    @Test
    public void testGetPercentage() throws Exception {
        Whitebox.setInternalState(progressBar, "current", 1L);
        Whitebox.setInternalState(progressBar, "total", 10L);
        Assert.assertEquals(10, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", 1000000000L);
        Whitebox.setInternalState(progressBar, "total", 10000000000L);
        Assert.assertEquals(10, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", 8L);
        Whitebox.setInternalState(progressBar, "total", 8914156L);
        Assert.assertEquals(0, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", 17456122L);
        Whitebox.setInternalState(progressBar, "total", 28462154L);
        Assert.assertEquals(61, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", 7499L);
        Whitebox.setInternalState(progressBar, "total", 7500L);
        Assert.assertEquals(99, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", 7500L);
        Whitebox.setInternalState(progressBar, "total", 7500L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", 8000L);
        Whitebox.setInternalState(progressBar, "total", 7500L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", 1000L);
        Whitebox.setInternalState(progressBar, "total", 1L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", -50L);
        Whitebox.setInternalState(progressBar, "total", 100L);
        Assert.assertEquals(0, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", 50L);
        Whitebox.setInternalState(progressBar, "total", -100L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", -50L);
        Whitebox.setInternalState(progressBar, "total", -100L);
        Assert.assertEquals(100, progressBar.getPercentage());
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(100, progressBar.getPercentage()));
        
        Whitebox.setInternalState(progressBar, "current", 1L);
        Whitebox.setInternalState(progressBar, "total", 0L);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(100, progressBar.getPercentage()));
    }
    
    /**
     * JUnit test of getLastSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getLastSpeed()
     */
    @Test
    public void testGetLastSpeed() throws Exception {
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(10, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 11L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(1, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 11L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.5, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 21L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19650147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0.2619047619047619, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 18500L);
        Whitebox.setInternalState(progressBar, "previous", 600L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19650147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(426.1904761904762, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "previous", 20L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 0L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 0L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", -50L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 50L);
        Whitebox.setInternalState(progressBar, "previous", -10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", -50L);
        Whitebox.setInternalState(progressBar, "previous", -10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 50L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", -19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 50L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", -19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 50L);
        Whitebox.setInternalState(progressBar, "previous", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", -19609147071900L);
        Whitebox.setInternalState(progressBar, "previousUpdate", -19608147071900L);
        Assert.assertEquals(0, progressBar.getLastSpeed(), 0.0000001);
    }
    
    /**
     * JUnit test of getAverageSpeed.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getAverageSpeed()
     */
    @Test
    public void testGetAverageSpeed() throws Exception {
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(20, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(10, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 1500L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(750, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19708147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0.2, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 8754L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19990147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(22.916230366492147, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 10000L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(10000, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0L);
        Assert.assertEquals(5.099400817003212E-4, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", -20L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "currentUpdate", -19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "currentUpdate", -19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals(0, progressBar.getAverageSpeed(), 0.0000001);
    }
    
    /**
     * JUnit test of getTotalDuration.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getTotalDuration()
     */
    @Test
    public void testGetTotalDuration() throws Exception {
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", 0L);
        Assert.assertEquals(1000000000L, progressBar.getTotalDuration());
        
        Whitebox.setInternalState(progressBar, "currentUpdate", 19884806395487L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", 0L);
        Assert.assertEquals(276659323587L, progressBar.getTotalDuration());
        
        Whitebox.setInternalState(progressBar, "currentUpdate", 19884806395487L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", 24L);
        Assert.assertEquals(300659323587L, progressBar.getTotalDuration());
        
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0L);
        Whitebox.setInternalState(progressBar, "initialDuration", 0L);
        Assert.assertEquals(19609147071900L, progressBar.getTotalDuration());
        
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0L);
        Whitebox.setInternalState(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        Whitebox.setInternalState(progressBar, "currentUpdate", -19884806395487L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        Whitebox.setInternalState(progressBar, "currentUpdate", 19884806395487L);
        Whitebox.setInternalState(progressBar, "firstUpdate", -19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        Whitebox.setInternalState(progressBar, "currentUpdate", -19884806395487L);
        Whitebox.setInternalState(progressBar, "firstUpdate", -19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", 0L);
        Assert.assertEquals(0, progressBar.getTotalDuration());
        
        Whitebox.setInternalState(progressBar, "currentUpdate", 19884806395487L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", -456877L);
        Assert.assertEquals(276659323587L, progressBar.getTotalDuration());
    }
    
    /**
     * JUnit test of getTimeRemaining.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getTimeRemaining()
     */
    @Test
    public void testGetTimeRemaining() throws Exception {
        //standard
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(499, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(998, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 1500L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(11, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19708147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(49900, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 8754L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19990147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(54, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 10000L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0L);
        Assert.assertEquals(19590536L, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", -20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", -19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", -19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        //initial progress
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(998, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(1996, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 1500L);
        Whitebox.setInternalState(progressBar, "initialProgress", 515L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(17, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 8L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19708147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(83166, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 8754L);
        Whitebox.setInternalState(progressBar, "initialProgress", 1000L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19990147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(61, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 10000L);
        Whitebox.setInternalState(progressBar, "initialProgress", 5000L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(0, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "initialProgress", 60L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Long.MAX_VALUE, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "initialProgress", -5L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(1998, progressBar.getTimeRemaining());
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "initialProgress", -50L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(1998, progressBar.getTimeRemaining());
    }
    
    /**
     * JUnit test of isComplete.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#isComplete()
     */
    @Test
    public void testIsComplete() throws Exception {
        Whitebox.setInternalState(progressBar, "current", 0L);
        Assert.assertFalse(progressBar.isComplete());
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Assert.assertFalse(progressBar.isComplete());
        
        Whitebox.setInternalState(progressBar, "current", 9999L);
        Assert.assertFalse(progressBar.isComplete());
        
        Whitebox.setInternalState(progressBar, "current", 10000L);
        Assert.assertTrue(progressBar.isComplete());
        
        Whitebox.setInternalState(progressBar, "current", 10001L);
        Assert.assertTrue(progressBar.isComplete());
        
        Whitebox.setInternalState(progressBar, "current", 20000L);
        Assert.assertTrue(progressBar.isComplete());
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
        PrintStream saveOut;
        ByteArrayOutputStream out;
        
        Whitebox.setInternalState(progressBar, "title", "");
        
        //standard
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).get();
        Whitebox.setInternalState(progressBar, "update", true);
        Whitebox.setInternalState(progressBar, "progress", 5000L);
        Whitebox.setInternalState(progressBar, "current", 5000L);
        progressBar.complete(false, "");
        Assert.assertEquals(progressBar.getTotal(), progressBar.getProgress());
        Assert.assertEquals(progressBar.getTotal(), progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).get();
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Assert.assertEquals(
                "100% [====================] 10000B/10000B - Complete",
                StringUtility.trim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll("\\s+", " "))
        );
        System.setOut(saveOut);
        
        //print time
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(progressBar.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toNanos(57653L));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).get();
        Whitebox.setInternalState(progressBar, "update", true);
        Whitebox.setInternalState(progressBar, "progress", 5000L);
        Whitebox.setInternalState(progressBar, "current", 5000L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 50008147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", 120L);
        progressBar.complete(true, "");
        Assert.assertEquals(progressBar.getTotal(), progressBar.getProgress());
        Assert.assertEquals(progressBar.getTotal(), progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).get();
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Assert.assertEquals(
                "100% [====================] 10000B/10000B - Complete (16h 0m 53s)",
                StringUtility.trim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll("\\s+", " "))
        );
        Mockito.when(progressBar.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        //additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).get();
        Whitebox.setInternalState(progressBar, "update", true);
        Whitebox.setInternalState(progressBar, "progress", 5000L);
        Whitebox.setInternalState(progressBar, "current", 5000L);
        progressBar.complete(false, "Press any key to continue...");
        Assert.assertEquals(progressBar.getTotal(), progressBar.getProgress());
        Assert.assertEquals(progressBar.getTotal(), progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).get();
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Assert.assertEquals(
                "100% [====================] 10000B/10000B - Complete - Press any key to continue...",
                StringUtility.trim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll("\\s+", " "))
        );
        System.setOut(saveOut);
        
        //print time and additional info
        saveOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Mockito.when(progressBar.getTotalDuration()).thenReturn(TimeUnit.SECONDS.toNanos(57653L));
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(3)).get();
        Whitebox.setInternalState(progressBar, "update", true);
        Whitebox.setInternalState(progressBar, "progress", 5000L);
        Whitebox.setInternalState(progressBar, "current", 5000L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 50008147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Whitebox.setInternalState(progressBar, "initialDuration", 120L);
        progressBar.complete(true, "Press any key to continue...");
        Assert.assertEquals(progressBar.getTotal(), progressBar.getProgress());
        Assert.assertEquals(progressBar.getTotal(), progressBar.getCurrent());
        Mockito.verify(progressBar, VerificationModeFactory.times(0)).print();
        Mockito.verify(progressBar, VerificationModeFactory.times(4)).get();
        Assert.assertFalse(Whitebox.getInternalState(progressBar, "update"));
        Assert.assertEquals(
                "100% [====================] 10000B/10000B - Complete (16h 0m 53s) - Press any key to continue...",
                StringUtility.trim(StringUtility.removeConsoleEscapeCharacters(out.toString()).replaceAll("[\r\n]", "").replaceAll("\\s+", " "))
        );
        Mockito.when(progressBar.getTotalDuration()).thenCallRealMethod();
        System.setOut(saveOut);
        
        
        //default additional info
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).complete(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
        Mockito.verify(progressBar, VerificationModeFactory.times(1)).complete(ArgumentMatchers.eq(true), ArgumentMatchers.eq(""));
        final AtomicBoolean printTime = new AtomicBoolean(true);
        Mockito.doAnswer(invocationOnMock -> {
            Assert.assertEquals(printTime.get(), invocationOnMock.getArgument(0));
            Assert.assertEquals("", invocationOnMock.getArgument(1));
            return null;
        }).when(progressBar).complete(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        printTime.set(false);
        progressBar.complete(false);
        Mockito.verify(progressBar, VerificationModeFactory.times(2)).complete(ArgumentMatchers.eq(false), ArgumentMatchers.eq(""));
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
     * JUnit test of getTitleString.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getTitleString()
     */
    @Test
    public void testGetTitleString() throws Exception {
        Whitebox.setInternalState(progressBar, "title", "Test Bar");
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("Test Bar: "),
                progressBar.getTitleString()
        );
        
        Whitebox.setInternalState(progressBar, "title", "");
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply(": "),
                progressBar.getTitleString()
        );
        Whitebox.setInternalState(progressBar, "title", "Test Bar");
    }
    
    /**
     * JUnit test of getPercentageString.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getPercentageString()
     */
    @Test
    public void testGetPercentageString() throws Exception {
        Whitebox.setInternalState(progressBar, "current", 5000L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 50") + '%',
                progressBar.getPercentageString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 7784L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 77") + '%',
                progressBar.getPercentageString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 9999L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 99") + '%',
                progressBar.getPercentageString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 10000L);
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("100") + '%',
                progressBar.getPercentageString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 20000L);
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("100") + '%',
                progressBar.getPercentageString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 1L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("  0") + '%',
                progressBar.getPercentageString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("  0") + '%',
                progressBar.getPercentageString()
        );
        
        Whitebox.setInternalState(progressBar, "current", -941L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("  0") + '%',
                progressBar.getPercentageString()
        );
    }
    
    /**
     * JUnit test of getBarString.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getBarString()
     */
    @Test
    public void testGetBarString() throws Exception {
        Whitebox.setInternalState(progressBar, "current", 5000L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply("==========>         ") + ']',
                progressBar.getBarString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 7784L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply("===============>    ") + ']',
                progressBar.getBarString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 9999L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply("===================>") + ']',
                progressBar.getBarString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 10000L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.CYAN.apply("====================") + ']',
                progressBar.getBarString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 20000L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.CYAN.apply("====================") + ']',
                progressBar.getBarString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 1L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply(">                   ") + ']',
                progressBar.getBarString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply(">                   ") + ']',
                progressBar.getBarString()
        );
        
        Whitebox.setInternalState(progressBar, "current", -941L);
        Assert.assertEquals(
                '[' + Console.ConsoleEffect.GREEN.apply(">                   ") + ']',
                progressBar.getBarString()
        );
    }
    
    /**
     * JUnit test of getRatioString.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getRatioString()
     */
    @Test
    public void testGetRatioString() throws Exception {
        Whitebox.setInternalState(progressBar, "current", 5000L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 5000") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 7784L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 7784") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 9999L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply(" 9999") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 10000L);
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("10000") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 20000L);
        Assert.assertEquals(
                Console.ConsoleEffect.CYAN.apply("10000") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 1L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("    1") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("    0") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
        
        Whitebox.setInternalState(progressBar, "current", -941L);
        Assert.assertEquals(
                Console.ConsoleEffect.GREEN.apply("    0") + "B/" + Console.ConsoleEffect.CYAN.apply("10000") + 'B',
                progressBar.getRatioString()
        );
    }
    
    /**
     * JUnit test of getTimeRemainingString.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getTimeRemainingString()
     */
    @Test
    public void testGetTimeRemainingString() throws Exception {
        //standard
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:08:19", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:16:38", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 1500L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:00:11", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19708147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 13:51:40", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 8754L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19990147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:00:54", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 10000L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Console.ConsoleEffect.CYAN.apply("Complete"), progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0L);
        Assert.assertEquals("ETA: 5441:48:56", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 0L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 0L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", -20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", -19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 0L);
        Whitebox.setInternalState(progressBar, "currentUpdate", -19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", -19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        //initial progress
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:16:38", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 10L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:33:16", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 1500L);
        Whitebox.setInternalState(progressBar, "initialProgress", 515L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:00:17", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 20L);
        Whitebox.setInternalState(progressBar, "initialProgress", 8L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19708147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 23:06:06", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 8754L);
        Whitebox.setInternalState(progressBar, "initialProgress", 1000L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19990147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:01:01", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 10000L);
        Whitebox.setInternalState(progressBar, "initialProgress", 5000L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19609147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals(Console.ConsoleEffect.CYAN.apply("Complete"), progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 0L);
        Whitebox.setInternalState(progressBar, "initialProgress", 60L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: --:--:--", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "initialProgress", -5L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:33:18", progressBar.getTimeRemainingString());
        
        Whitebox.setInternalState(progressBar, "current", 10L);
        Whitebox.setInternalState(progressBar, "initialProgress", -50L);
        Whitebox.setInternalState(progressBar, "currentUpdate", 19610147071900L);
        Whitebox.setInternalState(progressBar, "firstUpdate", 19608147071900L);
        Assert.assertEquals("ETA: 00:33:18", progressBar.getTimeRemainingString());
    }
    
    /**
     * JUnit test of getTitle.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getTitle()
     */
    @Test
    public void testGetTitle() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "title", "test title");
        Assert.assertEquals("test title", sut.getTitle());
    }
    
    /**
     * JUnit test of getTotal.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getTotal()
     */
    @Test
    public void testGetTotal() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "total", 87000L);
        Assert.assertEquals(87000L, sut.getTotal());
    }
    
    /**
     * JUnit test of getProgress.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getProgress()
     */
    @Test
    public void testGetProgress() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "progress", 51L);
        Assert.assertEquals(51L, sut.getProgress());
    }
    
    /**
     * JUnit test of getCurrent.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getCurrent()
     */
    @Test
    public void testGetCurrent() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "current", 50400L);
        Assert.assertEquals(50400L, sut.getCurrent());
    }
    
    /**
     * JUnit test of getPrevious.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getPrevious()
     */
    @Test
    public void testGetPrevious() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "previous", 11123L);
        Assert.assertEquals(11123L, sut.getPrevious());
    }
    
    /**
     * JUnit test of getInitialProgress.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getInitialProgress()
     */
    @Test
    public void testGetInitialProgress() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "initialProgress", 75436L);
        Assert.assertEquals(75436L, sut.getInitialProgress());
    }
    
    /**
     * JUnit test of getInitialDuration.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getInitialDuration()
     */
    @Test
    public void testGetInitialDuration() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "initialDuration", 32554778965L);
        Assert.assertEquals(32554778965L, sut.getInitialDuration());
    }
    
    /**
     * JUnit test of getCurrentUpdate.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getCurrentUpdate()
     */
    @Test
    public void testGetCurrentUpdate() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "currentUpdate", 641L);
        Assert.assertEquals(641L, sut.getCurrentUpdate());
    }
    
    /**
     * JUnit test of getPreviousUpdate.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getPreviousUpdate()
     */
    @Test
    public void testGetPreviousUpdate() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "previousUpdate", 15462L);
        Assert.assertEquals(15462L, sut.getPreviousUpdate());
    }
    
    /**
     * JUnit test of getFirstUpdate.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getFirstUpdate()
     */
    @Test
    public void testGetFirstUpdate() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "firstUpdate", 8745100L);
        Assert.assertEquals(8745100L, sut.getFirstUpdate());
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getWidth()
     */
    @Test
    public void testGetWidth() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "width", 163);
        Assert.assertEquals(163, sut.getWidth());
    }
    
    /**
     * JUnit test of getUnits.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getUnits()
     */
    @Test
    public void testGetUnits() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "units", "test unit");
        Assert.assertEquals("test unit", sut.getUnits());
    }
    
    /**
     * JUnit test of getAutoPrint.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#getAutoPrint()
     */
    @Test
    public void testGetAutoPrint() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        Whitebox.setInternalState(sut, "autoPrint", false);
        Assert.assertFalse(sut.getAutoPrint());
        Whitebox.setInternalState(sut, "autoPrint", true);
        Assert.assertTrue(sut.getAutoPrint());
    }
    
    /**
     * JUnit test of setInitialProgress.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#setInitialProgress(long)
     */
    @Test
    public void testSetInitialProgress() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        sut.setInitialProgress(74461210L);
        Assert.assertEquals(74461210L, (long) Whitebox.getInternalState(sut, "initialProgress"));
    }
    
    /**
     * JUnit test of setInitialDuration.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#setInitialDuration(long)
     */
    @Test
    public void testSetInitialDuration() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        sut.setInitialDuration(158L);
        Assert.assertEquals(158L, (long) Whitebox.getInternalState(sut, "initialDuration"));
    }
    
    /**
     * JUnit test of setAutoPrint.
     *
     * @throws Exception When there is an exception.
     * @see ConsoleProgressBar#setAutoPrint(boolean)
     */
    @Test
    public void testSetAutoPrint() throws Exception {
        ConsoleProgressBar sut = new ConsoleProgressBar("", 0);
        sut.setAutoPrint(false);
        Assert.assertFalse(Whitebox.getInternalState(sut, "autoPrint"));
        sut.setAutoPrint(true);
        Assert.assertTrue(Whitebox.getInternalState(sut, "autoPrint"));
    }
    
}
