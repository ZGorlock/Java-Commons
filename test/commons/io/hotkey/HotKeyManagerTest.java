/*
 * File:    HotKeyManagerTest.java
 * Package: commons.io.hotkey
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.hotkey;

import commons.access.OperatingSystem;
import commons.test.TestUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

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
        
        TestUtils.setField(HotKeyManager.class, "instance", null);
        TestUtils.setField(HotKeyManager.class, "instanced", new AtomicBoolean(false));
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
        Map<HotKey.ModifierKey, AtomicBoolean> modifierDown = (Map<HotKey.ModifierKey, AtomicBoolean>) TestUtils.getField(HotKeyManager.class, "modifierDown");
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
        TestUtils.setField(HotKeyManager.class, "instanced", new AtomicBoolean(false));
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(HotKeyManager.class, "instanced")).get());
        sut = Mockito.spy(HotKeyManager.getInstance());
        Assert.assertNotNull(TestUtils.getField(sut, "keyboardHook"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "setup")).get());
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(HotKeyManager.class, "instanced")).get());
        
        //direct call
        sut = PowerMockito.spy(sut);
        TestUtils.setField(sut, "hotKeys", new ArrayList<>());
        TestUtils.setField(sut, "keyboardHook", null);
        TestUtils.setField(sut, "setup", new AtomicBoolean(false));
        TestUtils.setField(HotKeyManager.class, "instanced", new AtomicBoolean(false));
        Assert.assertTrue((boolean) TestUtils.invokeMethod(sut, "setup"));
        keyboardHook = (NativeKeyListener) TestUtils.getField(sut, "keyboardHook");
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.registerNativeHook();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.addNativeKeyListener(ArgumentMatchers.any(NativeKeyListener.class));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.addNativeKeyListener(ArgumentMatchers.eq(keyboardHook));
        Assert.assertNotNull(TestUtils.getField(sut, "keyboardHook"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "setup")).get());
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(HotKeyManager.class, "instanced")).get());
        
        //already setup
        keyboardHook = (NativeKeyListener) TestUtils.getField(sut, "keyboardHook");
        Assert.assertNotNull(keyboardHook);
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "setup")).get());
        Assert.assertFalse((boolean) TestUtils.invokeMethod(sut, "setup"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.noMoreInteractions());
        HotKeyManager.GlobalScreenWrapper.registerNativeHook();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.noMoreInteractions());
        HotKeyManager.GlobalScreenWrapper.addNativeKeyListener(ArgumentMatchers.any(NativeKeyListener.class));
        Assert.assertEquals(keyboardHook, TestUtils.getField(sut, "keyboardHook"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(sut, "setup")).get());
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(HotKeyManager.class, "instanced")).get());
        
        //keyboard hook
        PowerMockito.doNothing().when(sut, "hit", ArgumentMatchers.any(NativeKeyEvent.class));
        PowerMockito.doNothing().when(sut, "release", ArgumentMatchers.any(NativeKeyEvent.class));
        NativeKeyEvent keyEvent = Mockito.mock(NativeKeyEvent.class);
        keyboardHook = (NativeKeyListener) TestUtils.getField(sut, "keyboardHook");
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
        TestUtils.setField(sut, "setup", new AtomicBoolean(false));
        Assert.assertFalse((boolean) TestUtils.invokeMethod(sut, "setup"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(3));
        HotKeyManager.GlobalScreenWrapper.registerNativeHook();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.isNativeHookRegistered();
        PowerMockito.doNothing().when(HotKeyManager.GlobalScreenWrapper.class, "registerNativeHook");
        PowerMockito.doThrow(new RuntimeException()).when(HotKeyManager.GlobalScreenWrapper.class, "addNativeKeyListener", ArgumentMatchers.any(NativeKeyListener.class));
        PowerMockito.doReturn(true).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        sut = PowerMockito.spy(sut);
        TestUtils.setField(sut, "setup", new AtomicBoolean(false));
        Assert.assertFalse((boolean) TestUtils.invokeMethod(sut, "setup"));
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
        TestUtils.setField(sut, "setup", new AtomicBoolean(false));
        Assert.assertFalse((boolean) TestUtils.invokeMethod(sut, "setup"));
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
        TestUtils.setField(sut, "keyboardHook", keyboardHook);
        PowerMockito.doReturn(true).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        TestUtils.invokeMethod(sut, "shutdown");
        Assert.assertNull(TestUtils.getField(sut, "keyboardHook"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.removeNativeKeyListener(ArgumentMatchers.eq(keyboardHook));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.isNativeHookRegistered();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1));
        HotKeyManager.GlobalScreenWrapper.unregisterNativeHook();
        
        //keyboardHook null
        TestUtils.setField(sut, "keyboardHook", null);
        PowerMockito.doReturn(true).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        TestUtils.invokeMethod(sut, "shutdown");
        Assert.assertNull(TestUtils.getField(sut, "keyboardHook"));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(1)); //still 1
        HotKeyManager.GlobalScreenWrapper.removeNativeKeyListener(ArgumentMatchers.eq(keyboardHook));
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.isNativeHookRegistered();
        PowerMockito.verifyStatic(HotKeyManager.GlobalScreenWrapper.class, VerificationModeFactory.times(2));
        HotKeyManager.GlobalScreenWrapper.unregisterNativeHook();
        
        //not registered
        TestUtils.setField(sut, "keyboardHook", keyboardHook);
        PowerMockito.doReturn(false).when(HotKeyManager.GlobalScreenWrapper.class, "isNativeHookRegistered");
        TestUtils.invokeMethod(sut, "shutdown");
        Assert.assertNull(TestUtils.getField(sut, "keyboardHook"));
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
        List<HotKey> hotKeys = (List<HotKey>) TestUtils.getField(sut, "hotKeys");
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
        List<HotKey> hotKeys = (List<HotKey>) TestUtils.getField(sut, "hotKeys");
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
        List<HotKey> hotKeys = (List<HotKey>) TestUtils.getField(sut, "hotKeys");
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
        List<HotKey> hotKeys = (List<HotKey>) TestUtils.getField(sut, "hotKeys");
        hotKeys.addAll(sampleHotkeys);
        Assert.assertEquals(sampleHotkeys.size(), hotKeys.size());
        Map<HotKey.ModifierKey, AtomicBoolean> modifierDown = (Map<HotKey.ModifierKey, AtomicBoolean>) TestUtils.getField(HotKeyManager.class, "modifierDown");
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
        TestUtils.invokeMethod(sut, "hit", keyEvent);
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
        TestUtils.invokeMethod(sut, "hit", keyEvent);
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
        TestUtils.invokeMethod(sut, "hit", keyEvent);
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertTrue(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertTrue(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        //invalid
        
        TestUtils.assertNoException(() ->
                TestUtils.invokeMethod(sut, "hit", (NativeKeyEvent) null));
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
        List<HotKey> hotKeys = (List<HotKey>) TestUtils.getField(sut, "hotKeys");
        hotKeys.addAll(sampleHotkeys);
        Assert.assertEquals(sampleHotkeys.size(), hotKeys.size());
        Map<HotKey.ModifierKey, AtomicBoolean> modifierDown = (Map<HotKey.ModifierKey, AtomicBoolean>) TestUtils.getField(HotKeyManager.class, "modifierDown");
        modifierDown.get(HotKey.ModifierKey.CONTROL).set(true);
        modifierDown.get(HotKey.ModifierKey.SHIFT).set(true);
        TestUtils.setField(hotKeys.get(1), "hit", new AtomicBoolean(true));
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
        TestUtils.invokeMethod(sut, "release", keyEvent);
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
        TestUtils.invokeMethod(sut, "release", keyEvent);
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
        TestUtils.invokeMethod(sut, "release", keyEvent);
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
        TestUtils.setField(hotKeys.get(1), "hit", new AtomicBoolean(true));
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
        TestUtils.invokeMethod(sut, "release", keyEvent);
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
        TestUtils.invokeMethod(sut, "release", keyEvent);
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
        TestUtils.invokeMethod(sut, "release", keyEvent);
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.CONTROL).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.SHIFT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.ALT).get());
        Assert.assertFalse(modifierDown.get(HotKey.ModifierKey.META).get());
        Assert.assertFalse(hotKeys.get(0).isHit());
        Assert.assertFalse(hotKeys.get(1).isHit());
        Assert.assertFalse(hotKeys.get(2).isHit());
        
        //invalid
        
        TestUtils.assertNoException(() ->
                TestUtils.invokeMethod(sut, "release", (NativeKeyEvent) null));
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
        Assert.assertNull(TestUtils.getField(HotKeyManager.class, "instance"));
        Assert.assertFalse(((AtomicBoolean) TestUtils.getField(HotKeyManager.class, "instanced")).get());
        
        //standard
        sut = HotKeyManager.getInstance();
        Assert.assertNotNull(sut);
        Assert.assertNotNull(TestUtils.getField(HotKeyManager.class, "instance"));
        Assert.assertEquals(sut, TestUtils.getField(HotKeyManager.class, "instance"));
        Assert.assertTrue(((AtomicBoolean) TestUtils.getField(HotKeyManager.class, "instanced")).get());
        
        //already instanced
        HotKeyManager oldSut = sut;
        sut = null;
        sut = HotKeyManager.getInstance();
        Assert.assertNotNull(sut);
        Assert.assertEquals(oldSut, sut);
        Assert.assertEquals(sut, TestUtils.getField(HotKeyManager.class, "instance"));
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
        TestUtils.setField(HotKeyManager.class, "instance", sut);
        TestUtils.setField(HotKeyManager.class, "instanced", new AtomicBoolean(true));
        
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
        TestUtils.setField(HotKeyManager.class, "instance", null);
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
        TestUtils.setField(HotKeyManager.class, "instance", sut);
        TestUtils.setField(HotKeyManager.class, "instanced", new AtomicBoolean(true));
        
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
        TestUtils.setField(HotKeyManager.class, "instance", null);
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
        TestUtils.setField(HotKeyManager.class, "instance", sut);
        TestUtils.setField(HotKeyManager.class, "instanced", new AtomicBoolean(true));
        
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
        TestUtils.setField(HotKeyManager.class, "instance", null);
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
