/*
 * File:    UncheckedSupplierTest.java
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
 * JUnit test of UncheckedSupplier.
 *
 * @see UncheckedSupplier
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({UncheckedSupplier.class})
public class UncheckedSupplierTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UncheckedSupplierTest.class);
    
    
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
     * JUnit test of tryGet.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedSupplier#tryGet()
     */
    @Test
    public void testTryGet() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final UncheckedSupplier<Object> sut = () ->
                result.get().toString();
        
        //standard
        result.set(new StringBuilder("test"));
        TestUtils.assertNoException(() ->
                Assert.assertEquals("test", sut.tryGet()));
        
        //exception
        result.set(null);
        TestUtils.assertException(sut::tryGet);
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see UncheckedSupplier#get()
     */
    @Test
    public void testGet() throws Exception {
        final AtomicReference<Object> result = new AtomicReference<>(null);
        final UncheckedSupplier<Object> sut = () ->
                result.get().toString();
        
        //standard
        result.set(new StringBuilder("test"));
        TestUtils.assertNoException(() ->
                Assert.assertEquals("test", sut.get()));
        
        //exception
        result.set(null);
        TestUtils.assertException(RuntimeException.class,
                sut::get);
    }
    
    /**
     * JUnit test of invoke.
     *
     * @throws Throwable When there is an error.
     * @see UncheckedSupplier#invoke(UncheckedSupplier)
     */
    @Test
    public void testInvoke() throws Throwable {
        final UncheckedSupplier<Object> sut = Mockito.mock(UncheckedSupplier.class, Mockito.CALLS_REAL_METHODS);
        
        //standard
        TestUtils.assertNoException(() ->
                UncheckedSupplier.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .get();
        
        //wrapped exception
        Mockito.doThrow(new RuntimeException()).when(sut).tryGet();
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedSupplier.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(2))
                .get();
        Mockito.doReturn(null).when(sut).tryGet();
        
        //exception
        Mockito.doThrow(new RuntimeException()).when(sut).get();
        TestUtils.assertException(RuntimeException.class, () ->
                UncheckedSupplier.invoke(sut));
        Mockito.verify(sut, VerificationModeFactory.times(3))
                .get();
        Mockito.doReturn(null).when(sut).get();
    }
    
}