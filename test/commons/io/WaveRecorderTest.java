/*
 * File:    WaveRecorderTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.TargetDataLine;

import commons.access.Filesystem;
import commons.object.collection.MapUtility;
import commons.test.TestAccess;
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
        PowerMockito.spy(WaveRecorder.class);
        
        sut = Mockito.spy(WaveRecorder.class);
        TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(getClass()));
        TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(getClass()));
        
        TestAccess.setFieldValue(WaveRecorder.class, "output", null);
        TestAccess.setFieldValue(WaveRecorder.class, "format", null);
        TestAccess.setFieldValue(WaveRecorder.class, "line", null);
        TestAccess.setFieldValue(WaveRecorder.class, "recorder", null);
        TestAccess.setFieldValue(WaveRecorder.class, "recorderHandle", null);
        TestAccess.setFieldValue(WaveRecorder.class, "recorderWarning", new AtomicBoolean(false));
        TestAccess.setFieldValue(WaveRecorder.class, "instance", sut);
        TestAccess.setFieldValue(WaveRecorder.class, "inUse", new AtomicBoolean(false));
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @After
    public void cleanup() throws Exception {
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "stop"));
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
        //standard
        Assert.assertEquals(100, WaveRecorder.RECORDING_THREAD_STATUS_DELAY);
        Assert.assertEquals(44100, WaveRecorder.DEFAULT_SAMPLE_RATE);
        Assert.assertEquals(16, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS);
        Assert.assertEquals(2, WaveRecorder.DEFAULT_CHANNELS);
        Assert.assertTrue(WaveRecorder.DEFAULT_SIGNED);
        Assert.assertTrue(WaveRecorder.DEFAULT_BIG_ENDIAN);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#WaveRecorder()
     */
    @Test
    public void testConstructors() throws Exception {
        WaveRecorder sut;
        
        //private
        sut = TestAccess.invokeConstructor(WaveRecorder.class);
        Assert.assertNotNull(sut);
        Assert.assertTrue(sut instanceof WaveRecorder);
        Assert.assertNotNull(TestAccess.getFieldValue(sut, "interrupt"));
    }
    
    /**
     * JUnit test of start.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#start(File, AudioFormat)
     * @see WaveRecorder#start(File, AudioFormat, Object)
     * @see WaveRecorder#start(File, int, int, int, boolean, boolean, Object)
     * @see WaveRecorder#start(File, Object)
     */
    @Test
    public void testStart() throws Exception {
        final File output = Filesystem.getTemporaryFile();
        final AudioFormat audioFormat = new AudioFormat(WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN);
        final AtomicBoolean lineSupported = new AtomicBoolean(false);
        long startTime;
        long duration;
        
        PowerMockito.spy(AudioSystem.class);
        PowerMockito.doAnswer((Answer<Boolean>) invocation ->
                lineSupported.get()
        ).when(AudioSystem.class, "isLineSupported", ArgumentMatchers.any(Line.Info.class));
        
        //standard
        lineSupported.set(true);
        startTime = System.currentTimeMillis();
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "start", output, audioFormat));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(duration >= WaveRecorder.RECORDING_THREAD_STATUS_DELAY);
        List.of("recorder", "recorderHandle", "recorderWarning", "line", "output", "format").forEach(e ->
                Assert.assertNotNull(TestAccess.getFieldValue(WaveRecorder.class, e)));
        Assert.assertFalse(TestAccess.getFieldValue(WaveRecorder.class, AtomicBoolean.class, "recorderWarning").get());
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "stop"));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Thread.sleep(250);
        PowerMockito.verifyPrivate(WaveRecorder.class, VerificationModeFactory.times(1))
                .invoke("stop");
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(1));
        AudioSystem.isLineSupported(ArgumentMatchers.any(Line.Info.class));
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(1));
        AudioSystem.getLine(ArgumentMatchers.any(Line.Info.class));
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(1));
        AudioSystem.write(ArgumentMatchers.any(AudioInputStream.class), ArgumentMatchers.eq(AudioFileFormat.Type.WAVE), ArgumentMatchers.eq(output));
        
        //in use
        lineSupported.set(true);
        Assert.assertTrue(WaveRecorder.start(output, audioFormat, getClass()));
        Assert.assertTrue(TestAccess.getFieldValue(WaveRecorder.class, AtomicBoolean.class, "inUse").get());
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertFalse(WaveRecorder.start(output, audioFormat, getClass()));
        Assert.assertTrue(TestAccess.getFieldValue(WaveRecorder.class, AtomicBoolean.class, "inUse").get());
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertTrue(WaveRecorder.stop(getClass()));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertFalse(TestAccess.getFieldValue(WaveRecorder.class, AtomicBoolean.class, "inUse").get());
        Thread.sleep(250);
        PowerMockito.verifyPrivate(WaveRecorder.class, VerificationModeFactory.times(3));
        WaveRecorder.owns(ArgumentMatchers.eq(getClass()));
        PowerMockito.verifyPrivate(WaveRecorder.class, VerificationModeFactory.times(3))
                .invoke("stop");
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(2));
        AudioSystem.isLineSupported(ArgumentMatchers.any(Line.Info.class));
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(2));
        AudioSystem.getLine(ArgumentMatchers.any(Line.Info.class));
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(2));
        AudioSystem.write(ArgumentMatchers.any(AudioInputStream.class), ArgumentMatchers.eq(AudioFileFormat.Type.WAVE), ArgumentMatchers.eq(output));
        
        //recording not supported
        lineSupported.set(false);
        Assert.assertFalse(TestAccess.getFieldValue(WaveRecorder.class, AtomicBoolean.class, "recorderWarning").get());
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "start", output, audioFormat));
        Assert.assertTrue(TestAccess.getFieldValue(WaveRecorder.class, AtomicBoolean.class, "recorderWarning").get());
        lineSupported.set(true);
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "start", output, audioFormat));
        Assert.assertFalse(TestAccess.getFieldValue(WaveRecorder.class, AtomicBoolean.class, "recorderWarning").get());
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "stop"));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Thread.sleep(250);
        PowerMockito.verifyPrivate(WaveRecorder.class, VerificationModeFactory.times(4))
                .invoke("stop");
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(4));
        AudioSystem.isLineSupported(ArgumentMatchers.any(Line.Info.class));
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(3));
        AudioSystem.getLine(ArgumentMatchers.any(Line.Info.class));
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(3));
        AudioSystem.write(ArgumentMatchers.any(AudioInputStream.class), ArgumentMatchers.eq(AudioFileFormat.Type.WAVE), ArgumentMatchers.eq(output));
        
        //recording failure
        lineSupported.set(true);
        TestAccess.setFieldValue(WaveRecorder.class, "inUse", new AtomicBoolean(true));
        PowerMockito.doThrow(new IOException()).when(AudioSystem.class, "write", ArgumentMatchers.any(AudioInputStream.class), ArgumentMatchers.eq(AudioFileFormat.Type.WAVE), ArgumentMatchers.eq(output));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "start", output, audioFormat));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertFalse(TestAccess.getFieldValue(WaveRecorder.class, AtomicBoolean.class, "inUse").get());
        Thread.sleep(250);
        PowerMockito.verifyPrivate(WaveRecorder.class, VerificationModeFactory.times(5))
                .invoke("stop");
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(5));
        AudioSystem.isLineSupported(ArgumentMatchers.any(Line.Info.class));
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(4));
        AudioSystem.getLine(ArgumentMatchers.any(Line.Info.class));
        PowerMockito.verifyStatic(AudioSystem.class, VerificationModeFactory.times(4));
        AudioSystem.write(ArgumentMatchers.any(AudioInputStream.class), ArgumentMatchers.eq(AudioFileFormat.Type.WAVE), ArgumentMatchers.eq(output));
        PowerMockito.doCallRealMethod().when(AudioSystem.class, "write", ArgumentMatchers.any(AudioInputStream.class), ArgumentMatchers.eq(AudioFileFormat.Type.WAVE), ArgumentMatchers.eq(output));
        
        //overloads
        PowerMockito.spy(WaveRecorder.class);
        PowerMockito.doReturn(true).when(WaveRecorder.class, "start", ArgumentMatchers.any(File.class), ArgumentMatchers.any(AudioFormat.class));
        WaveRecorder.start(output, audioFormat, getClass());
        TestAccess.setFieldValue(WaveRecorder.class, "inUse", new AtomicBoolean(false));
        PowerMockito.verifyPrivate(WaveRecorder.class, VerificationModeFactory.times(1))
                .invoke("start", ArgumentMatchers.eq(output), ArgumentMatchers.eq(audioFormat));
        PowerMockito.doReturn(true).when(WaveRecorder.class, "start", ArgumentMatchers.any(File.class), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.any());
        WaveRecorder.start(output, 0, 0, 0, false, false, getClass());
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(2));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(getClass()));
        WaveRecorder.start(output, getClass());
        PowerMockito.verifyStatic(WaveRecorder.class, VerificationModeFactory.times(3));
        WaveRecorder.start(ArgumentMatchers.eq(output), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.eq(getClass()));
        PowerMockito.doCallRealMethod().when(WaveRecorder.class, "start", ArgumentMatchers.any(File.class), ArgumentMatchers.any(AudioFormat.class), ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(WaveRecorder.class, "start", ArgumentMatchers.any(File.class), ArgumentMatchers.any(AudioFormat.class));
        
        //invalid
        Assert.assertFalse(WaveRecorder.start(output, audioFormat, TestUtilsTest.class));
        Assert.assertFalse(WaveRecorder.start(output, audioFormat, new TestUtilsTest()));
        Assert.assertFalse(WaveRecorder.start(output, 0, 0, 0, false, false, TestUtilsTest.class));
        Assert.assertFalse(WaveRecorder.start(output, 0, 0, 0, false, false, new TestUtilsTest()));
        Assert.assertFalse(WaveRecorder.start(output, TestUtilsTest.class));
        Assert.assertFalse(WaveRecorder.start(output, new TestUtilsTest()));
        Assert.assertFalse(WaveRecorder.start(output, null, getClass()));
        Assert.assertFalse(WaveRecorder.start(null, audioFormat, getClass()));
        Assert.assertFalse(WaveRecorder.start(null, 0, 0, 0, false, false, getClass()));
        Assert.assertFalse(WaveRecorder.start(null, null, getClass()));
        Assert.assertFalse(WaveRecorder.start(output, audioFormat, null));
        Assert.assertFalse(WaveRecorder.start(output, 0, 0, 0, false, false, null));
        Assert.assertFalse(WaveRecorder.start(null, null, null));
    }
    
    /**
     * JUnit test of stop.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#stop()
     * @see WaveRecorder#stop(Object)
     */
    @Test
    public void testStop() throws Exception {
        final ExecutorService mockRecorder = Mockito.mock(ExecutorService.class);
        final Future<?> mockRecorderHandle = Mockito.mock(Future.class);
        final TargetDataLine mockLine = Mockito.mock(TargetDataLine.class);
        final File mockOutput = Mockito.mock(File.class);
        final AudioFormat mockAudioFormat = Mockito.mock(AudioFormat.class);
        
        Mockito.doReturn(true).when(mockRecorder).awaitTermination(ArgumentMatchers.anyLong(), ArgumentMatchers.any());
        
        final Runnable fieldInitializer = () ->
                MapUtility.mapOf(
                        new ImmutablePair<>("recorder", mockRecorder),
                        new ImmutablePair<>("recorderHandle", mockRecorderHandle),
                        new ImmutablePair<>("line", mockLine),
                        new ImmutablePair<>("output", mockOutput),
                        new ImmutablePair<>("format", mockAudioFormat)
                ).forEach((String fieldName, Object testValue) ->
                        TestAccess.setFieldValue(WaveRecorder.class, fieldName, testValue));
        
        //standard
        fieldInitializer.run();
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "stop"));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        List.of("recorder", "recorderHandle", "line").forEach(e ->
                Assert.assertNull(TestAccess.getFieldValue(WaveRecorder.class, e)));
        List.of("output", "format").forEach(e ->
                Assert.assertNotNull(TestAccess.getFieldValue(WaveRecorder.class, e)));
        Mockito.verify(mockLine, VerificationModeFactory.times(1))
                .stop();
        Mockito.verify(mockLine, VerificationModeFactory.times(1))
                .close();
        Mockito.verify(mockRecorderHandle, VerificationModeFactory.times(1))
                .cancel(ArgumentMatchers.eq(true));
        Mockito.verify(mockRecorder, VerificationModeFactory.times(1))
                .shutdown();
        Mockito.verify(mockRecorder, VerificationModeFactory.times(1))
                .shutdownNow();
        Mockito.verify(mockRecorder, VerificationModeFactory.times(1))
                .awaitTermination(ArgumentMatchers.eq(1000L), ArgumentMatchers.eq(TimeUnit.MILLISECONDS));
        
        //recording not running
        List.of("recorder", "recorderHandle", "line", "output", "format").forEach(e ->
                TestAccess.setFieldValue(WaveRecorder.class, e, null));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "stop"));
        List.of("recorder", "recorderHandle", "line", "output", "format").forEach(e ->
                Assert.assertNull(TestAccess.getFieldValue(WaveRecorder.class, e)));
        
        //can't stop recorder
        fieldInitializer.run();
        Mockito.doReturn(false).when(mockRecorder).awaitTermination(ArgumentMatchers.anyLong(), ArgumentMatchers.any());
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "stop"));
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        List.of("recorder", "recorderHandle", "line", "output", "format").forEach(e ->
                TestAccess.setFieldValue(WaveRecorder.class, e, null));
        Mockito.verify(mockLine, VerificationModeFactory.times(2))
                .stop();
        Mockito.verify(mockLine, VerificationModeFactory.times(2))
                .close();
        Mockito.verify(mockRecorderHandle, VerificationModeFactory.times(2))
                .cancel(ArgumentMatchers.eq(true));
        Mockito.verify(mockRecorder, VerificationModeFactory.times(2))
                .shutdown();
        Mockito.verify(mockRecorder, VerificationModeFactory.times(2))
                .shutdownNow();
        Mockito.verify(mockRecorder, VerificationModeFactory.times(2))
                .awaitTermination(ArgumentMatchers.eq(1000L), ArgumentMatchers.eq(TimeUnit.MILLISECONDS));
        Mockito.doReturn(true).when(mockRecorder).awaitTermination(ArgumentMatchers.anyLong(), ArgumentMatchers.any());
        
        //invalid
        Assert.assertFalse(WaveRecorder.stop(TestUtilsTest.class));
        Assert.assertFalse(WaveRecorder.stop(new TestUtilsTest()));
        Assert.assertFalse(WaveRecorder.stop(null));
    }
    
    /**
     * JUnit test of isRecorderRunning.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#isRecorderRunning()
     */
    @Test
    public void testIsRecorderRunning() throws Exception {
        final ExecutorService mockRecorder = Mockito.mock(ExecutorService.class);
        final Future<?> mockRecorderHandle = Mockito.mock(Future.class);
        final File output = Filesystem.getTemporaryFile();
        final AudioFormat audioFormat = new AudioFormat(WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN);
        
        //standard
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "start", output, audioFormat));
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "stop"));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        
        //other
        TestAccess.setFieldValue(WaveRecorder.class, "recorder", mockRecorder);
        TestAccess.setFieldValue(WaveRecorder.class, "recorderHandle", mockRecorderHandle);
        Mockito.doReturn(true).when(mockRecorder).awaitTermination(ArgumentMatchers.anyLong(), ArgumentMatchers.any());
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Mockito.doReturn(true).when(mockRecorder).isShutdown();
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Mockito.doReturn(false).when(mockRecorder).isShutdown();
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Mockito.doReturn(true).when(mockRecorder).isTerminated();
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Mockito.doReturn(false).when(mockRecorder).isTerminated();
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Mockito.doReturn(true).when(mockRecorderHandle).isDone();
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Mockito.doReturn(false).when(mockRecorderHandle).isDone();
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Mockito.doReturn(true).when(mockRecorderHandle).isCancelled();
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Mockito.doReturn(false).when(mockRecorderHandle).isCancelled();
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        TestAccess.setFieldValue(WaveRecorder.class, "recorder", null);
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        TestAccess.setFieldValue(WaveRecorder.class, "recorder", mockRecorder);
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        TestAccess.setFieldValue(WaveRecorder.class, "recorderHandle", null);
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        TestAccess.setFieldValue(WaveRecorder.class, "recorderHandle", mockRecorderHandle);
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
        Assert.assertTrue(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "stop"));
        Assert.assertFalse(TestAccess.invokeMethod(WaveRecorder.class, boolean.class, "isRecorderRunning"));
    }
    
    /**
     * JUnit test of getLengthInMilliseconds.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#getLengthInMilliseconds(Object)
     */
    @Test
    public void testGetLengthInMilliseconds() throws Exception {
        final ExecutorService mockRecorder = Mockito.mock(ExecutorService.class);
        final Future<?> mockRecorderHandle = Mockito.mock(Future.class);
        final File mockOutput = Mockito.mock(File.class);
        final AudioFormat audioFormat = new AudioFormat(WaveRecorder.DEFAULT_SAMPLE_RATE, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS, WaveRecorder.DEFAULT_CHANNELS, WaveRecorder.DEFAULT_SIGNED, WaveRecorder.DEFAULT_BIG_ENDIAN);
        
        //standard
        TestAccess.setFieldValue(WaveRecorder.class, "output", mockOutput);
        TestAccess.setFieldValue(WaveRecorder.class, "format", audioFormat);
        TestAccess.setFieldValue(WaveRecorder.class, "recorder", null);
        Mockito.when(mockOutput.exists()).thenReturn(true);
        MapUtility.mapOf(
                new Long[] {176400L, 441000L, 14994L, 8945604895261265L, 0L},
                new Long[] {1000L, 2500L, 85L, 50712045891503L, 0L}
        ).forEach((fileLength, lengthInMilliseconds) -> {
            Mockito.when(mockOutput.length()).thenReturn(fileLength);
            Assert.assertEquals(lengthInMilliseconds.longValue(), WaveRecorder.getLengthInMilliseconds(getClass()));
        });
        
        //invalid
        TestAccess.setFieldValue(WaveRecorder.class, "output", null);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(getClass()));
        TestAccess.setFieldValue(WaveRecorder.class, "output", mockOutput);
        TestAccess.setFieldValue(WaveRecorder.class, "format", null);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(getClass()));
        TestAccess.setFieldValue(WaveRecorder.class, "format", audioFormat);
        Mockito.when(mockOutput.exists()).thenReturn(false);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(getClass()));
        Mockito.when(mockOutput.exists()).thenReturn(true);
        TestAccess.setFieldValue(WaveRecorder.class, "recorder", mockRecorder);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(getClass()));
        TestAccess.setFieldValue(WaveRecorder.class, "recorder", null);
        TestAccess.setFieldValue(WaveRecorder.class, "recorderHandle", mockRecorderHandle);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(getClass()));
        TestAccess.setFieldValue(WaveRecorder.class, "recorderHandle", null);
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(TestUtilsTest.class));
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(new TestUtilsTest()));
        Assert.assertEquals(0L, WaveRecorder.getLengthInMilliseconds(null));
    }
    
    /**
     * JUnit test of recordingEnabled.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#recordingEnabled()
     */
    @Test
    public void testRecordingEnabled() throws Exception {
        final AtomicBoolean lineSupported = new AtomicBoolean(false);
        
        PowerMockito.mockStatic(AudioSystem.class);
        PowerMockito.doAnswer((Answer<Boolean>) invocation ->
                lineSupported.get()
        ).when(AudioSystem.class, "isLineSupported", ArgumentMatchers.any(Line.Info.class));
        
        //standard
        Stream.of(true, false).forEach(e -> {
            lineSupported.set(e);
            Assert.assertEquals(e, WaveRecorder.recordingEnabled());
        });
    }
    
    /**
     * JUnit test of owns.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#owns(Object)
     */
    @Test
    public void testOwns() throws Exception {
        //standard
        WaveRecorder.owns(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .isOwner(ArgumentMatchers.eq(getClass()));
    }
    
    /**
     * JUnit test of own.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#own(Object)
     */
    @Test
    public void testOwn() throws Exception {
        //standard
        WaveRecorder.own(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .acquireOwnership(ArgumentMatchers.eq(getClass()));
    }
    
    /**
     * JUnit test of relinquish.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#relinquish(Object)
     */
    @Test
    public void testRelinquish() throws Exception {
        //standard
        WaveRecorder.relinquish(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .releaseOwnership(ArgumentMatchers.eq(getClass()));
    }
    
    /**
     * JUnit test of manage.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#manage(Object)
     */
    @Test
    public void testManage() throws Exception {
        //standard
        WaveRecorder.manage(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .acquireManagement(ArgumentMatchers.eq(getClass()));
    }
    
    /**
     * JUnit test of relinquishManagement.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#relinquishManagement(Object)
     */
    @Test
    public void testRelinquishManagement() throws Exception {
        //standard
        WaveRecorder.relinquishManagement(getClass());
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .releaseManagement(ArgumentMatchers.eq(getClass()));
    }
    
}
