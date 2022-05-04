/*
 * File:    ActionTest.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

import java.util.concurrent.atomic.AtomicReference;

import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
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
 * JUnit test of Action.
 *
 * @see Action
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Action.class})
public class ActionTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ActionTest.class);
    
    
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
     * JUnit test of perform.
     *
     * @throws Exception When there is an exception.
     * @see Action#perform()
     */
    @Test
    public void testPerform() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final Action sut = () ->
                result.get().toString();
        
        //standard
        result.set(new StringBuilder("tested"));
        TestUtils.assertNoException(sut);
        
        //exception
        result.set(null);
        TestUtils.assertException(sut);
    }
    
    /**
     * JUnit test of performQuietly.
     *
     * @throws Exception When there is an exception.
     * @see Action#performQuietly()
     */
    @Test
    public void testPerformQuietly() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final Action sut = () ->
                result.get().toString();
        
        //standard
        result.set(new StringBuilder("tested"));
        TestUtils.assertNoException(sut::performQuietly);
        
        //exception
        result.set(null);
        TestUtils.assertNoException(sut::performQuietly);
    }
    
    /**
     * JUnit test of performOrFail.
     *
     * @throws Exception When there is an exception.
     * @see Action#performOrFail()
     */
    @Test
    public void testPerformOrFail() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final Action sut = () ->
                result.get().toString();
        
        //standard
        result.set(new StringBuilder("tested"));
        TestUtils.assertNoException(sut::performOrFail);
        
        //exception
        result.set(null);
        TestUtils.assertException(RuntimeException.class, sut::performOrFail);
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see Action#invoke(Action)
     */
    @Test
    public void testInvoke() throws Throwable {
        final Action sut = Mockito.mock(Action.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                Action.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .perform();
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).perform();
        TestUtils.assertException(() ->
                Action.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .perform();
        Mockito.doNothing().when(sut).perform();
    }
    
    /**
     * JUnit test of invokeQuietly.
     *
     * @throws Throwable When there is an error.
     * @see Action#invokeQuietly(Action)
     */
    @Test
    public void testInvokeQuietly() throws Throwable {
        final Action sut = Mockito.mock(Action.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                Action.invokeQuietly(sut));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .perform();
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).perform();
        TestUtils.assertNoException(() ->
                Action.invokeQuietly(sut));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .perform();
        Mockito.doNothing().when(sut).perform();
    }
    
    /**
     * JUnit test of invokeOrFail.
     *
     * @throws Throwable When there is an error.
     * @see Action#invokeOrFail(Action)
     */
    @Test
    public void testInvokeOrFail() throws Throwable {
        final Action sut = Mockito.mock(Action.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                Action.invokeOrFail(sut));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .perform();
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).perform();
        TestUtils.assertException(RuntimeException.class, () ->
                Action.invokeOrFail(sut));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .perform();
        Mockito.doNothing().when(sut).perform();
    }
    
}
