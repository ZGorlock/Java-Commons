/*
 * File:    BufferedLineReader.java
 * Package: commons.io.stream
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A BufferedReader that allows checking whether a line is ready to be read or not without blocking.
 */
public class BufferedLineReader extends BufferedReader {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BufferedLineReader.class);
    
    
    //Constants
    
    /**
     * The character buffer size.
     */
    public static final int BUFFER_SIZE = 32768;
    
    
    //Constructors
    
    /**
     * Constructor for a BufferedLineReader.
     *
     * @param in The reader.
     * @see BufferedReader#BufferedReader(Reader, int)
     */
    public BufferedLineReader(Reader in) {
        super(in, BUFFER_SIZE);
    }
    
    
    //Methods
    
    /**
     * Determines if a new line of input is ready to be read from the buffer.
     *
     * @return Whether a new line of input is ready to be read from the buffer or not.
     * @throws IOException When there is an error looking ahead in the buffer.
     */
    public boolean lineReady() throws IOException {
        try {
            super.mark(BUFFER_SIZE);
            int read;
            while (super.ready() && ((read = super.read()) != -1)) {
                if ((read == '\n') || (read == '\r')) {
                    return true;
                }
            }
            return false;
        } finally {
            super.reset();
        }
    }
    
}
