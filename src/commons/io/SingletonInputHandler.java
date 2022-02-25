/*
 * File:    SingletonInputHandler.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.util.concurrent.atomic.AtomicReference;

import commons.object.CastUtility;
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
    protected final AtomicReference<Object> owner = new AtomicReference<>(null);
    
    /**
     * The manager of the Input Handler.
     */
    protected final AtomicReference<Object> manager = new AtomicReference<>(null);
    
    /**
     * The action to perform to interrupt the Input Handler.
     */
    protected Runnable interrupt = () -> {
    };
    
    
    //Methods
    
    /**
     * Determines if a specified caller is the owner of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller is the owner of the Input Handler or not.
     */
    protected boolean isOwner(Object caller) {
        return (caller != null) && ((caller == owner.get()) || (CastUtility.toClass(caller) == owner.get()));
    }
    
    /**
     * Determines if a specified caller is the manager of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller is the manager of the Input Handler or not.
     */
    protected boolean isManager(Object caller) {
        return (caller != null) && (caller == manager.get());
    }
    
    /**
     * Acquires ownership of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller acquired ownership of the Input Handler or not.
     */
    protected boolean acquireOwnership(Object caller) {
        if ((caller != null) && !isOwner(caller) && ((owner.get() == null) || isManager(caller))) {
            interrupt.run();
            owner.set(caller);
            return true;
        }
        return false;
    }
    
    /**
     * Relinquishes ownership of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller relinquished ownership of the Input Handler or not.
     */
    protected boolean releaseOwnership(Object caller) {
        if ((caller != null) && (owner.get() != null) && (isOwner(caller) || isManager(caller))) {
            interrupt.run();
            owner.set(null);
            return true;
        }
        return false;
    }
    
    /**
     * Acquires management of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller acquired management of the Input Handler or not.
     */
    protected boolean acquireManagement(Object caller) {
        if ((caller != null) && (manager.get() == null)) {
            manager.set(caller);
            return true;
        }
        return false;
    }
    
    /**
     * Relinquishes management of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller relinquished management of the Input Handler or not.
     */
    protected boolean releaseManagement(Object caller) {
        if ((caller != null) && isManager(caller)) {
            manager.set(null);
            return true;
        }
        return false;
    }
    
}
