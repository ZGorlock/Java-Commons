/*
 * File:    FFmpeg.java
 * Package: commons.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.media;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.access.CmdLine;
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
     * @param params The parameters send to ffmpeg.
     * @return The response from the ffmpeg process.
     */
    public static String ffmpeg(String params) {
        String executable = (ffmpeg == null) ? "ffmpeg" : ("\"" + ffmpeg.getAbsolutePath() + "\"");
        return CmdLine.executeCmd(executable + " " + params);
    }
    
    /**
     * Executes an ffmpeg process asynchronously.
     *
     * @param params The parameters to send to ffmpeg.
     * @return Whether the ffmpeg process was successfully created or not.
     */
    public static boolean ffmpegAsync(String params) {
        String executable = (ffmpeg == null) ? "ffmpeg" : ("\"" + ffmpeg.getAbsolutePath() + "\"");
        return CmdLine.executeCmdAsync(executable + " " + params) != null;
    }
    
    /**
     * Executes an ffprobe process.
     *
     * @param params The parameters send to ffprobe.
     * @return The response from the ffprobe process.
     */
    public static String ffprobe(String params) {
        String executable = (ffprobe == null) ? "ffprobe" : ("\"" + ffprobe.getAbsolutePath() + "\"");
        return CmdLine.executeCmd(executable + " " + params);
    }
    
    /**
     * Executes an ffprobe process asynchronously.
     *
     * @param params The parameters to send to ffprobe.
     * @return Whether the ffprobe process was successfully created or not.
     */
    public static boolean ffprobeAsync(String params) {
        String executable = (ffprobe == null) ? "ffprobe" : ("\"" + ffprobe.getAbsolutePath() + "\"");
        return CmdLine.executeCmdAsync(executable + " " + params) != null;
    }
    
    /**
     * Executes an ffplay process.
     *
     * @param params The parameters send to ffplay.
     * @return The response from the ffplay process.
     */
    public static String ffplay(String params) {
        String executable = (ffplay == null) ? "ffplay" : ("\"" + ffplay.getAbsolutePath() + "\"");
        return CmdLine.executeCmd(executable + " " + params);
    }
    
    /**
     * Executes an ffplay process asynchronously.
     *
     * @param params The parameters to send to ffplay.
     * @return Whether the ffplay process was successfully created or not.
     */
    public static boolean ffplayAsync(String params) {
        String executable = (ffplay == null) ? "ffplay" : ("\"" + ffplay.getAbsolutePath() + "\"");
        return CmdLine.executeCmdAsync(executable + " " + params) != null;
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
    
}
