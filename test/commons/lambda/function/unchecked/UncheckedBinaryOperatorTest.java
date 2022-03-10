/*
 * File:    UncheckedBinaryOperatorTest.java
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
 * JUnit test of UncheckedBinaryOperator.
 *
 * @see UncheckedBinaryOperator
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({UncheckedBiFunction.class})
public class UncheckedBinaryOperatorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UncheckedBinaryOperatorTest.class);
    
    
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
     * @see UncheckedBinaryOperator#tryApply(Object, Object)
     */
    @Test
    public void testTryApply() throws Exception {
        final UncheckedBinaryOperator<Object> sut = (Object op1, Object op2) ->
                op1.toString() + op2;
        
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
     * @see UncheckedBinaryOperator#apply(Object, Object)
     */
    @Test
    public void testApply() throws Exception {
        final UncheckedBinaryOperator<Object> sut = (Object op1, Object op2) ->
                op1.toString() + op2;
        
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
     * @see UncheckedBinaryOperator#invoke(UncheckedBinaryOperator, Object, Object)
     */
    @Test
    public void testInvoke() throws Throwable {
        final UncheckedBinaryOperator<Object> sut = Mockito.mock(UncheckedBinaryOperator.class, Mockito.CALLS_REAL_METHODS);
        final Object op1 = new Object();
        final Object op2 = new Object();
        
        //standard
        TestUtils.assertNoException(() ->
                UncheckedBinaryOperator.invoke(sut, op1, op2));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .apply(ArgumentMatchers.eq(op1), ArgumentMatchers.eq(op2));
        
        //wrapped exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryApply(ArgumentMatchers.any(), ArgumentMatchers.any());
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedBinaryOperator.invoke(sut, op1, op2));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .apply(ArgumentMatchers.eq(op1), ArgumentMatchers.eq(op2));
        Mockito.doReturn(false).when(sut).tryApply(ArgumentMatchers.any(), ArgumentMatchers.any());
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).apply(ArgumentMatchers.any(), ArgumentMatchers.any());
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedBinaryOperator.invoke(sut, op1, op2));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .apply(ArgumentMatchers.eq(op1), ArgumentMatchers.eq(op2));
        Mockito.doReturn(false).when(sut).apply(ArgumentMatchers.any(), ArgumentMatchers.any());
    }
    
}
