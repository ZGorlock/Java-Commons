/*
 * File:    WaveRecordingTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

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
 * JUnit test of WaveRecording.
 *
 * @see WaveRecording
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({WaveRecording.class})
public class WaveRecordingTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(WaveRecordingTest.class);
    
    
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
     * @see WaveRecording#RECORDING_THREAD_STATUS_DELAY
     * @see WaveRecording#DEFAULT_SAMPLE_RATE
     * @see WaveRecording#DEFAULT_SAMPLE_SIZE_IN_BITS
     * @see WaveRecording#DEFAULT_CHANNELS
     * @see WaveRecording#DEFAULT_SIGNED
     * @see WaveRecording#DEFAULT_BIG_ENDIAN
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(100, WaveRecording.RECORDING_THREAD_STATUS_DELAY);
        Assert.assertEquals(44100, WaveRecording.DEFAULT_SAMPLE_RATE, 0.0000001);
        Assert.assertEquals(16, WaveRecording.DEFAULT_SAMPLE_SIZE_IN_BITS);
        Assert.assertEquals(2, WaveRecording.DEFAULT_CHANNELS);
        Assert.assertTrue(WaveRecording.DEFAULT_SIGNED);
        Assert.assertTrue(WaveRecording.DEFAULT_BIG_ENDIAN);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecording#WaveRecording(String)
     */
    @Test
    public void testConstructors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of start.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecording#start(float, int, int, boolean, boolean)
     * @see WaveRecording#start()
     */
    @Test
    public void testStart() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of stop.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecording#stop()
     */
    @Test
    public void testStop() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getLengthInMilliseconds.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecording#getLengthInMilliseconds()
     */
    @Test
    public void testGetLengthInMilliseconds() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of speechCaptureEnabled.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecording#speechCaptureEnabled()
     */
    @Test
    public void testSpeechCaptureEnabled() throws Exception {
        //TODO
    }
    
}
