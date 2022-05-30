/*
 * File:    BoundUtilityTest.java
 * Package: commons.math.number
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import commons.object.collection.ArrayUtility;
import commons.object.collection.ListUtility;
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
 * JUnit test of BoundUtility.
 *
 * @see BoundUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BoundUtility.class})
public class BoundUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BoundUtilityTest.class);
    
    
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
     * JUnit test of inBounds.
     *
     * @throws Exception When there is an exception.
     * @see BoundUtility#inBounds(Number, Number, Number, boolean, boolean)
     * @see BoundUtility#inBounds(Number, Number, Number)
     */
    @Test
    public void testInBounds() throws Exception {
        //int
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6));
        Assert.assertTrue(BoundUtility.inBounds(4, 4, 6));
        Assert.assertTrue(BoundUtility.inBounds(6, 2, 6));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6));
        
        //float
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.5, 6.4));
        Assert.assertTrue(BoundUtility.inBounds(6.4, 2.4, 6.4));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6));
        Assert.assertTrue(BoundUtility.inBounds(4.4, 4.4, 4.6));
        Assert.assertTrue(BoundUtility.inBounds(4.6, 4.4, 4.6));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6));
        
        //double
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001));
        Assert.assertTrue(BoundUtility.inBounds(4.4999, 4.4999, 4.5001));
        Assert.assertTrue(BoundUtility.inBounds(4.5001, 4.4999, 4.5001));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001));
        
        //long
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L));
        Assert.assertTrue(BoundUtility.inBounds(123456788L, 123456788L, 123456790L));
        Assert.assertTrue(BoundUtility.inBounds(123456790L, 123456788L, 123456790L));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L));
        
        //int, touch upper and lower bounds
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4, 4, 6, true, true));
        Assert.assertTrue(BoundUtility.inBounds(6, 2, 6, true, true));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6, true, true));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6, true, true));
        
        //float, touch upper and lower bounds
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.5, 6.4, true, true));
        Assert.assertTrue(BoundUtility.inBounds(6.4, 2.4, 6.4, true, true));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4, true, true));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.4, 4.4, 4.6, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.6, 4.4, 4.6, true, true));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6, true, true));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6, true, true));
        
        //double, touch upper and lower bounds
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.4999, 4.4999, 4.5001, true, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5001, 4.4999, 4.5001, true, true));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001, true, true));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001, true, true));
        
        //long, touch upper and lower bounds
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L, true, true));
        Assert.assertTrue(BoundUtility.inBounds(123456788L, 123456788L, 123456790L, true, true));
        Assert.assertTrue(BoundUtility.inBounds(123456790L, 123456788L, 123456790L, true, true));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L, true, true));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L, true, true));
        
        //int, touch upper bound
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4, 4, 6, false, true));
        Assert.assertTrue(BoundUtility.inBounds(6, 2, 6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6, false, true));
        
        //float, touch upper bound
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.5, 4.5, 6.4, false, true));
        Assert.assertTrue(BoundUtility.inBounds(6.4, 2.4, 6.4, false, true));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4, false, true));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4, false, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.4, 4.4, 4.6, false, true));
        Assert.assertTrue(BoundUtility.inBounds(4.6, 4.4, 4.6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6, false, true));
        
        //double, touch upper bound
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.4999, 4.4999, 4.5001, false, true));
        Assert.assertTrue(BoundUtility.inBounds(4.5001, 4.4999, 4.5001, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001, false, true));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001, false, true));
        
        //long, touch upper bound
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L, false, true));
        Assert.assertFalse(BoundUtility.inBounds(123456788L, 123456788L, 123456790L, false, true));
        Assert.assertTrue(BoundUtility.inBounds(123456790L, 123456788L, 123456790L, false, true));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L, false, true));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L, false, true));
        
        //int, touch lower bound
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4, 4, 6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(6, 2, 6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6, true, false));
        
        //float, touch lower bound
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.5, 6.4, true, false));
        Assert.assertFalse(BoundUtility.inBounds(6.4, 2.4, 6.4, true, false));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4, true, false));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4.4, 4.4, 4.6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.6, 4.4, 4.6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6, true, false));
        
        //double, touch lower bound
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001, true, false));
        Assert.assertTrue(BoundUtility.inBounds(4.4999, 4.4999, 4.5001, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5001, 4.4999, 4.5001, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001, true, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001, true, false));
        
        //long, touch lower bound
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L, true, false));
        Assert.assertTrue(BoundUtility.inBounds(123456788L, 123456788L, 123456790L, true, false));
        Assert.assertFalse(BoundUtility.inBounds(123456790L, 123456788L, 123456790L, true, false));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L, true, false));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L, true, false));
        
        //int, no touching bounds
        Assert.assertTrue(BoundUtility.inBounds(4, 2, 6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4, 4, 6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(6, 2, 6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(1, 4, 6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(7, 4, 6, false, false));
        
        //float, no touching bounds
        Assert.assertTrue(BoundUtility.inBounds(4.5, 2.4, 6.4, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5, 4.5, 6.4, false, false));
        Assert.assertFalse(BoundUtility.inBounds(6.4, 2.4, 6.4, false, false));
        Assert.assertFalse(BoundUtility.inBounds(1.9, 4.5, 6.4, false, false));
        Assert.assertFalse(BoundUtility.inBounds(7.1, 4.5, 6.4, false, false));
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4, 4.6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.4, 4.4, 4.6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.6, 4.4, 4.6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.3, 4.4, 4.6, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.7, 4.4, 4.6, false, false));
        
        //double, no touching bounds
        Assert.assertTrue(BoundUtility.inBounds(4.5, 4.4999, 4.5001, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.4999, 4.4999, 4.5001, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5001, 4.4999, 4.5001, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.4998, 4.4999, 4.5001, false, false));
        Assert.assertFalse(BoundUtility.inBounds(4.5002, 4.4999, 4.5001, false, false));
        
        //long, no touching bounds
        Assert.assertTrue(BoundUtility.inBounds(123456789L, 123456788L, 123456790L, false, false));
        Assert.assertFalse(BoundUtility.inBounds(123456788L, 123456788L, 123456790L, false, false));
        Assert.assertFalse(BoundUtility.inBounds(123456790L, 123456788L, 123456790L, false, false));
        Assert.assertFalse(BoundUtility.inBounds(123456787L, 123456788L, 123456790L, false, false));
        Assert.assertFalse(BoundUtility.inBounds(123456791L, 123456788L, 123456790L, false, false));
        
        //edge cases
        Assert.assertTrue(BoundUtility.inBounds(0, 0, 0));
        Assert.assertFalse(BoundUtility.inBounds(0, 0, 0, true, false));
        Assert.assertFalse(BoundUtility.inBounds(0, 0, 0, false, true));
        Assert.assertFalse(BoundUtility.inBounds(0, 0, 0, false, false));
    }
    
    /**
     * JUnit test of indexInBounds.
     *
     * @throws Exception When there is an exception.
     * @see BoundUtility#indexInBounds(int, int)
     */
    @Test
    public void testIndexInBounds() throws Exception {
        //standard
        Assert.assertTrue(BoundUtility.indexInBounds(0, 1));
        Assert.assertFalse(BoundUtility.indexInBounds(1, 1));
        Assert.assertFalse(BoundUtility.indexInBounds(-1, 1));
        Assert.assertTrue(BoundUtility.indexInBounds(0, 4));
        Assert.assertTrue(BoundUtility.indexInBounds(2, 4));
        Assert.assertTrue(BoundUtility.indexInBounds(3, 4));
        Assert.assertFalse(BoundUtility.indexInBounds(4, 4));
        Assert.assertFalse(BoundUtility.indexInBounds(-1, 4));
        Assert.assertTrue(BoundUtility.indexInBounds(5, 7));
        Assert.assertTrue(BoundUtility.indexInBounds(6, 7));
        Assert.assertFalse(BoundUtility.indexInBounds(7, 7));
        Assert.assertFalse(BoundUtility.indexInBounds(-1, 7));
        
        //edge cases
        Assert.assertFalse(BoundUtility.indexInBounds(0, 0));
        Assert.assertFalse(BoundUtility.indexInBounds(3, 0));
        Assert.assertFalse(BoundUtility.indexInBounds(-1, 0));
        Assert.assertFalse(BoundUtility.indexInBounds(0, -1));
        Assert.assertFalse(BoundUtility.indexInBounds(3, -1));
        Assert.assertFalse(BoundUtility.indexInBounds(-1, -1));
    }
    
    /**
     * JUnit test of inArrayBounds.
     *
     * @throws Exception When there is an exception.
     * @see BoundUtility#inArrayBounds(int, Object[])
     */
    @Test
    public void testInArrayBounds() throws Exception {
        final Object[] array0 = ArrayUtility.create(Object.class, 0);
        final String[] array1 = ArrayUtility.create(String.class, 1);
        final Integer[] array4 = ArrayUtility.create(Integer.class, 4);
        final Boolean[] array7 = ArrayUtility.create(Boolean.class, 7);
        
        //standard
        Assert.assertTrue(BoundUtility.inArrayBounds(0, array1));
        Assert.assertFalse(BoundUtility.inArrayBounds(1, array1));
        Assert.assertFalse(BoundUtility.inArrayBounds(-1, array1));
        Assert.assertTrue(BoundUtility.inArrayBounds(0, array4));
        Assert.assertTrue(BoundUtility.inArrayBounds(2, array4));
        Assert.assertTrue(BoundUtility.inArrayBounds(3, array4));
        Assert.assertFalse(BoundUtility.inArrayBounds(4, array4));
        Assert.assertFalse(BoundUtility.inArrayBounds(-1, array4));
        Assert.assertTrue(BoundUtility.inArrayBounds(5, array7));
        Assert.assertTrue(BoundUtility.inArrayBounds(6, array7));
        Assert.assertFalse(BoundUtility.inArrayBounds(7, array7));
        Assert.assertFalse(BoundUtility.inArrayBounds(-1, array7));
        
        //edge cases
        Assert.assertFalse(BoundUtility.inArrayBounds(0, array0));
        Assert.assertFalse(BoundUtility.inArrayBounds(3, array0));
        Assert.assertFalse(BoundUtility.inArrayBounds(-1, array0));
        TestUtils.assertException(NullPointerException.class, () ->
                BoundUtility.inArrayBounds(0, null));
    }
    
    /**
     * JUnit test of inListBounds.
     *
     * @throws Exception When there is an exception.
     * @see BoundUtility#inListBounds(int, List)
     */
    @Test
    public void testInListBounds() throws Exception {
        final List<Object> list0 = ListUtility.create(Object.class, 0);
        final List<String> list1 = ListUtility.create(String.class, 1);
        final List<Integer> list4 = ListUtility.create(Integer.class, 4);
        final List<Boolean> list7 = ListUtility.create(Boolean.class, 7);
        
        //standard
        Assert.assertTrue(BoundUtility.inListBounds(0, list1));
        Assert.assertFalse(BoundUtility.inListBounds(1, list1));
        Assert.assertFalse(BoundUtility.inListBounds(-1, list1));
        Assert.assertTrue(BoundUtility.inListBounds(0, list4));
        Assert.assertTrue(BoundUtility.inListBounds(2, list4));
        Assert.assertTrue(BoundUtility.inListBounds(3, list4));
        Assert.assertFalse(BoundUtility.inListBounds(4, list4));
        Assert.assertFalse(BoundUtility.inListBounds(-1, list4));
        Assert.assertTrue(BoundUtility.inListBounds(5, list7));
        Assert.assertTrue(BoundUtility.inListBounds(6, list7));
        Assert.assertFalse(BoundUtility.inListBounds(7, list7));
        Assert.assertFalse(BoundUtility.inListBounds(-1, list7));
        
        //edge cases
        Assert.assertFalse(BoundUtility.inListBounds(0, list0));
        Assert.assertFalse(BoundUtility.inListBounds(3, list0));
        Assert.assertFalse(BoundUtility.inListBounds(-1, list0));
        TestUtils.assertException(NullPointerException.class, () ->
                BoundUtility.inListBounds(0, null));
    }
    
    /**
     * JUnit test of truncate.
     *
     * @throws Exception When there is an exception.
     * @see BoundUtility#truncate(Number, Number, Number)
     */
    @Test
    public void testTruncate() throws Exception {
        //byte
        Assert.assertEquals((byte) 4, BoundUtility.truncate((byte) 4, (byte) 2, (byte) 6).byteValue());
        Assert.assertEquals((byte) 4, BoundUtility.truncate((byte) 4, (byte) 4, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, BoundUtility.truncate((byte) 6, (byte) 2, (byte) 6).byteValue());
        Assert.assertEquals((byte) 4, BoundUtility.truncate((byte) 1, (byte) 4, (byte) 6).byteValue());
        Assert.assertEquals((byte) 6, BoundUtility.truncate((byte) 7, (byte) 4, (byte) 6).byteValue());
        Assert.assertEquals((byte) -2, BoundUtility.truncate((byte) -2, (byte) -4, (byte) 6).byteValue());
        Assert.assertEquals((byte) -1, BoundUtility.truncate((byte) -2, (byte) -1, (byte) 6).byteValue());
        
        //short
        Assert.assertEquals((short) 44, BoundUtility.truncate((short) 44, (short) 22, (short) 69).shortValue());
        Assert.assertEquals((short) 48, BoundUtility.truncate((short) 48, (short) 48, (short) 69).shortValue());
        Assert.assertEquals((short) 69, BoundUtility.truncate((short) 69, (short) 22, (short) 69).shortValue());
        Assert.assertEquals((short) 40, BoundUtility.truncate((short) 11, (short) 40, (short) 61).shortValue());
        Assert.assertEquals((short) 61, BoundUtility.truncate((short) 78, (short) 40, (short) 61).shortValue());
        Assert.assertEquals((short) -27, BoundUtility.truncate((short) -27, (short) -40, (short) 61).shortValue());
        Assert.assertEquals((short) -11, BoundUtility.truncate((short) -27, (short) -11, (short) 61).shortValue());
        
        //int
        Assert.assertEquals(446, BoundUtility.truncate(446, 222, 699).intValue());
        Assert.assertEquals(481, BoundUtility.truncate(481, 481, 699).intValue());
        Assert.assertEquals(699, BoundUtility.truncate(699, 222, 699).intValue());
        Assert.assertEquals(400, BoundUtility.truncate(11, 400, 617).intValue());
        Assert.assertEquals(617, BoundUtility.truncate(737, 400, 617).intValue());
        Assert.assertEquals(-278, BoundUtility.truncate(-278, -400, 617).intValue());
        Assert.assertEquals(-113, BoundUtility.truncate(-278, -113, 617).intValue());
        
        //float
        Assert.assertEquals(4.46f, BoundUtility.truncate(4.46f, 2.22f, 6.99f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(4.81f, BoundUtility.truncate(4.81f, 4.81f, 6.99f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.99f, BoundUtility.truncate(6.99f, 2.22f, 6.99f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(4.00f, BoundUtility.truncate(1.1f, 4.00f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(6.17f, BoundUtility.truncate(7.37f, 4.00f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(-2.78f, BoundUtility.truncate(-2.78f, -4.00f, 6.17f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(-1.13f, BoundUtility.truncate(-2.78f, -1.13f, 6.17f), TestUtils.DELTA_FLOAT);
        
        //double
        Assert.assertEquals(4.469845d, BoundUtility.truncate(4.469845d, 2.228808404d, 6.99198702d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(4.8100154d, BoundUtility.truncate(4.8100154d, 4.8100154d, 6.99198702d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.99198702d, BoundUtility.truncate(6.99198702d, 2.228808404d, 6.99198702d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(4.0000001d, BoundUtility.truncate(1.1d, 4.0000001d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(6.17348095d, BoundUtility.truncate(7.37980798d, 4.0000001d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(-2.7858074887d, BoundUtility.truncate(-2.7858074887d, -4.0000001d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(-1.139805605774d, BoundUtility.truncate(-2.7858074887d, -1.139805605774d, 6.17348095d), TestUtils.DELTA_DOUBLE);
        
        //long
        Assert.assertEquals(4469845000L, BoundUtility.truncate(4469845000L, 2228808404L, 6991987020L).longValue());
        Assert.assertEquals(48100154L, BoundUtility.truncate(48100154L, 48100154L, 6991987020L).longValue());
        Assert.assertEquals(6991987020L, BoundUtility.truncate(6991987020L, 2228808404L, 6991987020L).longValue());
        Assert.assertEquals(40000001L, BoundUtility.truncate(11L, 40000001L, 617348095L).longValue());
        Assert.assertEquals(617348095L, BoundUtility.truncate(737980798L, 40000001L, 617348095L).longValue());
        Assert.assertEquals(-27858074887L, BoundUtility.truncate(-27858074887L, -40000001000L, 617348095L).longValue());
        Assert.assertEquals(-11398056057L, BoundUtility.truncate(-27858074887L, -11398056057L, 617348095L).longValue());
        
        //big decimal
        Assert.assertEquals(new BigDecimal("498048940365649408940894156.46989846504649845"), BoundUtility.truncate(new BigDecimal("498048940365649408940894156.46989846504649845"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")));
        Assert.assertEquals(new BigDecimal("4161561587498065454121635654.0000000006546508100154"), BoundUtility.truncate(new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("4161561587498065454121635654.0000000006546508100154"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")));
        Assert.assertEquals(new BigDecimal("690890450454565456960987894649.991987020989845645645458"), BoundUtility.truncate(new BigDecimal("690890450454565456960987894649.991987020989845645645458"), new BigDecimal("298766565984054313210050444.22850908748485456409594808404"), new BigDecimal("690890450454565456960987894649.991987020989845645645458")));
        Assert.assertEquals(new BigDecimal("400000000000000000000.000000000000000000000001"), BoundUtility.truncate(new BigDecimal("1.1"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("6664598409846520651984845149.1734680098980487489408995"), BoundUtility.truncate(new BigDecimal("70000000000490988798465065653212318888.370989889489740546506505156980798"), new BigDecimal("400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("-234605409480456054264.785804564655496574887"), BoundUtility.truncate(new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-400000000000000000000.000000000000000000000001"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        Assert.assertEquals(new BigDecimal("-100870798769504941324.139805605774"), BoundUtility.truncate(new BigDecimal("-234605409480456054264.785804564655496574887"), new BigDecimal("-100870798769504941324.139805605774"), new BigDecimal("6664598409846520651984845149.1734680098980487489408995")));
        
        //big integer
        Assert.assertEquals(new BigInteger("498048940365649408940894156"), BoundUtility.truncate(new BigInteger("498048940365649408940894156"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")));
        Assert.assertEquals(new BigInteger("4161561587498065454121635654"), BoundUtility.truncate(new BigInteger("4161561587498065454121635654"), new BigInteger("4161561587498065454121635654"), new BigInteger("690890450454565456960987894649")));
        Assert.assertEquals(new BigInteger("690890450454565456960987894649"), BoundUtility.truncate(new BigInteger("690890450454565456960987894649"), new BigInteger("298766565984054313210050444"), new BigInteger("690890450454565456960987894649")));
        Assert.assertEquals(new BigInteger("400000000000000000000"), BoundUtility.truncate(new BigInteger("1"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("6664598409846520651984845149"), BoundUtility.truncate(new BigInteger("70000000000490988798465065653212318888"), new BigInteger("400000000000000000000"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("-234605409480456054264"), BoundUtility.truncate(new BigInteger("-234605409480456054264"), new BigInteger("-400000000000000000000"), new BigInteger("6664598409846520651984845149")));
        Assert.assertEquals(new BigInteger("-100870798769504941324"), BoundUtility.truncate(new BigInteger("-234605409480456054264"), new BigInteger("-100870798769504941324"), new BigInteger("6664598409846520651984845149")));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                BoundUtility.truncate(15, 10, null));
        TestUtils.assertException(NullPointerException.class, () ->
                BoundUtility.truncate(15, null, 25));
        TestUtils.assertException(NullPointerException.class, () ->
                BoundUtility.truncate(null, 10, 25));
        TestUtils.assertException(NullPointerException.class, () ->
                BoundUtility.truncate(null, null, null));
    }
    
}
