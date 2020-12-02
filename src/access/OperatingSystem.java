/*
 * File:    OperatingSystem.java
 * Package: access
 * Author:  Zachary Gill
 */

package access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides access to the operating system.
 */
public final class OperatingSystem {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(OperatingSystem.class);
    
    
    //Enums
    
    /**
     * An enumeration of operating systems.
     */
    public enum OS {
        
        //Values
        
        WINDOWS,
        UNIX,
        MACOS,
        POSIX,
        OTHER
        
    }
    
    
    //Functions
    
    /**
     * Determines the current operating system.
     *
     * @return The current operating system.
     * @see #getOSName()
     */
    public static OS getOS() {
        String osName = getOSName().toUpperCase();
        
        if (osName.contains("WINDOWS")) {
            return OS.WINDOWS;
        } else if (osName.contains("LINUX") ||
                osName.contains("MPE/IX") ||
                osName.contains("FREEBSD") ||
                osName.contains("IRIX") ||
                osName.contains("UNIX")) {
            return OS.UNIX;
        } else if (osName.contains("MAC")) {
            return OS.MACOS;
        } else if (osName.contains("SUN") ||
                osName.contains("SOL") ||
                osName.contains("HP-UX") ||
                osName.contains("AIX")) {
            return OS.POSIX;
        } else {
            return OS.OTHER;
        }
    }
    
    /**
     * Returns the name of the current operating system.
     *
     * @return The name of the current operating system.
     * @see System#getProperty(String)
     */
    public static String getOSName() {
        return System.getProperty("os.name");
    }
    
    /**
     * Determines if the current operating system is Windows.
     *
     * @return Whether the current operating system is Windows or not.
     * @see #getOS()
     */
    public static boolean isWindows() {
        return getOS().equals(OperatingSystem.OS.WINDOWS);
    }
    
    /**
     * Determines if the current operating system is Unix.
     *
     * @return Whether the current operating system is Unix or not.
     * @see #getOS()
     */
    public static boolean isUnix() {
        return getOS().equals(OperatingSystem.OS.UNIX);
    }
    
    /**
     * Determines if the current operating system is macOS.
     *
     * @return Whether the current operating system is macOS or not.
     * @see #getOS()
     */
    public static boolean isMacOS() {
        return getOS().equals(OperatingSystem.OS.MACOS);
    }
    
    /**
     * Determines if the current operating system is POSIX.
     *
     * @return Whether the current operating system is POSIX or not.
     * @see #getOS()
     */
    public static boolean isPosix() {
        return getOS().equals(OperatingSystem.OS.POSIX);
    }
    
    /**
     * Determines if the current operating system is Other.
     *
     * @return Whether the current operating system is Other or not.
     * @see #getOS()
     */
    public static boolean isOther() {
        return getOS().equals(OperatingSystem.OS.OTHER);
    }
    
    /**
     * Determines if the current operating system is a particular operating system.
     *
     * @param os The operating system to test for.
     * @return Whether the current operating system is the particular operating system or not.
     * @see #getOS()
     */
    public static boolean is(OperatingSystem.OS os) {
        return getOS().equals(os);
    }
    
}
