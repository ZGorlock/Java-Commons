/*
 * File:    Sorters.java
 * Package: commons.lambda.stream.sorter
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.sorter;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Map;

import commons.math.MathUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides access to custom stream sorters.
 */
public final class Sorters {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Sorters.class);
    
    
    //Static Methods
    
    /**
     * Creates a new custom sorter that sorts a stream.
     *
     * @param <T> The type of the elements in the stream.
     * @return The custom sorter.
     */
    public static <T extends Comparable<T>> Comparator<T> sort() {
        return (o1, o2) -> ((o1 != null) && o2 != null) ? o1.compareTo(o2) :
                           ((o1 == null) && (o2 == null)) ? 0 :
                           ((o1 == null) ? -1 : 1);
    }
    
    /**
     * Creates a new custom sorter that preserves a stream.<br/>
     * This method will not correctly preserve order when the stream contains non-distinct objects.
     *
     * @param <T> The type of the elements in the stream.
     * @return The custom sorter.
     */
    public static <T> Comparator<T> preserve() {
        final Map<T, Integer> uniqueIds = new IdentityHashMap<>();
        return Comparator.comparingInt(o ->
                uniqueIds.computeIfAbsent(o, (k -> uniqueIds.size() * ((uniqueIds.size() < 2) ? -1 : 1))));
    }
    
    /**
     * Creates a new custom sorter that reverses a stream.<br/>
     * This method will not correctly reverse order when the stream contains non-distinct objects.
     *
     * @param <T> The type of the elements in the stream.
     * @return The custom sorter.
     */
    public static <T> Comparator<T> reverse() {
        final Map<T, Integer> uniqueIds = new IdentityHashMap<>();
        return Comparator.comparingInt(o ->
                uniqueIds.computeIfAbsent(o, (k -> -uniqueIds.size() * ((uniqueIds.size() < 2) ? -1 : 1))));
    }
    
    /**
     * Creates a new custom sorter that shuffles a stream.
     *
     * @param <T> The type of the elements in the stream.
     * @return The custom sorter.
     */
    public static <T> Comparator<T> shuffle() {
        final Map<T, Integer> uniqueIds = new IdentityHashMap<>();
        return Comparator.comparingInt(o ->
                uniqueIds.computeIfAbsent(o, (k -> MathUtility.randomInt())));
    }
    
}
