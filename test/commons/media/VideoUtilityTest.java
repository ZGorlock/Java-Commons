/*
 * File:    VideoUtilityTest.java
 * Package: commons.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.media;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import commons.access.Filesystem;
import commons.access.Project;
import commons.graphics.DrawUtility;
import commons.math.MathUtility;
import commons.math.component.vector.IntVector;
import commons.string.StringUtility;
import commons.test.TestUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of VideoUtility.
 *
 * @see VideoUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.crypto.*", "javax.swing.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({VideoUtility.class})
public class VideoUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(VideoUtilityTest.class);
    
    
    //Constants
    
    /**
     * The directory containing resources for this test class.
     */
    private static final File testResources = Project.testResourcesDir(VideoUtility.class);
    
    
    //Initialization
    
    /**
     * The JUnit class setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @BeforeClass
    public static void setupClass() throws Exception {
    }
    
    /**
     * The JUnit class cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @AfterClass
    public static void cleanupClass() throws Exception {
    }
    
    /**
     * The JUnit setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Before
    public void setup() throws Exception {
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @After
    public void cleanup() throws Exception {
    }
    
    
    //Tests
    
    /**
     * JUnit test of constants.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of StreamType.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility.StreamType
     */
    @Test
    public void testStreamType() throws Exception {
        Assert.assertEquals(4, VideoUtility.StreamType.values().length);
        Assert.assertEquals(VideoUtility.StreamType.VIDEO, VideoUtility.StreamType.values()[0]);
        Assert.assertEquals(VideoUtility.StreamType.AUDIO, VideoUtility.StreamType.values()[1]);
        Assert.assertEquals(VideoUtility.StreamType.SUBTITLE, VideoUtility.StreamType.values()[2]);
        Assert.assertEquals(VideoUtility.StreamType.DATA, VideoUtility.StreamType.values()[3]);
    }
    
    /**
     * JUnit test of getMetadata.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getMetadata(File)
     */
    @Test
    public void testGetMetadata() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        VideoUtility.Metadata result;
        
        //standard
        result = VideoUtility.getMetadata(testVideo);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getFormat());
        Assert.assertNotNull(result.getStreams());
        Assert.assertNotNull(result.getChapters());
        Assert.assertEquals(((JSONObject) new JSONParser().parse(Filesystem.readFileToString(new File(testResources, "test.mkv.json")))).toJSONString(),
                result.getData().toJSONString());
        Assert.assertEquals(VideoUtility.getFormat(testVideo).getData().toJSONString(), result.getFormat().getData().toJSONString());
        Assert.assertEquals(VideoUtility.getStreams(testVideo).stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")),
                result.getStreams().stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")));
        Assert.assertEquals(VideoUtility.getChapters(testVideo).stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")),
                result.getChapters().stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")));
        
        //invalid
        Assert.assertNull(VideoUtility.getMetadata(new File(testResources, "fakeVideo.mp4")));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getMetadata(null));
    }
    
    /**
     * JUnit test of getFormat.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getFormat(File)
     */
    @Test
    public void testGetFormat() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        VideoUtility.FormatMetadata metadata;
        
        //standard
        metadata = VideoUtility.getFormat(testVideo);
        Assert.assertNotNull(metadata);
        Assert.assertEquals(testVideo.getAbsolutePath(), metadata.getVideoFile().getAbsolutePath());
        Assert.assertEquals(3067000L, metadata.getDurationExact());
        Assert.assertEquals(-5000L, metadata.getStartTimeExact());
        Assert.assertEquals(3062000L, metadata.getEndTimeExact());
        Assert.assertEquals(3067L, metadata.getDuration());
        Assert.assertEquals(-5L, metadata.getStartTime());
        Assert.assertEquals(3062L, metadata.getEndTime());
        Assert.assertEquals(565905L, metadata.getSize());
        Assert.assertEquals(1476113L, metadata.getBitRate());
        Assert.assertEquals("matroska,webm", metadata.getFormatName());
        Assert.assertEquals("Matroska / WebM", metadata.getFormatNameLong());
        Assert.assertEquals("FFmpeg Test Video", metadata.getTitle());
        Assert.assertEquals(15, metadata.getStreamCount());
        Assert.assertEquals(0, metadata.getProgramCount());
        Assert.assertEquals(2, metadata.getTags().size());
        Assert.assertEquals("Lavf58.45.100", metadata.getTag("encoder"));
        Assert.assertEquals("FFmpeg Test Video", metadata.getTag("title"));
        
        //invalid
        Assert.assertNull(VideoUtility.getFormat(new File(testResources, "fakeVideo.mp4")));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getFormat(null));
    }
    
    /**
     * JUnit test of getStreams.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getStreams(File)
     */
    @Test
    public void testGetStreams() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        List<VideoUtility.StreamMetadata> result;
        VideoUtility.StreamMetadata stream;
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //standard
        result = VideoUtility.getStreams(testVideo);
        Assert.assertNotNull(result);
        Assert.assertEquals(15, result.size());
        stream = result.get(0);
        Assert.assertEquals(VideoUtility.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals("h264", stream.getCodecName());
        Assert.assertEquals("H.264 / AVC / MPEG-4 AVC / MPEG-4 part 10", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertEquals("Red", stream.getTitle());
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3023L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:03.023000000", stream.getTag("duration"));
        Assert.assertTrue(stream.isDefaultStream());
        Assert.assertEquals(10.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(320L, stream.getVideoMetadata().getWidth());
        Assert.assertEquals(240L, stream.getVideoMetadata().getHeight());
        Assert.assertEquals("yuv444p", stream.getVideoMetadata().getPixelFormat());
        Assert.assertEquals("High 4:4:4 Predictive", stream.getVideoMetadata().getProfile());
        stream = result.get(1);
        Assert.assertEquals(VideoUtility.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals("hevc", stream.getCodecName());
        Assert.assertEquals("H.265 / HEVC (High Efficiency Video Coding)", stream.getCodecNameLong());
        Assert.assertEquals("spa", stream.getLanguage());
        Assert.assertEquals("Green", stream.getTitle());
        Assert.assertEquals("Lavc58.91.100 libx265", stream.getTag("encoder"));
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3023L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:03.023000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(10.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(320L, stream.getVideoMetadata().getWidth());
        Assert.assertEquals(240L, stream.getVideoMetadata().getHeight());
        Assert.assertEquals("yuv444p", stream.getVideoMetadata().getPixelFormat());
        Assert.assertEquals("Rext", stream.getVideoMetadata().getProfile());
        stream = result.get(2);
        Assert.assertEquals(VideoUtility.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals("mpeg4", stream.getCodecName());
        Assert.assertEquals("MPEG-4 part 2", stream.getCodecNameLong());
        Assert.assertEquals("rus", stream.getLanguage());
        Assert.assertEquals("Blue", stream.getTitle());
        Assert.assertEquals("Lavc58.91.100 mpeg4", stream.getTag("encoder"));
        Assert.assertEquals(23L, stream.getStartTime());
        Assert.assertEquals(3046L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:03.023000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(10.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(320L, stream.getVideoMetadata().getWidth());
        Assert.assertEquals(240L, stream.getVideoMetadata().getHeight());
        Assert.assertEquals("yuv420p", stream.getVideoMetadata().getPixelFormat());
        Assert.assertEquals("Simple Profile", stream.getVideoMetadata().getProfile());
        stream = result.get(3);
        Assert.assertEquals(VideoUtility.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("mp3", stream.getCodecName());
        Assert.assertEquals("MP3 (MPEG audio layer 3)", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertEquals("MP3 Audio", stream.getTag("description"));
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getTag("artist"));
        Assert.assertEquals(11L, stream.getStartTime());
        Assert.assertEquals(3078L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("00:00:03.067000000", stream.getTag("duration"));
        Assert.assertTrue(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioMetadata().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioMetadata().getChannels());
        Assert.assertEquals("fltp", stream.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioMetadata().getBitsPerSample());
        stream = result.get(4);
        Assert.assertEquals(VideoUtility.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("aac", stream.getCodecName());
        Assert.assertEquals("AAC (Advanced Audio Coding)", stream.getCodecNameLong());
        Assert.assertEquals("AAC Audio", stream.getTag("description"));
        Assert.assertEquals("spa", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getTag("artist"));
        Assert.assertEquals(23L, stream.getStartTime());
        Assert.assertEquals(3088L, stream.getEndTime());
        Assert.assertEquals(3065L, stream.getDuration());
        Assert.assertEquals("00:00:03.065000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioMetadata().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioMetadata().getChannels());
        Assert.assertEquals("fltp", stream.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioMetadata().getBitsPerSample());
        stream = result.get(5);
        Assert.assertEquals(VideoUtility.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("flac", stream.getCodecName());
        Assert.assertEquals("FLAC (Free Lossless Audio Codec)", stream.getCodecNameLong());
        Assert.assertEquals("FLAC Audio", stream.getTag("description"));
        Assert.assertEquals("rus", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getTag("artist"));
        Assert.assertEquals(23L, stream.getStartTime());
        Assert.assertEquals(3046L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:03.023000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioMetadata().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioMetadata().getChannels());
        Assert.assertEquals("s16", stream.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioMetadata().getBitsPerSample());
        stream = result.get(6);
        Assert.assertEquals(VideoUtility.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("aac", stream.getCodecName());
        Assert.assertEquals("AAC (Advanced Audio Coding)", stream.getCodecNameLong());
        Assert.assertEquals("M4A Audio", stream.getTag("description"));
        Assert.assertEquals("jpn", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getTag("artist"));
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3041L, stream.getEndTime());
        Assert.assertEquals(3041L, stream.getDuration());
        Assert.assertEquals("00:00:03.041000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioMetadata().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioMetadata().getChannels());
        Assert.assertEquals("fltp", stream.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioMetadata().getBitsPerSample());
        stream = result.get(7);
        Assert.assertEquals(VideoUtility.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("opus", stream.getCodecName());
        Assert.assertEquals("Opus (Opus Interactive Audio Codec)", stream.getCodecNameLong());
        Assert.assertEquals("OPUS Audio", stream.getTag("description"));
        Assert.assertEquals("fre", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getTag("artist"));
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3018L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:03.023000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(48000, stream.getAudioMetadata().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioMetadata().getChannels());
        Assert.assertEquals("fltp", stream.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioMetadata().getBitsPerSample());
        stream = result.get(8);
        Assert.assertEquals(VideoUtility.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("vorbis", stream.getCodecName());
        Assert.assertEquals("Vorbis", stream.getCodecNameLong());
        Assert.assertEquals("OGG Audio", stream.getTag("description"));
        Assert.assertEquals("ger", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getTag("artist"));
        Assert.assertEquals(20L, stream.getStartTime());
        Assert.assertEquals(3043L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:03.023000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioMetadata().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioMetadata().getChannels());
        Assert.assertEquals("fltp", stream.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioMetadata().getBitsPerSample());
        stream = result.get(9);
        Assert.assertEquals(VideoUtility.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertEquals("English", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("00:00:02.911000000", stream.getTag("duration"));
        Assert.assertTrue(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        stream = result.get(10);
        Assert.assertEquals(VideoUtility.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("spa", stream.getLanguage());
        Assert.assertEquals("Spanish", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("00:00:02.911000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        stream = result.get(11);
        Assert.assertEquals(VideoUtility.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("rus", stream.getLanguage());
        Assert.assertEquals("Russian", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("00:00:02.911000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        stream = result.get(12);
        Assert.assertEquals(VideoUtility.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("jpn", stream.getLanguage());
        Assert.assertEquals("Japanese", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("00:00:02.911000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        stream = result.get(13);
        Assert.assertEquals(VideoUtility.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("fre", stream.getLanguage());
        Assert.assertEquals("French", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("00:00:02.911000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        stream = result.get(14);
        Assert.assertEquals(VideoUtility.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("ger", stream.getLanguage());
        Assert.assertEquals("German", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("00:00:02.911000000", stream.getTag("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        result = VideoUtility.getStreams(testVideo2);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        stream = result.get(0);
        Assert.assertEquals(VideoUtility.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals("h264", stream.getCodecName());
        Assert.assertEquals("H.264 / AVC / MPEG-4 AVC / MPEG-4 part 10", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertNull(stream.getTitle());
        Assert.assertEquals(5L, stream.getStartTime());
        Assert.assertEquals(3028L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        stream = result.get(1);
        Assert.assertEquals(VideoUtility.StreamType.DATA, stream.getStreamType());
        Assert.assertEquals("bin_data", stream.getCodecName());
        Assert.assertEquals("binary data", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertNull(stream.getTitle());
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3004L, stream.getEndTime());
        Assert.assertEquals(3004L, stream.getDuration());
        
        //invalid
        Assert.assertNull(VideoUtility.getStreams(new File(testResources, "fakeVideo.mp4")));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getStreams(null));
    }
    
    /**
     * JUnit test of getStream.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getStream(File, VideoUtility.StreamType, int)
     * @see VideoUtility#getStream(File, VideoUtility.StreamType)
     * @see VideoUtility#getStream(File, int)
     */
    @Test
    public void testGetStream() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //stream type, index
        Assert.assertEquals("Red", VideoUtility.getStream(testVideo, VideoUtility.StreamType.VIDEO, 0).getTitle());
        Assert.assertEquals("Green", VideoUtility.getStream(testVideo, VideoUtility.StreamType.VIDEO, 1).getTitle());
        Assert.assertEquals("Blue", VideoUtility.getStream(testVideo, VideoUtility.StreamType.VIDEO, 2).getTitle());
        Assert.assertEquals("MP3 Audio", VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO, 0).getTag("description"));
        Assert.assertEquals("AAC Audio", VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO, 1).getTag("description"));
        Assert.assertEquals("FLAC Audio", VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO, 2).getTag("description"));
        Assert.assertEquals("M4A Audio", VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO, 3).getTag("description"));
        Assert.assertEquals("OPUS Audio", VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO, 4).getTag("description"));
        Assert.assertEquals("OGG Audio", VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO, 5).getTag("description"));
        Assert.assertEquals("English", VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE, 0).getTitle());
        Assert.assertEquals("Spanish", VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE, 1).getTitle());
        Assert.assertEquals("Russian", VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE, 2).getTitle());
        Assert.assertEquals("Japanese", VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE, 3).getTitle());
        Assert.assertEquals("French", VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE, 4).getTitle());
        Assert.assertEquals("German", VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE, 5).getTitle());
        Assert.assertEquals("bin_data", VideoUtility.getStream(testVideo2, VideoUtility.StreamType.DATA, 0).getCodecName());
        
        //stream type
        Assert.assertEquals("Red", VideoUtility.getStream(testVideo, VideoUtility.StreamType.VIDEO).getTitle());
        Assert.assertEquals("Red", VideoUtility.getStream(testVideo, VideoUtility.StreamType.VIDEO).getTitle());
        Assert.assertEquals("MP3 Audio", VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO).getTag("description"));
        Assert.assertEquals("MP3 Audio", VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO).getTag("description"));
        Assert.assertEquals("English", VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE).getTitle());
        Assert.assertEquals("English", VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE).getTitle());
        Assert.assertEquals("bin_data", VideoUtility.getStream(testVideo2, VideoUtility.StreamType.DATA).getCodecName());
        Assert.assertEquals("Red", VideoUtility.getStream(testVideo, null).getTitle());
        Assert.assertEquals("Red", VideoUtility.getStream(testVideo, null).getTitle());
        
        //null stream type
        Assert.assertEquals("Red", VideoUtility.getStream(testVideo, null, 0).getTitle());
        Assert.assertEquals("Green", VideoUtility.getStream(testVideo, null, 1).getTitle());
        Assert.assertEquals("Blue", VideoUtility.getStream(testVideo, null, 2).getTitle());
        Assert.assertEquals("MP3 Audio", VideoUtility.getStream(testVideo, null, 3).getTag("description"));
        Assert.assertEquals("AAC Audio", VideoUtility.getStream(testVideo, null, 4).getTag("description"));
        Assert.assertEquals("FLAC Audio", VideoUtility.getStream(testVideo, null, 5).getTag("description"));
        Assert.assertEquals("M4A Audio", VideoUtility.getStream(testVideo, null, 6).getTag("description"));
        Assert.assertEquals("OPUS Audio", VideoUtility.getStream(testVideo, null, 7).getTag("description"));
        Assert.assertEquals("OGG Audio", VideoUtility.getStream(testVideo, null, 8).getTag("description"));
        Assert.assertEquals("English", VideoUtility.getStream(testVideo, null, 9).getTitle());
        Assert.assertEquals("Spanish", VideoUtility.getStream(testVideo, null, 10).getTitle());
        Assert.assertEquals("Russian", VideoUtility.getStream(testVideo, null, 11).getTitle());
        Assert.assertEquals("Japanese", VideoUtility.getStream(testVideo, null, 12).getTitle());
        Assert.assertEquals("French", VideoUtility.getStream(testVideo, null, 13).getTitle());
        Assert.assertEquals("German", VideoUtility.getStream(testVideo, null, 14).getTitle());
        Assert.assertEquals("bin_data", VideoUtility.getStream(testVideo2, null, 1).getCodecName());
        Assert.assertEquals("Red", VideoUtility.getStream(testVideo, null).getTitle());
        Assert.assertEquals("Red", VideoUtility.getStream(testVideo, null).getTitle());
        
        //index
        Assert.assertEquals("Red", VideoUtility.getStream(testVideo, 0).getTitle());
        Assert.assertEquals("Green", VideoUtility.getStream(testVideo, 1).getTitle());
        Assert.assertEquals("Blue", VideoUtility.getStream(testVideo, 2).getTitle());
        Assert.assertEquals("MP3 Audio", VideoUtility.getStream(testVideo, 3).getTag("description"));
        Assert.assertEquals("AAC Audio", VideoUtility.getStream(testVideo, 4).getTag("description"));
        Assert.assertEquals("FLAC Audio", VideoUtility.getStream(testVideo, 5).getTag("description"));
        Assert.assertEquals("M4A Audio", VideoUtility.getStream(testVideo, 6).getTag("description"));
        Assert.assertEquals("OPUS Audio", VideoUtility.getStream(testVideo, 7).getTag("description"));
        Assert.assertEquals("OGG Audio", VideoUtility.getStream(testVideo, 8).getTag("description"));
        Assert.assertEquals("English", VideoUtility.getStream(testVideo, 9).getTitle());
        Assert.assertEquals("Spanish", VideoUtility.getStream(testVideo, 10).getTitle());
        Assert.assertEquals("Russian", VideoUtility.getStream(testVideo, 11).getTitle());
        Assert.assertEquals("Japanese", VideoUtility.getStream(testVideo, 12).getTitle());
        Assert.assertEquals("French", VideoUtility.getStream(testVideo, 13).getTitle());
        Assert.assertEquals("German", VideoUtility.getStream(testVideo, 14).getTitle());
        Assert.assertEquals("bin_data", VideoUtility.getStream(testVideo2, 1).getCodecName());
        
        //invalid
        Assert.assertNull(VideoUtility.getStream(testVideo, VideoUtility.StreamType.VIDEO, 3));
        Assert.assertNull(VideoUtility.getStream(testVideo, VideoUtility.StreamType.VIDEO, -1));
        Assert.assertNull(VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO, 6));
        Assert.assertNull(VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO, -1));
        Assert.assertNull(VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE, 6));
        Assert.assertNull(VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE, -1));
        Assert.assertNull(VideoUtility.getStream(testVideo, VideoUtility.StreamType.DATA));
        Assert.assertNull(VideoUtility.getStream(testVideo, null, 15));
        Assert.assertNull(VideoUtility.getStream(testVideo, null, -1));
        Assert.assertNull(VideoUtility.getStream(testVideo, 15));
        Assert.assertNull(VideoUtility.getStream(testVideo, -1));
        Assert.assertNull(VideoUtility.getStream(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO, 0));
        Assert.assertNull(VideoUtility.getStream(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO));
        Assert.assertNull(VideoUtility.getStream(new File(testResources, "fakeVideo.mp4"), 0));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getStream(null, VideoUtility.StreamType.SUBTITLE, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getStream(null, VideoUtility.StreamType.SUBTITLE));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getStream(null, 1));
    }
    
    /**
     * JUnit test of getStreamCount.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getStreamCount(File)
     * @see VideoUtility#getStreamCount(File, VideoUtility.StreamType)
     */
    @Test
    public void testGetStreamCount() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //standard
        Assert.assertEquals(15, VideoUtility.getStreamCount(testVideo));
        
        //stream type
        Assert.assertEquals(3, VideoUtility.getStreamCount(testVideo, VideoUtility.StreamType.VIDEO));
        Assert.assertEquals(6, VideoUtility.getStreamCount(testVideo, VideoUtility.StreamType.AUDIO));
        Assert.assertEquals(6, VideoUtility.getStreamCount(testVideo, VideoUtility.StreamType.SUBTITLE));
        Assert.assertEquals(0, VideoUtility.getStreamCount(testVideo, VideoUtility.StreamType.DATA));
        Assert.assertEquals(1, VideoUtility.getStreamCount(testVideo2, VideoUtility.StreamType.DATA));
        
        //invalid
        Assert.assertEquals(0, VideoUtility.getStreamCount(new File(testResources, "fakeVideo.mp4")));
        Assert.assertEquals(0, VideoUtility.getStreamCount(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getStreamCount(null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getStreamCount(null, VideoUtility.StreamType.VIDEO));
    }
    
    /**
     * JUnit test of getChapters.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getChapters(File)
     */
    @Test
    public void testGetChapters() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        List<VideoUtility.ChapterMetadata> result;
        VideoUtility.ChapterMetadata chapter;
        
        //standard
        result = VideoUtility.getChapters(testVideo);
        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
        chapter = result.get(0);
        Assert.assertEquals(0L, chapter.getStartTime());
        Assert.assertEquals(1000L, chapter.getEndTime());
        Assert.assertEquals(1L, chapter.getChapterId());
        Assert.assertEquals("Chapter 1", chapter.getTitle());
        chapter = result.get(1);
        Assert.assertEquals(1100L, chapter.getStartTime());
        Assert.assertEquals(1800L, chapter.getEndTime());
        Assert.assertEquals(2L, chapter.getChapterId());
        Assert.assertEquals("Second Chapter", chapter.getTitle());
        chapter = result.get(2);
        Assert.assertEquals(1900L, chapter.getStartTime());
        Assert.assertEquals(2999L, chapter.getEndTime());
        Assert.assertEquals(3L, chapter.getChapterId());
        Assert.assertEquals("The Last Chapter", chapter.getTitle());
        
        //invalid
        Assert.assertNull(VideoUtility.getChapters(new File(testResources, "fakeVideo.mp4")));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getChapters(null));
    }
    
    /**
     * JUnit test of getChapter.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getChapter(File, int)
     */
    @Test
    public void testGetChapter() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        
        //index
        Assert.assertEquals("Chapter 1", VideoUtility.getChapter(testVideo, 0).getTitle());
        Assert.assertEquals("Second Chapter", VideoUtility.getChapter(testVideo, 1).getTitle());
        Assert.assertEquals("The Last Chapter", VideoUtility.getChapter(testVideo, 2).getTitle());
        
        //invalid
        Assert.assertNull(VideoUtility.getChapter(testVideo, 3));
        Assert.assertNull(VideoUtility.getChapter(testVideo, -1));
        Assert.assertNull(VideoUtility.getChapter(new File(testResources, "fakeVideo.mp4"), 0));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getChapter(null, 0));
    }
    
    /**
     * JUnit test of getChapterCount.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getChapterCount(File)
     */
    @Test
    public void testGetChapterCount() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        
        //standard
        Assert.assertEquals(3, VideoUtility.getChapterCount(testVideo));
        
        //invalid
        Assert.assertEquals(0, VideoUtility.getChapterCount(new File(testResources, "fakeVideo.mp4")));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getChapterCount(null));
    }
    
    /**
     * JUnit test of getDuration.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getDuration(File)
     */
    @Test
    public void testGetDuration() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        
        //standard
        Assert.assertEquals(3067L, VideoUtility.getDuration(testVideo));
        
        //invalid
        Assert.assertEquals(0L, VideoUtility.getDuration(new File(testResources, "fakeVideo.mp4")));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getDuration(null));
    }
    
    /**
     * JUnit test of getBitrate.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getBitrate(File)
     */
    @Test
    public void testGetBitrate() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        
        //standard
        Assert.assertEquals(1476113, VideoUtility.getBitrate(testVideo));
        
        //invalid
        Assert.assertEquals(0, VideoUtility.getBitrate(new File(testResources, "fakeVideo.mp4")));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getBitrate(null));
    }
    
    /**
     * JUnit test of getEncoding.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getEncoding(File, VideoUtility.StreamType, int)
     * @see VideoUtility#getEncoding(File, VideoUtility.StreamType)
     * @see VideoUtility#getEncoding(File, int)
     */
    @Test
    public void testGetEncoding() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //stream type, index
        Assert.assertEquals("h264", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.VIDEO, 0));
        Assert.assertEquals("hevc", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.VIDEO, 1));
        Assert.assertEquals("mpeg4", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.VIDEO, 2));
        Assert.assertEquals("mp3", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO, 0));
        Assert.assertEquals("aac", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO, 1));
        Assert.assertEquals("flac", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO, 2));
        Assert.assertEquals("aac", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO, 3));
        Assert.assertEquals("opus", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO, 4));
        Assert.assertEquals("vorbis", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO, 5));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE, 0));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE, 1));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE, 2));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE, 3));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE, 4));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE, 5));
        Assert.assertEquals("bin_data", VideoUtility.getEncoding(testVideo2, VideoUtility.StreamType.DATA, 0));
        
        //stream type
        Assert.assertEquals("h264", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.VIDEO));
        Assert.assertEquals("h264", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.VIDEO));
        Assert.assertEquals("mp3", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO));
        Assert.assertEquals("mp3", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE));
        Assert.assertEquals("bin_data", VideoUtility.getEncoding(testVideo2, VideoUtility.StreamType.DATA));
        Assert.assertEquals("h264", VideoUtility.getEncoding(testVideo, null));
        Assert.assertEquals("h264", VideoUtility.getEncoding(testVideo, null));
        
        //null stream type
        Assert.assertEquals("h264", VideoUtility.getEncoding(testVideo, null, 0));
        Assert.assertEquals("hevc", VideoUtility.getEncoding(testVideo, null, 1));
        Assert.assertEquals("mpeg4", VideoUtility.getEncoding(testVideo, null, 2));
        Assert.assertEquals("mp3", VideoUtility.getEncoding(testVideo, null, 3));
        Assert.assertEquals("aac", VideoUtility.getEncoding(testVideo, null, 4));
        Assert.assertEquals("flac", VideoUtility.getEncoding(testVideo, null, 5));
        Assert.assertEquals("aac", VideoUtility.getEncoding(testVideo, null, 6));
        Assert.assertEquals("opus", VideoUtility.getEncoding(testVideo, null, 7));
        Assert.assertEquals("vorbis", VideoUtility.getEncoding(testVideo, null, 8));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, null, 9));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, null, 10));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, null, 11));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, null, 12));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, null, 13));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, null, 14));
        Assert.assertEquals("bin_data", VideoUtility.getEncoding(testVideo2, null, 1));
        Assert.assertEquals("h264", VideoUtility.getEncoding(testVideo, null));
        Assert.assertEquals("h264", VideoUtility.getEncoding(testVideo, null));
        
        //index
        Assert.assertEquals("h264", VideoUtility.getEncoding(testVideo, 0));
        Assert.assertEquals("hevc", VideoUtility.getEncoding(testVideo, 1));
        Assert.assertEquals("mpeg4", VideoUtility.getEncoding(testVideo, 2));
        Assert.assertEquals("mp3", VideoUtility.getEncoding(testVideo, 3));
        Assert.assertEquals("aac", VideoUtility.getEncoding(testVideo, 4));
        Assert.assertEquals("flac", VideoUtility.getEncoding(testVideo, 5));
        Assert.assertEquals("aac", VideoUtility.getEncoding(testVideo, 6));
        Assert.assertEquals("opus", VideoUtility.getEncoding(testVideo, 7));
        Assert.assertEquals("vorbis", VideoUtility.getEncoding(testVideo, 8));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, 9));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, 10));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, 11));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, 12));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, 13));
        Assert.assertEquals("subrip", VideoUtility.getEncoding(testVideo, 14));
        Assert.assertEquals("bin_data", VideoUtility.getEncoding(testVideo2, 1));
        
        //invalid
        Assert.assertNull(VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.VIDEO, 3));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.VIDEO, -1));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO, 6));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.AUDIO, -1));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE, 6));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.SUBTITLE, -1));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, VideoUtility.StreamType.DATA));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, null, 15));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, null, -1));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, 15));
        Assert.assertNull(VideoUtility.getEncoding(testVideo, -1));
        Assert.assertNull(VideoUtility.getEncoding(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO, 0));
        Assert.assertNull(VideoUtility.getEncoding(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO));
        Assert.assertNull(VideoUtility.getEncoding(new File(testResources, "fakeVideo.mp4"), 0));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getEncoding(null, VideoUtility.StreamType.SUBTITLE, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getEncoding(null, VideoUtility.StreamType.SUBTITLE));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getEncoding(null, 1));
    }
    
    /**
     * JUnit test of getFrameCount.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getFrameCount(File, VideoUtility.StreamType, int)
     * @see VideoUtility#getFrameCount(File, VideoUtility.StreamType)
     * @see VideoUtility#getFrameCount(File, int)
     * @see VideoUtility#getFrameCount(File)
     */
    @Test
    public void testGetFrameCount() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //standard
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo));
        
        //stream type, index
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.VIDEO, 0));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.VIDEO, 1));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.VIDEO, 2));
        Assert.assertEquals(117, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO, 0));
        Assert.assertEquals(131, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO, 1));
        Assert.assertEquals(29, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO, 2));
        Assert.assertEquals(131, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO, 3));
        Assert.assertEquals(151, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO, 4));
        Assert.assertEquals(286, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO, 5));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE, 0));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE, 1));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE, 2));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE, 3));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE, 4));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE, 5));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo2, VideoUtility.StreamType.DATA, 0));
        
        //stream type
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.VIDEO));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.VIDEO));
        Assert.assertEquals(117, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO));
        Assert.assertEquals(117, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo2, VideoUtility.StreamType.DATA));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, null));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, null));
        
        //null stream type
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, null, 0));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, null, 1));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, null, 2));
        Assert.assertEquals(117, VideoUtility.getFrameCount(testVideo, null, 3));
        Assert.assertEquals(131, VideoUtility.getFrameCount(testVideo, null, 4));
        Assert.assertEquals(29, VideoUtility.getFrameCount(testVideo, null, 5));
        Assert.assertEquals(131, VideoUtility.getFrameCount(testVideo, null, 6));
        Assert.assertEquals(151, VideoUtility.getFrameCount(testVideo, null, 7));
        Assert.assertEquals(286, VideoUtility.getFrameCount(testVideo, null, 8));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, null, 9));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, null, 10));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, null, 11));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, null, 12));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, null, 13));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, null, 14));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo2, null, 1));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, null));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, null));
        
        //index
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, 0));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, 1));
        Assert.assertEquals(30, VideoUtility.getFrameCount(testVideo, 2));
        Assert.assertEquals(117, VideoUtility.getFrameCount(testVideo, 3));
        Assert.assertEquals(131, VideoUtility.getFrameCount(testVideo, 4));
        Assert.assertEquals(29, VideoUtility.getFrameCount(testVideo, 5));
        Assert.assertEquals(131, VideoUtility.getFrameCount(testVideo, 6));
        Assert.assertEquals(151, VideoUtility.getFrameCount(testVideo, 7));
        Assert.assertEquals(286, VideoUtility.getFrameCount(testVideo, 8));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, 9));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, 10));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, 11));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, 12));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, 13));
        Assert.assertEquals(3, VideoUtility.getFrameCount(testVideo, 14));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo2, 1));
        
        //invalid
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.VIDEO, 3));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.VIDEO, -1));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO, 6));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.AUDIO, -1));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE, 6));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.SUBTITLE, -1));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, VideoUtility.StreamType.DATA));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, null, 15));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, null, -1));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, 15));
        Assert.assertEquals(0, VideoUtility.getFrameCount(testVideo, -1));
        Assert.assertEquals(0, VideoUtility.getFrameCount(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO, 0));
        Assert.assertEquals(0, VideoUtility.getFrameCount(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO));
        Assert.assertEquals(0, VideoUtility.getFrameCount(new File(testResources, "fakeVideo.mp4"), 0));
        Assert.assertEquals(0, VideoUtility.getFrameCount(new File(testResources, "fakeVideo.mp4")));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getFrameCount(null, VideoUtility.StreamType.SUBTITLE, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getFrameCount(null, VideoUtility.StreamType.SUBTITLE));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getFrameCount(null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.getFrameCount(null));
    }
    
    /**
     * JUnit test of convertVideo.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#convertVideo(File, File)
     * @see VideoUtility#convertVideo(File, String)
     */
    @Test
    public void testConvertVideo() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSourceInitial = new File(testResources, "test.mkv");
        final File testSource = new File(testDir, "source.mkv");
        final File testOutput1 = new File(testDir, "test.mp4");
        final File testOutput2 = new File(testDir, "test.mov");
        final File testOutput3 = new File(testDir, "test.avi");
        final File testOutput4 = new File(testDir, "test1.mkv");
        final File testOutput5 = new File(testDir, "test1.mp4");
        final File testOutput6 = new File(testDir, "test1.mov");
        final File testOutput7 = new File(testDir, "test1.avi");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        List<VideoUtility.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSourceInitial.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSourceInitial));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSourceInitial));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSourceInitial));
        streams = VideoUtility.getStreams(testSourceInitial);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeSubtitles(testSourceInitial, testSource);
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(9, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        streams = VideoUtility.getStreams(testSource);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //standard
        VideoUtility.convertVideo(testSource, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(10, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        streams = VideoUtility.getStreams(testOutput1);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|h264|h264|aac|aac|aac|aac|aac|aac|bin_data",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testSource, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(10, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput2));
        streams = VideoUtility.getStreams(testOutput2);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
        }
        Assert.assertEquals("h264|h264|h264|aac|aac|aac|aac|aac|aac|bin_data",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testSource, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(9, VideoUtility.getStreamCount(testOutput3));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput3));
        streams = VideoUtility.getStreams(testOutput3);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNull(stream.getLanguage());
        }
        Assert.assertEquals("mpeg4|mpeg4|mpeg4|mp3|mp3|mp3|mp3|mp3|mp3",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testSource, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(9, VideoUtility.getStreamCount(testOutput4));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput4));
        streams = VideoUtility.getStreams(testOutput4);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //file type
        VideoUtility.convertVideo(testOutput4, "mp4");
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(10, VideoUtility.getStreamCount(testOutput5));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput5));
        streams = VideoUtility.getStreams(testOutput5);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|h264|h264|aac|aac|aac|aac|aac|aac|bin_data",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testOutput4, "mov");
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertEquals(10, VideoUtility.getStreamCount(testOutput6));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput6));
        streams = VideoUtility.getStreams(testOutput6);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
        }
        Assert.assertEquals("h264|h264|h264|aac|aac|aac|aac|aac|aac|bin_data",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testOutput4, "avi");
        Assert.assertTrue(testOutput7.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput7));
        Assert.assertEquals(9, VideoUtility.getStreamCount(testOutput7));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput7));
        streams = VideoUtility.getStreams(testOutput7);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNull(stream.getLanguage());
        }
        Assert.assertEquals("mpeg4|mpeg4|mpeg4|mp3|mp3|mp3|mp3|mp3|mp3",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        Assert.assertTrue(VideoUtility.convertVideo(testSourceInitial, testOutput1).contains(
                "Automatic encoder selection failed for output stream #0:9. Default encoder for format mp4 (codec none) is probably disabled. Please choose an encoder manually."));
        Assert.assertTrue(VideoUtility.convertVideo(testSourceInitial, testOutput2).contains(
                "Automatic encoder selection failed for output stream #0:9. Default encoder for format mov (codec none) is probably disabled. Please choose an encoder manually."));
        Assert.assertTrue(VideoUtility.convertVideo(testSourceInitial, testOutput3).contains(
                "Automatic encoder selection failed for output stream #0:9. Default encoder for format avi (codec none) is probably disabled. Please choose an encoder manually."));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.convertVideo(testSourceInitial, (File) null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.convertVideo(null, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.convertVideo(null, (File) null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.convertVideo(testSourceInitial, (String) null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.convertVideo(null, "mp4"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.convertVideo(null, (String) null));
    }
    
    /**
     * JUnit test of transcodeVideo.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#transcodeVideo(File, Map, File)
     */
    @Test
    public void testTranscodeVideo() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput1 = new File(testDir, "default.mkv");
        final File testOutput2 = new File(testDir, "test.mkv");
        final File testOutput3 = new File(testDir, "test.mp4");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        List<VideoUtility.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        streams = VideoUtility.getStreams(testSource);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //default
        VideoUtility.transcodeVideo(testSource, new HashMap<>(), testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        streams = VideoUtility.getStreams(testOutput1);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //standard
        VideoUtility.transcodeVideo(testSource, new HashMap<>() {{
            put("v:0", "libx265");
            put("v:1", "libx264");
            put("v:2", "mpeg4");
            put("a", "aac");
        }}, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput2));
        streams = VideoUtility.getStreams(testOutput2);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("hevc|h264|mpeg4|aac|aac|aac|aac|aac|aac|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //to mp4
        VideoUtility.transcodeVideo(testSource, new HashMap<>() {{
            put("v:0", "libx265");
            put("v:1", "libx264");
            put("v:2", "mpeg4");
            put("a:2", "libvorbis");
            put("a:4", "mp3");
            put("s", "mov_text");
        }}, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(16, VideoUtility.getStreamCount(testOutput3));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput3));
        streams = VideoUtility.getStreams(testOutput3);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("hevc|h264|mpeg4|mp3|aac|vorbis|aac|mp3|vorbis|mov_text|mov_text|mov_text|mov_text|mov_text|mov_text|bin_data",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        Assert.assertEquals("[*]" + fakeSource.getAbsolutePath() + ": No such file or directory",
                VideoUtility.transcodeVideo(fakeSource, new HashMap<>(), fakeOutput));
        Assert.assertTrue(VideoUtility.transcodeVideo(testSource, new HashMap<>(), fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        Assert.assertTrue(VideoUtility.transcodeVideo(testSource, new HashMap<>() {{
            put("test", "libx265");
        }}, fakeOutput).contains(
                "Invalid stream specifier: test."));
        Assert.assertTrue(VideoUtility.transcodeVideo(testSource, new HashMap<>() {{
            put("v:1", "test");
        }}, fakeOutput).contains(
                "[*]Unknown encoder 'test'"));
        Assert.assertTrue(VideoUtility.transcodeVideo(testSource, new HashMap<>() {{
            put("v:1", "");
        }}, fakeOutput).contains(
                "[*]0: Invalid argument"));
        Assert.assertTrue(VideoUtility.transcodeVideo(testSource, new HashMap<>() {{
            put("v:1", null);
        }}, fakeOutput).contains(
                "[*]Unknown encoder 'null'"));
        Assert.assertTrue(VideoUtility.transcodeVideo(testSource, new HashMap<>() {{
            put(null, null);
        }}, fakeOutput).contains(
                "Invalid stream specifier: null."));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.transcodeVideo(testSource, null, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.transcodeVideo(testSource, new HashMap<>(), null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.transcodeVideo(null, new HashMap<>(), fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.transcodeVideo(null, new HashMap<>(), null));
    }
    
    /**
     * JUnit test of mergeStreams.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#mergeStreams(List, File)
     */
    @Test
    public void testMergeStreams() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testSource1 = new File(testDir, "source.mkv");
        final File testSource2 = new File(testDir, "source.mp4");
        final File testSource3 = new File(testDir, "source.mp3");
        final File testSource4 = new File(testDir, "source.aac");
        final File testSource5 = new File(testDir, "source.ogg");
        final File testSource6 = new File(testDir, "source.srt");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        VideoUtility.extractStream(testSource, 1, testSource1);
        Assert.assertTrue(testSource1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource1));
        VideoUtility.extractStream(testSource, 2, testSource2);
        Assert.assertTrue(testSource2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource2));
        VideoUtility.extractStream(testSource, 3, testSource3);
        Assert.assertTrue(testSource3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource3));
        VideoUtility.extractStream(testSource, 6, testSource4);
        Assert.assertTrue(testSource4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource4));
        VideoUtility.extractStream(testSource, 8, testSource5);
        Assert.assertTrue(testSource5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource5));
        VideoUtility.extractStream(testSource, 12, testSource6);
        Assert.assertTrue(testSource6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource6));
        
        //merge
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                VideoUtility.getStreams(testSource).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.mergeStreams(Arrays.asList(testSource, testSource1, testSource3), testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(17, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|hevc|mp3",
                VideoUtility.getStreams(testOutput1).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //from scratch
        VideoUtility.mergeStreams(Arrays.asList(testSource1, testSource2, testSource3, testSource4, testSource5, testSource6), testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(6, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput2));
        Assert.assertEquals("hevc|mpeg4|mp3|aac|vorbis|subrip",
                VideoUtility.getStreams(testOutput2).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        Assert.assertEquals("[*]" + fakeSource.getAbsolutePath() + ": No such file or directory",
                VideoUtility.mergeStreams(Arrays.asList(fakeSource, testSource1, testSource2), fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.mergeStreams(Arrays.asList(testSource1, testSource2, null), fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.mergeStreams(Arrays.asList(testSource1, testSource2, testSource3), null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.mergeStreams(null, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.mergeStreams(null, null));
    }
    
    /**
     * JUnit test of addStream.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#addStream(File, File, VideoUtility.StreamType, int, File)
     * @see VideoUtility#addStream(File, File, VideoUtility.StreamType, File)
     * @see VideoUtility#addStream(File, File, int, File)
     */
    @Test
    public void testAddStream() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testSource1 = new File(testDir, "source.mkv");
        final File testSource2 = new File(testDir, "source.mp4");
        final File testSource3 = new File(testDir, "source.mp3");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File testOutput4 = new File(testDir, "test4.mkv");
        final File testOutput5 = new File(testDir, "test5.mkv");
        final File testOutput6 = new File(testDir, "test6.mkv");
        final File testOutput7 = new File(testDir, "test7.mkv");
        final File testOutput8 = new File(testDir, "test8.mkv");
        final File testOutput9 = new File(testDir, "test9.mkv");
        final File testOutput10 = new File(testDir, "test10.mkv");
        final File testOutput11 = new File(testDir, "test11.mkv");
        final File testOutput12 = new File(testDir, "test12.mkv");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        VideoUtility.extractStream(testSource, 1, testSource1);
        Assert.assertTrue(testSource1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource1));
        VideoUtility.extractStream(testSource, 2, testSource2);
        Assert.assertTrue(testSource2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource2));
        VideoUtility.extractStream(testSource, 3, testSource3);
        Assert.assertTrue(testSource3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource3));
        
        //merge, stream type and index
        VideoUtility.addStream(testSource, testSource, VideoUtility.StreamType.VIDEO, 1, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(16, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|hevc",
                VideoUtility.getStreams(testOutput1).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource, testSource, VideoUtility.StreamType.SUBTITLE, 3, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(16, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput2));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|subrip",
                VideoUtility.getStreams(testOutput2).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //merge, stream type
        VideoUtility.addStream(testSource, testSource, VideoUtility.StreamType.VIDEO, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(16, VideoUtility.getStreamCount(testOutput3));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput3));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|h264",
                VideoUtility.getStreams(testOutput3).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource, testSource, VideoUtility.StreamType.AUDIO, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(16, VideoUtility.getStreamCount(testOutput4));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput4));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|mp3",
                VideoUtility.getStreams(testOutput4).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //merge, index
        VideoUtility.addStream(testSource, testSource, 2, testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(16, VideoUtility.getStreamCount(testOutput5));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput5));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|mpeg4",
                VideoUtility.getStreams(testOutput5).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource, testSource, 5, testOutput6);
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertEquals(16, VideoUtility.getStreamCount(testOutput6));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput6));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|flac",
                VideoUtility.getStreams(testOutput6).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //from scratch, stream type and index
        VideoUtility.addStream(testSource1, testSource, VideoUtility.StreamType.VIDEO, 2, testOutput7);
        Assert.assertTrue(testOutput7.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput7));
        Assert.assertEquals(2, VideoUtility.getStreamCount(testOutput7));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput7));
        Assert.assertEquals("hevc|mpeg4",
                VideoUtility.getStreams(testOutput7).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource1, testSource2, VideoUtility.StreamType.VIDEO, 0, testOutput8);
        Assert.assertTrue(testOutput8.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput8));
        Assert.assertEquals(2, VideoUtility.getStreamCount(testOutput8));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput8));
        Assert.assertEquals("hevc|mpeg4",
                VideoUtility.getStreams(testOutput8).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //from scratch, stream type
        VideoUtility.addStream(testSource2, testSource, VideoUtility.StreamType.AUDIO, testOutput9);
        Assert.assertTrue(testOutput9.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput9));
        Assert.assertEquals(2, VideoUtility.getStreamCount(testOutput9));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput9));
        Assert.assertEquals("mpeg4|mp3",
                VideoUtility.getStreams(testOutput9).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource2, testSource3, VideoUtility.StreamType.AUDIO, testOutput10);
        Assert.assertTrue(testOutput10.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput10));
        Assert.assertEquals(2, VideoUtility.getStreamCount(testOutput10));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput10));
        Assert.assertEquals("mpeg4|mp3",
                VideoUtility.getStreams(testOutput10).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //from scratch, index
        VideoUtility.addStream(testSource3, testSource, 6, testOutput11);
        Assert.assertTrue(testOutput11.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput11));
        Assert.assertEquals(2, VideoUtility.getStreamCount(testOutput11));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput11));
        Assert.assertEquals("mp3|aac",
                VideoUtility.getStreams(testOutput11).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource3, testSource1, 0, testOutput12);
        Assert.assertTrue(testOutput12.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput12));
        Assert.assertEquals(2, VideoUtility.getStreamCount(testOutput12));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput12));
        Assert.assertEquals("mp3|hevc",
                VideoUtility.getStreams(testOutput12).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource1, VideoUtility.StreamType.VIDEO, 2, testOutput1).contains(
                "[*]Stream map '1:v:2' matches no streams."));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource1, VideoUtility.StreamType.AUDIO, testOutput1).contains(
                "[*]Stream map '1:a:0' matches no streams."));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource1, 3, fakeOutput).contains(
                "[*]Stream map '1:3' matches no streams."));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource1, null, 2, testOutput1).contains(
                "[*]Stream map '1:2' matches no streams."));
        Assert.assertTrue(VideoUtility.addStream(testSource, fakeSource, null, testOutput1).contains(
                "fakeTest.mkv: No such file or directory"));
        Assert.assertTrue(VideoUtility.addStream(testSource, fakeSource, VideoUtility.StreamType.VIDEO, 2, testOutput1).contains(
                "fakeTest.mkv: No such file or directory"));
        Assert.assertTrue(VideoUtility.addStream(testSource, fakeSource, VideoUtility.StreamType.AUDIO, testOutput1).contains(
                "fakeTest.mkv: No such file or directory"));
        Assert.assertTrue(VideoUtility.addStream(testSource, fakeSource, 3, testOutput1).contains(
                "fakeTest.mkv: No such file or directory"));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource, VideoUtility.StreamType.VIDEO, 2, fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource, VideoUtility.StreamType.AUDIO, fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource, 3, fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, null, VideoUtility.StreamType.VIDEO, 2, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, null, VideoUtility.StreamType.AUDIO, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, null, 3, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, testSource, VideoUtility.StreamType.VIDEO, 2, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, testSource, VideoUtility.StreamType.AUDIO, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, testSource, 3, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, testSource, VideoUtility.StreamType.VIDEO, 2, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, testSource, VideoUtility.StreamType.AUDIO, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, testSource, 3, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, null, VideoUtility.StreamType.VIDEO, 2, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, null, VideoUtility.StreamType.AUDIO, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, null, 3, null));
    }
    
    /**
     * JUnit test of addVideoStream.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#addVideoStream(File, File, File)
     */
    @Test
    public void testAddVideoStream() throws Exception {
        PowerMockito.mockStatic(VideoUtility.class);
        PowerMockito.doCallRealMethod().when(VideoUtility.class, "addVideoStream", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testStream = new File(Filesystem.createTemporaryDirectory(), "video.mkv");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        VideoUtility.addVideoStream(testSource, testStream, testOutput);
        PowerMockito.verifyStatic(VideoUtility.class);
        VideoUtility.addStream(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(testStream), ArgumentMatchers.eq(VideoUtility.StreamType.VIDEO), ArgumentMatchers.eq(testOutput));
    }
    
    /**
     * JUnit test of addAudioStream.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#addAudioStream(File, File, File)
     */
    @Test
    public void testAddAudioStream() throws Exception {
        PowerMockito.mockStatic(VideoUtility.class);
        PowerMockito.doCallRealMethod().when(VideoUtility.class, "addAudioStream", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testStream = new File(Filesystem.createTemporaryDirectory(), "audio.aac");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        VideoUtility.addAudioStream(testSource, testStream, testOutput);
        PowerMockito.verifyStatic(VideoUtility.class);
        VideoUtility.addStream(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(testStream), ArgumentMatchers.eq(VideoUtility.StreamType.AUDIO), ArgumentMatchers.eq(testOutput));
    }
    
    /**
     * JUnit test of addSubtitles.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#addSubtitles(File, File, File)
     */
    @Test
    public void testAddSubtitles() throws Exception {
        PowerMockito.mockStatic(VideoUtility.class);
        PowerMockito.doCallRealMethod().when(VideoUtility.class, "addSubtitles", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testStream = new File(Filesystem.createTemporaryDirectory(), "subtitle.srt");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        VideoUtility.addSubtitles(testSource, testStream, testOutput);
        PowerMockito.verifyStatic(VideoUtility.class);
        VideoUtility.addStream(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(testStream), ArgumentMatchers.eq(VideoUtility.StreamType.SUBTITLE), ArgumentMatchers.eq(testOutput));
    }
    
    /**
     * JUnit test of addDataStream.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#addDataStream(File, File, File)
     */
    @Test
    public void testAddDataStream() throws Exception {
        PowerMockito.mockStatic(VideoUtility.class);
        PowerMockito.doCallRealMethod().when(VideoUtility.class, "addDataStream", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testStream = new File(Filesystem.createTemporaryDirectory(), "data.bin");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        VideoUtility.addDataStream(testSource, testStream, testOutput);
        PowerMockito.verifyStatic(VideoUtility.class);
        VideoUtility.addStream(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(testStream), ArgumentMatchers.eq(VideoUtility.StreamType.DATA), ArgumentMatchers.eq(testOutput));
    }
    
    /**
     * JUnit test of removeStream.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#removeStream(File, VideoUtility.StreamType, int, File)
     * @see VideoUtility#removeStream(File, int, File)
     */
    @Test
    public void testRemoveStream() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File testOutput4 = new File(testDir, "test4.mkv");
        final File testOutput5 = new File(testDir, "test5.mkv");
        final File testOutput6 = new File(testDir, "test6.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mkv");
        List<VideoUtility.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        streams = VideoUtility.getStreams(testSource);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //stream and index
        VideoUtility.removeStream(testSource, VideoUtility.StreamType.VIDEO, 1, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(14, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        streams = VideoUtility.getStreams(testOutput1);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStream(testOutput1, VideoUtility.StreamType.AUDIO, 4, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(13, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput2));
        streams = VideoUtility.getStreams(testOutput2);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|flac|aac|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStream(testOutput2, VideoUtility.StreamType.SUBTITLE, 2, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(12, VideoUtility.getStreamCount(testOutput3));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput3));
        streams = VideoUtility.getStreams(testOutput3);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|flac|aac|vorbis|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //index
        VideoUtility.removeStream(testOutput3, 9, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(11, VideoUtility.getStreamCount(testOutput4));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput4));
        streams = VideoUtility.getStreams(testOutput4);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|flac|aac|vorbis|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStream(testOutput4, 4, testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(10, VideoUtility.getStreamCount(testOutput5));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput5));
        streams = VideoUtility.getStreams(testOutput5);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|aac|vorbis|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStream(testOutput5, 1, testOutput6);
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertEquals(9, VideoUtility.getStreamCount(testOutput6));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput6));
        streams = VideoUtility.getStreams(testOutput6);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mp3|aac|aac|vorbis|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        streams = VideoUtility.getStreams(testSource);
        VideoUtility.removeStream(testSource, VideoUtility.StreamType.VIDEO, 3, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, VideoUtility.StreamType.VIDEO, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, VideoUtility.StreamType.AUDIO, 6, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, VideoUtility.StreamType.AUDIO, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, VideoUtility.StreamType.SUBTITLE, 6, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, VideoUtility.StreamType.SUBTITLE, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, VideoUtility.StreamType.DATA, 0, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, VideoUtility.StreamType.DATA, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, null, 15, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, null, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, 15, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                VideoUtility.getStreams(fakeOutput).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        Assert.assertTrue(VideoUtility.removeStream(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO, 0, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        Assert.assertTrue(VideoUtility.removeStream(new File(testResources, "fakeVideo.mp4"), 0, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(null, VideoUtility.StreamType.SUBTITLE, 0, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(null, 1, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(testSource, VideoUtility.StreamType.SUBTITLE, 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(testSource, 1, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(null, VideoUtility.StreamType.SUBTITLE, 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(null, 1, null));
    }
    
    /**
     * JUnit test of removeStreamType.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#removeStreamType(File, VideoUtility.StreamType, File)
     */
    @Test
    public void testRemoveStreamType() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File testOutput4 = new File(testDir, "test4.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mkv");
        List<VideoUtility.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        streams = VideoUtility.getStreams(testSource);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //stream and index
        VideoUtility.removeStreamType(testSource, VideoUtility.StreamType.DATA, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        streams = VideoUtility.getStreams(testOutput1);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreamType(testOutput1, VideoUtility.StreamType.SUBTITLE, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(9, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput2));
        streams = VideoUtility.getStreams(testOutput2);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreamType(testOutput2, VideoUtility.StreamType.AUDIO, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(3, VideoUtility.getStreamCount(testOutput3));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput3));
        streams = VideoUtility.getStreams(testOutput3);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreamType(testSource, VideoUtility.StreamType.VIDEO, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(12, VideoUtility.getStreamCount(testOutput4));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput4));
        streams = VideoUtility.getStreams(testOutput4);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        Assert.assertTrue(VideoUtility.removeStreamType(testOutput3, VideoUtility.StreamType.VIDEO, fakeOutput).contains(
                "[*]Output file #0 does not contain any stream"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreamType(testSource, null, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreamType(null, VideoUtility.StreamType.SUBTITLE, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreamType(testSource, VideoUtility.StreamType.SUBTITLE, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreamType(null, VideoUtility.StreamType.SUBTITLE, null));
    }
    
    /**
     * JUnit test of removeVideoStreams.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#removeVideoStreams(File, File)
     */
    @Test
    public void testRemoveVideoStreams() throws Exception {
        PowerMockito.mockStatic(VideoUtility.class);
        PowerMockito.doCallRealMethod().when(VideoUtility.class, "removeVideoStreams", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        VideoUtility.removeVideoStreams(testSource, testOutput);
        PowerMockito.verifyStatic(VideoUtility.class);
        VideoUtility.removeStreamType(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(VideoUtility.StreamType.VIDEO), ArgumentMatchers.eq(testOutput));
    }
    
    /**
     * JUnit test of removeAudioStreams.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#removeAudioStreams(File, File)
     */
    @Test
    public void testRemoveAudioStreams() throws Exception {
        PowerMockito.mockStatic(VideoUtility.class);
        PowerMockito.doCallRealMethod().when(VideoUtility.class, "removeAudioStreams", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        VideoUtility.removeAudioStreams(testSource, testOutput);
        PowerMockito.verifyStatic(VideoUtility.class);
        VideoUtility.removeStreamType(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(VideoUtility.StreamType.AUDIO), ArgumentMatchers.eq(testOutput));
    }
    
    /**
     * JUnit test of removeSubtitles.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#removeSubtitles(File, File)
     */
    @Test
    public void testRemoveSubtitles() throws Exception {
        PowerMockito.mockStatic(VideoUtility.class);
        PowerMockito.doCallRealMethod().when(VideoUtility.class, "removeSubtitles", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        VideoUtility.removeSubtitles(testSource, testOutput);
        PowerMockito.verifyStatic(VideoUtility.class);
        VideoUtility.removeStreamType(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(VideoUtility.StreamType.SUBTITLE), ArgumentMatchers.eq(testOutput));
    }
    
    /**
     * JUnit test of removeDataStreams.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#removeDataStreams(File, File)
     */
    @Test
    public void testRemoveDataStreams() throws Exception {
        PowerMockito.mockStatic(VideoUtility.class);
        PowerMockito.doCallRealMethod().when(VideoUtility.class, "removeDataStreams", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        VideoUtility.removeDataStreams(testSource, testOutput);
        PowerMockito.verifyStatic(VideoUtility.class);
        VideoUtility.removeStreamType(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(VideoUtility.StreamType.DATA), ArgumentMatchers.eq(testOutput));
    }
    
    /**
     * JUnit test of removeStreams.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#removeStreams(File, List, File)
     */
    @Test
    public void testRemoveStreams() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File testOutput4 = new File(testDir, "test4.mkv");
        final File testOutput5 = new File(testDir, "test5.mkv");
        final File testOutput6 = new File(testDir, "test6.mkv");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        
        //standard
        VideoUtility.removeStreams(testSource, Arrays.asList("v:2", "a:2", "s"), testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(7, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        Assert.assertEquals("h264|hevc|mp3|aac|aac|opus|vorbis",
                VideoUtility.getStreams(testOutput1).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreams(testSource, Arrays.asList("0:2", "0:5"), testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(13, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput2));
        Assert.assertEquals("h264|hevc|mp3|aac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                VideoUtility.getStreams(testOutput2).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreams(testSource, Arrays.asList("0:2", "a:2", "s"), testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(7, VideoUtility.getStreamCount(testOutput3));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput3));
        Assert.assertEquals("h264|hevc|mp3|aac|aac|opus|vorbis",
                VideoUtility.getStreams(testOutput3).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreams(testSource, Collections.emptyList(), testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput4));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput4));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                VideoUtility.getStreams(testOutput4).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        VideoUtility.removeStreams(testSource, Arrays.asList("v:3", "a:6", "s:6"), testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput5));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput5));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                VideoUtility.getStreams(testOutput5).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreams(testSource, Arrays.asList("v:-1", "a:-1", "s:-1"), testOutput6);
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput6));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput6));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                VideoUtility.getStreams(testOutput6).stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(VideoUtility.removeStreams(testSource, Arrays.asList("v:0", "a:0"), fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        Assert.assertTrue(VideoUtility.removeStreams(fakeSource, Arrays.asList("v:0", "a:0"), testOutput1).contains(
                "fakeTest.mkv: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreams(testSource, Arrays.asList("v:0", "a:0"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreams(null, Arrays.asList("v:0", "a:0"), testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreams(null, Arrays.asList("v:0", "a:0"), null));
    }
    
    /**
     * JUnit test of stripMetadataAndChapters.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#stripMetadataAndChapters(File, boolean, boolean, File)
     */
    @Test
    public void testStripMetadataAndChapters() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        for (VideoUtility.StreamMetadata stream : VideoUtility.getStreams(testSource)) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        
        //metadata
        VideoUtility.stripMetadataAndChapters(testSource, true, false, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        for (VideoUtility.StreamMetadata stream : VideoUtility.getStreams(testOutput1)) {
            Assert.assertNull(stream.getTitle());
            Assert.assertNull(stream.getLanguage());
            Assert.assertEquals(1, stream.getTags().size());
            Assert.assertTrue(stream.containsTag("duration"));
        }
        
        //chapters
        VideoUtility.stripMetadataAndChapters(testSource, false, true, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput2));
        for (VideoUtility.StreamMetadata stream : VideoUtility.getStreams(testOutput2)) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        
        //metadata and chapters
        VideoUtility.stripMetadataAndChapters(testSource, true, true, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(0, VideoUtility.getChapterCount(testOutput2));
        for (VideoUtility.StreamMetadata stream : VideoUtility.getStreams(testOutput2)) {
            Assert.assertNull(stream.getTitle());
            Assert.assertNull(stream.getLanguage());
            Assert.assertEquals(1, stream.getTags().size());
            Assert.assertTrue(stream.containsTag("duration"));
        }
        
        //invalid
        Assert.assertEquals("[*]" + fakeSource.getAbsolutePath() + ": No such file or directory",
                VideoUtility.stripMetadataAndChapters(fakeSource, true, true, fakeOutput));
        Assert.assertTrue(VideoUtility.stripMetadataAndChapters(testSource, true, true, fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.stripMetadataAndChapters(testSource, true, true, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.stripMetadataAndChapters(null, true, true, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.stripMetadataAndChapters(null, true, true, null));
    }
    
    /**
     * JUnit test of cutVideo.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#cutVideo(File, String, String, File)
     * @see VideoUtility#cutVideo(File, long, long, File)
     */
    @Test
    public void testCutVideo() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testSource2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mp4");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File testOutput4 = new File(testDir, "test4.mp4");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "test.mp4");
        FFmpeg.ffmpeg(testSource, "-y -map 0:0 -c copy -map_chapters 0", testSource2);
        Assert.assertTrue(testSource2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource2));
        
        //initial
        Assert.assertEquals(3067L, VideoUtility.getDuration(testSource));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        
        //timestamp
        VideoUtility.cutVideo(testSource, "00:00:00.000", "00:00:01.000", testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(1239L, VideoUtility.getDuration(testOutput1));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(1, VideoUtility.getChapterCount(testOutput1));
        VideoUtility.cutVideo(testSource2, "00:00:00.000", "00:00:01.000", testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(1000L, VideoUtility.getDuration(testOutput2));
        Assert.assertEquals(1, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(1, VideoUtility.getChapterCount(testOutput2));
        
        //milliseconds
        VideoUtility.cutVideo(testSource, 500, 2500, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(2416, VideoUtility.getDuration(testOutput3));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput3));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput3));
        VideoUtility.cutVideo(testSource2, 500, 2500, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(2000L, VideoUtility.getDuration(testOutput4));
        Assert.assertEquals(2, VideoUtility.getStreamCount(testOutput4));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput4));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.cutVideo(testSource, null, "00:00:01.000", testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.cutVideo(testSource, "00:00:00.000", null, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.cutVideo(testSource, null, null, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.cutVideo(null, "00:00:00.000", "00:00:01.000", testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.cutVideo(testSource, "00:00:00.000", "00:00:01.000", null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.cutVideo(null, "00:00:00.000", "00:00:01.000", null));
    }
    
    /**
     * JUnit test of scaleVideo.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#scaleVideo(File, int, int, File)
     */
    @Test
    public void testScaleVideo() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        List<VideoUtility.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        streams = VideoUtility.getStreams(testSource);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
            if (stream.getStreamType() == VideoUtility.StreamType.VIDEO) {
                Assert.assertEquals(320, stream.getVideoMetadata().getWidth());
                Assert.assertEquals(240, stream.getVideoMetadata().getHeight());
            }
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //scale down
        VideoUtility.scaleVideo(testSource, 120, 100, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        streams = VideoUtility.getStreams(testOutput1);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
            if (stream.getStreamType() == VideoUtility.StreamType.VIDEO) {
                Assert.assertEquals(120, stream.getVideoMetadata().getWidth());
                Assert.assertEquals(100, stream.getVideoMetadata().getHeight());
            }
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //scale up
        VideoUtility.scaleVideo(testSource, 1600, 1200, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput2));
        streams = VideoUtility.getStreams(testOutput2);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
            if (stream.getStreamType() == VideoUtility.StreamType.VIDEO) {
                Assert.assertEquals(1600, stream.getVideoMetadata().getWidth());
                Assert.assertEquals(1200, stream.getVideoMetadata().getHeight());
            }
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        VideoUtility.scaleVideo(testSource, -1, -1, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        VideoUtility.getStreams(testOutput3).stream().filter(e -> e.getStreamType() == VideoUtility.StreamType.VIDEO).forEach(e -> {
            Assert.assertEquals(320, e.getVideoMetadata().getWidth());
            Assert.assertEquals(240, e.getVideoMetadata().getHeight());
        });
        Assert.assertTrue(VideoUtility.scaleVideo(testSource, 120, 100, fakeOutput).contains(
                "Error selecting an encoder for stream 0:9"));
        Assert.assertTrue(VideoUtility.scaleVideo(fakeSource, 120, 100, fakeOutput).contains(
                "fakeTest.mkv: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.scaleVideo(testSource, 120, 100, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.scaleVideo(null, 120, 100, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.scaleVideo(null, 120, 100, null));
    }
    
    /**
     * JUnit test of adjustQuality.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#adjustQuality(File, int, File)
     */
    @Test
    public void testAdjustQuality() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File testOutput4 = new File(testDir, "test4.mkv");
        final File testOutput5 = new File(testDir, "test5.mkv");
        final File testOutput6 = new File(testDir, "test6.mkv");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        List<VideoUtility.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        streams = VideoUtility.getStreams(testSource);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //low quality
        VideoUtility.adjustQuality(testSource, 40, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertTrue(Filesystem.sizeCompare(testOutput1, testSource) < 0);
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput1));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput1));
        streams = VideoUtility.getStreams(testOutput1);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //high quality
        VideoUtility.adjustQuality(testSource, 4, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertTrue(Filesystem.sizeCompare(testOutput2, testSource) < 0);
        Assert.assertTrue(Filesystem.sizeCompare(testOutput2, testOutput1) > 0);
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput2));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput2));
        streams = VideoUtility.getStreams(testOutput2);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //limits
        VideoUtility.adjustQuality(testSource, 0, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertTrue(Filesystem.sizeCompare(testOutput3, testSource) < 0);
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput3));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput3));
        streams = VideoUtility.getStreams(testOutput3);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.adjustQuality(testSource, 51, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertTrue(Filesystem.sizeCompare(testOutput4, testSource) < 0);
        Assert.assertEquals(15, VideoUtility.getStreamCount(testOutput4));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testOutput4));
        streams = VideoUtility.getStreams(testOutput4);
        for (VideoUtility.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(VideoUtility.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        TestUtils.assertNoException(() ->
                VideoUtility.adjustQuality(testSource, -1, testOutput5));
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        TestUtils.assertNoException(() ->
                VideoUtility.adjustQuality(testSource, 52, testOutput6));
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertTrue(VideoUtility.adjustQuality(testSource, 24, fakeOutput).contains(
                "Error selecting an encoder for stream 0:9"));
        Assert.assertTrue(VideoUtility.adjustQuality(fakeSource, 24, fakeOutput).contains(
                "fakeTest.mkv: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.adjustQuality(testSource, 24, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.adjustQuality(null, 24, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.adjustQuality(null, 24, null));
    }
    
    /**
     * JUnit test of extractStream.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#extractStream(File, VideoUtility.StreamType, int, File)
     * @see VideoUtility#extractStream(File, VideoUtility.StreamType, File)
     * @see VideoUtility#extractStream(File, int, File)
     */
    @Test
    public void testExtractStream() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final List<VideoUtility.StreamMetadata> sourceStreams = VideoUtility.getStreams(testSource);
        VideoUtility.StreamMetadata sourceStream;
        File output;
        List<VideoUtility.StreamMetadata> outputStreams;
        VideoUtility.StreamMetadata outputStream;
        final File fakeOutput = new File(testDir, "fakeTest.mkv");
        
        //initial
        Assert.assertEquals(15, VideoUtility.getStreamCount(testSource));
        Assert.assertEquals(3, VideoUtility.getChapterCount(testSource));
        
        //index
        for (int i = 0; i < sourceStreams.size(); i++) {
            output = new File(testDir, i + "test.mkv");
            VideoUtility.extractStream(testSource, i, output);
            Assert.assertTrue(output.exists());
            Assert.assertEquals(1, VideoUtility.getStreamCount(output));
            Assert.assertEquals(0, VideoUtility.getChapterCount(output));
            sourceStream = sourceStreams.get(i);
            outputStream = VideoUtility.getStream(output, 0);
            Assert.assertEquals(sourceStream.getCodecName(), outputStream.getCodecName());
            Assert.assertEquals(sourceStream.getTitle(), outputStream.getTitle());
            Assert.assertEquals(sourceStream.getLanguage(), outputStream.getLanguage());
        }
        
        //stream type
        for (int i = -1; i < VideoUtility.getStreamCount(testSource, VideoUtility.StreamType.VIDEO); i++) {
            output = new File(testDir, "v" + i + "test.mkv");
            if (i >= 0) {
                VideoUtility.extractStream(testSource, VideoUtility.StreamType.VIDEO, i, output);
            } else {
                VideoUtility.extractStream(testSource, VideoUtility.StreamType.VIDEO, output);
            }
            Assert.assertTrue(output.exists());
            Assert.assertEquals(1, VideoUtility.getStreamCount(output));
            Assert.assertEquals(0, VideoUtility.getChapterCount(output));
            sourceStream = VideoUtility.getStream(testSource, VideoUtility.StreamType.VIDEO, Math.max(i, 0));
            outputStream = VideoUtility.getStream(output, 0);
            Assert.assertEquals(sourceStream.getCodecName(), outputStream.getCodecName());
            Assert.assertEquals(sourceStream.getTitle(), outputStream.getTitle());
            Assert.assertEquals(sourceStream.getLanguage(), outputStream.getLanguage());
        }
        for (int i = -1; i < VideoUtility.getStreamCount(testSource, VideoUtility.StreamType.AUDIO); i++) {
            output = new File(testDir, "a" + i + "test.mkv");
            if (i >= 0) {
                VideoUtility.extractStream(testSource, VideoUtility.StreamType.AUDIO, i, output);
            } else {
                VideoUtility.extractStream(testSource, VideoUtility.StreamType.AUDIO, output);
            }
            Assert.assertTrue(output.exists());
            Assert.assertEquals(1, VideoUtility.getStreamCount(output));
            Assert.assertEquals(0, VideoUtility.getChapterCount(output));
            sourceStream = VideoUtility.getStream(testSource, VideoUtility.StreamType.AUDIO, Math.max(i, 0));
            outputStream = VideoUtility.getStream(output, 0);
            Assert.assertEquals(sourceStream.getCodecName(), outputStream.getCodecName());
            Assert.assertEquals(sourceStream.getTitle(), outputStream.getTitle());
            Assert.assertEquals(sourceStream.getLanguage(), outputStream.getLanguage());
        }
        for (int i = -1; i < VideoUtility.getStreamCount(testSource, VideoUtility.StreamType.SUBTITLE); i++) {
            output = new File(testDir, "s" + i + "test.mkv");
            if (i >= 0) {
                VideoUtility.extractStream(testSource, VideoUtility.StreamType.SUBTITLE, i, output);
            } else {
                VideoUtility.extractStream(testSource, VideoUtility.StreamType.SUBTITLE, output);
            }
            Assert.assertTrue(output.exists());
            Assert.assertEquals(1, VideoUtility.getStreamCount(output));
            Assert.assertEquals(0, VideoUtility.getChapterCount(output));
            sourceStream = VideoUtility.getStream(testSource, VideoUtility.StreamType.SUBTITLE, Math.max(i, 0));
            outputStream = VideoUtility.getStream(output, 0);
            Assert.assertEquals(sourceStream.getCodecName(), outputStream.getCodecName());
            Assert.assertEquals(sourceStream.getTitle(), outputStream.getTitle());
            Assert.assertEquals(sourceStream.getLanguage(), outputStream.getLanguage());
        }
        
        //to file type
        output = new File(testDir, "test.mp4");
        VideoUtility.extractStream(testSource, 0, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        Assert.assertEquals(0, VideoUtility.getChapterCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("h264", outputStreams.get(0).getCodecName());
        Assert.assertNull(outputStreams.get(0).getTitle());
        Assert.assertEquals("eng", outputStreams.get(0).getLanguage());
        output = new File(testDir, "test.avi");
        VideoUtility.extractStream(testSource, 1, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        Assert.assertEquals(0, VideoUtility.getChapterCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("rawvideo", outputStreams.get(0).getCodecName());
        Assert.assertEquals("Green", outputStreams.get(0).getTitle());
        Assert.assertNull(outputStreams.get(0).getLanguage());
        output = new File(testDir, "test.m4v");
        VideoUtility.extractStream(testSource, 2, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        Assert.assertEquals(0, VideoUtility.getChapterCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("mpeg4", outputStreams.get(0).getCodecName());
        Assert.assertNull(outputStreams.get(0).getTitle());
        Assert.assertEquals("rus", outputStreams.get(0).getLanguage());
        output = new File(testDir, "test.mp3");
        VideoUtility.extractStream(testSource, 3, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("mp3", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.aac");
        VideoUtility.extractStream(testSource, 4, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("aac", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.flac");
        VideoUtility.extractStream(testSource, 5, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("flac", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.m4a");
        VideoUtility.extractStream(testSource, 6, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("aac", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.opus");
        VideoUtility.extractStream(testSource, 7, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("opus", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.ogg");
        VideoUtility.extractStream(testSource, 8, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("vorbis", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test1.srt");
        VideoUtility.extractStream(testSource, 9, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test2.srt");
        VideoUtility.extractStream(testSource, 10, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test3.srt");
        VideoUtility.extractStream(testSource, 11, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test4.srt");
        VideoUtility.extractStream(testSource, 12, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test5.srt");
        VideoUtility.extractStream(testSource, 13, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test6.srt");
        VideoUtility.extractStream(testSource, 14, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, VideoUtility.getStreamCount(output));
        outputStreams = VideoUtility.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        
        //invalid
        Assert.assertTrue(VideoUtility.extractStream(testSource, VideoUtility.StreamType.VIDEO, 3, fakeOutput).contains(
                "[*]Stream map '0:v:3' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, VideoUtility.StreamType.VIDEO, -1, fakeOutput).contains(
                "[*]Stream map '0:v:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, VideoUtility.StreamType.AUDIO, 6, fakeOutput).contains(
                "[*]Stream map '0:a:6' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, VideoUtility.StreamType.AUDIO, -1, fakeOutput).contains(
                "[*]Stream map '0:a:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, VideoUtility.StreamType.SUBTITLE, 6, fakeOutput).contains(
                "[*]Stream map '0:s:6' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, VideoUtility.StreamType.SUBTITLE, -1, fakeOutput).contains(
                "[*]Stream map '0:s:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, VideoUtility.StreamType.DATA, fakeOutput).contains(
                "[*]Stream map '0:d:0' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, null, 15, fakeOutput).contains(
                "[*]Stream map '0:15' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, null, -1, fakeOutput).contains(
                "[*]Stream map '0:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, 15, fakeOutput).contains(
                "[*]Stream map '0:15' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, -1, fakeOutput).contains(
                "[*]Stream map '0:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO, 0, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        Assert.assertTrue(VideoUtility.extractStream(new File(testResources, "fakeVideo.mp4"), VideoUtility.StreamType.VIDEO, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        Assert.assertTrue(VideoUtility.extractStream(new File(testResources, "fakeVideo.mp4"), 0, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, VideoUtility.StreamType.SUBTITLE, 0, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, VideoUtility.StreamType.SUBTITLE, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, 1, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(testSource, VideoUtility.StreamType.SUBTITLE, 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(testSource, VideoUtility.StreamType.SUBTITLE, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(testSource, 1, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, VideoUtility.StreamType.SUBTITLE, 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, VideoUtility.StreamType.SUBTITLE, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, 1, null));
    }
    
    /**
     * JUnit test of encodeFramesToVideo.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#encodeFramesToVideo(List, List, File)
     * @see VideoUtility#encodeFramesToVideo(File, String, double, File)
     * @see VideoUtility#encodeFramesToVideo(List, double, File)
     */
    @Test
    public void testEncodeFramesToVideo() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File testOutput4 = new File(testDir, "test4.mkv");
        final File testOutput5 = new File(testDir, "test5.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mkv");
        
        //prepare
        final File frameDir = Filesystem.createTemporaryDirectory();
        final List<File> frames = new ArrayList<>();
        final List<File> badFrames = new ArrayList<>();
        final List<Long> durations = new ArrayList<>();
        long totalDuration = 0;
        long totalBadDuration = 0;
        for (int i = 0; i < 30; i++) {
            BufferedImage image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            DrawUtility.setColor(graphics, new Color((i / 30.0f), 0, 0));
            DrawUtility.fillRect(graphics, new IntVector(0, 0), new IntVector(320, 240));
            File frame = new File(frameDir, "frame_" + StringUtility.padZero(i, 3) + ".png");
            ImageUtility.saveImage(image, frame);
            frames.add(frame);
            final long duration = MathUtility.random(100L, 1000L);
            durations.add(duration);
            totalDuration += duration;
            if (i >= 15) {
                File badFrame = new File(frameDir, "frame_" + StringUtility.padZero(i, 3) + ".jpg");
                ImageUtility.saveImage(image, badFrame);
                badFrames.add(badFrame);
            } else {
                badFrames.add(frame);
                totalBadDuration += ((i == 14) ? 0 : duration);
            }
            if (MathUtility.coinFlip()) {
                ImageUtility.saveImage(image, new File(frameDir, "distraction_" + UUID.randomUUID().toString() + ".png"));
            }
        }
        Filesystem.writeStringToFile(new File(frameDir, "distraction.txt"), "test");
        
        //name pattern and fps
        VideoUtility.encodeFramesToVideo(frameDir, "frame_%03d.png", 10, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(30L, VideoUtility.getFrameCount(testOutput1));
        Assert.assertEquals(3000L, VideoUtility.getDuration(testOutput1));
        
        //frame list and fps
        VideoUtility.encodeFramesToVideo(frames, 5, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(30L, VideoUtility.getFrameCount(testOutput2));
        Assert.assertEquals(6000L, VideoUtility.getDuration(testOutput2));
        
        //frame list and durations
        VideoUtility.encodeFramesToVideo(frames, durations, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(31L, VideoUtility.getFrameCount(testOutput3));
        Assert.assertTrue(Math.abs(totalDuration - VideoUtility.getDuration(testOutput3)) < (totalDuration * 0.01));
        
        //mixed image types
        VideoUtility.encodeFramesToVideo(badFrames, 10, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(15L, VideoUtility.getFrameCount(testOutput4));
        Assert.assertEquals(1500L, VideoUtility.getDuration(testOutput4));
        VideoUtility.encodeFramesToVideo(badFrames, durations, testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(15L, VideoUtility.getFrameCount(testOutput5));
        System.out.println(totalBadDuration);
        System.out.println(VideoUtility.getDuration(testOutput5));
        Assert.assertTrue(Math.abs(totalBadDuration - VideoUtility.getDuration(testOutput5)) < (totalBadDuration * 0.01));
        
        //invalid
        Assert.assertTrue(VideoUtility.encodeFramesToVideo(frameDir, "frame_%03d.png", -1, fakeOutput).contains(
                "frame_%03d.png: Invalid argument"));
        Assert.assertTrue(VideoUtility.encodeFramesToVideo(frameDir, "fake_%03d.png", 10, fakeOutput).contains(
                "fake_%03d.png: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.encodeFramesToVideo(frameDir, null, 10, fakeOutput));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index: 0", () ->
                VideoUtility.encodeFramesToVideo(Collections.emptyList(), 10, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.encodeFramesToVideo(null, 10, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.encodeFramesToVideo(frames, null, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.encodeFramesToVideo(null, null, fakeOutput));
        Assert.assertTrue(VideoUtility.encodeFramesToVideo(null, "frame_%03d.png", 10, fakeOutput).contains(
                "frame_%03d.png: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.encodeFramesToVideo(frameDir, "frame_%03d.png", 10, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.encodeFramesToVideo(null, "frame_%03d.png", 10, null));
    }
    
    /**
     * JUnit test of decodeFramesFromVideo.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#decodeFramesFromVideo(File, int, double, File, String)
     * @see VideoUtility#decodeFramesFromVideo(File, double, File, String)
     */
    @Test
    public void testDecodeFramesFromVideo() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testDir, "test.mkv");
        final File testSourceRed = new File(testDir, "testRed.mkv");
        final File testSourceGreen = new File(testDir, "testGreen.mkv");
        final File testSourceBlue = new File(testDir, "testBlue.mkv");
        final File testOutput1 = Filesystem.createTemporaryDirectory();
        final File testOutput2 = Filesystem.createTemporaryDirectory();
        final File testOutput3 = Filesystem.createTemporaryDirectory();
        final File testOutput4 = Filesystem.createTemporaryDirectory();
        final File testOutput5 = Filesystem.createTemporaryDirectory();
        final File fakeSource = new File(testDir, "fakeTest.mkv");
        final File fakeOutput = Filesystem.createTemporaryDirectory();
        
        //prepare
        final File frameDir = Filesystem.createTemporaryDirectory();
        for (int i = 1; i <= 30; i++) {
            for (int j = 1; j <= 3; j++) {
                BufferedImage image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = image.createGraphics();
                DrawUtility.setColor(graphics, new Color(((j == 1) ? ((i - 1) / 30.0f) : 0), ((j == 2) ? ((i - 1) / 30.0f) : 0), ((j == 3) ? ((i - 1) / 30.0f) : 0)));
                DrawUtility.fillRect(graphics, new IntVector(0, 0), new IntVector(320, 240));
                File frame = new File(frameDir, "frame" + j + "_" + StringUtility.padZero(i, 3) + ".png");
                ImageUtility.saveImage(image, frame);
            }
        }
        VideoUtility.encodeFramesToVideo(frameDir, "frame1_%03d.png", 10, testSourceRed);
        VideoUtility.encodeFramesToVideo(frameDir, "frame2_%03d.png", 10, testSourceGreen);
        VideoUtility.encodeFramesToVideo(frameDir, "frame3_%03d.png", 10, testSourceBlue);
        VideoUtility.mergeStreams(Arrays.asList(testSourceRed, testSourceGreen, testSourceBlue), testSource);
        
        //default
        VideoUtility.decodeFramesFromVideo(testSourceRed, 10, testOutput1, "frame_%03d.png");
        Assert.assertEquals(30, Filesystem.getFiles(testOutput1).size());
        for (int i = 1; i <= 30; i++) {
            Assert.assertTrue(Math.abs(
                    new Color(ImageUtility.loadImage(new File(frameDir, "frame1_" + StringUtility.padZero(i, 3) + ".png")).getRGB(160, 120)).getRed() -
                            new Color(ImageUtility.loadImage(new File(testOutput1, "frame_" + StringUtility.padZero(i, 3) + ".png")).getRGB(160, 120)).getRed()) <= 1);
        }
        VideoUtility.decodeFramesFromVideo(testSourceRed, 20, testOutput2, "frame_%03d.png");
        Assert.assertEquals(60, Filesystem.getFiles(testOutput2).size());
        for (int i = 1; i <= 30; i++) {
            Assert.assertTrue(Math.abs(
                    new Color(ImageUtility.loadImage(new File(frameDir, "frame1_" + StringUtility.padZero(i, 3) + ".png")).getRGB(160, 120)).getRed() -
                            new Color(ImageUtility.loadImage(new File(testOutput2, "frame_" + StringUtility.padZero((i * 2), 3) + ".png")).getRGB(160, 120)).getRed()) <= 1);
        }
        
        //stream selection
        VideoUtility.decodeFramesFromVideo(testSource, 0, 10, testOutput3, "frame_%03d.png");
        Assert.assertEquals(30, Filesystem.getFiles(testOutput3).size());
        for (int i = 1; i <= 30; i++) {
            Assert.assertTrue(Math.abs(
                    new Color(ImageUtility.loadImage(new File(frameDir, "frame1_" + StringUtility.padZero(i, 3) + ".png")).getRGB(160, 120)).getRed() -
                            new Color(ImageUtility.loadImage(new File(testOutput3, "frame_" + StringUtility.padZero(i, 3) + ".png")).getRGB(160, 120)).getRed()) <= 1);
        }
        VideoUtility.decodeFramesFromVideo(testSource, 1, 10, testOutput4, "frame_%03d.png");
        Assert.assertEquals(30, Filesystem.getFiles(testOutput4).size());
        for (int i = 1; i <= 30; i++) {
            Assert.assertTrue(Math.abs(
                    new Color(ImageUtility.loadImage(new File(frameDir, "frame2_" + StringUtility.padZero(i, 3) + ".png")).getRGB(160, 120)).getGreen() -
                            new Color(ImageUtility.loadImage(new File(testOutput4, "frame_" + StringUtility.padZero(i, 3) + ".png")).getRGB(160, 120)).getGreen()) <= 1);
        }
        VideoUtility.decodeFramesFromVideo(testSource, 2, 10, testOutput5, "frame_%03d.png");
        Assert.assertEquals(30, Filesystem.getFiles(testOutput5).size());
        for (int i = 1; i <= 30; i++) {
            Assert.assertTrue(Math.abs(
                    new Color(ImageUtility.loadImage(new File(frameDir, "frame3_" + StringUtility.padZero(i, 3) + ".png")).getRGB(160, 120)).getBlue() -
                            new Color(ImageUtility.loadImage(new File(testOutput5, "frame_" + StringUtility.padZero(i, 3) + ".png")).getRGB(160, 120)).getBlue()) <= 1);
        }
        
        //invalid
        Assert.assertTrue(VideoUtility.decodeFramesFromVideo(testSourceRed, -1, fakeOutput, "frame_%03d.png").contains(
                "[*]Failed to inject frame into filter network: Invalid argument"));
        Assert.assertTrue(VideoUtility.decodeFramesFromVideo(testSource, 0, -1, fakeOutput, "frame_%03d.png").contains(
                "[*]Failed to inject frame into filter network: Invalid argument"));
        Assert.assertTrue(VideoUtility.decodeFramesFromVideo(testSource, 3, 10, fakeOutput, "frame_%03d.png").contains(
                "[*]Stream map '0:v:3' matches no streams."));
        Assert.assertTrue(VideoUtility.decodeFramesFromVideo(testSource, -1, 10, fakeOutput, "frame_%03d.png").contains(
                "[*]Stream map '0:v:-1' matches no streams."));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.decodeFramesFromVideo(testSourceRed, 10, fakeOutput, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.decodeFramesFromVideo(testSource, 0, 10, fakeOutput, null));
        Assert.assertTrue(VideoUtility.decodeFramesFromVideo(fakeSource, 10, fakeOutput, "frame_%03d.png").contains(
                "fakeTest.mkv: No such file or directory"));
        Assert.assertTrue(VideoUtility.decodeFramesFromVideo(fakeSource, 0, 10, fakeOutput, "frame_%03d.png").contains(
                "fakeTest.mkv: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.decodeFramesFromVideo(null, 10, fakeOutput, "frame_%03d.png"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.decodeFramesFromVideo(null, 0, 10, fakeOutput, "frame_%03d.png"));
    }
    
    /**
     * JUnit test of extractFrameFromVideo.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#extractFrameFromVideo(File, int, String, File)
     * @see VideoUtility#extractFrameFromVideo(File, String, File)
     * @see VideoUtility#extractFrameFromVideo(File, int, long, File)
     * @see VideoUtility#extractFrameFromVideo(File, long, File)
     */
    @Test
    public void testExtractFrameFromVideo() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testDir, "test.mkv");
        final File testSourceRed = new File(testDir, "testRed.mkv");
        final File testSourceGreen = new File(testDir, "testGreen.mkv");
        final File testSourceBlue = new File(testDir, "testBlue.mkv");
        final File testOutput1 = new File(testDir, "test1.png");
        final File testOutput2 = new File(testDir, "test2.png");
        final File testOutput3 = new File(testDir, "test3.png");
        final File testOutput4 = new File(testDir, "test4.png");
        final File testOutput5 = new File(testDir, "test5.png");
        final File testOutput6 = new File(testDir, "test6.png");
        final File testOutput7 = new File(testDir, "test7.png");
        final File testOutput8 = new File(testDir, "test8.png");
        final File fakeSource = new File(testDir, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.png");
        
        //prepare
        final File frameDir = Filesystem.createTemporaryDirectory();
        for (int i = 1; i <= 30; i++) {
            for (int j = 1; j <= 3; j++) {
                BufferedImage image = new BufferedImage(320, 240, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = image.createGraphics();
                DrawUtility.setColor(graphics, new Color(((j == 1) ? ((i - 1) / 30.0f) : 0), ((j == 2) ? ((i - 1) / 30.0f) : 0), ((j == 3) ? ((i - 1) / 30.0f) : 0)));
                DrawUtility.fillRect(graphics, new IntVector(0, 0), new IntVector(320, 240));
                File frame = new File(frameDir, "frame" + j + "_" + StringUtility.padZero(i, 3) + ".png");
                ImageUtility.saveImage(image, frame);
            }
        }
        VideoUtility.encodeFramesToVideo(frameDir, "frame1_%03d.png", 10, testSourceRed);
        VideoUtility.encodeFramesToVideo(frameDir, "frame2_%03d.png", 10, testSourceGreen);
        VideoUtility.encodeFramesToVideo(frameDir, "frame3_%03d.png", 10, testSourceBlue);
        VideoUtility.mergeStreams(Arrays.asList(testSourceRed, testSourceGreen, testSourceBlue), testSource);
        
        //default
        VideoUtility.extractFrameFromVideo(testSourceRed, 1100, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertTrue(Math.abs(
                new Color(ImageUtility.loadImage(new File(frameDir, "frame1_012.png")).getRGB(160, 120)).getRed() -
                        new Color(ImageUtility.loadImage(testOutput1).getRGB(160, 120)).getRed()) <= 1);
        VideoUtility.extractFrameFromVideo(testSourceRed, "00:00:02.300", testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertTrue(Math.abs(
                new Color(ImageUtility.loadImage(new File(frameDir, "frame1_024.png")).getRGB(160, 120)).getRed() -
                        new Color(ImageUtility.loadImage(testOutput2).getRGB(160, 120)).getRed()) <= 1);
        
        //stream selection
        VideoUtility.extractFrameFromVideo(testSource, 0, 1100, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertTrue(Math.abs(
                new Color(ImageUtility.loadImage(new File(frameDir, "frame1_012.png")).getRGB(160, 120)).getRed() -
                        new Color(ImageUtility.loadImage(testOutput3).getRGB(160, 120)).getRed()) <= 1);
        VideoUtility.extractFrameFromVideo(testSource, 0, "00:00:02.300", testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertTrue(Math.abs(
                new Color(ImageUtility.loadImage(new File(frameDir, "frame1_024.png")).getRGB(160, 120)).getRed() -
                        new Color(ImageUtility.loadImage(testOutput4).getRGB(160, 120)).getRed()) <= 1);
        VideoUtility.extractFrameFromVideo(testSource, 1, 1100, testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertTrue(Math.abs(
                new Color(ImageUtility.loadImage(new File(frameDir, "frame2_012.png")).getRGB(160, 120)).getGreen() -
                        new Color(ImageUtility.loadImage(testOutput5).getRGB(160, 120)).getGreen()) <= 1);
        VideoUtility.extractFrameFromVideo(testSource, 1, "00:00:02.300", testOutput6);
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertTrue(Math.abs(
                new Color(ImageUtility.loadImage(new File(frameDir, "frame2_024.png")).getRGB(160, 120)).getGreen() -
                        new Color(ImageUtility.loadImage(testOutput6).getRGB(160, 120)).getGreen()) <= 1);
        VideoUtility.extractFrameFromVideo(testSource, 2, 1100, testOutput7);
        Assert.assertTrue(testOutput7.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput7));
        Assert.assertTrue(Math.abs(
                new Color(ImageUtility.loadImage(new File(frameDir, "frame3_012.png")).getRGB(160, 120)).getBlue() -
                        new Color(ImageUtility.loadImage(testOutput7).getRGB(160, 120)).getBlue()) <= 1);
        VideoUtility.extractFrameFromVideo(testSource, 2, "00:00:02.300", testOutput8);
        Assert.assertTrue(testOutput8.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput8));
        Assert.assertTrue(Math.abs(
                new Color(ImageUtility.loadImage(new File(frameDir, "frame3_024.png")).getRGB(160, 120)).getBlue() -
                        new Color(ImageUtility.loadImage(testOutput8).getRGB(160, 120)).getBlue()) <= 1);
        
        //invalid
        TestUtils.assertNoException(() ->
                VideoUtility.extractFrameFromVideo(testSourceRed, -1, fakeOutput));
        TestUtils.assertNoException(() ->
                VideoUtility.extractFrameFromVideo(testSourceRed, 5000, fakeOutput));
        TestUtils.assertNoException(() ->
                VideoUtility.extractFrameFromVideo(testSourceRed, "-00:00:01.000", fakeOutput));
        TestUtils.assertNoException(() ->
                VideoUtility.extractFrameFromVideo(testSourceRed, "00:00:05.000", fakeOutput));
        Assert.assertTrue(VideoUtility.extractFrameFromVideo(testSource, 3, 1100, fakeOutput).contains(
                "[*]Stream map '0:v:3' matches no streams."));
        Assert.assertTrue(VideoUtility.extractFrameFromVideo(testSource, -1, 1100, fakeOutput).contains(
                "[*]Stream map '0:v:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractFrameFromVideo(testSource, 3, "-00:00:01.000", fakeOutput).contains(
                "[*]Stream map '0:v:3' matches no streams."));
        Assert.assertTrue(VideoUtility.extractFrameFromVideo(testSource, -1, "00:00:05.000", fakeOutput).contains(
                "[*]Stream map '0:v:-1' matches no streams."));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractFrameFromVideo(testSourceRed, 1100, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractFrameFromVideo(null, 1100, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractFrameFromVideo(null, 1100, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractFrameFromVideo(testSource, 0, 1100, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractFrameFromVideo(null, 0, 1100, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractFrameFromVideo(null, 0, 1100, null));
    }
    
    /**
     * JUnit test of MetadataBase.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility.MetadataBase
     */
    @Test
    public void testMetadataBase() throws Exception {
        //class
        Class<?> metadataBase = Arrays.stream(VideoUtility.class.getDeclaredClasses())
                .filter(e -> e.getSimpleName().equals("MetadataBase")).findFirst().orElseGet(null);
        Assert.assertNotNull(metadataBase);
        
        //fields
        Assert.assertEquals(JSONObject.class, metadataBase.getDeclaredField("data").getType());
        Assert.assertEquals(long.class, metadataBase.getDeclaredField("duration").getType());
        Assert.assertEquals(long.class, metadataBase.getDeclaredField("startTime").getType());
        Assert.assertEquals(long.class, metadataBase.getDeclaredField("endTime").getType());
        Assert.assertEquals(String.class, metadataBase.getDeclaredField("title").getType());
        Assert.assertEquals(String.class, metadataBase.getDeclaredField("language").getType());
        Assert.assertEquals(Map.class, metadataBase.getDeclaredField("tags").getType());
        
        //methods
        Assert.assertNotNull(metadataBase.getConstructor(JSONObject.class));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getData"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getDurationExact"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getStartTimeExact"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getEndTimeExact"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getDuration"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getStartTime"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getEndTime"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getTitle"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getLanguage"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getTags"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getTag", String.class));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("containsTag", String.class));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("get", String.class));
        
        //subclasses
        Assert.assertEquals(metadataBase.getSimpleName(), VideoUtility.FormatMetadata.class.getSuperclass().getSimpleName());
        Assert.assertEquals(metadataBase.getSimpleName(), VideoUtility.StreamMetadata.class.getSuperclass().getSimpleName());
        Assert.assertEquals(metadataBase.getSimpleName(), VideoUtility.ChapterMetadata.class.getSuperclass().getSimpleName());
        Assert.assertNotEquals(metadataBase.getSimpleName(), VideoUtility.StreamMetadata.VideoStreamMetadata.class.getSuperclass().getSimpleName());
        Assert.assertNotEquals(metadataBase.getSimpleName(), VideoUtility.StreamMetadata.AudioStreamMetadata.class.getSuperclass().getSimpleName());
        Assert.assertNotEquals(metadataBase.getSimpleName(), VideoUtility.StreamMetadata.SubtitleStreamMetadata.class.getSuperclass().getSimpleName());
        Assert.assertNotEquals(metadataBase.getSimpleName(), VideoUtility.Metadata.class.getSuperclass().getSimpleName());
    }
    
    /**
     * JUnit test of FormatMetadata.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility.FormatMetadata
     */
    @Test
    public void testFormatMetadata() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final VideoUtility.FormatMetadata metadata = VideoUtility.getFormat(testVideo);
        Assert.assertNotNull(metadata);
        Assert.assertEquals("MetadataBase", VideoUtility.FormatMetadata.class.getSuperclass().getSimpleName());
        
        //base
        Assert.assertNotNull(metadata.getData());
        Assert.assertEquals(3067000L, metadata.getDurationExact());
        Assert.assertEquals(-5000L, metadata.getStartTimeExact());
        Assert.assertEquals(3062000L, metadata.getEndTimeExact());
        Assert.assertEquals(3067L, metadata.getDuration());
        Assert.assertEquals(-5L, metadata.getStartTime());
        Assert.assertEquals(3062L, metadata.getEndTime());
        Assert.assertEquals("FFmpeg Test Video", metadata.getTitle());
        Assert.assertNull(metadata.getLanguage());
        Assert.assertEquals(2, metadata.getTags().size());
        Assert.assertEquals("FFmpeg Test Video", metadata.getTag("title"));
        Assert.assertEquals("FFmpeg Test Video", metadata.getTag("TITLE"));
        Assert.assertNull(metadata.getTag("tag"));
        Assert.assertTrue(metadata.containsTag("encoder"));
        Assert.assertTrue(metadata.containsTag("ENCODER"));
        Assert.assertFalse(metadata.containsTag("tag"));
        Assert.assertEquals("1476113", metadata.get("bit_rate"));
        Assert.assertEquals(100L, metadata.get("probe_score"));
        Assert.assertNull(metadata.get("something_else"));
        
        //format
        Assert.assertEquals(testVideo.getAbsolutePath(), metadata.getVideoFile().getAbsolutePath());
        Assert.assertEquals(565905L, metadata.getSize());
        Assert.assertEquals(1476113L, metadata.getBitRate());
        Assert.assertEquals("matroska,webm", metadata.getFormatName());
        Assert.assertEquals("Matroska / WebM", metadata.getFormatNameLong());
        Assert.assertEquals(15, metadata.getStreamCount());
        Assert.assertEquals(0, metadata.getProgramCount());
    }
    
    /**
     * JUnit test of StreamMetadata.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility.StreamMetadata
     * @see VideoUtility.StreamMetadata.VideoStreamMetadata
     * @see VideoUtility.StreamMetadata.AudioStreamMetadata
     * @see VideoUtility.StreamMetadata.SubtitleStreamMetadata
     * @see VideoUtility.StreamMetadata.DataStreamMetadata
     */
    @Test
    public void testStreamMetadata() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        final VideoUtility.StreamMetadata metadataVideo = VideoUtility.getStream(testVideo, VideoUtility.StreamType.VIDEO, 0);
        final VideoUtility.StreamMetadata metadataAudio = VideoUtility.getStream(testVideo, VideoUtility.StreamType.AUDIO, 2);
        final VideoUtility.StreamMetadata metadataSubtitle = VideoUtility.getStream(testVideo, VideoUtility.StreamType.SUBTITLE, 4);
        final VideoUtility.StreamMetadata metadataData = VideoUtility.getStream(testVideo2, VideoUtility.StreamType.DATA, 0);
        Assert.assertNotNull(metadataVideo);
        Assert.assertNotNull(metadataAudio);
        Assert.assertNotNull(metadataSubtitle);
        Assert.assertNotNull(metadataData);
        Assert.assertEquals("MetadataBase", VideoUtility.StreamMetadata.class.getSuperclass().getSimpleName());
        
        //video, base
        Assert.assertNotNull(metadataVideo.getData());
        Assert.assertEquals(3023000L, metadataVideo.getDurationExact());
        Assert.assertEquals(0L, metadataVideo.getStartTimeExact());
        Assert.assertEquals(3023000L, metadataVideo.getEndTimeExact());
        Assert.assertEquals(3023L, metadataVideo.getDuration());
        Assert.assertEquals(0L, metadataVideo.getStartTime());
        Assert.assertEquals(3023L, metadataVideo.getEndTime());
        Assert.assertEquals("Red", metadataVideo.getTitle());
        Assert.assertEquals("eng", metadataVideo.getLanguage());
        Assert.assertEquals(3, metadataVideo.getTags().size());
        Assert.assertEquals("Red", metadataVideo.getTag("title"));
        Assert.assertEquals("Red", metadataVideo.getTag("TITLE"));
        Assert.assertNull(metadataVideo.getTag("tag"));
        Assert.assertTrue(metadataVideo.containsTag("duration"));
        Assert.assertTrue(metadataVideo.containsTag("DURATION"));
        Assert.assertFalse(metadataVideo.containsTag("tag"));
        Assert.assertEquals("10/1", metadataVideo.get("r_frame_rate"));
        Assert.assertEquals("4", metadataVideo.get("nal_length_size"));
        Assert.assertNull(metadataVideo.get("something_else"));
        
        //video, stream
        Assert.assertEquals(VideoUtility.StreamType.VIDEO, metadataVideo.getStreamType());
        Assert.assertEquals(0, metadataVideo.getStreamIndex());
        Assert.assertTrue(metadataVideo.isDefaultStream());
        Assert.assertEquals("h264", metadataVideo.getCodecName());
        Assert.assertEquals("H.264 / AVC / MPEG-4 AVC / MPEG-4 part 10", metadataVideo.getCodecNameLong());
        Assert.assertEquals(10.0, metadataVideo.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(10.0, metadataVideo.getFrameRateAverage(), TestUtils.DELTA);
        Assert.assertEquals(12, metadataVideo.getDispositions().size());
        Assert.assertTrue(metadataVideo.getDisposition("default"));
        Assert.assertTrue(metadataVideo.getDisposition("DEFAULT"));
        Assert.assertFalse(metadataVideo.getDisposition("comment"));
        Assert.assertFalse(metadataVideo.getDisposition("something"));
        
        //video, video stream
        Assert.assertNotNull(metadataVideo.getVideoMetadata());
        Assert.assertEquals(320, metadataVideo.getVideoMetadata().getWidth());
        Assert.assertEquals(240, metadataVideo.getVideoMetadata().getHeight());
        Assert.assertEquals(12, metadataVideo.getVideoMetadata().getLevel());
        Assert.assertEquals("yuv444p", metadataVideo.getVideoMetadata().getPixelFormat());
        Assert.assertEquals("4:3", metadataVideo.getVideoMetadata().getAspectRatio());
        Assert.assertEquals("High 4:4:4 Predictive", metadataVideo.getVideoMetadata().getProfile());
        Assert.assertFalse(metadataVideo.getVideoMetadata().hasClosedCaptions());
        
        //video, audio stream
        Assert.assertNotNull(metadataVideo.getAudioMetadata());
        Assert.assertNull(metadataVideo.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(-1, metadataVideo.getAudioMetadata().getChannels());
        Assert.assertEquals(-1, metadataVideo.getAudioMetadata().getSampleRate());
        Assert.assertNull(metadataVideo.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(-1, metadataVideo.getAudioMetadata().getBitsPerSample());
        
        //video, subtitle stream
        Assert.assertNotNull(metadataVideo.getSubtitleMetadata());
        
        //video, data stream
        Assert.assertNotNull(metadataVideo.getDataMetadata());
        
        //audio, base
        Assert.assertNotNull(metadataAudio.getData());
        Assert.assertEquals(3023000L, metadataAudio.getDurationExact());
        Assert.assertEquals(23000L, metadataAudio.getStartTimeExact());
        Assert.assertEquals(3046000L, metadataAudio.getEndTimeExact());
        Assert.assertEquals(3023L, metadataAudio.getDuration());
        Assert.assertEquals(23L, metadataAudio.getStartTime());
        Assert.assertEquals(3046L, metadataAudio.getEndTime());
        Assert.assertEquals("Galway", metadataAudio.getTitle());
        Assert.assertEquals("rus", metadataAudio.getLanguage());
        Assert.assertEquals(5, metadataAudio.getTags().size());
        Assert.assertEquals("Galway", metadataAudio.getTag("title"));
        Assert.assertEquals("Galway", metadataAudio.getTag("TITLE"));
        Assert.assertNull(metadataAudio.getTag("tag"));
        Assert.assertTrue(metadataAudio.containsTag("duration"));
        Assert.assertTrue(metadataAudio.containsTag("DURATION"));
        Assert.assertFalse(metadataAudio.containsTag("tag"));
        Assert.assertEquals("0/0", metadataAudio.get("r_frame_rate"));
        Assert.assertEquals("1/44100", metadataAudio.get("codec_time_base"));
        Assert.assertNull(metadataAudio.get("something_else"));
        
        //audio, stream
        Assert.assertEquals(VideoUtility.StreamType.AUDIO, metadataAudio.getStreamType());
        Assert.assertEquals(5, metadataAudio.getStreamIndex());
        Assert.assertFalse(metadataAudio.isDefaultStream());
        Assert.assertEquals("flac", metadataAudio.getCodecName());
        Assert.assertEquals("FLAC (Free Lossless Audio Codec)", metadataAudio.getCodecNameLong());
        Assert.assertEquals(0.0, metadataAudio.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(0.0, metadataAudio.getFrameRateAverage(), TestUtils.DELTA);
        Assert.assertEquals(12, metadataAudio.getDispositions().size());
        Assert.assertFalse(metadataAudio.getDisposition("default"));
        Assert.assertFalse(metadataAudio.getDisposition("DEFAULT"));
        Assert.assertFalse(metadataAudio.getDisposition("comment"));
        Assert.assertFalse(metadataAudio.getDisposition("something"));
        
        //audio, video stream
        Assert.assertNotNull(metadataAudio.getVideoMetadata());
        Assert.assertEquals(-1, metadataAudio.getVideoMetadata().getWidth());
        Assert.assertEquals(-1, metadataAudio.getVideoMetadata().getHeight());
        Assert.assertEquals(-1, metadataAudio.getVideoMetadata().getLevel());
        Assert.assertNull(metadataAudio.getVideoMetadata().getPixelFormat());
        Assert.assertNull(metadataAudio.getVideoMetadata().getAspectRatio());
        Assert.assertNull(metadataAudio.getVideoMetadata().getProfile());
        Assert.assertFalse(metadataAudio.getVideoMetadata().hasClosedCaptions());
        
        //audio, audio stream
        Assert.assertNotNull(metadataAudio.getAudioMetadata());
        Assert.assertEquals("stereo", metadataAudio.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(2, metadataAudio.getAudioMetadata().getChannels());
        Assert.assertEquals(44100, metadataAudio.getAudioMetadata().getSampleRate());
        Assert.assertEquals("s16", metadataAudio.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(0, metadataAudio.getAudioMetadata().getBitsPerSample());
        
        //audio, subtitle stream
        Assert.assertNotNull(metadataAudio.getSubtitleMetadata());
        
        //audio, data stream
        Assert.assertNotNull(metadataAudio.getDataMetadata());
        
        //subtitle, base
        Assert.assertNotNull(metadataSubtitle.getData());
        Assert.assertEquals(3067000L, metadataSubtitle.getDurationExact());
        Assert.assertEquals(-5000L, metadataSubtitle.getStartTimeExact());
        Assert.assertEquals(3062000L, metadataSubtitle.getEndTimeExact());
        Assert.assertEquals(3067L, metadataSubtitle.getDuration());
        Assert.assertEquals(-5L, metadataSubtitle.getStartTime());
        Assert.assertEquals(3062L, metadataSubtitle.getEndTime());
        Assert.assertEquals("French", metadataSubtitle.getTitle());
        Assert.assertEquals("fre", metadataSubtitle.getLanguage());
        Assert.assertEquals(3, metadataSubtitle.getTags().size());
        Assert.assertEquals("French", metadataSubtitle.getTag("title"));
        Assert.assertEquals("French", metadataSubtitle.getTag("TITLE"));
        Assert.assertNull(metadataSubtitle.getTag("tag"));
        Assert.assertTrue(metadataSubtitle.containsTag("duration"));
        Assert.assertTrue(metadataSubtitle.containsTag("DURATION"));
        Assert.assertFalse(metadataSubtitle.containsTag("tag"));
        Assert.assertEquals("0/0", metadataSubtitle.get("r_frame_rate"));
        Assert.assertEquals(3067L, metadataSubtitle.get("duration_ts"));
        Assert.assertNull(metadataSubtitle.get("something_else"));
        
        //subtitle, stream
        Assert.assertEquals(VideoUtility.StreamType.SUBTITLE, metadataSubtitle.getStreamType());
        Assert.assertEquals(13, metadataSubtitle.getStreamIndex());
        Assert.assertFalse(metadataSubtitle.isDefaultStream());
        Assert.assertEquals("subrip", metadataSubtitle.getCodecName());
        Assert.assertEquals("SubRip subtitle", metadataSubtitle.getCodecNameLong());
        Assert.assertEquals(0.0, metadataSubtitle.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(0.0, metadataSubtitle.getFrameRateAverage(), TestUtils.DELTA);
        Assert.assertEquals(12, metadataSubtitle.getDispositions().size());
        Assert.assertFalse(metadataSubtitle.getDisposition("default"));
        Assert.assertFalse(metadataSubtitle.getDisposition("DEFAULT"));
        Assert.assertFalse(metadataSubtitle.getDisposition("comment"));
        Assert.assertFalse(metadataSubtitle.getDisposition("something"));
        
        //subtitle, video stream
        Assert.assertNotNull(metadataSubtitle.getVideoMetadata());
        Assert.assertEquals(-1, metadataSubtitle.getVideoMetadata().getWidth());
        Assert.assertEquals(-1, metadataSubtitle.getVideoMetadata().getHeight());
        Assert.assertEquals(-1, metadataSubtitle.getVideoMetadata().getLevel());
        Assert.assertNull(metadataSubtitle.getVideoMetadata().getPixelFormat());
        Assert.assertNull(metadataSubtitle.getVideoMetadata().getAspectRatio());
        Assert.assertNull(metadataSubtitle.getVideoMetadata().getProfile());
        Assert.assertFalse(metadataSubtitle.getVideoMetadata().hasClosedCaptions());
        
        //subtitle, audio stream
        Assert.assertNotNull(metadataSubtitle.getAudioMetadata());
        Assert.assertNull(metadataSubtitle.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(-1, metadataSubtitle.getAudioMetadata().getChannels());
        Assert.assertEquals(-1, metadataSubtitle.getAudioMetadata().getSampleRate());
        Assert.assertNull(metadataSubtitle.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(-1, metadataSubtitle.getAudioMetadata().getBitsPerSample());
        
        //subtitle, subtitle stream
        Assert.assertNotNull(metadataSubtitle.getSubtitleMetadata());
        
        //subtitle, data stream
        Assert.assertNotNull(metadataSubtitle.getDataMetadata());
        
        //data, base
        Assert.assertNotNull(metadataData.getData());
        Assert.assertEquals(3004000L, metadataData.getDurationExact());
        Assert.assertEquals(0L, metadataData.getStartTimeExact());
        Assert.assertEquals(3004000L, metadataData.getEndTimeExact());
        Assert.assertEquals(3004L, metadataData.getDuration());
        Assert.assertEquals(0L, metadataData.getStartTime());
        Assert.assertEquals(3004L, metadataData.getEndTime());
        Assert.assertNull(metadataData.getTitle());
        Assert.assertEquals("eng", metadataData.getLanguage());
        Assert.assertEquals(2, metadataData.getTags().size());
        Assert.assertNull(metadataData.getTag("title"));
        Assert.assertNull(metadataData.getTag("TITLE"));
        Assert.assertNull(metadataData.getTag("tag"));
        Assert.assertFalse(metadataData.containsTag("duration"));
        Assert.assertFalse(metadataData.containsTag("DURATION"));
        Assert.assertFalse(metadataData.containsTag("tag"));
        Assert.assertEquals("0/0", metadataData.get("r_frame_rate"));
        Assert.assertEquals(3004L, metadataData.get("duration_ts"));
        Assert.assertNull(metadataData.get("something_else"));
        
        //data, stream
        Assert.assertEquals(VideoUtility.StreamType.DATA, metadataData.getStreamType());
        Assert.assertEquals(1, metadataData.getStreamIndex());
        Assert.assertFalse(metadataData.isDefaultStream());
        Assert.assertEquals("bin_data", metadataData.getCodecName());
        Assert.assertEquals("binary data", metadataData.getCodecNameLong());
        Assert.assertEquals(0.0, metadataData.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(0.0, metadataData.getFrameRateAverage(), TestUtils.DELTA);
        Assert.assertEquals(12, metadataData.getDispositions().size());
        Assert.assertFalse(metadataData.getDisposition("default"));
        Assert.assertFalse(metadataData.getDisposition("DEFAULT"));
        Assert.assertFalse(metadataData.getDisposition("comment"));
        Assert.assertFalse(metadataData.getDisposition("something"));
        
        //data, video stream
        Assert.assertNotNull(metadataData.getVideoMetadata());
        Assert.assertEquals(-1, metadataData.getVideoMetadata().getWidth());
        Assert.assertEquals(-1, metadataData.getVideoMetadata().getHeight());
        Assert.assertEquals(-1, metadataData.getVideoMetadata().getLevel());
        Assert.assertNull(metadataData.getVideoMetadata().getPixelFormat());
        Assert.assertNull(metadataData.getVideoMetadata().getAspectRatio());
        Assert.assertNull(metadataData.getVideoMetadata().getProfile());
        Assert.assertFalse(metadataData.getVideoMetadata().hasClosedCaptions());
        
        //data, audio stream
        Assert.assertNotNull(metadataData.getAudioMetadata());
        Assert.assertNull(metadataData.getAudioMetadata().getChannelLayout());
        Assert.assertEquals(-1, metadataData.getAudioMetadata().getChannels());
        Assert.assertEquals(-1, metadataData.getAudioMetadata().getSampleRate());
        Assert.assertNull(metadataData.getAudioMetadata().getSampleFormat());
        Assert.assertEquals(-1, metadataData.getAudioMetadata().getBitsPerSample());
        
        //data, subtitle stream
        Assert.assertNotNull(metadataData.getSubtitleMetadata());
        
        //data, data stream
        Assert.assertNotNull(metadataData.getDataMetadata());
    }
    
    /**
     * JUnit test of ChapterMetadata.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility.ChapterMetadata
     */
    @Test
    public void testChapterMetadata() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final VideoUtility.ChapterMetadata metadata = VideoUtility.getChapter(testVideo, 1);
        Assert.assertNotNull(metadata);
        Assert.assertEquals("MetadataBase", VideoUtility.ChapterMetadata.class.getSuperclass().getSimpleName());
        
        //base
        Assert.assertNotNull(metadata.getData());
        Assert.assertEquals(700000L, metadata.getDurationExact());
        Assert.assertEquals(1100000L, metadata.getStartTimeExact());
        Assert.assertEquals(1800000L, metadata.getEndTimeExact());
        Assert.assertEquals(700L, metadata.getDuration());
        Assert.assertEquals(1100L, metadata.getStartTime());
        Assert.assertEquals(1800L, metadata.getEndTime());
        Assert.assertEquals("Second Chapter", metadata.getTitle());
        Assert.assertNull(metadata.getLanguage());
        Assert.assertEquals(1, metadata.getTags().size());
        Assert.assertEquals("Second Chapter", metadata.getTag("title"));
        Assert.assertEquals("Second Chapter", metadata.getTag("TITLE"));
        Assert.assertNull(metadata.getTag("tag"));
        Assert.assertTrue(metadata.containsTag("title"));
        Assert.assertTrue(metadata.containsTag("TITLE"));
        Assert.assertFalse(metadata.containsTag("tag"));
        Assert.assertEquals("1.100000", metadata.get("start_time"));
        Assert.assertEquals(1800000000L, metadata.get("end"));
        Assert.assertNull(metadata.get("something_else"));
        
        //chapter
        Assert.assertEquals(2L, metadata.getChapterId());
    }
    
    /**
     * JUnit test of Metadata.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility.Metadata
     */
    @Test
    public void testMetadata() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final VideoUtility.Metadata metadata = VideoUtility.getMetadata(testVideo);
        Assert.assertNotNull(metadata);
        Assert.assertNotEquals("MetadataBase", VideoUtility.Metadata.class.getSuperclass().getSimpleName());
        
        //base
        Assert.assertNotNull(metadata.getData());
        Assert.assertNotNull(metadata.getFormat());
        Assert.assertNotNull(metadata.getStreams());
        Assert.assertNotNull(metadata.getChapters());
        Assert.assertEquals(15, metadata.getStreams().size());
        Assert.assertEquals(3, metadata.getChapters().size());
    }
    
}
