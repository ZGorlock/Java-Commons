/*
 * File:    TestAccessTest.java
 * Package: commons.test
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import commons.math.number.BoundUtility;
import commons.object.CastUtility;
import commons.object.collection.ArrayUtility;
import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
import commons.object.string.EntityStringUtility;
import commons.object.string.StringUtility;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of TestAccess.
 *
 * @see TestAccess
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({TestAccess.class})
public class TestAccessTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(TestAccessTest.class);
    
    
    //Static Fields
    
    /**
     * A list of classes to use for testing.
     */
    static final Class<?>[] classes = new Class[] {TestClass.class, TestSubClass.class};
    
    /**
     * A list of instances to use for testing.
     */
    static final Object[] instances = new Object[] {new TestClass(), new TestSubClass(), Mockito.spy(new TestClass()), Mockito.spy(new TestSubClass())};
    
    
    //Initialization
    
    /**
     * The JUnit class setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @BeforeClass
    public static void setupClass() throws Exception {
    }
    
    /**
     * The JUnit class cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @AfterClass
    public static void cleanupClass() throws Exception {
    }
    
    /**
     * The JUnit setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Before
    public void setup() throws Exception {
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @After
    public void cleanup() throws Exception {
    }
    
    
    //Tests
    
    /**
     * JUnit test of constants.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of getClass.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getClass(String)
     * @see TestAccess#getClass(Class, String)
     */
    @Test
    public void testGetClass() throws Exception {
        Class<?> retrievedClass;
        
        //standard
        retrievedClass = TestAccess.getClass(TestAccessTest.class, "TestClass");
        Assert.assertNotNull(retrievedClass);
        Assert.assertEquals(TestClass.class, retrievedClass);
        TestUtils.assertFieldExists(retrievedClass, "field8");
        TestUtils.assertConstructorExists(retrievedClass, String.class, int.class, Long.class, boolean.class, Exception.class);
        TestUtils.assertMethodExists(retrievedClass, "method10");
        TestUtils.assertMethodExists(retrievedClass, "method3", String.class);
        TestUtils.assertMethodDoesNotExist(retrievedClass, "subMethod", String.class, Long.class);
        
        //qualified name
        retrievedClass = TestAccess.getClass("commons.test.TestAccessTest$TestSubClass");
        Assert.assertNotNull(retrievedClass);
        Assert.assertEquals(TestSubClass.class, retrievedClass);
        TestUtils.assertFieldExists(retrievedClass, "field9");
        TestUtils.assertConstructorExists(retrievedClass, String.class, int.class, Long.class, boolean.class, Exception.class);
        TestUtils.assertMethodExists(retrievedClass, "method10");
        TestUtils.assertMethodExists(retrievedClass, "method3", String.class);
        TestUtils.assertMethodExists(retrievedClass, "subMethod", String.class, Long.class);
        
        //not a class
        TestUtils.assertException(RuntimeException.class, "java.io.InvalidClassException: TestInterface is not a class", () ->
                TestAccess.getClass(TestAccessTest.class, "TestInterface"));
        TestUtils.assertException(RuntimeException.class, "java.io.InvalidClassException: Enum0 is not a class", () ->
                TestAccess.getClass(TestClass.class, "Enum0"));
        
        //does not exist
        TestUtils.assertException(RuntimeException.class, "java.lang.ClassNotFoundException: commons.test.TestAccessTest$TestClass$MissingClass", () ->
                TestAccess.getClass(TestClass.class, "MissingClass"));
        TestUtils.assertException(RuntimeException.class, "java.lang.ClassNotFoundException: commons.test.TestAccessTest$TestClass$MissingClass", () ->
                TestAccess.getClass("commons.test.TestAccessTest$TestClass$MissingClass"));
        
        //invalid
        Stream.of(TestClass.class, null).forEach(clazz ->
                Stream.of("", null).forEach(innerClassName ->
                        TestUtils.assertException(RuntimeException.class, StringUtility.format("java.lang.ClassNotFoundException: {}${}", EntityStringUtility.classString(clazz), innerClassName), () ->
                                TestAccess.getClass(clazz, innerClassName))));
        TestUtils.assertException(RuntimeException.class, "java.lang.ClassNotFoundException: ", () ->
                TestAccess.getClass(""));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getClass(null));
    }
    
    /**
     * JUnit test of getInterface.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getInterface(String)
     * @see TestAccess#getInterface(Class, String)
     */
    @Test
    public void testGetInterface() throws Exception {
        Class<?> retrievedInterface;
        
        //standard
        retrievedInterface = TestAccess.getInterface(TestAccessTest.class, "TestInterface");
        Assert.assertNotNull(retrievedInterface);
        Assert.assertEquals(TestInterface.class, retrievedInterface);
        TestUtils.assertFieldExists(retrievedInterface, "BASE_NAME");
        TestUtils.assertMethodExists(retrievedInterface, "getName");
        Assert.assertEquals("TestInterface", TestAccess.invokeInterfaceDefaultMethod(retrievedInterface, "getName"));
        
        //qualified name
        retrievedInterface = TestAccess.getInterface("commons.test.TestAccessTest$TestInterface");
        Assert.assertNotNull(retrievedInterface);
        Assert.assertEquals(TestInterface.class, retrievedInterface);
        TestUtils.assertFieldExists(retrievedInterface, "BASE_NAME");
        TestUtils.assertMethodExists(retrievedInterface, "getName");
        Assert.assertEquals("TestInterface", TestAccess.invokeInterfaceDefaultMethod(retrievedInterface, "getName"));
        
        //not an interface
        TestUtils.assertException(RuntimeException.class, "java.io.InvalidClassException: TestClass is not an interface", () ->
                TestAccess.getInterface(TestAccessTest.class, "TestClass"));
        TestUtils.assertException(RuntimeException.class, "java.io.InvalidClassException: Enum0 is not an interface", () ->
                TestAccess.getInterface(TestClass.class, "Enum0"));
        
        //does not exist
        TestUtils.assertException(RuntimeException.class, "java.lang.ClassNotFoundException: commons.test.TestAccessTest$TestClass$MissingInterface", () ->
                TestAccess.getInterface(TestClass.class, "MissingInterface"));
        TestUtils.assertException(RuntimeException.class, "java.lang.ClassNotFoundException: commons.test.TestAccessTest$TestClass$MissingInterface", () ->
                TestAccess.getInterface("commons.test.TestAccessTest$TestClass$MissingInterface"));
        
        //invalid
        Stream.of(TestClass.class, null).forEach(clazz ->
                Stream.of("", null).forEach(innerInterfaceName ->
                        TestUtils.assertException(RuntimeException.class, StringUtility.format("java.lang.ClassNotFoundException: {}${}", EntityStringUtility.classString(clazz), innerInterfaceName), () ->
                                TestAccess.getInterface(clazz, innerInterfaceName))));
        TestUtils.assertException(RuntimeException.class, "java.lang.ClassNotFoundException: ", () ->
                TestAccess.getInterface(""));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getInterface(null));
    }
    
    /**
     * JUnit test of getEnum.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getEnum(String)
     * @see TestAccess#getEnum(Class, String)
     */
    @Test
    public void testGetEnum() throws Exception {
        Class<?> retrievedEnum;
        
        //standard
        retrievedEnum = TestAccess.getEnum(TestClass.class, "Enum0");
        Assert.assertNotNull(retrievedEnum);
        Assert.assertEquals(TestClass.Enum0.class, retrievedEnum);
        Assert.assertEquals(3, retrievedEnum.getEnumConstants().length);
        Assert.assertEquals("PUBLIC_VALUE_1 | PUBLIC_VALUE_2 | PUBLIC_VALUE_3",
                Arrays.stream(retrievedEnum.getEnumConstants()).map(Object::toString).collect(Collectors.joining(" | ")));
        TestUtils.assertMethodExists(retrievedEnum, "getEnumType");
        Assert.assertEquals("Public", ((TestClass.Enum0) retrievedEnum.getEnumConstants()[0]).getEnumType());
        
        //qualified name
        retrievedEnum = TestAccess.getEnum("commons.test.TestAccessTest$TestClass$Enum1");
        Assert.assertNotNull(retrievedEnum);
        Assert.assertEquals(TestClass.Enum1.class, retrievedEnum);
        Assert.assertEquals(3, retrievedEnum.getEnumConstants().length);
        Assert.assertEquals("PRIVATE_VALUE_1 | PRIVATE_VALUE_2 | PRIVATE_VALUE_3",
                Arrays.stream(retrievedEnum.getEnumConstants()).map(Object::toString).collect(Collectors.joining(" | ")));
        TestUtils.assertMethodExists(retrievedEnum, "getEnumType");
        Assert.assertEquals("Private", TestAccess.invokeMethod(TestClass.Enum1.class, "getEnumType"));
        
        //not an enum
        TestUtils.assertException(RuntimeException.class, "java.io.InvalidClassException: TestClass is not an enum", () ->
                TestAccess.getEnum(TestAccessTest.class, "TestClass"));
        TestUtils.assertException(RuntimeException.class, "java.io.InvalidClassException: TestInterface is not an enum", () ->
                TestAccess.getEnum(TestAccessTest.class, "TestInterface"));
        
        //does not exist
        TestUtils.assertException(RuntimeException.class, "java.lang.ClassNotFoundException: commons.test.TestAccessTest$TestClass$MissingEnum", () ->
                TestAccess.getEnum(TestClass.class, "MissingEnum"));
        TestUtils.assertException(RuntimeException.class, "java.lang.ClassNotFoundException: commons.test.TestAccessTest$TestClass$MissingEnum", () ->
                TestAccess.getEnum("commons.test.TestAccessTest$TestClass$MissingEnum"));
        
        //invalid
        Stream.of(TestClass.class, null).forEach(clazz ->
                Stream.of("", null).forEach(innerEnumName ->
                        TestUtils.assertException(RuntimeException.class, StringUtility.format("java.lang.ClassNotFoundException: {}${}", EntityStringUtility.classString(clazz), innerEnumName), () ->
                                TestAccess.getEnum(clazz, innerEnumName))));
        TestUtils.assertException(RuntimeException.class, "java.lang.ClassNotFoundException: ", () ->
                TestAccess.getEnum(""));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getEnum(null));
    }
    
    /**
     * JUnit test of getAllMethods.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getAllMethods(Class)
     * @see TestAccess#getAllMethods(Object)
     */
    @Test
    public void testGetAllMethods() throws Exception {
        final List<String> testClassMethods = List.of(
                "String getName()",
                "void voidMethod(int, Long, StringBuilder)",
                "long method7()",
                "int method8(String)",
                "float method9(int)",
                "boolean method10()",
                "String method11(boolean, String, float, char[])",
                "void staticVoidMethod(boolean, Long, StringBuilder)",
                "double method1()",
                "boolean method3(String)",
                "byte method5(boolean, int, BigDecimal)"
        );
        final List<String> testSubClassMethods = List.of(
                "boolean subMethod(String, Long)",
                "String staticSubMethod(String, long)"
        );
        final List<String> objectMethods = List.of(
                "void registerNatives()",
                "Class getClass()",
                "int hashCode()",
                "boolean equals(Object)",
                "Object clone()",
                "String toString()",
                "void notify()",
                "void notifyAll()",
                "void wait()",
                "void wait(long)",
                "void wait(long, int)",
                "void finalize()"
        );
        
        final Function<Method, String> methodNameGetter = (Method method) ->
                StringUtility.containsAny(method.toString(), new String[] {"IndicateReloadClass", "MockitoMock"}) ? null :
                EntityStringUtility.simpleClassString(method.getReturnType()) + ' ' + EntityStringUtility.simpleMethodString(method).replaceAll("^.*::", "");
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
            final List<Method> methods = (entity instanceof Class<?>) ? TestAccess.getAllMethods((Class<?>) entity) : TestAccess.getAllMethods(entity);
            final List<String> methodStrings = methods.stream().map(methodNameGetter).filter(Objects::nonNull).distinct().collect(Collectors.toList());
            final List<String> expectedMethods = Stream.of(testClassMethods, objectMethods,
                            (((entity instanceof TestSubClass) || (entity == TestSubClass.class)) ? testSubClassMethods : ListUtility.emptyList()))
                    .flatMap(Collection::stream).map(Object::toString).collect(Collectors.toList());
            TestUtils.assertListEquals(expectedMethods, methodStrings, false);
        });
        
        //invalid
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getAllMethods(null));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getAllMethods((Object) null));
    }
    
    /**
     * JUnit test of getMethod.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getMethod(Class, String, Class[])
     * @see TestAccess#getMethod(Object, String, Class[])
     */
    @Test
    public void testGetMethod() throws Exception {
        final Function<Object[], Method> methodGetter = (Object[] params) -> {
            final Object caller = params[0];
            final String methodName = (String) params[1];
            final Class<?>[] arguments = (Class<?>[]) params[2];
            return (caller instanceof Class<?>) ?
                   TestAccess.getMethod((Class<?>) caller, methodName, arguments) :
                   TestAccess.getMethod(caller, methodName, arguments);
        };
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity ->
                List.of(new ImmutablePair<>("voidMethod", new Class<?>[] {int.class, Long.class, StringBuilder.class}),
                        new ImmutablePair<>("voidMethod", new Class<?>[] {Integer.class, long.class, StringBuilder.class}),
                        new ImmutablePair<>("method7", new Class<?>[] {}),
                        new ImmutablePair<>("method8", new Class<?>[] {String.class}),
                        new ImmutablePair<>("method9", new Class<?>[] {int.class}),
                        new ImmutablePair<>("method10", new Class<?>[] {}),
                        new ImmutablePair<>("method11", new Class<?>[] {boolean.class, String.class, float.class, char[].class})
                ).forEach(methodNameArgumentTypesPair ->
                        Assert.assertNotNull(methodGetter.apply(new Object[] {entity, methodNameArgumentTypesPair.getLeft(), methodNameArgumentTypesPair.getRight()}))));
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity ->
                List.of(new ImmutablePair<>("voidMethod", new Class<?>[] {int.class, long.class, String.class}),
                        new ImmutablePair<>("voidMethod", new Class<?>[] {Integer.class, Long.class, String.class}),
                        new ImmutablePair<>("method7", new Class<?>[] {int.class}),
                        new ImmutablePair<>("method8", new Class<?>[] {int.class}),
                        new ImmutablePair<>("missingMethod", new Class<?>[] {})
                ).forEach(methodNameArgumentTypesPair ->
                        TestUtils.assertException(RuntimeException.class, StringUtility.format("org.powermock.reflect.exceptions.MethodNotFoundException: {}", EntityStringUtility.simpleMethodString(entity, methodNameArgumentTypesPair.getLeft(), methodNameArgumentTypesPair.getRight())), () ->
                                methodGetter.apply(new Object[] {entity, methodNameArgumentTypesPair.getLeft(), methodNameArgumentTypesPair.getRight()}))));
        
        //invalid
        Stream.of(classes, instances, new Object[] {null}).flatMap(Arrays::stream).forEach(entity ->
                Stream.of("", null).forEach(methodName ->
                        Stream.of(String.class, null).map(argumentType -> new Class<?>[] {argumentType}).forEach(argumentTypes ->
                                TestUtils.assertException(RuntimeException.class, (((entity == null) || (methodName == null)) ? "java.lang.NullPointerException" : StringUtility.format("org.powermock.reflect.exceptions.MethodNotFoundException: {}", EntityStringUtility.simpleMethodString(entity, methodName, argumentTypes[0]))), () ->
                                        methodGetter.apply(new Object[] {entity, methodName, argumentTypes})))));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getMethod(null, null, (Class<?>) null));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getMethod((Object) null, null, (Class<?>) null));
    }
    
    /**
     * JUnit test of getAllConstructors.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getAllConstructors(Class)
     * @see TestAccess#getAllConstructors(Object)
     */
    @Test
    public void testGetAllConstructors() throws Exception {
        final List<String> testClassConstructors = List.of(
                "TestClass()",
                "TestClass(String, Exception, int, Long, boolean)",
                "TestClass(Exception, String, int, Long, boolean)",
                "TestClass(String, int, Long, boolean, Exception)",
                "TestClass(Exception, int, Long, boolean, String)"
        );
        final List<String> testSubClassConstructors = List.of(
                "TestSubClass()",
                "TestSubClass(int, Long, boolean, String, Exception)"
        );
        final List<String> objectConstructors = List.of(
                "Object()"
        );
        
        final Function<Constructor<?>, String> constructorNameGetter = (Constructor<?> constructor) ->
                StringUtility.containsAny(constructor.toString(), new String[] {"IndicateReloadClass", "MockitoMock"}) ? null :
                EntityStringUtility.simpleConstructorString(constructor).replaceAll("^.*::", "");
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
            final List<Constructor<?>> constructors = (entity instanceof Class<?>) ? TestAccess.getAllConstructors((Class<?>) entity) : TestAccess.getAllConstructors(entity);
            final List<String> constructorStrings = constructors.stream().map(constructorNameGetter).filter(Objects::nonNull).distinct().collect(Collectors.toList());
            final List<String> expectedConstructors = Stream.of(testClassConstructors, objectConstructors,
                            (((entity instanceof TestSubClass) || (entity == TestSubClass.class)) ? testSubClassConstructors : ListUtility.emptyList()))
                    .flatMap(Collection::stream).map(Object::toString).collect(Collectors.toList());
            TestUtils.assertListEquals(expectedConstructors, constructorStrings, false);
        });
        
        //invalid
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getAllConstructors(null));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getAllConstructors((Object) null));
    }
    
    /**
     * JUnit test of getConstructor.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getConstructor(Class, Class[])
     * @see TestAccess#getConstructor(Object, Class[])
     */
    @Test
    public void testGetConstructor() throws Exception {
        final Function<Object[], Constructor<?>> constructorGetter = (Object[] params) -> {
            final Object caller = params[0];
            final Class<?>[] arguments = (Class<?>[]) params[1];
            return (caller instanceof Class<?>) ?
                   TestAccess.getConstructor((Class<?>) caller, arguments) :
                   TestAccess.getConstructor(caller, arguments);
        };
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity ->
                List.of(new Class<?>[] {},
                        new Class<?>[] {String.class, Exception.class, int.class, Long.class, boolean.class},
                        new Class<?>[] {String.class, Exception.class, Integer.class, Long.class, Boolean.class},
                        new Class<?>[] {String.class, int.class, Long.class, boolean.class, Exception.class},
                        new Class<?>[] {Exception.class, int.class, Long.class, boolean.class, String.class}
                ).forEach(argumentTypes ->
                        Assert.assertNotNull(constructorGetter.apply(new Object[] {entity, argumentTypes}))));
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity ->
                List.of(new Class<?>[] {StringBuilder.class},
                        new Class<?>[] {StringBuilder.class, Exception.class, int.class, Long.class, boolean.class},
                        new Class<?>[] {StringBuilder.class, Exception.class, Integer.class, Long.class, Boolean.class}
                ).forEach(argumentTypes ->
                        TestUtils.assertException(RuntimeException.class, StringUtility.format("org.powermock.reflect.exceptions.MethodNotFoundException: {}", EntityStringUtility.simpleConstructorString(entity, argumentTypes)), () ->
                                constructorGetter.apply(new Object[] {entity, argumentTypes}))));
        
        //invalid
        Stream.of(classes, instances, new Object[] {null}).flatMap(Arrays::stream).forEach(entity ->
                Stream.of(String.class, null).map(argumentType -> new Class<?>[] {argumentType}).forEach(argumentTypes ->
                        TestUtils.assertException(RuntimeException.class, ((entity == null) ? "java.lang.NullPointerException" : StringUtility.format("org.powermock.reflect.exceptions.MethodNotFoundException: {}", EntityStringUtility.simpleConstructorString(entity, argumentTypes[0]))), () ->
                                constructorGetter.apply(new Object[] {entity, argumentTypes}))));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getConstructor(null, (Class<?>) null));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getConstructor((Object) null, (Class<?>) null));
    }
    
    /**
     * JUnit test of getAllFields.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getAllFields(Class)
     * @see TestAccess#getAllFields(Object)
     */
    @Test
    public void testGetAllFields() throws Exception {
        final List<String> testClassFields = List.of(
                "int field0",
                "double field1",
                "String field2",
                "boolean field3",
                "String field4",
                "byte field5",
                "String field6",
                "long field7",
                "int field8",
                "float field9",
                "boolean field10",
                "String field11",
                "Object[] arguments"
        );
        final List<String> testSubClassFields = List.of(
                "int field12",
                "float field13"
        );
        
        final Function<Field, String> fieldNameGetter = (Field field) ->
                StringUtility.containsAny(field.toString(), new String[] {"IndicateReloadClass", "MockitoMock"}) ? null :
                EntityStringUtility.simpleClassString(field.getType()) + ' ' + EntityStringUtility.simpleFieldString(field).replaceAll("^.*::", "");
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
            final List<Field> fields = (entity instanceof Class<?>) ? TestAccess.getAllFields((Class<?>) entity) : TestAccess.getAllFields(entity);
            final List<String> fieldStrings = fields.stream().map(fieldNameGetter).filter(Objects::nonNull).distinct().collect(Collectors.toList());
            final List<String> expectedFields = Stream.of(testClassFields,
                            (((entity instanceof TestSubClass) || (entity == TestSubClass.class)) ? testSubClassFields : ListUtility.emptyList()))
                    .flatMap(Collection::stream).map(Object::toString).collect(Collectors.toList());
            TestUtils.assertListEquals(expectedFields, fieldStrings, false);
        });
        
        //invalid
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getAllFields(null));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getAllFields((Object) null));
    }
    
    /**
     * JUnit test of getField.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getField(Class, String)
     * @see TestAccess#getField(Object, String)
     */
    @Test
    public void testGetField() throws Exception {
        final Function<Object[], Field> fieldGetter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            return (caller instanceof Class<?>) ?
                   TestAccess.getField((Class<?>) caller, fieldName) :
                   TestAccess.getField(caller, fieldName);
        };
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity ->
                List.of("field2",
                        "field5",
                        "field7",
                        "field10"
                ).forEach(fieldName ->
                        Assert.assertNotNull(fieldGetter.apply(new Object[] {entity, fieldName}))));
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity ->
                List.of("Field7",
                        "field15",
                        "missingField"
                ).forEach(fieldName ->
                        TestUtils.assertException(RuntimeException.class, StringUtility.format("org.powermock.reflect.exceptions.FieldNotFoundException: {}", EntityStringUtility.simpleFieldString(entity, fieldName)), () ->
                                fieldGetter.apply(new Object[] {entity, fieldName}))));
        
        //invalid
        Stream.of(classes, instances, new Object[] {null}).flatMap(Arrays::stream).forEach(entity ->
                Stream.of("", null).forEach(fieldName ->
                        TestUtils.assertException(RuntimeException.class, (((entity == null) || (fieldName == null)) ? "java.lang.NullPointerException" : StringUtility.format("org.powermock.reflect.exceptions.FieldNotFoundException: {}", EntityStringUtility.simpleFieldString(entity, fieldName))), () ->
                                fieldGetter.apply(new Object[] {entity, fieldName}))));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getField(null, null));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.getField((Object) null, null));
    }
    
    /**
     * JUnit test of getFieldValue.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#getFieldValue(Class, String)
     * @see TestAccess#getFieldValue(Class, Class, String)
     * @see TestAccess#getFieldValue(Object, String)
     * @see TestAccess#getFieldValue(Object, Class, String)
     */
    @Test
    public void testGetFieldValue() throws Exception {
        final Map<Object, Object[]> values = MapUtility.mapOf(
                new ImmutablePair<>(EntityStringUtility.classString(classes[0]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test"}),
                new ImmutablePair<>(EntityStringUtility.classString(classes[1]), new Object[] {18, 6.4488121, "test", true, "subclass tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test", -644, -116.103f}));
        final Map<Object, Object[]> mockValues = MapUtility.mapOf(
                new ImmutablePair<>(EntityStringUtility.classString(classes[0]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test"}),
                new ImmutablePair<>(EntityStringUtility.classString(classes[1]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test", -644, -116.103f}));
        
        final Function<Object[], Object> fieldValueGetter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            return (caller instanceof Class<?>) ?
                   TestAccess.getFieldValue((Class<?>) caller, fieldName) :
                   TestAccess.getFieldValue(caller, fieldName);
        };
        final Consumer<Object[]> getFieldValueValidator = (Object[] params) -> {
            final Object caller = params[0];
            final int index = (int) params[1];
            final String fieldName = "field" + index;
            final Object expected = (MockUtil.isSpy(caller) ? mockValues : values).get(EntityStringUtility.classString(params[0]))[index];
            TestUtils.assertNoException(() ->
                    Assert.assertEquals(expected, fieldValueGetter.apply(new Object[] {caller, fieldName})));
        };
        
        //standard
        IntStream.rangeClosed(0, values.get(EntityStringUtility.classString(classes[1])).length).forEach(i -> {
            final String fieldName = "field" + i;
            Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
                if (!BoundUtility.inArrayBounds(i, values.get(EntityStringUtility.classString(entity)))) {
                    TestUtils.assertException(RuntimeException.class, StringUtility.format("org.powermock.reflect.exceptions.FieldNotFoundException: {}", EntityStringUtility.simpleFieldString(entity, fieldName)), () ->
                            fieldValueGetter.apply(new Object[] {entity, fieldName}));
                } else {
                    final Field field = TestAccess.getField(entity, fieldName);
                    if ((entity instanceof Class<?>) && !Modifier.isStatic(field.getModifiers())) {
                        TestUtils.assertException(RuntimeException.class, StringUtility.format("java.lang.IllegalArgumentException: Can not set {} field {} to java.lang.Class", ((Modifier.isFinal(field.getModifiers()) ? "final " : "") + EntityStringUtility.classString(field.getType())), EntityStringUtility.fieldString(field).replace("::", ".")), () ->
                                fieldValueGetter.apply(new Object[] {entity, fieldName}));
                    } else {
                        getFieldValueValidator.accept(new Object[] {entity, i});
                    }
                }
            });
        });
        
        //type
        Arrays.stream(classes).forEach(clazz -> TestUtils.assertNoException(() -> {
            int field0 = TestAccess.getFieldValue(clazz, int.class, "field0");
            double field1 = TestAccess.getFieldValue(clazz, double.class, "field1");
            String field4 = TestAccess.getFieldValue(clazz, String.class, "field4");
            byte field5 = TestAccess.getFieldValue(clazz, byte.class, "field5");
            TestUtils.assertException(ClassCastException.class, "class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')", () -> {
                String incorrect = TestAccess.getFieldValue(clazz, String.class, "field0");
            });
        }));
        Arrays.stream(instances).forEach(instance -> TestUtils.assertNoException(() -> {
            String field6 = TestAccess.getFieldValue(instance, String.class, "field6");
            long field7 = TestAccess.getFieldValue(instance, long.class, "field7");
            float field9 = TestAccess.getFieldValue(instance, float.class, "field9");
            boolean field10 = TestAccess.getFieldValue(instance, boolean.class, "field10");
            TestUtils.assertException(ClassCastException.class, "class java.lang.String cannot be cast to class java.lang.Boolean (java.lang.String and java.lang.Boolean are in module java.base of loader 'bootstrap')", () -> {
                boolean incorrect = TestAccess.getFieldValue(instance, boolean.class, "field6");
            });
        }));
        
        //invalid
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity ->
                Stream.of("", null).forEach(fieldName ->
                        TestUtils.assertException(RuntimeException.class, (((entity == null) || (fieldName == null)) ? "java.lang.NullPointerException" : StringUtility.format("org.powermock.reflect.exceptions.FieldNotFoundException: {}", EntityStringUtility.simpleFieldString(entity, fieldName))), () ->
                                fieldValueGetter.apply(new Object[] {entity, fieldName}))));
        Stream.of("field0", "", null).forEach(fieldName -> {
            Stream.of(int.class, null).forEach(fieldType -> {
                TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                        TestAccess.getFieldValue(null, fieldType, fieldName));
                TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                        TestAccess.getFieldValue((Object) null, fieldType, fieldName));
            });
            TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                    TestAccess.getFieldValue(null, fieldName));
            TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                    TestAccess.getFieldValue((Object) null, fieldName));
        });
    }
    
    /**
     * JUnit test of setFieldValue.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#setFieldValue(Class, String, Object)
     * @see TestAccess#setFieldValue(Object, String, Object)
     */
    @Test
    public void testSetFieldValue() throws Exception {
        final Map<Object, Object[]> values = MapUtility.mapOf(
                new ImmutablePair<>(EntityStringUtility.classString(classes[0]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test"}),
                new ImmutablePair<>(EntityStringUtility.classString(classes[1]), new Object[] {18, 6.4488121, "test", true, "subclass tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test", -644, -116.103f}));
        final Map<Object, Object[]> mockValues = MapUtility.mapOf(
                new ImmutablePair<>(EntityStringUtility.classString(classes[0]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test"}),
                new ImmutablePair<>(EntityStringUtility.classString(classes[1]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test", -644, -116.103f}));
        final Map<Object, Object[]> setValues = MapUtility.mapOf(
                new ImmutablePair<>(EntityStringUtility.classString(classes[0]), new Object[] {7, 0.221548773, "different", false, "tset2", (byte) 3, "an even other test", 156423157842311L, 1568, 3.46f, false, "the last test"}),
                new ImmutablePair<>(EntityStringUtility.classString(classes[1]), new Object[] {10, 6.4488121, "another different", true, "subclass tset2", (byte) 5, "an even other test", 156423157842311L, 1568, 3.46f, false, "the last test", 61, 8.9f}));
        
        final Function<Object[], Object> fieldValueGetter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            return (caller instanceof Class<?>) ?
                   TestAccess.getFieldValue((Class<?>) caller, fieldName) :
                   TestAccess.getFieldValue(caller, fieldName);
        };
        final Function<Object[], Boolean> fieldValueSetter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            final Object set = params[2];
            return (caller instanceof Class<?>) ?
                   TestAccess.setFieldValue((Class<?>) caller, fieldName, set) :
                   TestAccess.setFieldValue(caller, fieldName, set);
        };
        final Consumer<Object[]> setFieldValueValidator = (Object[] params) -> {
            final Object caller = params[0];
            final int index = (int) params[1];
            final String fieldName = "field" + index;
            final Object expected = (MockUtil.isSpy(caller) ? mockValues : values).get(EntityStringUtility.classString(params[0]))[index];
            final Object set = setValues.get(EntityStringUtility.classString(params[0]))[index];
            Assert.assertEquals(expected, fieldValueGetter.apply(new Object[] {caller, fieldName}));
            Assert.assertTrue(fieldValueSetter.apply(new Object[] {caller, fieldName, set}));
            Assert.assertEquals(set, fieldValueGetter.apply(new Object[] {caller, fieldName}));
            Assert.assertTrue(fieldValueSetter.apply(new Object[] {caller, fieldName, expected}));
            Assert.assertEquals(expected, fieldValueGetter.apply(new Object[] {caller, fieldName}));
        };
        
        //standard
        IntStream.rangeClosed(0, values.get(EntityStringUtility.classString(classes[1])).length).forEach(i -> {
            final String fieldName = "field" + i;
            Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
                if (!BoundUtility.inArrayBounds(i, values.get(EntityStringUtility.classString(entity)))) {
                    TestUtils.assertException(RuntimeException.class, StringUtility.format("org.powermock.reflect.exceptions.FieldNotFoundException: {}", EntityStringUtility.simpleFieldString(entity, fieldName)), () ->
                            fieldValueSetter.apply(new Object[] {entity, fieldName, null}));
                } else {
                    final Field field = TestAccess.getField(entity, fieldName);
                    if ((entity instanceof Class<?>) && !Modifier.isStatic(field.getModifiers())) {
                        TestUtils.assertException(RuntimeException.class, StringUtility.format("java.lang.IllegalArgumentException: Can not set {} field {} to java.lang.Class", ((Modifier.isFinal(field.getModifiers()) ? "final " : "") + EntityStringUtility.classString(field.getType())), EntityStringUtility.fieldString(field).replace("::", ".")), () ->
                                fieldValueSetter.apply(new Object[] {entity, fieldName, null}));
                    } else {
                        setFieldValueValidator.accept(new Object[] {entity, i});
                    }
                }
            });
        });
        
        //invalid
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
            final boolean isClass = (entity instanceof Class<?>);
            MapUtility.mapOf(
                    new ImmutablePair<>((isClass ? "field0" : "field8"), null),
                    new ImmutablePair<>((isClass ? "field3" : "field10"), "test"),
                    new ImmutablePair<>((isClass ? "field4" : "field11"), Boolean.FALSE)
            ).forEach((fieldName, setValue) -> {
                final Field field = TestAccess.getField(entity, fieldName);
                TestUtils.assertException(RuntimeException.class, StringUtility.format("java.lang.IllegalArgumentException: Can not set {}{} field {} to {}", (isClass ? "static " : (Modifier.isFinal(field.getModifiers()) ? "final " : "")), EntityStringUtility.classString(field.getType()), EntityStringUtility.fieldString(field).replace("::", "."), ((setValue == null) ? "null value" : EntityStringUtility.classString(setValue))), () ->
                        fieldValueSetter.apply(new Object[] {entity, field.getName(), setValue}));
            });
            TestUtils.assertException(RuntimeException.class, StringUtility.format("org.powermock.reflect.exceptions.FieldNotFoundException: {}::", EntityStringUtility.simpleClassString(entity)), () ->
                    TestAccess.setFieldValue(entity, "", null));
            TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                    TestAccess.setFieldValue(entity, null, null));
        });
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.setFieldValue(null, null, null));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.setFieldValue((Object) null, null, null));
    }
    
    /**
     * JUnit test of testInvokeMethod.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#invokeMethod(Class, String, Object...)
     * @see TestAccess#invokeMethod(Class, Class, String, Object...)
     * @see TestAccess#invokeMethod(Object, String, Object...)
     * @see TestAccess#invokeMethod(Object, Class, String, Object...)
     */
    @Test
    public void testInvokeMethod() throws Exception {
        final StringBuilder builder = new StringBuilder();
        
        final Function<Object[], Object> methodInvoker = (Object[] params) -> {
            final Object caller = params[0];
            final String methodName = (String) params[1];
            final Object[] arguments = (Object[]) params[2];
            return (caller instanceof Class<?>) ?
                   TestAccess.invokeMethod((Class<?>) caller, methodName, arguments) :
                   TestAccess.invokeMethod(caller, methodName, arguments);
        };
        final Consumer<Object[]> invokeMethodValidator = (Object[] params) -> {
            final Object caller = params[0];
            final String methodName = (String) params[1];
            final Object[] arguments = (Object[]) params[2];
            final Object expected = params[3];
            final String expectedBuilder = (String) params[4];
            builder.setLength(0);
            Assert.assertEquals(expected, methodInvoker.apply(new Object[] {caller, methodName, arguments}));
            Assert.assertEquals(expectedBuilder, builder.toString());
        };
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
            final boolean isClass = (entity instanceof Class<?>);
            final boolean isSubclass = !EntityStringUtility.classString(entity).equals(EntityStringUtility.classString(classes[0]));
            List.of(new Object[] {false, "staticVoidMethod", new Object[] {isSubclass, 1966L, builder}, null, ((isSubclass ? "subclass " : "") + "static void method hit: (" + isSubclass + ", 1966)")},
                    new Object[] {false, "staticVoidMethod", new Object[] {false, null, builder}, null, ((isSubclass ? "subclass " : "") + "static void method hit: (false, null)")},
                    new Object[] {false, "method1", new Object[] {}, 6.4488121, ""},
                    new Object[] {false, "method3", new Object[] {"testing"}, true, ""},
                    new Object[] {false, "method5", new Object[] {false, 54, BigDecimal.ZERO}, (byte) 4, ""},
                    new Object[] {true, "voidMethod", new Object[] {180, (long) 150, builder}, null, ((isSubclass ? "subclass " : "") + "void method hit: (" + (isSubclass ? "-" : "") + "180, 150)")},
                    new Object[] {true, "voidMethod", new Object[] {-55, null, builder}, null, ((isSubclass ? "subclass " : "") + "void method hit: (" + (isSubclass ? "" : "-") + "55, null)")},
                    new Object[] {true, "method7", new Object[] {}, 874561564112154L, ""},
                    new Object[] {true, "method8", new Object[] {"testing"}, -44, ""},
                    new Object[] {true, "method9", new Object[] {-13}, 7.66f, ""},
                    new Object[] {true, "method10", new Object[] {}, true, ""},
                    new Object[] {true, "method11", new Object[] {true, "testing", 19.41f, new char[] {'t', 'e', 's', 't'}}, "last test", ""}
            ).forEach(params ->
                    TestUtils.assertExceptionIf(() ->
                                    (isClass && (boolean) params[0]),
                            RuntimeException.class, "java.lang.IllegalArgumentException: object is not an instance of declaring class", () ->
                                    invokeMethodValidator.accept(new Object[] {entity, params[1], params[2], params[3], params[4]})));
        });
        
        //return type
        Arrays.stream(classes).forEach(clazz -> TestUtils.assertNoException(() -> {
            double method1 = TestAccess.invokeMethod(clazz, double.class, "method1");
            boolean method3 = TestAccess.invokeMethod(clazz, boolean.class, "method3", "test");
            byte method5 = TestAccess.invokeMethod(clazz, byte.class, "method5", true, -5, BigDecimal.ONE);
            TestUtils.assertException(ClassCastException.class, "class java.lang.Double cannot be cast to class java.lang.String (java.lang.Double and java.lang.String are in module java.base of loader 'bootstrap')", () -> {
                String incorrect = TestAccess.invokeMethod(clazz, String.class, "method1");
            });
        }));
        Arrays.stream(instances).forEach(instance -> TestUtils.assertNoException(() -> {
            long method7 = TestAccess.invokeMethod(instance, long.class, "method7");
            int method8 = TestAccess.invokeMethod(instance, int.class, "method8", "test");
            float method9 = TestAccess.invokeMethod(instance, float.class, "method9", 88);
            boolean method10 = TestAccess.invokeMethod(instance, boolean.class, "method10");
            String method11 = TestAccess.invokeMethod(instance, String.class, "method11", false, "test", 1.6f, new char[] {'t'});
            TestUtils.assertException(ClassCastException.class, "class java.lang.Long cannot be cast to class java.lang.Boolean (java.lang.Long and java.lang.Boolean are in module java.base of loader 'bootstrap')", () -> {
                boolean incorrect = TestAccess.invokeMethod(instance, boolean.class, "method7");
            });
        }));
        
        //invalid
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
            final boolean isClass = (entity instanceof Class<?>);
            MapUtility.mapOf(
                    new ImmutablePair<>((isClass ? "method1" : "method7"), new Object[] {"invalid argument"}),
                    new ImmutablePair<>((isClass ? "field3" : "method8"), new Object[] {76}),
                    new ImmutablePair<>((isClass ? "method3" : "method8"), new Object[] {}),
                    new ImmutablePair<>((isClass ? "staticVoidMethod" : "voidMethod"), new Object[] {null, 150L, new StringBuilder()})
            ).forEach((methodName, arguments) ->
                    TestUtils.assertException(RuntimeException.class, StringUtility.format("org.powermock.reflect.exceptions.MethodNotFoundException: {}", EntityStringUtility.simpleMethodString(entity, methodName, Arrays.stream(arguments).map(CastUtility::toClass).toArray(Class<?>[]::new))), () ->
                            TestAccess.invokeMethod(entity, methodName, arguments)));
            TestUtils.assertException(RuntimeException.class, StringUtility.format("org.powermock.reflect.exceptions.MethodNotFoundException: {}::()", EntityStringUtility.simpleClassString(entity)), () ->
                    TestAccess.invokeMethod(entity, ""));
            TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                    TestAccess.invokeMethod(entity, null));
        });
        Stream.of("method1", "", null).forEach(methodName -> {
            Stream.of(double.class, null).forEach(returnType -> {
                TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                        TestAccess.invokeMethod(null, returnType, methodName));
                TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                        TestAccess.invokeMethod((Object) null, returnType, methodName));
            });
            TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                    TestAccess.invokeMethod(null, methodName));
            TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                    TestAccess.invokeMethod((Object) null, methodName));
        });
    }
    
    /**
     * JUnit test of invokeConstructor.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#invokeConstructor(Class, Object...)
     */
    @Test
    public void testInvokeConstructor() throws Exception {
        final Object[] testArguments = new Object[] {"test", new Exception(), -994, 180L, true};
        
        final Function<Object[], Object> constructorInvoker = (Object[] params) -> {
            final Class<?> caller = (Class<?>) params[0];
            final Object[] arguments = (Object[]) params[1];
            return TestAccess.invokeConstructor(caller, arguments);
        };
        final Consumer<Object[]> invokeConstructorValidator = (Object[] params) -> {
            final Class<?> caller = (Class<?>) params[0];
            final Object[] arguments = (Object[]) params[1];
            Object testInstance = constructorInvoker.apply(new Object[] {caller, arguments});
            Assert.assertEquals(caller, testInstance.getClass());
            TestUtils.assertArrayEquals(
                    TestAccess.getFieldValue(testInstance, Object[].class, "arguments"),
                    arguments);
        };
        
        //standard
        MapUtility.mapOf(
                new ImmutablePair<>(classes[0], Stream.of(
                        IntStream.of(),
                        IntStream.of(0, 1, 2, 3, 4),
                        IntStream.of(1, 0, 2, 3, 4),
                        IntStream.of(0, 2, 3, 4, 1),
                        IntStream.of(1, 2, 3, 4, 0),
                        IntStream.of(1, 2, 3, 4, -1))),
                new ImmutablePair<>(classes[1], Stream.of(
                        IntStream.of(),
                        IntStream.of(2, 3, 4, 0, 1),
                        IntStream.of(2, 3, 4, 0, -1)))
        ).forEach((clazz, argumentsStreams) ->
                argumentsStreams.map(argumentsStream -> argumentsStream.mapToObj(i -> ArrayUtility.getOrNull(testArguments, i)).toArray()).forEach(arguments ->
                        invokeConstructorValidator.accept(new Object[] {clazz, arguments})));
        
        //invalid
        Arrays.stream(classes).forEach(clazz -> Stream.of(
                IntStream.of(1, -1, 3, 4, 0),
                IntStream.of(0, 1, 2, 3),
                IntStream.of(2),
                IntStream.of(-1)
        ).map(argumentsStream -> argumentsStream.mapToObj(i -> ArrayUtility.getOrNull(testArguments, i)).toArray()).forEach(arguments ->
                TestUtils.assertException(RuntimeException.class, StringUtility.format("org.powermock.reflect.exceptions.MethodNotFoundException: {}", EntityStringUtility.simpleConstructorString(clazz, Arrays.stream(arguments).map(CastUtility::toClass).toArray(Class<?>[]::new))), () ->
                        constructorInvoker.apply(new Object[] {clazz, arguments}))));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.invokeConstructor(null, (Object) null));
        TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                TestAccess.invokeConstructor(null));
    }
    
    /**
     * JUnit test of invokeInterfaceDefaultMethod.
     *
     * @throws Exception When there is an exception.
     * @see TestAccess#invokeInterfaceDefaultMethod(Class, String, Object...)
     * @see TestAccess#invokeInterfaceDefaultMethod(Class, Class, String, Object...)
     */
    @Test
    public void testInvokeInterfaceDefaultMethod() throws Exception {
        //standard
        Assert.assertEquals("TestInterface", TestAccess.invokeInterfaceDefaultMethod(TestInterface.class, "getName"));
        Assert.assertEquals(888L, TestAccess.invokeInterfaceDefaultMethod(TestInterface.class, "returnLong", 888));
        
        //return type
        TestUtils.assertNoException(() -> {
            String getName = TestAccess.invokeInterfaceDefaultMethod(TestInterface.class, String.class, "getName");
            long returnLong = TestAccess.invokeInterfaceDefaultMethod(TestInterface.class, long.class, "returnLong", 113);
            TestUtils.assertException(ClassCastException.class, "class java.lang.String cannot be cast to class java.lang.Boolean (java.lang.String and java.lang.Boolean are in module java.base of loader 'bootstrap')", () -> {
                boolean incorrect = TestAccess.invokeInterfaceDefaultMethod(TestInterface.class, boolean.class, "getName");
            });
        });
        
        //invalid
        Stream.of(classes, new Class<?>[] {TestInterface.class}).flatMap(Arrays::stream).forEach(clazz -> {
            final boolean isInterface = clazz.isInterface();
            MapUtility.mapOf(
                    new ImmutablePair<>("getName", new Object[] {"test"}),
                    new ImmutablePair<>("returnLong", new Object[] {}),
                    new ImmutablePair<>("thisMethod", new Object[] {}),
                    new ImmutablePair<>("thatMethod", new Object[] {"test", 5, 9})
            ).forEach((methodName, arguments) ->
                    TestUtils.assertException(RuntimeException.class, ((!isInterface) ? StringUtility.format("java.io.InvalidClassException: {} is not an interface", EntityStringUtility.simpleClassString(clazz)) : StringUtility.format("org.powermock.reflect.exceptions.MethodNotFoundException: {}", EntityStringUtility.simpleMethodString(clazz, methodName, Arrays.stream(arguments).map(CastUtility::toClass).toArray(Class<?>[]::new)))), () ->
                            TestAccess.invokeInterfaceDefaultMethod(clazz, methodName, arguments)));
            TestUtils.assertException(RuntimeException.class, ((!isInterface) ? StringUtility.format("java.io.InvalidClassException: {} is not an interface", EntityStringUtility.simpleClassString(clazz)) : StringUtility.format("org.powermock.reflect.exceptions.MethodNotFoundException: {}::()", EntityStringUtility.simpleClassString(clazz))), () ->
                    TestAccess.invokeInterfaceDefaultMethod(clazz, ""));
            TestUtils.assertException(RuntimeException.class, ((!isInterface) ? StringUtility.format("java.io.InvalidClassException: {} is not an interface", EntityStringUtility.simpleClassString(clazz)) : "java.lang.NullPointerException"), () ->
                    TestAccess.invokeInterfaceDefaultMethod(clazz, null));
        });
        Stream.of("getName", "", null).forEach(methodName -> {
            Stream.of(String.class, null).forEach(returnType ->
                    TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                            TestAccess.invokeInterfaceDefaultMethod(null, returnType, methodName)));
            TestUtils.assertException(RuntimeException.class, "java.lang.NullPointerException", () ->
                    TestAccess.invokeInterfaceDefaultMethod(null, methodName));
        });
    }
    
    
    //Inner Classes
    
    /**
     * An interface for testing the getting and setting of fields and interaction with methods.
     */
    interface TestInterface {
        
        //Constants
        
        /**
         * An interface constant.
         */
        String BASE_NAME = "Test";
        
        
        //Methods
        
        /**
         * A default interface method.
         *
         * @return The name of the class.
         */
        default String getName() {
            return BASE_NAME + "Interface";
        }
        
        /**
         * A default interface method.
         *
         * @param arg1 A sample int argument.
         * @return A long.
         */
        default long returnLong(int arg1) {
            return arg1;
        }
        
    }
    
    /**
     * A class for testing the getting and setting of fields and interaction with methods.
     */
    @SuppressWarnings("NewClassNamingConvention")
    static class TestClass implements TestInterface {
        
        //Enums
        
        /**
         * A private enum.
         */
        public enum Enum0 {
            
            //Values
            
            PUBLIC_VALUE_1,
            PUBLIC_VALUE_2,
            PUBLIC_VALUE_3;
            
            
            //Methods
            
            /**
             * A public enum method.
             */
            public String getEnumType() {
                return "Public";
            }
            
        }
        
        /**
         * A public enum.
         */
        private enum Enum1 {
            
            //Values
            
            PRIVATE_VALUE_1,
            PRIVATE_VALUE_2,
            PRIVATE_VALUE_3;
            
            
            //Methods
            
            /**
             * A private static enum method.
             */
            private static String getEnumType() {
                return "Private";
            }
            
        }
        
        //Static Fields
        
        /**
         * A private static final field.
         */
        private static final int field0 = 18;
        
        /**
         * A private static field.
         */
        private static double field1 = 6.4488121;
        
        /**
         * A public static final field.
         */
        public static final String field2 = "test";
        
        /**
         * A public static field.
         */
        public static boolean field3 = true;
        
        /**
         * A static final field.
         */
        static final String field4 = "tset";
        
        /**
         * A static field.
         */
        static byte field5 = 4;
        
        
        //Fields
        
        /**
         * A private final field.
         */
        private final String field6 = "another test";
        
        /**
         * A private field.
         */
        private long field7 = 874561564112154L;
        
        /**
         * A public final field.
         */
        public final int field8 = -44;
        
        /**
         * A public field.
         */
        public float field9 = 7.66f;
        
        /**
         * A package private final field.
         */
        final boolean field10 = true;
        
        /**
         * A package private final field.
         */
        String field11 = "last test";
        
        /**
         * The arguments passed to the constructor.
         */
        public Object[] arguments = new Object[] {};
        
        
        //Constructors
        
        /**
         * A public no-argument constructor.
         */
        public TestClass() {
        }
        
        /**
         * A public constructor with arguments.
         *
         * @param arg1 An sample String argument.
         * @param arg2 An sample Exception argument.
         * @param arg3 An sample int argument.
         * @param arg4 An sample Long argument.
         * @param arg5 An sample boolean argument.
         */
        public TestClass(String arg1, Exception arg2, int arg3, Long arg4, boolean arg5) {
            arguments = new Object[] {arg1, arg2, arg3, arg4, arg5};
        }
        
        /**
         * A private constructor with arguments.
         *
         * @param arg1 An sample Exception argument.
         * @param arg2 An sample String argument.
         * @param arg3 An sample int argument.
         * @param arg4 An sample Long argument.
         * @param arg5 An sample boolean argument.
         */
        private TestClass(Exception arg1, String arg2, int arg3, Long arg4, boolean arg5) {
            arguments = new Object[] {arg1, arg2, arg3, arg4, arg5};
        }
        
        /**
         * A protected constructor with arguments.
         *
         * @param arg1 An sample String argument.
         * @param arg2 An sample int argument.
         * @param arg3 An sample Long argument.
         * @param arg4 An sample boolean argument.
         * @param arg5 An sample Exception argument.
         */
        protected TestClass(String arg1, int arg2, Long arg3, boolean arg4, Exception arg5) {
            arguments = new Object[] {arg1, arg2, arg3, arg4, arg5};
        }
        
        /**
         * A constructor with arguments.
         *
         * @param arg1 An sample Exception argument.
         * @param arg2 An sample int argument.
         * @param arg3 An sample Long argument.
         * @param arg4 An sample boolean argument.
         * @param arg5 An sample String argument.
         */
        TestClass(Exception arg1, int arg2, Long arg3, boolean arg4, String arg5) {
            arguments = new Object[] {arg1, arg2, arg3, arg4, arg5};
        }
        
        
        //Methods
        
        /**
         * An overriden default interface method.
         *
         * @return The name of the class.
         */
        @Override
        public String getName() {
            return BASE_NAME + "Class";
        }
        
        /**
         * A void method.
         *
         * @param arg1    A sample int argument.
         * @param arg2    A sample Long argument.
         * @param builder A StringBuilder that is updated during the call.
         */
        public void voidMethod(int arg1, Long arg2, StringBuilder builder) {
            builder.append("void method hit: (").append(arg1).append(", ").append(arg2).append(')');
        }
        
        /**
         * A private method.
         *
         * @return A private field.
         */
        private long method7() {
            return field7;
        }
        
        /**
         * A public final method.
         *
         * @param arg1 A sample String argument.
         * @return A public final field.
         */
        public final int method8(String arg1) {
            return field8;
        }
        
        /**
         * A public method.
         *
         * @param arg1 A sample int argument.
         * @return A public field.
         */
        public float method9(int arg1) {
            return field9;
        }
        
        /**
         * A final method.
         *
         * @return A final field.
         */
        final boolean method10() {
            return field10;
        }
        
        /**
         * A method.
         *
         * @return A field.
         */
        String method11(boolean arg1, String arg2, float arg3, char[] arg4) {
            return field11;
        }
        
        
        //Static Methods
        
        /**
         * A static void method.
         *
         * @param arg1    A sample boolean argument.
         * @param arg2    A sample Long argument.
         * @param builder A StringBuilder that is updated during the call.
         */
        public static void staticVoidMethod(boolean arg1, Long arg2, StringBuilder builder) {
            builder.append("static void method hit: (").append(arg1).append(", ").append(arg2).append(')');
        }
        
        /**
         * A private static method.
         *
         * @return A private static field.
         */
        private static double method1() {
            return field1;
        }
        
        /**
         * A public static method.
         *
         * @param arg1 A sample String argument.
         * @return A public static field.
         */
        public static boolean method3(String arg1) {
            return field3;
        }
        
        /**
         * A static method.
         *
         * @param arg1 A sample boolean argument.
         * @param arg2 A sample int argument.
         * @param arg3 A sample BigDecimal argument.
         * @return A static field.
         */
        static byte method5(boolean arg1, int arg2, BigDecimal arg3) {
            return field5;
        }
        
        
        //Inner Classes
        
        /**
         * An inner class.
         */
        protected static class TestInnerClass {
            
        }
        
    }
    
    /**
     * A subclass for testing the getting and setting of fields and interaction with methods.
     */
    static class TestSubClass extends TestClass {
        
        //Fields
        
        /**
         * A private static final field.
         */
        private static final int field12 = -644;
        
        /**
         * A static final field.
         */
        static final String field4 = "subclass tset";
        
        /**
         * A public field.
         */
        public float field13 = -116.103f;
        
        
        //Constructors
        
        /**
         * A public no-argument constructor.
         */
        public TestSubClass() {
        }
        
        /**
         * A protected constructor with arguments.
         *
         * @param arg1 An sample int argument.
         * @param arg2 An sample Long argument.
         * @param arg3 An sample boolean argument.
         * @param arg4 An sample String argument.
         * @param arg5 An sample Exception argument.
         */
        protected TestSubClass(int arg1, Long arg2, boolean arg3, String arg4, Exception arg5) {
            arguments = new Object[] {arg1, arg2, arg3, arg4, arg5};
        }
        
        
        //Methods
        
        /**
         * An overriden default interface method.
         *
         * @return The name of the class.
         */
        @Override
        public String getName() {
            return BASE_NAME + "SubClass";
        }
        
        /**
         * An overrided void method.
         *
         * @param arg1    A sample int argument.
         * @param arg2    A sample Long argument.
         * @param builder A StringBuilder that is updated during the call.
         */
        @Override
        public void voidMethod(int arg1, Long arg2, StringBuilder builder) {
            builder.append("subclass void method hit: (").append(arg1 * -1).append(", ").append(arg2).append(')');
        }
        
        /**
         * A protected method.
         *
         * @param arg1 A sample String argument.
         * @param arg2 A sample Long argument.
         * @return A boolean.
         */
        protected boolean subMethod(String arg1, Long arg2) {
            return true;
        }
        
        
        //Static Methods
        
        /**
         * A static void method.
         *
         * @param arg1    A sample boolean argument.
         * @param arg2    A sample Long argument.
         * @param builder A StringBuilder that is updated during the call.
         */
        public static void staticVoidMethod(boolean arg1, Long arg2, StringBuilder builder) {
            builder.append("subclass static void method hit: (").append(arg1).append(", ").append(arg2).append(')');
        }
        
        /**
         * A protected static method.
         *
         * @param arg1 A sample String argument.
         * @param arg2 A sample long argument.
         * @return A String.
         */
        protected static String staticSubMethod(String arg1, long arg2) {
            return "test";
        }
        
    }
    
}
