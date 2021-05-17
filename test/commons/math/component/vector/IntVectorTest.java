/*
 * File:    IntVectorTest.java
 * Package: commons.math.component.vector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.vector;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import commons.math.component.BaseComponent;
import commons.math.component.ComponentInterface;
import commons.math.component.handler.error.ComponentErrorHandlerInterface;
import commons.math.component.handler.error.ComponentErrorHandlerProvider;
import commons.math.component.handler.math.IntComponentMathHandler;
import commons.math.component.matrix.BigMatrix;
import commons.math.component.matrix.IntMatrix;
import commons.math.component.matrix.Matrix;
import commons.math.component.matrix.RawMatrix;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of IntVector.
 *
 * @see IntVector
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({IntVector.class})
public class IntVectorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(IntVectorTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private IntVector sut;
    
    
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
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#IntVector(int...)
     * @see IntVector#IntVector(List)
     * @see IntVector#IntVector(IntVector)
     * @see IntVector#IntVector(IntVector, int...)
     * @see IntVector#IntVector(int)
     * @see IntVector#IntVector()
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testConstructors() throws Exception {
        //components
        IntVector vector = new IntVector(1, 2, 1);
        Assert.assertArrayEquals(new Integer[] {1, 2, 1}, vector.getRawComponents());
        Assert.assertEquals(3, vector.getDimensionality());
        
        //list of components
        List<Integer> values = Arrays.asList(1, 2, 1, 8, 9);
        IntVector vector2 = new IntVector(values);
        Assert.assertArrayEquals(new Integer[] {1, 2, 1, 8, 9}, vector2.getRawComponents());
        Assert.assertEquals(5, vector2.getDimensionality());
        
        //int vector
        IntVector vector3 = new IntVector(new IntVector(1, 2, 1));
        Assert.assertArrayEquals(new Integer[] {1, 2, 1}, vector3.getRawComponents());
        Assert.assertEquals(3, vector3.getDimensionality());
        
        //vector
        IntVector vector4 = new IntVector(new Vector(1.15, 2.94, 1.3));
        Assert.assertArrayEquals(new Integer[] {1, 2, 1}, vector4.getRawComponents());
        Assert.assertEquals(3, vector4.getDimensionality());
        
        //int vector and component
        IntVector vector5 = new IntVector(new IntVector(1, 2), 1);
        Assert.assertArrayEquals(new Integer[] {1, 2, 1}, vector5.getRawComponents());
        Assert.assertEquals(3, vector5.getDimensionality());
        
        //int vector and components
        IntVector vector6 = new IntVector(new IntVector(1, 2, 1), 8, 9);
        Assert.assertArrayEquals(new Integer[] {1, 2, 1, 8, 9}, vector6.getRawComponents());
        Assert.assertEquals(5, vector6.getDimensionality());
        
        //vector and component
        IntVector vector7 = new IntVector(new Vector(1.15, 2.94), 1);
        Assert.assertArrayEquals(new Integer[] {1, 2, 1}, vector7.getRawComponents());
        Assert.assertEquals(3, vector7.getDimensionality());
        
        //vector and components
        IntVector vector8 = new IntVector(new Vector(1.15, 2.94, 1.3), 8, 9);
        Assert.assertArrayEquals(new Integer[] {1, 2, 1, 8, 9}, vector8.getRawComponents());
        Assert.assertEquals(5, vector8.getDimensionality());
        
        //dimensionality
        IntVector vectorDimensionality = new IntVector(6);
        Assert.assertArrayEquals(new Integer[] {0, 0, 0, 0, 0, 0}, vectorDimensionality.getRawComponents());
        Assert.assertEquals(6, vectorDimensionality.getDimensionality());
        
        //empty
        IntVector vectorDefault = new IntVector();
        Assert.assertArrayEquals(new Integer[] {}, vectorDefault.getRawComponents());
        Assert.assertEquals(0, vectorDefault.getDimensionality());
        
        //equality
        Assert.assertEquals(vector, vector3);
        Assert.assertEquals(vector3, vector4);
        Assert.assertEquals(vector4, vector5);
        Assert.assertEquals(vector5, vector7);
        Assert.assertEquals(vector2, vector6);
        Assert.assertEquals(vector6, vector8);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new IntVector(Arrays.asList(1, null, 1)));
        TestUtils.assertException(NullPointerException.class, () ->
                new IntVector((IntVector) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new IntVector((IntVector) null, 8, 9));
    }
    
    /**
     * JUnit test of vectorString.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#vectorString()
     */
    @Test
    public void testIntVectorString() throws Exception {
        //standard
        
        sut = new IntVector(new int[] {1});
        Assert.assertEquals("<1>", sut.vectorString());
        
        sut = new IntVector(1, 2);
        Assert.assertEquals("<1, 2>", sut.vectorString());
        
        sut = new IntVector(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Assert.assertEquals("<1, 2, 3, 4, 5, 6, 7, 8, 9>", sut.vectorString());
        
        sut = new IntVector(8, 77, 3, 4, 7, 890, 11, 8, 1);
        Assert.assertEquals("<8, 77, 3, 4, 7, 890, 11, 8, 1>", sut.vectorString());
        
        //empty
        
        sut = new IntVector();
        Assert.assertEquals("<>", sut.vectorString());
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new IntVector(9, -2, 3, 8, 6);
        Assert.assertEquals("<9, -2, 3, 8, 6>", sut.toString());
        
        sut = new IntVector(0, 9, -2, 3, 8, 6, 0);
        Assert.assertEquals("<0, 9, -2, 3, 8, 6, 0>", sut.toString());
        
        sut = new IntVector(new int[] {9});
        Assert.assertEquals("<9>", sut.toString());
        
        //empty
        
        sut = new IntVector();
        Assert.assertEquals("<>", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new IntVector(9, -2, 3, 8, 6);
        
        //standard
        
        other = new IntVector(9, -2, 3, 8, 6);
        Assert.assertTrue(sut.equals(other));
        
        other = new IntVector(0, 9, -2, 3, 8, 6);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(9, -2, 3, 8, 6, 0);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertFalse(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1, 2, 8, 5);
        Assert.assertFalse(sut.equals(other));
        
        sut = new IntVector(9, -2, 3, 8, 6, 2, 5, 5, 1);
        other = new Matrix(9, -2, 3, 8, 6, 2, 5, 5, 1);
        Assert.assertFalse(sut.equals(other));
        
        sut = new IntVector(9, -2, 3, 8, 6, 29, 5, 5, 1);
        other = new RawMatrix(9, -2, 3, 8, 6, 2, 5, 5, 1);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new IntVector().equals(new IntVector()));
        
        //invalid
        
        Assert.assertFalse(sut.equals(""));
        Assert.assertFalse(sut.equals(BigDecimal.valueOf(8.5)));
        Assert.assertFalse(sut.equals(new File(".")));
        Assert.assertFalse(sut.equals(null));
    }
    
    /**
     * JUnit test of dimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new IntVector(9, -2, 3, 8, 6);
        
        //standard
        
        other = new IntVector(6, 8, 3, -2, 9);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(6, 8, 3, -2, 9);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(6, 3, -2, 9);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new IntVector(6);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(5.501, 8.13);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        Assert.assertTrue(new IntVector().dimensionalityEqual(new IntVector()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new IntVector(9, -2, 3, 8, 6);
        
        //standard
        
        other = new IntVector(6, 8, 3, -2, 9);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(6, 8, 3, -2, 9);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(6, 3, -2, 9);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntVector(6);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Vector(5.501, 8.13);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.lengthEqual(other));
        
        sut = new IntVector(9, -2, 3, 8);
        other = new Matrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        Assert.assertTrue(new IntVector().lengthEqual(new IntVector()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new IntVector(9, -2, 3, 8, 6);
        
        //standard
        
        other = new IntVector(6, 8, 3, -2, 9);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(6, 8, 3, -2, 9);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(6, 3, -2, 9);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(6);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Vector(5.501, 8.13);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new IntVector().componentTypeEqual(new IntVector()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#cloned()
     */
    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testCloned() throws Exception {
        //standard
        sut = new IntVector(8, 7, 5, 1);
        IntVector clone = sut.cloned();
        Assert.assertNotNull(clone);
        Assert.assertArrayEquals(sut.getRawComponents(), clone.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), clone.hashCode());
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        //standard
        sut = new IntVector(8, 7, 5, 1);
        IntVector emptyCopy = sut.emptyCopy();
        Assert.assertNotNull(emptyCopy);
        Assert.assertArrayEquals(IntVector.origin(sut.getDimensionality()).getRawComponents(), emptyCopy.getRawComponents());
        Assert.assertNotEquals(sut.hashCode(), emptyCopy.hashCode());
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        sut = new IntVector();
        
        //standard
        Assert.assertEquals(
                new IntVector(), sut.createNewInstance(0));
        Assert.assertEquals(
                new IntVector(new int[] {0}), sut.createNewInstance(1));
        Assert.assertEquals(
                new IntVector(0, 0), sut.createNewInstance(2));
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.createNewInstance(3));
        Assert.assertEquals(
                new IntVector(0, 0, 0, 0), sut.createNewInstance(4));
        
        //invalid
        Assert.assertEquals(
                new IntVector(), sut.createNewInstance(-1));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        IntVector reversed;
        
        //standard
        
        sut = new IntVector(9, -2, 3, 8, 6);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Integer[] {6, 8, 3, -2, 9}, reversed.getRawComponents());
        
        sut = new IntVector(6, 8, 3, -2, 9);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Integer[] {9, -2, 3, 8, 6}, reversed.getRawComponents());
        
        sut = new IntVector(0, 1, 0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Integer[] {1, 0, 1, 0}, reversed.getRawComponents());
        
        sut = new IntVector(new int[] {6});
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Integer[] {6}, reversed.getRawComponents());
        
        sut = new IntVector();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotEquals(sut.hashCode(), reversed.hashCode());
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        Assert.assertArrayEquals(new Integer[] {}, reversed.getRawComponents());
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        IntVector other;
        
        //standard
        
        sut = new IntVector(new int[] {9});
        other = new IntVector(new int[] {10});
        Assert.assertEquals(1, sut.distance(other).intValue());
        
        sut = new IntVector(9, 2);
        other = new IntVector(10, 6);
        Assert.assertEquals(4, sut.distance(other).intValue());
        
        sut = new IntVector(9, 2, -5);
        other = new IntVector(10, 6, 0);
        Assert.assertEquals(6, sut.distance(other).intValue());
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(10, 6, 0, 5);
        Assert.assertEquals(8, sut.distance(other).intValue());
        
        sut = new IntVector();
        other = new IntVector();
        Assert.assertEquals(0, sut.distance(other).intValue());
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector other1 = new IntVector(10, 6, 0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new IntVector(9, 2, -5);
        final IntVector other2 = new IntVector(10, 6, 0, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new IntVector(9, 2);
        final IntVector other3 = new IntVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        IntVector other;
        
        //standard
        
        sut = new IntVector(new int[] {9});
        other = new IntVector(new int[] {10});
        Assert.assertEquals(
                new IntVector(new int[] {9}), sut.midpoint(other));
        
        sut = new IntVector(9, 2);
        other = new IntVector(10, 6);
        Assert.assertEquals(
                new IntVector(9, 4), sut.midpoint(other));
        
        sut = new IntVector(9, 2, -5);
        other = new IntVector(10, 6, 0);
        Assert.assertEquals(
                new IntVector(9, 4, -2), sut.midpoint(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(10, 6, 0, 5);
        Assert.assertEquals(
                new IntVector(9, 4, -2, 8), sut.midpoint(other));
        
        sut = new IntVector();
        other = new IntVector();
        Assert.assertEquals(
                new IntVector(), sut.midpoint(other));
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector other1 = new IntVector(10, 6, 0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new IntVector(9, 2, -5);
        final IntVector other2 = new IntVector(10, 6, 0, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new IntVector(9, 2);
        final IntVector other3 = new IntVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#average(List)
     * @see IntVector#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        IntVector other1;
        IntVector other2;
        IntVector other3;
        IntVector other4;
        
        //standard
        
        sut = new IntVector(new int[] {9});
        other1 = new IntVector(new int[] {10});
        other2 = new IntVector(new int[] {2});
        other3 = new IntVector(new int[] {12});
        Assert.assertEquals(
                new IntVector(new int[] {8}), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new IntVector(new int[] {8}), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new IntVector(9, 2);
        other1 = new IntVector(10, 6);
        other2 = new IntVector(2, 5);
        Assert.assertEquals(
                new IntVector(7, 4), sut.average(other1, other2));
        Assert.assertEquals(
                new IntVector(7, 4), sut.average(Arrays.asList(other1, other2)));
        
        sut = new IntVector(9, 2, -5);
        other1 = new IntVector(10, 6, 0);
        Assert.assertEquals(
                new IntVector(9, 4, -2), sut.average(other1));
        Assert.assertEquals(
                new IntVector(9, 4, -2), sut.average(Collections.singletonList(other1)));
        
        sut = new IntVector(9, 2, -5, 11);
        other1 = new IntVector(10, 6, 0, 5);
        other2 = new IntVector(2, 5, 0, 3);
        other3 = new IntVector(12, 0, 7, 8);
        Assert.assertEquals(
                new IntVector(8, 3, 0, 6), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new IntVector(8, 3, 0, 6), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new IntVector();
        other1 = new IntVector();
        other2 = new IntVector();
        other3 = new IntVector();
        Assert.assertEquals(
                new IntVector(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new IntVector(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector otherF11 = new IntVector(10, 6, 0, 5);
        final IntVector otherF12 = new IntVector(2, 5, 0, 3);
        final IntVector otherF13 = new IntVector(12, 0, 7);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector otherF21 = new IntVector(10, 6, 0, 5);
        final IntVector otherF22 = new IntVector(2, 5, 0, 3, 9);
        final IntVector otherF23 = new IntVector(12, 0, 7, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector otherF31 = new IntVector(10, 6, 0, 5);
        final IntVector otherF33 = new IntVector(12, 0, 7, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new IntVector(9, 2, -5, 11);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new IntVector(new int[] {9});
        Assert.assertEquals(9, sut.sum().intValue());
        
        sut = new IntVector(9, 2);
        Assert.assertEquals(11, sut.sum().intValue());
        
        sut = new IntVector(9, 2, -5);
        Assert.assertEquals(6, sut.sum().intValue());
        
        sut = new IntVector(9, 2, -5, 11);
        Assert.assertEquals(17, sut.sum().intValue());
        
        sut = new IntVector();
        Assert.assertEquals(0, sut.sum().intValue());
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new IntVector(new int[] {9});
        Assert.assertEquals(81, sut.squareSum().intValue());
        
        sut = new IntVector(9, 2);
        Assert.assertEquals(85, sut.squareSum().intValue());
        
        sut = new IntVector(9, 2, -5);
        Assert.assertEquals(110, sut.squareSum().intValue());
        
        sut = new IntVector(9, 2, -5, 11);
        Assert.assertEquals(231, sut.squareSum().intValue());
        
        sut = new IntVector();
        Assert.assertEquals(0, sut.squareSum().intValue());
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        IntVector other;
        
        //standard
        
        sut = new IntVector(new int[] {9});
        other = new IntVector(new int[] {10});
        Assert.assertEquals(
                new IntVector(new int[] {19}), sut.plus(other));
        
        sut = new IntVector(9, 2);
        other = new IntVector(10, 6);
        Assert.assertEquals(
                new IntVector(19, 8), sut.plus(other));
        
        sut = new IntVector(9, 2, -5);
        other = new IntVector(10, 6, 0);
        Assert.assertEquals(
                new IntVector(19, 8, -5), sut.plus(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(10, 6, 0, 5);
        Assert.assertEquals(
                new IntVector(19, 8, -5, 16), sut.plus(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(1, 1, 1, 1);
        Assert.assertEquals(
                new IntVector(10, 3, -4, 12), sut.plus(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(0, 0, 0, 0);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), sut.plus(other));
        
        sut = new IntVector();
        other = new IntVector();
        Assert.assertEquals(
                new IntVector(), sut.plus(other));
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector other1 = new IntVector(10, 6, 0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new IntVector(9, 2, -5);
        final IntVector other2 = new IntVector(10, 6, 0, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new IntVector(9, 2);
        final IntVector other3 = new IntVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        IntVector other;
        
        //standard
        
        sut = new IntVector(new int[] {9});
        other = new IntVector(new int[] {10});
        Assert.assertEquals(
                new IntVector(new int[] {-1}), sut.minus(other));
        
        sut = new IntVector(9, 2);
        other = new IntVector(10, 6);
        Assert.assertEquals(
                new IntVector(-1, -4), sut.minus(other));
        
        sut = new IntVector(9, 2, -5);
        other = new IntVector(10, 6, 0);
        Assert.assertEquals(
                new IntVector(-1, -4, -5), sut.minus(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(10, 6, 0, 5);
        Assert.assertEquals(
                new IntVector(-1, -4, -5, 6), sut.minus(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(1, 1, 1, 1);
        Assert.assertEquals(
                new IntVector(8, 1, -6, 10), sut.minus(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(0, 0, 0, 0);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), sut.minus(other));
        
        sut = new IntVector();
        other = new IntVector();
        Assert.assertEquals(
                new IntVector(), sut.minus(other));
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector other1 = new IntVector(10, 6, 0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new IntVector(9, 2, -5);
        final IntVector other2 = new IntVector(10, 6, 0, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new IntVector(9, 2);
        final IntVector other3 = new IntVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#times(BaseComponent)
     */
    @Test
    public void testTimes() throws Exception {
        IntVector other;
        
        //standard
        
        sut = new IntVector(new int[] {9});
        other = new IntVector(new int[] {10});
        Assert.assertEquals(
                new IntVector(new int[] {90}), sut.times(other));
        
        sut = new IntVector(9, 2);
        other = new IntVector(10, 6);
        Assert.assertEquals(
                new IntVector(90, 12), sut.times(other));
        
        sut = new IntVector(9, 2, -5);
        other = new IntVector(10, 6, 0);
        Assert.assertEquals(
                new IntVector(90, 12, 0), sut.times(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(10, 6, 0, 5);
        Assert.assertEquals(
                new IntVector(90, 12, 0, 55), sut.times(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(1, 1, 1, 1);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), sut.times(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(0, 0, 0, 0);
        Assert.assertEquals(
                new IntVector(0, 0, 0, 0), sut.times(other));
        
        sut = new IntVector();
        other = new IntVector();
        Assert.assertEquals(
                new IntVector(), sut.times(other));
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector other1 = new IntVector(10, 6, 0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new IntVector(9, 2, -5);
        final IntVector other2 = new IntVector(10, 6, 0, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new IntVector(9, 2);
        final IntVector other3 = new IntVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times(null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new IntVector(new int[] {9});
        Assert.assertEquals(
                new IntVector(new int[] {45}), sut.scale(5));
        
        sut = new IntVector(9, 2);
        Assert.assertEquals(
                new IntVector(9, 2), sut.scale(1.0094));
        
        sut = new IntVector(9, 2, -5);
        Assert.assertEquals(
                new IntVector(144, 32, -80), sut.scale(16.42));
        
        sut = new IntVector(9, 2, -5, 11);
        Assert.assertEquals(
                new IntVector(-18, -4, 10, -22), sut.scale(-2.677));
        
        sut = new IntVector(9, 2, -5, 11);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), sut.scale(1));
        
        sut = new IntVector(9, 2, -5, 11);
        Assert.assertEquals(
                new IntVector(-9, -2, 5, -11), sut.scale(-1));
        
        sut = new IntVector(9, 2, -5, 11);
        Assert.assertEquals(
                new IntVector(0, 0, 0, 0), sut.scale(0));
        
        sut = new IntVector();
        Assert.assertEquals(
                new IntVector(), sut.scale(7.1));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        IntVector other;
        
        //standard
        
        sut = new IntVector(new int[] {9});
        other = new IntVector(new int[] {10});
        Assert.assertEquals(
                new IntVector(new int[] {0}), sut.dividedBy(other));
        
        sut = new IntVector(9, 2);
        other = new IntVector(10, 6);
        Assert.assertEquals(
                new IntVector(0, 0), sut.dividedBy(other));
        
        sut = new IntVector(9, 2, -5);
        other = new IntVector(10, 6, 1);
        Assert.assertEquals(
                new IntVector(0, 0, -5), sut.dividedBy(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(10, 6, 1, 5);
        Assert.assertEquals(
                new IntVector(0, 0, -5, 2), sut.dividedBy(other));
        
        sut = new IntVector(9, 2, -5, 11);
        other = new IntVector(1, 1, 1, 1);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), sut.dividedBy(other));
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector other0 = new IntVector(0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new IntVector();
        other = new IntVector();
        Assert.assertEquals(
                new IntVector(), sut.dividedBy(other));
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector other1 = new IntVector(10, 6, 0);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new IntVector(9, 2, -5);
        final IntVector other2 = new IntVector(10, 6, 0, 5);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new IntVector(9, 2);
        final IntVector other3 = new IntVector();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new IntVector(new int[] {9});
        Assert.assertEquals(
                new IntVector(new int[] {9}), sut.round());
        
        sut = new IntVector(9, 2);
        Assert.assertEquals(
                new IntVector(9, 2), sut.round());
        
        sut = new IntVector(9, 2, -5);
        Assert.assertEquals(
                new IntVector(9, 2, -5), sut.round());
        
        sut = new IntVector(9, 2, -5, 11);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), sut.round());
        
        sut = new IntVector();
        Assert.assertEquals(
                new IntVector(), sut.round());
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#dot(VectorInterface)
     */
    @Test
    public void testDot() throws Exception {
        IntVector other;
        
        //standard
        
        sut = new IntVector(8, 9, 8);
        other = new IntVector(1, 2, 1);
        Assert.assertEquals(34, sut.dot(other).intValue());
        
        sut = new IntVector(18, 9, -8);
        other = new IntVector(12, 72, 31);
        Assert.assertEquals(616, sut.dot(other).intValue());
        
        sut = new IntVector(8, 9, 8);
        other = new IntVector(8, 9, 8);
        Assert.assertEquals(209, sut.dot(other).intValue());
        
        sut = new IntVector(8, 9, 8);
        other = new IntVector(-8, -9, -8);
        Assert.assertEquals(-209, sut.dot(other).intValue());
        
        sut = new IntVector(1, -1, 3);
        other = new IntVector(3, 3, 0);
        Assert.assertEquals(0, sut.dot(other).intValue());
        
        sut = new IntVector(8, 9, 8);
        other = IntVector.origin(3);
        Assert.assertEquals(0, sut.dot(other).intValue());
        
        sut = new IntVector(8, 9, 8);
        other = IntVector.identity(3);
        Assert.assertEquals(25, sut.dot(other).intValue());
        
        //invalid
        
        sut = new IntVector(8, 9, 8);
        final IntVector other1 = new IntVector(1, 2, 1, 2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dot(other1));
        
        sut = new IntVector(8, 9, 8);
        final IntVector other2 = new IntVector(1, 2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dot(other2));
        
        sut = new IntVector(8, 9, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dot(null));
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        //standard
        
        sut = new IntVector(8, 9, 8);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.normalize());
        
        sut = new IntVector(-8, -9, -8);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.normalize());
        
        sut = new IntVector(18, 9, -8);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.normalize());
        
        sut = new IntVector(12, 72, 31);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.normalize());
        
        sut = new IntVector(1, 2, 1);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.normalize());
        
        sut = new IntVector(0, 0, 0);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.normalize());
        
        sut = new IntVector(1, -1, 3);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.normalize());
        
        sut = IntVector.origin(3);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut.normalize());
        
        sut = IntVector.identity(3);
        Assert.assertEquals(
                new IntVector(1, 1, 1), sut.normalize());
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        //standard
        
        sut = new IntVector(8, 9, 8);
        Assert.assertEquals(14, sut.hypotenuse().intValue());
        
        sut = new IntVector(-8, -9, -8);
        Assert.assertEquals(14, sut.hypotenuse().intValue());
        
        sut = new IntVector(18, 9, -8);
        Assert.assertEquals(21, sut.hypotenuse().intValue());
        
        sut = new IntVector(12, 72, 31);
        Assert.assertEquals(79, sut.hypotenuse().intValue());
        
        sut = new IntVector(1, 2, 1);
        Assert.assertEquals(2, sut.hypotenuse().intValue());
        
        sut = new IntVector(0, 0, 0);
        Assert.assertEquals(0, sut.hypotenuse().intValue());
        
        sut = new IntVector(1, -1, 3);
        Assert.assertEquals(3, sut.hypotenuse().intValue());
        
        sut = IntVector.origin(3);
        Assert.assertEquals(0, sut.hypotenuse().intValue());
        
        sut = IntVector.identity(3);
        Assert.assertEquals(1, sut.hypotenuse().intValue());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        IntVector copy;
        
        //standard
        
        sut = new IntVector(new int[] {9});
        copy = new IntVector(1);
        sut.copy(copy);
        Assert.assertEquals(
                new IntVector(new int[] {9}), copy);
        
        sut = new IntVector(9, 2);
        copy = new IntVector(2);
        sut.copy(copy);
        Assert.assertEquals(
                new IntVector(9, 2), copy);
        
        sut = new IntVector(9, 2, -5);
        copy = new IntVector(3);
        sut.copy(copy);
        Assert.assertEquals(
                new IntVector(9, 2, -5), copy);
        
        sut = new IntVector(9, 2, -5, 11);
        copy = new IntVector(4);
        sut.copy(copy);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), copy);
        
        sut = new IntVector();
        copy = new IntVector();
        sut.copy(copy);
        Assert.assertEquals(
                new IntVector(), copy);
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector copy1 = new IntVector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new IntVector(9, 2, -5);
        final IntVector copy2 = new IntVector(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                sut.copy(copy2));
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#copyMeta(IntVector)
     */
    @Test
    public void testCopyMeta() throws Exception {
        IntVector component1 = new IntVector(8, 7, 7, 3);
        Assert.assertEquals(4, component1.getDimensionality());
        Assert.assertArrayEquals(new Integer[] {8, 7, 7, 3}, component1.getRawComponents());
        
        IntVector component2 = new IntVector(9, 6, 2);
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        Assert.assertArrayEquals(new Integer[] {9, 6, 2}, component2.getRawComponents());
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new IntVector(9, 2, -5, 11);
        sut.redim(3);
        Assert.assertEquals(
                new IntVector(9, 2, -5), sut);
        
        sut = new IntVector(9, 2, -5, 11);
        sut.redim(5);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11, 0), sut);
        
        sut = new IntVector(9, 2, -5, 11);
        sut.redim(4);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), sut);
        
        sut = new IntVector(9, 2, -5, 11);
        sut.redim(1);
        Assert.assertEquals(
                new IntVector(new int[] {9}), sut);
        
        sut = new IntVector(9, 2, -5, 11);
        sut.redim(0);
        Assert.assertEquals(
                new IntVector(), sut);
        
        sut = new IntVector();
        sut.redim(3);
        Assert.assertEquals(
                new IntVector(0, 0, 0), sut);
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        sut.redim(-1);
        Assert.assertEquals(
                new IntVector(), sut);
        
        sut = Mockito.spy(IntVector.class);
        sut.setComponents(new Integer[] {9, 2, -5, 11});
        Mockito.when(sut.isResizeable()).thenReturn(false);
        Assert.assertFalse(sut.isResizeable());
        sut.redim(3);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), sut);
    }
    
    /**
     * JUnit test of subVector.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#subVector(int, int)
     * @see IntVector#subVector(int)
     */
    @Test
    public void testSubVector() throws Exception {
        //standard
        
        IntVector subVector;
        sut = new IntVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        subVector = sut.subVector(0, 0);
        Assert.assertEquals(
                new IntVector(), subVector);
        
        subVector = sut.subVector(0, 1);
        Assert.assertEquals(
                new IntVector(new int[] {1}), subVector);
        
        subVector = sut.subVector(0, 2);
        Assert.assertEquals(
                new IntVector(1, 2), subVector);
        
        subVector = sut.subVector(2, 9);
        Assert.assertEquals(
                new IntVector(3, 4, 5, 6, 7, 8, 9), subVector);
        
        subVector = sut.subVector(8, 10);
        Assert.assertEquals(
                new IntVector(9, 10), subVector);
        
        subVector = sut.subVector(0, 10);
        Assert.assertEquals(
                new IntVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(5);
        Assert.assertEquals(
                new IntVector(6, 7, 8, 9, 10), subVector);
        
        subVector = sut.subVector(8);
        Assert.assertEquals(
                new IntVector(9, 10), subVector);
        
        subVector = sut.subVector(0);
        Assert.assertEquals(
                new IntVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), subVector);
        
        //invalid
        
        sut = new IntVector(10);
        
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
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#dimensionalityToLength(int)
     * @see IntVector#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new IntVector(9, 2, -5);
        
        //standard
        Assert.assertEquals(3, sut.dimensionalityToLength());
        Assert.assertEquals(3, sut.dimensionalityToLength(3));
        Assert.assertEquals(5, sut.dimensionalityToLength(5));
        Assert.assertEquals(1, sut.dimensionalityToLength(1));
        Assert.assertEquals(0, sut.dimensionalityToLength(0));
        
        //invalid
        Assert.assertEquals(0, sut.dimensionalityToLength(-1));
        Assert.assertEquals(0, sut.dimensionalityToLength(-3));
    }
    
    /**
     * JUnit test of lengthToDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#lengthToDimensionality(int)
     * @see IntVector#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new IntVector(9, 2, -5);
        
        //standard
        Assert.assertEquals(3, sut.lengthToDimensionality());
        Assert.assertEquals(3, sut.lengthToDimensionality(3));
        Assert.assertEquals(5, sut.lengthToDimensionality(5));
        Assert.assertEquals(1, sut.lengthToDimensionality(1));
        Assert.assertEquals(0, sut.lengthToDimensionality(0));
        
        //invalid
        Assert.assertEquals(0, sut.lengthToDimensionality(-1));
        Assert.assertEquals(0, sut.lengthToDimensionality(-3));
    }
    
    /**
     * JUnit test of calculateDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new IntVector(9, 2, -5);
        
        //standard
        Assert.assertEquals(3, sut.getDimensionality());
        Whitebox.setInternalState(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(3, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        IntVector component;
        
        //standard
        component = new IntVector(8, 7, 7, 3);
        Assert.assertArrayEquals(new Integer[] {8, 7, 7, 3}, component.getRawComponents());
        Assert.assertArrayEquals(new Integer[] {8, 7, 7, 3}, component.getComponents());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        IntVector component;
        
        //standard
        
        component = new IntVector(9, 2, -5);
        Assert.assertArrayEquals(new Integer[] {9, 2, -5}, component.getComponents());
        
        component = new IntVector(9, 2);
        Assert.assertArrayEquals(new Integer[] {9, 2}, component.getComponents());
        
        component = new IntVector();
        Assert.assertArrayEquals(new Integer[] {}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        IntVector component;
        
        //standard
        component = new IntVector(8, 7, 7, 3);
        Assert.assertArrayEquals(new int[] {8, 7, 7, 3}, component.getPrimitiveComponents());
        Assert.assertArrayEquals(new Integer[] {8, 7, 7, 3}, component.getComponents());
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getRaw(int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new IntVector(9, 2, -5);
        Assert.assertEquals(9, sut.getRaw(0).intValue());
        Assert.assertEquals(2, sut.getRaw(1).intValue());
        Assert.assertEquals(-5, sut.getRaw(2).intValue());
        
        sut = new IntVector(9, 2);
        Assert.assertEquals(9, sut.getRaw(0).intValue());
        Assert.assertEquals(2, sut.getRaw(1).intValue());
        
        //invalid
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.getRaw(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.getRaw(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
        
        sut = new IntVector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.getRaw(0));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new IntVector(9, 2, -5);
        Assert.assertEquals(9, sut.get(0).intValue());
        Assert.assertEquals(2, sut.get(1).intValue());
        Assert.assertEquals(-5, sut.get(2).intValue());
        
        sut = new IntVector(9, 2);
        Assert.assertEquals(9, sut.get(0).intValue());
        Assert.assertEquals(2, sut.get(1).intValue());
        
        //invalid
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.get(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.get(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
        
        sut = new IntVector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.get(0));
    }
    
    /**
     * JUnit test of getRawX.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getRawX()
     */
    @Test
    public void testGetRawX() throws Exception {
        //standard
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertEquals(8, sut.getRawX().intValue());
        
        sut = new IntVector(4);
        Assert.assertEquals(0, sut.getRawX().intValue());
        
        //invalid
        
        sut = new IntVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawX().intValue()));
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getX()
     */
    @Test
    public void testGetX() throws Exception {
        //standard
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertEquals(8, sut.getX().intValue());
        
        sut = new IntVector(4);
        Assert.assertEquals(0, sut.getX().intValue());
        
        //invalid
        
        sut = new IntVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getX().intValue()));
    }
    
    /**
     * JUnit test of getRawY.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getRawY()
     */
    @Test
    public void testGetRawY() throws Exception {
        //standard
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertEquals(77, sut.getRawY().intValue());
        
        sut = new IntVector(4);
        Assert.assertEquals(0, sut.getRawY().intValue());
        
        //invalid
        
        sut = new IntVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawY().intValue()));
        
        sut = new IntVector(new int[] {8});
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawY().intValue()));
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getY()
     */
    @Test
    public void testGetY() throws Exception {
        //standard
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertEquals(77, sut.getY().intValue());
        
        sut = new IntVector(4);
        Assert.assertEquals(0, sut.getY().intValue());
        
        //invalid
        
        sut = new IntVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getY().intValue()));
        
        sut = new IntVector(new int[] {8});
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getY().intValue()));
    }
    
    /**
     * JUnit test of getRawZ.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getRawZ()
     */
    @Test
    public void testGetRawZ() throws Exception {
        //standard
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertEquals(1, sut.getRawZ().intValue());
        
        sut = new IntVector(4);
        Assert.assertEquals(0, sut.getRawZ().intValue());
        
        //invalid
        
        sut = new IntVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawZ().intValue()));
        
        sut = new IntVector(new int[] {8});
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawZ().intValue()));
        
        sut = new IntVector(8, 77);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawZ().intValue()));
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        //standard
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertEquals(1, sut.getZ().intValue());
        
        sut = new IntVector(4);
        Assert.assertEquals(0, sut.getZ().intValue());
        
        //invalid
        
        sut = new IntVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getZ().intValue()));
        
        sut = new IntVector(new int[] {8});
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getZ().intValue()));
        
        sut = new IntVector(8, 77);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getZ().intValue()));
    }
    
    /**
     * JUnit test of getRawW.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getRawW()
     */
    @Test
    public void testGetRawW() throws Exception {
        //standard
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertEquals(3, sut.getRawW().intValue());
        
        sut = new IntVector(4);
        Assert.assertEquals(0, sut.getRawW().intValue());
        
        //invalid
        
        sut = new IntVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawW().intValue()));
        
        sut = new IntVector(new int[] {8});
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawW().intValue()));
        
        sut = new IntVector(8, 77);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawW().intValue()));
        
        sut = new IntVector(8, 77, 1);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getRawW().intValue()));
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getW()
     */
    @Test
    public void testGetW() throws Exception {
        //standard
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertEquals(3, sut.getW().intValue());
        
        sut = new IntVector(4);
        Assert.assertEquals(0, sut.getW().intValue());
        
        //invalid
        
        sut = new IntVector();
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getW().intValue()));
        
        sut = new IntVector(new int[] {8});
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getW().intValue()));
        
        sut = new IntVector(8, 77);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getW().intValue()));
        
        sut = new IntVector(8, 77, 1);
        TestUtils.assertNoException(() ->
                Assert.assertEquals(0, sut.getW().intValue()));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new IntVector(6, 8, 3, -2, 9);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new IntVector(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new IntVector(6, 3, -2, 9);
        Assert.assertEquals(4, sut.getDimensionality());
        
        sut = new IntVector(new int[] {6});
        Assert.assertEquals(1, sut.getDimensionality());
        
        sut = new IntVector(6, 8, 3, -2, 9, 8);
        Assert.assertEquals(6, sut.getDimensionality());
        
        sut = new IntVector();
        Assert.assertEquals(0, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new IntVector(6, 8, 3, -2, 9);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new IntVector(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new IntVector(6, 3, -2, 9);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new IntVector(new int[] {6});
        Assert.assertEquals(1, sut.getLength());
        
        sut = new IntVector(6, 8, 6, -2, 9, 8);
        Assert.assertEquals(6, sut.getLength());
        
        sut = new IntVector();
        Assert.assertEquals(0, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        sut = new IntVector();
        Assert.assertEquals(IntVector.class, sut.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getType()
     */
    @Test
    public void testGetType() throws Exception {
        sut = new IntVector();
        Assert.assertEquals(Integer.class, sut.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        sut = new IntVector();
        Assert.assertTrue(sut.getHandler() instanceof IntComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        sut = new IntVector();
        Assert.assertEquals(errorHandler, sut.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getName()
     */
    @Test
    public void testGetName() throws Exception {
        sut = new IntVector();
        Assert.assertEquals("Integer Vector", sut.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        sut = new IntVector();
        Assert.assertEquals("Integer Vectors", sut.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        sut = new IntVector();
        Assert.assertEquals(1, sut.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        sut = new IntVector();
        Assert.assertTrue(sut.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        IntVector component;
        Integer[] newComponents;
        
        //standard
        
        component = new IntVector(6, 3, -2, 9);
        newComponents = new Integer[] {5, 7, 8, 9};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Integer[] {5, 7, 8, 9}, component.getRawComponents());
        Assert.assertEquals(4, component.getDimensionality());
        
        component = new IntVector(6, 3, -2, 9);
        newComponents = new Integer[] {5, 7, 8};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Integer[] {5, 7, 8}, component.getRawComponents());
        Assert.assertEquals(3, component.getDimensionality());
        
        component = new IntVector(6, 3, -2, 9);
        newComponents = new Integer[] {5, 7, 8, 9, 10};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Integer[] {5, 7, 8, 9, 10}, component.getRawComponents());
        Assert.assertEquals(5, component.getDimensionality());
        
        component = new IntVector(6, 3, -2, 9);
        newComponents = new Integer[] {};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        Assert.assertArrayEquals(new Integer[] {}, component.getRawComponents());
        Assert.assertEquals(0, component.getDimensionality());
        
        //invalid
        
        final IntVector component2 = new IntVector(6, 3, -2, 9);
        Assert.assertTrue(component2.isResizeable());
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new IntVector(9, 2, -5);
        Assert.assertArrayEquals(new Integer[] {9, 2, -5}, sut.getRawComponents());
        sut.set(0, 6);
        sut.set(1, 3);
        sut.set(2, 1);
        Assert.assertArrayEquals(new Integer[] {6, 3, 1}, sut.getRawComponents());
        
        sut = new IntVector(9, 2);
        Assert.assertArrayEquals(new Integer[] {9, 2}, sut.getRawComponents());
        sut.set(0, 6);
        sut.set(1, 3);
        Assert.assertArrayEquals(new Integer[] {6, 3}, sut.getRawComponents());
        
        //invalid
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.set(3, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 6));
        
        sut = new IntVector();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.set(0, 6));
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#setX(Number)
     */
    @Test
    public void testSetX() throws Exception {
        //standard
        
        sut = new IntVector(4);
        Assert.assertArrayEquals(new Integer[] {0, 0, 0, 0}, sut.getRawComponents());
        sut.setX(6);
        Assert.assertArrayEquals(new Integer[] {6, 0, 0, 0}, sut.getRawComponents());
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
        sut.setX(6);
        Assert.assertArrayEquals(new Integer[] {6, 77, 1, 3}, sut.getRawComponents());
        
        //invalid
        
        sut = new IntVector();
        Assert.assertArrayEquals(new Integer[] {}, sut.getRawComponents());
        sut.setX(6);
        Assert.assertArrayEquals(new Integer[] {}, sut.getRawComponents());
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setX(null));
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#setY(Number)
     */
    @Test
    public void testSetY() throws Exception {
        //standard
        
        sut = new IntVector(4);
        Assert.assertArrayEquals(new Integer[] {0, 0, 0, 0}, sut.getRawComponents());
        sut.setY(6);
        Assert.assertArrayEquals(new Integer[] {0, 6, 0, 0}, sut.getRawComponents());
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
        sut.setY(6);
        Assert.assertArrayEquals(new Integer[] {8, 6, 1, 3}, sut.getRawComponents());
        
        //invalid
        
        sut = new IntVector();
        Assert.assertArrayEquals(new Integer[] {}, sut.getRawComponents());
        sut.setY(6);
        Assert.assertArrayEquals(new Integer[] {}, sut.getRawComponents());
        
        sut = new IntVector(new int[] {8});
        Assert.assertArrayEquals(new Integer[] {8}, sut.getRawComponents());
        sut.setY(6);
        Assert.assertArrayEquals(new Integer[] {8}, sut.getRawComponents());
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setY(null));
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#setZ(Number)
     */
    @Test
    public void testSetZ() throws Exception {
        //standard
        
        sut = new IntVector(4);
        Assert.assertArrayEquals(new Integer[] {0, 0, 0, 0}, sut.getRawComponents());
        sut.setZ(6);
        Assert.assertArrayEquals(new Integer[] {0, 0, 6, 0}, sut.getRawComponents());
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
        sut.setZ(6);
        Assert.assertArrayEquals(new Integer[] {8, 77, 6, 3}, sut.getRawComponents());
        
        //invalid
        
        sut = new IntVector();
        Assert.assertArrayEquals(new Integer[] {}, sut.getRawComponents());
        sut.setZ(6);
        Assert.assertArrayEquals(new Integer[] {}, sut.getRawComponents());
        
        sut = new IntVector(new int[] {8});
        Assert.assertArrayEquals(new Integer[] {8}, sut.getRawComponents());
        sut.setZ(6);
        Assert.assertArrayEquals(new Integer[] {8}, sut.getRawComponents());
        
        sut = new IntVector(8, 77);
        Assert.assertArrayEquals(new Integer[] {8, 77}, sut.getRawComponents());
        sut.setZ(6);
        Assert.assertArrayEquals(new Integer[] {8, 77}, sut.getRawComponents());
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setZ(null));
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#setW(Number)
     */
    @Test
    public void testSetW() throws Exception {
        //standard
        
        sut = new IntVector(4);
        Assert.assertArrayEquals(new Integer[] {0, 0, 0, 0}, sut.getRawComponents());
        sut.setW(6);
        Assert.assertArrayEquals(new Integer[] {0, 0, 0, 6}, sut.getRawComponents());
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
        sut.setW(6);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 6}, sut.getRawComponents());
        
        //invalid
        
        sut = new IntVector();
        Assert.assertArrayEquals(new Integer[] {}, sut.getRawComponents());
        sut.setW(6);
        Assert.assertArrayEquals(new Integer[] {}, sut.getRawComponents());
        
        sut = new IntVector(new int[] {8});
        Assert.assertArrayEquals(new Integer[] {8}, sut.getRawComponents());
        sut.setW(6);
        Assert.assertArrayEquals(new Integer[] {8}, sut.getRawComponents());
        
        sut = new IntVector(8, 77);
        Assert.assertArrayEquals(new Integer[] {8, 77}, sut.getRawComponents());
        sut.setW(6);
        Assert.assertArrayEquals(new Integer[] {8, 77}, sut.getRawComponents());
        
        sut = new IntVector(8, 77, 1);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1}, sut.getRawComponents());
        sut.setW(6);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1}, sut.getRawComponents());
        
        sut = new IntVector(8, 77, 1, 3);
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
        TestUtils.assertNoException(() ->
                sut.setW(null));
        Assert.assertArrayEquals(new Integer[] {8, 77, 1, 3}, sut.getRawComponents());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#copy(BaseComponent, BaseComponent)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testStaticCopy() throws Exception {
        IntVector copy;
        
        //standard
        
        sut = new IntVector(new int[] {9});
        copy = new IntVector(1);
        IntVector.copy(sut, copy);
        Assert.assertEquals(
                new IntVector(new int[] {9}), copy);
        
        sut = new IntVector(9, 2);
        copy = new IntVector(2);
        IntVector.copy(sut, copy);
        Assert.assertEquals(
                new IntVector(9, 2), copy);
        
        sut = new IntVector(9, 2, -5);
        copy = new IntVector(3);
        IntVector.copy(sut, copy);
        Assert.assertEquals(
                new IntVector(9, 2, -5), copy);
        
        sut = new IntVector(9, 2, -5, 11);
        copy = new IntVector(4);
        IntVector.copy(sut, copy);
        Assert.assertEquals(
                new IntVector(9, 2, -5, 11), copy);
        
        sut = new IntVector();
        copy = new IntVector();
        IntVector.copy(sut, copy);
        Assert.assertEquals(
                new IntVector(), copy);
        
        //invalid
        
        sut = new IntVector(9, 2, -5, 11);
        final IntVector copy1 = new IntVector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                IntVector.copy(sut, copy1));
        
        sut = new IntVector(9, 2, -5);
        final IntVector copy2 = new IntVector(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                IntVector.copy(sut, copy2));
        
        sut = new IntVector(9, 2, -5);
        final BigVector copy3 = new BigVector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy3), () ->
                IntVector.copy(sut, copy3));
        
        sut = new IntVector(9, 2, -5);
        final BigMatrix copy4 = new BigMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy4), () ->
                IntVector.copy(sut, copy4));
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(NullPointerException.class, () ->
                IntVector.copy(sut, null));
        
        sut = new IntVector(9, 2, -5);
        TestUtils.assertException(NullPointerException.class, () ->
                IntVector.copy(null, sut));
    }
    
    /**
     * JUnit test of createInstance.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#createInstance(int)
     */
    @Test
    public void testCreateInstance() throws Exception {
        //standard
        Assert.assertEquals(
                new IntVector(), IntVector.createInstance(0));
        Assert.assertEquals(
                new IntVector(new int[] {0}), IntVector.createInstance(1));
        Assert.assertEquals(
                new IntVector(0, 0), IntVector.createInstance(2));
        Assert.assertEquals(
                new IntVector(0, 0, 0), IntVector.createInstance(3));
        Assert.assertEquals(
                new IntVector(0, 0, 0, 0), IntVector.createInstance(4));
        
        //invalid
        Assert.assertEquals(
                new IntVector(), IntVector.createInstance(-1));
    }
    
    /**
     * JUnit test of identity.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#identity(int)
     */
    @Test
    public void testIdentity() throws Exception {
        //standard
        Assert.assertEquals(
                new IntVector(), IntVector.identity(0));
        Assert.assertEquals(
                new IntVector(new int[] {1}), IntVector.identity(1));
        Assert.assertEquals(
                new IntVector(1, 1), IntVector.identity(2));
        Assert.assertEquals(
                new IntVector(1, 1, 1), IntVector.identity(3));
        Assert.assertEquals(
                new IntVector(1, 1, 1, 1), IntVector.identity(4));
        
        //invalid
        Assert.assertEquals(
                new IntVector(), IntVector.identity(-1));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see IntVector#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(
                new IntVector(), IntVector.origin(0));
        Assert.assertEquals(
                new IntVector(new int[] {0}), IntVector.origin(1));
        Assert.assertEquals(
                new IntVector(0, 0), IntVector.origin(2));
        Assert.assertEquals(
                new IntVector(0, 0, 0), IntVector.origin(3));
        Assert.assertEquals(
                new IntVector(0, 0, 0, 0), IntVector.origin(4));
        
        //invalid
        Assert.assertEquals(
                new IntVector(), IntVector.origin(-1));
    }
    
}
