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

import commons.math.component.BaseComponent;
import commons.math.component.ComponentInterface;
import commons.string.StringUtility;
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
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(0.000000001, TestUtils.DELTA, 0.000000001);
    }
    
    /**
     * JUnit test of assertException.
     *
     * @throws Exception When there is an exception.
     * @see TestUtils#assertException(Class, Runnable)
     * @see TestUtils#assertException(Class, String, Runnable)
     */
    @Test
    public void testAssertException() throws Exception {
        PowerMockito.mockStatic(TestUtils.AssertWrapper.class);
        
        TestUtils.assertException(NumberFormatException.class, () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NumberFormatException but instead it produced a NumberFormatException", //success
                NumberFormatException.class, NumberFormatException.class);
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\"", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(2));
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NumberFormatException but instead it produced a NumberFormatException", //success
                NumberFormatException.class, NumberFormatException.class);
        TestUtils.AssertWrapper.assertEquals(
                "Expected the error message of the NumberFormatException to be: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\" but the error message was: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\"", //success
                "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.");
        
        TestUtils.assertException(NumberFormatException.class, "Could not parse BigDecimal", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(3));
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NumberFormatException but instead it produced a NumberFormatException", //success
                NumberFormatException.class, NumberFormatException.class);
        TestUtils.AssertWrapper.assertEquals(
                "Expected the error message of the NumberFormatException to be: \"Could not parse BigDecimal\" but the error message was: \"Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.\"",
                "Could not parse BigDecimal", "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.");
        
        TestUtils.assertException(NullPointerException.class, () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NullPointerException but instead it produced a NumberFormatException",
                NullPointerException.class, NumberFormatException.class);
        
        TestUtils.assertException(NullPointerException.class, "Could not parse the BigDecimal", () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class, VerificationModeFactory.times(2));
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NullPointerException but instead it produced a NumberFormatException",
                NullPointerException.class, NumberFormatException.class);
        PowerMockito.verifyNoMoreInteractions();
        
        TestUtils.assertException(NullPointerException.class, () ->
                new BigDecimal("1564"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected code to produce a NullPointerException but no exception was produced");
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
                BaseComponent.class, "set", Integer.class, Number.class);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected method BaseComponent::set(Integer, Number) to exist but it does not");
        
        TestUtils.assertMethodExists(
                BaseComponent.class, "sat", int.class, Number.class);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected method BaseComponent::sat(int, Number) to exist but it does not");
        
        TestUtils.assertMethodExists(
                BaseComponent.class, "set", int.class, Number.class);
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
                BaseComponent.class, "set", int.class, Number.class);
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Expected method BaseComponent::set(int, Number) to not exist but it does");
        
        TestUtils.assertMethodDoesNotExist(
                BaseComponent.class, "set", Integer.class, Number.class);
        TestUtils.assertMethodDoesNotExist(
                BaseComponent.class, "sat", int.class, Number.class);
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
        
        for (int i = 0; i < 10; i++) {
            if (i < 4) {
                Assert.assertEquals(Whitebox.getInternalState(TestClass.class, "field" + i),
                        TestUtils.getField(TestClass.class, "field" + i));
            } else {
                Assert.assertEquals(Whitebox.getInternalState(testClass, "field" + i),
                        TestUtils.getField(testClass, "field" + i));
            }
        }
        
        //sub class
        
        for (int i = 0; i < 10; i++) {
            if (i < 4) {
                Assert.assertEquals(Whitebox.getInternalState(TestClass.class, "field" + i),
                        TestUtils.getField(TestSubClass.class, "field" + i));
            } else {
                Assert.assertEquals(Whitebox.getInternalState(testClass, "field" + i),
                        TestUtils.getField(subClass, "field" + i));
            }
        }
        
        //mock
        
        for (int i = 4; i < 10; i++) {
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
        TestClass mockClass = Mockito.mock(TestClass.class);
        
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
        
        Assert.assertEquals("another test", Whitebox.getInternalState(testClass, "field4"));
        Assert.assertTrue(TestUtils.setField(testClass, "field4", "an even other test"));
        Assert.assertEquals("an even other test", Whitebox.getInternalState(testClass, "field4"));
        
        Assert.assertEquals(874561564112154L, (long) Whitebox.getInternalState(testClass, "field5"));
        Assert.assertTrue(TestUtils.setField(testClass, "field5", 156423157842311L));
        Assert.assertEquals(156423157842311L, (long) Whitebox.getInternalState(testClass, "field5"));
        
        Assert.assertEquals(-44, (int) Whitebox.getInternalState(testClass, "field6"));
        Assert.assertTrue(TestUtils.setField(testClass, "field6", 1568));
        Assert.assertEquals(1568, (int) Whitebox.getInternalState(testClass, "field6"));
        
        Assert.assertEquals(7.66f, Whitebox.getInternalState(testClass, "field7"), (float) TestUtils.DELTA);
        Assert.assertTrue(TestUtils.setField(testClass, "field7", 3.46f));
        Assert.assertEquals(3.46f, Whitebox.getInternalState(testClass, "field7"), (float) TestUtils.DELTA);
        
        Assert.assertEquals(true, Whitebox.getInternalState(testClass, "field8"));
        Assert.assertTrue(TestUtils.setField(testClass, "field8", false));
        Assert.assertEquals(false, Whitebox.getInternalState(testClass, "field8"));
        
        Assert.assertEquals("last test", Whitebox.getInternalState(testClass, "field9"));
        Assert.assertTrue(TestUtils.setField(testClass, "field9", "the last test"));
        Assert.assertEquals("the last test", Whitebox.getInternalState(testClass, "field9"));
        
        //sub class
        
        Assert.assertEquals(7, (int) Whitebox.getInternalState(TestSubClass.class, "field0"));
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field0", 10));
        Assert.assertEquals(10, (int) Whitebox.getInternalState(TestSubClass.class, "field0"));
        
        Assert.assertEquals(0.221548773, Whitebox.getInternalState(TestSubClass.class, "field1"), TestUtils.DELTA);
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field1", 16.0156748941));
        Assert.assertEquals(16.0156748941, Whitebox.getInternalState(TestSubClass.class, "field1"), TestUtils.DELTA);
        
        Assert.assertEquals("different", Whitebox.getInternalState(TestSubClass.class, "field2"));
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field2", "another different"));
        Assert.assertEquals("another different", Whitebox.getInternalState(TestSubClass.class, "field2"));
        
        Assert.assertEquals(false, Whitebox.getInternalState(TestSubClass.class, "field3"));
        Assert.assertTrue(TestUtils.setField(TestSubClass.class, "field3", true));
        Assert.assertEquals(true, Whitebox.getInternalState(TestSubClass.class, "field3"));
        
        Assert.assertEquals("another test", Whitebox.getInternalState(subClass, "field4"));
        Assert.assertTrue(TestUtils.setField(subClass, "field4", "an even other test"));
        Assert.assertEquals("an even other test", Whitebox.getInternalState(subClass, "field4"));
        
        Assert.assertEquals(874561564112154L, (long) Whitebox.getInternalState(subClass, "field5"));
        Assert.assertTrue(TestUtils.setField(subClass, "field5", 156423157842311L));
        Assert.assertEquals(156423157842311L, (long) Whitebox.getInternalState(subClass, "field5"));
        
        Assert.assertEquals(-44, (int) Whitebox.getInternalState(subClass, "field6"));
        Assert.assertTrue(TestUtils.setField(subClass, "field6", 1568));
        Assert.assertEquals(1568, (int) Whitebox.getInternalState(subClass, "field6"));
        
        Assert.assertEquals(7.66f, Whitebox.getInternalState(subClass, "field7"), (float) TestUtils.DELTA);
        Assert.assertTrue(TestUtils.setField(subClass, "field7", 3.46f));
        Assert.assertEquals(3.46f, Whitebox.getInternalState(subClass, "field7"), (float) TestUtils.DELTA);
        
        Assert.assertEquals(true, Whitebox.getInternalState(subClass, "field8"));
        Assert.assertTrue(TestUtils.setField(subClass, "field8", false));
        Assert.assertEquals(false, Whitebox.getInternalState(subClass, "field8"));
        
        Assert.assertEquals("last test", Whitebox.getInternalState(subClass, "field9"));
        Assert.assertTrue(TestUtils.setField(subClass, "field9", "the last test"));
        Assert.assertEquals("the last test", Whitebox.getInternalState(subClass, "field9"));
        
        //mock
        
        Assert.assertEquals((String) null, Whitebox.getInternalState(mockClass, "field4"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field4", "an even other test"));
        Assert.assertEquals("an even other test", Whitebox.getInternalState(mockClass, "field4"));
        
        Assert.assertEquals(0L, (long) Whitebox.getInternalState(mockClass, "field5"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field5", 156423157842311L));
        Assert.assertEquals(156423157842311L, (long) Whitebox.getInternalState(mockClass, "field5"));
        
        Assert.assertEquals(0, (int) Whitebox.getInternalState(mockClass, "field6"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field6", 1568));
        Assert.assertEquals(1568, (int) Whitebox.getInternalState(mockClass, "field6"));
        
        Assert.assertEquals(0.0f, Whitebox.getInternalState(mockClass, "field7"), (float) TestUtils.DELTA);
        Assert.assertTrue(TestUtils.setField(mockClass, "field7", 3.46f));
        Assert.assertEquals(3.46f, Whitebox.getInternalState(mockClass, "field7"), (float) TestUtils.DELTA);
        
        Assert.assertEquals(false, Whitebox.getInternalState(mockClass, "field8"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field8", true));
        Assert.assertEquals(true, Whitebox.getInternalState(mockClass, "field8"));
        
        Assert.assertEquals((String) null, Whitebox.getInternalState(mockClass, "field9"));
        Assert.assertTrue(TestUtils.setField(mockClass, "field9", "the last test"));
        Assert.assertEquals("the last test", Whitebox.getInternalState(mockClass, "field9"));
        
        //invalid
        
        Assert.assertEquals(10, (int) Whitebox.getInternalState(TestClass.class, "field0"));
        TestUtils.assertException(IllegalArgumentException.class, "Can not set static int field commons.test.TestUtilsTest$TestClass.field0 to java.lang.Boolean", () ->
                TestUtils.setField(TestClass.class, "field0", false));
        Assert.assertEquals(10, (int) Whitebox.getInternalState(TestClass.class, "field0"));
        
        Assert.assertEquals(true, Whitebox.getInternalState(TestClass.class, "field3"));
        TestUtils.assertException(IllegalArgumentException.class, "Can not set static boolean field commons.test.TestUtilsTest$TestClass.field3 to java.lang.Integer", () ->
                TestUtils.setField(TestClass.class, "field3", 103));
        Assert.assertEquals(true, Whitebox.getInternalState(TestClass.class, "field3"));
        
        Assert.assertEquals("an even other test", Whitebox.getInternalState(testClass, "field4"));
        TestUtils.assertException(IllegalArgumentException.class, "Can not set final java.lang.String field commons.test.TestUtilsTest$TestClass.field4 to java.math.BigDecimal", () ->
                TestUtils.setField(testClass, "field4", BigDecimal.ZERO));
        Assert.assertEquals("an even other test", Whitebox.getInternalState(testClass, "field4"));
        
        Assert.assertEquals(false, Whitebox.getInternalState(testClass, "field8"));
        TestUtils.assertException(IllegalArgumentException.class, "Can not set final boolean field commons.test.TestUtilsTest$TestClass.field8 to null value", () ->
                TestUtils.setField(testClass, "field8", null));
        Assert.assertEquals(false, Whitebox.getInternalState(testClass, "field8"));
        
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
                Assert.assertFalse(TestUtils.setField((Object) null, "field5", 11)));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertFalse(TestUtils.setField(testClass, null, 11)));
        
        Assert.assertFalse(TestUtils.setField(subClass, "missingField", false));
        TestUtils.assertException(NullPointerException.class, () ->
                Assert.assertFalse(TestUtils.setField(subClass, null, 11)));
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
                TestUtils.invokeInterfaceDefaultMethod(ComponentInterface.class, "thisMethod"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Attempted to invoke the method ComponentInterface::thisMethod() but an exception occurred");
        
        Assert.assertNull(
                TestUtils.invokeInterfaceDefaultMethod(ComponentInterface.class, "thatMethod", "test", 5, 9));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.fail(
                "Attempted to invoke the method ComponentInterface::thatMethod(String, Integer, Integer) but an exception occurred");
        
        Assert.assertEquals("Component Interface",
                TestUtils.invokeInterfaceDefaultMethod(ComponentInterface.class, "getName"));
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
     * A class for testing the getting and setting of fields.
     */
    private static class TestClass {
        
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
         * A private final field.
         */
        private final String field4 = "another test";
        
        /**
         * A private field.
         */
        private long field5 = 874561564112154L;
        
        /**
         * A public final field.
         */
        public final int field6 = -44;
        
        /**
         * A private field.
         */
        public float field7 = 7.66f;
        
        /**
         * A package private final field.
         */
        final boolean field8 = true;
        
        /**
         * A package private final field.
         */
        String field9 = "last test";
        
    }
    
    /**
     * A subclass for testing the getting and setting of fields.
     */
    private static class TestSubClass extends TestClass {
        
    }
    
}
