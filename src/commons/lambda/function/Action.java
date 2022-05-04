/*
 * File:    Action.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

/**
 * Represents a lambda function that runs a task and returns no value and may throw an error.
 */
@FunctionalInterface
public interface Action {
    
    //Methods
    
    /**
     * Performs the action.
     *
     * @throws Throwable When there is an error.
     */
    void perform() throws Throwable;
    
    /**
     * Performs the action and ignores errors.
     *
     * @see #perform()
     */
    default void performQuietly() {
        try {
            perform();
        } catch (Throwable ignored) {
        }
    }
    
    /**
     * Performs the action and throws a runtime exception in the event of an error.
     *
     * @throws RuntimeException When there is an error.
     * @see #perform()
     */
    default void performOrFail() {
        try {
            perform();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    
    //Static Methods
    
    /**
     * Invokes an Action.
     *
     * @param action The Action.
     * @throws Throwable When there is an error.
     * @see #perform()
     */
    static void invoke(Action action) throws Throwable {
        action.perform();
    }
    
    /**
     * Invokes an Action and ignores errors.
     *
     * @param action The Action.
     * @see #performQuietly()
     */
    static void invokeQuietly(Action action) {
        action.performQuietly();
    }
    
    /**
     * Invokes an Action and throws a runtime exception in the event of an error.
     *
     * @param action The Action.
     * @throws RuntimeException When there is an error.
     * @see #performOrFail()
     */
    static void invokeOrFail(Action action) {
        action.performOrFail();
    }
    
}
