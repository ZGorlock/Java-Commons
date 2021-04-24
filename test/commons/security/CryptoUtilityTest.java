/*
 * File:    CryptoUtilityTest.java
 * Package: security
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.security;

import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;

import commons.access.Filesystem;
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
 * JUnit test of CryptoUtility.
 *
 * @see CryptoUtility
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CryptoUtility.class})
public class CryptoUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CryptoUtilityTest.class);
    
    
    //Fields
    
    /**
     * The sample string to use for testing cryptography.
     */
    private final String message = "A sample message for cryptography";
    
    
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
     * @see CryptoUtility#DSA_ALGORITHM
     * @see CryptoUtility#RSA_ALGORITHM
     * @see CryptoUtility#AES_ALGORITHM
     * @see CryptoUtility#SHA256_ALGORITHM
     * @see CryptoUtility#SHA512_ALGORITHM
     * @see CryptoUtility#MD5_ALGORITHM
     * @see CryptoUtility#CBC_MODE
     * @see CryptoUtility#ECB_MODE
     * @see CryptoUtility#NO_PADDING
     * @see CryptoUtility#PKCS1_PADDING
     * @see CryptoUtility#PKCS5_PADDING
     * @see CryptoUtility#SHA1PRNG_ALGORITHM
     * @see CryptoUtility#SHADSA_ALGORITHM
     * @see CryptoUtility#SHA256RSA_ALGORITHM
     * @see CryptoUtility#UTF8_ENCODING
     * @see CryptoUtility#UTF16_ENCODING
     * @see CryptoUtility#ISO_8859_1_ENCODING
     * @see CryptoUtility#HASH_ITERATIONS
     * @see CryptoUtility#AES_PASSWORD_LENGTH
     * @see CryptoUtility#KEY_SIZE_2048
     * @see CryptoUtility#KEY_SIZE_1024
     * @see CryptoUtility#KEY_SIZE_512
     * @see CryptoUtility#AES_KEY_INIT
     * @see CryptoUtility#RSA_MAX_DATA_LENGTH
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals("DSA", CryptoUtility.DSA_ALGORITHM);
        Assert.assertEquals("RSA", CryptoUtility.RSA_ALGORITHM);
        Assert.assertEquals("AES", CryptoUtility.AES_ALGORITHM);
        Assert.assertEquals("SHA-256", CryptoUtility.SHA256_ALGORITHM);
        Assert.assertEquals("SHA-512", CryptoUtility.SHA512_ALGORITHM);
        Assert.assertEquals("MD5", CryptoUtility.MD5_ALGORITHM);
        Assert.assertEquals("CBC", CryptoUtility.CBC_MODE);
        Assert.assertEquals("ECB", CryptoUtility.ECB_MODE);
        Assert.assertEquals("NoPadding", CryptoUtility.NO_PADDING);
        Assert.assertEquals("PKCS1Padding", CryptoUtility.PKCS1_PADDING);
        Assert.assertEquals("PKCS5Padding", CryptoUtility.PKCS5_PADDING);
        Assert.assertEquals("SHA1PRNG", CryptoUtility.SHA1PRNG_ALGORITHM);
        Assert.assertEquals("SHA/DSA", CryptoUtility.SHADSA_ALGORITHM);
        Assert.assertEquals("SHA256withRSA", CryptoUtility.SHA256RSA_ALGORITHM);
        Assert.assertEquals("UTF-8", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("UTF-16", CryptoUtility.UTF16_ENCODING);
        Assert.assertEquals("ISO-8859-1", CryptoUtility.ISO_8859_1_ENCODING);
        Assert.assertEquals(1024, CryptoUtility.HASH_ITERATIONS);
        Assert.assertEquals(16, CryptoUtility.AES_PASSWORD_LENGTH);
        Assert.assertEquals(2048, CryptoUtility.KEY_SIZE_2048);
        Assert.assertEquals(1024, CryptoUtility.KEY_SIZE_1024);
        Assert.assertEquals(512, CryptoUtility.KEY_SIZE_512);
        Assert.assertEquals(128, CryptoUtility.AES_KEY_INIT);
        Assert.assertEquals(128, CryptoUtility.RSA_MAX_DATA_LENGTH);
    }
    
    /**
     * JUnit test of DSA Cryptography.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#generateDSAKeyPair()
     * @see CryptoUtility#storeDSAPrivateKey(PrivateKey)
     * @see CryptoUtility#readDSAPrivateKey(String)
     * @see CryptoUtility#storeDSAPublicKey(PublicKey)
     * @see CryptoUtility#readDSAPublicKey(String)
     * @see CryptoUtility#signDSA(String, PrivateKey)
     * @see CryptoUtility#verifyDSA(String, String, PublicKey)
     */
    @Test
    public void testDSACryptography() throws Exception {
        KeyPair kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        
        String kspr = CryptoUtility.storeDSAPrivateKey(kp.getPrivate());
        Assert.assertNotEquals("", kspr);
        
        PrivateKey prk = CryptoUtility.readDSAPrivateKey(kspr);
        Assert.assertNotNull(prk);
        
        String kspu = CryptoUtility.storeDSAPublicKey(kp.getPublic());
        Assert.assertNotEquals("", kspu);
        
        PublicKey puk = CryptoUtility.readDSAPublicKey(kspu);
        Assert.assertNotNull(puk);
        
        String s = CryptoUtility.signDSA(message, prk);
        Assert.assertNotEquals("", s);
        
        Assert.assertTrue(CryptoUtility.verifyDSA(message, s, puk));
    }
    
    /**
     * JUnit test of RSA Cryptography.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#generateRSAKeyPair()
     * @see CryptoUtility#storeRSAPrivateKey(PrivateKey)
     * @see CryptoUtility#readRSAPrivateKey(String)
     * @see CryptoUtility#storeRSAPublicKey(PublicKey)
     * @see CryptoUtility#readRSAPublicKey(String)
     * @see CryptoUtility#encryptRSA(String, PublicKey)
     * @see CryptoUtility#decryptRSA(String, PrivateKey)
     * @see CryptoUtility#signRSA(String, PrivateKey)
     * @see CryptoUtility#verifyRSA(String, String, PublicKey)
     */
    @Test
    public void testRSACryptography() throws Exception {
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        
        String kspr = CryptoUtility.storeRSAPrivateKey(kp.getPrivate());
        Assert.assertNotEquals("", kspr);
        
        PrivateKey prk = CryptoUtility.readRSAPrivateKey(kspr);
        Assert.assertNotNull(prk);
        
        String kspu = CryptoUtility.storeRSAPublicKey(kp.getPublic());
        Assert.assertNotEquals("", kspu);
        
        PublicKey puk = CryptoUtility.readRSAPublicKey(kspu);
        Assert.assertNotNull(puk);
        
        String e = CryptoUtility.encryptRSA(message, puk);
        Assert.assertNotEquals("", e);
        
        String d = CryptoUtility.decryptRSA(e, prk);
        Assert.assertEquals(message, d);
        
        String s = CryptoUtility.signRSA(message, prk);
        Assert.assertNotEquals("", s);
        
        Assert.assertTrue(CryptoUtility.verifyRSA(message, s, puk));
    }
    
    /**
     * JUnit test of AES Cryptography.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#generateAESKey()
     * @see CryptoUtility#storeAESSecret(SecretKey)
     * @see CryptoUtility#readAESSecret(String)
     * @see CryptoUtility#encryptAES(String, SecretKey, String, String, String)
     * @see CryptoUtility#decryptAES(String, SecretKey, String, String)
     */
    @Test
    public void testAESCryptography() throws Exception {
        SecretKey kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        
        String kss = CryptoUtility.storeAESSecret(kp);
        Assert.assertNotEquals("", kss);
        
        SecretKey sk = CryptoUtility.readAESSecret(kss);
        Assert.assertNotNull(sk);
        
        String e = CryptoUtility.encryptAES(message, sk);
        Assert.assertNotEquals("", e);
        
        String d = CryptoUtility.decryptAES(e, sk);
        Assert.assertEquals(message, d);
    }
    
    /**
     * JUnit test of AES Key Transfer.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#generateAESKey()
     * @see CryptoUtility#storeAESSecret(SecretKey)
     * @see CryptoUtility#generateRSAKeyPair()
     * @see CryptoUtility#encryptRSA(String, PublicKey)
     * @see CryptoUtility#decryptRSA(String, PrivateKey)
     */
    @Test
    public void testAESKeyTransfer() throws Exception {
        SecretKey secret = CryptoUtility.generateAESKey();
        Assert.assertNotNull(secret);
        
        String secretStore = CryptoUtility.storeAESSecret(secret);
        Assert.assertNotEquals("", secretStore);
        
        KeyPair rsa = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(rsa);
        
        String e = CryptoUtility.encryptRSA(secretStore, rsa.getPublic());
        Assert.assertNotEquals(secretStore, e);
        
        String d = CryptoUtility.decryptRSA(e, rsa.getPrivate());
        Assert.assertEquals(secretStore, d);
    }
    
    /**
     * JUnit test of generateKeyPair.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#generateKeyPair(String, int)
     */
    @Test
    public void testGenerateKeyPair() throws Exception {
        //dsa
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, CryptoUtility.KEY_SIZE_2048));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, CryptoUtility.KEY_SIZE_1024));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, CryptoUtility.KEY_SIZE_512));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, CryptoUtility.KEY_SIZE_2048 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, CryptoUtility.KEY_SIZE_2048 - 1));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, CryptoUtility.KEY_SIZE_1024 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, CryptoUtility.KEY_SIZE_1024 - 1));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, CryptoUtility.KEY_SIZE_512 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, CryptoUtility.KEY_SIZE_512 - 1));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, 9999));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, 300));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.DSA_ALGORITHM, 1));
        
        //rsa
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, CryptoUtility.KEY_SIZE_2048));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, CryptoUtility.KEY_SIZE_1024));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, CryptoUtility.KEY_SIZE_512));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, CryptoUtility.KEY_SIZE_2048 + 1));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, CryptoUtility.KEY_SIZE_2048 - 1));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, CryptoUtility.KEY_SIZE_1024 + 1));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, CryptoUtility.KEY_SIZE_1024 - 1));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, CryptoUtility.KEY_SIZE_512 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, CryptoUtility.KEY_SIZE_512 - 1));
        Assert.assertNotNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, 999));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, 300));
        Assert.assertNull(CryptoUtility.generateKeyPair(CryptoUtility.RSA_ALGORITHM, 1));
        
        //unknown
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", CryptoUtility.KEY_SIZE_2048));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", CryptoUtility.KEY_SIZE_1024));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", CryptoUtility.KEY_SIZE_512));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", CryptoUtility.KEY_SIZE_2048 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", CryptoUtility.KEY_SIZE_2048 - 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", CryptoUtility.KEY_SIZE_1024 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", CryptoUtility.KEY_SIZE_1024 - 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", CryptoUtility.KEY_SIZE_512 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", CryptoUtility.KEY_SIZE_512 - 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", 9999));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", 300));
        Assert.assertNull(CryptoUtility.generateKeyPair("ALGORITHM", 1));
        
        //empty algorithm
        Assert.assertNull(CryptoUtility.generateKeyPair("", CryptoUtility.KEY_SIZE_2048));
        Assert.assertNull(CryptoUtility.generateKeyPair("", CryptoUtility.KEY_SIZE_1024));
        Assert.assertNull(CryptoUtility.generateKeyPair("", CryptoUtility.KEY_SIZE_512));
        Assert.assertNull(CryptoUtility.generateKeyPair("", CryptoUtility.KEY_SIZE_2048 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("", CryptoUtility.KEY_SIZE_2048 - 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("", CryptoUtility.KEY_SIZE_1024 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("", CryptoUtility.KEY_SIZE_1024 - 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("", CryptoUtility.KEY_SIZE_512 + 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("", CryptoUtility.KEY_SIZE_512 - 1));
        Assert.assertNull(CryptoUtility.generateKeyPair("", 9999));
        Assert.assertNull(CryptoUtility.generateKeyPair("", 300));
        Assert.assertNull(CryptoUtility.generateKeyPair("", 1));
    }
    
    /**
     * JUnit test of generateDSAKeyPair.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#generateDSAKeyPair()
     */
    @Test
    public void testGenerateDSAKeyPair() throws Exception {
        Assert.assertNotNull(CryptoUtility.generateDSAKeyPair());
    }
    
    /**
     * JUnit test of generateRSAKeyPair.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#generateRSAKeyPair()
     */
    @Test
    public void testGenerateRSAKeyPair() throws Exception {
        Assert.assertNotNull(CryptoUtility.generateRSAKeyPair());
    }
    
    /**
     * JUnit test of generateAESKey.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#generateAESKey()
     */
    @Test
    public void testGenerateAESKey() throws Exception {
        Assert.assertNotNull(CryptoUtility.generateAESKey());
    }
    
    /**
     * JUnit test of encrypt.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#encrypt(String, PublicKey, String, String, String, String)
     */
    @Test
    public void testEncrypt() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        
        //dsa
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.DSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
        
        //over max data length
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message + message + message + message + message + message + message + message,
                kp.getPublic(), CryptoUtility.DSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
        
        //invalid algorithm
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), "ALGORITHM", "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
        
        //invalid encoding
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", "UTF-7");
        Assert.assertEquals("", e);
        
        //invalid block mode
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "BLOCK", CryptoUtility.PKCS5_PADDING, CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
        
        //invalid padding algorithm
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, CryptoUtility.CBC_MODE, "PADDING", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
        
        //invalid key
        e = CryptoUtility.encrypt(message, null, CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
    }
    
    /**
     * JUnit test of encryptRSA.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#encryptRSA(String, PublicKey)
     */
    @Test
    public void testEncryptRSA() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        
        //invalid key
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
    }
    
    /**
     * JUnit test of encryptAES.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#encryptAES(String, SecretKey, String, String, String)
     */
    @Test
    public void testEncryptAES() throws Exception {
        //valid
        SecretKey kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        String e = CryptoUtility.encryptAES(message, kp);
        Assert.assertNotEquals("", e);
        
        //aes
        kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encryptAES(message, kp, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        
        //invalid encoding
        kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encryptAES(message, kp, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING, "UTF-7");
        Assert.assertEquals("", e);
        
        //invalid block mode
        kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encryptAES(message, kp, "BLOCK", CryptoUtility.PKCS5_PADDING, CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
        
        //invalid padding algorithm
        kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encryptAES(message, kp, CryptoUtility.CBC_MODE, "PADDING", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
        
        //invalid key
        e = CryptoUtility.encryptAES(message, null, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING, CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", e);
    }
    
    /**
     * JUnit test of encryptWithPassword.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#encryptWithPassword(String, String)
     */
    @Test
    public void testEncryptWithPassword() throws Exception {
        //normal password
        String e = CryptoUtility.encryptWithPassword(message, "password");
        Assert.assertNotEquals("", e);
        
        //short password
        e = CryptoUtility.encryptWithPassword(message, "p");
        Assert.assertNotEquals("", e);
        
        //empty password
        e = CryptoUtility.encryptWithPassword(message, "");
        Assert.assertNotEquals("", e);
        
        //long password
        e = CryptoUtility.encryptWithPassword(message, "a really long password");
        Assert.assertNotEquals("", e);
    }
    
    /**
     * JUnit test of decrypt.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#decrypt(String, PrivateKey, String, String, String)
     */
    @Test
    public void testDecrypt() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        String d = CryptoUtility.decrypt(e, kp.getPrivate(), CryptoUtility.RSA_ALGORITHM, "", "");
        Assert.assertEquals(message, d);
        
        //over max data length
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decrypt(e + e + e + e + e + e + e,
                kp.getPrivate(), CryptoUtility.DSA_ALGORITHM, "", "");
        Assert.assertEquals("", d);
        
        //invalid algorithm
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decrypt(e, kp.getPrivate(), "ALGORITHM", "", "");
        Assert.assertEquals("", d);
        
        //invalid block mode
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decrypt(e, kp.getPrivate(), CryptoUtility.RSA_ALGORITHM, "BLOCK", CryptoUtility.PKCS5_PADDING);
        Assert.assertEquals("", d);
        
        //invalid padding algorithm
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decrypt(e, kp.getPrivate(), CryptoUtility.RSA_ALGORITHM, CryptoUtility.CBC_MODE, "PADDING");
        Assert.assertEquals("", d);
        
        //invalid key
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encrypt(message, kp.getPublic(), CryptoUtility.RSA_ALGORITHM, "", "", CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decrypt(e, null, CryptoUtility.RSA_ALGORITHM, "", "");
        Assert.assertEquals("", d);
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        d = CryptoUtility.decrypt(e, kp.getPrivate(), CryptoUtility.RSA_ALGORITHM, "", "");
        Assert.assertEquals("", d);
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        d = CryptoUtility.decrypt(e, kp.getPrivate(), CryptoUtility.RSA_ALGORITHM, "", "");
        Assert.assertEquals("", d);
    }
    
    /**
     * JUnit test of decryptRSA.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#decryptRSA(String, PrivateKey)
     */
    @Test
    public void testDecryptRSA() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String e = CryptoUtility.encryptRSA(message, kp.getPublic());
        Assert.assertNotEquals("", e);
        String d = CryptoUtility.decryptRSA(e, kp.getPrivate());
        Assert.assertEquals(message, d);
        
        //valid
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encryptRSA(message, kp.getPublic());
        Assert.assertNotEquals("", e);
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        d = CryptoUtility.decryptRSA(e, kp.getPrivate());
        Assert.assertEquals("", d);
    }
    
    /**
     * JUnit test of decryptAES.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#decryptAES(String, SecretKey, String, String)
     */
    @Test
    public void testDecryptAES() throws Exception {
        //valid
        SecretKey kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        String e = CryptoUtility.encryptAES(message, kp);
        Assert.assertNotEquals("", e);
        String d = CryptoUtility.decryptAES(e, kp);
        Assert.assertEquals(message, d);
        
        //aes
        kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encryptAES(message, kp, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decryptAES(e, kp, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING);
        Assert.assertEquals(message, d);
        
        //invalid block mode
        kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encryptAES(message, kp, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decryptAES(message, kp, "BLOCK", CryptoUtility.PKCS5_PADDING);
        Assert.assertEquals("", d);
        
        //invalid padding algorithm
        kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encryptAES(message, kp, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decryptAES(message, kp, CryptoUtility.CBC_MODE, "PADDING");
        Assert.assertEquals("", d);
        
        //invalid key
        kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        e = CryptoUtility.encryptAES(message, kp, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decryptAES(message, null, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING);
        Assert.assertEquals("", d);
        kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        d = CryptoUtility.decryptAES(message, kp, CryptoUtility.CBC_MODE, CryptoUtility.PKCS5_PADDING);
        Assert.assertEquals("", d);
    }
    
    /**
     * JUnit test of decryptWithPassword.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#decryptWithPassword(String, String)
     */
    @Test
    public void testDecryptWithPassword() throws Exception {
        //normal password
        String e = CryptoUtility.encryptWithPassword(message, "password");
        Assert.assertNotEquals("", e);
        String d = CryptoUtility.decryptWithPassword(e, "password");
        Assert.assertEquals(message, d);
        
        //short password
        e = CryptoUtility.encryptWithPassword(message, "p");
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decryptWithPassword(e, "p");
        Assert.assertEquals(message, d);
        
        //empty password
        e = CryptoUtility.encryptWithPassword(message, "");
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decryptWithPassword(e, "");
        Assert.assertEquals(message, d);
        
        //long password
        e = CryptoUtility.encryptWithPassword(message, "a really long password");
        Assert.assertNotEquals("", e);
        d = CryptoUtility.decryptWithPassword(e, "a really long password");
        Assert.assertEquals(message, d);
    }
    
    /**
     * JUnit test of sign.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#sign(String, PrivateKey, String, String)
     */
    @Test
    public void testSign() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        String s = CryptoUtility.sign(message, kp.getPrivate(), CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", s);
        
        //invalid algorithm
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.sign(message, kp.getPrivate(), "ALGORITHM", CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", s);
        
        //invalid encoding
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.sign(message, kp.getPrivate(), CryptoUtility.SHADSA_ALGORITHM, "UTF-7");
        Assert.assertEquals("", s);
        
        //invalid key
        s = CryptoUtility.sign(message, null, CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", s);
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.sign(message, kp.getPrivate(), CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", s);
    }
    
    /**
     * JUnit test of signDSA.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#signDSA(String, PrivateKey)
     */
    @Test
    public void testSignDSA() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        String s = CryptoUtility.signDSA(message, kp.getPrivate());
        Assert.assertNotEquals("", s);
        
        //invalid key
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.signDSA(message, kp.getPrivate());
        Assert.assertEquals("", s);
    }
    
    /**
     * JUnit test of signRSA.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#signRSA(String, PrivateKey)
     */
    @Test
    public void testSignRSA() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String s = CryptoUtility.signRSA(message, kp.getPrivate());
        Assert.assertNotEquals("", s);
        
        //invalid key
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.signRSA(message, kp.getPrivate());
        Assert.assertEquals("", s);
    }
    
    /**
     * JUnit test of verify.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#verify(String, String, PublicKey, String, String)
     */
    @Test
    public void testVerify() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        String s = CryptoUtility.sign(message, kp.getPrivate(), CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", s);
        Assert.assertTrue(CryptoUtility.verify(message, s, kp.getPublic(), CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING));
        
        //invalid algorithm
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.sign(message, kp.getPrivate(), CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", s);
        Assert.assertFalse(CryptoUtility.verify(message, s, kp.getPublic(), "ALGORITHM", CryptoUtility.UTF8_ENCODING));
        
        //invalid encoding
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.sign(message, kp.getPrivate(), CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", s);
        Assert.assertFalse(CryptoUtility.verify(message, s, kp.getPublic(), CryptoUtility.SHADSA_ALGORITHM, "UTF-7"));
        
        //invalid key
        s = CryptoUtility.sign(message, null, CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", s);
        
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.sign(message, kp.getPrivate(), CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING);
        Assert.assertNotEquals("", s);
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        Assert.assertFalse(CryptoUtility.verify(message, s, kp.getPublic(), CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING));
        
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.sign(message, kp.getPrivate(), CryptoUtility.SHADSA_ALGORITHM, CryptoUtility.UTF8_ENCODING);
        Assert.assertEquals("", s);
    }
    
    /**
     * JUnit test of verifyDSA.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#verifyDSA(String, String, PublicKey)
     */
    @Test
    public void testVerifyDSA() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        String s = CryptoUtility.signDSA(message, kp.getPrivate());
        Assert.assertNotEquals("", s);
        Assert.assertTrue(CryptoUtility.verifyDSA(message, s, kp.getPublic()));
        
        //invalid key
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.signDSA(message, kp.getPrivate());
        Assert.assertNotEquals("", s);
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        Assert.assertFalse(CryptoUtility.verifyDSA(message, s, kp.getPublic()));
    }
    
    /**
     * JUnit test of verifyRSA.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#verifyRSA(String, String, PublicKey)
     */
    @Test
    public void testVerifyRSA() throws Exception {
        //valid
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String s = CryptoUtility.signRSA(message, kp.getPrivate());
        Assert.assertNotEquals("", s);
        Assert.assertTrue(CryptoUtility.verifyRSA(message, s, kp.getPublic()));
        
        //invalid key
        kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        s = CryptoUtility.signRSA(message, kp.getPrivate());
        Assert.assertNotEquals("", s);
        kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        Assert.assertFalse(CryptoUtility.verifyRSA(message, s, kp.getPublic()));
    }
    
    /**
     * JUnit test of hash.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#hash(String, String, String)
     */
    @Test
    public void testHash() throws Exception {
        //sha256
        Assert.assertNotEquals("", CryptoUtility.hash(message, CryptoUtility.SHA256_ALGORITHM, CryptoUtility.UTF8_ENCODING));
        
        //sha512
        Assert.assertNotEquals("", CryptoUtility.hash(message, CryptoUtility.SHA256_ALGORITHM, CryptoUtility.UTF8_ENCODING));
        
        //invalid algorithm
        Assert.assertEquals("", CryptoUtility.hash(message, "ALGORITHM", CryptoUtility.UTF8_ENCODING));
        
        //invalid encoding
        Assert.assertEquals("", CryptoUtility.hash(message, CryptoUtility.SHA256_ALGORITHM, "UTF-7"));
    }
    
    /**
     * JUnit test of hashSHA256.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#hashSHA256(String, boolean)
     */
    @Test
    public void testHashSHA256() throws Exception {
        //default
        Assert.assertEquals("79a88beb5de0884ee41d1252c22300cfd2bf95cd3842a6839a6e4fd62e7fd552",
                CryptoUtility.hashSHA256(message));
        
        //iterate off
        Assert.assertEquals("79a88beb5de0884ee41d1252c22300cfd2bf95cd3842a6839a6e4fd62e7fd552",
                CryptoUtility.hashSHA256(message, false));
        
        //iterate on
        Assert.assertEquals("6e0da579805bdc7e066c345674f86cc2e33846948d04a1c753e964f6a08ce1fd",
                CryptoUtility.hashSHA256(message, true));
    }
    
    /**
     * JUnit test of hashSHA512.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#hashSHA512(String, boolean)
     */
    @Test
    public void testHashSHA512() throws Exception {
        //default
        Assert.assertEquals("f8e6d7758bd636222e95e1933c06e03ed35291fef822827694a71ae7da10b263aa001a2d6182616bed74ab599f431856564b94338d59e67b84bc6daf8a95ebaf",
                CryptoUtility.hashSHA512(message));
        
        //iterate off
        Assert.assertEquals("f8e6d7758bd636222e95e1933c06e03ed35291fef822827694a71ae7da10b263aa001a2d6182616bed74ab599f431856564b94338d59e67b84bc6daf8a95ebaf",
                CryptoUtility.hashSHA512(message, false));
        
        //iterate on
        Assert.assertEquals("60ebacc9a87ad5b87a4c23bdf54ddf82c14a0e9fd403b055cfc9b6ebd4ab89dfd402ce167bbf5c32ef28af45a61016b2690e38afc7bc9414d9ba947b8b92fdcf",
                CryptoUtility.hashSHA512(message, true));
    }
    
    /**
     * JUnit test of checksum.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#checksum(String, String)
     */
    @Test
    public void testChecksum() throws Exception {
        File dir = new File("tmp", "testDir");
        Assert.assertTrue(Filesystem.createDirectory(dir));
        File file = new File(dir, "testChecksumMD5.txt");
        File file2 = new File(dir, "testChecksumMD5x.txt");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Filesystem.deleteDirectory(dir)));
        
        String checksum;
        String checksum2;
        
        Assert.assertTrue(Filesystem.writeStringToFile(file, message));
        
        //valid
        Assert.assertNotEquals("", CryptoUtility.checksum(file.getAbsolutePath(), CryptoUtility.MD5_ALGORITHM));
        
        //directory
        checksum = CryptoUtility.checksum(dir.getAbsolutePath(), CryptoUtility.MD5_ALGORITHM);
        Assert.assertNotEquals("", checksum);
        
        //directory multiple files
        Assert.assertTrue(Filesystem.writeStringToFile(file2, message));
        checksum2 = CryptoUtility.checksum(dir.getAbsolutePath(), CryptoUtility.MD5_ALGORITHM);
        Assert.assertNotEquals("", checksum2);
        Assert.assertNotEquals(checksum2, checksum);
        
        //invalid algorithm
        Assert.assertEquals("", CryptoUtility.checksum(file.getAbsolutePath(), "ALGORITHM"));
        
        //invalid file
        Assert.assertEquals("", CryptoUtility.checksum(file.getAbsolutePath() + ".invalid", CryptoUtility.MD5_ALGORITHM));
    }
    
    /**
     * JUnit test of checksumMD5.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#checksumMD5(String)
     */
    @Test
    public void testChecksumMD5() throws Exception {
        File file = new File("tmp", "testChecksumMD5.txt");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Filesystem.deleteFile(file)));
        
        Assert.assertTrue(Filesystem.writeStringToFile(file, message));
        
        Assert.assertEquals("000000000000000000000000000000001e39effaec303bac0f30ed554528a7c9",
                CryptoUtility.checksumMD5(file.getAbsolutePath()));
    }
    
    /**
     * JUnit test of storeDSAPrivateKey.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#storeDSAPrivateKey(PrivateKey)
     */
    @Test
    public void testStoreDSAPrivateKey() throws Exception {
        KeyPair kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        String kspr = CryptoUtility.storeDSAPrivateKey(kp.getPrivate());
        Assert.assertNotEquals("", kspr);
    }
    
    /**
     * JUnit test of storeDSAPublicKey.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#storeDSAPublicKey(PublicKey)
     */
    @Test
    public void testStoreDSAPublicKey() throws Exception {
        KeyPair kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        String kspu = CryptoUtility.storeDSAPublicKey(kp.getPublic());
        Assert.assertNotEquals("", kspu);
    }
    
    /**
     * JUnit test of readDSAPrivateKey.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#readDSAPrivateKey(String)
     */
    @Test
    public void testReadDSAPrivateKey() throws Exception {
        KeyPair kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        String kspr = CryptoUtility.storeDSAPrivateKey(kp.getPrivate());
        Assert.assertNotEquals("", kspr);
        PrivateKey prk = CryptoUtility.readDSAPrivateKey(kspr);
        Assert.assertEquals(kp.getPrivate(), prk);
    }
    
    /**
     * JUnit test of readDSAPublicKey.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#readDSAPublicKey(String)
     */
    @Test
    public void testReadDSAPublicKey() throws Exception {
        KeyPair kp = CryptoUtility.generateDSAKeyPair();
        Assert.assertNotNull(kp);
        String kspu = CryptoUtility.storeDSAPublicKey(kp.getPublic());
        Assert.assertNotEquals("", kspu);
        PublicKey puk = CryptoUtility.readDSAPublicKey(kspu);
        Assert.assertNotNull(puk);
    }
    
    /**
     * JUnit test of storeRSAPrivateKey.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#storeRSAPrivateKey(PrivateKey)
     */
    @Test
    public void testStoreRSAPrivateKey() throws Exception {
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String kspr = CryptoUtility.storeRSAPrivateKey(kp.getPrivate());
        Assert.assertNotEquals("", kspr);
    }
    
    /**
     * JUnit test of storeRSAPublicKey.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#storeRSAPublicKey(PublicKey)
     */
    @Test
    public void testStoreRSAPublicKey() throws Exception {
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String kspr = CryptoUtility.storeRSAPrivateKey(kp.getPrivate());
        Assert.assertNotEquals("", kspr);
        PrivateKey prk = CryptoUtility.readRSAPrivateKey(kspr);
        Assert.assertNotNull(prk);
    }
    
    /**
     * JUnit test of readRSAPrivateKey.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#readRSAPrivateKey(String)
     */
    @Test
    public void testReadRSAPrivateKey() throws Exception {
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String kspu = CryptoUtility.storeRSAPublicKey(kp.getPublic());
        Assert.assertNotEquals("", kspu);
    }
    
    /**
     * JUnit test of readRSAPublicKey.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#readRSAPublicKey(String)
     */
    @Test
    public void testReadRSAPublicKey() throws Exception {
        KeyPair kp = CryptoUtility.generateRSAKeyPair();
        Assert.assertNotNull(kp);
        String kspu = CryptoUtility.storeRSAPublicKey(kp.getPublic());
        Assert.assertNotEquals("", kspu);
        PublicKey puk = CryptoUtility.readRSAPublicKey(kspu);
        Assert.assertNotNull(puk);
    }
    
    /**
     * JUnit test of storeAESSecret.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#storeAESSecret(SecretKey)
     */
    @Test
    public void testStoreAESSecret() throws Exception {
        SecretKey kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        String kss = CryptoUtility.storeAESSecret(kp);
        Assert.assertNotEquals("", kss);
    }
    
    /**
     * JUnit test of readAESSecret.
     *
     * @throws Exception When there is an exception.
     * @see CryptoUtility#readAESSecret(String)
     */
    @Test
    public void testReadAESSecret() throws Exception {
        SecretKey kp = CryptoUtility.generateAESKey();
        Assert.assertNotNull(kp);
        String kss = CryptoUtility.storeAESSecret(kp);
        Assert.assertNotEquals("", kss);
        SecretKey sk = CryptoUtility.readAESSecret(kss);
        Assert.assertNotNull(sk);
    }
    
}
