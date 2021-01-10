/*
 * File:    ImageUtility.java
 * Package: media
 * Author:  Zachary Gill
 */

package media;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import access.Filesystem;
import math.BoundUtility;

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
     * @return Whether the image was successfully saved or not.
     */
    public static boolean saveImage(BufferedImage image, File file) {
        try {
            return file.mkdirs() && ImageIO.write(image, Filesystem.getFileType(file), file);
        } catch (Exception ignored) {
        }
        return false;
    }
    
    /**
     * Deletes the extra data in an image file, reducing the file size.
     *
     * @param image            The image file.
     * @param preserveMetadata Whether or not to preserve the metadata of the image file.
     * @return Whether the image file was successfully cleaned or not.
     */
    public static boolean cleanImageFile(File image, boolean preserveMetadata) {
        try {
            File tmp = Filesystem.createTemporaryFile(Filesystem.getFileType(image));
            
            if (preserveMetadata) {
                Map<String, FileTime> dates = Filesystem.readDates(image);
                try (FileInputStream fileInputStream = new FileInputStream(image);
                     FileOutputStream fileOutputStream = new FileOutputStream(tmp)) {
                    
                    ImageInputStream imageInputStream = ImageIO.createImageInputStream(fileInputStream);
                    ImageReader reader = ImageIO.getImageReaders(imageInputStream).next();
                    reader.setInput(imageInputStream);
                    IIOMetadata metadata = reader.getImageMetadata(0);
                    BufferedImage data = reader.read(0);
                    imageInputStream.flush();
                    
                    ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(fileOutputStream);
                    ImageWriter writer = ImageIO.getImageWriter(reader);
                    writer.setOutput(imageOutputStream);
                    ImageWriteParam params = writer.getDefaultWriteParam();
                    writer.write(null, new IIOImage(data, null, null), params);
                    writer.dispose();
                    ImageIO.write(data, Filesystem.getFileType(image), imageOutputStream);
                    imageOutputStream.flush();
                }
                Filesystem.writeDates(tmp, dates);
                
            } else {
                BufferedImage data = ImageIO.read(image);
                ImageIO.write(data, Filesystem.getFileType(image), tmp);
            }
            
            return Filesystem.safeReplace(tmp, image);
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Deletes the extra data in an image file, reducing the file size.
     *
     * @param image The image file.
     * @return Whether the image file was successfully cleaned or not.
     * @see #cleanImageFile(File, boolean)
     */
    public static boolean cleanImageFile(File image) {
        return cleanImageFile(image, true);
    }
    
    /**
     * Crops an image.
     *
     * @param image The image.
     * @param rect  The bounds within the image to crop.
     * @return The cropped image.
     */
    public static BufferedImage cropImage(BufferedImage image, Rectangle rect) {
        rect.x = BoundUtility.truncateNum(rect.getX(), 0, image.getWidth() - 1).intValue();
        rect.y = BoundUtility.truncateNum(rect.getY(), 0, image.getHeight() - 1).intValue();
        rect.width = BoundUtility.truncateNum(rect.getWidth(), 1, image.getWidth() - rect.x).intValue();
        rect.height = BoundUtility.truncateNum(rect.getHeight(), 1, image.getHeight() - rect.y).intValue();
        
        BufferedImage cropped = new BufferedImage((int) rect.getWidth(), (int) rect.getHeight(), image.getType());
        Graphics g = cropped.getGraphics();
        g.drawImage(image, 0, 0, (int) rect.getWidth(), (int) rect.getHeight(), (int) rect.getX(), (int) rect.getY(), (int) (rect.getX() + rect.getWidth()), (int) (rect.getY() + rect.getHeight()), null);
        g.dispose();
        return cropped;
    }
    
    /**
     * Crops an image.
     *
     * @param image        The image.
     * @param offTheLeft   The number of pixels to crop off the left.
     * @param offTheTop    The number of pixels to crop off the top.
     * @param offTheRight  The number of pixels to crop off the right.
     * @param offTheBottom The number of pixels to crop off the bottom.
     * @return The cropped image.
     * @see #cropImage(BufferedImage, Rectangle)
     */
    public static BufferedImage cropImage(BufferedImage image, int offTheLeft, int offTheTop, int offTheRight, int offTheBottom) {
        offTheLeft = BoundUtility.truncateNum(offTheLeft, 0, image.getWidth() - 1).intValue();
        offTheTop = BoundUtility.truncateNum(offTheTop, 0, image.getHeight() - 1).intValue();
        offTheRight = BoundUtility.truncateNum(offTheRight, 0, image.getWidth() - 1).intValue();
        offTheBottom = BoundUtility.truncateNum(offTheBottom, 0, image.getHeight() - 1).intValue();
        
        Rectangle rect = new Rectangle(offTheLeft, offTheTop,
                BoundUtility.truncateNum((image.getWidth() - offTheLeft - offTheRight), 1, image.getWidth() - offTheLeft).intValue(),
                BoundUtility.truncateNum((image.getHeight() - offTheTop - offTheBottom), 1, image.getHeight() - offTheTop).intValue());
        return cropImage(image, rect);
    }
    
    /**
     * Scales an image.
     *
     * @param image The image.
     * @param scale The factor to scale the image by.
     * @return The scaled image.
     */
    public static BufferedImage scaleImage(BufferedImage image, double scale) {
        if (scale == 1.0) {
            return image;
        }
        
        AffineTransform transform = new AffineTransform();
        transform.scale(scale, scale);
        AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage scaled = new BufferedImage((int) (image.getWidth() * scale), (int) (image.getHeight() * scale), image.getType());
        return transformOp.filter(image, scaled);
    }
    
    /**
     * Scales an image to a maximum width or height.
     *
     * @param image     The image.
     * @param maxWidth  The maximum width of the final image.
     * @param maxHeight The maximum height of the final image.
     * @return The scaled image with the specified maximum width or height, or the original image if it is within the specified maximum dimensions.
     * @see #scaleImage(BufferedImage, double)
     */
    public static BufferedImage scaleImage(BufferedImage image, int maxWidth, int maxHeight) {
        if ((image.getWidth() <= maxWidth) && (image.getHeight() <= maxHeight)) {
            return image;
        }
        
        double scale = Math.min(((double) maxWidth / image.getWidth()), ((double) maxHeight / image.getHeight()));
        return scaleImage(image, scale);
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
    
    /**
     * Copies an image from the clipboard.
     *
     * @return The image, or null if there is no image on the clipboard.
     */
    public static BufferedImage copyImageFromClipboard() {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            try {
                return (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
            } catch (Exception ignored) {
            }
        }
        return null;
    }
    
    /**
     * Returns a list of available image formats.
     *
     * @return A list of available image formats.
     */
    public static List<String> getAvailableImageFormats() {
        List<String> imageFormats = new ArrayList<>();
        Iterator<ImageWriterSpi> serviceProviders = IIORegistry.getDefaultInstance()
                .getServiceProviders(ImageWriterSpi.class, false);
        while (serviceProviders.hasNext()) {
            imageFormats.addAll(Arrays.asList(serviceProviders.next().getFormatNames()));
        }
        return imageFormats;
    }
    
}