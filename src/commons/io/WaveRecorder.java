/*
 * File:    WaveRecorder.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
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
    public static final int DEFAULT_SAMPLE_RATE = 44100;
    
    /**
     * The default recording sample size in bits.
     */
    public static final int DEFAULT_SAMPLE_SIZE_IN_BITS = 16;
    
    /**
     * The default recording channel count.
     */
    public static final int DEFAULT_CHANNELS = 2;
    
    /**
     * The default flag for signing recordings or not.
     */
    public static final boolean DEFAULT_SIGNED = true;
    
    /**
     * The default flag for saving recordings in big endian or not.
     */
    public static final boolean DEFAULT_BIG_ENDIAN = true;
    
    
    //Static Fields
    
    /**
     * The WAV file to store the recording in.
     */
    private static File output = null;
    
    /**
     * The format of the WAV recording.
     */
    private static AudioFormat format = null;
    
    /**
     * The line from which audio data is captured.
     */
    private static TargetDataLine line = null;
    
    /**
     * The thread that handles recording.
     */
    private static ExecutorService recorder = null;
    
    /**
     * The handle for the thread that handles recording.
     */
    private static Future<?> recorderHandle = null;
    
    /**
     * A flag indicating whether or not a warning has been produced about microphone issues yet.
     */
    private static AtomicBoolean recorderWarning = new AtomicBoolean(false);
    
    /**
     * The singleton instance of the Input Handler.
     */
    private static SingletonInputHandler instance = new WaveRecorder();
    
    /**
     * A flag indicating whether the Input Handler is currently in use.
     */
    private static AtomicBoolean inUse = new AtomicBoolean(false);
    
    
    //Constructors
    
    /**
     * The private constructor for the Wave Recorder.
     */
    private WaveRecorder() {
        interrupt = WaveRecorder::stop;
    }
    
    
    //Static Methods
    
    /**
     * Opens the target data line and begins capturing the recording.
     *
     * @param output The file to save the recording in.
     * @param format The AudioFormat to use to record the audio.
     * @return Whether the recording was successfully started or not.
     */
    private static boolean start(File output, AudioFormat format) {
        WaveRecorder.output = output;
        WaveRecorder.format = format;
        
        try {
            final Info info = new Info(TargetDataLine.class, format);
            
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                if (recorderWarning.compareAndSet(false, true)) {
                    logger.warn("Your microphone is not supported or your system does not allow audio capture");
                }
                inUse.set(false);
                return false;
            }
            recorderWarning.set(false);
            
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            
        } catch (LineUnavailableException e) {
            logger.warn("You do not have a microphone installed", e);
            inUse.set(false);
            return false;
        }
        
        recorder = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("WavRecorder").build());
        recorderHandle = recorder.submit(() -> {
            try {
                AudioSystem.write(new AudioInputStream(line), Type.WAVE, output);
            } catch (Exception e) {
                logger.error("There was an error capturing the recording", e);
                stop();
            } finally {
                inUse.set(false);
            }
        });
        
        try {
            Thread.sleep(RECORDING_THREAD_STATUS_DELAY);
        } catch (InterruptedException ignored) {
        }
        return isRecorderRunning();
    }
    
    /**
     * Opens the target data line and begins capturing the recording.
     *
     * @param output The file to save the recording in.
     * @param format The AudioFormat to use to record the audio.
     * @param caller The caller.
     * @return Whether the recording was successfully started or not.
     * @see #start(File, AudioFormat)
     */
    public static boolean start(File output, AudioFormat format, Object caller) {
        if (!owns(caller) || (output == null) || (format == null) || !inUse.compareAndSet(false, true)) {
            return false;
        }
        
        stop();
        return start(output, format);
    }
    
    /**
     * Opens the target data line and begins capturing the recording with the specified format.
     *
     * @param output           The file to save the recording in.
     * @param sampleRate       The sample rate in kHz.
     * @param sampleSizeInBits The sample size in bits.
     * @param channels         The number of channels.
     * @param signed           Whether to sign the audio file or not.
     * @param bigEndian        Whether to store the samples in big endian or little endian.
     * @param caller           The caller.
     * @return Whether the recording was successfully started or not.
     * @see #start(File, AudioFormat, Object)
     */
    public static boolean start(File output, int sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian, Object caller) {
        final AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        return start(output, format, caller);
    }
    
    /**
     * Opens the target data line and begins capturing the recording with the default format.
     *
     * @param output The file to save the recording in.
     * @param caller The caller.
     * @return Whether the recording was successfully started or not.
     * @see #start(File, int, int, int, boolean, boolean, Object)
     */
    public static boolean start(File output, Object caller) {
        return start(output, DEFAULT_SAMPLE_RATE, DEFAULT_SAMPLE_SIZE_IN_BITS, DEFAULT_CHANNELS, DEFAULT_SIGNED, DEFAULT_BIG_ENDIAN, caller);
    }
    
    /**
     * Closes the target data line to finish capturing the recording.
     *
     * @return Whether the recording was successfully stopped or not.
     */
    private static boolean stop() {
        if (line != null) {
            line.stop();
            line.close();
            line = null;
        }
        
        if (isRecorderRunning()) {
            try {
                recorderHandle.cancel(true);
                recorder.shutdown();
                recorder.shutdownNow();
                if (!recorder.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                    throw new InterruptedException();
                }
                recorder = null;
                recorderHandle = null;
            } catch (InterruptedException ignored) {
                logger.error("Unable to shutdown WaveRecorder thread");
                return false;
            }
        }
        return true;
    }
    
    /**
     * Closes the target data line to finish capturing the recording.
     *
     * @param caller The caller.
     * @return Whether the recording was successfully stopped or not.
     * @see #stop()
     */
    public static boolean stop(Object caller) {
        if (!owns(caller)) {
            return false;
        }
        
        return stop();
    }
    
    /**
     * Determines if the recorder thread is running or not.
     *
     * @return Whether the recorder thread is running or not.
     */
    private static boolean isRecorderRunning() {
        return !((recorder == null) || (recorderHandle == null) ||
                recorder.isShutdown() || recorder.isTerminated() ||
                recorderHandle.isDone() || recorderHandle.isCancelled());
    }
    
    /**
     * Determines the length of the recording in milliseconds.
     *
     * @param caller The caller.
     * @return The length of the recording in milliseconds.
     */
    public static long getLengthInMilliseconds(Object caller) {
        if (!owns(caller)) {
            return 0L;
        }
        
        if ((output == null) || (format == null) || !output.exists() || (recorder != null) || (recorderHandle != null) || (line != null)) {
            logger.debug("The length of the wav file cannot be determined until a recording has been produced");
            return 0L;
        }
        
        return (long) ((output.length() * 8.0) / (format.getSampleRate() * format.getChannels() * format.getSampleSizeInBits()) * 1000);
    }
    
    /**
     * Determines if recording is enabled on the system or not.
     *
     * @return Whether recording is enabled on the system or not.
     */
    public static boolean recordingEnabled() {
        final AudioFormat format = new AudioFormat(DEFAULT_SAMPLE_RATE, DEFAULT_SAMPLE_SIZE_IN_BITS, DEFAULT_CHANNELS, DEFAULT_SIGNED, DEFAULT_BIG_ENDIAN);
        final Info info = new Info(TargetDataLine.class, format);
        return AudioSystem.isLineSupported(info);
    }
    
    /**
     * Determines if a specified caller is the owner of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller is the owner of the Input Handler or not.
     * @see SingletonInputHandler#isOwner(Object)
     */
    public static boolean owns(Object caller) {
        return instance.isOwner(caller);
    }
    
    /**
     * Acquires ownership of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller acquired ownership of the Input Handler or not.
     * @see SingletonInputHandler#acquireOwnership(Object)
     */
    public static boolean own(Object caller) {
        return instance.acquireOwnership(caller);
    }
    
    /**
     * Relinquishes ownership of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller relinquished ownership of the Input Handler or not.
     * @see SingletonInputHandler#releaseOwnership(Object)
     */
    public static boolean relinquish(Object caller) {
        return instance.releaseOwnership(caller);
    }
    
    /**
     * Acquires management of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller acquired management of the Input Handler or not.
     * @see SingletonInputHandler#acquireManagement(Object)
     */
    public static boolean manage(Object caller) {
        return instance.acquireManagement(caller);
    }
    
    /**
     * Relinquishes management of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller relinquished management of the Input Handler or not.
     * @see SingletonInputHandler#releaseManagement(Object)
     */
    public static boolean relinquishManagement(Object caller) {
        return instance.releaseManagement(caller);
    }
    
}
