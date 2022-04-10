/*
 * File:    EntityStringUtility.java
 * Package: commons.object.string
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.string;

import java.lang.reflect.Method;
import java.util.Arrays;

import commons.test.TestAccess;
import commons.test.TestAccessTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of EntityStringUtility.
 *
 * @see EntityStringUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({EntityStringUtility.class})
public class EntityStringUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(EntityStringUtilityTest.class);
    
    
    //Static Fields
    
    /**
     * The TestClass class.
     */
    private static final Class<?> TestClass = TestAccess.getClass(TestAccessTest.class, "TestClass");
    
    /**
     * The TestSubClass class.
     */
    private static final Class<?> TestSubClass = TestAccess.getClass(TestAccessTest.class, "TestSubClass");
    
    /**
     * The TestInnerClass class.
     */
    private static final Class<?> TestInnerClass = TestAccess.getClass(TestClass, "TestInnerClass");
    
    /**
     * A list of mocks to use for testing.
     */
    private static final Object[] testMocks = new Object[] {Mockito.mock(TestAccessTest.class), PowerMockito.mock(TestAccessTest.class), Mockito.spy(new TestAccessTest())};
    
    
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
     * The JUnit cleanup constants.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of generateClassString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#generateClassString(boolean, Class)
     */
    @Test
    public void testGenerateClassString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateClassString", false, TestAccessTest.class));
        Assert.assertEquals("TestAccessTest",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateClassString", true, TestAccessTest.class));
        
        //invalid
        Assert.assertEquals("null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateClassString", false, null));
        Assert.assertEquals("null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateClassString", true, null));
    }
    
    /**
     * JUnit test of classString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#classString(Class)
     * @see EntityStringUtility#classString(Object)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testClassString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest",
                EntityStringUtility.classString(TestAccessTest.class));
        
        //inner class
        Assert.assertEquals("commons.test.TestAccessTest$TestClass",
                EntityStringUtility.classString(TestClass));
        Assert.assertEquals("commons.test.TestAccessTest$TestClass$TestInnerClass",
                EntityStringUtility.classString(TestInnerClass));
        
        //object
        Assert.assertEquals("commons.test.TestAccessTest",
                EntityStringUtility.classString(new TestAccessTest()));
        
        //mock
        Arrays.stream(testMocks).forEach(mock ->
                Assert.assertEquals("commons.test.TestAccessTest",
                        EntityStringUtility.classString(mock)));
        
        //invalid
        Assert.assertEquals("null",
                EntityStringUtility.classString(null));
        Assert.assertEquals("null",
                EntityStringUtility.classString((Object) null));
    }
    
    /**
     * JUnit test of simpleClassString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#simpleClassString(Class)
     * @see EntityStringUtility#simpleClassString(Object)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testSimpleClassString() throws Exception {
        //class
        Assert.assertEquals("TestAccessTest",
                EntityStringUtility.simpleClassString(TestAccessTest.class));
        
        //inner class
        Assert.assertEquals("TestClass",
                EntityStringUtility.simpleClassString(TestClass));
        Assert.assertEquals("TestInnerClass",
                EntityStringUtility.simpleClassString(TestInnerClass));
        
        //object
        Assert.assertEquals("TestAccessTest",
                EntityStringUtility.simpleClassString(new TestAccessTest()));
        
        //mock
        Arrays.stream(testMocks).forEach(mock ->
                Assert.assertEquals("TestAccessTest",
                        EntityStringUtility.simpleClassString(mock)));
        
        //invalid
        Assert.assertEquals("null",
                EntityStringUtility.simpleClassString(null));
        Assert.assertEquals("null",
                EntityStringUtility.simpleClassString((Object) null));
    }
    
    /**
     * JUnit test of generateSuperClassString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#generateSuperClassString(boolean, Class)
     */
    @Test
    public void testGenerateSuperClassString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest$TestClass",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateSuperClassString", false, TestSubClass));
        Assert.assertEquals("TestClass",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateSuperClassString", true, TestSubClass));
        
        //invalid
        Assert.assertEquals("null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateSuperClassString", false, null));
        Assert.assertEquals("null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateSuperClassString", true, null));
    }
    
    /**
     * JUnit test of superClassString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#superClassString(Class)
     * @see EntityStringUtility#superClassString(Object)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testSuperClassString() throws Exception {
        //class
        Assert.assertEquals("java.lang.Object",
                EntityStringUtility.superClassString(TestAccessTest.class));
        
        //inner class
        Assert.assertEquals("java.lang.Object",
                EntityStringUtility.superClassString(TestClass));
        Assert.assertEquals("commons.test.TestAccessTest$TestClass",
                EntityStringUtility.superClassString(TestSubClass));
        
        //object
        Assert.assertEquals("java.lang.Object",
                EntityStringUtility.superClassString(new TestAccessTest()));
        
        //mock
        Arrays.stream(testMocks).forEach(mock ->
                Assert.assertEquals("commons.test.TestAccessTest",
                        EntityStringUtility.superClassString(mock)));
        
        //invalid
        Assert.assertEquals("null",
                EntityStringUtility.superClassString(null));
        Assert.assertEquals("null",
                EntityStringUtility.superClassString((Object) null));
    }
    
    /**
     * JUnit test of simpleSuperClassString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#simpleSuperClassString(Class)
     * @see EntityStringUtility#simpleSuperClassString(Object)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testSimpleSuperClassString() throws Exception {
        //class
        Assert.assertEquals("Object",
                EntityStringUtility.simpleSuperClassString(TestAccessTest.class));
        
        //inner class
        Assert.assertEquals("Object",
                EntityStringUtility.simpleSuperClassString(TestClass));
        Assert.assertEquals("TestClass",
                EntityStringUtility.simpleSuperClassString(TestSubClass));
        
        //object
        Assert.assertEquals("Object",
                EntityStringUtility.simpleSuperClassString(new TestAccessTest()));
        
        //mock
        Arrays.stream(testMocks).forEach(mock ->
                Assert.assertEquals("TestAccessTest",
                        EntityStringUtility.simpleSuperClassString(mock)));
        
        //invalid
        Assert.assertEquals("null",
                EntityStringUtility.simpleSuperClassString(null));
        Assert.assertEquals("null",
                EntityStringUtility.simpleSuperClassString((Object) null));
    }
    
    /**
     * JUnit test of generateInnerClassString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#generateInnerClassString(boolean, Class, String)
     */
    @Test
    public void testGenerateInnerClassString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest$InnerClass",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateInnerClassString", false, TestAccessTest.class, "InnerClass"));
        Assert.assertEquals("TestAccessTest$InnerClass",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateInnerClassString", true, TestAccessTest.class, "InnerClass"));
        
        //invalid
        Assert.assertEquals("commons.test.TestAccessTest$null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateInnerClassString", false, TestAccessTest.class, null));
        Assert.assertEquals("TestAccessTest$null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateInnerClassString", true, TestAccessTest.class, null));
        Assert.assertEquals("null$InnerClass",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateInnerClassString", false, null, "InnerClass"));
        Assert.assertEquals("null$InnerClass",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateInnerClassString", true, null, "InnerClass"));
        Assert.assertEquals("null$null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateInnerClassString", false, null, null));
        Assert.assertEquals("null$null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateInnerClassString", true, null, null));
    }
    
    /**
     * JUnit test of innerClassString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#innerClassString(Class, String)
     * @see EntityStringUtility#innerClassString(Object, String)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testInnerClassString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest$InnerClass",
                EntityStringUtility.innerClassString(TestAccessTest.class, "InnerClass"));
        
        //inner class
        Assert.assertEquals("commons.test.TestAccessTest$TestClass",
                EntityStringUtility.innerClassString(TestAccessTest.class, "TestClass"));
        Assert.assertEquals("commons.test.TestAccessTest$TestClass$TestInnerClass",
                EntityStringUtility.innerClassString(TestClass, "TestInnerClass"));
        
        //object
        Assert.assertEquals("commons.test.TestAccessTest$InnerClass",
                EntityStringUtility.innerClassString(new TestAccessTest(), "InnerClass"));
        
        //mock
        Arrays.stream(testMocks).forEach(mock ->
                Assert.assertEquals("commons.test.TestAccessTest$InnerClass",
                        EntityStringUtility.innerClassString(mock, "InnerClass")));
        
        //invalid
        Assert.assertEquals("commons.test.TestAccessTest$null",
                EntityStringUtility.innerClassString(TestAccessTest.class, null));
        Assert.assertEquals("commons.test.TestAccessTest$null",
                EntityStringUtility.innerClassString(new TestAccessTest(), null));
        Assert.assertEquals("null$InnerClass",
                EntityStringUtility.innerClassString(null, "InnerClass"));
        Assert.assertEquals("null$InnerClass",
                EntityStringUtility.innerClassString((Object) null, "InnerClass"));
        Assert.assertEquals("null$null",
                EntityStringUtility.innerClassString(null, null));
        Assert.assertEquals("null$null",
                EntityStringUtility.innerClassString((Object) null, null));
    }
    
    /**
     * JUnit test of simpleInnerClassString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#simpleInnerClassString(Class, String)
     * @see EntityStringUtility#simpleInnerClassString(Object, String)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testSimpleInnerClassString() throws Exception {
        //class
        Assert.assertEquals("TestAccessTest$InnerClass",
                EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "InnerClass"));
        
        //inner class
        Assert.assertEquals("TestAccessTest$TestClass",
                EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "TestClass"));
        Assert.assertEquals("TestClass$TestInnerClass",
                EntityStringUtility.simpleInnerClassString(TestClass, "TestInnerClass"));
        
        //object
        Assert.assertEquals("TestAccessTest$InnerClass",
                EntityStringUtility.simpleInnerClassString(new TestAccessTest(), "InnerClass"));
        
        //mock
        Arrays.stream(testMocks).forEach(mock ->
                Assert.assertEquals("TestAccessTest$InnerClass",
                        EntityStringUtility.simpleInnerClassString(mock, "InnerClass")));
        
        //invalid
        Assert.assertEquals("TestAccessTest$null",
                EntityStringUtility.simpleInnerClassString(TestAccessTest.class, null));
        Assert.assertEquals("TestAccessTest$null",
                EntityStringUtility.simpleInnerClassString(new TestAccessTest(), null));
        Assert.assertEquals("null$InnerClass",
                EntityStringUtility.simpleInnerClassString(null, "InnerClass"));
        Assert.assertEquals("null$InnerClass",
                EntityStringUtility.simpleInnerClassString((Object) null, "InnerClass"));
        Assert.assertEquals("null$null",
                EntityStringUtility.simpleInnerClassString(null, null));
        Assert.assertEquals("null$null",
                EntityStringUtility.simpleInnerClassString((Object) null, null));
    }
    
    /**
     * JUnit test of generateMethodString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#generateMethodString(boolean, Class, String, Class[])
     */
    @Test
    public void testGenerateMethodString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest::method(java.lang.Class, java.lang.String, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, TestAccessTest.class, "method", new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("TestAccessTest::method(Class, String, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, TestAccessTest.class, "method", new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("commons.test.TestAccessTest::method(commons.test.TestAccessTest, java.lang.String, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, TestAccessTest.class, "method", new Class<?>[] {Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class}));
        Assert.assertEquals("TestAccessTest::method(TestAccessTest, String, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, TestAccessTest.class, "method", new Class<?>[] {Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class}));
        Assert.assertEquals("commons.test.TestAccessTest::method()",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, TestAccessTest.class, "method", new Class<?>[] {}));
        Assert.assertEquals("TestAccessTest::method()",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, TestAccessTest.class, "method", new Class<?>[] {}));
        
        //invalid
        Assert.assertEquals("commons.test.TestAccessTest::method(java.lang.Class, null, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, TestAccessTest.class, "method", new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("TestAccessTest::method(Class, null, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, TestAccessTest.class, "method", new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("commons.test.TestAccessTest::method(null)",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, TestAccessTest.class, "method", null));
        Assert.assertEquals("TestAccessTest::method(null)",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, TestAccessTest.class, "method", null));
        Assert.assertEquals("commons.test.TestAccessTest::(java.lang.Class, java.lang.String, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, TestAccessTest.class, "", new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("TestAccessTest::(Class, String, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, TestAccessTest.class, "", new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("commons.test.TestAccessTest::null(java.lang.Class, java.lang.String, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, TestAccessTest.class, null, new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("TestAccessTest::null(Class, String, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, TestAccessTest.class, null, new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("null::method(java.lang.Class, null, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, null, "method", new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::method(Class, null, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, null, "method", new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(java.lang.Class, null, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, null, null, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(Class, null, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, null, null, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(null)",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", false, null, null, null));
        Assert.assertEquals("null::null(null)",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateMethodString", true, null, null, null));
    }
    
    /**
     * JUnit test of methodString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#methodString(Class, String, Class[])
     * @see EntityStringUtility#methodString(Object, String, Class[])
     * @see EntityStringUtility#methodString(Method)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testMethodString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest::method(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(TestAccessTest.class, "method", Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::method(commons.test.TestAccessTest, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(TestAccessTest.class, "method", Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::method()",
                EntityStringUtility.methodString(TestAccessTest.class, "method"));
        
        //object
        Assert.assertEquals("commons.test.TestAccessTest::method(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(new TestAccessTest(), "method", Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::method(commons.test.TestAccessTest, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(new TestAccessTest(), "method", Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::method()",
                EntityStringUtility.methodString(new TestAccessTest(), "method"));
        
        //mock
        Arrays.stream(testMocks).forEach(mock -> {
            Assert.assertEquals("commons.test.TestAccessTest::method(java.lang.Class, java.lang.String, java.lang.Class[])",
                    EntityStringUtility.methodString(mock, "method", Class.class, String.class, Class[].class));
            Assert.assertEquals("commons.test.TestAccessTest::method(commons.test.TestAccessTest, java.lang.String, java.lang.Class[])",
                    EntityStringUtility.methodString(mock, "method", Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
            Assert.assertEquals("commons.test.TestAccessTest::method()",
                    EntityStringUtility.methodString(mock, "method"));
        });
        
        //inner class
        Assert.assertEquals("commons.test.TestAccessTest$TestClass::method(java.lang.String)",
                EntityStringUtility.methodString(TestClass, "method", String.class));
        Assert.assertEquals("commons.test.TestAccessTest$TestClass$TestInnerClass::method(java.lang.String)",
                EntityStringUtility.methodString(TestInnerClass, "method", String.class));
        
        //method
        Assert.assertEquals("commons.test.TestAccessTest$TestClass::voidMethod(int, java.lang.Long, java.lang.StringBuilder)",
                EntityStringUtility.methodString(TestAccess.getMethod(TestClass, "voidMethod", int.class, Long.class, StringBuilder.class)));
        Assert.assertEquals("commons.test.TestAccessTest$TestClass::method1()",
                EntityStringUtility.methodString(TestAccess.getMethod(TestClass, "method1")));
        
        //invalid
        Assert.assertEquals("commons.test.TestAccessTest::method(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.methodString(TestAccessTest.class, "method", Class.class, null, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::method(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.methodString(new TestAccessTest(), "method", Class.class, null, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::method(null)",
                EntityStringUtility.methodString(TestAccessTest.class, "method", (Class<?>) null));
        Assert.assertEquals("commons.test.TestAccessTest::method(null)",
                EntityStringUtility.methodString(new TestAccessTest(), "method", (Class<?>) null));
        Assert.assertEquals("commons.test.TestAccessTest::(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(TestAccessTest.class, "", Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(new TestAccessTest(), "", Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::null(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(TestAccessTest.class, null, Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::null(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(new TestAccessTest(), null, Class.class, String.class, Class[].class));
        Assert.assertEquals("null::method(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.methodString(null, "method", Class.class, null, Class[].class));
        Assert.assertEquals("null::method(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.methodString((Object) null, "method", Class.class, null, Class[].class));
        Assert.assertEquals("null::null(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.methodString(null, null, Class.class, null, Class[].class));
        Assert.assertEquals("null::null(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.methodString((Object) null, null, Class.class, null, Class[].class));
        Assert.assertEquals("null::null(null)",
                EntityStringUtility.methodString(null, null, (Class<?>) null));
        Assert.assertEquals("null::null(null)",
                EntityStringUtility.methodString((Object) null, null, (Class<?>) null));
    }
    
    /**
     * JUnit test of simpleMethodString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#simpleMethodString(Class, String, Class[])
     * @see EntityStringUtility#simpleMethodString(Object, String, Class[])
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testSimpleMethodString() throws Exception {
        //class
        Assert.assertEquals("TestAccessTest::method(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(TestAccessTest.class, "method", Class.class, String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::method(TestAccessTest, String, Class[])",
                EntityStringUtility.simpleMethodString(TestAccessTest.class, "method", Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::method()",
                EntityStringUtility.simpleMethodString(TestAccessTest.class, "method"));
        
        //object
        Assert.assertEquals("TestAccessTest::method(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(new TestAccessTest(), "method", Class.class, String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::method(TestAccessTest, String, Class[])",
                EntityStringUtility.simpleMethodString(new TestAccessTest(), "method", Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::method()",
                EntityStringUtility.simpleMethodString(new TestAccessTest(), "method"));
        
        //mock
        Arrays.stream(testMocks).forEach(mock -> {
            Assert.assertEquals("TestAccessTest::method(Class, String, Class[])",
                    EntityStringUtility.simpleMethodString(mock, "method", Class.class, String.class, Class[].class));
            Assert.assertEquals("TestAccessTest::method(TestAccessTest, String, Class[])",
                    EntityStringUtility.simpleMethodString(mock, "method", Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
            Assert.assertEquals("TestAccessTest::method()",
                    EntityStringUtility.simpleMethodString(mock, "method"));
        });
        
        //inner class
        Assert.assertEquals("TestClass::method(String)",
                EntityStringUtility.simpleMethodString(TestClass, "method", String.class));
        Assert.assertEquals("TestInnerClass::method(String)",
                EntityStringUtility.simpleMethodString(TestInnerClass, "method", String.class));
        
        //method
        Assert.assertEquals("TestClass::voidMethod(int, Long, StringBuilder)",
                EntityStringUtility.simpleMethodString(TestAccess.getMethod(TestClass, "voidMethod", int.class, Long.class, StringBuilder.class)));
        Assert.assertEquals("TestClass::method1()",
                EntityStringUtility.simpleMethodString(TestAccess.getMethod(TestClass, "method1")));
        
        //invalid
        Assert.assertEquals("TestAccessTest::method(Class, null, Class[])",
                EntityStringUtility.simpleMethodString(TestAccessTest.class, "method", Class.class, null, Class[].class));
        Assert.assertEquals("TestAccessTest::method(Class, null, Class[])",
                EntityStringUtility.simpleMethodString(new TestAccessTest(), "method", Class.class, null, Class[].class));
        Assert.assertEquals("TestAccessTest::method(null)",
                EntityStringUtility.simpleMethodString(TestAccessTest.class, "method", (Class<?>) null));
        Assert.assertEquals("TestAccessTest::method(null)",
                EntityStringUtility.simpleMethodString(new TestAccessTest(), "method", (Class<?>) null));
        Assert.assertEquals("TestAccessTest::(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(TestAccessTest.class, "", Class.class, String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(new TestAccessTest(), "", Class.class, String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::null(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(TestAccessTest.class, null, Class.class, String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::null(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(new TestAccessTest(), null, Class.class, String.class, Class[].class));
        Assert.assertEquals("null::method(Class, null, Class[])",
                EntityStringUtility.simpleMethodString(null, "method", Class.class, null, Class[].class));
        Assert.assertEquals("null::method(Class, null, Class[])",
                EntityStringUtility.simpleMethodString((Object) null, "method", Class.class, null, Class[].class));
        Assert.assertEquals("null::null(Class, null, Class[])",
                EntityStringUtility.simpleMethodString(null, null, Class.class, null, Class[].class));
        Assert.assertEquals("null::null(Class, null, Class[])",
                EntityStringUtility.simpleMethodString((Object) null, null, Class.class, null, Class[].class));
        Assert.assertEquals("null::null(null)",
                EntityStringUtility.simpleMethodString(null, null, (Class<?>) null));
        Assert.assertEquals("null::null(null)",
                EntityStringUtility.simpleMethodString((Object) null, null, (Class<?>) null));
    }
    
    /**
     * JUnit test of generateConstructorString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#generateConstructorString(boolean, Class, Class[])
     */
    @Test
    public void testGenerateConstructorString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(java.lang.Class, java.lang.String, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, TestAccessTest.class, new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("TestAccessTest::TestAccessTest(Class, String, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, TestAccessTest.class, new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(commons.test.TestAccessTest, java.lang.String, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, TestAccessTest.class, new Class<?>[] {Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class}));
        Assert.assertEquals("TestAccessTest::TestAccessTest(TestAccessTest, String, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, TestAccessTest.class, new Class<?>[] {Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class}));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest()",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, TestAccessTest.class, new Class<?>[] {}));
        Assert.assertEquals("TestAccessTest::TestAccessTest()",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, TestAccessTest.class, new Class<?>[] {}));
        
        //invalid
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(java.lang.Class, null, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, TestAccessTest.class, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("TestAccessTest::TestAccessTest(Class, null, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, TestAccessTest.class, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(null)",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, TestAccessTest.class, null));
        Assert.assertEquals("TestAccessTest::TestAccessTest(null)",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, TestAccessTest.class, null));
        Assert.assertEquals("null::null(java.lang.Class, null, java.lang.Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, null, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(Class, null, Class[])",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, null, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(null)",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, null, null));
        Assert.assertEquals("null::null(null)",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, null, null));
    }
    
    /**
     * JUnit test of constructorString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#constructorString(Class, Class[])
     * @see EntityStringUtility#constructorString(Object, Class[])
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testConstructorString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.constructorString(TestAccessTest.class, Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(commons.test.TestAccessTest, java.lang.String, java.lang.Class[])",
                EntityStringUtility.constructorString(TestAccessTest.class, Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest()",
                EntityStringUtility.constructorString(TestAccessTest.class));
        
        //object
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.constructorString(new TestAccessTest(), Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(commons.test.TestAccessTest, java.lang.String, java.lang.Class[])",
                EntityStringUtility.constructorString(new TestAccessTest(), Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest()",
                EntityStringUtility.constructorString(new TestAccessTest()));
        
        //mock
        Arrays.stream(testMocks).forEach(mock -> {
            Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(java.lang.Class, java.lang.String, java.lang.Class[])",
                    EntityStringUtility.constructorString(mock, Class.class, String.class, Class[].class));
            Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(commons.test.TestAccessTest, java.lang.String, java.lang.Class[])",
                    EntityStringUtility.constructorString(mock, Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
            Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest()",
                    EntityStringUtility.constructorString(mock));
        });
        
        //inner class
        Assert.assertEquals("commons.test.TestAccessTest$TestClass::TestClass(java.lang.String)",
                EntityStringUtility.constructorString(TestClass, String.class));
        Assert.assertEquals("commons.test.TestAccessTest$TestClass$TestInnerClass::TestInnerClass(java.lang.String)",
                EntityStringUtility.constructorString(TestInnerClass, String.class));
        
        //constructor
        Assert.assertEquals("commons.test.TestAccessTest$TestClass::TestClass(java.lang.String, java.lang.Exception, int, java.lang.Long, boolean)",
                EntityStringUtility.constructorString(TestAccess.getConstructor(TestClass, String.class, Exception.class, int.class, Long.class, boolean.class)));
        Assert.assertEquals("commons.test.TestAccessTest$TestClass::TestClass()",
                EntityStringUtility.constructorString(TestAccess.getConstructor(TestClass)));
        
        //invalid
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.constructorString(TestAccessTest.class, Class.class, null, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.constructorString(new TestAccessTest(), Class.class, null, Class[].class));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(null)",
                EntityStringUtility.constructorString(TestAccessTest.class, (Class<?>) null));
        Assert.assertEquals("commons.test.TestAccessTest::TestAccessTest(null)",
                EntityStringUtility.constructorString(new TestAccessTest(), (Class<?>) null));
        Assert.assertEquals("null::null(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.constructorString(null, Class.class, null, Class[].class));
        Assert.assertEquals("null::null(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.constructorString((Object) null, Class.class, null, Class[].class));
        Assert.assertEquals("null::null(null)",
                EntityStringUtility.constructorString(null, (Class<?>) null));
        Assert.assertEquals("null::null(null)",
                EntityStringUtility.constructorString((Object) null, (Class<?>) null));
    }
    
    /**
     * JUnit test of simpleConstructorString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#simpleConstructorString(Class, Class[])
     * @see EntityStringUtility#simpleConstructorString(Object, Class[])
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testSimpleConstructorString() throws Exception {
        //class
        Assert.assertEquals("TestAccessTest::TestAccessTest(Class, String, Class[])",
                EntityStringUtility.simpleConstructorString(TestAccessTest.class, Class.class, String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::TestAccessTest(TestAccessTest, String, Class[])",
                EntityStringUtility.simpleConstructorString(TestAccessTest.class, Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::TestAccessTest()",
                EntityStringUtility.simpleConstructorString(TestAccessTest.class));
        
        //object
        Assert.assertEquals("TestAccessTest::TestAccessTest(Class, String, Class[])",
                EntityStringUtility.simpleConstructorString(new TestAccessTest(), Class.class, String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::TestAccessTest(TestAccessTest, String, Class[])",
                EntityStringUtility.simpleConstructorString(new TestAccessTest(), Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("TestAccessTest::TestAccessTest()",
                EntityStringUtility.simpleConstructorString(new TestAccessTest()));
        
        //mock
        Arrays.stream(testMocks).forEach(mock -> {
            Assert.assertEquals("TestAccessTest::TestAccessTest(Class, String, Class[])",
                    EntityStringUtility.simpleConstructorString(mock, Class.class, String.class, Class[].class));
            Assert.assertEquals("TestAccessTest::TestAccessTest(TestAccessTest, String, Class[])",
                    EntityStringUtility.simpleConstructorString(mock, Mockito.mock(TestAccessTest.class).getClass(), String.class, Class[].class));
            Assert.assertEquals("TestAccessTest::TestAccessTest()",
                    EntityStringUtility.simpleConstructorString(mock));
        });
        
        //inner class
        Assert.assertEquals("TestClass::TestClass(String)",
                EntityStringUtility.simpleConstructorString(TestClass, String.class));
        Assert.assertEquals("TestInnerClass::TestInnerClass(String)",
                EntityStringUtility.simpleConstructorString(TestInnerClass, String.class));
        
        //constructor
        Assert.assertEquals("TestClass::TestClass(String, Exception, int, Long, boolean)",
                EntityStringUtility.simpleConstructorString(TestAccess.getConstructor(TestClass, String.class, Exception.class, int.class, Long.class, boolean.class)));
        Assert.assertEquals("TestClass::TestClass()",
                EntityStringUtility.simpleConstructorString(TestAccess.getConstructor(TestClass)));
        
        //invalid
        Assert.assertEquals("TestAccessTest::TestAccessTest(Class, null, Class[])",
                EntityStringUtility.simpleConstructorString(TestAccessTest.class, Class.class, null, Class[].class));
        Assert.assertEquals("TestAccessTest::TestAccessTest(Class, null, Class[])",
                EntityStringUtility.simpleConstructorString(new TestAccessTest(), Class.class, null, Class[].class));
        Assert.assertEquals("TestAccessTest::TestAccessTest(null)",
                EntityStringUtility.simpleConstructorString(TestAccessTest.class, (Class<?>) null));
        Assert.assertEquals("TestAccessTest::TestAccessTest(null)",
                EntityStringUtility.simpleConstructorString(new TestAccessTest(), (Class<?>) null));
        Assert.assertEquals("null::null(Class, null, Class[])",
                EntityStringUtility.simpleConstructorString(null, Class.class, null, Class[].class));
        Assert.assertEquals("null::null(Class, null, Class[])",
                EntityStringUtility.simpleConstructorString((Object) null, Class.class, null, Class[].class));
        Assert.assertEquals("null::null(null)",
                EntityStringUtility.simpleConstructorString(null, (Class<?>) null));
        Assert.assertEquals("null::null(null)",
                EntityStringUtility.simpleConstructorString((Object) null, (Class<?>) null));
    }
    
    /**
     * JUnit test of generateFieldString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#generateFieldString(boolean, Class, String)
     */
    @Test
    public void testGenerateFieldString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest::field",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", false, TestAccessTest.class, "field"));
        Assert.assertEquals("TestAccessTest::field",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", true, TestAccessTest.class, "field"));
        
        //invalid
        Assert.assertEquals("commons.test.TestAccessTest::",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", false, TestAccessTest.class, ""));
        Assert.assertEquals("TestAccessTest::",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", true, TestAccessTest.class, ""));
        Assert.assertEquals("commons.test.TestAccessTest::null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", false, TestAccessTest.class, null));
        Assert.assertEquals("TestAccessTest::null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", true, TestAccessTest.class, null));
        Assert.assertEquals("null::field",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", false, null, "field"));
        Assert.assertEquals("null::field",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", true, null, "field"));
        Assert.assertEquals("null::",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", false, null, ""));
        Assert.assertEquals("null::",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", true, null, ""));
        Assert.assertEquals("null::null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", false, null, null));
        Assert.assertEquals("null::null",
                TestAccess.invokeMethod(EntityStringUtility.class, "generateFieldString", true, null, null));
    }
    
    /**
     * JUnit test of fieldString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#fieldString(Class, String)
     * @see EntityStringUtility#fieldString(Object, String)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testFieldString() throws Exception {
        //class
        Assert.assertEquals("commons.test.TestAccessTest::field",
                EntityStringUtility.fieldString(TestAccessTest.class, "field"));
        
        //object
        Assert.assertEquals("commons.test.TestAccessTest::field",
                EntityStringUtility.fieldString(new TestAccessTest(), "field"));
        
        //mock
        Arrays.stream(testMocks).forEach(mock ->
                Assert.assertEquals("commons.test.TestAccessTest::field",
                        EntityStringUtility.fieldString(mock, "field")));
        
        //inner class
        Assert.assertEquals("commons.test.TestAccessTest$TestClass::field",
                EntityStringUtility.fieldString(TestClass, "field"));
        Assert.assertEquals("commons.test.TestAccessTest$TestClass$TestInnerClass::field",
                EntityStringUtility.fieldString(TestInnerClass, "field"));
        
        //field
        Assert.assertEquals("commons.test.TestAccessTest$TestClass::field0",
                EntityStringUtility.fieldString(TestAccess.getField(TestClass, "field0")));
        Assert.assertEquals("commons.test.TestAccessTest$TestClass::arguments",
                EntityStringUtility.fieldString(TestAccess.getField(TestClass, "arguments")));
        
        //invalid
        Assert.assertEquals("commons.test.TestAccessTest::null",
                EntityStringUtility.fieldString(TestAccessTest.class, null));
        Assert.assertEquals("commons.test.TestAccessTest::null",
                EntityStringUtility.fieldString(new TestAccessTest(), null));
        Assert.assertEquals("commons.test.TestAccessTest::null",
                EntityStringUtility.fieldString(Mockito.mock(TestAccessTest.class), null));
        Assert.assertEquals("null::field",
                EntityStringUtility.fieldString(null, "field"));
        Assert.assertEquals("null::field",
                EntityStringUtility.fieldString((Object) null, "field"));
        Assert.assertEquals("null::null",
                EntityStringUtility.fieldString(null, null));
        Assert.assertEquals("null::null",
                EntityStringUtility.fieldString((Object) null, null));
    }
    
    /**
     * JUnit test of simpleFieldString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#simpleFieldString(Class, String)
     * @see EntityStringUtility#simpleFieldString(Object, String)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testSimpleFieldString() throws Exception {
        //class
        Assert.assertEquals("TestAccessTest::field",
                EntityStringUtility.simpleFieldString(TestAccessTest.class, "field"));
        
        //object
        Assert.assertEquals("TestAccessTest::field",
                EntityStringUtility.simpleFieldString(new TestAccessTest(), "field"));
        
        //mock
        Arrays.stream(testMocks).forEach(mock ->
                Assert.assertEquals("TestAccessTest::field",
                        EntityStringUtility.simpleFieldString(mock, "field")));
        
        //inner class
        Assert.assertEquals("TestClass::field",
                EntityStringUtility.simpleFieldString(TestClass, "field"));
        Assert.assertEquals("TestInnerClass::field",
                EntityStringUtility.simpleFieldString(TestInnerClass, "field"));
        
        //field
        Assert.assertEquals("TestClass::field0",
                EntityStringUtility.simpleFieldString(TestAccess.getField(TestClass, "field0")));
        Assert.assertEquals("TestClass::arguments",
                EntityStringUtility.simpleFieldString(TestAccess.getField(TestClass, "arguments")));
        
        //invalid
        Assert.assertEquals("TestAccessTest::null",
                EntityStringUtility.simpleFieldString(TestAccessTest.class, null));
        Assert.assertEquals("TestAccessTest::null",
                EntityStringUtility.simpleFieldString(new TestAccessTest(), null));
        Assert.assertEquals("TestAccessTest::null",
                EntityStringUtility.simpleFieldString(Mockito.mock(TestAccessTest.class), null));
        Assert.assertEquals("null::field",
                EntityStringUtility.simpleFieldString(null, "field"));
        Assert.assertEquals("null::field",
                EntityStringUtility.simpleFieldString((Object) null, "field"));
        Assert.assertEquals("null::null",
                EntityStringUtility.simpleFieldString(null, null));
        Assert.assertEquals("null::null",
                EntityStringUtility.simpleFieldString((Object) null, null));
    }
    
}
