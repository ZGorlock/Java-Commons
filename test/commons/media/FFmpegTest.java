/*
 * File:    FFmpegTest.java
 * Package: commons.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import commons.access.CmdLine;
import commons.access.Filesystem;
import commons.access.Project;
import commons.console.Console;
import commons.console.ProgressBar;
import commons.log.CommonsLogging;
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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of FFmpeg.
 *
 * @see FFmpeg
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.crypto.*", "javax.swing.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({FFmpeg.class, CmdLine.class, CommonsLogging.class})
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
        final String sampleNegativeResponse = "\"" + testExecutable.getAbsolutePath() + "\" is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffmpeg -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -version"))
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
        final String sampleNegativeResponse = "\"" + testExecutable.getAbsolutePath() + "\" is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffprobe -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -version"))
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
        final String sampleNegativeResponse = "\"" + testExecutable.getAbsolutePath() + "\" is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffplay -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -version"))
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
        final String sampleNegativeResponse = "\"" + testExecutable.getAbsolutePath() + "\" is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffmpeg -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -version"))
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
        final String sampleNegativeResponse = "\"" + testExecutable.getAbsolutePath() + "\" is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffprobe -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -version"))
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
        final String sampleNegativeResponse = "\"" + testExecutable.getAbsolutePath() + "\" is not recognized as an internal or external command, operable program or batch file.";
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("ffplay -version")).thenReturn(sampleResponse);
        PowerMockito.when(CmdLine.class, "executeCmd", ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -version"))
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
        final FFmpeg.FFmpegProgressBar mockFFmpegProgressBar = Mockito.mock(FFmpeg.FFmpegProgressBar.class);
        
        //standard
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //standard, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\"", mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\"", null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\"", mockFFmpegProgressBar);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.eq(mockFFmpegProgressBar));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\"", null);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -v quiet -progress - -nostats -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -progress - -nostats -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files, default progress bar off
        PowerMockito.doReturn(false).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.doReturn(true).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        
        //files, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files, input params, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -v quiet -progress - -nostats -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -progress - -nostats -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files, input params, default progress bar off
        PowerMockito.doReturn(false).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.doReturn(true).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        
        //file list
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -v quiet -progress - -nostats -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -progress - -nostats -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list, default progress bar off
        PowerMockito.doReturn(false).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.doReturn(true).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        
        //file list, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list, input params, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -v quiet -progress - -nostats -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -progress - -nostats -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list, input params, default progress bar off
        PowerMockito.doReturn(false).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.doReturn(true).when(CommonsLogging.class, "showFfmpegProgressBarsByDefault");
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
        
        //standard
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync("-y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync("-y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpegAsync("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpegAsync("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
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
        FFmpeg.ffprobe("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobe("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobe("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobe("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        
        //file
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobe(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobe(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner \"" + testFile1.getAbsolutePath() + "\""));
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
        FFmpeg.ffprobeAsync("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffprobe -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobeAsync("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobeAsync("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobeAsync("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0:e=none \"" + testFile1.getAbsolutePath() + "\""));
        
        //file
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobeAsync(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffprobe -hide_banner \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobeAsync(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner \"" + testFile1.getAbsolutePath() + "\""));
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
        FFmpeg.ffplay("-autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffplay -autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplay("-autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\""));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplay("-autoexit -nodisp", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffplay -hide_banner -autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplay("-autoexit -nodisp", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\""));
        
        //file
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplay(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffplay -hide_banner \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplay(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner \"" + testFile1.getAbsolutePath() + "\""));
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
        FFmpeg.ffplayAsync("-autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffplay -autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplayAsync("-autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\""));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplayAsync("-autoexit -nodisp", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffplay -hide_banner -autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplayAsync("-autoexit -nodisp", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -autoexit -nodisp \"" + testFile1.getAbsolutePath() + "\""));
        
        //file
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(null);
        FFmpeg.ffplayAsync(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffplay -hide_banner \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFplayExecutable(testExecutable);
        FFmpeg.ffplayAsync(testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner \"" + testFile1.getAbsolutePath() + "\""));
    }
    
    /**
     * JUnit test of setFFmpegExecutable.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#setFFmpegExecutable(File)
     */
    @Test
    public void testSetFFmpegExecutable() throws Exception {
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffmpeg"));
        FFmpeg.setFFmpegExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffmpeg")).getAbsolutePath());
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffmpeg")).getAbsolutePath());
    }
    
    /**
     * JUnit test of setFFprobeExecutable.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#setFFprobeExecutable(File)
     */
    @Test
    public void testSetFFprobeExecutable() throws Exception {
        final File testExecutable = new File(testResources, "ffprobe.exe");
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffprobe"));
        FFmpeg.setFFprobeExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffprobe")).getAbsolutePath());
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffprobe")).getAbsolutePath());
    }
    
    /**
     * JUnit test of setFFplayExecutable.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#setFFplayExecutable(File)
     */
    @Test
    public void testSetFFplayExecutable() throws Exception {
        final File testExecutable = new File(testResources, "ffplay.exe");
        Assert.assertNull(TestUtils.getField(FFmpeg.class, "ffplay"));
        FFmpeg.setFFplayExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffplay")).getAbsolutePath());
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffplay")).getAbsolutePath());
    }
    
    /**
     * JUnit test of getMetadata.
     *
     * @throws Exception When there is an exception.
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
        FFmpeg.Metadata result;
        
        //standard
        result = FFmpeg.getMetadata(testVideo);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getFormat());
        Assert.assertNotNull(result.getStreams());
        Assert.assertNotNull(result.getChapters());
        Assert.assertEquals(((JSONObject) new JSONParser().parse(Filesystem.readFileToString(new File(testResources, "test.mkv.json")))).toJSONString(),
                result.getData().toJSONString().replace(testVideo.getAbsolutePath().replace("\\", "\\\\"), testVideo.getName()));
        Assert.assertEquals(FFmpeg.getFormatMetadata(testVideo).getData().toJSONString(), result.getFormat().getData().toJSONString());
        Assert.assertEquals(FFmpeg.getStreams(testVideo).stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")),
                result.getStreams().stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")));
        Assert.assertEquals(FFmpeg.getChapters(testVideo).stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")),
                result.getChapters().stream().map(e -> e.getData().toJSONString()).collect(Collectors.joining(",", "[", "]")));
        
        //invalid
        Assert.assertNull(FFmpeg.getMetadata(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getMetadata(null));
    }
    
    /**
     * JUnit test of getFormatMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getFormatMetadata(File)
     */
    @Test
    public void testGetFormatMetadata() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        FFmpeg.FormatMetadata metadata;
        
        //standard
        metadata = FFmpeg.getFormatMetadata(testVideo);
        Assert.assertNotNull(metadata);
        Assert.assertEquals(testVideo.getAbsolutePath(), metadata.getMediaFile().getAbsolutePath());
        Assert.assertEquals(3067000L, metadata.getDurationExact());
        Assert.assertEquals(-5000L, metadata.getStartTimeExact());
        Assert.assertEquals(3062000L, metadata.getEndTimeExact());
        Assert.assertEquals(3067L, metadata.getDuration());
        Assert.assertEquals(-5L, metadata.getStartTime());
        Assert.assertEquals(3062L, metadata.getEndTime());
        Assert.assertEquals(565905L, metadata.getSize());
        Assert.assertEquals(1476113L, metadata.getBitrate());
        Assert.assertEquals("matroska,webm", metadata.getFormatName());
        Assert.assertEquals("Matroska / WebM", metadata.getFormatNameLong());
        Assert.assertEquals("FFmpeg Test Video", metadata.getTitle());
        Assert.assertEquals(15, metadata.getStreamCount());
        Assert.assertEquals(0, metadata.getProgramCount());
        Assert.assertEquals(2, metadata.getTags().size());
        Assert.assertEquals("Lavf58.45.100", metadata.getTag("encoder"));
        Assert.assertEquals("FFmpeg Test Video", metadata.getTag("title"));
        
        //invalid
        Assert.assertNull(FFmpeg.getFormatMetadata(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getFormatMetadata(null));
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
        List<FFmpeg.StreamMetadata> result;
        FFmpeg.StreamMetadata stream;
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        //standard
        result = FFmpeg.getStreams(testVideo);
        Assert.assertNotNull(result);
        Assert.assertEquals(15, result.size());
        stream = result.get(0);
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
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
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, stream.getStreamType());
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
        result = FFmpeg.getStreams(testVideo2);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        stream = result.get(0);
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, stream.getStreamType());
        Assert.assertEquals("h264", stream.getCodecName());
        Assert.assertEquals("H.264 / AVC / MPEG-4 AVC / MPEG-4 part 10", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertNull(stream.getTitle());
        Assert.assertEquals(5L, stream.getStartTime());
        Assert.assertEquals(3028L, stream.getEndTime());
        Assert.assertEquals(3023L, stream.getDuration());
        stream = result.get(1);
        Assert.assertEquals(FFmpeg.StreamType.DATA, stream.getStreamType());
        Assert.assertEquals("bin_data", stream.getCodecName());
        Assert.assertEquals("binary data", stream.getCodecNameLong());
        Assert.assertEquals("eng", stream.getLanguage());
        Assert.assertNull(stream.getTitle());
        Assert.assertEquals(0L, stream.getStartTime());
        Assert.assertEquals(3004L, stream.getEndTime());
        Assert.assertEquals(3004L, stream.getDuration());
        
        //invalid
        Assert.assertNull(FFmpeg.getStreams(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreams(null));
    }
    
    /**
     * JUnit test of getStream.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getStream(File, FFmpeg.StreamType, int)
     * @see FFmpeg#getStream(File, FFmpeg.StreamType)
     * @see FFmpeg#getStream(File, int)
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
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, FFmpeg.StreamType.VIDEO, 0).getTitle());
        Assert.assertEquals("Green", FFmpeg.getStream(testVideo, FFmpeg.StreamType.VIDEO, 1).getTitle());
        Assert.assertEquals("Blue", FFmpeg.getStream(testVideo, FFmpeg.StreamType.VIDEO, 2).getTitle());
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO, 0).getTag("description"));
        Assert.assertEquals("AAC Audio", FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO, 1).getTag("description"));
        Assert.assertEquals("FLAC Audio", FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO, 2).getTag("description"));
        Assert.assertEquals("M4A Audio", FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO, 3).getTag("description"));
        Assert.assertEquals("OPUS Audio", FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO, 4).getTag("description"));
        Assert.assertEquals("OGG Audio", FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO, 5).getTag("description"));
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE, 0).getTitle());
        Assert.assertEquals("Spanish", FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE, 1).getTitle());
        Assert.assertEquals("Russian", FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE, 2).getTitle());
        Assert.assertEquals("Japanese", FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE, 3).getTitle());
        Assert.assertEquals("French", FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE, 4).getTitle());
        Assert.assertEquals("German", FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE, 5).getTitle());
        Assert.assertEquals("bin_data", FFmpeg.getStream(testVideo2, FFmpeg.StreamType.DATA, 0).getCodecName());
        
        //stream type
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, FFmpeg.StreamType.VIDEO).getTitle());
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, FFmpeg.StreamType.VIDEO).getTitle());
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO).getTag("description"));
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO).getTag("description"));
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE).getTitle());
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE).getTitle());
        Assert.assertEquals("bin_data", FFmpeg.getStream(testVideo2, FFmpeg.StreamType.DATA).getCodecName());
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, null).getTitle());
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, null).getTitle());
        
        //null stream type
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, null, 0).getTitle());
        Assert.assertEquals("Green", FFmpeg.getStream(testVideo, null, 1).getTitle());
        Assert.assertEquals("Blue", FFmpeg.getStream(testVideo, null, 2).getTitle());
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, null, 3).getTag("description"));
        Assert.assertEquals("AAC Audio", FFmpeg.getStream(testVideo, null, 4).getTag("description"));
        Assert.assertEquals("FLAC Audio", FFmpeg.getStream(testVideo, null, 5).getTag("description"));
        Assert.assertEquals("M4A Audio", FFmpeg.getStream(testVideo, null, 6).getTag("description"));
        Assert.assertEquals("OPUS Audio", FFmpeg.getStream(testVideo, null, 7).getTag("description"));
        Assert.assertEquals("OGG Audio", FFmpeg.getStream(testVideo, null, 8).getTag("description"));
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, null, 9).getTitle());
        Assert.assertEquals("Spanish", FFmpeg.getStream(testVideo, null, 10).getTitle());
        Assert.assertEquals("Russian", FFmpeg.getStream(testVideo, null, 11).getTitle());
        Assert.assertEquals("Japanese", FFmpeg.getStream(testVideo, null, 12).getTitle());
        Assert.assertEquals("French", FFmpeg.getStream(testVideo, null, 13).getTitle());
        Assert.assertEquals("German", FFmpeg.getStream(testVideo, null, 14).getTitle());
        Assert.assertEquals("bin_data", FFmpeg.getStream(testVideo2, null, 1).getCodecName());
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, null).getTitle());
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, null).getTitle());
        
        //index
        Assert.assertEquals("Red", FFmpeg.getStream(testVideo, 0).getTitle());
        Assert.assertEquals("Green", FFmpeg.getStream(testVideo, 1).getTitle());
        Assert.assertEquals("Blue", FFmpeg.getStream(testVideo, 2).getTitle());
        Assert.assertEquals("MP3 Audio", FFmpeg.getStream(testVideo, 3).getTag("description"));
        Assert.assertEquals("AAC Audio", FFmpeg.getStream(testVideo, 4).getTag("description"));
        Assert.assertEquals("FLAC Audio", FFmpeg.getStream(testVideo, 5).getTag("description"));
        Assert.assertEquals("M4A Audio", FFmpeg.getStream(testVideo, 6).getTag("description"));
        Assert.assertEquals("OPUS Audio", FFmpeg.getStream(testVideo, 7).getTag("description"));
        Assert.assertEquals("OGG Audio", FFmpeg.getStream(testVideo, 8).getTag("description"));
        Assert.assertEquals("English", FFmpeg.getStream(testVideo, 9).getTitle());
        Assert.assertEquals("Spanish", FFmpeg.getStream(testVideo, 10).getTitle());
        Assert.assertEquals("Russian", FFmpeg.getStream(testVideo, 11).getTitle());
        Assert.assertEquals("Japanese", FFmpeg.getStream(testVideo, 12).getTitle());
        Assert.assertEquals("French", FFmpeg.getStream(testVideo, 13).getTitle());
        Assert.assertEquals("German", FFmpeg.getStream(testVideo, 14).getTitle());
        Assert.assertEquals("bin_data", FFmpeg.getStream(testVideo2, 1).getCodecName());
        
        //invalid
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.StreamType.VIDEO, 3));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.StreamType.VIDEO, -1));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO, 6));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO, -1));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE, 6));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE, -1));
        Assert.assertNull(FFmpeg.getStream(testVideo, FFmpeg.StreamType.DATA));
        Assert.assertNull(FFmpeg.getStream(testVideo, null, 15));
        Assert.assertNull(FFmpeg.getStream(testVideo, null, -1));
        Assert.assertNull(FFmpeg.getStream(testVideo, 15));
        Assert.assertNull(FFmpeg.getStream(testVideo, -1));
        Assert.assertNull(FFmpeg.getStream(fakeVideo, FFmpeg.StreamType.VIDEO, 0));
        Assert.assertNull(FFmpeg.getStream(fakeVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertNull(FFmpeg.getStream(fakeVideo, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStream(null, FFmpeg.StreamType.SUBTITLE, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStream(null, FFmpeg.StreamType.SUBTITLE));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStream(null, 1));
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
        List<FFmpeg.ChapterMetadata> result;
        FFmpeg.ChapterMetadata chapter;
        
        //standard
        result = FFmpeg.getChapters(testVideo);
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
     * JUnit test of getFormat.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getFormat(File, boolean)
     * @see FFmpeg#getFormat(File)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testGetFormat() throws Exception {
        if (!FFmpeg.ffprobeExists()) {
            logger.warn("ffprobe is not installed... skipping test");
            return;
        }
        
        final File testVideo = new File(testResources, "test.mkv");
        final File fakeVideo = new File(testResources, "fakeTest.mp4");
        
        //standard
        Assert.assertEquals("matroska,webm", FFmpeg.getFormat(testVideo, true));
        Assert.assertEquals("Matroska / WebM", FFmpeg.getFormat(testVideo, false));
        
        //default abbreviate
        Assert.assertEquals("Matroska / WebM", FFmpeg.getFormat(testVideo));
        
        //invalid
        Assert.assertNull(FFmpeg.getFormat(fakeVideo));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getFormat(null));
    }
    
    /**
     * JUnit test of getDuration.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getDuration(File)
     */
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
     * JUnit test of getStreamDuration.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getStreamDuration(File, FFmpeg.StreamType, int)
     * @see FFmpeg#getStreamDuration(File, FFmpeg.StreamType)
     * @see FFmpeg#getStreamDuration(File, int)
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
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.VIDEO, 0));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.VIDEO, 1));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.VIDEO, 2));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO, 0));
        Assert.assertEquals(3065L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO, 1));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO, 2));
        Assert.assertEquals(3041L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO, 3));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO, 4));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO, 5));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE, 0));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE, 1));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE, 2));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE, 3));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE, 4));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE, 5));
        
        //stream type
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null));
        
        //null stream type
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null, 0));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null, 1));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null, 2));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, null, 3));
        Assert.assertEquals(3065L, FFmpeg.getStreamDuration(testVideo, null, 4));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null, 5));
        Assert.assertEquals(3041L, FFmpeg.getStreamDuration(testVideo, null, 6));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null, 7));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null, 8));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, null, 9));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, null, 10));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, null, 11));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, null, 12));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, null, 13));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, null, 14));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, null));
        
        //index
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, 0));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, 1));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, 2));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, 3));
        Assert.assertEquals(3065L, FFmpeg.getStreamDuration(testVideo, 4));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, 5));
        Assert.assertEquals(3041L, FFmpeg.getStreamDuration(testVideo, 6));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, 7));
        Assert.assertEquals(3023L, FFmpeg.getStreamDuration(testVideo, 8));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, 9));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, 10));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, 11));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, 12));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, 13));
        Assert.assertEquals(3067L, FFmpeg.getStreamDuration(testVideo, 14));
        
        //invalid
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.VIDEO, 3));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.VIDEO, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO, 6));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.AUDIO, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE, 6));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.SUBTITLE, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, FFmpeg.StreamType.DATA));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, null, 15));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, null, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, 15));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(testVideo, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(fakeVideo, FFmpeg.StreamType.VIDEO, 0));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(fakeVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(-1L, FFmpeg.getStreamDuration(fakeVideo, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamDuration(null, FFmpeg.StreamType.SUBTITLE, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamDuration(null, FFmpeg.StreamType.SUBTITLE));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamDuration(null, 1));
    }
    
    /**
     * JUnit test of getStreamBitrate.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getStreamBitrate(File, FFmpeg.StreamType, int)
     * @see FFmpeg#getStreamBitrate(File, FFmpeg.StreamType)
     * @see FFmpeg#getStreamBitrate(File, int)
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
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.VIDEO, 0));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.VIDEO, 1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.VIDEO, 2));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO, 0));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO, 1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO, 2));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO, 3));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO, 4));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO, 5));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE, 0));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE, 1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE, 2));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE, 3));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE, 4));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE, 5));
        
        //stream type
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null));
        
        //null stream type
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 0));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 2));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, null, 3));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 4));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 5));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 6));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 7));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 8));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 9));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 10));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 11));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 12));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 13));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 14));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null));
        
        //index
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 0));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 2));
        Assert.assertEquals(128000L, FFmpeg.getStreamBitrate(testVideo, 3));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 4));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 5));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 6));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 7));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 8));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 9));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 10));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 11));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 12));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 13));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 14));
        
        //invalid
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.VIDEO, 3));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.VIDEO, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO, 6));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.AUDIO, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE, 6));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.SUBTITLE, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, FFmpeg.StreamType.DATA));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, 15));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, null, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, 15));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(testVideo, -1));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(fakeVideo, FFmpeg.StreamType.VIDEO, 0));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(fakeVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(-1L, FFmpeg.getStreamBitrate(fakeVideo, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamBitrate(null, FFmpeg.StreamType.SUBTITLE, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamBitrate(null, FFmpeg.StreamType.SUBTITLE));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getStreamBitrate(null, 1));
    }
    
    /**
     * JUnit test of getEncoding.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getEncoding(File, FFmpeg.StreamType, int)
     * @see FFmpeg#getEncoding(File, FFmpeg.StreamType)
     * @see FFmpeg#getEncoding(File, int)
     */
    @Test
    public void testGetEncoding() throws Exception {
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
        Assert.assertEquals("h264", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.VIDEO, 0));
        Assert.assertEquals("hevc", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.VIDEO, 1));
        Assert.assertEquals("mpeg4", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.VIDEO, 2));
        Assert.assertEquals("mp3", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO, 0));
        Assert.assertEquals("aac", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO, 1));
        Assert.assertEquals("flac", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO, 2));
        Assert.assertEquals("aac", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO, 3));
        Assert.assertEquals("opus", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO, 4));
        Assert.assertEquals("vorbis", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO, 5));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE, 0));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE, 1));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE, 2));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE, 3));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE, 4));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE, 5));
        Assert.assertEquals("bin_data", FFmpeg.getEncoding(testVideo2, FFmpeg.StreamType.DATA, 0));
        
        //stream type
        Assert.assertEquals("h264", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals("h264", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals("mp3", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO));
        Assert.assertEquals("mp3", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals("bin_data", FFmpeg.getEncoding(testVideo2, FFmpeg.StreamType.DATA));
        Assert.assertEquals("h264", FFmpeg.getEncoding(testVideo, null));
        Assert.assertEquals("h264", FFmpeg.getEncoding(testVideo, null));
        
        //null stream type
        Assert.assertEquals("h264", FFmpeg.getEncoding(testVideo, null, 0));
        Assert.assertEquals("hevc", FFmpeg.getEncoding(testVideo, null, 1));
        Assert.assertEquals("mpeg4", FFmpeg.getEncoding(testVideo, null, 2));
        Assert.assertEquals("mp3", FFmpeg.getEncoding(testVideo, null, 3));
        Assert.assertEquals("aac", FFmpeg.getEncoding(testVideo, null, 4));
        Assert.assertEquals("flac", FFmpeg.getEncoding(testVideo, null, 5));
        Assert.assertEquals("aac", FFmpeg.getEncoding(testVideo, null, 6));
        Assert.assertEquals("opus", FFmpeg.getEncoding(testVideo, null, 7));
        Assert.assertEquals("vorbis", FFmpeg.getEncoding(testVideo, null, 8));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, null, 9));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, null, 10));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, null, 11));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, null, 12));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, null, 13));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, null, 14));
        Assert.assertEquals("bin_data", FFmpeg.getEncoding(testVideo2, null, 1));
        Assert.assertEquals("h264", FFmpeg.getEncoding(testVideo, null));
        Assert.assertEquals("h264", FFmpeg.getEncoding(testVideo, null));
        
        //index
        Assert.assertEquals("h264", FFmpeg.getEncoding(testVideo, 0));
        Assert.assertEquals("hevc", FFmpeg.getEncoding(testVideo, 1));
        Assert.assertEquals("mpeg4", FFmpeg.getEncoding(testVideo, 2));
        Assert.assertEquals("mp3", FFmpeg.getEncoding(testVideo, 3));
        Assert.assertEquals("aac", FFmpeg.getEncoding(testVideo, 4));
        Assert.assertEquals("flac", FFmpeg.getEncoding(testVideo, 5));
        Assert.assertEquals("aac", FFmpeg.getEncoding(testVideo, 6));
        Assert.assertEquals("opus", FFmpeg.getEncoding(testVideo, 7));
        Assert.assertEquals("vorbis", FFmpeg.getEncoding(testVideo, 8));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, 9));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, 10));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, 11));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, 12));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, 13));
        Assert.assertEquals("subrip", FFmpeg.getEncoding(testVideo, 14));
        Assert.assertEquals("bin_data", FFmpeg.getEncoding(testVideo2, 1));
        
        //invalid
        Assert.assertNull(FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.VIDEO, 3));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.VIDEO, -1));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO, 6));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.AUDIO, -1));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE, 6));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.SUBTITLE, -1));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, FFmpeg.StreamType.DATA));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, null, 15));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, null, -1));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, 15));
        Assert.assertNull(FFmpeg.getEncoding(testVideo, -1));
        Assert.assertNull(FFmpeg.getEncoding(fakeVideo, FFmpeg.StreamType.VIDEO, 0));
        Assert.assertNull(FFmpeg.getEncoding(fakeVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertNull(FFmpeg.getEncoding(fakeVideo, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getEncoding(null, FFmpeg.StreamType.SUBTITLE, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getEncoding(null, FFmpeg.StreamType.SUBTITLE));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getEncoding(null, 1));
    }
    
    /**
     * JUnit test of getFrameCount.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#getFrameCount(File, FFmpeg.StreamType, int)
     * @see FFmpeg#getFrameCount(File, FFmpeg.StreamType)
     * @see FFmpeg#getFrameCount(File, int)
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
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.VIDEO, 0));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.VIDEO, 1));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.VIDEO, 2));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO, 0));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO, 1));
        Assert.assertEquals(29L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO, 2));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO, 3));
        Assert.assertEquals(151L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO, 4));
        Assert.assertEquals(286L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO, 5));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE, 0));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE, 1));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE, 2));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE, 3));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE, 4));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE, 5));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo2, FFmpeg.StreamType.DATA, 0));
        
        //stream type
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo2, FFmpeg.StreamType.DATA));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, null));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, null));
        
        //null stream type
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, null, 0));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, null, 1));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, null, 2));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, null, 3));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, null, 4));
        Assert.assertEquals(29L, FFmpeg.getFrameCount(testVideo, null, 5));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, null, 6));
        Assert.assertEquals(151L, FFmpeg.getFrameCount(testVideo, null, 7));
        Assert.assertEquals(286L, FFmpeg.getFrameCount(testVideo, null, 8));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, null, 9));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, null, 10));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, null, 11));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, null, 12));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, null, 13));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, null, 14));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo2, null, 1));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, null));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, null));
        
        //index
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, 0));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, 1));
        Assert.assertEquals(30L, FFmpeg.getFrameCount(testVideo, 2));
        Assert.assertEquals(117L, FFmpeg.getFrameCount(testVideo, 3));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, 4));
        Assert.assertEquals(29L, FFmpeg.getFrameCount(testVideo, 5));
        Assert.assertEquals(131L, FFmpeg.getFrameCount(testVideo, 6));
        Assert.assertEquals(151L, FFmpeg.getFrameCount(testVideo, 7));
        Assert.assertEquals(286L, FFmpeg.getFrameCount(testVideo, 8));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, 9));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, 10));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, 11));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, 12));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, 13));
        Assert.assertEquals(3L, FFmpeg.getFrameCount(testVideo, 14));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo2, 1));
        
        //invalid
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.VIDEO, 3));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.VIDEO, -1));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO, 6));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.AUDIO, -1));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE, 6));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.SUBTITLE, -1));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, FFmpeg.StreamType.DATA));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, null, 15));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, null, -1));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, 15));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(testVideo, -1));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(fakeVideo, FFmpeg.StreamType.VIDEO, 0));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(fakeVideo, FFmpeg.StreamType.VIDEO));
        Assert.assertEquals(-1L, FFmpeg.getFrameCount(fakeVideo, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getFrameCount(null, FFmpeg.StreamType.SUBTITLE, 0));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getFrameCount(null, FFmpeg.StreamType.SUBTITLE));
        TestUtils.assertException(NullPointerException.class, () ->
                FFmpeg.getFrameCount(null, 1));
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
        
        //constants
        Assert.assertEquals("^\\s*frame=\\s*(?<frame>\\d+)\\s+fps=\\s*(?<fps>[\\d.]+)\\s+q=\\s*(?<q>[\\-\\d.]+)\\s+(?:Lq=\\s*(?<Lq>[\\-\\d.]+)\\s+)?(?:q=\\s*(?<q2>[\\-\\d.]+)\\s+)?(?:Lsize=\\s*(?<Lsize>[\\d.]+kB)\\s+)?time=\\s*(?<time>[\\d:.]+)\\s+bitrate=\\s*(?<bitrate>[\\d.]+kbits/s)\\s+speed=\\s*(?<speed>[\\d.]+x)\\s*$",
                FFmpeg.FFmpegProgressBar.MUXING_PROGRESS_LOG_PATTERN.pattern());
        Assert.assertEquals("^\\s*frame=(?<frame>\\d+)\\s*$",
                FFmpeg.FFmpegProgressBar.MUXING_PROGRESS_PATTERN.pattern());
        Assert.assertEquals("^\\s*video:\\s*(?<video>\\d+kB)\\s+audio:\\s*(?<audio>\\d+kB)\\s+subtitle:\\s*(?<subtitle>\\d+kB)\\s+other\\sstreams:\\s*(?<otherStreams>\\d+kB)\\s+global\\sheaders:\\s*(?<globalHeaders>\\d+kB)\\s+muxing\\soverhead:\\s*(?<muxingOverhead>[\\d.]+%)\\s*$",
                FFmpeg.FFmpegProgressBar.MUXING_COMPLETE_LOG_PATTERN.pattern());
        Assert.assertEquals("^\\s*progress=end\\s*$",
                FFmpeg.FFmpegProgressBar.MUXING_COMPLETE_PATTERN.pattern());
        
        //initialization
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo);
        Assert.assertNotNull(progressBar);
        Assert.assertFalse((boolean) TestUtils.getField(progressBar, "completedNaturally"));
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertEquals("Test FFmpeg Progress Bar", progressBar.getTitle());
        Assert.assertEquals(30L, progressBar.getTotal());
        Assert.assertEquals(0L, progressBar.getProgress());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_WIDTH, progressBar.getWidth());
        Assert.assertEquals("frames", progressBar.getUnits());
        Assert.assertEquals(ProgressBar.DEFAULT_PROGRESS_BAR_AUTO_PRINT, progressBar.getAutoPrint());
        
        //processLog
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo);
        Assert.assertTrue(progressBar.processLog("frame=5"));
        Assert.assertEquals(5L, progressBar.getProgress());
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Assert.assertFalse(progressBar.processLog("progress=continue"));
        Assert.assertEquals(5L, progressBar.getProgress());
        Assert.assertTrue(((List<String>) TestUtils.getField(progressBar, "errors")).isEmpty());
        Thread.sleep(ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 2);
        Assert.assertTrue(progressBar.processLog("frame=10", false));
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
        
        //complete, complete naturally
        progressBar = PowerMockito.spy(new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo));
        Mockito.doNothing().when(progressBar).complete(ArgumentMatchers.anyBoolean());
        Mockito.doNothing().when(progressBar).fail(ArgumentMatchers.anyBoolean());
        Mockito.doNothing().when(progressBar).fail(ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString());
        TestUtils.setField(progressBar, "completedNaturally", true);
        progressBar.complete();
        Mockito.verify(progressBar).complete();
        Mockito.verify(progressBar).complete(ArgumentMatchers.eq(true));
        Mockito.verifyNoMoreInteractions(progressBar);
        
        //complete, fail
        progressBar = PowerMockito.spy(new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo));
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
        progressBar = PowerMockito.spy(new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo));
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
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo);
        for (int i = 5; i <= 30; i += 5) {
            Assert.assertTrue(progressBar.processLog("frame=" + i));
            Assert.assertFalse(progressBar.processLog("progress=continue"));
            Thread.sleep(ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 2);
        }
        Assert.assertTrue(progressBar.processLog("progress=end", false));
        progressBar.complete();
        Assert.assertTrue(progressBar.isComplete());
        Assert.assertFalse(progressBar.isFailed());
        
        //full example, complete
        progressBar = new FFmpeg.FFmpegProgressBar("Test FFmpeg Progress Bar", testVideo);
        for (int i = 5; i <= 20; i += 5) {
            Assert.assertTrue(progressBar.processLog("frame=" + i));
            Assert.assertFalse(progressBar.processLog("progress=continue"));
            Thread.sleep(ProgressBar.PROGRESS_BAR_MINIMUM_UPDATE_DELAY * 2);
        }
        Assert.assertFalse(progressBar.processLog("Conversion failed", true));
        progressBar.complete();
        Assert.assertFalse(progressBar.isComplete());
        Assert.assertTrue(progressBar.isFailed());
    }
    
    /**
     * JUnit test of MetadataBase.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.MetadataBase
     */
    @Test
    public void testMetadataBase() throws Exception {
        //class
        Class<?> metadataBase = Arrays.stream(FFmpeg.class.getDeclaredClasses())
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
        Assert.assertEquals(metadataBase.getSimpleName(), FFmpeg.FormatMetadata.class.getSuperclass().getSimpleName());
        Assert.assertEquals(metadataBase.getSimpleName(), FFmpeg.StreamMetadata.class.getSuperclass().getSimpleName());
        Assert.assertEquals(metadataBase.getSimpleName(), FFmpeg.ChapterMetadata.class.getSuperclass().getSimpleName());
        Assert.assertNotEquals(metadataBase.getSimpleName(), FFmpeg.StreamMetadata.VideoStreamMetadata.class.getSuperclass().getSimpleName());
        Assert.assertNotEquals(metadataBase.getSimpleName(), FFmpeg.StreamMetadata.AudioStreamMetadata.class.getSuperclass().getSimpleName());
        Assert.assertNotEquals(metadataBase.getSimpleName(), FFmpeg.StreamMetadata.SubtitleStreamMetadata.class.getSuperclass().getSimpleName());
        Assert.assertNotEquals(metadataBase.getSimpleName(), FFmpeg.Metadata.class.getSuperclass().getSimpleName());
    }
    
    /**
     * JUnit test of FormatMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.FormatMetadata
     */
    @Test
    public void testFormatMetadata() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.FormatMetadata metadata = FFmpeg.getFormatMetadata(testVideo);
        Assert.assertNotNull(metadata);
        Assert.assertEquals("MetadataBase", FFmpeg.FormatMetadata.class.getSuperclass().getSimpleName());
        
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
        Assert.assertEquals(testVideo.getAbsolutePath(), metadata.getMediaFile().getAbsolutePath());
        Assert.assertEquals(565905L, metadata.getSize());
        Assert.assertEquals(1476113L, metadata.getBitrate());
        Assert.assertEquals("matroska,webm", metadata.getFormatName());
        Assert.assertEquals("Matroska / WebM", metadata.getFormatNameLong());
        Assert.assertEquals(15, metadata.getStreamCount());
        Assert.assertEquals(0, metadata.getProgramCount());
    }
    
    /**
     * JUnit test of StreamMetadata.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg.StreamMetadata
     * @see FFmpeg.StreamMetadata.VideoStreamMetadata
     * @see FFmpeg.StreamMetadata.AudioStreamMetadata
     * @see FFmpeg.StreamMetadata.SubtitleStreamMetadata
     * @see FFmpeg.StreamMetadata.DataStreamMetadata
     */
    @Test
    public void testStreamMetadata() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final File testVideo2 = new File(Filesystem.createTemporaryDirectory(), "test.mp4");
        FFmpeg.ffmpeg(testVideo, "-y -map 0:0 -c copy -map_chapters 0", testVideo2);
        Assert.assertTrue(testVideo2.exists());
        Assert.assertFalse(Filesystem.isEmpty(testVideo2));
        
        final FFmpeg.StreamMetadata metadataVideo = FFmpeg.getStream(testVideo, FFmpeg.StreamType.VIDEO, 0);
        final FFmpeg.StreamMetadata metadataAudio = FFmpeg.getStream(testVideo, FFmpeg.StreamType.AUDIO, 2);
        final FFmpeg.StreamMetadata metadataSubtitle = FFmpeg.getStream(testVideo, FFmpeg.StreamType.SUBTITLE, 4);
        final FFmpeg.StreamMetadata metadataData = FFmpeg.getStream(testVideo2, FFmpeg.StreamType.DATA, 0);
        Assert.assertNotNull(metadataVideo);
        Assert.assertNotNull(metadataAudio);
        Assert.assertNotNull(metadataSubtitle);
        Assert.assertNotNull(metadataData);
        Assert.assertEquals("MetadataBase", FFmpeg.StreamMetadata.class.getSuperclass().getSimpleName());
        
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
        Assert.assertEquals(FFmpeg.StreamType.VIDEO, metadataVideo.getStreamType());
        Assert.assertEquals(0, metadataVideo.getStreamIndex());
        Assert.assertTrue(metadataVideo.isDefaultStream());
        Assert.assertEquals("h264", metadataVideo.getCodecName());
        Assert.assertEquals("H.264 / AVC / MPEG-4 AVC / MPEG-4 part 10", metadataVideo.getCodecNameLong());
        Assert.assertEquals(10.0, metadataVideo.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(10.0, metadataVideo.getFrameRateAverage(), TestUtils.DELTA);
        Assert.assertEquals(-1L, metadataVideo.getBitrate());
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
        Assert.assertEquals(-1, metadataVideo.getAudioMetadata().getChannels());
        Assert.assertNull(metadataVideo.getAudioMetadata().getChannelLayout());
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
        Assert.assertEquals(FFmpeg.StreamType.AUDIO, metadataAudio.getStreamType());
        Assert.assertEquals(5, metadataAudio.getStreamIndex());
        Assert.assertFalse(metadataAudio.isDefaultStream());
        Assert.assertEquals("flac", metadataAudio.getCodecName());
        Assert.assertEquals("FLAC (Free Lossless Audio Codec)", metadataAudio.getCodecNameLong());
        Assert.assertEquals(0.0, metadataAudio.getFrameRateBase(), TestUtils.DELTA);
        Assert.assertEquals(0.0, metadataAudio.getFrameRateAverage(), TestUtils.DELTA);
        Assert.assertEquals(-1L, metadataAudio.getBitrate());
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
        Assert.assertEquals(2, metadataAudio.getAudioMetadata().getChannels());
        Assert.assertEquals("stereo", metadataAudio.getAudioMetadata().getChannelLayout());
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
        Assert.assertEquals(FFmpeg.StreamType.SUBTITLE, metadataSubtitle.getStreamType());
        Assert.assertEquals(13, metadataSubtitle.getStreamIndex());
        Assert.assertFalse(metadataSubtitle.isDefaultStream());
        Assert.assertEquals("subrip", metadataSubtitle.getCodecName());
        Assert.assertEquals("SubRip subtitle", metadataSubtitle.getCodecNameLong());
        Assert.assertEquals(-1L, metadataSubtitle.getBitrate());
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
        Assert.assertEquals(-1, metadataSubtitle.getAudioMetadata().getChannels());
        Assert.assertNull(metadataSubtitle.getAudioMetadata().getChannelLayout());
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
        Assert.assertEquals(FFmpeg.StreamType.DATA, metadataData.getStreamType());
        Assert.assertEquals(1, metadataData.getStreamIndex());
        Assert.assertFalse(metadataData.isDefaultStream());
        Assert.assertEquals("bin_data", metadataData.getCodecName());
        Assert.assertEquals("binary data", metadataData.getCodecNameLong());
        Assert.assertEquals(215L, metadataData.getBitrate());
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
        Assert.assertEquals(-1, metadataData.getAudioMetadata().getChannels());
        Assert.assertNull(metadataData.getAudioMetadata().getChannelLayout());
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
     * @see FFmpeg.ChapterMetadata
     */
    @Test
    public void testChapterMetadata() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.ChapterMetadata metadata = FFmpeg.getChapter(testVideo, 1);
        Assert.assertNotNull(metadata);
        Assert.assertEquals("MetadataBase", FFmpeg.ChapterMetadata.class.getSuperclass().getSimpleName());
        
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
     * @see FFmpeg.Metadata
     */
    @Test
    public void testMetadata() throws Exception {
        final File testVideo = new File(testResources, "test.mkv");
        final FFmpeg.Metadata metadata = FFmpeg.getMetadata(testVideo);
        Assert.assertNotNull(metadata);
        Assert.assertNotEquals("MetadataBase", FFmpeg.Metadata.class.getSuperclass().getSimpleName());
        
        //base
        Assert.assertNotNull(metadata.getData());
        Assert.assertNotNull(metadata.getFormat());
        Assert.assertNotNull(metadata.getStreams());
        Assert.assertNotNull(metadata.getChapters());
        Assert.assertEquals(15, metadata.getStreams().size());
        Assert.assertEquals(3, metadata.getChapters().size());
    }
    
}
