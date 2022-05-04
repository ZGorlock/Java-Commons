/*
 * File:    ConditionalTest.java
 * Package: commons.lambda.function
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.function;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

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
 * JUnit test of Conditional.
 *
 * @see Conditional
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Conditional.class})
public class ConditionalTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ConditionalTest.class);
    
    
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
     * JUnit test of test.
     *
     * @throws Exception When there is an exception.
     * @see Conditional#test()
     */
    @Test
    public void testTest() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final Conditional sut = () ->
                Boolean.valueOf(result.get().toString());
        
        
        //standard
        Stream.of(true, false).forEach(condition -> {
            result.set(new StringBuilder(condition.toString()));
            TestUtils.assertNoException(() ->
                    Assert.assertEquals(condition, sut.test()));
        });
        
        //exception
        result.set(null);
        TestUtils.assertException(sut::test);
    }
    
    /**
     * JUnit test of testOrDefault.
     *
     * @throws Exception When there is an exception.
     * @see Conditional#testOrDefault(Boolean)
     */
    @Test
    public void testTestOrDefault() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final Conditional sut = () ->
                Boolean.valueOf(result.get().toString());
        
        //standard
        Stream.of(true, false).forEach(condition -> {
            result.set(new StringBuilder(condition.toString()));
            TestUtils.assertNoException(() ->
                    Assert.assertEquals(condition, sut.testOrDefault(true)));
        });
        
        //exception
        result.set(null);
        TestUtils.assertNoException(() ->
                Assert.assertTrue(sut.testOrDefault(true)));
    }
    
    /**
     * JUnit test of testQuietly.
     *
     * @throws Exception When there is an exception.
     * @see Conditional#testQuietly()
     */
    @Test
    public void testTestQuietly() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final Conditional sut = () ->
                Boolean.valueOf(result.get().toString());
        
        //standard
        Stream.of(true, false).forEach(condition -> {
            result.set(new StringBuilder(condition.toString()));
            TestUtils.assertNoException(() ->
                    Assert.assertEquals(condition, sut.testQuietly()));
        });
        
        //exception
        result.set(null);
        TestUtils.assertNoException(() ->
                Assert.assertNull(sut.testQuietly()));
    }
    
    /**
     * JUnit test of testOrFail.
     *
     * @throws Exception When there is an exception.
     * @see Conditional#testOrFail()
     */
    @Test
    public void testTestOrFail() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final Conditional sut = () ->
                Boolean.valueOf(result.get().toString());
        
        //standard
        Stream.of(true, false).forEach(condition -> {
            result.set(new StringBuilder(condition.toString()));
            TestUtils.assertNoException(() ->
                    Assert.assertEquals(condition, sut.testOrFail()));
        });
        
        //exception
        result.set(null);
        TestUtils.assertException(RuntimeException.class, sut::testOrFail);
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see Conditional#invoke(Conditional)
     */
    @Test
    public void testInvoke() throws Throwable {
        final Conditional sut = Mockito.mock(Conditional.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                Conditional.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .test();
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).test();
        TestUtils.assertException(() ->
                Conditional.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .test();
        Mockito.doReturn(false).when(sut).test();
    }
    
    /**
     * JUnit test of invokeOrDefault.
     *
     * @throws Throwable When there is an error.
     * @see Conditional#invokeOrDefault(Conditional, Boolean)
     */
    @Test
    public void testInvokeOrDefault() throws Throwable {
        final Conditional sut = Mockito.mock(Conditional.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                Conditional.invokeOrDefault(sut, true));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .testOrDefault(ArgumentMatchers.eq(true));
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).test();
        TestUtils.assertNoException(() ->
                Assert.assertTrue(Conditional.invokeOrDefault(sut, true)));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .testOrDefault(ArgumentMatchers.eq(true));
        Mockito.doReturn(false).when(sut).test();
    }
    
    /**
     * JUnit test of invokeQuietly.
     *
     * @throws Throwable When there is an error.
     * @see Conditional#invokeQuietly(Conditional)
     */
    @Test
    public void testInvokeQuietly() throws Throwable {
        final Conditional sut = Mockito.mock(Conditional.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                Conditional.invokeQuietly(sut));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .testQuietly();
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).test();
        TestUtils.assertNoException(() ->
                Assert.assertNull(Conditional.invokeQuietly(sut)));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .testQuietly();
        Mockito.doReturn(false).when(sut).test();
    }
    
    /**
     * JUnit test of invokeOrFail.
     *
     * @throws Throwable When there is an error.
     * @see Conditional#invokeOrFail(Conditional)
     */
    @Test
    public void testInvokeOrFail() throws Throwable {
        final Conditional sut = Mockito.mock(Conditional.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                Conditional.invokeOrFail(sut));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .testOrFail();
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).test();
        TestUtils.assertException(RuntimeException.class, () ->
                Conditional.invokeOrFail(sut));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .testOrFail();
        Mockito.doReturn(false).when(sut).test();
    }
    
    /**
     * JUnit test of of.
     *
     * @throws Throwable When there is an error.
     * @see Conditional#of(Boolean)
     */
    @Test
    public void testOf() throws Throwable {
        Conditional conditional;
        
        //standard
        conditional = Conditional.of(true);
        Assert.assertNotNull(conditional);
        Assert.assertTrue(conditional.test());
        Assert.assertTrue(conditional.testOrDefault(false));
        Assert.assertTrue(conditional.testQuietly());
        Assert.assertTrue(conditional.testOrFail());
    }
    
}
