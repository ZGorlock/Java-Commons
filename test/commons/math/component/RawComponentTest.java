/*
 * File:    RawComponentTest.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import java.util.Arrays;

import commons.math.component.handler.math.RawComponentMathHandler;
import commons.math.component.matrix.RawMatrix;
import commons.math.component.vector.RawVector;
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
 * JUnit test of RawComponent.
 *
 * @see RawComponent
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({RawComponent.class})
public class RawComponentTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RawComponentTest.class);
    
    
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
     * @see RawComponent#RawComponent()
     */
    @Test
    public void testConstructors() throws Exception {
        //Raw Vector
        RawComponent<?> c1 = new RawVector();
        Assert.assertNotNull(c1);
        Assert.assertTrue(c1 instanceof RawComponent);
        Assert.assertEquals(Number.class, c1.getType());
        Assert.assertEquals(RawVector.class, c1.getComponentClass());
        Assert.assertTrue(c1.getHandler() instanceof RawComponentMathHandler);
        
        //Raw Matrix
        RawComponent<?> c2 = new RawMatrix();
        Assert.assertNotNull(c2);
        Assert.assertTrue(c2 instanceof RawComponent);
        Assert.assertEquals(Number.class, c2.getType());
        Assert.assertEquals(RawMatrix.class, c2.getComponentClass());
        Assert.assertTrue(c2.getHandler() instanceof RawComponentMathHandler);
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#copyMeta(RawComponent)
     */
    @Test
    public void testCopyMeta() throws Exception {
        RawVector component1 = new RawVector(8.1, 6.6, 7.0, 2.6);
        Assert.assertEquals(4, component1.getDimensionality());
        TestUtils.assertArrayEquals(
                component1.getRawComponents(),
                new Number[] {8.1, 6.6, 7.0, 2.6});
        
        RawVector component2 = new RawVector(9.1, 6.3, 1.7);
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        TestUtils.assertArrayEquals(
                component2.getRawComponents(),
                new Number[] {9.1, 6.3, 1.7});
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        RawComponent<?> component;
        
        //standard
        component = new RawVector(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new Number[] {8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509});
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        RawComponent<?> component;
        
        //standard
        component = new RawVector(8.160456540859010650161, 6.64908498410841501980404, 7.04808971059084054054, 2.6908405165094841828);
        TestUtils.assertArrayEquals(
                Arrays.stream(component.getPrimitiveComponents()).boxed().toArray(),
                new Double[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new Number[] {8.160456540859, 6.649084984108, 7.048089710591, 2.690840516509});
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see RawComponent#getName()
     */
    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Raw Component", new TestComponent().getName());
    }
    
    
    //Inner Classes
    
    /**
     * Defines a Test Component for Raw Component unit tests.
     */
    private static class TestComponent extends RawComponent<TestComponent> {
        
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
