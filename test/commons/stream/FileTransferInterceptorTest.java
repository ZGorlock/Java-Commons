/*
 * File:    FileTransferInterceptorTest.java
 * Package: commons.stream
 * Author:  Zachary Gill
 */

package commons.stream;

import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of FileTransferInterceptor.
 *
 * @see FileTransferInterceptor
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"com.sun.org.apache.*", "javax.*", "org.xml.*", "org.w3c.*"})
@PrepareForTest({FileTransferInterceptor.class})
public class FileTransferInterceptorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FileTransferInterceptorTest.class);
    
    
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
     * @see FileTransferInterceptor#CONTENT_DISPOSITION_PATTERN
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals("attachment;\\sfilename=(?<fileName>[^;]+);\\ssize=(?<fileSize>[^;]+);", FileTransferInterceptor.CONTENT_DISPOSITION_PATTERN.pattern());
    }
    
    /**
     * JUnit test of aroundReadFrom.
     *
     * @throws Exception When there is an exception.
     * @see FileTransferInterceptor#aroundReadFrom(ReaderInterceptorContext)
     */
    @Test
    public void testAroundReadFrom() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of aroundWriteTo.
     *
     * @throws Exception When there is an exception.
     * @see FileTransferInterceptor#aroundWriteTo(WriterInterceptorContext)
     */
    @Test
    public void testAroundWriteTo() throws Exception {
        //TODO
    }
    
}
