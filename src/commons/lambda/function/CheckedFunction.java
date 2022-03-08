/*
 * File:    CheckedFunction.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

import java.util.function.Function;

/**
 * A lambda function that accepts an input and tries to produce a result and ignored errors.
 *
 * @param <T> The type of the input.
 * @param <R> The type of the result.
 */
@FunctionalInterface
public interface CheckedFunction<T, R> extends Function<T, R> {
    
    //Methods
    
    /**
     * Tries to produce a result from an input.
     *
     * @param arg The input.
     * @return The result.
     * @throws Throwable When there is an error.
     */
    R tryApply(T arg) throws Throwable;
    
    /**
     * Tries to produce a result from an input and ignores errors.
     *
     * @param arg The input.
     * @return The result, or null if there was an error.
     * @see Function#apply(Object)
     * @see #tryApply(Object)
     */
    default R apply(T arg) {
        try {
            return tryApply(arg);
        } catch (Throwable ignored) {
            return null;
        }
    }
    
}
