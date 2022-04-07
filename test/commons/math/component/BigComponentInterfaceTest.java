/*
 * File:    BigComponentInterfaceTest.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import java.math.BigDecimal;
import java.util.Arrays;

import commons.math.component.vector.BigVector;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of BigComponentInterface.
 *
 * @see BigComponentInterface
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigComponentInterface.class})
public class BigComponentInterfaceTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigComponentInterfaceTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private BigComponent<?> sut;
    
    
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
     * JUnit test of movePointLeft.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#movePointLeft(int)
     */
    @Test
    public void testMovePointLeft() throws Exception {
        TestUtils.assertMethodExists(
                BigComponentInterface.class, "movePointLeft", int.class);
        
        //standard
        
        sut = new BigVector("8.16545460549178401501230541064000", "0.4541984048301290878421201", "2.48908405461649302010508710120000000000");
        
        TestUtils.assertArrayEquals(
                Arrays.stream(sut.movePointLeft(0).getRawComponents()).map(BigDecimal::toPlainString).toArray(),
                new String[] {"8.16545460549178401501230541064000", "0.4541984048301290878421201", "2.48908405461649302010508710120000000000"});
        
        TestUtils.assertArrayEquals(
                Arrays.stream(sut.movePointLeft(5).getRawComponents()).map(BigDecimal::toPlainString).toArray(),
                new String[] {"0.0000816545460549178401501230541064000", "0.000004541984048301290878421201", "0.0000248908405461649302010508710120000000000"});
        
        TestUtils.assertArrayEquals(
                Arrays.stream(sut.movePointLeft(-8).getRawComponents()).map(BigDecimal::toPlainString).toArray(),
                new String[] {"816545460.549178401501230541064000", "45419840.48301290878421201", "248908405.461649302010508710120000000000"});
    }
    
    /**
     * JUnit test of movePointRight.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#movePointRight(int)
     */
    @Test
    public void testMovePointRight() throws Exception {
        TestUtils.assertMethodExists(
                BigComponentInterface.class, "movePointRight", int.class);
        
        //standard
        
        sut = new BigVector("8.16545460549178401501230541064000", "0.4541984048301290878421201", "2.48908405461649302010508710120000000000");
        
        TestUtils.assertArrayEquals(
                Arrays.stream(sut.movePointRight(0).getRawComponents()).map(BigDecimal::toPlainString).toArray(),
                new String[] {"8.16545460549178401501230541064000", "0.4541984048301290878421201", "2.48908405461649302010508710120000000000"});
        
        TestUtils.assertArrayEquals(
                Arrays.stream(sut.movePointRight(5).getRawComponents()).map(BigDecimal::toPlainString).toArray(),
                new String[] {"816545.460549178401501230541064000", "45419.84048301290878421201", "248908.405461649302010508710120000000000"});
        
        TestUtils.assertArrayEquals(
                Arrays.stream(sut.movePointRight(-8).getRawComponents()).map(BigDecimal::toPlainString).toArray(),
                new String[] {"0.0000000816545460549178401501230541064000", "0.000000004541984048301290878421201", "0.0000000248908405461649302010508710120000000000"});
    }
    
    /**
     * JUnit test of stripTrailingZeros.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#stripTrailingZeros()
     */
    @Test
    public void testStripTrailingZeros() throws Exception {
        TestUtils.assertMethodExists(
                BigComponentInterface.class, "stripTrailingZeros");
        
        //standard
        
        sut = new BigVector("8.16545460549178401501230541064000", "0.4541984048301290878421201", "2.48908405461649302010508710120000000000");
        
        TestUtils.assertArrayEquals(
                Arrays.stream(sut.stripTrailingZeros().getRawComponents()).map(BigDecimal::toPlainString).toArray(),
                new String[] {"8.16545460549178401501230541064", "0.4541984048301290878421201", "2.4890840546164930201050871012"});
    }
    
    /**
     * JUnit test of getMathPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#getMathPrecision()
     */
    @Test
    public void testGetMathPrecision() throws Exception {
        TestUtils.assertMethodExists(
                BigComponentInterface.class, "getMathPrecision");
    }
    
    /**
     * JUnit test of setMathPrecision.
     *
     * @throws Exception When there is an exception.
     * @see BigComponentInterface#setMathPrecision(int)
     */
    @Test
    public void testSetMathPrecision() throws Exception {
        TestUtils.assertMethodExists(
                BigComponentInterface.class, "setMathPrecision", int.class);
    }
    
}
