/*
 * File:    ListCollectorsTest.java
 * Package: commons.lambda.stream.collector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import commons.object.string.StringUtility;
import commons.test.TestAccess;
import commons.test.TestUtils;
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
 * JUnit test of ListCollectors.
 *
 * @see ListCollectors
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ListCollectors.class})
public class ListCollectorsTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ListCollectorsTest.class);
    
    
    //Static Fields
    
    /**
     * A set of lists containing the elements of the streams to use for testing.
     */
    private static final List<?>[] testStreamElements = new List<?>[] {
            List.of("hello", "world", "test"),
            List.of("test:test", "key:value", "other:another", "test:else"),
            List.of(1, 4, 11),
            List.of(List.of(0, "test"), List.of(9, "value"), List.of(-4, "another"), List.of(10, "else"))};
    
    /**
     * A set of lists corresponding to the streams to use for testing.
     */
    private static final List<?>[] testLists = new List<?>[] {
            List.of("hello", "world", "test"),
            List.of("test", "key", "another", "test"),
            List.of(false, true, true),
            List.of(0, 9, -4, 10)};
    
    /**
     * The UnmodifiableList class.
     */
    private static final Class<?> UnmodifiableList = TestAccess.getClass(Collections.class, "UnmodifiableList");
    
    /**
     * The UnmodifiableRandomAccessList class.
     */
    private static final Class<?> UnmodifiableRandomAccessList = TestAccess.getClass(Collections.class, "UnmodifiableRandomAccessList");
    
    /**
     * The SynchronizedList class.
     */
    private static final Class<?> SynchronizedList = TestAccess.getClass(Collections.class, "SynchronizedList");
    
    /**
     * The SynchronizedRandomAccessList class.
     */
    private static final Class<?> SynchronizedRandomAccessList = TestAccess.getClass(Collections.class, "SynchronizedRandomAccessList");
    
    
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
     * JUnit test of ListFlavor.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors.ListFlavor
     */
    @Test
    public void testListFlavor() throws Exception {
        final Class<?> ListFlavor = ListCollectors.ListFlavor.class;
        final Object[] listFlavors = ListFlavor.getEnumConstants();
        
        //values
        Assert.assertEquals(3, listFlavors.length);
        Assert.assertEquals(ListCollectors.ListFlavor.STANDARD, listFlavors[0]);
        Assert.assertEquals(ListCollectors.ListFlavor.UNMODIFIABLE, listFlavors[1]);
        Assert.assertEquals(ListCollectors.ListFlavor.SYNCHRONIZED, listFlavors[2]);
        
        //fields
        TestUtils.assertFieldExists(ListFlavor, "styler");
        Assert.assertTrue(Arrays.stream(listFlavors).map(e -> TestAccess.getFieldValue(e, "styler")).allMatch(Objects::nonNull));
        
        //methods
        TestUtils.assertConstructorExists(ListFlavor, String.class, int.class, Function.class);
        TestUtils.assertMethodExists(ListFlavor, "apply", List.class);
    }
    
    /**
     * JUnit test of toList.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toList(Supplier, ListCollectors.ListFlavor, Function)
     * @see ListCollectors#toList(Supplier, Function)
     * @see ListCollectors#toList(Class, ListCollectors.ListFlavor, Function)
     * @see ListCollectors#toList(Class, Function)
     * @see ListCollectors#toList(Supplier, ListCollectors.ListFlavor)
     * @see ListCollectors#toList(Supplier)
     * @see ListCollectors#toList(Class, ListCollectors.ListFlavor)
     * @see ListCollectors#toList(Class)
     */
    @Test
    public void testToList() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toList(ArrayList::new,
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toList(LinkedList::new,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof LinkedList);
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toList(Stack::new,
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertTrue(list3 instanceof Stack);
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toList(Vector::new,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Vector);
        TestUtils.assertListEquals(list4, testList4);
        
        //flavor
        list1 = testElements1.stream().collect(ListCollectors.toList(ArrayList::new, ListCollectors.ListFlavor.STANDARD,
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toList(LinkedList::new, ListCollectors.ListFlavor.UNMODIFIABLE,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toList(Stack::new, ListCollectors.ListFlavor.SYNCHRONIZED,
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toList(Vector::new, ListCollectors.ListFlavor.STANDARD,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Vector);
        TestUtils.assertListEquals(list4, testList4);
        
        //class
        list1 = testElements1.stream().collect(ListCollectors.toList(ArrayList.class,
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toList(LinkedList.class,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof LinkedList);
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toList(Stack.class,
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertTrue(list3 instanceof Stack);
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toList(Vector.class,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Vector);
        TestUtils.assertListEquals(list4, testList4);
        
        //class, flavor
        list1 = testElements1.stream().collect(ListCollectors.toList(ArrayList.class, ListCollectors.ListFlavor.STANDARD,
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toList(LinkedList.class, ListCollectors.ListFlavor.UNMODIFIABLE,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toList(Stack.class, ListCollectors.ListFlavor.SYNCHRONIZED,
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toList(Vector.class, ListCollectors.ListFlavor.STANDARD,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Vector);
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toList(ArrayList::new));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toList(LinkedList::new));
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof LinkedList);
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toList(Stack::new));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Stack);
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toList(Vector::new));
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof Vector);
        TestUtils.assertListEquals(list5, testElements4);
        
        //identity, flavor
        list1 = testElements1.stream().collect(ListCollectors.toList(ArrayList::new, ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toList(LinkedList::new, ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toList(Stack::new, ListCollectors.ListFlavor.SYNCHRONIZED));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toList(Vector::new, ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof Vector);
        TestUtils.assertListEquals(list5, testElements4);
        
        //class, identity
        list1 = testElements1.stream().collect(ListCollectors.toList(ArrayList.class));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toList(LinkedList.class));
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof LinkedList);
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toList(Stack.class));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Stack);
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toList(Vector.class));
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof Vector);
        TestUtils.assertListEquals(list5, testElements4);
        
        //class, identity, flavor
        list1 = testElements1.stream().collect(ListCollectors.toList(ArrayList.class, ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toList(LinkedList.class, ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toList(Stack.class, ListCollectors.ListFlavor.SYNCHRONIZED));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toList(Vector.class, ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof Vector);
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toList(ArrayList::new, ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()),
                ListCollectors.toList(ArrayList::new, ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toList(ArrayList::new, Function.identity()),
                ListCollectors.toList(ArrayList::new, Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toList(ArrayList.class, ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()),
                ListCollectors.toList(ArrayList.class, ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toList(ArrayList.class, Function.identity()),
                ListCollectors.toList(ArrayList.class, Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toList(ArrayList::new, ListCollectors.ListFlavor.UNMODIFIABLE),
                ListCollectors.toList(ArrayList::new, ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                ListCollectors.toList(ArrayList::new),
                ListCollectors.toList(ArrayList::new));
        Assert.assertNotSame(
                ListCollectors.toList(ArrayList.class, ListCollectors.ListFlavor.UNMODIFIABLE),
                ListCollectors.toList(ArrayList.class, ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                ListCollectors.toList(ArrayList.class),
                ListCollectors.toList(ArrayList.class));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Supplier<List<Integer>>) null, ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList(ArrayList::new, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList(ArrayList::new, ListCollectors.ListFlavor.UNMODIFIABLE, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Supplier<List<Integer>>) null, null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Supplier<List<Integer>>) null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList(ArrayList::new, (Function<Integer, Integer>) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Supplier<List<Integer>>) null, (Function<Integer, Integer>) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Class<List<Integer>>) null, ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList(ArrayList.class, null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList(ArrayList.class, ListCollectors.ListFlavor.UNMODIFIABLE, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Class<List<Integer>>) null, null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Class<List<Integer>>) null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList(ArrayList.class, (Function<Integer, Integer>) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Class<List<Integer>>) null, (Function<Integer, Integer>) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Supplier<List<Integer>>) null, ListCollectors.ListFlavor.UNMODIFIABLE)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList(ArrayList::new, (ListCollectors.ListFlavor) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Supplier<List<Integer>>) null, (ListCollectors.ListFlavor) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Class<List<Integer>>) null, ListCollectors.ListFlavor.UNMODIFIABLE)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList(ArrayList.class, (ListCollectors.ListFlavor) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toList((Class<List<Integer>>) null, (ListCollectors.ListFlavor) null)));
    }
    
    /**
     * JUnit test of generator.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#generator(Class)
     * @see ListCollectors#generator(Class, Class)
     * @see ListCollectors#generator()
     */
    @Test
    public void testGenerator() throws Exception {
        List<Object> list1;
        List<String> list2;
        List<Boolean> list3;
        
        //standard
        list1 = ListCollectors.generator(ArrayList.class).get();
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        list1 = ListCollectors.generator(LinkedList.class).get();
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof LinkedList);
        list1 = ListCollectors.generator(Stack.class).get();
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Stack);
        list1 = ListCollectors.generator(Vector.class).get();
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Vector);
        list1 = ListCollectors.generator().get();
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        
        //type
        list1 = ListCollectors.generator(ArrayList.class, Object.class).get();
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        list2 = ListCollectors.generator(LinkedList.class, String.class).get();
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof LinkedList);
        list2 = ListCollectors.generator(Stack.class, String.class).get();
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof Stack);
        list3 = ListCollectors.generator(Vector.class, Boolean.class).get();
        Assert.assertNotNull(list3);
        Assert.assertTrue(list3 instanceof Vector);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.generator(ArrayList.class, String.class),
                ListCollectors.generator(ArrayList.class, String.class));
        Assert.assertNotSame(
                ListCollectors.generator(ArrayList.class),
                ListCollectors.generator(ArrayList.class));
        Assert.assertNotSame(
                ListCollectors.generator(),
                ListCollectors.generator());
        
        //invalid
        Assert.assertNotNull(ListCollectors.generator(ArrayList.class, null).get());
        TestUtils.assertNoException(() ->
                ListCollectors.generator(null, String.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ListCollectors.generator(null, String.class).get());
        TestUtils.assertNoException(() ->
                ListCollectors.generator(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListCollectors.generator(null, null).get());
        TestUtils.assertNoException(() ->
                ListCollectors.generator(null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListCollectors.generator(null).get());
    }
    
    /**
     * JUnit test of toArrayList.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toArrayList(ListCollectors.ListFlavor, Function)
     * @see ListCollectors#toArrayList(ListCollectors.ListFlavor)
     * @see ListCollectors#toArrayList(Function)
     * @see ListCollectors#toArrayList()
     */
    @Test
    public void testToArrayList() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toArrayList(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toArrayList(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof ArrayList);
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toArrayList(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertTrue(list3 instanceof ArrayList);
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toArrayList(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof ArrayList);
        TestUtils.assertListEquals(list4, testList4);
        
        //flavor
        list1 = testElements1.stream().collect(ListCollectors.toArrayList(ListCollectors.ListFlavor.STANDARD,
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toArrayList(ListCollectors.ListFlavor.UNMODIFIABLE,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toArrayList(ListCollectors.ListFlavor.SYNCHRONIZED,
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toArrayList(ListCollectors.ListFlavor.STANDARD,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof ArrayList);
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toArrayList());
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toArrayList());
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof ArrayList);
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toArrayList());
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof ArrayList);
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toArrayList());
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof ArrayList);
        TestUtils.assertListEquals(list5, testElements4);
        
        //identity, flavor
        list1 = testElements1.stream().collect(ListCollectors.toArrayList(ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof ArrayList);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toArrayList(ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toArrayList(ListCollectors.ListFlavor.SYNCHRONIZED));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toArrayList(ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof ArrayList);
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toArrayList(ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()),
                ListCollectors.toArrayList(ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toArrayList(ListCollectors.ListFlavor.UNMODIFIABLE),
                ListCollectors.toArrayList(ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                ListCollectors.toArrayList(Function.identity()),
                ListCollectors.toArrayList(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toArrayList(),
                ListCollectors.toArrayList());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toArrayList(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toArrayList(ListCollectors.ListFlavor.UNMODIFIABLE, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toArrayList(null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toArrayList((ListCollectors.ListFlavor) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toArrayList((Function<? super Integer, ?>) null)));
    }
    
    /**
     * JUnit test of toUnmodifiableArrayList.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toUnmodifiableArrayList(Function)
     * @see ListCollectors#toUnmodifiableArrayList()
     */
    @Test
    public void testToUnmodifiableArrayList() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toUnmodifiableArrayList(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertEquals(UnmodifiableRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toUnmodifiableArrayList(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toUnmodifiableArrayList(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(UnmodifiableRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toUnmodifiableArrayList(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertEquals(UnmodifiableRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toUnmodifiableArrayList());
        Assert.assertNotNull(list1);
        Assert.assertEquals(UnmodifiableRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toUnmodifiableArrayList());
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toUnmodifiableArrayList());
        Assert.assertNotNull(list4);
        Assert.assertEquals(UnmodifiableRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toUnmodifiableArrayList());
        Assert.assertNotNull(list5);
        Assert.assertEquals(UnmodifiableRandomAccessList, list5.getClass());
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toUnmodifiableArrayList(Function.identity()),
                ListCollectors.toUnmodifiableArrayList(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toUnmodifiableArrayList(),
                ListCollectors.toUnmodifiableArrayList());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toUnmodifiableArrayList(null)));
    }
    
    /**
     * JUnit test of toSynchronizedArrayList.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toSynchronizedArrayList(Function)
     * @see ListCollectors#toSynchronizedArrayList()
     */
    @Test
    public void testToSynchronizedArrayList() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toSynchronizedArrayList(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertEquals(SynchronizedRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toSynchronizedArrayList(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(SynchronizedRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toSynchronizedArrayList(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toSynchronizedArrayList(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toSynchronizedArrayList());
        Assert.assertNotNull(list1);
        Assert.assertEquals(SynchronizedRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toSynchronizedArrayList());
        Assert.assertNotNull(list2);
        Assert.assertEquals(SynchronizedRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toSynchronizedArrayList());
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toSynchronizedArrayList());
        Assert.assertNotNull(list5);
        Assert.assertEquals(SynchronizedRandomAccessList, list5.getClass());
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toSynchronizedArrayList(Function.identity()),
                ListCollectors.toSynchronizedArrayList(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toSynchronizedArrayList(),
                ListCollectors.toSynchronizedArrayList());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toSynchronizedArrayList(null)));
    }
    
    /**
     * JUnit test of toLinkedList.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toLinkedList(ListCollectors.ListFlavor, Function)
     * @see ListCollectors#toLinkedList(ListCollectors.ListFlavor)
     * @see ListCollectors#toLinkedList(Function)
     * @see ListCollectors#toLinkedList()
     */
    @Test
    public void testToLinkedList() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toLinkedList(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof LinkedList);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toLinkedList(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof LinkedList);
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toLinkedList(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertTrue(list3 instanceof LinkedList);
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toLinkedList(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof LinkedList);
        TestUtils.assertListEquals(list4, testList4);
        
        //flavor
        list1 = testElements1.stream().collect(ListCollectors.toLinkedList(ListCollectors.ListFlavor.STANDARD,
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof LinkedList);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toLinkedList(ListCollectors.ListFlavor.UNMODIFIABLE,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toLinkedList(ListCollectors.ListFlavor.SYNCHRONIZED,
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toLinkedList(ListCollectors.ListFlavor.STANDARD,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof LinkedList);
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toLinkedList());
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof LinkedList);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toLinkedList());
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof LinkedList);
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toLinkedList());
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof LinkedList);
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toLinkedList());
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof LinkedList);
        TestUtils.assertListEquals(list5, testElements4);
        
        //identity, flavor
        list1 = testElements1.stream().collect(ListCollectors.toLinkedList(ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof LinkedList);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toLinkedList(ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toLinkedList(ListCollectors.ListFlavor.SYNCHRONIZED));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedList, list3.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toLinkedList(ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof LinkedList);
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toLinkedList(ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()),
                ListCollectors.toLinkedList(ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toLinkedList(ListCollectors.ListFlavor.UNMODIFIABLE),
                ListCollectors.toLinkedList(ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                ListCollectors.toLinkedList(Function.identity()),
                ListCollectors.toLinkedList(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toLinkedList(),
                ListCollectors.toLinkedList());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toLinkedList(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toLinkedList(ListCollectors.ListFlavor.UNMODIFIABLE, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toLinkedList(null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toLinkedList((ListCollectors.ListFlavor) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toLinkedList((Function<? super Integer, ?>) null)));
    }
    
    /**
     * JUnit test of toUnmodifiableLinkedList.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toUnmodifiableLinkedList(Function)
     * @see ListCollectors#toUnmodifiableLinkedList()
     */
    @Test
    public void testToUnmodifiableLinkedList() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toUnmodifiableLinkedList(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertEquals(UnmodifiableList, list1.getClass());
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toUnmodifiableLinkedList(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toUnmodifiableLinkedList(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(UnmodifiableList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toUnmodifiableLinkedList(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertEquals(UnmodifiableList, list4.getClass());
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toUnmodifiableLinkedList());
        Assert.assertNotNull(list1);
        Assert.assertEquals(UnmodifiableList, list1.getClass());
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toUnmodifiableLinkedList());
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toUnmodifiableLinkedList());
        Assert.assertNotNull(list4);
        Assert.assertEquals(UnmodifiableList, list4.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toUnmodifiableLinkedList());
        Assert.assertNotNull(list5);
        Assert.assertEquals(UnmodifiableList, list5.getClass());
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toUnmodifiableLinkedList(Function.identity()),
                ListCollectors.toUnmodifiableLinkedList(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toUnmodifiableLinkedList(),
                ListCollectors.toUnmodifiableLinkedList());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toUnmodifiableLinkedList(null)));
    }
    
    /**
     * JUnit test of toSynchronizedLinkedList.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toSynchronizedLinkedList(Function)
     * @see ListCollectors#toSynchronizedLinkedList()
     */
    @Test
    public void testToSynchronizedLinkedList() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toSynchronizedLinkedList(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertEquals(SynchronizedList, list1.getClass());
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toSynchronizedLinkedList(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(SynchronizedList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toSynchronizedLinkedList(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toSynchronizedLinkedList(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedList, list4.getClass());
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toSynchronizedLinkedList());
        Assert.assertNotNull(list1);
        Assert.assertEquals(SynchronizedList, list1.getClass());
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toSynchronizedLinkedList());
        Assert.assertNotNull(list2);
        Assert.assertEquals(SynchronizedList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toSynchronizedLinkedList());
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedList, list4.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toSynchronizedLinkedList());
        Assert.assertNotNull(list5);
        Assert.assertEquals(SynchronizedList, list5.getClass());
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toSynchronizedLinkedList(Function.identity()),
                ListCollectors.toSynchronizedLinkedList(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toSynchronizedLinkedList(),
                ListCollectors.toSynchronizedLinkedList());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toSynchronizedLinkedList(null)));
    }
    
    /**
     * JUnit test of toStack.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toStack(ListCollectors.ListFlavor, Function)
     * @see ListCollectors#toStack(ListCollectors.ListFlavor)
     * @see ListCollectors#toStack(Function)
     * @see ListCollectors#toStack()
     */
    @Test
    public void testToStack() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toStack(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Stack);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toStack(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof Stack);
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toStack(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertTrue(list3 instanceof Stack);
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toStack(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Stack);
        TestUtils.assertListEquals(list4, testList4);
        
        //flavor
        list1 = testElements1.stream().collect(ListCollectors.toStack(ListCollectors.ListFlavor.STANDARD,
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Stack);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toStack(ListCollectors.ListFlavor.UNMODIFIABLE,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toStack(ListCollectors.ListFlavor.SYNCHRONIZED,
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toStack(ListCollectors.ListFlavor.STANDARD,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Stack);
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toStack());
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Stack);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toStack());
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof Stack);
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toStack());
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Stack);
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toStack());
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof Stack);
        TestUtils.assertListEquals(list5, testElements4);
        
        //identity, flavor
        list1 = testElements1.stream().collect(ListCollectors.toStack(ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Stack);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toStack(ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toStack(ListCollectors.ListFlavor.SYNCHRONIZED));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toStack(ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof Stack);
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toStack(ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()),
                ListCollectors.toStack(ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toStack(ListCollectors.ListFlavor.UNMODIFIABLE),
                ListCollectors.toStack(ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                ListCollectors.toStack(Function.identity()),
                ListCollectors.toStack(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toStack(),
                ListCollectors.toStack());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toStack(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toStack(ListCollectors.ListFlavor.UNMODIFIABLE, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toStack(null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toStack((ListCollectors.ListFlavor) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toStack((Function<? super Integer, ?>) null)));
    }
    
    /**
     * JUnit test of toUnmodifiableStack.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toUnmodifiableStack(Function)
     * @see ListCollectors#toUnmodifiableStack()
     */
    @Test
    public void testToUnmodifiableStack() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toUnmodifiableStack(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertEquals(UnmodifiableRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toUnmodifiableStack(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toUnmodifiableStack(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(UnmodifiableRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toUnmodifiableStack(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertEquals(UnmodifiableRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toUnmodifiableStack());
        Assert.assertNotNull(list1);
        Assert.assertEquals(UnmodifiableRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toUnmodifiableStack());
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toUnmodifiableStack());
        Assert.assertNotNull(list4);
        Assert.assertEquals(UnmodifiableRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toUnmodifiableStack());
        Assert.assertNotNull(list5);
        Assert.assertEquals(UnmodifiableRandomAccessList, list5.getClass());
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toUnmodifiableStack(Function.identity()),
                ListCollectors.toUnmodifiableStack(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toUnmodifiableStack(),
                ListCollectors.toUnmodifiableStack());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toUnmodifiableStack(null)));
    }
    
    /**
     * JUnit test of toSynchronizedStack.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toSynchronizedStack(Function)
     * @see ListCollectors#toSynchronizedStack()
     */
    @Test
    public void testToSynchronizedStack() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toSynchronizedStack(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertEquals(SynchronizedRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toSynchronizedStack(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(SynchronizedRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toSynchronizedStack(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toSynchronizedStack(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toSynchronizedStack());
        Assert.assertNotNull(list1);
        Assert.assertEquals(SynchronizedRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toSynchronizedStack());
        Assert.assertNotNull(list2);
        Assert.assertEquals(SynchronizedRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toSynchronizedStack());
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toSynchronizedStack());
        Assert.assertNotNull(list5);
        Assert.assertEquals(SynchronizedRandomAccessList, list5.getClass());
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toSynchronizedStack(Function.identity()),
                ListCollectors.toSynchronizedStack(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toSynchronizedStack(),
                ListCollectors.toSynchronizedStack());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toSynchronizedStack(null)));
    }
    
    /**
     * JUnit test of toVector.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toVector(ListCollectors.ListFlavor, Function)
     * @see ListCollectors#toVector(ListCollectors.ListFlavor)
     * @see ListCollectors#toVector(Function)
     * @see ListCollectors#toVector()
     */
    @Test
    public void testToVector() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toVector(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Vector);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toVector(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof Vector);
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toVector(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertTrue(list3 instanceof Vector);
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toVector(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Vector);
        TestUtils.assertListEquals(list4, testList4);
        
        //flavor
        list1 = testElements1.stream().collect(ListCollectors.toVector(ListCollectors.ListFlavor.STANDARD,
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Vector);
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toVector(ListCollectors.ListFlavor.UNMODIFIABLE,
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toVector(ListCollectors.ListFlavor.SYNCHRONIZED,
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toVector(ListCollectors.ListFlavor.STANDARD,
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Vector);
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toVector());
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Vector);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toVector());
        Assert.assertNotNull(list2);
        Assert.assertTrue(list2 instanceof Vector);
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toVector());
        Assert.assertNotNull(list4);
        Assert.assertTrue(list4 instanceof Vector);
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toVector());
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof Vector);
        TestUtils.assertListEquals(list5, testElements4);
        
        //identity, flavor
        list1 = testElements1.stream().collect(ListCollectors.toVector(ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list1);
        Assert.assertTrue(list1 instanceof Vector);
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toVector(ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toVector(ListCollectors.ListFlavor.SYNCHRONIZED));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toVector(ListCollectors.ListFlavor.STANDARD));
        Assert.assertNotNull(list5);
        Assert.assertTrue(list5 instanceof Vector);
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toVector(ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()),
                ListCollectors.toVector(ListCollectors.ListFlavor.UNMODIFIABLE, Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toVector(ListCollectors.ListFlavor.UNMODIFIABLE),
                ListCollectors.toVector(ListCollectors.ListFlavor.UNMODIFIABLE));
        Assert.assertNotSame(
                ListCollectors.toVector(Function.identity()),
                ListCollectors.toVector(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toVector(),
                ListCollectors.toVector());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toVector(null, Function.identity())));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toVector(ListCollectors.ListFlavor.UNMODIFIABLE, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toVector(null, null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toVector((ListCollectors.ListFlavor) null)));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toVector((Function<? super Integer, ?>) null)));
    }
    
    /**
     * JUnit test of toUnmodifiableVector.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toUnmodifiableVector(Function)
     * @see ListCollectors#toUnmodifiableVector()
     */
    @Test
    public void testToUnmodifiableVector() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toUnmodifiableVector(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertEquals(UnmodifiableRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toUnmodifiableVector(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toUnmodifiableVector(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(UnmodifiableRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toUnmodifiableVector(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertEquals(UnmodifiableRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toUnmodifiableVector());
        Assert.assertNotNull(list1);
        Assert.assertEquals(UnmodifiableRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toUnmodifiableVector());
        Assert.assertNotNull(list2);
        Assert.assertEquals(UnmodifiableRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toUnmodifiableVector());
        Assert.assertNotNull(list4);
        Assert.assertEquals(UnmodifiableRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toUnmodifiableVector());
        Assert.assertNotNull(list5);
        Assert.assertEquals(UnmodifiableRandomAccessList, list5.getClass());
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toUnmodifiableVector(Function.identity()),
                ListCollectors.toUnmodifiableVector(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toUnmodifiableVector(),
                ListCollectors.toUnmodifiableVector());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toUnmodifiableVector(null)));
    }
    
    /**
     * JUnit test of toSynchronizedVector.
     *
     * @throws Exception When there is an exception.
     * @see ListCollectors#toSynchronizedVector(Function)
     * @see ListCollectors#toSynchronizedVector()
     */
    @Test
    public void testToSynchronizedVector() throws Exception {
        final List<String> testElements1 = ((List<String>) testStreamElements[0]);
        final List<String> testElements2 = ((List<String>) testStreamElements[1]);
        final List<Integer> testElements3 = ((List<Integer>) testStreamElements[2]);
        final List<List<Object>> testElements4 = ((List<List<Object>>) testStreamElements[3]);
        final List<String> testList1 = (List<String>) testLists[0];
        final List<String> testList2 = (List<String>) testLists[1];
        final List<Boolean> testList3 = (List<Boolean>) testLists[2];
        final List<Integer> testList4 = (List<Integer>) testLists[3];
        List<String> list1;
        List<String> list2;
        List<Boolean> list3;
        List<Integer> list4;
        List<List<Object>> list5;
        
        //standard
        list1 = testElements1.stream().collect(ListCollectors.toSynchronizedVector(
                Function.identity()));
        Assert.assertNotNull(list1);
        Assert.assertEquals(SynchronizedRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testList1);
        list2 = testElements2.stream().collect(ListCollectors.toSynchronizedVector(
                (e -> StringUtility.tokenize(e, ":").get(e.length() / 10))));
        Assert.assertNotNull(list2);
        Assert.assertEquals(SynchronizedRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testList2);
        list3 = testElements3.stream().collect(ListCollectors.toSynchronizedVector(
                (e -> (e > 3))));
        Assert.assertNotNull(list3);
        Assert.assertEquals(SynchronizedRandomAccessList, list3.getClass());
        TestUtils.assertListEquals(list3, testList3);
        list4 = testElements4.stream().collect(ListCollectors.toSynchronizedVector(
                (e -> (int) e.get(0))));
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testList4);
        
        //identity
        list1 = testElements1.stream().collect(ListCollectors.toSynchronizedVector());
        Assert.assertNotNull(list1);
        Assert.assertEquals(SynchronizedRandomAccessList, list1.getClass());
        TestUtils.assertListEquals(list1, testElements1);
        list2 = testElements2.stream().collect(ListCollectors.toSynchronizedVector());
        Assert.assertNotNull(list2);
        Assert.assertEquals(SynchronizedRandomAccessList, list2.getClass());
        TestUtils.assertListEquals(list2, testElements2);
        list4 = testElements3.stream().collect(ListCollectors.toSynchronizedVector());
        Assert.assertNotNull(list4);
        Assert.assertEquals(SynchronizedRandomAccessList, list4.getClass());
        TestUtils.assertListEquals(list4, testElements3);
        list5 = testElements4.stream().collect(ListCollectors.toSynchronizedVector());
        Assert.assertNotNull(list5);
        Assert.assertEquals(SynchronizedRandomAccessList, list5.getClass());
        TestUtils.assertListEquals(list5, testElements4);
        
        //uniqueness
        Assert.assertNotSame(
                ListCollectors.toSynchronizedVector(Function.identity()),
                ListCollectors.toSynchronizedVector(Function.identity()));
        Assert.assertNotSame(
                ListCollectors.toSynchronizedVector(),
                ListCollectors.toSynchronizedVector());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).collect(ListCollectors.toSynchronizedVector(null)));
    }
    
}
