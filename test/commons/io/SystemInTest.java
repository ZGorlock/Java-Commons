/*
 * File:    SystemInTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import commons.math.BoundUtility;
import commons.test.TestUtils;
import commons.test.TestUtilsTest;
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
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of SystemIn.
 *
 * @see SystemIn
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({SystemIn.class, SystemIn.PasswordIn.class})
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
     * The scanner of the system under test.
     */
    private Scanner in;
    
    /**
     * The input stream of the system under test.
     */
    private PipedInputStream systemIn;
    
    /**
     * The output stream to the system under test.
     */
    private PipedOutputStream writer;
    
    /**
     * A flag indicating whether the interrupt was activated or not.
     */
    private final AtomicBoolean interrupt = new AtomicBoolean(false);
    
    
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
        PowerMockito.mockStatic(SystemIn.class, Mockito.CALLS_REAL_METHODS);
        sut = Mockito.mock(SystemIn.class, Mockito.CALLS_REAL_METHODS);
        TestUtils.setField(sut, "interrupt", (Runnable) () -> interrupt.set(true));
        TestUtils.setField(SystemIn.class, "instance", sut);
        TestUtils.setField(SystemIn.class, "scannerThread", null);
        TestUtils.setField(SystemIn.class, "scannerThreadHandle", null);
        
        writer = new PipedOutputStream();
        systemIn = new PipedInputStream();
        writer.connect(systemIn);
        in = new Scanner(systemIn);
        TestUtils.setField(SystemIn.class, "systemIn", systemIn);
        TestUtils.setField(SystemIn.class, "in", in);
        TestUtils.setField(SystemIn.class, "buffer", null);
        
        TestUtils.setField(sut, "owner", "");
        TestUtils.setField(sut, "defaultOwner", "");
        SystemIn.defaultOwn(SystemInTest.class);
        Assert.assertTrue(interrupt.getAndSet(false));
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
        Assert.assertTrue(SystemIn.owns(SystemInTest.class) || SystemIn.own(SystemInTest.class));
        
        //start
        Assert.assertNull(TestUtils.getField(SystemIn.class, "scannerThread"));
        Assert.assertNull(TestUtils.getField(SystemIn.class, "scannerThreadHandle"));
        Whitebox.invokeMethod(SystemIn.class, "startScanner");
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(1))
                .invoke("interruptScanner");
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        
        //scanner
        for (int i = 0; i < 10; i++) {
            Thread.sleep(5);
            Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        }
        writer.write("test\n".getBytes(StandardCharsets.UTF_8));
        writer.flush();
        Thread.sleep(50);
        Assert.assertNotNull(TestUtils.getField(SystemIn.class, "buffer"));
        Assert.assertEquals("test", ((AtomicReference<String>) TestUtils.getField(SystemIn.class, "buffer")).get());
        
        //scanner exception
        InputStream mockSystemIn = Mockito.mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
        in = new Scanner(mockSystemIn);
        TestUtils.setField(SystemIn.class, "systemIn", mockSystemIn);
        TestUtils.setField(SystemIn.class, "in", in);
        Whitebox.invokeMethod(SystemIn.class, "startScanner");
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        Mockito.when(mockSystemIn.available()).thenThrow(new IOException());
        Thread.sleep(50);
        Assert.assertNotNull(TestUtils.getField(SystemIn.class, "buffer"));
        Assert.assertEquals("", ((AtomicReference<String>) TestUtils.getField(SystemIn.class, "buffer")).get());
    }
    
    /**
     * JUnit test of interruptScanner.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#interruptScanner()
     */
    @Test
    public void testInterruptScanner() throws Exception {
        Assert.assertTrue(SystemIn.owns(SystemInTest.class) || SystemIn.own(SystemInTest.class));
        
        //standard
        Whitebox.invokeMethod(SystemIn.class, "startScanner");
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        TestUtils.setField(SystemIn.class, "buffer", new AtomicReference<>("test"));
        Whitebox.invokeMethod(SystemIn.class, "interruptScanner");
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        
        //scanner not alive
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        TestUtils.setField(SystemIn.class, "buffer", new AtomicReference<>("test"));
        Whitebox.invokeMethod(SystemIn.class, "interruptScanner");
        
        //scanner not started
        TestUtils.setField(SystemIn.class, "scannerThread", null);
        TestUtils.setField(SystemIn.class, "scannerThreadHandle", null);
        TestUtils.setField(SystemIn.class, "buffer", new AtomicReference<>("test"));
        Whitebox.invokeMethod(SystemIn.class, "interruptScanner");
    }
    
    /**
     * JUnit test of isScannerRunning.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#isScannerRunning()
     */
    @Test
    public void testIsScannerRunning() throws Exception {
        Assert.assertTrue(SystemIn.owns(SystemInTest.class) || SystemIn.own(SystemInTest.class));
        
        //standard
        
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        Whitebox.invokeMethod(SystemIn.class, "startScanner");
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        Whitebox.invokeMethod(SystemIn.class, "interruptScanner");
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        
        //other
        
        ExecutorService scannerThread = Mockito.mock(ExecutorService.class, Mockito.CALLS_REAL_METHODS);
        Future<?> scannerThreadHandle = Mockito.mock(Future.class, Mockito.CALLS_REAL_METHODS);
        TestUtils.setField(SystemIn.class, "scannerThread", scannerThread);
        TestUtils.setField(SystemIn.class, "scannerThreadHandle", scannerThreadHandle);
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        
        Mockito.when(scannerThread.isShutdown()).thenReturn(true);
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        Mockito.when(scannerThread.isShutdown()).thenReturn(false);
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        
        Mockito.when(scannerThread.isTerminated()).thenReturn(true);
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        Mockito.when(scannerThread.isTerminated()).thenReturn(false);
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        
        Mockito.when(scannerThreadHandle.isDone()).thenReturn(true);
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        Mockito.when(scannerThreadHandle.isDone()).thenReturn(false);
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        
        Mockito.when(scannerThreadHandle.isCancelled()).thenReturn(true);
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        Mockito.when(scannerThreadHandle.isCancelled()).thenReturn(false);
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        
        TestUtils.setField(SystemIn.class, "scannerThreadHandle", null);
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        TestUtils.setField(SystemIn.class, "scannerThreadHandle", scannerThreadHandle);
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        
        TestUtils.setField(SystemIn.class, "scannerThread", null);
        Assert.assertFalse(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
        TestUtils.setField(SystemIn.class, "scannerThread", scannerThread);
        Assert.assertTrue(Whitebox.invokeMethod(SystemIn.class, "isScannerRunning"));
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
        long startTime;
        long duration;
        ScheduledExecutorService thread;
        Assert.assertTrue(SystemIn.own(SystemInTest.class));
        
        //standard
        thread = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().build());
        thread.schedule(() -> {
            try {
                Thread.sleep(500);
                writer.write("test\n".getBytes(StandardCharsets.UTF_8));
                writer.flush();
            } catch (InterruptedException | IOException ignored) {
            }
        }, 0, TimeUnit.MILLISECONDS);
        startTime = System.currentTimeMillis();
        Assert.assertEquals("test", SystemIn.nextLine(SystemInTest.class));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.9), (500 * 1.125)));
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(1))
                .invoke("startScanner");
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(1))
                .invoke("getBuffer", ArgumentMatchers.eq(SystemInTest.class));
        thread = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().build());
        thread.schedule(() -> {
            try {
                Thread.sleep(500);
                writer.write("test\n".getBytes(StandardCharsets.UTF_8));
                writer.flush();
            } catch (InterruptedException | IOException ignored) {
            }
        }, 0, TimeUnit.MILLISECONDS);
        startTime = System.currentTimeMillis();
        Assert.assertEquals("test", SystemIn.nextLine(new SystemInTest()));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.9), (500 * 1.125)));
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(2))
                .invoke("startScanner");
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(2))
                .invoke("getBuffer", ArgumentMatchers.eq(SystemInTest.class));
        
        //invalid
        Assert.assertEquals("", SystemIn.nextLine(null));
        Assert.assertEquals("", SystemIn.nextLine((Object) null));
        
        //doesnt own
        Assert.assertEquals("", SystemIn.nextLine(TestUtilsTest.class));
        Assert.assertEquals("", SystemIn.nextLine(new TestUtilsTest()));
        
        //scanner failure
        PowerMockito.mockStatic(SystemIn.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.when(SystemIn.class, "isScannerRunning").thenReturn(false);
        Assert.assertNull(SystemIn.nextLine(SystemInTest.class));
    }
    
    /**
     * JUnit test of getPassword.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn#getPassword(Class)
     * @see SystemIn#getPassword(Object)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testGetPassword() throws Exception {
        PowerMockito.mockStatic(SystemIn.PasswordIn.class);
        PowerMockito.doReturn("password").when(SystemIn.PasswordIn.class, "readPassword", ArgumentMatchers.any(Class.class));
        PowerMockito.doReturn(true).when(SystemIn.PasswordIn.class, "hasConsole");
        PowerMockito.doReturn("test").when(SystemIn.class, "nextLine", ArgumentMatchers.any(Class.class));
        Assert.assertTrue(SystemIn.own(SystemInTest.class));
        
        //standard
        Assert.assertEquals("password", SystemIn.getPassword(SystemInTest.class));
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(1))
                .invoke("interruptScanner");
        PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.times(1));
        SystemIn.PasswordIn.hasConsole();
        PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.times(1));
        SystemIn.PasswordIn.readPassword(ArgumentMatchers.eq(SystemInTest.class));
        Assert.assertEquals("password", SystemIn.getPassword(new SystemInTest()));
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(2))
                .invoke("interruptScanner");
        PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.times(2));
        SystemIn.PasswordIn.hasConsole();
        PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.times(2));
        SystemIn.PasswordIn.readPassword(ArgumentMatchers.eq(SystemInTest.class));
        
        //no console
        PowerMockito.doReturn(false).when(SystemIn.PasswordIn.class, "hasConsole");
        Assert.assertEquals("test", SystemIn.getPassword(SystemInTest.class));
        PowerMockito.verifyPrivate(SystemIn.class, VerificationModeFactory.times(3))
                .invoke("interruptScanner");
        PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.times(3));
        SystemIn.PasswordIn.hasConsole();
        PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.noMoreInteractions());
        SystemIn.PasswordIn.readPassword(ArgumentMatchers.any(Class.class));
        
        //invalid
        Assert.assertEquals("", SystemIn.getPassword(null));
        Assert.assertEquals("", SystemIn.getPassword((Object) null));
        
        //doesnt own
        Assert.assertEquals("", SystemIn.getPassword(TestUtils.class));
        Assert.assertEquals("", SystemIn.getPassword(new TestUtilsTest()));
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
        Assert.assertTrue(SystemIn.own(SystemInTest.class));
        TestUtils.setField(SystemIn.class, "buffer", new AtomicReference<>("test"));
        
        //standard
        Assert.assertEquals("test", SystemIn.getBuffer(SystemInTest.class));
        Assert.assertNull(TestUtils.getField(SystemIn.class, "buffer"));
        TestUtils.setField(SystemIn.class, "buffer", new AtomicReference<>("test"));
        
        //no buffer
        TestUtils.setField(SystemIn.class, "buffer", null);
        Assert.assertNull(SystemIn.getBuffer(SystemInTest.class));
        Assert.assertNull(TestUtils.getField(SystemIn.class, "buffer"));
        TestUtils.setField(SystemIn.class, "buffer", new AtomicReference<>("test"));
        
        //invalid
        Assert.assertNull(SystemIn.getBuffer(null));
        Assert.assertNull(SystemIn.getBuffer((Object) null));
        
        //doesnt own
        Assert.assertNull(SystemIn.getBuffer(TestUtils.class));
        Assert.assertNull(SystemIn.getBuffer(new TestUtilsTest()));
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
        //class
        SystemIn.owns(SystemInTest.class);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .isOwner(ArgumentMatchers.eq(SystemInTest.class));
        
        //object
        Object object = new SystemInTest();
        SystemIn.owns(object);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .isOwner(ArgumentMatchers.eq(object));
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
        //class
        SystemIn.own(SystemInTest.class);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .claimOwnership(ArgumentMatchers.eq(SystemInTest.class));
        
        //object
        Object object = new SystemInTest();
        SystemIn.own(object);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .claimOwnership(ArgumentMatchers.eq(object));
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
        //class
        SystemIn.defaultOwn(SystemInTest.class);
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .claimDefaultOwnership(ArgumentMatchers.eq(SystemInTest.class));
        
        //object
        Object object = new SystemInTest();
        SystemIn.defaultOwn(object);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .claimDefaultOwnership(ArgumentMatchers.eq(object));
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
        //class
        SystemIn.relinquish(SystemInTest.class);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .relinquishOwnership(ArgumentMatchers.eq(SystemInTest.class));
        
        //object
        Object object = new SystemInTest();
        SystemIn.relinquish(object);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .relinquishOwnership(ArgumentMatchers.eq(object));
    }
    
    /**
     * JUnit test of PasswordIn.
     *
     * @throws Exception When there is an exception.
     * @see SystemIn.PasswordIn
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testPasswordIn() throws Exception {
        PowerMockito.mockStatic(SystemIn.PasswordIn.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn("password".toCharArray()).when(SystemIn.PasswordIn.class, "doReadPassword");
        TestUtils.assertMethodExists(
                SystemIn.PasswordIn.class, "doReadPassword");
        TestUtils.assertMethodExists(
                SystemIn.PasswordIn.class, "readPassword", Class.class);
        TestUtils.assertMethodExists(
                SystemIn.PasswordIn.class, "readPassword", Object.class);
        TestUtils.assertMethodExists(
                SystemIn.PasswordIn.class, "hasConsole");
        Assert.assertTrue(SystemIn.own(SystemInTest.class));
        
        //console
        Console console = System.console();
        Assert.assertEquals(console, TestUtils.getField(SystemIn.PasswordIn.class, "console"));
        
        //methods
        if (console == null) { //running from IDE (can't mock)
            
            //standard
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword(SystemInTest.class));
            PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.times(1));
            SystemIn.PasswordIn.hasConsole();
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword(new SystemInTest()));
            PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.times(2));
            SystemIn.PasswordIn.hasConsole();
            
            //doesn't own
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword(TestUtils.class));
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword(new TestUtilsTest()));
            
            //invalid
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword(null));
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword((Object) null));
            
            //hasConsole
            Assert.assertFalse(SystemIn.PasswordIn.hasConsole());
            
        } else { //running in console mode
            
            //standard
            Assert.assertEquals("password", SystemIn.PasswordIn.readPassword(SystemInTest.class));
            PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.times(1));
            SystemIn.PasswordIn.hasConsole();
            PowerMockito.verifyPrivate(SystemIn.PasswordIn.class, VerificationModeFactory.times(1))
                    .invoke("doReadPassword");
            Assert.assertEquals("password", SystemIn.PasswordIn.readPassword(new SystemInTest()));
            PowerMockito.verifyStatic(SystemIn.PasswordIn.class, VerificationModeFactory.times(2));
            SystemIn.PasswordIn.hasConsole();
            PowerMockito.verifyPrivate(SystemIn.PasswordIn.class, VerificationModeFactory.times(2))
                    .invoke("doReadPassword");
            
            //doesn't own
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword(TestUtils.class));
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword(new TestUtilsTest()));
            
            //invalid
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword(null));
            Assert.assertEquals("", SystemIn.PasswordIn.readPassword((Object) null));
            
            //hasConsole
            Assert.assertTrue(SystemIn.PasswordIn.hasConsole());
            
        }
    }
    
}
