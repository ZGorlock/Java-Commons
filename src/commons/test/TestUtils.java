/*
 * File:    TestUtils.java
 * Package: commons.test
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import commons.string.StringUtility;
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
            AssertWrapper.fail("Expected code to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                    " but no exception was produced");
        } catch (Exception e) {
            AssertWrapper.assertEquals("Expected code to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                            " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()),
                    exception, e.getClass());
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
            AssertWrapper.fail("Expected code to produce no Exception" +
                    " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()));
        }
    }
    
    /**
     * Asserts that a read from an input stream throws an exception.
     *
     * @param exception The expected exception class.
     * @param in        The input stream.
     * @param buffer    The buffer to store the read bytes in.
     * @param off       The offset to read from in the input stream.
     * @param len       The number of bytes of read.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void assertInputStreamReadThrowsException(Class<? extends Exception> exception, InputStream in, byte[] buffer, int off, int len) {
        try {
            in.read(buffer, off, len);
            AssertWrapper.fail("Expected input stream read operation to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                    " but no exception was produced");
        } catch (Exception e) {
            AssertWrapper.assertEquals("Expected input stream read operation to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                            " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()),
                    exception, e.getClass());
        }
    }
    
    /**
     * Asserts that a read from an input stream does not throw an exception.
     *
     * @param in     The input stream.
     * @param buffer The buffer to store the read bytes in.
     * @param off    The offset to read from in the input stream.
     * @param len    The number of bytes of read.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void assertInputStreamReadDoesNotThrowException(InputStream in, byte[] buffer, int off, int len) {
        try {
            in.read(buffer, off, len);
        } catch (Exception e) {
            AssertWrapper.fail("Expected input stream read operation to produce no Exception" +
                    " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()));
        }
    }
    
    /**
     * Asserts that a write to an output stream throws an exception.
     *
     * @param exception The expected exception class.
     * @param out       The output stream.
     * @param buffer    The buffer storing the bytes to write.
     * @param off       The offset to write to in the output stream.
     * @param len       The number of bytes of write.
     */
    public static void assertOutputStreamWriteThrowsException(Class<? extends Exception> exception, OutputStream out, byte[] buffer, int off, int len) {
        try {
            out.write(buffer, off, len);
            AssertWrapper.fail("Expected output stream write operation to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                    " but no exception was produced");
        } catch (Exception e) {
            AssertWrapper.assertEquals("Expected output stream write operation to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                            " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()),
                    exception, e.getClass());
        }
    }
    
    /**
     * Asserts that a write to an output stream does not throw an exception.
     *
     * @param output The output stream.
     * @param buffer The buffer store the bytes to write.
     * @param off    The offset to write to in the output stream.
     * @param len    The number of bytes of write.
     */
    public static void assertOutputStreamWriteDoesNotThrowException(OutputStream output, byte[] buffer, int off, int len) {
        try {
            output.write(buffer, off, len);
        } catch (Exception e) {
            AssertWrapper.fail("Expected output stream write operation to produce no Exception" +
                    " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()));
        }
    }
    
    /**
     * Asserts that a method for a class exists.
     *
     * @param clazz      The class.
     * @param methodName The name of the method.
     * @param argClasses The classes of the arguments to the method.
     */
    public static void assertMethodExists(Class<?> clazz, String methodName, Class<?>... argClasses) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, argClasses);
        } catch (Exception e) {
            AssertWrapper.fail("Expected method " + StringUtility.methodString(clazz, methodName, argClasses) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that a method for a class does not exist.
     *
     * @param clazz      The class.
     * @param methodName The name of the method.
     * @param argClasses The classes of the arguments to the method.
     */
    public static void assertMethodDoesNotExist(Class<?> clazz, String methodName, Class<?>... argClasses) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, argClasses);
            AssertWrapper.fail("Expected method " + StringUtility.methodString(clazz, methodName, argClasses) + " to not exist" +
                    " but it does");
        } catch (Exception ignored) {
        }
    }
    
    
    //Inner Classes
    
    /**
     * Wraps the Assert calls from TestUtils.
     */
    public static final class AssertWrapper {
        
        //Methods
        
        /**
         * Calls Assert.fail().
         *
         * @param message The message.
         */
        public static void fail(String message) {
            Assert.fail(message);
        }
        
        /**
         * Calls Assert.assertEquals().
         *
         * @param message  The message on a failure.
         * @param expected The expected object.
         * @param actual   The actual object.
         */
        public static void assertEquals(String message, Object expected, Object actual) {
            Assert.assertEquals(message, expected, actual);
        }
        
    }
    
}
