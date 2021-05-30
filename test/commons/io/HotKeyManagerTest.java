/*
 * File:    HotKeyManagerTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import commons.test.TestUtils;
import org.jnativehook.keyboard.NativeKeyEvent;
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
 * JUnit test of HotKeyManager.
 *
 * @see HotKeyManager
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({HotKeyManager.class})
public class HotKeyManagerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(HotKeyManagerTest.class);
    
    
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
        //static
        Map<HotKeyManager.ModifierKey, AtomicBoolean> modifierDown = (Map<HotKeyManager.ModifierKey, AtomicBoolean>) TestUtils.getField(HotKeyManager.class, "modifierDown");
        Assert.assertNotNull(modifierDown);
        Assert.assertEquals(HotKeyManager.ModifierKey.values().length, modifierDown.size());
        Assert.assertFalse(modifierDown.get(HotKeyManager.ModifierKey.CONTROL).get());
        Assert.assertFalse(modifierDown.get(HotKeyManager.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKeyManager.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKeyManager.ModifierKey.META).get());
    }
    
    /**
     * JUnit test of ModifierKey.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager.ModifierKey
     */
    @Test
    public void testModifierKey() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setup.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#setup()
     */
    @Test
    public void testSetup() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of register.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#register(HotKeyManager.HotKey)
     */
    @Test
    public void testRegister() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of unregister.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#unregister(HotKeyManager.HotKey)
     */
    @Test
    public void testUnregister() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of has.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#has(HotKeyManager.HotKey)
     */
    @Test
    public void testHas() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of hit.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#hit(NativeKeyEvent)
     */
    @Test
    public void testHit() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of release.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#release(NativeKeyEvent)
     */
    @Test
    public void testRelease() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of getInstance.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of registerHotkey.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#registerHotkey(HotKeyManager.HotKey)
     */
    @Test
    public void testRegisterHotkey() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of unregisterHotkey.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#unregisterHotkey(HotKeyManager.HotKey)
     */
    @Test
    public void testUnregisterHotkey() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of hasHotkey.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#hasHotkey(HotKeyManager.HotKey)
     */
    @Test
    public void testHasHotkey() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of HotKey.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager.HotKey
     */
    @Test
    public void testHotKey() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of HotKeyCallback.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager.HotKeyCallback
     */
    @Test
    public void testHotKeyCallback() throws Exception {
        TestUtils.assertMethodExists(HotKeyManager.HotKeyCallback.class,
                "hit");
        TestUtils.assertMethodExists(HotKeyManager.HotKeyCallback.class,
                "release");
    }
    
}
