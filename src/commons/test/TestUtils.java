/*
 * File:    TestUtils.java
 * Package: commons.test
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.test;

import org.junit.Assert;

/**
 * A resource class that provides test utilities.
 */
public final class TestUtils {
    
    //Constants
    
    /**
     * The delta to use when comparing the equality of doubles in unit tests.
     */
    public static final double DELTA = 0.000000001;
    
    
    //Functions
    
    /**
     * Asserts that a call throws an exception.
     *
     * @param exception The expected exception class.
     * @param call      The call.
     */
    public static void assertException(Class<? extends Exception> exception, Runnable call) {
        try {
            call.run();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals(exception, e.getClass());
        }
    }
    
    /**
     * Asserts that a call does not throws an exception.
     *
     * @param call The call.
     */
    public static void assertNoException(Runnable call) {
        try {
            call.run();
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
}
