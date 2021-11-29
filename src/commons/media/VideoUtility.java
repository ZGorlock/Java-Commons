/*
 * File:    VideoUtility.java
 * Package: commons.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import commons.access.Filesystem;
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
 * Handles video operations.
 */
public class VideoUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(VideoUtility.class);
    
    
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
    
    
    //Functions
    
    /**
     * Returns the metadata of a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @return The metadata of the video file, or null if the video does not exist or cannot be read.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static Metadata getMetadata(File video) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_format -show_streams -show_chapters -byte_binary_prefix", video);
        if (!StringUtility.removeWhiteSpace(ffprobeResponse).equals("{}")) {
            try {
                return new Metadata((JSONObject) new JSONParser().parse(ffprobeResponse));
            } catch (Exception ignored) {
            }
        }
        return null;
    }
    
    /**
     * Returns the format metadata of a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @return The format of the video file, or null if the video does not exist or cannot be read.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static FormatMetadata getFormat(File video) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_format -byte_binary_prefix", video);
        try {
            return new FormatMetadata((JSONObject) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("format"));
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns the streams metadata of a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @return The streams metadata of the video file, or null if the video does not exist or cannot be read.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static List<StreamMetadata> getStreams(File video) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_streams -byte_binary_prefix", video);
        try {
            return Arrays.stream(((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("streams")).toArray())
                    .map(e -> new StreamMetadata((JSONObject) e)).collect(Collectors.toList());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns a stream metadata from a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video      The video file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @return The specified stream metadata from the video file, or null if the video does not exist or cannot be read, or if the specified stream does not exist.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static StreamMetadata getStream(File video, StreamType streamType, int index) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -select_streams " + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + index +
                " -print_format json -show_streams -byte_binary_prefix", video);
        try {
            final JSONArray streams = (JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("streams");
            return (!streams.isEmpty()) ? new StreamMetadata((JSONObject) streams.get(0)) : null;
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns a stream metadata from a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video      The video file.
     * @param streamType The type of stream.
     * @return The specified stream metadata from the video file, or null if the video does not exist or cannot be read, or if the specified stream does not exist.
     * @see #getStream(File, StreamType, int)
     */
    public static StreamMetadata getStream(File video, StreamType streamType) {
        return getStream(video, streamType, 0);
    }
    
    /**
     * Returns a stream metadata from a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @param index The index of the stream.
     * @return The specified stream metadata from the video file, or null if the video does not exist or cannot be read, or the specified stream does not exist.
     * @see #getStream(File, StreamType, int)
     */
    public static StreamMetadata getStream(File video, int index) {
        return getStream(video, null, index);
    }
    
    /**
     * Returns the number of streams in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @return The number of streams in the video file, or 0 if the video does not exist or cannot be read.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static int getStreamCount(File video) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -show_entries format=nb_streams -of csv=p=0", video);
        try {
            return Integer.parseInt(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return 0;
    }
    
    /**
     * Returns the number of streams of a particular stream type in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video      The video file.
     * @param streamType The type of the stream.
     * @return The number of streams of a specified stream type in the video file, or 0 if the video does not exist or cannot be read.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static int getStreamCount(File video, StreamType streamType) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_streams", video);
        try {
            return (int) Arrays.stream(((JSONArray) ((JSONObject) new JSONParser()
                    .parse(ffprobeResponse)).get("streams")).toArray())
                    .filter(e -> streamType.name().equalsIgnoreCase((String) ((JSONObject) e).get("codec_type"))).count();
        } catch (Exception ignored) {
        }
        return 0;
    }
    
    /**
     * Returns the chapters metadata of a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @return The chapters metadata of the video file, or null if the video does not exist or cannot be read.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static List<ChapterMetadata> getChapters(File video) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_chapters -byte_binary_prefix", video);
        try {
            return Arrays.stream(((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters")).toArray())
                    .map(e -> new ChapterMetadata((JSONObject) e)).collect(Collectors.toList());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns a chapter metadata from a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @param index The index of the chapter.
     * @return The specified chapter metadata from the video file, or null if the video does not exist or cannot be read, or if the specified chapter does not exist.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static ChapterMetadata getChapter(File video, int index) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_chapters -byte_binary_prefix", video);
        try {
            final JSONArray chapters = (JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters");
            if (BoundUtility.inBounds(index, 0, chapters.size(), true, false)) {
                return new ChapterMetadata((JSONObject) chapters.get(index));
            }
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Returns the number of chapters in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @return The number of chapters in the video file, or 0 if the video does not exist or cannot be read.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static int getChapterCount(File video) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -print_format json -show_chapters -byte_binary_prefix", video);
        try {
            return ((JSONArray) ((JSONObject) new JSONParser().parse(ffprobeResponse)).get("chapters")).toArray().length;
        } catch (Exception ignored) {
        }
        return 0;
    }
    
    /**
     * Returns the duration of a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @return The duration of the video file in milliseconds, or 0 if the video does not exist or cannot be read.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static long getDuration(File video) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -show_entries format=duration -of csv=p=0", video);
        try {
            return (long) (Double.parseDouble(StringUtility.trim(ffprobeResponse)) * 1000);
        } catch (Exception ignored) {
        }
        return 0L;
    }
    
    /**
     * Returns the bitrate of a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @return The bitrate of the video file in bits per second, or 0 if the video does not exist or cannot be read.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static int getBitrate(File video) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -show_entries format=bit_rate -of csv=p=0", video);
        try {
            return Integer.parseInt(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return 0;
    }
    
    /**
     * Returns the encoding of a stream in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video      The video file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @return The encoding of the specified stream in the video file, or null if the video does not exist or cannot be read, or if the specified stream does not exist.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static String getEncoding(File video, StreamType streamType, int index) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -select_streams " + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + index +
                " -show_entries stream=codec_name -of csv=p=0", video);
        return (!StringUtility.isWhitespace(ffprobeResponse)) ? StringUtility.trim(ffprobeResponse) : null;
    }
    
    /**
     * Returns the encoding of a stream in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video      The video file.
     * @param streamType The type of stream.
     * @return The encoding of the specified stream in the video file, or null if the video does not exist or cannot be read, or if the specified stream does not exist.
     * @see #getEncoding(File, StreamType, int)
     */
    public static String getEncoding(File video, StreamType streamType) {
        return getEncoding(video, streamType, 0);
    }
    
    /**
     * Returns the encoding of a stream in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @param index The index of the stream.
     * @return The encoding of the specified stream in the video file, or null if the video does not exist or cannot be read, or if the specified stream does not exist.
     * @see #getEncoding(File, StreamType, int)
     */
    public static String getEncoding(File video, int index) {
        return getEncoding(video, null, index);
    }
    
    /**
     * Returns the number of frames in a stream in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video      The video file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @return The number of frames in the specified stream in the video file, or 0 if the video does not exist or cannot be read, or if the specified stream does not exist.
     * @see FFmpeg#ffprobe(String, File)
     */
    public static long getFrameCount(File video, StreamType streamType, int index) {
        final String ffprobeResponse = FFmpeg.ffprobe("-v quiet" +
                " -select_streams " + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + index +
                " -count_packets -show_entries stream=nb_read_packets -of csv=p=0", video);
        try {
            return Integer.parseInt(StringUtility.trim(ffprobeResponse));
        } catch (Exception ignored) {
        }
        return 0;
    }
    
    /**
     * Returns the number of frames in a stream in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video      The video file.
     * @param streamType The type of stream.
     * @return The number of frames in the specified stream in the video file, or 0 if the video does not exist or cannot be read, or if the specified stream does not exist.
     * @see #getFrameCount(File, StreamType, int)
     */
    public static long getFrameCount(File video, StreamType streamType) {
        return getFrameCount(video, streamType, 0);
    }
    
    /**
     * Returns the number of frames in a stream in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @param index The index of the stream.
     * @return The number of frames in the specified stream in the video file, or 0 if the video does not exist or cannot be read, or if the specified stream does not exist.
     * @see #getFrameCount(File, StreamType, int)
     */
    public static long getFrameCount(File video, int index) {
        return getFrameCount(video, null, index);
    }
    
    /**
     * Returns the number of frames in a video file.<br>
     * This method is for a specific purpose, if the ffprobe parameters or method parameters do not fit your needs then see the FFmpeg.ffprobe() methods.
     *
     * @param video The video file.
     * @return The number of frames in the specified video file, or 0 if the video does not exist or cannot be read.
     * @see #getFrameCount(File, StreamType, int)
     */
    public static long getFrameCount(File video) {
        return getFrameCount(video, StreamType.VIDEO, 0);
    }
    
    /**
     * Converts a video from one video file to another.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String convertVideo(File source, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0 -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Converts a video from one file format to another, in the same directory.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source           The video file.
     * @param outputFileFormat The output file format.
     * @return The response from the ffmpeg process.
     * @see #convertVideo(File, File)
     */
    public static String convertVideo(File source, String outputFileFormat) {
        return convertVideo(source, new File(source.getParentFile(),
                source.getName().replace('.' + Filesystem.getFileType(source), '.' + outputFileFormat.replace(".", ""))));
    }
    
    /**
     * Transcodes a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The video file.
     * @param encoders A map from stream identifiers to the encoders to use; example: 'v:1'->'libx264', 's'->'mov_text'; streams not present in the map will have their current encoding copied.
     * @param output   The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String transcodeVideo(File source, Map<String, String> encoders, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0 -c copy " +
                        encoders.entrySet().stream().map(e -> (" -c:" + e.getKey() + ' ' + e.getValue())).collect(Collectors.joining()) +
                        " -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Merges a number of files into one video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param sources The list of source files.
     * @param output  The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(List, String, File)
     */
    public static String mergeStreams(List<File> sources, File output) {
        return FFmpeg.ffmpeg(sources,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        IntStream.range(0, sources.size()).boxed().map(i -> (" -map " + i)).collect(Collectors.joining()) +
                        " -c copy -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Adds a stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the stream to add.
     * @param streamType   The type of the stream being added from the file.
     * @param streamIndex  The index of the stream of the specified stream type being added from the file.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(List, String, File)
     */
    public static String addStream(File source, File streamSource, StreamType streamType, int streamIndex, File output) {
        return FFmpeg.ffmpeg(Arrays.asList(source, streamSource),
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0 -map 1:" + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + streamIndex +
                        " -c copy -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Adds a stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the stream to add.
     * @param streamType   The type of the stream being added.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStream(File, File, StreamType, int, File)
     */
    public static String addStream(File source, File streamSource, StreamType streamType, File output) {
        return addStream(source, streamSource, streamType, 0, output);
    }
    
    /**
     * Adds a stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the stream to add.
     * @param streamIndex  The index of the stream being added from the file.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStream(File, File, StreamType, int, File)
     */
    public static String addStream(File source, File streamSource, int streamIndex, File output) {
        return addStream(source, streamSource, null, streamIndex, output);
    }
    
    /**
     * Adds a video stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param video  The video stream to add.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStream(File, File, StreamType, File)
     */
    public static String addVideoStream(File source, File video, File output) {
        return addStream(source, video, StreamType.VIDEO, output);
    }
    
    /**
     * Adds an audio stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param audio  The audio stream to add.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStream(File, File, StreamType, File)
     */
    public static String addAudioStream(File source, File audio, File output) {
        return addStream(source, audio, StreamType.AUDIO, output);
    }
    
    /**
     * Adds subtitles to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source    The video file.
     * @param subtitles The subtitles to add.
     * @param output    The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStream(File, File, StreamType, File)
     */
    public static String addSubtitles(File source, File subtitles, File output) {
        return addStream(source, subtitles, StreamType.SUBTITLE, output);
    }
    
    /**
     * Adds a data stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source     The video file.
     * @param dataStream The data stream to add.
     * @param output     The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStream(File, File, StreamType, File)
     */
    public static String addDataStream(File source, File dataStream, File output) {
        return addStream(source, dataStream, StreamType.DATA, output);
    }
    
    /**
     * Removes a stream from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source     The video file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @param output     The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String removeStream(File source, StreamType streamType, int index, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0 -map -0:" + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + index +
                        " -c copy -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Removes a stream from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param index  The index of the stream.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeStream(File, StreamType, int, File)
     */
    public static String removeStream(File source, int index, File output) {
        return removeStream(source, null, index, output);
    }
    
    /**
     * Removes streams of a certain type from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source     The video file.
     * @param streamType The type of stream.
     * @param output     The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String removeStreamType(File source, StreamType streamType, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0 -map -0:" + StringUtility.lSnip(streamType.name().toLowerCase(), 1) +
                        " -c copy -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Removes the video streams from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeVideoStreams(File, File)
     */
    public static String removeVideoStreams(File source, File output) {
        return removeStreamType(source, StreamType.VIDEO, output);
    }
    
    /**
     * Removes the audio streams from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeVideoStreams(File, File)
     */
    public static String removeAudioStreams(File source, File output) {
        return removeStreamType(source, StreamType.AUDIO, output);
    }
    
    /**
     * Removes the subtitles from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeVideoStreams(File, File)
     */
    public static String removeSubtitles(File source, File output) {
        return removeStreamType(source, StreamType.SUBTITLE, output);
    }
    
    /**
     * Removes the data streams from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeVideoStreams(File, File)
     */
    public static String removeDataStreams(File source, File output) {
        return removeStreamType(source, StreamType.DATA, output);
    }
    
    /**
     * Removes a stream from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source  The video file.
     * @param streams A list of stream identifiers to remove; example 'v:1', '0:3'.
     * @param output  The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String removeStreams(File source, List<String> streams, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0" + streams.stream().map(e -> " -map -" + e).collect(Collectors.joining()) +
                        " -c copy -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Strips the metadata and/or chapters from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source        The video file.
     * @param stripMetadata Whether or not to strip metadata.
     * @param stripChapters Whether or not to strip chapters.
     * @param output        The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String stripMetadataAndChapters(File source, boolean stripMetadata, boolean stripChapters, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0 -c copy -movflags use_metadata_tags" +
                        " -map_metadata " + (stripMetadata ? "-1" : "0") +
                        " -map_chapters " + (stripChapters ? "-1" : "0"),
                output);
    }
    
    /**
     * Cuts a video file.<br>
     * The resulting video's start time, stop time, and duration may not be exact depending on the encoding of the streams it contains.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source         The video file.
     * @param startTimestamp The starting timestamp of the output video inside the source video, in the format 'HH:mm:ss.SSS'.
     * @param endTimestamp   The ending timestamp of the output video inside the source video, in the format 'HH:mm:ss.SSS'.
     * @param output         The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    @SuppressWarnings("StringOperationCanBeSimplified")
    public static String cutVideo(File source, String startTimestamp, String endTimestamp, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -ss " + startTimestamp.toString() + " -to " + endTimestamp.toString() +
                        " -map 0 -c copy -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Cuts a video file.<br>
     * The resulting video's start time, stop time, and duration may not be exact depending on the encoding of the streams it contains.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source    The video file.
     * @param startTime The starting time of the output video inside the source video, in milliseconds.
     * @param endTime   The ending time of the output video inside the source video, in milliseconds.
     * @param output    The output video file.
     * @return The response from the ffmpeg process.
     * @see #cutVideo(File, String, String, File)
     */
    public static String cutVideo(File source, long startTime, long endTime, File output) {
        return cutVideo(source,
                DateTimeUtility.durationToDurationStamp(startTime, false, true),
                DateTimeUtility.durationToDurationStamp(endTime, false, true), output);
    }
    
    /**
     * Scales a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param width  The width of the output video.
     * @param height The height of the output video.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String scaleVideo(File source, int width, int height, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -vf scale=" + width + ':' + height +
                        " -map 0 -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Adjusts the quality of a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param crf    The crf quality value.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String adjustQuality(File source, int crf, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -crf " + crf +
                        " -map 0 -map_metadata 0 -map_chapters 0 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Extracts a stream from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source     The video file.
     * @param streamType The type of stream.
     * @param index      The index of the stream from the specified type of stream.
     * @param output     The output file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String extractStream(File source, StreamType streamType, int index, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0:" + ((streamType != null) ? (StringUtility.lSnip(streamType.name().toLowerCase(), 1) + ":") : "") + index +
                        " -c copy -map_metadata 0 -map_chapters -1 -movflags use_metadata_tags",
                output);
    }
    
    /**
     * Extracts a stream from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source     The video file.
     * @param streamType The type of stream.
     * @param output     The output file.
     * @return The response from the ffmpeg process.
     * @see #extractStream(File, StreamType, int, File)
     */
    public static String extractStream(File source, StreamType streamType, File output) {
        return extractStream(source, streamType, 0, output);
    }
    
    /**
     * Extracts a stream from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param index  The index of the stream.
     * @param output The output file.
     * @return The response from the ffmpeg process.
     * @see #extractStream(File, StreamType, int, File)
     */
    public static String extractStream(File source, int index, File output) {
        return extractStream(source, null, index, output);
    }
    
    /**
     * Encodes a series of frames into a video file.<br>
     * This will duplicate the last frame giving a total frame count of frames.size() + 1, due to a limitation of ffmpeg.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param frames         The list of frames to encode, in order.
     * @param frameDurations The list of durations for each frame in the corresponding frame list, in order, in milliseconds.
     * @param output         The output video file.
     * @return The response from the ffmpeg process, or an empty string if there was an error generating the concat demuxer.
     * @see FFmpeg#ffmpeg(String, File, String, File)
     */
    public static String encodeFramesToVideo(List<File> frames, List<Long> frameDurations, File output) {
        final File concatDemuxer = Filesystem.getTemporaryFile("txt");
        Filesystem.writeStringToFile(concatDemuxer, IntStream.range(0, Math.min(frames.size(), frameDurations.size())).boxed()
                .map(i -> "file '" + frames.get(i).getAbsolutePath() + "'" + System.lineSeparator() + "duration " + (frameDurations.get(i) / 1000.0))
                .collect(Collectors.joining(System.lineSeparator())));
        Filesystem.writeStringToFile(concatDemuxer, System.lineSeparator() + "file '" + frames.get(frames.size() - 1).getAbsolutePath() + "'", true);
        
        return FFmpeg.ffmpeg("-f concat -safe 0",
                concatDemuxer,
                "-y -strict -2 -max_muxing_queue_size 1024 -vsync vfr",
                output);
    }
    
    /**
     * Encodes a series of frames into a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param frameDir         The directory containing the frames.
     * @param frameNamePattern The name pattern of the frame images in the frame directory; example: 'frame_%03d.png'.
     * @param fps              The number of frames per second in the output video.
     * @param output           The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(String, File, String, File)
     */
    public static String encodeFramesToVideo(File frameDir, String frameNamePattern, double fps, File output) {
        return FFmpeg.ffmpeg("-framerate " + fps,
                new File(frameDir, frameNamePattern),
                "-y -strict -2 -max_muxing_queue_size 1024",
                output);
    }
    
    /**
     * Encodes a series of frames into a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param frames The list of frames to encode, in order; all images must be of the same file type.
     * @param fps    The number of frames per second in the output video.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #encodeFramesToVideo(File, String, double, File)
     */
    public static String encodeFramesToVideo(List<File> frames, double fps, File output) {
        final File frameDir = Filesystem.createTemporaryDirectory();
        final String fileType = Filesystem.getFileType(frames.get(0));
        for (int i = 0; i < frames.size(); i++) {
            Filesystem.copyFile(frames.get(i), new File(frameDir, "frame_" + StringUtility.padZero(i, 10) + '.' + fileType.toLowerCase()));
        }
        
        return encodeFramesToVideo(frameDir, "frame_%010d." + fileType.toLowerCase(), fps, output);
    }
    
    /**
     * Decodes a video file into a series of frames.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source           The video file.
     * @param videoStreamIndex The index of the video stream in the video file.
     * @param fps              The number of frames per second to decode frames at.
     * @param outputFrameDir   The output directory for the decoded frames.
     * @param frameNamePattern The name pattern of the resulting frame images; example: 'frame_%03d.png'.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String decodeFramesFromVideo(File source, int videoStreamIndex, double fps, File outputFrameDir, String frameNamePattern) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0:v:" + videoStreamIndex + " -filter:v fps=fps=" + fps,
                new File(outputFrameDir, frameNamePattern));
    }
    
    /**
     * Decodes a video file into a series of frames.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source           The video file.
     * @param fps              The number of frames per second to decode frames at.
     * @param outputFrameDir   The output directory for the decoded frames.
     * @param frameNamePattern The name pattern of the resulting frame images; example: 'frame_%03d.png'.
     * @return The response from the ffmpeg process.
     * @see #decodeFramesFromVideo(File, int, double, File, String)
     */
    public static String decodeFramesFromVideo(File source, double fps, File outputFrameDir, String frameNamePattern) {
        return decodeFramesFromVideo(source, 0, fps, outputFrameDir, frameNamePattern);
    }
    
    /**
     * Extracts a frame from a video file at a specified time.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source           The video file.
     * @param videoStreamIndex The index of the video stream in the video file.
     * @param timestamp        The timestamp to extract a frame from.
     * @param outputFrame      The output file to write the frame to.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String extractFrameFromVideo(File source, int videoStreamIndex, String timestamp, File outputFrame) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2 -max_muxing_queue_size 1024" +
                        " -map 0:v:" + videoStreamIndex + " -ss " + timestamp,
                outputFrame);
    }
    
    /**
     * Extracts a frame from a video file at a specified time.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source      The video file.
     * @param timestamp   The timestamp to extract a frame from.
     * @param outputFrame The output file to write the frame to.
     * @return The response from the ffmpeg process.
     * @see #extractFrameFromVideo(File, int, String, File)
     */
    public static String extractFrameFromVideo(File source, String timestamp, File outputFrame) {
        return extractFrameFromVideo(source, 0, timestamp, outputFrame);
    }
    
    /**
     * Extracts a frame from a video file at a specified time.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source           The video file.
     * @param videoStreamIndex The index of the video stream in the video file.
     * @param time             The time to extract a frame from, in milliseconds.
     * @param outputFrame      The output file to write the frame to.
     * @return The response from the ffmpeg process.
     * @see #extractFrameFromVideo(File, int, String, File)
     */
    public static String extractFrameFromVideo(File source, int videoStreamIndex, long time, File outputFrame) {
        return extractFrameFromVideo(source, videoStreamIndex,
                DateTimeUtility.durationToDurationStamp(time, false, true),
                outputFrame);
    }
    
    /**
     * Extracts a frame from a video file at a specified time.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source      The video file.
     * @param time        The time to extract a frame from, in milliseconds.
     * @param outputFrame The output file to write the frame to.
     * @return The response from the ffmpeg process.
     * @see #extractFrameFromVideo(File, int, long, File)
     */
    public static String extractFrameFromVideo(File source, long time, File outputFrame) {
        return extractFrameFromVideo(source, 0, time, outputFrame);
    }
    
    
    //Inner Classes
    
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
            return (duration > 0) ? (duration / 1000) : duration;
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
            return (endTime > 0) ? (endTime / 1000) : endTime;
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
     * Defines the format metadata of a video.
     */
    public static class FormatMetadata extends MetadataBase {
        
        //Fields
        
        /**
         * The video file.
         */
        private File videoFile;
        
        /**
         * The size in bytes.
         */
        private long size;
        
        /**
         * The bit rate in bits per second.
         */
        private int bitRate;
        
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
            
            this.videoFile = data.containsKey("filename") ? new File((String) data.get("filename")) : null;
            this.size = data.containsKey("size") ? (long) Double.parseDouble((String) data.get("size")) : 0L;
            this.bitRate = data.containsKey("bit_rate") ? (int) Double.parseDouble((String) data.get("bit_rate")) : 0;
            this.formatName = data.containsKey("format_name") ? (String) data.get("format_name") : "";
            this.formatNameLong = data.containsKey("format_long_name") ? (String) data.get("format_long_name") : "";
            this.streamCount = data.containsKey("nb_streams") ? (int) (long) data.get("nb_streams") : 0;
            this.programCount = data.containsKey("nb_programs") ? (int) (long) data.get("nb_programs") : 0;
        }
        
        
        //Getters
        
        /**
         * Returns the video file.
         *
         * @return The video file.
         */
        public File getVideoFile() {
            return videoFile;
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
        public long getBitRate() {
            return bitRate;
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
        private StreamType streamType;
        
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
            
            this.streamType = data.containsKey("codec_type") ? StreamType.valueOf(((String) data.get("codec_type")).toUpperCase()) : null;
            this.streamIndex = data.containsKey("index") ? (int) (long) data.get("index") : -1;
            this.isDefault = getDisposition("default");
            this.codecName = data.containsKey("codec_name") ? (String) data.get("codec_name") : "";
            this.codecNameLong = data.containsKey("codec_long_name") ? (String) data.get("codec_long_name") : "";
            
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
             * The channel layout of the audio stream.
             */
            private String channelLayout;
            
            /**
             * The number of channels of the audio stream.
             */
            private int channels;
            
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
                this.channelLayout = data.containsKey("channel_layout") ? (String) data.get("channel_layout") : null;
                this.channels = data.containsKey("channels") ? (int) (long) data.get("channels") : -1;
                this.sampleRate = data.containsKey("sample_rate") ? Integer.parseInt((String) data.get("sample_rate")) : -1;
                this.sampleFormat = data.containsKey("sample_fmt") ? (String) data.get("sample_fmt") : null;
                this.bitsPerSample = data.containsKey("bits_per_sample") ? (int) (long) data.get("bits_per_sample") : -1;
            }
            
            
            //Getters
            
            /**
             * Returns the channel layout of the audio stream.
             *
             * @return The channel layout of the audio stream, or null if it is not a audio stream or it cannot be determined.
             */
            public String getChannelLayout() {
                return channelLayout;
            }
            
            /**
             * Returns the number of channels of the audio stream.
             *
             * @return The number of channels of the audio stream, or -1 if it is not a audio stream or it cannot be determined.
             */
            public int getChannels() {
                return channels;
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
    
}
