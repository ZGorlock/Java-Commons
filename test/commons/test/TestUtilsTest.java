/*
 * File:    TestUtilsTest.java
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import commons.lambda.function.Action;
import commons.math.BoundUtility;
import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
import commons.object.string.StringUtility;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of TestUtils.
 *
 * @see TestUtils
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({TestUtils.class})
public class TestUtilsTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(TestUtilsTest.class);
    
    
    //Static Fields
    
    /**
     * A list of classes to use for testing.
     */
    private static final Class<?>[] classes = new Class[] {TestClass.class, TestSubClass.class};
    
    /**
     * A list of instances to use for testing.
     */
    private static final Object[] instances = new Object[] {new TestClass(), new TestSubClass(), Mockito.spy(new TestClass()), Mockito.spy(new TestSubClass())};
    
    /**
     * The AssertWrapper class.
     */
    private static Class<?> AssertWrapper = TestUtils.getClass(TestUtils.class, "AssertWrapper");
    
    
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
     * @see TestUtils#DELTA
     * @see TestUtils#DELTA_FLOAT
     * @see TestUtils#DELTA_DOUBLE
     * @see TestUtils#DELTA_BIG
     */
    @Test
    public void testConstants() throws Exception {
        //constants
        Assert.assertEquals(1E-9, TestUtils.DELTA, 1E-9);
        Assert.assertEquals(1E-3f, TestUtils.DELTA_FLOAT, 1E-3f);
        Assert.assertEquals(1E-12, TestUtils.DELTA_DOUBLE, 1E-12);
        Assert.assertEquals(BigDecimal.valueOf(1E-36), TestUtils.DELTA_BIG);
    }
    
    /**
     * JUnit test of assertException.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertException(Class, String, Action)
     * @see TestUtils#assertException(Class, Action)
     * @see TestUtils#assertException(Action)
     */
    @Test
    public void testAssertException() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        //exception
        TestUtils.assertException(() ->
                new BigDecimal("15a4"));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //exception, correct throwable
        TestUtils.assertException(NumberFormatException.class, () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                .invoke("assertEquals",
                        ArgumentMatchers.eq("Expected code to produce a NumberFormatException but instead it produced a NumberFormatException"),
                        ArgumentMatchers.eq(NumberFormatException.class), ArgumentMatchers.eq(NumberFormatException.class));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //exception, correct throwable, correct message
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(2))
                .invoke("assertEquals",
                        ArgumentMatchers.eq("Expected code to produce a NumberFormatException but instead it produced a NumberFormatException"),
                        ArgumentMatchers.eq(NumberFormatException.class), ArgumentMatchers.eq(NumberFormatException.class));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                .invoke("assertEquals",
                        ArgumentMatchers.eq("Expected the error message of the NumberFormatException to be: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\" but the error message was: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\""),
                        ArgumentMatchers.eq("Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark."), ArgumentMatchers.eq("Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark."));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //exception, correct throwable, wrong message
        TestUtils.assertException(NumberFormatException.class, "Could not parse BigDecimal", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(3))
                .invoke("assertEquals",
                        ArgumentMatchers.eq("Expected code to produce a NumberFormatException but instead it produced a NumberFormatException"),
                        ArgumentMatchers.eq(NumberFormatException.class), ArgumentMatchers.eq(NumberFormatException.class));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                .invoke("assertEquals",
                        ArgumentMatchers.eq("Expected the error message of the NumberFormatException to be: \"Could not parse BigDecimal\" but the error message was: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\""),
                        ArgumentMatchers.eq("Could not parse BigDecimal"), ArgumentMatchers.eq("Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark."));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //exception, wrong throwable
        TestUtils.assertException(NullPointerException.class, () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                .invoke("assertEquals",
                        ArgumentMatchers.eq("Expected code to produce a NullPointerException but instead it produced a NumberFormatException"),
                        ArgumentMatchers.eq(NullPointerException.class), ArgumentMatchers.eq(NumberFormatException.class));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //exception, wrong throwable, wrong message
        TestUtils.assertException(NullPointerException.class, "Could not parse BigDecimal", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(2))
                .invoke("assertEquals",
                        ArgumentMatchers.eq("Expected code to produce a NullPointerException but instead it produced a NumberFormatException"),
                        ArgumentMatchers.eq(NullPointerException.class), ArgumentMatchers.eq(NumberFormatException.class));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                .invoke("assertEquals",
                        ArgumentMatchers.eq("Expected the error message of the NullPointerException to be: \"Could not parse BigDecimal\" but the error message was: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\""),
                        ArgumentMatchers.eq("Could not parse BigDecimal"), ArgumentMatchers.eq("Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark."));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //exception, null throwable, wrong message
        TestUtils.assertException(null, "Could not parse BigDecimal", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                .invoke("assertEquals",
                        ArgumentMatchers.eq("Expected the error message of the exception to be: \"Could not parse BigDecimal\" but the error message was: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\""),
                        ArgumentMatchers.eq("Could not parse BigDecimal"), ArgumentMatchers.eq("Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark."));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //no exception, wrong throwable
        TestUtils.assertException(NullPointerException.class, () ->
                new BigDecimal("1564"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                .invoke("fail",
                        ArgumentMatchers.eq("Expected code to produce a NullPointerException but no exception was produced"));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //no exception, null throwable
        TestUtils.assertException(null, () ->
                new BigDecimal("1564"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                .invoke("fail",
                        ArgumentMatchers.eq("Expected code to produce an exception but no exception was produced"));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //no exception
        TestUtils.assertException(() ->
                new BigDecimal("1564"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(2))
                .invoke("fail",
                        ArgumentMatchers.eq("Expected code to produce an exception but no exception was produced"));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertNoException.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertNoException(Action)
     */
    @Test
    public void testAssertNoException() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        //exception
        TestUtils.assertNoException(() ->
                new BigDecimal("15a4"));
        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                .invoke("fail",
                        ArgumentMatchers.eq("Expected code to produce no Exception but instead it produced a NumberFormatException"));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //no exception
        TestUtils.assertNoException(() ->
                new BigDecimal("1564"));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertClassExists.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertClassExists(String)
     * @see TestUtils#assertClassExists(Class, String)
     */
    @Test
    public void testAssertClassExists() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        //exists
        TestUtils.assertClassExists(TestUtilsTest.class, "TestClass");
        TestUtils.assertClassExists("commons.test.TestUtilsTest$TestSubClass");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertClassExists(TestUtilsTest.class, "MissingClass");
        TestUtils.assertClassExists("commons.test.TestUtilsTest$TestClass$MissingClass");
        List.of("Expected class TestUtilsTest$MissingClass to exist but it does not",
                "Expected class commons.test.TestUtilsTest$TestClass$MissingClass to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not a class
        TestUtils.assertClassExists(TestUtilsTest.class, "TestInterface");
        TestUtils.assertClassExists("commons.test.TestUtilsTest$TestClass$Enum0");
        List.of("Expected class TestUtilsTest$TestInterface to exist but it does not",
                "Expected class commons.test.TestUtilsTest$TestClass$Enum0 to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(TestUtilsTest.class, null).forEach(e -> {
            TestUtils.assertClassExists(e, "");
            TestUtils.assertClassExists(e, null);
            List.of("Expected class " + StringUtility.classString(e) + "$ to exist but it does not",
                    "Expected class " + StringUtility.classString(e) + "$null to exist but it does not"
            ).forEach(failure -> {
                try {
                    PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                            .invoke("fail", ArgumentMatchers.eq(failure));
                } catch (Exception ignored) {
                }
            });
        });
        Stream.of("", null).forEach(e -> {
            TestUtils.assertClassExists(e);
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq("Expected class " + e + " to exist but it does not"));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertClassDoesNotExist.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertClassDoesNotExist(String)
     * @see TestUtils#assertClassDoesNotExist(Class, String)
     */
    @Test
    public void testAssertClassDoesNotExist() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        //exists
        TestUtils.assertClassDoesNotExist(TestUtilsTest.class, "TestClass");
        TestUtils.assertClassDoesNotExist("commons.test.TestUtilsTest$TestSubClass");
        List.of("Expected class TestUtilsTest$TestClass to not exist but it does",
                "Expected class commons.test.TestUtilsTest$TestSubClass to not exist but it does"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertClassDoesNotExist(TestUtilsTest.class, "MissingClass");
        TestUtils.assertClassDoesNotExist("commons.test.TestUtilsTest$TestClass$MissingClass");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not a class
        TestUtils.assertClassDoesNotExist(TestUtilsTest.class, "TestInterface");
        TestUtils.assertClassDoesNotExist("commons.test.TestUtilsTest$TestClass$Enum0");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(TestUtilsTest.class, null).forEach(e -> {
            TestUtils.assertClassDoesNotExist(e, "");
            TestUtils.assertClassDoesNotExist(e, null);
        });
        Stream.of("", null).forEach(TestUtils::assertClassDoesNotExist);
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertInterfaceExists.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertInterfaceExists(String)
     * @see TestUtils#assertInterfaceExists(Class, String)
     */
    @Test
    public void testAssertInterfaceExists() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        //exists
        TestUtils.assertInterfaceExists(TestUtilsTest.class, "TestInterface");
        TestUtils.assertInterfaceExists("commons.test.TestUtilsTest$TestInterface");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertInterfaceExists(TestUtilsTest.class, "MissingInterface");
        TestUtils.assertInterfaceExists("commons.test.TestUtilsTest$TestClass$MissingInterface");
        List.of("Expected interface TestUtilsTest$MissingInterface to exist but it does not",
                "Expected interface commons.test.TestUtilsTest$TestClass$MissingInterface to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not an interface
        TestUtils.assertInterfaceExists(TestUtilsTest.class, "TestClass");
        TestUtils.assertInterfaceExists("commons.test.TestUtilsTest$TestClass$Enum0");
        List.of("Expected interface TestUtilsTest$TestClass to exist but it does not",
                "Expected interface commons.test.TestUtilsTest$TestClass$Enum0 to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(TestUtilsTest.class, null).forEach(e -> {
            TestUtils.assertInterfaceExists(e, "");
            TestUtils.assertInterfaceExists(e, null);
            List.of("Expected interface " + StringUtility.classString(e) + "$ to exist but it does not",
                    "Expected interface " + StringUtility.classString(e) + "$null to exist but it does not"
            ).forEach(failure -> {
                try {
                    PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                            .invoke("fail", ArgumentMatchers.eq(failure));
                } catch (Exception ignored) {
                }
            });
        });
        Stream.of("", null).forEach(e -> {
            TestUtils.assertInterfaceExists(e);
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq("Expected interface " + e + " to exist but it does not"));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertInterfaceDoesNotExist.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertInterfaceDoesNotExist(String)
     * @see TestUtils#assertInterfaceDoesNotExist(Class, String)
     */
    @Test
    public void testAssertInterfaceDoesNotExist() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        //exists
        TestUtils.assertInterfaceDoesNotExist(TestUtilsTest.class, "TestInterface");
        TestUtils.assertInterfaceDoesNotExist("commons.test.TestUtilsTest$TestInterface");
        List.of("Expected interface TestUtilsTest$TestInterface to not exist but it does",
                "Expected interface commons.test.TestUtilsTest$TestInterface to not exist but it does"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertInterfaceDoesNotExist(TestUtilsTest.class, "MissingInterface");
        TestUtils.assertInterfaceDoesNotExist("commons.test.TestUtilsTest$MissingInterface");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not an interface
        TestUtils.assertInterfaceDoesNotExist(TestUtilsTest.class, "TestClass");
        TestUtils.assertInterfaceDoesNotExist("commons.test.TestUtilsTest$TestClass$Enum0");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(TestUtilsTest.class, null).forEach(e -> {
            TestUtils.assertInterfaceDoesNotExist(e, "");
            TestUtils.assertInterfaceDoesNotExist(e, null);
        });
        Stream.of("", null).forEach(TestUtils::assertInterfaceDoesNotExist);
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertEnumExists.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertEnumExists(String)
     * @see TestUtils#assertEnumExists(Class, String)
     */
    @Test
    public void testAssertEnumExists() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        //exists
        TestUtils.assertEnumExists(TestClass.class, "Enum0");
        TestUtils.assertEnumExists("commons.test.TestUtilsTest$TestClass$Enum0");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertEnumExists(TestUtilsTest.class, "MissingEnum");
        TestUtils.assertEnumExists("commons.test.TestUtilsTest$TestClass$MissingEnum");
        List.of("Expected enum TestUtilsTest$MissingEnum to exist but it does not",
                "Expected enum commons.test.TestUtilsTest$TestClass$MissingEnum to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not an enum
        TestUtils.assertEnumExists(TestUtilsTest.class, "TestClass");
        TestUtils.assertEnumExists("commons.test.TestUtilsTest$TestInterface");
        List.of("Expected enum TestUtilsTest$TestClass to exist but it does not",
                "Expected enum commons.test.TestUtilsTest$TestInterface to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(TestUtilsTest.class, null).forEach(e -> {
            TestUtils.assertEnumExists(e, "");
            TestUtils.assertEnumExists(e, null);
            List.of("Expected enum " + StringUtility.classString(e) + "$ to exist but it does not",
                    "Expected enum " + StringUtility.classString(e) + "$null to exist but it does not"
            ).forEach(failure -> {
                try {
                    PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                            .invoke("fail", ArgumentMatchers.eq(failure));
                } catch (Exception ignored) {
                }
            });
        });
        Stream.of("", null).forEach(e -> {
            TestUtils.assertEnumExists(e);
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq("Expected enum " + e + " to exist but it does not"));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertEnumDoesNotExist.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertEnumDoesNotExist(String)
     * @see TestUtils#assertEnumDoesNotExist(Class, String)
     */
    @Test
    public void testAssertEnumDoesNotExist() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        //exists
        TestUtils.assertEnumDoesNotExist(TestClass.class, "Enum0");
        TestUtils.assertEnumDoesNotExist("commons.test.TestUtilsTest$TestClass$Enum1");
        List.of("Expected enum TestClass$Enum0 to not exist but it does",
                "Expected enum commons.test.TestUtilsTest$TestClass$Enum1 to not exist but it does"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertEnumDoesNotExist(TestUtilsTest.class, "MissingEnum");
        TestUtils.assertEnumDoesNotExist("commons.test.TestUtilsTest$MissingEnum");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not an enum
        TestUtils.assertEnumDoesNotExist(TestUtilsTest.class, "TestClass");
        TestUtils.assertEnumDoesNotExist("commons.test.TestUtilsTest$TestInterface");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(TestUtilsTest.class, null).forEach(e -> {
            TestUtils.assertEnumDoesNotExist(e, "");
            TestUtils.assertEnumDoesNotExist(e, null);
        });
        Stream.of("", null).forEach(TestUtils::assertEnumDoesNotExist);
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertMethodExists.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertMethodExists(Class, String, Class[])
     * @see TestUtils#assertMethodExists(Object, String, Class[])
     */
    @Test
    public void testAssertMethodExists() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        final Consumer<Object[]> methodExistsAsserter = (Object[] params) -> {
            final Object caller = params[0];
            final String methodName = (String) params[1];
            final Class<?>[] arguments = (Class<?>[]) params[2];
            if (caller instanceof Class<?>) {
                TestUtils.assertMethodExists((Class<?>) caller, methodName, arguments);
            } else {
                TestUtils.assertMethodExists(caller, methodName, arguments);
            }
        };
        
        //exists
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            methodExistsAsserter.accept(new Object[] {e, "voidMethod", new Class<?>[] {int.class, Long.class, StringBuilder.class}});
            methodExistsAsserter.accept(new Object[] {e, "voidMethod", new Class<?>[] {Integer.class, long.class, StringBuilder.class}});
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            methodExistsAsserter.accept(new Object[] {e, "voidMethod", new Class<?>[] {int.class, long.class, String.class}});
            methodExistsAsserter.accept(new Object[] {e, "voidMethod", new Class<?>[] {Integer.class, Long.class, String.class}});
            methodExistsAsserter.accept(new Object[] {e, "missingMethod", new Class<?>[] {}});
        });
        Arrays.stream(classes).forEach(e -> List.of(
                "Expected method " + StringUtility.methodString(e, "voidMethod", int.class, long.class, String.class) + " to exist but it does not",
                "Expected method " + StringUtility.methodString(e, "voidMethod", Integer.class, Long.class, String.class) + " to exist but it does not",
                "Expected method " + StringUtility.methodString(e, "missingMethod") + " to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(3))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        }));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> {
            methodExistsAsserter.accept(new Object[] {e, "", new Class<?>[] {String.class}});
            methodExistsAsserter.accept(new Object[] {e, "", new Class<?>[] {null}});
            methodExistsAsserter.accept(new Object[] {e, null, new Class<?>[] {String.class}});
            methodExistsAsserter.accept(new Object[] {e, null, new Class<?>[] {null}});
        });
        Stream.of(classes, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> List.of(
                "Expected method " + StringUtility.methodString(e, "", String.class) + " to exist but it does not",
                "Expected method " + StringUtility.methodString(e, "", (Class<?>) null) + " to exist but it does not",
                "Expected method " + StringUtility.methodString(e, null, String.class) + " to exist but it does not",
                "Expected method " + StringUtility.methodString(e, null, (Class<?>) null) + " to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times((e == null) ? 2 : 3))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        }));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertMethodDoesNotExist.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertMethodDoesNotExist(Class, String, Class[])
     * @see TestUtils#assertMethodDoesNotExist(Object, String, Class[])
     */
    @Test
    public void testAssertMethodDoesNotExist() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        final Consumer<Object[]> methodDoesNotExistAsserter = (Object[] params) -> {
            final Object caller = params[0];
            final String methodName = (String) params[1];
            final Class<?>[] arguments = (Class<?>[]) params[2];
            if (caller instanceof Class<?>) {
                TestUtils.assertMethodDoesNotExist((Class<?>) caller, methodName, arguments);
            } else {
                TestUtils.assertMethodDoesNotExist(caller, methodName, arguments);
            }
        };
        
        //exists
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            methodDoesNotExistAsserter.accept(new Object[] {e, "voidMethod", new Class<?>[] {int.class, Long.class, StringBuilder.class}});
            methodDoesNotExistAsserter.accept(new Object[] {e, "voidMethod", new Class<?>[] {Integer.class, long.class, StringBuilder.class}});
        });
        Arrays.stream(classes).forEach(e -> List.of(
                "Expected method " + StringUtility.methodString(e, "voidMethod", int.class, Long.class, StringBuilder.class) + " to not exist but it does",
                "Expected method " + StringUtility.methodString(e, "voidMethod", Integer.class, long.class, StringBuilder.class) + " to not exist but it does"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(3))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        }));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            methodDoesNotExistAsserter.accept(new Object[] {e, "voidMethod", new Class<?>[] {int.class, long.class, String.class}});
            methodDoesNotExistAsserter.accept(new Object[] {e, "voidMethod", new Class<?>[] {Integer.class, Long.class, String.class}});
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> {
            methodDoesNotExistAsserter.accept(new Object[] {e, "missingMethod", new Class<?>[] {String.class}});
            methodDoesNotExistAsserter.accept(new Object[] {e, "missingMethod", new Class<?>[] {null}});
            methodDoesNotExistAsserter.accept(new Object[] {e, "", new Class<?>[] {String.class}});
            methodDoesNotExistAsserter.accept(new Object[] {e, "", new Class<?>[] {null}});
            methodDoesNotExistAsserter.accept(new Object[] {e, null, new Class<?>[] {String.class}});
            methodDoesNotExistAsserter.accept(new Object[] {e, null, new Class<?>[] {null}});
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertConstructorExists.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertConstructorExists(Class, Class[])
     * @see TestUtils#assertConstructorExists(Object, Class[])
     */
    @Test
    public void testAssertConstructorExists() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        final Consumer<Object[]> constructorExistsAsserter = (Object[] params) -> {
            final Object caller = params[0];
            final Class<?>[] arguments = (Class<?>[]) params[1];
            if (caller instanceof Class<?>) {
                TestUtils.assertConstructorExists((Class<?>) caller, arguments);
            } else {
                TestUtils.assertConstructorExists(caller, arguments);
            }
        };
        
        //exists
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            constructorExistsAsserter.accept(new Object[] {e, new Class<?>[] {String.class, Exception.class, int.class, Long.class, boolean.class}});
            constructorExistsAsserter.accept(new Object[] {e, new Class<?>[] {String.class, Exception.class, Integer.class, Long.class, Boolean.class}});
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            constructorExistsAsserter.accept(new Object[] {e, new Class<?>[] {StringBuilder.class, Exception.class, int.class, Long.class, boolean.class}});
            constructorExistsAsserter.accept(new Object[] {e, new Class<?>[] {StringBuilder.class, Exception.class, Integer.class, Long.class, Boolean.class}});
        });
        Arrays.stream(classes).forEach(e -> List.of(
                "Expected constructor " + StringUtility.constructorString(e, StringBuilder.class, Exception.class, int.class, Long.class, boolean.class) + " to exist but it does not",
                "Expected constructor " + StringUtility.constructorString(e, StringBuilder.class, Exception.class, Integer.class, Long.class, Boolean.class) + " to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(3))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        }));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> {
            constructorExistsAsserter.accept(new Object[] {e, new Class<?>[] {String.class}});
            constructorExistsAsserter.accept(new Object[] {e, new Class<?>[] {null}});
        });
        Stream.of(classes, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> List.of(
                "Expected constructor " + StringUtility.constructorString(e, String.class) + " to exist but it does not",
                "Expected constructor " + StringUtility.constructorString(e, (Class<?>) null) + " to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times((e == null) ? 2 : 3))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        }));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertConstructorDoesNotExist.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertConstructorDoesNotExist(Class, Class[])
     * @see TestUtils#assertConstructorDoesNotExist(Object, Class[])
     */
    @Test
    public void testAssertConstructorDoesNotExist() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        final Consumer<Object[]> constructorDoesNotExistAsserter = (Object[] params) -> {
            final Object caller = params[0];
            final Class<?>[] arguments = (Class<?>[]) params[1];
            if (caller instanceof Class<?>) {
                TestUtils.assertConstructorDoesNotExist((Class<?>) caller, arguments);
            } else {
                TestUtils.assertConstructorDoesNotExist(caller, arguments);
            }
        };
        
        //exists
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            constructorDoesNotExistAsserter.accept(new Object[] {e, new Class<?>[] {String.class, Exception.class, int.class, Long.class, boolean.class}});
            constructorDoesNotExistAsserter.accept(new Object[] {e, new Class<?>[] {String.class, Exception.class, Integer.class, Long.class, Boolean.class}});
        });
        Arrays.stream(classes).forEach(e -> List.of(
                "Expected constructor " + StringUtility.constructorString(e, String.class, Exception.class, int.class, Long.class, boolean.class) + " to not exist but it does",
                "Expected constructor " + StringUtility.constructorString(e, String.class, Exception.class, Integer.class, Long.class, Boolean.class) + " to not exist but it does"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(3))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        }));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            constructorDoesNotExistAsserter.accept(new Object[] {e, new Class<?>[] {StringBuilder.class, Exception.class, int.class, Long.class, boolean.class}});
            constructorDoesNotExistAsserter.accept(new Object[] {e, new Class<?>[] {StringBuilder.class, Exception.class, Integer.class, Long.class, Boolean.class}});
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> {
            constructorDoesNotExistAsserter.accept(new Object[] {e, new Class<?>[] {String.class}});
            constructorDoesNotExistAsserter.accept(new Object[] {e, new Class<?>[] {null}});
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertFieldExists.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertFieldExists(Class, String)
     * @see TestUtils#assertFieldExists(Object, String)
     */
    @Test
    public void testAssertFieldExists() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        final Consumer<Object[]> fieldExistsAsserter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            if (caller instanceof Class<?>) {
                TestUtils.assertFieldExists((Class<?>) caller, fieldName);
            } else {
                TestUtils.assertFieldExists(caller, fieldName);
            }
        };
        
        //exists
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            fieldExistsAsserter.accept(new Object[] {e, "field2"});
            fieldExistsAsserter.accept(new Object[] {e, "field7"});
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            fieldExistsAsserter.accept(new Object[] {e, "Field7"});
            fieldExistsAsserter.accept(new Object[] {e, "field15"});
        });
        Arrays.stream(classes).forEach(e -> List.of(
                "Expected field " + StringUtility.fieldString(e, "Field7") + " to exist but it does not",
                "Expected field " + StringUtility.fieldString(e, "field15") + " to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(3))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        }));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> {
            fieldExistsAsserter.accept(new Object[] {e, ""});
            fieldExistsAsserter.accept(new Object[] {e, null});
        });
        Stream.of(classes, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> List.of(
                "Expected field " + StringUtility.fieldString(e, "") + " to exist but it does not",
                "Expected field " + StringUtility.fieldString(e, null) + " to exist but it does not"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times((e == null) ? 2 : 3))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        }));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of assertFieldDoesNotExist.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertFieldDoesNotExist(Class, String)
     * @see TestUtils#assertFieldDoesNotExist(Object, String)
     */
    @Test
    public void testAssertFieldDoesNotExist() throws Exception {
        PowerMockito.mockStatic(AssertWrapper);
        
        final Consumer<Object[]> fieldDoesNotExistAsserter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            if (caller instanceof Class<?>) {
                TestUtils.assertFieldDoesNotExist((Class<?>) caller, fieldName);
            } else {
                TestUtils.assertFieldDoesNotExist(caller, fieldName);
            }
        };
        
        //exists
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            fieldDoesNotExistAsserter.accept(new Object[] {e, "field2"});
            fieldDoesNotExistAsserter.accept(new Object[] {e, "field7"});
        });
        Arrays.stream(classes).forEach(e -> List.of(
                "Expected field " + StringUtility.fieldString(e, "field2") + " to not exist but it does",
                "Expected field " + StringUtility.fieldString(e, "field7") + " to not exist but it does"
        ).forEach(failure -> {
            try {
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(3))
                        .invoke("fail", ArgumentMatchers.eq(failure));
            } catch (Exception ignored) {
            }
        }));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            fieldDoesNotExistAsserter.accept(new Object[] {e, "Field7"});
            fieldDoesNotExistAsserter.accept(new Object[] {e, "field15"});
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> {
            fieldDoesNotExistAsserter.accept(new Object[] {e, ""});
            fieldDoesNotExistAsserter.accept(new Object[] {e, null});
        });
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
    }
    
    /**
     * JUnit test of getClass.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getClass(String)
     * @see TestUtils#getClass(Class, String)
     */
    @Test
    public void testGetClass() throws Exception {
        Class<?> retrievedClass;
        
        //standard
        retrievedClass = TestUtils.getClass(TestUtilsTest.class, "TestClass");
        Assert.assertNotNull(retrievedClass);
        Assert.assertEquals("TestClass", retrievedClass.getSimpleName());
        TestUtils.assertFieldExists(retrievedClass, "field8");
        TestUtils.assertConstructorExists(retrievedClass, String.class, int.class, Long.class, boolean.class, Exception.class);
        TestUtils.assertMethodExists(retrievedClass, "method10");
        TestUtils.assertMethodExists(retrievedClass, "method3", String.class);
        TestUtils.assertMethodDoesNotExist(retrievedClass, "subMethod", String.class, Long.class);
        
        //qualified name
        retrievedClass = TestUtils.getClass("commons.test.TestUtilsTest$TestSubClass");
        Assert.assertNotNull(retrievedClass);
        Assert.assertEquals("TestSubClass", retrievedClass.getSimpleName());
        TestUtils.assertFieldExists(retrievedClass, "field9");
        TestUtils.assertConstructorExists(retrievedClass, String.class, int.class, Long.class, boolean.class, Exception.class);
        TestUtils.assertMethodExists(retrievedClass, "method10");
        TestUtils.assertMethodExists(retrievedClass, "method3", String.class);
        TestUtils.assertMethodExists(retrievedClass, "subMethod", String.class, Long.class);
        
        //not a class
        Assert.assertNull(TestUtils.getClass(TestUtilsTest.class, "TestInterface"));
        Assert.assertNull(TestUtils.getClass(TestClass.class, "Enum0"));
        
        //invalid
        Assert.assertNull(TestUtils.getClass(TestClass.class, "MissingClass"));
        Assert.assertNull(TestUtils.getClass("commons.test.TestUtilsTest$TestClass$MissingClass"));
        Stream.of(TestClass.class, null).forEach(e -> {
            Assert.assertNull(TestUtils.getClass(e, ""));
            Assert.assertNull(TestUtils.getClass(e, null));
        });
        Stream.of("", null).forEach(e -> Assert.assertNull(TestUtils.getClass(e)));
    }
    
    /**
     * JUnit test of getInterface.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getInterface(String)
     * @see TestUtils#getInterface(Class, String)
     */
    @Test
    public void testGetInterface() throws Exception {
        Class<?> retrievedInterface;
        
        //standard
        retrievedInterface = TestUtils.getInterface(TestUtilsTest.class, "TestInterface");
        Assert.assertNotNull(retrievedInterface);
        Assert.assertEquals("TestInterface", retrievedInterface.getSimpleName());
        TestUtils.assertFieldExists(retrievedInterface, "BASE_NAME");
        TestUtils.assertMethodExists(retrievedInterface, "getName");
        Assert.assertEquals("TestInterface", TestUtils.invokeInterfaceDefaultMethod(retrievedInterface, "getName"));
        
        //qualified name
        retrievedInterface = TestUtils.getInterface("commons.test.TestUtilsTest$TestInterface");
        Assert.assertNotNull(retrievedInterface);
        Assert.assertEquals("TestInterface", retrievedInterface.getSimpleName());
        TestUtils.assertFieldExists(retrievedInterface, "BASE_NAME");
        TestUtils.assertMethodExists(retrievedInterface, "getName");
        Assert.assertEquals("TestInterface", TestUtils.invokeInterfaceDefaultMethod(retrievedInterface, "getName"));
        
        //not an interface
        Assert.assertNull(TestUtils.getInterface(TestUtilsTest.class, "TestClass"));
        Assert.assertNull(TestUtils.getInterface(TestClass.class, "Enum0"));
        
        //invalid
        Assert.assertNull(TestUtils.getInterface(TestClass.class, "MissingInterface"));
        Assert.assertNull(TestUtils.getInterface("commons.test.TestUtilsTest$TestClass$MissingInterface"));
        Stream.of(TestClass.class, null).forEach(e -> {
            Assert.assertNull(TestUtils.getInterface(e, ""));
            Assert.assertNull(TestUtils.getInterface(e, null));
        });
        Stream.of("", null).forEach(e -> Assert.assertNull(TestUtils.getInterface(e)));
    }
    
    /**
     * JUnit test of getEnum.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getEnum(String)
     * @see TestUtils#getEnum(Class, String)
     */
    @Test
    public void testGetEnum() throws Exception {
        Class<?> retrievedEnum;
        
        //standard
        retrievedEnum = TestUtils.getEnum(TestClass.class, "Enum0");
        Assert.assertNotNull(retrievedEnum);
        Assert.assertEquals("Enum0", retrievedEnum.getSimpleName());
        Assert.assertEquals(3, retrievedEnum.getEnumConstants().length);
        Assert.assertEquals("PUBLIC_VALUE_1 | PUBLIC_VALUE_2 | PUBLIC_VALUE_3",
                Arrays.stream(retrievedEnum.getEnumConstants()).map(Object::toString).collect(Collectors.joining(" | ")));
        TestUtils.assertMethodExists(retrievedEnum, "getEnumType");
        Assert.assertEquals("Public", ((TestClass.Enum0) retrievedEnum.getEnumConstants()[0]).getEnumType());
        
        //qualified name
        retrievedEnum = TestUtils.getEnum("commons.test.TestUtilsTest$TestClass$Enum1");
        Assert.assertNotNull(retrievedEnum);
        Assert.assertEquals("Enum1", retrievedEnum.getSimpleName());
        Assert.assertEquals(3, retrievedEnum.getEnumConstants().length);
        Assert.assertEquals("PRIVATE_VALUE_1 | PRIVATE_VALUE_2 | PRIVATE_VALUE_3",
                Arrays.stream(retrievedEnum.getEnumConstants()).map(Object::toString).collect(Collectors.joining(" | ")));
        TestUtils.assertMethodExists(retrievedEnum, "getEnumType");
        Assert.assertEquals("Private", TestUtils.invokeMethod(TestClass.Enum1.class, "getEnumType"));
        
        //not an enum
        Assert.assertNull(TestUtils.getEnum(TestUtilsTest.class, "TestClass"));
        Assert.assertNull(TestUtils.getEnum(TestUtilsTest.class, "TestInterface"));
        
        //invalid
        Assert.assertNull(TestUtils.getEnum(TestClass.class, "MissingEnum"));
        Assert.assertNull(TestUtils.getEnum("commons.test.TestUtilsTest$TestClass$MissingEnum"));
        Stream.of(TestClass.class, null).forEach(e -> {
            Assert.assertNull(TestUtils.getEnum(e, ""));
            Assert.assertNull(TestUtils.getEnum(e, null));
        });
        Stream.of("", null).forEach(e -> Assert.assertNull(TestUtils.getEnum(e)));
    }
    
    /**
     * JUnit test of getAllMethods.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getAllMethods(Class)
     * @see TestUtils#getAllMethods(Object)
     */
    @Test
    public void testGetAllMethods() throws Exception {
        final List<String> testClassMethods = List.of(
                "String getName()",
                "void voidMethod(int,Long,StringBuilder)",
                "long method7()",
                "int method8(String)",
                "float method9(int)",
                "boolean method10()",
                "String method11(boolean,String,float,char[])",
                "void staticVoidMethod(boolean,Long,StringBuilder)",
                "double method1()",
                "boolean method3(String)",
                "byte method5(boolean,int,BigDecimal)"
        );
        final List<String> testSubClassMethods = List.of(
                "boolean subMethod(String,Long)",
                "String staticSubMethod(String,long)"
        );
        final List<String> objectMethods = List.of(
                "void registerNatives()",
                "Class getClass()",
                "int hashCode()",
                "boolean equals(Object)",
                "Object clone() throws CloneNotSupportedException",
                "String toString()",
                "void notify()",
                "void notifyAll()",
                "void wait() throws InterruptedException",
                "void wait(long) throws InterruptedException",
                "void wait(long,int) throws InterruptedException",
                "void finalize() throws Throwable"
        );
        
        final Function<Method, String> methodNameGetter = (Method method) ->
                StringUtility.containsAny(method.toString(), new String[] {"IndicateReloadClass", "MockitoMock"}) ? null :
                method.toString().replaceAll("(?:commons\\.test\\.TestUtilsTest\\$)|(?:\\$[^.($]+)", "")
                        .replaceAll("(?:(?:java\\.(?:lang|math))|(?:org\\.mockito)|(?:internal\\.creation\\.bytebuddy))\\.", "")
                        .replaceAll("(?:TestClass|TestSubClass|Object|Mock)\\.", "")
                        .replaceAll("(?:public|private|protected|native|default|abstract|final|static)\\s", "");
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            final List<Method> methods = (e instanceof Class<?>) ? TestUtils.getAllMethods((Class<?>) e) : TestUtils.getAllMethods(e);
            final List<String> methodStrings = methods.stream().map(methodNameGetter).filter(Objects::nonNull).distinct().collect(Collectors.toList());
            final List<String> expectedMethods = Stream.of(testClassMethods, objectMethods,
                            (((e instanceof TestSubClass) || (e == TestSubClass.class)) ? testSubClassMethods : Collections.emptyList()))
                    .flatMap(Collection::stream).map(Object::toString).collect(Collectors.toList());
            Assert.assertEquals(expectedMethods.size(), methodStrings.size());
            Assert.assertTrue(ListUtility.equals(expectedMethods, methodStrings, false));
        });
        
        //invalid
        Assert.assertArrayEquals(Collections.emptyList().toArray(), TestUtils.getAllMethods(null).toArray());
        Assert.assertArrayEquals(Collections.emptyList().toArray(), TestUtils.getAllMethods((Object) null).toArray());
    }
    
    /**
     * JUnit test of getMethod.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getMethod(Class, String, Class[])
     * @see TestUtils#getMethod(Object, String, Class[])
     */
    @Test
    public void testGetMethod() throws Exception {
        final Function<Object[], Method> methodGetter = (Object[] params) -> {
            final Object caller = params[0];
            final String methodName = (String) params[1];
            final Class<?>[] arguments = (Class<?>[]) params[2];
            return (caller instanceof Class<?>) ?
                   TestUtils.getMethod((Class<?>) caller, methodName, arguments) :
                   TestUtils.getMethod(caller, methodName, arguments);
        };
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            Assert.assertNotNull(methodGetter.apply(new Object[] {e, "voidMethod", new Class<?>[] {int.class, Long.class, StringBuilder.class}}));
            Assert.assertNotNull(methodGetter.apply(new Object[] {e, "voidMethod", new Class<?>[] {Integer.class, long.class, StringBuilder.class}}));
            Assert.assertNotNull(methodGetter.apply(new Object[] {e, "method7", new Class<?>[] {}}));
            Assert.assertNotNull(methodGetter.apply(new Object[] {e, "method8", new Class<?>[] {String.class}}));
            Assert.assertNotNull(methodGetter.apply(new Object[] {e, "method9", new Class<?>[] {int.class}}));
            Assert.assertNotNull(methodGetter.apply(new Object[] {e, "method10", new Class<?>[] {}}));
            Assert.assertNotNull(methodGetter.apply(new Object[] {e, "method11", new Class<?>[] {boolean.class, String.class, float.class, char[].class}}));
            Assert.assertNull(methodGetter.apply(new Object[] {e, "voidMethod", new Class<?>[] {int.class, long.class, String.class}}));
            Assert.assertNull(methodGetter.apply(new Object[] {e, "voidMethod", new Class<?>[] {Integer.class, Long.class, String.class}}));
            Assert.assertNull(methodGetter.apply(new Object[] {e, "method7", new Class<?>[] {int.class}}));
            Assert.assertNull(methodGetter.apply(new Object[] {e, "method8", new Class<?>[] {int.class}}));
            Assert.assertNull(methodGetter.apply(new Object[] {e, "missingMethod", new Class<?>[] {}}));
        });
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> {
            Assert.assertNull(methodGetter.apply(new Object[] {e, "", new Class<?>[] {String.class}}));
            Assert.assertNull(methodGetter.apply(new Object[] {e, "", new Class<?>[] {null}}));
            Assert.assertNull(methodGetter.apply(new Object[] {e, null, new Class<?>[] {String.class}}));
            Assert.assertNull(methodGetter.apply(new Object[] {e, null, new Class<?>[] {null}}));
        });
    }
    
    /**
     * JUnit test of getAllConstructors.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getAllConstructors(Class)
     * @see TestUtils#getAllConstructors(Object)
     */
    @Test
    public void testGetAllConstructors() throws Exception {
        final List<String> testClassConstructors = List.of(
                "TestClass()",
                "TestClass(String,Exception,int,Long,boolean)",
                "TestClass(Exception,String,int,Long,boolean)",
                "TestClass(String,int,Long,boolean,Exception)",
                "TestClass(Exception,int,Long,boolean,String)"
        );
        final List<String> testSubClassConstructors = List.of(
                "TestSubClass()",
                "TestSubClass(int,Long,boolean,String,Exception)"
        );
        final List<String> objectConstructors = List.of(
                "Object()"
        );
        
        final Function<Constructor<?>, String> constructorNameGetter = (Constructor<?> constructor) ->
                StringUtility.containsAny(constructor.toString(), new String[] {"IndicateReloadClass", "MockitoMock"}) ? null :
                constructor.toString()
                        .replaceAll("(?:commons\\.test\\.TestUtilsTest\\$)|(?:\\$[^.($]+)", "")
                        .replaceAll("(?:(?:java\\.(?:lang|math))|(?:org\\.mockito)|(?:internal\\.creation\\.bytebuddy))\\.", "")
                        .replaceAll("(?:TestClass|TestSubClass|Object|Mock)\\.", "")
                        .replaceAll("(?:public|private|protected|native|default|abstract|final|static)\\s", "");
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            final List<Constructor<?>> constructors = (e instanceof Class<?>) ? TestUtils.getAllConstructors((Class<?>) e) : TestUtils.getAllConstructors(e);
            final List<String> constructorStrings = constructors.stream().map(constructorNameGetter).filter(Objects::nonNull).distinct().collect(Collectors.toList());
            final List<String> expectedConstructors = Stream.of(testClassConstructors, objectConstructors,
                            (((e instanceof TestSubClass) || (e == TestSubClass.class)) ? testSubClassConstructors : Collections.emptyList()))
                    .flatMap(Collection::stream).map(Object::toString).collect(Collectors.toList());
            Assert.assertEquals(expectedConstructors.size(), constructorStrings.size());
            Assert.assertTrue(ListUtility.equals(expectedConstructors, constructorStrings, false));
        });
        
        //invalid
        Assert.assertArrayEquals(Collections.emptyList().toArray(), TestUtils.getAllConstructors(null).toArray());
        Assert.assertArrayEquals(Collections.emptyList().toArray(), TestUtils.getAllConstructors((Object) null).toArray());
    }
    
    /**
     * JUnit test of getConstructor.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getConstructor(Class, Class[])
     * @see TestUtils#getConstructor(Object, Class[])
     */
    @Test
    public void testGetConstructor() throws Exception {
        final Function<Object[], Constructor<?>> constructorGetter = (Object[] params) -> {
            final Object caller = params[0];
            final Class<?>[] arguments = (Class<?>[]) params[1];
            return (caller instanceof Class<?>) ?
                   TestUtils.getConstructor((Class<?>) caller, arguments) :
                   TestUtils.getConstructor(caller, arguments);
        };
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            Assert.assertNotNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {}}));
            Assert.assertNotNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {String.class, Exception.class, int.class, Long.class, boolean.class}}));
            Assert.assertNotNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {String.class, Exception.class, Integer.class, Long.class, Boolean.class}}));
            Assert.assertNotNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {String.class, int.class, Long.class, boolean.class, Exception.class}}));
            Assert.assertNotNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {Exception.class, int.class, Long.class, boolean.class, String.class}}));
            Assert.assertNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {StringBuilder.class}}));
            Assert.assertNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {StringBuilder.class, Exception.class, int.class, Long.class, boolean.class}}));
            Assert.assertNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {StringBuilder.class, Exception.class, Integer.class, Long.class, Boolean.class}}));
        });
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> {
            Assert.assertNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {String.class}}));
            Assert.assertNull(constructorGetter.apply(new Object[] {e, new Class<?>[] {null}}));
        });
    }
    
    /**
     * JUnit test of getAllFields.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getAllFields(Class)
     * @see TestUtils#getAllFields(Object)
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
                field.toString()
                        .replaceAll("(?:commons\\.test\\.TestUtilsTest\\$)|(?:\\$[^.($]+)", "")
                        .replaceAll("(?:(?:java\\.(?:lang|math))|(?:org\\.mockito)|(?:internal\\.creation\\.bytebuddy))\\.", "")
                        .replaceAll("(?:TestClass|TestSubClass|Object|Mock)\\.", "")
                        .replaceAll("(?:public|private|protected|native|default|abstract|final|static)\\s", "");
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            final List<Field> fields = (e instanceof Class<?>) ? TestUtils.getAllFields((Class<?>) e) : TestUtils.getAllFields(e);
            final List<String> fieldStrings = fields.stream().map(fieldNameGetter).filter(Objects::nonNull).distinct().collect(Collectors.toList());
            final List<String> expectedFields = Stream.of(testClassFields,
                            (((e instanceof TestSubClass) || (e == TestSubClass.class)) ? testSubClassFields : Collections.emptyList()))
                    .flatMap(Collection::stream).map(Object::toString).collect(Collectors.toList());
            Assert.assertEquals(expectedFields.size(), fieldStrings.size());
            Assert.assertTrue(ListUtility.equals(expectedFields, fieldStrings, false));
        });
        
        //invalid
        Assert.assertArrayEquals(Collections.emptyList().toArray(), TestUtils.getAllFields(null).toArray());
        Assert.assertArrayEquals(Collections.emptyList().toArray(), TestUtils.getAllFields((Object) null).toArray());
    }
    
    /**
     * JUnit test of getField.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getField(Class, String)
     * @see TestUtils#getField(Object, String)
     */
    @Test
    public void testGetField() throws Exception {
        final Function<Object[], Field> fieldGetter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            return (caller instanceof Class<?>) ?
                   TestUtils.getField((Class<?>) caller, fieldName) :
                   TestUtils.getField(caller, fieldName);
        };
        
        //standard
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            Assert.assertNotNull(fieldGetter.apply(new Object[] {e, "field2"}));
            Assert.assertNotNull(fieldGetter.apply(new Object[] {e, "field5"}));
            Assert.assertNotNull(fieldGetter.apply(new Object[] {e, "field7"}));
            Assert.assertNotNull(fieldGetter.apply(new Object[] {e, "field10"}));
            Assert.assertNull(fieldGetter.apply(new Object[] {e, "Field7"}));
            Assert.assertNull(fieldGetter.apply(new Object[] {e, "field15"}));
            Assert.assertNull(fieldGetter.apply(new Object[] {e, "missingField"}));
        });
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(e -> {
            Assert.assertNull(fieldGetter.apply(new Object[] {e, ""}));
            Assert.assertNull(fieldGetter.apply(new Object[] {e, null}));
        });
    }
    
    /**
     * JUnit test of getFieldValue.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getFieldValue(Class, String)
     * @see TestUtils#getFieldValue(Class, Class, String)
     * @see TestUtils#getFieldValue(Object, String)
     * @see TestUtils#getFieldValue(Object, Class, String)
     */
    @Test
    public void testGetFieldValue() throws Exception {
        final Map<Object, Object[]> values = MapUtility.mapOf(
                new ImmutablePair<>(StringUtility.classString(classes[0]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test"}),
                new ImmutablePair<>(StringUtility.classString(classes[1]), new Object[] {18, 6.4488121, "test", true, "subclass tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test", -644, -116.103f}));
        final Map<Object, Object[]> mockValues = MapUtility.mapOf(
                new ImmutablePair<>(StringUtility.classString(classes[0]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test"}),
                new ImmutablePair<>(StringUtility.classString(classes[1]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test", -644, -116.103f}));
        
        final Function<Object[], Object> fieldValueGetter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            return (caller instanceof Class<?>) ?
                   TestUtils.getFieldValue((Class<?>) caller, fieldName) :
                   TestUtils.getFieldValue(caller, fieldName);
        };
        final Consumer<Object[]> getFieldValueValidator = (Object[] params) -> {
            final Object caller = params[0];
            final int index = (int) params[1];
            final String fieldName = "field" + index;
            final Object expected = (MockUtil.isSpy(caller) ? mockValues : values).get(StringUtility.classString(params[0]))[index];
            Assert.assertEquals(expected, fieldValueGetter.apply(new Object[] {caller, fieldName}));
        };
        
        //standard
        IntStream.rangeClosed(0, values.get(StringUtility.classString(classes[1])).length).forEach(i -> {
            final String fieldName = "field" + i;
            final boolean isPresent = BoundUtility.inArrayBounds(i, values.get(StringUtility.classString(classes[1])));
            final boolean isStatic = isPresent && Modifier.isStatic(TestUtils.getField(classes[1], fieldName).getModifiers());
            Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
                final boolean isClass = (e instanceof Class<?>);
                final boolean hasField = BoundUtility.inArrayBounds(i, values.get(StringUtility.classString(e)));
                if (!hasField) {
                    TestUtils.assertException(AssertionError.class, "Attempted to get the value of the field " + StringUtility.fieldString(e, fieldName) + " but an exception occurred: [FieldNotFoundException: " + StringUtility.fieldString(e, fieldName) + ']', () ->
                            fieldValueGetter.apply(new Object[] {e, fieldName}));
                } else if (isClass && !isStatic) {
                    TestUtils.assertException(AssertionError.class, () ->
                            fieldValueGetter.apply(new Object[] {e, fieldName}));
                } else {
                    getFieldValueValidator.accept(new Object[] {e, i});
                }
            });
        });
        
        //type
        Arrays.stream(classes).forEach(e -> TestUtils.assertNoException(() -> {
            int field0 = TestUtils.getFieldValue(e, int.class, "field0");
            double field1 = TestUtils.getFieldValue(e, double.class, "field1");
            String field4 = TestUtils.getFieldValue(e, String.class, "field4");
            byte field5 = TestUtils.getFieldValue(e, byte.class, "field5");
            TestUtils.assertException(ClassCastException.class, "class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')", () -> {
                String incorrect = TestUtils.getFieldValue(e, String.class, "field0");
            });
        }));
        Arrays.stream(instances).forEach(e -> TestUtils.assertNoException(() -> {
            String field6 = TestUtils.getFieldValue(e, String.class, "field6");
            long field7 = TestUtils.getFieldValue(e, long.class, "field7");
            float field9 = TestUtils.getFieldValue(e, float.class, "field9");
            boolean field10 = TestUtils.getFieldValue(e, boolean.class, "field10");
            TestUtils.assertException(ClassCastException.class, "class java.lang.String cannot be cast to class java.lang.Boolean (java.lang.String and java.lang.Boolean are in module java.base of loader 'bootstrap')", () -> {
                boolean incorrect = TestUtils.getFieldValue(e, boolean.class, "field6");
            });
        }));
        
        //invalid
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            TestUtils.assertException(AssertionError.class, "Attempted to get the value of the field " + StringUtility.fieldString(e, "") + " but an exception occurred: [FieldNotFoundException: " + StringUtility.fieldString(e, "") + ']', () ->
                    fieldValueGetter.apply(new Object[] {e, ""}));
            TestUtils.assertException(AssertionError.class, "Attempted to get the value of the field " + StringUtility.fieldString(e, null) + " but an exception occurred: [FieldNotFoundException: " + StringUtility.fieldString(e, null) + ']', () ->
                    fieldValueGetter.apply(new Object[] {e, null}));
        });
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.getFieldValue(null, int.class, "field0"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.getFieldValue(null, (Class<?>) null, "field0"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.getFieldValue((Object) null, int.class, "field0"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.getFieldValue((Object) null, (Class<?>) null, "field0"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.getFieldValue(null, "field0"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.getFieldValue((Object) null, "field0"));
    }
    
    /**
     * JUnit test of setFieldValue.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#setFieldValue(Class, String, Object)
     * @see TestUtils#setFieldValue(Object, String, Object)
     */
    @Test
    public void testSetFieldValue() throws Exception {
        final Map<Object, Object[]> values = MapUtility.mapOf(
                new ImmutablePair<>(classes[0].getSimpleName(), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test"}),
                new ImmutablePair<>(classes[1].getSimpleName(), new Object[] {18, 6.4488121, "test", true, "subclass tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test", -644, -116.103f}));
        final Map<Object, Object[]> mockValues = MapUtility.mapOf(
                new ImmutablePair<>(StringUtility.classString(classes[0]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test"}),
                new ImmutablePair<>(StringUtility.classString(classes[1]), new Object[] {18, 6.4488121, "test", true, "tset", (byte) 4, "another test", 874561564112154L, -44, 7.66f, true, "last test", -644, -116.103f}));
        final Map<Object, Object[]> setValues = MapUtility.mapOf(
                new ImmutablePair<>(classes[0].getSimpleName(), new Object[] {7, 0.221548773, "different", false, "tset2", (byte) 3, "an even other test", 156423157842311L, 1568, 3.46f, false, "the last test"}),
                new ImmutablePair<>(classes[1].getSimpleName(), new Object[] {10, 6.4488121, "another different", true, "subclass tset2", (byte) 5, "an even other test", 156423157842311L, 1568, 3.46f, false, "the last test", 61, 8.9f}));
        
        final Function<Object[], Object> fieldValueGetter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            return (caller instanceof Class<?>) ?
                   TestUtils.getFieldValue((Class<?>) caller, fieldName) :
                   TestUtils.getFieldValue(caller, fieldName);
        };
        final Function<Object[], Boolean> fieldValueSetter = (Object[] params) -> {
            final Object caller = params[0];
            final String fieldName = (String) params[1];
            final Object set = params[2];
            return (caller instanceof Class<?>) ?
                   TestUtils.setFieldValue((Class<?>) caller, fieldName, set) :
                   TestUtils.setFieldValue(caller, fieldName, set);
        };
        final Consumer<Object[]> setFieldValueValidator = (Object[] params) -> {
            final Object caller = params[0];
            final int index = (int) params[1];
            final String fieldName = "field" + index;
            final Object expected = (MockUtil.isSpy(caller) ? mockValues : values).get(StringUtility.classString(params[0]))[index];
            final Object set = setValues.get(StringUtility.classString(params[0]))[index];
            Assert.assertEquals(expected, fieldValueGetter.apply(new Object[] {caller, fieldName}));
            Assert.assertTrue(fieldValueSetter.apply(new Object[] {caller, fieldName, set}));
            Assert.assertEquals(set, fieldValueGetter.apply(new Object[] {caller, fieldName}));
            Assert.assertTrue(fieldValueSetter.apply(new Object[] {caller, fieldName, expected}));
            Assert.assertEquals(expected, fieldValueGetter.apply(new Object[] {caller, fieldName}));
        };
        
        //standard
        IntStream.rangeClosed(0, values.get(classes[1].getSimpleName()).length).forEach(i -> {
            final String fieldName = "field" + i;
            final boolean isPresent = BoundUtility.inArrayBounds(i, values.get(StringUtility.classString(classes[1])));
            final boolean isStatic = isPresent && Modifier.isStatic(TestUtils.getField(classes[1], fieldName).getModifiers());
            Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
                final boolean isClass = (e instanceof Class<?>);
                final boolean hasField = BoundUtility.inArrayBounds(i, values.get(StringUtility.classString(e)));
                if (!hasField) {
                    TestUtils.assertException(AssertionError.class, "Attempted to set the value of the field " + StringUtility.fieldString(e, fieldName) + " but an exception occurred: [FieldNotFoundException: " + StringUtility.fieldString(e, fieldName) + ']', () ->
                            fieldValueSetter.apply(new Object[] {e, fieldName, null}));
                } else if (isClass && !isStatic) {
                    TestUtils.assertException(AssertionError.class, () ->
                            fieldValueSetter.apply(new Object[] {e, fieldName, null}));
                } else {
                    setFieldValueValidator.accept(new Object[] {e, i});
                }
            });
        });
        
        //invalid
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            final boolean isClass = (e instanceof Class<?>);
            TestUtils.assertException(AssertionError.class, "Attempted to set the value of the field " + StringUtility.fieldString(e, (isClass ? "field0" : "field8")) + " but an exception occurred: [IllegalArgumentException: Can not set " + (isClass ? "static" : "final") + " int field commons.test.TestUtilsTest$" + StringUtility.fieldString(TestClass.class, (isClass ? "field0" : "field8")).replace("::", ".") + " to null value]", () -> {
                Assert.assertEquals(int.class, TestUtils.getField(e, (isClass ? "field0" : "field8")).getType());
                fieldValueSetter.apply(new Object[] {e, (isClass ? "field0" : "field8"), null});
            });
            TestUtils.assertException(AssertionError.class, "Attempted to set the value of the field " + StringUtility.fieldString(e, (isClass ? "field3" : "field10")) + " but an exception occurred: [IllegalArgumentException: Can not set " + (isClass ? "static" : "final") + " boolean field commons.test.TestUtilsTest$" + StringUtility.fieldString(TestClass.class, (isClass ? "field3" : "field10")).replace("::", ".") + " to java.lang.String]", () -> {
                Assert.assertEquals(boolean.class, TestUtils.getField(e, (isClass ? "field3" : "field10")).getType());
                fieldValueSetter.apply(new Object[] {e, (isClass ? "field3" : "field10"), "test"});
            });
            TestUtils.assertException(AssertionError.class, "Attempted to set the value of the field " + StringUtility.fieldString(e, (isClass ? "field4" : "field11")) + " but an exception occurred: [IllegalArgumentException: Can not set " + (isClass ? "static " : "") + "java.lang.String field commons.test.TestUtilsTest$" + StringUtility.fieldString((isClass ? e : TestClass.class), (isClass ? "field4" : "field11")).replace("::", ".") + " to java.lang.Boolean]", () -> {
                Assert.assertEquals(String.class, TestUtils.getField(e, (isClass ? "field4" : "field11")).getType());
                fieldValueSetter.apply(new Object[] {e, (isClass ? "field4" : "field11"), false});
            });
            TestUtils.assertException(AssertionError.class, "Attempted to set the value of the field " + StringUtility.fieldString(e, "") + " but an exception occurred: [FieldNotFoundException: " + StringUtility.fieldString(e, "") + ']', () ->
                    TestUtils.setFieldValue(e, "", false));
            TestUtils.assertException(AssertionError.class, "Attempted to set the value of the field " + StringUtility.fieldString(e, null) + " but an exception occurred: [FieldNotFoundException: " + StringUtility.fieldString(e, null) + ']', () ->
                    TestUtils.setFieldValue(e, null, null));
        });
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.setFieldValue(null, "field0", 11));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.setFieldValue((Object) null, "field0", 11));
    }
    
    /**
     * JUnit test of testInvokeMethod.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#invokeMethod(Class, String, Object...)
     * @see TestUtils#invokeMethod(Class, Class, String, Object...)
     * @see TestUtils#invokeMethod(Object, String, Object...)
     * @see TestUtils#invokeMethod(Object, Class, String, Object...)
     */
    @Test
    public void testInvokeMethod() throws Exception {
        final StringBuilder builder = new StringBuilder();
        
        final Function<Object[], Object> methodInvoker = (Object[] params) -> {
            final Object caller = params[0];
            final String methodName = (String) params[1];
            final Object[] arguments = (Object[]) params[2];
            return (caller instanceof Class<?>) ?
                   TestUtils.invokeMethod((Class<?>) caller, methodName, arguments) :
                   TestUtils.invokeMethod(caller, methodName, arguments);
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
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            final boolean isSubclass = !StringUtility.classString(e).equals(StringUtility.classString(classes[0]));
            invokeMethodValidator.accept(new Object[] {e, "staticVoidMethod", new Object[] {isSubclass, 1966L, builder},
                                                       null, ((isSubclass ? "subclass " : "") + "static void method hit: (" + isSubclass + ", 1966)")});
            invokeMethodValidator.accept(new Object[] {e, "staticVoidMethod", new Object[] {false, null, builder},
                                                       null, ((isSubclass ? "subclass " : "") + "static void method hit: (false, null)")});
            invokeMethodValidator.accept(new Object[] {e, "method1", new Object[] {}, 6.4488121, ""});
            invokeMethodValidator.accept(new Object[] {e, "method3", new Object[] {"testing"}, true, ""});
            invokeMethodValidator.accept(new Object[] {e, "method5", new Object[] {false, 54, BigDecimal.ZERO}, (byte) 4, ""});
            if (e instanceof Class<?>) {
                TestUtils.assertException(AssertionError.class, "Attempted to invoke the method " + StringUtility.methodString(e, "voidMethod", Integer.class, Long.class, StringBuilder.class) + " but an exception occurred: [IllegalArgumentException: object is not an instance of declaring class]", () ->
                        methodInvoker.apply(new Object[] {e, "voidMethod", new Object[] {180, 150L, builder}}));
                TestUtils.assertException(AssertionError.class, "Attempted to invoke the method " + StringUtility.methodString(e, "method7") + " but an exception occurred: [IllegalArgumentException: object is not an instance of declaring class]", () ->
                        methodInvoker.apply(new Object[] {e, "method7", new Object[] {}}));
            } else {
                invokeMethodValidator.accept(new Object[] {e, "voidMethod", new Object[] {180, (long) 150, builder},
                                                           null, ((isSubclass ? "subclass " : "") + "void method hit: (" + (isSubclass ? "-" : "") + "180, 150)")});
                invokeMethodValidator.accept(new Object[] {e, "voidMethod", new Object[] {-55, null, builder},
                                                           null, ((isSubclass ? "subclass " : "") + "void method hit: (" + (isSubclass ? "" : "-") + "55, null)")});
                invokeMethodValidator.accept(new Object[] {e, "method7", new Object[] {}, 874561564112154L, ""});
                invokeMethodValidator.accept(new Object[] {e, "method8", new Object[] {"testing"}, -44, ""});
                invokeMethodValidator.accept(new Object[] {e, "method9", new Object[] {-13}, 7.66f, ""});
                invokeMethodValidator.accept(new Object[] {e, "method10", new Object[] {}, true, ""});
                invokeMethodValidator.accept(new Object[] {e, "method11", new Object[] {true, "testing", 19.41f, new char[] {'t', 'e', 's', 't'}}, "last test", ""});
            }
        });
        
        //return type
        Arrays.stream(classes).forEach(e -> TestUtils.assertNoException(() -> {
            double method1 = TestUtils.invokeMethod(e, double.class, "method1");
            boolean method3 = TestUtils.invokeMethod(e, boolean.class, "method3", "test");
            byte method5 = TestUtils.invokeMethod(e, byte.class, "method5", true, -5, BigDecimal.ONE);
            TestUtils.assertException(ClassCastException.class, "class java.lang.Double cannot be cast to class java.lang.String (java.lang.Double and java.lang.String are in module java.base of loader 'bootstrap')", () -> {
                String incorrect = TestUtils.invokeMethod(e, String.class, "method1");
            });
        }));
        Arrays.stream(instances).forEach(e -> TestUtils.assertNoException(() -> {
            long method7 = TestUtils.invokeMethod(e, long.class, "method7");
            int method8 = TestUtils.invokeMethod(e, int.class, "method8", "test");
            float method9 = TestUtils.invokeMethod(e, float.class, "method9", 88);
            boolean method10 = TestUtils.invokeMethod(e, boolean.class, "method10");
            String method11 = TestUtils.invokeMethod(e, String.class, "method11", false, "test", 1.6f, new char[] {'t'});
            TestUtils.assertException(ClassCastException.class, "class java.lang.Long cannot be cast to class java.lang.Boolean (java.lang.Long and java.lang.Boolean are in module java.base of loader 'bootstrap')", () -> {
                boolean incorrect = TestUtils.invokeMethod(e, boolean.class, "method7");
            });
        }));
        
        //invalid
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            final boolean isClass = (e instanceof Class<?>);
            TestUtils.assertException(AssertionError.class, (isClass ?
                                                             "Attempted to invoke the method " + StringUtility.methodString(e, "method1", String.class) + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, "method1", String.class) + ']' :
                                                             "Attempted to invoke the method " + StringUtility.methodString(e, "method7", String.class) + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, "method7", String.class) + ']'), () ->
                    TestUtils.invokeMethod(e, (isClass ? "method1" : "method7"), "invalid argument"));
            TestUtils.assertException(AssertionError.class, (isClass ?
                                                             "Attempted to invoke the method " + StringUtility.methodString(e, "method3", Integer.class) + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, "method3", Integer.class) + ']' :
                                                             "Attempted to invoke the method " + StringUtility.methodString(e, "method8", Integer.class) + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, "method8", Integer.class) + ']'), () ->
                    TestUtils.invokeMethod(e, (isClass ? "method3" : "method8"), 76));
            TestUtils.assertException(AssertionError.class, (isClass ?
                                                             "Attempted to invoke the method " + StringUtility.methodString(e, "method3") + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, "method3") + ']' :
                                                             "Attempted to invoke the method " + StringUtility.methodString(e, "method8") + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, "method8") + ']'), () ->
                    TestUtils.invokeMethod(e, (isClass ? "method3" : "method8")));
            TestUtils.assertException(AssertionError.class, (isClass ?
                                                             "Attempted to invoke the method " + StringUtility.methodString(e, "staticVoidMethod", null, Long.class, StringBuilder.class) + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, "staticVoidMethod", null, Long.class, StringBuilder.class) + ']' :
                                                             "Attempted to invoke the method " + StringUtility.methodString(e, "voidMethod", null, Long.class, StringBuilder.class) + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, "voidMethod", null, Long.class, StringBuilder.class) + ']'), () ->
                    TestUtils.invokeMethod(e, (isClass ? "staticVoidMethod" : "voidMethod"), null, 150L, new StringBuilder()));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the method " + StringUtility.methodString(e, "") + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, "") + ']', () ->
                    TestUtils.invokeMethod(e, ""));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the method " + StringUtility.methodString(e, null) + " but an exception occurred: [MethodNotFoundException: " + StringUtility.methodString(e, null) + ']', () ->
                    TestUtils.invokeMethod(e, null));
        });
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(null, double.class, "method1"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(null, (Class<?>) null, "method1"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod((Object) null, double.class, "method1"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod((Object) null, (Class<?>) null, "method1"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(null, "method1"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod((Object) null, "method1"));
    }
    
    /**
     * JUnit test of invokeConstructor.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#invokeConstructor(Class, Object...)
     */
    @Test
    public void testInvokeConstructor() throws Exception {
        final String stringArgument = "test";
        final Exception exceptionArgument = new Exception();
        final int intArgument = -994;
        final long longArgument = 180L;
        final boolean booleanArgument = true;
        
        final Function<Object[], Object> constructorInvoker = (Object[] params) -> {
            final Class<?> caller = (Class<?>) params[0];
            final Object[] arguments = (Object[]) params[1];
            return TestUtils.invokeConstructor(caller, arguments);
        };
        final Consumer<Object[]> invokeConstructorValidator = (Object[] params) -> {
            final Class<?> caller = (Class<?>) params[0];
            final Object[] arguments = (Object[]) params[1];
            Object testInstance = constructorInvoker.apply(new Object[] {caller, arguments});
            Assert.assertEquals(caller, testInstance.getClass());
            Assert.assertArrayEquals(arguments, TestUtils.getFieldValue(testInstance, Object[].class, "arguments"));
        };
        
        //standard
        invokeConstructorValidator.accept(new Object[] {TestClass.class, new Object[] {}});
        invokeConstructorValidator.accept(new Object[] {TestClass.class, new Object[] {stringArgument, exceptionArgument, intArgument, longArgument, booleanArgument}});
        invokeConstructorValidator.accept(new Object[] {TestClass.class, new Object[] {exceptionArgument, stringArgument, intArgument, longArgument, booleanArgument}});
        invokeConstructorValidator.accept(new Object[] {TestClass.class, new Object[] {stringArgument, intArgument, longArgument, booleanArgument, exceptionArgument}});
        invokeConstructorValidator.accept(new Object[] {TestClass.class, new Object[] {exceptionArgument, intArgument, longArgument, booleanArgument, stringArgument}});
        invokeConstructorValidator.accept(new Object[] {TestClass.class, new Object[] {exceptionArgument, intArgument, longArgument, booleanArgument, null}});
        
        //subclass
        invokeConstructorValidator.accept(new Object[] {TestSubClass.class, new Object[] {}});
        invokeConstructorValidator.accept(new Object[] {TestSubClass.class, new Object[] {intArgument, longArgument, booleanArgument, stringArgument, exceptionArgument}});
        invokeConstructorValidator.accept(new Object[] {TestSubClass.class, new Object[] {intArgument, longArgument, booleanArgument, stringArgument, null}});
        
        //invalid
        Arrays.stream(classes).forEach(e -> {
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the constructor " + StringUtility.constructorString(e, Exception.class, null, Long.class, Boolean.class, String.class) + " but an exception occurred: [ConstructorNotFoundException: " + StringUtility.constructorString(e, Exception.class, null, Long.class, Boolean.class, String.class) + ']', () ->
                    constructorInvoker.apply(new Object[] {e, new Object[] {exceptionArgument, null, longArgument, booleanArgument, stringArgument}}));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the constructor " + StringUtility.constructorString(e, String.class, Exception.class, Integer.class, Long.class) + " but an exception occurred: [ConstructorNotFoundException: " + StringUtility.constructorString(e, String.class, Exception.class, Integer.class, Long.class) + ']', () ->
                    constructorInvoker.apply(new Object[] {e, new Object[] {stringArgument, exceptionArgument, intArgument, longArgument}}));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the constructor " + StringUtility.constructorString(e, Integer.class) + " but an exception occurred: [ConstructorNotFoundException: " + StringUtility.constructorString(e, Integer.class) + ']', () ->
                    constructorInvoker.apply(new Object[] {e, new Object[] {intArgument}}));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the constructor " + StringUtility.constructorString(e, (Class<?>) null) + " but an exception occurred: [ConstructorNotFoundException: " + StringUtility.constructorString(e, (Class<?>) null) + ']', () ->
                    constructorInvoker.apply(new Object[] {e, new Object[] {null}}));
        });
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeConstructor(null, intArgument));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeConstructor(null));
    }
    
    /**
     * JUnit test of invokeInterfaceDefaultMethod.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#invokeInterfaceDefaultMethod(Class, String, Object...)
     * @see TestUtils#invokeInterfaceDefaultMethod(Class, Class, String, Object...)
     */
    @Test
    public void testInvokeInterfaceDefaultMethod() throws Exception {
        //standard
        Assert.assertEquals("TestInterface", TestUtils.invokeInterfaceDefaultMethod(TestInterface.class, "getName"));
        Assert.assertEquals(888L, TestUtils.invokeInterfaceDefaultMethod(TestInterface.class, "returnLong", 888));
        
        //return type
        TestUtils.assertNoException(() -> {
            String getName = TestUtils.invokeInterfaceDefaultMethod(TestInterface.class, String.class, "getName");
            long returnLong = TestUtils.invokeInterfaceDefaultMethod(TestInterface.class, long.class, "returnLong", 113);
            TestUtils.assertException(ClassCastException.class, "class java.lang.String cannot be cast to class java.lang.Boolean (java.lang.String and java.lang.Boolean are in module java.base of loader 'bootstrap')", () -> {
                boolean incorrect = TestUtils.invokeInterfaceDefaultMethod(TestInterface.class, boolean.class, "getName");
            });
        });
        
        //invalid
        Stream.of(classes, new Class<?>[] {TestInterface.class}).flatMap(Arrays::stream).forEach(e -> {
            final boolean isInterface = e.isInterface();
            if (!isInterface) {
                TestUtils.assertException(AssertionError.class, "Attempted to invoke the interface method " + StringUtility.methodString(e, "getName") + " but an exception occurred: [InvalidClassException: " + StringUtility.classString(e) + " is not an interface]", () ->
                        TestUtils.invokeInterfaceDefaultMethod(e, "getName"));
                TestUtils.assertException(AssertionError.class, "Attempted to invoke the interface method " + StringUtility.methodString(e, "returnLong", Integer.class) + " but an exception occurred: [InvalidClassException: " + StringUtility.classString(e) + " is not an interface]", () ->
                        TestUtils.invokeInterfaceDefaultMethod(e, "returnLong", 50));
            }
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the interface method " + StringUtility.methodString(e, "getName", String.class) + " but an exception occurred: " + (isInterface ? ("[MethodNotFoundException: " + StringUtility.methodString(e, "getName", String.class) + ']') : ("[InvalidClassException: " + StringUtility.classString(e) + " is not an interface]")), () ->
                    TestUtils.invokeInterfaceDefaultMethod(e, "getName", "test"));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the interface method " + StringUtility.methodString(e, "returnLong") + " but an exception occurred: " + (isInterface ? ("[MethodNotFoundException: " + StringUtility.methodString(e, "returnLong") + ']') : ("[InvalidClassException: " + StringUtility.classString(e) + " is not an interface]")), () ->
                    TestUtils.invokeInterfaceDefaultMethod(e, "returnLong"));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the interface method " + StringUtility.methodString(e, "thisMethod") + " but an exception occurred: " + (isInterface ? ("[MethodNotFoundException: " + StringUtility.methodString(e, "thisMethod") + ']') : ("[InvalidClassException: " + StringUtility.classString(e) + " is not an interface]")), () ->
                    TestUtils.invokeInterfaceDefaultMethod(e, "thisMethod"));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the interface method " + StringUtility.methodString(e, "thisMethod", String.class, Integer.class, Integer.class) + " but an exception occurred: " + (isInterface ? ("[MethodNotFoundException: " + StringUtility.methodString(e, "thisMethod", String.class, Integer.class, Integer.class) + ']') : ("[InvalidClassException: " + StringUtility.classString(e) + " is not an interface]")), () ->
                    TestUtils.invokeInterfaceDefaultMethod(e, "thisMethod", "test", 5, 9));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the interface method " + StringUtility.methodString(e, "") + " but an exception occurred: " + (isInterface ? ("[MethodNotFoundException: " + StringUtility.methodString(e, "") + ']') : ("[InvalidClassException: " + StringUtility.classString(e) + " is not an interface]")), () ->
                    TestUtils.invokeInterfaceDefaultMethod(e, ""));
            TestUtils.assertException(AssertionError.class, "Attempted to invoke the interface method " + StringUtility.methodString(e, null) + " but an exception occurred: " + (isInterface ? ("[MethodNotFoundException: " + StringUtility.methodString(e, null) + ']') : ("[InvalidClassException: " + StringUtility.classString(e) + " is not an interface]")), () ->
                    TestUtils.invokeInterfaceDefaultMethod(e, null));
        });
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeInterfaceDefaultMethod(null, String.class, "getName"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeInterfaceDefaultMethod(null, (Class<?>) null, "getName"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeInterfaceDefaultMethod(null, "getName"));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeInterfaceDefaultMethod(null, null));
    }
    
    /**
     * JUnit test of AssertWrapper.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils.AssertWrapper
     */
    @Test
    public void testAssertWrapper() throws Exception {
        //fail
        TestUtils.assertMethodExists(AssertWrapper, "fail", String.class);
        TestUtils.assertMethodExists(AssertWrapper, "fail", String.class, Throwable.class);
        
        //assertEquals
        TestUtils.assertMethodExists(AssertWrapper, "assertEquals", String.class, Object.class, Object.class);
    }
    
    
    //Inner Classes
    
    /**
     * An interface for testing the getting and setting of fields and interaction with methods.
     */
    private interface TestInterface {
        
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
    private static class TestClass implements TestInterface {
        
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
        
    }
    
    /**
     * A subclass for testing the getting and setting of fields and interaction with methods.
     */
    public static class TestSubClass extends TestClass {
        
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
