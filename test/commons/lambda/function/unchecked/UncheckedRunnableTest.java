/*
 * File:    UncheckedRunnableTest.java
 * Package: commons.lambda.function.unchecked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.unchecked;

import java.util.concurrent.atomic.AtomicReference;

import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of UncheckedRunnable.
 *
 * @see UncheckedRunnable
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({UncheckedRunnable.class})
public class UncheckedRunnableTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UncheckedRunnableTest.class);
    
    
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
     */
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of tryRun.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedRunnable#tryRun()
     */
    @Test
    public void testTryRun() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final UncheckedRunnable sut = () ->
                Assert.assertFalse(result.get().toString().isEmpty());
        
        //standard
        result.set(new StringBuilder("tested"));
        TestUtils.assertNoException(sut::tryRun);
        
        //exception
        result.set(null);
        TestUtils.assertException(sut::tryRun);
    }
    
    /**
     * JUnit test of run.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedRunnable#run()
     */
    @Test
    public void testRun() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final UncheckedRunnable sut = () ->
                Assert.assertFalse(result.get().toString().isEmpty());
        
        //standard
        result.set(new StringBuilder("tested"));
        TestUtils.assertNoException(sut::run);
        
        //exception
        result.set(null);
        TestUtils.assertException(RuntimeException.class,
                sut::run);
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see UncheckedRunnable#invoke(UncheckedRunnable)
     */
    @Test
    public void testInvoke() throws Throwable {
        final UncheckedRunnable sut = Mockito.mock(UncheckedRunnable.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                UncheckedRunnable.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .run();
        
        //wrapped exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryRun();
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedRunnable.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .run();
        Mockito.doNothing().when(sut).tryRun();
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).run();
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedRunnable.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .run();
        Mockito.doNothing().when(sut).run();
    }
    
}
