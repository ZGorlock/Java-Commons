/*
 * File:    BoundUtility.java
 * Package: commons.math
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides additional bound functionality.
 */
public final class BoundUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BoundUtility.class);
    
    
    //Static Methods
    
    /**
     * Determines if a number is in defined bounds.
     *
     * @param num        The number to test.
     * @param lower      The lower bound.
     * @param upper      The upper bound.
     * @param touchLower Whether the number can be equal to the lower bound or not.
     * @param touchUpper Whether the number can be equal to the upper bound or not.
     * @return Whether the number is in the defined bounds or not.
     */
    public static boolean inBounds(Number num, Number lower, Number upper, boolean touchLower, boolean touchUpper) {
        double n = num.doubleValue();
        double min = lower.doubleValue();
        double max = upper.doubleValue();
        
        boolean inLower = touchLower ? (n >= min) : (n > min);
        boolean inUpper = touchUpper ? (n <= max) : (n < max);
        return inLower && inUpper;
    }
    
    /**
     * Determines if a number is in defined bounds.<br>
     * The number can be in between or equal to these bounds. (lower &lt;= num &lt;= upper)<br>
     * To test bounds for other cases see inBounds(Number, Number, Number, boolean, boolean).
     *
     * @param num   The number to test.
     * @param lower The lower bound.
     * @param upper The upper bound.
     * @return Whether the number is in the defined bounds or not.
     * @see #inBounds(Number, Number, Number, boolean, boolean)
     */
    public static boolean inBounds(Number num, Number lower, Number upper) {
        return inBounds(num, lower, upper, true, true);
    }
    
    /**
     * Determines if an index is in the bounds of a collection with a specific length.
     *
     * @param index  The index to test.
     * @param length The length of the set.
     * @return Whether the index is in the bounds of the collection or not.
     * @see #inBounds(Number, Number, Number)
     */
    public static <T> boolean indexInBounds(int index, int length) {
        return inBounds((index + 1), 1, length);
    }
    
    /**
     * Determines if an index is in the bounds of an array.
     *
     * @param index The index to test.
     * @param array The array.
     * @param <T>   The type of the array.
     * @return Whether the index is in the bounds of the array or not.
     * @see #indexInBounds(int, int)
     */
    public static <T> boolean inArrayBounds(int index, T[] array) {
        return indexInBounds(index, array.length);
    }
    
    /**
     * Determines if the index is in the bounds of a list.
     *
     * @param index The index to test.
     * @param list  The list.
     * @param <T>   The type of the list.
     * @return Whether the index is in the bounds of the list or not.
     * @see #indexInBounds(int, int)
     */
    public static <T> boolean inListBounds(int index, List<T> list) {
        return indexInBounds(index, list.size());
    }
    
    /**
     * Forces a number within defined bounds.
     *
     * @param num The number value.
     * @param min The minimum value allowed.
     * @param max The maximum value allowed.
     * @return The truncated number.
     */
    public static Number truncateNum(Number num, Number min, Number max) {
        Number n = num;
        if (num.doubleValue() < min.doubleValue()) {
            n = min;
        }
        if (num.doubleValue() > max.doubleValue()) {
            n = max;
        }
        return n;
    }
    
}
