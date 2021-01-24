/*
 * File:    ClipboardTest.java
 * Package: commons.access
 * Author:  Zachary Gill
 */

package commons.access;

import commons.media.ImageUtility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Clipboard.
 *
 * @see Clipboard
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({Clipboard.class, ImageUtility.class})
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
     * @see Clipboard#DEFAULT_LOG_CLIPBOARD
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertFalse(Clipboard.DEFAULT_LOG_CLIPBOARD);
    }
    
    /**
     * JUnit test of getClipboard.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#getClipboard()
     */
    @Test
    public void testGetClipboard() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of putClipboard.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#putClipboard(String)
     */
    @Test
    public void testPutClipboard() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getClipboardImage.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#getClipboardImage()
     */
    @Test
    public void testGetClipboardImage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of putClipboardImage.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#putClipboardImage(java.awt.image.BufferedImage)
     */
    @Test
    public void testPutClipboardImage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of logClipboard.
     *
     * @throws Exception When there is an exception.
     * @see Clipboard#logClipboard()
     */
    @Test
    public void testLogClipboard() throws Exception {
        //TODO
    }
    
}
