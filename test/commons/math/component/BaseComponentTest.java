/*
 * File:    BaseComponentTest.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import commons.math.component.handler.error.ComponentErrorHandlerInterface;
import commons.math.component.handler.error.ComponentErrorHandlerProvider;
import commons.math.component.handler.math.BigComponentMathHandler;
import commons.math.component.handler.math.ComponentMathHandlerInterface;
import commons.math.component.handler.math.DoubleComponentMathHandler;
import commons.math.component.handler.math.IntComponentMathHandler;
import commons.math.component.handler.math.RawComponentMathHandler;
import commons.math.component.matrix.BigMatrix;
import commons.math.component.matrix.IntMatrix;
import commons.math.component.matrix.Matrix;
import commons.math.component.matrix.Matrix2;
import commons.math.component.matrix.Matrix3;
import commons.math.component.matrix.Matrix4;
import commons.math.component.matrix.RawMatrix;
import commons.math.component.vector.BigVector;
import commons.math.component.vector.IntVector;
import commons.math.component.vector.RawVector;
import commons.math.component.vector.Vector;
import commons.math.component.vector.Vector2;
import commons.math.component.vector.Vector3;
import commons.math.component.vector.Vector4;
import commons.test.TestAccess;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of BaseComponent.
 *
 * @see BaseComponent
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BaseComponent.class})
public class BaseComponentTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BaseComponentTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private TestComponent sut;
    
    
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
     * @see BaseComponent#errorHandler
     */
    @Test
    public void testConstants() throws Exception {
        //static
        Object errorHandler = TestAccess.getFieldValue(BaseComponent.class, "errorHandler");
        Assert.assertNotNull(errorHandler);
        Assert.assertEquals(ComponentErrorHandlerProvider.getErrorHandler(), errorHandler);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#BaseComponent(Class, ComponentMathHandlerInterface)
     */
    @Test
    public void testConstructors() throws Exception {
        //TestComponent
        BaseComponent<?, ?> c0 = new TestComponent();
        Assert.assertNotNull(c0);
        Assert.assertTrue(c0 instanceof BaseComponent);
        Assert.assertTrue(c0 instanceof ComponentInterface);
        Assert.assertEquals(Number.class, c0.getType());
        Assert.assertEquals(TestComponent.class, c0.getComponentClass());
        Assert.assertTrue(c0.getHandler() instanceof RawComponentMathHandler);
        
        //Vector
        BaseComponent<?, ?> c1 = new Vector();
        Assert.assertNotNull(c1);
        Assert.assertTrue(c1 instanceof BaseComponent);
        Assert.assertTrue(c1 instanceof ComponentInterface);
        Assert.assertEquals(Double.class, c1.getType());
        Assert.assertEquals(Vector.class, c1.getComponentClass());
        Assert.assertTrue(c1.getHandler() instanceof DoubleComponentMathHandler);
        
        //IntMatrix
        BaseComponent<?, ?> c2 = new IntMatrix();
        Assert.assertNotNull(c2);
        Assert.assertTrue(c2 instanceof BaseComponent);
        Assert.assertTrue(c2 instanceof ComponentInterface);
        Assert.assertEquals(Integer.class, c2.getType());
        Assert.assertEquals(IntMatrix.class, c2.getComponentClass());
        Assert.assertTrue(c2.getHandler() instanceof IntComponentMathHandler);
        
        //BigVector
        BaseComponent<?, ?> c3 = new BigVector();
        Assert.assertNotNull(c3);
        Assert.assertTrue(c3 instanceof BaseComponent);
        Assert.assertTrue(c3 instanceof ComponentInterface);
        Assert.assertEquals(BigDecimal.class, c3.getType());
        Assert.assertEquals(BigVector.class, c3.getComponentClass());
        Assert.assertTrue(c3.getHandler() instanceof BigComponentMathHandler);
        
        //RawMatrix
        BaseComponent<?, ?> c4 = new RawMatrix();
        Assert.assertNotNull(c4);
        Assert.assertTrue(c4 instanceof BaseComponent);
        Assert.assertTrue(c4 instanceof ComponentInterface);
        Assert.assertEquals(Number.class, c4.getType());
        Assert.assertEquals(RawMatrix.class, c4.getComponentClass());
        Assert.assertTrue(c4.getHandler() instanceof RawComponentMathHandler);
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        
        sut = new TestComponent(8.5, -1.944, 2.67, 8.13, 5.501);
        Assert.assertEquals("[8.5, -1.944, 2.67, 8.13, 5.501]", sut.toString());
        
        sut = new TestComponent(0, 8.5, -1.944, 2.67, 8.13, 5.501, 0);
        Assert.assertEquals("[0.0, 8.5, -1.944, 2.67, 8.13, 5.501, 0.0]", sut.toString());
        
        sut = new TestComponent(8.5);
        Assert.assertEquals("[8.5]", sut.toString());
        
        //empty
        
        sut = new TestComponent();
        Assert.assertEquals("[]", sut.toString());
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#equals(Object)
     */
    @SuppressWarnings({"AssertBetweenInconvertibleTypes", "SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals() throws Exception {
        BaseComponent<?, ?> other;
        sut = new TestComponent(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new TestComponent(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new TestComponent(0, 8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new TestComponent(8.5, -1.944, 2.67, 8, 5.501, 0);
        Assert.assertFalse(sut.equals(other));
        
        other = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertTrue(sut.equals(other));
        
        other = new RawVector(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.0000000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.equals(other));
        
        other = new Vector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new BigVector(8.5, -1.944, 2.67, 8, 5.501);
        Assert.assertFalse(sut.equals(other));
        
        other = new IntVector(8, -1, 2, 8, 5);
        Assert.assertFalse(sut.equals(other));
        
        sut = new TestComponent(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        other = new Matrix(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        Assert.assertFalse(sut.equals(other));
        
        sut = new TestComponent(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        other = new RawMatrix(8.5, -1.944, 2.67, 8, 5.501, 1.9, 4.6, 5.13, 0.78);
        Assert.assertFalse(sut.equals(other));
        
        Assert.assertTrue(new TestComponent().equals(new TestComponent()));
        
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
     * @see BaseComponent#dimensionalityEqual(ComponentInterface)
     */
    @Test
    public void testDimensionalityEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new TestComponent(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.dimensionalityEqual(other));
        
        other = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new Vector(5.501);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertFalse(sut.dimensionalityEqual(other));
        
        other = new IntVector(5, 8);
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
        
        Assert.assertTrue(new TestComponent().dimensionalityEqual(new TestComponent()));
        
        //invalid
        
        Assert.assertFalse(sut.dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of lengthEqual.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#lengthEqual(ComponentInterface)
     */
    @Test
    public void testLengthEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new TestComponent(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(sut.lengthEqual(other));
        
        other = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new Vector(5.501);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertFalse(sut.lengthEqual(other));
        
        other = new IntVector(5, 8);
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
        
        sut = new TestComponent(8.5, -1.944, 2.67, 8.13);
        other = new Matrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertTrue(sut.lengthEqual(other));
        
        Assert.assertTrue(new TestComponent().lengthEqual(new TestComponent()));
        
        //invalid
        
        Assert.assertFalse(sut.lengthEqual(null));
    }
    
    /**
     * JUnit test of componentTypeEqual.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#componentTypeEqual(ComponentInterface)
     */
    @Test
    public void testComponentTypeEqual() throws Exception {
        BaseComponent<?, ?> other;
        sut = new TestComponent(8.5, -1.944, 2.67, 8, 5.501);
        
        //standard
        
        other = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(5, 8, 2, -1, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new Vector(5.501);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntVector(5, 8);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        other = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertTrue(sut.componentTypeEqual(other));
        
        other = new IntMatrix(new int[] {6});
        Assert.assertFalse(sut.componentTypeEqual(other));
        
        Assert.assertTrue(new TestComponent().componentTypeEqual(new TestComponent()));
        
        //invalid
        
        Assert.assertFalse(sut.componentTypeEqual(null));
    }
    
    /**
     * JUnit test of cloned.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#cloned()
     */
    @Test
    public void testCloned() throws Exception {
        TestUtils.assertMethodExists(
                BaseComponent.class, "cloned");
    }
    
    /**
     * JUnit test of emptyCopy.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#emptyCopy()
     */
    @Test
    public void testEmptyCopy() throws Exception {
        TestUtils.assertMethodExists(
                BaseComponent.class, "emptyCopy");
    }
    
    /**
     * JUnit test of createNewInstance.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#createNewInstance(int)
     */
    @Test
    public void testCreateNewInstance() throws Exception {
        TestUtils.assertMethodExists(
                BaseComponent.class, "createNewInstance", int.class);
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        TestComponent reversed;
        
        //standard
        
        sut = new TestComponent(8.5, -1.944, 2.67, 8, 5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {5.501, 8, 2.67, -1.944, 8.5});
        
        sut = new TestComponent(5.501, 8, 2.67, -1.944, 8.5);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {8.5, -1.944, 2.67, 8, 5.501});
        
        sut = new TestComponent(0, 1, 0, 1);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {1, 0, 1, 0});
        
        sut = new TestComponent(5.501);
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {5.501});
        
        sut = new TestComponent();
        reversed = sut.reverse();
        Assert.assertNotNull(reversed);
        Assert.assertNotSame(sut, reversed);
        Assert.assertEquals(sut.getDimensionality(), reversed.getDimensionality());
        TestUtils.assertArrayEquals(
                reversed.getRawComponents(),
                new Number[] {});
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#distance(BaseComponent)
     */
    @Test
    public void testDistance() throws Exception {
        TestComponent other;
        
        //standard
        
        sut = new TestComponent(8.5);
        other = new TestComponent(9.9);
        Assert.assertEquals(1.4, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent(8.5, 1.5);
        other = new TestComponent(9.9, 6);
        Assert.assertEquals(4.712748667179271, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        other = new TestComponent(9.9, 6, 0.514);
        Assert.assertEquals(7.258126480022238, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(9.545700602889239, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent();
        other = new TestComponent();
        Assert.assertEquals(0.0, sut.distance(other).doubleValue(), TestUtils.DELTA);
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent other1 = new TestComponent(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.distance(other1));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final TestComponent other2 = new TestComponent(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.distance(other2));
        
        sut = new TestComponent(8.5, 1.5);
        final TestComponent other3 = new TestComponent();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.distance(other3));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.distance(null));
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#midpoint(BaseComponent)
     */
    @Test
    public void testMidpoint() throws Exception {
        TestComponent other;
        
        //standard
        
        sut = new TestComponent(8.5);
        other = new TestComponent(9.9);
        Assert.assertEquals(
                new TestComponent(9.2), sut.midpoint(other));
        
        sut = new TestComponent(8.5, 1.5);
        other = new TestComponent(9.9, 6);
        Assert.assertEquals(
                new TestComponent(9.2, 3.75), sut.midpoint(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        other = new TestComponent(9.9, 6, 0.514);
        Assert.assertEquals(
                new TestComponent(9.2, 3.75, -2.246), sut.midpoint(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new TestComponent(9.2, 3.75, -2.246, 8.0), sut.midpoint(other));
        
        sut = new TestComponent();
        other = new TestComponent();
        Assert.assertEquals(
                new TestComponent(), sut.midpoint(other));
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent other1 = new TestComponent(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.midpoint(other1));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final TestComponent other2 = new TestComponent(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.midpoint(other2));
        
        sut = new TestComponent(8.5, 1.5);
        final TestComponent other3 = new TestComponent();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.midpoint(other3));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.midpoint(null));
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#average(List)
     * @see BaseComponent#average(BaseComponent...)
     */
    @Test
    public void testAverage() throws Exception {
        TestComponent other1;
        TestComponent other2;
        TestComponent other3;
        TestComponent other4;
        
        //standard
        
        sut = new TestComponent(8.5);
        other1 = new TestComponent(9.9);
        other2 = new TestComponent(1.8);
        other3 = new TestComponent(11.7);
        Assert.assertEquals(
                new TestComponent(7.975), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new TestComponent(7.975), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new TestComponent(8.5, 1.5);
        other1 = new TestComponent(9.9, 6);
        other2 = new TestComponent(1.8, 4.77);
        Assert.assertEquals(
                new TestComponent(6.733333333333, 4.09), sut.average(other1, other2));
        Assert.assertEquals(
                new TestComponent(6.733333333333, 4.09), sut.average(Arrays.asList(other1, other2)));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        other1 = new TestComponent(9.9, 6, 0.514);
        Assert.assertEquals(
                new TestComponent(9.2, 3.75, -2.246), sut.average(other1));
        Assert.assertEquals(
                new TestComponent(9.2, 3.75, -2.246), sut.average(Collections.singletonList(other1)));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other1 = new TestComponent(9.9, 6, 0.514, 4.9);
        other2 = new TestComponent(1.8, 4.77, 0.514, 2.895);
        other3 = new TestComponent(11.7, 0.447, 7.16, 8);
        Assert.assertEquals(
                new TestComponent(7.975, 3.17925, 0.7955, 6.72375), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new TestComponent(7.975, 3.17925, 0.7955, 6.72375), sut.average(Arrays.asList(other1, other2, other3)));
        
        sut = new TestComponent();
        other1 = new TestComponent();
        other2 = new TestComponent();
        other3 = new TestComponent();
        Assert.assertEquals(
                new TestComponent(), sut.average(other1, other2, other3));
        Assert.assertEquals(
                new TestComponent(), sut.average(Arrays.asList(other1, other2, other3)));
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent otherF11 = new TestComponent(9.9, 6, 0.514, 4.9);
        final TestComponent otherF12 = new TestComponent(1.8, 4.77, 0.514, 2.895);
        final TestComponent otherF13 = new TestComponent(11.7, 0.447, 7.16);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(otherF11, otherF12, otherF13));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF13), () ->
                sut.average(Arrays.asList(otherF11, otherF12, otherF13)));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent otherF21 = new TestComponent(9.9, 6, 0.514, 4.9);
        final TestComponent otherF22 = new TestComponent(1.8, 4.77, 0.514, 2.895, 9.11);
        final TestComponent otherF23 = new TestComponent(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(otherF21, otherF22, otherF23));
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, otherF22), () ->
                sut.average(Arrays.asList(otherF21, otherF22, otherF23)));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent otherF31 = new TestComponent(9.9, 6, 0.514, 4.9);
        final TestComponent otherF33 = new TestComponent(11.7, 0.447, 7.16, 8);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(otherF31, null, otherF33));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(otherF31, null, otherF33)));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.average(Arrays.asList(null, null, null)));
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        
        sut = new TestComponent(8.5);
        Assert.assertEquals(8.5, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent(8.5, 1.5);
        Assert.assertEquals(10.0, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        Assert.assertEquals(4.994, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(16.094, sut.sum().doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent();
        Assert.assertEquals(0, sut.sum().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        
        sut = new TestComponent(8.5);
        Assert.assertEquals(72.25, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent(8.5, 1.5);
        Assert.assertEquals(74.5, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        Assert.assertEquals(99.560036, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(222.770036, sut.squareSum().doubleValue(), TestUtils.DELTA);
        
        sut = new TestComponent();
        Assert.assertEquals(0, sut.squareSum().doubleValue(), TestUtils.DELTA);
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#plus(BaseComponent)
     */
    @Test
    public void testPlus() throws Exception {
        TestComponent other;
        
        //standard
        
        sut = new TestComponent(8.5);
        other = new TestComponent(9.9);
        Assert.assertEquals(
                new TestComponent(18.4), sut.plus(other));
        
        sut = new TestComponent(8.5, 1.5);
        other = new TestComponent(9.9, 6);
        Assert.assertEquals(
                new TestComponent(18.4, 7.5), sut.plus(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        other = new TestComponent(9.9, 6, 0.514);
        Assert.assertEquals(
                new TestComponent(18.4, 7.5, -4.492), sut.plus(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new TestComponent(18.4, 7.5, -4.492, 16.0), sut.plus(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(1, 1, 1, 1);
        Assert.assertEquals(
                new TestComponent(9.5, 2.5, -4.006, 12.1), sut.plus(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(0, 0, 0, 0);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1), sut.plus(other));
        
        sut = new TestComponent();
        other = new TestComponent();
        Assert.assertEquals(
                new TestComponent(), sut.plus(other));
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent other1 = new TestComponent(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.plus(other1));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final TestComponent other2 = new TestComponent(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.plus(other2));
        
        sut = new TestComponent(8.5, 1.5);
        final TestComponent other3 = new TestComponent();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.plus(other3));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.plus(null));
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#minus(BaseComponent)
     */
    @Test
    public void testMinus() throws Exception {
        TestComponent other;
        
        //standard
        
        sut = new TestComponent(8.5);
        other = new TestComponent(9.9);
        Assert.assertEquals(
                new TestComponent(-1.4), sut.minus(other));
        
        sut = new TestComponent(8.5, 1.5);
        other = new TestComponent(9.9, 6);
        Assert.assertEquals(
                new TestComponent(-1.4, -4.5), sut.minus(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        other = new TestComponent(9.9, 6, 0.514);
        Assert.assertEquals(
                new TestComponent(-1.4, -4.5, -5.52), sut.minus(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new TestComponent(-1.4, -4.5, -5.52, 6.2), sut.minus(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(1, 1, 1, 1);
        Assert.assertEquals(
                new TestComponent(7.5, 0.5, -6.006, 10.1), sut.minus(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(0, 0, 0, 0);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1), sut.minus(other));
        
        sut = new TestComponent();
        other = new TestComponent();
        Assert.assertEquals(
                new TestComponent(), sut.minus(other));
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent other1 = new TestComponent(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.minus(other1));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final TestComponent other2 = new TestComponent(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.minus(other2));
        
        sut = new TestComponent(8.5, 1.5);
        final TestComponent other3 = new TestComponent();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.minus(other3));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.minus(null));
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#times(BaseComponent)
     */
    @Test
    public void testTimes() throws Exception {
        TestComponent other;
        
        //standard
        
        sut = new TestComponent(8.5);
        other = new TestComponent(9.9);
        Assert.assertEquals(
                new TestComponent(84.15), sut.times(other));
        
        sut = new TestComponent(8.5, 1.5);
        other = new TestComponent(9.9, 6);
        Assert.assertEquals(
                new TestComponent(84.15, 9.0), sut.times(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        other = new TestComponent(9.9, 6, 0.514);
        Assert.assertEquals(
                new TestComponent(84.15, 9.0, -2.573084), sut.times(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new TestComponent(84.15, 9.0, -2.573084, 54.39), sut.times(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(1, 1, 1, 1);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1), sut.times(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(0, 0, 0, 0);
        Assert.assertEquals(
                new TestComponent(0, 0, 0, 0), sut.times(other));
        
        sut = new TestComponent();
        other = new TestComponent();
        Assert.assertEquals(
                new TestComponent(), sut.times(other));
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent other1 = new TestComponent(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.times(other1));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final TestComponent other2 = new TestComponent(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.times(other2));
        
        sut = new TestComponent(8.5, 1.5);
        final TestComponent other3 = new TestComponent();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.times(other3));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.times(null));
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#scale(Number)
     */
    @Test
    public void testScale() throws Exception {
        //standard
        
        sut = new TestComponent(8.5);
        Assert.assertEquals(
                new TestComponent(42.5), sut.scale(5));
        
        sut = new TestComponent(8.5, 1.5);
        Assert.assertEquals(
                new TestComponent(8.5799, 1.5141), sut.scale(1.0094));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new TestComponent(139.57, 24.63, -82.19852), sut.scale(16.42));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new TestComponent(-22.7545, -4.0155, 13.401062, -29.7147), sut.scale(-2.677));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1), sut.scale(1));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new TestComponent(-8.5, -1.5, 5.006, -11.1), sut.scale(-1));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new TestComponent(0, 0, 0, 0), sut.scale(0));
        
        sut = new TestComponent();
        Assert.assertEquals(
                new TestComponent(), sut.scale(7.1));
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#dividedBy(BaseComponent)
     */
    @Test
    public void testDividedBy() throws Exception {
        TestComponent other;
        
        //standard
        
        sut = new TestComponent(8.5);
        other = new TestComponent(9.9);
        Assert.assertEquals(
                new TestComponent(0.858585858586), sut.dividedBy(other));
        
        sut = new TestComponent(8.5, 1.5);
        other = new TestComponent(9.9, 6);
        Assert.assertEquals(
                new TestComponent(0.858585858586, 0.25), sut.dividedBy(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        other = new TestComponent(9.9, 6, 0.514);
        Assert.assertEquals(
                new TestComponent(0.858585858586, 0.25, -9.739299610895), sut.dividedBy(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(9.9, 6, 0.514, 4.9);
        Assert.assertEquals(
                new TestComponent(0.858585858586, 0.25, -9.739299610895, 2.265306122449), sut.dividedBy(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        other = new TestComponent(1, 1, 1, 1);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1), sut.dividedBy(other));
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent other0 = new TestComponent(0, 0, 0, 0);
        TestUtils.assertException(ArithmeticException.class, "Attempted to divide by zero", () ->
                sut.dividedBy(other0));
        
        sut = new TestComponent();
        other = new TestComponent();
        Assert.assertEquals(
                new TestComponent(), sut.dividedBy(other));
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent other1 = new TestComponent(9.9, 6, 0.514);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other1), () ->
                sut.dividedBy(other1));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final TestComponent other2 = new TestComponent(9.9, 6, 0.514, 4.9);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other2), () ->
                sut.dividedBy(other2));
        
        sut = new TestComponent(8.5, 1.5);
        final TestComponent other3 = new TestComponent();
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, other3), () ->
                sut.dividedBy(other3));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.dividedBy(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        
        sut = new TestComponent(8.5);
        Assert.assertEquals(
                new TestComponent(9.0), sut.round());
        
        sut = new TestComponent(8.5, 1.5);
        Assert.assertEquals(
                new TestComponent(9.0, 2.0), sut.round());
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        Assert.assertEquals(
                new TestComponent(9.0, 2.0, -5.0), sut.round());
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        Assert.assertEquals(
                new TestComponent(9.0, 2.0, -5.0, 11.0), sut.round());
        
        sut = new TestComponent();
        Assert.assertEquals(
                new TestComponent(), sut.round());
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#copy(BaseComponent)
     */
    @Test
    public void testCopy() throws Exception {
        TestComponent copy;
        
        //standard
        
        sut = new TestComponent(8.5);
        copy = new TestComponent(1);
        sut.copy(copy);
        Assert.assertEquals(
                new TestComponent(8.5), copy);
        
        sut = new TestComponent(8.5, 1.5);
        copy = new TestComponent(2);
        sut.copy(copy);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5), copy);
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        copy = new TestComponent(3);
        sut.copy(copy);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006), copy);
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        copy = new TestComponent(4);
        sut.copy(copy);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new TestComponent();
        copy = new TestComponent();
        sut.copy(copy);
        Assert.assertEquals(
                new TestComponent(), copy);
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent copy1 = new TestComponent(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                sut.copy(copy1));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final TestComponent copy2 = new TestComponent(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                sut.copy(copy2));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                sut.copy(null));
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#copyMeta(BaseComponent)
     */
    @Test
    public void testCopyMeta() throws Exception {
        TestUtils.assertMethodExists(
                BaseComponent.class, "copyMeta", BaseComponent.class);
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        //standard
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        sut.redim(3);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006), sut);
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        sut.redim(5);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1, 0), sut);
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        sut.redim(4);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1), sut);
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        sut.redim(1);
        Assert.assertEquals(
                new TestComponent(8.5), sut);
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        sut.redim(0);
        Assert.assertEquals(
                new TestComponent(), sut);
        
        sut = new TestComponent();
        sut.redim(3);
        Assert.assertEquals(
                new TestComponent(0, 0, 0), sut);
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        sut.redim(-1);
        Assert.assertEquals(
                new TestComponent(), sut);
        
        sut = Mockito.spy(TestComponent.class);
        sut.setComponents(new Number[] {8.5, 1.5, -5.006, 11.1});
        Mockito.when(sut.isResizeable()).thenReturn(false);
        Assert.assertFalse(sut.isResizeable());
        sut.redim(3);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1), sut);
    }
    
    /**
     * JUnit test of dimensionalityToLength.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#dimensionalityToLength(int)
     * @see BaseComponent#dimensionalityToLength()
     */
    @Test
    public void testDimensionalityToLength() throws Exception {
        sut = new TestComponent(8.5, 1.5, -5.006);
        
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
     * @see BaseComponent#lengthToDimensionality(int)
     * @see BaseComponent#lengthToDimensionality()
     */
    @Test
    public void testLengthToDimensionality() throws Exception {
        sut = new TestComponent(8.5, 1.5, -5.006);
        
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
     * @see BaseComponent#calculateDimensionality()
     */
    @Test
    public void testCalculateDimensionality() throws Exception {
        sut = new TestComponent(8.5, 1.5, -5.006);
        
        //standard
        Assert.assertEquals(3, sut.getDimensionality());
        TestAccess.setFieldValue(sut, "dimensionality", 5);
        Assert.assertEquals(5, sut.getDimensionality());
        sut.calculateDimensionality();
        Assert.assertEquals(3, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        TestUtils.assertMethodExists(
                BaseComponent.class, "getRawComponents");
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        TestComponent component;
        
        //standard
        
        component = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.5, 1.5, -5.006});
        
        component = new TestComponent(8.5, 1.5);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.5, 1.5});
        
        component = new TestComponent();
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {});
        
        component = new TestComponent(8.500000000001, 1.499999999996, -5.005999999999);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.500000000001, 1.499999999996, -5.005999999999});
        
        component = new TestComponent(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.5, 1.5, -5.006});
    }
    
    /**
     * JUnit test of getRaw.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getRaw(int)
     */
    @Test
    public void testGetRaw() throws Exception {
        //standard
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        Assert.assertEquals(8.5, sut.getRaw(0));
        Assert.assertEquals(1.5, sut.getRaw(1));
        Assert.assertEquals(-5.006, sut.getRaw(2));
        
        sut = new TestComponent(8.5, 1.5);
        Assert.assertEquals(8.5, sut.getRaw(0));
        Assert.assertEquals(1.5, sut.getRaw(1));
        
        sut = new TestComponent(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(8.500000000001, sut.getRaw(0));
        Assert.assertEquals(1.499999999996, sut.getRaw(1));
        Assert.assertEquals(-5.005999999999, sut.getRaw(2));
        
        sut = new TestComponent(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(8.5000000000000000000000001, sut.getRaw(0));
        Assert.assertEquals(1.49999999999999999999996, sut.getRaw(1));
        Assert.assertEquals(-5.0059999999999999999999, sut.getRaw(2));
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.getRaw(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.getRaw(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.getRaw(-1));
        
        sut = new TestComponent();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.getRaw(0));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#get(int)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        Assert.assertEquals(8.5, sut.get(0));
        Assert.assertEquals(1.5, sut.get(1));
        Assert.assertEquals(-5.006, sut.get(2));
        
        sut = new TestComponent(8.5, 1.5);
        Assert.assertEquals(8.5, sut.get(0));
        Assert.assertEquals(1.5, sut.get(1));
        
        sut = new TestComponent(8.500000000001, 1.499999999996, -5.005999999999);
        Assert.assertEquals(8.500000000001, sut.get(0));
        Assert.assertEquals(1.499999999996, sut.get(1));
        Assert.assertEquals(-5.005999999999, sut.get(2));
        
        sut = new TestComponent(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        Assert.assertEquals(8.5, sut.get(0));
        Assert.assertEquals(1.5, sut.get(1));
        Assert.assertEquals(-5.006, sut.get(2));
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.get(3));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.get(8));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.get(-1));
        
        sut = new TestComponent();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.get(0));
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        
        sut = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new TestComponent(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getDimensionality());
        
        sut = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(4, sut.getDimensionality());
        
        sut = new TestComponent(5.501);
        Assert.assertEquals(1, sut.getDimensionality());
        
        sut = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(6, sut.getDimensionality());
        
        sut = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(2, sut.getDimensionality());
        
        sut = new TestComponent();
        Assert.assertEquals(0, sut.getDimensionality());
    }
    
    /**
     * JUnit test of getLength.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getLength()
     */
    @Test
    public void testGetLength() throws Exception {
        //standard
        
        sut = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new TestComponent(5, 8, 2, -1, 8);
        Assert.assertEquals(5, sut.getLength());
        
        sut = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(4, sut.getLength());
        
        sut = new TestComponent(5.501);
        Assert.assertEquals(1, sut.getLength());
        
        sut = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(6, sut.getLength());
        
        sut = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(2, sut.getLength());
        
        sut = new TestComponent();
        Assert.assertEquals(0, sut.getLength());
    }
    
    /**
     * JUnit test of getComponentClass.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getComponentClass()
     */
    @Test
    public void testGetComponentClass() throws Exception {
        BaseComponent<?, ?> component;
        
        //standard
        
        component = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(TestComponent.class, component.getComponentClass());
        
        component = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(Vector.class, component.getComponentClass());
        
        component = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(BigVector.class, component.getComponentClass());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertEquals(TestComponent.class, component.getComponentClass());
        
        component = new IntVector(5, 8, 2, -1, 8);
        Assert.assertEquals(IntVector.class, component.getComponentClass());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(TestComponent.class, component.getComponentClass());
        
        component = new Vector(5.501);
        Assert.assertEquals(Vector.class, component.getComponentClass());
        
        component = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(RawVector.class, component.getComponentClass());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(TestComponent.class, component.getComponentClass());
        
        component = new IntVector(5, 8);
        Assert.assertEquals(IntVector.class, component.getComponentClass());
        
        component = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(Matrix.class, component.getComponentClass());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(RawMatrix.class, component.getComponentClass());
        
        component = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(BigMatrix.class, component.getComponentClass());
        
        component = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(Matrix.class, component.getComponentClass());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(RawMatrix.class, component.getComponentClass());
        
        component = new IntMatrix(new int[] {6});
        Assert.assertEquals(IntMatrix.class, component.getComponentClass());
    }
    
    /**
     * JUnit test of getType.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getType()
     */
    @Test
    public void testGetType() throws Exception {
        BaseComponent<?, ?> component;
        
        //standard
        
        component = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(Number.class, component.getType());
        
        component = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(Double.class, component.getType());
        
        component = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(BigDecimal.class, component.getType());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertEquals(Number.class, component.getType());
        
        component = new IntVector(5, 8, 2, -1, 8);
        Assert.assertEquals(Integer.class, component.getType());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(Number.class, component.getType());
        
        component = new Vector(5.501);
        Assert.assertEquals(Double.class, component.getType());
        
        component = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(Number.class, component.getType());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(Number.class, component.getType());
        
        component = new IntVector(5, 8);
        Assert.assertEquals(Integer.class, component.getType());
        
        component = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(Double.class, component.getType());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(Number.class, component.getType());
        
        component = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(BigDecimal.class, component.getType());
        
        component = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(Double.class, component.getType());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(Number.class, component.getType());
        
        component = new IntMatrix(new int[] {6});
        Assert.assertEquals(Integer.class, component.getType());
    }
    
    /**
     * JUnit test of getHandler.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getHandler()
     */
    @Test
    public void testGetHandler() throws Exception {
        BaseComponent<?, ?> component;
        
        //standard
        
        component = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.getHandler() instanceof RawComponentMathHandler);
        
        component = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.getHandler() instanceof DoubleComponentMathHandler);
        
        component = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.getHandler() instanceof BigComponentMathHandler);
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(component.getHandler() instanceof RawComponentMathHandler);
        
        component = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(component.getHandler() instanceof IntComponentMathHandler);
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.getHandler() instanceof RawComponentMathHandler);
        
        component = new Vector(5.501);
        Assert.assertTrue(component.getHandler() instanceof DoubleComponentMathHandler);
        
        component = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertTrue(component.getHandler() instanceof RawComponentMathHandler);
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(component.getHandler() instanceof RawComponentMathHandler);
        
        component = new IntVector(5, 8);
        Assert.assertTrue(component.getHandler() instanceof IntComponentMathHandler);
        
        component = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.getHandler() instanceof DoubleComponentMathHandler);
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.getHandler() instanceof RawComponentMathHandler);
        
        component = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.getHandler() instanceof BigComponentMathHandler);
        
        component = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.getHandler() instanceof DoubleComponentMathHandler);
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertTrue(component.getHandler() instanceof RawComponentMathHandler);
        
        component = new IntMatrix(new int[] {6});
        Assert.assertTrue(component.getHandler() instanceof IntComponentMathHandler);
    }
    
    /**
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        BaseComponent<?, ?> component;
        ComponentErrorHandlerInterface errorHandler = ComponentErrorHandlerProvider.getErrorHandler();
        
        //standard
        
        component = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new IntVector(5, 8, 2, -1, 8);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new Vector(5.501);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new IntVector(5, 8);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(errorHandler, component.getErrorHandler());
        
        component = new IntMatrix(new int[] {6});
        Assert.assertEquals(errorHandler, component.getErrorHandler());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getName()
     */
    @Test
    public void testGetName() throws Exception {
        BaseComponent<?, ?> component;
        
        //standard
        
        component = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Test Component", component.getName());
        
        component = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Vector", component.getName());
        
        component = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Big Vector", component.getName());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertEquals("Test Component", component.getName());
        
        component = new IntVector(5, 8, 2, -1, 8);
        Assert.assertEquals("Integer Vector", component.getName());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals("Test Component", component.getName());
        
        component = new Vector(5.501);
        Assert.assertEquals("Vector", component.getName());
        
        component = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals("Raw Vector", component.getName());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals("Test Component", component.getName());
        
        component = new IntVector(5, 8);
        Assert.assertEquals("Integer Vector", component.getName());
        
        component = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Matrix", component.getName());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Raw Matrix", component.getName());
        
        component = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Big Matrix", component.getName());
        
        component = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Matrix", component.getName());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals("Raw Matrix", component.getName());
        
        component = new IntMatrix(new int[] {6});
        Assert.assertEquals("Integer Matrix", component.getName());
    }
    
    /**
     * JUnit test of getNamePlural.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getNamePlural()
     */
    @Test
    public void testGetNamePlural() throws Exception {
        BaseComponent<?, ?> component;
        
        //standard
        
        component = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Test Components", component.getNamePlural());
        
        component = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Vectors", component.getNamePlural());
        
        component = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Big Vectors", component.getNamePlural());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertEquals("Test Components", component.getNamePlural());
        
        component = new IntVector(5, 8, 2, -1, 8);
        Assert.assertEquals("Integer Vectors", component.getNamePlural());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals("Test Components", component.getNamePlural());
        
        component = new Vector(5.501);
        Assert.assertEquals("Vectors", component.getNamePlural());
        
        component = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals("Raw Vectors", component.getNamePlural());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals("Test Components", component.getNamePlural());
        
        component = new IntVector(5, 8);
        Assert.assertEquals("Integer Vectors", component.getNamePlural());
        
        component = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Matrices", component.getNamePlural());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Raw Matrices", component.getNamePlural());
        
        component = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Big Matrices", component.getNamePlural());
        
        component = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals("Matrices", component.getNamePlural());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals("Raw Matrices", component.getNamePlural());
        
        component = new IntMatrix(new int[] {6});
        Assert.assertEquals("Integer Matrices", component.getNamePlural());
    }
    
    /**
     * JUnit test of getPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#getPrecision()
     */
    @Test
    public void testGetPrecision() throws Exception {
        BaseComponent<?, ?> component;
        
        //standard
        
        component = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(BigDecimal.valueOf(1E-36), component.getPrecision());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new IntVector(5, 8, 2, -1, 8);
        Assert.assertEquals(1, component.getPrecision());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new Vector(5.501);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new IntVector(5, 8);
        Assert.assertEquals(1, component.getPrecision());
        
        component = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(BigDecimal.valueOf(1E-36), component.getPrecision());
        
        component = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertEquals(1E-12, component.getPrecision());
        
        component = new IntMatrix(new int[] {6});
        Assert.assertEquals(1, component.getPrecision());
    }
    
    /**
     * JUnit test of isResizeable.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#isResizeable()
     */
    @Test
    public void testIsResizeable() throws Exception {
        BaseComponent<?, ?> component;
        
        //standard
        
        component = new TestComponent(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.isResizeable());
        
        component = new Vector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.isResizeable());
        
        component = new BigVector(5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.isResizeable());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999, 2.6699999999999999999999999999999999, 8.1300000000000000000000006, 5.500999999999999999999999999999);
        Assert.assertTrue(component.isResizeable());
        
        component = new IntVector(5, 8, 2, -1, 8);
        Assert.assertTrue(component.isResizeable());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.isResizeable());
        
        component = new Vector(5.501);
        Assert.assertTrue(component.isResizeable());
        
        component = new RawVector(5.501, 8.13, 2.67, -1.944, 8.5, 8.11);
        Assert.assertTrue(component.isResizeable());
        
        component = new TestComponent(8.50000000000000000000000000000001, -1.943999999999999999999999999);
        Assert.assertTrue(component.isResizeable());
        
        component = new IntVector(5, 8);
        Assert.assertTrue(component.isResizeable());
        
        component = new Matrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.isResizeable());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.isResizeable());
        
        component = new BigMatrix(5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.isResizeable());
        
        component = new Matrix(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertTrue(component.isResizeable());
        
        component = new RawMatrix(5.501, 8.13, 2.67, -1.944);
        Assert.assertTrue(component.isResizeable());
        
        component = new IntMatrix(new int[] {6});
        Assert.assertTrue(component.isResizeable());
        
        component = new Vector2(5.501, 1.8);
        Assert.assertFalse(component.isResizeable());
        
        component = new Vector3(5.501, 1.8, 4.4);
        Assert.assertFalse(component.isResizeable());
        
        component = new Vector4(5.501, 1.8, 4.4, 0.8);
        Assert.assertFalse(component.isResizeable());
        
        component = new Matrix2(5.501, 8.13, 2.67, -1.944);
        Assert.assertFalse(component.isResizeable());
        
        component = new Matrix3(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67);
        Assert.assertFalse(component.isResizeable());
        
        component = new Matrix4(8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5, 5.501, 8.13, 2.67, -1.944, 8.5);
        Assert.assertFalse(component.isResizeable());
    }
    
    /**
     * JUnit test of setComponents.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#setComponents(Number[])
     */
    @Test
    public void testSetComponents() throws Exception {
        TestComponent component;
        Number[] newComponents;
        
        //standard
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {5.6, 6.7, 7.8, 8.9};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Number[] {5.6, 6.7, 7.8, 8.9});
        Assert.assertEquals(4, component.getDimensionality());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {5.6, 6.7, 7.8};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Number[] {5.6, 6.7, 7.8});
        Assert.assertEquals(3, component.getDimensionality());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {5.6, 6.7, 7.8, 8.9, 9.0};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Number[] {5.6, 6.7, 7.8, 8.9, 9.0});
        Assert.assertEquals(5, component.getDimensionality());
        
        component = new TestComponent(5.501, 2.67, -1.944, 8.5);
        newComponents = new Number[] {};
        Assert.assertEquals(4, component.getDimensionality());
        Assert.assertTrue(component.isResizeable());
        component.setComponents(newComponents);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Number[] {});
        Assert.assertEquals(0, component.getDimensionality());
        
        //invalid
        
        final Vector3 component1 = new Vector3(5.501, 2.67, -1.944);
        final Double[] newComponents1 = new Double[] {5.6, 6.7};
        Assert.assertFalse(component1.isResizeable());
        TestUtils.assertException(IndexOutOfBoundsException.class, component1.getErrorHandler().componentLengthNotEqualErrorMessage(newComponents1, component1.getLength()), () ->
                component1.setComponents(newComponents1));
        
        final TestComponent component2 = new TestComponent(5.501, 2.67, -1.944, 8.5);
        Assert.assertTrue(component2.isResizeable());
        TestUtils.assertException(NullPointerException.class, () ->
                component2.setComponents(null));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#set(int, Number)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertArrayEquals(
                sut.getComponents(),
                new Number[] {8.5, 1.5, -5.006});
        sut.set(0, 5.77);
        sut.set(1, 3);
        sut.set(2, 0.997);
        TestUtils.assertArrayEquals(
                sut.getComponents(),
                new Number[] {5.77, 3.0, 0.997});
        
        sut = new TestComponent(8.5, 1.5);
        TestUtils.assertArrayEquals(
                sut.getComponents(),
                new Number[] {8.5, 1.5});
        sut.set(0, 5.77);
        sut.set(1, 3);
        TestUtils.assertArrayEquals(
                sut.getComponents(),
                new Number[] {5.77, 3.0});
        
        sut = new TestComponent(8.500000000001, 1.499999999996, -5.005999999999);
        TestUtils.assertArrayEquals(
                sut.getComponents(),
                new Number[] {8.500000000001, 1.499999999996, -5.005999999999});
        sut.set(0, 5.769999999996);
        sut.set(1, 3.000000000001);
        TestUtils.assertArrayEquals(
                sut.getComponents(),
                new Number[] {5.769999999996, 3.000000000001, -5.005999999999});
        
        sut = new TestComponent(8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999);
        TestUtils.assertArrayEquals(
                sut.getComponents(),
                new Number[] {8.5000000000000000000000001, 1.49999999999999999999996, -5.0059999999999999999999});
        sut.set(0, 6.5000000000000000000000001);
        sut.set(1, -1.49999999999999999999996);
        sut.set(2, 3.0059999999999999999999);
        TestUtils.assertArrayEquals(
                sut.getComponents(),
                new Number[] {6.5, -1.5, 3.006});
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 3), () ->
                sut.set(3, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 8), () ->
                sut.set(8, 5.5));
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, -1), () ->
                sut.set(-1, 5.5));
        
        sut = new TestComponent();
        TestUtils.assertException(IndexOutOfBoundsException.class, sut.getErrorHandler().componentIndexOutOfBoundsErrorMessage(sut, 0), () ->
                sut.set(0, 5.5));
    }
    
    /**
     * JUnit test of copy.
     *
     * @throws Exception When there is an exception.
     * @see BaseComponent#copy(BaseComponent, BaseComponent)
     */
    @Test
    public void testStaticCopy() throws Exception {
        TestComponent copy;
        
        //standard
        
        sut = new TestComponent(8.5);
        copy = new TestComponent(1);
        BaseComponent.copy(sut, copy);
        Assert.assertEquals(
                new TestComponent(8.5), copy);
        
        sut = new TestComponent(8.5, 1.5);
        copy = new TestComponent(2);
        BaseComponent.copy(sut, copy);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5), copy);
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        copy = new TestComponent(3);
        BaseComponent.copy(sut, copy);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006), copy);
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        copy = new TestComponent(4);
        BaseComponent.copy(sut, copy);
        Assert.assertEquals(
                new TestComponent(8.5, 1.5, -5.006, 11.1), copy);
        
        sut = new TestComponent();
        copy = new TestComponent();
        BaseComponent.copy(sut, copy);
        Assert.assertEquals(
                new TestComponent(), copy);
        
        //invalid
        
        sut = new TestComponent(8.5, 1.5, -5.006, 11.1);
        final TestComponent copy1 = new TestComponent(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy1), () ->
                BaseComponent.copy(sut, copy1));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final TestComponent copy2 = new TestComponent(2);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().dimensionalityNotSameErrorMessage(sut, copy2), () ->
                BaseComponent.copy(sut, copy2));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final Vector copy3 = new Vector(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy3), () ->
                BaseComponent.copy(sut, copy3));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        final BigMatrix copy4 = new BigMatrix(3);
        TestUtils.assertException(ArithmeticException.class, sut.getErrorHandler().componentTypeNotSameErrorMessage(sut, copy4), () ->
                BaseComponent.copy(sut, copy4));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                BaseComponent.copy(sut, null));
        
        sut = new TestComponent(8.5, 1.5, -5.006);
        TestUtils.assertException(NullPointerException.class, () ->
                BaseComponent.copy(null, sut));
    }
    
    
    //Inner Classes
    
    /**
     * Defines a Test Component for Base Component unit tests.
     */
    private static class TestComponent extends RawComponent<TestComponent> {
        
        //Constructors
        
        /**
         * The constructor for a Test Component from components.
         *
         * @param components The components that define the Test Component.
         */
        public TestComponent(Number... components) {
            super();
            setComponents(components);
        }
        
        /**
         * The constructor for a Test Component from another Test Component.
         *
         * @param component The Test Component.
         * @see #TestComponent(Number...)
         */
        public TestComponent(TestComponent component) {
            this(Arrays.stream(component.getRawComponents())
                    .toArray(Number[]::new));
        }
        
        /**
         * The constructor for a Test Component of a certain dimensionality.
         *
         * @param dim The dimensionality of the Test Component.
         * @see #TestComponent(Number...)
         */
        public TestComponent(int dim) {
            this(Collections.nCopies(dim, 0).stream()
                    .map(Number.class::cast).toArray(Number[]::new));
        }
        
        /**
         * The constructor for an empty Test Component.
         *
         * @see #TestComponent(int)
         */
        public TestComponent() {
            this(0);
        }
        
        
        //Methods
        
        /**
         * Creates a cloned copy of the Test Component.
         *
         * @return The cloned Test Component.
         * @see #TestComponent(TestComponent)
         */
        @Override
        public TestComponent cloned() {
            TestComponent clone = new TestComponent(this);
            copyMeta(clone);
            return clone;
        }
        
        /**
         * Creates an empty copy of the Test Component.
         *
         * @return The empty copy of the Test Component.
         * @see #TestComponent(int)
         */
        @Override
        public TestComponent emptyCopy() {
            return new TestComponent(getLength());
        }
        
        /**
         * Creates a new Test Component instance of the specified dimensionality.
         *
         * @param dim The dimensionality of the new Test Component.
         * @return The new Test Component.
         * @see #TestComponent(int)
         */
        @Override
        public TestComponent createNewInstance(int dim) {
            return createInstance(Math.max(dim, 0));
        }
        
        
        //Getters
        
        /**
         * Returns the name of the type of Component.
         *
         * @return The name of the type of Component.
         */
        @Override
        public String getName() {
            return "Test Component";
        }
        
        
        //Static Methods
        
        /**
         * Creates a new Test Component instance of the specified dimensionality.
         *
         * @param dim The dimensionality of the new TestComponent.
         * @return The new Test Component.
         * @see #TestComponent(int)
         */
        public static TestComponent createInstance(int dim) {
            return new TestComponent(Math.max(dim, 0));
        }
        
    }
    
}
