/*
 * File:    CheckedCallable.java
 * Package: commons.lambda.function.checked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.checked;

import java.util.concurrent.Callable;

/**
 * Represents a lambda function that run a task and returns a result and ignores errors.
 *
 * @param <T> The type of the result.
 * @see Callable
 */
@FunctionalInterface
public interface CheckedCallable<T> extends Callable<T> {
    
    //Methods
    
    /**
     * Tries to run the task.
     *
     * @return The result.
     * @throws Exception When there is an exception.
     */
    T tryCall() throws Exception;
    
    /**
     * Tries to run the task and ignores errors.
     *
     * @return The result, or null if there was an error.
     * @throws Exception This will not throw an exception.
     * @see Callable#call()
     * @see #tryCall()
     */
    @Override
    default T call() throws Exception {
        try {
            return tryCall();
        } catch (Throwable ignored) {
            return null;
        }
    }
    
    /**
     * Invokes a CheckedCallable.
     *
     * @param checkedCallable The CheckedCallable.
     * @param <T>             The type of the result.
     * @return The result, or null if there was an error.
     * @see #call()
     */
    static <T> T invoke(CheckedCallable<T> checkedCallable) {
        try {
            return checkedCallable.call();
        } catch (Throwable ignored) {
            return null;
        }
    }
    
}
