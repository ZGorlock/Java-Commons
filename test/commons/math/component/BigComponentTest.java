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
import commons.math.big.BigMathUtility;
import commons.math.component.handler.math.BigComponentMathHandler;
import commons.math.component.matrix.BigMatrix;
import commons.math.component.vector.BigVector;
import commons.test.TestAccess;
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
 * JUnit test of BigComponent.
 *
 * @see BigComponent
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
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
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), BigMathUtility.DEFAULT_ROUNDING_MODE);
        component1.setMathPrecision(newMathContext.getPrecision());
        Assert.assertEquals(4, component1.getDimensionality());
        TestUtils.assertArrayEquals(
                component1.getRawComponents(),
                new BigDecimal[] {new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313")});
        MathContext component1MathContext = TestAccess.getFieldValue(component1.getHandler(), MathContext.class, "mathContext");
        Assert.assertEquals(newMathContext.getPrecision(), component1MathContext.getPrecision());
        Assert.assertEquals(newMathContext.getRoundingMode(), component1MathContext.getRoundingMode());
        
        BigVector component2 = new BigVector(new BigDecimal("9.1048948183846435490480"), new BigDecimal("6.38408978971844641532021"), new BigDecimal("1.75449011520601216645401"));
        component1.copyMeta(component2);
        Assert.assertEquals(3, component2.getDimensionality());
        TestUtils.assertArrayEquals(
                component2.getRawComponents(),
                new BigDecimal[] {new BigDecimal("9.1048948183846435490480"), new BigDecimal("6.38408978971844641532021"), new BigDecimal("1.75449011520601216645401")});
        MathContext component2MathContext = TestAccess.getFieldValue(component2.getHandler(), MathContext.class, "mathContext");
        Assert.assertEquals(newMathContext.getPrecision(), component2MathContext.getPrecision());
        Assert.assertEquals(newMathContext.getRoundingMode(), component2MathContext.getRoundingMode());
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
        component = new BigVector(new BigDecimal("8.10189480651650154101650154109480164790846540948016479084654059078489194801647908469842"), new BigDecimal("6.6899078451030610518840560549840560549045160541801498419851884056054984056042"), new BigDecimal("7.087486240984019519841498049847098080206240920624098401951984149804984146254697784084169804"), new BigDecimal("2.6700345131350484540981798465797874161453451313504954165491350484540981798484904954516"));
        TestUtils.assertArrayEquals(
                component.getRawComponents(),
                new BigDecimal[] {new BigDecimal("8.10189480651650154101650154109480164790846540948016479084654059078489194801647908469842"), new BigDecimal("6.6899078451030610518840560549840560549045160541801498419851884056054984056042"), new BigDecimal("7.087486240984019519841498049847098080206240920624098401951984149804984146254697784084169804"), new BigDecimal("2.6700345131350484540981798465797874161453451313504954165491350484540981798484904954516")});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new BigDecimal[] {new BigDecimal("8.1018948065165015410165015410948016479084654094801647908465405908"), new BigDecimal("6.6899078451030610518840560549840560549045160541801498419851884056"), new BigDecimal("7.0874862409840195198414980498470980802062409206240984019519841498"), new BigDecimal("2.6700345131350484540981798465797874161453451313504954165491350485")});
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
        component = new BigVector(new BigDecimal("8.10189480651650154101650154109480164790846540948016479084654059078489194801647908469842"), new BigDecimal("6.6899078451030610518840560549840560549045160541801498419851884056054984056042"), new BigDecimal("7.087486240984019519841498049847098080206240920624098401951984149804984146254697784084169804"), new BigDecimal("2.6700345131350484540981798465797874161453451313504954165491350484540981798484904954516"));
        TestUtils.assertArrayEquals(
                component.getPrimitiveComponents(),
                new BigDecimal[] {new BigDecimal("8.1018948065165015410165015410948016479084654094801647908465405908"), new BigDecimal("6.6899078451030610518840560549840560549045160541801498419851884056"), new BigDecimal("7.0874862409840195198414980498470980802062409206240984019519841498"), new BigDecimal("2.6700345131350484540981798465797874161453451313504954165491350485")});
        TestUtils.assertArrayEquals(
                component.getComponents(),
                new BigDecimal[] {new BigDecimal("8.1018948065165015410165015410948016479084654094801647908465405908"), new BigDecimal("6.6899078451030610518840560549840560549045160541801498419851884056"), new BigDecimal("7.0874862409840195198414980498470980802062409206240984019519841498"), new BigDecimal("2.6700345131350484540981798465797874161453451313504954165491350485")});
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
     * JUnit test of getMathPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigComponent#getMathPrecision()
     */
    @Test
    public void testGetMathPrecision() throws Exception {
        BigVector component = new BigVector(new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313"));
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), RoundingMode.DOWN);
        TestAccess.setFieldValue(component.getHandler(), "mathContext", newMathContext);
        Assert.assertEquals(newMathContext.getPrecision(), component.getMathPrecision());
    }
    
    /**
     * JUnit test of setMathPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigComponent#setMathPrecision(int)
     */
    @Test
    public void testSetMathPrecision() throws Exception {
        BigVector component = new BigVector(new BigDecimal("8.1018948065165015410948016"), new BigDecimal("6.689907845103061051849840560"), new BigDecimal("7.087487098020624098401951984149"), new BigDecimal("2.670084540981798465797874161453451313"));
        MathContext newMathContext = new MathContext(MathUtility.dice(6, 4), RoundingMode.DOWN);
        component.setMathPrecision(newMathContext.getPrecision());
        MathContext mathContext = TestAccess.getFieldValue(component.getHandler(), MathContext.class, "mathContext");
        Assert.assertEquals(newMathContext.getPrecision(), mathContext.getPrecision());
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
