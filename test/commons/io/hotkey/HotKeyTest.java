/*
 * File:    HotKeyTest.java
 * Package: commons.io.hotkey
 * Author:  Zachary Gill
 */

package commons.io.hotkey;

import java.awt.event.KeyEvent;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
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
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private HotKey sut;
    
    /**
     * A sample HotKey Callback.
     */
    private HotKey.HotKeyCallback callback;
    
    
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
    @Before
    public void setup() throws Exception {
        callback = Mockito.mock(HotKey.HotKeyCallback.class);
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
        HotKey.HotKeyCallback callback = new HotKey.HotKeyCallback() {
            @Override
            public void hit() {
                System.out.println("hit");
            }
            
            @Override
            public void release() {
                System.out.println("release");
            }
        };
        Map<HotKey.ModifierKey, Boolean> modifiers;
        
        //standard
        
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        Assert.assertNotNull(sut);
        Assert.assertEquals(KeyEvent.VK_Z, (int) TestUtils.getField(sut, "code"));
        modifiers = (Map<HotKey.ModifierKey, Boolean>) TestUtils.getField(sut, "modifiers");
        Assert.assertTrue(modifiers.get(HotKey.ModifierKey.CONTROL));
        Assert.assertTrue(modifiers.get(HotKey.ModifierKey.SHIFT));
        Assert.assertFalse(modifiers.get(HotKey.ModifierKey.ALT));
        Assert.assertFalse(modifiers.get(HotKey.ModifierKey.META));
        Assert.assertEquals(callback, TestUtils.getField(sut, "callback"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "active")).get());
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(sut, "hit")).get());
        
        sut = new HotKey(HotKey.NO_KEY, true, true, true, true, callback);
        Assert.assertNotNull(sut);
        Assert.assertEquals(HotKey.NO_KEY, (int) TestUtils.getField(sut, "code"));
        
        //invalid
        
        sut = new HotKey(5566110, false, false, false, false, callback);
        Assert.assertNotNull(sut);
        Assert.assertEquals(5566110, (int) TestUtils.getField(sut, "code"));
        
        sut = new HotKey(-5432151, false, false, false, false, callback);
        Assert.assertNotNull(sut);
        Assert.assertEquals(HotKey.NO_KEY, (int) TestUtils.getField(sut, "code"));
        
        TestUtils.assertNoException(() ->
                new HotKey(KeyEvent.VK_Z, true, true, false, false, null));
    }
    
    /**
     * JUnit test of ModifierKey.
     *
     * @throws Exception When there is an exception.
     * @see HotKey.ModifierKey
     */
    @Test
    public void testModifierKey() throws Exception {
        Assert.assertEquals(4, HotKey.ModifierKey.values().length);
        Assert.assertEquals(HotKey.ModifierKey.CONTROL, HotKey.ModifierKey.values()[0]);
        Assert.assertEquals(HotKey.ModifierKey.SHIFT, HotKey.ModifierKey.values()[1]);
        Assert.assertEquals(HotKey.ModifierKey.ALT, HotKey.ModifierKey.values()[2]);
        Assert.assertEquals(HotKey.ModifierKey.META, HotKey.ModifierKey.values()[3]);
        
        //getName
        Assert.assertEquals("Ctrl", HotKey.ModifierKey.CONTROL.getName());
        Assert.assertEquals("Shift", HotKey.ModifierKey.SHIFT.getName());
        Assert.assertEquals("Alt", HotKey.ModifierKey.ALT.getName());
        Assert.assertEquals("Meta", HotKey.ModifierKey.META.getName());
        
        //getModifierKeyByName
        Assert.assertEquals(HotKey.ModifierKey.CONTROL, HotKey.ModifierKey.getModifierKeyByName("Ctrl"));
        Assert.assertEquals(HotKey.ModifierKey.SHIFT, HotKey.ModifierKey.getModifierKeyByName("Shift"));
        Assert.assertEquals(HotKey.ModifierKey.SHIFT, HotKey.ModifierKey.getModifierKeyByName("Unknown keyCode: 0xe36"));
        Assert.assertEquals(HotKey.ModifierKey.ALT, HotKey.ModifierKey.getModifierKeyByName("Alt"));
        Assert.assertEquals(HotKey.ModifierKey.META, HotKey.ModifierKey.getModifierKeyByName("Meta"));
        Assert.assertNull(HotKey.ModifierKey.getModifierKeyByName("Other"));
        Assert.assertNull(HotKey.ModifierKey.getModifierKeyByName(""));
        Assert.assertNull(HotKey.ModifierKey.getModifierKeyByName(null));
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new HotKey(KeyEvent.VK_E, false, false, false, false, callback);
        Assert.assertEquals("[E]", sut.toString());
        
        sut = new HotKey(KeyEvent.VK_Z, true, false, false, false, callback);
        Assert.assertEquals("[Ctrl-Z]", sut.toString());
        
        sut = new HotKey(KeyEvent.VK_7, true, true, false, false, callback);
        Assert.assertEquals("[Ctrl-Shift-7]", sut.toString());
        
        sut = new HotKey(KeyEvent.VK_PERIOD, true, true, true, false, callback);
        Assert.assertEquals("[Ctrl-Shift-Alt-Period]", sut.toString());
        
        sut = new HotKey(KeyEvent.VK_Q, true, true, true, true, callback);
        Assert.assertEquals("[Ctrl-Shift-Alt-Meta-Q]", sut.toString());
        
        sut = new HotKey(HotKey.NO_KEY, true, true, true, true, callback);
        Assert.assertEquals("[Ctrl-Shift-Alt-Meta]", sut.toString());
        
        sut = new HotKey(KeyEvent.VK_H, true, false, true, true, callback);
        Assert.assertEquals("[Ctrl-Alt-Meta-H]", sut.toString());
        
        sut = new HotKey(KeyEvent.VK_UNDERSCORE, false, false, true, true, callback);
        Assert.assertEquals("[Alt-Meta-Underscore]", sut.toString());
        
        sut = new HotKey(KeyEvent.VK_J, false, false, true, false, callback);
        Assert.assertEquals("[Alt-J]", sut.toString());
        
        sut = new HotKey(HotKey.NO_KEY, false, false, false, false, callback);
        Assert.assertEquals("[]", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals() throws Exception {
        HotKey other;
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        
        //standard
        
        other = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        Assert.assertTrue(sut.equals(other));
        
        other = new HotKey(KeyEvent.VK_Z, true, true, false, false, null);
        Assert.assertTrue(sut.equals(other));
        
        other = new HotKey(KeyEvent.VK_E, true, true, false, false, callback);
        Assert.assertFalse(sut.equals(other));
        
        other = new HotKey(KeyEvent.VK_Z, false, true, false, false, callback);
        Assert.assertFalse(sut.equals(other));
        
        other = new HotKey(KeyEvent.VK_Z, true, false, false, false, callback);
        Assert.assertFalse(sut.equals(other));
        
        other = new HotKey(KeyEvent.VK_Z, true, true, true, false, callback);
        Assert.assertFalse(sut.equals(other));
        
        other = new HotKey(KeyEvent.VK_Z, true, true, false, true, callback);
        Assert.assertFalse(sut.equals(other));
        
        //invalid
        
        Assert.assertFalse(sut.equals(""));
        Assert.assertFalse(sut.equals(BigDecimal.valueOf(8.5)));
        Assert.assertFalse(sut.equals(new File(".")));
        Assert.assertFalse(sut.equals(null));
    }
    
    /**
     * JUnit test of activate.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#activate()
     */
    @Test
    public void testActivate() throws Exception {
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        Assert.assertTrue(sut.isActive());
        sut.activate();
        Assert.assertTrue(sut.isActive());
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        Assert.assertFalse(sut.isActive());
        sut.activate();
        Assert.assertTrue(sut.isActive());
    }
    
    /**
     * JUnit test of deactivate.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#deactivate()
     */
    @Test
    public void testDeactivate() throws Exception {
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        Assert.assertTrue(sut.isActive());
        sut.deactivate();
        Assert.assertFalse(sut.isActive());
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        Assert.assertFalse(sut.isActive());
        sut.deactivate();
        Assert.assertFalse(sut.isActive());
    }
    
    /**
     * JUnit test of hit.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#hit()
     */
    @Test
    public void testHit() throws Exception {
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        
        //standard
        Assert.assertTrue(sut.isActive());
        Assert.assertFalse(sut.isHit());
        sut.hit();
        Assert.assertTrue(sut.isActive());
        Assert.assertTrue(sut.isHit());
        Mockito.verify(callback, VerificationModeFactory.times(1))
                .hit();
        TestUtils.setField(sut, "hit", new AtomicBoolean(false));
        
        //not active
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        Assert.assertFalse(sut.isActive());
        Assert.assertFalse(sut.isHit());
        sut.hit();
        Assert.assertFalse(sut.isActive());
        Assert.assertFalse(sut.isHit());
        Mockito.verify(callback, VerificationModeFactory.noMoreInteractions())
                .hit();
        TestUtils.setField(sut, "active", new AtomicBoolean(true));
        
        //already hit
        TestUtils.setField(sut, "hit", new AtomicBoolean(true));
        Assert.assertTrue(sut.isActive());
        Assert.assertTrue(sut.isHit());
        sut.hit();
        Assert.assertTrue(sut.isActive());
        Assert.assertTrue(sut.isHit());
        Mockito.verify(callback, VerificationModeFactory.noMoreInteractions())
                .hit();
        TestUtils.setField(sut, "hit", new AtomicBoolean(false));
        
        //null callback
        TestUtils.setField(sut, "callback", null);
        Assert.assertTrue(sut.isActive());
        Assert.assertFalse(sut.isHit());
        TestUtils.assertNoException(() ->
                sut.hit());
        Assert.assertTrue(sut.isActive());
        Assert.assertTrue(sut.isHit());
        TestUtils.setField(sut, "callback", callback);
    }
    
    /**
     * JUnit test of release.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#release()
     */
    @Test
    public void testRelease() throws Exception {
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        
        //standard
        TestUtils.setField(sut, "hit", new AtomicBoolean(true));
        Assert.assertTrue(sut.isActive());
        Assert.assertTrue(sut.isHit());
        sut.release();
        Assert.assertTrue(sut.isActive());
        Assert.assertFalse(sut.isHit());
        Mockito.verify(callback, VerificationModeFactory.times(1))
                .release();
        TestUtils.setField(sut, "hit", new AtomicBoolean(true));
        
        //not active
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        Assert.assertFalse(sut.isActive());
        Assert.assertTrue(sut.isHit());
        sut.release();
        Assert.assertFalse(sut.isActive());
        Assert.assertTrue(sut.isHit());
        Mockito.verify(callback, VerificationModeFactory.noMoreInteractions())
                .release();
        TestUtils.setField(sut, "active", new AtomicBoolean(true));
        
        //already hit
        TestUtils.setField(sut, "hit", new AtomicBoolean(false));
        Assert.assertTrue(sut.isActive());
        Assert.assertFalse(sut.isHit());
        sut.release();
        Assert.assertTrue(sut.isActive());
        Assert.assertFalse(sut.isHit());
        Mockito.verify(callback, VerificationModeFactory.noMoreInteractions())
                .release();
        TestUtils.setField(sut, "hit", new AtomicBoolean(true));
        
        //null callback
        TestUtils.setField(sut, "callback", null);
        Assert.assertTrue(sut.isActive());
        Assert.assertTrue(sut.isHit());
        TestUtils.assertNoException(() ->
                sut.release());
        Assert.assertTrue(sut.isActive());
        Assert.assertFalse(sut.isHit());
        TestUtils.setField(sut, "callback", callback);
    }
    
    /**
     * JUnit test of isMatch.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#isMatch(int, Map, boolean)
     */
    @Test
    public void testIsMatch() throws Exception {
        Map<HotKey.ModifierKey, AtomicBoolean> modiferDown = new HashMap<>();
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        
        //match hit
        modiferDown.put(HotKey.ModifierKey.CONTROL, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.SHIFT, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.ALT, new AtomicBoolean(false));
        modiferDown.put(HotKey.ModifierKey.META, new AtomicBoolean(false));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_Z, modiferDown, false));
        modiferDown.put(HotKey.ModifierKey.ALT, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.META, new AtomicBoolean(true));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_Z, modiferDown, false));
        
        //match release
        modiferDown.put(HotKey.ModifierKey.CONTROL, new AtomicBoolean(false));
        modiferDown.put(HotKey.ModifierKey.SHIFT, new AtomicBoolean(false));
        modiferDown.put(HotKey.ModifierKey.ALT, new AtomicBoolean(false));
        modiferDown.put(HotKey.ModifierKey.META, new AtomicBoolean(false));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_Z, modiferDown, true));
        modiferDown.put(HotKey.ModifierKey.ALT, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.META, new AtomicBoolean(true));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_Z, modiferDown, true));
        
        //match release, code first
        modiferDown.put(HotKey.ModifierKey.CONTROL, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.SHIFT, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.ALT, new AtomicBoolean(false));
        modiferDown.put(HotKey.ModifierKey.META, new AtomicBoolean(false));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_Z, modiferDown, true));
        
        //match release, modifiers first
        modiferDown.put(HotKey.ModifierKey.CONTROL, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.SHIFT, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.ALT, new AtomicBoolean(false));
        modiferDown.put(HotKey.ModifierKey.META, new AtomicBoolean(false));
        Assert.assertFalse(sut.isMatch(HotKey.NO_KEY, modiferDown, true));
        modiferDown.put(HotKey.ModifierKey.CONTROL, new AtomicBoolean(false));
        Assert.assertFalse(sut.isMatch(HotKey.NO_KEY, modiferDown, true));
        modiferDown.put(HotKey.ModifierKey.SHIFT, new AtomicBoolean(false));
        Assert.assertTrue(sut.isMatch(HotKey.NO_KEY, modiferDown, true));
        
        //no key
        TestUtils.setField(sut, "code", HotKey.NO_KEY);
        modiferDown.put(HotKey.ModifierKey.CONTROL, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.SHIFT, new AtomicBoolean(true));
        modiferDown.put(HotKey.ModifierKey.ALT, new AtomicBoolean(false));
        modiferDown.put(HotKey.ModifierKey.META, new AtomicBoolean(false));
        Assert.assertTrue(sut.isMatch(HotKey.NO_KEY, modiferDown, false));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_Z, modiferDown, false));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_P, modiferDown, false));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_9, modiferDown, false));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_PERIOD, modiferDown, false));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_ESCAPE, modiferDown, false));
        Assert.assertTrue(sut.isMatch(4949415, modiferDown, false));
        Assert.assertTrue(sut.isMatch(-1900751, modiferDown, false));
        modiferDown.put(HotKey.ModifierKey.CONTROL, new AtomicBoolean(false));
        modiferDown.put(HotKey.ModifierKey.SHIFT, new AtomicBoolean(false));
        Assert.assertTrue(sut.isMatch(HotKey.NO_KEY, modiferDown, true));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_Z, modiferDown, true));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_P, modiferDown, true));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_9, modiferDown, true));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_PERIOD, modiferDown, true));
        Assert.assertTrue(sut.isMatch(KeyEvent.VK_ESCAPE, modiferDown, true));
        Assert.assertTrue(sut.isMatch(4949415, modiferDown, true));
        Assert.assertTrue(sut.isMatch(-1900751, modiferDown, true));
        TestUtils.setField(sut, "code", KeyEvent.VK_Z);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.isMatch(KeyEvent.VK_Z, null, false));
        modiferDown.clear();
        TestUtils.assertException(NullPointerException.class, () ->
                sut.isMatch(KeyEvent.VK_Z, modiferDown, false));
    }
    
    /**
     * JUnit test of getCode.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#getCode()
     */
    @Test
    public void testGetCode() throws Exception {
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        Assert.assertEquals(KeyEvent.VK_Z, sut.getCode());
        Assert.assertEquals(KeyEvent.VK_Z, sut.getCode());
        TestUtils.setField(sut, "code", KeyEvent.VK_NUMPAD0);
        Assert.assertEquals(KeyEvent.VK_NUMPAD0, sut.getCode());
        Assert.assertEquals(KeyEvent.VK_NUMPAD0, sut.getCode());
    }
    
    /**
     * JUnit test of hasModifier.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#hasModifier(HotKey.ModifierKey)
     */
    @Test
    public void testHasModifier() throws Exception {
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        Assert.assertTrue(sut.hasModifier(HotKey.ModifierKey.CONTROL));
        Assert.assertTrue(sut.hasModifier(HotKey.ModifierKey.SHIFT));
        Assert.assertFalse(sut.hasModifier(HotKey.ModifierKey.ALT));
        Assert.assertFalse(sut.hasModifier(HotKey.ModifierKey.META));
        Map<HotKey.ModifierKey, Boolean> modifiers = (Map<HotKey.ModifierKey, Boolean>) TestUtils.getField(sut, "modifiers");
        modifiers.replace(HotKey.ModifierKey.CONTROL, false);
        modifiers.replace(HotKey.ModifierKey.SHIFT, false);
        modifiers.replace(HotKey.ModifierKey.ALT, true);
        modifiers.replace(HotKey.ModifierKey.META, true);
        Assert.assertFalse(sut.hasModifier(HotKey.ModifierKey.CONTROL));
        Assert.assertFalse(sut.hasModifier(HotKey.ModifierKey.SHIFT));
        Assert.assertTrue(sut.hasModifier(HotKey.ModifierKey.ALT));
        Assert.assertTrue(sut.hasModifier(HotKey.ModifierKey.META));
    }
    
    /**
     * JUnit test of getCallback.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#getCallback()
     */
    @Test
    public void testGetCallback() throws Exception {
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        Assert.assertEquals(callback, sut.getCallback());
        Assert.assertEquals(callback, sut.getCallback());
    }
    
    /**
     * JUnit test of isActive.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#isActive()
     */
    @Test
    public void testIsActive() throws Exception {
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        Assert.assertTrue(sut.isActive());
        Assert.assertTrue(sut.isActive());
        TestUtils.setField(sut, "active", new AtomicBoolean(false));
        Assert.assertFalse(sut.isActive());
        Assert.assertFalse(sut.isActive());
    }
    
    /**
     * JUnit test of isHit.
     *
     * @throws Exception When there is an exception.
     * @see HotKey#isHit()
     */
    @Test
    public void testIsHit() throws Exception {
        sut = new HotKey(KeyEvent.VK_Z, true, true, false, false, callback);
        Assert.assertFalse(sut.isHit());
        Assert.assertFalse(sut.isHit());
        TestUtils.setField(sut, "hit", new AtomicBoolean(true));
        Assert.assertTrue(sut.isHit());
        Assert.assertTrue(sut.isHit());
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
