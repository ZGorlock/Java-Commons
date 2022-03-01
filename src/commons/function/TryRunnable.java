/*
 * File:    TryRunnable.java
 * Package: commons.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.function;

/**
 * A function that tries to run a task and returns no value and ignores errors.
 */
@FunctionalInterface
public interface TryRunnable extends Runnable {
    
    //Methods
    
    /**
     * Tries to run the task.
     *
     * @throws Exception When there is an error.
     */
    void tryRun() throws Exception;
    
    /**
     * Tries to run the task and ignores errors.
     *
     * @see Runnable#run()
     * @see #tryRun()
     */
    default void run() {
        try {
            tryRun();
        } catch (Throwable ignored) {
        }
    }
    
}
