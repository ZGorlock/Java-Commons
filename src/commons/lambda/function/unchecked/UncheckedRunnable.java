/*
 * File:    UncheckedRunnable.java
 * Package: commons.lambda.function.unchecked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.unchecked;

/**
 * A lambda function that tries to run a task and returns no value and throws a runtime exception in the event of an error.
 *
 * @see Runnable
 */
@FunctionalInterface
public interface UncheckedRunnable extends Runnable {
    
    //Methods
    
    /**
     * Tries to run the task.
     *
     * @throws Exception When there is an error.
     */
    void tryRun() throws Exception;
    
    /**
     * Tries to run the task and throws a runtime exception in the event of an error.
     *
     * @throws RuntimeException When there is an error.
     * @see Runnable#run()
     * @see #tryRun()
     */
    @Override
    default void run() {
        try {
            tryRun();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    
    //Static Methods
    
    /**
     * Invokes an UncheckedRunnable.
     *
     * @param uncheckedRunnable The UncheckedRunnable.
     * @throws RuntimeException When there is an error.
     * @see #run()
     */
    static void invoke(UncheckedRunnable uncheckedRunnable) {
        uncheckedRunnable.run();
    }
    
}
