/*
 * File:    StringUtilityTest.java
 * Package: string
 * Author:  Zachary Gill
 */

package commons.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commons.console.Console;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of StringUtility.
 *
 * @see StringUtility
 */
@SuppressWarnings("SpellCheckingInspection")
public class StringUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(StringUtilityTest.class);
    
    
    //Fields
    
    /**
     * A string of vowel characters.
     */
    private String vowels = "AEIOUaeiou";
    
    /**
     * A string of consonant characters.
     */
    private String consonants = "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz";
    
    /**
     * A string of number characters.
     */
    private String numbers = "012345679";
    
    /**
     * A string of symbol characters.
     */
    private String symbols = "`~!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
    
    /**
     * A string of whitespace characters.
     */
    private String whiteSpace = " \t\r\n\0";
    
    
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
     * JUnit test of tokenize.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#tokenize(String, String, boolean)
     * @see StringUtility#tokenize(String, String)
     * @see StringUtility#tokenize(String)
     */
    @Test
    public void testTokenize() throws Exception {
        List<String> tokens;
        
        //normal cases
        
        tokens = StringUtility.tokenize("This is a string");
        Assert.assertEquals(4, tokens.size());
        Assert.assertEquals("This", tokens.get(0));
        Assert.assertEquals("is", tokens.get(1));
        Assert.assertEquals("a", tokens.get(2));
        Assert.assertEquals("string", tokens.get(3));
        
        tokens = StringUtility.tokenize("Thisisastring");
        Assert.assertEquals(1, tokens.size());
        Assert.assertEquals("Thisisastring", tokens.get(0));
        
        tokens = StringUtility.tokenize("");
        Assert.assertEquals(1, tokens.size());
        Assert.assertEquals("", tokens.get(0));
        
        //delimiter
        
        tokens = StringUtility.tokenize("This is a string,This is another string,And another", ",");
        Assert.assertEquals(3, tokens.size());
        Assert.assertEquals("This is a string", tokens.get(0));
        Assert.assertEquals("This is another string", tokens.get(1));
        Assert.assertEquals("And another", tokens.get(2));
        
        tokens = StringUtility.tokenize("This is a string|This is another string|And another", "\\|");
        Assert.assertEquals(3, tokens.size());
        Assert.assertEquals("This is a string", tokens.get(0));
        Assert.assertEquals("This is another string", tokens.get(1));
        Assert.assertEquals("And another", tokens.get(2));
        
        tokens = StringUtility.tokenize("This is a string,This is another string,And another", "is");
        Assert.assertEquals(5, tokens.size());
        Assert.assertEquals("Th", tokens.get(0));
        Assert.assertEquals(" ", tokens.get(1));
        Assert.assertEquals(" a string,Th", tokens.get(2));
        Assert.assertEquals(" ", tokens.get(3));
        Assert.assertEquals(" another string,And another", tokens.get(4));
        
        tokens = StringUtility.tokenize("string", "");
        Assert.assertEquals(6, tokens.size());
        Assert.assertEquals("s", tokens.get(0));
        Assert.assertEquals("t", tokens.get(1));
        Assert.assertEquals("r", tokens.get(2));
        Assert.assertEquals("i", tokens.get(3));
        Assert.assertEquals("n", tokens.get(4));
        Assert.assertEquals("g", tokens.get(5));
        
        //hard
        
        tokens = StringUtility.tokenize("|This is a string|This is another string|And another|", "\\|", false);
        Assert.assertEquals(4, tokens.size());
        Assert.assertEquals("", tokens.get(0));
        Assert.assertEquals("This is a string", tokens.get(1));
        Assert.assertEquals("This is another string", tokens.get(2));
        Assert.assertEquals("And another", tokens.get(3));
        
        tokens = StringUtility.tokenize("|This is a string|This is another string|And another|", "\\|", true);
        Assert.assertEquals(5, tokens.size());
        Assert.assertEquals("", tokens.get(0));
        Assert.assertEquals("This is a string", tokens.get(1));
        Assert.assertEquals("This is another string", tokens.get(2));
        Assert.assertEquals("And another", tokens.get(3));
        Assert.assertEquals("", tokens.get(4));
    }
    
    /**
     * JUnit test of detokenize.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#detokenize(List, String)
     * @see StringUtility#detokenize(List)
     */
    @Test
    public void testDetokenize() throws Exception {
        //normal cases
        Assert.assertEquals("This is a string", StringUtility.detokenize(StringUtility.tokenize("This is a string")));
        Assert.assertEquals("Thisisastring", StringUtility.detokenize(StringUtility.tokenize("Thisisastring")));
        Assert.assertEquals("", StringUtility.detokenize(StringUtility.tokenize("")));
        
        //delimiter
        Assert.assertEquals("This is a string,This is another string,And another", StringUtility.detokenize(StringUtility.tokenize("This is a string,This is another string,And another", ","), ","));
        Assert.assertEquals("This is a string,This is another string,And another", StringUtility.detokenize(StringUtility.tokenize("This is a string,This is another string,And another", "is"), "is"));
        Assert.assertEquals("string", StringUtility.detokenize(StringUtility.tokenize("string", ""), ""));
    }
    
    /**
     * JUnit test of splitLines.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#splitLines(String)
     */
    @Test
    public void testSplitLines() throws Exception {
        List<String> lines;
        
        //normal cases
        
        lines = StringUtility.splitLines("One line");
        Assert.assertEquals(1, lines.size());
        Assert.assertEquals("One line", lines.get(0));
        
        lines = StringUtility.splitLines("One line\nTwo line\nThree line");
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals("One line", lines.get(0));
        Assert.assertEquals("Two line", lines.get(1));
        Assert.assertEquals("Three line", lines.get(2));
        
        lines = StringUtility.splitLines("One line\r\nTwo line\r\nThree line");
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals("One line", lines.get(0));
        Assert.assertEquals("Two line", lines.get(1));
        Assert.assertEquals("Three line", lines.get(2));
        
        //other cases
        
        lines = StringUtility.splitLines("One line\r\n\r\n\r\nTwo line\r\n\r\n\nThree line");
        Assert.assertEquals(7, lines.size());
        Assert.assertEquals("One line", lines.get(0));
        Assert.assertEquals("", lines.get(1));
        Assert.assertEquals("", lines.get(2));
        Assert.assertEquals("Two line", lines.get(3));
        Assert.assertEquals("", lines.get(4));
        Assert.assertEquals("", lines.get(5));
        Assert.assertEquals("Three line", lines.get(6));
        
        lines = StringUtility.splitLines("One line\rTwo line\rThree line");
        Assert.assertEquals(1, lines.size());
        Assert.assertEquals("One line\rTwo line\rThree line", lines.get(0));
        
        //empty cases
        
        lines = StringUtility.splitLines("");
        Assert.assertEquals(1, lines.size());
        Assert.assertEquals("", lines.get(0));
        
        lines = StringUtility.splitLines("\n\n\n");
        Assert.assertEquals(4, lines.size());
        Assert.assertEquals("", lines.get(0));
        Assert.assertEquals("", lines.get(1));
        Assert.assertEquals("", lines.get(2));
        Assert.assertEquals("", lines.get(3));
        
        lines = StringUtility.splitLines("\r\n\r\n\r\n");
        Assert.assertEquals(4, lines.size());
        Assert.assertEquals("", lines.get(0));
        Assert.assertEquals("", lines.get(1));
        Assert.assertEquals("", lines.get(2));
        Assert.assertEquals("", lines.get(3));
        
        lines = StringUtility.splitLines("\r\n\n");
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals("", lines.get(0));
        Assert.assertEquals("", lines.get(1));
        Assert.assertEquals("", lines.get(2));
    }
    
    /**
     * JUnit test of unsplitLines.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#unsplitLines(List)
     */
    @Test
    public void testUnsplitLines() throws Exception {
        List<String> lines = new ArrayList<>();
        String unsplit;
        
        //normal cases
        
        lines.add("One line");
        unsplit = StringUtility.unsplitLines(lines);
        Assert.assertEquals("One line", unsplit);
        
        lines.clear();
        lines.add("One line");
        lines.add("Two line");
        lines.add("Three line");
        unsplit = StringUtility.unsplitLines(lines);
        Assert.assertEquals("One line" + System.lineSeparator() + "Two line" + System.lineSeparator() + "Three line", unsplit);
        
        //other cases
        
        lines.clear();
        lines.add("One line");
        lines.add("");
        lines.add("");
        lines.add("Two line");
        lines.add("");
        lines.add("");
        lines.add("Three line");
        unsplit = StringUtility.unsplitLines(lines);
        Assert.assertEquals("One line" + System.lineSeparator() + System.lineSeparator() + System.lineSeparator() + "Two line" + System.lineSeparator() + System.lineSeparator() + System.lineSeparator() + "Three line", unsplit);
        
        //empty cases
        
        lines.clear();
        lines.add("");
        unsplit = StringUtility.unsplitLines(lines);
        Assert.assertEquals("", unsplit);
        
        lines.clear();
        lines.add("");
        lines.add("");
        lines.add("");
        lines.add("");
        unsplit = StringUtility.unsplitLines(lines);
        Assert.assertEquals(System.lineSeparator() + System.lineSeparator() + System.lineSeparator(), unsplit);
    }
    
    /**
     * JUnit test of tokenizeArgs.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#tokenizeArgs(String)
     */
    @Test
    public void testTokenizeArgs() throws Exception {
        List<String> tokens;
        
        //tokenize cases
        
        tokens = StringUtility.tokenizeArgs("This is a string");
        Assert.assertEquals(4, tokens.size());
        Assert.assertEquals("This", tokens.get(0));
        Assert.assertEquals("is", tokens.get(1));
        Assert.assertEquals("a", tokens.get(2));
        Assert.assertEquals("string", tokens.get(3));
        
        tokens = StringUtility.tokenizeArgs("Thisisastring");
        Assert.assertEquals(1, tokens.size());
        Assert.assertEquals("Thisisastring", tokens.get(0));
        
        tokens = StringUtility.tokenizeArgs("");
        Assert.assertEquals(0, tokens.size());
        
        //args cases
        
        tokens = StringUtility.tokenizeArgs("3 -q --simple");
        Assert.assertEquals(3, tokens.size());
        Assert.assertEquals("3", tokens.get(0));
        Assert.assertEquals("-q", tokens.get(1));
        Assert.assertEquals("--simple", tokens.get(2));
        
        tokens = StringUtility.tokenizeArgs("3 -q --simple \"C:\\blah blah\\blah.txt\" -i 5.4874 --read_write");
        Assert.assertEquals(7, tokens.size());
        Assert.assertEquals("3", tokens.get(0));
        Assert.assertEquals("-q", tokens.get(1));
        Assert.assertEquals("--simple", tokens.get(2));
        Assert.assertEquals("C:\\blah blah\\blah.txt", tokens.get(3));
        Assert.assertEquals("-i", tokens.get(4));
        Assert.assertEquals("5.4874", tokens.get(5));
        Assert.assertEquals("--read_write", tokens.get(6));
        
        tokens = StringUtility.tokenizeArgs("\"C:\\blah blah\\blah.txt\"");
        Assert.assertEquals(1, tokens.size());
        Assert.assertEquals("C:\\blah blah\\blah.txt", tokens.get(0));
    }
    
    /**
     * JUnit test of isAlphanumeric.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#isAlphanumeric(char)
     * @see StringUtility#isAlphanumeric(String)
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testIsAlphanumeric() throws Exception {
        //vowels
        for (char c : vowels.toCharArray()) {
            Assert.assertTrue(StringUtility.isAlphanumeric(c));
        }
        
        //consonants
        for (char c : consonants.toCharArray()) {
            Assert.assertTrue(StringUtility.isAlphanumeric(c));
        }
        
        //numbers
        for (char c : numbers.toCharArray()) {
            Assert.assertTrue(StringUtility.isAlphanumeric(c));
        }
        
        //symbols
        for (char c : symbols.toCharArray()) {
            Assert.assertFalse(StringUtility.isAlphanumeric(c));
        }
        
        //white space
        for (char c : whiteSpace.toCharArray()) {
            Assert.assertFalse(StringUtility.isAlphanumeric(c));
        }
        
        //valid strings
        Assert.assertTrue(StringUtility.isAlphanumeric("agaerg"));
        Assert.assertTrue(StringUtility.isAlphanumeric("FADHAED"));
        Assert.assertTrue(StringUtility.isAlphanumeric("84945"));
        Assert.assertTrue(StringUtility.isAlphanumeric("ASF456"));
        Assert.assertTrue(StringUtility.isAlphanumeric("afeAGA3t3tEA46"));
        Assert.assertTrue(StringUtility.isAlphanumeric(""));
        
        //invalid strings
        Assert.assertFalse(StringUtility.isAlphanumeric("aga erg"));
        Assert.assertFalse(StringUtility.isAlphanumeric("FA#DHAE$D"));
        Assert.assertFalse(StringUtility.isAlphanumeric("   84945"));
        Assert.assertFalse(StringUtility.isAlphanumeric("84945 "));
        Assert.assertFalse(StringUtility.isAlphanumeric("ASF\n456"));
        Assert.assertFalse(StringUtility.isAlphanumeric("a!feAGA#3t3tE$A46\n"));
    }
    
    /**
     * JUnit test of isAlphabetic.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#isAlphabetic(char)
     * @see StringUtility#isAlphabetic(String)
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testIsAlphabetic() throws Exception {
        //vowels
        for (char c : vowels.toCharArray()) {
            Assert.assertTrue(StringUtility.isAlphabetic(c));
        }
        
        //consonants
        for (char c : consonants.toCharArray()) {
            Assert.assertTrue(StringUtility.isAlphabetic(c));
        }
        
        //numbers
        for (char c : numbers.toCharArray()) {
            Assert.assertFalse(StringUtility.isAlphabetic(c));
        }
        
        //symbols
        for (char c : symbols.toCharArray()) {
            Assert.assertFalse(StringUtility.isAlphabetic(c));
        }
        
        //white space
        for (char c : whiteSpace.toCharArray()) {
            Assert.assertFalse(StringUtility.isAlphabetic(c));
        }
        
        //valid strings
        Assert.assertTrue(StringUtility.isAlphabetic("agaerg"));
        Assert.assertTrue(StringUtility.isAlphabetic("FADHAED"));
        Assert.assertTrue(StringUtility.isAlphabetic("FaeADHAasfsdgED"));
        Assert.assertTrue(StringUtility.isAlphabetic("agfFaeADHAasfsdgEDjkl"));
        Assert.assertTrue(StringUtility.isAlphabetic(""));
        
        //invalid strings
        Assert.assertFalse(StringUtility.isAlphabetic("aga erg"));
        Assert.assertFalse(StringUtility.isAlphabetic("FA#DHAE$D"));
        Assert.assertFalse(StringUtility.isAlphabetic("ASF456"));
        Assert.assertFalse(StringUtility.isAlphabetic("84945"));
        Assert.assertFalse(StringUtility.isAlphabetic("   84945"));
        Assert.assertFalse(StringUtility.isAlphabetic("84945 "));
        Assert.assertFalse(StringUtility.isAlphabetic("ASF\n456"));
        Assert.assertFalse(StringUtility.isAlphabetic("afeAGA3t3tEA46"));
        Assert.assertFalse(StringUtility.isAlphabetic("a!feAGA#3t3tE$A46\n"));
    }
    
    /**
     * JUnit test of isVowel.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#isVowel(char)
     */
    @Test
    public void testIsVowel() throws Exception {
        //vowels
        for (char c : vowels.toCharArray()) {
            Assert.assertTrue(StringUtility.isVowel(c));
        }
        
        //consonants
        for (char c : consonants.toCharArray()) {
            Assert.assertFalse(StringUtility.isVowel(c));
        }
        
        //numbers
        for (char c : numbers.toCharArray()) {
            Assert.assertFalse(StringUtility.isVowel(c));
        }
        
        //symbols
        for (char c : symbols.toCharArray()) {
            Assert.assertFalse(StringUtility.isVowel(c));
        }
        
        //white space
        for (char c : whiteSpace.toCharArray()) {
            Assert.assertFalse(StringUtility.isVowel(c));
        }
    }
    
    /**
     * JUnit test of isConsonant.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#isConsonant(char)
     */
    @Test
    public void testIsConsonant() throws Exception {
        //vowels
        for (char c : vowels.toCharArray()) {
            Assert.assertFalse(StringUtility.isConsonant(c));
        }
        
        //consonants
        for (char c : consonants.toCharArray()) {
            Assert.assertTrue(StringUtility.isConsonant(c));
        }
        
        //numbers
        for (char c : numbers.toCharArray()) {
            Assert.assertFalse(StringUtility.isConsonant(c));
        }
        
        //symbols
        for (char c : symbols.toCharArray()) {
            Assert.assertFalse(StringUtility.isConsonant(c));
        }
        
        //white space
        for (char c : whiteSpace.toCharArray()) {
            Assert.assertFalse(StringUtility.isConsonant(c));
        }
    }
    
    /**
     * JUnit test of isNumeric.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#isNumeric(char)
     * @see StringUtility#isNumeric(String)
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testIsNumeric() throws Exception {
        //vowels
        for (char c : vowels.toCharArray()) {
            Assert.assertFalse(StringUtility.isNumeric(c));
        }
        
        //consonants
        for (char c : consonants.toCharArray()) {
            Assert.assertFalse(StringUtility.isNumeric(c));
        }
        
        //numbers
        for (char c : numbers.toCharArray()) {
            Assert.assertTrue(StringUtility.isNumeric(c));
        }
        
        //symbols
        for (char c : symbols.toCharArray()) {
            Assert.assertFalse(StringUtility.isNumeric(c));
        }
        
        //white space
        for (char c : whiteSpace.toCharArray()) {
            Assert.assertFalse(StringUtility.isNumeric(c));
        }
        
        //valid strings
        Assert.assertTrue(StringUtility.isNumeric("84945"));
        Assert.assertTrue(StringUtility.isNumeric("126549821668"));
        Assert.assertTrue(StringUtility.isNumeric("05648004887"));
        
        //invalid strings
        Assert.assertFalse(StringUtility.isNumeric("agaerg"));
        Assert.assertFalse(StringUtility.isNumeric("FADHAED"));
        Assert.assertFalse(StringUtility.isNumeric("FaeADHAasfsdgED"));
        Assert.assertFalse(StringUtility.isNumeric("agfFaeADHAasfsdgEDjkl"));
        Assert.assertFalse(StringUtility.isNumeric("aga erg"));
        Assert.assertFalse(StringUtility.isNumeric("FA#DHAE$D"));
        Assert.assertFalse(StringUtility.isNumeric("ASF456"));
        Assert.assertFalse(StringUtility.isNumeric("   84945"));
        Assert.assertFalse(StringUtility.isNumeric("84945 "));
        Assert.assertFalse(StringUtility.isNumeric("ASF\n456"));
        Assert.assertFalse(StringUtility.isNumeric("afeAGA3t3tEA46"));
        Assert.assertFalse(StringUtility.isNumeric("a!feAGA#3t3tE$A46\n"));
        Assert.assertFalse(StringUtility.isNumeric(""));
    }
    
    /**
     * JUnit test of isSymbol.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#isSymbol(char)
     * @see StringUtility#isSymbol(String)
     */
    @Test
    public void testIsSymbol() throws Exception {
        //vowels
        for (char c : vowels.toCharArray()) {
            Assert.assertFalse(StringUtility.isSymbol(c));
        }
        
        //consonants
        for (char c : consonants.toCharArray()) {
            Assert.assertFalse(StringUtility.isSymbol(c));
        }
        
        //numbers
        for (char c : numbers.toCharArray()) {
            Assert.assertFalse(StringUtility.isSymbol(c));
        }
        
        //symbols
        for (char c : symbols.toCharArray()) {
            Assert.assertTrue(StringUtility.isSymbol(c));
        }
        
        //white space
        for (char c : whiteSpace.toCharArray()) {
            Assert.assertFalse(StringUtility.isSymbol(c));
        }
        
        //valid strings
        Assert.assertTrue(StringUtility.isSymbol("?!."));
        Assert.assertTrue(StringUtility.isSymbol("+$#)_"));
        Assert.assertTrue(StringUtility.isSymbol("#@%@&{:{}<:>,.;[];]"));
        Assert.assertTrue(StringUtility.isSymbol(""));
        
        //invalid strings
        Assert.assertFalse(StringUtility.isSymbol("agaerg"));
        Assert.assertFalse(StringUtility.isSymbol("FADHAED"));
        Assert.assertFalse(StringUtility.isSymbol("FaeADHAasfsdgED"));
        Assert.assertFalse(StringUtility.isSymbol("agfFaeADHAasfsdgEDjkl"));
        Assert.assertFalse(StringUtility.isSymbol("126549821668"));
        Assert.assertFalse(StringUtility.isSymbol("05648004887"));
        Assert.assertFalse(StringUtility.isSymbol("aga erg"));
        Assert.assertFalse(StringUtility.isSymbol("FA#DHAE$D"));
        Assert.assertFalse(StringUtility.isSymbol("ASF456"));
        Assert.assertFalse(StringUtility.isSymbol("   84945"));
        Assert.assertFalse(StringUtility.isSymbol("84945 "));
        Assert.assertFalse(StringUtility.isSymbol("ASF\n456"));
        Assert.assertFalse(StringUtility.isSymbol("afeAGA3t3tEA46"));
        Assert.assertFalse(StringUtility.isSymbol("a!feAGA#3t3tE$A46\n"));
    }
    
    /**
     * JUnit test of isWhitespace.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#isWhitespace(char)
     * @see StringUtility#isWhitespace(String)
     */
    @Test
    public void testIsWhitespace() throws Exception {
        //vowels
        for (char c : vowels.toCharArray()) {
            Assert.assertFalse(StringUtility.isWhitespace(c));
        }
        
        //consonants
        for (char c : consonants.toCharArray()) {
            Assert.assertFalse(StringUtility.isWhitespace(c));
        }
        
        //numbers
        for (char c : numbers.toCharArray()) {
            Assert.assertFalse(StringUtility.isWhitespace(c));
        }
        
        //symbols
        for (char c : symbols.toCharArray()) {
            Assert.assertFalse(StringUtility.isWhitespace(c));
        }
        
        //white space
        for (char c : whiteSpace.toCharArray()) {
            Assert.assertTrue(StringUtility.isWhitespace(c));
        }
        
        //valid strings
        Assert.assertTrue(StringUtility.isWhitespace("    "));
        Assert.assertTrue(StringUtility.isWhitespace("\t\t"));
        Assert.assertTrue(StringUtility.isWhitespace("\n\n\n\n"));
        Assert.assertTrue(StringUtility.isWhitespace("\r\n\r\n\r\n\r\n"));
        Assert.assertTrue(StringUtility.isWhitespace("\0\0\0\0"));
        Assert.assertTrue(StringUtility.isWhitespace(" \t \0 \n\r\n\0  \t "));
        Assert.assertTrue(StringUtility.isWhitespace(""));
        
        //invalid strings
        Assert.assertFalse(StringUtility.isWhitespace("agaerg"));
        Assert.assertFalse(StringUtility.isWhitespace("FADHAED"));
        Assert.assertFalse(StringUtility.isWhitespace("FaeADHAasfsdgED"));
        Assert.assertFalse(StringUtility.isWhitespace("agfFaeADHAasfsdgEDjkl"));
        Assert.assertFalse(StringUtility.isWhitespace("126549821668"));
        Assert.assertFalse(StringUtility.isWhitespace("05648004887"));
        Assert.assertFalse(StringUtility.isWhitespace("aga erg"));
        Assert.assertFalse(StringUtility.isWhitespace("FA#DHAE$D"));
        Assert.assertFalse(StringUtility.isWhitespace("ASF456"));
        Assert.assertFalse(StringUtility.isWhitespace("   84945"));
        Assert.assertFalse(StringUtility.isWhitespace("84945 "));
        Assert.assertFalse(StringUtility.isWhitespace("ASF\n456"));
        Assert.assertFalse(StringUtility.isWhitespace("afeAGA3t3tEA46"));
        Assert.assertFalse(StringUtility.isWhitespace("a!feAGA#3t3tE$A46\n"));
    }
    
    /**
     * JUnit test of removeWhiteSpace.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#removeWhiteSpace(String)
     */
    @Test
    public void testRemoveWhiteSpace() throws Exception {
        //whitespace
        Assert.assertEquals("astring", StringUtility.removeWhiteSpace("a string"));
        Assert.assertEquals("atabbedstring", StringUtility.removeWhiteSpace("a\ttabbed\t\t\tstring\t"));
        Assert.assertEquals("amultilinestring", StringUtility.removeWhiteSpace("a\nmultiline\nstring"));
        Assert.assertEquals("amultilinestring", StringUtility.removeWhiteSpace("a\r\nmultiline\n\n\n\nstring"));
        Assert.assertEquals("amultilinestring", StringUtility.removeWhiteSpace("a\0multiline\0string"));
        Assert.assertEquals("thisisastring", StringUtility.removeWhiteSpace("\0\0  this\n is\t\t a\0\0\0   string\r\n   \0\0"));
        
        //no whitespace
        Assert.assertEquals("string", StringUtility.removeWhiteSpace("string"));
        Assert.assertEquals("ABC234546#!45", StringUtility.removeWhiteSpace("ABC234546#!45"));
        Assert.assertEquals("123-456-7890", StringUtility.removeWhiteSpace("123-456-7890"));
        Assert.assertEquals("", StringUtility.removeWhiteSpace(""));
    }
    
    /**
     * JUnit test of removeDiacritics.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#removeDiacritics(String)
     */
    @Test
    public void testRemoveDiacritics() throws Exception {
        //diacritics
        Assert.assertEquals("a string", StringUtility.removeDiacritics("â string"));
        Assert.assertEquals("A string", StringUtility.removeDiacritics("Ā string"));
        Assert.assertEquals("a, e, i, o, u, n",
                StringUtility.removeDiacritics("á, é, í, ó, ú, ñ"));
        Assert.assertEquals("a, e, i, u, c, g, k, l, n, s, z, a, e, i, u, u, e",
                StringUtility.removeDiacritics("ā, ē, ī, ū, č, ģ, ķ, ļ, ņ, š, ž, ą, ę, į, ų, ū, ė"));
        Assert.assertEquals("a, e, i, o, u, w, y, a, e, i, o, u, w, y, a, e, i, o, u, w, y, a, e, i, o, u, w, y",
                StringUtility.removeDiacritics("â, ê, î, ô, û, ŵ, ŷ, ä, ë, ï, ö, ü, ẅ, ÿ, á, é, í, ó, ú, ẃ, ý, à, è, ì, ò, ù, ẁ, ỳ"));
        Assert.assertEquals("у, и, е, ґ, и, і, к, г, и",
                StringUtility.removeDiacritics("ў, й, ё, ґ, й, ї, ќ, ѓ, ѝ"));
        Assert.assertEquals("o, a, o, u, a, s, z, o, u, a, a, a, d, e, i, l, n, o, o, o, o, o, r, s, t, u, z",
                StringUtility.removeDiacritics("õ, ä, ö, ü, å, š, ž, ő, ű, ā, ä, ǟ, ḑ, ē, ī, ļ, ņ, ō, ȯ, ȱ, õ, ȭ, ŗ, š, ț, ū, ž"));
        Assert.assertEquals("a, i, o, u, y, o, a",
                StringUtility.removeDiacritics("á, í, ó, ú, ý, ö, å"));
        Assert.assertEquals("c, c, s, z, c, d, e, n, r, s, t, z, u, a, c, e, n, o, s, z, z, a, e, i, o, u, y, l, r, c, d, l, n, s, t, z, a, o",
                StringUtility.removeDiacritics("č, ć, š, ž, č, ď, ě, ň, ř, š, ť, ž, ů, ą, ć, ę, ń, ó, ś, ź, ż, á, é, í, ó, ú, ý, ĺ, ŕ, č, ď, ľ, ň, š, ť, ž, ä, ô"));
        Assert.assertEquals("C, G, I, I, O, S, U, N, A, E, S, T, G, Y, Z",
                StringUtility.removeDiacritics("Ç, Ğ, I, İ, Ö, Ş, Ü, Ñ, Ä, Ê, Ș, Ț, Ğ, Ý, Ž"));
        Assert.assertEquals("C, E, u, c, g, h, j, s, C, E, I, S, U, c, h, g, s, z, a, a, c, i, n, o, s, u, e, C, G, Z",
                StringUtility.removeDiacritics("Ç, Ë, ŭ, ĉ, ĝ, ĥ, ĵ, ŝ, Ç, Ê, Î, Ş, Û, č, ȟ, ǧ, š, ž, â, ā, ç, î, ñ, ô, š, û, ē, Ċ, Ġ, Ż"));
        Assert.assertEquals("A, C, D, E, E, G, H, O, S, S, T, U, Z",
                StringUtility.removeDiacritics("Ā, Č, Ḏ, Ē, Ë, Ġ, Ḥ, Ō, Š, Ṣ, Ṭ, Ū, Ž"));
        
        //no diacritics
        Assert.assertEquals("string", StringUtility.removeDiacritics("string"));
        Assert.assertEquals("ABC234546#!45", StringUtility.removeDiacritics("ABC234546#!45"));
        Assert.assertEquals("123-456-7890", StringUtility.removeDiacritics("123-456-7890"));
        Assert.assertEquals("", StringUtility.removeDiacritics(""));
    }
    
    /**
     * JUnit test of replaceCharAt.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#replaceCharAt(String, int, char)
     */
    @Test
    public void testReplaceCharAt() throws Exception {
        //standard
        Assert.assertEquals("i string", StringUtility.replaceCharAt("a string", 0, 'i'));
        Assert.assertEquals("a strung", StringUtility.replaceCharAt("a string", 5, 'u'));
        Assert.assertEquals("a strint", StringUtility.replaceCharAt("a string", 7, 't'));
        Assert.assertEquals("i", StringUtility.replaceCharAt("a", 0, 'i'));
        
        //edge cases
        Assert.assertEquals("a string", StringUtility.replaceCharAt("a string", -1, 'u'));
        Assert.assertEquals("a string", StringUtility.replaceCharAt("a string", 8, 'u'));
    }
    
    /**
     * JUnit test of insertCharAt.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#insertCharAt(String, int, char)
     */
    @Test
    public void testInsertCharAt() throws Exception {
        //standard
        Assert.assertEquals("ia string", StringUtility.insertCharAt("a string", 0, 'i'));
        Assert.assertEquals("a struing", StringUtility.insertCharAt("a string", 5, 'u'));
        Assert.assertEquals("a strintg", StringUtility.insertCharAt("a string", 7, 't'));
        Assert.assertEquals("a stringt", StringUtility.insertCharAt("a string", 8, 't'));
        Assert.assertEquals("ia", StringUtility.insertCharAt("a", 0, 'i'));
        Assert.assertEquals("ai", StringUtility.insertCharAt("a", 1, 'i'));
        
        //edge cases
        Assert.assertEquals("a string", StringUtility.insertCharAt("a string", -1, 'u'));
        Assert.assertEquals("a string", StringUtility.insertCharAt("a string", 9, 'u'));
    }
    
    /**
     * JUnit test of deleteCharAt.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#deleteCharAt(String, int)
     */
    @Test
    public void testDeleteCharAt() throws Exception {
        //standard
        Assert.assertEquals(" string", StringUtility.deleteCharAt("a string", 0));
        Assert.assertEquals("a strng", StringUtility.deleteCharAt("a string", 5));
        Assert.assertEquals("a strin", StringUtility.deleteCharAt("a string", 7));
        Assert.assertEquals("", StringUtility.deleteCharAt("a", 0));
        
        //edge cases
        Assert.assertEquals("a string", StringUtility.deleteCharAt("a string", -1));
        Assert.assertEquals("a string", StringUtility.deleteCharAt("a string", 8));
    }
    
    /**
     * JUnit test of replaceSubstringAt.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#replaceSubstringAt(String, int, int, String)
     */
    @Test
    public void testReplaceSubstringAt() throws Exception {
        //standard
        Assert.assertEquals("another string", StringUtility.replaceSubstringAt("a string", 0, 1, "another"));
        Assert.assertEquals("another string", StringUtility.replaceSubstringAt("a string", 1, 1, "nother"));
        Assert.assertEquals("a strung", StringUtility.replaceSubstringAt("a string", 5, 6, "u"));
        Assert.assertEquals("a strut", StringUtility.replaceSubstringAt("a string", 5, 8, "ut"));
        Assert.assertEquals("a stringer", StringUtility.replaceSubstringAt("a string", 7, 8, "ger"));
        Assert.assertEquals("a stringer", StringUtility.replaceSubstringAt("a string", 8, 8, "er"));
        Assert.assertEquals("another", StringUtility.replaceSubstringAt("a", 0, 1, "another"));
        Assert.assertEquals("another", StringUtility.replaceSubstringAt("a", 1, 1, "nother"));
        
        //edge cases
        Assert.assertEquals("a string", StringUtility.replaceSubstringAt("a string", -1, 2, "u"));
        Assert.assertEquals("a string", StringUtility.replaceSubstringAt("a string", -1, -1, "u"));
        Assert.assertEquals("a string", StringUtility.replaceSubstringAt("a string", 4, 9, "u"));
        Assert.assertEquals("a string", StringUtility.replaceSubstringAt("a string", 9, 9, "u"));
        Assert.assertEquals("a string", StringUtility.replaceSubstringAt("a string", 5, 2, "u"));
    }
    
    /**
     * JUnit test of insertSubStringAt.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#insertSubstringAt(String, int, String)
     */
    @Test
    public void testInsertSubstringAt() throws Exception {
        //standard
        Assert.assertEquals("this is a string", StringUtility.insertSubstringAt("a string", 0, "this is "));
        Assert.assertEquals("a strung string", StringUtility.insertSubstringAt("a string", 5, "ung str"));
        Assert.assertEquals("a strinterg", StringUtility.insertSubstringAt("a string", 7, "ter"));
        Assert.assertEquals("a stringter", StringUtility.insertSubstringAt("a string", 8, "ter"));
        Assert.assertEquals("string a", StringUtility.insertSubstringAt("a", 0, "string "));
        Assert.assertEquals("a string", StringUtility.insertSubstringAt("a", 1, " string"));
        
        //edge cases
        Assert.assertEquals("a string", StringUtility.insertSubstringAt("a string", -1, "this is "));
        Assert.assertEquals("a string", StringUtility.insertSubstringAt("a string", 9, "ter"));
    }
    
    /**
     * JUnit test of deleteSubstringAt.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#deleteSubstringAt(String, int, int)
     */
    @Test
    public void testDeleteSubstringAt() throws Exception {
        //standard
        Assert.assertEquals("a string", StringUtility.deleteSubstringAt("a string", 0, 0));
        Assert.assertEquals(" string", StringUtility.deleteSubstringAt("a string", 0, 1));
        Assert.assertEquals("a strg", StringUtility.deleteSubstringAt("a string", 5, 7));
        Assert.assertEquals("a str", StringUtility.deleteSubstringAt("a string", 5, 8));
        Assert.assertEquals("a strin", StringUtility.deleteSubstringAt("a string", 7, 8));
        Assert.assertEquals("", StringUtility.deleteSubstringAt("a", 0, 1));
        
        //edge cases
        Assert.assertEquals("a string", StringUtility.deleteSubstringAt("a string", -1, 2));
        Assert.assertEquals("a string", StringUtility.deleteSubstringAt("a string", -1, -1));
        Assert.assertEquals("a string", StringUtility.deleteSubstringAt("a string", 4, 9));
        Assert.assertEquals("a string", StringUtility.deleteSubstringAt("a string", 8, 8));
        Assert.assertEquals("a string", StringUtility.deleteSubstringAt("a string", 8, 9));
        Assert.assertEquals("a string", StringUtility.deleteSubstringAt("a string", 5, 2));
    }
    
    /**
     * JUnit test of trim.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#trim(String)
     */
    @Test
    public void testTrim() throws Exception {
        //prepended white space
        Assert.assertEquals("string", StringUtility.trim(" string"));
        Assert.assertEquals("string", StringUtility.trim("\tstring"));
        Assert.assertEquals("string", StringUtility.trim("\nstring"));
        Assert.assertEquals("string", StringUtility.trim("\r\nstring"));
        Assert.assertEquals("string", StringUtility.trim("\0string"));
        Assert.assertEquals("string", StringUtility.trim("\0\0\0   \t  \n \r\n   string"));
        
        //appended white space
        Assert.assertEquals("string", StringUtility.trim("string "));
        Assert.assertEquals("string", StringUtility.trim("string\t"));
        Assert.assertEquals("string", StringUtility.trim("string\n"));
        Assert.assertEquals("string", StringUtility.trim("string\r\n"));
        Assert.assertEquals("string", StringUtility.trim("string\0"));
        Assert.assertEquals("string", StringUtility.trim("string   \t  \n \r\n   \0\0\0"));
        
        //prepended and appended white space
        Assert.assertEquals("string", StringUtility.trim(" string "));
        Assert.assertEquals("string", StringUtility.trim("\tstring\t"));
        Assert.assertEquals("string", StringUtility.trim("\nstring\n"));
        Assert.assertEquals("string", StringUtility.trim("\r\nstring\r\n"));
        Assert.assertEquals("string", StringUtility.trim("\0string\0"));
        Assert.assertEquals("string", StringUtility.trim("\0\0\0   \t  \n \r\n   string   \t  \n \r\n   \0\0\0"));
        
        //internal white space
        Assert.assertEquals("a string with spaces", StringUtility.trim(" a string with spaces "));
        Assert.assertEquals("a string with spaces", StringUtility.trim("\ta string with spaces\t"));
        Assert.assertEquals("a string with spaces", StringUtility.trim("\na string with spaces\n"));
        Assert.assertEquals("a string with spaces", StringUtility.trim("\r\na string with spaces\r\n"));
        Assert.assertEquals("a string with spaces", StringUtility.trim("   \t\0  \n \r\n   a string with spaces  \0 \t  \n \r\n   "));
        
        //no white space
        Assert.assertEquals("string", StringUtility.trim("string"));
    }
    
    /**
     * JUnit test of lTrim.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#lTrim(String)
     */
    @Test
    public void testLTrim() throws Exception {
        //prepended white space
        Assert.assertEquals("string", StringUtility.lTrim(" string"));
        Assert.assertEquals("string", StringUtility.lTrim("\tstring"));
        Assert.assertEquals("string", StringUtility.lTrim("\nstring"));
        Assert.assertEquals("string", StringUtility.lTrim("\r\nstring"));
        Assert.assertEquals("string", StringUtility.lTrim("\0string"));
        Assert.assertEquals("string", StringUtility.lTrim("\0\0\0   \t  \n \r\n   string"));
        
        //internal white space
        Assert.assertEquals("a string with spaces", StringUtility.lTrim(" a string with spaces"));
        Assert.assertEquals("a string with spaces", StringUtility.lTrim("\ta string with spaces"));
        Assert.assertEquals("a string with spaces", StringUtility.lTrim("\na string with spaces"));
        Assert.assertEquals("a string with spaces", StringUtility.lTrim("\r\na string with spaces"));
        Assert.assertEquals("a string with spaces", StringUtility.lTrim("\0a string with spaces"));
        Assert.assertEquals("a string with spaces", StringUtility.lTrim("\0   \t  \n \r\n\0\0   a string with spaces"));
        
        //no white space
        Assert.assertEquals("string", StringUtility.lTrim("string"));
    }
    
    /**
     * JUnit test of rTrim.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#rTrim(String)
     */
    @Test
    public void testRTrim() throws Exception {
        //appended white space
        Assert.assertEquals("string", StringUtility.rTrim("string "));
        Assert.assertEquals("string", StringUtility.rTrim("string\t"));
        Assert.assertEquals("string", StringUtility.rTrim("string\n"));
        Assert.assertEquals("string", StringUtility.rTrim("string\r\n"));
        Assert.assertEquals("string", StringUtility.rTrim("string\0"));
        Assert.assertEquals("string", StringUtility.rTrim("string   \t  \n \r\n   \0\0\0"));
        
        //internal white space
        Assert.assertEquals("a string with spaces", StringUtility.rTrim("a string with spaces "));
        Assert.assertEquals("a string with spaces", StringUtility.rTrim("a string with spaces\t"));
        Assert.assertEquals("a string with spaces", StringUtility.rTrim("a string with spaces\n"));
        Assert.assertEquals("a string with spaces", StringUtility.rTrim("a string with spaces\r\n"));
        Assert.assertEquals("a string with spaces", StringUtility.rTrim("a string with spaces\0"));
        Assert.assertEquals("a string with spaces", StringUtility.rTrim("a string with spaces \0  \t  \n\0 \r\n   \0"));
        
        //no white space
        Assert.assertEquals("string", StringUtility.rTrim("string"));
    }
    
    /**
     * JUnit test of skin.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#skin(String, int)
     */
    @Test
    public void testSkin() throws Exception {
        //standard
        Assert.assertEquals("this is a string", StringUtility.skin("this is a string", 0));
        Assert.assertEquals("is is a stri", StringUtility.skin("this is a string", 2));
        Assert.assertEquals("is a s", StringUtility.skin("this is a string", 5));
        Assert.assertEquals("", StringUtility.skin("this is a string", 10));
        Assert.assertEquals("", StringUtility.skin("this is a string", 15));
        
        //edge cases
        Assert.assertEquals("this is a string", StringUtility.skin("this is a string", -1));
        Assert.assertEquals("", StringUtility.skin("this is a string", 16));
        Assert.assertEquals("", StringUtility.skin("this is a string", 100));
    }
    
    /**
     * JUnit test of lShear.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#lShear(String, int)
     */
    @Test
    public void testLShear() throws Exception {
        //standard
        Assert.assertEquals("this is a string", StringUtility.lShear("this is a string", 0));
        Assert.assertEquals("is is a string", StringUtility.lShear("this is a string", 2));
        Assert.assertEquals("is a string", StringUtility.lShear("this is a string", 5));
        Assert.assertEquals("string", StringUtility.lShear("this is a string", 10));
        Assert.assertEquals("g", StringUtility.lShear("this is a string", 15));
        
        //edge cases
        Assert.assertEquals("this is a string", StringUtility.lShear("this is a string", -1));
        Assert.assertEquals("", StringUtility.lShear("this is a string", 16));
        Assert.assertEquals("", StringUtility.lShear("this is a string", 100));
    }
    
    /**
     * JUnit test of rShear.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#rShear(String, int)
     */
    @Test
    public void testRShear() throws Exception {
        //standard
        Assert.assertEquals("this is a string", StringUtility.rShear("this is a string", 0));
        Assert.assertEquals("this is a stri", StringUtility.rShear("this is a string", 2));
        Assert.assertEquals("this is a s", StringUtility.rShear("this is a string", 5));
        Assert.assertEquals("this i", StringUtility.rShear("this is a string", 10));
        Assert.assertEquals("t", StringUtility.rShear("this is a string", 15));
        
        //edge cases
        Assert.assertEquals("this is a string", StringUtility.rShear("this is a string", -1));
        Assert.assertEquals("", StringUtility.rShear("this is a string", 16));
        Assert.assertEquals("", StringUtility.rShear("this is a string", 100));
    }
    
    /**
     * JUnit test of gut.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#gut(String, int)
     */
    @Test
    public void testGut() throws Exception {
        //standard
        Assert.assertEquals("", StringUtility.gut("this is a string", 0));
        Assert.assertEquals("thng", StringUtility.gut("this is a string", 2));
        Assert.assertEquals("this tring", StringUtility.gut("this is a string", 5));
        Assert.assertEquals("this is a string", StringUtility.gut("this is a string", 8));
        Assert.assertEquals("this is an string", StringUtility.gut("this is an string", 8));
        Assert.assertEquals("this is a string", StringUtility.gut("this is a string", 10));
        Assert.assertEquals("this is a string", StringUtility.gut("this is a string", 15));
        
        //edge cases
        Assert.assertEquals("", StringUtility.gut("this is a string", -1));
        Assert.assertEquals("this is a string", StringUtility.gut("this is a string", 16));
        Assert.assertEquals("this is a string", StringUtility.gut("this is a string", 100));
    }
    
    /**
     * JUnit test of lSnip.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#lSnip(String, int)
     */
    @Test
    public void testLSnip() throws Exception {
        //standard
        Assert.assertEquals("", StringUtility.lSnip("this is a string", 0));
        Assert.assertEquals("th", StringUtility.lSnip("this is a string", 2));
        Assert.assertEquals("this ", StringUtility.lSnip("this is a string", 5));
        Assert.assertEquals("this is a ", StringUtility.lSnip("this is a string", 10));
        Assert.assertEquals("this is a strin", StringUtility.lSnip("this is a string", 15));
        
        //edge cases
        Assert.assertEquals("", StringUtility.lSnip("this is a string", -1));
        Assert.assertEquals("this is a string", StringUtility.lSnip("this is a string", 16));
        Assert.assertEquals("this is a string", StringUtility.lSnip("this is a string", 100));
    }
    
    /**
     * JUnit test of rSnip.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#rSnip(String, int)
     */
    @Test
    public void testRSnip() throws Exception {
        //standard
        Assert.assertEquals("", StringUtility.rSnip("this is a string", 0));
        Assert.assertEquals("ng", StringUtility.rSnip("this is a string", 2));
        Assert.assertEquals("tring", StringUtility.rSnip("this is a string", 5));
        Assert.assertEquals("s a string", StringUtility.rSnip("this is a string", 10));
        Assert.assertEquals("his is a string", StringUtility.rSnip("this is a string", 15));
        
        //edge cases
        Assert.assertEquals("", StringUtility.rSnip("this is a string", -1));
        Assert.assertEquals("this is a string", StringUtility.rSnip("this is a string", 16));
        Assert.assertEquals("this is a string", StringUtility.rSnip("this is a string", 100));
    }
    
    /**
     * JUnit test of toCamelCase.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#toCamelCase(String)
     */
    @Test
    public void testToCamelCase() throws Exception {
        //valid
        Assert.assertEquals("name", StringUtility.toCamelCase("name"));
        Assert.assertEquals("name", StringUtility.toCamelCase("Name"));
        Assert.assertEquals("name", StringUtility.toCamelCase("NAME"));
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("theBestName")); //camel case
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("TheBestName")); //pascal case
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("THEBestName"));
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("the best name"));
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("The Best Name"));
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("THE Best Name"));
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("the_best_name"));
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("The_Best_Name"));
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("THE_BEST_NAME")); //constant case
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("the best:name"));
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("The~Best.Name"));
        Assert.assertEquals("theBestName", StringUtility.toCamelCase("THE-BEST-NAME"));
        
        //invalid
        Assert.assertNotEquals("theBestName", StringUtility.toCamelCase("THEBESTNAME"));
        Assert.assertNotEquals("theBestName", StringUtility.toCamelCase("tHEbESTnAME"));
    }
    
    /**
     * JUnit test of toPascalCase.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#toPascalCase(String)
     */
    @Test
    public void testToPascalCase() throws Exception {
        //valid
        Assert.assertEquals("Name", StringUtility.toPascalCase("name"));
        Assert.assertEquals("Name", StringUtility.toPascalCase("Name"));
        Assert.assertEquals("Name", StringUtility.toPascalCase("NAME"));
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("theBestName")); //camel case
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("TheBestName")); //pascal case
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("THEBestName"));
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("the best name"));
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("The Best Name"));
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("THE Best Name"));
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("the_best_name"));
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("The_Best_Name"));
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("THE_BEST_NAME")); //constant case
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("the best:name"));
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("The~Best.Name"));
        Assert.assertEquals("TheBestName", StringUtility.toPascalCase("THE-BEST-NAME"));
        
        //invalid
        Assert.assertNotEquals("TheBestName", StringUtility.toPascalCase("THEBESTNAME"));
        Assert.assertNotEquals("TheBestName", StringUtility.toPascalCase("tHEbESTnAME"));
    }
    
    /**
     * JUnit test of toConstantCase.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#toConstantCase(String)
     */
    @Test
    public void testToConstantCase() throws Exception {
        //valid
        Assert.assertEquals("NAME", StringUtility.toConstantCase("name"));
        Assert.assertEquals("NAME", StringUtility.toConstantCase("Name"));
        Assert.assertEquals("NAME", StringUtility.toConstantCase("NAME"));
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("theBestName")); //camel case
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("TheBestName")); //pascal case
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("THEBestName"));
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("the best name"));
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("The Best Name"));
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("THE Best Name"));
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("the_best_name"));
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("The_Best_Name"));
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("THE_BEST_NAME")); //constant case
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("the best:name"));
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("The~Best.Name"));
        Assert.assertEquals("THE_BEST_NAME", StringUtility.toConstantCase("THE-BEST-NAME"));
        
        //invalid
        Assert.assertNotEquals("THE_BEST_NAME", StringUtility.toConstantCase("THEBESTNAME"));
        Assert.assertNotEquals("THE_BEST_NAME", StringUtility.toConstantCase("tHEbESTnAME"));
    }
    
    /**
     * JUnit test of toTitleCase.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#toTitleCase(String)
     */
    @Test
    public void testToTitleCase() throws Exception {
        //valid
        Assert.assertEquals("Name", StringUtility.toTitleCase("Name"));
        Assert.assertEquals("Name", StringUtility.toTitleCase("name"));
        Assert.assertEquals("NAME", StringUtility.toTitleCase("NAME"));
        Assert.assertEquals("The Best Name", StringUtility.toTitleCase("The Best Name"));
        Assert.assertEquals("The Best Name", StringUtility.toTitleCase("the Best Name"));
        Assert.assertEquals("THE Best Name", StringUtility.toTitleCase("THE Best Name"));
        Assert.assertEquals("The Best Name", StringUtility.toTitleCase("the best name"));
        Assert.assertEquals("The Best Name", StringUtility.toTitleCase("The Best Name"));
        Assert.assertEquals("THE Best Name", StringUtility.toTitleCase("THE Best name"));
        Assert.assertEquals("THE BEST NAME", StringUtility.toTitleCase("THE BEST NAME"));
        Assert.assertEquals("THE BEST NAME", StringUtility.toTitleCase(" THE BEST NAME"));
        Assert.assertEquals("THE BEST NAME", StringUtility.toTitleCase("THE BEST NAME "));
        Assert.assertEquals("THE BEST NAME", StringUtility.toTitleCase("THE BEST    NAME"));
        Assert.assertEquals("THE BEST NAME", StringUtility.toTitleCase(" THE BEST    NAME "));
        Assert.assertEquals("The TITLE of the Book", StringUtility.toTitleCase("the TITLE of the book"));
        Assert.assertEquals("Book is the TITLE of the Book The", StringUtility.toTitleCase("book Is the TITLE of the book the"));
        
        //invalid
        Assert.assertNotEquals("The Best Name", StringUtility.toTitleCase("THEBESTNAME"));
        Assert.assertNotEquals("The Best Name", StringUtility.toTitleCase("tHEbESTnAME"));
        Assert.assertNotEquals("The Best Name", StringUtility.toTitleCase("the_best_name"));
        Assert.assertNotEquals("The Best Name", StringUtility.toTitleCase("The_Best_Name"));
        Assert.assertNotEquals("The Best Name", StringUtility.toTitleCase("THE_BEST_NAME"));
    }
    
    /**
     * JUnit test of toUpperTitleCase.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#toUpperTitleCase(String)
     */
    @Test
    public void testToUpperTitleCase() throws Exception {
        //valid
        Assert.assertEquals("Name", StringUtility.toUpperTitleCase("Name"));
        Assert.assertEquals("Name", StringUtility.toUpperTitleCase("name"));
        Assert.assertEquals("NAME", StringUtility.toUpperTitleCase("NAME"));
        Assert.assertEquals("The Best Name", StringUtility.toUpperTitleCase("The Best Name"));
        Assert.assertEquals("The Best Name", StringUtility.toUpperTitleCase("the Best Name"));
        Assert.assertEquals("THE Best Name", StringUtility.toUpperTitleCase("THE Best Name"));
        Assert.assertEquals("The Best Name", StringUtility.toUpperTitleCase("the best name"));
        Assert.assertEquals("The Best Name", StringUtility.toUpperTitleCase("The Best Name"));
        Assert.assertEquals("THE Best Name", StringUtility.toUpperTitleCase("THE Best name"));
        Assert.assertEquals("THE BEST NAME", StringUtility.toUpperTitleCase("THE BEST NAME"));
        Assert.assertEquals("The TITLE Of The Book", StringUtility.toUpperTitleCase("the TITLE of the book"));
        Assert.assertEquals("Book The TITLE Of The Book The", StringUtility.toUpperTitleCase("book the TITLE of the book the"));
        
        //invalid
        Assert.assertNotEquals("The Best Name", StringUtility.toUpperTitleCase("THEBESTNAME"));
        Assert.assertNotEquals("The Best Name", StringUtility.toUpperTitleCase("tHEbESTnAME"));
        Assert.assertNotEquals("The Best Name", StringUtility.toUpperTitleCase("the_best_name"));
        Assert.assertNotEquals("The Best Name", StringUtility.toUpperTitleCase("The_Best_Name"));
        Assert.assertNotEquals("The Best Name", StringUtility.toUpperTitleCase("THE_BEST_NAME"));
    }
    
    /**
     * JUnit test of toSentenceCase.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#toSentenceCase(String)
     */
    @Test
    public void testToSentenceCase() throws Exception {
        //valid
        Assert.assertEquals("Name", StringUtility.toSentenceCase("Name"));
        Assert.assertEquals("Name", StringUtility.toSentenceCase("name"));
        Assert.assertEquals("Name", StringUtility.toSentenceCase("NAME"));
        Assert.assertEquals("The best name", StringUtility.toSentenceCase("The Best Name"));
        Assert.assertEquals("The best name", StringUtility.toSentenceCase("the Best Name"));
        Assert.assertEquals("The best name", StringUtility.toSentenceCase("THE Best Name"));
        Assert.assertEquals("The best name", StringUtility.toSentenceCase("the best name"));
        Assert.assertEquals("The best name", StringUtility.toSentenceCase("The Best Name"));
        Assert.assertEquals("The best name", StringUtility.toSentenceCase("THE Best name"));
        Assert.assertEquals("The best name", StringUtility.toSentenceCase("THE BEST NAME"));
        Assert.assertEquals("The title of the book", StringUtility.toSentenceCase("the TITLE of the book"));
        Assert.assertEquals("Book the title of the book the", StringUtility.toSentenceCase("book the TITLE of the book the"));
        
        //invalid
        Assert.assertNotEquals("The best name", StringUtility.toSentenceCase("THEBESTNAME"));
        Assert.assertNotEquals("The best name", StringUtility.toSentenceCase("tHEbESTnAME"));
        Assert.assertNotEquals("The best name", StringUtility.toSentenceCase("the_best_name"));
        Assert.assertNotEquals("The best name", StringUtility.toSentenceCase("The_Best_Name"));
        Assert.assertNotEquals("The best name", StringUtility.toSentenceCase("THE_BEST_NAME"));
    }
    
    /**
     * JUnit test of numberOfOccurrences.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#numberOfOccurrences(String, String)
     * @see StringUtility#numberOfOccurrences(String, String, int, int)
     */
    @Test
    public void testNumberOfOccurrences() throws Exception {
        //standard
        Assert.assertEquals(5, StringUtility.numberOfOccurrences("a", "That hat is a great hat"));
        Assert.assertEquals(5, StringUtility.numberOfOccurrences(" ", "That hat is a great hat"));
        Assert.assertEquals(3, StringUtility.numberOfOccurrences("hat", "That hat is a great hat"));
        Assert.assertEquals(2, StringUtility.numberOfOccurrences("hat ", "That hat is a great hat"));
        Assert.assertEquals(2, StringUtility.numberOfOccurrences(" hat", "That hat is a great hat"));
        Assert.assertEquals(1, StringUtility.numberOfOccurrences(" hat ", "That hat is a great hat"));
        Assert.assertEquals(1, StringUtility.numberOfOccurrences("great hat", "That hat is a great hat"));
        Assert.assertEquals(3, StringUtility.numberOfOccurrences("t ", "That hat is a great hat"));
        Assert.assertEquals(1, StringUtility.numberOfOccurrences("That hat is a great hat", "That hat is a great hat"));
        Assert.assertEquals(24, StringUtility.numberOfOccurrences("", "That hat is a great hat"));
        Assert.assertEquals(0, StringUtility.numberOfOccurrences("bad", "That hat is a great hat"));
        Assert.assertEquals(0, StringUtility.numberOfOccurrences("  ", "That hat is a great hat"));
        Assert.assertEquals(0, StringUtility.numberOfOccurrences("\n", "That hat is a great hat"));
        
        //regex
        Assert.assertEquals(5, StringUtility.numberOfOccurrences("\\s+", "That hat is a great hat"));
        Assert.assertEquals(9, StringUtility.numberOfOccurrences("a|t", "That hat is a great hat"));
        Assert.assertEquals(10, StringUtility.numberOfOccurrences("a|t|T", "That hat is a great hat"));
        Assert.assertEquals(4, StringUtility.numberOfOccurrences("great|hat", "That hat is a great hat"));
        Assert.assertEquals(1, StringUtility.numberOfOccurrences("^", "That hat is a great hat"));
        Assert.assertEquals(1, StringUtility.numberOfOccurrences("$", "That hat is a great hat"));
        
        //substring
        Assert.assertEquals(5, StringUtility.numberOfOccurrences("a", "That hat is a great hat", 0, 23));
        Assert.assertEquals(4, StringUtility.numberOfOccurrences(" ", "That hat is a great hat", 3, 14));
        Assert.assertEquals(2, StringUtility.numberOfOccurrences("hat", "That hat is a great hat", 0, 8));
        Assert.assertEquals(3, StringUtility.numberOfOccurrences("\\s+", "That hat is a great hat", 7, 14));
        Assert.assertEquals(4, StringUtility.numberOfOccurrences("a|t", "That hat is a great hat", 16, 23));
    }
    
    /**
     * JUnit test of fixSpaces.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#fixSpaces(String)
     */
    @Test
    public void testFixSpaces() throws Exception {
        //standard
        Assert.assertEquals("thisisastring", StringUtility.fixSpaces("thisisastring"));
        Assert.assertEquals("this is a string", StringUtility.fixSpaces("this is a string"));
        Assert.assertEquals("this is a string ", StringUtility.fixSpaces("this   is  a string   "));
        Assert.assertEquals(" this is a string ", StringUtility.fixSpaces("    this   is  a string  "));
        
        //edge cases
        Assert.assertEquals("", StringUtility.fixSpaces(""));
        Assert.assertEquals(" ", StringUtility.fixSpaces(" "));
        Assert.assertEquals(" ", StringUtility.fixSpaces("  "));
        Assert.assertEquals(" this is a string ", StringUtility.fixSpaces("    this  \r\n is  a\r\n string  "));
    }
    
    /**
     * JUnit test of removePunctuation.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#removePunctuation(String)
     */
    @Test
    public void testRemovePunctuation() throws Exception {
        //punctuation
        Assert.assertEquals("a string", StringUtility.removePunctuation("a? string"));
        Assert.assertEquals("a\ttabbed\tstring", StringUtility.removePunctuation("a!\ttabbed*|\t#string."));
        Assert.assertEquals("a\nmultiline\nstring", StringUtility.removePunctuation("a\n{multiline}\nstring"));
        Assert.assertEquals("a\r\nmultiline\r\nstring", StringUtility.removePunctuation("a<\r\n=multiline\r\n~:''string"));
        Assert.assertEquals("this\n  is a   string\r\n   ", StringUtility.removePunctuation("?><this^%(\n  is #@a   stri-*ng\r\n   #%"));
        
        //no punctuation
        Assert.assertEquals("string", StringUtility.removePunctuation("string"));
        Assert.assertEquals("ABC23454645", StringUtility.removePunctuation("ABC23454645"));
        Assert.assertEquals("1234567890", StringUtility.removePunctuation("1234567890"));
        Assert.assertEquals("", StringUtility.removePunctuation(""));
    }
    
    /**
     * JUnit test of removePunctuationSoft.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#removePunctuationSoft(String, List)
     */
    @Test
    public void testRemovePunctuationSoft() throws Exception {
        List<Character> save = Arrays.asList('!', '?', '#', ':');
        
        //save specific punctuation
        Assert.assertEquals("a? string", StringUtility.removePunctuationSoft("a? string", save));
        Assert.assertEquals("a!\ttabbed\t#string", StringUtility.removePunctuationSoft("a!\ttabbed*|\t#string.", save));
        Assert.assertEquals("a\nmultiline\nstring", StringUtility.removePunctuationSoft("a\n{multiline}\nstring", save));
        Assert.assertEquals("a\r\nmultiline\r\n:string", StringUtility.removePunctuationSoft("a<\r\n=multiline\r\n~:''string", save));
        Assert.assertEquals("?this\n  is #a   string\r\n   #", StringUtility.removePunctuationSoft("?><this^%(\n  is #@a   stri-*ng\r\n   #%", save));
        
        //save specific punctuation, no punctuation
        Assert.assertEquals("string", StringUtility.removePunctuationSoft("string", save));
        Assert.assertEquals("ABC23454645", StringUtility.removePunctuationSoft("ABC23454645", save));
        Assert.assertEquals("1234567890", StringUtility.removePunctuationSoft("1234567890", save));
        Assert.assertEquals("", StringUtility.removePunctuationSoft("", save));
    }
    
    /**
     * JUnit test of removeConsoleEscapeCharacters.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#removeConsoleEscapeCharacters(String)
     */
    @Test
    public void testRemoveConsoleEscapeCharacters() throws Exception {
        //effects
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                Console.ConsoleEffect.BOLD.apply("a") + " string"));
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                Console.ConsoleEffect.ITALIC.apply("a") + " string"));
        
        //colors
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.ConsoleEffect.YELLOW.apply("string")));
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.ConsoleEffect.PURPLE.apply("string")));
        
        //backgrounds
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.ConsoleEffect.RED_BG.apply("string")));
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.ConsoleEffect.BLUE_BG.apply("string")));
        
        //color and background
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.colorAndBackground("string", Console.ConsoleEffect.BLUE, Console.ConsoleEffect.GREEN)));
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.colorAndBackground("string", Console.ConsoleEffect.DARK_GREEN, Console.ConsoleEffect.CYAN_BG)));
        
        //8-bit colors
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.color8Bit("string", 156)));
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.colorAndBackground8Bit("string", 124, 211)));
        
        //24-bit colors
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.color24Bit("string", 164, 74, 215)));
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                "a " + Console.colorAndBackground24Bit("string", 97, 214, 74, 81, 81, 244)));
        
        //multiple effects
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                Console.stringEffects("a", Console.ConsoleEffect.BOLD, Console.ConsoleEffect.REVERSE) + ' ' + Console.stringEffects("string", Console.ConsoleEffect.ITALIC, Console.ConsoleEffect.FAINT, Console.ConsoleEffect.ORANGE)));
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                Console.stringEffectsWithColorAndBackground("a", Console.ConsoleEffect.BLACK, Console.ConsoleEffect.RED_BG, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.REVERSE) + ' ' + Console.stringEffects("string", Console.ConsoleEffect.ORANGE, Console.ConsoleEffect.DARK_GREEN_BG, Console.ConsoleEffect.ITALIC, Console.ConsoleEffect.FAINT)));
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                Console.stringEffectsWithColorAndBackground8Bit("a", 46, 154, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.REVERSE) + ' ' + Console.stringEffectsWithColorAndBackground8Bit("string", 99, 210, Console.ConsoleEffect.ITALIC, Console.ConsoleEffect.FAINT)));
        Assert.assertEquals("a string", StringUtility.removeConsoleEscapeCharacters(
                Console.stringEffectsWithColorAndBackground24Bit("a", 74, 39, 199, 167, 0, 255, Console.ConsoleEffect.BOLD, Console.ConsoleEffect.REVERSE) + ' ' + Console.stringEffectsWithColorAndBackground24Bit("string", 109, 49, 193, 88, 164, 164, Console.ConsoleEffect.ITALIC, Console.ConsoleEffect.FAINT)));
    }
    
    /**
     * JUnit test of tokenIsNum.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#tokenIsNum(String)
     */
    @Test
    public void testTokenIsNum() throws Exception {
        //numbers
        Assert.assertTrue(StringUtility.tokenIsNum("8"));
        Assert.assertTrue(StringUtility.tokenIsNum("86548913"));
        Assert.assertTrue(StringUtility.tokenIsNum("-86548913"));
        Assert.assertTrue(StringUtility.tokenIsNum("-86548913.641123"));
        Assert.assertTrue(StringUtility.tokenIsNum("4.597854e105"));
        Assert.assertTrue(StringUtility.tokenIsNum("-4.597854e105"));
        
        //non-numbers
        Assert.assertFalse(StringUtility.tokenIsNum("the number"));
        Assert.assertFalse(StringUtility.tokenIsNum("the number 8"));
        Assert.assertFalse(StringUtility.tokenIsNum("89ge32a16a878w4g5"));
        Assert.assertFalse(StringUtility.tokenIsNum("number"));
        Assert.assertFalse(StringUtility.tokenIsNum("number."));
    }
    
    /**
     * JUnit test of tokenIsOperator.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#tokenIsOperator(String)
     */
    @Test
    public void testTokenIsOperator() throws Exception {
        //operators
        Assert.assertTrue(StringUtility.tokenIsOperator("+"));
        Assert.assertTrue(StringUtility.tokenIsOperator("-"));
        Assert.assertTrue(StringUtility.tokenIsOperator("*"));
        Assert.assertTrue(StringUtility.tokenIsOperator("/"));
        Assert.assertTrue(StringUtility.tokenIsOperator("\\"));
        Assert.assertTrue(StringUtility.tokenIsOperator("%"));
        Assert.assertTrue(StringUtility.tokenIsOperator(">"));
        Assert.assertTrue(StringUtility.tokenIsOperator("<"));
        Assert.assertTrue(StringUtility.tokenIsOperator("!"));
        Assert.assertTrue(StringUtility.tokenIsOperator("=="));
        Assert.assertTrue(StringUtility.tokenIsOperator("!="));
        Assert.assertTrue(StringUtility.tokenIsOperator("<>"));
        Assert.assertTrue(StringUtility.tokenIsOperator(">="));
        Assert.assertTrue(StringUtility.tokenIsOperator("<="));
        
        //non-operators
        Assert.assertFalse(StringUtility.tokenIsOperator("a"));
        Assert.assertFalse(StringUtility.tokenIsOperator("r"));
        Assert.assertFalse(StringUtility.tokenIsOperator("D"));
        Assert.assertFalse(StringUtility.tokenIsOperator("Y"));
        Assert.assertFalse(StringUtility.tokenIsOperator("3"));
        Assert.assertFalse(StringUtility.tokenIsOperator("."));
        Assert.assertFalse(StringUtility.tokenIsOperator("@"));
        Assert.assertFalse(StringUtility.tokenIsOperator("~"));
    }
    
    /**
     * JUnit test of padLeft.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#padLeft(String, int, char)
     */
    @Test
    public void testPadLeft() throws Exception {
        //valid
        Assert.assertEquals("word", StringUtility.padLeft("word", -1));
        Assert.assertEquals("word", StringUtility.padLeft("word", 0));
        Assert.assertEquals("word", StringUtility.padLeft("word", 1));
        Assert.assertEquals(" word", StringUtility.padLeft("word", 5));
        Assert.assertEquals("     word", StringUtility.padLeft("word", 9));
        
        //specified padding
        Assert.assertEquals("word", StringUtility.padLeft("word", -1, '-'));
        Assert.assertEquals("word", StringUtility.padLeft("word", 0, '-'));
        Assert.assertEquals("word", StringUtility.padLeft("word", 1, '-'));
        Assert.assertEquals("-word", StringUtility.padLeft("word", 5, '-'));
        Assert.assertEquals("-----word", StringUtility.padLeft("word", 9, '-'));
    }
    
    /**
     * JUnit test of padRight.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#padRight(String, int, char)
     */
    @Test
    public void testPadRight() throws Exception {
        //valid
        Assert.assertEquals("word", StringUtility.padRight("word", -1));
        Assert.assertEquals("word", StringUtility.padRight("word", 0));
        Assert.assertEquals("word", StringUtility.padRight("word", 1));
        Assert.assertEquals("word ", StringUtility.padRight("word", 5));
        Assert.assertEquals("word     ", StringUtility.padRight("word", 9));
        
        //specified padding
        Assert.assertEquals("word", StringUtility.padRight("word", -1, '-'));
        Assert.assertEquals("word", StringUtility.padRight("word", 0, '-'));
        Assert.assertEquals("word", StringUtility.padRight("word", 1, '-'));
        Assert.assertEquals("word-", StringUtility.padRight("word", 5, '-'));
        Assert.assertEquals("word-----", StringUtility.padRight("word", 9, '-'));
    }
    
    /**
     * JUnit test of padZero.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#padZero(String, int)
     */
    @Test
    public void testPadZero() throws Exception {
        //valid
        Assert.assertEquals("100", StringUtility.padZero("100", -1));
        Assert.assertEquals("100", StringUtility.padZero("100", 0));
        Assert.assertEquals("100", StringUtility.padZero("100", 1));
        Assert.assertEquals("00100", StringUtility.padZero("100", 5));
        Assert.assertEquals("000000100", StringUtility.padZero("100", 9));
    }
    
    /**
     * JUnit test of spaces.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#spaces(int)
     */
    @Test
    public void testSpaces() throws Exception {
        //valid
        Assert.assertEquals("", StringUtility.spaces(-1));
        Assert.assertEquals("", StringUtility.spaces(0));
        Assert.assertEquals(" ", StringUtility.spaces(1));
        Assert.assertEquals("    ", StringUtility.spaces(4));
        Assert.assertEquals("         ", StringUtility.spaces(9));
        Assert.assertEquals("                    ", StringUtility.spaces(20));
    }
    
    /**
     * JUnit test of fillStringOfLength.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#fillStringOfLength(char, int)
     */
    @Test
    public void testFillStringOfLength() throws Exception {
        //valid
        Assert.assertEquals("", StringUtility.fillStringOfLength('*', -1));
        Assert.assertEquals("", StringUtility.fillStringOfLength('*', 0));
        Assert.assertEquals("*", StringUtility.fillStringOfLength('*', 1));
        Assert.assertEquals("****", StringUtility.fillStringOfLength('*', 4));
        Assert.assertEquals("*********", StringUtility.fillStringOfLength('*', 9));
        Assert.assertEquals("********************", StringUtility.fillStringOfLength('*', 20));
        
        Assert.assertEquals("", StringUtility.fillStringOfLength('|', -1));
        Assert.assertEquals("", StringUtility.fillStringOfLength('|', 0));
        Assert.assertEquals("|", StringUtility.fillStringOfLength('|', 1));
        Assert.assertEquals("||||", StringUtility.fillStringOfLength('|', 4));
        Assert.assertEquals("|||||||||", StringUtility.fillStringOfLength('|', 9));
        Assert.assertEquals("||||||||||||||||||||", StringUtility.fillStringOfLength('|', 20));
    }
    
    /**
     * JUnit test of repeatString.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#repeatString(String, int)
     */
    @Test
    public void testRepeatString() throws Exception {
        //valid
        Assert.assertEquals("aaaaa", StringUtility.repeatString("a", 5));
        Assert.assertEquals("an an an an an ", StringUtility.repeatString("an ", 5));
        Assert.assertEquals("a string a string a string a string a string a string a string a string a string a string ", StringUtility.repeatString("a string ", 10));
        
        //edge case
        Assert.assertEquals("", StringUtility.repeatString("a string", 0));
        Assert.assertEquals("", StringUtility.repeatString("a string", -1));
    }
    
    /**
     * JUnit test of centerText.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#centerText(String, int)
     */
    @Test
    public void testCenterText() throws Exception {
        String centered;
        
        //normal cases
        
        centered = StringUtility.centerText("Test", 6);
        Assert.assertEquals(" Test ", centered);
        
        centered = StringUtility.centerText("Test", 10);
        Assert.assertEquals("   Test   ", centered);
        
        centered = StringUtility.centerText("Test", 11);
        Assert.assertEquals("   Test   ", centered);
        
        centered = StringUtility.centerText("Test", 12);
        Assert.assertEquals("    Test    ", centered);
        
        centered = StringUtility.centerText("Test", 40);
        Assert.assertEquals("                  Test                  ", centered);
        
        //edge cases
        
        centered = StringUtility.centerText("Test", -1);
        Assert.assertEquals("Test", centered);
        
        centered = StringUtility.centerText("Test", 0);
        Assert.assertEquals("Test", centered);
        
        centered = StringUtility.centerText("Test", 4);
        Assert.assertEquals("Test", centered);
        
        centered = StringUtility.centerText("Test", 5);
        Assert.assertEquals("Test", centered);
    }
    
    /**
     * JUnit test of wrapText.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#wrapText(String, int, boolean, int)
     */
    @Test
    public void testWrapText() throws Exception {
        String text1 = "    This is an exampletest string, let's format it.";
        String text2 = "    12. This is an exampletest string, let's format it.";
        String text3 = "    * This is an exampletest string, let's format it.";
        String text4 = "    Thisisanexampleteststring,let'sformatit.";
        List<String> wrapped;
        
        //basic
        
        wrapped = StringUtility.wrapText(text1, 20);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    This is an examp", wrapped.get(0));
        Assert.assertEquals("letest string, let's", wrapped.get(1));
        Assert.assertEquals(" format it.         ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text2, 20);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    12. This is an e", wrapped.get(0));
        Assert.assertEquals("xampletest string, l", wrapped.get(1));
        Assert.assertEquals("et's format it.     ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text3, 20);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    * This is an exa", wrapped.get(0));
        Assert.assertEquals("mpletest string, let", wrapped.get(1));
        Assert.assertEquals("'s format it.       ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text4, 20);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    Thisisanexamplet", wrapped.get(0));
        Assert.assertEquals("eststring,let'sforma", wrapped.get(1));
        Assert.assertEquals("tit.                ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text1, 60);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    This is an exampletest string, let's format it.         ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text2, 60);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    12. This is an exampletest string, let's format it.     ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text3, 60);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    * This is an exampletest string, let's format it.       ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text4, 60);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    Thisisanexampleteststring,let'sformatit.                ", wrapped.get(0));
        
        //clean off
        
        wrapped = StringUtility.wrapText(text1, 20, false);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    This is an examp", wrapped.get(0));
        Assert.assertEquals("letest string, let's", wrapped.get(1));
        Assert.assertEquals(" format it.         ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text2, 20, false);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    12. This is an e", wrapped.get(0));
        Assert.assertEquals("xampletest string, l", wrapped.get(1));
        Assert.assertEquals("et's format it.     ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text3, 20, false);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    * This is an exa", wrapped.get(0));
        Assert.assertEquals("mpletest string, let", wrapped.get(1));
        Assert.assertEquals("'s format it.       ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text4, 20, false);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    Thisisanexamplet", wrapped.get(0));
        Assert.assertEquals("eststring,let'sforma", wrapped.get(1));
        Assert.assertEquals("tit.                ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text1, 60, false);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    This is an exampletest string, let's format it.         ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text2, 60, false);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    12. This is an exampletest string, let's format it.     ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text3, 60, false);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    * This is an exampletest string, let's format it.       ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text4, 60, false);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    Thisisanexampleteststring,let'sformatit.                ", wrapped.get(0));
        
        //clean on
        
        wrapped = StringUtility.wrapText(text1, 20, true);
        Assert.assertEquals(4, wrapped.size());
        Assert.assertEquals("    This is an      ", wrapped.get(0));
        Assert.assertEquals("    exampletest     ", wrapped.get(1));
        Assert.assertEquals("    string, let's   ", wrapped.get(2));
        Assert.assertEquals("    format it.      ", wrapped.get(3));
        
        wrapped = StringUtility.wrapText(text2, 20, true);
        Assert.assertEquals(5, wrapped.size());
        Assert.assertEquals("    12. This is an  ", wrapped.get(0));
        Assert.assertEquals("        exampletest ", wrapped.get(1));
        Assert.assertEquals("        string,     ", wrapped.get(2));
        Assert.assertEquals("        let's format", wrapped.get(3));
        Assert.assertEquals("        it.         ", wrapped.get(4));
        
        wrapped = StringUtility.wrapText(text3, 20, true);
        Assert.assertEquals(4, wrapped.size());
        Assert.assertEquals("    * This is an    ", wrapped.get(0));
        Assert.assertEquals("      exampletest   ", wrapped.get(1));
        Assert.assertEquals("      string, let's ", wrapped.get(2));
        Assert.assertEquals("      format it.    ", wrapped.get(3));
        
        wrapped = StringUtility.wrapText(text4, 20, true);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    Thisisanexample-", wrapped.get(0));
        Assert.assertEquals("    teststring,let'-", wrapped.get(1));
        Assert.assertEquals("    sformatit.      ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text1, 60, true);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    This is an exampletest string, let's format it.         ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text2, 60, true);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    12. This is an exampletest string, let's format it.     ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text3, 60, true);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    * This is an exampletest string, let's format it.       ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text4, 60, true);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    Thisisanexampleteststring,let'sformatit.                ", wrapped.get(0));
        
        //clean off, break indent off
        
        wrapped = StringUtility.wrapText(text1, 20, false, 0);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    This is an examp", wrapped.get(0));
        Assert.assertEquals("letest string, let's", wrapped.get(1));
        Assert.assertEquals(" format it.         ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text2, 20, false, 0);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    12. This is an e", wrapped.get(0));
        Assert.assertEquals("xampletest string, l", wrapped.get(1));
        Assert.assertEquals("et's format it.     ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text3, 20, false, 0);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    * This is an exa", wrapped.get(0));
        Assert.assertEquals("mpletest string, let", wrapped.get(1));
        Assert.assertEquals("'s format it.       ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text4, 20, false, 0);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    Thisisanexamplet", wrapped.get(0));
        Assert.assertEquals("eststring,let'sforma", wrapped.get(1));
        Assert.assertEquals("tit.                ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text1, 60, false, 0);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    This is an exampletest string, let's format it.         ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text2, 60, false, 0);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    12. This is an exampletest string, let's format it.     ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text3, 60, false, 0);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    * This is an exampletest string, let's format it.       ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text4, 60, false, 0);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    Thisisanexampleteststring,let'sformatit.                ", wrapped.get(0));
        
        //clean on, break indent off
        
        wrapped = StringUtility.wrapText(text1, 20, true, 0);
        Assert.assertEquals(4, wrapped.size());
        Assert.assertEquals("    This is an      ", wrapped.get(0));
        Assert.assertEquals("    exampletest     ", wrapped.get(1));
        Assert.assertEquals("    string, let's   ", wrapped.get(2));
        Assert.assertEquals("    format it.      ", wrapped.get(3));
        
        wrapped = StringUtility.wrapText(text2, 20, true, 0);
        Assert.assertEquals(5, wrapped.size());
        Assert.assertEquals("    12. This is an  ", wrapped.get(0));
        Assert.assertEquals("        exampletest ", wrapped.get(1));
        Assert.assertEquals("        string,     ", wrapped.get(2));
        Assert.assertEquals("        let's format", wrapped.get(3));
        Assert.assertEquals("        it.         ", wrapped.get(4));
        
        wrapped = StringUtility.wrapText(text3, 20, true, 0);
        Assert.assertEquals(4, wrapped.size());
        Assert.assertEquals("    * This is an    ", wrapped.get(0));
        Assert.assertEquals("      exampletest   ", wrapped.get(1));
        Assert.assertEquals("      string, let's ", wrapped.get(2));
        Assert.assertEquals("      format it.    ", wrapped.get(3));
        
        wrapped = StringUtility.wrapText(text4, 20, true, 0);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    Thisisanexample-", wrapped.get(0));
        Assert.assertEquals("    teststring,let'-", wrapped.get(1));
        Assert.assertEquals("    sformatit.      ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text1, 60, true, 0);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    This is an exampletest string, let's format it.         ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text2, 60, true, 0);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    12. This is an exampletest string, let's format it.     ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text3, 60, true, 0);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    * This is an exampletest string, let's format it.       ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text4, 60, true, 0);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    Thisisanexampleteststring,let'sformatit.                ", wrapped.get(0));
        
        //clean off, break indent on
        
        wrapped = StringUtility.wrapText(text1, 20, false, 2);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    This is an examp", wrapped.get(0));
        Assert.assertEquals("  letest string, let", wrapped.get(1));
        Assert.assertEquals("  's format it.     ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text2, 20, false, 2);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    12. This is an e", wrapped.get(0));
        Assert.assertEquals("xampletest string, l", wrapped.get(1));
        Assert.assertEquals("et's format it.     ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text3, 20, false, 2);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    * This is an exa", wrapped.get(0));
        Assert.assertEquals("  mpletest string, l", wrapped.get(1));
        Assert.assertEquals("  et's format it.   ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text4, 20, false, 2);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    Thisisanexamplet", wrapped.get(0));
        Assert.assertEquals("  eststring,let'sfor", wrapped.get(1));
        Assert.assertEquals("  matit.            ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text1, 60, false, 2);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    This is an exampletest string, let's format it.         ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text2, 60, false, 2);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    12. This is an exampletest string, let's format it.     ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text3, 60, false, 2);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    * This is an exampletest string, let's format it.       ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text4, 60, false, 2);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    Thisisanexampleteststring,let'sformatit.                ", wrapped.get(0));
        
        //clean on, break indent on
        
        wrapped = StringUtility.wrapText(text1, 20, true, 2);
        Assert.assertEquals(4, wrapped.size());
        Assert.assertEquals("    This is an      ", wrapped.get(0));
        Assert.assertEquals("      exampletest   ", wrapped.get(1));
        Assert.assertEquals("      string, let's ", wrapped.get(2));
        Assert.assertEquals("      format it.    ", wrapped.get(3));
        
        wrapped = StringUtility.wrapText(text2, 20, true, 2);
        Assert.assertEquals(5, wrapped.size());
        Assert.assertEquals("    12. This is an  ", wrapped.get(0));
        Assert.assertEquals("        exampletest ", wrapped.get(1));
        Assert.assertEquals("        string,     ", wrapped.get(2));
        Assert.assertEquals("        let's format", wrapped.get(3));
        Assert.assertEquals("        it.         ", wrapped.get(4));
        
        wrapped = StringUtility.wrapText(text3, 20, true, 2);
        Assert.assertEquals(5, wrapped.size());
        Assert.assertEquals("    * This is an    ", wrapped.get(0));
        Assert.assertEquals("        exampletest ", wrapped.get(1));
        Assert.assertEquals("        string,     ", wrapped.get(2));
        Assert.assertEquals("        let's format", wrapped.get(3));
        Assert.assertEquals("        it.         ", wrapped.get(4));
        
        wrapped = StringUtility.wrapText(text4, 20, true, 2);
        Assert.assertEquals(3, wrapped.size());
        Assert.assertEquals("    Thisisanexample-", wrapped.get(0));
        Assert.assertEquals("      teststring,le-", wrapped.get(1));
        Assert.assertEquals("      t'sformatit.  ", wrapped.get(2));
        
        wrapped = StringUtility.wrapText(text1, 60, true, 2);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    This is an exampletest string, let's format it.         ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text2, 60, true, 2);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    12. This is an exampletest string, let's format it.     ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text3, 60, true, 2);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    * This is an exampletest string, let's format it.       ", wrapped.get(0));
        
        wrapped = StringUtility.wrapText(text4, 60, true, 2);
        Assert.assertEquals(1, wrapped.size());
        Assert.assertEquals("    Thisisanexampleteststring,let'sformatit.                ", wrapped.get(0));
    }
    
    /**
     * JUnit test of boxText.
     *
     * @throws Exception When there is an exception.
     * @see StringUtility#boxText(List, int, boolean, int, int, StringUtility.BoxType)
     */
    @Test
    public void testBoxText() throws Exception {
        String s = "\"The quick brown fox jumps over the lazy dog\"\n" +
                "is an English-language pangram - \n" +
                "a sentence that contains all of the letters of the alphabet.";
        List<String> text = StringUtility.splitLines(s);
        List<String> expected = new ArrayList<>();
        List<String> actual;
        
        //standard
        actual = StringUtility.boxText(text, 40);
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("dog\"                                    ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the     ");
        expected.add("letters of the alphabet.                ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off
        actual = StringUtility.boxText(text, 40, false);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add(" dog\"                                   ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the lett");
        expected.add("ers of the alphabet.                    ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on
        actual = StringUtility.boxText(text, 40, true);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("dog\"                                    ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the     ");
        expected.add("letters of the alphabet.                ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent off
        actual = StringUtility.boxText(text, 40, false, 0);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add(" dog\"                                   ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the lett");
        expected.add("ers of the alphabet.                    ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent off
        actual = StringUtility.boxText(text, 40, true, 0);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("dog\"                                    ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the     ");
        expected.add("letters of the alphabet.                ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent on
        actual = StringUtility.boxText(text, 40, false, 5);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("      dog\"                              ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the lett");
        expected.add("     ers of the alphabet.               ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent on
        actual = StringUtility.boxText(text, 40, true, 5);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("     dog\"                               ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the     ");
        expected.add("     letters of the alphabet.           ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent off, border off
        actual = StringUtility.boxText(text, 40, false, 0, 0);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add(" dog\"                                   ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the lett");
        expected.add("ers of the alphabet.                    ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent off, border on
        actual = StringUtility.boxText(text, 40, false, 0, 2);
        expected.clear();
        expected.add("  \"The quick brown fox jumps over the   ");
        expected.add("  lazy dog\"                             ");
        expected.add("  is an English-language pangram -      ");
        expected.add("  a sentence that contains all of the   ");
        expected.add("  letters of the alphabet.              ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent on, border off
        actual = StringUtility.boxText(text, 40, false, 5, 0);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("      dog\"                              ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the lett");
        expected.add("     ers of the alphabet.               ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent on, border on
        actual = StringUtility.boxText(text, 40, false, 5, 2);
        expected.clear();
        expected.add("  \"The quick brown fox jumps over the   ");
        expected.add("       lazy dog\"                        ");
        expected.add("  is an English-language pangram -      ");
        expected.add("  a sentence that contains all of the   ");
        expected.add("       letters of the alphabet.         ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent off, border off
        actual = StringUtility.boxText(text, 40, true, 0, 0);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("dog\"                                    ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the     ");
        expected.add("letters of the alphabet.                ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent off, border on
        actual = StringUtility.boxText(text, 40, true, 0, 2);
        expected.clear();
        expected.add("  \"The quick brown fox jumps over the   ");
        expected.add("  lazy dog\"                             ");
        expected.add("  is an English-language pangram -      ");
        expected.add("  a sentence that contains all of the   ");
        expected.add("  letters of the alphabet.              ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent on, border off
        actual = StringUtility.boxText(text, 40, true, 5, 0);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("     dog\"                               ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the     ");
        expected.add("     letters of the alphabet.           ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent on, border on
        actual = StringUtility.boxText(text, 40, true, 5, 2);
        expected.clear();
        expected.add("  \"The quick brown fox jumps over the   ");
        expected.add("       lazy dog\"                        ");
        expected.add("  is an English-language pangram -      ");
        expected.add("  a sentence that contains all of the   ");
        expected.add("       letters of the alphabet.         ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent off, border off, box type none
        actual = StringUtility.boxText(text, 40, false, 0, 0, StringUtility.BoxType.NO_BOX);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add(" dog\"                                   ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the lett");
        expected.add("ers of the alphabet.                    ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent off, border off, box type single
        actual = StringUtility.boxText(text, 40, false, 0, 0, StringUtility.BoxType.BOX);
        expected.clear();
        expected.add("┌────────────────────────────────────────┐");
        expected.add("│\"The quick brown fox jumps over the lazy│");
        expected.add("│ dog\"                                   │");
        expected.add("│is an English-language pangram -        │");
        expected.add("│a sentence that contains all of the lett│");
        expected.add("│ers of the alphabet.                    │");
        expected.add("└────────────────────────────────────────┘");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent off, border off, box type double
        actual = StringUtility.boxText(text, 40, false, 0, 0, StringUtility.BoxType.DOUBLE_BOX);
        expected.clear();
        expected.add("╔════════════════════════════════════════╗");
        expected.add("║\"The quick brown fox jumps over the lazy║");
        expected.add("║ dog\"                                   ║");
        expected.add("║is an English-language pangram -        ║");
        expected.add("║a sentence that contains all of the lett║");
        expected.add("║ers of the alphabet.                    ║");
        expected.add("╚════════════════════════════════════════╝");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent off, border on, box type none
        actual = StringUtility.boxText(text, 40, false, 0, 2, StringUtility.BoxType.NO_BOX);
        expected.clear();
        expected.add("  \"The quick brown fox jumps over the   ");
        expected.add("  lazy dog\"                             ");
        expected.add("  is an English-language pangram -      ");
        expected.add("  a sentence that contains all of the   ");
        expected.add("  letters of the alphabet.              ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent off, border on, box type single
        actual = StringUtility.boxText(text, 40, false, 0, 2, StringUtility.BoxType.BOX);
        expected.clear();
        expected.add("┌────────────────────────────────────────┐");
        expected.add("│  \"The quick brown fox jumps over the   │");
        expected.add("│  lazy dog\"                             │");
        expected.add("│  is an English-language pangram -      │");
        expected.add("│  a sentence that contains all of the   │");
        expected.add("│  letters of the alphabet.              │");
        expected.add("└────────────────────────────────────────┘");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent off, border on, box type double
        actual = StringUtility.boxText(text, 40, false, 0, 2, StringUtility.BoxType.DOUBLE_BOX);
        expected.clear();
        expected.add("╔════════════════════════════════════════╗");
        expected.add("║  \"The quick brown fox jumps over the   ║");
        expected.add("║  lazy dog\"                             ║");
        expected.add("║  is an English-language pangram -      ║");
        expected.add("║  a sentence that contains all of the   ║");
        expected.add("║  letters of the alphabet.              ║");
        expected.add("╚════════════════════════════════════════╝");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent on, border off, box type none
        actual = StringUtility.boxText(text, 40, false, 5, 0, StringUtility.BoxType.NO_BOX);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("      dog\"                              ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the lett");
        expected.add("     ers of the alphabet.               ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent on, border off, box type single
        actual = StringUtility.boxText(text, 40, false, 5, 0, StringUtility.BoxType.BOX);
        expected.clear();
        expected.add("┌────────────────────────────────────────┐");
        expected.add("│\"The quick brown fox jumps over the lazy│");
        expected.add("│      dog\"                              │");
        expected.add("│is an English-language pangram -        │");
        expected.add("│a sentence that contains all of the lett│");
        expected.add("│     ers of the alphabet.               │");
        expected.add("└────────────────────────────────────────┘");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent on, border off, box type double
        actual = StringUtility.boxText(text, 40, false, 5, 0, StringUtility.BoxType.DOUBLE_BOX);
        expected.clear();
        expected.add("╔════════════════════════════════════════╗");
        expected.add("║\"The quick brown fox jumps over the lazy║");
        expected.add("║      dog\"                              ║");
        expected.add("║is an English-language pangram -        ║");
        expected.add("║a sentence that contains all of the lett║");
        expected.add("║     ers of the alphabet.               ║");
        expected.add("╚════════════════════════════════════════╝");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent on, border on, box type none
        actual = StringUtility.boxText(text, 40, false, 5, 2, StringUtility.BoxType.NO_BOX);
        expected.clear();
        expected.add("  \"The quick brown fox jumps over the   ");
        expected.add("       lazy dog\"                        ");
        expected.add("  is an English-language pangram -      ");
        expected.add("  a sentence that contains all of the   ");
        expected.add("       letters of the alphabet.         ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent on, border on, box type single
        actual = StringUtility.boxText(text, 40, false, 5, 2, StringUtility.BoxType.BOX);
        expected.clear();
        expected.add("┌────────────────────────────────────────┐");
        expected.add("│  \"The quick brown fox jumps over the   │");
        expected.add("│       lazy dog\"                        │");
        expected.add("│  is an English-language pangram -      │");
        expected.add("│  a sentence that contains all of the   │");
        expected.add("│       letters of the alphabet.         │");
        expected.add("└────────────────────────────────────────┘");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean off, break indent on, border on, box type double
        actual = StringUtility.boxText(text, 40, false, 5, 2, StringUtility.BoxType.DOUBLE_BOX);
        expected.clear();
        expected.add("╔════════════════════════════════════════╗");
        expected.add("║  \"The quick brown fox jumps over the   ║");
        expected.add("║       lazy dog\"                        ║");
        expected.add("║  is an English-language pangram -      ║");
        expected.add("║  a sentence that contains all of the   ║");
        expected.add("║       letters of the alphabet.         ║");
        expected.add("╚════════════════════════════════════════╝");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent off, border off, box type none
        actual = StringUtility.boxText(text, 40, true, 0, 0, StringUtility.BoxType.NO_BOX);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("dog\"                                    ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the     ");
        expected.add("letters of the alphabet.                ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent off, border off, box type single
        actual = StringUtility.boxText(text, 40, true, 0, 0, StringUtility.BoxType.BOX);
        expected.clear();
        expected.add("┌────────────────────────────────────────┐");
        expected.add("│\"The quick brown fox jumps over the lazy│");
        expected.add("│dog\"                                    │");
        expected.add("│is an English-language pangram -        │");
        expected.add("│a sentence that contains all of the     │");
        expected.add("│letters of the alphabet.                │");
        expected.add("└────────────────────────────────────────┘");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent off, border off, box type double
        actual = StringUtility.boxText(text, 40, true, 0, 0, StringUtility.BoxType.DOUBLE_BOX);
        expected.clear();
        expected.add("╔════════════════════════════════════════╗");
        expected.add("║\"The quick brown fox jumps over the lazy║");
        expected.add("║dog\"                                    ║");
        expected.add("║is an English-language pangram -        ║");
        expected.add("║a sentence that contains all of the     ║");
        expected.add("║letters of the alphabet.                ║");
        expected.add("╚════════════════════════════════════════╝");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent off, border on, box type none
        actual = StringUtility.boxText(text, 40, true, 0, 2, StringUtility.BoxType.NO_BOX);
        expected.clear();
        expected.add("  \"The quick brown fox jumps over the   ");
        expected.add("  lazy dog\"                             ");
        expected.add("  is an English-language pangram -      ");
        expected.add("  a sentence that contains all of the   ");
        expected.add("  letters of the alphabet.              ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent off, border on, box type single
        actual = StringUtility.boxText(text, 40, true, 0, 2, StringUtility.BoxType.BOX);
        expected.clear();
        expected.add("┌────────────────────────────────────────┐");
        expected.add("│  \"The quick brown fox jumps over the   │");
        expected.add("│  lazy dog\"                             │");
        expected.add("│  is an English-language pangram -      │");
        expected.add("│  a sentence that contains all of the   │");
        expected.add("│  letters of the alphabet.              │");
        expected.add("└────────────────────────────────────────┘");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent off, border on, box type double
        actual = StringUtility.boxText(text, 40, true, 0, 2, StringUtility.BoxType.DOUBLE_BOX);
        expected.clear();
        expected.add("╔════════════════════════════════════════╗");
        expected.add("║  \"The quick brown fox jumps over the   ║");
        expected.add("║  lazy dog\"                             ║");
        expected.add("║  is an English-language pangram -      ║");
        expected.add("║  a sentence that contains all of the   ║");
        expected.add("║  letters of the alphabet.              ║");
        expected.add("╚════════════════════════════════════════╝");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent on, border off, box type none
        actual = StringUtility.boxText(text, 40, true, 5, 0, StringUtility.BoxType.NO_BOX);
        expected.clear();
        expected.add("\"The quick brown fox jumps over the lazy");
        expected.add("     dog\"                               ");
        expected.add("is an English-language pangram -        ");
        expected.add("a sentence that contains all of the     ");
        expected.add("     letters of the alphabet.           ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent on, border off, box type single
        actual = StringUtility.boxText(text, 40, true, 5, 0, StringUtility.BoxType.BOX);
        expected.clear();
        expected.add("┌────────────────────────────────────────┐");
        expected.add("│\"The quick brown fox jumps over the lazy│");
        expected.add("│     dog\"                               │");
        expected.add("│is an English-language pangram -        │");
        expected.add("│a sentence that contains all of the     │");
        expected.add("│     letters of the alphabet.           │");
        expected.add("└────────────────────────────────────────┘");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent on, border off, box type double
        actual = StringUtility.boxText(text, 40, true, 5, 0, StringUtility.BoxType.DOUBLE_BOX);
        expected.clear();
        expected.add("╔════════════════════════════════════════╗");
        expected.add("║\"The quick brown fox jumps over the lazy║");
        expected.add("║     dog\"                               ║");
        expected.add("║is an English-language pangram -        ║");
        expected.add("║a sentence that contains all of the     ║");
        expected.add("║     letters of the alphabet.           ║");
        expected.add("╚════════════════════════════════════════╝");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent on, border on, box type none
        actual = StringUtility.boxText(text, 40, true, 5, 2, StringUtility.BoxType.NO_BOX);
        expected.clear();
        expected.add("  \"The quick brown fox jumps over the   ");
        expected.add("       lazy dog\"                        ");
        expected.add("  is an English-language pangram -      ");
        expected.add("  a sentence that contains all of the   ");
        expected.add("       letters of the alphabet.         ");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent on, border on, box type single
        actual = StringUtility.boxText(text, 40, true, 5, 2, StringUtility.BoxType.BOX);
        expected.clear();
        expected.add("┌────────────────────────────────────────┐");
        expected.add("│  \"The quick brown fox jumps over the   │");
        expected.add("│       lazy dog\"                        │");
        expected.add("│  is an English-language pangram -      │");
        expected.add("│  a sentence that contains all of the   │");
        expected.add("│       letters of the alphabet.         │");
        expected.add("└────────────────────────────────────────┘");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        
        //clean on, break indent on, border on, box type double
        actual = StringUtility.boxText(text, 40, true, 5, 2, StringUtility.BoxType.DOUBLE_BOX);
        expected.clear();
        expected.add("╔════════════════════════════════════════╗");
        expected.add("║  \"The quick brown fox jumps over the   ║");
        expected.add("║       lazy dog\"                        ║");
        expected.add("║  is an English-language pangram -      ║");
        expected.add("║  a sentence that contains all of the   ║");
        expected.add("║       letters of the alphabet.         ║");
        expected.add("╚════════════════════════════════════════╝");
        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }
    
}
