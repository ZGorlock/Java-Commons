/*
 * File:    CheckedRunnable.java
 * Package: commons.lambda.function.checked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.checked;

/**
 * A lambda function that tries to run a task and returns no value and ignores errors.
 */
@FunctionalInterface
public interface CheckedRunnable extends Runnable {
    
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
