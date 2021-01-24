/*
 * File:    ScreenUtilityTest.java
 * Package: commons.graphics
 * Author:  Zachary Gill
 */

package commons.graphics;

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
 * JUnit test of ScreenUtility.
 *
 * @see ScreenUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({ScreenUtility.class})
public class ScreenUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ScreenUtilityTest.class);
    
    
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
     * @see ScreenUtility#MONITOR_WIDTH
     * @see ScreenUtility#MONITOR_HEIGHT
     * @see ScreenUtility#TASKBAR_WIDTH
     * @see ScreenUtility#TASKBAR_HEIGHT
     * @see ScreenUtility#SCREEN_WIDTH
     * @see ScreenUtility#SCREEN_HEIGHT
     * @see ScreenUtility#BORDER_WIDTH
     * @see ScreenUtility#BORDER_HEIGHT
     * @see ScreenUtility#DISPLAY_WIDTH
     * @see ScreenUtility#DISPLAY_HEIGHT
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertTrue(ScreenUtility.MONITOR_WIDTH > 0);
        Assert.assertTrue(ScreenUtility.MONITOR_HEIGHT > 0);
        Assert.assertTrue(ScreenUtility.TASKBAR_WIDTH >= 0);
        Assert.assertTrue(ScreenUtility.TASKBAR_HEIGHT >= 0);
        Assert.assertTrue(ScreenUtility.SCREEN_WIDTH > 0);
        Assert.assertTrue(ScreenUtility.SCREEN_HEIGHT > 0);
        Assert.assertTrue(ScreenUtility.BORDER_WIDTH >= 0);
        Assert.assertTrue(ScreenUtility.BORDER_HEIGHT >= 0);
        Assert.assertTrue(ScreenUtility.DISPLAY_WIDTH > 0);
        Assert.assertTrue(ScreenUtility.DISPLAY_HEIGHT > 0);
        
        Assert.assertTrue(ScreenUtility.SCREEN_WIDTH <= ScreenUtility.MONITOR_WIDTH);
        Assert.assertTrue(ScreenUtility.DISPLAY_WIDTH <= ScreenUtility.SCREEN_WIDTH);
        
        Assert.assertTrue(ScreenUtility.SCREEN_HEIGHT <= ScreenUtility.MONITOR_HEIGHT);
        Assert.assertTrue(ScreenUtility.DISPLAY_HEIGHT <= ScreenUtility.SCREEN_HEIGHT);
    }
    
}
