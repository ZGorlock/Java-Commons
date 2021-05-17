/*
 * File:    ComponentErrorHandlerTest.java
 * Package: commons.math.component.handler.error
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.error;

import java.math.BigDecimal;

import commons.math.component.ComponentInterface;
import commons.math.component.matrix.BigMatrix;
import commons.math.component.matrix.IntMatrix;
import commons.math.component.matrix.Matrix;
import commons.math.component.matrix.Matrix2;
import commons.math.component.matrix.Matrix3;
import commons.math.component.matrix.RawMatrix;
import commons.math.component.vector.BigVector;
import commons.math.component.vector.IntVector;
import commons.math.component.vector.RawVector;
import commons.math.component.vector.Vector;
import commons.math.component.vector.Vector3;
import commons.math.component.vector.Vector4;
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
 * JUnit test of ComponentErrorHandler.
 *
 * @see ComponentErrorHandler
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ComponentErrorHandler.class})
public class ComponentErrorHandlerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ComponentErrorHandlerTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private ComponentErrorHandler sut;
    
    
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
    @Before
    public void setup() throws Exception {
        sut = (ComponentErrorHandler) ComponentErrorHandlerProvider.getErrorHandler();
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
     * JUnit test of dimensionalityNotSameErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#dimensionalityNotSameErrorMessage(ComponentInterface, ComponentInterface)
     */
    @Test
    public void testDimensionalityNotSameErrorMessage() throws Exception {
        Assert.assertEquals(
                "The Vector: <8.0, 9.0, -4.0, 11.0> and the Vector: <4.0, -6.0, 11.0> do not have the same dimensionality",
                sut.dimensionalityNotSameErrorMessage(new Vector(8, 9, -4, 11), new Vector(4, -6, 11)));
        
        Assert.assertEquals(
                "The Matrix: [<8.0, 9.0>, <-4.0, 11.0>] and the Matrix: [<4.0, -6.0, 11.0>, <8.11, 6.0, 1.0>, <0.9, 4.0, -3.0>] do not have the same dimensionality",
                sut.dimensionalityNotSameErrorMessage(new Matrix(8, 9, -4, 11), new Matrix(4, -6, 11, 8.11, 6, 1, 0.9, 4, -3)));
        
        Assert.assertEquals(
                "The 3D Vector: <8.0, 9.0, -4.0> and the 4D Vector: <4.0, -6.0, 11.0, 9.0> do not have the same dimensionality",
                sut.dimensionalityNotSameErrorMessage(new Vector3(8, 9, -4), new Vector4(4, -6, 11, 9)));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] and the 3D Matrix: [<4.0, -6.0, 11.0>, <8.11, 6.0, 1.0>, <0.9, 4.0, -3.0>] do not have the same dimensionality",
                sut.dimensionalityNotSameErrorMessage(new Matrix2(8, 9, -4, 11), new Matrix3(4, -6, 11, 8.11, 6, 1, 0.9, 4, -3)));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] and the 3D Vector: <4.0, 11.0, 7.0> do not have the same dimensionality",
                sut.dimensionalityNotSameErrorMessage(new Matrix2(8, 9, -4, 11), new Vector3(4, 11, 7)));
        
        Assert.assertEquals(
                "The Raw Vector: <8.0, 9.0, -4.0, 11.0> and the Raw Matrix: [<4.0>] do not have the same dimensionality",
                sut.dimensionalityNotSameErrorMessage(new RawVector(8, 9, -4, 11), new RawMatrix(4.0)));
    }
    
    /**
     * JUnit test of dimensionalityNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#dimensionalityNotEqualErrorMessage(ComponentInterface, int)
     */
    @Test
    public void testDimensionalityNotEqualErrorMessage() throws Exception {
        Assert.assertEquals(
                "The Vector: <8.0, 9.0, -4.0, 11.0> does not have the expected dimensionality of: 5",
                sut.dimensionalityNotEqualErrorMessage(new Vector(8, 9, -4, 11), 5));
        
        Assert.assertEquals(
                "The Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have the expected dimensionality of: 3",
                sut.dimensionalityNotEqualErrorMessage(new Matrix(8, 9, -4, 11), 3));
        
        Assert.assertEquals(
                "The 3D Vector: <8.0, 9.0, -4.0> does not have the expected dimensionality of: 1",
                sut.dimensionalityNotEqualErrorMessage(new Vector3(8, 9, -4), 1));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have the expected dimensionality of: 3",
                sut.dimensionalityNotEqualErrorMessage(new Matrix2(8, 9, -4, 11), 3));
        
        Assert.assertEquals(
                "The Integer Matrix: [<8, 9>, <-4, 11>] does not have the expected dimensionality of: 2",
                sut.dimensionalityNotEqualErrorMessage(new IntMatrix(8, 9, -4, 11), 2));
        
        Assert.assertEquals(
                "The Raw Vector: <8.0, 9.0, -4.0, 11.0> does not have the expected dimensionality of: 6",
                sut.dimensionalityNotEqualErrorMessage(new RawVector(8, 9, -4, 11), 6));
    }
    
    /**
     * JUnit test of componentTypeNotSameErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentTypeNotSameErrorMessage(ComponentInterface, ComponentInterface)
     */
    @Test
    public void testComponentTypeNotSameErrorMessage() throws Exception {
        Assert.assertEquals(
                "The Vector: <8.0, 9.0, -4.0, 11.0> and the Big Vector: <4, -6, 11> do not have the same component type",
                sut.componentTypeNotSameErrorMessage(new Vector(8, 9, -4, 11), new BigVector(4, -6, 11)));
        
        Assert.assertEquals(
                "The Matrix: [<8.0, 9.0>, <-4.0, 11.0>] and the Integer Matrix: [<4, -6, 11>, <8, 6, 1>, <0, 4, -3>] do not have the same component type",
                sut.componentTypeNotSameErrorMessage(new Matrix(8, 9, -4, 11), new IntMatrix(4, -6, 11, 8, 6, 1, 0, 4, -3)));
        
        Assert.assertEquals(
                "The 3D Vector: <8.0, 9.0, -4.0> and the Raw Matrix: [<4.0, -6.0>, <11.0, 9.0>] do not have the same component type",
                sut.componentTypeNotSameErrorMessage(new Vector3(8, 9, -4), new RawMatrix(4, -6, 11, 9)));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] and the Raw Vector: <4.0, -6.0, 11.0, 8.11, 6.0, 1.0, 0.9, 4.0, -3.0> do not have the same component type",
                sut.componentTypeNotSameErrorMessage(new Matrix2(8, 9, -4, 11), new RawVector(4, -6, 11, 8.11, 6, 1, 0.9, 4, -3)));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] and the Integer Vector: <4, 11, 7> do not have the same component type",
                sut.componentTypeNotSameErrorMessage(new Matrix2(8, 9, -4, 11), new IntVector(4, 11, 7)));
        
        Assert.assertEquals(
                "The Raw Vector: <8.0, 9.0, -4.0, 11.0> and the 3D Vector: <4.0, 6.1, 4.3> do not have the same component type",
                sut.componentTypeNotSameErrorMessage(new RawVector(8, 9, -4, 11), new Vector3(4.0, 6.1, 4.3)));
    }
    
    /**
     * JUnit test of componentTypeNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentTypeNotEqualErrorMessage(ComponentInterface, Class)
     */
    @Test
    public void testComponentTypeNotEqualErrorMessage() throws Exception {
        Assert.assertEquals(
                "The Vector: <8.0, 9.0, -4.0, 11.0> does not have the expected component type of: BigDecimal",
                sut.componentTypeNotEqualErrorMessage(new Vector(8, 9, -4, 11), BigDecimal.class));
        
        Assert.assertEquals(
                "The Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have the expected component type of: Integer",
                sut.componentTypeNotEqualErrorMessage(new Matrix(8, 9, -4, 11), Integer.class));
        
        Assert.assertEquals(
                "The 3D Vector: <8.0, 9.0, -4.0> does not have the expected component type of: Number",
                sut.componentTypeNotEqualErrorMessage(new Vector3(8, 9, -4), Number.class));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have the expected component type of: Number",
                sut.componentTypeNotEqualErrorMessage(new Matrix2(8, 9, -4, 11), Number.class));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have the expected component type of: Integer",
                sut.componentTypeNotEqualErrorMessage(new Matrix2(8, 9, -4, 11), Integer.class));
        
        Assert.assertEquals(
                "The Raw Vector: <8.0, 9.0, -4.0, 11.0> does not have the expected component type of: Double",
                sut.componentTypeNotEqualErrorMessage(new RawVector(8, 9, -4, 11), Double.class));
    }
    
    /**
     * JUnit test of dimensionalityMinimumNotMetErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#dimensionalityMinimumNotMetErrorMessage(ComponentInterface, int)
     */
    @Test
    public void testDimensionalityMinimumNotMetErrorMessage() throws Exception {
        Assert.assertEquals(
                "The Vector: <8.0, 9.0, -4.0, 11.0> does not have the minimum dimensionality of: 6",
                sut.dimensionalityMinimumNotMetErrorMessage(new Vector(8, 9, -4, 11), 6));
        
        Assert.assertEquals(
                "The Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have the minimum dimensionality of: 3",
                sut.dimensionalityMinimumNotMetErrorMessage(new Matrix(8, 9, -4, 11), 3));
        
        Assert.assertEquals(
                "The 3D Vector: <8.0, 9.0, -4.0> does not have the minimum dimensionality of: 4",
                sut.dimensionalityMinimumNotMetErrorMessage(new Vector3(8, 9, -4), 4));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have the minimum dimensionality of: 10",
                sut.dimensionalityMinimumNotMetErrorMessage(new Matrix2(8, 9, -4, 11), 10));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have the minimum dimensionality of: 5",
                sut.dimensionalityMinimumNotMetErrorMessage(new Matrix2(8, 9, -4, 11), 5));
        
        Assert.assertEquals(
                "The Raw Vector: <8.0, 9.0, -4.0, 11.0> does not have the minimum dimensionality of: 8",
                sut.dimensionalityMinimumNotMetErrorMessage(new RawVector(8, 9, -4, 11), 8));
    }
    
    /**
     * JUnit test of componentLengthNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentLengthNotEqualErrorMessage(Number[], int)
     */
    @Test
    public void testComponentLengthNotEqualErrorMessage() throws Exception {
        Assert.assertEquals(
                "The components: [8.0,9.0,-4.0,11.0] has a length of: 4 but was expecting a length of: 6",
                sut.componentLengthNotEqualErrorMessage(new Double[] {8.0, 9.0, -4.0, 11.0}, 6));
        
        Assert.assertEquals(
                "The components: [8.0,9.0,-4.0,11.0] has a length of: 4 but was expecting a length of: 3",
                sut.componentLengthNotEqualErrorMessage(new Number[] {8.0, 9.0, -4.0, 11.0}, 3));
        
        Assert.assertEquals(
                "The components: [8,9,-4] has a length of: 3 but was expecting a length of: 4",
                sut.componentLengthNotEqualErrorMessage(new Integer[] {8, 9, -4}, 4));
        
        Assert.assertEquals(
                "The components: [8,9,-4,11,9.4] has a length of: 5 but was expecting a length of: 10",
                sut.componentLengthNotEqualErrorMessage(new BigDecimal[] {BigDecimal.valueOf(8), BigDecimal.valueOf(9), BigDecimal.valueOf(-4), BigDecimal.valueOf(11), BigDecimal.valueOf(9.4)}, 10));
        
        Assert.assertEquals(
                "The components: [8,9,-4,11] has a length of: 4 but was expecting a length of: 5",
                sut.componentLengthNotEqualErrorMessage(new Integer[] {8, 9, -4, 11}, 5));
        
        Assert.assertEquals(
                "The components: [8,9,-4,11] has a length of: 4 but was expecting a length of: 8",
                sut.componentLengthNotEqualErrorMessage(new Number[] {8, 9, -4, 11}, 8));
    }
    
    /**
     * JUnit test of componentLengthNotSquareErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentLengthNotSquareErrorMessage(Number[])
     */
    @Test
    public void testComponentLengthNotSquareErrorMessage() throws Exception {
        Assert.assertEquals(
                "The components: [8.0,9.0,-4.0] has a length of: 3 but was expecting a perfect square",
                sut.componentLengthNotSquareErrorMessage(new Double[] {8.0, 9.0, -4.0}));
        
        Assert.assertEquals(
                "The components: [8.0,9.0,-4.0,11.0,9.4] has a length of: 5 but was expecting a perfect square",
                sut.componentLengthNotSquareErrorMessage(new Number[] {8.0, 9.0, -4.0, 11.0, 9.4}));
        
        Assert.assertEquals(
                "The components: [8,9,-4] has a length of: 3 but was expecting a perfect square",
                sut.componentLengthNotSquareErrorMessage(new Integer[] {8, 9, -4}));
        
        Assert.assertEquals(
                "The components: [8,9,-4,11,9.4] has a length of: 5 but was expecting a perfect square",
                sut.componentLengthNotSquareErrorMessage(new BigDecimal[] {BigDecimal.valueOf(8), BigDecimal.valueOf(9), BigDecimal.valueOf(-4), BigDecimal.valueOf(11), BigDecimal.valueOf(9.4)}));
        
        Assert.assertEquals(
                "The components: [8,9,-4,11,1,9,7,3] has a length of: 8 but was expecting a perfect square",
                sut.componentLengthNotSquareErrorMessage(new Integer[] {8, 9, -4, 11, 1, 9, 7, 3}));
        
        Assert.assertEquals(
                "The components: [8] has a length of: 1 but was expecting a perfect square",
                sut.componentLengthNotSquareErrorMessage(new Number[] {8}));
    }
    
    /**
     * JUnit test of componentIndexOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentIndexOutOfBoundsErrorMessage(ComponentInterface, int)
     */
    @Test
    public void testComponentIndexOutOfBoundsErrorMessage() throws Exception {
        Assert.assertEquals(
                "The Vector: <8.0, 9.0, -4.0, 11.0> does not have a component at index: 6",
                sut.componentIndexOutOfBoundsErrorMessage(new Vector(8, 9, -4, 11), 6));
        
        Assert.assertEquals(
                "The Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have a component at index: -2",
                sut.componentIndexOutOfBoundsErrorMessage(new Matrix(8, 9, -4, 11), -2));
        
        Assert.assertEquals(
                "The 3D Vector: <8.0, 9.0, -4.0> does not have a component at index: 4",
                sut.componentIndexOutOfBoundsErrorMessage(new Vector3(8, 9, -4), 4));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have a component at index: 10",
                sut.componentIndexOutOfBoundsErrorMessage(new Matrix2(8, 9, -4, 11), 10));
        
        Assert.assertEquals(
                "The 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have a component at index: -1",
                sut.componentIndexOutOfBoundsErrorMessage(new Matrix2(8, 9, -4, 11), -1));
        
        Assert.assertEquals(
                "The Raw Vector: <8.0, 9.0, -4.0, 11.0> does not have a component at index: 5",
                sut.componentIndexOutOfBoundsErrorMessage(new RawVector(8, 9, -4, 11), 5));
    }
    
    /**
     * JUnit test of componentCoordinateOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentCoordinateOutOfBoundsErrorMessage(ComponentInterface, int, int)
     */
    @Test
    public void testComponentCoordinateOutOfBoundsErrorMessage() throws Exception {
        Assert.assertEquals(
                "The Matrix: [<8.0, 9.0>, <-4.0, 11.0>] does not have a component at coordinate: (1,5)",
                sut.componentCoordinateOutOfBoundsErrorMessage(new Matrix(8, 9, -4, 11), 1, 5));
        
        Assert.assertEquals(
                "The Integer Matrix: [<8, 9>, <-4, 11>] does not have a component at coordinate: (-1,-2)",
                sut.componentCoordinateOutOfBoundsErrorMessage(new IntMatrix(8, 9, -4, 11), -1, -2));
        
        Assert.assertEquals(
                "The Matrix: [<8.0, 9.0, -4.0>, <9.0, 4.0, 11.0>, <3.6, 7.0, 0.7>] does not have a component at coordinate: (8,8)",
                sut.componentCoordinateOutOfBoundsErrorMessage(new Matrix(8, 9, -4, 9, 4, 11, 3.6, 7, 0.7), 8, 8));
        
        Assert.assertEquals(
                "The Big Matrix: [<8, 9, -4>, <9, 4, 11>, <3.6, 7, 0.7>] does not have a component at coordinate: (-3,0)",
                sut.componentCoordinateOutOfBoundsErrorMessage(new BigMatrix(8, 9, -4, 9, 4, 11, 3.6, 7, 0.7), -3, 0));
        
        Assert.assertEquals(
                "The Raw Matrix: [<8.0>] does not have a component at coordinate: (1,1)",
                sut.componentCoordinateOutOfBoundsErrorMessage(new RawMatrix(8.0), 1, 1));
        
        Assert.assertEquals(
                "The Matrix: [<8.0, 9.0, -4.0, 9.0>, <4.0, 11.0, 3.6, 7.0>, <0.7, 10.0, 4.5, 8.0>, <7.1, 6.0, 6.0, 3.5>] does not have a component at coordinate: (5,5)",
                sut.componentCoordinateOutOfBoundsErrorMessage(new Matrix(8, 9, -4, 9, 4, 11, 3.6, 7, 0.7, 10, 4.5, 8, 7.1, 6, 6, 3.5), 5, 5));
    }
    
    /**
     * JUnit test of componentRangeOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentRangeOutOfBoundsErrorMessage(ComponentInterface, int, int)
     */
    @Test
    public void testComponentRangeOutOfBoundsErrorMessage() throws Exception {
        Assert.assertEquals(
                "The range: [1,6) is out of bounds of the Vector: <8.0, 9.0, -4.0, 11.0>",
                sut.componentRangeOutOfBoundsErrorMessage(new Vector(8, 9, -4, 11), 1, 6));
        
        Assert.assertEquals(
                "The range: [-2,2) is out of bounds of the Matrix: [<8.0, 9.0>, <-4.0, 11.0>]",
                sut.componentRangeOutOfBoundsErrorMessage(new Matrix(8, 9, -4, 11), -2, 2));
        
        Assert.assertEquals(
                "The range: [4,5) is out of bounds of the 3D Vector: <8.0, 9.0, -4.0>",
                sut.componentRangeOutOfBoundsErrorMessage(new Vector3(8, 9, -4), 4, 5));
        
        Assert.assertEquals(
                "The range: [10,4) is out of bounds of the 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>]",
                sut.componentRangeOutOfBoundsErrorMessage(new Matrix2(8, 9, -4, 11), 10, 4));
        
        Assert.assertEquals(
                "The range: [-1,-4) is out of bounds of the 2D Matrix: [<8.0, 9.0>, <-4.0, 11.0>]",
                sut.componentRangeOutOfBoundsErrorMessage(new Matrix2(8, 9, -4, 11), -1, -4));
        
        Assert.assertEquals(
                "The range: [5,5) is out of bounds of the Raw Vector: <8.0, 9.0, -4.0, 11.0>",
                sut.componentRangeOutOfBoundsErrorMessage(new RawVector(8, 9, -4, 11), 5, 5));
    }
    
    /**
     * JUnit test of componentCoordinateRangeOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentCoordinateRangeOutOfBoundsErrorMessage(ComponentInterface, int, int, int, int)
     */
    @Test
    public void testComponentCoordinateRangeOutOfBoundsErrorMessage() throws Exception {
        Assert.assertEquals(
                "The coordinate range: (1,5) to (2,6) is out of bounds of the Matrix: [<8.0, 9.0>, <-4.0, 11.0>]",
                sut.componentCoordinateRangeOutOfBoundsErrorMessage(new Matrix(8, 9, -4, 11), 1, 5, 2, 6));
        
        Assert.assertEquals(
                "The coordinate range: (-1,-2) to (-2,-4) is out of bounds of the Integer Matrix: [<8, 9>, <-4, 11>]",
                sut.componentCoordinateRangeOutOfBoundsErrorMessage(new IntMatrix(8, 9, -4, 11), -1, -2, -2, -4));
        
        Assert.assertEquals(
                "The coordinate range: (8,8) to (4,4) is out of bounds of the Matrix: [<8.0, 9.0, -4.0>, <9.0, 4.0, 11.0>, <3.6, 7.0, 0.7>]",
                sut.componentCoordinateRangeOutOfBoundsErrorMessage(new Matrix(8, 9, -4, 9, 4, 11, 3.6, 7, 0.7), 8, 8, 4, 4));
        
        Assert.assertEquals(
                "The coordinate range: (-3,0) to (0,3) is out of bounds of the Big Matrix: [<8, 9, -4>, <9, 4, 11>, <3.6, 7, 0.7>]",
                sut.componentCoordinateRangeOutOfBoundsErrorMessage(new BigMatrix(8, 9, -4, 9, 4, 11, 3.6, 7, 0.7), -3, 0, 0, 3));
        
        Assert.assertEquals(
                "The coordinate range: (1,1) to (2,2) is out of bounds of the Raw Matrix: [<8.0>]",
                sut.componentCoordinateRangeOutOfBoundsErrorMessage(new RawMatrix(8.0), 1, 1, 2, 2));
        
        Assert.assertEquals(
                "The coordinate range: (5,5) to (10,5) is out of bounds of the Matrix: [<8.0, 9.0, -4.0, 9.0>, <4.0, 11.0, 3.6, 7.0>, <0.7, 10.0, 4.5, 8.0>, <7.1, 6.0, 6.0, 3.5>]",
                sut.componentCoordinateRangeOutOfBoundsErrorMessage(new Matrix(8, 9, -4, 9, 4, 11, 3.6, 7, 0.7, 10, 4.5, 8, 7.1, 6, 6, 3.5), 5, 5, 10, 5));
    }
    
}
