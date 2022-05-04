/*
 * File:    TestUtils.java
 * Package: commons.test
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import commons.lambda.function.Action;
import commons.lambda.function.Conditional;
import commons.object.collection.ArrayUtility;
import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
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
     * Asserts that an action throws an exception if the result of the conditional is true, or that it does not throw an exception if the result of the conditional is false.
     *
     * @param conditional     The conditional.
     * @param thrown          The expected exception.
     * @param expectedMessage The expected message.
     * @param action          The action.
     * @see #assertException(Class, String, Action)
     * @see #assertNoException(Action)
     */
    public static void assertExceptionIf(Conditional conditional, Class<? extends Throwable> thrown, String expectedMessage, Action action) {
        if (conditional.testQuietly()) {
            assertException(thrown, expectedMessage, action);
        } else {
            assertNoException(action);
        }
    }
    
    /**
     * Asserts that an action throws an exception if the result of the conditional is true, or that it does not throw an exception if the result of the conditional is false.
     *
     * @param conditional The conditional.
     * @param thrown      The expected exception.
     * @param action      The action.
     * @see #assertExceptionIf(Conditional, Class, String, Action)
     */
    public static void assertExceptionIf(Conditional conditional, Class<? extends Throwable> thrown, Action action) {
        assertExceptionIf(conditional, thrown, null, action);
    }
    
    /**
     * Asserts that an action throws an exception if the result of the conditional is true, or that it does not throw an exception if the result of the conditional is false.
     *
     * @param conditional The conditional.
     * @param action      The action.
     * @see #assertExceptionIf(Conditional, Class, Action)
     */
    public static void assertExceptionIf(Conditional conditional, Action action) {
        assertExceptionIf(conditional, null, action);
    }
    
    /**
     * Compares two lists.
     *
     * @param list            The first list.
     * @param other           The second list.
     * @param checkOrder      Whether or not to check the list order.
     * @param expectedToEqual Whether the lists are expected to be equal or not.
     * @param <T>             The type of the lists.
     * @see ListUtility#equals(List, List, boolean)
     */
    private static <T> void compareLists(List<T> list, List<? extends T> other, boolean checkOrder, boolean expectedToEqual) {
        final boolean equals = ListUtility.equals(list, other, checkOrder);
        if (expectedToEqual) {
            AssertWrapper.assertTrue(equals);
        } else {
            AssertWrapper.assertFalse(equals);
        }
    }
    
    /**
     * Asserts that a list has the expected contents.
     *
     * @param list       The list.
     * @param expected   The expected contents as a collection.
     * @param checkOrder Whether or not to check the list order.
     * @param <T>        The type of the list.
     * @see ListUtility#toList(Collection)
     * @see #compareLists(List, List, boolean, boolean)
     */
    public static <T> void assertListEquals(List<T> list, Collection<? extends T> expected, boolean checkOrder) {
        compareLists(list, ListUtility.toList(expected), checkOrder, true);
    }
    
    /**
     * Asserts that a list has the expected contents.
     *
     * @param list     The list.
     * @param expected The expected contents as a collection.
     * @param <T>      The type of the list.
     * @see #assertListEquals(List, Collection, boolean)
     */
    public static <T> void assertListEquals(List<T> list, Collection<? extends T> expected) {
        assertListEquals(list, expected, true);
    }
    
    /**
     * Asserts that a list has the expected contents.
     *
     * @param list       The list.
     * @param expected   The expected contents as an array.
     * @param checkOrder Whether or not to check the list order.
     * @param <T>        The type of the list.
     * @param <T2>       The type of the expected contents.
     * @see ListUtility#toList(Object[])
     * @see #compareLists(List, List, boolean, boolean)
     */
    public static <T, T2 extends T> void assertListEquals(List<T> list, T2[] expected, boolean checkOrder) {
        compareLists(list, ListUtility.toList(expected), checkOrder, true);
    }
    
    /**
     * Asserts that a list has the expected contents.
     *
     * @param list     The list.
     * @param expected The expected contents as an array.
     * @param <T>      The type of the list.
     * @param <T2>     The type of the expected contents.
     * @see #assertListEquals(List, Object[], boolean)
     */
    public static <T, T2 extends T> void assertListEquals(List<T> list, T2[] expected) {
        assertListEquals(list, expected, true);
    }
    
    /**
     * Asserts that a list does not have the unexpected contents.
     *
     * @param list       The list.
     * @param unexpected The unexpected contents as a collection.
     * @param checkOrder Whether or not to check the list order.
     * @param <T>        The type of the list.
     * @see ListUtility#toList(Collection)
     * @see #compareLists(List, List, boolean, boolean)
     */
    public static <T> void assertListNotEquals(List<T> list, Collection<? extends T> unexpected, boolean checkOrder) {
        compareLists(list, ListUtility.toList(unexpected), checkOrder, false);
    }
    
    /**
     * Asserts that a list does not have the unexpected contents.
     *
     * @param list       The list.
     * @param unexpected The unexpected contents as a collection.
     * @param <T>        The type of the list.
     * @see #assertListNotEquals(List, Collection, boolean)
     */
    public static <T> void assertListNotEquals(List<T> list, Collection<? extends T> unexpected) {
        assertListNotEquals(list, unexpected, true);
    }
    
    /**
     * Asserts that a list does not have the unexpected contents.
     *
     * @param list       The list.
     * @param unexpected The unexpected contents as an array.
     * @param checkOrder Whether or not to check the list order.
     * @param <T>        The type of the list.
     * @param <T2>       The type of the unexpected contents.
     * @see ListUtility#toList(Object[])
     * @see #compareLists(List, List, boolean, boolean)
     */
    public static <T, T2 extends T> void assertListNotEquals(List<T> list, T2[] unexpected, boolean checkOrder) {
        compareLists(list, ListUtility.toList(unexpected), checkOrder, false);
    }
    
    /**
     * Asserts that a list does not have the unexpected contents.
     *
     * @param list       The list.
     * @param unexpected The unexpected contents as an array.
     * @param <T>        The type of the list.
     * @param <T2>       The type of the unexpected contents.
     * @see #assertListNotEquals(List, Object[], boolean)
     */
    public static <T, T2 extends T> void assertListNotEquals(List<T> list, T2[] unexpected) {
        assertListNotEquals(list, unexpected, true);
    }
    
    /**
     * Compares two arrays.
     *
     * @param array           The first array.
     * @param other           The second array.
     * @param checkOrder      Whether or not to check the array order.
     * @param expectedToEqual Whether the arrays are expected to be equal or not.
     * @param <T>             The type of the first array.
     * @param <T2>            The type of the second array.
     * @see ArrayUtility#equals(Object[], Object[], boolean)
     */
    private static <T, T2 extends T> void compareArrays(T[] array, T2[] other, boolean checkOrder, boolean expectedToEqual) {
        final boolean equals = ArrayUtility.equals(array, other, checkOrder);
        if (expectedToEqual) {
            AssertWrapper.assertTrue(equals);
        } else {
            AssertWrapper.assertFalse(equals);
        }
    }
    
    /**
     * Asserts that an array has the expected contents.
     *
     * @param array      The array.
     * @param expected   The expected contents as a collection.
     * @param checkOrder Whether or not to check the array order.
     * @param <T>        The type of the array.
     * @see ArrayUtility#toArray(Collection)
     * @see #compareArrays(Object[], Object[], boolean, boolean)
     */
    public static <T> void assertArrayEquals(T[] array, Collection<? extends T> expected, boolean checkOrder) {
        compareArrays(array, ArrayUtility.toArray(expected), checkOrder, true);
    }
    
    /**
     * Asserts that an array has the expected contents.
     *
     * @param array    The array.
     * @param expected The expected contents as a collection.
     * @param <T>      The type of the array.
     * @see #assertArrayEquals(Object[], Collection, boolean)
     */
    public static <T> void assertArrayEquals(T[] array, Collection<? extends T> expected) {
        assertArrayEquals(array, expected, true);
    }
    
    /**
     * Asserts that an array has the expected contents.
     *
     * @param array      The array.
     * @param expected   The expected contents as an array.
     * @param checkOrder Whether or not to check the array order.
     * @param <T>        The type of the array.
     * @param <T2>       The type of the expected contents.
     * @see #compareArrays(Object[], Object[], boolean, boolean)
     */
    public static <T, T2 extends T> void assertArrayEquals(T[] array, T2[] expected, boolean checkOrder) {
        compareArrays(array, expected, checkOrder, true);
    }
    
    /**
     * Asserts that an array has the expected contents.
     *
     * @param array    The array.
     * @param expected The expected contents as an array.
     * @param <T>      The type of the array.
     * @param <T2>     The type of the expected contents.
     * @see #assertArrayEquals(Object[], Object[], boolean)
     */
    public static <T, T2 extends T> void assertArrayEquals(T[] array, T2[] expected) {
        assertArrayEquals(array, expected, true);
    }
    
    /**
     * Asserts that an array does not have the unexpected contents.
     *
     * @param array      The array.
     * @param unexpected The unexpected contents as a collection.
     * @param checkOrder Whether or not to check the array order.
     * @param <T>        The type of the array.
     * @see ArrayUtility#toArray(Collection)
     * @see #compareArrays(Object[], Object[], boolean, boolean)
     */
    public static <T> void assertArrayNotEquals(T[] array, Collection<? extends T> unexpected, boolean checkOrder) {
        compareArrays(array, ArrayUtility.toArray(unexpected), checkOrder, false);
    }
    
    /**
     * Asserts that an array does not have the unexpected contents.
     *
     * @param array      The array.
     * @param unexpected The unexpected contents as a collection.
     * @param <T>        The type of the array.
     * @see #assertArrayNotEquals(Object[], Collection, boolean)
     */
    public static <T> void assertArrayNotEquals(T[] array, Collection<? extends T> unexpected) {
        assertArrayNotEquals(array, unexpected, true);
    }
    
    /**
     * Asserts that an array does not have the unexpected contents.
     *
     * @param array      The array.
     * @param unexpected The unexpected contents as an array.
     * @param checkOrder Whether or not to check the array order.
     * @param <T>        The type of the array.
     * @param <T2>       The type of the unexpected contents.
     * @see #compareArrays(Object[], Object[], boolean, boolean)
     */
    public static <T, T2 extends T> void assertArrayNotEquals(T[] array, T2[] unexpected, boolean checkOrder) {
        compareArrays(array, unexpected, checkOrder, false);
    }
    
    /**
     * Asserts that an array does not have the unexpected contents.
     *
     * @param array      The array.
     * @param unexpected The unexpected contents as an array.
     * @param <T>        The type of the array.
     * @param <T2>       The type of the unexpected contents.
     * @see #assertArrayNotEquals(Object[], Object[], boolean)
     */
    public static <T, T2 extends T> void assertArrayNotEquals(T[] array, T2[] unexpected) {
        assertArrayNotEquals(array, unexpected, true);
    }
    
    /**
     * Compares two maps.
     *
     * @param map             The first map.
     * @param other           The second map.
     * @param expectedToEqual Whether the maps are expected to be equal or not.
     * @param <K>             The type of the keys of the maps.
     * @param <V>             The type of the values of the maps.
     * @see MapUtility#equals(Map, Map)
     */
    private static <K, V> void compareMaps(Map<K, V> map, Map<? extends K, ? extends V> other, boolean expectedToEqual) {
        final boolean equals = MapUtility.equals(map, other);
        if (expectedToEqual) {
            AssertWrapper.assertTrue(equals);
        } else {
            AssertWrapper.assertFalse(equals);
        }
    }
    
    /**
     * Asserts that a map has the expected contents.
     *
     * @param map      The map.
     * @param expected The expected contents as a map.
     * @param <K>      The type of the keys of the map.
     * @param <V>      The type of the values of the map.
     * @see #compareMaps(Map, Map, boolean)
     */
    public static <K, V> void assertMapEquals(Map<K, V> map, Map<? extends K, ? extends V> expected) {
        compareMaps(map, expected, true);
    }
    
    /**
     * Asserts that a map does not have the unexpected contents.
     *
     * @param map        The map.
     * @param unexpected The unexpected contents as a map.
     * @param <K>        The type of the keys of the map.
     * @param <V>        The type of the values of the map.
     * @see #compareMaps(Map, Map, boolean)
     */
    public static <K, V> void assertMapNotEquals(Map<K, V> map, Map<? extends K, ? extends V> unexpected) {
        compareMaps(map, unexpected, false);
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
         * @see Assert#fail()
         */
        private static void fail() {
            Assert.fail();
        }
        
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
         * Calls Assert.assertTrue().
         *
         * @param condition The condition.
         * @see Assert#assertTrue(boolean)
         */
        private static void assertTrue(boolean condition) {
            Assert.assertTrue(condition);
        }
        
        /**
         * Calls Assert.assertTrue().
         *
         * @param message   The message on a failure.
         * @param condition The condition.
         * @see Assert#assertTrue(String, boolean)
         */
        private static void assertTrue(String message, boolean condition) {
            Assert.assertTrue(message, condition);
        }
        
        /**
         * Calls Assert.assertFalse().
         *
         * @param condition The condition.
         * @see Assert#assertFalse(boolean)
         */
        private static void assertFalse(boolean condition) {
            Assert.assertFalse(condition);
        }
        
        /**
         * Calls Assert.assertFalse().
         *
         * @param message   The message on a failure.
         * @param condition The condition.
         * @see Assert#assertFalse(String, boolean)
         */
        private static void assertFalse(String message, boolean condition) {
            Assert.assertFalse(message, condition);
        }
        
        /**
         * Calls Assert.assertNull().
         *
         * @param object The object.
         * @see Assert#assertNull(Object)
         */
        private static void assertNull(Object object) {
            Assert.assertNull(object);
        }
        
        /**
         * Calls Assert.assertNull().
         *
         * @param message The message on a failure.
         * @param object  The object.
         * @see Assert#assertNull(String, Object)
         */
        private static void assertNull(String message, Object object) {
            Assert.assertNull(message, object);
        }
        
        /**
         * Calls Assert.assertNotNull().
         *
         * @param object The object.
         * @see Assert#assertNotNull(Object)
         */
        private static void assertNotNull(Object object) {
            Assert.assertNotNull(object);
        }
        
        /**
         * Calls Assert.assertNotNull().
         *
         * @param message The message on a failure.
         * @param object  The object.
         * @see Assert#assertNotNull(String, Object)
         */
        private static void assertNotNull(String message, Object object) {
            Assert.assertNotNull(message, object);
        }
        
        /**
         * Calls Assert.assertEquals().
         *
         * @param expected The expected object.
         * @param actual   The actual object.
         * @see Assert#assertEquals(Object, Object)
         */
        private static void assertEquals(Object expected, Object actual) {
            Assert.assertEquals(expected, actual);
        }
        
        /**
         * Calls Assert.assertEquals().
         *
         * @param message  The message on a failure.
         * @param expected The expected object.
         * @param actual   The actual object.
         * @see Assert#assertEquals(String, Object, Object)
         */
        private static void assertEquals(String message, Object expected, Object actual) {
            Assert.assertEquals(message, expected, actual);
        }
        
        /**
         * Calls Assert.assertNotEquals().
         *
         * @param unexpected The unexpected object.
         * @param actual     The actual object.
         * @see Assert#assertNotEquals(Object, Object)
         */
        private static void assertNotEquals(Object unexpected, Object actual) {
            Assert.assertNotEquals(unexpected, actual);
        }
        
        /**
         * Calls Assert.assertNotEquals().
         *
         * @param message    The message on a failure.
         * @param unexpected The unexpected object.
         * @param actual     The actual object.
         * @see Assert#assertNotEquals(String, Object, Object)
         */
        private static void assertNotEquals(String message, Object unexpected, Object actual) {
            Assert.assertNotEquals(message, unexpected, actual);
        }
        
    }
    
}
