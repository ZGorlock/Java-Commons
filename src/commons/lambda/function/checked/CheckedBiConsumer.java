/*
 * File:    CheckedBiConsumer.java
 * Package: commons.lambda.function.checked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.checked;

import java.util.function.BiConsumer;

/**
 * A lambda function that tries to consume two inputs and returns no result and ignores errors.
 *
 * @param <T> The type of the first input.
 * @param <U> The type of the second input.
 */
@FunctionalInterface
public interface CheckedBiConsumer<T, U> extends BiConsumer<T, U> {
    
    //Methods
    
    /**
     * Tries to consume the inputs.
     *
     * @param arg1 The first input.
     * @param arg2 The second input.
     * @throws Throwable When there is an error.
     */
    void tryAccept(T arg1, U arg2) throws Throwable;
    
    /**
     * Tries to consume the inputs and ignores errors.
     *
     * @param arg1 The first input.
     * @param arg2 The second input.
     * @see BiConsumer#accept(Object, Object)
     * @see #tryAccept(Object, Object)
     */
    default void accept(T arg1, U arg2) {
        try {
            tryAccept(arg1, arg2);
        } catch (Throwable ignored) {
        }
    }
    
}
