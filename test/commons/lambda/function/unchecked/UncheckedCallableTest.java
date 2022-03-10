/*
 * File:    UncheckedCallableTest.java
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
 * JUnit test of UncheckedCallable.
 *
 * @see UncheckedCallable
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({UncheckedCallable.class})
public class UncheckedCallableTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UncheckedCallableTest.class);
    
    
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
     * JUnit test of tryCall.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedCallable#tryCall()
     */
    @Test
    public void testTryCall() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final UncheckedCallable<Object> sut = () ->
                result.get().toString();
        
        //standard
        result.set(new StringBuilder("tested"));
        TestUtils.assertNoException(() ->
                Assert.assertEquals("tested", sut.tryCall()));
        
        //exception
        result.set(null);
        TestUtils.assertException(sut::tryCall);
    }
    
    /**
     * JUnit test of call.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedCallable#call()
     */
    @Test
    public void testCall() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final UncheckedCallable<Object> sut = () ->
                result.get().toString();
        
        //standard
        result.set(new StringBuilder("tested"));
        TestUtils.assertNoException(() ->
                Assert.assertEquals("tested", sut.call()));
        
        //exception
        result.set(null);
        TestUtils.assertException(RuntimeException.class,
                sut::call);
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see UncheckedCallable#invoke(UncheckedCallable)
     */
    @Test
    public void testInvoke() throws Throwable {
        final UncheckedCallable<Object> sut = Mockito.mock(UncheckedCallable.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                UncheckedCallable.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .call();
        
        //wrapped exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryCall();
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedCallable.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .call();
        Mockito.doReturn(null).when(sut).tryCall();
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).call();
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedCallable.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .call();
        Mockito.doReturn(null).when(sut).call();
    }
    
}
