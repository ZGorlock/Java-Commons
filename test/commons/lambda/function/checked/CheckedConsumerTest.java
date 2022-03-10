/*
 * File:    CheckedConsumerTest.java
 * Package: commons.lambda.function.checked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.checked;

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
 * JUnit test of CheckedConsumer.
 *
 * @see CheckedConsumer
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CheckedConsumer.class})
public class CheckedConsumerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CheckedConsumerTest.class);
    
    
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
     * @see CheckedConsumer#tryAccept(Object)
     */
    @Test
    public void testTryAccept() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final CheckedConsumer<Object> sut = (Object in) ->
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
     * @see CheckedConsumer#accept(Object)
     */
    @Test
    public void testAccept() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final CheckedConsumer<Object> sut = (Object in) ->
                result.set(in.toString());
        
        //standard
        result.set("");
        TestUtils.assertNoException(() ->
                sut.accept("test"));
        Assert.assertEquals("test", result.get());
        
        //exception
        result.set("");
        TestUtils.assertNoException(() ->
                sut.accept(null));
        Assert.assertEquals("", result.get());
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see CheckedConsumer#invoke(CheckedConsumer, Object)
     */
    @Test
    public void testInvoke() throws Throwable {
        final CheckedConsumer<Object> sut = Mockito.mock(CheckedConsumer.class, Mockito.CALLS_REAL_METHODS);
        final Object in = new Object();
        
        //standard
        TestUtils.assertNoException(() ->
                CheckedConsumer.invoke(sut, in));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .accept(ArgumentMatchers.eq(in));
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryAccept(ArgumentMatchers.any());
        TestUtils.assertNoException(() ->
                CheckedConsumer.invoke(sut, in));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .accept(ArgumentMatchers.eq(in));
        Mockito.doNothing().when(sut).tryAccept(ArgumentMatchers.any());
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).accept(ArgumentMatchers.any());
        TestUtils.assertException(() ->
                CheckedConsumer.invoke(sut, in));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .accept(ArgumentMatchers.eq(in));
        Mockito.doNothing().when(sut).accept(ArgumentMatchers.any());
    }
    
}
