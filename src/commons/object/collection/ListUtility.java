/*
 * File:    ListUtility.java
 * Package: commons.object.collection
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import commons.math.BoundUtility;
import commons.math.MathUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides additional list functionality.
 */
public final class ListUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ListUtility.class);
    
    
    //Constants
    
    /**
     * The default list class to use when one is not specified.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static final Class<? extends List<?>> DEFAULT_LIST_CLASS = (Class) ArrayList.class;
    
    
    //Functions
    
    /**
     * Creates a new list of a certain class, type, and length, filled with a default value or null.
     *
     * @param clazz  The class of the list.
     * @param type   The type of the list.
     * @param fill   The object to fill the list with, or null.
     * @param length The length of the list.
     * @param <T>    The type of the list.
     * @param <L>    The class of the list.
     * @return The created list.
     */
    private static <T, L extends List<?>> List<T> create(Class<L> clazz, Class<T> type, T fill, int length) {
        List<T> list;
        switch (clazz.getSimpleName()) {
            case "ArrayList":
            default:
                list = new ArrayList<>(length);
                break;
            case "LinkedList":
                list = new LinkedList<>();
                break;
            case "Stack":
                list = new Stack<>();
                break;
            case "Vector":
                list = new Vector<>(length);
                break;
        }
        IntStream.range(0, length).boxed().forEach(i -> list.add(fill));
        return list;
    }
    
    /**
     * Creates a new list of a certain class, type, and length, filled with null.
     *
     * @param clazz  The class of the list.
     * @param type   The type of the list.
     * @param length The length of the list.
     * @param <T>    The type of the list.
     * @param <L>    The class of the list.
     * @return The created list.
     * @see #create(Class, Class, Object, int)
     */
    public static <T, L extends List<?>> List<T> create(Class<L> clazz, Class<T> type, int length) {
        return create(clazz, type, null, length);
    }
    
    /**
     * Creates a new list of a certain class, type, and length, filled with a default value.
     *
     * @param clazz  The class of the list.
     * @param fill   The object to fill the list with.
     * @param length The length of the list.
     * @param <T>    The type of the list.
     * @param <L>    The class of the list.
     * @return The created list.
     * @see #create(Class, Class, Object, int)
     */
    @SuppressWarnings("unchecked")
    public static <T, L extends List<?>> List<T> create(Class<L> clazz, T fill, int length) {
        return create(clazz, (Class<T>) fill.getClass(), fill, length);
    }
    
    /**
     * Creates a new list of a certain class and type.
     *
     * @param clazz The class of the list.
     * @param type  The type of the list.
     * @param <T>   The type of the list.
     * @param <L>   The class of the list.
     * @return The created list.
     * @see #create(Class, Class, int)
     */
    public static <T, L extends List<?>> List<T> create(Class<L> clazz, Class<T> type) {
        return create(clazz, type, 0);
    }
    
    /**
     * Creates a new list of a certain type and length, filled with null.
     *
     * @param type   The type of the list.
     * @param length The length of the list.
     * @param <T>    The type of the list.
     * @return The created list.
     * @see #create(Class, Class, int)
     */
    public static <T> List<T> create(Class<T> type, int length) {
        return create(DEFAULT_LIST_CLASS, type, length);
    }
    
    /**
     * Creates a new list of a certain type and length, filled with a default value.
     *
     * @param fill   The object to fill the list with.
     * @param length The length of the list.
     * @param <T>    The type of the list.
     * @return The created list.
     * @see #create(Class, Object, int)
     */
    public static <T> List<T> create(T fill, int length) {
        return create(DEFAULT_LIST_CLASS, fill, length);
    }
    
    /**
     * Creates a new list of a certain type.
     *
     * @param type The type of the list.
     * @param <T>  The type of the list.
     * @return The created list.
     * @see #create(Class, int)
     */
    public static <T> List<T> create(Class<T> type) {
        return create(DEFAULT_LIST_CLASS, type);
    }
    
    /**
     * Creates a new 2D list of a certain class, type, and dimensions, filled with a default value or null.
     *
     * @param clazz  The class of the list.
     * @param type   The type of the list.
     * @param fill   The object to fill the list with, or null.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param <T>    The type of the list.
     * @param <L>    The class of the list.
     * @return The created list.
     */
    @SuppressWarnings("unchecked")
    private static <T, L extends List<?>> List<List<T>> create2D(Class<L> clazz, Class<T> type, T fill, int width, int height) {
        List<List<T>> list = (List<List<T>>) create(clazz, clazz, width);
        IntStream.range(0, width).boxed().forEach(i -> list.set(i, create(clazz, type, fill, height)));
        return list;
    }
    
    /**
     * Creates a new 2D list of a certain class, type, and dimensions, filled with null.
     *
     * @param clazz  The class of the list.
     * @param type   The type of the list.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param <T>    The type of the list.
     * @param <L>    The class of the list.
     * @return The created list.
     * @see #create2D(Class, Class, Object, int, int)
     */
    public static <T, L extends List<?>> List<List<T>> create2D(Class<L> clazz, Class<T> type, int width, int height) {
        return create2D(clazz, type, null, width, height);
    }
    
    /**
     * Creates a new 2D list of a certain class, type, and dimensions, filled with a default value.
     *
     * @param clazz  The class of the list.
     * @param fill   The object to fill the list with.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param <T>    The type of the list.
     * @param <L>    The class of the list.
     * @return The created list.
     * @see #create2D(Class, Class, Object, int, int)
     */
    @SuppressWarnings("unchecked")
    public static <T, L extends List<?>> List<List<T>> create2D(Class<L> clazz, T fill, int width, int height) {
        return create2D(clazz, (Class<T>) fill.getClass(), fill, width, height);
    }
    
    /**
     * Creates a new 2D list of a certain class and type.
     *
     * @param clazz The class of the list.
     * @param type  The type of the list.
     * @param <T>   The type of the list.
     * @param <L>   The class of the list.
     * @return The created list.
     * @see #create2D(Class, Class, int, int)
     */
    public static <T, L extends List<?>> List<List<T>> create2D(Class<L> clazz, Class<T> type) {
        return create2D(clazz, type, 0, 0);
    }
    
    /**
     * Creates a new 2D list of a certain type and dimensions, filled with null.
     *
     * @param type   The type of the list.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param <T>    The type of the list.
     * @return The created list.
     * @see #create2D(Class, Class, int, int)
     */
    public static <T> List<List<T>> create2D(Class<T> type, int width, int height) {
        return create2D(DEFAULT_LIST_CLASS, type, width, height);
    }
    
    /**
     * Creates a new 2D list of a certain type and dimensions, filled with a default value.
     *
     * @param fill   The object to fill the list with.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param <T>    The type of the list.
     * @return The created list.
     * @see #create2D(Class, Object, int, int)
     */
    public static <T> List<List<T>> create2D(T fill, int width, int height) {
        return create2D(DEFAULT_LIST_CLASS, fill, width, height);
    }
    
    /**
     * Creates a new 2D list of a certain type.
     *
     * @param type The type of the list.
     * @param <T>  The type of the list.
     * @return The created list.
     * @see #create2D(Class, Class)
     */
    public static <T> List<List<T>> create2D(Class<T> type) {
        return create2D(DEFAULT_LIST_CLASS, type);
    }
    
    /**
     * Creates a new 3D list of a certain class, type, and dimensions, filled with a default value or null.
     *
     * @param clazz  The class of the list.
     * @param type   The type of the list.
     * @param fill   The object to fill the list with, or null.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param depth  The depth of the list.
     * @param <T>    The type of the list.
     * @param <L>    The class of the list.
     * @return The created list.
     */
    @SuppressWarnings({"SuspiciousNameCombination", "unchecked"})
    private static <T, L extends List<?>> List<List<List<T>>> create3D(Class<L> clazz, Class<T> type, T fill, int width, int height, int depth) {
        List<List<List<T>>> list = (List<List<List<T>>>) create(clazz, clazz, width);
        IntStream.range(0, width).boxed().forEach(i -> list.set(i, create2D(clazz, type, fill, height, depth)));
        return list;
    }
    
    /**
     * Creates a new 3D list of a certain class, type and dimensions, filled with null.
     *
     * @param clazz  The class of the list.
     * @param type   The type of the list.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param depth  The depth of the list.
     * @param <T>    The type of the list.
     * @param <L>    The class of the list.
     * @return The created list.
     * @see #create3D(Class, Object, int, int, int)
     */
    public static <T, L extends List<?>> List<List<List<T>>> create3D(Class<L> clazz, Class<T> type, int width, int height, int depth) {
        return create3D(clazz, type, null, width, height, depth);
    }
    
    /**
     * Creates a new 3D list of a certain class, type, and dimensions, filled with a default value.
     *
     * @param clazz  The class of the list.
     * @param fill   The object to fill the list with.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param depth  The depth of the list.
     * @param <T>    The type of the list.
     * @param <L>    The class of the list.
     * @return The created list.
     * @see #create3D(Class, Class, Object, int, int, int)
     */
    @SuppressWarnings("unchecked")
    public static <T, L extends List<?>> List<List<List<T>>> create3D(Class<L> clazz, T fill, int width, int height, int depth) {
        return create3D(clazz, (Class<T>) fill.getClass(), fill, width, height, depth);
    }
    
    /**
     * Creates a new 3D list of a certain class and type.
     *
     * @param clazz The class of the list.
     * @param type  The type of the list.
     * @param <T>   The type of the list.
     * @param <L>   The class of the list.
     * @return The created list.
     * @see #create3D(Class, Class, int, int, int)
     */
    public static <T, L extends List<?>> List<List<List<T>>> create3D(Class<L> clazz, Class<T> type) {
        return create3D(clazz, type, 0, 0, 0);
    }
    
    /**
     * Creates a new 3D list of a certain type and dimensions, filled with null.
     *
     * @param type   The type of the list.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param depth  The depth of the list.
     * @param <T>    The type of the list.
     * @return The created list.
     * @see #create3D(Class, Class, int, int, int)
     */
    public static <T> List<List<List<T>>> create3D(Class<T> type, int width, int height, int depth) {
        return create3D(DEFAULT_LIST_CLASS, type, width, height, depth);
    }
    
    /**
     * Creates a new 3D list of a certain type and dimensions, filled with a default value.
     *
     * @param fill   The object to fill the list with.
     * @param width  The width of the list.
     * @param height The height of the list.
     * @param depth  The depth of the list.
     * @param <T>    The type of the list.
     * @return The created list.
     * @see #create3D(Class, Object, int, int, int)
     */
    public static <T> List<List<List<T>>> create3D(T fill, int width, int height, int depth) {
        return create3D(DEFAULT_LIST_CLASS, fill, width, height, depth);
    }
    
    /**
     * Creates a new 3D list of a certain type.
     *
     * @param type The type of the list.
     * @param <T>  The type of the list.
     * @return The created list.
     * @see #create3D(Class, Class)
     */
    public static <T> List<List<List<T>>> create3D(Class<T> type) {
        return create3D(DEFAULT_LIST_CLASS, type);
    }
    
    /**
     * Converts an array to a list of a certain class.
     *
     * @param array The array.
     * @param clazz The class of the list.
     * @param <T>   The type of the array.
     * @param <L>   The class of the list.
     * @return The list built from the array.
     */
    public static <T, L extends List<?>> List<T> toList(T[] array, Class<L> clazz) {
        return convertTo(Arrays.asList(array), clazz);
    }
    
    /**
     * Converts an array to a list.
     *
     * @param array The array.
     * @param <T>   The type of the array.
     * @return The list built from the array.
     * @see #toList(Object[], Class)
     */
    public static <T> List<T> toList(T[] array) {
        return toList(array, DEFAULT_LIST_CLASS);
    }
    
    /**
     * Clones a list.
     *
     * @param list The list.
     * @param <T>  The type of the list.
     * @return The clone of the list.
     */
    public static <T> List<T> clone(List<T> list) {
        return convertTo(list, list.getClass());
    }
    
    /**
     * Converts a list to a list of a specific class.
     *
     * @param list  The list.
     * @param clazz The list class to convert to.
     * @param <T>   The type of the list.
     * @param <L>   The list class to convert to.
     * @return The converted list.
     */
    public static <T, L extends List<?>> List<T> convertTo(List<T> list, Class<L> clazz) {
        switch (clazz.getSimpleName()) {
            case "ArrayList":
            default:
                return new ArrayList<>(list);
            case "LinkedList":
                return new LinkedList<>(list);
            case "Stack":
                final Stack<T> stack = new Stack<>();
                list.forEach(stack::push);
                return stack;
            case "Vector":
                return new Vector<>(list);
        }
    }
    
    /**
     * Creates a sub list from a list.
     *
     * @param list The list.
     * @param from The index to start the sub list at.
     * @param to   The index to end the sub list at.
     * @param <T>  The type of the list.
     * @return The sub list.
     * @throws IndexOutOfBoundsException When the from or to indices are out of bounds of the list.
     */
    public static <T> List<T> subList(List<T> list, int from, int to) throws IndexOutOfBoundsException {
        if ((from > to) || (from < 0) || (to > list.size())) {
            throw new IndexOutOfBoundsException("The range [" + from + "," + to + ") is out of bounds of the list");
        }
        
        return convertTo(list.subList(from, to), list.getClass());
    }
    
    /**
     * Creates a sub list from a list.
     *
     * @param list The list.
     * @param from The index to start the sub list at.
     * @param <T>  The type of the list.
     * @return The sub list.
     * @throws IndexOutOfBoundsException When the from or to indices are out of bounds of the list.
     * @see #subList(List, int, int)
     */
    public static <T> List<T> subList(List<T> list, int from) throws IndexOutOfBoundsException {
        return subList(list, from, list.size());
    }
    
    /**
     * Merges two lists.
     *
     * @param list1 The first list.
     * @param list2 The second list.
     * @param <T>   The type of the lists.
     * @return The merged list.
     */
    public static <T> List<T> merge(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return convertTo(result, list1.getClass());
    }
    
    /**
     * Splits a list into a list of lists of a certain length.
     *
     * @param list   The list.
     * @param length The length of the resulting lists.
     * @param <T>    The type of the list.
     * @return The list of lists of the specified length.
     */
    public static <T> List<List<T>> split(List<T> list, int length) {
        length = BoundUtility.truncateNum(length, 1, list.size()).intValue();
        
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < (int) Math.ceil(list.size() / (double) length); i++) {
            result.add(new ArrayList<>(Collections.nCopies(length, null)));
        }
        
        for (int i = 0; i < list.size(); i++) {
            result.get(i / length).set((i % length), list.get(i));
        }
        
        for (int i = 0; i < result.size(); i++) {
            result.set(i, convertTo(result.get(i), list.getClass()));
        }
        return convertTo(result, list.getClass());
    }
    
    /**
     * Reverses a list.
     *
     * @param list The list.
     * @param <T>  The type of the list.
     * @return The reversed list.
     */
    public static <T> List<T> reverse(List<T> list) {
        List<T> reversed = clone(list);
        Collections.reverse(reversed);
        return reversed;
    }
    
    /**
     * Determines whether an element exists in a list.
     *
     * @param list    The list.
     * @param element The element.
     * @param <T>     The type of the list.
     * @return Whether the list contains the specified element or not.
     */
    public static <T> boolean contains(List<T> list, T element) {
        return list.contains(element);
    }
    
    /**
     * Determines whether a string exists in a list, regardless of case.
     *
     * @param list    The list.
     * @param element The element.
     * @return Whether the list contains the specified string or not.
     */
    public static boolean containsIgnoreCase(List<String> list, String element) {
        return list.stream().anyMatch(e -> e.equalsIgnoreCase(element));
    }
    
    /**
     * Returns the index of an element in a list.
     *
     * @param list    The list.
     * @param element The element.
     * @param <T>     The type of the list.
     * @return The index of the element in the list, or -1 if it does not exist.
     */
    public static <T> int indexOf(List<T> list, T element) {
        return list.indexOf(element);
    }
    
    /**
     * Returns an element from a list at a specified index, or a default value if the index is invalid.
     *
     * @param list         The list.
     * @param index        The index.
     * @param defaultValue The default value.
     * @param <T>          The type of the list.
     * @return The element in the list at the specified index, or the default value if the index is invalid.
     */
    public static <T> T getOrDefault(List<T> list, int index, T defaultValue) {
        return ((list != null) && BoundUtility.inBounds(index, 0, list.size(), true, false)) ?
               list.get(index) : defaultValue;
    }
    
    /**
     * Returns an element from a list at a specified index, or null if the index is invalid.
     *
     * @param list  The list.
     * @param index The index.
     * @param <T>   The type of the list.
     * @return The element in the list at the specified index, or null if the index is invalid.
     * @see #getOrDefault(List, int, Object)
     */
    public static <T> T getOrNull(List<T> list, int index) {
        return getOrDefault(list, index, null);
    }
    
    /**
     * Determines if any element in a list is null.
     *
     * @param list The list.
     * @param <T>  The type of the list.
     * @return Whether or not any element in the list is null.
     */
    public static <T> boolean anyNull(List<T> list) {
        return list.stream().anyMatch(Objects::isNull);
    }
    
    /**
     * Determines if any element in a list is null.
     *
     * @param list The list.
     * @param <T>  The type of the list.
     * @return Whether or not any element in the list is null.
     * @see #anyNull(List)
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean anyNull(T... list) {
        return anyNull(Arrays.asList(list));
    }
    
    /**
     * Removes null elements from a list.
     *
     * @param list The list.
     * @param <T>  The type of the list.
     * @return The list with null elements removed.
     */
    public static <T> List<T> removeNull(List<T> list) {
        return convertTo(list.stream().filter(Objects::nonNull).collect(Collectors.toList()), list.getClass());
    }
    
    /**
     * Removes duplicate elements from a list.
     *
     * @param list The list to operate on.
     * @param <T>  The type of the list.
     * @return The list with duplicate elements removed.
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        return convertTo(list.stream().distinct().collect(Collectors.toList()), list.getClass());
    }
    
    /**
     * Selects a random element from a list.
     *
     * @param list The list to select from.
     * @param <T>  The type of the list.
     * @return A random element from the list.
     */
    public static <T> T selectRandom(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(MathUtility.random(list.size() - 1));
    }
    
    /**
     * Selects a random subset of a list.
     *
     * @param list The list to select from.
     * @param n    The number of elements to select.
     * @param <T>  The type of the list.
     * @return A random subset of the list.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    public static <T> List<T> selectN(List<T> list, int n) {
        if (n >= list.size()) {
            List<T> shuffle = clone(list);
            Collections.shuffle(shuffle);
            return shuffle;
        }
        
        List<Integer> previousChoices = new ArrayList<>();
        List<T> choices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index;
            while (previousChoices.contains(index = MathUtility.random(list.size() - 1))) {
            }
            choices.add(list.get(index));
            previousChoices.add(index);
        }
        return convertTo(choices, list.getClass());
    }
    
    /**
     * Copies a list to the end of itself a number of times making a list n times the original length.
     *
     * @param list  The list to duplicate.
     * @param times The number of copies of the list to add.
     * @param <T>   The type of the list.
     * @return A list of double size with duplicated elements.
     */
    public static <T> List<T> duplicateInOrder(List<T> list, int times) {
        if (times <= 0) {
            return convertTo(new ArrayList<>(), list.getClass());
        }
        if (times == 1) {
            return clone(list);
        }
        
        List<T> finalList = new ArrayList<>(list.size() * times);
        for (int time = 0; time < times; time++) {
            finalList.addAll(list);
        }
        return convertTo(finalList, list.getClass());
    }
    
    /**
     * Copies a list to the end of itself making a list double the original length.
     *
     * @param list The list to duplicate.
     * @param <T>  The type of the list.
     * @return A list of double size with duplicated elements.
     * @see #duplicateInOrder(List, int)
     */
    public static <T> List<T> duplicateInOrder(List<T> list) {
        return duplicateInOrder(list, 2);
    }
    
    /**
     * Sorts a list by the number of occurrences of each entry in the list.
     *
     * @param list    The list to sort.
     * @param reverse Whether to sort in reverse or not.
     * @param <T>     The type of the list.
     * @return The list sorted by the number of occurrences of each entry in the list.
     */
    public static <T> List<T> sortByNumberOfOccurrences(List<T> list, boolean reverse) {
        Map<T, Integer> store = new LinkedHashMap<>();
        for (T entry : list) {
            if (store.containsKey(entry)) {
                store.replace(entry, store.get(entry) + 1);
            } else {
                store.put(entry, 1);
            }
        }
        
        List<T> sorted = new ArrayList<>();
        if (reverse) {
            store.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e -> {
                for (int i = 0; i < e.getValue(); i++) {
                    sorted.add(e.getKey());
                }
            });
        } else {
            store.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(e -> {
                for (int i = 0; i < e.getValue(); i++) {
                    sorted.add(e.getKey());
                }
            });
        }
        return convertTo(sorted, list.getClass());
    }
    
    /**
     * Sorts a list by the number of occurrences of each entry in the list.
     *
     * @param list The list to sort.
     * @param <T>  The type of the list.
     * @return The list sorted by the number of occurrences of each entry in the list.
     * @see #sortByNumberOfOccurrences(List, boolean)
     */
    public static <T> List<T> sortByNumberOfOccurrences(List<T> list) {
        return sortByNumberOfOccurrences(list, false);
    }
    
}
