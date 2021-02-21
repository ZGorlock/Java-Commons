/*
 * File:    ScreenSizeTest.java
 * Package: commons.graphics
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.graphics;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ScreenSize.
 *
 * @see ScreenSize
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ScreenSize.class})
public class ScreenSizeTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ScreenSizeTest.class);
    
    
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
     * @see ScreenSize#MONITOR_WIDTH
     * @see ScreenSize#MONITOR_HEIGHT
     * @see ScreenSize#TASKBAR_WIDTH
     * @see ScreenSize#TASKBAR_HEIGHT
     * @see ScreenSize#SCREEN_WIDTH
     * @see ScreenSize#SCREEN_HEIGHT
     * @see ScreenSize#BORDER_WIDTH
     * @see ScreenSize#BORDER_HEIGHT
     * @see ScreenSize#DISPLAY_WIDTH
     * @see ScreenSize#DISPLAY_HEIGHT
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertTrue(ScreenSize.MONITOR_WIDTH > 0);
        Assert.assertTrue(ScreenSize.MONITOR_HEIGHT > 0);
        Assert.assertTrue(ScreenSize.TASKBAR_WIDTH >= 0);
        Assert.assertTrue(ScreenSize.TASKBAR_HEIGHT >= 0);
        Assert.assertTrue(ScreenSize.SCREEN_WIDTH > 0);
        Assert.assertTrue(ScreenSize.SCREEN_HEIGHT > 0);
        Assert.assertTrue(ScreenSize.BORDER_WIDTH >= 0);
        Assert.assertTrue(ScreenSize.BORDER_HEIGHT >= 0);
        Assert.assertTrue(ScreenSize.DISPLAY_WIDTH > 0);
        Assert.assertTrue(ScreenSize.DISPLAY_HEIGHT > 0);
        
        Assert.assertTrue(ScreenSize.SCREEN_WIDTH <= ScreenSize.MONITOR_WIDTH);
        Assert.assertTrue(ScreenSize.DISPLAY_WIDTH <= ScreenSize.SCREEN_WIDTH);
        
        Assert.assertTrue(ScreenSize.SCREEN_HEIGHT <= ScreenSize.MONITOR_HEIGHT);
        Assert.assertTrue(ScreenSize.DISPLAY_HEIGHT <= ScreenSize.SCREEN_HEIGHT);
    }
    
}
