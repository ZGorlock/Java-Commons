/*
 * File:    CheckedBiConsumerTest.java
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
 * JUnit test of CheckedBiConsumer.
 *
 * @see CheckedBiConsumer
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CheckedBiConsumer.class})
public class CheckedBiConsumerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CheckedBiConsumerTest.class);
    
    
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
     * @see CheckedBiConsumer#tryAccept(Object, Object)
     */
    @Test
    public void testTryAccept() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final CheckedBiConsumer<Object, Object> sut = (Object in1, Object in2) ->
                result.set(in1.toString() + in2);
        
        //standard
        result.set("");
        TestUtils.assertNoException(() ->
                sut.tryAccept("test", "ed"));
        Assert.assertEquals("tested", result.get());
        
        //exception
        result.set("");
        TestUtils.assertException(() ->
                sut.tryAccept(null, "ed"));
        Assert.assertEquals("", result.get());
    }
    
    /**
     * JUnit test of accept.
     *
     * @throws Exception When there is an exception.
     * @see CheckedBiConsumer#accept(Object, Object)
     */
    @Test
    public void testAccept() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final CheckedBiConsumer<Object, Object> sut = (Object in1, Object in2) ->
                result.set(in1.toString() + in2);
        
        //standard
        result.set("");
        TestUtils.assertNoException(() ->
                sut.accept("test", "ed"));
        Assert.assertEquals("tested", result.get());
        
        //exception
        result.set("");
        TestUtils.assertNoException(() ->
                sut.accept(null, "ed"));
        Assert.assertEquals("", result.get());
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see CheckedBiConsumer#invoke(CheckedBiConsumer, Object, Object)
     */
    @Test
    public void testInvoke() throws Throwable {
        final CheckedBiConsumer<Object, Object> sut = Mockito.mock(CheckedBiConsumer.class, Mockito.CALLS_REAL_METHODS);
        final Object in1 = new Object();
        final Object in2 = new Object();
        
        //standard
        TestUtils.assertNoException(() ->
                CheckedBiConsumer.invoke(sut, in1, in2));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .accept(ArgumentMatchers.eq(in1), ArgumentMatchers.eq(in2));
        
        //wrapped exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryAccept(ArgumentMatchers.any(), ArgumentMatchers.any());
        TestUtils.assertNoException(() ->
                CheckedBiConsumer.invoke(sut, in1, in2));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .accept(ArgumentMatchers.eq(in1), ArgumentMatchers.eq(in2));
        Mockito.doNothing().when(sut).tryAccept(ArgumentMatchers.any(), ArgumentMatchers.any());
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).accept(ArgumentMatchers.any(), ArgumentMatchers.any());
        TestUtils.assertException(() ->
                CheckedBiConsumer.invoke(sut, in1, in2));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .accept(ArgumentMatchers.eq(in1), ArgumentMatchers.eq(in2));
        Mockito.doNothing().when(sut).accept(ArgumentMatchers.any(), ArgumentMatchers.any());
    }
    
}
