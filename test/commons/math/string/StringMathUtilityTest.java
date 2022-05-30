/*
 * File:    StringMathUtilityTest.java
 * Package: commons.math.string
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.string;

import commons.math.big.BigMathUtility;
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
 * JUnit test of StringMathUtility.
 *
 * @see StringMathUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({StringMathUtility.class})
public class StringMathUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(StringMathUtilityTest.class);
    
    
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
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#add(String, String, int)
     * @see StringMathUtility#add(String, String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#add(String, String)
     */
    @Test
    public void testAdd() throws Exception {
        //standard
        Assert.assertEquals("2", StringMathUtility.add("1", "1"));
        Assert.assertEquals("21", StringMathUtility.add("17", "4"));
        Assert.assertEquals("186", StringMathUtility.add("9", "177"));
        Assert.assertEquals("88", StringMathUtility.add("0", "88"));
        Assert.assertEquals("549", StringMathUtility.add("0549", "0"));
        Assert.assertEquals("8", StringMathUtility.add("9", "-1"));
        
        //negative
        Assert.assertEquals("0", StringMathUtility.add("-1", "1"));
        Assert.assertEquals("-13", StringMathUtility.add("-17", "4"));
        Assert.assertEquals("168", StringMathUtility.add("-9", "177"));
        Assert.assertEquals("88", StringMathUtility.add("-0", "88"));
        Assert.assertEquals("-549", StringMathUtility.add("-0549", "0"));
        Assert.assertEquals("0", StringMathUtility.add("1", "-1"));
        Assert.assertEquals("13", StringMathUtility.add("17", "-4"));
        Assert.assertEquals("-168", StringMathUtility.add("9", "-177"));
        Assert.assertEquals("-88", StringMathUtility.add("0", "-88"));
        Assert.assertEquals("549", StringMathUtility.add("0549", "-0"));
        Assert.assertEquals("-2", StringMathUtility.add("-1", "-1"));
        Assert.assertEquals("-21", StringMathUtility.add("-17", "-4"));
        Assert.assertEquals("-186", StringMathUtility.add("-9", "-177"));
        Assert.assertEquals("-88", StringMathUtility.add("-0", "-88"));
        Assert.assertEquals("-549", StringMathUtility.add("-0549", "-0"));
        
        //decimal
        Assert.assertEquals("1.1", StringMathUtility.add("1", "0.1"));
        Assert.assertEquals("1.1", StringMathUtility.add("1", ".1"));
        Assert.assertEquals("0.2", StringMathUtility.add(".1", ".1"));
        Assert.assertEquals("5.0", StringMathUtility.add("3.1", "1.9"));
        Assert.assertEquals("3.2", StringMathUtility.add("3.1", "0.1"));
        Assert.assertEquals("23.2", StringMathUtility.add("03.1", "20.1"));
        Assert.assertEquals("1.519", StringMathUtility.add(".578", ".941"));
        Assert.assertEquals("985.519", StringMathUtility.add("0.578", "984.941"));
        Assert.assertEquals("0.0094100578", StringMathUtility.add(".0000000578", ".00941"));
        Assert.assertEquals("20.0094100578", StringMathUtility.add("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("-0.9", StringMathUtility.add("-1", "0.1"));
        Assert.assertEquals("-0.9", StringMathUtility.add("-1", ".1"));
        Assert.assertEquals("0.0", StringMathUtility.add("-.1", ".1"));
        Assert.assertEquals("-1.2", StringMathUtility.add("-3.1", "1.9"));
        Assert.assertEquals("-3.0", StringMathUtility.add("-3.1", "0.1"));
        Assert.assertEquals("17.0", StringMathUtility.add("-03.1", "20.1"));
        Assert.assertEquals("0.363", StringMathUtility.add("-.578", ".941"));
        Assert.assertEquals("984.363", StringMathUtility.add("-0.578", "984.941"));
        Assert.assertEquals("0.0094099422", StringMathUtility.add("-.0000000578", ".00941"));
        Assert.assertEquals("-15.9905900578", StringMathUtility.add("-18.0000000578", "2.00941"));
        Assert.assertEquals("0.9", StringMathUtility.add("1", "-0.1"));
        Assert.assertEquals("0.9", StringMathUtility.add("1", "-.1"));
        Assert.assertEquals("0.0", StringMathUtility.add(".1", "-.1"));
        Assert.assertEquals("1.2", StringMathUtility.add("3.1", "-1.9"));
        Assert.assertEquals("3.0", StringMathUtility.add("3.1", "-0.1"));
        Assert.assertEquals("-17.0", StringMathUtility.add("03.1", "-20.1"));
        Assert.assertEquals("-0.363", StringMathUtility.add(".578", "-.941"));
        Assert.assertEquals("-984.363", StringMathUtility.add("0.578", "-984.941"));
        Assert.assertEquals("-0.0094099422", StringMathUtility.add(".0000000578", "-.00941"));
        Assert.assertEquals("15.9905900578", StringMathUtility.add("18.0000000578", "-2.00941"));
        Assert.assertEquals("-1.1", StringMathUtility.add("-1", "-0.1"));
        Assert.assertEquals("-1.1", StringMathUtility.add("-1", "-.1"));
        Assert.assertEquals("-0.2", StringMathUtility.add("-.1", "-.1"));
        Assert.assertEquals("-5.0", StringMathUtility.add("-3.1", "-1.9"));
        Assert.assertEquals("-3.2", StringMathUtility.add("-3.1", "-0.1"));
        Assert.assertEquals("-23.2", StringMathUtility.add("-03.1", "-20.1"));
        Assert.assertEquals("-1.519", StringMathUtility.add("-.578", "-.941"));
        Assert.assertEquals("-985.519", StringMathUtility.add("-0.578", "-984.941"));
        Assert.assertEquals("-0.0094100578", StringMathUtility.add("-.0000000578", "-.00941"));
        Assert.assertEquals("-20.0094100578", StringMathUtility.add("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.add("1", "0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.0", StringMathUtility.add("1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.0", StringMathUtility.add("1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.add("1", "0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.add("1", "0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.add("1", "0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.add("1", "0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.add("1", "0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.add("1", "0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.add("1", "0", null));
        
        //precision, negative
        Assert.assertEquals("-1", StringMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1.0", StringMathUtility.add("-1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", StringMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("-1.00000000", StringMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("-1.0000000000000000", StringMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("-1", StringMathUtility.add("-1", "0", null));
        
        //custom precision
        Assert.assertEquals("0.0094100578", StringMathUtility.add(".0000000578", ".00941", 10));
        Assert.assertEquals("0.0094101", StringMathUtility.add(".0000000578", ".00941", 7));
        Assert.assertEquals("0.009410", StringMathUtility.add(".0000000578", ".00941", 6));
        Assert.assertEquals("0.009", StringMathUtility.add(".0000000578", ".00941", 3));
        Assert.assertEquals("0.01", StringMathUtility.add(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.add(".0000000578", ".00941", 1));
        Assert.assertEquals("20.0094100578", StringMathUtility.add("18.0000000578", "2.00941", 10));
        Assert.assertEquals("20.0094101", StringMathUtility.add("18.0000000578", "2.00941", 7));
        Assert.assertEquals("20.009410", StringMathUtility.add("18.0000000578", "2.00941", 6));
        Assert.assertEquals("20.009", StringMathUtility.add("18.0000000578", "2.00941", 3));
        Assert.assertEquals("20.01", StringMathUtility.add("18.0000000578", "2.00941", 2));
        Assert.assertEquals("20.0", StringMathUtility.add("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.add("2.99999999999999", "0", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.add("2.99999999999999", "0", 10));
        Assert.assertEquals("3.00000", StringMathUtility.add("2.99999999999999", "0", 5));
        Assert.assertEquals("3.0", StringMathUtility.add("2.99999999999999", "0", 1));
        
        //custom precision, negative
        Assert.assertEquals("0.0094099422", StringMathUtility.add("-.0000000578", ".00941", 10));
        Assert.assertEquals("0.0094099", StringMathUtility.add("-.0000000578", ".00941", 7));
        Assert.assertEquals("0.009410", StringMathUtility.add("-.0000000578", ".00941", 6));
        Assert.assertEquals("0.009", StringMathUtility.add("-.0000000578", ".00941", 3));
        Assert.assertEquals("0.01", StringMathUtility.add("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.add("-.0000000578", ".00941", 1));
        Assert.assertEquals("-15.9905900578", StringMathUtility.add("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-15.9905901", StringMathUtility.add("-18.0000000578", "2.00941", 7));
        Assert.assertEquals("-15.991", StringMathUtility.add("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-15.99", StringMathUtility.add("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-16.0", StringMathUtility.add("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-2.99999999999999", StringMathUtility.add("-2.99999999999999", "0", 14));
        Assert.assertEquals("-3.0000000000", StringMathUtility.add("-2.99999999999999", "0", 10));
        Assert.assertEquals("-3.00000", StringMathUtility.add("-2.99999999999999", "0", 5));
        Assert.assertEquals("-3.0", StringMathUtility.add("-2.99999999999999", "0", 1));
        Assert.assertEquals("-0.0094099422", StringMathUtility.add(".0000000578", "-.00941", 10));
        Assert.assertEquals("-0.0094099", StringMathUtility.add(".0000000578", "-.00941", 7));
        Assert.assertEquals("-0.009410", StringMathUtility.add("-.0000000578", "-.00941", 6));
        Assert.assertEquals("-0.009", StringMathUtility.add(".0000000578", "-.00941", 3));
        Assert.assertEquals("-0.01", StringMathUtility.add(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.add(".0000000578", "-.00941", 1));
        Assert.assertEquals("15.9905900578", StringMathUtility.add("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("15.9905901", StringMathUtility.add("18.0000000578", "-2.00941", 7));
        Assert.assertEquals("15.990590", StringMathUtility.add("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("15.991", StringMathUtility.add("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("15.99", StringMathUtility.add("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("16.0", StringMathUtility.add("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.add("2.99999999999999", "-0", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.add("2.99999999999999", "-0", 10));
        Assert.assertEquals("3.00000", StringMathUtility.add("2.99999999999999", "-0", 5));
        Assert.assertEquals("3.0", StringMathUtility.add("2.99999999999999", "-0", 1));
        Assert.assertEquals("-0.0094100578", StringMathUtility.add("-.0000000578", "-.00941", 10));
        Assert.assertEquals("-0.0094101", StringMathUtility.add("-.0000000578", "-.00941", 7));
        Assert.assertEquals("-0.009410", StringMathUtility.add("-.0000000578", "-.00941", 6));
        Assert.assertEquals("-0.009", StringMathUtility.add("-.0000000578", "-.00941", 3));
        Assert.assertEquals("-0.01", StringMathUtility.add("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.add("-.0000000578", "-.00941", 1));
        Assert.assertEquals("-20.0094100578", StringMathUtility.add("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-20.0094101", StringMathUtility.add("-18.0000000578", "-2.00941", 7));
        Assert.assertEquals("-20.009410", StringMathUtility.add("-18.0000000578", "-2.00941", 6));
        Assert.assertEquals("-20.009", StringMathUtility.add("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-20.01", StringMathUtility.add("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-20.0", StringMathUtility.add("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-2.99999999999999", StringMathUtility.add("-2.99999999999999", "-0", 14));
        Assert.assertEquals("-3.0000000000", StringMathUtility.add("-2.99999999999999", "-0", 10));
        Assert.assertEquals("-3.00000", StringMathUtility.add("-2.99999999999999", "-0", 5));
        Assert.assertEquals("-3.0", StringMathUtility.add("-2.99999999999999", "-0", 1));
        
        //larger cases
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829",
                StringMathUtility.add(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829",
                StringMathUtility.add(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.add(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.add(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.add(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.add(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299",
                StringMathUtility.add(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299",
                StringMathUtility.add(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.add(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.add(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.add(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.add(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299",
                StringMathUtility.add(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299",
                StringMathUtility.add(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.add(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.add(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.add(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.add(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829",
                StringMathUtility.add(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829",
                StringMathUtility.add(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.add(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.add(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.add(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.add(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.add(null, "2"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.add("2", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.add(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.add(null, "2", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.add("2", null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.add(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.add(null, "2", 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.add("2", null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.add(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.add("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.add("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.add("15s5", "a"));
    }
    
    /**
     * JUnit test of subtract.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#subtract(String, String, int)
     * @see StringMathUtility#subtract(String, String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#subtract(String, String)
     */
    @Test
    public void testSubtract() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.subtract("1", "1"));
        Assert.assertEquals("13", StringMathUtility.subtract("17", "4"));
        Assert.assertEquals("-168", StringMathUtility.subtract("9", "177"));
        Assert.assertEquals("-88", StringMathUtility.subtract("0", "88"));
        Assert.assertEquals("549", StringMathUtility.subtract("0549", "0"));
        Assert.assertEquals("10", StringMathUtility.subtract("9", "-1"));
        
        //negative
        Assert.assertEquals("-2", StringMathUtility.subtract("-1", "1"));
        Assert.assertEquals("-21", StringMathUtility.subtract("-17", "4"));
        Assert.assertEquals("-186", StringMathUtility.subtract("-9", "177"));
        Assert.assertEquals("-88", StringMathUtility.subtract("-0", "88"));
        Assert.assertEquals("-549", StringMathUtility.subtract("-0549", "0"));
        Assert.assertEquals("2", StringMathUtility.subtract("1", "-1"));
        Assert.assertEquals("21", StringMathUtility.subtract("17", "-4"));
        Assert.assertEquals("186", StringMathUtility.subtract("9", "-177"));
        Assert.assertEquals("88", StringMathUtility.subtract("0", "-88"));
        Assert.assertEquals("549", StringMathUtility.subtract("0549", "-0"));
        Assert.assertEquals("0", StringMathUtility.subtract("-1", "-1"));
        Assert.assertEquals("-13", StringMathUtility.subtract("-17", "-4"));
        Assert.assertEquals("168", StringMathUtility.subtract("-9", "-177"));
        Assert.assertEquals("88", StringMathUtility.subtract("-0", "-88"));
        Assert.assertEquals("-549", StringMathUtility.subtract("-0549", "-0"));
        
        //decimal
        Assert.assertEquals("0.9", StringMathUtility.subtract("1", "0.1"));
        Assert.assertEquals("0.9", StringMathUtility.subtract("1", ".1"));
        Assert.assertEquals("0.0", StringMathUtility.subtract(".1", ".1"));
        Assert.assertEquals("1.2", StringMathUtility.subtract("3.1", "1.9"));
        Assert.assertEquals("3.0", StringMathUtility.subtract("3.1", "0.1"));
        Assert.assertEquals("-17.0", StringMathUtility.subtract("03.1", "20.1"));
        Assert.assertEquals("-0.363", StringMathUtility.subtract(".578", ".941"));
        Assert.assertEquals("-984.363", StringMathUtility.subtract("0.578", "984.941"));
        Assert.assertEquals("-0.0094099422", StringMathUtility.subtract(".0000000578", ".00941"));
        Assert.assertEquals("15.9905900578", StringMathUtility.subtract("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("-1.1", StringMathUtility.subtract("-1", "0.1"));
        Assert.assertEquals("-1.1", StringMathUtility.subtract("-1", ".1"));
        Assert.assertEquals("-0.2", StringMathUtility.subtract("-.1", ".1"));
        Assert.assertEquals("-5.0", StringMathUtility.subtract("-3.1", "1.9"));
        Assert.assertEquals("-3.2", StringMathUtility.subtract("-3.1", "0.1"));
        Assert.assertEquals("-23.2", StringMathUtility.subtract("-03.1", "20.1"));
        Assert.assertEquals("-1.519", StringMathUtility.subtract("-.578", ".941"));
        Assert.assertEquals("-985.519", StringMathUtility.subtract("-0.578", "984.941"));
        Assert.assertEquals("-0.0094100578", StringMathUtility.subtract("-.0000000578", ".00941"));
        Assert.assertEquals("-20.0094100578", StringMathUtility.subtract("-18.0000000578", "2.00941"));
        Assert.assertEquals("1.1", StringMathUtility.subtract("1", "-0.1"));
        Assert.assertEquals("1.1", StringMathUtility.subtract("1", "-.1"));
        Assert.assertEquals("0.2", StringMathUtility.subtract(".1", "-.1"));
        Assert.assertEquals("5.0", StringMathUtility.subtract("3.1", "-1.9"));
        Assert.assertEquals("3.2", StringMathUtility.subtract("3.1", "-0.1"));
        Assert.assertEquals("23.2", StringMathUtility.subtract("03.1", "-20.1"));
        Assert.assertEquals("1.519", StringMathUtility.subtract(".578", "-.941"));
        Assert.assertEquals("985.519", StringMathUtility.subtract("0.578", "-984.941"));
        Assert.assertEquals("0.0094100578", StringMathUtility.subtract(".0000000578", "-.00941"));
        Assert.assertEquals("20.0094100578", StringMathUtility.subtract("18.0000000578", "-2.00941"));
        Assert.assertEquals("-0.9", StringMathUtility.subtract("-1", "-0.1"));
        Assert.assertEquals("-0.9", StringMathUtility.subtract("-1", "-.1"));
        Assert.assertEquals("0.0", StringMathUtility.subtract("-.1", "-.1"));
        Assert.assertEquals("-1.2", StringMathUtility.subtract("-3.1", "-1.9"));
        Assert.assertEquals("-3.0", StringMathUtility.subtract("-3.1", "-0.1"));
        Assert.assertEquals("17.0", StringMathUtility.subtract("-03.1", "-20.1"));
        Assert.assertEquals("0.363", StringMathUtility.subtract("-.578", "-.941"));
        Assert.assertEquals("984.363", StringMathUtility.subtract("-0.578", "-984.941"));
        Assert.assertEquals("0.0094099422", StringMathUtility.subtract("-.0000000578", "-.00941"));
        Assert.assertEquals("-15.9905900578", StringMathUtility.subtract("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.0", StringMathUtility.subtract("1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.subtract("1", "0", null));
        
        //precision, negative
        Assert.assertEquals("-1", StringMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1.0", StringMathUtility.subtract("-1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", StringMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("-1.00000000", StringMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("-1.0000000000000000", StringMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("-1", StringMathUtility.subtract("-1", "0", null));
        
        //custom precision
        Assert.assertEquals("-0.0094099422", StringMathUtility.subtract(".0000000578", ".00941", 10));
        Assert.assertEquals("-0.0094099", StringMathUtility.subtract(".0000000578", ".00941", 7));
        Assert.assertEquals("-0.009410", StringMathUtility.subtract("-.0000000578", ".00941", 6));
        Assert.assertEquals("-0.009", StringMathUtility.subtract(".0000000578", ".00941", 3));
        Assert.assertEquals("-0.01", StringMathUtility.subtract(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.subtract(".0000000578", ".00941", 1));
        Assert.assertEquals("15.9905900578", StringMathUtility.subtract("18.0000000578", "2.00941", 10));
        Assert.assertEquals("15.9905901", StringMathUtility.subtract("18.0000000578", "2.00941", 7));
        Assert.assertEquals("15.990590", StringMathUtility.subtract("18.0000000578", "2.00941", 6));
        Assert.assertEquals("15.991", StringMathUtility.subtract("18.0000000578", "2.00941", 3));
        Assert.assertEquals("15.99", StringMathUtility.subtract("18.0000000578", "2.00941", 2));
        Assert.assertEquals("16.0", StringMathUtility.subtract("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.subtract("2.99999999999999", "0", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.subtract("2.99999999999999", "0", 10));
        Assert.assertEquals("3.00000", StringMathUtility.subtract("2.99999999999999", "0", 5));
        Assert.assertEquals("3.0", StringMathUtility.subtract("2.99999999999999", "0", 1));
        
        //custom precision, negative
        Assert.assertEquals("-0.0094100578", StringMathUtility.subtract("-.0000000578", ".00941", 10));
        Assert.assertEquals("-0.0094101", StringMathUtility.subtract("-.0000000578", ".00941", 7));
        Assert.assertEquals("-0.009410", StringMathUtility.subtract("-.0000000578", ".00941", 6));
        Assert.assertEquals("-0.009", StringMathUtility.subtract("-.0000000578", ".00941", 3));
        Assert.assertEquals("-0.01", StringMathUtility.subtract("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.subtract("-.0000000578", ".00941", 1));
        Assert.assertEquals("-20.0094100578", StringMathUtility.subtract("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-20.0094101", StringMathUtility.subtract("-18.0000000578", "2.00941", 7));
        Assert.assertEquals("-20.009410", StringMathUtility.subtract("-18.0000000578", "2.00941", 6));
        Assert.assertEquals("-20.009", StringMathUtility.subtract("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-20.01", StringMathUtility.subtract("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-20.0", StringMathUtility.subtract("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-2.99999999999999", StringMathUtility.subtract("-2.99999999999999", "0", 14));
        Assert.assertEquals("-3.0000000000", StringMathUtility.subtract("-2.99999999999999", "0", 10));
        Assert.assertEquals("-3.00000", StringMathUtility.subtract("-2.99999999999999", "0", 5));
        Assert.assertEquals("-3.0", StringMathUtility.subtract("-2.99999999999999", "0", 1));
        Assert.assertEquals("0.0094100578", StringMathUtility.subtract(".0000000578", "-.00941", 10));
        Assert.assertEquals("0.0094101", StringMathUtility.subtract(".0000000578", "-.00941", 7));
        Assert.assertEquals("0.009410", StringMathUtility.subtract(".0000000578", "-.00941", 6));
        Assert.assertEquals("0.009", StringMathUtility.subtract(".0000000578", "-.00941", 3));
        Assert.assertEquals("0.01", StringMathUtility.subtract(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.subtract(".0000000578", "-.00941", 1));
        Assert.assertEquals("20.0094100578", StringMathUtility.subtract("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("20.0094101", StringMathUtility.subtract("18.0000000578", "-2.00941", 7));
        Assert.assertEquals("20.009410", StringMathUtility.subtract("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("20.009", StringMathUtility.subtract("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("20.01", StringMathUtility.subtract("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("20.0", StringMathUtility.subtract("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.subtract("2.99999999999999", "-0", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.subtract("2.99999999999999", "-0", 10));
        Assert.assertEquals("3.00000", StringMathUtility.subtract("2.99999999999999", "-0", 5));
        Assert.assertEquals("3.0", StringMathUtility.subtract("2.99999999999999", "-0", 1));
        Assert.assertEquals("0.0094099422", StringMathUtility.subtract("-.0000000578", "-.00941", 10));
        Assert.assertEquals("0.0094099", StringMathUtility.subtract("-.0000000578", "-.00941", 7));
        Assert.assertEquals("0.009410", StringMathUtility.subtract("-.0000000578", "-.00941", 6));
        Assert.assertEquals("0.009", StringMathUtility.subtract("-.0000000578", "-.00941", 3));
        Assert.assertEquals("0.01", StringMathUtility.subtract("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.subtract("-.0000000578", "-.00941", 1));
        Assert.assertEquals("-15.9905900578", StringMathUtility.subtract("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-15.9905901", StringMathUtility.subtract("-18.0000000578", "-2.00941", 7));
        Assert.assertEquals("-15.991", StringMathUtility.subtract("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-15.99", StringMathUtility.subtract("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-16.0", StringMathUtility.subtract("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-2.99999999999999", StringMathUtility.subtract("-2.99999999999999", "-0", 14));
        Assert.assertEquals("-3.0000000000", StringMathUtility.subtract("-2.99999999999999", "-0", 10));
        Assert.assertEquals("-3.00000", StringMathUtility.subtract("-2.99999999999999", "-0", 5));
        Assert.assertEquals("-3.0", StringMathUtility.subtract("-2.99999999999999", "-0", 1));
        
        //larger cases
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299",
                StringMathUtility.subtract(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299",
                StringMathUtility.subtract(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.subtract(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.subtract(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.subtract(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.subtract(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829",
                StringMathUtility.subtract(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829",
                StringMathUtility.subtract(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.subtract(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.subtract(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.subtract(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.subtract(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829",
                StringMathUtility.subtract(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829",
                StringMathUtility.subtract(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.subtract(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.subtract(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.subtract(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                StringMathUtility.subtract(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299",
                StringMathUtility.subtract(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299",
                StringMathUtility.subtract(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.subtract(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.subtract(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.subtract(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                StringMathUtility.subtract(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.subtract(null, "2"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.subtract("2", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.subtract(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.subtract(null, "2", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.subtract("2", null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.subtract(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.subtract(null, "2", 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.subtract("2", null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.subtract(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.subtract("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.subtract("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.subtract("15s5", "a"));
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#multiply(String, String, int)
     * @see StringMathUtility#multiply(String, String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#multiply(String, String)
     */
    @Test
    public void testMultiply() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.multiply("1", "1"));
        Assert.assertEquals("68", StringMathUtility.multiply("17", "4"));
        Assert.assertEquals("1593", StringMathUtility.multiply("9", "177"));
        Assert.assertEquals("0", StringMathUtility.multiply("0", "88"));
        Assert.assertEquals("0", StringMathUtility.multiply("0549", "0"));
        Assert.assertEquals("-9", StringMathUtility.multiply("9", "-1"));
        
        //negative
        Assert.assertEquals("-1", StringMathUtility.multiply("-1", "1"));
        Assert.assertEquals("-68", StringMathUtility.multiply("-17", "4"));
        Assert.assertEquals("-1593", StringMathUtility.multiply("-9", "177"));
        Assert.assertEquals("0", StringMathUtility.multiply("-0", "88"));
        Assert.assertEquals("0", StringMathUtility.multiply("-0549", "0"));
        Assert.assertEquals("-1", StringMathUtility.multiply("1", "-1"));
        Assert.assertEquals("-68", StringMathUtility.multiply("17", "-4"));
        Assert.assertEquals("-1593", StringMathUtility.multiply("9", "-177"));
        Assert.assertEquals("0", StringMathUtility.multiply("0", "-88"));
        Assert.assertEquals("0", StringMathUtility.multiply("0549", "-0"));
        Assert.assertEquals("1", StringMathUtility.multiply("-1", "-1"));
        Assert.assertEquals("68", StringMathUtility.multiply("-17", "-4"));
        Assert.assertEquals("1593", StringMathUtility.multiply("-9", "-177"));
        Assert.assertEquals("0", StringMathUtility.multiply("-0", "-88"));
        Assert.assertEquals("0", StringMathUtility.multiply("-0549", "-0"));
        
        //decimal
        Assert.assertEquals("0.1", StringMathUtility.multiply("1", "0.1"));
        Assert.assertEquals("0.1", StringMathUtility.multiply("1", ".1"));
        Assert.assertEquals("0.01", StringMathUtility.multiply(".1", ".1"));
        Assert.assertEquals("5.89", StringMathUtility.multiply("3.1", "1.9"));
        Assert.assertEquals("0.31", StringMathUtility.multiply("3.1", "0.1"));
        Assert.assertEquals("62.31", StringMathUtility.multiply("03.1", "20.1"));
        Assert.assertEquals("0.543898", StringMathUtility.multiply(".578", ".941"));
        Assert.assertEquals("569.295898", StringMathUtility.multiply("0.578", "984.941"));
        Assert.assertEquals("0.000000000543898", StringMathUtility.multiply(".0000000578", ".00941"));
        Assert.assertEquals("36.169380116143898", StringMathUtility.multiply("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("-0.1", StringMathUtility.multiply("-1", "0.1"));
        Assert.assertEquals("-0.1", StringMathUtility.multiply("-1", ".1"));
        Assert.assertEquals("-0.01", StringMathUtility.multiply("-.1", ".1"));
        Assert.assertEquals("-5.89", StringMathUtility.multiply("-3.1", "1.9"));
        Assert.assertEquals("-0.31", StringMathUtility.multiply("-3.1", "0.1"));
        Assert.assertEquals("-62.31", StringMathUtility.multiply("-03.1", "20.1"));
        Assert.assertEquals("-0.543898", StringMathUtility.multiply("-.578", ".941"));
        Assert.assertEquals("-569.295898", StringMathUtility.multiply("-0.578", "984.941"));
        Assert.assertEquals("-0.000000000543898", StringMathUtility.multiply("-.0000000578", ".00941"));
        Assert.assertEquals("-36.169380116143898", StringMathUtility.multiply("-18.0000000578", "2.00941"));
        Assert.assertEquals("-0.1", StringMathUtility.multiply("1", "-0.1"));
        Assert.assertEquals("-0.1", StringMathUtility.multiply("1", "-.1"));
        Assert.assertEquals("-0.01", StringMathUtility.multiply(".1", "-.1"));
        Assert.assertEquals("-5.89", StringMathUtility.multiply("3.1", "-1.9"));
        Assert.assertEquals("-0.31", StringMathUtility.multiply("3.1", "-0.1"));
        Assert.assertEquals("-62.31", StringMathUtility.multiply("03.1", "-20.1"));
        Assert.assertEquals("-0.543898", StringMathUtility.multiply(".578", "-.941"));
        Assert.assertEquals("-569.295898", StringMathUtility.multiply("0.578", "-984.941"));
        Assert.assertEquals("-0.000000000543898", StringMathUtility.multiply(".0000000578", "-.00941"));
        Assert.assertEquals("-36.169380116143898", StringMathUtility.multiply("18.0000000578", "-2.00941"));
        Assert.assertEquals("0.1", StringMathUtility.multiply("-1", "-0.1"));
        Assert.assertEquals("0.1", StringMathUtility.multiply("-1", "-.1"));
        Assert.assertEquals("0.01", StringMathUtility.multiply("-.1", "-.1"));
        Assert.assertEquals("5.89", StringMathUtility.multiply("-3.1", "-1.9"));
        Assert.assertEquals("0.31", StringMathUtility.multiply("-3.1", "-0.1"));
        Assert.assertEquals("62.31", StringMathUtility.multiply("-03.1", "-20.1"));
        Assert.assertEquals("0.543898", StringMathUtility.multiply("-.578", "-.941"));
        Assert.assertEquals("569.295898", StringMathUtility.multiply("-0.578", "-984.941"));
        Assert.assertEquals("0.000000000543898", StringMathUtility.multiply("-.0000000578", "-.00941"));
        Assert.assertEquals("36.169380116143898", StringMathUtility.multiply("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.00", StringMathUtility.multiply("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.multiply("1", "1", null));
        
        //precision, negative
        Assert.assertEquals("-1", StringMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1.00", StringMathUtility.multiply("-1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", StringMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("-1.00000000", StringMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("-1.0000000000000000", StringMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("-1", StringMathUtility.multiply("-1", "1", null));
        
        //custom precision
        Assert.assertEquals("0.000000000543898", StringMathUtility.multiply(".0000000578", ".00941", 15));
        Assert.assertEquals("0.0000000005439", StringMathUtility.multiply(".0000000578", ".00941", 13));
        Assert.assertEquals("0.000000000544", StringMathUtility.multiply(".0000000578", ".00941", 12));
        Assert.assertEquals("0.0000000005", StringMathUtility.multiply(".0000000578", ".00941", 10));
        Assert.assertEquals("0.000000001", StringMathUtility.multiply(".0000000578", ".00941", 9));
        Assert.assertEquals("0.000000", StringMathUtility.multiply(".0000000578", ".00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.multiply(".0000000578", ".00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.multiply(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.multiply(".0000000578", ".00941", 1));
        Assert.assertEquals("36.169380116143898", StringMathUtility.multiply("18.0000000578", "2.00941", 15));
        Assert.assertEquals("36.1693801161439", StringMathUtility.multiply("18.0000000578", "2.00941", 13));
        Assert.assertEquals("36.169380116144", StringMathUtility.multiply("18.0000000578", "2.00941", 12));
        Assert.assertEquals("36.1693801161", StringMathUtility.multiply("18.0000000578", "2.00941", 10));
        Assert.assertEquals("36.169380116", StringMathUtility.multiply("18.0000000578", "2.00941", 9));
        Assert.assertEquals("36.169380", StringMathUtility.multiply("18.0000000578", "2.00941", 6));
        Assert.assertEquals("36.169", StringMathUtility.multiply("18.0000000578", "2.00941", 3));
        Assert.assertEquals("36.17", StringMathUtility.multiply("18.0000000578", "2.00941", 2));
        Assert.assertEquals("36.2", StringMathUtility.multiply("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.multiply("2.99999999999999", "1", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.multiply("2.99999999999999", "1", 10));
        Assert.assertEquals("3.00000", StringMathUtility.multiply("2.99999999999999", "1", 5));
        Assert.assertEquals("3.0", StringMathUtility.multiply("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("-0.000000000543898", StringMathUtility.multiply("-.0000000578", ".00941", 15));
        Assert.assertEquals("-0.0000000005439", StringMathUtility.multiply("-.0000000578", ".00941", 13));
        Assert.assertEquals("-0.000000000544", StringMathUtility.multiply("-.0000000578", ".00941", 12));
        Assert.assertEquals("-0.0000000005", StringMathUtility.multiply("-.0000000578", ".00941", 10));
        Assert.assertEquals("-0.000000001", StringMathUtility.multiply("-.0000000578", ".00941", 9));
        Assert.assertEquals("0.000000", StringMathUtility.multiply("-.0000000578", ".00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.multiply("-.0000000578", ".00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.multiply("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.multiply("-.0000000578", ".00941", 1));
        Assert.assertEquals("-36.169380116143898", StringMathUtility.multiply("-18.0000000578", "2.00941", 15));
        Assert.assertEquals("-36.1693801161439", StringMathUtility.multiply("-18.0000000578", "2.00941", 13));
        Assert.assertEquals("-36.169380116144", StringMathUtility.multiply("-18.0000000578", "2.00941", 12));
        Assert.assertEquals("-36.1693801161", StringMathUtility.multiply("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-36.169380116", StringMathUtility.multiply("-18.0000000578", "2.00941", 9));
        Assert.assertEquals("-36.169380", StringMathUtility.multiply("-18.0000000578", "2.00941", 6));
        Assert.assertEquals("-36.169", StringMathUtility.multiply("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-36.17", StringMathUtility.multiply("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-36.2", StringMathUtility.multiply("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-2.99999999999999", StringMathUtility.multiply("-2.99999999999999", "1", 14));
        Assert.assertEquals("-3.0000000000", StringMathUtility.multiply("-2.99999999999999", "1", 10));
        Assert.assertEquals("-3.00000", StringMathUtility.multiply("-2.99999999999999", "1", 5));
        Assert.assertEquals("-3.0", StringMathUtility.multiply("-2.99999999999999", "1", 1));
        Assert.assertEquals("-0.000000000543898", StringMathUtility.multiply(".0000000578", "-.00941", 15));
        Assert.assertEquals("-0.0000000005439", StringMathUtility.multiply(".0000000578", "-.00941", 13));
        Assert.assertEquals("-0.000000000544", StringMathUtility.multiply(".0000000578", "-.00941", 12));
        Assert.assertEquals("-0.0000000005", StringMathUtility.multiply(".0000000578", "-.00941", 10));
        Assert.assertEquals("-0.000000001", StringMathUtility.multiply(".0000000578", "-.00941", 9));
        Assert.assertEquals("0.000000", StringMathUtility.multiply(".0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.multiply(".0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.multiply(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.multiply(".0000000578", "-.00941", 1));
        Assert.assertEquals("-36.169380116143898", StringMathUtility.multiply("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("-36.1693801161439", StringMathUtility.multiply("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("-36.169380116144", StringMathUtility.multiply("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("-36.1693801161", StringMathUtility.multiply("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-36.169380116", StringMathUtility.multiply("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("-36.169380", StringMathUtility.multiply("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("-36.169", StringMathUtility.multiply("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-36.17", StringMathUtility.multiply("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-36.2", StringMathUtility.multiply("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-2.99999999999999", StringMathUtility.multiply("2.99999999999999", "-1", 14));
        Assert.assertEquals("-3.0000000000", StringMathUtility.multiply("2.99999999999999", "-1", 10));
        Assert.assertEquals("-3.00000", StringMathUtility.multiply("2.99999999999999", "-1", 5));
        Assert.assertEquals("-3.0", StringMathUtility.multiply("2.99999999999999", "-1", 1));
        Assert.assertEquals("0.000000000543898", StringMathUtility.multiply("-.0000000578", "-.00941", 15));
        Assert.assertEquals("0.0000000005439", StringMathUtility.multiply("-.0000000578", "-.00941", 13));
        Assert.assertEquals("0.000000000544", StringMathUtility.multiply("-.0000000578", "-.00941", 12));
        Assert.assertEquals("0.0000000005", StringMathUtility.multiply("-.0000000578", "-.00941", 10));
        Assert.assertEquals("0.000000001", StringMathUtility.multiply("-.0000000578", "-.00941", 9));
        Assert.assertEquals("0.000000", StringMathUtility.multiply("-.0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.multiply("-.0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.multiply("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.multiply("-.0000000578", "-.00941", 1));
        Assert.assertEquals("36.169380116143898", StringMathUtility.multiply("-18.0000000578", "-2.00941", 15));
        Assert.assertEquals("36.1693801161439", StringMathUtility.multiply("-18.0000000578", "-2.00941", 13));
        Assert.assertEquals("36.169380116144", StringMathUtility.multiply("-18.0000000578", "-2.00941", 12));
        Assert.assertEquals("36.1693801161", StringMathUtility.multiply("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("36.169380116", StringMathUtility.multiply("-18.0000000578", "-2.00941", 9));
        Assert.assertEquals("36.169380", StringMathUtility.multiply("-18.0000000578", "-2.00941", 6));
        Assert.assertEquals("36.169", StringMathUtility.multiply("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("36.17", StringMathUtility.multiply("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("36.2", StringMathUtility.multiply("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.multiply("-2.99999999999999", "-1", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.multiply("-2.99999999999999", "-1", 10));
        Assert.assertEquals("3.00000", StringMathUtility.multiply("-2.99999999999999", "-1", 5));
        Assert.assertEquals("3.0", StringMathUtility.multiply("-2.99999999999999", "-1", 1));
        
        //larger cases
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                StringMathUtility.multiply(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                StringMathUtility.multiply(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                StringMathUtility.multiply(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                StringMathUtility.multiply(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                StringMathUtility.multiply(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                StringMathUtility.multiply(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                StringMathUtility.multiply(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                StringMathUtility.multiply(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                StringMathUtility.multiply(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.multiply(null, "2"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.multiply("2", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.multiply(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.multiply(null, "2", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.multiply("2", null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.multiply(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.multiply(null, "2", 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.multiply("2", null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.multiply(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.multiply("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.multiply("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.multiply("15s5", "a"));
    }
    
    /**
     * JUnit test of divide.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#divide(String, String, int)
     * @see StringMathUtility#divide(String, String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#divide(String, String)
     */
    @Test
    public void testDivide() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.divide("1", "1"));
        Assert.assertEquals("4.25", StringMathUtility.divide("17", "4"));
        Assert.assertEquals("0.0508474576271186440677966101694915254237288135593220338983050847", StringMathUtility.divide("9", "177"));
        Assert.assertEquals("0", StringMathUtility.divide("0", "88"));
        Assert.assertEquals("-9", StringMathUtility.divide("9", "-1"));
        
        //negative
        Assert.assertEquals("-1", StringMathUtility.divide("-1", "1"));
        Assert.assertEquals("-4.25", StringMathUtility.divide("-17", "4"));
        Assert.assertEquals("-0.0508474576271186440677966101694915254237288135593220338983050847", StringMathUtility.divide("-9", "177"));
        Assert.assertEquals("0", StringMathUtility.divide("-0", "88"));
        Assert.assertEquals("-1", StringMathUtility.divide("1", "-1"));
        Assert.assertEquals("-4.25", StringMathUtility.divide("17", "-4"));
        Assert.assertEquals("-0.0508474576271186440677966101694915254237288135593220338983050847", StringMathUtility.divide("9", "-177"));
        Assert.assertEquals("0", StringMathUtility.divide("0", "-88"));
        Assert.assertEquals("1", StringMathUtility.divide("-1", "-1"));
        Assert.assertEquals("4.25", StringMathUtility.divide("-17", "-4"));
        Assert.assertEquals("0.0508474576271186440677966101694915254237288135593220338983050847", StringMathUtility.divide("-9", "-177"));
        Assert.assertEquals("0", StringMathUtility.divide("-0", "-88"));
        
        //decimal
        Assert.assertEquals("10", StringMathUtility.divide("1", "0.1"));
        Assert.assertEquals("10", StringMathUtility.divide("1", ".1"));
        Assert.assertEquals("1", StringMathUtility.divide(".1", ".1"));
        Assert.assertEquals("1.6315789473684210526315789473684210526315789473684210526315789474", StringMathUtility.divide("3.1", "1.9"));
        Assert.assertEquals("31", StringMathUtility.divide("3.1", "0.1"));
        Assert.assertEquals("0.1542288557213930348258706467661691542288557213930348258706467662", StringMathUtility.divide("03.1", "20.1"));
        Assert.assertEquals("0.6142401700318809776833156216790648246546227417640807651434643996", StringMathUtility.divide(".578", ".941"));
        Assert.assertEquals("0.0005868371811103406193873541663916924973170981815154410264168108", StringMathUtility.divide("0.578", "984.941"));
        Assert.assertEquals("0.0000061424017003188097768331562167906482465462274176408076514346", StringMathUtility.divide(".0000000578", ".00941"));
        Assert.assertEquals("8.9578533289871156210031800379215789709417192111117193604092743641", StringMathUtility.divide("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("-10", StringMathUtility.divide("-1", "0.1"));
        Assert.assertEquals("-10", StringMathUtility.divide("-1", ".1"));
        Assert.assertEquals("-1", StringMathUtility.divide("-.1", ".1"));
        Assert.assertEquals("-1.6315789473684210526315789473684210526315789473684210526315789474", StringMathUtility.divide("-3.1", "1.9"));
        Assert.assertEquals("-31", StringMathUtility.divide("-3.1", "0.1"));
        Assert.assertEquals("-0.1542288557213930348258706467661691542288557213930348258706467662", StringMathUtility.divide("-03.1", "20.1"));
        Assert.assertEquals("-0.6142401700318809776833156216790648246546227417640807651434643996", StringMathUtility.divide("-.578", ".941"));
        Assert.assertEquals("-0.0005868371811103406193873541663916924973170981815154410264168108", StringMathUtility.divide("-0.578", "984.941"));
        Assert.assertEquals("-0.0000061424017003188097768331562167906482465462274176408076514346", StringMathUtility.divide("-.0000000578", ".00941"));
        Assert.assertEquals("-8.9578533289871156210031800379215789709417192111117193604092743641", StringMathUtility.divide("-18.0000000578", "2.00941"));
        Assert.assertEquals("-10", StringMathUtility.divide("1", "-0.1"));
        Assert.assertEquals("-10", StringMathUtility.divide("1", "-.1"));
        Assert.assertEquals("-1", StringMathUtility.divide(".1", "-.1"));
        Assert.assertEquals("-1.6315789473684210526315789473684210526315789473684210526315789474", StringMathUtility.divide("3.1", "-1.9"));
        Assert.assertEquals("-31", StringMathUtility.divide("3.1", "-0.1"));
        Assert.assertEquals("-0.1542288557213930348258706467661691542288557213930348258706467662", StringMathUtility.divide("03.1", "-20.1"));
        Assert.assertEquals("-0.6142401700318809776833156216790648246546227417640807651434643996", StringMathUtility.divide(".578", "-.941"));
        Assert.assertEquals("-0.0005868371811103406193873541663916924973170981815154410264168108", StringMathUtility.divide("0.578", "-984.941"));
        Assert.assertEquals("-0.0000061424017003188097768331562167906482465462274176408076514346", StringMathUtility.divide(".0000000578", "-.00941"));
        Assert.assertEquals("-8.9578533289871156210031800379215789709417192111117193604092743641", StringMathUtility.divide("18.0000000578", "-2.00941"));
        Assert.assertEquals("10", StringMathUtility.divide("-1", "-0.1"));
        Assert.assertEquals("10", StringMathUtility.divide("-1", "-.1"));
        Assert.assertEquals("1", StringMathUtility.divide("-.1", "-.1"));
        Assert.assertEquals("1.6315789473684210526315789473684210526315789473684210526315789474", StringMathUtility.divide("-3.1", "-1.9"));
        Assert.assertEquals("31", StringMathUtility.divide("-3.1", "-0.1"));
        Assert.assertEquals("0.1542288557213930348258706467661691542288557213930348258706467662", StringMathUtility.divide("-03.1", "-20.1"));
        Assert.assertEquals("0.6142401700318809776833156216790648246546227417640807651434643996", StringMathUtility.divide("-.578", "-.941"));
        Assert.assertEquals("0.0005868371811103406193873541663916924973170981815154410264168108", StringMathUtility.divide("-0.578", "-984.941"));
        Assert.assertEquals("0.0000061424017003188097768331562167906482465462274176408076514346", StringMathUtility.divide("-.0000000578", "-.00941"));
        Assert.assertEquals("8.9578533289871156210031800379215789709417192111117193604092743641", StringMathUtility.divide("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.divide("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.divide("1", "1", null));
        
        //precision, negative
        Assert.assertEquals("-1", StringMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", StringMathUtility.divide("-1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", StringMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("-1.00000000", StringMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("-1.0000000000000000", StringMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("-1", StringMathUtility.divide("-1", "1", null));
        
        //custom precision
        Assert.assertEquals("0.000006142401700", StringMathUtility.divide(".0000000578", ".00941", 15));
        Assert.assertEquals("0.0000061424017", StringMathUtility.divide(".0000000578", ".00941", 13));
        Assert.assertEquals("0.000006142402", StringMathUtility.divide(".0000000578", ".00941", 12));
        Assert.assertEquals("0.0000061424", StringMathUtility.divide(".0000000578", ".00941", 10));
        Assert.assertEquals("0.000006142", StringMathUtility.divide(".0000000578", ".00941", 9));
        Assert.assertEquals("0.000006", StringMathUtility.divide(".0000000578", ".00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.divide(".0000000578", ".00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.divide(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.divide(".0000000578", ".00941", 1));
        Assert.assertEquals("8.957853328987116", StringMathUtility.divide("18.0000000578", "2.00941", 15));
        Assert.assertEquals("8.9578533289871", StringMathUtility.divide("18.0000000578", "2.00941", 13));
        Assert.assertEquals("8.957853328987", StringMathUtility.divide("18.0000000578", "2.00941", 12));
        Assert.assertEquals("8.9578533290", StringMathUtility.divide("18.0000000578", "2.00941", 10));
        Assert.assertEquals("8.957853329", StringMathUtility.divide("18.0000000578", "2.00941", 9));
        Assert.assertEquals("8.957853", StringMathUtility.divide("18.0000000578", "2.00941", 6));
        Assert.assertEquals("8.958", StringMathUtility.divide("18.0000000578", "2.00941", 3));
        Assert.assertEquals("8.96", StringMathUtility.divide("18.0000000578", "2.00941", 2));
        Assert.assertEquals("9.0", StringMathUtility.divide("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.divide("2.99999999999999", "1", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.divide("2.99999999999999", "1", 10));
        Assert.assertEquals("3.00000", StringMathUtility.divide("2.99999999999999", "1", 5));
        Assert.assertEquals("3.0", StringMathUtility.divide("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("-0.000006142401700", StringMathUtility.divide("-.0000000578", ".00941", 15));
        Assert.assertEquals("-0.0000061424017", StringMathUtility.divide("-.0000000578", ".00941", 13));
        Assert.assertEquals("-0.000006142402", StringMathUtility.divide("-.0000000578", ".00941", 12));
        Assert.assertEquals("-0.0000061424", StringMathUtility.divide("-.0000000578", ".00941", 10));
        Assert.assertEquals("-0.000006142", StringMathUtility.divide("-.0000000578", ".00941", 9));
        Assert.assertEquals("-0.000006", StringMathUtility.divide("-.0000000578", ".00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.divide("-.0000000578", ".00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.divide("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.divide("-.0000000578", ".00941", 1));
        Assert.assertEquals("-8.957853328987116", StringMathUtility.divide("-18.0000000578", "2.00941", 15));
        Assert.assertEquals("-8.9578533289871", StringMathUtility.divide("-18.0000000578", "2.00941", 13));
        Assert.assertEquals("-8.957853328987", StringMathUtility.divide("-18.0000000578", "2.00941", 12));
        Assert.assertEquals("-8.9578533290", StringMathUtility.divide("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-8.957853329", StringMathUtility.divide("-18.0000000578", "2.00941", 9));
        Assert.assertEquals("-8.957853", StringMathUtility.divide("-18.0000000578", "2.00941", 6));
        Assert.assertEquals("-8.958", StringMathUtility.divide("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-8.96", StringMathUtility.divide("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-9.0", StringMathUtility.divide("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-2.99999999999999", StringMathUtility.divide("-2.99999999999999", "1", 14));
        Assert.assertEquals("-3.0000000000", StringMathUtility.divide("-2.99999999999999", "1", 10));
        Assert.assertEquals("-3.00000", StringMathUtility.divide("-2.99999999999999", "1", 5));
        Assert.assertEquals("-3.0", StringMathUtility.divide("-2.99999999999999", "1", 1));
        Assert.assertEquals("-0.000006142401700", StringMathUtility.divide(".0000000578", "-.00941", 15));
        Assert.assertEquals("-0.0000061424017", StringMathUtility.divide(".0000000578", "-.00941", 13));
        Assert.assertEquals("-0.000006142402", StringMathUtility.divide(".0000000578", "-.00941", 12));
        Assert.assertEquals("-0.0000061424", StringMathUtility.divide(".0000000578", "-.00941", 10));
        Assert.assertEquals("-0.000006142", StringMathUtility.divide(".0000000578", "-.00941", 9));
        Assert.assertEquals("-0.000006", StringMathUtility.divide(".0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.divide(".0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.divide(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.divide(".0000000578", "-.00941", 1));
        Assert.assertEquals("-8.957853328987116", StringMathUtility.divide("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("-8.9578533289871", StringMathUtility.divide("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("-8.957853328987", StringMathUtility.divide("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("-8.9578533290", StringMathUtility.divide("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-8.957853329", StringMathUtility.divide("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("-8.957853", StringMathUtility.divide("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("-8.958", StringMathUtility.divide("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-8.96", StringMathUtility.divide("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-9.0", StringMathUtility.divide("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-2.99999999999999", StringMathUtility.divide("2.99999999999999", "-1", 14));
        Assert.assertEquals("-3.0000000000", StringMathUtility.divide("2.99999999999999", "-1", 10));
        Assert.assertEquals("-3.00000", StringMathUtility.divide("2.99999999999999", "-1", 5));
        Assert.assertEquals("-3.0", StringMathUtility.divide("2.99999999999999", "-1", 1));
        Assert.assertEquals("0.000006142401700", StringMathUtility.divide("-.0000000578", "-.00941", 15));
        Assert.assertEquals("0.0000061424017", StringMathUtility.divide("-.0000000578", "-.00941", 13));
        Assert.assertEquals("0.000006142402", StringMathUtility.divide("-.0000000578", "-.00941", 12));
        Assert.assertEquals("0.0000061424", StringMathUtility.divide("-.0000000578", "-.00941", 10));
        Assert.assertEquals("0.000006142", StringMathUtility.divide("-.0000000578", "-.00941", 9));
        Assert.assertEquals("0.000006", StringMathUtility.divide("-.0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.divide("-.0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.divide("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.divide("-.0000000578", "-.00941", 1));
        Assert.assertEquals("8.957853328987116", StringMathUtility.divide("-18.0000000578", "-2.00941", 15));
        Assert.assertEquals("8.9578533289871", StringMathUtility.divide("-18.0000000578", "-2.00941", 13));
        Assert.assertEquals("8.957853328987", StringMathUtility.divide("-18.0000000578", "-2.00941", 12));
        Assert.assertEquals("8.9578533290", StringMathUtility.divide("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("8.957853329", StringMathUtility.divide("-18.0000000578", "-2.00941", 9));
        Assert.assertEquals("8.957853", StringMathUtility.divide("-18.0000000578", "-2.00941", 6));
        Assert.assertEquals("8.958", StringMathUtility.divide("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("8.96", StringMathUtility.divide("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("9.0", StringMathUtility.divide("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.divide("-2.99999999999999", "-1", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.divide("-2.99999999999999", "-1", 10));
        Assert.assertEquals("3.00000", StringMathUtility.divide("-2.99999999999999", "-1", 5));
        Assert.assertEquals("3.0", StringMathUtility.divide("-2.99999999999999", "-1", 1));
        
        //larger cases
        Assert.assertEquals(
                "0.0000000000026223138268334470936228458885653236991922531664445308",
                StringMathUtility.divide(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "381342610395.1644686525787761778068482368292582851082174091447092024910096499",
                StringMathUtility.divide(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.000000000000000000000000000000089144977163172159799930593788901",
                StringMathUtility.divide(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781",
                StringMathUtility.divide(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "0.0000000000026223138268334470936228458885653236991922531664445308",
                StringMathUtility.divide(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "381342610395.1644686525787761778068482368292582851082174091797929770547804939",
                StringMathUtility.divide(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "-0.0000000000026223138268334470936228458885653236991922531664445308",
                StringMathUtility.divide(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-381342610395.1644686525787761778068482368292582851082174091447092024910096499",
                StringMathUtility.divide(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.000000000000000000000000000000089144977163172159799930593788901",
                StringMathUtility.divide(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781",
                StringMathUtility.divide(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-0.0000000000026223138268334470936228458885653236991922531664445308",
                StringMathUtility.divide(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-381342610395.1644686525787761778068482368292582851082174091797929770547804939",
                StringMathUtility.divide(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-0.0000000000026223138268334470936228458885653236991922531664445308",
                StringMathUtility.divide(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-381342610395.1644686525787761778068482368292582851082174091447092024910096499",
                StringMathUtility.divide(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.000000000000000000000000000000089144977163172159799930593788901",
                StringMathUtility.divide(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781",
                StringMathUtility.divide(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-0.0000000000026223138268334470936228458885653236991922531664445308",
                StringMathUtility.divide(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-381342610395.1644686525787761778068482368292582851082174091797929770547804939",
                StringMathUtility.divide(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "0.0000000000026223138268334470936228458885653236991922531664445308",
                StringMathUtility.divide(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "381342610395.1644686525787761778068482368292582851082174091447092024910096499",
                StringMathUtility.divide(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.000000000000000000000000000000089144977163172159799930593788901",
                StringMathUtility.divide(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781",
                StringMathUtility.divide(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "0.0000000000026223138268334470936228458885653236991922531664445308",
                StringMathUtility.divide(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "381342610395.1644686525787761778068482368292582851082174091797929770547804939",
                StringMathUtility.divide(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //invalid divisor
        TestUtils.assertException(ArithmeticException.class, "Cannot divide by zero", () ->
                StringMathUtility.divide("0549", "0"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.divide(null, "2"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.divide("2", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.divide(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.divide(null, "2", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.divide("2", null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.divide(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.divide(null, "2", 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.divide("2", null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.divide(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.divide("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.divide("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.divide("15s5", "a"));
    }
    
    /**
     * JUnit test of mod.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#mod(String, String, int)
     * @see StringMathUtility#mod(String, String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#mod(String, String)
     */
    @Test
    public void testMod() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.mod("1", "1"));
        Assert.assertEquals("1", StringMathUtility.mod("17", "4"));
        Assert.assertEquals("9", StringMathUtility.mod("9", "177"));
        Assert.assertEquals("0", StringMathUtility.mod("0", "88"));
        Assert.assertEquals("0", StringMathUtility.mod("9", "-1"));
        
        //negative
        Assert.assertEquals("0", StringMathUtility.mod("-1", "1"));
        Assert.assertEquals("-1", StringMathUtility.mod("-17", "4"));
        Assert.assertEquals("-9", StringMathUtility.mod("-9", "177"));
        Assert.assertEquals("0", StringMathUtility.mod("-0", "88"));
        Assert.assertEquals("0", StringMathUtility.mod("1", "-1"));
        Assert.assertEquals("1", StringMathUtility.mod("17", "-4"));
        Assert.assertEquals("9", StringMathUtility.mod("9", "-177"));
        Assert.assertEquals("0", StringMathUtility.mod("0", "-88"));
        Assert.assertEquals("0", StringMathUtility.mod("-1", "-1"));
        Assert.assertEquals("-1", StringMathUtility.mod("-17", "-4"));
        Assert.assertEquals("-9", StringMathUtility.mod("-9", "-177"));
        Assert.assertEquals("0", StringMathUtility.mod("-0", "-88"));
        
        //decimal
        Assert.assertEquals("0", StringMathUtility.mod("1", "0.1"));
        Assert.assertEquals("0", StringMathUtility.mod("1", ".1"));
        Assert.assertEquals("0", StringMathUtility.mod(".1", ".1"));
        Assert.assertEquals("1.2", StringMathUtility.mod("3.1", "1.9"));
        Assert.assertEquals("0", StringMathUtility.mod("3.1", "0.1"));
        Assert.assertEquals("3.1", StringMathUtility.mod("03.1", "20.1"));
        Assert.assertEquals("0.578", StringMathUtility.mod(".578", ".941"));
        Assert.assertEquals("0.578", StringMathUtility.mod("0.578", "984.941"));
        Assert.assertEquals("0.0000000578", StringMathUtility.mod(".0000000578", ".00941"));
        Assert.assertEquals("1.9247200578", StringMathUtility.mod("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("0", StringMathUtility.mod("-1", "0.1"));
        Assert.assertEquals("0", StringMathUtility.mod("-1", ".1"));
        Assert.assertEquals("0", StringMathUtility.mod("-.1", ".1"));
        Assert.assertEquals("-1.2", StringMathUtility.mod("-3.1", "1.9"));
        Assert.assertEquals("0", StringMathUtility.mod("-3.1", "0.1"));
        Assert.assertEquals("-3.1", StringMathUtility.mod("-03.1", "20.1"));
        Assert.assertEquals("-0.578", StringMathUtility.mod("-.578", ".941"));
        Assert.assertEquals("-0.578", StringMathUtility.mod("-0.578", "984.941"));
        Assert.assertEquals("-0.0000000578", StringMathUtility.mod("-.0000000578", ".00941"));
        Assert.assertEquals("-1.9247200578", StringMathUtility.mod("-18.0000000578", "2.00941"));
        Assert.assertEquals("0", StringMathUtility.mod("1", "-0.1"));
        Assert.assertEquals("0", StringMathUtility.mod("1", "-.1"));
        Assert.assertEquals("0", StringMathUtility.mod(".1", "-.1"));
        Assert.assertEquals("1.2", StringMathUtility.mod("3.1", "-1.9"));
        Assert.assertEquals("0", StringMathUtility.mod("3.1", "-0.1"));
        Assert.assertEquals("3.1", StringMathUtility.mod("03.1", "-20.1"));
        Assert.assertEquals("0.578", StringMathUtility.mod(".578", "-.941"));
        Assert.assertEquals("0.578", StringMathUtility.mod("0.578", "-984.941"));
        Assert.assertEquals("0.0000000578", StringMathUtility.mod(".0000000578", "-.00941"));
        Assert.assertEquals("1.9247200578", StringMathUtility.mod("18.0000000578", "-2.00941"));
        Assert.assertEquals("0", StringMathUtility.mod("-1", "-0.1"));
        Assert.assertEquals("0", StringMathUtility.mod("-1", "-.1"));
        Assert.assertEquals("0", StringMathUtility.mod("-.1", "-.1"));
        Assert.assertEquals("-1.2", StringMathUtility.mod("-3.1", "-1.9"));
        Assert.assertEquals("0", StringMathUtility.mod("-3.1", "-0.1"));
        Assert.assertEquals("-3.1", StringMathUtility.mod("-03.1", "-20.1"));
        Assert.assertEquals("-0.578", StringMathUtility.mod("-.578", "-.941"));
        Assert.assertEquals("-0.578", StringMathUtility.mod("-0.578", "-984.941"));
        Assert.assertEquals("-0.0000000578", StringMathUtility.mod("-.0000000578", "-.00941"));
        Assert.assertEquals("-1.9247200578", StringMathUtility.mod("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.mod("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.mod("1", "1", null));
        
        //precision, negative
        Assert.assertEquals("0", StringMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.mod("-1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.mod("-1", "1", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.mod(".0000000578", ".00941", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.mod(".0000000578", ".00941", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.mod(".0000000578", ".00941", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.mod(".0000000578", ".00941", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.mod(".0000000578", ".00941", 9));
        Assert.assertEquals("0.000000", StringMathUtility.mod(".0000000578", ".00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.mod(".0000000578", ".00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.mod(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.mod(".0000000578", ".00941", 1));
        Assert.assertEquals("1.924720057800000", StringMathUtility.mod("18.0000000578", "2.00941", 15));
        Assert.assertEquals("1.9247200578000", StringMathUtility.mod("18.0000000578", "2.00941", 13));
        Assert.assertEquals("1.924720057800", StringMathUtility.mod("18.0000000578", "2.00941", 12));
        Assert.assertEquals("1.9247200578", StringMathUtility.mod("18.0000000578", "2.00941", 10));
        Assert.assertEquals("1.924720058", StringMathUtility.mod("18.0000000578", "2.00941", 9));
        Assert.assertEquals("1.924720", StringMathUtility.mod("18.0000000578", "2.00941", 6));
        Assert.assertEquals("1.925", StringMathUtility.mod("18.0000000578", "2.00941", 3));
        Assert.assertEquals("1.92", StringMathUtility.mod("18.0000000578", "2.00941", 2));
        Assert.assertEquals("1.9", StringMathUtility.mod("18.0000000578", "2.00941", 1));
        Assert.assertEquals("0.99999999999999", StringMathUtility.mod("2.99999999999999", "1", 14));
        Assert.assertEquals("1.0000000000", StringMathUtility.mod("2.99999999999999", "1", 10));
        Assert.assertEquals("1.00000", StringMathUtility.mod("2.99999999999999", "1", 5));
        Assert.assertEquals("1.0", StringMathUtility.mod("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("-0.000000057800000", StringMathUtility.mod("-.0000000578", ".00941", 15));
        Assert.assertEquals("-0.0000000578000", StringMathUtility.mod("-.0000000578", ".00941", 13));
        Assert.assertEquals("-0.000000057800", StringMathUtility.mod("-.0000000578", ".00941", 12));
        Assert.assertEquals("-0.0000000578", StringMathUtility.mod("-.0000000578", ".00941", 10));
        Assert.assertEquals("-0.000000058", StringMathUtility.mod("-.0000000578", ".00941", 9));
        Assert.assertEquals("0.000000", StringMathUtility.mod("-.0000000578", ".00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.mod("-.0000000578", ".00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.mod("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.mod("-.0000000578", ".00941", 1));
        Assert.assertEquals("-1.924720057800000", StringMathUtility.mod("-18.0000000578", "2.00941", 15));
        Assert.assertEquals("-1.9247200578000", StringMathUtility.mod("-18.0000000578", "2.00941", 13));
        Assert.assertEquals("-1.924720057800", StringMathUtility.mod("-18.0000000578", "2.00941", 12));
        Assert.assertEquals("-1.9247200578", StringMathUtility.mod("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-1.924720058", StringMathUtility.mod("-18.0000000578", "2.00941", 9));
        Assert.assertEquals("-1.924720", StringMathUtility.mod("-18.0000000578", "2.00941", 6));
        Assert.assertEquals("-1.925", StringMathUtility.mod("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-1.92", StringMathUtility.mod("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-1.9", StringMathUtility.mod("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-0.99999999999999", StringMathUtility.mod("-2.99999999999999", "1", 14));
        Assert.assertEquals("-1.0000000000", StringMathUtility.mod("-2.99999999999999", "1", 10));
        Assert.assertEquals("-1.00000", StringMathUtility.mod("-2.99999999999999", "1", 5));
        Assert.assertEquals("-1.0", StringMathUtility.mod("-2.99999999999999", "1", 1));
        Assert.assertEquals("0.000000057800000", StringMathUtility.mod(".0000000578", "-.00941", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.mod(".0000000578", "-.00941", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.mod(".0000000578", "-.00941", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.mod(".0000000578", "-.00941", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.mod(".0000000578", "-.00941", 9));
        Assert.assertEquals("0.000000", StringMathUtility.mod(".0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.mod(".0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.mod(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.mod(".0000000578", "-.00941", 1));
        Assert.assertEquals("1.924720057800000", StringMathUtility.mod("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("1.9247200578000", StringMathUtility.mod("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("1.924720057800", StringMathUtility.mod("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("1.9247200578", StringMathUtility.mod("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("1.924720058", StringMathUtility.mod("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("1.924720", StringMathUtility.mod("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("1.925", StringMathUtility.mod("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("1.92", StringMathUtility.mod("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("1.9", StringMathUtility.mod("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("0.99999999999999", StringMathUtility.mod("2.99999999999999", "-1", 14));
        Assert.assertEquals("1.0000000000", StringMathUtility.mod("2.99999999999999", "-1", 10));
        Assert.assertEquals("1.00000", StringMathUtility.mod("2.99999999999999", "-1", 5));
        Assert.assertEquals("1.0", StringMathUtility.mod("2.99999999999999", "-1", 1));
        Assert.assertEquals("-0.000000057800000", StringMathUtility.mod("-.0000000578", "-.00941", 15));
        Assert.assertEquals("-0.0000000578000", StringMathUtility.mod("-.0000000578", "-.00941", 13));
        Assert.assertEquals("-0.000000057800", StringMathUtility.mod("-.0000000578", "-.00941", 12));
        Assert.assertEquals("-0.0000000578", StringMathUtility.mod("-.0000000578", "-.00941", 10));
        Assert.assertEquals("-0.000000058", StringMathUtility.mod("-.0000000578", "-.00941", 9));
        Assert.assertEquals("0.000000", StringMathUtility.mod("-.0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", StringMathUtility.mod("-.0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.mod("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.mod("-.0000000578", "-.00941", 1));
        Assert.assertEquals("-1.924720057800000", StringMathUtility.mod("-18.0000000578", "-2.00941", 15));
        Assert.assertEquals("-1.9247200578000", StringMathUtility.mod("-18.0000000578", "-2.00941", 13));
        Assert.assertEquals("-1.924720057800", StringMathUtility.mod("-18.0000000578", "-2.00941", 12));
        Assert.assertEquals("-1.9247200578", StringMathUtility.mod("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-1.924720058", StringMathUtility.mod("-18.0000000578", "-2.00941", 9));
        Assert.assertEquals("-1.924720", StringMathUtility.mod("-18.0000000578", "-2.00941", 6));
        Assert.assertEquals("-1.925", StringMathUtility.mod("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-1.92", StringMathUtility.mod("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-1.9", StringMathUtility.mod("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-0.99999999999999", StringMathUtility.mod("-2.99999999999999", "-1", 14));
        Assert.assertEquals("-1.0000000000", StringMathUtility.mod("-2.99999999999999", "-1", 10));
        Assert.assertEquals("-1.00000", StringMathUtility.mod("-2.99999999999999", "-1", 5));
        Assert.assertEquals("-1.0", StringMathUtility.mod("-2.99999999999999", "-1", 1));
        
        //larger cases
        Assert.assertEquals(
                "15649826500097987154602319879456339009444871265",
                StringMathUtility.mod(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "2573905877562740580297797195670688273455187889",
                StringMathUtility.mod(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.0000000000000000000000000000000489454940894843663168974515098988",
                StringMathUtility.mod(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.0000000000000000000000000000000095023522339419134067499237792953",
                StringMathUtility.mod(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988",
                StringMathUtility.mod(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566",
                StringMathUtility.mod(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "-15649826500097987154602319879456339009444871265",
                StringMathUtility.mod(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-2573905877562740580297797195670688273455187889",
                StringMathUtility.mod(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.0000000000000000000000000000000489454940894843663168974515098988",
                StringMathUtility.mod(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.0000000000000000000000000000000095023522339419134067499237792953",
                StringMathUtility.mod(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988",
                StringMathUtility.mod(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566",
                StringMathUtility.mod(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "15649826500097987154602319879456339009444871265",
                StringMathUtility.mod(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "2573905877562740580297797195670688273455187889",
                StringMathUtility.mod(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.0000000000000000000000000000000489454940894843663168974515098988",
                StringMathUtility.mod(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.0000000000000000000000000000000095023522339419134067499237792953",
                StringMathUtility.mod(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988",
                StringMathUtility.mod(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566",
                StringMathUtility.mod(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-15649826500097987154602319879456339009444871265",
                StringMathUtility.mod(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-2573905877562740580297797195670688273455187889",
                StringMathUtility.mod(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.0000000000000000000000000000000489454940894843663168974515098988",
                StringMathUtility.mod(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.0000000000000000000000000000000095023522339419134067499237792953",
                StringMathUtility.mod(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988",
                StringMathUtility.mod(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566",
                StringMathUtility.mod(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //invalid divisor
        TestUtils.assertException(ArithmeticException.class, "Cannot divide by zero", () ->
                StringMathUtility.mod("0549", "0"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mod(null, "2"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mod("2", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mod(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mod(null, "2", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mod("2", null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mod(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mod(null, "2", 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mod("2", null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mod(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.mod("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.mod("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.mod("15s5", "a"));
    }
    
    /**
     * JUnit test of power.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#power(String, String, int)
     * @see StringMathUtility#power(String, String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#power(String, String)
     */
    @Test
    public void testPower() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.power("1", "1"));
        Assert.assertEquals("83521", StringMathUtility.power("17", "4"));
        Assert.assertEquals("7960203505214079922596627255169838497787047322828694156142117910690491617566742105220063689523875143398306807872117412260518969904597664896897486680228885639266332044169", StringMathUtility.power("9", "177"));
        Assert.assertEquals("673316599491770128906526915476100771794417410780749.6323339009051411530625125585849849592487451775943659571939043426", StringMathUtility.power("9.841123", "51.184223"));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000002155916908314036", StringMathUtility.power("9", "-51"));
        Assert.assertEquals("0", StringMathUtility.power("0", "88"));
        Assert.assertEquals("1", StringMathUtility.power("0549", "0"));
        
        //negative
        Assert.assertEquals("1", StringMathUtility.power("1", "-1"));
        Assert.assertEquals("0.0000119730367213036242382155386070569078435363561260042384549993", StringMathUtility.power("17", "-4"));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000019403252174826328", StringMathUtility.power("9", "-50"));
        Assert.assertEquals("1", StringMathUtility.power("0549", "-0"));
        
        //decimal
        Assert.assertEquals("1", StringMathUtility.power("1", "0.1"));
        Assert.assertEquals("1", StringMathUtility.power("1", ".1"));
        Assert.assertEquals("0.7943282347242815020659182828363879325889606317554843320923239293", StringMathUtility.power(".1", ".1"));
        Assert.assertEquals("8.5819744708515433237089273766814486911245451273740734442610709486", StringMathUtility.power("3.1", "1.9"));
        Assert.assertEquals("1.1197889288345146071831413740562719532493883819177179451078698313", StringMathUtility.power("3.1", "0.1"));
        Assert.assertEquals("7522635966.6749226381185704632188586848126005617185129642614558334105950269", StringMathUtility.power("03.1", "20.1"));
        Assert.assertEquals("0.5969996767174540961674861214368860564978206903586129549726970645", StringMathUtility.power(".578", ".941"));
        Assert.assertEquals("0.004298576318225759690022273201971798742713621049755334924106211", StringMathUtility.power("0.578", "9.941"));
        Assert.assertEquals("0.8548496553984821559145185197132255021001018761158728967864167369", StringMathUtility.power(".0000000578", ".00941"));
        Assert.assertEquals("332.9332170560748796291677077768639059501997058681864257213118194601", StringMathUtility.power("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("1", StringMathUtility.power("1", "-0.1"));
        Assert.assertEquals("1", StringMathUtility.power("1", "-.1"));
        Assert.assertEquals("1.2589254117941672104239541063958006060936174094669310691079230195", StringMathUtility.power(".1", "-.1"));
        Assert.assertEquals("0.1165233016477122380003268859579887568417677816771818881485816682", StringMathUtility.power("3.1", "-1.9"));
        Assert.assertEquals("0.8930254392145206372225730881042090209286727499868963001312248646", StringMathUtility.power("3.1", "-0.1"));
        Assert.assertEquals("0.0000000001329321270403052091710434166742584104992395066858522505", StringMathUtility.power("03.1", "-20.1"));
        Assert.assertEquals("1.6750427831023373855356029315347448400113294641181765184245490238", StringMathUtility.power(".578", "-.941"));
        Assert.assertEquals("3069254478712442726368134501384516054452292932994473569305301262650456077411768362483242150924003353261119501597964322174177341844445236641962645768645641188185060160572001023801965593053780344728160087751668369537615514465170567341324.7274103625509027509977636938888447303544039462539967897369128548", StringMathUtility.power("0.578", "-984.941"));
        Assert.assertEquals("1.1697963421811956297586846961524296945858030609856244543434384263", StringMathUtility.power(".0000000578", "-.00941"));
        Assert.assertEquals("0.0030036053742020376122100772870281232149079136512395701659532183", StringMathUtility.power("18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.power("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.power("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.power("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.power("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.power("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.power("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.power("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.power("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.power("1", "1", null));
        
        //custom precision
        Assert.assertEquals("0.854849655398482", StringMathUtility.power(".0000000578", ".00941", 15));
        Assert.assertEquals("0.8548496553985", StringMathUtility.power(".0000000578", ".00941", 13));
        Assert.assertEquals("0.854849655398", StringMathUtility.power(".0000000578", ".00941", 12));
        Assert.assertEquals("0.8548496554", StringMathUtility.power(".0000000578", ".00941", 10));
        Assert.assertEquals("0.854849655", StringMathUtility.power(".0000000578", ".00941", 9));
        Assert.assertEquals("0.854850", StringMathUtility.power(".0000000578", ".00941", 6));
        Assert.assertEquals("0.855", StringMathUtility.power(".0000000578", ".00941", 3));
        Assert.assertEquals("0.85", StringMathUtility.power(".0000000578", ".00941", 2));
        Assert.assertEquals("0.9", StringMathUtility.power(".0000000578", ".00941", 1));
        Assert.assertEquals("332.933217056074880", StringMathUtility.power("18.0000000578", "2.00941", 15));
        Assert.assertEquals("332.9332170560749", StringMathUtility.power("18.0000000578", "2.00941", 13));
        Assert.assertEquals("332.933217056075", StringMathUtility.power("18.0000000578", "2.00941", 12));
        Assert.assertEquals("332.9332170561", StringMathUtility.power("18.0000000578", "2.00941", 10));
        Assert.assertEquals("332.933217056", StringMathUtility.power("18.0000000578", "2.00941", 9));
        Assert.assertEquals("332.933217", StringMathUtility.power("18.0000000578", "2.00941", 6));
        Assert.assertEquals("332.933", StringMathUtility.power("18.0000000578", "2.00941", 3));
        Assert.assertEquals("332.93", StringMathUtility.power("18.0000000578", "2.00941", 2));
        Assert.assertEquals("332.9", StringMathUtility.power("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.power("2.99999999999999", "1", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.power("2.99999999999999", "1", 10));
        Assert.assertEquals("3.00000", StringMathUtility.power("2.99999999999999", "1", 5));
        Assert.assertEquals("3.0", StringMathUtility.power("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("1.169796342181196", StringMathUtility.power(".0000000578", "-.00941", 15));
        Assert.assertEquals("1.1697963421812", StringMathUtility.power(".0000000578", "-.00941", 13));
        Assert.assertEquals("1.169796342181", StringMathUtility.power(".0000000578", "-.00941", 12));
        Assert.assertEquals("1.1697963422", StringMathUtility.power(".0000000578", "-.00941", 10));
        Assert.assertEquals("1.169796342", StringMathUtility.power(".0000000578", "-.00941", 9));
        Assert.assertEquals("1.169796", StringMathUtility.power(".0000000578", "-.00941", 6));
        Assert.assertEquals("1.170", StringMathUtility.power(".0000000578", "-.00941", 3));
        Assert.assertEquals("1.17", StringMathUtility.power(".0000000578", "-.00941", 2));
        Assert.assertEquals("1.2", StringMathUtility.power(".0000000578", "-.00941", 1));
        Assert.assertEquals("0.003003605374202", StringMathUtility.power("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("0.0030036053742", StringMathUtility.power("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("0.003003605374", StringMathUtility.power("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("0.0030036054", StringMathUtility.power("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("0.003003605", StringMathUtility.power("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("0.003004", StringMathUtility.power("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("0.003", StringMathUtility.power("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("0.00", StringMathUtility.power("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("0.0", StringMathUtility.power("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("0.33333333333333", StringMathUtility.power("2.99999999999999", "-1", 14));
        Assert.assertEquals("0.3333333333", StringMathUtility.power("2.99999999999999", "-1", 10));
        Assert.assertEquals("0.33333", StringMathUtility.power("2.99999999999999", "-1", 5));
        Assert.assertEquals("0.3", StringMathUtility.power("2.99999999999999", "-1", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.power(null, "2"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.power("2", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.power(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.power(null, "2", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.power("2", null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.power(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.power(null, "2", 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.power("2", null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.power(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.power("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.power("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.power("15s5", "a"));
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#root(String, String, int)
     * @see StringMathUtility#root(String, String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#root(String, String)
     */
    @Test
    public void testRoot() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.root("1", "1"));
        Assert.assertEquals("2.0305431848689307178670594733633386532430700031031400799571665478", StringMathUtility.root("17", "4"));
        Assert.assertEquals("1.0124910679451132345095913456226846565491587770562236112132003255", StringMathUtility.root("9", "177"));
        Assert.assertEquals("0", StringMathUtility.root("0", "88"));
        Assert.assertEquals("786013.743768254270833882303879116136981964490156798758108407658146822",
                StringMathUtility.root("485613128980565211.60898418142984980484918454928718798489728798", "3"));
        Assert.assertEquals("15466433495295412499023611679537033631383377069071860256091919717954555618746195215761497736288772707382375752568911674707134443470866066103504779855486983583751316437950986008868049165095774057300631205677165355804916360902067252274690819222348061110476389209016437182982924353393295210298843002027977692102025727209864091478462962487401528904611880600824686359234237451443768697535106664024704138935506678165219668487717463368314786245112249351123975878934532980032897390671120033970530220117657359293732955044382945381806819082172887283570647280164673608220650603081142737309275105223241985180845148006045131342931929585828584139272435283956277543290281355912251531204849162399808618704370190691160030264806149893642218242519864921893234957568111856037015983761438303.8797086075973767519068093447977901303826693684151996747933700452",
                StringMathUtility.root("0.0000000578", "-.00941"));
        
        //negative
        Assert.assertEquals("1", StringMathUtility.root("1", "-1"));
        Assert.assertEquals("0.492479060505452326504424784829822551365761865581268607366424297", StringMathUtility.root("17", "-4"));
        Assert.assertEquals("0.9570070779813869713074626695181980893729131502075731940006351718", StringMathUtility.root("9", "-50"));
        
        //decimal
        Assert.assertEquals("1", StringMathUtility.root("1", "0.1"));
        Assert.assertEquals("1", StringMathUtility.root("1", ".1"));
        Assert.assertEquals("0.0000000001", StringMathUtility.root(".1", ".1"));
        Assert.assertEquals("1.8138919683222467296576624749558020090228037579642605673476495722", StringMathUtility.root("3.1", "1.9"));
        Assert.assertEquals("81962.8286980801", StringMathUtility.root("3.1", "0.1"));
        Assert.assertEquals("1.0579030163444754474619230745795692523521649663181500343460914296", StringMathUtility.root("03.1", "20.1"));
        Assert.assertEquals("0.5584713404830014300064202482968069916221478150084999563624704728", StringMathUtility.root(".578", ".941"));
        Assert.assertEquals("0.9463493488067071397052452650369088434934874310004880110138953471", StringMathUtility.root("0.578", "9.941"));
        Assert.assertEquals("0", StringMathUtility.root(".0000000578", ".00941"));
        Assert.assertEquals("4.2140244745683787111819132714516190061175605006126904318302040206", StringMathUtility.root("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("1", StringMathUtility.root("1", "-0.1"));
        Assert.assertEquals("1", StringMathUtility.root("1", "-.1"));
        Assert.assertEquals("10000000000", StringMathUtility.root(".1", "-.1"));
        Assert.assertEquals("0.5513007485914095721430511858984092694446184590851318960365024668", StringMathUtility.root("3.1", "-1.9"));
        Assert.assertEquals("0.0000122006526114858702155650677564629168732634669806726488718195", StringMathUtility.root("3.1", "-0.1"));
        Assert.assertEquals("0.9452662338136098017669418987962490812251510876944860421982048497", StringMathUtility.root("03.1", "-20.1"));
        Assert.assertEquals("1.7906021804720302858502912366668021476211023928749588351828935813", StringMathUtility.root(".578", "-.941"));
        Assert.assertEquals("1.0005567175975761298214226757423347928668370568555705134626797141", StringMathUtility.root("0.578", "-984.941"));
        Assert.assertEquals("15466433495295412499023611679537033631383377069071860256091919717954555618746195215761497736288772707382375752568911674707134443470866066103504779855486983583751316437950986008868049165095774057300631205677165355804916360902067252274690819222348061110476389209016437182982924353393295210298843002027977692102025727209864091478462962487401528904611880600824686359234237451443768697535106664024704138935506678165219668487717463368314786245112249351123975878934532980032897390671120033970530220117657359293732955044382945381806819082172887283570647280164673608220650603081142737309275105223241985180845148006045131342931929585828584139272435283956277543290281355912251531204849162399808618704370190691160030264806149893642218242519864921893234957568111856037015983761438303.8797086075973767519068093447977901303826693684151996747933700452", StringMathUtility.root(".0000000578", "-.00941"));
        Assert.assertEquals("0.2373028457796095168943210118868724235991924357822924566320684476", StringMathUtility.root("18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.root("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.root("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.root("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.root("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.root("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.root("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.root("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.root("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.root("1", "1", null));
        
        //custom precision
        Assert.assertEquals("0.000000020328536", StringMathUtility.root(".0000000578", ".941", 15));
        Assert.assertEquals("0.0000000203285", StringMathUtility.root(".0000000578", ".941", 13));
        Assert.assertEquals("0.000000020329", StringMathUtility.root(".0000000578", ".941", 12));
        Assert.assertEquals("0.0000000203", StringMathUtility.root(".0000000578", ".941", 10));
        Assert.assertEquals("0.000000020", StringMathUtility.root(".0000000578", ".941", 9));
        Assert.assertEquals("0.000000", StringMathUtility.root(".0000000578", ".941", 6));
        Assert.assertEquals("0.000", StringMathUtility.root(".0000000578", ".941", 3));
        Assert.assertEquals("0.00", StringMathUtility.root(".0000000578", ".941", 2));
        Assert.assertEquals("0.0", StringMathUtility.root(".0000000578", ".941", 1));
        Assert.assertEquals("4.214024474568379", StringMathUtility.root("18.0000000578", "2.00941", 15));
        Assert.assertEquals("4.2140244745684", StringMathUtility.root("18.0000000578", "2.00941", 13));
        Assert.assertEquals("4.214024474568", StringMathUtility.root("18.0000000578", "2.00941", 12));
        Assert.assertEquals("4.2140244746", StringMathUtility.root("18.0000000578", "2.00941", 10));
        Assert.assertEquals("4.214024475", StringMathUtility.root("18.0000000578", "2.00941", 9));
        Assert.assertEquals("4.214024", StringMathUtility.root("18.0000000578", "2.00941", 6));
        Assert.assertEquals("4.214", StringMathUtility.root("18.0000000578", "2.00941", 3));
        Assert.assertEquals("4.21", StringMathUtility.root("18.0000000578", "2.00941", 2));
        Assert.assertEquals("4.2", StringMathUtility.root("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", StringMathUtility.root("2.99999999999999", "1", 14));
        Assert.assertEquals("3.0000000000", StringMathUtility.root("2.99999999999999", "1", 10));
        Assert.assertEquals("3.00000", StringMathUtility.root("2.99999999999999", "1", 5));
        Assert.assertEquals("3.0", StringMathUtility.root("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("49191934.502173645154754", StringMathUtility.root(".0000000578", "-.941", 15));
        Assert.assertEquals("49191934.5021736451548", StringMathUtility.root(".0000000578", "-.941", 13));
        Assert.assertEquals("49191934.502173645155", StringMathUtility.root(".0000000578", "-.941", 12));
        Assert.assertEquals("49191934.5021736452", StringMathUtility.root(".0000000578", "-.941", 10));
        Assert.assertEquals("49191934.502173645", StringMathUtility.root(".0000000578", "-.941", 9));
        Assert.assertEquals("49191934.502174", StringMathUtility.root(".0000000578", "-.941", 6));
        Assert.assertEquals("49191934.502", StringMathUtility.root(".0000000578", "-.941", 3));
        Assert.assertEquals("49191934.50", StringMathUtility.root(".0000000578", "-.941", 2));
        Assert.assertEquals("49191934.5", StringMathUtility.root(".0000000578", "-.941", 1));
        Assert.assertEquals("0.237302845779610", StringMathUtility.root("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("0.2373028457796", StringMathUtility.root("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("0.237302845780", StringMathUtility.root("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("0.2373028458", StringMathUtility.root("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("0.237302846", StringMathUtility.root("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("0.237303", StringMathUtility.root("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("0.237", StringMathUtility.root("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("0.24", StringMathUtility.root("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("0.2", StringMathUtility.root("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("0.33333333333333", StringMathUtility.root("2.99999999999999", "-1", 14));
        Assert.assertEquals("0.3333333333", StringMathUtility.root("2.99999999999999", "-1", 10));
        Assert.assertEquals("0.33333", StringMathUtility.root("2.99999999999999", "-1", 5));
        Assert.assertEquals("0.3", StringMathUtility.root("2.99999999999999", "-1", 1));
        
        //imaginary numbers
        TestUtils.assertException(ArithmeticException.class, "Illegal root(x) for x < 0: x = -1", () ->
                StringMathUtility.root("-1", "1"));
        TestUtils.assertException(ArithmeticException.class, "Division by zero", () ->
                StringMathUtility.root("0549", "0"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.root(null, "2"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.root("2", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.root(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.root(null, "2", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.root("2", null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.root(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.root(null, "2", 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.root("2", null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.root(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.root("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.root("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.root("15s5", "a"));
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#sqrt(String, int)
     * @see StringMathUtility#sqrt(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#sqrt(String)
     */
    @Test
    public void testSqrt() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.sqrt("1"));
        Assert.assertEquals("4.1231056256176605498214098559740770251471992253736204343986335731", StringMathUtility.sqrt("17"));
        Assert.assertEquals("3", StringMathUtility.sqrt("9"));
        Assert.assertEquals("0", StringMathUtility.sqrt("0"));
        Assert.assertEquals("1408566466962021869.0127349699943764140819920917866950425361447066125981115756352251", StringMathUtility.sqrt("1984059491849872645425452087817945451.185908484125974894141541"));
        
        //decimal
        Assert.assertEquals("0.3162277660168379331998893544432718533719555139325216826857504853", StringMathUtility.sqrt("0.1"));
        Assert.assertEquals("0.3162277660168379331998893544432718533719555139325216826857504853", StringMathUtility.sqrt(".1"));
        Assert.assertEquals("1.7606816861659009145769228176496579286155228680517596833957365273", StringMathUtility.sqrt("3.1"));
        Assert.assertEquals("1.7606816861659009145769228176496579286155228680517596833957365273", StringMathUtility.sqrt("03.1"));
        Assert.assertEquals("0.7602631123499284967791190473686339200498102422679187462521050634", StringMathUtility.sqrt(".578"));
        Assert.assertEquals("0.7602631123499284967791190473686339200498102422679187462521050634", StringMathUtility.sqrt("0.578"));
        Assert.assertEquals("0.0002404163056034261582962870831156486733568442188140811724400356", StringMathUtility.sqrt(".0000000578"));
        Assert.assertEquals("4.2426406939310804663671160857775699474512953036348794594149578547", StringMathUtility.sqrt("18.0000000578"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.sqrt("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.sqrt("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.sqrt("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.sqrt("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.sqrt("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.sqrt("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.sqrt("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.sqrt("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.sqrt("1", null));
        
        //custom precision
        Assert.assertEquals("0.000240416305603", StringMathUtility.sqrt(".0000000578", 15));
        Assert.assertEquals("0.0002404163056", StringMathUtility.sqrt(".0000000578", 13));
        Assert.assertEquals("0.000240416306", StringMathUtility.sqrt(".0000000578", 12));
        Assert.assertEquals("0.0002404163", StringMathUtility.sqrt(".0000000578", 10));
        Assert.assertEquals("0.000240416", StringMathUtility.sqrt(".0000000578", 9));
        Assert.assertEquals("0.000240", StringMathUtility.sqrt(".0000000578", 6));
        Assert.assertEquals("0.000", StringMathUtility.sqrt(".0000000578", 3));
        Assert.assertEquals("0.00", StringMathUtility.sqrt(".0000000578", 2));
        Assert.assertEquals("0.0", StringMathUtility.sqrt(".0000000578", 1));
        Assert.assertEquals("4.242640693931080", StringMathUtility.sqrt("18.0000000578", 15));
        Assert.assertEquals("4.2426406939311", StringMathUtility.sqrt("18.0000000578", 13));
        Assert.assertEquals("4.242640693931", StringMathUtility.sqrt("18.0000000578", 12));
        Assert.assertEquals("4.2426406939", StringMathUtility.sqrt("18.0000000578", 10));
        Assert.assertEquals("4.242640694", StringMathUtility.sqrt("18.0000000578", 9));
        Assert.assertEquals("4.242641", StringMathUtility.sqrt("18.0000000578", 6));
        Assert.assertEquals("4.243", StringMathUtility.sqrt("18.0000000578", 3));
        Assert.assertEquals("4.24", StringMathUtility.sqrt("18.0000000578", 2));
        Assert.assertEquals("4.2", StringMathUtility.sqrt("18.0000000578", 1));
        Assert.assertEquals("1.73205080756887", StringMathUtility.sqrt("2.99999999999999", 14));
        Assert.assertEquals("1.7320508076", StringMathUtility.sqrt("2.99999999999999", 10));
        Assert.assertEquals("1.73205", StringMathUtility.sqrt("2.99999999999999", 5));
        Assert.assertEquals("1.7", StringMathUtility.sqrt("2.99999999999999", 1));
        
        //imaginary numbers
        TestUtils.assertException(ArithmeticException.class, "Illegal sqrt(x) for x < 0: x = -1", () ->
                StringMathUtility.sqrt("-1"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.sqrt(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.sqrt(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.sqrt(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.sqrt("15s5"));
    }
    
    /**
     * JUnit test of log.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#log(String, int, int)
     * @see StringMathUtility#log(String, int, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#log(String, int)
     */
    @Test
    public void testLog() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.log("1", 10));
        Assert.assertEquals("7.7772464838640963795913548138936262173008734442830041081204282002", StringMathUtility.log("59875132", 10));
        Assert.assertEquals("6.4588633989030237852940101777646830166306059006880449171282885624", StringMathUtility.log("59875132", 16));
        Assert.assertEquals("7.7416033471438101343043086923917835687985581359699429124459764835", StringMathUtility.log("8976464100065468", 115));
        Assert.assertEquals("51.4372649403029813971127507109192339983014045530619734442644483616", StringMathUtility.log("897646410054048148429841842848065468.0849817826849832454984", 5));
        
        //decimal
        Assert.assertEquals("-1.2295235577981631081928840810289268477073326768745561420897153278", StringMathUtility.log(".058949", 10));
        Assert.assertEquals("-0.0054883516015224846240309445288773850725933116441204479526233334", StringMathUtility.log(".9874421200001", 10));
        Assert.assertEquals("-8.5876501260611484010681707592423294502392943685268343302579861858", StringMathUtility.log(".0000000000456498", 16));
        Assert.assertEquals("-0.1203288744667847051395786262414959272464250468884981793858640109", StringMathUtility.log(".5649871212025644980798794213200031654", 115));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.log("1", 10, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.log("1.0", 10, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.log("1", 10, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.log("1", 10, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.log("1", 10, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.log("1", 10, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.log("1", 10, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.log("1", 10, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.log("1", 10, null));
        
        //custom precision
        Assert.assertEquals("-7.238072161579471", StringMathUtility.log(".0000000578", 10, 15));
        Assert.assertEquals("-7.2380721615795", StringMathUtility.log(".0000000578", 10, 13));
        Assert.assertEquals("-7.238072161579", StringMathUtility.log(".0000000578", 10, 12));
        Assert.assertEquals("-7.2380721616", StringMathUtility.log(".0000000578", 10, 10));
        Assert.assertEquals("-7.238072162", StringMathUtility.log(".0000000578", 10, 9));
        Assert.assertEquals("-7.238072", StringMathUtility.log(".0000000578", 10, 6));
        Assert.assertEquals("-7.238", StringMathUtility.log(".0000000578", 10, 3));
        Assert.assertEquals("-7.24", StringMathUtility.log(".0000000578", 10, 2));
        Assert.assertEquals("-7.2", StringMathUtility.log(".0000000578", 10, 1));
        Assert.assertEquals("1.255272506497874", StringMathUtility.log("18.0000000578", 10, 15));
        Assert.assertEquals("1.2552725064979", StringMathUtility.log("18.0000000578", 10, 13));
        Assert.assertEquals("1.255272506498", StringMathUtility.log("18.0000000578", 10, 12));
        Assert.assertEquals("1.2552725065", StringMathUtility.log("18.0000000578", 10, 10));
        Assert.assertEquals("1.255272506", StringMathUtility.log("18.0000000578", 10, 9));
        Assert.assertEquals("1.255273", StringMathUtility.log("18.0000000578", 10, 6));
        Assert.assertEquals("1.255", StringMathUtility.log("18.0000000578", 10, 3));
        Assert.assertEquals("1.26", StringMathUtility.log("18.0000000578", 10, 2));
        Assert.assertEquals("1.3", StringMathUtility.log("18.0000000578", 10, 1));
        Assert.assertEquals("0.47712125471966", StringMathUtility.log("2.99999999999999", 10, 14));
        Assert.assertEquals("0.4771212547", StringMathUtility.log("2.99999999999999", 10, 10));
        Assert.assertEquals("0.47712", StringMathUtility.log("2.99999999999999", 10, 5));
        Assert.assertEquals("0.5", StringMathUtility.log("2.99999999999999", 10, 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot handle imaginary numbers", () ->
                StringMathUtility.log("-88", 10));
        
        //invalid base
        TestUtils.assertException(ArithmeticException.class, "Cannot take a log with base: 0", () ->
                StringMathUtility.log("88", 0));
        TestUtils.assertException(ArithmeticException.class, "Cannot take a log with base: 1", () ->
                StringMathUtility.log("88", 1));
        TestUtils.assertException(ArithmeticException.class, "Cannot take a log with base: -1", () ->
                StringMathUtility.log("88", -1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.log(null, 10));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.log(null, 10, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.log(null, 10, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.log("15s5", 10));
    }
    
    /**
     * JUnit test of log10.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#log10(String, int)
     * @see StringMathUtility#log10(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#log10(String)
     */
    @Test
    public void testLog10() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.log10("1"));
        Assert.assertEquals("7.7772464838640963795913548138936262173008734442830041081204282002", StringMathUtility.log10("59875132"));
        Assert.assertEquals("15.9531052983335411068962592236934206525044278008590552005073530174", StringMathUtility.log10("8976464100065468"));
        Assert.assertEquals("35.9531052983565229550666849438660017416167932565920217005105313809", StringMathUtility.log10("897646410054048148429841842848065468.0849817826849832454984"));
        
        //decimal
        Assert.assertEquals("-1.2295235577981631081928840810289268477073326768745561420897153278", StringMathUtility.log10(".058949"));
        Assert.assertEquals("-0.0054883516015224846240309445288773850725933116441204479526233334", StringMathUtility.log10(".9874421200001"));
        Assert.assertEquals("-10.3405611208479002705892249718096440146004590828010092355155979251", StringMathUtility.log10(".0000000000456498"));
        Assert.assertEquals("-0.2479614517458840895185617233623326176436035990178915280974126274", StringMathUtility.log10(".5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.log10("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.log10("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.log10("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.log10("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.log10("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.log10("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.log10("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.log10("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.log10("1", null));
        
        //custom precision
        Assert.assertEquals("-7.238072161579471", StringMathUtility.log10(".0000000578", 15));
        Assert.assertEquals("-7.2380721615795", StringMathUtility.log10(".0000000578", 13));
        Assert.assertEquals("-7.238072161579", StringMathUtility.log10(".0000000578", 12));
        Assert.assertEquals("-7.2380721616", StringMathUtility.log10(".0000000578", 10));
        Assert.assertEquals("-7.238072162", StringMathUtility.log10(".0000000578", 9));
        Assert.assertEquals("-7.238072", StringMathUtility.log10(".0000000578", 6));
        Assert.assertEquals("-7.238", StringMathUtility.log10(".0000000578", 3));
        Assert.assertEquals("-7.24", StringMathUtility.log10(".0000000578", 2));
        Assert.assertEquals("-7.2", StringMathUtility.log10(".0000000578", 1));
        Assert.assertEquals("1.255272506497874", StringMathUtility.log10("18.0000000578", 15));
        Assert.assertEquals("1.2552725064979", StringMathUtility.log10("18.0000000578", 13));
        Assert.assertEquals("1.255272506498", StringMathUtility.log10("18.0000000578", 12));
        Assert.assertEquals("1.2552725065", StringMathUtility.log10("18.0000000578", 10));
        Assert.assertEquals("1.255272506", StringMathUtility.log10("18.0000000578", 9));
        Assert.assertEquals("1.255273", StringMathUtility.log10("18.0000000578", 6));
        Assert.assertEquals("1.255", StringMathUtility.log10("18.0000000578", 3));
        Assert.assertEquals("1.26", StringMathUtility.log10("18.0000000578", 2));
        Assert.assertEquals("1.3", StringMathUtility.log10("18.0000000578", 1));
        Assert.assertEquals("0.47712125471966", StringMathUtility.log10("2.99999999999999", 14));
        Assert.assertEquals("0.4771212547", StringMathUtility.log10("2.99999999999999", 10));
        Assert.assertEquals("0.47712", StringMathUtility.log10("2.99999999999999", 5));
        Assert.assertEquals("0.5", StringMathUtility.log10("2.99999999999999", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot handle imaginary numbers", () ->
                StringMathUtility.log10("-88"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.log10(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.log10(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.log10(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.log10("15s5"));
    }
    
    /**
     * JUnit test of log2.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#log2(String, int)
     * @see StringMathUtility#log2(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#log2(String)
     */
    @Test
    public void testLog2() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.log2("1"));
        Assert.assertEquals("25.8354535956120951411760407110587320665224236027521796685131542495", StringMathUtility.log2("59875132"));
        Assert.assertEquals("52.9950686912306265588542243859600621340148673884330424543045365237", StringMathUtility.log2("8976464100065468"));
        Assert.assertEquals("119.4336305890542175633703859022802534717567830775870122829617446884", StringMathUtility.log2("897646410054048148429841842848065468.0849817826849832454984"));
        
        //decimal
        Assert.assertEquals("-4.0843888499755837217099496089968796326627069362951711314842385558", StringMathUtility.log2(".058949"));
        Assert.assertEquals("-0.0182319093797175914083162419197591209958004061785861436462674006", StringMathUtility.log2(".9874421200001"));
        Assert.assertEquals("-34.3506005042445936042726830369693178009571774741073373210319447433", StringMathUtility.log2(".0000000000456498"));
        Assert.assertEquals("-0.8237101130037093618418817457943766871976077197708825098385726642", StringMathUtility.log2(".5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.log2("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.log2("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.log2("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.log2("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.log2("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.log2("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.log2("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.log2("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.log2("1", null));
        
        //custom precision
        Assert.assertEquals("-24.044355266372945", StringMathUtility.log2(".0000000578", 15));
        Assert.assertEquals("-24.0443552663729", StringMathUtility.log2(".0000000578", 13));
        Assert.assertEquals("-24.044355266373", StringMathUtility.log2(".0000000578", 12));
        Assert.assertEquals("-24.0443552664", StringMathUtility.log2(".0000000578", 10));
        Assert.assertEquals("-24.044355266", StringMathUtility.log2(".0000000578", 9));
        Assert.assertEquals("-24.044355", StringMathUtility.log2(".0000000578", 6));
        Assert.assertEquals("-24.044", StringMathUtility.log2(".0000000578", 3));
        Assert.assertEquals("-24.04", StringMathUtility.log2(".0000000578", 2));
        Assert.assertEquals("-24.0", StringMathUtility.log2(".0000000578", 1));
        Assert.assertEquals("4.169925006074966", StringMathUtility.log2("18.0000000578", 15));
        Assert.assertEquals("4.1699250060750", StringMathUtility.log2("18.0000000578", 13));
        Assert.assertEquals("4.169925006075", StringMathUtility.log2("18.0000000578", 12));
        Assert.assertEquals("4.1699250061", StringMathUtility.log2("18.0000000578", 10));
        Assert.assertEquals("4.169925006", StringMathUtility.log2("18.0000000578", 9));
        Assert.assertEquals("4.169925", StringMathUtility.log2("18.0000000578", 6));
        Assert.assertEquals("4.170", StringMathUtility.log2("18.0000000578", 3));
        Assert.assertEquals("4.17", StringMathUtility.log2("18.0000000578", 2));
        Assert.assertEquals("4.2", StringMathUtility.log2("18.0000000578", 1));
        Assert.assertEquals("1.58496250072115", StringMathUtility.log2("2.99999999999999", 14));
        Assert.assertEquals("1.5849625007", StringMathUtility.log2("2.99999999999999", 10));
        Assert.assertEquals("1.58496", StringMathUtility.log2("2.99999999999999", 5));
        Assert.assertEquals("1.6", StringMathUtility.log2("2.99999999999999", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot handle imaginary numbers", () ->
                StringMathUtility.log2("-88"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.log2(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.log2(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.log2(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.log2("15s5"));
    }
    
    /**
     * JUnit test of ln.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#ln(String, int)
     * @see StringMathUtility#ln(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#ln(String)
     */
    @Test
    public void testLn() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.ln("1"));
        Assert.assertEquals("17.9077718182858251785274312729302818977540155002079702638520349514", StringMathUtility.ln("59875132"));
        Assert.assertEquals("36.7333824469071396648015133798406311377520440118648690819591645191", StringMathUtility.ln("8976464100065468"));
        Assert.assertEquals("82.7850843068409710061680172190752326331853971152562735574815381239", StringMathUtility.ln("897646410054048148429841842848065468.0849817826849832454984"));
        
        //decimal
        Assert.assertEquals("-2.8310826156710533039370274436619723487535459125338690312395922065", StringMathUtility.ln(".058949"));
        Assert.assertEquals("-0.01263739658277566981995953491747700296236180670053491641928599", StringMathUtility.ln(".9874421200001"));
        Assert.assertEquals("-23.810021890058175715064614897181291146313170435974947745688483307", StringMathUtility.ln(".0000000000456498"));
        Assert.assertEquals("-0.5709523424272350877962652126686863544530223365935135039341903949", StringMathUtility.ln(".5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.ln("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.ln("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.ln("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.ln("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.ln("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.ln("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.ln("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.ln("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.ln("1", null));
        
        //custom precision
        Assert.assertEquals("-16.666277061268079", StringMathUtility.ln(".0000000578", 15));
        Assert.assertEquals("-16.6662770612681", StringMathUtility.ln(".0000000578", 13));
        Assert.assertEquals("-16.666277061268", StringMathUtility.ln(".0000000578", 12));
        Assert.assertEquals("-16.6662770613", StringMathUtility.ln(".0000000578", 10));
        Assert.assertEquals("-16.666277061", StringMathUtility.ln(".0000000578", 9));
        Assert.assertEquals("-16.666277", StringMathUtility.ln(".0000000578", 6));
        Assert.assertEquals("-16.666", StringMathUtility.ln(".0000000578", 3));
        Assert.assertEquals("-16.67", StringMathUtility.ln(".0000000578", 2));
        Assert.assertEquals("-16.7", StringMathUtility.ln(".0000000578", 1));
        Assert.assertEquals("2.890371761107276", StringMathUtility.ln("18.0000000578", 15));
        Assert.assertEquals("2.8903717611073", StringMathUtility.ln("18.0000000578", 13));
        Assert.assertEquals("2.890371761107", StringMathUtility.ln("18.0000000578", 12));
        Assert.assertEquals("2.8903717611", StringMathUtility.ln("18.0000000578", 10));
        Assert.assertEquals("2.890371761", StringMathUtility.ln("18.0000000578", 9));
        Assert.assertEquals("2.890372", StringMathUtility.ln("18.0000000578", 6));
        Assert.assertEquals("2.890", StringMathUtility.ln("18.0000000578", 3));
        Assert.assertEquals("2.89", StringMathUtility.ln("18.0000000578", 2));
        Assert.assertEquals("2.9", StringMathUtility.ln("18.0000000578", 1));
        Assert.assertEquals("1.09861228866811", StringMathUtility.ln("2.99999999999999", 14));
        Assert.assertEquals("1.0986122887", StringMathUtility.ln("2.99999999999999", 10));
        Assert.assertEquals("1.09861", StringMathUtility.ln("2.99999999999999", 5));
        Assert.assertEquals("1.1", StringMathUtility.ln("2.99999999999999", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot handle imaginary numbers", () ->
                StringMathUtility.ln("-88"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.ln(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.ln(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.ln(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.ln("15a5"));
    }
    
    /**
     * JUnit test of exp.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#exp(String, int)
     * @see StringMathUtility#exp(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#exp(String)
     */
    @Test
    public void testExp() throws Exception {
        //standard
        Assert.assertEquals("2.7182818284590452353602874713526624977572470936999595749669676277", StringMathUtility.exp("1"));
        Assert.assertEquals("2980.9579870417282747435920994528886737559679391328357022089635303877", StringMathUtility.exp("8"));
        Assert.assertEquals("78962960182680.6951609780226351082242199561951153523306550800205987543078540199", StringMathUtility.exp("32"));
        Assert.assertEquals("0.0000000000000000000000000000000000000060546018954011858845318605", StringMathUtility.exp("-88"));
        Assert.assertEquals("87652482559058384867898537124044922325997153244135452151780861343144658710465992236921743054335794223587689153471025357788596255420511085825460468811868290364245791857017367334570705261411551194318979977100012.8404004491039759569464177641362925271313236687731948056722009908", StringMathUtility.exp("481.108494184282465154519"));
        
        //decimal
        Assert.assertEquals("1.0607211425823960124895609618468854219978420526398202114475258501", StringMathUtility.exp(".058949"));
        Assert.assertEquals("2.6843594140529377897494899599910939339616794738454523774341626372", StringMathUtility.exp(".9874421200001"));
        Assert.assertEquals("1.000000000045649800001041952120035854968629677276036737347425951", StringMathUtility.exp(".0000000000456498"));
        Assert.assertEquals("1.7594251232961364761446178758448439790607929366893508782787876251", StringMathUtility.exp(".5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("2.7182818284590452353602874713526624977572470936999595749669676277", StringMathUtility.exp("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("2.7182818284590452353602874713526624977572470936999595749669676277", StringMathUtility.exp("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("3", StringMathUtility.exp("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("2.71828183", StringMathUtility.exp("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("2.7182818284590452", StringMathUtility.exp("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("2.7182818284590452353602874713526624977572470936999595749669676277", StringMathUtility.exp("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138217852516642742746639193200305992181741359662904357290033429526059563073813232862794349076323382988075319525101901157383418793070215408914993488416750924476146066808226480016847741185374234544243710753907774499206955170276183860626133138458300075204493382656029760673711320070932870912744374704723069697720931014169283681902551510865746377211125238978442505695369677078544996996794686445490598793163688923009879312773617821540", StringMathUtility.exp("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695517027618386062613313845830007520449338265602976067371132007093287091274437470472306969772093101416928368190255151086574637721112523897844250569536967707854499699679468644549059879316368892300987931277361782154000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.exp("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("2.7182818284590452353602874713526624977572470936999595749669676277", StringMathUtility.exp("1", null));
        
        //custom precision
        Assert.assertEquals("1.000000057800002", StringMathUtility.exp(".0000000578", 15));
        Assert.assertEquals("1.0000000578000", StringMathUtility.exp(".0000000578", 13));
        Assert.assertEquals("1.000000057800", StringMathUtility.exp(".0000000578", 12));
        Assert.assertEquals("1.0000000578", StringMathUtility.exp(".0000000578", 10));
        Assert.assertEquals("1.000000058", StringMathUtility.exp(".0000000578", 9));
        Assert.assertEquals("1.000000", StringMathUtility.exp(".0000000578", 6));
        Assert.assertEquals("1.000", StringMathUtility.exp(".0000000578", 3));
        Assert.assertEquals("1.00", StringMathUtility.exp(".0000000578", 2));
        Assert.assertEquals("1.0", StringMathUtility.exp(".0000000578", 1));
        Assert.assertEquals("65659972.932476836956218", StringMathUtility.exp("18.0000000578", 15));
        Assert.assertEquals("65659972.9324768369562", StringMathUtility.exp("18.0000000578", 13));
        Assert.assertEquals("65659972.932476836956", StringMathUtility.exp("18.0000000578", 12));
        Assert.assertEquals("65659972.9324768370", StringMathUtility.exp("18.0000000578", 10));
        Assert.assertEquals("65659972.932476837", StringMathUtility.exp("18.0000000578", 9));
        Assert.assertEquals("65659972.932477", StringMathUtility.exp("18.0000000578", 6));
        Assert.assertEquals("65659972.932", StringMathUtility.exp("18.0000000578", 3));
        Assert.assertEquals("65659972.93", StringMathUtility.exp("18.0000000578", 2));
        Assert.assertEquals("65659972.9", StringMathUtility.exp("18.0000000578", 1));
        Assert.assertEquals("20.08553692318747", StringMathUtility.exp("2.99999999999999", 14));
        Assert.assertEquals("20.0855369232", StringMathUtility.exp("2.99999999999999", 10));
        Assert.assertEquals("20.08554", StringMathUtility.exp("2.99999999999999", 5));
        Assert.assertEquals("20.1", StringMathUtility.exp("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.exp(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.exp(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.exp(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.exp("15a5"));
    }
    
    /**
     * JUnit test of factorial.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#factorial(String, int)
     * @see StringMathUtility#factorial(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#factorial(String)
     */
    @Test
    public void testFactorial() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.factorial("0"));
        Assert.assertEquals("1", StringMathUtility.factorial("1"));
        Assert.assertEquals("40320", StringMathUtility.factorial("8"));
        Assert.assertEquals("263130836933693530167218012160000000", StringMathUtility.factorial("32"));
        Assert.assertEquals("2081929273133112204907576555893846691069279186793568416674890991274093223697761081708113722368678652136297664577472410098584848350934833059606387693625837705666213584623982997692368828785873294218908258441840675093460164876260853652550440164398990642157406675760013482570258729059916140375884629493040944923096494059481593985914985339112226470469399067087769382394244428497624623827281446035790259200538077377257274661089064857807402628953589922710838784071610829094667161604336673778816757610963553678271550256200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.factorial("5987"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.factorial("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.factorial("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.factorial("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.factorial("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.factorial("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.factorial("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.factorial("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.factorial("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.factorial("1", null));
        
        //custom precision
        Assert.assertEquals("1.000000000000000", StringMathUtility.factorial("1", 15));
        Assert.assertEquals("1.0000000000000", StringMathUtility.factorial("1", 13));
        Assert.assertEquals("1.000000000000", StringMathUtility.factorial("1", 12));
        Assert.assertEquals("1.0000000000", StringMathUtility.factorial("1", 10));
        Assert.assertEquals("1.000000000", StringMathUtility.factorial("1", 9));
        Assert.assertEquals("1.000000", StringMathUtility.factorial("1", 6));
        Assert.assertEquals("1.000", StringMathUtility.factorial("1", 3));
        Assert.assertEquals("1.00", StringMathUtility.factorial("1", 2));
        Assert.assertEquals("1.0", StringMathUtility.factorial("1", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot take the factorial of a number less than 0", () ->
                StringMathUtility.factorial("-88"));
        TestUtils.assertException(ArithmeticException.class, "Cannot take the factorial of a number that is not an integer", () ->
                StringMathUtility.factorial("8.058949"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.factorial(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.factorial(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.factorial(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.factorial("15a5"));
    }
    
    /**
     * JUnit test of gamma.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#gamma(String, int)
     * @see StringMathUtility#gamma(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#gamma(String)
     */
    @Test
    public void testGamma() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.gamma("1"));
        Assert.assertEquals("5040", StringMathUtility.gamma("8"));
        Assert.assertEquals("8222838654177922817725562880000000", StringMathUtility.gamma("32"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.gamma("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.gamma("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.gamma("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.gamma("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.gamma("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.gamma("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.gamma("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.gamma("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.gamma("1", null));
        
        //custom precision
        Assert.assertEquals("1.000000000000000", StringMathUtility.gamma("1", 15));
        Assert.assertEquals("1.0000000000000", StringMathUtility.gamma("1", 13));
        Assert.assertEquals("1.000000000000", StringMathUtility.gamma("1", 12));
        Assert.assertEquals("1.0000000000", StringMathUtility.gamma("1", 10));
        Assert.assertEquals("1.000000000", StringMathUtility.gamma("1", 9));
        Assert.assertEquals("1.000000", StringMathUtility.gamma("1", 6));
        Assert.assertEquals("1.000", StringMathUtility.gamma("1", 3));
        Assert.assertEquals("1.00", StringMathUtility.gamma("1", 2));
        Assert.assertEquals("1.0", StringMathUtility.gamma("1", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot take the gamma of a number less than or equal to 0", () ->
                StringMathUtility.gamma("0"));
        TestUtils.assertException(ArithmeticException.class, "Cannot take the gamma of a number less than or equal to 0", () ->
                StringMathUtility.gamma("-88"));
        TestUtils.assertException(ArithmeticException.class, "Cannot take the gamma of a number that is not an integer", () ->
                StringMathUtility.gamma("0.058949"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.gamma(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.gamma(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.gamma(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.gamma("15a5"));
    }
    
    /**
     * JUnit test of reciprocal.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#reciprocal(String, int)
     * @see StringMathUtility#reciprocal(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#reciprocal(String)
     */
    @Test
    public void testReciprocal() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.reciprocal("1"));
        Assert.assertEquals("0.0000000167014245580285317784351606940924990361607887561734310665", StringMathUtility.reciprocal("59875132"));
        Assert.assertEquals("0.0000000000000001114024396301776225097687998785101202832809969624", StringMathUtility.reciprocal("8976464100065468"));
        Assert.assertEquals("-0.0113636363636363636363636363636363636363636363636363636363636364", StringMathUtility.reciprocal("-88"));
        Assert.assertEquals("0.000065875936319237801749576983044329762896146385608832657533651", StringMathUtility.reciprocal("15180.049891874845498498428248984"));
        Assert.assertEquals("2004334379.2886651634556187315535266092583612792306845887817197112665553011", StringMathUtility.reciprocal("0.00000000049891874845498498428248984"));
        
        //decimal
        Assert.assertEquals("16.9638161800878725678128551799012705898318885816553291828529746052", StringMathUtility.reciprocal(".058949"));
        Assert.assertEquals("1.012717585917743440234662286490246672656976568239497726047149022", StringMathUtility.reciprocal(".9874421200001"));
        Assert.assertEquals("21905901011.6145087163580125214130182388531822702399572396812253284789856692", StringMathUtility.reciprocal(".0000000000456498"));
        Assert.assertEquals("1.7699518492944029285618439558735248732689751542591527095301915305", StringMathUtility.reciprocal(".5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.reciprocal("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.reciprocal("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.reciprocal("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.reciprocal("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.reciprocal("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.reciprocal("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.reciprocal("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.reciprocal("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.reciprocal("1", null));
        
        //custom precision
        Assert.assertEquals("17301038.062283737024221", StringMathUtility.reciprocal(".0000000578", 15));
        Assert.assertEquals("17301038.0622837370242", StringMathUtility.reciprocal(".0000000578", 13));
        Assert.assertEquals("17301038.062283737024", StringMathUtility.reciprocal(".0000000578", 12));
        Assert.assertEquals("17301038.0622837370", StringMathUtility.reciprocal(".0000000578", 10));
        Assert.assertEquals("17301038.062283737", StringMathUtility.reciprocal(".0000000578", 9));
        Assert.assertEquals("17301038.062284", StringMathUtility.reciprocal(".0000000578", 6));
        Assert.assertEquals("17301038.062", StringMathUtility.reciprocal(".0000000578", 3));
        Assert.assertEquals("17301038.06", StringMathUtility.reciprocal(".0000000578", 2));
        Assert.assertEquals("17301038.1", StringMathUtility.reciprocal(".0000000578", 1));
        Assert.assertEquals("0.055555555377160", StringMathUtility.reciprocal("18.0000000578", 15));
        Assert.assertEquals("0.0555555553772", StringMathUtility.reciprocal("18.0000000578", 13));
        Assert.assertEquals("0.055555555377", StringMathUtility.reciprocal("18.0000000578", 12));
        Assert.assertEquals("0.0555555554", StringMathUtility.reciprocal("18.0000000578", 10));
        Assert.assertEquals("0.055555555", StringMathUtility.reciprocal("18.0000000578", 9));
        Assert.assertEquals("0.055556", StringMathUtility.reciprocal("18.0000000578", 6));
        Assert.assertEquals("0.056", StringMathUtility.reciprocal("18.0000000578", 3));
        Assert.assertEquals("0.06", StringMathUtility.reciprocal("18.0000000578", 2));
        Assert.assertEquals("0.1", StringMathUtility.reciprocal("18.0000000578", 1));
        Assert.assertEquals("0.33333333333333", StringMathUtility.reciprocal("2.99999999999999", 14));
        Assert.assertEquals("0.3333333333", StringMathUtility.reciprocal("2.99999999999999", 10));
        Assert.assertEquals("0.33333", StringMathUtility.reciprocal("2.99999999999999", 5));
        Assert.assertEquals("0.3", StringMathUtility.reciprocal("2.99999999999999", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Cannot divide by zero", () ->
                StringMathUtility.reciprocal("0"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.reciprocal(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.reciprocal(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.reciprocal(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.reciprocal("15a5"));
    }
    
    /**
     * JUnit test of sin.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#sin(String, int)
     * @see StringMathUtility#sin(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#sin(String)
     */
    @Test
    public void testSin() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.sin("0"));
        Assert.assertEquals("0.84147098480789650665250232163029899962256306079837106567275171", StringMathUtility.sin("1"));
        Assert.assertEquals("0.9887268693155572100594417202811589868635848078878924573178952055", StringMathUtility.sin("59875132"));
        Assert.assertEquals("0.2383372052864161529120185202768111857587228700438293794092371984", StringMathUtility.sin("8976464100065468"));
        Assert.assertEquals("-0.84147098480789650665250232163029899962256306079837106567275171", StringMathUtility.sin("-1"));
        Assert.assertEquals("-0.9887268693155572100594417202811589868635848078878924573178952055", StringMathUtility.sin("-59875132"));
        Assert.assertEquals("-0.2383372052864161529120185202768111857587228700438293794092371984", StringMathUtility.sin("-8976464100065468"));
        
        //decimal
        Assert.assertEquals("0.0589148647869660758248794842111068958178113890100723079283948464", StringMathUtility.sin(".058949"));
        Assert.assertEquals("0.8346197623573961989965635400607378987462777110968244036415045414", StringMathUtility.sin(".9874421200001"));
        Assert.assertEquals("0.0000000000456497999999999999999841450313705036680000016520118176", StringMathUtility.sin(".0000000000456498"));
        Assert.assertEquals("0.5354049386812795177902675880563185927218239542553910821939165109", StringMathUtility.sin(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-0.0589148647869660758248794842111068958178113890100723079283948464", StringMathUtility.sin("-.058949"));
        Assert.assertEquals("-0.8346197623573961989965635400607378987462777110968244036415045414", StringMathUtility.sin("-.9874421200001"));
        Assert.assertEquals("-0.0000000000456497999999999999999841450313705036680000016520118176", StringMathUtility.sin("-.0000000000456498"));
        Assert.assertEquals("-0.5354049386812795177902675880563185927218239542553910821939165109", StringMathUtility.sin("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.sin("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.sin("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.sin("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.sin("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.sin("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.sin("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.sin("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.sin("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.sin("0", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.sin(".0000000578", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.sin(".0000000578", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.sin(".0000000578", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.sin(".0000000578", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.sin(".0000000578", 9));
        Assert.assertEquals("0.000000", StringMathUtility.sin(".0000000578", 6));
        Assert.assertEquals("0.000", StringMathUtility.sin(".0000000578", 3));
        Assert.assertEquals("0.00", StringMathUtility.sin(".0000000578", 2));
        Assert.assertEquals("0.0", StringMathUtility.sin(".0000000578", 1));
        Assert.assertEquals("-0.750987208605369", StringMathUtility.sin("18.0000000578", 15));
        Assert.assertEquals("-0.7509872086054", StringMathUtility.sin("18.0000000578", 13));
        Assert.assertEquals("-0.750987208605", StringMathUtility.sin("18.0000000578", 12));
        Assert.assertEquals("-0.7509872086", StringMathUtility.sin("18.0000000578", 10));
        Assert.assertEquals("-0.750987209", StringMathUtility.sin("18.0000000578", 9));
        Assert.assertEquals("-0.750987", StringMathUtility.sin("18.0000000578", 6));
        Assert.assertEquals("-0.751", StringMathUtility.sin("18.0000000578", 3));
        Assert.assertEquals("-0.75", StringMathUtility.sin("18.0000000578", 2));
        Assert.assertEquals("-0.8", StringMathUtility.sin("18.0000000578", 1));
        Assert.assertEquals("0.14112000805988", StringMathUtility.sin("2.99999999999999", 14));
        Assert.assertEquals("0.1411200081", StringMathUtility.sin("2.99999999999999", 10));
        Assert.assertEquals("0.14112", StringMathUtility.sin("2.99999999999999", 5));
        Assert.assertEquals("0.1", StringMathUtility.sin("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.sin(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.sin(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.sin(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.sin("15a5"));
    }
    
    /**
     * JUnit test of asin.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#asin(String, int)
     * @see StringMathUtility#asin(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#asin(String)
     */
    @Test
    public void testAsin() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.asin("0"));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.asin("1"));
        Assert.assertEquals("-1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.asin("-1"));
        
        //decimal
        Assert.assertEquals("0.0589831946432107470709666338410392420549923625915029627094655333", StringMathUtility.asin(".058949"));
        Assert.assertEquals("1.4121504837013315733856448346542194274921801471177022446621884583", StringMathUtility.asin(".9874421200001"));
        Assert.assertEquals("0.0000000000456498000000000000000158549686294963320000148681063587", StringMathUtility.asin(".0000000000456498"));
        Assert.assertEquals("0.6004176447202814336032066140794371275975021614740801114811587924", StringMathUtility.asin(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-0.0589831946432107470709666338410392420549923625915029627094655333", StringMathUtility.asin("-.058949"));
        Assert.assertEquals("-1.4121504837013315733856448346542194274921801471177022446621884583", StringMathUtility.asin("-.9874421200001"));
        Assert.assertEquals("-0.0000000000456498000000000000000158549686294963320000148681063587", StringMathUtility.asin("-.0000000000456498"));
        Assert.assertEquals("-0.6004176447202814336032066140794371275975021614740801114811587924", StringMathUtility.asin("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.asin("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.asin("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.asin("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.asin("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.asin("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.asin("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.asin("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.asin("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.asin("0", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.asin(".0000000578", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.asin(".0000000578", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.asin(".0000000578", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.asin(".0000000578", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.asin(".0000000578", 9));
        Assert.assertEquals("0.000000", StringMathUtility.asin(".0000000578", 6));
        Assert.assertEquals("0.000", StringMathUtility.asin(".0000000578", 3));
        Assert.assertEquals("0.00", StringMathUtility.asin(".0000000578", 2));
        Assert.assertEquals("0.0", StringMathUtility.asin(".0000000578", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = 59875132", () ->
                StringMathUtility.asin("59875132"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = 8976464100065468", () ->
                StringMathUtility.asin("8976464100065468"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = -59875132", () ->
                StringMathUtility.asin("-59875132"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = -8976464100065468", () ->
                StringMathUtility.asin("-8976464100065468"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = 18.0000000578", () ->
                StringMathUtility.asin("18.0000000578"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for asin where input < -1 or input > 1; input = 2.99999999999999", () ->
                StringMathUtility.asin("2.99999999999999"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.asin(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.asin(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.asin(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.asin("15a5"));
    }
    
    /**
     * JUnit test of sinh.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#sinh(String, int)
     * @see StringMathUtility#sinh(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#sinh(String)
     */
    @Test
    public void testSinh() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.sinh("0"));
        Assert.assertEquals("1.175201193643801456882381850595600815155717981334095870229565413", StringMathUtility.sinh("1"));
        Assert.assertEquals("1490.478825789550186115876639031881446447474314116350990944801492906", StringMathUtility.sinh("8"));
        Assert.assertEquals("107321789892958.0323121488807633009971120612543453068258577876221075511591878698", StringMathUtility.sinh("33"));
        Assert.assertEquals("-1.175201193643801456882381850595600815155717981334095870229565413", StringMathUtility.sinh("-1"));
        Assert.assertEquals("-1490.478825789550186115876639031881446447474314116350990944801492906", StringMathUtility.sinh("-8"));
        Assert.assertEquals("-107321789892958.0323121488807633009971120612543453068258577876221075511591878698", StringMathUtility.sinh("-33"));
        
        //decimal
        Assert.assertEquals("0.0589831470770291255041457997953890764607511701723486081538511883", StringMathUtility.sinh(".058949"));
        Assert.assertEquals("1.1559155289203475265453560615865545756569057240305867132546368664", StringMathUtility.sinh(".9874421200001"));
        Assert.assertEquals("0.0000000000456498000000000000000158549686294963320000016520118176", StringMathUtility.sinh(".0000000000456498"));
        Assert.assertEquals("0.5955288283481301135426784576349972391683072352752568991925517737", StringMathUtility.sinh(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-0.0589831470770291255041457997953890764607511701723486081538511883", StringMathUtility.sinh("-.058949"));
        Assert.assertEquals("-1.1559155289203475265453560615865545756569057240305867132546368664", StringMathUtility.sinh("-.9874421200001"));
        Assert.assertEquals("-0.0000000000456498000000000000000158549686294963320000016520118176", StringMathUtility.sinh("-.0000000000456498"));
        Assert.assertEquals("-0.5955288283481301135426784576349972391683072352752568991925517737", StringMathUtility.sinh("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.sinh("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.sinh("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.sinh("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.sinh("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.sinh("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.sinh("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.sinh("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.sinh("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.sinh("0", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.sinh(".0000000578", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.sinh(".0000000578", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.sinh(".0000000578", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.sinh(".0000000578", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.sinh(".0000000578", 9));
        Assert.assertEquals("0.000000", StringMathUtility.sinh(".0000000578", 6));
        Assert.assertEquals("0.000", StringMathUtility.sinh(".0000000578", 3));
        Assert.assertEquals("0.00", StringMathUtility.sinh(".0000000578", 2));
        Assert.assertEquals("0.0", StringMathUtility.sinh(".0000000578", 1));
        Assert.assertEquals("32829986.466238410863119", StringMathUtility.sinh("18.0000000578", 15));
        Assert.assertEquals("32829986.4662384108631", StringMathUtility.sinh("18.0000000578", 13));
        Assert.assertEquals("32829986.466238410863", StringMathUtility.sinh("18.0000000578", 12));
        Assert.assertEquals("32829986.4662384109", StringMathUtility.sinh("18.0000000578", 10));
        Assert.assertEquals("32829986.466238411", StringMathUtility.sinh("18.0000000578", 9));
        Assert.assertEquals("32829986.466238", StringMathUtility.sinh("18.0000000578", 6));
        Assert.assertEquals("32829986.466", StringMathUtility.sinh("18.0000000578", 3));
        Assert.assertEquals("32829986.47", StringMathUtility.sinh("18.0000000578", 2));
        Assert.assertEquals("32829986.5", StringMathUtility.sinh("18.0000000578", 1));
        Assert.assertEquals("10.01787492740980", StringMathUtility.sinh("2.99999999999999", 14));
        Assert.assertEquals("10.0178749274", StringMathUtility.sinh("2.99999999999999", 10));
        Assert.assertEquals("10.01787", StringMathUtility.sinh("2.99999999999999", 5));
        Assert.assertEquals("10.0", StringMathUtility.sinh("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.sinh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.sinh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.sinh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.sinh("15a5"));
    }
    
    /**
     * JUnit test of asinh.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#asinh(String, int)
     * @see StringMathUtility#asinh(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#asinh(String)
     */
    @Test
    public void testAsinh() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.asinh("0"));
        Assert.assertEquals("0.8813735870195430252326093249797923090281603282616354107532956087", StringMathUtility.asinh("1"));
        Assert.assertEquals("18.6009189988457705576790589612680856850210353465978847311035447278", StringMathUtility.asinh("59875132"));
        Assert.assertEquals("37.4265296274670849742187455012988108084534330350675490218116388292", StringMathUtility.asinh("8976464100065468"));
        Assert.assertEquals("-0.8813735870195430252326093249797923090281603282616354107532956087", StringMathUtility.asinh("-1"));
        Assert.assertEquals("-18.6009189988457705576790589612680856850210353465978847311035447278", StringMathUtility.asinh("-59875132"));
        Assert.assertEquals("-37.4265296274670849742187455012988108084534330350675490218116388292", StringMathUtility.asinh("-8976464100065468"));
        
        //decimal
        Assert.assertEquals("0.058914912133267958365901008212414384935023065357773423545078109", StringMathUtility.asinh(".058949"));
        Assert.assertEquals("0.8724658890992712179094633459986864838961800933695003534977581999", StringMathUtility.asinh(".9874421200001"));
        Assert.assertEquals("0.0000000000456497999999999999999841450313705036680000148681063587", StringMathUtility.asinh(".0000000000456498"));
        Assert.assertEquals("0.5385707378000174054224768157374569685354018007944244697415971121", StringMathUtility.asinh(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-0.058914912133267958365901008212414384935023065357773423545078109", StringMathUtility.asinh("-.058949"));
        Assert.assertEquals("-0.8724658890992712179094633459986864838961800933695003534977581999", StringMathUtility.asinh("-.9874421200001"));
        Assert.assertEquals("-0.0000000000456497999999999999999841450313705036680000148681063587", StringMathUtility.asinh("-.0000000000456498"));
        Assert.assertEquals("-0.5385707378000174054224768157374569685354018007944244697415971121", StringMathUtility.asinh("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.asinh("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.asinh("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.asinh("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.asinh("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.asinh("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.asinh("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.asinh("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.asinh("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.asinh("0", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.asinh(".0000000578", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.asinh(".0000000578", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.asinh(".0000000578", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.asinh(".0000000578", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.asinh(".0000000578", 9));
        Assert.assertEquals("0.000000", StringMathUtility.asinh(".0000000578", 6));
        Assert.assertEquals("0.000", StringMathUtility.asinh(".0000000578", 3));
        Assert.assertEquals("0.00", StringMathUtility.asinh(".0000000578", 2));
        Assert.assertEquals("0.0", StringMathUtility.asinh(".0000000578", 1));
        Assert.assertEquals("3.584289655067495", StringMathUtility.asinh("18.0000000578", 15));
        Assert.assertEquals("3.5842896550675", StringMathUtility.asinh("18.0000000578", 13));
        Assert.assertEquals("3.584289655067", StringMathUtility.asinh("18.0000000578", 12));
        Assert.assertEquals("3.5842896551", StringMathUtility.asinh("18.0000000578", 10));
        Assert.assertEquals("3.584289655", StringMathUtility.asinh("18.0000000578", 9));
        Assert.assertEquals("3.584290", StringMathUtility.asinh("18.0000000578", 6));
        Assert.assertEquals("3.584", StringMathUtility.asinh("18.0000000578", 3));
        Assert.assertEquals("3.58", StringMathUtility.asinh("18.0000000578", 2));
        Assert.assertEquals("3.6", StringMathUtility.asinh("18.0000000578", 1));
        Assert.assertEquals("1.81844645923206", StringMathUtility.asinh("2.99999999999999", 14));
        Assert.assertEquals("1.8184464592", StringMathUtility.asinh("2.99999999999999", 10));
        Assert.assertEquals("1.81845", StringMathUtility.asinh("2.99999999999999", 5));
        Assert.assertEquals("1.8", StringMathUtility.asinh("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.asinh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.asinh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.asinh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.asinh("15a5"));
    }
    
    /**
     * JUnit test of cos.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#cos(String, int)
     * @see StringMathUtility#cos(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#cos(String)
     */
    @Test
    public void testCos() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.cos("0"));
        Assert.assertEquals("0.5403023058681397174009366074429766037323104206179222276700972554", StringMathUtility.cos("1"));
        Assert.assertEquals("0.149730350608876403296990024816258956415716863988028005537882937", StringMathUtility.cos("59875132"));
        Assert.assertEquals("0.9711824630707973838326749665224886322531798596915813909431857908", StringMathUtility.cos("8976464100065468"));
        Assert.assertEquals("0.5403023058681397174009366074429766037323104206179222276700972554", StringMathUtility.cos("-1"));
        Assert.assertEquals("0.149730350608876403296990024816258956415716863988028005537882937", StringMathUtility.cos("-59875132"));
        Assert.assertEquals("0.9711824630707973838326749665224886322531798596915813909431857908", StringMathUtility.cos("-8976464100065468"));
        
        //decimal
        Assert.assertEquals("0.9982630107878051116510740873288762374519730576301470929098243326", StringMathUtility.cos(".058949"));
        Assert.assertEquals("0.5508265174104125270456038278746384315487855991166391417084306408", StringMathUtility.cos(".9874421200001"));
        Assert.assertEquals("0.9999999999999999999989580478799800000000001809440367356954141334", StringMathUtility.cos(".0000000000456498"));
        Assert.assertEquals("0.8445954958651480516250212993590999182184375833356161421610919771", StringMathUtility.cos(".5649871212025644980798794213200031654"));
        Assert.assertEquals("0.9982630107878051116510740873288762374519730576301470929098243326", StringMathUtility.cos("-.058949"));
        Assert.assertEquals("0.5508265174104125270456038278746384315487855991166391417084306408", StringMathUtility.cos("-.9874421200001"));
        Assert.assertEquals("0.9999999999999999999989580478799800000000001809440367356954141334", StringMathUtility.cos("-.0000000000456498"));
        Assert.assertEquals("0.8445954958651480516250212993590999182184375833356161421610919771", StringMathUtility.cos("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.cos("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.cos("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.cos("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.cos("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.cos("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.cos("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.cos("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.cos("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.cos("0", null));
        
        //custom precision
        Assert.assertEquals("0.999999999999998", StringMathUtility.cos(".0000000578", 15));
        Assert.assertEquals("1.0000000000000", StringMathUtility.cos(".0000000578", 13));
        Assert.assertEquals("1.000000000000", StringMathUtility.cos(".0000000578", 12));
        Assert.assertEquals("1.0000000000", StringMathUtility.cos(".0000000578", 10));
        Assert.assertEquals("1.000000000", StringMathUtility.cos(".0000000578", 9));
        Assert.assertEquals("1.000000", StringMathUtility.cos(".0000000578", 6));
        Assert.assertEquals("1.000", StringMathUtility.cos(".0000000578", 3));
        Assert.assertEquals("1.00", StringMathUtility.cos(".0000000578", 2));
        Assert.assertEquals("1.0", StringMathUtility.cos(".0000000578", 1));
        Assert.assertEquals("0.660316751651142", StringMathUtility.cos("18.0000000578", 15));
        Assert.assertEquals("0.6603167516511", StringMathUtility.cos("18.0000000578", 13));
        Assert.assertEquals("0.660316751651", StringMathUtility.cos("18.0000000578", 12));
        Assert.assertEquals("0.6603167517", StringMathUtility.cos("18.0000000578", 10));
        Assert.assertEquals("0.660316752", StringMathUtility.cos("18.0000000578", 9));
        Assert.assertEquals("0.660317", StringMathUtility.cos("18.0000000578", 6));
        Assert.assertEquals("0.660", StringMathUtility.cos("18.0000000578", 3));
        Assert.assertEquals("0.66", StringMathUtility.cos("18.0000000578", 2));
        Assert.assertEquals("0.7", StringMathUtility.cos("18.0000000578", 1));
        Assert.assertEquals("-0.98999249660044", StringMathUtility.cos("2.99999999999999", 14));
        Assert.assertEquals("-0.9899924966", StringMathUtility.cos("2.99999999999999", 10));
        Assert.assertEquals("-0.98999", StringMathUtility.cos("2.99999999999999", 5));
        Assert.assertEquals("-1.0", StringMathUtility.cos("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.cos(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.cos(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.cos(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.cos("15a5"));
    }
    
    /**
     * JUnit test of acos.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#acos(String, int)
     * @see StringMathUtility#acos(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#acos(String)
     */
    @Test
    public void testAcos() throws Exception {
        //standard
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acos("0"));
        Assert.assertEquals("0", StringMathUtility.acos("1"));
        Assert.assertEquals("3.1415926535897932384626433832795028841971693993751058209749445923", StringMathUtility.acos("-1"));
        
        //decimal
        Assert.assertEquals("1.5118131321516858721603550577987122000435923370960499477780067629", StringMathUtility.acos(".058949"));
        Assert.assertEquals("0.1586458430935650458456768569855320146064045525698506658252838379", StringMathUtility.acos(".9874421200001"));
        Assert.assertEquals("1.5707963267492468192313216916397355871299552033555528956193659374", StringMathUtility.acos(".0000000000456498"));
        Assert.assertEquals("0.9703786820746151856281150775603143145010825382134727990063135038", StringMathUtility.acos(".5649871212025644980798794213200031654"));
        Assert.assertEquals("1.6297795214381073663022883254807906841535770622790558731969378294", StringMathUtility.acos("-.058949"));
        Assert.assertEquals("2.9829468104962281926169665262939708695907648468052551551496607544", StringMathUtility.acos("-.9874421200001"));
        Assert.assertEquals("1.5707963268405464192313216916397672970672141960195529253555786549", StringMathUtility.acos("-.0000000000456498"));
        Assert.assertEquals("2.1712139715151780528345283057191885696960868611616330219686310885", StringMathUtility.acos("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acos("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acos("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("2", StringMathUtility.acos("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.57079633", StringMathUtility.acos("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.5707963267948966", StringMathUtility.acos("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acos("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.57079632679489661923132169163975144209858469968755291048747229615390820314310449931401741267105853399107404325664115332354692230477529111586267970406424055872514205135096926055277982231147447746519098221440548783296672306423782411689339158263560095457282428346173017430522716332410669680363012457063686229350330315779408744076046048141462704585768218394629518000566526527441023326069207347597075580471652863518287979597654609305869096630589655255927403723118998137478367594287636244561396909150597456491683668120", StringMathUtility.acos("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722961539082031431044993140174126710585339910740432566411533235469223047752911158626797040642405587251420513509692605527798223114744774651909822144054878329667230642378241168933915826356009545728242834617301743052271633241066968036301245706368622935033031577940874407604604814146270458576821839462951800056652652744102332606920734759707558047165286351828797959765460930586909663058965525592740372311899813747836759428763624456139690915059745649168366812000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.acos("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acos("0", null));
        
        //custom precision
        Assert.assertEquals("1.570796268994897", StringMathUtility.acos(".0000000578", 15));
        Assert.assertEquals("1.5707962689949", StringMathUtility.acos(".0000000578", 13));
        Assert.assertEquals("1.570796268995", StringMathUtility.acos(".0000000578", 12));
        Assert.assertEquals("1.5707962690", StringMathUtility.acos(".0000000578", 10));
        Assert.assertEquals("1.570796269", StringMathUtility.acos(".0000000578", 9));
        Assert.assertEquals("1.570796", StringMathUtility.acos(".0000000578", 6));
        Assert.assertEquals("1.571", StringMathUtility.acos(".0000000578", 3));
        Assert.assertEquals("1.57", StringMathUtility.acos(".0000000578", 2));
        Assert.assertEquals("1.6", StringMathUtility.acos(".0000000578", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = 59875132", () ->
                StringMathUtility.acos("59875132"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = 8976464100065468", () ->
                StringMathUtility.acos("8976464100065468"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = -59875132", () ->
                StringMathUtility.acos("-59875132"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = -8976464100065468", () ->
                StringMathUtility.acos("-8976464100065468"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = 18.0000000578", () ->
                StringMathUtility.acos("18.0000000578"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acos where input < -1 or input > 1; input = 2.99999999999999", () ->
                StringMathUtility.acos("2.99999999999999"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acos(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acos(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acos(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.acos("15a5"));
    }
    
    /**
     * JUnit test of cosh.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#cosh(String, int)
     * @see StringMathUtility#cosh(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#cosh(String)
     */
    @Test
    public void testCosh() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.cosh("0"));
        Assert.assertEquals("1.5430806348152437784779056207570616826015291123658637047374022147", StringMathUtility.cosh("1"));
        Assert.assertEquals("1490.4791612521780886277154604210072273084936250164847112641620374817", StringMathUtility.cosh("8"));
        Assert.assertEquals("107321789892958.0323121488807679598832571646517094910714013977905189491538819889", StringMathUtility.cosh("33"));
        Assert.assertEquals("1.5430806348152437784779056207570616826015291123658637047374022147", StringMathUtility.cosh("-1"));
        Assert.assertEquals("1490.4791612521780886277154604210072273084936250164847112641620374817", StringMathUtility.cosh("-8"));
        Assert.assertEquals("107321789892958.0323121488807679598832571646517094910714013977905189491538819889", StringMathUtility.cosh("-33"));
        
        //decimal
        Assert.assertEquals("1.0017379955053668869854151620514963455370908824674716032936746617", StringMathUtility.cosh(".058949"));
        Assert.assertEquals("1.5284438851325902632041338984045393583047737498148656641795257708", StringMathUtility.cosh(".9874421200001"));
        Assert.assertEquals("1.0000000000000000000010419521200200000000001809440367356954141334", StringMathUtility.cosh(".0000000000456498"));
        Assert.assertEquals("1.1638962949480063626019394182098467398924857014140939790862358513", StringMathUtility.cosh(".5649871212025644980798794213200031654"));
        Assert.assertEquals("1.0017379955053668869854151620514963455370908824674716032936746617", StringMathUtility.cosh("-.058949"));
        Assert.assertEquals("1.5284438851325902632041338984045393583047737498148656641795257708", StringMathUtility.cosh("-.9874421200001"));
        Assert.assertEquals("1.0000000000000000000010419521200200000000001809440367356954141334", StringMathUtility.cosh("-.0000000000456498"));
        Assert.assertEquals("1.1638962949480063626019394182098467398924857014140939790862358513", StringMathUtility.cosh("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("1", StringMathUtility.cosh("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.cosh("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.cosh("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", StringMathUtility.cosh("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", StringMathUtility.cosh("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.cosh("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.cosh("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.cosh("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1", StringMathUtility.cosh("0", null));
        
        //custom precision
        Assert.assertEquals("1.000000000000002", StringMathUtility.cosh(".0000000578", 15));
        Assert.assertEquals("1.0000000000000", StringMathUtility.cosh(".0000000578", 13));
        Assert.assertEquals("1.000000000000", StringMathUtility.cosh(".0000000578", 12));
        Assert.assertEquals("1.0000000000", StringMathUtility.cosh(".0000000578", 10));
        Assert.assertEquals("1.000000000", StringMathUtility.cosh(".0000000578", 9));
        Assert.assertEquals("1.000000", StringMathUtility.cosh(".0000000578", 6));
        Assert.assertEquals("1.000", StringMathUtility.cosh(".0000000578", 3));
        Assert.assertEquals("1.00", StringMathUtility.cosh(".0000000578", 2));
        Assert.assertEquals("1.0", StringMathUtility.cosh(".0000000578", 1));
        Assert.assertEquals("32829986.466238426093098", StringMathUtility.cosh("18.0000000578", 15));
        Assert.assertEquals("32829986.4662384260931", StringMathUtility.cosh("18.0000000578", 13));
        Assert.assertEquals("32829986.466238426093", StringMathUtility.cosh("18.0000000578", 12));
        Assert.assertEquals("32829986.4662384261", StringMathUtility.cosh("18.0000000578", 10));
        Assert.assertEquals("32829986.466238426", StringMathUtility.cosh("18.0000000578", 9));
        Assert.assertEquals("32829986.466238", StringMathUtility.cosh("18.0000000578", 6));
        Assert.assertEquals("32829986.466", StringMathUtility.cosh("18.0000000578", 3));
        Assert.assertEquals("32829986.47", StringMathUtility.cosh("18.0000000578", 2));
        Assert.assertEquals("32829986.5", StringMathUtility.cosh("18.0000000578", 1));
        Assert.assertEquals("10.06766199577767", StringMathUtility.cosh("2.99999999999999", 14));
        Assert.assertEquals("10.0676619958", StringMathUtility.cosh("2.99999999999999", 10));
        Assert.assertEquals("10.06766", StringMathUtility.cosh("2.99999999999999", 5));
        Assert.assertEquals("10.1", StringMathUtility.cosh("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.cosh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.cosh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.cosh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.cosh("15a5"));
    }
    
    /**
     * JUnit test of acosh.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#acosh(String, int)
     * @see StringMathUtility#acosh(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#acosh(String)
     */
    @Test
    public void testAcosh() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.acosh("1"));
        Assert.assertEquals("18.6009189988457704182102678275088166579802206884124985184922551594", StringMathUtility.acosh("59875132"));
        Assert.assertEquals("37.4265296274670849742187455012988046032016552573826996503480502279", StringMathUtility.acosh("8976464100065468"));
        
        //decimal
        Assert.assertEquals("0.3416980281899250610129909174187108668752786117684185436719641118", StringMathUtility.acosh("1.058949"));
        Assert.assertEquals("1.3096770600423673228657322678089338513711126708637894849504987827", StringMathUtility.acosh("1.9874421200001"));
        Assert.assertEquals("0.0000095550824171906212424610277186890932663323498279701849900641", StringMathUtility.acosh("1.0000000000456498"));
        Assert.assertEquals("1.0184168998681986666063927856588688638724554912576914541103577361", StringMathUtility.acosh("1.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.acosh("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.acosh("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.acosh("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.acosh("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.acosh("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.acosh("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.acosh("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.acosh("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.acosh("1", null));
        
        //custom precision
        Assert.assertEquals("3.5827464421382", StringMathUtility.acosh("18.0000000578", 13));
        Assert.assertEquals("3.582746442138", StringMathUtility.acosh("18.0000000578", 12));
        Assert.assertEquals("3.5827464421", StringMathUtility.acosh("18.0000000578", 10));
        Assert.assertEquals("3.582746442", StringMathUtility.acosh("18.0000000578", 9));
        Assert.assertEquals("3.582746", StringMathUtility.acosh("18.0000000578", 6));
        Assert.assertEquals("3.583", StringMathUtility.acosh("18.0000000578", 3));
        Assert.assertEquals("3.58", StringMathUtility.acosh("18.0000000578", 2));
        Assert.assertEquals("3.6", StringMathUtility.acosh("18.0000000578", 1));
        Assert.assertEquals("1.7627471740", StringMathUtility.acosh("2.99999999999999", 10));
        Assert.assertEquals("1.76275", StringMathUtility.acosh("2.99999999999999", 5));
        Assert.assertEquals("1.8", StringMathUtility.acosh("2.99999999999999", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = 0", () ->
                StringMathUtility.acosh("0"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -1", () ->
                StringMathUtility.acosh("-1"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -59875132", () ->
                StringMathUtility.acosh("-59875132"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -8976464100065468", () ->
                StringMathUtility.acosh("-8976464100065468"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = 0.0000000578", () ->
                StringMathUtility.acosh(".0000000578"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -0.058949", () ->
                StringMathUtility.acosh("-.058949"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -0.9874421200001", () ->
                StringMathUtility.acosh("-.9874421200001"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -0.0000000000456498", () ->
                StringMathUtility.acosh("-.0000000000456498"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acosh where input < 1; input = -0.5649871212025644980798794213200031654", () ->
                StringMathUtility.acosh("-.5649871212025644980798794213200031654"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acosh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acosh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acosh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.acosh("15a5"));
    }
    
    /**
     * JUnit test of tan.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#tan(String, int)
     * @see StringMathUtility#tan(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#tan(String)
     */
    @Test
    public void testTan() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.tan("0"));
        Assert.assertEquals("1.5574077246549022305069748074583601730872507723815200383839466057", StringMathUtility.tan("1"));
        Assert.assertEquals("6.6033831170161096142405723990811607213988054805311837427662663651", StringMathUtility.tan("59875132"));
        Assert.assertEquals("0.2454092967585245788497093354025717027307249360746822241398594704", StringMathUtility.tan("8976464100065468"));
        Assert.assertEquals("-1.5574077246549022305069748074583601730872507723815200383839466057", StringMathUtility.tan("-1"));
        Assert.assertEquals("-6.6033831170161096142405723990811607213988054805311837427662663651", StringMathUtility.tan("-59875132"));
        Assert.assertEquals("-0.2454092967585245788497093354025717027307249360746822241398594704", StringMathUtility.tan("-8976464100065468"));
        
        //decimal
        Assert.assertEquals("0.0590173773347285344013098330944501262058551767501590972262584092", StringMathUtility.tan(".058949"));
        Assert.assertEquals("1.5152134764338761983018002136921813421325326042915924530058092206", StringMathUtility.tan(".9874421200001"));
        Assert.assertEquals("0.0000000000456498000000000000000317099372589926640000264321890822", StringMathUtility.tan(".0000000000456498"));
        Assert.assertEquals("0.6339187709411662540563153652515695063537225632345790291467568026", StringMathUtility.tan(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-0.0590173773347285344013098330944501262058551767501590972262584092", StringMathUtility.tan("-.058949"));
        Assert.assertEquals("-1.5152134764338761983018002136921813421325326042915924530058092206", StringMathUtility.tan("-.9874421200001"));
        Assert.assertEquals("-0.0000000000456498000000000000000317099372589926640000264321890822", StringMathUtility.tan("-.0000000000456498"));
        Assert.assertEquals("-0.6339187709411662540563153652515695063537225632345790291467568026", StringMathUtility.tan("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.tan("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.tan("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.tan("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.tan("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.tan("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.tan("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.tan("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.tan("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.tan("0", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.tan(".0000000578", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.tan(".0000000578", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.tan(".0000000578", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.tan(".0000000578", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.tan(".0000000578", 9));
        Assert.assertEquals("0.000000", StringMathUtility.tan(".0000000578", 6));
        Assert.assertEquals("0.000", StringMathUtility.tan(".0000000578", 3));
        Assert.assertEquals("0.00", StringMathUtility.tan(".0000000578", 2));
        Assert.assertEquals("0.0", StringMathUtility.tan(".0000000578", 1));
        Assert.assertEquals("-1.137313579774408", StringMathUtility.tan("18.0000000578", 15));
        Assert.assertEquals("-1.1373135797744", StringMathUtility.tan("18.0000000578", 13));
        Assert.assertEquals("-1.137313579774", StringMathUtility.tan("18.0000000578", 12));
        Assert.assertEquals("-1.1373135798", StringMathUtility.tan("18.0000000578", 10));
        Assert.assertEquals("-1.137313580", StringMathUtility.tan("18.0000000578", 9));
        Assert.assertEquals("-1.137314", StringMathUtility.tan("18.0000000578", 6));
        Assert.assertEquals("-1.137", StringMathUtility.tan("18.0000000578", 3));
        Assert.assertEquals("-1.14", StringMathUtility.tan("18.0000000578", 2));
        Assert.assertEquals("-1.1", StringMathUtility.tan("18.0000000578", 1));
        Assert.assertEquals("-0.14254654307429", StringMathUtility.tan("2.99999999999999", 14));
        Assert.assertEquals("-0.1425465431", StringMathUtility.tan("2.99999999999999", 10));
        Assert.assertEquals("-0.14255", StringMathUtility.tan("2.99999999999999", 5));
        Assert.assertEquals("-0.1", StringMathUtility.tan("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.tan(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.tan(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.tan(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.tan("15a5"));
    }
    
    /**
     * JUnit test of atan.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#atan(String, int)
     * @see StringMathUtility#atan(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#atan(String)
     */
    @Test
    public void testAtan() throws Exception {
        //satandard
        Assert.assertEquals("0", StringMathUtility.atan("0"));
        Assert.assertEquals("0.7853981633974483096156608458198757210492923498437764552437361481", StringMathUtility.atan("1"));
        Assert.assertEquals("1.5707963100934720612027914660895862946184999257992802698645135631", StringMathUtility.atan("59875132"));
        Assert.assertEquals("1.570796326794896507828882061462128932329784821177893480664184621", StringMathUtility.atan("8976464100065468"));
        Assert.assertEquals("-0.7853981633974483096156608458198757210492923498437764552437361481", StringMathUtility.atan("-1"));
        Assert.assertEquals("-1.5707963100934720612027914660895862946184999257992802698645135631", StringMathUtility.atan("-59875132"));
        Assert.assertEquals("-1.570796326794896507828882061462128932329784821177893480664184621", StringMathUtility.atan("-8976464100065468"));
        
        //decimal
        Assert.assertEquals("0.0588808597264367788262377361220484197761735784956719355661059602", StringMathUtility.atan(".058949"));
        Assert.assertEquals("0.7790796332860257910538771336194885133263586933360305089561627823", StringMathUtility.atan(".9874421200001"));
        Assert.assertEquals("0.0000000000456497999999999999999682900627410073360000396482836233", StringMathUtility.atan(".0000000000456498"));
        Assert.assertEquals("0.5142767788191687133609461231049609671307129760306840071436079287", StringMathUtility.atan(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-0.0588808597264367788262377361220484197761735784956719355661059602", StringMathUtility.atan("-.058949"));
        Assert.assertEquals("-0.7790796332860257910538771336194885133263586933360305089561627823", StringMathUtility.atan("-.9874421200001"));
        Assert.assertEquals("-0.0000000000456497999999999999999682900627410073360000396482836233", StringMathUtility.atan("-.0000000000456498"));
        Assert.assertEquals("-0.5142767788191687133609461231049609671307129760306840071436079287", StringMathUtility.atan("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.atan("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.atan("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.atan("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.atan("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.atan("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.atan("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.atan("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.atan("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.atan("0", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.atan(".0000000578", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.atan(".0000000578", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.atan(".0000000578", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.atan(".0000000578", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.atan(".0000000578", 9));
        Assert.assertEquals("0.000000", StringMathUtility.atan(".0000000578", 6));
        Assert.assertEquals("0.000", StringMathUtility.atan(".0000000578", 3));
        Assert.assertEquals("0.00", StringMathUtility.atan(".0000000578", 2));
        Assert.assertEquals("0.0", StringMathUtility.atan(".0000000578", 1));
        Assert.assertEquals("1.515297821727026", StringMathUtility.atan("18.0000000578", 15));
        Assert.assertEquals("1.5152978217270", StringMathUtility.atan("18.0000000578", 13));
        Assert.assertEquals("1.515297821727", StringMathUtility.atan("18.0000000578", 12));
        Assert.assertEquals("1.5152978217", StringMathUtility.atan("18.0000000578", 10));
        Assert.assertEquals("1.515297822", StringMathUtility.atan("18.0000000578", 9));
        Assert.assertEquals("1.515298", StringMathUtility.atan("18.0000000578", 6));
        Assert.assertEquals("1.515", StringMathUtility.atan("18.0000000578", 3));
        Assert.assertEquals("1.52", StringMathUtility.atan("18.0000000578", 2));
        Assert.assertEquals("1.5", StringMathUtility.atan("18.0000000578", 1));
        Assert.assertEquals("1.24904577239825", StringMathUtility.atan("2.99999999999999", 14));
        Assert.assertEquals("1.2490457724", StringMathUtility.atan("2.99999999999999", 10));
        Assert.assertEquals("1.24905", StringMathUtility.atan("2.99999999999999", 5));
        Assert.assertEquals("1.2", StringMathUtility.atan("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.atan("15a5"));
    }
    
    /**
     * JUnit test of atan2.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#atan2(String, String, int)
     * @see StringMathUtility#atan2(String, String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#atan2(String, String)
     */
    @Test
    public void testAtan2() throws Exception {
        //satandard
        Assert.assertEquals("0", StringMathUtility.atan2("0", "1"));
        Assert.assertEquals("0.7853981633974483096156608458198757210492923498437764552437361481", StringMathUtility.atan2("1", "1"));
        Assert.assertEquals("1.5707963100934720612027914660895862946184999257992802698645135631", StringMathUtility.atan2("59875132", "1"));
        Assert.assertEquals("1.570796326794896507828882061462128932329784821177893480664184621", StringMathUtility.atan2("8976464100065468", "1"));
        Assert.assertEquals("-0.7853981633974483096156608458198757210492923498437764552437361481", StringMathUtility.atan2("-1", "1"));
        Assert.assertEquals("-1.5707963100934720612027914660895862946184999257992802698645135631", StringMathUtility.atan2("-59875132", "1"));
        Assert.assertEquals("-1.570796326794896507828882061462128932329784821177893480664184621", StringMathUtility.atan2("-8976464100065468", "1"));
        Assert.assertEquals("3.1415926535897932384626433832795028841971693993751058209749445923", StringMathUtility.atan2("0", "-1"));
        Assert.assertEquals("2.3561944901923449288469825374596271631478770495313293657312084442", StringMathUtility.atan2("1", "-1"));
        Assert.assertEquals("1.5707963434963211772598519171899165895786694735758255511104310292", StringMathUtility.atan2("59875132", "-1"));
        Assert.assertEquals("1.5707963267948967306337613218173739518673845781972123403107599713", StringMathUtility.atan2("8976464100065468", "-1"));
        Assert.assertEquals("-2.3561944901923449288469825374596271631478770495313293657312084442", StringMathUtility.atan2("-1", "-1"));
        Assert.assertEquals("-1.5707963434963211772598519171899165895786694735758255511104310292", StringMathUtility.atan2("-59875132", "-1"));
        Assert.assertEquals("-1.5707963267948967306337613218173739518673845781972123403107599713", StringMathUtility.atan2("-8976464100065468", "-1"));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("1", "0"));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("59875132", "0"));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("8976464100065468", "0"));
        Assert.assertEquals("-1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("-1", "0"));
        Assert.assertEquals("-1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("-59875132", "0"));
        Assert.assertEquals("-1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("-8976464100065468", "0"));
        Assert.assertEquals("0", StringMathUtility.atan2("0", "0.4211315"));
        Assert.assertEquals("1.1722068912534580611863788464231873075622560323935635630021869362", StringMathUtility.atan2("1", "0.4211315"));
        Assert.assertEquals("1.5707963197614006429719291769721871265534929272275367549065998079", StringMathUtility.atan2("59875132", "0.4211315"));
        Assert.assertEquals("1.5707963267948965723162451865236040081258853536508026108183474352", StringMathUtility.atan2("8976464100065468", "0.4211315"));
        Assert.assertEquals("-1.1722068912534580611863788464231873075622560323935635630021869362", StringMathUtility.atan2("-1", "0.4211315"));
        Assert.assertEquals("-1.5707963197614006429719291769721871265534929272275367549065998079", StringMathUtility.atan2("-59875132", "0.4211315"));
        Assert.assertEquals("-1.5707963267948965723162451865236040081258853536508026108183474352", StringMathUtility.atan2("-8976464100065468", "0.4211315"));
        Assert.assertEquals("0", StringMathUtility.atan2("0", "184451.115"));
        Assert.assertEquals("0.0000054214906751320125097633575167965987344790604548221826347343", StringMathUtility.atan2("1", "184451.115"));
        Assert.assertEquals("1.5677157401580536647605789234358615376532817568750299399353693848", StringMathUtility.atan2("59875132", "184451.115"));
        Assert.assertEquals("1.5707963267743483150278152415197783806248266392837327249941579295", StringMathUtility.atan2("8976464100065468", "184451.115"));
        Assert.assertEquals("-0.0000054214906751320125097633575167965987344790604548221826347343", StringMathUtility.atan2("-1", "184451.115"));
        Assert.assertEquals("-1.5677157401580536647605789234358615376532817568750299399353693848", StringMathUtility.atan2("-59875132", "184451.115"));
        Assert.assertEquals("-1.5707963267743483150278152415197783806248266392837327249941579295", StringMathUtility.atan2("-8976464100065468", "184451.115"));
        
        //decimal
        Assert.assertEquals("0.0588808597264367788262377361220484197761735784956719355661059602", StringMathUtility.atan2(".058949", "1"));
        Assert.assertEquals("0.7790796332860257910538771336194885133263586933360305089561627823", StringMathUtility.atan2(".9874421200001", "1"));
        Assert.assertEquals("0.0000000000456497999999999999999682900627410073360000396482836233", StringMathUtility.atan2(".0000000000456498", "1"));
        Assert.assertEquals("0.5142767788191687133609461231049609671307129760306840071436079287", StringMathUtility.atan2(".5649871212025644980798794213200031654", "1"));
        Assert.assertEquals("-0.0588808597264367788262377361220484197761735784956719355661059602", StringMathUtility.atan2("-.058949", "1"));
        Assert.assertEquals("-0.7790796332860257910538771336194885133263586933360305089561627823", StringMathUtility.atan2("-.9874421200001", "1"));
        Assert.assertEquals("-0.0000000000456497999999999999999682900627410073360000396482836233", StringMathUtility.atan2("-.0000000000456498", "1"));
        Assert.assertEquals("-0.5142767788191687133609461231049609671307129760306840071436079287", StringMathUtility.atan2("-.5649871212025644980798794213200031654", "1"));
        Assert.assertEquals("3.0827117938633564596364056471574544644209958208794338854088386321", StringMathUtility.atan2(".058949", "-1"));
        Assert.assertEquals("2.36251302030376744740876624966001437087081070603907531201878181", StringMathUtility.atan2(".9874421200001", "-1"));
        Assert.assertEquals("3.1415926535441434384626433832795345941344283920391057813266609691", StringMathUtility.atan2(".0000000000456498", "-1"));
        Assert.assertEquals("2.6273158747706245251016972601745419170664564233444218138313366636", StringMathUtility.atan2(".5649871212025644980798794213200031654", "-1"));
        Assert.assertEquals("-3.0827117938633564596364056471574544644209958208794338854088386321", StringMathUtility.atan2("-.058949", "-1"));
        Assert.assertEquals("-2.36251302030376744740876624966001437087081070603907531201878181", StringMathUtility.atan2("-.9874421200001", "-1"));
        Assert.assertEquals("-3.1415926535441434384626433832795345941344283920391057813266609691", StringMathUtility.atan2("-.0000000000456498", "-1"));
        Assert.assertEquals("-2.6273158747706245251016972601745419170664564233444218138313366636", StringMathUtility.atan2("-.5649871212025644980798794213200031654", "-1"));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2(".058949", "0"));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2(".9874421200001", "0"));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2(".0000000000456498", "0"));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2(".5649871212025644980798794213200031654", "0"));
        Assert.assertEquals("-1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("-.058949", "0"));
        Assert.assertEquals("-1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("-.9874421200001", "0"));
        Assert.assertEquals("-1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("-.0000000000456498", "0"));
        Assert.assertEquals("-1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.atan2("-.5649871212025644980798794213200031654", "0"));
        Assert.assertEquals("0.1390740263848412026674342066544472935363088088656305832534203354", StringMathUtility.atan2(".058949", "0.4211315"));
        Assert.assertEquals("1.1676666179209042963910839801885052796490360589098335599468441626", StringMathUtility.atan2(".9874421200001", "0.4211315"));
        Assert.assertEquals("0.0000000001083979707051122986521340795528278634770153096330017606", StringMathUtility.atan2(".0000000000456498", "0.4211315"));
        Assert.assertEquals("0.9302569884393380973507087360019915390032012318306688817914219126", StringMathUtility.atan2(".5649871212025644980798794213200031654", "0.4211315"));
        Assert.assertEquals("-0.1390740263848412026674342066544472935363088088656305832534203354", StringMathUtility.atan2("-.058949", "0.4211315"));
        Assert.assertEquals("-1.1676666179209042963910839801885052796490360589098335599468441626", StringMathUtility.atan2("-.9874421200001", "0.4211315"));
        Assert.assertEquals("-0.0000000001083979707051122986521340795528278634770153096330017606", StringMathUtility.atan2("-.0000000000456498", "0.4211315"));
        Assert.assertEquals("-0.9302569884393380973507087360019915390032012318306688817914219126", StringMathUtility.atan2("-.5649871212025644980798794213200031654", "0.4211315"));
        Assert.assertEquals("0.0000003195914538114773283351706387351322518103857388544063302231", StringMathUtility.atan2(".058949", "184451.115"));
        Assert.assertEquals("0.0000053534082458144369149578773963068955135110120457257307574391", StringMathUtility.atan2(".9874421200001", "184451.115"));
        Assert.assertEquals("0.0000000000000002474899650240661326444136702562034822274201703837", StringMathUtility.atan2(".0000000000456498", "184451.115"));
        Assert.assertEquals("0.0000030630724091898144249293370279406619076964361679466042127893", StringMathUtility.atan2(".5649871212025644980798794213200031654", "184451.115"));
        Assert.assertEquals("-0.0000003195914538114773283351706387351322518103857388544063302231", StringMathUtility.atan2("-.058949", "184451.115"));
        Assert.assertEquals("-0.0000053534082458144369149578773963068955135110120457257307574391", StringMathUtility.atan2("-.9874421200001", "184451.115"));
        Assert.assertEquals("-0.0000000000000002474899650240661326444136702562034822274201703837", StringMathUtility.atan2("-.0000000000456498", "184451.115"));
        Assert.assertEquals("-0.0000030630724091898144249293370279406619076964361679466042127893", StringMathUtility.atan2("-.5649871212025644980798794213200031654", "184451.115"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.atan2("0", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.atan2("0.0", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.atan2("0", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.atan2("0", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.atan2("0", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.atan2("0", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.atan2("0", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.atan2("0", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.atan2("0", "1", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.atan2(".0000000578", "1", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.atan2(".0000000578", "1", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.atan2(".0000000578", "1", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.atan2(".0000000578", "1", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.atan2(".0000000578", "1", 9));
        Assert.assertEquals("0.000000", StringMathUtility.atan2(".0000000578", "1", 6));
        Assert.assertEquals("0.000", StringMathUtility.atan2(".0000000578", "1", 3));
        Assert.assertEquals("0.00", StringMathUtility.atan2(".0000000578", "1", 2));
        Assert.assertEquals("0.0", StringMathUtility.atan2(".0000000578", "1", 1));
        Assert.assertEquals("1.515297821727026", StringMathUtility.atan2("18.0000000578", "1", 15));
        Assert.assertEquals("1.5152978217270", StringMathUtility.atan2("18.0000000578", "1", 13));
        Assert.assertEquals("1.515297821727", StringMathUtility.atan2("18.0000000578", "1", 12));
        Assert.assertEquals("1.5152978217", StringMathUtility.atan2("18.0000000578", "1", 10));
        Assert.assertEquals("1.515297822", StringMathUtility.atan2("18.0000000578", "1", 9));
        Assert.assertEquals("1.515298", StringMathUtility.atan2("18.0000000578", "1", 6));
        Assert.assertEquals("1.515", StringMathUtility.atan2("18.0000000578", "1", 3));
        Assert.assertEquals("1.52", StringMathUtility.atan2("18.0000000578", "1", 2));
        Assert.assertEquals("1.5", StringMathUtility.atan2("18.0000000578", "1", 1));
        Assert.assertEquals("1.24904577239825", StringMathUtility.atan2("2.99999999999999", "1", 14));
        Assert.assertEquals("1.2490457724", StringMathUtility.atan2("2.99999999999999", "1", 10));
        Assert.assertEquals("1.24905", StringMathUtility.atan2("2.99999999999999", "1", 5));
        Assert.assertEquals("1.2", StringMathUtility.atan2("2.99999999999999", "1", 1));
        Assert.assertEquals("3.141592595789793", StringMathUtility.atan2(".0000000578", "-1", 15));
        Assert.assertEquals("3.1415925957898", StringMathUtility.atan2(".0000000578", "-1", 13));
        Assert.assertEquals("3.141592595790", StringMathUtility.atan2(".0000000578", "-1", 12));
        Assert.assertEquals("3.1415925958", StringMathUtility.atan2(".0000000578", "-1", 10));
        Assert.assertEquals("3.141592596", StringMathUtility.atan2(".0000000578", "-1", 9));
        Assert.assertEquals("3.141593", StringMathUtility.atan2(".0000000578", "-1", 6));
        Assert.assertEquals("3.142", StringMathUtility.atan2(".0000000578", "-1", 3));
        Assert.assertEquals("3.14", StringMathUtility.atan2(".0000000578", "-1", 2));
        Assert.assertEquals("3.1", StringMathUtility.atan2(".0000000578", "-1", 1));
        Assert.assertEquals("1.626294831862767", StringMathUtility.atan2("18.0000000578", "-1", 15));
        Assert.assertEquals("1.6262948318628", StringMathUtility.atan2("18.0000000578", "-1", 13));
        Assert.assertEquals("1.626294831863", StringMathUtility.atan2("18.0000000578", "-1", 12));
        Assert.assertEquals("1.6262948319", StringMathUtility.atan2("18.0000000578", "-1", 10));
        Assert.assertEquals("1.626294832", StringMathUtility.atan2("18.0000000578", "-1", 9));
        Assert.assertEquals("1.626295", StringMathUtility.atan2("18.0000000578", "-1", 6));
        Assert.assertEquals("1.626", StringMathUtility.atan2("18.0000000578", "-1", 3));
        Assert.assertEquals("1.63", StringMathUtility.atan2("18.0000000578", "-1", 2));
        Assert.assertEquals("1.6", StringMathUtility.atan2("18.0000000578", "-1", 1));
        Assert.assertEquals("1.89254688119154", StringMathUtility.atan2("2.99999999999999", "-1", 14));
        Assert.assertEquals("1.8925468812", StringMathUtility.atan2("2.99999999999999", "-1", 10));
        Assert.assertEquals("1.89255", StringMathUtility.atan2("2.99999999999999", "-1", 5));
        Assert.assertEquals("1.9", StringMathUtility.atan2("2.99999999999999", "-1", 1));
        Assert.assertEquals("1.570796326794897", StringMathUtility.atan2(".0000000578", "0", 15));
        Assert.assertEquals("1.5707963267949", StringMathUtility.atan2(".0000000578", "0", 13));
        Assert.assertEquals("1.570796326795", StringMathUtility.atan2(".0000000578", "0", 12));
        Assert.assertEquals("1.5707963268", StringMathUtility.atan2(".0000000578", "0", 10));
        Assert.assertEquals("1.570796327", StringMathUtility.atan2(".0000000578", "0", 9));
        Assert.assertEquals("1.570796", StringMathUtility.atan2(".0000000578", "0", 6));
        Assert.assertEquals("1.571", StringMathUtility.atan2(".0000000578", "0", 3));
        Assert.assertEquals("1.57", StringMathUtility.atan2(".0000000578", "0", 2));
        Assert.assertEquals("1.6", StringMathUtility.atan2(".0000000578", "0", 1));
        Assert.assertEquals("1.570796326794897", StringMathUtility.atan2("18.0000000578", "0", 15));
        Assert.assertEquals("1.5707963267949", StringMathUtility.atan2("18.0000000578", "0", 13));
        Assert.assertEquals("1.570796326795", StringMathUtility.atan2("18.0000000578", "0", 12));
        Assert.assertEquals("1.5707963268", StringMathUtility.atan2("18.0000000578", "0", 10));
        Assert.assertEquals("1.570796327", StringMathUtility.atan2("18.0000000578", "0", 9));
        Assert.assertEquals("1.570796", StringMathUtility.atan2("18.0000000578", "0", 6));
        Assert.assertEquals("1.571", StringMathUtility.atan2("18.0000000578", "0", 3));
        Assert.assertEquals("1.57", StringMathUtility.atan2("18.0000000578", "0", 2));
        Assert.assertEquals("1.6", StringMathUtility.atan2("18.0000000578", "0", 1));
        Assert.assertEquals("1.57079632679490", StringMathUtility.atan2("2.99999999999999", "0", 14));
        Assert.assertEquals("1.5707963268", StringMathUtility.atan2("2.99999999999999", "0", 10));
        Assert.assertEquals("1.57080", StringMathUtility.atan2("2.99999999999999", "0", 5));
        Assert.assertEquals("1.6", StringMathUtility.atan2("2.99999999999999", "0", 1));
        Assert.assertEquals("0.000000137249291", StringMathUtility.atan2(".0000000578", "0.4211315", 15));
        Assert.assertEquals("0.0000001372493", StringMathUtility.atan2(".0000000578", "0.4211315", 13));
        Assert.assertEquals("0.000000137249", StringMathUtility.atan2(".0000000578", "0.4211315", 12));
        Assert.assertEquals("0.0000001372", StringMathUtility.atan2(".0000000578", "0.4211315", 10));
        Assert.assertEquals("0.000000137", StringMathUtility.atan2(".0000000578", "0.4211315", 9));
        Assert.assertEquals("0.000000", StringMathUtility.atan2(".0000000578", "0.4211315", 6));
        Assert.assertEquals("0.000", StringMathUtility.atan2(".0000000578", "0.4211315", 3));
        Assert.assertEquals("0.00", StringMathUtility.atan2(".0000000578", "0.4211315", 2));
        Assert.assertEquals("0.0", StringMathUtility.atan2(".0000000578", "0.4211315", 1));
        Assert.assertEquals("1.547404399908630", StringMathUtility.atan2("18.0000000578", "0.4211315", 15));
        Assert.assertEquals("1.5474043999086", StringMathUtility.atan2("18.0000000578", "0.4211315", 13));
        Assert.assertEquals("1.547404399909", StringMathUtility.atan2("18.0000000578", "0.4211315", 12));
        Assert.assertEquals("1.5474043999", StringMathUtility.atan2("18.0000000578", "0.4211315", 10));
        Assert.assertEquals("1.547404400", StringMathUtility.atan2("18.0000000578", "0.4211315", 9));
        Assert.assertEquals("1.547404", StringMathUtility.atan2("18.0000000578", "0.4211315", 6));
        Assert.assertEquals("1.547", StringMathUtility.atan2("18.0000000578", "0.4211315", 3));
        Assert.assertEquals("1.55", StringMathUtility.atan2("18.0000000578", "0.4211315", 2));
        Assert.assertEquals("1.5", StringMathUtility.atan2("18.0000000578", "0.4211315", 1));
        Assert.assertEquals("1.43133048817898", StringMathUtility.atan2("2.99999999999999", "0.4211315", 14));
        Assert.assertEquals("1.4313304882", StringMathUtility.atan2("2.99999999999999", "0.4211315", 10));
        Assert.assertEquals("1.43133", StringMathUtility.atan2("2.99999999999999", "0.4211315", 5));
        Assert.assertEquals("1.4", StringMathUtility.atan2("2.99999999999999", "0.4211315", 1));
        Assert.assertEquals("0.000000000000313", StringMathUtility.atan2(".0000000578", "184451.115", 15));
        Assert.assertEquals("0.0000000000003", StringMathUtility.atan2(".0000000578", "184451.115", 13));
        Assert.assertEquals("0.000000000000", StringMathUtility.atan2(".0000000578", "184451.115", 12));
        Assert.assertEquals("0.0000000000", StringMathUtility.atan2(".0000000578", "184451.115", 10));
        Assert.assertEquals("0.000000000", StringMathUtility.atan2(".0000000578", "184451.115", 9));
        Assert.assertEquals("0.000000", StringMathUtility.atan2(".0000000578", "184451.115", 6));
        Assert.assertEquals("0.000", StringMathUtility.atan2(".0000000578", "184451.115", 3));
        Assert.assertEquals("0.00", StringMathUtility.atan2(".0000000578", "184451.115", 2));
        Assert.assertEquals("0.0", StringMathUtility.atan2(".0000000578", "184451.115", 1));
        Assert.assertEquals("0.000097586832157", StringMathUtility.atan2("18.0000000578", "184451.115", 15));
        Assert.assertEquals("0.0000975868322", StringMathUtility.atan2("18.0000000578", "184451.115", 13));
        Assert.assertEquals("0.000097586832", StringMathUtility.atan2("18.0000000578", "184451.115", 12));
        Assert.assertEquals("0.0000975868", StringMathUtility.atan2("18.0000000578", "184451.115", 10));
        Assert.assertEquals("0.000097587", StringMathUtility.atan2("18.0000000578", "184451.115", 9));
        Assert.assertEquals("0.000098", StringMathUtility.atan2("18.0000000578", "184451.115", 6));
        Assert.assertEquals("0.000", StringMathUtility.atan2("18.0000000578", "184451.115", 3));
        Assert.assertEquals("0.00", StringMathUtility.atan2("18.0000000578", "184451.115", 2));
        Assert.assertEquals("0.0", StringMathUtility.atan2("18.0000000578", "184451.115", 1));
        Assert.assertEquals("0.00001626447202", StringMathUtility.atan2("2.99999999999999", "184451.115", 14));
        Assert.assertEquals("0.0000162645", StringMathUtility.atan2("2.99999999999999", "184451.115", 10));
        Assert.assertEquals("0.00002", StringMathUtility.atan2("2.99999999999999", "184451.115", 5));
        Assert.assertEquals("0.0", StringMathUtility.atan2("2.99999999999999", "184451.115", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atan2 where x == 0 and y == 0", () ->
                StringMathUtility.atan2("0", "0"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan2("1", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan2(null, "1"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan2(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan2("1", null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan2(null, "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan2(null, null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan2(null, "1", 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan2("1", null, 1));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atan2(null, null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.atan2("1", "15a5"));
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.atan2("15a5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.atan2("15a5", "15a5"));
    }
    
    /**
     * JUnit test of tanh.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#tanh(String, int)
     * @see StringMathUtility#tanh(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#tanh(String)
     */
    @Test
    public void testTanh() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.tanh("0"));
        Assert.assertEquals("0.7615941559557648881194582826047935904127685972579365515968105001", StringMathUtility.tanh("1"));
        Assert.assertEquals("0.9999997749296758898100188329563683933540618881268771274302418414", StringMathUtility.tanh("8"));
        Assert.assertEquals("0.9999999999999999999999999999565895597739272117602686148094881066", StringMathUtility.tanh("33"));
        Assert.assertEquals("-0.7615941559557648881194582826047935904127685972579365515968105001", StringMathUtility.tanh("-1"));
        Assert.assertEquals("-0.9999997749296758898100188329563683933540618881268771274302418414", StringMathUtility.tanh("-8"));
        Assert.assertEquals("-0.9999999999999999999999999999565895597739272117602686148094881066", StringMathUtility.tanh("-33"));
        
        //decimal
        Assert.assertEquals("0.0588808124895699025503369324461617802189982811484215777591335805", StringMathUtility.tanh(".058949"));
        Assert.assertEquals("0.7562695236404269767173080647354269900206377835634293153037495674", StringMathUtility.tanh(".9874421200001"));
        Assert.assertEquals("0.0000000000456497999999999999999682900627410073360000264321890822", StringMathUtility.tanh(".0000000000456498"));
        Assert.assertEquals("0.5116682911811602674982627928146046840482678885886842312412331123", StringMathUtility.tanh(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-0.0588808124895699025503369324461617802189982811484215777591335805", StringMathUtility.tanh("-.058949"));
        Assert.assertEquals("-0.7562695236404269767173080647354269900206377835634293153037495674", StringMathUtility.tanh("-.9874421200001"));
        Assert.assertEquals("-0.0000000000456497999999999999999682900627410073360000264321890822", StringMathUtility.tanh("-.0000000000456498"));
        Assert.assertEquals("-0.5116682911811602674982627928146046840482678885886842312412331123", StringMathUtility.tanh("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.tanh("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.tanh("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.tanh("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.tanh("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.tanh("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.tanh("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.tanh("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.tanh("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.tanh("0", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.tanh(".0000000578", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.tanh(".0000000578", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.tanh(".0000000578", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.tanh(".0000000578", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.tanh(".0000000578", 9));
        Assert.assertEquals("0.000000", StringMathUtility.tanh(".0000000578", 6));
        Assert.assertEquals("0.000", StringMathUtility.tanh(".0000000578", 3));
        Assert.assertEquals("0.00", StringMathUtility.tanh(".0000000578", 2));
        Assert.assertEquals("0.0", StringMathUtility.tanh(".0000000578", 1));
        Assert.assertEquals("1.000000000000000", StringMathUtility.tanh("18.0000000578", 15));
        Assert.assertEquals("1.0000000000000", StringMathUtility.tanh("18.0000000578", 13));
        Assert.assertEquals("1.000000000000", StringMathUtility.tanh("18.0000000578", 12));
        Assert.assertEquals("1.0000000000", StringMathUtility.tanh("18.0000000578", 10));
        Assert.assertEquals("1.000000000", StringMathUtility.tanh("18.0000000578", 9));
        Assert.assertEquals("1.000000", StringMathUtility.tanh("18.0000000578", 6));
        Assert.assertEquals("1.000", StringMathUtility.tanh("18.0000000578", 3));
        Assert.assertEquals("1.00", StringMathUtility.tanh("18.0000000578", 2));
        Assert.assertEquals("1.0", StringMathUtility.tanh("18.0000000578", 1));
        Assert.assertEquals("0.99505475368673", StringMathUtility.tanh("2.99999999999999", 14));
        Assert.assertEquals("0.9950547537", StringMathUtility.tanh("2.99999999999999", 10));
        Assert.assertEquals("0.99505", StringMathUtility.tanh("2.99999999999999", 5));
        Assert.assertEquals("1.0", StringMathUtility.tanh("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.tanh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.tanh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.tanh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.tanh("15a5"));
    }
    
    /**
     * JUnit test of atanh.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#atanh(String, int)
     * @see StringMathUtility#atanh(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#atanh(String)
     */
    @Test
    public void testAtanh() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.atanh("0"));
        
        //decimal
        Assert.assertEquals("0.0590174250113571171814773219623446367674678442211608571230798809", StringMathUtility.atanh(".058949"));
        Assert.assertEquals("2.5321276835576676358357835946901525560274181461720906884975295693", StringMathUtility.atanh(".9874421200001"));
        Assert.assertEquals("0.0000000000456498000000000000000317099372589926640000396482836233", StringMathUtility.atanh(".0000000000456498"));
        Assert.assertEquals("0.6401286182977736873005705872839052912086664039668740110853195761", StringMathUtility.atanh(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-0.0590174250113571171814773219623446367674678442211608571230798809", StringMathUtility.atanh("-.058949"));
        Assert.assertEquals("-2.5321276835576676358357835946901525560274181461720906884975295693", StringMathUtility.atanh("-.9874421200001"));
        Assert.assertEquals("-0.0000000000456498000000000000000317099372589926640000396482836233", StringMathUtility.atanh("-.0000000000456498"));
        Assert.assertEquals("-0.6401286182977736873005705872839052912086664039668740110853195761", StringMathUtility.atanh("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.atanh("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.atanh("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.atanh("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", StringMathUtility.atanh("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", StringMathUtility.atanh("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.atanh("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.atanh("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.atanh("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.atanh("0", null));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", StringMathUtility.atanh(".0000000578", 15));
        Assert.assertEquals("0.0000000578000", StringMathUtility.atanh(".0000000578", 13));
        Assert.assertEquals("0.000000057800", StringMathUtility.atanh(".0000000578", 12));
        Assert.assertEquals("0.0000000578", StringMathUtility.atanh(".0000000578", 10));
        Assert.assertEquals("0.000000058", StringMathUtility.atanh(".0000000578", 9));
        Assert.assertEquals("0.000000", StringMathUtility.atanh(".0000000578", 6));
        Assert.assertEquals("0.000", StringMathUtility.atanh(".0000000578", 3));
        Assert.assertEquals("0.00", StringMathUtility.atanh(".0000000578", 2));
        Assert.assertEquals("0.0", StringMathUtility.atanh(".0000000578", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 1", () ->
                StringMathUtility.atanh("1"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 59875132", () ->
                StringMathUtility.atanh("59875132"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 8976464100065468", () ->
                StringMathUtility.atanh("8976464100065468"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = -1", () ->
                StringMathUtility.atanh("-1"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = -59875132", () ->
                StringMathUtility.atanh("-59875132"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = -8976464100065468", () ->
                StringMathUtility.atanh("-8976464100065468"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 18.0000000578", () ->
                StringMathUtility.atanh("18.0000000578"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for atanh where input <= -1 or input >= 1; input = 2.99999999999999", () ->
                StringMathUtility.atanh("2.99999999999999"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atanh(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atanh(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.atanh(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.atanh("15a5"));
    }
    
    /**
     * JUnit test of cot.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#cot(String, int)
     * @see StringMathUtility#cot(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#cot(String)
     */
    @Test
    public void testCot() throws Exception {
        //standard
        Assert.assertEquals("0.6420926159343307030064199865942656202302781139181713791011622804", StringMathUtility.cot("1"));
        Assert.assertEquals("0.1514375256257845261533114547571066682820283507474647108913067143", StringMathUtility.cot("59875132"));
        Assert.assertEquals("4.0748252540080832566234931080849330819872131084728688315385049387", StringMathUtility.cot("8976464100065468"));
        Assert.assertEquals("-0.6420926159343307030064199865942656202302781139181713791011622804", StringMathUtility.cot("-1"));
        Assert.assertEquals("-0.1514375256257845261533114547571066682820283507474647108913067143", StringMathUtility.cot("-59875132"));
        Assert.assertEquals("-4.0748252540080832566234931080849330819872131084728688315385049387", StringMathUtility.cot("-8976464100065468"));
        
        //decimal
        Assert.assertEquals("16.9441619597615377001691330341472291728403918778012929533892275639", StringMathUtility.cot(".058949"));
        Assert.assertEquals("0.6599730107691131827722103236797793817656712745028477262096064406", StringMathUtility.cot(".9874421200001"));
        Assert.assertEquals("21905901011.6145087163427959214130182388531801562441399735036253280594271123", StringMathUtility.cot(".0000000000456498"));
        Assert.assertEquals("1.5774891765948505056094855730256698970914637387164325415539242618", StringMathUtility.cot(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-16.9441619597615377001691330341472291728403918778012929533892275639", StringMathUtility.cot("-.058949"));
        Assert.assertEquals("-0.6599730107691131827722103236797793817656712745028477262096064406", StringMathUtility.cot("-.9874421200001"));
        Assert.assertEquals("-21905901011.6145087163427959214130182388531801562441399735036253280594271123", StringMathUtility.cot("-.0000000000456498"));
        Assert.assertEquals("-1.5774891765948505056094855730256698970914637387164325415539242618", StringMathUtility.cot("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0.6420926159343307030064199865942656202302781139181713791011622804", StringMathUtility.cot("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0.6420926159343307030064199865942656202302781139181713791011622804", StringMathUtility.cot("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.cot("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.64209262", StringMathUtility.cot("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.6420926159343307", StringMathUtility.cot("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.6420926159343307030064199865942656202302781139181713791011622804", StringMathUtility.cot("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.64209261593433070300641998659426562023027811391817137910116228042627685683916467219848291976019680465814306596047141573918356963493705933122378784310056202796590177952583993144431226921022120997092394574813060354777658685526661570956826754318872654659780710610492629489626709295081160952483427016354137699541561458952860701107858227259376088670827067970590687137491185081969260425814554198558997437568690607879275252280812126851999661677146531833095334863246223721746553979982097961711441872912451219319661779094", StringMathUtility.cot("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.6420926159343307030064199865942656202302781139181713791011622804262768568391646721984829197601968046581430659604714157391835696349370593312237878431005620279659017795258399314443122692102212099709239457481306035477765868552666157095682675431887265465978071061049262948962670929508116095248342701635413769954156145895286070110785822725937608867082706797059068713749118508196926042581455419855899743756869060787927525228081212685199966167714653183309533486324622372174655397998209796171144187291245121931966177909400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.cot("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0.6420926159343307030064199865942656202302781139181713791011622804", StringMathUtility.cot("1", null));
        
        //custom precision
        Assert.assertEquals("17301038.062283717757555", StringMathUtility.cot(".0000000578", 15));
        Assert.assertEquals("17301038.0622837177576", StringMathUtility.cot(".0000000578", 13));
        Assert.assertEquals("17301038.062283717758", StringMathUtility.cot(".0000000578", 12));
        Assert.assertEquals("17301038.0622837178", StringMathUtility.cot(".0000000578", 10));
        Assert.assertEquals("17301038.062283718", StringMathUtility.cot(".0000000578", 9));
        Assert.assertEquals("17301038.062284", StringMathUtility.cot(".0000000578", 6));
        Assert.assertEquals("17301038.062", StringMathUtility.cot(".0000000578", 3));
        Assert.assertEquals("17301038.06", StringMathUtility.cot(".0000000578", 2));
        Assert.assertEquals("17301038.1", StringMathUtility.cot(".0000000578", 1));
        Assert.assertEquals("-0.879264978264266", StringMathUtility.cot("18.0000000578", 15));
        Assert.assertEquals("-0.8792649782643", StringMathUtility.cot("18.0000000578", 13));
        Assert.assertEquals("-0.879264978264", StringMathUtility.cot("18.0000000578", 12));
        Assert.assertEquals("-0.8792649783", StringMathUtility.cot("18.0000000578", 10));
        Assert.assertEquals("-0.879264978", StringMathUtility.cot("18.0000000578", 9));
        Assert.assertEquals("-0.879265", StringMathUtility.cot("18.0000000578", 6));
        Assert.assertEquals("-0.879", StringMathUtility.cot("18.0000000578", 3));
        Assert.assertEquals("-0.88", StringMathUtility.cot("18.0000000578", 2));
        Assert.assertEquals("-0.9", StringMathUtility.cot("18.0000000578", 1));
        Assert.assertEquals("-7.01525255143403", StringMathUtility.cot("2.99999999999999", 14));
        Assert.assertEquals("-7.0152525514", StringMathUtility.cot("2.99999999999999", 10));
        Assert.assertEquals("-7.01525", StringMathUtility.cot("2.99999999999999", 5));
        Assert.assertEquals("-7.0", StringMathUtility.cot("2.99999999999999", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for cot where input == 0", () ->
                StringMathUtility.cot("0"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.cot(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.cot(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.cot(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.cot("15a5"));
    }
    
    /**
     * JUnit test of acot.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#acot(String, int)
     * @see StringMathUtility#acot(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#acot(String)
     */
    @Test
    public void testAcot() throws Exception {
        //standard
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acot("0"));
        Assert.assertEquals("0.7853981633974483096156608458198757210492923498437764552437361481", StringMathUtility.acot("1"));
        Assert.assertEquals("0.0000000167014245580285302255501651474800847738882726406229587331", StringMathUtility.acot("59875132"));
        Assert.assertEquals("0.0000000000000001114024396301776225097687998785096594298232876751", StringMathUtility.acot("8976464100065468"));
        Assert.assertEquals("2.3561944901923449288469825374596271631478770495313293657312084442", StringMathUtility.acot("-1"));
        Assert.assertEquals("3.1415926368883686804341131577293377367170846254868331803519858593", StringMathUtility.acot("-59875132"));
        Assert.assertEquals("3.1415926535897931270602037531018803744283695208654463911516569172", StringMathUtility.acot("-8976464100065468"));
        
        //decimal
        Assert.assertEquals("1.511915467068459840405083955517703022322411121191880974921366336", StringMathUtility.acot(".058949"));
        Assert.assertEquals("0.7917166935088708281774445580202629287722260063515224015313095139", StringMathUtility.acot(".9874421200001"));
        Assert.assertEquals("1.5707963267492468192313216916397831520358436923515528708391886729", StringMathUtility.acot(".0000000000456498"));
        Assert.assertEquals("1.0565195479757279058703755685347904749678717236568689033438643675", StringMathUtility.acot(".5649871212025644980798794213200031654"));
        Assert.assertEquals("1.6296771865213333980575594277617998618747582781832248460535782563", StringMathUtility.acot("-.058949"));
        Assert.assertEquals("2.3498759600809224102851988252592399554249433930235834194436350784", StringMathUtility.acot("-.9874421200001"));
        Assert.assertEquals("1.5707963268405464192313216916397197321613257070235529501357559194", StringMathUtility.acot("-.0000000000456498"));
        Assert.assertEquals("2.0850731056140653325922678147447124092292976757182369176310802248", StringMathUtility.acot("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acot("0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acot("0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("2", StringMathUtility.acot("0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.57079633", StringMathUtility.acot("0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.5707963267948966", StringMathUtility.acot("0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acot("0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.57079632679489661923132169163975144209858469968755291048747229615390820314310449931401741267105853399107404325664115332354692230477529111586267970406424055872514205135096926055277982231147447746519098221440548783296672306423782411689339158263560095457282428346173017430522716332410669680363012457063686229350330315779408744076046048141462704585768218394629518000566526527441023326069207347597075580471652863518287979597654609305869096630589655255927403723118998137478367594287636244561396909150597456491683668120", StringMathUtility.acot("0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722961539082031431044993140174126710585339910740432566411533235469223047752911158626797040642405587251420513509692605527798223114744774651909822144054878329667230642378241168933915826356009545728242834617301743052271633241066968036301245706368622935033031577940874407604604814146270458576821839462951800056652652744102332606920734759707558047165286351828797959765460930586909663058965525592740372311899813747836759428763624456139690915059745649168366812000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.acot("0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1.5707963267948966192313216916397514420985846996875529104874722962", StringMathUtility.acot("0", null));
        
        //custom precision
        Assert.assertEquals("1.570796268994897", StringMathUtility.acot(".0000000578", 15));
        Assert.assertEquals("1.5707962689949", StringMathUtility.acot(".0000000578", 13));
        Assert.assertEquals("1.570796268995", StringMathUtility.acot(".0000000578", 12));
        Assert.assertEquals("1.5707962690", StringMathUtility.acot(".0000000578", 10));
        Assert.assertEquals("1.570796269", StringMathUtility.acot(".0000000578", 9));
        Assert.assertEquals("1.570796", StringMathUtility.acot(".0000000578", 6));
        Assert.assertEquals("1.571", StringMathUtility.acot(".0000000578", 3));
        Assert.assertEquals("1.57", StringMathUtility.acot(".0000000578", 2));
        Assert.assertEquals("1.6", StringMathUtility.acot(".0000000578", 1));
        Assert.assertEquals("0.055498505067871", StringMathUtility.acot("18.0000000578", 15));
        Assert.assertEquals("0.0554985050679", StringMathUtility.acot("18.0000000578", 13));
        Assert.assertEquals("0.055498505068", StringMathUtility.acot("18.0000000578", 12));
        Assert.assertEquals("0.0554985051", StringMathUtility.acot("18.0000000578", 10));
        Assert.assertEquals("0.055498505", StringMathUtility.acot("18.0000000578", 9));
        Assert.assertEquals("0.055499", StringMathUtility.acot("18.0000000578", 6));
        Assert.assertEquals("0.055", StringMathUtility.acot("18.0000000578", 3));
        Assert.assertEquals("0.06", StringMathUtility.acot("18.0000000578", 2));
        Assert.assertEquals("0.1", StringMathUtility.acot("18.0000000578", 1));
        Assert.assertEquals("0.32175055439664", StringMathUtility.acot("2.99999999999999", 14));
        Assert.assertEquals("0.3217505544", StringMathUtility.acot("2.99999999999999", 10));
        Assert.assertEquals("0.32175", StringMathUtility.acot("2.99999999999999", 5));
        Assert.assertEquals("0.3", StringMathUtility.acot("2.99999999999999", 1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acot(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acot(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acot(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.acot("15a5"));
    }
    
    /**
     * JUnit test of coth.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#coth(String, int)
     * @see StringMathUtility#coth(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#coth(String)
     */
    @Test
    public void testCoth() throws Exception {
        //standard
        Assert.assertEquals("1.3130352854993313036361612469308478329120139412404526555431529676", StringMathUtility.coth("1"));
        Assert.assertEquals("1.000000225070374766852177544388048446482025237819737168908464686", StringMathUtility.coth("8"));
        Assert.assertEquals("1.0000000000000000000000000000434104402260727882397313851923963597", StringMathUtility.coth("33"));
        Assert.assertEquals("-1.3130352854993313036361612469308478329120139412404526555431529676", StringMathUtility.coth("-1"));
        Assert.assertEquals("-1.000000225070374766852177544388048446482025237819737168908464686", StringMathUtility.coth("-8"));
        Assert.assertEquals("-1.0000000000000000000000000000434104402260727882397313851923963597", StringMathUtility.coth("-33"));
        
        //decimal
        Assert.assertEquals("16.9834612961079495353598753450403584506208498848148767421908847728", StringMathUtility.coth(".058949"));
        Assert.assertEquals("1.3222799131007375962694582792897014500316554312690697819110305133", StringMathUtility.coth(".9874421200001"));
        Assert.assertEquals("21905901011.614508716373229121413018238853180156244139973503625328898544226", StringMathUtility.coth(".0000000000456498"));
        Assert.assertEquals("1.954391189048574380771452529002789587020825985489042277667700316", StringMathUtility.coth(".5649871212025644980798794213200031654"));
        Assert.assertEquals("-16.9834612961079495353598753450403584506208498848148767421908847728", StringMathUtility.coth("-.058949"));
        Assert.assertEquals("-1.3222799131007375962694582792897014500316554312690697819110305133", StringMathUtility.coth("-.9874421200001"));
        Assert.assertEquals("-21905901011.614508716373229121413018238853180156244139973503625328898544226", StringMathUtility.coth("-.0000000000456498"));
        Assert.assertEquals("-1.954391189048574380771452529002789587020825985489042277667700316", StringMathUtility.coth("-.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("1.3130352854993313036361612469308478329120139412404526555431529676", StringMathUtility.coth("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.3130352854993313036361612469308478329120139412404526555431529676", StringMathUtility.coth("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.coth("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.31303529", StringMathUtility.coth("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.3130352854993313", StringMathUtility.coth("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.3130352854993313036361612469308478329120139412404526555431529676", StringMathUtility.coth("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.31303528549933130363616124693084783291201394124045265554315296756708427046187438267467924148085630294679470507384482041977039618612671089896960175354961874327866039526774154301931642149012222071169026987217362794495510788635344264314594215127054915455302849022912492878501496724636129939185725560266407256980263388317166110796962326028749323531083074352389608925392978085234472228962687929005848959618480788283481459978814671678868649257103226048738076630787529421237089242315501117484712985991948833155233888840", StringMathUtility.coth("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.3130352854993313036361612469308478329120139412404526555431529675670842704618743826746792414808563029467947050738448204197703961861267108989696017535496187432786603952677415430193164214901222207116902698721736279449551078863534426431459421512705491545530284902291249287850149672463612993918572556026640725698026338831716611079696232602874932353108307435238960892539297808523447222896268792900584895961848078828348145997881467167886864925710322604873807663078752942123708924231550111748471298599194883315523388884000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.coth("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("1.3130352854993313036361612469308478329120139412404526555431529676", StringMathUtility.coth("1", null));
        
        //custom precision
        Assert.assertEquals("17301038.062283756290888", StringMathUtility.coth(".0000000578", 15));
        Assert.assertEquals("17301038.0622837562909", StringMathUtility.coth(".0000000578", 13));
        Assert.assertEquals("17301038.062283756291", StringMathUtility.coth(".0000000578", 12));
        Assert.assertEquals("17301038.0622837563", StringMathUtility.coth(".0000000578", 10));
        Assert.assertEquals("17301038.062283756", StringMathUtility.coth(".0000000578", 9));
        Assert.assertEquals("17301038.062284", StringMathUtility.coth(".0000000578", 6));
        Assert.assertEquals("17301038.062", StringMathUtility.coth(".0000000578", 3));
        Assert.assertEquals("17301038.06", StringMathUtility.coth(".0000000578", 2));
        Assert.assertEquals("17301038.1", StringMathUtility.coth(".0000000578", 1));
        Assert.assertEquals("1.000000000000000", StringMathUtility.coth("18.0000000578", 15));
        Assert.assertEquals("1.0000000000000", StringMathUtility.coth("18.0000000578", 13));
        Assert.assertEquals("1.000000000000", StringMathUtility.coth("18.0000000578", 12));
        Assert.assertEquals("1.0000000000", StringMathUtility.coth("18.0000000578", 10));
        Assert.assertEquals("1.000000000", StringMathUtility.coth("18.0000000578", 9));
        Assert.assertEquals("1.000000", StringMathUtility.coth("18.0000000578", 6));
        Assert.assertEquals("1.000", StringMathUtility.coth("18.0000000578", 3));
        Assert.assertEquals("1.00", StringMathUtility.coth("18.0000000578", 2));
        Assert.assertEquals("1.0", StringMathUtility.coth("18.0000000578", 1));
        Assert.assertEquals("1.00496982331369", StringMathUtility.coth("2.99999999999999", 14));
        Assert.assertEquals("1.0049698233", StringMathUtility.coth("2.99999999999999", 10));
        Assert.assertEquals("1.00497", StringMathUtility.coth("2.99999999999999", 5));
        Assert.assertEquals("1.0", StringMathUtility.coth("2.99999999999999", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for coth where input == 0", () ->
                StringMathUtility.coth("0"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.coth(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.coth(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.coth(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.coth("15a5"));
    }
    
    /**
     * JUnit test of acoth.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#acoth(String, int)
     * @see StringMathUtility#acoth(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#acoth(String)
     */
    @Test
    public void testAcoth() throws Exception {
        //standard
        Assert.assertEquals("0.0000000167014245580285333313201562407054330880167416058415770091", StringMathUtility.acoth("59875132"));
        Assert.assertEquals("0.0000000000000001114024396301776225097687998785105811367387062497", StringMathUtility.acoth("8976464100065468"));
        Assert.assertEquals("-0.0000000167014245580285333313201562407054330880167416058415770091", StringMathUtility.acoth("-59875132"));
        Assert.assertEquals("-0.0000000000000001114024396301776225097687998785105811367387062497", StringMathUtility.acoth("-8976464100065468"));
        
        //decimal
        Assert.assertEquals("1.776639137052226643312329244203206253702357261617447434453137773", StringMathUtility.acoth("1.058949"));
        Assert.assertEquals("0.5535274697970906475737781169691852759772105515454078636538961603", StringMathUtility.acoth("1.9874421200001"));
        Assert.assertEquals("12.2515845353204729622407932653047333390654139382820946125811959398", StringMathUtility.acoth("1.0000000000456498"));
        Assert.assertEquals("0.7564529000318518745129303373120338839458524348113284343101286263", StringMathUtility.acoth("1.5649871212025644980798794213200031654"));
        Assert.assertEquals("-1.776639137052226643312329244203206253702357261617447434453137773", StringMathUtility.acoth("-1.058949"));
        Assert.assertEquals("-0.5535274697970906475737781169691852759772105515454078636538961603", StringMathUtility.acoth("-1.9874421200001"));
        Assert.assertEquals("-12.2515845353204729622407932653047333390654139382820946125811959398", StringMathUtility.acoth("-1.0000000000456498"));
        Assert.assertEquals("-0.7564529000318518745129303373120338839458524348113284343101286263", StringMathUtility.acoth("-1.5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0.5493061443340548456976226184612628523237452789113747258673471668", StringMathUtility.acoth("2", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0.5493061443340548456976226184612628523237452789113747258673471668", StringMathUtility.acoth("2.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", StringMathUtility.acoth("2", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.54930614", StringMathUtility.acoth("2", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.5493061443340548", StringMathUtility.acoth("2", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.5493061443340548456976226184612628523237452789113747258673471668", StringMathUtility.acoth("2", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.54930614433405484569762261846126285232374527891137472586734716681874714660930448343680787740686604439398501453297893287118400211296525991052640093538363870530158138459169068358968684942218047995187128515839795576057279595887533567352747008338779011110158512647344878034505326075282143406901815868664928889118349582739606590907451001505191181506112432637409911299554872624544822902673350442298254287422205950942854382374743353980654291470580108306059200070491275719597438444683992471511278657676648426726476257296", StringMathUtility.acoth("2", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.5493061443340548456976226184612628523237452789113747258673471668187471466093044834368078774068660443939850145329789328711840021129652599105264009353836387053015813845916906835896868494221804799518712851583979557605727959588753356735274700833877901111015851264734487803450532607528214340690181586866492888911834958273960659090745100150519118150611243263740991129955487262454482290267335044229825428742220595094285438237474335398065429147058010830605920007049127571959743844468399247151127865767664842672647625729600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", StringMathUtility.acoth("2", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0.5493061443340548456976226184612628523237452789113747258673471668", StringMathUtility.acoth("2", null));
        
        //custom precision
        Assert.assertEquals("0.055612817376165", StringMathUtility.acoth("18.0000000578", 15));
        Assert.assertEquals("0.0556128173762", StringMathUtility.acoth("18.0000000578", 13));
        Assert.assertEquals("0.055612817376", StringMathUtility.acoth("18.0000000578", 12));
        Assert.assertEquals("0.0556128174", StringMathUtility.acoth("18.0000000578", 10));
        Assert.assertEquals("0.055612817", StringMathUtility.acoth("18.0000000578", 9));
        Assert.assertEquals("0.055613", StringMathUtility.acoth("18.0000000578", 6));
        Assert.assertEquals("0.056", StringMathUtility.acoth("18.0000000578", 3));
        Assert.assertEquals("0.06", StringMathUtility.acoth("18.0000000578", 2));
        Assert.assertEquals("0.1", StringMathUtility.acoth("18.0000000578", 1));
        Assert.assertEquals("0.34657359027997", StringMathUtility.acoth("2.99999999999999", 14));
        Assert.assertEquals("0.3465735903", StringMathUtility.acoth("2.99999999999999", 10));
        Assert.assertEquals("0.34657", StringMathUtility.acoth("2.99999999999999", 5));
        Assert.assertEquals("0.3", StringMathUtility.acoth("2.99999999999999", 1));
        
        //invalid number
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0", () ->
                StringMathUtility.acoth("0"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 1", () ->
                StringMathUtility.acoth("1"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -1", () ->
                StringMathUtility.acoth("-1"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.058949", () ->
                StringMathUtility.acoth(".058949"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.9874421200001", () ->
                StringMathUtility.acoth(".9874421200001"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.0000000000456498", () ->
                StringMathUtility.acoth(".0000000000456498"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.5649871212025644980798794213200031654", () ->
                StringMathUtility.acoth(".5649871212025644980798794213200031654"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -0.058949", () ->
                StringMathUtility.acoth("-.058949"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -0.9874421200001", () ->
                StringMathUtility.acoth("-.9874421200001"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -0.0000000000456498", () ->
                StringMathUtility.acoth("-.0000000000456498"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = -0.5649871212025644980798794213200031654", () ->
                StringMathUtility.acoth("-.5649871212025644980798794213200031654"));
        TestUtils.assertException(ArithmeticException.class, "Illegal input for acoth where input >= -1 and input <= 1; input = 0.0000000578", () ->
                StringMathUtility.acoth(".0000000578"));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acoth(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acoth(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.acoth(null, 1));
        
        TestUtils.assertException(NumberFormatException.class, "Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.acoth("15a5"));
    }
    
    /**
     * JUnit test of pi.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#pi(int)
     * @see StringMathUtility#pi(BigMathUtility.PrecisionMode)
     * @see StringMathUtility#pi()
     */
    @Test
    public void testPi() throws Exception {
        //standard
        Assert.assertEquals("3.141592653589793238462643383279502884197169399375105820974944592", StringMathUtility.pi());
        
        //precision
        Assert.assertEquals("3.141592653589793238462643383279502884197169399375105820974944592", StringMathUtility.pi(BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("3", StringMathUtility.pi(BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("3.1415927", StringMathUtility.pi(BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("3.141592653589793", StringMathUtility.pi(BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("3.141592653589793238462643383279502884197169399375105820974944592", StringMathUtility.pi(BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624", StringMathUtility.pi(BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381964428810975665933446128475648233786783165271201909145648566923460348610454326648213393607260249141273724587006606315588174881520920962829254091715364367892590360011330530548820466521384146951941511609433057270365759591953092186117381932611793105118548074462379962749567351885752724891227938183011949129833673362440656643086021394946395224737190702179860943702770539217176293176752384674818467669405132000568127145263560827785771342757789609173637178721468440901224953430146549585371050792279689258923542019956112129021960864034418159813629774771309960518707211349999998372978049951059731732816096318595024459455346908302642522308253344685035261931188171010003137838752886587533208381420617177669147303598253490428755468731159562863882353787593751957781857780532171226806613001927876611195909216420198938095257201065485863279", StringMathUtility.pi(BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("3.141592653589793238462643383279502884197169399375105820974944592", StringMathUtility.pi(null));
        
        //custom precision
        Assert.assertEquals("3.14159265358979", StringMathUtility.pi(15));
        Assert.assertEquals("3.141592653590", StringMathUtility.pi(13));
        Assert.assertEquals("3.14159265359", StringMathUtility.pi(12));
        Assert.assertEquals("3.141592654", StringMathUtility.pi(10));
        Assert.assertEquals("3.14159265", StringMathUtility.pi(9));
        Assert.assertEquals("3.14159", StringMathUtility.pi(6));
        Assert.assertEquals("3.14", StringMathUtility.pi(3));
        Assert.assertEquals("3.1", StringMathUtility.pi(2));
        Assert.assertEquals("3", StringMathUtility.pi(1));
    }
    
    /**
     * JUnit test of e.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#e(int)
     * @see StringMathUtility#e(BigMathUtility.PrecisionMode)
     * @see StringMathUtility#e()
     */
    @Test
    public void testE() throws Exception {
        //standard
        Assert.assertEquals("2.718281828459045235360287471352662497757247093699959574966967628", StringMathUtility.e());
        
        //precision
        Assert.assertEquals("2.718281828459045235360287471352662497757247093699959574966967628", StringMathUtility.e(BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("3", StringMathUtility.e(BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("2.7182818", StringMathUtility.e(BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("2.718281828459045", StringMathUtility.e(BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("2.718281828459045235360287471352662497757247093699959574966967628", StringMathUtility.e(BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695517027618386062613313845830007520449338265602976067371132007093287091274437470472306969772093101416928368190255151086574637721112523897844250569536967707854499699679468644549059879316368892300987931277361782154", StringMathUtility.e(BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059921817413596629043572900334295260595630738132328627943490763233829880753195251019011573834187930702154089149934884167509244761460668082264800168477411853742345442437107539077744992069551702761838606261331384583000752044933826560297606737113200709328709127443747047230696977209310141692836819025515108657463772111252389784425056953696770785449969967946864454905987931636889230098793127736178215424999229576351482208269895193668033182528869398496465105820939239829488793320362509443117301238197068416140397019837679320683282376464804295311802328782509819455815301756717361332069811250996181881593041690351598888519345807273866738589422879228499892086805825749279610484198444363463244968487560233624827041978623209002160990235304369941849146314093431738143640546253152096183690888707016768396424378140592714563549061303107208510383750510115747704171898610687396965521267154688957035035402123407849819334321068", StringMathUtility.e(BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("2.718281828459045235360287471352662497757247093699959574966967628", StringMathUtility.e(null));
        
        //custom precision
        Assert.assertEquals("2.71828182845905", StringMathUtility.e(15));
        Assert.assertEquals("2.718281828459", StringMathUtility.e(13));
        Assert.assertEquals("2.71828182846", StringMathUtility.e(12));
        Assert.assertEquals("2.718281828", StringMathUtility.e(10));
        Assert.assertEquals("2.71828183", StringMathUtility.e(9));
        Assert.assertEquals("2.71828", StringMathUtility.e(6));
        Assert.assertEquals("2.72", StringMathUtility.e(3));
        Assert.assertEquals("2.7", StringMathUtility.e(2));
        Assert.assertEquals("3", StringMathUtility.e(1));
    }
    
    /**
     * JUnit test of bernoulli.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#bernoulli(int, int)
     * @see StringMathUtility#bernoulli(int, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#bernoulli(int)
     */
    @Test
    public void testBernoulli() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.bernoulli(0));
        Assert.assertEquals("-0.5", StringMathUtility.bernoulli(1));
        Assert.assertEquals("-0.03333333333333333333333333333333333333333333333333333333333333333", StringMathUtility.bernoulli(8));
        Assert.assertEquals("0", StringMathUtility.bernoulli(33));
        Assert.assertEquals("36528776484818123335110430842.97117794486215538847117794486215539", StringMathUtility.bernoulli(54));
        
        //precision
        Assert.assertEquals("0", StringMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", StringMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0", StringMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0", StringMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0", StringMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0", StringMathUtility.bernoulli(5, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("0", StringMathUtility.bernoulli(5, null));
        
        //custom precision
        Assert.assertEquals("0", StringMathUtility.bernoulli(15, 15));
        Assert.assertEquals("0", StringMathUtility.bernoulli(13, 13));
        Assert.assertEquals("-0.253113553114", StringMathUtility.bernoulli(12, 12));
        Assert.assertEquals("0.07575757576", StringMathUtility.bernoulli(10, 10));
        Assert.assertEquals("0", StringMathUtility.bernoulli(9, 9));
        Assert.assertEquals("0.0238095", StringMathUtility.bernoulli(6, 6));
        Assert.assertEquals("0", StringMathUtility.bernoulli(3, 3));
        Assert.assertEquals("0.17", StringMathUtility.bernoulli(2, 2));
        Assert.assertEquals("-0.5", StringMathUtility.bernoulli(1, 1));
    }
    
    /**
     * JUnit test of integralPart.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#integralPart(String)
     */
    @Test
    public void testIntegralPart() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.integralPart("1"));
        Assert.assertEquals("8", StringMathUtility.integralPart("8"));
        Assert.assertEquals("33", StringMathUtility.integralPart("33"));
        Assert.assertEquals("54", StringMathUtility.integralPart("54"));
        Assert.assertEquals("0", StringMathUtility.integralPart(".4154211"));
        Assert.assertEquals("0", StringMathUtility.integralPart(".94108"));
        Assert.assertEquals("0", StringMathUtility.integralPart(".00033"));
        Assert.assertEquals("0", StringMathUtility.integralPart(".421654"));
        Assert.assertEquals("1", StringMathUtility.integralPart("1.4154211"));
        Assert.assertEquals("8", StringMathUtility.integralPart("8.94108"));
        Assert.assertEquals("33", StringMathUtility.integralPart("33.00033"));
        Assert.assertEquals("54", StringMathUtility.integralPart("54.421654"));
        Assert.assertEquals("840000000000000000000000000000000000000000", StringMathUtility.integralPart("840000000000000000000000000000000000000000"));
        Assert.assertEquals("0", StringMathUtility.integralPart("0.0000000000000000000000000000000000000084"));
        Assert.assertEquals("874560984810495456040984654132198077871049809840598711111771980", StringMathUtility.integralPart("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.integralPart(null));
    }
    
    /**
     * JUnit test of fractionalPart.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#fractionalPart(String)
     */
    @Test
    public void testFractionalPart() throws Exception {
        //standard
        Assert.assertEquals("0", StringMathUtility.fractionalPart("1"));
        Assert.assertEquals("0", StringMathUtility.fractionalPart("8"));
        Assert.assertEquals("0", StringMathUtility.fractionalPart("33"));
        Assert.assertEquals("0", StringMathUtility.fractionalPart("54"));
        Assert.assertEquals("0.4154211", StringMathUtility.fractionalPart(".4154211"));
        Assert.assertEquals("0.94108", StringMathUtility.fractionalPart(".94108"));
        Assert.assertEquals("0.00033", StringMathUtility.fractionalPart(".00033"));
        Assert.assertEquals("0.421654", StringMathUtility.fractionalPart(".421654"));
        Assert.assertEquals("0.4154211", StringMathUtility.fractionalPart("1.4154211"));
        Assert.assertEquals("0.94108", StringMathUtility.fractionalPart("8.94108"));
        Assert.assertEquals("0.00033", StringMathUtility.fractionalPart("33.00033"));
        Assert.assertEquals("0.421654", StringMathUtility.fractionalPart("54.421654"));
        Assert.assertEquals("0", StringMathUtility.fractionalPart("840000000000000000000000000000000000000000"));
        Assert.assertEquals("0.0000000000000000000000000000000000000084", StringMathUtility.fractionalPart("0.0000000000000000000000000000000000000084"));
        Assert.assertEquals("0.000158979806579154563234089338737573040498108453021219870845654655086888484521260098", StringMathUtility.fractionalPart("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.fractionalPart(null));
    }
    
    /**
     * JUnit test of significantDigits.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#significantDigits(String)
     */
    @Test
    public void testSignificantDigits() throws Exception {
        //standard
        Assert.assertEquals(1, StringMathUtility.significantDigits("1"));
        Assert.assertEquals(1, StringMathUtility.significantDigits("8"));
        Assert.assertEquals(2, StringMathUtility.significantDigits("33"));
        Assert.assertEquals(2, StringMathUtility.significantDigits("54"));
        Assert.assertEquals(7, StringMathUtility.significantDigits(".4154211"));
        Assert.assertEquals(5, StringMathUtility.significantDigits(".94108"));
        Assert.assertEquals(2, StringMathUtility.significantDigits(".00033"));
        Assert.assertEquals(6, StringMathUtility.significantDigits(".421654"));
        Assert.assertEquals(8, StringMathUtility.significantDigits("1.4154211"));
        Assert.assertEquals(6, StringMathUtility.significantDigits("8.94108"));
        Assert.assertEquals(7, StringMathUtility.significantDigits("33.00033"));
        Assert.assertEquals(8, StringMathUtility.significantDigits("54.421654"));
        Assert.assertEquals(42, StringMathUtility.significantDigits("840000000000000000000000000000000000000000"));
        Assert.assertEquals(2, StringMathUtility.significantDigits("0.0000000000000000000000000000000000000084"));
        Assert.assertEquals(147, StringMathUtility.significantDigits("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.significantDigits(null));
    }
    
    /**
     * JUnit test of mantissa.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#mantissa(String)
     */
    @Test
    public void testMantissa() throws Exception {
        //standard
        Assert.assertEquals("1", StringMathUtility.mantissa("1"));
        Assert.assertEquals("8", StringMathUtility.mantissa("8"));
        Assert.assertEquals("3.3", StringMathUtility.mantissa("33"));
        Assert.assertEquals("5.4", StringMathUtility.mantissa("54"));
        Assert.assertEquals("4.154211", StringMathUtility.mantissa(".4154211"));
        Assert.assertEquals("9.4108", StringMathUtility.mantissa(".94108"));
        Assert.assertEquals("3.3", StringMathUtility.mantissa(".00033"));
        Assert.assertEquals("4.21654", StringMathUtility.mantissa(".421654"));
        Assert.assertEquals("1.4154211", StringMathUtility.mantissa("1.4154211"));
        Assert.assertEquals("8.94108", StringMathUtility.mantissa("8.94108"));
        Assert.assertEquals("3.300033", StringMathUtility.mantissa("33.00033"));
        Assert.assertEquals("5.4421654", StringMathUtility.mantissa("54.421654"));
        Assert.assertEquals("8.40000000000000000000000000000000000000000", StringMathUtility.mantissa("840000000000000000000000000000000000000000"));
        Assert.assertEquals("8.4", StringMathUtility.mantissa("0.0000000000000000000000000000000000000084"));
        Assert.assertEquals("8.74560984810495456040984654132198077871049809840598711111771980000158979806579154563234089338737573040498108453021219870845654655086888484521260098", StringMathUtility.mantissa("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.mantissa(null));
    }
    
    /**
     * JUnit test of exponent.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#exponent(String)
     */
    @Test
    public void testExponent() throws Exception {
        //standard
        Assert.assertEquals(0, StringMathUtility.exponent("1"));
        Assert.assertEquals(0, StringMathUtility.exponent("8"));
        Assert.assertEquals(1, StringMathUtility.exponent("33"));
        Assert.assertEquals(1, StringMathUtility.exponent("54"));
        Assert.assertEquals(-1, StringMathUtility.exponent(".4154211"));
        Assert.assertEquals(-1, StringMathUtility.exponent(".94108"));
        Assert.assertEquals(-4, StringMathUtility.exponent(".00033"));
        Assert.assertEquals(-1, StringMathUtility.exponent(".421654"));
        Assert.assertEquals(0, StringMathUtility.exponent("1.4154211"));
        Assert.assertEquals(0, StringMathUtility.exponent("8.94108"));
        Assert.assertEquals(1, StringMathUtility.exponent("33.00033"));
        Assert.assertEquals(1, StringMathUtility.exponent("54.421654"));
        Assert.assertEquals(41, StringMathUtility.exponent("840000000000000000000000000000000000000000"));
        Assert.assertEquals(-39, StringMathUtility.exponent("0.0000000000000000000000000000000000000084"));
        Assert.assertEquals(62, StringMathUtility.exponent("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.exponent(null));
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#round(String, int)
     * @see StringMathUtility#round(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#round(String)
     */
    @Test
    public void testRound() throws Exception {
        String n;
        
        //standard
        Assert.assertEquals("1", StringMathUtility.round("1"));
        Assert.assertEquals("8", StringMathUtility.round("8"));
        Assert.assertEquals("33", StringMathUtility.round("33"));
        Assert.assertEquals("54", StringMathUtility.round("54"));
        Assert.assertEquals("0", StringMathUtility.round(".4154211"));
        Assert.assertEquals("1", StringMathUtility.round(".94108"));
        Assert.assertEquals("0", StringMathUtility.round(".00033"));
        Assert.assertEquals("0", StringMathUtility.round(".421654"));
        Assert.assertEquals("1", StringMathUtility.round("1.4154211"));
        Assert.assertEquals("9", StringMathUtility.round("8.94108"));
        Assert.assertEquals("33", StringMathUtility.round("33.00033"));
        Assert.assertEquals("54", StringMathUtility.round("54.421654"));
        Assert.assertEquals("840000000000000000000000000000000000000000", StringMathUtility.round("840000000000000000000000000000000000000000"));
        Assert.assertEquals("0", StringMathUtility.round("0.0000000000000000000000000000000000000084"));
        Assert.assertEquals("874560984810495456040984654132198077871049809840598711111771980", StringMathUtility.round("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //precision
        n = "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals(
                "154",
                StringMathUtility.round(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                "154",
                StringMathUtility.round(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                "154.48910622",
                StringMathUtility.round(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685",
                StringMathUtility.round(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210",
                StringMathUtility.round(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                "154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048408",
                StringMathUtility.round(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                StringMathUtility.round(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                "154",
                StringMathUtility.round(n, null));
        
        //custom precision
        n = "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals("154.489106216501269", StringMathUtility.round(n, 15));
        Assert.assertEquals("154.4891062165013", StringMathUtility.round(n, 13));
        Assert.assertEquals("154.489106216501", StringMathUtility.round(n, 12));
        Assert.assertEquals("154.4891062165", StringMathUtility.round(n, 10));
        Assert.assertEquals("154.489106217", StringMathUtility.round(n, 9));
        Assert.assertEquals("154.489106", StringMathUtility.round(n, 6));
        Assert.assertEquals("154.489", StringMathUtility.round(n, 3));
        Assert.assertEquals("154.49", StringMathUtility.round(n, 2));
        Assert.assertEquals("154.5", StringMathUtility.round(n, 1));
        Assert.assertEquals("154", StringMathUtility.round(n, 0));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.round(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.round(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.round(null, 1));
    }
    
    /**
     * JUnit test of abs.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#abs(String, int)
     * @see StringMathUtility#abs(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#abs(String)
     */
    @Test
    public void testAbs() throws Exception {
        String n;
        
        //standard
        Assert.assertEquals("1", StringMathUtility.abs("1"));
        Assert.assertEquals("8", StringMathUtility.abs("8"));
        Assert.assertEquals("33", StringMathUtility.abs("33"));
        Assert.assertEquals("54", StringMathUtility.abs("54"));
        Assert.assertEquals("0.4154211", StringMathUtility.abs(".4154211"));
        Assert.assertEquals("0.94108", StringMathUtility.abs(".94108"));
        Assert.assertEquals("0.00033", StringMathUtility.abs(".00033"));
        Assert.assertEquals("0.421654", StringMathUtility.abs(".421654"));
        Assert.assertEquals("1.4154211", StringMathUtility.abs("1.4154211"));
        Assert.assertEquals("8.94108", StringMathUtility.abs("8.94108"));
        Assert.assertEquals("33.00033", StringMathUtility.abs("33.00033"));
        Assert.assertEquals("54.421654", StringMathUtility.abs("54.421654"));
        Assert.assertEquals("840000000000000000000000000000000000000000", StringMathUtility.abs("840000000000000000000000000000000000000000"));
        Assert.assertEquals("0.0000000000000000000000000000000000000084", StringMathUtility.abs("0.0000000000000000000000000000000000000084"));
        Assert.assertEquals("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098", StringMathUtility.abs("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //negative
        Assert.assertEquals("1", StringMathUtility.abs("-1"));
        Assert.assertEquals("8", StringMathUtility.abs("-8"));
        Assert.assertEquals("33", StringMathUtility.abs("-33"));
        Assert.assertEquals("54", StringMathUtility.abs("-54"));
        Assert.assertEquals("0.4154211", StringMathUtility.abs("-.4154211"));
        Assert.assertEquals("0.94108", StringMathUtility.abs("-.94108"));
        Assert.assertEquals("0.00033", StringMathUtility.abs("-.00033"));
        Assert.assertEquals("0.421654", StringMathUtility.abs("-.421654"));
        Assert.assertEquals("1.4154211", StringMathUtility.abs("-1.4154211"));
        Assert.assertEquals("8.94108", StringMathUtility.abs("-8.94108"));
        Assert.assertEquals("33.00033", StringMathUtility.abs("-33.00033"));
        Assert.assertEquals("54.421654", StringMathUtility.abs("-54.421654"));
        Assert.assertEquals("840000000000000000000000000000000000000000", StringMathUtility.abs("-840000000000000000000000000000000000000000"));
        Assert.assertEquals("0.0000000000000000000000000000000000000084", StringMathUtility.abs("-0.0000000000000000000000000000000000000084"));
        Assert.assertEquals("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098", StringMathUtility.abs("-874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //precision
        n = "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                "154",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                "154.48910622",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                "154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048408",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515",
                StringMathUtility.abs(n, null));
        
        //precision, negative
        n = "-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                "154",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                "154.48910622",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                "154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048408",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                StringMathUtility.abs(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515",
                StringMathUtility.abs(n, null));
        
        //custom precision
        n = "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals("154.489106216501269", StringMathUtility.abs(n, 15));
        Assert.assertEquals("154.4891062165013", StringMathUtility.abs(n, 13));
        Assert.assertEquals("154.489106216501", StringMathUtility.abs(n, 12));
        Assert.assertEquals("154.4891062165", StringMathUtility.abs(n, 10));
        Assert.assertEquals("154.489106217", StringMathUtility.abs(n, 9));
        Assert.assertEquals("154.489106", StringMathUtility.abs(n, 6));
        Assert.assertEquals("154.489", StringMathUtility.abs(n, 3));
        Assert.assertEquals("154.49", StringMathUtility.abs(n, 2));
        Assert.assertEquals("154.5", StringMathUtility.abs(n, 1));
        Assert.assertEquals("154", StringMathUtility.abs(n, 0));
        
        //custom precision, negative
        n = "-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals("154.489106216501269", StringMathUtility.abs(n, 15));
        Assert.assertEquals("154.4891062165013", StringMathUtility.abs(n, 13));
        Assert.assertEquals("154.489106216501", StringMathUtility.abs(n, 12));
        Assert.assertEquals("154.4891062165", StringMathUtility.abs(n, 10));
        Assert.assertEquals("154.489106217", StringMathUtility.abs(n, 9));
        Assert.assertEquals("154.489106", StringMathUtility.abs(n, 6));
        Assert.assertEquals("154.489", StringMathUtility.abs(n, 3));
        Assert.assertEquals("154.49", StringMathUtility.abs(n, 2));
        Assert.assertEquals("154.5", StringMathUtility.abs(n, 1));
        Assert.assertEquals("154", StringMathUtility.abs(n, 0));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.abs(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.abs(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.abs(null, 1));
    }
    
    /**
     * JUnit test of negate.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#negate(String, int)
     * @see StringMathUtility#negate(String, BigMathUtility.PrecisionMode)
     * @see StringMathUtility#negate(String)
     */
    @Test
    public void testNegate() throws Exception {
        String n;
        
        //standard
        Assert.assertEquals("-1", StringMathUtility.negate("1"));
        Assert.assertEquals("-8", StringMathUtility.negate("8"));
        Assert.assertEquals("-33", StringMathUtility.negate("33"));
        Assert.assertEquals("-54", StringMathUtility.negate("54"));
        Assert.assertEquals("-0.4154211", StringMathUtility.negate(".4154211"));
        Assert.assertEquals("-0.94108", StringMathUtility.negate(".94108"));
        Assert.assertEquals("-0.00033", StringMathUtility.negate(".00033"));
        Assert.assertEquals("-0.421654", StringMathUtility.negate(".421654"));
        Assert.assertEquals("-1.4154211", StringMathUtility.negate("1.4154211"));
        Assert.assertEquals("-8.94108", StringMathUtility.negate("8.94108"));
        Assert.assertEquals("-33.00033", StringMathUtility.negate("33.00033"));
        Assert.assertEquals("-54.421654", StringMathUtility.negate("54.421654"));
        Assert.assertEquals("-840000000000000000000000000000000000000000", StringMathUtility.negate("840000000000000000000000000000000000000000"));
        Assert.assertEquals("-0.0000000000000000000000000000000000000084", StringMathUtility.negate("0.0000000000000000000000000000000000000084"));
        Assert.assertEquals("-874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098", StringMathUtility.negate("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //negative
        Assert.assertEquals("1", StringMathUtility.negate("-1"));
        Assert.assertEquals("8", StringMathUtility.negate("-8"));
        Assert.assertEquals("33", StringMathUtility.negate("-33"));
        Assert.assertEquals("54", StringMathUtility.negate("-54"));
        Assert.assertEquals("0.4154211", StringMathUtility.negate("-.4154211"));
        Assert.assertEquals("0.94108", StringMathUtility.negate("-.94108"));
        Assert.assertEquals("0.00033", StringMathUtility.negate("-.00033"));
        Assert.assertEquals("0.421654", StringMathUtility.negate("-.421654"));
        Assert.assertEquals("1.4154211", StringMathUtility.negate("-1.4154211"));
        Assert.assertEquals("8.94108", StringMathUtility.negate("-8.94108"));
        Assert.assertEquals("33.00033", StringMathUtility.negate("-33.00033"));
        Assert.assertEquals("54.421654", StringMathUtility.negate("-54.421654"));
        Assert.assertEquals("840000000000000000000000000000000000000000", StringMathUtility.negate("-840000000000000000000000000000000000000000"));
        Assert.assertEquals("0.0000000000000000000000000000000000000084", StringMathUtility.negate("-0.0000000000000000000000000000000000000084"));
        Assert.assertEquals("874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098", StringMathUtility.negate("-874560984810495456040984654132198077871049809840598711111771980.000158979806579154563234089338737573040498108453021219870845654655086888484521260098"));
        
        //precision
        n = "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals(
                "-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                "-154",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                "-154.48910622",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                "-154.4891062165012685",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                "-154.4891062165012685085610410498409854154201095120408084848770653210",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                "-154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048408",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                "-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                "-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515",
                StringMathUtility.negate(n, null));
        
        //precision, negative
        n = "-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals(
                "154",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals(
                "154.48910622",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals(
                "154.48910621650126850856104104984098541542010951204080848487706532102050948405426540478917101491846546521304840840169548898080845432321215946513218978787904654232154804878917101491846546521304840840169548898080845432154804878917101491846546521304840840169548898080406549804840542654048456804878917101491846546521304840840169548898080845440654980484054265404845619187880654513212159465132189787879046542321548048789171014918465465213048408401695488998705490489498780563215054688442321548048789171014918465465213048408",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                StringMathUtility.negate(n, BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals(
                "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515",
                StringMathUtility.negate(n, null));
        
        //custom precision
        n = "154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals("-154.489106216501269", StringMathUtility.negate(n, 15));
        Assert.assertEquals("-154.4891062165013", StringMathUtility.negate(n, 13));
        Assert.assertEquals("-154.489106216501", StringMathUtility.negate(n, 12));
        Assert.assertEquals("-154.4891062165", StringMathUtility.negate(n, 10));
        Assert.assertEquals("-154.489106217", StringMathUtility.negate(n, 9));
        Assert.assertEquals("-154.489106", StringMathUtility.negate(n, 6));
        Assert.assertEquals("-154.489", StringMathUtility.negate(n, 3));
        Assert.assertEquals("-154.49", StringMathUtility.negate(n, 2));
        Assert.assertEquals("-154.5", StringMathUtility.negate(n, 1));
        Assert.assertEquals("-154", StringMathUtility.negate(n, 0));
        
        //custom precision, negative
        n = "-154.4891062165012685085610410498409854154201095120408084848770653210205094840542654047891710149184654652130484084016954889808084543232121594651321897878790465423215480487891710149184654652130484084016954889808084543215480487891710149184654652130484084016954889808040654980484054265404845680487891710149184654652130484084016954889808084544065498048405426540484561918788065451321215946513218978787904654232154804878917101491846546521304840840169548899870549048949878056321505468844232154804878917101491846546521304840840169548899870549048949878056321505484844535246515";
        Assert.assertEquals("154.489106216501269", StringMathUtility.negate(n, 15));
        Assert.assertEquals("154.4891062165013", StringMathUtility.negate(n, 13));
        Assert.assertEquals("154.489106216501", StringMathUtility.negate(n, 12));
        Assert.assertEquals("154.4891062165", StringMathUtility.negate(n, 10));
        Assert.assertEquals("154.489106217", StringMathUtility.negate(n, 9));
        Assert.assertEquals("154.489106", StringMathUtility.negate(n, 6));
        Assert.assertEquals("154.489", StringMathUtility.negate(n, 3));
        Assert.assertEquals("154.49", StringMathUtility.negate(n, 2));
        Assert.assertEquals("154.5", StringMathUtility.negate(n, 1));
        Assert.assertEquals("154", StringMathUtility.negate(n, 0));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.negate(null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.negate(null, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.negate(null, 1));
    }
    
    /**
     * JUnit test of greaterThan.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#greaterThan(String, String)
     */
    @Test
    public void testGreaterThan() throws Exception {
        //standard
        Assert.assertFalse(StringMathUtility.greaterThan("1", "1"));
        Assert.assertTrue(StringMathUtility.greaterThan("17", "4"));
        Assert.assertFalse(StringMathUtility.greaterThan("9", "177"));
        Assert.assertFalse(StringMathUtility.greaterThan("0", "88"));
        Assert.assertTrue(StringMathUtility.greaterThan("0549", "0"));
        
        //negative
        Assert.assertFalse(StringMathUtility.greaterThan("-1", "1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-17", "4"));
        Assert.assertFalse(StringMathUtility.greaterThan("-9", "177"));
        Assert.assertFalse(StringMathUtility.greaterThan("-0", "88"));
        Assert.assertFalse(StringMathUtility.greaterThan("-0549", "0"));
        Assert.assertTrue(StringMathUtility.greaterThan("1", "-1"));
        Assert.assertTrue(StringMathUtility.greaterThan("17", "-4"));
        Assert.assertTrue(StringMathUtility.greaterThan("9", "-177"));
        Assert.assertTrue(StringMathUtility.greaterThan("0", "-88"));
        Assert.assertTrue(StringMathUtility.greaterThan("0549", "-0"));
        Assert.assertFalse(StringMathUtility.greaterThan("-1", "-1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-17", "-4"));
        Assert.assertTrue(StringMathUtility.greaterThan("-9", "-177"));
        Assert.assertTrue(StringMathUtility.greaterThan("-0", "-88"));
        Assert.assertFalse(StringMathUtility.greaterThan("-0549", "-0"));
        
        //decimal
        Assert.assertTrue(StringMathUtility.greaterThan("1", "0.1"));
        Assert.assertTrue(StringMathUtility.greaterThan("1", ".1"));
        Assert.assertFalse(StringMathUtility.greaterThan(".1", ".1"));
        Assert.assertTrue(StringMathUtility.greaterThan("3.1", "1.9"));
        Assert.assertTrue(StringMathUtility.greaterThan("3.1", "0.1"));
        Assert.assertFalse(StringMathUtility.greaterThan("03.1", "20.1"));
        Assert.assertFalse(StringMathUtility.greaterThan(".578", ".941"));
        Assert.assertFalse(StringMathUtility.greaterThan("0.578", "984.941"));
        Assert.assertFalse(StringMathUtility.greaterThan(".0000000578", ".00941"));
        Assert.assertTrue(StringMathUtility.greaterThan("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertFalse(StringMathUtility.greaterThan("-1", "0.1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-1", ".1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-.1", ".1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-3.1", "1.9"));
        Assert.assertFalse(StringMathUtility.greaterThan("-3.1", "0.1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-03.1", "20.1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-.578", ".941"));
        Assert.assertFalse(StringMathUtility.greaterThan("-0.578", "984.941"));
        Assert.assertFalse(StringMathUtility.greaterThan("-.0000000578", ".00941"));
        Assert.assertFalse(StringMathUtility.greaterThan("-18.0000000578", "2.00941"));
        Assert.assertTrue(StringMathUtility.greaterThan("1", "-0.1"));
        Assert.assertTrue(StringMathUtility.greaterThan("1", "-.1"));
        Assert.assertTrue(StringMathUtility.greaterThan(".1", "-.1"));
        Assert.assertTrue(StringMathUtility.greaterThan("3.1", "-1.9"));
        Assert.assertTrue(StringMathUtility.greaterThan("3.1", "-0.1"));
        Assert.assertTrue(StringMathUtility.greaterThan("03.1", "-20.1"));
        Assert.assertTrue(StringMathUtility.greaterThan(".578", "-.941"));
        Assert.assertTrue(StringMathUtility.greaterThan("0.578", "-984.941"));
        Assert.assertTrue(StringMathUtility.greaterThan(".0000000578", "-.00941"));
        Assert.assertTrue(StringMathUtility.greaterThan("18.0000000578", "-2.00941"));
        Assert.assertFalse(StringMathUtility.greaterThan("-1", "-0.1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-1", "-.1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-.1", "-.1"));
        Assert.assertFalse(StringMathUtility.greaterThan("-3.1", "-1.9"));
        Assert.assertFalse(StringMathUtility.greaterThan("-3.1", "-0.1"));
        Assert.assertTrue(StringMathUtility.greaterThan("-03.1", "-20.1"));
        Assert.assertTrue(StringMathUtility.greaterThan("-.578", "-.941"));
        Assert.assertTrue(StringMathUtility.greaterThan("-0.578", "-984.941"));
        Assert.assertTrue(StringMathUtility.greaterThan("-.0000000578", "-.00941"));
        Assert.assertFalse(StringMathUtility.greaterThan("-18.0000000578", "-2.00941"));
        
        //larger cases
        Assert.assertFalse(StringMathUtility.greaterThan(
                "15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        
        //larger cases, negative
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "-15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.greaterThan("1", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.greaterThan(null, "1"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.greaterThan(null, null));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.greaterThan("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.greaterThan("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.greaterThan("15s5", "a"));
    }
    
    /**
     * JUnit test of lessThan.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#lessThan(String, String)
     */
    @Test
    public void testLessThan() throws Exception {
        //standard
        Assert.assertFalse(StringMathUtility.lessThan("1", "1"));
        Assert.assertFalse(StringMathUtility.lessThan("17", "4"));
        Assert.assertTrue(StringMathUtility.lessThan("9", "177"));
        Assert.assertTrue(StringMathUtility.lessThan("0", "88"));
        Assert.assertFalse(StringMathUtility.lessThan("0549", "0"));
        
        //negative
        Assert.assertTrue(StringMathUtility.lessThan("-1", "1"));
        Assert.assertTrue(StringMathUtility.lessThan("-17", "4"));
        Assert.assertTrue(StringMathUtility.lessThan("-9", "177"));
        Assert.assertTrue(StringMathUtility.lessThan("-0", "88"));
        Assert.assertTrue(StringMathUtility.lessThan("-0549", "0"));
        Assert.assertFalse(StringMathUtility.lessThan("1", "-1"));
        Assert.assertFalse(StringMathUtility.lessThan("17", "-4"));
        Assert.assertFalse(StringMathUtility.lessThan("9", "-177"));
        Assert.assertFalse(StringMathUtility.lessThan("0", "-88"));
        Assert.assertFalse(StringMathUtility.lessThan("0549", "-0"));
        Assert.assertFalse(StringMathUtility.lessThan("-1", "-1"));
        Assert.assertTrue(StringMathUtility.lessThan("-17", "-4"));
        Assert.assertFalse(StringMathUtility.lessThan("-9", "-177"));
        Assert.assertFalse(StringMathUtility.lessThan("-0", "-88"));
        Assert.assertTrue(StringMathUtility.lessThan("-0549", "-0"));
        
        //decimal
        Assert.assertFalse(StringMathUtility.lessThan("1", "0.1"));
        Assert.assertFalse(StringMathUtility.lessThan("1", ".1"));
        Assert.assertFalse(StringMathUtility.lessThan(".1", ".1"));
        Assert.assertFalse(StringMathUtility.lessThan("3.1", "1.9"));
        Assert.assertFalse(StringMathUtility.lessThan("3.1", "0.1"));
        Assert.assertTrue(StringMathUtility.lessThan("03.1", "20.1"));
        Assert.assertTrue(StringMathUtility.lessThan(".578", ".941"));
        Assert.assertTrue(StringMathUtility.lessThan("0.578", "984.941"));
        Assert.assertTrue(StringMathUtility.lessThan(".0000000578", ".00941"));
        Assert.assertFalse(StringMathUtility.lessThan("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertTrue(StringMathUtility.lessThan("-1", "0.1"));
        Assert.assertTrue(StringMathUtility.lessThan("-1", ".1"));
        Assert.assertTrue(StringMathUtility.lessThan("-.1", ".1"));
        Assert.assertTrue(StringMathUtility.lessThan("-3.1", "1.9"));
        Assert.assertTrue(StringMathUtility.lessThan("-3.1", "0.1"));
        Assert.assertTrue(StringMathUtility.lessThan("-03.1", "20.1"));
        Assert.assertTrue(StringMathUtility.lessThan("-.578", ".941"));
        Assert.assertTrue(StringMathUtility.lessThan("-0.578", "984.941"));
        Assert.assertTrue(StringMathUtility.lessThan("-.0000000578", ".00941"));
        Assert.assertTrue(StringMathUtility.lessThan("-18.0000000578", "2.00941"));
        Assert.assertFalse(StringMathUtility.lessThan("1", "-0.1"));
        Assert.assertFalse(StringMathUtility.lessThan("1", "-.1"));
        Assert.assertFalse(StringMathUtility.lessThan(".1", "-.1"));
        Assert.assertFalse(StringMathUtility.lessThan("3.1", "-1.9"));
        Assert.assertFalse(StringMathUtility.lessThan("3.1", "-0.1"));
        Assert.assertFalse(StringMathUtility.lessThan("03.1", "-20.1"));
        Assert.assertFalse(StringMathUtility.lessThan(".578", "-.941"));
        Assert.assertFalse(StringMathUtility.lessThan("0.578", "-984.941"));
        Assert.assertFalse(StringMathUtility.lessThan(".0000000578", "-.00941"));
        Assert.assertFalse(StringMathUtility.lessThan("18.0000000578", "-2.00941"));
        Assert.assertTrue(StringMathUtility.lessThan("-1", "-0.1"));
        Assert.assertTrue(StringMathUtility.lessThan("-1", "-.1"));
        Assert.assertFalse(StringMathUtility.lessThan("-.1", "-.1"));
        Assert.assertTrue(StringMathUtility.lessThan("-3.1", "-1.9"));
        Assert.assertTrue(StringMathUtility.lessThan("-3.1", "-0.1"));
        Assert.assertFalse(StringMathUtility.lessThan("-03.1", "-20.1"));
        Assert.assertFalse(StringMathUtility.lessThan("-.578", "-.941"));
        Assert.assertFalse(StringMathUtility.lessThan("-0.578", "-984.941"));
        Assert.assertFalse(StringMathUtility.lessThan("-.0000000578", "-.00941"));
        Assert.assertTrue(StringMathUtility.lessThan("-18.0000000578", "-2.00941"));
        
        //larger cases
        Assert.assertTrue(StringMathUtility.lessThan(
                "15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        
        //larger cases, negative
        Assert.assertTrue(StringMathUtility.lessThan(
                "-15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "-15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.lessThan("1", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.lessThan(null, "1"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.lessThan(null, null));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.lessThan("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.lessThan("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.lessThan("15s5", "a"));
    }
    
    /**
     * JUnit test of equalTo.
     *
     * @throws Exception When there is an exception.
     * @see StringMathUtility#equalTo(String, String)
     */
    @Test
    public void testEqualTo() throws Exception {
        //standard
        Assert.assertTrue(StringMathUtility.equalTo("1", "1"));
        Assert.assertFalse(StringMathUtility.equalTo("17", "4"));
        Assert.assertFalse(StringMathUtility.equalTo("9", "177"));
        Assert.assertFalse(StringMathUtility.equalTo("0", "88"));
        Assert.assertFalse(StringMathUtility.equalTo("0549", "0"));
        
        //negative
        Assert.assertFalse(StringMathUtility.equalTo("-1", "1"));
        Assert.assertFalse(StringMathUtility.equalTo("-17", "4"));
        Assert.assertFalse(StringMathUtility.equalTo("-9", "177"));
        Assert.assertFalse(StringMathUtility.equalTo("-0", "88"));
        Assert.assertFalse(StringMathUtility.equalTo("-0549", "0"));
        Assert.assertFalse(StringMathUtility.equalTo("1", "-1"));
        Assert.assertFalse(StringMathUtility.equalTo("17", "-4"));
        Assert.assertFalse(StringMathUtility.equalTo("9", "-177"));
        Assert.assertFalse(StringMathUtility.equalTo("0", "-88"));
        Assert.assertFalse(StringMathUtility.equalTo("0549", "-0"));
        Assert.assertTrue(StringMathUtility.equalTo("-1", "-1"));
        Assert.assertFalse(StringMathUtility.equalTo("-17", "-4"));
        Assert.assertFalse(StringMathUtility.equalTo("-9", "-177"));
        Assert.assertFalse(StringMathUtility.equalTo("-0", "-88"));
        Assert.assertFalse(StringMathUtility.equalTo("-0549", "-0"));
        
        //decimal
        Assert.assertFalse(StringMathUtility.equalTo("1", "0.1"));
        Assert.assertFalse(StringMathUtility.equalTo("1", ".1"));
        Assert.assertTrue(StringMathUtility.equalTo(".1", ".1"));
        Assert.assertFalse(StringMathUtility.equalTo("3.1", "1.9"));
        Assert.assertFalse(StringMathUtility.equalTo("3.1", "0.1"));
        Assert.assertFalse(StringMathUtility.equalTo("03.1", "20.1"));
        Assert.assertFalse(StringMathUtility.equalTo(".578", ".941"));
        Assert.assertFalse(StringMathUtility.equalTo("0.578", "984.941"));
        Assert.assertFalse(StringMathUtility.equalTo(".0000000578", ".00941"));
        Assert.assertFalse(StringMathUtility.equalTo("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertFalse(StringMathUtility.equalTo("-1", "0.1"));
        Assert.assertFalse(StringMathUtility.equalTo("-1", ".1"));
        Assert.assertFalse(StringMathUtility.equalTo("-.1", ".1"));
        Assert.assertFalse(StringMathUtility.equalTo("-3.1", "1.9"));
        Assert.assertFalse(StringMathUtility.equalTo("-3.1", "0.1"));
        Assert.assertFalse(StringMathUtility.equalTo("-03.1", "20.1"));
        Assert.assertFalse(StringMathUtility.equalTo("-.578", ".941"));
        Assert.assertFalse(StringMathUtility.equalTo("-0.578", "984.941"));
        Assert.assertFalse(StringMathUtility.equalTo("-.0000000578", ".00941"));
        Assert.assertFalse(StringMathUtility.equalTo("-18.0000000578", "2.00941"));
        Assert.assertFalse(StringMathUtility.equalTo("1", "-0.1"));
        Assert.assertFalse(StringMathUtility.equalTo("1", "-.1"));
        Assert.assertFalse(StringMathUtility.equalTo(".1", "-.1"));
        Assert.assertFalse(StringMathUtility.equalTo("3.1", "-1.9"));
        Assert.assertFalse(StringMathUtility.equalTo("3.1", "-0.1"));
        Assert.assertFalse(StringMathUtility.equalTo("03.1", "-20.1"));
        Assert.assertFalse(StringMathUtility.equalTo(".578", "-.941"));
        Assert.assertFalse(StringMathUtility.equalTo("0.578", "-984.941"));
        Assert.assertFalse(StringMathUtility.equalTo(".0000000578", "-.00941"));
        Assert.assertFalse(StringMathUtility.equalTo("18.0000000578", "-2.00941"));
        Assert.assertFalse(StringMathUtility.equalTo("-1", "-0.1"));
        Assert.assertFalse(StringMathUtility.equalTo("-1", "-.1"));
        Assert.assertTrue(StringMathUtility.equalTo("-.1", "-.1"));
        Assert.assertFalse(StringMathUtility.equalTo("-3.1", "-1.9"));
        Assert.assertFalse(StringMathUtility.equalTo("-3.1", "-0.1"));
        Assert.assertFalse(StringMathUtility.equalTo("-03.1", "-20.1"));
        Assert.assertFalse(StringMathUtility.equalTo("-.578", "-.941"));
        Assert.assertFalse(StringMathUtility.equalTo("-0.578", "-984.941"));
        Assert.assertFalse(StringMathUtility.equalTo("-.0000000578", "-.00941"));
        Assert.assertFalse(StringMathUtility.equalTo("-18.0000000578", "-2.00941"));
        
        //larger cases
        Assert.assertFalse(StringMathUtility.equalTo(
                "15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        
        //larger cases, negative
        Assert.assertFalse(StringMathUtility.equalTo(
                "-15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(StringMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.equalTo("1", null));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.equalTo(null, "1"));
        TestUtils.assertException(NullPointerException.class, () ->
                StringMathUtility.equalTo(null, null));
        
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.equalTo("1", "15s5"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.equalTo("15s5", "1"));
        TestUtils.assertException(NumberFormatException.class, "Character s is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.", () ->
                StringMathUtility.equalTo("15s5", "a"));
    }
    
}
