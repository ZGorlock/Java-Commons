/*
 * File:    UncheckedPredicate.java
 * Package: commons.lambda.function.unchecked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.unchecked;

import java.util.function.Predicate;

/**
 * A lambda function that accepts an argument and tries to evaluate the predicate and throws a runtime exception in the event of an error.
 *
 * @param <T> The type of the argument.
 * @see Predicate
 */
@FunctionalInterface
public interface UncheckedPredicate<T> extends Predicate<T> {
    
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
     * Tries to evaluate the predicate for an argument and throws a runtime exception in the event of an error.
     *
     * @param arg The argument.
     * @return Whether the argument matches the predicate or not.
     * @throws RuntimeException When there is an error.
     * @see Predicate#test(Object)
     * @see #tryTest(Object)
     */
    @Override
    default boolean test(T arg) {
        try {
            return tryTest(arg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Invokes an UncheckedPredicate.
     *
     * @param uncheckedPredicate The UncheckedPredicate.
     * @param arg                The argument.
     * @param <T>                The type of the argument.
     * @return Whether the argument matches the predicate or not.
     * @throws RuntimeException When there is an error.
     * @see #test(Object)
     */
    static <T> boolean invoke(UncheckedPredicate<T> uncheckedPredicate, T arg) {
        return uncheckedPredicate.test(arg);
    }
    
}
