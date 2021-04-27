/*
 * File:    ImageUtilityTest.java
 * Package: commons.media
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.media;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ImageUtility.
 *
 * @see ImageUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ImageUtility.class})
public class ImageUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ImageUtilityTest.class);
    
    
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
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of loadImage.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#loadImage(File)
     */
    @Test
    public void testLoadImage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of saveImage.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#saveImage(BufferedImage, File)
     */
    @Test
    public void testSaveImage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getDimensions.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getDimensions(File)
     */
    @Test
    public void testGetDimensions() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getWidth(File)
     */
    @Test
    public void testGetWidth() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getHeight(File)
     */
    @Test
    public void testGetHeight() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getDateTaken.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getDateTaken(File)
     */
    @Test
    public void testGetDateTaken() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cleanImageFile.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#cleanImageFile(File, boolean)
     * @see ImageUtility#cleanImageFile(File)
     */
    @Test
    public void testCleanImageFile() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of cropImage.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#cropImage(BufferedImage, Rectangle)
     * @see ImageUtility#cropImage(BufferedImage, int, int, int, int)
     */
    @Test
    public void testCropImage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of scaleImage.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#scaleImage(BufferedImage, double)
     * @see ImageUtility#scaleImage(BufferedImage, int, int)
     */
    @Test
    public void testScaleImage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copyImageFromClipboard.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#copyImageFromClipboard()
     */
    @Test
    public void testCopyImageFromClipboard() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of copyImageToClipboard.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#copyImageToClipboard(BufferedImage)
     */
    @Test
    public void testCopyImageToClipboard() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getAvailableImageFormats.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getAvailableImageFormats()
     */
    @Test
    public void testGetAvailableImageFormats() throws Exception {
        //TODO
    }
    
}
