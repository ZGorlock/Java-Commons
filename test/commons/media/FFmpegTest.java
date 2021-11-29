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

import commons.access.CmdLine;
import commons.access.Project;
import commons.console.Console;
import commons.console.ProgressBar;
import commons.test.TestUtils;
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
@SuppressWarnings("SpellCheckingInspection")
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.crypto.*", "javax.swing.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({FFmpeg.class, CmdLine.class})
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
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -v quiet -progress - -nostats -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -progress - -nostats -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(testFile1, "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //files, input params, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -v quiet -progress - -nostats -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -progress - -nostats -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", testFile1, "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -v quiet -progress - -nostats -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -progress - -nostats -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg(Arrays.asList(testFile1, testFile2, testFile3), "-y -c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -y -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list, input params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        
        //file list, input params, progress bar
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(null);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -v quiet -progress - -nostats -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFmpegExecutable(testExecutable);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, true);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -progress - -nostats -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""), ArgumentMatchers.any(FFmpeg.FFmpegProgressBar.class));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.ffmpeg("-y", Arrays.asList(testFile1, testFile2, testFile3), "-c:copy", testFile4, false);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -y -i \"" + testFile1.getAbsolutePath() + "\" -i \"" + testFile2.getAbsolutePath() + "\" -i \"" + testFile3.getAbsolutePath() + "\" -c:copy \"" + testFile4.getAbsolutePath() + "\""));
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
        FFmpeg.ffprobe("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobe("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobe("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobe("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        
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
        FFmpeg.ffprobeAsync("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffprobe -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobeAsync("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        
        //file and params
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(null);
        FFmpeg.ffprobeAsync("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffprobe -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        PowerMockito.mockStatic(CmdLine.class);
        FFmpeg.setFFprobeExecutable(testExecutable);
        FFmpeg.ffprobeAsync("-v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0", testFile1);
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -hide_banner -v quiet -select_streams v:0 -count_packets -show_entries stream=nb_read_packets -of csv=p=0 \"" + testFile1.getAbsolutePath() + "\""));
        
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
    
}
