/*
 * File:    ClipboardTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.access;

import java.awt.image.BufferedImage;

import commons.log.CommonsLogging;
import commons.media.ImageUtility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Clipboard.
 *
 * @see Clipboard
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Clipboard.class, ImageUtility.class, CommonsLogging.class})
public class ClipboardTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ClipboardTest.class);
    
    
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
     * JUnit test of getClipboard.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#getClipboard()
     */
    @Test
    public void testGetClipboard() throws Exception {
        Clipboard.putClipboard("test");
        Assert.assertEquals("test", Clipboard.getClipboard());
        Assert.assertEquals("test", Clipboard.getClipboard());
        Assert.assertEquals("test", Clipboard.getClipboard());
        Clipboard.putClipboard(null);
        Assert.assertNull(Clipboard.getClipboard());
    }
    
    /**
     * JUnit test of putClipboard.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#putClipboard(String)
     */
    @Test
    public void testPutClipboard() throws Exception {
        Assert.assertTrue(Clipboard.putClipboard("test"));
        Assert.assertEquals("test", Clipboard.getClipboard());
        Assert.assertEquals("test", Clipboard.getClipboard());
        Assert.assertEquals("test", Clipboard.getClipboard());
        Assert.assertTrue(Clipboard.putClipboard(null));
        Assert.assertNull(Clipboard.getClipboard());
    }
    
    /**
     * JUnit test of getClipboardImage.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#getClipboardImage()
     */
    @Test
    public void testGetClipboardImage() throws Exception {
        PowerMockito.mockStatic(ImageUtility.class);
        Clipboard.getClipboardImage();
        PowerMockito.verifyStatic(ImageUtility.class);
        ImageUtility.copyImageFromClipboard();
    }
    
    /**
     * JUnit test of putClipboardImage.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#putClipboardImage(java.awt.image.BufferedImage)
     */
    @Test
    public void testPutClipboardImage() throws Exception {
        PowerMockito.mockStatic(ImageUtility.class);
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Clipboard.putClipboardImage(image);
        PowerMockito.verifyStatic(ImageUtility.class);
        ImageUtility.copyImageToClipboard(ArgumentMatchers.eq(image));
    }
    
    /**
     * JUnit test of logClipboard.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#logClipboard()
     */
    @Test
    public void testLogClipboard() throws Exception {
        PowerMockito.mockStatic(CommonsLogging.class);
        Clipboard.logClipboard();
        PowerMockito.verifyStatic(CommonsLogging.class);
        CommonsLogging.logClipboard();
    }
    
}
