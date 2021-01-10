/*
 * File:    Clipboard.java
 * Package: access
 * Author:  Zachary Gill
 */

package access;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides access to the clipboard.
 */
public final class Clipboard {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Clipboard.class);
    
    
    //Constants
    
    /**
     * The default value of the flag to enable clipboard logging or not.
     */
    public static final boolean DEFAULT_LOG_CLIPBOARD = false;
    
    
    //Functions
    
    /**
     * Returns the contents of the clipboard as a string.
     *
     * @return The contents of the clipboard.
     */
    public static String getClipboard() {
        String clipboard;
        try {
            clipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (HeadlessException | UnsupportedFlavorException | IOException ignored) {
            if (logClipboard()) {
                logger.trace("Clipboard: Unable to retrieve contents from the clipboard");
            }
            return "";
        }
        if (logClipboard()) {
            logger.trace("Clipboard: Retrieved contents of the clipboard");
        }
        return clipboard;
    }
    
    /**
     * Publishes a string to the clipboard.
     *
     * @param content The new content of the clipboard.
     */
    public static void putClipboard(String content) {
        StringSelection selection = new StringSelection(content);
        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        if (logClipboard()) {
            logger.trace("Clipboard: Published contents to the clipboard");
        }
    }
    
    /**
     * Determines if clipboard logging is enabled or not.
     *
     * @return Whether clipboard logging is enabled or not.
     */
    public static boolean logClipboard() {
        return DEFAULT_LOG_CLIPBOARD;
    }
    
}
