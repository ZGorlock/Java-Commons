/*
 * File:    ComponentTest.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import java.util.Arrays;

import commons.math.component.handler.math.DoubleComponentMathHandler;
import commons.math.component.matrix.Matrix;
import commons.math.component.matrix.Matrix2;
import commons.math.component.vector.Vector;
import commons.math.component.vector.Vector3;
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
 * JUnit test of Component.
 *
 * @see Component
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Component.class})
public class ComponentTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ComponentTest.class);
    
    
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
     * @see Component#Component()
     */
    @Test
    public void testConstructors() throws Exception {
        //Vector
        Component<?> c1 = new Vector();
        Assert.assertNotNull(c1);
        Assert.assertTrue(c1 instanceof Component);
        Assert.assertEquals(Double.class, c1.getType());
        Assert.assertEquals(Vector.class, c1.getComponentClass());
        Assert.assertTrue(c1.getHandler() instanceof DoubleComponentMathHandler);
        
        //Matrix
        Component<?> c2 = new Matrix();
        Assert.assertNotNull(c2);
        Assert.assertTrue(c2 instanceof Component);
        Assert.assertEquals(Double.class, c2.getType());
        Assert.assertEquals(Matrix.class, c2.getComponentClass());
        Assert.assertTrue(c2.getHandler() instanceof DoubleComponentMathHandler);
        
        //3D Vector
        Component<?> c3 = new Vector3();
        Assert.assertNotNull(c3);
        Assert.assertTrue(c3 instanceof Component);
        Assert.assertEquals(Double.class, c3.getType());
        Assert.assertEquals(Vector3.class, c3.getComponentClass());
        Assert.assertTrue(c3.getHandler() instanceof DoubleComponentMathHandler);
        
        //2D Matrix
        Component<?> c4 = new Matrix2();
        Assert.assertNotNull(c4);
        Assert.assertTrue(c4 instanceof Component);
        Assert.assertEquals(Double.class, c4.getType());
        Assert.assertEquals(Matrix2.class, c4.getComponentClass());
        Assert.assertTrue(c4.getHandler() instanceof DoubleComponentMathHandler);
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see Component#copyMeta(Component)
     */
    @Test
    public void testCopyMeta() throws Exception {
        Vector component1 = new Vector(8.1, 6.6, 7.0, 2.6);
        Assert.assertEquals(4, component1.getDimensionality());
        TestUtils.assertArrayEquals(
                component1.getRawComponents(),
                new Double[] {8.1, 6.6, 7.0, 2.6});
        
        Vector component2 = new Vector(9.1, 6.3, 1.7);
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        TestUtils.assertArrayEquals(
                component2.getRawComponents(),
                new Double[] {9.1, 6.3, 1.7});
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see Component#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        Component<?> component;
        
        //standard
        component = new Vector(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Double[] {8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509});
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see Component#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        Component<?> component;
        
        //standard
        component = new Vector(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        TestUtils.assertArrayEquals(
                Arrays.stream(component.getPrimitiveComponents()).boxed().toArray(),
                new Double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509});
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see Component#getName()
     */
    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Component", new TestComponent().getName());
    }
    
    
    //Inner Classes
    
    /**
     * Defines a Test Component for Component unit tests.
     */
    private static class TestComponent extends Component<TestComponent> {
        
        //Methods
        
        /* (non-Javadoc)
         * @see ComponentInterface#cloned()
         */
        @Override
        public TestComponent cloned() {
            return null;
        }
        
        /* (non-Javadoc)
         * @see ComponentInterface#emptyCopy()
         */
        @Override
        public TestComponent emptyCopy() {
            return null;
        }
        
        /* (non-Javadoc)
         * @see ComponentInterface#createNewInstance(int)
         */
        @Override
        public TestComponent createNewInstance(int dim) {
            return null;
        }
        
    }
    
}
