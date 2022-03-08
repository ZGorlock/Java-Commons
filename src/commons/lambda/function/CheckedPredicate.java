/*
 * File:    CheckedPredicate.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

import java.util.function.Predicate;

/**
 * A lambda function that accepts an input and tries to evaluate the predicate and ignores errors.
 *
 * @param <T> The type of the input.
 */
@FunctionalInterface
public interface CheckedPredicate<T> extends Predicate<T> {
    
    //Methods
    
    /**
     * Tries to evaluate the predicate for an input.
     *
     * @param arg The input.
     * @return Whether the input matches the predicate or not.
     * @throws Throwable When there is an error.
     */
    boolean tryTest(T arg) throws Throwable;
    
    /**
     * Tries to evaluate the predicate for an input and ignores errors.
     *
     * @param arg The input.
     * @return Whether the input matches the predicate or not, or false if there was an error.
     * @see Predicate#test(Object)
     * @see #tryTest(Object)
     */
    default boolean test(T arg) {
        try {
            return tryTest(arg);
        } catch (Throwable ignored) {
            return false;
        }
    }
    
}
