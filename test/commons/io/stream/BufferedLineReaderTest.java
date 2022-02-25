/*
 * File:    BufferedLineReaderTest.java
 * Package: commons.io.stream
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

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
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of BufferedLineReader.
 *
 * @see BufferedLineReader
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BufferedLineReader.class})
public class BufferedLineReaderTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BufferedLineReaderTest.class);
    
    
    //Field
    
    /**
     * The system under test.
     */
    private BufferedLineReader sut;
    
    /**
     * The input stream of the system under test.
     */
    private PipedInputStream systemIn;
    
    /**
     * The output stream to the system under test.
     */
    private PipedOutputStream writer;
    
    
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
    @Before
    public void setup() throws Exception {
        writer = Mockito.spy(new PipedOutputStream());
        systemIn = Mockito.spy(new PipedInputStream());
        Mockito.doAnswer((Answer<Void>) invocation -> {
            invocation.callRealMethod();
            writer.flush();
            return null;
        }).when(writer).write(ArgumentMatchers.any(byte[].class));
        writer.connect(systemIn);
        
        sut = new BufferedLineReader(new InputStreamReader(systemIn));
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @After
    public void cleanup() throws Exception {
        sut.close();
    }
    
    
    //Tests
    
    /**
     * JUnit test of constants.
     *
     * @throws Exception When there is an exception.
     * @see BufferedLineReader#BUFFER_SIZE
     */
    @Test
    public void testConstants() throws Exception {
        //standard
        Assert.assertEquals(32768, BufferedLineReader.BUFFER_SIZE);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see BufferedLineReader#BufferedLineReader(Reader)
     */
    @Test
    public void testConstructors() throws Exception {
        final Reader mockReader = Mockito.mock(Reader.class);
        BufferedLineReader sut;
        
        //standard
        sut = new BufferedLineReader(mockReader);
        Assert.assertNotNull(sut);
        Assert.assertTrue(sut instanceof BufferedLineReader);
        Assert.assertTrue(sut instanceof BufferedReader);
        Assert.assertEquals(mockReader, TestUtils.getFieldValue(sut, "in"));
        Assert.assertEquals(BufferedLineReader.BUFFER_SIZE, TestUtils.getFieldValue(sut, char[].class, "cb").length);
    }
    
    /**
     * JUnit test of lineReady.
     *
     * @throws Exception When there is an exception.
     * @see BufferedLineReader#lineReady()
     */
    @Test
    public void testLineReady() throws Exception {
        //empty
        Assert.assertFalse(sut.lineReady());
        
        //standard
        Assert.assertFalse(sut.lineReady());
        writer.write("t".getBytes(StandardCharsets.UTF_8));
        Assert.assertFalse(sut.lineReady());
        writer.write("est".getBytes(StandardCharsets.UTF_8));
        Assert.assertFalse(sut.lineReady());
        writer.write("\n".getBytes(StandardCharsets.UTF_8));
        Assert.assertTrue(sut.lineReady());
        Assert.assertTrue(sut.lineReady());
        Assert.assertEquals("test", sut.readLine());
        Assert.assertFalse(sut.lineReady());
        
        //another line
        Assert.assertFalse(sut.lineReady());
        writer.write("test 2\n".getBytes(StandardCharsets.UTF_8));
        Assert.assertTrue(sut.lineReady());
        Assert.assertEquals("test 2", sut.readLine());
        Assert.assertFalse(sut.lineReady());
        
        //multiple lines
        Assert.assertFalse(sut.lineReady());
        writer.write("test 3\ntest 4\ntest 5\n".getBytes(StandardCharsets.UTF_8));
        Assert.assertTrue(sut.lineReady());
        Assert.assertTrue(sut.lineReady());
        Assert.assertEquals("test 3", sut.readLine());
        Assert.assertTrue(sut.lineReady());
        Assert.assertEquals("test 4", sut.readLine());
        Assert.assertTrue(sut.lineReady());
        Assert.assertEquals("test 5", sut.readLine());
        Assert.assertFalse(sut.lineReady());
    }
    
}
