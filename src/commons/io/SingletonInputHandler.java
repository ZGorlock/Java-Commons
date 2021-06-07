/*
 * File:    SingletonInputHandler.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prevents input handling classes from being used concurrently.
 */
public abstract class SingletonInputHandler {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SingletonInputHandler.class);
    
    
    //Static Fields
    
    /**
     * The current owner of the Input Handler.
     */
    protected static String owner = "";
    
    /**
     * The default owner of the Input Handler.
     */
    protected static String defaultOwner = "";
    
    /**
     * The action to perform to interrupt the Input Handler.
     */
    protected static Runnable interrupt = () -> {
    };
    
    
    //Functions
    
    /**
     * Determines if a specified class is the owner of the Input Handler.
     *
     * @param owner The calling class.
     * @return Whether the class is the owner of the Input Handler or not.
     */
    public static synchronized boolean owns(Class<?> owner) {
        return SingletonInputHandler.owner.equals(owner.getCanonicalName());
    }
    
    /**
     * Determines if a specified class is the owner of the Input Handler.
     *
     * @param owner The calling object.
     * @return Whether the class is the owner of the Input Handler or not.
     * @see #owns(Class)
     */
    public static synchronized boolean owns(Object owner) {
        return owns(owner.getClass());
    }
    
    /**
     * Claims ownership of the Input Handler.
     *
     * @param owner The new owner of the Input Handler.
     * @return Whether the class acquired ownership of the Input Handler or not.
     */
    public static synchronized boolean own(Class<?> owner) {
        if (SingletonInputHandler.owner.equals(SingletonInputHandler.defaultOwner) ||
                owner.getCanonicalName().equals(SingletonInputHandler.defaultOwner)) {
            interrupt.run();
            SingletonInputHandler.owner = owner.getCanonicalName();
            return true;
        }
        return false;
    }
    
    /**
     * Claims ownership of the Input Handler.
     *
     * @param owner The new owner of the Input Handler.
     * @return Whether the class acquired ownership of the Input Handler or not.
     * @see #own(Class)
     */
    public static synchronized boolean own(Object owner) {
        return own(owner.getClass());
    }
    
    /**
     * Claims the default ownership of the Input Handler.
     *
     * @param owner The default owner of the Input Handler.
     * @return Whether default ownership was successfully acquired or not.
     */
    public static synchronized boolean defaultOwn(Class<?> owner) {
        if (SingletonInputHandler.defaultOwner.isEmpty()) {
            interrupt.run();
            SingletonInputHandler.defaultOwner = owner.getCanonicalName();
            SingletonInputHandler.owner = SingletonInputHandler.defaultOwner;
            return true;
        }
        return false;
    }
    
    /**
     * Claims the default ownership of the Input Handler.
     *
     * @param owner The default owner of the Input Handler.
     * @return Whether default ownership was successfully acquired or not.
     * @see #defaultOwn(Class)
     */
    public static synchronized boolean defaultOwn(Object owner) {
        return defaultOwn(owner.getClass());
    }
    
    /**
     * Relinquishes the ownership of the Input Handler to the default owner.
     *
     * @param owner The calling class.
     * @return Whether the class relinquished ownership of the Input Handler or not.
     */
    public static synchronized boolean relinquish(Class<?> owner) {
        if (owner.getCanonicalName().equals(SingletonInputHandler.owner) ||
                (SingletonInputHandler.owner.equals(SingletonInputHandler.defaultOwner) && !SingletonInputHandler.owner.isEmpty())) {
            interrupt.run();
            SingletonInputHandler.owner = SingletonInputHandler.defaultOwner;
            return true;
        }
        return false;
    }
    
    /**
     * Relinquishes the ownership of the Input Handler to the default owner.
     *
     * @param owner The calling object.
     * @return Whether the class relinquished ownership of the Input Handler or not.
     * @see #relinquish(Class)
     */
    public static synchronized boolean relinquish(Object owner) {
        return relinquish(owner.getClass());
    }
    
}
