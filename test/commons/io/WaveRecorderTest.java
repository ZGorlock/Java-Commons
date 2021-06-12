/*
 * File:    WaveRecorderTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.io.File;

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
 * JUnit test of WaveRecorder.
 *
 * @see WaveRecorder
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({WaveRecorder.class})
public class WaveRecorderTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(WaveRecorderTest.class);
    
    
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
        Assert.assertEquals(44100, WaveRecorder.DEFAULT_SAMPLE_RATE, 0.0000001);
        Assert.assertEquals(16, WaveRecorder.DEFAULT_SAMPLE_SIZE_IN_BITS);
        Assert.assertEquals(2, WaveRecorder.DEFAULT_CHANNELS);
        Assert.assertTrue(WaveRecorder.DEFAULT_SIGNED);
        Assert.assertTrue(WaveRecorder.DEFAULT_BIG_ENDIAN);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#WaveRecorder(File)
     */
    @Test
    public void testConstructors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of start.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#start(float, int, int, boolean, boolean)
     * @see WaveRecorder#start()
     */
    @Test
    public void testStart() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of stop.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#stop()
     */
    @Test
    public void testStop() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getLengthInMilliseconds.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#getLengthInMilliseconds()
     */
    @Test
    public void testGetLengthInMilliseconds() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of speechCaptureEnabled.
     *
     * @throws Exception When there is an exception.
     * @see WaveRecorder#speechCaptureEnabled()
     */
    @Test
    public void testSpeechCaptureEnabled() throws Exception {
        //TODO
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
        //TODO
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
        //TODO
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
        //TODO
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
        //TODO
    }
    
}
