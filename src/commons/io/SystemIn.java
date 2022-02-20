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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
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
     * The buffer for the scanner.
     */
    private static AtomicReference<String> buffer = null;
    
    /**
     * The thread that scans for input.
     */
    private static ExecutorService scannerThread = null;
    
    /**
     * The handle for the thread that scans for input.
     */
    private static Future<?> scannerThreadHandle = null;
    
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
    
    
    //Static Methods
    
    /**
     * Starts the scanner for System.in.
     */
    @SuppressWarnings("BusyWait")
    private static synchronized void startScanner() {
        interruptScanner();
        
        scannerThread = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("SystemIn").build());
        scannerThreadHandle = scannerThread.submit(() -> {
            try {
                while ((systemIn.available() == 0) || !in.hasNextLine()) {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    Thread.sleep(5);
                }
                buffer = new AtomicReference<>(in.nextLine());
            } catch (IOException | InterruptedException | IllegalStateException ignored) {
                buffer = new AtomicReference<>("");
            }
        });
    }
    
    /**
     * Interrupts the scanner for System.in.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    private static synchronized void interruptScanner() {
        if (isScannerRunning()) {
            scannerThreadHandle.cancel(true);
            scannerThread.shutdown();
            scannerThread.shutdownNow();
            while (!scannerThread.isShutdown()) {
            }
            scannerThread = null;
            scannerThreadHandle = null;
        }
        
        buffer = null;
    }
    
    /**
     * Determines if the scanner thread is running or not.
     *
     * @return Whether the scanner thread is running or not.
     */
    private static synchronized boolean isScannerRunning() {
        return ((scannerThread != null) && (scannerThreadHandle != null) &&
                !scannerThread.isShutdown() && !scannerThread.isTerminated() &&
                !scannerThreadHandle.isDone() && !scannerThreadHandle.isCancelled());
    }
    
    /**
     * Gets the next line of input from the scanner, blocks the calling thread until the next line is retrieved.
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
        while (isScannerRunning() && (buffer == null)) {
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
     * Gets a password input from the console, privately if there is a console, or not privately otherwise.
     *
     * @param caller The calling class.
     * @return The password input, or an empty string if the caller is not the owner of the Input Handler.
     * @see PasswordIn#readPassword(Class)
     */
    public static synchronized String getPassword(Class<?> caller) {
        if (!owns(caller)) {
            return "";
        }
        
        interruptScanner();
        return PasswordIn.hasConsole() ? PasswordIn.readPassword(caller) : nextLine(caller);
    }
    
    /**
     * Gets a password input from the console, privately if there is a console, or not privately otherwise.
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
            String line = buffer.getAndSet("");
            buffer = null;
            return line;
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
    
    
    //Inner Classes
    
    /**
     * Handles the reading of passwords from System.in.
     */
    protected static final class PasswordIn {
        
        //Static Fields
        
        /**
         * The console input object.
         */
        private static final Console console = System.console();
        
        
        //Static Methods
        
        /**
         * Reads a password from the console.
         *
         * @return The password.
         * @see Console#readPassword()
         */
        private static char[] doReadPassword() {
            return console.readPassword();
        }
        
        /**
         * Reads a password from the console.
         *
         * @param caller The calling class.
         * @return The password.
         * @see #doReadPassword()
         */
        public static String readPassword(Class<?> caller) {
            if (!owns(caller)) {
                return "";
            }
            
            return hasConsole() ? String.valueOf(doReadPassword()) : "";
        }
        
        /**
         * Reads a password from the console.
         *
         * @param caller The calling object.
         * @return The password.
         * @see #readPassword(Class)
         */
        public static String readPassword(Object caller) {
            return readPassword((caller != null) ? caller.getClass() : null);
        }
        
        /**
         * Determines if the system has a console or not.
         *
         * @return Whether the system has a console or not.
         */
        public static boolean hasConsole() {
            return (console != null);
        }
        
    }
    
}
