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
     * @see #perform()
     */
    static void invokeQuietly(Action action) {
        try {
            action.perform();
        } catch (Throwable ignored) {
        }
    }
    
}
