/*
 * File:    UncheckedUnaryOperator.java
 * Package: commons.lambda.function.unchecked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.unchecked;

import java.util.function.UnaryOperator;

/**
 * A lambda function that accepts an operand and tries to produce a result of the same type and throws a runtime exception in the event of an error.
 *
 * @param <T> The type of the operand and the result.
 * @see UnaryOperator
 */
@FunctionalInterface
public interface UncheckedUnaryOperator<T> extends UnaryOperator<T> {
    
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
     * Tries to produce a result from an operand and throws a runtime exception in the event of an error.
     *
     * @param op The operand.
     * @return The result.
     * @throws RuntimeException When there is an error.
     * @see UnaryOperator#apply(Object)
     * @see #tryApply(Object)
     */
    @Override
    default T apply(T op) {
        try {
            return tryApply(op);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    
    //Static Methods
    
    /**
     * Invokes an UncheckedUnaryOperator.
     *
     * @param uncheckedUnaryOperator The UncheckedUnaryOperator.
     * @param op                     The operand.
     * @param <T>                    The type of the operand and the result.
     * @return The result.
     * @throws RuntimeException When there is an error.
     * @see #apply(Object)
     */
    static <T> T invoke(UncheckedUnaryOperator<T> uncheckedUnaryOperator, T op) {
        return uncheckedUnaryOperator.apply(op);
    }
    
}
