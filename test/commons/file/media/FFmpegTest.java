/*
 * File:    FFmpegTest.java
 * Package: commons.file.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.file.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import commons.access.CmdLine;
import commons.access.Filesystem;
import commons.access.Project;
import commons.console.Console;
import commons.console.ProgressBar;
import commons.log.CommonsLogging;
import commons.object.string.StringUtility;
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
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.exceptions.MethodInvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of FFmpeg.
 *
 * @see FFmpeg
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({FFmpeg.class, FFmpeg.Implements.class, FFmpeg.Identifier.class, FFmpeg.Identifier.Stream.class, FFmpeg.Identifier.Chapter.class, FFmpeg.Identifier.Global.class, CmdLine.class, CommonsLogging.class, TestUtils.AssertWrapper.class})
public class FFmpegTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FFmpegTest.class);
    
    
    //Constants
    
    /**
     * The directory containing resources for this test class.
     */
    private static final File testResources = Project.testResourcesDir(FFmpeg.class);
    
    
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
        
        FFmpeg.setFFmpegExecutable(null);
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffmpeg"));
        FFmpeg.setFFprobeExecutable(null);
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffprobe"));
        FFmpeg.setFFplayExecutable(null);
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffplay"));
        
        FFmpeg.setMaxMuxingQueueSize(1024);
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @After
    public void cleanup() throws Exception {
        FFmpeg.setFFmpegExecutable(null);
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffmpeg"));
        FFmpeg.setFFprobeExecutable(null);
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffprobe"));
        FFmpeg.setFFplayExecutable(null);
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffplay"));
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
     * @see FFmpeg.StreamType
     */
    @Test
    public void testStreamType() throws Exception {
        Assert.assertEquals(4, FFmpeg.StreamType.values().length);
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, FFmpeg.StreamType.values()[0]);
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, FFmpeg.StreamType.values()[1]);
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, FFmpeg.StreamType.values()[2]);
        Assert.assertEquals(FFmpeg.StreamType.DATA, FFmpeg.StreamType.values()[3]);
    }
    
    /**
     * JUnit test of ffmpegExists.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffmpegExists()
     */
    @Test
    public void testFFmpegExists() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        final String sampleResponse = "ffmpeg version 4.3.1 Copyright (c) 2000-2020 the FFmpeg developers\nbuilt with gcc 10.2.1 (GCC) 20200726\nconfiguration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libdav1d --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-li\nbsoxr --enable-libsrt --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvmaf --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --ena\nble-libxvid --enable-libaom --enable-libgsm --enable-librav1e --disable-w32threads --enable-libmfx --enable-ffnvcodec --enable-cuda-llvm --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth --enable-libopenmpt --enable-amf\nlibavutil      56. 51.100 / 56. 51.100\nlibavcodec     58. 91.100 / 58. 91.100\nlibavformat    58. 45.100 / 58. 45.100\nlibavdevice    58. 10.100 / 58. 10.100\nlibavfilter     7. 85.100 /  7. 85.100\nlibswscale      5.  7.100 /  5.  7.100\nlibswresample   3.  7.100 /  3.  7.100\nlibpostproc    55.  7.100 / 55.  7.100\n";
        final String sampleNegativeResponse = StringUtility.quote(testExecutable.getAbsolutePath()) + " is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffmpeg -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -version"))
                .thenReturn(sampleNegativeResponse).thenReturn("");
        Assert.assertTrue(FFmpeg.ffmpegExists());
        FFmpeg.setFFmpegExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffmpeg")).getAbsolutePath());
        Assert.assertFalse(FFmpeg.ffmpegExists());
        Assert.assertFalse(FFmpeg.ffmpegExists());
    }
    
    /**
     * JUnit test of ffprobeExists.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffprobeExists()
     */
    @Test
    public void testFFprobeExists() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffprobe.exe");
        final String sampleResponse = "ffprobe version 4.3.1 Copyright (c) 2000-2020 the FFmpeg developers\nbuilt with gcc 10.2.1 (GCC) 20200726\nconfiguration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libdav1d --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-li\nbsoxr --enable-libsrt --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvmaf --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --ena\nble-libxvid --enable-libaom --enable-libgsm --enable-librav1e --disable-w32threads --enable-libmfx --enable-ffnvcodec --enable-cuda-llvm --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth --enable-libopenmpt --enable-amf\nlibavutil      56. 51.100 / 56. 51.100\nlibavcodec     58. 91.100 / 58. 91.100\nlibavformat    58. 45.100 / 58. 45.100\nlibavdevice    58. 10.100 / 58. 10.100\nlibavfilter     7. 85.100 /  7. 85.100\nlibswscale      5.  7.100 /  5.  7.100\nlibswresample   3.  7.100 /  3.  7.100\nlibpostproc    55.  7.100 / 55.  7.100\n";
        final String sampleNegativeResponse = StringUtility.quote(testExecutable.getAbsolutePath()) + " is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffprobe -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -version"))
                .thenReturn(sampleNegativeResponse).thenReturn("");
        Assert.assertTrue(FFmpeg.ffprobeExists());
        FFmpeg.setFFprobeExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffprobe")).getAbsolutePath());
        Assert.assertFalse(FFmpeg.ffprobeExists());
        Assert.assertFalse(FFmpeg.ffprobeExists());
    }
    
    /**
     * JUnit test of ffplayExists.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffplayExists()
     */
    @Test
    public void testFFplayExists() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffplay.exe");
        final String sampleResponse = "ffplay version 4.3.1 Copyright (c) 2000-2020 the FFmpeg developers\nbuilt with gcc 10.2.1 (GCC) 20200726\nconfiguration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libdav1d --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-li\nbsoxr --enable-libsrt --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvmaf --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --ena\nble-libxvid --enable-libaom --enable-libgsm --enable-librav1e --disable-w32threads --enable-libmfx --enable-ffnvcodec --enable-cuda-llvm --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth --enable-libopenmpt --enable-amf\nlibavutil      56. 51.100 / 56. 51.100\nlibavcodec     58. 91.100 / 58. 91.100\nlibavformat    58. 45.100 / 58. 45.100\nlibavdevice    58. 10.100 / 58. 10.100\nlibavfilter     7. 85.100 /  7. 85.100\nlibswscale      5.  7.100 /  5.  7.100\nlibswresample   3.  7.100 /  3.  7.100\nlibpostproc    55.  7.100 / 55.  7.100\n";
        final String sampleNegativeResponse = StringUtility.quote(testExecutable.getAbsolutePath()) + " is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffplay -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -version"))
                .thenReturn(sampleNegativeResponse).thenReturn("");
        Assert.assertTrue(FFmpeg.ffplayExists());
        FFmpeg.setFFplayExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffplay")).getAbsolutePath());
        Assert.assertFalse(FFmpeg.ffplayExists());
        Assert.assertFalse(FFmpeg.ffplayExists());
    }
    
    /**
     * JUnit test of ffmpegVersion.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffmpegVersion()
     */
    @Test
    public void testFFmpegVersion() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        final String sampleResponse = "ffmpeg version 4.3.1 Copyright (c) 2000-2020 the FFmpeg developers\nbuilt with gcc 10.2.1 (GCC) 20200726\nconfiguration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libdav1d --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-li\nbsoxr --enable-libsrt --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvmaf --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --ena\nble-libxvid --enable-libaom --enable-libgsm --enable-librav1e --disable-w32threads --enable-libmfx --enable-ffnvcodec --enable-cuda-llvm --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth --enable-libopenmpt --enable-amf\nlibavutil      56. 51.100 / 56. 51.100\nlibavcodec     58. 91.100 / 58. 91.100\nlibavformat    58. 45.100 / 58. 45.100\nlibavdevice    58. 10.100 / 58. 10.100\nlibavfilter     7. 85.100 /  7. 85.100\nlibswscale      5.  7.100 /  5.  7.100\nlibswresample   3.  7.100 /  3.  7.100\nlibpostproc    55.  7.100 / 55.  7.100\n";
        final String sampleNegativeResponse = StringUtility.quote(testExecutable.getAbsolutePath()) + " is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffmpeg -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -version"))
                .thenReturn(sampleNegativeResponse).thenReturn("");
        Assert.assertEquals("4.3.1", FFmpeg.ffmpegVersion());
        FFmpeg.setFFmpegExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffmpeg")).getAbsolutePath());
        Assert.assertNull(FFmpeg.ffmpegVersion());
        Assert.assertNull(FFmpeg.ffmpegVersion());
    }
    
    /**
     * JUnit test of ffprobeVersion.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffprobeVersion()
     */
    @Test
    public void testFFprobeVersion() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffprobe.exe");
        final String sampleResponse = "ffprobe version 4.3.1 Copyright (c) 2000-2020 the FFmpeg developers\nbuilt with gcc 10.2.1 (GCC) 20200726\nconfiguration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libdav1d --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-li\nbsoxr --enable-libsrt --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvmaf --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --ena\nble-libxvid --enable-libaom --enable-libgsm --enable-librav1e --disable-w32threads --enable-libmfx --enable-ffnvcodec --enable-cuda-llvm --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth --enable-libopenmpt --enable-amf\nlibavutil      56. 51.100 / 56. 51.100\nlibavcodec     58. 91.100 / 58. 91.100\nlibavformat    58. 45.100 / 58. 45.100\nlibavdevice    58. 10.100 / 58. 10.100\nlibavfilter     7. 85.100 /  7. 85.100\nlibswscale      5.  7.100 /  5.  7.100\nlibswresample   3.  7.100 /  3.  7.100\nlibpostproc    55.  7.100 / 55.  7.100\n";
        final String sampleNegativeResponse = StringUtility.quote(testExecutable.getAbsolutePath()) + " is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffprobe -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -version"))
                .thenReturn(sampleNegativeResponse).thenReturn("");
        Assert.assertEquals("4.3.1", FFmpeg.ffprobeVersion());
        FFmpeg.setFFprobeExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffprobe")).getAbsolutePath());
        Assert.assertNull(FFmpeg.ffprobeVersion());
        Assert.assertNull(FFmpeg.ffprobeVersion());
    }
    
    /**
     * JUnit test of ffplayVersion.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffplayVersion()
     */
    @Test
    public void testFFplayVersion() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffplay.exe");
        final String sampleResponse = "ffplay version 4.3.1 Copyright (c) 2000-2020 the FFmpeg developers\nbuilt with gcc 10.2.1 (GCC) 20200726\nconfiguration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libdav1d --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-li\nbsoxr --enable-libsrt --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvmaf --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --ena\nble-libxvid --enable-libaom --enable-libgsm --enable-librav1e --disable-w32threads --enable-libmfx --enable-ffnvcodec --enable-cuda-llvm --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth --enable-libopenmpt --enable-amf\nlibavutil      56. 51.100 / 56. 51.100\nlibavcodec     58. 91.100 / 58. 91.100\nlibavformat    58. 45.100 / 58. 45.100\nlibavdevice    58. 10.100 / 58. 10.100\nlibavfilter     7. 85.100 /  7. 85.100\nlibswscale      5.  7.100 /  5.  7.100\nlibswresample   3.  7.100 /  3.  7.100\nlibpostproc    55.  7.100 / 55.  7.100\n";
        final String sampleNegativeResponse = StringUtility.quote(testExecutable.getAbsolutePath()) + " is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffplay -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -version"))
                .thenReturn(sampleNegativeResponse).thenReturn("");
        Assert.assertEquals("4.3.1", FFmpeg.ffplayVersion());
        FFmpeg.setFFplayExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffplay")).getAbsolutePath());
        Assert.assertNull(FFmpeg.ffplayVersion());
        Assert.assertNull(FFmpeg.ffplayVersion());
    }
    
    /**
     * JUnit test of ffmpeg.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffmpeg(String, FFmpeg.FFmpegProgressBar)
     * @see FFmpeg#ffmpeg(String)
     * @see FFmpeg#ffmpeg(String, List, String, File, boolean)
     * @see FFmpeg#ffmpeg(List, String, File, boolean)
     * @see FFmpeg#ffmpeg(String, List, String, File)
     * @see FFmpeg#ffmpeg(List, String, File)
     * @see FFmpeg#ffmpeg(String, File, String, File, boolean)
     * @see FFmpeg#ffmpeg(File, String, File, boolean)
     * @see FFmpeg#ffmpeg(String, File, String, File)
     * @see FFmpeg#ffmpeg(File, String, File)
     */
    @Test
    public void testFFmpeg() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        PowerMockito.mockStatic(FFmpeg.FFmpegProgressBar.class);
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        final File testFile1 = new File(testResources, "null1.mp4");
        final File testFile2 = new File(testResources, "null2.mp4");
        final File testFile3 = new File(testResources, "null3.mp4");
        final File testFile4 = new File(testResources, "null4.mp4");
        final FFmpeg.FFmpegProgressBar mockFFmpegProgressBar = Mockito.mock(FFmpeg.FFmpegProgressBar.class, Mockito.CALLS_REAL_METHODS);
        TestUtils.setField(mockFFmpegProgressBar, "title", "");
        TestUtils.setField(mockFFmpegProgressBar, "firstPrint", new AtomicBoolean(true));
        
        //standard
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //standard, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath()), mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath()), null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath()), mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath()), null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //files
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //files, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -progress - -nostats -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -progress - -nostats -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //files, default progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -progress - -nostats -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -progress - -nostats -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.doReturn(false).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.doReturn(true).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        
        //files, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //files, input params, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //files, input params, default progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.doReturn(false).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.doReturn(true).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        
        //file list
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //file list, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -progress - -nostats -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -progress - -nostats -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //file list, default progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -progress - -nostats -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -progress - -nostats -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.doReturn(false).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.doReturn(true).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        
        //file list, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //file list, input params, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //file list, input params, default progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.doReturn(false).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.doReturn(true).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        
        //progress bar name
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        Mockito.doCallRealMethod().when(mockFFmpegProgressBar).getTitle();
        Mockito.doCallRealMethod().when(mockFFmpegProgressBar).updateTitle(ArgumentMatchers.anyString());
        Assert.assertTrue(TestUtils.setField(mockFFmpegProgressBar, "title", ""));
        Assert.assertEquals("", TestUtils.getField(mockFFmpegProgressBar, "title"));
        Assert.assertTrue(TestUtils.setField(mockFFmpegProgressBar, "firstPrint", new AtomicBoolean(true)));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(mockFFmpegProgressBar, "firstPrint")).get());
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, mockFFmpegProgressBar);
        Assert.assertEquals("FFmpeg " + Console.ConsoleEffect.GREY.apply("-hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())),
                TestUtils.getField(mockFFmpegProgressBar, "title"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(mockFFmpegProgressBar, "firstPrint")).get());
        Assert.assertTrue(TestUtils.setField(mockFFmpegProgressBar, "title", "Test FFmpeg Progress Bar"));
        Assert.assertEquals("Test FFmpeg Progress Bar", TestUtils.getField(mockFFmpegProgressBar, "title"));
        Assert.assertTrue(TestUtils.setField(mockFFmpegProgressBar, "firstPrint", new AtomicBoolean(true)));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(mockFFmpegProgressBar, "firstPrint")).get());
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, mockFFmpegProgressBar);
        Assert.assertEquals("Test FFmpeg Progress Bar",
                TestUtils.getField(mockFFmpegProgressBar, "title"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(mockFFmpegProgressBar, "firstPrint")).get());
        Assert.assertTrue(TestUtils.setField(mockFFmpegProgressBar, "title", ""));
        Assert.assertEquals("", TestUtils.getField(mockFFmpegProgressBar, "title"));
        Assert.assertTrue(TestUtils.setField(mockFFmpegProgressBar, "firstPrint", new AtomicBoolean(false)));
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(mockFFmpegProgressBar, "firstPrint")).get());
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, mockFFmpegProgressBar);
        Assert.assertEquals("",
                TestUtils.getField(mockFFmpegProgressBar, "title"));
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(mockFFmpegProgressBar, "firstPrint")).get());
        Assert.assertTrue(TestUtils.setField(mockFFmpegProgressBar, "title", ""));
        Assert.assertEquals("", TestUtils.getField(mockFFmpegProgressBar, "title"));
        Assert.assertTrue(TestUtils.setField(mockFFmpegProgressBar, "firstPrint", new AtomicBoolean(true)));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(mockFFmpegProgressBar, "firstPrint")).get());
        FFmpeg.ffmpeg("-hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath()), mockFFmpegProgressBar);
        Assert.assertEquals("",
                TestUtils.getField(mockFFmpegProgressBar, "title"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(mockFFmpegProgressBar, "firstPrint")).get());
        
        //default progress bar name
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        PowerMockito.doAnswer((Answer<String>) invocation -> {
            Assert.assertEquals("FFmpeg " + Console.ConsoleEffect.GREY.apply("-hide_banner -progress - -nostats -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())),
                    ((FFmpeg.FFmpegProgressBar) invocation.getArgument(1)).getTitle());
            return "";
        }).when(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.anyString(), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, true);
        PowerMockito.doReturn("").when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
    }
    
    /**
     * JUnit test of ffmpegAsync.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffmpegAsync(String)
     * @see FFmpeg#ffmpegAsync(String, List, String, File)
     * @see FFmpeg#ffmpegAsync(List, String, File)
     * @see FFmpeg#ffmpegAsync(String, File, String, File)
     * @see FFmpeg#ffmpegAsync(File, String, File)
     */
    @Test
    public void testFFmpegAsync() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        PowerMockito.mockStatic(FFmpeg.FFmpegProgressBar.class);
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        final File testFile1 = new File(testResources, "null1.mp4");
        final File testFile2 = new File(testResources, "null2.mp4");
        final File testFile3 = new File(testResources, "null3.mp4");
        final File testFile4 = new File(testResources, "null4.mp4");
        final FFmpeg.FFmpegProgressBar mockFFmpegProgressBar = Mockito.mock(FFmpeg.FFmpegProgressBar.class);
        Mockito.doReturn("Test FFmpeg Progress Bar").when(mockFFmpegProgressBar).getTitle();
        
        //standard
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync("-y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync("-y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //files
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //files, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //file list
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -y -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        
        //file list, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -y -i " + StringUtility.quote(testFile1.getAbsolutePath()) + " -i " + StringUtility.quote(testFile2.getAbsolutePath()) + " -i " + StringUtility.quote(testFile3.getAbsolutePath()) + " -c:copy " + StringUtility.quote(testFile4.getAbsolutePath())));
    }
    
    /**
     * JUnit test of ffprobe.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffprobe(String)
     * @see FFmpeg#ffprobe(String, File)
     * @see FFmpeg#ffprobe(File)
     */
    @Test
    public void testFFprobe() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffprobe.exe");
        final File testFile1 = new File(testResources, "null1.mp4");
        
        //standard
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobe("-v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobe("-v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobe("-v quiet -show_entries format=duration -of csv=p=0:e=none", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobe("-v quiet -show_entries format=duration -of csv=p=0:e=none", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        
        //file
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobe(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobe(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner " + StringUtility.quote(testFile1.getAbsolutePath())));
    }
    
    /**
     * JUnit test of ffprobeAsync.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffprobeAsync(String)
     * @see FFmpeg#ffprobeAsync(String, File)
     * @see FFmpeg#ffprobeAsync(File)
     */
    @Test
    public void testFFprobeAsync() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffprobe.exe");
        final File testFile1 = new File(testResources, "null1.mp4");
        
        //standard
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobeAsync("-v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffprobe -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobeAsync("-v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobeAsync("-v quiet -show_entries format=duration -of csv=p=0:e=none", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobeAsync("-v quiet -show_entries format=duration -of csv=p=0:e=none", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -v quiet -show_entries format=duration -of csv=p=0:e=none " + StringUtility.quote(testFile1.getAbsolutePath())));
        
        //file
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobeAsync(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffprobe -hide_banner " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobeAsync(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner " + StringUtility.quote(testFile1.getAbsolutePath())));
    }
    
    /**
     * JUnit test of ffplay.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffplay(String)
     * @see FFmpeg#ffplay(String, File)
     * @see FFmpeg#ffplay(File)
     */
    @Test
    public void testFFplay() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffplay.exe");
        final File testFile1 = new File(testResources, "null1.mp4");
        
        //standard
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplay("-autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffplay -autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplay("-autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath())));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplay("-autoexit -nodisp", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffplay -hide_banner -autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplay("-autoexit -nodisp", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath())));
        
        //file
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplay(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffplay -hide_banner " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplay(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner " + StringUtility.quote(testFile1.getAbsolutePath())));
    }
    
    /**
     * JUnit test of ffplayAsync.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffplayAsync(String)
     * @see FFmpeg#ffplayAsync(String, File)
     * @see FFmpeg#ffplayAsync(File)
     */
    @Test
    public void testFFplayAsync() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffplay.exe");
        final File testFile1 = new File(testResources, "null1.mp4");
        
        //standard
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplayAsync("-autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffplay -autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplayAsync("-autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath()));
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath())));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplayAsync("-autoexit -nodisp", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffplay -hide_banner -autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplayAsync("-autoexit -nodisp", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner -autoexit -nodisp " + StringUtility.quote(testFile1.getAbsolutePath())));
        
        //file
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplayAsync(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffplay -hide_banner " + StringUtility.quote(testFile1.getAbsolutePath())));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplayAsync(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq(StringUtility.quote(testExecutable.getAbsolutePath()) + " -hide_banner " + StringUtility.quote(testFile1.getAbsolutePath())));
    }
    
    /**
     * JUnit test of getMediaInfo.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getMediaInfo(File)
     */
    @Test
    public void testGetMediaInfo() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        FFmpeg.MediaInfo mediaInfo;
        
        //standard
        mediaInfo = FFmpeg.getMediaInfo(testVideo);
        Assert.assertNotNull(mediaInfo);
        Assert.assertNotNull(mediaInfo.getFormat());
        Assert.assertNotNull(mediaInfo.getStreams());
        Assert.assertNotNull(mediaInfo.getChapters());
        Assert.assertEquals(((JSONObject) new JSONParser().parse(Filesystem.readFileToString(new File(testResources, "test.mkv.json")))).toJSONString(),
                mediaInfo.getData().toJSONString().replace(testVideo.getAbsolutePath().replace("\\", "\\\\"), testVideo.getName()));
        Assert.assertEquals(FFmpeg.getFormat(testVideo).getData().toJSONString(), mediaInfo.getFormat().getData().toJSONString());
        Assert.assertEquals(FFmpeg.getStreams(testVideo).stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")),
                mediaInfo.getStreams().stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")));
        Assert.assertEquals(FFmpeg.getChapters(testVideo).stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")),
                mediaInfo.getChapters().stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")));
        
        //invalid
        Assert.assertNull(FFmpeg.getMediaInfo(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getMediaInfo(null));
    }
    
    /**
     * JUnit test of getFormat.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getFormat(File)
     */
    @Test
    public void testGetFormat() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        FFmpeg.MediaInfo.Format format;
        
        //standard
        format = FFmpeg.getFormat(testVideo);
        Assert.assertNotNull(format);
        Assert.assertEquals(testVideo.getAbsolutePath(), format.getMediaFile().getAbsolutePath());
        Assert.assertEquals(3067000L, format.getDurationExact());
        Assert.assertEquals(-5000L, format.getStartTimeExact());
        Assert.assertEquals(3062000L, format.getEndTimeExact());
        Assert.assertEquals(3067L, format.getDuration());
        Assert.assertEquals(-5L, format.getStartTime());
        Assert.assertEquals(3062L, format.getEndTime());
        Assert.assertEquals("00:00:03.067", format.getDurationTimestamp());
        Assert.assertEquals("-00:00:00.005", format.getStartTimestamp());
        Assert.assertEquals("00:00:03.062", format.getEndTimestamp());
        Assert.assertEquals(565905L, format.getSize());
        Assert.assertEquals(1476113L, format.getBitrate());
        Assert.assertEquals("matroska,webm", format.getFormatName());
        Assert.assertEquals("Matroska / WebM", format.getFormatNameLong());
        Assert.assertEquals("FFmpeg Test Video", format.getTitle());
        Assert.assertEquals(15, format.getStreamCount());
        Assert.assertEquals(0, format.getProgramCount());
        Assert.assertEquals(2, format.getMetadata().size());
        Assert.assertEquals("Lavf58.45.100", format.getMetadata().get("encoder"));
        Assert.assertEquals("FFmpeg Test Video", format.getMetadata().get("title"));
        
        //invalid
        Assert.assertNull(FFmpeg.getFormat(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getFormat(null));
    }
    
    /**
     * JUnit test of getStreams.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getStreams(File)
     */
    @Test
    public void testGetStreams() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        List<FFmpeg.MediaInfo.Stream> streams;
        FFmpeg.MediaInfo.Stream stream;
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //standard
        streams = FFmpeg.getStreams(testVideo);
        Assert.assertNotNull(streams);
        Assert.assertEquals(15, streams.size());
        stream = streams.get(0);
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals("h264", stream.getCodecName());
        Assert.assertEquals("H.264 / AVC / MPEG-4 AVC / MPEG-4 part 10", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertEquals("Red", stream.getTitle());
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3023L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:00.000", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:03.023000000", stream.getMetadata().get("duration"));
        Assert.assertTrue(stream.isDefaultStream());
        Assert.assertEquals(10.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(320L, stream.getVideoInfo().getWidth());
        Assert.assertEquals(240L, stream.getVideoInfo().getHeight());
        Assert.assertEquals("yuv444p", stream.getVideoInfo().getPixelFormat());
        Assert.assertEquals("High 4:4:4 Predictive", stream.getVideoInfo().getProfile());
        Assert.assertEquals("Stream #0: Video (eng): h264: \"Red\"", stream.toString());
        stream = streams.get(1);
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals("hevc", stream.getCodecName());
        Assert.assertEquals("H.265 / HEVC (High Efficiency Video Coding)", stream.getCodecNameLong());
        Assert.assertEquals("spa", stream.getLanguage());
        Assert.assertEquals("Green", stream.getTitle());
        Assert.assertEquals("Lavc58.91.100 libx265", stream.getMetadata().get("encoder"));
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3023L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:00.000", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:03.023000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(10.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(320L, stream.getVideoInfo().getWidth());
        Assert.assertEquals(240L, stream.getVideoInfo().getHeight());
        Assert.assertEquals("yuv444p", stream.getVideoInfo().getPixelFormat());
        Assert.assertEquals("Rext", stream.getVideoInfo().getProfile());
        Assert.assertEquals("Stream #1: Video (spa): hevc: \"Green\"", stream.toString());
        stream = streams.get(2);
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals("mpeg4", stream.getCodecName());
        Assert.assertEquals("MPEG-4 part 2", stream.getCodecNameLong());
        Assert.assertEquals("rus", stream.getLanguage());
        Assert.assertEquals("Blue", stream.getTitle());
        Assert.assertEquals("Lavc58.91.100 mpeg4", stream.getMetadata().get("encoder"));
        Assert.assertEquals(23L, stream.getStartTime());
        Assert.assertEquals(3046L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:00.023", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.046", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:03.023000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(10.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(320L, stream.getVideoInfo().getWidth());
        Assert.assertEquals(240L, stream.getVideoInfo().getHeight());
        Assert.assertEquals("yuv420p", stream.getVideoInfo().getPixelFormat());
        Assert.assertEquals("Simple Profile", stream.getVideoInfo().getProfile());
        Assert.assertEquals("Stream #2: Video (rus): mpeg4: \"Blue\"", stream.toString());
        stream = streams.get(3);
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("mp3", stream.getCodecName());
        Assert.assertEquals("MP3 (MPEG audio layer 3)", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertEquals("MP3 Audio", stream.getMetadata().get("description"));
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getMetadata().get("artist"));
        Assert.assertEquals(11L, stream.getStartTime());
        Assert.assertEquals(3078L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("00:00:00.011", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.078", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.067", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:03.067000000", stream.getMetadata().get("duration"));
        Assert.assertTrue(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioInfo().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioInfo().getChannels());
        Assert.assertEquals("fltp", stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioInfo().getBitsPerSample());
        Assert.assertEquals("Stream #3: Audio (eng): mp3: \"Galway\"", stream.toString());
        stream = streams.get(4);
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("aac", stream.getCodecName());
        Assert.assertEquals("AAC (Advanced Audio Coding)", stream.getCodecNameLong());
        Assert.assertEquals("AAC Audio", stream.getMetadata().get("description"));
        Assert.assertEquals("spa", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getMetadata().get("artist"));
        Assert.assertEquals(23L, stream.getStartTime());
        Assert.assertEquals(3088L, stream.getEndTime());
        Assert.assertEquals(3065L, stream.getDuration());
        Assert.assertEquals("00:00:00.023", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.088", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.065", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:03.065000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioInfo().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioInfo().getChannels());
        Assert.assertEquals("fltp", stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioInfo().getBitsPerSample());
        Assert.assertEquals("Stream #4: Audio (spa): aac: \"Galway\"", stream.toString());
        stream = streams.get(5);
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("flac", stream.getCodecName());
        Assert.assertEquals("FLAC (Free Lossless Audio Codec)", stream.getCodecNameLong());
        Assert.assertEquals("FLAC Audio", stream.getMetadata().get("description"));
        Assert.assertEquals("rus", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getMetadata().get("artist"));
        Assert.assertEquals(23L, stream.getStartTime());
        Assert.assertEquals(3046L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:00.023", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.046", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:03.023000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioInfo().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioInfo().getChannels());
        Assert.assertEquals("s16", stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioInfo().getBitsPerSample());
        Assert.assertEquals("Stream #5: Audio (rus): flac: \"Galway\"", stream.toString());
        stream = streams.get(6);
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("aac", stream.getCodecName());
        Assert.assertEquals("AAC (Advanced Audio Coding)", stream.getCodecNameLong());
        Assert.assertEquals("M4A Audio", stream.getMetadata().get("description"));
        Assert.assertEquals("jpn", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getMetadata().get("artist"));
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3041L, stream.getEndTime());
        Assert.assertEquals(3041L, stream.getDuration());
        Assert.assertEquals("00:00:00.000", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.041", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.041", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:03.041000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioInfo().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioInfo().getChannels());
        Assert.assertEquals("fltp", stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioInfo().getBitsPerSample());
        Assert.assertEquals("Stream #6: Audio (jpn): aac: \"Galway\"", stream.toString());
        stream = streams.get(7);
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("opus", stream.getCodecName());
        Assert.assertEquals("Opus (Opus Interactive Audio Codec)", stream.getCodecNameLong());
        Assert.assertEquals("OPUS Audio", stream.getMetadata().get("description"));
        Assert.assertEquals("fre", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getMetadata().get("artist"));
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3018L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("-00:00:00.005", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.018", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:03.023000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(48000, stream.getAudioInfo().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioInfo().getChannels());
        Assert.assertEquals("fltp", stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioInfo().getBitsPerSample());
        Assert.assertEquals("Stream #7: Audio (fre): opus: \"Galway\"", stream.toString());
        stream = streams.get(8);
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals("vorbis", stream.getCodecName());
        Assert.assertEquals("Vorbis", stream.getCodecNameLong());
        Assert.assertEquals("OGG Audio", stream.getMetadata().get("description"));
        Assert.assertEquals("ger", stream.getLanguage());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("Kevin MacLeod", stream.getMetadata().get("artist"));
        Assert.assertEquals(20L, stream.getStartTime());
        Assert.assertEquals(3043L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:00.020", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.043", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:03.023000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("stereo", stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioInfo().getSampleRate());
        Assert.assertEquals(2L, stream.getAudioInfo().getChannels());
        Assert.assertEquals("fltp", stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(0L, stream.getAudioInfo().getBitsPerSample());
        Assert.assertEquals("Stream #8: Audio (ger): vorbis: \"Galway\"", stream.toString());
        stream = streams.get(9);
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertEquals("English", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("-00:00:00.005", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.062", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.067", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:02.911000000", stream.getMetadata().get("duration"));
        Assert.assertTrue(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("Stream #9: Subtitle (eng): subrip: \"English\"", stream.toString());
        stream = streams.get(10);
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("spa", stream.getLanguage());
        Assert.assertEquals("Spanish", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("-00:00:00.005", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.062", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.067", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:02.911000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("Stream #10: Subtitle (spa): subrip: \"Spanish\"", stream.toString());
        stream = streams.get(11);
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("rus", stream.getLanguage());
        Assert.assertEquals("Russian", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("-00:00:00.005", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.062", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.067", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:02.911000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("Stream #11: Subtitle (rus): subrip: \"Russian\"", stream.toString());
        stream = streams.get(12);
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("jpn", stream.getLanguage());
        Assert.assertEquals("Japanese", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("-00:00:00.005", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.062", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.067", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:02.911000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("Stream #12: Subtitle (jpn): subrip: \"Japanese\"", stream.toString());
        stream = streams.get(13);
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("fre", stream.getLanguage());
        Assert.assertEquals("French", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("-00:00:00.005", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.062", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.067", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:02.911000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("Stream #13: Subtitle (fre): subrip: \"French\"", stream.toString());
        stream = streams.get(14);
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals("ger", stream.getLanguage());
        Assert.assertEquals("German", stream.getTitle());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals("-00:00:00.005", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.062", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.067", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:02.911000000", stream.getMetadata().get("duration"));
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals("Stream #14: Subtitle (ger): subrip: \"German\"", stream.toString());
        
        //data stream
        streams = FFmpeg.getStreams(testVideo2);
        Assert.assertNotNull(streams);
        Assert.assertEquals(2, streams.size());
        stream = streams.get(0);
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals("h264", stream.getCodecName());
        Assert.assertEquals("H.264 / AVC / MPEG-4 AVC / MPEG-4 part 10", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertNull(stream.getTitle());
        Assert.assertEquals(5L, stream.getStartTime());
        Assert.assertEquals(3028L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals("00:00:00.005", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.028", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getDurationTimestamp());
        Assert.assertEquals("Stream #0: Video (eng): h264", stream.toString());
        stream = streams.get(1);
        Assert.assertEquals(FFmpeg.StreamType.DATA, stream.getStreamType());
        Assert.assertEquals("bin_data", stream.getCodecName());
        Assert.assertEquals("binary data", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertNull(stream.getTitle());
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3004L, stream.getEndTime());
        Assert.assertEquals(3004L, stream.getDuration());
        Assert.assertEquals("00:00:00.000", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.004", stream.getEndTimestamp());
        Assert.assertEquals("00:00:03.004", stream.getDurationTimestamp());
        Assert.assertEquals("Stream #1: Data (eng): bin_data", stream.toString());
        
        //invalid
        Assert.assertNull(FFmpeg.getStreams(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreams(null));
    }
    
    /**
     * JUnit test of getStream.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getStream(File, FFmpeg.Identifier.Stream)
     */
    @Test
    public void testGetStream() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //stream type, index
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)).getTitle());
        Assert.assertEquals("Green", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)).getTitle());
        Assert.assertEquals("Blue", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 2)).getTitle());
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 0)).getMetadata().get("description"));
        Assert.assertEquals("AAC Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 1)).getMetadata().get("description"));
        Assert.assertEquals("FLAC Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2)).getMetadata().get("description"));
        Assert.assertEquals("M4A Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)).getMetadata().get("description"));
        Assert.assertEquals("OPUS Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 4)).getMetadata().get("description"));
        Assert.assertEquals("OGG Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 5)).getMetadata().get("description"));
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)).getTitle());
        Assert.assertEquals("Spanish", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 1)).getTitle());
        Assert.assertEquals("Russian", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 2)).getTitle());
        Assert.assertEquals("Japanese", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 3)).getTitle());
        Assert.assertEquals("French", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 4)).getTitle());
        Assert.assertEquals("German", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 5)).getTitle());
        Assert.assertEquals("bin_data", FFmpeg.getStream(testVideo2, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.DATA, 0)).getCodecName());
        
        //stream type
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)).getTitle());
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)).getTitle());
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)).getMetadata().get("description"));
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)).getMetadata().get("description"));
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)).getTitle());
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)).getTitle());
        Assert.assertEquals("bin_data", FFmpeg.getStream(testVideo2, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.DATA)).getCodecName());
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofFirst(null)).getTitle());
        
        //null stream type
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 0)).getTitle());
        Assert.assertEquals("Green", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 1)).getTitle());
        Assert.assertEquals("Blue", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 2)).getTitle());
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 3)).getMetadata().get("description"));
        Assert.assertEquals("AAC Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 4)).getMetadata().get("description"));
        Assert.assertEquals("FLAC Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 5)).getMetadata().get("description"));
        Assert.assertEquals("M4A Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 6)).getMetadata().get("description"));
        Assert.assertEquals("OPUS Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 7)).getMetadata().get("description"));
        Assert.assertEquals("OGG Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 8)).getMetadata().get("description"));
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 9)).getTitle());
        Assert.assertEquals("Spanish", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 10)).getTitle());
        Assert.assertEquals("Russian", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 11)).getTitle());
        Assert.assertEquals("Japanese", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 12)).getTitle());
        Assert.assertEquals("French", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 13)).getTitle());
        Assert.assertEquals("German", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 14)).getTitle());
        Assert.assertEquals("bin_data", FFmpeg.getStream(testVideo2, FFmpeg.Identifier.Stream.of(null, 1)).getCodecName());
        
        //index
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(0)).getTitle());
        Assert.assertEquals("Green", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(1)).getTitle());
        Assert.assertEquals("Blue", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(2)).getTitle());
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(3)).getMetadata().get("description"));
        Assert.assertEquals("AAC Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(4)).getMetadata().get("description"));
        Assert.assertEquals("FLAC Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(5)).getMetadata().get("description"));
        Assert.assertEquals("M4A Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(6)).getMetadata().get("description"));
        Assert.assertEquals("OPUS Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(7)).getMetadata().get("description"));
        Assert.assertEquals("OGG Audio", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(8)).getMetadata().get("description"));
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(9)).getTitle());
        Assert.assertEquals("Spanish", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(10)).getTitle());
        Assert.assertEquals("Russian", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(11)).getTitle());
        Assert.assertEquals("Japanese", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(12)).getTitle());
        Assert.assertEquals("French", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(13)).getTitle());
        Assert.assertEquals("German", FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(14)).getTitle());
        Assert.assertEquals("bin_data", FFmpeg.getStream(testVideo2, FFmpeg.Identifier.Stream.ofIndex(1)).getCodecName());
        
        //invalid
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 3)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, -1)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 6)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, -1)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 6)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, -1)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.DATA)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, 15)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(null, -1)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(15)));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.ofIndex(-1)));
        Assert.assertNull(FFmpeg.getStream(fakeVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStream(null, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStream(fakeVideo, null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStream(null, null));
    }
    
    /**
     * JUnit test of getStreamCount.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getStreamCount(File)
     * @see FFmpeg#getStreamCount(File, FFmpeg.StreamType)
     */
    @Test
    public void testGetStreamCount() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //standard
        Assert.assertEquals(15, FFmpeg.getStreamCount(testVideo));
        
        //stream type
        Assert.assertEquals(3, FFmpeg.getStreamCount(testVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(6, FFmpeg.getStreamCount(testVideo, FFmpeg.StreamType.AUDIO));
        Assert.assertEquals(6, FFmpeg.getStreamCount(testVideo, FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals(0, FFmpeg.getStreamCount(testVideo, FFmpeg.StreamType.DATA));
        Assert.assertEquals(1, FFmpeg.getStreamCount(testVideo2, FFmpeg.StreamType.DATA));
        
        //invalid
        Assert.assertEquals(-1, FFmpeg.getStreamCount(fakeVideo));
        Assert.assertEquals(-1, FFmpeg.getStreamCount(fakeVideo, FFmpeg.StreamType.VIDEO));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertEquals(-1, FFmpeg.getStreamCount(fakeVideo, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamCount(null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamCount(null, FFmpeg.StreamType.VIDEO));
    }
    
    /**
     * JUnit test of getChapters.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getChapters(File)
     */
    @Test
    public void testGetChapters() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        List<FFmpeg.MediaInfo.Chapter> chapters;
        FFmpeg.MediaInfo.Chapter chapter;
        
        //standard
        chapters = FFmpeg.getChapters(testVideo);
        Assert.assertNotNull(chapters);
        Assert.assertEquals(3, chapters.size());
        chapter = chapters.get(0);
        Assert.assertEquals(0L, chapter.getStartTime());
        Assert.assertEquals(1000L, chapter.getEndTime());
        Assert.assertEquals(1L, chapter.getChapterId());
        Assert.assertEquals("Chapter 1", chapter.getTitle());
        Assert.assertEquals("Chapter #1: (00:00:00.000 --> 00:00:01.000): \"Chapter 1\"", chapter.toString());
        chapter = chapters.get(1);
        Assert.assertEquals(1100L, chapter.getStartTime());
        Assert.assertEquals(1800L, chapter.getEndTime());
        Assert.assertEquals(2L, chapter.getChapterId());
        Assert.assertEquals("Second Chapter", chapter.getTitle());
        Assert.assertEquals("Chapter #2: (00:00:01.100 --> 00:00:01.800): \"Second Chapter\"", chapter.toString());
        chapter = chapters.get(2);
        Assert.assertEquals(1900L, chapter.getStartTime());
        Assert.assertEquals(2999L, chapter.getEndTime());
        Assert.assertEquals(3L, chapter.getChapterId());
        Assert.assertEquals("The Last Chapter", chapter.getTitle());
        Assert.assertEquals("Chapter #3: (00:00:01.900 --> 00:00:02.999): \"The Last Chapter\"", chapter.toString());
        
        //invalid
        Assert.assertNull(FFmpeg.getChapters(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getChapters(null));
    }
    
    /**
     * JUnit test of getChapter.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getChapter(File, int)
     */
    @Test
    public void testGetChapter() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        
        //index
        Assert.assertEquals("Chapter 1", FFmpeg.getChapter(testVideo, 0).getTitle());
        Assert.assertEquals("Second Chapter", FFmpeg.getChapter(testVideo, 1).getTitle());
        Assert.assertEquals("The Last Chapter", FFmpeg.getChapter(testVideo, 2).getTitle());
        
        //invalid
        Assert.assertNull(FFmpeg.getChapter(testVideo, 3));
        Assert.assertNull(FFmpeg.getChapter(testVideo, -1));
        Assert.assertNull(FFmpeg.getChapter(fakeVideo, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getChapter(null, 0));
    }
    
    /**
     * JUnit test of getChapterCount.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getChapterCount(File)
     */
    @Test
    public void testGetChapterCount() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        
        //standard
        Assert.assertEquals(3, FFmpeg.getChapterCount(testVideo));
        
        //invalid
        Assert.assertEquals(-1, FFmpeg.getChapterCount(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getChapterCount(null));
    }
    
    /**
     * JUnit test of addChapters.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#addChapters(File, List, File)
     * @see FFmpeg#addChapters(File, FFmpeg.MediaInfo.Chapter.ChapterDTO[], File)
     */
    @Test
    public void testAddChapters() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testSourceOriginal = new File(testResources, "test.mkv");
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testDir, "test.mkv");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File testOutput4 = new File(testDir, "test4.mkv");
        final File testOutput5 = new File(testDir, "test5.mkv");
        final File testOutput6 = new File(testDir, "test6.mkv");
        final File testOutput7 = new File(testDir, "test7.mkv");
        final File testOutput8 = new File(testDir, "test8.mkv");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        List<FFmpeg.MediaInfo.Chapter> mockChapters;
        FFmpeg.stripMediaFile(testSourceOriginal, testSource);
        
        //initial
        Assert.assertTrue(testSourceOriginal.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSourceOriginal));
        Assert.assertEquals(Stream.of(
                        "Chapter #1: (00:00:00.000 --> 00:00:01.000): \"Chapter 1\"",
                        "Chapter #2: (00:00:01.100 --> 00:00:01.800): \"Second Chapter\"",
                        "Chapter #3: (00:00:01.900 --> 00:00:02.999): \"The Last Chapter\""
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getChapters(testSourceOriginal).stream().map(FFmpeg.MediaInfo.Chapter::toString).collect(Collectors.joining(System.lineSeparator()))
        );
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testSource));
        
        //chapter DTOs
        FFmpeg.addChapters(testSource, new FFmpeg.MediaInfo.Chapter.ChapterDTO[] {
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(0, 1000, "Intro"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(1001, 2000, "Main"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(2001, 3000, "Outro")
        }, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(Stream.of(
                        "Chapter #1: (00:00:00.000 --> 00:00:01.000): \"Intro\"",
                        "Chapter #2: (00:00:01.001 --> 00:00:02.000): \"Main\"",
                        "Chapter #3: (00:00:02.001 --> 00:00:03.000): \"Outro\""
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getChapters(testOutput1).stream().map(FFmpeg.MediaInfo.Chapter::toString).collect(Collectors.joining(System.lineSeparator()))
        );
        FFmpeg.addChapters(testSource, new FFmpeg.MediaInfo.Chapter.ChapterDTO[] {
                new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:01.052", "00:00:02.880", null)
        }, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(Stream.of(
                        "Chapter #1: (00:00:01.052 --> 00:00:02.880)"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getChapters(testOutput2).stream().map(FFmpeg.MediaInfo.Chapter::toString).collect(Collectors.joining(System.lineSeparator()))
        );
        FFmpeg.addChapters(testSource, new FFmpeg.MediaInfo.Chapter.ChapterDTO[] {
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(50, 510, "Chapter# 1:"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:00.515", "00:00:01.031", "Chapter# 2:"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:01.036", "00:00:01.488", "Chapter# 3:"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(1493, 2117, "Chapter# 4:"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:02.122", "00:00:02.950", "Chapter# 5:")
        }, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(Stream.of(
                        "Chapter #1: (00:00:00.050 --> 00:00:00.510): \"Chapter# 1:\"",
                        "Chapter #2: (00:00:00.515 --> 00:00:01.031): \"Chapter# 2:\"",
                        "Chapter #3: (00:00:01.036 --> 00:00:01.488): \"Chapter# 3:\"",
                        "Chapter #4: (00:00:01.493 --> 00:00:02.117): \"Chapter# 4:\"",
                        "Chapter #5: (00:00:02.122 --> 00:00:02.950): \"Chapter# 5:\""
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getChapters(testOutput3).stream().map(FFmpeg.MediaInfo.Chapter::toString).collect(Collectors.joining(System.lineSeparator()))
        );
        
        //chapters
        mockChapters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FFmpeg.MediaInfo.Chapter mockChapter = Mockito.mock(FFmpeg.MediaInfo.Chapter.class);
            TestUtils.setField(mockChapter, "chapterId", (i + 1));
            TestUtils.setField(mockChapter, "startTime", (i * 1000000));
            TestUtils.setField(mockChapter, "endTime", ((i + 1) * 1000000));
            TestUtils.setField(mockChapter, "title", ("Chapter " + (i + 1)));
            mockChapters.add(mockChapter);
        }
        FFmpeg.addChapters(testSource, mockChapters, testOutput4);
        Assert.assertTrue(testOutput4.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput4));
        Assert.assertEquals(Stream.of(
                        "Chapter #1: (00:00:00.000 --> 00:00:01.000): \"Chapter 1\"",
                        "Chapter #2: (00:00:01.000 --> 00:00:02.000): \"Chapter 2\"",
                        "Chapter #3: (00:00:02.000 --> 00:00:03.000): \"Chapter 3\""
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getChapters(testOutput4).stream().map(FFmpeg.MediaInfo.Chapter::toString).collect(Collectors.joining(System.lineSeparator()))
        );
        
        //copy
        FFmpeg.addChapters(testSource, FFmpeg.getChapters(testSourceOriginal), testOutput5);
        Assert.assertTrue(testOutput5.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput5));
        Assert.assertEquals(Stream.of(
                        "Chapter #1: (00:00:00.000 --> 00:00:01.000): \"Chapter 1\"",
                        "Chapter #2: (00:00:01.100 --> 00:00:01.800): \"Second Chapter\"",
                        "Chapter #3: (00:00:01.900 --> 00:00:02.999): \"The Last Chapter\""
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getChapters(testOutput5).stream().map(FFmpeg.MediaInfo.Chapter::toString).collect(Collectors.joining(System.lineSeparator()))
        );
        
        //overwrite
        FFmpeg.addChapters(testSourceOriginal, new FFmpeg.MediaInfo.Chapter.ChapterDTO[] {
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(0, 1000, "Intro"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(1001, 2000, "Main"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(2001, 3000, "Outro")
        }, testOutput6);
        Assert.assertTrue(testOutput6.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput6));
        Assert.assertEquals(Stream.of(
                        "Chapter #1: (00:00:00.000 --> 00:00:01.000): \"Intro\"",
                        "Chapter #2: (00:00:01.001 --> 00:00:02.000): \"Main\"",
                        "Chapter #3: (00:00:02.001 --> 00:00:03.000): \"Outro\""
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getChapters(testOutput6).stream().map(FFmpeg.MediaInfo.Chapter::toString).collect(Collectors.joining(System.lineSeparator()))
        );
        
        //empty
        FFmpeg.addChapters(testSourceOriginal, new FFmpeg.MediaInfo.Chapter.ChapterDTO[] {}, testOutput7);
        Assert.assertTrue(testOutput7.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput7));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput7));
        FFmpeg.addChapters(testSourceOriginal, new ArrayList<>(), testOutput8);
        Assert.assertTrue(testOutput8.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput8));
        Assert.assertEquals(0, FFmpeg.getChapterCount(testOutput8));
        
        //invalid
        Assert.assertEquals("[*]" + fakeSource.getAbsolutePath() + ": No such file or directory",
                FFmpeg.addChapters(fakeSource, new ArrayList<>(), fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.addChapters(fakeSource, (FFmpeg.MediaInfo.Chapter.ChapterDTO[]) null, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.addChapters(fakeSource, (List<FFmpeg.MediaInfo.Chapter>) null, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.addChapters(fakeSource, new ArrayList<>(), null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.addChapters(null, new ArrayList<>(), fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.addChapters(null, new ArrayList<>(), null));
    }
    
    /**
     * JUnit test of stripChapters.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#stripChapters(File, File)
     */
    @Test
    public void testStripChapters() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn("").when(FFmpeg.class, "stripMediaFile", ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        FFmpeg.stripChapters(testSource, testOutput);
        PowerMockito.verifyStatic(FFmpeg.class);
        FFmpeg.stripMediaFile(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(false), ArgumentMatchers.eq(true), ArgumentMatchers.eq(testOutput));
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "stripMediaFile", ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any(File.class));
    }
    
    /**
     * JUnit test of getMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getMetadata(File, FFmpeg.Identifier)
     * @see FFmpeg#getMetadata(File)
     */
    @Test
    public void testGetMetadata() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        FFmpeg.MediaInfo.MetadataTags tags;
        
        //global
        tags = FFmpeg.getMetadata(testVideo);
        Assert.assertNotNull(tags);
        Assert.assertEquals(2, tags.size());
        Assert.assertEquals("{ENCODER:'Lavf58.45.100', title:'FFmpeg Test Video'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Global.get());
        Assert.assertNotNull(tags);
        Assert.assertEquals(2, tags.size());
        Assert.assertEquals("{ENCODER:'Lavf58.45.100', title:'FFmpeg Test Video'}", tags.toString());
        
        //streams
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofIndex(0));
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{language:'eng', title:'Red', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1));
        Assert.assertNotNull(tags);
        Assert.assertEquals(4, tags.size());
        Assert.assertEquals("{ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofIndex(2));
        Assert.assertNotNull(tags);
        Assert.assertEquals(4, tags.size());
        Assert.assertEquals("{ENCODER:'Lavc58.91.100 mpeg4', language:'rus', title:'Blue', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO));
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'MP3 Audio', language:'eng', title:'Galway', DURATION:'00:00:03.067000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofIndex(4));
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'AAC Audio', language:'spa', title:'Galway', DURATION:'00:00:03.065000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofIndex(5));
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'FLAC Audio', language:'rus', title:'Galway', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3));
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'M4A Audio', language:'jpn', title:'Galway', DURATION:'00:00:03.041000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofIndex(7));
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'OPUS Audio', language:'fre', title:'Galway', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 5));
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'OGG Audio', language:'ger', title:'Galway', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofIndex(9));
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{language:'eng', title:'English', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 1));
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{language:'spa', title:'Spanish', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 2));
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{language:'rus', title:'Russian', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 3));
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{language:'jpn', title:'Japanese', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofIndex(13));
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{language:'fre', title:'French', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofIndex(14));
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{language:'ger', title:'German', DURATION:'00:00:02.911000000'}", tags.toString());
        
        //chapters
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Chapter.ofIndex(0));
        Assert.assertNotNull(tags);
        Assert.assertEquals(1, tags.size());
        Assert.assertEquals("{title:'Chapter 1'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Chapter.ofIndex(1));
        Assert.assertNotNull(tags);
        Assert.assertEquals(1, tags.size());
        Assert.assertEquals("{title:'Second Chapter'}", tags.toString());
        tags = FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Chapter.ofIndex(2));
        Assert.assertNotNull(tags);
        Assert.assertEquals(1, tags.size());
        Assert.assertEquals("{title:'The Last Chapter'}", tags.toString());
        
        //invalid
        Assert.assertNull(FFmpeg.getMetadata(testVideo, FFmpeg.Identifier.Stream.ofIndex(16)));
        Assert.assertNull(FFmpeg.getMetadata(fakeVideo));
        Assert.assertNull(FFmpeg.getMetadata(fakeVideo, FFmpeg.Identifier.Stream.ofIndex(0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getMetadata(testVideo, null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getMetadata(null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getMetadata(null, FFmpeg.Identifier.Stream.ofIndex(0)));
    }
    
    /**
     * JUnit test of modifyMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#modifyMetadata(File, Map, File, boolean, boolean)
     * @see FFmpeg#modifyMetadataHelper(File, Map, File, boolean, boolean)
     * @see FFmpeg#modifyMetadataHelper(File, FFmpeg.Identifier, FFmpeg.MediaInfo.MetadataTags, File, boolean, boolean)
     */
    @Test
    public void testModifyMetadata() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class, Mockito.CALLS_REAL_METHODS);
        final File testSourceOriginal = new File(testResources, "test.mkv");
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testDir, "source.mkv");
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
        final AtomicReference<Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags>> testModifiers = new AtomicReference<>(null);
        final FFmpeg.MediaInfo mediaInfo = FFmpeg.getMediaInfo(testSourceOriginal);
        FFmpeg.MediaInfo.MetadataTags metadata;
        FFmpeg.stripMetadata(testSourceOriginal, testSource);
        
        //initial
        Assert.assertTrue(testSourceOriginal.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSourceOriginal));
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'FFmpeg Test Video'}",
                        "Stream #0: {language:'eng', title:'Red', D:''}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', D:''}",
                        "Stream #2: {ENCODER:'Lavc58.91.100 mpeg4', language:'rus', title:'Blue', D:''}",
                        "Stream #3: {ARTIST:'Kevin MacLeod', DESCRIPTION:'MP3 Audio', language:'eng', title:'Galway', D:''}",
                        "Stream #4: {ARTIST:'Kevin MacLeod', DESCRIPTION:'AAC Audio', language:'spa', title:'Galway', D:''}",
                        "Stream #5: {ARTIST:'Kevin MacLeod', DESCRIPTION:'FLAC Audio', language:'rus', title:'Galway', D:''}",
                        "Stream #6: {ARTIST:'Kevin MacLeod', DESCRIPTION:'M4A Audio', language:'jpn', title:'Galway', D:''}",
                        "Stream #7: {ARTIST:'Kevin MacLeod', DESCRIPTION:'OPUS Audio', language:'fre', title:'Galway', D:''}",
                        "Stream #8: {ARTIST:'Kevin MacLeod', DESCRIPTION:'OGG Audio', language:'ger', title:'Galway', D:''}",
                        "Stream #9: {language:'eng', title:'English', D:''}",
                        "Stream #10: {language:'spa', title:'Spanish', D:''}",
                        "Stream #11: {language:'rus', title:'Russian', D:''}",
                        "Stream #12: {language:'jpn', title:'Japanese', D:''}",
                        "Stream #13: {language:'fre', title:'French', D:''}",
                        "Stream #14: {language:'ger', title:'German', D:''}",
                        "Chapter #1: {title:'Chapter 1'}", "Chapter #2: {title:'Second Chapter'}", "Chapter #3: {title:'The Last Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testSourceOriginal).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100'}",
                        "Stream #0: {D:''}", "Stream #1: {D:''}", "Stream #2: {D:''}",
                        "Stream #3: {D:''}", "Stream #4: {D:''}", "Stream #5: {D:''}", "Stream #6: {D:''}", "Stream #7: {D:''}", "Stream #8: {D:''}",
                        "Stream #9: {D:''}", "Stream #10: {D:''}", "Stream #11: {D:''}", "Stream #12: {D:''}", "Stream #13: {D:''}", "Stream #14: {D:''}",
                        "Chapter #1: {}", "Chapter #2: {}", "Chapter #3: {}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testSource).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        
        //set
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                new LinkedHashMap<>() {{
                    put(FFmpeg.Identifier.Stream.ofIndex(2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Video 3");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Galway");
                        put("artist", "Kevin MacLeod");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Galway+");
                        put("artist", "Kevin MacLeod+");
                        put("Special", "true");
                    }}));
                    put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Chapter");
                    }}));
                    put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Global");
                        put("Title", "Global");
                    }}));
                }},
                testOutput1, true, false);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'Global', NAME:'Global'}",
                        "Stream #0: {D:''}", "Stream #1: {D:''}",
                        "Stream #2: {D:'', NAME:'Video 3'}",
                        "Stream #3: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #4: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #5: {ARTIST:'Kevin MacLeod+', title:'Galway+', SPECIAL:'true', D:''}",
                        "Stream #6: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #7: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #8: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #9: {D:''}", "Stream #10: {D:''}", "Stream #11: {D:''}", "Stream #12: {D:''}", "Stream #13: {D:''}", "Stream #14: {D:''}",
                        "Chapter #1: {title:'Chapter'}", "Chapter #2: {title:'Chapter'}", "Chapter #3: {title:'Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput1).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testOutput1,
                FFmpeg.Identifier.Stream.ofIndex(11), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                    put("new", "yes");
                }}),
                testOutput2, true, false);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'Global', NAME:'Global'}",
                        "Stream #0: {D:''}", "Stream #1: {D:''}",
                        "Stream #2: {D:'', NAME:'Video 3'}",
                        "Stream #3: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #4: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #5: {ARTIST:'Kevin MacLeod+', title:'Galway+', SPECIAL:'true', D:''}",
                        "Stream #6: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #7: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #8: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #9: {D:''}", "Stream #10: {D:''}",
                        "Stream #11: {NEW:'yes', D:''}",
                        "Stream #12: {D:''}", "Stream #13: {D:''}", "Stream #14: {D:''}",
                        "Chapter #1: {title:'Chapter'}", "Chapter #2: {title:'Chapter'}", "Chapter #3: {title:'Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput2).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSourceOriginal,
                new LinkedHashMap<>() {{
                    put(FFmpeg.Identifier.Stream.ofIndex(2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Video 3");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Galway+");
                        put("artist", "Kevin MacLeod+");
                        put("Special", "true");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Not Galway");
                        put("artist", "Not Kevin MacLeod");
                    }}));
                    put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Chapter");
                    }}));
                    put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Global");
                        put("Title", "Global");
                    }}));
                }},
                testOutput3, true, false);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'Global', NAME:'Global'}",
                        "Stream #0: {language:'eng', title:'Red', D:''}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', D:''}",
                        "Stream #2: {D:'', NAME:'Video 3'}",
                        "Stream #3: {ARTIST:'Not Kevin MacLeod', title:'Not Galway', D:''}",
                        "Stream #4: {ARTIST:'Not Kevin MacLeod', title:'Not Galway', D:''}",
                        "Stream #5: {ARTIST:'Not Kevin MacLeod', title:'Not Galway', D:''}",
                        "Stream #6: {ARTIST:'Not Kevin MacLeod', title:'Not Galway', D:''}",
                        "Stream #7: {ARTIST:'Not Kevin MacLeod', title:'Not Galway', D:''}",
                        "Stream #8: {ARTIST:'Not Kevin MacLeod', title:'Not Galway', D:''}",
                        "Stream #9: {language:'eng', title:'English', D:''}",
                        "Stream #10: {language:'spa', title:'Spanish', D:''}",
                        "Stream #11: {language:'rus', title:'Russian', D:''}",
                        "Stream #12: {language:'jpn', title:'Japanese', D:''}",
                        "Stream #13: {language:'fre', title:'French', D:''}",
                        "Stream #14: {language:'ger', title:'German', D:''}",
                        "Chapter #1: {title:'Chapter'}", "Chapter #2: {title:'Chapter'}", "Chapter #3: {title:'Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput3).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        
        //add
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                new LinkedHashMap<>() {{
                    put(FFmpeg.Identifier.Stream.ofIndex(2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Video 3");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Galway");
                        put("artist", "Kevin MacLeod");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Galway+");
                        put("artist", "Kevin MacLeod+");
                        put("Special", "true");
                    }}));
                    put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Chapter");
                    }}));
                    put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Global");
                        put("Title", "Global");
                    }}));
                }},
                testOutput4, false, false);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'Global', NAME:'Global'}",
                        "Stream #0: {D:''}", "Stream #1: {D:''}",
                        "Stream #2: {D:'', NAME:'Video 3'}",
                        "Stream #3: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #4: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #5: {ARTIST:'Kevin MacLeod+', title:'Galway+', SPECIAL:'true', D:''}",
                        "Stream #6: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #7: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #8: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #9: {D:''}", "Stream #10: {D:''}", "Stream #11: {D:''}", "Stream #12: {D:''}", "Stream #13: {D:''}", "Stream #14: {D:''}",
                        "Chapter #1: {title:'Chapter'}", "Chapter #2: {title:'Chapter'}", "Chapter #3: {title:'Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput4).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testOutput4,
                FFmpeg.Identifier.Stream.ofIndex(3), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                    put("new", "yes");
                }}),
                testOutput5, false, false);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'Global', NAME:'Global'}",
                        "Stream #0: {D:''}", "Stream #1: {D:''}",
                        "Stream #2: {D:'', NAME:'Video 3'}",
                        "Stream #3: {NEW:'yes', ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #4: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #5: {ARTIST:'Kevin MacLeod+', title:'Galway+', SPECIAL:'true', D:''}",
                        "Stream #6: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #7: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #8: {ARTIST:'Kevin MacLeod', title:'Galway', D:''}",
                        "Stream #9: {D:''}", "Stream #10: {D:''}", "Stream #11: {D:''}",
                        "Stream #12: {D:''}", "Stream #13: {D:''}", "Stream #14: {D:''}",
                        "Chapter #1: {title:'Chapter'}", "Chapter #2: {title:'Chapter'}", "Chapter #3: {title:'Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput5).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSourceOriginal,
                new LinkedHashMap<>() {{
                    put(FFmpeg.Identifier.Stream.ofIndex(2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Video 3");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Galway+");
                        put("artist", "Kevin MacLeod+");
                        put("Special", "true");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Not Galway");
                        put("artist", "Not Kevin MacLeod");
                        put("isSpecial", "false");
                    }}));
                    put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("title", "Chapter");
                    }}));
                    put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Global");
                        put("Title", "Global");
                    }}));
                }},
                testOutput6, false, false);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'Global', NAME:'Global'}",
                        "Stream #0: {language:'eng', title:'Red', D:''}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', D:''}",
                        "Stream #2: {ENCODER:'Lavc58.91.100 mpeg4', language:'rus', title:'Blue', D:'', NAME:'Video 3'}",
                        "Stream #3: {ARTIST:'Not Kevin MacLeod', DESCRIPTION:'MP3 Audio', ISSPECIAL:'false', language:'eng', title:'Not Galway', D:''}",
                        "Stream #4: {ARTIST:'Not Kevin MacLeod', DESCRIPTION:'AAC Audio', ISSPECIAL:'false', language:'spa', title:'Not Galway', D:''}",
                        "Stream #5: {ARTIST:'Not Kevin MacLeod', DESCRIPTION:'FLAC Audio', ISSPECIAL:'false', language:'rus', title:'Not Galway', D:''}",
                        "Stream #6: {ARTIST:'Not Kevin MacLeod', DESCRIPTION:'M4A Audio', ISSPECIAL:'false', language:'jpn', title:'Not Galway', D:''}",
                        "Stream #7: {ARTIST:'Not Kevin MacLeod', DESCRIPTION:'OPUS Audio', ISSPECIAL:'false', language:'fre', title:'Not Galway', D:''}",
                        "Stream #8: {ARTIST:'Not Kevin MacLeod', DESCRIPTION:'OGG Audio', ISSPECIAL:'false', language:'ger', title:'Not Galway', D:''}",
                        "Stream #9: {language:'eng', title:'English', D:''}",
                        "Stream #10: {language:'spa', title:'Spanish', D:''}",
                        "Stream #11: {language:'rus', title:'Russian', D:''}",
                        "Stream #12: {language:'jpn', title:'Japanese', D:''}",
                        "Stream #13: {language:'fre', title:'French', D:''}",
                        "Stream #14: {language:'ger', title:'German', D:''}",
                        "Chapter #1: {title:'Chapter'}", "Chapter #2: {title:'Chapter'}", "Chapter #3: {title:'Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput6).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        
        //remove
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testOutput5,
                new LinkedHashMap<>() {{
                    put(FFmpeg.Identifier.Stream.ofIndex(2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Video 3");
                        put("description", "not valid");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(List.of("artist")));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2), new FFmpeg.MediaInfo.MetadataTags(List.of("artist", "Special")));
                    put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(List.of("description")));
                    put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("name", "Title")));
                }},
                testOutput7, false, true);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100'}",
                        "Stream #0: {D:''}", "Stream #1: {D:''}", "Stream #2: {D:''}",
                        "Stream #3: {NEW:'yes', title:'Galway', D:''}",
                        "Stream #4: {title:'Galway', D:''}",
                        "Stream #5: {title:'Galway+', D:''}",
                        "Stream #6: {title:'Galway', D:''}",
                        "Stream #7: {title:'Galway', D:''}",
                        "Stream #8: {title:'Galway', D:''}",
                        "Stream #9: {D:''}", "Stream #10: {D:''}", "Stream #11: {D:''}", "Stream #12: {D:''}", "Stream #13: {D:''}", "Stream #14: {D:''}",
                        "Chapter #1: {title:'Chapter'}", "Chapter #2: {title:'Chapter'}", "Chapter #3: {title:'Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput7).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testOutput7,
                FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(List.of("new")),
                testOutput8, false, true);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100'}",
                        "Stream #0: {D:''}", "Stream #1: {D:''}", "Stream #2: {D:''}",
                        "Stream #3: {title:'Galway', D:''}",
                        "Stream #4: {title:'Galway', D:''}",
                        "Stream #5: {title:'Galway+', D:''}",
                        "Stream #6: {title:'Galway', D:''}",
                        "Stream #7: {title:'Galway', D:''}",
                        "Stream #8: {title:'Galway', D:''}",
                        "Stream #9: {D:''}", "Stream #10: {D:''}", "Stream #11: {D:''}", "Stream #12: {D:''}", "Stream #13: {D:''}", "Stream #14: {D:''}",
                        "Chapter #1: {title:'Chapter'}", "Chapter #2: {title:'Chapter'}", "Chapter #3: {title:'Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput8).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSourceOriginal,
                new LinkedHashMap<>() {{
                    put(FFmpeg.Identifier.Stream.ofIndex(2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Video 3");
                        put("description", "not valid");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2), new FFmpeg.MediaInfo.MetadataTags(List.of("title", "description")));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(List.of("artist")));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE), new FFmpeg.MediaInfo.MetadataTags(List.of("title")));
                    put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("name", "Title")));
                }},
                testOutput9, false, true);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100'}",
                        "Stream #0: {language:'eng', title:'Red', D:''}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', D:''}",
                        "Stream #2: {ENCODER:'Lavc58.91.100 mpeg4', language:'rus', title:'Blue', D:''}",
                        "Stream #3: {DESCRIPTION:'MP3 Audio', language:'eng', title:'Galway', D:''}",
                        "Stream #4: {DESCRIPTION:'AAC Audio', language:'spa', title:'Galway', D:''}",
                        "Stream #5: {DESCRIPTION:'FLAC Audio', language:'rus', title:'Galway', D:''}",
                        "Stream #6: {DESCRIPTION:'M4A Audio', language:'jpn', title:'Galway', D:''}",
                        "Stream #7: {DESCRIPTION:'OPUS Audio', language:'fre', title:'Galway', D:''}",
                        "Stream #8: {DESCRIPTION:'OGG Audio', language:'ger', title:'Galway', D:''}",
                        "Stream #9: {language:'eng', D:''}",
                        "Stream #10: {language:'spa', D:''}",
                        "Stream #11: {language:'rus', D:''}",
                        "Stream #12: {language:'jpn', D:''}",
                        "Stream #13: {language:'fre', D:''}",
                        "Stream #14: {language:'ger', D:''}",
                        "Chapter #1: {title:'Chapter 1'}", "Chapter #2: {title:'Second Chapter'}", "Chapter #3: {title:'The Last Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput9).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        
        //clear
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testOutput9,
                new LinkedHashMap<>() {{
                    put(FFmpeg.Identifier.Stream.ofIndex(2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Video 3");
                        put("description", "not valid");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(List.of("artist")));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2), new FFmpeg.MediaInfo.MetadataTags(List.of("artist", "Special")));
                    put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(List.of("description")));
                    put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("name", "Title")));
                }},
                testOutput10, true, true);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100'}",
                        "Stream #0: {language:'eng', title:'Red', D:''}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', D:''}",
                        "Stream #2: {D:''}", "Stream #3: {D:''}", "Stream #4: {D:''}", "Stream #5: {D:''}", "Stream #6: {D:''}", "Stream #7: {D:''}", "Stream #8: {D:''}",
                        "Stream #9: {language:'eng', D:''}",
                        "Stream #10: {language:'spa', D:''}",
                        "Stream #11: {language:'rus', D:''}",
                        "Stream #12: {language:'jpn', D:''}",
                        "Stream #13: {language:'fre', D:''}",
                        "Stream #14: {language:'ger', D:''}",
                        "Chapter #1: {}", "Chapter #2: {}", "Chapter #3: {}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput10).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testOutput10,
                FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE), new FFmpeg.MediaInfo.MetadataTags(List.of("new")),
                testOutput11, true, true);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100'}",
                        "Stream #0: {language:'eng', title:'Red', D:''}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', D:''}",
                        "Stream #2: {D:''}",
                        "Stream #3: {D:''}", "Stream #4: {D:''}", "Stream #5: {D:''}", "Stream #6: {D:''}", "Stream #7: {D:''}", "Stream #8: {D:''}",
                        "Stream #9: {D:''}",
                        "Stream #10: {language:'spa', D:''}",
                        "Stream #11: {language:'rus', D:''}",
                        "Stream #12: {language:'jpn', D:''}",
                        "Stream #13: {language:'fre', D:''}",
                        "Stream #14: {language:'ger', D:''}",
                        "Chapter #1: {}", "Chapter #2: {}", "Chapter #3: {}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput11).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSourceOriginal,
                new LinkedHashMap<>() {{
                    put(FFmpeg.Identifier.Stream.ofIndex(2), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                        put("name", "Video 3");
                        put("description", "not valid");
                    }}));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2), new FFmpeg.MediaInfo.MetadataTags(List.of("title", "description")));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(List.of("artist")));
                    put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE), new FFmpeg.MediaInfo.MetadataTags(List.of("title")));
                    put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("name", "Title")));
                }},
                testOutput12, true, true);
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100'}",
                        "Stream #0: {language:'eng', title:'Red', D:''}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', D:''}",
                        "Stream #2: {D:''}",
                        "Stream #3: {D:''}", "Stream #4: {D:''}", "Stream #5: {D:''}", "Stream #6: {D:''}", "Stream #7: {D:''}", "Stream #8: {D:''}",
                        "Stream #9: {D:''}", "Stream #10: {D:''}", "Stream #11: {D:''}", "Stream #12: {D:''}", "Stream #13: {D:''}", "Stream #14: {D:''}",
                        "Chapter #1: {title:'Chapter 1'}", "Chapter #2: {title:'Second Chapter'}", "Chapter #3: {title:'The Last Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput12).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        
        //helpers
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doAnswer((Answer<String>) invocation -> {
            final Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags> modifiersMap = invocation.getArgument(1);
            Assert.assertNotNull(modifiersMap);
            Assert.assertNotNull(testModifiers.get());
            final Map<FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>, FFmpeg.MediaInfo.MetadataTags> testModifiersMap =
                    ((testModifiers.get().size() == 1) && (testModifiers.get().keySet().toArray(FFmpeg.Identifier[]::new)[0].isSingularScoped())) ?
                    new LinkedHashMap<>() {{
                        testModifiers.get().forEach((key, value) -> put((FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>) key, value));
                    }} :
                    FFmpeg.Identifier.decompose(new ArrayList<>(testModifiers.get().keySet()), new ArrayList<>(testModifiers.get().values()), mediaInfo);
            final Map<String, String> testModifiersStringMap = testModifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertEquals(testModifiersMap.size(), modifiersMap.size());
            final Map<String, String> modifiersStringMap = modifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertFalse(testModifiersStringMap.entrySet().stream().anyMatch(e -> (!modifiersStringMap.containsKey(e.getKey()) || !Objects.equals(e.getValue(), modifiersStringMap.get(e.getKey())))));
            return null;
        }).when(FFmpeg.class, "modifyMetadata", ArgumentMatchers.any(File.class), ArgumentMatchers.anyMap(), ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean());
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.ofIndex(3), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("name", "A Name");
                put("duration", "00:00:03.000");
            }}));
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
                put("duration", "00:00:03.000");
            }}));
            put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("language", "eng");
            }}));
        }});
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSourceOriginal,
                testModifiers.get(),
                fakeOutput, false, false);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(1))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSourceOriginal), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(fakeOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(false));
        PowerMockito.verifyStatic(FFmpeg.class, VerificationModeFactory.times(1));
        FFmpeg.getMediaInfo(ArgumentMatchers.eq(testSourceOriginal));
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.ofIndex(3), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("name", "A Name");
                put("duration", "00:00:03.000");
            }}));
        }});
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSourceOriginal,
                testModifiers.get(),
                fakeOutput, false, true);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(1))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSourceOriginal), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(fakeOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(true));
        PowerMockito.verifyStatic(FFmpeg.class, VerificationModeFactory.times(1));
        FFmpeg.getMediaInfo(ArgumentMatchers.eq(testSourceOriginal));
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
                put("duration", "00:00:03.000");
            }}));
        }});
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSourceOriginal,
                testModifiers.get().keySet().iterator().next(), testModifiers.get().values().iterator().next(),
                fakeOutput, true, false);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(1))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSourceOriginal), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(fakeOutput), ArgumentMatchers.eq(true), ArgumentMatchers.eq(false));
        PowerMockito.verifyStatic(FFmpeg.class, VerificationModeFactory.times(2));
        FFmpeg.getMediaInfo(ArgumentMatchers.eq(testSourceOriginal));
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.ofIndex(3), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("name", "A Name");
                put("duration", "00:00:03.000");
            }}));
        }});
        TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSourceOriginal,
                testModifiers.get().keySet().iterator().next(), testModifiers.get().values().iterator().next(),
                fakeOutput, true, true);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(1))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSourceOriginal), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(fakeOutput), ArgumentMatchers.eq(true), ArgumentMatchers.eq(true));
        PowerMockito.verifyStatic(FFmpeg.class, VerificationModeFactory.times(2));
        FFmpeg.getMediaInfo(ArgumentMatchers.eq(testSourceOriginal));
        PowerMockito.doCallRealMethod().when(FFmpeg.class,
                "modifyMetadata", ArgumentMatchers.any(File.class), ArgumentMatchers.anyMap(), ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean());
        
        //invalid
        PowerMockito.doNothing().when(TestUtils.AssertWrapper.class, "fail", ArgumentMatchers.anyString());
        Assert.assertTrue(((String) TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                new LinkedHashMap<>() {{
                    put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title")));
                }},
                fakeOutput, true, true)).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        Assert.assertTrue(((String) TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title")),
                fakeOutput, true, true)).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        TestUtils.assertException(MethodInvocationException.class, "java.lang.NullPointerException", () ->
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                        null,
                        testOutput1, true, true));
        TestUtils.assertException(MethodInvocationException.class, "java.lang.NullPointerException", () ->
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                        FFmpeg.Identifier.Global.get(), null,
                        testOutput1, true, true));
        TestUtils.assertException(MethodInvocationException.class, "java.lang.NullPointerException", () ->
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                        null, new FFmpeg.MediaInfo.MetadataTags(List.of("title")),
                        testOutput1, true, true));
        TestUtils.assertException(MethodInvocationException.class, "java.lang.NullPointerException", () ->
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                        null, null,
                        testOutput1, true, true));
        TestUtils.assertException(MethodInvocationException.class, () -> //NullPointerException
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", null,
                        new LinkedHashMap<>() {{
                            put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title")));
                        }},
                        fakeOutput, true, true));
        TestUtils.assertException(MethodInvocationException.class, () -> //NullPointerException
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", null,
                        FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title")),
                        fakeOutput, true, true));
        TestUtils.assertException(MethodInvocationException.class, () -> //NullPointerException
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                        new LinkedHashMap<>() {{
                            put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title")));
                        }},
                        null, true, true));
        TestUtils.assertException(MethodInvocationException.class, () -> //NullPointerException
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", testSource,
                        FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title")),
                        null, true, true));
        TestUtils.assertException(MethodInvocationException.class, () -> //NullPointerException
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", null,
                        new LinkedHashMap<>() {{
                            put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title")));
                        }},
                        null, true, true));
        TestUtils.assertException(MethodInvocationException.class, () -> //NullPointerException
                TestUtils.invokeMethod(FFmpeg.class, "modifyMetadataHelper", null,
                        FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title")),
                        null, true, true));
        PowerMockito.doCallRealMethod().when(TestUtils.AssertWrapper.class, "fail", ArgumentMatchers.anyString());
    }
    
    /**
     * JUnit test of setMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#setMetadata(File, Map, File)
     * @see FFmpeg#setMetadata(File, FFmpeg.Identifier, FFmpeg.MediaInfo.MetadataTags, File)
     * @see FFmpeg#setMetadata(File, FFmpeg.MediaInfo.MetadataTags, File)
     */
    @Test
    public void testSetMetadata() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        final AtomicReference<Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags>> testModifiers = new AtomicReference<>(null);
        final File testSource = new File(testResources, "test.mkv");
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testOutput = new File(testDir, "test.mkv");
        final FFmpeg.MediaInfo mediaInfo = FFmpeg.getMediaInfo(testSource);
        
        PowerMockito.doAnswer((Answer<String>) invocation -> {
            final Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags> modifiersMap = invocation.getArgument(1);
            Assert.assertNotNull(modifiersMap);
            Assert.assertNotNull(testModifiers.get());
            final Map<FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>, FFmpeg.MediaInfo.MetadataTags> testModifiersMap =
                    ((testModifiers.get().size() == 1) && (testModifiers.get().keySet().toArray(FFmpeg.Identifier[]::new)[0].isSingularScoped())) ?
                    new LinkedHashMap<>() {{
                        testModifiers.get().forEach((key, value) -> put((FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>) key, value));
                    }} :
                    FFmpeg.Identifier.decompose(new ArrayList<>(testModifiers.get().keySet()), new ArrayList<>(testModifiers.get().values()), mediaInfo);
            final Map<String, String> testModifiersStringMap = testModifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertEquals(testModifiersMap.size(), modifiersMap.size());
            final Map<String, String> modifiersStringMap = modifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertFalse(testModifiersStringMap.entrySet().stream().anyMatch(e -> (!modifiersStringMap.containsKey(e.getKey()) || !Objects.equals(e.getValue(), modifiersStringMap.get(e.getKey())))));
            return null;
        }).when(FFmpeg.class, "modifyMetadata", ArgumentMatchers.any(File.class), ArgumentMatchers.anyMap(), ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean());
        
        //map
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.ofIndex(3), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("name", "A Name");
                put("duration", "00:00:03.000");
            }}));
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
                put("duration", "00:00:03.000");
            }}));
            put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("language", "eng");
            }}));
        }});
        FFmpeg.setMetadata(testSource, new LinkedHashMap<>(testModifiers.get()), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(1))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(true), ArgumentMatchers.eq(false));
        
        //identifier, tags
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
                put("duration", "00:00:03.000");
            }}));
        }});
        FFmpeg.setMetadata(testSource, new ArrayList<>(testModifiers.get().keySet()).get(0), new ArrayList<>(testModifiers.get().values()).get(0), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(2))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(true), ArgumentMatchers.eq(false));
        
        //global, tags
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
                put("duration", "00:00:03.000");
            }}));
        }});
        FFmpeg.setMetadata(testSource, new ArrayList<>(testModifiers.get().values()).get(0), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(3))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(true), ArgumentMatchers.eq(false));
    }
    
    /**
     * JUnit test of addMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#addMetadata(File, Map, File)
     * @see FFmpeg#addMetadata(File, FFmpeg.Identifier, FFmpeg.MediaInfo.MetadataTags, File)
     * @see FFmpeg#addMetadata(File, FFmpeg.MediaInfo.MetadataTags, File)
     * @see FFmpeg#addMetadata(File, FFmpeg.Identifier, String, String, File)
     * @see FFmpeg#addMetadata(File, String, String, File)
     */
    @Test
    public void testAddMetadata() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        final AtomicReference<Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags>> testModifiers = new AtomicReference<>(null);
        final File testSource = new File(testResources, "test.mkv");
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testOutput = new File(testDir, "test.mkv");
        final FFmpeg.MediaInfo mediaInfo = FFmpeg.getMediaInfo(testSource);
        
        PowerMockito.doAnswer((Answer<String>) invocation -> {
            final Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags> modifiersMap = invocation.getArgument(1);
            Assert.assertNotNull(modifiersMap);
            Assert.assertNotNull(testModifiers.get());
            final Map<FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>, FFmpeg.MediaInfo.MetadataTags> testModifiersMap =
                    ((testModifiers.get().size() == 1) && (testModifiers.get().keySet().toArray(FFmpeg.Identifier[]::new)[0].isSingularScoped())) ?
                    new LinkedHashMap<>() {{
                        testModifiers.get().forEach((key, value) -> put((FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>) key, value));
                    }} :
                    FFmpeg.Identifier.decompose(new ArrayList<>(testModifiers.get().keySet()), new ArrayList<>(testModifiers.get().values()), mediaInfo);
            final Map<String, String> testModifiersStringMap = testModifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertEquals(testModifiersMap.size(), modifiersMap.size());
            final Map<String, String> modifiersStringMap = modifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertFalse(testModifiersStringMap.entrySet().stream().anyMatch(e -> (!modifiersStringMap.containsKey(e.getKey()) || !Objects.equals(e.getValue(), modifiersStringMap.get(e.getKey())))));
            return null;
        }).when(FFmpeg.class, "modifyMetadata", ArgumentMatchers.any(File.class), ArgumentMatchers.anyMap(), ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean());
        
        //map
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.ofIndex(3), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("name", "A Name");
                put("duration", "00:00:03.000");
            }}));
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
                put("duration", "00:00:03.000");
            }}));
            put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("language", "eng");
            }}));
        }});
        FFmpeg.addMetadata(testSource, new LinkedHashMap<>(testModifiers.get()), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(1))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(false));
        
        //identifier, tags
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
                put("duration", "00:00:03.000");
            }}));
        }});
        FFmpeg.addMetadata(testSource, new ArrayList<>(testModifiers.get().keySet()).get(0), new ArrayList<>(testModifiers.get().values()).get(0), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(2))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(false));
        
        //global, tags
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
                put("duration", "00:00:03.000");
            }}));
        }});
        FFmpeg.addMetadata(testSource, new ArrayList<>(testModifiers.get().values()).get(0), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(3))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(false));
        
        //identifier, tag name and value
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
            }}));
        }});
        FFmpeg.addMetadata(testSource, new ArrayList<>(testModifiers.get().keySet()).get(0), "title", "The Title", testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(4))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(false));
        
        //global, tag name and value
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
                put("title", "The Title");
            }}));
        }});
        FFmpeg.addMetadata(testSource, "title", "The Title", testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(5))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(false));
    }
    
    /**
     * JUnit test of removeMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#removeMetadata(File, Map, File)
     * @see FFmpeg#removeMetadata(File, FFmpeg.Identifier, FFmpeg.MediaInfo.MetadataTags, File)
     * @see FFmpeg#removeMetadata(File, FFmpeg.MediaInfo.MetadataTags, File)
     * @see FFmpeg#removeMetadata(File, FFmpeg.Identifier, List, File)
     * @see FFmpeg#removeMetadata(File, List, File)
     * @see FFmpeg#removeMetadata(File, FFmpeg.Identifier, String, File)
     * @see FFmpeg#removeMetadata(File, String, File)
     */
    @Test
    public void testRemoveMetadata() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        final AtomicReference<Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags>> testModifiers = new AtomicReference<>(null);
        final File testSource = new File(testResources, "test.mkv");
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testOutput = new File(testDir, "test.mkv");
        final FFmpeg.MediaInfo mediaInfo = FFmpeg.getMediaInfo(testSource);
        
        PowerMockito.doAnswer((Answer<String>) invocation -> {
            final Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags> modifiersMap = invocation.getArgument(1);
            Assert.assertNotNull(modifiersMap);
            Assert.assertNotNull(testModifiers.get());
            final Map<FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>, FFmpeg.MediaInfo.MetadataTags> testModifiersMap =
                    ((testModifiers.get().size() == 1) && (testModifiers.get().keySet().toArray(FFmpeg.Identifier[]::new)[0].isSingularScoped())) ?
                    new LinkedHashMap<>() {{
                        testModifiers.get().forEach((key, value) -> put((FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>) key, value));
                    }} :
                    FFmpeg.Identifier.decompose(new ArrayList<>(testModifiers.get().keySet()), new ArrayList<>(testModifiers.get().values()), mediaInfo);
            final Map<String, String> testModifiersStringMap = testModifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertEquals(testModifiersMap.size(), modifiersMap.size());
            final Map<String, String> modifiersStringMap = modifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertFalse(testModifiersStringMap.entrySet().stream().anyMatch(e -> (!modifiersStringMap.containsKey(e.getKey()) || !Objects.equals(e.getValue(), modifiersStringMap.get(e.getKey())))));
            return null;
        }).when(FFmpeg.class, "modifyMetadata", ArgumentMatchers.any(File.class), ArgumentMatchers.anyMap(), ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean());
        
        //map
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.ofIndex(3), new FFmpeg.MediaInfo.MetadataTags(List.of("name", "duration")));
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(List.of("title", "duration")));
            put(FFmpeg.Identifier.Chapter.all(), new FFmpeg.MediaInfo.MetadataTags(List.of("language")));
        }});
        FFmpeg.removeMetadata(testSource, new LinkedHashMap<>(testModifiers.get()), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(1))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(true));
        
        //identifier, tags
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(List.of("title", "duration")));
        }});
        FFmpeg.removeMetadata(testSource, new ArrayList<>(testModifiers.get().keySet()).get(0), new ArrayList<>(testModifiers.get().values()).get(0), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(2))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(true));
        
        //global, tags
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title", "duration")));
        }});
        FFmpeg.removeMetadata(testSource, new ArrayList<>(testModifiers.get().values()).get(0), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(3))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(true));
        
        //identifier, list
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(List.of("title", "duration")));
        }});
        FFmpeg.removeMetadata(testSource, new ArrayList<>(testModifiers.get().keySet()).get(0), List.of("title", "duration"), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(4))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(true));
        
        //global, list
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title", "duration")));
        }});
        FFmpeg.removeMetadata(testSource, List.of("title", "duration"), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(5))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(true));
        
        //identifier, tag name
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), new FFmpeg.MediaInfo.MetadataTags(List.of("title")));
        }});
        FFmpeg.removeMetadata(testSource, new ArrayList<>(testModifiers.get().keySet()).get(0), "title", testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(6))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(true));
        
        //global, tag name
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Global.get(), new FFmpeg.MediaInfo.MetadataTags(List.of("title")));
        }});
        FFmpeg.removeMetadata(testSource, "title", testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(7))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(false), ArgumentMatchers.eq(true));
    }
    
    /**
     * JUnit test of clearMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#clearMetadata(File, List, File)
     * @see FFmpeg#clearMetadata(File, FFmpeg.Identifier, File)
     * @see FFmpeg#clearMetadata(File, File)
     */
    @Test
    public void testClearMetadata() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        final AtomicReference<Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags>> testModifiers = new AtomicReference<>(null);
        final File testSource = new File(testResources, "test.mkv");
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testOutput = new File(testDir, "test.mkv");
        final FFmpeg.MediaInfo mediaInfo = FFmpeg.getMediaInfo(testSource);
        
        PowerMockito.doAnswer((Answer<String>) invocation -> {
            final Map<FFmpeg.Identifier<?>, FFmpeg.MediaInfo.MetadataTags> modifiersMap = invocation.getArgument(1);
            Assert.assertNotNull(modifiersMap);
            Assert.assertNotNull(testModifiers.get());
            final Map<FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>, FFmpeg.MediaInfo.MetadataTags> testModifiersMap =
                    ((testModifiers.get().size() == 1) && (testModifiers.get().keySet().toArray(FFmpeg.Identifier[]::new)[0].isSingularScoped())) ?
                    new LinkedHashMap<>() {{
                        testModifiers.get().forEach((key, value) -> put((FFmpeg.Identifier<FFmpeg.Identifier.Scope.Singular>) key, value));
                    }} :
                    FFmpeg.Identifier.decompose(new ArrayList<>(testModifiers.get().keySet()), new ArrayList<>(testModifiers.get().values()), mediaInfo);
            final Map<String, String> testModifiersStringMap = testModifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertEquals(testModifiersMap.size(), modifiersMap.size());
            final Map<String, String> modifiersStringMap = modifiersMap.entrySet().stream().collect(Collectors.toMap(Object::toString, Object::toString));
            Assert.assertFalse(testModifiersStringMap.entrySet().stream().anyMatch(e -> (!modifiersStringMap.containsKey(e.getKey()) || !Objects.equals(e.getValue(), modifiersStringMap.get(e.getKey())))));
            return null;
        }).when(FFmpeg.class, "modifyMetadata", ArgumentMatchers.any(File.class), ArgumentMatchers.anyMap(), ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean());
        
        //list
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.ofIndex(3), null);
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), null);
            put(FFmpeg.Identifier.Chapter.all(), null);
        }});
        FFmpeg.clearMetadata(testSource, new ArrayList<>(testModifiers.get().keySet()), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(1))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(true), ArgumentMatchers.eq(true));
        
        //identifier
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO), null);
        }});
        FFmpeg.clearMetadata(testSource, new ArrayList<>(testModifiers.get().keySet()).get(0), testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(2))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(true), ArgumentMatchers.eq(true));
        
        //global
        testModifiers.set(new LinkedHashMap<>() {{
            put(FFmpeg.Identifier.Global.get(), null);
        }});
        FFmpeg.clearMetadata(testSource, testOutput);
        PowerMockito.verifyPrivate(FFmpeg.class, VerificationModeFactory.times(3))
                .invoke("modifyMetadata", ArgumentMatchers.eq(testSource), ArgumentMatchers.anyMap(), ArgumentMatchers.eq(testOutput), ArgumentMatchers.eq(true), ArgumentMatchers.eq(true));
    }
    
    /**
     * JUnit test of stripMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#stripMetadata(File, File)
     */
    @Test
    public void testStripMetadata() throws Exception {
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn("").when(FFmpeg.class, "stripMediaFile", ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any(File.class));
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput = new File(Filesystem.createTemporaryDirectory(), "test.mkv");
        FFmpeg.stripMetadata(testSource, testOutput);
        PowerMockito.verifyStatic(FFmpeg.class);
        FFmpeg.stripMediaFile(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(true), ArgumentMatchers.eq(false), ArgumentMatchers.eq(testOutput));
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "stripMediaFile", ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any(File.class));
    }
    
    /**
     * JUnit test of getDisposition.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getDisposition(File, FFmpeg.Identifier.Stream)
     */
    @Test
    public void testGetDisposition() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        
        //stream type, index
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 2)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 0)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 1)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 4)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 5)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 1)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 2)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 3)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 4)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 5)).toString());
        
        //stream type
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofFirst(null)).toString());
        
        //null stream type
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 0)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 1)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 2)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 3)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 4)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 5)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 6)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 7)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 8)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 9)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 10)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 11)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 12)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 13)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 14)).toString());
        
        //index
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(0)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(1)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(2)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(3)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(4)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(5)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(6)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(7)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(8)).toString());
        Assert.assertEquals("default", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(9)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(10)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(11)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(12)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(13)).toString());
        Assert.assertEquals("", FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(14)).toString());
        
        //invalid
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 3)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, -1)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 6)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, -1)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 6)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, -1)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.DATA)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, 15)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.of(null, -1)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(15)));
        Assert.assertNull(FFmpeg.getDisposition(testVideo, FFmpeg.Identifier.Stream.ofIndex(-1)));
        Assert.assertNull(FFmpeg.getDisposition(fakeVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getDisposition(null, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getDisposition(fakeVideo, null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getDisposition(null, null));
    }
    
    /**
     * JUnit test of getContainer.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getContainer(File, boolean)
     * @see FFmpeg#getContainer(File)
     */
    @Test
    public void testGetContainer() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        
        //standard
        Assert.assertEquals("matroska,webm", FFmpeg.getContainer(testVideo, true));
        Assert.assertEquals("Matroska / WebM", FFmpeg.getContainer(testVideo, false));
        
        //default abbreviate
        Assert.assertEquals("Matroska / WebM", FFmpeg.getContainer(testVideo));
        
        //invalid
        Assert.assertNull(FFmpeg.getContainer(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getContainer(null));
    }
    
    /**
     * JUnit test of getDuration.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getDuration(File)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testGetDuration() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        
        //standard
        Assert.assertEquals(3067L, FFmpeg.getDuration(testVideo));
        
        //invalid
        Assert.assertEquals(-1L, FFmpeg.getDuration(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getDuration(null));
    }
    
    /**
     * JUnit test of getStreamDuration.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getStreamDuration(File, FFmpeg.Identifier.Stream)
     */
    @Test
    public void testGetStreamDuration() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        
        //stream type, index
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 2)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 0)));
        Assert.assertEquals(3065L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 1)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2)));
        Assert.assertEquals(3041L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 4)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 5)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 1)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 2)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 3)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 4)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 5)));
        
        //stream type
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofFirst(null)));
        
        //null stream type
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 0)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 1)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 2)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 3)));
        Assert.assertEquals(3065L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 4)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 5)));
        Assert.assertEquals(3041L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 6)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 7)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 8)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 9)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 10)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 11)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 12)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 13)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 14)));
        
        //index
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(0)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(1)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(2)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(3)));
        Assert.assertEquals(3065L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(4)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(5)));
        Assert.assertEquals(3041L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(6)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(7)));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(8)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(9)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(10)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(11)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(12)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(13)));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(14)));
        
        //invalid
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 3)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, -1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 6)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, -1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 6)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, -1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.DATA)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, 15)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.of(null, -1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(15)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.Identifier.Stream.ofIndex(-1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(fakeVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamDuration(null, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamDuration(fakeVideo, null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamDuration(null, null));
    }
    
    /**
     * JUnit test of getBitrate.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getBitrate(File)
     */
    @Test
    public void testGetBitrate() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        
        //standard
        Assert.assertEquals(1476113L, FFmpeg.getBitrate(testVideo));
        
        //invalid
        Assert.assertEquals(-1L, FFmpeg.getBitrate(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getBitrate(null));
    }
    
    /**
     * JUnit test of getStreamBitrate.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getStreamBitrate(File, FFmpeg.Identifier.Stream)
     */
    @Test
    public void testGetStreamBitrate() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        
        //stream type, index
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 2)));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 0)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 4)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 5)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 2)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 3)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 4)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 5)));
        
        //stream type
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofFirst(null)));
        
        //null stream type
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 0)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 2)));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 3)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 4)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 5)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 6)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 7)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 8)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 9)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 10)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 11)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 12)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 13)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 14)));
        
        //index
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(0)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(2)));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(3)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(4)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(5)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(6)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(7)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(8)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(9)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(10)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(11)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(12)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(13)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(14)));
        
        //invalid
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 3)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, -1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 6)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, -1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 6)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, -1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.DATA)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, 15)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.of(null, -1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(15)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.Identifier.Stream.ofIndex(-1)));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(fakeVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamBitrate(null, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamBitrate(fakeVideo, null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamBitrate(null, null));
    }
    
    /**
     * JUnit test of getStreamEncoding.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getStreamEncoding(File, FFmpeg.Identifier.Stream)
     */
    @Test
    public void testGetStreamEncoding() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //stream type, index
        Assert.assertEquals("h264", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        Assert.assertEquals("hevc", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)));
        Assert.assertEquals("mpeg4", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 2)));
        Assert.assertEquals("mp3", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 0)));
        Assert.assertEquals("aac", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 1)));
        Assert.assertEquals("flac", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2)));
        Assert.assertEquals("aac", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)));
        Assert.assertEquals("opus", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 4)));
        Assert.assertEquals("vorbis", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 5)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 1)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 2)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 3)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 4)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 5)));
        Assert.assertEquals("bin_data", FFmpeg.getStreamEncoding(testVideo2, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.DATA, 0)));
        
        //stream type
        Assert.assertEquals("h264", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)));
        Assert.assertEquals("h264", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)));
        Assert.assertEquals("mp3", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)));
        Assert.assertEquals("mp3", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertEquals("bin_data", FFmpeg.getStreamEncoding(testVideo2, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.DATA)));
        Assert.assertEquals("h264", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofFirst(null)));
        
        //null stream type
        Assert.assertEquals("h264", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 0)));
        Assert.assertEquals("hevc", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 1)));
        Assert.assertEquals("mpeg4", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 2)));
        Assert.assertEquals("mp3", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 3)));
        Assert.assertEquals("aac", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 4)));
        Assert.assertEquals("flac", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 5)));
        Assert.assertEquals("aac", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 6)));
        Assert.assertEquals("opus", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 7)));
        Assert.assertEquals("vorbis", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 8)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 9)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 10)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 11)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 12)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 13)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 14)));
        Assert.assertEquals("bin_data", FFmpeg.getStreamEncoding(testVideo2, FFmpeg.Identifier.Stream.of(null, 1)));
        
        //index
        Assert.assertEquals("h264", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(0)));
        Assert.assertEquals("hevc", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(1)));
        Assert.assertEquals("mpeg4", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(2)));
        Assert.assertEquals("mp3", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(3)));
        Assert.assertEquals("aac", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(4)));
        Assert.assertEquals("flac", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(5)));
        Assert.assertEquals("aac", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(6)));
        Assert.assertEquals("opus", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(7)));
        Assert.assertEquals("vorbis", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(8)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(9)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(10)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(11)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(12)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(13)));
        Assert.assertEquals("subrip", FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(14)));
        Assert.assertEquals("bin_data", FFmpeg.getStreamEncoding(testVideo2, FFmpeg.Identifier.Stream.ofIndex(1)));
        
        //invalid
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 3)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, -1)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 6)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, -1)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 6)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, -1)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.DATA)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, 15)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.of(null, -1)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(15)));
        Assert.assertNull(FFmpeg.getStreamEncoding(testVideo, FFmpeg.Identifier.Stream.ofIndex(-1)));
        Assert.assertNull(FFmpeg.getStreamEncoding(fakeVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamEncoding(null, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamEncoding(fakeVideo, null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamEncoding(null, null));
    }
    
    /**
     * JUnit test of getFrameCount.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getFrameCount(File, FFmpeg.Identifier.Stream)
     */
    @Test
    public void testGetFrameCount() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //stream type, index
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 2)));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 0)));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 1)));
        Assert.assertEquals(29L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2)));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)));
        Assert.assertEquals(151L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 4)));
        Assert.assertEquals(286L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 5)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 1)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 2)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 3)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 4)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 5)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo2, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.DATA, 0)));
        
        //stream type
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO)));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo2, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.DATA)));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofFirst(null)));
        
        //null stream type
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 0)));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 1)));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 2)));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 3)));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 4)));
        Assert.assertEquals(29L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 5)));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 6)));
        Assert.assertEquals(151L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 7)));
        Assert.assertEquals(286L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 8)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 9)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 10)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 11)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 12)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 13)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 14)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo2, FFmpeg.Identifier.Stream.of(null, 1)));
        
        //index
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(0)));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(1)));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(2)));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(3)));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(4)));
        Assert.assertEquals(29L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(5)));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(6)));
        Assert.assertEquals(151L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(7)));
        Assert.assertEquals(286L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(8)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(9)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(10)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(11)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(12)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(13)));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(14)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo2, FFmpeg.Identifier.Stream.ofIndex(1)));
        
        //invalid
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 3)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, -1)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 6)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, -1)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 6)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, -1)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.DATA)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, 15)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.of(null, -1)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(15)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.Identifier.Stream.ofIndex(-1)));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(fakeVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getFrameCount(null, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getFrameCount(fakeVideo, null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getFrameCount(null, null));
    }
    
    /**
     * JUnit test of stripMediaFile.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#stripMediaFile(File, boolean, boolean, File)
     * @see FFmpeg#stripMediaFile(File, File)
     */
    @Test
    public void testStripMediaFile() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        final File testDir = Filesystem.createTemporaryDirectory();
        final File testSource = new File(testResources, "test.mkv");
        final File testOutput1 = new File(testDir, "test1.mkv");
        final File testOutput2 = new File(testDir, "test2.mkv");
        final File testOutput3 = new File(testDir, "test3.mkv");
        final File testOutput4 = new File(testDir, "test4.mkv");
        final File fakeSource = new File(testResources, "fakeTest.mkv");
        final File fakeOutput = new File(testDir, "fakeTest.mp4");
        
        //initial
        Assert.assertTrue(testSource.exists());
        Assert.assertFalse(Filesystem.isEmpty(testSource));
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'FFmpeg Test Video'}",
                        "Stream #0: {language:'eng', title:'Red', D:''}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', D:''}",
                        "Stream #2: {ENCODER:'Lavc58.91.100 mpeg4', language:'rus', title:'Blue', D:''}",
                        "Stream #3: {ARTIST:'Kevin MacLeod', DESCRIPTION:'MP3 Audio', language:'eng', title:'Galway', D:''}",
                        "Stream #4: {ARTIST:'Kevin MacLeod', DESCRIPTION:'AAC Audio', language:'spa', title:'Galway', D:''}",
                        "Stream #5: {ARTIST:'Kevin MacLeod', DESCRIPTION:'FLAC Audio', language:'rus', title:'Galway', D:''}",
                        "Stream #6: {ARTIST:'Kevin MacLeod', DESCRIPTION:'M4A Audio', language:'jpn', title:'Galway', D:''}",
                        "Stream #7: {ARTIST:'Kevin MacLeod', DESCRIPTION:'OPUS Audio', language:'fre', title:'Galway', D:''}",
                        "Stream #8: {ARTIST:'Kevin MacLeod', DESCRIPTION:'OGG Audio', language:'ger', title:'Galway', D:''}",
                        "Stream #9: {language:'eng', title:'English', D:''}",
                        "Stream #10: {language:'spa', title:'Spanish', D:''}",
                        "Stream #11: {language:'rus', title:'Russian', D:''}",
                        "Stream #12: {language:'jpn', title:'Japanese', D:''}",
                        "Stream #13: {language:'fre', title:'French', D:''}",
                        "Stream #14: {language:'ger', title:'German', D:''}",
                        "Chapter #1: {title:'Chapter 1'}", "Chapter #2: {title:'Second Chapter'}", "Chapter #3: {title:'The Last Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testSource).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        
        //metadata
        FFmpeg.stripMediaFile(testSource, true, false, testOutput1);
        Assert.assertTrue(testOutput1.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput1));
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100'}",
                        "Stream #0: {D:''}",
                        "Stream #1: {D:''}",
                        "Stream #2: {D:''}",
                        "Stream #3: {D:''}",
                        "Stream #4: {D:''}",
                        "Stream #5: {D:''}",
                        "Stream #6: {D:''}",
                        "Stream #7: {D:''}",
                        "Stream #8: {D:''}",
                        "Stream #9: {D:''}",
                        "Stream #10: {D:''}",
                        "Stream #11: {D:''}",
                        "Stream #12: {D:''}",
                        "Stream #13: {D:''}",
                        "Stream #14: {D:''}",
                        "Chapter #1: {}", "Chapter #2: {}", "Chapter #3: {}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput1).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        
        //chapters
        FFmpeg.stripMediaFile(testSource, false, true, testOutput2);
        Assert.assertTrue(testOutput2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput2));
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'FFmpeg Test Video'}",
                        "Stream #0: {language:'eng', title:'Red', D:''}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', D:''}",
                        "Stream #2: {ENCODER:'Lavc58.91.100 mpeg4', language:'rus', title:'Blue', D:''}",
                        "Stream #3: {ARTIST:'Kevin MacLeod', DESCRIPTION:'MP3 Audio', language:'eng', title:'Galway', D:''}",
                        "Stream #4: {ARTIST:'Kevin MacLeod', DESCRIPTION:'AAC Audio', language:'spa', title:'Galway', D:''}",
                        "Stream #5: {ARTIST:'Kevin MacLeod', DESCRIPTION:'FLAC Audio', language:'rus', title:'Galway', D:''}",
                        "Stream #6: {ARTIST:'Kevin MacLeod', DESCRIPTION:'M4A Audio', language:'jpn', title:'Galway', D:''}",
                        "Stream #7: {ARTIST:'Kevin MacLeod', DESCRIPTION:'OPUS Audio', language:'fre', title:'Galway', D:''}",
                        "Stream #8: {ARTIST:'Kevin MacLeod', DESCRIPTION:'OGG Audio', language:'ger', title:'Galway', D:''}",
                        "Stream #9: {language:'eng', title:'English', D:''}",
                        "Stream #10: {language:'spa', title:'Spanish', D:''}",
                        "Stream #11: {language:'rus', title:'Russian', D:''}",
                        "Stream #12: {language:'jpn', title:'Japanese', D:''}",
                        "Stream #13: {language:'fre', title:'French', D:''}",
                        "Stream #14: {language:'ger', title:'German', D:''}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput2).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        
        //metadata, chapters
        FFmpeg.stripMediaFile(testSource, true, true, testOutput3);
        Assert.assertTrue(testOutput3.exists());
        Assert.assertFalse(Filesystem.isEmpty(testOutput3));
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100'}",
                        "Stream #0: {D:''}",
                        "Stream #1: {D:''}",
                        "Stream #2: {D:''}",
                        "Stream #3: {D:''}",
                        "Stream #4: {D:''}",
                        "Stream #5: {D:''}",
                        "Stream #6: {D:''}",
                        "Stream #7: {D:''}",
                        "Stream #8: {D:''}",
                        "Stream #9: {D:''}",
                        "Stream #10: {D:''}",
                        "Stream #11: {D:''}",
                        "Stream #12: {D:''}",
                        "Stream #13: {D:''}",
                        "Stream #14: {D:''}"
                ).collect(Collectors.joining(System.lineSeparator())),
                FFmpeg.getMediaInfo(testOutput3).metadataString().replaceAll("DURATION:'[\\d.:]+'", "D:''")
        );
        
        //default
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn("").when(FFmpeg.class, "stripMediaFile", ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any(File.class));
        FFmpeg.stripMediaFile(testSource, testOutput4);
        PowerMockito.verifyStatic(FFmpeg.class);
        FFmpeg.stripMediaFile(ArgumentMatchers.eq(testSource), ArgumentMatchers.eq(true), ArgumentMatchers.eq(true), ArgumentMatchers.eq(testOutput4));
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "stripMediaFile", ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any(File.class));
        
        //invalid
        Assert.assertEquals("[*]" + fakeSource.getAbsolutePath() + ": No such file or directory",
                FFmpeg.stripMediaFile(fakeSource, true, true, fakeOutput));
        Assert.assertTrue(FFmpeg.stripMediaFile(testSource, true, false, fakeOutput).contains(
                "[*]Could not write header for output file #0 (incorrect codec parameters ?): Invalid argument"));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.stripMediaFile(testSource, true, true, null));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.stripMediaFile(null, false, true, fakeOutput));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.stripMediaFile(null, true, false, null));
    }
    
    /**
     * JUnit test of getMaxMuxingQueueSize.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getMaxMuxingQueueSize()
     */
    @Test
    public void testGetMaxMuxingQueueSize() throws Exception {
        FFmpeg.maxMuxingQueueSize = -1;
        Assert.assertEquals(-1, FFmpeg.getMaxMuxingQueueSize());
        FFmpeg.maxMuxingQueueSize = 1024;
        Assert.assertEquals(1024, FFmpeg.getMaxMuxingQueueSize());
        Assert.assertEquals(1024, FFmpeg.getMaxMuxingQueueSize());
    }
    
    /**
     * JUnit test of getFormats.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getFormats()
     */
    @Test
    public void testGetFormats() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Format> formats;
        List<FFmpeg.Implements.Format> formatsCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        formats = FFmpeg.getFormats();
        Assert.assertNotNull(formats);
        Assert.assertFalse(formats.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        formatsCache = FFmpeg.getFormats();
        Assert.assertNotNull(formatsCache);
        Assert.assertSame(formats, formatsCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getFormats");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getFormats());
        Assert.assertTrue(FFmpeg.getFormats().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getFormats);
    }
    
    /**
     * JUnit test of getDemuxers.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getDemuxers()
     */
    @Test
    public void testGetDemuxers() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Demuxer> demuxers;
        List<FFmpeg.Implements.Demuxer> demuxersCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        demuxers = FFmpeg.getDemuxers();
        Assert.assertNotNull(demuxers);
        Assert.assertFalse(demuxers.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        demuxersCache = FFmpeg.getDemuxers();
        Assert.assertNotNull(demuxersCache);
        Assert.assertSame(demuxers, demuxersCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getDemuxers");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getDemuxers());
        Assert.assertTrue(FFmpeg.getDemuxers().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getDemuxers);
    }
    
    /**
     * JUnit test of getMuxers.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getMuxers()
     */
    @Test
    public void testGetMuxers() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Muxer> muxers;
        List<FFmpeg.Implements.Muxer> muxersCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        muxers = FFmpeg.getMuxers();
        Assert.assertNotNull(muxers);
        Assert.assertFalse(muxers.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        muxersCache = FFmpeg.getMuxers();
        Assert.assertNotNull(muxersCache);
        Assert.assertSame(muxers, muxersCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getMuxers");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getMuxers());
        Assert.assertTrue(FFmpeg.getMuxers().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getMuxers);
    }
    
    /**
     * JUnit test of getDevices.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getDevices()
     */
    @Test
    public void testGetDevices() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Device> devices;
        List<FFmpeg.Implements.Device> devicesCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        devices = FFmpeg.getDevices();
        Assert.assertNotNull(devices);
        Assert.assertFalse(devices.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        devicesCache = FFmpeg.getDevices();
        Assert.assertNotNull(devicesCache);
        Assert.assertSame(devices, devicesCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getDevices");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getDevices());
        Assert.assertTrue(FFmpeg.getDevices().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getDevices);
    }
    
    /**
     * JUnit test of getCodecs.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getCodecs()
     */
    @Test
    public void testGetCodecs() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Codec> codecs;
        List<FFmpeg.Implements.Codec> codecsCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        codecs = FFmpeg.getCodecs();
        Assert.assertNotNull(codecs);
        Assert.assertFalse(codecs.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        codecsCache = FFmpeg.getCodecs();
        Assert.assertNotNull(codecsCache);
        Assert.assertSame(codecs, codecsCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getCodecs");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getCodecs());
        Assert.assertTrue(FFmpeg.getCodecs().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getCodecs);
    }
    
    /**
     * JUnit test of getDecoders.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getDecoders()
     */
    @Test
    public void testGetDecoders() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Decoder> decoders;
        List<FFmpeg.Implements.Decoder> decodersCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        decoders = FFmpeg.getDecoders();
        Assert.assertNotNull(decoders);
        Assert.assertFalse(decoders.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        decodersCache = FFmpeg.getDecoders();
        Assert.assertNotNull(decodersCache);
        Assert.assertSame(decoders, decodersCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getDecoders");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getDecoders());
        Assert.assertTrue(FFmpeg.getDecoders().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getDecoders);
    }
    
    /**
     * JUnit test of getEncoders.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getEncoders()
     */
    @Test
    public void testGetEncoders() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Encoder> encoders;
        List<FFmpeg.Implements.Encoder> encodersCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        encoders = FFmpeg.getEncoders();
        Assert.assertNotNull(encoders);
        Assert.assertFalse(encoders.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        encodersCache = FFmpeg.getEncoders();
        Assert.assertNotNull(encodersCache);
        Assert.assertSame(encoders, encodersCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getEncoders");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getEncoders());
        Assert.assertTrue(FFmpeg.getEncoders().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getEncoders);
    }
    
    /**
     * JUnit test of getBitstreamFilters.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getBitstreamFilters()
     */
    @Test
    public void testGetBitstreamFilters() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.BitstreamFilter> bitstreamFilters;
        List<FFmpeg.Implements.BitstreamFilter> bitstreamFiltersCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        bitstreamFilters = FFmpeg.getBitstreamFilters();
        Assert.assertNotNull(bitstreamFilters);
        Assert.assertFalse(bitstreamFilters.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        bitstreamFiltersCache = FFmpeg.getBitstreamFilters();
        Assert.assertNotNull(bitstreamFiltersCache);
        Assert.assertSame(bitstreamFilters, bitstreamFiltersCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getBitstreamFilters");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getBitstreamFilters());
        Assert.assertTrue(FFmpeg.getBitstreamFilters().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getBitstreamFilters);
    }
    
    /**
     * JUnit test of getProtocols.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getProtocols()
     */
    @Test
    public void testGetProtocols() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Protocol> protocols;
        List<FFmpeg.Implements.Protocol> protocolsCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        protocols = FFmpeg.getProtocols();
        Assert.assertNotNull(protocols);
        Assert.assertFalse(protocols.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        protocolsCache = FFmpeg.getProtocols();
        Assert.assertNotNull(protocolsCache);
        Assert.assertSame(protocols, protocolsCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getProtocols");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getProtocols());
        Assert.assertTrue(FFmpeg.getProtocols().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getProtocols);
    }
    
    /**
     * JUnit test of getFilters.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getFilters()
     */
    @Test
    public void testGetFilters() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Filter> filters;
        List<FFmpeg.Implements.Filter> filtersCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        filters = FFmpeg.getFilters();
        Assert.assertNotNull(filters);
        Assert.assertFalse(filters.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        filtersCache = FFmpeg.getFilters();
        Assert.assertNotNull(filtersCache);
        Assert.assertSame(filters, filtersCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getFilters");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getFilters());
        Assert.assertTrue(FFmpeg.getFilters().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getFilters);
    }
    
    /**
     * JUnit test of getPixelFormats.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getPixelFormats()
     */
    @Test
    public void testGetPixelFormats() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.PixelFormat> pixelFormats;
        List<FFmpeg.Implements.PixelFormat> pixelFormatsCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        pixelFormats = FFmpeg.getPixelFormats();
        Assert.assertNotNull(pixelFormats);
        Assert.assertFalse(pixelFormats.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        pixelFormatsCache = FFmpeg.getPixelFormats();
        Assert.assertNotNull(pixelFormatsCache);
        Assert.assertSame(pixelFormats, pixelFormatsCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getPixelFormats");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getPixelFormats());
        Assert.assertTrue(FFmpeg.getPixelFormats().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getPixelFormats);
    }
    
    /**
     * JUnit test of getSampleFormats.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getSampleFormats()
     */
    @Test
    public void testGetSampleFormats() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.SampleFormat> sampleFormats;
        List<FFmpeg.Implements.SampleFormat> sampleFormatsCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        sampleFormats = FFmpeg.getSampleFormats();
        Assert.assertNotNull(sampleFormats);
        Assert.assertFalse(sampleFormats.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        sampleFormatsCache = FFmpeg.getSampleFormats();
        Assert.assertNotNull(sampleFormatsCache);
        Assert.assertSame(sampleFormats, sampleFormatsCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getSampleFormats");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getSampleFormats());
        Assert.assertTrue(FFmpeg.getSampleFormats().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getSampleFormats);
    }
    
    /**
     * JUnit test of getChannels.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getChannels()
     */
    @Test
    public void testGetChannels() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Channel> channels;
        List<FFmpeg.Implements.Channel> channelsCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        channels = FFmpeg.getChannels();
        Assert.assertNotNull(channels);
        Assert.assertFalse(channels.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        channelsCache = FFmpeg.getChannels();
        Assert.assertNotNull(channelsCache);
        Assert.assertSame(channels, channelsCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getChannels");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getChannels());
        Assert.assertTrue(FFmpeg.getChannels().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getChannels);
    }
    
    /**
     * JUnit test of getChannelLayouts.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getChannelLayouts()
     */
    @Test
    public void testGetChannelLayouts() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.ChannelLayout> channelLayouts;
        List<FFmpeg.Implements.ChannelLayout> channelLayoutsCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        channelLayouts = FFmpeg.getChannelLayouts();
        Assert.assertNotNull(channelLayouts);
        Assert.assertFalse(channelLayouts.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        channelLayoutsCache = FFmpeg.getChannelLayouts();
        Assert.assertNotNull(channelLayoutsCache);
        Assert.assertSame(channelLayouts, channelLayoutsCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getChannelLayouts");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getChannelLayouts());
        Assert.assertTrue(FFmpeg.getChannelLayouts().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getChannelLayouts);
    }
    
    /**
     * JUnit test of getColors.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getColors()
     */
    @Test
    public void testGetColors() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        List<FFmpeg.Implements.Color> colors;
        List<FFmpeg.Implements.Color> colorsCache;
        
        //standard
        FFmpeg.Implements.clearCache();
        colors = FFmpeg.getColors();
        Assert.assertNotNull(colors);
        Assert.assertFalse(colors.isEmpty());
        
        //cache
        PowerMockito.mockStatic(CmdLine.class);
        colorsCache = FFmpeg.getColors();
        Assert.assertNotNull(colorsCache);
        Assert.assertSame(colors, colorsCache);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        
        //invalid
        PowerMockito.mockStatic(FFmpeg.class);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "getColors");
        PowerMockito.doReturn("").when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        Assert.assertNotNull(FFmpeg.getColors());
        Assert.assertTrue(FFmpeg.getColors().isEmpty());
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.doCallRealMethod().when(FFmpeg.class, "ffmpeg", ArgumentMatchers.anyString());
        FFmpeg.Implements.clearCache();
        TestUtils.assertException(NullPointerException.class, FFmpeg::getColors);
    }
    
    /**
     * JUnit test of setFFmpegExecutable.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#setFFmpegExecutable(File)
     */
    @Test
    public void testSetFFmpegExecutable() throws Exception {
        PowerMockito.mockStatic(FFmpeg.Implements.class);
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffmpeg"));
        FFmpeg.setFFmpegExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffmpeg")).getAbsolutePath());
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffmpeg")).getAbsolutePath());
        PowerMockito.verifyStatic(FFmpeg.Implements.class);
        FFmpeg.Implements.clearCache();
        FFmpeg.setFFmpegExecutable(testExecutable);
        PowerMockito.verifyNoMoreInteractions(FFmpeg.Implements.class);
        FFmpeg.Implements.clearCache();
    }
    
    /**
     * JUnit test of setFFprobeExecutable.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#setFFprobeExecutable(File)
     */
    @Test
    public void testSetFFprobeExecutable() throws Exception {
        PowerMockito.mockStatic(FFmpeg.Implements.class);
        final File testExecutable = new File(testResources, "ffprobe.exe");
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffprobe"));
        FFmpeg.setFFprobeExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffprobe")).getAbsolutePath());
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffprobe")).getAbsolutePath());
        PowerMockito.verifyNoMoreInteractions(FFmpeg.Implements.class);
    }
    
    /**
     * JUnit test of setFFplayExecutable.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#setFFplayExecutable(File)
     */
    @Test
    public void testSetFFplayExecutable() throws Exception {
        PowerMockito.mockStatic(FFmpeg.Implements.class);
        final File testExecutable = new File(testResources, "ffplay.exe");
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffplay"));
        FFmpeg.setFFplayExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffplay")).getAbsolutePath());
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffplay")).getAbsolutePath());
        PowerMockito.verifyNoMoreInteractions(FFmpeg.Implements.class);
    }
    
    /**
     * JUnit test of setMaxMuxingQueueSize.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#setMaxMuxingQueueSize(int)
     */
    @Test
    public void testSetMaxMuxingQueueSize() throws Exception {
        FFmpeg.setMaxMuxingQueueSize(-1);
        Assert.assertEquals(-1, FFmpeg.maxMuxingQueueSize);
        FFmpeg.setMaxMuxingQueueSize(1024);
        Assert.assertEquals(1024, FFmpeg.maxMuxingQueueSize);
        Assert.assertEquals(1024, FFmpeg.maxMuxingQueueSize);
    }
    
    /**
     * JUnit test of FFmpegProgressBar.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.FFmpegProgressBar
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testFFmpegProgressBar() throws Exception {
        FFmpeg.FFmpegProgressBar progressBar;
        final File testVideo = new File(testResources, "test.mkv");
        final File testOutput = new File(testResources, "test.mp4");
        
        //constants
        Assert.assertEquals("^\\s*frame=\\s*(?<frame>\\d+)\\s+fps=\\s*(?<fps>[\\d.]+)\\s+q=\\s*(?<q>[\\-\\d.]+)\\s+(?:Lq=\\s*(?<Lq>[\\-\\d.]+)\\s+)?(?:q=\\s*(?<q2>[\\-\\d.]+)\\s+)?(?:Lsize=\\s*(?<Lsize>[\\d.]+kB)\\s+)?time=\\s*(?<time>[\\d:.]+)\\s+bitrate=\\s*(?<bitrate>[\\d.]+kbits/s)\\s+speed=\\s*(?<speed>[\\d.]+x)\\s*$",
                FFmpeg.FFmpegProgressBar.MUXING_PROGRESS_LOG_PATTERN.pattern());
        Assert.assertEquals("^\\s*out_time_us=(?<outputTime>\\d+)\\s*$",
                FFmpeg.FFmpegProgressBar.MUXING_PROGRESS_PATTERN.pattern());
        Assert.assertEquals("^\\s*video:\\s*(?<video>\\d+kB)\\s+audio:\\s*(?<audio>\\d+kB)\\s+subtitle:\\s*(?<subtitle>\\d+kB)\\s+other\\sstreams:\\s*(?<otherStreams>\\d+kB)\\s+global\\sheaders:\\s*(?<globalHeaders>\\d+kB)\\s+muxing\\soverhead:\\s*(?<muxingOverhead>[\\d.]+%)\\s*$",
                FFmpeg.FFmpegProgressBar.MUXING_COMPLETE_LOG_PATTERN.pattern());
        Assert.assertEquals("^\\s*progress=end\\s*$",
                FFmpeg.FFmpegProgressBar.MUXING_COMPLETE_PATTERN.pattern());
        
        //constructors
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", Arrays.asList(testVideo, testVideo, testOutput), testOutput, 30L);
        Assert.assertNotNull(progressBar);
        Assert.assertEquals(Arrays.asList(testVideo, testVideo, testOutput), TestUtils.getField(progressBar, "sourceFiles"));
        Assert.assertEquals(testOutput, TestUtils.getField(progressBar, "outputFile"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("Test FFmpeg Progress Bar", progressBar.getTitle());
        Assert.assertEquals(30L, progressBar.getTotal());
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("s", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        progressBar = new FFmpeg.FFmpegProgressBar(Arrays.asList(testVideo, testVideo, testOutput), testOutput, 30L);
        Assert.assertNotNull(progressBar);
        Assert.assertEquals(Arrays.asList(testVideo, testVideo, testOutput), TestUtils.getField(progressBar, "sourceFiles"));
        Assert.assertEquals(testOutput, TestUtils.getField(progressBar, "outputFile"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("", progressBar.getTitle());
        Assert.assertEquals(30L, progressBar.getTotal());
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("s", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo, testOutput, 30L);
        Assert.assertNotNull(progressBar);
        Assert.assertEquals(Collections.singletonList(testVideo), TestUtils.getField(progressBar, "sourceFiles"));
        Assert.assertEquals(testOutput, TestUtils.getField(progressBar, "outputFile"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("Test FFmpeg Progress Bar", progressBar.getTitle());
        Assert.assertEquals(30L, progressBar.getTotal());
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("s", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        progressBar = new FFmpeg.FFmpegProgressBar(testVideo, testOutput, 30L);
        Assert.assertNotNull(progressBar);
        Assert.assertEquals(Collections.singletonList(testVideo), TestUtils.getField(progressBar, "sourceFiles"));
        Assert.assertEquals(testOutput, TestUtils.getField(progressBar, "outputFile"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("", progressBar.getTitle());
        Assert.assertEquals(30L, progressBar.getTotal());
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("s", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", Arrays.asList(testVideo, testVideo, testOutput), testOutput);
        Assert.assertNotNull(progressBar);
        Assert.assertEquals(Arrays.asList(testVideo, testVideo, testOutput), TestUtils.getField(progressBar, "sourceFiles"));
        Assert.assertEquals(testOutput, TestUtils.getField(progressBar, "outputFile"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("Test FFmpeg Progress Bar", progressBar.getTitle());
        Assert.assertEquals(3L, progressBar.getTotal());
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("s", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        progressBar = new FFmpeg.FFmpegProgressBar(Arrays.asList(testVideo, testVideo, testOutput), testOutput);
        Assert.assertNotNull(progressBar);
        Assert.assertEquals(Arrays.asList(testVideo, testVideo, testOutput), TestUtils.getField(progressBar, "sourceFiles"));
        Assert.assertEquals(testOutput, TestUtils.getField(progressBar, "outputFile"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("", progressBar.getTitle());
        Assert.assertEquals(3L, progressBar.getTotal());
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("s", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo, testOutput);
        Assert.assertNotNull(progressBar);
        Assert.assertEquals(Collections.singletonList(testVideo), TestUtils.getField(progressBar, "sourceFiles"));
        Assert.assertEquals(testOutput, TestUtils.getField(progressBar, "outputFile"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("Test FFmpeg Progress Bar", progressBar.getTitle());
        Assert.assertEquals(3L, progressBar.getTotal());
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("s", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        progressBar = new FFmpeg.FFmpegProgressBar(testVideo, testOutput);
        Assert.assertNotNull(progressBar);
        Assert.assertEquals(Collections.singletonList(testVideo), TestUtils.getField(progressBar, "sourceFiles"));
        Assert.assertEquals(testOutput, TestUtils.getField(progressBar, "outputFile"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("", progressBar.getTitle());
        Assert.assertEquals(3L, progressBar.getTotal());
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("s", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        
        //processLog
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo, testOutput, 30L);
        Assert.assertTrue(progressBar.processLog("out_time_us=5000000"));
        Assert.assertEquals(5L, progressBar.getProgress());
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertFalse(progressBar.processLog("progress=continue"));
        Assert.assertEquals(5L, progressBar.getProgress());
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Thread.sleep(ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 2);
        Assert.assertTrue(progressBar.processLog("out_time_us=10000000", false));
        Assert.assertEquals(10L, progressBar.getProgress());
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Thread.sleep(ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 2);
        Assert.assertFalse(progressBar.processLog("This is a warning", true));
        Assert.assertFalse(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("This is a warning", ((List<String>) TestUtils.getField(progressBar, "errors")).get(0));
        Assert.assertFalse(progressBar.processLog("Press [q] to stop, [?] for help", true));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertFalse(progressBar.processLog("  Warning", true));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertFalse(progressBar.processLog("Input #1: Stream 3", true));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertFalse(progressBar.processLog("Output #1: Stream 3", true));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertFalse(progressBar.processLog("Stream mapping: 0:0->0 (copy)", true));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertFalse(progressBar.processLog("[mp4@010981151312354] Working", true));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertFalse(progressBar.processLog("This is another warning", true));
        Assert.assertFalse(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("This is another warning", ((List<String>) TestUtils.getField(progressBar, "errors")).get(0));
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(progressBar.processLog("progress=end"));
        Assert.assertFalse(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("This is another warning", ((List<String>) TestUtils.getField(progressBar, "errors")).get(0));
        Assert.assertTrue((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        System.out.println();
        
        //complete, complete naturally
        progressBar = PowerMockito.spy(new FFmpeg.FFmpegProgressBar(testVideo, testOutput));
        Mockito.doNothing().when(progressBar).complete(ArgumentMatchers.anyBoolean());
        Mockito.doNothing().when(progressBar).fail(ArgumentMatchers.anyBoolean());
        Mockito.doNothing().when(progressBar).fail(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        TestUtils.setField(progressBar, "completedNaturally", true);
        progressBar.complete();
        Mockito.verify(progressBar).complete();
        Mockito.verify(progressBar).complete(ArgumentMatchers.eq(true));
        Mockito.verifyNoMoreInteractions(progressBar);
        
        //complete, fail
        progressBar = PowerMockito.spy(new FFmpeg.FFmpegProgressBar(testVideo, testOutput));
        Mockito.doNothing().when(progressBar).complete(ArgumentMatchers.anyBoolean());
        Mockito.doNothing().when(progressBar).fail(ArgumentMatchers.anyBoolean());
        Mockito.doNothing().when(progressBar).fail(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        TestUtils.setField(progressBar, "completedNaturally", false);
        TestUtils.setField(progressBar, "errors", new ArrayList<String>());
        progressBar.complete();
        Mockito.verify(progressBar).complete();
        Mockito.verify(progressBar).fail(ArgumentMatchers.eq(true), ArgumentMatchers.eq(Console.ConsoleEffect.RED.apply(
                "See method response for more information")));
        Mockito.verifyNoMoreInteractions(progressBar);
        
        //complete, fail with logs
        progressBar = PowerMockito.spy(new FFmpeg.FFmpegProgressBar(testVideo, testOutput));
        Mockito.doNothing().when(progressBar).complete(ArgumentMatchers.anyBoolean());
        Mockito.doNothing().when(progressBar).fail(ArgumentMatchers.anyBoolean());
        Mockito.doNothing().when(progressBar).fail(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        TestUtils.setField(progressBar, "completedNaturally", false);
        TestUtils.setField(progressBar, "errors", Arrays.asList("Errors:", "Error 1", "Error 2"));
        progressBar.complete();
        Mockito.verify(progressBar).complete();
        Mockito.verify(progressBar).fail(ArgumentMatchers.eq(true), ArgumentMatchers.eq(Console.ConsoleEffect.RED.apply(
                "See method response for more information" + System.lineSeparator() + "Errors:" + System.lineSeparator() + "Error 1" + System.lineSeparator() + "Error 2")));
        Mockito.verifyNoMoreInteractions(progressBar);
        
        //full example, complete
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo, testOutput, 30L);
        for (int i = 5; i <= 30; i += 5) {
            Assert.assertTrue(progressBar.processLog("out_time_us=" + (i * 1000000)));
            Assert.assertFalse(progressBar.processLog("progress=continue"));
            Thread.sleep(ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 2);
        }
        Assert.assertTrue(progressBar.processLog("progress=end", false));
        progressBar.complete();
        Assert.assertTrue(progressBar.isComplete());
        Assert.assertFalse(progressBar.isFailed());
        
        //full example, complete
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo, testOutput, 30L);
        for (int i = 5; i <= 20; i += 5) {
            Assert.assertTrue(progressBar.processLog("out_time_us=" + (i * 1000000)));
            Assert.assertFalse(progressBar.processLog("progress=continue"));
            Thread.sleep(ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 2);
        }
        Assert.assertFalse(progressBar.processLog("Conversion failed", true));
        progressBar.complete();
        Assert.assertFalse(progressBar.isComplete());
        Assert.assertTrue(progressBar.isFailed());
    }
    
    /**
     * JUnit test of MediaInfo.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo
     * @see #testMediaInfoMetadataTags()
     * @see #testMediaInfoMetadataBase()
     * @see #testMediaInfoFormat()
     * @see #testMediaInfoStream()
     * @see #testMediaInfoChapter()
     */
    @Test
    public void testMediaInfo() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.MediaInfo mediaInfo = FFmpeg.getMediaInfo(testVideo);
        Assert.assertNotNull(mediaInfo);
        
        //base
        Assert.assertNotNull(mediaInfo.getData());
        Assert.assertNotNull(mediaInfo.getFormat());
        Assert.assertNotNull(mediaInfo.getStreams());
        Assert.assertNotNull(mediaInfo.getChapters());
        Assert.assertNotNull(mediaInfo.getMetadata());
        Assert.assertEquals(15, mediaInfo.getStreams().size());
        Assert.assertEquals(3, mediaInfo.getChapters().size());
        Assert.assertEquals(2, mediaInfo.getMetadata().size());
        
        //toString
        Assert.assertEquals(
                "Media (test.mkv): matroska,webm: 565905B: 1476113B/s: Streams [15]: Chapters [3]: Programs [0]",
                mediaInfo.toString()
        );
        Assert.assertEquals(Stream.of(
                        "Media (test.mkv): matroska,webm: 565905B: 1476113B/s: Streams [15]: Chapters [3]: Programs [0]",
                        "Format (test.mkv): matroska,webm: 565905B: 1476113B/s: Streams [15]: Programs [0]",
                        "Stream #0: Video (eng): h264: \"Red\"",
                        "Stream #1: Video (spa): hevc: \"Green\"",
                        "Stream #2: Video (rus): mpeg4: \"Blue\"",
                        "Stream #3: Audio (eng): mp3: \"Galway\"",
                        "Stream #4: Audio (spa): aac: \"Galway\"",
                        "Stream #5: Audio (rus): flac: \"Galway\"",
                        "Stream #6: Audio (jpn): aac: \"Galway\"",
                        "Stream #7: Audio (fre): opus: \"Galway\"",
                        "Stream #8: Audio (ger): vorbis: \"Galway\"",
                        "Stream #9: Subtitle (eng): subrip: \"English\"",
                        "Stream #10: Subtitle (spa): subrip: \"Spanish\"",
                        "Stream #11: Subtitle (rus): subrip: \"Russian\"",
                        "Stream #12: Subtitle (jpn): subrip: \"Japanese\"",
                        "Stream #13: Subtitle (fre): subrip: \"French\"",
                        "Stream #14: Subtitle (ger): subrip: \"German\"",
                        "Chapter #1: (00:00:00.000 --> 00:00:01.000): \"Chapter 1\"",
                        "Chapter #2: (00:00:01.100 --> 00:00:01.800): \"Second Chapter\"",
                        "Chapter #3: (00:00:01.900 --> 00:00:02.999): \"The Last Chapter\""
                ).collect(Collectors.joining(System.lineSeparator())),
                mediaInfo.fullString()
        );
        Assert.assertEquals(Stream.of(
                        "Global: {ENCODER:'Lavf58.45.100', title:'FFmpeg Test Video'}",
                        "Stream #0: {language:'eng', title:'Red', DURATION:'00:00:03.023000000'}",
                        "Stream #1: {ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', DURATION:'00:00:03.023000000'}",
                        "Stream #2: {ENCODER:'Lavc58.91.100 mpeg4', language:'rus', title:'Blue', DURATION:'00:00:03.023000000'}",
                        "Stream #3: {ARTIST:'Kevin MacLeod', DESCRIPTION:'MP3 Audio', language:'eng', title:'Galway', DURATION:'00:00:03.067000000'}",
                        "Stream #4: {ARTIST:'Kevin MacLeod', DESCRIPTION:'AAC Audio', language:'spa', title:'Galway', DURATION:'00:00:03.065000000'}",
                        "Stream #5: {ARTIST:'Kevin MacLeod', DESCRIPTION:'FLAC Audio', language:'rus', title:'Galway', DURATION:'00:00:03.023000000'}",
                        "Stream #6: {ARTIST:'Kevin MacLeod', DESCRIPTION:'M4A Audio', language:'jpn', title:'Galway', DURATION:'00:00:03.041000000'}",
                        "Stream #7: {ARTIST:'Kevin MacLeod', DESCRIPTION:'OPUS Audio', language:'fre', title:'Galway', DURATION:'00:00:03.023000000'}",
                        "Stream #8: {ARTIST:'Kevin MacLeod', DESCRIPTION:'OGG Audio', language:'ger', title:'Galway', DURATION:'00:00:03.023000000'}",
                        "Stream #9: {language:'eng', title:'English', DURATION:'00:00:02.911000000'}",
                        "Stream #10: {language:'spa', title:'Spanish', DURATION:'00:00:02.911000000'}",
                        "Stream #11: {language:'rus', title:'Russian', DURATION:'00:00:02.911000000'}",
                        "Stream #12: {language:'jpn', title:'Japanese', DURATION:'00:00:02.911000000'}",
                        "Stream #13: {language:'fre', title:'French', DURATION:'00:00:02.911000000'}",
                        "Stream #14: {language:'ger', title:'German', DURATION:'00:00:02.911000000'}",
                        "Chapter #1: {title:'Chapter 1'}",
                        "Chapter #2: {title:'Second Chapter'}",
                        "Chapter #3: {title:'The Last Chapter'}"
                ).collect(Collectors.joining(System.lineSeparator())),
                mediaInfo.metadataString()
        );
        
        //stream selection
        Assert.assertNotNull(mediaInfo.getStream(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)));
        Assert.assertNotNull(mediaInfo.getStream(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)));
        Assert.assertNotNull(mediaInfo.getStream(FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertNotNull(mediaInfo.getStream(FFmpeg.Identifier.Stream.ofIndex(8)));
        Assert.assertEquals(1, mediaInfo.getStream(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)).getStreamIndex());
        Assert.assertEquals(6, mediaInfo.getStream(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)).getStreamIndex());
        Assert.assertEquals(9, mediaInfo.getStream(FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)).getStreamIndex());
        Assert.assertEquals(8, mediaInfo.getStream(FFmpeg.Identifier.Stream.ofIndex(8)).getStreamIndex());
        Assert.assertNull(mediaInfo.getStream(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 3)));
        Assert.assertNull(mediaInfo.getStream(FFmpeg.Identifier.Stream.ofIndex(16)));
        
        //stream metadata
        Assert.assertNotNull(mediaInfo.getMetadata(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)));
        Assert.assertNotNull(mediaInfo.getMetadata(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)));
        Assert.assertNotNull(mediaInfo.getMetadata(FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)));
        Assert.assertNotNull(mediaInfo.getMetadata(FFmpeg.Identifier.Stream.ofIndex(8)));
        Assert.assertEquals(4, mediaInfo.getMetadata(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)).size());
        Assert.assertEquals(5, mediaInfo.getMetadata(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 3)).size());
        Assert.assertEquals(3, mediaInfo.getMetadata(FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.SUBTITLE)).size());
        Assert.assertEquals(5, mediaInfo.getMetadata(FFmpeg.Identifier.Stream.ofIndex(8)).size());
        Assert.assertNull(mediaInfo.getMetadata(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 3)));
        Assert.assertNull(mediaInfo.getMetadata(FFmpeg.Identifier.Stream.ofIndex(16)));
        
        //components
        testMediaInfoMetadataTags();
        testMediaInfoMetadataBase();
        testMediaInfoFormat();
        testMediaInfoStream();
        testMediaInfoChapter();
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for MetadataTags.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.MetadataTags
     */
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    private void testMediaInfoMetadataTags() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.MediaInfo mediaInfo = FFmpeg.getMediaInfo(testVideo);
        Assert.assertNotNull(mediaInfo);
        FFmpeg.MediaInfo.MetadataTags tags;
        FFmpeg.MediaInfo.MetadataTags tags2;
        
        //global
        tags = mediaInfo.getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(2, tags.size());
        Assert.assertEquals(2, tags.get().size());
        Assert.assertEquals("Lavf58.45.100", tags.get("ENCODER"));
        Assert.assertEquals("FFmpeg Test Video", tags.get("title"));
        Assert.assertNull(tags.get("tag"));
        Assert.assertNull(tags.get(""));
        Assert.assertNull(tags.get(null));
        Assert.assertTrue(tags.contains("ENCODER"));
        Assert.assertTrue(tags.contains("encoder"));
        Assert.assertTrue(tags.contains("EnCodER"));
        Assert.assertTrue(tags.contains("title"));
        Assert.assertFalse(tags.contains("tag"));
        Assert.assertFalse(tags.contains(""));
        Assert.assertFalse(tags.contains(null));
        Assert.assertEquals("{ENCODER:'Lavf58.45.100', title:'FFmpeg Test Video'}", tags.toString());
        
        //format
        tags = mediaInfo.getFormat().getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(2, tags.size());
        Assert.assertEquals("Lavf58.45.100", tags.get("ENCODER"));
        Assert.assertEquals("FFmpeg Test Video", tags.get("title"));
        Assert.assertEquals("{ENCODER:'Lavf58.45.100', title:'FFmpeg Test Video'}", tags.toString());
        Assert.assertEquals(mediaInfo.getMetadata(), tags);
        
        //stream
        tags = mediaInfo.getStreams().get(0).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("eng", tags.get("language"));
        Assert.assertEquals("Red", tags.get("title"));
        Assert.assertEquals("00:00:03.023000000", tags.get("DURATION"));
        Assert.assertEquals("{language:'eng', title:'Red', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(1).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(4, tags.size());
        Assert.assertEquals("Lavc58.91.100 libx265", tags.get("ENCODER"));
        Assert.assertEquals("spa", tags.get("language"));
        Assert.assertEquals("Green", tags.get("title"));
        Assert.assertEquals("00:00:03.023000000", tags.get("DURATION"));
        Assert.assertEquals("{ENCODER:'Lavc58.91.100 libx265', language:'spa', title:'Green', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(2).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(4, tags.size());
        Assert.assertEquals("Lavc58.91.100 mpeg4", tags.get("ENCODER"));
        Assert.assertEquals("rus", tags.get("language"));
        Assert.assertEquals("Blue", tags.get("title"));
        Assert.assertEquals("00:00:03.023000000", tags.get("DURATION"));
        Assert.assertEquals("{ENCODER:'Lavc58.91.100 mpeg4', language:'rus', title:'Blue', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(3).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("Kevin MacLeod", tags.get("ARTIST"));
        Assert.assertEquals("MP3 Audio", tags.get("DESCRIPTION"));
        Assert.assertEquals("eng", tags.get("language"));
        Assert.assertEquals("Galway", tags.get("title"));
        Assert.assertEquals("00:00:03.067000000", tags.get("DURATION"));
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'MP3 Audio', language:'eng', title:'Galway', DURATION:'00:00:03.067000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(4).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("Kevin MacLeod", tags.get("ARTIST"));
        Assert.assertEquals("AAC Audio", tags.get("DESCRIPTION"));
        Assert.assertEquals("spa", tags.get("language"));
        Assert.assertEquals("Galway", tags.get("title"));
        Assert.assertEquals("00:00:03.065000000", tags.get("DURATION"));
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'AAC Audio', language:'spa', title:'Galway', DURATION:'00:00:03.065000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(5).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("Kevin MacLeod", tags.get("ARTIST"));
        Assert.assertEquals("FLAC Audio", tags.get("DESCRIPTION"));
        Assert.assertEquals("rus", tags.get("language"));
        Assert.assertEquals("Galway", tags.get("title"));
        Assert.assertEquals("00:00:03.023000000", tags.get("DURATION"));
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'FLAC Audio', language:'rus', title:'Galway', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(6).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("Kevin MacLeod", tags.get("ARTIST"));
        Assert.assertEquals("M4A Audio", tags.get("DESCRIPTION"));
        Assert.assertEquals("jpn", tags.get("language"));
        Assert.assertEquals("Galway", tags.get("title"));
        Assert.assertEquals("00:00:03.041000000", tags.get("DURATION"));
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'M4A Audio', language:'jpn', title:'Galway', DURATION:'00:00:03.041000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(7).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("Kevin MacLeod", tags.get("ARTIST"));
        Assert.assertEquals("OPUS Audio", tags.get("DESCRIPTION"));
        Assert.assertEquals("fre", tags.get("language"));
        Assert.assertEquals("Galway", tags.get("title"));
        Assert.assertEquals("00:00:03.023000000", tags.get("DURATION"));
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'OPUS Audio', language:'fre', title:'Galway', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(8).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(5, tags.size());
        Assert.assertEquals("Kevin MacLeod", tags.get("ARTIST"));
        Assert.assertEquals("OGG Audio", tags.get("DESCRIPTION"));
        Assert.assertEquals("ger", tags.get("language"));
        Assert.assertEquals("Galway", tags.get("title"));
        Assert.assertEquals("00:00:03.023000000", tags.get("DURATION"));
        Assert.assertEquals("{ARTIST:'Kevin MacLeod', DESCRIPTION:'OGG Audio', language:'ger', title:'Galway', DURATION:'00:00:03.023000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(9).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("eng", tags.get("language"));
        Assert.assertEquals("English", tags.get("title"));
        Assert.assertEquals("00:00:02.911000000", tags.get("DURATION"));
        Assert.assertEquals("{language:'eng', title:'English', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(10).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("spa", tags.get("language"));
        Assert.assertEquals("Spanish", tags.get("title"));
        Assert.assertEquals("00:00:02.911000000", tags.get("DURATION"));
        Assert.assertEquals("{language:'spa', title:'Spanish', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(11).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("rus", tags.get("language"));
        Assert.assertEquals("Russian", tags.get("title"));
        Assert.assertEquals("00:00:02.911000000", tags.get("DURATION"));
        Assert.assertEquals("{language:'rus', title:'Russian', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(12).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("jpn", tags.get("language"));
        Assert.assertEquals("Japanese", tags.get("title"));
        Assert.assertEquals("00:00:02.911000000", tags.get("DURATION"));
        Assert.assertEquals("{language:'jpn', title:'Japanese', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(13).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("fre", tags.get("language"));
        Assert.assertEquals("French", tags.get("title"));
        Assert.assertEquals("00:00:02.911000000", tags.get("DURATION"));
        Assert.assertEquals("{language:'fre', title:'French', DURATION:'00:00:02.911000000'}", tags.toString());
        tags = mediaInfo.getStreams().get(14).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("ger", tags.get("language"));
        Assert.assertEquals("German", tags.get("title"));
        Assert.assertEquals("00:00:02.911000000", tags.get("DURATION"));
        Assert.assertEquals("{language:'ger', title:'German', DURATION:'00:00:02.911000000'}", tags.toString());
        
        //chapters
        tags = mediaInfo.getChapters().get(0).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(1, tags.size());
        Assert.assertEquals("Chapter 1", tags.get("title"));
        Assert.assertEquals("{title:'Chapter 1'}", tags.toString());
        tags = mediaInfo.getChapters().get(1).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(1, tags.size());
        Assert.assertEquals("Second Chapter", tags.get("title"));
        Assert.assertEquals("{title:'Second Chapter'}", tags.toString());
        tags = mediaInfo.getChapters().get(2).getMetadata();
        Assert.assertNotNull(tags);
        Assert.assertEquals(1, tags.size());
        Assert.assertEquals("The Last Chapter", tags.get("title"));
        Assert.assertEquals("{title:'The Last Chapter'}", tags.toString());
        
        //constructors
        tags = new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
            put("test", "value");
            put("test 2", "2nd value");
            put("s:o=m;t(h)i[n]g\n", "s:o=m;t(h)i[n]g\n");
        }});
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{test:'value', test 2:'2nd value', s:o=m;t(h)i[n]g\n:'s:o=m;t(h)i[n]g\n'}", tags.toString());
        tags = new FFmpeg.MediaInfo.MetadataTags(List.of("test", "test 2", "s:o=m;t(h)i[n]g\n"));
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{test:null, test 2:null, s:o=m;t(h)i[n]g\n:null}", tags.toString());
        tags = new FFmpeg.MediaInfo.MetadataTags((JSONObject) new JSONParser().parse("{\"tags\":{\"test 2\":\"2nd value\",\"test\":\"value\",\"s:o=m;t(h)i[n]g\n\":\"s:o=m;t(h)i[n]g\n\"}}"));
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{s:o=m;t(h)i[n]g\n:'s:o=m;t(h)i[n]g\n', test 2:'2nd value', test:'value'}", tags.toString());
        
        //toString
        tags = new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>());
        Assert.assertNotNull(tags);
        Assert.assertEquals(0, tags.size());
        Assert.assertEquals("{}", tags.toString());
        TestUtils.setField(tags, "tags", new LinkedHashMap<String, String>() {{
            put("test", "value");
        }});
        Assert.assertEquals(1, tags.size());
        Assert.assertEquals("{test:'value'}", tags.toString());
        TestUtils.setField(tags, "tags", new LinkedHashMap<String, String>() {{
            put("test", "value");
            put("test 2", "2nd value");
            put("s:o=m;t(h)i[n]g\n", "s:o=m;t(h)i[n]g\n");
        }});
        Assert.assertEquals(3, tags.size());
        Assert.assertEquals("{test:'value', test 2:'2nd value', s:o=m;t(h)i[n]g\n:'s:o=m;t(h)i[n]g\n'}", tags.toString());
        
        //equals
        tags = new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>());
        tags2 = new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>());
        TestUtils.setField(tags, "tags", new LinkedHashMap<String, String>() {{
            put("test", "value");
            put("other", "other value");
        }});
        TestUtils.setField(tags2, "tags", new LinkedHashMap<String, String>() {{
            put("test", "value");
            put("other", "other value");
        }});
        Assert.assertEquals(tags, tags2);
        Assert.assertEquals(tags2, tags);
        TestUtils.setField(tags2, "tags", new LinkedHashMap<String, String>() {{
            put("other", "other value");
            put("test", "value");
        }});
        Assert.assertEquals(tags, tags2);
        Assert.assertEquals(tags2, tags);
        TestUtils.setField(tags2, "tags", new LinkedHashMap<String, String>() {{
            put("test", "value");
            put("other", "other value");
            put("last", "last value");
        }});
        Assert.assertNotEquals(tags, tags2);
        Assert.assertNotEquals(tags2, tags);
        TestUtils.setField(tags, "tags", new LinkedHashMap<String, String>() {{
            put("test", "value");
            put("other", "other value");
        }});
        TestUtils.setField(tags2, "tags", new LinkedHashMap<String, String>() {{
            put("another", "another value");
            put("last", "last value");
        }});
        Assert.assertNotEquals(tags, tags2);
        Assert.assertNotEquals(tags2, tags);
        tags = mediaInfo.getMetadata();
        tags2 = mediaInfo.getMetadata();
        Assert.assertEquals(tags, tags2);
        Assert.assertEquals(tags2, tags);
        Assert.assertNotEquals(tags, "");
        Assert.assertNotEquals(tags, new StringBuilder());
        Assert.assertNotEquals(tags, null);
        
        //metadata cmds
        tags = new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>() {{
            put("test", "value");
            put("test 2", "2nd value");
            put("s:o=m;t(h)i[n]g\n", "s:o=m;t(h)i[n]g\n");
        }});
        Assert.assertEquals(
                " -metadata:s:v:1 \"test\"=\"value\" -metadata:s:v:1 \"test 2\"=\"2nd value\" -metadata:s:v:1 \"s:o-m;t(h)i[n]g - \"=\"s:o-m;t(h)i[n]g - \"",
                tags.setMetadataCmd(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)));
        Assert.assertEquals(
                " -metadata:g \"test\"=\"value\" -metadata:g \"test 2\"=\"2nd value\" -metadata:g \"s:o-m;t(h)i[n]g - \"=\"s:o-m;t(h)i[n]g - \"",
                tags.setMetadataCmd());
        Assert.assertEquals(
                " -metadata:s:v:1 \"test\"= -metadata:s:v:1 \"test 2\"= -metadata:s:v:1 \"s:o-m;t(h)i[n]g - \"=",
                tags.clearMetadataCmd(FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 1)));
        Assert.assertEquals(
                " -metadata:g \"test\"= -metadata:g \"test 2\"= -metadata:g \"s:o-m;t(h)i[n]g - \"=",
                tags.clearMetadataCmd());
        
        //format string
        Assert.assertEquals("\"test\"", FFmpeg.MediaInfo.MetadataTags.formatString("test"));
        Assert.assertEquals("\"'test'\"", FFmpeg.MediaInfo.MetadataTags.formatString("\"test\""));
        Assert.assertEquals("\"test-3\"", FFmpeg.MediaInfo.MetadataTags.formatString("test=3"));
        Assert.assertEquals("\"test - key\"", FFmpeg.MediaInfo.MetadataTags.formatString("test\nkey"));
        Assert.assertEquals("\"test - key\"", FFmpeg.MediaInfo.MetadataTags.formatString("test\r\n\r\nkey"));
        Assert.assertEquals("\"'test'- - key\"", FFmpeg.MediaInfo.MetadataTags.formatString("\"test\"=\nkey"));
        Assert.assertEquals("", FFmpeg.MediaInfo.MetadataTags.formatString(""));
        Assert.assertEquals("", FFmpeg.MediaInfo.MetadataTags.formatString(null));
        
        //invalid
        TestUtils.assertNoException(() ->
                new FFmpeg.MediaInfo.MetadataTags(new LinkedHashMap<>()));
        TestUtils.assertNoException(() ->
                new FFmpeg.MediaInfo.MetadataTags(new ArrayList<>()));
        TestUtils.assertNoException(() ->
                new FFmpeg.MediaInfo.MetadataTags(new JSONObject()));
        TestUtils.assertException(NullPointerException.class, () ->
                new FFmpeg.MediaInfo.MetadataTags((LinkedHashMap<String, String>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new FFmpeg.MediaInfo.MetadataTags((ArrayList<String>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new FFmpeg.MediaInfo.MetadataTags((JSONObject) null));
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for MetadataBase.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.MetadataBase
     */
    private void testMediaInfoMetadataBase() throws Exception {
        //class
        Class<?> metadataBase = Arrays.stream(FFmpeg.MediaInfo.class.getDeclaredClasses())
                .filter(e -> e.getSimpleName().equals("MetadataBase")).findFirst().orElseGet(null);
        Assert.assertNotNull(metadataBase);
        
        //fields
        Assert.assertEquals(JSONObject.class, metadataBase.getDeclaredField("data").getType());
        Assert.assertEquals(FFmpeg.MediaInfo.MetadataTags.class, metadataBase.getDeclaredField("tags").getType());
        Assert.assertEquals(long.class, metadataBase.getDeclaredField("duration").getType());
        Assert.assertEquals(long.class, metadataBase.getDeclaredField("startTime").getType());
        Assert.assertEquals(long.class, metadataBase.getDeclaredField("endTime").getType());
        Assert.assertEquals(String.class, metadataBase.getDeclaredField("title").getType());
        Assert.assertEquals(String.class, metadataBase.getDeclaredField("language").getType());
        
        //methods
        Assert.assertNotNull(metadataBase.getConstructor(JSONObject.class));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getData"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getMetadata"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getDurationExact"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getStartTimeExact"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getEndTimeExact"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getDuration"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getStartTime"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getEndTime"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getDurationTimestamp"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getStartTimestamp"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getEndTimestamp"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getTitle"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("getLanguage"));
        Assert.assertNotNull(metadataBase.getDeclaredMethod("get", String.class));
        
        //subclasses
        Assert.assertEquals(metadataBase.getSimpleName(), FFmpeg.MediaInfo.Format.class.getSuperclass().getSimpleName());
        Assert.assertEquals(metadataBase.getSimpleName(), FFmpeg.MediaInfo.Stream.class.getSuperclass().getSimpleName());
        Assert.assertEquals(metadataBase.getSimpleName(), FFmpeg.MediaInfo.Chapter.class.getSuperclass().getSimpleName());
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for Format.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.Format
     */
    private void testMediaInfoFormat() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.MediaInfo.Format format = FFmpeg.getFormat(testVideo);
        Assert.assertNotNull(format);
        Assert.assertEquals("MetadataBase", FFmpeg.MediaInfo.Format.class.getSuperclass().getSimpleName());
        
        //base
        Assert.assertNotNull(format.getData());
        Assert.assertEquals(3067000L, format.getDurationExact());
        Assert.assertEquals(-5000L, format.getStartTimeExact());
        Assert.assertEquals(3062000L, format.getEndTimeExact());
        Assert.assertEquals(3067L, format.getDuration());
        Assert.assertEquals(-5L, format.getStartTime());
        Assert.assertEquals(3062L, format.getEndTime());
        Assert.assertEquals("00:00:03.067", format.getDurationTimestamp());
        Assert.assertEquals("-00:00:00.005", format.getStartTimestamp());
        Assert.assertEquals("00:00:03.062", format.getEndTimestamp());
        Assert.assertEquals("FFmpeg Test Video", format.getTitle());
        Assert.assertNull(format.getLanguage());
        Assert.assertEquals("1476113", format.get("bit_rate"));
        Assert.assertEquals(100L, format.get("probe_score"));
        Assert.assertNull(format.get("something_else"));
        
        //tags
        Assert.assertEquals(2, format.getMetadata().size());
        Assert.assertEquals("FFmpeg Test Video", format.getMetadata().get("title"));
        Assert.assertEquals("FFmpeg Test Video", format.getMetadata().get("TITLE"));
        Assert.assertNull(format.getMetadata().get("tag"));
        Assert.assertTrue(format.getMetadata().contains("encoder"));
        Assert.assertTrue(format.getMetadata().contains("ENCODER"));
        Assert.assertFalse(format.getMetadata().contains("tag"));
        
        //format
        Assert.assertEquals(testVideo.getAbsolutePath(), format.getMediaFile().getAbsolutePath());
        Assert.assertEquals(565905L, format.getSize());
        Assert.assertEquals(1476113L, format.getBitrate());
        Assert.assertEquals("matroska,webm", format.getFormatName());
        Assert.assertEquals("Matroska / WebM", format.getFormatNameLong());
        Assert.assertEquals(15, format.getStreamCount());
        Assert.assertEquals(0, format.getProgramCount());
        
        //toString
        Assert.assertEquals(
                "Format (test.mkv): matroska,webm: 565905B: 1476113B/s: Streams [15]: Programs [0]",
                format.toString()
        );
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for Stream.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.Stream
     * @see #testMediaInfoStreamVideoInfo()
     * @see #testMediaInfoStreamAudioInfo()
     * @see #testMediaInfoStreamSubtitleInfo()
     * @see #testMediaInfoStreamDataInfo()
     * @see #testMediaInfoStreamDisposition()
     */
    private void testMediaInfoStream() throws Exception {
        Assert.assertEquals("MetadataBase", FFmpeg.MediaInfo.Stream.class.getSuperclass().getSimpleName());
        
        //components
        testMediaInfoStreamVideoInfo();
        testMediaInfoStreamAudioInfo();
        testMediaInfoStreamSubtitleInfo();
        testMediaInfoStreamDataInfo();
        testMediaInfoStreamDisposition();
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for Stream.VideoInfo.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.Stream.VideoInfo
     */
    private void testMediaInfoStreamVideoInfo() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.MediaInfo.Stream stream = FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0));
        Assert.assertNotNull(stream);
        
        //video, base
        Assert.assertNotNull(stream.getData());
        Assert.assertEquals(3023000L, stream.getDurationExact());
        Assert.assertEquals(0L, stream.getStartTimeExact());
        Assert.assertEquals(3023000L, stream.getEndTimeExact());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3023L, stream.getEndTime());
        Assert.assertEquals("00:00:03.023", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:00.000", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.023", stream.getEndTimestamp());
        Assert.assertEquals("Red", stream.getTitle());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertEquals("10/1", stream.get("r_frame_rate"));
        Assert.assertEquals("4", stream.get("nal_length_size"));
        Assert.assertNull(stream.get("something_else"));
        
        //video, tags
        Assert.assertEquals(3, stream.getMetadata().size());
        Assert.assertEquals("Red", stream.getMetadata().get("title"));
        Assert.assertEquals("Red", stream.getMetadata().get("TITLE"));
        Assert.assertNull(stream.getMetadata().get("tag"));
        Assert.assertTrue(stream.getMetadata().contains("duration"));
        Assert.assertTrue(stream.getMetadata().contains("DURATION"));
        Assert.assertFalse(stream.getMetadata().contains("tag"));
        
        //video, stream
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals(0, stream.getStreamIndex());
        Assert.assertTrue(stream.isDefaultStream());
        Assert.assertEquals("h264", stream.getCodecName());
        Assert.assertEquals("H.264 / AVC / MPEG-4 AVC / MPEG-4 part 10", stream.getCodecNameLong());
        Assert.assertEquals(10.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(10.0, stream.getFrameRateAverage(), TestUtils.DELTA);
        Assert.assertEquals(-1L, stream.getBitrate());
        
        //video, disposition
        Assert.assertEquals(1, stream.getDisposition().getAll().size());
        Assert.assertTrue(stream.getDisposition().has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        Assert.assertFalse(stream.getDisposition().has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.COMMENT));
        
        //video, video stream
        Assert.assertNotNull(stream.getVideoInfo());
        Assert.assertEquals(320, stream.getVideoInfo().getWidth());
        Assert.assertEquals(240, stream.getVideoInfo().getHeight());
        Assert.assertEquals(12, stream.getVideoInfo().getLevel());
        Assert.assertEquals("yuv444p", stream.getVideoInfo().getPixelFormat());
        Assert.assertEquals("4:3", stream.getVideoInfo().getAspectRatio());
        Assert.assertEquals("High 4:4:4 Predictive", stream.getVideoInfo().getProfile());
        Assert.assertFalse(stream.getVideoInfo().hasClosedCaptions());
        
        //video, audio stream
        Assert.assertNotNull(stream.getAudioInfo());
        Assert.assertEquals(-1, stream.getAudioInfo().getChannels());
        Assert.assertNull(stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(-1, stream.getAudioInfo().getSampleRate());
        Assert.assertNull(stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(-1, stream.getAudioInfo().getBitsPerSample());
        
        //video, subtitle stream
        Assert.assertNotNull(stream.getSubtitleInfo());
        
        //video, data stream
        Assert.assertNotNull(stream.getDataInfo());
        
        //toString
        Assert.assertEquals(
                "Stream #0: Video (eng): h264: \"Red\"",
                stream.toString()
        );
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for Stream.AudioInfo.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.Stream.AudioInfo
     */
    private void testMediaInfoStreamAudioInfo() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.MediaInfo.Stream stream = FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO, 2));
        Assert.assertNotNull(stream);
        
        //audio, base
        Assert.assertNotNull(stream.getData());
        Assert.assertEquals(3023000L, stream.getDurationExact());
        Assert.assertEquals(23000L, stream.getStartTimeExact());
        Assert.assertEquals(3046000L, stream.getEndTimeExact());
        Assert.assertEquals(3023L, stream.getDuration());
        Assert.assertEquals(23L, stream.getStartTime());
        Assert.assertEquals(3046L, stream.getEndTime());
        Assert.assertEquals("00:00:03.023", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:00.023", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.046", stream.getEndTimestamp());
        Assert.assertEquals("Galway", stream.getTitle());
        Assert.assertEquals("rus", stream.getLanguage());
        Assert.assertEquals("0/0", stream.get("r_frame_rate"));
        Assert.assertEquals("1/44100", stream.get("codec_time_base"));
        Assert.assertNull(stream.get("something_else"));
        
        //audio, tags
        Assert.assertEquals(5, stream.getMetadata().size());
        Assert.assertEquals("Galway", stream.getMetadata().get("title"));
        Assert.assertEquals("Galway", stream.getMetadata().get("TITLE"));
        Assert.assertNull(stream.getMetadata().get("tag"));
        Assert.assertTrue(stream.getMetadata().contains("duration"));
        Assert.assertTrue(stream.getMetadata().contains("DURATION"));
        Assert.assertFalse(stream.getMetadata().contains("tag"));
        
        //audio, stream
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
        Assert.assertEquals(5, stream.getStreamIndex());
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals("flac", stream.getCodecName());
        Assert.assertEquals("FLAC (Free Lossless Audio Codec)", stream.getCodecNameLong());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(0.0, stream.getFrameRateAverage(), TestUtils.DELTA);
        Assert.assertEquals(-1L, stream.getBitrate());
        
        //audio, disposition
        Assert.assertEquals(0, stream.getDisposition().getAll().size());
        Assert.assertFalse(stream.getDisposition().has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        Assert.assertFalse(stream.getDisposition().has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.COMMENT));
        
        //audio, video stream
        Assert.assertNotNull(stream.getVideoInfo());
        Assert.assertEquals(-1, stream.getVideoInfo().getWidth());
        Assert.assertEquals(-1, stream.getVideoInfo().getHeight());
        Assert.assertEquals(-1, stream.getVideoInfo().getLevel());
        Assert.assertNull(stream.getVideoInfo().getPixelFormat());
        Assert.assertNull(stream.getVideoInfo().getAspectRatio());
        Assert.assertNull(stream.getVideoInfo().getProfile());
        Assert.assertFalse(stream.getVideoInfo().hasClosedCaptions());
        
        //audio, audio stream
        Assert.assertNotNull(stream.getAudioInfo());
        Assert.assertEquals(2, stream.getAudioInfo().getChannels());
        Assert.assertEquals("stereo", stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(44100, stream.getAudioInfo().getSampleRate());
        Assert.assertEquals("s16", stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(0, stream.getAudioInfo().getBitsPerSample());
        
        //audio, subtitle stream
        Assert.assertNotNull(stream.getSubtitleInfo());
        
        //audio, data stream
        Assert.assertNotNull(stream.getDataInfo());
        
        //toString
        Assert.assertEquals(
                "Stream #5: Audio (rus): flac: \"Galway\"",
                stream.toString()
        );
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for Stream.SubtitleInfo.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.Stream.SubtitleInfo
     */
    private void testMediaInfoStreamSubtitleInfo() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.MediaInfo.Stream stream = FFmpeg.getStream(testVideo, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE, 4));
        Assert.assertNotNull(stream);
        
        //subtitle, base
        Assert.assertNotNull(stream.getData());
        Assert.assertEquals(3067000L, stream.getDurationExact());
        Assert.assertEquals(-5000L, stream.getStartTimeExact());
        Assert.assertEquals(3062000L, stream.getEndTimeExact());
        Assert.assertEquals(3067L, stream.getDuration());
        Assert.assertEquals(-5L, stream.getStartTime());
        Assert.assertEquals(3062L, stream.getEndTime());
        Assert.assertEquals("00:00:03.067", stream.getDurationTimestamp());
        Assert.assertEquals("-00:00:00.005", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.062", stream.getEndTimestamp());
        Assert.assertEquals("French", stream.getTitle());
        Assert.assertEquals("fre", stream.getLanguage());
        Assert.assertEquals("0/0", stream.get("r_frame_rate"));
        Assert.assertEquals(3067L, stream.get("duration_ts"));
        Assert.assertNull(stream.get("something_else"));
        
        //subtitle, tags
        Assert.assertEquals(3, stream.getMetadata().size());
        Assert.assertEquals("French", stream.getMetadata().get("title"));
        Assert.assertEquals("French", stream.getMetadata().get("TITLE"));
        Assert.assertNull(stream.getMetadata().get("tag"));
        Assert.assertTrue(stream.getMetadata().contains("duration"));
        Assert.assertTrue(stream.getMetadata().contains("DURATION"));
        Assert.assertFalse(stream.getMetadata().contains("tag"));
        
        //subtitle, stream
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
        Assert.assertEquals(13, stream.getStreamIndex());
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals("subrip", stream.getCodecName());
        Assert.assertEquals("SubRip subtitle", stream.getCodecNameLong());
        Assert.assertEquals(-1L, stream.getBitrate());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(0.0, stream.getFrameRateAverage(), TestUtils.DELTA);
        
        //subtitle, disposition
        Assert.assertEquals(0, stream.getDisposition().getAll().size());
        Assert.assertFalse(stream.getDisposition().has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        Assert.assertFalse(stream.getDisposition().has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.COMMENT));
        
        //subtitle, video stream
        Assert.assertNotNull(stream.getVideoInfo());
        Assert.assertEquals(-1, stream.getVideoInfo().getWidth());
        Assert.assertEquals(-1, stream.getVideoInfo().getHeight());
        Assert.assertEquals(-1, stream.getVideoInfo().getLevel());
        Assert.assertNull(stream.getVideoInfo().getPixelFormat());
        Assert.assertNull(stream.getVideoInfo().getAspectRatio());
        Assert.assertNull(stream.getVideoInfo().getProfile());
        Assert.assertFalse(stream.getVideoInfo().hasClosedCaptions());
        
        //subtitle, audio stream
        Assert.assertNotNull(stream.getAudioInfo());
        Assert.assertEquals(-1, stream.getAudioInfo().getChannels());
        Assert.assertNull(stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(-1, stream.getAudioInfo().getSampleRate());
        Assert.assertNull(stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(-1, stream.getAudioInfo().getBitsPerSample());
        
        //subtitle, subtitle stream
        Assert.assertNotNull(stream.getSubtitleInfo());
        
        //subtitle, data stream
        Assert.assertNotNull(stream.getDataInfo());
        
        //toString
        Assert.assertEquals(
                "Stream #13: Subtitle (fre): subrip: \"French\"",
                stream.toString()
        );
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for Stream.DataInfo.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.Stream.DataInfo
     */
    private void testMediaInfoStreamDataInfo() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        final FFmpeg.MediaInfo.Stream stream = FFmpeg.getStream(testVideo2, FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.DATA, 0));
        Assert.assertNotNull(stream);
        
        //data, base
        Assert.assertNotNull(stream.getData());
        Assert.assertEquals(3004000L, stream.getDurationExact());
        Assert.assertEquals(0L, stream.getStartTimeExact());
        Assert.assertEquals(3004000L, stream.getEndTimeExact());
        Assert.assertEquals(3004L, stream.getDuration());
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3004L, stream.getEndTime());
        Assert.assertEquals("00:00:03.004", stream.getDurationTimestamp());
        Assert.assertEquals("00:00:00.000", stream.getStartTimestamp());
        Assert.assertEquals("00:00:03.004", stream.getEndTimestamp());
        Assert.assertNull(stream.getTitle());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertEquals("0/0", stream.get("r_frame_rate"));
        Assert.assertEquals(3004L, stream.get("duration_ts"));
        Assert.assertNull(stream.get("something_else"));
        
        //data, tags
        Assert.assertEquals(2, stream.getMetadata().size());
        Assert.assertNull(stream.getMetadata().get("title"));
        Assert.assertNull(stream.getMetadata().get("TITLE"));
        Assert.assertNull(stream.getMetadata().get("tag"));
        Assert.assertFalse(stream.getMetadata().contains("duration"));
        Assert.assertFalse(stream.getMetadata().contains("DURATION"));
        Assert.assertFalse(stream.getMetadata().contains("tag"));
        
        //data, stream
        Assert.assertEquals(FFmpeg.StreamType.DATA, stream.getStreamType());
        Assert.assertEquals(1, stream.getStreamIndex());
        Assert.assertFalse(stream.isDefaultStream());
        Assert.assertEquals("bin_data", stream.getCodecName());
        Assert.assertEquals("binary data", stream.getCodecNameLong());
        Assert.assertEquals(215L, stream.getBitrate());
        Assert.assertEquals(0.0, stream.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(0.0, stream.getFrameRateAverage(), TestUtils.DELTA);
        
        //data, disposition
        Assert.assertEquals(0, stream.getDisposition().getAll().size());
        Assert.assertFalse(stream.getDisposition().has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        Assert.assertFalse(stream.getDisposition().has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.COMMENT));
        
        //data, video stream
        Assert.assertNotNull(stream.getVideoInfo());
        Assert.assertEquals(-1, stream.getVideoInfo().getWidth());
        Assert.assertEquals(-1, stream.getVideoInfo().getHeight());
        Assert.assertEquals(-1, stream.getVideoInfo().getLevel());
        Assert.assertNull(stream.getVideoInfo().getPixelFormat());
        Assert.assertNull(stream.getVideoInfo().getAspectRatio());
        Assert.assertNull(stream.getVideoInfo().getProfile());
        Assert.assertFalse(stream.getVideoInfo().hasClosedCaptions());
        
        //data, audio stream
        Assert.assertNotNull(stream.getAudioInfo());
        Assert.assertEquals(-1, stream.getAudioInfo().getChannels());
        Assert.assertNull(stream.getAudioInfo().getChannelLayout());
        Assert.assertEquals(-1, stream.getAudioInfo().getSampleRate());
        Assert.assertNull(stream.getAudioInfo().getSampleFormat());
        Assert.assertEquals(-1, stream.getAudioInfo().getBitsPerSample());
        
        //data, subtitle stream
        Assert.assertNotNull(stream.getSubtitleInfo());
        
        //data, data stream
        Assert.assertNotNull(stream.getDataInfo());
        
        //toString
        Assert.assertEquals(
                "Stream #1: Data (eng): bin_data",
                stream.toString()
        );
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for Stream.Disposition.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.Stream.Disposition
     * @see FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition
     */
    private void testMediaInfoStreamDisposition() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.MediaInfo mediaInfo = FFmpeg.getMediaInfo(testVideo);
        Assert.assertNotNull(mediaInfo);
        FFmpeg.MediaInfo.Stream.Disposition disposition;
        
        //stream disposition
        Assert.assertEquals(12, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values().length);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[0]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DUB, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[1]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.ORIGINAL, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[2]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.COMMENT, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[3]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.LYRICS, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[4]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.KARAOKE, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[5]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.FORCED, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[6]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.HEARING_IMPAIRED, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[7]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.VISUAL_IMPAIRED, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[8]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.CLEAN_EFFECTS, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[9]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.ATTACHED_PIC, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[10]);
        Assert.assertEquals(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.TIMED_THUMBNAILS, FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.values()[11]);
        Assert.assertEquals("default", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT.getKey());
        Assert.assertEquals("dub", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DUB.getKey());
        Assert.assertEquals("original", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.ORIGINAL.getKey());
        Assert.assertEquals("comment", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.COMMENT.getKey());
        Assert.assertEquals("lyrics", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.LYRICS.getKey());
        Assert.assertEquals("karaoke", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.KARAOKE.getKey());
        Assert.assertEquals("forced", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.FORCED.getKey());
        Assert.assertEquals("hearing_impaired", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.HEARING_IMPAIRED.getKey());
        Assert.assertEquals("visual_impaired", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.VISUAL_IMPAIRED.getKey());
        Assert.assertEquals("clean_effects", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.CLEAN_EFFECTS.getKey());
        Assert.assertEquals("attached_pic", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.ATTACHED_PIC.getKey());
        Assert.assertEquals("timed_thumbnails", FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.TIMED_THUMBNAILS.getKey());
        
        //streams
        disposition = mediaInfo.getStreams().get(0).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(1, disposition.getAll().size());
        Assert.assertTrue(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(1).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(2).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(3).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(1, disposition.getAll().size());
        Assert.assertTrue(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(4).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(5).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(6).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(7).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(8).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(9).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(1, disposition.getAll().size());
        Assert.assertTrue(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(10).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(11).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(12).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(13).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        disposition = mediaInfo.getStreams().get(14).getDisposition();
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertFalse(disposition.has(FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT));
        
        //toString
        disposition = Mockito.mock(FFmpeg.MediaInfo.Stream.Disposition.class, Mockito.CALLS_REAL_METHODS);
        TestUtils.setField(disposition, "dispositions", Collections.emptyList());
        Assert.assertNotNull(disposition);
        Assert.assertEquals(0, disposition.getAll().size());
        Assert.assertEquals("", disposition.toString());
        TestUtils.setField(disposition, "dispositions", Collections.singletonList(
                FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT
        ));
        Assert.assertEquals(1, disposition.getAll().size());
        Assert.assertEquals("default", disposition.toString());
        Assert.assertEquals(1, disposition.getAll().size());
        Assert.assertEquals("default", disposition.toString());
        TestUtils.setField(disposition, "dispositions", Arrays.asList(
                FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.DEFAULT,
                FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.VISUAL_IMPAIRED,
                FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.HEARING_IMPAIRED,
                FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.COMMENT,
                FFmpeg.MediaInfo.Stream.Disposition.StreamDisposition.FORCED
        ));
        Assert.assertEquals(5, disposition.getAll().size());
        Assert.assertEquals("default|comment|forced|hearing_impaired|visual_impaired", disposition.toString());
        
        //invalid
        TestUtils.assertNoException(() ->
                new FFmpeg.MediaInfo.Stream.Disposition(new JSONObject()));
        TestUtils.assertException(NullPointerException.class, () ->
                new FFmpeg.MediaInfo.Stream.Disposition(null));
    }
    
    /**
     * Helper method for JUnit test of MediaInfo for Chapter.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MediaInfo.Chapter
     * @see FFmpeg.MediaInfo.Chapter.ChapterDTO
     */
    private void testMediaInfoChapter() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.MediaInfo.Chapter chapter = FFmpeg.getChapter(testVideo, 1);
        FFmpeg.MediaInfo.Chapter.ChapterDTO chapterDTO;
        Assert.assertNotNull(chapter);
        List<FFmpeg.MediaInfo.Chapter.ChapterDTO> chapterDTOs;
        String ffmetadataContent;
        File ffmetadataFile;
        Assert.assertEquals("MetadataBase", FFmpeg.MediaInfo.Chapter.class.getSuperclass().getSimpleName());
        
        //base
        Assert.assertNotNull(chapter.getData());
        Assert.assertEquals(700000L, chapter.getDurationExact());
        Assert.assertEquals(1100000L, chapter.getStartTimeExact());
        Assert.assertEquals(1800000L, chapter.getEndTimeExact());
        Assert.assertEquals(700L, chapter.getDuration());
        Assert.assertEquals(1100L, chapter.getStartTime());
        Assert.assertEquals(1800L, chapter.getEndTime());
        Assert.assertEquals("00:00:00.700", chapter.getDurationTimestamp());
        Assert.assertEquals("00:00:01.100", chapter.getStartTimestamp());
        Assert.assertEquals("00:00:01.800", chapter.getEndTimestamp());
        Assert.assertEquals("Second Chapter", chapter.getTitle());
        Assert.assertNull(chapter.getLanguage());
        Assert.assertEquals("1.100000", chapter.get("start_time"));
        Assert.assertEquals(1800000000L, chapter.get("end"));
        Assert.assertNull(chapter.get("something_else"));
        
        //tags
        Assert.assertEquals(1, chapter.getMetadata().size());
        Assert.assertEquals("Second Chapter", chapter.getMetadata().get("title"));
        Assert.assertEquals("Second Chapter", chapter.getMetadata().get("TITLE"));
        Assert.assertNull(chapter.getMetadata().get("tag"));
        Assert.assertTrue(chapter.getMetadata().contains("title"));
        Assert.assertTrue(chapter.getMetadata().contains("TITLE"));
        Assert.assertFalse(chapter.getMetadata().contains("tag"));
        
        //chapter
        Assert.assertEquals(2L, chapter.getChapterId());
        
        //toString
        Assert.assertEquals(
                "Chapter #2: (00:00:01.100 --> 00:00:01.800): \"Second Chapter\"",
                chapter.toString()
        );
        
        //DTO, initialization
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO(10000L, 18711000L, "Chapter 1");
        Assert.assertEquals(10000L, (long) TestUtils.getField(chapterDTO, "startTime"));
        Assert.assertEquals(18711000L, (long) TestUtils.getField(chapterDTO, "endTime"));
        Assert.assertEquals("Chapter 1", TestUtils.getField(chapterDTO, "title"));
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:18.722", "00:38:50.430", "Another Chapter");
        Assert.assertEquals(18722L, (long) TestUtils.getField(chapterDTO, "startTime"));
        Assert.assertEquals(2330430L, (long) TestUtils.getField(chapterDTO, "endTime"));
        Assert.assertEquals("Another Chapter", TestUtils.getField(chapterDTO, "title"));
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:00.000", "01:54:02.077", "Last=Chapter:\nA\\Really#Good=One;");
        Assert.assertEquals(0L, (long) TestUtils.getField(chapterDTO, "startTime"));
        Assert.assertEquals(6842077L, (long) TestUtils.getField(chapterDTO, "endTime"));
        Assert.assertEquals("Last=Chapter:\nA\\Really#Good=One;", TestUtils.getField(chapterDTO, "title"));
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO(1100, 1800, null);
        Assert.assertEquals(1100L, (long) TestUtils.getField(chapterDTO, "startTime"));
        Assert.assertEquals(1800L, (long) TestUtils.getField(chapterDTO, "endTime"));
        Assert.assertNull(TestUtils.getField(chapterDTO, "title"));
        
        //DTO, initialization, chapter
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO(chapter);
        Assert.assertEquals(1100L, (long) TestUtils.getField(chapterDTO, "startTime"));
        Assert.assertEquals(1800L, (long) TestUtils.getField(chapterDTO, "endTime"));
        Assert.assertEquals("Second Chapter", TestUtils.getField(chapterDTO, "title"));
        
        //DTO, toFFmetadataChapterString
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO(10000L, 18711000L, "Chapter 1");
        Assert.assertEquals(String.join("\n", "[CHAPTER]", "TIMEBASE=1/1000", "START=10000", "END=18711000", "title=Chapter 1"),
                chapterDTO.toFFmetadataChapterString());
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:18.722", "00:38:50.430", "Another Chapter");
        Assert.assertEquals(String.join("\n", "[CHAPTER]", "TIMEBASE=1/1000", "START=18722", "END=2330430", "title=Another Chapter"),
                chapterDTO.toFFmetadataChapterString());
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:00.000", "01:54:02.077", "Last=Chapter:\nA\\Really#Good=One;");
        Assert.assertEquals(String.join("\n", "[CHAPTER]", "TIMEBASE=1/1000", "START=0", "END=6842077", "title=Last\\=Chapter:\\", "A\\\\Really\\#Good\\=One\\;"),
                chapterDTO.toFFmetadataChapterString());
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO(1100, 1800, null);
        Assert.assertEquals(String.join("\n", "[CHAPTER]", "TIMEBASE=1/1000", "START=1100", "END=1800"),
                chapterDTO.toFFmetadataChapterString());
        
        //DTO, toFFmetadataChapterString, chapter
        chapterDTO = new FFmpeg.MediaInfo.Chapter.ChapterDTO(chapter);
        Assert.assertEquals(String.join("\n", "[CHAPTER]", "TIMEBASE=1/1000", "START=1100", "END=1800", "title=Second Chapter"),
                chapterDTO.toFFmetadataChapterString());
        
        //DTO, FFMETADATA
        chapterDTOs = Arrays.asList(
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(10000L, 18711000L, "Chapter 1"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:18.722", "00:38:50.430", "Another Chapter"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO("00:00:00.000", "01:54:02.077", "Last=Chapter:\nA\\Really#Good=One;"),
                new FFmpeg.MediaInfo.Chapter.ChapterDTO(1100, 1800, null)
        );
        ffmetadataContent = FFmpeg.MediaInfo.Chapter.ChapterDTO.generateFFmetadataFileContents(chapterDTOs);
        Assert.assertEquals(String.join("\n", ";FFMETADATA1\n",
                        "[CHAPTER]", "TIMEBASE=1/1000", "START=10000", "END=18711000", "title=Chapter 1",
                        "[CHAPTER]", "TIMEBASE=1/1000", "START=18722", "END=2330430", "title=Another Chapter",
                        "[CHAPTER]", "TIMEBASE=1/1000", "START=0", "END=6842077", "title=Last\\=Chapter:\\", "A\\\\Really\\#Good\\=One\\;",
                        "[CHAPTER]", "TIMEBASE=1/1000", "START=1100", "END=1800"),
                ffmetadataContent
        );
        ffmetadataFile = FFmpeg.MediaInfo.Chapter.ChapterDTO.generateFFmetadataFile(chapterDTOs, Filesystem.getTemporaryFile());
        Assert.assertNotNull(ffmetadataFile);
        Assert.assertFalse(Filesystem.isEmpty(ffmetadataFile));
        Assert.assertEquals(ffmetadataContent, Filesystem.readFileToString(ffmetadataFile));
        ffmetadataFile = FFmpeg.MediaInfo.Chapter.ChapterDTO.generateFFmetadataFile(chapterDTOs, null);
        Assert.assertNotNull(ffmetadataFile);
        Assert.assertEquals("FFMETADATA", ffmetadataFile.getName());
        Assert.assertFalse(Filesystem.isEmpty(ffmetadataFile));
        Assert.assertEquals(ffmetadataContent, Filesystem.readFileToString(ffmetadataFile));
        ffmetadataFile = FFmpeg.MediaInfo.Chapter.ChapterDTO.generateFFmetadataFile(chapterDTOs);
        Assert.assertNotNull(ffmetadataFile);
        Assert.assertEquals("FFMETADATA", ffmetadataFile.getName());
        Assert.assertFalse(Filesystem.isEmpty(ffmetadataFile));
        Assert.assertEquals(ffmetadataContent, Filesystem.readFileToString(ffmetadataFile));
    }
    
    /**
     * JUnit test of Identifier.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Identifier
     * @see FFmpeg.Identifier.IdentifierScope
     * @see FFmpeg.Identifier.IdentifierType
     * @see FFmpeg.Identifier.Scope
     * @see #testIdentifierGlobal()
     * @see #testIdentifierStream()
     * @see #testIdentifierChapter()
     */
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testIdentifier() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        FFmpeg.Identifier<?> entityId;
        FFmpeg.Identifier<?> entityId2;
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.MediaInfo mediaInfo = FFmpeg.getMediaInfo(testVideo);
        Assert.assertNotNull(mediaInfo);
        
        //identifier scope
        Assert.assertEquals(3, FFmpeg.Identifier.IdentifierScope.values().length);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, FFmpeg.Identifier.IdentifierScope.values()[0]);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, FFmpeg.Identifier.IdentifierScope.values()[1]);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, FFmpeg.Identifier.IdentifierScope.values()[2]);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, TestUtils.invokeMethod(FFmpeg.Identifier.IdentifierScope.class, "getScope", FFmpeg.Identifier.Scope.Singular.class));
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, TestUtils.invokeMethod(FFmpeg.Identifier.IdentifierScope.class, "getScope", FFmpeg.Identifier.Scope.Type.class));
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, TestUtils.invokeMethod(FFmpeg.Identifier.IdentifierScope.class, "getScope", FFmpeg.Identifier.Scope.All.class));
        Assert.assertNull(TestUtils.invokeMethod(FFmpeg.Identifier.IdentifierScope.class, "getScope", FFmpeg.Identifier.class));
        
        //identifier type
        Assert.assertEquals(3, FFmpeg.Identifier.IdentifierType.values().length);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, FFmpeg.Identifier.IdentifierType.values()[0]);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, FFmpeg.Identifier.IdentifierType.values()[1]);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, FFmpeg.Identifier.IdentifierType.values()[2]);
        Assert.assertEquals("g", TestUtils.getField(FFmpeg.Identifier.IdentifierType.values()[0], "keys"));
        Assert.assertEquals("xvasd", TestUtils.getField(FFmpeg.Identifier.IdentifierType.values()[1], "keys"));
        Assert.assertEquals("c", TestUtils.getField(FFmpeg.Identifier.IdentifierType.values()[2], "keys"));
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, TestUtils.invokeMethod(FFmpeg.Identifier.IdentifierType.class, "getType", FFmpeg.Identifier.Global.class));
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, TestUtils.invokeMethod(FFmpeg.Identifier.IdentifierType.class, "getType", FFmpeg.Identifier.Stream.class));
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, TestUtils.invokeMethod(FFmpeg.Identifier.IdentifierType.class, "getType", FFmpeg.Identifier.Chapter.class));
        Assert.assertNull(TestUtils.invokeMethod(FFmpeg.Identifier.IdentifierType.class, "getType", FFmpeg.Identifier.class));
        
        //constants
        Assert.assertEquals("(?i)^(?:(?<sourceIndex>\\d+):)?(?:(?<type>[gsxc]):?)?(?:(?<streamType>[vasd]):?)?(?<index>\\d+)?$", FFmpeg.Identifier.SPECIFIER_PATTERN.pattern());
        
        //scope
        Assert.assertNull("Identifier", FFmpeg.Identifier.Scope.class.getSuperclass());
        Assert.assertEquals("Scope", FFmpeg.Identifier.Scope.Singular.class.getInterfaces()[0].getSimpleName());
        Assert.assertEquals("Scope", FFmpeg.Identifier.Scope.Type.class.getInterfaces()[0].getSimpleName());
        Assert.assertEquals("Scope", FFmpeg.Identifier.Scope.All.class.getInterfaces()[0].getSimpleName());
        
        //static string constructors
        entityId = FFmpeg.Identifier.of("g");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isGlobalId());
        Assert.assertEquals("0:g", entityId.toString());
        entityId = FFmpeg.Identifier.of("x");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isAllScoped() && entityId.isStreamId());
        Assert.assertEquals("0:s", entityId.toString());
        entityId = FFmpeg.Identifier.of("c");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isAllScoped() && entityId.isChapterId());
        Assert.assertEquals("0:c", entityId.toString());
        entityId = FFmpeg.Identifier.of("6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isStreamId());
        Assert.assertEquals("0:s:6", entityId.toString());
        entityId = FFmpeg.Identifier.of("v");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isTypeScoped() && entityId.isStreamId());
        Assert.assertEquals("0:s:v", entityId.toString());
        entityId = FFmpeg.Identifier.of("v:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isStreamId());
        Assert.assertEquals("0:s:v:6", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:x");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isAllScoped() && entityId.isStreamId());
        Assert.assertEquals("3:s", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:s");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isTypeScoped() && entityId.isStreamId());
        Assert.assertEquals("3:s:s", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:v:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isStreamId());
        Assert.assertEquals("3:s:v:6", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:x:v:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isStreamId());
        Assert.assertEquals("3:s:v:6", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:s:v:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isStreamId());
        Assert.assertEquals("3:s:v:6", entityId.toString());
        entityId = FFmpeg.Identifier.of("c:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isChapterId());
        Assert.assertEquals("0:c:6", entityId.toString());
        entityId = FFmpeg.Identifier.of("c:v:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isChapterId());
        Assert.assertEquals("0:c:6", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:c");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isAllScoped() && entityId.isChapterId());
        Assert.assertEquals("3:c", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:c:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isChapterId());
        Assert.assertEquals("3:c:6", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:c:a:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isChapterId());
        Assert.assertEquals("3:c:6", entityId.toString());
        entityId = FFmpeg.Identifier.of("g:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isGlobalId());
        Assert.assertEquals("0:g", entityId.toString());
        entityId = FFmpeg.Identifier.of("g:v");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isGlobalId());
        Assert.assertEquals("0:g", entityId.toString());
        entityId = FFmpeg.Identifier.of("g:v:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isGlobalId());
        Assert.assertEquals("0:g", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:g");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isGlobalId());
        Assert.assertEquals("3:g", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:g:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isGlobalId());
        Assert.assertEquals("3:g", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:g:v");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isGlobalId());
        Assert.assertEquals("3:g", entityId.toString());
        entityId = FFmpeg.Identifier.of("3:g:v:6");
        Assert.assertNotNull(entityId);
        Assert.assertTrue(entityId instanceof FFmpeg.Identifier);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isGlobalId());
        Assert.assertEquals("3:g", entityId.toString());
        Assert.assertNull(FFmpeg.Identifier.of(":"));
        Assert.assertNull(FFmpeg.Identifier.of(":a"));
        Assert.assertNotNull(FFmpeg.Identifier.of("a:"));
        Assert.assertNull(FFmpeg.Identifier.of("a:2:"));
        Assert.assertNotNull(FFmpeg.Identifier.of("2:"));
        Assert.assertNotNull(FFmpeg.Identifier.of("2:a:"));
        Assert.assertNull(FFmpeg.Identifier.of("1:1:v"));
        Assert.assertNull(FFmpeg.Identifier.of("1:1:1"));
        Assert.assertNull(FFmpeg.Identifier.of("1:t:3"));
        Assert.assertNull(FFmpeg.Identifier.of("t:3"));
        Assert.assertNull(FFmpeg.Identifier.of("t"));
        Assert.assertNull(FFmpeg.Identifier.of("test"));
        Assert.assertNull(FFmpeg.Identifier.of(""));
        Assert.assertNull(FFmpeg.Identifier.of(null));
        
        //methods
        entityId = Mockito.mock(FFmpeg.Identifier.class, Mockito.CALLS_REAL_METHODS);
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.TYPE);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.CHAPTER);
        TestUtils.setField(entityId, "streamType", FFmpeg.StreamType.SUBTITLE);
        TestUtils.setField(entityId, "index", 20);
        TestUtils.setField(entityId, "sourceIndex", 11);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, entityId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, entityId.getType());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, entityId.getStreamType());
        Assert.assertEquals(20, entityId.getIndex().intValue());
        Assert.assertEquals(11, entityId.getSourceIndex().intValue());
        Assert.assertEquals("s:20", entityId.specifier());
        Assert.assertEquals("c:s:20", entityId.classSpecifier());
        Assert.assertEquals("11:c:s:20", entityId.fullSpecifier());
        Assert.assertEquals("11:c:s:20", entityId.toString());
        
        //classifiers
        entityId = Mockito.mock(FFmpeg.Identifier.class, Mockito.CALLS_REAL_METHODS);
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.SINGULAR);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.GLOBAL);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isGlobalId());
        Assert.assertFalse(entityId.isTypeScoped() || entityId.isAllScoped() || entityId.isStreamId() || entityId.isChapterId());
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.SINGULAR);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.STREAM);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isStreamId());
        Assert.assertFalse(entityId.isTypeScoped() || entityId.isAllScoped() || entityId.isGlobalId() || entityId.isChapterId());
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.SINGULAR);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.CHAPTER);
        Assert.assertTrue(entityId.isSingularScoped() && entityId.isChapterId());
        Assert.assertFalse(entityId.isTypeScoped() || entityId.isAllScoped() || entityId.isGlobalId() || entityId.isStreamId());
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.TYPE);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.GLOBAL);
        Assert.assertTrue(entityId.isTypeScoped() && entityId.isGlobalId());
        Assert.assertFalse(entityId.isSingularScoped() || entityId.isAllScoped() || entityId.isStreamId() || entityId.isChapterId());
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.TYPE);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.STREAM);
        Assert.assertTrue(entityId.isTypeScoped() && entityId.isStreamId());
        Assert.assertFalse(entityId.isSingularScoped() || entityId.isAllScoped() || entityId.isGlobalId() || entityId.isChapterId());
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.TYPE);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.CHAPTER);
        Assert.assertTrue(entityId.isTypeScoped() && entityId.isChapterId());
        Assert.assertFalse(entityId.isSingularScoped() || entityId.isAllScoped() || entityId.isGlobalId() || entityId.isStreamId());
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.ALL);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.GLOBAL);
        Assert.assertTrue(entityId.isAllScoped() && entityId.isGlobalId());
        Assert.assertFalse(entityId.isSingularScoped() || entityId.isTypeScoped() || entityId.isStreamId() || entityId.isChapterId());
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.ALL);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.STREAM);
        Assert.assertTrue(entityId.isAllScoped() && entityId.isStreamId());
        Assert.assertFalse(entityId.isSingularScoped() || entityId.isTypeScoped() || entityId.isGlobalId() || entityId.isChapterId());
        TestUtils.setField(entityId, "scope", FFmpeg.Identifier.IdentifierScope.ALL);
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.CHAPTER);
        Assert.assertTrue(entityId.isAllScoped() && entityId.isChapterId());
        Assert.assertFalse(entityId.isSingularScoped() || entityId.isTypeScoped() || entityId.isGlobalId() || entityId.isStreamId());
        TestUtils.setField(entityId, "scope", null);
        TestUtils.setField(entityId, "type", null);
        Assert.assertFalse(entityId.isSingularScoped() || entityId.isTypeScoped() || entityId.isAllScoped() || entityId.isGlobalId() || entityId.isStreamId() || entityId.isChapterId());
        
        //compare
        entityId = Mockito.mock(FFmpeg.Identifier.class, Mockito.CALLS_REAL_METHODS);
        entityId2 = Mockito.mock(FFmpeg.Identifier.class, Mockito.CALLS_REAL_METHODS);
        Assert.assertEquals(0, entityId.compareTo(entityId2));
        TestUtils.setField(entityId, "sourceIndex", 1);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId2, "sourceIndex", 1);
        Assert.assertEquals(0, entityId.compareTo(entityId2));
        TestUtils.setField(entityId2, "sourceIndex", 2);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.CHAPTER);
        TestUtils.setField(entityId2, "type", FFmpeg.Identifier.IdentifierType.STREAM);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "sourceIndex", 2);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.GLOBAL);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.STREAM);
        Assert.assertEquals(0, entityId.compareTo(entityId2));
        TestUtils.setField(entityId2, "type", FFmpeg.Identifier.IdentifierType.GLOBAL);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "sourceIndex", null);
        TestUtils.setField(entityId2, "sourceIndex", null);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "streamType", FFmpeg.StreamType.AUDIO);
        TestUtils.setField(entityId2, "streamType", FFmpeg.StreamType.SUBTITLE);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId2, "type", FFmpeg.Identifier.IdentifierType.STREAM);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "streamType", FFmpeg.StreamType.DATA);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "streamType", FFmpeg.StreamType.SUBTITLE);
        Assert.assertEquals(0, entityId.compareTo(entityId2));
        TestUtils.setField(entityId, "streamType", FFmpeg.StreamType.VIDEO);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "type", null);
        TestUtils.setField(entityId2, "type", null);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "sourceIndex", 1);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "sourceIndex", null);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "index", 2);
        TestUtils.setField(entityId2, "index", 1);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId2, "streamType", FFmpeg.StreamType.VIDEO);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId2, "index", 3);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId2, "index", 2);
        Assert.assertEquals(0, entityId.compareTo(entityId2));
        TestUtils.setField(entityId2, "index", 0);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "streamType", null);
        TestUtils.setField(entityId2, "streamType", null);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "type", FFmpeg.Identifier.IdentifierType.STREAM);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId, "type", null);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId2, "sourceIndex", 1);
        Assert.assertEquals(-1, entityId.compareTo(entityId2));
        Assert.assertEquals(1, entityId2.compareTo(entityId));
        TestUtils.setField(entityId2, "sourceIndex", null);
        Assert.assertEquals(1, entityId.compareTo(entityId2));
        Assert.assertEquals(-1, entityId2.compareTo(entityId));
        Assert.assertEquals(
                "0:g, 0:s, 0:s:5, 0:s:v, 0:s:v:1, 0:s:v:2, 0:s:a, 0:s:a:3, 0:s:s:0, 0:s:s:1, 0:s:d:2, 0:c, 0:c:3, 0:c:4, " +
                        "1:g, 1:s, 1:s:5, 1:s:v, 1:s:v:1, 1:s:v:2, 1:s:a, 1:s:a:3, 1:s:s:0, 1:s:s:1, 1:s:d:2, 1:c, 1:c:3, 1:c:4, " +
                        "3:g, 3:s, 3:s:5, 3:s:v, 3:s:v:1, 3:s:v:2, 3:s:a, 3:s:a:3, 3:s:s:0, 3:s:s:1, 3:s:d:2, 3:c, 3:c:3, 3:c:4",
                Stream.of(
                                "5", "v", "v:1", "v2", "a", "a:3", "s:0", "s:1", "d:2", "x", "c", "c:3", "c:4", "g",
                                "1:5", "1:v", "1:v:1", "1:v2", "1:a", "1:a:3", "1:s:0", "1:s:1", "1:d:2", "1:x", "1:c", "1:c:3", "1:c:4", "1:g",
                                "3:5", "3:v", "3:v:1", "3:v2", "3:a", "3:a:3", "3:s:0", "3:s:1", "3:d:2", "3:x", "3:c", "3:c:3", "3:c:4", "3:g"
                        ).collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), e -> {
                            Collections.shuffle(e);
                            return e;
                        })).stream().map(FFmpeg.Identifier::of).sorted(Comparator.naturalOrder())
                        .map(FFmpeg.Identifier::toString).collect(Collectors.joining(", "))
        );
        
        //equals
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.ofIndex(3),
                FFmpeg.Identifier.Stream.ofIndex(3));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.ofIndex(3),
                FFmpeg.Identifier.Stream.ofIndex(5));
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.ofIndex(3),
                FFmpeg.Identifier.of("x:3"));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.ofIndex(3),
                FFmpeg.Identifier.of("1:x:3"));
        Assert.assertEquals(
                FFmpeg.Identifier.of("1:x:3"),
                FFmpeg.Identifier.of("1:x:3"));
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0),
                FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0),
                FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO));
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0),
                FFmpeg.Identifier.of("v:0"));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0),
                FFmpeg.Identifier.of("1:v:0"));
        Assert.assertEquals(
                FFmpeg.Identifier.of("3:v:4"),
                FFmpeg.Identifier.of("3:v:4"));
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO),
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO),
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO),
                FFmpeg.Identifier.of("a"));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO),
                FFmpeg.Identifier.of("1:a"));
        Assert.assertEquals(
                FFmpeg.Identifier.of("1:a:1"),
                FFmpeg.Identifier.of("1:a:1"));
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.all(),
                FFmpeg.Identifier.Stream.all());
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.all(),
                FFmpeg.Identifier.of("x"));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.all(),
                FFmpeg.Identifier.of("2:x"));
        Assert.assertEquals(
                FFmpeg.Identifier.of("2:x"),
                FFmpeg.Identifier.of("2:x"));
        Assert.assertEquals(
                FFmpeg.Identifier.Chapter.ofIndex(3),
                FFmpeg.Identifier.Chapter.ofIndex(3));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Chapter.ofIndex(3),
                FFmpeg.Identifier.Chapter.ofIndex(5));
        Assert.assertEquals(
                FFmpeg.Identifier.Chapter.ofIndex(3),
                FFmpeg.Identifier.of("c:3"));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Chapter.ofIndex(3),
                FFmpeg.Identifier.of("1:c:3"));
        Assert.assertEquals(
                FFmpeg.Identifier.of("1:c:3"),
                FFmpeg.Identifier.of("1:c:3"));
        Assert.assertEquals(
                FFmpeg.Identifier.Chapter.all(),
                FFmpeg.Identifier.Chapter.all());
        Assert.assertEquals(
                FFmpeg.Identifier.Chapter.all(),
                FFmpeg.Identifier.of("c"));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Chapter.all(),
                FFmpeg.Identifier.of("2:c"));
        Assert.assertEquals(
                FFmpeg.Identifier.of("2:c"),
                FFmpeg.Identifier.of("2:c"));
        Assert.assertEquals(
                FFmpeg.Identifier.Global.get(),
                FFmpeg.Identifier.Global.get());
        Assert.assertEquals(
                FFmpeg.Identifier.Global.get(),
                FFmpeg.Identifier.of("g"));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Global.get(),
                FFmpeg.Identifier.of("2:g"));
        Assert.assertEquals(
                FFmpeg.Identifier.of("2:g"),
                FFmpeg.Identifier.of("2:g"));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.ofIndex(3),
                FFmpeg.Identifier.Chapter.ofIndex(3));
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.all(),
                FFmpeg.Identifier.Chapter.all());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.all(),
                FFmpeg.Identifier.Global.get());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Chapter.all(),
                FFmpeg.Identifier.Global.get());
        
        //hash code
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.ofIndex(3).hashCode(),
                FFmpeg.Identifier.Stream.ofIndex(3).hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.ofIndex(3).hashCode(),
                FFmpeg.Identifier.Stream.ofIndex(5).hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.ofIndex(3).hashCode(),
                FFmpeg.Identifier.of("x:3").hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.ofIndex(3).hashCode(),
                FFmpeg.Identifier.of("1:x:3").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.of("1:x:3").hashCode(),
                FFmpeg.Identifier.of("1:x:3").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0).hashCode(),
                FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.VIDEO).hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0).hashCode(),
                FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO).hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0).hashCode(),
                FFmpeg.Identifier.of("v:0").hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 0).hashCode(),
                FFmpeg.Identifier.of("1:v:0").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.of("3:v:4").hashCode(),
                FFmpeg.Identifier.of("3:v:4").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO).hashCode(),
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO).hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO).hashCode(),
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE).hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO).hashCode(),
                FFmpeg.Identifier.of("a").hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.AUDIO).hashCode(),
                FFmpeg.Identifier.of("1:a").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.of("1:a:1").hashCode(),
                FFmpeg.Identifier.of("1:a:1").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.all().hashCode(),
                FFmpeg.Identifier.Stream.all().hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Stream.all().hashCode(),
                FFmpeg.Identifier.of("x").hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.all().hashCode(),
                FFmpeg.Identifier.of("2:x").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.of("2:x").hashCode(),
                FFmpeg.Identifier.of("2:x").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Chapter.ofIndex(3).hashCode(),
                FFmpeg.Identifier.Chapter.ofIndex(3).hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Chapter.ofIndex(3).hashCode(),
                FFmpeg.Identifier.Chapter.ofIndex(5).hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Chapter.ofIndex(3).hashCode(),
                FFmpeg.Identifier.of("c:3").hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Chapter.ofIndex(3).hashCode(),
                FFmpeg.Identifier.of("1:c:3").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.of("1:c:3").hashCode(),
                FFmpeg.Identifier.of("1:c:3").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Chapter.all().hashCode(),
                FFmpeg.Identifier.Chapter.all().hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Chapter.all().hashCode(),
                FFmpeg.Identifier.of("c").hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Chapter.all().hashCode(),
                FFmpeg.Identifier.of("2:c").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.of("2:c").hashCode(),
                FFmpeg.Identifier.of("2:c").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Global.get().hashCode(),
                FFmpeg.Identifier.Global.get().hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.Global.get().hashCode(),
                FFmpeg.Identifier.of("g").hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Global.get().hashCode(),
                FFmpeg.Identifier.of("2:g").hashCode());
        Assert.assertEquals(
                FFmpeg.Identifier.of("2:g").hashCode(),
                FFmpeg.Identifier.of("2:g").hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.ofIndex(3).hashCode(),
                FFmpeg.Identifier.Chapter.ofIndex(3).hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.all().hashCode(),
                FFmpeg.Identifier.Chapter.all().hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Stream.all().hashCode(),
                FFmpeg.Identifier.Global.get().hashCode());
        Assert.assertNotEquals(
                FFmpeg.Identifier.Chapter.all().hashCode(),
                FFmpeg.Identifier.Global.get().hashCode());
        
        //decompose
        PowerMockito.mockStatic(FFmpeg.class, Mockito.CALLS_REAL_METHODS);
        FFmpeg.Identifier.decompose(FFmpeg.Identifier.of("v"), testVideo);
        PowerMockito.verifyStatic(FFmpeg.class, VerificationModeFactory.times(1));
        FFmpeg.getMediaInfo(ArgumentMatchers.eq(testVideo));
        FFmpeg.Identifier.decompose(FFmpeg.Identifier.of("a"), mediaInfo);
        PowerMockito.verifyStatic(FFmpeg.class, VerificationModeFactory.times(1));
        FFmpeg.getMediaInfo(ArgumentMatchers.eq(testVideo));
        List.of(
                "5 = 0:s:5[0]",
                "v:1 = 0:s:1[0]",
                "a:1 = 0:s:4[0]",
                "s:4 = 0:s:13[0]",
                "c:0 = 0:c:0[0]",
                "g = 0:g[0]",
                "15 = ",
                "v:5 = ",
                "c:5 = ",
                "0:5 = 0:s:5[0]",
                "0:v:1 = 0:s:1[0]",
                "0:a:1 = 0:s:4[0]",
                "0:s:4 = 0:s:13[0]",
                "0:c:0 = 0:c:0[0]",
                "0:g = 0:g[0]",
                "0:15 = ",
                "0:v:5 = ",
                "0:c:5 = ",
                "1:5 = 0:s:5[0]",
                "1:v:1 = 0:s:1[0]",
                "2:a:1 = 0:s:4[0]",
                "3:s:4 = 0:s:13[0]",
                "3:c:0 = 0:c:0[0]",
                "1:g = 0:g[0]",
                "2:15 = ",
                "1:v:5 = ",
                "3:c:5 = ",
                "5, 11 = 0:s:5[0], 0:s:11[1]",
                "5, 20 = 0:s:5[0]",
                "v:5, a:1 = 0:s:4[1]",
                "s:2, c:2 = 0:s:11[0], 0:c:2[1]",
                "s:5, c:5 = 0:s:14[0]",
                "0:s:2, 0:c:2 = 0:s:11[0], 0:c:2[1]",
                "0:s:5, c:5 = 0:s:14[0]",
                "1:s:2, 2:c:2 = 0:s:11[0], 0:c:2[1]",
                "3:s:5, c:5 = 0:s:14[0]",
                "5, 5, 5 = 0:s:5[2]",
                "v:1, 1 = 0:s:1[1]",
                "c:2, c:2, 0:c:2 = 0:c:2[2]",
                "g, g, g = 0:g[2]",
                "0:5, 0:5, 0:5 = 0:s:5[2]",
                "0:v:1, 0:1 = 0:s:1[1]",
                "0:c:2, 0:c:2, 0:c:2 = 0:c:2[2]",
                "0:g, 0:g, 0:g = 0:g[2]",
                "0:5, 1:5, 2:5 = 0:s:5[2]",
                "2:v:1, 1 = 0:s:1[1]",
                "c:2, 1:c:2, 1:c:2 = 0:c:2[2]",
                "g, 3:g, 1:g = 0:g[2]",
                "v, v:1, v:2 = 0:s:0[0], 0:s:1[1], 0:s:2[2]",
                "v:1, v:2, v = 0:s:0[2], 0:s:1[2], 0:s:2[2]",
                "v:1, v:2, v, v:0 = 0:s:0[3], 0:s:1[2], 0:s:2[2]",
                "a, a:3, a:4 = 0:s:3[0], 0:s:4[0], 0:s:5[0], 0:s:6[1], 0:s:7[2], 0:s:8[0]",
                "a:3, a:4, a = 0:s:3[2], 0:s:4[2], 0:s:5[2], 0:s:6[2], 0:s:7[2], 0:s:8[2]",
                "a:3, a:4, a, a:0 = 0:s:3[3], 0:s:4[2], 0:s:5[2], 0:s:6[2], 0:s:7[2], 0:s:8[2]",
                "s, s:3, s:5 = 0:s:9[0], 0:s:10[0], 0:s:11[0], 0:s:12[1], 0:s:13[0], 0:s:14[2]",
                "s:3, s:5, s = 0:s:9[2], 0:s:10[2], 0:s:11[2], 0:s:12[2], 0:s:13[2], 0:s:14[2]",
                "s:3, s:5, s, s:0 = 0:s:9[3], 0:s:10[2], 0:s:11[2], 0:s:12[2], 0:s:13[2], 0:s:14[2]",
                "c, c:1 = 0:c:0[0], 0:c:1[1], 0:c:2[0]",
                "c:1, c = 0:c:0[1], 0:c:1[1], 0:c:2[1]",
                "c:1, c, c:0 = 0:c:0[2], 0:c:1[1], 0:c:2[1]",
                "0:v, 0:v:1, 0:v:2 = 0:s:0[0], 0:s:1[1], 0:s:2[2]",
                "0:s:3, 0:s:5, 0:s, 0:s:0 = 0:s:9[3], 0:s:10[2], 0:s:11[2], 0:s:12[2], 0:s:13[2], 0:s:14[2]",
                "v, v:1, 1:v:2 = 0:s:0[0], 0:s:1[1], 0:s:2[2]",
                "2:s:3, 2:s:5, s, 1:s:0 = 0:s:9[3], 0:s:10[2], 0:s:11[2], 0:s:12[2], 0:s:13[2], 0:s:14[2]",
                "a:2, v = 0:s:0[1], 0:s:1[1], 0:s:2[1], 0:s:5[0]",
                "a:2, v, v:1, c, s:3, a:0 = 0:s:0[1], 0:s:1[2], 0:s:2[1], 0:s:3[5], 0:s:5[0], 0:s:12[4], 0:c:0[3], 0:c:1[3], 0:c:2[3]",
                "x, s:3, s:4, s:5, v:0, a = 0:s:0[4], 0:s:1[0], 0:s:2[0], 0:s:3[5], 0:s:4[5], 0:s:5[5], 0:s:6[5], 0:s:7[5], 0:s:8[5], 0:s:9[0], 0:s:10[0], 0:s:11[0], 0:s:12[1], 0:s:13[2], 0:s:14[3]",
                "0:x, 0:s:3, 0:s:4, 0:s:5, 0:v:0, 0:a = 0:s:0[4], 0:s:1[0], 0:s:2[0], 0:s:3[5], 0:s:4[5], 0:s:5[5], 0:s:6[5], 0:s:7[5], 0:s:8[5], 0:s:9[0], 0:s:10[0], 0:s:11[0], 0:s:12[1], 0:s:13[2], 0:s:14[3]",
                "1:x, s:3, s:4, s:5, v:0, a = 0:s:0[4], 0:s:1[0], 0:s:2[0], 0:s:3[5], 0:s:4[5], 0:s:5[5], 0:s:6[5], 0:s:7[5], 0:s:8[5], 0:s:9[0], 0:s:10[0], 0:s:11[0], 0:s:12[1], 0:s:13[2], 0:s:14[3]",
                "x, c, g = 0:g[2], 0:s:0[0], 0:s:1[0], 0:s:2[0], 0:s:3[0], 0:s:4[0], 0:s:5[0], 0:s:6[0], 0:s:7[0], 0:s:8[0], 0:s:9[0], 0:s:10[0], 0:s:11[0], 0:s:12[0], 0:s:13[0], 0:s:14[0], 0:c:0[1], 0:c:1[1], 0:c:2[1]"
        ).forEach(e -> {
            final String[] parts = StringUtility.removeWhiteSpace(e).split("=", -1);
            final List<FFmpeg.Identifier<?>> identifiers = Arrays.stream(parts[0].split(",")).map(FFmpeg.Identifier::of).collect(Collectors.toList());
            final List<Object> mappedValues = identifiers.stream().map(v -> new Object()).collect(Collectors.toList());
            Assert.assertEquals(parts[1].replaceAll("\\[[^]]*]", ""),
                    ((identifiers.size() == 1) ? FFmpeg.Identifier.decompose(identifiers.get(0), testVideo) : FFmpeg.Identifier.decompose(identifiers, testVideo))
                            .stream().map(FFmpeg.Identifier::toString).collect(Collectors.joining(",")));
            Assert.assertEquals(parts[1].replaceAll("\\[[^]]*]", ""),
                    ((identifiers.size() == 1) ? FFmpeg.Identifier.decompose(identifiers.get(0), mediaInfo) : FFmpeg.Identifier.decompose(identifiers, mediaInfo))
                            .stream().map(FFmpeg.Identifier::toString).collect(Collectors.joining(",")));
            Assert.assertEquals(parts[1],
                    FFmpeg.Identifier.decompose(identifiers, mappedValues, testVideo).entrySet()
                            .stream().map(d -> d.getKey().toString() + '[' + mappedValues.indexOf(d.getValue()) + ']').collect(Collectors.joining(",")));
            Assert.assertEquals(parts[1],
                    FFmpeg.Identifier.decompose(identifiers, mappedValues, mediaInfo).entrySet()
                            .stream().map(d -> d.getKey().toString() + '[' + mappedValues.indexOf(d.getValue()) + ']').collect(Collectors.joining(",")));
        });
        
        //components
        testIdentifierGlobal();
        testIdentifierStream();
        testIdentifierChapter();
    }
    
    /**
     * Helper method for JUnit test of Identifier for Global.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Identifier.Global
     */
    private void testIdentifierGlobal() throws Exception {
        FFmpeg.Identifier.Global globalId;
        Assert.assertEquals("Identifier", FFmpeg.Identifier.Global.class.getSuperclass().getSimpleName());
        
        //global
        globalId = TestUtils.invokeConstructor(FFmpeg.Identifier.Global.class);
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(0, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("0:g", globalId.fullSpecifier());
        Assert.assertEquals("0:g", globalId.toString());
        
        //static constructors
        globalId = FFmpeg.Identifier.Global.get();
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(0, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("0:g", globalId.fullSpecifier());
        Assert.assertEquals("0:g", globalId.toString());
        
        //static string constructors
        globalId = FFmpeg.Identifier.Global.of("g");
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(0, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("0:g", globalId.fullSpecifier());
        Assert.assertEquals("0:g", globalId.toString());
        globalId = FFmpeg.Identifier.Global.of("g:6");
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(0, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("0:g", globalId.fullSpecifier());
        Assert.assertEquals("0:g", globalId.toString());
        globalId = FFmpeg.Identifier.Global.of("g:v");
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(0, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("0:g", globalId.fullSpecifier());
        Assert.assertEquals("0:g", globalId.toString());
        globalId = FFmpeg.Identifier.Global.of("g:s:3");
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(0, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("0:g", globalId.fullSpecifier());
        Assert.assertEquals("0:g", globalId.toString());
        globalId = FFmpeg.Identifier.Global.of("2:g");
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(2, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("2:g", globalId.fullSpecifier());
        Assert.assertEquals("2:g", globalId.toString());
        globalId = FFmpeg.Identifier.Global.of("1:g:6");
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(1, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("1:g", globalId.fullSpecifier());
        Assert.assertEquals("1:g", globalId.toString());
        globalId = FFmpeg.Identifier.Global.of("1:g:v");
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(1, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("1:g", globalId.fullSpecifier());
        Assert.assertEquals("1:g", globalId.toString());
        globalId = FFmpeg.Identifier.Global.of("3:g:s:3");
        Assert.assertNotNull(globalId);
        Assert.assertTrue(globalId instanceof FFmpeg.Identifier.Global);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, globalId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.GLOBAL, globalId.getType());
        Assert.assertNull(globalId.getStreamType());
        Assert.assertNull(globalId.getIndex());
        Assert.assertEquals(3, globalId.getSourceIndex().intValue());
        Assert.assertEquals("", globalId.specifier());
        Assert.assertEquals("g", globalId.classSpecifier());
        Assert.assertEquals("3:g", globalId.fullSpecifier());
        Assert.assertEquals("3:g", globalId.toString());
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Global.of("x"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Global.of("v:3"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Global.of("x:v:3"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Global.of("c"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Global.of("c:3"));
        Assert.assertNull(FFmpeg.Identifier.Global.of(":"));
        Assert.assertNull(FFmpeg.Identifier.Global.of(":a"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Global.of("a:"));
        Assert.assertNull(FFmpeg.Identifier.Global.of("a:2:"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Global.of("2:"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Global.of("2:a:"));
        Assert.assertNull(FFmpeg.Identifier.Global.of("1:1:v"));
        Assert.assertNull(FFmpeg.Identifier.Global.of("1:1:1"));
        Assert.assertNull(FFmpeg.Identifier.Global.of("1:t:3"));
        Assert.assertNull(FFmpeg.Identifier.Global.of("t:3"));
        Assert.assertNull(FFmpeg.Identifier.Global.of("t"));
        Assert.assertNull(FFmpeg.Identifier.Global.of("test"));
        Assert.assertNull(FFmpeg.Identifier.Global.of(""));
        Assert.assertNull(FFmpeg.Identifier.Global.of(null));
    }
    
    /**
     * Helper method for JUnit test of Identifier for Stream.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Identifier.Stream
     * @see FFmpeg.Identifier.Stream.StreamSpecifier
     * @see FFmpeg.Identifier.Stream.StreamTypeSpecifier
     * @see FFmpeg.Identifier.Stream.AllStreams
     */
    private void testIdentifierStream() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class, Mockito.CALLS_REAL_METHODS);
        FFmpeg.Identifier.Stream<?> streamId;
        Assert.assertEquals("Identifier", FFmpeg.Identifier.Stream.class.getSuperclass().getSimpleName());
        Assert.assertEquals("Stream", FFmpeg.Identifier.Stream.StreamSpecifier.class.getSuperclass().getSimpleName());
        Assert.assertEquals("Stream", FFmpeg.Identifier.Stream.StreamTypeSpecifier.class.getSuperclass().getSimpleName());
        Assert.assertEquals("Stream", FFmpeg.Identifier.Stream.AllStreams.class.getSuperclass().getSimpleName());
        
        //stream specifier
        streamId = TestUtils.invokeConstructor(FFmpeg.Identifier.Stream.StreamSpecifier.class,
                FFmpeg.StreamType.VIDEO, 3);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, streamId.getStreamType());
        Assert.assertEquals(3, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("v:3", streamId.specifier());
        Assert.assertEquals("s:v:3", streamId.classSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.fullSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.toString());
        streamId = TestUtils.invokeConstructor(FFmpeg.Identifier.Stream.StreamSpecifier.class,
                null, 3);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(3, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("3", streamId.specifier());
        Assert.assertEquals("s:3", streamId.classSpecifier());
        Assert.assertEquals("0:s:3", streamId.fullSpecifier());
        Assert.assertEquals("0:s:3", streamId.toString());
        streamId = TestUtils.invokeConstructor(FFmpeg.Identifier.Stream.StreamSpecifier.class,
                FFmpeg.StreamType.AUDIO);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, streamId.getStreamType());
        Assert.assertEquals(0, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("a:0", streamId.specifier());
        Assert.assertEquals("s:a:0", streamId.classSpecifier());
        Assert.assertEquals("0:s:a:0", streamId.fullSpecifier());
        Assert.assertEquals("0:s:a:0", streamId.toString());
        streamId = TestUtils.invokeConstructor(FFmpeg.Identifier.Stream.StreamSpecifier.class,
                (FFmpeg.StreamType) null);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(0, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("0", streamId.specifier());
        Assert.assertEquals("s:0", streamId.classSpecifier());
        Assert.assertEquals("0:s:0", streamId.fullSpecifier());
        Assert.assertEquals("0:s:0", streamId.toString());
        streamId = TestUtils.invokeConstructor(FFmpeg.Identifier.Stream.StreamSpecifier.class,
                5);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(5, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("5", streamId.specifier());
        Assert.assertEquals("s:5", streamId.classSpecifier());
        Assert.assertEquals("0:s:5", streamId.fullSpecifier());
        Assert.assertEquals("0:s:5", streamId.toString());
        
        //stream type specifier
        streamId = TestUtils.invokeConstructor(FFmpeg.Identifier.Stream.StreamTypeSpecifier.class,
                FFmpeg.StreamType.SUBTITLE);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamTypeSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("s", streamId.specifier());
        Assert.assertEquals("s:s", streamId.classSpecifier());
        Assert.assertEquals("0:s:s", streamId.fullSpecifier());
        Assert.assertEquals("0:s:s", streamId.toString());
        PowerMockito.doNothing().when(TestUtils.AssertWrapper.class, "fail", ArgumentMatchers.anyString());
        TestUtils.assertException(MethodInvocationException.class, "java.lang.NullPointerException: Invalid Stream Type for Stream Type Specifier: null", () ->
                TestUtils.invokeConstructor(FFmpeg.Identifier.Stream.StreamTypeSpecifier.class, (FFmpeg.StreamType) null));
        PowerMockito.doCallRealMethod().when(TestUtils.AssertWrapper.class, "fail", ArgumentMatchers.anyString());
        
        //all streams
        streamId = TestUtils.invokeConstructor(FFmpeg.Identifier.Stream.AllStreams.class);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.AllStreams);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("", streamId.specifier());
        Assert.assertEquals("s", streamId.classSpecifier());
        Assert.assertEquals("0:s", streamId.fullSpecifier());
        Assert.assertEquals("0:s", streamId.toString());
        
        //static constructors
        streamId = FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.VIDEO, 3);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, streamId.getStreamType());
        Assert.assertEquals(3, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("v:3", streamId.specifier());
        Assert.assertEquals("s:v:3", streamId.classSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.fullSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of(null, 3);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(3, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("3", streamId.specifier());
        Assert.assertEquals("s:3", streamId.classSpecifier());
        Assert.assertEquals("0:s:3", streamId.fullSpecifier());
        Assert.assertEquals("0:s:3", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.ofFirst(FFmpeg.StreamType.AUDIO);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, streamId.getStreamType());
        Assert.assertEquals(0, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("a:0", streamId.specifier());
        Assert.assertEquals("s:a:0", streamId.classSpecifier());
        Assert.assertEquals("0:s:a:0", streamId.fullSpecifier());
        Assert.assertEquals("0:s:a:0", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.ofFirst(null);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(0, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("0", streamId.specifier());
        Assert.assertEquals("s:0", streamId.classSpecifier());
        Assert.assertEquals("0:s:0", streamId.fullSpecifier());
        Assert.assertEquals("0:s:0", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.ofIndex(5);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(5, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("5", streamId.specifier());
        Assert.assertEquals("s:5", streamId.classSpecifier());
        Assert.assertEquals("0:s:5", streamId.fullSpecifier());
        Assert.assertEquals("0:s:5", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of(FFmpeg.StreamType.SUBTITLE);
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.StreamTypeSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("s", streamId.specifier());
        Assert.assertEquals("s:s", streamId.classSpecifier());
        Assert.assertEquals("0:s:s", streamId.fullSpecifier());
        Assert.assertEquals("0:s:s", streamId.toString());
        TestUtils.assertException(NullPointerException.class, "Invalid Stream Type for Stream Type Specifier: null", () ->
                FFmpeg.Identifier.Stream.of((FFmpeg.StreamType) null));
        streamId = FFmpeg.Identifier.Stream.all();
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream.AllStreams);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("", streamId.specifier());
        Assert.assertEquals("s", streamId.classSpecifier());
        Assert.assertEquals("0:s", streamId.fullSpecifier());
        Assert.assertEquals("0:s", streamId.toString());
        
        //static string constructors
        streamId = FFmpeg.Identifier.Stream.of("v:3");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, streamId.getStreamType());
        Assert.assertEquals(3, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("v:3", streamId.specifier());
        Assert.assertEquals("s:v:3", streamId.classSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.fullSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("a:0");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, streamId.getStreamType());
        Assert.assertEquals(0, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("a:0", streamId.specifier());
        Assert.assertEquals("s:a:0", streamId.classSpecifier());
        Assert.assertEquals("0:s:a:0", streamId.fullSpecifier());
        Assert.assertEquals("0:s:a:0", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("5");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(5, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("5", streamId.specifier());
        Assert.assertEquals("s:5", streamId.classSpecifier());
        Assert.assertEquals("0:s:5", streamId.fullSpecifier());
        Assert.assertEquals("0:s:5", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("s");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("s", streamId.specifier());
        Assert.assertEquals("s:s", streamId.classSpecifier());
        Assert.assertEquals("0:s:s", streamId.fullSpecifier());
        Assert.assertEquals("0:s:s", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("x:v:3");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, streamId.getStreamType());
        Assert.assertEquals(3, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("v:3", streamId.specifier());
        Assert.assertEquals("s:v:3", streamId.classSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.fullSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("x:5");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(5, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("5", streamId.specifier());
        Assert.assertEquals("s:5", streamId.classSpecifier());
        Assert.assertEquals("0:s:5", streamId.fullSpecifier());
        Assert.assertEquals("0:s:5", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("x:s");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("s", streamId.specifier());
        Assert.assertEquals("s:s", streamId.classSpecifier());
        Assert.assertEquals("0:s:s", streamId.fullSpecifier());
        Assert.assertEquals("0:s:s", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("x");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("", streamId.specifier());
        Assert.assertEquals("s", streamId.classSpecifier());
        Assert.assertEquals("0:s", streamId.fullSpecifier());
        Assert.assertEquals("0:s", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("s:v:3");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, streamId.getStreamType());
        Assert.assertEquals(3, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("v:3", streamId.specifier());
        Assert.assertEquals("s:v:3", streamId.classSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.fullSpecifier());
        Assert.assertEquals("0:s:v:3", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("s:5");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, streamId.getStreamType());
        Assert.assertEquals(5, streamId.getIndex().intValue());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("s:5", streamId.specifier());
        Assert.assertEquals("s:s:5", streamId.classSpecifier());
        Assert.assertEquals("0:s:s:5", streamId.fullSpecifier());
        Assert.assertEquals("0:s:s:5", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("s:a");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(0, streamId.getSourceIndex().intValue());
        Assert.assertEquals("a", streamId.specifier());
        Assert.assertEquals("s:a", streamId.classSpecifier());
        Assert.assertEquals("0:s:a", streamId.fullSpecifier());
        Assert.assertEquals("0:s:a", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("2:15");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(15, streamId.getIndex().intValue());
        Assert.assertEquals(2, streamId.getSourceIndex().intValue());
        Assert.assertEquals("15", streamId.specifier());
        Assert.assertEquals("s:15", streamId.classSpecifier());
        Assert.assertEquals("2:s:15", streamId.fullSpecifier());
        Assert.assertEquals("2:s:15", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("1:v");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, streamId.getStreamType());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(1, streamId.getSourceIndex().intValue());
        Assert.assertEquals("v", streamId.specifier());
        Assert.assertEquals("s:v", streamId.classSpecifier());
        Assert.assertEquals("1:s:v", streamId.fullSpecifier());
        Assert.assertEquals("1:s:v", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("3:a:6");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, streamId.getStreamType());
        Assert.assertEquals(6, streamId.getIndex().intValue());
        Assert.assertEquals(3, streamId.getSourceIndex().intValue());
        Assert.assertEquals("a:6", streamId.specifier());
        Assert.assertEquals("s:a:6", streamId.classSpecifier());
        Assert.assertEquals("3:s:a:6", streamId.fullSpecifier());
        Assert.assertEquals("3:s:a:6", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("2:x");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(2, streamId.getSourceIndex().intValue());
        Assert.assertEquals("", streamId.specifier());
        Assert.assertEquals("s", streamId.classSpecifier());
        Assert.assertEquals("2:s", streamId.fullSpecifier());
        Assert.assertEquals("2:s", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("1:x:6");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertNull(streamId.getStreamType());
        Assert.assertEquals(6, streamId.getIndex().intValue());
        Assert.assertEquals(1, streamId.getSourceIndex().intValue());
        Assert.assertEquals("6", streamId.specifier());
        Assert.assertEquals("s:6", streamId.classSpecifier());
        Assert.assertEquals("1:s:6", streamId.fullSpecifier());
        Assert.assertEquals("1:s:6", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("1:x:v");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(1, streamId.getSourceIndex().intValue());
        Assert.assertEquals("v", streamId.specifier());
        Assert.assertEquals("s:v", streamId.classSpecifier());
        Assert.assertEquals("1:s:v", streamId.fullSpecifier());
        Assert.assertEquals("1:s:v", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("3:x:a:6");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, streamId.getStreamType());
        Assert.assertEquals(6, streamId.getIndex().intValue());
        Assert.assertEquals(3, streamId.getSourceIndex().intValue());
        Assert.assertEquals("a:6", streamId.specifier());
        Assert.assertEquals("s:a:6", streamId.classSpecifier());
        Assert.assertEquals("3:s:a:6", streamId.fullSpecifier());
        Assert.assertEquals("3:s:a:6", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("1:s:6");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, streamId.getStreamType());
        Assert.assertEquals(6, streamId.getIndex().intValue());
        Assert.assertEquals(1, streamId.getSourceIndex().intValue());
        Assert.assertEquals("s:6", streamId.specifier());
        Assert.assertEquals("s:s:6", streamId.classSpecifier());
        Assert.assertEquals("1:s:s:6", streamId.fullSpecifier());
        Assert.assertEquals("1:s:s:6", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("1:s:v");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.TYPE, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, streamId.getStreamType());
        Assert.assertNull(streamId.getIndex());
        Assert.assertEquals(1, streamId.getSourceIndex().intValue());
        Assert.assertEquals("v", streamId.specifier());
        Assert.assertEquals("s:v", streamId.classSpecifier());
        Assert.assertEquals("1:s:v", streamId.fullSpecifier());
        Assert.assertEquals("1:s:v", streamId.toString());
        streamId = FFmpeg.Identifier.Stream.of("3:s:a:6");
        Assert.assertNotNull(streamId);
        Assert.assertTrue(streamId instanceof FFmpeg.Identifier.Stream);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, streamId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.STREAM, streamId.getType());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, streamId.getStreamType());
        Assert.assertEquals(6, streamId.getIndex().intValue());
        Assert.assertEquals(3, streamId.getSourceIndex().intValue());
        Assert.assertEquals("a:6", streamId.specifier());
        Assert.assertEquals("s:a:6", streamId.classSpecifier());
        Assert.assertEquals("3:s:a:6", streamId.fullSpecifier());
        Assert.assertEquals("3:s:a:6", streamId.toString());
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Stream.of("g"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Stream.of("c"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Stream.of("c:3"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of(":"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of(":a"));
        Assert.assertNotNull(FFmpeg.Identifier.Stream.of("a:"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of("a:2:"));
        Assert.assertNotNull(FFmpeg.Identifier.Stream.of("2:"));
        Assert.assertNotNull(FFmpeg.Identifier.Stream.of("2:a:"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of("1:1:v"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of("1:1:1"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of("1:t:3"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of("t:3"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of("t"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of("test"));
        Assert.assertNull(FFmpeg.Identifier.Stream.of(""));
        Assert.assertNull(FFmpeg.Identifier.Stream.of((String) null));
    }
    
    /**
     * Helper method for JUnit test of Identifier for Chapter.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Identifier.Chapter
     * @see FFmpeg.Identifier.Chapter.ChapterSpecifier
     * @see FFmpeg.Identifier.Chapter.AllChapters
     */
    private void testIdentifierChapter() throws Exception {
        FFmpeg.Identifier.Chapter<?> chapterId;
        Assert.assertEquals("Identifier", FFmpeg.Identifier.Chapter.class.getSuperclass().getSimpleName());
        Assert.assertEquals("Chapter", FFmpeg.Identifier.Chapter.ChapterSpecifier.class.getSuperclass().getSimpleName());
        Assert.assertEquals("Chapter", FFmpeg.Identifier.Chapter.AllChapters.class.getSuperclass().getSimpleName());
        
        //chapter specifier
        chapterId = TestUtils.invokeConstructor(FFmpeg.Identifier.Chapter.ChapterSpecifier.class,
                5);
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter.ChapterSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertEquals(5, chapterId.getIndex().intValue());
        Assert.assertEquals(0, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("5", chapterId.specifier());
        Assert.assertEquals("c:5", chapterId.classSpecifier());
        Assert.assertEquals("0:c:5", chapterId.fullSpecifier());
        Assert.assertEquals("0:c:5", chapterId.toString());
        
        //all chapters
        chapterId = TestUtils.invokeConstructor(FFmpeg.Identifier.Chapter.AllChapters.class);
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter.AllChapters);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertNull(chapterId.getIndex());
        Assert.assertEquals(0, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("", chapterId.specifier());
        Assert.assertEquals("c", chapterId.classSpecifier());
        Assert.assertEquals("0:c", chapterId.fullSpecifier());
        Assert.assertEquals("0:c", chapterId.toString());
        
        //static constructors
        chapterId = FFmpeg.Identifier.Chapter.ofIndex(5);
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter.ChapterSpecifier);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertEquals(5, chapterId.getIndex().intValue());
        Assert.assertEquals(0, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("5", chapterId.specifier());
        Assert.assertEquals("c:5", chapterId.classSpecifier());
        Assert.assertEquals("0:c:5", chapterId.fullSpecifier());
        Assert.assertEquals("0:c:5", chapterId.toString());
        chapterId = FFmpeg.Identifier.Chapter.all();
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter.AllChapters);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertNull(chapterId.getIndex());
        Assert.assertEquals(0, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("", chapterId.specifier());
        Assert.assertEquals("c", chapterId.classSpecifier());
        Assert.assertEquals("0:c", chapterId.fullSpecifier());
        Assert.assertEquals("0:c", chapterId.toString());
        
        //static string constructors
        chapterId = FFmpeg.Identifier.Chapter.of("c:5");
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertEquals(5, chapterId.getIndex().intValue());
        Assert.assertEquals(0, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("5", chapterId.specifier());
        Assert.assertEquals("c:5", chapterId.classSpecifier());
        Assert.assertEquals("0:c:5", chapterId.fullSpecifier());
        Assert.assertEquals("0:c:5", chapterId.toString());
        chapterId = FFmpeg.Identifier.Chapter.of("c");
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertNull(chapterId.getIndex());
        Assert.assertEquals(0, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("", chapterId.specifier());
        Assert.assertEquals("c", chapterId.classSpecifier());
        Assert.assertEquals("0:c", chapterId.fullSpecifier());
        Assert.assertEquals("0:c", chapterId.toString());
        chapterId = FFmpeg.Identifier.Chapter.of("c:v");
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertNull(chapterId.getIndex());
        Assert.assertEquals(0, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("", chapterId.specifier());
        Assert.assertEquals("c", chapterId.classSpecifier());
        Assert.assertEquals("0:c", chapterId.fullSpecifier());
        Assert.assertEquals("0:c", chapterId.toString());
        chapterId = FFmpeg.Identifier.Chapter.of("c:v:3");
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertEquals(3, chapterId.getIndex().intValue());
        Assert.assertEquals(0, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("3", chapterId.specifier());
        Assert.assertEquals("c:3", chapterId.classSpecifier());
        Assert.assertEquals("0:c:3", chapterId.fullSpecifier());
        Assert.assertEquals("0:c:3", chapterId.toString());
        chapterId = FFmpeg.Identifier.Chapter.of("2:c:5");
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertEquals(5, chapterId.getIndex().intValue());
        Assert.assertEquals(2, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("5", chapterId.specifier());
        Assert.assertEquals("c:5", chapterId.classSpecifier());
        Assert.assertEquals("2:c:5", chapterId.fullSpecifier());
        Assert.assertEquals("2:c:5", chapterId.toString());
        chapterId = FFmpeg.Identifier.Chapter.of("1:c");
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertNull(chapterId.getIndex());
        Assert.assertEquals(1, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("", chapterId.specifier());
        Assert.assertEquals("c", chapterId.classSpecifier());
        Assert.assertEquals("1:c", chapterId.fullSpecifier());
        Assert.assertEquals("1:c", chapterId.toString());
        chapterId = FFmpeg.Identifier.Chapter.of("1:c:v");
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.ALL, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertNull(chapterId.getIndex());
        Assert.assertEquals(1, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("", chapterId.specifier());
        Assert.assertEquals("c", chapterId.classSpecifier());
        Assert.assertEquals("1:c", chapterId.fullSpecifier());
        Assert.assertEquals("1:c", chapterId.toString());
        chapterId = FFmpeg.Identifier.Chapter.of("3:c:v:3");
        Assert.assertNotNull(chapterId);
        Assert.assertTrue(chapterId instanceof FFmpeg.Identifier.Chapter);
        Assert.assertEquals(FFmpeg.Identifier.IdentifierScope.SINGULAR, chapterId.getScope());
        Assert.assertEquals(FFmpeg.Identifier.IdentifierType.CHAPTER, chapterId.getType());
        Assert.assertNull(chapterId.getStreamType());
        Assert.assertEquals(3, chapterId.getIndex().intValue());
        Assert.assertEquals(3, chapterId.getSourceIndex().intValue());
        Assert.assertEquals("3", chapterId.specifier());
        Assert.assertEquals("c:3", chapterId.classSpecifier());
        Assert.assertEquals("3:c:3", chapterId.fullSpecifier());
        Assert.assertEquals("3:c:3", chapterId.toString());
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Chapter.of("x"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Chapter.of("v:3"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Chapter.of("x:v:3"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Chapter.of("g"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of(":"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of(":a"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Chapter.of("a:"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of("a:2:"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Chapter.of("2:"));
        TestUtils.assertException(ClassCastException.class, () ->
                FFmpeg.Identifier.Chapter.of("2:a:"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of("1:1:v"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of("1:1:1"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of("1:t:3"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of("t:3"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of("t"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of("test"));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of(""));
        Assert.assertNull(FFmpeg.Identifier.Chapter.of(null));
    }
    
    /**
     * JUnit test of Implements.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements
     * @see #testImplementsImplement()
     * @see #testImplementsFormat()
     * @see #testImplementsDemuxer()
     * @see #testImplementsMuxer()
     * @see #testImplementsDevice()
     * @see #testImplementsCodec()
     * @see #testImplementsCoder()
     * @see #testImplementsDecoder()
     * @see #testImplementsEncoder()
     * @see #testImplementsBitstreamFilter()
     * @see #testImplementsProtocol()
     * @see #testImplementsFilter()
     * @see #testImplementsPixelFormat()
     * @see #testImplementsSampleFormat()
     * @see #testImplementsChannel()
     * @see #testImplementsChannelLayout()
     * @see #testImplementsColor()
     */
    @Test
    public void testImplements() throws Exception {
        if (!FFmpeg.ffmpegExists()) {
            logger.warn("ffmpeg is not installed... skipping test");
            return;
        }
        
        PowerMockito.mockStatic(FFmpeg.Implements.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn(new ArrayList<>()).when(FFmpeg.Implements.class, "loadImplement", ArgumentMatchers.any(Class.class), ArgumentMatchers.any(Function.class));
        TestUtils.setField(FFmpeg.Implements.class, "formats", null);
        TestUtils.setField(FFmpeg.Implements.class, "demuxers", null);
        TestUtils.setField(FFmpeg.Implements.class, "muxers", null);
        TestUtils.setField(FFmpeg.Implements.class, "devices", null);
        TestUtils.setField(FFmpeg.Implements.class, "codecs", null);
        TestUtils.setField(FFmpeg.Implements.class, "decoders", null);
        TestUtils.setField(FFmpeg.Implements.class, "encoders", null);
        TestUtils.setField(FFmpeg.Implements.class, "bitstreamFilters", null);
        TestUtils.setField(FFmpeg.Implements.class, "protocols", null);
        TestUtils.setField(FFmpeg.Implements.class, "filters", null);
        TestUtils.setField(FFmpeg.Implements.class, "pixelFormats", null);
        TestUtils.setField(FFmpeg.Implements.class, "sampleFormats", null);
        TestUtils.setField(FFmpeg.Implements.class, "channels", null);
        TestUtils.setField(FFmpeg.Implements.class, "channelLayouts", null);
        TestUtils.setField(FFmpeg.Implements.class, "colors", null);
        List<FFmpeg.Implements.Format> formats;
        List<FFmpeg.Implements.Demuxer> demuxers;
        List<FFmpeg.Implements.Muxer> muxers;
        List<FFmpeg.Implements.Device> devices;
        List<FFmpeg.Implements.Codec> codecs;
        List<FFmpeg.Implements.Decoder> decoders;
        List<FFmpeg.Implements.Encoder> encoders;
        List<FFmpeg.Implements.BitstreamFilter> bitstreamFilters;
        List<FFmpeg.Implements.Protocol> protocols;
        List<FFmpeg.Implements.Filter> filters;
        List<FFmpeg.Implements.PixelFormat> pixelFormats;
        List<FFmpeg.Implements.SampleFormat> sampleFormats;
        List<FFmpeg.Implements.Channel> channels;
        List<FFmpeg.Implements.ChannelLayout> channelLayouts;
        List<FFmpeg.Implements.Color> colors;
        
        //formats
        formats = FFmpeg.Implements.getFormats();
        Assert.assertNotNull(formats);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Format.class), ArgumentMatchers.any());
        Assert.assertSame(formats, FFmpeg.Implements.getFormats());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Format.class), ArgumentMatchers.any());
        
        //demuxers
        demuxers = FFmpeg.Implements.getDemuxers();
        Assert.assertNotNull(demuxers);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Demuxer.class), ArgumentMatchers.any());
        Assert.assertSame(demuxers, FFmpeg.Implements.getDemuxers());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Demuxer.class), ArgumentMatchers.any());
        
        //muxers
        muxers = FFmpeg.Implements.getMuxers();
        Assert.assertNotNull(muxers);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Muxer.class), ArgumentMatchers.any());
        Assert.assertSame(muxers, FFmpeg.Implements.getMuxers());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Muxer.class), ArgumentMatchers.any());
        
        //devices
        devices = FFmpeg.Implements.getDevices();
        Assert.assertNotNull(devices);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Device.class), ArgumentMatchers.any());
        Assert.assertSame(devices, FFmpeg.Implements.getDevices());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Device.class), ArgumentMatchers.any());
        
        //codecs
        codecs = FFmpeg.Implements.getCodecs();
        Assert.assertNotNull(codecs);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Codec.class), ArgumentMatchers.any());
        Assert.assertSame(codecs, FFmpeg.Implements.getCodecs());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Codec.class), ArgumentMatchers.any());
        
        //decoders
        decoders = FFmpeg.Implements.getDecoders();
        Assert.assertNotNull(decoders);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Decoder.class), ArgumentMatchers.any());
        Assert.assertSame(decoders, FFmpeg.Implements.getDecoders());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Decoder.class), ArgumentMatchers.any());
        
        //encoders
        encoders = FFmpeg.Implements.getEncoders();
        Assert.assertNotNull(encoders);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Encoder.class), ArgumentMatchers.any());
        Assert.assertSame(encoders, FFmpeg.Implements.getEncoders());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Encoder.class), ArgumentMatchers.any());
        
        //bitstream filters
        bitstreamFilters = FFmpeg.Implements.getBitstreamFilters();
        Assert.assertNotNull(bitstreamFilters);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.BitstreamFilter.class), ArgumentMatchers.any());
        Assert.assertSame(bitstreamFilters, FFmpeg.Implements.getBitstreamFilters());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.BitstreamFilter.class), ArgumentMatchers.any());
        
        //protocols
        protocols = FFmpeg.Implements.getProtocols();
        Assert.assertNotNull(protocols);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Protocol.class), ArgumentMatchers.any());
        Assert.assertSame(protocols, FFmpeg.Implements.getProtocols());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Protocol.class), ArgumentMatchers.any());
        
        //filters
        filters = FFmpeg.Implements.getFilters();
        Assert.assertNotNull(filters);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Filter.class), ArgumentMatchers.any());
        Assert.assertSame(filters, FFmpeg.Implements.getFilters());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Filter.class), ArgumentMatchers.any());
        
        //pixel formats
        pixelFormats = FFmpeg.Implements.getPixelFormats();
        Assert.assertNotNull(pixelFormats);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.PixelFormat.class), ArgumentMatchers.any());
        Assert.assertSame(pixelFormats, FFmpeg.Implements.getPixelFormats());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.PixelFormat.class), ArgumentMatchers.any());
        
        //sample formats
        sampleFormats = FFmpeg.Implements.getSampleFormats();
        Assert.assertNotNull(sampleFormats);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.SampleFormat.class), ArgumentMatchers.any());
        Assert.assertSame(sampleFormats, FFmpeg.Implements.getSampleFormats());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.SampleFormat.class), ArgumentMatchers.any());
        
        //channels
        channels = FFmpeg.Implements.getChannels();
        Assert.assertNotNull(channels);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Channel.class), ArgumentMatchers.any());
        Assert.assertSame(channels, FFmpeg.Implements.getChannels());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Channel.class), ArgumentMatchers.any());
        
        //channel layouts
        channelLayouts = FFmpeg.Implements.getChannelLayouts();
        Assert.assertNotNull(channelLayouts);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.ChannelLayout.class), ArgumentMatchers.any());
        Assert.assertSame(channelLayouts, FFmpeg.Implements.getChannelLayouts());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.ChannelLayout.class), ArgumentMatchers.any());
        
        //colors
        colors = FFmpeg.Implements.getColors();
        Assert.assertNotNull(colors);
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Color.class), ArgumentMatchers.any());
        Assert.assertSame(colors, FFmpeg.Implements.getColors());
        PowerMockito.verifyPrivate(FFmpeg.Implements.class, VerificationModeFactory.times(1))
                .invoke("loadImplement", ArgumentMatchers.eq(FFmpeg.Implements.Color.class), ArgumentMatchers.any());
        
        //clear cache
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "formats"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "demuxers"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "muxers"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "devices"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "codecs"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "decoders"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "encoders"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "bitstreamFilters"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "protocols"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "filters"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "pixelFormats"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "sampleFormats"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "channels"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "channelLayouts"));
        Assert.assertNotNull(TestUtils.getField(FFmpeg.Implements.class, "colors"));
        FFmpeg.Implements.clearCache();
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "formats"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "demuxers"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "muxers"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "devices"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "codecs"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "decoders"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "encoders"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "bitstreamFilters"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "protocols"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "filters"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "pixelFormats"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "sampleFormats"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "channels"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "channelLayouts"));
        Assert.assertNull(TestUtils.getField(FFmpeg.Implements.class, "colors"));
        
        //implements
        testImplementsImplement();
        testImplementsFormat();
        testImplementsDemuxer();
        testImplementsMuxer();
        testImplementsDevice();
        testImplementsCodec();
        testImplementsCoder();
        testImplementsDecoder();
        testImplementsEncoder();
        testImplementsBitstreamFilter();
        testImplementsProtocol();
        testImplementsFilter();
        testImplementsPixelFormat();
        testImplementsSampleFormat();
        testImplementsChannel();
        testImplementsChannelLayout();
        testImplementsColor();
    }
    
    /**
     * Helper method for JUnit test of Implements for Implement.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Implement
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private void testImplementsImplement() throws Exception {
        //class
        Class<?> implement = Arrays.stream(FFmpeg.Implements.class.getDeclaredClasses())
                .filter(e -> e.getSimpleName().equals("Implement")).findFirst().orElseGet(null);
        Assert.assertNotNull(implement);
        
        //fields
        Assert.assertEquals(String.class, implement.getDeclaredField("name").getType());
        Assert.assertEquals(String.class, implement.getDeclaredField("description").getType());
        Assert.assertEquals(FFmpeg.StreamType.class, implement.getDeclaredField("type").getType());
        Assert.assertEquals(String.class, implement.getDeclaredField("logLine").getType());
        Assert.assertEquals(Matcher.class, implement.getDeclaredField("logLineMatcher").getType());
        Assert.assertEquals(boolean.class, implement.getDeclaredField("invalid").getType());
        
        //constructors
        Assert.assertNotNull(implement.getConstructor(String.class, String.class));
        
        //getters
        Assert.assertNotNull(implement.getDeclaredMethod("getName"));
        Assert.assertNotNull(implement.getDeclaredMethod("getDescription"));
        Assert.assertNotNull(implement.getDeclaredMethod("getType"));
        Assert.assertNotNull(implement.getDeclaredMethod("getImplementLinePattern"));
        
        //subclasses
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.Format.class.getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.Codec.class.getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), Arrays.stream(FFmpeg.Implements.class.getDeclaredClasses())
                .filter(e -> e.getSimpleName().equals("Coder")).findFirst().get().getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.BitstreamFilter.class.getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.Protocol.class.getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.Filter.class.getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.PixelFormat.class.getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.SampleFormat.class.getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.Channel.class.getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.ChannelLayout.class.getSuperclass().getSimpleName());
        Assert.assertEquals(implement.getSimpleName(), FFmpeg.Implements.Color.class.getSuperclass().getSimpleName());
    }
    
    /**
     * Helper method for JUnit test of Implements for Format.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Format
     */
    private void testImplementsFormat() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.Format.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Format format1;
        FFmpeg.Implements.Format format2;
        FFmpeg.Implements.Format format3;
        FFmpeg.Implements.Format format4;
        
        //constants
        Assert.assertEquals("^\\s*(?<supportsDemuxing>.)(?<supportsMuxing>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$",
                FFmpeg.Implements.Format.FORMAT_LINE_PATTERN.pattern());
        
        //constructors
        format1 = new FFmpeg.Implements.Format("D. test1                Just a test Format", "");
        Assert.assertNotNull(format1);
        format2 = new FFmpeg.Implements.Format(".E test-2               Another test Format", "");
        Assert.assertNotNull(format2);
        format3 = new FFmpeg.Implements.Format("DE test:3               An even other test Format (Format 3)", "");
        Assert.assertNotNull(format3);
        format4 = new FFmpeg.Implements.Format(".. test_4               The last Format", "");
        Assert.assertNotNull(format4);
        
        //getters
        Assert.assertEquals("test1", format1.getName());
        Assert.assertEquals("Just a test Format", format1.getDescription());
        Assert.assertNull(format1.getType());
        Assert.assertTrue(format1.supportsDemuxing());
        Assert.assertFalse(format1.supportsMuxing());
        Assert.assertEquals("test-2", format2.getName());
        Assert.assertEquals("Another test Format", format2.getDescription());
        Assert.assertNull(format2.getType());
        Assert.assertFalse(format2.supportsDemuxing());
        Assert.assertTrue(format2.supportsMuxing());
        Assert.assertEquals("test:3", format3.getName());
        Assert.assertEquals("An even other test Format (Format 3)", format3.getDescription());
        Assert.assertNull(format3.getType());
        Assert.assertTrue(format3.supportsDemuxing());
        Assert.assertTrue(format3.supportsMuxing());
        Assert.assertEquals("test_4", format4.getName());
        Assert.assertEquals("The last Format", format4.getDescription());
        Assert.assertNull(format4.getType());
        Assert.assertFalse(format4.supportsDemuxing());
        Assert.assertFalse(format4.supportsMuxing());
        Assert.assertNotNull(FFmpeg.Implements.Format.class.getDeclaredMethod("getImplementLinePattern"));
        
        //subclasses
        Assert.assertEquals("Format", FFmpeg.Implements.Demuxer.class.getSuperclass().getSimpleName());
        Assert.assertEquals("Format", FFmpeg.Implements.Muxer.class.getSuperclass().getSimpleName());
        Assert.assertEquals("Format", FFmpeg.Implements.Device.class.getSuperclass().getSimpleName());
    }
    
    /**
     * Helper method for JUnit test of Implements for Demuxer.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Demuxer
     */
    private void testImplementsDemuxer() throws Exception {
        Assert.assertEquals("Format", FFmpeg.Implements.Demuxer.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Demuxer demuxer1;
        FFmpeg.Implements.Demuxer demuxer2;
        FFmpeg.Implements.Demuxer demuxer3;
        FFmpeg.Implements.Demuxer demuxer4;
        
        //constants
        Assert.assertEquals("^\\s*(?<supportsDemuxing>.)(?<supportsMuxing>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$",
                FFmpeg.Implements.Demuxer.DEMUXER_LINE_PATTERN.pattern());
        
        //constructors
        demuxer1 = new FFmpeg.Implements.Demuxer("D. test1                Just a test Demuxer", "");
        Assert.assertNotNull(demuxer1);
        demuxer2 = new FFmpeg.Implements.Demuxer(".E test-2               Another test Demuxer", "");
        Assert.assertNotNull(demuxer2);
        demuxer3 = new FFmpeg.Implements.Demuxer("DE test:3               An even other test Demuxer (Demuxer 3)", "");
        Assert.assertNotNull(demuxer3);
        demuxer4 = new FFmpeg.Implements.Demuxer(".. test_4               The last Demuxer", "");
        Assert.assertNotNull(demuxer4);
        
        //getters
        Assert.assertEquals("test1", demuxer1.getName());
        Assert.assertEquals("Just a test Demuxer", demuxer1.getDescription());
        Assert.assertNull(demuxer1.getType());
        Assert.assertTrue(demuxer1.supportsDemuxing());
        Assert.assertFalse(demuxer1.supportsMuxing());
        Assert.assertEquals("test-2", demuxer2.getName());
        Assert.assertEquals("Another test Demuxer", demuxer2.getDescription());
        Assert.assertNull(demuxer2.getType());
        Assert.assertFalse(demuxer2.supportsDemuxing());
        Assert.assertTrue(demuxer2.supportsMuxing());
        Assert.assertEquals("test:3", demuxer3.getName());
        Assert.assertEquals("An even other test Demuxer (Demuxer 3)", demuxer3.getDescription());
        Assert.assertNull(demuxer3.getType());
        Assert.assertTrue(demuxer3.supportsDemuxing());
        Assert.assertTrue(demuxer3.supportsMuxing());
        Assert.assertEquals("test_4", demuxer4.getName());
        Assert.assertEquals("The last Demuxer", demuxer4.getDescription());
        Assert.assertNull(demuxer4.getType());
        Assert.assertFalse(demuxer4.supportsDemuxing());
        Assert.assertFalse(demuxer4.supportsMuxing());
        Assert.assertNotNull(FFmpeg.Implements.Demuxer.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for Muxer.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Muxer
     */
    private void testImplementsMuxer() throws Exception {
        Assert.assertEquals("Format", FFmpeg.Implements.Muxer.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Muxer muxer1;
        FFmpeg.Implements.Muxer muxer2;
        FFmpeg.Implements.Muxer muxer3;
        FFmpeg.Implements.Muxer muxer4;
        
        //constants
        Assert.assertEquals("^\\s*(?<supportsDemuxing>.)(?<supportsMuxing>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$",
                FFmpeg.Implements.Muxer.MUXER_LINE_PATTERN.pattern());
        
        //constructors
        muxer1 = new FFmpeg.Implements.Muxer("D. test1                Just a test Muxer", "");
        Assert.assertNotNull(muxer1);
        muxer2 = new FFmpeg.Implements.Muxer(".E test-2               Another test Muxer", "");
        Assert.assertNotNull(muxer2);
        muxer3 = new FFmpeg.Implements.Muxer("DE test:3               An even other test Muxer (Muxer 3)", "");
        Assert.assertNotNull(muxer3);
        muxer4 = new FFmpeg.Implements.Muxer(".. test_4               The last Muxer", "");
        Assert.assertNotNull(muxer4);
        
        //getters
        Assert.assertEquals("test1", muxer1.getName());
        Assert.assertEquals("Just a test Muxer", muxer1.getDescription());
        Assert.assertNull(muxer1.getType());
        Assert.assertTrue(muxer1.supportsDemuxing());
        Assert.assertFalse(muxer1.supportsMuxing());
        Assert.assertEquals("test-2", muxer2.getName());
        Assert.assertEquals("Another test Muxer", muxer2.getDescription());
        Assert.assertNull(muxer2.getType());
        Assert.assertFalse(muxer2.supportsDemuxing());
        Assert.assertTrue(muxer2.supportsMuxing());
        Assert.assertEquals("test:3", muxer3.getName());
        Assert.assertEquals("An even other test Muxer (Muxer 3)", muxer3.getDescription());
        Assert.assertNull(muxer3.getType());
        Assert.assertTrue(muxer3.supportsDemuxing());
        Assert.assertTrue(muxer3.supportsMuxing());
        Assert.assertEquals("test_4", muxer4.getName());
        Assert.assertEquals("The last Muxer", muxer4.getDescription());
        Assert.assertNull(muxer4.getType());
        Assert.assertFalse(muxer4.supportsDemuxing());
        Assert.assertFalse(muxer4.supportsMuxing());
        Assert.assertNotNull(FFmpeg.Implements.Muxer.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for Device.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Device
     */
    private void testImplementsDevice() throws Exception {
        Assert.assertEquals("Format", FFmpeg.Implements.Device.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Device device1;
        FFmpeg.Implements.Device device2;
        FFmpeg.Implements.Device device3;
        FFmpeg.Implements.Device device4;
        
        //constants
        Assert.assertEquals("^\\s*(?<supportsDemuxing>.)(?<supportsMuxing>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$",
                FFmpeg.Implements.Device.DEVICE_LINE_PATTERN.pattern());
        
        //constructors
        device1 = new FFmpeg.Implements.Device("D. test1                Just a test Device", "");
        Assert.assertNotNull(device1);
        device2 = new FFmpeg.Implements.Device(".E test-2               Another test Device", "");
        Assert.assertNotNull(device2);
        device3 = new FFmpeg.Implements.Device("DE test:3               An even other test Device (Device 3)", "");
        Assert.assertNotNull(device3);
        device4 = new FFmpeg.Implements.Device(".. test_4               The last Device", "");
        Assert.assertNotNull(device4);
        
        //getters
        Assert.assertEquals("test1", device1.getName());
        Assert.assertEquals("Just a test Device", device1.getDescription());
        Assert.assertNull(device1.getType());
        Assert.assertTrue(device1.supportsDemuxing());
        Assert.assertFalse(device1.supportsMuxing());
        Assert.assertEquals("test-2", device2.getName());
        Assert.assertEquals("Another test Device", device2.getDescription());
        Assert.assertNull(device2.getType());
        Assert.assertFalse(device2.supportsDemuxing());
        Assert.assertTrue(device2.supportsMuxing());
        Assert.assertEquals("test:3", device3.getName());
        Assert.assertEquals("An even other test Device (Device 3)", device3.getDescription());
        Assert.assertNull(device3.getType());
        Assert.assertTrue(device3.supportsDemuxing());
        Assert.assertTrue(device3.supportsMuxing());
        Assert.assertEquals("test_4", device4.getName());
        Assert.assertEquals("The last Device", device4.getDescription());
        Assert.assertNull(device4.getType());
        Assert.assertFalse(device4.supportsDemuxing());
        Assert.assertFalse(device4.supportsMuxing());
        Assert.assertNotNull(FFmpeg.Implements.Device.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for Codec.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Codec
     */
    private void testImplementsCodec() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.Codec.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Codec codec1;
        FFmpeg.Implements.Codec codec2;
        FFmpeg.Implements.Codec codec3;
        FFmpeg.Implements.Codec codec4;
        
        //constants
        Assert.assertEquals("^\\s*(?<supportsDecoding>.)(?<supportsEncoding>.)(?<type>.)(?<isIntraFrameOnly>.)(?<hasLossyCompression>.)(?<hasLosslessCompression>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$",
                FFmpeg.Implements.Codec.CODEC_LINE_PATTERN.pattern());
        
        //constructors
        codec1 = new FFmpeg.Implements.Codec("DEVILS test1                Just a test Codec", "");
        Assert.assertNotNull(codec1);
        codec2 = new FFmpeg.Implements.Codec("..A.L. test-2               Another test Codec", "");
        Assert.assertNotNull(codec2);
        codec3 = new FFmpeg.Implements.Codec("D.SI.S test:3               An even other test Codec (Codec 3)", "");
        Assert.assertNotNull(codec3);
        codec4 = new FFmpeg.Implements.Codec("D...LS test_4               The last Codec", "");
        Assert.assertNotNull(codec4);
        
        //getters
        Assert.assertEquals("test1", codec1.getName());
        Assert.assertEquals("Just a test Codec", codec1.getDescription());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, codec1.getType());
        Assert.assertTrue(codec1.supportsDecoding());
        Assert.assertTrue(codec1.supportsEncoding());
        Assert.assertTrue(codec1.isIntraFrameOnly());
        Assert.assertTrue(codec1.hasLossyCompression());
        Assert.assertTrue(codec1.hasLosslessCompression());
        Assert.assertEquals("test-2", codec2.getName());
        Assert.assertEquals("Another test Codec", codec2.getDescription());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, codec2.getType());
        Assert.assertFalse(codec2.supportsDecoding());
        Assert.assertFalse(codec2.supportsEncoding());
        Assert.assertFalse(codec2.isIntraFrameOnly());
        Assert.assertTrue(codec2.hasLossyCompression());
        Assert.assertFalse(codec2.hasLosslessCompression());
        Assert.assertEquals("test:3", codec3.getName());
        Assert.assertEquals("An even other test Codec (Codec 3)", codec3.getDescription());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, codec3.getType());
        Assert.assertTrue(codec3.supportsDecoding());
        Assert.assertFalse(codec3.supportsEncoding());
        Assert.assertTrue(codec3.isIntraFrameOnly());
        Assert.assertFalse(codec3.hasLossyCompression());
        Assert.assertTrue(codec3.hasLosslessCompression());
        Assert.assertEquals("test_4", codec4.getName());
        Assert.assertEquals("The last Codec", codec4.getDescription());
        Assert.assertNull(codec4.getType());
        Assert.assertTrue(codec4.supportsDecoding());
        Assert.assertFalse(codec4.supportsEncoding());
        Assert.assertFalse(codec4.isIntraFrameOnly());
        Assert.assertTrue(codec4.hasLossyCompression());
        Assert.assertTrue(codec4.hasLosslessCompression());
        Assert.assertNotNull(FFmpeg.Implements.Codec.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for Coder.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Coder
     */
    private void testImplementsCoder() throws Exception {
        //class
        Class<?> coder = Arrays.stream(FFmpeg.Implements.class.getDeclaredClasses())
                .filter(e -> e.getSimpleName().equals("Coder")).findFirst().orElseGet(null);
        Assert.assertNotNull(coder);
        Assert.assertEquals("Implement", coder.getSuperclass().getSimpleName());
        
        //constants
        Assert.assertEquals("^\\s*(?<type>.)(?<hasFrameLevelMultithreading>.)(?<hasSliceLevelMultithreading>.)(?<isExperimental>.)(?<supportsDrawHorizontalBand>.)(?<supportsDirectRenderingMethod1>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$",
                ((Pattern) TestUtils.getField(coder, "CODER_LINE_PATTERN")).pattern());
        
        //fields
        Assert.assertEquals(boolean.class, coder.getDeclaredField("hasFrameLevelMultithreading").getType());
        Assert.assertEquals(boolean.class, coder.getDeclaredField("hasSliceLevelMultithreading").getType());
        Assert.assertEquals(boolean.class, coder.getDeclaredField("isExperimental").getType());
        Assert.assertEquals(boolean.class, coder.getDeclaredField("supportsDrawHorizontalBand").getType());
        Assert.assertEquals(boolean.class, coder.getDeclaredField("supportsDirectRenderingMethod1").getType());
        
        //constructors
        Assert.assertNotNull(coder.getConstructor(String.class, String.class));
        
        //getters
        Assert.assertNotNull(coder.getDeclaredMethod("hasFrameLevelMultithreading"));
        Assert.assertNotNull(coder.getDeclaredMethod("hasSliceLevelMultithreading"));
        Assert.assertNotNull(coder.getDeclaredMethod("isExperimental"));
        Assert.assertNotNull(coder.getDeclaredMethod("supportsDrawHorizontalBand"));
        Assert.assertNotNull(coder.getDeclaredMethod("supportsDirectRenderingMethod1"));
        Assert.assertNotNull(coder.getDeclaredMethod("getImplementLinePattern"));
        
        //subclasses
        Assert.assertEquals(coder.getSimpleName(), FFmpeg.Implements.Decoder.class.getSuperclass().getSimpleName());
        Assert.assertEquals(coder.getSimpleName(), FFmpeg.Implements.Encoder.class.getSuperclass().getSimpleName());
    }
    
    /**
     * Helper method for JUnit test of Implements for Decoder.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Decoder
     */
    private void testImplementsDecoder() throws Exception {
        Assert.assertEquals("Coder", FFmpeg.Implements.Decoder.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Decoder decoder1;
        FFmpeg.Implements.Decoder decoder2;
        FFmpeg.Implements.Decoder decoder3;
        FFmpeg.Implements.Decoder decoder4;
        
        //constants
        Assert.assertEquals("^\\s*(?<type>.)(?<hasFrameLevelMultithreading>.)(?<hasSliceLevelMultithreading>.)(?<isExperimental>.)(?<supportsDrawHorizontalBand>.)(?<supportsDirectRenderingMethod1>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$",
                FFmpeg.Implements.Decoder.DECODER_LINE_PATTERN.pattern());
        
        //constructors
        decoder1 = new FFmpeg.Implements.Decoder("VFSXBD test1                Just a test Decoder", "");
        Assert.assertNotNull(decoder1);
        decoder2 = new FFmpeg.Implements.Decoder("A...B. test-2               Another test Decoder", "");
        Assert.assertNotNull(decoder2);
        decoder3 = new FFmpeg.Implements.Decoder("SF.X.D test:3               An even other test Decoder (Decoder 3)", "");
        Assert.assertNotNull(decoder3);
        decoder4 = new FFmpeg.Implements.Decoder(".F..BC test_4               The last Decoder", "");
        Assert.assertNotNull(decoder4);
        
        //getters
        Assert.assertEquals("test1", decoder1.getName());
        Assert.assertEquals("Just a test Decoder", decoder1.getDescription());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, decoder1.getType());
        Assert.assertTrue(decoder1.hasFrameLevelMultithreading());
        Assert.assertTrue(decoder1.hasSliceLevelMultithreading());
        Assert.assertTrue(decoder1.isExperimental());
        Assert.assertTrue(decoder1.supportsDrawHorizontalBand());
        Assert.assertTrue(decoder1.supportsDirectRenderingMethod1());
        Assert.assertEquals("test-2", decoder2.getName());
        Assert.assertEquals("Another test Decoder", decoder2.getDescription());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, decoder2.getType());
        Assert.assertFalse(decoder2.hasFrameLevelMultithreading());
        Assert.assertFalse(decoder2.hasSliceLevelMultithreading());
        Assert.assertFalse(decoder2.isExperimental());
        Assert.assertTrue(decoder2.supportsDrawHorizontalBand());
        Assert.assertFalse(decoder2.supportsDirectRenderingMethod1());
        Assert.assertEquals("test:3", decoder3.getName());
        Assert.assertEquals("An even other test Decoder (Decoder 3)", decoder3.getDescription());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, decoder3.getType());
        Assert.assertTrue(decoder3.hasFrameLevelMultithreading());
        Assert.assertFalse(decoder3.hasSliceLevelMultithreading());
        Assert.assertTrue(decoder3.isExperimental());
        Assert.assertFalse(decoder3.supportsDrawHorizontalBand());
        Assert.assertTrue(decoder3.supportsDirectRenderingMethod1());
        Assert.assertEquals("test_4", decoder4.getName());
        Assert.assertEquals("The last Decoder", decoder4.getDescription());
        Assert.assertNull(decoder4.getType());
        Assert.assertTrue(decoder4.hasFrameLevelMultithreading());
        Assert.assertFalse(decoder4.hasSliceLevelMultithreading());
        Assert.assertFalse(decoder4.isExperimental());
        Assert.assertTrue(decoder4.supportsDrawHorizontalBand());
        Assert.assertTrue(decoder4.supportsDirectRenderingMethod1());
        Assert.assertNotNull(FFmpeg.Implements.Decoder.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for Encoder.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Encoder
     */
    private void testImplementsEncoder() throws Exception {
        Assert.assertEquals("Coder", FFmpeg.Implements.Encoder.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Encoder encoder1;
        FFmpeg.Implements.Encoder encoder2;
        FFmpeg.Implements.Encoder encoder3;
        FFmpeg.Implements.Encoder encoder4;
        
        //constants
        Assert.assertEquals("^\\s*(?<type>.)(?<hasFrameLevelMultithreading>.)(?<hasSliceLevelMultithreading>.)(?<isExperimental>.)(?<supportsDrawHorizontalBand>.)(?<supportsDirectRenderingMethod1>.)\\s+(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$",
                FFmpeg.Implements.Encoder.ENCODER_LINE_PATTERN.pattern());
        
        //constructors
        encoder1 = new FFmpeg.Implements.Encoder("VFSXBD test1                Just a test Encoder", "");
        Assert.assertNotNull(encoder1);
        encoder2 = new FFmpeg.Implements.Encoder("A...B. test-2               Another test Encoder", "");
        Assert.assertNotNull(encoder2);
        encoder3 = new FFmpeg.Implements.Encoder("SF.X.D test:3               An even other test Encoder (Encoder 3)", "");
        Assert.assertNotNull(encoder3);
        encoder4 = new FFmpeg.Implements.Encoder(".F..BC test_4               The last Encoder", "");
        Assert.assertNotNull(encoder4);
        
        //getters
        Assert.assertEquals("test1", encoder1.getName());
        Assert.assertEquals("Just a test Encoder", encoder1.getDescription());
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, encoder1.getType());
        Assert.assertTrue(encoder1.hasFrameLevelMultithreading());
        Assert.assertTrue(encoder1.hasSliceLevelMultithreading());
        Assert.assertTrue(encoder1.isExperimental());
        Assert.assertTrue(encoder1.supportsDrawHorizontalBand());
        Assert.assertTrue(encoder1.supportsDirectRenderingMethod1());
        Assert.assertEquals("test-2", encoder2.getName());
        Assert.assertEquals("Another test Encoder", encoder2.getDescription());
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, encoder2.getType());
        Assert.assertFalse(encoder2.hasFrameLevelMultithreading());
        Assert.assertFalse(encoder2.hasSliceLevelMultithreading());
        Assert.assertFalse(encoder2.isExperimental());
        Assert.assertTrue(encoder2.supportsDrawHorizontalBand());
        Assert.assertFalse(encoder2.supportsDirectRenderingMethod1());
        Assert.assertEquals("test:3", encoder3.getName());
        Assert.assertEquals("An even other test Encoder (Encoder 3)", encoder3.getDescription());
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, encoder3.getType());
        Assert.assertTrue(encoder3.hasFrameLevelMultithreading());
        Assert.assertFalse(encoder3.hasSliceLevelMultithreading());
        Assert.assertTrue(encoder3.isExperimental());
        Assert.assertFalse(encoder3.supportsDrawHorizontalBand());
        Assert.assertTrue(encoder3.supportsDirectRenderingMethod1());
        Assert.assertEquals("test_4", encoder4.getName());
        Assert.assertEquals("The last Encoder", encoder4.getDescription());
        Assert.assertNull(encoder4.getType());
        Assert.assertTrue(encoder4.hasFrameLevelMultithreading());
        Assert.assertFalse(encoder4.hasSliceLevelMultithreading());
        Assert.assertFalse(encoder4.isExperimental());
        Assert.assertTrue(encoder4.supportsDrawHorizontalBand());
        Assert.assertTrue(encoder4.supportsDirectRenderingMethod1());
        Assert.assertNotNull(FFmpeg.Implements.Encoder.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for BitstreamFilter.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.BitstreamFilter
     */
    private void testImplementsBitstreamFilter() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.BitstreamFilter.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.BitstreamFilter bitstreamFilter1;
        FFmpeg.Implements.BitstreamFilter bitstreamFilter2;
        
        //constants
        Assert.assertEquals("^\\s*(?<name>[^\\s]+)\\s*$",
                FFmpeg.Implements.BitstreamFilter.BITSTREAM_FILTER_LINE_PATTERN.pattern());
        
        //constructors
        bitstreamFilter1 = new FFmpeg.Implements.BitstreamFilter("test1", "");
        Assert.assertNotNull(bitstreamFilter1);
        bitstreamFilter2 = new FFmpeg.Implements.BitstreamFilter("test-2", "");
        Assert.assertNotNull(bitstreamFilter2);
        
        //getters
        Assert.assertEquals("test1", bitstreamFilter1.getName());
        Assert.assertNull(bitstreamFilter1.getDescription());
        Assert.assertNull(bitstreamFilter1.getType());
        Assert.assertEquals("test-2", bitstreamFilter2.getName());
        Assert.assertNull(bitstreamFilter2.getDescription());
        Assert.assertNull(bitstreamFilter2.getType());
        Assert.assertNotNull(FFmpeg.Implements.BitstreamFilter.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for Protocol.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Protocol
     */
    private void testImplementsProtocol() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.Protocol.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Protocol protocol1;
        FFmpeg.Implements.Protocol protocol2;
        FFmpeg.Implements.Protocol protocol3;
        FFmpeg.Implements.Protocol protocol4;
        
        //constants
        Assert.assertEquals("^\\s*(?<name>[^\\s]+)\\s*$",
                FFmpeg.Implements.Protocol.PROTOCOL_LINE_PATTERN.pattern());
        
        //constructors
        protocol1 = new FFmpeg.Implements.Protocol("test1", "");
        Assert.assertNotNull(protocol1);
        protocol2 = new FFmpeg.Implements.Protocol("test-2", "Input");
        Assert.assertNotNull(protocol2);
        protocol3 = new FFmpeg.Implements.Protocol("test:3", "Input");
        Assert.assertNotNull(protocol3);
        protocol4 = new FFmpeg.Implements.Protocol("test_4", "Output");
        Assert.assertNotNull(protocol4);
        
        //getters
        Assert.assertEquals("test1", protocol1.getName());
        Assert.assertNull(protocol1.getDescription());
        Assert.assertNull(protocol1.getType());
        Assert.assertFalse(protocol1.isInput());
        Assert.assertFalse(protocol1.isOutput());
        Assert.assertEquals("test-2", protocol2.getName());
        Assert.assertNull(protocol2.getDescription());
        Assert.assertNull(protocol2.getType());
        Assert.assertTrue(protocol2.isInput());
        Assert.assertFalse(protocol2.isOutput());
        Assert.assertEquals("test:3", protocol3.getName());
        Assert.assertNull(protocol3.getDescription());
        Assert.assertNull(protocol3.getType());
        Assert.assertTrue(protocol3.isInput());
        Assert.assertFalse(protocol3.isOutput());
        Assert.assertEquals("test_4", protocol4.getName());
        Assert.assertNull(protocol4.getDescription());
        Assert.assertNull(protocol4.getType());
        Assert.assertFalse(protocol4.isInput());
        Assert.assertTrue(protocol4.isOutput());
        Assert.assertNotNull(FFmpeg.Implements.Protocol.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for Filter.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Filter
     */
    private void testImplementsFilter() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.Filter.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Filter filter1;
        FFmpeg.Implements.Filter filter2;
        FFmpeg.Implements.Filter filter3;
        FFmpeg.Implements.Filter filter4;
        
        //constants
        Assert.assertEquals("^\\s*(?<supportsTimeline>.)(?<hasSliceThreading>.)(?<supportsCommand>.)\\s+(?<name>[^\\s]+)\\s+(?<specification>[^\\s]+)\\s+(?<description>.+)\\s*$",
                FFmpeg.Implements.Filter.FILTER_LINE_PATTERN.pattern());
        
        //constructors
        filter1 = new FFmpeg.Implements.Filter("TSC test1        A->A          Just a test Filter", "");
        Assert.assertNotNull(filter1);
        filter2 = new FFmpeg.Implements.Filter("T.C test-2       |->A          Another test Filter", "");
        Assert.assertNotNull(filter2);
        filter3 = new FFmpeg.Implements.Filter(".S. test:3       V->N          An even other test Filter (Filter 3)", "");
        Assert.assertNotNull(filter3);
        filter4 = new FFmpeg.Implements.Filter(".SC test_4       AA->A         The last Filter", "");
        Assert.assertNotNull(filter4);
        
        //getters
        Assert.assertEquals("test1", filter1.getName());
        Assert.assertEquals("Just a test Filter", filter1.getDescription());
        Assert.assertNull(filter1.getType());
        Assert.assertEquals("A->A", filter1.getSpecification());
        Assert.assertTrue(filter1.supportsTimeline());
        Assert.assertTrue(filter1.hasSliceThreading());
        Assert.assertTrue(filter1.supportsCommand());
        Assert.assertEquals("test-2", filter2.getName());
        Assert.assertEquals("Another test Filter", filter2.getDescription());
        Assert.assertNull(filter2.getType());
        Assert.assertEquals("|->A", filter2.getSpecification());
        Assert.assertTrue(filter2.supportsTimeline());
        Assert.assertFalse(filter2.hasSliceThreading());
        Assert.assertTrue(filter2.supportsCommand());
        Assert.assertEquals("test:3", filter3.getName());
        Assert.assertEquals("An even other test Filter (Filter 3)", filter3.getDescription());
        Assert.assertNull(filter3.getType());
        Assert.assertEquals("V->N", filter3.getSpecification());
        Assert.assertFalse(filter3.supportsTimeline());
        Assert.assertTrue(filter3.hasSliceThreading());
        Assert.assertFalse(filter3.supportsCommand());
        Assert.assertEquals("test_4", filter4.getName());
        Assert.assertEquals("The last Filter", filter4.getDescription());
        Assert.assertNull(filter4.getType());
        Assert.assertEquals("AA->A", filter4.getSpecification());
        Assert.assertFalse(filter4.supportsTimeline());
        Assert.assertTrue(filter4.hasSliceThreading());
        Assert.assertTrue(filter4.supportsCommand());
        Assert.assertNotNull(FFmpeg.Implements.Filter.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for PixelFormat.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.PixelFormat
     */
    private void testImplementsPixelFormat() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.PixelFormat.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.PixelFormat pixelFormat1;
        FFmpeg.Implements.PixelFormat pixelFormat2;
        FFmpeg.Implements.PixelFormat pixelFormat3;
        FFmpeg.Implements.PixelFormat pixelFormat4;
        
        //constants
        Assert.assertEquals("^\\s*(?<isSupportedInputForConversion>.)(?<isSupportedOutputForConversion>.)(?<isHardwareAccelerated>.)(?<isPaletted>.)(?<isBitstream>.)\\s+(?<name>[^\\s]+)\\s+(?<components>\\d+)\\s+(?<bitsPerPixel>\\d+)\\s*$",
                FFmpeg.Implements.PixelFormat.PIXEL_FORMAT_LINE_PATTERN.pattern());
        
        //constructors
        pixelFormat1 = new FFmpeg.Implements.PixelFormat("IOHPB test1        3        12", "");
        Assert.assertNotNull(pixelFormat1);
        pixelFormat2 = new FFmpeg.Implements.PixelFormat("I.HP. test-2       1         1", "");
        Assert.assertNotNull(pixelFormat2);
        pixelFormat3 = new FFmpeg.Implements.PixelFormat(".O..B test:3       3         4", "");
        Assert.assertNotNull(pixelFormat3);
        pixelFormat4 = new FFmpeg.Implements.PixelFormat("IO.P. test_4       4        32", "");
        Assert.assertNotNull(pixelFormat4);
        
        //getters
        Assert.assertEquals("test1", pixelFormat1.getName());
        Assert.assertNull(pixelFormat1.getDescription());
        Assert.assertNull(pixelFormat1.getType());
        Assert.assertEquals(3, pixelFormat1.getComponents());
        Assert.assertEquals(12, pixelFormat1.getBitsPerPixel());
        Assert.assertTrue(pixelFormat1.isSupportedInputForConversion());
        Assert.assertTrue(pixelFormat1.isSupportedOutputForConversion());
        Assert.assertTrue(pixelFormat1.isHardwareAccelerated());
        Assert.assertTrue(pixelFormat1.isPaletted());
        Assert.assertTrue(pixelFormat1.isBitstream());
        Assert.assertEquals("test-2", pixelFormat2.getName());
        Assert.assertNull(pixelFormat2.getDescription());
        Assert.assertNull(pixelFormat2.getType());
        Assert.assertEquals(1, pixelFormat2.getComponents());
        Assert.assertEquals(1, pixelFormat2.getBitsPerPixel());
        Assert.assertTrue(pixelFormat2.isSupportedInputForConversion());
        Assert.assertFalse(pixelFormat2.isSupportedOutputForConversion());
        Assert.assertTrue(pixelFormat2.isHardwareAccelerated());
        Assert.assertTrue(pixelFormat2.isPaletted());
        Assert.assertFalse(pixelFormat2.isBitstream());
        Assert.assertEquals("test:3", pixelFormat3.getName());
        Assert.assertNull(pixelFormat3.getDescription());
        Assert.assertNull(pixelFormat3.getType());
        Assert.assertEquals(3, pixelFormat3.getComponents());
        Assert.assertEquals(4, pixelFormat3.getBitsPerPixel());
        Assert.assertFalse(pixelFormat3.isSupportedInputForConversion());
        Assert.assertTrue(pixelFormat3.isSupportedOutputForConversion());
        Assert.assertFalse(pixelFormat3.isHardwareAccelerated());
        Assert.assertFalse(pixelFormat3.isPaletted());
        Assert.assertTrue(pixelFormat3.isBitstream());
        Assert.assertEquals("test_4", pixelFormat4.getName());
        Assert.assertNull(pixelFormat4.getDescription());
        Assert.assertNull(pixelFormat4.getType());
        Assert.assertEquals(4, pixelFormat4.getComponents());
        Assert.assertEquals(32, pixelFormat4.getBitsPerPixel());
        Assert.assertTrue(pixelFormat4.isSupportedInputForConversion());
        Assert.assertTrue(pixelFormat4.isSupportedOutputForConversion());
        Assert.assertFalse(pixelFormat4.isHardwareAccelerated());
        Assert.assertTrue(pixelFormat4.isPaletted());
        Assert.assertFalse(pixelFormat4.isBitstream());
        Assert.assertNotNull(FFmpeg.Implements.PixelFormat.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for SampleFormat.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.SampleFormat
     */
    private void testImplementsSampleFormat() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.SampleFormat.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.SampleFormat sampleFormat1;
        FFmpeg.Implements.SampleFormat sampleFormat2;
        FFmpeg.Implements.SampleFormat sampleFormat3;
        FFmpeg.Implements.SampleFormat sampleFormat4;
        
        //constants
        Assert.assertEquals("^\\s*(?<name>[^\\s]+)\\s+(?<bitDepth>\\d+)\\s*$",
                FFmpeg.Implements.SampleFormat.SAMPLE_FORMAT_LINE_PATTERN.pattern());
        
        //constructors
        sampleFormat1 = new FFmpeg.Implements.SampleFormat("test1      8", "");
        Assert.assertNotNull(sampleFormat1);
        sampleFormat2 = new FFmpeg.Implements.SampleFormat("test-2    64", "");
        Assert.assertNotNull(sampleFormat2);
        sampleFormat3 = new FFmpeg.Implements.SampleFormat("test:3    16", "");
        Assert.assertNotNull(sampleFormat3);
        sampleFormat4 = new FFmpeg.Implements.SampleFormat("test_4    32", "");
        Assert.assertNotNull(sampleFormat4);
        
        //getters
        Assert.assertEquals("test1", sampleFormat1.getName());
        Assert.assertNull(sampleFormat1.getDescription());
        Assert.assertNull(sampleFormat1.getType());
        Assert.assertEquals(8, sampleFormat1.getBitDepth());
        Assert.assertEquals("test-2", sampleFormat2.getName());
        Assert.assertNull(sampleFormat2.getDescription());
        Assert.assertNull(sampleFormat2.getType());
        Assert.assertEquals(64, sampleFormat2.getBitDepth());
        Assert.assertEquals("test:3", sampleFormat3.getName());
        Assert.assertNull(sampleFormat3.getDescription());
        Assert.assertNull(sampleFormat3.getType());
        Assert.assertEquals(16, sampleFormat3.getBitDepth());
        Assert.assertEquals("test_4", sampleFormat4.getName());
        Assert.assertNull(sampleFormat4.getDescription());
        Assert.assertNull(sampleFormat4.getType());
        Assert.assertEquals(32, sampleFormat4.getBitDepth());
        Assert.assertNotNull(FFmpeg.Implements.SampleFormat.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for Channel.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Channel
     */
    private void testImplementsChannel() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.Channel.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Channel channel1;
        FFmpeg.Implements.Channel channel2;
        FFmpeg.Implements.Channel channel3;
        FFmpeg.Implements.Channel channel4;
        
        //constants
        Assert.assertEquals("^\\s*(?<name>[^\\s]+)\\s+(?<description>.+)\\s*$",
                FFmpeg.Implements.Channel.CHANNEL_LINE_PATTERN.pattern());
        
        //constructors
        channel1 = new FFmpeg.Implements.Channel("test1      front left", "Individual Channels");
        Assert.assertNotNull(channel1);
        channel2 = new FFmpeg.Implements.Channel("test-2     top back center", "Individual Channels");
        Assert.assertNotNull(channel2);
        channel3 = new FFmpeg.Implements.Channel("test:3     FL+FR", "Standard Channel Layouts");
        Assert.assertNotNull(channel3);
        channel4 = new FFmpeg.Implements.Channel("test_4     wide right", "");
        Assert.assertNotNull(channel4);
        
        //getters
        Assert.assertEquals("test1", channel1.getName());
        Assert.assertEquals("front left", channel1.getDescription());
        Assert.assertNull(channel1.getType());
        Assert.assertFalse(channel1.invalid);
        Assert.assertEquals("test-2", channel2.getName());
        Assert.assertEquals("top back center", channel2.getDescription());
        Assert.assertNull(channel2.getType());
        Assert.assertFalse(channel2.invalid);
        Assert.assertEquals("test:3", channel3.getName());
        Assert.assertEquals("FL+FR", channel3.getDescription());
        Assert.assertNull(channel3.getType());
        Assert.assertTrue(channel3.invalid);
        Assert.assertEquals("test_4", channel4.getName());
        Assert.assertEquals("wide right", channel4.getDescription());
        Assert.assertNull(channel4.getType());
        Assert.assertTrue(channel4.invalid);
        Assert.assertNotNull(FFmpeg.Implements.Channel.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for ChannelLayout.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.ChannelLayout
     */
    private void testImplementsChannelLayout() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.ChannelLayout.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.ChannelLayout channelLayout1;
        FFmpeg.Implements.ChannelLayout channelLayout2;
        FFmpeg.Implements.ChannelLayout channelLayout3;
        FFmpeg.Implements.ChannelLayout channelLayout4;
        
        //constants
        Assert.assertEquals("^\\s*(?<name>[^\\s]+)\\s+(?<decomposition>.+)\\s*$",
                FFmpeg.Implements.ChannelLayout.CHANNEL_LAYOUT_LINE_PATTERN.pattern());
        
        //constructors
        channelLayout1 = new FFmpeg.Implements.ChannelLayout("test1      FL+FR", "Standard Channel Layouts");
        Assert.assertNotNull(channelLayout1);
        channelLayout2 = new FFmpeg.Implements.ChannelLayout("test-2     FL+FR+FC+LFE+BL+BR+FLC+FRC", "Standard Channel Layouts");
        Assert.assertNotNull(channelLayout2);
        channelLayout3 = new FFmpeg.Implements.ChannelLayout("test:3     front left", "Individual Channel");
        Assert.assertNotNull(channelLayout3);
        channelLayout4 = new FFmpeg.Implements.ChannelLayout("test_4     DL+DR", "");
        Assert.assertNotNull(channelLayout4);
        
        //getters
        Assert.assertEquals("test1", channelLayout1.getName());
        Assert.assertNull(channelLayout1.getDescription());
        Assert.assertNull(channelLayout1.getType());
        Assert.assertEquals("FL+FR", channelLayout1.getDecomposition());
        Assert.assertFalse(channelLayout1.invalid);
        Assert.assertEquals("test-2", channelLayout2.getName());
        Assert.assertNull(channelLayout2.getDescription());
        Assert.assertNull(channelLayout2.getType());
        Assert.assertEquals("FL+FR+FC+LFE+BL+BR+FLC+FRC", channelLayout2.getDecomposition());
        Assert.assertFalse(channelLayout2.invalid);
        Assert.assertEquals("test:3", channelLayout3.getName());
        Assert.assertNull(channelLayout3.getDescription());
        Assert.assertNull(channelLayout3.getType());
        Assert.assertNull(channelLayout3.getDecomposition());
        Assert.assertTrue(channelLayout3.invalid);
        Assert.assertEquals("test_4", channelLayout4.getName());
        Assert.assertNull(channelLayout4.getDescription());
        Assert.assertNull(channelLayout4.getType());
        Assert.assertNull(channelLayout4.getDecomposition());
        Assert.assertTrue(channelLayout4.invalid);
        Assert.assertNotNull(FFmpeg.Implements.ChannelLayout.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
    /**
     * Helper method for JUnit test of Implements for Color.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.Implements.Color
     */
    private void testImplementsColor() throws Exception {
        Assert.assertEquals("Implement", FFmpeg.Implements.Color.class.getSuperclass().getSimpleName());
        
        FFmpeg.Implements.Color color1;
        FFmpeg.Implements.Color color2;
        FFmpeg.Implements.Color color3;
        FFmpeg.Implements.Color color4;
        
        //constants
        Assert.assertEquals("^\\s*(?<name>[^\\s]+)\\s+(?<hexCode>#[0-f]+)\\s*$",
                FFmpeg.Implements.Color.COLOR_LINE_PATTERN.pattern());
        
        //constructors
        color1 = new FFmpeg.Implements.Color("test1      #ffebcd", "");
        Assert.assertNotNull(color1);
        color2 = new FFmpeg.Implements.Color("test-2     #6495ed", "");
        Assert.assertNotNull(color2);
        color3 = new FFmpeg.Implements.Color("test:3     #228b22", "");
        Assert.assertNotNull(color3);
        color4 = new FFmpeg.Implements.Color("test_4     #dcdcdc", "");
        Assert.assertNotNull(color4);
        
        //getters
        Assert.assertEquals("test1", color1.getName());
        Assert.assertNull(color1.getDescription());
        Assert.assertNull(color1.getType());
        Assert.assertEquals("#ffebcd", color1.getHexCode());
        Assert.assertEquals("test-2", color2.getName());
        Assert.assertNull(color2.getDescription());
        Assert.assertNull(color2.getType());
        Assert.assertEquals("#6495ed", color2.getHexCode());
        Assert.assertEquals("test:3", color3.getName());
        Assert.assertNull(color3.getDescription());
        Assert.assertNull(color3.getType());
        Assert.assertEquals("#228b22", color3.getHexCode());
        Assert.assertEquals("test_4", color4.getName());
        Assert.assertNull(color4.getDescription());
        Assert.assertNull(color4.getType());
        Assert.assertEquals("#dcdcdc", color4.getHexCode());
        Assert.assertNotNull(FFmpeg.Implements.Color.class.getDeclaredMethod("getImplementLinePattern"));
    }
    
}
