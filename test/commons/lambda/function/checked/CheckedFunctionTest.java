/*
 * File:    CheckedFunctionTest.java
 * Package: commons.lambda.function.checked
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function.checked;

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
 * JUnit test of CheckedFunction.
 *
 * @see CheckedFunction
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CheckedFunction.class})
public class CheckedFunctionTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CheckedFunctionTest.class);
    
    
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
     * @see CheckedFunction#tryApply(Object)
     */
    @Test
    public void testTryApply() throws Exception {
        final CheckedFunction<Object, Object> sut =
                Object::toString;
        
        //standard
        TestUtils.assertNoException(() ->
                Assert.assertEquals("test", sut.tryApply("test")));
        
        //exception
        TestUtils.assertException(() ->
                sut.tryApply(null));
    }
    
    /**
     * JUnit test of apply.
     *
     * @throws Exception When there is an exception.
     * @see CheckedFunction#apply(Object)
     */
    @Test
    public void testApply() throws Exception {
        final CheckedFunction<Object, Object> sut =
                Object::toString;
        
        //standard
        TestUtils.assertNoException(() ->
                Assert.assertEquals("test", sut.apply("test")));
        
        //exception
        TestUtils.assertNoException(() ->
                Assert.assertNull(sut.apply(null)));
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see CheckedFunction#invoke(CheckedFunction, Object)
     */
    @Test
    public void testInvoke() throws Throwable {
        final CheckedFunction<Object, Object> sut = Mockito.mock(CheckedFunction.class, Mockito.CALLS_REAL_METHODS);
        final Object arg = new Object();
        
        //standard
        TestUtils.assertNoException(() ->
                CheckedFunction.invoke(sut, arg));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .apply(ArgumentMatchers.eq(arg));
        
        //wrapped exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryApply(ArgumentMatchers.any());
        TestUtils.assertNoException(() ->
                CheckedFunction.invoke(sut, arg));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .apply(ArgumentMatchers.eq(arg));
        Mockito.doReturn(null).when(sut).tryApply(ArgumentMatchers.any());
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).apply(ArgumentMatchers.any());
        TestUtils.assertException(() ->
                CheckedFunction.invoke(sut, arg));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .apply(ArgumentMatchers.eq(arg));
        Mockito.doReturn(null).when(sut).apply(ArgumentMatchers.any());
    }
    
}
