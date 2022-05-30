/*
 * File:    SystemIn.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import commons.io.stream.BufferedLineReader;
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
     * The stream reader for the scanner.
     */
    private static final BufferedLineReader in = new BufferedLineReader(new InputStreamReader(systemIn));
    
    /**
     * The System console.
     */
    private static final Console console = System.console();
    
    /**
     * The buffer for the scanner.
     */
    private static AtomicReference<String> buffer = null;
    
    /**
     * The thread that handles scanning for input.
     */
    private static ExecutorService scanner = null;
    
    /**
     * The handle for the thread that handles scanning for input.
     */
    private static Future<?> scannerHandle = null;
    
    /**
     * The singleton instance of the Input Handler.
     */
    private static SingletonInputHandler instance = new SystemIn();
    
    /**
     * A flag indicating whether the Input Handler is currently in use.
     */
    private static AtomicBoolean inUse = new AtomicBoolean(false);
    
    
    //Constructors
    
    /**
     * The private constructor for SystemIn.
     *
     * @see SingletonInputHandler#SingletonInputHandler(Runnable)
     */
    private SystemIn() {
        super(SystemIn::interruptScanner);
    }
    
    
    //Static Methods
    
    /**
     * Starts the scanner for System.in.
     *
     * @return Whether the scanner was successfully started or not.
     */
    @SuppressWarnings("BusyWait")
    private static boolean startScanner() {
        if (!interruptScanner()) {
            return false;
        }
        
        scanner = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("SystemIn").build());
        scannerHandle = scanner.submit(() -> {
            try {
                while (!in.lineReady()) {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    Thread.sleep(5);
                }
                buffer = new AtomicReference<>(in.readLine());
            } catch (Exception ignored) {
                buffer = new AtomicReference<>(null);
            }
        });
        return true;
    }
    
    /**
     * Interrupts the scanner for System.in.
     *
     * @return Whether the scanner was successfully shutdown or not.
     */
    private static boolean interruptScanner() {
        if (isScannerRunning()) {
            try {
                scannerHandle.cancel(true);
                scanner.shutdown();
                scanner.shutdownNow();
                if (!scanner.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                    throw new InterruptedException();
                }
            } catch (Exception ignored) {
                return false;
            }
            
            scanner = null;
            scannerHandle = null;
        }
        
        buffer = null;
        return true;
    }
    
    /**
     * Determines if the scanner thread is running or not.
     *
     * @return Whether the scanner thread is running or not.
     */
    private static boolean isScannerRunning() {
        return !((scanner == null) || (scannerHandle == null) ||
                scanner.isShutdown() || scanner.isTerminated() ||
                scannerHandle.isDone() || scannerHandle.isCancelled());
    }
    
    /**
     * Gets the next line of input from the scanner.
     *
     * @return The next line of input, or null if the read was interrupted.
     */
    @SuppressWarnings("BusyWait")
    private static String nextLine() {
        while (isScannerRunning() && (buffer == null)) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {
            }
        }
        return Optional.ofNullable(buffer)
                .map(AtomicReference::get)
                .orElse(null);
    }
    
    /**
     * Gets the next line of input from the scanner.
     *
     * @param caller The caller.
     * @return The next line of input, or null if the caller is not the owner of the Input Handler or if the caller if already waiting on input or if the read was interrupted.
     * @see #nextLine()
     */
    public static String nextLine(Object caller) {
        if (!owns(caller) || !inUse.compareAndSet(false, true)) {
            return null;
        }
        
        try {
            startScanner();
            return nextLine();
            
        } finally {
            buffer = null;
            inUse.set(false);
        }
    }
    
    /**
     * Gets a password input from the console, privately if there is a console, or not privately otherwise.
     *
     * @return The password input, or null if the caller is not the owner of the Input Handler or if the caller if already waiting on input or if the read was interrupted.
     * @see Console#readPassword()
     */
    private static String readPassword() {
        return Optional.ofNullable(console)
                .map(e -> String.valueOf(e.readPassword()))
                .orElse(null);
    }
    
    /**
     * Gets a password input from the console, privately if there is a console, or not privately otherwise.
     *
     * @param caller The caller.
     * @return The password input, or null if the caller is not the owner of the Input Handler or if the caller if already waiting on input or if the read was interrupted.
     * @see #readPassword()
     * @see #nextLine()
     */
    public static String readPassword(Object caller) {
        if (!owns(caller) || !inUse.compareAndSet(false, true)) {
            return null;
        }
        
        try {
            return (console != null) ?
                   (interruptScanner() ? readPassword() : null) :
                   (startScanner() ? nextLine() : null);
            
        } finally {
            inUse.set(false);
        }
    }
    
    /**
     * Determines if a specified caller is the owner of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller is the owner of the Input Handler or not.
     * @see SingletonInputHandler#isOwner(Object)
     */
    public static boolean owns(Object caller) {
        return instance.isOwner(caller);
    }
    
    /**
     * Acquires ownership of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller acquired ownership of the Input Handler or not.
     * @see SingletonInputHandler#acquireOwnership(Object)
     */
    public static boolean own(Object caller) {
        return instance.acquireOwnership(caller);
    }
    
    /**
     * Relinquishes ownership of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller relinquished ownership of the Input Handler or not.
     * @see SingletonInputHandler#releaseOwnership(Object)
     */
    public static boolean relinquish(Object caller) {
        return instance.releaseOwnership(caller);
    }
    
    /**
     * Acquires management of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller acquired management of the Input Handler or not.
     * @see SingletonInputHandler#acquireManagement(Object)
     */
    public static boolean manage(Object caller) {
        return instance.acquireManagement(caller);
    }
    
    /**
     * Relinquishes management of the Input Handler.
     *
     * @param caller The caller.
     * @return Whether the caller relinquished management of the Input Handler or not.
     * @see SingletonInputHandler#releaseManagement(Object)
     */
    public static boolean relinquishManagement(Object caller) {
        return instance.releaseManagement(caller);
    }
    
}
