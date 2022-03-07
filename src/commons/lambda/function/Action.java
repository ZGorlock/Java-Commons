/*
 * File:    Action.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

import java.util.concurrent.Callable;

/**
 * Represents an action that returns no value but may throw an exception.
 */
@FunctionalInterface
public interface Action extends Callable<Void> {
    
    //Methods
    
    /**
     * Performs the action.
     *
     * @throws Exception When there is an exception.
     */
    void perform() throws Exception;
    
    /**
     * Performs the action and returns null.
     *
     * @return Null.
     * @throws Exception When there is an exception.
     * @see Callable#call()
     * @see #perform()
     */
    default Void call() throws Exception {
        perform();
        return null;
    }
    
}
