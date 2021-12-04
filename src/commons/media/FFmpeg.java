/*
 * File:    FFmpeg.java
 * Package: commons.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import commons.access.CmdLine;
import commons.console.Console;
import commons.console.ProgressBar;
import commons.log.CommonsLogging;
import commons.math.BoundUtility;
import commons.math.EquationUtility;
import commons.string.StringUtility;
import commons.time.DateTimeUtility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles ffmpeg access.<br>
 * This will use the ffmpeg executables installed and path accessible on your system, if they exist.<br>
 * You can instead specify the ffmpeg executables to use if you do not have it installed, if it is not path accessible, or if you would like to use a different version that what you have installed.
 */
public class FFmpeg {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FFmpeg.class);
    
    
    //Enums
    
    /**
     * An enumeration of stream types.
     */
    public enum StreamType {
        
        //Values
        
        VIDEO,
        AUDIO,
        SUBTITLE,
        DATA
        
    }
    
    
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
    
    /**
     * The list of Codecs supported by ffmpeg.
     */
    private static List<Codec> codecs = null;
    
    /**
     * The list of Encoders supported by ffmpeg.
     */
    private static List<Encoder> encoders = null;
    
    /**
     * The list of Decoders supported by ffmpeg.
     */
    private static List<Decoder> decoders = null;
    
    
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
                ((inputParams == null || StringUtility.isWhitespace(inputParams)) ? "" : ' ') + StringUtility.trim(inputParams) +
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
        return ffmpeg(inputParams, sourceFiles, params, outputFile, CommonsLogging.showFfmpegProgressBarsByDefault());
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
        return ffmpeg(sourceFiles, params, outputFile, CommonsLogging.showFfmpegProgressBarsByDefault());
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
        return ffmpeg(inputParams, sourceFile, params, outputFile, CommonsLogging.showFfmpegProgressBarsByDefault());
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
        return ffmpeg(sourceFile, params, outputFile, CommonsLogging.showFfmpegProgressBarsByDefault());
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
                ((inputParams == null || StringUtility.isWhitespace(inputParams)) ? "" : ' ') + StringUtility.trim(inputParams) +
                sourceFiles.stream().map(File::getAbsolutePath)
                        .collect(Collectors.joining("\" -i \"", " -i \"", "\"")) +
                ((params == null || StringUtility.isWhitespace(params)) ? "" : ' ') + StringUtility.trim(params) +
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
                ((params == null || StringUtility.isWhitespace(params)) ? "" : ' ') + StringUtility.trim(params) +
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
                ((params == null || StringUtility.isWhitespace(params)) ? "" : ' ') + StringUtility.trim(params) +
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
                ((params == null || StringUtility.isWhitespace(params)) ? "" : ' ') + StringUtility.trim(params) +
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
                ((params == null || StringUtility.isWhitespace(params)) ? "" : ' ') + StringUtility.trim(params) +
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
    
    /**
     * Returns the metadata of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The metadata of the media file, or null if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static FFmpeg.Metadata getMetadata(File mediaFile) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_format -show_streams -show_chapters -byte_binary_prefix", mediaFile);
        if (!StringUtility.removeWhiteSpace(ffprobeResponse).equals("{}")) {
            try {
                return new FFmpeg.Metadata((JSONObject) new JSONParser().parse(ffprobeResponse));
            } catch (Exception ignored) {
            }
        }
        return null;
    }
    
    /**
     * Returns the format metadata of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The format of the media file, or null if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static FFmpeg.FormatMetadata getFormatMetadata(File mediaFile) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_format -byte_binary_prefix", mediaFile);
        try {
            return new FFmpeg.FormatMetadata((JSONObject) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("format"));
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns the streams metadata of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The streams metadata of the media file, or null if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static List<FFmpeg.StreamMetadata> getStreams(File mediaFile) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_streams -byte_binary_prefix", mediaFile);
        try {
            return Arrays.stream(((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("streams")).toArray())
                    .map(e -> new FFmpeg.StreamMetadata((JSONObject) e)).collect(Collectors.toList());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns a stream metadata from a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @return The specified stream metadata from the media file, or null if the media file does not exist or cannot be read, or if the specified stream does not exist.
     * @see #ffprobe(String, File)
     */
    public static FFmpeg.StreamMetadata getStream(File mediaFile, FFmpeg.StreamType streamType, int index) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -select_streams " + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + index +
                " -print_format json -show_streams -byte_binary_prefix", mediaFile);
        try {
            final JSONArray streams = (JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("streams");
            return !streams.isEmpty() ? new FFmpeg.StreamMetadata((JSONObject) streams.get(0)) : null;
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns a stream metadata from a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @return The specified stream metadata from the media file, or null if the media file does not exist or cannot be read, or if the specified stream does not exist.
     * @see #getStream(File, FFmpeg.StreamType, int)
     */
    public static FFmpeg.StreamMetadata getStream(File mediaFile, FFmpeg.StreamType streamType) {
        return getStream(mediaFile, streamType, 0);
    }
    
    /**
     * Returns a stream metadata from a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param index     The index of the stream.
     * @return The specified stream metadata from the media file, or null if the media file does not exist or cannot be read, or the specified stream does not exist.
     * @see #getStream(File, FFmpeg.StreamType, int)
     */
    public static FFmpeg.StreamMetadata getStream(File mediaFile, int index) {
        return getStream(mediaFile, null, index);
    }
    
    /**
     * Returns the number of streams in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The number of streams in the media file, or -1 if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static int getStreamCount(File mediaFile) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -show_entries format=nb_streams -of csv=p=0:e=none", mediaFile);
        try {
            return Integer.parseInt(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return -1;
    }
    
    /**
     * Returns the number of streams of a particular stream type in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of the stream.
     * @return The number of streams of a specified stream type in the media file, or -1 if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static int getStreamCount(File mediaFile, FFmpeg.StreamType streamType) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_streams", mediaFile);
        try {
            return (int) Arrays.stream(((JSONArray) ((JSONObject) new JSONParser()
                            .parse(ffprobeResponse)).get("streams")).toArray())
                    .filter(e -> streamType.name().equalsIgnoreCase((String) ((JSONObject) e).get("codec_type"))).count();
        } catch (Exception ignored) {
        }
        return -1;
    }
    
    /**
     * Returns the chapters metadata of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The chapters metadata of the media file, or null if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static List<FFmpeg.ChapterMetadata> getChapters(File mediaFile) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_chapters -byte_binary_prefix", mediaFile);
        try {
            return Arrays.stream(((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters")).toArray())
                    .map(e -> new FFmpeg.ChapterMetadata((JSONObject) e)).collect(Collectors.toList());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns a chapter metadata from a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param index     The index of the chapter.
     * @return The specified chapter metadata from the media file, or null if the media file does not exist or cannot be read, or if the specified chapter does not exist.
     * @see #ffprobe(String, File)
     */
    public static FFmpeg.ChapterMetadata getChapter(File mediaFile, int index) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_chapters -byte_binary_prefix", mediaFile);
        try {
            final JSONArray chapters = (JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters");
            if (BoundUtility.inBounds(index, 0, chapters.size(), true, false)) {
                return new FFmpeg.ChapterMetadata((JSONObject) chapters.get(index));
            }
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns the number of chapters in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The number of chapters in the media file, or -1 if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static int getChapterCount(File mediaFile) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_chapters -byte_binary_prefix", mediaFile);
        try {
            return ((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters")).toArray().length;
        } catch (Exception ignored) {
        }
        return -1;
    }
    
    /**
     * Returns the format of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile   The media file.
     * @param abbreviated Whether or not to abbreviate the format name.
     * @return The format of the media file, or null if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static String getFormat(File mediaFile, boolean abbreviated) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -show_entries format=format" + (abbreviated ? "" : "_long") + "_name -of csv=p=0:e=none", mediaFile);
        return !StringUtility.isWhitespace(ffprobeResponse) ? StringUtility.trim(ffprobeResponse) : null;
    }
    
    /**
     * Returns the format of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The format of the media file, or null if the media file does not exist or cannot be read.
     * @see #getFormat(File, boolean)
     */
    public static String getFormat(File mediaFile) {
        return getFormat(mediaFile, false);
    }
    
    /**
     * Returns the duration of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The duration of the media file in milliseconds, or -1 if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static long getDuration(File mediaFile) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -show_entries format=duration -of csv=p=0:e=none", mediaFile);
        try {
            return (long) (Double.parseDouble(StringUtility.trim(ffprobeResponse)) * 1000);
        } catch (Exception ignored) {
        }
        return -1L;
    }
    
    /**
     * Returns the bitrate of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The bitrate of the media file in bits per second, or -1 if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static long getBitrate(File mediaFile) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -show_entries format=bit_rate -of csv=p=0:e=none", mediaFile);
        try {
            return Long.parseLong(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return -1;
    }
    
    /**
     * Returns the duration of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @return The duration of the specified stream in the media file in milliseconds, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist or does not specify a duration.
     * @see #ffprobe(String, File)
     */
    public static long getStreamDuration(File mediaFile, FFmpeg.StreamType streamType, int index) {
        final StreamMetadata stream = getStream(mediaFile, streamType, index);
        return (stream != null) ? stream.getDuration() : -1L;
    }
    
    /**
     * Returns the duration of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @return The duration of the specified stream in the media file in milliseconds, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist or does not specify a duration.
     * @see #getStreamDuration(File, StreamType, int)
     */
    public static long getStreamDuration(File mediaFile, FFmpeg.StreamType streamType) {
        return getStreamDuration(mediaFile, streamType, 0);
    }
    
    /**
     * Returns the duration of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param index     The index of the stream.
     * @return The duration of the specified stream in the media file in milliseconds, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist or does not specify a duration.
     * @see #getStreamDuration(File, StreamType, int)
     */
    public static long getStreamDuration(File mediaFile, int index) {
        return getStreamDuration(mediaFile, null, index);
    }
    
    /**
     * Returns the bitrate of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @return The bitrate of the specified stream in the media file in bits per second, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist or does not specify a bitrate.
     * @see #ffprobe(String, File)
     */
    public static long getStreamBitrate(File mediaFile, FFmpeg.StreamType streamType, int index) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -select_streams " + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + index +
                " -show_entries stream=bit_rate -of csv=p=0:e=none", mediaFile);
        try {
            return Long.parseLong(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return -1L;
    }
    
    /**
     * Returns the bitrate of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @return The bitrate of the specified stream in the media file in bits per second, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist or does not specify a bitrate.
     * @see #getStreamBitrate(File, StreamType, int)
     */
    public static long getStreamBitrate(File mediaFile, FFmpeg.StreamType streamType) {
        return getStreamBitrate(mediaFile, streamType, 0);
    }
    
    /**
     * Returns the bitrate of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param index     The index of the stream.
     * @return The bitrate of the specified stream in the media file in bits per second, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist or does not specify a bitrate.
     * @see #getStreamBitrate(File, StreamType, int)
     */
    public static long getStreamBitrate(File mediaFile, int index) {
        return getStreamBitrate(mediaFile, null, index);
    }
    
    /**
     * Returns the encoding of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @return The encoding of the specified stream in the media file, or null if the media file does not exist or cannot be read, or if the specified stream does not exist.
     * @see #ffprobe(String, File)
     */
    public static String getEncoding(File mediaFile, FFmpeg.StreamType streamType, int index) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -select_streams " + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + index +
                " -show_entries stream=codec_name -of csv=p=0:e=none", mediaFile);
        return !StringUtility.isWhitespace(ffprobeResponse) ? StringUtility.trim(ffprobeResponse) : null;
    }
    
    /**
     * Returns the encoding of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @return The encoding of the specified stream in the media file, or null if the media file does not exist or cannot be read, or if the specified stream does not exist.
     * @see #getEncoding(File, FFmpeg.StreamType, int)
     */
    public static String getEncoding(File mediaFile, FFmpeg.StreamType streamType) {
        return getEncoding(mediaFile, streamType, 0);
    }
    
    /**
     * Returns the encoding of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param index     The index of the stream.
     * @return The encoding of the specified stream in the media file, or null if the media file does not exist or cannot be read, or if the specified stream does not exist.
     * @see #getEncoding(File, FFmpeg.StreamType, int)
     */
    public static String getEncoding(File mediaFile, int index) {
        return getEncoding(mediaFile, null, index);
    }
    
    /**
     * Returns the number of frames in a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @return The number of frames in the specified stream in the media file, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist, or the stream frame count can not be determined.
     * @see #ffprobe(String, File)
     */
    public static long getFrameCount(File mediaFile, FFmpeg.StreamType streamType, int index) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -select_streams " + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + index +
                " -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none", mediaFile);
        try {
            return Integer.parseInt(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return -1L;
    }
    
    /**
     * Returns the number of frames in a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile  The media file.
     * @param streamType The type of stream.
     * @return The number of frames in the specified stream in the media file, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist, or the stream frame count can not be determined.
     * @see #getFrameCount(File, FFmpeg.StreamType, int)
     */
    public static long getFrameCount(File mediaFile, FFmpeg.StreamType streamType) {
        return getFrameCount(mediaFile, streamType, 0);
    }
    
    /**
     * Returns the number of frames in a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param index     The index of the stream.
     * @return The number of frames in the specified stream in the media file, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist, or the stream frame count can not be determined.
     * @see #getFrameCount(File, FFmpeg.StreamType, int)
     */
    public static long getFrameCount(File mediaFile, int index) {
        return getFrameCount(mediaFile, null, index);
    }
    
    
    //Getters
    
    /**
     * Returns the list of Codecs supported by ffmpeg.
     *
     * @return The list of Codecs supported by ffmpeg.
     */
    public static List<Codec> getCodecs() {
        if (codecs != null) {
            return codecs;
        }
        
        List<Codec> tmpCodecs = new ArrayList<>();
        boolean start = false;
        for (String codecLine : StringUtility.splitLines(ffmpeg("-hide_banner -codecs"))) {
            if (!start) {
                start = codecLine.matches("^\\s*-+$");
                continue;
            }
            tmpCodecs.add(new Codec(codecLine));
        }
        codecs = Collections.unmodifiableList(tmpCodecs);
        return codecs;
    }
    
    /**
     * Returns the list of Encoders supported by ffmpeg.
     *
     * @return The list of Encoders supported by ffmpeg.
     */
    public static List<Encoder> getEncoders() {
        if (encoders != null) {
            return encoders;
        }
        
        List<Encoder> tmpEncoders = new ArrayList<>();
        boolean start = false;
        for (String encoderLine : StringUtility.splitLines(ffmpeg("-hide_banner -encoders"))) {
            if (!start) {
                start = encoderLine.matches("^\\s*-+$");
                continue;
            }
            tmpEncoders.add(new Encoder(encoderLine));
        }
        encoders = Collections.unmodifiableList(tmpEncoders);
        return encoders;
    }
    
    /**
     * Returns the list of Decoders supported by ffmpeg.
     *
     * @return The list of Decoders supported by ffmpeg.
     */
    public static List<Decoder> getDecoders() {
        if (decoders != null) {
            return decoders;
        }
        
        List<Decoder> tmpDecoders = new ArrayList<>();
        boolean start = false;
        for (String decoderLine : StringUtility.splitLines(ffmpeg("-hide_banner -decoders"))) {
            if (!start) {
                start = decoderLine.matches("^\\s*-+$");
                continue;
            }
            tmpDecoders.add(new Decoder(decoderLine));
        }
        decoders = Collections.unmodifiableList(tmpDecoders);
        return decoders;
    }
    
    
    //Setters
    
    /**
     * Sets the ffmpeg executable to use.
     *
     * @param ffmpeg The ffmpeg executable, null to return to using the path accessible ffmpeg.
     */
    public static void setFFmpegExecutable(File ffmpeg) {
        FFmpeg.ffmpeg = ffmpeg;
        FFmpeg.codecs = null;
        FFmpeg.encoders = null;
        FFmpeg.decoders = null;
    }
    
    /**
     * Sets the ffprobe executable to use.
     *
     * @param ffprobe The ffprobe executable, null to return to using the path accessible ffprobe.
     */
    public static void setFFprobeExecutable(File ffprobe) {
        FFmpeg.ffprobe = ffprobe;
        FFmpeg.codecs = null;
        FFmpeg.encoders = null;
        FFmpeg.decoders = null;
    }
    
    /**
     * Sets the ffplay executable to use.
     *
     * @param ffplay The ffplay executable, null to return to using the path accessible ffplay.
     */
    public static void setFFplayExecutable(File ffplay) {
        FFmpeg.ffplay = ffplay;
        FFmpeg.codecs = null;
        FFmpeg.encoders = null;
        FFmpeg.decoders = null;
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
         * @param mediaFile The media file being operated on.
         */
        public FFmpegProgressBar(String title, File mediaFile) {
            super(title, FFmpeg.getFrameCount(mediaFile, StreamType.VIDEO), "frames");
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
    
    /**
     * Defines base metadata.
     */
    private static abstract class MetadataBase {
        
        //Fields
        
        /**
         * The raw metadata.
         */
        private JSONObject data;
        
        /**
         * The duration in milliseconds.
         */
        private long duration;
        
        /**
         * The start time in milliseconds.
         */
        private long startTime;
        
        /**
         * The end time in milliseconds.
         */
        private long endTime;
        
        /**
         * The title.
         */
        private String title;
        
        /**
         * The language.
         */
        private String language;
        
        /**
         * The map of tags.
         */
        private Map<String, Object> tags;
        
        
        //Constructors
        
        /**
         * Creates a new MetadataBase from json metadata.
         *
         * @param data The metadata in json.
         */
        @SuppressWarnings({"unchecked", "ConstantConditions"})
        public MetadataBase(JSONObject data) {
            this.data = data;
            
            this.tags = new LinkedHashMap<>();
            if (data.containsKey("tags")) {
                for (Map.Entry<String, Object> tag : (Set<Map.Entry<String, Object>>) ((JSONObject) data.get("tags")).entrySet()) {
                    this.tags.put(tag.getKey(), tag.getValue());
                }
            }
            
            this.duration = data.containsKey("duration") ? (long) (Double.parseDouble((String) data.get("duration")) * 1000000) :
                            (containsTag("duration") ? (DateTimeUtility.durationStampToDuration((String) getTag("duration")) * 1000) : -1L);
            this.startTime = data.containsKey("start_time") ? (long) (Double.parseDouble((String) data.get("start_time")) * 1000000) : 0L;
            this.endTime = data.containsKey("end_time") ? (long) (Double.parseDouble((String) data.get("end_time")) * 1000000) :
                           ((this.duration > 0) ? (this.startTime + this.duration) : -1L);
            this.duration = (this.duration > 0) ? this.duration : (this.endTime - this.startTime);
            this.title = (String) getTag("title");
            this.language = (String) getTag("language");
        }
        
        
        //Getters
        
        /**
         * Returns the raw metadata.
         *
         * @return The raw metadata.
         */
        public final JSONObject getData() {
            return data;
        }
        
        /**
         * Returns the duration in microseconds.
         *
         * @return The duration in microseconds, or -1 if the duration could not be determined.
         */
        public final long getDurationExact() {
            return duration;
        }
        
        /**
         * Returns the start time in microseconds.
         *
         * @return The start time in microseconds.
         */
        public final long getStartTimeExact() {
            return startTime;
        }
        
        /**
         * Returns the end time in microseconds.
         *
         * @return The end time in microseconds, or -1 if the end time could not be determined.
         */
        public final long getEndTimeExact() {
            return endTime;
        }
        
        /**
         * Returns the duration in milliseconds.
         *
         * @return The duration in milliseconds, or -1 if the duration could not be determined.
         */
        public final long getDuration() {
            return (duration > 0) ? (duration / 1000) : -1L;
        }
        
        /**
         * Returns the start time in milliseconds.
         *
         * @return The start time in milliseconds.
         */
        public final long getStartTime() {
            return (startTime / 1000);
        }
        
        /**
         * Returns the end time in milliseconds.
         *
         * @return The end time in milliseconds, or -1 if the end time could not be determined.
         */
        public final long getEndTime() {
            return (endTime > 0) ? (endTime / 1000) : -1L;
        }
        
        /**
         * Returns the title.
         *
         * @return The title, or null if there is no title.
         */
        public final String getTitle() {
            return title;
        }
        
        /**
         * Returns the language.
         *
         * @return The language, or null if the language is undefined.
         */
        public final String getLanguage() {
            return language;
        }
        
        /**
         * Returns the map of metadata tags.
         *
         * @return The map of metadata tags.
         */
        public final Map<String, Object> getTags() {
            return tags;
        }
        
        /**
         * Returns the metadata tag of a specified name.
         *
         * @param name The name of the tag.
         * @return The metadata tag of the specified name, or null if it doesn't exist.
         */
        public final Object getTag(String name) {
            if (tags.containsKey(name)) {
                return tags.get(name);
            }
            for (String tag : tags.keySet()) {
                if (tag.equalsIgnoreCase(name)) {
                    return tags.get(tag);
                }
            }
            return null;
        }
        
        /**
         * Returns whether the metadata tag of a specified name exists.
         *
         * @param name The name of the tag.
         * @return Whether the metadata tag of a specified name exists or not.
         */
        public final boolean containsTag(String name) {
            if (tags.containsKey(name)) {
                return true;
            }
            for (String tag : tags.keySet()) {
                if (tag.equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }
        
        /**
         * Returns the value from the json metadata of a specified key.
         *
         * @param key The key.
         * @return The value from the json metadata of the specified key, or null if it doesn't exist.
         */
        public final Object get(String key) {
            return data.get(key);
        }
        
    }
    
    /**
     * Defines the format metadata of a media file.
     */
    public static class FormatMetadata extends MetadataBase {
        
        //Fields
        
        /**
         * The media file.
         */
        private File mediaFile;
        
        /**
         * The size in bytes.
         */
        private long size;
        
        /**
         * The bit rate in bits per second.
         */
        private long bitrate;
        
        /**
         * The format name.
         */
        private String formatName;
        
        /**
         * The long format name.
         */
        private String formatNameLong;
        
        /**
         * The number of streams.
         */
        private int streamCount;
        
        /**
         * The number of programs.
         */
        private int programCount;
        
        
        //Constructors
        
        /**
         * Creates a new FormatMetadata from json metadata.
         *
         * @param data The format metadata in json.
         */
        public FormatMetadata(JSONObject data) {
            super(data);
            
            this.mediaFile = data.containsKey("filename") ? new File((String) data.get("filename")) : null;
            this.size = data.containsKey("size") ? (long) Double.parseDouble((String) data.get("size")) : 0L;
            this.bitrate = data.containsKey("bit_rate") ? Long.parseLong((String) data.get("bit_rate")) : 0L;
            this.formatName = data.containsKey("format_name") ? (String) data.get("format_name") : "";
            this.formatNameLong = data.containsKey("format_long_name") ? (String) data.get("format_long_name") : "";
            this.streamCount = data.containsKey("nb_streams") ? (int) (long) data.get("nb_streams") : 0;
            this.programCount = data.containsKey("nb_programs") ? (int) (long) data.get("nb_programs") : 0;
        }
        
        
        //Getters
        
        /**
         * Returns the media file.
         *
         * @return The media file.
         */
        public File getMediaFile() {
            return mediaFile;
        }
        
        /**
         * Returns the size in bytes.
         *
         * @return The size in bytes.
         */
        public long getSize() {
            return size;
        }
        
        /**
         * Returns the bitrate in bits per second.
         *
         * @return The bitrate in bits per second.
         */
        public long getBitrate() {
            return bitrate;
        }
        
        /**
         * Returns the format name.
         *
         * @return The format name.
         */
        public String getFormatName() {
            return formatName;
        }
        
        /**
         * Returns the long format name.
         *
         * @return The long format name.
         */
        public String getFormatNameLong() {
            return formatNameLong;
        }
        
        /**
         * Returns the number of streams.
         *
         * @return The number of streams.
         */
        public int getStreamCount() {
            return streamCount;
        }
        
        /**
         * Returns the number of programs.
         *
         * @return The number of programs.
         */
        public int getProgramCount() {
            return programCount;
        }
        
    }
    
    /**
     * Defines the metadata of a stream.
     */
    public static class StreamMetadata extends MetadataBase {
        
        //Fields
        
        /**
         * The type of stream.
         */
        private FFmpeg.StreamType streamType;
        
        /**
         * The index of the stream.
         */
        private int streamIndex;
        
        /**
         * A flag indicating whether the stream is a default stream or not.
         */
        private boolean isDefault;
        
        /**
         * The codec name.
         */
        private String codecName;
        
        /**
         * The long codec name.
         */
        private String codecNameLong;
        
        /**
         * The bitrate, or -1 if the bitrate is not specified.
         */
        private long bitrate;
        
        /**
         * The real base frame rate of the stream.
         */
        private double frameRateBase;
        
        /**
         * The average frame rate of the stream.
         */
        private double frameRateAverage;
        
        /**
         * The map of dispositions.
         */
        private Map<String, Boolean> dispositions;
        
        /**
         * The metadata specific to video streams.
         */
        private VideoStreamMetadata videoMetadata;
        
        /**
         * The metadata specific to audio streams.
         */
        private AudioStreamMetadata audioMetadata;
        
        /**
         * The metadata specific to subtitle streams.
         */
        private SubtitleStreamMetadata subtitleMetadata;
        
        /**
         * The metadata specific to data streams.
         */
        private DataStreamMetadata dataMetadata;
        
        
        //Constructors
        
        /**
         * Creates a new StreamMetadata from json metadata.
         *
         * @param data The stream metadata in json.
         */
        @SuppressWarnings("unchecked")
        public StreamMetadata(JSONObject data) {
            super(data);
            
            this.dispositions = new LinkedHashMap<>();
            if (data.containsKey("disposition")) {
                for (Map.Entry<String, Object> disposition : (Set<Map.Entry<String, Object>>) ((JSONObject) data.get("disposition")).entrySet()) {
                    this.dispositions.put(disposition.getKey(), ((int) (long) disposition.getValue()) != 0);
                }
            }
            
            this.streamType = data.containsKey("codec_type") ? FFmpeg.StreamType.valueOf(((String) data.get("codec_type")).toUpperCase()) : null;
            this.streamIndex = data.containsKey("index") ? (int) (long) data.get("index") : -1;
            this.isDefault = getDisposition("default");
            this.codecName = data.containsKey("codec_name") ? (String) data.get("codec_name") : "";
            this.codecNameLong = data.containsKey("codec_long_name") ? (String) data.get("codec_long_name") : "";
            this.bitrate = data.containsKey("bit_rate") ? Long.parseLong((String) data.get("bit_rate")) : -1L;
            
            this.frameRateBase = 0.0;
            if (data.containsKey("r_frame_rate")) {
                String frameRate = (String) data.get("r_frame_rate");
                if (frameRate.endsWith("/1")) {
                    this.frameRateBase = Double.parseDouble(StringUtility.rShear(frameRate, 2));
                } else if (!frameRate.endsWith("/0")) {
                    try {
                        EquationUtility.MathOperation operation = EquationUtility.parseMath(frameRate);
                        this.frameRateBase = (operation != null) ? operation.evaluate().doubleValue() : 0.0;
                    } catch (Exception ignored) {
                    }
                }
            }
            this.frameRateAverage = 0.0;
            if (data.containsKey("avg_frame_rate")) {
                String frameRate = (String) data.get("avg_frame_rate");
                if (frameRate.endsWith("/1")) {
                    this.frameRateAverage = Double.parseDouble(StringUtility.rShear(frameRate, 2));
                } else if (!frameRate.endsWith("/0")) {
                    try {
                        EquationUtility.MathOperation operation = EquationUtility.parseMath(frameRate);
                        this.frameRateAverage = (operation != null) ? operation.evaluate().doubleValue() : 0.0;
                    } catch (Exception ignored) {
                    }
                }
            }
            
            videoMetadata = new VideoStreamMetadata(data);
            audioMetadata = new AudioStreamMetadata(data);
            subtitleMetadata = new SubtitleStreamMetadata(data);
            dataMetadata = new DataStreamMetadata(data);
        }
        
        
        //Getters
        
        /**
         * Returns the type of the stream.
         *
         * @return The type of the stream.
         */
        public FFmpeg.StreamType getStreamType() {
            return streamType;
        }
        
        /**
         * Returns the index of the stream.
         *
         * @return The index of the stream, or -1 if there was an error.
         */
        public int getStreamIndex() {
            return streamIndex;
        }
        
        /**
         * Returns whether the stream is a default stream or not.
         *
         * @return Whether the stream is a default stream or not.
         */
        public boolean isDefaultStream() {
            return isDefault;
        }
        
        /**
         * Returns the codec name.
         *
         * @return The codec name.
         */
        public String getCodecName() {
            return codecName;
        }
        
        /**
         * Returns the long codec name.
         *
         * @return The long codec name.
         */
        public String getCodecNameLong() {
            return codecNameLong;
        }
        
        /**
         * Returns the bitrate.
         *
         * @return The bitrate, or -1 if the bitrate is not specified.
         */
        public long getBitrate() {
            return bitrate;
        }
        
        /**
         * Returns the real base frame rate of the stream.
         *
         * @return The real base frame rate of the stream.
         */
        public double getFrameRateBase() {
            return frameRateBase;
        }
        
        /**
         * Returns the average frame rate of the stream.
         *
         * @return The average frame rate of the stream.
         */
        public double getFrameRateAverage() {
            return frameRateAverage;
        }
        
        /**
         * Returns the map of stream dispositions.
         *
         * @return The map of stream dispositions.
         */
        public Map<String, Boolean> getDispositions() {
            return dispositions;
        }
        
        /**
         * Returns whether the stream has the disposition of a specified name.
         *
         * @param name The name of the disposition.
         * @return Whether the stream has the disposition of the specified name.
         */
        public boolean getDisposition(String name) {
            if (dispositions.containsKey(name)) {
                return dispositions.get(name);
            }
            for (String disposition : dispositions.keySet()) {
                if (disposition.equalsIgnoreCase(name)) {
                    return dispositions.get(disposition);
                }
            }
            return false;
        }
        
        /**
         * Returns the metadata specific to video streams.
         *
         * @return The metadata specific to video streams.
         */
        public VideoStreamMetadata getVideoMetadata() {
            return videoMetadata;
        }
        
        /**
         * Returns the metadata specific to audio streams.
         *
         * @return The metadata specific to audio streams.
         */
        public AudioStreamMetadata getAudioMetadata() {
            return audioMetadata;
        }
        
        /**
         * Returns the metadata specific to subtitle streams.
         *
         * @return The metadata specific to subtitle streams.
         */
        public SubtitleStreamMetadata getSubtitleMetadata() {
            return subtitleMetadata;
        }
        
        /**
         * Returns the metadata specific to data streams.
         *
         * @return The metadata specific to data streams.
         */
        public DataStreamMetadata getDataMetadata() {
            return dataMetadata;
        }
        
        
        //Inner Classes
        
        /**
         * Defines the metadata of a video stream.
         */
        public static class VideoStreamMetadata {
            
            //Fields
            
            /**
             * The width of the video stream.
             */
            private int width;
            
            /**
             * The height of the video stream.
             */
            private int height;
            
            /**
             * The level of the video stream.
             */
            private int level;
            
            /**
             * The pixel format of the video stream.
             */
            private String pixelFormat;
            
            /**
             * The aspect ratio of the video stream.
             */
            private String aspectRatio;
            
            /**
             * The profile of the video stream.
             */
            private String profile;
            
            /**
             * A flag indicating whether the video stream has closed captions or not.
             */
            private boolean closedCaptions;
            
            
            //Constructors
            
            /**
             * Creates a new VideoStreamMetadata from json metadata.
             *
             * @param data The stream metadata in json.
             */
            public VideoStreamMetadata(JSONObject data) {
                this.width = data.containsKey("width") ? (int) (long) data.get("width") : -1;
                this.height = data.containsKey("height") ? (int) (long) data.get("height") : -1;
                this.level = data.containsKey("level") ? (int) (long) data.get("level") : -1;
                this.pixelFormat = data.containsKey("pix_fmt") ? (String) data.get("pix_fmt") : null;
                this.aspectRatio = data.containsKey("display_aspect_ratio") ? (String) data.get("display_aspect_ratio") : null;
                this.profile = data.containsKey("profile") ? (String) data.get("profile") : null;
                this.closedCaptions = data.containsKey("closed_captions") && (((int) (long) data.get("closed_captions")) > 0);
            }
            
            
            //Getters
            
            /**
             * Returns the width of the video stream.
             *
             * @return The width of the video stream, or -1 if it is not a video stream or it cannot be determined.
             */
            public int getWidth() {
                return width;
            }
            
            /**
             * Returns the height of the video stream.
             *
             * @return The height of the video stream, or -1 if it is not a video stream or it cannot be determined.
             */
            public int getHeight() {
                return height;
            }
            
            /**
             * Returns the level of the video stream.
             *
             * @return The level of the video stream, or -1 if it is not a video stream or it cannot be determined.
             */
            public int getLevel() {
                return level;
            }
            
            /**
             * Returns the pixel format of the video stream.
             *
             * @return The pixel format of the video stream, or null if it is not a video stream or it cannot be determined.
             */
            public String getPixelFormat() {
                return pixelFormat;
            }
            
            /**
             * Returns the aspect ratio of the video stream.
             *
             * @return The aspect ratio of the video stream, or null if it is not a video stream or it cannot be determined.
             */
            public String getAspectRatio() {
                return aspectRatio;
            }
            
            /**
             * Returns the profile of the video stream.
             *
             * @return The profile of the video stream, or null if it is not a video stream or it cannot be determined.
             */
            public String getProfile() {
                return profile;
            }
            
            /**
             * Returns whether the video stream has closed captions or not.
             *
             * @return Whether the video stream has closed captions or not, or false if it is not a video stream or it cannot be determined.
             */
            public boolean hasClosedCaptions() {
                return closedCaptions;
            }
            
        }
        
        /**
         * Defines the metadata of an audio stream.
         */
        public static class AudioStreamMetadata {
            
            //Fields
            
            /**
             * The number of channels of the audio stream.
             */
            private int channels;
            
            /**
             * The channel layout of the audio stream.
             */
            private String channelLayout;
            
            /**
             * The sample rate of the audio stream.
             */
            private int sampleRate;
            
            /**
             * The sample format of the audio stream.
             */
            private String sampleFormat;
            
            /**
             * The bits per sample of the audio stream.
             */
            private int bitsPerSample;
            
            
            //Constructors
            
            /**
             * Creates a new AudioStreamMetadata from json metadata.
             *
             * @param data The stream metadata in json.
             */
            public AudioStreamMetadata(JSONObject data) {
                this.channels = data.containsKey("channels") ? (int) (long) data.get("channels") : -1;
                this.channelLayout = data.containsKey("channel_layout") ? (String) data.get("channel_layout") : null;
                this.sampleRate = data.containsKey("sample_rate") ? Integer.parseInt((String) data.get("sample_rate")) : -1;
                this.sampleFormat = data.containsKey("sample_fmt") ? (String) data.get("sample_fmt") : null;
                this.bitsPerSample = data.containsKey("bits_per_sample") ? (int) (long) data.get("bits_per_sample") : -1;
            }
            
            
            //Getters
            
            /**
             * Returns the number of channels of the audio stream.
             *
             * @return The number of channels of the audio stream, or -1 if it is not a audio stream or it cannot be determined.
             */
            public int getChannels() {
                return channels;
            }
            
            /**
             * Returns the channel layout of the audio stream.
             *
             * @return The channel layout of the audio stream, or null if it is not a audio stream or it cannot be determined.
             */
            public String getChannelLayout() {
                return channelLayout;
            }
            
            /**
             * Returns the sample rate of the audio stream.
             *
             * @return The sample rate of the audio stream, or -1 if it is not a audio stream or it cannot be determined.
             */
            public int getSampleRate() {
                return sampleRate;
            }
            
            /**
             * Returns the sample format of the audio stream.
             *
             * @return The sample format of the audio stream, or null if it is not a audio stream or it cannot be determined.
             */
            public String getSampleFormat() {
                return sampleFormat;
            }
            
            /**
             * Returns the bits per sample of the audio stream.
             *
             * @return The bits per sample of the audio stream, or -1 if it is not a audio stream or it cannot be determined.
             */
            public int getBitsPerSample() {
                return bitsPerSample;
            }
            
        }
        
        /**
         * Defines the metadata of a subtitle stream.
         */
        public static class SubtitleStreamMetadata {
            
            //Constructors
            
            /**
             * Creates a new SubtitleStreamMetadata from json metadata.
             *
             * @param data The stream metadata in json.
             */
            @SuppressWarnings("EmptyMethod")
            public SubtitleStreamMetadata(JSONObject data) {
            }
            
        }
        
        /**
         * Defines the metadata of a data stream.
         */
        public static class DataStreamMetadata {
            
            //Constructors
            
            /**
             * Creates a new DataStreamMetadata from json metadata.
             *
             * @param data The stream metadata in json.
             */
            @SuppressWarnings("EmptyMethod")
            public DataStreamMetadata(JSONObject data) {
            }
            
        }
        
    }
    
    /**
     * Defines the metadata of a chapter.
     */
    public static class ChapterMetadata extends MetadataBase {
        
        //Fields
        
        /**
         * The chapter id.
         */
        private long chapterId;
        
        
        //Constructors
        
        /**
         * Creates a new ChapterMetadata from json metadata.
         *
         * @param data The chapter metadata in json.
         */
        public ChapterMetadata(JSONObject data) {
            super(data);
            
            this.chapterId = data.containsKey("id") ? (long) data.get("id") : 0;
        }
        
        
        //Getters
        
        /**
         * Returns the chapter id.
         *
         * @return The chapter id.
         */
        public long getChapterId() {
            return chapterId;
        }
        
    }
    
    /**
     * Defines metadata.
     */
    public static class Metadata {
        
        //Fields
        
        /**
         * The raw metadata.
         */
        private JSONObject data;
        
        /**
         * The format metadata.
         */
        private FormatMetadata formatMetadata;
        
        /**
         * The stream metadata.
         */
        private List<StreamMetadata> streamMetadata;
        
        /**
         * The chapter metadata.
         */
        private List<ChapterMetadata> chapterMetadata;
        
        
        //Constructors
        
        /**
         * Creates a new Metadata from json metadata.
         *
         * @param data The metadata in json.
         */
        public Metadata(JSONObject data) {
            this.data = data;
            
            this.formatMetadata = new FormatMetadata((JSONObject) data.get("format"));
            
            this.streamMetadata = new ArrayList<>();
            for (Object stream : (JSONArray) data.get("streams")) {
                this.streamMetadata.add(new StreamMetadata((JSONObject) stream));
            }
            
            this.chapterMetadata = new ArrayList<>();
            for (Object chapter : (JSONArray) data.get("chapters")) {
                this.chapterMetadata.add(new ChapterMetadata((JSONObject) chapter));
            }
        }
        
        
        //Getters
        
        /**
         * Returns the raw metadata.
         *
         * @return The raw metadata.
         */
        public JSONObject getData() {
            return data;
        }
        
        /**
         * Returns the format metadata.
         *
         * @return The format metadata.
         */
        public FormatMetadata getFormat() {
            return formatMetadata;
        }
        
        /**
         * Returns the stream metadata.
         *
         * @return The stream metadata.
         */
        public List<StreamMetadata> getStreams() {
            return streamMetadata;
        }
        
        /**
         * Returns the chapter metadata.
         *
         * @return The chapter metadata.
         */
        public List<ChapterMetadata> getChapters() {
            return chapterMetadata;
        }
        
    }
    
    /**
     * Defines an FFmpeg codec.
     */
    public static class Codec {
        
        //Constants
        
        /**
         * The regex pattern for an ffmpeg codec line.
         */
        public static final Pattern CODEC_LINE_PATTERN = Pattern.compile("^\\s*(?<supportsDecoding>.)(?<supportsEncoding>.)(?<type>.)(?<isIntraFrameOnly>.)(?<hasLossyCompression>.)(?<hasLosslessCompression>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$");
        
        
        //Fields
        
        /**
         * The name of the codec.
         */
        private String name;
        
        /**
         * The description of the codec.
         */
        private String description;
        
        /**
         * The stream type the codec is for.
         */
        private StreamType type;
        
        /**
         * Whether or not the codec supports decoding.
         */
        private boolean supportsDecoding;
        
        /**
         * Whether or not the codec supports encoding.
         */
        private boolean supportsEncoding;
        
        /**
         * Whether or not the codec is intra frame-only.
         */
        private boolean isIntraFrameOnly;
        
        /**
         * Whether or not the codec uses lossy compression.
         */
        private boolean hasLossyCompression;
        
        /**
         * Whether or not the codec uses lossless compression.
         */
        private boolean hasLosslessCompression;
        
        
        //Constructors
        
        /**
         * Creates a new Codec from an ffmpeg codec line.
         *
         * @param codecLine The ffmpeg codec line.
         */
        public Codec(String codecLine) {
            final Matcher codecLineMatcher = CODEC_LINE_PATTERN.matcher(codecLine);
            if (codecLineMatcher.matches()) {
                this.name = codecLineMatcher.group("name");
                this.description = codecLineMatcher.group("description");
                this.type = Arrays.stream(StreamType.values())
                        .filter(e -> StringUtility.lSnip(e.name(), 1).equalsIgnoreCase(codecLineMatcher.group("type")))
                        .findFirst().orElse(null);
                this.supportsDecoding = !codecLineMatcher.group("supportsDecoding").equals(".");
                this.supportsEncoding = !codecLineMatcher.group("supportsEncoding").equals(".");
                this.isIntraFrameOnly = !codecLineMatcher.group("isIntraFrameOnly").equals(".");
                this.hasLossyCompression = !codecLineMatcher.group("hasLossyCompression").equals(".");
                this.hasLosslessCompression = !codecLineMatcher.group("hasLosslessCompression").equals(".");
            }
        }
        
        
        //Getters
        
        /**
         * Returns the name of the codec.
         *
         * @return The name of the codec.
         */
        public String getName() {
            return name;
        }
        
        /**
         * Returns the description of the codec.
         *
         * @return The description of the codec.
         */
        public String getDescription() {
            return description;
        }
        
        /**
         * Returns the stream type the codec is for.
         *
         * @return The stream type the codec is for.
         */
        public StreamType getType() {
            return type;
        }
        
        /**
         * Returns whether or not the codec supports decoding.
         *
         * @return Whether or not the codec supports decoding.
         */
        public boolean supportsDecoding() {
            return supportsDecoding;
        }
        
        /**
         * Returns whether or not the codec supports encoding.
         *
         * @return Whether or not the codec supports encoding.
         */
        public boolean supportsEncoding() {
            return supportsEncoding;
        }
        
        /**
         * Returns whether or not the codec is intra frame-only.
         *
         * @return Whether or not the codec is intra frame-only.
         */
        public boolean isIntraFrameOnly() {
            return isIntraFrameOnly;
        }
        
        /**
         * Returns whether or not the codec uses lossy compression.
         *
         * @return Whether or not the codec uses lossy compression.
         */
        public boolean hasLossyCompression() {
            return hasLossyCompression;
        }
        
        /**
         * Returns whether or not the codec uses lossless compression.
         *
         * @return Whether or not the codec uses lossless compression.
         */
        public boolean hasLosslessCompression() {
            return hasLosslessCompression;
        }
        
    }
    
    /**
     * Defines an FFmpeg coder.
     */
    private static abstract class Coder {
        
        //Constants
        
        /**
         * The regex pattern for an ffmpeg coder line.
         */
        public static final Pattern CODER_LINE_PATTERN = Pattern.compile("^\\s*(?<type>.)(?<hasFrameLevelMultithreading>.)(?<hasSliceLevelMultithreading>.)(?<isExperimental>.)(?<supportsDrawHorizontalBand>.)(?<supportsDirectRenderingMethod1>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$");
        
        
        //Fields
        
        /**
         * The name of the coder.
         */
        private String name;
        
        /**
         * The description of the coder.
         */
        private String description;
        
        /**
         * The stream type the coder is for.
         */
        private StreamType type;
        
        /**
         * Whether or not the coder has frame-level multithreading.
         */
        private boolean hasFrameLevelMultithreading;
        
        /**
         * Whether or not the coder has slice-level multithreading.
         */
        private boolean hasSliceLevelMultithreading;
        
        /**
         * Whether or not the coder is experimental.
         */
        private boolean isExperimental;
        
        /**
         * Whether or not the coder supports drawing horizontal bands.
         */
        private boolean supportsDrawHorizontalBand;
        
        /**
         * Whether or not the coder supports direct rendering method 1.
         */
        private boolean supportsDirectRenderingMethod1;
        
        
        //Constructors
        
        /**
         * Creates a new Coder from an ffmpeg coder line.
         *
         * @param coderLine The ffmpeg coder line.
         */
        public Coder(String coderLine) {
            final Matcher coderLineMatcher = CODER_LINE_PATTERN.matcher(coderLine);
            if (coderLineMatcher.matches()) {
                this.name = coderLineMatcher.group("name");
                this.description = coderLineMatcher.group("description");
                this.type = Arrays.stream(StreamType.values())
                        .filter(e -> StringUtility.lSnip(e.name(), 1).equalsIgnoreCase(coderLineMatcher.group("type")))
                        .findFirst().orElse(null);
                this.hasFrameLevelMultithreading = !coderLineMatcher.group("hasFrameLevelMultithreading").equals(".");
                this.hasSliceLevelMultithreading = !coderLineMatcher.group("hasSliceLevelMultithreading").equals(".");
                this.isExperimental = !coderLineMatcher.group("isExperimental").equals(".");
                this.supportsDrawHorizontalBand = !coderLineMatcher.group("supportsDrawHorizontalBand").equals(".");
                this.supportsDirectRenderingMethod1 = !coderLineMatcher.group("supportsDirectRenderingMethod1").equals(".");
            }
        }
        
        
        //Getters
        
        /**
         * Returns the name of the coder.
         *
         * @return The name of the coder.
         */
        public String getName() {
            return name;
        }
        
        /**
         * Returns the description of the coder.
         *
         * @return The description of the coder.
         */
        public String getDescription() {
            return description;
        }
        
        /**
         * Returns the stream type the coder is for.
         *
         * @return The stream type the coder is for.
         */
        public StreamType getType() {
            return type;
        }
        
        /**
         * Returns whether or not the coder has frame-level multithreading.
         *
         * @return Whether or not the coder has frame-level multithreading.
         */
        public boolean hasFrameLevelMultithreading() {
            return hasFrameLevelMultithreading;
        }
        
        /**
         * Returns whether or not the coder has slice-level multithreading.
         *
         * @return Whether or not the coder has slice-level multithreading.
         */
        public boolean hasSliceLevelMultithreading() {
            return hasSliceLevelMultithreading;
        }
        
        /**
         * Returns whether or not the coder is experimental.
         *
         * @return Whether or not the coder is experimental.
         */
        public boolean isExperimental() {
            return isExperimental;
        }
        
        /**
         * Returns whether or not the coder supports drawing horizontal bands.
         *
         * @return Whether or not the coder supports drawing horizontal bands.
         */
        public boolean supportsDrawHorizontalBand() {
            return supportsDrawHorizontalBand;
        }
        
        /**
         * Returns whether or not the coder supports direct rendering method 1.
         *
         * @return Whether or not the coder supports direct rendering method 1.
         */
        public boolean supportsDirectRenderingMethod1() {
            return supportsDirectRenderingMethod1;
        }
        
    }
    
    /**
     * Defines an FFmpeg encoder.
     */
    public static class Encoder extends Coder {
        
        //Constants
        
        /**
         * The regex pattern for an ffmpeg encoder line.
         */
        public static final Pattern ENCODER_LINE_PATTERN = CODER_LINE_PATTERN;
        
        
        //Constructors
        
        /**
         * Creates a new Encoder from an ffmpeg encoder line.
         *
         * @param encoderLine The ffmpeg encoder line.
         */
        public Encoder(String encoderLine) {
            super(encoderLine);
        }
        
    }
    
    /**
     * Defines an FFmpeg decoder.
     */
    public static class Decoder extends Coder {
        
        //Constants
        
        /**
         * The regex pattern for an ffmpeg decoder line.
         */
        public static final Pattern DECODER_LINE_PATTERN = CODER_LINE_PATTERN;
        
        
        //Constructors
        
        /**
         * Creates a new Decoder from an ffmpeg decoder line.
         *
         * @param decoderLine The ffmpeg decoder line.
         */
        public Decoder(String decoderLine) {
            super(decoderLine);
        }
        
    }
    
}
