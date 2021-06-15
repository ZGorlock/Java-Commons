/*
 * File:    WaveRecorderTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.TargetDataLine;

import commons.access.Filesystem;
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
 * JUnit test of WaveRecorder.
 *
 * @see WaveRecorder
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({WaveRecorder.class, AudioSystem.class})
public class WaveRecorderTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(WaveRecorderTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private WaveRecorder sut;
    
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
        PowerMockito.mockStatic(WaveRecorder.class, Mockito.CALLS_REAL_METHODS);
        sut = Mockito.mock(WaveRecorder.class, Mockito.CALLS_REAL_METHODS);
        TestUtils.setField(sut, "interrupt", (Runnable) () -> interrupt.set(true));
        TestUtils.setField(WaveRecorder.class, "instance", sut);
        
        TestUtils.setField(sut, "owner", "");
        TestUtils.setField(sut, "defaultOwner", "");
        WaveRecorder.defaultOwn(WaveRecorderTest.class);
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @After
    public void cleanup() throws Exception {
        WaveRecorder.stop(WaveRecorderTest.class);
    }
    
    
    //Tests
    
    /**
     * JUnit test of constants.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#RECORDING_THREAD_STATUS_DELAY
     * @see WaveRecorder#DEFAULT_SAMPLE_RATE
     * @see WaveRecorder#DEFAULT_SAMPLE_SIZE_IN_BITS
     * @see WaveRecorder#DEFAULT_CHANNELS
     * @see WaveRecorder#DEFAULT_SIGNED
     * @see WaveRecorder#DEFAULT_BIG_ENDIAN
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(100, WaveRecorder.RECORDING_THREAD_STATUS_DELAY);
        Assert.assertEquals(44100, WaveRecorder.DEFAULT_SAMPLE_RATE);
        Assert.assertEquals(16, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS);
        Assert.assertEquals(2, WaveRecorder.DEFAULT_CHANNELS);
        Assert.assertTrue(WaveRecorder.DEFAULT_SIGNED);
        Assert.assertTrue(WaveRecorder.DEFAULT_BIG_ENDIAN);
    }
    
    /**
     * JUnit test of start.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#start(File, AudioFormat, Class)
     * @see WaveRecorder#start(File, AudioFormat, Object)
     * @see WaveRecorder#start(File, int, int, int, boolean, boolean, Class)
     * @see WaveRecorder#start(File, int, int, int, boolean, boolean, Object)
     * @see WaveRecorder#start(File, Class)
     * @see WaveRecorder#start(File, Object)
     */
    @Test
    public void testStart() throws Exception {
        final File output = Filesystem.getTemporaryFile();
        final AudioFormat audioFormat = new AudioFormat(WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN);
        PowerMockito.mockStatic(AudioSystem.class, Mockito.CALLS_REAL_METHODS);
        long startTime;
        long duration;
        Assert.assertTrue(WaveRecorder.owns(WaveRecorderTest.class));
        
        //standard
        
        startTime = System.currentTimeMillis();
        Assert.assertTrue(WaveRecorder.start(output, WaveRecorderTest.class));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(duration >= WaveRecorder.RECORDING_THREAD_STATUS_DELAY);
        Assert.assertNotNull(TestUtils.getField(WaveRecorder.class, "output"));
        Assert.assertNotNull(TestUtils.getField(WaveRecorder.class, "format"));
        Assert.assertNotNull(TestUtils.getField(WaveRecorder.class, "line"));
        Assert.assertNotNull(TestUtils.getField(WaveRecorder.class, "recording"));
        Assert.assertTrue(((Thread) TestUtils.getField(WaveRecorder.class, "recording")).isAlive());
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(1));
        WaveRecorder.stop(ArgumentMatchers.eq(WaveRecorderTest.class));
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(1));
        AudioSystem.write(ArgumentMatchers.any(AudioInputStream.class), ArgumentMatchers.eq(AudioFileFormat.Type.WAVE), ArgumentMatchers.eq(output));
        WaveRecorder.stop(WaveRecorderTest.class);
        
        //doenst own
        
        Assert.assertTrue(WaveRecorder.relinquish(WaveRecorderTest.class));
        Assert.assertTrue(WaveRecorder.own(TestUtils.class));
        Assert.assertFalse(WaveRecorder.start(output, audioFormat, WaveRecorderTest.class));
        Assert.assertTrue(WaveRecorder.own(WaveRecorderTest.class));
        
        //recording not allowed
        
        PowerMockito.mockStatic(AudioSystem.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn(false).when(AudioSystem.class, "isLineSupported", ArgumentMatchers.any(Line.Info.class));
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(WaveRecorder.class, "recordingWarning")).get());
        Assert.assertFalse(WaveRecorder.start(output, audioFormat, WaveRecorderTest.class));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(WaveRecorder.class, "recordingWarning")).get());
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(1));
        AudioSystem.isLineSupported(ArgumentMatchers.any(Line.Info.class));
        PowerMockito.doCallRealMethod().when(AudioSystem.class, "isLineSupported", ArgumentMatchers.any(Line.Info.class));
        Assert.assertTrue(WaveRecorder.start(output, WaveRecorderTest.class));
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(WaveRecorder.class, "recordingWarning")).get());
        
        //overloads
        
        PowerMockito.mockStatic(WaveRecorder.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn(true).when(WaveRecorder.class, "start", ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(WaveRecorderTest.class));
        
        WaveRecorder.start(output, audioFormat, new WaveRecorderTest());
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(1));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(WaveRecorderTest.class));
        
        WaveRecorder.start(output, new AudioFormat(WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN), null);
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(1));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(null));
        
        WaveRecorder.start(output, WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN, WaveRecorderTest.class);
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(2));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(WaveRecorderTest.class));
        
        WaveRecorder.start(output, WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN, new WaveRecorderTest());
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(3));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(WaveRecorderTest.class));
        
        WaveRecorder.start(output, WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN, null);
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(2));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(null));
        
        WaveRecorder.start(output, WaveRecorderTest.class);
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(4));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(WaveRecorderTest.class));
        
        WaveRecorder.start(output, new WaveRecorderTest());
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(5));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(WaveRecorderTest.class));
        
        WaveRecorder.start(output, null);
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(3));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(null));
        
        PowerMockito.doCallRealMethod().when(WaveRecorder.class, "start", ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(WaveRecorderTest.class));
    }
    
    /**
     * JUnit test of stop.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#stop()
     * @see WaveRecorder#stop(Class)
     * @see WaveRecorder#stop(Object)
     */
    @Test
    public void testStop() throws Exception {
        final File output = Filesystem.getTemporaryFile();
        final AudioFormat audioFormat = new AudioFormat(WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN);
        final TargetDataLine line = Mockito.mock(TargetDataLine.class);
        final Thread recording = Mockito.mock(Thread.class);
        Assert.assertTrue(WaveRecorder.owns(WaveRecorderTest.class));
        
        //standard
        
        TestUtils.setField(WaveRecorder.class, "output", output);
        TestUtils.setField(WaveRecorder.class, "format", audioFormat);
        TestUtils.setField(WaveRecorder.class, "line", line);
        TestUtils.setField(WaveRecorder.class, "recording", recording);
        Assert.assertTrue(WaveRecorder.stop(WaveRecorderTest.class));
        Assert.assertNotNull(TestUtils.getField(WaveRecorder.class, "output"));
        Assert.assertNotNull(TestUtils.getField(WaveRecorder.class, "format"));
        Assert.assertNull(TestUtils.getField(WaveRecorder.class, "line"));
        Assert.assertNull(TestUtils.getField(WaveRecorder.class, "recording"));
        Mockito.verify(line, VerificationModeFactory.times(1))
                .stop();
        Mockito.verify(line, VerificationModeFactory.times(1))
                .close();
        
        TestUtils.setField(WaveRecorder.class, "output", output);
        TestUtils.setField(WaveRecorder.class, "format", audioFormat);
        TestUtils.setField(WaveRecorder.class, "line", null);
        TestUtils.setField(WaveRecorder.class, "recording", null);
        TestUtils.assertNoException(() ->
                Assert.assertTrue(WaveRecorder.stop(WaveRecorderTest.class)));
        
        //doesn't own
        
        Assert.assertTrue(WaveRecorder.relinquish(WaveRecorderTest.class));
        Assert.assertTrue(WaveRecorder.own(TestUtils.class));
        Assert.assertFalse(WaveRecorder.stop(WaveRecorderTest.class));
        Assert.assertTrue(WaveRecorder.own(WaveRecorderTest.class));
        
        //overloads
        
        PowerMockito.mockStatic(WaveRecorder.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn(true).when(WaveRecorder.class, "stop", ArgumentMatchers.any(Class.class));
        
        WaveRecorder.stop(new WaveRecorderTest());
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(1));
        WaveRecorder.stop(ArgumentMatchers.eq(WaveRecorderTest.class));
        
        WaveRecorder.stop(null);
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(1));
        WaveRecorder.stop(ArgumentMatchers.eq(null));
        
        PowerMockito.doCallRealMethod().when(WaveRecorder.class, "stop", ArgumentMatchers.any(Class.class));
    }
    
    /**
     * JUnit test of getLengthInMilliseconds.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#getLengthInMilliseconds(Class)
     * @see WaveRecorder#getLengthInMilliseconds(Object)
     */
    @Test
    public void testGetLengthInMilliseconds() throws Exception {
        final File output = Mockito.mock(File.class);
        final AudioFormat audioFormat = new AudioFormat(WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN);
        final Thread recording = Mockito.mock(Thread.class);
        Assert.assertTrue(WaveRecorder.owns(WaveRecorderTest.class));
        
        //standard
        
        TestUtils.setField(WaveRecorder.class, "output", output);
        TestUtils.setField(WaveRecorder.class, "format", audioFormat);
        TestUtils.setField(WaveRecorder.class, "recording", null);
        Mockito.when(output.exists()).thenReturn(true);
        
        Mockito.when(output.length()).thenReturn(176400L);
        Assert.assertEquals(1000L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        
        Mockito.when(output.length()).thenReturn(441000L);
        Assert.assertEquals(2500L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        
        Mockito.when(output.length()).thenReturn(14994L);
        Assert.assertEquals(85L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        
        Mockito.when(output.length()).thenReturn(8945604895261265L);
        Assert.assertEquals(50712045891503L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        
        Mockito.when(output.length()).thenReturn(0L);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        
        //invalid
        
        TestUtils.setField(WaveRecorder.class, "output", null);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        TestUtils.setField(WaveRecorder.class, "output", output);
        
        TestUtils.setField(WaveRecorder.class, "format", null);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        TestUtils.setField(WaveRecorder.class, "format", audioFormat);
        
        TestUtils.setField(WaveRecorder.class, "recording", recording);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        TestUtils.setField(WaveRecorder.class, "recording", null);
        
        Mockito.when(output.exists()).thenReturn(false);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        Mockito.when(output.exists()).thenReturn(true);
        
        //doesn't own
        
        Assert.assertTrue(WaveRecorder.relinquish(WaveRecorderTest.class));
        Assert.assertTrue(WaveRecorder.own(TestUtils.class));
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(WaveRecorderTest.class));
        Assert.assertTrue(WaveRecorder.own(WaveRecorderTest.class));
        
        //overloads
        
        PowerMockito.mockStatic(WaveRecorder.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn(0L).when(WaveRecorder.class, "getLengthInMilliseconds", ArgumentMatchers.any(Class.class));
        
        WaveRecorder.getLengthInMilliseconds(new WaveRecorderTest());
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(1));
        WaveRecorder.getLengthInMilliseconds(ArgumentMatchers.eq(WaveRecorderTest.class));
        
        WaveRecorder.getLengthInMilliseconds(null);
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(1));
        WaveRecorder.getLengthInMilliseconds(ArgumentMatchers.eq(null));
        
        PowerMockito.doCallRealMethod().when(WaveRecorder.class, "getLengthInMilliseconds", ArgumentMatchers.any(Class.class));
    }
    
    /**
     * JUnit test of recordingEnabled.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#recordingEnabled()
     */
    @Test
    public void testRecordingEnabled() throws Exception {
        PowerMockito.mockStatic(AudioSystem.class);
        
        PowerMockito.doReturn(true).when(AudioSystem.class, "isLineSupported", ArgumentMatchers.any(Line.Info.class));
        Assert.assertTrue(WaveRecorder.recordingEnabled());
        
        PowerMockito.doReturn(false).when(AudioSystem.class, "isLineSupported", ArgumentMatchers.any(Line.Info.class));
        Assert.assertFalse(WaveRecorder.recordingEnabled());
        
        PowerMockito.doCallRealMethod().when(AudioSystem.class, "isLineSupported", ArgumentMatchers.any(Line.Info.class));
    }
    
    /**
     * JUnit test of owns.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#owns(Class)
     * @see WaveRecorder#owns(Object)
     */
    @Test
    public void testOwns() throws Exception {
        //class
        WaveRecorder.owns(WaveRecorderTest.class);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .isOwner(ArgumentMatchers.eq(WaveRecorderTest.class));
        
        //object
        Object object = new WaveRecorderTest();
        WaveRecorder.owns(object);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .isOwner(ArgumentMatchers.eq(object));
    }
    
    /**
     * JUnit test of own.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#own(Class)
     * @see WaveRecorder#own(Object)
     */
    @Test
    public void testOwn() throws Exception {
        //class
        WaveRecorder.own(WaveRecorderTest.class);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .claimOwnership(ArgumentMatchers.eq(WaveRecorderTest.class));
        
        //object
        Object object = new WaveRecorderTest();
        WaveRecorder.own(object);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .claimOwnership(ArgumentMatchers.eq(object));
    }
    
    /**
     * JUnit test of defaultOwn.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#defaultOwn(Class)
     * @see WaveRecorder#defaultOwn(Object)
     */
    @Test
    public void testDefaultOwn() throws Exception {
        //class
        WaveRecorder.defaultOwn(WaveRecorderTest.class);
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .claimDefaultOwnership(ArgumentMatchers.eq(WaveRecorderTest.class));
        
        //object
        Object object = new WaveRecorderTest();
        WaveRecorder.defaultOwn(object);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .claimDefaultOwnership(ArgumentMatchers.eq(object));
    }
    
    /**
     * JUnit test of relinquish.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#relinquish(Class)
     * @see WaveRecorder#relinquish(Object)
     */
    @Test
    public void testRelinquish() throws Exception {
        //class
        WaveRecorder.relinquish(WaveRecorderTest.class);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .relinquishOwnership(ArgumentMatchers.eq(WaveRecorderTest.class));
        
        //object
        Object object = new WaveRecorderTest();
        WaveRecorder.relinquish(object);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .relinquishOwnership(ArgumentMatchers.eq(object));
    }
    
}
