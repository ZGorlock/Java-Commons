/*
 * File:    ImageTransformationUtilityTest.java
 * Package: commons.graphics
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

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
 * JUnit test of ImageTransformationUtility.
 *
 * @see ImageTransformationUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ImageTransformationUtility.class})
public class ImageTransformationUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ImageTransformationUtilityTest.class);
    
    
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
     * @see ImageTransformationUtility#transformImage(BufferedImage, List, BufferedImage, List)
     * @see ImageTransformationUtility#transformImage(BufferedImage, List, Graphics2D, int, int, List)
     */
    @Test
    public void testTransformImage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of calculateProjectiveMatrix.
     *
     * @throws Exception When there is an exception.
     * @see ImageTransformationUtility#calculateProjectiveMatrix(List, List)
     */
    @Test
    public void testCalculateProjectiveMatrix() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getBoundsForImage.
     *
     * @throws Exception When there is an exception.
     * @see ImageTransformationUtility#getBoundsForImage(BufferedImage)
     */
    @Test
    public void testGetBoundsForImage() throws Exception {
        //TODO
    }
    
}
