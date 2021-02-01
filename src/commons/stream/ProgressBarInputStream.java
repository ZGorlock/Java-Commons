/*
 * File:    ProgressBarInputStream.java
 * Package: commons.stream
 * Author:  Zachary Gill
 */

package commons.stream;

import java.io.IOException;
import java.io.InputStream;

import commons.console.ConsoleProgressBar;
import org.apache.commons.io.input.CountingInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An InputStream that displays a progress bar while the stream is transferring data if the total size is known.<br>
 * This InputStream should only be used if the transfer operation of the InputStream is blocking, prints nothing in between reads, and is closed after the transfer is complete and before anything is printed.
 */
public class ProgressBarInputStream extends CountingInputStream {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProgressBarInputStream.class);
    
    
    //Fields
    
    /**
     * The number of bytes that have been read so far.
     */
    private long progress = 0;
    
    /**
     * The progress bar for the input stream.
     */
    private ConsoleProgressBar progressBar;
    
    
    //Constructors
    
    /**
     * Wraps an input stream and creates a progress bar for it.
     *
     * @param title The title to display for the progress bar.
     * @param in    The input stream to wrap.
     * @param size  The total size in bytes of the data being transferred by the input stream.
     * @see CountingInputStream#CountingInputStream(InputStream)
     */
    public ProgressBarInputStream(String title, InputStream in, long size) {
        super(in);
        progressBar = new ConsoleProgressBar(title, size, "B");
    }
    
    /**
     * Wraps an input stream and creates a progress bar for it.
     *
     * @param in   The input stream to wrap.
     * @param size The total size in bytes of the data being transferred by the input stream.
     * @see #ProgressBarInputStream(String, InputStream, long)
     */
    public ProgressBarInputStream(InputStream in, long size) {
        this("", in, size);
    }
    
    
    //Methods
    
    /**
     * Wraps the read method of the input stream to monitor its progress.
     *
     * @param b   The buffer to read the data into.
     * @param off The offset in the buffer to start writing.
     * @param len The maximum number of bytes to read.
     * @return The number of bytes that were read.
     * @throws IOException If an I/O error occurs.
     * @see ConsoleProgressBar#update(long)
     */
    public int read(byte[] b, int off, int len) throws IOException {
        int read = super.read(b, off, len);
        
        progress += read;
        progressBar.update(progress);
        
        return read;
    }
    
    /**
     * Wraps the close method of the input stream to complete the progress bar.
     *
     * @throws IOException If an I/O error occurs.
     * @see ConsoleProgressBar#complete()
     */
    @Override
    public void close() throws IOException {
        progressBar.complete();
        super.close();
    }
    
}
