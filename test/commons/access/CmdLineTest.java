/*
 * File:    CmdLineTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.ws.rs.NotSupportedException;

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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of CmdLine.
 *
 * @see CmdLine
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CmdLine.class, OperatingSystem.class})
public class CmdLineTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CmdLineTest.class);
    
    
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
     * JUnit test of executeCmd.
     *
     * @throws Exception When there is an exception.
     * @see CmdLine#executeCmd(String)
     */
    @Test
    public void testExecuteCmd() throws Exception {
        if (OperatingSystem.getOperatingSystem() != OperatingSystem.OS.WINDOWS) {
            System.err.println("Unit test only available on Windows");
            return;
        }
        
        String response;
        List<String> responseLines;
        long startTime;
        long duration;
        
        //standard
        
        startTime = System.currentTimeMillis();
        response = CmdLine.executeCmd("dir /s");
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Executed in: " + duration + "ms");
        responseLines = StringUtility.splitLines(response);
        Assert.assertFalse(responseLines.isEmpty());
        Assert.assertTrue(responseLines.get(0).matches("^\\s*Volume in drive [A-Z] is .+$"));
        
        File log = Filesystem.createTemporaryFile();
        startTime = System.currentTimeMillis();
        response = CmdLine.executeCmd("dir /s > tmp/" + log.getName());
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Executed in: " + duration + "ms");
        responseLines = StringUtility.splitLines(response);
        Assert.assertEquals(1, responseLines.size());
        Assert.assertTrue(responseLines.get(0).matches(""));
        responseLines = Filesystem.readLines(log);
        Assert.assertFalse(responseLines.isEmpty());
        Assert.assertTrue(responseLines.get(0).matches("^\\s*Volume in drive [A-Z] is .+$"));
        Filesystem.deleteFile(log);
    }
    
    /**
     * JUnit test of executeCmdAsync.
     *
     * @throws Exception When there is an exception.
     * @see CmdLine#executeCmdAsync(String)
     */
    @SuppressWarnings("BusyWait")
    @Test
    public void testExecuteCmdAsync() throws Exception {
        if (OperatingSystem.getOperatingSystem() != OperatingSystem.OS.WINDOWS) {
            System.err.println("Unit test only available on Windows");
            return;
        }
        
        Process process;
        List<String> responseLines;
        long startTime;
        long duration;
        
        //standard
        
        startTime = System.currentTimeMillis();
        process = CmdLine.executeCmdAsync("dir /s");
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Started in: " + duration + "ms");
        Assert.assertNotNull(process);
        responseLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            while (process.isAlive()) {
                responseLines.add(reader.readLine());
            }
        }
        process.destroy();
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Executed in: " + duration + "ms");
        Assert.assertFalse(responseLines.isEmpty());
        Assert.assertTrue(responseLines.get(0).matches("^\\s*Volume in drive [A-Z] is .+$"));
        
        File log = Filesystem.createTemporaryFile();
        startTime = System.currentTimeMillis();
        process = CmdLine.executeCmdAsync("dir /s > tmp/" + log.getName());
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Started in: " + duration + "ms");
        Assert.assertNotNull(process);
        while (process.isAlive()) {
            Thread.sleep(5);
        }
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Executed in: " + duration + "ms");
        process.destroy();
        responseLines = Filesystem.readLines(log);
        Assert.assertFalse(responseLines.isEmpty());
        Assert.assertTrue(responseLines.get(0).matches("^\\s*Volume in drive [A-Z] is .+$"));
        Filesystem.deleteFile(log);
    }
    
    /**
     * JUnit test of buildProcess.
     *
     * @throws Exception When there is an exception.
     * @see CmdLine#buildProcess(String, boolean)
     * @see CmdLine#buildProcess(String)
     */
    @Test
    public void testBuildProcess() throws Exception {
        Queue<OperatingSystem.OS> operatingSystem = new PriorityQueue<>();
        ProcessBuilder builder;
        
        PowerMockito.spy(OperatingSystem.class);
        PowerMockito.doAnswer(invocationOnMock -> operatingSystem.peek()).when(OperatingSystem.class, "getOperatingSystem");
        
        //Windows
        
        operatingSystem.clear();
        operatingSystem.add(OperatingSystem.OS.WINDOWS);
        
        builder = CmdLine.buildProcess("dir /s", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(3, builder.command().size());
        Assert.assertEquals("cmd.exe", builder.command().get(0));
        Assert.assertEquals("/c", builder.command().get(1));
        Assert.assertEquals("dir /s", builder.command().get(2));
        Assert.assertTrue(builder.redirectErrorStream());
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(3, builder.command().size());
        Assert.assertEquals("cmd.exe", builder.command().get(0));
        Assert.assertEquals("/c", builder.command().get(1));
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(2));
        Assert.assertTrue(builder.redirectErrorStream());
        
        builder = CmdLine.buildProcess("dir /s", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("dir /s", builder.command().get(0));
        Assert.assertTrue(builder.redirectErrorStream());
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(0));
        Assert.assertTrue(builder.redirectErrorStream());
        
        //Unix
        
        operatingSystem.clear();
        operatingSystem.add(OperatingSystem.OS.UNIX);
        
        builder = CmdLine.buildProcess("ls -l", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(3, builder.command().size());
        Assert.assertEquals("bash", builder.command().get(0));
        Assert.assertEquals("-c", builder.command().get(1));
        Assert.assertEquals("ls -l", builder.command().get(2));
        Assert.assertTrue(builder.redirectErrorStream());
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(3, builder.command().size());
        Assert.assertEquals("bash", builder.command().get(0));
        Assert.assertEquals("-c", builder.command().get(1));
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(2));
        Assert.assertTrue(builder.redirectErrorStream());
        
        builder = CmdLine.buildProcess("ls -l", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ls -l", builder.command().get(0));
        Assert.assertTrue(builder.redirectErrorStream());
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(0));
        Assert.assertTrue(builder.redirectErrorStream());
        
        //MacOS
        
        operatingSystem.clear();
        operatingSystem.add(OperatingSystem.OS.MACOS);
        
        builder = CmdLine.buildProcess("ls -l", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(2, builder.command().size());
        Assert.assertEquals("/usr/local/bin/nmap", builder.command().get(0));
        Assert.assertEquals("ls -l", builder.command().get(1));
        Assert.assertTrue(builder.redirectErrorStream());
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(2, builder.command().size());
        Assert.assertEquals("/usr/local/bin/nmap", builder.command().get(0));
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(1));
        Assert.assertTrue(builder.redirectErrorStream());
        
        builder = CmdLine.buildProcess("ls -l", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ls -l", builder.command().get(0));
        Assert.assertTrue(builder.redirectErrorStream());
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(0));
        Assert.assertTrue(builder.redirectErrorStream());
        
        //invalid
        
        operatingSystem.clear();
        operatingSystem.add(OperatingSystem.OS.POSIX);
        String saveOs = System.getProperty("os.name");
        System.setProperty("os.name", "Posix");
        TestUtils.assertException(NotSupportedException.class, "Operating system: POSIX is not supported!", () ->
                CmdLine.buildProcess("ls -l", true));
        
        operatingSystem.clear();
        operatingSystem.add(OperatingSystem.OS.OTHER);
        System.setProperty("os.name", "OtherOS");
        TestUtils.assertException(NotSupportedException.class, "Operating system: OTHEROS is not supported!", () ->
                CmdLine.buildProcess("ls -l", true));
        
        operatingSystem.clear();
        System.setProperty("os.name", saveOs);
        PowerMockito.doReturn(null).when(OperatingSystem.class, "getOperatingSystem");
        TestUtils.assertException(NullPointerException.class, () ->
                CmdLine.buildProcess("ls -l", true));
        
        //default user script command
        
        PowerMockito.spy(CmdLine.class);
        PowerMockito.doReturn(null).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString());
        CmdLine.buildProcess("ls -l");
        PowerMockito.verifyStatic(CmdLine.class);
        CmdLine.buildProcess("ls -l", true);
    }
    
}
