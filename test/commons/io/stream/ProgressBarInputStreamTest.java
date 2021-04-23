/*
 * File:    ProgressBarInputStream.java
 * Package: commons.io.stream
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.stream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import commons.console.ConsoleProgressBar;
import commons.string.StringUtility;
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
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ProgressBarInputStream.
 *
 * @see ProgressBarInputStream
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ProgressBarInputStream.class})
public class ProgressBarInputStreamTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProgressBarInputStreamTest.class);
    
    
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
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBarInputStream#ProgressBarInputStream(String, InputStream, long)
     * @see ProgressBarInputStream#ProgressBarInputStream(InputStream, long)
     */
    @Test
    public void testConstructors() throws Exception {
        ProgressBarInputStream sut;
        ConsoleProgressBar progressBar;
        InputStream inputStream = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8));
        
        //standard
        sut = new ProgressBarInputStream("test", inputStream, 4);
        progressBar = Whitebox.getInternalState(sut, "progressBar");
        Assert.assertEquals("test", progressBar.getTitle());
        Assert.assertEquals(4, progressBar.getTotal());
        Assert.assertEquals("B", progressBar.getUnits());
        Assert.assertEquals(0L, (long) Whitebox.getInternalState(sut, "progress"));
        
        //default title
        sut = new ProgressBarInputStream(inputStream, 100);
        progressBar = Whitebox.getInternalState(sut, "progressBar");
        Assert.assertEquals("", progressBar.getTitle());
        Assert.assertEquals(100, progressBar.getTotal());
        Assert.assertEquals("B", progressBar.getUnits());
        Assert.assertEquals(0L, (long) Whitebox.getInternalState(sut, "progress"));
    }
    
    /**
     * JUnit test of read.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBarInputStream#read(byte[], int, int)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testRead() throws Exception {
        ProgressBarInputStream sut;
        ConsoleProgressBar progressBar;
        InputStream inputStream;
        byte[] buffer;
        int read;
        
        //standard
        inputStream = new ByteArrayInputStream(StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8));
        sut = new ProgressBarInputStream("test", inputStream, 200);
        progressBar = Mockito.mock(ConsoleProgressBar.class);
        Whitebox.setInternalState(sut, "progressBar", progressBar);
        buffer = new byte[5];
        read = sut.read(buffer, 0, 5);
        Assert.assertEquals(5, read);
        Assert.assertEquals("testt", new String(buffer));
        Assert.assertEquals(5L, (long) Whitebox.getInternalState(sut, "progress"));
        Mockito.verify(progressBar).update(ArgumentMatchers.eq(5L));
        buffer = new byte[3];
        read = sut.read(buffer, 0, 3);
        Assert.assertEquals(3, read);
        Assert.assertEquals("est", new String(buffer));
        Assert.assertEquals(8L, (long) Whitebox.getInternalState(sut, "progress"));
        Mockito.verify(progressBar).update(ArgumentMatchers.eq(8L));
        buffer = new byte[192];
        read = sut.read(buffer, 0, 192);
        Assert.assertEquals(192, read);
        Assert.assertEquals(StringUtility.repeatString("test", 48), new String(buffer));
        Assert.assertEquals(200L, (long) Whitebox.getInternalState(sut, "progress"));
        Mockito.verify(progressBar).update(ArgumentMatchers.eq(200L));
        
        //end of stream
        inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        sut = new ProgressBarInputStream("test", inputStream, 200);
        progressBar = Mockito.mock(ConsoleProgressBar.class);
        Whitebox.setInternalState(sut, "progressBar", progressBar);
        buffer = new byte[5];
        read = sut.read(buffer, 0, 5);
        Assert.assertEquals(-1, read);
        
        //overflow
        inputStream = new ByteArrayInputStream(StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8));
        sut = new ProgressBarInputStream("test", inputStream, 200);
        progressBar = Mockito.mock(ConsoleProgressBar.class);
        Whitebox.setInternalState(sut, "progressBar", progressBar);
        buffer = new byte[200];
        try {
            sut.read(buffer, 0, 201);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
    
    /**
     * JUnit test of close.
     *
     * @throws Exception When there is an exception.
     * @see ProgressBarInputStream#close()
     */
    @Test
    public void testClose() throws Exception {
        InputStream inputStream = new ByteArrayInputStream(StringUtility.repeatString("test", 50).getBytes(StandardCharsets.UTF_8));
        ProgressBarInputStream sut = new ProgressBarInputStream("test", inputStream, 200);
        ConsoleProgressBar progressBar = Mockito.mock(ConsoleProgressBar.class);
        Whitebox.setInternalState(sut, "progressBar", progressBar);
        sut.close();
        Assert.assertEquals(0L, (long) Whitebox.getInternalState(sut, "progress"));
        Mockito.verify(progressBar).complete();
    }
    
}
