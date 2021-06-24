/*
 * File:    HotKey.java
 * Package: commons.io.hotkey
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.hotkey;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a global HotKey.
 */
public class HotKey {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(HotKey.class);
    
    
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
    
    
    //Constants
    
    /**
     * The virtual key code to use when you are not expecting any.
     */
    public static int NO_KEY = -1;
    
    
    //Fields
    
    /**
     * The virtual key code of the main key of the HotKey.
     */
    private final int code;
    
    /**
     * A map of flags indicating which Modifier Keys are components of the HotKey.
     */
    private final Map<ModifierKey, Boolean> modifiers = new LinkedHashMap<>();
    
    /**
     * The callback to alert when the HotKey is pressed or released.
     */
    private final HotKeyCallback callback;
    
    /**
     * Whether the HotKey is activated or not.<br>
     * The HotKey is active by default.
     */
    private final AtomicBoolean active = new AtomicBoolean(true);
    
    /**
     * Whether the HotKey is currently hit or not.
     */
    private final AtomicBoolean hit = new AtomicBoolean(false);
    
    
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
        this.code = Math.max(code, NO_KEY);
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
     * Determines if another HotKey is equal to this HotKey.
     *
     * @param o The other HotKey.
     * @return Whether the two HotKeys are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HotKey)) {
            return false;
        }
        HotKey other = (HotKey) o;
        
        return (code == other.code) &&
                (modifiers.get(ModifierKey.CONTROL) == other.modifiers.get(ModifierKey.CONTROL)) &&
                (modifiers.get(ModifierKey.SHIFT) == other.modifiers.get(ModifierKey.SHIFT)) &&
                (modifiers.get(ModifierKey.ALT) == other.modifiers.get(ModifierKey.ALT)) &&
                (modifiers.get(ModifierKey.META) == other.modifiers.get(ModifierKey.META));
        
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
     * Hits the HotKey.
     */
    public void hit() {
        if (isActive() && !isHit()) {
            hit.set(true);
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
            hit.set(false);
            if (callback != null) {
                callback.release();
            }
        }
    }
    
    /**
     * Determines if a key code combined with the current special key states is a match for this HotKey.
     *
     * @param keyCode      The key code.
     * @param modifierDown A map indicating whether each Modifier Key is currently down or not.
     * @param checkRelease A flag indicating whether to check the match of  a release instead of a hit.
     * @return Whether the key code combined with the current special key states is a match for this HotKey or not.
     */
    public boolean isMatch(int keyCode, Map<ModifierKey, AtomicBoolean> modifierDown, boolean checkRelease) {
        boolean code = ((getCode() == keyCode) || (getCode() == NO_KEY));
        boolean modifiers = (!hasModifier(ModifierKey.CONTROL) || (modifierDown.get(ModifierKey.CONTROL).get() ^ checkRelease)) &&
                (!hasModifier(ModifierKey.SHIFT) || (modifierDown.get(ModifierKey.SHIFT).get() ^ checkRelease)) &&
                (!hasModifier(ModifierKey.ALT) || (modifierDown.get(ModifierKey.ALT).get() ^ checkRelease)) &&
                (!hasModifier(ModifierKey.META) || (modifierDown.get(ModifierKey.META).get() ^ checkRelease));
        
        return checkRelease ? (code || modifiers) : (code && modifiers);
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
    
    
    //Inner Classes
    
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
