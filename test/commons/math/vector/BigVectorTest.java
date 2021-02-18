/*
 * File:    BigVectorTest.java
 * Package: commons.math.vector
 * Author:  Zachary Gill
 */

package commons.math.vector;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import commons.list.ArrayUtility;
import commons.list.ListUtility;
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
 * JUnit test of BigVector.
 *
 * @see BigVector
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BigVector.class})
public class BigVectorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigVectorTest.class);
    
    
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
     * @see BigVector#PRECISION
     * @see BigVector#DEFAULT_MATH_PRECISION
     * @see BigVector#DEFAULT_ROUNDING_MODE
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(new BigDecimal("0.000000000000000000000000000001"), BigVector.PRECISION);
        Assert.assertEquals(32, BigVector.DEFAULT_MATH_PRECISION);
        Assert.assertEquals(RoundingMode.HALF_UP, BigVector.DEFAULT_ROUNDING_MODE);
    }
    
    /**
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#BigVector(Number...)
     * @see BigVector#BigVector(String...)
     * @see BigVector#BigVector(List)
     * @see BigVector#BigVector(BigVector)
     * @see BigVector#BigVector(Vector)
     * @see BigVector#BigVector(BigVector, Number...)
     * @see BigVector#BigVector(BigVector, String...)
     * @see BigVector#BigVector(Vector, Number...)
     * @see BigVector#BigVector(Vector, String...)
     */
    @Test
    public void testConstructor() throws Exception {
        BigVector testVector;
        List<Number> testValues;
        
        //components
        BigVector vector1 = new BigVector(BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1));
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector1.components);
        
        //components, other types
        testVector = new BigVector(0.884d, 2.0d, 1.1d);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.1)}, testVector.components);
        testVector = new BigVector(0.884f, 2.0f, 1.1f);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.8840000033378601), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.100000023841858)}, testVector.components);
        testVector = new BigVector(0, 2, 1);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        testVector = new BigVector(0L, 2L, 1L);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        testVector = new BigVector(0x0, 0x2, 0x1);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        
        //string components
        BigVector vector2 = new BigVector("0.884", "2", "1.1");
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector1.components);
        
        //list of components
        List<BigDecimal> values = Arrays.asList(BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1));
        BigVector vector3 = new BigVector(values);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector2.components);
        
        //list of components, other types
        testValues = Arrays.asList(0.884d, 2.0d, 1.1d);
        testVector = new BigVector(testValues);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.1)}, testVector.components);
        testValues = Arrays.asList(0.884f, 2.0f, 1.1f);
        testVector = new BigVector(testValues);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.8840000033378601), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.100000023841858)}, testVector.components);
        testValues = Arrays.asList(0, 2, 1);
        testVector = new BigVector(testValues);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        testValues = Arrays.asList(0L, 2L, 1L);
        testVector = new BigVector(testValues);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        testValues = Arrays.asList(0x0, 0x2, 0x1);
        testVector = new BigVector(testValues);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        
        //big vector
        BigVector vector4 = new BigVector(new BigVector(BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)));
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector3.components);
        
        //vector
        BigVector vector5 = new BigVector(new Vector(0.884, 2, 1.1));
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector4.components);
        
        //big vector and component
        BigVector vector6 = new BigVector(new BigVector(BigDecimal.valueOf(0.884), BigDecimal.valueOf(2)), BigDecimal.valueOf(1.1));
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.1)}, vector5.components);
        
        //big vector and components
        BigVector vector7 = new BigVector(new BigVector(BigDecimal.valueOf(0.884)), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1));
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector6.components);
        
        //big vector and components, other types
        testVector = new BigVector(new BigVector(BigDecimal.valueOf(0.884)), 2.0d, 1.1d);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.1)}, testVector.components);
        testVector = new BigVector(new BigVector(BigDecimal.valueOf(0.884)), 2.0f, 1.1f);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.100000023841858)}, testVector.components);
        testVector = new BigVector(new BigVector(BigDecimal.valueOf(0.884)), 2, 1);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        testVector = new BigVector(new BigVector(BigDecimal.valueOf(0.884)), 2L, 1L);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        testVector = new BigVector(new BigVector(BigDecimal.valueOf(0.884)), 0x2, 0x1);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        
        //big vector and string components
        BigVector vector8 = new BigVector(new BigVector(BigDecimal.valueOf(0.884)), "2", "1.1");
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector6.components);
        
        //vector and component
        BigVector vector9 = new BigVector(new Vector(0.884, 2), BigDecimal.valueOf(1.1));
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector7.components);
        
        //vector and components
        BigVector vector10 = new BigVector(new Vector(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1));
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector8.components);
        
        //vector and components, other types
        testVector = new BigVector(new Vector(0.884), 2.0d, 1.1d);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.1)}, testVector.components);
        testVector = new BigVector(new Vector(0.884), 2.0f, 1.1f);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.100000023841858)}, testVector.components);
        testVector = new BigVector(new Vector(0.884), 2, 1);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        testVector = new BigVector(new Vector(0.884), 2L, 1L);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        testVector = new BigVector(new Vector(0.884), 0x2, 0x1);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, testVector.components);
        
        //vector and string components
        BigVector vector11 = new BigVector(new Vector(0.884), "2", "1.1");
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.884), BigDecimal.valueOf(2), BigDecimal.valueOf(1.1)}, vector8.components);
        
        //equality
        Assert.assertEquals(vector1, vector2);
        Assert.assertEquals(vector2, vector3);
        Assert.assertEquals(vector3, vector4);
        Assert.assertEquals(vector4, vector5);
        Assert.assertEquals(vector5, vector6);
        Assert.assertEquals(vector6, vector7);
        Assert.assertEquals(vector7, vector8);
        Assert.assertEquals(vector8, vector9);
        Assert.assertEquals(vector9, vector10);
        Assert.assertEquals(vector10, vector11);
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#toString()
     */
    @Test
    public void testToString() throws Exception {
        //standard
        Assert.assertEquals("<8.0, 9.0, 1.0>", new BigVector(8, 9, 1).toString());
        Assert.assertEquals("<-8.0, -9.0, -1.0>", new BigVector(-8, -9, -1).toString());
        Assert.assertEquals("<0.0, 0.0, 0.0>", new BigVector(0, 0, 0).toString());
        Assert.assertEquals("<-1.87, 88.06, -7.4>", new BigVector(-1.87, 88.06, -7.4).toString());
        Assert.assertEquals("<1.8187612301, -8.1787546309, 7.4589760359>", new BigVector(1.8187612301, -8.1787546309, 7.4589760359).toString());
        Assert.assertEquals("<1.8187612301079148, 8.178754630997467, 7.458976035987448>", new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).toString());
        Assert.assertEquals("<0.00000000084, 0.000000000000002774, 0.0000000000010>", new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).toString());
        Assert.assertEquals("<8.0, 1.0, 1.0, -6.0, 3.0, 4.0, 7.0, 0.0, -6.0, 0.0, 44.0, 9.0, -1.0, -5.0, 8.0, 7.0, 0.0, 3.0>", new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).toString());
        Assert.assertEquals("<3.0, 5.0>", new BigVector(3, 5).toString());
        Assert.assertEquals("<1.87>", new BigVector(1.87).toString());
        Assert.assertEquals("<0.0>", new BigVector(0).toString());
        Assert.assertEquals("<>", new BigVector().toString());
        
        //big
        Assert.assertEquals(
                "<84894560451354987897191613980704105013356, 979870123002169049094413418578, 1054984501590123415455451205690>",
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).toString()
        );
        Assert.assertEquals(
                "<0.84894560451354987897191613980704105013356, 0.979870123002169049094413418578, 0.1054984501590123415455451205690>",
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).toString()
        );
        Assert.assertEquals(
                "<84894560451354987897191613980704105013356.84894560451354987897191613980704105013356, 979870123002169049094413418578.979870123002169049094413418578, 1054984501590123415455451205690.1054984501590123415455451205690>",
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).toString()
        );
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#equals(Object)
     */
    @SuppressWarnings({"SimplifiableJUnitAssertion", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() throws Exception {
        //standard
        Assert.assertTrue(new BigVector(8).equals(new BigVector(8)));
        Assert.assertTrue(new BigVector(8).equals(new BigVector(8.0)));
        Assert.assertTrue(new BigVector(8).equals(new BigVector(8.00000)));
        Assert.assertTrue(new BigVector(1, 2, 3).equals(new BigVector(1, 2, 3)));
        Assert.assertTrue(new BigVector(1, 2, -3, 4, -5, -6, 7, 8, 9, 10).equals(new BigVector(1, 2, -3, 4, -5, -6, 7, 8, 9, 10)));
        Assert.assertTrue(new BigVector().equals(new BigVector()));
        
        //precision
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.1")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.01")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.0001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.00001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.0000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.00000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.0000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.00000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.0000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.00000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.0000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.00000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.0000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.00000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.000000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.0000000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.00000000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.000000000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.0000000000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.00000000000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.000000000000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.0000000000000000000000000001")));
        Assert.assertFalse(new BigVector(1).equals(new BigVector("1.00000000000000000000000000001")));
        Assert.assertTrue(new BigVector(1).equals(new BigVector("1.000000000000000000000000000001")));
        
        //big
        Assert.assertTrue(
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690")).equals(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690")))
        );
        Assert.assertTrue(
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.9798701230021690490943456413418578"), new BigDecimal("0.10549845015901234152456455451205690")).equals(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.9798701230021690490943456413418578"), new BigDecimal("0.10549845015901234152456455451205690")))
        );
        Assert.assertTrue(
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.9798701230021690490943456413418578"), new BigDecimal("0.10549845015901234152456455451205690")).equals(
                        new BigVector(new BigDecimal("0.848945604513549878971916139807"), new BigDecimal("0.979870123002169049094345641341"), new BigDecimal("0.105498450159012341524564554512")))
        );
        Assert.assertTrue(
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578.9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690.10549845015901234152456455451205690")).equals(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578.9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690.10549845015901234152456455451205690")))
        );
        Assert.assertTrue(
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578.9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690.10549845015901234152456455451205690")).equals(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356.848945604513549878971916139807"), new BigDecimal("9798701230021690490943456413418578.979870123002169049094345641341"), new BigDecimal("10549845015901234152456455451205690.105498450159012341524564554512")))
        );
        
        //invalid
        Assert.assertFalse(new BigVector(1, 1, 0).equals(new BigVector(1, 1)));
        Assert.assertFalse(new BigVector(1, 1).equals(new BigVector(1)));
        Assert.assertFalse(new BigVector(1).equals(new BigVector()));
        Assert.assertFalse(new BigVector(1).equals(1.0));
        Assert.assertFalse(new BigVector(1).equals(1));
        Assert.assertFalse(new BigVector(1).equals("test"));
        Assert.assertFalse(new BigVector(1).equals(null));
    }
    
    /**
     * JUnit test of dimensionsEqual.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dimensionalityEqual(BigVector)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDimensionsEqual() throws Exception {
        //equal
        Assert.assertTrue(new BigVector(8).dimensionalityEqual(new BigVector(8)));
        Assert.assertTrue(new BigVector(8).dimensionalityEqual(new BigVector(8.0)));
        Assert.assertTrue(new BigVector(8).dimensionalityEqual(new BigVector(8.00000)));
        Assert.assertTrue(new BigVector(6).dimensionalityEqual(new BigVector(1)));
        Assert.assertTrue(new BigVector(6).dimensionalityEqual(new BigVector(1.844156187)));
        Assert.assertTrue(new BigVector(6).dimensionalityEqual(new BigVector(0)));
        Assert.assertTrue(new BigVector(6, 5).dimensionalityEqual(new BigVector(1, 2)));
        Assert.assertTrue(new BigVector(6, 5, 4).dimensionalityEqual(new BigVector(1, 2, 3)));
        Assert.assertTrue(new BigVector(6.489, -5.01547, 4.3367).dimensionalityEqual(new BigVector(1.04001, 2.754, 3.4169)));
        Assert.assertTrue(new BigVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).dimensionalityEqual(new BigVector(11, 12, 13, 14, 15, 16, 17, 18, 19, 20)));
        Assert.assertTrue(new BigVector().dimensionalityEqual(new BigVector()));
        
        //not equal
        Assert.assertFalse(new BigVector(5).dimensionalityEqual(new BigVector()));
        Assert.assertFalse(new BigVector(5).dimensionalityEqual(new BigVector(8.01, -9)));
        Assert.assertFalse(new BigVector(5).dimensionalityEqual(new BigVector(8.01, -9, 4.7)));
        Assert.assertFalse(new BigVector(5).dimensionalityEqual(new BigVector(8.01, -9, 4.7, 910)));
        Assert.assertFalse(new BigVector(5, 5).dimensionalityEqual(new BigVector()));
        Assert.assertFalse(new BigVector(5, 5).dimensionalityEqual(new BigVector(8)));
        Assert.assertFalse(new BigVector(5, 5).dimensionalityEqual(new BigVector(8.01, -9, 4.7)));
        Assert.assertFalse(new BigVector(5, 5).dimensionalityEqual(new BigVector(8.01, -9, 4.7, 910)));
        Assert.assertFalse(new BigVector(5, 5, 9.1).dimensionalityEqual(new BigVector()));
        Assert.assertFalse(new BigVector(5, 5, 9.1).dimensionalityEqual(new BigVector(8)));
        Assert.assertFalse(new BigVector(5, 5, 9.1).dimensionalityEqual(new BigVector(8.01, -9)));
        Assert.assertFalse(new BigVector(5, 5, 9.1).dimensionalityEqual(new BigVector(8.01, -9, 4.7, 910)));
        
        //big
        Assert.assertTrue(
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690")).dimensionalityEqual(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690")))
        );
        Assert.assertTrue(
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.9798701230021690490943456413418578"), new BigDecimal("0.10549845015901234152456455451205690")).dimensionalityEqual(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.9798701230021690490943456413418578"), new BigDecimal("0.10549845015901234152456455451205690")))
        );
        Assert.assertTrue(
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578.9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690.10549845015901234152456455451205690")).dimensionalityEqual(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578.9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690.10549845015901234152456455451205690")))
        );
        Assert.assertFalse(
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578"), new BigDecimal("10549845015901234152456455451205690")).dimensionalityEqual(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("9798701230021690490943456413418578")))
        );
        Assert.assertFalse(
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.9798701230021690490943456413418578")).dimensionalityEqual(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356")))
        );
        Assert.assertFalse(
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.9798701230021690490943456413418578")).dimensionalityEqual(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.9798701230021690490943456413418578"), new BigDecimal("0.10549845015901234152456455451205690")))
        );
        
        //invalid
        Assert.assertFalse(new BigVector(6).dimensionalityEqual(null));
        Assert.assertFalse(new BigVector(6, 5, 4).dimensionalityEqual(null));
        Assert.assertFalse(new BigVector().dimensionalityEqual(null));
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#clone()
     */
    @Test
    public void testClone() throws Exception {
        BigVector vector;
        BigVector clone;
        
        //standard
        
        vector = new BigVector(1, 2, 3);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new BigVector(8);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new BigVector(1, 2.1897642106, -3);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new BigVector(8.4675112, 10.1084423, 0.1455741);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new BigVector(1, 2, -3, 4, 5, 6, -7, 8, -9, 10);
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new BigVector();
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        //independence
        
        vector = new BigVector(1, 2, 3);
        clone = vector.clone();
        clone.setX(BigDecimal.valueOf(4));
        vector.setY(BigDecimal.valueOf(5));
        Assert.assertEquals(BigDecimal.valueOf(1.0), vector.getX());
        Assert.assertEquals(BigDecimal.valueOf(4), clone.getX());
        Assert.assertEquals(BigDecimal.valueOf(5), vector.getY());
        Assert.assertEquals(BigDecimal.valueOf(2.0), clone.getY());
        
        //big
        
        vector = new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690"));
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690"));
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
        
        vector = new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690"));
        clone = vector.clone();
        Assert.assertArrayEquals(vector.components, clone.components);
        Assert.assertEquals(vector, clone);
        Assert.assertNotEquals(vector.hashCode(), clone.hashCode());
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        BigVector vector;
        BigVector reverse;
        
        //standard
        
        vector = new BigVector(1, 2, 3);
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(3.0)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(3.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, reverse.components);
        
        vector = new BigVector(8);
        reverse = vector.reverse();
        Assert.assertEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(8.0)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(8.0)}, reverse.components);
        
        vector = new BigVector(1, 2.1897642106, -3);
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.1897642106), BigDecimal.valueOf(-3.0)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(-3.0), BigDecimal.valueOf(2.1897642106), BigDecimal.valueOf(1.0)}, reverse.components);
        
        vector = new BigVector(8.4675112, -10.1084423, 0.1455741);
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(8.4675112), BigDecimal.valueOf(-10.1084423), BigDecimal.valueOf(0.1455741)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.1455741), BigDecimal.valueOf(-10.1084423), BigDecimal.valueOf(8.4675112)}, reverse.components);
        
        vector = new BigVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(3.0), BigDecimal.valueOf(4.0), BigDecimal.valueOf(5.0), BigDecimal.valueOf(6.0), BigDecimal.valueOf(7.0), BigDecimal.valueOf(8.0), BigDecimal.valueOf(9.0), BigDecimal.valueOf(10.0)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(10.0), BigDecimal.valueOf(9.0), BigDecimal.valueOf(8.0), BigDecimal.valueOf(7.0), BigDecimal.valueOf(6.0), BigDecimal.valueOf(5.0), BigDecimal.valueOf(4.0), BigDecimal.valueOf(3.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0)}, reverse.components);
        
        vector = new BigVector();
        reverse = vector.reverse();
        Assert.assertEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {}, reverse.components);
        
        //independence
        
        vector = new BigVector(1, 2, 3);
        reverse = vector.reverse();
        reverse.setX(BigDecimal.valueOf(4));
        vector.setY(BigDecimal.valueOf(5));
        Assert.assertEquals(BigDecimal.valueOf(1.0), vector.getX());
        Assert.assertEquals(BigDecimal.valueOf(4), reverse.getX());
        Assert.assertEquals(BigDecimal.valueOf(5), vector.getY());
        Assert.assertEquals(BigDecimal.valueOf(2.0), reverse.getY());
        
        //big
        
        vector = new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690"));
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("1054984501590123415455451205690"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("84894560451354987897191613980704105013356")}, reverse.components);
        
        vector = new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690"));
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.1054984501590123415455451205690"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.84894560451354987897191613980704105013356")}, reverse.components);
        
        vector = new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690"));
        reverse = vector.reverse();
        Assert.assertNotEquals(vector, reverse);
        Assert.assertNotEquals(vector.hashCode(), reverse.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356")}, reverse.components);
    }
    
    /**
     * JUnit test of justify.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#justify()
     */
    @Test
    public void testJustify() throws Exception {
        BigVector vector;
        BigVector justified;
        
        //standard
        
        vector = new BigVector(1, 2, 3);
        justified = vector.justify();
        Assert.assertEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(3.0)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(3.0)}, justified.components);
        
        vector = new BigVector(1, 2.1897642106, -3);
        justified = vector.justify();
        Assert.assertEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.1897642106), BigDecimal.valueOf(-3.0)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.1897642106), BigDecimal.valueOf(-3.0)}, justified.components);
        
        vector = new BigVector(8.4675112, -10.1084423, 0.1455741);
        justified = vector.justify();
        Assert.assertEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(8.4675112), BigDecimal.valueOf(-10.1084423), BigDecimal.valueOf(0.1455741)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(8.4675112), BigDecimal.valueOf(-10.1084423), BigDecimal.valueOf(0.1455741)}, justified.components);
        
        vector = new BigVector(1, 2, 3);
        justified = vector.justify();
        justified.setX(BigDecimal.valueOf(4));
        vector.setY(BigDecimal.valueOf(5));
        Assert.assertEquals(BigDecimal.valueOf(1.0), vector.getX());
        Assert.assertEquals(BigDecimal.valueOf(4), justified.getX());
        Assert.assertEquals(BigDecimal.valueOf(5), vector.getY());
        Assert.assertEquals(BigDecimal.valueOf(2.0), justified.getY());
        
        //justification
        
        BigVector.setJustificationVector(new BigVector(BigDecimal.valueOf(4), BigDecimal.valueOf(-2), BigDecimal.valueOf(-3)));
        
        vector = new BigVector(1, 2, 3);
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(3.0)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(4.0), BigDecimal.valueOf(-4.0), BigDecimal.valueOf(-9.0)}, justified.components);
        
        vector = new BigVector(1, 2.1897642106, -3);
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.1897642106), BigDecimal.valueOf(-3.0)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(4.0), BigDecimal.valueOf(-4.3795284212), BigDecimal.valueOf(9.0)}, justified.components);
        
        vector = new BigVector(8.4675112, -10.1084423, 0.1455741);
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(8.4675112), BigDecimal.valueOf(-10.1084423), BigDecimal.valueOf(0.1455741)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(33.8700448), BigDecimal.valueOf(20.2168846), BigDecimal.valueOf(-0.4367223)}, justified.components);
        
        vector = new BigVector(1, 2, 3);
        justified = vector.justify();
        justified.setX(BigDecimal.valueOf(4));
        vector.setY(BigDecimal.valueOf(5));
        Assert.assertEquals(BigDecimal.valueOf(1.0), vector.getX());
        Assert.assertEquals(BigDecimal.valueOf(4), justified.getX());
        Assert.assertEquals(BigDecimal.valueOf(5), vector.getY());
        Assert.assertEquals(BigDecimal.valueOf(-4.0), justified.getY());
        
        BigVector.setJustificationVector(new BigVector(BigDecimal.valueOf(4), BigDecimal.valueOf(-2)));
        
        vector = new BigVector(1, 2);
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0)}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(4.0), BigDecimal.valueOf(-4.0)}, justified.components);
        
        BigVector.setJustificationVector(new BigVector(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
        
        //big
        
        vector = new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690"));
        justified = vector.justify();
        Assert.assertEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")}, justified.components);
        
        vector = new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690"));
        justified = vector.justify();
        Assert.assertEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")}, justified.components);
        
        vector = new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690"));
        justified = vector.justify();
        Assert.assertEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")}, justified.components);
        
        BigVector.setJustificationVector(new BigVector(BigDecimal.valueOf(4), BigDecimal.valueOf(-2), BigDecimal.valueOf(-3)));
        
        vector = new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690"));
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("339578241805419951588766455922816420053424"), new BigDecimal("-1959740246004338098188826837156"), new BigDecimal("-3164953504770370246366353617070")}, justified.components);
        
        vector = new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690"));
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("3.39578241805419951588766455922816420053424"), new BigDecimal("-1.959740246004338098188826837156"), new BigDecimal("-0.3164953504770370246366353617070")}, justified.components);
        
        vector = new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690"));
        justified = vector.justify();
        Assert.assertNotEquals(vector, justified);
        Assert.assertNotEquals(vector.hashCode(), justified.hashCode());
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")}, vector.components);
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("339578241805419951588766455922816420053427.39578241805419951588766455922816420053424"), new BigDecimal("-1959740246004338098188826837157.959740246004338098188826837156"), new BigDecimal("-3164953504770370246366353617070.3164953504770370246366353617070")}, justified.components);
        
        
        BigVector.setJustificationVector(new BigVector(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
        
        //invalid
        
        try {
            vector = new BigVector(8);
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            vector = new BigVector(8, 8);
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            vector = new BigVector(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            vector = new BigVector();
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        BigVector.setJustificationVector(new BigVector(BigDecimal.valueOf(4), BigDecimal.valueOf(-2)));
        
        try {
            vector = new BigVector(8, 8);
            vector.justify();
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
            vector = new BigVector(1, 2, 3);
            vector.justify();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        BigVector.setJustificationVector(new BigVector(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
    }
    
    /**
     * JUnit test of distance.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#distance(BigVector)
     */
    @Test
    public void testDistance() throws Exception {
        //origin
        Assert.assertEquals(new BigDecimal("12.08304597359457206828283926680786"), new BigVector(8, 9, 1).distance(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("12.08304597359457206828283926680786"), new BigVector(-8, -9, -1).distance(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(0, 0, 0).distance(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("88.39016065151143280843055901191871"), new BigVector(-1.87, 88.06, -7.4).distance(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("11.21767548241227820150003634614717"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).distance(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("11.21767548254277104270198334339978"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).distance(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("8.4000059524246473952867E-10"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).distance(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("48.75448697299562396452621555565469"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).distance(BigVector.origin(18)));
        Assert.assertEquals(new BigDecimal("5.83095189484530047087415287754558"), new BigVector(3, 5).distance(BigVector.origin(2)));
        Assert.assertEquals(new BigDecimal("1.87"), new BigVector(1.87).distance(BigVector.origin(1)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(0).distance(BigVector.origin(1)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector().distance(BigVector.origin(0)));
        
        //standard
        Assert.assertEquals(new BigDecimal("0"), new BigVector(8, 9, 1).distance(new BigVector(8, 9, 1)));
        Assert.assertEquals(new BigDecimal("20.02498439450078572769721214832261"), new BigVector(-8, -9, -1).distance(new BigVector(5, 5, 5)));
        Assert.assertEquals(new BigDecimal("13.41640786499873817845504201238766"), new BigVector(0, 0, 0).distance(new BigVector(10, 8, 4)));
        Assert.assertEquals(new BigDecimal("89.44778655339252719881021337137455"), new BigVector(-1.87, 88.06, -7.4).distance(new BigVector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new BigDecimal("16.40762064192865712386163146012918"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).distance(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigDecimal("10.01226499656495416735425439201195"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).distance(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigDecimal("92.63908462354924218900441722048069"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).distance(new BigVector(66, 65, 1)));
        Assert.assertEquals(new BigDecimal("54.46660031248599969846199315225121"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).distance(new BigVector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new BigDecimal("4.47213595499957939281834733746255"), new BigVector(3, 5).distance(new BigVector(1, 1)));
        Assert.assertEquals(new BigDecimal("0.13"), new BigVector(1.87).distance(new BigVector(2)));
        Assert.assertEquals(new BigDecimal("1E+1"), new BigVector(0).distance(new BigVector(10)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector().distance(new BigVector()));
        
        //big
        Assert.assertEquals(new BigDecimal("84894560451354987897203824054856619533585.09662095964373338817710029031259"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).distance(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigDecimal("1.3007628609247406374838690478937"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).distance(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigDecimal("120059038760569945831372463730781879723568.48840817014091093189630160836415"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).distance(
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578")))
        );
        
        //invalid
        
        try {
            new BigVector(8, 9, 1).distance(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector(8).distance(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().distance(new BigVector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().distance(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of midpoint.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#midpoint(BigVector)
     */
    @Test
    public void testMidpoint() throws Exception {
        //origin
        Assert.assertEquals(new BigVector("5.3333333333333333333333333333333", "6.0", "0.66666666666666666666666666666667"), new BigVector(8, 9, 1).midpoint(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("-5.3333333333333333333333333333333", "-6.0", "-0.66666666666666666666666666666667"), new BigVector(-8, -9, -1).midpoint(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), new BigVector(0, 0, 0).midpoint(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("-1.2466666666666666666666666666667", "58.706666666666666666666666666667", "-4.9333333333333333333333333333333"), new BigVector(-1.87, 88.06, -7.4).midpoint(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("1.2125074867333333333333333333333", "-5.4525030872666666666666666666667", "4.9726506906"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).midpoint(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("1.2125074867386098666666666666667", "5.4525030873316446666666666666667", "4.9726506906582986666666666666667"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).midpoint(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("0.00000000056", "0.0000000000000018493333333333333333333333333333", "0.00000000000066666666666666666666666666666667"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).midpoint(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("5.3333333333333333333333333333333", "0.66666666666666666666666666666667", "0.66666666666666666666666666666667", "-4.0", "2.0", "2.6666666666666666666666666666667", "4.6666666666666666666666666666667", "0.0", "-4.0", "0.0", "29.333333333333333333333333333333", "6.0", "-0.66666666666666666666666666666667", "-3.3333333333333333333333333333333", "5.3333333333333333333333333333333", "4.6666666666666666666666666666667", "0.0", "2.0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).midpoint(BigVector.origin(18)));
        Assert.assertEquals(new BigVector("2.0", "3.3333333333333333333333333333333"), new BigVector(3, 5).midpoint(BigVector.origin(2)));
        Assert.assertEquals(new BigVector("1.2466666666666666666666666666667"), new BigVector(1.87).midpoint(BigVector.origin(1)));
        Assert.assertEquals(new BigVector("0.0"), new BigVector(0).midpoint(BigVector.origin(1)));
        Assert.assertEquals(new BigVector(), new BigVector().midpoint(BigVector.origin(0)));
        
        //standard
        Assert.assertEquals(new BigVector("8.0", "9.0", "1.0"), new BigVector(8, 9, 1).midpoint(new BigVector(8, 9, 1)));
        Assert.assertEquals(new BigVector("-3.6666666666666666666666666666667", "-4.3333333333333333333333333333333", "1.0"), new BigVector(-8, -9, -1).midpoint(new BigVector(5, 5, 5)));
        Assert.assertEquals(new BigVector("3.3333333333333333333333333333333", "2.6666666666666666666666666666667", "1.3333333333333333333333333333333"), new BigVector(0, 0, 0).midpoint(new BigVector(10, 8, 4)));
        Assert.assertEquals(new BigVector("0.93580333333333333333333333333333", "59.124829", "-0.77633333333333333333333333333333"), new BigVector(-1.87, 88.06, -7.4).midpoint(new BigVector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new BigVector("4.2125074867333333333333333333333", "-3.7309230872666666666666666666667", "5.3615973572666666666666666666667"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).midpoint(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("4.2125074867386098666666666666667", "7.1740830873316446666666666666667", "5.3615973573249653333333333333333"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).midpoint(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("22.00000000056", "21.666666666666668516", "0.3333333333340"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).midpoint(new BigVector(66, 65, 1)));
        Assert.assertEquals(new BigVector("8.0", "1.0", "1.0", "-4.0", "4.2333333333333333333333333333333", "5.0", "5.6666666666666666666666666666667", "11.0", "-3.5066633333333333333333333333333", "2.0", "31.666666666666666666666666666667", "7.3333333333333333333333333333333", "-0.46666666666666666666666666666667", "-1.0", "5.6666666666666666666666666666667", "4.6666666666666666666666666666667", "0.96666666666666666666666666666667", "5.6666666666666666666666666666667"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).midpoint(new BigVector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new BigVector("2.3333333333333333333333333333333", "3.6666666666666666666666666666667"), new BigVector(3, 5).midpoint(new BigVector(1, 1)));
        Assert.assertEquals(new BigVector("1.9133333333333333333333333333333"), new BigVector(1.87).midpoint(new BigVector(2)));
        Assert.assertEquals(new BigVector("3.3333333333333333333333333333333"), new BigVector(0).midpoint(new BigVector(10)));
        Assert.assertEquals(new BigVector(), new BigVector().midpoint(new BigVector()));
        
        //big
        Assert.assertEquals(new BigVector(new BigDecimal("56596373634236658598127742653803000000000"), new BigDecimal("653246748668112699396275612385.66"), new BigDecimal("703323001060082276970300803793.37")),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).midpoint(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector(new BigDecimal("84894560451354987897191613980704000000000"), new BigDecimal("979870123002169049094413418578.65"), new BigDecimal("1054984501590123415455451205690.1")),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).midpoint(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector(new BigDecimal("56596373634588320098657783792288000000000"), new BigDecimal("28298186817771576047731984026298000000000"), new BigDecimal("1029946375394138626668438609986")),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).midpoint(
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578")))
        );
        
        //invalid
        
        try {
            new BigVector(8, 9, 1).midpoint(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector(8).midpoint(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().midpoint(new BigVector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().midpoint(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of average.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#average(List)
     * @see BigVector#average(BigVector...)
     * @see #testAverageList()
     * @see #testAverageArray()
     */
    @Test
    public void testAverage() throws Exception {
        BigVector[] vectorSet3 = new BigVector[] {new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)};
        BigVector[] vectorSet2 = new BigVector[] {new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)};
        BigVector[] vectorSet1 = new BigVector[] {new BigVector(8), new BigVector(0.1), new BigVector(-5.6)};
        BigVector[] vectorSet18 = new BigVector[] {new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<BigVector> vectorList3 = Arrays.asList(vectorSet3);
        List<BigVector> vectorList2 = Arrays.asList(vectorSet2);
        List<BigVector> vectorList1 = Arrays.asList(vectorSet1);
        List<BigVector> vectorList18 = Arrays.asList(vectorSet18);
        
        //list
        testAverageList();
        
        //array
        testAverageArray();
        
        //big
        Assert.assertEquals(new BigVector("56596373634236658598127742653803000000000", "653246748668112699396275612385.99", "703323001060082276970300803793.40"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).average(
                        Arrays.asList(
                                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")),
                                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690"))))
        );
        Assert.assertEquals(new BigVector("28298186817796614173927968815085000000000", "28298186817796614173927968815085000000000", "28298186817796614173927968815085000000000"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("84894560451354987897191613980704105013356")).average(
                        new BigVector(new BigDecimal("979870123002169049094413418578"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("979870123002169049094413418578")),
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("1054984501590123415455451205690"), new BigDecimal("1054984501590123415455451205690")))
        );
        
        //invalid
        
        try {
            new BigVector(8, 9, 1).average(vectorList2);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector(8).average(vectorSet3);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().average(new BigVector(8), new BigVector(0.1), new BigVector(5.6));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().average(Collections.singletonList(null));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().average(null, null, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * Helper method for JUnit test of average for list cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testAverageList() throws Exception {
        BigVector[] vectorSet3 = new BigVector[] {new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)};
        BigVector[] vectorSet2 = new BigVector[] {new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)};
        BigVector[] vectorSet1 = new BigVector[] {new BigVector(8), new BigVector(0.1), new BigVector(-5.6)};
        BigVector[] vectorSet18 = new BigVector[] {new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<BigVector> vectorList3 = Arrays.asList(vectorSet3);
        List<BigVector> vectorList2 = Arrays.asList(vectorSet2);
        List<BigVector> vectorList1 = Arrays.asList(vectorSet1);
        List<BigVector> vectorList18 = Arrays.asList(vectorSet18);
        
        //origin
        Assert.assertEquals(new BigVector("4.0", "4.5", "0.5"), new BigVector(8, 9, 1).average(Collections.singletonList(BigVector.origin(3))));
        Assert.assertEquals(new BigVector("-4.0", "-4.5", "-0.5"), new BigVector(-8, -9, -1).average(Collections.singletonList(BigVector.origin(3))));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), new BigVector(0, 0, 0).average(Collections.singletonList(BigVector.origin(3))));
        Assert.assertEquals(new BigVector("-0.935", "44.03", "-3.7"), new BigVector(-1.87, 88.06, -7.4).average(Collections.singletonList(BigVector.origin(3))));
        Assert.assertEquals(new BigVector("0.90938061505", "-4.08937731545", "3.72948801795"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).average(Collections.singletonList(BigVector.origin(3))));
        Assert.assertEquals(new BigVector("0.9093806150539574", "4.0893773154987335", "3.729488017993724"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).average(Collections.singletonList(BigVector.origin(3))));
        Assert.assertEquals(new BigVector("0.00000000042", "0.000000000000001387", "0.0000000000005"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).average(Collections.singletonList(BigVector.origin(3))));
        Assert.assertEquals(new BigVector("4.0", "0.5", "0.5", "-3.0", "1.5", "2.0", "3.5", "0.0", "-3.0", "0.0", "22.0", "4.5", "-0.5", "-2.5", "4.0", "3.5", "0.0", "1.5"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).average(Collections.singletonList(BigVector.origin(18))));
        Assert.assertEquals(new BigVector("1.5", "2.5"), new BigVector(3, 5).average(Collections.singletonList(BigVector.origin(2))));
        Assert.assertEquals(new BigVector("0.935"), new BigVector(1.87).average(Collections.singletonList(BigVector.origin(1))));
        Assert.assertEquals(new BigVector("0.0"), new BigVector(0).average(Collections.singletonList(BigVector.origin(1))));
        Assert.assertEquals(new BigVector(), new BigVector().average(Collections.singletonList(BigVector.origin(0))));
        
        //list
        Assert.assertEquals(new BigVector("2.625", "3.1075", "1.54"), new BigVector(8, 9, 1).average(vectorList3));
        Assert.assertEquals(new BigVector("-1.375", "-1.3925", "1.04"), new BigVector(-8, -9, -1).average(vectorList3));
        Assert.assertEquals(new BigVector("0.625", "0.8575", "1.29"), new BigVector(0, 0, 0).average(vectorList3));
        Assert.assertEquals(new BigVector("0.1575", "22.8725", "-0.56"), new BigVector(-1.87, 88.06, -7.4).average(vectorList3));
        Assert.assertEquals(new BigVector("1.079690307525", "-1.187188657725", "3.154744008975"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).average(vectorList3));
        Assert.assertEquals(new BigVector("1.0796903075269787", "2.90218865774936675", "3.154744008996862"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).average(vectorList3));
        Assert.assertEquals(new BigVector("0.62500000021", "0.8575000000000006935", "1.29000000000025"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).average(vectorList3));
        Assert.assertEquals(new BigVector("2.625", "1.1075", "1.54", "1.25", "6.5", "4.575", "1.375", "4.4425", "-2.35", "1.9725", "14.5", "0.725", "2.2475", "2.375", "1.75", "1.75", "1.25", "-1.025"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).average(vectorList18));
        Assert.assertEquals(new BigVector("1.375", "2.1075"), new BigVector(3, 5).average(vectorList2));
        Assert.assertEquals(new BigVector("1.0925"), new BigVector(1.87).average(vectorList1));
        Assert.assertEquals(new BigVector("0.625"), new BigVector(0).average(vectorList1));
        Assert.assertEquals(new BigVector(), new BigVector().average(Collections.emptyList()));
    }
    
    /**
     * Helper method for JUnit test of average for array cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testAverageArray() throws Exception {
        BigVector[] vectorSet3 = new BigVector[] {new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)};
        BigVector[] vectorSet2 = new BigVector[] {new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)};
        BigVector[] vectorSet1 = new BigVector[] {new BigVector(8), new BigVector(0.1), new BigVector(-5.6)};
        BigVector[] vectorSet18 = new BigVector[] {new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        
        //array
        Assert.assertEquals(new BigVector("2.625", "3.1075", "1.54"), new BigVector(8, 9, 1).average(vectorSet3));
        Assert.assertEquals(new BigVector("-1.375", "-1.3925", "1.04"), new BigVector(-8, -9, -1).average(vectorSet3));
        Assert.assertEquals(new BigVector("0.625", "0.8575", "1.29"), new BigVector(0, 0, 0).average(vectorSet3));
        Assert.assertEquals(new BigVector("0.1575", "22.8725", "-0.56"), new BigVector(-1.87, 88.06, -7.4).average(vectorSet3));
        Assert.assertEquals(new BigVector("1.079690307525", "-1.187188657725", "3.154744008975"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).average(vectorSet3));
        Assert.assertEquals(new BigVector("1.0796903075269787", "2.90218865774936675", "3.154744008996862"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).average(vectorSet3));
        Assert.assertEquals(new BigVector("0.62500000021", "0.8575000000000006935", "1.29000000000025"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).average(vectorSet3));
        Assert.assertEquals(new BigVector("2.625", "1.1075", "1.54", "1.25", "6.5", "4.575", "1.375", "4.4425", "-2.35", "1.9725", "14.5", "0.725", "2.2475", "2.375", "1.75", "1.75", "1.25", "-1.025"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).average(vectorSet18));
        Assert.assertEquals(new BigVector("1.375", "2.1075"), new BigVector(3, 5).average(vectorSet2));
        Assert.assertEquals(new BigVector("1.0925"), new BigVector(1.87).average(vectorSet1));
        Assert.assertEquals(new BigVector("0.625"), new BigVector(0).average(vectorSet1));
        Assert.assertEquals(new BigVector(), new BigVector().average(Collections.emptyList()));
        
        //set
        Assert.assertEquals(new BigVector("2.625", "3.1075", "1.54"), new BigVector(8, 9, 1).average(new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("-1.375", "-1.3925", "1.04"), new BigVector(-8, -9, -1).average(new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("0.625", "0.8575", "1.29"), new BigVector(0, 0, 0).average(new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("0.1575", "22.8725", "-0.56"), new BigVector(-1.87, 88.06, -7.4).average(new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("1.079690307525", "-1.187188657725", "3.154744008975"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).average(new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("1.0796903075269787", "2.90218865774936675", "3.154744008996862"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).average(new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("0.62500000021", "0.8575000000000006935", "1.29000000000025"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).average(new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("2.625", "1.1075", "1.54", "1.25", "6.5", "4.575", "1.375", "4.4425", "-2.35", "1.9725", "14.5", "0.725", "2.2475", "2.375", "1.75", "1.75", "1.25", "-1.025"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).average(new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)));
        Assert.assertEquals(new BigVector("1.375", "2.1075"), new BigVector(3, 5).average(new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)));
        Assert.assertEquals(new BigVector("1.0925"), new BigVector(1.87).average(new BigVector(8), new BigVector(0.1), new BigVector(-5.6)));
        Assert.assertEquals(new BigVector("0.625"), new BigVector(0).average(new BigVector(8), new BigVector(0.1), new BigVector(-5.6)));
        Assert.assertEquals(new BigVector(), new BigVector().average(Collections.emptyList()));
    }
    
    /**
     * JUnit test of dot.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dot(BigVector)
     */
    @Test
    public void testDot() throws Exception {
        //origin
        Assert.assertEquals(new BigDecimal("0"), new BigVector(8, 9, 1).dot(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(-8, -9, -1).dot(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(0, 0, 0).dot(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(-1.87, 88.06, -7.4).dot(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).dot(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).dot(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).dot(BigVector.origin(3)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).dot(BigVector.origin(18)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(3, 5).dot(BigVector.origin(2)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(1.87).dot(BigVector.origin(1)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(0).dot(BigVector.origin(1)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector().dot(BigVector.origin(0)));
        
        //standard
        Assert.assertEquals(new BigDecimal("146"), new BigVector(8, 9, 1).dot(new BigVector(8, 9, 1)));
        Assert.assertEquals(new BigDecimal("-9E+1"), new BigVector(-8, -9, -1).dot(new BigVector(5, 5, 5)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(0, 0, 0).dot(new BigVector(10, 8, 4)));
        Assert.assertEquals(new BigDecimal("5.94106852"), new BigVector(-1.87, 88.06, -7.4).dot(new BigVector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new BigDecimal("-17.16885852376491"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).dot(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigDecimal("67.3134238617006847379"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).dot(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigDecimal("5.544118031E-8"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).dot(new BigVector(66, 65, 1)));
        Assert.assertEquals(new BigDecimal("475.61994"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).dot(new BigVector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new BigDecimal("8"), new BigVector(3, 5).dot(new BigVector(1, 1)));
        Assert.assertEquals(new BigDecimal("3.74"), new BigVector(1.87).dot(new BigVector(2)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector(0).dot(new BigVector(10)));
        Assert.assertEquals(new BigDecimal("0"), new BigVector().dot(new BigVector()));
        
        //big
        Assert.assertEquals(new BigDecimal("72070863943359108751773219101711591126038.89619789779470292857166941562452938382736"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).dot(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigDecimal("7207086394228766406398212846593982952114726385120895396695987686264410823853426958.89619789779470292857166941562452938382736"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).dot(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigDecimal("172748088938203380418013402067823224821440135264786061852442876807032228"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).dot(
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578")))
        );
        
        //invalid
        
        try {
            new BigVector(8, 9, 1).dot(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector(8).dot(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().dot(new BigVector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().dot(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of normalize.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#normalize()
     */
    @Test
    public void testNormalize() throws Exception {
        //standard
        Assert.assertEquals(new BigVector("0.6620847108818943599059090009209760", "0.7448452997421311548941476260360980", "0.0827605888602367949882386251151220"), new BigVector(8, 9, 1).normalize());
        Assert.assertEquals(new BigVector("-0.6620847108818943599059090009209760", "-0.7448452997421311548941476260360980", "-0.0827605888602367949882386251151220"), new BigVector(-8, -9, -1).normalize());
        Assert.assertEquals(new BigVector("-0.02115620094155835006727277880661423", "0.99626473524792957589520903834783374", "-0.0837197256510865189827906754914146"), new BigVector(-1.87, 88.06, -7.4).normalize());
        Assert.assertEquals(new BigVector("0.1621335215973718683577236528634636616295329", "-0.7290953142407378027164366833176193224850361", "0.6649306309132061229443238148906184267387811"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).normalize());
        Assert.assertEquals(new BigVector("0.1621335215961913685377808717840092599045060408520", "0.729095314240945089951775093444810756378699825330", "0.664930630913266680300523834042213689963731325520"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).normalize());
        Assert.assertEquals(new BigVector("0.999999291378520311985079602205808", "0.0000033023786122428754112459652577606088", "0.00119047534687919084760128524072120"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).normalize());
        Assert.assertEquals(new BigVector("0.1640874614152145526782539858835680", "0.0205109326769018190847817482354460", "0.0205109326769018190847817482354460", "-0.1230655960614109145086904894126760", "0.0615327980307054572543452447063380", "0.0820437307076072763391269929417840", "0.1435765287383127335934722376481220", "0.0000000000000000000000000000000000", "-0.1230655960614109145086904894126760", "0.0000000000000000000000000000000000", "0.9024810377836800397303969223596240", "0.1845983940921163717630357341190140", "-0.0205109326769018190847817482354460", "-0.1025546633845090954239087411772300", "0.1640874614152145526782539858835680", "0.1435765287383127335934722376481220", "0.0000000000000000000000000000000000", "0.0615327980307054572543452447063380"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).normalize());
        Assert.assertEquals(new BigVector("0.514495755427526512135954665665790", "0.857492925712544186893257776109650"), new BigVector(3, 5).normalize());
        Assert.assertEquals(new BigVector(), new BigVector().normalize());
        
        //simple
        Assert.assertEquals(new BigVector("1"), new BigVector(1.87).normalize());
        Assert.assertEquals(new BigVector("1", "0"), new BigVector(1.87, 0).normalize());
        Assert.assertEquals(new BigVector("1", "0", "0"), new BigVector(1.87, 0, 0).normalize());
        Assert.assertEquals(new BigVector("0", "1", "0"), new BigVector(0, 2.7845, 0).normalize());
        Assert.assertEquals(new BigVector("0", "0", "1"), new BigVector(0, 0, 7.41001).normalize());
        
        //big
        Assert.assertEquals(new BigVector("0.999999999999999999999856173657225864532681033032606022243874363758393916", "0.000000000011542201500220259468397155071816984240073702893489654478902858", "0.000000000012426997630721403706463776234112306205024929779230935778355090"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).normalize()
        );
        Assert.assertEquals(new BigVector("0.6526520936414312796744190416791896546425692249903886088651045360069545988", "0.75330419743500213815998343499165546814782274158627837281116694", "0.081105060213674302217073111974692455354194821783351108275617870"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).normalize()
        );
        Assert.assertEquals(new BigVector("0.99999999999999999999985617365722586453269103303260602224387436232013048825864532681033032606022243874363758393916", "0.000000000011542201500220259468397155071828526441573923152958051633974674984240073702893489654478902858", "0.0000000000124269976307214037064637762341135489047880019196015821559785012306205024929779230935778355090"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).normalize()
        );
        
        //invalid
        
        try {
            Assert.assertEquals(new BigVector(0, 0, 0), new BigVector(0, 0, 0).normalize());
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
            Assert.assertEquals(new BigVector(0), new BigVector(0).normalize());
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of hypotenuse.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#hypotenuse()
     */
    @Test
    public void testHypotenuse() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("12.08304597359457206828283926680786"), new BigVector(8, 9, 1).hypotenuse());
        Assert.assertEquals(new BigDecimal("12.08304597359457206828283926680786"), new BigVector(-8, -9, -1).hypotenuse());
        Assert.assertEquals(new BigDecimal("88.39016065151143280843055901191871"), new BigVector(-1.87, 88.06, -7.4).hypotenuse());
        Assert.assertEquals(new BigDecimal("11.21767548241227820150003634614717"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).hypotenuse());
        Assert.assertEquals(new BigDecimal("11.21767548254277104270198334339978"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).hypotenuse());
        Assert.assertEquals(new BigDecimal("8.4000059524246473952867E-10"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).hypotenuse());
        Assert.assertEquals(new BigDecimal("48.75448697299562396452621555565469"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).hypotenuse());
        Assert.assertEquals(new BigDecimal("5.83095189484530047087415287754558"), new BigVector(3, 5).hypotenuse());
        
        //simple
        Assert.assertEquals(new BigDecimal("1.87"), new BigVector(1.87).hypotenuse());
        Assert.assertEquals(new BigDecimal("1.87"), new BigVector(1.87, 0).hypotenuse());
        Assert.assertEquals(new BigDecimal("1.87"), new BigVector(1.87, 0, 0).hypotenuse());
        Assert.assertEquals(new BigDecimal("2.7845"), new BigVector(0, 2.7845, 0).hypotenuse());
        Assert.assertEquals(new BigDecimal("7.41001"), new BigVector(0, 0, 7.41001).hypotenuse());
        Assert.assertEquals(new BigDecimal("0"), new BigVector(0, 0, 0).hypotenuse());
        Assert.assertEquals(new BigDecimal("0"), new BigVector(0).hypotenuse());
        Assert.assertEquals(new BigDecimal("0"), new BigVector().hypotenuse());
        
        //big
        Assert.assertEquals(new BigDecimal("84894560451354987897203824054856619533585.09662095965621946495710064564843"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).hypotenuse()
        );
        Assert.assertEquals(new BigDecimal("1.3007628609247406374838690478937"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).hypotenuse()
        );
        Assert.assertEquals(new BigDecimal("84894560451354987897203824054856619533585.09662095966935340421153498168565"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).hypotenuse()
        );
    }
    
    /**
     * JUnit test of sum.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#sum()
     */
    @Test
    public void testSum() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("18.0"), new BigVector(8, 9, 1).sum());
        Assert.assertEquals(new BigDecimal("-18.0"), new BigVector(-8, -9, -1).sum());
        Assert.assertEquals(new BigDecimal("78.79"), new BigVector(-1.87, 88.06, -7.4).sum());
        Assert.assertEquals(new BigDecimal("1.0989826351"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).sum());
        Assert.assertEquals(new BigDecimal("17.4564918970928298"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).sum());
        Assert.assertEquals(new BigDecimal("8.41002774E-10"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).sum());
        Assert.assertEquals(new BigDecimal("77.0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).sum());
        Assert.assertEquals(new BigDecimal("8.0"), new BigVector(3, 5).sum());
        
        //simple
        Assert.assertEquals(new BigDecimal("1.87"), new BigVector(1.87).sum());
        Assert.assertEquals(new BigDecimal("1.87"), new BigVector(1.87, 0).sum());
        Assert.assertEquals(new BigDecimal("1.87"), new BigVector(1.87, 0, 0).sum());
        Assert.assertEquals(new BigDecimal("2.7845"), new BigVector(0, 2.7845, 0).sum());
        Assert.assertEquals(new BigDecimal("7.41001"), new BigVector(0, 0, 7.41001).sum());
        Assert.assertEquals(new BigDecimal("0.0"), new BigVector(0, 0, 0).sum());
        Assert.assertEquals(new BigDecimal("0.0"), new BigVector(0).sum());
        Assert.assertEquals(new BigDecimal("0"), new BigVector().sum());
        
        //big
        Assert.assertEquals(new BigDecimal("84894560453389842521783906445253969637624"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).sum()
        );
        Assert.assertEquals(new BigDecimal("1.93431417767473126961187467895404105013356"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).sum()
        );
        Assert.assertEquals(new BigDecimal("84894560453389842521783906445253969637625.93431417767473126961187467895404105013356"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).sum()
        );
    }
    
    /**
     * JUnit test of squareSum.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#squareSum()
     */
    @Test
    public void testSquareSum() throws Exception {
        //standard
        Assert.assertEquals(new BigDecimal("146.00"), new BigVector(8, 9, 1).squareSum());
        Assert.assertEquals(new BigDecimal("146.00"), new BigVector(-8, -9, -1).squareSum());
        Assert.assertEquals(new BigDecimal("7812.8205"), new BigVector(-1.87, 88.06, -7.4).squareSum());
        Assert.assertEquals(new BigDecimal("125.83624322871353846963"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).squareSum());
        Assert.assertEquals(new BigDecimal("125.8362432316411911604098275728520"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).squareSum());
        Assert.assertEquals(new BigDecimal("7.05601000007695076E-19"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).squareSum());
        Assert.assertEquals(new BigDecimal("2377.00"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).squareSum());
        Assert.assertEquals(new BigDecimal("34.00"), new BigVector(3, 5).squareSum());
        
        //simple
        Assert.assertEquals(new BigDecimal("3.4969"), new BigVector(1.87).squareSum());
        Assert.assertEquals(new BigDecimal("3.4969"), new BigVector(1.87, 0).squareSum());
        Assert.assertEquals(new BigDecimal("3.4969"), new BigVector(1.87, 0, 0).squareSum());
        Assert.assertEquals(new BigDecimal("7.75344025"), new BigVector(0, 2.7845, 0).squareSum());
        Assert.assertEquals(new BigDecimal("54.9082482001"), new BigVector(0, 0, 7.41001).squareSum());
        Assert.assertEquals(new BigDecimal("0.00"), new BigVector(0, 0, 0).squareSum());
        Assert.assertEquals(new BigDecimal("0.00"), new BigVector(0).squareSum());
        Assert.assertEquals(new BigDecimal("0"), new BigVector().squareSum());
        
        //big
        Assert.assertEquals(new BigDecimal("7207086394228766406398212846593947647017119491936321920000000000000000000000000000"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).squareSum()
        );
        Assert.assertEquals(new BigDecimal("1.691984020361116153598626659821442"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).squareSum()
        );
        Assert.assertEquals(new BigDecimal("7207086394228766406398212846593947647017119491936324150000000000000000000000000000"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).squareSum()
        );
    }
    
    /**
     * JUnit test of movePointLeft.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#movePointLeft(int)
     */
    @Test
    public void testMovePointLeft() throws Exception {
        //standard
        Assert.assertEquals(new BigVector("0.008", "0.009", "0.001"), new BigVector(8, 9, 1).movePointLeft(3));
        Assert.assertEquals(new BigVector("-0.08", "-0.09", "-0.01"), new BigVector(-8, -9, -1).movePointLeft(2));
        Assert.assertEquals(new BigVector("-1870", "88060", "-7400"), new BigVector(-1.87, 88.06, -7.4).movePointLeft(-3));
        Assert.assertEquals(new BigVector("0.00018187612301", "-0.00081787546309", "0.00074589760359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).movePointLeft(4));
        Assert.assertEquals(new BigVector("0.000000018187612301079148", "0.00000008178754630997467", "0.00000007458976035987448"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).movePointLeft(8));
        Assert.assertEquals(new BigVector("0.00000000000000000000084", "0.000000000000000000000000002774", "0.0000000000000000000000010"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).movePointLeft(12));
        Assert.assertEquals(new BigVector("0.80", "0.10", "0.10", "-0.60", "0.30", "0.40", "0.70", "0.00", "-0.60", "0.00", "4.40", "0.90", "-0.10", "-0.50", "0.80", "0.70", "0.00", "0.30"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).movePointLeft(1));
        Assert.assertEquals(new BigVector("300000", "500000"), new BigVector(3, 5).movePointLeft(-5));
        
        //simple
        Assert.assertEquals(new BigVector("1.87"), new BigVector(1.87).movePointLeft(0));
        Assert.assertEquals(new BigVector("1.87", "0.0"), new BigVector(1.87, 0).movePointLeft(0));
        Assert.assertEquals(new BigVector("1.87", "0.0", "0.0"), new BigVector(1.87, 0, 0).movePointLeft(0));
        Assert.assertEquals(new BigVector("0.0", "2.7845", "0.0"), new BigVector(0, 2.7845, 0).movePointLeft(0));
        Assert.assertEquals(new BigVector("0.0", "0.0", "7.41001"), new BigVector(0, 0, 7.41001).movePointLeft(0));
        Assert.assertEquals(new BigVector("0.00", "0.00", "0.00"), new BigVector(0, 0, 0).movePointLeft(1));
        Assert.assertEquals(new BigVector("0.000000"), new BigVector(0).movePointLeft(5));
        Assert.assertEquals(new BigVector(), new BigVector().movePointLeft(1));
        
        //big
        Assert.assertEquals(new BigVector("848945604513549878971916139807041050133560000000000", "9798701230021690490944134185780000000000", "10549845015901234154554512056900000000000"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).movePointLeft(-10)
        );
        Assert.assertEquals(new BigVector("0.000000000084894560451354987897191613980704105013356", "0.0000000000979870123002169049094413418578", "0.00000000001054984501590123415455451205690"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).movePointLeft(10)
        );
        Assert.assertEquals(new BigVector("8489456045135498789719161398070410501335684894.560451354987897191613980704105013356", "97987012300216904909441341857897987.0123002169049094413418578", "105498450159012341545545120569010549.84501590123415455451205690"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).movePointLeft(-5)
        );
    }
    
    /**
     * JUnit test of movePointRight.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#movePointRight(int)
     */
    @Test
    public void testMovePointRight() throws Exception {
        //standard
        Assert.assertEquals(new BigVector("0.008", "0.009", "0.001"), new BigVector(8, 9, 1).movePointRight(-3));
        Assert.assertEquals(new BigVector("-0.08", "-0.09", "-0.01"), new BigVector(-8, -9, -1).movePointRight(-2));
        Assert.assertEquals(new BigVector("-1870", "88060", "-7400"), new BigVector(-1.87, 88.06, -7.4).movePointRight(3));
        Assert.assertEquals(new BigVector("0.00018187612301", "-0.00081787546309", "0.00074589760359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).movePointRight(-4));
        Assert.assertEquals(new BigVector("0.000000018187612301079148", "0.00000008178754630997467", "0.00000007458976035987448"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).movePointRight(-8));
        Assert.assertEquals(new BigVector("0.00000000000000000000084", "0.000000000000000000000000002774", "0.0000000000000000000000010"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).movePointRight(-12));
        Assert.assertEquals(new BigVector("0.80", "0.10", "0.10", "-0.60", "0.30", "0.40", "0.70", "0.00", "-0.60", "0.00", "4.40", "0.90", "-0.10", "-0.50", "0.80", "0.70", "0.00", "0.30"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).movePointRight(-1));
        Assert.assertEquals(new BigVector("300000", "500000"), new BigVector(3, 5).movePointRight(5));
        
        //simple
        Assert.assertEquals(new BigVector("1.87"), new BigVector(1.87).movePointRight(0));
        Assert.assertEquals(new BigVector("1.87", "0.0"), new BigVector(1.87, 0).movePointRight(0));
        Assert.assertEquals(new BigVector("1.87", "0.0", "0.0"), new BigVector(1.87, 0, 0).movePointRight(0));
        Assert.assertEquals(new BigVector("0.0", "2.7845", "0.0"), new BigVector(0, 2.7845, 0).movePointRight(0));
        Assert.assertEquals(new BigVector("0.0", "0.0", "7.41001"), new BigVector(0, 0, 7.41001).movePointRight(0));
        Assert.assertEquals(new BigVector("0.00", "0.00", "0.00"), new BigVector(0, 0, 0).movePointRight(1));
        Assert.assertEquals(new BigVector("0.0"), new BigVector(0).movePointRight(5));
        Assert.assertEquals(new BigVector(), new BigVector().movePointRight(1));
        
        //big
        Assert.assertEquals(new BigVector("848945604513549878971916139807041050133560000000000", "9798701230021690490944134185780000000000", "10549845015901234154554512056900000000000"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).movePointRight(10)
        );
        Assert.assertEquals(new BigVector("0.000000000084894560451354987897191613980704105013356", "0.0000000000979870123002169049094413418578", "0.00000000001054984501590123415455451205690"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).movePointRight(-10)
        );
        Assert.assertEquals(new BigVector("8489456045135498789719161398070410501335684894.560451354987897191613980704105013356", "97987012300216904909441341857897987.0123002169049094413418578", "105498450159012341545545120569010549.84501590123415455451205690"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).movePointRight(5)
        );
    }
    
    /**
     * JUnit test of stripTrailingZeros.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#stripTrailingZeros()
     */
    @Test
    public void testStripTrailingZeros() throws Exception {
        //standard
        Assert.assertEquals(new BigVector("8", "9", "1"), new BigVector(8.00, 9.000, 1.0).stripTrailingZeros());
        Assert.assertEquals(new BigVector("-8", "-9", "-1"), new BigVector(-8.00, -9.000, -1.0).stripTrailingZeros());
        Assert.assertEquals(new BigVector("-1.87", "88.06", "-7.4"), new BigVector(-1.870, 88.060000, -7.400).stripTrailingZeros());
        Assert.assertEquals(new BigVector("1.8187612301", "-8.1787546309", "7.4589760359"), new BigVector(1.8187612301000, -8.178754630900, 7.458976035900000000).stripTrailingZeros());
        Assert.assertEquals(new BigVector("1.8187612301079148", "8.178754630997467", "7.458976035987448"), new BigVector(1.8187612301079148755000, 8.17875463099746649010, 7.4589760359874486477300).stripTrailingZeros());
        Assert.assertEquals(new BigVector("0.00000000084", "0.000000000000002774", "0.000000000001"), new BigVector(0.00000000084000000000, 0.0000000000000027740000000000, 0.000000000001000000000).stripTrailingZeros());
        Assert.assertEquals(new BigVector("8", "1", "1", "-6", "3", "4", "7", "0", "-6", "0", "44", "9", "-1", "-5", "8", "7", "0", "3"), new BigVector(8.0, 1.0, 1.0000, -6.0, 3.00, 4.0000000000, 7.000, 0.0, -6.0, 0.000, 44.00, 9.00, -1.0000, -5.0000, 8.0, 7.000, 0.0, 3.000000).stripTrailingZeros());
        Assert.assertEquals(new BigVector("3", "5"), new BigVector(3.000, 5.0).stripTrailingZeros());
        
        //simple
        Assert.assertEquals(new BigVector("1.87"), new BigVector(1.870).stripTrailingZeros());
        Assert.assertEquals(new BigVector("1.87", "0"), new BigVector(1.870, 0.0).stripTrailingZeros());
        Assert.assertEquals(new BigVector("1.87", "0", "0"), new BigVector(1.870, 0.0, 0.0).stripTrailingZeros());
        Assert.assertEquals(new BigVector("0", "2.7845", "0"), new BigVector(0.0, 2.78450, 0.0).stripTrailingZeros());
        Assert.assertEquals(new BigVector("0", "0", "7.41001"), new BigVector(0.0, 0.0, 7.410010).stripTrailingZeros());
        Assert.assertEquals(new BigVector("0", "0", "0"), new BigVector(0, 0, 0).stripTrailingZeros());
        Assert.assertEquals(new BigVector("0"), new BigVector(0).stripTrailingZeros());
        Assert.assertEquals(new BigVector(), new BigVector().stripTrailingZeros());
        
        //big
        Assert.assertEquals(new BigVector("84894560451354987897191613980704105013356000", "979870123002169049094413418578000", "1054984501590123415455451205690000"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356000"), new BigDecimal("979870123002169049094413418578000"), new BigDecimal("1054984501590123415455451205690000")).stripTrailingZeros()
        );
        Assert.assertEquals(new BigVector("0.84894560451354987897191613980704105013356", "0.979870123002169049094413418578", "0.1054984501590123415455451205690"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356000"), new BigDecimal("0.979870123002169049094413418578000"), new BigDecimal("0.1054984501590123415455451205690000")).stripTrailingZeros()
        );
        Assert.assertEquals(new BigVector("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356", "979870123002169049094413418578.979870123002169049094413418578", "1054984501590123415455451205690.105498450159012341545545120569"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356000"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578000"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690000")).stripTrailingZeros()
        );
    }
    
    /**
     * JUnit test of plus.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#plus(BigVector)
     */
    @Test
    public void testPlus() throws Exception {
        //identity
        Assert.assertEquals(new BigVector("8.0", "9.0", "1.0"), new BigVector(8, 9, 1).plus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("-8.0", "-9.0", "-1.0"), new BigVector(-8, -9, -1).plus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), new BigVector(0, 0, 0).plus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("-1.87", "88.06", "-7.4"), new BigVector(-1.87, 88.06, -7.4).plus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("1.8187612301", "-8.1787546309", "7.4589760359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).plus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("1.8187612301079148", "8.178754630997467", "7.458976035987448"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).plus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("0.00000000084", "0.000000000000002774", "0.0000000000010"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).plus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("8.0", "1.0", "1.0", "-6.0", "3.0", "4.0", "7.0", "0.0", "-6.0", "0.0", "44.0", "9.0", "-1.0", "-5.0", "8.0", "7.0", "0.0", "3.0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).plus(BigVector.origin(18)));
        Assert.assertEquals(new BigVector("3.0", "5.0"), new BigVector(3, 5).plus(BigVector.origin(2)));
        Assert.assertEquals(new BigVector("1.87"), new BigVector(1.87).plus(BigVector.origin(1)));
        Assert.assertEquals(new BigVector("0.0"), new BigVector(0).plus(BigVector.origin(1)));
        Assert.assertEquals(new BigVector(), new BigVector().plus(BigVector.origin(0)));
        
        //standard
        Assert.assertEquals(new BigVector("16.0", "18.0", "2.0"), new BigVector(8, 9, 1).plus(new BigVector(8, 9, 1)));
        Assert.assertEquals(new BigVector("-3.0", "-4.0", "4.0"), new BigVector(-8, -9, -1).plus(new BigVector(5, 5, 5)));
        Assert.assertEquals(new BigVector("10.0", "8.0", "4.0"), new BigVector(0, 0, 0).plus(new BigVector(10, 8, 4)));
        Assert.assertEquals(new BigVector("4.67741", "89.314487", "5.071"), new BigVector(-1.87, 88.06, -7.4).plus(new BigVector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new BigVector("10.8187612301", "-3.0140146309", "8.6258160359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).plus(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("10.8187612301079148", "13.343494630997467", "8.625816035987448"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).plus(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("66.00000000084", "65.000000000000002774", "1.0000000000010"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).plus(new BigVector(66, 65, 1)));
        Assert.assertEquals(new BigVector("16.0", "2.0", "2.0", "-6.0", "9.7", "11.0", "10.0", "33.0", "-4.51999", "6.0", "51.0", "13.0", "-0.4", "2.0", "9.0", "7.0", "2.9", "14.0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).plus(new BigVector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new BigVector("4.0", "6.0"), new BigVector(3, 5).plus(new BigVector(1, 1)));
        Assert.assertEquals(new BigVector("3.87"), new BigVector(1.87).plus(new BigVector(2)));
        Assert.assertEquals(new BigVector("10.0"), new BigVector(0).plus(new BigVector(10)));
        Assert.assertEquals(new BigVector(), new BigVector().plus(new BigVector()));
        
        //big
        Assert.assertEquals(new BigVector("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356", "979870123002169049094413418578.979870123002169049094413418578", "1054984501590123415455451205690.1054984501590123415455451205690"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).plus(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector("169789120902709975794383227961408210026712.84894560451354987897191613980704105013356", "1959740246004338098188826837156.979870123002169049094413418578", "2109969003180246830910902411380.1054984501590123415455451205690"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).plus(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector("84894560452409972398781737396159556219046", "84894560452334858020193783029798518431934", "2034854624592292464549864624268"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).plus(
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578")))
        );
        
        //invalid
        
        try {
            new BigVector(8, 9, 1).plus(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector(8).plus(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().plus(new BigVector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().plus(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of minus.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#minus(BigVector)
     */
    @Test
    public void testMinus() throws Exception {
        //identity
        Assert.assertEquals(new BigVector("8.0", "9.0", "1.0"), new BigVector(8, 9, 1).minus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("-8.0", "-9.0", "-1.0"), new BigVector(-8, -9, -1).minus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), new BigVector(0, 0, 0).minus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("-1.87", "88.06", "-7.4"), new BigVector(-1.87, 88.06, -7.4).minus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("1.8187612301", "-8.1787546309", "7.4589760359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).minus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("1.8187612301079148", "8.178754630997467", "7.458976035987448"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).minus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("0.00000000084", "0.000000000000002774", "0.0000000000010"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).minus(BigVector.origin(3)));
        Assert.assertEquals(new BigVector("8.0", "1.0", "1.0", "-6.0", "3.0", "4.0", "7.0", "0.0", "-6.0", "0.0", "44.0", "9.0", "-1.0", "-5.0", "8.0", "7.0", "0.0", "3.0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).minus(BigVector.origin(18)));
        Assert.assertEquals(new BigVector("3.0", "5.0"), new BigVector(3, 5).minus(BigVector.origin(2)));
        Assert.assertEquals(new BigVector("1.87"), new BigVector(1.87).minus(BigVector.origin(1)));
        Assert.assertEquals(new BigVector("0.0"), new BigVector(0).minus(BigVector.origin(1)));
        Assert.assertEquals(new BigVector(), new BigVector().minus(BigVector.origin(0)));
        
        //standard
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), new BigVector(8, 9, 1).minus(new BigVector(8, 9, 1)));
        Assert.assertEquals(new BigVector("-13.0", "-14.0", "-6.0"), new BigVector(-8, -9, -1).minus(new BigVector(5, 5, 5)));
        Assert.assertEquals(new BigVector("-10.0", "-8.0", "-4.0"), new BigVector(0, 0, 0).minus(new BigVector(10, 8, 4)));
        Assert.assertEquals(new BigVector("-8.41741", "86.805513", "-19.871"), new BigVector(-1.87, 88.06, -7.4).minus(new BigVector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new BigVector("-7.1812387699", "-13.3434946309", "6.2921360359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).minus(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("-7.1812387698920852", "3.014014630997467", "6.292136035987448"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).minus(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("-65.99999999916", "-64.999999999999997226", "-0.9999999999990"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).minus(new BigVector(66, 65, 1)));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0", "-6.0", "-3.7", "-3.0", "4.0", "-33.0", "-7.48001", "-6.0", "37.0", "5.0", "-1.6", "-12.0", "7.0", "7.0", "-2.9", "-8.0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).minus(new BigVector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new BigVector("2.0", "4.0"), new BigVector(3, 5).minus(new BigVector(1, 1)));
        Assert.assertEquals(new BigVector("-0.13"), new BigVector(1.87).minus(new BigVector(2)));
        Assert.assertEquals(new BigVector("-10.0"), new BigVector(0).minus(new BigVector(10)));
        Assert.assertEquals(new BigVector(), new BigVector().minus(new BigVector()));
        
        //big
        Assert.assertEquals(new BigVector("84894560451354987897191613980704105013355.15105439548645012102808386019295894986644", "979870123002169049094413418577.020129876997830950905586581422", "1054984501590123415455451205689.8945015498409876584544548794310"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).minus(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector("0.84894560451354987897191613980704105013356", "0.979870123002169049094413418578", "0.1054984501590123415455451205690"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).minus(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector("84894560450300003395601490565248653807666", "-84894560450375117774189444931609691594778", "75114378587954366361037787112"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).minus(
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578")))
        );
        
        //invalid
        
        try {
            new BigVector(8, 9, 1).minus(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector(8).minus(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().minus(new BigVector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().minus(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of times.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#times(BigVector)
     */
    @Test
    public void testTimes() throws Exception {
        //identity
        Assert.assertEquals(new BigVector("8.0", "9.0", "1.0"), new BigVector(8, 9, 1).times(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("-8.0", "-9.0", "-1.0"), new BigVector(-8, -9, -1).times(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), new BigVector(0, 0, 0).times(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("-1.87", "88.06", "-7.4"), new BigVector(-1.87, 88.06, -7.4).times(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("1.8187612301", "-8.1787546309", "7.4589760359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).times(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("1.8187612301079148", "8.178754630997467", "7.458976035987448"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).times(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("0.00000000084", "0.000000000000002774", "0.0000000000010"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).times(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("8.0", "1.0", "1.0", "-6.0", "3.0", "4.0", "7.0", "0.0", "-6.0", "0.0", "44.0", "9.0", "-1.0", "-5.0", "8.0", "7.0", "0.0", "3.0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).times(BigVector.unit(18)));
        Assert.assertEquals(new BigVector("3.0", "5.0"), new BigVector(3, 5).times(BigVector.unit(2)));
        Assert.assertEquals(new BigVector("1.87"), new BigVector(1.87).times(BigVector.unit(1)));
        Assert.assertEquals(new BigVector("0.0"), new BigVector(0).times(BigVector.unit(1)));
        Assert.assertEquals(new BigVector(), new BigVector().times(BigVector.unit(0)));
        
        //standard
        Assert.assertEquals(new BigVector("64.00", "81.00", "1.00"), new BigVector(8, 9, 1).times(new BigVector(8, 9, 1)));
        Assert.assertEquals(new BigVector("-40.00", "-45.00", "-5.00"), new BigVector(-8, -9, -1).times(new BigVector(5, 5, 5)));
        Assert.assertEquals(new BigVector("0.00", "0.00", "0.00"), new BigVector(0, 0, 0).times(new BigVector(10, 8, 4)));
        Assert.assertEquals(new BigVector("-12.2436567", "110.47012522", "-92.2854"), new BigVector(-1.87, 88.06, -7.4).times(new BigVector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new BigVector("16.36885107090", "-42.241141192394466", "8.703431597729556"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).times(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("16.36885107097123320", "42.24114119289785771358", "8.70343159783159382432"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).times(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("0.000000055440", "0.0000000000001803100", "0.00000000000100"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).times(new BigVector(66, 65, 1)));
        Assert.assertEquals(new BigVector("64.00", "1.00", "1.00", "0.00", "20.10", "28.00", "21.00", "0.00", "-8.880060", "0.00", "308.00", "36.00", "-0.60", "-35.00", "8.00", "0.00", "0.00", "33.00"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).times(new BigVector(8, 1, 1, 0, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 0, 2.9, 11)));
        Assert.assertEquals(new BigVector("3.00", "5.00"), new BigVector(3, 5).times(new BigVector(1, 1)));
        Assert.assertEquals(new BigVector("3.740"), new BigVector(1.87).times(new BigVector(2)));
        Assert.assertEquals(new BigVector("0.00"), new BigVector(0).times(new BigVector(10)));
        Assert.assertEquals(new BigVector(), new BigVector().times(new BigVector()));
        
        //big
        Assert.assertEquals(new BigVector("72070863942287664063961397088374353050975.34822320630079833053956503593052938382736", "960145457952285901805876534923.724665859220278251584635542084", "111299229859536111531361540139.8233088322736263464474688376100"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).times(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector("7207086394228766406396139708837435305097606893184572367497117917900681427291433711.34822320630079833053956503593052938382736", "960145457952285901805876534924684811317172564153390512077007.724665859220278251584635542084", "1112992298595361115313615401398344387552595799576006049916239.8233088322736263464474688376100"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).times(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector("89562445545485344647722610926084347839793106902825008354205363753195640", "83185643391684287976952294901873569658760968502494728300232848308527768", "1033747793338496239865307322886059859466325198004664745308820"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).times(
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578")))
        );
        
        //invalid
        
        try {
            new BigVector(8, 9, 1).times(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector(8).times(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().times(new BigVector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().times(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of scale.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#scale(BigDecimal)
     * @see BigVector#scale(double)
     */
    @Test
    public void testScale() throws Exception {
        //identity
        Assert.assertEquals(new BigVector("8.0", "9.0", "1.0"), new BigVector(8, 9, 1).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("-8.0", "-9.0", "-1.0"), new BigVector(-8, -9, -1).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), new BigVector(0, 0, 0).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("-1.87", "88.06", "-7.4"), new BigVector(-1.87, 88.06, -7.4).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("1.8187612301", "-8.1787546309", "7.4589760359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("1.8187612301079148", "8.178754630997467", "7.458976035987448"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("0.00000000084", "0.000000000000002774", "0.0000000000010"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("8.0", "1.0", "1.0", "-6.0", "3.0", "4.0", "7.0", "0.0", "-6.0", "0.0", "44.0", "9.0", "-1.0", "-5.0", "8.0", "7.0", "0.0", "3.0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("3.0", "5.0"), new BigVector(3, 5).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("1.87"), new BigVector(1.87).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector("0.0"), new BigVector(0).scale(BigDecimal.ONE));
        Assert.assertEquals(new BigVector(), new BigVector().scale(BigDecimal.ONE));
        
        //identity, double
        Assert.assertEquals(new BigVector("8.00", "9.00", "1.00"), new BigVector(8, 9, 1).scale(1));
        Assert.assertEquals(new BigVector("-8.00", "-9.00", "-1.00"), new BigVector(-8, -9, -1).scale(1));
        Assert.assertEquals(new BigVector("0.00", "0.00", "0.00"), new BigVector(0, 0, 0).scale(1));
        Assert.assertEquals(new BigVector("-1.870", "88.060", "-7.40"), new BigVector(-1.87, 88.06, -7.4).scale(1));
        Assert.assertEquals(new BigVector("1.81876123010", "-8.17875463090", "7.45897603590"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).scale(1));
        Assert.assertEquals(new BigVector("1.81876123010791480", "8.1787546309974670", "7.4589760359874480"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).scale(1));
        Assert.assertEquals(new BigVector("0.000000000840", "0.0000000000000027740", "0.00000000000100"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).scale(1));
        Assert.assertEquals(new BigVector("8.00", "1.00", "1.00", "-6.00", "3.00", "4.00", "7.00", "0.00", "-6.00", "0.00", "44.00", "9.00", "-1.00", "-5.00", "8.00", "7.00", "0.00", "3.00"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).scale(1));
        Assert.assertEquals(new BigVector("3.00", "5.00"), new BigVector(3, 5).scale(1));
        Assert.assertEquals(new BigVector("1.870"), new BigVector(1.87).scale(1));
        Assert.assertEquals(new BigVector("0.00"), new BigVector(0).scale(1));
        Assert.assertEquals(new BigVector(), new BigVector().scale(1));
        
        //standard
        Assert.assertEquals(new BigVector("64.0", "72.0", "8.0"), new BigVector(8, 9, 1).scale(BigDecimal.valueOf(8)));
        Assert.assertEquals(new BigVector("-48.0", "-54.0", "-6.0"), new BigVector(-8, -9, -1).scale(BigDecimal.valueOf(6)));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), new BigVector(0, 0, 0).scale(BigDecimal.valueOf(-3)));
        Assert.assertEquals(new BigVector("5.888256", "-277.283328", "23.30112"), new BigVector(-1.87, 88.06, -7.4).scale(BigDecimal.valueOf(-3.1488)));
        Assert.assertEquals(new BigVector("16.65021361507259301", "-74.87404583737569309", "68.28468866175133359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).scale(BigDecimal.valueOf(9.1547001)));
        Assert.assertEquals(new BigVector("1.8242175137982385444", "8.203290894890459401", "7.481352964095410344"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).scale(BigDecimal.valueOf(1.003)));
        Assert.assertEquals(new BigVector("0.00084000420", "0.000000002774013870", "0.0000010000050"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).scale(BigDecimal.valueOf(1000005)));
        Assert.assertEquals(new BigVector("64.80", "8.10", "8.10", "-48.60", "24.30", "32.40", "56.70", "0.00", "-48.60", "0.00", "356.40", "72.90", "-8.10", "-40.50", "64.80", "56.70", "0.00", "24.30"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).scale(BigDecimal.valueOf(8.1)));
        Assert.assertEquals(new BigVector("-23.310", "-38.850"), new BigVector(3, 5).scale(BigDecimal.valueOf(-7.77)));
        Assert.assertEquals(new BigVector("3.74"), new BigVector(1.87).scale(BigDecimal.valueOf(2)));
        Assert.assertEquals(new BigVector("0.00"), new BigVector(0).scale(BigDecimal.valueOf(3.5)));
        Assert.assertEquals(new BigVector(), new BigVector().scale(BigDecimal.valueOf(11.91)));
        
        //standard, double
        Assert.assertEquals(new BigVector("64.00", "72.00", "8.00"), new BigVector(8, 9, 1).scale(8));
        Assert.assertEquals(new BigVector("-48.00", "-54.00", "-6.00"), new BigVector(-8, -9, -1).scale(6));
        Assert.assertEquals(new BigVector("0.00", "0.00", "0.00"), new BigVector(0, 0, 0).scale(-3));
        Assert.assertEquals(new BigVector("5.888256", "-277.283328", "23.30112"), new BigVector(-1.87, 88.06, -7.4).scale(-3.1488));
        Assert.assertEquals(new BigVector("16.65021361507259301", "-74.87404583737569309", "68.28468866175133359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).scale(9.1547001));
        Assert.assertEquals(new BigVector("1.8242175137982385444", "8.203290894890459401", "7.481352964095410344"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).scale(1.003));
        Assert.assertEquals(new BigVector("0.000840004200", "0.0000000027740138700", "0.00000100000500"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).scale(1000005));
        Assert.assertEquals(new BigVector("64.80", "8.10", "8.10", "-48.60", "24.30", "32.40", "56.70", "0.00", "-48.60", "0.00", "356.40", "72.90", "-8.10", "-40.50", "64.80", "56.70", "0.00", "24.30"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).scale(8.1));
        Assert.assertEquals(new BigVector("-23.310", "-38.850"), new BigVector(3, 5).scale(-7.77));
        Assert.assertEquals(new BigVector("3.740"), new BigVector(1.87).scale(2));
        Assert.assertEquals(new BigVector("0.00"), new BigVector(0).scale(3.5));
        Assert.assertEquals(new BigVector(), new BigVector().scale(11.91));
        
        //big
        Assert.assertEquals(new BigVector("35568968847196364708340955703115515218805.922089721373956224174881824", "410544205589397553700937529458.727991692650908839025998512", "442015491591312642336694318859.692023312413850441518363760"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).scale(new BigDecimal("0.418978184916542015485418904"))
        );
        Assert.assertEquals(new BigVector("161081947747451218052244550191638.94661915561316774580396958476225769453020", "185924029894903289435894344949149.453890508504116474878448062010", "20017649830095652394255597878154.0436202499952479791886282986050"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).scale(new BigDecimal("189743544098743509065431697817045"))
        );
        Assert.assertEquals(new BigVector("83836829779039439609411684129543613123506256501831078632540937946.3045545866866350541813353328823814484382946325915055540", "967661582449339543978284136576148705306705987693025886.89389693290642425664814904760275732175186270", "1041840085031316739443608710470666966923199222216570947.359535050111791469609054262658648848799383350"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).scale(new BigDecimal("987540654351798743246574.04897815464215"))
        );
        
        //big, double
        Assert.assertEquals(new BigVector("733888006733828463874852345378992776608958.6132", "8470683252316850778706475679581.2366", "9120024520896139889587739037828.3430"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).scale(8.6447)
        );
        Assert.assertEquals(new BigVector("127.73744932873579608938833389220623864939623896", "147.437137927644368141040009439757348", "15.8739298016259509829919921115351540"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).scale(150.466)
        );
        Assert.assertEquals(new BigVector("-13277509254591920107120768426582122024.089011175092545919201071207684265821220240888784", "-153251687237539239278366258.6657524516872375392392783662586655992", "-164999576048695302177232568.56993249995760486953021772325685699160"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).scale(-.0001564)
        );
        
        //invalid
        try {
            new BigVector(1).scale(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of dividedBy.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dividedBy(BigVector)
     */
    @Test
    public void testDividedBy() throws Exception {
        //identity
        Assert.assertEquals(new BigVector("8.0", "9.0", "1.0"), new BigVector(8, 9, 1).dividedBy(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("-8.0", "-9.0", "-1.0"), new BigVector(-8, -9, -1).dividedBy(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), new BigVector(0, 0, 0).dividedBy(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("-1.87", "88.06", "-7.4"), new BigVector(-1.87, 88.06, -7.4).dividedBy(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("1.8187612301", "-8.1787546309", "7.4589760359"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).dividedBy(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("1.8187612301079148", "8.178754630997467", "7.458976035987448"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).dividedBy(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("0.00000000084", "0.000000000000002774", "0.0000000000010"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).dividedBy(BigVector.unit(3)));
        Assert.assertEquals(new BigVector("8.0", "1.0", "1.0", "-6.0", "3.0", "4.0", "7.0", "0.0", "-6.0", "0.0", "44.0", "9.0", "-1.0", "-5.0", "8.0", "7.0", "0.0", "3.0"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).dividedBy(BigVector.unit(18)));
        Assert.assertEquals(new BigVector("3.0", "5.0"), new BigVector(3, 5).dividedBy(BigVector.unit(2)));
        Assert.assertEquals(new BigVector("1.87"), new BigVector(1.87).dividedBy(BigVector.unit(1)));
        Assert.assertEquals(new BigVector("0.0"), new BigVector(0).dividedBy(BigVector.unit(1)));
        Assert.assertEquals(new BigVector(), new BigVector().dividedBy(BigVector.unit(0)));
        
        //standard
        Assert.assertEquals(new BigVector("1", "1", "1"), new BigVector(8, 9, 1).dividedBy(new BigVector(8, 9, 1)));
        Assert.assertEquals(new BigVector("-1.6", "-1.8", "-0.2"), new BigVector(-8, -9, -1).dividedBy(new BigVector(5, 5, 5)));
        Assert.assertEquals(new BigVector("0", "0", "0"), new BigVector(0, 0, 0).dividedBy(new BigVector(10, 8, 4)));
        Assert.assertEquals(new BigVector("-0.28560911872022677669490684102569", "70.196024350989687418044188580671", "-0.59337663379039371341512308555850"), new BigVector(-1.87, 88.06, -7.4).dividedBy(new BigVector(6.54741, 1.254487, 12.471)));
        Assert.assertEquals(new BigVector("0.20208458112222222222222222222222", "-1.5835752876040226613537177089263", "6.3924582941105892838778238661685"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).dividedBy(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("0.20208458112310164444444444444444", "1.5835752876228942792860821648331", "6.3924582941855335778684309759693"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).dividedBy(new BigVector(9, 5.16474, 1.16684)));
        Assert.assertEquals(new BigVector("0.000000000012727272727272727272727272727273", "0.000000000000000042676923076923076923076923076923", "0.000000000001"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).dividedBy(new BigVector(66, 65, 1)));
        Assert.assertEquals(new BigVector("1", "1", "1", "-0.74074074074074074074074074074074", "0.44776119402985074626865671641791", "0.57142857142857142857142857142857", "2.3333333333333333333333333333333", "0", "-4.0540266619820136350430064661725", "0", "6.2857142857142857142857142857143", "2.25", "-1.6666666666666666666666666666667", "-0.71428571428571428571428571428571", "8", "1.75", "0", "0.27272727272727272727272727272727"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).dividedBy(new BigVector(8, 1, 1, 8.1, 6.7, 7, 3, 33, 1.48001, 6, 7, 4, 0.6, 7, 1, 4, 2.9, 11)));
        Assert.assertEquals(new BigVector("3", "5"), new BigVector(3, 5).dividedBy(new BigVector(1, 1)));
        Assert.assertEquals(new BigVector("0.935"), new BigVector(1.87).dividedBy(new BigVector(2)));
        Assert.assertEquals(new BigVector("0"), new BigVector(0).dividedBy(new BigVector(10)));
        Assert.assertEquals(new BigVector(), new BigVector().dividedBy(new BigVector()));
        
        //big
        Assert.assertEquals(new BigVector("100000000000000000000000000000000000000000", "1000000000000000000000000000000", "10000000000000000000000000000000"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).dividedBy(
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector("1.0000000000000000000000000000000", "1.000000000000000000000000000001", "1.0000000000000000000000000000001"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).dividedBy(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector("80469959817.796205110269008043134", "0.000000000011542201500220259468398815144446", "1.0766574843182437684285811106726"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).dividedBy(
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578")))
        );
        
        //invalid
        
        try {
            new BigVector(8, 9, 1).dividedBy(new BigVector(1, 1, 0));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector(8, 9, 1).dividedBy(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector(8).dividedBy(new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().dividedBy(new BigVector(1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            new BigVector().dividedBy(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
    }
    
    /**
     * JUnit test of round.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#round()
     */
    @Test
    public void testRound() throws Exception {
        //standard
        Assert.assertEquals(new BigVector("8", "9", "1"), new BigVector(8, 9, 1).round());
        Assert.assertEquals(new BigVector("-8", "-9", "-1"), new BigVector(-8, -9, -1).round());
        Assert.assertEquals(new BigVector("0", "0", "0"), new BigVector(0, 0, 0).round());
        Assert.assertEquals(new BigVector("-2", "88", "-7"), new BigVector(-1.87, 88.06, -7.4).round());
        Assert.assertEquals(new BigVector("2", "-8", "7"), new BigVector(1.8187612301, -8.1787546309, 7.4589760359).round());
        Assert.assertEquals(new BigVector("2", "8", "7"), new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).round());
        Assert.assertEquals(new BigVector("0", "0", "0"), new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).round());
        Assert.assertEquals(new BigVector("8", "1", "1", "-6", "3", "4", "7", "0", "-6", "0", "44", "9", "-1", "-5", "8", "7", "0", "3"), new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).round());
        Assert.assertEquals(new BigVector("3", "5"), new BigVector(3, 5).round());
        Assert.assertEquals(new BigVector("2"), new BigVector(1.87).round());
        Assert.assertEquals(new BigVector("0"), new BigVector(0).round());
        Assert.assertEquals(new BigVector(), new BigVector().round());
        
        //big
        Assert.assertEquals(new BigVector("84894560451354987897191613980704105013356", "979870123002169049094413418578", "1054984501590123415455451205690"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).round()
        );
        Assert.assertEquals(new BigVector("1", "1", "0"),
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).round()
        );
        Assert.assertEquals(new BigVector("84894560451354987897191613980704105013357", "979870123002169049094413418579", "1054984501590123415455451205690"),
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).round()
        );
    }
    
    /**
     * JUnit test of redim.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#redim(int)
     */
    @Test
    public void testRedim() throws Exception {
        BigVector vector = new BigVector(BigDecimal.valueOf(5), BigDecimal.valueOf(1.8744), BigDecimal.valueOf(11.001), BigDecimal.valueOf(8));
        
        //standard
        vector.redim(3);
        Assert.assertEquals(new BigVector("5", "1.8744", "11.001"), vector);
        vector.redim(2);
        Assert.assertEquals(new BigVector("5", "1.8744"), vector);
        vector.redim(8);
        Assert.assertEquals(new BigVector("5", "1.8744", "0", "0", "0", "0", "0", "0"), vector);
        vector.redim(1);
        Assert.assertEquals(new BigVector("5"), vector);
        vector.redim(0);
        Assert.assertEquals(new BigVector(), vector);
        vector.redim(1);
        Assert.assertEquals(new BigVector("0"), vector);
        
        //invalid
        vector.redim(-1);
        Assert.assertEquals(new BigVector(), vector);
    }
    
    /**
     * JUnit test of subVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#subVector(int, int)
     * @see BigVector#subVector(int)
     */
    @Test
    public void testSubVector() throws Exception {
        BigVector vector = new BigVector(5, 1.8744, 11.001, 8, 1.4, 7.5, 3, 1);
        BigVector vector2 = new BigVector("41984891405154418948887987", "19801604815.46898715", "4940545409541984843864510364.15", "87085660054654987804054", "89745049146501516040549784", "321023015049487984980654650", "31597987188746550098489984040", "89489708432040549840459070944");
        
        //standard
        Assert.assertEquals(new BigVector(11.001), vector.subVector(2, 3));
        Assert.assertEquals(new BigVector(11.001, 8, 1.4), vector.subVector(2, 5));
        Assert.assertEquals(new BigVector(5, 1.8744), vector.subVector(0, 2));
        Assert.assertEquals(new BigVector(1.8744), vector.subVector(1, 2));
        Assert.assertEquals(new BigVector(1.4, 7.5, 3), vector.subVector(4, 7));
        Assert.assertEquals(new BigVector(7.5, 3, 1), vector.subVector(5, 8));
        Assert.assertEquals(new BigVector(5, 1.8744, 11.001, 8, 1.4, 7.5, 3, 1), vector.subVector(0, 8));
        
        //to end
        Assert.assertEquals(new BigVector(3, 1), vector.subVector(6));
        Assert.assertEquals(new BigVector(7.5, 3, 1), vector.subVector(5));
        Assert.assertEquals(new BigVector(11.001, 8, 1.4, 7.5, 3, 1), vector.subVector(2));
        Assert.assertEquals(new BigVector(5, 1.8744, 11.001, 8, 1.4, 7.5, 3, 1), vector.subVector(0));
        
        //big
        Assert.assertEquals(new BigVector("4940545409541984843864510364.15"), vector2.subVector(2, 3));
        Assert.assertEquals(new BigVector("4940545409541984843864510364.15", "87085660054654987804054", "89745049146501516040549784"), vector2.subVector(2, 5));
        Assert.assertEquals(new BigVector("41984891405154418948887987", "19801604815.46898715"), vector2.subVector(0, 2));
        Assert.assertEquals(new BigVector("19801604815.46898715"), vector2.subVector(1, 2));
        Assert.assertEquals(new BigVector("89745049146501516040549784", "321023015049487984980654650", "31597987188746550098489984040"), vector2.subVector(4, 7));
        Assert.assertEquals(new BigVector("321023015049487984980654650", "31597987188746550098489984040", "89489708432040549840459070944"), vector2.subVector(5, 8));
        Assert.assertEquals(new BigVector("41984891405154418948887987", "19801604815.46898715", "4940545409541984843864510364.15", "87085660054654987804054", "89745049146501516040549784", "321023015049487984980654650", "31597987188746550098489984040", "89489708432040549840459070944"), vector2.subVector(0, 8));
        
        //big, to end
        Assert.assertEquals(new BigVector("31597987188746550098489984040", "89489708432040549840459070944"), vector2.subVector(6));
        Assert.assertEquals(new BigVector("321023015049487984980654650", "31597987188746550098489984040", "89489708432040549840459070944"), vector2.subVector(5));
        Assert.assertEquals(new BigVector("4940545409541984843864510364.15", "87085660054654987804054", "89745049146501516040549784", "321023015049487984980654650", "31597987188746550098489984040", "89489708432040549840459070944"), vector2.subVector(2));
        Assert.assertEquals(new BigVector("41984891405154418948887987", "19801604815.46898715", "4940545409541984843864510364.15", "87085660054654987804054", "89745049146501516040549784", "321023015049487984980654650", "31597987188746550098489984040", "89489708432040549840459070944"), vector2.subVector(0));
        
        //invalid
        
        try {
            vector.subVector(5, 9);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.subVector(-1, 5);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.subVector(6, 4);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.subVector(-10);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.subVector(84);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector2.subVector(5, 9);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector2.subVector(-1, 5);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector2.subVector(6, 4);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector2.subVector(-10);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector2.subVector(84);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
    
    /**
     * JUnit test of getDimensionality.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getDimensionality()
     */
    @Test
    public void testGetDimensionality() throws Exception {
        //standard
        Assert.assertEquals(3, new BigVector(8, 9, 1).getDimensionality());
        Assert.assertEquals(3, new BigVector(-8, -9, -1).getDimensionality());
        Assert.assertEquals(3, new BigVector(0, 0, 0).getDimensionality());
        Assert.assertEquals(3, new BigVector(-1.87, 88.06, -7.4).getDimensionality());
        Assert.assertEquals(3, new BigVector(1.8187612301, -8.1787546309, 7.4589760359).getDimensionality());
        Assert.assertEquals(3, new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).getDimensionality());
        Assert.assertEquals(3, new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).getDimensionality());
        Assert.assertEquals(18, new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).getDimensionality());
        Assert.assertEquals(2, new BigVector(3, 5).getDimensionality());
        Assert.assertEquals(1, new BigVector(1.87).getDimensionality());
        Assert.assertEquals(1, new BigVector(0).getDimensionality());
        Assert.assertEquals(0, new BigVector().getDimensionality());
        
        //big
        Assert.assertEquals(3, new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).getDimensionality());
        Assert.assertEquals(1, new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356")).getDimensionality());
        Assert.assertEquals(4, new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578")).getDimensionality());
    }
    
    /**
     * JUnit test of getComponents.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getComponents()
     */
    @Test
    public void testGetComponents() throws Exception {
        //standard
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(8.0), BigDecimal.valueOf(9.0), BigDecimal.valueOf(1.0)}, new BigVector(8, 9, 1).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(-8.0), BigDecimal.valueOf(-9.0), BigDecimal.valueOf(-1.0)}, new BigVector(-8, -9, -1).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0)}, new BigVector(0, 0, 0).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(-1.87), BigDecimal.valueOf(88.06), BigDecimal.valueOf(-7.4)}, new BigVector(-1.87, 88.06, -7.4).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.8187612301), BigDecimal.valueOf(-8.1787546309), BigDecimal.valueOf(7.4589760359)}, new BigVector(1.8187612301, -8.1787546309, 7.4589760359).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.8187612301079148755), BigDecimal.valueOf(8.1787546309974664901), BigDecimal.valueOf(7.45897603598744864773)}, new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.00000000084), BigDecimal.valueOf(0.000000000000002774), BigDecimal.valueOf(0.000000000001)}, new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(8.0), BigDecimal.valueOf(1.0), BigDecimal.valueOf(1.0), BigDecimal.valueOf(-6.0), BigDecimal.valueOf(3.0), BigDecimal.valueOf(4.0), BigDecimal.valueOf(7.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(-6.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(44.0), BigDecimal.valueOf(9.0), BigDecimal.valueOf(-1.0), BigDecimal.valueOf(-5.0), BigDecimal.valueOf(8.0), BigDecimal.valueOf(7.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(3.0)}, new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(3.0), BigDecimal.valueOf(5.0)}, new BigVector(3, 5).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(1.87)}, new BigVector(1.87).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {BigDecimal.valueOf(0.0)}, new BigVector(0).getComponents());
        Assert.assertArrayEquals(new BigDecimal[] {}, new BigVector().getComponents());
        
        //big
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")},
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")).getComponents()
        );
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")},
                new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")).getComponents()
        );
        Assert.assertArrayEquals(new BigDecimal[] {new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")},
                new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")).getComponents()
        );
    }
    
    /**
     * JUnit test of getX.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getX()
     */
    @Test
    public void testGetX() throws Exception {
        BigVector vector = new BigVector("5");
        BigVector vector2 = new BigVector();
        BigVector vector3 = new BigVector("9480184196742351498717681768104518468572089428138798784924");
        BigVector vector4 = new BigVector("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827");
        
        //standard
        Assert.assertEquals(BigDecimal.valueOf(5), vector.getX());
        
        //big
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924"), vector3.getX());
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"), vector4.getX());
        
        
        //invalid
        try {
            Assert.assertEquals(BigDecimal.valueOf(0), vector2.getX());
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getY.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getY()
     */
    @Test
    public void testGetY() throws Exception {
        BigVector vector = new BigVector("5", "1.8744");
        BigVector vector2 = new BigVector("5");
        BigVector vector3 = new BigVector("5", "9480184196742351498717681768104518468572089428138798784924");
        BigVector vector4 = new BigVector("5", "9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827");
        
        //standard
        Assert.assertEquals(BigDecimal.valueOf(1.8744), vector.getY());
        
        //big
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924"), vector3.getY());
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"), vector4.getY());
        
        
        //invalid
        try {
            Assert.assertEquals(BigDecimal.valueOf(0), vector2.getY());
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getZ.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getZ()
     */
    @Test
    public void testGetZ() throws Exception {
        BigVector vector = new BigVector("5", "1.8744", "11.001");
        BigVector vector2 = new BigVector("5", "1.8744");
        BigVector vector3 = new BigVector("5", "1.8744", "9480184196742351498717681768104518468572089428138798784924");
        BigVector vector4 = new BigVector("5", "1.8744", "9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827");
        
        //standard
        Assert.assertEquals(BigDecimal.valueOf(11.001), vector.getZ());
        
        //big
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924"), vector3.getZ());
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"), vector4.getZ());
        
        
        //invalid
        try {
            Assert.assertEquals(BigDecimal.valueOf(0), vector2.getZ());
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getW.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getW()
     */
    @Test
    public void testGetW() throws Exception {
        BigVector vector = new BigVector("5", "1.8744", "11.001", "8");
        BigVector vector2 = new BigVector("5", "1.8744", "11.001");
        BigVector vector3 = new BigVector("5", "1.8744", "11.001", "9480184196742351498717681768104518468572089428138798784924");
        BigVector vector4 = new BigVector("5", "1.8744", "11.001", "9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827");
        
        //standard
        Assert.assertEquals(BigDecimal.valueOf(8), vector.getW());
        
        //big
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924"), vector3.getW());
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"), vector4.getW());
        
        
        //invalid
        try {
            Assert.assertEquals(BigDecimal.valueOf(0), vector2.getW());
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#get(int)
     */
    @Test
    public void testGet() throws Exception {
        BigVector vector = new BigVector("5", "1.8744", "11.001", "8", "4.49");
        BigVector vector2 = new BigVector("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827", "1.8744", "11.001", "9480184196742351498717681768104518468572089428138798784924", "4.49");
        
        //standard
        Assert.assertEquals(BigDecimal.valueOf(5), vector.get(0));
        Assert.assertEquals(BigDecimal.valueOf(1.8744), vector.get(1));
        Assert.assertEquals(BigDecimal.valueOf(11.001), vector.get(2));
        Assert.assertEquals(BigDecimal.valueOf(8), vector.get(3));
        Assert.assertEquals(BigDecimal.valueOf(4.49), vector.get(4));
        
        //big
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924"), vector2.get(3));
        Assert.assertEquals(new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"), vector2.get(0));
        
        //invalid
        
        try {
            vector.get(5);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.get(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
    
    /**
     * JUnit test of getMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getMathContext()
     */
    @Test
    public void testGetMathContext() throws Exception {
        BigVector vector = new BigVector();
        MathContext context;
        
        Assert.assertEquals(Whitebox.getInternalState(vector, "mathContext"), vector.getMathContext());
        context = vector.getMathContext();
        Assert.assertEquals(BigVector.DEFAULT_MATH_PRECISION, context.getPrecision());
        Assert.assertEquals(BigVector.DEFAULT_ROUNDING_MODE, context.getRoundingMode());
        
        context = new MathContext(100, RoundingMode.CEILING);
        vector.setMathContext(context);
        Assert.assertEquals(context, vector.getMathContext());
        context = vector.getMathContext();
        Assert.assertEquals(100, context.getPrecision());
        Assert.assertEquals(RoundingMode.CEILING, context.getRoundingMode());
        
        context = new MathContext(BigVector.DEFAULT_MATH_PRECISION, BigVector.DEFAULT_ROUNDING_MODE);
        vector.setMathContext(context);
        Assert.assertEquals(context, vector.getMathContext());
        context = vector.getMathContext();
        Assert.assertEquals(BigVector.DEFAULT_MATH_PRECISION, context.getPrecision());
        Assert.assertEquals(BigVector.DEFAULT_ROUNDING_MODE, context.getRoundingMode());
    }
    
    /**
     * JUnit test of getJustificationVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#getJustificationVector()
     */
    @Test
    public void testGetJustificationVector() throws Exception {
        Assert.assertEquals(Whitebox.getInternalState(BigVector.class, "JUSTIFICATION_VECTOR"), BigVector.getJustificationVector());
        Whitebox.setInternalState(BigVector.class, "JUSTIFICATION_VECTOR", new BigVector("1", "-2", "-4"));
        Assert.assertEquals(new BigVector("1", "-2", "-4"), BigVector.getJustificationVector());
        Assert.assertEquals(new BigVector("1", "-2", "-4"), BigVector.getJustificationVector());
        Whitebox.setInternalState(BigVector.class, "JUSTIFICATION_VECTOR", new BigVector("8945604845148704849848198450105418451498798178408484", "1540198419878145105494545046545012305210840", "97897802605481321064189089807483504657498"));
        Assert.assertEquals(new BigVector("8945604845148704849848198450105418451498798178408484", "1540198419878145105494545046545012305210840", "97897802605481321064189089807483504657498"), BigVector.getJustificationVector());
        Whitebox.setInternalState(BigVector.class, "JUSTIFICATION_VECTOR", new BigVector("1", "1", "1"));
        Assert.assertEquals(new BigVector("1", "1", "1"), BigVector.getJustificationVector());
    }
    
    /**
     * JUnit test of setX.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setX(BigDecimal)
     */
    @Test
    public void testSetX() throws Exception {
        BigVector vector = BigVector.origin(1);
        BigVector vector2 = BigVector.origin(0);
        
        //standard
        Assert.assertEquals(new BigVector("0"), vector);
        vector.setX(BigDecimal.valueOf(5));
        Assert.assertEquals(new BigVector("5"), vector);
        vector.setX(BigDecimal.valueOf(1.8744));
        Assert.assertEquals(new BigVector("1.8744"), vector);
        vector.setX(BigDecimal.valueOf(11.001));
        Assert.assertEquals(new BigVector("11.001"), vector);
        
        //big
        vector.setX(new BigDecimal("9480184196742351498717681768104518468572089428138798784924"));
        Assert.assertEquals(new BigVector("9480184196742351498717681768104518468572089428138798784924"), vector);
        vector.setX(new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"));
        Assert.assertEquals(new BigVector("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"), vector);
        
        //invalid
        Assert.assertEquals(new BigVector(), vector2);
        try {
            vector2.setX(new BigDecimal("5"));
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(new BigVector(), vector2);
    }
    
    /**
     * JUnit test of setY.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setY(BigDecimal)
     */
    @Test
    public void testSetY() throws Exception {
        BigVector vector = BigVector.origin(2);
        BigVector vector2 = BigVector.origin(1);
        
        //standard
        Assert.assertEquals(new BigVector("0", "0"), vector);
        vector.setY(BigDecimal.valueOf(5));
        Assert.assertEquals(new BigVector("0", "5"), vector);
        vector.setY(BigDecimal.valueOf(1.8744));
        Assert.assertEquals(new BigVector("0", "1.8744"), vector);
        vector.setY(BigDecimal.valueOf(11.001));
        Assert.assertEquals(new BigVector("0", "11.001"), vector);
        
        //big
        vector.setY(new BigDecimal("9480184196742351498717681768104518468572089428138798784924"));
        Assert.assertEquals(new BigVector("0", "9480184196742351498717681768104518468572089428138798784924"), vector);
        vector.setY(new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"));
        Assert.assertEquals(new BigVector("0", "9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"), vector);
        
        //invalid
        Assert.assertEquals(new BigVector("0"), vector2);
        try {
            vector2.setY(new BigDecimal("5"));
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(new BigVector("0"), vector2);
    }
    
    /**
     * JUnit test of setZ.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setZ(BigDecimal)
     */
    @Test
    public void testSetZ() throws Exception {
        BigVector vector = BigVector.origin(3);
        BigVector vector2 = BigVector.origin(2);
        
        //standard
        Assert.assertEquals(new BigVector("0", "0", "0"), vector);
        vector.setZ(BigDecimal.valueOf(5));
        Assert.assertEquals(new BigVector("0", "0", "5"), vector);
        vector.setZ(BigDecimal.valueOf(1.8744));
        Assert.assertEquals(new BigVector("0", "0", "1.8744"), vector);
        vector.setZ(BigDecimal.valueOf(11.001));
        Assert.assertEquals(new BigVector("0", "0", "11.001"), vector);
        
        //big
        vector.setZ(new BigDecimal("9480184196742351498717681768104518468572089428138798784924"));
        Assert.assertEquals(new BigVector("0", "0", "9480184196742351498717681768104518468572089428138798784924"), vector);
        vector.setZ(new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"));
        Assert.assertEquals(new BigVector("0", "0", "9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"), vector);
        
        //invalid
        Assert.assertEquals(new BigVector("0", "0"), vector2);
        try {
            vector2.setZ(new BigDecimal("5"));
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(new BigVector("0", "0"), vector2);
    }
    
    /**
     * JUnit test of setW.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setW(BigDecimal)
     */
    @Test
    public void testSetW() throws Exception {
        BigVector vector = BigVector.origin(4);
        BigVector vector2 = BigVector.origin(3);
        
        //standard
        Assert.assertEquals(new BigVector("0", "0", "0", "0"), vector);
        vector.setW(BigDecimal.valueOf(5));
        Assert.assertEquals(new BigVector("0", "0", "0", "5"), vector);
        vector.setW(BigDecimal.valueOf(1.8744));
        Assert.assertEquals(new BigVector("0", "0", "0", "1.8744"), vector);
        vector.setW(BigDecimal.valueOf(11.001));
        Assert.assertEquals(new BigVector("0", "0", "0", "11.001"), vector);
        
        //big
        vector.setW(new BigDecimal("9480184196742351498717681768104518468572089428138798784924"));
        Assert.assertEquals(new BigVector("0", "0", "0", "9480184196742351498717681768104518468572089428138798784924"), vector);
        vector.setW(new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"));
        Assert.assertEquals(new BigVector("0", "0", "0", "9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"), vector);
        
        //invalid
        Assert.assertEquals(new BigVector("0", "0", "0"), vector2);
        try {
            vector2.setW(new BigDecimal("5"));
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(new BigVector("0", "0", "0"), vector2);
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#set(int, BigDecimal)
     */
    @Test
    public void testSet() throws Exception {
        BigVector vector = BigVector.origin(5);
        
        //standard
        Assert.assertEquals(new BigVector("0", "0", "0", "0", "0"), vector);
        vector.set(0, BigDecimal.valueOf(5));
        Assert.assertEquals(new BigVector("5", "0", "0", "0", "0"), vector);
        vector.set(1, BigDecimal.valueOf(1.8744));
        Assert.assertEquals(new BigVector("5", "1.8744", "0", "0", "0"), vector);
        vector.set(2, BigDecimal.valueOf(11.001));
        Assert.assertEquals(new BigVector("5", "1.8744", "11.001", "0", "0"), vector);
        vector.set(3, BigDecimal.valueOf(8));
        Assert.assertEquals(new BigVector("5", "1.8744", "11.001", "8", "0"), vector);
        vector.set(4, BigDecimal.valueOf(4.49));
        Assert.assertEquals(new BigVector("5", "1.8744", "11.001", "8", "4.49"), vector);
        
        //big
        vector.set(3, new BigDecimal("9480184196742351498717681768104518468572089428138798784924"));
        Assert.assertEquals(new BigVector("5", "1.8744", "11.001", "9480184196742351498717681768104518468572089428138798784924", "4.49"), vector);
        vector.set(0, new BigDecimal("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827"));
        Assert.assertEquals(new BigVector("9480184196742351498717681768104518468572089428138798784924.5848940984981984897846279871789487298718798728798179827", "1.8744", "11.001", "9480184196742351498717681768104518468572089428138798784924", "4.49"), vector);
        
        //invalid
        
        try {
            vector.set(5, BigDecimal.valueOf(5.01));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
            vector.set(-1, BigDecimal.valueOf(5.01));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
    
    /**
     * JUnit test of setMathContext.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setMathContext(MathContext)
     */
    @Test
    public void testSetMathContext() throws Exception {
        BigVector vector = new BigVector();
        MathContext context;
        
        context = Whitebox.getInternalState(vector, "mathContext");
        Assert.assertEquals(BigVector.DEFAULT_MATH_PRECISION, context.getPrecision());
        Assert.assertEquals(BigVector.DEFAULT_ROUNDING_MODE, context.getRoundingMode());
        
        vector.setMathContext(new MathContext(100, RoundingMode.CEILING));
        context = Whitebox.getInternalState(vector, "mathContext");
        Assert.assertEquals(100, context.getPrecision());
        Assert.assertEquals(RoundingMode.CEILING, context.getRoundingMode());
        context = Whitebox.getInternalState(vector, "mathContext");
        Assert.assertEquals(100, context.getPrecision());
        Assert.assertEquals(RoundingMode.CEILING, context.getRoundingMode());
        
        vector.setMathContext(new MathContext(BigVector.DEFAULT_MATH_PRECISION, BigVector.DEFAULT_ROUNDING_MODE));
        context = Whitebox.getInternalState(vector, "mathContext");
        Assert.assertEquals(BigVector.DEFAULT_MATH_PRECISION, context.getPrecision());
        Assert.assertEquals(BigVector.DEFAULT_ROUNDING_MODE, context.getRoundingMode());
    }
    
    /**
     * JUnit test of setJustificationVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#setJustificationVector(BigVector)
     */
    @Test
    public void testSetJustificationVector() throws Exception {
        Assert.assertEquals(new BigVector("1", "1", "1"), Whitebox.getInternalState(BigVector.class, "JUSTIFICATION_VECTOR"));
        BigVector.setJustificationVector(new BigVector("1", "-2", "-4"));
        Assert.assertEquals(new BigVector("1", "-2", "-4"), Whitebox.getInternalState(BigVector.class, "JUSTIFICATION_VECTOR"));
        Assert.assertEquals(new BigVector("1", "-2", "-4"), Whitebox.getInternalState(BigVector.class, "JUSTIFICATION_VECTOR"));
        BigVector.setJustificationVector(new BigVector("8945604845148704849848198450105418451498798178408484", "1540198419878145105494545046545012305210840", "97897802605481321064189089807483504657498"));
        Assert.assertEquals(new BigVector("8945604845148704849848198450105418451498798178408484", "1540198419878145105494545046545012305210840", "97897802605481321064189089807483504657498"), Whitebox.getInternalState(BigVector.class, "JUSTIFICATION_VECTOR"));
        BigVector.setJustificationVector(new BigVector("1", "1", "1"));
        Assert.assertEquals(new BigVector("1", "1", "1"), Whitebox.getInternalState(BigVector.class, "JUSTIFICATION_VECTOR"));
    }
    
    /**
     * JUnit test of copyVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#copyVector(BigVector, BigVector)
     */
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testCopyVector() throws Exception {
        BigVector vector;
        BigVector copy;
        int hash;
        
        //standard
        
        vector = new BigVector(8, 6, 1);
        copy = new BigVector(1, 7, 7);
        hash = System.identityHashCode(copy);
        BigVector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        vector = new BigVector(1.654, 8.8778, -5.21);
        copy = new BigVector(0, 0, 0);
        hash = System.identityHashCode(copy);
        BigVector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        vector = new BigVector(1.654, 8.8778, -5.21, 4.1, 3.05914, 7.56, -0.54);
        copy = new BigVector(1, 1, 1, 1, 1, 1, 1);
        hash = System.identityHashCode(copy);
        BigVector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        vector = new BigVector();
        copy = new BigVector();
        hash = System.identityHashCode(copy);
        BigVector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        //big
        
        vector = new BigVector("89786749871847684841864387545", "546849817540685416846871451571", "5641840540159878074981546150165457819106544151");
        copy = new BigVector("141894154914354", "798046549084032102635491872", "7564984841614345");
        hash = System.identityHashCode(copy);
        BigVector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        vector = new BigVector("89786749871847684841864387545.20456978453571", "546849817540685416846871451571.401615348778945", "5641840540159878074981546150165457819106544151.12318978416340848");
        copy = new BigVector("141894154914354.0015697189716546142165", "798046549084032102635491872.546878465465", "7564984841614345.04859179814");
        hash = System.identityHashCode(copy);
        BigVector.copyVector(vector, copy);
        Assert.assertArrayEquals(vector.components, copy.components);
        Assert.assertEquals(vector, copy);
        Assert.assertNotEquals(System.identityHashCode(vector), System.identityHashCode(copy));
        Assert.assertEquals(hash, System.identityHashCode(copy));
        
        //invalid
        
        try {
            BigVector.copyVector(new BigVector(1, 1, 1), new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            BigVector.copyVector(new BigVector(1), new BigVector(1, 1));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            BigVector.copyVector(new BigVector(1), new BigVector());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            BigVector.copyVector(new BigVector(1), null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * JUnit test of unit.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#unit(int)
     */
    @Test
    public void testUnit() throws Exception {
        //standard
        Assert.assertEquals(new BigVector(), BigVector.unit(0));
        Assert.assertEquals(new BigVector("1"), BigVector.unit(1));
        Assert.assertEquals(new BigVector("1", "1"), BigVector.unit(2));
        Assert.assertEquals(new BigVector("1", "1", "1"), BigVector.unit(3));
        Assert.assertEquals(new BigVector("1", "1", "1", "1"), BigVector.unit(4));
        Assert.assertEquals(new BigVector("1", "1", "1", "1", "1"), BigVector.unit(5));
        Assert.assertEquals(new BigVector("1", "1", "1", "1", "1", "1", "1", "1", "1", "1"), BigVector.unit(10));
        
        //uniqueness
        Assert.assertEquals(BigVector.unit(3), BigVector.unit(3));
        Assert.assertNotEquals(System.identityHashCode(BigVector.unit(3)), System.identityHashCode(BigVector.unit(3)));
    }
    
    /**
     * JUnit test of origin.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#origin(int)
     */
    @Test
    public void testOrigin() throws Exception {
        //standard
        Assert.assertEquals(new BigVector(), BigVector.origin(0));
        Assert.assertEquals(new BigVector("0"), BigVector.origin(1));
        Assert.assertEquals(new BigVector("0", "0"), BigVector.origin(2));
        Assert.assertEquals(new BigVector("0", "0", "0"), BigVector.origin(3));
        Assert.assertEquals(new BigVector("0", "0", "0", "0"), BigVector.origin(4));
        Assert.assertEquals(new BigVector("0", "0", "0", "0", "0"), BigVector.origin(5));
        Assert.assertEquals(new BigVector("0", "0", "0", "0", "0", "0", "0", "0", "0", "0"), BigVector.origin(10));
        
        //uniqueness
        Assert.assertEquals(BigVector.origin(3), BigVector.origin(3));
        Assert.assertNotEquals(System.identityHashCode(BigVector.origin(3)), System.identityHashCode(BigVector.origin(3)));
    }
    
    /**
     * JUnit test of averageVector.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#averageVector(List)
     * @see BigVector#averageVector(BigVector...)
     * @see #testAverageVectorList()
     * @see #testAverageVectorArray()
     */
    @Test
    public void testAverageVector() throws Exception {
        BigVector[] vectorSet3 = new BigVector[] {new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)};
        BigVector[] vectorSet2 = new BigVector[] {new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)};
        BigVector[] vectorSet1 = new BigVector[] {new BigVector(8), new BigVector(0.1), new BigVector(-5.6)};
        BigVector[] vectorSet18 = new BigVector[] {new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<BigVector> vectorList3 = Arrays.asList(vectorSet3);
        List<BigVector> vectorList2 = Arrays.asList(vectorSet2);
        List<BigVector> vectorList1 = Arrays.asList(vectorSet1);
        List<BigVector> vectorList18 = Arrays.asList(vectorSet18);
        
        //list
        testAverageVectorList();
        
        //array
        testAverageVectorArray();
        
        //empty
        Assert.assertEquals(new BigVector(), BigVector.averageVector());
        
        //big
        Assert.assertEquals(new BigVector("56596373634236658598127742653803000000000", "653246748668112699396275612385.99", "703323001060082276970300803793.40"),
                BigVector.averageVector(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")),
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")),
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690")))
        );
        Assert.assertEquals(new BigVector("28298186817796614173927968815085000000000", "28298186817796614173927968815085000000000", "28298186817796614173927968815085000000000"),
                BigVector.averageVector(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("84894560451354987897191613980704105013356")),
                        new BigVector(new BigDecimal("979870123002169049094413418578"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("979870123002169049094413418578")),
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("1054984501590123415455451205690"), new BigDecimal("1054984501590123415455451205690")))
        );
        
        //invalid
        
        try {
            List<BigVector> list = new ArrayList<>(vectorList2);
            list.add(new BigVector(8, 9, 1));
            BigVector.averageVector(list);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            BigVector[] array = new BigVector[4];
            System.arraycopy(vectorSet3, 0, array, 0, 3);
            array[3] = new BigVector(8);
            BigVector.averageVector(array);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            BigVector.averageVector(new BigVector(), new BigVector(8), new BigVector(0.1), new BigVector(5.6));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            BigVector.averageVector(Collections.singletonList(null));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            BigVector.averageVector(null, null, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * Helper method for JUnit test of averageVector for list cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testAverageVectorList() throws Exception {
        BigVector[] vectorSet3 = new BigVector[] {new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)};
        BigVector[] vectorSet2 = new BigVector[] {new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)};
        BigVector[] vectorSet1 = new BigVector[] {new BigVector(8), new BigVector(0.1), new BigVector(-5.6)};
        BigVector[] vectorSet18 = new BigVector[] {new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<BigVector> vectorList3 = Arrays.asList(vectorSet3);
        List<BigVector> vectorList2 = Arrays.asList(vectorSet2);
        List<BigVector> vectorList1 = Arrays.asList(vectorSet1);
        List<BigVector> vectorList18 = Arrays.asList(vectorSet18);
        
        //origin
        Assert.assertEquals(new BigVector("4.0", "4.5", "0.5"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(8, 9, 1)), Collections.singletonList(BigVector.origin(3)))));
        Assert.assertEquals(new BigVector("-4.0", "-4.5", "-0.5"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(-8, -9, -1)), Collections.singletonList(BigVector.origin(3)))));
        Assert.assertEquals(new BigVector("0.0", "0.0", "0.0"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(0, 0, 0)), Collections.singletonList(BigVector.origin(3)))));
        Assert.assertEquals(new BigVector("-0.935", "44.03", "-3.7"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(-1.87, 88.06, -7.4)), Collections.singletonList(BigVector.origin(3)))));
        Assert.assertEquals(new BigVector("0.90938061505", "-4.08937731545", "3.72948801795"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(1.8187612301, -8.1787546309, 7.4589760359)), Collections.singletonList(BigVector.origin(3)))));
        Assert.assertEquals(new BigVector("0.9093806150539574", "4.0893773154987335", "3.729488017993724"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)), Collections.singletonList(BigVector.origin(3)))));
        Assert.assertEquals(new BigVector("0.00000000042", "0.000000000000001387", "0.0000000000005"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001)), Collections.singletonList(BigVector.origin(3)))));
        Assert.assertEquals(new BigVector("4.0", "0.5", "0.5", "-3.0", "1.5", "2.0", "3.5", "0.0", "-3.0", "0.0", "22.0", "4.5", "-0.5", "-2.5", "4.0", "3.5", "0.0", "1.5"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)), Collections.singletonList(BigVector.origin(18)))));
        Assert.assertEquals(new BigVector("1.5", "2.5"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(3, 5)), Collections.singletonList(BigVector.origin(2)))));
        Assert.assertEquals(new BigVector("0.935"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(1.87)), Collections.singletonList(BigVector.origin(1)))));
        Assert.assertEquals(new BigVector("0.0"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(0)), Collections.singletonList(BigVector.origin(1)))));
        Assert.assertEquals(new BigVector(), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector()), Collections.singletonList(BigVector.origin(0)))));
        
        //list
        Assert.assertEquals(new BigVector("2.625", "3.1075", "1.54"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(8, 9, 1)), vectorList3)));
        Assert.assertEquals(new BigVector("-1.375", "-1.3925", "1.04"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(-8, -9, -1)), vectorList3)));
        Assert.assertEquals(new BigVector("0.625", "0.8575", "1.29"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(0, 0, 0)), vectorList3)));
        Assert.assertEquals(new BigVector("0.1575", "22.8725", "-0.56"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(-1.87, 88.06, -7.4)), vectorList3)));
        Assert.assertEquals(new BigVector("1.079690307525", "-1.187188657725", "3.154744008975"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(1.8187612301, -8.1787546309, 7.4589760359)), vectorList3)));
        Assert.assertEquals(new BigVector("1.0796903075269787", "2.90218865774936675", "3.154744008996862"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)), vectorList3)));
        Assert.assertEquals(new BigVector("0.62500000021", "0.8575000000000006935", "1.29000000000025"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001)), vectorList3)));
        Assert.assertEquals(new BigVector("2.625", "1.1075", "1.54", "1.25", "6.5", "4.575", "1.375", "4.4425", "-2.35", "1.9725", "14.5", "0.725", "2.2475", "2.375", "1.75", "1.75", "1.25", "-1.025"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)), vectorList18)));
        Assert.assertEquals(new BigVector("1.375", "2.1075"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(3, 5)), vectorList2)));
        Assert.assertEquals(new BigVector("1.0925"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(1.87)), vectorList1)));
        Assert.assertEquals(new BigVector("0.625"), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector(0)), vectorList1)));
        Assert.assertEquals(new BigVector(), BigVector.averageVector(ListUtility.merge(Collections.singletonList(new BigVector()), Collections.emptyList())));
    }
    
    /**
     * Helper method for JUnit test of averageVector for array cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testAverageVectorArray() throws Exception {
        BigVector[] vectorSet3 = new BigVector[] {new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)};
        BigVector[] vectorSet2 = new BigVector[] {new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)};
        BigVector[] vectorSet1 = new BigVector[] {new BigVector(8), new BigVector(0.1), new BigVector(-5.6)};
        BigVector[] vectorSet18 = new BigVector[] {new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        
        //array
        Assert.assertEquals(new BigVector("2.625", "3.1075", "1.54"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(8, 9, 1)}, vectorSet3, BigVector.class)));
        Assert.assertEquals(new BigVector("-1.375", "-1.3925", "1.04"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(-8, -9, -1)}, vectorSet3, BigVector.class)));
        Assert.assertEquals(new BigVector("0.625", "0.8575", "1.29"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(0, 0, 0)}, vectorSet3, BigVector.class)));
        Assert.assertEquals(new BigVector("0.1575", "22.8725", "-0.56"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(-1.87, 88.06, -7.4)}, vectorSet3, BigVector.class)));
        Assert.assertEquals(new BigVector("1.079690307525", "-1.187188657725", "3.154744008975"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(1.8187612301, -8.1787546309, 7.4589760359)}, vectorSet3, BigVector.class)));
        Assert.assertEquals(new BigVector("1.0796903075269787", "2.90218865774936675", "3.154744008996862"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)}, vectorSet3, BigVector.class)));
        Assert.assertEquals(new BigVector("0.62500000021", "0.8575000000000006935", "1.29000000000025"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001)}, vectorSet3, BigVector.class)));
        Assert.assertEquals(new BigVector("2.625", "1.1075", "1.54", "1.25", "6.5", "4.575", "1.375", "4.4425", "-2.35", "1.9725", "14.5", "0.725", "2.2475", "2.375", "1.75", "1.75", "1.25", "-1.025"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)}, vectorSet18, BigVector.class)));
        Assert.assertEquals(new BigVector("1.375", "2.1075"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(3, 5)}, vectorSet2, BigVector.class)));
        Assert.assertEquals(new BigVector("1.0925"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(1.87)}, vectorSet1, BigVector.class)));
        Assert.assertEquals(new BigVector("0.625"), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector(0)}, vectorSet1, BigVector.class)));
        Assert.assertEquals(new BigVector(), BigVector.averageVector(ArrayUtility.merge(new BigVector[] {new BigVector()}, new BigVector[] {}, BigVector.class)));
        
        //set
        Assert.assertEquals(new BigVector("2.625", "3.1075", "1.54"), BigVector.averageVector(new BigVector(8, 9, 1), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("-1.375", "-1.3925", "1.04"), BigVector.averageVector(new BigVector(-8, -9, -1), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("0.625", "0.8575", "1.29"), BigVector.averageVector(new BigVector(0, 0, 0), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("0.1575", "22.8725", "-0.56"), BigVector.averageVector(new BigVector(-1.87, 88.06, -7.4), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("1.079690307525", "-1.187188657725", "3.154744008975"), BigVector.averageVector(new BigVector(1.8187612301, -8.1787546309, 7.4589760359), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("1.0796903075269787", "2.90218865774936675", "3.154744008996862"), BigVector.averageVector(new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("0.62500000021", "0.8575000000000006935", "1.29000000000025"), BigVector.averageVector(new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)));
        Assert.assertEquals(new BigVector("2.625", "1.1075", "1.54", "1.25", "6.5", "4.575", "1.375", "4.4425", "-2.35", "1.9725", "14.5", "0.725", "2.2475", "2.375", "1.75", "1.75", "1.25", "-1.025"), BigVector.averageVector(new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)));
        Assert.assertEquals(new BigVector("1.375", "2.1075"), BigVector.averageVector(new BigVector(3, 5), new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)));
        Assert.assertEquals(new BigVector("1.0925"), BigVector.averageVector(new BigVector(1.87), new BigVector(8), new BigVector(0.1), new BigVector(-5.6)));
        Assert.assertEquals(new BigVector("0.625"), BigVector.averageVector(new BigVector(0), new BigVector(8), new BigVector(0.1), new BigVector(-5.6)));
        Assert.assertEquals(new BigVector(), BigVector.averageVector(new BigVector()));
    }
    
    /**
     * JUnit test of calculateMinMax.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#calculateMinMax(List)
     * @see BigVector#calculateMinMax(BigVector...)
     * @see #testCalculateMinMaxList()
     * @see #testCalculateMinMaxArray()
     */
    @Test
    public void testCalculateMinMax() throws Exception {
        BigVector[] vectorSet3 = new BigVector[] {new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)};
        BigVector[] vectorSet2 = new BigVector[] {new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)};
        BigVector[] vectorSet1 = new BigVector[] {new BigVector(8), new BigVector(0.1), new BigVector(-5.6)};
        BigVector[] vectorSet18 = new BigVector[] {new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<BigVector> vectorList3 = Arrays.asList(vectorSet3);
        List<BigVector> vectorList2 = Arrays.asList(vectorSet2);
        List<BigVector> vectorList1 = Arrays.asList(vectorSet1);
        List<BigVector> vectorList18 = Arrays.asList(vectorSet18);
        
        //list
        testCalculateMinMaxList();
        
        //array
        testCalculateMinMaxArray();
        
        //empty
        Assert.assertEquals(Collections.emptyList(), BigVector.calculateMinMax());
        
        //big
        Assert.assertArrayEquals(
                new BigVector[] {new BigVector("0.84894560451354987897191613980704105013356", "84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigVector("0.979870123002169049094413418578", "979870123002169049094413418578.979870123002169049094413418578"), new BigVector("0.1054984501590123415455451205690", "1054984501590123415455451205690.1054984501590123415455451205690")},
                BigVector.calculateMinMax(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690")),
                        new BigVector(new BigDecimal("0.84894560451354987897191613980704105013356"), new BigDecimal("0.979870123002169049094413418578"), new BigDecimal("0.1054984501590123415455451205690")),
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356.84894560451354987897191613980704105013356"), new BigDecimal("979870123002169049094413418578.979870123002169049094413418578"), new BigDecimal("1054984501590123415455451205690.1054984501590123415455451205690"))).toArray()
        );
        Assert.assertArrayEquals(
                new BigVector[] {new BigVector("979870123002169049094413418578", "84894560451354987897191613980704105013356"), new BigVector("979870123002169049094413418578", "84894560451354987897191613980704105013356"), new BigVector("979870123002169049094413418578", "84894560451354987897191613980704105013356")},
                BigVector.calculateMinMax(
                        new BigVector(new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("84894560451354987897191613980704105013356"), new BigDecimal("84894560451354987897191613980704105013356")),
                        new BigVector(new BigDecimal("979870123002169049094413418578"), new BigDecimal("979870123002169049094413418578"), new BigDecimal("979870123002169049094413418578")),
                        new BigVector(new BigDecimal("1054984501590123415455451205690"), new BigDecimal("1054984501590123415455451205690"), new BigDecimal("1054984501590123415455451205690"))).toArray()
        );
        
        //invalid
        
        try {
            List<BigVector> list = new ArrayList<>(vectorList2);
            list.add(new BigVector(8, 9, 1));
            BigVector.calculateMinMax(list);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            BigVector[] array = new BigVector[4];
            System.arraycopy(vectorSet3, 0, array, 0, 3);
            array[3] = new BigVector(8);
            BigVector.calculateMinMax(array);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            BigVector.calculateMinMax(new BigVector(), new BigVector(8), new BigVector(0.1), new BigVector(5.6));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ArithmeticException);
        }
        
        try {
            BigVector.calculateMinMax(Collections.singletonList(null));
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
        
        try {
            BigVector.calculateMinMax(null, null, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }
    
    /**
     * Helper method for JUnit test of calculateMinMax for list cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testCalculateMinMaxList() throws Exception {
        BigVector[] vectorSet3 = new BigVector[] {new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)};
        BigVector[] vectorSet2 = new BigVector[] {new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)};
        BigVector[] vectorSet1 = new BigVector[] {new BigVector(8), new BigVector(0.1), new BigVector(-5.6)};
        BigVector[] vectorSet18 = new BigVector[] {new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        List<BigVector> vectorList3 = Arrays.asList(vectorSet3);
        List<BigVector> vectorList2 = Arrays.asList(vectorSet2);
        List<BigVector> vectorList1 = Arrays.asList(vectorSet1);
        List<BigVector> vectorList18 = Arrays.asList(vectorSet18);
        
        //list
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "9.0"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(8, 9, 1)), vectorList3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-8.0", "8.0"), new BigVector("-9.0", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(-8, -9, -1)), vectorList3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(0, 0, 0)), vectorList3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "88.06"), new BigVector("-7.4", "9.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(-1.87, 88.06, -7.4)), vectorList3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-8.1787546309", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(1.8187612301, -8.1787546309, 7.4589760359)), vectorList3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "8.178754630997467"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)), vectorList3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001)), vectorList3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "4.1"), new BigVector("-3.0", "9.0"), new BigVector("-6.0", "10.0"), new BigVector("3.0", "9.0"), new BigVector("2.0", "6.3"), new BigVector("-3.0", "7.0"), new BigVector("0.0", "14.0"), new BigVector("-6.0", "3.6"), new BigVector("-4.0", "7.0"), new BigVector("2.0", "44.0"), new BigVector("-11.0", "9.0"), new BigVector("-1.0", "6.0"), new BigVector("-5.0", "5.5"), new BigVector("-5.0", "8.0"), new BigVector("-7.0", "7.0"), new BigVector("0.0", "3.0"), new BigVector("-8.1", "3.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)), vectorList18)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "5.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(3, 5)), vectorList2)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(1.87)), vectorList1)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0")}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector(0)), vectorList1)).toArray());
        Assert.assertArrayEquals(new BigVector[] {}, BigVector.calculateMinMax(ListUtility.merge(Collections.singletonList(new BigVector()), Collections.emptyList())).toArray());
        
    }
    
    /**
     * Helper method for JUnit test of calculateMinMax for array cases.
     *
     * @throws Exception When there is an exception.
     */
    private void testCalculateMinMaxArray() throws Exception {
        BigVector[] vectorSet3 = new BigVector[] {new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)};
        BigVector[] vectorSet2 = new BigVector[] {new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)};
        BigVector[] vectorSet1 = new BigVector[] {new BigVector(8), new BigVector(0.1), new BigVector(-5.6)};
        BigVector[] vectorSet18 = new BigVector[] {new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)};
        
        //array
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "9.0"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(8, 9, 1)}, vectorSet3, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-8.0", "8.0"), new BigVector("-9.0", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(-8, -9, -1)}, vectorSet3, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(0, 0, 0)}, vectorSet3, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "88.06"), new BigVector("-7.4", "9.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(-1.87, 88.06, -7.4)}, vectorSet3, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-8.1787546309", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(1.8187612301, -8.1787546309, 7.4589760359)}, vectorSet3, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "8.178754630997467"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773)}, vectorSet3, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001)}, vectorSet3, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "4.1"), new BigVector("-3.0", "9.0"), new BigVector("-6.0", "10.0"), new BigVector("3.0", "9.0"), new BigVector("2.0", "6.3"), new BigVector("-3.0", "7.0"), new BigVector("0.0", "14.0"), new BigVector("-6.0", "3.6"), new BigVector("-4.0", "7.0"), new BigVector("2.0", "44.0"), new BigVector("-11.0", "9.0"), new BigVector("-1.0", "6.0"), new BigVector("-5.0", "5.5"), new BigVector("-5.0", "8.0"), new BigVector("-7.0", "7.0"), new BigVector("0.0", "3.0"), new BigVector("-8.1", "3.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3)}, vectorSet18, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "5.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(3, 5)}, vectorSet2, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(1.87)}, vectorSet1, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0")}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector(0)}, vectorSet1, BigVector.class)).toArray());
        Assert.assertArrayEquals(new BigVector[] {}, BigVector.calculateMinMax(ArrayUtility.merge(new BigVector[] {new BigVector()}, new BigVector[] {}, BigVector.class)).toArray());
        
        //set
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "9.0"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(new BigVector(8, 9, 1), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-8.0", "8.0"), new BigVector("-9.0", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(new BigVector(-8, -9, -1), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(new BigVector(0, 0, 0), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "88.06"), new BigVector("-7.4", "9.0")}, BigVector.calculateMinMax(new BigVector(-1.87, 88.06, -7.4), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-8.1787546309", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(new BigVector(1.8187612301, -8.1787546309, 7.4589760359), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "8.178754630997467"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(new BigVector(1.8187612301079148755, 8.1787546309974664901, 7.45897603598744864773), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "4.1"), new BigVector("-3.0", "9.0")}, BigVector.calculateMinMax(new BigVector(0.00000000084, 0.000000000000002774, 0.000000000001), new BigVector(8, -1.66, 9), new BigVector(0.1, 0.99, -0.84), new BigVector(-5.6, 4.1, -3)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "4.1"), new BigVector("-3.0", "9.0"), new BigVector("-6.0", "10.0"), new BigVector("3.0", "9.0"), new BigVector("2.0", "6.3"), new BigVector("-3.0", "7.0"), new BigVector("0.0", "14.0"), new BigVector("-6.0", "3.6"), new BigVector("-4.0", "7.0"), new BigVector("2.0", "44.0"), new BigVector("-11.0", "9.0"), new BigVector("-1.0", "6.0"), new BigVector("-5.0", "5.5"), new BigVector("-5.0", "8.0"), new BigVector("-7.0", "7.0"), new BigVector("0.0", "3.0"), new BigVector("-8.1", "3.0")}, BigVector.calculateMinMax(new BigVector(8, 1, 1, -6, 3, 4, 7, 0, -6, 0, 44, 9, -1, -5, 8, 7, 0, 3), new BigVector(8, -1.66, 9, 1, 7, 6.3, 4, 0.77, -4, -4, 8, 6, 3, 5.5, -5, 4, 3, 0), new BigVector(0.1, 0.99, -0.84, 0, 7, 6, -2.5, 14, 3.6, 7, 2, -1.1, 0.99, 5, 5, -7, 1, 1), new BigVector(-5.6, 4.1, -3, 10, 9, 2, -3, 3, -3, 4.89, 4, -11, 6, 4, -1, 3, 1, -8.1)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0"), new BigVector("-1.66", "5.0")}, BigVector.calculateMinMax(new BigVector(3, 5), new BigVector(8, -1.66), new BigVector(0.1, 0.99), new BigVector(-5.6, 4.1)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0")}, BigVector.calculateMinMax(new BigVector(1.87), new BigVector(8), new BigVector(0.1), new BigVector(-5.6)).toArray());
        Assert.assertArrayEquals(new BigVector[] {new BigVector("-5.6", "8.0")}, BigVector.calculateMinMax(new BigVector(0), new BigVector(8), new BigVector(0.1), new BigVector(-5.6)).toArray());
        Assert.assertArrayEquals(new BigVector[] {}, BigVector.calculateMinMax(new BigVector()).toArray());
    }
    
    /**
     * JUnit test of dimensionalityNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dimensionalityNotEqualErrorMessage(BigVector, BigVector)
     */
    @Test
    public void testDimensionalityNotEqualErrorMessage() throws Exception {
        //standard
        Assert.assertEquals(
                "The big vectors: <5.0, 11.0, 2.0> and <8.0, 3.0, 2.0, 7.0> do not have the same dimensionality",
                BigVector.dimensionalityNotEqualErrorMessage(new BigVector(5, 11, 2), new BigVector(8, 3, 2, 7))
        );
        Assert.assertEquals(
                "The big vectors: <0.1, 0.001, 0.00010> and <0.0000000080> do not have the same dimensionality",
                BigVector.dimensionalityNotEqualErrorMessage(new BigVector(0.1, 0.001, 0.0001), new BigVector(0.000000008))
        );
        Assert.assertEquals(
                "The big vectors: <1.54, 7784500.0, 0.0, 3.0, 5.9> and <199.0, 211.0, 187.0> do not have the same dimensionality",
                BigVector.dimensionalityNotEqualErrorMessage(new BigVector(1.54, 7784500, 0, 3, 5.9), new BigVector(199, 211, 187))
        );
        Assert.assertEquals(
                "The big vectors: <> and <19.0> do not have the same dimensionality",
                BigVector.dimensionalityNotEqualErrorMessage(new BigVector(), new BigVector(19))
        );
        
        //big
        Assert.assertEquals(
                "The big vectors: <867848189849843046546517874687145.05498715205187498798214187, 97013210545897981765350424818970.8975406543216542984508798178, 8754698768570894568968479465421320.018549879816051650148567> and <867848189849843046546517874687145.05498715205187498798214187, 97013210545897981765350424818970.8975406543216542984508798178> do not have the same dimensionality",
                BigVector.dimensionalityNotEqualErrorMessage(new BigVector("867848189849843046546517874687145.05498715205187498798214187", "97013210545897981765350424818970.8975406543216542984508798178", "8754698768570894568968479465421320.018549879816051650148567"), new BigVector("867848189849843046546517874687145.05498715205187498798214187", "97013210545897981765350424818970.8975406543216542984508798178"))
        );
    }
    
    /**
     * JUnit test of dimensionalityMinimumNotMetErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#dimensionalityMinimumNotMetErrorMessage(BigVector, int)
     */
    @Test
    public void testDimensionalityMinimumNotMetErrorMessage() throws Exception {
        //standard
        Assert.assertEquals(
                "The big vector: <5.0, 11.0, 2.0> does not have the minimum dimensionality of: 4",
                BigVector.dimensionalityMinimumNotMetErrorMessage(new BigVector(5, 11, 2), 4)
        );
        Assert.assertEquals(
                "The big vector: <0.1, 0.001, 0.00010> does not have the minimum dimensionality of: 5",
                BigVector.dimensionalityMinimumNotMetErrorMessage(new BigVector(0.1, 0.001, 0.0001), 5)
        );
        Assert.assertEquals(
                "The big vector: <1.54, 7784500.0, 0.0, 3.0, 5.9> does not have the minimum dimensionality of: 12",
                BigVector.dimensionalityMinimumNotMetErrorMessage(new BigVector(1.54, 7784500, 0, 3, 5.9), 12)
        );
        Assert.assertEquals(
                "The big vector: <> does not have the minimum dimensionality of: 1",
                BigVector.dimensionalityMinimumNotMetErrorMessage(new BigVector(), 1)
        );
        
        //big
        Assert.assertEquals(
                "The big vector: <867848189849843046546517874687145.05498715205187498798214187, 97013210545897981765350424818970.8975406543216542984508798178, 8754698768570894568968479465421320.018549879816051650148567> does not have the minimum dimensionality of: 4",
                BigVector.dimensionalityMinimumNotMetErrorMessage(new BigVector("867848189849843046546517874687145.05498715205187498798214187", "97013210545897981765350424818970.8975406543216542984508798178", "8754698768570894568968479465421320.018549879816051650148567"), 4)
        );
    }
    
    /**
     * JUnit test of componentIndexOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#componentIndexOutOfBoundsErrorMessage(BigVector, int)
     */
    @Test
    public void testComponentIndexOutOfBoundsErrorMessage() throws Exception {
        //standard
        Assert.assertEquals(
                "The big vector: <5.0, 11.0, 2.0> does not have a component at index: 3",
                BigVector.componentIndexOutOfBoundsErrorMessage(new BigVector(5, 11, 2), 3)
        );
        Assert.assertEquals(
                "The big vector: <0.1, 0.001, 0.00010> does not have a component at index: 8",
                BigVector.componentIndexOutOfBoundsErrorMessage(new BigVector(0.1, 0.001, 0.0001), 8)
        );
        Assert.assertEquals(
                "The big vector: <1.54, 7784500.0, 0.0, 3.0, 5.9> does not have a component at index: -1",
                BigVector.componentIndexOutOfBoundsErrorMessage(new BigVector(1.54, 7784500, 0, 3, 5.9), -1)
        );
        Assert.assertEquals(
                "The big vector: <> does not have a component at index: 0",
                BigVector.componentIndexOutOfBoundsErrorMessage(new BigVector(), 0)
        );
        
        //big
        Assert.assertEquals(
                "The big vector: <867848189849843046546517874687145.05498715205187498798214187, 97013210545897981765350424818970.8975406543216542984508798178, 8754698768570894568968479465421320.018549879816051650148567> does not have a component at index: 8",
                BigVector.componentIndexOutOfBoundsErrorMessage(new BigVector("867848189849843046546517874687145.05498715205187498798214187", "97013210545897981765350424818970.8975406543216542984508798178", "8754698768570894568968479465421320.018549879816051650148567"), 8)
        );
    }
    
    /**
     * JUnit test of componentRangeOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see BigVector#componentRangeOutOfBoundsErrorMessage(BigVector, int, int)
     */
    @Test
    public void testComponentRangeOutOfBoundsErrorMessage() throws Exception {
        //standard
        Assert.assertEquals(
                "The range: [2,5) is out of bounds of the big vector: <5.0, 11.0, 2.0>",
                BigVector.componentRangeOutOfBoundsErrorMessage(new BigVector(5, 11, 2), 2, 5)
        );
        Assert.assertEquals(
                "The range: [8,5) is out of bounds of the big vector: <0.1, 0.001, 0.00010>",
                BigVector.componentRangeOutOfBoundsErrorMessage(new BigVector(0.1, 0.001, 0.0001), 8, 5)
        );
        Assert.assertEquals(
                "The range: [-1,2) is out of bounds of the big vector: <1.54, 7784500.0, 0.0, 3.0, 5.9>",
                BigVector.componentRangeOutOfBoundsErrorMessage(new BigVector(1.54, 7784500, 0, 3, 5.9), -1, 2)
        );
        Assert.assertEquals(
                "The range: [0,0) is out of bounds of the big vector: <>",
                BigVector.componentRangeOutOfBoundsErrorMessage(new BigVector(), 0, 0)
        );
        
        //big
        Assert.assertEquals(
                "The range: [1,4) is out of bounds of the big vector: <867848189849843046546517874687145.05498715205187498798214187, 97013210545897981765350424818970.8975406543216542984508798178, 8754698768570894568968479465421320.018549879816051650148567>",
                BigVector.componentRangeOutOfBoundsErrorMessage(new BigVector("867848189849843046546517874687145.05498715205187498798214187", "97013210545897981765350424818970.8975406543216542984508798178", "8754698768570894568968479465421320.018549879816051650148567"), 1, 4)
        );
    }
    
}
