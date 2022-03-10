/*
 * File:    Action.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

/**
 * Represents a lambda function that run a task and returns no value and may throw an error.
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
    
}
