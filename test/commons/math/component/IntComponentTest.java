/*
 * File:    IntComponentTest.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import commons.math.component.handler.math.IntComponentMathHandler;
import commons.math.component.matrix.IntMatrix;
import commons.math.component.vector.IntVector;
import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of IntComponent.
 *
 * @see IntComponent
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({IntComponent.class})
public class IntComponentTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(IntComponentTest.class);
    
    
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
     * @see IntComponent#IntComponent()
     */
    @Test
    public void testConstructors() throws Exception {
        //Int Vector
        IntComponent<?> c1 = new IntVector();
        Assert.assertNotNull(c1);
        Assert.assertTrue(c1 instanceof IntComponent);
        Assert.assertEquals(Integer.class, c1.getType());
        Assert.assertEquals(IntVector.class, c1.getComponentClass());
        Assert.assertTrue(c1.getHandler() instanceof IntComponentMathHandler);
        
        //Int Matrix
        IntComponent<?> c2 = new IntMatrix();
        Assert.assertNotNull(c2);
        Assert.assertTrue(c2 instanceof IntComponent);
        Assert.assertEquals(Integer.class, c2.getType());
        Assert.assertEquals(IntMatrix.class, c2.getComponentClass());
        Assert.assertTrue(c2.getHandler() instanceof IntComponentMathHandler);
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see IntComponent#copyMeta(IntComponent)
     */
    @Test
    public void testCopyMeta() throws Exception {
        IntVector component1 = new IntVector(8, 6, 7, 2);
        Assert.assertEquals(4, component1.getDimensionality());
        Assert.assertArrayEquals(new Integer[] {8, 6, 7, 2}, component1.getRawComponents());
        
        IntVector component2 = new IntVector(9, 6, 1);
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        Assert.assertArrayEquals(new Integer[] {9, 6, 1}, component2.getRawComponents());
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntComponent#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        IntComponent<?> component;
        
        //standard
        component = new IntVector(8, 6, 7, 2);
        Assert.assertArrayEquals(new Integer[] {8, 6, 7, 2}, component.getRawComponents());
        Assert.assertArrayEquals(new Integer[] {8, 6, 7, 2}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see IntComponent#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        IntComponent<?> component;
        
        //standard
        component = new IntVector(8, 6, 7, 2);
        Assert.assertArrayEquals(new int[] {8, 6, 7, 2}, component.getPrimitiveComponents());
        Assert.assertArrayEquals(new Integer[] {8, 6, 7, 2}, component.getComponents());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see IntComponent#getName()
     */
    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Integer Component", new TestComponent().getName());
    }
    
    
    //Inner Classes
    
    /**
     * Defines a Test Component for Int Component unit tests.
     */
    private static class TestComponent extends IntComponent<TestComponent> {
        
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
