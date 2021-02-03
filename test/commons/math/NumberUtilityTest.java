/*
 * File:    NumberUtilityTest.java
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
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of NumberUtility.
 *
 * @see NumberUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({NumberUtility.class})
public class NumberUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(NumberUtilityTest.class);
    
    
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
     * @see NumberUtility#HEX_NUMBER_PATTERN
     */
    @Test
    public void testConstants() throws Exception {
        //patterns
        Assert.assertEquals("-?[0-9A-Fa-f]*(?:\\.[0-9A-Fa-f]+)?", NumberUtility.HEX_NUMBER_PATTERN.pattern());
    }
    
    /**
     * JUnit test of isEven.
     *
     * @throws Exception When there is an exception.
     * @see NumberUtility#isEven(int)
     * @see NumberUtility#isEven(long)
     */
    @Test
    public void testIsEven() throws Exception {
        //even
        Assert.assertTrue(NumberUtility.isEven(12));
        Assert.assertTrue(NumberUtility.isEven(2880642));
        Assert.assertTrue(NumberUtility.isEven(0));
        Assert.assertTrue(NumberUtility.isEven(2));
        Assert.assertTrue(NumberUtility.isEven(-2));
        Assert.assertTrue(NumberUtility.isEven(-456450));
        Assert.assertTrue(NumberUtility.isEven(564842130610260L));
        Assert.assertTrue(NumberUtility.isEven(789874653120632158L));
        Assert.assertTrue(NumberUtility.isEven(99000000000002L));
        Assert.assertTrue(NumberUtility.isEven(-99000000000002L));
        Assert.assertTrue(NumberUtility.isEven(-8984567098465654L));
        
        //odd
        Assert.assertFalse(NumberUtility.isEven(19));
        Assert.assertFalse(NumberUtility.isEven(2880641));
        Assert.assertFalse(NumberUtility.isEven(3));
        Assert.assertFalse(NumberUtility.isEven(7));
        Assert.assertFalse(NumberUtility.isEven(-1));
        Assert.assertFalse(NumberUtility.isEven(-456459));
        Assert.assertFalse(NumberUtility.isEven(564842130610265L));
        Assert.assertFalse(NumberUtility.isEven(789874653120632157L));
        Assert.assertFalse(NumberUtility.isEven(99000000000001L));
        Assert.assertFalse(NumberUtility.isEven(-99000000000001L));
        Assert.assertFalse(NumberUtility.isEven(-8984567098465659L));
    }
    
    /**
     * JUnit test of isOdd.
     *
     * @throws Exception When there is an exception.
     * @see NumberUtility#isOdd(int)
     * @see NumberUtility#isOdd(long)
     */
    @Test
    public void testIdOdd() throws Exception {
        //even
        Assert.assertFalse(NumberUtility.isOdd(12));
        Assert.assertFalse(NumberUtility.isOdd(2880642));
        Assert.assertFalse(NumberUtility.isOdd(0));
        Assert.assertFalse(NumberUtility.isOdd(2));
        Assert.assertFalse(NumberUtility.isOdd(-2));
        Assert.assertFalse(NumberUtility.isOdd(-456450));
        Assert.assertFalse(NumberUtility.isOdd(564842130610260L));
        Assert.assertFalse(NumberUtility.isOdd(789874653120632158L));
        Assert.assertFalse(NumberUtility.isOdd(99000000000002L));
        Assert.assertFalse(NumberUtility.isOdd(-99000000000002L));
        Assert.assertFalse(NumberUtility.isOdd(-8984567098465654L));
        
        //odd
        Assert.assertTrue(NumberUtility.isOdd(19));
        Assert.assertTrue(NumberUtility.isOdd(2880641));
        Assert.assertTrue(NumberUtility.isOdd(3));
        Assert.assertTrue(NumberUtility.isOdd(7));
        Assert.assertTrue(NumberUtility.isOdd(-1));
        Assert.assertTrue(NumberUtility.isOdd(-456459));
        Assert.assertTrue(NumberUtility.isOdd(564842130610265L));
        Assert.assertTrue(NumberUtility.isOdd(789874653120632157L));
        Assert.assertTrue(NumberUtility.isOdd(99000000000001L));
        Assert.assertTrue(NumberUtility.isOdd(-99000000000001L));
        Assert.assertTrue(NumberUtility.isOdd(-8984567098465659L));
    }
    
    /**
     * JUnit test of isNumberChar.
     *
     * @throws Exception When there is an exception.
     * @see NumberUtility#isNumberChar(char)
     */
    @Test
    public void testIsNumberChar() throws Exception {
        //valid
        Assert.assertTrue(NumberUtility.isNumberChar('0'));
        Assert.assertTrue(NumberUtility.isNumberChar('1'));
        Assert.assertTrue(NumberUtility.isNumberChar('2'));
        Assert.assertTrue(NumberUtility.isNumberChar('3'));
        Assert.assertTrue(NumberUtility.isNumberChar('4'));
        Assert.assertTrue(NumberUtility.isNumberChar('5'));
        Assert.assertTrue(NumberUtility.isNumberChar('6'));
        Assert.assertTrue(NumberUtility.isNumberChar('7'));
        Assert.assertTrue(NumberUtility.isNumberChar('8'));
        Assert.assertTrue(NumberUtility.isNumberChar('9'));
        Assert.assertTrue(NumberUtility.isNumberChar('.'));
        Assert.assertTrue(NumberUtility.isNumberChar('-'));
        
        //invalid
        Assert.assertFalse(NumberUtility.isNumberChar('a'));
        Assert.assertFalse(NumberUtility.isNumberChar('t'));
        Assert.assertFalse(NumberUtility.isNumberChar('z'));
        Assert.assertFalse(NumberUtility.isNumberChar('A'));
        Assert.assertFalse(NumberUtility.isNumberChar('T'));
        Assert.assertFalse(NumberUtility.isNumberChar('Z'));
        Assert.assertFalse(NumberUtility.isNumberChar('$'));
        Assert.assertFalse(NumberUtility.isNumberChar('~'));
        Assert.assertFalse(NumberUtility.isNumberChar('+'));
        Assert.assertFalse(NumberUtility.isNumberChar('='));
    }
    
    /**
     * JUnit test of extractNumberChars.
     *
     * @throws Exception When there is an exception.
     * @see NumberUtility#extractNumberChars(String)
     */
    @Test
    public void testExtractNumberChars() throws Exception {
        //valid
        Assert.assertEquals("5", NumberUtility.extractNumberChars("A number like 5"));
        Assert.assertEquals("6548744", NumberUtility.extractNumberChars("6548744 is a number"));
        Assert.assertEquals("-46022.1546", NumberUtility.extractNumberChars("Here, -46022.1546 is the number"));
        
        //valid but unusable
        Assert.assertEquals("52", NumberUtility.extractNumberChars("A number like 5 is a good number 2"));
        Assert.assertEquals("6548744", NumberUtility.extractNumberChars("654 is a 87 number, 44"));
        Assert.assertEquals("-46022.1546-...", NumberUtility.extractNumberChars("-Here, 46 is the 022.1546 - number..."));
    }
    
    /**
     * JUnit test of hexToDecimal.
     *
     * @throws Exception When there is an exception.
     * @see NumberUtility#hexToDecimal(String, int)
     * @see NumberUtility#hexToDecimal(String)
     */
    @Test
    public void testHexToDecimal() throws Exception {
        //integer
        Assert.assertEquals("0", NumberUtility.hexToDecimal("0"));
        Assert.assertEquals("1", NumberUtility.hexToDecimal("1"));
        Assert.assertEquals("9", NumberUtility.hexToDecimal("9"));
        Assert.assertEquals("15", NumberUtility.hexToDecimal("F"));
        Assert.assertEquals("242", NumberUtility.hexToDecimal("F2"));
        Assert.assertEquals("33190", NumberUtility.hexToDecimal("81A6"));
        Assert.assertEquals("256", NumberUtility.hexToDecimal("100"));
        Assert.assertEquals("1048576", NumberUtility.hexToDecimal("100000"));
        Assert.assertEquals("5191557193152165532727847676938654", NumberUtility.hexToDecimal("FFF6AA0322BC458D5D11A632099E"));
        Assert.assertEquals("282886881332428154466487121231991859970997056152877088222", NumberUtility.hexToDecimal("B897A12C89896321C454A7DD9E150233CBB87A9F0233DDE"));
        Assert.assertEquals("-256", NumberUtility.hexToDecimal("-100"));
        Assert.assertEquals("-144147542", NumberUtility.hexToDecimal("-8978456"));
        Assert.assertEquals("-332651442596728389665499138728075237402", NumberUtility.hexToDecimal("-FA42566214321CC67445D58EE874981A"));
        Assert.assertEquals("33190", NumberUtility.hexToDecimal("81a6"));
        
        //decimal
        Assert.assertEquals("0.10", NumberUtility.hexToDecimal("0.1a"));
        Assert.assertEquals("0.5", NumberUtility.hexToDecimal("0.8"));
        Assert.assertEquals("0.0711", NumberUtility.hexToDecimal("0.1234"));
        Assert.assertEquals("0.528966901", NumberUtility.hexToDecimal("0.876A5FF4A"));
        Assert.assertEquals("-0.528966901", NumberUtility.hexToDecimal("-0.876A5FF4A"));
        Assert.assertEquals("-0.00000000", NumberUtility.hexToDecimal("-0.00000001"));
        Assert.assertEquals("-0.62067648792835838863907521427468", NumberUtility.hexToDecimal("-0.9EE4A7810C666FF7453D06A44621030E"));
        Assert.assertEquals("0.528966901", NumberUtility.hexToDecimal("0.876a5ff4a"));
        Assert.assertEquals("0.528966901", NumberUtility.hexToDecimal(".876a5ff4a"));
        Assert.assertEquals("-0.528966901", NumberUtility.hexToDecimal("-.876a5ff4a"));
        
        //combined
        Assert.assertEquals("15.33693", NumberUtility.hexToDecimal("F.56412"));
        Assert.assertEquals("17220744.33934412", NumberUtility.hexToDecimal("106C488.56DF41A2"));
        Assert.assertEquals("282886881332428154466487121231991859970997056152877088222.62067648792835838863907521427468", NumberUtility.hexToDecimal("B897A12C89896321C454A7DD9E150233CBB87A9F0233DDE.9EE4A7810C666FF7453D06A44621030E"));
        Assert.assertEquals("-17220744.33934412", NumberUtility.hexToDecimal("-106C488.56DF41A2"));
        Assert.assertEquals("-282886881332428154466487121231991859970997056152877088222.62067648792835838863907521427468", NumberUtility.hexToDecimal("-B897A12C89896321C454A7DD9E150233CBB87A9F0233DDE.9EE4A7810C666FF7453D06A44621030E"));
        Assert.assertEquals("-17220744.33934412", NumberUtility.hexToDecimal("-106c488.56df41a2"));
        Assert.assertEquals("3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808", NumberUtility.hexToDecimal("3.243F6A8885A308D313198A2E03707344A4093822299F31D0082EFA98EC4E6C89452821E638D01377BE5466CF34E90C6CC0AC29B7C97"));
        
        //accuracy
        Assert.assertEquals("-0.00000", NumberUtility.hexToDecimal("-0.00000001", 5));
        Assert.assertEquals("-0.000000000232830", NumberUtility.hexToDecimal("-0.00000001", 15));
        Assert.assertEquals("-0", NumberUtility.hexToDecimal("-0.00000001", 0));
        Assert.assertEquals("282886881332428154466487121231991859970997056152877088222.5", NumberUtility.hexToDecimal("B897A12C89896321C454A7DD9E150233CBB87A9F0233DDE.9EE4A7810C666FF7453D06A44621030E", 1));
        Assert.assertEquals("3.14", NumberUtility.hexToDecimal("3.243F6A8885A308D313198A2E03707344A4093822299F31D0082EFA98EC4E6C89452821E638D01377BE5466CF34E90C6CC0AC29B7C97", 2));
        Assert.assertEquals("3.1415926535", NumberUtility.hexToDecimal("3.243F6A8885A308D313198A2E03707344A4093822299F31D0082EFA98EC4E6C89452821E638D01377BE5466CF34E90C6CC0AC29B7C97", 10));
        Assert.assertEquals("3.1415926535897932384626433", NumberUtility.hexToDecimal("3.243F6A8885A308D313198A2E03707344A4093822299F31D0082EFA98EC4E6C89452821E638D01377BE5466CF34E90C6CC0AC29B7C97", 25));
        
        //invalid
        Assert.assertEquals("", NumberUtility.hexToDecimal("0.00000.001"));
        Assert.assertEquals("", NumberUtility.hexToDecimal("0.00000-001"));
        Assert.assertEquals("", NumberUtility.hexToDecimal("156-081.00000001"));
        Assert.assertEquals("", NumberUtility.hexToDecimal("hello"));
        Assert.assertEquals("", NumberUtility.hexToDecimal("9g"));
        Assert.assertEquals("", NumberUtility.hexToDecimal("9G"));
        Assert.assertEquals("", NumberUtility.hexToDecimal("546.FDA", -1));
        Assert.assertEquals("", NumberUtility.hexToDecimal("546.FDA", -999));
    }
    
}
