/*
 * File:    ArrayCollectors.java
 * Package: commons.lambda.stream.collector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.collector;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import commons.object.collection.ArrayUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides access to custom stream collectors for collecting to arrays.
 */
public final class ArrayCollectors {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ArrayCollectors.class);
    
    
    //Static Methods
    
    /**
     * Creates a new custom collector that collects a stream to an array.
     *
     * @param arraySupplier The supplier that provides an array of a certain type.
     * @param mapper        The function that produces the elements of the array.
     * @param <T>           The type of the elements of the stream.
     * @param <U>           The type of the array.
     * @return The custom collector.
     * @see ListCollectors#toArrayList(Function)
     * @see List#toArray(IntFunction)
     */
    public static <T, U> Collector<T, ?, U[]> toArray(IntFunction<U[]> arraySupplier, Function<? super T, ? extends U> mapper) {
        return Collectors.collectingAndThen(
                ListCollectors.toArrayList(mapper),
                list -> list.toArray(arraySupplier));
    }
    
    /**
     * Creates a new custom collector that collects a stream to an array.
     *
     * @param arrayType The type of the array.
     * @param mapper    The function that produces the elements of the array.
     * @param <T>       The type of the elements of the stream.
     * @param <U>       The type of the array.
     * @return The custom collector.
     * @see #toArray(IntFunction, Function)
     */
    public static <T, U> Collector<T, ?, U[]> toArray(Class<U> arrayType, Function<? super T, ? extends U> mapper) {
        return toArray(generator(arrayType), mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to an array.
     *
     * @param arraySupplier The supplier that provides an array of a certain type.
     * @param <T>           The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(IntFunction, Function)
     */
    public static <T> Collector<T, ?, T[]> toArray(IntFunction<T[]> arraySupplier) {
        return toArray(arraySupplier, Function.identity());
    }
    
    /**
     * Creates a new custom collector that collects a stream to an array.
     *
     * @param arrayType The type of the array.
     * @param <T>       The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(IntFunction)
     */
    public static <T> Collector<T, ?, T[]> toArray(Class<T> arrayType) {
        return toArray(generator(arrayType));
    }
    
    /**
     * Creates a new custom collector that collects a stream to an array.
     *
     * @return The custom collector.
     * @see #toArray(IntFunction, Function)
     */
    public static <T> Collector<T, ?, Object[]> toArray(Function<? super T, ?> mapper) {
        return toArray(generator(), mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to an array.
     *
     * @return The custom collector.
     * @see #toArray(IntFunction)
     */
    public static Collector<?, ?, Object[]> toArray() {
        return toArray(generator());
    }
    
    /**
     * Creates an int function that produces an array of a certain type.
     *
     * @param type The type of the array.
     * @param <T>  The type of the array.
     * @return The array supplier.
     * @see ArrayUtility#create(Class, int)
     */
    public static <T> IntFunction<T[]> generator(Class<T> type) {
        return (i -> ArrayUtility.create(type, i));
    }
    
    /**
     * Creates an int function that produces an array.
     *
     * @return The array supplier.
     * @see #generator(Class)
     */
    public static IntFunction<Object[]> generator() {
        return generator(Object.class);
    }
    
    /**
     * Creates an int function that produces a 2D array of a certain type.
     *
     * @param type The type of the array.
     * @param <T>  The type of the array.
     * @return The 2D array supplier.
     * @see #generator(Class)
     */
    @SuppressWarnings("unchecked")
    public static <T> IntFunction<T[][]> generator2D(Class<T> type) {
        return generator((Class<T[]>) type.arrayType());
    }
    
    /**
     * Creates an int function that produces a 2D array.
     *
     * @return The 2D array supplier.
     * @see #generator2D(Class)
     */
    public static IntFunction<Object[][]> generator2D() {
        return generator2D(Object.class);
    }
    
    /**
     * Creates an int function that produces a 3D array of a certain type.
     *
     * @param type The type of the array.
     * @param <T>  The type of the array.
     * @return The 3D array supplier.
     * @see #generator2D(Class)
     */
    @SuppressWarnings("unchecked")
    public static <T> IntFunction<T[][][]> generator3D(Class<T> type) {
        return generator2D((Class<T[]>) type.arrayType());
    }
    
    /**
     * Creates an int function that produces a 3D array.
     *
     * @return The 3D array supplier.
     * @see #generator3D(Class)
     */
    public static IntFunction<Object[][][]> generator3D() {
        return generator3D(Object.class);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a byte array.
     *
     * @param mapper The function that produces the elements of the array.
     * @param <T>    The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(Class, Function)
     */
    public static <T> Collector<T, ?, Byte[]> toByteArray(Function<? super T, ? extends Byte> mapper) {
        return toArray(Byte.class, mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a byte array.
     *
     * @return The custom collector.
     * @see #toArray(Class)
     */
    public static Collector<Byte, ?, Byte[]> toByteArray() {
        return toArray(Byte.class);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a short array.
     *
     * @param mapper The function that produces the elements of the array.
     * @param <T>    The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(Class, Function)
     */
    public static <T> Collector<T, ?, Short[]> toShortArray(Function<? super T, ? extends Short> mapper) {
        return toArray(Short.class, mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a byte array.
     *
     * @return The custom collector.
     * @see #toArray(Class)
     */
    public static Collector<Short, ?, Short[]> toShortArray() {
        return toArray(Short.class);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a int array.
     *
     * @param mapper The function that produces the elements of the array.
     * @param <T>    The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(Class, Function)
     */
    public static <T> Collector<T, ?, Integer[]> toIntArray(Function<? super T, ? extends Integer> mapper) {
        return toArray(Integer.class, mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a int array.
     *
     * @return The custom collector.
     * @see #toArray(Class)
     */
    public static Collector<Integer, ?, Integer[]> toIntArray() {
        return toArray(Integer.class);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a long array.
     *
     * @param mapper The function that produces the elements of the array.
     * @param <T>    The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(Class, Function)
     */
    public static <T> Collector<T, ?, Long[]> toLongArray(Function<? super T, ? extends Long> mapper) {
        return toArray(Long.class, mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a long array.
     *
     * @return The custom collector.
     * @see #toArray(Class)
     */
    public static Collector<Long, ?, Long[]> toLongArray() {
        return toArray(Long.class);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a float array.
     *
     * @param mapper The function that produces the elements of the array.
     * @param <T>    The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(Class, Function)
     */
    public static <T> Collector<T, ?, Float[]> toFloatArray(Function<? super T, ? extends Float> mapper) {
        return toArray(Float.class, mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a float array.
     *
     * @return The custom collector.
     * @see #toArray(Class)
     */
    public static Collector<Float, ?, Float[]> toFloatArray() {
        return toArray(Float.class);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a double array.
     *
     * @param mapper The function that produces the elements of the array.
     * @param <T>    The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(Class, Function)
     */
    public static <T> Collector<T, ?, Double[]> toDoubleArray(Function<? super T, ? extends Double> mapper) {
        return toArray(Double.class, mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a double array.
     *
     * @return The custom collector.
     * @see #toArray(Class)
     */
    public static Collector<Double, ?, Double[]> toDoubleArray() {
        return toArray(Double.class);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a char array.
     *
     * @param mapper The function that produces the elements of the array.
     * @param <T>    The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(Class, Function)
     */
    public static <T> Collector<T, ?, Character[]> toCharArray(Function<? super T, ? extends Character> mapper) {
        return toArray(Character.class, mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a char array.
     *
     * @return The custom collector.
     * @see #toArray(Class)
     */
    public static Collector<Character, ?, Character[]> toCharArray() {
        return toArray(Character.class);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a string array.
     *
     * @param mapper The function that produces the elements of the array.
     * @param <T>    The type of the elements of the stream.
     * @return The custom collector.
     * @see #toArray(Class, Function)
     */
    public static <T> Collector<T, ?, String[]> toStringArray(Function<? super T, ? extends String> mapper) {
        return toArray(String.class, mapper);
    }
    
    /**
     * Creates a new custom collector that collects a stream to a string array.
     *
     * @return The custom collector.
     * @see #toArray(Class)
     */
    public static Collector<String, ?, String[]> toStringArray() {
        return toArray(String.class);
    }
    
}
