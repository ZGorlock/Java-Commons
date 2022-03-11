/*
 * File:    ProgressBarOutputStreamTest.java
 * Package: commons.io.stream
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.stream;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import commons.console.ProgressBar;
import commons.object.string.StringUtility;
import commons.test.TestAccess;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ProgressBarOutputStream.
 *
 * @see ProgressBarOutputStream
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ProgressBarOutputStream.class})
public class ProgressBarOutputStreamTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProgressBarOutputStreamTest.class);
    
    
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
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBarOutputStream#ProgressBarOutputStream(String, OutputStream, long)
     * @see ProgressBarOutputStream#ProgressBarOutputStream(OutputStream, long)
     */
    @Test
    public void testConstructors() throws Exception {
        ProgressBarOutputStream sut;
        ProgressBar progressBar;
        OutputStream outputStream = new ByteArrayOutputStream(100);
        
        //standard
        sut = new ProgressBarOutputStream("test", outputStream, 4);
        progressBar = TestAccess.getFieldValue(sut, ProgressBar.class, "progressBar");
        Assert.assertEquals("test", progressBar.getTitle());
        Assert.assertEquals(4, progressBar.getTotal());
        Assert.assertEquals("B", progressBar.getUnits());
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "progress"));
        
        //default title
        sut = new ProgressBarOutputStream(outputStream, 100);
        progressBar = TestAccess.getFieldValue(sut, ProgressBar.class, "progressBar");
        Assert.assertEquals("", progressBar.getTitle());
        Assert.assertEquals(100, progressBar.getTotal());
        Assert.assertEquals("B", progressBar.getUnits());
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "progress"));
    }
    
    /**
     * JUnit test of write.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBarOutputStream#write(byte[], int, int)
     */
    @Test
    public void testWrite() throws Exception {
        ProgressBarOutputStream sut;
        ProgressBar progressBar;
        OutputStream outputStream;
        byte[] buffer;
        
        //standard
        outputStream = new ByteArrayOutputStream(200);
        sut = new ProgressBarOutputStream("test", outputStream, 200);
        progressBar = Mockito.mock(ProgressBar.class);
        TestAccess.setFieldValue(sut, "progressBar", progressBar);
        buffer = "testt".getBytes(StandardCharsets.UTF_8);
        sut.write(buffer, 0, 5);
        Assert.assertEquals(5L, TestAccess.getFieldValue(sut, "progress"));
        Mockito.verify(progressBar).update(ArgumentMatchers.eq(5L));
        buffer = "est".getBytes(StandardCharsets.UTF_8);
        sut.write(buffer, 0, 3);
        Assert.assertEquals(8L, TestAccess.getFieldValue(sut, "progress"));
        Mockito.verify(progressBar).update(ArgumentMatchers.eq(8L));
        buffer = StringUtility.repeatString("test", 48).getBytes(StandardCharsets.UTF_8);
        sut.write(buffer, 0, 192);
        Assert.assertEquals(200L, TestAccess.getFieldValue(sut, "progress"));
        Mockito.verify(progressBar).update(ArgumentMatchers.eq(200L));
        
        //end of stream
        TestUtils.assertNoException(() ->
                new ProgressBarOutputStream("test", new ByteArrayOutputStream(0), 200)
                        .write(new byte[5], 0, 5));
        
        //overflow
        TestUtils.assertException(IndexOutOfBoundsException.class, () ->
                new ProgressBarOutputStream("test", new ByteArrayOutputStream(200), 200)
                        .write(StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8), 0, 201));
    }
    
    /**
     * JUnit test of close.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBarOutputStream#close()
     */
    @Test
    public void testClose() throws Exception {
        OutputStream outputStream = new ByteArrayOutputStream(100);
        ProgressBarOutputStream sut = new ProgressBarOutputStream("test", outputStream, 200);
        ProgressBar progressBar = Mockito.mock(ProgressBar.class);
        TestAccess.setFieldValue(sut, "progressBar", progressBar);
        sut.close();
        Assert.assertEquals(0L, TestAccess.getFieldValue(sut, "progress"));
        Mockito.verify(progressBar).complete();
    }
    
}
