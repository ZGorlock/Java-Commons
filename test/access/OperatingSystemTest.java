/*
 * File:    OperatingSystemTest.java
 * Package: dla.resource.access
 * Author:  Zachary Gill
 */

package access;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of OperatingSystem.
 *
 * @see OperatingSystem
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
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
        PowerMockito.when(OperatingSystem.getOSName()).thenAnswer((Answer<String>) invocationOnMock -> osName);
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
     * JUnit test of getOS.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem.OS#getOS()
     */
    @Test
    public void testGetOS() throws Exception {
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertEquals(OperatingSystem.OS.WINDOWS, OperatingSystem.getOS());
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertEquals(OperatingSystem.OS.UNIX, OperatingSystem.getOS());
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertEquals(OperatingSystem.OS.MACOS, OperatingSystem.getOS());
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertEquals(OperatingSystem.OS.POSIX, OperatingSystem.getOS());
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertEquals(OperatingSystem.OS.OTHER, OperatingSystem.getOS());
        }
    }
    
    /**
     * JUnit test of getOSName.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem.OS#getOSName()
     */
    @Test
    public void testGetOSName() throws Exception {
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getProperty("os.name")).thenAnswer((Answer<String>) invocationOnMock -> osName);
        PowerMockito.when(OperatingSystem.getOSName()).thenCallRealMethod();
        
        //Windows
        for (String windowsOs : WINDOWS_OS_EXAMPLES) {
            osName = windowsOs;
            Assert.assertEquals(osName, OperatingSystem.getOSName());
        }
        
        //Unix
        for (String unixOs : UNIX_OS_EXAMPLES) {
            osName = unixOs;
            Assert.assertEquals(osName, OperatingSystem.getOSName());
        }
        
        //MacOS
        for (String macOs : MAC_OS_EXAMPLES) {
            osName = macOs;
            Assert.assertEquals(osName, OperatingSystem.getOSName());
        }
        
        //POSIX
        for (String posixOs : POSIX_OS_EXAMPLES) {
            osName = posixOs;
            Assert.assertEquals(osName, OperatingSystem.getOSName());
        }
        
        //Other
        for (String otherOs : OTHER_OS_EXAMPLES) {
            osName = otherOs;
            Assert.assertEquals(osName, OperatingSystem.getOSName());
        }
    }
    
    /**
     * JUnit test of isWindows.
     *
     * @throws Exception When there is an exception.
     * @see OperatingSystem.OS#isWindows()
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
     * @see OperatingSystem.OS#isUnix()
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
     * @see OperatingSystem.OS#isMacOS()
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
     * @see OperatingSystem.OS#isPosix()
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
     * @see OperatingSystem.OS#isOther()
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
     * @see OperatingSystem.OS#is(OperatingSystem.OS)
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
    
}
