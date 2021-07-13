/*
 * File:    BigMathUtilityTest.java
 * Package: commons.math
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
 * JUnit test of BigMathUtility.
 *
 * @see BigMathUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigMathUtility.class})
public class BigMathUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigMathUtilityTest.class);
    
    
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
     * @see BigMathUtility#DEFAULT_MATH_PRECISION
     * @see BigMathUtility#DEFAULT_ROUNDING_MODE
     * @see BigMathUtility#ONE
     * @see BigMathUtility#ZERO
     * @see BigMathUtility#NEGATIVE_ONE
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(64, BigMathUtility.DEFAULT_MATH_PRECISION);
        Assert.assertEquals(RoundingMode.HALF_UP, BigMathUtility.DEFAULT_ROUNDING_MODE);
        Assert.assertEquals(BigDecimal.ONE, BigMathUtility.ONE);
        Assert.assertEquals(BigDecimal.ZERO, BigMathUtility.ZERO);
        Assert.assertEquals(BigDecimal.ONE.negate(), BigMathUtility.NEGATIVE_ONE);
    }
    
    /**
     * JUnit test of PrecisionMode.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility.PrecisionMode
     */
    @Test
    public void testPrecisionMode() throws Exception {
        Assert.assertEquals(7, BigMathUtility.PrecisionMode.values().length);
        Assert.assertEquals(BigMathUtility.PrecisionMode.DEFAULT_PRECISION, BigMathUtility.PrecisionMode.values()[0]);
        Assert.assertEquals(BigMathUtility.PrecisionMode.NO_PRECISION, BigMathUtility.PrecisionMode.values()[1]);
        Assert.assertEquals(BigMathUtility.PrecisionMode.LOW_PRECISION, BigMathUtility.PrecisionMode.values()[2]);
        Assert.assertEquals(BigMathUtility.PrecisionMode.MID_PRECISION, BigMathUtility.PrecisionMode.values()[3]);
        Assert.assertEquals(BigMathUtility.PrecisionMode.HIGH_PRECISION, BigMathUtility.PrecisionMode.values()[4]);
        Assert.assertEquals(BigMathUtility.PrecisionMode.MAX_PRECISION, BigMathUtility.PrecisionMode.values()[5]);
        Assert.assertEquals(BigMathUtility.PrecisionMode.MATH_PRECISION, BigMathUtility.PrecisionMode.values()[6]);
        
        //getPrecision
        Assert.assertEquals(-1, BigMathUtility.PrecisionMode.DEFAULT_PRECISION.getPrecision());
        Assert.assertEquals(0, BigMathUtility.PrecisionMode.NO_PRECISION.getPrecision());
        Assert.assertEquals(8, BigMathUtility.PrecisionMode.LOW_PRECISION.getPrecision());
        Assert.assertEquals(16, BigMathUtility.PrecisionMode.MID_PRECISION.getPrecision());
        Assert.assertEquals(64, BigMathUtility.PrecisionMode.HIGH_PRECISION.getPrecision());
        Assert.assertEquals(512, BigMathUtility.PrecisionMode.MAX_PRECISION.getPrecision());
        Assert.assertEquals(1024, BigMathUtility.PrecisionMode.MATH_PRECISION.getPrecision());
    }
    
    /**
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#add(BigDecimal, BigDecimal, int)
     * @see BigMathUtility#add(BigDecimal, BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#add(BigDecimal, BigDecimal)
     */
    @Test
    public void testAdd() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("2"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("21"), BigMathUtility.add(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("186"), BigMathUtility.add(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("88"), BigMathUtility.add(new BigDecimal("0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("549"), BigMathUtility.add(new BigDecimal("0549"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("8"), BigMathUtility.add(new BigDecimal("9"), new BigDecimal("-1")));
        
        //negative
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-13"), BigMathUtility.add(new BigDecimal("-17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("168"), BigMathUtility.add(new BigDecimal("-9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("88"), BigMathUtility.add(new BigDecimal("-0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("-549"), BigMathUtility.add(new BigDecimal("-0549"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("13"), BigMathUtility.add(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("-168"), BigMathUtility.add(new BigDecimal("9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("-88"), BigMathUtility.add(new BigDecimal("0"), new BigDecimal("-88")));
        Assert.assertEquals(new BigDecimal("549"), BigMathUtility.add(new BigDecimal("0549"), new BigDecimal("-0")));
        Assert.assertEquals(new BigDecimal("-2"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-21"), BigMathUtility.add(new BigDecimal("-17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("-186"), BigMathUtility.add(new BigDecimal("-9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("-88"), BigMathUtility.add(new BigDecimal("-0"), new BigDecimal("-88")));
        Assert.assertEquals(new BigDecimal("-549"), BigMathUtility.add(new BigDecimal("-0549"), new BigDecimal("-0")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("1.1"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("1.1"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("0.2"), BigMathUtility.add(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("5.0"), BigMathUtility.add(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("3.2"), BigMathUtility.add(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("23.2"), BigMathUtility.add(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("1.519"), BigMathUtility.add(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("985.519"), BigMathUtility.add(new BigDecimal("0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("0.0094100578"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("20.0094100578"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertEquals(new BigDecimal("-0.9"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("-0.9"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.add(new BigDecimal("-.1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("-1.2"), BigMathUtility.add(new BigDecimal("-3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.add(new BigDecimal("-3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("17.0"), BigMathUtility.add(new BigDecimal("-03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("0.363"), BigMathUtility.add(new BigDecimal("-.578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("984.363"), BigMathUtility.add(new BigDecimal("-0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("0.0094099422"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("-15.9905900578"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941")));
        Assert.assertEquals(new BigDecimal("0.9"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("0.9"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.add(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("1.2"), BigMathUtility.add(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.add(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("-17.0"), BigMathUtility.add(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("-0.363"), BigMathUtility.add(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("-984.363"), BigMathUtility.add(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("-0.0094099422"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("15.9905900578"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        Assert.assertEquals(new BigDecimal("-1.1"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("-1.1"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("-0.2"), BigMathUtility.add(new BigDecimal("-.1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("-5.0"), BigMathUtility.add(new BigDecimal("-3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("-3.2"), BigMathUtility.add(new BigDecimal("-3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("-23.2"), BigMathUtility.add(new BigDecimal("-03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("-1.519"), BigMathUtility.add(new BigDecimal("-.578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("-985.519"), BigMathUtility.add(new BigDecimal("-0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("-0.0094100578"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("-20.0094100578"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.add(new BigDecimal("1.0"), new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.add(new BigDecimal("1.0"), new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.add(new BigDecimal("1"), new BigDecimal("0"), null));
        
        //precision, negative
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0"), BigMathUtility.add(new BigDecimal("-1.0"), new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.00000000"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.add(new BigDecimal("-1"), new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.0094100578"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("0.0094101"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 7));
        Assert.assertEquals(new BigDecimal("0.009410"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("0.009"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("0.01"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("20.0094100578"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("20.0094101"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 7));
        Assert.assertEquals(new BigDecimal("20.009410"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("20.009"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("20.01"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("20.0"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.add(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.add(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.add(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.add(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 1));
        
        //custom precision, negative
        Assert.assertEquals(new BigDecimal("0.0094099422"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("0.0094099"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 7));
        Assert.assertEquals(new BigDecimal("0.009410"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("0.009"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("0.01"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("-15.9905900578"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-15.9905901"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 7));
        Assert.assertEquals(new BigDecimal("-15.991"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-15.99"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-16.0"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-2.99999999999999"), BigMathUtility.add(new BigDecimal("-2.99999999999999"), new BigDecimal("0"), 14));
        Assert.assertEquals(new BigDecimal("-3.0000000000"), BigMathUtility.add(new BigDecimal("-2.99999999999999"), new BigDecimal("0"), 10));
        Assert.assertEquals(new BigDecimal("-3.00000"), BigMathUtility.add(new BigDecimal("-2.99999999999999"), new BigDecimal("0"), 5));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.add(new BigDecimal("-2.99999999999999"), new BigDecimal("0"), 1));
        Assert.assertEquals(new BigDecimal("-0.0094099422"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.0094099"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 7));
        Assert.assertEquals(new BigDecimal("-0.009410"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("-0.009"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("-0.01"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.add(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("15.9905900578"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("15.9905901"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 7));
        Assert.assertEquals(new BigDecimal("15.990590"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("15.991"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("15.99"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("16.0"), BigMathUtility.add(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.add(new BigDecimal("2.99999999999999"), new BigDecimal("-0"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.add(new BigDecimal("2.99999999999999"), new BigDecimal("-0"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.add(new BigDecimal("2.99999999999999"), new BigDecimal("-0"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.add(new BigDecimal("2.99999999999999"), new BigDecimal("-0"), 1));
        Assert.assertEquals(new BigDecimal("-0.0094100578"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.0094101"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 7));
        Assert.assertEquals(new BigDecimal("-0.009410"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("-0.009"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("-0.01"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.add(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("-20.0094100578"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-20.0094101"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 7));
        Assert.assertEquals(new BigDecimal("-20.009410"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("-20.009"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-20.01"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-20.0"), BigMathUtility.add(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-2.99999999999999"), BigMathUtility.add(new BigDecimal("-2.99999999999999"), new BigDecimal("-0"), 14));
        Assert.assertEquals(new BigDecimal("-3.0000000000"), BigMathUtility.add(new BigDecimal("-2.99999999999999"), new BigDecimal("-0"), 10));
        Assert.assertEquals(new BigDecimal("-3.00000"), BigMathUtility.add(new BigDecimal("-2.99999999999999"), new BigDecimal("-0"), 5));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.add(new BigDecimal("-2.99999999999999"), new BigDecimal("-0"), 1));
        
        //larger cases
        Assert.assertEquals(
                new BigDecimal("5967945689794436877198887828689582168366902795322725858829"),
                BigMathUtility.add(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689794436877198887828689582168366902795322725858829"),
                BigMathUtility.add(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                new BigDecimal("5967945689763137224198691854380377528607990117303836116299"),
                BigMathUtility.add(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689763137224198691854380377528607990117303836116299"),
                BigMathUtility.add(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689763137224198691854380377528607990117303836116299"),
                BigMathUtility.add(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689763137224198691854380377528607990117303836116299"),
                BigMathUtility.add(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689794436877198887828689582168366902795322725858829"),
                BigMathUtility.add(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689794436877198887828689582168366902795322725858829"),
                BigMathUtility.add(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.add(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.add(null, new BigDecimal("2")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.add(new BigDecimal("2"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.add(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.add(null, new BigDecimal("2"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.add(new BigDecimal("2"), null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.add(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.add(null, new BigDecimal("2"), 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.add(new BigDecimal("2"), null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.add(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.add(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.add(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.add(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
    /**
     * JUnit test of subtract.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#subtract(BigDecimal, BigDecimal, int)
     * @see BigMathUtility#subtract(BigDecimal, BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#subtract(BigDecimal, BigDecimal)
     */
    @Test
    public void testSubtract() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("13"), BigMathUtility.subtract(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("-168"), BigMathUtility.subtract(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("-88"), BigMathUtility.subtract(new BigDecimal("0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("549"), BigMathUtility.subtract(new BigDecimal("0549"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("10"), BigMathUtility.subtract(new BigDecimal("9"), new BigDecimal("-1")));
        
        //negative
        Assert.assertEquals(new BigDecimal("-2"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-21"), BigMathUtility.subtract(new BigDecimal("-17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("-186"), BigMathUtility.subtract(new BigDecimal("-9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("-88"), BigMathUtility.subtract(new BigDecimal("-0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("-549"), BigMathUtility.subtract(new BigDecimal("-0549"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("2"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("21"), BigMathUtility.subtract(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("186"), BigMathUtility.subtract(new BigDecimal("9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("88"), BigMathUtility.subtract(new BigDecimal("0"), new BigDecimal("-88")));
        Assert.assertEquals(new BigDecimal("549"), BigMathUtility.subtract(new BigDecimal("0549"), new BigDecimal("-0")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-13"), BigMathUtility.subtract(new BigDecimal("-17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("168"), BigMathUtility.subtract(new BigDecimal("-9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("88"), BigMathUtility.subtract(new BigDecimal("-0"), new BigDecimal("-88")));
        Assert.assertEquals(new BigDecimal("-549"), BigMathUtility.subtract(new BigDecimal("-0549"), new BigDecimal("-0")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.9"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("0.9"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.subtract(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("1.2"), BigMathUtility.subtract(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.subtract(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("-17.0"), BigMathUtility.subtract(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("-0.363"), BigMathUtility.subtract(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("-984.363"), BigMathUtility.subtract(new BigDecimal("0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("-0.0094099422"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("15.9905900578"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertEquals(new BigDecimal("-1.1"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("-1.1"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("-0.2"), BigMathUtility.subtract(new BigDecimal("-.1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("-5.0"), BigMathUtility.subtract(new BigDecimal("-3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("-3.2"), BigMathUtility.subtract(new BigDecimal("-3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("-23.2"), BigMathUtility.subtract(new BigDecimal("-03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("-1.519"), BigMathUtility.subtract(new BigDecimal("-.578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("-985.519"), BigMathUtility.subtract(new BigDecimal("-0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("-0.0094100578"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("-20.0094100578"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941")));
        Assert.assertEquals(new BigDecimal("1.1"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("1.1"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("0.2"), BigMathUtility.subtract(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("5.0"), BigMathUtility.subtract(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("3.2"), BigMathUtility.subtract(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("23.2"), BigMathUtility.subtract(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("1.519"), BigMathUtility.subtract(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("985.519"), BigMathUtility.subtract(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("0.0094100578"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("20.0094100578"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        Assert.assertEquals(new BigDecimal("-0.9"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("-0.9"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.subtract(new BigDecimal("-.1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("-1.2"), BigMathUtility.subtract(new BigDecimal("-3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.subtract(new BigDecimal("-3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("17.0"), BigMathUtility.subtract(new BigDecimal("-03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("0.363"), BigMathUtility.subtract(new BigDecimal("-.578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("984.363"), BigMathUtility.subtract(new BigDecimal("-0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("0.0094099422"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("-15.9905900578"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.subtract(new BigDecimal("1.0"), new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("0"), null));
        
        //precision, negative
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0"), BigMathUtility.subtract(new BigDecimal("-1.0"), new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.00000000"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.subtract(new BigDecimal("-1"), new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("-0.0094099422"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.0094099"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 7));
        Assert.assertEquals(new BigDecimal("-0.009410"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("-0.009"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("-0.01"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("15.9905900578"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("15.9905901"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 7));
        Assert.assertEquals(new BigDecimal("15.990590"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("15.991"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("15.99"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("16.0"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.subtract(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.subtract(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.subtract(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.subtract(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 1));
        
        //custom precision, negative
        Assert.assertEquals(new BigDecimal("-0.0094100578"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.0094101"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 7));
        Assert.assertEquals(new BigDecimal("-0.009410"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("-0.009"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("-0.01"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("-20.0094100578"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-20.0094101"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 7));
        Assert.assertEquals(new BigDecimal("-20.009410"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("-20.009"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-20.01"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-20.0"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-2.99999999999999"), BigMathUtility.subtract(new BigDecimal("-2.99999999999999"), new BigDecimal("0"), 14));
        Assert.assertEquals(new BigDecimal("-3.0000000000"), BigMathUtility.subtract(new BigDecimal("-2.99999999999999"), new BigDecimal("0"), 10));
        Assert.assertEquals(new BigDecimal("-3.00000"), BigMathUtility.subtract(new BigDecimal("-2.99999999999999"), new BigDecimal("0"), 5));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.subtract(new BigDecimal("-2.99999999999999"), new BigDecimal("0"), 1));
        Assert.assertEquals(new BigDecimal("0.0094100578"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("0.0094101"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 7));
        Assert.assertEquals(new BigDecimal("0.009410"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.009"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.01"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.subtract(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("20.0094100578"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("20.0094101"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 7));
        Assert.assertEquals(new BigDecimal("20.009410"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("20.009"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("20.01"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("20.0"), BigMathUtility.subtract(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.subtract(new BigDecimal("2.99999999999999"), new BigDecimal("-0"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.subtract(new BigDecimal("2.99999999999999"), new BigDecimal("-0"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.subtract(new BigDecimal("2.99999999999999"), new BigDecimal("-0"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.subtract(new BigDecimal("2.99999999999999"), new BigDecimal("-0"), 1));
        Assert.assertEquals(new BigDecimal("0.0094099422"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("0.0094099"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 7));
        Assert.assertEquals(new BigDecimal("0.009410"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.009"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.01"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.subtract(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("-15.9905900578"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-15.9905901"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 7));
        Assert.assertEquals(new BigDecimal("-15.991"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-15.99"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-16.0"), BigMathUtility.subtract(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-2.99999999999999"), BigMathUtility.subtract(new BigDecimal("-2.99999999999999"), new BigDecimal("-0"), 14));
        Assert.assertEquals(new BigDecimal("-3.0000000000"), BigMathUtility.subtract(new BigDecimal("-2.99999999999999"), new BigDecimal("-0"), 10));
        Assert.assertEquals(new BigDecimal("-3.00000"), BigMathUtility.subtract(new BigDecimal("-2.99999999999999"), new BigDecimal("-0"), 5));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.subtract(new BigDecimal("-2.99999999999999"), new BigDecimal("-0"), 1));
        
        //larger cases
        Assert.assertEquals(
                new BigDecimal("-5967945689763137224198691854380377528607990117303836116299"),
                BigMathUtility.subtract(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689763137224198691854380377528607990117303836116299"),
                BigMathUtility.subtract(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                new BigDecimal("-5967945689794436877198887828689582168366902795322725858829"),
                BigMathUtility.subtract(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689794436877198887828689582168366902795322725858829"),
                BigMathUtility.subtract(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689794436877198887828689582168366902795322725858829"),
                BigMathUtility.subtract(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689794436877198887828689582168366902795322725858829"),
                BigMathUtility.subtract(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689763137224198691854380377528607990117303836116299"),
                BigMathUtility.subtract(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689763137224198691854380377528607990117303836116299"),
                BigMathUtility.subtract(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001"),
                BigMathUtility.subtract(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.subtract(null, new BigDecimal("2")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.subtract(new BigDecimal("2"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.subtract(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.subtract(null, new BigDecimal("2"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.subtract(new BigDecimal("2"), null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.subtract(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.subtract(null, new BigDecimal("2"), 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.subtract(new BigDecimal("2"), null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.subtract(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.subtract(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.subtract(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.subtract(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#multiply(BigDecimal, BigDecimal, int)
     * @see BigMathUtility#multiply(BigDecimal, BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#multiply(BigDecimal, BigDecimal)
     */
    @Test
    public void testMultiply() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("68"), BigMathUtility.multiply(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("1593"), BigMathUtility.multiply(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.multiply(new BigDecimal("0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.multiply(new BigDecimal("0549"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("-9"), BigMathUtility.multiply(new BigDecimal("9"), new BigDecimal("-1")));
        
        //negative
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-68"), BigMathUtility.multiply(new BigDecimal("-17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("-1593"), BigMathUtility.multiply(new BigDecimal("-9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.multiply(new BigDecimal("-0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.multiply(new BigDecimal("-0549"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-68"), BigMathUtility.multiply(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("-1593"), BigMathUtility.multiply(new BigDecimal("9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.multiply(new BigDecimal("0"), new BigDecimal("-88")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.multiply(new BigDecimal("0549"), new BigDecimal("-0")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("68"), BigMathUtility.multiply(new BigDecimal("-17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("1593"), BigMathUtility.multiply(new BigDecimal("-9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.multiply(new BigDecimal("-0"), new BigDecimal("-88")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.multiply(new BigDecimal("-0549"), new BigDecimal("-0")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.1"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("0.1"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("0.01"), BigMathUtility.multiply(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("5.89"), BigMathUtility.multiply(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("0.31"), BigMathUtility.multiply(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("62.31"), BigMathUtility.multiply(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("0.543898"), BigMathUtility.multiply(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("569.295898"), BigMathUtility.multiply(new BigDecimal("0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("0.000000000543898"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("36.169380116143898"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertEquals(new BigDecimal("-0.1"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("-0.1"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("-0.01"), BigMathUtility.multiply(new BigDecimal("-.1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("-5.89"), BigMathUtility.multiply(new BigDecimal("-3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("-0.31"), BigMathUtility.multiply(new BigDecimal("-3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("-62.31"), BigMathUtility.multiply(new BigDecimal("-03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("-0.543898"), BigMathUtility.multiply(new BigDecimal("-.578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("-569.295898"), BigMathUtility.multiply(new BigDecimal("-0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("-0.000000000543898"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("-36.169380116143898"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941")));
        Assert.assertEquals(new BigDecimal("-0.1"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("-0.1"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("-0.01"), BigMathUtility.multiply(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("-5.89"), BigMathUtility.multiply(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("-0.31"), BigMathUtility.multiply(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("-62.31"), BigMathUtility.multiply(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("-0.543898"), BigMathUtility.multiply(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("-569.295898"), BigMathUtility.multiply(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("-0.000000000543898"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("-36.169380116143898"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        Assert.assertEquals(new BigDecimal("0.1"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("0.1"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("0.01"), BigMathUtility.multiply(new BigDecimal("-.1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("5.89"), BigMathUtility.multiply(new BigDecimal("-3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("0.31"), BigMathUtility.multiply(new BigDecimal("-3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("62.31"), BigMathUtility.multiply(new BigDecimal("-03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("0.543898"), BigMathUtility.multiply(new BigDecimal("-.578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("569.295898"), BigMathUtility.multiply(new BigDecimal("-0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("0.000000000543898"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("36.169380116143898"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00"), BigMathUtility.multiply(new BigDecimal("1.0"), new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("1"), null));
        
        //precision, negative
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.00"), BigMathUtility.multiply(new BigDecimal("-1.0"), new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.00000000"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.multiply(new BigDecimal("-1"), new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000000543898"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000005439"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 13));
        Assert.assertEquals(new BigDecimal("0.000000000544"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000005"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("0.000000001"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("36.169380116143898"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 15));
        Assert.assertEquals(new BigDecimal("36.1693801161439"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 13));
        Assert.assertEquals(new BigDecimal("36.169380116144"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 12));
        Assert.assertEquals(new BigDecimal("36.1693801161"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("36.169380116"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 9));
        Assert.assertEquals(new BigDecimal("36.169380"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("36.169"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("36.17"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("36.2"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.multiply(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.multiply(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.multiply(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.multiply(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 1));
        
        //custom precision, negative
        Assert.assertEquals(new BigDecimal("-0.000000000543898"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 15));
        Assert.assertEquals(new BigDecimal("-0.0000000005439"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 13));
        Assert.assertEquals(new BigDecimal("-0.000000000544"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 12));
        Assert.assertEquals(new BigDecimal("-0.0000000005"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.000000001"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("-36.169380116143898"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 15));
        Assert.assertEquals(new BigDecimal("-36.1693801161439"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 13));
        Assert.assertEquals(new BigDecimal("-36.169380116144"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 12));
        Assert.assertEquals(new BigDecimal("-36.1693801161"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-36.169380116"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 9));
        Assert.assertEquals(new BigDecimal("-36.169380"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("-36.169"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-36.17"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-36.2"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-2.99999999999999"), BigMathUtility.multiply(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 14));
        Assert.assertEquals(new BigDecimal("-3.0000000000"), BigMathUtility.multiply(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("-3.00000"), BigMathUtility.multiply(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 5));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.multiply(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 1));
        Assert.assertEquals(new BigDecimal("-0.000000000543898"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 15));
        Assert.assertEquals(new BigDecimal("-0.0000000005439"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 13));
        Assert.assertEquals(new BigDecimal("-0.000000000544"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 12));
        Assert.assertEquals(new BigDecimal("-0.0000000005"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.000000001"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.multiply(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("-36.169380116143898"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 15));
        Assert.assertEquals(new BigDecimal("-36.1693801161439"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 13));
        Assert.assertEquals(new BigDecimal("-36.169380116144"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 12));
        Assert.assertEquals(new BigDecimal("-36.1693801161"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-36.169380116"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 9));
        Assert.assertEquals(new BigDecimal("-36.169380"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("-36.169"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-36.17"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-36.2"), BigMathUtility.multiply(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-2.99999999999999"), BigMathUtility.multiply(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 14));
        Assert.assertEquals(new BigDecimal("-3.0000000000"), BigMathUtility.multiply(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("-3.00000"), BigMathUtility.multiply(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 5));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.multiply(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 1));
        Assert.assertEquals(new BigDecimal("0.000000000543898"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000005439"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 13));
        Assert.assertEquals(new BigDecimal("0.000000000544"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000005"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("0.000000001"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.multiply(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("36.169380116143898"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 15));
        Assert.assertEquals(new BigDecimal("36.1693801161439"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 13));
        Assert.assertEquals(new BigDecimal("36.169380116144"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 12));
        Assert.assertEquals(new BigDecimal("36.1693801161"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("36.169380116"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 9));
        Assert.assertEquals(new BigDecimal("36.169380"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("36.169"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("36.17"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("36.2"), BigMathUtility.multiply(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.multiply(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.multiply(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.multiply(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.multiply(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 1));
        
        //larger cases
        Assert.assertEquals(
                new BigDecimal("93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460"),
                BigMathUtility.multiply(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460"),
                BigMathUtility.multiply(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                new BigDecimal("-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460"),
                BigMathUtility.multiply(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460"),
                BigMathUtility.multiply(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460"),
                BigMathUtility.multiply(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460"),
                BigMathUtility.multiply(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460"),
                BigMathUtility.multiply(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460"),
                BigMathUtility.multiply(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549"),
                BigMathUtility.multiply(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.multiply(null, new BigDecimal("2")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.multiply(new BigDecimal("2"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.multiply(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.multiply(null, new BigDecimal("2"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.multiply(new BigDecimal("2"), null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.multiply(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.multiply(null, new BigDecimal("2"), 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.multiply(new BigDecimal("2"), null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.multiply(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.multiply(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.multiply(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.multiply(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
    /**
     * JUnit test of divide.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#divide(BigDecimal, BigDecimal, int)
     * @see BigMathUtility#divide(BigDecimal, BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#divide(BigDecimal, BigDecimal)
     */
    @Test
    public void testDivide() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("4.25"), BigMathUtility.divide(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("0.0508474576271186440677966101694915254237288135593220338983050847"), BigMathUtility.divide(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.divide(new BigDecimal("0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("-9"), BigMathUtility.divide(new BigDecimal("9"), new BigDecimal("-1")));
        
        //negative
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-4.25"), BigMathUtility.divide(new BigDecimal("-17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("-0.0508474576271186440677966101694915254237288135593220338983050847"), BigMathUtility.divide(new BigDecimal("-9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.divide(new BigDecimal("-0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-4.25"), BigMathUtility.divide(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("-0.0508474576271186440677966101694915254237288135593220338983050847"), BigMathUtility.divide(new BigDecimal("9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.divide(new BigDecimal("0"), new BigDecimal("-88")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("4.25"), BigMathUtility.divide(new BigDecimal("-17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("0.0508474576271186440677966101694915254237288135593220338983050847"), BigMathUtility.divide(new BigDecimal("-9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.divide(new BigDecimal("-0"), new BigDecimal("-88")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("1E+1"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("1E+1"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.divide(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("1.6315789473684210526315789473684210526315789473684210526315789474"), BigMathUtility.divide(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("31"), BigMathUtility.divide(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("0.1542288557213930348258706467661691542288557213930348258706467662"), BigMathUtility.divide(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("0.6142401700318809776833156216790648246546227417640807651434643996"), BigMathUtility.divide(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("0.0005868371811103406193873541663916924973170981815154410264168108"), BigMathUtility.divide(new BigDecimal("0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("0.0000061424017003188097768331562167906482465462274176408076514346"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("8.9578533289871156210031800379215789709417192111117193604092743641"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertEquals(new BigDecimal("-1E+1"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("-1E+1"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.divide(new BigDecimal("-.1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("-1.6315789473684210526315789473684210526315789473684210526315789474"), BigMathUtility.divide(new BigDecimal("-3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("-31"), BigMathUtility.divide(new BigDecimal("-3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("-0.1542288557213930348258706467661691542288557213930348258706467662"), BigMathUtility.divide(new BigDecimal("-03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("-0.6142401700318809776833156216790648246546227417640807651434643996"), BigMathUtility.divide(new BigDecimal("-.578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("-0.0005868371811103406193873541663916924973170981815154410264168108"), BigMathUtility.divide(new BigDecimal("-0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("-0.0000061424017003188097768331562167906482465462274176408076514346"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("-8.9578533289871156210031800379215789709417192111117193604092743641"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941")));
        Assert.assertEquals(new BigDecimal("-1E+1"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("-1E+1"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.divide(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("-1.6315789473684210526315789473684210526315789473684210526315789474"), BigMathUtility.divide(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("-31"), BigMathUtility.divide(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("-0.1542288557213930348258706467661691542288557213930348258706467662"), BigMathUtility.divide(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("-0.6142401700318809776833156216790648246546227417640807651434643996"), BigMathUtility.divide(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("-0.0005868371811103406193873541663916924973170981815154410264168108"), BigMathUtility.divide(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("-0.0000061424017003188097768331562167906482465462274176408076514346"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("-8.9578533289871156210031800379215789709417192111117193604092743641"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        Assert.assertEquals(new BigDecimal("1E+1"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("1E+1"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.divide(new BigDecimal("-.1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("1.6315789473684210526315789473684210526315789473684210526315789474"), BigMathUtility.divide(new BigDecimal("-3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("31"), BigMathUtility.divide(new BigDecimal("-3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("0.1542288557213930348258706467661691542288557213930348258706467662"), BigMathUtility.divide(new BigDecimal("-03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("0.6142401700318809776833156216790648246546227417640807651434643996"), BigMathUtility.divide(new BigDecimal("-.578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("0.0005868371811103406193873541663916924973170981815154410264168108"), BigMathUtility.divide(new BigDecimal("-0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("0.0000061424017003188097768331562167906482465462274176408076514346"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("8.9578533289871156210031800379215789709417192111117193604092743641"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.divide(new BigDecimal("1.0"), new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("1"), null));
        
        //precision, negative
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.divide(new BigDecimal("-1.0"), new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.00000000"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.divide(new BigDecimal("-1"), new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000006142401700"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 15));
        Assert.assertEquals(new BigDecimal("0.0000061424017"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 13));
        Assert.assertEquals(new BigDecimal("0.000006142402"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 12));
        Assert.assertEquals(new BigDecimal("0.0000061424"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("0.000006142"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000006"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("8.957853328987116"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 15));
        Assert.assertEquals(new BigDecimal("8.9578533289871"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 13));
        Assert.assertEquals(new BigDecimal("8.957853328987"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 12));
        Assert.assertEquals(new BigDecimal("8.9578533290"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("8.957853329"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 9));
        Assert.assertEquals(new BigDecimal("8.957853"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("8.958"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("8.96"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("9.0"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.divide(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.divide(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.divide(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.divide(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 1));
        
        //custom precision, negative
        Assert.assertEquals(new BigDecimal("-0.000006142401700"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 15));
        Assert.assertEquals(new BigDecimal("-0.0000061424017"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 13));
        Assert.assertEquals(new BigDecimal("-0.000006142402"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 12));
        Assert.assertEquals(new BigDecimal("-0.0000061424"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.000006142"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 9));
        Assert.assertEquals(new BigDecimal("-0.000006"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("-8.957853328987116"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 15));
        Assert.assertEquals(new BigDecimal("-8.9578533289871"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 13));
        Assert.assertEquals(new BigDecimal("-8.957853328987"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 12));
        Assert.assertEquals(new BigDecimal("-8.9578533290"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-8.957853329"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 9));
        Assert.assertEquals(new BigDecimal("-8.957853"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("-8.958"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-8.96"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-9.0"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-2.99999999999999"), BigMathUtility.divide(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 14));
        Assert.assertEquals(new BigDecimal("-3.0000000000"), BigMathUtility.divide(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("-3.00000"), BigMathUtility.divide(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 5));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.divide(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 1));
        Assert.assertEquals(new BigDecimal("-0.000006142401700"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 15));
        Assert.assertEquals(new BigDecimal("-0.0000061424017"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 13));
        Assert.assertEquals(new BigDecimal("-0.000006142402"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 12));
        Assert.assertEquals(new BigDecimal("-0.0000061424"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.000006142"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 9));
        Assert.assertEquals(new BigDecimal("-0.000006"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.divide(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("-8.957853328987116"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 15));
        Assert.assertEquals(new BigDecimal("-8.9578533289871"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 13));
        Assert.assertEquals(new BigDecimal("-8.957853328987"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 12));
        Assert.assertEquals(new BigDecimal("-8.9578533290"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-8.957853329"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 9));
        Assert.assertEquals(new BigDecimal("-8.957853"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("-8.958"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-8.96"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-9.0"), BigMathUtility.divide(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-2.99999999999999"), BigMathUtility.divide(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 14));
        Assert.assertEquals(new BigDecimal("-3.0000000000"), BigMathUtility.divide(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("-3.00000"), BigMathUtility.divide(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 5));
        Assert.assertEquals(new BigDecimal("-3.0"), BigMathUtility.divide(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 1));
        Assert.assertEquals(new BigDecimal("0.000006142401700"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 15));
        Assert.assertEquals(new BigDecimal("0.0000061424017"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 13));
        Assert.assertEquals(new BigDecimal("0.000006142402"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 12));
        Assert.assertEquals(new BigDecimal("0.0000061424"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("0.000006142"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000006"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.divide(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("8.957853328987116"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 15));
        Assert.assertEquals(new BigDecimal("8.9578533289871"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 13));
        Assert.assertEquals(new BigDecimal("8.957853328987"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 12));
        Assert.assertEquals(new BigDecimal("8.9578533290"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("8.957853329"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 9));
        Assert.assertEquals(new BigDecimal("8.957853"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("8.958"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("8.96"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("9.0"), BigMathUtility.divide(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.divide(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.divide(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.divide(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.divide(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 1));
        
        //larger cases
        Assert.assertEquals(
                new BigDecimal("0.0000000000026223138268334470936228458885653236991922531664445308"),
                BigMathUtility.divide(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("381342610395.1644686525787761778068482368292582851082174091447092024910096499"),
                BigMathUtility.divide(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.000000000000000000000000000000089144977163172159799930593788901"),
                BigMathUtility.divide(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781"),
                BigMathUtility.divide(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.0000000000026223138268334470936228458885653236991922531664445308"),
                BigMathUtility.divide(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("381342610395.1644686525787761778068482368292582851082174091797929770547804939"),
                BigMathUtility.divide(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                new BigDecimal("-0.0000000000026223138268334470936228458885653236991922531664445308"),
                BigMathUtility.divide(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-381342610395.1644686525787761778068482368292582851082174091447092024910096499"),
                BigMathUtility.divide(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.000000000000000000000000000000089144977163172159799930593788901"),
                BigMathUtility.divide(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781"),
                BigMathUtility.divide(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.0000000000026223138268334470936228458885653236991922531664445308"),
                BigMathUtility.divide(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-381342610395.1644686525787761778068482368292582851082174091797929770547804939"),
                BigMathUtility.divide(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.0000000000026223138268334470936228458885653236991922531664445308"),
                BigMathUtility.divide(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-381342610395.1644686525787761778068482368292582851082174091447092024910096499"),
                BigMathUtility.divide(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.000000000000000000000000000000089144977163172159799930593788901"),
                BigMathUtility.divide(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781"),
                BigMathUtility.divide(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.0000000000026223138268334470936228458885653236991922531664445308"),
                BigMathUtility.divide(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-381342610395.1644686525787761778068482368292582851082174091797929770547804939"),
                BigMathUtility.divide(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.0000000000026223138268334470936228458885653236991922531664445308"),
                BigMathUtility.divide(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("381342610395.1644686525787761778068482368292582851082174091447092024910096499"),
                BigMathUtility.divide(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.000000000000000000000000000000089144977163172159799930593788901"),
                BigMathUtility.divide(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781"),
                BigMathUtility.divide(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.0000000000026223138268334470936228458885653236991922531664445308"),
                BigMathUtility.divide(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("381342610395.1644686525787761778068482368292582851082174091797929770547804939"),
                BigMathUtility.divide(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //invalid divisor
        TestUtils.assertException(ArithmeticException.class, "Cannot divide by zero", () ->
                BigMathUtility.divide(new BigDecimal("0549"), new BigDecimal("0")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.divide(null, new BigDecimal("2")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.divide(new BigDecimal("2"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.divide(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.divide(null, new BigDecimal("2"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.divide(new BigDecimal("2"), null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.divide(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.divide(null, new BigDecimal("2"), 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.divide(new BigDecimal("2"), null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.divide(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.divide(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.divide(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.divide(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
    /**
     * JUnit test of mod.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#mod(BigDecimal, BigDecimal, int)
     * @see BigMathUtility#mod(BigDecimal, BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#mod(BigDecimal, BigDecimal)
     */
    @Test
    public void testMod() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.mod(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("9"), BigMathUtility.mod(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("9"), new BigDecimal("-1")));
        
        //negative
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.mod(new BigDecimal("-17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("-9"), BigMathUtility.mod(new BigDecimal("-9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.mod(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("9"), BigMathUtility.mod(new BigDecimal("9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("0"), new BigDecimal("-88")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.mod(new BigDecimal("-17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("-9"), BigMathUtility.mod(new BigDecimal("-9"), new BigDecimal("-177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-0"), new BigDecimal("-88")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("1.2"), BigMathUtility.mod(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("3.1"), BigMathUtility.mod(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("0.578"), BigMathUtility.mod(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("0.578"), BigMathUtility.mod(new BigDecimal("0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("1.9247200578"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-.1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("-1.2"), BigMathUtility.mod(new BigDecimal("-3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("-3.1"), BigMathUtility.mod(new BigDecimal("-03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("-0.578"), BigMathUtility.mod(new BigDecimal("-.578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("-0.578"), BigMathUtility.mod(new BigDecimal("-0.578"), new BigDecimal("984.941")));
        Assert.assertEquals(new BigDecimal("-0.0000000578"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("-1.9247200578"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("1.2"), BigMathUtility.mod(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("3.1"), BigMathUtility.mod(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("0.578"), BigMathUtility.mod(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("0.578"), BigMathUtility.mod(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("1.9247200578"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-.1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("-1.2"), BigMathUtility.mod(new BigDecimal("-3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("-3.1"), BigMathUtility.mod(new BigDecimal("-03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("-0.578"), BigMathUtility.mod(new BigDecimal("-.578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("-0.578"), BigMathUtility.mod(new BigDecimal("-0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("-0.0000000578"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("-1.9247200578"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1.0"), new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("1"), null));
        
        //precision, negative
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1.0"), new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.mod(new BigDecimal("-1"), new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("1.924720057800000"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 15));
        Assert.assertEquals(new BigDecimal("1.9247200578000"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 13));
        Assert.assertEquals(new BigDecimal("1.924720057800"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 12));
        Assert.assertEquals(new BigDecimal("1.9247200578"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("1.924720058"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 9));
        Assert.assertEquals(new BigDecimal("1.924720"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("1.925"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("1.92"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("1.9"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("0.99999999999999"), BigMathUtility.mod(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 14));
        Assert.assertEquals(new BigDecimal("1.0000000000"), BigMathUtility.mod(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("1.00000"), BigMathUtility.mod(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 5));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.mod(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 1));
        
        //custom precision, negative
        Assert.assertEquals(new BigDecimal("-0.000000057800000"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 15));
        Assert.assertEquals(new BigDecimal("-0.0000000578000"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 13));
        Assert.assertEquals(new BigDecimal("-0.000000057800"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 12));
        Assert.assertEquals(new BigDecimal("-0.0000000578"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.000000058"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("-1.924720057800000"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 15));
        Assert.assertEquals(new BigDecimal("-1.9247200578000"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 13));
        Assert.assertEquals(new BigDecimal("-1.924720057800"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 12));
        Assert.assertEquals(new BigDecimal("-1.9247200578"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-1.924720058"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 9));
        Assert.assertEquals(new BigDecimal("-1.924720"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("-1.925"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-1.92"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-1.9"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-0.99999999999999"), BigMathUtility.mod(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 14));
        Assert.assertEquals(new BigDecimal("-1.0000000000"), BigMathUtility.mod(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("-1.00000"), BigMathUtility.mod(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 5));
        Assert.assertEquals(new BigDecimal("-1.0"), BigMathUtility.mod(new BigDecimal("-2.99999999999999"), new BigDecimal("1"), 1));
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.mod(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("1.924720057800000"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 15));
        Assert.assertEquals(new BigDecimal("1.9247200578000"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 13));
        Assert.assertEquals(new BigDecimal("1.924720057800"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 12));
        Assert.assertEquals(new BigDecimal("1.9247200578"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("1.924720058"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 9));
        Assert.assertEquals(new BigDecimal("1.924720"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("1.925"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("1.92"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("1.9"), BigMathUtility.mod(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("0.99999999999999"), BigMathUtility.mod(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 14));
        Assert.assertEquals(new BigDecimal("1.0000000000"), BigMathUtility.mod(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("1.00000"), BigMathUtility.mod(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 5));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.mod(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 1));
        Assert.assertEquals(new BigDecimal("-0.000000057800000"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 15));
        Assert.assertEquals(new BigDecimal("-0.0000000578000"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 13));
        Assert.assertEquals(new BigDecimal("-0.000000057800"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 12));
        Assert.assertEquals(new BigDecimal("-0.0000000578"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("-0.000000058"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.mod(new BigDecimal("-.0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("-1.924720057800000"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 15));
        Assert.assertEquals(new BigDecimal("-1.9247200578000"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 13));
        Assert.assertEquals(new BigDecimal("-1.924720057800"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 12));
        Assert.assertEquals(new BigDecimal("-1.9247200578"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("-1.924720058"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 9));
        Assert.assertEquals(new BigDecimal("-1.924720"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("-1.925"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("-1.92"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("-1.9"), BigMathUtility.mod(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("-0.99999999999999"), BigMathUtility.mod(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 14));
        Assert.assertEquals(new BigDecimal("-1.0000000000"), BigMathUtility.mod(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("-1.00000"), BigMathUtility.mod(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 5));
        Assert.assertEquals(new BigDecimal("-1.0"), BigMathUtility.mod(new BigDecimal("-2.99999999999999"), new BigDecimal("-1"), 1));
        
        //larger cases
        Assert.assertEquals(
                new BigDecimal("15649826500097987154602319879456339009444871265"),
                BigMathUtility.mod(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("2573905877562740580297797195670688273455187889"),
                BigMathUtility.mod(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.0000000000000000000000000000000489454940894843663168974515098988"),
                BigMathUtility.mod(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.0000000000000000000000000000000095023522339419134067499237792953"),
                BigMathUtility.mod(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988"),
                BigMathUtility.mod(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566"),
                BigMathUtility.mod(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                new BigDecimal("-15649826500097987154602319879456339009444871265"),
                BigMathUtility.mod(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-2573905877562740580297797195670688273455187889"),
                BigMathUtility.mod(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.0000000000000000000000000000000489454940894843663168974515098988"),
                BigMathUtility.mod(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.0000000000000000000000000000000095023522339419134067499237792953"),
                BigMathUtility.mod(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988"),
                BigMathUtility.mod(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566"),
                BigMathUtility.mod(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("15649826500097987154602319879456339009444871265"),
                BigMathUtility.mod(
                        new BigDecimal("15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("2573905877562740580297797195670688273455187889"),
                BigMathUtility.mod(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.0000000000000000000000000000000489454940894843663168974515098988"),
                BigMathUtility.mod(
                        new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("0.0000000000000000000000000000000095023522339419134067499237792953"),
                BigMathUtility.mod(
                        new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988"),
                BigMathUtility.mod(
                        new BigDecimal("15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566"),
                BigMathUtility.mod(
                        new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-15649826500097987154602319879456339009444871265"),
                BigMathUtility.mod(
                        new BigDecimal("-15649826500097987154602319879456339009444871265"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-2573905877562740580297797195670688273455187889"),
                BigMathUtility.mod(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.0000000000000000000000000000000489454940894843663168974515098988"),
                BigMathUtility.mod(
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-0.0000000000000000000000000000000095023522339419134067499237792953"),
                BigMathUtility.mod(
                        new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988"),
                BigMathUtility.mod(
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"),
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
                )
        );
        Assert.assertEquals(
                new BigDecimal("-2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566"),
                BigMathUtility.mod(
                        new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                        new BigDecimal("-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549")
                )
        );
        
        //invalid divisor
        TestUtils.assertException(ArithmeticException.class, "Cannot divide by zero", () ->
                BigMathUtility.mod(new BigDecimal("0549"), new BigDecimal("0")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mod(null, new BigDecimal("2")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mod(new BigDecimal("2"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mod(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mod(null, new BigDecimal("2"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mod(new BigDecimal("2"), null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mod(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mod(null, new BigDecimal("2"), 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mod(new BigDecimal("2"), null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mod(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.mod(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.mod(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.mod(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
    /**
     * JUnit test of power.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#power(BigDecimal, BigDecimal, int)
     * @see BigMathUtility#power(BigDecimal, BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#power(BigDecimal, BigDecimal)
     */
    @Test
    public void testPower() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("83521"), BigMathUtility.power(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("7960203505214079922596627255169838497787047322828694156142117910690491617566742105220063689523875143398306807872117412260518969904597664896897486680228885639266332044169"), BigMathUtility.power(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.power(new BigDecimal("0"), new BigDecimal("88")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("0549"), new BigDecimal("0")));
        
        //negative
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("0.0000119730367213036242382155386070569078435363561260042384549993"), BigMathUtility.power(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000019403252174826328"), BigMathUtility.power(new BigDecimal("9"), new BigDecimal("-50")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("0549"), new BigDecimal("-0")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("0.7943282347242815020659182828363879325889606317554843320923239293"), BigMathUtility.power(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("8.5819744708515433237089273766814486911245451273740734442610709486"), BigMathUtility.power(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("1.1197889288345146071831413740562719532493883819177179451078698313"), BigMathUtility.power(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("7522635966.6749226381185704632188586848126005617185129642614558334105950269"), BigMathUtility.power(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("0.5969996767174540961674861214368860564978206903586129549726970645"), BigMathUtility.power(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("0.004298576318225759690022273201971798742713621049755334924106211"), BigMathUtility.power(new BigDecimal("0.578"), new BigDecimal("9.941")));
        Assert.assertEquals(new BigDecimal("0.8548496553984821559145185197132255021001018761158728967864167369"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("332.9332170560748796291677077768639059501997058681864257213118194601"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("1.2589254117941672104239541063958006060936174094669310691079230195"), BigMathUtility.power(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("0.1165233016477122380003268859579887568417677816771818881485816682"), BigMathUtility.power(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("0.8930254392145206372225730881042090209286727499868963001312248646"), BigMathUtility.power(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("0.0000000001329321270403052091710434166742584104992395066858522505"), BigMathUtility.power(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("1.6750427831023373855356029315347448400113294641181765184245490238"), BigMathUtility.power(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("3069254478712442726368134501384516054452292932994473569305301262650456077411768362483242150924003353261119501597964322174177341844445236641962645768645641188185060160572001023801965593053780344728160087751668369537615514465170567341324.7274103625509027509977636938888447303544039462539967897369128548"), BigMathUtility.power(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("1.1697963421811956297586846961524296945858030609856244543434384263"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("0.0030036053742020376122100772870281232149079136512395701659532183"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1.0"), new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.power(new BigDecimal("1"), new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.854849655398482"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 15));
        Assert.assertEquals(new BigDecimal("0.8548496553985"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 13));
        Assert.assertEquals(new BigDecimal("0.854849655398"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 12));
        Assert.assertEquals(new BigDecimal("0.8548496554"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 10));
        Assert.assertEquals(new BigDecimal("0.854849655"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 9));
        Assert.assertEquals(new BigDecimal("0.854850"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 6));
        Assert.assertEquals(new BigDecimal("0.855"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 3));
        Assert.assertEquals(new BigDecimal("0.85"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 2));
        Assert.assertEquals(new BigDecimal("0.9"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal(".00941"), 1));
        Assert.assertEquals(new BigDecimal("332.933217056074880"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 15));
        Assert.assertEquals(new BigDecimal("332.9332170560749"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 13));
        Assert.assertEquals(new BigDecimal("332.933217056075"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 12));
        Assert.assertEquals(new BigDecimal("332.9332170561"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("332.933217056"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 9));
        Assert.assertEquals(new BigDecimal("332.933217"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("332.933"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("332.93"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("332.9"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.power(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.power(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.power(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.power(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 1));
        
        //custom precision, negative
        Assert.assertEquals(new BigDecimal("1.169796342181196"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 15));
        Assert.assertEquals(new BigDecimal("1.1697963421812"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 13));
        Assert.assertEquals(new BigDecimal("1.169796342181"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 12));
        Assert.assertEquals(new BigDecimal("1.1697963422"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 10));
        Assert.assertEquals(new BigDecimal("1.169796342"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 9));
        Assert.assertEquals(new BigDecimal("1.169796"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 6));
        Assert.assertEquals(new BigDecimal("1.170"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 3));
        Assert.assertEquals(new BigDecimal("1.17"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 2));
        Assert.assertEquals(new BigDecimal("1.2"), BigMathUtility.power(new BigDecimal(".0000000578"), new BigDecimal("-.00941"), 1));
        Assert.assertEquals(new BigDecimal("0.003003605374202"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 15));
        Assert.assertEquals(new BigDecimal("0.0030036053742"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 13));
        Assert.assertEquals(new BigDecimal("0.003003605374"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 12));
        Assert.assertEquals(new BigDecimal("0.0030036054"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("0.003003605"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 9));
        Assert.assertEquals(new BigDecimal("0.003004"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.003"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.power(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("0.33333333333333"), BigMathUtility.power(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 14));
        Assert.assertEquals(new BigDecimal("0.3333333333"), BigMathUtility.power(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("0.33333"), BigMathUtility.power(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 5));
        Assert.assertEquals(new BigDecimal("0.3"), BigMathUtility.power(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.power(null, new BigDecimal("2")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.power(new BigDecimal("2"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.power(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.power(null, new BigDecimal("2"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.power(new BigDecimal("2"), null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.power(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.power(null, new BigDecimal("2"), 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.power(new BigDecimal("2"), null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.power(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.power(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.power(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.power(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#root(BigDecimal, BigDecimal, int)
     * @see BigMathUtility#root(BigDecimal, BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#root(BigDecimal, BigDecimal)
     */
    @Test
    public void testRoot() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("2.0305431848689307178670594733633386532430700031031400799571665478"), BigMathUtility.root(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertEquals(new BigDecimal("1.0124910679451132345095913456226846565491587770562236112132003255"), BigMathUtility.root(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.root(new BigDecimal("0"), new BigDecimal("88")));
        
        //negative
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("0.492479060505452326504424784829822551365761865581268607366424297"), BigMathUtility.root(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertEquals(new BigDecimal("0.9570070779813869713074626695181980893729131502075731940006351718"), BigMathUtility.root(new BigDecimal("9"), new BigDecimal("-50")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("0.0000000001"), BigMathUtility.root(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("1.8138919683222467296576624749558020090228037579642605673476495722"), BigMathUtility.root(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertEquals(new BigDecimal("81962.8286980801"), BigMathUtility.root(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("1.0579030163444754474619230745795692523521649663181500343460914296"), BigMathUtility.root(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertEquals(new BigDecimal("0.5584713404830014300064202482968069916221478150084999563624704728"), BigMathUtility.root(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertEquals(new BigDecimal("0.9463493488067071397052452650369088434934874310004880110138953471"), BigMathUtility.root(new BigDecimal("0.578"), new BigDecimal("9.941")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertEquals(new BigDecimal("4.2140244745683787111819132714516190061175605006126904318302040206"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("1E+10"), BigMathUtility.root(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertEquals(new BigDecimal("0.5513007485914095721430511858984092694446184590851318960365024668"), BigMathUtility.root(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertEquals(new BigDecimal("0.0000122006526114858702155650677564629168732634669806726488718195"), BigMathUtility.root(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertEquals(new BigDecimal("0.9452662338136098017669418987962490812251510876944860421982048497"), BigMathUtility.root(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertEquals(new BigDecimal("1.7906021804720302858502912366668021476211023928749588351828935813"), BigMathUtility.root(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertEquals(new BigDecimal("1.0005567175975761298214226757423347928668370568555705134626797141"), BigMathUtility.root(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertEquals(new BigDecimal("1.5466433495295412499023611679537033631383377069071860256091919717954555618746195215761497736288772707382375752568911674707134443470866066103504779855486983583751316437950986008868049165095774057300631205677165355804916360902067252274690819222348061110476389209016437182982924353393295210298843002027977692102025727209864091478462962487401528904611880600824686359234237451443768697535106664024704138935506678165219668487717463368314786245112249351123975878934532980032897390671120033970530220117657359293732955044E+769"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertEquals(new BigDecimal("0.2373028457796095168943210118868724235991924357822924566320684476"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1.0"), new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.root(new BigDecimal("1"), new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000020328536"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".941"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000203285"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".941"), 13));
        Assert.assertEquals(new BigDecimal("0.000000020329"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".941"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000203"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".941"), 10));
        Assert.assertEquals(new BigDecimal("0.000000020"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".941"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".941"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".941"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".941"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal(".941"), 1));
        Assert.assertEquals(new BigDecimal("4.214024474568379"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 15));
        Assert.assertEquals(new BigDecimal("4.2140244745684"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 13));
        Assert.assertEquals(new BigDecimal("4.214024474568"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 12));
        Assert.assertEquals(new BigDecimal("4.2140244746"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 10));
        Assert.assertEquals(new BigDecimal("4.214024475"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 9));
        Assert.assertEquals(new BigDecimal("4.214024"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 6));
        Assert.assertEquals(new BigDecimal("4.214"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 3));
        Assert.assertEquals(new BigDecimal("4.21"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 2));
        Assert.assertEquals(new BigDecimal("4.2"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("2.00941"), 1));
        Assert.assertEquals(new BigDecimal("2.99999999999999"), BigMathUtility.root(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 14));
        Assert.assertEquals(new BigDecimal("3.0000000000"), BigMathUtility.root(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("3.00000"), BigMathUtility.root(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 5));
        Assert.assertEquals(new BigDecimal("3.0"), BigMathUtility.root(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 1));
        
        //custom precision, negative
        Assert.assertEquals(new BigDecimal("49191934.502173645154754"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.941"), 15));
        Assert.assertEquals(new BigDecimal("49191934.5021736451548"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.941"), 13));
        Assert.assertEquals(new BigDecimal("49191934.502173645155"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.941"), 12));
        Assert.assertEquals(new BigDecimal("49191934.5021736452"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.941"), 10));
        Assert.assertEquals(new BigDecimal("49191934.502173645"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.941"), 9));
        Assert.assertEquals(new BigDecimal("49191934.502174"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.941"), 6));
        Assert.assertEquals(new BigDecimal("49191934.502"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.941"), 3));
        Assert.assertEquals(new BigDecimal("49191934.50"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.941"), 2));
        Assert.assertEquals(new BigDecimal("49191934.5"), BigMathUtility.root(new BigDecimal(".0000000578"), new BigDecimal("-.941"), 1));
        Assert.assertEquals(new BigDecimal("0.237302845779610"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 15));
        Assert.assertEquals(new BigDecimal("0.2373028457796"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 13));
        Assert.assertEquals(new BigDecimal("0.237302845780"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 12));
        Assert.assertEquals(new BigDecimal("0.2373028458"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 10));
        Assert.assertEquals(new BigDecimal("0.237302846"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 9));
        Assert.assertEquals(new BigDecimal("0.237303"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 6));
        Assert.assertEquals(new BigDecimal("0.237"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 3));
        Assert.assertEquals(new BigDecimal("0.24"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 2));
        Assert.assertEquals(new BigDecimal("0.2"), BigMathUtility.root(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941"), 1));
        Assert.assertEquals(new BigDecimal("0.33333333333333"), BigMathUtility.root(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 14));
        Assert.assertEquals(new BigDecimal("0.3333333333"), BigMathUtility.root(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("0.33333"), BigMathUtility.root(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 5));
        Assert.assertEquals(new BigDecimal("0.3"), BigMathUtility.root(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 1));
        
        //imaginary numbers
        TestUtils.assertException(ArithmeticException.class, "Illegal root(x) for x < 0: x = -1", () ->
                BigMathUtility.root(new BigDecimal("-1"), new BigDecimal("1")));
        TestUtils.assertException(ArithmeticException.class, "Division by zero", () ->
                BigMathUtility.root(new BigDecimal("0549"), new BigDecimal("0")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.root(null, new BigDecimal("2")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.root(new BigDecimal("2"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.root(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.root(null, new BigDecimal("2"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.root(new BigDecimal("2"), null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.root(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.root(null, new BigDecimal("2"), 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.root(new BigDecimal("2"), null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.root(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.root(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.root(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.root(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#sqrt(BigDecimal, int)
     * @see BigMathUtility#sqrt(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#sqrt(BigDecimal)
     */
    @Test
    public void testSqrt() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.sqrt(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("4.1231056256176605498214098559740770251471992253736204343986335731"), BigMathUtility.sqrt(new BigDecimal("17")));
        Assert.assertEquals(new BigDecimal("3"), BigMathUtility.sqrt(new BigDecimal("9")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sqrt(new BigDecimal("0")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.3162277660168379331998893544432718533719555139325216826857504853"), BigMathUtility.sqrt(new BigDecimal("0.1")));
        Assert.assertEquals(new BigDecimal("0.3162277660168379331998893544432718533719555139325216826857504853"), BigMathUtility.sqrt(new BigDecimal(".1")));
        Assert.assertEquals(new BigDecimal("1.7606816861659009145769228176496579286155228680517596833957365273"), BigMathUtility.sqrt(new BigDecimal("3.1")));
        Assert.assertEquals(new BigDecimal("1.7606816861659009145769228176496579286155228680517596833957365273"), BigMathUtility.sqrt(new BigDecimal("03.1")));
        Assert.assertEquals(new BigDecimal("0.7602631123499284967791190473686339200498102422679187462521050634"), BigMathUtility.sqrt(new BigDecimal(".578")));
        Assert.assertEquals(new BigDecimal("0.7602631123499284967791190473686339200498102422679187462521050634"), BigMathUtility.sqrt(new BigDecimal("0.578")));
        Assert.assertEquals(new BigDecimal("0.0002404163056034261582962870831156486733568442188140811724400356"), BigMathUtility.sqrt(new BigDecimal(".0000000578")));
        Assert.assertEquals(new BigDecimal("4.2426406939310804663671160857775699474512953036348794594149578547"), BigMathUtility.sqrt(new BigDecimal("18.0000000578")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.sqrt(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.sqrt(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.sqrt(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.sqrt(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.sqrt(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.sqrt(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.sqrt(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.sqrt(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.sqrt(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000240416305603"), BigMathUtility.sqrt(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0002404163056"), BigMathUtility.sqrt(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.000240416306"), BigMathUtility.sqrt(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0002404163"), BigMathUtility.sqrt(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.000240416"), BigMathUtility.sqrt(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.000240"), BigMathUtility.sqrt(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.sqrt(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.sqrt(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.sqrt(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("4.242640693931080"), BigMathUtility.sqrt(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("4.2426406939311"), BigMathUtility.sqrt(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("4.242640693931"), BigMathUtility.sqrt(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("4.2426406939"), BigMathUtility.sqrt(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("4.242640694"), BigMathUtility.sqrt(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("4.242641"), BigMathUtility.sqrt(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("4.243"), BigMathUtility.sqrt(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("4.24"), BigMathUtility.sqrt(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("4.2"), BigMathUtility.sqrt(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.73205080756887"), BigMathUtility.sqrt(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("1.7320508076"), BigMathUtility.sqrt(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("1.73205"), BigMathUtility.sqrt(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("1.7"), BigMathUtility.sqrt(new BigDecimal("2.99999999999999"), 1));
        
        //imaginary numbers
        TestUtils.assertException(ArithmeticException.class, "Illegal sqrt(x) for x < 0: x = -1", () ->
                BigMathUtility.sqrt(new BigDecimal("-1")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.sqrt(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.sqrt(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.sqrt(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.sqrt(new BigDecimal("15s5")));
    }
    
    /**
     * JUnit test of log.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#log(BigDecimal, int, int)
     * @see BigMathUtility#log(BigDecimal, int, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#log(BigDecimal, int)
     */
    @Test
    public void testLog() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log(new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("7.7772464838640963795913548138936262173008734442830041081204282002"), BigMathUtility.log(new BigDecimal("59875132"), 10));
        Assert.assertEquals(new BigDecimal("6.4588633989030237852940101777646830166306059006880449171282885624"), BigMathUtility.log(new BigDecimal("59875132"), 16));
        Assert.assertEquals(new BigDecimal("7.7416033471438101343043086923917835687985581359699429124459764835"), BigMathUtility.log(new BigDecimal("8976464100065468"), 115));
        
        //decimal
        Assert.assertEquals(new BigDecimal("-1.2295235577981631081928840810289268477073326768745561420897153278"), BigMathUtility.log(new BigDecimal(".058949"), 10));
        Assert.assertEquals(new BigDecimal("-0.0054883516015224846240309445288773850725933116441204479526233334"), BigMathUtility.log(new BigDecimal(".9874421200001"), 10));
        Assert.assertEquals(new BigDecimal("-8.5876501260611484010681707592423294502392943685268343302579861858"), BigMathUtility.log(new BigDecimal(".0000000000456498"), 16));
        Assert.assertEquals(new BigDecimal("-0.1203288744667847051395786262414959272464250468884981793858640109"), BigMathUtility.log(new BigDecimal(".5649871212025644980798794213200031654"), 115));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log(new BigDecimal("1"), 10, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log(new BigDecimal("1.0"), 10, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log(new BigDecimal("1"), 10, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.log(new BigDecimal("1"), 10, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.log(new BigDecimal("1"), 10, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.log(new BigDecimal("1"), 10, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.log(new BigDecimal("1"), 10, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.log(new BigDecimal("1"), 10, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log(new BigDecimal("1"), 10, null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("-7.238072161579471"), BigMathUtility.log(new BigDecimal(".0000000578"), 10, 15));
        Assert.assertEquals(new BigDecimal("-7.2380721615795"), BigMathUtility.log(new BigDecimal(".0000000578"), 10, 13));
        Assert.assertEquals(new BigDecimal("-7.238072161579"), BigMathUtility.log(new BigDecimal(".0000000578"), 10, 12));
        Assert.assertEquals(new BigDecimal("-7.2380721616"), BigMathUtility.log(new BigDecimal(".0000000578"), 10, 10));
        Assert.assertEquals(new BigDecimal("-7.238072162"), BigMathUtility.log(new BigDecimal(".0000000578"), 10, 9));
        Assert.assertEquals(new BigDecimal("-7.238072"), BigMathUtility.log(new BigDecimal(".0000000578"), 10, 6));
        Assert.assertEquals(new BigDecimal("-7.238"), BigMathUtility.log(new BigDecimal(".0000000578"), 10, 3));
        Assert.assertEquals(new BigDecimal("-7.24"), BigMathUtility.log(new BigDecimal(".0000000578"), 10, 2));
        Assert.assertEquals(new BigDecimal("-7.2"), BigMathUtility.log(new BigDecimal(".0000000578"), 10, 1));
        Assert.assertEquals(new BigDecimal("1.255272506497874"), BigMathUtility.log(new BigDecimal("18.0000000578"), 10, 15));
        Assert.assertEquals(new BigDecimal("1.2552725064979"), BigMathUtility.log(new BigDecimal("18.0000000578"), 10, 13));
        Assert.assertEquals(new BigDecimal("1.255272506498"), BigMathUtility.log(new BigDecimal("18.0000000578"), 10, 12));
        Assert.assertEquals(new BigDecimal("1.2552725065"), BigMathUtility.log(new BigDecimal("18.0000000578"), 10, 10));
        Assert.assertEquals(new BigDecimal("1.255272506"), BigMathUtility.log(new BigDecimal("18.0000000578"), 10, 9));
        Assert.assertEquals(new BigDecimal("1.255273"), BigMathUtility.log(new BigDecimal("18.0000000578"), 10, 6));
        Assert.assertEquals(new BigDecimal("1.255"), BigMathUtility.log(new BigDecimal("18.0000000578"), 10, 3));
        Assert.assertEquals(new BigDecimal("1.26"), BigMathUtility.log(new BigDecimal("18.0000000578"), 10, 2));
        Assert.assertEquals(new BigDecimal("1.3"), BigMathUtility.log(new BigDecimal("18.0000000578"), 10, 1));
        Assert.assertEquals(new BigDecimal("0.47712125471966"), BigMathUtility.log(new BigDecimal("2.99999999999999"), 10, 14));
        Assert.assertEquals(new BigDecimal("0.4771212547"), BigMathUtility.log(new BigDecimal("2.99999999999999"), 10, 10));
        Assert.assertEquals(new BigDecimal("0.47712"), BigMathUtility.log(new BigDecimal("2.99999999999999"), 10, 5));
        Assert.assertEquals(new BigDecimal("0.5"), BigMathUtility.log(new BigDecimal("2.99999999999999"), 10, 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot handle imaginary numbers", () ->
                BigMathUtility.log(new BigDecimal("-88"), 10));
        
        //invalid base
        TestUtils.assertException(ArithmeticException.class, "Cannot take a log with base: 0", () ->
                BigMathUtility.log(new BigDecimal("88"), 0));
        TestUtils.assertException(ArithmeticException.class, "Cannot take a log with base: 1", () ->
                BigMathUtility.log(new BigDecimal("88"), 1));
        TestUtils.assertException(ArithmeticException.class, "Cannot take a log with base: -1", () ->
                BigMathUtility.log(new BigDecimal("88"), -1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.log(null, 10));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.log(null, 10, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.log(null, 10, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.log(new BigDecimal("15s5"), 10));
    }
    
    /**
     * JUnit test of log10.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#log10(BigDecimal, int)
     * @see BigMathUtility#log10(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#log10(BigDecimal)
     */
    @Test
    public void testLog10() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log10(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("7.7772464838640963795913548138936262173008734442830041081204282002"), BigMathUtility.log10(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("15.9531052983335411068962592236934206525044278008590552005073530174"), BigMathUtility.log10(new BigDecimal("8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("-1.2295235577981631081928840810289268477073326768745561420897153278"), BigMathUtility.log10(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("-0.0054883516015224846240309445288773850725933116441204479526233334"), BigMathUtility.log10(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("-10.3405611208479002705892249718096440146004590828010092355155979251"), BigMathUtility.log10(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.2479614517458840895185617233623326176436035990178915280974126274"), BigMathUtility.log10(new BigDecimal(".5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log10(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log10(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log10(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.log10(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.log10(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.log10(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.log10(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.log10(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log10(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("-7.238072161579471"), BigMathUtility.log10(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("-7.2380721615795"), BigMathUtility.log10(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("-7.238072161579"), BigMathUtility.log10(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("-7.2380721616"), BigMathUtility.log10(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("-7.238072162"), BigMathUtility.log10(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("-7.238072"), BigMathUtility.log10(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("-7.238"), BigMathUtility.log10(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("-7.24"), BigMathUtility.log10(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("-7.2"), BigMathUtility.log10(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.255272506497874"), BigMathUtility.log10(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("1.2552725064979"), BigMathUtility.log10(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("1.255272506498"), BigMathUtility.log10(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("1.2552725065"), BigMathUtility.log10(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("1.255272506"), BigMathUtility.log10(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("1.255273"), BigMathUtility.log10(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("1.255"), BigMathUtility.log10(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("1.26"), BigMathUtility.log10(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("1.3"), BigMathUtility.log10(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("0.47712125471966"), BigMathUtility.log10(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("0.4771212547"), BigMathUtility.log10(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("0.47712"), BigMathUtility.log10(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("0.5"), BigMathUtility.log10(new BigDecimal("2.99999999999999"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot handle imaginary numbers", () ->
                BigMathUtility.log10(new BigDecimal("-88")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.log10(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.log10(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.log10(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.log10(new BigDecimal("15s5")));
    }
    
    /**
     * JUnit test of log2.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#log2(BigDecimal, int)
     * @see BigMathUtility#log2(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#log2(BigDecimal)
     */
    @Test
    public void testLog2() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log2(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("25.8354535956120951411760407110587320665224236027521796685131542495"), BigMathUtility.log2(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("52.9950686912306265588542243859600621340148673884330424543045365237"), BigMathUtility.log2(new BigDecimal("8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("-4.0843888499755837217099496089968796326627069362951711314842385558"), BigMathUtility.log2(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("-0.0182319093797175914083162419197591209958004061785861436462674006"), BigMathUtility.log2(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("-34.3506005042445936042726830369693178009571774741073373210319447433"), BigMathUtility.log2(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.8237101130037093618418817457943766871976077197708825098385726642"), BigMathUtility.log2(new BigDecimal(".5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log2(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log2(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log2(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.log2(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.log2(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.log2(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.log2(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.log2(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.log2(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("-24.044355266372945"), BigMathUtility.log2(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("-24.0443552663729"), BigMathUtility.log2(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("-24.044355266373"), BigMathUtility.log2(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("-24.0443552664"), BigMathUtility.log2(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("-24.044355266"), BigMathUtility.log2(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("-24.044355"), BigMathUtility.log2(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("-24.044"), BigMathUtility.log2(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("-24.04"), BigMathUtility.log2(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("-24.0"), BigMathUtility.log2(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("4.169925006074966"), BigMathUtility.log2(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("4.1699250060750"), BigMathUtility.log2(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("4.169925006075"), BigMathUtility.log2(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("4.1699250061"), BigMathUtility.log2(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("4.169925006"), BigMathUtility.log2(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("4.169925"), BigMathUtility.log2(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("4.170"), BigMathUtility.log2(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("4.17"), BigMathUtility.log2(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("4.2"), BigMathUtility.log2(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.58496250072115"), BigMathUtility.log2(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("1.5849625007"), BigMathUtility.log2(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("1.58496"), BigMathUtility.log2(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("1.6"), BigMathUtility.log2(new BigDecimal("2.99999999999999"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot handle imaginary numbers", () ->
                BigMathUtility.log2(new BigDecimal("-88")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.log2(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.log2(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.log2(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.log2(new BigDecimal("15s5")));
    }
    
    /**
     * JUnit test of ln.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#ln(BigDecimal, int)
     * @see BigMathUtility#ln(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#ln(BigDecimal)
     */
    @Test
    public void testLn() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.ln(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("17.9077718182858251785274312729302818977540155002079702638520349514"), BigMathUtility.ln(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("36.7333824469071396648015133798406311377520440118648690819591645191"), BigMathUtility.ln(new BigDecimal("8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("-2.8310826156710533039370274436619723487535459125338690312395922065"), BigMathUtility.ln(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("-0.01263739658277566981995953491747700296236180670053491641928599"), BigMathUtility.ln(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("-23.810021890058175715064614897181291146313170435974947745688483307"), BigMathUtility.ln(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.5709523424272350877962652126686863544530223365935135039341903949"), BigMathUtility.ln(new BigDecimal(".5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.ln(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.ln(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.ln(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.ln(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.ln(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.ln(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.ln(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.ln(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.ln(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("-16.666277061268079"), BigMathUtility.ln(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("-16.6662770612681"), BigMathUtility.ln(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("-16.666277061268"), BigMathUtility.ln(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("-16.6662770613"), BigMathUtility.ln(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("-16.666277061"), BigMathUtility.ln(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("-16.666277"), BigMathUtility.ln(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("-16.666"), BigMathUtility.ln(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("-16.67"), BigMathUtility.ln(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("-16.7"), BigMathUtility.ln(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("2.890371761107276"), BigMathUtility.ln(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("2.8903717611073"), BigMathUtility.ln(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("2.890371761107"), BigMathUtility.ln(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("2.8903717611"), BigMathUtility.ln(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("2.890371761"), BigMathUtility.ln(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("2.890372"), BigMathUtility.ln(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("2.890"), BigMathUtility.ln(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("2.89"), BigMathUtility.ln(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("2.9"), BigMathUtility.ln(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.09861228866811"), BigMathUtility.ln(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("1.0986122887"), BigMathUtility.ln(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("1.09861"), BigMathUtility.ln(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("1.1"), BigMathUtility.ln(new BigDecimal("2.99999999999999"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot handle imaginary numbers", () ->
                BigMathUtility.ln(new BigDecimal("-88")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.ln(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.ln(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.ln(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.ln(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of exp.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#exp(BigDecimal, int)
     * @see BigMathUtility#exp(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#exp(BigDecimal)
     */
    @Test
    public void testExp() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277"), BigMathUtility.exp(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("2980.9579870417282747435920994528886737559679391328357022089635303877"), BigMathUtility.exp(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("78962960182680.6951609780226351082242199561951153523306550800205987543078540199"), BigMathUtility.exp(new BigDecimal("32")));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000060546018954011858845318605"), BigMathUtility.exp(new BigDecimal("-88")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("1.0607211425823960124895609618468854219978420526398202114475258501"), BigMathUtility.exp(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("2.6843594140529377897494899599910939339616794738454523774341626372"), BigMathUtility.exp(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("1.000000000045649800001041952120035854968629677276036737347425951"), BigMathUtility.exp(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("1.7594251232961364761446178758448439790607929366893508782787876251"), BigMathUtility.exp(new BigDecimal(".5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277"), BigMathUtility.exp(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277"), BigMathUtility.exp(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("3"), BigMathUtility.exp(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("2.71828183"), BigMathUtility.exp(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("2.7182818284590452"), BigMathUtility.exp(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277"), BigMathUtility.exp(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138217852516642742746639193200305992181741359662904357290033429526059563073813232862794349076323382988075319525101901157383418793070215408914993488416750924476146066808226480016847741185374234544243710753907774499206955170276183860626133138458300075204493382656029760673711320070932870912744374704723069697720931014169283681902551510865746377211125238978442505695369677078544996996794686445490598793163688923009879312773617821540"), BigMathUtility.exp(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695517027618386062613313845830007520449338265602976067371132007093287091274437470472306969772093101416928368190255151086574637721112523897844250569536967707854499699679468644549059879316368892300987931277361782154000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.exp(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277"), BigMathUtility.exp(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("1.000000057800002"), BigMathUtility.exp(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("1.0000000578000"), BigMathUtility.exp(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("1.000000057800"), BigMathUtility.exp(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("1.0000000578"), BigMathUtility.exp(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("1.000000058"), BigMathUtility.exp(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("1.000000"), BigMathUtility.exp(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("1.000"), BigMathUtility.exp(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("1.00"), BigMathUtility.exp(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.exp(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("65659972.932476836956218"), BigMathUtility.exp(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("65659972.9324768369562"), BigMathUtility.exp(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("65659972.932476836956"), BigMathUtility.exp(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("65659972.9324768370"), BigMathUtility.exp(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("65659972.932476837"), BigMathUtility.exp(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("65659972.932477"), BigMathUtility.exp(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("65659972.932"), BigMathUtility.exp(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("65659972.93"), BigMathUtility.exp(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("65659972.9"), BigMathUtility.exp(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("20.08553692318747"), BigMathUtility.exp(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("20.0855369232"), BigMathUtility.exp(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("20.08554"), BigMathUtility.exp(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("20.1"), BigMathUtility.exp(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.exp(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.exp(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.exp(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.exp(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of factorial.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#factorial(BigDecimal, int)
     * @see BigMathUtility#factorial(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#factorial(BigDecimal)
     */
    @Test
    public void testFactorial() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.factorial(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.factorial(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("4.032E+4"), BigMathUtility.factorial(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("2.6313083693369353016721801216E+35"), BigMathUtility.factorial(new BigDecimal("32")));
        Assert.assertEquals(new BigDecimal("2.0819292731331122049075765558938466910692791867935684166748909912740932236977610817081137223686786521362976645774724100985848483509348330596063876936258377056662135846239829976923688287858732942189082584418406750934601648762608536525504401643989906421574066757600134825702587290599161403758846294930409449230964940594815939859149853391122264704693990670877693823942444284976246238272814460357902592005380773772572746610890648578074026289535899227108387840716108290946671616043366737788167576109635536782715502562E+20016"), BigMathUtility.factorial(new BigDecimal("5987")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.factorial(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.factorial(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.factorial(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.factorial(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.factorial(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.factorial(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.factorial(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.factorial(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.factorial(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("1.000000000000000"), BigMathUtility.factorial(new BigDecimal("1"), 15));
        Assert.assertEquals(new BigDecimal("1.0000000000000"), BigMathUtility.factorial(new BigDecimal("1"), 13));
        Assert.assertEquals(new BigDecimal("1.000000000000"), BigMathUtility.factorial(new BigDecimal("1"), 12));
        Assert.assertEquals(new BigDecimal("1.0000000000"), BigMathUtility.factorial(new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("1.000000000"), BigMathUtility.factorial(new BigDecimal("1"), 9));
        Assert.assertEquals(new BigDecimal("1.000000"), BigMathUtility.factorial(new BigDecimal("1"), 6));
        Assert.assertEquals(new BigDecimal("1.000"), BigMathUtility.factorial(new BigDecimal("1"), 3));
        Assert.assertEquals(new BigDecimal("1.00"), BigMathUtility.factorial(new BigDecimal("1"), 2));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.factorial(new BigDecimal("1"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot take the factorial of a number less than 0", () ->
                BigMathUtility.factorial(new BigDecimal("-88")));
        TestUtils.assertException(ArithmeticException.class, "Cannot take the factorial of a number that is not an integer", () ->
                BigMathUtility.factorial(new BigDecimal("8.058949")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.factorial(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.factorial(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.factorial(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.factorial(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of gamma.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#gamma(BigDecimal, int)
     * @see BigMathUtility#gamma(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#gamma(BigDecimal)
     */
    @Test
    public void testGamma() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.gamma(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("5.04E+3"), BigMathUtility.gamma(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("8.22283865417792281772556288E+33"), BigMathUtility.gamma(new BigDecimal("32")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.gamma(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.gamma(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.gamma(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.gamma(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.gamma(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.gamma(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.gamma(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.gamma(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.gamma(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("1.000000000000000"), BigMathUtility.gamma(new BigDecimal("1"), 15));
        Assert.assertEquals(new BigDecimal("1.0000000000000"), BigMathUtility.gamma(new BigDecimal("1"), 13));
        Assert.assertEquals(new BigDecimal("1.000000000000"), BigMathUtility.gamma(new BigDecimal("1"), 12));
        Assert.assertEquals(new BigDecimal("1.0000000000"), BigMathUtility.gamma(new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("1.000000000"), BigMathUtility.gamma(new BigDecimal("1"), 9));
        Assert.assertEquals(new BigDecimal("1.000000"), BigMathUtility.gamma(new BigDecimal("1"), 6));
        Assert.assertEquals(new BigDecimal("1.000"), BigMathUtility.gamma(new BigDecimal("1"), 3));
        Assert.assertEquals(new BigDecimal("1.00"), BigMathUtility.gamma(new BigDecimal("1"), 2));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.gamma(new BigDecimal("1"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot take the gamma of a number less than or equal to 0", () ->
                BigMathUtility.gamma(new BigDecimal("0")));
        TestUtils.assertException(ArithmeticException.class, "Cannot take the gamma of a number less than or equal to 0", () ->
                BigMathUtility.gamma(new BigDecimal("-88")));
        TestUtils.assertException(ArithmeticException.class, "Cannot take the gamma of a number that is not an integer", () ->
                BigMathUtility.gamma(new BigDecimal("0.058949")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.gamma(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.gamma(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.gamma(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.gamma(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of reciprocal.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#reciprocal(BigDecimal, int)
     * @see BigMathUtility#reciprocal(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#reciprocal(BigDecimal)
     */
    @Test
    public void testReciprocal() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.reciprocal(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.0000000167014245580285317784351606940924990361607887561734310665"), BigMathUtility.reciprocal(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("0.0000000000000001114024396301776225097687998785101202832809969624"), BigMathUtility.reciprocal(new BigDecimal("8976464100065468")));
        Assert.assertEquals(new BigDecimal("-0.0113636363636363636363636363636363636363636363636363636363636364"), BigMathUtility.reciprocal(new BigDecimal("-88")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("16.9638161800878725678128551799012705898318885816553291828529746052"), BigMathUtility.reciprocal(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("1.012717585917743440234662286490246672656976568239497726047149022"), BigMathUtility.reciprocal(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("21905901011.6145087163580125214130182388531822702399572396812253284789856692"), BigMathUtility.reciprocal(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("1.7699518492944029285618439558735248732689751542591527095301915305"), BigMathUtility.reciprocal(new BigDecimal(".5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.reciprocal(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.reciprocal(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.reciprocal(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.reciprocal(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.reciprocal(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.reciprocal(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.reciprocal(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.reciprocal(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.reciprocal(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("17301038.062283737024221"), BigMathUtility.reciprocal(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("17301038.0622837370242"), BigMathUtility.reciprocal(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("17301038.062283737024"), BigMathUtility.reciprocal(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("17301038.0622837370"), BigMathUtility.reciprocal(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("17301038.062283737"), BigMathUtility.reciprocal(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("17301038.062284"), BigMathUtility.reciprocal(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("17301038.062"), BigMathUtility.reciprocal(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("17301038.06"), BigMathUtility.reciprocal(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("17301038.1"), BigMathUtility.reciprocal(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("0.055555555377160"), BigMathUtility.reciprocal(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0555555553772"), BigMathUtility.reciprocal(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.055555555377"), BigMathUtility.reciprocal(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0555555554"), BigMathUtility.reciprocal(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.055555555"), BigMathUtility.reciprocal(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.055556"), BigMathUtility.reciprocal(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.056"), BigMathUtility.reciprocal(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.06"), BigMathUtility.reciprocal(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.1"), BigMathUtility.reciprocal(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("0.33333333333333"), BigMathUtility.reciprocal(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("0.3333333333"), BigMathUtility.reciprocal(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("0.33333"), BigMathUtility.reciprocal(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("0.3"), BigMathUtility.reciprocal(new BigDecimal("2.99999999999999"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot divide by zero", () ->
                BigMathUtility.reciprocal(new BigDecimal("0")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.reciprocal(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.reciprocal(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.reciprocal(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.reciprocal(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of sin.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#sin(BigDecimal, int)
     * @see BigMathUtility#sin(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#sin(BigDecimal)
     */
    @Test
    public void testSin() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sin(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0.84147098480789650665250232163029899962256306079837106567275171"), BigMathUtility.sin(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.9887268693155572100594417202811589868635848078878924573178952055"), BigMathUtility.sin(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("0.2383372052864161529120185202768111857587228700438293794092371984"), BigMathUtility.sin(new BigDecimal("8976464100065468")));
        Assert.assertEquals(new BigDecimal("-0.84147098480789650665250232163029899962256306079837106567275171"), BigMathUtility.sin(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-0.9887268693155572100594417202811589868635848078878924573178952055"), BigMathUtility.sin(new BigDecimal("-59875132")));
        Assert.assertEquals(new BigDecimal("-0.2383372052864161529120185202768111857587228700438293794092371984"), BigMathUtility.sin(new BigDecimal("-8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.0589148647869660758248794842111068958178113890100723079283948464"), BigMathUtility.sin(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("0.8346197623573961989965635400607378987462777110968244036415045414"), BigMathUtility.sin(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("0.0000000000456497999999999999999841450313705036680000016520118176"), BigMathUtility.sin(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.5354049386812795177902675880563185927218239542553910821939165109"), BigMathUtility.sin(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-0.0589148647869660758248794842111068958178113890100723079283948464"), BigMathUtility.sin(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-0.8346197623573961989965635400607378987462777110968244036415045414"), BigMathUtility.sin(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-0.0000000000456497999999999999999841450313705036680000016520118176"), BigMathUtility.sin(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.5354049386812795177902675880563185927218239542553910821939165109"), BigMathUtility.sin(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sin(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sin(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sin(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.sin(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.sin(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.sin(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.sin(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.sin(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sin(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.sin(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.sin(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.sin(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.sin(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.sin(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.sin(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.sin(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.sin(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.sin(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("-0.750987208605369"), BigMathUtility.sin(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("-0.7509872086054"), BigMathUtility.sin(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("-0.750987208605"), BigMathUtility.sin(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("-0.7509872086"), BigMathUtility.sin(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("-0.750987209"), BigMathUtility.sin(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("-0.750987"), BigMathUtility.sin(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("-0.751"), BigMathUtility.sin(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("-0.75"), BigMathUtility.sin(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("-0.8"), BigMathUtility.sin(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("0.14112000805988"), BigMathUtility.sin(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("0.1411200081"), BigMathUtility.sin(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("0.14112"), BigMathUtility.sin(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("0.1"), BigMathUtility.sin(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.sin(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.sin(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.sin(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.sin(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of asin.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#asin(BigDecimal, int)
     * @see BigMathUtility#asin(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#asin(BigDecimal)
     */
    @Test
    public void testAsin() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asin(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.asin(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.asin(new BigDecimal("-1")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.0589831946432107470709666338410392420549923625915029627094655333"), BigMathUtility.asin(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("1.4121504837013315733856448346542194274921801471177022446621884583"), BigMathUtility.asin(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("0.0000000000456498000000000000000158549686294963320000148681063587"), BigMathUtility.asin(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.6004176447202814336032066140794371275975021614740801114811587924"), BigMathUtility.asin(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-0.0589831946432107470709666338410392420549923625915029627094655333"), BigMathUtility.asin(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-1.4121504837013315733856448346542194274921801471177022446621884583"), BigMathUtility.asin(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-0.0000000000456498000000000000000158549686294963320000148681063587"), BigMathUtility.asin(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.6004176447202814336032066140794371275975021614740801114811587924"), BigMathUtility.asin(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asin(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asin(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asin(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.asin(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.asin(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.asin(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.asin(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.asin(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asin(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.asin(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.asin(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.asin(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.asin(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.asin(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.asin(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.asin(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.asin(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.asin(new BigDecimal(".0000000578"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = 59875132", () ->
                BigMathUtility.asin(new BigDecimal("59875132")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = 8976464100065468", () ->
                BigMathUtility.asin(new BigDecimal("8976464100065468")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = -59875132", () ->
                BigMathUtility.asin(new BigDecimal("-59875132")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = -8976464100065468", () ->
                BigMathUtility.asin(new BigDecimal("-8976464100065468")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = 18.0000000578", () ->
                BigMathUtility.asin(new BigDecimal("18.0000000578")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = 2.99999999999999", () ->
                BigMathUtility.asin(new BigDecimal("2.99999999999999")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.asin(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.asin(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.asin(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.asin(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of sinh.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#sinh(BigDecimal, int)
     * @see BigMathUtility#sinh(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#sinh(BigDecimal)
     */
    @Test
    public void testSinh() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sinh(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1.175201193643801456882381850595600815155717981334095870229565413"), BigMathUtility.sinh(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("1490.478825789550186115876639031881446447474314116350990944801492906"), BigMathUtility.sinh(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("107321789892958.0323121488807633009971120612543453068258577876221075511591878698"), BigMathUtility.sinh(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("-1.175201193643801456882381850595600815155717981334095870229565413"), BigMathUtility.sinh(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-1490.478825789550186115876639031881446447474314116350990944801492906"), BigMathUtility.sinh(new BigDecimal("-8")));
        Assert.assertEquals(new BigDecimal("-107321789892958.0323121488807633009971120612543453068258577876221075511591878698"), BigMathUtility.sinh(new BigDecimal("-33")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.0589831470770291255041457997953890764607511701723486081538511883"), BigMathUtility.sinh(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("1.1559155289203475265453560615865545756569057240305867132546368664"), BigMathUtility.sinh(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("0.0000000000456498000000000000000158549686294963320000016520118176"), BigMathUtility.sinh(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.5955288283481301135426784576349972391683072352752568991925517737"), BigMathUtility.sinh(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-0.0589831470770291255041457997953890764607511701723486081538511883"), BigMathUtility.sinh(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-1.1559155289203475265453560615865545756569057240305867132546368664"), BigMathUtility.sinh(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-0.0000000000456498000000000000000158549686294963320000016520118176"), BigMathUtility.sinh(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.5955288283481301135426784576349972391683072352752568991925517737"), BigMathUtility.sinh(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sinh(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.sinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.sinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.sinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.sinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.sinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.sinh(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.sinh(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.sinh(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.sinh(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.sinh(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.sinh(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.sinh(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.sinh(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.sinh(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.sinh(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("32829986.466238410863119"), BigMathUtility.sinh(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("32829986.4662384108631"), BigMathUtility.sinh(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("32829986.466238410863"), BigMathUtility.sinh(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("32829986.4662384109"), BigMathUtility.sinh(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("32829986.466238411"), BigMathUtility.sinh(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("32829986.466238"), BigMathUtility.sinh(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("32829986.466"), BigMathUtility.sinh(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("32829986.47"), BigMathUtility.sinh(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("32829986.5"), BigMathUtility.sinh(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("10.01787492740980"), BigMathUtility.sinh(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("10.0178749274"), BigMathUtility.sinh(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("10.01787"), BigMathUtility.sinh(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("10.0"), BigMathUtility.sinh(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.sinh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.sinh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.sinh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.sinh(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of asinh.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#asinh(BigDecimal, int)
     * @see BigMathUtility#asinh(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#asinh(BigDecimal)
     */
    @Test
    public void testAsinh() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asinh(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0.8813735870195430252326093249797923090281603282616354107532956087"), BigMathUtility.asinh(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("18.6009189988457705576790589612680856850210353465978847311035447278"), BigMathUtility.asinh(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("37.4265296274670849742187455012988108084534330350675490218116388292"), BigMathUtility.asinh(new BigDecimal("8976464100065468")));
        Assert.assertEquals(new BigDecimal("-0.8813735870195430252326093249797923090281603282616354107532956087"), BigMathUtility.asinh(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-18.6009189988457705576790589612680856850210353465978847311035447278"), BigMathUtility.asinh(new BigDecimal("-59875132")));
        Assert.assertEquals(new BigDecimal("-37.4265296274670849742187455012988108084534330350675490218116388292"), BigMathUtility.asinh(new BigDecimal("-8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.058914912133267958365901008212414384935023065357773423545078109"), BigMathUtility.asinh(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("0.8724658890992712179094633459986864838961800933695003534977581999"), BigMathUtility.asinh(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("0.0000000000456497999999999999999841450313705036680000148681063587"), BigMathUtility.asinh(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.5385707378000174054224768157374569685354018007944244697415971121"), BigMathUtility.asinh(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-0.058914912133267958365901008212414384935023065357773423545078109"), BigMathUtility.asinh(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-0.8724658890992712179094633459986864838961800933695003534977581999"), BigMathUtility.asinh(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-0.0000000000456497999999999999999841450313705036680000148681063587"), BigMathUtility.asinh(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.5385707378000174054224768157374569685354018007944244697415971121"), BigMathUtility.asinh(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asinh(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.asinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.asinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.asinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.asinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.asinh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.asinh(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.asinh(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.asinh(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.asinh(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.asinh(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.asinh(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.asinh(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.asinh(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.asinh(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.asinh(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("3.584289655067495"), BigMathUtility.asinh(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("3.5842896550675"), BigMathUtility.asinh(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("3.584289655067"), BigMathUtility.asinh(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("3.5842896551"), BigMathUtility.asinh(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("3.584289655"), BigMathUtility.asinh(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("3.584290"), BigMathUtility.asinh(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("3.584"), BigMathUtility.asinh(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("3.58"), BigMathUtility.asinh(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("3.6"), BigMathUtility.asinh(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.81844645923206"), BigMathUtility.asinh(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("1.8184464592"), BigMathUtility.asinh(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("1.81845"), BigMathUtility.asinh(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("1.8"), BigMathUtility.asinh(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.asinh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.asinh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.asinh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.asinh(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of cos.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#cos(BigDecimal, int)
     * @see BigMathUtility#cos(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#cos(BigDecimal)
     */
    @Test
    public void testCos() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cos(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0.5403023058681397174009366074429766037323104206179222276700972554"), BigMathUtility.cos(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.149730350608876403296990024816258956415716863988028005537882937"), BigMathUtility.cos(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("0.9711824630707973838326749665224886322531798596915813909431857908"), BigMathUtility.cos(new BigDecimal("8976464100065468")));
        Assert.assertEquals(new BigDecimal("0.5403023058681397174009366074429766037323104206179222276700972554"), BigMathUtility.cos(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("0.149730350608876403296990024816258956415716863988028005537882937"), BigMathUtility.cos(new BigDecimal("-59875132")));
        Assert.assertEquals(new BigDecimal("0.9711824630707973838326749665224886322531798596915813909431857908"), BigMathUtility.cos(new BigDecimal("-8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.9982630107878051116510740873288762374519730576301470929098243326"), BigMathUtility.cos(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("0.5508265174104125270456038278746384315487855991166391417084306408"), BigMathUtility.cos(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("0.9999999999999999999989580478799800000000001809440367356954141334"), BigMathUtility.cos(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.8445954958651480516250212993590999182184375833356161421610919771"), BigMathUtility.cos(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("0.9982630107878051116510740873288762374519730576301470929098243326"), BigMathUtility.cos(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("0.5508265174104125270456038278746384315487855991166391417084306408"), BigMathUtility.cos(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("0.9999999999999999999989580478799800000000001809440367356954141334"), BigMathUtility.cos(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.8445954958651480516250212993590999182184375833356161421610919771"), BigMathUtility.cos(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cos(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cos(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cos(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.cos(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.cos(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.cos(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.cos(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.cos(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cos(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.999999999999998"), BigMathUtility.cos(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("1.0000000000000"), BigMathUtility.cos(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("1.000000000000"), BigMathUtility.cos(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("1.0000000000"), BigMathUtility.cos(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("1.000000000"), BigMathUtility.cos(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("1.000000"), BigMathUtility.cos(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("1.000"), BigMathUtility.cos(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("1.00"), BigMathUtility.cos(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.cos(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("0.660316751651142"), BigMathUtility.cos(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.6603167516511"), BigMathUtility.cos(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.660316751651"), BigMathUtility.cos(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.6603167517"), BigMathUtility.cos(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.660316752"), BigMathUtility.cos(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.660317"), BigMathUtility.cos(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.660"), BigMathUtility.cos(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.66"), BigMathUtility.cos(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.7"), BigMathUtility.cos(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("-0.98999249660044"), BigMathUtility.cos(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("-0.9899924966"), BigMathUtility.cos(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("-0.98999"), BigMathUtility.cos(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("-1.0"), BigMathUtility.cos(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.cos(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.cos(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.cos(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.cos(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of acos.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#acos(BigDecimal, int)
     * @see BigMathUtility#acos(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#acos(BigDecimal)
     */
    @Test
    public void testAcos() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acos(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.acos(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923"), BigMathUtility.acos(new BigDecimal("-1")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("1.5118131321516858721603550577987122000435923370960499477780067629"), BigMathUtility.acos(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("0.1586458430935650458456768569855320146064045525698506658252838379"), BigMathUtility.acos(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("1.5707963267492468192313216916397355871299552033555528956193659374"), BigMathUtility.acos(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.9703786820746151856281150775603143145010825382134727990063135038"), BigMathUtility.acos(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("1.6297795214381073663022883254807906841535770622790558731969378294"), BigMathUtility.acos(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("2.9829468104962281926169665262939708695907648468052551551496607544"), BigMathUtility.acos(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("1.5707963268405464192313216916397672970672141960195529253555786549"), BigMathUtility.acos(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("2.1712139715151780528345283057191885696960868611616330219686310885"), BigMathUtility.acos(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acos(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acos(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("2"), BigMathUtility.acos(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.57079633"), BigMathUtility.acos(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966"), BigMathUtility.acos(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acos(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.57079632679489661923132169163975144209858469968755291048747229615390820314310449931401741267105853399107404325664115332354692230477529111586267970406424055872514205135096926055277982231147447746519098221440548783296672306423782411689339158263560095457282428346173017430522716332410669680363012457063686229350330315779408744076046048141462704585768218394629518000566526527441023326069207347597075580471652863518287979597654609305869096630589655255927403723118998137478367594287636244561396909150597456491683668120"), BigMathUtility.acos(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722961539082031431044993140174126710585339910740432566411533235469223047752911158626797040642405587251420513509692605527798223114744774651909822144054878329667230642378241168933915826356009545728242834617301743052271633241066968036301245706368622935033031577940874407604604814146270458576821839462951800056652652744102332606920734759707558047165286351828797959765460930586909663058965525592740372311899813747836759428763624456139690915059745649168366812000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.acos(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acos(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("1.570796268994897"), BigMathUtility.acos(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("1.5707962689949"), BigMathUtility.acos(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("1.570796268995"), BigMathUtility.acos(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("1.5707962690"), BigMathUtility.acos(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("1.570796269"), BigMathUtility.acos(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("1.570796"), BigMathUtility.acos(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("1.571"), BigMathUtility.acos(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("1.57"), BigMathUtility.acos(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("1.6"), BigMathUtility.acos(new BigDecimal(".0000000578"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = 59875132", () ->
                BigMathUtility.acos(new BigDecimal("59875132")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = 8976464100065468", () ->
                BigMathUtility.acos(new BigDecimal("8976464100065468")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = -59875132", () ->
                BigMathUtility.acos(new BigDecimal("-59875132")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = -8976464100065468", () ->
                BigMathUtility.acos(new BigDecimal("-8976464100065468")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = 18.0000000578", () ->
                BigMathUtility.acos(new BigDecimal("18.0000000578")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = 2.99999999999999", () ->
                BigMathUtility.acos(new BigDecimal("2.99999999999999")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acos(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acos(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acos(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.acos(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of cosh.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#cosh(BigDecimal, int)
     * @see BigMathUtility#cosh(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#cosh(BigDecimal)
     */
    @Test
    public void testCosh() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cosh(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1.5430806348152437784779056207570616826015291123658637047374022147"), BigMathUtility.cosh(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("1490.4791612521780886277154604210072273084936250164847112641620374817"), BigMathUtility.cosh(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("107321789892958.0323121488807679598832571646517094910714013977905189491538819889"), BigMathUtility.cosh(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("1.5430806348152437784779056207570616826015291123658637047374022147"), BigMathUtility.cosh(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("1490.4791612521780886277154604210072273084936250164847112641620374817"), BigMathUtility.cosh(new BigDecimal("-8")));
        Assert.assertEquals(new BigDecimal("107321789892958.0323121488807679598832571646517094910714013977905189491538819889"), BigMathUtility.cosh(new BigDecimal("-33")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("1.0017379955053668869854151620514963455370908824674716032936746617"), BigMathUtility.cosh(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("1.5284438851325902632041338984045393583047737498148656641795257708"), BigMathUtility.cosh(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000010419521200200000000001809440367356954141334"), BigMathUtility.cosh(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("1.1638962949480063626019394182098467398924857014140939790862358513"), BigMathUtility.cosh(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("1.0017379955053668869854151620514963455370908824674716032936746617"), BigMathUtility.cosh(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("1.5284438851325902632041338984045393583047737498148656641795257708"), BigMathUtility.cosh(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000010419521200200000000001809440367356954141334"), BigMathUtility.cosh(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("1.1638962949480063626019394182098467398924857014140939790862358513"), BigMathUtility.cosh(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cosh(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cosh(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cosh(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000"), BigMathUtility.cosh(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000"), BigMathUtility.cosh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.cosh(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.cosh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.cosh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cosh(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("1.000000000000002"), BigMathUtility.cosh(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("1.0000000000000"), BigMathUtility.cosh(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("1.000000000000"), BigMathUtility.cosh(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("1.0000000000"), BigMathUtility.cosh(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("1.000000000"), BigMathUtility.cosh(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("1.000000"), BigMathUtility.cosh(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("1.000"), BigMathUtility.cosh(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("1.00"), BigMathUtility.cosh(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.cosh(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("32829986.466238426093098"), BigMathUtility.cosh(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("32829986.4662384260931"), BigMathUtility.cosh(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("32829986.466238426093"), BigMathUtility.cosh(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("32829986.4662384261"), BigMathUtility.cosh(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("32829986.466238426"), BigMathUtility.cosh(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("32829986.466238"), BigMathUtility.cosh(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("32829986.466"), BigMathUtility.cosh(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("32829986.47"), BigMathUtility.cosh(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("32829986.5"), BigMathUtility.cosh(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("10.06766199577767"), BigMathUtility.cosh(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("10.0676619958"), BigMathUtility.cosh(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("10.06766"), BigMathUtility.cosh(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("10.1"), BigMathUtility.cosh(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.cosh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.cosh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.cosh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.cosh(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of acosh.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#acosh(BigDecimal, int)
     * @see BigMathUtility#acosh(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#acosh(BigDecimal)
     */
    @Test
    public void testAcosh() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.acosh(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("18.6009189988457704182102678275088166579802206884124985184922551594"), BigMathUtility.acosh(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("37.4265296274670849742187455012988046032016552573826996503480502279"), BigMathUtility.acosh(new BigDecimal("8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.3416980281899250610129909174187108668752786117684185436719641118"), BigMathUtility.acosh(new BigDecimal("1.058949")));
        Assert.assertEquals(new BigDecimal("1.3096770600423673228657322678089338513711126708637894849504987827"), BigMathUtility.acosh(new BigDecimal("1.9874421200001")));
        Assert.assertEquals(new BigDecimal("0.0000095550824171906212424610277186890932663323498279701849900641"), BigMathUtility.acosh(new BigDecimal("1.0000000000456498")));
        Assert.assertEquals(new BigDecimal("1.0184168998681986666063927856588688638724554912576914541103577361"), BigMathUtility.acosh(new BigDecimal("1.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.acosh(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.acosh(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.acosh(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.acosh(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.acosh(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.acosh(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.acosh(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.acosh(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.acosh(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("3.5827464421382"), BigMathUtility.acosh(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("3.582746442138"), BigMathUtility.acosh(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("3.5827464421"), BigMathUtility.acosh(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("3.582746442"), BigMathUtility.acosh(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("3.582746"), BigMathUtility.acosh(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("3.583"), BigMathUtility.acosh(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("3.58"), BigMathUtility.acosh(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("3.6"), BigMathUtility.acosh(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.7627471740"), BigMathUtility.acosh(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("1.76275"), BigMathUtility.acosh(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("1.8"), BigMathUtility.acosh(new BigDecimal("2.99999999999999"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = 0", () ->
                BigMathUtility.acosh(new BigDecimal("0")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -1", () ->
                BigMathUtility.acosh(new BigDecimal("-1")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -59875132", () ->
                BigMathUtility.acosh(new BigDecimal("-59875132")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -8976464100065468", () ->
                BigMathUtility.acosh(new BigDecimal("-8976464100065468")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = 0.0000000578", () ->
                BigMathUtility.acosh(new BigDecimal(".0000000578")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -0.058949", () ->
                BigMathUtility.acosh(new BigDecimal("-.058949")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -0.9874421200001", () ->
                BigMathUtility.acosh(new BigDecimal("-.9874421200001")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -0.0000000000456498", () ->
                BigMathUtility.acosh(new BigDecimal("-.0000000000456498")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -0.5649871212025644980798794213200031654", () ->
                BigMathUtility.acosh(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acosh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acosh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acosh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.acosh(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of tan.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#tan(BigDecimal, int)
     * @see BigMathUtility#tan(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#tan(BigDecimal)
     */
    @Test
    public void testTan() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tan(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1.5574077246549022305069748074583601730872507723815200383839466057"), BigMathUtility.tan(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("6.6033831170161096142405723990811607213988054805311837427662663651"), BigMathUtility.tan(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("0.2454092967585245788497093354025717027307249360746822241398594704"), BigMathUtility.tan(new BigDecimal("8976464100065468")));
        Assert.assertEquals(new BigDecimal("-1.5574077246549022305069748074583601730872507723815200383839466057"), BigMathUtility.tan(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-6.6033831170161096142405723990811607213988054805311837427662663651"), BigMathUtility.tan(new BigDecimal("-59875132")));
        Assert.assertEquals(new BigDecimal("-0.2454092967585245788497093354025717027307249360746822241398594704"), BigMathUtility.tan(new BigDecimal("-8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.0590173773347285344013098330944501262058551767501590972262584092"), BigMathUtility.tan(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("1.5152134764338761983018002136921813421325326042915924530058092206"), BigMathUtility.tan(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("0.0000000000456498000000000000000317099372589926640000264321890822"), BigMathUtility.tan(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.6339187709411662540563153652515695063537225632345790291467568026"), BigMathUtility.tan(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-0.0590173773347285344013098330944501262058551767501590972262584092"), BigMathUtility.tan(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-1.5152134764338761983018002136921813421325326042915924530058092206"), BigMathUtility.tan(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-0.0000000000456498000000000000000317099372589926640000264321890822"), BigMathUtility.tan(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.6339187709411662540563153652515695063537225632345790291467568026"), BigMathUtility.tan(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tan(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tan(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tan(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.tan(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.tan(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.tan(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.tan(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.tan(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tan(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.tan(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.tan(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.tan(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.tan(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.tan(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.tan(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.tan(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.tan(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.tan(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("-1.137313579774408"), BigMathUtility.tan(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("-1.1373135797744"), BigMathUtility.tan(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("-1.137313579774"), BigMathUtility.tan(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("-1.1373135798"), BigMathUtility.tan(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("-1.137313580"), BigMathUtility.tan(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("-1.137314"), BigMathUtility.tan(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("-1.137"), BigMathUtility.tan(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("-1.14"), BigMathUtility.tan(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("-1.1"), BigMathUtility.tan(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("-0.14254654307429"), BigMathUtility.tan(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("-0.1425465431"), BigMathUtility.tan(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("-0.14255"), BigMathUtility.tan(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("-0.1"), BigMathUtility.tan(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.tan(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.tan(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.tan(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.tan(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of atan.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#atan(BigDecimal, int)
     * @see BigMathUtility#atan(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#atan(BigDecimal)
     */
    @Test
    public void testAtan() throws Exception {
        //satandard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0.7853981633974483096156608458198757210492923498437764552437361481"), BigMathUtility.atan(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("1.5707963100934720612027914660895862946184999257992802698645135631"), BigMathUtility.atan(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("1.570796326794896507828882061462128932329784821177893480664184621"), BigMathUtility.atan(new BigDecimal("8976464100065468")));
        Assert.assertEquals(new BigDecimal("-0.7853981633974483096156608458198757210492923498437764552437361481"), BigMathUtility.atan(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-1.5707963100934720612027914660895862946184999257992802698645135631"), BigMathUtility.atan(new BigDecimal("-59875132")));
        Assert.assertEquals(new BigDecimal("-1.570796326794896507828882061462128932329784821177893480664184621"), BigMathUtility.atan(new BigDecimal("-8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.0588808597264367788262377361220484197761735784956719355661059602"), BigMathUtility.atan(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("0.7790796332860257910538771336194885133263586933360305089561627823"), BigMathUtility.atan(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("0.0000000000456497999999999999999682900627410073360000396482836233"), BigMathUtility.atan(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.5142767788191687133609461231049609671307129760306840071436079287"), BigMathUtility.atan(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-0.0588808597264367788262377361220484197761735784956719355661059602"), BigMathUtility.atan(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-0.7790796332860257910538771336194885133263586933360305089561627823"), BigMathUtility.atan(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-0.0000000000456497999999999999999682900627410073360000396482836233"), BigMathUtility.atan(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.5142767788191687133609461231049609671307129760306840071436079287"), BigMathUtility.atan(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.atan(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.atan(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.atan(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.atan(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.atan(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.atan(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.atan(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.atan(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.atan(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.atan(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.atan(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.atan(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.atan(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.atan(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.515297821727026"), BigMathUtility.atan(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("1.5152978217270"), BigMathUtility.atan(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("1.515297821727"), BigMathUtility.atan(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("1.5152978217"), BigMathUtility.atan(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("1.515297822"), BigMathUtility.atan(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("1.515298"), BigMathUtility.atan(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("1.515"), BigMathUtility.atan(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("1.52"), BigMathUtility.atan(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("1.5"), BigMathUtility.atan(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.24904577239825"), BigMathUtility.atan(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("1.2490457724"), BigMathUtility.atan(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("1.24905"), BigMathUtility.atan(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("1.2"), BigMathUtility.atan(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.atan(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of atan2.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#atan2(BigDecimal, BigDecimal, int)
     * @see BigMathUtility#atan2(BigDecimal, BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#atan2(BigDecimal, BigDecimal)
     */
    @Test
    public void testAtan2() throws Exception {
        //satandard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.7853981633974483096156608458198757210492923498437764552437361481"), BigMathUtility.atan2(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("1.5707963100934720612027914660895862946184999257992802698645135631"), BigMathUtility.atan2(new BigDecimal("59875132"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("1.570796326794896507828882061462128932329784821177893480664184621"), BigMathUtility.atan2(new BigDecimal("8976464100065468"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-0.7853981633974483096156608458198757210492923498437764552437361481"), BigMathUtility.atan2(new BigDecimal("-1"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-1.5707963100934720612027914660895862946184999257992802698645135631"), BigMathUtility.atan2(new BigDecimal("-59875132"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-1.570796326794896507828882061462128932329784821177893480664184621"), BigMathUtility.atan2(new BigDecimal("-8976464100065468"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("2.3561944901923449288469825374596271631478770495313293657312084442"), BigMathUtility.atan2(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("1.5707963434963211772598519171899165895786694735758255511104310292"), BigMathUtility.atan2(new BigDecimal("59875132"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("1.5707963267948967306337613218173739518673845781972123403107599713"), BigMathUtility.atan2(new BigDecimal("8976464100065468"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-2.3561944901923449288469825374596271631478770495313293657312084442"), BigMathUtility.atan2(new BigDecimal("-1"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-1.5707963434963211772598519171899165895786694735758255511104310292"), BigMathUtility.atan2(new BigDecimal("-59875132"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948967306337613218173739518673845781972123403107599713"), BigMathUtility.atan2(new BigDecimal("-8976464100065468"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("1"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("59875132"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("8976464100065468"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("-1"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("-59875132"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("-8976464100065468"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("1.1722068912534580611863788464231873075622560323935635630021869362"), BigMathUtility.atan2(new BigDecimal("1"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("1.5707963197614006429719291769721871265534929272275367549065998079"), BigMathUtility.atan2(new BigDecimal("59875132"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("1.5707963267948965723162451865236040081258853536508026108183474352"), BigMathUtility.atan2(new BigDecimal("8976464100065468"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("-1.1722068912534580611863788464231873075622560323935635630021869362"), BigMathUtility.atan2(new BigDecimal("-1"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("-1.5707963197614006429719291769721871265534929272275367549065998079"), BigMathUtility.atan2(new BigDecimal("-59875132"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948965723162451865236040081258853536508026108183474352"), BigMathUtility.atan2(new BigDecimal("-8976464100065468"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("0.0000054214906751320125097633575167965987344790604548221826347343"), BigMathUtility.atan2(new BigDecimal("1"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("1.5677157401580536647605789234358615376532817568750299399353693848"), BigMathUtility.atan2(new BigDecimal("59875132"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("1.5707963267743483150278152415197783806248266392837327249941579295"), BigMathUtility.atan2(new BigDecimal("8976464100065468"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("-0.0000054214906751320125097633575167965987344790604548221826347343"), BigMathUtility.atan2(new BigDecimal("-1"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("-1.5677157401580536647605789234358615376532817568750299399353693848"), BigMathUtility.atan2(new BigDecimal("-59875132"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("-1.5707963267743483150278152415197783806248266392837327249941579295"), BigMathUtility.atan2(new BigDecimal("-8976464100065468"), new BigDecimal("184451.115")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.0588808597264367788262377361220484197761735784956719355661059602"), BigMathUtility.atan2(new BigDecimal(".058949"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.7790796332860257910538771336194885133263586933360305089561627823"), BigMathUtility.atan2(new BigDecimal(".9874421200001"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.0000000000456497999999999999999682900627410073360000396482836233"), BigMathUtility.atan2(new BigDecimal(".0000000000456498"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.5142767788191687133609461231049609671307129760306840071436079287"), BigMathUtility.atan2(new BigDecimal(".5649871212025644980798794213200031654"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-0.0588808597264367788262377361220484197761735784956719355661059602"), BigMathUtility.atan2(new BigDecimal("-.058949"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-0.7790796332860257910538771336194885133263586933360305089561627823"), BigMathUtility.atan2(new BigDecimal("-.9874421200001"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-0.0000000000456497999999999999999682900627410073360000396482836233"), BigMathUtility.atan2(new BigDecimal("-.0000000000456498"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-0.5142767788191687133609461231049609671307129760306840071436079287"), BigMathUtility.atan2(new BigDecimal("-.5649871212025644980798794213200031654"), new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("3.0827117938633564596364056471574544644209958208794338854088386321"), BigMathUtility.atan2(new BigDecimal(".058949"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("2.36251302030376744740876624966001437087081070603907531201878181"), BigMathUtility.atan2(new BigDecimal(".9874421200001"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("3.1415926535441434384626433832795345941344283920391057813266609691"), BigMathUtility.atan2(new BigDecimal(".0000000000456498"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("2.6273158747706245251016972601745419170664564233444218138313366636"), BigMathUtility.atan2(new BigDecimal(".5649871212025644980798794213200031654"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-3.0827117938633564596364056471574544644209958208794338854088386321"), BigMathUtility.atan2(new BigDecimal("-.058949"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-2.36251302030376744740876624966001437087081070603907531201878181"), BigMathUtility.atan2(new BigDecimal("-.9874421200001"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-3.1415926535441434384626433832795345941344283920391057813266609691"), BigMathUtility.atan2(new BigDecimal("-.0000000000456498"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-2.6273158747706245251016972601745419170664564233444218138313366636"), BigMathUtility.atan2(new BigDecimal("-.5649871212025644980798794213200031654"), new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal(".058949"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal(".9874421200001"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal(".0000000000456498"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal(".5649871212025644980798794213200031654"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("-.058949"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("-.9874421200001"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("-.0000000000456498"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("-1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.atan2(new BigDecimal("-.5649871212025644980798794213200031654"), new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0.1390740263848412026674342066544472935363088088656305832534203354"), BigMathUtility.atan2(new BigDecimal(".058949"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("1.1676666179209042963910839801885052796490360589098335599468441626"), BigMathUtility.atan2(new BigDecimal(".9874421200001"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("0.0000000001083979707051122986521340795528278634770153096330017606"), BigMathUtility.atan2(new BigDecimal(".0000000000456498"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("0.9302569884393380973507087360019915390032012318306688817914219126"), BigMathUtility.atan2(new BigDecimal(".5649871212025644980798794213200031654"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("-0.1390740263848412026674342066544472935363088088656305832534203354"), BigMathUtility.atan2(new BigDecimal("-.058949"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("-1.1676666179209042963910839801885052796490360589098335599468441626"), BigMathUtility.atan2(new BigDecimal("-.9874421200001"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("-0.0000000001083979707051122986521340795528278634770153096330017606"), BigMathUtility.atan2(new BigDecimal("-.0000000000456498"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("-0.9302569884393380973507087360019915390032012318306688817914219126"), BigMathUtility.atan2(new BigDecimal("-.5649871212025644980798794213200031654"), new BigDecimal("0.4211315")));
        Assert.assertEquals(new BigDecimal("0.0000003195914538114773283351706387351322518103857388544063302231"), BigMathUtility.atan2(new BigDecimal(".058949"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("0.0000053534082458144369149578773963068955135110120457257307574391"), BigMathUtility.atan2(new BigDecimal(".9874421200001"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("0.0000000000000002474899650240661326444136702562034822274201703837"), BigMathUtility.atan2(new BigDecimal(".0000000000456498"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("0.0000030630724091898144249293370279406619076964361679466042127893"), BigMathUtility.atan2(new BigDecimal(".5649871212025644980798794213200031654"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("-0.0000003195914538114773283351706387351322518103857388544063302231"), BigMathUtility.atan2(new BigDecimal("-.058949"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("-0.0000053534082458144369149578773963068955135110120457257307574391"), BigMathUtility.atan2(new BigDecimal("-.9874421200001"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("-0.0000000000000002474899650240661326444136702562034822274201703837"), BigMathUtility.atan2(new BigDecimal("-.0000000000456498"), new BigDecimal("184451.115")));
        Assert.assertEquals(new BigDecimal("-0.0000030630724091898144249293370279406619076964361679466042127893"), BigMathUtility.atan2(new BigDecimal("-.5649871212025644980798794213200031654"), new BigDecimal("184451.115")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan2(new BigDecimal("0.0"), new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("1"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("1"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("1"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("1"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("1"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("1"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("1"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("1"), 1));
        Assert.assertEquals(new BigDecimal("1.515297821727026"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("1"), 15));
        Assert.assertEquals(new BigDecimal("1.5152978217270"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("1"), 13));
        Assert.assertEquals(new BigDecimal("1.515297821727"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("1"), 12));
        Assert.assertEquals(new BigDecimal("1.5152978217"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("1.515297822"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("1"), 9));
        Assert.assertEquals(new BigDecimal("1.515298"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("1"), 6));
        Assert.assertEquals(new BigDecimal("1.515"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("1"), 3));
        Assert.assertEquals(new BigDecimal("1.52"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("1"), 2));
        Assert.assertEquals(new BigDecimal("1.5"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("1"), 1));
        Assert.assertEquals(new BigDecimal("1.24904577239825"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 14));
        Assert.assertEquals(new BigDecimal("1.2490457724"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 10));
        Assert.assertEquals(new BigDecimal("1.24905"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 5));
        Assert.assertEquals(new BigDecimal("1.2"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("1"), 1));
        Assert.assertEquals(new BigDecimal("3.141592595789793"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("-1"), 15));
        Assert.assertEquals(new BigDecimal("3.1415925957898"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("-1"), 13));
        Assert.assertEquals(new BigDecimal("3.141592595790"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("-1"), 12));
        Assert.assertEquals(new BigDecimal("3.1415925958"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("3.141592596"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("-1"), 9));
        Assert.assertEquals(new BigDecimal("3.141593"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("-1"), 6));
        Assert.assertEquals(new BigDecimal("3.142"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("-1"), 3));
        Assert.assertEquals(new BigDecimal("3.14"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("-1"), 2));
        Assert.assertEquals(new BigDecimal("3.1"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("-1"), 1));
        Assert.assertEquals(new BigDecimal("1.626294831862767"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("-1"), 15));
        Assert.assertEquals(new BigDecimal("1.6262948318628"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("-1"), 13));
        Assert.assertEquals(new BigDecimal("1.626294831863"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("-1"), 12));
        Assert.assertEquals(new BigDecimal("1.6262948319"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("1.626294832"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("-1"), 9));
        Assert.assertEquals(new BigDecimal("1.626295"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("-1"), 6));
        Assert.assertEquals(new BigDecimal("1.626"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("-1"), 3));
        Assert.assertEquals(new BigDecimal("1.63"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("-1"), 2));
        Assert.assertEquals(new BigDecimal("1.6"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("-1"), 1));
        Assert.assertEquals(new BigDecimal("1.89254688119154"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 14));
        Assert.assertEquals(new BigDecimal("1.8925468812"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 10));
        Assert.assertEquals(new BigDecimal("1.89255"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 5));
        Assert.assertEquals(new BigDecimal("1.9"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("-1"), 1));
        Assert.assertEquals(new BigDecimal("1.570796326794897"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0"), 15));
        Assert.assertEquals(new BigDecimal("1.5707963267949"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0"), 13));
        Assert.assertEquals(new BigDecimal("1.570796326795"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0"), 12));
        Assert.assertEquals(new BigDecimal("1.5707963268"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0"), 10));
        Assert.assertEquals(new BigDecimal("1.570796327"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0"), 9));
        Assert.assertEquals(new BigDecimal("1.570796"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0"), 6));
        Assert.assertEquals(new BigDecimal("1.571"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0"), 3));
        Assert.assertEquals(new BigDecimal("1.57"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0"), 2));
        Assert.assertEquals(new BigDecimal("1.6"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0"), 1));
        Assert.assertEquals(new BigDecimal("1.570796326794897"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0"), 15));
        Assert.assertEquals(new BigDecimal("1.5707963267949"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0"), 13));
        Assert.assertEquals(new BigDecimal("1.570796326795"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0"), 12));
        Assert.assertEquals(new BigDecimal("1.5707963268"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0"), 10));
        Assert.assertEquals(new BigDecimal("1.570796327"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0"), 9));
        Assert.assertEquals(new BigDecimal("1.570796"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0"), 6));
        Assert.assertEquals(new BigDecimal("1.571"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0"), 3));
        Assert.assertEquals(new BigDecimal("1.57"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0"), 2));
        Assert.assertEquals(new BigDecimal("1.6"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0"), 1));
        Assert.assertEquals(new BigDecimal("1.57079632679490"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 14));
        Assert.assertEquals(new BigDecimal("1.5707963268"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 10));
        Assert.assertEquals(new BigDecimal("1.57080"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 5));
        Assert.assertEquals(new BigDecimal("1.6"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("0"), 1));
        Assert.assertEquals(new BigDecimal("0.000000137249291"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0.4211315"), 15));
        Assert.assertEquals(new BigDecimal("0.0000001372493"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0.4211315"), 13));
        Assert.assertEquals(new BigDecimal("0.000000137249"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0.4211315"), 12));
        Assert.assertEquals(new BigDecimal("0.0000001372"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0.4211315"), 10));
        Assert.assertEquals(new BigDecimal("0.000000137"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0.4211315"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0.4211315"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0.4211315"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0.4211315"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("0.4211315"), 1));
        Assert.assertEquals(new BigDecimal("1.547404399908630"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0.4211315"), 15));
        Assert.assertEquals(new BigDecimal("1.5474043999086"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0.4211315"), 13));
        Assert.assertEquals(new BigDecimal("1.547404399909"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0.4211315"), 12));
        Assert.assertEquals(new BigDecimal("1.5474043999"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0.4211315"), 10));
        Assert.assertEquals(new BigDecimal("1.547404400"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0.4211315"), 9));
        Assert.assertEquals(new BigDecimal("1.547404"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0.4211315"), 6));
        Assert.assertEquals(new BigDecimal("1.547"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0.4211315"), 3));
        Assert.assertEquals(new BigDecimal("1.55"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0.4211315"), 2));
        Assert.assertEquals(new BigDecimal("1.5"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("0.4211315"), 1));
        Assert.assertEquals(new BigDecimal("1.43133048817898"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("0.4211315"), 14));
        Assert.assertEquals(new BigDecimal("1.4313304882"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("0.4211315"), 10));
        Assert.assertEquals(new BigDecimal("1.43133"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("0.4211315"), 5));
        Assert.assertEquals(new BigDecimal("1.4"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("0.4211315"), 1));
        Assert.assertEquals(new BigDecimal("0.000000000000313"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("184451.115"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000000003"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("184451.115"), 13));
        Assert.assertEquals(new BigDecimal("0.000000000000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("184451.115"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("184451.115"), 10));
        Assert.assertEquals(new BigDecimal("0.000000000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("184451.115"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("184451.115"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("184451.115"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("184451.115"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.atan2(new BigDecimal(".0000000578"), new BigDecimal("184451.115"), 1));
        Assert.assertEquals(new BigDecimal("0.000097586832157"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("184451.115"), 15));
        Assert.assertEquals(new BigDecimal("0.0000975868322"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("184451.115"), 13));
        Assert.assertEquals(new BigDecimal("0.000097586832"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("184451.115"), 12));
        Assert.assertEquals(new BigDecimal("0.0000975868"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("184451.115"), 10));
        Assert.assertEquals(new BigDecimal("0.000097587"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("184451.115"), 9));
        Assert.assertEquals(new BigDecimal("0.000098"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("184451.115"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("184451.115"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("184451.115"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.atan2(new BigDecimal("18.0000000578"), new BigDecimal("184451.115"), 1));
        Assert.assertEquals(new BigDecimal("0.00001626447202"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("184451.115"), 14));
        Assert.assertEquals(new BigDecimal("0.0000162645"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("184451.115"), 10));
        Assert.assertEquals(new BigDecimal("0.00002"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("184451.115"), 5));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.atan2(new BigDecimal("2.99999999999999"), new BigDecimal("184451.115"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atan2 where x == 0 and y == 0", () ->
                BigMathUtility.atan2(new BigDecimal("0"), new BigDecimal("0")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan2(new BigDecimal("1"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan2(null, new BigDecimal("1")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan2(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan2(new BigDecimal("1"), null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan2(null, new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan2(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan2(null, new BigDecimal("1"), 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan2(new BigDecimal("1"), null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atan2(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.atan2(new BigDecimal("1"), new BigDecimal("15a5")));
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.atan2(new BigDecimal("15a5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.atan2(new BigDecimal("15a5"), new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of tanh.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#tanh(BigDecimal, int)
     * @see BigMathUtility#tanh(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#tanh(BigDecimal)
     */
    @Test
    public void testTanh() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tanh(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0.7615941559557648881194582826047935904127685972579365515968105001"), BigMathUtility.tanh(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.9999997749296758898100188329563683933540618881268771274302418414"), BigMathUtility.tanh(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("0.9999999999999999999999999999565895597739272117602686148094881066"), BigMathUtility.tanh(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("-0.7615941559557648881194582826047935904127685972579365515968105001"), BigMathUtility.tanh(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-0.9999997749296758898100188329563683933540618881268771274302418414"), BigMathUtility.tanh(new BigDecimal("-8")));
        Assert.assertEquals(new BigDecimal("-0.9999999999999999999999999999565895597739272117602686148094881066"), BigMathUtility.tanh(new BigDecimal("-33")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.0588808124895699025503369324461617802189982811484215777591335805"), BigMathUtility.tanh(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("0.7562695236404269767173080647354269900206377835634293153037495674"), BigMathUtility.tanh(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("0.0000000000456497999999999999999682900627410073360000264321890822"), BigMathUtility.tanh(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.5116682911811602674982627928146046840482678885886842312412331123"), BigMathUtility.tanh(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-0.0588808124895699025503369324461617802189982811484215777591335805"), BigMathUtility.tanh(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-0.7562695236404269767173080647354269900206377835634293153037495674"), BigMathUtility.tanh(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-0.0000000000456497999999999999999682900627410073360000264321890822"), BigMathUtility.tanh(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.5116682911811602674982627928146046840482678885886842312412331123"), BigMathUtility.tanh(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tanh(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.tanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.tanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.tanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.tanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.tanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.tanh(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.tanh(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.tanh(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.tanh(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.tanh(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.tanh(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.tanh(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.tanh(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.tanh(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.tanh(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.000000000000000"), BigMathUtility.tanh(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("1.0000000000000"), BigMathUtility.tanh(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("1.000000000000"), BigMathUtility.tanh(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("1.0000000000"), BigMathUtility.tanh(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("1.000000000"), BigMathUtility.tanh(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("1.000000"), BigMathUtility.tanh(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("1.000"), BigMathUtility.tanh(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("1.00"), BigMathUtility.tanh(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.tanh(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("0.99505475368673"), BigMathUtility.tanh(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("0.9950547537"), BigMathUtility.tanh(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("0.99505"), BigMathUtility.tanh(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.tanh(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.tanh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.tanh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.tanh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.tanh(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of atanh.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#atanh(BigDecimal, int)
     * @see BigMathUtility#atanh(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#atanh(BigDecimal)
     */
    @Test
    public void testAtanh() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atanh(new BigDecimal("0")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("0.0590174250113571171814773219623446367674678442211608571230798809"), BigMathUtility.atanh(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("2.5321276835576676358357835946901525560274181461720906884975295693"), BigMathUtility.atanh(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("0.0000000000456498000000000000000317099372589926640000396482836233"), BigMathUtility.atanh(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.6401286182977736873005705872839052912086664039668740110853195761"), BigMathUtility.atanh(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-0.0590174250113571171814773219623446367674678442211608571230798809"), BigMathUtility.atanh(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-2.5321276835576676358357835946901525560274181461720906884975295693"), BigMathUtility.atanh(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-0.0000000000456498000000000000000317099372589926640000396482836233"), BigMathUtility.atanh(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.6401286182977736873005705872839052912086664039668740110853195761"), BigMathUtility.atanh(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atanh(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000"), BigMathUtility.atanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000"), BigMathUtility.atanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.atanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.atanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.atanh(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.atanh(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.000000057800000"), BigMathUtility.atanh(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0000000578000"), BigMathUtility.atanh(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.000000057800"), BigMathUtility.atanh(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0000000578"), BigMathUtility.atanh(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.000000058"), BigMathUtility.atanh(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.000000"), BigMathUtility.atanh(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.000"), BigMathUtility.atanh(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.00"), BigMathUtility.atanh(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.0"), BigMathUtility.atanh(new BigDecimal(".0000000578"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 1", () ->
                BigMathUtility.atanh(new BigDecimal("1")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 59875132", () ->
                BigMathUtility.atanh(new BigDecimal("59875132")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 8976464100065468", () ->
                BigMathUtility.atanh(new BigDecimal("8976464100065468")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = -1", () ->
                BigMathUtility.atanh(new BigDecimal("-1")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = -59875132", () ->
                BigMathUtility.atanh(new BigDecimal("-59875132")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = -8976464100065468", () ->
                BigMathUtility.atanh(new BigDecimal("-8976464100065468")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 18.0000000578", () ->
                BigMathUtility.atanh(new BigDecimal("18.0000000578")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 2.99999999999999", () ->
                BigMathUtility.atanh(new BigDecimal("2.99999999999999")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atanh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atanh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.atanh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.atanh(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of cot.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#cot(BigDecimal, int)
     * @see BigMathUtility#cot(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#cot(BigDecimal)
     */
    @Test
    public void testCot() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0.6420926159343307030064199865942656202302781139181713791011622804"), BigMathUtility.cot(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.1514375256257845261533114547571066682820283507474647108913067143"), BigMathUtility.cot(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("4.0748252540080832566234931080849330819872131084728688315385049387"), BigMathUtility.cot(new BigDecimal("8976464100065468")));
        Assert.assertEquals(new BigDecimal("-0.6420926159343307030064199865942656202302781139181713791011622804"), BigMathUtility.cot(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-0.1514375256257845261533114547571066682820283507474647108913067143"), BigMathUtility.cot(new BigDecimal("-59875132")));
        Assert.assertEquals(new BigDecimal("-4.0748252540080832566234931080849330819872131084728688315385049387"), BigMathUtility.cot(new BigDecimal("-8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("16.9441619597615377001691330341472291728403918778012929533892275639"), BigMathUtility.cot(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("0.6599730107691131827722103236797793817656712745028477262096064406"), BigMathUtility.cot(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("21905901011.6145087163427959214130182388531801562441399735036253280594271123"), BigMathUtility.cot(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("1.5774891765948505056094855730256698970914637387164325415539242618"), BigMathUtility.cot(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-16.9441619597615377001691330341472291728403918778012929533892275639"), BigMathUtility.cot(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-0.6599730107691131827722103236797793817656712745028477262096064406"), BigMathUtility.cot(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-21905901011.6145087163427959214130182388531801562441399735036253280594271123"), BigMathUtility.cot(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-1.5774891765948505056094855730256698970914637387164325415539242618"), BigMathUtility.cot(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0.6420926159343307030064199865942656202302781139181713791011622804"), BigMathUtility.cot(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0.6420926159343307030064199865942656202302781139181713791011622804"), BigMathUtility.cot(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.cot(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.64209262"), BigMathUtility.cot(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.6420926159343307"), BigMathUtility.cot(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.6420926159343307030064199865942656202302781139181713791011622804"), BigMathUtility.cot(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.64209261593433070300641998659426562023027811391817137910116228042627685683916467219848291976019680465814306596047141573918356963493705933122378784310056202796590177952583993144431226921022120997092394574813060354777658685526661570956826754318872654659780710610492629489626709295081160952483427016354137699541561458952860701107858227259376088670827067970590687137491185081969260425814554198558997437568690607879275252280812126851999661677146531833095334863246223721746553979982097961711441872912451219319661779094"), BigMathUtility.cot(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.6420926159343307030064199865942656202302781139181713791011622804262768568391646721984829197601968046581430659604714157391835696349370593312237878431005620279659017795258399314443122692102212099709239457481306035477765868552666157095682675431887265465978071061049262948962670929508116095248342701635413769954156145895286070110785822725937608867082706797059068713749118508196926042581455419855899743756869060787927525228081212685199966167714653183309533486324622372174655397998209796171144187291245121931966177909400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.cot(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.6420926159343307030064199865942656202302781139181713791011622804"), BigMathUtility.cot(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("17301038.062283717757555"), BigMathUtility.cot(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("17301038.0622837177576"), BigMathUtility.cot(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("17301038.062283717758"), BigMathUtility.cot(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("17301038.0622837178"), BigMathUtility.cot(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("17301038.062283718"), BigMathUtility.cot(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("17301038.062284"), BigMathUtility.cot(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("17301038.062"), BigMathUtility.cot(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("17301038.06"), BigMathUtility.cot(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("17301038.1"), BigMathUtility.cot(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("-0.879264978264266"), BigMathUtility.cot(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("-0.8792649782643"), BigMathUtility.cot(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("-0.879264978264"), BigMathUtility.cot(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("-0.8792649783"), BigMathUtility.cot(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("-0.879264978"), BigMathUtility.cot(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("-0.879265"), BigMathUtility.cot(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("-0.879"), BigMathUtility.cot(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("-0.88"), BigMathUtility.cot(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("-0.9"), BigMathUtility.cot(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("-7.01525255143403"), BigMathUtility.cot(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("-7.0152525514"), BigMathUtility.cot(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("-7.01525"), BigMathUtility.cot(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("-7.0"), BigMathUtility.cot(new BigDecimal("2.99999999999999"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for cot where input == 0", () ->
                BigMathUtility.cot(new BigDecimal("0")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.cot(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.cot(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.cot(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.cot(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of acot.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#acot(BigDecimal, int)
     * @see BigMathUtility#acot(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#acot(BigDecimal)
     */
    @Test
    public void testAcot() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acot(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("0.7853981633974483096156608458198757210492923498437764552437361481"), BigMathUtility.acot(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0.0000000167014245580285302255501651474800847738882726406229587331"), BigMathUtility.acot(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("0.0000000000000001114024396301776225097687998785096594298232876751"), BigMathUtility.acot(new BigDecimal("8976464100065468")));
        Assert.assertEquals(new BigDecimal("2.3561944901923449288469825374596271631478770495313293657312084442"), BigMathUtility.acot(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("3.1415926368883686804341131577293377367170846254868331803519858593"), BigMathUtility.acot(new BigDecimal("-59875132")));
        Assert.assertEquals(new BigDecimal("3.1415926535897931270602037531018803744283695208654463911516569172"), BigMathUtility.acot(new BigDecimal("-8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("1.511915467068459840405083955517703022322411121191880974921366336"), BigMathUtility.acot(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("0.7917166935088708281774445580202629287722260063515224015313095139"), BigMathUtility.acot(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("1.5707963267492468192313216916397831520358436923515528708391886729"), BigMathUtility.acot(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("1.0565195479757279058703755685347904749678717236568689033438643675"), BigMathUtility.acot(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("1.6296771865213333980575594277617998618747582781832248460535782563"), BigMathUtility.acot(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("2.3498759600809224102851988252592399554249433930235834194436350784"), BigMathUtility.acot(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("1.5707963268405464192313216916397197321613257070235529501357559194"), BigMathUtility.acot(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("2.0850731056140653325922678147447124092292976757182369176310802248"), BigMathUtility.acot(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acot(new BigDecimal("0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acot(new BigDecimal("0.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("2"), BigMathUtility.acot(new BigDecimal("0"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.57079633"), BigMathUtility.acot(new BigDecimal("0"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966"), BigMathUtility.acot(new BigDecimal("0"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acot(new BigDecimal("0"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.57079632679489661923132169163975144209858469968755291048747229615390820314310449931401741267105853399107404325664115332354692230477529111586267970406424055872514205135096926055277982231147447746519098221440548783296672306423782411689339158263560095457282428346173017430522716332410669680363012457063686229350330315779408744076046048141462704585768218394629518000566526527441023326069207347597075580471652863518287979597654609305869096630589655255927403723118998137478367594287636244561396909150597456491683668120"), BigMathUtility.acot(new BigDecimal("0"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722961539082031431044993140174126710585339910740432566411533235469223047752911158626797040642405587251420513509692605527798223114744774651909822144054878329667230642378241168933915826356009545728242834617301743052271633241066968036301245706368622935033031577940874407604604814146270458576821839462951800056652652744102332606920734759707558047165286351828797959765460930586909663058965525592740372311899813747836759428763624456139690915059745649168366812000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.acot(new BigDecimal("0"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722962"), BigMathUtility.acot(new BigDecimal("0"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("1.570796268994897"), BigMathUtility.acot(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("1.5707962689949"), BigMathUtility.acot(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("1.570796268995"), BigMathUtility.acot(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("1.5707962690"), BigMathUtility.acot(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("1.570796269"), BigMathUtility.acot(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("1.570796"), BigMathUtility.acot(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("1.571"), BigMathUtility.acot(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("1.57"), BigMathUtility.acot(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("1.6"), BigMathUtility.acot(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("0.055498505067871"), BigMathUtility.acot(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0554985050679"), BigMathUtility.acot(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.055498505068"), BigMathUtility.acot(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0554985051"), BigMathUtility.acot(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.055498505"), BigMathUtility.acot(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.055499"), BigMathUtility.acot(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.055"), BigMathUtility.acot(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.06"), BigMathUtility.acot(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.1"), BigMathUtility.acot(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("0.32175055439664"), BigMathUtility.acot(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("0.3217505544"), BigMathUtility.acot(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("0.32175"), BigMathUtility.acot(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("0.3"), BigMathUtility.acot(new BigDecimal("2.99999999999999"), 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acot(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acot(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acot(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.acot(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of coth.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#coth(BigDecimal, int)
     * @see BigMathUtility#coth(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#coth(BigDecimal)
     */
    @Test
    public void testCoth() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1.3130352854993313036361612469308478329120139412404526555431529676"), BigMathUtility.coth(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("1.000000225070374766852177544388048446482025237819737168908464686"), BigMathUtility.coth(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("1.0000000000000000000000000000434104402260727882397313851923963597"), BigMathUtility.coth(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("-1.3130352854993313036361612469308478329120139412404526555431529676"), BigMathUtility.coth(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("-1.000000225070374766852177544388048446482025237819737168908464686"), BigMathUtility.coth(new BigDecimal("-8")));
        Assert.assertEquals(new BigDecimal("-1.0000000000000000000000000000434104402260727882397313851923963597"), BigMathUtility.coth(new BigDecimal("-33")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("16.9834612961079495353598753450403584506208498848148767421908847728"), BigMathUtility.coth(new BigDecimal(".058949")));
        Assert.assertEquals(new BigDecimal("1.3222799131007375962694582792897014500316554312690697819110305133"), BigMathUtility.coth(new BigDecimal(".9874421200001")));
        Assert.assertEquals(new BigDecimal("21905901011.614508716373229121413018238853180156244139973503625328898544226"), BigMathUtility.coth(new BigDecimal(".0000000000456498")));
        Assert.assertEquals(new BigDecimal("1.954391189048574380771452529002789587020825985489042277667700316"), BigMathUtility.coth(new BigDecimal(".5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-16.9834612961079495353598753450403584506208498848148767421908847728"), BigMathUtility.coth(new BigDecimal("-.058949")));
        Assert.assertEquals(new BigDecimal("-1.3222799131007375962694582792897014500316554312690697819110305133"), BigMathUtility.coth(new BigDecimal("-.9874421200001")));
        Assert.assertEquals(new BigDecimal("-21905901011.614508716373229121413018238853180156244139973503625328898544226"), BigMathUtility.coth(new BigDecimal("-.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-1.954391189048574380771452529002789587020825985489042277667700316"), BigMathUtility.coth(new BigDecimal("-.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("1.3130352854993313036361612469308478329120139412404526555431529676"), BigMathUtility.coth(new BigDecimal("1"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1.3130352854993313036361612469308478329120139412404526555431529676"), BigMathUtility.coth(new BigDecimal("1.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.coth(new BigDecimal("1"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("1.31303529"), BigMathUtility.coth(new BigDecimal("1"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("1.3130352854993313"), BigMathUtility.coth(new BigDecimal("1"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("1.3130352854993313036361612469308478329120139412404526555431529676"), BigMathUtility.coth(new BigDecimal("1"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.31303528549933130363616124693084783291201394124045265554315296756708427046187438267467924148085630294679470507384482041977039618612671089896960175354961874327866039526774154301931642149012222071169026987217362794495510788635344264314594215127054915455302849022912492878501496724636129939185725560266407256980263388317166110796962326028749323531083074352389608925392978085234472228962687929005848959618480788283481459978814671678868649257103226048738076630787529421237089242315501117484712985991948833155233888840"), BigMathUtility.coth(new BigDecimal("1"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("1.3130352854993313036361612469308478329120139412404526555431529675670842704618743826746792414808563029467947050738448204197703961861267108989696017535496187432786603952677415430193164214901222207116902698721736279449551078863534426431459421512705491545530284902291249287850149672463612993918572556026640725698026338831716611079696232602874932353108307435238960892539297808523447222896268792900584895961848078828348145997881467167886864925710322604873807663078752942123708924231550111748471298599194883315523388884000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.coth(new BigDecimal("1"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("1.3130352854993313036361612469308478329120139412404526555431529676"), BigMathUtility.coth(new BigDecimal("1"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("17301038.062283756290888"), BigMathUtility.coth(new BigDecimal(".0000000578"), 15));
        Assert.assertEquals(new BigDecimal("17301038.0622837562909"), BigMathUtility.coth(new BigDecimal(".0000000578"), 13));
        Assert.assertEquals(new BigDecimal("17301038.062283756291"), BigMathUtility.coth(new BigDecimal(".0000000578"), 12));
        Assert.assertEquals(new BigDecimal("17301038.0622837563"), BigMathUtility.coth(new BigDecimal(".0000000578"), 10));
        Assert.assertEquals(new BigDecimal("17301038.062283756"), BigMathUtility.coth(new BigDecimal(".0000000578"), 9));
        Assert.assertEquals(new BigDecimal("17301038.062284"), BigMathUtility.coth(new BigDecimal(".0000000578"), 6));
        Assert.assertEquals(new BigDecimal("17301038.062"), BigMathUtility.coth(new BigDecimal(".0000000578"), 3));
        Assert.assertEquals(new BigDecimal("17301038.06"), BigMathUtility.coth(new BigDecimal(".0000000578"), 2));
        Assert.assertEquals(new BigDecimal("17301038.1"), BigMathUtility.coth(new BigDecimal(".0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.000000000000000"), BigMathUtility.coth(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("1.0000000000000"), BigMathUtility.coth(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("1.000000000000"), BigMathUtility.coth(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("1.0000000000"), BigMathUtility.coth(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("1.000000000"), BigMathUtility.coth(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("1.000000"), BigMathUtility.coth(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("1.000"), BigMathUtility.coth(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("1.00"), BigMathUtility.coth(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.coth(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("1.00496982331369"), BigMathUtility.coth(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("1.0049698233"), BigMathUtility.coth(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("1.00497"), BigMathUtility.coth(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("1.0"), BigMathUtility.coth(new BigDecimal("2.99999999999999"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for coth where input == 0", () ->
                BigMathUtility.coth(new BigDecimal("0")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.coth(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.coth(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.coth(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.coth(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of acoth.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#acoth(BigDecimal, int)
     * @see BigMathUtility#acoth(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#acoth(BigDecimal)
     */
    @Test
    public void testAcoth() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0.0000000167014245580285333313201562407054330880167416058415770091"), BigMathUtility.acoth(new BigDecimal("59875132")));
        Assert.assertEquals(new BigDecimal("0.0000000000000001114024396301776225097687998785105811367387062497"), BigMathUtility.acoth(new BigDecimal("8976464100065468")));
        Assert.assertEquals(new BigDecimal("-0.0000000167014245580285333313201562407054330880167416058415770091"), BigMathUtility.acoth(new BigDecimal("-59875132")));
        Assert.assertEquals(new BigDecimal("-0.0000000000000001114024396301776225097687998785105811367387062497"), BigMathUtility.acoth(new BigDecimal("-8976464100065468")));
        
        //decimal
        Assert.assertEquals(new BigDecimal("1.776639137052226643312329244203206253702357261617447434453137773"), BigMathUtility.acoth(new BigDecimal("1.058949")));
        Assert.assertEquals(new BigDecimal("0.5535274697970906475737781169691852759772105515454078636538961603"), BigMathUtility.acoth(new BigDecimal("1.9874421200001")));
        Assert.assertEquals(new BigDecimal("12.2515845353204729622407932653047333390654139382820946125811959398"), BigMathUtility.acoth(new BigDecimal("1.0000000000456498")));
        Assert.assertEquals(new BigDecimal("0.7564529000318518745129303373120338839458524348113284343101286263"), BigMathUtility.acoth(new BigDecimal("1.5649871212025644980798794213200031654")));
        Assert.assertEquals(new BigDecimal("-1.776639137052226643312329244203206253702357261617447434453137773"), BigMathUtility.acoth(new BigDecimal("-1.058949")));
        Assert.assertEquals(new BigDecimal("-0.5535274697970906475737781169691852759772105515454078636538961603"), BigMathUtility.acoth(new BigDecimal("-1.9874421200001")));
        Assert.assertEquals(new BigDecimal("-12.2515845353204729622407932653047333390654139382820946125811959398"), BigMathUtility.acoth(new BigDecimal("-1.0000000000456498")));
        Assert.assertEquals(new BigDecimal("-0.7564529000318518745129303373120338839458524348113284343101286263"), BigMathUtility.acoth(new BigDecimal("-1.5649871212025644980798794213200031654")));
        
        //precision
        Assert.assertEquals(new BigDecimal("0.5493061443340548456976226184612628523237452789113747258673471668"), BigMathUtility.acoth(new BigDecimal("2"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0.5493061443340548456976226184612628523237452789113747258673471668"), BigMathUtility.acoth(new BigDecimal("2.0"), BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.acoth(new BigDecimal("2"), BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0.54930614"), BigMathUtility.acoth(new BigDecimal("2"), BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0.5493061443340548"), BigMathUtility.acoth(new BigDecimal("2"), BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0.5493061443340548456976226184612628523237452789113747258673471668"), BigMathUtility.acoth(new BigDecimal("2"), BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.54930614433405484569762261846126285232374527891137472586734716681874714660930448343680787740686604439398501453297893287118400211296525991052640093538363870530158138459169068358968684942218047995187128515839795576057279595887533567352747008338779011110158512647344878034505326075282143406901815868664928889118349582739606590907451001505191181506112432637409911299554872624544822902673350442298254287422205950942854382374743353980654291470580108306059200070491275719597438444683992471511278657676648426726476257296"), BigMathUtility.acoth(new BigDecimal("2"), BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0.5493061443340548456976226184612628523237452789113747258673471668187471466093044834368078774068660443939850145329789328711840021129652599105264009353836387053015813845916906835896868494221804799518712851583979557605727959588753356735274700833877901111015851264734487803450532607528214340690181586866492888911834958273960659090745100150519118150611243263740991129955487262454482290267335044229825428742220595094285438237474335398065429147058010830605920007049127571959743844468399247151127865767664842672647625729600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), BigMathUtility.acoth(new BigDecimal("2"), BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0.5493061443340548456976226184612628523237452789113747258673471668"), BigMathUtility.acoth(new BigDecimal("2"), null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0.055612817376165"), BigMathUtility.acoth(new BigDecimal("18.0000000578"), 15));
        Assert.assertEquals(new BigDecimal("0.0556128173762"), BigMathUtility.acoth(new BigDecimal("18.0000000578"), 13));
        Assert.assertEquals(new BigDecimal("0.055612817376"), BigMathUtility.acoth(new BigDecimal("18.0000000578"), 12));
        Assert.assertEquals(new BigDecimal("0.0556128174"), BigMathUtility.acoth(new BigDecimal("18.0000000578"), 10));
        Assert.assertEquals(new BigDecimal("0.055612817"), BigMathUtility.acoth(new BigDecimal("18.0000000578"), 9));
        Assert.assertEquals(new BigDecimal("0.055613"), BigMathUtility.acoth(new BigDecimal("18.0000000578"), 6));
        Assert.assertEquals(new BigDecimal("0.056"), BigMathUtility.acoth(new BigDecimal("18.0000000578"), 3));
        Assert.assertEquals(new BigDecimal("0.06"), BigMathUtility.acoth(new BigDecimal("18.0000000578"), 2));
        Assert.assertEquals(new BigDecimal("0.1"), BigMathUtility.acoth(new BigDecimal("18.0000000578"), 1));
        Assert.assertEquals(new BigDecimal("0.34657359027997"), BigMathUtility.acoth(new BigDecimal("2.99999999999999"), 14));
        Assert.assertEquals(new BigDecimal("0.3465735903"), BigMathUtility.acoth(new BigDecimal("2.99999999999999"), 10));
        Assert.assertEquals(new BigDecimal("0.34657"), BigMathUtility.acoth(new BigDecimal("2.99999999999999"), 5));
        Assert.assertEquals(new BigDecimal("0.3"), BigMathUtility.acoth(new BigDecimal("2.99999999999999"), 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0", () ->
                BigMathUtility.acoth(new BigDecimal("0")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 1", () ->
                BigMathUtility.acoth(new BigDecimal("1")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -1", () ->
                BigMathUtility.acoth(new BigDecimal("-1")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.058949", () ->
                BigMathUtility.acoth(new BigDecimal(".058949")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.9874421200001", () ->
                BigMathUtility.acoth(new BigDecimal(".9874421200001")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.0000000000456498", () ->
                BigMathUtility.acoth(new BigDecimal(".0000000000456498")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.5649871212025644980798794213200031654", () ->
                BigMathUtility.acoth(new BigDecimal(".5649871212025644980798794213200031654")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -0.058949", () ->
                BigMathUtility.acoth(new BigDecimal("-.058949")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -0.9874421200001", () ->
                BigMathUtility.acoth(new BigDecimal("-.9874421200001")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -0.0000000000456498", () ->
                BigMathUtility.acoth(new BigDecimal("-.0000000000456498")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -0.5649871212025644980798794213200031654", () ->
                BigMathUtility.acoth(new BigDecimal("-.5649871212025644980798794213200031654")));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.0000000578", () ->
                BigMathUtility.acoth(new BigDecimal(".0000000578")));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acoth(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acoth(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.acoth(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.acoth(new BigDecimal("15a5")));
    }
    
    /**
     * JUnit test of pi.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#pi(int)
     * @see BigMathUtility#pi(BigMathUtility.PrecisionMode)
     * @see BigMathUtility#pi()
     */
    @Test
    public void testPi() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592"), BigMathUtility.pi());
        
        //precision
        Assert.assertEquals(new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592"), BigMathUtility.pi(BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("3"), BigMathUtility.pi(BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("3.1415927"), BigMathUtility.pi(BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("3.141592653589793"), BigMathUtility.pi(BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592"), BigMathUtility.pi(BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624"), BigMathUtility.pi(BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381964428810975665933446128475648233786783165271201909145648566923460348610454326648213393607260249141273724587006606315588174881520920962829254091715364367892590360011330530548820466521384146951941511609433057270365759591953092186117381932611793105118548074462379962749567351885752724891227938183011949129833673362440656643086021394946395224737190702179860943702770539217176293176752384674818467669405132000568127145263560827785771342757789609173637178721468440901224953430146549585371050792279689258923542019956112129021960864034418159813629774771309960518707211349999998372978049951059731732816096318595024459455346908302642522308253344685035261931188171010003137838752886587533208381420617177669147303598253490428755468731159562863882353787593751957781857780532171226806613001927876611195909216420198938095257201065485863279"), BigMathUtility.pi(BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592"), BigMathUtility.pi(null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("3.14159265358979"), BigMathUtility.pi(15));
        Assert.assertEquals(new BigDecimal("3.141592653590"), BigMathUtility.pi(13));
        Assert.assertEquals(new BigDecimal("3.14159265359"), BigMathUtility.pi(12));
        Assert.assertEquals(new BigDecimal("3.141592654"), BigMathUtility.pi(10));
        Assert.assertEquals(new BigDecimal("3.14159265"), BigMathUtility.pi(9));
        Assert.assertEquals(new BigDecimal("3.14159"), BigMathUtility.pi(6));
        Assert.assertEquals(new BigDecimal("3.14"), BigMathUtility.pi(3));
        Assert.assertEquals(new BigDecimal("3.1"), BigMathUtility.pi(2));
        Assert.assertEquals(new BigDecimal("3"), BigMathUtility.pi(1));
    }
    
    /**
     * JUnit test of e.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#e(int)
     * @see BigMathUtility#e(BigMathUtility.PrecisionMode)
     * @see BigMathUtility#e()
     */
    @Test
    public void testE() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967628"), BigMathUtility.e());
        
        //precision
        Assert.assertEquals(new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967628"), BigMathUtility.e(BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("3"), BigMathUtility.e(BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("2.7182818"), BigMathUtility.e(BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("2.718281828459045"), BigMathUtility.e(BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967628"), BigMathUtility.e(BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695517027618386062613313845830007520449338265602976067371132007093287091274437470472306969772093101416928368190255151086574637721112523897844250569536967707854499699679468644549059879316368892300987931277361782154"), BigMathUtility.e(BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059921817413596629043572900334295260595630738132328627943490763233829880753195251019011573834187930702154089149934884167509244761460668082264800168477411853742345442437107539077744992069551702761838606261331384583000752044933826560297606737113200709328709127443747047230696977209310141692836819025515108657463772111252389784425056953696770785449969967946864454905987931636889230098793127736178215424999229576351482208269895193668033182528869398496465105820939239829488793320362509443117301238197068416140397019837679320683282376464804295311802328782509819455815301756717361332069811250996181881593041690351598888519345807273866738589422879228499892086805825749279610484198444363463244968487560233624827041978623209002160990235304369941849146314093431738143640546253152096183690888707016768396424378140592714563549061303107208510383750510115747704171898610687396965521267154688957035035402123407849819334321068"), BigMathUtility.e(BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967628"), BigMathUtility.e(null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("2.71828182845905"), BigMathUtility.e(15));
        Assert.assertEquals(new BigDecimal("2.718281828459"), BigMathUtility.e(13));
        Assert.assertEquals(new BigDecimal("2.71828182846"), BigMathUtility.e(12));
        Assert.assertEquals(new BigDecimal("2.718281828"), BigMathUtility.e(10));
        Assert.assertEquals(new BigDecimal("2.71828183"), BigMathUtility.e(9));
        Assert.assertEquals(new BigDecimal("2.71828"), BigMathUtility.e(6));
        Assert.assertEquals(new BigDecimal("2.72"), BigMathUtility.e(3));
        Assert.assertEquals(new BigDecimal("2.7"), BigMathUtility.e(2));
        Assert.assertEquals(new BigDecimal("3"), BigMathUtility.e(1));
    }
    
    /**
     * JUnit test of bernoulli.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#bernoulli(int, int)
     * @see BigMathUtility#bernoulli(int, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#bernoulli(int)
     */
    @Test
    public void testBernoulli() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.bernoulli(0));
        Assert.assertEquals(new BigDecimal("-0.5"), BigMathUtility.bernoulli(1));
        Assert.assertEquals(new BigDecimal("-0.03333333333333333333333333333333333333333333333333333333333333333"), BigMathUtility.bernoulli(8));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(33));
        Assert.assertEquals(new BigDecimal("36528776484818123335110430842.97117794486215538847117794486215539"), BigMathUtility.bernoulli(54));
        
        //precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(5, null));
        
        //custom precision
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(15, 15));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(13, 13));
        Assert.assertEquals(new BigDecimal("-0.253113553114"), BigMathUtility.bernoulli(12, 12));
        Assert.assertEquals(new BigDecimal("0.07575757576"), BigMathUtility.bernoulli(10, 10));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(9, 9));
        Assert.assertEquals(new BigDecimal("0.0238095"), BigMathUtility.bernoulli(6, 6));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.bernoulli(3, 3));
        Assert.assertEquals(new BigDecimal("0.17"), BigMathUtility.bernoulli(2, 2));
        Assert.assertEquals(new BigDecimal("-0.5"), BigMathUtility.bernoulli(1, 1));
    }
    
    /**
     * JUnit test of integralPart.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#integralPart(BigDecimal)
     */
    @Test
    public void testIntegralPart() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.integralPart(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("8"), BigMathUtility.integralPart(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("33"), BigMathUtility.integralPart(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("54"), BigMathUtility.integralPart(new BigDecimal("54")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.integralPart(new BigDecimal(".4154211")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.integralPart(new BigDecimal(".94108")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.integralPart(new BigDecimal(".00033")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.integralPart(new BigDecimal(".421654")));
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.integralPart(new BigDecimal("1.4154211")));
        Assert.assertEquals(new BigDecimal("8"), BigMathUtility.integralPart(new BigDecimal("8.94108")));
        Assert.assertEquals(new BigDecimal("33"), BigMathUtility.integralPart(new BigDecimal("33.00033")));
        Assert.assertEquals(new BigDecimal("54"), BigMathUtility.integralPart(new BigDecimal("54.421654")));
        Assert.assertEquals(new BigDecimal("840000000000000000000000000000000000000000"), BigMathUtility.integralPart(new BigDecimal("840000000000000000000000000000000000000000")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.integralPart(new BigDecimal("0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980"), BigMathUtility.integralPart(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.integralPart(null));
    }
    
    /**
     * JUnit test of fractionalPart.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#fractionalPart(BigDecimal)
     */
    @Test
    public void testFractionalPart() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.fractionalPart(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.fractionalPart(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.fractionalPart(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.fractionalPart(new BigDecimal("54")));
        Assert.assertEquals(new BigDecimal("0.4154211"), BigMathUtility.fractionalPart(new BigDecimal(".4154211")));
        Assert.assertEquals(new BigDecimal("0.94108"), BigMathUtility.fractionalPart(new BigDecimal(".94108")));
        Assert.assertEquals(new BigDecimal("0.00033"), BigMathUtility.fractionalPart(new BigDecimal(".00033")));
        Assert.assertEquals(new BigDecimal("0.421654"), BigMathUtility.fractionalPart(new BigDecimal(".421654")));
        Assert.assertEquals(new BigDecimal("0.4154211"), BigMathUtility.fractionalPart(new BigDecimal("1.4154211")));
        Assert.assertEquals(new BigDecimal("0.94108"), BigMathUtility.fractionalPart(new BigDecimal("8.94108")));
        Assert.assertEquals(new BigDecimal("0.00033"), BigMathUtility.fractionalPart(new BigDecimal("33.00033")));
        Assert.assertEquals(new BigDecimal("0.421654"), BigMathUtility.fractionalPart(new BigDecimal("54.421654")));
        Assert.assertEquals(new BigDecimal("0"), BigMathUtility.fractionalPart(new BigDecimal("840000000000000000000000000000000000000000")));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000084"), BigMathUtility.fractionalPart(new BigDecimal("0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(new BigDecimal("0.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"), BigMathUtility.fractionalPart(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.fractionalPart(null));
    }
    
    /**
     * JUnit test of significantDigits.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#significantDigits(BigDecimal)
     */
    @Test
    public void testSignificantDigits() throws Exception {
        //standard
        Assert.assertEquals(1, BigMathUtility.significantDigits(new BigDecimal("1")));
        Assert.assertEquals(1, BigMathUtility.significantDigits(new BigDecimal("8")));
        Assert.assertEquals(2, BigMathUtility.significantDigits(new BigDecimal("33")));
        Assert.assertEquals(2, BigMathUtility.significantDigits(new BigDecimal("54")));
        Assert.assertEquals(7, BigMathUtility.significantDigits(new BigDecimal(".4154211")));
        Assert.assertEquals(5, BigMathUtility.significantDigits(new BigDecimal(".94108")));
        Assert.assertEquals(2, BigMathUtility.significantDigits(new BigDecimal(".00033")));
        Assert.assertEquals(6, BigMathUtility.significantDigits(new BigDecimal(".421654")));
        Assert.assertEquals(8, BigMathUtility.significantDigits(new BigDecimal("1.4154211")));
        Assert.assertEquals(6, BigMathUtility.significantDigits(new BigDecimal("8.94108")));
        Assert.assertEquals(7, BigMathUtility.significantDigits(new BigDecimal("33.00033")));
        Assert.assertEquals(8, BigMathUtility.significantDigits(new BigDecimal("54.421654")));
        Assert.assertEquals(42, BigMathUtility.significantDigits(new BigDecimal("840000000000000000000000000000000000000000")));
        Assert.assertEquals(2, BigMathUtility.significantDigits(new BigDecimal("0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(147, BigMathUtility.significantDigits(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.significantDigits(null));
    }
    
    /**
     * JUnit test of mantissa.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#mantissa(BigDecimal)
     */
    @Test
    public void testMantissa() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.mantissa(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("8"), BigMathUtility.mantissa(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("3.3"), BigMathUtility.mantissa(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("5.4"), BigMathUtility.mantissa(new BigDecimal("54")));
        Assert.assertEquals(new BigDecimal("4.154211"), BigMathUtility.mantissa(new BigDecimal(".4154211")));
        Assert.assertEquals(new BigDecimal("9.4108"), BigMathUtility.mantissa(new BigDecimal(".94108")));
        Assert.assertEquals(new BigDecimal("3.3"), BigMathUtility.mantissa(new BigDecimal(".00033")));
        Assert.assertEquals(new BigDecimal("4.21654"), BigMathUtility.mantissa(new BigDecimal(".421654")));
        Assert.assertEquals(new BigDecimal("1.4154211"), BigMathUtility.mantissa(new BigDecimal("1.4154211")));
        Assert.assertEquals(new BigDecimal("8.94108"), BigMathUtility.mantissa(new BigDecimal("8.94108")));
        Assert.assertEquals(new BigDecimal("3.300033"), BigMathUtility.mantissa(new BigDecimal("33.00033")));
        Assert.assertEquals(new BigDecimal("5.4421654"), BigMathUtility.mantissa(new BigDecimal("54.421654")));
        Assert.assertEquals(new BigDecimal("8.40000000000000000000000000000000000000000"), BigMathUtility.mantissa(new BigDecimal("840000000000000000000000000000000000000000")));
        Assert.assertEquals(new BigDecimal("8.4"), BigMathUtility.mantissa(new BigDecimal("0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(new BigDecimal("8.74560984810495456040984654132198077871049809840598711111771980000158979806579154563234089338737573040498108453021219870845654655086888484521260098"), BigMathUtility.mantissa(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.mantissa(null));
    }
    
    /**
     * JUnit test of exponent.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#exponent(BigDecimal)
     */
    @Test
    public void testExponent() throws Exception {
        //standard
        Assert.assertEquals(0, BigMathUtility.exponent(new BigDecimal("1")));
        Assert.assertEquals(0, BigMathUtility.exponent(new BigDecimal("8")));
        Assert.assertEquals(1, BigMathUtility.exponent(new BigDecimal("33")));
        Assert.assertEquals(1, BigMathUtility.exponent(new BigDecimal("54")));
        Assert.assertEquals(-1, BigMathUtility.exponent(new BigDecimal(".4154211")));
        Assert.assertEquals(-1, BigMathUtility.exponent(new BigDecimal(".94108")));
        Assert.assertEquals(-4, BigMathUtility.exponent(new BigDecimal(".00033")));
        Assert.assertEquals(-1, BigMathUtility.exponent(new BigDecimal(".421654")));
        Assert.assertEquals(0, BigMathUtility.exponent(new BigDecimal("1.4154211")));
        Assert.assertEquals(0, BigMathUtility.exponent(new BigDecimal("8.94108")));
        Assert.assertEquals(1, BigMathUtility.exponent(new BigDecimal("33.00033")));
        Assert.assertEquals(1, BigMathUtility.exponent(new BigDecimal("54.421654")));
        Assert.assertEquals(41, BigMathUtility.exponent(new BigDecimal("840000000000000000000000000000000000000000")));
        Assert.assertEquals(-39, BigMathUtility.exponent(new BigDecimal("0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(62, BigMathUtility.exponent(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.exponent(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#round(BigDecimal, int)
     * @see BigMathUtility#round(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#round(BigDecimal)
     */
    @Test
    public void testRound() throws Exception {
        BigDecimal n;
        
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.round(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("8"), BigMathUtility.round(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("33"), BigMathUtility.round(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("54"), BigMathUtility.round(new BigDecimal("54")));
        Assert.assertEquals(new BigDecimal("0.4154211"), BigMathUtility.round(new BigDecimal(".4154211")));
        Assert.assertEquals(new BigDecimal("0.94108"), BigMathUtility.round(new BigDecimal(".94108")));
        Assert.assertEquals(new BigDecimal("0.00033"), BigMathUtility.round(new BigDecimal(".00033")));
        Assert.assertEquals(new BigDecimal("0.421654"), BigMathUtility.round(new BigDecimal(".421654")));
        Assert.assertEquals(new BigDecimal("1.4154211"), BigMathUtility.round(new BigDecimal("1.4154211")));
        Assert.assertEquals(new BigDecimal("8.94108"), BigMathUtility.round(new BigDecimal("8.94108")));
        Assert.assertEquals(new BigDecimal("33.00033"), BigMathUtility.round(new BigDecimal("33.00033")));
        Assert.assertEquals(new BigDecimal("54.421654"), BigMathUtility.round(new BigDecimal("54.421654")));
        Assert.assertEquals(new BigDecimal("8.4E+41"), BigMathUtility.round(new BigDecimal("840000000000000000000000000000000000000000")));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000084"), BigMathUtility.round(new BigDecimal("0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.0001589798065791545632340893387375730404981084530212198708456547"), BigMathUtility.round(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //precision
        n = new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(
                new BigDecimal("154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.round(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154"),
                BigMathUtility.round(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.48910622"),
                BigMathUtility.round(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685"),
                BigMathUtility.round(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210"),
                BigMathUtility.round(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048000"),
                BigMathUtility.round(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),
                BigMathUtility.round(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.round(n, null));
        
        //custom precision
        n = new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(new BigDecimal("154.489106216501269"), BigMathUtility.round(n, 15));
        Assert.assertEquals(new BigDecimal("154.4891062165013"), BigMathUtility.round(n, 13));
        Assert.assertEquals(new BigDecimal("154.489106216501"), BigMathUtility.round(n, 12));
        Assert.assertEquals(new BigDecimal("154.4891062165"), BigMathUtility.round(n, 10));
        Assert.assertEquals(new BigDecimal("154.489106217"), BigMathUtility.round(n, 9));
        Assert.assertEquals(new BigDecimal("154.489106"), BigMathUtility.round(n, 6));
        Assert.assertEquals(new BigDecimal("154.489"), BigMathUtility.round(n, 3));
        Assert.assertEquals(new BigDecimal("154.49"), BigMathUtility.round(n, 2));
        Assert.assertEquals(new BigDecimal("154.5"), BigMathUtility.round(n, 1));
        Assert.assertEquals(new BigDecimal("154"), BigMathUtility.round(n, 0));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.round(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.round(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.round(null, 1));
    }
    
    /**
     * JUnit test of abs.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#abs(BigDecimal, int)
     * @see BigMathUtility#abs(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#abs(BigDecimal)
     */
    @Test
    public void testAbs() throws Exception {
        BigDecimal n;
        
        //standard
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.abs(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("8"), BigMathUtility.abs(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("33"), BigMathUtility.abs(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("54"), BigMathUtility.abs(new BigDecimal("54")));
        Assert.assertEquals(new BigDecimal("0.4154211"), BigMathUtility.abs(new BigDecimal(".4154211")));
        Assert.assertEquals(new BigDecimal("0.94108"), BigMathUtility.abs(new BigDecimal(".94108")));
        Assert.assertEquals(new BigDecimal("0.00033"), BigMathUtility.abs(new BigDecimal(".00033")));
        Assert.assertEquals(new BigDecimal("0.421654"), BigMathUtility.abs(new BigDecimal(".421654")));
        Assert.assertEquals(new BigDecimal("1.4154211"), BigMathUtility.abs(new BigDecimal("1.4154211")));
        Assert.assertEquals(new BigDecimal("8.94108"), BigMathUtility.abs(new BigDecimal("8.94108")));
        Assert.assertEquals(new BigDecimal("33.00033"), BigMathUtility.abs(new BigDecimal("33.00033")));
        Assert.assertEquals(new BigDecimal("54.421654"), BigMathUtility.abs(new BigDecimal("54.421654")));
        Assert.assertEquals(new BigDecimal("8.4E+41"), BigMathUtility.abs(new BigDecimal("840000000000000000000000000000000000000000")));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000084"), BigMathUtility.abs(new BigDecimal("0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.0001589798065791545632340893387375730404981084530212198708456547"), BigMathUtility.abs(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //negative
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.abs(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("8"), BigMathUtility.abs(new BigDecimal("-8")));
        Assert.assertEquals(new BigDecimal("33"), BigMathUtility.abs(new BigDecimal("-33")));
        Assert.assertEquals(new BigDecimal("54"), BigMathUtility.abs(new BigDecimal("-54")));
        Assert.assertEquals(new BigDecimal("0.4154211"), BigMathUtility.abs(new BigDecimal("-.4154211")));
        Assert.assertEquals(new BigDecimal("0.94108"), BigMathUtility.abs(new BigDecimal("-.94108")));
        Assert.assertEquals(new BigDecimal("0.00033"), BigMathUtility.abs(new BigDecimal("-.00033")));
        Assert.assertEquals(new BigDecimal("0.421654"), BigMathUtility.abs(new BigDecimal("-.421654")));
        Assert.assertEquals(new BigDecimal("1.4154211"), BigMathUtility.abs(new BigDecimal("-1.4154211")));
        Assert.assertEquals(new BigDecimal("8.94108"), BigMathUtility.abs(new BigDecimal("-8.94108")));
        Assert.assertEquals(new BigDecimal("33.00033"), BigMathUtility.abs(new BigDecimal("-33.00033")));
        Assert.assertEquals(new BigDecimal("54.421654"), BigMathUtility.abs(new BigDecimal("-54.421654")));
        Assert.assertEquals(new BigDecimal("8.4E+41"), BigMathUtility.abs(new BigDecimal("-840000000000000000000000000000000000000000")));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000084"), BigMathUtility.abs(new BigDecimal("-0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.0001589798065791545632340893387375730404981084530212198708456547"), BigMathUtility.abs(new BigDecimal("-874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //precision
        n = new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(
                new BigDecimal("154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.48910622"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048000"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.abs(n, null));
        
        //precision, negative
        n = new BigDecimal("-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(
                new BigDecimal("154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.48910622"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048000"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),
                BigMathUtility.abs(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.abs(n, null));
        
        //custom precision
        n = new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(new BigDecimal("154.489106216501269"), BigMathUtility.abs(n, 15));
        Assert.assertEquals(new BigDecimal("154.4891062165013"), BigMathUtility.abs(n, 13));
        Assert.assertEquals(new BigDecimal("154.489106216501"), BigMathUtility.abs(n, 12));
        Assert.assertEquals(new BigDecimal("154.4891062165"), BigMathUtility.abs(n, 10));
        Assert.assertEquals(new BigDecimal("154.489106217"), BigMathUtility.abs(n, 9));
        Assert.assertEquals(new BigDecimal("154.489106"), BigMathUtility.abs(n, 6));
        Assert.assertEquals(new BigDecimal("154.489"), BigMathUtility.abs(n, 3));
        Assert.assertEquals(new BigDecimal("154.49"), BigMathUtility.abs(n, 2));
        Assert.assertEquals(new BigDecimal("154.5"), BigMathUtility.abs(n, 1));
        Assert.assertEquals(new BigDecimal("154"), BigMathUtility.abs(n, 0));
        
        //custom precision, negative
        n = new BigDecimal("-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(new BigDecimal("154.489106216501269"), BigMathUtility.abs(n, 15));
        Assert.assertEquals(new BigDecimal("154.4891062165013"), BigMathUtility.abs(n, 13));
        Assert.assertEquals(new BigDecimal("154.489106216501"), BigMathUtility.abs(n, 12));
        Assert.assertEquals(new BigDecimal("154.4891062165"), BigMathUtility.abs(n, 10));
        Assert.assertEquals(new BigDecimal("154.489106217"), BigMathUtility.abs(n, 9));
        Assert.assertEquals(new BigDecimal("154.489106"), BigMathUtility.abs(n, 6));
        Assert.assertEquals(new BigDecimal("154.489"), BigMathUtility.abs(n, 3));
        Assert.assertEquals(new BigDecimal("154.49"), BigMathUtility.abs(n, 2));
        Assert.assertEquals(new BigDecimal("154.5"), BigMathUtility.abs(n, 1));
        Assert.assertEquals(new BigDecimal("154"), BigMathUtility.abs(n, 0));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.abs(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.abs(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.abs(null, 1));
    }
    
    /**
     * JUnit test of negate.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#negate(BigDecimal, int)
     * @see BigMathUtility#negate(BigDecimal, BigMathUtility.PrecisionMode)
     * @see BigMathUtility#negate(BigDecimal)
     */
    @Test
    public void testNegate() throws Exception {
        BigDecimal n;
        
        //standard
        Assert.assertEquals(new BigDecimal("-1"), BigMathUtility.negate(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("-8"), BigMathUtility.negate(new BigDecimal("8")));
        Assert.assertEquals(new BigDecimal("-33"), BigMathUtility.negate(new BigDecimal("33")));
        Assert.assertEquals(new BigDecimal("-54"), BigMathUtility.negate(new BigDecimal("54")));
        Assert.assertEquals(new BigDecimal("-0.4154211"), BigMathUtility.negate(new BigDecimal(".4154211")));
        Assert.assertEquals(new BigDecimal("-0.94108"), BigMathUtility.negate(new BigDecimal(".94108")));
        Assert.assertEquals(new BigDecimal("-0.00033"), BigMathUtility.negate(new BigDecimal(".00033")));
        Assert.assertEquals(new BigDecimal("-0.421654"), BigMathUtility.negate(new BigDecimal(".421654")));
        Assert.assertEquals(new BigDecimal("-1.4154211"), BigMathUtility.negate(new BigDecimal("1.4154211")));
        Assert.assertEquals(new BigDecimal("-8.94108"), BigMathUtility.negate(new BigDecimal("8.94108")));
        Assert.assertEquals(new BigDecimal("-33.00033"), BigMathUtility.negate(new BigDecimal("33.00033")));
        Assert.assertEquals(new BigDecimal("-54.421654"), BigMathUtility.negate(new BigDecimal("54.421654")));
        Assert.assertEquals(new BigDecimal("-8.4E+41"), BigMathUtility.negate(new BigDecimal("840000000000000000000000000000000000000000")));
        Assert.assertEquals(new BigDecimal("-0.0000000000000000000000000000000000000084"), BigMathUtility.negate(new BigDecimal("0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(new BigDecimal("-874560984810495456040984654132198077871049809840598711111771980.0001589798065791545632340893387375730404981084530212198708456547"), BigMathUtility.negate(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //negative
        Assert.assertEquals(new BigDecimal("1"), BigMathUtility.negate(new BigDecimal("-1")));
        Assert.assertEquals(new BigDecimal("8"), BigMathUtility.negate(new BigDecimal("-8")));
        Assert.assertEquals(new BigDecimal("33"), BigMathUtility.negate(new BigDecimal("-33")));
        Assert.assertEquals(new BigDecimal("54"), BigMathUtility.negate(new BigDecimal("-54")));
        Assert.assertEquals(new BigDecimal("0.4154211"), BigMathUtility.negate(new BigDecimal("-.4154211")));
        Assert.assertEquals(new BigDecimal("0.94108"), BigMathUtility.negate(new BigDecimal("-.94108")));
        Assert.assertEquals(new BigDecimal("0.00033"), BigMathUtility.negate(new BigDecimal("-.00033")));
        Assert.assertEquals(new BigDecimal("0.421654"), BigMathUtility.negate(new BigDecimal("-.421654")));
        Assert.assertEquals(new BigDecimal("1.4154211"), BigMathUtility.negate(new BigDecimal("-1.4154211")));
        Assert.assertEquals(new BigDecimal("8.94108"), BigMathUtility.negate(new BigDecimal("-8.94108")));
        Assert.assertEquals(new BigDecimal("33.00033"), BigMathUtility.negate(new BigDecimal("-33.00033")));
        Assert.assertEquals(new BigDecimal("54.421654"), BigMathUtility.negate(new BigDecimal("-54.421654")));
        Assert.assertEquals(new BigDecimal("8.4E+41"), BigMathUtility.negate(new BigDecimal("-840000000000000000000000000000000000000000")));
        Assert.assertEquals(new BigDecimal("0.0000000000000000000000000000000000000084"), BigMathUtility.negate(new BigDecimal("-0.0000000000000000000000000000000000000084")));
        Assert.assertEquals(new BigDecimal("874560984810495456040984654132198077871049809840598711111771980.0001589798065791545632340893387375730404981084530212198708456547"), BigMathUtility.negate(new BigDecimal("-874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098")));
        
        //precision
        n = new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(
                new BigDecimal("-154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                new BigDecimal("-154"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                new BigDecimal("-154.48910622"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                new BigDecimal("-154.4891062165012685"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                new BigDecimal("-154.4891062165012685085610410498409854154201095120408084848770653210"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("-154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048000"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                new BigDecimal("-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("-154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.negate(n, null));
        
        //precision, negative
        n = new BigDecimal("-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(
                new BigDecimal("154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.48910622"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048000"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),
                BigMathUtility.negate(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                new BigDecimal("154.489106216501268508561041049840985415420109512040808484877065321"),
                BigMathUtility.negate(n, null));
        
        //custom precision
        n = new BigDecimal("154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(new BigDecimal("-154.489106216501269"), BigMathUtility.negate(n, 15));
        Assert.assertEquals(new BigDecimal("-154.4891062165013"), BigMathUtility.negate(n, 13));
        Assert.assertEquals(new BigDecimal("-154.489106216501"), BigMathUtility.negate(n, 12));
        Assert.assertEquals(new BigDecimal("-154.4891062165"), BigMathUtility.negate(n, 10));
        Assert.assertEquals(new BigDecimal("-154.489106217"), BigMathUtility.negate(n, 9));
        Assert.assertEquals(new BigDecimal("-154.489106"), BigMathUtility.negate(n, 6));
        Assert.assertEquals(new BigDecimal("-154.489"), BigMathUtility.negate(n, 3));
        Assert.assertEquals(new BigDecimal("-154.49"), BigMathUtility.negate(n, 2));
        Assert.assertEquals(new BigDecimal("-154.5"), BigMathUtility.negate(n, 1));
        Assert.assertEquals(new BigDecimal("-154"), BigMathUtility.negate(n, 0));
        
        //custom precision, negative
        n = new BigDecimal("-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515");
        Assert.assertEquals(new BigDecimal("154.489106216501269"), BigMathUtility.negate(n, 15));
        Assert.assertEquals(new BigDecimal("154.4891062165013"), BigMathUtility.negate(n, 13));
        Assert.assertEquals(new BigDecimal("154.489106216501"), BigMathUtility.negate(n, 12));
        Assert.assertEquals(new BigDecimal("154.4891062165"), BigMathUtility.negate(n, 10));
        Assert.assertEquals(new BigDecimal("154.489106217"), BigMathUtility.negate(n, 9));
        Assert.assertEquals(new BigDecimal("154.489106"), BigMathUtility.negate(n, 6));
        Assert.assertEquals(new BigDecimal("154.489"), BigMathUtility.negate(n, 3));
        Assert.assertEquals(new BigDecimal("154.49"), BigMathUtility.negate(n, 2));
        Assert.assertEquals(new BigDecimal("154.5"), BigMathUtility.negate(n, 1));
        Assert.assertEquals(new BigDecimal("154"), BigMathUtility.negate(n, 0));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.negate(null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.negate(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.negate(null, 1));
    }
    
    /**
     * JUnit test of greaterThan.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#greaterThan(BigDecimal, BigDecimal)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testGreaterThan() throws Exception {
        //standard
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("0"), new BigDecimal("88")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("0549"), new BigDecimal("0")));
        
        //negative
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-1"), new BigDecimal("1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-17"), new BigDecimal("4")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-9"), new BigDecimal("177")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-0"), new BigDecimal("88")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-0549"), new BigDecimal("0")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("9"), new BigDecimal("-177")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("0"), new BigDecimal("-88")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("0549"), new BigDecimal("-0")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-1"), new BigDecimal("-1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-17"), new BigDecimal("-4")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("-9"), new BigDecimal("-177")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("-0"), new BigDecimal("-88")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-0549"), new BigDecimal("-0")));
        
        //decimal
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("0.578"), new BigDecimal("984.941")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-1"), new BigDecimal("0.1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-1"), new BigDecimal(".1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-.1"), new BigDecimal(".1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-3.1"), new BigDecimal("1.9")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-3.1"), new BigDecimal("0.1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-03.1"), new BigDecimal("20.1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-.578"), new BigDecimal(".941")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-0.578"), new BigDecimal("984.941")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-.0000000578"), new BigDecimal(".00941")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-1"), new BigDecimal("-0.1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-1"), new BigDecimal("-.1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-.1"), new BigDecimal("-.1")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-3.1"), new BigDecimal("-1.9")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-3.1"), new BigDecimal("-0.1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("-03.1"), new BigDecimal("-20.1")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("-.578"), new BigDecimal("-.941")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("-0.578"), new BigDecimal("-984.941")));
        Assert.assertTrue(BigMathUtility.greaterThan(new BigDecimal("-.0000000578"), new BigDecimal("-.00941")));
        Assert.assertFalse(BigMathUtility.greaterThan(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941")));
        
        //larger cases
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("15649826500097987154602319879456339009444871265"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("15649826500097987154602319879456339009444871265"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        
        //larger cases, negative
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-15649826500097987154602319879456339009444871265"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("15649826500097987154602319879456339009444871265"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("15649826500097987154602319879456339009444871265"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-15649826500097987154602319879456339009444871265"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("-15649826500097987154602319879456339009444871265"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-15649826500097987154602319879456339009444871265"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.greaterThan(new BigDecimal("1"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.greaterThan(null, new BigDecimal("1")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.greaterThan(null, null));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.greaterThan(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.greaterThan(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.greaterThan(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
    /**
     * JUnit test of lessThan.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#lessThan(BigDecimal, BigDecimal)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testLessThan() throws Exception {
        //standard
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("0"), new BigDecimal("88")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("0549"), new BigDecimal("0")));
        
        //negative
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-1"), new BigDecimal("1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-17"), new BigDecimal("4")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-9"), new BigDecimal("177")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-0"), new BigDecimal("88")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-0549"), new BigDecimal("0")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("9"), new BigDecimal("-177")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("0"), new BigDecimal("-88")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("0549"), new BigDecimal("-0")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("-1"), new BigDecimal("-1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-17"), new BigDecimal("-4")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("-9"), new BigDecimal("-177")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("-0"), new BigDecimal("-88")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-0549"), new BigDecimal("-0")));
        
        //decimal
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("0.578"), new BigDecimal("984.941")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-1"), new BigDecimal("0.1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-1"), new BigDecimal(".1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-.1"), new BigDecimal(".1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-3.1"), new BigDecimal("1.9")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-3.1"), new BigDecimal("0.1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-03.1"), new BigDecimal("20.1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-.578"), new BigDecimal(".941")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-0.578"), new BigDecimal("984.941")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-.0000000578"), new BigDecimal(".00941")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-1"), new BigDecimal("-0.1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-1"), new BigDecimal("-.1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("-.1"), new BigDecimal("-.1")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-3.1"), new BigDecimal("-1.9")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-3.1"), new BigDecimal("-0.1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("-03.1"), new BigDecimal("-20.1")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("-.578"), new BigDecimal("-.941")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("-0.578"), new BigDecimal("-984.941")));
        Assert.assertFalse(BigMathUtility.lessThan(new BigDecimal("-.0000000578"), new BigDecimal("-.00941")));
        Assert.assertTrue(BigMathUtility.lessThan(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941")));
        
        //larger cases
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("15649826500097987154602319879456339009444871265"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("15649826500097987154602319879456339009444871265"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        
        //larger cases, negative
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-15649826500097987154602319879456339009444871265"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("15649826500097987154602319879456339009444871265"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("15649826500097987154602319879456339009444871265"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-15649826500097987154602319879456339009444871265"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("-15649826500097987154602319879456339009444871265"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-15649826500097987154602319879456339009444871265"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.lessThan(new BigDecimal("1"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.lessThan(null, new BigDecimal("1")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.lessThan(null, null));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.lessThan(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.lessThan(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.lessThan(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
    /**
     * JUnit test of equalTo.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#equalTo(BigDecimal, BigDecimal)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testEqualTo() throws Exception {
        //standard
        Assert.assertTrue(BigMathUtility.equalTo(new BigDecimal("1"), new BigDecimal("1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("17"), new BigDecimal("4")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("9"), new BigDecimal("177")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("0"), new BigDecimal("88")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("0549"), new BigDecimal("0")));
        
        //negative
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-1"), new BigDecimal("1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-17"), new BigDecimal("4")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-9"), new BigDecimal("177")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-0"), new BigDecimal("88")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-0549"), new BigDecimal("0")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("1"), new BigDecimal("-1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("17"), new BigDecimal("-4")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("9"), new BigDecimal("-177")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("0"), new BigDecimal("-88")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("0549"), new BigDecimal("-0")));
        Assert.assertTrue(BigMathUtility.equalTo(new BigDecimal("-1"), new BigDecimal("-1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-17"), new BigDecimal("-4")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-9"), new BigDecimal("-177")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-0"), new BigDecimal("-88")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-0549"), new BigDecimal("-0")));
        
        //decimal
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("1"), new BigDecimal("0.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("1"), new BigDecimal(".1")));
        Assert.assertTrue(BigMathUtility.equalTo(new BigDecimal(".1"), new BigDecimal(".1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("3.1"), new BigDecimal("1.9")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("3.1"), new BigDecimal("0.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("03.1"), new BigDecimal("20.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal(".578"), new BigDecimal(".941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("0.578"), new BigDecimal("984.941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal(".0000000578"), new BigDecimal(".00941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("18.0000000578"), new BigDecimal("2.00941")));
        
        //decimal, negative
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-1"), new BigDecimal("0.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-1"), new BigDecimal(".1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-.1"), new BigDecimal(".1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-3.1"), new BigDecimal("1.9")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-3.1"), new BigDecimal("0.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-03.1"), new BigDecimal("20.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-.578"), new BigDecimal(".941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-0.578"), new BigDecimal("984.941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-.0000000578"), new BigDecimal(".00941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-18.0000000578"), new BigDecimal("2.00941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("1"), new BigDecimal("-0.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("1"), new BigDecimal("-.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal(".1"), new BigDecimal("-.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("3.1"), new BigDecimal("-1.9")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("3.1"), new BigDecimal("-0.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("03.1"), new BigDecimal("-20.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal(".578"), new BigDecimal("-.941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("0.578"), new BigDecimal("-984.941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal(".0000000578"), new BigDecimal("-.00941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("18.0000000578"), new BigDecimal("-2.00941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-1"), new BigDecimal("-0.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-1"), new BigDecimal("-.1")));
        Assert.assertTrue(BigMathUtility.equalTo(new BigDecimal("-.1"), new BigDecimal("-.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-3.1"), new BigDecimal("-1.9")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-3.1"), new BigDecimal("-0.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-03.1"), new BigDecimal("-20.1")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-.578"), new BigDecimal("-.941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-0.578"), new BigDecimal("-984.941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-.0000000578"), new BigDecimal("-.00941")));
        Assert.assertFalse(BigMathUtility.equalTo(new BigDecimal("-18.0000000578"), new BigDecimal("-2.00941")));
        
        //larger cases
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("15649826500097987154602319879456339009444871265"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("15649826500097987154602319879456339009444871265"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        
        //larger cases, negative
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-15649826500097987154602319879456339009444871265"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("15649826500097987154602319879456339009444871265"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("15649826500097987154602319879456339009444871265"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-15649826500097987154602319879456339009444871265"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-15649826500097987154602319879456339009444871265"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-15649826500097987154602319879456339009444871265"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-0.000000000000000000000000000000048945494089484366316897451509898750316549"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"))
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"),
                new BigDecimal("-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002"))
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.equalTo(new BigDecimal("1"), null));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.equalTo(null, new BigDecimal("1")));
        TestUtils.assertException(NullPointerException.class, () ->
                BigMathUtility.equalTo(null, null));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.equalTo(new BigDecimal("1"), new BigDecimal("15s5")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.equalTo(new BigDecimal("15s5"), new BigDecimal("1")));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                BigMathUtility.equalTo(new BigDecimal("15s5"), new BigDecimal("a")));
    }
    
}
