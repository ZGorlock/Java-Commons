/*
 * File:    CheckedPredicate.java
 * Package: commons.lambda.function.checked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.checked;

import java.util.function.Predicate;

/**
 * A lambda function that accepts an argument and tries to evaluate the predicate and ignores errors.
 *
 * @param <T> The type of the argument.
 * @see Predicate
 */
@FunctionalInterface
public interface CheckedPredicate<T> extends Predicate<T> {
    
    //Methods
    
    /**
     * Tries to evaluate the predicate for an argument.
     *
     * @param arg The argument.
     * @return Whether the argument matches the predicate or not.
     * @throws Throwable When there is an error.
     */
    boolean tryTest(T arg) throws Throwable;
    
    /**
     * Tries to evaluate the predicate for an argument and ignores errors.
     *
     * @param arg The argument.
     * @return Whether the argument matches the predicate or not, or false if there was an error.
     * @see Predicate#test(Object)
     * @see #tryTest(Object)
     */
    @Override
    default boolean test(T arg) {
        try {
            return tryTest(arg);
        } catch (Throwable ignored) {
            return false;
        }
    }
    
    
    //Static Methods
    
    /**
     * Invokes a CheckedPredicate.
     *
     * @param checkedPredicate The CheckedPredicate.
     * @param arg              The argument.
     * @param <T>              The type of the argument.
     * @return Whether the argument matches the predicate or not, or false if there was an error.
     * @see #test(Object)
     */
    static <T> boolean invoke(CheckedPredicate<T> checkedPredicate, T arg) {
        return checkedPredicate.test(arg);
    }
    
}
