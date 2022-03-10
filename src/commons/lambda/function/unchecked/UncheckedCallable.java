/*
 * File:    UncheckedCallable.java
 * Package: commons.lambda.function.unchecked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.unchecked;

import java.util.concurrent.Callable;

/**
 * Represents a lambda function that run a task and returns a result and throws a runtime exception in the event of an error.
 *
 * @param <T> The type of the result.
 * @see Callable
 */
@FunctionalInterface
public interface UncheckedCallable<T> extends Callable<T> {
    
    //Methods
    
    /**
     * Tries to run the task.
     *
     * @return The result.
     * @throws Exception When there is an exception.
     */
    T tryCall() throws Exception;
    
    /**
     * Tries to run the task and throws a runtime exception in the event of an error.
     *
     * @return The result.
     * @throws RuntimeException When there is an error.
     * @see Callable#call()
     * @see #tryCall()
     */
    @Override
    default T call() throws Exception {
        try {
            return tryCall();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Invokes an UncheckedCallable.
     *
     * @param uncheckedCallable The UncheckedCallable.
     * @param <T>               The type of the result.
     * @return The result.
     * @throws RuntimeException When there is an error.
     * @see #call()
     */
    static <T> T invoke(UncheckedCallable<T> uncheckedCallable) {
        try {
            return uncheckedCallable.call();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
}
