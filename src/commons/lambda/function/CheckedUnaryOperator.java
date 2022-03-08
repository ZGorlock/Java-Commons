/*
 * File:    CheckedUnaryOperator.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * A lambda function that accepts an operand and tries to produce a result of the same type and ignores errors.
 *
 * @param <T> The type of the input and the result.
 */
@FunctionalInterface
public interface CheckedUnaryOperator<T> extends UnaryOperator<T> {
    
    //Methods
    
    /**
     * Tries to produce a result from an operand.
     *
     * @param op The operand.
     * @return The result.
     * @throws Throwable When there is an error.
     */
    T tryApply(T op) throws Throwable;
    
    /**
     * Tries to produce a result from an operand and ignores errors.
     *
     * @param op The operand.
     * @return The result, or null if there was an error.
     * @see Function#apply(Object)
     * @see #tryApply(Object)
     */
    default T apply(T op) {
        try {
            return tryApply(op);
        } catch (Throwable ignored) {
            return null;
        }
    }
    
}
