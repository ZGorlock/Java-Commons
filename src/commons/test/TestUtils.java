/*
 * File:    TestUtils.java
 * Package: commons.test
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Consumer;

import commons.lambda.function.Action;
import commons.object.string.EntityStringUtility;
import commons.object.string.StringUtility;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides additional test assertions.
 */
public final class TestUtils {
    
    //Logger
    
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
     * Checks if an action throws an exception or not.
     *
     * @param action        The action.
     * @param onException   The callback to perform in the event of an exception.
     * @param onNoException The callback to perform in the event of no exception.
     */
    private static void checkException(Action action, Consumer<Throwable> onException, Consumer<Void> onNoException) {
        try {
            action.perform();
            
        } catch (Throwable e) {
            Optional.ofNullable(onException).ifPresent(validateException -> onException.accept(e));
            return;
        }
        
        Optional.ofNullable(onNoException).ifPresent(validateNoException -> onNoException.accept(null));
    }
    
    /**
     * Asserts that an action throws an exception with a particular message.
     *
     * @param thrown          The expected exception.
     * @param expectedMessage The expected message.
     * @param action          The action.
     * @see #checkException(Action, Consumer, Consumer)
     */
    public static void assertException(Class<? extends Throwable> thrown, String expectedMessage, Action action) {
        final Consumer<Throwable> onException = (Throwable e) -> {
            if (thrown != null) {
                AssertWrapper.assertEquals(StringUtility.format("Expected code to produce {} but instead it produced {}", StringUtility.justifyAOrAn(EntityStringUtility.simpleClassString(thrown)), StringUtility.justifyAOrAn(EntityStringUtility.simpleClassString(e))),
                        thrown, e.getClass());
            }
            if (expectedMessage != null) {
                AssertWrapper.assertEquals(StringUtility.format("Expected the error message of the {} to be: {} but the error message was: {}", ((thrown != null) ? EntityStringUtility.simpleClassString(thrown) : "exception"), StringUtility.quote(expectedMessage), StringUtility.quote(e.getMessage())),
                        expectedMessage, e.getMessage());
            }
        };
        final Consumer<Void> onNoException = (Void e) ->
                AssertWrapper.fail(StringUtility.format("Expected code to produce {} but no exception was produced", ((thrown != null) ? StringUtility.justifyAOrAn(EntityStringUtility.simpleClassString(thrown)) : "an exception")));
        
        checkException(action, onException, onNoException);
    }
    
    /**
     * Asserts that an action throws an exception.
     *
     * @param thrown The expected exception.
     * @param action The action.
     * @see #assertException(Class, String, Action)
     */
    public static void assertException(Class<? extends Throwable> thrown, Action action) {
        assertException(thrown, null, action);
    }
    
    /**
     * Asserts that an action throws an exception.
     *
     * @param action The action.
     * @see #assertException(Class, Action)
     */
    public static void assertException(Action action) {
        assertException(null, action);
    }
    
    /**
     * Asserts that an action does not throw an exception.
     *
     * @param action The action.
     * @see #checkException(Action, Consumer, Consumer)
     */
    public static void assertNoException(Action action) {
        final Consumer<Throwable> onException = (Throwable e) ->
                AssertWrapper.fail(StringUtility.format("Expected code to produce no Exception but instead it produced {}", StringUtility.justifyAOrAn(EntityStringUtility.simpleClassString(e))));
        
        checkException(action, onException, null);
    }
    
    /**
     * Checks if an entity exists or not.
     *
     * @param expectedToExist Whether the entity is expected to exist or not.
     * @param entityType      The name of the entity type.
     * @param entityString    The string of the entity.
     * @param getEntity       The action that attempts to get the entity.
     */
    private static void checkExists(boolean expectedToExist, String entityType, String entityString, Action getEntity) {
        final Consumer<Throwable> onException = !expectedToExist ? null : (Throwable e) ->
                AssertWrapper.fail(StringUtility.format("Expected {} {} to exist but it does not", entityType, entityString));
        final Consumer<Void> onNoException = expectedToExist ? null : (Void e) ->
                AssertWrapper.fail(StringUtility.format("Expected {} {} to not exist but it does", entityType, entityString));
        
        checkException(getEntity, onException, onNoException);
    }
    
    /**
     * Asserts that a class exists.
     *
     * @param qualifiedClassName The qualified name of the class.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getClass(String)
     */
    public static void assertClassExists(String qualifiedClassName) {
        checkExists(true, "class", qualifiedClassName, () ->
                TestAccess.getClass(qualifiedClassName));
    }
    
    /**
     * Asserts that an inner class exists.
     *
     * @param clazz     The class.
     * @param className The name of the inner class.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getClass(Class, String)
     */
    public static void assertClassExists(Class<?> clazz, String className) {
        checkExists(true, "class", EntityStringUtility.simpleInnerClassString(clazz, className), () ->
                TestAccess.getClass(clazz, className));
    }
    
    /**
     * Asserts that a class does not exist.
     *
     * @param qualifiedClassName The qualified name of the class.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getClass(String)
     */
    public static void assertClassDoesNotExist(String qualifiedClassName) {
        checkExists(false, "class", qualifiedClassName, () ->
                TestAccess.getClass(qualifiedClassName));
    }
    
    /**
     * Asserts that an inner class does not exist.
     *
     * @param clazz     The class.
     * @param className The name of the inner class.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getClass(Class, String)
     */
    public static void assertClassDoesNotExist(Class<?> clazz, String className) {
        checkExists(false, "class", EntityStringUtility.simpleInnerClassString(clazz, className), () ->
                TestAccess.getClass(clazz, className));
    }
    
    /**
     * Asserts that an interface exists.
     *
     * @param qualifiedInterfaceName The qualified name of the interface.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getInterface(String)
     */
    public static void assertInterfaceExists(String qualifiedInterfaceName) {
        checkExists(true, "interface", qualifiedInterfaceName, () ->
                TestAccess.getInterface(qualifiedInterfaceName));
    }
    
    /**
     * Asserts that an inner interface exists.
     *
     * @param clazz         The class.
     * @param interfaceName The name of the inner interface.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getInterface(Class, String)
     */
    public static void assertInterfaceExists(Class<?> clazz, String interfaceName) {
        checkExists(true, "interface", EntityStringUtility.simpleInnerClassString(clazz, interfaceName), () ->
                TestAccess.getInterface(clazz, interfaceName));
    }
    
    /**
     * Asserts that an interface does not exist.
     *
     * @param qualifiedInterfaceName The qualified name of the interface.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getInterface(String)
     */
    public static void assertInterfaceDoesNotExist(String qualifiedInterfaceName) {
        checkExists(false, "interface", qualifiedInterfaceName, () ->
                TestAccess.getInterface(qualifiedInterfaceName));
    }
    
    /**
     * Asserts that an inner interface does not exist.
     *
     * @param clazz         The class.
     * @param interfaceName The name of the inner interface.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getInterface(Class, String)
     */
    public static void assertInterfaceDoesNotExist(Class<?> clazz, String interfaceName) {
        checkExists(false, "interface", EntityStringUtility.simpleInnerClassString(clazz, interfaceName), () ->
                TestAccess.getInterface(clazz, interfaceName));
    }
    
    /**
     * Asserts that an enum exists.
     *
     * @param qualifiedEnumName The qualified name of the enum.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getEnum(String)
     */
    public static void assertEnumExists(String qualifiedEnumName) {
        checkExists(true, "enum", qualifiedEnumName, () ->
                TestAccess.getEnum(qualifiedEnumName));
    }
    
    /**
     * Asserts that an inner enum exists.
     *
     * @param clazz    The class.
     * @param enumName The name of the inner enum.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getEnum(Class, String)
     */
    public static void assertEnumExists(Class<?> clazz, String enumName) {
        checkExists(true, "enum", EntityStringUtility.simpleInnerClassString(clazz, enumName), () ->
                TestAccess.getEnum(clazz, enumName));
    }
    
    /**
     * Asserts that an enum does not exist.
     *
     * @param qualifiedEnumName The qualified name of the enum.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getEnum(String)
     */
    public static void assertEnumDoesNotExist(String qualifiedEnumName) {
        checkExists(false, "enum", qualifiedEnumName, () ->
                TestAccess.getEnum(qualifiedEnumName));
    }
    
    /**
     * Asserts that an inner enum does not exist.
     *
     * @param clazz    The class.
     * @param enumName The name of the inner enum.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getEnum(Class, String)
     */
    public static void assertEnumDoesNotExist(Class<?> clazz, String enumName) {
        checkExists(false, "enum", EntityStringUtility.simpleInnerClassString(clazz, enumName), () ->
                TestAccess.getEnum(clazz, enumName));
    }
    
    /**
     * Asserts that a method exists.
     *
     * @param clazz           The class.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getMethod(Class, String, Class[])
     */
    public static void assertMethodExists(Class<?> clazz, String methodName, Class<?>... argumentClasses) {
        checkExists(true, "method", EntityStringUtility.simpleMethodString(clazz, methodName, argumentClasses), () ->
                TestAccess.getMethod(clazz, methodName, argumentClasses));
    }
    
    /**
     * Asserts that a method exists.
     *
     * @param object          The object.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getMethod(Object, String, Class[])
     */
    public static void assertMethodExists(Object object, String methodName, Class<?>... argumentClasses) {
        checkExists(true, "method", EntityStringUtility.simpleMethodString(object, methodName, argumentClasses), () ->
                TestAccess.getMethod(object, methodName, argumentClasses));
    }
    
    /**
     * Asserts that a method does not exist.
     *
     * @param clazz           The class.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getMethod(Class, String, Class[])
     */
    public static void assertMethodDoesNotExist(Class<?> clazz, String methodName, Class<?>... argumentClasses) {
        checkExists(false, "method", EntityStringUtility.simpleMethodString(clazz, methodName, argumentClasses), () ->
                TestAccess.getMethod(clazz, methodName, argumentClasses));
    }
    
    /**
     * Asserts that a method does not exist.
     *
     * @param object          The object.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getMethod(Object, String, Class[])
     */
    public static void assertMethodDoesNotExist(Object object, String methodName, Class<?>... argumentClasses) {
        checkExists(false, "method", EntityStringUtility.simpleMethodString(object, methodName, argumentClasses), () ->
                TestAccess.getMethod(object, methodName, argumentClasses));
    }
    
    /**
     * Asserts that a constructor exists.
     *
     * @param clazz           The class.
     * @param argumentClasses The classes of the arguments to the constructor.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getConstructor(Class, Class[])
     */
    public static void assertConstructorExists(Class<?> clazz, Class<?>... argumentClasses) {
        checkExists(true, "constructor", EntityStringUtility.simpleConstructorString(clazz, argumentClasses), () ->
                TestAccess.getConstructor(clazz, argumentClasses));
    }
    
    /**
     * Asserts that a constructor exists.
     *
     * @param object          The object.
     * @param argumentClasses The classes of the arguments to the constructor.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getConstructor(Object, Class[])
     */
    public static void assertConstructorExists(Object object, Class<?>... argumentClasses) {
        checkExists(true, "constructor", EntityStringUtility.simpleConstructorString(object, argumentClasses), () ->
                TestAccess.getConstructor(object, argumentClasses));
    }
    
    /**
     * Asserts that a constructor does not exist.
     *
     * @param clazz           The class.
     * @param argumentClasses The classes of the arguments to the constructor.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getConstructor(Class, Class[])
     */
    public static void assertConstructorDoesNotExist(Class<?> clazz, Class<?>... argumentClasses) {
        checkExists(false, "constructor", EntityStringUtility.simpleConstructorString(clazz, argumentClasses), () ->
                TestAccess.getConstructor(clazz, argumentClasses));
    }
    
    /**
     * Asserts that a constructor does not exist.
     *
     * @param object          The object.
     * @param argumentClasses The classes of the arguments to the constructor.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getConstructor(Object, Class[])
     */
    public static void assertConstructorDoesNotExist(Object object, Class<?>... argumentClasses) {
        checkExists(false, "constructor", EntityStringUtility.simpleConstructorString(object, argumentClasses), () ->
                TestAccess.getConstructor(object, argumentClasses));
    }
    
    /**
     * Asserts that a field exists.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getField(Class, String)
     */
    public static void assertFieldExists(Class<?> clazz, String fieldName) {
        checkExists(true, "field", EntityStringUtility.simpleFieldString(clazz, fieldName), () ->
                TestAccess.getField(clazz, fieldName));
    }
    
    /**
     * Asserts that a field exists.
     *
     * @param object    The object.
     * @param fieldName The name of the field.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getField(Object, String)
     */
    public static void assertFieldExists(Object object, String fieldName) {
        checkExists(true, "field", EntityStringUtility.simpleFieldString(object, fieldName), () ->
                TestAccess.getField(object, fieldName));
    }
    
    /**
     * Asserts that a field does not exist.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getField(Class, String)
     */
    public static void assertFieldDoesNotExist(Class<?> clazz, String fieldName) {
        checkExists(false, "field", EntityStringUtility.simpleFieldString(clazz, fieldName), () ->
                TestAccess.getField(clazz, fieldName));
    }
    
    /**
     * Asserts that a field does not exist.
     *
     * @param object    The object.
     * @param fieldName The name of the field.
     * @see #checkExists(boolean, String, String, Action)
     * @see TestAccess#getField(Object, String)
     */
    public static void assertFieldDoesNotExist(Object object, String fieldName) {
        checkExists(false, "field", EntityStringUtility.simpleFieldString(object, fieldName), () ->
                TestAccess.getField(object, fieldName));
    }
    
    
    //Inner Classes
    
    /**
     * Wraps the Assert calls from TestUtils.
     */
    private static final class AssertWrapper {
        
        //Static Methods
        
        /**
         * Calls Assert.fail().
         *
         * @param message The message.
         * @see Assert#fail(String)
         */
        private static void fail(String message) {
            Assert.fail(message);
        }
        
        /**
         * Calls Assert.fail().
         *
         * @param message The message.
         * @param cause   The throwable cause of the failure,
         * @see #fail(String, Throwable)
         */
        private static void fail(String message, Throwable cause) {
            final String causeMessage = (cause == null) ? "" : (EntityStringUtility.simpleClassString(cause.getClass()) + ": " +
                    cause.getMessage().replaceAll("\\$MockitoMock\\$\\d*", ""));
            fail(message + ": [" + causeMessage + ']');
        }
        
        /**
         * Calls Assert.assertEquals().
         *
         * @param message  The message on a failure.
         * @param expected The expected object.
         * @param actual   The actual object.
         * @see Assert#assertEquals(Object, Object)
         */
        private static void assertEquals(String message, Object expected, Object actual) {
            Assert.assertEquals(message, expected, actual);
        }
        
    }
    
}
