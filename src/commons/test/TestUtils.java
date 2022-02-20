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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

import commons.object.string.StringUtility;
import org.junit.Assert;
import org.powermock.reflect.Whitebox;
import org.powermock.reflect.exceptions.ConstructorNotFoundException;
import org.powermock.reflect.exceptions.MethodNotFoundException;
import org.powermock.reflect.internal.primitivesupport.PrimitiveWrapper;
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
    
    
    //Static Methods
    
    /**
     * Asserts that a call throws an exception with a particular message.
     *
     * @param throwable       The expected throwable class.
     * @param expectedMessage The expected message.
     * @param call            The call.
     */
    public static void assertException(Class<? extends Throwable> throwable, String expectedMessage, Runnable call) {
        try {
            call.run();
            
        } catch (Throwable e) {
            if (throwable != null) {
                AssertWrapper.assertEquals("Expected code to produce " + StringUtility.justifyAOrAn(throwable.getSimpleName()) +
                                " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()),
                        throwable, e.getClass());
            }
            if (expectedMessage != null) {
                AssertWrapper.assertEquals("Expected the error message of the " + ((throwable != null) ? throwable.getSimpleName() : "exception") +
                                " to be: " + StringUtility.quote(expectedMessage) +
                                " but the error message was: " + StringUtility.quote(e.getMessage()),
                        expectedMessage, e.getMessage());
            }
            return;
        }
        
        AssertWrapper.fail("Expected code to produce " + ((throwable != null) ? StringUtility.justifyAOrAn(throwable.getSimpleName()) : "an exception") +
                " but no exception was produced");
    }
    
    /**
     * Asserts that a call throws an exception.
     *
     * @param throwable The expected throwable class.
     * @param call      The call.
     * @see #assertException(Class, String, Runnable)
     */
    public static void assertException(Class<? extends Throwable> throwable, Runnable call) {
        assertException(throwable, null, call);
    }
    
    /**
     * Asserts that a call throws an exception.
     *
     * @param call The call.
     * @see #assertException(Class, String, Runnable)
     */
    public static void assertException(Runnable call) {
        assertException(null, null, call);
    }
    
    /**
     * Asserts that a call does not throw an exception.
     *
     * @param call The call.
     */
    public static void assertNoException(Runnable call) {
        try {
            call.run();
            
        } catch (Throwable e) {
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
            
        } catch (Throwable e) {
            AssertWrapper.assertEquals("Expected input stream read operation to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                            " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()),
                    exception, e.getClass());
            return;
        }
        
        AssertWrapper.fail("Expected input stream read operation to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                " but no exception was produced");
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
            
        } catch (Throwable e) {
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
            
        } catch (Throwable e) {
            AssertWrapper.assertEquals("Expected output stream write operation to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                            " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()),
                    exception, e.getClass());
            return;
        }
        
        AssertWrapper.fail("Expected output stream write operation to produce " + StringUtility.justifyAOrAn(exception.getSimpleName()) +
                " but no exception was produced");
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
            
        } catch (Throwable e) {
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
            
        } catch (Throwable e) {
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
            
        } catch (Throwable e) {
            return;
        }
        
        AssertWrapper.fail("Expected method " + StringUtility.methodString(clazz, methodName, argumentClasses) + " to not exist" +
                " but it does");
    }
    
    /**
     * Gets the value of a field from an object or class.
     *
     * @param clazz     The class.
     * @param object    The object.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the object or class.
     * @throws AssertionError When there is an exception while getting the field.
     */
    @SuppressWarnings("deprecation")
    private static Object getField(Class<?> clazz, Object object, String fieldName) throws AssertionError {
        try {
            if (clazz.getSimpleName().contains("MockitoMock")) {
                return Whitebox.getInternalState(((object == null) ? clazz : object), Objects.requireNonNull(fieldName));
            }
            
            try {
                Field field = clazz.getDeclaredField(Objects.requireNonNull(fieldName));
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                Object value = field.get(object);
                field.setAccessible(isAccessible);
                return value;
                
            } catch (NoSuchFieldException retry) {
                Class<?> superClazz = clazz.getSuperclass();
                if (superClazz != null) {
                    return getField(superClazz, object, Objects.requireNonNull(fieldName));
                } else {
                    throw retry;
                }
            }
            
        } catch (Exception e) {
            AssertWrapper.fail("Attempted to get the field " + StringUtility.fieldString(clazz, fieldName) +
                    " but an exception occurred");
            throw new AssertionError(e);
        }
    }
    
    /**
     * Gets the value of a field from a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the class.
     * @throws AssertionError When there is an exception while getting the field.
     * @see #getField(Class, Object, String)
     */
    public static Object getField(Class<?> clazz, String fieldName) throws AssertionError {
        return getField(clazz, null, fieldName);
    }
    
    /**
     * Gets the value of a field from an object.
     *
     * @param object    The object.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the object.
     * @throws AssertionError When there is an exception while getting the field.
     * @see #getField(Class, Object, String)
     */
    public static Object getField(Object object, String fieldName) throws AssertionError {
        return getField(((object != null) ? object.getClass() : null), object, fieldName);
    }
    
    /**
     * Sets the value of a field of an object or class.
     *
     * @param clazz     The class.
     * @param object    The object.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field of the object or class was successfully set or not.
     * @throws AssertionError When there is an exception while setting the field.
     */
    @SuppressWarnings("deprecation")
    private static boolean setField(Class<?> clazz, Object object, String fieldName, Object value) throws AssertionError {
        try {
            if (clazz.getSimpleName().contains("MockitoMock")) {
                Whitebox.setInternalState(((object == null) ? clazz : object), fieldName, value);
                return Objects.equals(getField(clazz, object, fieldName), value);
            }
            
            try {
                Field field = clazz.getDeclaredField(fieldName);
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                field.set(object, value);
                boolean success = Objects.equals(field.get(object), value);
                field.setAccessible(isAccessible);
                return success;
                
            } catch (NoSuchFieldException retry) {
                Class<?> superClazz = clazz.getSuperclass();
                if (superClazz != null) {
                    return setField(superClazz, object, fieldName, value);
                } else {
                    throw retry;
                }
            }
            
        } catch (Exception e) {
            AssertWrapper.fail("Attempted to set the field " + StringUtility.fieldString(clazz, fieldName) +
                    " but an exception occurred");
            throw new AssertionError(e);
        }
    }
    
    /**
     * Sets the value of a field of a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field of the class was successfully set or not.
     * @throws AssertionError When there is an exception while setting the field.
     * @see #setField(Class, Object, String, Object)
     */
    public static boolean setField(Class<?> clazz, String fieldName, Object value) throws AssertionError {
        return setField(clazz, null, fieldName, value);
    }
    
    /**
     * Sets the value of a field of an object.
     *
     * @param object    The object.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field of the object was successfully set or not.
     * @throws AssertionError When there is an exception while setting the field.
     * @see #setField(Class, Object, String, Object)
     */
    public static boolean setField(Object object, String fieldName, Object value) throws AssertionError {
        return setField(((object != null) ? object.getClass() : null), object, fieldName, value);
    }
    
    /**
     * Gets an enum from a class.
     *
     * @param clazz    The class.
     * @param enumName The name of the enum.
     * @return The enum from the class, or null if it cannot be retrieved.
     */
    public static Class<?> getEnum(Class<?> clazz, String enumName) {
        try {
            return Class.forName(clazz.getName() + '$' + enumName);
            
        } catch (Exception e) {
            AssertWrapper.fail("Attempted to get the enum " + StringUtility.fieldString(clazz, enumName) +
                    " but an exception occurred");
            throw new AssertionError(e);
        }
    }
    
    /**
     * Invokes a method of an object.
     *
     * @param object     The object.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the method invocation.
     * @throws AssertionError When there is an exception while invoking the method.
     */
    public static Object invokeMethod(Object object, String methodName, Object... arguments) throws AssertionError {
        Class<?> clazz = (object instanceof Class<?>) ? (Class<?>) object : object.getClass();
        
        try {
            try {
                return Whitebox.invokeMethod(((object instanceof Class<?>) ? clazz : object), Objects.requireNonNull(methodName), arguments);
                
            } catch (MethodNotFoundException | IllegalAccessException retry) {
                Class<?>[] argumentTypes = Arrays.stream(arguments).map(arg -> (arg == null) ? null : arg.getClass()).toArray(Class<?>[]::new);
                
                for (Method method : clazz.getDeclaredMethods()) {
                    Class<?>[] methodArgumentTypes = method.getParameterTypes();
                    if (!method.getName().equals(methodName) || (methodArgumentTypes.length != arguments.length)) {
                        continue;
                    }
                    
                    boolean hit = true;
                    for (int i = 0; i < argumentTypes.length; i++) {
                        if (((argumentTypes[i] != null) && (methodArgumentTypes[i] != argumentTypes[i]) &&
                                (methodArgumentTypes[i] != PrimitiveWrapper.getPrimitiveFromWrapperType(argumentTypes[i]))) ||
                                ((argumentTypes[i] == null) && methodArgumentTypes[i].isPrimitive()) ||
                                (methodArgumentTypes[i].getSimpleName().equals("IndicateReloadClass"))) {
                            hit = false;
                            break;
                        }
                    }
                    if (hit) {
                        argumentTypes = methodArgumentTypes;
                        break;
                    }
                }
                
                return Whitebox.invokeMethod(((object instanceof Class<?>) ? clazz : object), methodName, argumentTypes, arguments);
            }
            
        } catch (Throwable e) {
            AssertWrapper.fail("Attempted to invoke the method " + StringUtility.methodString(clazz, methodName,
                    Arrays.stream(arguments).map(arg -> (arg == null) ? null : arg.getClass()).toArray(Class<?>[]::new)) +
                    " but an exception occurred");
            throw new AssertionError(e);
        }
    }
    
    /**
     * Invokes a method of a class.
     *
     * @param clazz      The class.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the method invocation.
     * @throws AssertionError When there is an exception while invoking the method.
     * @see #invokeMethod(Object, String, Object...)
     */
    public static Object invokeMethod(Class<?> clazz, String methodName, Object... arguments) throws AssertionError {
        return invokeMethod((Object) clazz, methodName, arguments);
    }
    
    /**
     * Invokes a constructor of a class.
     *
     * @param clazz     The class.
     * @param arguments The arguments to the constructor.
     * @param <T>       The type of the class.
     * @return The constructed instance of the class, or null if there was there was an error.
     * @throws AssertionError When there is an exception while invoking the constructor.
     */
    public static <T> T invokeConstructor(Class<T> clazz, Object... arguments) throws AssertionError {
        try {
            try {
                return Whitebox.invokeConstructor(clazz, arguments);
                
            } catch (ConstructorNotFoundException | IllegalAccessException retry) {
                Class<?>[] argumentTypes = Arrays.stream(arguments).map(arg -> (arg == null) ? null : arg.getClass()).toArray(Class<?>[]::new);
                
                for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                    Class<?>[] constructorArgumentTypes = constructor.getParameterTypes();
                    if (constructorArgumentTypes.length != arguments.length) {
                        continue;
                    }
                    
                    boolean hit = true;
                    for (int i = 0; i < argumentTypes.length; i++) {
                        if (((argumentTypes[i] != null) && (constructorArgumentTypes[i] != argumentTypes[i]) &&
                                (constructorArgumentTypes[i] != PrimitiveWrapper.getPrimitiveFromWrapperType(argumentTypes[i]))) ||
                                ((argumentTypes[i] == null) && constructorArgumentTypes[i].isPrimitive()) ||
                                (constructorArgumentTypes[i].getSimpleName().equals("IndicateReloadClass"))) {
                            hit = false;
                            break;
                        }
                    }
                    if (hit) {
                        argumentTypes = constructorArgumentTypes;
                        break;
                    }
                }
                
                return Whitebox.invokeConstructor(clazz, argumentTypes, arguments);
            }
            
        } catch (Throwable e) {
            AssertWrapper.fail("Attempted to invoke the constructor " + StringUtility.methodString(clazz, ((clazz != null) ? clazz.getSimpleName() : null),
                    Arrays.stream(arguments).map(arg -> (arg == null) ? null : arg.getClass()).toArray(Class<?>[]::new)) +
                    " but an exception occurred");
            throw new AssertionError(e);
        }
    }
    
    /**
     * Invokes a default method of an interface and returns the result.
     *
     * @param clazz      The class.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the invocation.
     * @throws AssertionError When there is an exception while invoking the interface default method.
     */
    @SuppressWarnings("SuspiciousInvocationHandlerImplementation")
    public static Object invokeInterfaceDefaultMethod(Class<?> clazz, String methodName, Object... arguments) throws AssertionError {
        try {
            final Object target = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(), new Class[] {clazz},
                    (Object proxy, Method method, Object[] args) -> null);
            final Method method = clazz.getMethod(Objects.requireNonNull(methodName));
            final Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            field.setAccessible(true);
            final MethodHandles.Lookup lookup = (MethodHandles.Lookup) field.get(null);
            return lookup.unreflectSpecial(method, method.getDeclaringClass())
                    .bindTo(target).invokeWithArguments(arguments);
            
        } catch (Throwable e) {
            AssertWrapper.fail("Attempted to invoke the method " + StringUtility.methodString(clazz, methodName,
                    Arrays.stream(arguments).map(arg -> (arg == null) ? null : arg.getClass()).toArray(Class<?>[]::new)) +
                    " but an exception occurred");
            throw new AssertionError(e);
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
