/*
 * File:    TestUtilsTest.java
 * Package: commons.test
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import commons.lambda.function.Action;
import commons.lambda.function.checked.CheckedConsumer;
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
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
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
    static final Class<?>[] classes = TestAccessTest.classes;
    
    /**
     * A list of instances to use for testing.
     */
    static final Object[] instances = TestAccessTest.instances;
    
    /**
     * The AssertWrapper class.
     */
    private static final Class<?> AssertWrapper = TestAccess.getClass(TestUtils.class, "AssertWrapper");
    
    
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
     * JUnit test of checkException.
     *
     * @throws Throwable When there is an error.
     * @see TestUtils#checkException(Action, Consumer, Consumer)
     */
    @Test
    public void testCheckException() throws Throwable {
        final Action mockAction = Mockito.mock(Action.class);
        final Consumer<Throwable> mockOnException = Mockito.mock(Consumer.class);
        final Consumer<Void> mockOnNoException = Mockito.mock(Consumer.class);
        final Throwable mockException = Mockito.mock(IOException.class);
        
        //no exception
        TestUtils.assertNoException(() -> {
            TestAccess.invokeMethod(TestUtils.class, "checkException", mockAction, mockOnException, mockOnNoException);
            Mockito.verify(mockAction, VerificationModeFactory.times(1))
                    .perform();
            Mockito.verify(mockOnNoException, VerificationModeFactory.times(1))
                    .accept(ArgumentMatchers.eq(null));
        });
        List.of(mockAction, mockOnException, mockOnNoException).forEach(Mockito::verifyNoMoreInteractions);
        
        //exception
        TestUtils.assertNoException(() -> {
            Mockito.doThrow(mockException).when(mockAction).perform();
            TestAccess.invokeMethod(TestUtils.class, "checkException", mockAction, mockOnException, mockOnNoException);
            Mockito.verify(mockAction, VerificationModeFactory.times(2))
                    .perform();
            Mockito.verify(mockOnException, VerificationModeFactory.times(1))
                    .accept(ArgumentMatchers.eq(mockException));
            Mockito.doNothing().when(mockAction).perform();
        });
        List.of(mockAction, mockOnException, mockOnNoException).forEach(Mockito::verifyNoMoreInteractions);
        
        //invalid
        TestUtils.assertNoException(() -> {
            TestAccess.invokeMethod(TestUtils.class, "checkException", null, mockOnException, mockOnNoException);
            Mockito.verify(mockOnException, VerificationModeFactory.times(1))
                    .accept(ArgumentMatchers.any(NullPointerException.class));
        });
        TestUtils.assertNoException(() -> {
            Mockito.doThrow(mockException).when(mockAction).perform();
            TestAccess.invokeMethod(TestUtils.class, "checkException", mockAction, null, mockOnNoException);
            Mockito.verify(mockAction, VerificationModeFactory.times(3))
                    .perform();
            Mockito.doNothing().when(mockAction).perform();
        });
        TestUtils.assertNoException(() -> {
            TestAccess.invokeMethod(TestUtils.class, "checkException", mockAction, mockOnException, null);
            Mockito.verify(mockAction, VerificationModeFactory.times(4))
                    .perform();
        });
        TestUtils.assertNoException(() ->
                TestAccess.invokeMethod(TestUtils.class, "checkException", null, null, null));
        List.of(mockAction, mockOnException, mockOnNoException).forEach(Mockito::verifyNoMoreInteractions);
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
     * JUnit test of checkExists.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#checkExists(boolean, String, String, Action)
     */
    @Test
    public void testCheckExists() throws Exception {
        final Action mockAction = Mockito.mock(Action.class);
        
        PowerMockito.mockStatic(AssertWrapper);
        
        //exists, expected to exist
        TestUtils.assertNoException(() -> {
            TestAccess.invokeMethod(TestUtils.class, "checkExists", true, "interface", "TestInterface", mockAction);
            Mockito.verify(mockAction, VerificationModeFactory.times(1))
                    .perform();
        });
        Mockito.verifyNoMoreInteractions(mockAction);
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //exists, expected to not exist
        TestUtils.assertNoException(() -> {
            TestAccess.invokeMethod(TestUtils.class, "checkExists", false, "class", "TestClass", mockAction);
            Mockito.verify(mockAction, VerificationModeFactory.times(2))
                    .perform();
            PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                    .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected {} {} to not exist but it does", "class", "TestClass")));
        });
        Mockito.verifyNoMoreInteractions(mockAction);
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist, expected to exist
        TestUtils.assertNoException(() -> {
            Mockito.doThrow(new RuntimeException()).when(mockAction).perform();
            TestAccess.invokeMethod(TestUtils.class, "checkExists", true, "method", "TestClass::voidMethod(int)", mockAction);
            Mockito.verify(mockAction, VerificationModeFactory.times(3))
                    .perform();
            PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                    .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected {} {} to exist but it does not", "method", "TestClass::voidMethod(int)")));
            Mockito.doNothing().when(mockAction).perform();
        });
        Mockito.verifyNoMoreInteractions(mockAction);
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist, expected to not exist
        TestUtils.assertNoException(() -> {
            Mockito.doThrow(new RuntimeException()).when(mockAction).perform();
            TestAccess.invokeMethod(TestUtils.class, "checkExists", false, "field", "TestClass::field0", mockAction);
            Mockito.verify(mockAction, VerificationModeFactory.times(4))
                    .perform();
            Mockito.doNothing().when(mockAction).perform();
        });
        Mockito.verifyNoMoreInteractions(mockAction);
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        TestUtils.assertNoException(() -> {
            TestAccess.invokeMethod(TestUtils.class, "checkExists", true, null, null, null);
            PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                    .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected {} {} to exist but it does not", "null", "null")));
        });
        Mockito.verifyNoMoreInteractions(mockAction);
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
        TestUtils.assertClassExists(TestAccessTest.class, "TestClass");
        TestUtils.assertClassExists("commons.test.TestAccessTest$TestSubClass");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertClassExists(TestAccessTest.class, "MissingClass");
        TestUtils.assertClassExists("commons.test.TestAccessTest$TestClass$MissingClass");
        List.of(EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "MissingClass"),
                EntityStringUtility.innerClassString(TestAccessTest.TestClass.class, "MissingClass")
        ).forEach((CheckedConsumer<String>) classString ->
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected class {} to exist but it does not", classString))));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not a class
        TestUtils.assertClassExists(TestAccessTest.class, "TestInterface");
        TestUtils.assertClassExists("commons.test.TestAccessTest$TestClass$Enum0");
        List.of(EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "TestInterface"),
                EntityStringUtility.innerClassString(TestAccessTest.TestClass.class, "Enum0")
        ).forEach((CheckedConsumer<String>) classString ->
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected class {} to exist but it does not", classString))));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of("", null).forEach((CheckedConsumer<String>) className -> {
            Stream.of(TestAccessTest.class, null).forEach((CheckedConsumer<Class<?>>) parentClass -> {
                TestUtils.assertClassExists(parentClass, className);
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected class {} to exist but it does not", EntityStringUtility.simpleInnerClassString(parentClass, className))));
            });
            TestUtils.assertClassExists(className);
            PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                    .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected class {} to exist but it does not", className)));
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
        TestUtils.assertClassDoesNotExist(TestAccessTest.class, "TestClass");
        TestUtils.assertClassDoesNotExist("commons.test.TestAccessTest$TestSubClass");
        List.of(EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "TestClass"),
                EntityStringUtility.innerClassString(TestAccessTest.class, "TestSubClass")
        ).forEach((CheckedConsumer<String>) classString ->
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected class {} to not exist but it does", classString))));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertClassDoesNotExist(TestAccessTest.class, "MissingClass");
        TestUtils.assertClassDoesNotExist("commons.test.TestAccessTest$TestClass$MissingClass");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not a class
        TestUtils.assertClassDoesNotExist(TestAccessTest.class, "TestInterface");
        TestUtils.assertClassDoesNotExist("commons.test.TestAccessTest$TestClass$Enum0");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of("", null).forEach(className -> {
            Stream.of(TestAccessTest.class, null).forEach(parentClass ->
                    TestUtils.assertClassDoesNotExist(parentClass, className));
            TestUtils.assertClassDoesNotExist(className);
        });
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
        TestUtils.assertInterfaceExists(TestAccessTest.class, "TestInterface");
        TestUtils.assertInterfaceExists("commons.test.TestAccessTest$TestInterface");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertInterfaceExists(TestAccessTest.class, "MissingInterface");
        TestUtils.assertInterfaceExists("commons.test.TestAccessTest$TestClass$MissingInterface");
        List.of(EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "MissingInterface"),
                EntityStringUtility.innerClassString(TestAccessTest.TestClass.class, "MissingInterface")
        ).forEach((CheckedConsumer<String>) interfaceString ->
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected interface {} to exist but it does not", interfaceString))));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not an interface
        TestUtils.assertInterfaceExists(TestAccessTest.class, "TestClass");
        TestUtils.assertInterfaceExists("commons.test.TestAccessTest$TestClass$Enum0");
        List.of(EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "TestClass"),
                EntityStringUtility.innerClassString(TestAccessTest.TestClass.class, "Enum0")
        ).forEach((CheckedConsumer<String>) interfaceString ->
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected interface {} to exist but it does not", interfaceString))));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of("", null).forEach((CheckedConsumer<String>) interfaceName -> {
            Stream.of(TestAccessTest.class, null).forEach((CheckedConsumer<Class<?>>) parentClass -> {
                TestUtils.assertInterfaceExists(parentClass, interfaceName);
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected interface {} to exist but it does not", EntityStringUtility.simpleInnerClassString(parentClass, interfaceName))));
            });
            TestUtils.assertInterfaceExists(interfaceName);
            PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                    .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected interface {} to exist but it does not", interfaceName)));
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
        TestUtils.assertInterfaceDoesNotExist(TestAccessTest.class, "TestInterface");
        TestUtils.assertInterfaceDoesNotExist("commons.test.TestAccessTest$TestInterface");
        List.of(EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "TestInterface"),
                EntityStringUtility.innerClassString(TestAccessTest.class, "TestInterface")
        ).forEach((CheckedConsumer<String>) interfaceString ->
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected interface {} to not exist but it does", interfaceString))));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertInterfaceDoesNotExist(TestAccessTest.class, "MissingInterface");
        TestUtils.assertInterfaceDoesNotExist("commons.test.TestAccessTest$MissingInterface");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not an interface
        TestUtils.assertInterfaceDoesNotExist(TestAccessTest.class, "TestClass");
        TestUtils.assertInterfaceDoesNotExist("commons.test.TestAccessTest$TestClass$Enum0");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of("", null).forEach(interfaceName -> {
            Stream.of(TestAccessTest.class, null).forEach(parentClass ->
                    TestUtils.assertInterfaceDoesNotExist(parentClass, interfaceName));
            TestUtils.assertInterfaceDoesNotExist(interfaceName);
        });
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
        TestUtils.assertEnumExists(TestAccessTest.TestClass.class, "Enum0");
        TestUtils.assertEnumExists("commons.test.TestAccessTest$TestClass$Enum0");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertEnumExists(TestAccessTest.class, "MissingEnum");
        TestUtils.assertEnumExists("commons.test.TestAccessTest$TestClass$MissingEnum");
        List.of(EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "MissingEnum"),
                EntityStringUtility.innerClassString(TestAccessTest.TestClass.class, "MissingEnum")
        ).forEach((CheckedConsumer<String>) enumString ->
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected enum {} to exist but it does not", enumString))));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not an enum
        TestUtils.assertEnumExists(TestAccessTest.class, "TestClass");
        TestUtils.assertEnumExists("commons.test.TestAccessTest$TestInterface");
        List.of(EntityStringUtility.simpleInnerClassString(TestAccessTest.class, "TestClass"),
                EntityStringUtility.innerClassString(TestAccessTest.class, "TestInterface")
        ).forEach((CheckedConsumer<String>) enumString ->
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected enum {} to exist but it does not", enumString))));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of("", null).forEach((CheckedConsumer<String>) enumName -> {
            Stream.of(TestAccessTest.class, null).forEach((CheckedConsumer<Class<?>>) parentClass -> {
                TestUtils.assertEnumExists(parentClass, enumName);
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected enum {} to exist but it does not", EntityStringUtility.simpleInnerClassString(parentClass, enumName))));
            });
            TestUtils.assertEnumExists(enumName);
            PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                    .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected enum {} to exist but it does not", enumName)));
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
        TestUtils.assertEnumDoesNotExist(TestAccessTest.TestClass.class, "Enum0");
        TestUtils.assertEnumDoesNotExist("commons.test.TestAccessTest$TestClass$Enum1");
        List.of(EntityStringUtility.simpleInnerClassString(TestAccessTest.TestClass.class, "Enum0"),
                EntityStringUtility.innerClassString(TestAccessTest.TestClass.class, "Enum1")
        ).forEach((CheckedConsumer<String>) enumString ->
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(1))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected enum {} to not exist but it does", enumString))));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        TestUtils.assertEnumDoesNotExist(TestAccessTest.class, "MissingEnum");
        TestUtils.assertEnumDoesNotExist("commons.test.TestAccessTest$MissingEnum");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //not an enum
        TestUtils.assertEnumDoesNotExist(TestAccessTest.class, "TestClass");
        TestUtils.assertEnumDoesNotExist("commons.test.TestAccessTest$TestInterface");
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of("", null).forEach(className -> {
            Stream.of(TestAccessTest.class, null).forEach(parentClass ->
                    TestUtils.assertEnumDoesNotExist(parentClass, className));
            TestUtils.assertEnumDoesNotExist(className);
        });
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
        final Map<String, AtomicInteger> calls = Stream.of(classes, new Object[] {null}).flatMap(Arrays::stream)
                .collect(Collectors.toMap(EntityStringUtility::simpleClassString, e -> new AtomicInteger(0)));
        
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
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e ->
                List.of(new ImmutablePair<>("voidMethod", new Class<?>[] {int.class, Long.class, StringBuilder.class}),
                        new ImmutablePair<>("voidMethod", new Class<?>[] {Integer.class, long.class, StringBuilder.class}),
                        new ImmutablePair<>("method7", new Class<?>[] {}),
                        new ImmutablePair<>("method8", new Class<?>[] {String.class}),
                        new ImmutablePair<>("method9", new Class<?>[] {int.class}),
                        new ImmutablePair<>("method10", new Class<?>[] {}),
                        new ImmutablePair<>("method11", new Class<?>[] {boolean.class, String.class, float.class, char[].class})
                ).forEach(methodNameArgumentTypesPair ->
                        methodExistsAsserter.accept(new Object[] {e, methodNameArgumentTypesPair.getLeft(), methodNameArgumentTypesPair.getRight()})));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            calls.get(EntityStringUtility.simpleClassString(e)).incrementAndGet();
            List.of(new ImmutablePair<>("voidMethod", new Class<?>[] {int.class, long.class, String.class}),
                    new ImmutablePair<>("voidMethod", new Class<?>[] {Integer.class, Long.class, String.class}),
                    new ImmutablePair<>("method7", new Class<?>[] {int.class}),
                    new ImmutablePair<>("method8", new Class<?>[] {int.class}),
                    new ImmutablePair<>("missingMethod", new Class<?>[] {})
            ).forEach((CheckedConsumer<ImmutablePair<String, Class<?>[]>>) methodNameArgumentTypesPair -> {
                methodExistsAsserter.accept(new Object[] {e, methodNameArgumentTypesPair.getLeft(), methodNameArgumentTypesPair.getRight()});
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(e)).get()))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected method {} to exist but it does not", EntityStringUtility.simpleMethodString(e, methodNameArgumentTypesPair.getLeft(), methodNameArgumentTypesPair.getRight()))));
            });
        });
        calls.values().forEach(e -> e.set(0));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(entity -> {
            calls.get(EntityStringUtility.simpleClassString(entity)).incrementAndGet();
            Stream.of("", null).forEach(methodName ->
                    Stream.of(String.class, null).map(argumentType -> new Class<?>[] {argumentType}).forEach((CheckedConsumer<Class<?>[]>) argumentTypes -> {
                        methodExistsAsserter.accept(new Object[] {entity, methodName, argumentTypes});
                        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(entity)).get()))
                                .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected method {} to exist but it does not", EntityStringUtility.simpleMethodString(entity, methodName, argumentTypes))));
                    }));
        });
        calls.values().forEach(e -> e.set(0));
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
        final Map<String, AtomicInteger> calls = Stream.of(classes, new Object[] {null}).flatMap(Arrays::stream)
                .collect(Collectors.toMap(EntityStringUtility::simpleClassString, e -> new AtomicInteger(0)));
        
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
            calls.get(EntityStringUtility.simpleClassString(e)).incrementAndGet();
            List.of(new ImmutablePair<>("voidMethod", new Class<?>[] {int.class, Long.class, StringBuilder.class}),
                    new ImmutablePair<>("voidMethod", new Class<?>[] {Integer.class, long.class, StringBuilder.class}),
                    new ImmutablePair<>("method7", new Class<?>[] {}),
                    new ImmutablePair<>("method8", new Class<?>[] {String.class}),
                    new ImmutablePair<>("method9", new Class<?>[] {int.class}),
                    new ImmutablePair<>("method10", new Class<?>[] {}),
                    new ImmutablePair<>("method11", new Class<?>[] {boolean.class, String.class, float.class, char[].class})
            ).forEach((CheckedConsumer<ImmutablePair<String, Class<?>[]>>) methodNameArgumentTypesPair -> {
                methodDoesNotExistAsserter.accept(new Object[] {e, methodNameArgumentTypesPair.getLeft(), methodNameArgumentTypesPair.getRight()});
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(e)).get()))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected method {} to not exist but it does", EntityStringUtility.simpleMethodString(e, methodNameArgumentTypesPair.getLeft(), methodNameArgumentTypesPair.getRight()))));
            });
        });
        calls.values().forEach(e -> e.set(0));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e ->
                List.of(new ImmutablePair<>("voidMethod", new Class<?>[] {int.class, long.class, String.class}),
                        new ImmutablePair<>("voidMethod", new Class<?>[] {Integer.class, Long.class, String.class}),
                        new ImmutablePair<>("method7", new Class<?>[] {int.class}),
                        new ImmutablePair<>("method8", new Class<?>[] {int.class}),
                        new ImmutablePair<>("missingMethod", new Class<?>[] {})
                ).forEach(methodNameArgumentTypesPair ->
                        methodDoesNotExistAsserter.accept(new Object[] {e, methodNameArgumentTypesPair.getLeft(), methodNameArgumentTypesPair.getRight()})));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(entity -> {
            calls.get(EntityStringUtility.simpleClassString(entity)).incrementAndGet();
            Stream.of("", null).forEach(methodName ->
                    Stream.of(String.class, null).map(argumentType -> new Class<?>[] {argumentType}).forEach((CheckedConsumer<Class<?>[]>) argumentTypes -> {
                        methodDoesNotExistAsserter.accept(new Object[] {entity, methodName, argumentTypes});
                        PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(entity)).get()))
                                .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected method {} to not exist but it does", EntityStringUtility.simpleMethodString(entity, methodName, argumentTypes))));
                    }));
        });
        calls.values().forEach(e -> e.set(0));
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
        final Map<String, AtomicInteger> calls = Stream.of(classes, new Object[] {null}).flatMap(Arrays::stream)
                .collect(Collectors.toMap(EntityStringUtility::simpleClassString, e -> new AtomicInteger(0)));
        
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
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e ->
                List.of(new Class<?>[] {},
                        new Class<?>[] {String.class, Exception.class, int.class, Long.class, boolean.class},
                        new Class<?>[] {String.class, Exception.class, Integer.class, Long.class, Boolean.class},
                        new Class<?>[] {String.class, int.class, Long.class, boolean.class, Exception.class},
                        new Class<?>[] {Exception.class, int.class, Long.class, boolean.class, String.class}
                ).forEach(argumentTypes ->
                        constructorExistsAsserter.accept(new Object[] {e, argumentTypes})));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e -> {
            calls.get(EntityStringUtility.simpleClassString(e)).incrementAndGet();
            List.of(new Class<?>[] {StringBuilder.class},
                    new Class<?>[] {StringBuilder.class, Exception.class, int.class, Long.class, boolean.class},
                    new Class<?>[] {StringBuilder.class, Exception.class, Integer.class, Long.class, Boolean.class}
            ).forEach((CheckedConsumer<Class<?>[]>) argumentTypes -> {
                constructorExistsAsserter.accept(new Object[] {e, argumentTypes});
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(e)).get()))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected constructor {} to exist but it does not", EntityStringUtility.simpleConstructorString(e, argumentTypes))));
            });
        });
        calls.values().forEach(e -> e.set(0));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(entity -> {
            calls.get(EntityStringUtility.simpleClassString(entity)).incrementAndGet();
            Stream.of(String.class, null).map(argumentType -> new Class<?>[] {argumentType}).forEach((CheckedConsumer<Class<?>[]>) argumentTypes -> {
                constructorExistsAsserter.accept(new Object[] {entity, argumentTypes});
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(entity)).get()))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected constructor {} to exist but it does not", EntityStringUtility.simpleConstructorString(entity, argumentTypes))));
            });
        });
        calls.values().forEach(e -> e.set(0));
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
        final Map<String, AtomicInteger> calls = Stream.of(classes, new Object[] {null}).flatMap(Arrays::stream)
                .collect(Collectors.toMap(EntityStringUtility::simpleClassString, e -> new AtomicInteger(0)));
        
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
            calls.get(EntityStringUtility.simpleClassString(e)).incrementAndGet();
            List.of(new Class<?>[] {},
                    new Class<?>[] {String.class, Exception.class, int.class, Long.class, boolean.class},
                    new Class<?>[] {String.class, Exception.class, Integer.class, Long.class, Boolean.class},
                    new Class<?>[] {String.class, int.class, Long.class, boolean.class, Exception.class},
                    new Class<?>[] {Exception.class, int.class, Long.class, boolean.class, String.class}
            ).forEach((CheckedConsumer<Class<?>[]>) argumentTypes -> {
                constructorDoesNotExistAsserter.accept(new Object[] {e, argumentTypes});
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(e)).get()))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected constructor {} to not exist but it does", EntityStringUtility.simpleConstructorString(e, argumentTypes))));
            });
        });
        calls.values().forEach(e -> e.set(0));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(e ->
                List.of(new Class<?>[] {StringBuilder.class},
                        new Class<?>[] {StringBuilder.class, Exception.class, int.class, Long.class, boolean.class},
                        new Class<?>[] {StringBuilder.class, Exception.class, Integer.class, Long.class, Boolean.class}
                ).forEach(argumentTypes ->
                        constructorDoesNotExistAsserter.accept(new Object[] {e, argumentTypes})));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(entity ->
                Stream.of(String.class, null).map(argumentType -> new Class<?>[] {argumentType}).forEach(argumentTypes ->
                        constructorDoesNotExistAsserter.accept(new Object[] {entity, argumentTypes})));
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
        final Map<String, AtomicInteger> calls = Stream.of(classes, new Object[] {null}).flatMap(Arrays::stream)
                .collect(Collectors.toMap(EntityStringUtility::simpleClassString, e -> new AtomicInteger(0)));
        
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
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity ->
                List.of("field2",
                        "field5",
                        "field7",
                        "field10"
                ).forEach(fieldName ->
                        fieldExistsAsserter.accept(new Object[] {entity, fieldName})));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
            calls.get(EntityStringUtility.simpleClassString(entity)).incrementAndGet();
            List.of("Field7",
                    "field15",
                    "missingField"
            ).forEach((CheckedConsumer<String>) fieldName -> {
                fieldExistsAsserter.accept(new Object[] {entity, fieldName});
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(entity)).get()))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected field {} to exist but it does not", EntityStringUtility.simpleFieldString(entity, fieldName))));
            });
        });
        calls.values().forEach(e -> e.set(0));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(entity -> {
            calls.get(EntityStringUtility.simpleClassString(entity)).incrementAndGet();
            Stream.of("", null).forEach((CheckedConsumer<String>) fieldName -> {
                fieldExistsAsserter.accept(new Object[] {entity, fieldName});
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(entity)).get()))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected field {} to exist but it does not", EntityStringUtility.simpleFieldString(entity, fieldName))));
            });
        });
        calls.values().forEach(e -> e.set(0));
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
        final Map<String, AtomicInteger> calls = Stream.of(classes, new Object[] {null}).flatMap(Arrays::stream)
                .collect(Collectors.toMap(EntityStringUtility::simpleClassString, e -> new AtomicInteger(0)));
        
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
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity -> {
            calls.get(EntityStringUtility.simpleClassString(entity)).incrementAndGet();
            List.of("field2",
                    "field5",
                    "field7",
                    "field10"
            ).forEach((CheckedConsumer<String>) fieldName -> {
                fieldDoesNotExistAsserter.accept(new Object[] {entity, fieldName});
                PowerMockito.verifyPrivate(AssertWrapper, VerificationModeFactory.times(calls.get(EntityStringUtility.simpleClassString(entity)).get()))
                        .invoke("fail", ArgumentMatchers.eq(StringUtility.format("Expected field {} to not exist but it does", EntityStringUtility.simpleFieldString(entity, fieldName))));
            });
        });
        calls.values().forEach(e -> e.set(0));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //does not exist
        Stream.of(classes, instances).flatMap(Arrays::stream).forEach(entity ->
                List.of("Field7",
                        "field15",
                        "missingField"
                ).forEach(fieldName ->
                        fieldDoesNotExistAsserter.accept(new Object[] {entity, fieldName})));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
        
        //invalid
        Stream.of(classes, instances, new Class<?>[] {null}, new Object[] {null}).flatMap(Arrays::stream).forEach(entity ->
                Stream.of("", null).forEach((CheckedConsumer<String>) fieldName ->
                        fieldDoesNotExistAsserter.accept(new Object[] {entity, fieldName})));
        PowerMockito.verifyNoMoreInteractions(AssertWrapper);
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
    
}
