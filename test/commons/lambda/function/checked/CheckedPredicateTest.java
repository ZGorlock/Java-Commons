/*
 * File:    CheckedPredicateTest.java
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
 * JUnit test of CheckedPredicate.
 *
 * @see CheckedPredicate
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CheckedPredicate.class})
public class CheckedPredicateTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CheckedPredicateTest.class);
    
    
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
     * JUnit test of tryTest
     *
     * @throws Exception When there is an exception.
     * @see CheckedPredicate#tryTest(Object)
     */
    @Test
    public void testTryTest() throws Exception {
        final CheckedPredicate<Object> sut = (Object arg) ->
                !arg.toString().isEmpty();
        
        //standard
        TestUtils.assertNoException(() ->
                Assert.assertTrue(sut.tryTest("test")));
        TestUtils.assertNoException(() ->
                Assert.assertFalse(sut.tryTest("")));
        
        //exception
        TestUtils.assertException(() ->
                sut.tryTest(null));
    }
    
    /**
     * JUnit test of test.
     *
     * @throws Exception When there is an exception.
     * @see CheckedPredicate#test(Object)
     */
    @Test
    public void testTest() throws Exception {
        final CheckedPredicate<Object> sut = (Object arg) ->
                !arg.toString().isEmpty();
        
        //standard
        TestUtils.assertNoException(() ->
                Assert.assertTrue(sut.test("test")));
        TestUtils.assertNoException(() ->
                Assert.assertFalse(sut.test("")));
        
        //exception
        TestUtils.assertNoException(() ->
                Assert.assertFalse(sut.test(null)));
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see CheckedPredicate#invoke(CheckedPredicate, Object)
     */
    @Test
    public void testInvoke() throws Throwable {
        final CheckedPredicate<Object> sut = Mockito.mock(CheckedPredicate.class, Mockito.CALLS_REAL_METHODS);
        final Object arg = new Object();
        
        //standard
        TestUtils.assertNoException(() ->
                CheckedPredicate.invoke(sut, arg));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .test(ArgumentMatchers.eq(arg));
        
        //wrapped exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryTest(ArgumentMatchers.any());
        TestUtils.assertNoException(() ->
                CheckedPredicate.invoke(sut, arg));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .test(ArgumentMatchers.eq(arg));
        Mockito.doReturn(false).when(sut).tryTest(ArgumentMatchers.any());
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).test(ArgumentMatchers.any());
        TestUtils.assertException(() ->
                CheckedPredicate.invoke(sut, arg));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .test(ArgumentMatchers.eq(arg));
        Mockito.doReturn(false).when(sut).test(ArgumentMatchers.any());
    }
    
}
