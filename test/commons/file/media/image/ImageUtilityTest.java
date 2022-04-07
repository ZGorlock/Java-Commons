/*
 * File:    ImageUtilityTest.java
 * Package: commons.file.media.image
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.file.media.image;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import commons.access.Filesystem;
import commons.access.Project;
import commons.graphics.ColorUtility;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ImageUtility.
 *
 * @see ImageUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ImageUtility.class, Filesystem.class, Toolkit.class})
public class ImageUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ImageUtilityTest.class);
    
    
    //Constants
    
    /**
     * The directory containing resources for this test class.
     */
    private static final File testResources = Project.testResourcesDir(ImageUtility.class);
    
    
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
    @SuppressWarnings("EmptyMethod")
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
        BufferedImage image;
        
        //standard
        image = ImageUtility.loadImage(new File(testResources, "test.bmp"));
        Assert.assertNotNull(image);
        Assert.assertEquals(60, image.getWidth());
        Assert.assertEquals(40, image.getHeight());
        image = ImageUtility.loadImage(new File(testResources, "test.gif"));
        Assert.assertNotNull(image);
        Assert.assertEquals(60, image.getWidth());
        Assert.assertEquals(40, image.getHeight());
        image = ImageUtility.loadImage(new File(testResources, "test.jpeg"));
        Assert.assertNotNull(image);
        Assert.assertEquals(60, image.getWidth());
        Assert.assertEquals(40, image.getHeight());
        image = ImageUtility.loadImage(new File(testResources, "test.jpg"));
        Assert.assertNotNull(image);
        Assert.assertEquals(60, image.getWidth());
        Assert.assertEquals(40, image.getHeight());
        image = ImageUtility.loadImage(new File(testResources, "test.png"));
        Assert.assertNotNull(image);
        Assert.assertEquals(60, image.getWidth());
        Assert.assertEquals(40, image.getHeight());
        image = ImageUtility.loadImage(new File(testResources, "test.tif"));
        Assert.assertNotNull(image);
        Assert.assertEquals(60, image.getWidth());
        Assert.assertEquals(40, image.getHeight());
        image = ImageUtility.loadImage(new File(testResources, "test.tiff"));
        Assert.assertNotNull(image);
        Assert.assertEquals(60, image.getWidth());
        Assert.assertEquals(40, image.getHeight());
        
        //invalid
        File txtFile = Filesystem.getTemporaryFile("txt");
        Filesystem.writeStringToFile(txtFile, "test");
        image = ImageUtility.loadImage(txtFile);
        Assert.assertNull(image);
        image = ImageUtility.loadImage(new File(testResources, "test.nothing"));
        Assert.assertNull(image);
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.loadImage(null)));
    }
    
    /**
     * JUnit test of saveImage.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#saveImage(BufferedImage, File)
     */
    @Test
    public void testSaveImage() throws Exception {
        BufferedImage image;
        BufferedImage loadedImage;
        File savedImage;
        
        //standard
        
        for (String imageType : new String[] {"bmp", "gif", "jpeg", "jpg", "png", "tif", "tiff"}) {
            image = ImageUtility.loadImage(new File(testResources, "test." + imageType));
            
            savedImage = Filesystem.getTemporaryFile("bmp");
            Assert.assertTrue(ImageUtility.saveImage(image, savedImage));
            loadedImage = ImageUtility.loadImage(savedImage);
            Assert.assertEquals(image.getWidth(), loadedImage.getWidth());
            Assert.assertEquals(image.getHeight(), loadedImage.getHeight());
            Assert.assertTrue(ImageUtility.pixelsEqual(image, loadedImage));
            
            savedImage = Filesystem.getTemporaryFile("gif");
            Assert.assertTrue(ImageUtility.saveImage(image, savedImage));
            loadedImage = ImageUtility.loadImage(savedImage);
            Assert.assertEquals(image.getWidth(), loadedImage.getWidth());
            Assert.assertEquals(image.getHeight(), loadedImage.getHeight());
            Assert.assertTrue(ImageUtility.pixelsEqual(image, loadedImage) || !imageType.equals("gif"));
            
            savedImage = Filesystem.getTemporaryFile("jpeg");
            Assert.assertTrue(ImageUtility.saveImage(image, savedImage));
            loadedImage = ImageUtility.loadImage(savedImage);
            Assert.assertEquals(image.getWidth(), loadedImage.getWidth());
            Assert.assertEquals(image.getHeight(), loadedImage.getHeight());
            Assert.assertFalse(ImageUtility.pixelsEqual(image, loadedImage));
            
            savedImage = Filesystem.getTemporaryFile("jpg");
            Assert.assertTrue(ImageUtility.saveImage(image, savedImage));
            loadedImage = ImageUtility.loadImage(savedImage);
            Assert.assertEquals(image.getWidth(), loadedImage.getWidth());
            Assert.assertEquals(image.getHeight(), loadedImage.getHeight());
            Assert.assertFalse(ImageUtility.pixelsEqual(image, loadedImage));
            
            savedImage = Filesystem.getTemporaryFile("png");
            Assert.assertTrue(ImageUtility.saveImage(image, savedImage));
            loadedImage = ImageUtility.loadImage(savedImage);
            Assert.assertEquals(image.getWidth(), loadedImage.getWidth());
            Assert.assertEquals(image.getHeight(), loadedImage.getHeight());
            Assert.assertTrue(ImageUtility.pixelsEqual(image, loadedImage));
            
            savedImage = Filesystem.getTemporaryFile("tif");
            Assert.assertTrue(ImageUtility.saveImage(image, savedImage));
            loadedImage = ImageUtility.loadImage(savedImage);
            Assert.assertEquals(image.getWidth(), loadedImage.getWidth());
            Assert.assertEquals(image.getHeight(), loadedImage.getHeight());
            Assert.assertTrue(ImageUtility.pixelsEqual(image, loadedImage));
            
            savedImage = Filesystem.getTemporaryFile("tiff");
            Assert.assertTrue(ImageUtility.saveImage(image, savedImage));
            loadedImage = ImageUtility.loadImage(savedImage);
            Assert.assertEquals(image.getWidth(), loadedImage.getWidth());
            Assert.assertEquals(image.getHeight(), loadedImage.getHeight());
            Assert.assertTrue(ImageUtility.pixelsEqual(image, loadedImage));
        }
        
        //invalid
        
        image = ImageUtility.loadImage(new File(testResources, "test.jpg"));
        Assert.assertFalse(ImageUtility.saveImage(image, Filesystem.createTemporaryFile("txt")));
        
        image = ImageUtility.loadImage(new File(testResources, "test.jpg"));
        Assert.assertFalse(ImageUtility.saveImage(image, Filesystem.createTemporaryFile()));
        
        TestUtils.assertNoException(() ->
                Assert.assertFalse(ImageUtility.saveImage(null, new File(Project.TMP_DIR, "test.jpg"))));
        TestUtils.assertNoException(() ->
                Assert.assertFalse(ImageUtility.saveImage(new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB), null)));
        TestUtils.assertNoException(() ->
                Assert.assertFalse(ImageUtility.saveImage(null, null)));
    }
    
    /**
     * JUnit test of getDimensions.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getDimensions(File)
     */
    @Test
    public void testGetDimensions() throws Exception {
        Dimension dimension;
        
        //standard
        dimension = ImageUtility.getDimensions(new File(testResources, "test.bmp"));
        Assert.assertNotNull(dimension);
        Assert.assertEquals(new Dimension(60, 40), dimension);
        dimension = ImageUtility.getDimensions(new File(testResources, "test.gif"));
        Assert.assertNotNull(dimension);
        Assert.assertEquals(new Dimension(60, 40), dimension);
        dimension = ImageUtility.getDimensions(new File(testResources, "test.jpeg"));
        Assert.assertNotNull(dimension);
        Assert.assertEquals(new Dimension(60, 40), dimension);
        dimension = ImageUtility.getDimensions(new File(testResources, "test.jpg"));
        Assert.assertNotNull(dimension);
        Assert.assertEquals(new Dimension(60, 40), dimension);
        dimension = ImageUtility.getDimensions(new File(testResources, "test.png"));
        Assert.assertNotNull(dimension);
        Assert.assertEquals(new Dimension(60, 40), dimension);
        dimension = ImageUtility.getDimensions(new File(testResources, "test.tif"));
        Assert.assertNotNull(dimension);
        Assert.assertEquals(new Dimension(60, 40), dimension);
        dimension = ImageUtility.getDimensions(new File(testResources, "test.tiff"));
        Assert.assertNotNull(dimension);
        Assert.assertEquals(new Dimension(60, 40), dimension);
        
        //invalid
        File txtFile = Filesystem.getTemporaryFile("txt");
        Filesystem.writeStringToFile(txtFile, "test");
        dimension = ImageUtility.getDimensions(txtFile);
        Assert.assertNull(dimension);
        dimension = ImageUtility.getDimensions(new File(testResources, "test.nothing"));
        Assert.assertNull(dimension);
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.getDimensions(null)));
    }
    
    /**
     * JUnit test of getWidth.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getWidth(File)
     */
    @Test
    public void testGetWidth() throws Exception {
        //standard
        Assert.assertEquals(60, ImageUtility.getWidth(new File(testResources, "test.bmp")));
        Assert.assertEquals(60, ImageUtility.getWidth(new File(testResources, "test.gif")));
        Assert.assertEquals(60, ImageUtility.getWidth(new File(testResources, "test.jpg")));
        Assert.assertEquals(60, ImageUtility.getWidth(new File(testResources, "test.jpeg")));
        Assert.assertEquals(60, ImageUtility.getWidth(new File(testResources, "test.png")));
        Assert.assertEquals(60, ImageUtility.getWidth(new File(testResources, "test.tif")));
        Assert.assertEquals(60, ImageUtility.getWidth(new File(testResources, "test.tiff")));
        
        //invalid
        File txtFile = Filesystem.getTemporaryFile("txt");
        Filesystem.writeStringToFile(txtFile, "test");
        Assert.assertEquals(-1, ImageUtility.getWidth(txtFile));
        Assert.assertEquals(-1, ImageUtility.getWidth(new File(testResources, "test.nothing")));
        TestUtils.assertNoException(() ->
                Assert.assertEquals(-1, ImageUtility.getWidth(null)));
    }
    
    /**
     * JUnit test of getHeight.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getHeight(File)
     */
    @Test
    public void testGetHeight() throws Exception {
        //standard
        Assert.assertEquals(40, ImageUtility.getHeight(new File(testResources, "test.bmp")));
        Assert.assertEquals(40, ImageUtility.getHeight(new File(testResources, "test.gif")));
        Assert.assertEquals(40, ImageUtility.getHeight(new File(testResources, "test.jpg")));
        Assert.assertEquals(40, ImageUtility.getHeight(new File(testResources, "test.jpeg")));
        Assert.assertEquals(40, ImageUtility.getHeight(new File(testResources, "test.png")));
        Assert.assertEquals(40, ImageUtility.getHeight(new File(testResources, "test.tif")));
        Assert.assertEquals(40, ImageUtility.getHeight(new File(testResources, "test.tiff")));
        
        //invalid
        File txtFile = Filesystem.getTemporaryFile("txt");
        Filesystem.writeStringToFile(txtFile, "test");
        Assert.assertEquals(-1, ImageUtility.getHeight(txtFile));
        Assert.assertEquals(-1, ImageUtility.getHeight(new File(testResources, "test.nothing")));
        TestUtils.assertNoException(() ->
                Assert.assertEquals(-1, ImageUtility.getHeight(null)));
    }
    
    /**
     * JUnit test of getDateTaken.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getDateTaken(File)
     */
    @Test
    public void testGetDateTaken() throws Exception {
        Date dateTaken;
        
        //standard
        dateTaken = ImageUtility.getDateTaken(new File(testResources, "test.bmp"));
        Assert.assertNotNull(dateTaken);
        Assert.assertEquals(new Date(1624426852801L), dateTaken);
        dateTaken = ImageUtility.getDateTaken(new File(testResources, "test.gif"));
        Assert.assertNotNull(dateTaken);
        Assert.assertEquals(new Date(1624426852801L), dateTaken);
        dateTaken = ImageUtility.getDateTaken(new File(testResources, "test.jpeg"));
        Assert.assertNotNull(dateTaken);
        Assert.assertEquals(new Date(1624426852802L), dateTaken);
        dateTaken = ImageUtility.getDateTaken(new File(testResources, "test.jpg"));
        Assert.assertNotNull(dateTaken);
        Assert.assertEquals(new Date(1624426852802L), dateTaken);
        dateTaken = ImageUtility.getDateTaken(new File(testResources, "test.png"));
        Assert.assertNotNull(dateTaken);
        Assert.assertEquals(new Date(1624426852803L), dateTaken);
        dateTaken = ImageUtility.getDateTaken(new File(testResources, "test.tif"));
        Assert.assertNotNull(dateTaken);
        Assert.assertEquals(new Date(1624426852803L), dateTaken);
        dateTaken = ImageUtility.getDateTaken(new File(testResources, "test.tiff"));
        Assert.assertNotNull(dateTaken);
        Assert.assertEquals(new Date(1624426852804L), dateTaken);
        dateTaken = ImageUtility.getDateTaken(new File(testResources, "dateTaken.jpg"));
        Assert.assertNotNull(dateTaken);
        Assert.assertEquals(new Date(1026590308000L), dateTaken);
        
        //invalid
        File txtFile = Filesystem.getTemporaryFile("txt");
        Filesystem.writeStringToFile(txtFile, "test");
        dateTaken = ImageUtility.getDateTaken(txtFile);
        Assert.assertNull(dateTaken);
        dateTaken = ImageUtility.getDateTaken(new File(testResources, "test.nothing"));
        Assert.assertNull(dateTaken);
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.getDateTaken(null)));
    }
    
    /**
     * JUnit test of pixelsEqual.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#pixelsEqual(BufferedImage, BufferedImage)
     */
    @Test
    public void testPixelsEqual() throws Exception {
        BufferedImage image;
        BufferedImage otherImage;
        
        //standard
        
        image = ImageUtility.loadImage(new File(testResources, "test.jpg"));
        Assert.assertNotNull(image);
        Assert.assertEquals(60, image.getWidth());
        Assert.assertEquals(40, image.getHeight());
        otherImage = new BufferedImage(60, 40, BufferedImage.TYPE_INT_RGB);
        Assert.assertFalse(ImageUtility.pixelsEqual(image, otherImage));
        for (int x = 0; x < 60; x++) {
            for (int y = 0; y < 40; y++) {
                otherImage.setRGB(x, y, image.getRGB(x, y));
            }
        }
        Assert.assertTrue(ImageUtility.pixelsEqual(image, otherImage));
        
        image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        otherImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                int rgb = ColorUtility.getRandomColor().getRGB();
                image.setRGB(x, y, rgb);
                otherImage.setRGB(x, y, rgb);
            }
        }
        Assert.assertTrue(ImageUtility.pixelsEqual(image, otherImage));
        
        image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        otherImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                image.setRGB(x, y, ColorUtility.getRandomColor().getRGB());
                otherImage.setRGB(x, y, ColorUtility.getRandomColor().getRGB());
            }
        }
        Assert.assertFalse(ImageUtility.pixelsEqual(image, otherImage));
        
        //empty
        
        image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        otherImage = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        Assert.assertTrue(ImageUtility.pixelsEqual(image, otherImage));
        
        //invalid
        
        image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        otherImage = new BufferedImage(600, 200, BufferedImage.TYPE_INT_RGB);
        Assert.assertFalse(ImageUtility.pixelsEqual(image, otherImage));
        
        image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        otherImage = new BufferedImage(300, 400, BufferedImage.TYPE_INT_RGB);
        Assert.assertFalse(ImageUtility.pixelsEqual(image, otherImage));
        
        image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        otherImage = new BufferedImage(300, 200, BufferedImage.TYPE_INT_RGB);
        Assert.assertFalse(ImageUtility.pixelsEqual(image, otherImage));
        
        final BufferedImage image1 = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        final BufferedImage image2 = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        TestUtils.assertNoException(() ->
                Assert.assertFalse(ImageUtility.pixelsEqual(image1, null)));
        TestUtils.assertNoException(() ->
                Assert.assertFalse(ImageUtility.pixelsEqual(null, image2)));
        TestUtils.assertNoException(() ->
                Assert.assertFalse(ImageUtility.pixelsEqual(null, null)));
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
        final File originalUncleanedImageFile = new File(testResources, "cleanImageFile.jpg");
        final BufferedImage originalUncleanedImage = ImageUtility.loadImage(originalUncleanedImageFile);
        final Map<String, FileTime> originalUncleanedImageDates = Filesystem.readDates(originalUncleanedImageFile);
        File imageFile;
        BufferedImage image;
        Map<String, FileTime> imageDates;
        
        //uncleaned
        imageFile = Filesystem.getTemporaryFile("jpg");
        Assert.assertTrue(Filesystem.copyFile(originalUncleanedImageFile, imageFile));
        Assert.assertTrue(imageFile.exists());
        Assert.assertEquals(2260480L, imageFile.length());
        image = ImageUtility.loadImage(imageFile);
        Assert.assertNotNull(image);
        Assert.assertEquals(2400, image.getWidth());
        Assert.assertEquals(1800, image.getHeight());
        imageDates = Filesystem.readDates(imageFile);
        Assert.assertNotNull(imageDates);
        Assert.assertEquals(1624426852796L, imageDates.get("lastModifiedTime").toMillis());
        Assert.assertEquals(1624337753000L, ImageUtility.getDateTaken(imageFile).getTime());
        
        //clean
        imageFile = Filesystem.getTemporaryFile("jpg");
        Assert.assertTrue(Filesystem.copyFile(originalUncleanedImageFile, imageFile));
        Assert.assertTrue(ImageUtility.cleanImageFile(imageFile, false));
        Assert.assertTrue(imageFile.exists());
        Assert.assertEquals(198895L, imageFile.length());
        image = ImageUtility.loadImage(imageFile);
        Assert.assertNotNull(image);
        Assert.assertEquals(2400, image.getWidth());
        Assert.assertEquals(1800, image.getHeight());
        imageDates = Filesystem.readDates(imageFile);
        Assert.assertNotNull(imageDates);
        Assert.assertEquals(1624426852796L, imageDates.get("lastModifiedTime").toMillis());
        Assert.assertEquals(1624426852796L, ImageUtility.getDateTaken(imageFile).getTime());
        
        //clean preserve metadata
        imageFile = Filesystem.getTemporaryFile("jpg");
        Assert.assertTrue(Filesystem.copyFile(originalUncleanedImageFile, imageFile));
        Assert.assertTrue(ImageUtility.cleanImageFile(imageFile, true));
        Assert.assertTrue(imageFile.exists());
        Assert.assertEquals(1732153L, imageFile.length());
        image = ImageUtility.loadImage(imageFile);
        Assert.assertNotNull(image);
        Assert.assertEquals(2400, image.getWidth());
        Assert.assertEquals(1800, image.getHeight());
        imageDates = Filesystem.readDates(imageFile);
        Assert.assertNotNull(imageDates);
        Assert.assertEquals(1624426852796L, imageDates.get("lastModifiedTime").toMillis());
        Assert.assertEquals(1624337753000L, ImageUtility.getDateTaken(imageFile).getTime());
        
        //failure
        imageFile = Filesystem.getTemporaryFile("jpg");
        Assert.assertTrue(Filesystem.copyFile(originalUncleanedImageFile, imageFile));
        PowerMockito.mockStatic(Filesystem.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doThrow(new NullPointerException()).when(Filesystem.class, "createTemporaryFile", ArgumentMatchers.anyString());
        Assert.assertFalse(ImageUtility.cleanImageFile(imageFile));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "createTemporaryFile", ArgumentMatchers.anyString());
        PowerMockito.doReturn(false).when(Filesystem.class, "safeReplace", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        Assert.assertFalse(ImageUtility.cleanImageFile(imageFile));
        PowerMockito.doCallRealMethod().when(Filesystem.class, "safeReplace", ArgumentMatchers.any(File.class), ArgumentMatchers.any(File.class));
        
        //invalid
        TestUtils.assertNoException(() ->
                Assert.assertFalse(ImageUtility.cleanImageFile(null, false)));
        TestUtils.assertNoException(() ->
                Assert.assertFalse(ImageUtility.cleanImageFile(null)));
        
        //default preserve metadata
        PowerMockito.mockStatic(ImageUtility.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doReturn(true).when(ImageUtility.class, "cleanImageFile", ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean());
        ImageUtility.cleanImageFile(imageFile);
        PowerMockito.verifyStatic(ImageUtility.class, VerificationModeFactory.times(1));
        ImageUtility.cleanImageFile(ArgumentMatchers.eq(imageFile), ArgumentMatchers.eq(true));
        PowerMockito.doCallRealMethod().when(ImageUtility.class, "cleanImageFile", ArgumentMatchers.any(File.class), ArgumentMatchers.anyBoolean());
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
        final File testImageFile = Filesystem.getTemporaryFile("bmp");
        Assert.assertTrue(Filesystem.copyFile(new File(testResources, "test.bmp"), testImageFile));
        File verifyImageFile;
        File croppedImageFile;
        final BufferedImage image = ImageUtility.loadImage(testImageFile);
        BufferedImage croppedImage;
        BufferedImage loadedImage;
        
        //standard
        
        croppedImage = ImageUtility.cropImage(image, new Rectangle(10, 10, 30, 30));
        Assert.assertNotEquals(image, croppedImage);
        Assert.assertEquals(30, croppedImage.getWidth());
        Assert.assertEquals(30, croppedImage.getHeight());
        croppedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(croppedImage, croppedImageFile);
        croppedImage = ImageUtility.loadImage(croppedImageFile);
        verifyImageFile = new File(testResources, "cropImage1.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(croppedImage, loadedImage));
        
        croppedImage = ImageUtility.cropImage(image, 3, 5, 10, 10);
        Assert.assertNotEquals(image, croppedImage);
        Assert.assertEquals(47, croppedImage.getWidth());
        Assert.assertEquals(25, croppedImage.getHeight());
        croppedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(croppedImage, croppedImageFile);
        croppedImage = ImageUtility.loadImage(croppedImageFile);
        verifyImageFile = new File(testResources, "cropImage2.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(croppedImage, loadedImage));
        
        //oversized
        
        croppedImage = ImageUtility.cropImage(image, new Rectangle(-15, -88, 152, 3120));
        Assert.assertNotEquals(image, croppedImage);
        Assert.assertEquals(60, croppedImage.getWidth());
        Assert.assertEquals(40, croppedImage.getHeight());
        
        croppedImage = ImageUtility.cropImage(image, -15, -88, -45, -50);
        Assert.assertNotEquals(image, croppedImage);
        Assert.assertEquals(60, croppedImage.getWidth());
        Assert.assertEquals(40, croppedImage.getHeight());
        
        croppedImage = ImageUtility.cropImage(image, new Rectangle(-20, -20, 0, 0));
        Assert.assertNotEquals(image, croppedImage);
        Assert.assertEquals(1, croppedImage.getWidth());
        Assert.assertEquals(1, croppedImage.getHeight());
        
        //invalid
        
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.cropImage(null, new Rectangle(10, 10, 10, 10))));
        TestUtils.assertNoException(() ->
                Assert.assertEquals(image, ImageUtility.cropImage(image, null)));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.cropImage(null, null)));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.cropImage(null, 10, 10, 10, 10)));
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
        final File testImageFile = Filesystem.getTemporaryFile("bmp");
        Assert.assertTrue(Filesystem.copyFile(new File(testResources, "test.bmp"), testImageFile));
        File verifyImageFile;
        File scaledImageFile;
        final BufferedImage image = ImageUtility.loadImage(testImageFile);
        BufferedImage scaledImage;
        BufferedImage loadedImage;
        
        //scale up
        
        scaledImage = ImageUtility.scaleImage(image, 2.2);
        Assert.assertNotEquals(image, scaledImage);
        Assert.assertEquals(132, scaledImage.getWidth());
        Assert.assertEquals(88, scaledImage.getHeight());
        scaledImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(scaledImage, scaledImageFile);
        scaledImage = ImageUtility.loadImage(scaledImageFile);
        verifyImageFile = new File(testResources, "scaledImage1.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(scaledImage, loadedImage));
        
        //scale down
        
        scaledImage = ImageUtility.scaleImage(image, 0.44);
        Assert.assertNotEquals(image, scaledImage);
        Assert.assertEquals(26, scaledImage.getWidth());
        Assert.assertEquals(17, scaledImage.getHeight());
        scaledImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(scaledImage, scaledImageFile);
        scaledImage = ImageUtility.loadImage(scaledImageFile);
        verifyImageFile = new File(testResources, "scaledImage2.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(scaledImage, loadedImage));
        
        //max dimension
        
        scaledImage = ImageUtility.scaleImage(image, 30, 40);
        Assert.assertNotEquals(image, scaledImage);
        Assert.assertEquals(30, scaledImage.getWidth());
        Assert.assertEquals(20, scaledImage.getHeight());
        scaledImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(scaledImage, scaledImageFile);
        scaledImage = ImageUtility.loadImage(scaledImageFile);
        verifyImageFile = new File(testResources, "scaledImage3.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(scaledImage, loadedImage));
        
        scaledImage = ImageUtility.scaleImage(image, 80, 10);
        Assert.assertNotEquals(image, scaledImage);
        Assert.assertEquals(15, scaledImage.getWidth());
        Assert.assertEquals(10, scaledImage.getHeight());
        scaledImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(scaledImage, scaledImageFile);
        scaledImage = ImageUtility.loadImage(scaledImageFile);
        verifyImageFile = new File(testResources, "scaledImage4.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(scaledImage, loadedImage));
        
        //invalid
        
        scaledImage = ImageUtility.scaleImage(image, 1.0);
        Assert.assertEquals(image, scaledImage);
        scaledImage = ImageUtility.scaleImage(image, 0.0);
        Assert.assertEquals(image, scaledImage);
        scaledImage = ImageUtility.scaleImage(image, -2.2);
        Assert.assertEquals(image, scaledImage);
        
        scaledImage = ImageUtility.scaleImage(image, 60, 40);
        Assert.assertEquals(image, scaledImage);
        scaledImage = ImageUtility.scaleImage(image, 6000, 4000);
        Assert.assertEquals(image, scaledImage);
        
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.scaleImage(null, 5.0)));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.scaleImage(null, 500, 500)));
    }
    
    /**
     * JUnit test of copyImageFromClipboard.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#copyImageFromClipboard()
     */
    @Test
    public void testCopyImageFromClipboard() throws Exception {
        BufferedImage image;
        BufferedImage clipboardImage;
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        
        final AtomicReference<BufferedImage> transferableImage = new AtomicReference<>();
        final Transferable transferable = new Transferable() {
            
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
                    return transferableImage.get();
                } else {
                    throw new UnsupportedFlavorException(flavor);
                }
            }
            
        };
        
        //standard
        
        image = ImageUtility.loadImage(new File(testResources, "test.bmp"));
        transferableImage.set(image);
        clipboard.setContents(transferable, (clipboard1, contents) -> {
        });
        clipboardImage = ImageUtility.copyImageFromClipboard();
        Assert.assertNotNull(clipboardImage);
        Assert.assertSame(image, clipboardImage);
        
        image = ImageUtility.loadImage(new File(testResources, "test.gif"));
        transferableImage.set(image);
        clipboard.setContents(transferable, (clipboard1, contents) -> {
        });
        clipboardImage = ImageUtility.copyImageFromClipboard();
        Assert.assertNotNull(clipboardImage);
        Assert.assertSame(image, clipboardImage);
        
        image = ImageUtility.loadImage(new File(testResources, "test.jpg"));
        transferableImage.set(image);
        clipboard.setContents(transferable, (clipboard1, contents) -> {
        });
        clipboardImage = ImageUtility.copyImageFromClipboard();
        Assert.assertNotNull(clipboardImage);
        Assert.assertSame(image, clipboardImage);
        
        image = ImageUtility.loadImage(new File(testResources, "test.png"));
        transferableImage.set(image);
        clipboard.setContents(transferable, (clipboard1, contents) -> {
        });
        clipboardImage = ImageUtility.copyImageFromClipboard();
        Assert.assertNotNull(clipboardImage);
        Assert.assertSame(image, clipboardImage);
        
        //invalid
        
        commons.access.Clipboard.putClipboard("test");
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.copyImageFromClipboard()));
        
        commons.access.Clipboard.putClipboard(null);
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageUtility.copyImageFromClipboard()));
    }
    
    /**
     * JUnit test of copyImageToClipboard.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#copyImageToClipboard(BufferedImage)
     */
    @Test
    public void testCopyImageToClipboard() throws Exception {
        BufferedImage image;
        BufferedImage clipboardImage;
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable;
        
        //standard
        
        image = ImageUtility.loadImage(new File(testResources, "test.bmp"));
        Assert.assertTrue(ImageUtility.copyImageToClipboard(image));
        transferable = clipboard.getContents(null);
        Assert.assertNotNull(transferable);
        Assert.assertEquals(1, transferable.getTransferDataFlavors().length);
        Assert.assertEquals(DataFlavor.imageFlavor, transferable.getTransferDataFlavors()[0]);
        clipboardImage = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
        Assert.assertNotNull(clipboardImage);
        Assert.assertSame(image, clipboardImage);
        
        image = ImageUtility.loadImage(new File(testResources, "test.gif"));
        Assert.assertTrue(ImageUtility.copyImageToClipboard(image));
        transferable = clipboard.getContents(null);
        Assert.assertNotNull(transferable);
        Assert.assertEquals(1, transferable.getTransferDataFlavors().length);
        Assert.assertEquals(DataFlavor.imageFlavor, transferable.getTransferDataFlavors()[0]);
        clipboardImage = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
        Assert.assertNotNull(clipboardImage);
        Assert.assertSame(image, clipboardImage);
        
        image = ImageUtility.loadImage(new File(testResources, "test.jpg"));
        Assert.assertTrue(ImageUtility.copyImageToClipboard(image));
        transferable = clipboard.getContents(null);
        Assert.assertNotNull(transferable);
        Assert.assertEquals(1, transferable.getTransferDataFlavors().length);
        Assert.assertEquals(DataFlavor.imageFlavor, transferable.getTransferDataFlavors()[0]);
        clipboardImage = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
        Assert.assertNotNull(clipboardImage);
        Assert.assertSame(image, clipboardImage);
        
        image = ImageUtility.loadImage(new File(testResources, "test.png"));
        Assert.assertTrue(ImageUtility.copyImageToClipboard(image));
        transferable = clipboard.getContents(null);
        Assert.assertNotNull(transferable);
        Assert.assertEquals(1, transferable.getTransferDataFlavors().length);
        Assert.assertEquals(DataFlavor.imageFlavor, transferable.getTransferDataFlavors()[0]);
        clipboardImage = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
        Assert.assertNotNull(clipboardImage);
        Assert.assertSame(image, clipboardImage);
        
        //invalid
        
        TestUtils.assertNoException(() ->
                Assert.assertFalse(ImageUtility.copyImageToClipboard(null)));
    }
    
    /**
     * JUnit test of getAvailableImageFormats.
     *
     * @throws Exception When there is an exception.
     * @see ImageUtility#getAvailableImageFormats()
     */
    @Test
    public void testGetAvailableImageFormats() throws Exception {
        final List<String> imageFormats = ImageUtility.getAvailableImageFormats();
        Assert.assertTrue(imageFormats.contains("BMP"));
        Assert.assertTrue(imageFormats.contains("GIF"));
        Assert.assertTrue(imageFormats.contains("JPEG"));
        Assert.assertTrue(imageFormats.contains("JPG"));
        Assert.assertTrue(imageFormats.contains("PNG"));
        Assert.assertTrue(imageFormats.contains("TIF"));
        Assert.assertTrue(imageFormats.contains("TIFF"));
        Assert.assertTrue(imageFormats.contains("WBMP"));
    }
    
}
