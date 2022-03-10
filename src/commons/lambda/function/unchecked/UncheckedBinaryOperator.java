/*
 * File:    UncheckedBinaryOperator.java
 * Package: commons.lambda.function.unchecked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.unchecked;

import java.util.function.BinaryOperator;

/**
 * A lambda function that accepts two operands and tries to produce a result of the same type and throws a runtime exception in the event of an error.
 *
 * @param <T> The type of the operands and the result.
 * @see BinaryOperator
 */
@FunctionalInterface
public interface UncheckedBinaryOperator<T> extends BinaryOperator<T> {
    
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
     * Tries to produce a result from the operands and throws a runtime exception in the event of an error.
     *
     * @param op1 The first operand.
     * @param op2 The second operand.
     * @return The result.
     * @throws RuntimeException When there is an error.
     * @see BinaryOperator#apply(Object, Object)
     * @see #tryApply(Object, Object)
     */
    @Override
    default T apply(T op1, T op2) {
        try {
            return tryApply(op1, op2);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Invokes an UncheckedBinaryOperator.
     *
     * @param uncheckedBinaryOperator The UncheckedBinaryOperator.
     * @param op1                     The first operand.
     * @param op2                     The second operand.
     * @param <T>                     The type of the operands and the result.
     * @return The result.
     * @throws RuntimeException When there is an error.
     * @see #apply(Object, Object)
     */
    static <T> T invoke(UncheckedBinaryOperator<T> uncheckedBinaryOperator, T op1, T op2) {
        return uncheckedBinaryOperator.apply(op1, op2);
    }
    
}
