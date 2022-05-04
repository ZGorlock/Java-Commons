/*
 * File:    Conditional.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

/**
 * Represents a lambda function that evaluates a condition and returns a boolean and may throw an error.
 */
@FunctionalInterface
public interface Conditional {
    
    //Methods
    
    /**
     * Evaluates the condition.
     *
     * @return Whether the condition is true or not.
     * @throws Throwable When there is an error.
     */
    Boolean test() throws Throwable;
    
    /**
     * Evaluates the condition and ignores errors.
     *
     * @param defaultResult The default result.
     * @return Whether the condition is true or not, or the default result if there was an error.
     * @see #test()
     */
    default Boolean testOrDefault(Boolean defaultResult) {
        try {
            return test();
        } catch (Throwable ignored) {
            return defaultResult;
        }
    }
    
    /**
     * Evaluates the condition and ignores errors.
     *
     * @return Whether the condition is true or not, or null if there was an error.
     * @see #testOrDefault(Boolean)
     */
    default Boolean testQuietly() {
        return testOrDefault(null);
    }
    
    /**
     * Evaluates the condition and throws a runtime exception in the event of an error.
     *
     * @return Whether the condition is true or not.
     * @throws RuntimeException When there is an error.
     * @see #test()
     */
    default Boolean testOrFail() {
        try {
            return test();
        } catch (Throwable ignored) {
            throw new RuntimeException();
        }
    }
    
    
    //Static Methods
    
    /**
     * Invokes a Conditional.
     *
     * @param conditional The Conditional.
     * @return Whether the condition is true or not.
     * @throws Throwable When there is an error.
     * @see #test()
     */
    static Boolean invoke(Conditional conditional) throws Throwable {
        return conditional.test();
    }
    
    /**
     * Invokes a Conditional and ignores errors.
     *
     * @param conditional   The Conditional.
     * @param defaultResult The default result.
     * @return Whether the condition is true or not, or the default result if there was an error.
     * @see #testOrDefault(Boolean)
     */
    static Boolean invokeOrDefault(Conditional conditional, Boolean defaultResult) {
        return conditional.testOrDefault(defaultResult);
    }
    
    /**
     * Invokes a Conditional and ignores errors.
     *
     * @param conditional The Conditional.
     * @return Whether the condition is true or not, or null if there was an error.
     * @see #testQuietly()
     */
    static Boolean invokeQuietly(Conditional conditional) {
        return conditional.testQuietly();
    }
    
    /**
     * Invokes a Conditional and throws a runtime exception in the event of an error.
     *
     * @param conditional The Conditional.
     * @return Whether the condition is true or not.
     * @throws RuntimeException When there is an error.
     * @see #testOrFail()
     */
    static Boolean invokeOrFail(Conditional conditional) {
        return conditional.testOrFail();
    }
    
    /**
     * Creates a Conditional.
     *
     * @param result The result of the Conditional test.
     */
    static Conditional of(Boolean result) {
        return () -> result;
    }
    
}
