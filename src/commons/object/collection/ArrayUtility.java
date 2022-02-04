/*
 * File:    ArrayUtility.java
 * Package: commons.object.collection
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import commons.math.BoundUtility;
import commons.math.MathUtility;
import commons.object.string.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides additional array functionality.
 */
public final class ArrayUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ArrayUtility.class);
    
    
    //Functions
    
    /**
     * Creates a new array of a certain type and length, filled with a default value or null.
     *
     * @param type   The type of the array.
     * @param fill   The object to fill the array with, or null.
     * @param length The length of the array.
     * @param <T>    The type of the array.
     * @return The created array.
     */
    @SuppressWarnings("unchecked")
    private static <T> T[] create(Class<T> type, T fill, int length) {
        T[] array = (T[]) Array.newInstance(type, length);
        Arrays.fill(array, fill);
        return array;
    }
    
    /**
     * Creates a new array of a certain type and length, filled with null.
     *
     * @param type   The type of the array.
     * @param length The length of the array.
     * @param <T>    The type of the array.
     * @return The created array.
     * @see #create(Class, Object, int)
     */
    public static <T> T[] create(Class<T> type, int length) {
        return create(type, null, length);
    }
    
    /**
     * Creates a new array of a certain type and length, filled with a default value.
     *
     * @param fill   The object to fill the array with.
     * @param length The length of the array.
     * @param <T>    The type of the array.
     * @return The created array.
     * @see #create(Class, Object, int)
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] create(T fill, int length) {
        return create((Class<T>) fill.getClass(), fill, length);
    }
    
    /**
     * Creates a new array of a certain type.
     *
     * @param type The type of the array.
     * @param <T>  The type of the array.
     * @return The created array.
     * @see #create(Class, int)
     */
    public static <T> T[] create(Class<T> type) {
        return create(type, 0);
    }
    
    /**
     * Creates a new 2D array of a certain type and dimensions, filled with a default value, or null.
     *
     * @param type   The type of the array.
     * @param fill   The object to fill the array with, or null.
     * @param width  The width of the array.
     * @param height The height of the array.
     * @param <T>    The type of the array.
     * @return The created array.
     */
    @SuppressWarnings("unchecked")
    private static <T> T[][] create2D(Class<T> type, T fill, int width, int height) {
        T[][] array = (T[][]) Array.newInstance(type, width, height);
        IntStream.range(0, width).boxed().forEach(i -> array[i] = create(type, fill, height));
        return array;
    }
    
    /**
     * Creates a new 2D array of a certain type and dimensions, filled with null.
     *
     * @param type   The type of the array.
     * @param width  The width of the array.
     * @param height The height of the array.
     * @param <T>    The type of the array.
     * @return The created array.
     * @see #create2D(Class, Object, int, int)
     */
    public static <T> T[][] create2D(Class<T> type, int width, int height) {
        return create2D(type, null, width, height);
    }
    
    /**
     * Creates a new 2D array of a certain type and dimensions, filled with a default value.
     *
     * @param fill   The object to fill the array with.
     * @param width  The width of the array.
     * @param height The height of the array.
     * @param <T>    The type of the array.
     * @return The created array.
     * @see #create2D(Class, Object, int, int)
     */
    @SuppressWarnings("unchecked")
    public static <T> T[][] create2D(T fill, int width, int height) {
        return create2D((Class<T>) fill.getClass(), fill, width, height);
    }
    
    /**
     * Creates a new 2D array of a certain type.
     *
     * @param type The type of the array.
     * @param <T>  The type of the array.
     * @return The created array.
     * @see #create2D(Class, int, int)
     */
    public static <T> T[][] create2D(Class<T> type) {
        return create2D(type, 0, 0);
    }
    
    /**
     * Creates a new 3D array of a certain type and length, filled with a default value or null.
     *
     * @param type   The type of the array.
     * @param fill   The object to fill the array with, or null.
     * @param width  The width of the array.
     * @param height The height of the array.
     * @param depth  The depth of the array.
     * @param <T>    The type of the array.
     * @return The created array.
     */
    @SuppressWarnings({"unchecked", "SuspiciousNameCombination"})
    private static <T> T[][][] create3D(Class<T> type, T fill, int width, int height, int depth) {
        T[][][] array = (T[][][]) Array.newInstance(type, width, height, depth);
        IntStream.range(0, width).boxed().forEach(i -> array[i] = create2D(type, fill, height, depth));
        return array;
    }
    
    /**
     * Creates a new 3D array of a certain type and length, filled with null.
     *
     * @param type   The type of the array.
     * @param width  The width of the array.
     * @param height The height of the array.
     * @param depth  The depth of the array.
     * @param <T>    The type of the array.
     * @return The created array.
     * @see #create3D(Class, Object, int, int, int)
     */
    public static <T> T[][][] create3D(Class<T> type, int width, int height, int depth) {
        return create3D(type, null, width, height, depth);
    }
    
    /**
     * Creates a new 3D array of a certain type and length, filled with a default value.
     *
     * @param fill   The object to fill the array with.
     * @param width  The width of the array.
     * @param height The height of the array.
     * @param depth  The depth of the array.
     * @param <T>    The type of the array.
     * @return The created array.
     * @see #create3D(Class, Object, int, int, int)
     */
    @SuppressWarnings("unchecked")
    public static <T> T[][][] create3D(T fill, int width, int height, int depth) {
        return create3D((Class<T>) fill.getClass(), fill, width, height, depth);
    }
    
    /**
     * Creates a new 3D array of a certain type.
     *
     * @param type The type of the array.
     * @param <T>  The type of the array.
     * @return The created array.
     * @see #create3D(Class, Object, int, int, int)
     */
    public static <T> T[][][] create3D(Class<T> type) {
        return create3D(type, 0, 0, 0);
    }
    
    /**
     * Creates and populates a new array.
     *
     * @param type     The type of the array.
     * @param elements The elements to populate the array with.
     * @param <T>      The type of the array.
     * @return The created and populated array.
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] arrayOf(Class<T> type, T... elements) {
        T[] result = create(type, elements.length);
        System.arraycopy(elements, 0, result, 0, elements.length);
        return result;
    }
    
    /**
     * Converts a list to an array.
     *
     * @param list The list.
     * @param type The type of the list.
     * @param <T>  The type of the array.
     * @return The array built from the list, or null if the list is empty.
     */
    public static <T> T[] toArray(List<T> list, Class<T> type) {
        return list.toArray(create(type, list.size()));
    }
    
    /**
     * Clones an array.
     *
     * @param array The array.
     * @param <T>   The type of the array.
     * @return The clone of the array.
     */
    public static <T> T[] clone(T[] array) {
        return Arrays.copyOf(array, array.length);
    }
    
    /**
     * Creates a sub array from an array.
     *
     * @param array The array.
     * @param from  The index to start the sub array at.
     * @param to    The index to end the sub array at.
     * @param <T>   The type of the array.
     * @return The sub array.
     * @throws IndexOutOfBoundsException When the from or to indices are out of bounds of the array.
     */
    public static <T> T[] subArray(T[] array, int from, int to) throws IndexOutOfBoundsException {
        if ((from > to) || (from < 0) || (to > array.length)) {
            throw new IndexOutOfBoundsException("The range [" + from + "," + to + ") is out of bounds of the array");
        }
        
        return Arrays.copyOfRange(array, from, to);
    }
    
    /**
     * Creates a sub array from an array.
     *
     * @param array The array.
     * @param from  The index to start the sub array at.
     * @param <T>   The type of the array.
     * @return The sub array.
     * @throws IndexOutOfBoundsException When the from or to indices are out of bounds of the array.
     * @see #subArray(Object[], int, int)
     */
    public static <T> T[] subArray(T[] array, int from) throws IndexOutOfBoundsException {
        return subArray(array, from, array.length);
    }
    
    /**
     * Merges two arrays.
     *
     * @param array1 The first array.
     * @param array2 The second array.
     * @param type   The type of the arrays.
     * @param <T>    The type of the arrays.
     * @return The merged array.
     */
    public static <T> T[] merge(T[] array1, T[] array2, Class<T> type) {
        T[] result = create(type, (array1.length + array2.length));
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
    
    /**
     * Splits an array into an array of arrays of a certain length.
     *
     * @param array  The array.
     * @param length The length of the resulting arrays.
     * @param type   The type of the array.
     * @param <T>    The type of the array.
     * @return The array of arrays of the specified length.
     */
    public static <T> T[][] split(T[] array, int length, Class<T> type) {
        length = BoundUtility.truncateNum(length, 1, array.length).intValue();
        
        T[][] result = create2D(type, (int) Math.ceil(array.length / (double) length), length);
        
        for (int i = 0; i < array.length; i++) {
            result[i / length][i % length] = array[i];
        }
        return result;
    }
    
    /**
     * Reverses an array.
     *
     * @param array The array.
     * @param <T>   The type of the array.
     * @return The reversed array.
     */
    public static <T> T[] reverse(T[] array) {
        T[] reversed = array.clone();
        for (int i = 0; i < array.length / 2; i++) {
            T tmp = reversed[i];
            reversed[i] = reversed[reversed.length - i - 1];
            reversed[reversed.length - i - 1] = tmp;
        }
        return reversed;
    }
    
    /**
     * Determines if an array is null or empty.
     *
     * @param array The array.
     * @param <T>   The type of the array.
     * @return Whether the array is null or empty.
     */
    public static <T> boolean isNullOrEmpty(T[] array) {
        return (array == null) || (array.length == 0);
    }
    
    /**
     * Determines if an array equals another array.
     *
     * @param array1     The first array.
     * @param array2     The second array.
     * @param checkOrder Whether to check the order of the arrays or not.
     * @param <T>        The type of the arrays.
     * @return Whether the arrays are equal or not.
     */
    public static <T> boolean equals(T[] array1, T[] array2, boolean checkOrder) {
        return ((array1 == null) || (array2 == null)) ? ((array1 == null) && (array2 == null)) : ((array1.length == array2.length) &&
                (checkOrder ? IntStream.range(0, array1.length).boxed().allMatch(i -> Objects.equals(array1[i], array2[i])) :
                 Arrays.stream(array1).allMatch(e -> contains(array2, e) && (numberOfOccurrences(array1, e) == numberOfOccurrences(array2, e)))));
    }
    
    /**
     * Determines if an array equals another array.
     *
     * @param array1 The first array.
     * @param array2 The second array.
     * @param <T>    The type of the arrays.
     * @return Whether the arrays are equal or not.
     * @see #equals(Object[], Object[], boolean)
     */
    public static <T> boolean equals(T[] array1, T[] array2) {
        return equals(array1, array2, true);
    }
    
    /**
     * Determines if an array of strings equals another array of strings, regardless of case.
     *
     * @param array1     The first array.
     * @param array2     The second array.
     * @param checkOrder Whether to check the order of the arrays or not.
     * @return Whether the arrays of strings are equal or not, regardless of case.
     */
    public static boolean equalsIgnoreCase(String[] array1, String[] array2, boolean checkOrder) {
        return ((array1 == null) || (array2 == null)) ? ((array1 == null) && (array2 == null)) : ((array1.length == array2.length) &&
                (checkOrder ? IntStream.range(0, array1.length).boxed().allMatch(i -> StringUtility.equalsIgnoreCase(array1[i], array2[i])) :
                 Arrays.stream(array1).allMatch(e -> containsIgnoreCase(array2, e) && (numberOfOccurrencesIgnoreCase(array1, e) == numberOfOccurrencesIgnoreCase(array2, e)))));
    }
    
    /**
     * Determines if an array of strings equals another array of strings, regardless of case.
     *
     * @param array1 The first array.
     * @param array2 The second array.
     * @return Whether the arrays of strings are equal or not, regardless of case.
     * @see #equalsIgnoreCase(String[], String[], boolean)
     */
    public static boolean equalsIgnoreCase(String[] array1, String[] array2) {
        return equalsIgnoreCase(array1, array2, true);
    }
    
    /**
     * Determines if an element exists in an array.
     *
     * @param array   The array.
     * @param element The element.
     * @param <T>     The type of the array.
     * @return Whether the array contains the specified element or not.
     */
    public static <T> boolean contains(T[] array, T element) {
        return (array != null) && Arrays.asList(array).contains(element);
    }
    
    /**
     * Determines if a string exists in an array, regardless of case.
     *
     * @param array   The array.
     * @param element The element.
     * @return Whether the array contains the specified string or not, regardless of case.
     */
    public static boolean containsIgnoreCase(String[] array, String element) {
        return (array != null) && Arrays.stream(array).anyMatch(e -> StringUtility.equalsIgnoreCase(e, element));
    }
    
    /**
     * Determines the number of occurrences of an element in an array.
     *
     * @param array   The array.
     * @param element The element.
     * @param <T>     The type of the array.
     * @return The number of occurrences of the specified element in the array.
     */
    public static <T> int numberOfOccurrences(T[] array, T element) {
        return (array == null) ? 0 :
               (int) Arrays.stream(array).filter(e -> Objects.equals(e, element)).count();
    }
    
    /**
     * Determines the number of occurrences of a string element in an array, regardless of case.
     *
     * @param array   The array.
     * @param element The element.
     * @return The number of occurrences of the specified string element in the array, regardless of case.
     */
    public static int numberOfOccurrencesIgnoreCase(String[] array, String element) {
        return (array == null) ? 0 :
               (int) Arrays.stream(array).filter(e -> StringUtility.equalsIgnoreCase(e, element)).count();
    }
    
    /**
     * Returns the index of an element in an array.
     *
     * @param array   The array.
     * @param element The element.
     * @param <T>     The type of the array.
     * @return The index of the element in the array, or -1 if it does not exist.
     */
    public static <T> int indexOf(T[] array, T element) {
        return (array == null) ? -1 :
               Arrays.asList(array).indexOf(element);
    }
    
    /**
     * Returns the index of a string in an array, regardless of case.
     *
     * @param array   The array.
     * @param element The element.
     * @return The index of the string in the array, regardless of case, or -1 if it does not exist.
     */
    public static int indexOfIgnoreCase(String[] array, String element) {
        return (array == null) ? -1 :
               IntStream.range(0, array.length).boxed()
                       .filter(i -> StringUtility.equalsIgnoreCase(array[i], element))
                       .findFirst().orElse(-1);
    }
    
    /**
     * Returns an element from an array at a specified index, or a default value if the index is invalid.
     *
     * @param array        The array.
     * @param index        The index.
     * @param defaultValue The default value.
     * @param <T>          The type of the array.
     * @return The element in the array at the specified index, or the default value if the index is invalid.
     */
    public static <T> T getOrDefault(T[] array, int index, T defaultValue) {
        return ((array != null) && BoundUtility.inArrayBounds(index, array)) ?
               array[index] : defaultValue;
    }
    
    /**
     * Returns an element from an array at a specified index, or null if the index is invalid.
     *
     * @param array The array.
     * @param index The index.
     * @param <T>   The type of the array.
     * @return The element in the array at the specified index, or null if the index is invalid.
     * @see #getOrDefault(Object[], int, Object)
     */
    public static <T> T getOrNull(T[] array, int index) {
        return getOrDefault(array, index, null);
    }
    
    /**
     * Determines if any element in an array is null.
     *
     * @param array The array.
     * @param <T>   The type of the array.
     * @return Whether or not any element in the array is null.
     */
    public static <T> boolean anyNull(T[] array) {
        return Arrays.stream(array).anyMatch(Objects::isNull);
    }
    
    /**
     * Removes null elements from an array.
     *
     * @param array The array.
     * @param <T>   The type of the array.
     * @return The array with null elements removed.
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] removeNull(T[] array) {
        return (T[]) Arrays.stream(array).filter(Objects::nonNull).toArray();
    }
    
    /**
     * Removes duplicate elements from an array.
     *
     * @param array The array to operate on.
     * @param type  The type of the array.
     * @param <T>   The type of the array.
     * @return The array with duplicate elements removed.
     */
    public static <T> T[] removeDuplicates(T[] array, Class<T> type) {
        return toArray(Arrays.stream(array).distinct().collect(Collectors.toList()), type);
    }
    
    /**
     * Selects a random element from an array.
     *
     * @param array The array to select from.
     * @param <T>   The type of the array.
     * @return A random element from the array.
     */
    public static <T> T selectRandom(T[] array) {
        if (array.length == 0) {
            return null;
        }
        
        return array[MathUtility.random(array.length - 1)];
    }
    
    /**
     * Selects a random subset of an array.
     *
     * @param array The array to select from.
     * @param n     The number of elements to select.
     * @param type  The type of the array.
     * @param <T>   The type of the array.
     * @return A random subset of the array.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    public static <T> T[] selectN(T[] array, int n, Class<T> type) {
        if (n >= array.length) {
            List<T> shuffle = Arrays.asList(array);
            Collections.shuffle(shuffle);
            return toArray(shuffle, type);
        }
        
        List<Integer> previousChoices = new ArrayList<>();
        T[] choices = create(type, Math.max(n, 0));
        for (int i = 0; i < n; i++) {
            int index;
            while (previousChoices.contains(index = MathUtility.random(array.length - 1))) {
            }
            choices[i] = array[index];
            previousChoices.add(index);
        }
        return choices;
    }
    
    /**
     * Copies an array to the end of itself a number of times making an array n times the original length.
     *
     * @param array The array to duplicate.
     * @param times The number of copies of the array to add.
     * @param type  The type of the array.
     * @param <T>   The type of the array.
     * @return An array of double size with duplicated elements.
     */
    public static <T> T[] duplicateInOrder(T[] array, int times, Class<T> type) {
        if (times <= 0) {
            return toArray(Collections.emptyList(), type);
        }
        if (times == 1) {
            return clone(array);
        }
        
        T[] finalArray = create(type, (array.length * times));
        for (int time = 0; time < times; time++) {
            System.arraycopy(array, 0, finalArray, (array.length * time), array.length);
        }
        return finalArray;
    }
    
    /**
     * Copies an array to the end of itself making an array double the original length.
     *
     * @param array The array to duplicate.
     * @param type  The type of the array.
     * @param <T>   The type of the array.
     * @return A array of double size with duplicated elements.
     * @see #duplicateInOrder(Object[], int, Class)
     */
    public static <T> T[] duplicateInOrder(T[] array, Class<T> type) {
        return duplicateInOrder(array, 2, type);
    }
    
    /**
     * Sorts an array by the number of occurrences of each entry in the array.
     *
     * @param array   The array to sort.
     * @param reverse Whether to sort in reverse or not.
     * @param type    The type of the array.
     * @param <T>     The type of the array.
     * @return The array sorted by the number of occurrences of each entry in the array.
     */
    public static <T> T[] sortByNumberOfOccurrences(T[] array, boolean reverse, Class<T> type) {
        Map<T, Integer> store = new LinkedHashMap<>();
        for (T entry : array) {
            if (store.containsKey(entry)) {
                store.replace(entry, store.get(entry) + 1);
            } else {
                store.put(entry, 1);
            }
        }
        
        T[] sorted = create(type, array.length);
        final AtomicInteger index = new AtomicInteger(0);
        if (reverse) {
            store.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e -> {
                for (int i = 0; i < e.getValue(); i++) {
                    sorted[index.getAndIncrement()] = e.getKey();
                }
            });
        } else {
            store.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(e -> {
                for (int i = 0; i < e.getValue(); i++) {
                    sorted[index.getAndIncrement()] = e.getKey();
                }
            });
        }
        return sorted;
    }
    
    /**
     * Sorts an array by the number of occurrences of each entry in the array.
     *
     * @param array The array to sort.
     * @param type  The type of the array.
     * @param <T>   The type of the array.
     * @return The array sorted by the number of occurrences of each entry in the array.
     * @see #sortByNumberOfOccurrences(Object[], boolean, Class)
     */
    public static <T> T[] sortByNumberOfOccurrences(T[] array, Class<T> type) {
        return sortByNumberOfOccurrences(array, false, type);
    }
    
}
