/*
 * File:    UncheckedConsumerTest.java
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
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of UncheckedConsumer.
 *
 * @see UncheckedConsumer
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({UncheckedConsumer.class})
public class UncheckedConsumerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UncheckedConsumerTest.class);
    
    
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
     * JUnit test of tryAccept.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedConsumer#tryAccept(Object)
     */
    @Test
    public void testTryAccept() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final UncheckedConsumer<Object> sut = (Object in) ->
                result.set(in.toString());
        
        //standard
        result.set("");
        TestUtils.assertNoException(() ->
                sut.tryAccept("test"));
        Assert.assertEquals("test", result.get());
        
        //exception
        result.set("");
        TestUtils.assertException(() ->
                sut.tryAccept(null));
        Assert.assertEquals("", result.get());
    }
    
    /**
     * JUnit test of accept.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedConsumer#accept(Object)
     */
    @Test
    public void testAccept() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final UncheckedConsumer<Object> sut = (Object in) ->
                result.set(in.toString());
        
        //standard
        result.set("");
        TestUtils.assertNoException(() ->
                sut.accept("test"));
        Assert.assertEquals("test", result.get());
        
        //exception
        result.set("");
        TestUtils.assertException(RuntimeException.class, () ->
                sut.accept(null));
        Assert.assertEquals("", result.get());
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see UncheckedConsumer#invoke(UncheckedConsumer, Object)
     */
    @Test
    public void testInvoke() throws Throwable {
        final UncheckedConsumer<Object> sut = Mockito.mock(UncheckedConsumer.class, Mockito.CALLS_REAL_METHODS);
        final Object in = new Object();
        
        //standard
        TestUtils.assertNoException(() ->
                UncheckedConsumer.invoke(sut, in));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .accept(ArgumentMatchers.eq(in));
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryAccept(ArgumentMatchers.any());
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedConsumer.invoke(sut, in));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .accept(ArgumentMatchers.eq(in));
        Mockito.doNothing().when(sut).tryAccept(ArgumentMatchers.any());
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).accept(ArgumentMatchers.any());
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedConsumer.invoke(sut, in));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .accept(ArgumentMatchers.eq(in));
        Mockito.doNothing().when(sut).accept(ArgumentMatchers.any());
    }
    
}
