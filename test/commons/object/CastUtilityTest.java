/*
 * File:    CastUtilityTest.java
 * Package: object
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
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
 * JUnit test of CastUtility.
 *
 * @see CastUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CastUtility.class})
public class CastUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CastUtilityTest.class);
    
    
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
     * JUnit test of toBoolean.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toBoolean(Object)
     */
    @SuppressWarnings("UnnecessaryUnboxing")
    @Test
    public void testToBoolean() throws Exception {
        //boolean
        Assert.assertTrue(CastUtility.toBoolean(Boolean.TRUE).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(Boolean.FALSE).booleanValue());
        Assert.assertTrue(CastUtility.toBoolean(true).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(false).booleanValue());
        
        //byte
        Assert.assertFalse(CastUtility.toBoolean((byte) 1).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean((byte) 0).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean((byte) 6).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean((byte) -2).booleanValue());
        
        //short
        Assert.assertFalse(CastUtility.toBoolean((short) 1).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean((short) 0).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean((short) 32100).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean((short) -11054).booleanValue());
        
        //int
        Assert.assertFalse(CastUtility.toBoolean(1).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(0).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(6415123).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(-115411).booleanValue());
        
        //long
        Assert.assertFalse(CastUtility.toBoolean(1L).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(0L).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(15624653112L).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(-57988744511L).booleanValue());
        
        //float
        Assert.assertFalse(CastUtility.toBoolean(1.0f).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(0.0f).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(984.561f).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(-8.154f).booleanValue());
        
        //double
        Assert.assertFalse(CastUtility.toBoolean(1.0d).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(0.0d).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(984.565489421d).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(-8.154874652d).booleanValue());
        
        //big integer
        Assert.assertFalse(CastUtility.toBoolean(new BigInteger("1")).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(new BigInteger("0")).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(new BigInteger("948414649432084846513216")).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(new BigInteger("-1897562164944876591156")).booleanValue());
        
        //big decimal
        Assert.assertFalse(CastUtility.toBoolean(new BigDecimal("1")).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(new BigDecimal("0")).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(new BigDecimal("948414649432084846513216.54689231")).booleanValue());
        Assert.assertFalse(CastUtility.toBoolean(new BigDecimal("-1897562164959.0005484897")).booleanValue());
        
        //char
        Assert.assertFalse(CastUtility.toBoolean('t').booleanValue());
        Assert.assertFalse(CastUtility.toBoolean('f').booleanValue());
        Assert.assertFalse(CastUtility.toBoolean('0').booleanValue());
        Assert.assertFalse(CastUtility.toBoolean('1').booleanValue());
        Assert.assertFalse(CastUtility.toBoolean('6').booleanValue());
        Assert.assertFalse(CastUtility.toBoolean('n').booleanValue());
        Assert.assertFalse(CastUtility.toBoolean('\r').booleanValue());
        
        //string
        Assert.assertTrue(CastUtility.toBoolean("true").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("false").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("t").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("f").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("1").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("0").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("6").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("-2").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("1564984").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("1564567897562131").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("1564567897562131.6494564").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("test").booleanValue());
        Assert.assertFalse(CastUtility.toBoolean("").booleanValue());
        
        //invalid
        Assert.assertNull(CastUtility.toBoolean(null));
    }
    
    /**
     * JUnit test of toByte.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toByte(Object)
     */
    @Test
    public void testToByte() throws Exception {
        //boolean
        Assert.assertNull(CastUtility.toByte(Boolean.TRUE));
        Assert.assertNull(CastUtility.toByte(Boolean.FALSE));
        Assert.assertNull(CastUtility.toByte(true));
        Assert.assertNull(CastUtility.toByte(false));
        
        //byte
        Assert.assertEquals(1, CastUtility.toByte((byte) 1).byteValue());
        Assert.assertEquals(0, CastUtility.toByte((byte) 0).byteValue());
        Assert.assertEquals(6, CastUtility.toByte((byte) 6).byteValue());
        Assert.assertEquals(-2, CastUtility.toByte((byte) -2).byteValue());
        
        //short
        Assert.assertEquals(1, CastUtility.toByte((short) 1).byteValue());
        Assert.assertEquals(0, CastUtility.toByte((short) 0).byteValue());
        Assert.assertNull(CastUtility.toByte((short) 32100));
        Assert.assertNull(CastUtility.toByte((short) -11054));
        
        //int
        Assert.assertEquals(1, CastUtility.toByte(1).byteValue());
        Assert.assertEquals(0, CastUtility.toByte(0).byteValue());
        Assert.assertNull(CastUtility.toByte(6415123));
        Assert.assertNull(CastUtility.toByte(-115411));
        
        //long
        Assert.assertEquals(1, CastUtility.toByte(1L).byteValue());
        Assert.assertEquals(0, CastUtility.toByte(0L).byteValue());
        Assert.assertNull(CastUtility.toByte(15624653112L));
        Assert.assertNull(CastUtility.toByte(-57988744511L));
        
        //float
        Assert.assertNull(CastUtility.toByte(1.0f));
        Assert.assertNull(CastUtility.toByte(0.0f));
        Assert.assertNull(CastUtility.toByte(984.561f));
        Assert.assertNull(CastUtility.toByte(-8.154f));
        
        //double
        Assert.assertNull(CastUtility.toByte(1.0d));
        Assert.assertNull(CastUtility.toByte(0.0d));
        Assert.assertNull(CastUtility.toByte(984.565489421d));
        Assert.assertNull(CastUtility.toByte(-8.154874652d));
        
        //big integer
        Assert.assertEquals(1, CastUtility.toByte(new BigInteger("1")).byteValue());
        Assert.assertEquals(0, CastUtility.toByte(new BigInteger("0")).byteValue());
        Assert.assertNull(CastUtility.toByte(new BigInteger("948414649432084846513216")));
        Assert.assertNull(CastUtility.toByte(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(1, CastUtility.toByte(new BigDecimal("1")).byteValue());
        Assert.assertEquals(0, CastUtility.toByte(new BigDecimal("0")).byteValue());
        Assert.assertNull(CastUtility.toByte(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(CastUtility.toByte(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(CastUtility.toByte('t'));
        Assert.assertNull(CastUtility.toByte('f'));
        Assert.assertEquals(0, CastUtility.toByte('0').byteValue());
        Assert.assertEquals(1, CastUtility.toByte('1').byteValue());
        Assert.assertEquals(6, CastUtility.toByte('6').byteValue());
        Assert.assertNull(CastUtility.toByte('n'));
        Assert.assertNull(CastUtility.toByte('\r'));
        
        //string
        Assert.assertNull(CastUtility.toByte("true"));
        Assert.assertNull(CastUtility.toByte("false"));
        Assert.assertNull(CastUtility.toByte("t"));
        Assert.assertNull(CastUtility.toByte("f"));
        Assert.assertEquals(1, CastUtility.toByte("1").byteValue());
        Assert.assertEquals(0, CastUtility.toByte("0").byteValue());
        Assert.assertEquals(6, CastUtility.toByte("6").byteValue());
        Assert.assertEquals(-2, CastUtility.toByte("-2").byteValue());
        Assert.assertNull(CastUtility.toByte("1564984"));
        Assert.assertNull(CastUtility.toByte("1564567897562131"));
        Assert.assertNull(CastUtility.toByte("1564567897562131.6494564"));
        Assert.assertNull(CastUtility.toByte("test"));
        Assert.assertNull(CastUtility.toByte(""));
        
        //invalid
        Assert.assertNull(CastUtility.toByte(null));
    }
    
    /**
     * JUnit test of toShort.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toShort(Object)
     */
    @Test
    public void testToShort() throws Exception {
        //boolean
        Assert.assertNull(CastUtility.toShort(Boolean.TRUE));
        Assert.assertNull(CastUtility.toShort(Boolean.FALSE));
        Assert.assertNull(CastUtility.toShort(true));
        Assert.assertNull(CastUtility.toShort(false));
        
        //byte
        Assert.assertEquals(1, CastUtility.toShort((byte) 1).shortValue());
        Assert.assertEquals(0, CastUtility.toShort((byte) 0).shortValue());
        Assert.assertEquals(6, CastUtility.toShort((byte) 6).shortValue());
        Assert.assertEquals(-2, CastUtility.toShort((byte) -2).shortValue());
        
        //short
        Assert.assertEquals(1, CastUtility.toShort((short) 1).shortValue());
        Assert.assertEquals(0, CastUtility.toShort((short) 0).shortValue());
        Assert.assertEquals(32100, CastUtility.toShort((short) 32100).shortValue());
        Assert.assertEquals(-11054, CastUtility.toShort((short) -11054).shortValue());
        
        //int
        Assert.assertEquals(1, CastUtility.toShort(1).shortValue());
        Assert.assertEquals(0, CastUtility.toShort(0).shortValue());
        Assert.assertNull(CastUtility.toShort(6415123));
        Assert.assertNull(CastUtility.toShort(-115411));
        
        //long
        Assert.assertEquals(1, CastUtility.toShort(1L).shortValue());
        Assert.assertEquals(0, CastUtility.toShort(0L).shortValue());
        Assert.assertNull(CastUtility.toShort(15624653112L));
        Assert.assertNull(CastUtility.toShort(-57988744511L));
        
        //float
        Assert.assertNull(CastUtility.toShort(1.0f));
        Assert.assertNull(CastUtility.toShort(0.0f));
        Assert.assertNull(CastUtility.toShort(984.561f));
        Assert.assertNull(CastUtility.toShort(-8.154f));
        
        //double
        Assert.assertNull(CastUtility.toShort(1.0d));
        Assert.assertNull(CastUtility.toShort(0.0d));
        Assert.assertNull(CastUtility.toShort(984.565489421d));
        Assert.assertNull(CastUtility.toShort(-8.154874652d));
        
        //big integer
        Assert.assertEquals(1, CastUtility.toShort(new BigInteger("1")).shortValue());
        Assert.assertEquals(0, CastUtility.toShort(new BigInteger("0")).shortValue());
        Assert.assertNull(CastUtility.toShort(new BigInteger("948414649432084846513216")));
        Assert.assertNull(CastUtility.toShort(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(1, CastUtility.toShort(new BigDecimal("1")).shortValue());
        Assert.assertEquals(0, CastUtility.toShort(new BigDecimal("0")).shortValue());
        Assert.assertNull(CastUtility.toShort(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(CastUtility.toShort(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(CastUtility.toShort('t'));
        Assert.assertNull(CastUtility.toShort('f'));
        Assert.assertEquals(0, CastUtility.toShort('0').shortValue());
        Assert.assertEquals(1, CastUtility.toShort('1').shortValue());
        Assert.assertEquals(6, CastUtility.toShort('6').shortValue());
        Assert.assertNull(CastUtility.toShort('n'));
        Assert.assertNull(CastUtility.toShort('\r'));
        
        //string
        Assert.assertNull(CastUtility.toShort("true"));
        Assert.assertNull(CastUtility.toShort("false"));
        Assert.assertNull(CastUtility.toShort("t"));
        Assert.assertNull(CastUtility.toShort("f"));
        Assert.assertEquals(1, CastUtility.toShort("1").shortValue());
        Assert.assertEquals(0, CastUtility.toShort("0").shortValue());
        Assert.assertEquals(6, CastUtility.toShort("6").shortValue());
        Assert.assertEquals(-2, CastUtility.toShort("-2").shortValue());
        Assert.assertNull(CastUtility.toShort("1564984"));
        Assert.assertNull(CastUtility.toShort("1564567897562131"));
        Assert.assertNull(CastUtility.toShort("1564567897562131.6494564"));
        Assert.assertNull(CastUtility.toShort("test"));
        Assert.assertNull(CastUtility.toShort(""));
        
        //invalid
        Assert.assertNull(CastUtility.toShort(null));
    }
    
    /**
     * JUnit test of toInt.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toInt(Object)
     */
    @Test
    public void testToInt() throws Exception {
        //boolean
        Assert.assertNull(CastUtility.toInt(Boolean.TRUE));
        Assert.assertNull(CastUtility.toInt(Boolean.FALSE));
        Assert.assertNull(CastUtility.toInt(true));
        Assert.assertNull(CastUtility.toInt(false));
        
        //byte
        Assert.assertEquals(1, CastUtility.toInt((byte) 1).intValue());
        Assert.assertEquals(0, CastUtility.toInt((byte) 0).intValue());
        Assert.assertEquals(6, CastUtility.toInt((byte) 6).intValue());
        Assert.assertEquals(-2, CastUtility.toInt((byte) -2).intValue());
        
        //short
        Assert.assertEquals(1, CastUtility.toInt((short) 1).intValue());
        Assert.assertEquals(0, CastUtility.toInt((short) 0).intValue());
        Assert.assertEquals(32100, CastUtility.toInt((short) 32100).intValue());
        Assert.assertEquals(-11054, CastUtility.toInt((short) -11054).intValue());
        
        //int
        Assert.assertEquals(1, CastUtility.toInt(1).intValue());
        Assert.assertEquals(0, CastUtility.toInt(0).intValue());
        Assert.assertEquals(6415123, CastUtility.toInt(6415123).intValue());
        Assert.assertEquals(-115411, CastUtility.toInt(-115411).intValue());
        
        //long
        Assert.assertEquals(1, CastUtility.toInt(1L).intValue());
        Assert.assertEquals(0, CastUtility.toInt(0L).intValue());
        Assert.assertNull(CastUtility.toInt(15624653112L));
        Assert.assertNull(CastUtility.toInt(-57988744511L));
        
        //float
        Assert.assertNull(CastUtility.toInt(1.0f));
        Assert.assertNull(CastUtility.toInt(0.0f));
        Assert.assertNull(CastUtility.toInt(984.561f));
        Assert.assertNull(CastUtility.toInt(-8.154f));
        
        //double
        Assert.assertNull(CastUtility.toInt(1.0d));
        Assert.assertNull(CastUtility.toInt(0.0d));
        Assert.assertNull(CastUtility.toInt(984.565489421d));
        Assert.assertNull(CastUtility.toInt(-8.154874652d));
        
        //big integer
        Assert.assertEquals(1, CastUtility.toInt(new BigInteger("1")).intValue());
        Assert.assertEquals(0, CastUtility.toInt(new BigInteger("0")).intValue());
        Assert.assertNull(CastUtility.toInt(new BigInteger("948414649432084846513216")));
        Assert.assertNull(CastUtility.toInt(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(1, CastUtility.toInt(new BigDecimal("1")).intValue());
        Assert.assertEquals(0, CastUtility.toInt(new BigDecimal("0")).intValue());
        Assert.assertNull(CastUtility.toInt(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(CastUtility.toInt(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(CastUtility.toInt('t'));
        Assert.assertNull(CastUtility.toInt('f'));
        Assert.assertEquals(0, CastUtility.toInt('0').intValue());
        Assert.assertEquals(1, CastUtility.toInt('1').intValue());
        Assert.assertEquals(6, CastUtility.toInt('6').intValue());
        Assert.assertNull(CastUtility.toInt('n'));
        Assert.assertNull(CastUtility.toInt('\r'));
        
        //string
        Assert.assertNull(CastUtility.toInt("true"));
        Assert.assertNull(CastUtility.toInt("false"));
        Assert.assertNull(CastUtility.toInt("t"));
        Assert.assertNull(CastUtility.toInt("f"));
        Assert.assertEquals(1, CastUtility.toInt("1").intValue());
        Assert.assertEquals(0, CastUtility.toInt("0").intValue());
        Assert.assertEquals(6, CastUtility.toInt("6").intValue());
        Assert.assertEquals(-2, CastUtility.toInt("-2").intValue());
        Assert.assertEquals(1564984, CastUtility.toInt("1564984").intValue());
        Assert.assertNull(CastUtility.toInt("1564567897562131"));
        Assert.assertNull(CastUtility.toInt("1564567897562131.6494564"));
        Assert.assertNull(CastUtility.toInt("test"));
        Assert.assertNull(CastUtility.toInt(""));
        
        //invalid
        Assert.assertNull(CastUtility.toInt(null));
    }
    
    /**
     * JUnit test of toLong.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toLong(Object)
     */
    @Test
    public void testToLong() throws Exception {
        //boolean
        Assert.assertNull(CastUtility.toLong(Boolean.TRUE));
        Assert.assertNull(CastUtility.toLong(Boolean.FALSE));
        Assert.assertNull(CastUtility.toLong(true));
        Assert.assertNull(CastUtility.toLong(false));
        
        //byte
        Assert.assertEquals(1, CastUtility.toLong((byte) 1).longValue());
        Assert.assertEquals(0, CastUtility.toLong((byte) 0).longValue());
        Assert.assertEquals(6, CastUtility.toLong((byte) 6).longValue());
        Assert.assertEquals(-2, CastUtility.toLong((byte) -2).longValue());
        
        //short
        Assert.assertEquals(1, CastUtility.toLong((short) 1).longValue());
        Assert.assertEquals(0, CastUtility.toLong((short) 0).longValue());
        Assert.assertEquals(32100, CastUtility.toLong((short) 32100).longValue());
        Assert.assertEquals(-11054, CastUtility.toLong((short) -11054).longValue());
        
        //int
        Assert.assertEquals(1, CastUtility.toLong(1).longValue());
        Assert.assertEquals(0, CastUtility.toLong(0).longValue());
        Assert.assertEquals(6415123, CastUtility.toLong(6415123).longValue());
        Assert.assertEquals(-115411, CastUtility.toLong(-115411).longValue());
        
        //long
        Assert.assertEquals(1, CastUtility.toLong(1L).longValue());
        Assert.assertEquals(0, CastUtility.toLong(0L).longValue());
        Assert.assertEquals(15624653112L, CastUtility.toLong(15624653112L).longValue());
        Assert.assertEquals(-57988744511L, CastUtility.toLong(-57988744511L).longValue());
        
        //float
        Assert.assertNull(CastUtility.toLong(1.0f));
        Assert.assertNull(CastUtility.toLong(0.0f));
        Assert.assertNull(CastUtility.toLong(984.561f));
        Assert.assertNull(CastUtility.toLong(-8.154f));
        
        //double
        Assert.assertNull(CastUtility.toLong(1.0d));
        Assert.assertNull(CastUtility.toLong(0.0d));
        Assert.assertNull(CastUtility.toLong(984.565489421d));
        Assert.assertNull(CastUtility.toLong(-8.154874652d));
        
        //big integer
        Assert.assertEquals(1, CastUtility.toLong(new BigInteger("1")).longValue());
        Assert.assertEquals(0, CastUtility.toLong(new BigInteger("0")).longValue());
        Assert.assertNull(CastUtility.toLong(new BigInteger("948414649432084846513216")));
        Assert.assertNull(CastUtility.toLong(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(1, CastUtility.toLong(new BigDecimal("1")).longValue());
        Assert.assertEquals(0, CastUtility.toLong(new BigDecimal("0")).longValue());
        Assert.assertNull(CastUtility.toLong(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(CastUtility.toLong(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(CastUtility.toLong('t'));
        Assert.assertNull(CastUtility.toLong('f'));
        Assert.assertEquals(0, CastUtility.toLong('0').longValue());
        Assert.assertEquals(1, CastUtility.toLong('1').longValue());
        Assert.assertEquals(6, CastUtility.toLong('6').longValue());
        Assert.assertNull(CastUtility.toLong('n'));
        Assert.assertNull(CastUtility.toLong('\r'));
        
        //string
        Assert.assertNull(CastUtility.toLong("true"));
        Assert.assertNull(CastUtility.toLong("false"));
        Assert.assertNull(CastUtility.toLong("t"));
        Assert.assertNull(CastUtility.toLong("f"));
        Assert.assertEquals(1, CastUtility.toLong("1").longValue());
        Assert.assertEquals(0, CastUtility.toLong("0").longValue());
        Assert.assertEquals(6, CastUtility.toLong("6").longValue());
        Assert.assertEquals(-2, CastUtility.toLong("-2").longValue());
        Assert.assertEquals(1564984, CastUtility.toLong("1564984").longValue());
        Assert.assertEquals(1564567897562131L, CastUtility.toLong("1564567897562131").longValue());
        Assert.assertNull(CastUtility.toLong("1564567897562131.6494564"));
        Assert.assertNull(CastUtility.toLong("test"));
        Assert.assertNull(CastUtility.toLong(""));
        
        //invalid
        Assert.assertNull(CastUtility.toLong(null));
    }
    
    /**
     * JUnit test of toFloat.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toFloat(Object)
     */
    @SuppressWarnings("UnnecessaryUnboxing")
    @Test
    public void testToFloat() throws Exception {
        //boolean
        Assert.assertNull(CastUtility.toFloat(Boolean.TRUE));
        Assert.assertNull(CastUtility.toFloat(Boolean.FALSE));
        Assert.assertNull(CastUtility.toFloat(true));
        Assert.assertNull(CastUtility.toFloat(false));
        
        //byte
        Assert.assertEquals(1, CastUtility.toFloat((byte) 1).floatValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toFloat((byte) 0).floatValue(), 0.0000001);
        Assert.assertEquals(6, CastUtility.toFloat((byte) 6).floatValue(), 0.0000001);
        Assert.assertEquals(-2, CastUtility.toFloat((byte) -2).floatValue(), 0.0000001);
        
        //short
        Assert.assertEquals(1, CastUtility.toFloat((short) 1).floatValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toFloat((short) 0).floatValue(), 0.0000001);
        Assert.assertEquals(32100, CastUtility.toFloat((short) 32100).floatValue(), 0.0000001);
        Assert.assertEquals(-11054, CastUtility.toFloat((short) -11054).floatValue(), 0.0000001);
        
        //int
        Assert.assertEquals(1, CastUtility.toFloat(1).floatValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toFloat(0).floatValue(), 0.0000001);
        Assert.assertEquals(6415123, CastUtility.toFloat(6415123).floatValue(), 0.0000001);
        Assert.assertEquals(-115411, CastUtility.toFloat(-115411).floatValue(), 0.0000001);
        
        //long
        Assert.assertEquals(1, CastUtility.toFloat(1L).floatValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toFloat(0L).floatValue(), 0.0000001);
        Assert.assertEquals(1.56246528E10, CastUtility.toFloat(15624653112L).floatValue(), 0.0000001);
        Assert.assertEquals(-5.7988743168E10, CastUtility.toFloat(-57988744511L).floatValue(), 0.0000001);
        
        //float
        Assert.assertEquals(1.0, CastUtility.toFloat(1.0f).floatValue(), 0.0000001);
        Assert.assertEquals(0.0, CastUtility.toFloat(0.0f).floatValue(), 0.0000001);
        Assert.assertEquals(984.5609741210938, CastUtility.toFloat(984.561f).floatValue(), 0.0000001);
        Assert.assertEquals(-8.154000282287598, CastUtility.toFloat(-8.154f).floatValue(), 0.0000001);
        
        //double
        Assert.assertEquals(1.0, CastUtility.toFloat(1.0d).floatValue(), 0.0000001);
        Assert.assertEquals(0.0, CastUtility.toFloat(0.0d).floatValue(), 0.0000001);
        Assert.assertEquals(984.5654907226562, CastUtility.toFloat(984.565489421d).floatValue(), 0.0000001);
        Assert.assertEquals(-8.154874801635742, CastUtility.toFloat(-8.154874652d).floatValue(), 0.0000001);
        
        //big integer
        Assert.assertEquals(1, CastUtility.toFloat(new BigInteger("1")).floatValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toFloat(new BigInteger("0")).floatValue(), 0.0000001);
        Assert.assertEquals(9.484146307950216E23, CastUtility.toFloat(new BigInteger("948414649432084846513216")).floatValue(), 0.0000001);
        Assert.assertEquals(-1.897562148120004E21, CastUtility.toFloat(new BigInteger("-1897562164944876591156")).floatValue(), 0.0000001);
        
        //big decimal
        Assert.assertEquals(1, CastUtility.toFloat(new BigDecimal("1")).floatValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toFloat(new BigDecimal("0")).floatValue(), 0.0000001);
        Assert.assertEquals(9.484146307950216E23, CastUtility.toFloat(new BigDecimal("948414649432084846513216.54689231")).floatValue(), 0.0000001);
        Assert.assertEquals(-1.897562112E12, CastUtility.toFloat(new BigDecimal("-1897562164959.0005484897")).floatValue(), 0.0000001);
        
        //char
        Assert.assertNull(CastUtility.toFloat('t'));
        Assert.assertNull(CastUtility.toFloat('f'));
        Assert.assertEquals(0, CastUtility.toFloat('0').floatValue(), 0.0000001);
        Assert.assertEquals(1, CastUtility.toFloat('1').floatValue(), 0.0000001);
        Assert.assertEquals(6, CastUtility.toFloat('6').floatValue(), 0.0000001);
        Assert.assertNull(CastUtility.toFloat('n'));
        Assert.assertNull(CastUtility.toFloat('\r'));
        
        //string
        Assert.assertNull(CastUtility.toFloat("true"));
        Assert.assertNull(CastUtility.toFloat("false"));
        Assert.assertNull(CastUtility.toFloat("t"));
        Assert.assertNull(CastUtility.toFloat("f"));
        Assert.assertEquals(1, CastUtility.toFloat("1").floatValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toFloat("0").floatValue(), 0.0000001);
        Assert.assertEquals(6, CastUtility.toFloat("6").floatValue(), 0.0000001);
        Assert.assertEquals(-2, CastUtility.toFloat("-2").floatValue(), 0.0000001);
        Assert.assertEquals(1564984, CastUtility.toFloat("1564984").floatValue(), 0.0000001);
        Assert.assertEquals(1.564567868014592E15, CastUtility.toFloat("1564567897562131").floatValue(), 0.0000001);
        Assert.assertEquals(1.564567868014592E15, CastUtility.toFloat("1564567897562131.6494564").floatValue(), 0.0000001);
        Assert.assertNull(CastUtility.toFloat("test"));
        Assert.assertNull(CastUtility.toFloat(""));
        
        //invalid
        Assert.assertNull(CastUtility.toFloat(null));
    }
    
    /**
     * JUnit test of toDouble.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toDouble(Object)
     */
    @SuppressWarnings("UnnecessaryUnboxing")
    @Test
    public void testToDouble() throws Exception {
        //boolean
        Assert.assertNull(CastUtility.toDouble(Boolean.TRUE));
        Assert.assertNull(CastUtility.toDouble(Boolean.FALSE));
        Assert.assertNull(CastUtility.toDouble(true));
        Assert.assertNull(CastUtility.toDouble(false));
        
        //byte
        Assert.assertEquals(1, CastUtility.toDouble((byte) 1).doubleValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toDouble((byte) 0).doubleValue(), 0.0000001);
        Assert.assertEquals(6, CastUtility.toDouble((byte) 6).doubleValue(), 0.0000001);
        Assert.assertEquals(-2, CastUtility.toDouble((byte) -2).doubleValue(), 0.0000001);
        
        //short
        Assert.assertEquals(1, CastUtility.toDouble((short) 1).doubleValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toDouble((short) 0).doubleValue(), 0.0000001);
        Assert.assertEquals(32100, CastUtility.toDouble((short) 32100).doubleValue(), 0.0000001);
        Assert.assertEquals(-11054, CastUtility.toDouble((short) -11054).doubleValue(), 0.0000001);
        
        //int
        Assert.assertEquals(1, CastUtility.toDouble(1).doubleValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toDouble(0).doubleValue(), 0.0000001);
        Assert.assertEquals(6415123, CastUtility.toDouble(6415123).doubleValue(), 0.0000001);
        Assert.assertEquals(-115411, CastUtility.toDouble(-115411).doubleValue(), 0.0000001);
        
        //long
        Assert.assertEquals(1, CastUtility.toDouble(1L).doubleValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toDouble(0L).doubleValue(), 0.0000001);
        Assert.assertEquals(1.5624653112E10, CastUtility.toDouble(15624653112L).doubleValue(), 0.0000001);
        Assert.assertEquals(-5.7988744511E10, CastUtility.toDouble(-57988744511L).doubleValue(), 0.0000001);
        
        //float
        Assert.assertEquals(1.0, CastUtility.toDouble(1.0f).doubleValue(), 0.0000001);
        Assert.assertEquals(0.0, CastUtility.toDouble(0.0f).doubleValue(), 0.0000001);
        Assert.assertEquals(984.561, CastUtility.toDouble(984.561f).doubleValue(), 0.0000001);
        Assert.assertEquals(-8.154, CastUtility.toDouble(-8.154f).doubleValue(), 0.0000001);
        
        //double
        Assert.assertEquals(1.0, CastUtility.toDouble(1.0d).doubleValue(), 0.0000001);
        Assert.assertEquals(0.0, CastUtility.toDouble(0.0d).doubleValue(), 0.0000001);
        Assert.assertEquals(984.565489421, CastUtility.toDouble(984.565489421d).doubleValue(), 0.0000001);
        Assert.assertEquals(-8.154874652, CastUtility.toDouble(-8.154874652d).doubleValue(), 0.0000001);
        
        //big integer
        Assert.assertEquals(1, CastUtility.toDouble(new BigInteger("1")).doubleValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toDouble(new BigInteger("0")).doubleValue(), 0.0000001);
        Assert.assertEquals(9.484146494320849E23, CastUtility.toDouble(new BigInteger("948414649432084846513216")).doubleValue(), 0.0000001);
        Assert.assertEquals(-1.8975621649448766E21, CastUtility.toDouble(new BigInteger("-1897562164944876591156")).doubleValue(), 0.0000001);
        
        //big decimal
        Assert.assertEquals(1, CastUtility.toDouble(new BigDecimal("1")).doubleValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toDouble(new BigDecimal("0")).doubleValue(), 0.0000001);
        Assert.assertEquals(9.484146494320849E23, CastUtility.toDouble(new BigDecimal("948414649432084846513216.54689231")).doubleValue(), 0.0000001);
        Assert.assertEquals(-1.8975621649590005E12, CastUtility.toDouble(new BigDecimal("-1897562164959.0005484897")).doubleValue(), 0.0000001);
        
        //char
        Assert.assertNull(CastUtility.toDouble('t'));
        Assert.assertNull(CastUtility.toDouble('f'));
        Assert.assertEquals(0, CastUtility.toDouble('0').doubleValue(), 0.0000001);
        Assert.assertEquals(1, CastUtility.toDouble('1').doubleValue(), 0.0000001);
        Assert.assertEquals(6, CastUtility.toDouble('6').doubleValue(), 0.0000001);
        Assert.assertNull(CastUtility.toDouble('n'));
        Assert.assertNull(CastUtility.toDouble('\r'));
        
        //string
        Assert.assertNull(CastUtility.toDouble("true"));
        Assert.assertNull(CastUtility.toDouble("false"));
        Assert.assertNull(CastUtility.toDouble("t"));
        Assert.assertNull(CastUtility.toDouble("f"));
        Assert.assertEquals(1, CastUtility.toDouble("1").doubleValue(), 0.0000001);
        Assert.assertEquals(0, CastUtility.toDouble("0").doubleValue(), 0.0000001);
        Assert.assertEquals(6, CastUtility.toDouble("6").doubleValue(), 0.0000001);
        Assert.assertEquals(-2, CastUtility.toDouble("-2").doubleValue(), 0.0000001);
        Assert.assertEquals(1564984, CastUtility.toDouble("1564984").doubleValue(), 0.0000001);
        Assert.assertEquals(1.564567897562131E15, CastUtility.toDouble("1564567897562131").doubleValue(), 0.0000001);
        Assert.assertEquals(1.5645678975621318E15, CastUtility.toDouble("1564567897562131.6494564").doubleValue(), 0.0000001);
        Assert.assertNull(CastUtility.toDouble("test"));
        Assert.assertNull(CastUtility.toDouble(""));
        
        //invalid
        Assert.assertNull(CastUtility.toDouble(null));
    }
    
    /**
     * JUnit test of toBigInteger.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toBigInteger(Object)
     */
    @Test
    public void testToBigInteger() throws Exception {
        //boolean
        Assert.assertNull(CastUtility.toBigInteger(Boolean.TRUE));
        Assert.assertNull(CastUtility.toBigInteger(Boolean.FALSE));
        Assert.assertNull(CastUtility.toBigInteger(true));
        Assert.assertNull(CastUtility.toBigInteger(false));
        
        //byte
        Assert.assertEquals(new BigInteger("1"), CastUtility.toBigInteger((byte) 1));
        Assert.assertEquals(new BigInteger("0"), CastUtility.toBigInteger((byte) 0));
        Assert.assertEquals(new BigInteger("6"), CastUtility.toBigInteger((byte) 6));
        Assert.assertEquals(new BigInteger("-2"), CastUtility.toBigInteger((byte) -2));
        
        //short
        Assert.assertEquals(new BigInteger("1"), CastUtility.toBigInteger((short) 1));
        Assert.assertEquals(new BigInteger("0"), CastUtility.toBigInteger((short) 0));
        Assert.assertEquals(new BigInteger("32100"), CastUtility.toBigInteger((short) 32100));
        Assert.assertEquals(new BigInteger("-11054"), CastUtility.toBigInteger((short) -11054));
        
        //int
        Assert.assertEquals(new BigInteger("1"), CastUtility.toBigInteger(1));
        Assert.assertEquals(new BigInteger("0"), CastUtility.toBigInteger(0));
        Assert.assertEquals(new BigInteger("6415123"), CastUtility.toBigInteger(6415123));
        Assert.assertEquals(new BigInteger("-115411"), CastUtility.toBigInteger(-115411));
        
        //long
        Assert.assertEquals(new BigInteger("1"), CastUtility.toBigInteger(1L));
        Assert.assertEquals(new BigInteger("0"), CastUtility.toBigInteger(0L));
        Assert.assertEquals(new BigInteger("15624653112"), CastUtility.toBigInteger(15624653112L));
        Assert.assertEquals(new BigInteger("-57988744511"), CastUtility.toBigInteger(-57988744511L));
        
        //float
        Assert.assertNull(CastUtility.toBigInteger(1.0f));
        Assert.assertNull(CastUtility.toBigInteger(0.0f));
        Assert.assertNull(CastUtility.toBigInteger(984.561f));
        Assert.assertNull(CastUtility.toBigInteger(-8.154f));
        
        //double
        Assert.assertNull(CastUtility.toBigInteger(1.0d));
        Assert.assertNull(CastUtility.toBigInteger(0.0d));
        Assert.assertNull(CastUtility.toBigInteger(984.565489421d));
        Assert.assertNull(CastUtility.toBigInteger(-8.154874652d));
        
        //big integer
        Assert.assertEquals(new BigInteger("1"), CastUtility.toBigInteger(new BigInteger("1")));
        Assert.assertEquals(new BigInteger("0"), CastUtility.toBigInteger(new BigInteger("0")));
        Assert.assertEquals(new BigInteger("948414649432084846513216"), CastUtility.toBigInteger(new BigInteger("948414649432084846513216")));
        Assert.assertEquals(new BigInteger("-1897562164944876591156"), CastUtility.toBigInteger(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(new BigInteger("1"), CastUtility.toBigInteger(new BigDecimal("1")));
        Assert.assertEquals(new BigInteger("0"), CastUtility.toBigInteger(new BigDecimal("0")));
        Assert.assertNull(CastUtility.toBigInteger(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(CastUtility.toBigInteger(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(CastUtility.toBigInteger('t'));
        Assert.assertNull(CastUtility.toBigInteger('f'));
        Assert.assertEquals(new BigInteger("0"), CastUtility.toBigInteger('0'));
        Assert.assertEquals(new BigInteger("1"), CastUtility.toBigInteger('1'));
        Assert.assertEquals(new BigInteger("6"), CastUtility.toBigInteger('6'));
        Assert.assertNull(CastUtility.toBigInteger('n'));
        Assert.assertNull(CastUtility.toBigInteger('\r'));
        
        //string
        Assert.assertNull(CastUtility.toBigInteger("true"));
        Assert.assertNull(CastUtility.toBigInteger("false"));
        Assert.assertNull(CastUtility.toBigInteger("t"));
        Assert.assertNull(CastUtility.toBigInteger("f"));
        Assert.assertEquals(new BigInteger("1"), CastUtility.toBigInteger("1"));
        Assert.assertEquals(new BigInteger("0"), CastUtility.toBigInteger("0"));
        Assert.assertEquals(new BigInteger("6"), CastUtility.toBigInteger("6"));
        Assert.assertEquals(new BigInteger("-2"), CastUtility.toBigInteger("-2"));
        Assert.assertEquals(new BigInteger("1564984"), CastUtility.toBigInteger("1564984"));
        Assert.assertEquals(new BigInteger("1564567897562131"), CastUtility.toBigInteger("1564567897562131"));
        Assert.assertNull(CastUtility.toBigInteger("1564567897562131.6494564"));
        Assert.assertNull(CastUtility.toBigInteger("test"));
        Assert.assertNull(CastUtility.toBigInteger(""));
        
        //invalid
        Assert.assertNull(CastUtility.toBigInteger(null));
    }
    
    /**
     * JUnit test of toBigDecimal.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toBigDecimal(Object)
     */
    @Test
    public void testToBigDecimal() throws Exception {
        //boolean
        Assert.assertNull(CastUtility.toBigDecimal(Boolean.TRUE));
        Assert.assertNull(CastUtility.toBigDecimal(Boolean.FALSE));
        Assert.assertNull(CastUtility.toBigDecimal(true));
        Assert.assertNull(CastUtility.toBigDecimal(false));
        
        //byte
        Assert.assertEquals(new BigDecimal("1"), CastUtility.toBigDecimal((byte) 1));
        Assert.assertEquals(new BigDecimal("0"), CastUtility.toBigDecimal((byte) 0));
        Assert.assertEquals(new BigDecimal("6"), CastUtility.toBigDecimal((byte) 6));
        Assert.assertEquals(new BigDecimal("-2"), CastUtility.toBigDecimal((byte) -2));
        
        //short
        Assert.assertEquals(new BigDecimal("1"), CastUtility.toBigDecimal((short) 1));
        Assert.assertEquals(new BigDecimal("0"), CastUtility.toBigDecimal((short) 0));
        Assert.assertEquals(new BigDecimal("32100"), CastUtility.toBigDecimal((short) 32100));
        Assert.assertEquals(new BigDecimal("-11054"), CastUtility.toBigDecimal((short) -11054));
        
        //int
        Assert.assertEquals(new BigDecimal("1"), CastUtility.toBigDecimal(1));
        Assert.assertEquals(new BigDecimal("0"), CastUtility.toBigDecimal(0));
        Assert.assertEquals(new BigDecimal("6415123"), CastUtility.toBigDecimal(6415123));
        Assert.assertEquals(new BigDecimal("-115411"), CastUtility.toBigDecimal(-115411));
        
        //long
        Assert.assertEquals(new BigDecimal("1"), CastUtility.toBigDecimal(1L));
        Assert.assertEquals(new BigDecimal("0"), CastUtility.toBigDecimal(0L));
        Assert.assertEquals(new BigDecimal("15624653112"), CastUtility.toBigDecimal(15624653112L));
        Assert.assertEquals(new BigDecimal("-57988744511"), CastUtility.toBigDecimal(-57988744511L));
        
        //float
        Assert.assertEquals(new BigDecimal("1.0"), CastUtility.toBigDecimal(1.0f));
        Assert.assertEquals(new BigDecimal("0.0"), CastUtility.toBigDecimal(0.0f));
        Assert.assertEquals(new BigDecimal("984.561"), CastUtility.toBigDecimal(984.561f));
        Assert.assertEquals(new BigDecimal("-8.154"), CastUtility.toBigDecimal(-8.154f));
        
        //double
        Assert.assertEquals(new BigDecimal("1.0"), CastUtility.toBigDecimal(1.0d));
        Assert.assertEquals(new BigDecimal("0.0"), CastUtility.toBigDecimal(0.0d));
        Assert.assertEquals(new BigDecimal("984.565489421"), CastUtility.toBigDecimal(984.565489421d));
        Assert.assertEquals(new BigDecimal("-8.154874652"), CastUtility.toBigDecimal(-8.154874652d));
        
        //big integer
        Assert.assertEquals(new BigDecimal("1"), CastUtility.toBigDecimal(new BigInteger("1")));
        Assert.assertEquals(new BigDecimal("0"), CastUtility.toBigDecimal(new BigInteger("0")));
        Assert.assertEquals(new BigDecimal("948414649432084846513216"), CastUtility.toBigDecimal(new BigInteger("948414649432084846513216")));
        Assert.assertEquals(new BigDecimal("-1897562164944876591156"), CastUtility.toBigDecimal(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(new BigDecimal("1"), CastUtility.toBigDecimal(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0"), CastUtility.toBigDecimal(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("948414649432084846513216.54689231"), CastUtility.toBigDecimal(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertEquals(new BigDecimal("-1897562164959.0005484897"), CastUtility.toBigDecimal(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(CastUtility.toBigDecimal('t'));
        Assert.assertNull(CastUtility.toBigDecimal('f'));
        Assert.assertEquals(new BigDecimal("0"), CastUtility.toBigDecimal('0'));
        Assert.assertEquals(new BigDecimal("1"), CastUtility.toBigDecimal('1'));
        Assert.assertEquals(new BigDecimal("6"), CastUtility.toBigDecimal('6'));
        Assert.assertNull(CastUtility.toBigDecimal('n'));
        Assert.assertNull(CastUtility.toBigDecimal('\r'));
        
        //string
        Assert.assertNull(CastUtility.toBigDecimal("true"));
        Assert.assertNull(CastUtility.toBigDecimal("false"));
        Assert.assertNull(CastUtility.toBigDecimal("t"));
        Assert.assertNull(CastUtility.toBigDecimal("f"));
        Assert.assertEquals(new BigDecimal("1"), CastUtility.toBigDecimal("1"));
        Assert.assertEquals(new BigDecimal("0"), CastUtility.toBigDecimal("0"));
        Assert.assertEquals(new BigDecimal("6"), CastUtility.toBigDecimal("6"));
        Assert.assertEquals(new BigDecimal("-2"), CastUtility.toBigDecimal("-2"));
        Assert.assertEquals(new BigDecimal("1564984"), CastUtility.toBigDecimal("1564984"));
        Assert.assertEquals(new BigDecimal("1564567897562131"), CastUtility.toBigDecimal("1564567897562131"));
        Assert.assertEquals(new BigDecimal("1564567897562131.6494564"), CastUtility.toBigDecimal("1564567897562131.6494564"));
        Assert.assertNull(CastUtility.toBigDecimal("test"));
        Assert.assertNull(CastUtility.toBigDecimal(""));
        
        //invalid
        Assert.assertNull(CastUtility.toBigDecimal(null));
    }
    
    /**
     * JUnit test of toChar.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toChar(Object)
     */
    @Test
    public void testToChar() throws Exception {
        //boolean
        Assert.assertNull(CastUtility.toChar(Boolean.TRUE));
        Assert.assertNull(CastUtility.toChar(Boolean.FALSE));
        Assert.assertNull(CastUtility.toChar(true));
        Assert.assertNull(CastUtility.toChar(false));
        
        //byte
        Assert.assertEquals('1', CastUtility.toChar((byte) 1).charValue());
        Assert.assertEquals('0', CastUtility.toChar((byte) 0).charValue());
        Assert.assertEquals('6', CastUtility.toChar((byte) 6).charValue());
        Assert.assertNull(CastUtility.toChar((byte) -2));
        
        //short
        Assert.assertEquals('1', CastUtility.toChar((short) 1).charValue());
        Assert.assertEquals('0', CastUtility.toChar((short) 0).charValue());
        Assert.assertNull(CastUtility.toChar((short) 32100));
        Assert.assertNull(CastUtility.toChar((short) -11054));
        
        //int
        Assert.assertEquals('1', CastUtility.toChar(1).charValue());
        Assert.assertEquals('0', CastUtility.toChar(0).charValue());
        Assert.assertNull(CastUtility.toChar(6415123));
        Assert.assertNull(CastUtility.toChar(-115411));
        
        //long
        Assert.assertEquals('1', CastUtility.toChar(1L).charValue());
        Assert.assertEquals('0', CastUtility.toChar(0L).charValue());
        Assert.assertNull(CastUtility.toChar(15624653112L));
        Assert.assertNull(CastUtility.toChar(-57988744511L));
        
        //float
        Assert.assertNull(CastUtility.toChar(1.0f));
        Assert.assertNull(CastUtility.toChar(0.0f));
        Assert.assertNull(CastUtility.toChar(984.561f));
        Assert.assertNull(CastUtility.toChar(-8.154f));
        
        //double
        Assert.assertNull(CastUtility.toChar(1.0d));
        Assert.assertNull(CastUtility.toChar(0.0d));
        Assert.assertNull(CastUtility.toChar(984.565489421d));
        Assert.assertNull(CastUtility.toChar(-8.154874652d));
        
        //big integer
        Assert.assertEquals('1', CastUtility.toChar(new BigInteger("1")).charValue());
        Assert.assertEquals('0', CastUtility.toChar(new BigInteger("0")).charValue());
        Assert.assertNull(CastUtility.toChar(new BigInteger("948414649432084846513216")));
        Assert.assertNull(CastUtility.toChar(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals('1', CastUtility.toChar(new BigDecimal("1")).charValue());
        Assert.assertEquals('0', CastUtility.toChar(new BigDecimal("0")).charValue());
        Assert.assertNull(CastUtility.toChar(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(CastUtility.toChar(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertEquals('t', CastUtility.toChar('t').charValue());
        Assert.assertEquals('f', CastUtility.toChar('f').charValue());
        Assert.assertEquals('0', CastUtility.toChar('0').charValue());
        Assert.assertEquals('1', CastUtility.toChar('1').charValue());
        Assert.assertEquals('6', CastUtility.toChar('6').charValue());
        Assert.assertEquals('n', CastUtility.toChar('n').charValue());
        Assert.assertEquals('\r', CastUtility.toChar('\r').charValue());
        
        //string
        Assert.assertNull(CastUtility.toChar("true"));
        Assert.assertNull(CastUtility.toChar("false"));
        Assert.assertEquals('t', CastUtility.toChar("t").charValue());
        Assert.assertEquals('f', CastUtility.toChar("f").charValue());
        Assert.assertEquals('1', CastUtility.toChar("1").charValue());
        Assert.assertEquals('0', CastUtility.toChar("0").charValue());
        Assert.assertEquals('6', CastUtility.toChar("6").charValue());
        Assert.assertNull(CastUtility.toChar("-2"));
        Assert.assertNull(CastUtility.toChar("1564984"));
        Assert.assertNull(CastUtility.toChar("1564567897562131"));
        Assert.assertNull(CastUtility.toChar("1564567897562131.6494564"));
        Assert.assertNull(CastUtility.toChar("test"));
        Assert.assertNull(CastUtility.toChar(""));
        
        //invalid
        Assert.assertNull(CastUtility.toChar(null));
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toString(Object)
     */
    @Test
    public void testToString() throws Exception {
        //boolean
        Assert.assertEquals("true", CastUtility.toString(Boolean.TRUE));
        Assert.assertEquals("false", CastUtility.toString(Boolean.FALSE));
        Assert.assertEquals("true", CastUtility.toString(true));
        Assert.assertEquals("false", CastUtility.toString(false));
        
        //byte
        Assert.assertEquals("1", CastUtility.toString((byte) 1));
        Assert.assertEquals("0", CastUtility.toString((byte) 0));
        Assert.assertEquals("6", CastUtility.toString((byte) 6));
        Assert.assertEquals("-2", CastUtility.toString((byte) -2));
        
        //short
        Assert.assertEquals("1", CastUtility.toString((short) 1));
        Assert.assertEquals("0", CastUtility.toString((short) 0));
        Assert.assertEquals("32100", CastUtility.toString((short) 32100));
        Assert.assertEquals("-11054", CastUtility.toString((short) -11054));
        
        //int
        Assert.assertEquals("1", CastUtility.toString(1));
        Assert.assertEquals("0", CastUtility.toString(0));
        Assert.assertEquals("6415123", CastUtility.toString(6415123));
        Assert.assertEquals("-115411", CastUtility.toString(-115411));
        
        //long
        Assert.assertEquals("1", CastUtility.toString(1L));
        Assert.assertEquals("0", CastUtility.toString(0L));
        Assert.assertEquals("15624653112", CastUtility.toString(15624653112L));
        Assert.assertEquals("-57988744511", CastUtility.toString(-57988744511L));
        
        //float
        Assert.assertEquals("1.0", CastUtility.toString(1.0f));
        Assert.assertEquals("0.0", CastUtility.toString(0.0f));
        Assert.assertEquals("984.561", CastUtility.toString(984.561f));
        Assert.assertEquals("-8.154", CastUtility.toString(-8.154f));
        
        //double
        Assert.assertEquals("1.0", CastUtility.toString(1.0d));
        Assert.assertEquals("0.0", CastUtility.toString(0.0d));
        Assert.assertEquals("984.565489421", CastUtility.toString(984.565489421d));
        Assert.assertEquals("-8.154874652", CastUtility.toString(-8.154874652d));
        
        //big integer
        Assert.assertEquals("1", CastUtility.toString(new BigInteger("1")));
        Assert.assertEquals("0", CastUtility.toString(new BigInteger("0")));
        Assert.assertEquals("948414649432084846513216", CastUtility.toString(new BigInteger("948414649432084846513216")));
        Assert.assertEquals("-1897562164944876591156", CastUtility.toString(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals("1", CastUtility.toString(new BigDecimal("1")));
        Assert.assertEquals("0", CastUtility.toString(new BigDecimal("0")));
        Assert.assertEquals("948414649432084846513216.54689231", CastUtility.toString(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertEquals("-1897562164959.0005484897", CastUtility.toString(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertEquals("t", CastUtility.toString('t'));
        Assert.assertEquals("f", CastUtility.toString('f'));
        Assert.assertEquals("0", CastUtility.toString('0'));
        Assert.assertEquals("1", CastUtility.toString('1'));
        Assert.assertEquals("6", CastUtility.toString('6'));
        Assert.assertEquals("n", CastUtility.toString('n'));
        Assert.assertEquals("\r", CastUtility.toString('\r'));
        
        //string
        Assert.assertEquals("true", CastUtility.toString("true"));
        Assert.assertEquals("false", CastUtility.toString("false"));
        Assert.assertEquals("t", CastUtility.toString("t"));
        Assert.assertEquals("f", CastUtility.toString("f"));
        Assert.assertEquals("1", CastUtility.toString("1"));
        Assert.assertEquals("0", CastUtility.toString("0"));
        Assert.assertEquals("6", CastUtility.toString("6"));
        Assert.assertEquals("-2", CastUtility.toString("-2"));
        Assert.assertEquals("1564984", CastUtility.toString("1564984"));
        Assert.assertEquals("1564567897562131", CastUtility.toString("1564567897562131"));
        Assert.assertEquals("1564567897562131.6494564", CastUtility.toString("1564567897562131.6494564"));
        Assert.assertEquals("test", CastUtility.toString("test"));
        Assert.assertEquals("", CastUtility.toString(""));
        
        //invalid
        Assert.assertEquals("null", CastUtility.toString(null));
    }
    
    /**
     * JUnit test of toClass.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toClass(Object)
     */
    @SuppressWarnings("UnnecessaryBoxing")
    @Test
    public void testToClass() throws Exception {
        //standard
        Assert.assertEquals(Boolean.class, CastUtility.toClass(Boolean.TRUE));
        Assert.assertEquals(Boolean.class, CastUtility.toClass(false));
        Assert.assertEquals(Byte.class, CastUtility.toClass(Byte.valueOf((byte) 1)));
        Assert.assertEquals(Byte.class, CastUtility.toClass((byte) 1));
        Assert.assertEquals(Short.class, CastUtility.toClass(Short.valueOf((short) 32100)));
        Assert.assertEquals(Short.class, CastUtility.toClass((short) 32100));
        Assert.assertEquals(Integer.class, CastUtility.toClass(Integer.valueOf(-115411)));
        Assert.assertEquals(Integer.class, CastUtility.toClass(-115411));
        Assert.assertEquals(Long.class, CastUtility.toClass(Long.valueOf(15624653112L)));
        Assert.assertEquals(Long.class, CastUtility.toClass(15624653112L));
        Assert.assertEquals(Float.class, CastUtility.toClass(Float.valueOf(-8.154f)));
        Assert.assertEquals(Float.class, CastUtility.toClass(-8.154f));
        Assert.assertEquals(Double.class, CastUtility.toClass(Double.valueOf(984.565489421d)));
        Assert.assertEquals(Double.class, CastUtility.toClass(984.565489421d));
        Assert.assertEquals(BigInteger.class, CastUtility.toClass(new BigInteger("1")));
        Assert.assertEquals(BigDecimal.class, CastUtility.toClass(new BigDecimal("1")));
        Assert.assertEquals(Character.class, CastUtility.toClass(Character.valueOf('t')));
        Assert.assertEquals(Character.class, CastUtility.toClass('t'));
        Assert.assertEquals(String.class, CastUtility.toClass("test"));
        Assert.assertEquals(Exception.class, CastUtility.toClass(new Exception()));
        
        //class
        Assert.assertEquals(Boolean.class, CastUtility.toClass(Boolean.class));
        Assert.assertEquals(boolean.class, CastUtility.toClass(boolean.class));
        Assert.assertEquals(Byte.class, CastUtility.toClass(Byte.class));
        Assert.assertEquals(byte.class, CastUtility.toClass(byte.class));
        Assert.assertEquals(Short.class, CastUtility.toClass(Short.class));
        Assert.assertEquals(short.class, CastUtility.toClass(short.class));
        Assert.assertEquals(Integer.class, CastUtility.toClass(Integer.class));
        Assert.assertEquals(int.class, CastUtility.toClass(int.class));
        Assert.assertEquals(Long.class, CastUtility.toClass(Long.class));
        Assert.assertEquals(long.class, CastUtility.toClass(long.class));
        Assert.assertEquals(Float.class, CastUtility.toClass(Float.class));
        Assert.assertEquals(float.class, CastUtility.toClass(float.class));
        Assert.assertEquals(Double.class, CastUtility.toClass(Double.class));
        Assert.assertEquals(double.class, CastUtility.toClass(double.class));
        Assert.assertEquals(BigInteger.class, CastUtility.toClass(BigInteger.class));
        Assert.assertEquals(BigDecimal.class, CastUtility.toClass(BigDecimal.class));
        Assert.assertEquals(Character.class, CastUtility.toClass(Character.class));
        Assert.assertEquals(char.class, CastUtility.toClass(char.class));
        Assert.assertEquals(String.class, CastUtility.toClass(String.class));
        Assert.assertEquals(Exception.class, CastUtility.toClass(Exception.class));
        
        //invalid
        Assert.assertNull(CastUtility.toClass(null));
    }
    
    /**
     * JUnit test of toPrimitiveClass.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toPrimitiveClass(Class)
     */
    @Test
    public void testToPrimitiveClass() throws Exception {
        //standard
        Assert.assertEquals(boolean.class, CastUtility.toPrimitiveClass(Boolean.class));
        Assert.assertEquals(byte.class, CastUtility.toPrimitiveClass(Byte.class));
        Assert.assertEquals(short.class, CastUtility.toPrimitiveClass(Short.class));
        Assert.assertEquals(int.class, CastUtility.toPrimitiveClass(Integer.class));
        Assert.assertEquals(long.class, CastUtility.toPrimitiveClass(Long.class));
        Assert.assertEquals(float.class, CastUtility.toPrimitiveClass(Float.class));
        Assert.assertEquals(double.class, CastUtility.toPrimitiveClass(Double.class));
        Assert.assertEquals(char.class, CastUtility.toPrimitiveClass(Character.class));
        
        //primitive
        Assert.assertEquals(boolean.class, CastUtility.toPrimitiveClass(boolean.class));
        Assert.assertEquals(byte.class, CastUtility.toPrimitiveClass(byte.class));
        Assert.assertEquals(short.class, CastUtility.toPrimitiveClass(short.class));
        Assert.assertEquals(int.class, CastUtility.toPrimitiveClass(int.class));
        Assert.assertEquals(long.class, CastUtility.toPrimitiveClass(long.class));
        Assert.assertEquals(float.class, CastUtility.toPrimitiveClass(float.class));
        Assert.assertEquals(double.class, CastUtility.toPrimitiveClass(double.class));
        Assert.assertEquals(char.class, CastUtility.toPrimitiveClass(char.class));
        
        //no primitive class
        Assert.assertEquals(BigInteger.class, CastUtility.toPrimitiveClass(BigInteger.class));
        Assert.assertEquals(BigDecimal.class, CastUtility.toPrimitiveClass(BigDecimal.class));
        Assert.assertEquals(String.class, CastUtility.toPrimitiveClass(String.class));
        Assert.assertEquals(Exception.class, CastUtility.toPrimitiveClass(Exception.class));
        
        //invalid
        Assert.assertNull(CastUtility.toPrimitiveClass(null));
    }
    
    /**
     * JUnit test of toNonPrimitiveClass.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toNonPrimitiveClass(Class)
     */
    @Test
    public void testToNonPrimitiveClass() throws Exception {
        //standard
        Assert.assertEquals(Boolean.class, CastUtility.toNonPrimitiveClass(boolean.class));
        Assert.assertEquals(Byte.class, CastUtility.toNonPrimitiveClass(byte.class));
        Assert.assertEquals(Short.class, CastUtility.toNonPrimitiveClass(short.class));
        Assert.assertEquals(Integer.class, CastUtility.toNonPrimitiveClass(int.class));
        Assert.assertEquals(Long.class, CastUtility.toNonPrimitiveClass(long.class));
        Assert.assertEquals(Float.class, CastUtility.toNonPrimitiveClass(float.class));
        Assert.assertEquals(Double.class, CastUtility.toNonPrimitiveClass(double.class));
        Assert.assertEquals(Character.class, CastUtility.toNonPrimitiveClass(char.class));
        
        //non-primitive
        Assert.assertEquals(Boolean.class, CastUtility.toNonPrimitiveClass(Boolean.class));
        Assert.assertEquals(Byte.class, CastUtility.toNonPrimitiveClass(Byte.class));
        Assert.assertEquals(Short.class, CastUtility.toNonPrimitiveClass(Short.class));
        Assert.assertEquals(Integer.class, CastUtility.toNonPrimitiveClass(Integer.class));
        Assert.assertEquals(Long.class, CastUtility.toNonPrimitiveClass(Long.class));
        Assert.assertEquals(Float.class, CastUtility.toNonPrimitiveClass(Float.class));
        Assert.assertEquals(Double.class, CastUtility.toNonPrimitiveClass(Double.class));
        Assert.assertEquals(Character.class, CastUtility.toNonPrimitiveClass(Character.class));
        
        //no primitive class
        Assert.assertEquals(BigInteger.class, CastUtility.toNonPrimitiveClass(BigInteger.class));
        Assert.assertEquals(BigDecimal.class, CastUtility.toNonPrimitiveClass(BigDecimal.class));
        Assert.assertEquals(String.class, CastUtility.toNonPrimitiveClass(String.class));
        Assert.assertEquals(Exception.class, CastUtility.toNonPrimitiveClass(Exception.class));
        
        //invalid
        Assert.assertNull(CastUtility.toNonPrimitiveClass(null));
    }
    
    /**
     * JUnit test of toCollectionType.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toCollectionType(Collection, Class, Class)
     */
    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testToCollectionType() throws Exception {
        final Collection<Boolean> testCollection1 = new ArrayList<>(Arrays.asList(false, true, true));
        final Collection<Integer> testCollection2 = new PriorityQueue<>(Arrays.asList(1, 4, 11));
        final Collection<String> testCollection3 = new HashSet<>(Arrays.asList("test", "value", "another", "else"));
        ArrayList<Boolean> arrayList1;
        ArrayList<Integer> arrayList2;
        ArrayList<String> arrayList3;
        LinkedList<Boolean> linkedList1;
        LinkedList<Integer> linkedList2;
        LinkedList<String> linkedList3;
        Vector<Boolean> vector1;
        Vector<Integer> vector2;
        Vector<String> vector3;
        PriorityQueue<Boolean> priorityQueue1;
        PriorityQueue<Integer> priorityQueue2;
        PriorityQueue<String> priorityQueue3;
        ArrayDeque<Boolean> arrayDeque1;
        ArrayDeque<Integer> arrayDeque2;
        ArrayDeque<String> arrayDeque3;
        HashSet<Boolean> hashSet1;
        HashSet<Integer> hashSet2;
        HashSet<String> hashSet3;
        LinkedHashSet<Boolean> linkedHashSet1;
        LinkedHashSet<Integer> linkedHashSet2;
        LinkedHashSet<String> linkedHashSet3;
        TreeSet<Boolean> treeSet1;
        TreeSet<Integer> treeSet2;
        TreeSet<String> treeSet3;
        
        //ArrayList
        arrayList1 = CastUtility.toCollectionType(testCollection1, ArrayList.class, Boolean.class);
        Assert.assertNotNull(arrayList1);
        Assert.assertEquals(ArrayList.class, arrayList1.getClass());
        Assert.assertSame(testCollection1, arrayList1);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(false, true, true),
                arrayList1));
        arrayList2 = CastUtility.toCollectionType(testCollection2, ArrayList.class, Integer.class);
        Assert.assertNotNull(arrayList2);
        Assert.assertEquals(ArrayList.class, arrayList2.getClass());
        Assert.assertNotSame(testCollection2, arrayList2);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(1, 4, 11),
                arrayList2));
        arrayList3 = CastUtility.toCollectionType(testCollection3, ArrayList.class, String.class);
        Assert.assertNotNull(arrayList3);
        Assert.assertEquals(ArrayList.class, arrayList3.getClass());
        Assert.assertNotSame(testCollection3, arrayList3);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf("test", "value", "another", "else"),
                arrayList3, false));
        
        //LinkedList
        linkedList1 = CastUtility.toCollectionType(testCollection1, LinkedList.class, Boolean.class);
        Assert.assertNotNull(linkedList1);
        Assert.assertEquals(LinkedList.class, linkedList1.getClass());
        Assert.assertNotSame(testCollection1, linkedList1);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(false, true, true),
                linkedList1));
        linkedList2 = CastUtility.toCollectionType(testCollection2, LinkedList.class, Integer.class);
        Assert.assertNotNull(linkedList2);
        Assert.assertEquals(LinkedList.class, linkedList2.getClass());
        Assert.assertNotSame(testCollection2, linkedList2);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(1, 4, 11),
                linkedList2));
        linkedList3 = CastUtility.toCollectionType(testCollection3, LinkedList.class, String.class);
        Assert.assertNotNull(linkedList3);
        Assert.assertEquals(LinkedList.class, linkedList3.getClass());
        Assert.assertNotSame(testCollection3, linkedList3);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf("test", "value", "another", "else"),
                linkedList3, false));
        
        //Vector
        vector1 = CastUtility.toCollectionType(testCollection1, Vector.class, Boolean.class);
        Assert.assertNotNull(vector1);
        Assert.assertEquals(Vector.class, vector1.getClass());
        Assert.assertNotSame(testCollection1, vector1);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(false, true, true),
                vector1));
        vector2 = CastUtility.toCollectionType(testCollection2, Vector.class, Integer.class);
        Assert.assertNotNull(vector2);
        Assert.assertEquals(Vector.class, vector2.getClass());
        Assert.assertNotSame(testCollection2, vector2);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(1, 4, 11),
                vector2));
        vector3 = CastUtility.toCollectionType(testCollection3, Vector.class, String.class);
        Assert.assertNotNull(vector3);
        Assert.assertEquals(Vector.class, vector3.getClass());
        Assert.assertNotSame(testCollection3, vector3);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf("test", "value", "another", "else"),
                vector3, false));
        
        //PriorityQueue
        priorityQueue1 = CastUtility.toCollectionType(testCollection1, PriorityQueue.class, Boolean.class);
        Assert.assertNotNull(priorityQueue1);
        Assert.assertEquals(PriorityQueue.class, priorityQueue1.getClass());
        Assert.assertNotSame(testCollection1, priorityQueue1);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(false, true, true),
                new ArrayList<>(priorityQueue1)));
        priorityQueue2 = CastUtility.toCollectionType(testCollection2, PriorityQueue.class, Integer.class);
        Assert.assertNotNull(priorityQueue2);
        Assert.assertEquals(PriorityQueue.class, priorityQueue2.getClass());
        Assert.assertSame(testCollection2, priorityQueue2);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(1, 4, 11),
                new ArrayList<>(priorityQueue2)));
        priorityQueue3 = CastUtility.toCollectionType(testCollection3, PriorityQueue.class, String.class);
        Assert.assertNotNull(priorityQueue3);
        Assert.assertEquals(PriorityQueue.class, priorityQueue3.getClass());
        Assert.assertNotSame(testCollection3, priorityQueue3);
        Assert.assertEquals(4, priorityQueue3.size());
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf("test", "value", "another", "else"),
                new ArrayList<>(priorityQueue3), false));
        
        //ArrayDeque
        arrayDeque1 = CastUtility.toCollectionType(testCollection1, ArrayDeque.class, Boolean.class);
        Assert.assertNotNull(arrayDeque1);
        Assert.assertEquals(ArrayDeque.class, arrayDeque1.getClass());
        Assert.assertNotSame(testCollection1, arrayDeque1);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(false, true, true),
                new ArrayList<>(arrayDeque1), false));
        arrayDeque2 = CastUtility.toCollectionType(testCollection2, ArrayDeque.class, Integer.class);
        Assert.assertNotNull(arrayDeque2);
        Assert.assertEquals(ArrayDeque.class, arrayDeque2.getClass());
        Assert.assertNotSame(testCollection2, arrayDeque2);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(1, 4, 11),
                new ArrayList<>(arrayDeque2), false));
        arrayDeque3 = CastUtility.toCollectionType(testCollection3, ArrayDeque.class, String.class);
        Assert.assertNotNull(arrayDeque3);
        Assert.assertEquals(ArrayDeque.class, arrayDeque3.getClass());
        Assert.assertNotSame(testCollection3, arrayDeque3);
        Assert.assertEquals(4, arrayDeque3.size());
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf("test", "value", "another", "else"),
                new ArrayList<>(arrayDeque3), false));
        
        //HashSet
        hashSet1 = CastUtility.toCollectionType(testCollection1, HashSet.class, Boolean.class);
        Assert.assertNotNull(hashSet1);
        Assert.assertEquals(HashSet.class, hashSet1.getClass());
        Assert.assertNotSame(testCollection1, hashSet1);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(false, true),
                new ArrayList<>(hashSet1), false));
        hashSet2 = CastUtility.toCollectionType(testCollection2, HashSet.class, Integer.class);
        Assert.assertNotNull(hashSet2);
        Assert.assertEquals(HashSet.class, hashSet2.getClass());
        Assert.assertNotSame(testCollection2, hashSet2);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(1, 4, 11),
                new ArrayList<>(hashSet2), false));
        hashSet3 = CastUtility.toCollectionType(testCollection3, HashSet.class, String.class);
        Assert.assertNotNull(hashSet3);
        Assert.assertEquals(HashSet.class, hashSet3.getClass());
        Assert.assertSame(testCollection3, hashSet3);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf("test", "value", "another", "else"),
                new ArrayList<>(hashSet3), false));
        
        //LinkedHashSet
        linkedHashSet1 = CastUtility.toCollectionType(testCollection1, LinkedHashSet.class, Boolean.class);
        Assert.assertNotNull(linkedHashSet1);
        Assert.assertEquals(LinkedHashSet.class, linkedHashSet1.getClass());
        Assert.assertNotSame(testCollection1, linkedHashSet1);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(false, true),
                new ArrayList<>(linkedHashSet1)));
        linkedHashSet2 = CastUtility.toCollectionType(testCollection2, LinkedHashSet.class, Integer.class);
        Assert.assertNotNull(linkedHashSet2);
        Assert.assertEquals(LinkedHashSet.class, linkedHashSet2.getClass());
        Assert.assertNotSame(testCollection2, linkedHashSet2);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(1, 4, 11),
                new ArrayList<>(linkedHashSet2)));
        linkedHashSet3 = CastUtility.toCollectionType(testCollection3, LinkedHashSet.class, String.class);
        Assert.assertNotNull(linkedHashSet3);
        Assert.assertEquals(LinkedHashSet.class, linkedHashSet3.getClass());
        Assert.assertNotSame(testCollection3, linkedHashSet3);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf("test", "value", "another", "else"),
                new ArrayList<>(linkedHashSet3), false));
        
        //TreeSet
        treeSet1 = CastUtility.toCollectionType(testCollection1, TreeSet.class, Boolean.class);
        Assert.assertNotNull(treeSet1);
        Assert.assertEquals(TreeSet.class, treeSet1.getClass());
        Assert.assertNotSame(testCollection1, treeSet1);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(false, true),
                new ArrayList<>(treeSet1)));
        treeSet2 = CastUtility.toCollectionType(testCollection2, TreeSet.class, Integer.class);
        Assert.assertNotNull(treeSet2);
        Assert.assertEquals(TreeSet.class, treeSet2.getClass());
        Assert.assertNotSame(testCollection2, treeSet2);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf(1, 4, 11),
                new ArrayList<>(treeSet2)));
        treeSet3 = CastUtility.toCollectionType(testCollection3, TreeSet.class, String.class);
        Assert.assertNotNull(treeSet3);
        Assert.assertEquals(TreeSet.class, treeSet3.getClass());
        Assert.assertNotSame(testCollection3, treeSet3);
        Assert.assertTrue(ListUtility.equals(
                ListUtility.listOf("test", "value", "another", "else"),
                new ArrayList<>(treeSet3), false));
        
        //invalid
        Assert.assertNull(CastUtility.toCollectionType(testCollection1, Stack.class, Boolean.class));
        Assert.assertNotNull(CastUtility.toCollectionType(testCollection1, ArrayList.class, null));
        Assert.assertNull(CastUtility.toCollectionType(testCollection1, null, Boolean.class));
        Assert.assertNull(CastUtility.toCollectionType(testCollection1, null, null));
        Assert.assertNull(CastUtility.toCollectionType(null, ArrayList.class, Boolean.class));
        Assert.assertNull(CastUtility.toCollectionType(null, ArrayList.class, null));
        Assert.assertNull(CastUtility.toCollectionType(null, null, Boolean.class));
        Assert.assertNull(CastUtility.toCollectionType(null, null, null));
    }
    
    /**
     * JUnit test of toMapType.
     *
     * @throws Exception When there is an exception.
     * @see CastUtility#toMapType(Map, Class, Class, Class)
     */
    @SuppressWarnings("SimplifiableAssertion")
    @Test
    public void testToMapType() throws Exception {
        final Map<Integer, Boolean> testMap1 = MapUtility.mapOf(HashMap.class,
                new Integer[] {1, 4, 11},
                new Boolean[] {false, true, true});
        final Map<String, String> testMap2 = MapUtility.mapOf(LinkedHashMap.class,
                new String[] {"test", "key", "other"},
                new String[] {"test", "value", "another"});
        final Map<Integer, String> testMap3 = MapUtility.mapOf(TreeMap.class,
                new Integer[] {0, 9, -4, 10},
                new String[] {"test", "value", "another", "else"});
        HashMap<Integer, Boolean> hashMap1;
        HashMap<String, String> hashMap2;
        HashMap<Integer, String> hashMap3;
        LinkedHashMap<Integer, Boolean> linkedHashMap1;
        LinkedHashMap<String, String> linkedHashMap2;
        LinkedHashMap<Integer, String> linkedHashMap3;
        TreeMap<Integer, Boolean> treeMap1;
        TreeMap<String, String> treeMap2;
        TreeMap<Integer, String> treeMap3;
        
        //HashMap
        hashMap1 = CastUtility.toMapType(testMap1, HashMap.class, Integer.class, Boolean.class);
        Assert.assertNotNull(hashMap1);
        Assert.assertEquals(HashMap.class, hashMap1.getClass());
        Assert.assertSame(testMap1, hashMap1);
        Assert.assertTrue(MapUtility.equals(
                MapUtility.mapOf(
                        new Integer[] {1, 4, 11},
                        new Boolean[] {false, true, true}),
                hashMap1));
        hashMap2 = CastUtility.toMapType(testMap2, HashMap.class, String.class, String.class);
        Assert.assertNotNull(hashMap2);
        Assert.assertEquals(HashMap.class, hashMap2.getClass());
        Assert.assertNotSame(testMap2, hashMap2);
        Assert.assertTrue(MapUtility.equals(
                MapUtility.mapOf(
                        new String[] {"test", "key", "other"},
                        new String[] {"test", "value", "another"}
                ), hashMap2));
        hashMap3 = CastUtility.toMapType(testMap3, HashMap.class, Integer.class, String.class);
        Assert.assertNotNull(hashMap3);
        Assert.assertEquals(HashMap.class, hashMap3.getClass());
        Assert.assertNotSame(testMap3, hashMap3);
        Assert.assertTrue(MapUtility.equals(
                MapUtility.mapOf(
                        new Integer[] {0, 9, -4, 10},
                        new String[] {"test", "value", "another", "else"}
                ), hashMap3));
        
        //LinkedHashMap
        linkedHashMap1 = CastUtility.toMapType(testMap1, LinkedHashMap.class, Integer.class, Boolean.class);
        Assert.assertNotNull(linkedHashMap1);
        Assert.assertEquals(LinkedHashMap.class, linkedHashMap1.getClass());
        Assert.assertNotSame(testMap1, linkedHashMap1);
        Assert.assertTrue(MapUtility.equals(
                MapUtility.mapOf(
                        new Integer[] {1, 4, 11},
                        new Boolean[] {false, true, true}),
                linkedHashMap1));
        linkedHashMap2 = CastUtility.toMapType(testMap2, LinkedHashMap.class, String.class, String.class);
        Assert.assertNotNull(linkedHashMap2);
        Assert.assertEquals(LinkedHashMap.class, linkedHashMap2.getClass());
        Assert.assertSame(testMap2, linkedHashMap2);
        Assert.assertTrue(MapUtility.equals(
                MapUtility.mapOf(
                        new String[] {"test", "key", "other"},
                        new String[] {"test", "value", "another"}
                ), linkedHashMap2));
        linkedHashMap3 = CastUtility.toMapType(testMap3, LinkedHashMap.class, Integer.class, String.class);
        Assert.assertNotNull(linkedHashMap3);
        Assert.assertEquals(LinkedHashMap.class, linkedHashMap3.getClass());
        Assert.assertNotSame(testMap3, linkedHashMap3);
        Assert.assertTrue(MapUtility.equals(
                MapUtility.mapOf(
                        new Integer[] {0, 9, -4, 10},
                        new String[] {"test", "value", "another", "else"}
                ), linkedHashMap3));
        
        //TreeMap
        treeMap1 = CastUtility.toMapType(testMap1, TreeMap.class, Integer.class, Boolean.class);
        Assert.assertNotNull(treeMap1);
        Assert.assertEquals(TreeMap.class, treeMap1.getClass());
        Assert.assertNotSame(testMap1, treeMap1);
        Assert.assertTrue(MapUtility.equals(
                MapUtility.mapOf(
                        new Integer[] {1, 4, 11},
                        new Boolean[] {false, true, true}),
                treeMap1));
        treeMap2 = CastUtility.toMapType(testMap2, TreeMap.class, String.class, String.class);
        Assert.assertNotNull(treeMap2);
        Assert.assertEquals(TreeMap.class, treeMap2.getClass());
        Assert.assertNotSame(testMap2, treeMap2);
        Assert.assertTrue(MapUtility.equals(
                MapUtility.mapOf(
                        new String[] {"test", "key", "other"},
                        new String[] {"test", "value", "another"}
                ), treeMap2));
        treeMap3 = CastUtility.toMapType(testMap3, TreeMap.class, Integer.class, String.class);
        Assert.assertNotNull(treeMap3);
        Assert.assertEquals(TreeMap.class, treeMap3.getClass());
        Assert.assertSame(testMap3, treeMap3);
        Assert.assertTrue(MapUtility.equals(
                MapUtility.mapOf(
                        new Integer[] {0, 9, -4, 10},
                        new String[] {"test", "value", "another", "else"}
                ), treeMap3));
        
        //invalid
        Assert.assertNotNull(CastUtility.toMapType(testMap1, HashMap.class, Integer.class, null));
        Assert.assertNotNull(CastUtility.toMapType(testMap1, HashMap.class, null, Boolean.class));
        Assert.assertNotNull(CastUtility.toMapType(testMap1, HashMap.class, null, null));
        Assert.assertNull(CastUtility.toMapType(testMap1, null, Integer.class, Boolean.class));
        Assert.assertNull(CastUtility.toMapType(testMap1, null, Integer.class, null));
        Assert.assertNull(CastUtility.toMapType(testMap1, null, null, Boolean.class));
        Assert.assertNull(CastUtility.toMapType(testMap1, null, null, null));
        Assert.assertNull(CastUtility.toMapType(null, HashMap.class, Integer.class, null));
        Assert.assertNull(CastUtility.toMapType(null, HashMap.class, null, Boolean.class));
        Assert.assertNull(CastUtility.toMapType(null, HashMap.class, null, null));
        Assert.assertNull(CastUtility.toMapType(null, null, Integer.class, Boolean.class));
        Assert.assertNull(CastUtility.toMapType(null, null, Integer.class, null));
        Assert.assertNull(CastUtility.toMapType(null, null, null, Boolean.class));
        Assert.assertNull(CastUtility.toMapType(null, null, null, null));
    }
    
}
