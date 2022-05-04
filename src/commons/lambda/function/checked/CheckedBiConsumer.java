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
 * @see BiConsumer
 */
@FunctionalInterface
public interface CheckedBiConsumer<T, U> extends BiConsumer<T, U> {
    
    //Methods
    
    /**
     * Tries to consume the inputs.
     *
     * @param in1 The first input.
     * @param in2 The second input.
     * @throws Throwable When there is an error.
     */
    void tryAccept(T in1, U in2) throws Throwable;
    
    /**
     * Tries to consume the inputs and ignores errors.
     *
     * @param in1 The first input.
     * @param in2 The second input.
     * @see BiConsumer#accept(Object, Object)
     * @see #tryAccept(Object, Object)
     */
    @Override
    default void accept(T in1, U in2) {
        try {
            tryAccept(in1, in2);
        } catch (Throwable ignored) {
        }
    }
    
    
    //Static Methods
    
    /**
     * Invokes a CheckedBiConsumer.
     *
     * @param checkedBiConsumer The CheckedBiConsumer.
     * @param in1               The first input.
     * @param in2               The second input.
     * @param <T>               The type of the first input.
     * @param <U>               The type of the second input.
     * @see #accept(Object, Object)
     */
    static <T, U> void invoke(CheckedBiConsumer<T, U> checkedBiConsumer, T in1, U in2) {
        checkedBiConsumer.accept(in1, in2);
    }
    
}
