/*
 * File:    SpeechRecognizer.java
 * Package: commons.io.speech
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.speech;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.access.CmdLine;
import commons.access.Filesystem;
import commons.access.OperatingSystem;
import commons.access.Project;
import commons.console.Console;
import commons.io.HotKeyManager;
import commons.io.SystemIn;
import commons.io.WaveRecorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements PocketSphinx for speech recognition.
 */
public class SpeechRecognizer {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SpeechRecognizer.class);
    
    
    //Enums
    
    /**
     * An enumeration of speech Recognition Modes.
     */
    public enum RecognitionMode {
        
        //Values
        
        CONTINUOUS,
        TRIGGERED,
        ON_DEMAND,
        OFF
        
    }
    
    
    //Constants
    
    /**
     * The resource directory for the Speech Recognizer.
     */
    public static final File RESOURCE_DIRECTORY = Project.resourcesDir(SpeechRecognizer.class);
    
    /**
     * The Sphinx directory.
     */
    public static final File SPHINX_DIRECTORY = new File(RESOURCE_DIRECTORY, "pocketsphinx");
    
    /**
     * The Sphinx language to use.
     */
    public static final String SPHINX_LANGUAGE = "en-us";
    
    /**
     * The name of the PocketSphinx binary.
     */
    public static final String BINARY_NAME = "pocketsphinx_continuous";
    
    /**
     * The Sphinx dictionary to use.
     */
    public static final String SPHINX_DICTIONARY = "cmudict-en-us.dict";
    
    /**
     * The Sphinx language model to use.
     */
    public static final String SPHINX_LANGUAGE_MODEL = "en-us.lm.bin";
    
    /**
     * The Sphinx phonetic language model to use.
     */
    public static final String SPHINX_PHONETIC_LANGUAGE_MODEL = "en-us-phone.lm.bin";
    
    /**
     * The Sphinx transcription file to use for acoustic model adaption.
     */
    public static final File SPHINX_TRANSCRIPTION_FILE = new File(RESOURCE_DIRECTORY, "arctic20.transcription");
    
    /**
     * The Sphinx transcription fileids file to use for acoustic model adaption.
     */
    public static final File SPHINX_TRANSCRIPTION_FILEIDS_FILE = new File(RESOURCE_DIRECTORY, "arctic20.fileids");
    
    /**
     * The default mode to run Sphinx in.
     */
    public static final RecognitionMode DEFAULT_SPHINX_MODE = RecognitionMode.ON_DEMAND;
    
    /**
     * The recording sample rate.
     */
    public static final int RECORDING_SAMPLE_RATE = 16000;
    
    /**
     * The recording sample size in bits.
     */
    public static final int RECORDING_SAMPLE_SIZE_IN_BITS = 16;
    
    /**
     * The recording channel count.
     */
    public static final int RECORDING_CHANNELS = 1;
    
    /**
     * The flag for signing the recording or not.
     */
    public static final boolean RECORDING_SIGNED = true;
    
    /**
     * The flag for saving the recording in big endian or not.
     */
    public static final boolean RECORDING_BIG_ENDIAN = false;
    
    /**
     * The default minimum length in milliseconds for a speech recordings to be processed in triggered mode.
     */
    public static final long DEFAULT_MINIMUM_RECORDING_LENGTH = 750L;
    
    
    //Static Fields
    
    /**
     * The singleton instance of the Speech Recognizer.
     */
    private static SpeechRecognizer instance = null;
    
    /**
     * A flag indicating whether an instance has been created or not.
     */
    private static final AtomicBoolean instanced = new AtomicBoolean(false);
    
    
    //Fields
    
    /**
     * The PocketSphinx process if running in continuous mode.
     */
    private Process pocketsphinx = null;
    
    /**
     * The input stream of PocketSphinx output if running in continuous mode.
     */
    private BufferedReader speechStream = null;
    
    /**
     * The buffer holding PocketSphinx output if running in triggered or on-demand mode.
     */
    protected AtomicReference<String> speechBuffer = null;
    
    /**
     * A flag indicting whether the Speech Recognizer is active or not.
     */
    private AtomicBoolean active = new AtomicBoolean(false);
    
    /**
     * The mode that the Speech Recognizer is running in.
     */
    private RecognitionMode mode = null;
    
    /**
     * The adaption matrix to use for acoustic model adaption.
     */
    private File adaptionMatrix = null;
    
    /**
     * The HotKey trigger for capturing speech in triggered mode.<br>
     * By default, speech is captured by pressing and holding Ctrl.
     */
    private HotKeyManager.HotKey captureSpeechTrigger = null;
    
    /**
     * The callback for handling capture speech in triggered mode.
     */
    private HotKeyManager.HotKeyCallback captureSpeechTriggerCallback = null;
    
    /**
     * A flag indicating whether the Speech Recognizer is recording or not.
     */
    private AtomicBoolean recording = new AtomicBoolean(false);
    
    /**
     * The minimum length for speech recordings to be processed in triggered mode.
     */
    private long minimumRecordingLength = DEFAULT_MINIMUM_RECORDING_LENGTH;
    
    /**
     * The command to run PocketSphinx continuous mode.
     */
    private String runPocketSphinxCmd = "";
    
    /**
     * The command to kill PocketSphinx continuous mode.
     */
    private String killPocketSphinxCmd = "";
    
    /**
     * The command to decode a recording with PocketSphinx.
     */
    private String decodeRecordingCmd = "";
    
    /**
     * A flag indicating whether the Speech Recognizer has been setup already or not.
     */
    private final AtomicBoolean setup = new AtomicBoolean(false);
    
    
    //Constructors
    
    /**
     * The private constructor for the Speech Recognizer.
     */
    private SpeechRecognizer() {
        setup();
    }
    
    
    //Methods
    
    /**
     * Sets up the Speech Recognizer.
     *
     * @return Whether the setup was successful or not.
     */
    private boolean setup() {
        if (setup.compareAndSet(false, true)) {
            initializeCommands();
            
            captureSpeechTriggerCallback = new SpeechRecorder();
            captureSpeechTrigger = new HotKeyManager.HotKey(HotKeyManager.HotKey.NO_KEY, true, false, false, false, captureSpeechTriggerCallback);
            
            Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
            return true;
            
        } else {
            return false;
        }
    }
    
    /**
     * Initializes the PocketSphinx commands.
     */
    @SuppressWarnings("SpellCheckingInspection")
    private void initializeCommands() {
        boolean isWindows = OperatingSystem.isWindows();
        boolean hasAcousticModelAdaption = ((adaptionMatrix != null) && adaptionMatrix.exists());
        
        String hmm = Filesystem.generatePath(true, "model", SPHINX_LANGUAGE);
        
        String cmdHeader = "cd \"" + SPHINX_DIRECTORY.getAbsolutePath() + "\" && " +
                (isWindows ? "" : "LD_LIBRARY_PATH=$(pwd) ") +
                BINARY_NAME + (isWindows ? ".exe" : "") + ' ';
        
        String cmdFooter = " -hmm " + hmm + SPHINX_LANGUAGE +
                " -lm " + hmm + SPHINX_LANGUAGE_MODEL +
                " -dict " + hmm + SPHINX_DICTIONARY +
                (hasAcousticModelAdaption ? (" -mllr \"" + adaptionMatrix.getAbsolutePath() + '"') : "") +
                (isWindows ? " -logfn nul" : " &> /dev/null");
        
        runPocketSphinxCmd = cmdHeader + "-inmic yes" + cmdFooter;
        decodeRecordingCmd = cmdHeader + "-infile \"%s\"" + cmdFooter;
        killPocketSphinxCmd = isWindows ? ("Taskkill /IM " + BINARY_NAME + ".exe" + " /F") : ("pkill -f " + BINARY_NAME);
    }
    
    /**
     * Starts PocketSphinx or switches the mode that it is running in.
     *
     * @return Whether the PocketSphinx mode was successfully set or not.
     */
    private boolean initialize(RecognitionMode newMode) {
        if (newMode == null) {
            return false;
        }
        if (mode == null) {
            mode = RecognitionMode.OFF;
        }
        
        switch (mode) {
            case CONTINUOUS:
                CmdLine.executeCmd(killPocketSphinxCmd);
                if ((pocketsphinx != null) && pocketsphinx.isAlive()) {
                    pocketsphinx.destroyForcibly();
                }
                pocketsphinx = null;
                logger.debug("Continuous speech recognition terminated");
                if (speechStream != null) {
                    try {
                        speechStream.close();
                        speechStream = null;
                    } catch (IOException ignored) {
                    }
                }
                break;
            case TRIGGERED:
                if (HotKeyManager.hasHotkey(captureSpeechTrigger)) {
                    HotKeyManager.unregisterHotkey(captureSpeechTrigger);
                }
                if (isRecording()) {
                    captureSpeechTrigger.release();
                }
                speechBuffer = null;
                break;
            case ON_DEMAND:
                if (isRecording()) {
                    captureSpeechTrigger.release();
                }
                speechBuffer = null;
                break;
            case OFF:
                break;
        }
        clearSpeech();
        
        boolean success = true;
        switch (newMode) {
            case CONTINUOUS:
                pocketsphinx = CmdLine.executeCmdAsync(runPocketSphinxCmd);
                if (pocketsphinx != null) {
                    logger.debug("Continuous speech recognition started");
                    speechStream = new BufferedReader(new InputStreamReader(pocketsphinx.getInputStream()));
                    success = true;
                } else {
                    success = false;
                }
                break;
            case TRIGGERED:
                HotKeyManager.registerHotkey(captureSpeechTrigger);
                speechBuffer = new AtomicReference<>("");
                break;
            case ON_DEMAND:
                speechBuffer = new AtomicReference<>("");
                break;
            case OFF:
                break;
        }
        
        mode = newMode;
        return success;
    }
    
    /**
     * Starts Speech Recognition.
     *
     * @return Whether Speech Recognition was successfully started or not.
     */
    public boolean start() {
        if (!isActive() && initialize((mode != null) ? mode : DEFAULT_SPHINX_MODE)) {
            active.set(true);
            return true;
        }
        return false;
    }
    
    /**
     * Stops Speech Recognition.
     *
     * @return Whether Speech Recognition was successfully stopped or not.
     */
    public boolean stop() {
        if (isActive() && initialize(RecognitionMode.OFF)) {
            active.set(false);
            return true;
        }
        return false;
    }
    
    /**
     * Returns speech that was recognized.
     *
     * @param wait Whether or not to wait for speech; If enabled this will block the calling thread, and null will not be returned.
     * @return Speech that was recognized, or null if there is none.
     */
    @SuppressWarnings("BusyWait")
    public String getSpeech(boolean wait) {
        switch (mode) {
            case CONTINUOUS:
                try {
                    while ((speechStream != null) && (speechStream.ready() || wait)) {
                        String speech = speechStream.readLine();
                        if (!speech.isEmpty()) {
                            if (speech.matches("Allocating \\d* buffers of \\d* samples each")) {
                                continue;
                            }
                            return speech;
                        }
                        
                        if (!wait) {
                            return null;
                        }
                    }
                } catch (IOException ignored) {
                }
                break;
            
            case TRIGGERED:
            case ON_DEMAND:
                while (speechBuffer != null) {
                    String input = speechBuffer.getAndSet("");
                    if (!input.isEmpty()) {
                        return input;
                    }
                    
                    if (!wait) {
                        break;
                    } else {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
                break;
        }
        
        return null;
    }
    
    /**
     * Returns speech that was recognized.
     *
     * @return Speech that was recognized, or null if there is none.
     * @see #getSpeech(boolean)
     */
    public String getSpeech() {
        return getSpeech(false);
    }
    
    /**
     * Clears any speech that has been recognized but not retrieved yet.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void clearSpeech() {
        if (speechStream != null) {
            try {
                while (speechStream.ready()) {
                    speechStream.read();
                }
            } catch (IOException ignored) {
            }
        }
        
        if (speechBuffer != null) {
            speechBuffer.set("");
        }
    }
    
    /**
     * Starts the speech recording for on-demand mode.
     *
     * @return Whether the speech recording has been started or not.
     */
    public boolean startRecording() {
        if ((mode == RecognitionMode.ON_DEMAND) && !isRecording()) {
            captureSpeechTriggerCallback.hit();
            return true;
        }
        return false;
    }
    
    /**
     * Finalizes the speech recording for on-demand mode.
     *
     * @return Whether the speech recording has been finalized or not.
     */
    public boolean finalizeRecording() {
        if ((mode == RecognitionMode.ON_DEMAND) && isRecording()) {
            captureSpeechTriggerCallback.release();
            return true;
        }
        return false;
    }
    
    
    //Getters
    
    /**
     * Creates and returns the singleton instance of the Speech Recognizer.
     *
     * @return The singleton instance of the Speech Recognizer.
     */
    public static SpeechRecognizer getInstance() {
        if (instanced.compareAndSet(false, true)) {
            instance = new SpeechRecognizer();
        }
        return instance;
    }
    
    /**
     * Returns whether the Speech Recognizer is active or not.
     *
     * @return Whether the Speech Recognizer is active or not.
     */
    public boolean isActive() {
        return active.get();
    }
    
    /**
     * Returns the mode that the Speech Recognizer is running in.
     *
     * @return The mode that the Speech Recognizer is running in.
     */
    public RecognitionMode getMode() {
        return mode;
    }
    
    /**
     * Returns whether the Speech Recognizer is recording or not.
     *
     * @return Whether the Speech Recognizer is recording or not.
     */
    public boolean isRecording() {
        return recording.get();
    }
    
    
    //Setters
    
    /**
     * Switches the mode that the Speech Recognizer is running in.
     *
     * @param mode The mode.
     */
    public void setMode(RecognitionMode mode) {
        if (isActive()) {
            initialize(mode);
        } else {
            this.mode = mode;
        }
    }
    
    /**
     * Sets the minimum length for speech recordings to be processed in triggered mode.
     *
     * @param minimumRecordingLength The minimum length for speech recordings to be processed.
     */
    public void setMinimumRecordingLength(long minimumRecordingLength) {
        this.minimumRecordingLength = Math.max(minimumRecordingLength, 0);
    }
    
    /**
     * Sets the HotKey trigger to use for speech recording in triggered mode.
     *
     * @param keyCode The virtual key code of the trigger.
     * @param control Whether the trigger includes the Ctrl key.
     * @param shift   Whether the trigger includes the Shift key.
     * @param alt     Whether the trigger includes the Alt key.
     * @param meta    Whether the trigger includes the Meta key.
     */
    public void setCaptureSpeechTrigger(int keyCode, boolean control, boolean shift, boolean alt, boolean meta) {
        if ((captureSpeechTrigger != null) && HotKeyManager.hasHotkey(captureSpeechTrigger)) {
            HotKeyManager.unregisterHotkey(captureSpeechTrigger);
        }
        
        captureSpeechTrigger = new HotKeyManager.HotKey(keyCode, control, shift, alt, meta, captureSpeechTriggerCallback);
        if (mode == RecognitionMode.TRIGGERED) {
            HotKeyManager.registerHotkey(captureSpeechTrigger);
        }
    }
    
    /**
     * Sets the adaption matrix for the acoustic model.
     *
     * @param adaptionMatrix The adaption matrix file.
     */
    public void setAcousticModelAdaptionMatrix(File adaptionMatrix) {
        this.adaptionMatrix = adaptionMatrix;
        initializeCommands();
    }
    
    
    //Inner Classes
    
    /**
     * Handles recording speech for PocketSphinx in triggered and on-demand mode.
     */
    private class SpeechRecorder implements HotKeyManager.HotKeyCallback {
        
        //Fields
        
        /**
         * A flag indicating whether a recording is in progress or not.
         */
        private AtomicBoolean recording = SpeechRecognizer.this.recording;
        
        /**
         * The file that the recording will be written to.
         */
        private File wavFile = null;
        
        
        //Methods
        
        /**
         * Starts the speech recording.
         */
        @Override
        public void hit() {
            if (recording.compareAndSet(false, true)) {
                if (!WaveRecorder.owns(SpeechRecognizer.SpeechRecorder.class) && !WaveRecorder.own(SpeechRecognizer.SpeechRecorder.class)) {
                    logger.warn("The Speech Recognizer could not acquire ownership of the wave recorder");
                    recording.set(false);
                    return;
                }
                
                wavFile = Filesystem.createTemporaryFile(".wav");
                if (!WaveRecorder.start(wavFile, RECORDING_SAMPLE_RATE, RECORDING_SAMPLE_SIZE_IN_BITS, RECORDING_CHANNELS, RECORDING_SIGNED, RECORDING_BIG_ENDIAN, SpeechRecognizer.SpeechRecorder.class)) {
                    logger.warn("The Speech Recognizer could not start the wave recorder");
                    recording.set(false);
                    return;
                }
                logger.debug(((mode == RecognitionMode.TRIGGERED) ? "Triggered" : "On-demand") + " speech recognition started");
            }
        }
        
        /**
         * Finalizes the speech recording.
         */
        @Override
        public void release() {
            if (recording.compareAndSet(true, false)) {
                if (!WaveRecorder.owns(SpeechRecognizer.SpeechRecorder.class)) {
                    logger.warn("The Speech Recognizer does not have ownership of the wave recorder");
                    return;
                }
                
                if (!WaveRecorder.stop(SpeechRecognizer.SpeechRecorder.class)) {
                    logger.warn("The Speech Recognizer could not stop the wave recorder");
                    WaveRecorder.relinquish(SpeechRecognizer.SpeechRecorder.class);
                    return;
                }
                logger.debug(((mode == RecognitionMode.TRIGGERED) ? "Triggered" : "On-demand") + " speech recognition finished");
                
                long recordingLength = WaveRecorder.getLengthInMilliseconds(SpeechRecognizer.SpeechRecorder.class);
                WaveRecorder.relinquish(SpeechRecognizer.SpeechRecorder.class);
                
                if ((mode == RecognitionMode.ON_DEMAND) || (recordingLength >= minimumRecordingLength)) {
                    String decodeCmd = String.format(decodeRecordingCmd, wavFile.getAbsolutePath());
                    String speech = CmdLine.executeCmd(decodeCmd);
                    if (speechBuffer != null) {
                        speechBuffer.set(speech.trim());
                    }
                }
                Filesystem.deleteFile(wavFile);
            }
        }
        
    }
    
    /**
     * Handles the training of the PocketSphinx acoustic model.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static class SpeechTrainer {
        
        //Static Fields
        
        /**
         * A flag indicating whether the running operating system is Windows or not.
         */
        private static final boolean isWindows = OperatingSystem.isWindows();
        
        /**
         * The singleton instance of the Speech Trainer.
         */
        private static SpeechTrainer instance = null;
        
        
        //Constructors
        
        /**
         * The private constructor for the Speech Trainer.
         */
        private SpeechTrainer() {
        }
        
        
        //Methods
        
        /**
         * Performs acoustic model training.
         *
         * @param adaptionMatrixOutput The file to save the acoustic model adaption matrix in.
         * @return Whether the acoustic model training was successful or not.
         */
        public boolean train(File adaptionMatrixOutput) {
            if (!SPHINX_DIRECTORY.exists() || !SPHINX_TRANSCRIPTION_FILE.exists() || !SPHINX_TRANSCRIPTION_FILEIDS_FILE.exists()) {
                logger.warn("Unable to train speech recognition without training resources");
                return false;
            }
            
            File trainingDirectory = Filesystem.createTemporaryDirectory();
            if (!prepareTrainingDirectory(trainingDirectory)) {
                Filesystem.deleteDirectory(trainingDirectory);
                return false;
            }
            
            System.out.println(Console.color("You will now train the Speech Recognizer to better recognize your voice:", Console.ConsoleEffect.YELLOW));
            System.out.println(Console.color("You will be asked to read several sentences", Console.ConsoleEffect.YELLOW));
            System.out.println(Console.color("Speak into the microphone as you normally would and make sure there is no background noise or music playing", Console.ConsoleEffect.YELLOW));
            
            if (!collectRecordings(trainingDirectory) ||
                    !generateAcousticFeatureFiles(trainingDirectory) ||
                    !accumulateObservationCounts(trainingDirectory) ||
                    !createMllrTransformation(trainingDirectory)) {
                Filesystem.deleteDirectory(trainingDirectory);
                return false;
            }
            
            File mllrMatrixFile = new File(trainingDirectory, "mllr_matrix");
            boolean trainingSuccess = Filesystem.copyFile(mllrMatrixFile, adaptionMatrixOutput, true);
            
            Filesystem.deleteDirectory(trainingDirectory);
            return trainingSuccess;
        }
        
        /**
         * Prepares the training directory for acoustic model training.
         *
         * @param trainingDirectory The training directory.
         * @return Whether the training directory was successfully prepared or not.
         */
        public boolean prepareTrainingDirectory(File trainingDirectory) {
            if ((trainingDirectory.exists() && !Filesystem.deleteDirectory(trainingDirectory)) || !Filesystem.createDirectory(trainingDirectory)) {
                logger.warn("Unable to create training directory");
                return false;
            }
            
            File defaultModelDir = new File(SPHINX_DIRECTORY, "model");
            File languageModelDir = new File(defaultModelDir, SPHINX_LANGUAGE);
            File defaultLanguageModelDir = new File(languageModelDir, SPHINX_LANGUAGE);
            if (!Filesystem.copyDirectory(defaultLanguageModelDir, trainingDirectory, true, true)) {
                logger.warn("Unable to copy default model to training directory");
                return false;
            }
            
            File sphinxBase = new File(SPHINX_DIRECTORY, "sphinxbase.dll");
            File libSphinxBase = new File(SPHINX_DIRECTORY, "libsphinxbase.so.3");
            File libSphinxAd = new File(SPHINX_DIRECTORY, "libsphinxad.so.3");
            File sphinxFe = new File(SPHINX_DIRECTORY, "sphinx_fe" + (isWindows ? ".exe" : ""));
            File sphinxBw = new File(SPHINX_DIRECTORY, "bw" + (isWindows ? ".exe" : ""));
            File sphinxMllr = new File(SPHINX_DIRECTORY, "mllr_solve" + (isWindows ? ".exe" : ""));
            File sphinxDict = new File(languageModelDir, SPHINX_DICTIONARY);
            File sphinxLmBin = new File(languageModelDir, SPHINX_LANGUAGE_MODEL);
            File sphinxPhoneLmBin = new File(languageModelDir, SPHINX_PHONETIC_LANGUAGE_MODEL);
            
            boolean copyTrainingResources;
            if (isWindows) {
                copyTrainingResources = Filesystem.copyFile(sphinxBase, trainingDirectory);
            } else {
                copyTrainingResources = Filesystem.copyFile(libSphinxBase, trainingDirectory) &&
                        Filesystem.copyFile(libSphinxAd, trainingDirectory);
            }
            copyTrainingResources &= Filesystem.copyFile(sphinxFe, trainingDirectory) &&
                    Filesystem.copyFile(sphinxBw, trainingDirectory) &&
                    Filesystem.copyFile(sphinxMllr, trainingDirectory) &&
                    Filesystem.copyFile(sphinxDict, trainingDirectory) &&
                    Filesystem.copyFile(sphinxLmBin, trainingDirectory) &&
                    Filesystem.copyFile(sphinxPhoneLmBin, trainingDirectory) &&
                    Filesystem.copyFile(SPHINX_TRANSCRIPTION_FILEIDS_FILE, trainingDirectory) &&
                    Filesystem.copyFile(SPHINX_TRANSCRIPTION_FILE, trainingDirectory);
            
            if (!copyTrainingResources) {
                logger.warn("Unable to copy training resources to training directory");
                return false;
            }
            return true;
        }
        
        /**
         * Collects recordings from the user of standard transcriptions.
         *
         * @param trainingDirectory The training directory.
         * @return Whether the user recordings were successfully collected or not.
         */
        @SuppressWarnings("StatementWithEmptyBody")
        public boolean collectRecordings(File trainingDirectory) {
            if (!SystemIn.owns(SpeechRecognizer.SpeechTrainer.class) && !SystemIn.own(SpeechRecognizer.SpeechTrainer.class)) {
                logger.error("The Speech Trainer could not acquire ownership of the system input");
                return false;
            }
            if (!WaveRecorder.owns(SpeechRecognizer.SpeechTrainer.class) && !WaveRecorder.own(SpeechRecognizer.SpeechTrainer.class)) {
                logger.warn("The Speech Trainer could not acquire ownership of the wave recorder");
                SystemIn.relinquish(SpeechRecognizer.SpeechTrainer.class);
                return false;
            }
            
            List<String> transcriptions = Filesystem.readLines(SPHINX_TRANSCRIPTION_FILE);
            if (transcriptions.isEmpty()) {
                logger.warn("Unable to train speech recognition with an empty transcription file");
                SystemIn.relinquish(SpeechRecognizer.SpeechTrainer.class);
                WaveRecorder.relinquish(SpeechRecognizer.SpeechTrainer.class);
                return false;
            }
            
            Pattern transcriptionPattern = Pattern.compile("<s>\\s(?<transcription>.*)\\s</s>\\s\\((?<fileId>.*)\\)");
            
            for (String transcription : transcriptions) {
                Matcher transcriptionMatcher = transcriptionPattern.matcher(transcription);
                if (!transcriptionMatcher.matches()) {
                    continue;
                }
                
                String phrase = transcriptionMatcher.group("transcription");
                String fileId = transcriptionMatcher.group("fileId");
                File recording = new File(trainingDirectory, fileId + ".wav");
                
                System.out.println(Console.color("Preview the sentence and press Enter to begin speaking, press Enter again to stop the recording", Console.ConsoleEffect.YELLOW));
                System.out.print(phrase);
                
                while (SystemIn.nextLine(SpeechRecognizer.SpeechTrainer.class) == null) {
                }
                WaveRecorder.start(recording, RECORDING_SAMPLE_RATE, RECORDING_SAMPLE_SIZE_IN_BITS, RECORDING_CHANNELS, RECORDING_SIGNED, RECORDING_BIG_ENDIAN, SpeechRecognizer.SpeechTrainer.class);
                System.out.print(Console.color("Recording...", Console.ConsoleEffect.GREEN));
                
                while (SystemIn.nextLine(SpeechRecognizer.SpeechTrainer.class) == null) {
                }
                WaveRecorder.stop(SpeechRecognizer.SpeechTrainer.class);
                System.out.println(Console.color("Recording complete", Console.ConsoleEffect.GREEN));
            }
            
            SystemIn.relinquish(SpeechRecognizer.SpeechTrainer.class);
            WaveRecorder.relinquish(SpeechRecognizer.SpeechTrainer.class);
            return true;
        }
        
        /**
         * Generates the acoustic feature files from the user recordings.
         *
         * @param trainingDirectory The training directory.
         * @return Whether the acoustic feature files were successfully generated or not.
         */
        public boolean generateAcousticFeatureFiles(File trainingDirectory) {
            File sphinxFe = new File(SPHINX_DIRECTORY, "sphinx_fe" + (isWindows ? ".exe" : ""));
            String featParams = Filesystem.generatePath(SPHINX_LANGUAGE, "feat.params");
            
            String genFeatureFilesCmd = "cd \"" + trainingDirectory.getAbsolutePath() + "\" && " + (isWindows ? "" : "LD_LIBRARY_PATH=$(pwd) ") +
                    sphinxFe.getName() +
                    " -argfile " + featParams +
                    " -samprate " + RECORDING_SAMPLE_RATE +
                    " -c " + SPHINX_TRANSCRIPTION_FILEIDS_FILE.getName() +
                    " -di ." +
                    " -do ." +
                    " -ei wav" +
                    " -eo mfc" +
                    " -mswav yes";
            
            String genFeatureFilesResponse = CmdLine.executeCmd(genFeatureFilesCmd);
            if (genFeatureFilesResponse.contains("ERROR")) {
                logger.warn("There was an error generating the acoustic feature files");
                return false;
            }
            return true;
        }
        
        /**
         * Accumulates observation counts from the acoustic feature files.
         *
         * @param trainingDirectory The training directory.
         * @return Whether the observation counts were successfully accumulated or not.
         */
        public boolean accumulateObservationCounts(File trainingDirectory) {
            File sphinxBw = new File(SPHINX_DIRECTORY, "bw" + (isWindows ? ".exe" : ""));
            String mdef = Filesystem.generatePath(SPHINX_LANGUAGE, "mdef");
            //String featureTransform = Filesystem.generatePath(SPHINX_LANGUAGE, "feature_transform");
            
            String accumulateObservationCountCmd = "cd \"" + trainingDirectory.getAbsolutePath() + "\" && " + (isWindows ? "" : "LD_LIBRARY_PATH=$(pwd) ") +
                    sphinxBw.getName() +
                    " -hmmdir " + SPHINX_LANGUAGE +
                    " -moddeffn " + mdef +
                    " -ts2cbfn .ptm." +
                    " -feat 1s_c_d_dd" +
                    " -svspec 0-12/13-25/26-38" +
                    " -cmn current" +
                    " -agc none" +
                    " -dictfn " + SPHINX_DICTIONARY +
                    " -ctlfn " + SPHINX_TRANSCRIPTION_FILEIDS_FILE.getName() +
                    " -lsnfn " + SPHINX_TRANSCRIPTION_FILE.getName() +
                    //" -lda " + featureTransform +
                    " -accumdir .";
            
            String accumulateObservationCountResponse = CmdLine.executeCmd(accumulateObservationCountCmd);
            if (accumulateObservationCountResponse.contains("ERROR")) {
                logger.warn("There was an error accumulating observation counts");
                return false;
            }
            return true;
        }
        
        /**
         * Creates the mllr transformation for the acoustic model.
         *
         * @param trainingDirectory The training directory.
         * @return Whether the mllr transformation were successfully created or not.
         */
        public boolean createMllrTransformation(File trainingDirectory) {
            File sphinxMllr = new File(SPHINX_DIRECTORY, "mllr_solve" + (isWindows ? ".exe" : ""));
            File mllrMatrixFile = new File(trainingDirectory, "mllr_matrix");
            String means = Filesystem.generatePath(SPHINX_LANGUAGE, "means");
            String variances = Filesystem.generatePath(SPHINX_LANGUAGE, "variances");
            
            String transformationMllrCmd = "cd \"" + trainingDirectory.getAbsolutePath() + "\" && " + (isWindows ? "" : "LD_LIBRARY_PATH=$(pwd) ") +
                    sphinxMllr.getName() +
                    " -meanfn " + means +
                    " -varfn " + variances +
                    " -outmllrfn " + mllrMatrixFile.getName() +
                    " -accumdir .";
            
            String transformationMllrResponse = CmdLine.executeCmd(transformationMllrCmd);
            if (transformationMllrCmd.contains("ERROR")) {
                logger.warn("There was an error creating the acoustic adaption matrix");
                return false;
            }
            return true;
        }
        
        
        //Functions
        
        /**
         * Performs training of the PocketSphinx acoustic model.
         *
         * @param adaptionMatrixOutput The file to save the acoustic model adaption matrix in.
         * @return Whether the acoustic model training was successful or not.
         */
        public static boolean performTraining(File adaptionMatrixOutput) {
            if (instance == null) {
                instance = new SpeechTrainer();
            }
            return instance.train(adaptionMatrixOutput);
        }
        
    }
    
}
