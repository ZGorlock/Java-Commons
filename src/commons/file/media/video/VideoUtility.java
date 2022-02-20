/*
 * File:    VideoUtility.java
 * Package: commons.file.media.video
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.file.media.video;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import commons.access.Filesystem;
import commons.file.media.FFmpeg;
import commons.log.CommonsLogging;
import commons.object.string.StringUtility;
import commons.time.DateTimeUtility;
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
    
    
    //Static Methods
    
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
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        " -map 0 -map_metadata 0 -map_chapters 0" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1",
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
     * @param encoders A map from stream identifiers to the encoders to use; streams not present in the map will have their current encoding copied.
     * @param output   The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String transcodeVideo(File source, Map<FFmpeg.Identifier.Stream<?>, String> encoders, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        " -map 0 -c copy -map_metadata 0 -map_chapters 0" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1" +
                        encoders.entrySet().stream().map(e -> (" -c:" + e.getKey().specifier() + ' ' + e.getValue())).collect(Collectors.joining()),
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
    public static String mergeVideos(List<File> sources, File output) {
        return FFmpeg.ffmpeg(sources,
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        IntStream.range(0, sources.size()).boxed().map(i -> (" -map " + i)).collect(Collectors.joining()) +
                        " -c copy -map_metadata 0 -map_chapters 0" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1",
                output);
    }
    
    /**
     * Adds the specified streams to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the streams to add.
     * @param streamIds    A list of stream identifiers to remove.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String addStreams(File source, File streamSource, List<FFmpeg.Identifier.Stream<?>> streamIds, File output) {
        return FFmpeg.ffmpeg(Arrays.asList(source, streamSource),
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        " -map 0" + streamIds.stream().map(e -> " -map 1:" + e.specifier()).collect(Collectors.joining()) +
                        " -c copy -map_metadata 0 -map_chapters 0" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1",
                output);
    }
    
    /**
     * Adds the specified stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the stream to add.
     * @param streamId     The stream identifier of the stream from the stream source to add to the source.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStreams(File, File, List, File)
     */
    public static String addStream(File source, File streamSource, FFmpeg.Identifier.Stream<?> streamId, File output) {
        return addStreams(source, streamSource, Collections.singletonList(streamId), output);
    }
    
    /**
     * Adds a stream of a certain type to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the stream to add.
     * @param streamType   The type of stream.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStream(File, File, FFmpeg.Identifier.Stream, File)
     */
    public static String addStreamOfType(File source, File streamSource, FFmpeg.StreamType streamType, File output) {
        return addStream(source, streamSource, FFmpeg.Identifier.Stream.ofFirst(streamType), output);
    }
    
    /**
     * Adds a video stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param video  The video stream to add.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStreamOfType(File, File, FFmpeg.StreamType, File)
     */
    public static String addVideoStream(File source, File video, File output) {
        return addStreamOfType(source, video, FFmpeg.StreamType.VIDEO, output);
    }
    
    /**
     * Adds an audio stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param audio  The audio stream to add.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStreamOfType(File, File, FFmpeg.StreamType, File)
     */
    public static String addAudioStream(File source, File audio, File output) {
        return addStreamOfType(source, audio, FFmpeg.StreamType.AUDIO, output);
    }
    
    /**
     * Adds subtitles to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source    The video file.
     * @param subtitles The subtitles to add.
     * @param output    The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStreamOfType(File, File, FFmpeg.StreamType, File)
     */
    public static String addSubtitleStream(File source, File subtitles, File output) {
        return addStreamOfType(source, subtitles, FFmpeg.StreamType.SUBTITLE, output);
    }
    
    /**
     * Adds a data stream to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source     The video file.
     * @param dataStream The data stream to add.
     * @param output     The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStreamOfType(File, File, FFmpeg.StreamType, File)
     */
    public static String addDataStream(File source, File dataStream, File output) {
        return addStreamOfType(source, dataStream, FFmpeg.StreamType.DATA, output);
    }
    
    /**
     * Adds all streams of a certain type to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the streams to add.
     * @param streamType   The type of stream.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see #addStream(File, File, FFmpeg.Identifier.Stream, File)
     */
    public static String addAllStreamsOfType(File source, File streamSource, FFmpeg.StreamType streamType, File output) {
        return addStream(source, streamSource, FFmpeg.Identifier.Stream.of(streamType), output);
    }
    
    /**
     * Adds all video streams from a stream source to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the stream to add.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see #addAllStreamsOfType(File, File, FFmpeg.StreamType, File)
     */
    public static String addAllVideoStreams(File source, File streamSource, File output) {
        return addAllStreamsOfType(source, streamSource, FFmpeg.StreamType.VIDEO, output);
    }
    
    /**
     * Adds all audio streams from a stream source to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the stream to add.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see #addAllStreamsOfType(File, File, FFmpeg.StreamType, File)
     */
    public static String addAllAudioStreams(File source, File streamSource, File output) {
        return addAllStreamsOfType(source, streamSource, FFmpeg.StreamType.AUDIO, output);
    }
    
    /**
     * Adds all subtitles from a stream source to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the stream to add.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see #addAllStreamsOfType(File, File, FFmpeg.StreamType, File)
     */
    public static String addAllSubtitleStreams(File source, File streamSource, File output) {
        return addAllStreamsOfType(source, streamSource, FFmpeg.StreamType.SUBTITLE, output);
    }
    
    /**
     * Adds all data streams from a stream source to a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source       The video file.
     * @param streamSource The file containing the stream to add.
     * @param output       The output video file.
     * @return The response from the ffmpeg process.
     * @see #addAllStreamsOfType(File, File, FFmpeg.StreamType, File)
     */
    public static String addAllDataStreams(File source, File streamSource, File output) {
        return addAllStreamsOfType(source, streamSource, FFmpeg.StreamType.DATA, output);
    }
    
    /**
     * Removes the specified streams from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source    The video file.
     * @param streamIds A list of stream identifiers to remove.
     * @param output    The output video file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String removeStreams(File source, List<FFmpeg.Identifier.Stream<?>> streamIds, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        " -map 0" + streamIds.stream().map(e -> " -map -0:" + e.specifier()).collect(Collectors.joining()) +
                        " -c copy -map_metadata 0 -map_chapters 0" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1",
                output);
    }
    
    /**
     * Removes the specified stream from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The video file.
     * @param streamId The stream identifier.
     * @param output   The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeStreams(File, List, File)
     */
    public static String removeStream(File source, FFmpeg.Identifier.Stream<?> streamId, File output) {
        return removeStreams(source, Collections.singletonList(streamId), output);
    }
    
    /**
     * Removes streams of a certain type from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source     The video file.
     * @param streamType The type of stream.
     * @param output     The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeStream(File, FFmpeg.Identifier.Stream, File)
     */
    public static String removeStreamType(File source, FFmpeg.StreamType streamType, File output) {
        return removeStream(source, FFmpeg.Identifier.Stream.of(streamType), output);
    }
    
    /**
     * Removes all video streams from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeStreamType(File, FFmpeg.StreamType, File)
     */
    public static String removeVideoStreams(File source, File output) {
        return removeStreamType(source, FFmpeg.StreamType.VIDEO, output);
    }
    
    /**
     * Removes all audio streams from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeStreamType(File, FFmpeg.StreamType, File)
     */
    public static String removeAudioStreams(File source, File output) {
        return removeStreamType(source, FFmpeg.StreamType.AUDIO, output);
    }
    
    /**
     * Removes all subtitles from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeStreamType(File, FFmpeg.StreamType, File)
     */
    public static String removeSubtitleStreams(File source, File output) {
        return removeStreamType(source, FFmpeg.StreamType.SUBTITLE, output);
    }
    
    /**
     * Removes all data streams from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source The video file.
     * @param output The output video file.
     * @return The response from the ffmpeg process.
     * @see #removeStreamType(File, FFmpeg.StreamType, File)
     */
    public static String removeDataStreams(File source, File output) {
        return removeStreamType(source, FFmpeg.StreamType.DATA, output);
    }
    
    /**
     * Extracts a stream from a video file.<br>
     * This method is for a specific purpose, if the ffmpeg parameters or method parameters do not fit your needs then see the FFmpeg.ffmpeg() methods.
     *
     * @param source   The video file.
     * @param streamId The stream identifier.
     * @param output   The output file.
     * @return The response from the ffmpeg process.
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    public static String extractStream(File source, FFmpeg.Identifier.Stream<FFmpeg.Identifier.Scope.Singular> streamId, File output) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        " -map 0:" + streamId.specifier() +
                        " -c copy -map_metadata 0 -map_chapters -1" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1",
                output);
    }
    
    /**
     * Returns the duration of a video stream.<br>
     * To get the duration of the entire video file see FFmpeg.getDuration().
     *
     * @param videoFile The video file.
     * @return The duration of the video stream in milliseconds, or -1 if the video file does not exist or cannot be read, or if the video file does not contain a video stream, or if the video stream duration is not specified.
     * @see FFmpeg#getStreamDuration(File, FFmpeg.Identifier.Stream)
     */
    public static long getDuration(File videoFile) {
        return FFmpeg.getStreamDuration(videoFile, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO));
    }
    
    /**
     * Returns the bitrate of a video stream.<br>
     * To get the bitrate of the entire video file see FFmpeg.getBitrate().
     *
     * @param videoFile The video file.
     * @return The bitrate of the video stream in bits per second, or -1 if the video file does not exist or cannot be read, or if the video file does not contain a video stream, or if the video stream bitrate is not specified.
     * @see FFmpeg#getStreamBitrate(File, FFmpeg.Identifier.Stream)
     */
    public static long getBitrate(File videoFile) {
        return FFmpeg.getStreamBitrate(videoFile, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO));
    }
    
    /**
     * Returns the encoding of a video stream.<br>
     * To get the encoding of the entire video file see FFmpeg.getFormat().
     *
     * @param videoFile The video file.
     * @return The encoding of the video stream, or null if the video file does not exist or cannot be read, or if the video file does not contain a video stream.
     * @see FFmpeg#getStreamEncoding(File, FFmpeg.Identifier.Stream)
     */
    public static String getEncoding(File videoFile) {
        return FFmpeg.getStreamEncoding(videoFile, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO));
    }
    
    /**
     * Returns the number of frames in a video stream.<br>
     * To get the number of frames of a different stream see the FFmpeg.getFrameCount() methods.
     *
     * @param videoFile The video file.
     * @return The encoding of the video stream, or -1 if the video file does not exist or cannot be read, or if the video file does not contain a video stream, or if the video stream frame count can not be determined.
     * @see FFmpeg#getFrameCount(File, FFmpeg.Identifier.Stream)
     */
    public static long getFrameCount(File videoFile) {
        return FFmpeg.getFrameCount(videoFile, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO));
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
                        " -map 0 -c copy -map_metadata 0 -map_chapters 0" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1",
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
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        " -vf scale=" + width + ':' + height +
                        " -map 0 -map_metadata 0 -map_chapters 0" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1",
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
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        " -crf " + crf +
                        " -map 0 -map_metadata 0 -map_chapters 0" +
                        " -movflags use_metadata_tags -id3v2_version 3 -write_id3v2 1 -write_apetag 1",
                output);
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
     * @see FFmpeg#ffmpeg(String, File, String, File, FFmpeg.FFmpegProgressBar)
     */
    public static String encodeFramesToVideo(List<File> frames, List<Long> frameDurations, File output) {
        final File concatDemuxer = Filesystem.getTemporaryFile("txt");
        Filesystem.writeStringToFile(concatDemuxer, IntStream.range(0, Math.min(frames.size(), frameDurations.size())).boxed()
                .map(i -> "file " + StringUtility.quote(frames.get(i).getAbsolutePath(), true) + System.lineSeparator() + "duration " + (frameDurations.get(i) / 1000.0))
                .collect(Collectors.joining(System.lineSeparator())));
        Filesystem.writeStringToFile(concatDemuxer, System.lineSeparator() + "file " + StringUtility.quote(frames.get(frames.size() - 1).getAbsolutePath(), true), true);
        
        return FFmpeg.ffmpeg("-f concat -safe 0",
                concatDemuxer,
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        " -vsync vfr",
                output,
                (CommonsLogging.showFfmpegProgressBarsByDefault() ? new FFmpeg.FFmpegProgressBar(concatDemuxer, output,
                        (frameDurations.stream().mapToLong(Long::valueOf).sum() / 1000)) : null));
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
     * @see FFmpeg#ffmpeg(String, File, String, File, FFmpeg.FFmpegProgressBar)
     */
    public static String encodeFramesToVideo(File frameDir, String frameNamePattern, double fps, File output) {
        return FFmpeg.ffmpeg("-framerate " + fps,
                new File(frameDir, frameNamePattern),
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : ""),
                output,
                (CommonsLogging.showFfmpegProgressBarsByDefault() ? new FFmpeg.FFmpegProgressBar(frameDir, output,
                        (long) (Optional.ofNullable(FFmpeg.getStream(new File(frameDir, frameNamePattern), FFmpeg.Identifier.Stream.ofIndex(0)))
                                .map(e -> e.get("duration_ts")).map(Long.class::cast).orElse(0L) / fps)) : null));
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
     * @see FFmpeg#ffmpeg(File, String, File, FFmpeg.FFmpegProgressBar)
     */
    public static String decodeFramesFromVideo(File source, int videoStreamIndex, double fps, File outputFrameDir, String frameNamePattern) {
        if (!outputFrameDir.exists()) {
            Filesystem.createDirectory(outputFrameDir);
        }
        
        return FFmpeg.ffmpeg(source,
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
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
     * @see FFmpeg#ffmpeg(File, String, File, boolean)
     */
    public static String extractFrameFromVideo(File source, int videoStreamIndex, String timestamp, File outputFrame) {
        return FFmpeg.ffmpeg(source,
                "-y -strict -2" +
                        ((FFmpeg.maxMuxingQueueSize >= 0) ? (" -max_muxing_queue_size " + FFmpeg.maxMuxingQueueSize) : "") +
                        " -map 0:v:" + videoStreamIndex + " -ss " + timestamp,
                outputFrame, false);
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
    
}
