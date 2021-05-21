/*
 * File:    BigComponentTest.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import commons.math.MathUtility;
import commons.math.component.handler.math.BigComponentMathHandler;
import commons.math.component.matrix.BigMatrix;
import commons.math.component.vector.BigVector;
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
 * JUnit test of BigComponent.
 *
 * @see BigComponent
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigComponent.class})
public class BigComponentTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigComponentTest.class);
    
    
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
     * @see BigComponent#BigComponent()
     */
    @Test
    public void testConstructors() throws Exception {
        //Big Vector
        BigComponent<?> c1 = new BigVector();
        Assert.assertNotNull(c1);
        Assert.assertTrue(c1 instanceof BigComponent);
        Assert.assertEquals(BigDecimal.class, c1.getType());
        Assert.assertEquals(BigVector.class, c1.getComponentClass());
        Assert.assertTrue(c1.getHandler() instanceof BigComponentMathHandler);
        
        //Big Matrix
        BigComponent<?> c2 = new BigMatrix();
        Assert.assertNotNull(c2);
        Assert.assertTrue(c2 instanceof BigComponent);
        Assert.assertEquals(BigDecimal.class, c2.getType());
        Assert.assertEquals(BigMatrix.class, c2.getComponentClass());
        Assert.assertTrue(c2.getHandler() instanceof BigComponentMathHandler);
    }
    
    /**
     * JUnit test of copyMeta.
     *
     * @throws Exception When there is an exception.
     * @see BigComponent#copyMeta(BigComponent)
     */
    @Test
    public void testCopyMeta() throws Exception {
        BigVector component1 = new BigVector(new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313"));
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), RoundingMode.DOWN);
        component1.setMathContext(newMathContext);
        Assert.assertEquals(4, component1.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313")}, component1.getRawComponents());
        Assert.assertEquals(newMathContext.getPrecision(), component1.getMathContext().getPrecision());
        Assert.assertEquals(newMathContext.getRoundingMode(), component1.getMathContext().getRoundingMode());
        
        BigVector component2 = new BigVector(new BigDecimal("9.1048948183846435490480"), new BigDecimal("6.38408978971844641532021"), new BigDecimal("1.75449011520601216645401"));
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("9.1048948183846435490480"), new BigDecimal("6.38408978971844641532021"), new BigDecimal("1.75449011520601216645401")}, component2.getRawComponents());
        Assert.assertEquals(newMathContext.getPrecision(), component2.getMathContext().getPrecision());
        Assert.assertEquals(newMathContext.getRoundingMode(), component2.getMathContext().getRoundingMode());
    }
    
    /**
     * JUnit test of getRawComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigComponent#getRawComponents()
     */
    @Test
    public void testGetRawComponents() throws Exception {
        BigComponent<?> component;
        
        //standard
        component = new BigVector(new BigDecimal("8.101894806516501541094801647908465405907848919842"), new BigDecimal("6.689907845103061051849840560549045160541801498419842"), new BigDecimal("7.087487098020624098401951984149804984146254697784084169804"), new BigDecimal("2.6700845409817984657978741614534513135049541654984904954516"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.101894806516501541094801647908465405907848919842"), new BigDecimal("6.689907845103061051849840560549045160541801498419842"), new BigDecimal("7.087487098020624098401951984149804984146254697784084169804"), new BigDecimal("2.6700845409817984657978741614534513135049541654984904954516")}, component.getRawComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.101894806516501541094801647908465406"), new BigDecimal("6.689907845103061051849840560549045161"), new BigDecimal("7.087487098020624098401951984149804984"), new BigDecimal("2.670084540981798465797874161453451314")}, component.getComponents());
    }
    
    /**
     * JUnit test of getPrimitiveComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigComponent#getPrimitiveComponents()
     */
    @Test
    public void testGetPrimitiveComponents() throws Exception {
        BigComponent<?> component;
        
        //standard
        component = new BigVector(new BigDecimal("8.101894806516501541094801647908465405907848919842"), new BigDecimal("6.689907845103061051849840560549045160541801498419842"), new BigDecimal("7.087487098020624098401951984149804984146254697784084169804"), new BigDecimal("2.6700845409817984657978741614534513135049541654984904954516"));
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.101894806516501541094801647908465406"), new BigDecimal("6.689907845103061051849840560549045161"), new BigDecimal("7.087487098020624098401951984149804984"), new BigDecimal("2.670084540981798465797874161453451314")}, component.getPrimitiveComponents());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("8.101894806516501541094801647908465406"), new BigDecimal("6.689907845103061051849840560549045161"), new BigDecimal("7.087487098020624098401951984149804984"), new BigDecimal("2.670084540981798465797874161453451314")}, component.getComponents());
    }
    
    /**
     * JUnit test of getName.
     *
     * @throws Exception When there is an exception.
     * @see BigComponent#getName()
     */
    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Big Component", new TestComponent().getName());
    }
    
    /**
     * JUnit test of getMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigComponent#getMathContext()
     */
    @Test
    public void testGetMathContext() throws Exception {
        BigVector component = new BigVector(new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313"));
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), RoundingMode.DOWN);
        Whitebox.setInternalState(component.getHandler(), "mathContext", newMathContext);
        Assert.assertEquals(newMathContext, component.getMathContext());
    }
    
    /**
     * JUnit test of setMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigComponent#setMathContext(MathContext)
     */
    @Test
    public void testSetMathContext() throws Exception {
        BigVector component = new BigVector(new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313"));
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), RoundingMode.DOWN);
        component.setMathContext(newMathContext);
        MathContext mathContext = Whitebox.getInternalState(component.getHandler(), "mathContext");
        Assert.assertEquals(newMathContext, mathContext);
    }
    
    
    //Inner Classes
    
    /**
     * Defines a Test Component for Big Component unit tests.
     */
    private static class TestComponent extends BigComponent<TestComponent> {
        
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
