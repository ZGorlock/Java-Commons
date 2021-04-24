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
import commons.string.StringUtility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
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
        
        TestUtils.assertException(NullPointerException.class, () ->
                new BigDecimal("15a4"));
        PowerMockito.verifyStatic(TestUtils.AssertWrapper.class);
        TestUtils.AssertWrapper.assertEquals(
                "Expected code to produce a NullPointerException but instead it produced a NumberFormatException",
                NullPointerException.class, NumberFormatException.class);
        
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
    
}
