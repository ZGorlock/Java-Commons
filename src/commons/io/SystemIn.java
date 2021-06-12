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
    
    /**
     * The singleton instance of the Input Handler.
     */
    private static SingletonInputHandler instance = new SystemIn();
    
    
    //Constructors
    
    /**
     * The private constructor for SystemIn.
     */
    private SystemIn() {
        interrupt = SystemIn::interruptScanner;
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
     * @return The next line of input, or an empty string if the caller is not the owner of the Input Handler.
     */
    @SuppressWarnings("BusyWait")
    public static synchronized String nextLine(Class<?> caller) {
        if (!owns(caller)) {
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
     * @return The next line of input, or an empty string if the caller is not the owner of the Input Handler.
     * @see #nextLine(Class)
     */
    public static synchronized String nextLine(Object caller) {
        return nextLine((caller != null) ? caller.getClass() : null);
    }
    
    /**
     * Gets a password input from the console.
     *
     * @param caller The calling class.
     * @return The password input, or an empty string if the caller is not the owner of the Input Handler.
     * @see Console#readPassword()
     */
    public static synchronized String getPassword(Class<?> caller) {
        if (!owns(caller)) {
            return "";
        }
        
        if ((scannerThread != null) && scannerThread.isAlive()) {
            scannerThread.interrupt();
        }
        return (console != null) ? String.valueOf(console.readPassword()) : nextLine(caller);
    }
    
    /**
     * Gets a password input from the console.
     *
     * @param caller The calling object.
     * @return The password input, or an empty string if the caller is not the owner of the Input Handler.
     * @see #getPassword(Class)
     */
    public static synchronized String getPassword(Object caller) {
        return getPassword((caller != null) ? caller.getClass() : null);
    }
    
    /**
     * Returns the input buffer from the scanner.
     *
     * @param caller The calling class.
     * @return The input buffer, or null if the caller is not the owner of the Input Handler.
     */
    public static synchronized String getBuffer(Class<?> caller) {
        if (!owns(caller)) {
            return null;
        }
        
        if (buffer != null) {
            String line = buffer;
            buffer = null;
            if (instance.owner.equals(instance.defaultOwner) && !scannerThread.isAlive()) {
                startScanner();
            }
            return line;
        }
        if (instance.owner.equals(instance.defaultOwner) && ((scannerThread == null) || !scannerThread.isAlive())) {
            startScanner();
        }
        return null;
    }
    
    /**
     * Returns the input buffer from the scanner.
     *
     * @param caller The calling object.
     * @return The input buffer, or null if the caller is not the owner of the Input Handler.
     * @see #getBuffer(Class)
     */
    public static synchronized String getBuffer(Object caller) {
        return getBuffer((caller != null) ? caller.getClass() : null);
    }
    
    /**
     * Determines if a specified class is the owner of the Input Handler.
     *
     * @param owner The calling class.
     * @return Whether the class is the owner of the Input Handler or not.
     * @see SingletonInputHandler#isOwner(Class)
     */
    public static synchronized boolean owns(Class<?> owner) {
        return instance.isOwner(owner);
    }
    
    /**
     * Determines if a specified class is the owner of the Input Handler.
     *
     * @param owner The calling object.
     * @return Whether the class is the owner of the Input Handler or not.
     * @see SingletonInputHandler#isOwner(Object)
     */
    public static synchronized boolean owns(Object owner) {
        return instance.isOwner(owner);
    }
    
    /**
     * Claims ownership of the Input Handler.
     *
     * @param owner The new owner of the Input Handler.
     * @return Whether the class acquired ownership of the Input Handler or not.
     * @see SingletonInputHandler#claimOwnership(Class)
     */
    public static synchronized boolean own(Class<?> owner) {
        return instance.claimOwnership(owner);
    }
    
    /**
     * Claims ownership of the Input Handler.
     *
     * @param owner The new owner of the Input Handler.
     * @return Whether the class acquired ownership of the Input Handler or not.
     * @see SingletonInputHandler#claimOwnership(Object)
     */
    public static synchronized boolean own(Object owner) {
        return instance.claimOwnership(owner);
    }
    
    /**
     * Claims the default ownership of the Input Handler.
     *
     * @param owner The default owner of the Input Handler.
     * @return Whether default ownership was successfully acquired or not.
     * @see SingletonInputHandler#claimDefaultOwnership(Class)
     */
    public static synchronized boolean defaultOwn(Class<?> owner) {
        return instance.claimDefaultOwnership(owner);
    }
    
    /**
     * Claims the default ownership of the Input Handler.
     *
     * @param owner The default owner of the Input Handler.
     * @return Whether default ownership was successfully acquired or not.
     * @see SingletonInputHandler#claimDefaultOwnership(Object)
     */
    public static synchronized boolean defaultOwn(Object owner) {
        return instance.claimDefaultOwnership(owner);
    }
    
    /**
     * Relinquishes the ownership of the Input Handler to the default owner.
     *
     * @param owner The calling class.
     * @return Whether the class relinquished ownership of the Input Handler or not.
     * @see SingletonInputHandler#relinquishOwnership(Class)
     */
    public static synchronized boolean relinquish(Class<?> owner) {
        return instance.relinquishOwnership(owner);
    }
    
    /**
     * Relinquishes the ownership of the Input Handler to the default owner.
     *
     * @param owner The calling object.
     * @return Whether the class relinquished ownership of the Input Handler or not.
     * @see SingletonInputHandler#relinquishOwnership(Object)
     */
    public static synchronized boolean relinquish(Object owner) {
        return instance.relinquishOwnership(owner);
    }
    
}
