/*
 * File:    VectorInterfaceTest.java
 * Package: commons.math.component.vector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.vector;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of VectorInterface.
 *
 * @see VectorInterface
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({VectorInterface.class})
public class VectorInterfaceTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(VectorInterfaceTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private RawVector sut;
    
    
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
     * JUnit test of vectorString.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#vectorString()
     */
    @Test
    public void testVectorString() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "vectorString");
        
        //standard
        
        sut = new RawVector(1.0);
        Assert.assertEquals("<1.0>", sut.vectorString());
        
        sut = new RawVector(1, 2);
        Assert.assertEquals("<1.0, 2.0>", sut.vectorString());
        
        sut = new RawVector(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("<1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0>", sut.vectorString());
        
        sut = new RawVector(8.15, 77.1654, 3, 3.66, -7.15, 890.1, 11, 7.9888, -0.79455);
        Assert.assertEquals("<8.15, 77.1654, 3.0, 3.66, -7.15, 890.1, 11.0, 7.9888, -0.79455>", sut.vectorString());
        
        //empty
        
        sut = new RawVector();
        Assert.assertEquals("<>", sut.vectorString());
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#dot(VectorInterface)
     */
    @Test
    public void testDot() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "dot", VectorInterface.class);
        
        //standard
        
        RawVector other;
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = new RawVector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(31.23598538692, sut.dot(other));
        
        sut = new RawVector(18.1644, 9.12154, -7.7741);
        other = new RawVector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(631.6597407041199, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = new RawVector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(210.2965501416, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = new RawVector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(-210.2965501416, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = new RawVector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(0.02102965501416, sut.dot(other));
        
        sut = new RawVector(1, -1, 3);
        other = new RawVector(3, 3, 0);
        Assert.assertEquals(0.0, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = RawVector.origin(3);
        Assert.assertEquals(0.0, sut.dot(other));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        other = RawVector.identity(3);
        Assert.assertEquals(25.06004, sut.dot(other));
        
        //invalid
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        final RawVector other1 = new RawVector(1.0481561, 1.655742, 0.974454, 1.5541);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dot(other1));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        final RawVector other2 = new RawVector(1.0481561, 1.655742);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dot(other2));
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dot(null));
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "normalize");
        
        //standard
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(
                new RawVector(0.562999747283, 0.629002096276, 0.536085485198), sut.normalize());
        
        sut = new RawVector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(
                new RawVector(-0.562999747283, -0.629002096276, -0.536085485198), sut.normalize());
        
        sut = new RawVector(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(
                new RawVector(0.834684394816, 0.419149935846, -0.357232826503), sut.normalize());
        
        sut = new RawVector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(
                new RawVector(0.152531277799, 0.907171337934, 0.392140756521), sut.normalize());
        
        sut = new RawVector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(
                new RawVector(0.478931075086, 0.756553624145, 0.445254577865), sut.normalize());
        
        sut = new RawVector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(
                new RawVector(0.562999747283, 0.629002096276, 0.536085485198), sut.normalize());
        
        sut = new RawVector(1, -1, 3);
        Assert.assertEquals(
                new RawVector(0.301511344578, -0.301511344578, 0.904534033733), sut.normalize());
        
        sut = RawVector.origin(3);
        Assert.assertEquals(
                new RawVector(0.0, 0.0, 0.0), sut.normalize());
        
        sut = RawVector.identity(3);
        Assert.assertEquals(
                new RawVector(0.57735026919, 0.57735026919, 0.57735026919), sut.normalize());
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "hypotenuse");
        
        //standard
        
        sut = new RawVector(8.1644, 9.12154, 7.7741);
        Assert.assertEquals(14.501605088458312, sut.hypotenuse());
        
        sut = new RawVector(-8.1644, -9.12154, -7.7741);
        Assert.assertEquals(14.501605088458312, sut.hypotenuse());
        
        sut = new RawVector(18.1644, 9.12154, -7.7741);
        Assert.assertEquals(21.761997843525304, sut.hypotenuse());
        
        sut = new RawVector(12.0481561, 71.655742, 30.974454);
        Assert.assertEquals(78.9881018102008, sut.hypotenuse());
        
        sut = new RawVector(1.0481561, 1.655742, 0.974454);
        Assert.assertEquals(2.188532242999223, sut.hypotenuse());
        
        sut = new RawVector(0.00081644, 0.000912154, 0.00077741);
        Assert.assertEquals(0.0014501605088458312, sut.hypotenuse());
        
        sut = new RawVector(1, -1, 3);
        Assert.assertEquals(3.3166247903554, sut.hypotenuse());
        
        sut = RawVector.origin(3);
        Assert.assertEquals(0.0, sut.hypotenuse());
        
        sut = RawVector.identity(3);
        Assert.assertEquals(1.7320508075688772, sut.hypotenuse());
    }
    
    /**
     * JUnit test of subVector.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#subVector(int, int)
     * @see VectorInterface#subVector(int)
     */
    @Test
    public void testSubVector() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "subVector", int.class, int.class);
        TestUtils.assertMethodExists(
                VectorInterface.class, "subVector", int.class);
        
        //standard
        
        RawVector subVector;
        sut = new RawVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        subVector = sut.subVector(0, 0);
        Assert.assertEquals(
                new RawVector(), subVector);
        
        subVector = sut.subVector(0, 1);
        Assert.assertEquals(
                new RawVector(1.0), subVector);
        
        subVector = sut.subVector(0, 2);
        Assert.assertEquals(
                new RawVector(1, 2), subVector);
        
        subVector = sut.subVector(2, 9);
        Assert.assertEquals(
                new RawVector(3, 4, 5, 6, 7, 8, 9), subVector);
        
        subVector = sut.subVector(8, 10);
        Assert.assertEquals(
                new RawVector(9, 10), subVector);
        
        subVector = sut.subVector(0, 10);
        Assert.assertEquals(
                new RawVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(5);
        Assert.assertEquals(
                new RawVector(6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(8);
        Assert.assertEquals(
                new RawVector(9, 10), subVector);
        
        subVector = sut.subVector(0);
        Assert.assertEquals(
                new RawVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        //not resizable
        
        Vector3 notResizable = new Vector3(1.0, 2.0, 3.0);
        Assert.assertFalse(notResizable.isResizeable());
        
        Vector subNotResizable = notResizable.subVector(0, 3);
        Assert.assertEquals(
                new Vector3(1.0, 2.0, 3.0), subNotResizable);
        
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(notResizable, Vector3.DIMENSIONALITY), () ->
                notResizable.subVector(0, 1));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotEqualErrorMessage(notResizable, Vector3.DIMENSIONALITY), () ->
                notResizable.subVector(1, 2));
        
        //invalid
        
        sut = new RawVector(10);
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 1, 0), () ->
                sut.subVector(1, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 3, 1), () ->
                sut.subVector(3, 1));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 9, 0), () ->
                sut.subVector(9, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 4, -2), () ->
                sut.subVector(4, -2));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 5, 11), () ->
                sut.subVector(5, 11));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 9, 21), () ->
                sut.subVector(9, 21));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 12, 16), () ->
                sut.subVector(12, 16));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 21, 33), () ->
                sut.subVector(21, 33));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, 15, 10), () ->
                sut.subVector(15));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, -3, 4), () ->
                sut.subVector(-3, 4));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentRangeOutOfBoundsErrorMessage(sut, -1, 1), () ->
                sut.subVector(-1, 1));
    }
    
    /**
     * JUnit test of getRawX.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#getRawX()
     */
    @Test
    public void testGetRawX() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "getRawX");
        
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(8.15, sut.getRawX());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(8.15000000000000000025, sut.getRawX());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getRawX());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawX()));
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#getX()
     */
    @Test
    public void testGetX() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "getX");
        
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(8.15, sut.getX());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(8.15, sut.getX());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getX());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getX()));
    }
    
    /**
     * JUnit test of getRawY.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#getRawY()
     */
    @Test
    public void testGetRawY() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "getRawY");
        
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(77.1654, sut.getRawY());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(77.16549999999999999999996, sut.getRawY());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getRawY());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawY()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawY()));
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#getY()
     */
    @Test
    public void testGetY() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "getY");
        
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(77.1654, sut.getY());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(77.1655, sut.getY());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getY());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getY()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getY()));
    }
    
    /**
     * JUnit test of getRawZ.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#getRawZ()
     */
    @Test
    public void testGetRawZ() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "getRawZ");
        
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(0.79455, sut.getRawZ());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(0.794550000000000000000001, sut.getRawZ());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getRawZ());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ()));
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawZ()));
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "getZ");
        
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(0.79455, sut.getZ());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(0.79455, sut.getZ());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getZ());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ()));
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getZ()));
    }
    
    /**
     * JUnit test of getRawW.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#getRawW()
     */
    @Test
    public void testGetRawW() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "getRawW");
        
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(3.3154, sut.getRawW());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(3.315499999999999999999999886, sut.getRawW());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getRawW());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW()));
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW()));
        
        sut = new RawVector(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getRawW()));
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#getW()
     */
    @Test
    public void testGetW() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "getW");
        
        //standard
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        Assert.assertEquals(3.3154, sut.getW());
        
        sut = new RawVector(8.15000000000000000025, 77.16549999999999999999996, 0.794550000000000000000001, 3.315499999999999999999999886);
        Assert.assertEquals(3.3155, sut.getW());
        
        sut = new RawVector(4);
        Assert.assertEquals(0.0, sut.getW());
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW()));
        
        sut = new RawVector(8.15);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW()));
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW()));
        
        sut = new RawVector(8.15, 77.1654, 0.79455);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0.0, sut.getW()));
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#setX(Number)
     */
    @Test
    public void testSetX() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "setX", Number.class);
        
        //standard
        
        sut = new RawVector(4);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {0.0, 0.0, 0.0, 0.0});
        sut.setX(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {5.555, 0.0, 0.0, 0.0});
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
        sut.setX(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {5.555, 77.1654, 0.79455, 3.3154});
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {});
        sut.setX(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {});
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
        TestUtils.assertNoException(() ->
                sut.setX(null));
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#setY(Number)
     */
    @Test
    public void testSetY() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "setY", Number.class);
        
        //standard
        
        sut = new RawVector(4);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {0.0, 0.0, 0.0, 0.0});
        sut.setY(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {0.0, 5.555, 0.0, 0.0});
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
        sut.setY(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 5.555, 0.79455, 3.3154});
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {});
        sut.setY(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {});
        
        sut = new RawVector(8.15);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15});
        sut.setY(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15});
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
        TestUtils.assertNoException(() ->
                sut.setY(null));
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#setZ(Number)
     */
    @Test
    public void testSetZ() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "setZ", Number.class);
        
        //standard
        
        sut = new RawVector(4);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {0.0, 0.0, 0.0, 0.0});
        sut.setZ(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {0.0, 0.0, 5.555, 0.0});
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
        sut.setZ(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 5.555, 3.3154});
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {});
        sut.setZ(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {});
        
        sut = new RawVector(8.15);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15});
        sut.setZ(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15});
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654});
        sut.setZ(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654});
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
        TestUtils.assertNoException(() ->
                sut.setZ(null));
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#setW(Number)
     */
    @Test
    public void testSetW() throws Exception {
        TestUtils.assertMethodExists(
                VectorInterface.class, "setW", Number.class);
        
        //standard
        
        sut = new RawVector(4);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {0.0, 0.0, 0.0, 0.0});
        sut.setW(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {0.0, 0.0, 0.0, 5.555});
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
        sut.setW(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 5.555});
        
        //invalid
        
        sut = new RawVector();
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {});
        sut.setW(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {});
        
        sut = new RawVector(8.15);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15});
        sut.setW(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15});
        
        sut = new RawVector(8.15, 77.1654);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654});
        sut.setW(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654});
        
        sut = new RawVector(8.15, 77.1654, 0.79455);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455});
        sut.setW(5.555);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455});
        
        sut = new RawVector(8.15, 77.1654, 0.79455, 3.3154);
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
        TestUtils.assertNoException(() ->
                sut.setW(null));
        TestUtils.assertArrayEquals(
                sut.getRawComponents(),
                new Number[] {8.15, 77.1654, 0.79455, 3.3154});
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#createInstance(int, Class)
     */
    @Test
    public void testCreateInstance() throws Exception {
        VectorInterface<?, ?> instance;
        
        //standard
        
        instance = VectorInterface.createInstance(3, RawVector.class);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance instanceof RawVector);
        Assert.assertEquals(3, instance.getDimensionality());
        
        instance = VectorInterface.createInstance(2, IntVector.class);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance instanceof IntVector);
        Assert.assertEquals(2, instance.getDimensionality());
        
        instance = VectorInterface.createInstance(2, BigVector.class);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance instanceof BigVector);
        Assert.assertEquals(2, instance.getDimensionality());
        
        instance = VectorInterface.createInstance(4, Vector.class);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance instanceof Vector);
        Assert.assertEquals(4, instance.getDimensionality());
        
        //invalid
        
        instance = VectorInterface.createInstance(-1, RawVector.class);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance instanceof RawVector);
        Assert.assertEquals(0, instance.getDimensionality());
        
        TestUtils.assertException(NullPointerException.class, () ->
                VectorInterface.createInstance(3, null));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#identity(int, Class)
     */
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testIdentity() throws Exception {
        //standard
        
        Assert.assertEquals(
                new RawVector(1, 1, 1), VectorInterface.identity(3, RawVector.class));
        Assert.assertEquals(
                new IntVector(1, 1), VectorInterface.identity(2, IntVector.class));
        Assert.assertEquals(
                new BigVector(1, 1), VectorInterface.identity(2, BigVector.class));
        Assert.assertEquals(
                new Vector(1, 1, 1, 1), VectorInterface.identity(4, Vector.class));
        
        Assert.assertNotEquals(
                new RawVector(1, 1, 1), VectorInterface.identity(3, IntVector.class));
        
        //invalid
        
        Assert.assertEquals(
                new RawVector(), VectorInterface.identity(-1, RawVector.class));
        
        TestUtils.assertException(NullPointerException.class, () ->
                VectorInterface.identity(3, null));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see VectorInterface#origin(int, Class)
     */
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testOrigin() throws Exception {
        //standard
        
        Assert.assertEquals(
                new RawVector(0.0, 0.0, 0.0), VectorInterface.origin(3, RawVector.class));
        Assert.assertEquals(
                new IntVector(0, 0), VectorInterface.origin(2, IntVector.class));
        Assert.assertEquals(
                new BigVector(0, 0), VectorInterface.origin(2, BigVector.class));
        Assert.assertEquals(
                new Vector(0.0, 0.0, 0.0, 0.0), VectorInterface.origin(4, Vector.class));
        
        Assert.assertNotEquals(
                new RawVector(0.0, 0.0, 0.0), VectorInterface.origin(3, IntVector.class));
        
        //invalid
        
        Assert.assertEquals(
                new RawVector(), VectorInterface.origin(-1, RawVector.class));
        
        TestUtils.assertException(NullPointerException.class, () ->
                VectorInterface.origin(3, null));
    }
    
}
