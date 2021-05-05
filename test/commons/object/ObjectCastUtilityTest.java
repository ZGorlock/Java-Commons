/*
 * File:    ObjectCastUtilityTest.java
 * Package: object
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object;

import java.math.BigDecimal;
import java.math.BigInteger;

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
 * JUnit test of ObjectCastUtility.
 *
 * @see ObjectCastUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection", "ConstantConditions", "UnnecessaryUnboxing"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ObjectCastUtility.class})
public class ObjectCastUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ObjectCastUtilityTest.class);
    
    
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
     * @see ObjectCastUtility#toBoolean(Object)
     */
    @Test
    public void testToBoolean() throws Exception {
        //boolean
        Assert.assertTrue(ObjectCastUtility.toBoolean(Boolean.TRUE).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(Boolean.FALSE).booleanValue());
        Assert.assertTrue(ObjectCastUtility.toBoolean(true).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(false).booleanValue());
        
        //byte
        Assert.assertFalse(ObjectCastUtility.toBoolean((byte) 1).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean((byte) 0).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean((byte) 6).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean((byte) -2).booleanValue());
        
        //short
        Assert.assertFalse(ObjectCastUtility.toBoolean((short) 1).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean((short) 0).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean((short) 32100).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean((short) -11054).booleanValue());
        
        //int
        Assert.assertFalse(ObjectCastUtility.toBoolean(1).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(0).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(6415123).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(-115411).booleanValue());
        
        //long
        Assert.assertFalse(ObjectCastUtility.toBoolean(1L).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(0L).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(15624653112L).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(-57988744511L).booleanValue());
        
        //float
        Assert.assertFalse(ObjectCastUtility.toBoolean(1.0f).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(0.0f).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(984.561f).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(-8.154f).booleanValue());
        
        //double
        Assert.assertFalse(ObjectCastUtility.toBoolean(1.0d).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(0.0d).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(984.565489421d).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(-8.154874652d).booleanValue());
        
        //big integer
        Assert.assertFalse(ObjectCastUtility.toBoolean(new BigInteger("1")).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(new BigInteger("0")).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(new BigInteger("948414649432084846513216")).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(new BigInteger("-1897562164944876591156")).booleanValue());
        
        //big decimal
        Assert.assertFalse(ObjectCastUtility.toBoolean(new BigDecimal("1")).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(new BigDecimal("0")).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(new BigDecimal("948414649432084846513216.54689231")).booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean(new BigDecimal("-1897562164959.0005484897")).booleanValue());
        
        //char
        Assert.assertFalse(ObjectCastUtility.toBoolean('t').booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean('f').booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean('0').booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean('1').booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean('6').booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean('n').booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean('\r').booleanValue());
        
        //string
        Assert.assertTrue(ObjectCastUtility.toBoolean("true").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("false").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("t").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("f").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("1").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("0").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("6").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("-2").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("1564984").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("1564567897562131").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("1564567897562131.6494564").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("test").booleanValue());
        Assert.assertFalse(ObjectCastUtility.toBoolean("").booleanValue());
    }
    
    /**
     * JUnit test of toByte.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toByte(Object)
     */
    @Test
    public void testToByte() throws Exception {
        //boolean
        Assert.assertNull(ObjectCastUtility.toByte(Boolean.TRUE));
        Assert.assertNull(ObjectCastUtility.toByte(Boolean.FALSE));
        Assert.assertNull(ObjectCastUtility.toByte(true));
        Assert.assertNull(ObjectCastUtility.toByte(false));
        
        //byte
        Assert.assertEquals(1, ObjectCastUtility.toByte((byte) 1).byteValue());
        Assert.assertEquals(0, ObjectCastUtility.toByte((byte) 0).byteValue());
        Assert.assertEquals(6, ObjectCastUtility.toByte((byte) 6).byteValue());
        Assert.assertEquals(-2, ObjectCastUtility.toByte((byte) -2).byteValue());
        
        //short
        Assert.assertEquals(1, ObjectCastUtility.toByte((short) 1).byteValue());
        Assert.assertEquals(0, ObjectCastUtility.toByte((short) 0).byteValue());
        Assert.assertNull(ObjectCastUtility.toByte((short) 32100));
        Assert.assertNull(ObjectCastUtility.toByte((short) -11054));
        
        //int
        Assert.assertEquals(1, ObjectCastUtility.toByte(1).byteValue());
        Assert.assertEquals(0, ObjectCastUtility.toByte(0).byteValue());
        Assert.assertNull(ObjectCastUtility.toByte(6415123));
        Assert.assertNull(ObjectCastUtility.toByte(-115411));
        
        //long
        Assert.assertEquals(1, ObjectCastUtility.toByte(1L).byteValue());
        Assert.assertEquals(0, ObjectCastUtility.toByte(0L).byteValue());
        Assert.assertNull(ObjectCastUtility.toByte(15624653112L));
        Assert.assertNull(ObjectCastUtility.toByte(-57988744511L));
        
        //float
        Assert.assertNull(ObjectCastUtility.toByte(1.0f));
        Assert.assertNull(ObjectCastUtility.toByte(0.0f));
        Assert.assertNull(ObjectCastUtility.toByte(984.561f));
        Assert.assertNull(ObjectCastUtility.toByte(-8.154f));
        
        //double
        Assert.assertNull(ObjectCastUtility.toByte(1.0d));
        Assert.assertNull(ObjectCastUtility.toByte(0.0d));
        Assert.assertNull(ObjectCastUtility.toByte(984.565489421d));
        Assert.assertNull(ObjectCastUtility.toByte(-8.154874652d));
        
        //big integer
        Assert.assertEquals(1, ObjectCastUtility.toByte(new BigInteger("1")).byteValue());
        Assert.assertEquals(0, ObjectCastUtility.toByte(new BigInteger("0")).byteValue());
        Assert.assertNull(ObjectCastUtility.toByte(new BigInteger("948414649432084846513216")));
        Assert.assertNull(ObjectCastUtility.toByte(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(1, ObjectCastUtility.toByte(new BigDecimal("1")).byteValue());
        Assert.assertEquals(0, ObjectCastUtility.toByte(new BigDecimal("0")).byteValue());
        Assert.assertNull(ObjectCastUtility.toByte(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(ObjectCastUtility.toByte(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(ObjectCastUtility.toByte('t'));
        Assert.assertNull(ObjectCastUtility.toByte('f'));
        Assert.assertEquals(0, ObjectCastUtility.toByte('0').byteValue());
        Assert.assertEquals(1, ObjectCastUtility.toByte('1').byteValue());
        Assert.assertEquals(6, ObjectCastUtility.toByte('6').byteValue());
        Assert.assertNull(ObjectCastUtility.toByte('n'));
        Assert.assertNull(ObjectCastUtility.toByte('\r'));
        
        //string
        Assert.assertNull(ObjectCastUtility.toByte("true"));
        Assert.assertNull(ObjectCastUtility.toByte("false"));
        Assert.assertNull(ObjectCastUtility.toByte("t"));
        Assert.assertNull(ObjectCastUtility.toByte("f"));
        Assert.assertEquals(1, ObjectCastUtility.toByte("1").byteValue());
        Assert.assertEquals(0, ObjectCastUtility.toByte("0").byteValue());
        Assert.assertEquals(6, ObjectCastUtility.toByte("6").byteValue());
        Assert.assertEquals(-2, ObjectCastUtility.toByte("-2").byteValue());
        Assert.assertNull(ObjectCastUtility.toByte("1564984"));
        Assert.assertNull(ObjectCastUtility.toByte("1564567897562131"));
        Assert.assertNull(ObjectCastUtility.toByte("1564567897562131.6494564"));
        Assert.assertNull(ObjectCastUtility.toByte("test"));
        Assert.assertNull(ObjectCastUtility.toByte(""));
    }
    
    /**
     * JUnit test of toShort.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toShort(Object)
     */
    @Test
    public void testToShort() throws Exception {
        //boolean
        Assert.assertNull(ObjectCastUtility.toShort(Boolean.TRUE));
        Assert.assertNull(ObjectCastUtility.toShort(Boolean.FALSE));
        Assert.assertNull(ObjectCastUtility.toShort(true));
        Assert.assertNull(ObjectCastUtility.toShort(false));
        
        //byte
        Assert.assertEquals(1, ObjectCastUtility.toShort((byte) 1).shortValue());
        Assert.assertEquals(0, ObjectCastUtility.toShort((byte) 0).shortValue());
        Assert.assertEquals(6, ObjectCastUtility.toShort((byte) 6).shortValue());
        Assert.assertEquals(-2, ObjectCastUtility.toShort((byte) -2).shortValue());
        
        //short
        Assert.assertEquals(1, ObjectCastUtility.toShort((short) 1).shortValue());
        Assert.assertEquals(0, ObjectCastUtility.toShort((short) 0).shortValue());
        Assert.assertEquals(32100, ObjectCastUtility.toShort((short) 32100).shortValue());
        Assert.assertEquals(-11054, ObjectCastUtility.toShort((short) -11054).shortValue());
        
        //int
        Assert.assertEquals(1, ObjectCastUtility.toShort(1).shortValue());
        Assert.assertEquals(0, ObjectCastUtility.toShort(0).shortValue());
        Assert.assertNull(ObjectCastUtility.toShort(6415123));
        Assert.assertNull(ObjectCastUtility.toShort(-115411));
        
        //long
        Assert.assertEquals(1, ObjectCastUtility.toShort(1L).shortValue());
        Assert.assertEquals(0, ObjectCastUtility.toShort(0L).shortValue());
        Assert.assertNull(ObjectCastUtility.toShort(15624653112L));
        Assert.assertNull(ObjectCastUtility.toShort(-57988744511L));
        
        //float
        Assert.assertNull(ObjectCastUtility.toShort(1.0f));
        Assert.assertNull(ObjectCastUtility.toShort(0.0f));
        Assert.assertNull(ObjectCastUtility.toShort(984.561f));
        Assert.assertNull(ObjectCastUtility.toShort(-8.154f));
        
        //double
        Assert.assertNull(ObjectCastUtility.toShort(1.0d));
        Assert.assertNull(ObjectCastUtility.toShort(0.0d));
        Assert.assertNull(ObjectCastUtility.toShort(984.565489421d));
        Assert.assertNull(ObjectCastUtility.toShort(-8.154874652d));
        
        //big integer
        Assert.assertEquals(1, ObjectCastUtility.toShort(new BigInteger("1")).shortValue());
        Assert.assertEquals(0, ObjectCastUtility.toShort(new BigInteger("0")).shortValue());
        Assert.assertNull(ObjectCastUtility.toShort(new BigInteger("948414649432084846513216")));
        Assert.assertNull(ObjectCastUtility.toShort(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(1, ObjectCastUtility.toShort(new BigDecimal("1")).shortValue());
        Assert.assertEquals(0, ObjectCastUtility.toShort(new BigDecimal("0")).shortValue());
        Assert.assertNull(ObjectCastUtility.toShort(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(ObjectCastUtility.toShort(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(ObjectCastUtility.toShort('t'));
        Assert.assertNull(ObjectCastUtility.toShort('f'));
        Assert.assertEquals(0, ObjectCastUtility.toShort('0').shortValue());
        Assert.assertEquals(1, ObjectCastUtility.toShort('1').shortValue());
        Assert.assertEquals(6, ObjectCastUtility.toShort('6').shortValue());
        Assert.assertNull(ObjectCastUtility.toShort('n'));
        Assert.assertNull(ObjectCastUtility.toShort('\r'));
        
        //string
        Assert.assertNull(ObjectCastUtility.toShort("true"));
        Assert.assertNull(ObjectCastUtility.toShort("false"));
        Assert.assertNull(ObjectCastUtility.toShort("t"));
        Assert.assertNull(ObjectCastUtility.toShort("f"));
        Assert.assertEquals(1, ObjectCastUtility.toShort("1").shortValue());
        Assert.assertEquals(0, ObjectCastUtility.toShort("0").shortValue());
        Assert.assertEquals(6, ObjectCastUtility.toShort("6").shortValue());
        Assert.assertEquals(-2, ObjectCastUtility.toShort("-2").shortValue());
        Assert.assertNull(ObjectCastUtility.toShort("1564984"));
        Assert.assertNull(ObjectCastUtility.toShort("1564567897562131"));
        Assert.assertNull(ObjectCastUtility.toShort("1564567897562131.6494564"));
        Assert.assertNull(ObjectCastUtility.toShort("test"));
        Assert.assertNull(ObjectCastUtility.toShort(""));
    }
    
    /**
     * JUnit test of toInt.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toInt(Object)
     */
    @Test
    public void testToInt() throws Exception {
        //boolean
        Assert.assertNull(ObjectCastUtility.toInt(Boolean.TRUE));
        Assert.assertNull(ObjectCastUtility.toInt(Boolean.FALSE));
        Assert.assertNull(ObjectCastUtility.toInt(true));
        Assert.assertNull(ObjectCastUtility.toInt(false));
        
        //byte
        Assert.assertEquals(1, ObjectCastUtility.toInt((byte) 1).intValue());
        Assert.assertEquals(0, ObjectCastUtility.toInt((byte) 0).intValue());
        Assert.assertEquals(6, ObjectCastUtility.toInt((byte) 6).intValue());
        Assert.assertEquals(-2, ObjectCastUtility.toInt((byte) -2).intValue());
        
        //short
        Assert.assertEquals(1, ObjectCastUtility.toInt((short) 1).intValue());
        Assert.assertEquals(0, ObjectCastUtility.toInt((short) 0).intValue());
        Assert.assertEquals(32100, ObjectCastUtility.toInt((short) 32100).intValue());
        Assert.assertEquals(-11054, ObjectCastUtility.toInt((short) -11054).intValue());
        
        //int
        Assert.assertEquals(1, ObjectCastUtility.toInt(1).intValue());
        Assert.assertEquals(0, ObjectCastUtility.toInt(0).intValue());
        Assert.assertEquals(6415123, ObjectCastUtility.toInt(6415123).intValue());
        Assert.assertEquals(-115411, ObjectCastUtility.toInt(-115411).intValue());
        
        //long
        Assert.assertEquals(1, ObjectCastUtility.toInt(1L).intValue());
        Assert.assertEquals(0, ObjectCastUtility.toInt(0L).intValue());
        Assert.assertNull(ObjectCastUtility.toInt(15624653112L));
        Assert.assertNull(ObjectCastUtility.toInt(-57988744511L));
        
        //float
        Assert.assertNull(ObjectCastUtility.toInt(1.0f));
        Assert.assertNull(ObjectCastUtility.toInt(0.0f));
        Assert.assertNull(ObjectCastUtility.toInt(984.561f));
        Assert.assertNull(ObjectCastUtility.toInt(-8.154f));
        
        //double
        Assert.assertNull(ObjectCastUtility.toInt(1.0d));
        Assert.assertNull(ObjectCastUtility.toInt(0.0d));
        Assert.assertNull(ObjectCastUtility.toInt(984.565489421d));
        Assert.assertNull(ObjectCastUtility.toInt(-8.154874652d));
        
        //big integer
        Assert.assertEquals(1, ObjectCastUtility.toInt(new BigInteger("1")).intValue());
        Assert.assertEquals(0, ObjectCastUtility.toInt(new BigInteger("0")).intValue());
        Assert.assertNull(ObjectCastUtility.toInt(new BigInteger("948414649432084846513216")));
        Assert.assertNull(ObjectCastUtility.toInt(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(1, ObjectCastUtility.toInt(new BigDecimal("1")).intValue());
        Assert.assertEquals(0, ObjectCastUtility.toInt(new BigDecimal("0")).intValue());
        Assert.assertNull(ObjectCastUtility.toInt(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(ObjectCastUtility.toInt(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(ObjectCastUtility.toInt('t'));
        Assert.assertNull(ObjectCastUtility.toInt('f'));
        Assert.assertEquals(0, ObjectCastUtility.toInt('0').intValue());
        Assert.assertEquals(1, ObjectCastUtility.toInt('1').intValue());
        Assert.assertEquals(6, ObjectCastUtility.toInt('6').intValue());
        Assert.assertNull(ObjectCastUtility.toInt('n'));
        Assert.assertNull(ObjectCastUtility.toInt('\r'));
        
        //string
        Assert.assertNull(ObjectCastUtility.toInt("true"));
        Assert.assertNull(ObjectCastUtility.toInt("false"));
        Assert.assertNull(ObjectCastUtility.toInt("t"));
        Assert.assertNull(ObjectCastUtility.toInt("f"));
        Assert.assertEquals(1, ObjectCastUtility.toInt("1").intValue());
        Assert.assertEquals(0, ObjectCastUtility.toInt("0").intValue());
        Assert.assertEquals(6, ObjectCastUtility.toInt("6").intValue());
        Assert.assertEquals(-2, ObjectCastUtility.toInt("-2").intValue());
        Assert.assertEquals(1564984, ObjectCastUtility.toInt("1564984").intValue());
        Assert.assertNull(ObjectCastUtility.toInt("1564567897562131"));
        Assert.assertNull(ObjectCastUtility.toInt("1564567897562131.6494564"));
        Assert.assertNull(ObjectCastUtility.toInt("test"));
        Assert.assertNull(ObjectCastUtility.toInt(""));
    }
    
    /**
     * JUnit test of toLong.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toLong(Object)
     */
    @Test
    public void testToLong() throws Exception {
        //boolean
        Assert.assertNull(ObjectCastUtility.toLong(Boolean.TRUE));
        Assert.assertNull(ObjectCastUtility.toLong(Boolean.FALSE));
        Assert.assertNull(ObjectCastUtility.toLong(true));
        Assert.assertNull(ObjectCastUtility.toLong(false));
        
        //byte
        Assert.assertEquals(1, ObjectCastUtility.toLong((byte) 1).longValue());
        Assert.assertEquals(0, ObjectCastUtility.toLong((byte) 0).longValue());
        Assert.assertEquals(6, ObjectCastUtility.toLong((byte) 6).longValue());
        Assert.assertEquals(-2, ObjectCastUtility.toLong((byte) -2).longValue());
        
        //short
        Assert.assertEquals(1, ObjectCastUtility.toLong((short) 1).longValue());
        Assert.assertEquals(0, ObjectCastUtility.toLong((short) 0).longValue());
        Assert.assertEquals(32100, ObjectCastUtility.toLong((short) 32100).longValue());
        Assert.assertEquals(-11054, ObjectCastUtility.toLong((short) -11054).longValue());
        
        //int
        Assert.assertEquals(1, ObjectCastUtility.toLong(1).longValue());
        Assert.assertEquals(0, ObjectCastUtility.toLong(0).longValue());
        Assert.assertEquals(6415123, ObjectCastUtility.toLong(6415123).longValue());
        Assert.assertEquals(-115411, ObjectCastUtility.toLong(-115411).longValue());
        
        //long
        Assert.assertEquals(1, ObjectCastUtility.toLong(1L).longValue());
        Assert.assertEquals(0, ObjectCastUtility.toLong(0L).longValue());
        Assert.assertEquals(15624653112L, ObjectCastUtility.toLong(15624653112L).longValue());
        Assert.assertEquals(-57988744511L, ObjectCastUtility.toLong(-57988744511L).longValue());
        
        //float
        Assert.assertNull(ObjectCastUtility.toLong(1.0f));
        Assert.assertNull(ObjectCastUtility.toLong(0.0f));
        Assert.assertNull(ObjectCastUtility.toLong(984.561f));
        Assert.assertNull(ObjectCastUtility.toLong(-8.154f));
        
        //double
        Assert.assertNull(ObjectCastUtility.toLong(1.0d));
        Assert.assertNull(ObjectCastUtility.toLong(0.0d));
        Assert.assertNull(ObjectCastUtility.toLong(984.565489421d));
        Assert.assertNull(ObjectCastUtility.toLong(-8.154874652d));
        
        //big integer
        Assert.assertEquals(1, ObjectCastUtility.toLong(new BigInteger("1")).longValue());
        Assert.assertEquals(0, ObjectCastUtility.toLong(new BigInteger("0")).longValue());
        Assert.assertNull(ObjectCastUtility.toLong(new BigInteger("948414649432084846513216")));
        Assert.assertNull(ObjectCastUtility.toLong(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(1, ObjectCastUtility.toLong(new BigDecimal("1")).longValue());
        Assert.assertEquals(0, ObjectCastUtility.toLong(new BigDecimal("0")).longValue());
        Assert.assertNull(ObjectCastUtility.toLong(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(ObjectCastUtility.toLong(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(ObjectCastUtility.toLong('t'));
        Assert.assertNull(ObjectCastUtility.toLong('f'));
        Assert.assertEquals(0, ObjectCastUtility.toLong('0').longValue());
        Assert.assertEquals(1, ObjectCastUtility.toLong('1').longValue());
        Assert.assertEquals(6, ObjectCastUtility.toLong('6').longValue());
        Assert.assertNull(ObjectCastUtility.toLong('n'));
        Assert.assertNull(ObjectCastUtility.toLong('\r'));
        
        //string
        Assert.assertNull(ObjectCastUtility.toLong("true"));
        Assert.assertNull(ObjectCastUtility.toLong("false"));
        Assert.assertNull(ObjectCastUtility.toLong("t"));
        Assert.assertNull(ObjectCastUtility.toLong("f"));
        Assert.assertEquals(1, ObjectCastUtility.toLong("1").longValue());
        Assert.assertEquals(0, ObjectCastUtility.toLong("0").longValue());
        Assert.assertEquals(6, ObjectCastUtility.toLong("6").longValue());
        Assert.assertEquals(-2, ObjectCastUtility.toLong("-2").longValue());
        Assert.assertEquals(1564984, ObjectCastUtility.toLong("1564984").longValue());
        Assert.assertEquals(1564567897562131L, ObjectCastUtility.toLong("1564567897562131").longValue());
        Assert.assertNull(ObjectCastUtility.toLong("1564567897562131.6494564"));
        Assert.assertNull(ObjectCastUtility.toLong("test"));
        Assert.assertNull(ObjectCastUtility.toLong(""));
    }
    
    /**
     * JUnit test of toFloat.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toFloat(Object)
     */
    @Test
    public void testToFloat() throws Exception {
        //boolean
        Assert.assertNull(ObjectCastUtility.toFloat(Boolean.TRUE));
        Assert.assertNull(ObjectCastUtility.toFloat(Boolean.FALSE));
        Assert.assertNull(ObjectCastUtility.toFloat(true));
        Assert.assertNull(ObjectCastUtility.toFloat(false));
        
        //byte
        Assert.assertEquals(1, ObjectCastUtility.toFloat((byte) 1).floatValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toFloat((byte) 0).floatValue(), 0.0000001);
        Assert.assertEquals(6, ObjectCastUtility.toFloat((byte) 6).floatValue(), 0.0000001);
        Assert.assertEquals(-2, ObjectCastUtility.toFloat((byte) -2).floatValue(), 0.0000001);
        
        //short
        Assert.assertEquals(1, ObjectCastUtility.toFloat((short) 1).floatValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toFloat((short) 0).floatValue(), 0.0000001);
        Assert.assertEquals(32100, ObjectCastUtility.toFloat((short) 32100).floatValue(), 0.0000001);
        Assert.assertEquals(-11054, ObjectCastUtility.toFloat((short) -11054).floatValue(), 0.0000001);
        
        //int
        Assert.assertEquals(1, ObjectCastUtility.toFloat(1).floatValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toFloat(0).floatValue(), 0.0000001);
        Assert.assertEquals(6415123, ObjectCastUtility.toFloat(6415123).floatValue(), 0.0000001);
        Assert.assertEquals(-115411, ObjectCastUtility.toFloat(-115411).floatValue(), 0.0000001);
        
        //long
        Assert.assertEquals(1, ObjectCastUtility.toFloat(1L).floatValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toFloat(0L).floatValue(), 0.0000001);
        Assert.assertEquals(1.56246528E10, ObjectCastUtility.toFloat(15624653112L).floatValue(), 0.0000001);
        Assert.assertEquals(-5.7988743168E10, ObjectCastUtility.toFloat(-57988744511L).floatValue(), 0.0000001);
        
        //float
        Assert.assertEquals(1.0, ObjectCastUtility.toFloat(1.0f).floatValue(), 0.0000001);
        Assert.assertEquals(0.0, ObjectCastUtility.toFloat(0.0f).floatValue(), 0.0000001);
        Assert.assertEquals(984.5609741210938, ObjectCastUtility.toFloat(984.561f).floatValue(), 0.0000001);
        Assert.assertEquals(-8.154000282287598, ObjectCastUtility.toFloat(-8.154f).floatValue(), 0.0000001);
        
        //double
        Assert.assertEquals(1.0, ObjectCastUtility.toFloat(1.0d).floatValue(), 0.0000001);
        Assert.assertEquals(0.0, ObjectCastUtility.toFloat(0.0d).floatValue(), 0.0000001);
        Assert.assertEquals(984.5654907226562, ObjectCastUtility.toFloat(984.565489421d).floatValue(), 0.0000001);
        Assert.assertEquals(-8.154874801635742, ObjectCastUtility.toFloat(-8.154874652d).floatValue(), 0.0000001);
        
        //big integer
        Assert.assertEquals(1, ObjectCastUtility.toFloat(new BigInteger("1")).floatValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toFloat(new BigInteger("0")).floatValue(), 0.0000001);
        Assert.assertEquals(9.484146307950216E23, ObjectCastUtility.toFloat(new BigInteger("948414649432084846513216")).floatValue(), 0.0000001);
        Assert.assertEquals(-1.897562148120004E21, ObjectCastUtility.toFloat(new BigInteger("-1897562164944876591156")).floatValue(), 0.0000001);
        
        //big decimal
        Assert.assertEquals(1, ObjectCastUtility.toFloat(new BigDecimal("1")).floatValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toFloat(new BigDecimal("0")).floatValue(), 0.0000001);
        Assert.assertEquals(9.484146307950216E23, ObjectCastUtility.toFloat(new BigDecimal("948414649432084846513216.54689231")).floatValue(), 0.0000001);
        Assert.assertEquals(-1.897562112E12, ObjectCastUtility.toFloat(new BigDecimal("-1897562164959.0005484897")).floatValue(), 0.0000001);
        
        //char
        Assert.assertNull(ObjectCastUtility.toFloat('t'));
        Assert.assertNull(ObjectCastUtility.toFloat('f'));
        Assert.assertEquals(0, ObjectCastUtility.toFloat('0').floatValue(), 0.0000001);
        Assert.assertEquals(1, ObjectCastUtility.toFloat('1').floatValue(), 0.0000001);
        Assert.assertEquals(6, ObjectCastUtility.toFloat('6').floatValue(), 0.0000001);
        Assert.assertNull(ObjectCastUtility.toFloat('n'));
        Assert.assertNull(ObjectCastUtility.toFloat('\r'));
        
        //string
        Assert.assertNull(ObjectCastUtility.toFloat("true"));
        Assert.assertNull(ObjectCastUtility.toFloat("false"));
        Assert.assertNull(ObjectCastUtility.toFloat("t"));
        Assert.assertNull(ObjectCastUtility.toFloat("f"));
        Assert.assertEquals(1, ObjectCastUtility.toFloat("1").floatValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toFloat("0").floatValue(), 0.0000001);
        Assert.assertEquals(6, ObjectCastUtility.toFloat("6").floatValue(), 0.0000001);
        Assert.assertEquals(-2, ObjectCastUtility.toFloat("-2").floatValue(), 0.0000001);
        Assert.assertEquals(1564984, ObjectCastUtility.toFloat("1564984").floatValue(), 0.0000001);
        Assert.assertEquals(1.564567868014592E15, ObjectCastUtility.toFloat("1564567897562131").floatValue(), 0.0000001);
        Assert.assertEquals(1.564567868014592E15, ObjectCastUtility.toFloat("1564567897562131.6494564").floatValue(), 0.0000001);
        Assert.assertNull(ObjectCastUtility.toFloat("test"));
        Assert.assertNull(ObjectCastUtility.toFloat(""));
    }
    
    /**
     * JUnit test of toDouble.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toDouble(Object)
     */
    @Test
    public void testToDouble() throws Exception {
        //boolean
        Assert.assertNull(ObjectCastUtility.toDouble(Boolean.TRUE));
        Assert.assertNull(ObjectCastUtility.toDouble(Boolean.FALSE));
        Assert.assertNull(ObjectCastUtility.toDouble(true));
        Assert.assertNull(ObjectCastUtility.toDouble(false));
        
        //byte
        Assert.assertEquals(1, ObjectCastUtility.toDouble((byte) 1).doubleValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toDouble((byte) 0).doubleValue(), 0.0000001);
        Assert.assertEquals(6, ObjectCastUtility.toDouble((byte) 6).doubleValue(), 0.0000001);
        Assert.assertEquals(-2, ObjectCastUtility.toDouble((byte) -2).doubleValue(), 0.0000001);
        
        //short
        Assert.assertEquals(1, ObjectCastUtility.toDouble((short) 1).doubleValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toDouble((short) 0).doubleValue(), 0.0000001);
        Assert.assertEquals(32100, ObjectCastUtility.toDouble((short) 32100).doubleValue(), 0.0000001);
        Assert.assertEquals(-11054, ObjectCastUtility.toDouble((short) -11054).doubleValue(), 0.0000001);
        
        //int
        Assert.assertEquals(1, ObjectCastUtility.toDouble(1).doubleValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toDouble(0).doubleValue(), 0.0000001);
        Assert.assertEquals(6415123, ObjectCastUtility.toDouble(6415123).doubleValue(), 0.0000001);
        Assert.assertEquals(-115411, ObjectCastUtility.toDouble(-115411).doubleValue(), 0.0000001);
        
        //long
        Assert.assertEquals(1, ObjectCastUtility.toDouble(1L).doubleValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toDouble(0L).doubleValue(), 0.0000001);
        Assert.assertEquals(1.5624653112E10, ObjectCastUtility.toDouble(15624653112L).doubleValue(), 0.0000001);
        Assert.assertEquals(-5.7988744511E10, ObjectCastUtility.toDouble(-57988744511L).doubleValue(), 0.0000001);
        
        //float
        Assert.assertEquals(1.0, ObjectCastUtility.toDouble(1.0f).doubleValue(), 0.0000001);
        Assert.assertEquals(0.0, ObjectCastUtility.toDouble(0.0f).doubleValue(), 0.0000001);
        Assert.assertEquals(984.561, ObjectCastUtility.toDouble(984.561f).doubleValue(), 0.0000001);
        Assert.assertEquals(-8.154, ObjectCastUtility.toDouble(-8.154f).doubleValue(), 0.0000001);
        
        //double
        Assert.assertEquals(1.0, ObjectCastUtility.toDouble(1.0d).doubleValue(), 0.0000001);
        Assert.assertEquals(0.0, ObjectCastUtility.toDouble(0.0d).doubleValue(), 0.0000001);
        Assert.assertEquals(984.565489421, ObjectCastUtility.toDouble(984.565489421d).doubleValue(), 0.0000001);
        Assert.assertEquals(-8.154874652, ObjectCastUtility.toDouble(-8.154874652d).doubleValue(), 0.0000001);
        
        //big integer
        Assert.assertEquals(1, ObjectCastUtility.toDouble(new BigInteger("1")).doubleValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toDouble(new BigInteger("0")).doubleValue(), 0.0000001);
        Assert.assertEquals(9.484146494320849E23, ObjectCastUtility.toDouble(new BigInteger("948414649432084846513216")).doubleValue(), 0.0000001);
        Assert.assertEquals(-1.8975621649448766E21, ObjectCastUtility.toDouble(new BigInteger("-1897562164944876591156")).doubleValue(), 0.0000001);
        
        //big decimal
        Assert.assertEquals(1, ObjectCastUtility.toDouble(new BigDecimal("1")).doubleValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toDouble(new BigDecimal("0")).doubleValue(), 0.0000001);
        Assert.assertEquals(9.484146494320849E23, ObjectCastUtility.toDouble(new BigDecimal("948414649432084846513216.54689231")).doubleValue(), 0.0000001);
        Assert.assertEquals(-1.8975621649590005E12, ObjectCastUtility.toDouble(new BigDecimal("-1897562164959.0005484897")).doubleValue(), 0.0000001);
        
        //char
        Assert.assertNull(ObjectCastUtility.toDouble('t'));
        Assert.assertNull(ObjectCastUtility.toDouble('f'));
        Assert.assertEquals(0, ObjectCastUtility.toDouble('0').doubleValue(), 0.0000001);
        Assert.assertEquals(1, ObjectCastUtility.toDouble('1').doubleValue(), 0.0000001);
        Assert.assertEquals(6, ObjectCastUtility.toDouble('6').doubleValue(), 0.0000001);
        Assert.assertNull(ObjectCastUtility.toDouble('n'));
        Assert.assertNull(ObjectCastUtility.toDouble('\r'));
        
        //string
        Assert.assertNull(ObjectCastUtility.toDouble("true"));
        Assert.assertNull(ObjectCastUtility.toDouble("false"));
        Assert.assertNull(ObjectCastUtility.toDouble("t"));
        Assert.assertNull(ObjectCastUtility.toDouble("f"));
        Assert.assertEquals(1, ObjectCastUtility.toDouble("1").doubleValue(), 0.0000001);
        Assert.assertEquals(0, ObjectCastUtility.toDouble("0").doubleValue(), 0.0000001);
        Assert.assertEquals(6, ObjectCastUtility.toDouble("6").doubleValue(), 0.0000001);
        Assert.assertEquals(-2, ObjectCastUtility.toDouble("-2").doubleValue(), 0.0000001);
        Assert.assertEquals(1564984, ObjectCastUtility.toDouble("1564984").doubleValue(), 0.0000001);
        Assert.assertEquals(1.564567897562131E15, ObjectCastUtility.toDouble("1564567897562131").doubleValue(), 0.0000001);
        Assert.assertEquals(1.5645678975621318E15, ObjectCastUtility.toDouble("1564567897562131.6494564").doubleValue(), 0.0000001);
        Assert.assertNull(ObjectCastUtility.toDouble("test"));
        Assert.assertNull(ObjectCastUtility.toDouble(""));
    }
    
    /**
     * JUnit test of toBigInteger.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toBigInteger(Object)
     */
    @Test
    public void testToBigInteger() throws Exception {
        //boolean
        Assert.assertNull(ObjectCastUtility.toBigInteger(Boolean.TRUE));
        Assert.assertNull(ObjectCastUtility.toBigInteger(Boolean.FALSE));
        Assert.assertNull(ObjectCastUtility.toBigInteger(true));
        Assert.assertNull(ObjectCastUtility.toBigInteger(false));
        
        //byte
        Assert.assertEquals(new BigInteger("1"), ObjectCastUtility.toBigInteger((byte) 1));
        Assert.assertEquals(new BigInteger("0"), ObjectCastUtility.toBigInteger((byte) 0));
        Assert.assertEquals(new BigInteger("6"), ObjectCastUtility.toBigInteger((byte) 6));
        Assert.assertEquals(new BigInteger("-2"), ObjectCastUtility.toBigInteger((byte) -2));
        
        //short
        Assert.assertEquals(new BigInteger("1"), ObjectCastUtility.toBigInteger((short) 1));
        Assert.assertEquals(new BigInteger("0"), ObjectCastUtility.toBigInteger((short) 0));
        Assert.assertEquals(new BigInteger("32100"), ObjectCastUtility.toBigInteger((short) 32100));
        Assert.assertEquals(new BigInteger("-11054"), ObjectCastUtility.toBigInteger((short) -11054));
        
        //int
        Assert.assertEquals(new BigInteger("1"), ObjectCastUtility.toBigInteger(1));
        Assert.assertEquals(new BigInteger("0"), ObjectCastUtility.toBigInteger(0));
        Assert.assertEquals(new BigInteger("6415123"), ObjectCastUtility.toBigInteger(6415123));
        Assert.assertEquals(new BigInteger("-115411"), ObjectCastUtility.toBigInteger(-115411));
        
        //long
        Assert.assertEquals(new BigInteger("1"), ObjectCastUtility.toBigInteger(1L));
        Assert.assertEquals(new BigInteger("0"), ObjectCastUtility.toBigInteger(0L));
        Assert.assertEquals(new BigInteger("15624653112"), ObjectCastUtility.toBigInteger(15624653112L));
        Assert.assertEquals(new BigInteger("-57988744511"), ObjectCastUtility.toBigInteger(-57988744511L));
        
        //float
        Assert.assertNull(ObjectCastUtility.toBigInteger(1.0f));
        Assert.assertNull(ObjectCastUtility.toBigInteger(0.0f));
        Assert.assertNull(ObjectCastUtility.toBigInteger(984.561f));
        Assert.assertNull(ObjectCastUtility.toBigInteger(-8.154f));
        
        //double
        Assert.assertNull(ObjectCastUtility.toBigInteger(1.0d));
        Assert.assertNull(ObjectCastUtility.toBigInteger(0.0d));
        Assert.assertNull(ObjectCastUtility.toBigInteger(984.565489421d));
        Assert.assertNull(ObjectCastUtility.toBigInteger(-8.154874652d));
        
        //big integer
        Assert.assertEquals(new BigInteger("1"), ObjectCastUtility.toBigInteger(new BigInteger("1")));
        Assert.assertEquals(new BigInteger("0"), ObjectCastUtility.toBigInteger(new BigInteger("0")));
        Assert.assertEquals(new BigInteger("948414649432084846513216"), ObjectCastUtility.toBigInteger(new BigInteger("948414649432084846513216")));
        Assert.assertEquals(new BigInteger("-1897562164944876591156"), ObjectCastUtility.toBigInteger(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(new BigInteger("1"), ObjectCastUtility.toBigInteger(new BigDecimal("1")));
        Assert.assertEquals(new BigInteger("0"), ObjectCastUtility.toBigInteger(new BigDecimal("0")));
        Assert.assertNull(ObjectCastUtility.toBigInteger(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(ObjectCastUtility.toBigInteger(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(ObjectCastUtility.toBigInteger('t'));
        Assert.assertNull(ObjectCastUtility.toBigInteger('f'));
        Assert.assertEquals(new BigInteger("0"), ObjectCastUtility.toBigInteger('0'));
        Assert.assertEquals(new BigInteger("1"), ObjectCastUtility.toBigInteger('1'));
        Assert.assertEquals(new BigInteger("6"), ObjectCastUtility.toBigInteger('6'));
        Assert.assertNull(ObjectCastUtility.toBigInteger('n'));
        Assert.assertNull(ObjectCastUtility.toBigInteger('\r'));
        
        //string
        Assert.assertNull(ObjectCastUtility.toBigInteger("true"));
        Assert.assertNull(ObjectCastUtility.toBigInteger("false"));
        Assert.assertNull(ObjectCastUtility.toBigInteger("t"));
        Assert.assertNull(ObjectCastUtility.toBigInteger("f"));
        Assert.assertEquals(new BigInteger("1"), ObjectCastUtility.toBigInteger("1"));
        Assert.assertEquals(new BigInteger("0"), ObjectCastUtility.toBigInteger("0"));
        Assert.assertEquals(new BigInteger("6"), ObjectCastUtility.toBigInteger("6"));
        Assert.assertEquals(new BigInteger("-2"), ObjectCastUtility.toBigInteger("-2"));
        Assert.assertEquals(new BigInteger("1564984"), ObjectCastUtility.toBigInteger("1564984"));
        Assert.assertEquals(new BigInteger("1564567897562131"), ObjectCastUtility.toBigInteger("1564567897562131"));
        Assert.assertNull(ObjectCastUtility.toBigInteger("1564567897562131.6494564"));
        Assert.assertNull(ObjectCastUtility.toBigInteger("test"));
        Assert.assertNull(ObjectCastUtility.toBigInteger(""));
    }
    
    /**
     * JUnit test of toBigDecimal.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toBigDecimal(Object)
     */
    @Test
    public void testToBigDecimal() throws Exception {
        //boolean
        Assert.assertNull(ObjectCastUtility.toBigDecimal(Boolean.TRUE));
        Assert.assertNull(ObjectCastUtility.toBigDecimal(Boolean.FALSE));
        Assert.assertNull(ObjectCastUtility.toBigDecimal(true));
        Assert.assertNull(ObjectCastUtility.toBigDecimal(false));
        
        //byte
        Assert.assertEquals(new BigDecimal("1"), ObjectCastUtility.toBigDecimal((byte) 1));
        Assert.assertEquals(new BigDecimal("0"), ObjectCastUtility.toBigDecimal((byte) 0));
        Assert.assertEquals(new BigDecimal("6"), ObjectCastUtility.toBigDecimal((byte) 6));
        Assert.assertEquals(new BigDecimal("-2"), ObjectCastUtility.toBigDecimal((byte) -2));
        
        //short
        Assert.assertEquals(new BigDecimal("1"), ObjectCastUtility.toBigDecimal((short) 1));
        Assert.assertEquals(new BigDecimal("0"), ObjectCastUtility.toBigDecimal((short) 0));
        Assert.assertEquals(new BigDecimal("32100"), ObjectCastUtility.toBigDecimal((short) 32100));
        Assert.assertEquals(new BigDecimal("-11054"), ObjectCastUtility.toBigDecimal((short) -11054));
        
        //int
        Assert.assertEquals(new BigDecimal("1"), ObjectCastUtility.toBigDecimal(1));
        Assert.assertEquals(new BigDecimal("0"), ObjectCastUtility.toBigDecimal(0));
        Assert.assertEquals(new BigDecimal("6415123"), ObjectCastUtility.toBigDecimal(6415123));
        Assert.assertEquals(new BigDecimal("-115411"), ObjectCastUtility.toBigDecimal(-115411));
        
        //long
        Assert.assertEquals(new BigDecimal("1"), ObjectCastUtility.toBigDecimal(1L));
        Assert.assertEquals(new BigDecimal("0"), ObjectCastUtility.toBigDecimal(0L));
        Assert.assertEquals(new BigDecimal("15624653112"), ObjectCastUtility.toBigDecimal(15624653112L));
        Assert.assertEquals(new BigDecimal("-57988744511"), ObjectCastUtility.toBigDecimal(-57988744511L));
        
        //float
        Assert.assertEquals(new BigDecimal("1.0"), ObjectCastUtility.toBigDecimal(1.0f));
        Assert.assertEquals(new BigDecimal("0.0"), ObjectCastUtility.toBigDecimal(0.0f));
        Assert.assertEquals(new BigDecimal("984.561"), ObjectCastUtility.toBigDecimal(984.561f));
        Assert.assertEquals(new BigDecimal("-8.154"), ObjectCastUtility.toBigDecimal(-8.154f));
        
        //double
        Assert.assertEquals(new BigDecimal("1.0"), ObjectCastUtility.toBigDecimal(1.0d));
        Assert.assertEquals(new BigDecimal("0.0"), ObjectCastUtility.toBigDecimal(0.0d));
        Assert.assertEquals(new BigDecimal("984.565489421"), ObjectCastUtility.toBigDecimal(984.565489421d));
        Assert.assertEquals(new BigDecimal("-8.154874652"), ObjectCastUtility.toBigDecimal(-8.154874652d));
        
        //big integer
        Assert.assertEquals(new BigDecimal("1"), ObjectCastUtility.toBigDecimal(new BigInteger("1")));
        Assert.assertEquals(new BigDecimal("0"), ObjectCastUtility.toBigDecimal(new BigInteger("0")));
        Assert.assertEquals(new BigDecimal("948414649432084846513216"), ObjectCastUtility.toBigDecimal(new BigInteger("948414649432084846513216")));
        Assert.assertEquals(new BigDecimal("-1897562164944876591156"), ObjectCastUtility.toBigDecimal(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals(new BigDecimal("1"), ObjectCastUtility.toBigDecimal(new BigDecimal("1")));
        Assert.assertEquals(new BigDecimal("0"), ObjectCastUtility.toBigDecimal(new BigDecimal("0")));
        Assert.assertEquals(new BigDecimal("948414649432084846513216.54689231"), ObjectCastUtility.toBigDecimal(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertEquals(new BigDecimal("-1897562164959.0005484897"), ObjectCastUtility.toBigDecimal(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertNull(ObjectCastUtility.toBigDecimal('t'));
        Assert.assertNull(ObjectCastUtility.toBigDecimal('f'));
        Assert.assertEquals(new BigDecimal("0"), ObjectCastUtility.toBigDecimal('0'));
        Assert.assertEquals(new BigDecimal("1"), ObjectCastUtility.toBigDecimal('1'));
        Assert.assertEquals(new BigDecimal("6"), ObjectCastUtility.toBigDecimal('6'));
        Assert.assertNull(ObjectCastUtility.toBigDecimal('n'));
        Assert.assertNull(ObjectCastUtility.toBigDecimal('\r'));
        
        //string
        Assert.assertNull(ObjectCastUtility.toBigDecimal("true"));
        Assert.assertNull(ObjectCastUtility.toBigDecimal("false"));
        Assert.assertNull(ObjectCastUtility.toBigDecimal("t"));
        Assert.assertNull(ObjectCastUtility.toBigDecimal("f"));
        Assert.assertEquals(new BigDecimal("1"), ObjectCastUtility.toBigDecimal("1"));
        Assert.assertEquals(new BigDecimal("0"), ObjectCastUtility.toBigDecimal("0"));
        Assert.assertEquals(new BigDecimal("6"), ObjectCastUtility.toBigDecimal("6"));
        Assert.assertEquals(new BigDecimal("-2"), ObjectCastUtility.toBigDecimal("-2"));
        Assert.assertEquals(new BigDecimal("1564984"), ObjectCastUtility.toBigDecimal("1564984"));
        Assert.assertEquals(new BigDecimal("1564567897562131"), ObjectCastUtility.toBigDecimal("1564567897562131"));
        Assert.assertEquals(new BigDecimal("1564567897562131.6494564"), ObjectCastUtility.toBigDecimal("1564567897562131.6494564"));
        Assert.assertNull(ObjectCastUtility.toBigDecimal("test"));
        Assert.assertNull(ObjectCastUtility.toBigDecimal(""));
    }
    
    /**
     * JUnit test of toChar.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toChar(Object)
     */
    @Test
    public void testToChar() throws Exception {
        //boolean
        Assert.assertNull(ObjectCastUtility.toChar(Boolean.TRUE));
        Assert.assertNull(ObjectCastUtility.toChar(Boolean.FALSE));
        Assert.assertNull(ObjectCastUtility.toChar(true));
        Assert.assertNull(ObjectCastUtility.toChar(false));
        
        //byte
        Assert.assertEquals('1', ObjectCastUtility.toChar((byte) 1).charValue());
        Assert.assertEquals('0', ObjectCastUtility.toChar((byte) 0).charValue());
        Assert.assertEquals('6', ObjectCastUtility.toChar((byte) 6).charValue());
        Assert.assertNull(ObjectCastUtility.toChar((byte) -2));
        
        //short
        Assert.assertEquals('1', ObjectCastUtility.toChar((short) 1).charValue());
        Assert.assertEquals('0', ObjectCastUtility.toChar((short) 0).charValue());
        Assert.assertNull(ObjectCastUtility.toChar((short) 32100));
        Assert.assertNull(ObjectCastUtility.toChar((short) -11054));
        
        //int
        Assert.assertEquals('1', ObjectCastUtility.toChar(1).charValue());
        Assert.assertEquals('0', ObjectCastUtility.toChar(0).charValue());
        Assert.assertNull(ObjectCastUtility.toChar(6415123));
        Assert.assertNull(ObjectCastUtility.toChar(-115411));
        
        //long
        Assert.assertEquals('1', ObjectCastUtility.toChar(1L).charValue());
        Assert.assertEquals('0', ObjectCastUtility.toChar(0L).charValue());
        Assert.assertNull(ObjectCastUtility.toChar(15624653112L));
        Assert.assertNull(ObjectCastUtility.toChar(-57988744511L));
        
        //float
        Assert.assertNull(ObjectCastUtility.toChar(1.0f));
        Assert.assertNull(ObjectCastUtility.toChar(0.0f));
        Assert.assertNull(ObjectCastUtility.toChar(984.561f));
        Assert.assertNull(ObjectCastUtility.toChar(-8.154f));
        
        //double
        Assert.assertNull(ObjectCastUtility.toChar(1.0d));
        Assert.assertNull(ObjectCastUtility.toChar(0.0d));
        Assert.assertNull(ObjectCastUtility.toChar(984.565489421d));
        Assert.assertNull(ObjectCastUtility.toChar(-8.154874652d));
        
        //big integer
        Assert.assertEquals('1', ObjectCastUtility.toChar(new BigInteger("1")).charValue());
        Assert.assertEquals('0', ObjectCastUtility.toChar(new BigInteger("0")).charValue());
        Assert.assertNull(ObjectCastUtility.toChar(new BigInteger("948414649432084846513216")));
        Assert.assertNull(ObjectCastUtility.toChar(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals('1', ObjectCastUtility.toChar(new BigDecimal("1")).charValue());
        Assert.assertEquals('0', ObjectCastUtility.toChar(new BigDecimal("0")).charValue());
        Assert.assertNull(ObjectCastUtility.toChar(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertNull(ObjectCastUtility.toChar(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertEquals('t', ObjectCastUtility.toChar('t').charValue());
        Assert.assertEquals('f', ObjectCastUtility.toChar('f').charValue());
        Assert.assertEquals('0', ObjectCastUtility.toChar('0').charValue());
        Assert.assertEquals('1', ObjectCastUtility.toChar('1').charValue());
        Assert.assertEquals('6', ObjectCastUtility.toChar('6').charValue());
        Assert.assertEquals('n', ObjectCastUtility.toChar('n').charValue());
        Assert.assertEquals('\r', ObjectCastUtility.toChar('\r').charValue());
        
        //string
        Assert.assertNull(ObjectCastUtility.toChar("true"));
        Assert.assertNull(ObjectCastUtility.toChar("false"));
        Assert.assertEquals('t', ObjectCastUtility.toChar("t").charValue());
        Assert.assertEquals('f', ObjectCastUtility.toChar("f").charValue());
        Assert.assertEquals('1', ObjectCastUtility.toChar("1").charValue());
        Assert.assertEquals('0', ObjectCastUtility.toChar("0").charValue());
        Assert.assertEquals('6', ObjectCastUtility.toChar("6").charValue());
        Assert.assertNull(ObjectCastUtility.toChar("-2"));
        Assert.assertNull(ObjectCastUtility.toChar("1564984"));
        Assert.assertNull(ObjectCastUtility.toChar("1564567897562131"));
        Assert.assertNull(ObjectCastUtility.toChar("1564567897562131.6494564"));
        Assert.assertNull(ObjectCastUtility.toChar("test"));
        Assert.assertNull(ObjectCastUtility.toChar(""));
    }
    
    /**
     * JUnit test of toString.
     *
     * @throws Exception When there is an exception.
     * @see ObjectCastUtility#toString(Object)
     */
    @Test
    public void testToString() throws Exception {
        //boolean
        Assert.assertEquals("true", ObjectCastUtility.toString(Boolean.TRUE));
        Assert.assertEquals("false", ObjectCastUtility.toString(Boolean.FALSE));
        Assert.assertEquals("true", ObjectCastUtility.toString(true));
        Assert.assertEquals("false", ObjectCastUtility.toString(false));
        
        //byte
        Assert.assertEquals("1", ObjectCastUtility.toString((byte) 1));
        Assert.assertEquals("0", ObjectCastUtility.toString((byte) 0));
        Assert.assertEquals("6", ObjectCastUtility.toString((byte) 6));
        Assert.assertEquals("-2", ObjectCastUtility.toString((byte) -2));
        
        //short
        Assert.assertEquals("1", ObjectCastUtility.toString((short) 1));
        Assert.assertEquals("0", ObjectCastUtility.toString((short) 0));
        Assert.assertEquals("32100", ObjectCastUtility.toString((short) 32100));
        Assert.assertEquals("-11054", ObjectCastUtility.toString((short) -11054));
        
        //int
        Assert.assertEquals("1", ObjectCastUtility.toString(1));
        Assert.assertEquals("0", ObjectCastUtility.toString(0));
        Assert.assertEquals("6415123", ObjectCastUtility.toString(6415123));
        Assert.assertEquals("-115411", ObjectCastUtility.toString(-115411));
        
        //long
        Assert.assertEquals("1", ObjectCastUtility.toString(1L));
        Assert.assertEquals("0", ObjectCastUtility.toString(0L));
        Assert.assertEquals("15624653112", ObjectCastUtility.toString(15624653112L));
        Assert.assertEquals("-57988744511", ObjectCastUtility.toString(-57988744511L));
        
        //float
        Assert.assertEquals("1.0", ObjectCastUtility.toString(1.0f));
        Assert.assertEquals("0.0", ObjectCastUtility.toString(0.0f));
        Assert.assertEquals("984.561", ObjectCastUtility.toString(984.561f));
        Assert.assertEquals("-8.154", ObjectCastUtility.toString(-8.154f));
        
        //double
        Assert.assertEquals("1.0", ObjectCastUtility.toString(1.0d));
        Assert.assertEquals("0.0", ObjectCastUtility.toString(0.0d));
        Assert.assertEquals("984.565489421", ObjectCastUtility.toString(984.565489421d));
        Assert.assertEquals("-8.154874652", ObjectCastUtility.toString(-8.154874652d));
        
        //big integer
        Assert.assertEquals("1", ObjectCastUtility.toString(new BigInteger("1")));
        Assert.assertEquals("0", ObjectCastUtility.toString(new BigInteger("0")));
        Assert.assertEquals("948414649432084846513216", ObjectCastUtility.toString(new BigInteger("948414649432084846513216")));
        Assert.assertEquals("-1897562164944876591156", ObjectCastUtility.toString(new BigInteger("-1897562164944876591156")));
        
        //big decimal
        Assert.assertEquals("1", ObjectCastUtility.toString(new BigDecimal("1")));
        Assert.assertEquals("0", ObjectCastUtility.toString(new BigDecimal("0")));
        Assert.assertEquals("948414649432084846513216.54689231", ObjectCastUtility.toString(new BigDecimal("948414649432084846513216.54689231")));
        Assert.assertEquals("-1897562164959.0005484897", ObjectCastUtility.toString(new BigDecimal("-1897562164959.0005484897")));
        
        //char
        Assert.assertEquals("t", ObjectCastUtility.toString('t'));
        Assert.assertEquals("f", ObjectCastUtility.toString('f'));
        Assert.assertEquals("0", ObjectCastUtility.toString('0'));
        Assert.assertEquals("1", ObjectCastUtility.toString('1'));
        Assert.assertEquals("6", ObjectCastUtility.toString('6'));
        Assert.assertEquals("n", ObjectCastUtility.toString('n'));
        Assert.assertEquals("\r", ObjectCastUtility.toString('\r'));
        
        //string
        Assert.assertEquals("true", ObjectCastUtility.toString("true"));
        Assert.assertEquals("false", ObjectCastUtility.toString("false"));
        Assert.assertEquals("t", ObjectCastUtility.toString("t"));
        Assert.assertEquals("f", ObjectCastUtility.toString("f"));
        Assert.assertEquals("1", ObjectCastUtility.toString("1"));
        Assert.assertEquals("0", ObjectCastUtility.toString("0"));
        Assert.assertEquals("6", ObjectCastUtility.toString("6"));
        Assert.assertEquals("-2", ObjectCastUtility.toString("-2"));
        Assert.assertEquals("1564984", ObjectCastUtility.toString("1564984"));
        Assert.assertEquals("1564567897562131", ObjectCastUtility.toString("1564567897562131"));
        Assert.assertEquals("1564567897562131.6494564", ObjectCastUtility.toString("1564567897562131.6494564"));
        Assert.assertEquals("test", ObjectCastUtility.toString("test"));
        Assert.assertEquals("", ObjectCastUtility.toString(""));
    }
    
}
