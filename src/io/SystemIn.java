/*
 * File:    SystemIn.java
 * Package: io
 * Author:  Zachary Gill
 */

package io;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles control over System.in.
 */
public final class SystemIn {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SystemIn.class);
    
    
    //Static Fields
    
    /**
     * The current owner of the scanner.
     */
    private static String owner = "";
    
    /**
     * The default owner of the scanner.
     */
    private static String defaultOwner = "";
    
    /**
     * The final stream for the scanner.
     */
    private static final InputStream systemIn = System.in;
    
    /**
     * The final scanner for the stream.
     */
    private static final Scanner in = new Scanner(systemIn);
    
    /**
     * The final console input object.
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
    
    /**
     * A flag indicating whether the scanner has been started or not.
     */
    private static final AtomicBoolean started = new AtomicBoolean(false);
    
    
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
     * @see #startScanner()
     * @see #getBuffer(Class)
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
     * @see #startScanner()
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
    
    /**
     * Returns the owner of the scanner.
     *
     * @return The owner of the scanner.
     */
    public static synchronized String getOwner() {
        return owner;
    }
    
    /**
     * Determines if an if a class is the owner of the scanner.
     *
     * @param owner The calling class.
     * @return Whether the class is the owner of the scanner or not.
     */
    public static synchronized boolean owns(Class<?> owner) {
        return SystemIn.owner.equals(owner.getCanonicalName());
    }
    
    /**
     * Determines if an if a class is the owner of the scanner.
     *
     * @param owner The calling object.
     * @return Whether the class is the owner of the scanner or not.
     * @see #owns(Class)
     */
    public static synchronized boolean owns(Object owner) {
        return owns(owner.getClass());
    }
    
    /**
     * Sets the owner of the scanner.
     *
     * @param owner The new owner of the scanner.
     * @return Whether the class acquired ownership of the scanner or not.
     * @see #interruptScanner()
     */
    public static synchronized boolean own(Class<?> owner) {
        if (SystemIn.owner.equals(defaultOwner) || owner.getCanonicalName().equals(defaultOwner)) {
            interruptScanner();
            SystemIn.owner = owner.getCanonicalName();
            return true;
        }
        return false;
    }
    
    /**
     * Sets the owner of the scanner.
     *
     * @param owner The new owner of the scanner.
     * @return Whether the class acquired ownership of the scanner or not.
     * @see #own(Class)
     */
    public static synchronized boolean own(Object owner) {
        return own(owner.getClass());
    }
    
    /**
     * Sets the default owner of the scanner.
     *
     * @param owner The default owner of the scanner.
     * @return Whether default ownership was successfully acquired or not.
     * @see #interruptScanner()
     */
    public static synchronized boolean defaultOwn(Class<?> owner) {
        if (defaultOwner.isEmpty()) {
            interruptScanner();
            defaultOwner = owner.getCanonicalName();
            return true;
        }
        return false;
    }
    
    /**
     * Sets the default owner of the scanner.
     *
     * @param owner The default owner of the scanner.
     * @return Whether default ownership was successfully acquired or not.
     * @see #defaultOwn(Class)
     */
    public static synchronized boolean defaultOwn(Object owner) {
        return defaultOwn(owner.getClass());
    }
    
    /**
     * Relinquishes the ownership of the scanner to the default owner.
     *
     * @see #interruptScanner()
     */
    public static synchronized void relinquish() {
        interruptScanner();
        owner = defaultOwner;
    }
    
}
