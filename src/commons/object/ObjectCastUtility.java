/*
 * File:    ObjectCastUtility.java
 * Package: commons.object
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

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
     * Casts and Object to a Boolean.
     *
     * @param o The Object.
     * @return The Boolean cast from the Object, or null if it cannot be cast.
     */
    public static Boolean toBoolean(Object o) {
        try {
            return Boolean.parseBoolean(o.toString());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to a Byte.
     *
     * @param o The Object.
     * @return The Byte cast from the Object, or null if it cannot be cast.
     */
    public static Byte toByte(Object o) {
        try {
            return Byte.parseByte(o.toString());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to a Short.
     *
     * @param o The Object.
     * @return The Short cast from the Object, or null if it cannot be cast.
     */
    public static Short toShort(Object o) {
        try {
            return Short.parseShort(o.toString());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to an Integer.
     *
     * @param o The Object.
     * @return The Integer cast from the Object, or null if it cannot be cast.
     */
    public static Integer toInt(Object o) {
        try {
            return Integer.parseInt(o.toString());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to a Long.
     *
     * @param o The Object.
     * @return The Long cast from the Object, or null if it cannot be cast.
     */
    public static Long toLong(Object o) {
        try {
            return Long.parseLong(o.toString());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to a Float.
     *
     * @param o The Object.
     * @return The Float cast from the Object, or null if it cannot be cast.
     */
    public static Float toFloat(Object o) {
        try {
            return Float.parseFloat(o.toString());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to a Double.
     *
     * @param o The Object.
     * @return The Double cast from the Object, or null if it cannot be cast.
     */
    public static Double toDouble(Object o) {
        try {
            return Double.parseDouble(o.toString());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to a BigInteger.
     *
     * @param o The Object.
     * @return The BigInteger cast from the Object, or null if it cannot be cast.
     */
    public static BigInteger toBigInteger(Object o) {
        try {
            return new BigInteger(o.toString());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to a BigDecimal.
     *
     * @param o The Object.
     * @return The BigDecimal cast from the Object, or null if it cannot be cast.
     */
    public static BigDecimal toBigDecimal(Object o) {
        try {
            return new BigDecimal(o.toString());
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to a Character.
     *
     * @param o The Object.
     * @return The Character cast from the Object, or null if it cannot be cast.
     */
    public static Character toChar(Object o) {
        try {
            return (o.toString().length() == 1) ? o.toString().charAt(0) : null;
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts and Object to a String.
     *
     * @param o The Object.
     * @return The String cast from the Object, or null if it cannot be cast.
     */
    public static String toString(Object o) {
        try {
            return String.valueOf(o);
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts a Collection to a Collection subclass.
     *
     * @param o         The Collection.
     * @param type      The subclass to cast the Collection to.
     * @param valueType The value type of the Collection.
     * @param <T>       The value type of the Collection.
     * @param <C>       The class of the Collection
     * @param <R>       The subclass to cast the Collection to.
     * @return The Collection subclass cast from the Collection, or null if it cannot be cast.
     */
    public static <T, C extends Collection<T>, R extends Collection<T>> R toCollectionType(C o, Class<R> type, Class<T> valueType) {
        try {
            return type.equals(o.getClass()) ? type.cast(o) : type.getConstructor(Collection.class).newInstance(o);
        } catch (Exception ignored) {
        }
        return null;
    }
    
    /**
     * Casts a Map to a Map subclass.
     *
     * @param o         The Map.
     * @param type      The subclass to cast the Map to.
     * @param keyType   The key type of the Map.
     * @param valueType The value type of the Map.
     * @param <T>       The key type of the Map.
     * @param <S>       The value type of the Map.
     * @param <M>       The class of the Map.
     * @param <R>       The subclass to cast the Map to.
     * @return The Map subclass cast from the Map, or null if it cannot be cast.
     */
    public static <T, S, M extends Map<T, S>, R extends Map<T, S>> R toMapType(M o, Class<R> type, Class<T> keyType, Class<S> valueType) {
        try {
            return type.equals(o.getClass()) ? type.cast(o) : type.getConstructor(Map.class).newInstance(o);
        } catch (Exception ignored) {
        }
        return null;
    }
    
}
