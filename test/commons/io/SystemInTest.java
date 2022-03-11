/*
 * File:    SystemInTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import commons.io.stream.BufferedLineReader;
import commons.object.collection.MapUtility;
import commons.test.TestAccess;
import commons.test.TestUtils;
import commons.test.TestUtilsTest;
import org.apache.commons.lang3.tuple.ImmutablePair;
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
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
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
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private SystemIn sut;
    
    /**
     * The input stream of the system under test.
     */
    private PipedInputStream systemIn;
    
    /**
     * The output stream to the system under test.
     */
    private PipedOutputStream writer;
    
    /**
     * The stream reader of the system under test.
     */
    private BufferedLineReader in;
    
    
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
        PowerMockito.spy(SystemIn.class);
        
        sut = Mockito.spy(SystemIn.class);
        TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(getClass()));
        TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(getClass()));
        
        writer = Mockito.spy(new PipedOutputStream());
        systemIn = Mockito.spy(new PipedInputStream());
        Mockito.doAnswer((Answer<Void>) invocation -> {
            invocation.callRealMethod();
            writer.flush();
            return null;
        }).when(writer).write(ArgumentMatchers.any(byte[].class));
        writer.connect(systemIn);
        in = new BufferedLineReader(new InputStreamReader(systemIn));
        
        TestAccess.setFieldValue(SystemIn.class, "systemIn", systemIn);
        TestAccess.setFieldValue(SystemIn.class, "in", in);
        TestAccess.setFieldValue(SystemIn.class, "console", System.console());
        TestAccess.setFieldValue(SystemIn.class, "buffer", null);
        TestAccess.setFieldValue(SystemIn.class, "scanner", null);
        TestAccess.setFieldValue(SystemIn.class, "scannerHandle", null);
        TestAccess.setFieldValue(SystemIn.class, "instance", sut);
        TestAccess.setFieldValue(SystemIn.class, "inUse", new AtomicBoolean(false));
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @After
    public void cleanup() throws Exception {
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "interruptScanner"));
        in.close();
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
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#SystemIn()
     */
    @Test
    public void testConstructors() throws Exception {
        SystemIn sut;
        
        //private
        sut = TestAccess.invokeConstructor(SystemIn.class);
        Assert.assertNotNull(sut);
        Assert.assertTrue(sut instanceof SystemIn);
        Assert.assertNotNull(TestAccess.getFieldValue(sut, "interrupt"));
    }
    
    /**
     * JUnit test of startScanner.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#startScanner()
     */
    @Test
    public void testStartScanner() throws Exception {
        final BufferedLineReader mockIn = Mockito.mock(BufferedLineReader.class);
        final ExecutorService mockScanner = Mockito.mock(ExecutorService.class);
        final Future<?> mockScannerHandle = Mockito.mock(Future.class);
        
        //start
        Assert.assertNull(TestAccess.getFieldValue(SystemIn.class, "scanner"));
        Assert.assertNull(TestAccess.getFieldValue(SystemIn.class, "scannerHandle"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "startScanner"));
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(1))
                .invoke("interruptScanner");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(5);
            Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        }
        
        //scanner
        writer.write("test\n".getBytes(StandardCharsets.UTF_8));
        Thread.sleep(250);
        Assert.assertNotNull(TestAccess.getFieldValue(SystemIn.class, "buffer"));
        Assert.assertEquals("test", TestAccess.getFieldValue(SystemIn.class, AtomicReference.class, "buffer").get());
        
        //scanner exception
        TestAccess.setFieldValue(SystemIn.class, "in", mockIn);
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "startScanner"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doThrow(new IOException()).when(mockIn).lineReady();
        Thread.sleep(250);
        Assert.assertNotNull(TestAccess.getFieldValue(SystemIn.class, "buffer"));
        Assert.assertNull(TestAccess.getFieldValue(SystemIn.class, AtomicReference.class, "buffer").get());
        TestAccess.setFieldValue(SystemIn.class, "in", in);
        
        //can't stop previous scanner
        TestAccess.setFieldValue(SystemIn.class, "scanner", mockScanner);
        TestAccess.setFieldValue(SystemIn.class, "scannerHandle", mockScannerHandle);
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doReturn(false).when(mockScanner).awaitTermination(ArgumentMatchers.anyLong(), ArgumentMatchers.any());
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "startScanner"));
        Mockito.doReturn(true).when(mockScanner).awaitTermination(ArgumentMatchers.anyLong(), ArgumentMatchers.any());
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "interruptScanner"));
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
    }
    
    /**
     * JUnit test of interruptScanner.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#interruptScanner()
     */
    @Test
    public void testInterruptScanner() throws Exception {
        final ExecutorService mockScanner = Mockito.mock(ExecutorService.class);
        final Future<?> mockScannerHandle = Mockito.mock(Future.class);
        final AtomicReference<String> buffer = new AtomicReference<>("test");
        
        Mockito.doReturn(true).when(mockScanner).awaitTermination(ArgumentMatchers.anyLong(), ArgumentMatchers.any());
        
        final Runnable fieldInitializer = () ->
                MapUtility.mapOf(
                        new ImmutablePair<>("scanner", mockScanner),
                        new ImmutablePair<>("scannerHandle", mockScannerHandle),
                        new ImmutablePair<>("buffer", buffer)
                ).forEach((String fieldName, Object testValue) ->
                        TestAccess.setFieldValue(SystemIn.class, fieldName, testValue));
        
        //standard
        fieldInitializer.run();
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "interruptScanner"));
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        List.of("scanner", "scannerHandle", "buffer").forEach(e ->
                Assert.assertNull(TestAccess.getFieldValue(SystemIn.class, e)));
        Mockito.verify(mockScannerHandle, VerificationModeFactory.times(1))
                .cancel(ArgumentMatchers.eq(true));
        Mockito.verify(mockScanner, VerificationModeFactory.times(1))
                .shutdown();
        Mockito.verify(mockScanner, VerificationModeFactory.times(1))
                .shutdownNow();
        Mockito.verify(mockScanner, VerificationModeFactory.times(1))
                .awaitTermination(ArgumentMatchers.eq(1000L), ArgumentMatchers.eq(TimeUnit.MILLISECONDS));
        
        //scanner not running
        List.of("scanner", "scannerHandle", "buffer").forEach(e ->
                TestAccess.setFieldValue(SystemIn.class, e, null));
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "interruptScanner"));
        List.of("scanner", "scannerHandle", "buffer").forEach(e ->
                Assert.assertNull(TestAccess.getFieldValue(SystemIn.class, e)));
        
        //can't stop scanner
        fieldInitializer.run();
        Mockito.doReturn(false).when(mockScanner).awaitTermination(ArgumentMatchers.anyLong(), ArgumentMatchers.any());
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "interruptScanner"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        List.of("scanner", "scannerHandle", "buffer").forEach(e ->
                TestAccess.setFieldValue(SystemIn.class, e, null));
        Mockito.verify(mockScannerHandle, VerificationModeFactory.times(2))
                .cancel(ArgumentMatchers.eq(true));
        Mockito.verify(mockScanner, VerificationModeFactory.times(2))
                .shutdown();
        Mockito.verify(mockScanner, VerificationModeFactory.times(2))
                .shutdownNow();
        Mockito.verify(mockScanner, VerificationModeFactory.times(2))
                .awaitTermination(ArgumentMatchers.eq(1000L), ArgumentMatchers.eq(TimeUnit.MILLISECONDS));
    }
    
    /**
     * JUnit test of isScannerRunning.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#isScannerRunning()
     */
    @Test
    public void testIsScannerRunning() throws Exception {
        final ExecutorService mockScanner = Mockito.mock(ExecutorService.class);
        final Future<?> mockScannerHandle = Mockito.mock(Future.class);
        
        //standard
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "startScanner"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "interruptScanner"));
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        
        //other
        TestAccess.setFieldValue(SystemIn.class, "scanner", mockScanner);
        TestAccess.setFieldValue(SystemIn.class, "scannerHandle", mockScannerHandle);
        Mockito.doReturn(true).when(mockScanner).awaitTermination(ArgumentMatchers.anyLong(), ArgumentMatchers.any());
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doReturn(true).when(mockScanner).isShutdown();
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doReturn(false).when(mockScanner).isShutdown();
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doReturn(true).when(mockScanner).isTerminated();
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doReturn(false).when(mockScanner).isTerminated();
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doReturn(true).when(mockScannerHandle).isDone();
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doReturn(false).when(mockScannerHandle).isDone();
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doReturn(true).when(mockScannerHandle).isCancelled();
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Mockito.doReturn(false).when(mockScannerHandle).isCancelled();
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        TestAccess.setFieldValue(SystemIn.class, "scanner", null);
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        TestAccess.setFieldValue(SystemIn.class, "scanner", mockScanner);
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        TestAccess.setFieldValue(SystemIn.class, "scannerHandle", null);
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        TestAccess.setFieldValue(SystemIn.class, "scannerHandle", mockScannerHandle);
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "interruptScanner"));
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
    }
    
    /**
     * JUnit test of nextLine.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#nextLine()
     * @see SystemIn#nextLine(Object)
     */
    @Test
    public void testNextLine() throws Exception {
        final AtomicReference<String> staticNextLine = new AtomicReference<>(null);
        final AtomicBoolean readSleep = new AtomicBoolean(false);
        final ScheduledExecutorService thread = Executors.newSingleThreadScheduledExecutor();
        
        PowerMockito.doAnswer((Answer<String>) invocation -> {
            if (readSleep.get()) {
                Thread.sleep(1000);
            }
            return (staticNextLine.get() == null) ? (String) invocation.callRealMethod() : staticNextLine.get();
        }).when(SystemIn.class, "nextLine");
        
        final Consumer<String> testWriter = (String toWrite) -> {
            try {
                writer.write(toWrite.getBytes(StandardCharsets.UTF_8));
            } catch (Exception ignored) {
                Assert.fail();
            }
        };
        
        //standard
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "startScanner"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        testWriter.accept("test 1\n");
        Assert.assertEquals("test 1", TestAccess.invokeMethod(SystemIn.class, "nextLine"));
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Thread.sleep(250);
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(1))
                .invoke("startScanner");
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(1))
                .invoke("interruptScanner");
        
        //wait
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "startScanner"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        testWriter.accept("test 2");
        Thread.sleep(250);
        testWriter.accept("\n");
        Assert.assertEquals("test 2", TestAccess.invokeMethod(SystemIn.class, "nextLine"));
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Thread.sleep(250);
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(2))
                .invoke("startScanner");
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(2))
                .invoke("interruptScanner");
        
        //immediate
        testWriter.accept("test 3\n");
        Thread.sleep(250);
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "startScanner"));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Assert.assertEquals("test 3", TestAccess.invokeMethod(SystemIn.class, "nextLine"));
        Assert.assertFalse(TestAccess.invokeMethod(SystemIn.class, boolean.class, "isScannerRunning"));
        Thread.sleep(250);
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(3))
                .invoke("startScanner");
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(3))
                .invoke("interruptScanner");
        
        //in use
        staticNextLine.set("test 4");
        readSleep.set(true);
        Assert.assertFalse(TestAccess.getFieldValue(SystemIn.class, AtomicBoolean.class, "inUse").get());
        thread.schedule(() -> {
            Assert.assertTrue(TestAccess.getFieldValue(SystemIn.class, AtomicBoolean.class, "inUse").get());
            Assert.assertNull(SystemIn.nextLine(getClass()));
        }, 250, TimeUnit.MILLISECONDS);
        Assert.assertEquals("test 4", SystemIn.nextLine(getClass()));
        Assert.assertTrue(TestAccess.invokeMethod(SystemIn.class, boolean.class, "interruptScanner"));
        Assert.assertFalse(TestAccess.getFieldValue(SystemIn.class, AtomicBoolean.class, "inUse").get());
        Thread.sleep(250);
        PowerMockito.verifyStatic(SystemIn.class, VerificationModeFactory.times(2));
        SystemIn.owns(ArgumentMatchers.eq(getClass()));
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(4))
                .invoke("startScanner");
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(5))
                .invoke("interruptScanner");
        readSleep.set(false);
        staticNextLine.set(null);
        
        //scanner failure
        PowerMockito.doReturn(false).when(SystemIn.class, "startScanner");
        Assert.assertNull(SystemIn.nextLine(getClass()));
        PowerMockito.doCallRealMethod().when(SystemIn.class, "startScanner");
        PowerMockito.doReturn(false).when(SystemIn.class, "isScannerRunning");
        Assert.assertNull(SystemIn.nextLine(getClass()));
        PowerMockito.doCallRealMethod().when(SystemIn.class, "isScannerRunning");
        
        //invalid
        Assert.assertNull(SystemIn.nextLine(TestUtilsTest.class));
        Assert.assertNull(SystemIn.nextLine(new TestUtilsTest()));
        Assert.assertNull(SystemIn.nextLine(null));
    }
    
    /**
     * JUnit test of readPassword.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#readPassword()
     * @see SystemIn#readPassword(Object)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testReadPassword() throws Exception {
        final Console mockConsole = PowerMockito.mock(Console.class);
        final AtomicBoolean readSleep = new AtomicBoolean(false);
        final ScheduledExecutorService thread = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().build());
        
        Mockito.doAnswer((Answer<char[]>) invocation -> {
            if (readSleep.get()) {
                Thread.sleep(1000);
            }
            return "password".toCharArray();
        }).when(mockConsole).readPassword();
        PowerMockito.doAnswer((Answer<String>) invocation -> {
            if (readSleep.get()) {
                Thread.sleep(1000);
            }
            return "test";
        }).when(SystemIn.class, "nextLine");
        
        //standard
        TestAccess.setFieldValue(SystemIn.class, "console", mockConsole);
        Assert.assertEquals("password", TestAccess.invokeMethod(SystemIn.class, "readPassword"));
        Mockito.verify(mockConsole, VerificationModeFactory.times(1))
                .readPassword();
        
        //no console
        TestAccess.setFieldValue(SystemIn.class, "console", null);
        Assert.assertNull(TestAccess.invokeMethod(SystemIn.class, "readPassword"));
        Mockito.verify(mockConsole, VerificationModeFactory.times(1))
                .readPassword();
        
        //in use
        TestAccess.setFieldValue(SystemIn.class, "console", mockConsole);
        readSleep.set(true);
        Assert.assertFalse(TestAccess.getFieldValue(SystemIn.class, AtomicBoolean.class, "inUse").get());
        thread.schedule(() -> {
            Assert.assertTrue(TestAccess.getFieldValue(SystemIn.class, AtomicBoolean.class, "inUse").get());
            Assert.assertNull(SystemIn.readPassword(getClass()));
        }, 250, TimeUnit.MILLISECONDS);
        Assert.assertEquals("password", SystemIn.readPassword(getClass()));
        Assert.assertFalse(TestAccess.getFieldValue(SystemIn.class, AtomicBoolean.class, "inUse").get());
        PowerMockito.verifyStatic(SystemIn.class, VerificationModeFactory.times(2));
        SystemIn.owns(ArgumentMatchers.eq(getClass()));
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(0))
                .invoke("startScanner");
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(1))
                .invoke("interruptScanner");
        readSleep.set(false);
        
        //in use, no console
        TestAccess.setFieldValue(SystemIn.class, "console", null);
        readSleep.set(true);
        Assert.assertFalse(TestAccess.getFieldValue(SystemIn.class, AtomicBoolean.class, "inUse").get());
        thread.schedule(() -> {
            Assert.assertTrue(TestAccess.getFieldValue(SystemIn.class, AtomicBoolean.class, "inUse").get());
            Assert.assertNull(SystemIn.readPassword(getClass()));
        }, 250, TimeUnit.MILLISECONDS);
        Assert.assertEquals("test", SystemIn.readPassword(getClass()));
        Assert.assertFalse(TestAccess.getFieldValue(SystemIn.class, AtomicBoolean.class, "inUse").get());
        PowerMockito.verifyStatic(SystemIn.class, VerificationModeFactory.times(4));
        SystemIn.owns(ArgumentMatchers.eq(getClass()));
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(1))
                .invoke("startScanner");
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(2))
                .invoke("interruptScanner");
        readSleep.set(false);
        
        //invalid
        Assert.assertNull(SystemIn.readPassword(TestUtils.class));
        Assert.assertNull(SystemIn.readPassword(new TestUtilsTest()));
        Assert.assertNull(SystemIn.readPassword(null));
    }
    
    /**
     * JUnit test of owns.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#owns(Object)
     */
    @Test
    public void testOwns() throws Exception {
        //standard
        SystemIn.owns(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .isOwner(ArgumentMatchers.eq(getClass()));
    }
    
    /**
     * JUnit test of own.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#own(Object)
     */
    @Test
    public void testOwn() throws Exception {
        //standard
        SystemIn.own(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .acquireOwnership(ArgumentMatchers.eq(getClass()));
    }
    
    /**
     * JUnit test of relinquish.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#relinquish(Object)
     */
    @Test
    public void testRelinquish() throws Exception {
        //standard
        SystemIn.relinquish(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .releaseOwnership(ArgumentMatchers.eq(getClass()));
    }
    
    /**
     * JUnit test of manage.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#manage(Object)
     */
    @Test
    public void testManage() throws Exception {
        //standard
        SystemIn.manage(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .acquireManagement(ArgumentMatchers.eq(getClass()));
    }
    
    /**
     * JUnit test of relinquishManagement.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#relinquishManagement(Object)
     */
    @Test
    public void testRelinquishManagement() throws Exception {
        //standard
        SystemIn.relinquishManagement(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .releaseManagement(ArgumentMatchers.eq(getClass()));
    }
    
}
