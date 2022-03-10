/*
 * File:    EntityStringUtility.java
 * Package: commons.object.string
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.string;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import commons.test.TestUtils;
import commons.test.TestUtilsTest;
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
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({EntityStringUtility.class})
public class EntityStringUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(EntityStringUtilityTest.class);
    
    
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
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testGenerateClassString() throws Exception {
        //class
        Assert.assertEquals("commons.object.string.EntityStringUtility",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateClassString", false, EntityStringUtility.class));
        Assert.assertEquals("EntityStringUtility",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateClassString", true, EntityStringUtility.class));
        
        //invalid
        Assert.assertEquals("null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateClassString", false, null));
        Assert.assertEquals("null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateClassString", true, null));
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
        Assert.assertEquals("commons.object.string.EntityStringUtility",
                EntityStringUtility.classString(EntityStringUtility.class));
        
        //inner class
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass",
                EntityStringUtility.classString(TestUtils.getClass(TestUtilsTest.class, "TestClass")));
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass$TestInnerClass",
                EntityStringUtility.classString(TestUtils.getClass(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass")));
        
        //object
        Assert.assertEquals("commons.object.string.EntityStringUtility",
                EntityStringUtility.classString(new EntityStringUtility()));
        
        //mock
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e ->
                Assert.assertEquals("commons.object.string.EntityStringUtility",
                        EntityStringUtility.classString(e)));
        
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
        Assert.assertEquals("EntityStringUtility",
                EntityStringUtility.simpleClassString(EntityStringUtility.class));
        
        //inner class
        Assert.assertEquals("TestClass",
                EntityStringUtility.simpleClassString(TestUtils.getClass(TestUtilsTest.class, "TestClass")));
        Assert.assertEquals("TestInnerClass",
                EntityStringUtility.simpleClassString(TestUtils.getClass(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass")));
        
        //object
        Assert.assertEquals("EntityStringUtility",
                EntityStringUtility.simpleClassString(new EntityStringUtility()));
        
        //mock
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e ->
                Assert.assertEquals("EntityStringUtility",
                        EntityStringUtility.simpleClassString(e)));
        
        //invalid
        Assert.assertEquals("null",
                EntityStringUtility.simpleClassString(null));
        Assert.assertEquals("null",
                EntityStringUtility.simpleClassString((Object) null));
    }
    
    /**
     * JUnit test of generateInnerClassString.
     *
     * @throws Exception When there is an exception.
     * @see EntityStringUtility#generateInnerClassString(boolean, Class, String)
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testGenerateInnerClassString() throws Exception {
        //class
        Assert.assertEquals("commons.object.string.EntityStringUtility$InnerClass",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateInnerClassString", false, EntityStringUtility.class, "InnerClass"));
        Assert.assertEquals("EntityStringUtility$InnerClass",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateInnerClassString", true, EntityStringUtility.class, "InnerClass"));
        
        //invalid
        Assert.assertEquals("commons.object.string.EntityStringUtility$null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateInnerClassString", false, EntityStringUtility.class, null));
        Assert.assertEquals("EntityStringUtility$null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateInnerClassString", true, EntityStringUtility.class, null));
        Assert.assertEquals("null$InnerClass",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateInnerClassString", false, null, "InnerClass"));
        Assert.assertEquals("null$InnerClass",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateInnerClassString", true, null, "InnerClass"));
        Assert.assertEquals("null$null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateInnerClassString", false, null, null));
        Assert.assertEquals("null$null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateInnerClassString", true, null, null));
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
        Assert.assertEquals("commons.object.string.EntityStringUtility$InnerClass",
                EntityStringUtility.innerClassString(EntityStringUtility.class, "InnerClass"));
        
        //inner class
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass",
                EntityStringUtility.innerClassString(TestUtilsTest.class, "TestClass"));
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass$TestInnerClass",
                EntityStringUtility.innerClassString(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass"));
        
        //object
        Assert.assertEquals("commons.object.string.EntityStringUtility$InnerClass",
                EntityStringUtility.innerClassString(new EntityStringUtility(), "InnerClass"));
        
        //mock
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e ->
                Assert.assertEquals("commons.object.string.EntityStringUtility$InnerClass",
                        EntityStringUtility.innerClassString(e, "InnerClass")));
        
        //invalid
        Assert.assertEquals("commons.object.string.EntityStringUtility$null",
                EntityStringUtility.innerClassString(EntityStringUtility.class, null));
        Assert.assertEquals("commons.object.string.EntityStringUtility$null",
                EntityStringUtility.innerClassString(new EntityStringUtility(), null));
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
        Assert.assertEquals("EntityStringUtility$InnerClass",
                EntityStringUtility.simpleInnerClassString(EntityStringUtility.class, "InnerClass"));
        
        //inner class
        Assert.assertEquals("TestUtilsTest$TestClass",
                EntityStringUtility.simpleInnerClassString(TestUtilsTest.class, "TestClass"));
        Assert.assertEquals("TestClass$TestInnerClass",
                EntityStringUtility.simpleInnerClassString(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass"));
        
        //object
        Assert.assertEquals("EntityStringUtility$InnerClass",
                EntityStringUtility.simpleInnerClassString(new EntityStringUtility(), "InnerClass"));
        
        //mock
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e ->
                Assert.assertEquals("EntityStringUtility$InnerClass",
                        EntityStringUtility.simpleInnerClassString(e, "InnerClass")));
        
        //invalid
        Assert.assertEquals("EntityStringUtility$null",
                EntityStringUtility.simpleInnerClassString(EntityStringUtility.class, null));
        Assert.assertEquals("EntityStringUtility$null",
                EntityStringUtility.simpleInnerClassString(new EntityStringUtility(), null));
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
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testGenerateMethodString() throws Exception {
        //class
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(java.lang.Class, java.lang.String, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, EntityStringUtility.class, "method", new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("EntityStringUtility::method(Class, String, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, EntityStringUtility.class, "method", new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(commons.object.string.EntityStringUtility, java.lang.String, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, EntityStringUtility.class, "method", new Class<?>[] {Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class}));
        Assert.assertEquals("EntityStringUtility::method(EntityStringUtility, String, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, EntityStringUtility.class, "method", new Class<?>[] {Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class}));
        Assert.assertEquals("java.io.PrintStream::println()",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, PrintStream.class, "println", new Class<?>[] {}));
        Assert.assertEquals("PrintStream::println()",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, PrintStream.class, "println", new Class<?>[] {}));
        
        //invalid
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(java.lang.Class, null, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, EntityStringUtility.class, "method", new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("EntityStringUtility::method(Class, null, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, EntityStringUtility.class, "method", new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(null)",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, EntityStringUtility.class, "method", null));
        Assert.assertEquals("EntityStringUtility::method(null)",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, EntityStringUtility.class, "method", null));
        Assert.assertEquals("commons.object.string.EntityStringUtility::(java.lang.Class, java.lang.String, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, EntityStringUtility.class, "", new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("EntityStringUtility::(Class, String, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, EntityStringUtility.class, "", new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("commons.object.string.EntityStringUtility::null(java.lang.Class, java.lang.String, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, EntityStringUtility.class, null, new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("EntityStringUtility::null(Class, String, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, EntityStringUtility.class, null, new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("null::method(java.lang.Class, null, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, null, "method", new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::method(Class, null, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, null, "method", new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(java.lang.Class, null, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, null, null, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(Class, null, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, null, null, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(null)",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", false, null, null, null));
        Assert.assertEquals("null::null(null)",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateMethodString", true, null, null, null));
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
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(EntityStringUtility.class, "method", Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(commons.object.string.EntityStringUtility, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(EntityStringUtility.class, "method", Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("java.io.PrintStream::println()",
                EntityStringUtility.methodString(PrintStream.class, "println"));
        
        //object
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(new EntityStringUtility(), "method", Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(commons.object.string.EntityStringUtility, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(new EntityStringUtility(), "method", Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("java.io.PrintStream::println()",
                EntityStringUtility.methodString(new PrintStream(new BufferedOutputStream(null)), "println"));
        
        //mock
        System.out.println();
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e -> {
            Assert.assertEquals("commons.object.string.EntityStringUtility::method(java.lang.Class, java.lang.String, java.lang.Class[])",
                    EntityStringUtility.methodString(e, "method", Class.class, String.class, Class[].class));
            Assert.assertEquals("commons.object.string.EntityStringUtility::method(commons.object.string.EntityStringUtility, java.lang.String, java.lang.Class[])",
                    EntityStringUtility.methodString(e, "method", Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        });
        Stream.of(Mockito.mock(PrintStream.class), PowerMockito.mock(PrintStream.class), Mockito.spy(new PrintStream(new BufferedOutputStream(null)))).forEach(e ->
                Assert.assertEquals("org.mockito.codegen.PrintStream::println()",
                        EntityStringUtility.methodString(e, "println")));
        
        //inner class
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass::method(java.lang.String)",
                EntityStringUtility.methodString(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "method", String.class));
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass$TestInnerClass::method(java.lang.String)",
                EntityStringUtility.methodString(TestUtils.getClass(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass"), "method", String.class));
        
        //method
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass::voidMethod(int, java.lang.Long, java.lang.StringBuilder)",
                EntityStringUtility.methodString(TestUtils.getMethod(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "voidMethod", int.class, Long.class, StringBuilder.class)));
        Assert.assertEquals("java.io.PrintStream::println()",
                EntityStringUtility.methodString(TestUtils.getMethod(PrintStream.class, "println")));
        
        //invalid
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.methodString(EntityStringUtility.class, "method", Class.class, null, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.methodString(new EntityStringUtility(), "method", Class.class, null, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(null)",
                EntityStringUtility.methodString(EntityStringUtility.class, "method", (Class<?>) null));
        Assert.assertEquals("commons.object.string.EntityStringUtility::method(null)",
                EntityStringUtility.methodString(new EntityStringUtility(), "method", (Class<?>) null));
        Assert.assertEquals("commons.object.string.EntityStringUtility::(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(EntityStringUtility.class, "", Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(new EntityStringUtility(), "", Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::null(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(EntityStringUtility.class, null, Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::null(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.methodString(new EntityStringUtility(), null, Class.class, String.class, Class[].class));
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
        Assert.assertEquals("EntityStringUtility::method(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(EntityStringUtility.class, "method", Class.class, String.class, Class[].class));
        Assert.assertEquals("EntityStringUtility::method(EntityStringUtility, String, Class[])",
                EntityStringUtility.simpleMethodString(EntityStringUtility.class, "method", Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("PrintStream::println()",
                EntityStringUtility.simpleMethodString(PrintStream.class, "println"));
        
        //object
        Assert.assertEquals("EntityStringUtility::method(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(new EntityStringUtility(), "method", Class.class, String.class, Class[].class));
        Assert.assertEquals("EntityStringUtility::method(EntityStringUtility, String, Class[])",
                EntityStringUtility.simpleMethodString(new EntityStringUtility(), "method", Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("PrintStream::println()",
                EntityStringUtility.simpleMethodString(new PrintStream(new BufferedOutputStream(null)), "println"));
        
        //mock
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e -> {
            Assert.assertEquals("EntityStringUtility::method(Class, String, Class[])",
                    EntityStringUtility.simpleMethodString(e, "method", Class.class, String.class, Class[].class));
            Assert.assertEquals("EntityStringUtility::method(EntityStringUtility, String, Class[])",
                    EntityStringUtility.simpleMethodString(e, "method", Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        });
        Stream.of(Mockito.mock(PrintStream.class), PowerMockito.mock(PrintStream.class), Mockito.spy(new PrintStream(new BufferedOutputStream(null)))).forEach(e ->
                Assert.assertEquals("PrintStream::println()",
                        EntityStringUtility.simpleMethodString(e, "println")));
        
        //inner class
        Assert.assertEquals("TestClass::method(String)",
                EntityStringUtility.simpleMethodString(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "method", String.class));
        Assert.assertEquals("TestInnerClass::method(String)",
                EntityStringUtility.simpleMethodString(TestUtils.getClass(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass"), "method", String.class));
        
        //method
        Assert.assertEquals("TestClass::voidMethod(int, Long, StringBuilder)",
                EntityStringUtility.simpleMethodString(TestUtils.getMethod(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "voidMethod", int.class, Long.class, StringBuilder.class)));
        Assert.assertEquals("PrintStream::println()",
                EntityStringUtility.simpleMethodString(TestUtils.getMethod(PrintStream.class, "println")));
        
        //invalid
        Assert.assertEquals("EntityStringUtility::method(Class, null, Class[])",
                EntityStringUtility.simpleMethodString(EntityStringUtility.class, "method", Class.class, null, Class[].class));
        Assert.assertEquals("EntityStringUtility::method(Class, null, Class[])",
                EntityStringUtility.simpleMethodString(new EntityStringUtility(), "method", Class.class, null, Class[].class));
        Assert.assertEquals("EntityStringUtility::method(null)",
                EntityStringUtility.simpleMethodString(EntityStringUtility.class, "method", (Class<?>) null));
        Assert.assertEquals("EntityStringUtility::method(null)",
                EntityStringUtility.simpleMethodString(new EntityStringUtility(), "method", (Class<?>) null));
        Assert.assertEquals("EntityStringUtility::(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(EntityStringUtility.class, "", Class.class, String.class, Class[].class));
        Assert.assertEquals("EntityStringUtility::(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(new EntityStringUtility(), "", Class.class, String.class, Class[].class));
        Assert.assertEquals("EntityStringUtility::null(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(EntityStringUtility.class, null, Class.class, String.class, Class[].class));
        Assert.assertEquals("EntityStringUtility::null(Class, String, Class[])",
                EntityStringUtility.simpleMethodString(new EntityStringUtility(), null, Class.class, String.class, Class[].class));
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
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testGenerateConstructorString() throws Exception {
        //class
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(java.lang.Class, java.lang.String, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, EntityStringUtility.class, new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(Class, String, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, EntityStringUtility.class, new Class<?>[] {Class.class, String.class, Class[].class}));
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(commons.object.string.EntityStringUtility, java.lang.String, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, EntityStringUtility.class, new Class<?>[] {Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class}));
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(EntityStringUtility, String, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, EntityStringUtility.class, new Class<?>[] {Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class}));
        Assert.assertEquals("java.io.PrintStream::PrintStream()",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, PrintStream.class, new Class<?>[] {}));
        Assert.assertEquals("PrintStream::PrintStream()",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, PrintStream.class, new Class<?>[] {}));
        
        //invalid
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(java.lang.Class, null, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, EntityStringUtility.class, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(Class, null, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, EntityStringUtility.class, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(null)",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, EntityStringUtility.class, null));
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(null)",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, EntityStringUtility.class, null));
        Assert.assertEquals("null::null(java.lang.Class, null, java.lang.Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, null, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(Class, null, Class[])",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, null, new Class<?>[] {Class.class, null, Class[].class}));
        Assert.assertEquals("null::null(null)",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", false, null, null));
        Assert.assertEquals("null::null(null)",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateConstructorString", true, null, null));
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
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.constructorString(EntityStringUtility.class, Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(commons.object.string.EntityStringUtility, java.lang.String, java.lang.Class[])",
                EntityStringUtility.constructorString(EntityStringUtility.class, Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("java.io.PrintStream::PrintStream()",
                EntityStringUtility.constructorString(PrintStream.class));
        
        //object
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(java.lang.Class, java.lang.String, java.lang.Class[])",
                EntityStringUtility.constructorString(new EntityStringUtility(), Class.class, String.class, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(commons.object.string.EntityStringUtility, java.lang.String, java.lang.Class[])",
                EntityStringUtility.constructorString(new EntityStringUtility(), Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("java.io.PrintStream::PrintStream()",
                EntityStringUtility.constructorString(new PrintStream(new BufferedOutputStream(null))));
        
        //mock
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e -> {
            Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(java.lang.Class, java.lang.String, java.lang.Class[])",
                    EntityStringUtility.constructorString(e, Class.class, String.class, Class[].class));
            Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(commons.object.string.EntityStringUtility, java.lang.String, java.lang.Class[])",
                    EntityStringUtility.constructorString(e, Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        });
        Stream.of(Mockito.mock(PrintStream.class), PowerMockito.mock(PrintStream.class), Mockito.spy(new PrintStream(new BufferedOutputStream(null)))).forEach(e ->
                Assert.assertEquals("org.mockito.codegen.PrintStream::PrintStream()",
                        EntityStringUtility.constructorString(e)));
        
        //inner class
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass::TestClass(java.lang.String)",
                EntityStringUtility.constructorString(TestUtils.getClass(TestUtilsTest.class, "TestClass"), String.class));
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass$TestInnerClass::TestInnerClass(java.lang.String)",
                EntityStringUtility.constructorString(TestUtils.getClass(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass"), String.class));
        
        //constructor
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass::TestClass(java.lang.String, java.lang.Exception, int, java.lang.Long, boolean)",
                EntityStringUtility.constructorString(TestUtils.getConstructor(TestUtils.getClass(TestUtilsTest.class, "TestClass"), String.class, Exception.class, int.class, Long.class, boolean.class)));
        Assert.assertEquals("java.io.PrintStream::PrintStream(java.io.OutputStream)",
                EntityStringUtility.constructorString(TestUtils.getConstructor(PrintStream.class, OutputStream.class)));
        
        //invalid
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.constructorString(EntityStringUtility.class, Class.class, null, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(java.lang.Class, null, java.lang.Class[])",
                EntityStringUtility.constructorString(new EntityStringUtility(), Class.class, null, Class[].class));
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(null)",
                EntityStringUtility.constructorString(EntityStringUtility.class, (Class<?>) null));
        Assert.assertEquals("commons.object.string.EntityStringUtility::EntityStringUtility(null)",
                EntityStringUtility.constructorString(new EntityStringUtility(), (Class<?>) null));
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
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(Class, String, Class[])",
                EntityStringUtility.simpleConstructorString(EntityStringUtility.class, Class.class, String.class, Class[].class));
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(EntityStringUtility, String, Class[])",
                EntityStringUtility.simpleConstructorString(EntityStringUtility.class, Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("PrintStream::PrintStream()",
                EntityStringUtility.simpleConstructorString(PrintStream.class));
        
        //object
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(Class, String, Class[])",
                EntityStringUtility.simpleConstructorString(new EntityStringUtility(), Class.class, String.class, Class[].class));
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(EntityStringUtility, String, Class[])",
                EntityStringUtility.simpleConstructorString(new EntityStringUtility(), Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        Assert.assertEquals("PrintStream::PrintStream()",
                EntityStringUtility.simpleConstructorString(new PrintStream(new BufferedOutputStream(null))));
        
        //mock
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e -> {
            Assert.assertEquals("EntityStringUtility::EntityStringUtility(Class, String, Class[])",
                    EntityStringUtility.simpleConstructorString(e, Class.class, String.class, Class[].class));
            Assert.assertEquals("EntityStringUtility::EntityStringUtility(EntityStringUtility, String, Class[])",
                    EntityStringUtility.simpleConstructorString(e, Mockito.mock(EntityStringUtility.class).getClass(), String.class, Class[].class));
        });
        Stream.of(Mockito.mock(PrintStream.class), PowerMockito.mock(PrintStream.class), Mockito.spy(new PrintStream(new BufferedOutputStream(null)))).forEach(e ->
                Assert.assertEquals("PrintStream::PrintStream()",
                        EntityStringUtility.simpleConstructorString(e)));
        
        //inner class
        Assert.assertEquals("TestClass::TestClass(String)",
                EntityStringUtility.simpleConstructorString(TestUtils.getClass(TestUtilsTest.class, "TestClass"), String.class));
        Assert.assertEquals("TestInnerClass::TestInnerClass(String)",
                EntityStringUtility.simpleConstructorString(TestUtils.getClass(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass"), String.class));
        
        //constructor
        Assert.assertEquals("TestClass::TestClass(String, Exception, int, Long, boolean)",
                EntityStringUtility.simpleConstructorString(TestUtils.getConstructor(TestUtils.getClass(TestUtilsTest.class, "TestClass"), String.class, Exception.class, int.class, Long.class, boolean.class)));
        Assert.assertEquals("PrintStream::PrintStream(OutputStream)",
                EntityStringUtility.simpleConstructorString(TestUtils.getConstructor(PrintStream.class, OutputStream.class)));
        
        //invalid
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(Class, null, Class[])",
                EntityStringUtility.simpleConstructorString(EntityStringUtility.class, Class.class, null, Class[].class));
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(Class, null, Class[])",
                EntityStringUtility.simpleConstructorString(new EntityStringUtility(), Class.class, null, Class[].class));
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(null)",
                EntityStringUtility.simpleConstructorString(EntityStringUtility.class, (Class<?>) null));
        Assert.assertEquals("EntityStringUtility::EntityStringUtility(null)",
                EntityStringUtility.simpleConstructorString(new EntityStringUtility(), (Class<?>) null));
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
    @SuppressWarnings("InstantiationOfUtilityClass")
    @Test
    public void testGenerateFieldString() throws Exception {
        //class
        Assert.assertEquals("commons.object.string.EntityStringUtility::field",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", false, EntityStringUtility.class, "field"));
        Assert.assertEquals("EntityStringUtility::field",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", true, EntityStringUtility.class, "field"));
        
        //invalid
        Assert.assertEquals("commons.object.string.EntityStringUtility::",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", false, EntityStringUtility.class, ""));
        Assert.assertEquals("EntityStringUtility::",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", true, EntityStringUtility.class, ""));
        Assert.assertEquals("commons.object.string.EntityStringUtility::null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", false, EntityStringUtility.class, null));
        Assert.assertEquals("EntityStringUtility::null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", true, EntityStringUtility.class, null));
        Assert.assertEquals("null::field",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", false, null, "field"));
        Assert.assertEquals("null::field",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", true, null, "field"));
        Assert.assertEquals("null::",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", false, null, ""));
        Assert.assertEquals("null::",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", true, null, ""));
        Assert.assertEquals("null::null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", false, null, null));
        Assert.assertEquals("null::null",
                TestUtils.invokeMethod(EntityStringUtility.class, "generateFieldString", true, null, null));
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
        Assert.assertEquals("commons.object.string.EntityStringUtility::field",
                EntityStringUtility.fieldString(EntityStringUtility.class, "field"));
        
        //object
        Assert.assertEquals("commons.object.string.EntityStringUtility::field",
                EntityStringUtility.fieldString(new EntityStringUtility(), "field"));
        
        //mock
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e ->
                Assert.assertEquals("commons.object.string.EntityStringUtility::field",
                        EntityStringUtility.fieldString(e, "field")));
        
        //inner class
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass::field",
                EntityStringUtility.fieldString(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "field"));
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass$TestInnerClass::field",
                EntityStringUtility.fieldString(TestUtils.getClass(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass"), "field"));
        
        //constructor
        Assert.assertEquals("commons.test.TestUtilsTest$TestClass::field0",
                EntityStringUtility.fieldString(TestUtils.getField(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "field0")));
        Assert.assertEquals("java.io.PrintStream::autoFlush",
                EntityStringUtility.fieldString(TestUtils.getField(PrintStream.class, "autoFlush")));
        
        //invalid
        Assert.assertEquals("commons.object.string.EntityStringUtility::null",
                EntityStringUtility.fieldString(EntityStringUtility.class, null));
        Assert.assertEquals("commons.object.string.EntityStringUtility::null",
                EntityStringUtility.fieldString(new EntityStringUtility(), null));
        Assert.assertEquals("commons.object.string.EntityStringUtility::null",
                EntityStringUtility.fieldString(Mockito.mock(EntityStringUtility.class), null));
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
        Assert.assertEquals("EntityStringUtility::field",
                EntityStringUtility.simpleFieldString(EntityStringUtility.class, "field"));
        
        //object
        Assert.assertEquals("EntityStringUtility::field",
                EntityStringUtility.simpleFieldString(new EntityStringUtility(), "field"));
        
        //mock
        Stream.of(Mockito.mock(EntityStringUtility.class), PowerMockito.mock(EntityStringUtility.class), Mockito.spy(new EntityStringUtility())).forEach(e ->
                Assert.assertEquals("EntityStringUtility::field",
                        EntityStringUtility.simpleFieldString(e, "field")));
        
        //inner class
        Assert.assertEquals("TestClass::field",
                EntityStringUtility.simpleFieldString(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "field"));
        Assert.assertEquals("TestInnerClass::field",
                EntityStringUtility.simpleFieldString(TestUtils.getClass(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "TestInnerClass"), "field"));
        
        //constructor
        Assert.assertEquals("TestClass::field0",
                EntityStringUtility.simpleFieldString(TestUtils.getField(TestUtils.getClass(TestUtilsTest.class, "TestClass"), "field0")));
        Assert.assertEquals("PrintStream::autoFlush",
                EntityStringUtility.simpleFieldString(TestUtils.getField(PrintStream.class, "autoFlush")));
        
        //invalid
        Assert.assertEquals("EntityStringUtility::null",
                EntityStringUtility.simpleFieldString(EntityStringUtility.class, null));
        Assert.assertEquals("EntityStringUtility::null",
                EntityStringUtility.simpleFieldString(new EntityStringUtility(), null));
        Assert.assertEquals("EntityStringUtility::null",
                EntityStringUtility.simpleFieldString(Mockito.mock(EntityStringUtility.class), null));
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
