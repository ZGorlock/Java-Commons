/*
 * File:    SpeechSynthesizerTest.java
 * Package: commons.io.speech
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.speech;

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
     * @see SpeechSynthesizer#DEFAULT_QUIET_MODE
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertFalse(SpeechSynthesizer.DEFAULT_QUIET_MODE);
    }
    
    /**
     * JUnit test of setup.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#setup()
     */
    @Test
    public void testSetup() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of say.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#say(String)
     */
    @Test
    public void testSay() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of applySpeechEffects.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#applySpeechEffects()
     */
    @Test
    public void testApplySpeechEffects() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of addSpeechEffect.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#addSpeechEffect(String, String)
     */
    @Test
    public void testAddSpeechEffect() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of removeSpeechEffect.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#removeSpeechEffect(String)
     */
    @Test
    public void testRemoveSpeechEffect() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of clearSpeechEffects.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#clearSpeechEffects()
     */
    @Test
    public void testClearSpeechEffects() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of printAvailableVoices.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#printAvailableVoices()
     */
    @Test
    public void testPrintAvailableVoices() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of printAvailableSpeechEffects.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#printAvailableSpeechEffects()
     */
    @Test
    public void testPrintAvailableSpeechEffects() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setVoiceName.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#setVoiceName(String, boolean)
     * @see SpeechSynthesizer#setVoiceName(String)
     */
    @Test
    public void testSetVoiceName() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getAvailableVoices.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#getAvailableVoices()
     */
    @Test
    public void testGetAvailableVoices() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of hasVoice.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#hasVoice(String)
     */
    @Test
    public void testHasVoice() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of speaking.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#speaking()
     */
    @Test
    public void testSpeaking() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of waitUntilSpeaking.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#waitUntilSpeaking()
     */
    @Test
    public void testWaitUntilSpeaking() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of waitUntilDoneSpeaking.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#waitUntilDoneSpeaking()
     */
    @Test
    public void testWaitUntilDoneSpeaking() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getVoiceNames.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#getVoiceNames()
     */
    @Test
    public void testGetVoiceNames() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getLetterPronunciations.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#getLetterPronunciations()
     */
    @Test
    public void testGetLetterPronunciations() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of quietMode.
     *
     * @throws Exception When there is an exception.
     * @see SpeechSynthesizer#quietMode()
     */
    @Test
    public void testQuietMode() throws Exception {
        //TODO
    }
    
}
