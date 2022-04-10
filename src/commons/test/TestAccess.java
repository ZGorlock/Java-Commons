/*
 * File:    TestAccess.java
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import commons.object.CastUtility;
import commons.object.string.EntityStringUtility;
import org.powermock.reflect.Whitebox;
import org.powermock.reflect.exceptions.ConstructorNotFoundException;
import org.powermock.reflect.exceptions.FieldNotFoundException;
import org.powermock.reflect.exceptions.MethodNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides test access to classes and objects.
 */
public final class TestAccess {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(TestAccess.class);
    
    
    //Static Methods
    
    /**
     * Gets a class.
     *
     * @param qualifiedClassName The qualified name of the class.
     * @return The class.
     * @throws RuntimeException When there is an exception while getting the class.
     */
    public static Class<?> getClass(String qualifiedClassName) {
        try {
            final Class<?> clazz = Class.forName(Objects.requireNonNull(qualifiedClassName));
            
            if (clazz.isInterface() || clazz.isEnum()) {
                throw new InvalidClassException(EntityStringUtility.simpleClassString(clazz) + " is not a class");
            }
            return clazz;
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Gets an inner class from a class.
     *
     * @param clazz     The class.
     * @param className The name of the inner class.
     * @return The inner class from the class.
     * @throws RuntimeException When there is an exception while getting the class.
     * @see #getClass(String)
     */
    public static Class<?> getClass(Class<?> clazz, String className) {
        return getClass(EntityStringUtility.classString(clazz) + '$' + className);
    }
    
    /**
     * Gets a interface.
     *
     * @param qualifiedInterfaceName The qualified name of the interface.
     * @return The interface.
     * @throws RuntimeException When there is an exception while getting the interface.
     */
    public static Class<?> getInterface(String qualifiedInterfaceName) {
        try {
            final Class<?> interfaceClazz = Class.forName(Objects.requireNonNull(qualifiedInterfaceName));
            
            if (!interfaceClazz.isInterface()) {
                throw new InvalidClassException(EntityStringUtility.simpleClassString(interfaceClazz) + " is not an interface");
            }
            return interfaceClazz;
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Gets an inner interface from a class.
     *
     * @param clazz         The class.
     * @param interfaceName The name of the inner interface.
     * @return The inner interface from the class.
     * @throws RuntimeException When there is an exception while getting the interface.
     * @see #getInterface(String)
     */
    public static Class<?> getInterface(Class<?> clazz, String interfaceName) {
        return getInterface(EntityStringUtility.classString(clazz) + '$' + interfaceName);
    }
    
    /**
     * Gets an enum.
     *
     * @param qualifiedEnumName The qualified name of the enum.
     * @return The enum.
     * @throws RuntimeException When there is an exception while getting the enum.
     */
    public static Class<?> getEnum(String qualifiedEnumName) {
        try {
            final Class<?> enumClazz = Class.forName(Objects.requireNonNull(qualifiedEnumName));
            
            if (!enumClazz.isEnum()) {
                throw new InvalidClassException(EntityStringUtility.simpleClassString(enumClazz) + " is not an enum");
            }
            return enumClazz;
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Gets an inner enum from a class.
     *
     * @param clazz    The class.
     * @param enumName The name of the enum.
     * @return The inner enum from the class.
     * @throws RuntimeException When there is an exception while getting the enum.
     * @see #getEnum(String)
     */
    public static Class<?> getEnum(Class<?> clazz, String enumName) {
        return getEnum(EntityStringUtility.classString(clazz) + '$' + enumName);
    }
    
    /**
     * Gets a list of all methods from a class.
     *
     * @param clazz The class.
     * @return The list of all methods from the class.
     * @throws RuntimeException When there is an exception while getting the methods.
     * @see #getAllMethods(Object)
     */
    public static List<Method> getAllMethods(Class<?> clazz) {
        return getAllMethods((Object) clazz);
    }
    
    /**
     * Gets a list of all methods from an object.
     *
     * @param object The object.
     * @return The list of all methods from the object.
     * @throws RuntimeException When there is an exception while getting the methods.
     */
    public static List<Method> getAllMethods(Object object) {
        try {
            final Class<?> clazz = Objects.requireNonNull(CastUtility.toClass(object));
            
            final List<Method> methods = new ArrayList<>();
            for (Class<?> current = clazz; current != null; current = current.getSuperclass()) {
                methods.addAll(Arrays.asList(current.getDeclaredMethods()));
            }
            return methods;
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Gets a method from a class.
     *
     * @param clazz         The class.
     * @param methodName    The name of the method.
     * @param argumentTypes The types of the arguments to the method.
     * @return The method from the class.
     * @throws RuntimeException When there is an exception while getting the method.
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
     * @return The method from the object.
     * @throws RuntimeException When there is an exception while getting the method.
     */
    public static Method getMethod(Object object, String methodName, Class<?>... argumentTypes) {
        try {
            final Class<?> clazz = Objects.requireNonNull(CastUtility.toClass(object));
            
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
                        Arrays.stream(methodArgumentTypes).map(EntityStringUtility::simpleClassString).noneMatch(arg -> arg.equals("IndicateReloadClass")) &&
                        IntStream.range(0, argumentTypes.length).boxed().noneMatch(i ->
                                (argumentTypes[i] == null) ? methodArgumentTypes[i].isPrimitive() :
                                ((methodArgumentTypes[i] != argumentTypes[i]) &&
                                        (CastUtility.toPrimitiveClass(methodArgumentTypes[i]) != CastUtility.toPrimitiveClass(argumentTypes[i])))));
            }).findFirst().orElseThrow(() -> new MethodNotFoundException(EntityStringUtility.simpleMethodString(clazz, methodName, argumentTypes)));
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Gets a list of all constructors from a class.
     *
     * @param clazz The class.
     * @return The list of all constructors from the class.
     * @throws RuntimeException When there is an exception while getting the constructors.
     * @see #getAllConstructors(Object)
     */
    public static List<Constructor<?>> getAllConstructors(Class<?> clazz) {
        return getAllConstructors((Object) clazz);
    }
    
    /**
     * Gets a list of all constructors from an object.
     *
     * @param object The object.
     * @return The list of all constructors from the object.
     * @throws RuntimeException When there is an exception while getting the constructors.
     */
    public static List<Constructor<?>> getAllConstructors(Object object) {
        try {
            final Class<?> clazz = Objects.requireNonNull(CastUtility.toClass(object));
            
            final List<Constructor<?>> constructors = new ArrayList<>();
            for (Class<?> current = clazz; current != null; current = current.getSuperclass()) {
                constructors.addAll(Arrays.asList(current.getDeclaredConstructors()));
            }
            return constructors;
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Gets a constructor from a class.
     *
     * @param clazz         The class.
     * @param argumentTypes The types of the arguments to the constructor.
     * @return The constructor from the class.
     * @throws RuntimeException When there is an exception while getting the constructor.
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
     * @return The constructor from the object.
     * @throws RuntimeException When there is an exception while getting the constructor.
     */
    public static Constructor<?> getConstructor(Object object, Class<?>... argumentTypes) {
        try {
            final Class<?> clazz = Objects.requireNonNull(CastUtility.toClass(object));
            
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
                        Arrays.stream(constructorArgumentTypes).map(EntityStringUtility::simpleClassString).noneMatch(arg -> arg.equals("IndicateReloadClass")) &&
                        IntStream.range(0, argumentTypes.length).boxed().noneMatch(i ->
                                (argumentTypes[i] == null) ? constructorArgumentTypes[i].isPrimitive() :
                                ((constructorArgumentTypes[i] != argumentTypes[i]) &&
                                        (CastUtility.toPrimitiveClass(constructorArgumentTypes[i]) != CastUtility.toPrimitiveClass(argumentTypes[i])))));
            }).findFirst().orElseThrow(() -> new MethodNotFoundException(EntityStringUtility.simpleConstructorString(clazz, argumentTypes)));
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Gets a list of all fields from a class.
     *
     * @param clazz The class.
     * @return The list of all fields from the class.
     * @throws RuntimeException When there is an exception while getting the fields.
     * @see #getAllFields(Object)
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        return getAllFields((Object) clazz);
    }
    
    /**
     * Gets a list of all fields from an object.
     *
     * @param object The object.
     * @return The list of all fields from the object.
     * @throws RuntimeException When there is an exception while getting the fields.
     */
    public static List<Field> getAllFields(Object object) {
        try {
            final Class<?> clazz = Objects.requireNonNull(CastUtility.toClass(object));
            
            final Map<String, Field> fields = new LinkedHashMap<>();
            for (Class<?> current = clazz; current != null; current = current.getSuperclass()) {
                Arrays.stream(current.getDeclaredFields()).forEachOrdered(e ->
                        fields.put(EntityStringUtility.simpleFieldString(clazz, e.getName()), e));
            }
            return new ArrayList<>(fields.values());
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Gets a field from a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field.
     * @return The field from the class.
     * @throws RuntimeException When there is an exception while getting the field.
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
     * @return The field from the object.
     * @throws RuntimeException When there is an exception while getting the field.
     */
    public static Field getField(Object object, String fieldName) {
        try {
            final Class<?> clazz = Objects.requireNonNull(CastUtility.toClass(object));
            
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException | SecurityException ignored) {
            }
            
            try {
                return clazz.getField(fieldName);
            } catch (NoSuchFieldException | SecurityException ignored) {
            }
            
            return getAllFields(clazz).stream().filter(e -> e.getName().equals(fieldName))
                    .findFirst().orElseThrow(() -> new FieldNotFoundException(EntityStringUtility.simpleFieldString(clazz, fieldName)));
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Gets the value of a field from a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the class.
     * @throws RuntimeException When there is an exception while getting the field value.
     * @see #getFieldValue(Object, String)
     */
    public static Object getFieldValue(Class<?> clazz, String fieldName) {
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
     * @throws RuntimeException When there is an exception while getting the field value.
     * @see #getFieldValue(Class, String)
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Class<?> clazz, Class<T> type, String fieldName) {
        return (T) getFieldValue(clazz, fieldName);
    }
    
    /**
     * Gets the value of a field from an object.
     *
     * @param object    The object.
     * @param fieldName The name of the field to get.
     * @return The value of the field from the object.
     * @throws RuntimeException When there is an exception while getting the field value.
     */
    @SuppressWarnings("deprecation")
    public static Object getFieldValue(Object object, String fieldName) {
        try {
            final Class<?> clazz = Objects.requireNonNull(CastUtility.toClass(object));
            
            try {
                return Whitebox.getInternalState(object, Objects.requireNonNull(fieldName));
                
            } catch (Exception ignored) {
                final Field field = getField(clazz, fieldName);
                if (field == null) {
                    throw new FieldNotFoundException(EntityStringUtility.simpleFieldString(clazz, fieldName));
                }
                
                final boolean isAccessible = field.isAccessible();
                try {
                    field.setAccessible(true);
                    return field.get(object);
                } finally {
                    field.setAccessible(isAccessible);
                }
            }
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
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
     * @throws RuntimeException When there is an exception while getting the field value.
     * @see #getFieldValue(Object, String)
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object object, Class<T> type, String fieldName) {
        return (T) getFieldValue(object, fieldName);
    }
    
    /**
     * Sets the value of a field from a class.
     *
     * @param clazz     The class.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field from the class was successfully set or not.
     * @throws RuntimeException When there is an exception while setting the field value.
     * @see #setFieldValue(Object, String, Object)
     */
    public static boolean setFieldValue(Class<?> clazz, String fieldName, Object value) {
        return setFieldValue((Object) clazz, fieldName, value);
    }
    
    /**
     * Sets the value of a field from an object.
     *
     * @param object    The object.
     * @param fieldName The name of the field to set.
     * @param value     The value to set.
     * @return Whether the field from the object was successfully set or not.
     * @throws RuntimeException When there is an exception while setting the field value.
     */
    @SuppressWarnings("deprecation")
    public static boolean setFieldValue(Object object, String fieldName, Object value) {
        try {
            final Class<?> clazz = Objects.requireNonNull(CastUtility.toClass(object));
            
            try {
                Whitebox.setInternalState(object, Objects.requireNonNull(fieldName), value);
                return Objects.equals(Whitebox.getInternalState(object, fieldName), value);
                
            } catch (Exception ignored) {
                final Field field = getField(clazz, fieldName);
                if (field == null) {
                    throw new FieldNotFoundException(EntityStringUtility.simpleFieldString(clazz, fieldName));
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
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Invokes a method from a class.
     *
     * @param clazz      The class.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the method invocation.
     * @throws RuntimeException When there is an exception while invoking the method.
     * @see #invokeMethod(Object, String, Object...)
     */
    public static Object invokeMethod(Class<?> clazz, String methodName, Object... arguments) {
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
     * @throws RuntimeException When there is an exception while invoking the method.
     * @see #invokeMethod(Class, String, Object...)
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Class<?> clazz, Class<T> returnType, String methodName, Object... arguments) {
        return (T) invokeMethod(clazz, methodName, arguments);
    }
    
    /**
     * Invokes a method from an object.
     *
     * @param object     The object.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the method invocation.
     * @throws RuntimeException When there is an exception while invoking the method.
     */
    @SuppressWarnings("deprecation")
    public static Object invokeMethod(Object object, String methodName, Object... arguments) {
        try {
            final Class<?> clazz = Objects.requireNonNull(CastUtility.toClass(object));
            final Class<?>[] argumentTypes = Arrays.stream(arguments).map(CastUtility::toClass).toArray(Class<?>[]::new);
            
            try {
                return Whitebox.invokeMethod(object, Objects.requireNonNull(methodName), arguments);
                
            } catch (Exception ignored) {
                final Method method = getMethod(clazz, methodName, argumentTypes);
                if (method == null) {
                    throw new MethodNotFoundException(EntityStringUtility.simpleMethodString(clazz, methodName, argumentTypes));
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
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
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
     * @throws RuntimeException When there is an exception while invoking the method.
     * @see #invokeMethod(Object, String, Object...)
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Object object, Class<T> returnType, String methodName, Object... arguments) {
        return (T) invokeMethod(object, methodName, arguments);
    }
    
    /**
     * Invokes a constructor.
     *
     * @param clazz     The class.
     * @param arguments The arguments to the constructor.
     * @param <T>       The type of the class.
     * @return The constructed instance of the class.
     * @throws RuntimeException When there is an exception while invoking the constructor.
     */
    @SuppressWarnings({"deprecation", "unchecked"})
    public static <T> T invokeConstructor(Class<T> clazz, Object... arguments) {
        try {
            Objects.requireNonNull(clazz);
            final Class<?>[] argumentTypes = Arrays.stream(arguments).map(CastUtility::toClass).toArray(Class<?>[]::new);
            
            try {
                return Whitebox.invokeConstructor(clazz, arguments);
                
            } catch (Exception ignored) {
                final Constructor<T> constructor = (Constructor<T>) getConstructor(clazz, argumentTypes);
                if ((constructor == null) || (constructor.getDeclaringClass() != clazz)) {
                    throw new ConstructorNotFoundException(EntityStringUtility.simpleConstructorString(clazz, argumentTypes));
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
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
        }
    }
    
    /**
     * Invokes a default method of an interface and returns the result.
     *
     * @param clazz      The class.
     * @param methodName The name of the method.
     * @param arguments  The arguments to the method.
     * @return The result of the invocation.
     * @throws RuntimeException When there is an exception while invoking the interface default method.
     */
    @SuppressWarnings("SuspiciousInvocationHandlerImplementation")
    public static Object invokeInterfaceDefaultMethod(Class<?> clazz, String methodName, Object... arguments) {
        try {
            Objects.requireNonNull(clazz);
            final Class<?>[] argumentTypes = Arrays.stream(arguments).map(CastUtility::toClass).toArray(Class<?>[]::new);
            
            if (!clazz.isInterface()) {
                throw new InvalidClassException(EntityStringUtility.simpleClassString(clazz) + " is not an interface");
            }
            
            final Method interfaceMethod = getMethod(clazz, methodName, argumentTypes);
            if (interfaceMethod == null) {
                throw new MethodNotFoundException(EntityStringUtility.simpleMethodString(clazz, methodName, argumentTypes));
            }
            
            return (getFieldValue(MethodHandles.Lookup.class, MethodHandles.Lookup.class, "IMPL_LOOKUP"))
                    .unreflectSpecial(interfaceMethod, interfaceMethod.getDeclaringClass())
                    .bindTo(Proxy.newProxyInstance(
                            Thread.currentThread().getContextClassLoader(), new Class[] {clazz},
                            (Object proxy, Method method, Object[] args) -> null))
                    .invokeWithArguments(arguments);
            
        } catch (Throwable e) {
            throw e.getClass().equals(RuntimeException.class) ? (RuntimeException) e : new RuntimeException(e);
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
     * @throws RuntimeException When there is an exception while invoking the interface default method.
     * @see #invokeInterfaceDefaultMethod(Class, String, Object...)
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeInterfaceDefaultMethod(Class<?> clazz, Class<T> returnType, String methodName, Object... arguments) {
        return (T) invokeInterfaceDefaultMethod(clazz, methodName, arguments);
    }
    
}
