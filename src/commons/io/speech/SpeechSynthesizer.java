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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.sound.sampled.LineEvent;

import commons.list.ListUtility;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.signalproc.effects.AudioEffect;
import marytts.signalproc.effects.AudioEffects;
import marytts.util.data.audio.AudioPlayer;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements MaryTTS for default system speech.
 */
public final class SpeechSynthesizer {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SpeechSynthesizer.class);
    
    
    //Constants
    
    /**
     * The default value of the flag to run in quiet mode or not.
     */
    public static final boolean DEFAULT_QUIET_MODE = false;
    
    
    //Static Fields
    
    /**
     * A map of user-friendly voice names to true voice names.
     *
     * @see #getVoiceNames()
     */
    private static final Map<String, String> voiceNameMap = getVoiceNames();
    
    /**
     * A map of letters to their pronunciations.
     *
     * @see #getLetterPronunciations()
     */
    public static final Map<String, String> letterPronunciations = getLetterPronunciations();
    
    /**
     * The singleton instance of the SystemSpeech class.
     */
    private static SpeechSynthesizer instance = null;
    
    /**
     * A flag indicating if an instance has been created or not.
     */
    private static final AtomicBoolean instanced = new AtomicBoolean(false);
    
    
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
     * A flag indicating whether or not the system is currently speaking or not.
     */
    private final AtomicBoolean speaking = new AtomicBoolean(false);
    
    /**
     * The name of the current speech voice.
     */
    private String voiceName = "";
    
    /**
     * A map of the current speech effects.
     */
    private final Map<String, String> effects = new HashMap<>();
    
    /**
     * A flag indicating whether the DLA speech system has been setup already or not.
     */
    private final AtomicBoolean setup = new AtomicBoolean(false);
    
    
    //Constructors
    
    /**
     * The private constructor for SystemSpeech.
     */
    private SpeechSynthesizer() {
    }
    
    
    //Methods
    
    /**
     * Sets up the DLA speech system.
     *
     * @return Whether the setup was successful or not.
     * @see #configureLogging()
     * @see #getVoiceName()
     */
    public boolean setup() {
        if (setup.compareAndSet(false, true)) {
            configureLogging();
            
            try {
                marytts = new LocalMaryInterface();
                audioPlayer = new AudioPlayer();
                voiceName = getVoiceName();
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
     * Produces speech output.
     *
     * @param output The output to speak.
     * @throws SynthesisException   When the speech cannot be synthesized.
     * @throws InterruptedException When the speech was interrupted.
     * @see LocalMaryInterface#generateAudio(String)
     */
    public void say(String output) throws SynthesisException, InterruptedException {
        audioPlayer.cancel();
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
     * Applies the speech effects on the voice.
     *
     * @see LocalMaryInterface#setAudioEffects(String)
     */
    public void applySpeechEffects() {
        String effectString = effects.entrySet().stream().map(e -> e.getKey() + '(' + e.getValue() + ')').collect(Collectors.joining("+"));
        logger.debug("Applying speech effects: '{}'", effectString);
        marytts.setAudioEffects(effectString);
    }
    
    /**
     * Adds a speech effect on the voice.
     *
     * @param effect     The name of the speech effect to add.
     * @param parameters The parameters for the speech effect.
     * @see #applySpeechEffects()
     */
    public void addSpeechEffect(String effect, String parameters) {
        logger.trace("Adding speech effect: {} with parameters: '{}'", effect, parameters);
        effects.put(effect, parameters);
        applySpeechEffects();
    }
    
    /**
     * Removes a speech effect on the voice.
     *
     * @param effect The name of the speech effect to remove.
     * @see #applySpeechEffects()
     */
    public void removeSpeechEffect(String effect) {
        logger.trace("Removing speech effect: {}", effect);
        effects.remove(effect);
        applySpeechEffects();
    }
    
    /**
     * Clears the speech effects on the voice.
     *
     * @see #applySpeechEffects()
     */
    public void clearSpeechEffects() {
        logger.trace("Clearing speech effects");
        effects.clear();
        applySpeechEffects();
    }
    
    /**
     * Prints the available voices to the output window.
     *
     * @see #getAvailableVoices()
     */
    public void printAvailableVoices() {
        System.out.println("Available Voices:");
        for (String voice : getAvailableVoices()) {
            if (voiceName.equals(voice) || voiceName.equals(voiceNameMap.get(voice))) {
                System.out.println(" * " + voice);
            } else {
                System.out.println("   " + voice);
            }
        }
    }
    
    /**
     * Prints the available speech effects to the output window.
     *
     * @see AudioEffects#getEffects()
     */
    public void printAvailableSpeechEffects() {
        System.out.println("Available Speech Effects:");
        System.out.println();
        for (AudioEffect e : AudioEffects.getEffects()) {
            System.out.println(e.getName());
            System.out.println(e.getHelpText());
            System.out.println();
        }
    }
    
    
    //Getters
    
    /**
     * Creates and returns the singleton instance of the SystemSpeech class.
     *
     * @return The singleton instance of the SystemSpeech class or null if an instance has been gotten before.
     */
    public static SpeechSynthesizer getInstance() {
        if (instanced.compareAndSet(false, true)) {
            instance = new SpeechSynthesizer();
            return instance;
        }
        return null;
    }
    
    /**
     * Returns the name of the speech voice.
     *
     * @return The name of the speech voice.
     */
    public String getVoiceName() {
        return voiceName;
    }
    
    
    //Setters
    
    /**
     * Sets the current speech voice.
     *
     * @param voiceName The new speech voice.
     * @param output    Whether to print the voice selections or not.
     * @return Whether the system voice was set to the specified voice or not.
     * @see LocalMaryInterface#setVoice(String)
     */
    public boolean setVoiceName(String voiceName, boolean output) {
        List<String> availableVoices = getAvailableVoices();
        if (ListUtility.anyNull(marytts, voiceName) || voiceName.equals(this.voiceName) || !hasVoice(voiceName)) {
            return false;
        }
        
        if (output) {
            logger.debug("Available Voices:");
            for (String voice : availableVoices) {
                if (voiceName.equals(voice) || voiceName.equals(voiceNameMap.get(voice))) {
                    logger.debug(" * {}", voice);
                } else {
                    logger.debug("   {}", voice);
                }
            }
        }
        
        marytts.setVoice(voiceNameMap.getOrDefault(voiceName, voiceName));
        this.voiceName = voiceName;
        return true;
    }
    
    /**
     * Sets the current speech voice.
     *
     * @param voiceName The new speech voice.
     * @return Whether the system voice was set to the specified voice or not.
     * @see #setVoiceName(String, boolean)
     */
    public boolean setVoiceName(String voiceName) {
        return setVoiceName(voiceName, true);
    }
    
    
    //Functions
    
    /**
     * Returns a list of the names of installed speech voices.
     *
     * @return A list of the names of installed speech voices.
     * @see LocalMaryInterface#getAvailableVoices()
     */
    public static List<String> getAvailableVoices() {
        List<String> availableVoices = new ArrayList<>();
        if ((instance == null) || (instance.marytts == null)) {
            return availableVoices;
        }
        
        Set<String> loadedVoices = instance.marytts.getAvailableVoices();
        for (Map.Entry<String, String> voiceNameEntry : voiceNameMap.entrySet()) {
            if (loadedVoices.contains(voiceNameEntry.getValue())) {
                availableVoices.add(voiceNameEntry.getKey());
            }
        }
        return availableVoices;
    }
    
    /**
     * Determines if a voice name exists on the system.
     *
     * @param voiceName The voice name to check for.
     * @return Whether the specified voice exists on the system or not.
     */
    public static boolean hasVoice(String voiceName) {
        if (ListUtility.anyNull(instance, voiceName) || voiceName.isEmpty()) {
            return false;
        }
        for (String availableVoice : voiceNameMap.keySet()) {
            if (voiceName.equalsIgnoreCase(availableVoice)) {
                return true;
            }
        }
        for (String availableVoice : voiceNameMap.values()) {
            if (voiceName.equalsIgnoreCase(availableVoice)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Determines if the system speech voice is currently speaking.
     *
     * @return Whether the system speech voice is currently speaking or not.
     */
    public static boolean speaking() {
        return (instance != null) && instance.speaking.get();
    }
    
    /**
     * Sleeps the calling thread until the system voice has started speaking.
     *
     * @see #speaking()
     */
    @SuppressWarnings("BusyWait")
    public static void waitUntilSpeaking() {
        if (quietMode()) {
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
     * Sleeps the calling thread until the system voice has finished speaking.
     *
     * @see #speaking()
     */
    @SuppressWarnings("BusyWait")
    public static void waitUntilDoneSpeaking() {
        if (quietMode()) {
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
        Properties props = new Properties();
        try {
            InputStream configStream = LocalMaryInterface.class.getResourceAsStream("util/log4j.properties");
            props.load(configStream);
            configStream.close();
        } catch (IOException ignored) {
        }
        String workingDirectory = Paths.get(".").toAbsolutePath().normalize().toString().replace("\\", "/") + '/';
        props.setProperty("log4j.appender.logfile.File", workingDirectory + "log/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/marytts.log");
        LogManager.resetConfiguration();
        PropertyConfigurator.configure(props);
    }
    
    /**
     * Returns a map of user-friendly voice names.
     *
     * @return The map of user-friendly voice names.
     */
    public static Map<String, String> getVoiceNames() {
        Map<String, String> voiceNameMap = new LinkedHashMap<>();
        voiceNameMap.put("Tom", "dfki-spike-hsmm");
        voiceNameMap.put("Robert", "dfki-obadiah-hsmm");
        voiceNameMap.put("David", "cmu-rms-hsmm");
        voiceNameMap.put("Mike", "cmu-bdl-hsmm");
        voiceNameMap.put("Poppy", "dfki-poppy-hsmm");
        voiceNameMap.put("Prudence", "dfki-prudence-hsmm");
        voiceNameMap.put("Sarah", "cmu-slt-hsmm");
        return voiceNameMap;
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
