/*
 * File:    OperatingSystemTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.util.Arrays;
import java.util.List;

import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of OperatingSystem.
 *
 * @see OperatingSystem
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({OperatingSystem.class, System.class})
public class OperatingSystemTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(OperatingSystemTest.class);
    
    
    //Constants
    
    /**
     * A list of Windows OS examples.
     */
    private static final List<String> WINDOWS_OS_EXAMPLES = Arrays.asList("Windows 10", "Windows 8.1", "Windows CE", "Windows 2003", "Windows XP", "Windows 2000", "Windows NT", "Windows Me", "Windows 98", "Windows 95");
    
    /**
     * A list of Unix OS examples.
     */
    private static final List<String> UNIX_OS_EXAMPLES = Arrays.asList("Linux", "FreeBSD", "MPE/iX", "Irix", "Digital Unix");
    
    /**
     * A list of MacOS examples.
     */
    private static final List<String> MAC_OS_EXAMPLES = Arrays.asList("Mac OS", "Mac OS X");
    
    /**
     * A list of POSIX OS examples.
     */
    private static final List<String> POSIX_OS_EXAMPLES = Arrays.asList("Solaris", "SunOS", "HP-UX", "AIX");
    
    /**
     * A list of Other OS examples.
     */
    private static final List<String> OTHER_OS_EXAMPLES = Arrays.asList("something", "something else", "");
    
    
    //Static Fields
    
    /**
     * The name of the operating system to use in the system under test.
     */
    private static String osName;
    
    
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
        PowerMockito.spy(OperatingSystem.class);
        PowerMockito.when(OperatingSystem.getOperatingSystemName()).thenAnswer((Answer<String>) invocationOnMock -> osName);
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
     * @see OperatingSystem#DEBUGGING
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertNotNull(TestUtils.getField(OperatingSystem.class, "DEBUGGING"));
    }
    
    /**
     * JUnit test of OS.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem.OS
     */
    @Test
    public void testOS() throws Exception {
        Assert.assertEquals(5, OperatingSystem.OS.values().length);
        Assert.assertEquals(OperatingSystem.OS.WINDOWS, OperatingSystem.OS.values()[0]);
        Assert.assertEquals(OperatingSystem.OS.UNIX, OperatingSystem.OS.values()[1]);
        Assert.assertEquals(OperatingSystem.OS.MACOS, OperatingSystem.OS.values()[2]);
        Assert.assertEquals(OperatingSystem.OS.POSIX, OperatingSystem.OS.values()[3]);
        Assert.assertEquals(OperatingSystem.OS.OTHER, OperatingSystem.OS.values()[4]);
    }
    
    /**
     * JUnit test of getOperatingSystem.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#getOperatingSystem()
     */
    @Test
    public void testGetOperatingSystem() throws Exception {
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertEquals(OperatingSystem.OS.WINDOWS, OperatingSystem.getOperatingSystem());
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertEquals(OperatingSystem.OS.UNIX, OperatingSystem.getOperatingSystem());
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertEquals(OperatingSystem.OS.MACOS, OperatingSystem.getOperatingSystem());
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertEquals(OperatingSystem.OS.POSIX, OperatingSystem.getOperatingSystem());
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertEquals(OperatingSystem.OS.OTHER, OperatingSystem.getOperatingSystem());
        }
    }
    
    /**
     * JUnit test of getOperatingSystemName.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#getOperatingSystemName()
     */
    @Test
    public void testGetOperatingSystemName() throws Exception {
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getProperty("os.name")).thenAnswer((Answer<String>) invocationOnMock -> osName);
        PowerMockito.when(OperatingSystem.getOperatingSystemName()).thenCallRealMethod();
        
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertEquals(osName, OperatingSystem.getOperatingSystemName());
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertEquals(osName, OperatingSystem.getOperatingSystemName());
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertEquals(osName, OperatingSystem.getOperatingSystemName());
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertEquals(osName, OperatingSystem.getOperatingSystemName());
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertEquals(osName, OperatingSystem.getOperatingSystemName());
        }
    }
    
    /**
     * JUnit test of isWindows.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#isWindows()
     */
    @Test
    public void testIsWindows() throws Exception {
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertTrue(OperatingSystem.isWindows());
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertFalse(OperatingSystem.isWindows());
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertFalse(OperatingSystem.isWindows());
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertFalse(OperatingSystem.isWindows());
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertFalse(OperatingSystem.isWindows());
        }
    }
    
    /**
     * JUnit test of isUnix.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#isUnix()
     */
    @Test
    public void testIsUnix() throws Exception {
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertFalse(OperatingSystem.isUnix());
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertTrue(OperatingSystem.isUnix());
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertFalse(OperatingSystem.isUnix());
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertFalse(OperatingSystem.isUnix());
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertFalse(OperatingSystem.isUnix());
        }
    }
    
    /**
     * JUnit test of isMacOS.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#isMacOS()
     */
    @Test
    public void testIsMacOS() throws Exception {
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertFalse(OperatingSystem.isMacOS());
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertFalse(OperatingSystem.isMacOS());
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertTrue(OperatingSystem.isMacOS());
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertFalse(OperatingSystem.isMacOS());
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertFalse(OperatingSystem.isMacOS());
        }
    }
    
    /**
     * JUnit test of isPosix.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#isPosix()
     */
    @Test
    public void testIsPosix() throws Exception {
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertFalse(OperatingSystem.isPosix());
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertFalse(OperatingSystem.isPosix());
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertFalse(OperatingSystem.isPosix());
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertTrue(OperatingSystem.isPosix());
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertFalse(OperatingSystem.isPosix());
        }
    }
    
    /**
     * JUnit test of isOther.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#isOther()
     */
    @Test
    public void testIsOther() throws Exception {
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertFalse(OperatingSystem.isOther());
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertFalse(OperatingSystem.isOther());
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertFalse(OperatingSystem.isOther());
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertFalse(OperatingSystem.isOther());
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertTrue(OperatingSystem.isOther());
        }
    }
    
    /**
     * JUnit test of is.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#is(OperatingSystem.OS)
     */
    @Test
    public void testIs() throws Exception {
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertTrue(OperatingSystem.is(OperatingSystem.OS.WINDOWS));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.UNIX));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.MACOS));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.POSIX));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.OTHER));
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.WINDOWS));
            Assert.assertTrue(OperatingSystem.is(OperatingSystem.OS.UNIX));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.MACOS));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.POSIX));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.OTHER));
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.WINDOWS));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.UNIX));
            Assert.assertTrue(OperatingSystem.is(OperatingSystem.OS.MACOS));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.POSIX));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.OTHER));
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.WINDOWS));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.UNIX));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.MACOS));
            Assert.assertTrue(OperatingSystem.is(OperatingSystem.OS.POSIX));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.OTHER));
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.WINDOWS));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.UNIX));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.MACOS));
            Assert.assertFalse(OperatingSystem.is(OperatingSystem.OS.POSIX));
            Assert.assertTrue(OperatingSystem.is(OperatingSystem.OS.OTHER));
        }
    }
    
    /**
     * JUnit test of is32Bit.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#is32Bit()
     */
    @Test
    public void testIs32Bit() throws Exception {
        Assert.assertTrue(OperatingSystem.is32Bit() || OperatingSystem.is64Bit());
        Assert.assertNotEquals(OperatingSystem.is32Bit(), OperatingSystem.is64Bit());
    }
    
    /**
     * JUnit test of is64Bit.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#is64Bit()
     */
    @Test
    public void testIs64Bit() throws Exception {
        Assert.assertTrue(OperatingSystem.is32Bit() || OperatingSystem.is64Bit());
        Assert.assertNotEquals(OperatingSystem.is32Bit(), OperatingSystem.is64Bit());
    }
    
    /**
     * JUnit test of getProcessorCount.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#getProcessorCount()
     */
    @Test
    public void testGetProcessorCount() throws Exception {
        TestUtils.assertNoException(() ->
                Assert.assertNotEquals(0, OperatingSystem.getProcessorCount()));
        
        Assert.assertEquals(OperatingSystem.getProcessorCount(), OperatingSystem.getProcessorCount());
    }
    
    /**
     * JUnit test of getMaximumMemory.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#getMaximumMemory()
     */
    @Test
    public void testGetMaximumMemory() throws Exception {
        TestUtils.assertNoException(() ->
                Assert.assertNotEquals(0, OperatingSystem.getMaximumMemory()));
        
        Assert.assertEquals(OperatingSystem.getMaximumMemory(), OperatingSystem.getMaximumMemory());
    }
    
    /**
     * JUnit test of getTotalMemory.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#getTotalMemory()
     */
    @Test
    public void testGetTotalMemory() throws Exception {
        TestUtils.assertNoException(() ->
                Assert.assertNotEquals(0, OperatingSystem.getTotalMemory()));
        
        Assert.assertTrue(OperatingSystem.getTotalMemory() <= OperatingSystem.getMaximumMemory());
        Assert.assertTrue(OperatingSystem.getTotalMemory() > OperatingSystem.getFreeMemory());
    }
    
    /**
     * JUnit test of getFreeMemory.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#getFreeMemory()
     */
    @Test
    public void testGetFreeMemory() throws Exception {
        TestUtils.assertNoException(() ->
                Assert.assertNotEquals(0, OperatingSystem.getFreeMemory()));
        
        Assert.assertTrue(OperatingSystem.getFreeMemory() < OperatingSystem.getTotalMemory());
    }
    
    /**
     * JUnit test of getUsedMemory.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#getUsedMemory()
     */
    @Test
    public void testGetUsedMemory() throws Exception {
        TestUtils.assertNoException(() ->
                Assert.assertNotEquals(0, OperatingSystem.getUsedMemory()));
        
        Assert.assertTrue(OperatingSystem.getUsedMemory() <= OperatingSystem.getTotalMemory());
    }
    
    /**
     * JUnit test of isDebugging.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem#isDebugging()
     */
    @Test
    public void testIsDebugging() throws Exception {
        boolean isDebugging = (boolean) TestUtils.getField(OperatingSystem.class, "DEBUGGING");
        Assert.assertEquals(isDebugging, OperatingSystem.isDebugging());
        Assert.assertEquals(isDebugging, OperatingSystem.isDebugging());
    }
    
}
