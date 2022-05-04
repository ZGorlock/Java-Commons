/*
 * File:    UncheckedBiConsumer.java
 * Package: commons.lambda.function.unchecked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.unchecked;

import java.util.function.BiConsumer;

/**
 * A lambda function that tries to consume two inputs and returns no result and throws a runtime exception in the event of an error.
 *
 * @param <T> The type of the first input.
 * @param <U> The type of the second input.
 * @see BiConsumer
 */
@FunctionalInterface
public interface UncheckedBiConsumer<T, U> extends BiConsumer<T, U> {
    
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
     * Tries to consume the inputs and throws a runtime exception in the event of an error.
     *
     * @param in1 The first input.
     * @param in2 The second input.
     * @throws RuntimeException When there is an error.
     * @see BiConsumer#accept(Object, Object)
     * @see #tryAccept(Object, Object)
     */
    @Override
    default void accept(T in1, U in2) {
        try {
            tryAccept(in1, in2);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    
    //Static Methods
    
    /**
     * Invokes an UncheckedBiConsumer.
     *
     * @param uncheckedBiConsumer The UncheckedBiConsumer.
     * @param in1                 The first input.
     * @param in2                 The second input.
     * @param <T>                 The type of the first input.
     * @param <U>                 The type of the second input.
     * @throws RuntimeException When there is an error.
     * @see #accept(Object, Object)
     */
    static <T, U> void invoke(UncheckedBiConsumer<T, U> uncheckedBiConsumer, T in1, U in2) {
        uncheckedBiConsumer.accept(in1, in2);
    }
    
}
