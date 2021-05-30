/*
 * File:    HotKeyManager.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.stream.Collectors;

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
    
    
    //Enums
    
    /**
     * An enumeration of Modifier Keys.
     */
    public enum ModifierKey {
        
        //Values
        
        CONTROL("Ctrl"),
        SHIFT("Shift", "Unknown keyCode: 0xe36"),
        ALT("Alt"),
        META("Meta");
        
        
        //Fields
        
        /**
         * The name of the Modifier Key.
         */
        private final String modifierName;
        
        /**
         * A list of names the Modifier Key might have.
         */
        private final List<String> names = new ArrayList<>();
        
        
        //Constructors
        
        /**
         * Constructs a Modifier Key
         *
         * @param name           The name of the Modifier Key.
         * @param alternateNames A list of alternate names of the Modifier Key.
         */
        ModifierKey(String name, String... alternateNames) {
            this.modifierName = name;
            this.names.add(name);
            this.names.addAll(Arrays.asList(alternateNames));
        }
        
        
        //Getters
        
        /**
         * Returns the name of the Modifier Key.
         *
         * @return The name of the Modifier Key.
         */
        public String getName() {
            return modifierName;
        }
        
        
        //Functions
        
        /**
         * Finds a Modifier Key by a name.
         *
         * @param name The name of the Modifier Key.
         * @return The Modifier Key by that name, or null if none are found.
         */
        public static ModifierKey getModifierKeyByName(String name) {
            return Arrays.stream(ModifierKey.values())
                    .filter(e -> e.names.contains(name))
                    .findFirst().orElse(null);
        }
        
    }
    
    
    //Static Fields
    
    /**
     * A map of flags indicating whether each Modifier Key is down or not.
     */
    private static final Map<ModifierKey, AtomicBoolean> modifierDown;
    
    //Populate modifierDown
    static {
        Map<ModifierKey, AtomicBoolean> modifierDownTmp = new LinkedHashMap<>();
        modifierDownTmp.put(ModifierKey.CONTROL, new AtomicBoolean(false));
        modifierDownTmp.put(ModifierKey.SHIFT, new AtomicBoolean(false));
        modifierDownTmp.put(ModifierKey.ALT, new AtomicBoolean(false));
        modifierDownTmp.put(ModifierKey.META, new AtomicBoolean(false));
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
    public boolean setup() {
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
        
        ModifierKey modifierKeyReleased = ModifierKey.getModifierKeyByName(NativeKeyEvent.getKeyText(event.getKeyCode()));
        if (modifierKeyReleased != null) {
            modifierDown.get(modifierKeyReleased).set(true);
        }
        
        hotKeys.parallelStream()
                .filter(e -> e.isMatch(code))
                .forEach(HotKey::hit);
    }
    
    /**
     * A method that is called when a key release event is detected.
     *
     * @param event The key release event.
     */
    private void release(NativeKeyEvent event) {
        int code = event.getRawCode();
        
        ModifierKey modifierKeyReleased = ModifierKey.getModifierKeyByName(NativeKeyEvent.getKeyText(event.getKeyCode()));
        if (modifierKeyReleased != null) {
            modifierDown.get(modifierKeyReleased).set(false);
        }
        
        hotKeys.parallelStream()
                .filter(e -> e.isMatch(code))
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
    
    
    //Inner Classes
    
    /**
     * Defines a HotKey event.
     */
    public static class HotKey {
        
        //Constants
        
        /**
         * The virtual key code to use when you are not expecting any.
         */
        public static int NO_KEY = -1;
        
        
        //Fields
        
        /**
         * The virtual key code of the main key of the HotKey.
         */
        private int code;
        
        /**
         * A map of flags indicating which Modifier Keys are components of the HotKey.
         */
        private final Map<ModifierKey, Boolean> modifiers = new LinkedHashMap<>();
        
        /**
         * The callback to alert when the HotKey is pressed or released.
         */
        private HotKeyCallback callback;
        
        /**
         * Whether the HotKey is activated or not.<br>
         * The HotKey is active by default.
         */
        private AtomicBoolean active = new AtomicBoolean(true);
        
        /**
         * Whether the HotKey is currently hit or not.
         */
        private AtomicBoolean hit = new AtomicBoolean(false);
        
        
        //Constructors
        
        /**
         * Constructor for a HotKey.
         *
         * @param code     The virtual key code value of the main key of the HotKey.
         * @param control  Whether the control key is a member of the HotKey.
         * @param shift    Whether the shift key is a member of the HotKey.
         * @param alt      Whether the alt key is a member of the HotKey.
         * @param meta     Whether the meta key is a member of the HotKey.
         * @param callback The callback to be alerted when the HotKey is pressed or released.
         */
        public HotKey(int code, boolean control, boolean shift, boolean alt, boolean meta, HotKeyCallback callback) {
            this.code = code;
            this.modifiers.put(ModifierKey.CONTROL, control);
            this.modifiers.put(ModifierKey.SHIFT, shift);
            this.modifiers.put(ModifierKey.ALT, alt);
            this.modifiers.put(ModifierKey.META, meta);
            this.callback = callback;
        }
        
        
        //Methods
        
        /**
         * Returns the string representing the HotKey.
         *
         * @return The string representing the HotKey.
         */
        @Override
        public String toString() {
            List<String> parts = new ArrayList<>();
            parts.add(modifiers.get(ModifierKey.CONTROL) ? ModifierKey.CONTROL.getName() : "");
            parts.add(modifiers.get(ModifierKey.SHIFT) ? ModifierKey.SHIFT.getName() : "");
            parts.add(modifiers.get(ModifierKey.ALT) ? ModifierKey.ALT.getName() : "");
            parts.add(modifiers.get(ModifierKey.META) ? ModifierKey.META.getName() : "");
            parts.add((code != NO_KEY) ? KeyEvent.getKeyText(code) : "");
            return parts.stream().filter(e -> !e.isEmpty())
                    .collect(Collectors.joining("-", "[", "]"));
        }
        
        /**
         * Sets the HotKey to an active state.
         */
        public void activate() {
            active.set(true);
        }
        
        /**
         * Sets the HotKey to an inactive state.
         */
        public void deactivate() {
            active.set(false);
        }
        
        /**
         * Hit the HotKey.
         */
        public void hit() {
            if (isActive() && !isHit()) {
                setHit(true);
                if (callback != null) {
                    callback.hit();
                }
            }
        }
        
        /**
         * Releases the HotKey.
         */
        public void release() {
            if (isActive() && isHit()) {
                setHit(false);
                if (callback != null) {
                    callback.release();
                }
            }
        }
        
        /**
         * Determines if a key code combined with the current special key states is a match for this HotKey.
         *
         * @return Whether the key code combined with the current special key states is a match for this HotKey or not.
         */
        public boolean isMatch(int keyCode) {
            return ((getCode() == code) || (getCode() == HotKey.NO_KEY)) &&
                    (!hasModifier(ModifierKey.CONTROL) || modifierDown.get(ModifierKey.CONTROL).get()) &&
                    (!hasModifier(ModifierKey.SHIFT) || modifierDown.get(ModifierKey.SHIFT).get()) &&
                    (!hasModifier(ModifierKey.ALT) || modifierDown.get(ModifierKey.ALT).get()) &&
                    (!hasModifier(ModifierKey.META) || modifierDown.get(ModifierKey.META).get());
        }
        
        
        //Getters
        
        /**
         * Returns the virtual key code of the main key of the HotKey.
         *
         * @return The virtual key code of main key of the HotKey.
         */
        public int getCode() {
            return code;
        }
        
        /**
         * Returns whether a Modifier Key is a component of the HotKey or not.
         *
         * @param modifierKey The Modifier Key to check for.
         * @return Whether the Modifier Key is a component of the HotKey or not.
         */
        public boolean hasModifier(ModifierKey modifierKey) {
            return modifiers.get(modifierKey);
        }
        
        /**
         * Returns callback to alert when the HotKey is pressed or released.
         *
         * @return The callback to alert when the HotKey is pressed or released.
         */
        public HotKeyCallback getCallback() {
            return callback;
        }
        
        /**
         * Returns whether HotKey is active or not.
         *
         * @return Whether HotKey is active or not.
         */
        public boolean isActive() {
            return active.get();
        }
        
        /**
         * Returns whether the HotKey is currently hit or not.
         *
         * @return Whether the HotKey is currently hit or not.
         */
        public boolean isHit() {
            return hit.get();
        }
        
        /**
         * Sets whether the HotKey is currently hit or not.
         *
         * @param hit Whether the HotKey is currently hit or not.
         */
        public void setHit(boolean hit) {
            this.hit.set(hit);
        }
        
    }
    
    /**
     * Defines a callback for a HotKey event.
     */
    public interface HotKeyCallback {
        
        //Methods
        
        /**
         * Called when the HotKey is pressed.
         */
        void hit();
        
        /**
         * Called when the HotKey is released.
         */
        void release();
        
    }
    
}
