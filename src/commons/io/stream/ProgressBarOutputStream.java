/*
 * File:    ProgressBarOutputStream.java
 * Package: commons.io.stream
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.stream;

import java.io.IOException;
import java.io.OutputStream;

import commons.console.ProgressBar;
import org.apache.commons.io.output.CountingOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An OutputStream that displays a progress bar while the stream is transferring data if the total size is known.<br>
 * This OutputStream should only be used if the transfer operation of the OutputStream is blocking, prints nothing in between writes, , and is closed after the transfer is complete and before anything is printed.
 */
public class ProgressBarOutputStream extends CountingOutputStream {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProgressBarOutputStream.class);
    
    
    //Fields
    
    /**
     * The number of bytes that have been written so far.
     */
    private long progress = 0;
    
    /**
     * The progress bar for the output stream.
     */
    private final ProgressBar progressBar;
    
    
    //Constructors
    
    /**
     * Wraps an output stream and creates a progress bar for it.
     *
     * @param title The title to display for the progress bar.
     * @param out   The output stream to wrap.
     * @param size  The total size in bytes of the data being transferred by the output stream.
     * @see CountingOutputStream#CountingOutputStream(OutputStream)
     */
    public ProgressBarOutputStream(String title, OutputStream out, long size) {
        super(out);
        progressBar = new ProgressBar(title, size, "B");
    }
    
    /**
     * Wraps an output stream and creates a progress bar for it.
     *
     * @param out  The output stream to wrap.
     * @param size The total size in bytes of the data being transferred by the output stream.
     * @see #ProgressBarOutputStream(String, OutputStream, long)
     */
    public ProgressBarOutputStream(OutputStream out, long size) {
        this("", out, size);
    }
    
    
    //Methods
    
    /**
     * Wraps the write method of the output stream to monitor its progress.
     *
     * @param b   The data to write.
     * @param off The offset of the data.
     * @param len The number of bytes to write.
     * @throws IOException If an I/O error occurs.
     * @see ProgressBar#update(long)
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
        
        progress += len;
        progressBar.update(progress);
    }
    
    /**
     * Wraps the close method of the output stream to complete the progress bar.
     *
     * @throws IOException If an I/O error occurs.
     * @see ProgressBar#complete()
     */
    @Override
    public void close() throws IOException {
        progressBar.complete();
        super.close();
    }
    
}
