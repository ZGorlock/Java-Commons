/*
 * File:    ImageTransformationUtilityTest.java
 * Package: commons.graphics
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commons.access.Filesystem;
import commons.access.Project;
import commons.file.media.image.ImageUtility;
import commons.math.component.matrix.Matrix;
import commons.math.component.vector.Vector;
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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ImageTransformationUtility.
 *
 * @see ImageTransformationUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ImageTransformationUtility.class, DrawUtility.class})
public class ImageTransformationUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ImageTransformationUtilityTest.class);
    
    
    //Constants
    
    /**
     * The directory containing resources for this test class.
     */
    private static final File testResources = Project.testResourcesDir(ImageTransformationUtility.class);
    
    
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
     * JUnit test of transformImage.
     *
     * @throws Exception When there is an exception.
     * @see ImageTransformationUtility#transformImage(BufferedImage, List, Graphics2D, int, int, List)
     * @see ImageTransformationUtility#transformImage(BufferedImage, List, BufferedImage, List)
     */
    @Test
    public void testTransformImage() throws Exception {
        final File testImageFile = Filesystem.getTemporaryFile("bmp");
        Assert.assertTrue(Filesystem.copyFile(new File(testResources, "test.bmp"), testImageFile));
        File verifyImageFile;
        File transformedImageFile;
        final BufferedImage image = ImageUtility.loadImage(testImageFile);
        BufferedImage transformedImage;
        BufferedImage loadedImage;
        Graphics2D transformedGraphics;
        List<Vector> sourceBounds;
        List<Vector> destBounds;
        
        //standard
        
        transformedImage = new BufferedImage(40, 60, BufferedImage.TYPE_INT_RGB);
        transformedGraphics = (Graphics2D) transformedImage.getGraphics();
        sourceBounds = ImageTransformationUtility.getBoundsForImage(image);
        destBounds = Arrays.asList(new Vector(40, 0), new Vector(40, 60),
                new Vector(0, 60), new Vector(0, 0));
        ImageTransformationUtility.transformImage(image, sourceBounds, transformedGraphics, 40, 60, destBounds);
        DrawUtility.dispose(transformedGraphics);
        transformedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(transformedImage, transformedImageFile);
        transformedImage = ImageUtility.loadImage(transformedImageFile);
        verifyImageFile = new File(testResources, "transformImage1.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(transformedImage, loadedImage));
        
        transformedImage = new BufferedImage(60, 40, BufferedImage.TYPE_INT_RGB);
        transformedGraphics = (Graphics2D) transformedImage.getGraphics();
        sourceBounds = ImageTransformationUtility.getBoundsForImage(image);
        destBounds = Arrays.asList(new Vector(60, 40), new Vector(0, 40),
                new Vector(0, 0), new Vector(60, 0));
        ImageTransformationUtility.transformImage(image, sourceBounds, transformedGraphics, 60, 40, destBounds);
        DrawUtility.dispose(transformedGraphics);
        transformedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(transformedImage, transformedImageFile);
        transformedImage = ImageUtility.loadImage(transformedImageFile);
        verifyImageFile = new File(testResources, "transformImage2.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(transformedImage, loadedImage));
        
        transformedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        transformedGraphics = (Graphics2D) transformedImage.getGraphics();
        sourceBounds = ImageTransformationUtility.getBoundsForImage(image);
        destBounds = Arrays.asList(new Vector(20, 20), new Vector(80, 20),
                new Vector(80, 60), new Vector(20, 60));
        ImageTransformationUtility.transformImage(image, sourceBounds, transformedGraphics, 100, 100, destBounds);
        DrawUtility.dispose(transformedGraphics);
        transformedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(transformedImage, transformedImageFile);
        transformedImage = ImageUtility.loadImage(transformedImageFile);
        verifyImageFile = new File(testResources, "transformImage3.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(transformedImage, loadedImage));
        
        transformedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        transformedGraphics = (Graphics2D) transformedImage.getGraphics();
        sourceBounds = ImageTransformationUtility.getBoundsForImage(image);
        destBounds = Arrays.asList(new Vector(10, 10), new Vector(85, 30),
                new Vector(65, 90), new Vector(10, 70));
        ImageTransformationUtility.transformImage(image, sourceBounds, transformedGraphics, 100, 100, destBounds);
        DrawUtility.dispose(transformedGraphics);
        transformedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(transformedImage, transformedImageFile);
        transformedImage = ImageUtility.loadImage(transformedImageFile);
        verifyImageFile = new File(testResources, "transformImage4.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(transformedImage, loadedImage));
        
        //partial
        
        transformedImage = new BufferedImage(40, 60, BufferedImage.TYPE_INT_RGB);
        transformedGraphics = (Graphics2D) transformedImage.getGraphics();
        sourceBounds = Arrays.asList(new Vector(0, 0), new Vector(50, 0),
                new Vector(50, 30), new Vector(0, 30));
        destBounds = Arrays.asList(new Vector(40, 0), new Vector(40, 60),
                new Vector(0, 60), new Vector(0, 0));
        ImageTransformationUtility.transformImage(image, sourceBounds, transformedGraphics, 40, 60, destBounds);
        DrawUtility.dispose(transformedGraphics);
        transformedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(transformedImage, transformedImageFile);
        transformedImage = ImageUtility.loadImage(transformedImageFile);
        verifyImageFile = new File(testResources, "transformImage5.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(transformedImage, loadedImage));
        
        transformedImage = new BufferedImage(60, 40, BufferedImage.TYPE_INT_RGB);
        transformedGraphics = (Graphics2D) transformedImage.getGraphics();
        sourceBounds = Arrays.asList(new Vector(0, 0), new Vector(30, 0),
                new Vector(30, 20), new Vector(0, 20));
        destBounds = Arrays.asList(new Vector(60, 40), new Vector(0, 40),
                new Vector(0, 0), new Vector(60, 0));
        ImageTransformationUtility.transformImage(image, sourceBounds, transformedGraphics, 60, 40, destBounds);
        DrawUtility.dispose(transformedGraphics);
        transformedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(transformedImage, transformedImageFile);
        transformedImage = ImageUtility.loadImage(transformedImageFile);
        verifyImageFile = new File(testResources, "transformImage6.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(transformedImage, loadedImage));
        
        transformedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        transformedGraphics = (Graphics2D) transformedImage.getGraphics();
        sourceBounds = Arrays.asList(new Vector(10, 10), new Vector(40, 10),
                new Vector(40, 25), new Vector(10, 25));
        destBounds = Arrays.asList(new Vector(20, 20), new Vector(80, 20),
                new Vector(80, 60), new Vector(20, 60));
        ImageTransformationUtility.transformImage(image, sourceBounds, transformedGraphics, 100, 100, destBounds);
        DrawUtility.dispose(transformedGraphics);
        transformedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(transformedImage, transformedImageFile);
        transformedImage = ImageUtility.loadImage(transformedImageFile);
        verifyImageFile = new File(testResources, "transformImage7.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(transformedImage, loadedImage));
        
        transformedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        transformedGraphics = (Graphics2D) transformedImage.getGraphics();
        sourceBounds = Arrays.asList(new Vector(50, 28), new Vector(45, 7),
                new Vector(3, 14), new Vector(31, 35));
        destBounds = Arrays.asList(new Vector(10, 10), new Vector(85, 30),
                new Vector(65, 90), new Vector(10, 70));
        ImageTransformationUtility.transformImage(image, sourceBounds, transformedGraphics, 100, 100, destBounds);
        DrawUtility.dispose(transformedGraphics);
        transformedImageFile = Filesystem.getTemporaryFile("bmp");
        ImageUtility.saveImage(transformedImage, transformedImageFile);
        transformedImage = ImageUtility.loadImage(transformedImageFile);
        verifyImageFile = new File(testResources, "transformImage8.bmp");
        loadedImage = ImageUtility.loadImage(verifyImageFile);
        Assert.assertTrue(ImageUtility.pixelsEqual(transformedImage, loadedImage));
        
        //invalid
        
        final BufferedImage testImage = new BufferedImage(60, 40, BufferedImage.TYPE_INT_RGB);
        final Graphics2D testGraphics = (Graphics2D) testImage.getGraphics();
        final List<Vector> testSourceBounds = ImageTransformationUtility.getBoundsForImage(image);
        final List<Vector> testDestBounds = ImageTransformationUtility.getBoundsForImage(transformedImage);
        
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(null, testSourceBounds, testImage, testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, null, testImage, testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, testSourceBounds, null, testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, testSourceBounds, testImage, null));
        
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, new ArrayList<>(), testImage, testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, testSourceBounds, testImage, new ArrayList<>()));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        Arrays.asList(new Vector(), new Vector(), new Vector()), testImage,
                        testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector()), testImage,
                        testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        testSourceBounds, testImage,
                        Arrays.asList(new Vector(), new Vector(), new Vector())));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        testSourceBounds, testImage,
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector())));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        Arrays.asList(new Vector(), new Vector(), new Vector()), testImage,
                        Arrays.asList(new Vector(), new Vector(), new Vector())));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector()), testImage,
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector())));
        
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(null, testSourceBounds, testGraphics, 60, 40, testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, null, testGraphics, 60, 40, testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, testSourceBounds, null, 60, 40, testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, testSourceBounds, testGraphics, 60, 40, null));
        
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, new ArrayList<>(), testGraphics, 60, 40, testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image, testSourceBounds, testGraphics, 60, 40, new ArrayList<>()));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        Arrays.asList(new Vector(), new Vector(), new Vector()), testGraphics, 60, 40,
                        testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector()), testGraphics, 60, 40,
                        testDestBounds));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        testSourceBounds, testGraphics, 60, 40,
                        Arrays.asList(new Vector(), new Vector(), new Vector())));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        testSourceBounds, testGraphics, 60, 40,
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector())));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        Arrays.asList(new Vector(), new Vector(), new Vector()), testGraphics, 60, 40,
                        Arrays.asList(new Vector(), new Vector(), new Vector())));
        TestUtils.assertNoException(() ->
                ImageTransformationUtility.transformImage(image,
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector()), testGraphics, 60, 40,
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector())));
        
        //to image
        
        PowerMockito.mockStatic(ImageTransformationUtility.class, Mockito.CALLS_REAL_METHODS);
        PowerMockito.doNothing().when(ImageTransformationUtility.class, "transformImage", ArgumentMatchers.any(BufferedImage.class), ArgumentMatchers.any(List.class), ArgumentMatchers.any(Graphics2D.class), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.any(List.class));
        PowerMockito.mockStatic(DrawUtility.class, Mockito.CALLS_REAL_METHODS);
        transformedImage = new BufferedImage(60, 40, BufferedImage.TYPE_INT_RGB);
        sourceBounds = ImageTransformationUtility.getBoundsForImage(image);
        destBounds = ImageTransformationUtility.getBoundsForImage(transformedImage);
        ImageTransformationUtility.transformImage(image, sourceBounds, transformedImage, destBounds);
        PowerMockito.verifyStatic(ImageTransformationUtility.class);
        ImageTransformationUtility.transformImage(ArgumentMatchers.eq(image), ArgumentMatchers.eq(sourceBounds), ArgumentMatchers.any(Graphics2D.class), ArgumentMatchers.eq(60), ArgumentMatchers.eq(40), ArgumentMatchers.eq(destBounds));
        PowerMockito.verifyStatic(DrawUtility.class);
        DrawUtility.dispose(ArgumentMatchers.any(Graphics2D.class));
        PowerMockito.doCallRealMethod().when(ImageTransformationUtility.class, "transformImage", ArgumentMatchers.any(BufferedImage.class), ArgumentMatchers.any(List.class), ArgumentMatchers.any(Graphics2D.class), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.any(List.class));
    }
    
    /**
     * JUnit test of calculateProjectiveMatrix.
     *
     * @throws Exception When there is an exception.
     * @see ImageTransformationUtility#calculateProjectiveMatrix(List, List)
     */
    @Test
    public void testCalculateProjectiveMatrix() throws Exception {
        List<Vector> sourceBounds;
        List<Vector> destBounds;
        Matrix projectiveMatrix;
        
        //standard
        
        sourceBounds = Arrays.asList(new Vector(0, 0), new Vector(60, 0),
                new Vector(60, 40), new Vector(0, 40));
        destBounds = Arrays.asList(new Vector(40, 0), new Vector(40, 60),
                new Vector(0, 60), new Vector(0, 0));
        projectiveMatrix = ImageTransformationUtility.calculateProjectiveMatrix(sourceBounds, destBounds);
        Assert.assertNotNull(projectiveMatrix);
        Assert.assertEquals(new Matrix(
                0, 1, 0,
                -1, 0, 40,
                0, 0, 1
        ), projectiveMatrix);
        
        sourceBounds = Arrays.asList(new Vector(0, 0), new Vector(60, 0),
                new Vector(60, 40), new Vector(0, 40));
        destBounds = Arrays.asList(new Vector(60, 40), new Vector(0, 40),
                new Vector(0, 0), new Vector(60, 0));
        projectiveMatrix = ImageTransformationUtility.calculateProjectiveMatrix(sourceBounds, destBounds);
        Assert.assertNotNull(projectiveMatrix);
        Assert.assertEquals(new Matrix(
                -1, 0, 60,
                0, -1, 40,
                0, 0, 1
        ), projectiveMatrix);
        
        sourceBounds = Arrays.asList(new Vector(0, 0), new Vector(60, 0),
                new Vector(60, 40), new Vector(0, 40));
        destBounds = Arrays.asList(new Vector(20, 20), new Vector(80, 20),
                new Vector(80, 60), new Vector(20, 60));
        projectiveMatrix = ImageTransformationUtility.calculateProjectiveMatrix(sourceBounds, destBounds);
        Assert.assertNotNull(projectiveMatrix);
        Assert.assertEquals(new Matrix(
                1, 0, -20,
                0, 1, -20,
                0, 0, 1
        ), projectiveMatrix);
        
        sourceBounds = Arrays.asList(new Vector(0, 0), new Vector(60, 0),
                new Vector(60, 40), new Vector(0, 40));
        destBounds = Arrays.asList(new Vector(10, 10), new Vector(85, 30),
                new Vector(65, 90), new Vector(10, 70));
        projectiveMatrix = ImageTransformationUtility.calculateProjectiveMatrix(sourceBounds, destBounds);
        Assert.assertNotNull(projectiveMatrix);
        Assert.assertEquals(new Matrix(
                1.090909090909, 0, -10.909090909091,
                -0.163265306122, 0.612244897959, -4.489795918367,
                0.003289374718, -0.004964147821, 1.23296394725
        ), projectiveMatrix);
        
        //partial
        
        sourceBounds = Arrays.asList(new Vector(0, 0), new Vector(50, 0),
                new Vector(50, 30), new Vector(0, 30));
        destBounds = Arrays.asList(new Vector(40, 0), new Vector(40, 60),
                new Vector(0, 60), new Vector(0, 0));
        projectiveMatrix = ImageTransformationUtility.calculateProjectiveMatrix(sourceBounds, destBounds);
        Assert.assertNotNull(projectiveMatrix);
        Assert.assertEquals(new Matrix(
                0, 0.833333333333, 0,
                -0.75, 0, 30,
                0, 0, 1
        ), projectiveMatrix);
        
        sourceBounds = Arrays.asList(new Vector(0, 0), new Vector(30, 0),
                new Vector(30, 20), new Vector(0, 20));
        destBounds = Arrays.asList(new Vector(60, 40), new Vector(0, 40),
                new Vector(0, 0), new Vector(60, 0));
        projectiveMatrix = ImageTransformationUtility.calculateProjectiveMatrix(sourceBounds, destBounds);
        Assert.assertNotNull(projectiveMatrix);
        Assert.assertEquals(new Matrix(
                -0.5, 0, 30,
                0, -0.5, 20,
                0, 0, 1
        ), projectiveMatrix);
        
        sourceBounds = Arrays.asList(new Vector(10, 10), new Vector(40, 10),
                new Vector(40, 25), new Vector(10, 25));
        destBounds = Arrays.asList(new Vector(20, 20), new Vector(80, 20),
                new Vector(80, 60), new Vector(20, 60));
        projectiveMatrix = ImageTransformationUtility.calculateProjectiveMatrix(sourceBounds, destBounds);
        Assert.assertNotNull(projectiveMatrix);
        Assert.assertEquals(new Matrix(
                0.5, 0, 0,
                0, 0.375, 2.5,
                0, 0, 1
        ), projectiveMatrix);
        
        sourceBounds = Arrays.asList(new Vector(50, 28), new Vector(45, 7),
                new Vector(3, 14), new Vector(31, 35));
        destBounds = Arrays.asList(new Vector(10, 10), new Vector(85, 30),
                new Vector(65, 90), new Vector(10, 70));
        projectiveMatrix = ImageTransformationUtility.calculateProjectiveMatrix(sourceBounds, destBounds);
        Assert.assertNotNull(projectiveMatrix);
        Assert.assertEquals(new Matrix(
                -0.488279014546, -1.514885771222, 171.077855355505,
                -0.879230969554, -0.277852160917, 96.156707503482,
                -0.010550067693, -0.018008380336, 3.306508630241
        ), projectiveMatrix);
        
        //invalid
        
        final List<Vector> testSourceBounds = Arrays.asList(new Vector(0, 0), new Vector(60, 0),
                new Vector(60, 40), new Vector(0, 40));
        final List<Vector> testDestBounds = Arrays.asList(new Vector(40, 0), new Vector(40, 60),
                new Vector(0, 60), new Vector(0, 0));
        
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(null, testDestBounds)));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(testSourceBounds, null)));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(null, null)));
        
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(new ArrayList<>(), testDestBounds)));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(testSourceBounds, new ArrayList<>())));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(new ArrayList<>(), new ArrayList<>())));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(
                        Arrays.asList(new Vector(), new Vector(), new Vector()),
                        testDestBounds)));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector()),
                        testDestBounds)));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(
                        testSourceBounds,
                        Arrays.asList(new Vector(), new Vector(), new Vector()))));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(
                        testSourceBounds,
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector()))));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector()),
                        Arrays.asList(new Vector(), new Vector(), new Vector(), new Vector(), new Vector()))));
        TestUtils.assertNoException(() ->
                Assert.assertNull(ImageTransformationUtility.calculateProjectiveMatrix(
                        Arrays.asList(new Vector(), new Vector(), new Vector()),
                        Arrays.asList(new Vector(), new Vector(), new Vector()))));
    }
    
    /**
     * JUnit test of getBoundsForImage.
     *
     * @throws Exception When there is an exception.
     * @see ImageTransformationUtility#getBoundsForImage(BufferedImage)
     */
    @Test
    public void testGetBoundsForImage() throws Exception {
        BufferedImage image;
        List<Vector> bounds;
        
        //standard
        image = ImageUtility.loadImage(new File(testResources, "test.bmp"));
        bounds = ImageTransformationUtility.getBoundsForImage(image);
        Assert.assertEquals(4, bounds.size());
        Assert.assertEquals(new Vector(0, 0), bounds.get(0));
        Assert.assertEquals(new Vector(59, 0), bounds.get(1));
        Assert.assertEquals(new Vector(59, 39), bounds.get(2));
        Assert.assertEquals(new Vector(0, 39), bounds.get(3));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ImageTransformationUtility.getBoundsForImage(null));
    }
    
}
