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
    private static final Logger logger = LoggerFactory.getLogger(Filesystem.class);
    
    
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
            logger.trace("Unable to retrieve contents from the clipboard.");
            return "";
        }
        logger.trace("Retrieved contents of the clipboard.");
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
        logger.trace("Published contents to the clipboard.");
    }
    
}
