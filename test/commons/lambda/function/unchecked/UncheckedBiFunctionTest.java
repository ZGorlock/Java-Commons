/*
 * File:    UncheckedBiFunctionTest.java
 * Package: commons.lambda.function.unchecked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.unchecked;

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
 * JUnit test of UncheckedBiFunction.
 *
 * @see UncheckedBiFunction
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({UncheckedBiFunction.class})
public class UncheckedBiFunctionTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UncheckedBiFunctionTest.class);
    
    
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
     * JUnit test of tryApply.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedBiFunction#tryApply(Object, Object)
     */
    @Test
    public void testTryApply() throws Exception {
        final UncheckedBiFunction<Object, Object, Object> sut = (Object arg1, Object arg2) ->
                arg1.toString() + arg2;
        
        //standard
        TestUtils.assertNoException(() ->
                Assert.assertEquals("tested", sut.tryApply("test", "ed")));
        
        //exception
        TestUtils.assertException(() ->
                sut.tryApply(null, "ed"));
    }
    
    /**
     * JUnit test of apply.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedBiFunction#apply(Object, Object)
     */
    @Test
    public void testApply() throws Exception {
        final UncheckedBiFunction<Object, Object, Object> sut = (Object arg1, Object arg2) ->
                arg1.toString() + arg2;
        
        //standard
        TestUtils.assertNoException(() ->
                Assert.assertEquals("tested", sut.apply("test", "ed")));
        
        //exception
        TestUtils.assertException(RuntimeException.class, () ->
                sut.apply(null, "ed"));
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see UncheckedBiFunction#invoke(UncheckedBiFunction, Object, Object)
     */
    @Test
    public void testInvoke() throws Throwable {
        final UncheckedBiFunction<Object, Object, Object> sut = Mockito.mock(UncheckedBiFunction.class, Mockito.CALLS_REAL_METHODS);
        final Object arg1 = new Object();
        final Object arg2 = new Object();
        
        //standard
        TestUtils.assertNoException(() ->
                UncheckedBiFunction.invoke(sut, arg1, arg2));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .apply(ArgumentMatchers.eq(arg1), ArgumentMatchers.eq(arg2));
        
        //wrapped exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryApply(ArgumentMatchers.any(), ArgumentMatchers.any());
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedBiFunction.invoke(sut, arg1, arg2));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .apply(ArgumentMatchers.eq(arg1), ArgumentMatchers.eq(arg2));
        Mockito.doReturn(null).when(sut).tryApply(ArgumentMatchers.any(), ArgumentMatchers.any());
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).apply(ArgumentMatchers.any(), ArgumentMatchers.any());
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedBiFunction.invoke(sut, arg1, arg2));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .apply(ArgumentMatchers.eq(arg1), ArgumentMatchers.eq(arg2));
        Mockito.doReturn(null).when(sut).apply(ArgumentMatchers.any(), ArgumentMatchers.any());
    }
    
}
