/*
 * File:    SpeechRecognizerTest.java
 * Package: commons.io.speech
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.speech;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import commons.access.CmdLine;
import commons.access.Filesystem;
import commons.access.OperatingSystem;
import commons.access.Project;
import commons.io.HotKeyManager;
import commons.io.SystemIn;
import commons.io.WaveRecorder;
import commons.math.BoundUtility;
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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of SpeechRecognizer.
 *
 * @see SpeechRecognizer
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpeechRecognizer.class, HotKeyManager.class, Filesystem.class, OperatingSystem.class, CmdLine.class, SystemIn.class})
public class SpeechRecognizerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SpeechRecognizerTest.class);
    
    
    //Constants
    
    /**
     * The test resources directory for this class.
     */
    private static final File testResources = Project.testResourcesDir(SpeechRecognizer.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private SpeechRecognizer sut;
    
    
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
        PowerMockito.spy(SpeechRecognizer.class);
        sut = Mockito.spy(SpeechRecognizer.class);
        
        TestUtils.setField(sut, "pocketsphinx", null);
        TestUtils.setField(sut, "speechStream", null);
        TestUtils.setField(sut, "speechBuffer", null);
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        TestUtils.setField(sut, "mode", null);
        TestUtils.setField(sut, "adaptionMatrix", null);
        TestUtils.setField(sut, "adaptionMatrix", null);
        TestUtils.setField(sut, "captureSpeechTrigger", null);
        TestUtils.setField(sut, "captureSpeechTriggerCallback", null);
        TestUtils.setField(sut, "recording", new AtomicBoolean(false));
        TestUtils.setField(sut, "minimumRecordingLength", SpeechRecognizer.DEFAULT_MINIMUM_RECORDING_LENGTH);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "killPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        TestUtils.setField(sut, "setup", new AtomicBoolean(false));
        TestUtils.setField(SpeechRecognizer.class, "instance", null);
        TestUtils.setField(SpeechRecognizer.class, "instanced", new AtomicBoolean(false));
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
     * @see SpeechRecognizer#SPHINX_PHONETIC_LANGUAGE_MODEL
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
        Assert.assertEquals("en-us-phone.lm.bin", SpeechRecognizer.SPHINX_PHONETIC_LANGUAGE_MODEL);
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
        //instantiation
        TestUtils.setField(SpeechRecognizer.class, "instanced", new AtomicBoolean(false));
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(SpeechRecognizer.class, "instanced")).get());
        sut = Mockito.spy(SpeechRecognizer.getInstance());
        Assert.assertFalse(((String) TestUtils.getField(sut, "runPocketSphinxCmd")).isEmpty());
        Assert.assertFalse(((String) TestUtils.getField(sut, "decodeRecordingCmd")).isEmpty());
        Assert.assertFalse(((String) TestUtils.getField(sut, "killPocketSphinxCmd")).isEmpty());
        Assert.assertNotNull(TestUtils.getField(sut, "captureSpeechTrigger"));
        Assert.assertNotNull(TestUtils.getField(sut, "captureSpeechTriggerCallback"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "setup")).get());
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(SpeechRecognizer.class, "instanced")).get());
        
        //direct call
        sut = Mockito.spy(SpeechRecognizer.class);
        TestUtils.setField(sut, "adaptionMatrix", null);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        TestUtils.setField(sut, "killPocketSphinxCmd", "");
        TestUtils.setField(sut, "setup", new AtomicBoolean(false));
        TestUtils.setField(SpeechRecognizer.class, "instanced", new AtomicBoolean(false));
        Assert.assertTrue(Whitebox.invokeMethod(sut, "setup"));
        Assert.assertFalse(((String) TestUtils.getField(sut, "runPocketSphinxCmd")).isEmpty());
        Assert.assertFalse(((String) TestUtils.getField(sut, "decodeRecordingCmd")).isEmpty());
        Assert.assertFalse(((String) TestUtils.getField(sut, "killPocketSphinxCmd")).isEmpty());
        Assert.assertNotNull(TestUtils.getField(sut, "captureSpeechTrigger"));
        Assert.assertNotNull(TestUtils.getField(sut, "captureSpeechTriggerCallback"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "setup")).get());
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(SpeechSynthesizer.class, "instanced")).get());
        
        //already setup
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "setup")).get());
        Assert.assertFalse(Whitebox.invokeMethod(sut, "setup"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "setup")).get());
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(SpeechSynthesizer.class, "instanced")).get());
    }
    
    /**
     * JUnit test of initializeCommands.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#initializeCommands()
     */
    @Test
    public void testInitializeCommands() throws Exception {
        PowerMockito.mockStatic(OperatingSystem.class);
        File adaptionMatrix = new File(Project.DATA_DIR, "mllr_matrix");
        
        //standard Windows
        PowerMockito.when(OperatingSystem.class, "isWindows").thenReturn(true);
        TestUtils.setField(sut, "adaptionMatrix", null);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        TestUtils.setField(sut, "killPocketSphinxCmd", "");
        Whitebox.invokeMethod(sut, "initializeCommands");
        Assert.assertEquals(
                "cd \"" + SpeechRecognizer.SPHINX_DIRECTORY.getAbsolutePath() + "\" && pocketsphinx_continuous.exe -inmic yes -hmm model/en-us/en-us -lm model/en-us/en-us.lm.bin -dict model/en-us/cmudict-en-us.dict -logfn nul",
                TestUtils.getField(sut, "runPocketSphinxCmd"));
        Assert.assertEquals(
                "cd \"" + SpeechRecognizer.SPHINX_DIRECTORY.getAbsolutePath() + "\" && pocketsphinx_continuous.exe -infile \"%s\" -hmm model/en-us/en-us -lm model/en-us/en-us.lm.bin -dict model/en-us/cmudict-en-us.dict -logfn nul",
                TestUtils.getField(sut, "decodeRecordingCmd"));
        Assert.assertEquals(
                "Taskkill /IM pocketsphinx_continuous.exe /F",
                TestUtils.getField(sut, "killPocketSphinxCmd"));
        
        //adaption matrix Windows
        TestUtils.setField(sut, "adaptionMatrix", adaptionMatrix);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        TestUtils.setField(sut, "killPocketSphinxCmd", "");
        Whitebox.invokeMethod(sut, "initializeCommands");
        Assert.assertEquals(
                "cd \"" + SpeechRecognizer.SPHINX_DIRECTORY.getAbsolutePath() + "\" && pocketsphinx_continuous.exe -inmic yes -hmm model/en-us/en-us -lm model/en-us/en-us.lm.bin -dict model/en-us/cmudict-en-us.dict -mllr \"" + adaptionMatrix.getAbsolutePath() + "\" -logfn nul",
                TestUtils.getField(sut, "runPocketSphinxCmd"));
        Assert.assertEquals(
                "cd \"" + SpeechRecognizer.SPHINX_DIRECTORY.getAbsolutePath() + "\" && pocketsphinx_continuous.exe -infile \"%s\" -hmm model/en-us/en-us -lm model/en-us/en-us.lm.bin -dict model/en-us/cmudict-en-us.dict -mllr \"" + adaptionMatrix.getAbsolutePath() + "\" -logfn nul",
                TestUtils.getField(sut, "decodeRecordingCmd"));
        Assert.assertEquals(
                "Taskkill /IM pocketsphinx_continuous.exe /F",
                TestUtils.getField(sut, "killPocketSphinxCmd"));
        
        //standard Linux
        PowerMockito.when(OperatingSystem.class, "isWindows").thenReturn(false);
        TestUtils.setField(sut, "adaptionMatrix", null);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        TestUtils.setField(sut, "killPocketSphinxCmd", "");
        Whitebox.invokeMethod(sut, "initializeCommands");
        Assert.assertEquals(
                "cd \"" + SpeechRecognizer.SPHINX_DIRECTORY.getAbsolutePath() + "\" && LD_LIBRARY_PATH=$(pwd) pocketsphinx_continuous -inmic yes -hmm model/en-us/en-us -lm model/en-us/en-us.lm.bin -dict model/en-us/cmudict-en-us.dict &> /dev/null",
                TestUtils.getField(sut, "runPocketSphinxCmd"));
        Assert.assertEquals(
                "cd \"" + SpeechRecognizer.SPHINX_DIRECTORY.getAbsolutePath() + "\" && LD_LIBRARY_PATH=$(pwd) pocketsphinx_continuous -infile \"%s\" -hmm model/en-us/en-us -lm model/en-us/en-us.lm.bin -dict model/en-us/cmudict-en-us.dict &> /dev/null",
                TestUtils.getField(sut, "decodeRecordingCmd"));
        Assert.assertEquals(
                "pkill -f pocketsphinx_continuous",
                TestUtils.getField(sut, "killPocketSphinxCmd"));
        
        //adaption matrix Linux
        TestUtils.setField(sut, "adaptionMatrix", adaptionMatrix);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        TestUtils.setField(sut, "killPocketSphinxCmd", "");
        Whitebox.invokeMethod(sut, "initializeCommands");
        Assert.assertEquals(
                "cd \"" + SpeechRecognizer.SPHINX_DIRECTORY.getAbsolutePath() + "\" && LD_LIBRARY_PATH=$(pwd) pocketsphinx_continuous -inmic yes -hmm model/en-us/en-us -lm model/en-us/en-us.lm.bin -dict model/en-us/cmudict-en-us.dict -mllr \"" + adaptionMatrix.getAbsolutePath() + "\" &> /dev/null",
                TestUtils.getField(sut, "runPocketSphinxCmd"));
        Assert.assertEquals(
                "cd \"" + SpeechRecognizer.SPHINX_DIRECTORY.getAbsolutePath() + "\" && LD_LIBRARY_PATH=$(pwd) pocketsphinx_continuous -infile \"%s\" -hmm model/en-us/en-us -lm model/en-us/en-us.lm.bin -dict model/en-us/cmudict-en-us.dict -mllr \"" + adaptionMatrix.getAbsolutePath() + "\" &> /dev/null",
                TestUtils.getField(sut, "decodeRecordingCmd"));
        Assert.assertEquals(
                "pkill -f pocketsphinx_continuous",
                TestUtils.getField(sut, "killPocketSphinxCmd"));
    }
    
    /**
     * JUnit test of initialize.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#initialize(SpeechRecognizer.RecognitionMode)
     */
    @SuppressWarnings("ConfusingArgumentToVarargsMethod")
    @Test
    public void testInitialize() throws Exception {
        Whitebox.invokeMethod(sut, "setup");
        TestUtils.setField(sut, "pocketsphinx", null);
        TestUtils.setField(sut, "speechStream", null);
        TestUtils.setField(sut, "speechBuffer", null);
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        HotKeyManager.HotKey captureSpeechTrigger = Mockito.mock(HotKeyManager.HotKey.class);
        TestUtils.setField(sut, "captureSpeechTrigger", captureSpeechTrigger);
        
        //standard
        
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.CONTINUOUS));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.CONTINUOUS, sut.getMode());
        Assert.assertNotNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNotNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .clearSpeech();
        
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.TRIGGERED));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.TRIGGERED, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertTrue(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNotNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .clearSpeech();
        
        TestUtils.setField(sut, "recording", new AtomicBoolean(false));
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.ON_DEMAND));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNotNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .clearSpeech();
        Mockito.verify(captureSpeechTrigger, VerificationModeFactory.noMoreInteractions())
                .release();
        
        TestUtils.setField(sut, "recording", new AtomicBoolean(true));
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.OFF));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(4))
                .clearSpeech();
        Mockito.verify(captureSpeechTrigger, VerificationModeFactory.times(1))
                .release();
        
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.ON_DEMAND));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNotNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(5))
                .clearSpeech();
        
        TestUtils.setField(sut, "recording", new AtomicBoolean(false));
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.ON_DEMAND);
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.CONTINUOUS));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.CONTINUOUS, sut.getMode());
        Assert.assertNotNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNotNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(6))
                .clearSpeech();
        Mockito.verify(captureSpeechTrigger, VerificationModeFactory.noMoreInteractions())
                .release();
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.OFF));
        
        TestUtils.setField(sut, "recording", new AtomicBoolean(true));
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.ON_DEMAND);
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.TRIGGERED));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.TRIGGERED, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertTrue(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNotNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(8))
                .clearSpeech();
        Mockito.verify(captureSpeechTrigger, VerificationModeFactory.times(2))
                .release();
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.OFF));
        
        Assert.assertTrue(TestUtils.setField(sut, "mode", null)); //default to OFF
        Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.ON_DEMAND); //same mode
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNotNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(10))
                .clearSpeech();
        
        Assert.assertTrue(Whitebox.invokeMethod(sut, "initialize", SpeechRecognizer.RecognitionMode.OFF));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(11))
                .clearSpeech();
        
        //invalid
        
        Assert.assertFalse(Whitebox.invokeMethod(sut, "initialize", null));
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        Mockito.verify(sut, VerificationModeFactory.times(11))
                .clearSpeech();
    }
    
    /**
     * JUnit test of start.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#start()
     */
    @Test
    public void testStart() throws Exception {
        //standard
        
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        TestUtils.setField(sut, "mode", null);
        Assert.assertTrue(sut.start());
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "active")).get());
        Assert.assertEquals(SpeechRecognizer.DEFAULT_SPHINX_MODE, sut.getMode());
        
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertTrue(sut.start());
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "active")).get());
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.TRIGGERED, sut.getMode());
        
        //invalid
        
        TestUtils.setField(sut, "active", new AtomicBoolean(true));
        TestUtils.setField(sut, "mode", null);
        Assert.assertFalse(sut.start());
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "active")).get());
        Assert.assertNull(sut.getMode());
    }
    
    /**
     * JUnit test of stop.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#stop()
     */
    @Test
    public void testStop() throws Exception {
        //standard
        
        TestUtils.setField(sut, "active", new AtomicBoolean(true));
        TestUtils.setField(sut, "mode", null);
        Assert.assertTrue(sut.stop());
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(sut, "active")).get());
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, sut.getMode());
        
        TestUtils.setField(sut, "active", new AtomicBoolean(true));
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertTrue(sut.stop());
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(sut, "active")).get());
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, sut.getMode());
        
        //invalid
        
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        TestUtils.setField(sut, "mode", null);
        Assert.assertFalse(sut.stop());
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(sut, "active")).get());
        Assert.assertNull(sut.getMode());
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
        PipedOutputStream writer = new PipedOutputStream();
        PipedInputStream reader = new PipedInputStream();
        writer.connect(reader);
        BufferedReader speechStream = new BufferedReader(new InputStreamReader(reader));
        AtomicReference<String> speechBuffer = new AtomicReference<>("");
        long startTime;
        long duration;
        ScheduledExecutorService thread;
        
        //standard
        
        sut.setMode(SpeechRecognizer.RecognitionMode.CONTINUOUS);
        Assert.assertNull(sut.getSpeech(false));
        TestUtils.setField(sut, "speechStream", speechStream);
        writer.write("test\n".getBytes(StandardCharsets.UTF_8));
        Assert.assertEquals("test", sut.getSpeech(false));
        Assert.assertNull(sut.getSpeech(false));
        writer.write(StringUtility.repeatString("test\n", 5).getBytes(StandardCharsets.UTF_8));
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals("test", sut.getSpeech(false));
        }
        Assert.assertNull(sut.getSpeech(false));
        writer.write("Allocating 3 buffers of 5012 samples each\ntest\n".getBytes(StandardCharsets.UTF_8));
        Assert.assertEquals("test", sut.getSpeech(false));
        Assert.assertNull(sut.getSpeech(false));
        TestUtils.setField(sut, "speechStream", null);
        
        sut.setMode(SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertNull(sut.getSpeech(false));
        TestUtils.setField(sut, "speechBuffer", speechBuffer);
        speechBuffer.set("test");
        Assert.assertEquals("test", sut.getSpeech(false));
        Assert.assertEquals("", speechBuffer.get());
        Assert.assertNull(sut.getSpeech(false));
        speechBuffer.set(StringUtility.repeatString("test\n", 5));
        Assert.assertEquals(StringUtility.repeatString("test\n", 5), sut.getSpeech(false));
        Assert.assertEquals("", speechBuffer.get());
        Assert.assertNull(sut.getSpeech(false));
        TestUtils.setField(sut, "speechBuffer", null);
        
        sut.setMode(SpeechRecognizer.RecognitionMode.ON_DEMAND);
        Assert.assertNull(sut.getSpeech(false));
        TestUtils.setField(sut, "speechBuffer", speechBuffer);
        speechBuffer.set("test");
        Assert.assertEquals("test", sut.getSpeech(false));
        Assert.assertEquals("", speechBuffer.get());
        Assert.assertNull(sut.getSpeech(false));
        speechBuffer.set(StringUtility.repeatString("test\n", 5));
        Assert.assertEquals(StringUtility.repeatString("test\n", 5), sut.getSpeech(false));
        Assert.assertEquals("", speechBuffer.get());
        Assert.assertNull(sut.getSpeech(false));
        TestUtils.setField(sut, "speechBuffer", null);
        
        //wait
        
        sut.setMode(SpeechRecognizer.RecognitionMode.CONTINUOUS);
        Assert.assertNull(sut.getSpeech(true));
        TestUtils.setField(sut, "speechStream", speechStream);
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
        Assert.assertEquals("test", sut.getSpeech(true));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.9), (500 * 1.125)));
        thread.schedule(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    writer.write("test\n".getBytes(StandardCharsets.UTF_8));
                    writer.flush();
                }
            } catch (InterruptedException | IOException ignored) {
            }
        }, 0, TimeUnit.MILLISECONDS);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals("test", sut.getSpeech(true));
        }
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.9), (500 * 1.125)));
        thread.schedule(() -> {
            try {
                Thread.sleep(250);
                writer.write("Allocating 3 buffers of 5012 samples each\n".getBytes(StandardCharsets.UTF_8));
                writer.flush();
                Thread.sleep(250);
                writer.write("test\n".getBytes(StandardCharsets.UTF_8));
                writer.flush();
            } catch (InterruptedException | IOException ignored) {
            }
        }, 0, TimeUnit.MILLISECONDS);
        startTime = System.currentTimeMillis();
        Assert.assertEquals("test", sut.getSpeech(true));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.9), (500 * 1.125)));
        TestUtils.setField(sut, "speechStream", null);
        
        sut.setMode(SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertNull(sut.getSpeech(true));
        TestUtils.setField(sut, "speechBuffer", speechBuffer);
        thread.schedule(() -> {
            try {
                Thread.sleep(500);
                speechBuffer.set("test");
            } catch (InterruptedException ignored) {
            }
        }, 0, TimeUnit.MILLISECONDS);
        startTime = System.currentTimeMillis();
        Assert.assertEquals("test", sut.getSpeech(true));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.9), (500 * 1.125)));
        Assert.assertEquals("", speechBuffer.get());
        thread.schedule(() -> {
            try {
                Thread.sleep(500);
                speechBuffer.set(StringUtility.repeatString("test\n", 5));
            } catch (InterruptedException ignored) {
            }
        }, 0, TimeUnit.MILLISECONDS);
        startTime = System.currentTimeMillis();
        Assert.assertEquals(StringUtility.repeatString("test\n", 5), sut.getSpeech(true));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.9), (500 * 1.125)));
        Assert.assertEquals("", speechBuffer.get());
        TestUtils.setField(sut, "speechBuffer", null);
        
        sut.setMode(SpeechRecognizer.RecognitionMode.ON_DEMAND);
        Assert.assertNull(sut.getSpeech(true));
        TestUtils.setField(sut, "speechBuffer", speechBuffer);
        thread.schedule(() -> {
            try {
                Thread.sleep(500);
                speechBuffer.set("test");
            } catch (InterruptedException ignored) {
            }
        }, 0, TimeUnit.MILLISECONDS);
        startTime = System.currentTimeMillis();
        Assert.assertEquals("test", sut.getSpeech(true));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.9), (500 * 1.125)));
        Assert.assertEquals("", speechBuffer.get());
        thread.schedule(() -> {
            try {
                Thread.sleep(500);
                speechBuffer.set(StringUtility.repeatString("test\n", 5));
            } catch (InterruptedException ignored) {
            }
        }, 0, TimeUnit.MILLISECONDS);
        startTime = System.currentTimeMillis();
        Assert.assertEquals(StringUtility.repeatString("test\n", 5), sut.getSpeech(true));
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.9), (500 * 1.125)));
        Assert.assertEquals("", speechBuffer.get());
        TestUtils.setField(sut, "speechBuffer", null);
        
        //default wait
        
        sut = Mockito.mock(SpeechRecognizer.class);
        Mockito.when(sut.getSpeech()).thenCallRealMethod();
        sut.getSpeech();
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .getSpeech(false);
    }
    
    /**
     * JUnit test of clearSpeech.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#clearSpeech()
     */
    @Test
    public void testClearSpeech() throws Exception {
        InputStream inputStream = new ByteArrayInputStream(StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8));
        BufferedReader speechStream = new BufferedReader(new InputStreamReader(inputStream));
        Assert.assertTrue(speechStream.ready());
        AtomicReference<String> speechBuffer = new AtomicReference<>(StringUtility.repeatString("test", 50));
        
        //standard
        TestUtils.setField(sut, "speechStream", speechStream);
        TestUtils.setField(sut, "speechBuffer", speechBuffer);
        sut.clearSpeech();
        Assert.assertNotNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertNotNull(TestUtils.getField(sut, "speechBuffer"));
        Assert.assertFalse(speechStream.ready());
        Assert.assertEquals("", speechBuffer.get());
        
        //invalid
        TestUtils.setField(sut, "speechStream", null);
        TestUtils.setField(sut, "speechBuffer", null);
        TestUtils.assertNoException(() ->
                sut.clearSpeech());
    }
    
    /**
     * JUnit test of startRecording.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#startRecording()
     */
    @Test
    public void testStartRecording() throws Exception {
        sut.start();
        HotKeyManager.HotKey captureSpeechTrigger = Mockito.mock(HotKeyManager.HotKey.class);
        TestUtils.setField(sut, "captureSpeechTrigger", captureSpeechTrigger);
        HotKeyManager.HotKeyCallback captureSpeechTriggerCallback = Mockito.mock(HotKeyManager.HotKeyCallback.class);
        TestUtils.setField(sut, "captureSpeechTriggerCallback", captureSpeechTriggerCallback);
        
        //standard
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        Assert.assertTrue(sut.startRecording());
        TestUtils.setField(sut, "recording", new AtomicBoolean(true));
        Mockito.verify(captureSpeechTriggerCallback, VerificationModeFactory.times(1))
                .hit();
        
        //already started
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        Assert.assertTrue(sut.isRecording());
        Assert.assertFalse(sut.startRecording());
        Mockito.verify(captureSpeechTriggerCallback, VerificationModeFactory.noMoreInteractions())
                .hit();
        
        //invalid
        sut.setMode(SpeechRecognizer.RecognitionMode.CONTINUOUS);
        Assert.assertFalse(sut.startRecording());
        sut.setMode(SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertFalse(sut.startRecording());
        sut.setMode(SpeechRecognizer.RecognitionMode.OFF);
        Assert.assertFalse(sut.startRecording());
    }
    
    /**
     * JUnit test of finalizeRecording.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#finalizeRecording()
     */
    @Test
    public void testFinalizeRecording() throws Exception {
        sut.start();
        HotKeyManager.HotKey captureSpeechTrigger = Mockito.mock(HotKeyManager.HotKey.class);
        TestUtils.setField(sut, "captureSpeechTrigger", captureSpeechTrigger);
        HotKeyManager.HotKeyCallback captureSpeechTriggerCallback = Mockito.mock(HotKeyManager.HotKeyCallback.class);
        TestUtils.setField(sut, "captureSpeechTriggerCallback", captureSpeechTriggerCallback);
        TestUtils.setField(sut, "recording", new AtomicBoolean(true));
        
        //standard
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        Assert.assertTrue(sut.finalizeRecording());
        TestUtils.setField(sut, "recording", new AtomicBoolean(false));
        Mockito.verify(captureSpeechTriggerCallback, VerificationModeFactory.times(1))
                .release();
        
        //already started
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        Assert.assertFalse(sut.isRecording());
        Assert.assertFalse(sut.finalizeRecording());
        Mockito.verify(captureSpeechTriggerCallback, VerificationModeFactory.noMoreInteractions())
                .release();
        
        //invalid
        sut.setMode(SpeechRecognizer.RecognitionMode.CONTINUOUS);
        Assert.assertFalse(sut.finalizeRecording());
        sut.setMode(SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertFalse(sut.finalizeRecording());
        sut.setMode(SpeechRecognizer.RecognitionMode.OFF);
        Assert.assertFalse(sut.finalizeRecording());
    }
    
    /**
     * JUnit test of getInstance.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        sut = null;
        PowerMockito.spy(SpeechRecognizer.class);
        Assert.assertNull(TestUtils.getField(SpeechRecognizer.class, "instance"));
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(SpeechRecognizer.class, "instanced")).get());
        
        //standard
        sut = SpeechRecognizer.getInstance();
        Assert.assertNotNull(sut);
        Assert.assertNotNull(TestUtils.getField(SpeechRecognizer.class, "instance"));
        Assert.assertEquals(sut, TestUtils.getField(SpeechRecognizer.class, "instance"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(SpeechRecognizer.class, "instanced")).get());
        
        //already instanced
        SpeechRecognizer oldSut = sut;
        sut = null;
        sut = SpeechRecognizer.getInstance();
        Assert.assertNotNull(sut);
        Assert.assertEquals(oldSut, sut);
        Assert.assertEquals(sut, TestUtils.getField(SpeechRecognizer.class, "instance"));
    }
    
    /**
     * JUnit test of isActive.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#isActive()
     */
    @Test
    public void testIsActive() throws Exception {
        //standard
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        Assert.assertFalse(sut.isActive());
        TestUtils.setField(sut, "active", new AtomicBoolean(true));
        Assert.assertTrue(sut.isActive());
        
        //invalid
        TestUtils.setField(sut, "active", null);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.isActive());
    }
    
    /**
     * JUnit test of getMode.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#getMode()
     */
    @Test
    public void testGetMode() throws Exception {
        //standard
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.CONTINUOUS);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.CONTINUOUS, sut.getMode());
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.TRIGGERED, sut.getMode());
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.ON_DEMAND);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        TestUtils.setField(sut, "mode", SpeechRecognizer.RecognitionMode.OFF);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, sut.getMode());
        
        //invalid
        TestUtils.setField(sut, "mode", null);
        Assert.assertNull(sut.getMode());
    }
    
    /**
     * JUnit test of isRecording.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#isRecording()
     */
    @Test
    public void testIsRecording() throws Exception {
        //standard
        TestUtils.setField(sut, "recording", new AtomicBoolean(false));
        Assert.assertFalse(sut.isRecording());
        TestUtils.setField(sut, "recording", new AtomicBoolean(true));
        Assert.assertTrue(sut.isRecording());
        
        //invalid
        TestUtils.setField(sut, "recording", null);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.isRecording());
    }
    
    /**
     * JUnit test of setMode.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#setMode(SpeechRecognizer.RecognitionMode)
     */
    @Test
    public void testSetMode() throws Exception {
        //active
        
        Whitebox.invokeMethod(sut, "setup");
        TestUtils.setField(sut, "pocketsphinx", null);
        TestUtils.setField(sut, "speechStream", null);
        TestUtils.setField(sut, "speechBuffer", null);
        sut.start();
        
        sut.setMode(SpeechRecognizer.RecognitionMode.CONTINUOUS);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.CONTINUOUS, sut.getMode());
        Assert.assertNotNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNotNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        
        sut.setMode(SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.TRIGGERED, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertTrue(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNotNull(TestUtils.getField(sut, "speechBuffer"));
        
        sut.setMode(SpeechRecognizer.RecognitionMode.ON_DEMAND);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNotNull(TestUtils.getField(sut, "speechBuffer"));
        
        sut.setMode(SpeechRecognizer.RecognitionMode.OFF);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        
        //inactive
        
        sut.stop();
        TestUtils.setField(sut, "pocketsphinx", null);
        TestUtils.setField(sut, "speechStream", null);
        TestUtils.setField(sut, "speechBuffer", null);
        
        sut.setMode(SpeechRecognizer.RecognitionMode.CONTINUOUS);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.CONTINUOUS, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        
        sut.setMode(SpeechRecognizer.RecognitionMode.TRIGGERED);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.TRIGGERED, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        
        sut.setMode(SpeechRecognizer.RecognitionMode.ON_DEMAND);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.ON_DEMAND, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        
        sut.setMode(SpeechRecognizer.RecognitionMode.OFF);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
        
        //invalid
        
        TestUtils.setField(sut, "pocketsphinx", null);
        TestUtils.setField(sut, "speechStream", null);
        TestUtils.setField(sut, "speechBuffer", null);
        sut.start();
        
        sut.setMode(null);
        Assert.assertEquals(SpeechRecognizer.RecognitionMode.OFF, sut.getMode());
        Assert.assertNull(TestUtils.getField(sut, "pocketsphinx"));
        Assert.assertNull(TestUtils.getField(sut, "speechStream"));
        Assert.assertFalse(HotKeyManager.hasHotkey((HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger")));
        Assert.assertNull(TestUtils.getField(sut, "speechBuffer"));
    }
    
    /**
     * JUnit test of setMinimumRecordingLength.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#setMinimumRecordingLength(long)
     */
    @Test
    public void testSetMinimumRecordingLength() throws Exception {
        Assert.assertEquals(SpeechRecognizer.DEFAULT_MINIMUM_RECORDING_LENGTH, TestUtils.getField(sut, "minimumRecordingLength"));
        
        //standard
        sut.setMinimumRecordingLength(100L);
        Assert.assertEquals(100L, TestUtils.getField(sut, "minimumRecordingLength"));
        sut.setMinimumRecordingLength(5000L);
        Assert.assertEquals(5000L, TestUtils.getField(sut, "minimumRecordingLength"));
        sut.setMinimumRecordingLength(0L);
        Assert.assertEquals(0L, TestUtils.getField(sut, "minimumRecordingLength"));
        sut.setMinimumRecordingLength(987908456051269450L);
        Assert.assertEquals(987908456051269450L, TestUtils.getField(sut, "minimumRecordingLength"));
        
        //invalid
        sut.setMinimumRecordingLength(-1L);
        Assert.assertEquals(0L, TestUtils.getField(sut, "minimumRecordingLength"));
        sut.setMinimumRecordingLength(-4984046579178104L);
        Assert.assertEquals(0L, TestUtils.getField(sut, "minimumRecordingLength"));
    }
    
    /**
     * JUnit test of setCaptureSpeechTrigger.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#setCaptureSpeechTrigger(int, boolean, boolean, boolean, boolean)
     */
    @Test
    public void testSetCaptureSpeechTrigger() throws Exception {
        Whitebox.invokeMethod(sut, "setup");
        sut.setMode(SpeechRecognizer.RecognitionMode.OFF);
        HotKeyManager.HotKey captureSpeechTrigger = (HotKeyManager.HotKey) TestUtils.getField(sut, "captureSpeechTrigger");
        Assert.assertEquals("[Ctrl]", captureSpeechTrigger.toString());
        PowerMockito.mockStatic(HotKeyManager.class);
        
        //standard
        
        PowerMockito.when(HotKeyManager.class, "hasHotkey", ArgumentMatchers.any(HotKeyManager.HotKey.class)).thenReturn(true);
        TestUtils.setField(sut, "captureSpeechTrigger", captureSpeechTrigger);
        sut.setCaptureSpeechTrigger(KeyEvent.VK_R, false, false, false, false);
        Assert.assertEquals("[R]", TestUtils.getField(sut, "captureSpeechTrigger").toString());
        PowerMockito.verifyStatic(HotKeyManager.class, VerificationModeFactory.times(1));
        HotKeyManager.unregisterHotkey(ArgumentMatchers.eq(captureSpeechTrigger));
        
        TestUtils.setField(sut, "captureSpeechTrigger", captureSpeechTrigger);
        sut.setCaptureSpeechTrigger(KeyEvent.VK_R, true, false, true, true);
        Assert.assertEquals("[Ctrl-Alt-Meta-R]", TestUtils.getField(sut, "captureSpeechTrigger").toString());
        PowerMockito.verifyStatic(HotKeyManager.class, VerificationModeFactory.times(2));
        HotKeyManager.unregisterHotkey(ArgumentMatchers.eq(captureSpeechTrigger));
        
        //previously null
        
        TestUtils.setField(sut, "captureSpeechTrigger", null);
        sut.setCaptureSpeechTrigger(HotKeyManager.HotKey.NO_KEY, true, true, true, true);
        Assert.assertEquals("[Ctrl-Shift-Alt-Meta]", TestUtils.getField(sut, "captureSpeechTrigger").toString());
        PowerMockito.verifyStatic(HotKeyManager.class, VerificationModeFactory.times(2));
        HotKeyManager.unregisterHotkey(ArgumentMatchers.eq(captureSpeechTrigger));
        
        //unregistered
        
        PowerMockito.when(HotKeyManager.class, "hasHotkey", ArgumentMatchers.any(HotKeyManager.HotKey.class)).thenReturn(false);
        sut.setCaptureSpeechTrigger(KeyEvent.VK_Z, false, true, false, false);
        Assert.assertEquals("[Shift-Z]", TestUtils.getField(sut, "captureSpeechTrigger").toString());
        PowerMockito.verifyStatic(HotKeyManager.class, VerificationModeFactory.times(2));
        HotKeyManager.unregisterHotkey(ArgumentMatchers.eq(captureSpeechTrigger));
        
        sut.setCaptureSpeechTrigger(-99, false, true, false, false);
        Assert.assertEquals("[Shift]", TestUtils.getField(sut, "captureSpeechTrigger").toString());
        PowerMockito.verifyStatic(HotKeyManager.class, VerificationModeFactory.times(2));
        HotKeyManager.unregisterHotkey(ArgumentMatchers.eq(captureSpeechTrigger));
        
        //register
        
        sut.setMode(SpeechRecognizer.RecognitionMode.TRIGGERED);
        HotKeyManager.registerHotkey(ArgumentMatchers.any(HotKeyManager.HotKey.class));
        sut.setCaptureSpeechTrigger(KeyEvent.VK_R, false, false, false, false);
        Assert.assertEquals("[R]", TestUtils.getField(sut, "captureSpeechTrigger").toString());
        PowerMockito.verifyStatic(HotKeyManager.class, VerificationModeFactory.times(1));
        HotKeyManager.registerHotkey(ArgumentMatchers.any(HotKeyManager.HotKey.class));
    }
    
    /**
     * JUnit test of setAcousticModelAdaptionMatrix.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer#setAcousticModelAdaptionMatrix(File)
     */
    @Test
    public void testSetAcousticModelAdaptionMatrix() throws Exception {
        PowerMockito.mockStatic(OperatingSystem.class);
        Assert.assertNull(TestUtils.getField(sut, "adaptionMatrix"));
        File adaptionMatrix = Filesystem.createTemporaryFile("", "mllr_matrix");
        Assert.assertTrue(adaptionMatrix.exists());
        
        //standard Windows
        PowerMockito.when(OperatingSystem.class, "isWindows").thenReturn(true);
        TestUtils.setField(sut, "adaptionMatrix", null);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        sut.setAcousticModelAdaptionMatrix(adaptionMatrix);
        Assert.assertEquals(adaptionMatrix, TestUtils.getField(sut, "adaptionMatrix"));
        Assert.assertTrue(((String) TestUtils.getField(sut, "runPocketSphinxCmd")).contains("-mllr \"" + adaptionMatrix.getAbsolutePath() + "\""));
        Assert.assertTrue(((String) TestUtils.getField(sut, "decodeRecordingCmd")).contains("-mllr \"" + adaptionMatrix.getAbsolutePath() + "\""));
        
        //standard Linux
        PowerMockito.when(OperatingSystem.class, "isWindows").thenReturn(false);
        TestUtils.setField(sut, "adaptionMatrix", null);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        sut.setAcousticModelAdaptionMatrix(adaptionMatrix);
        Assert.assertEquals(adaptionMatrix, TestUtils.getField(sut, "adaptionMatrix"));
        Assert.assertTrue(((String) TestUtils.getField(sut, "runPocketSphinxCmd")).contains("-mllr \"" + adaptionMatrix.getAbsolutePath() + "\""));
        Assert.assertTrue(((String) TestUtils.getField(sut, "decodeRecordingCmd")).contains("-mllr \"" + adaptionMatrix.getAbsolutePath() + "\""));
        
        //does not exist
        File missingAdaptionMatrix = new File("nonexistant_mllr_matrix");
        Assert.assertFalse(missingAdaptionMatrix.exists());
        TestUtils.setField(sut, "adaptionMatrix", null);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        sut.setAcousticModelAdaptionMatrix(missingAdaptionMatrix);
        Assert.assertEquals(missingAdaptionMatrix, TestUtils.getField(sut, "adaptionMatrix"));
        Assert.assertFalse(((String) TestUtils.getField(sut, "runPocketSphinxCmd")).contains("-mllr \"" + adaptionMatrix.getAbsolutePath() + "\""));
        Assert.assertFalse(((String) TestUtils.getField(sut, "decodeRecordingCmd")).contains("-mllr \"" + adaptionMatrix.getAbsolutePath() + "\""));
        
        //invalid
        TestUtils.setField(sut, "adaptionMatrix", null);
        TestUtils.setField(sut, "runPocketSphinxCmd", "");
        TestUtils.setField(sut, "decodeRecordingCmd", "");
        sut.setAcousticModelAdaptionMatrix(null);
        Assert.assertNull(TestUtils.getField(sut, "adaptionMatrix"));
        Assert.assertFalse(((String) TestUtils.getField(sut, "runPocketSphinxCmd")).contains("-mllr \"" + adaptionMatrix.getAbsolutePath() + "\""));
        Assert.assertFalse(((String) TestUtils.getField(sut, "decodeRecordingCmd")).contains("-mllr \"" + adaptionMatrix.getAbsolutePath() + "\""));
    }
    
    /**
     * JUnit test of SpeechRecorder.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechRecorder
     * @see #testSpeechRecorderHit()
     * @see #testSpeechRecorderRelease()
     */
    @Test
    public void testSpeechRecorder() throws Exception {
        Whitebox.invokeMethod(sut, "setup");
        sut.setMode(SpeechRecognizer.RecognitionMode.ON_DEMAND);
        sut.start();
        PowerMockito.mockStatic(CmdLine.class);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString()).thenReturn("release");
        
        Object captureSpeechTriggerCallback = TestUtils.getField(sut, "captureSpeechTriggerCallback");
        Assert.assertTrue(captureSpeechTriggerCallback instanceof HotKeyManager.HotKeyCallback);
        TestUtils.assertMethodExists(captureSpeechTriggerCallback.getClass(),
                "hit");
        TestUtils.assertMethodExists(captureSpeechTriggerCallback.getClass(),
                "release");
        Assert.assertEquals(TestUtils.getField(sut, "recording"), TestUtils.getField(captureSpeechTriggerCallback, "recording"));
        Assert.assertNull(TestUtils.getField(captureSpeechTriggerCallback, "wavFile"));
        Assert.assertNull(TestUtils.getField(captureSpeechTriggerCallback, "wav"));
        
        testSpeechRecorderHit();
        testSpeechRecorderRelease();
        
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
    }
    
    /**
     * Helper method for JUnit test of SpeechRecorder for the method hit.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechRecorder#hit()
     */
    private void testSpeechRecorderHit() throws Exception {
        Object captureSpeechTriggerCallback = TestUtils.getField(sut, "captureSpeechTriggerCallback");
        File wavFile = Filesystem.createTemporaryFile(".wav", "speech-recorder-test");
        
        //hit
        TestUtils.setField(captureSpeechTriggerCallback, "recording", new AtomicBoolean(false));
        TestUtils.setField(captureSpeechTriggerCallback, "wavFile", null);
        TestUtils.setField(captureSpeechTriggerCallback, "wav", null);
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(captureSpeechTriggerCallback, "recording")).get());
        Whitebox.invokeMethod(captureSpeechTriggerCallback, "hit");
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(captureSpeechTriggerCallback, "recording")).get());
        Assert.assertTrue(((File) TestUtils.getField(captureSpeechTriggerCallback, "wavFile")).exists());
        WaveRecorder wav = (WaveRecorder) TestUtils.getField(captureSpeechTriggerCallback, "wav");
        Assert.assertNotNull(TestUtils.getField(captureSpeechTriggerCallback, "wav"));
        wav.stop();
        
        
        //already hit
        TestUtils.setField(captureSpeechTriggerCallback, "recording", new AtomicBoolean(true));
        TestUtils.setField(captureSpeechTriggerCallback, "wavFile", null);
        TestUtils.setField(captureSpeechTriggerCallback, "wav", null);
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(captureSpeechTriggerCallback, "recording")).get());
        Whitebox.invokeMethod(captureSpeechTriggerCallback, "hit");
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(captureSpeechTriggerCallback, "recording")).get());
        Assert.assertNull(TestUtils.getField(captureSpeechTriggerCallback, "wavFile"));
        Assert.assertNull(TestUtils.getField(captureSpeechTriggerCallback, "wav"));
        TestUtils.setField(captureSpeechTriggerCallback, "recording", new AtomicBoolean(false));
    }
    
    /**
     * Helper method for JUnit test of SpeechRecorder for the method release.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechRecorder#release()
     */
    private void testSpeechRecorderRelease() throws Exception {
        Object captureSpeechTriggerCallback = TestUtils.getField(sut, "captureSpeechTriggerCallback");
        AtomicReference<String> speechBuffer = (AtomicReference<String>) TestUtils.getField(sut, "speechBuffer");
        File wavFile = Filesystem.createTemporaryFile(".wav", "speech-recorder-test");
        WaveRecorder wav = Mockito.mock(WaveRecorder.class);
        Mockito.when(wav.getLengthInMilliseconds()).thenReturn(SpeechRecognizer.DEFAULT_MINIMUM_RECORDING_LENGTH - 1);
        
        //release
        TestUtils.setField(captureSpeechTriggerCallback, "recording", new AtomicBoolean(true));
        TestUtils.setField(captureSpeechTriggerCallback, "wavFile", wavFile);
        TestUtils.setField(captureSpeechTriggerCallback, "wav", wav);
        Whitebox.invokeMethod(captureSpeechTriggerCallback, "release");
        Assert.assertEquals("release", speechBuffer.getAndSet(""));
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(captureSpeechTriggerCallback, "recording")).get());
        Assert.assertFalse(((File) TestUtils.getField(captureSpeechTriggerCallback, "wavFile")).exists());
        Assert.assertNull(TestUtils.getField(captureSpeechTriggerCallback, "wav"));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.executeCmd(ArgumentMatchers.eq(((String) TestUtils.getField(sut, "decodeRecordingCmd"))
                .replace("%s", ((File) TestUtils.getField(captureSpeechTriggerCallback, "wavFile")).getAbsolutePath())));
        TestUtils.setField(captureSpeechTriggerCallback, "recording", new AtomicBoolean(true));
        TestUtils.setField(captureSpeechTriggerCallback, "wavFile", null);
        TestUtils.setField(captureSpeechTriggerCallback, "wav", null);
        Whitebox.invokeMethod(captureSpeechTriggerCallback, "release");
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.noMoreInteractions());
        CmdLine.executeCmd(ArgumentMatchers.anyString());
        
        //already released
        TestUtils.setField(captureSpeechTriggerCallback, "recording", new AtomicBoolean(false));
        TestUtils.setField(captureSpeechTriggerCallback, "wavFile", wavFile);
        TestUtils.setField(captureSpeechTriggerCallback, "wav", new WaveRecorder(wavFile));
        Whitebox.invokeMethod(captureSpeechTriggerCallback, "release");
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(captureSpeechTriggerCallback, "recording")).get());
        Assert.assertNotNull(TestUtils.getField(captureSpeechTriggerCallback, "wavFile"));
        Assert.assertNotNull(TestUtils.getField(captureSpeechTriggerCallback, "wav"));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.noMoreInteractions());
        CmdLine.executeCmd(ArgumentMatchers.anyString());
        
        //minumum length not met
        TestUtils.setField(captureSpeechTriggerCallback, "recording", new AtomicBoolean(true));
        TestUtils.setField(captureSpeechTriggerCallback, "wavFile", wavFile);
        TestUtils.setField(captureSpeechTriggerCallback, "wav", wav);
        Whitebox.invokeMethod(captureSpeechTriggerCallback, "release");
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(captureSpeechTriggerCallback, "recording")).get());
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.executeCmd(ArgumentMatchers.eq(((String) TestUtils.getField(sut, "decodeRecordingCmd"))
                .replace("%s", ((File) TestUtils.getField(captureSpeechTriggerCallback, "wavFile")).getAbsolutePath())));
        sut.setMode(SpeechRecognizer.RecognitionMode.TRIGGERED);
        TestUtils.setField(captureSpeechTriggerCallback, "recording", new AtomicBoolean(true));
        TestUtils.setField(captureSpeechTriggerCallback, "wavFile", wavFile);
        TestUtils.setField(captureSpeechTriggerCallback, "wav", wav);
        Whitebox.invokeMethod(captureSpeechTriggerCallback, "release");
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(captureSpeechTriggerCallback, "recording")).get());
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.noMoreInteractions());
        CmdLine.executeCmd(ArgumentMatchers.anyString());
        
        Mockito.when(wav.getLengthInMilliseconds()).thenCallRealMethod();
    }
    
    /**
     * JUnit test of SpeechTrainer.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechTrainer
     * @see #testSpeechTrainerTrain(SpeechRecognizer.SpeechTrainer, File)
     * @see #testSpeechTrainerPrepareTrainingDirectory(SpeechRecognizer.SpeechTrainer, File)
     * @see #testSpeechTrainerCollectRecordings(SpeechRecognizer.SpeechTrainer, File)
     * @see #testSpeechTrainerGenerateAcousticFeatureFiles(SpeechRecognizer.SpeechTrainer, File)
     * @see #testSpeechTrainerAccumulateObservationCounts(SpeechRecognizer.SpeechTrainer, File)
     * @see #testSpeechTrainerCreateMllrTransformation(SpeechRecognizer.SpeechTrainer, File)
     */
    @Test
    public void testSpeechTrainer() throws Exception {
        SpeechRecognizer.SpeechTrainer trainer = Mockito.mock(SpeechRecognizer.SpeechTrainer.class);
        TestUtils.setField(SpeechRecognizer.SpeechTrainer.class, "instance", trainer);
        TestUtils.setField(SpeechRecognizer.SpeechTrainer.class, "isWindows", true);
        PowerMockito.mockStatic(SystemIn.class);
        PowerMockito.doReturn(true).when(SystemIn.class, "own", ArgumentMatchers.eq(SpeechRecognizer.class));
        
        File trainingDirectory = Filesystem.createTemporaryDirectory();
        testSpeechTrainerTrain(trainer, trainingDirectory);
        testSpeechTrainerPrepareTrainingDirectory(trainer, trainingDirectory);
        testSpeechTrainerCollectRecordings(trainer, trainingDirectory);
        testSpeechTrainerGenerateAcousticFeatureFiles(trainer, trainingDirectory);
        testSpeechTrainerAccumulateObservationCounts(trainer, trainingDirectory);
        testSpeechTrainerCreateMllrTransformation(trainer, trainingDirectory);
        
        PowerMockito.spy(Filesystem.class);
        PowerMockito.doReturn(trainingDirectory).when(Filesystem.class, "createTemporaryDirectory");
        PowerMockito.doReturn(true).when(Filesystem.class, "deleteDirectory", ArgumentMatchers.any(File.class));
        File adaptionMatrix = new File(Project.TMP_DIR, "test_mllr_matrix");
        Assert.assertTrue(trainer.train(adaptionMatrix));
        Assert.assertTrue(adaptionMatrix.exists());
        Assert.assertEquals(Filesystem.checksum(new File(trainingDirectory, "mllr_matrix")), Filesystem.checksum(adaptionMatrix));
        Assert.assertTrue(Filesystem.deleteFile(adaptionMatrix));
        PowerMockito.verifyStatic(Filesystem.class, VerificationModeFactory.times(1));
        Filesystem.deleteDirectory(ArgumentMatchers.eq(trainingDirectory));
        PowerMockito.verifyStatic(SystemIn.class, VerificationModeFactory.times(6));
        SystemIn.relinquish();
    }
    
    /**
     * Helper method for JUnit test of SpeechTrainer for the method train.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechTrainer#performTraining(File)
     * @see SpeechRecognizer.SpeechTrainer#train(File)
     */
    private void testSpeechTrainerTrain(SpeechRecognizer.SpeechTrainer trainer, File trainingDirectory) throws Exception {
        File adaptionMatrix = Filesystem.createTemporaryFile();
        
        //performTraining
        
        SpeechRecognizer.SpeechTrainer.performTraining(adaptionMatrix);
        Mockito.verify(trainer, VerificationModeFactory.times(1))
                .train(ArgumentMatchers.any(File.class));
        
        //train
        
        Mockito.when(trainer.train(ArgumentMatchers.any(File.class))).thenCallRealMethod();
        Mockito.when(trainer.prepareTrainingDirectory(ArgumentMatchers.any(File.class))).thenReturn(true);
        Mockito.when(trainer.collectRecordings(ArgumentMatchers.any(File.class))).thenReturn(true);
        Mockito.when(trainer.generateAcousticFeatureFiles(ArgumentMatchers.any(File.class))).thenReturn(true);
        Mockito.when(trainer.accumulateObservationCounts(ArgumentMatchers.any(File.class))).thenReturn(true);
        Mockito.when(trainer.createMllrTransformation(ArgumentMatchers.any(File.class))).thenReturn(true);
        trainer.train(adaptionMatrix);
        Mockito.verify(trainer, VerificationModeFactory.times(1))
                .prepareTrainingDirectory(ArgumentMatchers.any(File.class));
        Mockito.verify(trainer, VerificationModeFactory.times(1))
                .collectRecordings(ArgumentMatchers.any(File.class));
        Mockito.verify(trainer, VerificationModeFactory.times(1))
                .generateAcousticFeatureFiles(ArgumentMatchers.any(File.class));
        Mockito.verify(trainer, VerificationModeFactory.times(1))
                .accumulateObservationCounts(ArgumentMatchers.any(File.class));
        Mockito.verify(trainer, VerificationModeFactory.times(1))
                .createMllrTransformation(ArgumentMatchers.any(File.class));
        
        Mockito.when(trainer.prepareTrainingDirectory(ArgumentMatchers.any(File.class))).thenReturn(false);
        Assert.assertFalse(trainer.train(adaptionMatrix));
        Mockito.when(trainer.prepareTrainingDirectory(ArgumentMatchers.any(File.class))).thenReturn(true);
        
        Mockito.when(trainer.collectRecordings(ArgumentMatchers.any(File.class))).thenReturn(false);
        Assert.assertFalse(trainer.train(adaptionMatrix));
        Mockito.when(trainer.collectRecordings(ArgumentMatchers.any(File.class))).thenReturn(true);
        
        Mockito.when(trainer.generateAcousticFeatureFiles(ArgumentMatchers.any(File.class))).thenReturn(false);
        Assert.assertFalse(trainer.train(adaptionMatrix));
        Mockito.when(trainer.generateAcousticFeatureFiles(ArgumentMatchers.any(File.class))).thenReturn(true);
        
        Mockito.when(trainer.accumulateObservationCounts(ArgumentMatchers.any(File.class))).thenReturn(false);
        Assert.assertFalse(trainer.train(adaptionMatrix));
        Mockito.when(trainer.accumulateObservationCounts(ArgumentMatchers.any(File.class))).thenReturn(true);
        
        Mockito.when(trainer.createMllrTransformation(ArgumentMatchers.any(File.class))).thenReturn(false);
        Assert.assertFalse(trainer.train(adaptionMatrix));
        Mockito.when(trainer.createMllrTransformation(ArgumentMatchers.any(File.class))).thenReturn(true);
        
        PowerMockito.doReturn(false).when(SystemIn.class, "own", ArgumentMatchers.eq(SpeechRecognizer.class));
        Assert.assertFalse(trainer.train(adaptionMatrix));
        PowerMockito.doReturn(true).when(SystemIn.class, "own", ArgumentMatchers.eq(SpeechRecognizer.class));
    }
    
    /**
     * Helper method for JUnit test of SpeechTrainer for the method prepareTrainingDirectory.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechTrainer#prepareTrainingDirectory(File)
     */
    private void testSpeechTrainerPrepareTrainingDirectory(SpeechRecognizer.SpeechTrainer trainer, File trainingDirectory) throws Exception {
        File languageDirectory = new File(trainingDirectory, "en-us");
        Mockito.when(trainer.prepareTrainingDirectory(ArgumentMatchers.any(File.class))).thenCallRealMethod();
        
        //Windows
        TestUtils.setField(SpeechRecognizer.SpeechTrainer.class, "isWindows", true);
        Assert.assertTrue(trainer.prepareTrainingDirectory(trainingDirectory));
        Assert.assertEquals(19, Filesystem.getFilesAndDirsRecursively(trainingDirectory).size());
        Assert.assertTrue(new File(trainingDirectory, "en-us").exists());
        Assert.assertTrue(new File(languageDirectory, "feat.params").exists());
        Assert.assertTrue(new File(languageDirectory, "mdef").exists());
        Assert.assertTrue(new File(languageDirectory, "means").exists());
        Assert.assertTrue(new File(languageDirectory, "mixture_weights").exists());
        Assert.assertTrue(new File(languageDirectory, "noisedict").exists());
        Assert.assertTrue(new File(languageDirectory, "README").exists());
        Assert.assertTrue(new File(languageDirectory, "sendump").exists());
        Assert.assertTrue(new File(languageDirectory, "transition_matrices").exists());
        Assert.assertTrue(new File(languageDirectory, "variances").exists());
        Assert.assertTrue(new File(trainingDirectory, "arctic20.fileids").exists());
        Assert.assertTrue(new File(trainingDirectory, "arctic20.transcription").exists());
        Assert.assertTrue(new File(trainingDirectory, "bw.exe").exists());
        Assert.assertTrue(new File(trainingDirectory, "cmudict-en-us.dict").exists());
        Assert.assertTrue(new File(trainingDirectory, "en-us.lm.bin").exists());
        Assert.assertTrue(new File(trainingDirectory, "en-us-phone.lm.bin").exists());
        Assert.assertTrue(new File(trainingDirectory, "mllr_solve.exe").exists());
        Assert.assertTrue(new File(trainingDirectory, "sphinx_fe.exe").exists());
        Assert.assertTrue(new File(trainingDirectory, "sphinxbase.dll").exists());
        
        //Linux
        TestUtils.setField(SpeechRecognizer.SpeechTrainer.class, "isWindows", false);
        Assert.assertTrue(trainer.prepareTrainingDirectory(trainingDirectory));
        Assert.assertEquals(20, Filesystem.getFilesAndDirsRecursively(trainingDirectory).size());
        Assert.assertTrue(new File(trainingDirectory, "en-us").exists());
        Assert.assertTrue(new File(languageDirectory, "feat.params").exists());
        Assert.assertTrue(new File(languageDirectory, "mdef").exists());
        Assert.assertTrue(new File(languageDirectory, "means").exists());
        Assert.assertTrue(new File(languageDirectory, "mixture_weights").exists());
        Assert.assertTrue(new File(languageDirectory, "noisedict").exists());
        Assert.assertTrue(new File(languageDirectory, "README").exists());
        Assert.assertTrue(new File(languageDirectory, "sendump").exists());
        Assert.assertTrue(new File(languageDirectory, "transition_matrices").exists());
        Assert.assertTrue(new File(languageDirectory, "variances").exists());
        Assert.assertTrue(new File(trainingDirectory, "arctic20.fileids").exists());
        Assert.assertTrue(new File(trainingDirectory, "arctic20.transcription").exists());
        Assert.assertTrue(new File(trainingDirectory, "bw").exists());
        Assert.assertTrue(new File(trainingDirectory, "cmudict-en-us.dict").exists());
        Assert.assertTrue(new File(trainingDirectory, "en-us.lm.bin").exists());
        Assert.assertTrue(new File(trainingDirectory, "en-us-phone.lm.bin").exists());
        Assert.assertTrue(new File(trainingDirectory, "libsphinxad.so.3").exists());
        Assert.assertTrue(new File(trainingDirectory, "libsphinxbase.so.3").exists());
        Assert.assertTrue(new File(trainingDirectory, "mllr_solve").exists());
        Assert.assertTrue(new File(trainingDirectory, "sphinx_fe").exists());
        
        //prepare
        TestUtils.setField(SpeechRecognizer.SpeechTrainer.class, "isWindows", OperatingSystem.isWindows());
        Assert.assertTrue(trainer.prepareTrainingDirectory(trainingDirectory));
        
        Mockito.when(trainer.prepareTrainingDirectory(ArgumentMatchers.any(File.class))).thenReturn(true);
    }
    
    /**
     * Helper method for JUnit test of SpeechTrainer for the method collectRecordings.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechTrainer#collectRecordings(File)
     */
    private void testSpeechTrainerCollectRecordings(SpeechRecognizer.SpeechTrainer trainer, File trainingDirectory) throws Exception {
        PowerMockito.doReturn("").when(SystemIn.class, "nextLine", ArgumentMatchers.eq(SpeechRecognizer.class));
        Mockito.when(trainer.collectRecordings(ArgumentMatchers.any(File.class))).thenCallRealMethod();
        
        //standard
        Assert.assertTrue(trainer.collectRecordings(trainingDirectory));
        for (int i = 1; i <= 20; i++) {
            File recording = new File(trainingDirectory, "arctic_00" + StringUtility.padZero(i, 2) + ".wav");
            Assert.assertTrue(recording.exists());
        }
        PowerMockito.verifyStatic(SystemIn.class, VerificationModeFactory.times(40));
        SystemIn.nextLine(SpeechRecognizer.class);
        
        //prepare
        for (File recording : Filesystem.getFiles(testResources, "arctic_00\\d{2}\\.wav")) {
            Filesystem.copyFile(recording, trainingDirectory, true);
        }
        
        Mockito.when(trainer.collectRecordings(ArgumentMatchers.any(File.class))).thenReturn(true);
    }
    
    /**
     * Helper method for JUnit test of SpeechTrainer for the method generateAcousticFeatureFiles.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechTrainer#generateAcousticFeatureFiles(File)
     */
    private void testSpeechTrainerGenerateAcousticFeatureFiles(SpeechRecognizer.SpeechTrainer trainer, File trainingDirectory) throws Exception {
        Mockito.when(trainer.generateAcousticFeatureFiles(ArgumentMatchers.any(File.class))).thenCallRealMethod();
        
        Assert.assertTrue(trainer.generateAcousticFeatureFiles(trainingDirectory));
        for (int i = 1; i <= 20; i++) {
            File mfc = new File(trainingDirectory, "arctic_00" + StringUtility.padZero(i, 2) + ".mfc");
            Assert.assertTrue(mfc.exists());
            Assert.assertTrue(mfc.length() > 0);
        }
        
        Mockito.when(trainer.generateAcousticFeatureFiles(ArgumentMatchers.any(File.class))).thenReturn(true);
    }
    
    /**
     * Helper method for JUnit test of SpeechTrainer for the method accumulateObservationCounts.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechTrainer#accumulateObservationCounts(File)
     */
    private void testSpeechTrainerAccumulateObservationCounts(SpeechRecognizer.SpeechTrainer trainer, File trainingDirectory) throws Exception {
        Mockito.when(trainer.accumulateObservationCounts(ArgumentMatchers.any(File.class))).thenCallRealMethod();
        
        Assert.assertTrue(trainer.accumulateObservationCounts(trainingDirectory));
        Assert.assertTrue(new File(trainingDirectory, "gauden_counts").exists());
        Assert.assertTrue(new File(trainingDirectory, "gauden_counts").length() > 0);
        Assert.assertTrue(new File(trainingDirectory, "mixw_counts").exists());
        Assert.assertTrue(new File(trainingDirectory, "mixw_counts").length() > 0);
        Assert.assertTrue(new File(trainingDirectory, "tmat_counts").exists());
        Assert.assertTrue(new File(trainingDirectory, "tmat_counts").length() > 0);
        
        Mockito.when(trainer.accumulateObservationCounts(ArgumentMatchers.any(File.class))).thenReturn(true);
    }
    
    /**
     * Helper method for JUnit test of SpeechTrainer for the method createMllrTransformation.
     *
     * @throws Exception When there is an exception.
     * @see SpeechRecognizer.SpeechTrainer#createMllrTransformation(File)
     */
    private void testSpeechTrainerCreateMllrTransformation(SpeechRecognizer.SpeechTrainer trainer, File trainingDirectory) throws Exception {
        Mockito.when(trainer.createMllrTransformation(ArgumentMatchers.any(File.class))).thenCallRealMethod();
        
        Assert.assertTrue(trainer.createMllrTransformation(trainingDirectory));
        Assert.assertTrue(new File(trainingDirectory, "mllr_matrix").exists());
        Assert.assertTrue(new File(trainingDirectory, "mllr_matrix").length() > 0);
        
        Mockito.when(trainer.createMllrTransformation(ArgumentMatchers.any(File.class))).thenReturn(true);
    }
    
}