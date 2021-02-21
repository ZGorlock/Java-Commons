/*
 * File:    ObjectCastUtility.java
 * Package: commons.object
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides additional object casting functionality.
 */
public final class ObjectCastUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ObjectCastUtility.class);
    
    
    //Functions
    
    /**
     * Casts and object to a boolean.
     *
     * @param o The object.
     * @return The boolean cast from the object, or null if it cannot be cast.
     */
    public static Boolean toBoolean(Object o) {
        try {
            return Boolean.parseBoolean(o.toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to a byte.
     *
     * @param o The object.
     * @return The byte cast from the object, or null if it cannot be cast.
     */
    public static Byte toByte(Object o) {
        try {
            return Byte.parseByte(o.toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to a short.
     *
     * @param o The object.
     * @return The short cast from the object, or null if it cannot be cast.
     */
    public static Short toShort(Object o) {
        try {
            return Short.parseShort(o.toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to an int.
     *
     * @param o The object.
     * @return The int cast from the object, or null if it cannot be cast.
     */
    public static Integer toInt(Object o) {
        try {
            return Integer.parseInt(o.toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to a long.
     *
     * @param o The object.
     * @return The long cast from the object, or null if it cannot be cast.
     */
    public static Long toLong(Object o) {
        try {
            return Long.parseLong(o.toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to a float.
     *
     * @param o The object.
     * @return The float cast from the object, or null if it cannot be cast.
     */
    public static Float toFloat(Object o) {
        try {
            return Float.parseFloat(o.toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to a double.
     *
     * @param o The object.
     * @return The double cast from the object, or null if it cannot be cast.
     */
    public static Double toDouble(Object o) {
        try {
            return Double.parseDouble(o.toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to a big integer.
     *
     * @param o The object.
     * @return The big integer cast from the object, or null if it cannot be cast.
     */
    public static BigInteger toBigInteger(Object o) {
        try {
            return new BigInteger(o.toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to a big decimal.
     *
     * @param o The object.
     * @return The big decimal cast from the object, or null if it cannot be cast.
     */
    public static BigDecimal toBigDecimal(Object o) {
        try {
            return new BigDecimal(o.toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to a char.
     *
     * @param o The object.
     * @return The char cast from the object, or null if it cannot be cast.
     */
    public static Character toChar(Object o) {
        try {
            if (o.toString().length() > 1) {
                return null;
            }
            return o.toString().charAt(0);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Casts and object to a string.
     *
     * @param o The object.
     * @return The string cast from the object, or null if it cannot be cast.
     */
    public static String toString(Object o) {
        try {
            return String.valueOf(o);
        } catch (Exception e) {
            return null;
        }
    }
    
}
