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
    
    
    //Fields
    
    /**
     * The current owner of the Input Handler.
     */
    protected String owner = "";
    
    /**
     * The default owner of the Input Handler.
     */
    protected String defaultOwner = "";
    
    /**
     * The action to perform to interrupt the Input Handler.
     */
    protected Runnable interrupt = () -> {
    };
    
    
    //Methods
    
    /**
     * Determines if a specified class is the owner of the Input Handler.
     *
     * @param owner The calling class.
     * @return Whether the class is the owner of the Input Handler or not.
     */
    protected synchronized boolean isOwner(Class<?> owner) {
        return (owner != null) && this.owner.equals(owner.getCanonicalName());
    }
    
    /**
     * Determines if a specified class is the owner of the Input Handler.
     *
     * @param owner The calling object.
     * @return Whether the class is the owner of the Input Handler or not.
     * @see #isOwner(Class)
     */
    protected synchronized boolean isOwner(Object owner) {
        return isOwner((owner != null) ? owner.getClass() : null);
    }
    
    /**
     * Claims ownership of the Input Handler.
     *
     * @param owner The new owner of the Input Handler.
     * @return Whether the class acquired ownership of the Input Handler or not.
     */
    protected synchronized boolean claimOwnership(Class<?> owner) {
        if ((owner != null) &&
                (this.owner.equals(this.defaultOwner) ||
                        owner.getCanonicalName().equals(this.defaultOwner))) {
            this.interrupt.run();
            this.owner = owner.getCanonicalName();
            return true;
        }
        return false;
    }
    
    /**
     * Claims ownership of the Input Handler.
     *
     * @param owner The new owner of the Input Handler.
     * @return Whether the class acquired ownership of the Input Handler or not.
     * @see #claimOwnership(Class)
     */
    protected synchronized boolean claimOwnership(Object owner) {
        return claimOwnership((owner != null) ? owner.getClass() : null);
    }
    
    /**
     * Claims the default ownership of the Input Handler.
     *
     * @param owner The default owner of the Input Handler.
     * @return Whether default ownership was successfully acquired or not.
     */
    protected synchronized boolean claimDefaultOwnership(Class<?> owner) {
        if ((owner != null) &&
                this.defaultOwner.isEmpty()) {
            this.interrupt.run();
            this.defaultOwner = owner.getCanonicalName();
            this.owner = this.defaultOwner;
            return true;
        }
        return false;
    }
    
    /**
     * Claims the default ownership of the Input Handler.
     *
     * @param owner The default owner of the Input Handler.
     * @return Whether default ownership was successfully acquired or not.
     * @see #claimDefaultOwnership(Class)
     */
    protected synchronized boolean claimDefaultOwnership(Object owner) {
        return claimDefaultOwnership((owner != null) ? owner.getClass() : null);
    }
    
    /**
     * Relinquishes the ownership of the Input Handler to the default owner.
     *
     * @param owner The calling class.
     * @return Whether the class relinquished ownership of the Input Handler or not.
     */
    protected synchronized boolean relinquishOwnership(Class<?> owner) {
        if ((owner != null) && (
                owner.getCanonicalName().equals(this.owner) ||
                        (this.owner.equals(this.defaultOwner) && !this.owner.isEmpty()))) {
            this.interrupt.run();
            this.owner = this.defaultOwner;
            return true;
        }
        return false;
    }
    
    /**
     * Relinquishes the ownership of the Input Handler to the default owner.
     *
     * @param owner The calling object.
     * @return Whether the class relinquished ownership of the Input Handler or not.
     * @see #relinquishOwnership(Class)
     */
    protected synchronized boolean relinquishOwnership(Object owner) {
        return relinquishOwnership((owner != null) ? owner.getClass() : null);
    }
    
}
