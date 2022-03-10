/*
 * File:    CheckedUnaryOperator.java
 * Package: commons.lambda.function.checked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.checked;

import java.util.function.UnaryOperator;

/**
 * A lambda function that accepts an operand and tries to produce a result of the same type and ignores errors.
 *
 * @param <T> The type of the operand and the result.
 * @see UnaryOperator
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
     * @see UnaryOperator#apply(Object)
     * @see #tryApply(Object)
     */
    @Override
    default T apply(T op) {
        try {
            return tryApply(op);
        } catch (Throwable ignored) {
            return null;
        }
    }
    
    /**
     * Invokes a CheckedUnaryOperator.
     *
     * @param checkedUnaryOperator The CheckedUnaryOperator.
     * @param op                   The operand.
     * @param <T>                  The type of the operand and the result.
     * @return The result, or null if there was an error.
     * @see #apply(Object)
     */
    static <T> T invoke(CheckedUnaryOperator<T> checkedUnaryOperator, T op) {
        return checkedUnaryOperator.apply(op);
    }
    
}
