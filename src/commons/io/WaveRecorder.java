/*
 * File:    WaveRecorder.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Captures WAV recordings.
 */
public class WaveRecorder extends SingletonInputHandler {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(WaveRecorder.class);
    
    
    //Constants
    
    /**
     * The number of milliseconds to wait before returning the status of a started recording.
     */
    public static final int RECORDING_THREAD_STATUS_DELAY = 100;
    
    /**
     * The default recording sample rate.
     */
    public static final float DEFAULT_SAMPLE_RATE = 44100;
    
    /**
     * The default recording sample size in bits.
     */
    public static final int DEFAULT_SAMPLE_SIZE_IN_BITS = 16;
    
    /**
     * The default recording channel count.
     */
    public static final int DEFAULT_CHANNELS = 2;
    
    /**
     * The default flag for signing the recording or not.
     */
    public static final boolean DEFAULT_SIGNED = true;
    
    /**
     * The default flag for saving the recording in big endian or not.
     */
    public static final boolean DEFAULT_BIG_ENDIAN = true;
    
    
    //Static Fields
    
    /**
     * A flag indicating whether or not a warning has been produced about microphone issues.
     */
    private static boolean recordingWarning = false;
    
    /**
     * The singleton instance of the Input Handler.
     */
    private static SingletonInputHandler instance = new WaveRecorder();
    
    
    //Fields
    
    /**
     * The WAV file to store the recording to.
     */
    private final File wavFile;
    
    /**
     * The format of the WAV recording.
     */
    private AudioFormat format;
    
    /**
     * The line from which audio data is captured.
     */
    private TargetDataLine line = null;
    
    /**
     * The recording thread.
     */
    private Thread recording = null;
    
    
    //Constructors
    
    /**
     * The constructor for a Wave Recorder.
     *
     * @param file The file to produce the recording in.
     */
    public WaveRecorder(File file) {
        wavFile = file;
        interrupt = this::stop;
    }
    
    
    //Methods
    
    /**
     * Opens the target data line and begins capturing the recording.
     *
     * @param format The AudioFormat to use to record the audio.
     * @return Whether the recording was successfully started or not.
     */
    private boolean start(AudioFormat format) {
        this.format = format;
        
        try {
            Info info = new Info(TargetDataLine.class, format);
            
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                if (!recordingWarning) {
                    logger.warn("Your microphone is not supported or your system does not allow audio capture");
                }
                recordingWarning = true;
                return false;
            }
            recordingWarning = false;
            
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            
            recording = new Thread(() -> {
                try {
                    // start capturing
                    AudioInputStream ais = new AudioInputStream(line);
                    
                    // start recording
                    AudioSystem.write(ais, Type.WAVE, wavFile);
                } catch (IOException e) {
                    logger.error("There was an error capturing the recording", e);
                    stop();
                }
            }, "Recording");
            
        } catch (LineUnavailableException e) {
            logger.warn("You do not have a microphone installed", e);
            return false;
        }
        
        recording.start();
        
        try {
            Thread.sleep(RECORDING_THREAD_STATUS_DELAY);
        } catch (InterruptedException ignored) {
        }
        return recording.isAlive();
    }
    
    /**
     * Opens the target data line and begins capturing the recording with the specified format.
     *
     * @param sampleRate       The sample rate in kHz.
     * @param sampleSizeInBits The sample size in bits.
     * @param channels         The number of channels.
     * @param signed           Whether to sign the audio file or not.
     * @param bigEndian        Whether to store the samples in big endian or little endian.
     * @return Whether the recording was successfully started or not.
     * @see #start(AudioFormat)
     */
    public boolean start(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian) {
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        return start(format);
    }
    
    /**
     * Opens the target data line and begins capturing the recording with the default format.
     *
     * @return Whether the recording was successfully started or not.
     * @see #start(float, int, int, boolean, boolean)
     */
    public boolean start() {
        return start(DEFAULT_SAMPLE_RATE, DEFAULT_SAMPLE_SIZE_IN_BITS, DEFAULT_CHANNELS, DEFAULT_SIGNED, DEFAULT_BIG_ENDIAN);
    }
    
    /**
     * Closes the target data line to finish capturing and recording.
     */
    public void stop() {
        if (line != null) {
            line.stop();
            line.close();
            line = null;
        }
        
        try {
            if (recording != null) {
                recording.join();
            }
        } catch (InterruptedException e) {
            logger.error("Unable to shutdown recording thread");
        }
    }
    
    /**
     * Determines the length of the wave recording in milliseconds.
     *
     * @return The length of the wave recording in milliseconds.
     */
    public long getLengthInMilliseconds() {
        if ((recording == null) || (line != null)) {
            logger.debug("The length of the wave file cannot be determined until the recording has been produced.");
            return 0L;
        }
        
        long fileLength = wavFile.length();
        long bytesPerSecond = (long) (format.getSampleRate() * format.getChannels() * ((double) format.getSampleSizeInBits() / 8));
        double timeInSeconds = (double) fileLength / bytesPerSecond;
        return (long) (timeInSeconds * 1000);
    }
    
    
    //Functions
    
    /**
     * Determines if speech capture is enabled on the system or not.
     *
     * @return Whether speech capture is enabled on the system or not.
     */
    public static boolean speechCaptureEnabled() {
        AudioFormat format = new AudioFormat(DEFAULT_SAMPLE_RATE, DEFAULT_SAMPLE_SIZE_IN_BITS, DEFAULT_CHANNELS, DEFAULT_SIGNED, DEFAULT_BIG_ENDIAN);
        Info info = new Info(TargetDataLine.class, format);
        return AudioSystem.isLineSupported(info);
    }
    
    /**
     * Determines if a specified class is the owner of the Input Handler.
     *
     * @param owner The calling class.
     * @return Whether the class is the owner of the Input Handler or not.
     * @see SingletonInputHandler#isOwner(Class)
     */
    public static synchronized boolean owns(Class<?> owner) {
        return instance.isOwner(owner);
    }
    
    /**
     * Determines if a specified class is the owner of the Input Handler.
     *
     * @param owner The calling object.
     * @return Whether the class is the owner of the Input Handler or not.
     * @see SingletonInputHandler#isOwner(Object)
     */
    public static synchronized boolean owns(Object owner) {
        return instance.isOwner(owner);
    }
    
    /**
     * Claims ownership of the Input Handler.
     *
     * @param owner The new owner of the Input Handler.
     * @return Whether the class acquired ownership of the Input Handler or not.
     * @see SingletonInputHandler#claimOwnership(Class)
     */
    public static synchronized boolean own(Class<?> owner) {
        return instance.claimOwnership(owner);
    }
    
    /**
     * Claims ownership of the Input Handler.
     *
     * @param owner The new owner of the Input Handler.
     * @return Whether the class acquired ownership of the Input Handler or not.
     * @see SingletonInputHandler#claimOwnership(Object)
     */
    public static synchronized boolean own(Object owner) {
        return instance.claimOwnership(owner);
    }
    
    /**
     * Claims the default ownership of the Input Handler.
     *
     * @param owner The default owner of the Input Handler.
     * @return Whether default ownership was successfully acquired or not.
     * @see SingletonInputHandler#claimDefaultOwnership(Class)
     */
    public static synchronized boolean defaultOwn(Class<?> owner) {
        return instance.claimDefaultOwnership(owner);
    }
    
    /**
     * Claims the default ownership of the Input Handler.
     *
     * @param owner The default owner of the Input Handler.
     * @return Whether default ownership was successfully acquired or not.
     * @see SingletonInputHandler#claimDefaultOwnership(Object)
     */
    public static synchronized boolean defaultOwn(Object owner) {
        return instance.claimDefaultOwnership(owner);
    }
    
    /**
     * Relinquishes the ownership of the Input Handler to the default owner.
     *
     * @param owner The calling class.
     * @return Whether the class relinquished ownership of the Input Handler or not.
     * @see SingletonInputHandler#relinquishOwnership(Class)
     */
    public static synchronized boolean relinquish(Class<?> owner) {
        return instance.relinquishOwnership(owner);
    }
    
    /**
     * Relinquishes the ownership of the Input Handler to the default owner.
     *
     * @param owner The calling object.
     * @return Whether the class relinquished ownership of the Input Handler or not.
     * @see SingletonInputHandler#relinquishOwnership(Object)
     */
    public static synchronized boolean relinquish(Object owner) {
        return instance.relinquishOwnership(owner);
    }
    
}
