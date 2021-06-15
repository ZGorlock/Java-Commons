/*
 * File:    HotKeyManager.java
 * Package: commons.io.hotkey
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.hotkey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;

import commons.access.OperatingSystem;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages global HotKeys.
 */
public final class HotKeyManager {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(HotKeyManager.class);
    
    
    //Static Fields
    
    /**
     * A map of flags indicating whether each Modifier Key is down or not.
     */
    private static final Map<HotKey.ModifierKey, AtomicBoolean> modifierDown;
    
    //Populate modifierDown
    static {
        Map<HotKey.ModifierKey, AtomicBoolean> modifierDownTmp = new LinkedHashMap<>();
        modifierDownTmp.put(HotKey.ModifierKey.CONTROL, new AtomicBoolean(false));
        modifierDownTmp.put(HotKey.ModifierKey.SHIFT, new AtomicBoolean(false));
        modifierDownTmp.put(HotKey.ModifierKey.ALT, new AtomicBoolean(false));
        modifierDownTmp.put(HotKey.ModifierKey.META, new AtomicBoolean(false));
        modifierDown = Collections.unmodifiableMap(modifierDownTmp);
    }
    
    /**
     * The singleton instance of the HotKey Manager.
     */
    private static HotKeyManager instance = null;
    
    /**
     * A flag indicating whether an instance has been created or not.
     */
    private static final AtomicBoolean instanced = new AtomicBoolean(false);
    
    
    //Fields
    
    /**
     * The global key listener for the keyboard.
     */
    private NativeKeyListener keyboardHook = null;
    
    /**
     * A list of registered hotkeys.
     */
    private final List<HotKey> hotKeys = new ArrayList<>();
    
    /**
     * A flag indicating whether the HotKey Manager has been setup already or not.
     */
    private final AtomicBoolean setup = new AtomicBoolean(false);
    
    
    //Constructors
    
    /**
     * The private constructor for the HotKey Manager.
     */
    private HotKeyManager() {
        setup();
    }
    
    
    //Methods
    
    /**
     * Sets up the HotKey Manager.
     *
     * @return Whether the setup was successful or not.
     */
    private boolean setup() {
        if (OperatingSystem.isDebugging()) {
            return false;
        }
        
        if (setup.compareAndSet(false, true)) {
            java.util.logging.Logger jNativeLogger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
            jNativeLogger.setLevel(Level.WARNING);
            jNativeLogger.setUseParentHandlers(false);
            
            try {
                logger.trace("Registering the native keyboard hook");
                GlobalScreen.registerNativeHook();
                
                logger.trace("Adding the global hotkey listener");
                keyboardHook = new NativeKeyListener() {
                    @Override
                    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                        hit(nativeKeyEvent);
                    }
                    
                    @Override
                    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
                        release(nativeKeyEvent);
                    }
                    
                    @Override
                    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
                    }
                };
                GlobalScreen.addNativeKeyListener(keyboardHook);
                
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    if (keyboardHook != null) {
                        logger.trace("Removing the global hotkey hook");
                        GlobalScreen.removeNativeKeyListener(keyboardHook);
                    } else {
                        logger.trace("The global hotkey hook was never added");
                    }
                    
                    if (GlobalScreen.isNativeHookRegistered()) {
                        logger.trace("Unregistering the native keyboard hook");
                        try {
                            GlobalScreen.unregisterNativeHook();
                        } catch (NativeHookException ignored) {
                            logger.warn("There was an error unregistering the native keyboard hook");
                        }
                    } else {
                        logger.trace("The native keyboard hook was never registered");
                    }
                }));
                
            } catch (NativeHookException e) {
                logger.error("There was an error setting the global hotkey listener; continuing with setup");
                if (GlobalScreen.isNativeHookRegistered()) {
                    try {
                        GlobalScreen.unregisterNativeHook();
                    } catch (NativeHookException ignored) {
                    }
                }
                return false;
            }
            return true;
            
        } else {
            return false;
        }
    }
    
    /**
     * Registers a HotKey.
     *
     * @param hotKey The HotKey being registered.
     * @return Whether the HotKey was successfully registered or not.
     */
    public synchronized boolean register(HotKey hotKey) {
        if (!has(hotKey)) {
            hotKeys.add(hotKey);
            logger.trace("The hotkey: {} was registered with the system", hotKey);
            return true;
            
        } else {
            logger.trace("The hotkey: {} has already been registered", hotKey);
            return false;
        }
    }
    
    /**
     * Unregisters a HotKey.
     *
     * @param hotKey The HotKey being unregistered.
     * @return Whether the HotKey was successfully unregistered or not.
     */
    public synchronized boolean unregister(HotKey hotKey) {
        if (has(hotKey)) {
            hotKeys.remove(hotKey);
            logger.trace("The hotkey: {} was unregistered with the system", hotKey);
            return true;
            
        } else {
            logger.trace("The hotkey: {} has already been unregistered", hotKey);
            return false;
        }
    }
    
    /**
     * Determines if a HotKey is registered.
     *
     * @param hotKey The HotKey to check for.
     * @return Whether the HotKey is registered or not.
     */
    public synchronized boolean has(HotKey hotKey) {
        return hotKeys.contains(hotKey);
    }
    
    /**
     * A method that is called when a key hit event is detected.
     *
     * @param event The key hit event.
     */
    private void hit(NativeKeyEvent event) {
        int code = event.getRawCode();
        
        HotKey.ModifierKey modifierKeyReleased = HotKey.ModifierKey.getModifierKeyByName(NativeKeyEvent.getKeyText(event.getKeyCode()));
        if (modifierKeyReleased != null) {
            modifierDown.get(modifierKeyReleased).set(true);
        }
        
        hotKeys.parallelStream()
                .filter(e -> e.isMatch(code, modifierDown, false))
                .forEach(HotKey::hit);
    }
    
    /**
     * A method that is called when a key release event is detected.
     *
     * @param event The key release event.
     */
    private void release(NativeKeyEvent event) {
        int code = event.getRawCode();
        
        HotKey.ModifierKey modifierKeyReleased = HotKey.ModifierKey.getModifierKeyByName(NativeKeyEvent.getKeyText(event.getKeyCode()));
        if (modifierKeyReleased != null) {
            modifierDown.get(modifierKeyReleased).set(false);
        }
        
        hotKeys.parallelStream()
                .filter(e -> e.isMatch(code, modifierDown, true))
                .forEach(HotKey::release);
    }
    
    
    //Getters
    
    /**
     * Creates and returns the singleton instance of the HotKey Manager.
     *
     * @return The singleton instance of the HotKey Manager.
     */
    public static HotKeyManager getInstance() {
        if (instanced.compareAndSet(false, true)) {
            instance = new HotKeyManager();
        }
        return instance;
    }
    
    
    //Functions
    
    /**
     * Registers a HotKey.
     *
     * @param hotKey The HotKey being registered.
     * @return Whether the HotKey was successfully registered or not.
     * @see #register(HotKey)
     */
    public static boolean registerHotkey(HotKey hotKey) {
        HotKeyManager hotKeyManager = getInstance();
        if (hotKeyManager != null) {
            return hotKeyManager.register(hotKey);
        }
        return false;
    }
    
    /**
     * Unregisters a HotKey.
     *
     * @param hotKey The HotKey being unregistered.
     * @return Whether the HotKey was successfully unregistered or not.
     * @see #unregister(HotKey)
     */
    public static boolean unregisterHotkey(HotKey hotKey) {
        HotKeyManager hotKeyManager = getInstance();
        if (hotKeyManager != null) {
            return hotKeyManager.unregister(hotKey);
        }
        return false;
    }
    
    /**
     * Determines if a HotKey is registered.
     *
     * @param hotKey The HotKey to check for.
     * @return Whether the HotKey is registered or not.
     * @see #has(HotKey)
     */
    public static boolean hasHotkey(HotKey hotKey) {
        HotKeyManager hotKeyManager = getInstance();
        if (hotKeyManager != null) {
            return hotKeyManager.has(hotKey);
        }
        return false;
    }
    
}
