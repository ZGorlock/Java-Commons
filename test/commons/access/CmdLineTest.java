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
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import commons.console.ProgressBar;
import commons.math.MathUtility;
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
     * @see CmdLine#executeCmd(String, ProgressBar)
     * @see CmdLine#executeCmd(String)
     */
    @Test
    public void testExecuteCmd() throws Exception {
        PowerMockito.mockStatic(CmdLine.class);
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
        Assert.assertTrue(((Map<Process, String>) TestUtils.getField(CmdLine.class, "runningProcesses")).containsKey(mockProcess));
        processInputStreamWriter.close();
        processErrorStreamWriter.close();
        processAlive.set(false);
        Assert.assertFalse(mockProcess.isAlive());
        Thread.sleep(100);
        Mockito.verify(mockProcess).waitFor();
        Mockito.verify(mockProcess).destroy();
        Assert.assertFalse(((Map<Process, String>) TestUtils.getField(CmdLine.class, "runningProcesses")).containsKey(mockProcess));
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
        Assert.assertTrue(((Map<Process, String>) TestUtils.getField(CmdLine.class, "runningProcesses")).containsKey(mockProcess));
        processInputStreamWriter.close();
        processErrorStreamWriter.close();
        processAlive.set(false);
        Assert.assertFalse(mockProcess.isAlive());
        Thread.sleep(100);
        Mockito.verify(mockProcess).waitFor();
        Mockito.verify(mockProcess).destroy();
        Assert.assertFalse(((Map<Process, String>) TestUtils.getField(CmdLine.class, "runningProcesses")).containsKey(mockProcess));
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
        
        Assert.assertEquals("", CmdLine.executeCmd(""));
        Assert.assertEquals("", CmdLine.executeCmd(null));
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
        PowerMockito.mockStatic(CmdLine.class);
        PowerMockito.doCallRealMethod().when(CmdLine.class, "killProcess", ArgumentMatchers.any(Process.class));
        PowerMockito.doReturn(PowerMockito.mock(ProcessBuilder.class)).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString());
        PowerMockito.mockStatic(OperatingSystem.class);
        
        List<AtomicBoolean> processIsAlive = Arrays.asList(
                new AtomicBoolean(true), new AtomicBoolean(false), new AtomicBoolean(false), new AtomicBoolean(false));
        List<Long> processPid = Arrays.asList(
                MathUtility.random(1000001L, 1000100L), MathUtility.random(1000101L, 1000200L), MathUtility.random(1000201L, 1000300L), MathUtility.random(1000301L, 1000400L));
        ProcessHandle mockProcessHandle = Mockito.mock(ProcessHandle.class, Mockito.CALLS_REAL_METHODS);
        ProcessHandle mockSubProcessHandle1 = Mockito.mock(ProcessHandle.class, Mockito.CALLS_REAL_METHODS);
        ProcessHandle mockSubProcessHandle2 = Mockito.mock(ProcessHandle.class, Mockito.CALLS_REAL_METHODS);
        ProcessHandle mockSubSubProcessHandle1 = Mockito.mock(ProcessHandle.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(mockProcessHandle.isAlive()).thenAnswer((Answer<Boolean>) invocationOnMock -> processIsAlive.get(0).get());
        Mockito.when(mockSubProcessHandle1.isAlive()).thenAnswer((Answer<Boolean>) invocationOnMock -> processIsAlive.get(1).get());
        Mockito.when(mockSubProcessHandle2.isAlive()).thenAnswer((Answer<Boolean>) invocationOnMock -> processIsAlive.get(2).get());
        Mockito.when(mockSubSubProcessHandle1.isAlive()).thenAnswer((Answer<Boolean>) invocationOnMock -> processIsAlive.get(3).get());
        Mockito.when(mockProcessHandle.pid()).thenAnswer((Answer<Long>) invocationOnMock -> processPid.get(0));
        Mockito.when(mockSubProcessHandle1.pid()).thenAnswer((Answer<Long>) invocationOnMock -> processPid.get(1));
        Mockito.when(mockSubProcessHandle2.pid()).thenAnswer((Answer<Long>) invocationOnMock -> processPid.get(2));
        Mockito.when(mockSubSubProcessHandle1.pid()).thenAnswer((Answer<Long>) invocationOnMock -> processPid.get(3));
        Mockito.when(mockProcessHandle.descendants()).thenAnswer((Answer<Stream<ProcessHandle>>) invocationOnMock ->
                Stream.of(mockSubProcessHandle1, mockSubSubProcessHandle1, mockSubProcessHandle2));
        Process mockProcess = Mockito.mock(Process.class, Mockito.CALLS_REAL_METHODS);
        Mockito.doReturn(mockProcessHandle).when(mockProcess).toHandle();
        Mockito.doReturn(false).when(mockProcessHandle).destroy();
        Mockito.doReturn(false).when(mockSubProcessHandle1).destroy();
        Mockito.doReturn(false).when(mockSubProcessHandle2).destroy();
        Mockito.doReturn(false).when(mockSubSubProcessHandle1).destroy();
        Mockito.doReturn(false).when(mockProcessHandle).destroyForcibly();
        Mockito.doReturn(false).when(mockSubProcessHandle1).destroyForcibly();
        Mockito.doReturn(false).when(mockSubProcessHandle2).destroyForcibly();
        Mockito.doReturn(false).when(mockSubSubProcessHandle1).destroyForcibly();
        
        //none alive
        processIsAlive.get(0).set(false);
        processIsAlive.get(1).set(false);
        processIsAlive.get(2).set(false);
        processIsAlive.get(3).set(false);
        Assert.assertTrue(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(2)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(1)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(1)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(1)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(1)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(1)).isAlive();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        
        //main process alive
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(false);
        processIsAlive.get(2).set(false);
        processIsAlive.get(3).set(false);
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(0).set(false);
            return null;
        }).when(mockProcessHandle).destroy();
        Assert.assertTrue(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(4)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(2)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(2)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(4)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(1)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(3)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(3)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(3)).isAlive();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        Mockito.doReturn(false).when(mockProcessHandle).destroy();
        
        //all processes alive
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(true);
        processIsAlive.get(2).set(true);
        processIsAlive.get(3).set(true);
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(0).set(false);
            return null;
        }).when(mockProcessHandle).destroy();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(1).set(false);
            return null;
        }).when(mockSubProcessHandle1).destroy();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(2).set(false);
            return null;
        }).when(mockSubProcessHandle2).destroy();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(3).set(false);
            return null;
        }).when(mockSubSubProcessHandle1).destroy();
        Assert.assertTrue(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(6)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(3)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(3)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(7)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(2)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(5)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(5)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(1)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(5)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(1)).destroy();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        Mockito.doReturn(false).when(mockProcessHandle).destroy();
        Mockito.doReturn(false).when(mockSubProcessHandle1).destroy();
        Mockito.doReturn(false).when(mockSubProcessHandle2).destroy();
        Mockito.doReturn(false).when(mockSubSubProcessHandle1).destroy();
        
        //sub processes alive
        processIsAlive.get(0).set(false);
        processIsAlive.get(1).set(false);
        processIsAlive.get(2).set(true);
        processIsAlive.get(3).set(true);
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(2).set(false);
            return null;
        }).when(mockSubProcessHandle2).destroy();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(3).set(false);
            return null;
        }).when(mockSubSubProcessHandle1).destroy();
        Assert.assertTrue(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(8)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(4)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(4)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(10)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(2)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(8)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(7)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(2)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(8)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(2)).destroy();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        Mockito.doReturn(false).when(mockSubProcessHandle2).destroy();
        Mockito.doReturn(false).when(mockSubSubProcessHandle1).destroy();
        
        //main process still alive
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(false);
        processIsAlive.get(2).set(false);
        processIsAlive.get(3).set(false);
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(0).set(false);
            return null;
        }).when(mockProcessHandle).destroyForcibly();
        Assert.assertTrue(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(10)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(5)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(5)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(15)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(3)).destroy();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(1)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(11)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(10)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(2)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(11)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(2)).destroy();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        Mockito.doReturn(false).when(mockProcessHandle).destroyForcibly();
        
        //all processes still alive
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(true);
        processIsAlive.get(2).set(true);
        processIsAlive.get(3).set(true);
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(0).set(false);
            return null;
        }).when(mockProcessHandle).destroyForcibly();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(1).set(false);
            return null;
        }).when(mockSubProcessHandle1).destroyForcibly();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(2).set(false);
            return null;
        }).when(mockSubProcessHandle2).destroyForcibly();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(3).set(false);
            return null;
        }).when(mockSubSubProcessHandle1).destroyForcibly();
        Assert.assertTrue(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(12)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(6)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(6)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(20)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(4)).destroy();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(2)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(14)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(2)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(13)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(3)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(1)).destroyForcibly();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(14)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(3)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(1)).destroyForcibly();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        Mockito.doReturn(false).when(mockProcessHandle).destroyForcibly();
        Mockito.doReturn(false).when(mockSubProcessHandle1).destroyForcibly();
        Mockito.doReturn(false).when(mockSubProcessHandle2).destroyForcibly();
        Mockito.doReturn(false).when(mockSubSubProcessHandle1).destroyForcibly();
        
        //sub processes still alive
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(true);
        processIsAlive.get(2).set(true);
        processIsAlive.get(3).set(true);
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(0).set(false);
            return null;
        }).when(mockProcessHandle).destroy();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(1).set(false);
            return null;
        }).when(mockSubProcessHandle1).destroy();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(2).set(false);
            return null;
        }).when(mockSubProcessHandle2).destroyForcibly();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(3).set(false);
            return null;
        }).when(mockSubSubProcessHandle1).destroyForcibly();
        Assert.assertTrue(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(14)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(7)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(7)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(25)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(5)).destroy();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(2)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(18)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(3)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(16)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(4)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(2)).destroyForcibly();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(18)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(4)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(2)).destroyForcibly();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        Mockito.doReturn(false).when(mockProcessHandle).destroy();
        Mockito.doReturn(false).when(mockSubProcessHandle1).destroy();
        Mockito.doReturn(false).when(mockSubProcessHandle2).destroyForcibly();
        Mockito.doReturn(false).when(mockSubSubProcessHandle1).destroyForcibly();
        
        //main process not responding, Windows
        PowerMockito.when(OperatingSystem.class, "getOperatingSystem").thenReturn(OperatingSystem.OS.WINDOWS);
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(false);
        processIsAlive.get(2).set(false);
        processIsAlive.get(3).set(false);
        Assert.assertFalse(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(16)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(8)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(8)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(32)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(6)).destroy();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(3)).destroyForcibly();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(1)).pid();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(22)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(3)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(20)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(4)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(2)).destroyForcibly();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(22)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(4)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(2)).destroyForcibly();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(0)));
        
        //all process not responding, Windows
        PowerMockito.when(OperatingSystem.class, "getOperatingSystem").thenReturn(OperatingSystem.OS.WINDOWS);
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(true);
        processIsAlive.get(2).set(true);
        processIsAlive.get(3).set(true);
        PowerMockito.doAnswer((Answer<ProcessBuilder>) invocationOnMock -> {
            final String cmd = invocationOnMock.getArgument(0);
            processIsAlive.get(processPid.indexOf(
                    Long.parseLong(cmd.substring(cmd.lastIndexOf(' ') + 1)))).set(false);
            return PowerMockito.mock(ProcessBuilder.class);
        }).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString());
        Assert.assertTrue(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(18)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(9)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(9)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(39)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(7)).destroy();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(4)).destroyForcibly();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(2)).pid();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(26)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(4)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(2)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).pid();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(24)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(5)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(3)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(1)).pid();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(26)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(5)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(3)).destroyForcibly();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(1)).pid();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(0)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(1)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(2)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(3)));
        PowerMockito.doReturn(PowerMockito.mock(ProcessBuilder.class)).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString());
        
        //sub processes not responding, Windows
        PowerMockito.when(OperatingSystem.class, "getOperatingSystem").thenReturn(OperatingSystem.OS.WINDOWS);
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(true);
        processIsAlive.get(2).set(true);
        processIsAlive.get(3).set(true);
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(0).set(false);
            return null;
        }).when(mockProcessHandle).destroy();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(1).set(false);
            return null;
        }).when(mockSubProcessHandle1).destroy();
        Assert.assertFalse(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(20)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(10)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(10)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(45)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(8)).destroy();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(4)).destroyForcibly();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(2)).pid();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(31)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(5)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(2)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).pid();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(28)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(6)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(4)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(2)).pid();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(31)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(6)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(4)).destroyForcibly();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(2)).pid();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(0)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(1)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(2)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("taskkill /F /PID " + processPid.get(3)));
        Mockito.doReturn(false).when(mockProcessHandle).destroy();
        Mockito.doReturn(false).when(mockSubProcessHandle1).destroy();
        
        //main process not responding, not Windows
        PowerMockito.when(OperatingSystem.class, "getOperatingSystem").thenReturn(OperatingSystem.OS.UNIX);
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(false);
        processIsAlive.get(2).set(false);
        processIsAlive.get(3).set(false);
        Assert.assertFalse(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(22)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(11)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(11)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(53)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(9)).destroy();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(5)).destroyForcibly();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(4)).pid();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(36)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(5)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(2)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(1)).pid();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(33)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(6)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(4)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(2)).pid();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(36)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(6)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(4)).destroyForcibly();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(2)).pid();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(0)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(0)));
        
        //all process not responding, not Windows
        PowerMockito.when(OperatingSystem.class, "getOperatingSystem").thenReturn(OperatingSystem.OS.MACOS);
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(true);
        processIsAlive.get(2).set(true);
        processIsAlive.get(3).set(true);
        PowerMockito.doAnswer((Answer<ProcessBuilder>) invocationOnMock -> {
            final String cmd = invocationOnMock.getArgument(0);
            if (cmd.contains("SIGKILL")) {
                processIsAlive.get(processPid.indexOf(
                        Long.parseLong(cmd.substring(cmd.lastIndexOf(' ') + 1)))).set(false);
            }
            return PowerMockito.mock(ProcessBuilder.class);
        }).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString());
        Assert.assertTrue(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(24)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(12)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(12)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(61)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(10)).destroy();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(6)).destroyForcibly();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(6)).pid();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(41)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(6)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(3)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(3)).pid();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(38)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(7)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(5)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(4)).pid();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(41)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(7)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(5)).destroyForcibly();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(4)).pid();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(0)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(0)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(1)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(1)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(2)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(2)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(3)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(3)));
        PowerMockito.doReturn(PowerMockito.mock(ProcessBuilder.class)).when(CmdLine.class, "buildProcess", ArgumentMatchers.anyString());
        
        //sub processes not responding, not Windows
        PowerMockito.when(OperatingSystem.class, "getOperatingSystem").thenReturn(OperatingSystem.OS.POSIX);
        processIsAlive.get(0).set(true);
        processIsAlive.get(1).set(true);
        processIsAlive.get(2).set(true);
        processIsAlive.get(3).set(true);
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(0).set(false);
            return null;
        }).when(mockProcessHandle).destroy();
        Mockito.doAnswer(invocationOnMock -> {
            processIsAlive.get(1).set(false);
            return null;
        }).when(mockSubProcessHandle1).destroy();
        Assert.assertFalse(CmdLine.killProcess(mockProcess));
        Mockito.verify(mockProcess, VerificationModeFactory.times(26)).toHandle();
        Mockito.verify(mockProcess, VerificationModeFactory.times(13)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(13)).descendants();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(68)).isAlive();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(11)).destroy();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(6)).destroyForcibly();
        Mockito.verify(mockProcessHandle, VerificationModeFactory.times(6)).pid();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(47)).isAlive();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(7)).destroy();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(3)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle1, VerificationModeFactory.times(3)).pid();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(43)).isAlive();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(8)).destroy();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(6)).destroyForcibly();
        Mockito.verify(mockSubProcessHandle2, VerificationModeFactory.times(6)).pid();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(47)).isAlive();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(8)).destroy();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(6)).destroyForcibly();
        Mockito.verify(mockSubSubProcessHandle1, VerificationModeFactory.times(6)).pid();
        Mockito.verifyNoMoreInteractions(mockProcess);
        Mockito.verifyNoMoreInteractions(mockProcessHandle);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle1);
        Mockito.verifyNoMoreInteractions(mockSubProcessHandle2);
        Mockito.verifyNoMoreInteractions(mockSubSubProcessHandle1);
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(0)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(0)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(1)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(1));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(1)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(2)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(2)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGTERM " + processPid.get(3)));
        PowerMockito.verifyStatic(CmdLine.class, VerificationModeFactory.times(2));
        CmdLine.buildProcess(ArgumentMatchers.eq("kill -SIGKILL " + processPid.get(3)));
        Mockito.doReturn(false).when(mockProcessHandle).destroy();
        Mockito.doReturn(false).when(mockSubProcessHandle1).destroy();
    }
    
}
