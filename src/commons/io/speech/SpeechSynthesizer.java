/*
 * File:    SpeechSynthesizer.java
 * Package: commons.io.speech
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.speech;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.sound.sampled.LineEvent;

import commons.math.MathUtility;
import commons.math.number.BoundUtility;
import commons.object.collection.MapUtility;
import commons.object.string.StringUtility;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements MaryTTS for speech synthesis.
 */
public class SpeechSynthesizer {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SpeechSynthesizer.class);
    
    //Constants
    
    /**
     * The default voice of the Speech Synthesizer.
     */
    public static final Voice DEFAULT_VOICE = Voice.TOM;
    
    /**
     * The default volume of the Speech Synthesizer.
     */
    public static final float DEFAULT_VOLUME = 75.0f;
    
    /**
     * The default value of the flag to run in quiet mode or not.
     */
    public static final boolean DEFAULT_QUIET_MODE = false;
    
    /**
     * A map of letters to their pronunciations.
     *
     * @see #getLetterPronunciations()
     */
    public static final Map<String, String> LETTER_PRONUNCIATIONS = MapUtility.unmodifiableMap(getLetterPronunciations());
    
    
    //Enums
    
    /**
     * An enumeration of speech Voices.
     */
    public enum Voice {
        
        //Values
        
        TOM("dfki-spike-hsmm", true),
        ROBERT("dfki-obadiah-hsmm", true),
        DAVID("cmu-rms-hsmm", true),
        MIKE("cmu-bdl-hsmm", true),
        POPPY("dfki-poppy-hsmm", false),
        PRUDENCE("dfki-prudence-hsmm", false),
        SARAH("cmu-slt-hsmm", false);
        
        
        //Fields
        
        /**
         * The code of the Voice.
         */
        private final String code;
        
        /**
         * The gender of the Voice.
         */
        private final boolean gender;
        
        
        //Constructors
        
        /**
         * Constructs a Voice.
         *
         * @param code   The code of the Voice.
         * @param gender The gender of the Voice.
         */
        Voice(String code, boolean gender) {
            this.code = code;
            this.gender = gender;
        }
        
        
        //Getters
        
        /**
         * Returns the code of the Voice.
         *
         * @return The code of the Voice.
         */
        public String getCode() {
            return code;
        }
        
        
        //Methods
        
        /**
         * Returns whether the Voice is male or not.
         *
         * @return Whether ths Voice is male of not.
         */
        public boolean isMale() {
            return gender;
        }
        
        /**
         * Returns whether the Voice is female or not.
         *
         * @return Whether ths Voice is female of not.
         */
        public boolean isFemale() {
            return !gender;
        }
        
    }
    
    /**
     * An enumeration of Speech Effects.
     */
    public enum SpeechEffect {
        
        //Values
        
        ROBOT("Robot"),
        WHISPER("Whisper"),
        ECHO("Stadium"),
        PILOT("JetPilot");
        
        
        //Fields
        
        /**
         * The name of the Speech Effect.
         */
        private final String name;
        
        
        //Constructors
        
        /**
         * Constructs a Speech Effect.
         *
         * @param name The name of the Speech Effect.
         */
        SpeechEffect(String name) {
            this.name = name;
        }
        
        
        //Getters
        
        /**
         * Returns the name of the Speech Effect.
         *
         * @return The name of the Speech Effect.
         */
        public String getName() {
            return name;
        }
        
    }
    
    
    //Static Fields
    
    /**
     * The singleton instance of the Speech Synthesizer.
     */
    private static SpeechSynthesizer instance = null;
    
    /**
     * A flag indicating whether an instance has been created or not.
     */
    private static final AtomicBoolean instanced = new AtomicBoolean(false);
    
    /**
     * A flag indicating whether MaryTTS logging has been configured or not.
     */
    private static final AtomicBoolean loggingConfigured = new AtomicBoolean(false);
    
    
    //Fields
    
    /**
     * The interface to interact with MaryTTS.
     */
    private MaryInterface marytts = null;
    
    /**
     * The audio player for MaryTTS.
     */
    private AudioPlayer audioPlayer = null;
    
    /**
     * A flag indicating whether the Speech Synthesizer is currently speaking or not.
     */
    private final AtomicBoolean speaking = new AtomicBoolean(false);
    
    /**
     * The Voice of the Speech Synthesizer.
     */
    private Voice voice = null;
    
    /**
     * The volume of the Speech Synthesizer.
     */
    private float volume = 0.0f;
    
    /**
     * The map of the current Speech Effects.
     */
    private final Map<String, String> effects = new HashMap<>();
    
    /**
     * A flag indicating whether the Speech Synthesizer has been setup already or not.
     */
    private final AtomicBoolean setup = new AtomicBoolean(false);
    
    
    //Constructors
    
    /**
     * The private constructor for the Speech Synthesizer.
     */
    private SpeechSynthesizer() {
        setup();
    }
    
    
    //Methods
    
    /**
     * Sets up the Speech Synthesizer.
     *
     * @return Whether the setup was successful or not.
     */
    private boolean setup() {
        if (setup.compareAndSet(false, true)) {
            configureLogging();
            
            try {
                marytts = new LocalMaryInterface();
                audioPlayer = new AudioPlayer();
                setVoice(DEFAULT_VOICE);
                setVolume(DEFAULT_VOLUME);
                return true;
                
            } catch (MaryConfigurationException ignored) {
                logger.error("Could not configure MaryTTS");
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Produces speech from text.
     *
     * @param output The text to speak.
     * @throws SynthesisException   When the speech cannot be synthesized.
     * @throws InterruptedException When the synthesizer was interrupted.
     * @see LocalMaryInterface#generateAudio(String)
     */
    public void say(String output) throws SynthesisException, InterruptedException {
        if (!setup.get()) {
            return;
        }
        
        audioPlayer.cancel();
        if (quietMode()) {
            return;
        }
        
        audioPlayer = new AudioPlayer(marytts.generateAudio(output), event -> {
            if (event.getType() == LineEvent.Type.START) {
                speaking.set(true);
            } else if (event.getType() == LineEvent.Type.STOP) {
                speaking.set(false);
            }
        });
        audioPlayer.start();
    }
    
    /**
     * Applies the Speech Effects to the Voice.
     *
     * @return The parameter string sent to MaryTTS.
     * @see LocalMaryInterface#setAudioEffects(String)
     */
    public String applySpeechEffects() {
        String effectString = effects.entrySet().stream().map(e -> e.getKey() + '(' + e.getValue() + ')').collect(Collectors.joining("+"));
        logger.trace("Applying speech effects: '{}'", effectString);
        marytts.setAudioEffects(effectString);
        return effectString;
    }
    
    /**
     * Sets the volume of the Voice.
     *
     * @param percent A percent value from 0.0 to 100.0.
     */
    public void setVolume(float percent) {
        volume = (float) MathUtility.roundWithPrecision(BoundUtility.truncate(percent, 0.0f, 100.0f), 1);
        String parameters = String.format("amount:%.2f", (volume / 100.0));
        
        logger.trace("Setting volume to: " + volume + '%');
        effects.put("Volume", parameters);
        applySpeechEffects();
    }
    
    /**
     * Mutes the Voice.
     */
    public void mute() {
        logger.trace("Muting speech");
        effects.put("Volume", "amount:0.0");
        applySpeechEffects();
    }
    
    /**
     * Unmutes the Voice.
     */
    public void unmute() {
        logger.trace("Unmuting speech");
        setVolume(volume);
    }
    
    /**
     * Adds a Speech Effect to the Voice.
     *
     * @param effect  The Speech Effect to add.
     * @param percent An intensity percent value from 0.0 to 100.0.
     */
    public void addSpeechEffect(SpeechEffect effect, float percent) {
        percent = (float) MathUtility.roundWithPrecision(BoundUtility.truncate(percent, 0.0f, 100.0f), 1);
        String parameters = String.format("amount:%.1f", percent);
        
        logger.trace("Adding speech effect: {} with intensity: {}%", effect.name.toLowerCase(), percent);
        effects.put(effect.getName(), parameters);
        applySpeechEffects();
    }
    
    /**
     * Removes a Speech Effect from the Voice.
     *
     * @param effect The Speech Effect to remove.
     */
    public void removeSpeechEffect(SpeechEffect effect) {
        logger.trace("Removing speech effect: {}", effect.name.toLowerCase());
        effects.remove(effect.getName());
        applySpeechEffects();
    }
    
    /**
     * Clears all Speech Effects on the voice.
     */
    public void clearSpeechEffects() {
        logger.trace("Clearing speech effects");
        for (SpeechEffect speechEffect : SpeechEffect.values()) {
            effects.remove(speechEffect.getName());
        }
        applySpeechEffects();
    }
    
    
    //Getters
    
    /**
     * Creates and returns the singleton instance of the Speech Synthesizer.
     *
     * @return The singleton instance of the Speech Synthesizer.
     */
    public static SpeechSynthesizer getInstance() {
        if (instanced.compareAndSet(false, true)) {
            instance = new SpeechSynthesizer();
        }
        return instance;
    }
    
    /**
     * Returns the Voice of the Speech Synthesizer.
     *
     * @return The Voice of the Speech Synthesizer.
     */
    public Voice getVoice() {
        return voice;
    }
    
    /**
     * Returns the volume of the Speech Synthesizer.
     *
     * @return The volume of the Speech Synthesizer.
     */
    public float getVolume() {
        return volume;
    }
    
    /**
     * Returns whether the Speech Synthesizer is speaking or not.
     *
     * @return Whether the Speech Synthesizer is speaking or not.
     */
    public boolean isSpeaking() {
        return speaking.get();
    }
    
    
    //Setters
    
    /**
     * Sets the Voice of the Speech Synthesizer.
     *
     * @param voice The Voice.
     * @return Whether the Voice was successfully set or not.
     * @see LocalMaryInterface#setVoice(String)
     */
    public boolean setVoice(Voice voice) {
        if ((marytts == null) || (voice == null) || (this.voice == voice)) {
            return false;
        }
        
        marytts.setVoice(voice.getCode());
        this.voice = voice;
        return true;
    }
    
    
    //Static Methods
    
    /**
     * Determines if the Speech Synthesizer is currently speaking or not.
     *
     * @return Whether the Speech Synthesizer is currently speaking or not.
     */
    public static boolean speaking() {
        return (instance != null) && instance.isSpeaking();
    }
    
    /**
     * Sleeps the calling thread until the Speech Synthesizer has started speaking.
     */
    @SuppressWarnings("BusyWait")
    public static void waitUntilSpeaking() {
        if ((instance == null) || quietMode()) {
            return;
        }
        while (!speaking()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }
        }
    }
    
    /**
     * Sleeps the calling thread until the Speech Synthesizer has finished speaking.
     */
    @SuppressWarnings("BusyWait")
    public static void waitUntilDoneSpeaking() {
        if ((instance == null) || quietMode()) {
            return;
        }
        while (speaking()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }
        }
    }
    
    /**
     * Configures the logging for the MaryTTS system.
     */
    private static void configureLogging() {
        if (loggingConfigured.compareAndSet(false, true)) {
            Properties props = new Properties();
            try (InputStream configStream = LocalMaryInterface.class.getResourceAsStream("util/log4j.properties")) {
                props.load(configStream);
            } catch (IOException ignored) {
            }
            String workingDirectory = StringUtility.fixFileSeparators(Paths.get(".").toAbsolutePath().normalize().toString()) + '/';
            props.setProperty("log4j.appender.logfile.File", workingDirectory + "log/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/marytts.log");
            LogManager.resetConfiguration();
            PropertyConfigurator.configure(props);
        }
    }
    
    /**
     * Returns a map of letter pronunciations.
     *
     * @return The map of letter pronunciations.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static Map<String, String> getLetterPronunciations() {
        Map<String, String> letterPronunciations = new LinkedHashMap<>();
        letterPronunciations.put("a", "ay");
        letterPronunciations.put("b", "bee");
        letterPronunciations.put("c", "see");
        letterPronunciations.put("d", "dee");
        letterPronunciations.put("e", "ee");
        letterPronunciations.put("f", "ef");
        letterPronunciations.put("g", "gee");
        letterPronunciations.put("h", "aych");
        letterPronunciations.put("i", "eye");
        letterPronunciations.put("j", "jay");
        letterPronunciations.put("k", "kay");
        letterPronunciations.put("l", "el");
        letterPronunciations.put("m", "em");
        letterPronunciations.put("n", "en");
        letterPronunciations.put("o", "oh");
        letterPronunciations.put("p", "pee");
        letterPronunciations.put("q", "kyu");
        letterPronunciations.put("r", "are");
        letterPronunciations.put("s", "es");
        letterPronunciations.put("t", "tee");
        letterPronunciations.put("u", "yew");
        letterPronunciations.put("v", "vee");
        letterPronunciations.put("w", "double yew");
        letterPronunciations.put("x", "eks");
        letterPronunciations.put("y", "why");
        letterPronunciations.put("z", "zee");
        return letterPronunciations;
    }
    
    /**
     * Determines if quiet mode is enabled or not.
     *
     * @return Whether quiet mode is enabled or not.
     */
    public static boolean quietMode() {
        return DEFAULT_QUIET_MODE;
    }
    
}
