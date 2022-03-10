/*
 * File:    CheckedConsumer.java
 * Package: commons.lambda.function.checked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.checked;

import java.util.function.Consumer;

/**
 * A lambda function that tries to consume an input and returns no result and ignores errors.
 *
 * @param <T> The type of the input.
 */
@FunctionalInterface
public interface CheckedConsumer<T> extends Consumer<T> {
    
    //Methods
    
    /**
     * Tries to consume an input.
     *
     * @param arg The input.
     * @throws Throwable When there is an error.
     */
    void tryAccept(T arg) throws Throwable;
    
    /**
     * Tries to consume an input and ignores errors.
     *
     * @param arg the input.
     * @see Consumer#accept(Object)
     * @see #tryAccept(Object)
     */
    default void accept(T arg) {
        try {
            tryAccept(arg);
        } catch (Throwable ignored) {
        }
    }
    
}
