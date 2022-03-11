/*
 * File:    HotKeyManagerTest.java
 * Package: commons.io.hotkey
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.hotkey;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import commons.access.OperatingSystem;
import commons.test.TestAccess;
import commons.test.TestUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
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
 * JUnit test of HotKeyManager.
 *
 * @see HotKeyManager
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({HotKeyManager.class, GlobalScreen.class, HotKeyManager.GlobalScreenWrapper.class, OperatingSystem.class, NativeKeyEvent.class})
public class HotKeyManagerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(HotKeyManagerTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private HotKeyManager sut;
    
    /**
     * The native key listener for system under test.
     */
    private NativeKeyListener keyboardHook;
    
    /**
     * A list of sample HotKeys.
     */
    private List<HotKey> sampleHotkeys = new ArrayList<>();
    
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
        PowerMockito.spy(HotKeyManager.class);
        PowerMockito.mockStatic(HotKeyManager.GlobalScreenWrapper.class);
        PowerMockito.mockStatic(OperatingSystem.class);
        
        keyboardHook = Mockito.mock(NativeKeyListener.class);
        
        sampleHotkeys.add(new HotKey(KeyEvent.VK_T, true, false, true, false, callback));
        sampleHotkeys.add(new HotKey(KeyEvent.VK_6, true, true, false, false, callback));
        sampleHotkeys.add(new HotKey(HotKey.NO_KEY, false, false, false, true, callback));
        callback = Mockito.mock(HotKey.HotKeyCallback.class);
        
        TestAccess.setFieldValue(HotKeyManager.class, "instance", null);
        TestAccess.setFieldValue(HotKeyManager.class, "instanced", new AtomicBoolean(false));
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
    @Test
    public void testConstants() throws Exception {
        //static
        Map<HotKey.ModifierKey, AtomicBoolean> modifierDown = TestAccess.getFieldValue(HotKeyManager.class, Map.class, "modifierDown");
        Assert.assertNotNull(modifierDown);
        Assert.assertEquals(HotKey.ModifierKey.values().length, modifierDown.size());
        Assert.assertTrue(modifierDown.containsKey(HotKey.ModifierKey.CONTROL));
        Assert.assertTrue(modifierDown.containsKey(HotKey.ModifierKey.SHIFT));
        Assert.assertTrue(modifierDown.containsKey(HotKey.ModifierKey.ALT));
        Assert.assertTrue(modifierDown.containsKey(HotKey.ModifierKey.META));
    }
    
    /**
     * JUnit test of setup.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#setup()
     */
    @Test
    public void testSetup() throws Exception {
        //instantiation
        TestAccess.setFieldValue(HotKeyManager.class, "instanced", new AtomicBoolean(false));
        Assert.assertFalse(TestAccess.getFieldValue(HotKeyManager.class, AtomicBoolean.class, "instanced").get());
        sut = Mockito.spy(HotKeyManager.getInstance());
        Assert.assertNotNull(TestAccess.getFieldValue(sut, "keyboardHook"));
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "setup").get());
        Assert.assertTrue(TestAccess.getFieldValue(HotKeyManager.class, AtomicBoolean.class, "instanced").get());
        
        //direct call
        sut = PowerMockito.spy(sut);
        TestAccess.setFieldValue(sut, "hotKeys", new ArrayList<>());
        TestAccess.setFieldValue(sut, "keyboardHook", null);
        TestAccess.setFieldValue(sut, "setup", new AtomicBoolean(false));
        TestAccess.setFieldValue(HotKeyManager.class, "instanced", new AtomicBoolean(false));
        Assert.assertTrue(TestAccess.invokeMethod(sut, boolean.class, "setup"));
        keyboardHook = TestAccess.getFieldValue(sut, NativeKeyListener.class, "keyboardHook");
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.registerNativeHook();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.addNativeKeyListener(ArgumentMatchers.any(NativeKeyListener.class));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.addNativeKeyListener(ArgumentMatchers.eq(keyboardHook));
        Assert.assertNotNull(TestAccess.getFieldValue(sut, "keyboardHook"));
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "setup").get());
        Assert.assertFalse(TestAccess.getFieldValue(HotKeyManager.class, AtomicBoolean.class, "instanced").get());
        
        //already setup
        keyboardHook = TestAccess.getFieldValue(sut, NativeKeyListener.class, "keyboardHook");
        Assert.assertNotNull(keyboardHook);
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "setup").get());
        Assert.assertFalse(TestAccess.invokeMethod(sut, boolean.class, "setup"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.noMoreInteractions());
        HotKeyManager.GlobalScreenWrapper.registerNativeHook();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.noMoreInteractions());
        HotKeyManager.GlobalScreenWrapper.addNativeKeyListener(ArgumentMatchers.any(NativeKeyListener.class));
        Assert.assertEquals(keyboardHook, TestAccess.getFieldValue(sut, "keyboardHook"));
        Assert.assertTrue(TestAccess.getFieldValue(sut, AtomicBoolean.class, "setup").get());
        Assert.assertFalse(TestAccess.getFieldValue(HotKeyManager.class, AtomicBoolean.class, "instanced").get());
        
        //keyboard hook
        PowerMockito.doNothing().when(sut, "hit", ArgumentMatchers.any(NativeKeyEvent.class));
        PowerMockito.doNothing().when(sut, "release", ArgumentMatchers.any(NativeKeyEvent.class));
        NativeKeyEvent keyEvent = Mockito.mock(NativeKeyEvent.class);
        keyboardHook = TestAccess.getFieldValue(sut, NativeKeyListener.class, "keyboardHook");
        Assert.assertNotNull(keyboardHook);
        keyboardHook.nativeKeyPressed(keyEvent);
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("hit", keyEvent);
        keyboardHook.nativeKeyReleased(keyEvent);
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1))
                .invoke("release", keyEvent);
        keyboardHook.nativeKeyTyped(keyEvent);
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1)) //still 1
                .invoke("hit", keyEvent);
        PowerMockito.verifyPrivate(sut, VerificationModeFactory.times(1)) //still 1
                .invoke("release", keyEvent);
        
        //exception
        PowerMockito.doThrow(new NativeHookException()).when(HotKeyManager.GlobalScreenWrapper.class, "registerNativeHook");
        PowerMockito.doReturn(false).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        sut = PowerMockito.spy(sut);
        TestAccess.setFieldValue(sut, "setup", new AtomicBoolean(false));
        Assert.assertFalse(TestAccess.invokeMethod(sut, boolean.class, "setup"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(3));
        HotKeyManager.GlobalScreenWrapper.registerNativeHook();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.isNativeHookRegistered();
        PowerMockito.doNothing().when(HotKeyManager.GlobalScreenWrapper.class, "registerNativeHook");
        PowerMockito.doThrow(new RuntimeException()).when(HotKeyManager.GlobalScreenWrapper.class, "addNativeKeyListener", ArgumentMatchers.any(NativeKeyListener.class));
        PowerMockito.doReturn(true).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        sut = PowerMockito.spy(sut);
        TestAccess.setFieldValue(sut, "setup", new AtomicBoolean(false));
        Assert.assertFalse(TestAccess.invokeMethod(sut, boolean.class, "setup"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(4));
        HotKeyManager.GlobalScreenWrapper.registerNativeHook();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(3));
        HotKeyManager.GlobalScreenWrapper.addNativeKeyListener(ArgumentMatchers.any(NativeKeyListener.class));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.isNativeHookRegistered();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.unregisterNativeHook();
        PowerMockito.doNothing().when(HotKeyManager.GlobalScreenWrapper.class, "registerNativeHook");
        PowerMockito.doNothing().when(HotKeyManager.GlobalScreenWrapper.class, "addNativeKeyListener", ArgumentMatchers.any(NativeKeyListener.class));
        PowerMockito.doReturn(false).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        PowerMockito.doNothing().when(HotKeyManager.GlobalScreenWrapper.class, "unregisterNativeHook");
        
        //debugging
        PowerMockito.doReturn(true).when(OperatingSystem.class, "isDebugging");
        sut = PowerMockito.spy(sut);
        TestAccess.setFieldValue(sut, "setup", new AtomicBoolean(false));
        Assert.assertFalse(TestAccess.invokeMethod(sut, boolean.class, "setup"));
        PowerMockito.doReturn(false).when(OperatingSystem.class, "isDebugging");
    }
    
    /**
     * JUnit test of shutdown.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#shutdown()
     */
    @Test
    public void testShutdown() throws Exception {
        //standard
        sut = Mockito.spy(HotKeyManager.getInstance());
        TestAccess.setFieldValue(sut, "keyboardHook", keyboardHook);
        PowerMockito.doReturn(true).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        TestAccess.invokeMethod(sut, "shutdown");
        Assert.assertNull(TestAccess.getFieldValue(sut, "keyboardHook"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.removeNativeKeyListener(ArgumentMatchers.eq(keyboardHook));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.isNativeHookRegistered();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.unregisterNativeHook();
        
        //keyboardHook null
        TestAccess.setFieldValue(sut, "keyboardHook", null);
        PowerMockito.doReturn(true).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        TestAccess.invokeMethod(sut, "shutdown");
        Assert.assertNull(TestAccess.getFieldValue(sut, "keyboardHook"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1)); //still 1
        HotKeyManager.GlobalScreenWrapper.removeNativeKeyListener(ArgumentMatchers.eq(keyboardHook));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.isNativeHookRegistered();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.unregisterNativeHook();
        
        //not registered
        TestAccess.setFieldValue(sut, "keyboardHook", keyboardHook);
        PowerMockito.doReturn(false).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        TestAccess.invokeMethod(sut, "shutdown");
        Assert.assertNull(TestAccess.getFieldValue(sut, "keyboardHook"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.removeNativeKeyListener(ArgumentMatchers.eq(keyboardHook));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(3));
        HotKeyManager.GlobalScreenWrapper.isNativeHookRegistered();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2)); //still 2
        HotKeyManager.GlobalScreenWrapper.unregisterNativeHook();
    }
    
    /**
     * JUnit test of register.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#register(HotKey)
     */
    @Test
    public void testRegister() throws Exception {
        sut = Mockito.spy(HotKeyManager.getInstance());
        List<HotKey> hotKeys = TestAccess.getFieldValue(sut, List.class, "hotKeys");
        Assert.assertNotNull(hotKeys);
        Assert.assertTrue(hotKeys.isEmpty());
        
        //standard
        Assert.assertTrue(sut.register(sampleHotkeys.get(0)));
        Assert.assertTrue(hotKeys.contains(sampleHotkeys.get(0)));
        Assert.assertTrue(sut.register(sampleHotkeys.get(1)));
        Assert.assertTrue(hotKeys.contains(sampleHotkeys.get(1)));
        Assert.assertTrue(sut.register(sampleHotkeys.get(2)));
        Assert.assertTrue(hotKeys.contains(sampleHotkeys.get(2)));
        Assert.assertEquals(3, hotKeys.size());
        
        //already exists
        Assert.assertFalse(sut.register(sampleHotkeys.get(1)));
        Assert.assertTrue(hotKeys.contains(sampleHotkeys.get(1)));
        Assert.assertEquals(3, hotKeys.size());
    }
    
    /**
     * JUnit test of unregister.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#unregister(HotKey)
     */
    @Test
    public void testUnregister() throws Exception {
        sut = Mockito.spy(HotKeyManager.getInstance());
        List<HotKey> hotKeys = TestAccess.getFieldValue(sut, List.class, "hotKeys");
        Assert.assertNotNull(hotKeys);
        hotKeys.add(sampleHotkeys.get(0));
        hotKeys.add(sampleHotkeys.get(1));
        hotKeys.add(sampleHotkeys.get(2));
        Assert.assertEquals(3, hotKeys.size());
        
        //standard
        Assert.assertTrue(sut.unregister(sampleHotkeys.get(0)));
        Assert.assertFalse(hotKeys.contains(sampleHotkeys.get(0)));
        Assert.assertTrue(sut.unregister(sampleHotkeys.get(1)));
        Assert.assertFalse(hotKeys.contains(sampleHotkeys.get(1)));
        Assert.assertTrue(sut.unregister(sampleHotkeys.get(2)));
        Assert.assertFalse(hotKeys.contains(sampleHotkeys.get(2)));
        Assert.assertEquals(0, hotKeys.size());
        
        //doesnt exist
        Assert.assertFalse(sut.unregister(sampleHotkeys.get(1)));
        Assert.assertFalse(hotKeys.contains(sampleHotkeys.get(1)));
        Assert.assertEquals(0, hotKeys.size());
    }
    
    /**
     * JUnit test of has.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#has(HotKey)
     */
    @Test
    public void testHas() throws Exception {
        sut = Mockito.spy(HotKeyManager.getInstance());
        List<HotKey> hotKeys = TestAccess.getFieldValue(sut, List.class, "hotKeys");
        Assert.assertNotNull(hotKeys);
        hotKeys.add(sampleHotkeys.get(0));
        hotKeys.add(sampleHotkeys.get(2));
        Assert.assertEquals(2, hotKeys.size());
        
        //standard
        Assert.assertTrue(sut.has(sampleHotkeys.get(0)));
        Assert.assertTrue(hotKeys.contains(sampleHotkeys.get(0)));
        Assert.assertFalse(sut.has(sampleHotkeys.get(1)));
        Assert.assertFalse(hotKeys.contains(sampleHotkeys.get(1)));
        Assert.assertTrue(sut.has(sampleHotkeys.get(2)));
        Assert.assertTrue(hotKeys.contains(sampleHotkeys.get(2)));
        Assert.assertEquals(2, hotKeys.size());
    }
    
    /**
     * JUnit test of hit.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#hit(NativeKeyEvent)
     */
    @Test
    public void testHit() throws Exception {
        sut = Mockito.spy(HotKeyManager.getInstance());
        List<HotKey> hotKeys = TestAccess.getFieldValue(sut, List.class, "hotKeys");
        hotKeys.addAll(sampleHotkeys);
        Assert.assertEquals(sampleHotkeys.size(), hotKeys.size());
        Map<HotKey.ModifierKey, AtomicBoolean> modifierDown = TestAccess.getFieldValue(HotKeyManager.class, Map.class, "modifierDown");
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        PowerMockito.mockStatic(NativeKeyEvent.class);
        NativeKeyEvent keyEvent = Mockito.mock(NativeKeyEvent.class);
        
        //standard
        
        Mockito.doReturn(KeyEvent.VK_CONTROL).when(keyEvent).getRawCode();
        Mockito.doReturn(KeyEvent.VK_CONTROL).when(keyEvent).getKeyCode();
        PowerMockito.doReturn("Ctrl").when(NativeKeyEvent.class, "getKeyText", ArgumentMatchers.anyInt());
        TestAccess.invokeMethod(sut, "hit", keyEvent);
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertFalse(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        Mockito.doReturn(KeyEvent.VK_SHIFT).when(keyEvent).getRawCode();
        Mockito.doReturn(KeyEvent.VK_SHIFT).when(keyEvent).getKeyCode();
        PowerMockito.doReturn("Shift").when(NativeKeyEvent.class, "getKeyText", ArgumentMatchers.anyInt());
        TestAccess.invokeMethod(sut, "hit", keyEvent);
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertFalse(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        Mockito.doReturn(KeyEvent.VK_6).when(keyEvent).getRawCode();
        Mockito.doReturn(KeyEvent.VK_6).when(keyEvent).getKeyCode();
        PowerMockito.doReturn("6").when(NativeKeyEvent.class, "getKeyText", ArgumentMatchers.anyInt());
        TestAccess.invokeMethod(sut, "hit", keyEvent);
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertTrue(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        //invalid
        
        TestUtils.assertNoException(() ->
                TestAccess.invokeMethod(sut, "hit", (NativeKeyEvent) null));
    }
    
    /**
     * JUnit test of release.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#release(NativeKeyEvent)
     */
    @Test
    public void testRelease() throws Exception {
        sut = Mockito.spy(HotKeyManager.getInstance());
        List<HotKey> hotKeys = TestAccess.getFieldValue(sut, List.class, "hotKeys");
        hotKeys.addAll(sampleHotkeys);
        Assert.assertEquals(sampleHotkeys.size(), hotKeys.size());
        Map<HotKey.ModifierKey, AtomicBoolean> modifierDown = TestAccess.getFieldValue(HotKeyManager.class, Map.class, "modifierDown");
        modifierDown.get(HotKey.ModifierKey.CONTROL).set(true);
        modifierDown.get(HotKey.ModifierKey.SHIFT).set(true);
        TestAccess.setFieldValue(hotKeys.get(1), "hit", new AtomicBoolean(true));
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertTrue(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        PowerMockito.mockStatic(NativeKeyEvent.class);
        NativeKeyEvent keyEvent = Mockito.mock(NativeKeyEvent.class);
        
        //standard
        
        Mockito.doReturn(KeyEvent.VK_6).when(keyEvent).getRawCode();
        Mockito.doReturn(KeyEvent.VK_6).when(keyEvent).getKeyCode();
        PowerMockito.doReturn("6").when(NativeKeyEvent.class, "getKeyText", ArgumentMatchers.anyInt());
        TestAccess.invokeMethod(sut, "release", keyEvent);
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertFalse(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        Mockito.doReturn(KeyEvent.VK_SHIFT).when(keyEvent).getRawCode();
        Mockito.doReturn(KeyEvent.VK_SHIFT).when(keyEvent).getKeyCode();
        PowerMockito.doReturn("Shift").when(NativeKeyEvent.class, "getKeyText", ArgumentMatchers.anyInt());
        TestAccess.invokeMethod(sut, "release", keyEvent);
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertFalse(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        Mockito.doReturn(KeyEvent.VK_CONTROL).when(keyEvent).getRawCode();
        Mockito.doReturn(KeyEvent.VK_CONTROL).when(keyEvent).getKeyCode();
        PowerMockito.doReturn("Ctrl").when(NativeKeyEvent.class, "getKeyText", ArgumentMatchers.anyInt());
        TestAccess.invokeMethod(sut, "release", keyEvent);
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertFalse(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        //modifiers first
        
        modifierDown.get(HotKey.ModifierKey.CONTROL).set(true);
        modifierDown.get(HotKey.ModifierKey.SHIFT).set(true);
        TestAccess.setFieldValue(hotKeys.get(1), "hit", new AtomicBoolean(true));
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertTrue(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        Mockito.doReturn(KeyEvent.VK_CONTROL).when(keyEvent).getRawCode();
        Mockito.doReturn(KeyEvent.VK_CONTROL).when(keyEvent).getKeyCode();
        PowerMockito.doReturn("Ctrl").when(NativeKeyEvent.class, "getKeyText", ArgumentMatchers.anyInt());
        TestAccess.invokeMethod(sut, "release", keyEvent);
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertTrue(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        Mockito.doReturn(KeyEvent.VK_SHIFT).when(keyEvent).getRawCode();
        Mockito.doReturn(KeyEvent.VK_SHIFT).when(keyEvent).getKeyCode();
        PowerMockito.doReturn("Shift").when(NativeKeyEvent.class, "getKeyText", ArgumentMatchers.anyInt());
        TestAccess.invokeMethod(sut, "release", keyEvent);
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertFalse(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        Mockito.doReturn(KeyEvent.VK_6).when(keyEvent).getRawCode();
        Mockito.doReturn(KeyEvent.VK_6).when(keyEvent).getKeyCode();
        PowerMockito.doReturn("6").when(NativeKeyEvent.class, "getKeyText", ArgumentMatchers.anyInt());
        TestAccess.invokeMethod(sut, "release", keyEvent);
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertFalse(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        //invalid
        
        TestUtils.assertNoException(() ->
                TestAccess.invokeMethod(sut, "release", (NativeKeyEvent) null));
    }
    
    /**
     * JUnit test of getInstance.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        sut = null;
        PowerMockito.spy(HotKeyManager.class);
        Assert.assertNull(TestAccess.getFieldValue(HotKeyManager.class, "instance"));
        Assert.assertFalse(TestAccess.getFieldValue(HotKeyManager.class, AtomicBoolean.class, "instanced").get());
        
        //standard
        sut = HotKeyManager.getInstance();
        Assert.assertNotNull(sut);
        Assert.assertNotNull(TestAccess.getFieldValue(HotKeyManager.class, "instance"));
        Assert.assertEquals(sut, TestAccess.getFieldValue(HotKeyManager.class, "instance"));
        Assert.assertTrue(TestAccess.getFieldValue(HotKeyManager.class, AtomicBoolean.class, "instanced").get());
        
        //already instanced
        HotKeyManager oldSut = sut;
        sut = null;
        sut = HotKeyManager.getInstance();
        Assert.assertNotNull(sut);
        Assert.assertEquals(oldSut, sut);
        Assert.assertEquals(sut, TestAccess.getFieldValue(HotKeyManager.class, "instance"));
    }
    
    /**
     * JUnit test of registerHotkey.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#registerHotkey(HotKey)
     */
    @Test
    public void testRegisterHotkey() throws Exception {
        sut = Mockito.mock(HotKeyManager.class);
        HotKey hotKey = Mockito.mock(HotKey.class);
        TestAccess.setFieldValue(HotKeyManager.class, "instance", sut);
        TestAccess.setFieldValue(HotKeyManager.class, "instanced", new AtomicBoolean(true));
        
        //standard
        Mockito.doReturn(true).when(sut).register(ArgumentMatchers.any(HotKey.class));
        Assert.assertTrue(HotKeyManager.registerHotkey(hotKey));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .register(ArgumentMatchers.eq(hotKey));
        Mockito.doReturn(false).when(sut).register(ArgumentMatchers.any(HotKey.class));
        Assert.assertFalse(HotKeyManager.registerHotkey(hotKey));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .register(ArgumentMatchers.eq(hotKey));
        Mockito.doCallRealMethod().when(sut).register(ArgumentMatchers.any(HotKey.class));
        
        //unable to get instance
        TestAccess.setFieldValue(HotKeyManager.class, "instance", null);
        Assert.assertFalse(HotKeyManager.registerHotkey(hotKey));
        Mockito.verify(sut, VerificationModeFactory.noMoreInteractions())
                .register(ArgumentMatchers.any(HotKey.class));
    }
    
    /**
     * JUnit test of unregisterHotkey.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#unregisterHotkey(HotKey)
     */
    @Test
    public void testUnregisterHotkey() throws Exception {
        sut = Mockito.mock(HotKeyManager.class);
        HotKey hotKey = Mockito.mock(HotKey.class);
        TestAccess.setFieldValue(HotKeyManager.class, "instance", sut);
        TestAccess.setFieldValue(HotKeyManager.class, "instanced", new AtomicBoolean(true));
        
        //standard
        Mockito.doReturn(true).when(sut).unregister(ArgumentMatchers.any(HotKey.class));
        Assert.assertTrue(HotKeyManager.unregisterHotkey(hotKey));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .unregister(ArgumentMatchers.eq(hotKey));
        Mockito.doReturn(false).when(sut).unregister(ArgumentMatchers.any(HotKey.class));
        Assert.assertFalse(HotKeyManager.unregisterHotkey(hotKey));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .unregister(ArgumentMatchers.eq(hotKey));
        Mockito.doCallRealMethod().when(sut).register(ArgumentMatchers.any(HotKey.class));
        
        //unable to get instance
        TestAccess.setFieldValue(HotKeyManager.class, "instance", null);
        Assert.assertFalse(HotKeyManager.unregisterHotkey(hotKey));
        Mockito.verify(sut, VerificationModeFactory.noMoreInteractions())
                .unregister(ArgumentMatchers.any(HotKey.class));
    }
    
    /**
     * JUnit test of hasHotkey.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager#hasHotkey(HotKey)
     */
    @Test
    public void testHasHotkey() throws Exception {
        sut = Mockito.mock(HotKeyManager.class);
        HotKey hotKey = Mockito.mock(HotKey.class);
        TestAccess.setFieldValue(HotKeyManager.class, "instance", sut);
        TestAccess.setFieldValue(HotKeyManager.class, "instanced", new AtomicBoolean(true));
        
        //standard
        Mockito.doReturn(true).when(sut).has(ArgumentMatchers.any(HotKey.class));
        Assert.assertTrue(HotKeyManager.hasHotkey(hotKey));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .has(ArgumentMatchers.eq(hotKey));
        Mockito.doReturn(false).when(sut).has(ArgumentMatchers.any(HotKey.class));
        Assert.assertFalse(HotKeyManager.hasHotkey(hotKey));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .has(ArgumentMatchers.eq(hotKey));
        Mockito.doCallRealMethod().when(sut).register(ArgumentMatchers.any(HotKey.class));
        
        //unable to get instance
        TestAccess.setFieldValue(HotKeyManager.class, "instance", null);
        Assert.assertFalse(HotKeyManager.hasHotkey(hotKey));
        Mockito.verify(sut, VerificationModeFactory.noMoreInteractions())
                .has(ArgumentMatchers.any(HotKey.class));
    }
    
    /**
     * JUnit test of GlobalScreenWrapper.
     *
     * @throws Exception When there is an exception.
     * @see HotKeyManager.GlobalScreenWrapper
     */
    @Test
    public void testGlobalScreenWrapper() throws Exception {
        TestUtils.assertMethodExists(
                HotKeyManager.GlobalScreenWrapper.class, "registerNativeHook");
        TestUtils.assertMethodExists(
                HotKeyManager.GlobalScreenWrapper.class, "unregisterNativeHook");
        TestUtils.assertMethodExists(
                HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        TestUtils.assertMethodExists(
                HotKeyManager.GlobalScreenWrapper.class, "addNativeKeyListener", NativeKeyListener.class);
        TestUtils.assertMethodExists(
                HotKeyManager.GlobalScreenWrapper.class, "removeNativeKeyListener", NativeKeyListener.class);
    }
    
}
