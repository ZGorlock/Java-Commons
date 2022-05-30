/*
 * File:    Clipboard.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;

import commons.io.file.media.image.ImageUtility;
import commons.log.CommonsLogging;
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
    
    
    //Static Methods
    
    /**
     * Returns the contents of the clipboard as a string.
     *
     * @return The contents of the clipboard.
     */
    public static String getClipboard() {
        String clipboard;
        try {
            clipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (Exception ignored) {
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
     * @return Whether the string was successfully published to the clipboard or not.
     */
    public static boolean putClipboard(String content) {
        StringSelection selection = new StringSelection(content);
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
        } catch (Exception ignored) {
            if (logClipboard()) {
                logger.trace("Clipboard: Unable to publish contents to the clipboard");
            }
            return false;
        }
        if (logClipboard()) {
            logger.trace("Clipboard: Published contents to the clipboard");
        }
        return true;
    }
    
    /**
     * Returns the contents of the clipboard as an image.
     *
     * @return The contents of the clipboard, or null if there is no image on the clipboard.
     * @see ImageUtility#copyImageFromClipboard()
     */
    public static BufferedImage getClipboardImage() {
        return ImageUtility.copyImageFromClipboard();
    }
    
    /**
     * Publishes an image to the clipboard.
     *
     * @param content The new content of the clipboard.
     * @return Whether the image was copied to the clipboard or not.
     * @see ImageUtility#copyImageToClipboard(BufferedImage)
     */
    public static boolean putClipboardImage(BufferedImage content) {
        return ImageUtility.copyImageToClipboard(content);
    }
    
    /**
     * Determines if clipboard logging is enabled or not.
     *
     * @return Whether clipboard logging is enabled or not.
     */
    public static boolean logClipboard() {
        return CommonsLogging.logClipboard();
    }
    
}
