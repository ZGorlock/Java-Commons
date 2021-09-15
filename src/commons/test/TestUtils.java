/*
 * File:    TestUtils.java
 * Package: commons.test
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

import commons.string.StringUtility;
import org.junit.Assert;
import org.powermock.reflect.Whitebox;
import org.powermock.reflect.exceptions.MethodInvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides test utilities.
 */
public final class TestUtils {
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);
    
    
    //Constants
    
    /**
     * The delta to use when comparing the equality in unit tests.
     */
    public static final double DELTA = 1E-9;
    
    /**
     * The delta to use when comparing the equality of floats in unit tests.
     */
    public static final float DELTA_FLOAT = 1E-3f;
    
    /**
     * The delta to use when comparing the equality of doubles in unit tests.
     */
    public static final double DELTA_DOUBLE = 1E-12;
    
    /**
     * The delta to use when comparing the equality of Big Decimals in unit tests.
     */
    public static final BigDecimal DELTA_BIG = BigDecimal.valueOf(1E-36);
    
    
    //Functions
    
    /**
     * Asserts that a call throws an exception with a particular message.
     *
     * @param exception       The expected exception class.
     * @param expectedMessage The expected message.
     * @param call            The call.
     */
    public static void assertException(Class<? extends Exception> exception, String expectedMessage, Runnable call) {
        try {
            call.run();
            AssertWrapper.fail("Expected code to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                    " but no exception was produced");
            
        } catch (Exception e) {
            AssertWrapper.assertEquals("Expected code to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                            " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()),
                    exception, e.getClass());
            
            if (expectedMessage != null) {
                AssertWrapper.assertEquals("Expected the error message of the " + exception.getSimpleName() + " to be: \"" + expectedMessage + '\"' +
                                " but the error message was: \"" + e.getMessage() + '\"',
                        expectedMessage, e.getMessage());
            }
        }
    }
    
    /**
     * Asserts that a call throws an exception.
     *
     * @param exception The expected exception class.
     * @param call      The call.
     * @see #assertException(Class, String, Runnable)
     */
    public static void assertException(Class<? extends Exception> exception, Runnable call) {
        assertException(exception, null, call);
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
     * @param offset    The offset to read from in the input stream.
     * @param length    The number of bytes of read.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void assertInputStreamReadThrowsException(Class<? extends Exception> exception, InputStream in, byte[] buffer, int offset, int length) {
        try {
            in.read(buffer, offset, length);
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
     * @param offset The offset to read from in the input stream.
     * @param length The number of bytes of read.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void assertInputStreamReadDoesNotThrowException(InputStream in, byte[] buffer, int offset, int length) {
        try {
            in.read(buffer, offset, length);
            
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
     * @param offset    The offset to write to in the output stream.
     * @param length    The number of bytes of write.
     */
    public static void assertOutputStreamWriteThrowsException(Class<? extends Exception> exception, OutputStream out, byte[] buffer, int offset, int length) {
        try {
            out.write(buffer, offset, length);
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
     * @param offset The offset to write to in the output stream.
     * @param length The number of bytes of write.
     */
    public static void assertOutputStreamWriteDoesNotThrowException(OutputStream output, byte[] buffer, int offset, int length) {
        try {
            output.write(buffer, offset, length);
            
        } catch (Exception e) {
            AssertWrapper.fail("Expected output stream write operation to produce no Exception" +
                    " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()));
        }
    }
    
    /**
     * Asserts that a method for a class exists.
     *
     * @param clazz           The class.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     */
    public static void assertMethodExists(Class<?> clazz, String methodName, Class<?>... argumentClasses) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, argumentClasses);
            
        } catch (Exception e) {
            AssertWrapper.fail("Expected method " + StringUtility.methodString(clazz, methodName, argumentClasses) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that a method for a class does not exist.
     *
     * @param clazz           The class.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     */
    public static void assertMethodDoesNotExist(Class<?> clazz, String methodName, Class<?>... argumentClasses) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, argumentClasses);
            AssertWrapper.fail("Expected method " + StringUtility.methodString(clazz, methodName, argumentClasses) + " to not exist" +
                    " but it does");
            
        } catch (Exception ignored) {
        }
    }
    
    /**
     * Gets the value of a field from an object or class.
     *
     * @param clazz     The class.
     * @param object    The object.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the object or class, or null if it cannot be retrieved.
     */
    @SuppressWarnings("deprecation")
    private static Object getField(Class<?> clazz, Object object, String fieldName) {
        if (clazz.getSimpleName().contains("MockitoMock")) {
            return Whitebox.getInternalState(((object == null) ? clazz : object), fieldName);
        }
        
        try {
            Field field = clazz.getDeclaredField(fieldName);
            boolean isAccessible = field.isAccessible();
            field.setAccessible(true);
            Object value = field.get(object);
            field.setAccessible(isAccessible);
            return value;
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Class<?> superClazz = clazz.getSuperclass();
            if (superClazz != null) {
                return getField(superClazz, object, fieldName);
            }
            
            logger.warn("Failed to get field: " + fieldName + " for class: " + clazz.getSimpleName());
            return null;
        }
    }
    
    /**
     * Gets the value of a field from a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the class, or null if it cannot be retrieved.
     * @see #getField(Class, Object, String)
     */
    public static Object getField(Class<?> clazz, String fieldName) {
        return getField(clazz, null, fieldName);
    }
    
    /**
     * Gets the value of a field from an object.
     *
     * @param object    The object.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the object, or null if it cannot be retrieved.
     * @see #getField(Class, Object, String)
     */
    public static Object getField(Object object, String fieldName) {
        return getField(object.getClass(), object, fieldName);
    }
    
    /**
     * Sets a field of an object or class.
     *
     * @param clazz     The class.
     * @param object    The object.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field of the object or class was successfully set or not.
     */
    @SuppressWarnings("deprecation")
    private static boolean setField(Class<?> clazz, Object object, String fieldName, Object value) {
        if (clazz.getSimpleName().contains("MockitoMock")) {
            Whitebox.setInternalState(((object == null) ? clazz : object), fieldName, value);
            return Objects.equals(getField(clazz, object, fieldName), value);
        }
        
        try {
            Field field = clazz.getDeclaredField(fieldName);
            boolean isAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
            field.setAccessible(isAccessible);
            return true;
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Class<?> superClazz = clazz.getSuperclass();
            if (superClazz != null) {
                return setField(superClazz, object, fieldName, value);
            }
            
            logger.warn("Failed to set field: " + fieldName + " for class: " + clazz.getSimpleName());
            return false;
        }
    }
    
    /**
     * Sets a field of a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field of the class was successfully set or not.
     * @see #setField(Class, Object, String, Object)
     */
    public static boolean setField(Class<?> clazz, String fieldName, Object value) {
        return setField(clazz, null, fieldName, value);
    }
    
    /**
     * Sets a field of an object.
     *
     * @param object    The object.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field of the object was successfully set or not.
     * @see #setField(Class, Object, String, Object)
     */
    public static boolean setField(Object object, String fieldName, Object value) {
        return setField(object.getClass(), object, fieldName, value);
    }
    
    /**
     * Gets an enum from a class.
     *
     * @param clazz    The class.
     * @param enumName The name of the enum.
     * @return The enum from the class, or null if it cannot be retrieved.
     */
    public static Class<?> getEnum(Class<?> clazz, String enumName) {
        String enumPath = clazz.getName() + '$' + enumName;
        try {
            return Class.forName(enumPath);
        } catch (Exception e) {
            logger.warn("Failed to get enum: " + enumName + " for class: " + clazz.getSimpleName());
            return null;
        }
    }
    
    /**
     * Invokes a method of an object.
     *
     * @param object     The object.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the method invocation.
     * @throws MethodInvocationException When there is an exception while invoking the method.
     */
    public static Object invokeMethod(Object object, String methodName, Object... arguments) throws MethodInvocationException {
        try {
            return Whitebox.invokeMethod(object, methodName, arguments);
        } catch (Exception e) {
            AssertWrapper.fail("Attempted to invoke the method " + StringUtility.methodString(object.getClass(), methodName, Arrays.stream(arguments).map(Object::getClass).toArray(Class<?>[]::new)) +
                    " but an exception occurred");
            throw new MethodInvocationException(e);
        }
    }
    
    /**
     * Invokes a method of a class.
     *
     * @param clazz      The class.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the method invocation.
     * @throws MethodInvocationException When there is an exception while invoking the method.
     */
    public static Object invokeMethod(Class<?> clazz, String methodName, Object... arguments) throws MethodInvocationException {
        try {
            return Whitebox.invokeMethod(clazz, methodName, arguments);
        } catch (Exception e) {
            AssertWrapper.fail("Attempted to invoke the method " + StringUtility.methodString(clazz, methodName, Arrays.stream(arguments).map(Object::getClass).toArray(Class<?>[]::new)) +
                    " but an exception occurred");
            throw new MethodInvocationException(e);
        }
    }
    
    /**
     * Invokes a default method of an interface and returns the result.
     *
     * @param clazz      The class.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the invocation.
     */
    @SuppressWarnings("SuspiciousInvocationHandlerImplementation")
    public static Object invokeInterfaceDefaultMethod(Class<?> clazz, String methodName, Object... arguments) {
        try {
            final Object target = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(), new Class[] {clazz},
                    (Object proxy, Method method, Object[] args) -> null);
            final Method method = clazz.getMethod(methodName);
            final Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            field.setAccessible(true);
            final MethodHandles.Lookup lookup = (MethodHandles.Lookup) field.get(null);
            return lookup.unreflectSpecial(method, method.getDeclaringClass())
                    .bindTo(target).invokeWithArguments(arguments);
            
        } catch (Throwable ignored) {
            AssertWrapper.fail("Attempted to invoke the method " + StringUtility.methodString(clazz, methodName, Arrays.stream(arguments).map(Object::getClass).toArray(Class<?>[]::new)) +
                    " but an exception occurred");
            return null;
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
