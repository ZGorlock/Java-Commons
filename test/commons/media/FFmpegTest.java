/*
 * File:    FFmpegTest.java
 * Package: commons.media
 * Author:  Zachary Gill
 */

package commons.media;

import java.io.File;

import commons.access.CmdLine;
import commons.access.Project;
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
     * @see FFmpeg#ffmpeg(String)
     */
    @Test
    public void testFFmpeg() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        FFmpeg.ffmpeg("-y -i test.mp4 -c:copy \"out\\test2.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffmpeg -y -i test.mp4 -c:copy \"out\\test2.mp4\""));
        FFmpeg.setFFmpegExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffmpeg")).getAbsolutePath());
        FFmpeg.ffmpeg("-y -i test.mp4 -c:copy \"out\\test.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i test.mp4 -c:copy \"out\\test.mp4\""));
    }
    
    /**
     * JUnit test of ffmpegAsync.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffmpegAsync(String)
     */
    @Test
    public void testFFmpegAsync() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffmpeg.exe");
        FFmpeg.ffmpegAsync("-y -i test.mp4 -c:copy \"out\\test2.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffmpeg -y -i test.mp4 -c:copy \"out\\test2.mp4\""));
        FFmpeg.setFFmpegExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffmpeg")).getAbsolutePath());
        FFmpeg.ffmpegAsync("-y -i test.mp4 -c:copy \"out\\test.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i test.mp4 -c:copy \"out\\test.mp4\""));
    }
    
    /**
     * JUnit test of ffprobe.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffprobe(String)
     */
    @Test
    public void testFFprobe() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffprobe.exe");
        FFmpeg.ffprobe("-y -i test.mp4 -c:copy \"out\\test2.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffprobe -y -i test.mp4 -c:copy \"out\\test2.mp4\""));
        FFmpeg.setFFprobeExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffprobe")).getAbsolutePath());
        FFmpeg.ffprobe("-y -i test.mp4 -c:copy \"out\\test.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i test.mp4 -c:copy \"out\\test.mp4\""));
    }
    
    /**
     * JUnit test of ffprobeAsync.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffprobeAsync(String)
     */
    @Test
    public void testFFprobeAsync() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffprobe.exe");
        FFmpeg.ffprobeAsync("-y -i test.mp4 -c:copy \"out\\test2.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffprobe -y -i test.mp4 -c:copy \"out\\test2.mp4\""));
        FFmpeg.setFFprobeExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffprobe")).getAbsolutePath());
        FFmpeg.ffprobeAsync("-y -i test.mp4 -c:copy \"out\\test.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i test.mp4 -c:copy \"out\\test.mp4\""));
    }
    
    /**
     * JUnit test of ffplay.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffplay(String)
     */
    @Test
    public void testFFplay() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffplay.exe");
        FFmpeg.ffplay("-y -i test.mp4 -c:copy \"out\\test2.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("ffplay -y -i test.mp4 -c:copy \"out\\test2.mp4\""));
        FFmpeg.setFFplayExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffplay")).getAbsolutePath());
        FFmpeg.ffplay("-y -i test.mp4 -c:copy \"out\\test.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmd(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i test.mp4 -c:copy \"out\\test.mp4\""));
    }
    
    /**
     * JUnit test of ffplayAsync.
     *
     * @throws Exception When there is an exception.
     * @see FFmpeg#ffplayAsync(String)
     */
    @Test
    public void testFFplayAsync() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        final File testExecutable = new File(testResources, "ffplay.exe");
        FFmpeg.ffplayAsync("-y -i test.mp4 -c:copy \"out\\test2.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("ffplay -y -i test.mp4 -c:copy \"out\\test2.mp4\""));
        FFmpeg.setFFplayExecutable(testExecutable);
        Assert.assertEquals(testExecutable.getAbsolutePath(),
                ((File) TestUtils.getField(FFmpeg.class, "ffplay")).getAbsolutePath());
        FFmpeg.ffplayAsync("-y -i test.mp4 -c:copy \"out\\test.mp4\"");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.executeCmdAsync(ArgumentMatchers.eq("\"" + testExecutable.getAbsolutePath() + "\" -y -i test.mp4 -c:copy \"out\\test.mp4\""));
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
    
}
