/*
 * File:    SpeechRecognizerTest.java
 * Package: commons.io.speech
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.speech;

import java.io.File;

import commons.string.StringUtility;
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
 * JUnit test of SpeechRecognizer.
 *
 * @see SpeechRecognizer
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpeechRecognizer.class})
public class SpeechRecognizerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SpeechRecognizerTest.class);
    
    
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
     * @see SpeechRecognizer#RESOURCE_DIRECTORY
     * @see SpeechRecognizer#SPHINX_DIRECTORY
     * @see SpeechRecognizer#SPHINX_LANGUAGE
     * @see SpeechRecognizer#BINARY_NAME
     * @see SpeechRecognizer#SPHINX_DICTIONARY
     * @see SpeechRecognizer#SPHINX_LANGUAGE_MODEL
     * @see SpeechRecognizer#SPHINX_TRANSCRIPTION_FILE
     * @see SpeechRecognizer#SPHINX_TRANSCRIPTION_FILEIDS_FILE
     * @see SpeechRecognizer#DEFAULT_SPHINX_MODE
     * @see SpeechRecognizer#RECORDING_SAMPLE_RATE
     * @see SpeechRecognizer#RECORDING_SAMPLE_SIZE_IN_BITS
     * @see SpeechRecognizer#RECORDING_CHANNELS
     * @see SpeechRecognizer#RECORDING_SIGNED
     * @see SpeechRecognizer#RECORDING_BIG_ENDIAN
     * @see SpeechRecognizer#DEFAULT_MINIMUM_RECORDING_LENGTH
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals("resources/commons/io/speech/SpeechRecognizer", StringUtility.fileString(SpeechRecognizer.RESOURCE_DIRECTORY, false));
        Assert.assertEquals("resources/commons/io/speech/SpeechRecognizer/pocketsphinx", StringUtility.fileString(SpeechRecognizer.SPHINX_DIRECTORY, false));
        Assert.assertEquals("en-us", SpeechRecognizer.SPHINX_LANGUAGE);
        Assert.assertEquals("pocketsphinx_continuous", SpeechRecognizer.BINARY_NAME);
        Assert.assertEquals("cmudict-en-us.dict", SpeechRecognizer.SPHINX_DICTIONARY);
        Assert.assertEquals("en-us.lm.bin", SpeechRecognizer.SPHINX_LANGUAGE_MODEL);
        Assert.assertEquals("resources/commons/io/speech/SpeechRecognizer/arctic20.transcription", StringUtility.fileString(SpeechRecognizer.SPHINX_TRANSCRIPTION_FILE, false));
        Assert.assertEquals("resources/commons/io/speech/SpeechRecognizer/arctic20.fileids", StringUtility.fileString(SpeechRecognizer.SPHINX_TRANSCRIPTION_FILEIDS_FILE, false));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, SpeechRecognizer.DEFAULT_SPHINX_MODE);
        Assert.assertEquals(16000, SpeechRecognizer.RECORDING_SAMPLE_RATE);
        Assert.assertEquals(16, SpeechRecognizer.RECORDING_SAMPLE_SIZE_IN_BITS);
        Assert.assertEquals(1, SpeechRecognizer.RECORDING_CHANNELS);
        Assert.assertTrue(SpeechRecognizer.RECORDING_SIGNED);
        Assert.assertFalse(SpeechRecognizer.RECORDING_BIG_ENDIAN);
        Assert.assertEquals(750L, SpeechRecognizer.DEFAULT_MINIMUM_RECORDING_LENGTH);
    }
    
    /**
     * JUnit test of RecognitionMode.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.RecognitionMode
     */
    @Test
    public void testRecognitionMode() throws Exception {
        Assert.assertEquals(4, SpeechRecognizer.RecognitionMode.values().length);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.CONTINUOUS, SpeechRecognizer.RecognitionMode.values()[0]);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.TRIGGERED, SpeechRecognizer.RecognitionMode.values()[1]);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, SpeechRecognizer.RecognitionMode.values()[2]);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, SpeechRecognizer.RecognitionMode.values()[3]);
    }
    
    /**
     * JUnit test of setup.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#setup()
     */
    @Test
    public void testSetup() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of initializeCommands.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#initializeCommands()
     */
    @Test
    public void testInitializeCommands() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of initialize.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#initialize(SpeechRecognizer.RecognitionMode)
     */
    @Test
    public void testInitialize() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of start.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#start()
     */
    @Test
    public void testStart() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of stop.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#stop()
     */
    @Test
    public void testStop() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getSpeech.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#getSpeech(boolean)
     * @see SpeechRecognizer#getSpeech()
     */
    @Test
    public void testGetSpeech() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of clearSpeech.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#clearSpeech()
     */
    @Test
    public void testClearSpeech() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of startRecording.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#startRecording()
     */
    @Test
    public void testStartRecording() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of finalizeRecording.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#finalizeRecording()
     */
    @Test
    public void testFinalizeRecording() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getInstance.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setRecognitionMode.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#setRecognitionMode(SpeechRecognizer.RecognitionMode)
     */
    @Test
    public void testSetRecognitionMode() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setMinimumRecordingLength.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#setMinimumRecordingLength(long)
     */
    @Test
    public void testSetMinimumRecordingLength() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setCaptureSpeechTrigger.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#setCaptureSpeechTrigger(int, boolean, boolean, boolean, boolean)
     */
    @Test
    public void testSetCaptureSpeechTrigger() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setAcousticModelAdaptionMatrix.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#setAcousticModelAdaptionMatrix(File)
     */
    @Test
    public void testSetAcousticModelAdaptionMatrix() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of SpeechRecorder.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechRecorder
     */
    @Test
    public void testSpeechRecorder() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of SpeechTrainer.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechTrainer
     */
    @Test
    public void testSpeechTrainer() throws Exception {
        //TODO
    }
    
}
