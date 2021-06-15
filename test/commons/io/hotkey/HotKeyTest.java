/*
 * File:    HotKeyTest.java
 * Package: commons.io.hotkey
 * Author:  Zachary Gill
 */

package commons.io.hotkey;

import java.util.Map;

import commons.test.TestUtils;
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
 * JUnit test of HotKey.
 *
 * @see HotKey
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({HotKey.class})
public class HotKeyTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(HotKeyTest.class);
    
    
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
     * @see HotKey#NO_KEY
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(-1, HotKey.NO_KEY);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     */
    @Test
    public void testConstructors() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of ModifierKey.
     *
     * @throws Exception When there is an exception.
     * @see HotKey.ModifierKey
     */
    @Test
    public void testModifierKey() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#toString()
     */
    @Test
    public void testToString() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of activate.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#activate()
     */
    @Test
    public void testActivate() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of deactivate.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#deactivate()
     */
    @Test
    public void testDeactivate() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of hit.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#hit()
     */
    @Test
    public void testHit() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of release.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#release()
     */
    @Test
    public void testRelease() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isMatch.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#isMatch(int, Map, boolean)
     */
    @Test
    public void testIsMatch() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getCode.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#getCode()
     */
    @Test
    public void testGetCode() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of hasModifier.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#hasModifier(HotKey.ModifierKey)
     */
    @Test
    public void testHasModifier() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getCallback.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#getCallback()
     */
    @Test
    public void testGetCallback() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isActive.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#isActive()
     */
    @Test
    public void testIsActive() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of isHit.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#isHit()
     */
    @Test
    public void testIsHit() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of HotKeyCallback.
     *
     * @throws Exception When there is an exception.
     * @see HotKey.HotKeyCallback
     */
    @Test
    public void testHotKeyCallback() throws Exception {
        TestUtils.assertMethodExists(HotKey.HotKeyCallback.class,
                "hit");
        TestUtils.assertMethodExists(HotKey.HotKeyCallback.class,
                "release");
    }
    
}
