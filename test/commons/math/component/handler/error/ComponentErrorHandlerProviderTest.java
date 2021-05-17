/*
 * File:    ComponentErrorHandlerProviderTest.java
 * Package: commons.math.component.handler.error
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.error;

import commons.math.component.ComponentInterface;
import commons.math.component.matrix.BigMatrix;
import commons.math.component.matrix.MatrixInterface;
import commons.math.component.matrix.RawMatrix;
import commons.math.component.vector.RawVector;
import commons.math.component.vector.Vector;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ComponentErrorHandlerProvider.
 *
 * @see ComponentErrorHandlerProvider
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ComponentErrorHandlerProvider.class})
public class ComponentErrorHandlerProviderTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ComponentErrorHandlerProviderTest.class);
    
    
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
     * @see ComponentErrorHandlerProvider#errorHandler
     */
    @Test
    public void testConstants() throws Exception {
        //static
        Object errorHandler = Whitebox.getInternalState(ComponentErrorHandlerProvider.class, "errorHandler");
        Assert.assertNotNull(errorHandler);
        Assert.assertTrue(errorHandler instanceof ComponentErrorHandlerInterface);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = Whitebox.getInternalState(ComponentErrorHandlerProvider.class, "errorHandler");
        Assert.assertEquals(errorHandler, ComponentErrorHandlerProvider.getErrorHandler());
        ComponentErrorHandlerInterface newErrorHandler = new ComponentErrorHandler();
        Whitebox.setInternalState(ComponentErrorHandlerProvider.class, "errorHandler", newErrorHandler);
        Assert.assertEquals(newErrorHandler, ComponentErrorHandlerProvider.getErrorHandler());
    }
    
    /**
     * JUnit test of setErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#setErrorHandler(ComponentErrorHandlerInterface)
     */
    @Test
    public void testSetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface newErrorHandler = new ComponentErrorHandler();
        ComponentErrorHandlerProvider.setErrorHandler(newErrorHandler);
        ComponentErrorHandlerInterface errorHandler = Whitebox.getInternalState(ComponentErrorHandlerProvider.class, "errorHandler");
        Assert.assertEquals(newErrorHandler, errorHandler);
    }
    
    /**
     * JUnit test of assertDimensionalitySame.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#assertDimensionalitySame(ComponentInterface, ComponentInterface)
     */
    @Test
    public void testAssertDimensionalitySame() throws Exception {
        RawVector rv2 = new RawVector(19.1, 543.99);
        RawVector rv3 = new RawVector(1.168, 9.106, 5.21);
        RawMatrix rm2 = new RawMatrix(1.16, 2.78, 4.19, 3.98);
        RawMatrix rm3 = new RawMatrix(8.16, 4.66, 1.9, 0.67, 4.3, 1.0, 7.447, 6.5, 7.0);
        Vector v2 = new Vector(8.16, 11.64);
        Vector v3 = new Vector(4.19, 6.778, 10.96);
        BigMatrix bm2 = new BigMatrix(7.01, 9.48, 44.2, 6.3);
        BigMatrix bm3 = new BigMatrix(0.7, 6.41, 6.51, 7.0, 3.3, 5.9, 4.153, 1.16, 2.46);
        
        //standard
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(rv2, rm2));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(rm2, v2));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(v2, bm2));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(bm2, rv2));
        
        //invalid
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotSameErrorMessage(rv2, rv3), () ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(rv2, rv3));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotSameErrorMessage(rm2, rm3), () ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(rm2, rm3));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotSameErrorMessage(v2, v3), () ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(v2, v3));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotSameErrorMessage(bm2, bm3), () ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(bm2, bm3));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotSameErrorMessage(rv2, rm3), () ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(rv2, rm3));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotSameErrorMessage(v2, bm3), () ->
                ComponentErrorHandlerProvider.assertDimensionalitySame(v2, bm3));
    }
    
    /**
     * JUnit test of assertDimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#assertDimensionalityEqual(ComponentInterface, int)
     */
    @Test
    public void testAssertDimensionalityEqual() throws Exception {
        RawVector rv2 = new RawVector(19.1, 543.99);
        RawVector rv3 = new RawVector(1.168, 9.106, 5.21);
        RawMatrix rm2 = new RawMatrix(1.16, 2.78, 4.19, 3.98);
        RawMatrix rm3 = new RawMatrix(8.16, 4.66, 1.9, 0.67, 4.3, 1.0, 7.447, 6.5, 7.0);
        Vector v2 = new Vector(8.16, 11.64);
        Vector v3 = new Vector(4.19, 6.778, 10.96);
        BigMatrix bm2 = new BigMatrix(7.01, 9.48, 44.2, 6.3);
        BigMatrix bm3 = new BigMatrix(0.7, 6.41, 6.51, 7.0, 3.3, 5.9, 4.153, 1.16, 2.46);
        
        //standard
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(rv2, 2));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(rm2, 2));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(v2, 2));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(bm2, 2));
        
        //invalid
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotEqualErrorMessage(rv2, 3), () ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(rv2, 3));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotEqualErrorMessage(rm2, 3), () ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(rm2, 3));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotEqualErrorMessage(v2, 3), () ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(v2, 3));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotEqualErrorMessage(bm2, 3), () ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(bm2, 3));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotEqualErrorMessage(rm3, 2), () ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(rm3, 2));
        TestUtils.assertException(ArithmeticException.class, ComponentErrorHandlerProvider.getErrorHandler().dimensionalityNotEqualErrorMessage(v3, 2), () ->
                ComponentErrorHandlerProvider.assertDimensionalityEqual(v3, 2));
    }
    
    /**
     * JUnit test of assertIndexInBounds.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#assertIndexInBounds(ComponentInterface, int)
     */
    @Test
    public void testAssertIndexInBounds() throws Exception {
        RawVector rv2 = new RawVector(19.1, 543.99);
        RawVector rv3 = new RawVector(1.168, 9.106, 5.21);
        RawMatrix rm2 = new RawMatrix(1.16, 2.78, 4.19, 3.98);
        RawMatrix rm3 = new RawMatrix(8.16, 4.66, 1.9, 0.67, 4.3, 1.0, 7.447, 6.5, 7.0);
        Vector v2 = new Vector(8.16, 11.64);
        Vector v3 = new Vector(4.19, 6.778, 10.96);
        BigMatrix bm2 = new BigMatrix(7.01, 9.48, 44.2, 6.3);
        BigMatrix bm3 = new BigMatrix(0.7, 6.41, 6.51, 7.0, 3.3, 5.9, 4.153, 1.16, 2.46);
        
        //standard
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertIndexInBounds(rv2, 1));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertIndexInBounds(rv3, 0));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertIndexInBounds(rv3, 2));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertIndexInBounds(v2, 0));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertIndexInBounds(rm2, 3));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertIndexInBounds(bm3, 3));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertIndexInBounds(bm3, 0));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertIndexInBounds(bm3, 8));
        
        //invalid
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentIndexOutOfBoundsErrorMessage(rv2, 3), () ->
                ComponentErrorHandlerProvider.assertIndexInBounds(rv2, 3));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentIndexOutOfBoundsErrorMessage(rm2, -1), () ->
                ComponentErrorHandlerProvider.assertIndexInBounds(rm2, -1));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentIndexOutOfBoundsErrorMessage(v2, -1), () ->
                ComponentErrorHandlerProvider.assertIndexInBounds(v2, -1));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentIndexOutOfBoundsErrorMessage(bm2, 4), () ->
                ComponentErrorHandlerProvider.assertIndexInBounds(bm2, 4));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentIndexOutOfBoundsErrorMessage(rm3, 10), () ->
                ComponentErrorHandlerProvider.assertIndexInBounds(rm3, 10));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentIndexOutOfBoundsErrorMessage(v3, 4), () ->
                ComponentErrorHandlerProvider.assertIndexInBounds(v3, 4));
    }
    
    /**
     * JUnit test of assertCoordinateInBounds.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#assertCoordinateInBounds(MatrixInterface, int, int)
     */
    @Test
    public void testAssertCoordinateInBounds() throws Exception {
        RawMatrix rm2 = new RawMatrix(1.16, 2.78, 4.19, 3.98);
        RawMatrix rm3 = new RawMatrix(8.16, 4.66, 1.9, 0.67, 4.3, 1.0, 7.447, 6.5, 7.0);
        BigMatrix bm2 = new BigMatrix(7.01, 9.48, 44.2, 6.3);
        BigMatrix bm3 = new BigMatrix(0.7, 6.41, 6.51, 7.0, 3.3, 5.9, 4.153, 1.16, 2.46);
        
        //standard
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(rm2, 0, 0));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(rm2, 1, 1));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(rm3, 2, 2));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(bm2, 1, 1));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(bm2, 1, 0));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(bm3, 1, 2));
        TestUtils.assertNoException(() ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(bm3, 2, 1));
        
        //invalid
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(rm2, 0, 2), () ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(rm2, 0, 2));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(rm2, 2, 1), () ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(rm2, 2, 1));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(rm3, 3, 1), () ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(rm3, 3, 1));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(rm3, 1, 3), () ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(rm3, 1, 3));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(bm2, -1, 0), () ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(bm2, -1, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(bm2, 0, -3), () ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(bm2, 0, -3));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(bm3, 6, 9), () ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(bm3, 6, 9));
        TestUtils.assertException(IndexOutOfBoundsException.class, ComponentErrorHandlerProvider.getErrorHandler().componentCoordinateOutOfBoundsErrorMessage(bm3, -2, -4), () ->
                ComponentErrorHandlerProvider.assertCoordinateInBounds(bm3, -2, -4));
    }
    
}
