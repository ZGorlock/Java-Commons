/*
 * File:    TestUtils.java
 * Package: commons.test
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.test;

import java.io.InvalidClassException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import commons.function.Action;
import commons.object.ObjectCastUtility;
import commons.object.string.StringUtility;
import org.junit.Assert;
import org.powermock.reflect.Whitebox;
import org.powermock.reflect.exceptions.ConstructorNotFoundException;
import org.powermock.reflect.exceptions.FieldNotFoundException;
import org.powermock.reflect.exceptions.MethodNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides test utilities.
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
     * Asserts that an action throws an exception with a particular message.
     *
     * @param thrown          The expected exception.
     * @param expectedMessage The expected message.
     * @param action          The action.
     */
    public static void assertException(Class<? extends Throwable> thrown, String expectedMessage, Action action) {
        try {
            action.perform();
            
        } catch (Throwable e) {
            if (thrown != null) {
                AssertWrapper.assertEquals("Expected code to produce " + StringUtility.justifyAOrAn(thrown.getSimpleName()) +
                                " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()),
                        thrown, e.getClass());
            }
            if (expectedMessage != null) {
                AssertWrapper.assertEquals("Expected the error message of the " + ((thrown != null) ? thrown.getSimpleName() : "exception") +
                                " to be: " + StringUtility.quote(expectedMessage) +
                                " but the error message was: " + StringUtility.quote(e.getMessage()),
                        expectedMessage, e.getMessage());
            }
            return;
        }
        
        AssertWrapper.fail("Expected code to produce " + ((thrown != null) ? StringUtility.justifyAOrAn(thrown.getSimpleName()) : "an exception") +
                " but no exception was produced");
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
     * @see #assertException(Class, String, Action)
     */
    public static void assertException(Action action) {
        assertException(null, null, action);
    }
    
    /**
     * Asserts that an action does not throw an exception.
     *
     * @param action The action.
     */
    public static void assertNoException(Action action) {
        try {
            action.perform();
            
        } catch (Throwable e) {
            AssertWrapper.fail("Expected code to produce no Exception" +
                    " but instead it produced " + StringUtility.justifyAOrAn(e.getClass().getSimpleName()));
        }
    }
    
    /**
     * Asserts that a class exists.
     *
     * @param qualifiedClassName The qualified name of the class.
     * @see #getClass(String)
     */
    public static void assertClassExists(String qualifiedClassName) {
        if (getClass(qualifiedClassName) == null) {
            AssertWrapper.fail("Expected class " + qualifiedClassName + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that an inner class exists.
     *
     * @param clazz     The class.
     * @param className The name of the inner class.
     * @see #getClass(Class, String)
     */
    public static void assertClassExists(Class<?> clazz, String className) {
        if (getClass(clazz, className) == null) {
            AssertWrapper.fail("Expected class " + StringUtility.innerClassString(clazz, className) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that a class does not exist.
     *
     * @param qualifiedClassName The qualified name of the class.
     * @see #getClass(String)
     */
    public static void assertClassDoesNotExist(String qualifiedClassName) {
        if (getClass(qualifiedClassName) != null) {
            AssertWrapper.fail("Expected class " + qualifiedClassName + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that an inner class does not exist.
     *
     * @param clazz     The class.
     * @param className The name of the inner class.
     * @see #getClass(Class, String)
     */
    public static void assertClassDoesNotExist(Class<?> clazz, String className) {
        if (getClass(clazz, className) != null) {
            AssertWrapper.fail("Expected class " + StringUtility.innerClassString(clazz, className) + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that an interface exists.
     *
     * @param qualifiedInterfaceName The qualified name of the interface.
     * @see #getInterface(String)
     */
    public static void assertInterfaceExists(String qualifiedInterfaceName) {
        if (getInterface(qualifiedInterfaceName) == null) {
            AssertWrapper.fail("Expected interface " + qualifiedInterfaceName + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that an inner interface exists.
     *
     * @param clazz         The class.
     * @param interfaceName The name of the inner interface.
     * @see #getInterface(Class, String)
     */
    public static void assertInterfaceExists(Class<?> clazz, String interfaceName) {
        if (getInterface(clazz, interfaceName) == null) {
            AssertWrapper.fail("Expected interface " + StringUtility.innerClassString(clazz, interfaceName) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that an interface does not exist.
     *
     * @param qualifiedInterfaceName The qualified name of the interface.
     * @see #getInterface(String)
     */
    public static void assertInterfaceDoesNotExist(String qualifiedInterfaceName) {
        if (getInterface(qualifiedInterfaceName) != null) {
            AssertWrapper.fail("Expected interface " + qualifiedInterfaceName + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that an inner interface does not exist.
     *
     * @param clazz         The class.
     * @param interfaceName The name of the inner interface.
     * @see #getInterface(Class, String)
     */
    public static void assertInterfaceDoesNotExist(Class<?> clazz, String interfaceName) {
        if (getInterface(clazz, interfaceName) != null) {
            AssertWrapper.fail("Expected interface " + StringUtility.innerClassString(clazz, interfaceName) + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that an enum exists.
     *
     * @param qualifiedEnumName The qualified name of the enum.
     * @see #getEnum(String)
     */
    public static void assertEnumExists(String qualifiedEnumName) {
        if (getEnum(qualifiedEnumName) == null) {
            AssertWrapper.fail("Expected enum " + qualifiedEnumName + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that an inner enum exists.
     *
     * @param clazz    The class.
     * @param enumName The name of the inner enum.
     * @see #getEnum(Class, String)
     */
    public static void assertEnumExists(Class<?> clazz, String enumName) {
        if (getEnum(clazz, enumName) == null) {
            AssertWrapper.fail("Expected enum " + StringUtility.innerClassString(clazz, enumName) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that an enum does not exist.
     *
     * @param qualifiedEnumName The qualified name of the enum.
     * @see #getEnum(String)
     */
    public static void assertEnumDoesNotExist(String qualifiedEnumName) {
        if (getEnum(qualifiedEnumName) != null) {
            AssertWrapper.fail("Expected enum " + qualifiedEnumName + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that an inner enum does not exist.
     *
     * @param clazz    The class.
     * @param enumName The name of the inner enum.
     * @see #getEnum(Class, String)
     */
    public static void assertEnumDoesNotExist(Class<?> clazz, String enumName) {
        if (getEnum(clazz, enumName) != null) {
            AssertWrapper.fail("Expected enum " + StringUtility.innerClassString(clazz, enumName) + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that a method exists.
     *
     * @param clazz           The class.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     * @see #getMethod(Class, String, Class[])
     */
    public static void assertMethodExists(Class<?> clazz, String methodName, Class<?>... argumentClasses) {
        if (getMethod(clazz, methodName, argumentClasses) == null) {
            AssertWrapper.fail("Expected method " + StringUtility.methodString(clazz, methodName, argumentClasses) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that a method exists.
     *
     * @param object          The object.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     * @see #getMethod(Object, String, Class[])
     */
    public static void assertMethodExists(Object object, String methodName, Class<?>... argumentClasses) {
        if (getMethod(object, methodName, argumentClasses) == null) {
            AssertWrapper.fail("Expected method " + StringUtility.methodString(object, methodName, argumentClasses) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that a method does not exist.
     *
     * @param clazz           The class.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     * @see #getMethod(Class, String, Class[])
     */
    public static void assertMethodDoesNotExist(Class<?> clazz, String methodName, Class<?>... argumentClasses) {
        if (getMethod(clazz, methodName, argumentClasses) != null) {
            AssertWrapper.fail("Expected method " + StringUtility.methodString(clazz, methodName, argumentClasses) + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that a method does not exist.
     *
     * @param object          The object.
     * @param methodName      The name of the method.
     * @param argumentClasses The classes of the arguments to the method.
     * @see #getMethod(Object, String, Class[])
     */
    public static void assertMethodDoesNotExist(Object object, String methodName, Class<?>... argumentClasses) {
        if (getMethod(object, methodName, argumentClasses) != null) {
            AssertWrapper.fail("Expected method " + StringUtility.methodString(object, methodName, argumentClasses) + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that a constructor exists.
     *
     * @param clazz           The class.
     * @param argumentClasses The classes of the arguments to the constructor.
     * @see #getConstructor(Class, Class[])
     */
    public static void assertConstructorExists(Class<?> clazz, Class<?>... argumentClasses) {
        if (getConstructor(clazz, argumentClasses) == null) {
            AssertWrapper.fail("Expected constructor " + StringUtility.constructorString(clazz, argumentClasses) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that a constructor exists.
     *
     * @param object          The object.
     * @param argumentClasses The classes of the arguments to the constructor.
     * @see #getConstructor(Object, Class[])
     */
    public static void assertConstructorExists(Object object, Class<?>... argumentClasses) {
        if (getConstructor(object, argumentClasses) == null) {
            AssertWrapper.fail("Expected constructor " + StringUtility.constructorString(object, argumentClasses) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that a constructor does not exist.
     *
     * @param clazz           The class.
     * @param argumentClasses The classes of the arguments to the constructor.
     * @see #getConstructor(Class, Class[])
     */
    public static void assertConstructorDoesNotExist(Class<?> clazz, Class<?>... argumentClasses) {
        if (getConstructor(clazz, argumentClasses) != null) {
            AssertWrapper.fail("Expected constructor " + StringUtility.constructorString(clazz, argumentClasses) + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that a constructor does not exist.
     *
     * @param object          The object.
     * @param argumentClasses The classes of the arguments to the constructor.
     * @see #getConstructor(Object, Class[])
     */
    public static void assertConstructorDoesNotExist(Object object, Class<?>... argumentClasses) {
        if (getConstructor(object, argumentClasses) != null) {
            AssertWrapper.fail("Expected constructor " + StringUtility.constructorString(object, argumentClasses) + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that a field exists.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field.
     * @see #getField(Class, String)
     */
    public static void assertFieldExists(Class<?> clazz, String fieldName) {
        if (getField(clazz, fieldName) == null) {
            AssertWrapper.fail("Expected field " + StringUtility.fieldString(clazz, fieldName) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that a field exists.
     *
     * @param object    The object.
     * @param fieldName The name of the field.
     * @see #getField(Object, String)
     */
    public static void assertFieldExists(Object object, String fieldName) {
        if (getField(object, fieldName) == null) {
            AssertWrapper.fail("Expected field " + StringUtility.fieldString(object, fieldName) + " to exist" +
                    " but it does not");
        }
    }
    
    /**
     * Asserts that a field does not exist.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field.
     * @see #getField(Class, String)
     */
    public static void assertFieldDoesNotExist(Class<?> clazz, String fieldName) {
        if (getField(clazz, fieldName) != null) {
            AssertWrapper.fail("Expected field " + StringUtility.fieldString(clazz, fieldName) + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Asserts that a field does not exist.
     *
     * @param object    The object.
     * @param fieldName The name of the field.
     * @see #getField(Object, String)
     */
    public static void assertFieldDoesNotExist(Object object, String fieldName) {
        if (getField(object, fieldName) != null) {
            AssertWrapper.fail("Expected field " + StringUtility.fieldString(object, fieldName) + " to not exist" +
                    " but it does");
        }
    }
    
    /**
     * Gets a class.
     *
     * @param qualifiedClassName The qualified name of the class.
     * @return The class, or null if it cannot be retrieved.
     */
    public static Class<?> getClass(String qualifiedClassName) {
        try {
            final Class<?> clazz = Class.forName(Objects.requireNonNull(qualifiedClassName));
            
            return (clazz.isInterface() || clazz.isEnum()) ? null : clazz;
            
        } catch (Exception ignored) {
            return null;
        }
    }
    
    /**
     * Gets an inner class from a class.
     *
     * @param clazz     The class.
     * @param className The name of the inner class.
     * @return The inner class from the class, or null if it cannot be retrieved.
     * @see #getClass(String)
     */
    public static Class<?> getClass(Class<?> clazz, String className) {
        return getClass(((clazz == null) ? "null" : clazz.getName()) + '$' + className);
    }
    
    /**
     * Gets a interface.
     *
     * @param qualifiedInterfaceName The qualified name of the interface.
     * @return The interface, or null if it cannot be retrieved.
     */
    public static Class<?> getInterface(String qualifiedInterfaceName) {
        try {
            final Class<?> interfaceClazz = Class.forName(Objects.requireNonNull(qualifiedInterfaceName));
            
            return (!interfaceClazz.isInterface()) ? null : interfaceClazz;
            
        } catch (Exception ignored) {
            return null;
        }
    }
    
    /**
     * Gets an inner interface from a class.
     *
     * @param clazz         The class.
     * @param interfaceName The name of the inner interface.
     * @return The inner interface from the class, or null if it cannot be retrieved.
     * @see #getInterface(String)
     */
    public static Class<?> getInterface(Class<?> clazz, String interfaceName) {
        return getInterface(((clazz == null) ? "null" : clazz.getName()) + '$' + interfaceName);
    }
    
    /**
     * Gets an enum.
     *
     * @param qualifiedEnumName The qualified name of the enum.
     * @return The enum, or null if it cannot be retrieved.
     */
    public static Class<?> getEnum(String qualifiedEnumName) {
        try {
            final Class<?> enumClazz = Class.forName(Objects.requireNonNull(qualifiedEnumName));
            
            return (!enumClazz.isEnum()) ? null : enumClazz;
            
        } catch (Exception ignored) {
            return null;
        }
    }
    
    /**
     * Gets an inner enum from a class.
     *
     * @param clazz    The class.
     * @param enumName The name of the enum.
     * @return The inner enum from the class, or null if it cannot be retrieved.
     * @see #getEnum(String)
     */
    public static Class<?> getEnum(Class<?> clazz, String enumName) {
        return getEnum(((clazz == null) ? "null" : clazz.getName()) + '$' + enumName);
    }
    
    /**
     * Gets a list of all methods from a class.
     *
     * @param clazz The class.
     * @return The list of all methods from the class, or an empty list if it cannot be retrieved.
     * @see #getAllMethods(Object)
     */
    public static List<Method> getAllMethods(Class<?> clazz) {
        return getAllMethods((Object) clazz);
    }
    
    /**
     * Gets a list of all methods from an object.
     *
     * @param object The object.
     * @return The list of all methods from the object, or an empty list if it cannot be retrieved.
     */
    public static List<Method> getAllMethods(Object object) {
        try {
            final Class<?> clazz = Objects.requireNonNull(ObjectCastUtility.toClass(object));
            
            final List<Method> methods = new ArrayList<>();
            for (Class<?> current = clazz; current != null; current = current.getSuperclass()) {
                methods.addAll(Arrays.asList(current.getDeclaredMethods()));
            }
            return methods;
            
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }
    
    /**
     * Gets a method from a class.
     *
     * @param clazz         The class.
     * @param methodName    The name of the method.
     * @param argumentTypes The types of the arguments to the method.
     * @return The method from the class, or null if it cannot be retrieved.
     * @see #getMethod(Object, String, Class[])
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... argumentTypes) {
        return getMethod((Object) clazz, methodName, argumentTypes);
    }
    
    /**
     * Gets a method from an object.
     *
     * @param object        The object.
     * @param methodName    The name of the method.
     * @param argumentTypes The types of the arguments to the method.
     * @return The method from the object, or null if it cannot be retrieved.
     */
    public static Method getMethod(Object object, String methodName, Class<?>... argumentTypes) {
        try {
            final Class<?> clazz = Objects.requireNonNull(ObjectCastUtility.toClass(object));
            
            try {
                return clazz.getDeclaredMethod(methodName, argumentTypes);
            } catch (NoSuchMethodException | SecurityException ignored) {
            }
            
            try {
                return clazz.getMethod(methodName, argumentTypes);
            } catch (NoSuchMethodException | SecurityException ignored) {
            }
            
            return getAllMethods(clazz).stream().filter(e -> {
                final Class<?>[] methodArgumentTypes = e.getParameterTypes();
                return (e.getName().equals(methodName) && (methodArgumentTypes.length == argumentTypes.length) &&
                        Arrays.stream(methodArgumentTypes).noneMatch(arg -> arg.getSimpleName().equals("IndicateReloadClass")) &&
                        IntStream.range(0, argumentTypes.length).boxed().noneMatch(i ->
                                (argumentTypes[i] == null) ? methodArgumentTypes[i].isPrimitive() :
                                ((methodArgumentTypes[i] != argumentTypes[i]) &&
                                        (ObjectCastUtility.toPrimitiveClass(methodArgumentTypes[i]) != ObjectCastUtility.toPrimitiveClass(argumentTypes[i])))));
            }).findFirst().orElseThrow(() -> new MethodNotFoundException(StringUtility.methodString(clazz, methodName, argumentTypes)));
        } catch (Exception ignored) {
            return null;
        }
    }
    
    /**
     * Gets a list of all constructors from a class.
     *
     * @param clazz The class.
     * @return The list of all constructors from the class, or an empty list if it cannot be retrieved.
     * @see #getAllConstructors(Object)
     */
    public static List<Constructor<?>> getAllConstructors(Class<?> clazz) {
        return getAllConstructors((Object) clazz);
    }
    
    /**
     * Gets a list of all constructors from an object.
     *
     * @param object The object.
     * @return The list of all constructors from the object, or an empty list if it cannot be retrieved.
     */
    public static List<Constructor<?>> getAllConstructors(Object object) {
        try {
            final Class<?> clazz = Objects.requireNonNull(ObjectCastUtility.toClass(object));
            
            final List<Constructor<?>> constructors = new ArrayList<>();
            for (Class<?> current = clazz; current != null; current = current.getSuperclass()) {
                constructors.addAll(Arrays.asList(current.getDeclaredConstructors()));
            }
            return constructors;
            
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }
    
    /**
     * Gets a constructor from a class.
     *
     * @param clazz         The class.
     * @param argumentTypes The types of the arguments to the constructor.
     * @return The constructor from the class, or null if it cannot be retrieved.
     * @see #getConstructor(Object, Class[])
     */
    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... argumentTypes) {
        return getConstructor((Object) clazz, argumentTypes);
    }
    
    /**
     * Gets a constructor from an object.
     *
     * @param object        The object.
     * @param argumentTypes The types of the arguments to the constructor.
     * @return The constructor from the object, or null if it cannot be retrieved.
     */
    public static Constructor<?> getConstructor(Object object, Class<?>... argumentTypes) {
        try {
            final Class<?> clazz = Objects.requireNonNull(ObjectCastUtility.toClass(object));
            
            try {
                return clazz.getDeclaredConstructor(argumentTypes);
            } catch (NoSuchMethodException | SecurityException ignored) {
            }
            
            try {
                return clazz.getConstructor(argumentTypes);
            } catch (NoSuchMethodException | SecurityException ignored) {
            }
            
            return getAllConstructors(clazz).stream().filter(e -> {
                final Class<?>[] constructorArgumentTypes = e.getParameterTypes();
                return ((constructorArgumentTypes.length == argumentTypes.length) &&
                        Arrays.stream(constructorArgumentTypes).noneMatch(arg -> arg.getSimpleName().equals("IndicateReloadClass")) &&
                        IntStream.range(0, argumentTypes.length).boxed().noneMatch(i ->
                                (argumentTypes[i] == null) ? constructorArgumentTypes[i].isPrimitive() :
                                ((constructorArgumentTypes[i] != argumentTypes[i]) &&
                                        (ObjectCastUtility.toPrimitiveClass(constructorArgumentTypes[i]) != ObjectCastUtility.toPrimitiveClass(argumentTypes[i])))));
            }).findFirst().orElseThrow(() -> new MethodNotFoundException(StringUtility.constructorString(clazz, argumentTypes)));
            
        } catch (Exception ignored) {
            return null;
        }
    }
    
    /**
     * Gets a list of all fields from a class.
     *
     * @param clazz The class.
     * @return The list of all fields from the class, or an empty list if it cannot be retrieved.
     * @see #getAllFields(Object)
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        return getAllFields((Object) clazz);
    }
    
    /**
     * Gets a list of all fields from an object.
     *
     * @param object The object.
     * @return The list of all fields from the object, or an empty list if it cannot be retrieved.
     */
    public static List<Field> getAllFields(Object object) {
        try {
            final Class<?> clazz = Objects.requireNonNull(ObjectCastUtility.toClass(object));
            
            final Map<String, Field> fields = new LinkedHashMap<>();
            for (Class<?> current = clazz; current != null; current = current.getSuperclass()) {
                Arrays.stream(current.getDeclaredFields()).forEachOrdered(e ->
                        fields.put(StringUtility.fieldString(clazz, e.getName()), e));
            }
            return new ArrayList<>(fields.values());
            
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }
    
    /**
     * Gets a field from a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field.
     * @return The field from the class, or null if it cannot be retrieved.
     * @see #getField(Object, String)
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        return getField((Object) clazz, fieldName);
    }
    
    /**
     * Gets a field from an object.
     *
     * @param object    The object.
     * @param fieldName The name of the field.
     * @return The field from the object, or null if it cannot be retrieved.
     */
    public static Field getField(Object object, String fieldName) {
        try {
            final Class<?> clazz = Objects.requireNonNull(ObjectCastUtility.toClass(object));
            
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException | SecurityException ignored) {
            }
            
            try {
                return clazz.getField(fieldName);
            } catch (NoSuchFieldException | SecurityException ignored) {
            }
            
            return getAllFields(clazz).stream().filter(e -> e.getName().equals(fieldName))
                    .findFirst().orElseThrow(() -> new FieldNotFoundException(StringUtility.fieldString(clazz, fieldName)));
            
        } catch (Exception ignored) {
            return null;
        }
    }
    
    /**
     * Gets the value of a field from a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the class.
     * @throws AssertionError When there is an exception while getting the field.
     * @see #getFieldValue(Object, String)
     */
    public static Object getFieldValue(Class<?> clazz, String fieldName) throws AssertionError {
        return getFieldValue((Object) clazz, fieldName);
    }
    
    /**
     * Gets the value of a field from a class.
     *
     * @param clazz     The class.
     * @param type      The type of the field.
     * @param fieldName The name of the field to get.
     * @param <T>       The type of the field.
     * @return The value of the field from the class.
     * @throws AssertionError When there is an exception while getting the field.
     * @see #getFieldValue(Class, String)
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Class<?> clazz, Class<T> type, String fieldName) throws AssertionError {
        return (T) getFieldValue(clazz, fieldName);
    }
    
    /**
     * Gets the value of a field from an object.
     *
     * @param object    The object.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the object.
     * @throws AssertionError When there is an exception while getting the field.
     */
    @SuppressWarnings("deprecation")
    public static Object getFieldValue(Object object, String fieldName) throws AssertionError {
        final Class<?> clazz = Objects.requireNonNull(ObjectCastUtility.toClass(object));
        
        try {
            try {
                return Whitebox.getInternalState(object, Objects.requireNonNull(fieldName));
                
            } catch (Exception ignored) {
                final Field field = getField(clazz, fieldName);
                if (field == null) {
                    throw new FieldNotFoundException(StringUtility.fieldString(clazz, fieldName));
                }
                
                final boolean isAccessible = field.isAccessible();
                try {
                    field.setAccessible(true);
                    return field.get(object);
                } finally {
                    field.setAccessible(isAccessible);
                }
            }
            
        } catch (Exception e) {
            AssertWrapper.fail("Attempted to get the value of the field " + StringUtility.fieldString(clazz, fieldName) +
                    " but an exception occurred", e);
            throw new AssertionError(e);
        }
    }
    
    /**
     * Gets the value of a field from an object.
     *
     * @param object    The object.
     * @param type      The type of the field.
     * @param fieldName The name of the field to get.
     * @param <T>       The type of the field.
     * @return The value of the field from the object.
     * @throws AssertionError When there is an exception while getting the field.
     * @see #getFieldValue(Object, String)
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object object, Class<T> type, String fieldName) throws AssertionError {
        return (T) getFieldValue(object, fieldName);
    }
    
    /**
     * Sets the value of a field from a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field from the class was successfully set or not.
     * @throws AssertionError When there is an exception while setting the field.
     * @see #setFieldValue(Object, String, Object)
     */
    public static boolean setFieldValue(Class<?> clazz, String fieldName, Object value) throws AssertionError {
        return setFieldValue((Object) clazz, fieldName, value);
    }
    
    /**
     * Sets the value of a field from an object.
     *
     * @param object    The object.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field from the object was successfully set or not.
     * @throws AssertionError When there is an exception while setting the field.
     */
    @SuppressWarnings("deprecation")
    public static boolean setFieldValue(Object object, String fieldName, Object value) throws AssertionError {
        final Class<?> clazz = Objects.requireNonNull(ObjectCastUtility.toClass(object));
        
        try {
            try {
                Whitebox.setInternalState(object, Objects.requireNonNull(fieldName), value);
                return Objects.equals(Whitebox.getInternalState(object, fieldName), value);
                
            } catch (Exception ignored) {
                final Field field = getField(clazz, fieldName);
                if (field == null) {
                    throw new FieldNotFoundException(StringUtility.fieldString(clazz, fieldName));
                }
                
                final boolean isAccessible = field.isAccessible();
                try {
                    field.setAccessible(true);
                    field.set(object, value);
                    return Objects.equals(field.get(object), value);
                } finally {
                    field.setAccessible(isAccessible);
                }
            }
            
        } catch (Exception e) {
            AssertWrapper.fail("Attempted to set the value of the field " + StringUtility.fieldString(clazz, fieldName) +
                    " but an exception occurred", e);
            throw new AssertionError(e);
        }
    }
    
    /**
     * Invokes a method from a class.
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
     * Invokes a method from a class.
     *
     * @param clazz      The class.
     * @param returnType The return type of the method.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @param <T>        The return type of the method.
     * @return The result of the method invocation.
     * @throws AssertionError When there is an exception while invoking the method.
     * @see #invokeMethod(Class, String, Object...)
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Class<?> clazz, Class<T> returnType, String methodName, Object... arguments) throws AssertionError {
        return (T) invokeMethod(clazz, methodName, arguments);
    }
    
    /**
     * Invokes a method from an object.
     *
     * @param object     The object.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the method invocation.
     * @throws AssertionError When there is an exception while invoking the method.
     */
    @SuppressWarnings("deprecation")
    public static Object invokeMethod(Object object, String methodName, Object... arguments) throws AssertionError {
        final Class<?> clazz = Objects.requireNonNull(ObjectCastUtility.toClass(object));
        final Class<?>[] argumentTypes = Arrays.stream(arguments).map(ObjectCastUtility::toClass).toArray(Class<?>[]::new);
        
        try {
            try {
                return Whitebox.invokeMethod(object, Objects.requireNonNull(methodName), arguments);
                
            } catch (Exception ignored) {
                final Method method = getMethod(clazz, methodName, argumentTypes);
                if (method == null) {
                    throw new MethodNotFoundException(StringUtility.methodString(clazz, methodName, argumentTypes));
                }
                
                final boolean isAccessible = method.isAccessible();
                try {
                    method.setAccessible(true);
                    return method.invoke(object, arguments);
                } finally {
                    method.setAccessible(isAccessible);
                }
            }
            
        } catch (Throwable e) {
            AssertWrapper.fail("Attempted to invoke the method " + StringUtility.methodString(object, methodName, argumentTypes) +
                    " but an exception occurred", e);
            throw new AssertionError(e);
        }
    }
    
    /**
     * Invokes a method from an object.
     *
     * @param object     The object.
     * @param returnType The return type of the method.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @param <T>        The return type of the method.
     * @return The result of the method invocation.
     * @throws AssertionError When there is an exception while invoking the method.
     * @see #invokeMethod(Object, String, Object...)
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Object object, Class<T> returnType, String methodName, Object... arguments) throws AssertionError {
        return (T) invokeMethod(object, methodName, arguments);
    }
    
    /**
     * Invokes a constructor.
     *
     * @param clazz     The class.
     * @param arguments The arguments to the constructor.
     * @param <T>       The type of the class.
     * @return The constructed instance of the class, or null if there was there was an error.
     * @throws AssertionError When there is an exception while invoking the constructor.
     */
    @SuppressWarnings({"deprecation", "unchecked"})
    public static <T> T invokeConstructor(Class<T> clazz, Object... arguments) throws AssertionError {
        Objects.requireNonNull(clazz);
        final Class<?>[] argumentTypes = Arrays.stream(arguments).map(ObjectCastUtility::toClass).toArray(Class<?>[]::new);
        
        try {
            try {
                return Whitebox.invokeConstructor(clazz, arguments);
                
            } catch (Exception ignored) {
                final Constructor<T> constructor = (Constructor<T>) getConstructor(clazz, argumentTypes);
                if ((constructor == null) || (constructor.getDeclaringClass() != clazz)) {
                    throw new ConstructorNotFoundException(StringUtility.constructorString(clazz, argumentTypes));
                }
                
                final boolean isAccessible = constructor.isAccessible();
                try {
                    constructor.setAccessible(true);
                    return constructor.newInstance(arguments);
                } finally {
                    constructor.setAccessible(isAccessible);
                }
            }
            
        } catch (Throwable e) {
            AssertWrapper.fail("Attempted to invoke the constructor " + StringUtility.constructorString(clazz, argumentTypes) +
                    " but an exception occurred", e);
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
        Objects.requireNonNull(clazz);
        final Class<?>[] argumentTypes = Arrays.stream(arguments).map(ObjectCastUtility::toClass).toArray(Class<?>[]::new);
        
        try {
            if (!clazz.isInterface()) {
                throw new InvalidClassException(clazz.getSimpleName() + " is not an interface");
            }
            
            final Method interfaceMethod = getMethod(clazz, methodName, argumentTypes);
            if (interfaceMethod == null) {
                throw new MethodNotFoundException(StringUtility.methodString(clazz, methodName, argumentTypes));
            }
            
            return (getFieldValue(MethodHandles.Lookup.class, MethodHandles.Lookup.class, "IMPL_LOOKUP"))
                    .unreflectSpecial(interfaceMethod, interfaceMethod.getDeclaringClass())
                    .bindTo(Proxy.newProxyInstance(
                            Thread.currentThread().getContextClassLoader(), new Class[] {clazz},
                            (Object proxy, Method method, Object[] args) -> null))
                    .invokeWithArguments(arguments);
            
        } catch (Throwable e) {
            AssertWrapper.fail("Attempted to invoke the interface method " + StringUtility.methodString(clazz, methodName, argumentTypes) +
                    " but an exception occurred", e);
            throw new AssertionError(e);
        }
    }
    
    /**
     * Invokes a default method of an interface and returns the result.
     *
     * @param clazz      The class.
     * @param returnType The return type of the method.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @param <T>        The return type of the method.
     * @return The result of the invocation.
     * @throws AssertionError When there is an exception while invoking the interface default method.
     * @see #invokeInterfaceDefaultMethod(Class, String, Object...)
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeInterfaceDefaultMethod(Class<?> clazz, Class<T> returnType, String methodName, Object... arguments) throws AssertionError {
        return (T) invokeInterfaceDefaultMethod(clazz, methodName, arguments);
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
         * @see Assert#fail(String)
         */
        public static void fail(String message) {
            Assert.fail(message);
        }
        
        /**
         * Calls Assert.fail().
         *
         * @param message The message.
         * @param cause   The throwable cause of the failure,
         * @see #fail(String, Throwable)
         */
        public static void fail(String message, Throwable cause) {
            final String causeMessage = (cause == null) ? "" : (StringUtility.classString(cause.getClass()) + ": " +
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
        public static void assertEquals(String message, Object expected, Object actual) {
            Assert.assertEquals(message, expected, actual);
        }
        
    }
    
}
