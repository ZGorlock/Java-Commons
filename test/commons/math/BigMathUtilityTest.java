/*
 * File:    BigMathUtilityTest.java
 * Package: math
 * Author:  Zachary Gill
 */

package commons.math;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of BigMathUtility.
 *
 * @see BigMathUtility
 */
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
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#add(String, String, int)
     */
    @Test
    public void testAdd() throws Exception {
        //standard
        Assert.assertEquals("2", BigMathUtility.add("1", "1"));
        Assert.assertEquals("21", BigMathUtility.add("17", "4"));
        Assert.assertEquals("186", BigMathUtility.add("9", "177"));
        Assert.assertEquals("88", BigMathUtility.add("0", "88"));
        Assert.assertEquals("549", BigMathUtility.add("0549", "0"));
        
        //invalid
        try {
            BigMathUtility.add("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.add("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.add("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertEquals("0", BigMathUtility.add("-1", "1"));
        Assert.assertEquals("-13", BigMathUtility.add("-17", "4"));
        Assert.assertEquals("168", BigMathUtility.add("-9", "177"));
        Assert.assertEquals("88", BigMathUtility.add("-0", "88"));
        Assert.assertEquals("-549", BigMathUtility.add("-0549", "0"));
        Assert.assertEquals("0", BigMathUtility.add("1", "-1"));
        Assert.assertEquals("13", BigMathUtility.add("17", "-4"));
        Assert.assertEquals("-168", BigMathUtility.add("9", "-177"));
        Assert.assertEquals("-88", BigMathUtility.add("0", "-88"));
        Assert.assertEquals("549", BigMathUtility.add("0549", "-0"));
        Assert.assertEquals("-2", BigMathUtility.add("-1", "-1"));
        Assert.assertEquals("-21", BigMathUtility.add("-17", "-4"));
        Assert.assertEquals("-186", BigMathUtility.add("-9", "-177"));
        Assert.assertEquals("-88", BigMathUtility.add("-0", "-88"));
        Assert.assertEquals("-549", BigMathUtility.add("-0549", "-0"));
        
        //decimal
        Assert.assertEquals("1.1", BigMathUtility.add("1", "0.1"));
        Assert.assertEquals("1.1", BigMathUtility.add("1", ".1"));
        Assert.assertEquals("0.2", BigMathUtility.add(".1", ".1"));
        Assert.assertEquals("5.0", BigMathUtility.add("3.1", "1.9"));
        Assert.assertEquals("3.2", BigMathUtility.add("3.1", "0.1"));
        Assert.assertEquals("23.2", BigMathUtility.add("03.1", "20.1"));
        Assert.assertEquals("1.519", BigMathUtility.add(".578", ".941"));
        Assert.assertEquals("985.519", BigMathUtility.add("0.578", "984.941"));
        Assert.assertEquals("0.0094100578", BigMathUtility.add(".0000000578", ".00941"));
        Assert.assertEquals("20.0094100578", BigMathUtility.add("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("-0.9", BigMathUtility.add("-1", "0.1"));
        Assert.assertEquals("-0.9", BigMathUtility.add("-1", ".1"));
        Assert.assertEquals("0.0", BigMathUtility.add("-.1", ".1"));
        Assert.assertEquals("-1.2", BigMathUtility.add("-3.1", "1.9"));
        Assert.assertEquals("-3.0", BigMathUtility.add("-3.1", "0.1"));
        Assert.assertEquals("17.0", BigMathUtility.add("-03.1", "20.1"));
        Assert.assertEquals("0.363", BigMathUtility.add("-.578", ".941"));
        Assert.assertEquals("984.363", BigMathUtility.add("-0.578", "984.941"));
        Assert.assertEquals("0.0094099422", BigMathUtility.add("-.0000000578", ".00941"));
        Assert.assertEquals("-15.9905900578", BigMathUtility.add("-18.0000000578", "2.00941"));
        Assert.assertEquals("0.9", BigMathUtility.add("1", "-0.1"));
        Assert.assertEquals("0.9", BigMathUtility.add("1", "-.1"));
        Assert.assertEquals("0.0", BigMathUtility.add(".1", "-.1"));
        Assert.assertEquals("1.2", BigMathUtility.add("3.1", "-1.9"));
        Assert.assertEquals("3.0", BigMathUtility.add("3.1", "-0.1"));
        Assert.assertEquals("-17.0", BigMathUtility.add("03.1", "-20.1"));
        Assert.assertEquals("-0.363", BigMathUtility.add(".578", "-.941"));
        Assert.assertEquals("-984.363", BigMathUtility.add("0.578", "-984.941"));
        Assert.assertEquals("-0.0094099422", BigMathUtility.add(".0000000578", "-.00941"));
        Assert.assertEquals("15.9905900578", BigMathUtility.add("18.0000000578", "-2.00941"));
        Assert.assertEquals("-1.1", BigMathUtility.add("-1", "-0.1"));
        Assert.assertEquals("-1.1", BigMathUtility.add("-1", "-.1"));
        Assert.assertEquals("-0.2", BigMathUtility.add("-.1", "-.1"));
        Assert.assertEquals("-5.0", BigMathUtility.add("-3.1", "-1.9"));
        Assert.assertEquals("-3.2", BigMathUtility.add("-3.1", "-0.1"));
        Assert.assertEquals("-23.2", BigMathUtility.add("-03.1", "-20.1"));
        Assert.assertEquals("-1.519", BigMathUtility.add("-.578", "-.941"));
        Assert.assertEquals("-985.519", BigMathUtility.add("-0.578", "-984.941"));
        Assert.assertEquals("-0.0094100578", BigMathUtility.add("-.0000000578", "-.00941"));
        Assert.assertEquals("-20.0094100578", BigMathUtility.add("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", BigMathUtility.add("1", "0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.0", BigMathUtility.add("1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.0", BigMathUtility.add("1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", BigMathUtility.add("1", "0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", BigMathUtility.add("1", "0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", BigMathUtility.add("1", "0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.add("1", "0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.add("1", "0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.add("1", "0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //precision, negative
        Assert.assertEquals("-1", BigMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1.0", BigMathUtility.add("-1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", BigMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("-1.00000000", BigMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("-1.0000000000000000", BigMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.add("-1", "0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //custom precision
        Assert.assertEquals("0.0094100578", BigMathUtility.add(".0000000578", ".00941", 10));
        Assert.assertEquals("0.0094101", BigMathUtility.add(".0000000578", ".00941", 7));
        Assert.assertEquals("0.009410", BigMathUtility.add(".0000000578", ".00941", 6));
        Assert.assertEquals("0.009", BigMathUtility.add(".0000000578", ".00941", 3));
        Assert.assertEquals("0.01", BigMathUtility.add(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.add(".0000000578", ".00941", 1));
        Assert.assertEquals("20.0094100578", BigMathUtility.add("18.0000000578", "2.00941", 10));
        Assert.assertEquals("20.0094101", BigMathUtility.add("18.0000000578", "2.00941", 7));
        Assert.assertEquals("20.009410", BigMathUtility.add("18.0000000578", "2.00941", 6));
        Assert.assertEquals("20.009", BigMathUtility.add("18.0000000578", "2.00941", 3));
        Assert.assertEquals("20.01", BigMathUtility.add("18.0000000578", "2.00941", 2));
        Assert.assertEquals("20.0", BigMathUtility.add("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.add("2.99999999999999", "0", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.add("2.99999999999999", "0", 10));
        Assert.assertEquals("3.00000", BigMathUtility.add("2.99999999999999", "0", 5));
        Assert.assertEquals("3.0", BigMathUtility.add("2.99999999999999", "0", 1));
        
        //custom precision, negative
        Assert.assertEquals("0.0094099422", BigMathUtility.add("-.0000000578", ".00941", 10));
        Assert.assertEquals("0.0094099", BigMathUtility.add("-.0000000578", ".00941", 7));
        Assert.assertEquals("0.009410", BigMathUtility.add("-.0000000578", ".00941", 6));
        Assert.assertEquals("0.009", BigMathUtility.add("-.0000000578", ".00941", 3));
        Assert.assertEquals("0.01", BigMathUtility.add("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.add("-.0000000578", ".00941", 1));
        Assert.assertEquals("-15.9905900578", BigMathUtility.add("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-15.9905901", BigMathUtility.add("-18.0000000578", "2.00941", 7));
        Assert.assertEquals("-15.991", BigMathUtility.add("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-15.99", BigMathUtility.add("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-16.0", BigMathUtility.add("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-2.99999999999999", BigMathUtility.add("-2.99999999999999", "0", 14));
        Assert.assertEquals("-3.0000000000", BigMathUtility.add("-2.99999999999999", "0", 10));
        Assert.assertEquals("-3.00000", BigMathUtility.add("-2.99999999999999", "0", 5));
        Assert.assertEquals("-3.0", BigMathUtility.add("-2.99999999999999", "0", 1));
        Assert.assertEquals("-0.0094099422", BigMathUtility.add(".0000000578", "-.00941", 10));
        Assert.assertEquals("-0.0094099", BigMathUtility.add(".0000000578", "-.00941", 7));
        Assert.assertEquals("-0.009410", BigMathUtility.add("-.0000000578", "-.00941", 6));
        Assert.assertEquals("-0.009", BigMathUtility.add(".0000000578", "-.00941", 3));
        Assert.assertEquals("-0.01", BigMathUtility.add(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.add(".0000000578", "-.00941", 1));
        Assert.assertEquals("15.9905900578", BigMathUtility.add("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("15.9905901", BigMathUtility.add("18.0000000578", "-2.00941", 7));
        Assert.assertEquals("15.990590", BigMathUtility.add("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("15.991", BigMathUtility.add("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("15.99", BigMathUtility.add("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("16.0", BigMathUtility.add("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.add("2.99999999999999", "-0", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.add("2.99999999999999", "-0", 10));
        Assert.assertEquals("3.00000", BigMathUtility.add("2.99999999999999", "-0", 5));
        Assert.assertEquals("3.0", BigMathUtility.add("2.99999999999999", "-0", 1));
        Assert.assertEquals("-0.0094100578", BigMathUtility.add("-.0000000578", "-.00941", 10));
        Assert.assertEquals("-0.0094101", BigMathUtility.add("-.0000000578", "-.00941", 7));
        Assert.assertEquals("-0.009410", BigMathUtility.add("-.0000000578", "-.00941", 6));
        Assert.assertEquals("-0.009", BigMathUtility.add("-.0000000578", "-.00941", 3));
        Assert.assertEquals("-0.01", BigMathUtility.add("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.add("-.0000000578", "-.00941", 1));
        Assert.assertEquals("-20.0094100578", BigMathUtility.add("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-20.0094101", BigMathUtility.add("-18.0000000578", "-2.00941", 7));
        Assert.assertEquals("-20.009410", BigMathUtility.add("-18.0000000578", "-2.00941", 6));
        Assert.assertEquals("-20.009", BigMathUtility.add("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-20.01", BigMathUtility.add("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-20.0", BigMathUtility.add("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-2.99999999999999", BigMathUtility.add("-2.99999999999999", "-0", 14));
        Assert.assertEquals("-3.0000000000", BigMathUtility.add("-2.99999999999999", "-0", 10));
        Assert.assertEquals("-3.00000", BigMathUtility.add("-2.99999999999999", "-0", 5));
        Assert.assertEquals("-3.0", BigMathUtility.add("-2.99999999999999", "-0", 1));
        
        //larger cases
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829",
                BigMathUtility.add(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829",
                BigMathUtility.add(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.add(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.add(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.add(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.add(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299",
                BigMathUtility.add(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299",
                BigMathUtility.add(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.add(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.add(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.add(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.add(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299",
                BigMathUtility.add(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299",
                BigMathUtility.add(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.add(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.add(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.add(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.add(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829",
                BigMathUtility.add(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829",
                BigMathUtility.add(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.add(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.add(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.add(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.add(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
    }
    
    /**
     * JUnit test of subtract.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#subtract(String, String, int)
     */
    @Test
    public void testSubtract() throws Exception {
        //standard
        Assert.assertEquals("0", BigMathUtility.subtract("1", "1"));
        Assert.assertEquals("13", BigMathUtility.subtract("17", "4"));
        Assert.assertEquals("-168", BigMathUtility.subtract("9", "177"));
        Assert.assertEquals("-88", BigMathUtility.subtract("0", "88"));
        Assert.assertEquals("549", BigMathUtility.subtract("0549", "0"));
        
        //invalid
        try {
            BigMathUtility.subtract("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.subtract("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.subtract("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertEquals("-2", BigMathUtility.subtract("-1", "1"));
        Assert.assertEquals("-21", BigMathUtility.subtract("-17", "4"));
        Assert.assertEquals("-186", BigMathUtility.subtract("-9", "177"));
        Assert.assertEquals("-88", BigMathUtility.subtract("-0", "88"));
        Assert.assertEquals("-549", BigMathUtility.subtract("-0549", "0"));
        Assert.assertEquals("2", BigMathUtility.subtract("1", "-1"));
        Assert.assertEquals("21", BigMathUtility.subtract("17", "-4"));
        Assert.assertEquals("186", BigMathUtility.subtract("9", "-177"));
        Assert.assertEquals("88", BigMathUtility.subtract("0", "-88"));
        Assert.assertEquals("549", BigMathUtility.subtract("0549", "-0"));
        Assert.assertEquals("0", BigMathUtility.subtract("-1", "-1"));
        Assert.assertEquals("-13", BigMathUtility.subtract("-17", "-4"));
        Assert.assertEquals("168", BigMathUtility.subtract("-9", "-177"));
        Assert.assertEquals("88", BigMathUtility.subtract("-0", "-88"));
        Assert.assertEquals("-549", BigMathUtility.subtract("-0549", "-0"));
        
        //decimal
        Assert.assertEquals("0.9", BigMathUtility.subtract("1", "0.1"));
        Assert.assertEquals("0.9", BigMathUtility.subtract("1", ".1"));
        Assert.assertEquals("0.0", BigMathUtility.subtract(".1", ".1"));
        Assert.assertEquals("1.2", BigMathUtility.subtract("3.1", "1.9"));
        Assert.assertEquals("3.0", BigMathUtility.subtract("3.1", "0.1"));
        Assert.assertEquals("-17.0", BigMathUtility.subtract("03.1", "20.1"));
        Assert.assertEquals("-0.363", BigMathUtility.subtract(".578", ".941"));
        Assert.assertEquals("-984.363", BigMathUtility.subtract("0.578", "984.941"));
        Assert.assertEquals("-0.0094099422", BigMathUtility.subtract(".0000000578", ".00941"));
        Assert.assertEquals("15.9905900578", BigMathUtility.subtract("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("-1.1", BigMathUtility.subtract("-1", "0.1"));
        Assert.assertEquals("-1.1", BigMathUtility.subtract("-1", ".1"));
        Assert.assertEquals("-0.2", BigMathUtility.subtract("-.1", ".1"));
        Assert.assertEquals("-5.0", BigMathUtility.subtract("-3.1", "1.9"));
        Assert.assertEquals("-3.2", BigMathUtility.subtract("-3.1", "0.1"));
        Assert.assertEquals("-23.2", BigMathUtility.subtract("-03.1", "20.1"));
        Assert.assertEquals("-1.519", BigMathUtility.subtract("-.578", ".941"));
        Assert.assertEquals("-985.519", BigMathUtility.subtract("-0.578", "984.941"));
        Assert.assertEquals("-0.0094100578", BigMathUtility.subtract("-.0000000578", ".00941"));
        Assert.assertEquals("-20.0094100578", BigMathUtility.subtract("-18.0000000578", "2.00941"));
        Assert.assertEquals("1.1", BigMathUtility.subtract("1", "-0.1"));
        Assert.assertEquals("1.1", BigMathUtility.subtract("1", "-.1"));
        Assert.assertEquals("0.2", BigMathUtility.subtract(".1", "-.1"));
        Assert.assertEquals("5.0", BigMathUtility.subtract("3.1", "-1.9"));
        Assert.assertEquals("3.2", BigMathUtility.subtract("3.1", "-0.1"));
        Assert.assertEquals("23.2", BigMathUtility.subtract("03.1", "-20.1"));
        Assert.assertEquals("1.519", BigMathUtility.subtract(".578", "-.941"));
        Assert.assertEquals("985.519", BigMathUtility.subtract("0.578", "-984.941"));
        Assert.assertEquals("0.0094100578", BigMathUtility.subtract(".0000000578", "-.00941"));
        Assert.assertEquals("20.0094100578", BigMathUtility.subtract("18.0000000578", "-2.00941"));
        Assert.assertEquals("-0.9", BigMathUtility.subtract("-1", "-0.1"));
        Assert.assertEquals("-0.9", BigMathUtility.subtract("-1", "-.1"));
        Assert.assertEquals("0.0", BigMathUtility.subtract("-.1", "-.1"));
        Assert.assertEquals("-1.2", BigMathUtility.subtract("-3.1", "-1.9"));
        Assert.assertEquals("-3.0", BigMathUtility.subtract("-3.1", "-0.1"));
        Assert.assertEquals("17.0", BigMathUtility.subtract("-03.1", "-20.1"));
        Assert.assertEquals("0.363", BigMathUtility.subtract("-.578", "-.941"));
        Assert.assertEquals("984.363", BigMathUtility.subtract("-0.578", "-984.941"));
        Assert.assertEquals("0.0094099422", BigMathUtility.subtract("-.0000000578", "-.00941"));
        Assert.assertEquals("-15.9905900578", BigMathUtility.subtract("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", BigMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.0", BigMathUtility.subtract("1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", BigMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", BigMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", BigMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.subtract("1", "0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //precision, negative
        Assert.assertEquals("-1", BigMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1.0", BigMathUtility.subtract("-1.0", "0.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", BigMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("-1.00000000", BigMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("-1.0000000000000000", BigMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.subtract("-1", "0", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //custom precision
        Assert.assertEquals("-0.0094099422", BigMathUtility.subtract(".0000000578", ".00941", 10));
        Assert.assertEquals("-0.0094099", BigMathUtility.subtract(".0000000578", ".00941", 7));
        Assert.assertEquals("-0.009410", BigMathUtility.subtract("-.0000000578", ".00941", 6));
        Assert.assertEquals("-0.009", BigMathUtility.subtract(".0000000578", ".00941", 3));
        Assert.assertEquals("-0.01", BigMathUtility.subtract(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.subtract(".0000000578", ".00941", 1));
        Assert.assertEquals("15.9905900578", BigMathUtility.subtract("18.0000000578", "2.00941", 10));
        Assert.assertEquals("15.9905901", BigMathUtility.subtract("18.0000000578", "2.00941", 7));
        Assert.assertEquals("15.990590", BigMathUtility.subtract("18.0000000578", "2.00941", 6));
        Assert.assertEquals("15.991", BigMathUtility.subtract("18.0000000578", "2.00941", 3));
        Assert.assertEquals("15.99", BigMathUtility.subtract("18.0000000578", "2.00941", 2));
        Assert.assertEquals("16.0", BigMathUtility.subtract("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.subtract("2.99999999999999", "0", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.subtract("2.99999999999999", "0", 10));
        Assert.assertEquals("3.00000", BigMathUtility.subtract("2.99999999999999", "0", 5));
        Assert.assertEquals("3.0", BigMathUtility.subtract("2.99999999999999", "0", 1));
        
        //custom precision, negative
        Assert.assertEquals("-0.0094100578", BigMathUtility.subtract("-.0000000578", ".00941", 10));
        Assert.assertEquals("-0.0094101", BigMathUtility.subtract("-.0000000578", ".00941", 7));
        Assert.assertEquals("-0.009410", BigMathUtility.subtract("-.0000000578", ".00941", 6));
        Assert.assertEquals("-0.009", BigMathUtility.subtract("-.0000000578", ".00941", 3));
        Assert.assertEquals("-0.01", BigMathUtility.subtract("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.subtract("-.0000000578", ".00941", 1));
        Assert.assertEquals("-20.0094100578", BigMathUtility.subtract("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-20.0094101", BigMathUtility.subtract("-18.0000000578", "2.00941", 7));
        Assert.assertEquals("-20.009410", BigMathUtility.subtract("-18.0000000578", "2.00941", 6));
        Assert.assertEquals("-20.009", BigMathUtility.subtract("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-20.01", BigMathUtility.subtract("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-20.0", BigMathUtility.subtract("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-2.99999999999999", BigMathUtility.subtract("-2.99999999999999", "0", 14));
        Assert.assertEquals("-3.0000000000", BigMathUtility.subtract("-2.99999999999999", "0", 10));
        Assert.assertEquals("-3.00000", BigMathUtility.subtract("-2.99999999999999", "0", 5));
        Assert.assertEquals("-3.0", BigMathUtility.subtract("-2.99999999999999", "0", 1));
        Assert.assertEquals("0.0094100578", BigMathUtility.subtract(".0000000578", "-.00941", 10));
        Assert.assertEquals("0.0094101", BigMathUtility.subtract(".0000000578", "-.00941", 7));
        Assert.assertEquals("0.009410", BigMathUtility.subtract(".0000000578", "-.00941", 6));
        Assert.assertEquals("0.009", BigMathUtility.subtract(".0000000578", "-.00941", 3));
        Assert.assertEquals("0.01", BigMathUtility.subtract(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.subtract(".0000000578", "-.00941", 1));
        Assert.assertEquals("20.0094100578", BigMathUtility.subtract("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("20.0094101", BigMathUtility.subtract("18.0000000578", "-2.00941", 7));
        Assert.assertEquals("20.009410", BigMathUtility.subtract("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("20.009", BigMathUtility.subtract("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("20.01", BigMathUtility.subtract("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("20.0", BigMathUtility.subtract("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.subtract("2.99999999999999", "-0", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.subtract("2.99999999999999", "-0", 10));
        Assert.assertEquals("3.00000", BigMathUtility.subtract("2.99999999999999", "-0", 5));
        Assert.assertEquals("3.0", BigMathUtility.subtract("2.99999999999999", "-0", 1));
        Assert.assertEquals("0.0094099422", BigMathUtility.subtract("-.0000000578", "-.00941", 10));
        Assert.assertEquals("0.0094099", BigMathUtility.subtract("-.0000000578", "-.00941", 7));
        Assert.assertEquals("0.009410", BigMathUtility.subtract("-.0000000578", "-.00941", 6));
        Assert.assertEquals("0.009", BigMathUtility.subtract("-.0000000578", "-.00941", 3));
        Assert.assertEquals("0.01", BigMathUtility.subtract("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.subtract("-.0000000578", "-.00941", 1));
        Assert.assertEquals("-15.9905900578", BigMathUtility.subtract("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-15.9905901", BigMathUtility.subtract("-18.0000000578", "-2.00941", 7));
        Assert.assertEquals("-15.991", BigMathUtility.subtract("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-15.99", BigMathUtility.subtract("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-16.0", BigMathUtility.subtract("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-2.99999999999999", BigMathUtility.subtract("-2.99999999999999", "-0", 14));
        Assert.assertEquals("-3.0000000000", BigMathUtility.subtract("-2.99999999999999", "-0", 10));
        Assert.assertEquals("-3.00000", BigMathUtility.subtract("-2.99999999999999", "-0", 5));
        Assert.assertEquals("-3.0", BigMathUtility.subtract("-2.99999999999999", "-0", 1));
        
        //larger cases
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299",
                BigMathUtility.subtract(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299",
                BigMathUtility.subtract(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.subtract(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.subtract(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.subtract(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.subtract(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829",
                BigMathUtility.subtract(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829",
                BigMathUtility.subtract(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.subtract(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.subtract(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.subtract(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.subtract(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829",
                BigMathUtility.subtract(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829",
                BigMathUtility.subtract(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.subtract(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.subtract(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.subtract(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                BigMathUtility.subtract(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299",
                BigMathUtility.subtract(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299",
                BigMathUtility.subtract(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.subtract(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.subtract(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.subtract(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-5967945689763137224198691854380377528607990117303836116299.54905498489156465409400000098965561820578927208466775576507508609533345900000000000000000000001",
                BigMathUtility.subtract(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
    }
    
    /**
     * JUnit test of multiply.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#multiply(String, String, int)
     */
    @Test
    public void testMultiply() throws Exception {
        //standard
        Assert.assertEquals("1", BigMathUtility.multiply("1", "1"));
        Assert.assertEquals("68", BigMathUtility.multiply("17", "4"));
        Assert.assertEquals("1593", BigMathUtility.multiply("9", "177"));
        Assert.assertEquals("0", BigMathUtility.multiply("0", "88"));
        Assert.assertEquals("0", BigMathUtility.multiply("0549", "0"));
        
        //invalid
        try {
            BigMathUtility.multiply("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.multiply("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.multiply("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertEquals("-1", BigMathUtility.multiply("-1", "1"));
        Assert.assertEquals("-68", BigMathUtility.multiply("-17", "4"));
        Assert.assertEquals("-1593", BigMathUtility.multiply("-9", "177"));
        Assert.assertEquals("0", BigMathUtility.multiply("-0", "88"));
        Assert.assertEquals("0", BigMathUtility.multiply("-0549", "0"));
        Assert.assertEquals("-1", BigMathUtility.multiply("1", "-1"));
        Assert.assertEquals("-68", BigMathUtility.multiply("17", "-4"));
        Assert.assertEquals("-1593", BigMathUtility.multiply("9", "-177"));
        Assert.assertEquals("0", BigMathUtility.multiply("0", "-88"));
        Assert.assertEquals("0", BigMathUtility.multiply("0549", "-0"));
        Assert.assertEquals("1", BigMathUtility.multiply("-1", "-1"));
        Assert.assertEquals("68", BigMathUtility.multiply("-17", "-4"));
        Assert.assertEquals("1593", BigMathUtility.multiply("-9", "-177"));
        Assert.assertEquals("0", BigMathUtility.multiply("-0", "-88"));
        Assert.assertEquals("0", BigMathUtility.multiply("-0549", "-0"));
        
        //decimal
        Assert.assertEquals("0.1", BigMathUtility.multiply("1", "0.1"));
        Assert.assertEquals("0.1", BigMathUtility.multiply("1", ".1"));
        Assert.assertEquals("0.01", BigMathUtility.multiply(".1", ".1"));
        Assert.assertEquals("5.89", BigMathUtility.multiply("3.1", "1.9"));
        Assert.assertEquals("0.31", BigMathUtility.multiply("3.1", "0.1"));
        Assert.assertEquals("62.31", BigMathUtility.multiply("03.1", "20.1"));
        Assert.assertEquals("0.543898", BigMathUtility.multiply(".578", ".941"));
        Assert.assertEquals("569.295898", BigMathUtility.multiply("0.578", "984.941"));
        Assert.assertEquals("0.000000000543898", BigMathUtility.multiply(".0000000578", ".00941"));
        Assert.assertEquals("36.169380116143898", BigMathUtility.multiply("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("-0.1", BigMathUtility.multiply("-1", "0.1"));
        Assert.assertEquals("-0.1", BigMathUtility.multiply("-1", ".1"));
        Assert.assertEquals("-0.01", BigMathUtility.multiply("-.1", ".1"));
        Assert.assertEquals("-5.89", BigMathUtility.multiply("-3.1", "1.9"));
        Assert.assertEquals("-0.31", BigMathUtility.multiply("-3.1", "0.1"));
        Assert.assertEquals("-62.31", BigMathUtility.multiply("-03.1", "20.1"));
        Assert.assertEquals("-0.543898", BigMathUtility.multiply("-.578", ".941"));
        Assert.assertEquals("-569.295898", BigMathUtility.multiply("-0.578", "984.941"));
        Assert.assertEquals("-0.000000000543898", BigMathUtility.multiply("-.0000000578", ".00941"));
        Assert.assertEquals("-36.169380116143898", BigMathUtility.multiply("-18.0000000578", "2.00941"));
        Assert.assertEquals("-0.1", BigMathUtility.multiply("1", "-0.1"));
        Assert.assertEquals("-0.1", BigMathUtility.multiply("1", "-.1"));
        Assert.assertEquals("-0.01", BigMathUtility.multiply(".1", "-.1"));
        Assert.assertEquals("-5.89", BigMathUtility.multiply("3.1", "-1.9"));
        Assert.assertEquals("-0.31", BigMathUtility.multiply("3.1", "-0.1"));
        Assert.assertEquals("-62.31", BigMathUtility.multiply("03.1", "-20.1"));
        Assert.assertEquals("-0.543898", BigMathUtility.multiply(".578", "-.941"));
        Assert.assertEquals("-569.295898", BigMathUtility.multiply("0.578", "-984.941"));
        Assert.assertEquals("-0.000000000543898", BigMathUtility.multiply(".0000000578", "-.00941"));
        Assert.assertEquals("-36.169380116143898", BigMathUtility.multiply("18.0000000578", "-2.00941"));
        Assert.assertEquals("0.1", BigMathUtility.multiply("-1", "-0.1"));
        Assert.assertEquals("0.1", BigMathUtility.multiply("-1", "-.1"));
        Assert.assertEquals("0.01", BigMathUtility.multiply("-.1", "-.1"));
        Assert.assertEquals("5.89", BigMathUtility.multiply("-3.1", "-1.9"));
        Assert.assertEquals("0.31", BigMathUtility.multiply("-3.1", "-0.1"));
        Assert.assertEquals("62.31", BigMathUtility.multiply("-03.1", "-20.1"));
        Assert.assertEquals("0.543898", BigMathUtility.multiply("-.578", "-.941"));
        Assert.assertEquals("569.295898", BigMathUtility.multiply("-0.578", "-984.941"));
        Assert.assertEquals("0.000000000543898", BigMathUtility.multiply("-.0000000578", "-.00941"));
        Assert.assertEquals("36.169380116143898", BigMathUtility.multiply("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", BigMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1.00", BigMathUtility.multiply("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", BigMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", BigMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", BigMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.multiply("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //precision, negative
        Assert.assertEquals("-1", BigMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1.00", BigMathUtility.multiply("-1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", BigMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("-1.00000000", BigMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("-1.0000000000000000", BigMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.multiply("-1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //custom precision
        Assert.assertEquals("0.000000000543898", BigMathUtility.multiply(".0000000578", ".00941", 15));
        Assert.assertEquals("0.0000000005439", BigMathUtility.multiply(".0000000578", ".00941", 13));
        Assert.assertEquals("0.000000000544", BigMathUtility.multiply(".0000000578", ".00941", 12));
        Assert.assertEquals("0.0000000005", BigMathUtility.multiply(".0000000578", ".00941", 10));
        Assert.assertEquals("0.000000001", BigMathUtility.multiply(".0000000578", ".00941", 9));
        Assert.assertEquals("0.000000", BigMathUtility.multiply(".0000000578", ".00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.multiply(".0000000578", ".00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.multiply(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.multiply(".0000000578", ".00941", 1));
        Assert.assertEquals("36.169380116143898", BigMathUtility.multiply("18.0000000578", "2.00941", 15));
        Assert.assertEquals("36.1693801161439", BigMathUtility.multiply("18.0000000578", "2.00941", 13));
        Assert.assertEquals("36.169380116144", BigMathUtility.multiply("18.0000000578", "2.00941", 12));
        Assert.assertEquals("36.1693801161", BigMathUtility.multiply("18.0000000578", "2.00941", 10));
        Assert.assertEquals("36.169380116", BigMathUtility.multiply("18.0000000578", "2.00941", 9));
        Assert.assertEquals("36.169380", BigMathUtility.multiply("18.0000000578", "2.00941", 6));
        Assert.assertEquals("36.169", BigMathUtility.multiply("18.0000000578", "2.00941", 3));
        Assert.assertEquals("36.17", BigMathUtility.multiply("18.0000000578", "2.00941", 2));
        Assert.assertEquals("36.2", BigMathUtility.multiply("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.multiply("2.99999999999999", "1", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.multiply("2.99999999999999", "1", 10));
        Assert.assertEquals("3.00000", BigMathUtility.multiply("2.99999999999999", "1", 5));
        Assert.assertEquals("3.0", BigMathUtility.multiply("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("-0.000000000543898", BigMathUtility.multiply("-.0000000578", ".00941", 15));
        Assert.assertEquals("-0.0000000005439", BigMathUtility.multiply("-.0000000578", ".00941", 13));
        Assert.assertEquals("-0.000000000544", BigMathUtility.multiply("-.0000000578", ".00941", 12));
        Assert.assertEquals("-0.0000000005", BigMathUtility.multiply("-.0000000578", ".00941", 10));
        Assert.assertEquals("-0.000000001", BigMathUtility.multiply("-.0000000578", ".00941", 9));
        Assert.assertEquals("0.000000", BigMathUtility.multiply("-.0000000578", ".00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.multiply("-.0000000578", ".00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.multiply("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.multiply("-.0000000578", ".00941", 1));
        Assert.assertEquals("-36.169380116143898", BigMathUtility.multiply("-18.0000000578", "2.00941", 15));
        Assert.assertEquals("-36.1693801161439", BigMathUtility.multiply("-18.0000000578", "2.00941", 13));
        Assert.assertEquals("-36.169380116144", BigMathUtility.multiply("-18.0000000578", "2.00941", 12));
        Assert.assertEquals("-36.1693801161", BigMathUtility.multiply("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-36.169380116", BigMathUtility.multiply("-18.0000000578", "2.00941", 9));
        Assert.assertEquals("-36.169380", BigMathUtility.multiply("-18.0000000578", "2.00941", 6));
        Assert.assertEquals("-36.169", BigMathUtility.multiply("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-36.17", BigMathUtility.multiply("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-36.2", BigMathUtility.multiply("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-2.99999999999999", BigMathUtility.multiply("-2.99999999999999", "1", 14));
        Assert.assertEquals("-3.0000000000", BigMathUtility.multiply("-2.99999999999999", "1", 10));
        Assert.assertEquals("-3.00000", BigMathUtility.multiply("-2.99999999999999", "1", 5));
        Assert.assertEquals("-3.0", BigMathUtility.multiply("-2.99999999999999", "1", 1));
        Assert.assertEquals("-0.000000000543898", BigMathUtility.multiply(".0000000578", "-.00941", 15));
        Assert.assertEquals("-0.0000000005439", BigMathUtility.multiply(".0000000578", "-.00941", 13));
        Assert.assertEquals("-0.000000000544", BigMathUtility.multiply(".0000000578", "-.00941", 12));
        Assert.assertEquals("-0.0000000005", BigMathUtility.multiply(".0000000578", "-.00941", 10));
        Assert.assertEquals("-0.000000001", BigMathUtility.multiply(".0000000578", "-.00941", 9));
        Assert.assertEquals("0.000000", BigMathUtility.multiply(".0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.multiply(".0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.multiply(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.multiply(".0000000578", "-.00941", 1));
        Assert.assertEquals("-36.169380116143898", BigMathUtility.multiply("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("-36.1693801161439", BigMathUtility.multiply("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("-36.169380116144", BigMathUtility.multiply("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("-36.1693801161", BigMathUtility.multiply("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-36.169380116", BigMathUtility.multiply("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("-36.169380", BigMathUtility.multiply("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("-36.169", BigMathUtility.multiply("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-36.17", BigMathUtility.multiply("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-36.2", BigMathUtility.multiply("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-2.99999999999999", BigMathUtility.multiply("2.99999999999999", "-1", 14));
        Assert.assertEquals("-3.0000000000", BigMathUtility.multiply("2.99999999999999", "-1", 10));
        Assert.assertEquals("-3.00000", BigMathUtility.multiply("2.99999999999999", "-1", 5));
        Assert.assertEquals("-3.0", BigMathUtility.multiply("2.99999999999999", "-1", 1));
        Assert.assertEquals("0.000000000543898", BigMathUtility.multiply("-.0000000578", "-.00941", 15));
        Assert.assertEquals("0.0000000005439", BigMathUtility.multiply("-.0000000578", "-.00941", 13));
        Assert.assertEquals("0.000000000544", BigMathUtility.multiply("-.0000000578", "-.00941", 12));
        Assert.assertEquals("0.0000000005", BigMathUtility.multiply("-.0000000578", "-.00941", 10));
        Assert.assertEquals("0.000000001", BigMathUtility.multiply("-.0000000578", "-.00941", 9));
        Assert.assertEquals("0.000000", BigMathUtility.multiply("-.0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.multiply("-.0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.multiply("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.multiply("-.0000000578", "-.00941", 1));
        Assert.assertEquals("36.169380116143898", BigMathUtility.multiply("-18.0000000578", "-2.00941", 15));
        Assert.assertEquals("36.1693801161439", BigMathUtility.multiply("-18.0000000578", "-2.00941", 13));
        Assert.assertEquals("36.169380116144", BigMathUtility.multiply("-18.0000000578", "-2.00941", 12));
        Assert.assertEquals("36.1693801161", BigMathUtility.multiply("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("36.169380116", BigMathUtility.multiply("-18.0000000578", "-2.00941", 9));
        Assert.assertEquals("36.169380", BigMathUtility.multiply("-18.0000000578", "-2.00941", 6));
        Assert.assertEquals("36.169", BigMathUtility.multiply("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("36.17", BigMathUtility.multiply("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("36.2", BigMathUtility.multiply("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.multiply("-2.99999999999999", "-1", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.multiply("-2.99999999999999", "-1", 10));
        Assert.assertEquals("3.00000", BigMathUtility.multiply("-2.99999999999999", "-1", 5));
        Assert.assertEquals("3.0", BigMathUtility.multiply("-2.99999999999999", "-1", 1));
        
        //larger cases
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                BigMathUtility.multiply(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                BigMathUtility.multiply(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                BigMathUtility.multiply(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                BigMathUtility.multiply(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                BigMathUtility.multiply(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                BigMathUtility.multiply(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                BigMathUtility.multiply(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499910507978625847435139232065799353633314045948460",
                BigMathUtility.multiply(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.00000000000000000000000000000002687376751781200582372531975499527421227394267137713160899864598658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "93397314607045622740845367341124493415279050759344196499919100593878414343625317528707704224110471757243.92278194065385199761391275725684251488867999967640025100577012428437505314146594052170344735863658920749169571486635794800595273983953655922605516897451509898750316549",
                BigMathUtility.multiply(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
    }
    
    /**
     * JUnit test of divide.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#divide(String, String, int)
     */
    @Test
    public void testDivide() throws Exception {
        //standard
        Assert.assertEquals("1", BigMathUtility.divide("1", "1"));
        Assert.assertEquals("4.25", BigMathUtility.divide("17", "4"));
        Assert.assertEquals("0.0508474576271186440677966101694915254237288135593220338983050847", BigMathUtility.divide("9", "177"));
        Assert.assertEquals("0", BigMathUtility.divide("0", "88"));
        
        //divide by zero
        try {
            BigMathUtility.divide("0549", "0");
            Assert.fail();
        } catch (ArithmeticException ignored) {
        }
        
        //invalid
        try {
            BigMathUtility.divide("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.divide("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.divide("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertEquals("-1", BigMathUtility.divide("-1", "1"));
        Assert.assertEquals("-4.25", BigMathUtility.divide("-17", "4"));
        Assert.assertEquals("-0.0508474576271186440677966101694915254237288135593220338983050847", BigMathUtility.divide("-9", "177"));
        Assert.assertEquals("0", BigMathUtility.divide("-0", "88"));
        Assert.assertEquals("-1", BigMathUtility.divide("1", "-1"));
        Assert.assertEquals("-4.25", BigMathUtility.divide("17", "-4"));
        Assert.assertEquals("-0.0508474576271186440677966101694915254237288135593220338983050847", BigMathUtility.divide("9", "-177"));
        Assert.assertEquals("0", BigMathUtility.divide("0", "-88"));
        Assert.assertEquals("1", BigMathUtility.divide("-1", "-1"));
        Assert.assertEquals("4.25", BigMathUtility.divide("-17", "-4"));
        Assert.assertEquals("0.0508474576271186440677966101694915254237288135593220338983050847", BigMathUtility.divide("-9", "-177"));
        Assert.assertEquals("0", BigMathUtility.divide("-0", "-88"));
        
        //decimal
        Assert.assertEquals("10", BigMathUtility.divide("1", "0.1"));
        Assert.assertEquals("10", BigMathUtility.divide("1", ".1"));
        Assert.assertEquals("1", BigMathUtility.divide(".1", ".1"));
        Assert.assertEquals("1.6315789473684210526315789473684210526315789473684210526315789474", BigMathUtility.divide("3.1", "1.9"));
        Assert.assertEquals("31", BigMathUtility.divide("3.1", "0.1"));
        Assert.assertEquals("0.1542288557213930348258706467661691542288557213930348258706467662", BigMathUtility.divide("03.1", "20.1"));
        Assert.assertEquals("0.6142401700318809776833156216790648246546227417640807651434643996", BigMathUtility.divide(".578", ".941"));
        Assert.assertEquals("0.0005868371811103406193873541663916924973170981815154410264168108", BigMathUtility.divide("0.578", "984.941"));
        Assert.assertEquals("0.0000061424017003188097768331562167906482465462274176408076514346", BigMathUtility.divide(".0000000578", ".00941"));
        Assert.assertEquals("8.9578533289871156210031800379215789709417192111117193604092743641", BigMathUtility.divide("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("-10", BigMathUtility.divide("-1", "0.1"));
        Assert.assertEquals("-10", BigMathUtility.divide("-1", ".1"));
        Assert.assertEquals("-1", BigMathUtility.divide("-.1", ".1"));
        Assert.assertEquals("-1.6315789473684210526315789473684210526315789473684210526315789474", BigMathUtility.divide("-3.1", "1.9"));
        Assert.assertEquals("-31", BigMathUtility.divide("-3.1", "0.1"));
        Assert.assertEquals("-0.1542288557213930348258706467661691542288557213930348258706467662", BigMathUtility.divide("-03.1", "20.1"));
        Assert.assertEquals("-0.6142401700318809776833156216790648246546227417640807651434643996", BigMathUtility.divide("-.578", ".941"));
        Assert.assertEquals("-0.0005868371811103406193873541663916924973170981815154410264168108", BigMathUtility.divide("-0.578", "984.941"));
        Assert.assertEquals("-0.0000061424017003188097768331562167906482465462274176408076514346", BigMathUtility.divide("-.0000000578", ".00941"));
        Assert.assertEquals("-8.9578533289871156210031800379215789709417192111117193604092743641", BigMathUtility.divide("-18.0000000578", "2.00941"));
        Assert.assertEquals("-10", BigMathUtility.divide("1", "-0.1"));
        Assert.assertEquals("-10", BigMathUtility.divide("1", "-.1"));
        Assert.assertEquals("-1", BigMathUtility.divide(".1", "-.1"));
        Assert.assertEquals("-1.6315789473684210526315789473684210526315789473684210526315789474", BigMathUtility.divide("3.1", "-1.9"));
        Assert.assertEquals("-31", BigMathUtility.divide("3.1", "-0.1"));
        Assert.assertEquals("-0.1542288557213930348258706467661691542288557213930348258706467662", BigMathUtility.divide("03.1", "-20.1"));
        Assert.assertEquals("-0.6142401700318809776833156216790648246546227417640807651434643996", BigMathUtility.divide(".578", "-.941"));
        Assert.assertEquals("-0.0005868371811103406193873541663916924973170981815154410264168108", BigMathUtility.divide("0.578", "-984.941"));
        Assert.assertEquals("-0.0000061424017003188097768331562167906482465462274176408076514346", BigMathUtility.divide(".0000000578", "-.00941"));
        Assert.assertEquals("-8.9578533289871156210031800379215789709417192111117193604092743641", BigMathUtility.divide("18.0000000578", "-2.00941"));
        Assert.assertEquals("10", BigMathUtility.divide("-1", "-0.1"));
        Assert.assertEquals("10", BigMathUtility.divide("-1", "-.1"));
        Assert.assertEquals("1", BigMathUtility.divide("-.1", "-.1"));
        Assert.assertEquals("1.6315789473684210526315789473684210526315789473684210526315789474", BigMathUtility.divide("-3.1", "-1.9"));
        Assert.assertEquals("31", BigMathUtility.divide("-3.1", "-0.1"));
        Assert.assertEquals("0.1542288557213930348258706467661691542288557213930348258706467662", BigMathUtility.divide("-03.1", "-20.1"));
        Assert.assertEquals("0.6142401700318809776833156216790648246546227417640807651434643996", BigMathUtility.divide("-.578", "-.941"));
        Assert.assertEquals("0.0005868371811103406193873541663916924973170981815154410264168108", BigMathUtility.divide("-0.578", "-984.941"));
        Assert.assertEquals("0.0000061424017003188097768331562167906482465462274176408076514346", BigMathUtility.divide("-.0000000578", "-.00941"));
        Assert.assertEquals("8.9578533289871156210031800379215789709417192111117193604092743641", BigMathUtility.divide("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", BigMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", BigMathUtility.divide("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", BigMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", BigMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", BigMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.divide("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //precision, negative
        Assert.assertEquals("-1", BigMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", BigMathUtility.divide("-1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("-1", BigMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("-1.00000000", BigMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("-1.0000000000000000", BigMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("-1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("-1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.divide("-1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //custom precision
        Assert.assertEquals("0.000006142401700", BigMathUtility.divide(".0000000578", ".00941", 15));
        Assert.assertEquals("0.0000061424017", BigMathUtility.divide(".0000000578", ".00941", 13));
        Assert.assertEquals("0.000006142402", BigMathUtility.divide(".0000000578", ".00941", 12));
        Assert.assertEquals("0.0000061424", BigMathUtility.divide(".0000000578", ".00941", 10));
        Assert.assertEquals("0.000006142", BigMathUtility.divide(".0000000578", ".00941", 9));
        Assert.assertEquals("0.000006", BigMathUtility.divide(".0000000578", ".00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.divide(".0000000578", ".00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.divide(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.divide(".0000000578", ".00941", 1));
        Assert.assertEquals("8.957853328987116", BigMathUtility.divide("18.0000000578", "2.00941", 15));
        Assert.assertEquals("8.9578533289871", BigMathUtility.divide("18.0000000578", "2.00941", 13));
        Assert.assertEquals("8.957853328987", BigMathUtility.divide("18.0000000578", "2.00941", 12));
        Assert.assertEquals("8.9578533290", BigMathUtility.divide("18.0000000578", "2.00941", 10));
        Assert.assertEquals("8.957853329", BigMathUtility.divide("18.0000000578", "2.00941", 9));
        Assert.assertEquals("8.957853", BigMathUtility.divide("18.0000000578", "2.00941", 6));
        Assert.assertEquals("8.958", BigMathUtility.divide("18.0000000578", "2.00941", 3));
        Assert.assertEquals("8.96", BigMathUtility.divide("18.0000000578", "2.00941", 2));
        Assert.assertEquals("9.0", BigMathUtility.divide("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.divide("2.99999999999999", "1", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.divide("2.99999999999999", "1", 10));
        Assert.assertEquals("3.00000", BigMathUtility.divide("2.99999999999999", "1", 5));
        Assert.assertEquals("3.0", BigMathUtility.divide("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("-0.000006142401700", BigMathUtility.divide("-.0000000578", ".00941", 15));
        Assert.assertEquals("-0.0000061424017", BigMathUtility.divide("-.0000000578", ".00941", 13));
        Assert.assertEquals("-0.000006142402", BigMathUtility.divide("-.0000000578", ".00941", 12));
        Assert.assertEquals("-0.0000061424", BigMathUtility.divide("-.0000000578", ".00941", 10));
        Assert.assertEquals("-0.000006142", BigMathUtility.divide("-.0000000578", ".00941", 9));
        Assert.assertEquals("-0.000006", BigMathUtility.divide("-.0000000578", ".00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.divide("-.0000000578", ".00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.divide("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.divide("-.0000000578", ".00941", 1));
        Assert.assertEquals("-8.957853328987116", BigMathUtility.divide("-18.0000000578", "2.00941", 15));
        Assert.assertEquals("-8.9578533289871", BigMathUtility.divide("-18.0000000578", "2.00941", 13));
        Assert.assertEquals("-8.957853328987", BigMathUtility.divide("-18.0000000578", "2.00941", 12));
        Assert.assertEquals("-8.9578533290", BigMathUtility.divide("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-8.957853329", BigMathUtility.divide("-18.0000000578", "2.00941", 9));
        Assert.assertEquals("-8.957853", BigMathUtility.divide("-18.0000000578", "2.00941", 6));
        Assert.assertEquals("-8.958", BigMathUtility.divide("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-8.96", BigMathUtility.divide("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-9.0", BigMathUtility.divide("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-2.99999999999999", BigMathUtility.divide("-2.99999999999999", "1", 14));
        Assert.assertEquals("-3.0000000000", BigMathUtility.divide("-2.99999999999999", "1", 10));
        Assert.assertEquals("-3.00000", BigMathUtility.divide("-2.99999999999999", "1", 5));
        Assert.assertEquals("-3.0", BigMathUtility.divide("-2.99999999999999", "1", 1));
        Assert.assertEquals("-0.000006142401700", BigMathUtility.divide(".0000000578", "-.00941", 15));
        Assert.assertEquals("-0.0000061424017", BigMathUtility.divide(".0000000578", "-.00941", 13));
        Assert.assertEquals("-0.000006142402", BigMathUtility.divide(".0000000578", "-.00941", 12));
        Assert.assertEquals("-0.0000061424", BigMathUtility.divide(".0000000578", "-.00941", 10));
        Assert.assertEquals("-0.000006142", BigMathUtility.divide(".0000000578", "-.00941", 9));
        Assert.assertEquals("-0.000006", BigMathUtility.divide(".0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.divide(".0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.divide(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.divide(".0000000578", "-.00941", 1));
        Assert.assertEquals("-8.957853328987116", BigMathUtility.divide("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("-8.9578533289871", BigMathUtility.divide("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("-8.957853328987", BigMathUtility.divide("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("-8.9578533290", BigMathUtility.divide("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-8.957853329", BigMathUtility.divide("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("-8.957853", BigMathUtility.divide("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("-8.958", BigMathUtility.divide("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-8.96", BigMathUtility.divide("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-9.0", BigMathUtility.divide("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-2.99999999999999", BigMathUtility.divide("2.99999999999999", "-1", 14));
        Assert.assertEquals("-3.0000000000", BigMathUtility.divide("2.99999999999999", "-1", 10));
        Assert.assertEquals("-3.00000", BigMathUtility.divide("2.99999999999999", "-1", 5));
        Assert.assertEquals("-3.0", BigMathUtility.divide("2.99999999999999", "-1", 1));
        Assert.assertEquals("0.000006142401700", BigMathUtility.divide("-.0000000578", "-.00941", 15));
        Assert.assertEquals("0.0000061424017", BigMathUtility.divide("-.0000000578", "-.00941", 13));
        Assert.assertEquals("0.000006142402", BigMathUtility.divide("-.0000000578", "-.00941", 12));
        Assert.assertEquals("0.0000061424", BigMathUtility.divide("-.0000000578", "-.00941", 10));
        Assert.assertEquals("0.000006142", BigMathUtility.divide("-.0000000578", "-.00941", 9));
        Assert.assertEquals("0.000006", BigMathUtility.divide("-.0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.divide("-.0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.divide("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.divide("-.0000000578", "-.00941", 1));
        Assert.assertEquals("8.957853328987116", BigMathUtility.divide("-18.0000000578", "-2.00941", 15));
        Assert.assertEquals("8.9578533289871", BigMathUtility.divide("-18.0000000578", "-2.00941", 13));
        Assert.assertEquals("8.957853328987", BigMathUtility.divide("-18.0000000578", "-2.00941", 12));
        Assert.assertEquals("8.9578533290", BigMathUtility.divide("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("8.957853329", BigMathUtility.divide("-18.0000000578", "-2.00941", 9));
        Assert.assertEquals("8.957853", BigMathUtility.divide("-18.0000000578", "-2.00941", 6));
        Assert.assertEquals("8.958", BigMathUtility.divide("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("8.96", BigMathUtility.divide("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("9.0", BigMathUtility.divide("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.divide("-2.99999999999999", "-1", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.divide("-2.99999999999999", "-1", 10));
        Assert.assertEquals("3.00000", BigMathUtility.divide("-2.99999999999999", "-1", 5));
        Assert.assertEquals("3.0", BigMathUtility.divide("-2.99999999999999", "-1", 1));
        
        //larger cases
        Assert.assertEquals(
                "0.0000000000026223138268334470936228458885653236991922531664445308",
                BigMathUtility.divide(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "381342610395.1644686525787761778068482368292582851082174091447092024910096499",
                BigMathUtility.divide(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.000000000000000000000000000000089144977163172159799930593788901",
                BigMathUtility.divide(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781",
                BigMathUtility.divide(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "0.0000000000026223138268334470936228458885653236991922531664445308",
                BigMathUtility.divide(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "381342610395.1644686525787761778068482368292582851082174091797929770547804939",
                BigMathUtility.divide(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "-0.0000000000026223138268334470936228458885653236991922531664445308",
                BigMathUtility.divide(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-381342610395.1644686525787761778068482368292582851082174091447092024910096499",
                BigMathUtility.divide(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.000000000000000000000000000000089144977163172159799930593788901",
                BigMathUtility.divide(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781",
                BigMathUtility.divide(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-0.0000000000026223138268334470936228458885653236991922531664445308",
                BigMathUtility.divide(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-381342610395.1644686525787761778068482368292582851082174091797929770547804939",
                BigMathUtility.divide(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-0.0000000000026223138268334470936228458885653236991922531664445308",
                BigMathUtility.divide(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-381342610395.1644686525787761778068482368292582851082174091447092024910096499",
                BigMathUtility.divide(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.000000000000000000000000000000089144977163172159799930593788901",
                BigMathUtility.divide(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781",
                BigMathUtility.divide(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-0.0000000000026223138268334470936228458885653236991922531664445308",
                BigMathUtility.divide(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-381342610395.1644686525787761778068482368292582851082174091797929770547804939",
                BigMathUtility.divide(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "0.0000000000026223138268334470936228458885653236991922531664445308",
                BigMathUtility.divide(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "381342610395.1644686525787761778068482368292582851082174091447092024910096499",
                BigMathUtility.divide(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.000000000000000000000000000000089144977163172159799930593788901",
                BigMathUtility.divide(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "11217681935905223359580631369912.1941415121189558985548473204168550272381030037497870640639983781",
                BigMathUtility.divide(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "0.0000000000026223138268334470936228458885653236991922531664445308",
                BigMathUtility.divide(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "381342610395.1644686525787761778068482368292582851082174091797929770547804939",
                BigMathUtility.divide(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
    }
    
    /**
     * JUnit test of mod.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#mod(String, String, int)
     */
    @Test
    public void testMod() throws Exception {
        //standard
        Assert.assertEquals("0", BigMathUtility.mod("1", "1"));
        Assert.assertEquals("1", BigMathUtility.mod("17", "4"));
        Assert.assertEquals("9", BigMathUtility.mod("9", "177"));
        Assert.assertEquals("0", BigMathUtility.mod("0", "88"));
        
        //mod by zero
        try {
            BigMathUtility.mod("0549", "0");
            Assert.fail();
        } catch (ArithmeticException ignored) {
        }
        
        //invalid
        try {
            BigMathUtility.mod("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.mod("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.mod("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertEquals("0", BigMathUtility.mod("-1", "1"));
        Assert.assertEquals("-1", BigMathUtility.mod("-17", "4"));
        Assert.assertEquals("-9", BigMathUtility.mod("-9", "177"));
        Assert.assertEquals("0", BigMathUtility.mod("-0", "88"));
        Assert.assertEquals("0", BigMathUtility.mod("1", "-1"));
        Assert.assertEquals("1", BigMathUtility.mod("17", "-4"));
        Assert.assertEquals("9", BigMathUtility.mod("9", "-177"));
        Assert.assertEquals("0", BigMathUtility.mod("0", "-88"));
        Assert.assertEquals("0", BigMathUtility.mod("-1", "-1"));
        Assert.assertEquals("-1", BigMathUtility.mod("-17", "-4"));
        Assert.assertEquals("-9", BigMathUtility.mod("-9", "-177"));
        Assert.assertEquals("0", BigMathUtility.mod("-0", "-88"));
        
        //decimal
        Assert.assertEquals("0", BigMathUtility.mod("1", "0.1"));
        Assert.assertEquals("0", BigMathUtility.mod("1", ".1"));
        Assert.assertEquals("0", BigMathUtility.mod(".1", ".1"));
        Assert.assertEquals("1.2", BigMathUtility.mod("3.1", "1.9"));
        Assert.assertEquals("0", BigMathUtility.mod("3.1", "0.1"));
        Assert.assertEquals("3.1", BigMathUtility.mod("03.1", "20.1"));
        Assert.assertEquals("0.578", BigMathUtility.mod(".578", ".941"));
        Assert.assertEquals("0.578", BigMathUtility.mod("0.578", "984.941"));
        Assert.assertEquals("0.0000000578", BigMathUtility.mod(".0000000578", ".00941"));
        Assert.assertEquals("1.9247200578", BigMathUtility.mod("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("0", BigMathUtility.mod("-1", "0.1"));
        Assert.assertEquals("0", BigMathUtility.mod("-1", ".1"));
        Assert.assertEquals("0", BigMathUtility.mod("-.1", ".1"));
        Assert.assertEquals("-1.2", BigMathUtility.mod("-3.1", "1.9"));
        Assert.assertEquals("0", BigMathUtility.mod("-3.1", "0.1"));
        Assert.assertEquals("-3.1", BigMathUtility.mod("-03.1", "20.1"));
        Assert.assertEquals("-0.578", BigMathUtility.mod("-.578", ".941"));
        Assert.assertEquals("-0.578", BigMathUtility.mod("-0.578", "984.941"));
        Assert.assertEquals("-0.0000000578", BigMathUtility.mod("-.0000000578", ".00941"));
        Assert.assertEquals("-1.9247200578", BigMathUtility.mod("-18.0000000578", "2.00941"));
        Assert.assertEquals("0", BigMathUtility.mod("1", "-0.1"));
        Assert.assertEquals("0", BigMathUtility.mod("1", "-.1"));
        Assert.assertEquals("0", BigMathUtility.mod(".1", "-.1"));
        Assert.assertEquals("1.2", BigMathUtility.mod("3.1", "-1.9"));
        Assert.assertEquals("0", BigMathUtility.mod("3.1", "-0.1"));
        Assert.assertEquals("3.1", BigMathUtility.mod("03.1", "-20.1"));
        Assert.assertEquals("0.578", BigMathUtility.mod(".578", "-.941"));
        Assert.assertEquals("0.578", BigMathUtility.mod("0.578", "-984.941"));
        Assert.assertEquals("0.0000000578", BigMathUtility.mod(".0000000578", "-.00941"));
        Assert.assertEquals("1.9247200578", BigMathUtility.mod("18.0000000578", "-2.00941"));
        Assert.assertEquals("0", BigMathUtility.mod("-1", "-0.1"));
        Assert.assertEquals("0", BigMathUtility.mod("-1", "-.1"));
        Assert.assertEquals("0", BigMathUtility.mod("-.1", "-.1"));
        Assert.assertEquals("-1.2", BigMathUtility.mod("-3.1", "-1.9"));
        Assert.assertEquals("0", BigMathUtility.mod("-3.1", "-0.1"));
        Assert.assertEquals("-3.1", BigMathUtility.mod("-03.1", "-20.1"));
        Assert.assertEquals("-0.578", BigMathUtility.mod("-.578", "-.941"));
        Assert.assertEquals("-0.578", BigMathUtility.mod("-0.578", "-984.941"));
        Assert.assertEquals("-0.0000000578", BigMathUtility.mod("-.0000000578", "-.00941"));
        Assert.assertEquals("-1.9247200578", BigMathUtility.mod("-18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("0", BigMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", BigMathUtility.mod("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", BigMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", BigMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", BigMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.mod("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //precision, negative
        Assert.assertEquals("0", BigMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", BigMathUtility.mod("-1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", BigMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", BigMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", BigMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.mod("-1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //custom precision
        Assert.assertEquals("0.000000057800000", BigMathUtility.mod(".0000000578", ".00941", 15));
        Assert.assertEquals("0.0000000578000", BigMathUtility.mod(".0000000578", ".00941", 13));
        Assert.assertEquals("0.000000057800", BigMathUtility.mod(".0000000578", ".00941", 12));
        Assert.assertEquals("0.0000000578", BigMathUtility.mod(".0000000578", ".00941", 10));
        Assert.assertEquals("0.000000058", BigMathUtility.mod(".0000000578", ".00941", 9));
        Assert.assertEquals("0.000000", BigMathUtility.mod(".0000000578", ".00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.mod(".0000000578", ".00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.mod(".0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.mod(".0000000578", ".00941", 1));
        Assert.assertEquals("1.924720057800000", BigMathUtility.mod("18.0000000578", "2.00941", 15));
        Assert.assertEquals("1.9247200578000", BigMathUtility.mod("18.0000000578", "2.00941", 13));
        Assert.assertEquals("1.924720057800", BigMathUtility.mod("18.0000000578", "2.00941", 12));
        Assert.assertEquals("1.9247200578", BigMathUtility.mod("18.0000000578", "2.00941", 10));
        Assert.assertEquals("1.924720058", BigMathUtility.mod("18.0000000578", "2.00941", 9));
        Assert.assertEquals("1.924720", BigMathUtility.mod("18.0000000578", "2.00941", 6));
        Assert.assertEquals("1.925", BigMathUtility.mod("18.0000000578", "2.00941", 3));
        Assert.assertEquals("1.92", BigMathUtility.mod("18.0000000578", "2.00941", 2));
        Assert.assertEquals("1.9", BigMathUtility.mod("18.0000000578", "2.00941", 1));
        Assert.assertEquals("0.99999999999999", BigMathUtility.mod("2.99999999999999", "1", 14));
        Assert.assertEquals("1.0000000000", BigMathUtility.mod("2.99999999999999", "1", 10));
        Assert.assertEquals("1.00000", BigMathUtility.mod("2.99999999999999", "1", 5));
        Assert.assertEquals("1.0", BigMathUtility.mod("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("-0.000000057800000", BigMathUtility.mod("-.0000000578", ".00941", 15));
        Assert.assertEquals("-0.0000000578000", BigMathUtility.mod("-.0000000578", ".00941", 13));
        Assert.assertEquals("-0.000000057800", BigMathUtility.mod("-.0000000578", ".00941", 12));
        Assert.assertEquals("-0.0000000578", BigMathUtility.mod("-.0000000578", ".00941", 10));
        Assert.assertEquals("-0.000000058", BigMathUtility.mod("-.0000000578", ".00941", 9));
        Assert.assertEquals("0.000000", BigMathUtility.mod("-.0000000578", ".00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.mod("-.0000000578", ".00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.mod("-.0000000578", ".00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.mod("-.0000000578", ".00941", 1));
        Assert.assertEquals("-1.924720057800000", BigMathUtility.mod("-18.0000000578", "2.00941", 15));
        Assert.assertEquals("-1.9247200578000", BigMathUtility.mod("-18.0000000578", "2.00941", 13));
        Assert.assertEquals("-1.924720057800", BigMathUtility.mod("-18.0000000578", "2.00941", 12));
        Assert.assertEquals("-1.9247200578", BigMathUtility.mod("-18.0000000578", "2.00941", 10));
        Assert.assertEquals("-1.924720058", BigMathUtility.mod("-18.0000000578", "2.00941", 9));
        Assert.assertEquals("-1.924720", BigMathUtility.mod("-18.0000000578", "2.00941", 6));
        Assert.assertEquals("-1.925", BigMathUtility.mod("-18.0000000578", "2.00941", 3));
        Assert.assertEquals("-1.92", BigMathUtility.mod("-18.0000000578", "2.00941", 2));
        Assert.assertEquals("-1.9", BigMathUtility.mod("-18.0000000578", "2.00941", 1));
        Assert.assertEquals("-0.99999999999999", BigMathUtility.mod("-2.99999999999999", "1", 14));
        Assert.assertEquals("-1.0000000000", BigMathUtility.mod("-2.99999999999999", "1", 10));
        Assert.assertEquals("-1.00000", BigMathUtility.mod("-2.99999999999999", "1", 5));
        Assert.assertEquals("-1.0", BigMathUtility.mod("-2.99999999999999", "1", 1));
        Assert.assertEquals("0.000000057800000", BigMathUtility.mod(".0000000578", "-.00941", 15));
        Assert.assertEquals("0.0000000578000", BigMathUtility.mod(".0000000578", "-.00941", 13));
        Assert.assertEquals("0.000000057800", BigMathUtility.mod(".0000000578", "-.00941", 12));
        Assert.assertEquals("0.0000000578", BigMathUtility.mod(".0000000578", "-.00941", 10));
        Assert.assertEquals("0.000000058", BigMathUtility.mod(".0000000578", "-.00941", 9));
        Assert.assertEquals("0.000000", BigMathUtility.mod(".0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.mod(".0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.mod(".0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.mod(".0000000578", "-.00941", 1));
        Assert.assertEquals("1.924720057800000", BigMathUtility.mod("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("1.9247200578000", BigMathUtility.mod("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("1.924720057800", BigMathUtility.mod("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("1.9247200578", BigMathUtility.mod("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("1.924720058", BigMathUtility.mod("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("1.924720", BigMathUtility.mod("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("1.925", BigMathUtility.mod("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("1.92", BigMathUtility.mod("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("1.9", BigMathUtility.mod("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("0.99999999999999", BigMathUtility.mod("2.99999999999999", "-1", 14));
        Assert.assertEquals("1.0000000000", BigMathUtility.mod("2.99999999999999", "-1", 10));
        Assert.assertEquals("1.00000", BigMathUtility.mod("2.99999999999999", "-1", 5));
        Assert.assertEquals("1.0", BigMathUtility.mod("2.99999999999999", "-1", 1));
        Assert.assertEquals("-0.000000057800000", BigMathUtility.mod("-.0000000578", "-.00941", 15));
        Assert.assertEquals("-0.0000000578000", BigMathUtility.mod("-.0000000578", "-.00941", 13));
        Assert.assertEquals("-0.000000057800", BigMathUtility.mod("-.0000000578", "-.00941", 12));
        Assert.assertEquals("-0.0000000578", BigMathUtility.mod("-.0000000578", "-.00941", 10));
        Assert.assertEquals("-0.000000058", BigMathUtility.mod("-.0000000578", "-.00941", 9));
        Assert.assertEquals("0.000000", BigMathUtility.mod("-.0000000578", "-.00941", 6));
        Assert.assertEquals("0.000", BigMathUtility.mod("-.0000000578", "-.00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.mod("-.0000000578", "-.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.mod("-.0000000578", "-.00941", 1));
        Assert.assertEquals("-1.924720057800000", BigMathUtility.mod("-18.0000000578", "-2.00941", 15));
        Assert.assertEquals("-1.9247200578000", BigMathUtility.mod("-18.0000000578", "-2.00941", 13));
        Assert.assertEquals("-1.924720057800", BigMathUtility.mod("-18.0000000578", "-2.00941", 12));
        Assert.assertEquals("-1.9247200578", BigMathUtility.mod("-18.0000000578", "-2.00941", 10));
        Assert.assertEquals("-1.924720058", BigMathUtility.mod("-18.0000000578", "-2.00941", 9));
        Assert.assertEquals("-1.924720", BigMathUtility.mod("-18.0000000578", "-2.00941", 6));
        Assert.assertEquals("-1.925", BigMathUtility.mod("-18.0000000578", "-2.00941", 3));
        Assert.assertEquals("-1.92", BigMathUtility.mod("-18.0000000578", "-2.00941", 2));
        Assert.assertEquals("-1.9", BigMathUtility.mod("-18.0000000578", "-2.00941", 1));
        Assert.assertEquals("-0.99999999999999", BigMathUtility.mod("-2.99999999999999", "-1", 14));
        Assert.assertEquals("-1.0000000000", BigMathUtility.mod("-2.99999999999999", "-1", 10));
        Assert.assertEquals("-1.00000", BigMathUtility.mod("-2.99999999999999", "-1", 5));
        Assert.assertEquals("-1.0", BigMathUtility.mod("-2.99999999999999", "-1", 1));
        
        //larger cases
        Assert.assertEquals(
                "15649826500097987154602319879456339009444871265",
                BigMathUtility.mod(
                        "15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "2573905877562740580297797195670688273455187889",
                BigMathUtility.mod(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.0000000000000000000000000000000489454940894843663168974515098988",
                BigMathUtility.mod(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.0000000000000000000000000000000095023522339419134067499237792953",
                BigMathUtility.mod(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988",
                BigMathUtility.mod(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566",
                BigMathUtility.mod(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        
        //larger cases, negative
        Assert.assertEquals(
                "-15649826500097987154602319879456339009444871265",
                BigMathUtility.mod(
                        "-15649826500097987154602319879456339009444871265",
                        "5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-2573905877562740580297797195670688273455187889",
                BigMathUtility.mod(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.0000000000000000000000000000000489454940894843663168974515098988",
                BigMathUtility.mod(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.0000000000000000000000000000000095023522339419134067499237792953",
                BigMathUtility.mod(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988",
                BigMathUtility.mod(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566",
                BigMathUtility.mod(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "15649826500097987154602319879456339009444871265",
                BigMathUtility.mod(
                        "15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "2573905877562740580297797195670688273455187889",
                BigMathUtility.mod(
                        "5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "0.0000000000000000000000000000000489454940894843663168974515098988",
                BigMathUtility.mod(
                        "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "0.0000000000000000000000000000000095023522339419134067499237792953",
                BigMathUtility.mod(
                        "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988",
                BigMathUtility.mod(
                        "15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566",
                BigMathUtility.mod(
                        "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-15649826500097987154602319879456339009444871265",
                BigMathUtility.mod(
                        "-15649826500097987154602319879456339009444871265",
                        "-5967945689778787050698789841534979848487446456313280987564"
                )
        );
        Assert.assertEquals(
                "-2573905877562740580297797195670688273455187889",
                BigMathUtility.mod(
                        "-5967945689778787050698789841534979848487446456313280987564",
                        "-15649826500097987154602319879456339009444871265"
                )
        );
        Assert.assertEquals(
                "-0.0000000000000000000000000000000489454940894843663168974515098988",
                BigMathUtility.mod(
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-0.0000000000000000000000000000000095023522339419134067499237792953",
                BigMathUtility.mod(
                        "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-0.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
        Assert.assertEquals(
                "-15649826500097987154602319879456339009444871265.0000000000000000000000000000000489454940894843663168974515098988",
                BigMathUtility.mod(
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549",
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001"
                )
        );
        Assert.assertEquals(
                "-2573905877562740580297797195670688273455187889.5490549848915646540753349985065475517290506704946769295887238566",
                BigMathUtility.mod(
                        "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                        "-15649826500097987154602319879456339009444871265.000000000000000000000000000000048945494089484366316897451509898750316549"
                )
        );
    }
    
    /**
     * JUnit test of power.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#power(String, String, int)
     */
    @Test
    public void testPower() throws Exception {
        //standard
        Assert.assertEquals("1", BigMathUtility.power("1", "1"));
        Assert.assertEquals("83521", BigMathUtility.power("17", "4"));
        Assert.assertEquals("7960203505214079922596627255169838497787047322828694156142117910690491617566742105220063689523875143398306807872117412260518969904597664896897486680228885639266332044169", BigMathUtility.power("9", "177"));
        Assert.assertEquals("0", BigMathUtility.power("0", "88"));
        Assert.assertEquals("1", BigMathUtility.power("0549", "0"));
        
        //invalid
        try {
            BigMathUtility.power("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.power("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.power("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertEquals("1", BigMathUtility.power("1", "-1"));
        Assert.assertEquals("0.0000119730367213036242382155386070569078435363561260042384549993", BigMathUtility.power("17", "-4"));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000019403252174826328", BigMathUtility.power("9", "-50"));
        Assert.assertEquals("1", BigMathUtility.power("0549", "-0"));
        
        //decimal
        Assert.assertEquals("1", BigMathUtility.power("1", "0.1"));
        Assert.assertEquals("1", BigMathUtility.power("1", ".1"));
        Assert.assertEquals("0.7943282347242815020659182828363879325889606317554843320923239293", BigMathUtility.power(".1", ".1"));
        Assert.assertEquals("8.5819744708515433237089273766814486911245451273740734442610709486", BigMathUtility.power("3.1", "1.9"));
        Assert.assertEquals("1.1197889288345146071831413740562719532493883819177179451078698313", BigMathUtility.power("3.1", "0.1"));
        Assert.assertEquals("7522635966.6749226381185704632188586848126005617185129642614558334105950269", BigMathUtility.power("03.1", "20.1"));
        Assert.assertEquals("0.5969996767174540961674861214368860564978206903586129549726970645", BigMathUtility.power(".578", ".941"));
        Assert.assertEquals("0.004298576318225759690022273201971798742713621049755334924106211", BigMathUtility.power("0.578", "9.941"));
        Assert.assertEquals("0.8548496553984821559145185197132255021001018761158728967864167369", BigMathUtility.power(".0000000578", ".00941"));
        Assert.assertEquals("332.9332170560748796291677077768639059501997058681864257213118194601", BigMathUtility.power("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("1", BigMathUtility.power("1", "-0.1"));
        Assert.assertEquals("1", BigMathUtility.power("1", "-.1"));
        Assert.assertEquals("1.2589254117941672104239541063958006060936174094669310691079230195", BigMathUtility.power(".1", "-.1"));
        Assert.assertEquals("0.1165233016477122380003268859579887568417677816771818881485816682", BigMathUtility.power("3.1", "-1.9"));
        Assert.assertEquals("0.8930254392145206372225730881042090209286727499868963001312248646", BigMathUtility.power("3.1", "-0.1"));
        Assert.assertEquals("0.0000000001329321270403052091710434166742584104992395066858522505", BigMathUtility.power("03.1", "-20.1"));
        Assert.assertEquals("1.6750427831023373855356029315347448400113294641181765184245490238", BigMathUtility.power(".578", "-.941"));
        Assert.assertEquals("3069254478712442726368134501384516054452292932994473569305301262650456077411768362483242150924003353261119501597964322174177341844445236641962645768645641188185060160572001023801965593053780344728160087751668369537615514465170567341324.7274103625509027509977636938888447303544039462539967897369128548", BigMathUtility.power("0.578", "-984.941"));
        Assert.assertEquals("1.1697963421811956297586846961524296945858030609856244543434384263", BigMathUtility.power(".0000000578", "-.00941"));
        Assert.assertEquals("0.0030036053742020376122100772870281232149079136512395701659532183", BigMathUtility.power("18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", BigMathUtility.power("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", BigMathUtility.power("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", BigMathUtility.power("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", BigMathUtility.power("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", BigMathUtility.power("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.power("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.power("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.power("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //custom precision
        Assert.assertEquals("0.854849655398482", BigMathUtility.power(".0000000578", ".00941", 15));
        Assert.assertEquals("0.8548496553985", BigMathUtility.power(".0000000578", ".00941", 13));
        Assert.assertEquals("0.854849655398", BigMathUtility.power(".0000000578", ".00941", 12));
        Assert.assertEquals("0.8548496554", BigMathUtility.power(".0000000578", ".00941", 10));
        Assert.assertEquals("0.854849655", BigMathUtility.power(".0000000578", ".00941", 9));
        Assert.assertEquals("0.854850", BigMathUtility.power(".0000000578", ".00941", 6));
        Assert.assertEquals("0.855", BigMathUtility.power(".0000000578", ".00941", 3));
        Assert.assertEquals("0.85", BigMathUtility.power(".0000000578", ".00941", 2));
        Assert.assertEquals("0.9", BigMathUtility.power(".0000000578", ".00941", 1));
        Assert.assertEquals("332.933217056074880", BigMathUtility.power("18.0000000578", "2.00941", 15));
        Assert.assertEquals("332.9332170560749", BigMathUtility.power("18.0000000578", "2.00941", 13));
        Assert.assertEquals("332.933217056075", BigMathUtility.power("18.0000000578", "2.00941", 12));
        Assert.assertEquals("332.9332170561", BigMathUtility.power("18.0000000578", "2.00941", 10));
        Assert.assertEquals("332.933217056", BigMathUtility.power("18.0000000578", "2.00941", 9));
        Assert.assertEquals("332.933217", BigMathUtility.power("18.0000000578", "2.00941", 6));
        Assert.assertEquals("332.933", BigMathUtility.power("18.0000000578", "2.00941", 3));
        Assert.assertEquals("332.93", BigMathUtility.power("18.0000000578", "2.00941", 2));
        Assert.assertEquals("332.9", BigMathUtility.power("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.power("2.99999999999999", "1", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.power("2.99999999999999", "1", 10));
        Assert.assertEquals("3.00000", BigMathUtility.power("2.99999999999999", "1", 5));
        Assert.assertEquals("3.0", BigMathUtility.power("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("1.169796342181196", BigMathUtility.power(".0000000578", "-.00941", 15));
        Assert.assertEquals("1.1697963421812", BigMathUtility.power(".0000000578", "-.00941", 13));
        Assert.assertEquals("1.169796342181", BigMathUtility.power(".0000000578", "-.00941", 12));
        Assert.assertEquals("1.1697963422", BigMathUtility.power(".0000000578", "-.00941", 10));
        Assert.assertEquals("1.169796342", BigMathUtility.power(".0000000578", "-.00941", 9));
        Assert.assertEquals("1.169796", BigMathUtility.power(".0000000578", "-.00941", 6));
        Assert.assertEquals("1.170", BigMathUtility.power(".0000000578", "-.00941", 3));
        Assert.assertEquals("1.17", BigMathUtility.power(".0000000578", "-.00941", 2));
        Assert.assertEquals("1.2", BigMathUtility.power(".0000000578", "-.00941", 1));
        Assert.assertEquals("0.003003605374202", BigMathUtility.power("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("0.0030036053742", BigMathUtility.power("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("0.003003605374", BigMathUtility.power("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("0.0030036054", BigMathUtility.power("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("0.003003605", BigMathUtility.power("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("0.003004", BigMathUtility.power("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("0.003", BigMathUtility.power("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("0.00", BigMathUtility.power("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("0.0", BigMathUtility.power("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("0.33333333333333", BigMathUtility.power("2.99999999999999", "-1", 14));
        Assert.assertEquals("0.3333333333", BigMathUtility.power("2.99999999999999", "-1", 10));
        Assert.assertEquals("0.33333", BigMathUtility.power("2.99999999999999", "-1", 5));
        Assert.assertEquals("0.3", BigMathUtility.power("2.99999999999999", "-1", 1));
    }
    
    /**
     * JUnit test of root.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#root(String, String, int)
     */
    @Test
    public void testRoot() throws Exception {
        //standard
        Assert.assertEquals("1", BigMathUtility.root("1", "1"));
        Assert.assertEquals("2.0305431848689307178670594733633386532430700031031400799571665478", BigMathUtility.root("17", "4"));
        Assert.assertEquals("1.0124910679451132345095913456226846565491587770562236112132003255", BigMathUtility.root("9", "177"));
        Assert.assertEquals("0", BigMathUtility.root("0", "88"));
        
        //imaginary numbers
        try {
            BigMathUtility.root("-1", "1");
            Assert.fail();
        } catch (ArithmeticException ignored) {
        }
        try {
            BigMathUtility.root("0549", "0");
            Assert.fail();
        } catch (ArithmeticException ignored) {
        }
        
        //invalid
        try {
            BigMathUtility.root("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.root("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.root("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertEquals("1", BigMathUtility.root("1", "-1"));
        Assert.assertEquals("0.492479060505452326504424784829822551365761865581268607366424297", BigMathUtility.root("17", "-4"));
        Assert.assertEquals("0.9570070779813869713074626695181980893729131502075731940006351718", BigMathUtility.root("9", "-50"));
        
        //decimal
        Assert.assertEquals("1", BigMathUtility.root("1", "0.1"));
        Assert.assertEquals("1", BigMathUtility.root("1", ".1"));
        Assert.assertEquals("0.0000000001", BigMathUtility.root(".1", ".1"));
        Assert.assertEquals("1.8138919683222467296576624749558020090228037579642605673476495722", BigMathUtility.root("3.1", "1.9"));
        Assert.assertEquals("81962.8286980801", BigMathUtility.root("3.1", "0.1"));
        Assert.assertEquals("1.0579030163444754474619230745795692523521649663181500343460914296", BigMathUtility.root("03.1", "20.1"));
        Assert.assertEquals("0.5584713404830014300064202482968069916221478150084999563624704728", BigMathUtility.root(".578", ".941"));
        Assert.assertEquals("0.9463493488067071397052452650369088434934874310004880110138953471", BigMathUtility.root("0.578", "9.941"));
        Assert.assertEquals("0", BigMathUtility.root(".0000000578", ".00941"));
        Assert.assertEquals("4.2140244745683787111819132714516190061175605006126904318302040206", BigMathUtility.root("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertEquals("1", BigMathUtility.root("1", "-0.1"));
        Assert.assertEquals("1", BigMathUtility.root("1", "-.1"));
        Assert.assertEquals("10000000000", BigMathUtility.root(".1", "-.1"));
        Assert.assertEquals("0.5513007485914095721430511858984092694446184590851318960365024668", BigMathUtility.root("3.1", "-1.9"));
        Assert.assertEquals("0.0000122006526114858702155650677564629168732634669806726488718195", BigMathUtility.root("3.1", "-0.1"));
        Assert.assertEquals("0.9452662338136098017669418987962490812251510876944860421982048497", BigMathUtility.root("03.1", "-20.1"));
        Assert.assertEquals("1.7906021804720302858502912366668021476211023928749588351828935813", BigMathUtility.root(".578", "-.941"));
        Assert.assertEquals("1.0005567175975761298214226757423347928668370568555705134626797141", BigMathUtility.root("0.578", "-984.941"));
        Assert.assertEquals("15466433495295412499023611679537033631383377069071860256091919717954555618746195215761497736288772707382375752568911674707134443470866066103504779855486983583751316437950986008868049165095774057300631205677165355804916360902067252274690819222348061110476389209016437182982924353393295210298843002027977692102025727209864091478462962487401528904611880600824686359234237451443768697535106664024704138935506678165219668487717463368314786245112249351123975878934532980032897390671120033970530220117657359293732955044000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.root(".0000000578", "-.00941"));
        Assert.assertEquals("0.2373028457796095168943210118868724235991924357822924566320684476", BigMathUtility.root("18.0000000578", "-2.00941"));
        
        //precision
        Assert.assertEquals("1", BigMathUtility.root("1", "1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", BigMathUtility.root("1.0", "1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("1", BigMathUtility.root("1", "1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("1.00000000", BigMathUtility.root("1", "1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("1.0000000000000000", BigMathUtility.root("1", "1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.root("1", "1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("1.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.root("1", "1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("1.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.root("1", "1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //custom precision
        Assert.assertEquals("0.000000020328536", BigMathUtility.root(".0000000578", ".941", 15));
        Assert.assertEquals("0.0000000203285", BigMathUtility.root(".0000000578", ".941", 13));
        Assert.assertEquals("0.000000020329", BigMathUtility.root(".0000000578", ".941", 12));
        Assert.assertEquals("0.0000000203", BigMathUtility.root(".0000000578", ".941", 10));
        Assert.assertEquals("0.000000020", BigMathUtility.root(".0000000578", ".941", 9));
        Assert.assertEquals("0.000000", BigMathUtility.root(".0000000578", ".941", 6));
        Assert.assertEquals("0.000", BigMathUtility.root(".0000000578", ".941", 3));
        Assert.assertEquals("0.00", BigMathUtility.root(".0000000578", ".941", 2));
        Assert.assertEquals("0.0", BigMathUtility.root(".0000000578", ".941", 1));
        Assert.assertEquals("4.214024474568379", BigMathUtility.root("18.0000000578", "2.00941", 15));
        Assert.assertEquals("4.2140244745684", BigMathUtility.root("18.0000000578", "2.00941", 13));
        Assert.assertEquals("4.214024474568", BigMathUtility.root("18.0000000578", "2.00941", 12));
        Assert.assertEquals("4.2140244746", BigMathUtility.root("18.0000000578", "2.00941", 10));
        Assert.assertEquals("4.214024475", BigMathUtility.root("18.0000000578", "2.00941", 9));
        Assert.assertEquals("4.214024", BigMathUtility.root("18.0000000578", "2.00941", 6));
        Assert.assertEquals("4.214", BigMathUtility.root("18.0000000578", "2.00941", 3));
        Assert.assertEquals("4.21", BigMathUtility.root("18.0000000578", "2.00941", 2));
        Assert.assertEquals("4.2", BigMathUtility.root("18.0000000578", "2.00941", 1));
        Assert.assertEquals("2.99999999999999", BigMathUtility.root("2.99999999999999", "1", 14));
        Assert.assertEquals("3.0000000000", BigMathUtility.root("2.99999999999999", "1", 10));
        Assert.assertEquals("3.00000", BigMathUtility.root("2.99999999999999", "1", 5));
        Assert.assertEquals("3.0", BigMathUtility.root("2.99999999999999", "1", 1));
        
        //custom precision, negative
        Assert.assertEquals("49191934.502173645154754", BigMathUtility.root(".0000000578", "-.941", 15));
        Assert.assertEquals("49191934.5021736451548", BigMathUtility.root(".0000000578", "-.941", 13));
        Assert.assertEquals("49191934.502173645155", BigMathUtility.root(".0000000578", "-.941", 12));
        Assert.assertEquals("49191934.5021736452", BigMathUtility.root(".0000000578", "-.941", 10));
        Assert.assertEquals("49191934.502173645", BigMathUtility.root(".0000000578", "-.941", 9));
        Assert.assertEquals("49191934.502174", BigMathUtility.root(".0000000578", "-.941", 6));
        Assert.assertEquals("49191934.502", BigMathUtility.root(".0000000578", "-.941", 3));
        Assert.assertEquals("49191934.50", BigMathUtility.root(".0000000578", "-.941", 2));
        Assert.assertEquals("49191934.5", BigMathUtility.root(".0000000578", "-.941", 1));
        Assert.assertEquals("0.237302845779610", BigMathUtility.root("18.0000000578", "-2.00941", 15));
        Assert.assertEquals("0.2373028457796", BigMathUtility.root("18.0000000578", "-2.00941", 13));
        Assert.assertEquals("0.237302845780", BigMathUtility.root("18.0000000578", "-2.00941", 12));
        Assert.assertEquals("0.2373028458", BigMathUtility.root("18.0000000578", "-2.00941", 10));
        Assert.assertEquals("0.237302846", BigMathUtility.root("18.0000000578", "-2.00941", 9));
        Assert.assertEquals("0.237303", BigMathUtility.root("18.0000000578", "-2.00941", 6));
        Assert.assertEquals("0.237", BigMathUtility.root("18.0000000578", "-2.00941", 3));
        Assert.assertEquals("0.24", BigMathUtility.root("18.0000000578", "-2.00941", 2));
        Assert.assertEquals("0.2", BigMathUtility.root("18.0000000578", "-2.00941", 1));
        Assert.assertEquals("0.33333333333333", BigMathUtility.root("2.99999999999999", "-1", 14));
        Assert.assertEquals("0.3333333333", BigMathUtility.root("2.99999999999999", "-1", 10));
        Assert.assertEquals("0.33333", BigMathUtility.root("2.99999999999999", "-1", 5));
        Assert.assertEquals("0.3", BigMathUtility.root("2.99999999999999", "-1", 1));
    }
    
    /**
     * JUnit test of log.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#log(String, int, int)
     */
    @Test
    public void testLog() throws Exception {
        //standard
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.log("1", 10));
        Assert.assertEquals("7.7772464838640963795913548138936262173008734442830041081204282002", BigMathUtility.log("59875132", 10));
        Assert.assertEquals("6.4588633989030237852940101777646830166306059006880449171282885624", BigMathUtility.log("59875132", 16));
        Assert.assertEquals("7.7416033471438101343043086923917835687985581359699429124459764835", BigMathUtility.log("8976464100065468", 115));
        
        //invalid number
        try {
            BigMathUtility.log("-88", 10);
            Assert.fail();
        } catch (ArithmeticException ignored) {
        }
        
        //invalid base
        try {
            BigMathUtility.log("88", 0);
            Assert.fail();
        } catch (ArithmeticException ignored) {
        }
        try {
            BigMathUtility.log("88", 1);
            Assert.fail();
        } catch (ArithmeticException ignored) {
        }
        try {
            BigMathUtility.log("88", -1);
            Assert.fail();
        } catch (ArithmeticException ignored) {
        }
        
        //invalid
        try {
            BigMathUtility.log("15s5", 10);
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //decimal
        Assert.assertEquals("-1.2295235577981631081928840810289268477073326768745561420897153278", BigMathUtility.log(".058949", 10));
        Assert.assertEquals("-0.0054883516015224846240309445288773850725933116441204479526233334", BigMathUtility.log(".9874421200001", 10));
        Assert.assertEquals("-8.5876501260611484010681707592423294502392943685268343302579861858", BigMathUtility.log(".0000000000456498", 16));
        Assert.assertEquals("-0.1203288744667847051395786262414959272464250468884981793858640109", BigMathUtility.log(".5649871212025644980798794213200031654", 115));
        
        //precision
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.log("1", 10, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.log("1.0", 10, BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", BigMathUtility.log("1", 10, BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", BigMathUtility.log("1", 10, BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", BigMathUtility.log("1", 10, BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.log("1", 10, BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.log("1", 10, BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.log("1", 10, BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //custom precision
        Assert.assertEquals("-7.238072161579471", BigMathUtility.log(".0000000578", 10, 15));
        Assert.assertEquals("-7.2380721615795", BigMathUtility.log(".0000000578", 10, 13));
        Assert.assertEquals("-7.238072161579", BigMathUtility.log(".0000000578", 10, 12));
        Assert.assertEquals("-7.2380721616", BigMathUtility.log(".0000000578", 10, 10));
        Assert.assertEquals("-7.238072162", BigMathUtility.log(".0000000578", 10, 9));
        Assert.assertEquals("-7.238072", BigMathUtility.log(".0000000578", 10, 6));
        Assert.assertEquals("-7.238", BigMathUtility.log(".0000000578", 10, 3));
        Assert.assertEquals("-7.24", BigMathUtility.log(".0000000578", 10, 2));
        Assert.assertEquals("-7.2", BigMathUtility.log(".0000000578", 10, 1));
        Assert.assertEquals("1.255272506497874", BigMathUtility.log("18.0000000578", 10, 15));
        Assert.assertEquals("1.2552725064979", BigMathUtility.log("18.0000000578", 10, 13));
        Assert.assertEquals("1.255272506498", BigMathUtility.log("18.0000000578", 10, 12));
        Assert.assertEquals("1.2552725065", BigMathUtility.log("18.0000000578", 10, 10));
        Assert.assertEquals("1.255272506", BigMathUtility.log("18.0000000578", 10, 9));
        Assert.assertEquals("1.255273", BigMathUtility.log("18.0000000578", 10, 6));
        Assert.assertEquals("1.255", BigMathUtility.log("18.0000000578", 10, 3));
        Assert.assertEquals("1.26", BigMathUtility.log("18.0000000578", 10, 2));
        Assert.assertEquals("1.3", BigMathUtility.log("18.0000000578", 10, 1));
        Assert.assertEquals("0.47712125471966", BigMathUtility.log("2.99999999999999", 10, 14));
        Assert.assertEquals("0.4771212547", BigMathUtility.log("2.99999999999999", 10, 10));
        Assert.assertEquals("0.47712", BigMathUtility.log("2.99999999999999", 10, 5));
        Assert.assertEquals("0.5", BigMathUtility.log("2.99999999999999", 10, 1));
    }
    
    /**
     * JUnit test of ln.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#ln(String, int)
     */
    @Test
    public void testLn() throws Exception {
        //standard
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.ln("1"));
        Assert.assertEquals("41.2341684375238173373415454243641966384628703404061472780222856753", BigMathUtility.ln("59875132"));
        Assert.assertEquals("84.5817388374975215812641301657376711018943538822161890891117948071", BigMathUtility.ln("8976464100065468"));
        
        //invalid number
        try {
            BigMathUtility.ln("-88");
            Assert.fail();
        } catch (ArithmeticException ignored) {
        }
        
        //invalid
        try {
            BigMathUtility.ln("15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //decimal
        Assert.assertEquals("-6.5188086278787583687889208936710068012941815586077141031193054891", BigMathUtility.ln(".058949"));
        Assert.assertEquals("-0.0290986809857531508380850176169231775677365734706866203080057463", BigMathUtility.ln(".9874421200001"));
        Assert.assertEquals("-54.8246014679098679104104510873130495239335095741259199582493316435", BigMathUtility.ln(".0000000000456498"));
        Assert.assertEquals("-1.3146663524829833197087467982420764367924868051538425466435066234", BigMathUtility.ln(".5649871212025644980798794213200031654"));
        
        //precision
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.ln("1", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.ln("1.0", BigMathUtility.PrecisionMode.DEFAULT_PRECISION));
        Assert.assertEquals("0", BigMathUtility.ln("1", BigMathUtility.PrecisionMode.NO_PRECISION));
        Assert.assertEquals("0.00000000", BigMathUtility.ln("1", BigMathUtility.PrecisionMode.LOW_PRECISION));
        Assert.assertEquals("0.0000000000000000", BigMathUtility.ln("1", BigMathUtility.PrecisionMode.MID_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.ln("1", BigMathUtility.PrecisionMode.HIGH_PRECISION));
        Assert.assertEquals("0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.ln("1", BigMathUtility.PrecisionMode.MAX_PRECISION));
        Assert.assertEquals("0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", BigMathUtility.ln("1", BigMathUtility.PrecisionMode.MATH_PRECISION));
        
        //custom precision
        Assert.assertEquals("-38.375521116984491", BigMathUtility.ln(".0000000578", 15));
        Assert.assertEquals("-38.3755211169845", BigMathUtility.ln(".0000000578", 13));
        Assert.assertEquals("-38.375521116984", BigMathUtility.ln(".0000000578", 12));
        Assert.assertEquals("-38.3755211170", BigMathUtility.ln(".0000000578", 10));
        Assert.assertEquals("-38.375521117", BigMathUtility.ln(".0000000578", 9));
        Assert.assertEquals("-38.375521", BigMathUtility.ln(".0000000578", 6));
        Assert.assertEquals("-38.376", BigMathUtility.ln(".0000000578", 3));
        Assert.assertEquals("-38.38", BigMathUtility.ln(".0000000578", 2));
        Assert.assertEquals("-38.4", BigMathUtility.ln(".0000000578", 1));
        Assert.assertEquals("6.655326930336560", BigMathUtility.ln("18.0000000578", 15));
        Assert.assertEquals("6.6553269303366", BigMathUtility.ln("18.0000000578", 13));
        Assert.assertEquals("6.655326930337", BigMathUtility.ln("18.0000000578", 12));
        Assert.assertEquals("6.6553269303", BigMathUtility.ln("18.0000000578", 10));
        Assert.assertEquals("6.655326930", BigMathUtility.ln("18.0000000578", 9));
        Assert.assertEquals("6.655327", BigMathUtility.ln("18.0000000578", 6));
        Assert.assertEquals("6.655", BigMathUtility.ln("18.0000000578", 3));
        Assert.assertEquals("6.66", BigMathUtility.ln("18.0000000578", 2));
        Assert.assertEquals("6.7", BigMathUtility.ln("18.0000000578", 1));
        Assert.assertEquals("2.52964827886725", BigMathUtility.ln("2.99999999999999", 14));
        Assert.assertEquals("2.5296482789", BigMathUtility.ln("2.99999999999999", 10));
        Assert.assertEquals("2.52965", BigMathUtility.ln("2.99999999999999", 5));
        Assert.assertEquals("2.5", BigMathUtility.ln("2.99999999999999", 1));
    }
    
    /**
     * JUnit test of greaterThan.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#greaterThan(String, String)
     */
    @Test
    public void testGreaterThan() throws Exception {
        //standard
        Assert.assertFalse(BigMathUtility.greaterThan("1", "1"));
        Assert.assertTrue(BigMathUtility.greaterThan("17", "4"));
        Assert.assertFalse(BigMathUtility.greaterThan("9", "177"));
        Assert.assertFalse(BigMathUtility.greaterThan("0", "88"));
        Assert.assertTrue(BigMathUtility.greaterThan("0549", "0"));
        
        //invalid
        try {
            BigMathUtility.greaterThan("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.greaterThan("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.greaterThan("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertFalse(BigMathUtility.greaterThan("-1", "1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-17", "4"));
        Assert.assertFalse(BigMathUtility.greaterThan("-9", "177"));
        Assert.assertFalse(BigMathUtility.greaterThan("-0", "88"));
        Assert.assertFalse(BigMathUtility.greaterThan("-0549", "0"));
        Assert.assertTrue(BigMathUtility.greaterThan("1", "-1"));
        Assert.assertTrue(BigMathUtility.greaterThan("17", "-4"));
        Assert.assertTrue(BigMathUtility.greaterThan("9", "-177"));
        Assert.assertTrue(BigMathUtility.greaterThan("0", "-88"));
        Assert.assertTrue(BigMathUtility.greaterThan("0549", "-0"));
        Assert.assertFalse(BigMathUtility.greaterThan("-1", "-1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-17", "-4"));
        Assert.assertTrue(BigMathUtility.greaterThan("-9", "-177"));
        Assert.assertTrue(BigMathUtility.greaterThan("-0", "-88"));
        Assert.assertFalse(BigMathUtility.greaterThan("-0549", "-0"));
        
        //decimal
        Assert.assertTrue(BigMathUtility.greaterThan("1", "0.1"));
        Assert.assertTrue(BigMathUtility.greaterThan("1", ".1"));
        Assert.assertFalse(BigMathUtility.greaterThan(".1", ".1"));
        Assert.assertTrue(BigMathUtility.greaterThan("3.1", "1.9"));
        Assert.assertTrue(BigMathUtility.greaterThan("3.1", "0.1"));
        Assert.assertFalse(BigMathUtility.greaterThan("03.1", "20.1"));
        Assert.assertFalse(BigMathUtility.greaterThan(".578", ".941"));
        Assert.assertFalse(BigMathUtility.greaterThan("0.578", "984.941"));
        Assert.assertFalse(BigMathUtility.greaterThan(".0000000578", ".00941"));
        Assert.assertTrue(BigMathUtility.greaterThan("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertFalse(BigMathUtility.greaterThan("-1", "0.1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-1", ".1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-.1", ".1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-3.1", "1.9"));
        Assert.assertFalse(BigMathUtility.greaterThan("-3.1", "0.1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-03.1", "20.1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-.578", ".941"));
        Assert.assertFalse(BigMathUtility.greaterThan("-0.578", "984.941"));
        Assert.assertFalse(BigMathUtility.greaterThan("-.0000000578", ".00941"));
        Assert.assertFalse(BigMathUtility.greaterThan("-18.0000000578", "2.00941"));
        Assert.assertTrue(BigMathUtility.greaterThan("1", "-0.1"));
        Assert.assertTrue(BigMathUtility.greaterThan("1", "-.1"));
        Assert.assertTrue(BigMathUtility.greaterThan(".1", "-.1"));
        Assert.assertTrue(BigMathUtility.greaterThan("3.1", "-1.9"));
        Assert.assertTrue(BigMathUtility.greaterThan("3.1", "-0.1"));
        Assert.assertTrue(BigMathUtility.greaterThan("03.1", "-20.1"));
        Assert.assertTrue(BigMathUtility.greaterThan(".578", "-.941"));
        Assert.assertTrue(BigMathUtility.greaterThan("0.578", "-984.941"));
        Assert.assertTrue(BigMathUtility.greaterThan(".0000000578", "-.00941"));
        Assert.assertTrue(BigMathUtility.greaterThan("18.0000000578", "-2.00941"));
        Assert.assertFalse(BigMathUtility.greaterThan("-1", "-0.1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-1", "-.1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-.1", "-.1"));
        Assert.assertFalse(BigMathUtility.greaterThan("-3.1", "-1.9"));
        Assert.assertFalse(BigMathUtility.greaterThan("-3.1", "-0.1"));
        Assert.assertTrue(BigMathUtility.greaterThan("-03.1", "-20.1"));
        Assert.assertTrue(BigMathUtility.greaterThan("-.578", "-.941"));
        Assert.assertTrue(BigMathUtility.greaterThan("-0.578", "-984.941"));
        Assert.assertTrue(BigMathUtility.greaterThan("-.0000000578", "-.00941"));
        Assert.assertFalse(BigMathUtility.greaterThan("-18.0000000578", "-2.00941"));
        
        //larger cases
        Assert.assertFalse(BigMathUtility.greaterThan(
                "15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        
        //larger cases, negative
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "-15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.greaterThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
    }
    
    /**
     * JUnit test of lessThan.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#lessThan(String, String)
     */
    @Test
    public void testLessThan() throws Exception {
        //standard
        Assert.assertFalse(BigMathUtility.lessThan("1", "1"));
        Assert.assertFalse(BigMathUtility.lessThan("17", "4"));
        Assert.assertTrue(BigMathUtility.lessThan("9", "177"));
        Assert.assertTrue(BigMathUtility.lessThan("0", "88"));
        Assert.assertFalse(BigMathUtility.lessThan("0549", "0"));
        
        //invalid
        try {
            BigMathUtility.lessThan("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.lessThan("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.lessThan("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertTrue(BigMathUtility.lessThan("-1", "1"));
        Assert.assertTrue(BigMathUtility.lessThan("-17", "4"));
        Assert.assertTrue(BigMathUtility.lessThan("-9", "177"));
        Assert.assertTrue(BigMathUtility.lessThan("-0", "88"));
        Assert.assertTrue(BigMathUtility.lessThan("-0549", "0"));
        Assert.assertFalse(BigMathUtility.lessThan("1", "-1"));
        Assert.assertFalse(BigMathUtility.lessThan("17", "-4"));
        Assert.assertFalse(BigMathUtility.lessThan("9", "-177"));
        Assert.assertFalse(BigMathUtility.lessThan("0", "-88"));
        Assert.assertFalse(BigMathUtility.lessThan("0549", "-0"));
        Assert.assertFalse(BigMathUtility.lessThan("-1", "-1"));
        Assert.assertTrue(BigMathUtility.lessThan("-17", "-4"));
        Assert.assertFalse(BigMathUtility.lessThan("-9", "-177"));
        Assert.assertFalse(BigMathUtility.lessThan("-0", "-88"));
        Assert.assertTrue(BigMathUtility.lessThan("-0549", "-0"));
        
        //decimal
        Assert.assertFalse(BigMathUtility.lessThan("1", "0.1"));
        Assert.assertFalse(BigMathUtility.lessThan("1", ".1"));
        Assert.assertFalse(BigMathUtility.lessThan(".1", ".1"));
        Assert.assertFalse(BigMathUtility.lessThan("3.1", "1.9"));
        Assert.assertFalse(BigMathUtility.lessThan("3.1", "0.1"));
        Assert.assertTrue(BigMathUtility.lessThan("03.1", "20.1"));
        Assert.assertTrue(BigMathUtility.lessThan(".578", ".941"));
        Assert.assertTrue(BigMathUtility.lessThan("0.578", "984.941"));
        Assert.assertTrue(BigMathUtility.lessThan(".0000000578", ".00941"));
        Assert.assertFalse(BigMathUtility.lessThan("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertTrue(BigMathUtility.lessThan("-1", "0.1"));
        Assert.assertTrue(BigMathUtility.lessThan("-1", ".1"));
        Assert.assertTrue(BigMathUtility.lessThan("-.1", ".1"));
        Assert.assertTrue(BigMathUtility.lessThan("-3.1", "1.9"));
        Assert.assertTrue(BigMathUtility.lessThan("-3.1", "0.1"));
        Assert.assertTrue(BigMathUtility.lessThan("-03.1", "20.1"));
        Assert.assertTrue(BigMathUtility.lessThan("-.578", ".941"));
        Assert.assertTrue(BigMathUtility.lessThan("-0.578", "984.941"));
        Assert.assertTrue(BigMathUtility.lessThan("-.0000000578", ".00941"));
        Assert.assertTrue(BigMathUtility.lessThan("-18.0000000578", "2.00941"));
        Assert.assertFalse(BigMathUtility.lessThan("1", "-0.1"));
        Assert.assertFalse(BigMathUtility.lessThan("1", "-.1"));
        Assert.assertFalse(BigMathUtility.lessThan(".1", "-.1"));
        Assert.assertFalse(BigMathUtility.lessThan("3.1", "-1.9"));
        Assert.assertFalse(BigMathUtility.lessThan("3.1", "-0.1"));
        Assert.assertFalse(BigMathUtility.lessThan("03.1", "-20.1"));
        Assert.assertFalse(BigMathUtility.lessThan(".578", "-.941"));
        Assert.assertFalse(BigMathUtility.lessThan("0.578", "-984.941"));
        Assert.assertFalse(BigMathUtility.lessThan(".0000000578", "-.00941"));
        Assert.assertFalse(BigMathUtility.lessThan("18.0000000578", "-2.00941"));
        Assert.assertTrue(BigMathUtility.lessThan("-1", "-0.1"));
        Assert.assertTrue(BigMathUtility.lessThan("-1", "-.1"));
        Assert.assertFalse(BigMathUtility.lessThan("-.1", "-.1"));
        Assert.assertTrue(BigMathUtility.lessThan("-3.1", "-1.9"));
        Assert.assertTrue(BigMathUtility.lessThan("-3.1", "-0.1"));
        Assert.assertFalse(BigMathUtility.lessThan("-03.1", "-20.1"));
        Assert.assertFalse(BigMathUtility.lessThan("-.578", "-.941"));
        Assert.assertFalse(BigMathUtility.lessThan("-0.578", "-984.941"));
        Assert.assertFalse(BigMathUtility.lessThan("-.0000000578", "-.00941"));
        Assert.assertTrue(BigMathUtility.lessThan("-18.0000000578", "-2.00941"));
        
        //larger cases
        Assert.assertTrue(BigMathUtility.lessThan(
                "15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        
        //larger cases, negative
        Assert.assertTrue(BigMathUtility.lessThan(
                "-15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "-15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertTrue(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.lessThan(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
    }
    
    /**
     * JUnit test of equalTo.
     *
     * @throws Exception When there is an exception.
     * @see BigMathUtility#equalTo(String, String)
     */
    @Test
    public void testEqualTo() throws Exception {
        //standard
        Assert.assertTrue(BigMathUtility.equalTo("1", "1"));
        Assert.assertFalse(BigMathUtility.equalTo("17", "4"));
        Assert.assertFalse(BigMathUtility.equalTo("9", "177"));
        Assert.assertFalse(BigMathUtility.equalTo("0", "88"));
        Assert.assertFalse(BigMathUtility.equalTo("0549", "0"));
        
        //invalid
        try {
            BigMathUtility.equalTo("1", "15s5");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.equalTo("15s5", "1");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        try {
            BigMathUtility.equalTo("15s5", "a");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //negative
        Assert.assertFalse(BigMathUtility.equalTo("-1", "1"));
        Assert.assertFalse(BigMathUtility.equalTo("-17", "4"));
        Assert.assertFalse(BigMathUtility.equalTo("-9", "177"));
        Assert.assertFalse(BigMathUtility.equalTo("-0", "88"));
        Assert.assertFalse(BigMathUtility.equalTo("-0549", "0"));
        Assert.assertFalse(BigMathUtility.equalTo("1", "-1"));
        Assert.assertFalse(BigMathUtility.equalTo("17", "-4"));
        Assert.assertFalse(BigMathUtility.equalTo("9", "-177"));
        Assert.assertFalse(BigMathUtility.equalTo("0", "-88"));
        Assert.assertFalse(BigMathUtility.equalTo("0549", "-0"));
        Assert.assertTrue(BigMathUtility.equalTo("-1", "-1"));
        Assert.assertFalse(BigMathUtility.equalTo("-17", "-4"));
        Assert.assertFalse(BigMathUtility.equalTo("-9", "-177"));
        Assert.assertFalse(BigMathUtility.equalTo("-0", "-88"));
        Assert.assertFalse(BigMathUtility.equalTo("-0549", "-0"));
        
        //decimal
        Assert.assertFalse(BigMathUtility.equalTo("1", "0.1"));
        Assert.assertFalse(BigMathUtility.equalTo("1", ".1"));
        Assert.assertTrue(BigMathUtility.equalTo(".1", ".1"));
        Assert.assertFalse(BigMathUtility.equalTo("3.1", "1.9"));
        Assert.assertFalse(BigMathUtility.equalTo("3.1", "0.1"));
        Assert.assertFalse(BigMathUtility.equalTo("03.1", "20.1"));
        Assert.assertFalse(BigMathUtility.equalTo(".578", ".941"));
        Assert.assertFalse(BigMathUtility.equalTo("0.578", "984.941"));
        Assert.assertFalse(BigMathUtility.equalTo(".0000000578", ".00941"));
        Assert.assertFalse(BigMathUtility.equalTo("18.0000000578", "2.00941"));
        
        //decimal, negative
        Assert.assertFalse(BigMathUtility.equalTo("-1", "0.1"));
        Assert.assertFalse(BigMathUtility.equalTo("-1", ".1"));
        Assert.assertFalse(BigMathUtility.equalTo("-.1", ".1"));
        Assert.assertFalse(BigMathUtility.equalTo("-3.1", "1.9"));
        Assert.assertFalse(BigMathUtility.equalTo("-3.1", "0.1"));
        Assert.assertFalse(BigMathUtility.equalTo("-03.1", "20.1"));
        Assert.assertFalse(BigMathUtility.equalTo("-.578", ".941"));
        Assert.assertFalse(BigMathUtility.equalTo("-0.578", "984.941"));
        Assert.assertFalse(BigMathUtility.equalTo("-.0000000578", ".00941"));
        Assert.assertFalse(BigMathUtility.equalTo("-18.0000000578", "2.00941"));
        Assert.assertFalse(BigMathUtility.equalTo("1", "-0.1"));
        Assert.assertFalse(BigMathUtility.equalTo("1", "-.1"));
        Assert.assertFalse(BigMathUtility.equalTo(".1", "-.1"));
        Assert.assertFalse(BigMathUtility.equalTo("3.1", "-1.9"));
        Assert.assertFalse(BigMathUtility.equalTo("3.1", "-0.1"));
        Assert.assertFalse(BigMathUtility.equalTo("03.1", "-20.1"));
        Assert.assertFalse(BigMathUtility.equalTo(".578", "-.941"));
        Assert.assertFalse(BigMathUtility.equalTo("0.578", "-984.941"));
        Assert.assertFalse(BigMathUtility.equalTo(".0000000578", "-.00941"));
        Assert.assertFalse(BigMathUtility.equalTo("18.0000000578", "-2.00941"));
        Assert.assertFalse(BigMathUtility.equalTo("-1", "-0.1"));
        Assert.assertFalse(BigMathUtility.equalTo("-1", "-.1"));
        Assert.assertTrue(BigMathUtility.equalTo("-.1", "-.1"));
        Assert.assertFalse(BigMathUtility.equalTo("-3.1", "-1.9"));
        Assert.assertFalse(BigMathUtility.equalTo("-3.1", "-0.1"));
        Assert.assertFalse(BigMathUtility.equalTo("-03.1", "-20.1"));
        Assert.assertFalse(BigMathUtility.equalTo("-.578", "-.941"));
        Assert.assertFalse(BigMathUtility.equalTo("-0.578", "-984.941"));
        Assert.assertFalse(BigMathUtility.equalTo("-.0000000578", "-.00941"));
        Assert.assertFalse(BigMathUtility.equalTo("-18.0000000578", "-2.00941"));
        
        //larger cases
        Assert.assertFalse(BigMathUtility.equalTo(
                "15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        
        //larger cases, negative
        Assert.assertFalse(BigMathUtility.equalTo(
                "-15649826500097987154602319879456339009444871265",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564",
                "15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987565",
                "5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564",
                "5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-15649826500097987154602319879456339009444871265",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-15649826500097987154602319879456339009444871265")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987565",
                "-5967945689778787050698789841534979848487446456313280987564")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564",
                "-5967945689778787050698789841534979848487446456313280987565")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-0.000000000000000000000000000000048945494089484366316897451509898750316549",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-0.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987565.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001")
        );
        Assert.assertFalse(BigMathUtility.equalTo(
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000001",
                "-5967945689778787050698789841534979848487446456313280987564.54905498489156465409400000098970456369987875645098465321658498484565000800000000000000000000002")
        );
    }
    
}
