/*
 * File:    CheckedBiFunction.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

import java.util.function.BiFunction;

/**
 * A lambda function that accepts two inputs and tries to produce a result and ignores errors.
 *
 * @param <T> The type of the first input.
 * @param <U> The type of the second input.
 * @param <R> The type of the result.
 */
@FunctionalInterface
public interface CheckedBiFunction<T, U, R> extends BiFunction<T, U, R> {
    
    //Methods
    
    /**
     * Tries to produce a result from two inputs.
     *
     * @param arg1 The first input.
     * @param arg2 The second input.
     * @return The result.
     * @throws Throwable When there is an error.
     */
    R tryApply(T arg1, U arg2) throws Throwable;
    
    /**
     * Tries to produce a result from two inputs and ignores errors.
     *
     * @param arg1 The first input.
     * @param arg2 The second input.
     * @return The result, or null if there was an error.
     * @see BiFunction#apply(Object, Object)
     * @see #tryApply(Object, Object)
     */
    default R apply(T arg1, U arg2) {
        try {
            return tryApply(arg1, arg2);
        } catch (Throwable ignored) {
            return null;
        }
    }
    
}
