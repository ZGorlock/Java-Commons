/*
 * File:    SystemIn.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles control over System.in.
 */
public final class SystemIn extends SingletonInputHandler {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SystemIn.class);
    
    
    //Static Fields
    
    /**
     * The stream for the scanner.
     */
    private static final InputStream systemIn = System.in;
    
    /**
     * The scanner for the stream.
     */
    private static final Scanner in = new Scanner(systemIn);
    
    /**
     * The console input object.
     */
    private static final Console console = System.console();
    
    /**
     * The buffer for the scanner.
     */
    private static String buffer = null;
    
    /**
     * The thread that scans for input.
     */
    private static Thread scannerThread = null;
    
    //Initialize the interrupt action
    static {
        interrupt = SystemIn::interruptScanner;
    }
    
    
    //Constructors
    
    /**
     * The private constructor for SystemIn.
     */
    private SystemIn() {
    }
    
    
    //Functions
    
    /**
     * Starts the scanner for System.in.
     */
    @SuppressWarnings("BusyWait")
    private static synchronized void startScanner() {
        interruptScanner();
        scannerThread = new Thread(() -> {
            try {
                while (systemIn.available() == 0) {
                    Thread.sleep(5);
                }
                in.hasNextLine();
                buffer = in.nextLine();
            } catch (IOException | InterruptedException ignored) {
                buffer = "";
            }
        }, "SystemIn");
        scannerThread.start();
    }
    
    /**
     * Interrupts the scanner for System.in.
     */
    private static synchronized void interruptScanner() {
        if ((scannerThread != null) && scannerThread.isAlive()) {
            scannerThread.interrupt();
            try {
                scannerThread.join();
            } catch (InterruptedException ignored) {
            }
        }
        buffer = null;
    }
    
    /**
     * Gets the next line of input from the scanner.
     *
     * @param caller The calling class.
     * @return The next line of input.
     */
    @SuppressWarnings("BusyWait")
    public static synchronized String nextLine(Class<?> caller) {
        if (!caller.getCanonicalName().equals(owner)) {
            return "";
        }
        startScanner();
        while (buffer == null) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }
        }
        return getBuffer(caller);
    }
    
    /**
     * Gets the next line of input from the scanner.
     *
     * @param caller The calling object.
     * @return The next line of input.
     * @see #nextLine(Class)
     */
    public static synchronized String nextLine(Object caller) {
        return nextLine(caller.getClass());
    }
    
    /**
     * Gets a password input from the console.
     *
     * @param caller The calling class.
     * @return The password input.
     * @see Console#readPassword()
     */
    public static synchronized String getPassword(Class<?> caller) {
        if ((scannerThread != null) && scannerThread.isAlive()) {
            scannerThread.interrupt();
        }
        return (console != null) ? String.valueOf(console.readPassword()) : nextLine(caller);
    }
    
    /**
     * Gets a password input from the console.
     *
     * @param caller The calling object.
     * @return The password input.
     * @see #getPassword(Class)
     */
    public static synchronized String getPassword(Object caller) {
        return getPassword(caller.getClass());
    }
    
    /**
     * Returns the input buffer from the scanner.
     *
     * @param caller The calling class.
     * @return The input buffer.
     */
    public static synchronized String getBuffer(Class<?> caller) {
        if (!caller.getCanonicalName().equals(owner)) {
            return null;
        }
        if (buffer != null) {
            String line = buffer;
            buffer = null;
            if (owner.equals(defaultOwner) && !scannerThread.isAlive()) {
                startScanner();
            }
            return line;
        }
        if (owner.equals(defaultOwner) && ((scannerThread == null) || !scannerThread.isAlive())) {
            startScanner();
        }
        return null;
    }
    
    /**
     * Returns the input buffer from the scanner.
     *
     * @param caller The calling object.
     * @return The input buffer.
     * @see #getBuffer(Class)
     */
    public static synchronized String getBuffer(Object caller) {
        return getBuffer(caller.getClass());
    }
    
}
