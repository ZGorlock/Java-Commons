/*
 * File:    NumberStringUtilityTest.java
 * Package: commons.math
 * Author:  Zachary Gill
 */

package commons.math;

import java.math.BigDecimal;
import java.util.ArrayList;

import commons.list.ListUtility;
import commons.string.StringUtility;
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
 * JUnit test of NumberStringUtility.
 *
 * @see NumberStringUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({NumberStringUtility.class})
public class NumberStringUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(NumberStringUtilityTest.class);
    
    
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
     * @see NumberStringUtility#MAX_POWER
     * @see NumberStringUtility#MAGNITUDE_MAP
     * @see NumberStringUtility#MAGNITUDE_NAME_MAP
     * @see NumberStringUtility#EXPONENTIAL_NOTATION_PATTERN
     * @see NumberStringUtility#ZERO_STRING_PATTERN
     */
    @Test
    public void testConstants() throws Exception {
        //constants
        
        Assert.assertEquals(3003, NumberStringUtility.MAX_POWER);
        
        Assert.assertEquals(1001, NumberStringUtility.MAGNITUDE_MAP.size());
        Assert.assertEquals(
                ListUtility.removeDuplicates(new ArrayList<>(NumberStringUtility.MAGNITUDE_MAP.keySet())),
                new ArrayList<>(NumberStringUtility.MAGNITUDE_MAP.keySet())
        );
        Assert.assertEquals(
                ListUtility.removeDuplicates(new ArrayList<>(NumberStringUtility.MAGNITUDE_MAP.values())),
                new ArrayList<>(NumberStringUtility.MAGNITUDE_MAP.values())
        );
        
        Assert.assertEquals(1001, NumberStringUtility.MAGNITUDE_NAME_MAP.size());
        Assert.assertEquals(
                ListUtility.removeDuplicates(new ArrayList<>(NumberStringUtility.MAGNITUDE_NAME_MAP.keySet())),
                new ArrayList<>(NumberStringUtility.MAGNITUDE_NAME_MAP.keySet())
        );
        Assert.assertEquals(
                ListUtility.removeDuplicates(new ArrayList<>(NumberStringUtility.MAGNITUDE_NAME_MAP.values())),
                new ArrayList<>(NumberStringUtility.MAGNITUDE_NAME_MAP.values())
        );
        
        //patterns
        
        Assert.assertEquals("-?(?<first>\\d)\\.(?<decimal>\\d+)E(?<mantissa>-?\\d+)", NumberStringUtility.EXPONENTIAL_NOTATION_PATTERN.pattern());
        Assert.assertEquals("^0+$", NumberStringUtility.ZERO_STRING_PATTERN.pattern());
    }
    
    /**
     * JUnit test of stringValueOf.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#stringValueOf(Number)
     */
    @Test
    public void testStringValueOf() throws Exception {
        //simple cases
        Assert.assertEquals("0", NumberStringUtility.stringValueOf(0));
        Assert.assertEquals("1", NumberStringUtility.stringValueOf(1));
        Assert.assertEquals("984", NumberStringUtility.stringValueOf(984));
        Assert.assertEquals("456781", NumberStringUtility.stringValueOf(456781));
        Assert.assertEquals("56428452456781", NumberStringUtility.stringValueOf(56428452456781L));
        
        //exponential notation
        Assert.assertEquals("1156400000000",
                NumberStringUtility.stringValueOf(1.1564e12));
        Assert.assertEquals("845612897541000000000000000000000000000000000000000000000",
                NumberStringUtility.stringValueOf(8.45612897541e56));
        Assert.assertEquals("4600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.stringValueOf(4.6e105));
        
        //negative numbers
        Assert.assertEquals("0", NumberStringUtility.stringValueOf(-0));
        Assert.assertEquals("-1", NumberStringUtility.stringValueOf(-1));
        Assert.assertEquals("-984", NumberStringUtility.stringValueOf(-984));
        Assert.assertEquals("-456781", NumberStringUtility.stringValueOf(-456781));
        Assert.assertEquals("-56428452456781", NumberStringUtility.stringValueOf(-56428452456781L));
        
        //negative numbers, exponential notation
        Assert.assertEquals("-1156400000000",
                NumberStringUtility.stringValueOf(-1.1564e12));
        Assert.assertEquals("-845612897541000000000000000000000000000000000000000000000",
                NumberStringUtility.stringValueOf(-8.45612897541e56));
        Assert.assertEquals("-4600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.stringValueOf(-4.6e105));
        
        //decimals
        Assert.assertEquals("0.1", NumberStringUtility.stringValueOf(.1));
        Assert.assertEquals("0.056481", NumberStringUtility.stringValueOf(.056481));
        Assert.assertEquals("0.0875210023469445", NumberStringUtility.stringValueOf(.0875210023469445));
        Assert.assertEquals("0.0000000000000000000001", NumberStringUtility.stringValueOf(.0000000000000000000001));
        
        //decimals, exponential notation
        Assert.assertEquals("0.0000456541",
                NumberStringUtility.stringValueOf(4.56541e-5));
        Assert.assertEquals("0.0000000000000008156420069",
                NumberStringUtility.stringValueOf(8.156420069e-16));
        Assert.assertEquals("0.0000000000000000000001",
                NumberStringUtility.stringValueOf(1.0e-22));
        Assert.assertEquals("0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000184565770156",
                NumberStringUtility.stringValueOf(1.84565770156e-151));
        
        //negative numbers, decimals
        Assert.assertEquals("-0.1", NumberStringUtility.stringValueOf(-.1));
        Assert.assertEquals("-0.056481", NumberStringUtility.stringValueOf(-.056481));
        Assert.assertEquals("-0.0875210023469445", NumberStringUtility.stringValueOf(-.0875210023469445));
        Assert.assertEquals("-0.0000000000000000000001", NumberStringUtility.stringValueOf(-.0000000000000000000001));
        
        //negative numbers, decimals, exponential notation
        Assert.assertEquals("-0.0000456541",
                NumberStringUtility.stringValueOf(-4.56541e-5));
        Assert.assertEquals("-0.0000000000000008156420069",
                NumberStringUtility.stringValueOf(-8.156420069e-16));
        Assert.assertEquals("-0.0000000000000000000001",
                NumberStringUtility.stringValueOf(-1.0e-22));
        Assert.assertEquals("-0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000184565770156",
                NumberStringUtility.stringValueOf(-1.84565770156e-151));
        
        //mixed numbers
        Assert.assertEquals("0.16542", NumberStringUtility.stringValueOf(0.16542));
        Assert.assertEquals("1.18744", NumberStringUtility.stringValueOf(1.18744));
        Assert.assertEquals("984.8975", NumberStringUtility.stringValueOf(984.8975));
        Assert.assertEquals("456781.4987", NumberStringUtility.stringValueOf(456781.4987));
        Assert.assertEquals("56428481.6874977", NumberStringUtility.stringValueOf(56428481.6874977));
        
        //mixed numbers, exponential notation
        Assert.assertEquals("11564.654798",
                NumberStringUtility.stringValueOf(1.1564654798e4));
        Assert.assertEquals("845.612897",
                NumberStringUtility.stringValueOf(8.45612897e2));
        Assert.assertEquals("4665874.210054",
                NumberStringUtility.stringValueOf(4.665874210054e6));
        
        //mixed numbers, negative numbers
        Assert.assertEquals("-0.16542", NumberStringUtility.stringValueOf(-0.16542));
        Assert.assertEquals("-1.18744", NumberStringUtility.stringValueOf(-1.18744));
        Assert.assertEquals("-984.8975", NumberStringUtility.stringValueOf(-984.8975));
        Assert.assertEquals("-456781.4987", NumberStringUtility.stringValueOf(-456781.4987));
        Assert.assertEquals("-56428481.6874977", NumberStringUtility.stringValueOf(-56428481.6874977));
        
        //mixed numbers, negative numbers, exponential notation
        Assert.assertEquals("-11564.654798",
                NumberStringUtility.stringValueOf(-1.1564654798e4));
        Assert.assertEquals("-845.612897",
                NumberStringUtility.stringValueOf(-8.45612897e2));
        Assert.assertEquals("-4665874.210054",
                NumberStringUtility.stringValueOf(-4.665874210054e6));
        
        //big decimal cases
        Assert.assertEquals("5967945689794436877198887828689582168366902795322725858829",
                NumberStringUtility.stringValueOf(new BigDecimal("5967945689794436877198887828689582168366902795322725858829")));
        Assert.assertEquals("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                NumberStringUtility.stringValueOf(new BigDecimal("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                NumberStringUtility.stringValueOf(new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals("-5967945689794436877198887828689582168366902795322725858829",
                NumberStringUtility.stringValueOf(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829")));
        Assert.assertEquals("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                NumberStringUtility.stringValueOf(new BigDecimal("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                NumberStringUtility.stringValueOf(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
    }
    
    /**
     * JUnit test of numberValueOf.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#numberValueOf(String)
     */
    @Test
    public void testNumberValueOf() throws Exception {
        //simple cases
        Assert.assertEquals(0, NumberStringUtility.numberValueOf("0").intValue());
        Assert.assertEquals(1, NumberStringUtility.numberValueOf("1").intValue());
        Assert.assertEquals(984, NumberStringUtility.numberValueOf("984").intValue());
        Assert.assertEquals(456781, NumberStringUtility.numberValueOf("456781").intValue());
        Assert.assertEquals(56428452456781L, NumberStringUtility.numberValueOf("56428452456781").longValue());
        
        //invalid
        try {
            NumberStringUtility.numberValueOf("156a87");
            Assert.fail();
        } catch (NumberFormatException ignored) {
        }
        
        //exponential notation
        Assert.assertEquals(1.1564e12,
                NumberStringUtility.numberValueOf("1156400000000").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(8.45612897541e56,
                NumberStringUtility.numberValueOf("845612897541000000000000000000000000000000000000000000000").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(4.6e105,
                NumberStringUtility.numberValueOf("4600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000").doubleValue(), TestUtils.DELTA);
        
        //negative numbers
        Assert.assertEquals(-0, NumberStringUtility.numberValueOf("0").intValue());
        Assert.assertEquals(-1, NumberStringUtility.numberValueOf("-1").intValue());
        Assert.assertEquals(-984, NumberStringUtility.numberValueOf("-984").intValue());
        Assert.assertEquals(-456781, NumberStringUtility.numberValueOf("-456781").intValue());
        Assert.assertEquals(-56428452456781L, NumberStringUtility.numberValueOf("-56428452456781").longValue());
        
        //negative numbers, exponential notation
        Assert.assertEquals(-1.1564e12,
                NumberStringUtility.numberValueOf("-1156400000000").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-8.45612897541e56,
                NumberStringUtility.numberValueOf("-845612897541000000000000000000000000000000000000000000000").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-4.6e105,
                NumberStringUtility.numberValueOf("-4600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000").doubleValue(), TestUtils.DELTA);
        
        //decimals
        Assert.assertEquals(.1, NumberStringUtility.numberValueOf("0.1").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(.056481, NumberStringUtility.numberValueOf("0.056481").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(.0875210023469445, NumberStringUtility.numberValueOf("0.0875210023469445").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(.0000000000000000000001, NumberStringUtility.numberValueOf("0.0000000000000000000001").doubleValue(), TestUtils.DELTA);
        
        //decimals, exponential notation
        Assert.assertEquals(4.56541e-5,
                NumberStringUtility.numberValueOf("0.0000456541").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(8.156420069e-16,
                NumberStringUtility.numberValueOf("0.0000000000000008156420069").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.0e-22,
                NumberStringUtility.numberValueOf("0.0000000000000000000001").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.84565770156e-151,
                NumberStringUtility.numberValueOf("0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000184565770156").doubleValue(), TestUtils.DELTA);
        
        //negative numbers, decimals
        Assert.assertEquals(-.1, NumberStringUtility.numberValueOf("-0.1").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-.056481, NumberStringUtility.numberValueOf("-0.056481").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-.0875210023469445, NumberStringUtility.numberValueOf("-0.0875210023469445").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-.0000000000000000000001, NumberStringUtility.numberValueOf("-0.0000000000000000000001").doubleValue(), TestUtils.DELTA);
        
        //negative numbers, decimals, exponential notation
        Assert.assertEquals(-4.56541e-5,
                NumberStringUtility.numberValueOf("-0.0000456541").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-8.156420069e-16,
                NumberStringUtility.numberValueOf("-0.0000000000000008156420069").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1.0e-22,
                NumberStringUtility.numberValueOf("-0.0000000000000000000001").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1.84565770156e-151,
                NumberStringUtility.numberValueOf("-0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000184565770156").doubleValue(), TestUtils.DELTA);
        
        //mixed numbers
        Assert.assertEquals(0.16542, NumberStringUtility.numberValueOf("0.16542").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.18744, NumberStringUtility.numberValueOf("1.18744").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(984.8975, NumberStringUtility.numberValueOf("984.8975").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(456781.4987, NumberStringUtility.numberValueOf("456781.4987").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(56428481.6874977, NumberStringUtility.numberValueOf("56428481.6874977").doubleValue(), TestUtils.DELTA);
        
        //mixed numbers, exponential notation
        Assert.assertEquals(1.1564654798e4,
                NumberStringUtility.numberValueOf("11564.654798").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(8.45612897e2,
                NumberStringUtility.numberValueOf("845.612897").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(4.665874210054e6,
                NumberStringUtility.numberValueOf("4665874.210054").doubleValue(), TestUtils.DELTA);
        
        //mixed numbers, negative numbers
        Assert.assertEquals(-0.16542, NumberStringUtility.numberValueOf("-0.16542").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-1.18744, NumberStringUtility.numberValueOf("-1.18744").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-984.8975, NumberStringUtility.numberValueOf("-984.8975").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-456781.4987, NumberStringUtility.numberValueOf("-456781.4987").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-56428481.6874977, NumberStringUtility.numberValueOf("-56428481.6874977").doubleValue(), TestUtils.DELTA);
        
        //mixed numbers, negative numbers, exponential notation
        Assert.assertEquals(-1.1564654798e4,
                NumberStringUtility.numberValueOf("-11564.654798").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-8.45612897e2,
                NumberStringUtility.numberValueOf("-845.612897").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-4.665874210054e6,
                NumberStringUtility.numberValueOf("-4665874.210054").doubleValue(), TestUtils.DELTA);
        
        //big decimal cases
        Assert.assertEquals(new BigDecimal("5967945689794436877198887828689582168366902795322725858829"),
                NumberStringUtility.numberValueOf("5967945689794436877198887828689582168366902795322725858829"));
        Assert.assertEquals(new BigDecimal("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                NumberStringUtility.numberValueOf("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"));
        Assert.assertEquals(new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                NumberStringUtility.numberValueOf("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"));
        Assert.assertEquals(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829"),
                NumberStringUtility.numberValueOf("-5967945689794436877198887828689582168366902795322725858829"));
        Assert.assertEquals(new BigDecimal("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                NumberStringUtility.numberValueOf("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"));
        Assert.assertEquals(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                NumberStringUtility.numberValueOf("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"));
    }
    
    /**
     * JUnit test of numberToNumberPhrase.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#numberToNumberPhrase(Number)
     */
    @Test
    public void testNumberToNumberPhrase() throws Exception {
        //simple cases
        Assert.assertEquals("zero", NumberStringUtility.numberToNumberPhrase(0));
        Assert.assertEquals("one", NumberStringUtility.numberToNumberPhrase(1));
        Assert.assertEquals("two", NumberStringUtility.numberToNumberPhrase(2));
        Assert.assertEquals("three", NumberStringUtility.numberToNumberPhrase(3));
        Assert.assertEquals("four", NumberStringUtility.numberToNumberPhrase(4));
        Assert.assertEquals("five", NumberStringUtility.numberToNumberPhrase(5));
        Assert.assertEquals("six", NumberStringUtility.numberToNumberPhrase(6));
        Assert.assertEquals("seven", NumberStringUtility.numberToNumberPhrase(7));
        Assert.assertEquals("eight", NumberStringUtility.numberToNumberPhrase(8));
        Assert.assertEquals("nine", NumberStringUtility.numberToNumberPhrase(9));
        Assert.assertEquals("ten", NumberStringUtility.numberToNumberPhrase(10));
        Assert.assertEquals("eleven", NumberStringUtility.numberToNumberPhrase(11));
        Assert.assertEquals("twelve", NumberStringUtility.numberToNumberPhrase(12));
        Assert.assertEquals("thirteen", NumberStringUtility.numberToNumberPhrase(13));
        Assert.assertEquals("fourteen", NumberStringUtility.numberToNumberPhrase(14));
        Assert.assertEquals("fifteen", NumberStringUtility.numberToNumberPhrase(15));
        Assert.assertEquals("sixteen", NumberStringUtility.numberToNumberPhrase(16));
        Assert.assertEquals("seventeen", NumberStringUtility.numberToNumberPhrase(17));
        Assert.assertEquals("eighteen", NumberStringUtility.numberToNumberPhrase(18));
        Assert.assertEquals("nineteen", NumberStringUtility.numberToNumberPhrase(19));
        
        //two digit cases
        Assert.assertEquals("twenty", NumberStringUtility.numberToNumberPhrase(20));
        Assert.assertEquals("twenty five", NumberStringUtility.numberToNumberPhrase(25));
        Assert.assertEquals("thirty", NumberStringUtility.numberToNumberPhrase(30));
        Assert.assertEquals("thirty one", NumberStringUtility.numberToNumberPhrase(31));
        Assert.assertEquals("forty", NumberStringUtility.numberToNumberPhrase(40));
        Assert.assertEquals("forty six", NumberStringUtility.numberToNumberPhrase(46));
        Assert.assertEquals("fifty", NumberStringUtility.numberToNumberPhrase(50));
        Assert.assertEquals("fifty two", NumberStringUtility.numberToNumberPhrase(52));
        Assert.assertEquals("sixty", NumberStringUtility.numberToNumberPhrase(60));
        Assert.assertEquals("sixty eight", NumberStringUtility.numberToNumberPhrase(68));
        Assert.assertEquals("seventy", NumberStringUtility.numberToNumberPhrase(70));
        Assert.assertEquals("seventy seven", NumberStringUtility.numberToNumberPhrase(77));
        Assert.assertEquals("eighty", NumberStringUtility.numberToNumberPhrase(80));
        Assert.assertEquals("eighty three", NumberStringUtility.numberToNumberPhrase(83));
        Assert.assertEquals("ninety", NumberStringUtility.numberToNumberPhrase(90));
        Assert.assertEquals("ninety four", NumberStringUtility.numberToNumberPhrase(94));
        Assert.assertEquals("ninety nine", NumberStringUtility.numberToNumberPhrase(99));
        
        //three digit cases
        Assert.assertEquals("one hundred", NumberStringUtility.numberToNumberPhrase(100));
        Assert.assertEquals("one hundred and fifty four", NumberStringUtility.numberToNumberPhrase(154));
        Assert.assertEquals("two hundred", NumberStringUtility.numberToNumberPhrase(200));
        Assert.assertEquals("two hundred and nineteen", NumberStringUtility.numberToNumberPhrase(219));
        Assert.assertEquals("three hundred", NumberStringUtility.numberToNumberPhrase(300));
        Assert.assertEquals("three hundred and seventy seven", NumberStringUtility.numberToNumberPhrase(377));
        Assert.assertEquals("four hundred", NumberStringUtility.numberToNumberPhrase(400));
        Assert.assertEquals("four hundred and sixty eight", NumberStringUtility.numberToNumberPhrase(468));
        Assert.assertEquals("five hundred", NumberStringUtility.numberToNumberPhrase(500));
        Assert.assertEquals("five hundred and ninety four", NumberStringUtility.numberToNumberPhrase(594));
        Assert.assertEquals("six hundred", NumberStringUtility.numberToNumberPhrase(600));
        Assert.assertEquals("six hundred and eight", NumberStringUtility.numberToNumberPhrase(608));
        Assert.assertEquals("seven hundred", NumberStringUtility.numberToNumberPhrase(700));
        Assert.assertEquals("seven hundred and seventy six", NumberStringUtility.numberToNumberPhrase(776));
        Assert.assertEquals("eight hundred", NumberStringUtility.numberToNumberPhrase(800));
        Assert.assertEquals("eight hundred and forty two", NumberStringUtility.numberToNumberPhrase(842));
        Assert.assertEquals("nine hundred", NumberStringUtility.numberToNumberPhrase(900));
        Assert.assertEquals("nine hundred and ninety nine", NumberStringUtility.numberToNumberPhrase(999));
        
        //larger cases
        Assert.assertEquals("one thousand",
                NumberStringUtility.numberToNumberPhrase(1000));
        Assert.assertEquals("six hundred and forty three thousand eight hundred and twenty two",
                NumberStringUtility.numberToNumberPhrase(643822));
        Assert.assertEquals("fifteen million six hundred and twenty one thousand nine hundred and seven",
                NumberStringUtility.numberToNumberPhrase(15621907));
        Assert.assertEquals("nine hundred and forty six billion one hundred and four million sixty seven thousand and one",
                NumberStringUtility.numberToNumberPhrase(946104067001L));
        Assert.assertEquals("one hundred and ninety nine trillion four hundred and eighty six billion two hundred and seventy three million four hundred and sixteen thousand eight hundred and twenty one",
                NumberStringUtility.numberToNumberPhrase(199486273416821L));
        Assert.assertEquals("one hundred and ninety nine trillion four hundred and eighty six billion eight hundred and twenty one",
                NumberStringUtility.numberToNumberPhrase(199486000000821L));
        Assert.assertEquals("one quintillion nine hundred and ninety four quadrillion eight hundred and sixty trillion eight hundred and twenty one million and fifty four",
                NumberStringUtility.numberToNumberPhrase(1994860000821000054L));
        
        //extremely large cases
        Assert.assertEquals("three septendecillion four hundred and sixty five sedecillion eight hundred and seventy four quinquadecillion one hundred and twenty two quattuordecillion",
                NumberStringUtility.numberToNumberPhrase(3.465874122e54));
        Assert.assertEquals("ninety five septendecillion six hundred and sixty one sedecillion one hundred and fifty six quinquadecillion four hundred and sixty quattuordecillion",
                NumberStringUtility.numberToNumberPhrase(9.566115646e55));
        Assert.assertEquals("one hundred and eighty seven septendecillion four hundred and forty two sedecillion ninety quinquadecillion five hundred quattuordecillion",
                NumberStringUtility.numberToNumberPhrase(1.874420905e56));
        Assert.assertEquals("two octodecillion ninety seven septendecillion seven hundred and fifty one sedecillion two hundred and seventy four quinquadecillion",
                NumberStringUtility.numberToNumberPhrase(2.097751274e57));
        Assert.assertEquals("seven quinquatrigintillion four hundred and sixty one quattuortrigintillion one hundred and twenty three trestrigintillion eight hundred and seventy seven duotrigintillion",
                NumberStringUtility.numberToNumberPhrase(7.461123877e108));
        Assert.assertEquals("four duosexagintillion forty two sexagintillion three hundred and thirty nine novenquinquagintillion",
                NumberStringUtility.numberToNumberPhrase(4.000042339e189));
        Assert.assertEquals("eighty four uncentillion five hundred and nineteen centillion nine hundred and seventy four novenonagintillion six hundred and thirty octononagintillion",
                NumberStringUtility.numberToNumberPhrase(8.451997463e307));
        
        //negative cases
        Assert.assertEquals("zero", NumberStringUtility.numberToNumberPhrase(-0));
        Assert.assertEquals("negative two", NumberStringUtility.numberToNumberPhrase(-2));
        Assert.assertEquals("negative four", NumberStringUtility.numberToNumberPhrase(-4));
        Assert.assertEquals("negative seven", NumberStringUtility.numberToNumberPhrase(-7));
        Assert.assertEquals("negative thirteen", NumberStringUtility.numberToNumberPhrase(-13));
        Assert.assertEquals("negative fourteen", NumberStringUtility.numberToNumberPhrase(-14));
        Assert.assertEquals("negative nineteen", NumberStringUtility.numberToNumberPhrase(-19));
        Assert.assertEquals("negative forty", NumberStringUtility.numberToNumberPhrase(-40));
        Assert.assertEquals("negative forty six", NumberStringUtility.numberToNumberPhrase(-46));
        Assert.assertEquals("negative fifty two", NumberStringUtility.numberToNumberPhrase(-52));
        Assert.assertEquals("negative seventy seven", NumberStringUtility.numberToNumberPhrase(-77));
        Assert.assertEquals("negative eighty", NumberStringUtility.numberToNumberPhrase(-80));
        Assert.assertEquals("negative ninety nine", NumberStringUtility.numberToNumberPhrase(-99));
        Assert.assertEquals("negative two hundred and nineteen", NumberStringUtility.numberToNumberPhrase(-219));
        Assert.assertEquals("negative three hundred", NumberStringUtility.numberToNumberPhrase(-300));
        Assert.assertEquals("negative five hundred and ninety four", NumberStringUtility.numberToNumberPhrase(-594));
        Assert.assertEquals("negative seven hundred and seventy six", NumberStringUtility.numberToNumberPhrase(-776));
        Assert.assertEquals("negative nine hundred", NumberStringUtility.numberToNumberPhrase(-900));
        Assert.assertEquals("negative nine hundred and ninety nine", NumberStringUtility.numberToNumberPhrase(-999));
        Assert.assertEquals("negative six hundred and forty three thousand eight hundred and twenty two",
                NumberStringUtility.numberToNumberPhrase(-643822));
        Assert.assertEquals("negative nine hundred and forty six billion one hundred and four million sixty seven thousand and one",
                NumberStringUtility.numberToNumberPhrase(-946104067001L));
        Assert.assertEquals("negative one hundred and ninety nine trillion four hundred and eighty six billion two hundred and seventy three million four hundred and sixteen thousand eight hundred and twenty one",
                NumberStringUtility.numberToNumberPhrase(-199486273416821L));
        Assert.assertEquals("negative one hundred and ninety nine trillion four hundred and eighty six billion eight hundred and twenty one",
                NumberStringUtility.numberToNumberPhrase(-199486000000821L));
        Assert.assertEquals("negative three septendecillion four hundred and sixty five sedecillion eight hundred and seventy four quinquadecillion one hundred and twenty two quattuordecillion",
                NumberStringUtility.numberToNumberPhrase(-3.465874122e54));
        Assert.assertEquals("negative seven quinquatrigintillion four hundred and sixty one quattuortrigintillion one hundred and twenty three trestrigintillion eight hundred and seventy seven duotrigintillion",
                NumberStringUtility.numberToNumberPhrase(-7.461123877e108));
        Assert.assertEquals("negative eighty four uncentillion five hundred and nineteen centillion nine hundred and seventy four novenonagintillion six hundred and thirty octononagintillion",
                NumberStringUtility.numberToNumberPhrase(-8.451997463e307));
        
        //decimal cases
        Assert.assertEquals("zero point one", NumberStringUtility.numberToNumberPhrase(0.1));
        Assert.assertEquals("two point one five four two", NumberStringUtility.numberToNumberPhrase(2.1542));
        Assert.assertEquals("nineteen point zero zero eight", NumberStringUtility.numberToNumberPhrase(19.008));
        Assert.assertEquals("forty point seven four", NumberStringUtility.numberToNumberPhrase(40.74));
        Assert.assertEquals("three hundred point nine one four one", NumberStringUtility.numberToNumberPhrase(300.9141));
        Assert.assertEquals("five hundred and ninety four", NumberStringUtility.numberToNumberPhrase(594.000));
        Assert.assertEquals("seven hundred and seventy six point four one", NumberStringUtility.numberToNumberPhrase(776.410));
        Assert.assertEquals("six hundred and forty three thousand eight hundred and twenty two point nine six four one",
                NumberStringUtility.numberToNumberPhrase(643822.9641));
        
        //big decimal cases
        Assert.assertEquals("five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine",
                NumberStringUtility.numberToNumberPhrase(new BigDecimal("5967945689794436877198887828689582168366902795322725858829")));
        Assert.assertEquals("zero point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one",
                NumberStringUtility.numberToNumberPhrase(new BigDecimal("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals("five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one",
                NumberStringUtility.numberToNumberPhrase(new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals("negative five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine",
                NumberStringUtility.numberToNumberPhrase(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829")));
        Assert.assertEquals("negative zero point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one",
                NumberStringUtility.numberToNumberPhrase(new BigDecimal("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals("negative five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one",
                NumberStringUtility.numberToNumberPhrase(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        
        //overflow
        Assert.assertEquals("ten thousand millinillion",
                NumberStringUtility.numberToNumberPhrase(new BigDecimal("1" + StringUtility.fillStringOfLength('0', 3007))));
        Assert.assertEquals("fourteen quadrillion seven hundred and eighty six trillion millinillion eight thousand nine hundred and seventy four point zero one six six",
                NumberStringUtility.numberToNumberPhrase(new BigDecimal("14786" + StringUtility.fillStringOfLength('0', 3011) + "8974.0166")));
    }
    
    /**
     * JUnit test of numberStringToNumberPhrase.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#numberStringToNumberPhrase(String)
     */
    @Test
    public void testNumberStringToNumberPhrase() throws Exception {
        //simple cases
        Assert.assertEquals("zero", NumberStringUtility.numberStringToNumberPhrase("0"));
        Assert.assertEquals("one", NumberStringUtility.numberStringToNumberPhrase("1"));
        Assert.assertEquals("two", NumberStringUtility.numberStringToNumberPhrase("2"));
        Assert.assertEquals("three", NumberStringUtility.numberStringToNumberPhrase("3"));
        Assert.assertEquals("four", NumberStringUtility.numberStringToNumberPhrase("4"));
        Assert.assertEquals("five", NumberStringUtility.numberStringToNumberPhrase("5"));
        Assert.assertEquals("six", NumberStringUtility.numberStringToNumberPhrase("6"));
        Assert.assertEquals("seven", NumberStringUtility.numberStringToNumberPhrase("7"));
        Assert.assertEquals("eight", NumberStringUtility.numberStringToNumberPhrase("8"));
        Assert.assertEquals("nine", NumberStringUtility.numberStringToNumberPhrase("9"));
        Assert.assertEquals("ten", NumberStringUtility.numberStringToNumberPhrase("10"));
        Assert.assertEquals("eleven", NumberStringUtility.numberStringToNumberPhrase("11"));
        Assert.assertEquals("twelve", NumberStringUtility.numberStringToNumberPhrase("12"));
        Assert.assertEquals("thirteen", NumberStringUtility.numberStringToNumberPhrase("13"));
        Assert.assertEquals("fourteen", NumberStringUtility.numberStringToNumberPhrase("14"));
        Assert.assertEquals("fifteen", NumberStringUtility.numberStringToNumberPhrase("15"));
        Assert.assertEquals("sixteen", NumberStringUtility.numberStringToNumberPhrase("16"));
        Assert.assertEquals("seventeen", NumberStringUtility.numberStringToNumberPhrase("17"));
        Assert.assertEquals("eighteen", NumberStringUtility.numberStringToNumberPhrase("18"));
        Assert.assertEquals("nineteen", NumberStringUtility.numberStringToNumberPhrase("19"));
        
        //two digit cases
        Assert.assertEquals("twenty", NumberStringUtility.numberStringToNumberPhrase("20"));
        Assert.assertEquals("twenty five", NumberStringUtility.numberStringToNumberPhrase("25"));
        Assert.assertEquals("thirty", NumberStringUtility.numberStringToNumberPhrase("30"));
        Assert.assertEquals("thirty one", NumberStringUtility.numberStringToNumberPhrase("31"));
        Assert.assertEquals("forty", NumberStringUtility.numberStringToNumberPhrase("40"));
        Assert.assertEquals("forty six", NumberStringUtility.numberStringToNumberPhrase("46"));
        Assert.assertEquals("fifty", NumberStringUtility.numberStringToNumberPhrase("50"));
        Assert.assertEquals("fifty two", NumberStringUtility.numberStringToNumberPhrase("52"));
        Assert.assertEquals("sixty", NumberStringUtility.numberStringToNumberPhrase("60"));
        Assert.assertEquals("sixty eight", NumberStringUtility.numberStringToNumberPhrase("68"));
        Assert.assertEquals("seventy", NumberStringUtility.numberStringToNumberPhrase("70"));
        Assert.assertEquals("seventy seven", NumberStringUtility.numberStringToNumberPhrase("77"));
        Assert.assertEquals("eighty", NumberStringUtility.numberStringToNumberPhrase("80"));
        Assert.assertEquals("eighty three", NumberStringUtility.numberStringToNumberPhrase("83"));
        Assert.assertEquals("ninety", NumberStringUtility.numberStringToNumberPhrase("90"));
        Assert.assertEquals("ninety four", NumberStringUtility.numberStringToNumberPhrase("94"));
        Assert.assertEquals("ninety nine", NumberStringUtility.numberStringToNumberPhrase("99"));
        
        //three digit cases
        Assert.assertEquals("one hundred", NumberStringUtility.numberStringToNumberPhrase("100"));
        Assert.assertEquals("one hundred and fifty four", NumberStringUtility.numberStringToNumberPhrase("154"));
        Assert.assertEquals("two hundred", NumberStringUtility.numberStringToNumberPhrase("200"));
        Assert.assertEquals("two hundred and nineteen", NumberStringUtility.numberStringToNumberPhrase("219"));
        Assert.assertEquals("three hundred", NumberStringUtility.numberStringToNumberPhrase("300"));
        Assert.assertEquals("three hundred and seventy seven", NumberStringUtility.numberStringToNumberPhrase("377"));
        Assert.assertEquals("four hundred", NumberStringUtility.numberStringToNumberPhrase("400"));
        Assert.assertEquals("four hundred and sixty eight", NumberStringUtility.numberStringToNumberPhrase("468"));
        Assert.assertEquals("five hundred", NumberStringUtility.numberStringToNumberPhrase("500"));
        Assert.assertEquals("five hundred and ninety four", NumberStringUtility.numberStringToNumberPhrase("594"));
        Assert.assertEquals("six hundred", NumberStringUtility.numberStringToNumberPhrase("600"));
        Assert.assertEquals("six hundred and eight", NumberStringUtility.numberStringToNumberPhrase("608"));
        Assert.assertEquals("seven hundred", NumberStringUtility.numberStringToNumberPhrase("700"));
        Assert.assertEquals("seven hundred and seventy six", NumberStringUtility.numberStringToNumberPhrase("776"));
        Assert.assertEquals("eight hundred", NumberStringUtility.numberStringToNumberPhrase("800"));
        Assert.assertEquals("eight hundred and forty two", NumberStringUtility.numberStringToNumberPhrase("842"));
        Assert.assertEquals("nine hundred", NumberStringUtility.numberStringToNumberPhrase("900"));
        Assert.assertEquals("nine hundred and ninety nine", NumberStringUtility.numberStringToNumberPhrase("999"));
        
        //larger cases
        Assert.assertEquals("one thousand",
                NumberStringUtility.numberStringToNumberPhrase("1000"));
        Assert.assertEquals("six hundred and forty three thousand eight hundred and twenty two",
                NumberStringUtility.numberStringToNumberPhrase("643822"));
        Assert.assertEquals("fifteen million six hundred and twenty one thousand nine hundred and seven",
                NumberStringUtility.numberStringToNumberPhrase("15621907"));
        Assert.assertEquals("nine hundred and forty six billion one hundred and four million sixty seven thousand and one",
                NumberStringUtility.numberStringToNumberPhrase("946104067001"));
        Assert.assertEquals("one hundred and ninety nine trillion four hundred and eighty six billion two hundred and seventy three million four hundred and sixteen thousand eight hundred and twenty one",
                NumberStringUtility.numberStringToNumberPhrase("199486273416821"));
        Assert.assertEquals("one hundred and ninety nine trillion four hundred and eighty six billion eight hundred and twenty one",
                NumberStringUtility.numberStringToNumberPhrase("199486000000821"));
        Assert.assertEquals("one quintillion nine hundred and ninety four quadrillion eight hundred and sixty trillion eight hundred and twenty one million and fifty four",
                NumberStringUtility.numberStringToNumberPhrase("1994860000821000054"));
        
        //extremely large cases
        Assert.assertEquals("three septendecillion four hundred and sixty five sedecillion eight hundred and seventy four quinquadecillion one hundred and twenty two quattuordecillion",
                NumberStringUtility.numberStringToNumberPhrase("3.465874122e54"));
        Assert.assertEquals("ninety five septendecillion six hundred and sixty one sedecillion one hundred and fifty six quinquadecillion four hundred and sixty quattuordecillion",
                NumberStringUtility.numberStringToNumberPhrase("9.566115646e55"));
        Assert.assertEquals("one hundred and eighty seven septendecillion four hundred and forty two sedecillion ninety quinquadecillion five hundred quattuordecillion",
                NumberStringUtility.numberStringToNumberPhrase("1.874420905e56"));
        Assert.assertEquals("two octodecillion ninety seven septendecillion seven hundred and fifty one sedecillion two hundred and seventy four quinquadecillion",
                NumberStringUtility.numberStringToNumberPhrase("2.097751274e57"));
        Assert.assertEquals("seven quinquatrigintillion four hundred and sixty one quattuortrigintillion one hundred and twenty three trestrigintillion eight hundred and seventy seven duotrigintillion",
                NumberStringUtility.numberStringToNumberPhrase("7.461123877e108"));
        Assert.assertEquals("four duosexagintillion forty two sexagintillion three hundred and thirty nine novenquinquagintillion",
                NumberStringUtility.numberStringToNumberPhrase("4.000042339e189"));
        Assert.assertEquals("eighty four uncentillion five hundred and nineteen centillion nine hundred and seventy four novenonagintillion six hundred and thirty octononagintillion",
                NumberStringUtility.numberStringToNumberPhrase("8.451997463e307"));
        
        //negative cases
        Assert.assertEquals("zero", NumberStringUtility.numberStringToNumberPhrase("-0"));
        Assert.assertEquals("negative two", NumberStringUtility.numberStringToNumberPhrase("-2"));
        Assert.assertEquals("negative four", NumberStringUtility.numberStringToNumberPhrase("-4"));
        Assert.assertEquals("negative seven", NumberStringUtility.numberStringToNumberPhrase("-7"));
        Assert.assertEquals("negative thirteen", NumberStringUtility.numberStringToNumberPhrase("-13"));
        Assert.assertEquals("negative fourteen", NumberStringUtility.numberStringToNumberPhrase("-14"));
        Assert.assertEquals("negative nineteen", NumberStringUtility.numberStringToNumberPhrase("-19"));
        Assert.assertEquals("negative forty", NumberStringUtility.numberStringToNumberPhrase("-40"));
        Assert.assertEquals("negative forty six", NumberStringUtility.numberStringToNumberPhrase("-46"));
        Assert.assertEquals("negative fifty two", NumberStringUtility.numberStringToNumberPhrase("-52"));
        Assert.assertEquals("negative seventy seven", NumberStringUtility.numberStringToNumberPhrase("-77"));
        Assert.assertEquals("negative eighty", NumberStringUtility.numberStringToNumberPhrase("-80"));
        Assert.assertEquals("negative ninety nine", NumberStringUtility.numberStringToNumberPhrase("-99"));
        Assert.assertEquals("negative two hundred and nineteen", NumberStringUtility.numberStringToNumberPhrase("-219"));
        Assert.assertEquals("negative three hundred", NumberStringUtility.numberStringToNumberPhrase("-300"));
        Assert.assertEquals("negative five hundred and ninety four", NumberStringUtility.numberStringToNumberPhrase("-594"));
        Assert.assertEquals("negative seven hundred and seventy six", NumberStringUtility.numberStringToNumberPhrase("-776"));
        Assert.assertEquals("negative nine hundred", NumberStringUtility.numberStringToNumberPhrase("-900"));
        Assert.assertEquals("negative nine hundred and ninety nine", NumberStringUtility.numberStringToNumberPhrase("-999"));
        Assert.assertEquals("negative six hundred and forty three thousand eight hundred and twenty two",
                NumberStringUtility.numberStringToNumberPhrase("-643822"));
        Assert.assertEquals("negative nine hundred and forty six billion one hundred and four million sixty seven thousand and one",
                NumberStringUtility.numberStringToNumberPhrase("-946104067001"));
        Assert.assertEquals("negative one hundred and ninety nine trillion four hundred and eighty six billion two hundred and seventy three million four hundred and sixteen thousand eight hundred and twenty one",
                NumberStringUtility.numberStringToNumberPhrase("-199486273416821"));
        Assert.assertEquals("negative one hundred and ninety nine trillion four hundred and eighty six billion eight hundred and twenty one",
                NumberStringUtility.numberStringToNumberPhrase("-199486000000821"));
        Assert.assertEquals("negative three septendecillion four hundred and sixty five sedecillion eight hundred and seventy four quinquadecillion one hundred and twenty two quattuordecillion",
                NumberStringUtility.numberStringToNumberPhrase("-3.465874122e54"));
        Assert.assertEquals("negative seven quinquatrigintillion four hundred and sixty one quattuortrigintillion one hundred and twenty three trestrigintillion eight hundred and seventy seven duotrigintillion",
                NumberStringUtility.numberStringToNumberPhrase("-7.461123877e108"));
        Assert.assertEquals("negative eighty four uncentillion five hundred and nineteen centillion nine hundred and seventy four novenonagintillion six hundred and thirty octononagintillion",
                NumberStringUtility.numberStringToNumberPhrase("-8.451997463e307"));
        
        //decimal cases
        Assert.assertEquals("zero point one", NumberStringUtility.numberStringToNumberPhrase("0.1"));
        Assert.assertEquals("two point one five four two", NumberStringUtility.numberStringToNumberPhrase("2.1542"));
        Assert.assertEquals("nineteen point zero zero eight", NumberStringUtility.numberStringToNumberPhrase("19.008"));
        Assert.assertEquals("forty point seven four", NumberStringUtility.numberStringToNumberPhrase("40.74"));
        Assert.assertEquals("three hundred point nine one four one", NumberStringUtility.numberStringToNumberPhrase("300.9141"));
        Assert.assertEquals("five hundred and ninety four", NumberStringUtility.numberStringToNumberPhrase("594.000"));
        Assert.assertEquals("seven hundred and seventy six point four one", NumberStringUtility.numberStringToNumberPhrase("776.410"));
        Assert.assertEquals("six hundred and forty three thousand eight hundred and twenty two point nine six four one",
                NumberStringUtility.numberStringToNumberPhrase("643822.9641"));
        
        //big decimal cases
        Assert.assertEquals("five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine",
                NumberStringUtility.numberStringToNumberPhrase("5967945689794436877198887828689582168366902795322725858829"));
        Assert.assertEquals("zero point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one",
                NumberStringUtility.numberStringToNumberPhrase("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"));
        Assert.assertEquals("five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one",
                NumberStringUtility.numberStringToNumberPhrase("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"));
        Assert.assertEquals("negative five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine",
                NumberStringUtility.numberStringToNumberPhrase("-5967945689794436877198887828689582168366902795322725858829"));
        Assert.assertEquals("negative zero point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one",
                NumberStringUtility.numberStringToNumberPhrase("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"));
        Assert.assertEquals("negative five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one",
                NumberStringUtility.numberStringToNumberPhrase("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"));
        
        //overflow
        Assert.assertEquals("ten thousand millinillion",
                NumberStringUtility.numberStringToNumberPhrase("1" + StringUtility.fillStringOfLength('0', 3007)));
        Assert.assertEquals("fourteen quadrillion seven hundred and eighty six trillion millinillion eight thousand nine hundred and seventy four point zero one six six",
                NumberStringUtility.numberStringToNumberPhrase("14786" + StringUtility.fillStringOfLength('0', 3011) + "8974.0166"));
    }
    
    /**
     * JUnit test of numberPhraseToNumber.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#numberPhraseToNumber(String)
     */
    @Test
    public void testPhraseToNumber() throws Exception {
        //simple cases
        Assert.assertEquals(0, NumberStringUtility.numberPhraseToNumber("zero").intValue());
        Assert.assertEquals(1, NumberStringUtility.numberPhraseToNumber("one").intValue());
        Assert.assertEquals(2, NumberStringUtility.numberPhraseToNumber("two").intValue());
        Assert.assertEquals(3, NumberStringUtility.numberPhraseToNumber("three").intValue());
        Assert.assertEquals(4, NumberStringUtility.numberPhraseToNumber("four").intValue());
        Assert.assertEquals(5, NumberStringUtility.numberPhraseToNumber("five").intValue());
        Assert.assertEquals(6, NumberStringUtility.numberPhraseToNumber("six").intValue());
        Assert.assertEquals(7, NumberStringUtility.numberPhraseToNumber("seven").intValue());
        Assert.assertEquals(8, NumberStringUtility.numberPhraseToNumber("eight").intValue());
        Assert.assertEquals(9, NumberStringUtility.numberPhraseToNumber("nine").intValue());
        Assert.assertEquals(10, NumberStringUtility.numberPhraseToNumber("ten").intValue());
        Assert.assertEquals(11, NumberStringUtility.numberPhraseToNumber("eleven").intValue());
        Assert.assertEquals(12, NumberStringUtility.numberPhraseToNumber("twelve").intValue());
        Assert.assertEquals(13, NumberStringUtility.numberPhraseToNumber("thirteen").intValue());
        Assert.assertEquals(14, NumberStringUtility.numberPhraseToNumber("fourteen").intValue());
        Assert.assertEquals(15, NumberStringUtility.numberPhraseToNumber("fifteen").intValue());
        Assert.assertEquals(16, NumberStringUtility.numberPhraseToNumber("sixteen").intValue());
        Assert.assertEquals(17, NumberStringUtility.numberPhraseToNumber("seventeen").intValue());
        Assert.assertEquals(18, NumberStringUtility.numberPhraseToNumber("eighteen").intValue());
        Assert.assertEquals(19, NumberStringUtility.numberPhraseToNumber("nineteen").intValue());
        Assert.assertEquals(19, NumberStringUtility.numberPhraseToNumber("19").intValue());
        
        //two digit cases
        Assert.assertEquals(20, NumberStringUtility.numberPhraseToNumber("twenty").intValue());
        Assert.assertEquals(25, NumberStringUtility.numberPhraseToNumber("twenty five").intValue());
        Assert.assertEquals(30, NumberStringUtility.numberPhraseToNumber("thirty").intValue());
        Assert.assertEquals(31, NumberStringUtility.numberPhraseToNumber("thirty one").intValue());
        Assert.assertEquals(40, NumberStringUtility.numberPhraseToNumber("forty").intValue());
        Assert.assertEquals(46, NumberStringUtility.numberPhraseToNumber("forty six").intValue());
        Assert.assertEquals(50, NumberStringUtility.numberPhraseToNumber("fifty").intValue());
        Assert.assertEquals(52, NumberStringUtility.numberPhraseToNumber("fifty two").intValue());
        Assert.assertEquals(60, NumberStringUtility.numberPhraseToNumber("sixty").intValue());
        Assert.assertEquals(68, NumberStringUtility.numberPhraseToNumber("sixty eight").intValue());
        Assert.assertEquals(70, NumberStringUtility.numberPhraseToNumber("seventy").intValue());
        Assert.assertEquals(77, NumberStringUtility.numberPhraseToNumber("seventy seven").intValue());
        Assert.assertEquals(80, NumberStringUtility.numberPhraseToNumber("eighty").intValue());
        Assert.assertEquals(83, NumberStringUtility.numberPhraseToNumber("eighty three").intValue());
        Assert.assertEquals(90, NumberStringUtility.numberPhraseToNumber("ninety").intValue());
        Assert.assertEquals(94, NumberStringUtility.numberPhraseToNumber("ninety four").intValue());
        Assert.assertEquals(99, NumberStringUtility.numberPhraseToNumber("ninety nine").intValue());
        Assert.assertEquals(99, NumberStringUtility.numberPhraseToNumber("99").intValue());
        
        //three digit cases
        Assert.assertEquals(100, NumberStringUtility.numberPhraseToNumber("one hundred").intValue());
        Assert.assertEquals(154, NumberStringUtility.numberPhraseToNumber("one hundred and fifty four").intValue());
        Assert.assertEquals(200, NumberStringUtility.numberPhraseToNumber("two hundred").intValue());
        Assert.assertEquals(219, NumberStringUtility.numberPhraseToNumber("two hundred and nineteen").intValue());
        Assert.assertEquals(300, NumberStringUtility.numberPhraseToNumber("three hundred").intValue());
        Assert.assertEquals(377, NumberStringUtility.numberPhraseToNumber("three hundred and seventy seven").intValue());
        Assert.assertEquals(400, NumberStringUtility.numberPhraseToNumber("four hundred").intValue());
        Assert.assertEquals(468, NumberStringUtility.numberPhraseToNumber("four hundred and sixty eight").intValue());
        Assert.assertEquals(500, NumberStringUtility.numberPhraseToNumber("five hundred").intValue());
        Assert.assertEquals(594, NumberStringUtility.numberPhraseToNumber("five hundred and ninety four").intValue());
        Assert.assertEquals(600, NumberStringUtility.numberPhraseToNumber("six hundred").intValue());
        Assert.assertEquals(608, NumberStringUtility.numberPhraseToNumber("six hundred and eight").intValue());
        Assert.assertEquals(700, NumberStringUtility.numberPhraseToNumber("seven hundred").intValue());
        Assert.assertEquals(776, NumberStringUtility.numberPhraseToNumber("seven hundred and seventy six").intValue());
        Assert.assertEquals(800, NumberStringUtility.numberPhraseToNumber("eight hundred").intValue());
        Assert.assertEquals(842, NumberStringUtility.numberPhraseToNumber("eight hundred and forty two").intValue());
        Assert.assertEquals(900, NumberStringUtility.numberPhraseToNumber("nine hundred").intValue());
        Assert.assertEquals(999, NumberStringUtility.numberPhraseToNumber("nine hundred and ninety nine").intValue());
        Assert.assertEquals(999, NumberStringUtility.numberPhraseToNumber("999").intValue());
        
        //larger cases
        Assert.assertEquals(1000,
                NumberStringUtility.numberPhraseToNumber("one thousand").intValue());
        Assert.assertEquals(643822,
                NumberStringUtility.numberPhraseToNumber("six hundred and forty three thousand eight hundred and twenty two").intValue());
        Assert.assertEquals(15621907,
                NumberStringUtility.numberPhraseToNumber("fifteen million six hundred and twenty one thousand nine hundred and seven").intValue());
        Assert.assertEquals(946104067001L,
                NumberStringUtility.numberPhraseToNumber("nine hundred and forty six billion one hundred and four million sixty seven thousand and one").longValue());
        Assert.assertEquals(199486273416821L,
                NumberStringUtility.numberPhraseToNumber("one hundred and ninety nine trillion four hundred and eighty six billion two hundred and seventy three million four hundred and sixteen thousand eight hundred and twenty one").longValue());
        Assert.assertEquals(199486000000821L,
                NumberStringUtility.numberPhraseToNumber("one hundred and ninety nine trillion four hundred and eighty six billion eight hundred and twenty one").longValue());
        Assert.assertEquals(1994860000821000054L,
                NumberStringUtility.numberPhraseToNumber("one quintillion nine hundred and ninety four quadrillion eight hundred and sixty trillion eight hundred and twenty one million and fifty four").longValue());
        Assert.assertEquals(199486000000821L,
                NumberStringUtility.numberPhraseToNumber("199 trillion 486 billion 821").longValue());
        
        //extremely large cases
        Assert.assertEquals(3.465874122e54,
                NumberStringUtility.numberPhraseToNumber("three septendecillion four hundred and sixty five sedecillion eight hundred and seventy four quinquadecillion one hundred and twenty two quattuordecillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(9.566115646e55,
                NumberStringUtility.numberPhraseToNumber("ninety five septendecillion six hundred and sixty one sedecillion one hundred and fifty six quinquadecillion four hundred and sixty quattuordecillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(1.874420905e56,
                NumberStringUtility.numberPhraseToNumber("one hundred and eighty seven septendecillion four hundred and forty two sedecillion ninety quinquadecillion five hundred quattuordecillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(2.097751274e57,
                NumberStringUtility.numberPhraseToNumber("two octodecillion ninety seven septendecillion seven hundred and fifty one sedecillion two hundred and seventy four quinquadecillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(7.461123877e108,
                NumberStringUtility.numberPhraseToNumber("seven quinquatrigintillion four hundred and sixty one quattuortrigintillion one hundred and twenty three trestrigintillion eight hundred and seventy seven duotrigintillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(4.000042339e189,
                NumberStringUtility.numberPhraseToNumber("four duosexagintillion forty two sexagintillion three hundred and thirty nine novenquinquagintillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(8.451997463e307,
                NumberStringUtility.numberPhraseToNumber("eighty four uncentillion five hundred and nineteen centillion nine hundred and seventy four novenonagintillion six hundred and thirty octononagintillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(8.451997463e307,
                NumberStringUtility.numberPhraseToNumber("84 uncentillion 519 centillion 974 novenonagintillion 630 octononagintillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(8.4519974635648943123e124,
                NumberStringUtility.numberPhraseToNumber("84519974635648943123 quattuortrigintillion").doubleValue(), TestUtils.DELTA);
        
        //negative cases
        Assert.assertEquals(-0, NumberStringUtility.numberPhraseToNumber("zero").intValue());
        Assert.assertEquals(-2, NumberStringUtility.numberPhraseToNumber("negative two").intValue());
        Assert.assertEquals(-4, NumberStringUtility.numberPhraseToNumber("negative four").intValue());
        Assert.assertEquals(-7, NumberStringUtility.numberPhraseToNumber("negative seven").intValue());
        Assert.assertEquals(-13, NumberStringUtility.numberPhraseToNumber("negative thirteen").intValue());
        Assert.assertEquals(-14, NumberStringUtility.numberPhraseToNumber("negative fourteen").intValue());
        Assert.assertEquals(-19, NumberStringUtility.numberPhraseToNumber("negative nineteen").intValue());
        Assert.assertEquals(-40, NumberStringUtility.numberPhraseToNumber("negative forty").intValue());
        Assert.assertEquals(-46, NumberStringUtility.numberPhraseToNumber("negative forty six").intValue());
        Assert.assertEquals(-52, NumberStringUtility.numberPhraseToNumber("negative fifty two").intValue());
        Assert.assertEquals(-77, NumberStringUtility.numberPhraseToNumber("negative seventy seven").intValue());
        Assert.assertEquals(-80, NumberStringUtility.numberPhraseToNumber("negative eighty").intValue());
        Assert.assertEquals(-99, NumberStringUtility.numberPhraseToNumber("negative ninety nine").intValue());
        Assert.assertEquals(-219, NumberStringUtility.numberPhraseToNumber("negative two hundred and nineteen").intValue());
        Assert.assertEquals(-300, NumberStringUtility.numberPhraseToNumber("negative three hundred").intValue());
        Assert.assertEquals(-594, NumberStringUtility.numberPhraseToNumber("negative five hundred and ninety four").intValue());
        Assert.assertEquals(-776, NumberStringUtility.numberPhraseToNumber("negative seven hundred and seventy six").intValue());
        Assert.assertEquals(-900, NumberStringUtility.numberPhraseToNumber("negative nine hundred").intValue());
        Assert.assertEquals(-999, NumberStringUtility.numberPhraseToNumber("negative nine hundred and ninety nine").intValue());
        Assert.assertEquals(-999, NumberStringUtility.numberPhraseToNumber("negative 999").intValue());
        Assert.assertEquals(-643822,
                NumberStringUtility.numberPhraseToNumber("negative six hundred and forty three thousand eight hundred and twenty two").intValue());
        Assert.assertEquals(-946104067001L,
                NumberStringUtility.numberPhraseToNumber("negative nine hundred and forty six billion one hundred and four million sixty seven thousand and one").longValue());
        Assert.assertEquals(-199486273416821L,
                NumberStringUtility.numberPhraseToNumber("negative one hundred and ninety nine trillion four hundred and eighty six billion two hundred and seventy three million four hundred and sixteen thousand eight hundred and twenty one").longValue());
        Assert.assertEquals(-199486000000821L,
                NumberStringUtility.numberPhraseToNumber("negative one hundred and ninety nine trillion four hundred and eighty six billion eight hundred and twenty one").longValue());
        Assert.assertEquals(-3.465874122e54,
                NumberStringUtility.numberPhraseToNumber("negative three septendecillion four hundred and sixty five sedecillion eight hundred and seventy four quinquadecillion one hundred and twenty two quattuordecillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-7.461123877e108,
                NumberStringUtility.numberPhraseToNumber("negative seven quinquatrigintillion four hundred and sixty one quattuortrigintillion one hundred and twenty three trestrigintillion eight hundred and seventy seven duotrigintillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-8.451997463e307,
                NumberStringUtility.numberPhraseToNumber("negative eighty four uncentillion five hundred and nineteen centillion nine hundred and seventy four novenonagintillion six hundred and thirty octononagintillion").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(-8.451997463e307,
                NumberStringUtility.numberPhraseToNumber("negative 84 uncentillion 519 centillion 974 novenonagintillion 630 octononagintillion").doubleValue(), TestUtils.DELTA);
        
        //decimal cases
        Assert.assertEquals(0.1, NumberStringUtility.numberPhraseToNumber("zero point one").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(2.1542, NumberStringUtility.numberPhraseToNumber("two point one five four two").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(19.008, NumberStringUtility.numberPhraseToNumber("nineteen point zero zero eight").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(40.74, NumberStringUtility.numberPhraseToNumber("forty point seven four").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(300.9141, NumberStringUtility.numberPhraseToNumber("three hundred point nine one four one").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(594.000, NumberStringUtility.numberPhraseToNumber("five hundred and ninety four").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(776.410, NumberStringUtility.numberPhraseToNumber("seven hundred and seventy six point four one").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(643822.9641,
                NumberStringUtility.numberPhraseToNumber("six hundred and forty three thousand eight hundred and twenty two point nine six four one").doubleValue(), TestUtils.DELTA);
        Assert.assertEquals(643822.9641,
                NumberStringUtility.numberPhraseToNumber("643 thousand 822 point 9641").doubleValue(), TestUtils.DELTA);
        
        //big decimal cases
        Assert.assertEquals(new BigDecimal("5967945689794436877198887828689582168366902795322725858829"),
                NumberStringUtility.numberPhraseToNumber("five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine"));
        Assert.assertEquals(new BigDecimal("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                NumberStringUtility.numberPhraseToNumber("zero point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one"));
        Assert.assertEquals(new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                NumberStringUtility.numberPhraseToNumber("five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one"));
        Assert.assertEquals(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829"),
                NumberStringUtility.numberPhraseToNumber("negative five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine"));
        Assert.assertEquals(new BigDecimal("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                NumberStringUtility.numberPhraseToNumber("negative zero point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one"));
        Assert.assertEquals(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001"),
                NumberStringUtility.numberPhraseToNumber("negative five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one"));
        
        //overflow
        Assert.assertEquals(new BigDecimal("1" + StringUtility.fillStringOfLength('0', 3007)),
                NumberStringUtility.numberPhraseToNumber("ten thousand millinillion"));
        Assert.assertEquals(new BigDecimal("14786" + StringUtility.fillStringOfLength('0', 3011) + "8974.0166"),
                NumberStringUtility.numberPhraseToNumber("fourteen quadrillion seven hundred and eighty six trillion millinillion eight thousand nine hundred and seventy four point zero one six six"));
        Assert.assertEquals(new BigDecimal("150000000000048000000001"),
                NumberStringUtility.numberPhraseToNumber("one hundred and fifty thousand million trillion forty eight million thousand and one"));
    }
    
    /**
     * JUnit test of numberPhraseToNumberString.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#numberPhraseToNumberString(String)
     */
    @Test
    public void testPhraseToNumberString() throws Exception {
        //simple cases
        Assert.assertEquals("0", NumberStringUtility.numberPhraseToNumberString("zero"));
        Assert.assertEquals("1", NumberStringUtility.numberPhraseToNumberString("one"));
        Assert.assertEquals("2", NumberStringUtility.numberPhraseToNumberString("two"));
        Assert.assertEquals("3", NumberStringUtility.numberPhraseToNumberString("three"));
        Assert.assertEquals("4", NumberStringUtility.numberPhraseToNumberString("four"));
        Assert.assertEquals("5", NumberStringUtility.numberPhraseToNumberString("five"));
        Assert.assertEquals("6", NumberStringUtility.numberPhraseToNumberString("six"));
        Assert.assertEquals("7", NumberStringUtility.numberPhraseToNumberString("seven"));
        Assert.assertEquals("8", NumberStringUtility.numberPhraseToNumberString("eight"));
        Assert.assertEquals("9", NumberStringUtility.numberPhraseToNumberString("nine"));
        Assert.assertEquals("10", NumberStringUtility.numberPhraseToNumberString("ten"));
        Assert.assertEquals("11", NumberStringUtility.numberPhraseToNumberString("eleven"));
        Assert.assertEquals("12", NumberStringUtility.numberPhraseToNumberString("twelve"));
        Assert.assertEquals("13", NumberStringUtility.numberPhraseToNumberString("thirteen"));
        Assert.assertEquals("14", NumberStringUtility.numberPhraseToNumberString("fourteen"));
        Assert.assertEquals("15", NumberStringUtility.numberPhraseToNumberString("fifteen"));
        Assert.assertEquals("16", NumberStringUtility.numberPhraseToNumberString("sixteen"));
        Assert.assertEquals("17", NumberStringUtility.numberPhraseToNumberString("seventeen"));
        Assert.assertEquals("18", NumberStringUtility.numberPhraseToNumberString("eighteen"));
        Assert.assertEquals("19", NumberStringUtility.numberPhraseToNumberString("nineteen"));
        Assert.assertEquals("19", NumberStringUtility.numberPhraseToNumberString("19"));
        
        //two digit cases
        Assert.assertEquals("20", NumberStringUtility.numberPhraseToNumberString("twenty"));
        Assert.assertEquals("25", NumberStringUtility.numberPhraseToNumberString("twenty five"));
        Assert.assertEquals("30", NumberStringUtility.numberPhraseToNumberString("thirty"));
        Assert.assertEquals("31", NumberStringUtility.numberPhraseToNumberString("thirty one"));
        Assert.assertEquals("40", NumberStringUtility.numberPhraseToNumberString("forty"));
        Assert.assertEquals("46", NumberStringUtility.numberPhraseToNumberString("forty six"));
        Assert.assertEquals("50", NumberStringUtility.numberPhraseToNumberString("fifty"));
        Assert.assertEquals("52", NumberStringUtility.numberPhraseToNumberString("fifty two"));
        Assert.assertEquals("60", NumberStringUtility.numberPhraseToNumberString("sixty"));
        Assert.assertEquals("68", NumberStringUtility.numberPhraseToNumberString("sixty eight"));
        Assert.assertEquals("70", NumberStringUtility.numberPhraseToNumberString("seventy"));
        Assert.assertEquals("77", NumberStringUtility.numberPhraseToNumberString("seventy seven"));
        Assert.assertEquals("80", NumberStringUtility.numberPhraseToNumberString("eighty"));
        Assert.assertEquals("83", NumberStringUtility.numberPhraseToNumberString("eighty three"));
        Assert.assertEquals("90", NumberStringUtility.numberPhraseToNumberString("ninety"));
        Assert.assertEquals("94", NumberStringUtility.numberPhraseToNumberString("ninety four"));
        Assert.assertEquals("99", NumberStringUtility.numberPhraseToNumberString("ninety nine"));
        Assert.assertEquals("99", NumberStringUtility.numberPhraseToNumberString("99"));
        
        //three digit cases
        Assert.assertEquals("100", NumberStringUtility.numberPhraseToNumberString("one hundred"));
        Assert.assertEquals("154", NumberStringUtility.numberPhraseToNumberString("one hundred and fifty four"));
        Assert.assertEquals("200", NumberStringUtility.numberPhraseToNumberString("two hundred"));
        Assert.assertEquals("219", NumberStringUtility.numberPhraseToNumberString("two hundred and nineteen"));
        Assert.assertEquals("300", NumberStringUtility.numberPhraseToNumberString("three hundred"));
        Assert.assertEquals("377", NumberStringUtility.numberPhraseToNumberString("three hundred and seventy seven"));
        Assert.assertEquals("400", NumberStringUtility.numberPhraseToNumberString("four hundred"));
        Assert.assertEquals("468", NumberStringUtility.numberPhraseToNumberString("four hundred and sixty eight"));
        Assert.assertEquals("500", NumberStringUtility.numberPhraseToNumberString("five hundred"));
        Assert.assertEquals("594", NumberStringUtility.numberPhraseToNumberString("five hundred and ninety four"));
        Assert.assertEquals("600", NumberStringUtility.numberPhraseToNumberString("six hundred"));
        Assert.assertEquals("608", NumberStringUtility.numberPhraseToNumberString("six hundred and eight"));
        Assert.assertEquals("700", NumberStringUtility.numberPhraseToNumberString("seven hundred"));
        Assert.assertEquals("776", NumberStringUtility.numberPhraseToNumberString("seven hundred and seventy six"));
        Assert.assertEquals("800", NumberStringUtility.numberPhraseToNumberString("eight hundred"));
        Assert.assertEquals("842", NumberStringUtility.numberPhraseToNumberString("eight hundred and forty two"));
        Assert.assertEquals("900", NumberStringUtility.numberPhraseToNumberString("nine hundred"));
        Assert.assertEquals("999", NumberStringUtility.numberPhraseToNumberString("nine hundred and ninety nine"));
        Assert.assertEquals("999", NumberStringUtility.numberPhraseToNumberString("999"));
        
        //larger cases
        Assert.assertEquals("1000",
                NumberStringUtility.numberPhraseToNumberString("one thousand"));
        Assert.assertEquals("643822",
                NumberStringUtility.numberPhraseToNumberString("six hundred and forty three thousand eight hundred and twenty two"));
        Assert.assertEquals("15621907",
                NumberStringUtility.numberPhraseToNumberString("fifteen million six hundred and twenty one thousand nine hundred and seven"));
        Assert.assertEquals("946104067001",
                NumberStringUtility.numberPhraseToNumberString("nine hundred and forty six billion one hundred and four million sixty seven thousand and one"));
        Assert.assertEquals("199486273416821",
                NumberStringUtility.numberPhraseToNumberString("one hundred and ninety nine trillion four hundred and eighty six billion two hundred and seventy three million four hundred and sixteen thousand eight hundred and twenty one"));
        Assert.assertEquals("199486000000821",
                NumberStringUtility.numberPhraseToNumberString("one hundred and ninety nine trillion four hundred and eighty six billion eight hundred and twenty one"));
        Assert.assertEquals("1994860000821000054",
                NumberStringUtility.numberPhraseToNumberString("one quintillion nine hundred and ninety four quadrillion eight hundred and sixty trillion eight hundred and twenty one million and fifty four"));
        Assert.assertEquals("199486000000821",
                NumberStringUtility.numberPhraseToNumberString("199 trillion 486 billion 821"));
        
        //extremely large cases
        Assert.assertEquals("3465874122000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("three septendecillion four hundred and sixty five sedecillion eight hundred and seventy four quinquadecillion one hundred and twenty two quattuordecillion"));
        Assert.assertEquals("95661156460000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("ninety five septendecillion six hundred and sixty one sedecillion one hundred and fifty six quinquadecillion four hundred and sixty quattuordecillion"));
        Assert.assertEquals("187442090500000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("one hundred and eighty seven septendecillion four hundred and forty two sedecillion ninety quinquadecillion five hundred quattuordecillion"));
        Assert.assertEquals("2097751274000000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("two octodecillion ninety seven septendecillion seven hundred and fifty one sedecillion two hundred and seventy four quinquadecillion"));
        Assert.assertEquals("7461123877000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("seven quinquatrigintillion four hundred and sixty one quattuortrigintillion one hundred and twenty three trestrigintillion eight hundred and seventy seven duotrigintillion"));
        Assert.assertEquals("4000042339000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("four duosexagintillion forty two sexagintillion three hundred and thirty nine novenquinquagintillion"));
        Assert.assertEquals("84519974630000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("eighty four uncentillion five hundred and nineteen centillion nine hundred and seventy four novenonagintillion six hundred and thirty octononagintillion"));
        Assert.assertEquals("84519974630000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("84 uncentillion 519 centillion 974 novenonagintillion 630 octononagintillion"));
        Assert.assertEquals("84519974635648943123000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("84519974635648943123 quattuortrigintillion"));
        
        //negative cases
        Assert.assertEquals("0", NumberStringUtility.numberPhraseToNumberString("zero"));
        Assert.assertEquals("-2", NumberStringUtility.numberPhraseToNumberString("negative two"));
        Assert.assertEquals("-4", NumberStringUtility.numberPhraseToNumberString("negative four"));
        Assert.assertEquals("-7", NumberStringUtility.numberPhraseToNumberString("negative seven"));
        Assert.assertEquals("-13", NumberStringUtility.numberPhraseToNumberString("negative thirteen"));
        Assert.assertEquals("-14", NumberStringUtility.numberPhraseToNumberString("negative fourteen"));
        Assert.assertEquals("-19", NumberStringUtility.numberPhraseToNumberString("negative nineteen"));
        Assert.assertEquals("-40", NumberStringUtility.numberPhraseToNumberString("negative forty"));
        Assert.assertEquals("-46", NumberStringUtility.numberPhraseToNumberString("negative forty six"));
        Assert.assertEquals("-52", NumberStringUtility.numberPhraseToNumberString("negative fifty two"));
        Assert.assertEquals("-77", NumberStringUtility.numberPhraseToNumberString("negative seventy seven"));
        Assert.assertEquals("-80", NumberStringUtility.numberPhraseToNumberString("negative eighty"));
        Assert.assertEquals("-99", NumberStringUtility.numberPhraseToNumberString("negative ninety nine"));
        Assert.assertEquals("-219", NumberStringUtility.numberPhraseToNumberString("negative two hundred and nineteen"));
        Assert.assertEquals("-300", NumberStringUtility.numberPhraseToNumberString("negative three hundred"));
        Assert.assertEquals("-594", NumberStringUtility.numberPhraseToNumberString("negative five hundred and ninety four"));
        Assert.assertEquals("-776", NumberStringUtility.numberPhraseToNumberString("negative seven hundred and seventy six"));
        Assert.assertEquals("-900", NumberStringUtility.numberPhraseToNumberString("negative nine hundred"));
        Assert.assertEquals("-999", NumberStringUtility.numberPhraseToNumberString("negative nine hundred and ninety nine"));
        Assert.assertEquals("-999", NumberStringUtility.numberPhraseToNumberString("negative 999"));
        Assert.assertEquals("-643822",
                NumberStringUtility.numberPhraseToNumberString("negative six hundred and forty three thousand eight hundred and twenty two"));
        Assert.assertEquals("-946104067001",
                NumberStringUtility.numberPhraseToNumberString("negative nine hundred and forty six billion one hundred and four million sixty seven thousand and one"));
        Assert.assertEquals("-199486273416821",
                NumberStringUtility.numberPhraseToNumberString("negative one hundred and ninety nine trillion four hundred and eighty six billion two hundred and seventy three million four hundred and sixteen thousand eight hundred and twenty one"));
        Assert.assertEquals("-199486000000821",
                NumberStringUtility.numberPhraseToNumberString("negative one hundred and ninety nine trillion four hundred and eighty six billion eight hundred and twenty one"));
        Assert.assertEquals("-3465874122000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("negative three septendecillion four hundred and sixty five sedecillion eight hundred and seventy four quinquadecillion one hundred and twenty two quattuordecillion"));
        Assert.assertEquals("-7461123877000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("negative seven quinquatrigintillion four hundred and sixty one quattuortrigintillion one hundred and twenty three trestrigintillion eight hundred and seventy seven duotrigintillion"));
        Assert.assertEquals("-84519974630000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("negative eighty four uncentillion five hundred and nineteen centillion nine hundred and seventy four novenonagintillion six hundred and thirty octononagintillion"));
        Assert.assertEquals("-84519974630000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                NumberStringUtility.numberPhraseToNumberString("negative 84 uncentillion 519 centillion 974 novenonagintillion 630 octononagintillion"));
        
        //decimal cases
        Assert.assertEquals("0.1", NumberStringUtility.numberPhraseToNumberString("zero point one"));
        Assert.assertEquals("2.1542", NumberStringUtility.numberPhraseToNumberString("two point one five four two"));
        Assert.assertEquals("19.008", NumberStringUtility.numberPhraseToNumberString("nineteen point zero zero eight"));
        Assert.assertEquals("40.74", NumberStringUtility.numberPhraseToNumberString("forty point seven four"));
        Assert.assertEquals("300.9141", NumberStringUtility.numberPhraseToNumberString("three hundred point nine one four one"));
        Assert.assertEquals("594", NumberStringUtility.numberPhraseToNumberString("five hundred and ninety four point zero zero zero"));
        Assert.assertEquals("776.41", NumberStringUtility.numberPhraseToNumberString("seven hundred and seventy six point four one zero"));
        Assert.assertEquals("643822.9641",
                NumberStringUtility.numberPhraseToNumberString("six hundred and forty three thousand eight hundred and twenty two point nine six four one"));
        Assert.assertEquals("643822.9641",
                NumberStringUtility.numberPhraseToNumberString("643 thousand 822 point 9641"));
        
        //big decimal cases
        Assert.assertEquals("5967945689794436877198887828689582168366902795322725858829",
                NumberStringUtility.numberPhraseToNumberString("five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine"));
        Assert.assertEquals("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                NumberStringUtility.numberPhraseToNumberString("zero point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one"));
        Assert.assertEquals("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                NumberStringUtility.numberPhraseToNumberString("five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one"));
        Assert.assertEquals("-5967945689794436877198887828689582168366902795322725858829",
                NumberStringUtility.numberPhraseToNumberString("negative five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine"));
        Assert.assertEquals("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                NumberStringUtility.numberPhraseToNumberString("negative zero point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one"));
        Assert.assertEquals("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001",
                NumberStringUtility.numberPhraseToNumberString("negative five octodecillion nine hundred and sixty seven septendecillion nine hundred and forty five sedecillion six hundred and eighty nine quinquadecillion seven hundred and ninety four quattuordecillion four hundred and thirty six tredecillion eight hundred and seventy seven duodecillion one hundred and ninety eight undecillion eight hundred and eighty seven decillion eight hundred and twenty eight nonillion six hundred and eighty nine octillion five hundred and eighty two septillion one hundred and sixty eight sextillion three hundred and sixty six quintillion nine hundred and two quadrillion seven hundred and ninety five trillion three hundred and twenty two billion seven hundred and twenty five million eight hundred and fifty eight thousand eight hundred and twenty nine point five four nine zero five four nine eight four eight nine one five six four six five four zero nine four zero zero zero zero zero zero nine eight nine seven five three five zero nine one nine three nine six eight two four zero eight one seven three zero one five five zero six six eight zero nine four eight eight three five nine five nine six six five five seven zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero zero one"));
        
        //overflow
        Assert.assertEquals("1" + StringUtility.fillStringOfLength('0', 3007),
                NumberStringUtility.numberPhraseToNumberString("ten thousand millinillion"));
        Assert.assertEquals("14786" + StringUtility.fillStringOfLength('0', 3011) + "8974.0166",
                NumberStringUtility.numberPhraseToNumberString("fourteen quadrillion seven hundred and eighty six trillion millinillion eight thousand nine hundred and seventy four point zero one six six"));
        Assert.assertEquals("150000000000048000000001",
                NumberStringUtility.numberPhraseToNumberString("one hundred and fifty thousand million trillion forty eight million thousand and one"));
    }
    
    /**
     * JUnit test of cleanNumberString.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#cleanNumberString(String)
     */
    @Test
    public void testCleanNumberString() throws Exception {
        //standard
        Assert.assertEquals("0", NumberStringUtility.cleanNumberString("00000000"));
        Assert.assertEquals("1", NumberStringUtility.cleanNumberString("000000001"));
        Assert.assertEquals("8400000001", NumberStringUtility.cleanNumberString("00008400000001"));
        
        //negative
        Assert.assertEquals("0", NumberStringUtility.cleanNumberString("-00000000"));
        Assert.assertEquals("-1", NumberStringUtility.cleanNumberString("-000000001"));
        Assert.assertEquals("-8400000001", NumberStringUtility.cleanNumberString("-00008400000001"));
        
        //decimal
        Assert.assertEquals("0", NumberStringUtility.cleanNumberString("00000000.000000"));
        Assert.assertEquals("0.0000001", NumberStringUtility.cleanNumberString("0.0000001"));
        Assert.assertEquals("0.0000001", NumberStringUtility.cleanNumberString("00000000.0000001"));
        Assert.assertEquals("0.0000001", NumberStringUtility.cleanNumberString("00000000.00000010000"));
        Assert.assertEquals("1", NumberStringUtility.cleanNumberString("1.00000000"));
        Assert.assertEquals("1", NumberStringUtility.cleanNumberString("00000001.00000000"));
        
        //decimal, negative
        Assert.assertEquals("0", NumberStringUtility.cleanNumberString("-00000000.000000"));
        Assert.assertEquals("-0.0000001", NumberStringUtility.cleanNumberString("-0.0000001"));
        Assert.assertEquals("-0.0000001", NumberStringUtility.cleanNumberString("-00000000.0000001"));
        Assert.assertEquals("-0.0000001", NumberStringUtility.cleanNumberString("-00000000.00000010000"));
        Assert.assertEquals("-1", NumberStringUtility.cleanNumberString("-1.00000000"));
        Assert.assertEquals("-1", NumberStringUtility.cleanNumberString("-00000001.00000000"));
    }
    
    /**
     * JUnit test of reciprocalAppendix.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#reciprocalAppendix(Number)
     */
    @Test
    public void testReciprocalAppendix() throws Exception {
        //small numbers
        Assert.assertEquals("st", NumberStringUtility.reciprocalAppendix(1));
        Assert.assertEquals("nd", NumberStringUtility.reciprocalAppendix(2));
        Assert.assertEquals("rd", NumberStringUtility.reciprocalAppendix(3));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(4));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(5));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(6));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(7));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(8));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(9));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(10));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(11));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(12));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(13));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(14));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(15));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(16));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(17));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(18));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(19));
        
        //large numbers
        Assert.assertEquals("st", NumberStringUtility.reciprocalAppendix(584321));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(687745106849L));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(7000000000000L));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(7.498513e97));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(9.0048238e266));
        
        //decimals
        Assert.assertEquals("st", NumberStringUtility.reciprocalAppendix(.1));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(.9468));
        Assert.assertEquals("nd", NumberStringUtility.reciprocalAppendix(.00002));
        Assert.assertEquals("rd", NumberStringUtility.reciprocalAppendix(.0000046583));
        
        //numbers and decimals
        Assert.assertEquals("st", NumberStringUtility.reciprocalAppendix(654.1));
        Assert.assertEquals("th", NumberStringUtility.reciprocalAppendix(872100.9468));
        Assert.assertEquals("nd", NumberStringUtility.reciprocalAppendix(915670.00002));
        Assert.assertEquals("rd", NumberStringUtility.reciprocalAppendix(3548.0000046583));
        
        //big decimal cases
        Assert.assertEquals("th",
                NumberStringUtility.reciprocalAppendix(new BigDecimal("5967945689794436877198887828689582168366902795322725858829")));
        Assert.assertEquals("st",
                NumberStringUtility.reciprocalAppendix(new BigDecimal("0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals("st",
                NumberStringUtility.reciprocalAppendix(new BigDecimal("5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals("th",
                NumberStringUtility.reciprocalAppendix(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829")));
        Assert.assertEquals("st",
                NumberStringUtility.reciprocalAppendix(new BigDecimal("-0.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals("st",
                NumberStringUtility.reciprocalAppendix(new BigDecimal("-5967945689794436877198887828689582168366902795322725858829.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
    }
    
    /**
     * JUnit test of powerOfTen.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#powerOfTen(Number)
     */
    @Test
    public void testPowerOfTen() throws Exception {
        //positive powers
        Assert.assertEquals(0, NumberStringUtility.powerOfTen(5));
        Assert.assertEquals(1, NumberStringUtility.powerOfTen(10));
        Assert.assertEquals(1, NumberStringUtility.powerOfTen(59));
        Assert.assertEquals(4, NumberStringUtility.powerOfTen(61324));
        Assert.assertEquals(8, NumberStringUtility.powerOfTen(5.64981345E8));
        Assert.assertEquals(12, NumberStringUtility.powerOfTen(6.1245E12));
        Assert.assertEquals(59, NumberStringUtility.powerOfTen(9.9999999E59));
        Assert.assertEquals(102, NumberStringUtility.powerOfTen(4.568974E102));
        Assert.assertEquals(307, NumberStringUtility.powerOfTen(7.4982114E307));
        
        //negative powers
        Assert.assertEquals(-1, NumberStringUtility.powerOfTen(.1));
        Assert.assertEquals(-1, NumberStringUtility.powerOfTen(.6));
        Assert.assertEquals(-2, NumberStringUtility.powerOfTen(.01));
        Assert.assertEquals(-2, NumberStringUtility.powerOfTen(.03));
        Assert.assertEquals(-3, NumberStringUtility.powerOfTen(0.00481));
        Assert.assertEquals(-6, NumberStringUtility.powerOfTen(4.5684E-6));
        Assert.assertEquals(-84, NumberStringUtility.powerOfTen(8.4512265E-84));
        Assert.assertEquals(-109, NumberStringUtility.powerOfTen(1.0E-109));
        Assert.assertEquals(-324, NumberStringUtility.powerOfTen(6.5644114E-324));
        
        //big decimal cases
        Assert.assertEquals(757,
                NumberStringUtility.powerOfTen(new BigDecimal("59679456897944368771988878286895821683669027953227258588290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")));
        Assert.assertEquals(-701,
                NumberStringUtility.powerOfTen(new BigDecimal("0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000054905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals(757,
                NumberStringUtility.powerOfTen(new BigDecimal("59679456897944368771988878286895821683669027953227258588290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals(757,
                NumberStringUtility.powerOfTen(new BigDecimal("-59679456897944368771988878286895821683669027953227258588290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")));
        Assert.assertEquals(-701,
                NumberStringUtility.powerOfTen(new BigDecimal("-0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000054905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals(757,
                NumberStringUtility.powerOfTen(new BigDecimal("-59679456897944368771988878286895821683669027953227258588290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
    }
    
    /**
     * JUnit test of powerOfTenTruncated.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#powerOfTenTruncated(Number)
     */
    @Test
    public void testPowerOfTenTruncated() throws Exception {
        //positive powers
        Assert.assertEquals(0, NumberStringUtility.powerOfTenTruncated(5));
        Assert.assertEquals(0, NumberStringUtility.powerOfTenTruncated(10));
        Assert.assertEquals(0, NumberStringUtility.powerOfTenTruncated(59));
        Assert.assertEquals(3, NumberStringUtility.powerOfTenTruncated(61324));
        Assert.assertEquals(6, NumberStringUtility.powerOfTenTruncated(5.64981345E8));
        Assert.assertEquals(12, NumberStringUtility.powerOfTenTruncated(6.1245E12));
        Assert.assertEquals(57, NumberStringUtility.powerOfTenTruncated(9.9999999E59));
        Assert.assertEquals(102, NumberStringUtility.powerOfTenTruncated(4.568974E102));
        Assert.assertEquals(306, NumberStringUtility.powerOfTenTruncated(7.4982114E307));
        
        //negative powers
        Assert.assertEquals(0, NumberStringUtility.powerOfTenTruncated(.1));
        Assert.assertEquals(0, NumberStringUtility.powerOfTenTruncated(.6));
        Assert.assertEquals(0, NumberStringUtility.powerOfTenTruncated(.01));
        Assert.assertEquals(0, NumberStringUtility.powerOfTenTruncated(.03));
        Assert.assertEquals(-3, NumberStringUtility.powerOfTenTruncated(0.00481));
        Assert.assertEquals(-6, NumberStringUtility.powerOfTenTruncated(4.5684E-6));
        Assert.assertEquals(-84, NumberStringUtility.powerOfTenTruncated(8.4512265E-84));
        Assert.assertEquals(-108, NumberStringUtility.powerOfTenTruncated(1.0E-109));
        Assert.assertEquals(-324, NumberStringUtility.powerOfTenTruncated(6.5644114E-324));
        
        //big decimal cases
        Assert.assertEquals(756,
                NumberStringUtility.powerOfTenTruncated(new BigDecimal("59679456897944368771988878286895821683669027953227258588290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")));
        Assert.assertEquals(-699,
                NumberStringUtility.powerOfTenTruncated(new BigDecimal("0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000054905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals(756,
                NumberStringUtility.powerOfTenTruncated(new BigDecimal("59679456897944368771988878286895821683669027953227258588290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals(756,
                NumberStringUtility.powerOfTenTruncated(new BigDecimal("-59679456897944368771988878286895821683669027953227258588290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")));
        Assert.assertEquals(-699,
                NumberStringUtility.powerOfTenTruncated(new BigDecimal("-0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000054905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
        Assert.assertEquals(756,
                NumberStringUtility.powerOfTenTruncated(new BigDecimal("-59679456897944368771988878286895821683669027953227258588290000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.54905498489156465409400000098975350919396824081730155066809488359596655700000000000000000000001")));
    }
    
    /**
     * JUnit test of powerOfTenName.
     *
     * @throws Exception When there is an exception.
     * @see NumberStringUtility#powerOfTenName(int)
     */
    @Test
    public void testPowerOfTenName() throws Exception {
        //edge cases
        Assert.assertEquals("", NumberStringUtility.powerOfTenName(0));
        Assert.assertEquals("", NumberStringUtility.powerOfTenName(3004));
        Assert.assertEquals("", NumberStringUtility.powerOfTenName(-3004));
        Assert.assertEquals("", NumberStringUtility.powerOfTenName(1));
        Assert.assertEquals("", NumberStringUtility.powerOfTenName(4));
        Assert.assertEquals("", NumberStringUtility.powerOfTenName(5));
        Assert.assertEquals("", NumberStringUtility.powerOfTenName(10));
        Assert.assertEquals("", NumberStringUtility.powerOfTenName(1000));
        
        //small cases
        Assert.assertEquals("hundred", NumberStringUtility.powerOfTenName(2));
        Assert.assertEquals("thousand", NumberStringUtility.powerOfTenName(3));
        Assert.assertEquals("million", NumberStringUtility.powerOfTenName(6));
        Assert.assertEquals("billion", NumberStringUtility.powerOfTenName(9));
        Assert.assertEquals("trillion", NumberStringUtility.powerOfTenName(12));
        Assert.assertEquals("quadrillion", NumberStringUtility.powerOfTenName(15));
        Assert.assertEquals("quintillion", NumberStringUtility.powerOfTenName(18));
        Assert.assertEquals("sextillion", NumberStringUtility.powerOfTenName(21));
        Assert.assertEquals("septillion", NumberStringUtility.powerOfTenName(24));
        Assert.assertEquals("octillion", NumberStringUtility.powerOfTenName(27));
        Assert.assertEquals("nonillion", NumberStringUtility.powerOfTenName(30));
        Assert.assertEquals("millinillion", NumberStringUtility.powerOfTenName(3003));
        
        //larger cases
        Assert.assertEquals("decillion", NumberStringUtility.powerOfTenName(33));
        Assert.assertEquals("undecillion", NumberStringUtility.powerOfTenName(36));
        Assert.assertEquals("quattuordecillion", NumberStringUtility.powerOfTenName(45));
        Assert.assertEquals("vigintillion", NumberStringUtility.powerOfTenName(63));
        Assert.assertEquals("quinquavigintillion", NumberStringUtility.powerOfTenName(78));
        Assert.assertEquals("trestrigintillion", NumberStringUtility.powerOfTenName(102));
        Assert.assertEquals("noventrigintillion", NumberStringUtility.powerOfTenName(120));
        Assert.assertEquals("sesquadragintillion", NumberStringUtility.powerOfTenName(141));
        Assert.assertEquals("septuagintillion", NumberStringUtility.powerOfTenName(213));
        Assert.assertEquals("centillion", NumberStringUtility.powerOfTenName(303));
        Assert.assertEquals("undecicentillion", NumberStringUtility.powerOfTenName(336));
        Assert.assertEquals("quadragintacentillion", NumberStringUtility.powerOfTenName(423));
        Assert.assertEquals("ducentillion", NumberStringUtility.powerOfTenName(603));
        Assert.assertEquals("sescentillion", NumberStringUtility.powerOfTenName(1803));
        Assert.assertEquals("nongentillion", NumberStringUtility.powerOfTenName(2703));
    }
    
}
