/*
 * File:    StringComparisonUtilityTest.java
 * Package: string
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
 * JUnit test of StringComparisonUtility.
 *
 * @see StringComparisonUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({StringComparisonUtility.class})
public class StringComparisonUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(StringComparisonUtilityTest.class);
    
    
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
     * JUnit test of stringCompare.
     *
     * @throws Exception When there is an exception.
     * @see StringComparisonUtility#VARCHAR
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals('¿', StringComparisonUtility.VARCHAR);
        Assert.assertEquals("¿", StringComparisonUtility.VARCHAR_STRING);
    }
    
    /**
     * JUnit test of stringCompare.
     *
     * @throws Exception When there is an exception.
     * @see StringComparisonUtility#stringCompare(String, String, List, List, boolean, boolean)
     * @see StringComparisonUtility#stringCompare(String, String, List, List, boolean)
     * @see StringComparisonUtility#stringCompare(String, String, List, List)
     * @see StringComparisonUtility#stringCompare(String, String, List)
     * @see StringComparisonUtility#stringCompare(String, String, boolean, boolean)
     * @see StringComparisonUtility#stringCompare(String, String, boolean)
     * @see StringComparisonUtility#stringCompare(String, String)
     */
    @SuppressWarnings("PointlessArithmeticExpression")
    @Test
    public void testStringCompare() throws Exception {
        //general
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something", "Something"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("something", "something"), 0.0000001);
        Assert.assertEquals((9 - 3) / 9.0, StringComparisonUtility.stringCompare("Something", "Nothing"), 0.0000001);
        Assert.assertEquals((9 - 3) / 9.0, StringComparisonUtility.stringCompare("Nothing", "Something"), 0.0000001);
        
        //case
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Something", "something"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("something", "Something"), 0.0000001);
        Assert.assertEquals((9 - 9) / 9.0, StringComparisonUtility.stringCompare("SOMETHING", "something"), 0.0000001);
        Assert.assertEquals((9 - 9) / 9.0, StringComparisonUtility.stringCompare("something", "SOMETHING"), 0.0000001);
        Assert.assertEquals((9 - 5) / 9.0, StringComparisonUtility.stringCompare("Something", "sOmEtHiNg"), 0.0000001);
        Assert.assertEquals((9 - 4) / 9.0, StringComparisonUtility.stringCompare("SoMeThInG", "Something"), 0.0000001);
        Assert.assertEquals((9 - 9) / 9.0, StringComparisonUtility.stringCompare("SoMeThInG", "sOmEtHiNg"), 0.0000001);
        
        //removal
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Something", "omething"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Something", "Somethin"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Something", "Somehing"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("omething", "Something"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Somethin", "Something"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Somehing", "Something"), 0.0000001);
        
        //replacement
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Something", "Samething"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Something", "Domething"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Something", "Somethint"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Samething", "Something"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Domething", "Something"), 0.0000001);
        Assert.assertEquals((9 - 1) / 9.0, StringComparisonUtility.stringCompare("Somethint", "Something"), 0.0000001);
        
        //insertion
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Something", "aSomething"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Something", "Somethinga"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Something", "Sometahing"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("aSomething", "Something"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Somethinga", "Something"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Sometahing", "Something"), 0.0000001);
        
        //punctuation
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something?", "Something?"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("?Something", "?Something"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Some?thing", "Some?thing"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("-.Some?thing+", "-.Some?thing+"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Something", "Something?"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Something", "?Something"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Something", "Some?thing"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Something?", "Something"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("?Something", "Something"), 0.0000001);
        Assert.assertEquals((10 - 1) / 10.0, StringComparisonUtility.stringCompare("Some?thing", "Something"), 0.0000001);
        Assert.assertEquals((13 - 4) / 13.0, StringComparisonUtility.stringCompare("Something", "-.Some?thing+"), 0.0000001);
        Assert.assertEquals((13 - 4) / 13.0, StringComparisonUtility.stringCompare("-.Some?thing+", "Something"), 0.0000001);
        
        //empty
        Assert.assertEquals(0.0, StringComparisonUtility.stringCompare("Something", ""), 0.0000001);
        Assert.assertEquals(0.0, StringComparisonUtility.stringCompare("", "Something"), 0.0000001);
        
        //ignore case
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something", "Something", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("something", "something", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something", "something", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("something", "Something", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("SOMETHING", "something", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("something", "SOMETHING", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something", "sOmEtHiNg", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("SoMeThInG", "Something", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("SoMeThInG", "sOmEtHiNg", true), 0.0000001);
        
        //ignore punctuation
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something?", "Something?", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("?Something", "?Something", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Some?thing", "Some?thing", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("-.Some?thing+", "-.Some?thing+", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something", "Something?", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something", "?Something", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something", "Some?thing", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something?", "Something", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("?Something", "Something", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Some?thing", "Something", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something", "-.Some?thing+", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("-.Some?thing+", "Something", false, true), 0.0000001);
        
        //ignore case and punctuation
        Assert.assertEquals((13 - 4) / 13.0, StringComparisonUtility.stringCompare("-.SoMe?ThInG+", "Something", true, false), 0.0000001);
        Assert.assertEquals((13 - 4) / 13.0, StringComparisonUtility.stringCompare("Something", "-.SoMe?ThInG+", true, false), 0.0000001);
        Assert.assertEquals((9 - 4) / 9.0, StringComparisonUtility.stringCompare("-.SoMe?ThInG+", "Something", false, true), 0.0000001);
        Assert.assertEquals((9 - 4) / 9.0, StringComparisonUtility.stringCompare("Something", "-.SoMe?ThInG+", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("-.SoMe?ThInG+", "Something", true, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something", "-.SoMe?ThInG+", true, true), 0.0000001);
        
        //longer cases
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like that", "Something else like that"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("something else like that", "something else like that"), 0.0000001);
        Assert.assertEquals((24 - 3) / 24.0, StringComparisonUtility.stringCompare("Something else like that", "Nothing else like that"), 0.0000001);
        Assert.assertEquals((24 - 3) / 24.0, StringComparisonUtility.stringCompare("Nothing else like that", "Something else like that"), 0.0000001);
        Assert.assertEquals((24 - 3) / 24.0, StringComparisonUtility.stringCompare("Something else like that", "Something Else Like That"), 0.0000001);
        Assert.assertEquals((24 - 3) / 24.0, StringComparisonUtility.stringCompare("Something Else Like That", "Something else like that"), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("Something else like that", "Somthing ls lik that"), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("Somthing ls lik that", "Something else like that"), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("Something else like that", "Somathing alsa lika that"), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("Somathing alsa lika that", "Something else like that"), 0.0000001);
        Assert.assertEquals((27 - 3) / 27.0, StringComparisonUtility.stringCompare("Something else like that", "Something aelsea likea that"), 0.0000001);
        Assert.assertEquals((27 - 3) / 27.0, StringComparisonUtility.stringCompare("Something aelsea likea that", "Something else like that"), 0.0000001);
        Assert.assertEquals((25 - 1) / 25.0, StringComparisonUtility.stringCompare("Something else like that", "Something else like that?"), 0.0000001);
        Assert.assertEquals((25 - 1) / 25.0, StringComparisonUtility.stringCompare("Something else like that?", "Something else like that"), 0.0000001);
        Assert.assertEquals((29 - 5) / 29.0, StringComparisonUtility.stringCompare("Something else like that", "Something, else... like that?"), 0.0000001);
        Assert.assertEquals((29 - 5) / 29.0, StringComparisonUtility.stringCompare("Something, else... like that?", "Something else like that"), 0.0000001);
        Assert.assertEquals(0.0, StringComparisonUtility.stringCompare("Something else like that", ""), 0.0000001);
        Assert.assertEquals(0.0, StringComparisonUtility.stringCompare("", "Something else like that"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like that", "Something Else Like That", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something Else Like That", "Something else like that", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like that", "Something else like that?", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like that?", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like that", "Something, else... like that?", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something, else... like that?", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals((28 - 4) / 28.0, StringComparisonUtility.stringCompare("-.SoMe?ThInG+ else like that", "Something else like that", true, false), 0.0000001);
        Assert.assertEquals((28 - 4) / 28.0, StringComparisonUtility.stringCompare("Something else like that", "-.SoMe?ThInG+ else like that", true, false), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("-.SoMe?ThInG+ else like that", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("Something else like that", "-.SoMe?ThInG+ else like that", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("-.SoMe?ThInG+ else like that", "Something else like that", true, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like that", "-.SoMe?ThInG+ else like that", true, true), 0.0000001);
        
        //variables
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like ¿", "Something else like this"), 0.0000001);
        Assert.assertEquals((24 - 1) / 24.0, StringComparisonUtility.stringCompare("something else like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals((24 - 1) / 24.0, StringComparisonUtility.stringCompare("Something else like ¿", "something else like that"), 0.0000001);
        Assert.assertEquals((22 - 3) / 22.0, StringComparisonUtility.stringCompare("Something else like ¿", "Nothing else like that"), 0.0000001);
        Assert.assertEquals((24 - 3) / 24.0, StringComparisonUtility.stringCompare("Nothing else like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals((24 - 2) / 24.0, StringComparisonUtility.stringCompare("Something else like ¿", "Something Else Like That"), 0.0000001);
        Assert.assertEquals((24 - 2) / 24.0, StringComparisonUtility.stringCompare("Something Else Like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals((21 - 4) / 21.0, StringComparisonUtility.stringCompare("Something else like ¿", "Somthing ls lik that"), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("Somthing ls lik ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("Something else like ¿", "Somathing alsa lika that"), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("Somathing alsa lika ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals((27 - 3) / 27.0, StringComparisonUtility.stringCompare("Something else like ¿", "Something aelsea likea that"), 0.0000001);
        Assert.assertEquals((24 - 3) / 24.0, StringComparisonUtility.stringCompare("Something aelsea likea ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like ¿", "Something else like that?"), 0.0000001);
        Assert.assertEquals((24 - 1) / 24.0, StringComparisonUtility.stringCompare("Something else like ¿?", "Something else like that"), 0.0000001);
        Assert.assertEquals((29 - 4) / 29.0, StringComparisonUtility.stringCompare("Something else like ¿", "Something, else... like that?"), 0.0000001);
        Assert.assertEquals((26 - 5) / 26.0, StringComparisonUtility.stringCompare("Something, else... like ¿?", "Something else like that"), 0.0000001);
        Assert.assertEquals(0.0, StringComparisonUtility.stringCompare("Something else like ¿", ""), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like ¿", "Something Else Like That", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something Else Like ¿", "Something else like that", true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like ¿", "Something else like that?", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like ¿?", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like ¿", "Something, else... like that?", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something, else... like ¿?", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals((25 - 4) / 25.0, StringComparisonUtility.stringCompare("-.SoMe?ThInG+ else like ¿", "Something else like that", true, false), 0.0000001);
        Assert.assertEquals((28 - 4) / 28.0, StringComparisonUtility.stringCompare("Something else like ¿", "-.SoMe?ThInG+ else like that", true, false), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("-.SoMe?ThInG+ else like ¿", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals((24 - 4) / 24.0, StringComparisonUtility.stringCompare("Something else like ¿", "-.SoMe?ThInG+ else like that", false, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("-.SoMe?ThInG+ else like ¿", "Something else like that", true, true), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("Something else like ¿", "-.SoMe?ThInG+ else like that", true, true), 0.0000001);
        
        //multiple variables
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("¿ else like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("¿ else like ¿", "Nothing else like this"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("¿ else ¿ ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("¿ else ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("¿thing else ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals((23 - 1) / 23.0, StringComparisonUtility.stringCompare("¿thing else ¿", "Somethin else like that"), 0.0000001);
        Assert.assertEquals((24 - 1) / 24.0, StringComparisonUtility.stringCompare("¿thin else ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals((25 - 1) / 25.0, StringComparisonUtility.stringCompare("¿thing else ¿", "Something elsea like that"), 0.0000001);
        Assert.assertEquals((24 - 1) / 24.0, StringComparisonUtility.stringCompare("¿thing elsea ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringCompare("¿thing else ¿", "thing else again"), 0.0000001);
        Assert.assertEquals((24 - 1) / 24.0, StringComparisonUtility.stringCompare("¿¿¿¿¿thing elsea ¿¿", "Something else like that"), 0.0000001);
    }
    
    /**
     * JUnit test of stringComparePhonetic.
     *
     * @throws Exception When there is an exception.
     * @see StringComparisonUtility#stringComparePhonetic(String, String)
     */
    @Test
    public void testStringComparePhonetic() throws Exception {
        //general
        Assert.assertEquals(1.0, StringComparisonUtility.stringComparePhonetic("Something", "Something"), 0.0000001);
        Assert.assertEquals(0.5, StringComparisonUtility.stringComparePhonetic("Something", "Some"), 0.0000001);
        Assert.assertEquals(1.0, StringComparisonUtility.stringComparePhonetic("Some", "Sum"), 0.0000001);
        Assert.assertEquals(0.75, StringComparisonUtility.stringComparePhonetic("Something", "Sumfink"), 0.0000001);
        
        //invalid
        Assert.assertEquals(0, StringComparisonUtility.stringComparePhonetic("Something", ""), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringComparePhonetic("", "Something"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringComparePhonetic("", ""), 0.0000001);
    }
    
    /**
     * JUnit test of stringEditDistance.
     *
     * @throws Exception When there is an exception.
     * @see StringComparisonUtility#stringEditDistance(String, String, List, List, boolean, boolean)
     * @see StringComparisonUtility#stringEditDistance(String, String, List, List, boolean)
     * @see StringComparisonUtility#stringEditDistance(String, String, List, List)
     * @see StringComparisonUtility#stringEditDistance(String, String, boolean, boolean)
     * @see StringComparisonUtility#stringEditDistance(String, String, boolean)
     * @see StringComparisonUtility#stringEditDistance(String, String)
     */
    @Test
    public void testStringEditDistance() throws Exception {
        //general
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something", "Something"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("something", "something"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Something", "Nothing"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Nothing", "Something"), 0.0000001);
        
        //case
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("something", "Something"), 0.0000001);
        Assert.assertEquals(9, StringComparisonUtility.stringEditDistance("SOMETHING", "something"), 0.0000001);
        Assert.assertEquals(9, StringComparisonUtility.stringEditDistance("something", "SOMETHING"), 0.0000001);
        Assert.assertEquals(5, StringComparisonUtility.stringEditDistance("Something", "sOmEtHiNg"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("SoMeThInG", "Something"), 0.0000001);
        Assert.assertEquals(9, StringComparisonUtility.stringEditDistance("SoMeThInG", "sOmEtHiNg"), 0.0000001);
        
        //removal
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "omething"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "Somethin"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "Somehing"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("omething", "Something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Somethin", "Something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Somehing", "Something"), 0.0000001);
        
        //replacement
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "Samething"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "Domething"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "Somethint"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Samething", "Something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Domething", "Something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Somethint", "Something"), 0.0000001);
        
        //insertion
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "aSomething"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "Somethinga"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "Sometahing"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("aSomething", "Something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Somethinga", "Something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Sometahing", "Something"), 0.0000001);
        
        //punctuation
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something?", "Something?"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("?Something", "?Something"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Some?thing", "Some?thing"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("-.Some?thing+", "-.Some?thing+"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "Something?"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "?Something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something", "Some?thing"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something?", "Something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("?Something", "Something"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Some?thing", "Something"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something", "-.Some?thing+"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("-.Some?thing+", "Something"), 0.0000001);
        
        //empty
        Assert.assertEquals(9, StringComparisonUtility.stringEditDistance("Something", ""), 0.0000001);
        Assert.assertEquals(9, StringComparisonUtility.stringEditDistance("", "Something"), 0.0000001);
        
        //ignore case
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something", "Something", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("something", "something", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something", "something", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("something", "Something", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("SOMETHING", "something", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("something", "SOMETHING", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something", "sOmEtHiNg", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("SoMeThInG", "Something", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("SoMeThInG", "sOmEtHiNg", true), 0.0000001);
        
        //ignore punctuation
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something?", "Something?", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("?Something", "?Something", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Some?thing", "Some?thing", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("-.Some?thing+", "-.Some?thing+", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something", "Something?", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something", "?Something", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something", "Some?thing", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something?", "Something", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("?Something", "Something", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Some?thing", "Something", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something", "-.Some?thing+", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("-.Some?thing+", "Something", false, true), 0.0000001);
        
        //ignore case and punctuation
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("-.SoMe?ThInG+", "Something", true, false), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something", "-.SoMe?ThInG+", true, false), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("-.SoMe?ThInG+", "Something", false, true), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something", "-.SoMe?ThInG+", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("-.SoMe?ThInG+", "Something", true, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something", "-.SoMe?ThInG+", true, true), 0.0000001);
        
        //longer cases
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like that", "Something else like that"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("something else like that", "something else like that"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Something else like that", "Nothing else like that"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Nothing else like that", "Something else like that"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Something else like that", "Something Else Like That"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Something Else Like That", "Something else like that"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something else like that", "Somthing ls lik that"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Somthing ls lik that", "Something else like that"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something else like that", "Somathing alsa lika that"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Somathing alsa lika that", "Something else like that"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Something else like that", "Something aelsea likea that"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Something aelsea likea that", "Something else like that"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something else like that", "Something else like that?"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something else like that?", "Something else like that"), 0.0000001);
        Assert.assertEquals(5, StringComparisonUtility.stringEditDistance("Something else like that", "Something, else... like that?"), 0.0000001);
        Assert.assertEquals(5, StringComparisonUtility.stringEditDistance("Something, else... like that?", "Something else like that"), 0.0000001);
        Assert.assertEquals(24, StringComparisonUtility.stringEditDistance("Something else like that", ""), 0.0000001);
        Assert.assertEquals(24, StringComparisonUtility.stringEditDistance("", "Something else like that"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like that", "Something Else Like That", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something Else Like That", "Something else like that", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like that", "Something else like that?", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like that?", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like that", "Something, else... like that?", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something, else... like that?", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("-.SoMe?ThInG+ else like that", "Something else like that", true, false), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something else like that", "-.SoMe?ThInG+ else like that", true, false), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("-.SoMe?ThInG+ else like that", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something else like that", "-.SoMe?ThInG+ else like that", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("-.SoMe?ThInG+ else like that", "Something else like that", true, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like that", "-.SoMe?ThInG+ else like that", true, true), 0.0000001);
        
        //variables
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like this"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("something else like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something else like ¿", "something else like that"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Something else like ¿", "Nothing else like that"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Nothing else like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(2, StringComparisonUtility.stringEditDistance("Something else like ¿", "Something Else Like That"), 0.0000001);
        Assert.assertEquals(2, StringComparisonUtility.stringEditDistance("Something Else Like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something else like ¿", "Somthing ls lik that"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Somthing ls lik ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something else like ¿", "Somathing alsa lika that"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Somathing alsa lika ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Something else like ¿", "Something aelsea likea that"), 0.0000001);
        Assert.assertEquals(3, StringComparisonUtility.stringEditDistance("Something aelsea likea ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like that?"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("Something else like ¿?", "Something else like that"), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something else like ¿", "Something, else... like that?"), 0.0000001);
        Assert.assertEquals(5, StringComparisonUtility.stringEditDistance("Something, else... like ¿?", "Something else like that"), 0.0000001);
        Assert.assertEquals(20, StringComparisonUtility.stringEditDistance("Something else like ¿", ""), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like ¿", "Something Else Like That", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something Else Like ¿", "Something else like that", true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like that?", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like ¿?", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like ¿", "Something, else... like that?", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something, else... like ¿?", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("-.SoMe?ThInG+ else like ¿", "Something else like that", true, false), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something else like ¿", "-.SoMe?ThInG+ else like that", true, false), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("-.SoMe?ThInG+ else like ¿", "Something else like that", false, true), 0.0000001);
        Assert.assertEquals(4, StringComparisonUtility.stringEditDistance("Something else like ¿", "-.SoMe?ThInG+ else like that", false, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("-.SoMe?ThInG+ else like ¿", "Something else like that", true, true), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("Something else like ¿", "-.SoMe?ThInG+ else like that", true, true), 0.0000001);
        
        //multiple variables
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("¿ else like ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("¿ else like ¿", "Nothing else like this"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("¿ else ¿ ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("¿ else ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("¿thing else ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("¿thing else ¿", "Somethin else like that"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("¿thin else ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("¿thing else ¿", "Something elsea like that"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("¿thing elsea ¿", "Something else like that"), 0.0000001);
        Assert.assertEquals(0, StringComparisonUtility.stringEditDistance("¿thing else ¿", "thing else again"), 0.0000001);
        Assert.assertEquals(1, StringComparisonUtility.stringEditDistance("¿¿¿¿¿thing elsea ¿¿", "Something else like that"), 0.0000001);
    }
    
    /**
     * JUnit test of extractVariables.
     *
     * @throws Exception When there is an exception.
     * @see StringComparisonUtility#extractVariables(String, String, int[][], int, int, UUID, List, List, boolean, boolean)
     * @see StringComparisonUtility#extractVariables(String, String, int[][], List, List, boolean)
     * @see #testExtractVariablesStandard()
     * @see #testExtractVariablesSpecial()
     */
    @Test
    public void testExtractVariables() throws Exception {
        testExtractVariablesStandard();
        testExtractVariablesSpecial();
    }
    
    /**
     * Helper method for JUnit test of extractVariables for standard cases.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("RedundantOperationOnEmptyContainer")
    private void testExtractVariablesStandard() throws Exception {
        List<List<String>> vars = new ArrayList<>();
        List<List<String>> tokens = new ArrayList<>();
        List<String> expectedVars = new ArrayList<>();
        List<String> expectedTokens = new ArrayList<>();
        
        
        //general
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like this and that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("this and that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like that", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Something");
        expectedTokens.add(" else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something ¿ that", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ");
        expectedVars.add("else like");
        expectedTokens.add(" that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //case
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something else like ¿", "Something Else Like This", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something Else Like ");
        expectedVars.add("This");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something Else Like ¿", "something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something else like ");
        expectedVars.add("this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something else like ¿", "SOMETHING ELSE LIKE THIS", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("SOMETHING ELSE LIKE ");
        expectedVars.add("THIS");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SOMETHING ELSE LIKE ¿", "something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something else like ");
        expectedVars.add("this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something else like ¿", "SoMeThInG ElSe LiKe ThIs", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("SoMeThInG ElSe LiKe ");
        expectedVars.add("ThIs");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SoMeThInG ElSe LiKe ¿", "something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something else like ");
        expectedVars.add("this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like this", "Something Else Like This", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Something");
        expectedTokens.add(" Else Like This");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ Else Like This", "something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("something");
        expectedTokens.add(" else like this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like this", "SOMETHING ELSE LIKE THIS", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("SOMETHING");
        expectedTokens.add(" ELSE LIKE THIS");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ ELSE LIKE THIS", "something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("something");
        expectedTokens.add(" else like this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like this", "SoMeThInG ElSe LiKe ThIs", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("SoMeThInG");
        expectedTokens.add(" ElSe LiKe ThIs");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ ElSe LiKe ThIs", "something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("something");
        expectedTokens.add(" else like this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something ¿ this", "Something Else Like This", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ");
        expectedVars.add("Else Like");
        expectedTokens.add(" This");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something ¿ This", "something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something ");
        expectedVars.add("else like");
        expectedTokens.add(" this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something ¿ this", "SOMETHING ELSE LIKE THIS", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("SOMETHING ");
        expectedVars.add("ELSE LIKE");
        expectedTokens.add(" THIS");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SOMETHING ¿ THIS", "something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something ");
        expectedVars.add("else like");
        expectedTokens.add(" this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something ¿ this", "SoMeThInG ElSe LiKe ThIs", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("SoMeThInG ");
        expectedVars.add("ElSe LiKe");
        expectedTokens.add(" ThIs");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SoMeThInG ¿ ThIs", "something else like this", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something ");
        expectedVars.add("else like");
        expectedTokens.add(" this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //removal
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Somethin else lke that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Somethin else lke ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Somethin else lke ¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "omething else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("omething else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("omething else like ¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like a ¿ or something", "Something else like thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("thing");
        expectedTokens.add(" or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿ or something", "Something else like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("a thing");
        expectedTokens.add(" or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like a ¿ or something", "Something else like a thing something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like a ");
        expectedVars.add("thing");
        expectedTokens.add(" something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like a ¿ something", "Something else like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like a ");
        expectedVars.add("thing or");
        expectedTokens.add(" something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like a ¿ or else", "Something else like a thing", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like a ");
        expectedVars.add("thing");
        expectedTokens.add("");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like a ¿", "Something else like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like a ");
        expectedVars.add("thing or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("A ¿ else like a thing or something", "something else like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("");
        expectedVars.add("something");
        expectedTokens.add(" else like a thing or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like a thing or something", "A something else like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("A something");
        expectedTokens.add(" else like a thing or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("A ¿ else like a thing or something", "A something like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("A ");
        expectedVars.add("something");
        expectedTokens.add(" like a thing or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("A ¿ like a thing or something", "A something else like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("A ");
        expectedVars.add("something else");
        expectedTokens.add(" like a thing or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("A ¿ a thing or something", "A else like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("A ");
        expectedVars.add("else like");
        expectedTokens.add(" a thing or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("A ¿ a thing or something", "A something else like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("A ");
        expectedVars.add("something else like");
        expectedTokens.add(" a thing or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("A something ¿ a thing or something", "A something else like thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("A something ");
        expectedVars.add("else like");
        expectedTokens.add(" thing or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("A something ¿ thing or something", "A something else like a thing or something", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("A something ");
        expectedVars.add("else like a");
        expectedTokens.add(" thing or something");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //replacement
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Somethang alse lake that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Somethang alse lake ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Somethang alse lake ¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Aomething else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Aomething else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Aomething else like ¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿ or else", "Something else like that or elsa", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add(" or elsa");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿ or elsa", "Something else like that or else", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add(" or else");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("A Something else like ¿ or elsa", "Something else like that or else", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add(" or else");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like a¿", "Something else like ethat", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like e");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿a", "Something else like thate", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add("e");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("a¿ else like that", "eSomething else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("e");
        expectedVars.add("Something");
        expectedTokens.add(" else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿a else like that", "Somethinge else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Something");
        expectedTokens.add("e else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something a¿ that", "Something eelse like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something e");
        expectedVars.add("else like");
        expectedTokens.add(" that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something ¿a that", "Something else likee that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ");
        expectedVars.add("else like");
        expectedTokens.add("e that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //insertion
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Somethinga elsea alike that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Somethinga elsea alike ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Somethinga elsea alike ¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "ASomething else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("ASomething else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("ASomething else like ¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like a¿", "Something else like athat", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like a");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like a¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like athat", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("athat");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿a", "Something else like thata", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add("a");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿a", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add("");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like thata", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("thata");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("a¿ else like that", "aSomething else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("a");
        expectedVars.add("Something");
        expectedTokens.add(" else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("a¿ else like that", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("");
        expectedVars.add("Something");
        expectedTokens.add(" else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like that", "aSomething else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("aSomething");
        expectedTokens.add(" else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿a else like that", "Somethinga else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Something");
        expectedTokens.add("a else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿a else like that", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Something");
        expectedTokens.add(" else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like that", "Somethinga else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Somethinga");
        expectedTokens.add(" else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something a¿ that", "Something aelse like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something a");
        expectedVars.add("else like");
        expectedTokens.add(" that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something a¿ that", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ");
        expectedVars.add("else like");
        expectedTokens.add(" that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something ¿ that", "Something aelse like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ");
        expectedVars.add("aelse like");
        expectedTokens.add(" that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something ¿a that", "Something else likea that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ");
        expectedVars.add("else like");
        expectedTokens.add("a that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something ¿a that", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ");
        expectedVars.add("else like");
        expectedTokens.add(" that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something ¿ that", "Something else likea that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ");
        expectedVars.add("else likea");
        expectedTokens.add(" that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //punctuation
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿?", "Something else like that?", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add("?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?Something else like ¿", "?Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("?Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?Something! else like ¿?", "?Something! else like that?", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("?Something! else like ");
        expectedVars.add("that");
        expectedTokens.add("?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like that?", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "?Something! else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("?Something! else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿?", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add("");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?Something else like ¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?Something! else like ¿?", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add("");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something, else... like ¿", "Something else like that?-.", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that?-.");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something, else... like ¿?-.", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add("");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something, else... like ¿?-.", "Something else like that?-.", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        expectedTokens.add("?-.");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("S¿ing¿se... like ¿a?-.", "++++Somethi*()ng+! else$#@ like thata?-.", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("++++S");
        expectedVars.add("ometh");
        expectedTokens.add("i*()ng+!");
        expectedVars.add(" el");
        expectedTokens.add("se$#@ like ");
        expectedVars.add("that");
        expectedTokens.add("a?-.");
        Assert.assertFalse(containsExtraction(vars, tokens, expectedVars, expectedTokens)); //this will be true if ignorePunctuation is set to true
        
        
        //empty
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like that", "", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("", "", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Something else like that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿", "", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
    }
    
    /**
     * Helper method for JUnit test of extractVariables for special cases.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("RedundantOperationOnEmptyContainer")
    private void testExtractVariablesSpecial() throws Exception {
        List<List<String>> vars = new ArrayList<>();
        List<List<String>> tokens = new ArrayList<>();
        List<String> expectedVars = new ArrayList<>();
        List<String> expectedTokens = new ArrayList<>();
        
        
        //ignore case
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something else like ¿", "Something Else Like This", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something Else Like ");
        expectedVars.add("This");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something Else Like ¿", "something else like this", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something else like ");
        expectedVars.add("this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something else like ¿", "SOMETHING ELSE LIKE THIS", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("SOMETHING ELSE LIKE ");
        expectedVars.add("THIS");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SOMETHING ELSE LIKE ¿", "something else like this", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something else like ");
        expectedVars.add("this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something else like ¿", "SoMeThInG ElSe LiKe ThIs", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("SoMeThInG ElSe LiKe ");
        expectedVars.add("ThIs");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SoMeThInG ElSe LiKe ¿", "something else like this", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something else like ");
        expectedVars.add("this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like this", "Something Else Like This", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Something");
        expectedTokens.add(" Else Like This");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ Else Like This", "something else like this", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("something");
        expectedTokens.add(" else like this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like this", "SOMETHING ELSE LIKE THIS", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("SOMETHING");
        expectedTokens.add(" ELSE LIKE THIS");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ ELSE LIKE THIS", "something else like this", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("something");
        expectedTokens.add(" else like this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ else like this", "SoMeThInG ElSe LiKe ThIs", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("SoMeThInG");
        expectedTokens.add(" ElSe LiKe ThIs");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ ElSe LiKe ThIs", "something else like this", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("something");
        expectedTokens.add(" else like this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something ¿ this", "Something Else Like This", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ");
        expectedVars.add("Else Like");
        expectedTokens.add(" This");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something ¿ This", "something else like this", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something ");
        expectedVars.add("else like");
        expectedTokens.add(" this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something ¿ this", "SOMETHING ELSE LIKE THIS", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("SOMETHING ");
        expectedVars.add("ELSE LIKE");
        expectedTokens.add(" THIS");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SOMETHING ¿ THIS", "something else like this", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something ");
        expectedVars.add("else like");
        expectedTokens.add(" this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something ¿ this", "SoMeThInG ElSe LiKe ThIs", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("SoMeThInG ");
        expectedVars.add("ElSe LiKe");
        expectedTokens.add(" ThIs");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SoMeThInG ¿ ThIs", "something else like this", vars, tokens, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something ");
        expectedVars.add("else like");
        expectedTokens.add(" this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //ignore punctuation
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿?", "Something else like that?", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?Something else like ¿", "?Something else like that", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("?Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?Something! else like ¿?", "?Something! else like that?", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("?Something! else like ");
        expectedVars.add("that?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "Something else like that?", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿", "?Something! else like that", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("?Something! else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something else like ¿?", "Something else like that", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?Something else like ¿", "Something else like that", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?Something! else like ¿?", "Something else like that", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something, else... like ¿", "Something else like that?-.", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that?-.");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something, else... like ¿?-.", "Something else like that", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something, else... like ¿?-.", "Something else like that?-.", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something else like ");
        expectedVars.add("that?-.");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("S¿ing¿se... like ¿a?-.", "++++Somethi*()ng+! else$#@ like thata?-.", vars, tokens, false, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("++++S");
        expectedVars.add("ometh");
        expectedTokens.add("i*()ng+!");
        expectedVars.add(" el");
        expectedTokens.add("se$#@ like ");
        expectedVars.add("that");
        expectedTokens.add("a?-.");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //ignore case and punctuation
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something else like ¿?", "Something Else Like This?", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something Else Like ");
        expectedVars.add("This?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?Something Else Like ¿", "?something else like this", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("?something else like ");
        expectedVars.add("this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?something! else like ¿?", "?SOMETHING! ELSE LIKE THIS?", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("?SOMETHING! ELSE LIKE ");
        expectedVars.add("THIS?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SOMETHING ELSE LIKE ¿", "something else like this?", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something else like ");
        expectedVars.add("this?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something else like ¿", "?SoMeThInG! ElSe LiKe ThIs", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("?SoMeThInG! ElSe LiKe ");
        expectedVars.add("ThIs");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SoMeThInG ElSe LiKe ¿?", "something else like this", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something else like ");
        expectedVars.add("this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?¿ else like this", "Something Else Like This", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Something");
        expectedTokens.add(" Else Like This");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("?¿! Else Like This?", "something else like this", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("something");
        expectedTokens.add(" else like this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿, else... like this", "SOMETHING, ELSE LIKE THIS?-.", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("SOMETHING,");
        expectedTokens.add(" ELSE LIKE THIS?-.");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿, ELSE... LIKE THIS?-.", "something else like this", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("something");
        expectedTokens.add(" else like this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿, else... like this?-.", "SoMeThInG ElSe LiKe ThIs?-.", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("SoMeThInG");
        expectedTokens.add(" ElSe LiKe ThIs?-.");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿, ElSe... LiKe ThIs?-.", "something else like this", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("something");
        expectedTokens.add(" else like this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something, ¿... this", "Something ?Else Like... This", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something ?");
        expectedVars.add("Else Like...");
        expectedTokens.add(" This");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something, ¿- This?", "something, else like- this?", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something, ");
        expectedVars.add("else like-");
        expectedTokens.add(" this?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something ?¿? this", "SOMETHING ?ELSE LIKE? THIS", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("SOMETHING ?");
        expectedVars.add("ELSE LIKE?");
        expectedTokens.add(" THIS");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("--SOMETHING.. ¿-= THIS?", "something else like this", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("something ");
        expectedVars.add("else like");
        expectedTokens.add(" this");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("something ¿ this", "--SoMeThInG.. ElSe LiKe-= ThIs?", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("--SoMeThInG.. ");
        expectedVars.add("ElSe LiKe-=");
        expectedTokens.add(" ThIs?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("SoM-.eThInG ¿ ThIs", "som-.ething else? like& thi-.s?", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("som-.ething ");
        expectedVars.add("else? like&");
        expectedTokens.add(" thi-.s?");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("S¿Ing¿SE... lIkE ¿a?-.", "++++SoMetHI*()ng+! eLse$#@ LiKe thatA?-.", vars, tokens, true, true);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("++++S");
        expectedVars.add("oMetH");
        expectedTokens.add("I*()ng+!");
        expectedVars.add(" eL");
        expectedTokens.add("se$#@ LiKe ");
        expectedVars.add("that");
        expectedTokens.add("A?-.");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //multiple variables
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿thing else ¿", "Something else again", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Some");
        expectedTokens.add("thing else ");
        expectedVars.add("again");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿¿¿¿thing else ¿¿", "Something else again", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Some");
        expectedTokens.add("thing else ");
        expectedVars.add("again");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿thin¿ e¿e ¿", "Something else again", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("Some");
        expectedTokens.add("thin");
        expectedVars.add("g");
        expectedTokens.add(" e");
        expectedVars.add("ls");
        expectedTokens.add("e ");
        expectedVars.add("again");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("So¿thin¿ e¿e ¿in", "Something else again", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("So");
        expectedVars.add("me");
        expectedTokens.add("thin");
        expectedVars.add("g");
        expectedTokens.add(" e");
        expectedVars.add("ls");
        expectedTokens.add("e ");
        expectedVars.add("aga");
        expectedTokens.add("in");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("So¿¿thin¿¿¿¿¿ e¿¿¿¿e ¿¿¿¿¿¿¿¿¿in", "Something else again", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("So");
        expectedVars.add("me");
        expectedTokens.add("thin");
        expectedVars.add("g");
        expectedTokens.add(" e");
        expectedVars.add("ls");
        expectedTokens.add("e ");
        expectedVars.add("aga");
        expectedTokens.add("in");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //empty variables
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something¿ el¿se like ¿", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Something");
        expectedVars.add("");
        expectedTokens.add(" el");
        expectedVars.add("");
        expectedTokens.add("se like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("Something¿ el¿se like ¿", "Somethin ese like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("Somethin");
        expectedVars.add("");
        expectedTokens.add(" e");
        expectedVars.add("");
        expectedTokens.add("se like ");
        expectedVars.add("that");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿Something¿ el¿se like ¿t", "Something else like that", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("");
        expectedTokens.add("Something");
        expectedVars.add("");
        expectedTokens.add(" el");
        expectedVars.add("");
        expectedTokens.add("se like ");
        expectedVars.add("tha");
        expectedTokens.add("t");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        
        //multiple extractions
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("¿ ¿s", "What the hell are lobsters", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("What the hell are");
        expectedTokens.add(" ");
        expectedVars.add("lobster");
        expectedTokens.add("s");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("What the hell");
        expectedTokens.add(" ");
        expectedVars.add("are lobster");
        expectedTokens.add("s");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("What the");
        expectedTokens.add(" ");
        expectedVars.add("hell are lobster");
        expectedTokens.add("s");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        expectedVars.clear();
        expectedTokens.clear();
        expectedVars.add("What");
        expectedTokens.add(" ");
        expectedVars.add("the hell are lobster");
        expectedTokens.add("s");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        
        vars.clear();
        tokens.clear();
        StringComparisonUtility.stringEditDistance("What the ¿a are ¿a", "What the hellb are lobstersb", vars, tokens);
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("What the ");
        expectedVars.add("hellb");
        expectedTokens.add(" are ");
        expectedVars.add("lobsters");
        expectedTokens.add("b");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("What the ");
        expectedVars.add("hell");
        expectedTokens.add("b are ");
        expectedVars.add("lobsters");
        expectedTokens.add("b");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("What the ");
        expectedVars.add("hellb");
        expectedTokens.add(" are ");
        expectedVars.add("lobstersb");
        expectedTokens.add("");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
        expectedVars.clear();
        expectedTokens.clear();
        expectedTokens.add("What the ");
        expectedVars.add("hell");
        expectedTokens.add("b are ");
        expectedVars.add("lobstersb");
        expectedTokens.add("");
        Assert.assertTrue(containsExtraction(vars, tokens, expectedVars, expectedTokens));
    }
    
    
    //Methods
    
    /**
     * Determines if the expected vars and tokens exist within the extracted vars and tokens.
     *
     * @param vars           The extracted vars.
     * @param tokens         The extracted tokens.
     * @param expectedVars   The expected vars.
     * @param expectedTokens The expected tokens.
     * @return Whether the expected vars and tokens exist within the extracted vars and tokens or not.
     */
    private boolean containsExtraction(List<List<String>> vars, List<List<String>> tokens, List<String> expectedVars, List<String> expectedTokens) {
        boolean exists = vars.isEmpty() && tokens.isEmpty() && expectedVars.isEmpty() && expectedTokens.isEmpty();
        for (int i = 0; i < (vars.isEmpty() ? tokens : vars).size(); i++) {
            if (((vars.isEmpty() && expectedVars.isEmpty()) || Arrays.equals(vars.get(i).toArray(), expectedVars.toArray())) &&
                    ((tokens.isEmpty() && expectedTokens.isEmpty()) || Arrays.equals(tokens.get(i).toArray(), expectedTokens.toArray()))) {
                return true;
            }
        }
        return exists;
    }
    
}
