/*
 * File:    CommonsLogging.java
 * Package: commons.log
 * Author:  Zachary Gill
 */

package commons.log;

import commons.access.Clipboard;
import commons.access.Filesystem;
import commons.access.Internet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the determination of what should be logged.
 */
public final class CommonsLogging {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CommonsLogging.class);
    
    
    //Fields
    
    /**
     * The Commons Logger implementation used to determine what should be logged.
     */
    private static CommonsLogger commonsLogger;
    
    
    //Functions
    
    /**
     * Determines whether or not Filesystem operations should be logged.
     *
     * @return Whether or not Filesystem operations should be logged.
     */
    public static boolean logFilesystem() {
        if (commonsLogger != null) {
            return commonsLogger.logFilesystem();
        }
        return Filesystem.DEFAULT_LOG_FILESYSTEM;
    }
    
    /**
     * Determines whether or not Clipboard operations should be logged.
     *
     * @return Whether or not Clipboard operations should be logged.
     */
    public static boolean logClipboard() {
        if (commonsLogger != null) {
            return commonsLogger.logClipboard();
        }
        return Clipboard.DEFAULT_LOG_CLIPBOARD;
    }
    
    /**
     * Determines whether or not Internet operations should be logged.
     *
     * @return Whether or not Internet operations should be logged.
     */
    public static boolean logInternet() {
        if (commonsLogger != null) {
            return commonsLogger.logInternet();
        }
        return Internet.DEFAULT_LOG_INTERNET;
    }
    
    
    //Getters
    
    /**
     * Returns the Commons Logger implementation being used to determine what should be logged.
     *
     * @return The Commons Logger implementation.
     */
    public static CommonsLogger getCommonsLogger() {
        return commonsLogger;
    }
    
    
    //Setters
    
    /**
     * Sets the Commons Logger implementation to use for determining what should be logged.
     *
     * @param commonsLoggerImplementation The Commons Logger implementation.
     */
    public static void setCommonsLogger(CommonsLogger commonsLoggerImplementation) {
        commonsLogger = commonsLoggerImplementation;
    }
    
}
