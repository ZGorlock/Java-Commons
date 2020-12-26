/*
 * File:    ImageUtility.java
 * Package: media
 * Author:  Zachary Gill
 */

package media;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 * Handles image operations.
 */
public class ImageUtility {
    
    //Functions
    
    /**
     * Loads an image.
     *
     * @param file The image file.
     * @return The BufferedImage loaded from the file, or null if there was an error.
     */
    public static BufferedImage loadImage(File file) {
        try {
            BufferedImage tmpImage = ImageIO.read(file);
            BufferedImage image = new BufferedImage(tmpImage.getWidth(), tmpImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D imageGraphics = image.createGraphics();
            imageGraphics.drawImage(tmpImage, 0, 0, tmpImage.getWidth(), tmpImage.getHeight(), null);
            imageGraphics.dispose();
            return image;
        } catch (Exception ignored) {
            return null;
        }
    }
    
    /**
     * Saves an image to a file.
     *
     * @param image The image.
     * @param file  The output file.
     */
    public static void saveImage(BufferedImage image, File file) {
        try {
            if (file.mkdirs()) {
                ImageIO.write(image, "jpg", file);
            }
        } catch (Exception ignored) {
        }
    }
    
    /**
     * Copies an image to the clipboard.
     *
     * @param image The image.
     */
    public static void copyImageToClipboard(final BufferedImage image) {
        Transferable transferableImage = new Transferable() {
            
            @Override
            public DataFlavor[] getTransferDataFlavors() {
                DataFlavor[] flavors = new DataFlavor[1];
                flavors[0] = DataFlavor.imageFlavor;
                return flavors;
            }
            
            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                DataFlavor[] flavors = getTransferDataFlavors();
                return Arrays.stream(flavors).anyMatch(flavor::equals);
            }
            
            @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                if (flavor.equals(DataFlavor.imageFlavor)) {
                    return image;
                } else {
                    throw new UnsupportedFlavorException(flavor);
                }
            }
            
        };
        
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(transferableImage, (clipboard1, contents) -> {
            });
        } catch (Exception ignored) {
        }
    }
    
}
