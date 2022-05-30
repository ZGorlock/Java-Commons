/*
 * File:    FFmpeg.java
 * Package: commons.io.file.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.file.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import commons.access.CmdLine;
import commons.access.Filesystem;
import commons.io.console.Console;
import commons.io.console.ProgressBar;
import commons.lambda.stream.collector.MapCollectors;
import commons.log.CommonsLogging;
import commons.math.number.BoundUtility;
import commons.math.number.NumberUtility;
import commons.math.string.EquationUtility;
import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
import commons.object.string.EntityStringUtility;
import commons.object.string.StringUtility;
import commons.time.DateTimeUtility;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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
     * An enumeration of Stream Types.
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
     * The maximum muxing queue size to allow ffmpeg to use, or -1 for default.
     */
    public static int maxMuxingQueueSize = -1;
    
    
    //Static Methods
    
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
        final String executable = (ffmpeg == null) ? "ffmpeg" : StringUtility.quote(ffmpeg.getAbsolutePath());
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
     * @param progressBar The ffmpeg progress bar.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(String, FFmpegProgressBar)
     */
    public static String ffmpeg(String inputParams, List<File> sourceFiles, String params, File outputFile, FFmpegProgressBar progressBar) {
        final String cmd = "-hide_banner" +
                ((progressBar == null) ? "" : " -progress - -nostats") +
                (StringUtility.isNullOrBlank(inputParams) ? "" : ' ') + StringUtility.trim(inputParams) +
                sourceFiles.stream().map(e -> StringUtility.quote(e.getAbsolutePath()))
                        .collect(Collectors.joining(" -i ", " -i ", "")) +
                (StringUtility.isNullOrBlank(params) ? "" : ' ') + StringUtility.trim(params) +
                ' ' + StringUtility.quote(outputFile.getAbsolutePath());
        
        if ((progressBar != null) && StringUtility.isNullOrEmpty(progressBar.getTitle())) {
            progressBar.updateTitle("FFmpeg " + Console.ConsoleEffect.GREY.apply(cmd));
        }
        return ffmpeg(cmd, progressBar);
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
     * @see #ffmpeg(String, List, String, File, FFmpegProgressBar)
     */
    public static String ffmpeg(String inputParams, List<File> sourceFiles, String params, File outputFile, boolean progressBar) {
        return ffmpeg(inputParams, sourceFiles, params, outputFile,
                ((progressBar && !sourceFiles.isEmpty()) ? new FFmpegProgressBar("", sourceFiles, outputFile) : null));
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param sourceFiles The source files to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @param progressBar The ffmpeg progress bar.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(String, List, String, File, FFmpegProgressBar)
     */
    public static String ffmpeg(List<File> sourceFiles, String params, File outputFile, FFmpegProgressBar progressBar) {
        return ffmpeg("", sourceFiles, params, outputFile, progressBar);
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
     * @param progressBar The ffmpeg progress bar.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(String, List, String, File, FFmpegProgressBar)
     */
    public static String ffmpeg(String inputParams, File sourceFile, String params, File outputFile, FFmpegProgressBar progressBar) {
        return ffmpeg(inputParams, Collections.singletonList(sourceFile), params, outputFile, progressBar);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param inputParams The input parameters send to ffmpeg.
     * @param sourceFile  The source file to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @param progressBar Whether or not to show a progress bar.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(String, List, String, File, boolean)
     */
    public static String ffmpeg(String inputParams, File sourceFile, String params, File outputFile, boolean progressBar) {
        return ffmpeg(inputParams, Collections.singletonList(sourceFile), params, outputFile, progressBar);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param sourceFile  The source file to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @param progressBar The ffmpeg progress bar.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(List, String, File, FFmpegProgressBar)
     */
    public static String ffmpeg(File sourceFile, String params, File outputFile, FFmpegProgressBar progressBar) {
        return ffmpeg(Collections.singletonList(sourceFile), params, outputFile, progressBar);
    }
    
    /**
     * Executes an ffmpeg process.
     *
     * @param sourceFile  The source file to send to ffmpeg.
     * @param params      The parameters send to ffmpeg.
     * @param outputFile  The output file to send to ffmpeg.
     * @param progressBar Whether or not to show a progress bar.
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
        final String executable = (ffmpeg == null) ? "ffmpeg" : StringUtility.quote(ffmpeg.getAbsolutePath());
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
                (StringUtility.isNullOrEmpty(inputParams) ? "" : ' ') + StringUtility.trim(inputParams) +
                sourceFiles.stream().map(e -> StringUtility.quote(e.getAbsolutePath()))
                        .collect(Collectors.joining(" -i ", " -i ", "")) +
                (StringUtility.isNullOrEmpty(params) ? "" : ' ') + StringUtility.trim(params) +
                ' ' + StringUtility.quote(outputFile.getAbsolutePath()));
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
        final String executable = (ffprobe == null) ? "ffprobe" : StringUtility.quote(ffprobe.getAbsolutePath());
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
                (StringUtility.isNullOrEmpty(params) ? "" : ' ') + StringUtility.trim(params) +
                ' ' + StringUtility.quote(sourceFile.getAbsolutePath()));
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
        final String executable = (ffprobe == null) ? "ffprobe" : StringUtility.quote(ffprobe.getAbsolutePath());
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
                (StringUtility.isNullOrEmpty(params) ? "" : ' ') + StringUtility.trim(params) +
                ' ' + StringUtility.quote(sourceFile.getAbsolutePath()));
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
        final String executable = (ffplay == null) ? "ffplay" : StringUtility.quote(ffplay.getAbsolutePath());
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
                (StringUtility.isNullOrEmpty(params) ? "" : ' ') + StringUtility.trim(params) +
                ' ' + StringUtility.quote(sourceFile.getAbsolutePath()));
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
        final String executable = (ffplay == null) ? "ffplay" : StringUtility.quote(ffplay.getAbsolutePath());
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
                (StringUtility.isNullOrEmpty(params) ? "" : ' ') + StringUtility.trim(params) +
                ' ' + StringUtility.quote(sourceFile.getAbsolutePath()));
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
    public static MediaInfo getMediaInfo(File mediaFile) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -show_format -show_streams -show_chapters -print_format json -byte_binary_prefix", mediaFile);
        if (!StringUtility.removeWhiteSpace(ffprobeResponse).equals("{}")) {
            try {
                return new MediaInfo((JSONObject) new JSONParser().parse(ffprobeResponse));
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
    public static MediaInfo.Format getFormat(File mediaFile) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -show_format -print_format json -byte_binary_prefix", mediaFile);
        try {
            return new MediaInfo.Format((JSONObject) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("format"));
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
    public static List<MediaInfo.Stream> getStreams(File mediaFile) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -show_streams -print_format json -byte_binary_prefix", mediaFile);
        try {
            return Arrays.stream(((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("streams")).toArray())
                    .map(e -> new MediaInfo.Stream((JSONObject) e)).collect(Collectors.toList());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns a stream metadata from a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param streamId  The stream identifier.
     * @return The specified stream metadata from the media file, or null if the media file does not exist or cannot be read, or if the specified stream does not exist.
     * @see #ffprobe(String, File)
     */
    public static MediaInfo.Stream getStream(File mediaFile, Identifier.Stream<Identifier.Scope.Singular> streamId) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -select_streams " + streamId.specifier() +
                " -show_streams -print_format json -byte_binary_prefix", mediaFile);
        try {
            return new MediaInfo.Stream((JSONObject) ((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("streams")).get(0));
        } catch (Exception ignored) {
        }
        return null;
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
        final String ffprobeResponse = ffprobe("-v quiet" +
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
    public static int getStreamCount(File mediaFile, StreamType streamType) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -select_streams " + Identifier.Stream.of(streamType).specifier() +
                " -show_streams -print_format json", mediaFile);
        try {
            return ((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("streams")).toArray().length;
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
    public static List<MediaInfo.Chapter> getChapters(File mediaFile) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -show_chapters -print_format json -byte_binary_prefix", mediaFile);
        try {
            return Arrays.stream(((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters")).toArray())
                    .map(e -> new MediaInfo.Chapter((JSONObject) e)).collect(Collectors.toList());
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
    public static MediaInfo.Chapter getChapter(File mediaFile, int index) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -show_chapters -print_format json -byte_binary_prefix", mediaFile);
        try {
            return new MediaInfo.Chapter((JSONObject) ((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters")).get(index));
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
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -show_chapters -print_format json -byte_binary_prefix", mediaFile);
        try {
            return ((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters")).toArray().length;
        } catch (Exception ignored) {
        }
        return -1;
    }
    
    /**
     * Adds chapters to a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param chapters The list of chapters to add to the media file.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(List, String, File)
     */
    public static String addChapters(File source, MediaInfo.Chapter.ChapterDTO[] chapters, File output) {
        return ffmpeg(Arrays.asList(source,
                        MediaInfo.Chapter.ChapterDTO.generateFFmetadataFile(Arrays.asList(chapters))),
                "-y -strict -2" +
                        " -c copy -map_metadata 0 -map_chapters 1 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Adds chapters to a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param chapters The list of chapters to add to the media file.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #addChapters(File, MediaInfo.Chapter.ChapterDTO[], File)
     */
    public static String addChapters(File source, List<MediaInfo.Chapter> chapters, File output) {
        return addChapters(source,
                chapters.stream().map(MediaInfo.Chapter.ChapterDTO::new).toArray(MediaInfo.Chapter.ChapterDTO[]::new),
                output);
    }
    
    /**
     * Strips the chapters from a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The media file.
     * @param output The output media file.
     * @return The response from the ffmpeg process.
     * @see #stripMediaFile(File, boolean, boolean, File)
     */
    public static String stripChapters(File source, File output) {
        return stripMediaFile(source, false, true, output);
    }
    
    /**
     * Returns the metadata tags of an entity of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param entityId  The entity identifier.
     * @return The metadata tags for the specified entity of the media file, or null if the media file does not exist or cannot be read, or if the specified entity does not exist.
     * @see #ffprobe(String, File)
     */
    public static MediaInfo.MetadataTags getMetadata(File mediaFile, Identifier<Identifier.Scope.Singular> entityId) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                (entityId.isStreamId() ? (" -select_streams " + entityId.specifier()) : "") +
                (entityId.isStreamId() ? " -show_streams" : (entityId.isChapterId() ? " -show_chapters" : (entityId.isGlobalId() ? " -show_format" : ""))) +
                " -print_format json -byte_binary_prefix", mediaFile);
        try {
            return entityId.isStreamId() ? new MediaInfo.MetadataTags((JSONObject) ((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("streams")).get(0)) :
                   entityId.isChapterId() ? new MediaInfo.MetadataTags((JSONObject) ((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters")).get(entityId.getIndex())) :
                   entityId.isGlobalId() ? new MediaInfo.MetadataTags((JSONObject) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("format")) : null;
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns the global metadata tags of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The global metadata tags of the media file, or null if the media file does not exist or cannot be read.
     * @see #getMetadata(File, Identifier)
     */
    public static MediaInfo.MetadataTags getMetadata(File mediaFile) {
        return getMetadata(mediaFile, Identifier.Global.get());
    }
    
    /**
     * Modifies the metadata tags for entities of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source        The media file.
     * @param modifications The map of entity identifier and their corresponding metadata tags.
     * @param output        The output media file.
     * @param clearExisting Whether to clear the existing metadata tags of the specified entities before writing the new metadata tags or not.
     * @param isRemove      Whether to remove the specified metadata tags or not; if not they will be added.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(File, String, File)
     */
    private static String modifyMetadata(File source, Map<Identifier<Identifier.Scope.Singular>, MediaInfo.MetadataTags> modifications, File output, boolean clearExisting, boolean isRemove) {
        return ffmpeg(source,
                "-y -strict -2" +
                        ((maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + maxMuxingQueueSize) : "") +
                        " -map 0 -c copy -map_metadata 0 -map_chapters 0" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1" +
                        modifications.entrySet().stream().map(e ->
                                (clearExisting ? Optional.ofNullable(getMetadata(source, e.getKey())).map(m -> m.clearMetadataCmd(e.getKey())).orElse("") : "") +
                                        ((e.getValue() != null) ? (isRemove ? e.getValue().clearMetadataCmd(e.getKey()) : e.getValue().setMetadataCmd(e.getKey())) : "")
                        ).collect(Collectors.joining()),
                output);
    }
    
    /**
     * Modifies the metadata tags for entities of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source        The media file.
     * @param modifications The map of entity identifier and their corresponding metadata tags.
     * @param output        The output media file.
     * @param clearExisting Whether to clear the existing metadata tags of the specified entities before writing the new metadata tags or not.
     * @param isRemove      Whether to remove the specified metadata tags or not; if not they will be added.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadata(File, Map, File, boolean, boolean)
     */
    private static String modifyMetadataHelper(File source, Map<Identifier<?>, MediaInfo.MetadataTags> modifications, File output, boolean clearExisting, boolean isRemove) {
        return (modifications.size() == 1) ?
               modifyMetadataHelper(source, modifications.keySet().iterator().next(), modifications.values().iterator().next(), output, clearExisting, isRemove) :
               modifyMetadata(source,
                       Identifier.decompose(new ArrayList<>(modifications.keySet()), new ArrayList<>(modifications.values()), source),
                       output, clearExisting, isRemove);
    }
    
    /**
     * Modifies the metadata tags for an entity of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source        The media file.
     * @param entityId      The entity identifier.
     * @param modification  The metadata tags to modify.
     * @param output        The output media file.
     * @param clearExisting Whether to clear the existing metadata tags of the specified entities before writing the new metadata tags or not.
     * @param isRemove      Whether to remove the specified metadata tags or not; if not they will be added.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadata(File, Map, File, boolean, boolean)
     */
    @SuppressWarnings("unchecked")
    private static String modifyMetadataHelper(File source, Identifier<?> entityId, MediaInfo.MetadataTags modification, File output, boolean clearExisting, boolean isRemove) {
        return modifyMetadata(source,
                entityId.isSingularScoped() ? MapUtility.mapOf(LinkedHashMap.class,
                        new ImmutablePair<>((Identifier<Identifier.Scope.Singular>) entityId, modification)) :
                Identifier.decompose(Collections.singletonList(entityId), Collections.singletonList(modification), source),
                output, clearExisting, isRemove);
    }
    
    /**
     * Sets the metadata tags for entities of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source        The media file.
     * @param modifications The map of entity identifier and the corresponding metadata tags to set.
     * @param output        The output media file.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadataHelper(File, Map, File, boolean, boolean)
     */
    public static String setMetadata(File source, Map<Identifier<?>, MediaInfo.MetadataTags> modifications, File output) {
        return modifyMetadataHelper(source, modifications, output, true, false);
    }
    
    /**
     * Sets the metadata tags for an entity of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param entityId The entity identifier.
     * @param tags     The metadata tags to set.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadataHelper(File, Identifier, MediaInfo.MetadataTags, File, boolean, boolean)
     */
    public static String setMetadata(File source, Identifier<?> entityId, MediaInfo.MetadataTags tags, File output) {
        return modifyMetadataHelper(source, entityId, tags, output, true, false);
    }
    
    /**
     * Sets the global metadata tags for a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The media file.
     * @param tags   The metadata tags to set.
     * @param output The output media file.
     * @return The response from the ffmpeg process.
     * @see #setMetadata(File, Identifier, MediaInfo.MetadataTags, File)
     */
    public static String setMetadata(File source, MediaInfo.MetadataTags tags, File output) {
        return setMetadata(source, Identifier.Global.get(), tags, output);
    }
    
    /**
     * Adds metadata tags to entities of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source    The media file.
     * @param additions The map of entity identifier and the corresponding metadata tags to add.
     * @param output    The output media file.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadataHelper(File, Map, File, boolean, boolean)
     */
    public static String addMetadata(File source, Map<Identifier<?>, MediaInfo.MetadataTags> additions, File output) {
        return modifyMetadataHelper(source, additions, output, false, false);
    }
    
    /**
     * Adds metadata tags to an entity of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source    The media file.
     * @param entityId  The entity identifier.
     * @param additions The metadata tags to add.
     * @param output    The output media file.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadataHelper(File, Identifier, MediaInfo.MetadataTags, File, boolean, boolean)
     */
    public static String addMetadata(File source, Identifier<?> entityId, MediaInfo.MetadataTags additions, File output) {
        return modifyMetadataHelper(source, entityId, additions, output, false, false);
    }
    
    /**
     * Adds global metadata tags to a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The media file.
     * @param tags   The metadata tags to add.
     * @param output The output media file.
     * @return The response from the ffmpeg process.
     * @see #addMetadata(File, Identifier, MediaInfo.MetadataTags, File)
     */
    public static String addMetadata(File source, MediaInfo.MetadataTags tags, File output) {
        return addMetadata(source, Identifier.Global.get(), tags, output);
    }
    
    /**
     * Adds a metadata tag to an entity of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param entityId The entity identifier.
     * @param tagName  The name of the metadata tag to add.
     * @param tagValue The value of the metadata tag to add.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #addMetadata(File, Identifier, MediaInfo.MetadataTags, File)
     */
    @SuppressWarnings("unchecked")
    public static String addMetadata(File source, Identifier<?> entityId, String tagName, String tagValue, File output) {
        return addMetadata(source, entityId, new MediaInfo.MetadataTags(
                new ImmutablePair<>(tagName, tagValue)
        ), output);
    }
    
    /**
     * Adds a global metadata tag to a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param tagName  The name of the metadata tag to add.
     * @param tagValue The value of the metadata tag to add.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #addMetadata(File, Identifier, String, String, File)
     */
    public static String addMetadata(File source, String tagName, String tagValue, File output) {
        return addMetadata(source, Identifier.Global.get(), tagName, tagValue, output);
    }
    
    /**
     * Removes metadata tags from entities of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param removals The map of entity identifier and the corresponding metadata tags to remove.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadataHelper(File, Map, File, boolean, boolean)
     */
    public static String removeMetadata(File source, Map<Identifier<?>, MediaInfo.MetadataTags> removals, File output) {
        return modifyMetadataHelper(source, removals, output, false, true);
    }
    
    /**
     * Removes metadata tags from an entity of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param entityId The entity identifier.
     * @param removals The metadata tags to remove.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadataHelper(File, Identifier, MediaInfo.MetadataTags, File, boolean, boolean)
     */
    public static String removeMetadata(File source, Identifier<?> entityId, MediaInfo.MetadataTags removals, File output) {
        return modifyMetadataHelper(source, entityId, removals, output, false, true);
    }
    
    /**
     * Removes global metadata tags from a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param removals The metadata tags to remove.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #removeMetadata(File, Identifier, MediaInfo.MetadataTags, File)
     */
    public static String removeMetadata(File source, MediaInfo.MetadataTags removals, File output) {
        return removeMetadata(source, Identifier.Global.get(), removals, output);
    }
    
    /**
     * Removes metadata tags from an entity of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param entityId The entity identifier.
     * @param tagNames The names of the metadata tags to remove.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #removeMetadata(File, Identifier, MediaInfo.MetadataTags, File)
     */
    public static String removeMetadata(File source, Identifier<?> entityId, List<String> tagNames, File output) {
        return removeMetadata(source, entityId, new MediaInfo.MetadataTags(tagNames), output);
    }
    
    /**
     * Removes global metadata tags from a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param tagNames The names of the metadata tags to remove.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #removeMetadata(File, Identifier, List, File)
     */
    public static String removeMetadata(File source, List<String> tagNames, File output) {
        return removeMetadata(source, Identifier.Global.get(), tagNames, output);
    }
    
    /**
     * Removes a metadata tag from an entity of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param entityId The entity identifier.
     * @param tagName  The name of the metadata tag.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #removeMetadata(File, Identifier, List, File)
     */
    public static String removeMetadata(File source, Identifier<?> entityId, String tagName, File output) {
        return removeMetadata(source, entityId, Collections.singletonList(tagName), output);
    }
    
    /**
     * Removes a global metadata tag from a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source  The media file.
     * @param tagName The name of the metadata tag to remove.
     * @param output  The output media file.
     * @return The response from the ffmpeg process.
     * @see #removeMetadata(File, Identifier, String, File)
     */
    public static String removeMetadata(File source, String tagName, File output) {
        return removeMetadata(source, Identifier.Global.get(), tagName, output);
    }
    
    /**
     * Clears all metadata tags from entities of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source    The media file.
     * @param entityIds The list of entity identifiers.
     * @param output    The output media file.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadataHelper(File, Map, File, boolean, boolean)
     */
    public static String clearMetadata(File source, List<Identifier<?>> entityIds, File output) {
        return modifyMetadataHelper(source, MapUtility.mapOf(
                entityIds,
                ListUtility.create(MediaInfo.MetadataTags.class, entityIds.size())
        ), output, true, true);
    }
    
    /**
     * Clears all metadata tags from an entity of a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The media file.
     * @param entityId The entity identifier.
     * @param output   The output media file.
     * @return The response from the ffmpeg process.
     * @see #modifyMetadataHelper(File, Identifier, MediaInfo.MetadataTags, File, boolean, boolean)
     */
    public static String clearMetadata(File source, Identifier<?> entityId, File output) {
        return modifyMetadataHelper(source, entityId, null, output, true, true);
    }
    
    /**
     * Clears all global metadata tags from a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The media file.
     * @param output The output media file.
     * @return The response from the ffmpeg process.
     * @see #clearMetadata(File, Identifier, File)
     */
    public static String clearMetadata(File source, File output) {
        return clearMetadata(source, Identifier.Global.get(), output);
    }
    
    /**
     * Strips all metadata tags from a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The media file.
     * @param output The output media file.
     * @return The response from the ffmpeg process.
     * @see #stripMediaFile(File, boolean, boolean, File)
     */
    public static String stripMetadata(File source, File output) {
        return stripMediaFile(source, true, false, output);
    }
    
    /**
     * Returns the dispositions of a stream of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param streamId  The stream identifier.
     * @return The dispositions of the specified stream of the media file, or null if the media file does not exist or cannot be read, or if the specified stream does not exist.
     * @see #ffprobe(String, File)
     */
    public static MediaInfo.Stream.Disposition getDisposition(File mediaFile, Identifier.Stream<Identifier.Scope.Singular> streamId) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -select_streams " + streamId.specifier() +
                " -show_streams -print_format json -byte_binary_prefix", mediaFile);
        try {
            return new MediaInfo.Stream.Disposition((JSONObject) ((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("streams")).get(0));
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns the container format of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile   The media file.
     * @param abbreviated Whether or not to abbreviate the format name.
     * @return The container format of the media file, or null if the media file does not exist or cannot be read.
     * @see #ffprobe(String, File)
     */
    public static String getContainer(File mediaFile, boolean abbreviated) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -show_entries format=format" + (abbreviated ? "" : "_long") + "_name -of csv=p=0:e=none", mediaFile);
        return !StringUtility.isNullOrBlank(ffprobeResponse) ? StringUtility.trim(ffprobeResponse) : null;
    }
    
    /**
     * Returns the container format of a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @return The container format of the media file, or null if the media file does not exist or cannot be read.
     * @see #getContainer(File, boolean)
     */
    public static String getContainer(File mediaFile) {
        return getContainer(mediaFile, false);
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
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -show_entries format=duration -of csv=p=0:e=none", mediaFile);
        try {
            return Long.parseLong(StringUtility.trim(ffprobeResponse).replace(".", "")) / 1000;
        } catch (Exception ignored) {
        }
        return -1L;
    }
    
    /**
     * Returns the duration of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param streamId  The stream identifier.
     * @return The duration of the specified stream in the media file in milliseconds, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist or does not specify a duration.
     * @see #ffprobe(String, File)
     */
    public static long getStreamDuration(File mediaFile, Identifier.Stream<Identifier.Scope.Singular> streamId) {
        final MediaInfo.Stream stream = getStream(mediaFile, streamId);
        return (stream != null) ? stream.getDuration() : -1L;
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
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -show_entries format=bit_rate -of csv=p=0:e=none", mediaFile);
        try {
            return Long.parseLong(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return -1;
    }
    
    /**
     * Returns the bitrate of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param streamId  The stream identifier.
     * @return The bitrate of the specified stream in the media file in bits per second, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist or does not specify a bitrate.
     * @see #ffprobe(String, File)
     */
    public static long getStreamBitrate(File mediaFile, Identifier.Stream<Identifier.Scope.Singular> streamId) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -select_streams " + streamId.specifier() +
                " -show_entries stream=bit_rate -of csv=p=0:e=none", mediaFile);
        try {
            return Long.parseLong(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return -1L;
    }
    
    /**
     * Returns the encoding of a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param streamId  The stream identifier.
     * @return The encoding of the specified stream in the media file, or null if the media file does not exist or cannot be read, or if the specified stream does not exist.
     * @see #ffprobe(String, File)
     */
    public static String getStreamEncoding(File mediaFile, Identifier.Stream<Identifier.Scope.Singular> streamId) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -select_streams " + streamId.specifier() +
                " -show_entries stream=codec_name -of csv=p=0:e=none", mediaFile);
        return !StringUtility.isNullOrBlank(ffprobeResponse) ? StringUtility.trim(ffprobeResponse) : null;
    }
    
    /**
     * Returns the number of frames in a stream in a media file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param mediaFile The media file.
     * @param streamId  The stream identifier.
     * @return The number of frames in the specified stream in the media file, or -1 if the media file does not exist or cannot be read, or if the specified stream does not exist, or the stream frame count can not be determined.
     * @see #ffprobe(String, File)
     */
    public static long getFrameCount(File mediaFile, Identifier.Stream<Identifier.Scope.Singular> streamId) {
        final String ffprobeResponse = ffprobe("-v quiet" +
                " -select_streams " + streamId.specifier() +
                " -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none", mediaFile);
        try {
            return Integer.parseInt(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return -1L;
    }
    
    /**
     * Strips the metadata and/or chapters from a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source        The media file.
     * @param stripMetadata Whether or not to strip metadata.
     * @param stripChapters Whether or not to strip chapters.
     * @param output        The output media file.
     * @return The response from the ffmpeg process.
     * @see #ffmpeg(File, String, File)
     */
    public static String stripMediaFile(File source, boolean stripMetadata, boolean stripChapters, File output) {
        return ffmpeg(source,
                "-y -strict -2" +
                        ((maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + maxMuxingQueueSize) : "") +
                        " -map 0 -c copy" +
                        " -map_metadata " + (stripMetadata ?
                                             "-1 -write_xing 0 -id3v2_version 0 -write_id3v2 0 -write_apetag 0" :
                                             "0 -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1") +
                        " -map_chapters " + (stripChapters ? "-1" : "0"),
                output);
    }
    
    /**
     * Strips the metadata, chapters, and closed captions from a media file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The media file.
     * @param output The output media file.
     * @return The response from the ffmpeg process.
     * @see #stripMediaFile(File, boolean, boolean, File)
     */
    public static String stripMediaFile(File source, File output) {
        return stripMediaFile(source, true, true, output);
    }
    
    
    //Getters
    
    /**
     * Returns the maximum muxing queue size to allow ffmpeg to use.
     *
     * @return The maximum muxing queue size to allow ffmpeg to use.
     */
    public static int getMaxMuxingQueueSize() {
        return maxMuxingQueueSize;
    }
    
    /**
     * Returns the list of Formats supported by ffmpeg.
     *
     * @return The list of Formats supported by ffmpeg.
     * @see Implements#getFormats()
     */
    public static List<Implements.Format> getFormats() {
        return Implements.getFormats();
    }
    
    /**
     * Returns the list of Demuxers supported by ffmpeg.
     *
     * @return The list of Demuxers supported by ffmpeg.
     * @see Implements#getDemuxers()
     */
    public static List<Implements.Demuxer> getDemuxers() {
        return Implements.getDemuxers();
    }
    
    /**
     * Returns the list of Muxers supported by ffmpeg.
     *
     * @return The list of Muxers supported by ffmpeg.
     * @see Implements#getMuxers()
     */
    public static List<Implements.Muxer> getMuxers() {
        return Implements.getMuxers();
    }
    
    /**
     * Returns the list of Devices supported by ffmpeg.
     *
     * @return The list of Devices supported by ffmpeg.
     * @see Implements#getDevices()
     */
    public static List<Implements.Device> getDevices() {
        return Implements.getDevices();
    }
    
    /**
     * Returns the list of Codecs supported by ffmpeg.
     *
     * @return The list of Codecs supported by ffmpeg.
     * @see Implements#getCodecs()
     */
    public static List<Implements.Codec> getCodecs() {
        return Implements.getCodecs();
    }
    
    /**
     * Returns the list of Decoders supported by ffmpeg.
     *
     * @return The list of Decoders supported by ffmpeg.
     * @see Implements#getDecoders()
     */
    public static List<Implements.Decoder> getDecoders() {
        return Implements.getDecoders();
    }
    
    /**
     * Returns the list of Encoders supported by ffmpeg.
     *
     * @return The list of Encoders supported by ffmpeg.
     * @see Implements#getEncoders()
     */
    public static List<Implements.Encoder> getEncoders() {
        return Implements.getEncoders();
    }
    
    /**
     * Returns the list of BitstreamFilters supported by ffmpeg.
     *
     * @return The list of BitstreamFilters supported by ffmpeg.
     * @see Implements#getBitstreamFilters()
     */
    public static List<Implements.BitstreamFilter> getBitstreamFilters() {
        return Implements.getBitstreamFilters();
    }
    
    /**
     * Returns the list of Protocols supported by ffmpeg.
     *
     * @return The list of Protocols supported by ffmpeg.
     * @see Implements#getProtocols()
     */
    public static List<Implements.Protocol> getProtocols() {
        return Implements.getProtocols();
    }
    
    /**
     * Returns the list of Filters supported by ffmpeg.
     *
     * @return The list of Filters supported by ffmpeg.
     * @see Implements#getFilters()
     */
    public static List<Implements.Filter> getFilters() {
        return Implements.getFilters();
    }
    
    /**
     * Returns the list of PixelFormats supported by ffmpeg.
     *
     * @return The list of PixelFormats supported by ffmpeg.
     * @see Implements#getPixelFormats()
     */
    public static List<Implements.PixelFormat> getPixelFormats() {
        return Implements.getPixelFormats();
    }
    
    /**
     * Returns the list of SampleFormats supported by ffmpeg.
     *
     * @return The list of SampleFormats supported by ffmpeg.
     * @see Implements#getSampleFormats()
     */
    public static List<Implements.SampleFormat> getSampleFormats() {
        return Implements.getSampleFormats();
    }
    
    /**
     * Returns the list of Channels supported by ffmpeg.
     *
     * @return The list of Channels supported by ffmpeg.
     * @see Implements#getChannels()
     */
    public static List<Implements.Channel> getChannels() {
        return Implements.getChannels();
    }
    
    /**
     * Returns the list of ChannelLayouts supported by ffmpeg.
     *
     * @return The list of ChannelLayouts supported by ffmpeg.
     * @see Implements#getChannelLayouts()
     */
    public static List<Implements.ChannelLayout> getChannelLayouts() {
        return Implements.getChannelLayouts();
    }
    
    /**
     * Returns the list of Colors supported by ffmpeg.
     *
     * @return The list of Colors supported by ffmpeg.
     * @see Implements#getColors()
     */
    public static List<Implements.Color> getColors() {
        return Implements.getColors();
    }
    
    
    //Setters
    
    /**
     * Sets the ffmpeg executable to use.
     *
     * @param ffmpeg The ffmpeg executable, null to return to using the path accessible ffmpeg.
     */
    public static void setFFmpegExecutable(File ffmpeg) {
        if (FFmpeg.ffmpeg != ffmpeg) {
            Implements.clearCache();
        }
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
    
    /**
     * Sets the maximum muxing queue size to allow ffmpeg to use.
     *
     * @param maxMuxingQueueSize The maximum muxing queue size to allow ffmpeg to use, or -1 for default.
     */
    public static void setMaxMuxingQueueSize(int maxMuxingQueueSize) {
        FFmpeg.maxMuxingQueueSize = maxMuxingQueueSize;
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
        public static final Pattern MUXING_PROGRESS_PATTERN = Pattern.compile("^\\s*out_time_us=(?<outputTime>\\d+)\\s*$");
        
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
         * The source files being operated on by FFmpeg.
         */
        private final List<File> sourceFiles;
        
        /**
         * The output file of the FFmpeg process.
         */
        private final File outputFile;
        
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
         * @param title          The title to display for the progress bar.
         * @param sourceFiles    The source files being operated on.
         * @param outputFile     The output file.
         * @param outputDuration The estimated final duration of the output file, in seconds.
         */
        public FFmpegProgressBar(String title, List<File> sourceFiles, File outputFile, long outputDuration) {
            super(title, Math.max(outputDuration, 0), "s");
            this.sourceFiles = ListUtility.unmodifiableList(sourceFiles);
            this.outputFile = outputFile;
        }
        
        /**
         * Creates a new FFmpegProgressBar object.
         *
         * @param sourceFiles    The source files being operated on.
         * @param outputFile     The output file.
         * @param outputDuration The estimated final duration of the output file, in seconds.
         * @see #FFmpegProgressBar(String, List, File, long)
         */
        public FFmpegProgressBar(List<File> sourceFiles, File outputFile, long outputDuration) {
            this("", sourceFiles, outputFile, outputDuration);
        }
        
        /**
         * Creates a new FFmpegProgressBar object.
         *
         * @param title          The title to display for the progress bar.
         * @param sourceFile     The source file being operated on.
         * @param outputFile     The output file.
         * @param outputDuration The estimated final duration of the output file, in seconds.
         * @see #FFmpegProgressBar(String, List, File, long)
         */
        public FFmpegProgressBar(String title, File sourceFile, File outputFile, long outputDuration) {
            this(title, Collections.singletonList(sourceFile), outputFile, outputDuration);
        }
        
        /**
         * Creates a new FFmpegProgressBar object.
         *
         * @param sourceFile     The source file being operated on.
         * @param outputFile     The output file.
         * @param outputDuration The estimated final duration of the output file, in seconds.
         * @see #FFmpegProgressBar(String, File, File, long)
         */
        public FFmpegProgressBar(File sourceFile, File outputFile, long outputDuration) {
            this("", sourceFile, outputFile, outputDuration);
        }
        
        /**
         * Creates a new FFmpegProgressBar object.
         *
         * @param title       The title to display for the progress bar.
         * @param sourceFiles The source files being operated on.
         * @param outputFile  The output file.
         * @see #FFmpegProgressBar(String, List, File, long)
         */
        public FFmpegProgressBar(String title, List<File> sourceFiles, File outputFile) {
            this(title, sourceFiles, outputFile,
                    (sourceFiles.stream().mapToLong(FFmpeg::getDuration).max().orElse(1000L) / 1000));
        }
        
        /**
         * Creates a new FFmpegProgressBar object.
         *
         * @param sourceFiles The source files being operated on.
         * @param outputFile  The output file.
         * @see #FFmpegProgressBar(String, List, File)
         */
        public FFmpegProgressBar(List<File> sourceFiles, File outputFile) {
            this("", sourceFiles, outputFile);
        }
        
        /**
         * Creates a new FFmpegProgressBar object.
         *
         * @param title      The title to display for the progress bar.
         * @param sourceFile The source file being operated on.
         * @param outputFile The output file.
         * @see #FFmpegProgressBar(String, List, File)
         */
        public FFmpegProgressBar(String title, File sourceFile, File outputFile) {
            this(title, Collections.singletonList(sourceFile), outputFile);
        }
        
        /**
         * Creates a new FFmpegProgressBar object.
         *
         * @param sourceFile The source file being operated on.
         * @param outputFile The output file.
         * @see #FFmpegProgressBar(String, File, File)
         */
        public FFmpegProgressBar(File sourceFile, File outputFile) {
            this("", sourceFile, outputFile);
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
                    return update(Long.parseLong(muxingProgressMatcher.group("outputTime")) / 1000000);
                    
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
     * Defines media metadata.
     */
    public static class MediaInfo {
        
        //Fields
        
        /**
         * The raw metadata.
         */
        private JSONObject data;
        
        /**
         * The format metadata.
         */
        private Format format;
        
        /**
         * The stream metadata.
         */
        private List<Stream> streams;
        
        /**
         * The chapter metadata.
         */
        private List<Chapter> chapters;
        
        
        //Constructors
        
        /**
         * Creates a new Metadata from json metadata.
         *
         * @param data The metadata in json.
         */
        public MediaInfo(JSONObject data) {
            this.data = data;
            
            this.format = new Format((JSONObject) data.get("format"));
            
            this.streams = new ArrayList<>();
            for (Object stream : (JSONArray) data.get("streams")) {
                this.streams.add(new Stream((JSONObject) stream));
            }
            
            this.chapters = new ArrayList<>();
            for (Object chapter : (JSONArray) data.get("chapters")) {
                this.chapters.add(new Chapter((JSONObject) chapter));
            }
        }
        
        
        //Methods
        
        /**
         * Returns a string representation of the MediaInfo.
         *
         * @return A string representation of the MediaInfo.
         */
        @Override
        public String toString() {
            return "Media (" + format.getMediaFile().getName() + "): " +
                    format.getFormatName() + ": " +
                    format.getSize() + "B: " +
                    format.getBitrate() + "B/s: " +
                    "Streams [" + streams.size() + "]: " +
                    "Chapters [" + chapters.size() + "]: " +
                    "Programs [" + format.getProgramCount() + "]";
        }
        
        /**
         * Returns a string representation of the MediaInfo and all its components.
         *
         * @return A string representation of the MediaInfo and all its components.
         */
        public String fullString() {
            return java.util.stream.Stream.of(
                    Arrays.asList(toString(), format.toString()),
                    streams.stream().map(Stream::toString).collect(Collectors.toList()),
                    chapters.stream().map(Chapter::toString).collect(Collectors.toList())
            ).flatMap(Collection::stream).collect(Collectors.joining(System.lineSeparator()));
        }
        
        /**
         * Returns a string representation of the metadata of the MediaInfo and all its components.
         *
         * @return A string representation of the metadata of the MediaInfo and all its components.
         */
        public String metadataString() {
            return java.util.stream.Stream.of(
                    Collections.singletonList("Global: " + getMetadata().toString()),
                    streams.stream().map(e -> "Stream #" + e.getStreamIndex() + ": " + e.getMetadata().toString()).collect(Collectors.toList()),
                    chapters.stream().map(e -> "Chapter #" + e.getChapterId() + ": " + e.getMetadata().toString()).collect(Collectors.toList())
            ).flatMap(Collection::stream).collect(Collectors.joining(System.lineSeparator()));
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
        public Format getFormat() {
            return format;
        }
        
        /**
         * Returns the stream metadata.
         *
         * @return The stream metadata.
         */
        public List<Stream> getStreams() {
            return streams;
        }
        
        /**
         * Returns a stream metadata specified by a stream specifier.
         *
         * @param streamId The stream specifier.
         * @return The stream metadata specified by the stream specifier, or null if the stream does not exist.
         */
        public Stream getStream(Identifier.Stream<Identifier.Scope.Singular> streamId) {
            List<Stream> searchStreams = (streamId.getStreamType() == null) ? streams : streams.stream()
                    .filter(e -> Objects.equals(e.getStreamType(), streamId.getStreamType())).collect(Collectors.toList());
            return BoundUtility.inListBounds(streamId.getIndex(), searchStreams) ?
                   searchStreams.get(streamId.getIndex()) : null;
        }
        
        /**
         * Returns the chapter metadata.
         *
         * @return The chapter metadata.
         */
        public List<Chapter> getChapters() {
            return chapters;
        }
        
        /**
         * Returns the global metadata tags.
         *
         * @return The global metadata tags.
         */
        public MetadataTags getMetadata() {
            return format.getMetadata();
        }
        
        /**
         * Returns the metadata tags of a stream metadata specified by a stream specifier.
         *
         * @param streamId The stream specifier.
         * @return The metadata tags of the stream metadata specified by the stream specifier, or null if the stream does not exist.
         */
        public MetadataTags getMetadata(Identifier.Stream<Identifier.Scope.Singular> streamId) {
            return Optional.ofNullable(getStream(streamId)).map(MetadataBase::getMetadata).orElse(null);
        }
        
        
        //Inner Classes
        
        /**
         * Defines metadata tags.
         */
        public static class MetadataTags {
            
            //Fields
            
            /**
             * The map of metadata tags.
             */
            private Map<String, String> tags;
            
            
            //Constructors
            
            /**
             * Creates a new MetadataTags from a map of metadata tags.
             *
             * @param tags The map of metadata tags.
             */
            public MetadataTags(LinkedHashMap<String, String> tags) {
                this.tags = new LinkedHashMap<>(tags);
            }
            
            /**
             * Creates a new MetadataTags from a set of metadata tags.
             *
             * @param tags The set of metadata tags.
             * @see #MetadataTags(LinkedHashMap)
             */
            @SuppressWarnings("unchecked")
            public MetadataTags(Pair<String, String>... tags) {
                this((LinkedHashMap<String, String>) MapUtility.mapOf(LinkedHashMap.class, tags));
            }
            
            /**
             * Creates a new MetadataTags from a list of metadata tag names; values will be null.
             *
             * @param tagNames The list of metadata tag names.
             * @see #MetadataTags(LinkedHashMap)
             */
            public MetadataTags(List<String> tagNames) {
                this((LinkedHashMap<String, String>) MapUtility.mapOf(LinkedHashMap.class, tagNames, ListUtility.create(String.class, tagNames.size())));
            }
            
            /**
             * Creates a new MetadataTags from json metadata.
             *
             * @param data The metadata in json.
             * @see #MetadataTags(LinkedHashMap)
             */
            @SuppressWarnings({"unchecked"})
            public MetadataTags(JSONObject data) {
                this(((Set<Map.Entry<String, Object>>)
                        ((Optional.ofNullable((JSONObject) data.get("tags")).orElse(new JSONObject())).entrySet())).stream()
                        .collect(MapCollectors.toLinkedHashMap(Map.Entry::getKey, (e -> String.valueOf(e.getValue())))));
            }
            
            
            //Methods
            
            /**
             * Returns the metadata tags.
             *
             * @return The metadata tags.
             */
            public Map<String, String> get() {
                return tags;
            }
            
            /**
             * Returns a metadata tag of a specified name.
             *
             * @param name The name of the tag.
             * @return The metadata tag of the specified name, or null if it doesn't exist.
             */
            public String get(String name) {
                return tags.containsKey(name) ? tags.get(name) :
                       tags.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase(name))
                               .findAny().map(Map.Entry::getValue).orElse(null);
            }
            
            /**
             * Returns whether a metadata tag of a specified name exists.
             *
             * @param name The name of the tag.
             * @return Whether the metadata tag of a specified name exists or not.
             */
            public boolean contains(String name) {
                return tags.containsKey(name) ||
                        tags.entrySet().stream().anyMatch(e -> e.getKey().equalsIgnoreCase(name));
            }
            
            /**
             * Returns the number of metadata tags.
             *
             * @return The number of metadata tags.
             */
            public int size() {
                return tags.size();
            }
            
            /**
             * Generates a parameter string for ffmpeg which sets the metadata tags.
             *
             * @param entityId The entity identifier specifying the entity to set the metadata tags for.
             * @return The parameter string.
             */
            public String setMetadataCmd(Identifier<?> entityId) {
                return tags.entrySet().stream().map(e ->
                        "-metadata:" + entityId.classSpecifier() + ' ' + formatString(e.getKey()) + '=' + formatString(e.getValue())
                ).collect(Collectors.joining(" ", " ", ""));
            }
            
            /**
             * Generates a parameter string for ffmpeg which sets the metadata tags.
             *
             * @return The parameter string.
             * @see #setMetadataCmd(Identifier)
             */
            public String setMetadataCmd() {
                return setMetadataCmd(Identifier.Global.get());
            }
            
            /**
             * Generates a parameter string for ffmpeg which clears the metadata tags.
             *
             * @param entityId The entity identifier specifying the entity to clear the metadata tags for.
             * @return The parameter string.
             */
            public String clearMetadataCmd(Identifier<?> entityId) {
                return tags.keySet().stream().map(e ->
                        "-metadata:" + entityId.classSpecifier() + ' ' + formatString(e) + '='
                ).collect(Collectors.joining(" ", " ", ""));
            }
            
            /**
             * Generates a parameter string for ffmpeg which clears the metadata tags.
             *
             * @return The parameter string.
             * @see #clearMetadataCmd(Identifier)
             */
            public String clearMetadataCmd() {
                return clearMetadataCmd(Identifier.Global.get());
            }
            
            /**
             * Returns a string representation of the MetadataTags.
             *
             * @return A string representation of the MetadataTags.
             */
            @Override
            public String toString() {
                return tags.entrySet().stream().map(e ->
                                e.getKey() + ':' + Optional.ofNullable(e.getValue()).map(e2 -> StringUtility.quote(e2, true)).orElse("null"))
                        .collect(Collectors.joining(", ", "{", "}"));
            }
            
            /**
             * Determines if two MetadataTags are equal.
             *
             * @param o The other MetadataTags.
             * @return Whether the MetadataTags are equal or not.
             */
            @Override
            public boolean equals(Object o) {
                if (!(o instanceof MetadataTags)) {
                    return false;
                }
                MetadataTags other = (MetadataTags) o;
                
                return (size() == other.size()) &&
                        tags.keySet().stream().allMatch(e -> Objects.equals(get(e), other.get(e)));
            }
            
            
            //Static Methods
            
            /**
             * Formats a metadata tag key or value.
             *
             * @param string The metadata tag key or value.
             * @return The formatted metadata tag key or value.
             */
            public static String formatString(String string) {
                return StringUtility.isNullOrEmpty(string) ? "" :
                       StringUtility.quote(string
                               .replaceAll("[\r\n]+", " - ")
                               .replace("\"", "'")
                               .replace("=", "-"));
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
             * The metadata tags.
             */
            private MetadataTags tags;
            
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
            
            
            //Constructors
            
            /**
             * Creates a new MetadataBase from json metadata.
             *
             * @param data The metadata in json.
             */
            public MetadataBase(JSONObject data) {
                this.data = data;
                
                this.tags = new MetadataTags(data);
                
                this.duration = data.containsKey("duration") ? Long.parseLong(((String) data.get("duration")).replace(".", "")) :
                                (this.tags.contains("duration") ? (DateTimeUtility.durationStampToDuration(this.tags.get("duration")) * 1000) : -1L);
                this.startTime = data.containsKey("start_time") ? Long.parseLong(((String) data.get("start_time")).replace(".", "")) : 0L;
                this.endTime = data.containsKey("end_time") ? Long.parseLong(((String) data.get("end_time")).replace(".", "")) :
                               ((this.duration > 0) ? (this.startTime + this.duration) : -1L);
                this.duration = (this.duration > 0) ? this.duration : (this.endTime - this.startTime);
                this.title = this.tags.get("title");
                this.language = this.tags.get("language");
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
             * Returns the metadata tags.
             *
             * @return The metadata tags.
             */
            public final MetadataTags getMetadata() {
                return tags;
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
             * Returns the duration as a time stamp in the format 'HH:mm:ss.SSS'.
             *
             * @return The duration as a time stamp in the format 'HH:mm:ss.SSS', or an empty string if the duration could not be determined.
             */
            public final String getDurationTimestamp() {
                return (duration < 0) ? "" :
                       DateTimeUtility.durationToDurationStamp(getDuration());
            }
            
            /**
             * Returns the start time as a time stamp in the format 'HH:mm:ss.SSS'.
             *
             * @return The start time as a time stamp in the format 'HH:mm:ss.SSS'.
             */
            public final String getStartTimestamp() {
                return DateTimeUtility.durationToDurationStamp(getStartTime());
            }
            
            /**
             * Returns the end time as a time stamp in the format 'HH:mm:ss.SSS'.
             *
             * @return The end time as a time stamp in the format 'HH:mm:ss.SSS', or an empty string if the end time could not be determined.
             */
            public final String getEndTimestamp() {
                return (endTime < 0) ? "" :
                       DateTimeUtility.durationToDurationStamp(getEndTime());
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
        public static class Format extends MetadataBase {
            
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
             * Creates a new Format from json metadata.
             *
             * @param data The format metadata in json.
             */
            public Format(JSONObject data) {
                super(data);
                
                this.mediaFile = data.containsKey("filename") ? new File((String) data.get("filename")) : null;
                this.size = data.containsKey("size") ? (long) Double.parseDouble((String) data.get("size")) : 0L;
                this.bitrate = data.containsKey("bit_rate") ? Long.parseLong((String) data.get("bit_rate")) : 0L;
                this.formatName = data.containsKey("format_name") ? (String) data.get("format_name") : "";
                this.formatNameLong = data.containsKey("format_long_name") ? (String) data.get("format_long_name") : "";
                this.streamCount = data.containsKey("nb_streams") ? (int) (long) data.get("nb_streams") : 0;
                this.programCount = data.containsKey("nb_programs") ? (int) (long) data.get("nb_programs") : 0;
            }
            
            
            //Methods
            
            /**
             * Returns a string representation of the Format.
             *
             * @return A string representation of the Format.
             */
            @Override
            public String toString() {
                return "Format (" + mediaFile.getName() + "): " +
                        formatName + ": " +
                        size + "B: " +
                        bitrate + "B/s: " +
                        "Streams [" + streamCount + "]: " +
                        "Programs [" + programCount + "]";
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
        public static class Stream extends MetadataBase {
            
            //Fields
            
            /**
             * The type of stream.
             */
            private StreamType streamType;
            
            /**
             * The index of the stream.
             */
            private int streamIndex;
            
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
             * The metadata specific to video streams.
             */
            private VideoInfo videoInfo;
            
            /**
             * The metadata specific to audio streams.
             */
            private AudioInfo audioInfo;
            
            /**
             * The metadata specific to subtitle streams.
             */
            private SubtitleInfo subtitleInfo;
            
            /**
             * The metadata specific to data streams.
             */
            private DataInfo dataInfo;
            
            /**
             * The disposition of the stream.
             */
            private Disposition disposition;
            
            
            //Constructors
            
            /**
             * Creates a new Stream from json metadata.
             *
             * @param data The stream metadata in json.
             */
            public Stream(JSONObject data) {
                super(data);
                
                this.streamType = data.containsKey("codec_type") ? StreamType.valueOf(((String) data.get("codec_type")).toUpperCase()) : null;
                this.streamIndex = data.containsKey("index") ? (int) (long) data.get("index") : -1;
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
                
                this.videoInfo = new VideoInfo(data);
                this.audioInfo = new AudioInfo(data);
                this.subtitleInfo = new SubtitleInfo(data);
                this.dataInfo = new DataInfo(data);
                this.disposition = new Disposition(data);
            }
            
            
            //Methods
            
            /**
             * Returns a string representation of the Stream.
             *
             * @return A string representation of the Stream.
             */
            @Override
            public String toString() {
                return "Stream #" + streamIndex + ": " +
                        ((streamType != null) ? StringUtility.toTitleCase(streamType.name().toLowerCase()) : "UNKNOWN") +
                        ((getLanguage() != null) ? (" (" + getLanguage() + ")") : "") + ": " +
                        codecName +
                        ((getTitle() == null) ? "" : (": " + StringUtility.quote(getTitle())));
            }
            
            
            //Getters
            
            /**
             * Returns the type of the stream.
             *
             * @return The type of the stream.
             */
            public StreamType getStreamType() {
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
             * @see Disposition#has(Disposition.StreamDisposition)
             */
            public boolean isDefaultStream() {
                return disposition.has(Disposition.StreamDisposition.DEFAULT);
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
             * Returns the metadata specific to video streams.
             *
             * @return The metadata specific to video streams.
             */
            public VideoInfo getVideoInfo() {
                return videoInfo;
            }
            
            /**
             * Returns the metadata specific to audio streams.
             *
             * @return The metadata specific to audio streams.
             */
            public AudioInfo getAudioInfo() {
                return audioInfo;
            }
            
            /**
             * Returns the metadata specific to subtitle streams.
             *
             * @return The metadata specific to subtitle streams.
             */
            public SubtitleInfo getSubtitleInfo() {
                return subtitleInfo;
            }
            
            /**
             * Returns the metadata specific to data streams.
             *
             * @return The metadata specific to data streams.
             */
            public DataInfo getDataInfo() {
                return dataInfo;
            }
            
            /**
             * Returns the disposition of the stream.
             *
             * @return The disposition of the stream.
             */
            public Disposition getDisposition() {
                return disposition;
            }
            
            
            //Inner Classes
            
            /**
             * Defines the metadata of a video stream.
             */
            public static class VideoInfo {
                
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
                 * Creates a new VideoInfo from json metadata.
                 *
                 * @param data The stream metadata in json.
                 */
                public VideoInfo(JSONObject data) {
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
            public static class AudioInfo {
                
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
                 * Creates a new AudioInfo from json metadata.
                 *
                 * @param data The stream metadata in json.
                 */
                public AudioInfo(JSONObject data) {
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
            public static class SubtitleInfo {
                
                //Constructors
                
                /**
                 * Creates a new SubtitleInfo from json metadata.
                 *
                 * @param data The stream metadata in json.
                 */
                @SuppressWarnings("EmptyMethod")
                public SubtitleInfo(JSONObject data) {
                }
                
            }
            
            /**
             * Defines the metadata of a data stream.
             */
            public static class DataInfo {
                
                //Constructors
                
                /**
                 * Creates a new DataInfo from json metadata.
                 *
                 * @param data The stream metadata in json.
                 */
                @SuppressWarnings("EmptyMethod")
                public DataInfo(JSONObject data) {
                }
                
            }
            
            /**
             * Defines the disposition of the stream.
             */
            public static class Disposition {
                
                //Enums
                
                /**
                 * An enumeration of Stream Dispositions.
                 */
                public enum StreamDisposition {
                    
                    //Values
                    
                    DEFAULT("default"),
                    DUB("dub"),
                    ORIGINAL("original"),
                    COMMENT("comment"),
                    LYRICS("lyrics"),
                    KARAOKE("karaoke"),
                    FORCED("forced"),
                    HEARING_IMPAIRED("hearing_impaired"),
                    VISUAL_IMPAIRED("visual_impaired"),
                    CLEAN_EFFECTS("clean_effects"),
                    ATTACHED_PIC("attached_pic"),
                    TIMED_THUMBNAILS("timed_thumbnails");
                    
                    
                    //Fields
                    
                    /**
                     * The key of the Stream Disposition.
                     */
                    private final String key;
                    
                    
                    //Constructors
                    
                    /**
                     * Constructs a Stream Disposition.
                     *
                     * @param key The key of the Stream Disposition.
                     */
                    StreamDisposition(String key) {
                        this.key = key;
                    }
                    
                    
                    //Getters
                    
                    /**
                     * Returns the key of the Stream Disposition.
                     *
                     * @return The key of the Stream Disposition.
                     */
                    public String getKey() {
                        return key;
                    }
                    
                }
                
                
                //Fields
                
                /**
                 * The dispositions.
                 */
                private List<StreamDisposition> dispositions;
                
                
                //Constructors
                
                /**
                 * Constructs a new Disposition from json metadata.
                 *
                 * @param data The stream metadata in json.
                 */
                @SuppressWarnings("unchecked")
                public Disposition(JSONObject data) {
                    this.dispositions = new ArrayList<>();
                    if (data.containsKey("disposition")) {
                        for (Map.Entry<String, Object> dispositionEntry : (Set<Map.Entry<String, Object>>) ((JSONObject) data.get("disposition")).entrySet()) {
                            if (((int) (long) dispositionEntry.getValue()) != 0) {
                                Arrays.stream(StreamDisposition.values()).filter(e -> e.getKey()
                                                .equalsIgnoreCase(dispositionEntry.getKey())).findFirst()
                                        .ifPresent(disposition -> this.dispositions.add(disposition));
                            }
                        }
                    }
                }
                
                
                //Methods
                
                /**
                 * Returns whether the Disposition has a disposition.
                 *
                 * @param disposition The disposition.
                 * @return Whether the Disposition has the disposition of the specified name.
                 */
                public boolean has(StreamDisposition disposition) {
                    return dispositions.contains(disposition);
                }
                
                /**
                 * Returns a string representation of the Disposition.
                 *
                 * @return A string representation of the Disposition.
                 */
                @Override
                public String toString() {
                    return Arrays.stream(StreamDisposition.values()).filter(e -> dispositions.contains(e))
                            .map(StreamDisposition::getKey).collect(Collectors.joining("|"));
                }
                
                
                //Getters
                
                /**
                 * Returns the list of dispositions.
                 *
                 * @return The list of dispositions.
                 */
                public List<StreamDisposition> getAll() {
                    return dispositions;
                }
                
            }
            
        }
        
        /**
         * Defines the metadata of a chapter.
         */
        public static class Chapter extends MetadataBase {
            
            //Fields
            
            /**
             * The chapter id.
             */
            private long chapterId;
            
            
            //Constructors
            
            /**
             * Creates a new Chapter from json metadata.
             *
             * @param data The chapter metadata in json.
             */
            public Chapter(JSONObject data) {
                super(data);
                
                this.chapterId = data.containsKey("id") ? (long) data.get("id") : 0;
            }
            
            
            //Methods
            
            /**
             * Returns a string representation of the Chapter.
             *
             * @return A string representation of the Chapter.
             */
            @Override
            public String toString() {
                return "Chapter #" + chapterId + ": " +
                        "(" + getStartTimestamp() + " --> " + getEndTimestamp() + ")" +
                        ((getTitle() == null) ? "" : (": " + StringUtility.quote(getTitle())));
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
            
            
            //Inner Classes
            
            /**
             * Holds FFMETADATA chapter information.
             */
            public static class ChapterDTO {
                
                //Fields
                
                /**
                 * The start time of the chapter, in milliseconds.
                 */
                public final long startTime;
                
                /**
                 * The end time of the chapter, in milliseconds.
                 */
                public final long endTime;
                
                /**
                 * The title of the chapter.
                 */
                public final String title;
                
                
                //Constructors
                
                /**
                 * Creates a ChapterDTO.
                 *
                 * @param startTime The start time of the chapter, in milliseconds.
                 * @param endTime   The end time of the chapter, in milliseconds.
                 * @param title     The title of the chapter.
                 */
                public ChapterDTO(long startTime, long endTime, String title) {
                    this.startTime = startTime;
                    this.endTime = endTime;
                    this.title = title;
                }
                
                /**
                 * Creates a ChapterDTO.
                 *
                 * @param startTimestamp The start timestamp of the chapter, in the format 'HH:mm:ss.SSS'.
                 * @param endTimestamp   The end timestamp of the chapter, in the format 'HH:mm:ss.SSS'.
                 * @param title          The title of the chapter.
                 * @see #ChapterDTO(long, long, String)
                 */
                public ChapterDTO(String startTimestamp, String endTimestamp, String title) {
                    this(DateTimeUtility.durationStampToDuration(startTimestamp),
                            DateTimeUtility.durationStampToDuration(endTimestamp), title);
                }
                
                /**
                 * Creates a ChapterDTO.
                 *
                 * @param chapter A Chapter metadata.
                 * @see #ChapterDTO(long, long, String)
                 */
                public ChapterDTO(Chapter chapter) {
                    this(chapter.getStartTime(), chapter.getEndTime(), chapter.getTitle());
                }
                
                
                //Methods
                
                /**
                 * Converts the ChapterDTO to an FFMETADATA chapter string.
                 *
                 * @return The FFMETADATA chapter string.
                 */
                public String toFFmetadataChapterString() {
                    return java.util.stream.Stream.of(
                                    "[CHAPTER]", "TIMEBASE=1/1000",
                                    ("START=" + startTime), ("END=" + endTime),
                                    ((title != null) ? ("title=" + title.replaceAll("\\\\?([\n=;#\\\\])", "\\\\$0")) : ""))
                            .filter(e -> !e.isEmpty()).collect(Collectors.joining("\n"));
                }
                
                
                //Static Methods
                
                /**
                 * Generates the contents of an FFMETADATA file from a list of Chapter DTOs.
                 *
                 * @param chapters The list of Chapter DTOs.
                 * @return The FFMETADATA file contents.
                 */
                public static String generateFFmetadataFileContents(List<ChapterDTO> chapters) {
                    return chapters.stream()
                            .map(MediaInfo.Chapter.ChapterDTO::toFFmetadataChapterString)
                            .collect(Collectors.joining("\n", ";FFMETADATA1\n\n", ""));
                }
                
                /**
                 * Generates an FFMETADATA file from a list of Chapter DTOs.
                 *
                 * @param chapters       The list of Chapter DTOs.
                 * @param ffmetadataFile The FFMETADATA output file.
                 * @return The FFMETADATA output file.
                 */
                public static File generateFFmetadataFile(List<ChapterDTO> chapters, File ffmetadataFile) {
                    ffmetadataFile = (ffmetadataFile != null) ? ffmetadataFile :
                                     new File(Filesystem.createTemporaryDirectory(), "FFMETADATA");
                    Filesystem.writeStringToFile(ffmetadataFile, generateFFmetadataFileContents(chapters));
                    return ffmetadataFile;
                }
                
                /**
                 * Generates an FFMETADATA file from a list of Chapter DTOs.
                 *
                 * @param chapters The list of Chapter DTOs.
                 * @return The FFMETADATA output file.
                 * @see #generateFFmetadataFile(List, File)
                 */
                public static File generateFFmetadataFile(List<ChapterDTO> chapters) {
                    return generateFFmetadataFile(chapters, null);
                }
                
            }
            
        }
        
    }
    
    /**
     * Defines an entity identifier for a media file.
     *
     * @param <E> The scope of the identifier.
     */
    public abstract static class Identifier<E extends Identifier.Scope> implements Comparable<Identifier<?>> {
        
        //Constants
        
        /**
         * The regex pattern for an entity specifier.
         */
        public static final Pattern SPECIFIER_PATTERN = Pattern.compile("(?i)^(?:(?<sourceIndex>\\d+):)?" +
                Arrays.stream(IdentifierType.values()).map(e ->
                                List.of(StringUtility.lSnip(e.name(), 1), StringUtility.lSnip(e.keys, 1)))
                        .flatMap(List::stream).map(String::toLowerCase).distinct()
                        .collect(Collectors.joining("", "(?:(?<type>[", "]):?)?")) +
                StringUtility.stringStream(StringUtility.lShear(IdentifierType.STREAM.keys, 1).toLowerCase()).distinct()
                        .collect(Collectors.joining("", "(?:(?<streamType>[", "]):?)?")) +
                "(?<index>\\d+)?$");
        
        
        //Enums
        
        /**
         * An enumeration of Identifier Scopes.
         */
        public enum IdentifierScope {
            
            //Values
            
            SINGULAR,
            TYPE,
            ALL;
            
            
            //Static Methods
            
            /**
             * Determines the Identifier Scope for an identifier.
             *
             * @param scope The scope of the identifier.
             * @return The Identifier Scope of the identifier, or null if the Identifier Scope could not be determined.
             */
            private static IdentifierScope getScope(Class<? extends Scope> scope) {
                try {
                    return valueOf(EntityStringUtility.simpleClassString(scope).toUpperCase());
                } catch (Exception ignored) {
                    return null;
                }
            }
            
        }
        
        /**
         * An enumeration of Identifier Types.
         */
        public enum IdentifierType {
            
            //Values
            
            GLOBAL("g"),
            STREAM("x" + Arrays.stream(StreamType.values())
                    .map(e -> StringUtility.lSnip(e.name(), 1).toLowerCase()).collect(Collectors.joining())),
            CHAPTER("c");
            
            
            //Fields
            
            /**
             * The available keys for the Identifier Type.
             */
            private final String keys;
            
            
            //Constructors
            
            /**
             * Constructs an Identifier Type.
             *
             * @param keys The available keys for the Identifier Type.
             */
            IdentifierType(String keys) {
                this.keys = keys;
            }
            
            
            //Static Methods
            
            /**
             * Determines the Identifier Type for an identifier subclass.
             *
             * @param clazz The class of the identifier.
             * @return The Identifier Type of the identifier subclass, or null if the Identifier Type could not be determined.
             */
            @SuppressWarnings("rawtypes")
            private static IdentifierType getType(Class<? extends Identifier> clazz) {
                try {
                    return valueOf(EntityStringUtility.simpleClassString(clazz).toUpperCase());
                } catch (Exception ignored) {
                    return null;
                }
            }
            
        }
        
        
        //Fields
        
        /**
         * The scope of the identifier.
         */
        private final IdentifierScope scope;
        
        /**
         * The type of the identifier.
         */
        private final IdentifierType type;
        
        /**
         * The stream type of the entity.
         */
        protected StreamType streamType = null;
        
        /**
         * The index of the entity.
         */
        protected Integer index = null;
        
        /**
         * The index of the input source of the entity; this may not be honored by some methods.
         */
        protected Integer sourceIndex = 0;
        
        
        //Constructors
        
        /**
         * Creates a new entity identifier; identifying by the specified entity specifier.
         *
         * @param specifier The entity specifier; for example: 'v:2', 'a:1', 's', ''x', 0:v:3', '1:a', 'c:2', 'c', '1:c:1', '1:c', 'g', '1:g'.
         * @return The entity identifier, or null if the specified entity specifier is invalid.
         * @see Stream#of(StreamType, int)
         * @see Stream#of(StreamType)
         * @see Stream#all()
         * @see Chapter#ofIndex(int)
         * @see Chapter#all()
         * @see Global#get()
         */
        public static Identifier<?> of(String specifier) {
            if (StringUtility.isNullOrEmpty(specifier)) {
                return null;
            }
            
            final Matcher specifierMatcher = SPECIFIER_PATTERN.matcher(specifier);
            if (specifierMatcher.matches()) {
                final String typeKey = specifierMatcher.group("type");
                final IdentifierType type = Optional.ofNullable(typeKey)
                        .flatMap(e -> Arrays.stream(IdentifierType.values())
                                .filter(t -> (StringUtility.lSnip(t.keys, 1).equalsIgnoreCase(e) || StringUtility.lSnip(t.name(), 1).equalsIgnoreCase(e)))
                                .findFirst()).orElse(IdentifierType.STREAM);
                final Integer index = Optional.ofNullable(specifierMatcher.group("index"))
                        .map(Integer::parseInt).orElse(null);
                
                Identifier<?> identifier;
                switch (type) {
                    case STREAM:
                        final StreamType streamType = Optional.ofNullable(specifierMatcher.group("streamType"))
                                .flatMap(e -> Arrays.stream(StreamType.values())
                                        .filter(t -> StringUtility.lSnip(t.name(), 1).equalsIgnoreCase(e))
                                        .findFirst()).orElse(StringUtility.lSnip(IdentifierType.STREAM.name(), 1).equalsIgnoreCase(typeKey) ?
                                                             StreamType.SUBTITLE : null);
                        identifier = ((index == null) && (streamType == null)) ? Stream.all() :
                                     (streamType == null) ? Stream.ofIndex(index) :
                                     (index == null) ? Stream.of(streamType) : Stream.of(streamType, index);
                        break;
                    case CHAPTER:
                        identifier = (index == null) ? Chapter.all() : Chapter.ofIndex(index);
                        break;
                    case GLOBAL:
                        identifier = Global.get();
                        break;
                    default:
                        return null;
                }
                
                final Integer sourceIndex = Optional.ofNullable(specifierMatcher.group("sourceIndex"))
                        .map(Integer::parseInt).orElse(null);
                identifier.sourceIndex = (sourceIndex != null) ? sourceIndex : identifier.sourceIndex;
                
                return identifier;
            }
            return null;
        }
        
        /**
         * Initializes a new identifier.
         *
         * @param scope The scope of the identifier.
         * @param clazz The class of the identifier.
         */
        @SuppressWarnings("rawtypes")
        private Identifier(Class<? extends Scope> scope, Class<? extends Identifier> clazz) {
            this.scope = IdentifierScope.getScope(scope);
            this.type = IdentifierType.getType(clazz);
        }
        
        
        //Methods
        
        /**
         * Returns the specifier of the entity identifier.
         *
         * @return The specifier of the entity identifier.
         */
        public String specifier() {
            return java.util.stream.Stream.of(
                    Optional.ofNullable(streamType).map(e -> StringUtility.lSnip(e.name().toLowerCase(), 1)).orElse(null),
                    Optional.ofNullable(index).map(Object::toString).orElse(null)
            ).filter(e -> !StringUtility.isNullOrEmpty(e)).collect(Collectors.joining(":"));
        }
        
        /**
         * Returns the class qualified specifier of the entity identifier.
         *
         * @return The class qualified specifier of the entity identifier.
         * @see #specifier()
         */
        public String classSpecifier() {
            return java.util.stream.Stream.of(
                    Optional.ofNullable(type).map(e -> StringUtility.lSnip(e.name().toLowerCase(), 1)).orElse(null),
                    specifier()
            ).filter(e -> !StringUtility.isNullOrEmpty(e)).collect(Collectors.joining(":"));
        }
        
        /**
         * Returns the input source qualified specifier of the entity identifier.
         *
         * @return The input source qualified specifier of the entity identifier.
         * @see #classSpecifier()
         */
        public String fullSpecifier() {
            return java.util.stream.Stream.of(
                    Optional.ofNullable(sourceIndex).map(Object::toString).orElse(null),
                    classSpecifier()
            ).filter(e -> !StringUtility.isNullOrEmpty(e)).collect(Collectors.joining(":"));
        }
        
        /**
         * Returns the string representation of the entity identifier.
         *
         * @return The string representation of the entity identifier.
         * @see #fullSpecifier()
         */
        @Override
        public String toString() {
            return fullSpecifier();
        }
        
        /**
         * Compares two Identifiers for sorting.
         *
         * @param other The other Identifier.
         * @return -1 if this Identifier should be placed before the other Identifier, 1 if this Identifier should be placed after the other Identifier, or 0 if the two Identifiers are equal.
         */
        @Override
        public int compareTo(Identifier<?> other) {
            return ((getSourceIndex() != null) && (other.getSourceIndex() != null) && (!getSourceIndex().equals(other.getSourceIndex()))) ? Integer.compare(getSourceIndex(), other.getSourceIndex()) :
                   ((getSourceIndex() != null) && (other.getSourceIndex() == null)) ? 1 :
                   ((getSourceIndex() == null) && (other.getSourceIndex() != null)) ? -1 :
                   ((getType() != null) && (other.getType() != null) && (getType().ordinal() != other.getType().ordinal())) ? Integer.compare(getType().ordinal(), other.getType().ordinal()) :
                   ((getType() != null) && (other.getType() == null)) ? -1 :
                   ((getType() == null) && (other.getType() != null)) ? 1 :
                   ((getStreamType() != null) && (other.getStreamType() != null) && (getStreamType() != other.getStreamType())) ? Integer.compare(getStreamType().ordinal(), other.getStreamType().ordinal()) :
                   ((getStreamType() != null) && (other.getStreamType() == null)) ? ((getType() == IdentifierType.STREAM) ? 1 : -1) :
                   ((getStreamType() == null) && (other.getStreamType() != null)) ? ((getType() == IdentifierType.STREAM) ? -1 : 1) :
                   ((getIndex() != null) && (other.getIndex() != null) && (!getIndex().equals(other.getIndex()))) ? Integer.compare(getIndex(), other.getIndex()) :
                   ((getIndex() != null) && (other.getIndex() == null)) ? 1 :
                   ((getIndex() == null) && (other.getIndex() != null)) ? -1 : 0;
        }
        
        /**
         * Determines if two Identifiers are equal.
         *
         * @param o The other Identifier.
         * @return Whether the Identifiers are equal or not.
         */
        @Override
        public boolean equals(Object o) {
            if ((o == null) || !Identifier.class.isAssignableFrom(o.getClass())) {
                return false;
            }
            Identifier<?> other = (Identifier<?>) o;
            
            return fullSpecifier().equals(other.fullSpecifier());
        }
        
        /**
         * Returns the hash code of an Identifier.
         *
         * @return The hash code of an Identifier.
         */
        @Override
        public int hashCode() {
            return Objects.hash(fullSpecifier());
        }
        
        
        //Getters
        
        /**
         * Returns the scope of the identifier.
         *
         * @return The scope of the identifier.
         */
        public final IdentifierScope getScope() {
            return scope;
        }
        
        /**
         * Returns the type of the identifier.
         *
         * @return The type of the identifier.
         */
        public final IdentifierType getType() {
            return type;
        }
        
        /**
         * Returns the stream type of the entity.
         *
         * @return The stream type of the entity.
         */
        public StreamType getStreamType() {
            return streamType;
        }
        
        /**
         * Returns the index of the entity.
         *
         * @return The index of the entity.
         */
        public Integer getIndex() {
            return index;
        }
        
        /**
         * Returns the index of the input source of the entity.
         *
         * @return The index of the input source of the entity.
         */
        public final Integer getSourceIndex() {
            return sourceIndex;
        }
        
        /**
         * Returns whether the identifier is a global identifier or not.
         *
         * @return Whether the identifier is a global identifier or not.
         */
        public final boolean isGlobalId() {
            return (type == IdentifierType.GLOBAL);
        }
        
        /**
         * Returns whether the identifier is a stream identifier or not.
         *
         * @return Whether the identifier is a stream identifier or not.
         */
        public final boolean isStreamId() {
            return (type == IdentifierType.STREAM);
        }
        
        /**
         * Returns whether the identifier is a chapter identifier or not.
         *
         * @return Whether the identifier is a chapter identifier or not.
         */
        public final boolean isChapterId() {
            return (type == IdentifierType.CHAPTER);
        }
        
        /**
         * Returns whether the identifier specifies a singular entity or not.
         *
         * @return Whether the identifier specifies a singular entity or not.
         */
        public final boolean isSingularScoped() {
            return (scope == IdentifierScope.SINGULAR);
        }
        
        /**
         * Returns whether the identifier specifies a type of entity or not.
         *
         * @return Whether the identifier specifies a type of entity or not.
         */
        public final boolean isTypeScoped() {
            return (scope == IdentifierScope.TYPE);
        }
        
        /**
         * Returns whether the identifier specifies every entity within its identifier class or not.
         *
         * @return Whether the identifier specifies every entity within its identifier class or not.
         */
        public final boolean isAllScoped() {
            return (scope == IdentifierScope.ALL);
        }
        
        
        //Static Methods
        
        /**
         * Decomposes a list of identifiers into a list of valid singular identifiers for a specific media.
         *
         * @param identifiers  The list of identifiers.
         * @param mappedValues The list of values mapped to the identifiers.
         * @param mediaInfo    The media metadata.
         * @param <T>          The type of the values mapped to the identifiers.
         * @return The map of valid singular identifiers and their mapped values for the specified media.
         */
        public static <T> Map<Identifier<Scope.Singular>, T> decompose(List<Identifier<?>> identifiers, List<T> mappedValues, MediaInfo mediaInfo) {
            final Function<Identifier<?>, List<Identifier<Scope.Singular>>> decomposer = id -> {
                switch (id.getScope().name() + ':' + id.getType().name()) {
                    case "ALL:STREAM":
                        return IntStream.range(0, mediaInfo.getStreams().size()).boxed().map(Stream::ofIndex).collect(Collectors.toList());
                    case "ALL:CHAPTER":
                        return IntStream.range(0, mediaInfo.getChapters().size()).boxed().map(Chapter::ofIndex).collect(Collectors.toList());
                    case "TYPE:STREAM":
                        return mediaInfo.getStreams().stream().filter(e -> (e.getStreamType() == id.getStreamType()))
                                .map(MediaInfo.Stream::getStreamIndex).map(Stream::ofIndex).collect(Collectors.toList());
                    case "SINGULAR:STREAM":
                        return Collections.singletonList(Optional.of(mediaInfo.getStreams().stream()
                                        .filter(e -> ((id.getStreamType() == null) || (e.getStreamType() == id.getStreamType())))
                                        .map(MediaInfo.Stream::getStreamIndex).toArray(Integer[]::new))
                                .filter(e -> BoundUtility.inArrayBounds(id.getIndex(), e))
                                .map(e -> Stream.ofIndex(e[id.getIndex()])).orElse(null));
                    case "SINGULAR:CHAPTER":
                        return Collections.singletonList(BoundUtility.inListBounds(id.getIndex(), mediaInfo.getChapters()) ?
                                                         Chapter.ofIndex(id.getIndex()) : null);
                    case "SINGULAR:GLOBAL":
                        return Collections.singletonList(Global.get());
                    default:
                        return Collections.emptyList();
                }
            };
            
            return IntStream.range(0, identifiers.size()).boxed()
                    .map(i -> decomposer.apply(identifiers.get(i)).stream().filter(Objects::nonNull)
                            .collect(MapCollectors.mapEachTo(ListUtility.getOrNull(mappedValues, i)))
                    ).flatMap(e -> e.entrySet().stream()).sorted(Map.Entry.comparingByKey())
                    .collect(MapCollectors.toLinkedHashMap());
        }
        
        /**
         * Decomposes a list of identifiers into a list of valid singular identifiers for a specific media.
         *
         * @param identifiers  The list of identifiers.
         * @param mappedValues The list of values mapped to the identifiers.
         * @param mediaFile    The media file.
         * @param <T>          The type of the values mapped to the identifiers.
         * @return The map of valid singular identifiers and their mapped values for the specified media.
         * @see #decompose(List, List, MediaInfo)
         */
        public static <T> Map<Identifier<Scope.Singular>, T> decompose(List<Identifier<?>> identifiers, List<T> mappedValues, File mediaFile) {
            final MediaInfo mediaInfo = (mediaFile != null) ? getMediaInfo(mediaFile) : null;
            if (mediaInfo == null) {
                return Collections.emptyMap();
            }
            return decompose(identifiers, mappedValues, mediaInfo);
        }
        
        /**
         * Decomposes a list of identifiers into a list of valid singular identifiers for a specific media.
         *
         * @param identifiers The list of identifiers.
         * @param mediaInfo   The media metadata.
         * @return The list of valid singular identifiers for the specified media.
         * @see #decompose(List, List, MediaInfo)
         */
        public static List<Identifier<Scope.Singular>> decompose(List<Identifier<?>> identifiers, MediaInfo mediaInfo) {
            return Optional.ofNullable(decompose(identifiers, null, mediaInfo)).map(Map::keySet)
                    .map(ListUtility::toList).orElse(ListUtility.emptyList());
        }
        
        /**
         * Decomposes a list of identifiers into a list of valid singular identifiers for a specific media.
         *
         * @param identifiers The list of identifiers.
         * @param mediaFile   The media file.
         * @return The list of valid singular identifiers for the specified media.
         * @see #decompose(List, List, File)
         */
        public static List<Identifier<Scope.Singular>> decompose(List<Identifier<?>> identifiers, File mediaFile) {
            return Optional.ofNullable(decompose(identifiers, null, mediaFile)).map(Map::keySet)
                    .map(ListUtility::toList).orElse(ListUtility.emptyList());
        }
        
        /**
         * Decomposes an identifier into a list of valid singular identifiers for a specific media.
         *
         * @param identifier The identifier.
         * @param mediaInfo  The media metadata.
         * @return The list of valid singular identifiers for the specified media.
         * @see #decompose(List, MediaInfo)
         */
        public static List<Identifier<Scope.Singular>> decompose(Identifier<?> identifier, MediaInfo mediaInfo) {
            return decompose(Collections.singletonList(identifier), mediaInfo);
        }
        
        /**
         * Decomposes an identifier into a list of valid singular identifiers for a specific media.
         *
         * @param identifier The identifier.
         * @param mediaFile  The media file.
         * @return The list of valid singular identifiers for the specified media.
         * @see #decompose(List, File)
         */
        public static List<Identifier<Scope.Singular>> decompose(Identifier<?> identifier, File mediaFile) {
            return decompose(Collections.singletonList(identifier), mediaFile);
        }
        
        
        //Inner Classes
        
        /**
         * Defines an Identifier Scope.
         */
        public interface Scope {
            
            //Inner Classes
            
            /**
             * Claims that an identifier specifies a singular entity.
             */
            interface Singular extends Scope {
                
            }
            
            /**
             * Claims that an identifier specifies a type of entity.
             */
            interface Type extends Scope {
                
            }
            
            /**
             * Claims that an identifier specifies every entity within its identifier class.
             */
            interface All extends Scope {
                
            }
            
        }
        
        /**
         * Defines a global identifier for a media file.
         */
        public static class Global extends Identifier<Scope.Singular> {
            
            //Constructors
            
            /**
             * Creates a new global identifier.
             *
             * @return The global identifier.
             * @see #Global()
             */
            public static Global get() {
                return new Global();
            }
            
            /**
             * Creates a new global identifier; identifying by the specified global specifier.
             *
             * @param specifier The global specifier; for example: 'g', '1:g'.
             * @return The global identifier, or null if the specified global specifier is invalid.
             * @see Identifier#of(String)
             */
            public static Global of(String specifier) {
                return (Global) Identifier.of(specifier);
            }
            
            /**
             * Creates a new global identifier.
             */
            private Global() {
                super(Scope.Singular.class, Global.class);
            }
            
        }
        
        /**
         * Defines a stream identifier for a media file.
         *
         * @param <E> The scope of the stream identifier.
         */
        public static class Stream<E extends Scope> extends Identifier<E> {
            
            //Constructors
            
            /**
             * Creates a new stream identifier; identifying the stream of the specified index of the specified stream type.
             *
             * @param type  The type of the stream; or null to identify by stream index.
             * @param index The index of the stream of the specified stream type; or the stream index if the stream type is null.
             * @return The stream identifier.
             * @see StreamSpecifier#StreamSpecifier(StreamType, int)
             */
            public static StreamSpecifier of(StreamType type, int index) {
                return new StreamSpecifier(type, index);
            }
            
            /**
             * Creates a new stream identifier; identifying the first stream of the specified stream type.
             *
             * @param type The type of the stream.
             * @return The stream identifier.
             * @see StreamSpecifier#StreamSpecifier(StreamType)
             */
            public static StreamSpecifier ofFirst(StreamType type) {
                return new StreamSpecifier(type);
            }
            
            /**
             * Creates a new stream identifier; identifying the stream of the specified index.
             *
             * @param index The index of the stream.
             * @return The stream identifier.
             * @see StreamSpecifier#StreamSpecifier(int)
             */
            public static StreamSpecifier ofIndex(int index) {
                return new StreamSpecifier(index);
            }
            
            /**
             * Creates a new stream identifier; identifying the specified stream type.
             *
             * @param type The type of the stream.
             * @return The stream identifier.
             * @throws NullPointerException If the specified type is null.
             * @see StreamTypeSpecifier#StreamTypeSpecifier(StreamType)
             */
            public static StreamTypeSpecifier of(StreamType type) {
                return new StreamTypeSpecifier(type);
            }
            
            /**
             * Creates a new stream identifier; identifying all streams.
             *
             * @return The stream identifier.
             * @see AllStreams#AllStreams()
             */
            public static AllStreams all() {
                return new AllStreams();
            }
            
            /**
             * Creates a new stream identifier; identifying by the specified stream specifier.
             *
             * @param specifier The stream specifier; for example: 'v:2', 'a:1', 's', ''x', 0:v:3', '1:a'.
             * @return The stream identifier, or null if the specified stream specifier is invalid.
             * @see Identifier#of(String)
             */
            public static Stream<?> of(String specifier) {
                return (Stream<?>) Identifier.of(specifier);
            }
            
            /**
             * Initializes a new stream identifier.
             *
             * @param scope The scope of the stream identifier.
             */
            private Stream(Class<? extends Scope> scope) {
                super(scope, Stream.class);
            }
            
            
            //Inner Classes
            
            /**
             * Identifies a stream in a media file.
             */
            public static class StreamSpecifier extends Stream<Scope.Singular> {
                
                //Constructors
                
                /**
                 * Creates a new stream identifier.
                 *
                 * @param streamType The type of the stream; or null to identify by stream index.
                 * @param index      The index of the stream of the specified stream type; or the stream index if the stream type is null.
                 */
                private StreamSpecifier(StreamType streamType, int index) {
                    super(Scope.Singular.class);
                    
                    this.streamType = streamType;
                    this.index = index;
                }
                
                /**
                 * Creates a new stream identifier.
                 *
                 * @param streamType The type of the stream.
                 * @see #StreamSpecifier(StreamType, int)
                 */
                private StreamSpecifier(StreamType streamType) {
                    this(streamType, 0);
                }
                
                /**
                 * Creates a new stream identifier.
                 *
                 * @param index The index of the stream.
                 * @see #StreamSpecifier(StreamType, int)
                 */
                private StreamSpecifier(int index) {
                    this(null, index);
                }
                
            }
            
            /**
             * Identifies a stream type in a media file.
             */
            public static class StreamTypeSpecifier extends Stream<Scope.Type> {
                
                //Constructors
                
                /**
                 * Creates a new stream type identifier.
                 *
                 * @param streamType The type of the stream.
                 * @throws NullPointerException If the specified type is null.
                 */
                private StreamTypeSpecifier(StreamType streamType) {
                    super(Scope.Type.class);
                    
                    if (streamType == null) {
                        throw new NullPointerException("Invalid Stream Type for Stream Type Specifier: null");
                    }
                    
                    this.streamType = streamType;
                    this.index = null;
                }
                
            }
            
            /**
             * Identifies all streams in a media file.
             */
            public static class AllStreams extends Stream<Scope.All> {
                
                //Constructors
                
                /**
                 * Creates a new all streams identifier.
                 */
                private AllStreams() {
                    super(Scope.All.class);
                }
                
            }
            
        }
        
        /**
         * Defines a chapter identifier for a media file.
         *
         * @param <E> The scope of the chapter identifier.
         */
        public static class Chapter<E extends Scope> extends Identifier<E> {
            
            //Constructors
            
            /**
             * Creates a new chapter identifier; identifying the chapter of the specified index.
             *
             * @param index The index of the chapter.
             * @return The chapter identifier.
             * @see ChapterSpecifier#ChapterSpecifier(int)
             */
            public static ChapterSpecifier ofIndex(int index) {
                return new ChapterSpecifier(index);
            }
            
            /**
             * Creates a new chapter identifier; identifying all chapters.
             *
             * @return The chapter identifier.
             * @see AllChapters#AllChapters()
             */
            public static AllChapters all() {
                return new AllChapters();
            }
            
            /**
             * Creates a new chapter identifier; identifying by the specified chapter specifier.
             *
             * @param specifier The chapter specifier; for example: 'c:2', 'c', '1:c:1', '1:c'.
             * @return The chapter identifier, or null if the specified chapter specifier is invalid.
             * @see Identifier#of(String)
             */
            public static Chapter<?> of(String specifier) {
                return (Chapter<?>) Identifier.of(specifier);
            }
            
            /**
             * Initializes a new chapter identifier.
             *
             * @param scope The scope of the chapter identifier.
             */
            private Chapter(Class<? extends Scope> scope) {
                super(scope, Chapter.class);
            }
            
            
            //Inner Classes
            
            /**
             * Identifies a chapter in a media file.
             */
            public static class ChapterSpecifier extends Chapter<Scope.Singular> {
                
                //Constructors
                
                /**
                 * Creates a new chapter identifier.
                 *
                 * @param chapterIndex The index of the chapter.
                 */
                private ChapterSpecifier(int chapterIndex) {
                    super(Scope.Singular.class);
                    
                    this.index = chapterIndex;
                }
                
            }
            
            /**
             * Identifies all chapters in a media file.
             */
            public static class AllChapters extends Chapter<Scope.All> {
                
                //Constructors
                
                /**
                 * Creates a new all chapters identifier.
                 */
                private AllChapters() {
                    super(Scope.All.class);
                }
                
            }
            
        }
        
    }
    
    /**
     * Holds the lists of implements available to and supported by ffmpeg.
     */
    public static class Implements {
        
        //Static Fields
        
        /**
         * The list of formats supported by ffmpeg.
         */
        private static List<Format> formats;
        
        /**
         * The list of demuxers supported by ffmpeg.
         */
        private static List<Demuxer> demuxers;
        
        /**
         * The list of muxers supported by ffmpeg.
         */
        private static List<Muxer> muxers;
        
        /**
         * The list of devices supported by ffmpeg.
         */
        private static List<Device> devices;
        
        /**
         * The list of codecs supported by ffmpeg.
         */
        private static List<Codec> codecs;
        
        /**
         * The list of decoders supported by ffmpeg.
         */
        private static List<Decoder> decoders;
        
        /**
         * The list of encoders supported by ffmpeg.
         */
        private static List<Encoder> encoders;
        
        /**
         * The list of bitstream filters supported by ffmpeg.
         */
        private static List<BitstreamFilter> bitstreamFilters;
        
        /**
         * The list of protocols supported by ffmpeg.
         */
        private static List<Protocol> protocols;
        
        /**
         * The list of libavfilter filters supported by ffmpeg.
         */
        private static List<Filter> filters;
        
        /**
         * The list of pixel formats supported by ffmpeg.
         */
        private static List<PixelFormat> pixelFormats;
        
        /**
         * The list of sample formats supported by ffmpeg.
         */
        private static List<SampleFormat> sampleFormats;
        
        /**
         * The list of channels supported by ffmpeg.
         */
        private static List<Channel> channels;
        
        /**
         * The list of channel layouts supported by ffmpeg.
         */
        private static List<ChannelLayout> channelLayouts;
        
        /**
         * The list of colors supported by ffmpeg.
         */
        private static List<Color> colors;
        
        
        //Static Methods
        
        /**
         * Returns the list of formats supported by ffmpeg.
         *
         * @return The list of formats supported by ffmpeg.
         * @see Format
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Format> getFormats() {
            return formats = Optional.ofNullable(formats).orElseGet(() ->
                    (List<Format>) loadImplement(Format.class, Format::new));
        }
        
        /**
         * Returns the list of demuxers supported by ffmpeg.
         *
         * @return The list of demuxers supported by ffmpeg.
         * @see Demuxer
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Demuxer> getDemuxers() {
            return demuxers = Optional.ofNullable(demuxers).orElseGet(() ->
                    (List<Demuxer>) loadImplement(Demuxer.class, Demuxer::new));
        }
        
        /**
         * Returns the list of muxers supported by ffmpeg.
         *
         * @return The list of muxers supported by ffmpeg.
         * @see Muxer
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Muxer> getMuxers() {
            return muxers = Optional.ofNullable(muxers).orElseGet(() ->
                    (List<Muxer>) loadImplement(Muxer.class, Muxer::new));
        }
        
        /**
         * Returns the list of devices supported by ffmpeg.
         *
         * @return The list of devices supported by ffmpeg.
         * @see Device
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Device> getDevices() {
            return devices = Optional.ofNullable(devices).orElseGet(() ->
                    (List<Device>) loadImplement(Device.class, Device::new));
        }
        
        /**
         * Returns the list of codecs supported by ffmpeg.
         *
         * @return The list of codecs supported by ffmpeg.
         * @see Codec
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Codec> getCodecs() {
            return codecs = Optional.ofNullable(codecs).orElseGet(() ->
                    (List<Codec>) loadImplement(Codec.class, Codec::new));
        }
        
        /**
         * Returns the list of encoders supported by ffmpeg.
         *
         * @return The list of encoders supported by ffmpeg.
         * @see Encoder
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Encoder> getEncoders() {
            return encoders = Optional.ofNullable(encoders).orElseGet(() ->
                    (List<Encoder>) loadImplement(Encoder.class, Encoder::new));
        }
        
        /**
         * Returns the list of decoders supported by ffmpeg.
         *
         * @return The list of decoders supported by ffmpeg.
         * @see Decoder
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Decoder> getDecoders() {
            return decoders = Optional.ofNullable(decoders).orElseGet(() ->
                    (List<Decoder>) loadImplement(Decoder.class, Decoder::new));
        }
        
        /**
         * Returns the list of bitstream filters supported by ffmpeg.
         *
         * @return The list of bitstream filters supported by ffmpeg.
         * @see BitstreamFilter
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<BitstreamFilter> getBitstreamFilters() {
            return bitstreamFilters = Optional.ofNullable(bitstreamFilters).orElseGet(() ->
                    (List<BitstreamFilter>) loadImplement(BitstreamFilter.class, BitstreamFilter::new));
        }
        
        /**
         * Returns the list of protocols supported by ffmpeg.
         *
         * @return The list of protocols supported by ffmpeg.
         * @see Protocol
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Protocol> getProtocols() {
            return protocols = Optional.ofNullable(protocols).orElseGet(() ->
                    (List<Protocol>) loadImplement(Protocol.class, Protocol::new));
        }
        
        /**
         * Returns the list of filters supported by ffmpeg.
         *
         * @return The list of filters supported by ffmpeg.
         * @see Filter
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Filter> getFilters() {
            return filters = Optional.ofNullable(filters).orElseGet(() ->
                    (List<Filter>) loadImplement(Filter.class, Filter::new));
        }
        
        /**
         * Returns the list of pixel formats supported by ffmpeg.
         *
         * @return The list of pixel formats supported by ffmpeg.
         * @see PixelFormat
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<PixelFormat> getPixelFormats() {
            return pixelFormats = Optional.ofNullable(pixelFormats).orElseGet(() ->
                    (List<PixelFormat>) loadImplement(PixelFormat.class, PixelFormat::new));
        }
        
        /**
         * Returns the list of sample formats supported by ffmpeg.
         *
         * @return The list of sample formats supported by ffmpeg.
         * @see SampleFormat
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<SampleFormat> getSampleFormats() {
            return sampleFormats = Optional.ofNullable(sampleFormats).orElseGet(() ->
                    (List<SampleFormat>) loadImplement(SampleFormat.class, SampleFormat::new));
        }
        
        /**
         * Returns the list of channels supported by ffmpeg.
         *
         * @return The list of channels supported by ffmpeg.
         * @see Channel
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Channel> getChannels() {
            return channels = Optional.ofNullable(channels).orElseGet(() ->
                    (List<Channel>) loadImplement(Channel.class, Channel::new));
        }
        
        /**
         * Returns the list of channel layouts supported by ffmpeg.
         *
         * @return The list of channel layouts supported by ffmpeg.
         * @see ChannelLayout
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<ChannelLayout> getChannelLayouts() {
            return channelLayouts = Optional.ofNullable(channelLayouts).orElseGet(() ->
                    (List<ChannelLayout>) loadImplement(ChannelLayout.class, ChannelLayout::new));
        }
        
        /**
         * Returns the list of colors supported by ffmpeg.
         *
         * @return The list of colors supported by ffmpeg.
         * @see Color
         * @see #loadImplement(Class, BiFunction)
         */
        @SuppressWarnings("unchecked")
        public static List<Color> getColors() {
            return colors = Optional.ofNullable(colors).orElseGet(() ->
                    (List<Color>) loadImplement(Color.class, Color::new));
        }
        
        /**
         * Loads a list of a type of Implements supported by ffmpeg.
         *
         * @param type      The type of Implement.
         * @param generator The generator for the specified type of Implement.
         * @return The list of the specified type of Implements supported by ffmpeg.
         */
        @SuppressWarnings("SpellCheckingInspection")
        private static List<? extends Implement> loadImplement(Class<? extends Implement> type, BiFunction<String, String, ? extends Implement> generator) {
            List<Implement> tmpImplements = new ArrayList<>();
            String ffmpegKey = EntityStringUtility.simpleClassString(type).toLowerCase()
                    .replace("bitstreamfilter", "bsf")
                    .replace("pixelformat", "pix_fmt")
                    .replace("sampleformat", "sample_fmt")
                    .replaceAll("channel(?:layout)?", "layout");
            
            String title = "";
            boolean valid = false;
            boolean first = true;
            for (String line : StringUtility.splitLines(ffmpeg("-hide_banner -" + ffmpegKey + 's'))) {
                title = line.endsWith(":") ? StringUtility.rShear(line, 1) : title;
                valid = !first && !line.isBlank() && !(line.toUpperCase().equals(line) && NumberUtility.extractNumberChars(line).isEmpty()) &&
                        (valid || !StringUtility.containsAnyChar(line, new Character[] {':', '='}) && !line.matches("^\\s*-+$"));
                first = false;
                if (!valid) {
                    continue;
                }
                
                Implement implement = generator.apply(line, title);
                if (!implement.invalid) {
                    tmpImplements.add(implement);
                }
            }
            return ListUtility.unmodifiableList(tmpImplements);
        }
        
        /**
         * Clears the cached Implements.
         */
        public static void clearCache() {
            formats = null;
            demuxers = null;
            muxers = null;
            devices = null;
            codecs = null;
            decoders = null;
            encoders = null;
            bitstreamFilters = null;
            protocols = null;
            filters = null;
            pixelFormats = null;
            sampleFormats = null;
            channels = null;
            channelLayouts = null;
            colors = null;
        }
        
        
        //Inner Classes
        
        /**
         * Defines the base properties of an ffmpeg implement.
         */
        private static abstract class Implement {
            
            //Fields
            
            /**
             * The name of the implement.
             */
            protected String name;
            
            /**
             * The description of the implement; or null if no description.
             */
            protected String description;
            
            /**
             * The stream type the implement is for; or null if not applicable.
             */
            protected StreamType type;
            
            /**
             * The ffmpeg log line specifying the implement.
             */
            final String logLine;
            
            /**
             * The regex matcher matching the ffmpeg log line specifying the implement.
             */
            final Matcher logLineMatcher;
            
            /**
             * A flag indicating whether the ffmpeg log line specifying the implement is invalid or not.
             */
            boolean invalid = false;
            
            
            //Constructors
            
            /**
             * Creates a new Implement from an ffmpeg implement line.
             *
             * @param implementLine The ffmpeg implement line.
             * @param title         The title for the ffmpeg implement line.
             */
            Implement(String implementLine, String title) {
                this.logLine = implementLine;
                this.logLineMatcher = getImplementLinePattern().matcher(this.logLine);
                if (!this.logLineMatcher.matches()) {
                    this.invalid = true;
                    return;
                }
                
                this.name = logLineMatcher.group("name");
                this.description = getImplementLinePattern().pattern().contains("?<description>") ?
                                   logLineMatcher.group("description") : null;
                this.type = getImplementLinePattern().pattern().contains("?<type>") ?
                            Arrays.stream(StreamType.values())
                                    .filter(e -> StringUtility.lSnip(e.name(), 1).equalsIgnoreCase(logLineMatcher.group("type")))
                                    .findFirst().orElse(null) : null;
            }
            
            
            //Getters
            
            /**
             * Returns the name of the implement.
             *
             * @return The name of the implement.
             */
            public String getName() {
                return name;
            }
            
            /**
             * Returns the description of the ffmpeg implement.
             *
             * @return The description of the ffmpeg implement.
             */
            public String getDescription() {
                return description;
            }
            
            /**
             * Returns the stream type the ffmpeg implement is for.
             *
             * @return The stream type the ffmpeg implement is for.
             */
            public StreamType getType() {
                return type;
            }
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            protected abstract Pattern getImplementLinePattern();
            
        }
        
        /**
         * Defines an ffmpeg format.
         */
        public static class Format extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg format line.
             */
            public static final Pattern FORMAT_LINE_PATTERN = Pattern.compile("^\\s*(?<supportsDemuxing>.)(?<supportsMuxing>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$");
            
            
            //Fields
            
            /**
             * Whether or not the format supports demuxing.
             */
            private boolean supportsDemuxing;
            
            /**
             * Whether or not the format supports muxing.
             */
            private boolean supportsMuxing;
            
            
            //Constructors
            
            /**
             * Creates a new Format from an ffmpeg format line.
             *
             * @param formatLine The ffmpeg format line.
             * @param title      The title for the ffmpeg format line.
             */
            Format(String formatLine, String title) {
                super(formatLine, title);
                
                if (!this.invalid) {
                    this.supportsDemuxing = !this.logLineMatcher.group("supportsDemuxing").equals(".");
                    this.supportsMuxing = !this.logLineMatcher.group("supportsMuxing").equals(".");
                }
            }
            
            
            //Getters
            
            /**
             * Returns whether or not the format supports demuxing.
             *
             * @return Whether or not the format supports demuxing.
             */
            public boolean supportsDemuxing() {
                return supportsDemuxing;
            }
            
            /**
             * Returns whether or not the format supports muxing.
             *
             * @return Whether or not the format supports muxing.
             */
            public boolean supportsMuxing() {
                return supportsMuxing;
            }
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return FORMAT_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg demuxers.
         */
        public static class Demuxer extends Format {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg demuxer line.
             */
            public static final Pattern DEMUXER_LINE_PATTERN = FORMAT_LINE_PATTERN;
            
            
            //Constructors
            
            /**
             * Creates a new Demuxer from an ffmpeg demuxer line.
             *
             * @param demuxerLine The ffmpeg demuxer line.
             * @param title       The title for the ffmpeg demuxer line.
             */
            Demuxer(String demuxerLine, String title) {
                super(demuxerLine, title);
            }
            
            
            //Getters
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return DEMUXER_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg muxer.
         */
        public static class Muxer extends Format {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg muxer line.
             */
            public static final Pattern MUXER_LINE_PATTERN = FORMAT_LINE_PATTERN;
            
            
            //Constructors
            
            /**
             * Creates a new Muxer from an ffmpeg muxer line.
             *
             * @param muxerLine The ffmpeg muxer line.
             * @param title     The title for the ffmpeg muxer line.
             */
            Muxer(String muxerLine, String title) {
                super(muxerLine, title);
            }
            
            
            //Getters
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return MUXER_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg device.
         */
        public static class Device extends Format {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg device line.
             */
            public static final Pattern DEVICE_LINE_PATTERN = FORMAT_LINE_PATTERN;
            
            
            //Constructors
            
            /**
             * Creates a new Device from an ffmpeg device line.
             *
             * @param deviceLine The ffmpeg device line.
             * @param title      The title for the ffmpeg device line.
             */
            Device(String deviceLine, String title) {
                super(deviceLine, title);
            }
            
            
            //Getters
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return DEVICE_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg codec.
         */
        public static class Codec extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg codec line.
             */
            public static final Pattern CODEC_LINE_PATTERN = Pattern.compile("^\\s*(?<supportsDecoding>.)(?<supportsEncoding>.)(?<type>.)(?<isIntraFrameOnly>.)(?<hasLossyCompression>.)(?<hasLosslessCompression>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$");
            
            
            //Fields
            
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
             * @param title     The title for the ffmpeg codec line.
             */
            Codec(String codecLine, String title) {
                super(codecLine, title);
                
                if (!this.invalid) {
                    this.supportsDecoding = !this.logLineMatcher.group("supportsDecoding").equals(".");
                    this.supportsEncoding = !this.logLineMatcher.group("supportsEncoding").equals(".");
                    this.isIntraFrameOnly = !this.logLineMatcher.group("isIntraFrameOnly").equals(".");
                    this.hasLossyCompression = !this.logLineMatcher.group("hasLossyCompression").equals(".");
                    this.hasLosslessCompression = !this.logLineMatcher.group("hasLosslessCompression").equals(".");
                }
            }
            
            
            //Getters
            
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
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return CODEC_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines the base properties of an ffmpeg encoder or decoder.
         */
        private static abstract class Coder extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg coder line.
             */
            public static final Pattern CODER_LINE_PATTERN = Pattern.compile("^\\s*(?<type>.)(?<hasFrameLevelMultithreading>.)(?<hasSliceLevelMultithreading>.)(?<isExperimental>.)(?<supportsDrawHorizontalBand>.)(?<supportsDirectRenderingMethod1>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$");
            
            
            //Fields
            
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
             * @param title     The title for the ffmpeg coder line.
             */
            Coder(String coderLine, String title) {
                super(coderLine, title);
                
                if (!this.invalid) {
                    this.hasFrameLevelMultithreading = !this.logLineMatcher.group("hasFrameLevelMultithreading").equals(".");
                    this.hasSliceLevelMultithreading = !this.logLineMatcher.group("hasSliceLevelMultithreading").equals(".");
                    this.isExperimental = !this.logLineMatcher.group("isExperimental").equals(".");
                    this.supportsDrawHorizontalBand = !this.logLineMatcher.group("supportsDrawHorizontalBand").equals(".");
                    this.supportsDirectRenderingMethod1 = !this.logLineMatcher.group("supportsDirectRenderingMethod1").equals(".");
                }
            }
            
            
            //Getters
            
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
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return CODER_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg decoder.
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
             * @param title       The title for the ffmpeg decoder line.
             */
            Decoder(String decoderLine, String title) {
                super(decoderLine, title);
            }
            
            
            //Getters
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return DECODER_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg encoder.
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
             * @param title       The title for the ffmpeg encoder line.
             */
            Encoder(String encoderLine, String title) {
                super(encoderLine, title);
            }
            
            
            //Getters
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return ENCODER_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg bitstream filter.
         */
        public static class BitstreamFilter extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg bitstream filter line.
             */
            public static final Pattern BITSTREAM_FILTER_LINE_PATTERN = Pattern.compile("^\\s*(?<name>[^\\s]+)\\s*$");
            
            
            //Constructors
            
            /**
             * Creates a new BitstreamFilter from an ffmpeg bitstream filter line.
             *
             * @param bitstreamFilterLine The ffmpeg bitstream filter line.
             * @param title               The title for the ffmpeg bitstream filter line.
             */
            BitstreamFilter(String bitstreamFilterLine, String title) {
                super(bitstreamFilterLine, title);
            }
            
            
            //Getters
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return BITSTREAM_FILTER_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg protocol.
         */
        public static class Protocol extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg protocol line.
             */
            public static final Pattern PROTOCOL_LINE_PATTERN = Pattern.compile("^\\s*(?<name>[^\\s]+)\\s*$");
            
            
            //Fields
            
            /**
             * Whether or not the protocol is for input.
             */
            private boolean isInput;
            
            /**
             * Whether or not the protocol is for output.
             */
            private boolean isOutput;
            
            
            //Constructors
            
            /**
             * Creates a new Protocol from an ffmpeg protocol line.
             *
             * @param protocolLine The ffmpeg protocol line.
             * @param title        The title for the ffmpeg protocol line.
             */
            Protocol(String protocolLine, String title) {
                super(protocolLine, title);
                
                if (!this.invalid) {
                    this.isInput = title.equalsIgnoreCase("input");
                    this.isOutput = title.equalsIgnoreCase("output");
                }
            }
            
            
            //Getters
            
            /**
             * Returns whether or not the protocol is for input.
             *
             * @return Whether or not the protocol is for input.
             */
            public boolean isInput() {
                return isInput;
            }
            
            /**
             * Returns whether or not the protocol is for output.
             *
             * @return Whether or not the protocol is for output.
             */
            public boolean isOutput() {
                return isOutput;
            }
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return PROTOCOL_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg libavfilter filter.
         */
        public static class Filter extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg filter line.
             */
            public static final Pattern FILTER_LINE_PATTERN = Pattern.compile("^\\s*(?<supportsTimeline>.)(?<hasSliceThreading>.)(?<supportsCommand>.)\\s+(?<name>[^\\s]+)\\s+(?<specification>[^\\s]+)\\s+(?<description>.+)\\s*$");
            
            
            //Fields
            
            /**
             * The libavfilter specification of the filter.
             */
            private String specification;
            
            /**
             * Whether or not the filter can be used as a timeline filter.
             */
            private boolean supportsTimeline;
            
            /**
             * Whether or not the filter has slice threading.
             */
            private boolean hasSliceThreading;
            
            /**
             * Whether or not the filter can be used as a command filter.
             */
            private boolean supportsCommand;
            
            
            //Constructors
            
            /**
             * Creates a new Filter from an ffmpeg filter line.
             *
             * @param filterLine The ffmpeg filter line.
             * @param title      The title for the ffmpeg filter line.
             */
            Filter(String filterLine, String title) {
                super(filterLine, title);
                
                if (!this.invalid) {
                    this.specification = this.logLineMatcher.group("specification");
                    this.supportsTimeline = !this.logLineMatcher.group("supportsTimeline").equals(".");
                    this.hasSliceThreading = !this.logLineMatcher.group("hasSliceThreading").equals(".");
                    this.supportsCommand = !this.logLineMatcher.group("supportsCommand").equals(".");
                }
            }
            
            
            //Getters
            
            /**
             * Returns the libavfilter specification of the filter.
             *
             * @return The libavfilter specification of the filter.
             */
            public String getSpecification() {
                return specification;
            }
            
            /**
             * Returns whether or not the filter can be used as a timeline filter.
             *
             * @return Whether or not the filter can be used as a timeline filter.
             */
            public boolean supportsTimeline() {
                return supportsTimeline;
            }
            
            /**
             * Returns whether or not the filter has slice threading.
             *
             * @return Whether or not the filter has slice threading.
             */
            public boolean hasSliceThreading() {
                return hasSliceThreading;
            }
            
            /**
             * Returns whether or not the filter can be used as a command filter.
             *
             * @return Whether or not the filter can be used as a command filter.
             */
            public boolean supportsCommand() {
                return supportsCommand;
            }
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return FILTER_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg pixel formats.
         */
        public static class PixelFormat extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg pixel format line.
             */
            public static final Pattern PIXEL_FORMAT_LINE_PATTERN = Pattern.compile("^\\s*(?<isSupportedInputForConversion>.)(?<isSupportedOutputForConversion>.)(?<isHardwareAccelerated>.)(?<isPaletted>.)(?<isBitstream>.)\\s+(?<name>[^\\s]+)\\s+(?<components>\\d+)\\s+(?<bitsPerPixel>\\d+)\\s*$");
            
            
            //Fields
            
            /**
             * The number of components of the pixel format.
             */
            private int components;
            
            /**
             * The number of bits per pixel of the pixel format.
             */
            private int bitsPerPixel;
            
            /**
             * Whether or not the pixel format is a supported input for conversion.
             */
            private boolean isSupportedInputForConversion;
            
            /**
             * Whether or not the pixel format is a supported output for conversion.
             */
            private boolean isSupportedOutputForConversion;
            
            /**
             * Whether or not the pixel format is hardware accelerated.
             */
            private boolean isHardwareAccelerated;
            
            /**
             * Whether or not the pixel format is paletted.
             */
            private boolean isPaletted;
            
            /**
             * Whether or not the pixel format is bitstream.
             */
            private boolean isBitstream;
            
            
            //Constructors
            
            /**
             * Creates a new PixelFormat from an ffmpeg pixel format line.
             *
             * @param pixelFormatLine The ffmpeg pixel format line.
             * @param title           The title for the ffmpeg pixel format line.
             */
            PixelFormat(String pixelFormatLine, String title) {
                super(pixelFormatLine, title);
                
                if (!this.invalid) {
                    this.components = Integer.parseInt(this.logLineMatcher.group("components"));
                    this.bitsPerPixel = Integer.parseInt(this.logLineMatcher.group("bitsPerPixel"));
                    this.isSupportedInputForConversion = !this.logLineMatcher.group("isSupportedInputForConversion").equals(".");
                    this.isSupportedOutputForConversion = !this.logLineMatcher.group("isSupportedOutputForConversion").equals(".");
                    this.isHardwareAccelerated = !this.logLineMatcher.group("isHardwareAccelerated").equals(".");
                    this.isPaletted = !this.logLineMatcher.group("isPaletted").equals(".");
                    this.isBitstream = !this.logLineMatcher.group("isBitstream").equals(".");
                }
            }
            
            
            //Getters
            
            /**
             * Returns the number of components of the pixel format.
             *
             * @return The number of components of the pixel format.
             */
            public int getComponents() {
                return components;
            }
            
            /**
             * Returns the number of bits per pixel per pixel of the pixel format.
             *
             * @return The number of bits per pixel of the pixel format.
             */
            public int getBitsPerPixel() {
                return bitsPerPixel;
            }
            
            /**
             * Returns whether or not the pixel format is a supported input for conversion.
             *
             * @return Whether or not the pixel format is a supported input for conversion.
             */
            public boolean isSupportedInputForConversion() {
                return isSupportedInputForConversion;
            }
            
            /**
             * Returns whether or not the pixel format is a supported output for conversion.
             *
             * @return Whether or not the pixel format is a supported output for conversion.
             */
            public boolean isSupportedOutputForConversion() {
                return isSupportedOutputForConversion;
            }
            
            /**
             * Returns whether or not the pixel format is hardware accelerated.
             *
             * @return Whether or not the pixel format is hardware accelerated.
             */
            public boolean isHardwareAccelerated() {
                return isHardwareAccelerated;
            }
            
            /**
             * Returns whether or not the pixel format is paletted.
             *
             * @return Whether or not the pixel format is paletted.
             */
            public boolean isPaletted() {
                return isPaletted;
            }
            
            /**
             * Returns whether or not the pixel format is bitstream.
             *
             * @return Whether or not the pixel format is bitstream.
             */
            public boolean isBitstream() {
                return isBitstream;
            }
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return PIXEL_FORMAT_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg sample formats.
         */
        public static class SampleFormat extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg sample format line.
             */
            public static final Pattern SAMPLE_FORMAT_LINE_PATTERN = Pattern.compile("^\\s*(?<name>[^\\s]+)\\s+(?<bitDepth>\\d+)\\s*$");
            
            
            //Fields
            
            /**
             * The bit depth of the sample format.
             */
            private int bitDepth;
            
            
            //Constructors
            
            /**
             * Creates a new SampleFormat from an ffmpeg sample format line.
             *
             * @param sampleFormatLine The ffmpeg sample format line.
             * @param title            The title for the ffmpeg sample format line.
             */
            SampleFormat(String sampleFormatLine, String title) {
                super(sampleFormatLine, title);
                
                if (!this.invalid) {
                    this.bitDepth = Integer.parseInt(this.logLineMatcher.group("bitDepth"));
                }
            }
            
            
            //Getters
            
            /**
             * Returns the bit depth of the sample format.
             *
             * @return The bit depth of the sample format.
             */
            public int getBitDepth() {
                return bitDepth;
            }
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return SAMPLE_FORMAT_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg channel.
         */
        public static class Channel extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg channel line.
             */
            public static final Pattern CHANNEL_LINE_PATTERN = Pattern.compile("^\\s*(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$");
            
            
            //Constructors
            
            /**
             * Creates a new Channel from an ffmpeg channel line.
             *
             * @param channelLine The ffmpeg channel line.
             * @param title       The title for the ffmpeg channel line.
             */
            Channel(String channelLine, String title) {
                super(channelLine, title);
                this.invalid |= !title.equalsIgnoreCase("individual channels");
            }
            
            
            //Getters
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return CHANNEL_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg channel layout.
         */
        public static class ChannelLayout extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg channel layout line.
             */
            public static final Pattern CHANNEL_LAYOUT_LINE_PATTERN = Pattern.compile("^\\s*(?<name>[^\\s]+)\\s+(?<decomposition>.+)\\s*$");
            
            
            //Fields
            
            /**
             * The decomposition of the channel layout.
             */
            private String decomposition;
            
            
            //Constructors
            
            /**
             * Creates a new ChannelLayout from an ffmpeg channel layout line.
             *
             * @param channelLayoutLine The ffmpeg channel layout line.
             * @param title             The title for the ffmpeg channel layout line.
             */
            ChannelLayout(String channelLayoutLine, String title) {
                super(channelLayoutLine, title);
                this.invalid |= !title.equalsIgnoreCase("standard channel layouts");
                
                if (!this.invalid) {
                    this.decomposition = this.logLineMatcher.group("decomposition");
                }
            }
            
            
            //Getters
            
            /**
             * Returns the decomposition of the channel layout.
             *
             * @return The decomposition of the channel layout.
             */
            public String getDecomposition() {
                return decomposition;
            }
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return CHANNEL_LAYOUT_LINE_PATTERN;
            }
            
        }
        
        /**
         * Defines an ffmpeg color.
         */
        public static class Color extends Implement {
            
            //Constants
            
            /**
             * The regex pattern for an ffmpeg color line.
             */
            public static final Pattern COLOR_LINE_PATTERN = Pattern.compile("^\\s*(?<name>[^\\s]+)\\s+(?<hexCode>#[0-f]+)\\s*$");
            
            
            //Fields
            
            /**
             * The hex code of the color.
             */
            private String hexCode;
            
            
            //Constructors
            
            /**
             * Creates a new Color from an ffmpeg color line.
             *
             * @param colorLine The ffmpeg color line.
             * @param title     The title for the ffmpeg color line.
             */
            Color(String colorLine, String title) {
                super(colorLine, title);
                
                if (!this.invalid) {
                    this.hexCode = this.logLineMatcher.group("hexCode");
                }
            }
            
            //Getters
            
            /**
             * Returns the hex code of the color.
             *
             * @return The hex code of the color.
             */
            public String getHexCode() {
                return hexCode;
            }
            
            /**
             * Returns the line pattern for the Implement.
             *
             * @return The line pattern for the Implement.
             */
            @Override
            protected Pattern getImplementLinePattern() {
                return COLOR_LINE_PATTERN;
            }
            
        }
        
    }
    
}
