/*
 * File:    CheckedBinaryOperator.java
 * Package: commons.lambda.function.checked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.checked;

import java.util.function.BinaryOperator;

/**
 * A lambda function that accepts two operands and tries to produce a result of the same type and ignores errors.
 *
 * @param <T> The type of the operands and the result.
 */
@FunctionalInterface
public interface CheckedBinaryOperator<T> extends BinaryOperator<T> {
    
    //Methods
    
    /**
     * Tries to produce a result from the operands.
     *
     * @param op1 The first operand.
     * @param op2 The second operand.
     * @return The result.
     * @throws Throwable When there is an error.
     */
    T tryApply(T op1, T op2) throws Throwable;
    
    /**
     * Tries to produce a result from the operands and ignores errors.
     *
     * @param op1 The first operand.
     * @param op2 The second operand.
     * @return The result, or null if there was an error.
     * @see BinaryOperator#apply(Object, Object)
     * @see #tryApply(Object, Object)
     */
    default T apply(T op1, T op2) {
        try {
            return tryApply(op1, op2);
        } catch (Throwable ignored) {
            return null;
        }
    }
    
}
