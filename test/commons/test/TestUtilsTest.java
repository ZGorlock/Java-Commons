/*
 * File:    TestUtilsTest.java
 * Package: commons.test
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import commons.string.StringUtility;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.powermock.reflect.exceptions.MethodInvocationException;
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
        Assert.assertEquals(1E-9, TestUtils.DELTA, 1E-9);
        Assert.assertEquals(1E-3f, TestUtils.DELTA_FLOAT, 1E-3f);
        Assert.assertEquals(1E-12, TestUtils.DELTA_DOUBLE, 1E-12);
        Assert.assertEquals(BigDecimal.valueOf(1E-36), TestUtils.DELTA_BIG);
    }
    
    /**
     * JUnit test of assertException.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertException(Class, String, Runnable)
     * @see TestUtils#assertException(Class, Runnable)
     * @see TestUtils#assertException(Runnable)
     */
    @Test
    public void testAssertException() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        TestUtils.assertException(() ->
                new BigDecimal("15a4"));
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(NumberFormatException.class, () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(1));
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NumberFormatException but instead it produced a NumberFormatException", //success
                NumberFormatException.class, NumberFormatException.class);
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(2));
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NumberFormatException but instead it produced a NumberFormatException", //success
                NumberFormatException.class, NumberFormatException.class);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(1));
        TestUtils.AssertWrapper.assertEquals(
                "Expected the error message of the NumberFormatException to be: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\" but the error message was: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\"", //success
                "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.");
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(NumberFormatException.class, "Could not parse BigDecimal", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(3));
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NumberFormatException but instead it produced a NumberFormatException", //success
                NumberFormatException.class, NumberFormatException.class);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(1));
        TestUtils.AssertWrapper.assertEquals(
                "Expected the error message of the NumberFormatException to be: \"Could not parse BigDecimal\" but the error message was: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\"",
                "Could not parse BigDecimal", "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.");
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(NullPointerException.class, () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(1));
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NullPointerException but instead it produced a NumberFormatException",
                NullPointerException.class, NumberFormatException.class);
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(NullPointerException.class, "Could not parse BigDecimal", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(2));
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NullPointerException but instead it produced a NumberFormatException",
                NullPointerException.class, NumberFormatException.class);
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(null, "Could not parse BigDecimal", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(1));
        TestUtils.AssertWrapper.assertEquals(
                "Expected the error message of the exception to be: \"Could not parse BigDecimal\" but the error message was: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\"",
                "Could not parse BigDecimal", "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.");
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(NullPointerException.class, () ->
                new BigDecimal("1564"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(1));
        TestUtils.AssertWrapper.fail(
                "Expected code to produce a NullPointerException but no exception was produced");
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(null, () ->
                new BigDecimal("1564"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(1));
        TestUtils.AssertWrapper.fail(
                "Expected code to produce an exception but no exception was produced");
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(() ->
                new BigDecimal("1564"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(2));
        TestUtils.AssertWrapper.fail(
                "Expected code to produce an exception but no exception was produced");
        PowerMockito.verifyNoMoreInteractions();
    }
    
    /**
     * JUnit test of assertNoException.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertNoException(Runnable)
     */
    @Test
    public void testAssertNoException() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        TestUtils.assertNoException(() ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected code to produce no Exception but instead it produced a NumberFormatException");
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertNoException(() ->
                new BigDecimal("1564"));
        PowerMockito.verifyNoMoreInteractions(TestUtils.AssertWrapper.class);
    }
    
    /**
     * JUnit test of assertInputStreamReadThrowsException.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertInputStreamReadThrowsException(Class, InputStream, byte[], int, int)
     */
    @Test
    public void testAssertInputStreamReadThrowsException() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        InputStream inputStream = new ByteArrayInputStream(StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8));
        TestUtils.assertInputStreamReadThrowsException(IndexOutOfBoundsException.class,
                inputStream, new byte[200], 0, 201);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.assertEquals(
                "Expected input stream read operation to produce an IndexOutOfBoundsException but instead it produced an IndexOutOfBoundsException", //success
                IndexOutOfBoundsException.class, IndexOutOfBoundsException.class);
        
        TestUtils.assertInputStreamReadThrowsException(IndexOutOfBoundsException.class,
                null, new byte[200], 0, 201);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.assertEquals(
                "Expected input stream read operation to produce an IndexOutOfBoundsException but instead it produced a NullPointerException",
                IndexOutOfBoundsException.class, NullPointerException.class);
        
        inputStream = new ByteArrayInputStream(StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8));
        TestUtils.assertInputStreamReadThrowsException(IndexOutOfBoundsException.class,
                inputStream, new byte[200], 0, 30);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected input stream read operation to produce an IndexOutOfBoundsException but no exception was produced");
    }
    
    /**
     * JUnit test of assertInputStreamReadDoesNotThrowException.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertInputStreamReadDoesNotThrowException(InputStream, byte[], int, int)
     */
    @Test
    public void testAssertInputStreamReadDoesNotThrowException() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        InputStream inputStream = new ByteArrayInputStream(StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8));
        TestUtils.assertInputStreamReadDoesNotThrowException(
                inputStream, new byte[200], 0, 201);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected input stream read operation to produce no Exception but instead it produced an IndexOutOfBoundsException");
        
        inputStream = new ByteArrayInputStream(StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8));
        TestUtils.assertInputStreamReadDoesNotThrowException(
                inputStream, new byte[200], 0, 30);
        PowerMockito.verifyNoMoreInteractions(TestUtils.AssertWrapper.class);
    }
    
    /**
     * JUnit test of assertOutputStreamWriteThrowsException.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertOutputStreamWriteThrowsException(Class, OutputStream, byte[], int, int)
     */
    @Test
    public void testAssertOutputStreamWriteThrowsException() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        OutputStream outputStream = new ByteArrayOutputStream(200);
        TestUtils.assertOutputStreamWriteThrowsException(IndexOutOfBoundsException.class,
                outputStream, StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8), 0, 201);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.assertEquals(
                "Expected output stream write operation to produce an IndexOutOfBoundsException but instead it produced an IndexOutOfBoundsException", //success
                IndexOutOfBoundsException.class, IndexOutOfBoundsException.class);
        
        TestUtils.assertOutputStreamWriteThrowsException(IndexOutOfBoundsException.class,
                null, StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8), 0, 201);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.assertEquals(
                "Expected output stream write operation to produce an IndexOutOfBoundsException but instead it produced a NullPointerException",
                IndexOutOfBoundsException.class, NullPointerException.class);
        
        outputStream = new ByteArrayOutputStream(200);
        TestUtils.assertOutputStreamWriteThrowsException(IndexOutOfBoundsException.class,
                outputStream, StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8), 0, 30);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected output stream write operation to produce an IndexOutOfBoundsException but no exception was produced");
    }
    
    /**
     * JUnit test of assertOutputStreamWriteDoesNotThrowException.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertOutputStreamWriteDoesNotThrowException(OutputStream, byte[], int, int)
     */
    @Test
    public void testAssertOutputStreamWriteDoesNotThrowException() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        OutputStream outputStream = new ByteArrayOutputStream(200);
        TestUtils.assertOutputStreamWriteDoesNotThrowException(
                outputStream, StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8), 0, 201);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected output stream write operation to produce no Exception but instead it produced an IndexOutOfBoundsException");
        
        outputStream = new ByteArrayOutputStream(200);
        TestUtils.assertOutputStreamWriteDoesNotThrowException(
                outputStream, StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8), 0, 30);
        PowerMockito.verifyNoMoreInteractions(TestUtils.AssertWrapper.class);
    }
    
    /**
     * JUnit test of assertMethodExists.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertMethodExists(Class, String, Class[])
     */
    @Test
    public void testAssertMethodExists() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        TestUtils.assertMethodExists(
                TestClass.class, "voidMethod", int.class, long.class, String.class);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected method TestClass::voidMethod(int, long, String) to exist but it does not");
        
        TestUtils.assertMethodExists(
                TestClass.class, "voidMethod", Integer.class, Long.class, StringBuilder.class);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected method TestClass::voidMethod(Integer, Long, StringBuilder) to exist but it does not");
        
        TestUtils.assertMethodExists(
                TestClass.class, "voidMethod", int.class, Long.class, StringBuilder.class);
        PowerMockito.verifyNoMoreInteractions(TestUtils.AssertWrapper.class);
    }
    
    /**
     * JUnit test of assertMethodDoesNotExist.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertMethodDoesNotExist(Class, String, Class[])
     */
    @Test
    public void testAssertMethodDoesNotExist() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        TestUtils.assertMethodDoesNotExist(
                TestClass.class, "voidMethod", int.class, Long.class, StringBuilder.class);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected method TestClass::voidMethod(int, Long, StringBuilder) to not exist but it does");
        
        TestUtils.assertMethodDoesNotExist(
                TestClass.class, "voidMethod", int.class, long.class, String.class);
        TestUtils.assertMethodDoesNotExist(
                TestClass.class, "voidMethod", Integer.class, Long.class, StringBuilder.class);
        PowerMockito.verifyNoMoreInteractions(TestUtils.AssertWrapper.class);
    }
    
    /**
     * JUnit test of getField.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getField(Class, Object, String)
     * @see TestUtils#getField(Class, String)
     * @see TestUtils#getField(Object, String)
     */
    @Test
    public void testGetField() throws Exception {
        TestClass testClass = new TestClass();
        TestSubClass subClass = new TestSubClass();
        TestClass mockClass = Mockito.mock(TestClass.class);
        
        //standard
        
        for (int i = 0; i <= 11; i++) {
            if (i < 6) {
                Assert.assertEquals(Whitebox.getInternalState(TestClass.class, "field" + i),
                        TestUtils.getField(TestClass.class, "field" + i));
            } else {
                Assert.assertEquals(Whitebox.getInternalState(testClass, "field" + i),
                        TestUtils.getField(testClass, "field" + i));
            }
        }
        
        //sub class
        
        for (int i = 0; i <= 11; i++) {
            if (i < 6) {
                Assert.assertEquals(Whitebox.getInternalState(TestClass.class, "field" + i),
                        TestUtils.getField(TestSubClass.class, "field" + i));
            } else {
                Assert.assertEquals(Whitebox.getInternalState(testClass, "field" + i),
                        TestUtils.getField(subClass, "field" + i));
            }
        }
        
        //mock
        
        for (int i = 6; i <= 11; i++) {
            Whitebox.setInternalState(mockClass, "field" + i, TestUtils.getField(testClass, "field" + i));
            Assert.assertEquals(Whitebox.getInternalState(testClass, "field" + i),
                    TestUtils.getField(mockClass, "field" + i));
        }
        
        //invalid
        
        Assert.assertNull(TestUtils.getField(TestClass.class, "missingField"));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertNull(TestUtils.getField(TestClass.class, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertNull(TestUtils.getField(null, "field0")));
        
        Assert.assertNull(TestUtils.getField(TestSubClass.class, "missingField"));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertNull(TestUtils.getField(TestSubClass.class, null)));
        
        Assert.assertNull(TestUtils.getField(testClass, "missingField"));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertNull(TestUtils.getField((Object) null, "field5")));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertNull(TestUtils.getField(testClass, null)));
        
        Assert.assertNull(TestUtils.getField(subClass, "missingField"));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertNull(TestUtils.getField(subClass, null)));
    }
    
    /**
     * JUnit test of setField.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#setField(Class, Object, String, Object)
     * @see TestUtils#setField(Class, String, Object)
     * @see TestUtils#setField(Object, String, Object)
     */
    @Test
    public void testSetField() throws Exception {
        TestClass testClass = new TestClass();
        TestSubClass subClass = new TestSubClass();
        TestClass mockClass = Mockito.mock(TestClass.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        
        Assert.assertEquals(18, (int) Whitebox.getInternalState(TestClass.class, "field0"));
        Assert.assertTrue(TestUtils.setField(TestClass.class, "field0", 7));
        Assert.assertEquals(7, (int) Whitebox.getInternalState(TestClass.class, "field0"));
        
        Assert.assertEquals(6.4488121, Whitebox.getInternalState(TestClass.class, "field1"), TestUtils.DELTA);
        Assert.assertTrue(TestUtils.setField(TestClass.class, "field1", 0.221548773));
        Assert.assertEquals(0.221548773, Whitebox.getInternalState(TestClass.class, "field1"), TestUtils.DELTA);
        
        Assert.assertEquals("test", Whitebox.getInternalState(TestClass.class, "field2"));
        Assert.assertTrue(TestUtils.setField(TestClass.class, "field2", "different"));
        Assert.assertEquals("different", Whitebox.getInternalState(TestClass.class, "field2"));
        
        Assert.assertEquals(true, Whitebox.getInternalState(TestClass.class, "field3"));
        Assert.assertTrue(TestUtils.setField(TestClass.class, "field3", false));
        Assert.assertEquals(false, Whitebox.getInternalState(TestClass.class, "field3"));
        
        Assert.assertEquals("tset", Whitebox.getInternalState(TestClass.class, "field4"));
        Assert.assertTrue(TestUtils.setField(TestClass.class, "field4", "tset2"));
        Assert.assertEquals("tset2", Whitebox.getInternalState(TestClass.class, "field4"));
        
        Assert.assertEquals(4, (byte) Whitebox.getInternalState(TestClass.class, "field5"));
        Assert.assertTrue(TestUtils.setField(TestClass.class, "field5", (byte) 3));
        Assert.assertEquals(3, (byte) Whitebox.getInternalState(TestClass.class, "field5"));
        
        Assert.assertEquals("another test", Whitebox.getInternalState(testClass, "field6"));
        Assert.assertTrue(TestUtils.setField(testClass, "field6", "an even other test"));
        Assert.assertEquals("an even other test", Whitebox.getInternalState(testClass, "field6"));
        
        Assert.assertEquals(874561564112154L, (long) Whitebox.getInternalState(testClass, "field7"));
        Assert.assertTrue(TestUtils.setField(testClass, "field7", 156423157842311L));
        Assert.assertEquals(156423157842311L, (long) Whitebox.getInternalState(testClass, "field7"));
        
        Assert.assertEquals(-44, (int) Whitebox.getInternalState(testClass, "field8"));
        Assert.assertTrue(TestUtils.setField(testClass, "field8", 1568));
        Assert.assertEquals(1568, (int) Whitebox.getInternalState(testClass, "field8"));
        
        Assert.assertEquals(7.66f, Whitebox.getInternalState(testClass, "field9"), TestUtils.DELTA_FLOAT);
        Assert.assertTrue(TestUtils.setField(testClass, "field9", 3.46f));
        Assert.assertEquals(3.46f, Whitebox.getInternalState(testClass, "field9"), TestUtils.DELTA_FLOAT);
        
        Assert.assertEquals(true, Whitebox.getInternalState(testClass, "field10"));
        Assert.assertTrue(TestUtils.setField(testClass, "field10", false));
        Assert.assertEquals(false, Whitebox.getInternalState(testClass, "field10"));
        
        Assert.assertEquals("last test", Whitebox.getInternalState(testClass, "field11"));
        Assert.assertTrue(TestUtils.setField(testClass, "field11", "the last test"));
        Assert.assertEquals("the last test", Whitebox.getInternalState(testClass, "field11"));
        
        //sub class
        
        Assert.assertEquals(7, (int) Whitebox.getInternalState(TestSubClass.class, "field0"));
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field0", 10));
        Assert.assertEquals(10, (int) Whitebox.getInternalState(TestSubClass.class, "field0"));
        
        Assert.assertEquals(0.221548773, Whitebox.getInternalState(TestSubClass.class, "field1"), TestUtils.DELTA);
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field1", 6.4488121));
        Assert.assertEquals(6.4488121, Whitebox.getInternalState(TestSubClass.class, "field1"), TestUtils.DELTA);
        
        Assert.assertEquals("different", Whitebox.getInternalState(TestSubClass.class, "field2"));
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field2", "another different"));
        Assert.assertEquals("another different", Whitebox.getInternalState(TestSubClass.class, "field2"));
        
        Assert.assertEquals(false, Whitebox.getInternalState(TestSubClass.class, "field3"));
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field3", true));
        Assert.assertEquals(true, Whitebox.getInternalState(TestSubClass.class, "field3"));
        
        Assert.assertEquals("tset2", Whitebox.getInternalState(TestSubClass.class, "field4"));
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field4", "tset"));
        Assert.assertEquals("tset", Whitebox.getInternalState(TestSubClass.class, "field4"));
        
        Assert.assertEquals(3, (byte) Whitebox.getInternalState(TestSubClass.class, "field5"));
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field5", (byte) 4));
        Assert.assertEquals(4, (byte) Whitebox.getInternalState(TestSubClass.class, "field5"));
        
        Assert.assertEquals("another test", Whitebox.getInternalState(subClass, "field6"));
        Assert.assertTrue(TestUtils.setField(subClass, "field6", "an even other test"));
        Assert.assertEquals("an even other test", Whitebox.getInternalState(subClass, "field6"));
        
        Assert.assertEquals(874561564112154L, (long) Whitebox.getInternalState(subClass, "field7"));
        Assert.assertTrue(TestUtils.setField(subClass, "field7", 156423157842311L));
        Assert.assertEquals(156423157842311L, (long) Whitebox.getInternalState(subClass, "field7"));
        
        Assert.assertEquals(-44, (int) Whitebox.getInternalState(subClass, "field8"));
        Assert.assertTrue(TestUtils.setField(subClass, "field8", 1568));
        Assert.assertEquals(1568, (int) Whitebox.getInternalState(subClass, "field8"));
        
        Assert.assertEquals(7.66f, Whitebox.getInternalState(subClass, "field9"), TestUtils.DELTA_FLOAT);
        Assert.assertTrue(TestUtils.setField(subClass, "field9", 3.46f));
        Assert.assertEquals(3.46f, Whitebox.getInternalState(subClass, "field9"), TestUtils.DELTA_FLOAT);
        
        Assert.assertEquals(true, Whitebox.getInternalState(subClass, "field10"));
        Assert.assertTrue(TestUtils.setField(subClass, "field10", false));
        Assert.assertEquals(false, Whitebox.getInternalState(subClass, "field10"));
        
        Assert.assertEquals("last test", Whitebox.getInternalState(subClass, "field11"));
        Assert.assertTrue(TestUtils.setField(subClass, "field11", "the last test"));
        Assert.assertEquals("the last test", Whitebox.getInternalState(subClass, "field11"));
        
        //mock
        
        Assert.assertEquals((String) null, Whitebox.getInternalState(mockClass, "field6"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field6", "an even other test"));
        Assert.assertEquals("an even other test", Whitebox.getInternalState(mockClass, "field6"));
        
        Assert.assertEquals(0L, (long) Whitebox.getInternalState(mockClass, "field7"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field7", 156423157842311L));
        Assert.assertEquals(156423157842311L, (long) Whitebox.getInternalState(mockClass, "field7"));
        
        Assert.assertEquals(0, (int) Whitebox.getInternalState(mockClass, "field8"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field8", 1568));
        Assert.assertEquals(1568, (int) Whitebox.getInternalState(mockClass, "field8"));
        
        Assert.assertEquals(0.0f, Whitebox.getInternalState(mockClass, "field9"), TestUtils.DELTA_FLOAT);
        Assert.assertTrue(TestUtils.setField(mockClass, "field9", 3.46f));
        Assert.assertEquals(3.46f, Whitebox.getInternalState(mockClass, "field9"), TestUtils.DELTA_FLOAT);
        
        Assert.assertEquals(false, Whitebox.getInternalState(mockClass, "field10"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field10", true));
        Assert.assertEquals(true, Whitebox.getInternalState(mockClass, "field10"));
        
        Assert.assertEquals((String) null, Whitebox.getInternalState(mockClass, "field11"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field11", "the last test"));
        Assert.assertEquals("the last test", Whitebox.getInternalState(mockClass, "field11"));
        
        //invalid
        
        Assert.assertEquals(10, (int) Whitebox.getInternalState(TestClass.class, "field0"));
        TestUtils.assertException(IllegalArgumentException.class, "Can not set static int field commons.test.TestUtilsTest$TestClass.field0 to java.lang.Boolean", () ->
                TestUtils.setField(TestClass.class, "field0", false));
        Assert.assertEquals(10, (int) Whitebox.getInternalState(TestClass.class, "field0"));
        
        Assert.assertEquals(true, Whitebox.getInternalState(TestClass.class, "field3"));
        TestUtils.assertException(IllegalArgumentException.class, "Can not set static boolean field commons.test.TestUtilsTest$TestClass.field3 to java.lang.Integer", () ->
                TestUtils.setField(TestClass.class, "field3", 103));
        Assert.assertEquals(true, Whitebox.getInternalState(TestClass.class, "field3"));
        
        Assert.assertEquals("an even other test", Whitebox.getInternalState(testClass, "field6"));
        TestUtils.assertException(IllegalArgumentException.class, "Can not set final java.lang.String field commons.test.TestUtilsTest$TestClass.field6 to java.math.BigDecimal", () ->
                TestUtils.setField(testClass, "field6", BigDecimal.ZERO));
        Assert.assertEquals("an even other test", Whitebox.getInternalState(testClass, "field6"));
        
        Assert.assertEquals(false, Whitebox.getInternalState(testClass, "field10"));
        TestUtils.assertException(IllegalArgumentException.class, "Can not set final boolean field commons.test.TestUtilsTest$TestClass.field10 to null value", () ->
                TestUtils.setField(testClass, "field10", null));
        Assert.assertEquals(false, Whitebox.getInternalState(testClass, "field10"));
        
        Assert.assertFalse(TestUtils.setField(TestClass.class, "missingField", false));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertFalse(TestUtils.setField(TestClass.class, null, 11)));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertFalse(TestUtils.setField(null, "field0", 11)));
        
        Assert.assertFalse(TestUtils.setField(TestSubClass.class, "missingField", false));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertFalse(TestUtils.setField(TestSubClass.class, null, 11)));
        
        Assert.assertFalse(TestUtils.setField(testClass, "missingField", false));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertFalse(TestUtils.setField((Object) null, "field7", 11)));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertFalse(TestUtils.setField(testClass, null, 11)));
        
        Assert.assertFalse(TestUtils.setField(subClass, "missingField", false));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertFalse(TestUtils.setField(subClass, null, 11)));
    }
    
    /**
     * JUnit test of getEnum.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#getEnum(Class, String)
     */
    @Test
    public void testGetEnum() throws Exception {
        Class<?> retrievedEnum;
        Object[] enumValues;
        
        //standard
        
        retrievedEnum = TestUtils.getEnum(TestClass.class, "Enum0");
        Assert.assertNotNull(retrievedEnum);
        enumValues = retrievedEnum.getEnumConstants();
        Assert.assertEquals(3, enumValues.length);
        Assert.assertEquals("PUBLIC_VALUE_1", enumValues[0].toString());
        Assert.assertEquals("PUBLIC_VALUE_2", enumValues[1].toString());
        Assert.assertEquals("PUBLIC_VALUE_3", enumValues[2].toString());
        
        retrievedEnum = TestUtils.getEnum(TestClass.class, "Enum1");
        Assert.assertNotNull(retrievedEnum);
        enumValues = retrievedEnum.getEnumConstants();
        Assert.assertEquals(3, enumValues.length);
        Assert.assertEquals("PRIVATE_VALUE_1", enumValues[0].toString());
        Assert.assertEquals("PRIVATE_VALUE_2", enumValues[1].toString());
        Assert.assertEquals("PRIVATE_VALUE_3", enumValues[2].toString());
        
        //invalid
        
        TestUtils.assertNoException(() ->
                TestUtils.getEnum(TestClass.class, "MissingEnum"));
        retrievedEnum = TestUtils.getEnum(TestClass.class, "MissingEnum");
        Assert.assertNull(retrievedEnum);
        
        TestUtils.assertNoException(() ->
                TestUtils.getEnum(TestClass.class, ""));
        retrievedEnum = TestUtils.getEnum(TestClass.class, "");
        Assert.assertNull(retrievedEnum);
        
        TestUtils.assertNoException(() ->
                TestUtils.getEnum(TestClass.class, null));
        retrievedEnum = TestUtils.getEnum(TestClass.class, null);
        Assert.assertNull(retrievedEnum);
        
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.getEnum(null, "Enum0"));
    }
    
    /**
     * JUnit test of invokeMethod.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#invokeMethod(Object, String, Object...)
     * @see TestUtils#invokeMethod(Class, String, Object...)
     */
    @SuppressWarnings("UnnecessaryBoxing")
    @Test
    public void testInvokeMethod() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        TestClass testClass = new TestClass();
        TestSubClass subClass = new TestSubClass();
        TestClass mockClass = Mockito.mock(TestClass.class, Mockito.CALLS_REAL_METHODS);
        StringBuilder builder;
        
        //standard
        
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(TestClass.class, "staticVoidMethod", false, (long) 1966, builder));
        Assert.assertEquals("static void method hit: (false, 1966)", builder.toString());
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(TestClass.class, "staticVoidMethod", false, null, builder));
        Assert.assertEquals("static void method hit: (false, null)", builder.toString());
        Assert.assertEquals(6.4488121, (double) TestUtils.invokeMethod(TestClass.class, "method1"), TestUtils.DELTA);
        Assert.assertTrue((boolean) TestUtils.invokeMethod(TestClass.class, "method3", "testing"));
        Assert.assertEquals(4, (byte) TestUtils.invokeMethod(TestClass.class, "method5", false, 54, BigDecimal.ZERO));
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(testClass, "voidMethod", 180, 150L, builder));
        Assert.assertEquals("void method hit: (180, 150)", builder.toString());
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(testClass, "voidMethod", 180, null, builder));
        Assert.assertEquals("void method hit: (180, null)", builder.toString());
        Assert.assertEquals(874561564112154L, (long) TestUtils.invokeMethod(testClass, "method7"));
        Assert.assertEquals(-44, (int) TestUtils.invokeMethod(testClass, "method8", "testing"));
        Assert.assertEquals(7.66f, (float) TestUtils.invokeMethod(testClass, "method9", -13), TestUtils.DELTA_FLOAT);
        Assert.assertTrue((boolean) TestUtils.invokeMethod(testClass, "method10"));
        Assert.assertEquals("last test", TestUtils.invokeMethod(testClass, "method11", true, "testing", 19.41f, new char[] {'t', 'e', 's', 't'}));
        
        //subclass
        
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(TestSubClass.class, "staticVoidMethod", true, 1966L, builder));
        Assert.assertEquals("static void method hit: (true, 1966)", builder.toString());
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(TestSubClass.class, "staticVoidMethod", false, null, builder));
        Assert.assertEquals("static void method hit: (false, null)", builder.toString());
        Assert.assertEquals(6.4488121, (double) TestUtils.invokeMethod(TestSubClass.class, "method1"), TestUtils.DELTA);
        Assert.assertTrue((boolean) TestUtils.invokeMethod(TestSubClass.class, "method3", "testing"));
        Assert.assertEquals(4, (byte) TestUtils.invokeMethod(TestSubClass.class, "method5", false, 54, BigDecimal.ZERO));
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(subClass, "voidMethod", -55, Long.valueOf(150), builder));
        Assert.assertEquals("subclass void method hit: (55, 150)", builder.toString());
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(subClass, "voidMethod", 180, null, builder));
        Assert.assertEquals("subclass void method hit: (-180, null)", builder.toString());
        Assert.assertEquals(874561564112154L, (long) TestUtils.invokeMethod(subClass, "method7"));
        Assert.assertEquals(-44, (int) TestUtils.invokeMethod(subClass, "method8", "testing"));
        Assert.assertEquals(7.66f, (float) TestUtils.invokeMethod(subClass, "method9", -13), TestUtils.DELTA_FLOAT);
        Assert.assertTrue((boolean) TestUtils.invokeMethod(subClass, "method10"));
        Assert.assertEquals("last test", TestUtils.invokeMethod(subClass, "method11", true, "testing", 19.41f, new char[] {'t', 'e', 's', 't'}));
        
        //mock
        
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(mockClass, "voidMethod", 12, 844L, builder));
        Assert.assertEquals("void method hit: (12, 844)", builder.toString());
        builder = new StringBuilder();
        Assert.assertNull(TestUtils.invokeMethod(mockClass, "voidMethod", 12, null, builder));
        Assert.assertEquals("void method hit: (12, null)", builder.toString());
        Assert.assertEquals(0L, (long) TestUtils.invokeMethod(mockClass, "method7"));
        Assert.assertEquals(-44, (int) TestUtils.invokeMethod(mockClass, "method8", "testing"));
        Assert.assertEquals(0.0f, (float) TestUtils.invokeMethod(mockClass, "method9", -13), TestUtils.DELTA_FLOAT);
        Assert.assertTrue((boolean) TestUtils.invokeMethod(mockClass, "method10"));
        Assert.assertNull(TestUtils.invokeMethod(mockClass, "method11", true, "testing", 19.41f, new char[] {'t', 'e', 's', 't'}));
        
        //invalid
        TestUtils.assertException(MethodInvocationException.class, "java.lang.IllegalArgumentException", () ->
                Assert.assertNull(TestUtils.invokeMethod(TestClass.class, "staticVoidMethod", null, (long) 1966, new StringBuilder()))
        );
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.MethodNotFoundException: No method found with name 'method1' with parameter types: [ java.lang.String ] in class commons.test.TestUtilsTest$TestClass.", () ->
                TestUtils.invokeMethod(TestClass.class, "method1", "invalid argument"));
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.MethodNotFoundException: No method found with name 'method3' with parameter types: [ java.lang.Integer ] in class commons.test.TestUtilsTest$TestClass.", () ->
                TestUtils.invokeMethod(TestClass.class, "method3", 76));
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.MethodNotFoundException: No method found with name 'method3' with parameter types: [ <none> ] in class commons.test.TestUtilsTest$TestClass.", () ->
                TestUtils.invokeMethod(TestClass.class, "method3"));
        
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.MethodNotFoundException: No method found with name 'missingMethod' with parameter types: [ <none> ] in class commons.test.TestUtilsTest$TestClass.", () ->
                TestUtils.invokeMethod(TestClass.class, "missingMethod"));
        TestUtils.assertException(MethodInvocationException.class, "java.lang.NullPointerException", () ->
                TestUtils.invokeMethod(TestClass.class, null));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(null, "method1"));
        
        TestUtils.assertException(MethodInvocationException.class, "java.lang.IllegalArgumentException", () ->
                Assert.assertNull(TestUtils.invokeMethod(TestSubClass.class, "staticVoidMethod", null, 1966L, new StringBuilder()))
        );
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.MethodNotFoundException: No method found with name 'missingMethod' with parameter types: [ <none> ] in class commons.test.TestUtilsTest$TestSubClass.", () ->
                TestUtils.invokeMethod(TestSubClass.class, "missingMethod"));
        TestUtils.assertException(MethodInvocationException.class, "java.lang.NullPointerException", () ->
                TestUtils.invokeMethod(TestSubClass.class, null));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(null, "method1"));
        
        TestUtils.assertException(MethodInvocationException.class, "java.lang.IllegalArgumentException", () ->
                Assert.assertNull(TestUtils.invokeMethod(testClass, "voidMethod", null, 150L, new StringBuilder()))
        );
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.MethodNotFoundException: No method found with name 'missingMethod' with parameter types: [ <none> ] in class commons.test.TestUtilsTest$TestClass.", () ->
                TestUtils.invokeMethod(testClass, "missingMethod"));
        TestUtils.assertException(MethodInvocationException.class, () ->
                TestUtils.invokeMethod(testClass, null));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeMethod(null, "method7"));
        
        TestUtils.assertException(MethodInvocationException.class, "java.lang.IllegalArgumentException", () ->
                Assert.assertNull(TestUtils.invokeMethod(subClass, "voidMethod", null, Long.valueOf(150), new StringBuilder()))
        );
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.MethodNotFoundException: No method found with name 'missingMethod' with parameter types: [ <none> ] in class commons.test.TestUtilsTest$TestSubClass.", () ->
                TestUtils.invokeMethod(subClass, "missingMethod"));
        TestUtils.assertException(MethodInvocationException.class, "java.lang.IllegalMonitorStateException", () ->
                TestUtils.invokeMethod(subClass, null));
        
        TestUtils.assertException(MethodInvocationException.class, "java.lang.IllegalArgumentException", () ->
                Assert.assertNull(TestUtils.invokeMethod(mockClass, "voidMethod", null, 844L, new StringBuilder()))
        );
    }
    
    /**
     * JUnit test of invokeConstructor.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#invokeConstructor(Class, Object...)
     */
    @SuppressWarnings("UnnecessaryBoxing")
    @Test
    public void testInvokeConstructor() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        TestClass testClass;
        final String stringArgument = "test";
        final JSONObject jsonObjectArgument = new JSONObject();
        final int intArgument = -994;
        final Long longArgument = 180L;
        final boolean booleanArgument = true;
        
        //standard
        
        testClass = TestUtils.invokeConstructor(TestClass.class);
        Assert.assertNotNull(testClass);
        Assert.assertArrayEquals(new Object[] {},
                testClass.arguments);
        
        testClass = TestUtils.invokeConstructor(TestClass.class, stringArgument, jsonObjectArgument, intArgument, longArgument, booleanArgument);
        Assert.assertNotNull(testClass);
        Assert.assertArrayEquals(new Object[] {stringArgument, jsonObjectArgument, intArgument, longArgument, booleanArgument},
                testClass.arguments);
        
        testClass = TestUtils.invokeConstructor(TestClass.class, jsonObjectArgument, stringArgument, intArgument, longArgument, booleanArgument);
        Assert.assertNotNull(testClass);
        Assert.assertArrayEquals(new Object[] {jsonObjectArgument, stringArgument, intArgument, longArgument, booleanArgument},
                testClass.arguments);
        
        testClass = TestUtils.invokeConstructor(TestClass.class, stringArgument, intArgument, longArgument, booleanArgument, jsonObjectArgument);
        Assert.assertNotNull(testClass);
        Assert.assertArrayEquals(new Object[] {stringArgument, intArgument, longArgument, booleanArgument, jsonObjectArgument},
                testClass.arguments);
        
        testClass = TestUtils.invokeConstructor(TestClass.class, jsonObjectArgument, intArgument, longArgument, booleanArgument, stringArgument);
        Assert.assertNotNull(testClass);
        Assert.assertArrayEquals(new Object[] {jsonObjectArgument, intArgument, longArgument, booleanArgument, stringArgument},
                testClass.arguments);
        
        testClass = TestUtils.invokeConstructor(TestClass.class, jsonObjectArgument, intArgument, longArgument, booleanArgument, null);
        Assert.assertNotNull(testClass);
        Assert.assertArrayEquals(new Object[] {jsonObjectArgument, intArgument, longArgument, booleanArgument, null},
                testClass.arguments);
        
        //invalid
        
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.ConstructorNotFoundException: Could not lookup the constructor", () ->
                TestUtils.invokeConstructor(TestClass.class, jsonObjectArgument, null, longArgument, booleanArgument, stringArgument));
        
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.ConstructorNotFoundException: Could not lookup the constructor", () ->
                TestUtils.invokeConstructor(TestClass.class, stringArgument, jsonObjectArgument, intArgument, longArgument));
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.ConstructorNotFoundException: Could not lookup the constructor", () ->
                TestUtils.invokeConstructor(TestClass.class, intArgument));
        TestUtils.assertException(NullPointerException.class, () ->
                TestUtils.invokeConstructor(null));
        
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.ConstructorNotFoundException: Could not lookup the constructor", () ->
                TestUtils.invokeConstructor(TestSubClass.class, stringArgument, jsonObjectArgument, intArgument, longArgument, booleanArgument));
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.ConstructorNotFoundException: Could not lookup the constructor", () ->
                TestUtils.invokeConstructor(TestSubClass.class, intArgument));
        TestUtils.assertException(MethodInvocationException.class, "org.powermock.reflect.exceptions.ConstructorNotFoundException: Could not lookup the constructor", () ->
                TestUtils.invokeConstructor(TestSubClass.class));
    }
    
    /**
     * JUnit test of invokeInterfaceDefaultMethod.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#invokeInterfaceDefaultMethod(Class, String, Object...)
     */
    @Test
    public void testInvokeInterfaceDefaultMethod() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        Assert.assertNull(
                TestUtils.invokeInterfaceDefaultMethod(TestInterface.class, "thisMethod"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Attempted to invoke the method TestInterface::thisMethod() but an exception occurred");
        
        Assert.assertNull(
                TestUtils.invokeInterfaceDefaultMethod(TestInterface.class, "thatMethod", "test", 5, 9));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Attempted to invoke the method TestInterface::thatMethod(String, Integer, Integer) but an exception occurred");
        
        Assert.assertEquals("TestInterface",
                TestUtils.invokeInterfaceDefaultMethod(TestInterface.class, "getName"));
        PowerMockito.verifyNoMoreInteractions(TestUtils.AssertWrapper.class);
    }
    
    /**
     * JUnit test of AssertWrapper.fail.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils.AssertWrapper#fail(String)
     */
    @Test
    public void testAssertWrapperFail() throws Exception {
        TestUtils.assertMethodExists(TestUtils.AssertWrapper.class, "fail", String.class);
    }
    
    /**
     * JUnit test of AssertWrapper.assertEquals.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils.AssertWrapper#assertEquals(String, Object, Object)
     */
    @Test
    public void testAssertWrapperAssertEquals() throws Exception {
        TestUtils.assertMethodExists(TestUtils.AssertWrapper.class, "assertEquals", String.class, Object.class, Object.class);
    }
    
    
    //Inner Classes
    
    /**
     * An interface for testing the getting and setting of fields and interaction with methods.
     */
    private interface TestInterface {
        
        //Methods
        
        /**
         * A default interface method.
         *
         * @return The name of the class.
         */
        default String getName() {
            return "TestInterface";
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
        private enum Enum0 {
            
            //Values
            
            PUBLIC_VALUE_1,
            PUBLIC_VALUE_2,
            PUBLIC_VALUE_3
            
        }
        
        /**
         * A public enum.
         */
        public enum Enum1 {
            
            //Values
            
            PRIVATE_VALUE_1,
            PRIVATE_VALUE_2,
            PRIVATE_VALUE_3
            
        }
        
        //Fields
        
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
         * A private field.
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
         * @param arg2 An sample JSONObject argument.
         * @param arg3 An sample int argument.
         * @param arg4 An sample Long argument.
         * @param arg5 An sample boolean argument.
         */
        public TestClass(String arg1, JSONObject arg2, int arg3, Long arg4, boolean arg5) {
            arguments = new Object[] {arg1, arg2, arg3, arg4, arg5};
        }
        
        /**
         * A private constructor with arguments.
         *
         * @param arg1 An sample JSONObject argument.
         * @param arg2 An sample String argument.
         * @param arg3 An sample int argument.
         * @param arg4 An sample Long argument.
         * @param arg5 An sample boolean argument.
         */
        private TestClass(JSONObject arg1, String arg2, int arg3, Long arg4, boolean arg5) {
            arguments = new Object[] {arg1, arg2, arg3, arg4, arg5};
        }
        
        /**
         * A protected constructor with arguments.
         *
         * @param arg1 An sample String argument.
         * @param arg2 An sample int argument.
         * @param arg3 An sample Long argument.
         * @param arg4 An sample boolean argument.
         * @param arg5 An sample JSONObject argument.
         */
        protected TestClass(String arg1, int arg2, Long arg3, boolean arg4, JSONObject arg5) {
            arguments = new Object[] {arg1, arg2, arg3, arg4, arg5};
        }
        
        /**
         * A constructor with arguments.
         *
         * @param arg1 An sample JSONObject argument.
         * @param arg2 An sample int argument.
         * @param arg3 An sample Long argument.
         * @param arg4 An sample boolean argument.
         * @param arg5 An sample String argument.
         */
        TestClass(JSONObject arg1, int arg2, Long arg3, boolean arg4, String arg5) {
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
            return "TestClass";
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
    private static class TestSubClass extends TestClass {
        
        //Methods
        
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
        
    }
    
}
