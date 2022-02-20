/*
 * File:    CryptoUtility.java
 * Package: commons.security
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Vector;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import commons.access.Filesystem;
import commons.object.string.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides additional cryptographic functionality.
 */
public final class CryptoUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CryptoUtility.class);
    
    
    //Constants
    
    /**
     * The identifier of the Digital Signature Algorithm.
     */
    public static final String DSA_ALGORITHM = "DSA";
    
    /**
     * The identifier of the RSA algorithm.
     */
    public static final String RSA_ALGORITHM = "RSA";
    
    /**
     * The identifier of the Advanced Encryption Standard algorithm.
     */
    public static final String AES_ALGORITHM = "AES";
    
    /**
     * The identifier of the Secure Hash 256 Algorithm.
     */
    public static final String SHA256_ALGORITHM = "SHA-256";
    
    /**
     * The identifier of the Secure Hash 512 Algorithm.
     */
    public static final String SHA512_ALGORITHM = "SHA-512";
    
    /**
     * The identifier of the Message Digest 5 algorithm.
     */
    public static final String MD5_ALGORITHM = "MD5";
    
    /**
     * The identifier of the Cipher Block Chaining block mode.
     */
    public static final String CBC_MODE = "CBC";
    
    /**
     * The identifier of the Electronic Code Block block mode.
     */
    public static final String ECB_MODE = "ECB";
    
    /**
     * The identifier of the No Padding padding mode.
     */
    public static final String NO_PADDING = "NoPadding";
    
    /**
     * The identifier of the Password-Based Encryption 1 Standard padding mode.
     */
    public static final String PKCS1_PADDING = "PKCS1Padding";
    
    /**
     * The identifier of the Password-Based Encryption 5 Standard padding mode.
     */
    public static final String PKCS5_PADDING = "PKCS5Padding";
    
    /**
     * The identifier of the SHA-1 Pseudo-Random Number Generator algorithm.
     */
    public static final String SHA1PRNG_ALGORITHM = "SHA1PRNG";
    
    /**
     * The identifier of the SHA with DSA algorithm.
     */
    public static final String SHADSA_ALGORITHM = "SHA/DSA";
    
    /**
     * The identifier of the SHA-256 with RSA algorithm.
     */
    public static final String SHA256RSA_ALGORITHM = "SHA256withRSA";
    
    /**
     * The identifier of UTF-8 encoding.
     */
    public static final String UTF8_ENCODING = "UTF-8";
    
    /**
     * The identifier of UTF-16 encoding.
     */
    public static final String UTF16_ENCODING = "UTF-16";
    
    /**
     * The identifier of ISO-8859-1 encoding.
     */
    public static final String ISO_8859_1_ENCODING = "ISO-8859-1";
    
    /**
     * The number of iterations to apply when producing an SHA hash.
     */
    public static final int HASH_ITERATIONS = 1024;
    
    /**
     * The length of a password used to encrypt with AES.
     */
    public static final int AES_PASSWORD_LENGTH = 16;
    
    /**
     * The length of a 2048 bit key.
     */
    public static final int KEY_SIZE_2048 = 2048;
    
    /**
     * The length of a 1024 bit key.
     */
    public static final int KEY_SIZE_1024 = 1024;
    
    /**
     * The length of a 512 bit key.
     */
    public static final int KEY_SIZE_512 = 512;
    
    /**
     * The value to initialize AES keys.
     */
    public static final int AES_KEY_INIT = 128;
    
    /**
     * The max length of data to use with RSA encryption and decryption.
     */
    public static final int RSA_MAX_DATA_LENGTH = 128;
    
    
    //Static Methods
    
    /**
     * Generates a private/public key pair for the specified algorithm.
     *
     * @param algorithm The algorithm to generate the key pair for.
     * @param keySize   The size in bits of the key to generate.
     * @return The resulting key pair.
     */
    public static KeyPair generateKeyPair(String algorithm, int keySize) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
            
            SecureRandom rnd = SecureRandom.getInstance(SHA1PRNG_ALGORITHM);
            keyGen.initialize(keySize, rnd);
            
            return keyGen.generateKeyPair();
            
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", algorithm);
        } catch (InvalidParameterException ignored) {
            logger.error("The key size: {} for algorithm: {} is not valid", keySize, algorithm);
        }
        return null;
    }
    
    /**
     * Generates a private/public key pair for the DSA algorithm.
     *
     * @return The resulting key pair.
     * @see #generateKeyPair(String, int)
     */
    public static KeyPair generateDSAKeyPair() {
        return generateKeyPair(DSA_ALGORITHM, KEY_SIZE_1024);
    }
    
    /**
     * Generates a private/public key pair for the RSA algorithm.
     *
     * @return The resulting key pair.
     * @see #generateKeyPair(String, int)
     */
    public static KeyPair generateRSAKeyPair() {
        return generateKeyPair(RSA_ALGORITHM, KEY_SIZE_1024);
    }
    
    /**
     * Generates a secret key for the AES algorithm.
     *
     * @return The resulting secret key.
     */
    public static SecretKey generateAESKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(AES_ALGORITHM);
            keyGen.init(AES_KEY_INIT);
            
            return keyGen.generateKey();
            
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", AES_ALGORITHM);
        }
        return null;
    }
    
    /**
     * Encrypts a message with a public key.
     *
     * @param message   The message to encrypt.
     * @param publicKey The public key to encrypt the message with.
     * @param algorithm The algorithm to use to encrypt with.
     * @param blockMode The block mode to use.
     * @param padding   The padding method to use.
     * @param encoding  The encoding of the message.
     * @return The encrypted message.
     */
    public static String encrypt(String message, PublicKey publicKey, String algorithm, String blockMode, String padding, String encoding) {
        try {
            String cipherMode = algorithm;
            if (!blockMode.isEmpty() && !padding.isEmpty()) {
                cipherMode += '/' + blockMode + '/' + padding;
            }
            
            Cipher cipher = Cipher.getInstance(cipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            byte[] data = message.getBytes(encoding);
            if (algorithm.equals(RSA_ALGORITHM) && (data.length > RSA_MAX_DATA_LENGTH)) {
                logger.error("RSA cannot encrypt messages longer than {} bytes", RSA_MAX_DATA_LENGTH);
                return "";
            }
            
            byte[] result = cipher.doFinal(data);
            return Base64.getEncoder().encodeToString(result);
            
        } catch (UnsupportedEncodingException ignored) {
            logger.error("The encoding: {} is not supported", encoding);
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", algorithm);
        } catch (IllegalBlockSizeException ignored) {
            //logger.error("The block mode: {} can not be used with the algorithm: {}", blockMode, algorithm);
        } catch (NoSuchPaddingException ignored) {
            logger.error("The padding method: {} does not exist", padding);
        } catch (BadPaddingException ignored) {
            //logger.error("The padding method: {} can not be used with the algorithm: {} using the block mode: {}", padding, algorithm, blockMode);
        } catch (InvalidKeyException ignored) {
            logger.error("The public key provided is invalid");
        } catch (IllegalArgumentException ignored) {
        }
        return "";
    }
    
    /**
     * Encrypts a message with a public key using the RSA algorithm.
     *
     * @param message   The message to encrypt.
     * @param publicKey The public key to encrypt the message with.
     * @return The encrypted message.
     * @see #encrypt(String, PublicKey, String, String, String, String)
     */
    public static String encryptRSA(String message, PublicKey publicKey) {
        return encrypt(message, publicKey, RSA_ALGORITHM, "", "", UTF8_ENCODING);
    }
    
    /**
     * Encrypts a message with a shared secret using the AES algorithm.
     *
     * @param message   The message to encrypt.
     * @param secret    The shared secret to encrypt the message with.
     * @param blockMode The block mode to use.
     * @param padding   The padding method to use.
     * @param encoding  The encoding of the message.
     * @return The encrypted message.
     */
    public static String encryptAES(String message, SecretKey secret, String blockMode, String padding, String encoding) {
        String algorithm = AES_ALGORITHM;
        
        try {
            String cipherMode = algorithm;
            if (!blockMode.isEmpty() && !padding.isEmpty()) {
                cipherMode += '/' + blockMode + '/' + padding;
            }
            
            Cipher cipher = Cipher.getInstance(cipherMode);
            IvParameterSpec iv = new IvParameterSpec(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            cipher.init(Cipher.ENCRYPT_MODE, secret, iv);
            
            byte[] data = message.getBytes(encoding);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data));
            
        } catch (UnsupportedEncodingException ignored) {
            logger.error("The encoding: {} is not supported", encoding);
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", algorithm);
        } catch (IllegalBlockSizeException ignored) {
            //logger.error("The block mode: {} can not be used with the algorithm: {}", blockMode, algorithm);
        } catch (NoSuchPaddingException ignored) {
            logger.error("The padding method: {} does not exist", padding);
        } catch (BadPaddingException ignored) {
            //logger.error("The padding method: {} can not be used with the algorithm: {} using the block mode: {}", padding, algorithm, blockMode);
        } catch (InvalidAlgorithmParameterException ignored) {
            logger.error("The IV parameter specification is invalid");
        } catch (InvalidKeyException ignored) {
            logger.error("The shared secret provided is invalid");
        } catch (IllegalArgumentException ignored) {
        }
        return "";
    }
    
    /**
     * Encrypts a message with a shared secret using the AES algorithm.
     *
     * @param message The message to encrypt.
     * @param secret  The shared secret to encrypt the message with.
     * @return The encrypted message.
     * @see #encryptAES(String, SecretKey, String, String, String)
     */
    public static String encryptAES(String message, SecretKey secret) {
        return encryptAES(message, secret, CBC_MODE, PKCS5_PADDING, UTF8_ENCODING);
    }
    
    /**
     * Encrypts a message with a password using the AES algorithm.
     *
     * @param message  The message to encrypt.
     * @param password The password to encrypt the message with.
     * @return The encrypted message.
     */
    public static String encryptWithPassword(String message, String password) {
        password = (password.length() > AES_PASSWORD_LENGTH) ? StringUtility.lSnip(password, AES_PASSWORD_LENGTH) : StringUtility.padRight(password, AES_PASSWORD_LENGTH);
        SecretKey key = new SecretKeySpec(password.getBytes(), AES_ALGORITHM);
        return encryptAES(message, key);
    }
    
    /**
     * Decrypts a base64 encrypted message with a private key.
     *
     * @param message    The message to decrypt.
     * @param privateKey The private key to decrypt the message with.
     * @param algorithm  The algorithm to use to decrypt with.
     * @param blockMode  The block mode to use.
     * @param padding    The padding method to use.
     * @return The decrypted message.
     */
    public static String decrypt(String message, PrivateKey privateKey, String algorithm, String blockMode, String padding) {
        try {
            String cipherMode = algorithm;
            if (!blockMode.isEmpty() && !padding.isEmpty()) {
                cipherMode += '/' + blockMode + '/' + padding;
            }
            
            Cipher cipher = Cipher.getInstance(cipherMode);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            
            byte[] data = Base64.getDecoder().decode(message);
            if (algorithm.equals(RSA_ALGORITHM) && (data.length > RSA_MAX_DATA_LENGTH)) {
                logger.error("RSA cannot decrypt data longer than {} bytes", RSA_MAX_DATA_LENGTH);
                return "";
            }
            
            return new String(cipher.doFinal(data));
            
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", algorithm);
        } catch (IllegalBlockSizeException ignored) {
            //logger.error("The block mode: {} can not be used with the algorithm: {}", blockMode, algorithm);
        } catch (NoSuchPaddingException ignored) {
            logger.error("The padding method: {} does not exist", padding);
        } catch (BadPaddingException ignored) {
            //logger.error("The padding method: {} can not be used with the algorithm: {} using the block mode: {}", padding, algorithm, blockMode);
        } catch (InvalidKeyException ignored) {
            logger.error("The public key provided is invalid");
        } catch (IllegalArgumentException ignored) {
        }
        return "";
    }
    
    /**
     * Decrypts a message with a private key using the RSA algorithm.
     *
     * @param message    The message to decrypt.
     * @param privateKey The private key to decrypt the message with.
     * @return The decrypted message.
     * @see #decrypt(String, PrivateKey, String, String, String)
     */
    public static String decryptRSA(String message, PrivateKey privateKey) {
        return decrypt(message, privateKey, RSA_ALGORITHM, "", "");
    }
    
    /**
     * Decrypts a base64 encrypted message with a shared secret using the AES algorithm.
     *
     * @param message   The message to decrypt.
     * @param secret    The shared secret to decrypt the message with.
     * @param blockMode The block mode to use.
     * @param padding   The padding method to use.
     * @return The decrypted message.
     */
    public static String decryptAES(String message, SecretKey secret, String blockMode, String padding) {
        String algorithm = AES_ALGORITHM;
        
        try {
            String cipherMode = algorithm;
            if (!blockMode.isEmpty() && !padding.isEmpty()) {
                cipherMode += '/' + blockMode + '/' + padding;
            }
            
            Cipher cipher = Cipher.getInstance(cipherMode);
            IvParameterSpec iv = new IvParameterSpec(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            cipher.init(Cipher.DECRYPT_MODE, secret, iv);
            
            byte[] data = Base64.getDecoder().decode(message);
            return new String(cipher.doFinal(data));
            
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", algorithm);
        } catch (IllegalBlockSizeException ignored) {
            //logger.error("The block mode: {} can not be used with the algorithm: {}", blockMode, algorithm);
        } catch (NoSuchPaddingException ignored) {
            logger.error("The padding method: {} does not exist", padding);
        } catch (BadPaddingException ignored) {
            //logger.error("The padding method: {} can not be used with the algorithm: {} using the block mode: {}", padding, algorithm, blockMode);
        } catch (InvalidAlgorithmParameterException ignored) {
            logger.error("The IV parameter specification is invalid");
        } catch (InvalidKeyException ignored) {
            logger.error("The shared secret provided is invalid");
        } catch (IllegalArgumentException ignored) {
        }
        return "";
    }
    
    /**
     * Decrypts a message with a shared secret using the AES algorithm.
     *
     * @param message The message to decrypt.
     * @param secret  The shared secret to decrypt the message with.
     * @return The decrypted message.
     * @see #decryptAES(String, SecretKey, String, String)
     */
    public static String decryptAES(String message, SecretKey secret) {
        return decryptAES(message, secret, CBC_MODE, PKCS5_PADDING);
    }
    
    /**
     * Decrypts a message with a password using the AES algorithm.
     *
     * @param message  The message to decrypt.
     * @param password The password to decrypt the message with.
     * @return The decrypted message.
     */
    public static String decryptWithPassword(String message, String password) {
        password = (password.length() > AES_PASSWORD_LENGTH) ? StringUtility.lSnip(password, AES_PASSWORD_LENGTH) : StringUtility.padRight(password, AES_PASSWORD_LENGTH);
        SecretKey key = new SecretKeySpec(password.getBytes(), AES_ALGORITHM);
        return decryptAES(message, key);
    }
    
    /**
     * Signs a message with a private key.
     *
     * @param message    The message to sign.
     * @param privateKey The private key to sign the message with.
     * @param algorithm  The algorithm to use to sign with.
     * @param encoding   The encoding of the message.
     * @return The generated signature for the message.
     */
    public static String sign(String message, PrivateKey privateKey, String algorithm, String encoding) {
        try {
            byte[] data = message.getBytes(encoding);
            
            Signature sig = Signature.getInstance(algorithm);
            sig.initSign(privateKey);
            sig.update(data);
            
            byte[] signature = sig.sign();
            return Base64.getEncoder().encodeToString(signature);
            
        } catch (UnsupportedEncodingException ignored) {
            logger.error("The encoding: {} is not supported", encoding);
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", algorithm);
        } catch (InvalidKeyException ignored) {
            logger.error("The private key provided is invalid");
        } catch (SignatureException ignored) {
            logger.error("There was an error while signing the message");
        } catch (IllegalArgumentException ignored) {
        }
        return "";
    }
    
    /**
     * Signs a message with a private key using the SHA/DSA algorithm.
     *
     * @param message    The message to sign.
     * @param privateKey The private key to sign the message with.
     * @return The generated signature for the message.
     * @see #sign(String, PrivateKey, String, String)
     */
    public static String signDSA(String message, PrivateKey privateKey) {
        return sign(message, privateKey, SHADSA_ALGORITHM, UTF8_ENCODING);
    }
    
    /**
     * Signs a message with a private key using the RSA algorithm.
     *
     * @param message    The message to sign.
     * @param privateKey The private key to sign the message with.
     * @return The generated signature for the message.
     * @see #sign(String, PrivateKey, String, String)
     */
    public static String signRSA(String message, PrivateKey privateKey) {
        return sign(message, privateKey, SHA256RSA_ALGORITHM, UTF8_ENCODING);
    }
    
    /**
     * Verifies a signature of a message with a public key.
     *
     * @param message   The message that was signed.
     * @param signature The signature to verify.
     * @param publicKey The public key to verify the message with.
     * @param algorithm The algorithm to use to verify with.
     * @param encoding  The encoding of the message.
     * @return Whether the signature was verified or not.
     */
    public static boolean verify(String message, String signature, PublicKey publicKey, String algorithm, String encoding) {
        try {
            byte[] data = message.getBytes(encoding);
            
            Signature sig = Signature.getInstance(algorithm);
            sig.initVerify(publicKey);
            sig.update(data);
            
            byte[] sigBytes = Base64.getDecoder().decode(signature);
            return sig.verify(sigBytes);
            
        } catch (UnsupportedEncodingException ignored) {
            logger.error("The encoding: {} is not supported", encoding);
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", algorithm);
        } catch (InvalidKeyException ignored) {
            logger.error("The public key provided is invalid");
        } catch (SignatureException ignored) {
            logger.error("There was an error while verifying the signature");
        } catch (IllegalArgumentException ignored) {
        }
        return false;
    }
    
    /**
     * Verifies a signature of a message with a public key using the SHA/DSA algorithm.
     *
     * @param message   The message that was signed.
     * @param signature The signature to verify.
     * @param publicKey The public key to verify the message with.
     * @return Whether the signature was verified or not.
     * @see #verify(String, String, PublicKey, String, String)
     */
    public static boolean verifyDSA(String message, String signature, PublicKey publicKey) {
        return verify(message, signature, publicKey, SHADSA_ALGORITHM, UTF8_ENCODING);
    }
    
    /**
     * Verifies a signature of a message with a public key using the SHA/RSA algorithm.
     *
     * @param message   The message that was signed.
     * @param signature The signature to verify.
     * @param publicKey The public key to verify the message with.
     * @return Whether the signature was verified or not.
     * @see #verify(String, String, PublicKey, String, String)
     */
    public static boolean verifyRSA(String message, String signature, PublicKey publicKey) {
        return verify(message, signature, publicKey, SHA256RSA_ALGORITHM, UTF8_ENCODING);
    }
    
    /**
     * Produces a hash of a message as a hexadecimal string.
     *
     * @param message   The message to hash.
     * @param algorithm The algorithm to use to hash with.
     * @param encoding  The encoding of the message.
     * @return The hash of the message as a hexadecimal string.
     */
    public static String hash(String message, String algorithm, String encoding) {
        try {
            byte[] data = message.getBytes(encoding);
            
            MessageDigest digester = MessageDigest.getInstance(algorithm);
            digester.update(data);
            
            byte[] digest = digester.digest();
            return String.format("%064x", new BigInteger(1, digest));
            
        } catch (UnsupportedEncodingException ignored) {
            logger.error("The encoding: {} is not supported", encoding);
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", algorithm);
        }
        return "";
    }
    
    /**
     * Produces a hash of a message as a hexadecimal string using the SHA-256 algorithm.
     *
     * @param message The message to hash.
     * @param iterate Whether or not to iterate the hash multiple times.
     * @return The hash of the message as a hexadecimal string.
     * @see #hash(String, String, String)
     */
    public static String hashSHA256(String message, boolean iterate) {
        String hash = message;
        for (int i = 0; i < (iterate ? HASH_ITERATIONS : 1); i++) {
            hash = hash(hash, SHA256_ALGORITHM, UTF8_ENCODING);
        }
        return hash;
    }
    
    /**
     * Produces a hash of a message as a hexadecimal string using the SHA-256 algorithm.
     *
     * @param message The message to hash.
     * @return The hash of the message as a hexadecimal string.
     * @see #hashSHA256(String, boolean)
     */
    public static String hashSHA256(String message) {
        return hashSHA256(message, false);
    }
    
    /**
     * Produces a hash of a message as a hexadecimal string using the SHA-512 algorithm.
     *
     * @param message The message to hash.
     * @param iterate Whether or not to iterate the hash multiple times.
     * @return The hash of the message as a hexadecimal string.
     * @see #hash(String, String, String)
     */
    public static String hashSHA512(String message, boolean iterate) {
        String hash = message;
        for (int i = 0; i < (iterate ? HASH_ITERATIONS : 1); i++) {
            hash = hash(hash, SHA512_ALGORITHM, UTF8_ENCODING);
        }
        return hash;
    }
    
    /**
     * Produces a hash of a message as a hexadecimal string using the SHA-512 algorithm.
     *
     * @param message The message to hash.
     * @return The hash of the message as a hexadecimal string.
     * @see #hashSHA512(String, boolean)
     */
    public static String hashSHA512(String message) {
        return hashSHA512(message, false);
    }
    
    /**
     * Calculates a checksum for a file as a hexadecimal string.
     *
     * @param file      The file to calculate the checksum for.
     * @param algorithm The algorithm to use to calculate the checksum with.
     * @return The checksum of the file as a hexadecimal string.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    public static String checksum(String file, String algorithm) {
        try {
            MessageDigest digester = MessageDigest.getInstance(algorithm);
            File f = new File(file);
            Vector<FileInputStream> fileStreams = new Vector<>();
            if (f.isDirectory()) {
                Filesystem.getFilesRecursively(f).forEach(e -> {
                    try {
                        fileStreams.add(new FileInputStream(e));
                    } catch (FileNotFoundException ignored) {
                    }
                });
            }
            
            try (InputStream is = f.isFile() ? Files.newInputStream(Paths.get(file)) : (f.isDirectory() ? new SequenceInputStream(fileStreams.elements()) : null);
                 DigestInputStream dis = new DigestInputStream(is, digester)) {
                while (dis.read() != -1) {
                }
            } catch (Exception ignored) {
                logger.error("There was an error hashing the file");
                return "";
            } finally {
                for (FileInputStream fis : fileStreams) {
                    if (fis != null) {
                        fis.close();
                    }
                }
            }
            
            byte[] digest = digester.digest();
            return String.format("%064x", new BigInteger(1, digest));
            
        } catch (IOException e) {
            logger.error("There was an error opening an input stream to the file");
        } catch (NoSuchAlgorithmException ignored) {
            logger.error("The algorithm: {} does not exist", algorithm);
        }
        return "";
    }
    
    /**
     * Calculates a checksum for a file as a hexadecimal string.
     *
     * @param file The file to calculate the checksum for.
     * @return The checksum of the file as a hexadecimal string.
     * @see #checksum(String, String)
     */
    public static String checksumMD5(String file) {
        return checksum(file, MD5_ALGORITHM);
    }
    
    /**
     * Stores a DSA private key in a string.
     *
     * @param key The DSA private key.
     * @return A string representing the DSA private key.
     */
    public static String storeDSAPrivateKey(PrivateKey key) {
        try {
            KeyFactory factory = KeyFactory.getInstance(DSA_ALGORITHM);
            DSAPrivateKeySpec privateKey = factory.getKeySpec(key, DSAPrivateKeySpec.class);
            
            BigInteger x = privateKey.getX();
            BigInteger p = privateKey.getP();
            BigInteger q = privateKey.getQ();
            BigInteger g = privateKey.getG();
            
            return String.valueOf(x) + ':' + p + ':' + q + ':' + g;
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ignored) {
            return "";
        }
    }
    
    /**
     * Stores a DSA public key in a string.
     *
     * @param key The DSA public key.
     * @return A string representing the DSA public key.
     */
    public static String storeDSAPublicKey(PublicKey key) {
        try {
            KeyFactory factory = KeyFactory.getInstance(DSA_ALGORITHM);
            DSAPublicKeySpec pub = factory.getKeySpec(key, DSAPublicKeySpec.class);
            
            BigInteger y = pub.getY();
            BigInteger p = pub.getP();
            BigInteger q = pub.getQ();
            BigInteger g = pub.getG();
            
            return String.valueOf(y) + ':' + p + ':' + q + ':' + g;
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ignored) {
            return "";
        }
    }
    
    /**
     * Read a DSA private key from a string store.
     *
     * @param store The DSA private key store.
     * @return The DSA private key.
     */
    public static PrivateKey readDSAPrivateKey(String store) {
        try {
            List<String> keyParts = StringUtility.tokenize(store, ":");
            
            BigInteger x = new BigInteger(keyParts.get(0));
            BigInteger p = new BigInteger(keyParts.get(1));
            BigInteger q = new BigInteger(keyParts.get(2));
            BigInteger g = new BigInteger(keyParts.get(3));
            
            DSAPrivateKeySpec privateKey = new DSAPrivateKeySpec(x, p, q, g);
            KeyFactory factory = KeyFactory.getInstance(DSA_ALGORITHM);
            
            return factory.generatePrivate(privateKey);
            
        } catch (NumberFormatException | NoSuchAlgorithmException | InvalidKeySpecException ignored) {
            return null;
        }
    }
    
    /**
     * Read a DSA public key from a string store.
     *
     * @param store The DSA public key store.
     * @return The DSA public key.
     */
    public static PublicKey readDSAPublicKey(String store) {
        try {
            List<String> keyParts = StringUtility.tokenize(store, ":");
            
            BigInteger y = new BigInteger(keyParts.get(0));
            BigInteger p = new BigInteger(keyParts.get(1));
            BigInteger q = new BigInteger(keyParts.get(2));
            BigInteger g = new BigInteger(keyParts.get(3));
            
            DSAPublicKeySpec pub = new DSAPublicKeySpec(y, p, q, g);
            KeyFactory factory = KeyFactory.getInstance(DSA_ALGORITHM);
            
            return factory.generatePublic(pub);
            
        } catch (NumberFormatException | NoSuchAlgorithmException | InvalidKeySpecException ignored) {
            return null;
        }
    }
    
    /**
     * Stores an RSA private key in a string.
     *
     * @param key The RSA private key.
     * @return A string representing the RSA private key.
     */
    public static String storeRSAPrivateKey(PrivateKey key) {
        try {
            KeyFactory factory = KeyFactory.getInstance(RSA_ALGORITHM);
            RSAPrivateKeySpec privateKey = factory.getKeySpec(key, RSAPrivateKeySpec.class);
            
            BigInteger modulus = privateKey.getModulus();
            BigInteger exponent = privateKey.getPrivateExponent();
            
            return String.valueOf(modulus) + ':' + exponent;
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ignored) {
            return "";
        }
    }
    
    /**
     * Stores an RSA public key in a string.
     *
     * @param key The RSA public key.
     * @return A string representing the RSA public key.
     */
    public static String storeRSAPublicKey(PublicKey key) {
        try {
            KeyFactory factory = KeyFactory.getInstance(RSA_ALGORITHM);
            RSAPublicKeySpec pub = factory.getKeySpec(key, RSAPublicKeySpec.class);
            
            BigInteger modulus = pub.getModulus();
            BigInteger exponent = pub.getPublicExponent();
            
            return String.valueOf(modulus) + ':' + exponent;
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ignored) {
            return "";
        }
    }
    
    /**
     * Read an RSA private key from a string store.
     *
     * @param store The RSA private key store.
     * @return The RSA private key.
     */
    public static PrivateKey readRSAPrivateKey(String store) {
        try {
            List<String> keyParts = StringUtility.tokenize(store, ":");
            
            BigInteger modulus = new BigInteger(keyParts.get(0));
            BigInteger exponent = new BigInteger(keyParts.get(1));
            
            RSAPrivateKeySpec privateKey = new RSAPrivateKeySpec(modulus, exponent);
            KeyFactory factory = KeyFactory.getInstance(RSA_ALGORITHM);
            
            return factory.generatePrivate(privateKey);
            
        } catch (NumberFormatException | NoSuchAlgorithmException | InvalidKeySpecException ignored) {
            return null;
        }
    }
    
    /**
     * Read an RSA public key from a string store.
     *
     * @param store The RSA public key store.
     * @return The RSA public key.
     */
    public static PublicKey readRSAPublicKey(String store) {
        try {
            List<String> keyParts = StringUtility.tokenize(store, ":");
            
            BigInteger modulus = new BigInteger(keyParts.get(0));
            BigInteger exponent = new BigInteger(keyParts.get(1));
            
            RSAPublicKeySpec pub = new RSAPublicKeySpec(modulus, exponent);
            KeyFactory factory = KeyFactory.getInstance(RSA_ALGORITHM);
            
            return factory.generatePublic(pub);
            
        } catch (NumberFormatException | NoSuchAlgorithmException | InvalidKeySpecException ignored) {
            return null;
        }
    }
    
    /**
     * Stores an AES shared secret in a string.
     *
     * @param key The AES shared secret.
     * @return A string representing the AES shared secret.
     */
    public static String storeAESSecret(SecretKey key) {
        return Base64.getEncoder().withoutPadding().encodeToString(key.getEncoded());
    }
    
    /**
     * Read an AES shared secret from a string store.
     *
     * @param store The AES shared secret store.
     * @return The AES shared secret.
     */
    public static SecretKey readAESSecret(String store) {
        byte[] key = Base64.getDecoder().decode(store);
        return new SecretKeySpec(key, AES_ALGORITHM);
    }
    
}
