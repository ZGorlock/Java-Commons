/*
 * File:    FileTransferInterceptor.java
 * Package: commons.stream
 * Author:  Zachary Gill
 */

package commons.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An interceptor for monitoring the progress of file uploads and downloads.
 */
@Provider
@PreMatching
@ConstrainedTo(RuntimeType.CLIENT)
public class FileTransferInterceptor implements WriterInterceptor, ReaderInterceptor {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FileTransferInterceptor.class);
    
    
    //Constants
    
    /**
     * The regex pattern for a Content-Disposition header.
     */
    public static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile("attachment;\\sfilename=(?<fileName>[^;]+);\\ssize=(?<fileSize>[^;]+);");
    
    
    //Methods
    
    /**
     * Sets the input stream for uploads so that the progress of the upload can be monitored.
     *
     * @param context The invocation context.
     * @return The result of the next interceptor invoked or the wrapped method if last interceptor in chain.
     * @throws IOException             If MessageBodyReader.readFrom encounters an exception reading from the input stream.
     * @throws WebApplicationException If there is an error in the MessageBodyReader.readFrom method.
     */
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        MultivaluedMap<String, String> headers = context.getHeaders();
        List<String> contentDisposition = headers.get("Content-Disposition");
        if (contentDisposition != null) {
            Matcher contentDispositionMatcher = CONTENT_DISPOSITION_PATTERN.matcher(contentDisposition.get(0));
            if (contentDispositionMatcher.matches()) {
                String filename = contentDispositionMatcher.group("fileName");
                long size = Long.parseLong(contentDispositionMatcher.group("fileSize"));
                final InputStream is = context.getInputStream();
                context.setInputStream(new ProgressBarInputStream(filename, is, size));
            }
        }
        return context.proceed();
    }
    
    /**
     * Sets the output stream for uploads so that the progress of the upload can be monitored.
     *
     * @param context The invocation context.
     * @throws IOException             If MessageBodyWriter.writeTo encounters an exception writing to the output stream.
     * @throws WebApplicationException If there is an error in the MessageBodyWriter.writeTo method.
     */
    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        Object entity = context.getEntity();
        if (entity instanceof MultiPart) {
            List<BodyPart> bodyParts = ((MultiPart) context.getEntity()).getBodyParts();
            
            long size = 0;
            boolean upload = false;
            String filename = "";
            for (BodyPart bodyPart : bodyParts) {
                if (bodyPart.getContentDisposition().getFileName() != null) {
                    filename = bodyPart.getContentDisposition().getFileName();
                    size += bodyPart.getContentDisposition().getSize();
                    upload = true;
                } else {
                    size += bodyPart.getEntity().toString().length();
                }
            }
            
            if (upload) {
                final OutputStream os = context.getOutputStream();
                context.setOutputStream(new ProgressBarOutputStream(filename, os, size));
            }
        }
        context.proceed();
    }
    
}

