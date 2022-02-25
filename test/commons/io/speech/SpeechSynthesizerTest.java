/*
 * File:    SpeechSynthesizerTest.java
 * Package: commons.io.speech
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.speech;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import commons.math.BoundUtility;
import commons.test.TestUtils;
import marytts.MaryInterface;
import marytts.util.data.audio.AudioPlayer;
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
 * JUnit test of SpeechSynthesizer.
 *
 * @see SpeechSynthesizer
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({SpeechSynthesizer.class})
public class SpeechSynthesizerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SpeechSynthesizerTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private SpeechSynthesizer sut;
    
    /**
     * A Mary TTS Interface to use for testing.
     */
    private MaryInterface marytts;
    
    /**
     * An Audio Player to use for testing.
     */
    private AudioPlayer audioPlayer;
    
    
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
        PowerMockito.spy(SpeechSynthesizer.class);
        sut = Mockito.spy(SpeechSynthesizer.class);
        
        marytts = Mockito.spy(TestUtils.getFieldValue(sut, MaryInterface.class, "marytts"));
        TestUtils.setFieldValue(sut, "marytts", marytts);
        
        audioPlayer = Mockito.spy(TestUtils.getFieldValue(sut, AudioPlayer.class, "audioPlayer"));
        TestUtils.setFieldValue(sut, "audioPlayer", audioPlayer);
        
        TestUtils.setFieldValue(sut, "speaking", new AtomicBoolean(false));
        TestUtils.setFieldValue(sut, "voice", SpeechSynthesizer.DEFAULT_VOICE);
        TestUtils.setFieldValue(sut, "volume", SpeechSynthesizer.DEFAULT_VOLUME);
        TestUtils.setFieldValue(sut, "effects", new HashMap<>());
        TestUtils.setFieldValue(sut, "setup", new AtomicBoolean(false));
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instance", null);
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instanced", new AtomicBoolean(false));
        TestUtils.setFieldValue(SpeechSynthesizer.class, "loggingConfigured", new AtomicBoolean(false));
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
     * @see SpeechSynthesizer#DEFAULT_VOICE
     * @see SpeechSynthesizer#DEFAULT_VOLUME
     * @see SpeechSynthesizer#DEFAULT_QUIET_MODE
     * @see SpeechSynthesizer#LETTER_PRONUNCIATIONS
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(SpeechSynthesizer.Voice.TOM, SpeechSynthesizer.DEFAULT_VOICE);
        Assert.assertEquals(75.0f, SpeechSynthesizer.DEFAULT_VOLUME, TestUtils.DELTA_FLOAT);
        Assert.assertFalse(SpeechSynthesizer.DEFAULT_QUIET_MODE);
        
        Assert.assertEquals(26, SpeechSynthesizer.LETTER_PRONUNCIATIONS.size());
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                SpeechSynthesizer.LETTER_PRONUNCIATIONS.put("test", "test")); //unmodifiable
        Assert.assertEquals(26, SpeechSynthesizer.LETTER_PRONUNCIATIONS.size());
    }
    
    /**
     * JUnit test of Voice.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer.Voice
     */
    @Test
    public void testVoice() throws Exception {
        Assert.assertEquals(7, SpeechSynthesizer.Voice.values().length);
        Assert.assertEquals(SpeechSynthesizer.Voice.TOM, SpeechSynthesizer.Voice.values()[0]);
        Assert.assertEquals(SpeechSynthesizer.Voice.ROBERT, SpeechSynthesizer.Voice.values()[1]);
        Assert.assertEquals(SpeechSynthesizer.Voice.DAVID, SpeechSynthesizer.Voice.values()[2]);
        Assert.assertEquals(SpeechSynthesizer.Voice.MIKE, SpeechSynthesizer.Voice.values()[3]);
        Assert.assertEquals(SpeechSynthesizer.Voice.POPPY, SpeechSynthesizer.Voice.values()[4]);
        Assert.assertEquals(SpeechSynthesizer.Voice.PRUDENCE, SpeechSynthesizer.Voice.values()[5]);
        Assert.assertEquals(SpeechSynthesizer.Voice.SARAH, SpeechSynthesizer.Voice.values()[6]);
        
        //getCode
        Assert.assertEquals("dfki-spike-hsmm", SpeechSynthesizer.Voice.TOM.getCode());
        Assert.assertEquals("dfki-obadiah-hsmm", SpeechSynthesizer.Voice.ROBERT.getCode());
        Assert.assertEquals("cmu-rms-hsmm", SpeechSynthesizer.Voice.DAVID.getCode());
        Assert.assertEquals("cmu-bdl-hsmm", SpeechSynthesizer.Voice.MIKE.getCode());
        Assert.assertEquals("dfki-poppy-hsmm", SpeechSynthesizer.Voice.POPPY.getCode());
        Assert.assertEquals("dfki-prudence-hsmm", SpeechSynthesizer.Voice.PRUDENCE.getCode());
        Assert.assertEquals("cmu-slt-hsmm", SpeechSynthesizer.Voice.SARAH.getCode());
        
        //isMale
        Assert.assertTrue(SpeechSynthesizer.Voice.TOM.isMale());
        Assert.assertTrue(SpeechSynthesizer.Voice.ROBERT.isMale());
        Assert.assertTrue(SpeechSynthesizer.Voice.DAVID.isMale());
        Assert.assertTrue(SpeechSynthesizer.Voice.MIKE.isMale());
        Assert.assertFalse(SpeechSynthesizer.Voice.POPPY.isMale());
        Assert.assertFalse(SpeechSynthesizer.Voice.PRUDENCE.isMale());
        Assert.assertFalse(SpeechSynthesizer.Voice.SARAH.isMale());
        
        //isFemale
        Assert.assertFalse(SpeechSynthesizer.Voice.TOM.isFemale());
        Assert.assertFalse(SpeechSynthesizer.Voice.ROBERT.isFemale());
        Assert.assertFalse(SpeechSynthesizer.Voice.DAVID.isFemale());
        Assert.assertFalse(SpeechSynthesizer.Voice.MIKE.isFemale());
        Assert.assertTrue(SpeechSynthesizer.Voice.POPPY.isFemale());
        Assert.assertTrue(SpeechSynthesizer.Voice.PRUDENCE.isFemale());
        Assert.assertTrue(SpeechSynthesizer.Voice.SARAH.isFemale());
    }
    
    /**
     * JUnit test of SpeechEffect.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer.SpeechEffect
     */
    @Test
    public void testSpeechEffect() throws Exception {
        Assert.assertEquals(4, SpeechSynthesizer.SpeechEffect.values().length);
        Assert.assertEquals(SpeechSynthesizer.SpeechEffect.ROBOT, SpeechSynthesizer.SpeechEffect.values()[0]);
        Assert.assertEquals(SpeechSynthesizer.SpeechEffect.WHISPER, SpeechSynthesizer.SpeechEffect.values()[1]);
        Assert.assertEquals(SpeechSynthesizer.SpeechEffect.ECHO, SpeechSynthesizer.SpeechEffect.values()[2]);
        Assert.assertEquals(SpeechSynthesizer.SpeechEffect.PILOT, SpeechSynthesizer.SpeechEffect.values()[3]);
        
        //getName
        Assert.assertEquals("Robot", SpeechSynthesizer.SpeechEffect.ROBOT.getName());
        Assert.assertEquals("Whisper", SpeechSynthesizer.SpeechEffect.WHISPER.getName());
        Assert.assertEquals("Stadium", SpeechSynthesizer.SpeechEffect.ECHO.getName());
        Assert.assertEquals("JetPilot", SpeechSynthesizer.SpeechEffect.PILOT.getName());
    }
    
    /**
     * JUnit test of setup.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#setup()
     */
    @Test
    public void testSetup() throws Exception {
        //instantiation
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instanced", new AtomicBoolean(false));
        TestUtils.setFieldValue(SpeechSynthesizer.class, "loggingConfigured", new AtomicBoolean(false));
        Assert.assertFalse(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "instanced").get());
        Assert.assertFalse(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "loggingConfigured").get());
        sut = Mockito.spy(SpeechSynthesizer.getInstance());
        Assert.assertNotNull(TestUtils.getFieldValue(sut, "marytts"));
        Assert.assertNotNull(TestUtils.getFieldValue(sut, "audioPlayer"));
        Assert.assertTrue(TestUtils.getFieldValue(sut, AtomicBoolean.class, "setup").get());
        Assert.assertTrue(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "instanced").get());
        Assert.assertTrue(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "loggingConfigured").get());
        
        //direct call
        sut = Mockito.spy(SpeechSynthesizer.class);
        TestUtils.setFieldValue(sut, "voice", SpeechSynthesizer.DEFAULT_VOICE);
        TestUtils.setFieldValue(sut, "volume", SpeechSynthesizer.DEFAULT_VOLUME);
        TestUtils.setFieldValue(sut, "setup", new AtomicBoolean(false));
        TestUtils.setFieldValue(sut, "marytts", null);
        TestUtils.setFieldValue(sut, "audioPlayer", null);
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instanced", new AtomicBoolean(false));
        TestUtils.setFieldValue(SpeechSynthesizer.class, "loggingConfigured", new AtomicBoolean(false));
        Assert.assertTrue(TestUtils.invokeMethod(sut, boolean.class, "setup"));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .setVoice(SpeechSynthesizer.DEFAULT_VOICE);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .setVolume(SpeechSynthesizer.DEFAULT_VOLUME);
        Assert.assertNotNull(TestUtils.getFieldValue(sut, "marytts"));
        Assert.assertNotNull(TestUtils.getFieldValue(sut, "audioPlayer"));
        Assert.assertTrue(TestUtils.getFieldValue(sut, AtomicBoolean.class, "setup").get());
        Assert.assertFalse(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "instanced").get());
        Assert.assertTrue(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "loggingConfigured").get());
        
        //already setup
        marytts = TestUtils.getFieldValue(sut, MaryInterface.class, "marytts");
        audioPlayer = TestUtils.getFieldValue(sut, AudioPlayer.class, "audioPlayer");
        Assert.assertNotNull(marytts);
        Assert.assertNotNull(audioPlayer);
        Assert.assertTrue(TestUtils.getFieldValue(sut, AtomicBoolean.class, "setup").get());
        Assert.assertFalse(TestUtils.invokeMethod(sut, boolean.class, "setup"));
        Mockito.verify(sut, VerificationModeFactory.times(1)) //still just one
                .setVolume(SpeechSynthesizer.DEFAULT_VOLUME);
        Assert.assertEquals(marytts, TestUtils.getFieldValue(sut, "marytts"));
        Assert.assertEquals(audioPlayer, TestUtils.getFieldValue(sut, "audioPlayer"));
        Assert.assertTrue(TestUtils.getFieldValue(sut, AtomicBoolean.class, "setup").get());
        Assert.assertFalse(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "instanced").get());
        Assert.assertTrue(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "loggingConfigured").get());
    }
    
    /**
     * JUnit test of say.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#say(String)
     */
    @Test
    public void testSay() throws Exception {
        PowerMockito.when(SpeechSynthesizer.class, "quietMode").thenReturn(false);
        TestUtils.setFieldValue(sut, "setup", new AtomicBoolean(true));
        MaryInterface oldMarytts;
        AudioPlayer oldAudioPlayer;
        
        //standard
        oldMarytts = marytts;
        oldAudioPlayer = audioPlayer;
        sut.say("testing");
        Mockito.verify(audioPlayer, VerificationModeFactory.times(1))
                .cancel();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .generateAudio(ArgumentMatchers.eq("testing"));
        marytts = TestUtils.getFieldValue(sut, MaryInterface.class, "marytts");
        audioPlayer = TestUtils.getFieldValue(sut, AudioPlayer.class, "audioPlayer");
        Assert.assertEquals(oldMarytts, marytts);
        Assert.assertNotEquals(oldAudioPlayer, audioPlayer);
        
        //quiet mode
        PowerMockito.when(SpeechSynthesizer.class, "quietMode").thenReturn(true);
        marytts = Mockito.mock(MaryInterface.class);
        audioPlayer = Mockito.mock(AudioPlayer.class);
        TestUtils.setFieldValue(sut, "marytts", marytts);
        TestUtils.setFieldValue(sut, "audioPlayer", audioPlayer);
        oldMarytts = marytts;
        oldAudioPlayer = audioPlayer;
        sut.say("testing");
        Mockito.verify(audioPlayer, VerificationModeFactory.times(1))
                .cancel();
        Mockito.verify(marytts, VerificationModeFactory.noMoreInteractions())
                .generateAudio(ArgumentMatchers.anyString());
        Assert.assertEquals(oldMarytts, marytts);
        Assert.assertEquals(oldAudioPlayer, audioPlayer);
        
        //not setup
        TestUtils.setFieldValue(sut, "setup", new AtomicBoolean(false));
        PowerMockito.when(SpeechSynthesizer.class, "quietMode").thenReturn(false);
        audioPlayer = Mockito.mock(AudioPlayer.class);
        TestUtils.setFieldValue(sut, "audioPlayer", audioPlayer);
        oldMarytts = marytts;
        oldAudioPlayer = audioPlayer;
        sut.say("testing");
        Mockito.verify(audioPlayer, VerificationModeFactory.times(0))
                .cancel();
        Mockito.verify(marytts, VerificationModeFactory.noMoreInteractions())
                .generateAudio(ArgumentMatchers.anyString());
        Assert.assertEquals(oldMarytts, marytts);
        Assert.assertEquals(oldAudioPlayer, audioPlayer);
    }
    
    /**
     * JUnit test of applySpeechEffects.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#applySpeechEffects()
     */
    @Test
    public void testApplySpeechEffects() throws Exception {
        Map<String, String> effects = TestUtils.getFieldValue(sut, Map.class, "effects");
        Assert.assertNotNull(effects);
        String effectString;
        
        //no effects
        Assert.assertTrue(effects.isEmpty());
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("", effectString);
        
        //effect
        effects.put(SpeechSynthesizer.SpeechEffect.ROBOT.getName(), "amount:83.2");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("Robot(amount:83.2)", effectString);
        effects.clear();
        effects.put(SpeechSynthesizer.SpeechEffect.WHISPER.getName(), "amount:100.0");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("Whisper(amount:100.0)", effectString);
        effects.clear();
        effects.put(SpeechSynthesizer.SpeechEffect.ECHO.getName(), "amount:1.1");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("Stadium(amount:1.1)", effectString);
        effects.clear();
        effects.put(SpeechSynthesizer.SpeechEffect.PILOT.getName(), "amount:0.0");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("JetPilot(amount:0.0)", effectString);
        effects.clear();
        effects.put("Volume", "amount:50.0");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("Volume(amount:50.0)", effectString);
        effects.clear();
        
        //multiple effects
        effects.put(SpeechSynthesizer.SpeechEffect.ROBOT.getName(), "amount:83.2");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(2))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("Robot(amount:83.2)", effectString);
        effects.put(SpeechSynthesizer.SpeechEffect.WHISPER.getName(), "amount:100.0");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("Whisper(amount:100.0)+Robot(amount:83.2)", effectString);
        effects.put(SpeechSynthesizer.SpeechEffect.ECHO.getName(), "amount:1.1");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("Stadium(amount:1.1)+Whisper(amount:100.0)+Robot(amount:83.2)", effectString);
        effects.put(SpeechSynthesizer.SpeechEffect.PILOT.getName(), "amount:0.0");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("Stadium(amount:1.1)+Whisper(amount:100.0)+JetPilot(amount:0.0)+Robot(amount:83.2)", effectString);
        effects.put("Volume", "amount:50.0");
        effectString = sut.applySpeechEffects();
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setAudioEffects(effectString);
        Assert.assertNotNull(effectString);
        Assert.assertEquals("Stadium(amount:1.1)+Whisper(amount:100.0)+Volume(amount:50.0)+JetPilot(amount:0.0)+Robot(amount:83.2)", effectString);
        effects.clear();
    }
    
    /**
     * JUnit test of setVolume.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#setVolume(float)
     */
    @Test
    public void testSetVolume() throws Exception {
        float volume = sut.getVolume();
        Assert.assertEquals(SpeechSynthesizer.DEFAULT_VOLUME, volume, TestUtils.DELTA_FLOAT);
        
        //standard
        sut.setVolume(83.2f);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .applySpeechEffects();
        Assert.assertEquals(83.2f, sut.getVolume(), TestUtils.DELTA_FLOAT);
        sut.setVolume(100);
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .applySpeechEffects();
        Assert.assertEquals(100.0f, sut.getVolume(), TestUtils.DELTA_FLOAT);
        sut.setVolume(1.1f);
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .applySpeechEffects();
        Assert.assertEquals(1.1f, sut.getVolume(), TestUtils.DELTA_FLOAT);
        sut.setVolume(0);
        Mockito.verify(sut, VerificationModeFactory.times(4))
                .applySpeechEffects();
        Assert.assertEquals(0.0f, sut.getVolume(), TestUtils.DELTA_FLOAT);
        
        //out of range
        sut.setVolume(100.1f);
        Mockito.verify(sut, VerificationModeFactory.times(5))
                .applySpeechEffects();
        Assert.assertEquals(100.0f, sut.getVolume(), TestUtils.DELTA_FLOAT);
        sut.setVolume(50.456132f);
        Mockito.verify(sut, VerificationModeFactory.times(6))
                .applySpeechEffects();
        Assert.assertEquals(50.5f, sut.getVolume(), TestUtils.DELTA_FLOAT);
        sut.setVolume(-8.1f);
        Mockito.verify(sut, VerificationModeFactory.times(7))
                .applySpeechEffects();
        Assert.assertEquals(0.00f, sut.getVolume(), TestUtils.DELTA_FLOAT);
        sut.setVolume(0.99f);
        Mockito.verify(sut, VerificationModeFactory.times(8))
                .applySpeechEffects();
        Assert.assertEquals(1.0f, sut.getVolume(), TestUtils.DELTA_FLOAT);
    }
    
    /**
     * JUnit test of mute.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#mute()
     */
    @Test
    public void testMute() throws Exception {
        Map<String, String> effects = TestUtils.getFieldValue(sut, Map.class, "effects");
        Assert.assertNotNull(effects);
        float volume = sut.getVolume();
        Assert.assertEquals(SpeechSynthesizer.DEFAULT_VOLUME, volume, TestUtils.DELTA_FLOAT);
        
        //standard
        sut.mute();
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .applySpeechEffects();
        Assert.assertEquals(volume, sut.getVolume(), TestUtils.DELTA_FLOAT);
        Assert.assertTrue(effects.containsKey("Volume"));
        Assert.assertEquals("amount:0.0", effects.get("Volume"));
    }
    
    /**
     * JUnit test of unmute.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#unmute()
     */
    @Test
    public void testUnmute() throws Exception {
        Map<String, String> effects = TestUtils.getFieldValue(sut, Map.class, "effects");
        Assert.assertNotNull(effects);
        float volume = sut.getVolume();
        Assert.assertEquals(SpeechSynthesizer.DEFAULT_VOLUME, volume, TestUtils.DELTA_FLOAT);
        
        //standard
        sut.mute();
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .applySpeechEffects();
        Assert.assertEquals(volume, sut.getVolume(), TestUtils.DELTA_FLOAT);
        sut.unmute();
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .setVolume(volume);
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .applySpeechEffects();
        Assert.assertEquals(volume, sut.getVolume(), TestUtils.DELTA_FLOAT);
        Assert.assertTrue(effects.containsKey("Volume"));
        Assert.assertEquals("amount:0.75", effects.get("Volume"));
    }
    
    /**
     * JUnit test of addSpeechEffect.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#addSpeechEffect(SpeechSynthesizer.SpeechEffect, float)
     */
    @Test
    public void testAddSpeechEffect() throws Exception {
        Map<String, String> effects = TestUtils.getFieldValue(sut, Map.class, "effects");
        Assert.assertNotNull(effects);
        Assert.assertTrue(effects.isEmpty());
        
        //standard
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.ROBOT, 83.2f);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .applySpeechEffects();
        Assert.assertEquals(1, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        Assert.assertEquals("amount:83.2", effects.get(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.WHISPER, 100);
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .applySpeechEffects();
        Assert.assertEquals(2, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        Assert.assertEquals("amount:100.0", effects.get(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.ECHO, 1.1f);
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .applySpeechEffects();
        Assert.assertEquals(3, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        Assert.assertEquals("amount:1.1", effects.get(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.PILOT, 0);
        Mockito.verify(sut, VerificationModeFactory.times(4))
                .applySpeechEffects();
        Assert.assertEquals(4, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        Assert.assertEquals("amount:0.0", effects.get(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        effects.clear();
        
        //out of range
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.ROBOT, 100.1f);
        Mockito.verify(sut, VerificationModeFactory.times(5))
                .applySpeechEffects();
        Assert.assertEquals(1, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        Assert.assertEquals("amount:100.0", effects.get(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.WHISPER, 50.456132f);
        Mockito.verify(sut, VerificationModeFactory.times(6))
                .applySpeechEffects();
        Assert.assertEquals(2, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        Assert.assertEquals("amount:50.5", effects.get(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.ECHO, -8.1f);
        Mockito.verify(sut, VerificationModeFactory.times(7))
                .applySpeechEffects();
        Assert.assertEquals(3, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        Assert.assertEquals("amount:0.0", effects.get(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.PILOT, 0.099f);
        Mockito.verify(sut, VerificationModeFactory.times(8))
                .applySpeechEffects();
        Assert.assertEquals(4, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        Assert.assertEquals("amount:0.1", effects.get(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        effects.clear();
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.addSpeechEffect(null, 50.0f));
    }
    
    /**
     * JUnit test of removeSpeechEffect.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#removeSpeechEffect(SpeechSynthesizer.SpeechEffect)
     */
    @Test
    public void testRemoveSpeechEffect() throws Exception {
        Map<String, String> effects = TestUtils.getFieldValue(sut, Map.class, "effects");
        Assert.assertNotNull(effects);
        Assert.assertTrue(effects.isEmpty());
        
        //standard
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.ROBOT, 83.2f);
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.WHISPER, 100);
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.ECHO, 1.1f);
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.PILOT, 0);
        Mockito.verify(sut, VerificationModeFactory.times(4))
                .applySpeechEffects();
        Assert.assertEquals(4, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        sut.removeSpeechEffect(SpeechSynthesizer.SpeechEffect.ECHO);
        Mockito.verify(sut, VerificationModeFactory.times(5))
                .applySpeechEffects();
        Assert.assertEquals(3, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        sut.removeSpeechEffect(SpeechSynthesizer.SpeechEffect.ROBOT);
        Mockito.verify(sut, VerificationModeFactory.times(6))
                .applySpeechEffects();
        Assert.assertEquals(2, effects.size());
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        sut.removeSpeechEffect(SpeechSynthesizer.SpeechEffect.PILOT);
        Mockito.verify(sut, VerificationModeFactory.times(7))
                .applySpeechEffects();
        Assert.assertEquals(1, effects.size());
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        sut.removeSpeechEffect(SpeechSynthesizer.SpeechEffect.WHISPER);
        Mockito.verify(sut, VerificationModeFactory.times(8))
                .applySpeechEffects();
        Assert.assertEquals(0, effects.size());
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        
        //empty
        Assert.assertTrue(effects.isEmpty());
        sut.removeSpeechEffect(SpeechSynthesizer.SpeechEffect.ROBOT);
        Mockito.verify(sut, VerificationModeFactory.times(9))
                .applySpeechEffects();
        sut.removeSpeechEffect(SpeechSynthesizer.SpeechEffect.WHISPER);
        Mockito.verify(sut, VerificationModeFactory.times(10))
                .applySpeechEffects();
        sut.removeSpeechEffect(SpeechSynthesizer.SpeechEffect.ECHO);
        Mockito.verify(sut, VerificationModeFactory.times(11))
                .applySpeechEffects();
        sut.removeSpeechEffect(SpeechSynthesizer.SpeechEffect.PILOT);
        Mockito.verify(sut, VerificationModeFactory.times(12))
                .applySpeechEffects();
        Assert.assertTrue(effects.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.removeSpeechEffect(null));
    }
    
    /**
     * JUnit test of clearSpeechEffects.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#clearSpeechEffects()
     */
    @Test
    public void testClearSpeechEffects() throws Exception {
        Map<String, String> effects = TestUtils.getFieldValue(sut, Map.class, "effects");
        Assert.assertNotNull(effects);
        Assert.assertTrue(effects.isEmpty());
        
        //standard
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.ROBOT, 83.2f);
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.WHISPER, 100);
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.ECHO, 1.1f);
        sut.addSpeechEffect(SpeechSynthesizer.SpeechEffect.PILOT, 0);
        Mockito.verify(sut, VerificationModeFactory.times(4))
                .applySpeechEffects();
        Assert.assertEquals(4, effects.size());
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        Assert.assertTrue(effects.containsKey(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        sut.clearSpeechEffects();
        Mockito.verify(sut, VerificationModeFactory.times(5))
                .applySpeechEffects();
        Assert.assertEquals(0, effects.size());
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.ROBOT.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.WHISPER.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.ECHO.getName()));
        Assert.assertFalse(effects.containsKey(SpeechSynthesizer.SpeechEffect.PILOT.getName()));
        
        //empty
        Assert.assertTrue(effects.isEmpty());
        sut.clearSpeechEffects();
        Mockito.verify(sut, VerificationModeFactory.times(6))
                .applySpeechEffects();
        Assert.assertTrue(effects.isEmpty());
    }
    
    /**
     * JUnit test of getInstance.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        sut = null;
        PowerMockito.spy(SpeechSynthesizer.class);
        Assert.assertNull(TestUtils.getFieldValue(SpeechSynthesizer.class, "instance"));
        Assert.assertFalse(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "instanced").get());
        Assert.assertFalse(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "loggingConfigured").get());
        
        //standard
        sut = SpeechSynthesizer.getInstance();
        Assert.assertNotNull(sut);
        Assert.assertNotNull(TestUtils.getFieldValue(sut, "marytts"));
        Assert.assertNotNull(TestUtils.getFieldValue(sut, "audioPlayer"));
        Assert.assertNotNull(TestUtils.getFieldValue(SpeechSynthesizer.class, "instance"));
        Assert.assertEquals(sut, TestUtils.getFieldValue(SpeechSynthesizer.class, "instance"));
        Assert.assertTrue(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "instanced").get());
        Assert.assertTrue(TestUtils.getFieldValue(SpeechSynthesizer.class, AtomicBoolean.class, "loggingConfigured").get());
        
        //already instanced
        SpeechSynthesizer oldSut = sut;
        sut = null;
        sut = SpeechSynthesizer.getInstance();
        Assert.assertNotNull(sut);
        Assert.assertEquals(oldSut, sut);
        Assert.assertEquals(sut, TestUtils.getFieldValue(SpeechSynthesizer.class, "instance"));
    }
    
    /**
     * JUnit test of getVoice.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#getVoice()
     */
    @Test
    public void testGetVoice() throws Exception {
        Assert.assertEquals(SpeechSynthesizer.DEFAULT_VOICE, sut.getVoice());
        TestUtils.setFieldValue(sut, "voice", SpeechSynthesizer.Voice.POPPY);
        Assert.assertEquals(SpeechSynthesizer.Voice.POPPY, sut.getVoice());
    }
    
    /**
     * JUnit test of getVolume.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#getVolume()
     */
    @Test
    public void testGetVolume() throws Exception {
        Assert.assertEquals(SpeechSynthesizer.DEFAULT_VOLUME, sut.getVolume(), TestUtils.DELTA_FLOAT);
        TestUtils.setFieldValue(sut, "volume", 18.9f);
        Assert.assertEquals(18.9f, sut.getVolume(), TestUtils.DELTA_FLOAT);
    }
    
    /**
     * JUnit test of isSpeaking.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#isSpeaking()
     */
    @Test
    public void testIsSpeaking() throws Exception {
        Assert.assertFalse(sut.isSpeaking());
        TestUtils.setFieldValue(sut, "speaking", new AtomicBoolean(true));
        Assert.assertTrue(sut.isSpeaking());
    }
    
    /**
     * JUnit test of setVoice.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#setVoice(SpeechSynthesizer.Voice)
     */
    @Test
    public void testSetVoice() throws Exception {
        //standard
        
        Assert.assertTrue(sut.setVoice(SpeechSynthesizer.Voice.DAVID));
        Assert.assertEquals(SpeechSynthesizer.Voice.DAVID, TestUtils.getFieldValue(sut, "voice"));
        Mockito.verify(marytts, VerificationModeFactory.times(1))
                .setVoice(SpeechSynthesizer.Voice.DAVID.getCode());
        
        //invalid
        
        Assert.assertFalse(sut.setVoice(null));
        Assert.assertEquals(SpeechSynthesizer.Voice.DAVID, TestUtils.getFieldValue(sut, "voice"));
        Mockito.verify(marytts, VerificationModeFactory.noMoreInteractions())
                .setVoice(ArgumentMatchers.anyString());
        
        Assert.assertFalse(sut.setVoice(SpeechSynthesizer.Voice.DAVID));
        Assert.assertEquals(SpeechSynthesizer.Voice.DAVID, TestUtils.getFieldValue(sut, "voice"));
        Mockito.verify(marytts, VerificationModeFactory.noMoreInteractions())
                .setVoice(ArgumentMatchers.anyString());
        
        TestUtils.setFieldValue(sut, "marytts", null);
        Assert.assertNull(TestUtils.getFieldValue(sut, "marytts"));
        Assert.assertFalse(sut.setVoice(SpeechSynthesizer.Voice.TOM));
        Assert.assertEquals(SpeechSynthesizer.Voice.DAVID, TestUtils.getFieldValue(sut, "voice"));
        Mockito.verify(marytts, VerificationModeFactory.noMoreInteractions())
                .setVoice(ArgumentMatchers.anyString());
    }
    
    /**
     * JUnit test of speaking.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#speaking()
     */
    @Test
    public void testSpeaking() throws Exception {
        sut = SpeechSynthesizer.getInstance();
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instance", sut);
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instanced", new AtomicBoolean(true));
        Assert.assertEquals(sut, TestUtils.getFieldValue(SpeechSynthesizer.class, "instance"));
        
        //standard
        TestUtils.setFieldValue(sut, "speaking", new AtomicBoolean(false));
        Assert.assertFalse(SpeechSynthesizer.speaking());
        TestUtils.setFieldValue(sut, "speaking", new AtomicBoolean(true));
        Assert.assertTrue(SpeechSynthesizer.speaking());
        
        //invalid
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instance", null);
        Assert.assertFalse(SpeechSynthesizer.speaking());
    }
    
    /**
     * JUnit test of waitUntilSpeaking.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#waitUntilSpeaking()
     */
    @Test
    public void testWaitUntilSpeaking() throws Exception {
        sut = SpeechSynthesizer.getInstance();
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instance", sut);
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instanced", new AtomicBoolean(true));
        Assert.assertEquals(sut, TestUtils.getFieldValue(SpeechSynthesizer.class, "instance"));
        PowerMockito.spy(SpeechSynthesizer.class);
        final AtomicInteger wait = new AtomicInteger(0);
        long startTime;
        long duration;
        
        //standard
        
        wait.set(100);
        PowerMockito.when(SpeechSynthesizer.speaking()).thenAnswer((Answer<Boolean>) invocationOnMock ->
                (wait.decrementAndGet() < 0));
        startTime = System.currentTimeMillis();
        SpeechSynthesizer.waitUntilSpeaking();
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.8), (500 * 1.5)));
        
        //quiet mode
        
        PowerMockito.doReturn(true).when(SpeechSynthesizer.class, "quietMode");
        startTime = System.currentTimeMillis();
        SpeechSynthesizer.waitUntilSpeaking();
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, 0, 25));
        
        PowerMockito.doReturn(false).when(SpeechSynthesizer.class, "quietMode");
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instance", null);
        startTime = System.currentTimeMillis();
        SpeechSynthesizer.waitUntilSpeaking();
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, 0, 25));
    }
    
    /**
     * JUnit test of waitUntilDoneSpeaking.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#waitUntilDoneSpeaking()
     */
    @Test
    public void testWaitUntilDoneSpeaking() throws Exception {
        sut = SpeechSynthesizer.getInstance();
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instance", sut);
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instanced", new AtomicBoolean(true));
        Assert.assertEquals(sut, TestUtils.getFieldValue(SpeechSynthesizer.class, "instance"));
        PowerMockito.spy(SpeechSynthesizer.class);
        final AtomicInteger wait = new AtomicInteger(0);
        long startTime;
        long duration;
        
        //standard
        
        wait.set(100);
        PowerMockito.when(SpeechSynthesizer.speaking()).thenAnswer((Answer<Boolean>) invocationOnMock ->
                (wait.decrementAndGet() >= 0));
        startTime = System.currentTimeMillis();
        SpeechSynthesizer.waitUntilDoneSpeaking();
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, (500 * 0.75), (500 * 1.5)));
        
        //quiet mode
        
        PowerMockito.doReturn(true).when(SpeechSynthesizer.class, "quietMode");
        startTime = System.currentTimeMillis();
        SpeechSynthesizer.waitUntilDoneSpeaking();
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, 0, 25));
        
        PowerMockito.doReturn(false).when(SpeechSynthesizer.class, "quietMode");
        TestUtils.setFieldValue(SpeechSynthesizer.class, "instance", null);
        startTime = System.currentTimeMillis();
        SpeechSynthesizer.waitUntilDoneSpeaking();
        duration = System.currentTimeMillis() - startTime;
        Assert.assertTrue(BoundUtility.inBounds(duration, 0, 25));
    }
    
    /**
     * JUnit test of getLetterPronunciations.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#getLetterPronunciations()
     */
    @Test
    public void testGetLetterPronunciations() throws Exception {
        Map<String, String> letterPronunciations = SpeechSynthesizer.getLetterPronunciations();
        Assert.assertNotNull(letterPronunciations);
        Assert.assertEquals(26, letterPronunciations.size());
        Assert.assertEquals("ay", letterPronunciations.get("a"));
        Assert.assertEquals("bee", letterPronunciations.get("b"));
        Assert.assertEquals("see", letterPronunciations.get("c"));
        Assert.assertEquals("dee", letterPronunciations.get("d"));
        Assert.assertEquals("ee", letterPronunciations.get("e"));
        Assert.assertEquals("ef", letterPronunciations.get("f"));
        Assert.assertEquals("gee", letterPronunciations.get("g"));
        Assert.assertEquals("aych", letterPronunciations.get("h"));
        Assert.assertEquals("eye", letterPronunciations.get("i"));
        Assert.assertEquals("jay", letterPronunciations.get("j"));
        Assert.assertEquals("kay", letterPronunciations.get("k"));
        Assert.assertEquals("el", letterPronunciations.get("l"));
        Assert.assertEquals("em", letterPronunciations.get("m"));
        Assert.assertEquals("en", letterPronunciations.get("n"));
        Assert.assertEquals("oh", letterPronunciations.get("o"));
        Assert.assertEquals("pee", letterPronunciations.get("p"));
        Assert.assertEquals("kyu", letterPronunciations.get("q"));
        Assert.assertEquals("are", letterPronunciations.get("r"));
        Assert.assertEquals("es", letterPronunciations.get("s"));
        Assert.assertEquals("tee", letterPronunciations.get("t"));
        Assert.assertEquals("yew", letterPronunciations.get("u"));
        Assert.assertEquals("vee", letterPronunciations.get("v"));
        Assert.assertEquals("double yew", letterPronunciations.get("w"));
        Assert.assertEquals("eks", letterPronunciations.get("x"));
        Assert.assertEquals("why", letterPronunciations.get("y"));
        Assert.assertEquals("zee", letterPronunciations.get("z"));
    }
    
    /**
     * JUnit test of quietMode.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#quietMode()
     */
    @Test
    public void testQuietMode() throws Exception {
        Assert.assertFalse(SpeechSynthesizer.quietMode());
        Assert.assertFalse(SpeechSynthesizer.quietMode());
    }
    
}
