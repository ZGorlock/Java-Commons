/*
 * File:    FFmpeg.java
 * Package: commons.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import commons.access.CmdLine;
import commons.console.Console;
import commons.console.ProgressBar;
import commons.string.StringUtility;

/**
 * Handles ffmpeg access.<br>
 * This will use the ffmpeg executables installed and path accessible on your system, if they exist.<br>
 * You can instead specify the ffmpeg executables to use if you do not have it installed, if it is not path accessible, or if you would like to use a different version that what you have installed.
 */
public class FFmpeg {
    
    //Static Fields
    
    /**
     * The specific ffmpeg executable to use, if null then the path accessible ffmpeg will be used.
     */
    private static File ffmpeg = null;
    
    /**
     * The specific ffprobe executable to use, if null then the path accessible ffprobe will be used.
     */
    private static File ffprobe = null;
    
    /**
     * The specific ffplay executable to use, if null then the path accessible ffplay will be used.
     */
    private static File ffplay = null;
    
    
    //Functions
    
    /**
     * Determines whether ffmpeg exists and is accessible.
     *
     * @return Whether ffmpeg exists and is accessible.
     */
    public static boolean ffmpegExists() {
        return ffmpegVersion() != null;
    }
    
    /**
     * Determines whether ffprobe exists and is accessible.
     *
     * @return Whether ffprobe exists and is accessible.
     */
    public static boolean ffprobeExists() {
        return ffprobeVersion() != null;
    }
    
    /**
     * Determines whether ffplay exists and is accessible.
     *
     * @return Whether ffplay exists and is accessible.
     */
    public static boolean ffplayExists() {
        return ffplayVersion() != null;
    }
    
    /**
     * Determines the ffmpeg version.
     *
     * @return The ffmpeg version.
     */
    public static String ffmpegVersion() {
        String versionResponse = ffmpeg("-version");
        if (versionResponse.isEmpty()) {
            return null;
        }
        
        Pattern ffmpegVersionPattern = Pattern.compile("ffmpeg\\sversion\\s(?<version>[^\\s]+)\\s.*");
        Matcher ffmpegVersionMatcher = ffmpegVersionPattern.matcher(StringUtility.splitLines(versionResponse).get(0));
        if (ffmpegVersionMatcher.matches()) {
            return ffmpegVersionMatcher.group("version");
        }
        return null;
    }
    
    /**
     * Determines the ffprobe version.
     *
     * @return The ffprobe version.
     */
    public static String ffprobeVersion() {
        String versionResponse = ffprobe("-version");
        if (versionResponse.isEmpty()) {
            return null;
        }
        
        Pattern ffprobeVersionPattern = Pattern.compile("ffprobe\\sversion\\s(?<version>[^\\s]+)\\s.*");
        Matcher ffprobeVersionMatcher = ffprobeVersionPattern.matcher(StringUtility.splitLines(versionResponse).get(0));
        if (ffprobeVersionMatcher.matches()) {
            return ffprobeVersionMatcher.group("version");
        }
        return null;
    }
    
    /**
     * Determines the ffplay version.
     *
     * @return The ffplay version.
     */
    public static String ffplayVersion() {
        String versionResponse = ffplay("-version");
        if (versionResponse.isEmpty()) {
            return null;
        }
        
        Pattern ffplayVersionPattern = Pattern.compile("ffplay\\sversion\\s(?<version>[^\\s]+)\\s.*");
        Matcher ffplayVersionMatcher = ffplayVersionPattern.matcher(StringUtility.splitLines(versionResponse).get(0));
        if (ffplayVersionMatcher.matches()) {
            return ffplayVersionMatcher.group("version");
        }
        return null;
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param params      The parameters send to ffmpeg.
     * @param progressBar The FFmpegProgressBar for the process.
     * @return The response from the ffmpeg process.
     */
    public static String ffmpeg(String params, FFmpegProgressBar progressBar) {
        final String executable = (ffmpeg == null) ? "ffmpeg" : ("\"" + ffmpeg.getAbsolutePath() + "\"");
        final String cmd = executable + ' ' + params;
        
        return (progressBar != null) ?
               CmdLine.executeCmd(cmd, progressBar) :
               CmdLine.executeCmd(cmd);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param params The parameters send to ffmpeg.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(String, FFmpegProgressBar)
     */
    public static String ffmpeg(String params) {
        return ffmpeg(params, null);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param inputParams The input parameters send to ffmpeg.
     * @param sourceFiles The source files to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @param progressBar Whether or not to show a progress bar.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(String, FFmpegProgressBar)
     */
    public static String ffmpeg(String inputParams, List<File> sourceFiles, String params, File outputFile, boolean progressBar) {
        final String cmd = "-hide_banner" +
                (progressBar ? " -progress - -nostats" : "") +
                ((inputParams == null || StringUtility.trim(inputParams).isEmpty()) ? "" : ' ') + StringUtility.trim(inputParams) +
                sourceFiles.stream().map(File::getAbsolutePath)
                        .collect(Collectors.joining("\" -i \"", " -i \"", "\"")) +
                ((params == null || StringUtility.trim(params).isEmpty()) ? "" : ' ') + StringUtility.trim(params) +
                " \"" + outputFile.getAbsolutePath() + "\"";
        return ffmpeg(cmd,
                (progressBar && !sourceFiles.isEmpty()) ? new FFmpegProgressBar("FFmpeg " + Console.ConsoleEffect.GREY.apply(cmd), sourceFiles.get(0)) : null);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param sourceFiles The source files to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @param progressBar Whether or not to show a progress bar.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(String, List, String, File, boolean)
     */
    public static String ffmpeg(List<File> sourceFiles, String params, File outputFile, boolean progressBar) {
        return ffmpeg("", sourceFiles, params, outputFile, progressBar);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param inputParams The input parameters send to ffmpeg.
     * @param sourceFiles The source files to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(String, List, String, File, boolean)
     */
    public static String ffmpeg(String inputParams, List<File> sourceFiles, String params, File outputFile) {
        return ffmpeg(inputParams, sourceFiles, params, outputFile, true);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param sourceFiles The source files to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(List, String, File, boolean)
     */
    public static String ffmpeg(List<File> sourceFiles, String params, File outputFile) {
        return ffmpeg(sourceFiles, params, outputFile, true);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param inputParams The input parameters send to ffmpeg.
     * @param sourceFile  The source file to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(String, List, String, File, boolean)
     */
    public static String ffmpeg(String inputParams, File sourceFile, String params, File outputFile, boolean progressBar) {
        return ffmpeg(inputParams, Collections.singletonList(sourceFile), params, outputFile, progressBar);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param sourceFile The source file to send to ffmpeg.
     * @param params     The parameters send to ffmpeg.
     * @param outputFile The output file to send to ffmpeg.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(List, String, File, boolean)
     */
    public static String ffmpeg(File sourceFile, String params, File outputFile, boolean progressBar) {
        return ffmpeg(Collections.singletonList(sourceFile), params, outputFile, progressBar);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param inputParams The input parameters send to ffmpeg.
     * @param sourceFile  The source file to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(File, String, File, boolean)
     */
    public static String ffmpeg(String inputParams, File sourceFile, String params, File outputFile) {
        return ffmpeg(inputParams, sourceFile, params, outputFile, true);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param sourceFile The source file to send to ffmpeg.
     * @param params     The parameters send to ffmpeg.
     * @param outputFile The output file to send to ffmpeg.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(File, String, File, boolean)
     */
    public static String ffmpeg(File sourceFile, String params, File outputFile) {
        return ffmpeg(sourceFile, params, outputFile, true);
    }
    
    /**
     * Executes an ffmpeg process asynchronously.
     *
     * @param params The parameters to send to ffmpeg.
     * @return The ffmpeg Process that was started, or null if there was an error.
     */
    public static Process ffmpegAsync(String params) {
        final String executable = (ffmpeg == null) ? "ffmpeg" : ("\"" + ffmpeg.getAbsolutePath() + "\"");
        final String cmd = executable + ' ' + params;
        
        return CmdLine.executeCmdAsync(cmd);
    }
    
    /**
     * Executes an ffmpeg process asynchronously.
     *
     * @param inputParams The input parameters send to ffmpeg.
     * @param sourceFiles The source files to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @return The ffmpeg Process that was started, or null if there was an error.
     * @see #ffmpegAsync(String)
     */
    public static Process ffmpegAsync(String inputParams, List<File> sourceFiles, String params, File outputFile) {
        return ffmpegAsync("-hide_banner" +
                ((inputParams == null || StringUtility.trim(inputParams).isEmpty()) ? "" : ' ') + StringUtility.trim(inputParams) +
                sourceFiles.stream().map(File::getAbsolutePath)
                        .collect(Collectors.joining("\" -i \"", " -i \"", "\"")) +
                ((params == null || StringUtility.trim(params).isEmpty()) ? "" : ' ') + StringUtility.trim(params) +
                " \"" + outputFile.getAbsolutePath() + "\"");
    }
    
    /**
     * Executes an ffmpeg process asynchronously.
     *
     * @param sourceFiles The source files to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @return The ffmpeg Process that was started, or null if there was an error.
     * @see #ffmpegAsync(String, List, String, File)
     */
    public static Process ffmpegAsync(List<File> sourceFiles, String params, File outputFile) {
        return ffmpegAsync("", sourceFiles, params, outputFile);
    }
    
    /**
     * Executes an ffmpeg process asynchronously.
     *
     * @param inputParams The input parameters send to ffmpeg.
     * @param sourceFile  The source file to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @return The ffmpeg Process that was started, or null if there was an error.
     * @see #ffmpegAsync(String, List, String, File)
     */
    public static Process ffmpegAsync(String inputParams, File sourceFile, String params, File outputFile) {
        return ffmpegAsync(inputParams, Collections.singletonList(sourceFile), params, outputFile);
    }
    
    /**
     * Executes an ffmpeg process asynchronously.
     *
     * @param sourceFile The source file to send to ffmpeg.
     * @param params     The parameters send to ffmpeg.
     * @param outputFile The output file to send to ffmpeg.
     * @return The ffmpeg Process that was started, or null if there was an error.
     * @see #ffmpegAsync(List, String, File)
     */
    public static Process ffmpegAsync(File sourceFile, String params, File outputFile) {
        return ffmpegAsync(Collections.singletonList(sourceFile), params, outputFile);
    }
    
    /**
     * Executes an ffprobe process.
     *
     * @param params The parameters send to ffprobe.
     * @return The response from the ffprobe process.
     */
    public static String ffprobe(String params) {
        final String executable = (ffprobe == null) ? "ffprobe" : ("\"" + ffprobe.getAbsolutePath() + "\"");
        final String cmd = executable + ' ' + params;
        
        return CmdLine.executeCmd(cmd);
    }
    
    /**
     * Executes an ffprobe process.
     *
     * @param params     The parameters send to ffprobe.
     * @param sourceFile The source file to send to ffprobe.
     * @return The response from the ffprobe process.
     * @see #ffprobe(String)
     */
    public static String ffprobe(String params, File sourceFile) {
        return ffprobe("-hide_banner" +
                ((params == null || StringUtility.trim(params).isEmpty()) ? "" : ' ') + StringUtility.trim(params) +
                " \"" + sourceFile.getAbsolutePath() + "\"");
    }
    
    /**
     * Executes an ffprobe process.
     *
     * @param sourceFile The source file to send to ffprobe.
     * @return The response from the ffprobe process.
     * @see #ffprobe(String, File)
     */
    public static String ffprobe(File sourceFile) {
        return ffprobe("", sourceFile);
    }
    
    /**
     * Executes an ffprobe process asynchronously.
     *
     * @param params The parameters to send to ffprobe.
     * @return The ffprobe Process that was started, or null if there was an error.
     */
    public static Process ffprobeAsync(String params) {
        final String executable = (ffprobe == null) ? "ffprobe" : ("\"" + ffprobe.getAbsolutePath() + "\"");
        final String cmd = executable + ' ' + params;
        
        return CmdLine.executeCmdAsync(cmd);
    }
    
    /**
     * Executes an ffprobe process asynchronously.
     *
     * @param params     The parameters send to ffprobe.
     * @param sourceFile The source file to send to ffprobe.
     * @return The ffprobe Process that was started, or null if there was an error.
     * @see #ffprobeAsync(String)
     */
    public static Process ffprobeAsync(String params, File sourceFile) {
        return ffprobeAsync("-hide_banner" +
                ((params == null || StringUtility.trim(params).isEmpty()) ? "" : ' ') + StringUtility.trim(params) +
                " \"" + sourceFile.getAbsolutePath() + "\"");
    }
    
    /**
     * Executes an ffprobe process asynchronously.
     *
     * @param sourceFile The source file to send to ffprobe.
     * @return The ffprobe Process that was started, or null if there was an error.
     * @see #ffprobeAsync(String, File)
     */
    public static Process ffprobeAsync(File sourceFile) {
        return ffprobeAsync("", sourceFile);
    }
    
    /**
     * Executes an ffplay process.
     *
     * @param params The parameters send to ffplay.
     * @return The response from the ffplay process.
     */
    public static String ffplay(String params) {
        final String executable = (ffplay == null) ? "ffplay" : ("\"" + ffplay.getAbsolutePath() + "\"");
        final String cmd = executable + ' ' + params;
        
        return CmdLine.executeCmd(cmd);
    }
    
    /**
     * Executes an ffplay process.
     *
     * @param params     The parameters send to ffplay.
     * @param sourceFile The source file to send to ffplay.
     * @return The response from the ffplay process.
     * @see #ffplay(String)
     */
    public static String ffplay(String params, File sourceFile) {
        return ffplay("-hide_banner" +
                ((params == null || StringUtility.trim(params).isEmpty()) ? "" : ' ') + StringUtility.trim(params) +
                " \"" + sourceFile.getAbsolutePath() + "\"");
    }
    
    /**
     * Executes an ffplay process.
     *
     * @param sourceFile The source file to send to ffplay.
     * @return The response from the ffplay process.
     * @see #ffplay(String, File)
     */
    public static String ffplay(File sourceFile) {
        return ffplay("", sourceFile);
    }
    
    /**
     * Executes an ffplay process asynchronously.
     *
     * @param params The parameters to send to ffplay.
     * @return The ffplay Process that was started, or null if there was an error.
     */
    public static Process ffplayAsync(String params) {
        final String executable = (ffplay == null) ? "ffplay" : ("\"" + ffplay.getAbsolutePath() + "\"");
        final String cmd = executable + ' ' + params;
        
        return CmdLine.executeCmdAsync(cmd);
    }
    
    /**
     * Executes an ffplay process asynchronously.
     *
     * @param params     The parameters send to ffplay.
     * @param sourceFile The source file to send to ffplay.
     * @return The ffplay Process that was started, or null if there was an error.
     * @see #ffplayAsync(String)
     */
    public static Process ffplayAsync(String params, File sourceFile) {
        return ffplayAsync("-hide_banner" +
                ((params == null || StringUtility.trim(params).isEmpty()) ? "" : ' ') + StringUtility.trim(params) +
                " \"" + sourceFile.getAbsolutePath() + "\"");
    }
    
    /**
     * Executes an ffplay process asynchronously.
     *
     * @param sourceFile The source file to send to ffplay.
     * @return The ffplay Process that was started, or null if there was an error.
     * @see #ffplayAsync(String, File)
     */
    public static Process ffplayAsync(File sourceFile) {
        return ffplayAsync("", sourceFile);
    }
    
    
    //Setters
    
    /**
     * Sets the ffmpeg executable to use.
     *
     * @param ffmpeg The ffmpeg executable, null to return to using the path accessible ffmpeg.
     */
    public static void setFFmpegExecutable(File ffmpeg) {
        FFmpeg.ffmpeg = ffmpeg;
    }
    
    /**
     * Sets the ffprobe executable to use.
     *
     * @param ffprobe The ffprobe executable, null to return to using the path accessible ffprobe.
     */
    public static void setFFprobeExecutable(File ffprobe) {
        FFmpeg.ffprobe = ffprobe;
    }
    
    /**
     * Sets the ffplay executable to use.
     *
     * @param ffplay The ffplay executable, null to return to using the path accessible ffplay.
     */
    public static void setFFplayExecutable(File ffplay) {
        FFmpeg.ffplay = ffplay;
    }
    
    
    //Inner Classes
    
    /**
     * A progress bar for ffmpeg muxing operations.
     */
    public static class FFmpegProgressBar extends ProgressBar {
        
        //Constants
        
        /**
         * A regex pattern for an ffmpeg muxing progress log line.
         */
        public static final Pattern MUXING_PROGRESS_LOG_PATTERN = Pattern.compile("^\\s*frame=\\s*(?<frame>\\d+)\\s+fps=\\s*(?<fps>[\\d.]+)\\s+q=\\s*(?<q>[\\-\\d.]+)\\s+(?:Lq=\\s*(?<Lq>[\\-\\d.]+)\\s+)?(?:q=\\s*(?<q2>[\\-\\d.]+)\\s+)?(?:Lsize=\\s*(?<Lsize>[\\d.]+kB)\\s+)?time=\\s*(?<time>[\\d:.]+)\\s+bitrate=\\s*(?<bitrate>[\\d.]+kbits/s)\\s+speed=\\s*(?<speed>[\\d.]+x)\\s*$");
        
        /**
         * A regex pattern for an ffmpeg muxing progress line.
         */
        public static final Pattern MUXING_PROGRESS_PATTERN = Pattern.compile("^\\s*frame=(?<frame>\\d+)\\s*$");
        
        /**
         * A regex pattern for an ffmpeg muxing completion log line.
         */
        public static final Pattern MUXING_COMPLETE_LOG_PATTERN = Pattern.compile("^\\s*video:\\s*(?<video>\\d+kB)\\s+audio:\\s*(?<audio>\\d+kB)\\s+subtitle:\\s*(?<subtitle>\\d+kB)\\s+other\\sstreams:\\s*(?<otherStreams>\\d+kB)\\s+global\\sheaders:\\s*(?<globalHeaders>\\d+kB)\\s+muxing\\soverhead:\\s*(?<muxingOverhead>[\\d.]+%)\\s*$");
        
        /**
         * A regex pattern for an ffmpeg muxing completion line.
         */
        public static final Pattern MUXING_COMPLETE_PATTERN = Pattern.compile("^\\s*progress=end\\s*$");
        
        
        //Fields
        
        /**
         * A flag indicating whether the FFmpeg progress bar completed naturally or not.
         */
        private boolean completedNaturally = false;
        
        /**
         * A list of errors returned by ffmpeg.
         */
        private final List<String> errors = new ArrayList<>();
        
        
        //Constructors
        
        /**
         * Creates a new FFmpegProgressBar object.
         *
         * @param title     The title to display for the progress bar.
         * @param videoFile The video file being operated on.
         */
        public FFmpegProgressBar(String title, File videoFile) {
            super(title, VideoUtility.getFrameCount(videoFile), "frames");
        }
        
        
        //Methods
        
        /**
         * Processes ffmpeg log data and updates the progress bar accordingly.
         *
         * @param log     The ffmpeg log data.
         * @param isError Whether the passed log is an error log or not.
         * @return Whether the progress bar was updated or not.
         * @see #update(long)
         */
        @Override
        public synchronized boolean processLog(String log, boolean isError) {
            if (isError) {
                if (!log.startsWith("  ") && !log.startsWith("[") &&
                        !log.startsWith("Input #") && !log.startsWith("Output #") && !log.startsWith("Stream mapping:")) {
                    errors.add(log);
                    if (log.equals("Press [q] to stop, [?] for help")) {
                        errors.clear();
                    }
                }
                
            } else {
                final Matcher muxingProgressMatcher = MUXING_PROGRESS_PATTERN.matcher(log);
                if (muxingProgressMatcher.matches()) {
                    return update(Long.parseLong(muxingProgressMatcher.group("frame")));
                    
                } else {
                    final Matcher muxingCompleteMatcher = MUXING_COMPLETE_PATTERN.matcher(log);
                    if (muxingCompleteMatcher.matches()) {
                        completedNaturally = true;
                        return true;
                    }
                }
            }
            
            return false;
        }
        
        /**
         * Completes the progress bar.
         *
         * @see #complete(boolean)
         * @see #fail(boolean, String)
         */
        @Override
        public synchronized void complete() {
            if (completedNaturally) {
                complete(true);
            } else {
                fail(true, Console.ConsoleEffect.RED.apply(
                        "See method response for more information" + (errors.isEmpty() ? "" : System.lineSeparator()) +
                                StringUtility.trim(errors.stream().collect(Collectors.joining(System.lineSeparator())))));
            }
        }
        
    }
    
}
