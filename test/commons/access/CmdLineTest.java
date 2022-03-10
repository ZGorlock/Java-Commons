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
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import commons.console.ProgressBar;
import commons.math.MathUtility;
import commons.object.string.StringUtility;
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
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.stubbing.Answer;
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
     * @see CmdLine#executeCmd(String, boolean, ProgressBar)
     * @see CmdLine#executeCmd(String, boolean)
     * @see CmdLine#executeCmd(String, ProgressBar)
     * @see CmdLine#executeCmd(String)
     */
    @Test
    public void testExecuteCmd() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any(), ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean());
        
        Process mockProcess;
        ProcessBuilder mockProcessBuilder;
        ProgressBar mockProgressBar;
        PipedOutputStream processInputStreamWriter;
        PipedInputStream processInputStream;
        PipedOutputStream processErrorStreamWriter;
        PipedInputStream processErrorStream;
        ProcessBuilder invalidProcessBuilder;
        ProgressBar invalidProgressBar;
        final AtomicBoolean processAlive = new AtomicBoolean(false);
        final List<String> testResponseLines = Collections.synchronizedList(new ArrayList<>());
        
        //standard
        
        mockProcess = Mockito.mock(Process.class);
        processAlive.set(true);
        Mockito.when(mockProcess.isAlive()).thenAnswer((Answer<Boolean>) invocationOnMock -> processAlive.get());
        processInputStreamWriter = new PipedOutputStream();
        processInputStream = new PipedInputStream();
        processInputStreamWriter.connect(processInputStream);
        Mockito.when(mockProcess.getInputStream()).thenReturn(processInputStream);
        processErrorStreamWriter = new PipedOutputStream();
        processErrorStream = new PipedInputStream();
        processErrorStreamWriter.connect(processErrorStream);
        Mockito.when(mockProcess.getErrorStream()).thenReturn(processErrorStream);
        mockProcessBuilder = PowerMockito.mock(ProcessBuilder.class);
        PowerMockito.when(mockProcessBuilder.start()).thenReturn(mockProcess);
        
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString());
        PowerMockito.doReturn(mockProcessBuilder).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        
        testResponseLines.clear();
        Executors.newSingleThreadExecutor().execute(() -> {
            String testResponse = CmdLine.executeCmd("test cmd");
            testResponseLines.addAll(StringUtility.splitLines(testResponse));
        });
        
        Thread.sleep(100);
        Assert.assertTrue(mockProcess.isAlive());
        processInputStreamWriter.write("test cmd".getBytes(StandardCharsets.UTF_8));
        processInputStreamWriter.flush();
        Thread.sleep(5);
        processInputStreamWriter.write(" starting up\n".getBytes(StandardCharsets.UTF_8));
        processInputStreamWriter.flush();
        Thread.sleep(10);
        processErrorStreamWriter.write("Version 1.0.0 ... please update to version 1.0.1\n".getBytes(StandardCharsets.UTF_8));
        processErrorStreamWriter.flush();
        Thread.sleep(28);
        processInputStreamWriter.write("initializing...\ndoing stuff...\n".getBytes(StandardCharsets.UTF_8));
        processInputStreamWriter.flush();
        Thread.sleep(117);
        processErrorStreamWriter.write("encountered an error but continuing\n".getBytes(StandardCharsets.UTF_8));
        processErrorStreamWriter.flush();
        Thread.sleep(8);
        processInputStreamWriter.write("answer is 42\n".getBytes(StandardCharsets.UTF_8));
        processInputStreamWriter.flush();
        Assert.assertTrue(TestUtils.getFieldValue(CmdLine.class, Map.class, "runningProcesses").containsKey(mockProcess));
        processInputStreamWriter.close();
        processErrorStreamWriter.close();
        processAlive.set(false);
        Assert.assertFalse(mockProcess.isAlive());
        Thread.sleep(100);
        Mockito.verify(mockProcess).waitFor();
        Mockito.verify(mockProcess).destroy();
        Assert.assertFalse(TestUtils.getFieldValue(CmdLine.class, Map.class, "runningProcesses").containsKey(mockProcess));
        Assert.assertEquals(6, testResponseLines.size());
        Assert.assertEquals("test cmd starting up", testResponseLines.get(0));
        Assert.assertEquals("[*]Version 1.0.0 ... please update to version 1.0.1", testResponseLines.get(1));
        Assert.assertEquals("initializing...", testResponseLines.get(2));
        Assert.assertEquals("doing stuff...", testResponseLines.get(3));
        Assert.assertEquals("[*]encountered an error but continuing", testResponseLines.get(4));
        Assert.assertEquals("answer is 42", testResponseLines.get(5));
        
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any(), ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any());
        
        //progress bar
        
        mockProcess = Mockito.mock(Process.class);
        processAlive.set(true);
        Mockito.when(mockProcess.isAlive()).thenAnswer((Answer<Boolean>) invocationOnMock -> processAlive.get());
        processInputStreamWriter = new PipedOutputStream();
        processInputStream = new PipedInputStream();
        processInputStreamWriter.connect(processInputStream);
        Mockito.when(mockProcess.getInputStream()).thenReturn(processInputStream);
        processErrorStreamWriter = new PipedOutputStream();
        processErrorStream = new PipedInputStream();
        processErrorStreamWriter.connect(processErrorStream);
        Mockito.when(mockProcess.getErrorStream()).thenReturn(processErrorStream);
        mockProcessBuilder = PowerMockito.mock(ProcessBuilder.class);
        PowerMockito.when(mockProcessBuilder.start()).thenReturn(mockProcess);
        mockProgressBar = Mockito.mock(ProgressBar.class);
        
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.anyString());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString());
        PowerMockito.doReturn(mockProcessBuilder).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        
        testResponseLines.clear();
        Executors.newSingleThreadExecutor().execute(() -> {
            String testResponse = CmdLine.executeCmd("test cmd", mockProgressBar);
            testResponseLines.addAll(StringUtility.splitLines(testResponse));
        });
        
        Thread.sleep(100);
        Assert.assertTrue(mockProcess.isAlive());
        processInputStreamWriter.write("test cmd".getBytes(StandardCharsets.UTF_8));
        processInputStreamWriter.flush();
        Thread.sleep(5);
        processInputStreamWriter.write(" starting up\n".getBytes(StandardCharsets.UTF_8));
        processInputStreamWriter.flush();
        Thread.sleep(10);
        processErrorStreamWriter.write("Version 1.0.0 ... please update to version 1.0.1\n".getBytes(StandardCharsets.UTF_8));
        processErrorStreamWriter.flush();
        Thread.sleep(28);
        processInputStreamWriter.write("initializing...\ndoing stuff...\n".getBytes(StandardCharsets.UTF_8));
        processInputStreamWriter.flush();
        Thread.sleep(117);
        processErrorStreamWriter.write("encountered an error but continuing\n".getBytes(StandardCharsets.UTF_8));
        processErrorStreamWriter.flush();
        Thread.sleep(8);
        processInputStreamWriter.write("answer is 42\n".getBytes(StandardCharsets.UTF_8));
        processInputStreamWriter.flush();
        Assert.assertTrue(TestUtils.getFieldValue(CmdLine.class, Map.class, "runningProcesses").containsKey(mockProcess));
        processInputStreamWriter.close();
        processErrorStreamWriter.close();
        processAlive.set(false);
        Assert.assertFalse(mockProcess.isAlive());
        Thread.sleep(100);
        Mockito.verify(mockProcess).waitFor();
        Mockito.verify(mockProcess).destroy();
        Assert.assertFalse(TestUtils.getFieldValue(CmdLine.class, Map.class, "runningProcesses").containsKey(mockProcess));
        Assert.assertEquals(6, testResponseLines.size());
        Assert.assertEquals("test cmd starting up", testResponseLines.get(0));
        Assert.assertEquals("[*]Version 1.0.0 ... please update to version 1.0.1", testResponseLines.get(1));
        Assert.assertEquals("initializing...", testResponseLines.get(2));
        Assert.assertEquals("doing stuff...", testResponseLines.get(3));
        Assert.assertEquals("[*]encountered an error but continuing", testResponseLines.get(4));
        Assert.assertEquals("answer is 42", testResponseLines.get(5));
        Mockito.verify(mockProgressBar).processLog(ArgumentMatchers.eq("test cmd starting up"), ArgumentMatchers.eq(false));
        Mockito.verify(mockProgressBar).processLog(ArgumentMatchers.eq("Version 1.0.0 ... please update to version 1.0.1"), ArgumentMatchers.eq(true));
        Mockito.verify(mockProgressBar).processLog(ArgumentMatchers.eq("initializing..."), ArgumentMatchers.eq(false));
        Mockito.verify(mockProgressBar).processLog(ArgumentMatchers.eq("doing stuff..."), ArgumentMatchers.eq(false));
        Mockito.verify(mockProgressBar).processLog(ArgumentMatchers.eq("encountered an error but continuing"), ArgumentMatchers.eq(true));
        Mockito.verify(mockProgressBar).processLog(ArgumentMatchers.eq("answer is 42"), ArgumentMatchers.eq(false));
        Mockito.verify(mockProgressBar).complete();
        Mockito.verifyNoMoreInteractions(mockProgressBar);
        
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any(), ArgumentMatchers.any());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean());
        PowerMockito.doCallRealMethod().when(CmdLine.class, "executeCmd", ArgumentMatchers.any(), ArgumentMatchers.anyBoolean(), ArgumentMatchers.any());
        
        //safe execute
        PowerMockito.spy(OperatingSystem.class);
        String saveOs = System.getProperty("os.name");
        System.setProperty("os.name", "OtherOS");
        PowerMockito.doReturn(OperatingSystem.OS.OTHER).when(OperatingSystem.class, "getOperatingSystem");
        TestUtils.assertException(RuntimeException.class, "java.lang.RuntimeException: Operating system: OTHEROS is not supported!", () ->
                CmdLine.executeCmd("test cmd", false));
        Assert.assertNull(CmdLine.executeCmd("test cmd"));
        Assert.assertNull(CmdLine.executeCmd("test cmd", true));
        PowerMockito.doCallRealMethod().when(OperatingSystem.class, "getOperatingSystem");
        System.setProperty("os.name", saveOs);
        invalidProcessBuilder = PowerMockito.mock(ProcessBuilder.class);
        Mockito.doThrow(new UnsupportedOperationException()).when(invalidProcessBuilder).start();
        PowerMockito.doReturn(invalidProcessBuilder).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        TestUtils.assertException(RuntimeException.class, "java.lang.UnsupportedOperationException", () ->
                CmdLine.executeCmd("test cmd", false));
        Assert.assertNull(CmdLine.executeCmd("test cmd"));
        Assert.assertNull(CmdLine.executeCmd("test cmd", true));
        PowerMockito.doCallRealMethod().when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean());
        invalidProgressBar = PowerMockito.mock(ProgressBar.class);
        Mockito.doThrow(new IllegalStateException("progressBar#complete")).when(invalidProgressBar).complete();
        TestUtils.assertException(RuntimeException.class, "java.lang.IllegalStateException: progressBar#complete", () ->
                CmdLine.executeCmd("test cmd", false, invalidProgressBar));
        Assert.assertNull(CmdLine.executeCmd("test cmd", invalidProgressBar));
        Assert.assertNull(CmdLine.executeCmd("test cmd", true, invalidProgressBar));
        
        //real case, Windows
        
        if (OperatingSystem.getOperatingSystem() == OperatingSystem.OS.WINDOWS) {
            
            long startTime = System.currentTimeMillis();
            String response = CmdLine.executeCmd("dir /s");
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("Executed in: " + duration + "ms");
            List<String> responseLines = StringUtility.splitLines(response);
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
        
        //invalid
        Assert.assertNull(CmdLine.executeCmd(""));
        Assert.assertNull(CmdLine.executeCmd("", null));
        Assert.assertNull(CmdLine.executeCmd(null));
        Assert.assertNull(CmdLine.executeCmd(null, null));
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
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(3, builder.command().size());
        Assert.assertEquals("cmd.exe", builder.command().get(0));
        Assert.assertEquals("/c", builder.command().get(1));
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(2));
        
        builder = CmdLine.buildProcess("dir /s", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("dir /s", builder.command().get(0));
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(0));
        
        //Unix
        
        operatingSystem.clear();
        operatingSystem.add(OperatingSystem.OS.UNIX);
        
        builder = CmdLine.buildProcess("ls -l", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(3, builder.command().size());
        Assert.assertEquals("bash", builder.command().get(0));
        Assert.assertEquals("-c", builder.command().get(1));
        Assert.assertEquals("ls -l", builder.command().get(2));
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(3, builder.command().size());
        Assert.assertEquals("bash", builder.command().get(0));
        Assert.assertEquals("-c", builder.command().get(1));
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(2));
        
        builder = CmdLine.buildProcess("ls -l", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ls -l", builder.command().get(0));
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(0));
        
        //MacOS
        
        operatingSystem.clear();
        operatingSystem.add(OperatingSystem.OS.MACOS);
        
        builder = CmdLine.buildProcess("ls -l", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(2, builder.command().size());
        Assert.assertEquals("/usr/local/bin/nmap", builder.command().get(0));
        Assert.assertEquals("ls -l", builder.command().get(1));
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", true);
        Assert.assertNotNull(builder);
        Assert.assertEquals(2, builder.command().size());
        Assert.assertEquals("/usr/local/bin/nmap", builder.command().get(0));
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(1));
        
        builder = CmdLine.buildProcess("ls -l", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ls -l", builder.command().get(0));
        
        builder = CmdLine.buildProcess("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", false);
        Assert.assertNotNull(builder);
        Assert.assertEquals(1, builder.command().size());
        Assert.assertEquals("ffmpeg -i \"test video.mp4\" -map 0 -map -0:s -c copy \"new video.mp4\"", builder.command().get(0));
        
        //invalid
        
        Assert.assertNull(CmdLine.buildProcess(""));
        Assert.assertNull(CmdLine.buildProcess("", true));
        Assert.assertNull(CmdLine.buildProcess("", false));
        Assert.assertNull(CmdLine.buildProcess(null));
        Assert.assertNull(CmdLine.buildProcess(null, true));
        Assert.assertNull(CmdLine.buildProcess(null, false));
        
        operatingSystem.clear();
        operatingSystem.add(OperatingSystem.OS.POSIX);
        String saveOs = System.getProperty("os.name");
        System.setProperty("os.name", "Posix");
        TestUtils.assertException(RuntimeException.class, "Operating system: POSIX is not supported!", () ->
                CmdLine.buildProcess("ls -l", true));
        
        operatingSystem.clear();
        operatingSystem.add(OperatingSystem.OS.OTHER);
        System.setProperty("os.name", "OtherOS");
        TestUtils.assertException(RuntimeException.class, "Operating system: OTHEROS is not supported!", () ->
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
    
    /**
     * JUnit test of killProcess.
     *
     * @throws Exception When there is an exception.
     * @see CmdLine#killProcess(Process)
     */
    @Test
    public void testKillProcess() throws Exception {
        final Class<?> ProcessKiller = TestUtils.getClass(CmdLine.class, "ProcessKiller");
        final Process mockProcess = Mockito.mock(Process.class);
        
        //standard
        PowerMockito.spy(ProcessKiller);
        PowerMockito.doReturn(true).when(ProcessKiller, "kill", ArgumentMatchers.eq(mockProcess));
        CmdLine.killProcess(mockProcess);
        PowerMockito.verifyPrivate(ProcessKiller, VerificationModeFactory.times(1))
                .invoke("kill", ArgumentMatchers.eq(mockProcess));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                CmdLine.killProcess(null));
    }
    
    /**
     * JUnit test of ProcessKiller.
     *
     * @throws Exception When there is an exception.
     * @see CmdLine.ProcessKiller
     * @see CmdLine.ProcessKiller.KillStage
     */
    @Test
    public void testProcessKiller() throws Exception {
        final Class<?> ProcessKiller = TestUtils.getClass(CmdLine.class, "ProcessKiller");
        final Class<?> KillStage = TestUtils.getEnum(ProcessKiller, "KillStage");
        final Object[] enumValues = KillStage.getEnumConstants();
        final AtomicReference<OperatingSystem.OS> operatingSystem = new AtomicReference<>(null);
        
        PowerMockito.spy(OperatingSystem.class);
        PowerMockito.doAnswer((Answer<OperatingSystem.OS>) invocation -> (operatingSystem.get() != null) ? operatingSystem.get() : (OperatingSystem.OS) invocation.callRealMethod())
                .when(OperatingSystem.class, "getOperatingSystem");
        
        //constants
        operatingSystem.set(OperatingSystem.OS.WINDOWS);
        Assert.assertEquals(
                "[DESTROY->DESTROY_FORCIBLY | DESTROY_FORCIBLY->CMD_KILL_WINDOWS | CMD_KILL->CMD_KILL_HARD | CMD_KILL_HARD->null | CMD_KILL_WINDOWS->null]",
                ((Map<?, ?>) TestUtils.getFieldValue(ProcessKiller, LinkedHashMap.class, "KILL_SEQUENCE")).entrySet().stream()
                        .map(e -> e.getKey() + "->" + ((Supplier<?>) e.getValue()).get()).collect(Collectors.joining(" | ", "[", "]")));
        operatingSystem.set(OperatingSystem.OS.UNIX);
        Assert.assertEquals(
                "[DESTROY->DESTROY_FORCIBLY | DESTROY_FORCIBLY->CMD_KILL | CMD_KILL->CMD_KILL_HARD | CMD_KILL_HARD->null | CMD_KILL_WINDOWS->null]",
                ((Map<?, ?>) TestUtils.getFieldValue(ProcessKiller, LinkedHashMap.class, "KILL_SEQUENCE")).entrySet().stream()
                        .map(e -> e.getKey() + "->" + ((Supplier<?>) e.getValue()).get()).collect(Collectors.joining(" | ", "[", "]")));
        operatingSystem.set(null);
        Assert.assertEquals(250L, TestUtils.getFieldValue(ProcessKiller, "DEFAULT_VALIDATION_DELAY"));
        
        //kill stage
        Assert.assertEquals(5, enumValues.length);
        Assert.assertEquals("DESTROY", enumValues[0].toString());
        Assert.assertEquals("DESTROY_FORCIBLY", enumValues[1].toString());
        Assert.assertEquals("CMD_KILL", enumValues[2].toString());
        Assert.assertEquals("CMD_KILL_HARD", enumValues[3].toString());
        Assert.assertEquals("CMD_KILL_WINDOWS", enumValues[4].toString());
        Assert.assertTrue(Arrays.stream(enumValues).map(e -> TestUtils.getFieldValue(e, "action")).allMatch(Objects::nonNull));
        Assert.assertTrue(Arrays.stream(enumValues).allMatch(e -> TestUtils.getFieldValue(e, long.class, "validationDelay") == (e.toString().equals("DESTROY_FORCIBLY") ? 750 : 250)));
        Assert.assertTrue(Arrays.stream(enumValues).allMatch(e -> !TestUtils.getFieldValue(e, boolean.class, "reverseTree") || e.toString().matches("^CMD_KILL(?:_WINDOWS)?$")));
        
        //instance
        TestUtils.assertFieldExists(ProcessKiller, "processTree");
        TestUtils.assertFieldExists(ProcessKiller, "stage");
        TestUtils.assertConstructorExists(ProcessKiller, Process.class);
        TestUtils.assertMethodExists(ProcessKiller, "nextStage");
        TestUtils.assertMethodExists(ProcessKiller, "finished");
        TestUtils.assertMethodExists(ProcessKiller, "succeeded");
        
        //kill
        testProcessKillerKill();
    }
    
    /**
     * Helper method for JUnit test of ProcessKiller for kill.
     *
     * @throws Exception When there is an exception.
     * @see CmdLine.ProcessKiller#kill(Process)
     */
    private void testProcessKillerKill() throws Exception {
        final int PROCESS_TREE_SIZE = 4;
        final AtomicReference<Process> process = new AtomicReference<>(null);
        final List<ProcessHandle> processHandles = Arrays.asList(null, null, null, null); //handle, sub handle A, sub handle B, sub handle A sub handle
        final List<AtomicBoolean> processAlive = IntStream.range(0, PROCESS_TREE_SIZE).boxed().map(i -> new AtomicBoolean(false)).collect(Collectors.toList());
        final List<Long> processPid = IntStream.range(0, PROCESS_TREE_SIZE).boxed().map(i -> (MathUtility.random(1000001L, 1000100L) + (100L * i))).collect(Collectors.toList());
        final AtomicReference<OperatingSystem.OS> operatingSystem = new AtomicReference<>(null);
        
        PowerMockito.spy(CmdLine.class);
        PowerMockito.spy(OperatingSystem.class);
        PowerMockito.doAnswer((Answer<OperatingSystem.OS>) invocation -> (operatingSystem.get() != null) ? operatingSystem.get() : (OperatingSystem.OS) invocation.callRealMethod())
                .when(OperatingSystem.class, "getOperatingSystem");
        
        final Consumer<boolean[][]> mockProcessInitializer = (boolean[][] properties) -> { //handle, sub handle A, sub handle B, sub handle A sub handle  |  isAlive, destroy, destroyForcibly, cmd
            process.set(Mockito.mock(Process.class, Mockito.CALLS_REAL_METHODS));
            processHandles.replaceAll(e -> Mockito.mock(ProcessHandle.class, Mockito.CALLS_REAL_METHODS));
            processPid.replaceAll(e -> (e + 1000L));
            IntStream.range(0, PROCESS_TREE_SIZE).forEach(i -> {
                try {
                    processAlive.get(i).set(properties[0][i]);
                    Mockito.doAnswer((Answer<Boolean>) invocation -> processAlive.get(i).get()).when(processHandles.get(i)).isAlive();
                    Mockito.doAnswer((Answer<Long>) invocation -> processPid.get(i)).when(processHandles.get(i)).pid();
                    Mockito.doAnswer((Answer<Boolean>) invocation -> (properties[1][i] && processAlive.get(i).getAndSet(false))).when(processHandles.get(i)).destroy();
                    Mockito.doAnswer((Answer<Boolean>) invocation -> (properties[2][i] && processAlive.get(i).getAndSet(false))).when(processHandles.get(i)).destroyForcibly();
                    PowerMockito.doAnswer((Answer<ProcessBuilder>) invocation -> {
                        final String cmd = invocation.getArgument(0);
                        if ((properties[3][0] && cmd.contains("taskkill")) || (properties[3][1] && cmd.contains("SIGTERM")) || (properties[3][2] && cmd.contains("SIGKILL"))) {
                            processAlive.get(processPid.indexOf(Long.parseLong(cmd.replaceAll("(?:[^\\s]+\\s)*", "")))).set(false);
                        }
                        return PowerMockito.mock(ProcessBuilder.class);
                    }).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString());
                } catch (Exception e) {
                    Assert.fail(e.getMessage());
                }
            });
            Mockito.doAnswer((Answer<Stream<ProcessHandle>>) invocation -> Stream.of(processHandles.get(3))).when(processHandles.get(1)).descendants();
            Mockito.doAnswer((Answer<Stream<ProcessHandle>>) invocation -> IntStream.range(1, PROCESS_TREE_SIZE).boxed().map(processHandles::get)).when(processHandles.get(0)).descendants();
            Mockito.doReturn(processHandles.get(0)).when(process.get()).toHandle();
        };
        final Consumer<int[][]> mockProcessVerifier = (int[][] expected) -> { //process, handle, sub handle A, sub handle B, sub handle A sub handle  |  toHandle, descendants, isAlive, destroy, destroyForcibly, taskkill, SIGTERM, SIGKILL, killed
            IntStream.rangeClosed(0, PROCESS_TREE_SIZE).forEachOrdered(i -> {
                final Object mock = (i == 0) ? process.get() : processHandles.get(i - 1);
                try {
                    if (i == 0) {
                        PowerMockito.verifyPrivate(mock, ((expected[1][i] > 0) ? VerificationModeFactory.times(expected[0][i]) : Mockito.never())).invoke("toHandle");
                    }
                    PowerMockito.verifyPrivate(mock, ((expected[1][i] > 0) ? VerificationModeFactory.times(expected[1][i]) : Mockito.never())).invoke("descendants");
                    PowerMockito.verifyPrivate(mock, ((expected[2][i] > 0) ? VerificationModeFactory.times(expected[2][i]) : Mockito.never())).invoke("isAlive");
                    PowerMockito.verifyPrivate(mock, ((expected[3][i] > 0) ? VerificationModeFactory.times(expected[3][i]) : Mockito.never())).invoke("destroy");
                    PowerMockito.verifyPrivate(mock, ((expected[4][i] > 0) ? VerificationModeFactory.times(expected[4][i]) : Mockito.never())).invoke("destroyForcibly");
                    PowerMockito.verifyPrivate(mock, (((expected[5][i] + expected[6][i] + expected[7][i]) > 0) ? VerificationModeFactory.times((expected[5][i] + expected[6][i] + expected[7][i])) : Mockito.never())).invoke("pid");
                    PowerMockito.verifyNoMoreInteractions(mock);
                    if (i > 0) {
                        Assert.assertEquals((expected[8][i] == 0), processAlive.get(i - 1).get());
                        PowerMockito.verifyStatic(CmdLine.class, ((expected[5][i] > 0) ? VerificationModeFactory.times(expected[5][i]) : Mockito.never()));
                        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(i - 1)));
                        PowerMockito.verifyStatic(CmdLine.class, ((expected[6][i] > 0) ? VerificationModeFactory.times(expected[6][i]) : Mockito.never()));
                        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(i - 1)));
                        PowerMockito.verifyStatic(CmdLine.class, ((expected[7][i] > 0) ? VerificationModeFactory.times(expected[7][i]) : Mockito.never()));
                        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(i - 1)));
                    }
                } catch (Exception e) {
                    Assert.fail(e.getMessage());
                }
            });
        };
        
        //none alive
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {false, false, false, false},
                new boolean[] /*destroy  */ {false, false, false, false},
                new boolean[] /*forcibly */ {false, false, false, false},
                new boolean[] /*cmd      */ {false, false, false}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 2, 2, 2, 2}, new int[] /*destroy    */ {0, 0, 0, 0, 0},
                new int[] /*forcibly*/ {0, 0, 0, 0, 0}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        
        //main process alive
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, false, false, false},
                new boolean[] /*destroy  */ {true, false, false, false},
                new boolean[] /*forcibly */ {false, false, false, false},
                new boolean[] /*cmd      */ {false, false, false}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 4, 3, 3, 3}, new int[] /*destroy    */ {0, 1, 0, 0, 0},
                new int[] /*forcibly*/ {0, 0, 0, 0, 0}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        
        //all processes alive
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, true, true, true},
                new boolean[] /*destroy  */ {true, true, true, true},
                new boolean[] /*forcibly */ {false, false, false, false},
                new boolean[] /*cmd      */ {false, false, false}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 4, 3, 3, 3}, new int[] /*destroy    */ {0, 1, 1, 1, 1},
                new int[] /*forcibly*/ {0, 0, 0, 0, 0}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        
        //sub processes alive
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {false, false, true, true},
                new boolean[] /*destroy  */ {false, false, true, true},
                new boolean[] /*forcibly */ {false, false, false, false},
                new boolean[] /*cmd      */ {false, false, false}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 4, 4, 4, 3}, new int[] /*destroy    */ {0, 0, 0, 1, 1},
                new int[] /*forcibly*/ {0, 0, 0, 0, 0}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        
        //main process still alive
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, false, false, false},
                new boolean[] /*destroy  */ {false, false, false, false},
                new boolean[] /*forcibly */ {true, false, false, false},
                new boolean[] /*cmd      */ {false, false, false}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 6, 4, 4, 4}, new int[] /*destroy    */ {0, 1, 0, 0, 0},
                new int[] /*forcibly*/ {0, 1, 0, 0, 0}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        
        //all processes still alive
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, true, true, true},
                new boolean[] /*destroy  */ {false, false, false, false},
                new boolean[] /*forcibly */ {true, true, true, true},
                new boolean[] /*cmd      */ {false, false, false}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 6, 4, 4, 4}, new int[] /*destroy    */ {0, 1, 1, 1, 1},
                new int[] /*forcibly*/ {0, 1, 1, 1, 1}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        
        //sub processes still alive
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, true, true, true},
                new boolean[] /*destroy  */ {true, true, false, false},
                new boolean[] /*forcibly */ {false, false, true, true},
                new boolean[] /*cmd      */ {false, false, false}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 6, 5, 5, 4}, new int[] /*destroy    */ {0, 1, 1, 1, 1},
                new int[] /*forcibly*/ {0, 0, 0, 1, 1}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        
        //main process not responding, Windows
        operatingSystem.set(OperatingSystem.OS.WINDOWS);
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, false, false, false},
                new boolean[] /*destroy  */ {false, false, false, false},
                new boolean[] /*forcibly */ {false, false, false, false},
                new boolean[] /*cmd      */ {false, false, false}
        });
        Assert.assertFalse(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 7, 4, 4, 4}, new int[] /*destroy    */ {0, 1, 0, 0, 0},
                new int[] /*forcibly*/ {0, 1, 0, 0, 0}, new int[] /*taskkill   */ {0, 1, 0, 0, 0},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {0, 0, 1, 1, 1}
        });
        operatingSystem.set(null);
        
        //all process not responding, Windows
        operatingSystem.set(OperatingSystem.OS.WINDOWS);
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, true, true, true},
                new boolean[] /*destroy  */ {false, false, false, false},
                new boolean[] /*forcibly */ {false, false, false, false},
                new boolean[] /*cmd      */ {true, false, false}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 7, 4, 4, 4}, new int[] /*destroy    */ {0, 1, 1, 1, 1},
                new int[] /*forcibly*/ {0, 1, 1, 1, 1}, new int[] /*taskkill   */ {0, 1, 1, 1, 1},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        operatingSystem.set(null);
        
        //sub processes not responding, Windows
        operatingSystem.set(OperatingSystem.OS.WINDOWS);
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, true, true, true},
                new boolean[] /*destroy  */ {true, false, false, true},
                new boolean[] /*forcibly */ {false, true, false, false},
                new boolean[] /*cmd      */ {false, false, false}
        });
        Assert.assertFalse(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 6, 5, 5, 4}, new int[] /*destroy    */ {0, 1, 1, 1, 1},
                new int[] /*forcibly*/ {0, 0, 1, 1, 0}, new int[] /*taskkill   */ {0, 0, 0, 1, 0},
                new int[] /*sigterm */ {0, 0, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {0, 1, 1, 0, 1}
        });
        operatingSystem.set(null);
        
        //main process not responding, not Windows
        operatingSystem.set(OperatingSystem.OS.UNIX);
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, false, false, false},
                new boolean[] /*destroy  */ {false, false, false, false},
                new boolean[] /*forcibly */ {false, false, false, false},
                new boolean[] /*cmd      */ {false, true, true}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 8, 5, 5, 5}, new int[] /*destroy    */ {0, 1, 0, 0, 0},
                new int[] /*forcibly*/ {0, 1, 0, 0, 0}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 1, 0, 0, 0}, new int[] /*sigkill    */ {0, 0, 0, 0, 0},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        operatingSystem.set(null);
        
        //all process not responding, not Windows
        operatingSystem.set(OperatingSystem.OS.MACOS);
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, true, true, true},
                new boolean[] /*destroy  */ {false, false, false, false},
                new boolean[] /*forcibly */ {false, false, false, false},
                new boolean[] /*cmd      */ {false, false, true}
        });
        Assert.assertTrue(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 8, 5, 5, 6}, new int[] /*destroy    */ {0, 1, 1, 1, 1},
                new int[] /*forcibly*/ {0, 1, 1, 1, 1}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 1, 1, 1, 1}, new int[] /*sigkill    */ {0, 1, 1, 1, 1},
                new int[] /*killed  */ {1, 1, 1, 1, 1}
        });
        operatingSystem.set(null);
        
        //sub processes not responding, not Windows
        operatingSystem.set(OperatingSystem.OS.POSIX);
        mockProcessInitializer.accept(new boolean[][] {
                new boolean[] /*alive    */ {true, true, true, true},
                new boolean[] /*destroy  */ {true, false, false, false},
                new boolean[] /*forcibly */ {false, true, false, false},
                new boolean[] /*cmd      */ {true, false, false}
        });
        Assert.assertFalse(CmdLine.killProcess(process.get()));
        mockProcessVerifier.accept(new int[][] {
                new int[] /*toHandle*/ {2, 0, 0, 0, 0}, new int[] /*descendants*/ {1, 1, 0, 0, 0},
                new int[] /*alive   */ {0, 7, 6, 5, 6}, new int[] /*destroy    */ {0, 1, 1, 1, 1},
                new int[] /*forcibly*/ {0, 0, 1, 1, 1}, new int[] /*taskkill   */ {0, 0, 0, 0, 0},
                new int[] /*sigterm */ {0, 0, 0, 1, 1}, new int[] /*sigkill    */ {0, 0, 0, 1, 1},
                new int[] /*killed  */ {0, 1, 1, 0, 0}
        });
        operatingSystem.set(null);
    }
    
}
