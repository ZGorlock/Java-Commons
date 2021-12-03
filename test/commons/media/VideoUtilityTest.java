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
import commons.log.CommonsLogging;
import commons.math.MathUtility;
import commons.math.component.vector.IntVector;
import commons.string.StringUtility;
import commons.test.TestUtils;
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
@PrepareForTest({VideoUtility.class, FFmpeg.class, CommonsLogging.class})
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
    @Before
    public void setup() throws Exception {
        PowerMockito.mockStatic(CommonsLogging.class);
        PowerMockito.doReturn(true).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
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
        List<FFmpeg.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSourceInitial.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSourceInitial));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSourceInitial));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSourceInitial));
        streams = FFmpeg.getStreams(testSourceInitial);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeSubtitles(testSourceInitial, testSource);
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(9, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        streams = FFmpeg.getStreams(testSource);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //standard
        VideoUtility.convertVideo(testSource, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(10, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        streams = FFmpeg.getStreams(testOutput1);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|h264|h264|aac|aac|aac|aac|aac|aac|bin_data",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testSource, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(10, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput2));
        streams = FFmpeg.getStreams(testOutput2);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
        }
        Assert.assertEquals("h264|h264|h264|aac|aac|aac|aac|aac|aac|bin_data",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testSource, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(9, FFmpeg.getStreamCount(testOutput3));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput3));
        streams = FFmpeg.getStreams(testOutput3);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNull(stream.getLanguage());
        }
        Assert.assertEquals("mpeg4|mpeg4|mpeg4|mp3|mp3|mp3|mp3|mp3|mp3",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testSource, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(9, FFmpeg.getStreamCount(testOutput4));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput4));
        streams = FFmpeg.getStreams(testOutput4);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //file type
        VideoUtility.convertVideo(testOutput4, "mp4");
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(10, FFmpeg.getStreamCount(testOutput5));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput5));
        streams = FFmpeg.getStreams(testOutput5);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|h264|h264|aac|aac|aac|aac|aac|aac|bin_data",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testOutput4, "mov");
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertEquals(10, FFmpeg.getStreamCount(testOutput6));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput6));
        streams = FFmpeg.getStreams(testOutput6);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
        }
        Assert.assertEquals("h264|h264|h264|aac|aac|aac|aac|aac|aac|bin_data",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.convertVideo(testOutput4, "avi");
        Assert.assertTrue(testOutput7.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput7));
        Assert.assertEquals(9, FFmpeg.getStreamCount(testOutput7));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput7));
        streams = FFmpeg.getStreams(testOutput7);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNull(stream.getLanguage());
        }
        Assert.assertEquals("mpeg4|mpeg4|mpeg4|mp3|mp3|mp3|mp3|mp3|mp3",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
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
        List<FFmpeg.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        streams = FFmpeg.getStreams(testSource);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //default
        VideoUtility.transcodeVideo(testSource, new HashMap<>(), testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        streams = FFmpeg.getStreams(testOutput1);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //standard
        VideoUtility.transcodeVideo(testSource, new HashMap<>() {{
            put("v:0", "libx265");
            put("v:1", "libx264");
            put("v:2", "mpeg4");
            put("a", "aac");
        }}, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput2));
        streams = FFmpeg.getStreams(testOutput2);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("hevc|h264|mpeg4|aac|aac|aac|aac|aac|aac|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
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
        Assert.assertEquals(16, FFmpeg.getStreamCount(testOutput3));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput3));
        streams = FFmpeg.getStreams(testOutput3);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
        }
        Assert.assertEquals("hevc|h264|mpeg4|mp3|aac|vorbis|aac|mp3|vorbis|mov_text|mov_text|mov_text|mov_text|mov_text|mov_text|bin_data",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
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
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                FFmpeg.getStreams(testSource).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.mergeStreams(Arrays.asList(testSource, testSource1, testSource3), testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(17, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|hevc|mp3",
                FFmpeg.getStreams(testOutput1).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //from scratch
        VideoUtility.mergeStreams(Arrays.asList(testSource1, testSource2, testSource3, testSource4, testSource5, testSource6), testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(6, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput2));
        Assert.assertEquals("hevc|mpeg4|mp3|aac|vorbis|subrip",
                FFmpeg.getStreams(testOutput2).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
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
     * @see VideoUtility#addStream(File, File, FFmpeg.StreamType, int, File)
     * @see VideoUtility#addStream(File, File, FFmpeg.StreamType, File)
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
        VideoUtility.addStream(testSource, testSource, FFmpeg.StreamType.VIDEO, 1, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(16, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|hevc",
                FFmpeg.getStreams(testOutput1).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource, testSource, FFmpeg.StreamType.SUBTITLE, 3, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(16, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput2));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|subrip",
                FFmpeg.getStreams(testOutput2).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //merge, stream type
        VideoUtility.addStream(testSource, testSource, FFmpeg.StreamType.VIDEO, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(16, FFmpeg.getStreamCount(testOutput3));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput3));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|h264",
                FFmpeg.getStreams(testOutput3).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource, testSource, FFmpeg.StreamType.AUDIO, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(16, FFmpeg.getStreamCount(testOutput4));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput4));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|mp3",
                FFmpeg.getStreams(testOutput4).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //merge, index
        VideoUtility.addStream(testSource, testSource, 2, testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(16, FFmpeg.getStreamCount(testOutput5));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput5));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|mpeg4",
                FFmpeg.getStreams(testOutput5).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource, testSource, 5, testOutput6);
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertEquals(16, FFmpeg.getStreamCount(testOutput6));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput6));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip|flac",
                FFmpeg.getStreams(testOutput6).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //from scratch, stream type and index
        VideoUtility.addStream(testSource1, testSource, FFmpeg.StreamType.VIDEO, 2, testOutput7);
        Assert.assertTrue(testOutput7.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput7));
        Assert.assertEquals(2, FFmpeg.getStreamCount(testOutput7));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput7));
        Assert.assertEquals("hevc|mpeg4",
                FFmpeg.getStreams(testOutput7).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource1, testSource2, FFmpeg.StreamType.VIDEO, 0, testOutput8);
        Assert.assertTrue(testOutput8.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput8));
        Assert.assertEquals(2, FFmpeg.getStreamCount(testOutput8));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput8));
        Assert.assertEquals("hevc|mpeg4",
                FFmpeg.getStreams(testOutput8).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //from scratch, stream type
        VideoUtility.addStream(testSource2, testSource, FFmpeg.StreamType.AUDIO, testOutput9);
        Assert.assertTrue(testOutput9.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput9));
        Assert.assertEquals(2, FFmpeg.getStreamCount(testOutput9));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput9));
        Assert.assertEquals("mpeg4|mp3",
                FFmpeg.getStreams(testOutput9).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource2, testSource3, FFmpeg.StreamType.AUDIO, testOutput10);
        Assert.assertTrue(testOutput10.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput10));
        Assert.assertEquals(2, FFmpeg.getStreamCount(testOutput10));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput10));
        Assert.assertEquals("mpeg4|mp3",
                FFmpeg.getStreams(testOutput10).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //from scratch, index
        VideoUtility.addStream(testSource3, testSource, 6, testOutput11);
        Assert.assertTrue(testOutput11.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput11));
        Assert.assertEquals(2, FFmpeg.getStreamCount(testOutput11));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput11));
        Assert.assertEquals("mp3|aac",
                FFmpeg.getStreams(testOutput11).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.addStream(testSource3, testSource1, 0, testOutput12);
        Assert.assertTrue(testOutput12.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput12));
        Assert.assertEquals(2, FFmpeg.getStreamCount(testOutput12));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput12));
        Assert.assertEquals("mp3|hevc",
                FFmpeg.getStreams(testOutput12).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource1, FFmpeg.StreamType.VIDEO, 2, testOutput1).contains(
                "[*]Stream map '1:v:2' matches no streams."));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource1, FFmpeg.StreamType.AUDIO, testOutput1).contains(
                "[*]Stream map '1:a:0' matches no streams."));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource1, 3, fakeOutput).contains(
                "[*]Stream map '1:3' matches no streams."));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource1, null, 2, testOutput1).contains(
                "[*]Stream map '1:2' matches no streams."));
        Assert.assertTrue(VideoUtility.addStream(testSource, fakeSource, null, testOutput1).contains(
                "fakeTest.mkv: No such file or directory"));
        Assert.assertTrue(VideoUtility.addStream(testSource, fakeSource, FFmpeg.StreamType.VIDEO, 2, testOutput1).contains(
                "fakeTest.mkv: No such file or directory"));
        Assert.assertTrue(VideoUtility.addStream(testSource, fakeSource, FFmpeg.StreamType.AUDIO, testOutput1).contains(
                "fakeTest.mkv: No such file or directory"));
        Assert.assertTrue(VideoUtility.addStream(testSource, fakeSource, 3, testOutput1).contains(
                "fakeTest.mkv: No such file or directory"));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource, FFmpeg.StreamType.VIDEO, 2, fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource, FFmpeg.StreamType.AUDIO, fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        Assert.assertTrue(VideoUtility.addStream(testSource, testSource, 3, fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, null, FFmpeg.StreamType.VIDEO, 2, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, null, FFmpeg.StreamType.AUDIO, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, null, 3, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, testSource, FFmpeg.StreamType.VIDEO, 2, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, testSource, FFmpeg.StreamType.AUDIO, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(testSource, testSource, 3, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, testSource, FFmpeg.StreamType.VIDEO, 2, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, testSource, FFmpeg.StreamType.AUDIO, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, testSource, 3, testOutput1));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, null, FFmpeg.StreamType.VIDEO, 2, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.addStream(null, null, FFmpeg.StreamType.AUDIO, null));
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
        VideoUtility.addStream(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(testStream), ArgumentMatchers.eq(FFmpeg.StreamType.VIDEO), ArgumentMatchers.eq(testOutput));
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
        VideoUtility.addStream(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(testStream), ArgumentMatchers.eq(FFmpeg.StreamType.AUDIO), ArgumentMatchers.eq(testOutput));
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
        VideoUtility.addStream(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(testStream), ArgumentMatchers.eq(FFmpeg.StreamType.SUBTITLE), ArgumentMatchers.eq(testOutput));
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
        VideoUtility.addStream(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(testStream), ArgumentMatchers.eq(FFmpeg.StreamType.DATA), ArgumentMatchers.eq(testOutput));
    }
    
    /**
     * JUnit test of removeStream.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#removeStream(File, FFmpeg.StreamType, int, File)
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
        List<FFmpeg.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        streams = FFmpeg.getStreams(testSource);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //stream and index
        VideoUtility.removeStream(testSource, FFmpeg.StreamType.VIDEO, 1, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(14, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        streams = FFmpeg.getStreams(testOutput1);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStream(testOutput1, FFmpeg.StreamType.AUDIO, 4, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(13, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput2));
        streams = FFmpeg.getStreams(testOutput2);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|flac|aac|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStream(testOutput2, FFmpeg.StreamType.SUBTITLE, 2, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(12, FFmpeg.getStreamCount(testOutput3));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput3));
        streams = FFmpeg.getStreams(testOutput3);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|flac|aac|vorbis|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //index
        VideoUtility.removeStream(testOutput3, 9, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(11, FFmpeg.getStreamCount(testOutput4));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput4));
        streams = FFmpeg.getStreams(testOutput4);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|flac|aac|vorbis|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStream(testOutput4, 4, testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(10, FFmpeg.getStreamCount(testOutput5));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput5));
        streams = FFmpeg.getStreams(testOutput5);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mpeg4|mp3|aac|aac|vorbis|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStream(testOutput5, 1, testOutput6);
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertEquals(9, FFmpeg.getStreamCount(testOutput6));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput6));
        streams = FFmpeg.getStreams(testOutput6);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|mp3|aac|aac|vorbis|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        streams = FFmpeg.getStreams(testSource);
        VideoUtility.removeStream(testSource, FFmpeg.StreamType.VIDEO, 3, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, FFmpeg.StreamType.VIDEO, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, FFmpeg.StreamType.AUDIO, 6, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, FFmpeg.StreamType.AUDIO, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, FFmpeg.StreamType.SUBTITLE, 6, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, FFmpeg.StreamType.SUBTITLE, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, FFmpeg.StreamType.DATA, 0, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, FFmpeg.StreamType.DATA, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, null, 15, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, null, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, 15, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        VideoUtility.removeStream(testSource, -1, fakeOutput);
        Assert.assertEquals(streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")),
                FFmpeg.getStreams(fakeOutput).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        Assert.assertTrue(Filesystem.deleteFile(fakeOutput));
        Assert.assertTrue(VideoUtility.removeStream(new File(testResources, "fakeVideo.mp4"), FFmpeg.StreamType.VIDEO, 0, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        Assert.assertTrue(VideoUtility.removeStream(new File(testResources, "fakeVideo.mp4"), 0, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(null, FFmpeg.StreamType.SUBTITLE, 0, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(null, 1, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(testSource, FFmpeg.StreamType.SUBTITLE, 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(testSource, 1, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(null, FFmpeg.StreamType.SUBTITLE, 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStream(null, 1, null));
    }
    
    /**
     * JUnit test of removeStreamType.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#removeStreamType(File, FFmpeg.StreamType, File)
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
        List<FFmpeg.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        streams = FFmpeg.getStreams(testSource);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //stream and index
        VideoUtility.removeStreamType(testSource, FFmpeg.StreamType.DATA, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        streams = FFmpeg.getStreams(testOutput1);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreamType(testOutput1, FFmpeg.StreamType.SUBTITLE, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(9, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput2));
        streams = FFmpeg.getStreams(testOutput2);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreamType(testOutput2, FFmpeg.StreamType.AUDIO, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(3, FFmpeg.getStreamCount(testOutput3));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput3));
        streams = FFmpeg.getStreams(testOutput3);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreamType(testSource, FFmpeg.StreamType.VIDEO, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(12, FFmpeg.getStreamCount(testOutput4));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput4));
        streams = FFmpeg.getStreams(testOutput4);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        Assert.assertTrue(VideoUtility.removeStreamType(testOutput3, FFmpeg.StreamType.VIDEO, fakeOutput).contains(
                "[*]Output file #0 does not contain any stream"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreamType(testSource, null, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreamType(null, FFmpeg.StreamType.SUBTITLE, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreamType(testSource, FFmpeg.StreamType.SUBTITLE, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.removeStreamType(null, FFmpeg.StreamType.SUBTITLE, null));
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
        VideoUtility.removeStreamType(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(FFmpeg.StreamType.VIDEO), ArgumentMatchers.eq(testOutput));
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
        VideoUtility.removeStreamType(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(FFmpeg.StreamType.AUDIO), ArgumentMatchers.eq(testOutput));
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
        VideoUtility.removeStreamType(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(FFmpeg.StreamType.SUBTITLE), ArgumentMatchers.eq(testOutput));
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
        VideoUtility.removeStreamType(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(FFmpeg.StreamType.DATA), ArgumentMatchers.eq(testOutput));
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
        Assert.assertEquals(7, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        Assert.assertEquals("h264|hevc|mp3|aac|aac|opus|vorbis",
                FFmpeg.getStreams(testOutput1).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreams(testSource, Arrays.asList("0:2", "0:5"), testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(13, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput2));
        Assert.assertEquals("h264|hevc|mp3|aac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                FFmpeg.getStreams(testOutput2).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreams(testSource, Arrays.asList("0:2", "a:2", "s"), testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(7, FFmpeg.getStreamCount(testOutput3));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput3));
        Assert.assertEquals("h264|hevc|mp3|aac|aac|opus|vorbis",
                FFmpeg.getStreams(testOutput3).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreams(testSource, Collections.emptyList(), testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput4));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput4));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                FFmpeg.getStreams(testOutput4).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        VideoUtility.removeStreams(testSource, Arrays.asList("v:3", "a:6", "s:6"), testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput5));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput5));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                FFmpeg.getStreams(testOutput5).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.removeStreams(testSource, Arrays.asList("v:-1", "a:-1", "s:-1"), testOutput6);
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput6));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput6));
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                FFmpeg.getStreams(testOutput6).stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
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
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        for (FFmpeg.StreamMetadata stream : FFmpeg.getStreams(testSource)) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        
        //metadata
        VideoUtility.stripMetadataAndChapters(testSource, true, false, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        for (FFmpeg.StreamMetadata stream : FFmpeg.getStreams(testOutput1)) {
            Assert.assertNull(stream.getTitle());
            Assert.assertNull(stream.getLanguage());
            Assert.assertEquals(1, stream.getTags().size());
            Assert.assertTrue(stream.containsTag("duration"));
        }
        
        //chapters
        VideoUtility.stripMetadataAndChapters(testSource, false, true, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput2));
        for (FFmpeg.StreamMetadata stream : FFmpeg.getStreams(testOutput2)) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        
        //metadata and chapters
        VideoUtility.stripMetadataAndChapters(testSource, true, true, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput2));
        for (FFmpeg.StreamMetadata stream : FFmpeg.getStreams(testOutput2)) {
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
     * JUnit test of getDuration.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getDuration(File)
     */
    @Test
    public void testGetDuration() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class);
        final File testVideo = new File(testResources, "test.mkv");
        VideoUtility.getDuration(testVideo);
        PowerMockito.verifyStatic(FFmpeg.class);
        FFmpeg.getStreamDuration(ArgumentMatchers.eq(testVideo), ArgumentMatchers.eq(FFmpeg.StreamType.VIDEO));
    }
    
    /**
     * JUnit test of getBitrate.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getBitrate(File)
     */
    @Test
    public void testGetBitrate() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class);
        final File testVideo = new File(testResources, "test.mkv");
        VideoUtility.getBitrate(testVideo);
        PowerMockito.verifyStatic(FFmpeg.class);
        FFmpeg.getStreamBitrate(ArgumentMatchers.eq(testVideo), ArgumentMatchers.eq(FFmpeg.StreamType.VIDEO));
    }
    
    /**
     * JUnit test of getEncoding.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getEncoding(File)
     */
    @Test
    public void testGetEncoding() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class);
        final File testVideo = new File(testResources, "test.mkv");
        VideoUtility.getEncoding(testVideo);
        PowerMockito.verifyStatic(FFmpeg.class);
        FFmpeg.getEncoding(ArgumentMatchers.eq(testVideo), ArgumentMatchers.eq(FFmpeg.StreamType.VIDEO));
    }
    
    /**
     * JUnit test of getFrameCount.
     *
     * @throws Exception When there is an exception.
     * @see VideoUtility#getFrameCount(File)
     */
    @Test
    public void testGetFrameCount() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class);
        final File testVideo = new File(testResources, "test.mkv");
        VideoUtility.getFrameCount(testVideo);
        PowerMockito.verifyStatic(FFmpeg.class);
        FFmpeg.getFrameCount(ArgumentMatchers.eq(testVideo), ArgumentMatchers.eq(FFmpeg.StreamType.VIDEO));
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
        Assert.assertEquals(3067L, FFmpeg.getDuration(testSource));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        
        //timestamp
        VideoUtility.cutVideo(testSource, "00:00:00.000", "00:00:01.000", testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(1239L, FFmpeg.getDuration(testOutput1));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(1, FFmpeg.getChapterCount(testOutput1));
        VideoUtility.cutVideo(testSource2, "00:00:00.000", "00:00:01.000", testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(1000L, FFmpeg.getDuration(testOutput2));
        Assert.assertEquals(1, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(1, FFmpeg.getChapterCount(testOutput2));
        
        //milliseconds
        VideoUtility.cutVideo(testSource, 500, 2500, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(2416, FFmpeg.getDuration(testOutput3));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput3));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput3));
        VideoUtility.cutVideo(testSource2, 500, 2500, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(2000L, FFmpeg.getDuration(testOutput4));
        Assert.assertEquals(2, FFmpeg.getStreamCount(testOutput4));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput4));
        
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
        List<FFmpeg.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        streams = FFmpeg.getStreams(testSource);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
            if (stream.getStreamType() == FFmpeg.StreamType.VIDEO) {
                Assert.assertEquals(320, stream.getVideoMetadata().getWidth());
                Assert.assertEquals(240, stream.getVideoMetadata().getHeight());
            }
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //scale down
        VideoUtility.scaleVideo(testSource, 120, 100, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        streams = FFmpeg.getStreams(testOutput1);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
            if (stream.getStreamType() == FFmpeg.StreamType.VIDEO) {
                Assert.assertEquals(120, stream.getVideoMetadata().getWidth());
                Assert.assertEquals(100, stream.getVideoMetadata().getHeight());
            }
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //scale up
        VideoUtility.scaleVideo(testSource, 1600, 1200, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput2));
        streams = FFmpeg.getStreams(testOutput2);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
            if (stream.getStreamType() == FFmpeg.StreamType.VIDEO) {
                Assert.assertEquals(1600, stream.getVideoMetadata().getWidth());
                Assert.assertEquals(1200, stream.getVideoMetadata().getHeight());
            }
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //invalid
        VideoUtility.scaleVideo(testSource, -1, -1, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        FFmpeg.getStreams(testOutput3).stream().filter(e -> e.getStreamType() == FFmpeg.StreamType.VIDEO).forEach(e -> {
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
        List<FFmpeg.StreamMetadata> streams;
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        streams = FFmpeg.getStreams(testSource);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|hevc|mpeg4|mp3|aac|flac|aac|opus|vorbis|subrip|subrip|subrip|subrip|subrip|subrip",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //low quality
        VideoUtility.adjustQuality(testSource, 40, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertTrue(Filesystem.sizeCompare(testOutput1, testSource) < 0);
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput1));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput1));
        streams = FFmpeg.getStreams(testOutput1);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //high quality
        VideoUtility.adjustQuality(testSource, 4, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertTrue(Filesystem.sizeCompare(testOutput2, testSource) < 0);
        Assert.assertTrue(Filesystem.sizeCompare(testOutput2, testOutput1) > 0);
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput2));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput2));
        streams = FFmpeg.getStreams(testOutput2);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
        //limits
        VideoUtility.adjustQuality(testSource, 0, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertTrue(Filesystem.sizeCompare(testOutput3, testSource) < 0);
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput3));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput3));
        streams = FFmpeg.getStreams(testOutput3);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        VideoUtility.adjustQuality(testSource, 51, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertTrue(Filesystem.sizeCompare(testOutput4, testSource) < 0);
        Assert.assertEquals(15, FFmpeg.getStreamCount(testOutput4));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testOutput4));
        streams = FFmpeg.getStreams(testOutput4);
        for (FFmpeg.StreamMetadata stream : streams) {
            Assert.assertNotNull(stream.getTitle());
            Assert.assertNotNull(stream.getLanguage());
            Assert.assertTrue(stream.getTags().size() >= 3);
        }
        Assert.assertEquals("h264|h264|h264|vorbis|vorbis|vorbis|vorbis|vorbis|vorbis|ass|ass|ass|ass|ass|ass",
                streams.stream().map(FFmpeg.StreamMetadata::getCodecName).collect(Collectors.joining("|")));
        
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
     * @see VideoUtility#extractStream(File, FFmpeg.StreamType, int, File)
     * @see VideoUtility#extractStream(File, FFmpeg.StreamType, File)
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
        final List<FFmpeg.StreamMetadata> sourceStreams = FFmpeg.getStreams(testSource);
        FFmpeg.StreamMetadata sourceStream;
        File output;
        List<FFmpeg.StreamMetadata> outputStreams;
        FFmpeg.StreamMetadata outputStream;
        final File fakeOutput = new File(testDir, "fakeTest.mkv");
        
        //initial
        Assert.assertEquals(15, FFmpeg.getStreamCount(testSource));
        Assert.assertEquals(3, FFmpeg.getChapterCount(testSource));
        
        //index
        for (int i = 0; i < sourceStreams.size(); i++) {
            output = new File(testDir, i + "test.mkv");
            VideoUtility.extractStream(testSource, i, output);
            Assert.assertTrue(output.exists());
            Assert.assertEquals(1, FFmpeg.getStreamCount(output));
            Assert.assertEquals(0, FFmpeg.getChapterCount(output));
            sourceStream = sourceStreams.get(i);
            outputStream = FFmpeg.getStream(output, 0);
            Assert.assertEquals(sourceStream.getCodecName(), outputStream.getCodecName());
            Assert.assertEquals(sourceStream.getTitle(), outputStream.getTitle());
            Assert.assertEquals(sourceStream.getLanguage(), outputStream.getLanguage());
        }
        
        //stream type
        for (int i = -1; i < FFmpeg.getStreamCount(testSource, FFmpeg.StreamType.VIDEO); i++) {
            output = new File(testDir, "v" + i + "test.mkv");
            if (i >= 0) {
                VideoUtility.extractStream(testSource, FFmpeg.StreamType.VIDEO, i, output);
            } else {
                VideoUtility.extractStream(testSource, FFmpeg.StreamType.VIDEO, output);
            }
            Assert.assertTrue(output.exists());
            Assert.assertEquals(1, FFmpeg.getStreamCount(output));
            Assert.assertEquals(0, FFmpeg.getChapterCount(output));
            sourceStream = FFmpeg.getStream(testSource, FFmpeg.StreamType.VIDEO, Math.max(i, 0));
            outputStream = FFmpeg.getStream(output, 0);
            Assert.assertEquals(sourceStream.getCodecName(), outputStream.getCodecName());
            Assert.assertEquals(sourceStream.getTitle(), outputStream.getTitle());
            Assert.assertEquals(sourceStream.getLanguage(), outputStream.getLanguage());
        }
        for (int i = -1; i < FFmpeg.getStreamCount(testSource, FFmpeg.StreamType.AUDIO); i++) {
            output = new File(testDir, "a" + i + "test.mkv");
            if (i >= 0) {
                VideoUtility.extractStream(testSource, FFmpeg.StreamType.AUDIO, i, output);
            } else {
                VideoUtility.extractStream(testSource, FFmpeg.StreamType.AUDIO, output);
            }
            Assert.assertTrue(output.exists());
            Assert.assertEquals(1, FFmpeg.getStreamCount(output));
            Assert.assertEquals(0, FFmpeg.getChapterCount(output));
            sourceStream = FFmpeg.getStream(testSource, FFmpeg.StreamType.AUDIO, Math.max(i, 0));
            outputStream = FFmpeg.getStream(output, 0);
            Assert.assertEquals(sourceStream.getCodecName(), outputStream.getCodecName());
            Assert.assertEquals(sourceStream.getTitle(), outputStream.getTitle());
            Assert.assertEquals(sourceStream.getLanguage(), outputStream.getLanguage());
        }
        for (int i = -1; i < FFmpeg.getStreamCount(testSource, FFmpeg.StreamType.SUBTITLE); i++) {
            output = new File(testDir, "s" + i + "test.mkv");
            if (i >= 0) {
                VideoUtility.extractStream(testSource, FFmpeg.StreamType.SUBTITLE, i, output);
            } else {
                VideoUtility.extractStream(testSource, FFmpeg.StreamType.SUBTITLE, output);
            }
            Assert.assertTrue(output.exists());
            Assert.assertEquals(1, FFmpeg.getStreamCount(output));
            Assert.assertEquals(0, FFmpeg.getChapterCount(output));
            sourceStream = FFmpeg.getStream(testSource, FFmpeg.StreamType.SUBTITLE, Math.max(i, 0));
            outputStream = FFmpeg.getStream(output, 0);
            Assert.assertEquals(sourceStream.getCodecName(), outputStream.getCodecName());
            Assert.assertEquals(sourceStream.getTitle(), outputStream.getTitle());
            Assert.assertEquals(sourceStream.getLanguage(), outputStream.getLanguage());
        }
        
        //to file type
        output = new File(testDir, "test.mp4");
        VideoUtility.extractStream(testSource, 0, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        Assert.assertEquals(0, FFmpeg.getChapterCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("h264", outputStreams.get(0).getCodecName());
        Assert.assertNull(outputStreams.get(0).getTitle());
        Assert.assertEquals("eng", outputStreams.get(0).getLanguage());
        output = new File(testDir, "test.avi");
        VideoUtility.extractStream(testSource, 1, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        Assert.assertEquals(0, FFmpeg.getChapterCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("rawvideo", outputStreams.get(0).getCodecName());
        Assert.assertEquals("Green", outputStreams.get(0).getTitle());
        Assert.assertNull(outputStreams.get(0).getLanguage());
        output = new File(testDir, "test.m4v");
        VideoUtility.extractStream(testSource, 2, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        Assert.assertEquals(0, FFmpeg.getChapterCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("mpeg4", outputStreams.get(0).getCodecName());
        Assert.assertNull(outputStreams.get(0).getTitle());
        Assert.assertEquals("rus", outputStreams.get(0).getLanguage());
        output = new File(testDir, "test.mp3");
        VideoUtility.extractStream(testSource, 3, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("mp3", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.aac");
        VideoUtility.extractStream(testSource, 4, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("aac", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.flac");
        VideoUtility.extractStream(testSource, 5, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("flac", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.m4a");
        VideoUtility.extractStream(testSource, 6, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("aac", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.opus");
        VideoUtility.extractStream(testSource, 7, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("opus", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test.ogg");
        VideoUtility.extractStream(testSource, 8, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("vorbis", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test1.srt");
        VideoUtility.extractStream(testSource, 9, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test2.srt");
        VideoUtility.extractStream(testSource, 10, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test3.srt");
        VideoUtility.extractStream(testSource, 11, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test4.srt");
        VideoUtility.extractStream(testSource, 12, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test5.srt");
        VideoUtility.extractStream(testSource, 13, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        output = new File(testDir, "test6.srt");
        VideoUtility.extractStream(testSource, 14, output);
        Assert.assertTrue(output.exists());
        Assert.assertFalse(Filesystem.isEmpty(output));
        Assert.assertEquals(1, FFmpeg.getStreamCount(output));
        outputStreams = FFmpeg.getStreams(output);
        Assert.assertEquals("subrip", outputStreams.get(0).getCodecName());
        
        //invalid
        Assert.assertTrue(VideoUtility.extractStream(testSource, FFmpeg.StreamType.VIDEO, 3, fakeOutput).contains(
                "[*]Stream map '0:v:3' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, FFmpeg.StreamType.VIDEO, -1, fakeOutput).contains(
                "[*]Stream map '0:v:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, FFmpeg.StreamType.AUDIO, 6, fakeOutput).contains(
                "[*]Stream map '0:a:6' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, FFmpeg.StreamType.AUDIO, -1, fakeOutput).contains(
                "[*]Stream map '0:a:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, FFmpeg.StreamType.SUBTITLE, 6, fakeOutput).contains(
                "[*]Stream map '0:s:6' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, FFmpeg.StreamType.SUBTITLE, -1, fakeOutput).contains(
                "[*]Stream map '0:s:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, FFmpeg.StreamType.DATA, fakeOutput).contains(
                "[*]Stream map '0:d:0' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, null, 15, fakeOutput).contains(
                "[*]Stream map '0:15' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, null, -1, fakeOutput).contains(
                "[*]Stream map '0:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, 15, fakeOutput).contains(
                "[*]Stream map '0:15' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(testSource, -1, fakeOutput).contains(
                "[*]Stream map '0:-1' matches no streams."));
        Assert.assertTrue(VideoUtility.extractStream(new File(testResources, "fakeVideo.mp4"), FFmpeg.StreamType.VIDEO, 0, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        Assert.assertTrue(VideoUtility.extractStream(new File(testResources, "fakeVideo.mp4"), FFmpeg.StreamType.VIDEO, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        Assert.assertTrue(VideoUtility.extractStream(new File(testResources, "fakeVideo.mp4"), 0, fakeOutput).contains(
                "fakeVideo.mp4: No such file or directory"));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, FFmpeg.StreamType.SUBTITLE, 0, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, FFmpeg.StreamType.SUBTITLE, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, 1, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(testSource, FFmpeg.StreamType.SUBTITLE, 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(testSource, FFmpeg.StreamType.SUBTITLE, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(testSource, 1, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, FFmpeg.StreamType.SUBTITLE, 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                VideoUtility.extractStream(null, FFmpeg.StreamType.SUBTITLE, null));
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
                ImageUtility.saveImage(image, new File(frameDir, "distraction_" + UUID.randomUUID() + ".png"));
            }
        }
        Filesystem.writeStringToFile(new File(frameDir, "distraction.txt"), "test");
        
        //name pattern and fps
        VideoUtility.encodeFramesToVideo(frameDir, "frame_%03d.png", 10, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(30L, VideoUtility.getFrameCount(testOutput1));
        Assert.assertEquals(3000L, FFmpeg.getDuration(testOutput1));
        
        //frame list and fps
        VideoUtility.encodeFramesToVideo(frames, 5, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(30L, VideoUtility.getFrameCount(testOutput2));
        Assert.assertEquals(6000L, FFmpeg.getDuration(testOutput2));
        
        //frame list and durations
        VideoUtility.encodeFramesToVideo(frames, durations, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(31L, VideoUtility.getFrameCount(testOutput3));
        Assert.assertTrue(Math.abs(totalDuration - FFmpeg.getDuration(testOutput3)) < (totalDuration * 0.01));
        
        //mixed image types
        VideoUtility.encodeFramesToVideo(badFrames, 10, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(15L, VideoUtility.getFrameCount(testOutput4));
        Assert.assertEquals(1500L, FFmpeg.getDuration(testOutput4));
        VideoUtility.encodeFramesToVideo(badFrames, durations, testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(15L, VideoUtility.getFrameCount(testOutput5));
        System.out.println(totalBadDuration);
        System.out.println(FFmpeg.getDuration(testOutput5));
        Assert.assertTrue(Math.abs(totalBadDuration - FFmpeg.getDuration(testOutput5)) < (totalBadDuration * 0.01));
        
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
    
}
