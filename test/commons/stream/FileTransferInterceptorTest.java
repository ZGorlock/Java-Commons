/*
 * File:    FileTransferInterceptorTest.java
 * Package: commons.stream
 * Author:  Zachary Gill
 */

package commons.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptorContext;

import commons.console.ConsoleProgressBar;
import commons.string.StringUtility;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.MultiPart;
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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of FileTransferInterceptor.
 *
 * @see FileTransferInterceptor
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
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
        //patterns
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
        FileTransferInterceptor sut;
        ReaderInterceptorContext context;
        MultivaluedMap<String, String> headers;
        InputStream inputStream;
        
        //standard
        
        headers = new MultivaluedHashMap<>();
        headers.put("Content-Disposition", Collections.singletonList("attachment; filename=test.txt; size=500;"));
        inputStream = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8));
        
        context = Mockito.spy(ReaderInterceptorContext.class);
        Mockito.when(context.getHeaders()).thenReturn(headers);
        context.setInputStream(inputStream);
        Mockito.doAnswer(invocationOnMock -> {
            ProgressBarInputStream progressBarInputStream = invocationOnMock.getArgument(0);
            ConsoleProgressBar progressBar = Whitebox.getInternalState(progressBarInputStream, "progressBar");
            Assert.assertEquals("test.txt", progressBar.getTitle());
            Assert.assertEquals(500, progressBar.getTotal());
            Assert.assertEquals("B", progressBar.getUnits());
            return null;
        }).when(context).setInputStream(ArgumentMatchers.any(InputStream.class));
        
        sut = new FileTransferInterceptor();
        sut.aroundReadFrom(context);
        Mockito.verify(context).setInputStream(ArgumentMatchers.any(ProgressBarInputStream.class));
        Mockito.verify(context).proceed();
        
        //no attachment
        
        headers = new MultivaluedHashMap<>();
        inputStream = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8));
        
        context = Mockito.spy(ReaderInterceptorContext.class);
        Mockito.when(context.getHeaders()).thenReturn(headers);
        context.setInputStream(inputStream);
        
        sut = new FileTransferInterceptor();
        sut.aroundReadFrom(context);
        Mockito.verify(context, VerificationModeFactory.times(0))
                .setInputStream(ArgumentMatchers.any(ProgressBarInputStream.class));
        Mockito.verify(context).proceed();
        
        //corrupt header
        
        headers = new MultivaluedHashMap<>();
        headers.put("Content-Disposition", Collections.singletonList("filename=test.txt; size=500;"));
        inputStream = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8));
        
        context = Mockito.spy(ReaderInterceptorContext.class);
        Mockito.when(context.getHeaders()).thenReturn(headers);
        context.setInputStream(inputStream);
        
        sut = new FileTransferInterceptor();
        sut.aroundReadFrom(context);
        Mockito.verify(context, VerificationModeFactory.times(0))
                .setInputStream(ArgumentMatchers.any(ProgressBarInputStream.class));
        Mockito.verify(context).proceed();
    }
    
    /**
     * JUnit test of aroundWriteTo.
     *
     * @throws Exception When there is an exception.
     * @see FileTransferInterceptor#aroundWriteTo(WriterInterceptorContext)
     */
    @Test
    public void testAroundWriteTo() throws Exception {
        FileTransferInterceptor sut;
        WriterInterceptorContext context;
        OutputStream outputStream;
        MultiPart entity;
        BodyPart bodyPart1;
        BodyPart bodyPart2;
        BodyPart bodyPart3;
        ContentDisposition contentDisposition1;
        ContentDisposition contentDisposition2;
        ContentDisposition contentDisposition3;
        
        //standard
        
        entity = new MultiPart();
        outputStream = new ByteArrayOutputStream();
        
        contentDisposition1 = Mockito.mock(ContentDisposition.class);
        Mockito.when(contentDisposition1.getFileName()).thenReturn("test.txt");
        Mockito.when(contentDisposition1.getSize()).thenReturn(500L);
        bodyPart1 = Mockito.mock(BodyPart.class);
        Mockito.when(bodyPart1.getContentDisposition()).thenReturn(contentDisposition1);
        entity.bodyPart(bodyPart1);
        
        contentDisposition2 = Mockito.mock(ContentDisposition.class);
        Mockito.when(contentDisposition2.getFileName()).thenReturn(null);
        bodyPart2 = Mockito.mock(BodyPart.class);
        Mockito.when(bodyPart2.getContentDisposition()).thenReturn(contentDisposition2);
        Mockito.when(bodyPart2.getEntity()).thenReturn("0123456789");
        entity.bodyPart(bodyPart2);
        
        contentDisposition3 = Mockito.mock(ContentDisposition.class);
        Mockito.when(contentDisposition3.getFileName()).thenReturn(null);
        bodyPart3 = Mockito.mock(BodyPart.class);
        Mockito.when(bodyPart3.getContentDisposition()).thenReturn(contentDisposition3);
        Mockito.when(bodyPart3.getEntity()).thenReturn(StringUtility.repeatString("0123456789", 15));
        entity.bodyPart(bodyPart3);
        
        context = Mockito.spy(WriterInterceptorContext.class);
        Mockito.when(context.getEntity()).thenReturn(entity);
        context.setOutputStream(outputStream);
        Mockito.doAnswer(invocationOnMock -> {
            ProgressBarOutputStream progressBarOutputStream = invocationOnMock.getArgument(0);
            ConsoleProgressBar progressBar = Whitebox.getInternalState(progressBarOutputStream, "progressBar");
            Assert.assertEquals("test.txt", progressBar.getTitle());
            Assert.assertEquals(660, progressBar.getTotal());
            Assert.assertEquals("B", progressBar.getUnits());
            return null;
        }).when(context).setOutputStream(ArgumentMatchers.any(OutputStream.class));
        
        sut = new FileTransferInterceptor();
        sut.aroundWriteTo(context);
        Mockito.verify(context).setOutputStream(ArgumentMatchers.any(ProgressBarOutputStream.class));
        Mockito.verify(context).proceed();
        
        //only file
        
        entity = new MultiPart();
        outputStream = new ByteArrayOutputStream();
        
        contentDisposition1 = Mockito.mock(ContentDisposition.class);
        Mockito.when(contentDisposition1.getFileName()).thenReturn("test.txt");
        Mockito.when(contentDisposition1.getSize()).thenReturn(500L);
        bodyPart1 = Mockito.mock(BodyPart.class);
        Mockito.when(bodyPart1.getContentDisposition()).thenReturn(contentDisposition1);
        entity.bodyPart(bodyPart1);
        
        context = Mockito.spy(WriterInterceptorContext.class);
        Mockito.when(context.getEntity()).thenReturn(entity);
        context.setOutputStream(outputStream);
        Mockito.doAnswer(invocationOnMock -> {
            ProgressBarOutputStream progressBarOutputStream = invocationOnMock.getArgument(0);
            ConsoleProgressBar progressBar = Whitebox.getInternalState(progressBarOutputStream, "progressBar");
            Assert.assertEquals("test.txt", progressBar.getTitle());
            Assert.assertEquals(500, progressBar.getTotal());
            Assert.assertEquals("B", progressBar.getUnits());
            return null;
        }).when(context).setOutputStream(ArgumentMatchers.any(OutputStream.class));
        
        sut = new FileTransferInterceptor();
        sut.aroundWriteTo(context);
        Mockito.verify(context).setOutputStream(ArgumentMatchers.any(ProgressBarOutputStream.class));
        Mockito.verify(context).proceed();
        
        //no file
        
        entity = new MultiPart();
        outputStream = new ByteArrayOutputStream();
        
        contentDisposition2 = Mockito.mock(ContentDisposition.class);
        Mockito.when(contentDisposition2.getFileName()).thenReturn(null);
        bodyPart2 = Mockito.mock(BodyPart.class);
        Mockito.when(bodyPart2.getContentDisposition()).thenReturn(contentDisposition2);
        Mockito.when(bodyPart2.getEntity()).thenReturn("0123456789");
        entity.bodyPart(bodyPart2);
        
        context = Mockito.spy(WriterInterceptorContext.class);
        Mockito.when(context.getEntity()).thenReturn(entity);
        context.setOutputStream(outputStream);
        
        sut = new FileTransferInterceptor();
        sut.aroundWriteTo(context);
        Mockito.verify(context, VerificationModeFactory.times(0))
                .setOutputStream(ArgumentMatchers.any(ProgressBarOutputStream.class));
        Mockito.verify(context).proceed();
        
        //no parts
        
        entity = new MultiPart();
        outputStream = new ByteArrayOutputStream();
        
        context = Mockito.spy(WriterInterceptorContext.class);
        Mockito.when(context.getEntity()).thenReturn(entity);
        context.setOutputStream(outputStream);
        
        sut = new FileTransferInterceptor();
        sut.aroundWriteTo(context);
        Mockito.verify(context, VerificationModeFactory.times(0))
                .setOutputStream(ArgumentMatchers.any(ProgressBarOutputStream.class));
        Mockito.verify(context).proceed();
        
        //not multipart
        
        outputStream = new ByteArrayOutputStream();
        
        context = Mockito.spy(WriterInterceptorContext.class);
        Mockito.when(context.getEntity()).thenReturn(new Object());
        context.setOutputStream(outputStream);
        
        sut = new FileTransferInterceptor();
        sut.aroundWriteTo(context);
        Mockito.verify(context, VerificationModeFactory.times(0))
                .setOutputStream(ArgumentMatchers.any(ProgressBarOutputStream.class));
        Mockito.verify(context).proceed();
    }
    
}
