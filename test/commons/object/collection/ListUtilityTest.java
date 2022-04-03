/*
 * File:    ListUtilityTest.java
 * Package: commons.object.collection
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.stream.Collectors;

import commons.test.TestAccess;
import commons.test.TestUtils;
import org.json.simple.JSONObject;
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
 * JUnit test of ListUtility.
 *
 * @see ListUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ListUtility.class})
public class ListUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ListUtilityTest.class);
    
    
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
     * @see ListUtility#DEFAULT_LIST_CLASS
     */
    @Test
    public void testConstants() throws Exception {
        Assert.assertEquals(ArrayList.class, TestAccess.getFieldValue(ListUtility.class, "DEFAULT_LIST_CLASS"));
    }
    
    /**
     * JUnit test of emptyList.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#emptyList(Class)
     * @see ListUtility#emptyList()
     */
    @Test
    public void testEmptyList() throws Exception {
        List<?> list;
        
        //standard
        list = ListUtility.emptyList(ArrayList.class);
        Assert.assertNotNull(list);
        Assert.assertTrue(list instanceof ArrayList);
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.emptyList(LinkedList.class);
        Assert.assertNotNull(list);
        Assert.assertTrue(list instanceof LinkedList);
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.emptyList(Stack.class);
        Assert.assertNotNull(list);
        Assert.assertTrue(list instanceof Stack);
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.emptyList(Vector.class);
        Assert.assertNotNull(list);
        Assert.assertTrue(list instanceof Vector);
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.emptyList(AbstractList.class);
        Assert.assertNotNull(list);
        Assert.assertTrue(list instanceof ArrayList);
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.emptyList();
        Assert.assertNotNull(list);
        Assert.assertTrue(list instanceof ArrayList);
        Assert.assertTrue(list.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.emptyList(null));
    }
    
    /**
     * JUnit test of unmodifiableList.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#unmodifiableList(List)
     * @see ListUtility#unmodifiableList(Class)
     * @see ListUtility#unmodifiableList()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testUnmodifiableList() throws Exception {
        final Class<?> UnmodifiableList = TestAccess.getClass(Collections.class, "UnmodifiableList");
        final Class<?> UnmodifiableRandomAccessList = TestAccess.getClass(Collections.class, "UnmodifiableRandomAccessList");
        List<?> list;
        
        //standard
        List<Object> testList = ListUtility.listOf("", 54, new ArithmeticException(), new HashMap<>(), new Object());
        List<Object> unmodifiableList = ListUtility.unmodifiableList(testList);
        Assert.assertNotNull(unmodifiableList);
        Assert.assertEquals(UnmodifiableRandomAccessList, unmodifiableList.getClass());
        TestUtils.assertListEquals(unmodifiableList, testList);
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                unmodifiableList.add("test"));
        
        //new
        list = ListUtility.unmodifiableList(ArrayList.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(UnmodifiableRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.unmodifiableList(LinkedList.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(UnmodifiableList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.unmodifiableList(Stack.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(UnmodifiableRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.unmodifiableList(Vector.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(UnmodifiableRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.unmodifiableList(AbstractList.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(UnmodifiableRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.unmodifiableList();
        Assert.assertNotNull(list);
        Assert.assertEquals(UnmodifiableRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.unmodifiableList((List<?>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.unmodifiableList((Class<? extends List<Object>>) null));
    }
    
    /**
     * JUnit test of synchronizedList.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#synchronizedList(List)
     * @see ListUtility#synchronizedList(Class)
     * @see ListUtility#synchronizedList()
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testSynchronizedList() throws Exception {
        final Class<?> SynchronizedList = TestAccess.getClass(Collections.class, "SynchronizedList");
        final Class<?> SynchronizedRandomAccessList = TestAccess.getClass(Collections.class, "SynchronizedRandomAccessList");
        List<?> list;
        
        //standard
        List<Object> testList = ListUtility.listOf("", 54, new ArithmeticException(), new HashMap<>(), new Object());
        List<Object> synchronizedList = ListUtility.synchronizedList(testList);
        Assert.assertNotNull(synchronizedList);
        Assert.assertEquals(SynchronizedRandomAccessList, synchronizedList.getClass());
        TestUtils.assertListEquals(synchronizedList, testList);
        
        //new
        list = ListUtility.synchronizedList(ArrayList.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(SynchronizedRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.synchronizedList(LinkedList.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(SynchronizedList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.synchronizedList(Stack.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(SynchronizedRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.synchronizedList(Vector.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(SynchronizedRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.synchronizedList(AbstractList.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(SynchronizedRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        list = ListUtility.synchronizedList();
        Assert.assertNotNull(list);
        Assert.assertEquals(SynchronizedRandomAccessList, list.getClass());
        Assert.assertTrue(list.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.synchronizedList((List<?>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.synchronizedList((Class<? extends List<Object>>) null));
    }
    
    /**
     * JUnit test of create.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#create(Class, Class, Object, int)
     * @see ListUtility#create(Class, Class, int)
     * @see ListUtility#create(Class, Object, int)
     * @see ListUtility#create(Class, Class)
     * @see ListUtility#create(Class, int)
     * @see ListUtility#create(Object, int)
     * @see ListUtility#create(Class)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate() throws Exception {
        //boolean
        List<Boolean> booleanList = TestAccess.invokeMethod(ListUtility.class, List.class, "create", ArrayList.class, Boolean.class, true, 5);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create(LinkedList.class, Boolean.class, 5);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof LinkedList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(Objects::isNull));
        booleanList = ListUtility.create(Stack.class, true, 5);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof Stack);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create(Vector.class, Boolean.class);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof Vector);
        Assert.assertEquals(0, booleanList.size());
        booleanList = ListUtility.create(Boolean.class, 5);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(Objects::isNull));
        booleanList = ListUtility.create(true, 5);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create(Boolean.class);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(0, booleanList.size());
        
        //int
        List<Integer> integerList = TestAccess.invokeMethod(ListUtility.class, List.class, "create", ArrayList.class, Integer.class, 18, 7);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create(LinkedList.class, Integer.class, 7);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof LinkedList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(Objects::isNull));
        integerList = ListUtility.create(Stack.class, 18, 7);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof Stack);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create(Vector.class, Integer.class);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof Vector);
        Assert.assertEquals(0, integerList.size());
        integerList = ListUtility.create(Integer.class, 7);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(Objects::isNull));
        integerList = ListUtility.create(18, 7);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create(Integer.class);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(0, integerList.size());
        
        //float
        List<Float> floatList = TestAccess.invokeMethod(ListUtility.class, List.class, "create", ArrayList.class, Float.class, 6.847f, 8);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create(LinkedList.class, Float.class, 8);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof LinkedList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(Objects::isNull));
        floatList = ListUtility.create(Stack.class, 6.847f, 8);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof Stack);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create(Vector.class, Float.class);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof Vector);
        Assert.assertEquals(0, floatList.size());
        floatList = ListUtility.create(Float.class, 8);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(Objects::isNull));
        floatList = ListUtility.create(6.847f, 8);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create(Float.class);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(0, floatList.size());
        
        //double
        List<Double> doubleList = TestAccess.invokeMethod(ListUtility.class, List.class, "create", ArrayList.class, Double.class, 117.4984560456d, 8);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create(LinkedList.class, Double.class, 8);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof LinkedList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(Objects::isNull));
        doubleList = ListUtility.create(Stack.class, 117.4984560456d, 8);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof Stack);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create(Vector.class, Double.class);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof Vector);
        Assert.assertEquals(0, doubleList.size());
        doubleList = ListUtility.create(Double.class, 8);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(Objects::isNull));
        doubleList = ListUtility.create(117.4984560456d, 8);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create(Double.class);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(0, doubleList.size());
        
        //long
        List<Long> longList = TestAccess.invokeMethod(ListUtility.class, List.class, "create", ArrayList.class, Long.class, 178984654231545L, 7);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create(LinkedList.class, Long.class, 7);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof LinkedList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(Objects::isNull));
        longList = ListUtility.create(Stack.class, 178984654231545L, 7);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof Stack);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create(Vector.class, Long.class);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof Vector);
        Assert.assertEquals(0, longList.size());
        longList = ListUtility.create(Long.class, 7);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(Objects::isNull));
        longList = ListUtility.create(178984654231545L, 7);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create(Long.class);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(0, longList.size());
        
        //object
        final Object testObject = new StringBuilder();
        List<Object> objectList = TestAccess.invokeMethod(ListUtility.class, List.class, "create", ArrayList.class, Object.class, testObject, 5);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create(LinkedList.class, Object.class, 5);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof LinkedList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(Objects::isNull));
        objectList = ListUtility.create(Stack.class, testObject, 5);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof Stack);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create(Vector.class, Object.class);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof Vector);
        Assert.assertEquals(0, objectList.size());
        objectList = ListUtility.create(Object.class, 5);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(Objects::isNull));
        objectList = ListUtility.create(testObject, 5);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create(Object.class);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(0, objectList.size());
        
        //invalid
        TestUtils.assertNoException(() ->
                ListUtility.create(Object.class, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create(ArrayList.class, Object.class, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create(LinkedList.class, Object.class, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create(Vector.class, Object.class, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create(Stack.class, Object.class, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create(18, -1));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create((Class<? extends List<?>>) null, Object.class, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create((Class<? extends List<?>>) null, 18, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create((Class<? extends List<?>>) null, (Object) null, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create((Class<? extends List<?>>) null, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create((Class<? extends List<?>>) null, null));
        TestUtils.assertNoException(() ->
                ListUtility.create((Class<?>) null, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create((Object) null, 6));
        TestUtils.assertNoException(() ->
                ListUtility.create(null));
    }
    
    /**
     * JUnit test of create2D.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#create2D(Class, Class, Object, int, int)
     * @see ListUtility#create2D(Class, Class, int, int)
     * @see ListUtility#create2D(Class, Object, int, int)
     * @see ListUtility#create2D(Class, Class)
     * @see ListUtility#create2D(Class, int, int)
     * @see ListUtility#create2D(Object, int, int)
     * @see ListUtility#create2D(Class)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate2D() throws Exception {
        //boolean
        List<List<Boolean>> booleanList = TestAccess.invokeMethod(ListUtility.class, List.class, "create2D", ArrayList.class, Boolean.class, true, 5, 4);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create2D(LinkedList.class, Boolean.class, 5, 4);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof LinkedList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        booleanList = ListUtility.create2D(Stack.class, true, 5, 4);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof Stack);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create2D(Vector.class, Boolean.class);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof Vector);
        Assert.assertEquals(0, booleanList.size());
        booleanList = ListUtility.create2D(Boolean.class, 5, 4);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        booleanList = ListUtility.create2D(true, 5, 4);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create2D(Boolean.class);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(0, booleanList.size());
        
        //int
        List<List<Integer>> integerList = TestAccess.invokeMethod(ListUtility.class, List.class, "create2D", ArrayList.class, Integer.class, 18, 7, 6);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create2D(LinkedList.class, Integer.class, 7, 6);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof LinkedList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        integerList = ListUtility.create2D(Stack.class, 18, 7, 6);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof Stack);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create2D(Vector.class, Integer.class);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof Vector);
        Assert.assertEquals(0, integerList.size());
        integerList = ListUtility.create2D(Integer.class, 7, 6);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        integerList = ListUtility.create2D(18, 7, 6);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create2D(Integer.class);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(0, integerList.size());
        
        //float
        List<List<Float>> floatList = TestAccess.invokeMethod(ListUtility.class, List.class, "create2D", ArrayList.class, Float.class, 6.847f, 8, 7);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create2D(LinkedList.class, Float.class, 8, 7);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof LinkedList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        floatList = ListUtility.create2D(Stack.class, 6.847f, 8, 7);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof Stack);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create2D(Vector.class, Float.class);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof Vector);
        Assert.assertEquals(0, floatList.size());
        floatList = ListUtility.create2D(Float.class, 8, 7);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        floatList = ListUtility.create2D(6.847f, 8, 7);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create2D(Float.class);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(0, floatList.size());
        
        //double
        List<List<Double>> doubleList = TestAccess.invokeMethod(ListUtility.class, List.class, "create2D", ArrayList.class, Double.class, 117.4984560456d, 8, 7);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create2D(LinkedList.class, Double.class, 8, 7);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof LinkedList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        doubleList = ListUtility.create2D(Stack.class, 117.4984560456d, 8, 7);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof Stack);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create2D(Vector.class, Double.class);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof Vector);
        Assert.assertEquals(0, doubleList.size());
        doubleList = ListUtility.create2D(Double.class, 8, 7);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        doubleList = ListUtility.create2D(117.4984560456d, 8, 7);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create2D(Double.class);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(0, doubleList.size());
        
        //long
        List<List<Long>> longList = TestAccess.invokeMethod(ListUtility.class, List.class, "create2D", ArrayList.class, Long.class, 178984654231545L, 7, 6);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create2D(LinkedList.class, Long.class, 7, 6);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof LinkedList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        longList = ListUtility.create2D(Stack.class, 178984654231545L, 7, 6);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof Stack);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create2D(Vector.class, Long.class);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof Vector);
        Assert.assertEquals(0, longList.size());
        longList = ListUtility.create2D(Long.class, 7, 6);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        longList = ListUtility.create2D(178984654231545L, 7, 6);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create2D(Long.class);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(0, longList.size());
        
        //object
        final Object testObject = new StringBuilder();
        List<List<Object>> objectList = TestAccess.invokeMethod(ListUtility.class, List.class, "create2D", ArrayList.class, Object.class, testObject, 5, 4);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create2D(LinkedList.class, Object.class, 5, 4);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof LinkedList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        objectList = ListUtility.create2D(Stack.class, testObject, 5, 4);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof Stack);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create2D(Vector.class, Object.class);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof Vector);
        Assert.assertEquals(0, objectList.size());
        objectList = ListUtility.create2D(Object.class, 5, 4);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        objectList = ListUtility.create2D(testObject, 5, 4);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create2D(Object.class);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(0, objectList.size());
        
        //invalid
        TestUtils.assertNoException(() ->
                ListUtility.create2D(Object.class, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(Object.class, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(ArrayList.class, Object.class, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(ArrayList.class, Object.class, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(LinkedList.class, Object.class, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(LinkedList.class, Object.class, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(Vector.class, Object.class, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(Vector.class, Object.class, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(Stack.class, Object.class, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(Stack.class, Object.class, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(18, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(18, 5, -1));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create2D((Class<? extends List<?>>) null, Object.class, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create2D((Class<? extends List<?>>) null, 18, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create2D((Class<? extends List<?>>) null, (Object) null, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create2D((Class<? extends List<?>>) null, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create2D((Class<? extends List<?>>) null, null));
        TestUtils.assertNoException(() ->
                ListUtility.create2D((Class<?>) null, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create2D((Object) null, 6, 6));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(null));
    }
    
    /**
     * JUnit test of create3D.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#create3D(Class, Class, Object, int, int, int)
     * @see ListUtility#create3D(Class, Class, int, int, int)
     * @see ListUtility#create3D(Class, Object, int, int, int)
     * @see ListUtility#create3D(Class, Class)
     * @see ListUtility#create3D(Class, int, int, int)
     * @see ListUtility#create3D(Object, int, int, int)
     * @see ListUtility#create3D(Class)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCreate3D() throws Exception {
        //boolean
        List<List<List<Boolean>>> booleanList = TestAccess.invokeMethod(ListUtility.class, List.class, "create3D", ArrayList.class, Boolean.class, true, 5, 4, 3);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create3D(LinkedList.class, Boolean.class, 5, 4, 3);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof LinkedList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        booleanList = ListUtility.create3D(Stack.class, true, 5, 4, 3);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof Stack);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(Stack.class::isInstance));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create3D(Vector.class, Boolean.class);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof Vector);
        Assert.assertEquals(0, booleanList.size());
        booleanList = ListUtility.create3D(Boolean.class, 5, 4, 3);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        booleanList = ListUtility.create3D(true, 5, 4, 3);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create3D(Boolean.class);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(0, booleanList.size());
        
        //int
        List<List<List<Integer>>> integerList = TestAccess.invokeMethod(ListUtility.class, List.class, "create3D", ArrayList.class, Integer.class, 18, 7, 6, 5);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create3D(LinkedList.class, Integer.class, 7, 6, 5);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof LinkedList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        integerList = ListUtility.create3D(Stack.class, 18, 7, 6, 5);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof Stack);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(Stack.class::isInstance));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create3D(Vector.class, Integer.class);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof Vector);
        Assert.assertEquals(0, integerList.size());
        integerList = ListUtility.create3D(Integer.class, 7, 6, 5);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        integerList = ListUtility.create3D(18, 7, 6, 5);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create3D(Integer.class);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(0, integerList.size());
        
        //float
        List<List<List<Float>>> floatList = TestAccess.invokeMethod(ListUtility.class, List.class, "create3D", ArrayList.class, Float.class, 6.847f, 8, 7, 6);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create3D(LinkedList.class, Float.class, 8, 7, 6);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof LinkedList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        floatList = ListUtility.create3D(Stack.class, 6.847f, 8, 7, 6);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof Stack);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(Stack.class::isInstance));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create3D(Vector.class, Float.class);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof Vector);
        Assert.assertEquals(0, floatList.size());
        floatList = ListUtility.create3D(Float.class, 8, 7, 6);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        floatList = ListUtility.create3D(6.847f, 8, 7, 6);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create3D(Float.class);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(0, floatList.size());
        
        //double
        List<List<List<Double>>> doubleList = TestAccess.invokeMethod(ListUtility.class, List.class, "create3D", ArrayList.class, Double.class, 117.4984560456d, 8, 7, 6);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create3D(LinkedList.class, Double.class, 8, 7, 6);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof LinkedList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        doubleList = ListUtility.create3D(Stack.class, 117.4984560456d, 8, 7, 6);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof Stack);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(Stack.class::isInstance));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create3D(Vector.class, Double.class);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof Vector);
        Assert.assertEquals(0, doubleList.size());
        doubleList = ListUtility.create3D(Double.class, 8, 7, 6);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        doubleList = ListUtility.create3D(117.4984560456d, 8, 7, 6);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create3D(Double.class);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(0, doubleList.size());
        
        //long
        List<List<List<Long>>> longList = TestAccess.invokeMethod(ListUtility.class, List.class, "create3D", ArrayList.class, Long.class, 178984654231545L, 7, 6, 5);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create3D(LinkedList.class, Long.class, 7, 6, 5);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof LinkedList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        longList = ListUtility.create3D(Stack.class, 178984654231545L, 7, 6, 5);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof Stack);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(Stack.class::isInstance));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create3D(Vector.class, Long.class);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof Vector);
        Assert.assertEquals(0, longList.size());
        longList = ListUtility.create3D(Long.class, 7, 6, 5);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        longList = ListUtility.create3D(178984654231545L, 7, 6, 5);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create3D(Long.class);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(0, longList.size());
        
        //object
        final Object testObject = new StringBuilder();
        List<List<List<Object>>> objectList = TestAccess.invokeMethod(ListUtility.class, List.class, "create3D", ArrayList.class, Long.class, testObject, 5, 4, 3);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create3D(LinkedList.class, Object.class, 5, 4, 3);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof LinkedList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(LinkedList.class::isInstance));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        objectList = ListUtility.create3D(Stack.class, testObject, 5, 4, 3);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof Stack);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(Stack.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(Stack.class::isInstance));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create3D(Vector.class, Object.class);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof Vector);
        Assert.assertEquals(0, objectList.size());
        objectList = ListUtility.create3D(Object.class, 5, 4, 3);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        objectList = ListUtility.create3D(testObject, 5, 4, 3);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(ArrayList.class::isInstance));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create3D(Object.class);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(0, objectList.size());
        
        //invalid
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Object.class, -1, 5, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Object.class, 5, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Object.class, 5, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(ArrayList.class, Object.class, -1, 5, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(ArrayList.class, Object.class, 5, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(ArrayList.class, Object.class, 5, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(LinkedList.class, Object.class, -1, 5, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(LinkedList.class, Object.class, 5, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(LinkedList.class, Object.class, 5, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Vector.class, Object.class, -1, 5, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Vector.class, Object.class, 5, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Vector.class, Object.class, 5, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Stack.class, Object.class, -1, 5, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Stack.class, Object.class, 5, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Stack.class, Object.class, 5, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(18, -1, 5, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(18, 5, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(18, 5, 5, -1));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create3D((Class<? extends List<?>>) null, Object.class, 6, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create3D((Class<? extends List<?>>) null, 18, 6, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create3D((Class<? extends List<?>>) null, (Object) null, 6, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create3D((Class<? extends List<?>>) null, Object.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create3D((Class<? extends List<?>>) null, null));
        TestUtils.assertNoException(() ->
                ListUtility.create3D((Class<?>) null, 6, 6, 6));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.create3D((Object) null, 6, 6, 6));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(null));
    }
    
    /**
     * JUnit test of listOf.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#listOf(Class, Object[])
     * @see ListUtility#listOf(Object[])
     */
    @Test
    public void testListOf() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.listOf(ArrayList.class, true, false, false, true, false);
        Assert.assertTrue(booleanList instanceof ArrayList);
        TestUtils.assertListEquals(booleanList, booleanArray);
        booleanList = ListUtility.listOf(true, false, false, true, false);
        Assert.assertTrue(booleanList instanceof ArrayList);
        TestUtils.assertListEquals(booleanList, booleanArray);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.listOf(ArrayList.class, 15, 312, 48, 5, -4, -9, 6);
        Assert.assertTrue(integerList instanceof ArrayList);
        TestUtils.assertListEquals(integerList, integerArray);
        integerList = ListUtility.listOf(15, 312, 48, 5, -4, -9, 6);
        Assert.assertTrue(integerList instanceof ArrayList);
        TestUtils.assertListEquals(integerList, integerArray);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.listOf(LinkedList.class, 15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f);
        Assert.assertTrue(floatList instanceof LinkedList);
        TestUtils.assertListEquals(floatList, floatArray);
        floatList = ListUtility.listOf(15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f);
        Assert.assertTrue(floatList instanceof ArrayList);
        TestUtils.assertListEquals(floatList, floatArray);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.listOf(Stack.class, 15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d);
        Assert.assertTrue(doubleList instanceof Stack);
        TestUtils.assertListEquals(doubleList, doubleArray);
        doubleList = ListUtility.listOf(15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d);
        Assert.assertTrue(doubleList instanceof ArrayList);
        TestUtils.assertListEquals(doubleList, doubleArray);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.listOf(Vector.class, 15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L);
        Assert.assertTrue(longList instanceof Vector);
        TestUtils.assertListEquals(longList, longArray);
        longList = ListUtility.listOf(15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L);
        Assert.assertTrue(longList instanceof ArrayList);
        TestUtils.assertListEquals(longList, longArray);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.listOf(ArrayList.class, objectArray[0], objectArray[1], objectArray[2], objectArray[3], objectArray[4]);
        Assert.assertTrue(objectList instanceof ArrayList);
        TestUtils.assertListEquals(objectList, objectArray);
        objectList = ListUtility.listOf(objectArray[0], objectArray[1], objectArray[2], objectArray[3], objectArray[4]);
        Assert.assertTrue(objectList instanceof ArrayList);
        TestUtils.assertListEquals(objectList, objectArray);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.listOf((Object[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.listOf(null, (Object[]) null));
    }
    
    /**
     * JUnit test of toList.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#toList(Object[], Class)
     * @see ListUtility#toList(Object[])
     * @see ListUtility#toList(Collection, Class)
     * @see ListUtility#toList(Collection)
     */
    @Test
    public void testToList() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Collection<Boolean> booleanCollection = Set.of(true, false);
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Assert.assertTrue(booleanList instanceof ArrayList);
        TestUtils.assertListEquals(booleanList, booleanArray);
        booleanList = ListUtility.toList(booleanArray, ArrayList.class);
        Assert.assertTrue(booleanList instanceof ArrayList);
        TestUtils.assertListEquals(booleanList, booleanArray);
        booleanList = ListUtility.toList(booleanCollection);
        Assert.assertTrue(booleanList instanceof ArrayList);
        TestUtils.assertListEquals(booleanList, booleanCollection);
        booleanList = ListUtility.toList(booleanCollection, ArrayList.class);
        Assert.assertTrue(booleanList instanceof ArrayList);
        TestUtils.assertListEquals(booleanList, booleanCollection);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Collection<Integer> integerCollection = Set.of(15, 312, 48, 5, -4, -9, 6);
        List<Integer> integerList = ListUtility.toList(integerArray);
        Assert.assertTrue(integerList instanceof ArrayList);
        TestUtils.assertListEquals(integerList, integerArray);
        integerList = ListUtility.toList(integerArray, LinkedList.class);
        Assert.assertTrue(integerList instanceof LinkedList);
        TestUtils.assertListEquals(integerList, integerArray);
        integerList = ListUtility.toList(integerCollection);
        Assert.assertTrue(integerList instanceof ArrayList);
        TestUtils.assertListEquals(integerList, integerCollection);
        integerList = ListUtility.toList(integerCollection, LinkedList.class);
        Assert.assertTrue(integerList instanceof LinkedList);
        TestUtils.assertListEquals(integerList, integerCollection);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Collection<Float> floatCollection = Set.of(15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f);
        List<Float> floatList = ListUtility.toList(floatArray);
        Assert.assertTrue(floatList instanceof ArrayList);
        TestUtils.assertListEquals(floatList, floatArray);
        floatList = ListUtility.toList(floatArray, Vector.class);
        Assert.assertTrue(floatList instanceof Vector);
        TestUtils.assertListEquals(floatList, floatArray);
        floatList = ListUtility.toList(floatCollection);
        Assert.assertTrue(floatList instanceof ArrayList);
        TestUtils.assertListEquals(floatList, floatCollection);
        floatList = ListUtility.toList(floatCollection, Vector.class);
        Assert.assertTrue(floatList instanceof Vector);
        TestUtils.assertListEquals(floatList, floatCollection);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Collection<Double> doubleCollection = Set.of(15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d);
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Assert.assertTrue(doubleList instanceof ArrayList);
        TestUtils.assertListEquals(doubleList, doubleArray);
        doubleList = ListUtility.toList(doubleArray, Stack.class);
        Assert.assertTrue(doubleList instanceof Stack);
        TestUtils.assertListEquals(doubleList, doubleArray);
        doubleList = ListUtility.toList(doubleCollection);
        Assert.assertTrue(doubleList instanceof ArrayList);
        TestUtils.assertListEquals(doubleList, doubleCollection);
        doubleList = ListUtility.toList(doubleCollection, Stack.class);
        Assert.assertTrue(doubleList instanceof Stack);
        TestUtils.assertListEquals(doubleList, doubleCollection);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Collection<Long> longCollection = Set.of(15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L);
        List<Long> longList = ListUtility.toList(longArray);
        Assert.assertTrue(longList instanceof ArrayList);
        TestUtils.assertListEquals(longList, longArray);
        longList = ListUtility.toList(longArray, Vector.class);
        Assert.assertTrue(longList instanceof Vector);
        TestUtils.assertListEquals(longList, longArray);
        longList = ListUtility.toList(longCollection);
        Assert.assertTrue(longList instanceof ArrayList);
        TestUtils.assertListEquals(longList, longCollection);
        longList = ListUtility.toList(longCollection, Vector.class);
        Assert.assertTrue(longList instanceof Vector);
        TestUtils.assertListEquals(longList, longCollection);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Collection<Object> objectCollection = Set.of("", 54, objectArray[2], objectArray[3], objectArray[4]);
        List<Object> objectList = ListUtility.toList(objectArray);
        Assert.assertTrue(objectList instanceof ArrayList);
        TestUtils.assertListEquals(objectList, objectArray);
        objectList = ListUtility.toList(objectArray, LinkedList.class);
        Assert.assertTrue(objectList instanceof LinkedList);
        TestUtils.assertListEquals(objectList, objectArray);
        objectList = ListUtility.toList(objectCollection);
        Assert.assertTrue(objectList instanceof ArrayList);
        TestUtils.assertListEquals(objectList, objectCollection);
        objectList = ListUtility.toList(objectCollection, LinkedList.class);
        Assert.assertTrue(objectList instanceof LinkedList);
        TestUtils.assertListEquals(objectList, objectCollection);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList(objectArray, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList(objectCollection, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList((Object[]) null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList((Object[]) null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList((Collection<?>) null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList((Collection<?>) null));
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#clone(List)
     */
    @Test
    public void testClone() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanClone = ListUtility.clone(booleanList);
        Assert.assertTrue(booleanClone instanceof ArrayList);
        TestUtils.assertListEquals(booleanClone, booleanList);
        Assert.assertNotSame(booleanList, booleanClone);
        booleanList = ListUtility.toList(booleanArray, ArrayList.class);
        booleanClone = ListUtility.clone(booleanList);
        Assert.assertTrue(booleanClone instanceof ArrayList);
        TestUtils.assertListEquals(booleanClone, booleanList);
        Assert.assertNotSame(booleanList, booleanClone);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<Integer> integerClone = ListUtility.clone(integerList);
        Assert.assertTrue(integerClone instanceof ArrayList);
        TestUtils.assertListEquals(integerClone, integerList);
        Assert.assertNotSame(integerList, integerClone);
        integerList = ListUtility.toList(integerArray, LinkedList.class);
        integerClone = ListUtility.clone(integerList);
        Assert.assertTrue(integerClone instanceof LinkedList);
        TestUtils.assertListEquals(integerClone, integerList);
        Assert.assertNotSame(integerList, integerClone);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<Float> floatClone = ListUtility.clone(floatList);
        Assert.assertTrue(floatClone instanceof ArrayList);
        TestUtils.assertListEquals(floatClone, floatList);
        Assert.assertNotSame(floatList, floatClone);
        floatList = ListUtility.toList(floatArray, Vector.class);
        floatClone = ListUtility.clone(floatList);
        Assert.assertTrue(floatClone instanceof Vector);
        TestUtils.assertListEquals(floatClone, floatList);
        Assert.assertNotSame(floatList, floatClone);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<Double> doubleClone = ListUtility.clone(doubleList);
        Assert.assertTrue(doubleClone instanceof ArrayList);
        TestUtils.assertListEquals(doubleClone, doubleList);
        Assert.assertNotSame(doubleList, doubleClone);
        doubleList = ListUtility.toList(doubleArray, Stack.class);
        doubleClone = ListUtility.clone(doubleList);
        Assert.assertTrue(doubleClone instanceof Stack);
        TestUtils.assertListEquals(doubleClone, doubleList);
        Assert.assertNotSame(doubleList, doubleClone);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        List<Long> longClone = ListUtility.clone(longList);
        Assert.assertTrue(longClone instanceof ArrayList);
        TestUtils.assertListEquals(longClone, longList);
        Assert.assertNotSame(longList, longClone);
        longList = ListUtility.toList(longArray, Vector.class);
        longClone = ListUtility.clone(longList);
        Assert.assertTrue(longClone instanceof Vector);
        TestUtils.assertListEquals(longClone, longList);
        Assert.assertNotSame(longList, longClone);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectClone = ListUtility.clone(objectList);
        Assert.assertTrue(objectClone instanceof ArrayList);
        TestUtils.assertListEquals(objectClone, objectList);
        Assert.assertNotSame(objectList, objectClone);
        objectList = ListUtility.toList(objectArray, LinkedList.class);
        objectClone = ListUtility.clone(objectList);
        Assert.assertTrue(objectClone instanceof LinkedList);
        TestUtils.assertListEquals(objectClone, objectList);
        Assert.assertNotSame(objectList, objectClone);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.clone(null));
    }
    
    /**
     * JUnit test of cast.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#cast(List, Class)
     */
    @Test
    public void testCast() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanConverted = ListUtility.cast(booleanList, ArrayList.class);
        Assert.assertTrue(booleanConverted instanceof ArrayList);
        TestUtils.assertListEquals(booleanConverted, booleanList);
        Assert.assertSame(booleanList, booleanConverted);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerConverted = ListUtility.cast(integerList, ArrayList.class);
        Assert.assertTrue(integerConverted instanceof ArrayList);
        TestUtils.assertListEquals(integerConverted, integerList);
        Assert.assertSame(integerList, integerConverted);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, ArrayList.class);
        List<Float> floatConverted = ListUtility.cast(floatList, LinkedList.class);
        Assert.assertTrue(floatConverted instanceof LinkedList);
        TestUtils.assertListEquals(floatConverted, floatList);
        Assert.assertNotSame(floatList, floatConverted);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, LinkedList.class);
        List<Double> doubleConverted = ListUtility.cast(doubleList, Stack.class);
        Assert.assertTrue(doubleConverted instanceof Stack);
        TestUtils.assertListEquals(doubleConverted, doubleList);
        Assert.assertNotSame(doubleList, doubleConverted);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longConverted = ListUtility.cast(longList, LinkedList.class);
        Assert.assertTrue(longConverted instanceof LinkedList);
        TestUtils.assertListEquals(longConverted, longList);
        Assert.assertNotSame(longList, longConverted);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray, Stack.class);
        List<Object> objectConverted = ListUtility.cast(objectList, Vector.class);
        Assert.assertTrue(objectConverted instanceof Vector);
        TestUtils.assertListEquals(objectConverted, objectList);
        Assert.assertNotSame(objectList, objectConverted);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.cast(objectList, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.cast(null, ArrayList.class));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.cast(null, null));
    }
    
    /**
     * JUnit test of subList.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#subList(List, int, int)
     * @see ListUtility#subList(List, int)
     */
    @Test
    public void testSubList() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanSubList = ListUtility.subList(booleanList, 2, 4);
        Assert.assertTrue(booleanSubList instanceof ArrayList);
        TestUtils.assertListEquals(
                booleanSubList,
                new Boolean[] {false, true});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerSubList = ListUtility.subList(integerList, 2);
        Assert.assertTrue(integerSubList instanceof ArrayList);
        TestUtils.assertListEquals(
                integerSubList,
                new Integer[] {48, 5, -4, -9, 6});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatSubList = ListUtility.subList(floatList, 0, 4);
        Assert.assertTrue(floatSubList instanceof LinkedList);
        TestUtils.assertListEquals(
                floatSubList,
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleSubList = ListUtility.subList(doubleList, 0);
        Assert.assertTrue(doubleSubList instanceof Stack);
        TestUtils.assertListEquals(
                doubleSubList,
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longSubList = ListUtility.subList(longList, 6, 7);
        Assert.assertTrue(longSubList instanceof Vector);
        TestUtils.assertListEquals(
                longSubList,
                new Long[] {699546101L});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectSubList = ListUtility.subList(objectList, 1, 3);
        Assert.assertTrue(objectSubList instanceof ArrayList);
        TestUtils.assertListEquals(
                objectSubList,
                new Object[] {54, objectArray[2]});
        
        //empty
        List<Object> subList = ListUtility.subList(objectList, 0, 0);
        Assert.assertTrue(subList.isEmpty());
        
        //invalid
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [0,6) is out of bounds of the list", () ->
                ListUtility.subList(objectList, 0, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [-1,5) is out of bounds of the list", () ->
                ListUtility.subList(objectList, -1, 5));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [4,2) is out of bounds of the list", () ->
                ListUtility.subList(objectList, 4, 2));
        
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [6,5) is out of bounds of the list", () ->
                ListUtility.subList(objectList, 6));
        TestUtils.assertException(IndexOutOfBoundsException.class, "The range [-1,5) is out of bounds of the list", () ->
                ListUtility.subList(objectList, -1));
        
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.subList(null, 2, 4));
    }
    
    /**
     * JUnit test of merge.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#merge(List, List)
     */
    @Test
    public void testMerge() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanArray2 = new Boolean[] {true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanList2 = ListUtility.toList(booleanArray2);
        List<Boolean> booleanMergeList = ListUtility.merge(booleanList, booleanList2);
        Assert.assertTrue(booleanMergeList instanceof ArrayList);
        TestUtils.assertListEquals(
                booleanMergeList,
                new Boolean[] {true, false, false, true, false, true, false});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerArray2 = new Integer[] {15, 312, 48};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerList2 = ListUtility.toList(integerArray2, ArrayList.class);
        List<Integer> integerMergeList = ListUtility.merge(integerList, integerList2);
        Assert.assertTrue(integerMergeList instanceof ArrayList);
        TestUtils.assertListEquals(
                integerMergeList,
                new Integer[] {15, 312, 48, 5, -4, -9, 6, 15, 312, 48});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatArray2 = new Float[] {15.1f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatList2 = ListUtility.toList(floatArray2, ArrayList.class);
        List<Float> floatMergeList = ListUtility.merge(floatList, floatList2);
        Assert.assertTrue(floatMergeList instanceof LinkedList);
        TestUtils.assertListEquals(
                floatMergeList,
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f, 15.1f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleArray2 = new Double[] {-4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleList2 = ListUtility.toList(doubleArray2, Vector.class);
        List<Double> doubleMergeList = ListUtility.merge(doubleList, doubleList2);
        Assert.assertTrue(doubleMergeList instanceof Stack);
        TestUtils.assertListEquals(
                doubleMergeList,
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longArray2 = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longList2 = ListUtility.toList(longArray2);
        List<Long> longMergeList = ListUtility.merge(longList, longList2);
        Assert.assertTrue(longMergeList instanceof Vector);
        TestUtils.assertListEquals(
                longMergeList,
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L, 15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArray2 = new Object[] {54, objectArray[2], objectArray[4]};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectList2 = ListUtility.toList(objectArray2);
        List<Object> objectMergeList = ListUtility.merge(objectList, objectList2);
        Assert.assertTrue(objectMergeList instanceof ArrayList);
        TestUtils.assertListEquals(
                objectMergeList,
                new Object[] {"", 54, objectArray[2], objectArray[3], objectArray[4], 54, objectArray[2], objectArray[4]});
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.merge(objectList, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.merge(null, objectList2));
    }
    
    /**
     * JUnit test of split.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#split(List, int)
     */
    @Test
    public void testSplit() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<List<Boolean>> booleanSplitList = ListUtility.split(booleanList, 3);
        Assert.assertTrue(booleanSplitList instanceof ArrayList);
        Assert.assertTrue(booleanSplitList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertEquals(2, booleanSplitList.size());
        Assert.assertTrue(booleanSplitList.stream().allMatch(e -> (e.size() == 3)));
        TestUtils.assertListEquals(
                booleanSplitList.get(0),
                new Boolean[] {true, false, false});
        TestUtils.assertListEquals(
                booleanSplitList.get(1),
                new Boolean[] {true, false, null});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<List<Integer>> integerSplitList = ListUtility.split(integerList, 2);
        Assert.assertTrue(integerSplitList instanceof ArrayList);
        Assert.assertTrue(integerSplitList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertEquals(4, integerSplitList.size());
        Assert.assertTrue(integerSplitList.stream().allMatch(e -> (e.size() == 2)));
        TestUtils.assertListEquals(
                integerSplitList.get(0),
                new Integer[] {15, 312});
        TestUtils.assertListEquals(
                integerSplitList.get(1),
                new Integer[] {48, 5});
        TestUtils.assertListEquals(
                integerSplitList.get(2),
                new Integer[] {-4, -9});
        TestUtils.assertListEquals(
                integerSplitList.get(3),
                new Integer[] {6, null});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<List<Float>> floatSplitList = ListUtility.split(floatList, 4);
        Assert.assertTrue(floatSplitList instanceof LinkedList);
        Assert.assertTrue(floatSplitList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertEquals(2, floatSplitList.size());
        Assert.assertTrue(floatSplitList.stream().allMatch(e -> (e.size() == 4)));
        TestUtils.assertListEquals(
                floatSplitList.get(0),
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f});
        TestUtils.assertListEquals(
                floatSplitList.get(1),
                new Float[] {-4.006f, -9.7f, 6.99f, 19776.4f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<List<Double>> doubleSplitList = ListUtility.split(doubleList, 7);
        Assert.assertTrue(doubleSplitList instanceof Stack);
        Assert.assertTrue(doubleSplitList.stream().allMatch(Stack.class::isInstance));
        Assert.assertEquals(1, doubleSplitList.size());
        Assert.assertTrue(doubleSplitList.stream().allMatch(e -> (e.size() == 7)));
        TestUtils.assertListEquals(
                doubleSplitList.get(0),
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<List<Long>> longSplitList = ListUtility.split(longList, 1);
        Assert.assertTrue(longSplitList instanceof Vector);
        Assert.assertTrue(longSplitList.stream().allMatch(Vector.class::isInstance));
        Assert.assertEquals(7, longSplitList.size());
        Assert.assertTrue(longSplitList.stream().allMatch(e -> (e.size() == 1)));
        TestUtils.assertListEquals(
                longSplitList.get(0),
                new Long[] {15104564L});
        TestUtils.assertListEquals(
                longSplitList.get(1),
                new Long[] {3129113874L});
        TestUtils.assertListEquals(
                longSplitList.get(2),
                new Long[] {4800000015L});
        TestUtils.assertListEquals(
                longSplitList.get(3),
                new Long[] {5457894511L});
        TestUtils.assertListEquals(
                longSplitList.get(4),
                new Long[] {-4006005001L});
        TestUtils.assertListEquals(
                longSplitList.get(5),
                new Long[] {-970487745L});
        TestUtils.assertListEquals(
                longSplitList.get(6),
                new Long[] {699546101L});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<List<Object>> objectSplitList = ListUtility.split(objectList, 3);
        Assert.assertTrue(objectSplitList instanceof ArrayList);
        Assert.assertTrue(objectSplitList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertEquals(2, objectSplitList.size());
        Assert.assertTrue(objectSplitList.stream().allMatch(e -> (e.size() == 3)));
        TestUtils.assertListEquals(
                objectSplitList.get(0),
                new Object[] {"", 54, objectArray[2]});
        TestUtils.assertListEquals(
                objectSplitList.get(1),
                new Object[] {objectArray[3], objectArray[4], null});
        
        //invalid
        
        objectSplitList = ListUtility.split(objectList, 0);
        Assert.assertEquals(objectArray.length, objectSplitList.size());
        
        objectSplitList = ListUtility.split(objectList, -1);
        Assert.assertEquals(objectArray.length, objectSplitList.size());
        
        objectSplitList = ListUtility.split(objectList, 15613);
        Assert.assertEquals(1, objectSplitList.size());
        
        objectSplitList = ListUtility.split(ListUtility.emptyList(), 3);
        Assert.assertEquals(0, objectSplitList.size());
        
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.split(null, 3));
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#reverse(List)
     */
    @Test
    public void testReverse() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanReversedList = ListUtility.reverse(booleanList);
        Assert.assertTrue(booleanReversedList instanceof ArrayList);
        TestUtils.assertListEquals(
                booleanReversedList,
                new Boolean[] {false, true, false, false, true});
        Assert.assertNotSame(booleanList, booleanReversedList);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerReversedList = ListUtility.reverse(integerList);
        Assert.assertTrue(integerReversedList instanceof ArrayList);
        TestUtils.assertListEquals(
                integerReversedList,
                new Integer[] {6, -9, -4, 5, 48, 312, 15});
        Assert.assertNotSame(integerList, integerReversedList);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatReversedList = ListUtility.reverse(floatList);
        Assert.assertTrue(floatReversedList instanceof LinkedList);
        TestUtils.assertListEquals(
                floatReversedList,
                new Float[] {19776.4f, 6.99f, -9.7f, -4.006f, 5.45f, 48.0f, 312.91f, 15.1f});
        Assert.assertNotSame(floatList, floatReversedList);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleReversedList = ListUtility.reverse(doubleList);
        Assert.assertTrue(doubleReversedList instanceof Stack);
        TestUtils.assertListEquals(
                doubleReversedList,
                new Double[] {6.99546101d, -9.70487745d, -4.006005001d, 5.457894511d, 48.00000015d, 312.9113874d, 15.104564d});
        Assert.assertNotSame(doubleList, doubleReversedList);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longReversedList = ListUtility.reverse(longList);
        Assert.assertTrue(longReversedList instanceof Vector);
        TestUtils.assertListEquals(
                longReversedList,
                new Long[] {699546101L, -970487745L, -4006005001L, 5457894511L, 4800000015L, 3129113874L, 15104564L});
        Assert.assertNotSame(longList, longReversedList);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectReversedList = ListUtility.reverse(objectList);
        Assert.assertTrue(objectReversedList instanceof ArrayList);
        TestUtils.assertListEquals(
                objectReversedList,
                new Object[] {objectArray[4], objectArray[3], objectArray[2], 54, ""});
        Assert.assertNotSame(objectList, objectReversedList);
        
        //invalid
        
        List<Object> emptyReversedList = ListUtility.reverse(ListUtility.emptyList());
        Assert.assertTrue(emptyReversedList.isEmpty());
        
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.reverse(null));
    }
    
    /**
     * JUnit test of shuffle.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#shuffle(List)
     */
    @Test
    public void testShuffle() throws Exception {
        //boolean
        Boolean[] booleanArray = ArrayUtility.duplicateInOrder(new Boolean[] {true, false, false, true, false}, 10, Boolean.class);
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanShuffledList = ListUtility.shuffle(booleanList);
        Assert.assertTrue(booleanShuffledList instanceof ArrayList);
        TestUtils.assertListEquals(booleanShuffledList, booleanList, false);
        TestUtils.assertListNotEquals(booleanShuffledList, booleanList, true);
        
        //int
        Integer[] integerArray = ArrayUtility.duplicateInOrder(new Integer[] {15, 312, 48, 5, -4, -9, 6}, 10, Integer.class);
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerShuffledList = ListUtility.shuffle(integerList);
        Assert.assertTrue(integerShuffledList instanceof ArrayList);
        TestUtils.assertListEquals(integerShuffledList, integerList, false);
        TestUtils.assertListNotEquals(integerShuffledList, integerList, true);
        
        //float
        Float[] floatArray = ArrayUtility.duplicateInOrder(new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f}, 10, Float.class);
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatShuffledList = ListUtility.shuffle(floatList);
        Assert.assertTrue(floatShuffledList instanceof LinkedList);
        TestUtils.assertListEquals(floatShuffledList, floatList, false);
        TestUtils.assertListNotEquals(floatShuffledList, floatList, true);
        
        //double
        Double[] doubleArray = ArrayUtility.duplicateInOrder(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d}, 10, Double.class);
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleShuffledList = ListUtility.shuffle(doubleList);
        Assert.assertTrue(doubleShuffledList instanceof Stack);
        TestUtils.assertListEquals(doubleShuffledList, doubleList, false);
        TestUtils.assertListNotEquals(doubleShuffledList, doubleList, true);
        
        //long
        Long[] longArray = ArrayUtility.duplicateInOrder(new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L}, 10, Long.class);
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longShuffledList = ListUtility.shuffle(longList);
        Assert.assertTrue(longShuffledList instanceof Vector);
        TestUtils.assertListEquals(longShuffledList, longList, false);
        TestUtils.assertListNotEquals(longShuffledList, longList, true);
        
        //object
        Object[] objectArray = ArrayUtility.duplicateInOrder(new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()}, 10, Object.class);
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectShuffledList = ListUtility.shuffle(objectList);
        Assert.assertTrue(objectShuffledList instanceof ArrayList);
        TestUtils.assertListEquals(objectShuffledList, objectList, false);
        TestUtils.assertListNotEquals(objectShuffledList, objectList, true);
        
        //invalid
        
        List<Object> emptyShuffledList = ListUtility.shuffle(new ArrayList<>());
        Assert.assertTrue(emptyShuffledList.isEmpty());
        
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.shuffle(null));
    }
    
    /**
     * JUnit test of isNullOrEmpty.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#isNullOrEmpty(List)
     */
    @Test
    public void testIsNullOrEmpty() throws Exception {
        //standard
        Assert.assertFalse(ListUtility.isNullOrEmpty(ListUtility.listOf("test", "list")));
        
        //empty
        Assert.assertTrue(ListUtility.isNullOrEmpty(ListUtility.emptyList()));
        
        //null
        Assert.assertTrue(ListUtility.isNullOrEmpty(null));
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#equals(List, List, boolean)
     * @see ListUtility#equals(List, List)
     */
    @Test
    public void testEquals() throws Exception {
        //boolean
        Boolean[] booleanArray1 = new Boolean[] {true, false, false, true, false};
        Boolean[] booleanArray2 = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList1 = ListUtility.toList(booleanArray1);
        List<Boolean> booleanList2 = ListUtility.toList(booleanArray2);
        Assert.assertTrue(ListUtility.equals(booleanList1, booleanList2));
        Assert.assertTrue(ListUtility.equals(booleanList1, booleanList2, true));
        Assert.assertTrue(ListUtility.equals(booleanList1, booleanList2, false));
        Assert.assertTrue(ListUtility.equals(booleanList2, booleanList1));
        Assert.assertTrue(ListUtility.equals(booleanList2, booleanList1, true));
        Assert.assertTrue(ListUtility.equals(booleanList2, booleanList1, false));
        booleanArray1 = new Boolean[] {true, false, false, true, false};
        booleanArray2 = new Boolean[] {true, false};
        booleanList1 = ListUtility.toList(booleanArray1);
        booleanList2 = ListUtility.toList(booleanArray2);
        Assert.assertFalse(ListUtility.equals(booleanList1, booleanList2));
        Assert.assertFalse(ListUtility.equals(booleanList1, booleanList2, true));
        Assert.assertFalse(ListUtility.equals(booleanList1, booleanList2, false));
        Assert.assertFalse(ListUtility.equals(booleanList2, booleanList1));
        Assert.assertFalse(ListUtility.equals(booleanList2, booleanList1, true));
        Assert.assertFalse(ListUtility.equals(booleanList2, booleanList1, false));
        booleanArray1 = new Boolean[] {true, false, false, true, false};
        booleanArray2 = new Boolean[] {true, false, false, false, false};
        booleanList1 = ListUtility.toList(booleanArray1);
        booleanList2 = ListUtility.toList(booleanArray2);
        Assert.assertFalse(ListUtility.equals(booleanList1, booleanList2));
        Assert.assertFalse(ListUtility.equals(booleanList1, booleanList2, true));
        Assert.assertFalse(ListUtility.equals(booleanList1, booleanList2, false));
        Assert.assertFalse(ListUtility.equals(booleanList2, booleanList1));
        Assert.assertFalse(ListUtility.equals(booleanList2, booleanList1, true));
        Assert.assertFalse(ListUtility.equals(booleanList2, booleanList1, false));
        
        //int
        Integer[] integerArray1 = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerArray2 = new Integer[] {312, 48, 5, -4, -9, 6, 15};
        List<Integer> integerList1 = ListUtility.toList(integerArray1);
        List<Integer> integerList2 = ListUtility.toList(integerArray2);
        Assert.assertFalse(ListUtility.equals(integerList1, integerList2));
        Assert.assertFalse(ListUtility.equals(integerList1, integerList2, true));
        Assert.assertTrue(ListUtility.equals(integerList1, integerList2, false));
        Assert.assertFalse(ListUtility.equals(integerList2, integerList1));
        Assert.assertFalse(ListUtility.equals(integerList2, integerList1, true));
        Assert.assertTrue(ListUtility.equals(integerList2, integerList1, false));
        
        //float
        Float[] floatArray1 = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatArray2 = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f};
        List<Float> floatList1 = ListUtility.toList(floatArray1);
        List<Float> floatList2 = ListUtility.toList(floatArray2);
        Assert.assertFalse(ListUtility.equals(floatList1, floatList2));
        Assert.assertFalse(ListUtility.equals(floatList1, floatList2, true));
        Assert.assertFalse(ListUtility.equals(floatList1, floatList2, false));
        Assert.assertFalse(ListUtility.equals(floatList2, floatList1));
        Assert.assertFalse(ListUtility.equals(floatList2, floatList1, true));
        Assert.assertFalse(ListUtility.equals(floatList2, floatList1, false));
        
        //double
        Double[] doubleArray1 = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleArray2 = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList1 = ListUtility.toList(doubleArray2);
        List<Double> doubleList2 = ListUtility.toList(doubleArray2);
        Assert.assertTrue(ListUtility.equals(doubleList1, doubleList2));
        Assert.assertTrue(ListUtility.equals(doubleList1, doubleList2, true));
        Assert.assertTrue(ListUtility.equals(doubleList1, doubleList2, false));
        Assert.assertTrue(ListUtility.equals(doubleList2, doubleList1));
        Assert.assertTrue(ListUtility.equals(doubleList2, doubleList1, true));
        Assert.assertTrue(ListUtility.equals(doubleList2, doubleList1, false));
        
        //long
        Long[] longArray1 = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longArray2 = new Long[] {4800000015L, 15104564L, 3129113874L, 699546101L, 5457894511L, -4006005001L, -970487745L};
        List<Long> longList1 = ListUtility.toList(longArray1);
        List<Long> longList2 = ListUtility.toList(longArray2);
        Assert.assertFalse(ListUtility.equals(longList1, longList2));
        Assert.assertFalse(ListUtility.equals(longList1, longList2, true));
        Assert.assertTrue(ListUtility.equals(longList1, longList2, false));
        Assert.assertFalse(ListUtility.equals(longList2, longList1));
        Assert.assertFalse(ListUtility.equals(longList2, longList1, true));
        Assert.assertTrue(ListUtility.equals(longList2, longList1, false));
        
        //string
        String[] stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        String[] stringArray2 = new String[] {"Cat", "Dog", "Bird", "Lizard", "Fish"};
        List<String> stringList1 = ListUtility.toList(stringArray1);
        List<String> stringList2 = ListUtility.toList(stringArray2);
        Assert.assertFalse(ListUtility.equals(stringList1, stringList2));
        Assert.assertFalse(ListUtility.equals(stringList1, stringList2, true));
        Assert.assertFalse(ListUtility.equals(stringList1, stringList2, false));
        Assert.assertFalse(ListUtility.equals(stringList2, stringList1));
        Assert.assertFalse(ListUtility.equals(stringList2, stringList1, true));
        Assert.assertFalse(ListUtility.equals(stringList2, stringList1, false));
        
        //object
        Object[] objectArray1 = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArray2 = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList1 = ListUtility.toList(objectArray1);
        List<Object> objectList2 = ListUtility.toList(objectArray2);
        Assert.assertFalse(ListUtility.equals(objectList1, objectList2));
        Assert.assertFalse(ListUtility.equals(objectList1, objectList2, true));
        Assert.assertFalse(ListUtility.equals(objectList1, objectList2, false));
        Assert.assertFalse(ListUtility.equals(objectList2, objectList1));
        Assert.assertFalse(ListUtility.equals(objectList2, objectList1, true));
        Assert.assertFalse(ListUtility.equals(objectList2, objectList1, false));
        
        //invalid
        Assert.assertTrue(ListUtility.equals(new ArrayList<>(), new ArrayList<>()));
        Assert.assertTrue(ListUtility.equals(new ArrayList<>(), new ArrayList<>(), true));
        Assert.assertTrue(ListUtility.equals(new ArrayList<>(), new ArrayList<>(), false));
        Assert.assertFalse(ListUtility.equals(objectList1, null));
        Assert.assertFalse(ListUtility.equals(objectList1, null, true));
        Assert.assertFalse(ListUtility.equals(objectList1, null, false));
        Assert.assertFalse(ListUtility.equals(null, objectList2));
        Assert.assertFalse(ListUtility.equals(null, objectList2, true));
        Assert.assertFalse(ListUtility.equals(null, objectList2, false));
        Assert.assertTrue(ListUtility.equals(null, null));
        Assert.assertTrue(ListUtility.equals(null, null, true));
        Assert.assertTrue(ListUtility.equals(null, null, false));
    }
    
    /**
     * JUnit test of equalsIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#equalsIgnoreCase(List, List, boolean)
     * @see ListUtility#equalsIgnoreCase(List, List)
     */
    @Test
    public void testEqualsIgnoreCase() throws Exception {
        //standard
        String[] stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        String[] stringArray2 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        List<String> stringList1 = ListUtility.toList(stringArray1);
        List<String> stringList2 = ListUtility.toList(stringArray2);
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList1, stringList2));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList1, stringList2, true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList1, stringList2, false));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList2, stringList1));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList2, stringList1, true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList2, stringList1, false));
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"lizard", "dog", "fish", "cat", "bird"};
        stringList1 = ListUtility.toList(stringArray1);
        stringList2 = ListUtility.toList(stringArray2);
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2, true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList1, stringList2, false));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1, true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList2, stringList1, false));
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"dog", "bird", "lizard"};
        stringList1 = ListUtility.toList(stringArray1);
        stringList2 = ListUtility.toList(stringArray2);
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2, true));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2, false));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1, true));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1, false));
        
        //case
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"Cat", "DOG", "biRd", "LizARd", "FISh"};
        stringList1 = ListUtility.toList(stringArray1);
        stringList2 = ListUtility.toList(stringArray2);
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList1, stringList2));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList1, stringList2, true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList1, stringList2, false));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList2, stringList1));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList2, stringList1, true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList2, stringList1, false));
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"LizARd", "DOG", "FISh", "Cat", "biRd"};
        stringList1 = ListUtility.toList(stringArray1);
        stringList2 = ListUtility.toList(stringArray2);
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2, true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList1, stringList2, false));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1, true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(stringList2, stringList1, false));
        stringArray1 = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        stringArray2 = new String[] {"DOG", "biRd", "LizARd"};
        stringList1 = ListUtility.toList(stringArray1);
        stringList2 = ListUtility.toList(stringArray2);
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2, true));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, stringList2, false));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1, true));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList2, stringList1, false));
        
        //invalid
        Assert.assertTrue(ListUtility.equalsIgnoreCase(new ArrayList<>(), new ArrayList<>()));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(new ArrayList<>(), new ArrayList<>(), true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(new ArrayList<>(), new ArrayList<>(), false));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, null));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, null, true));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(stringList1, null, false));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(null, stringList2));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(null, stringList2, true));
        Assert.assertFalse(ListUtility.equalsIgnoreCase(null, stringList2, false));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(null, null));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(null, null, true));
        Assert.assertTrue(ListUtility.equalsIgnoreCase(null, null, false));
    }
    
    /**
     * JUnit test of addAndGet.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#addAndGet(List, Object)
     * @see ListUtility#addAndGet(List, int, Object)
     */
    @Test
    public void testAddAndGet() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        TestUtils.assertListEquals(
                ListUtility.addAndGet(booleanList, true),
                new Boolean[] {true, false, false, true, false, true});
        TestUtils.assertListEquals(
                ListUtility.addAndGet(booleanList, 0, false),
                new Boolean[] {false, true, false, false, true, false, true});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        TestUtils.assertListEquals(
                ListUtility.addAndGet(integerList, 18),
                new Integer[] {15, 312, 48, 5, -4, -9, 6, 18});
        TestUtils.assertListEquals(
                ListUtility.addAndGet(integerList, 3, -99),
                new Integer[] {15, 312, 48, -99, 5, -4, -9, 6, 18});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        TestUtils.assertListEquals(
                ListUtility.addAndGet(floatList, 6.0034f),
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f, 6.0034f});
        TestUtils.assertListEquals(
                ListUtility.addAndGet(floatList, 6, -0.77f),
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, -0.77f, 6.99f, 19776.4f, 6.0034f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        TestUtils.assertListEquals(
                ListUtility.addAndGet(doubleList, 111.78946044d),
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d, 111.78946044d});
        TestUtils.assertListEquals(
                ListUtility.addAndGet(doubleList, 1, -6.0897044603524d),
                new Double[] {15.104564d, -6.0897044603524d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d, 111.78946044d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        TestUtils.assertListEquals(
                ListUtility.addAndGet(longList, 890454440238L),
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L, 890454440238L});
        TestUtils.assertListEquals(
                ListUtility.addAndGet(longList, 5, 10000642399L),
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, 10000642399L, -970487745L, 699546101L, 890454440238L});
        
        //string
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        List<String> stringList = ListUtility.toList(stringArray);
        TestUtils.assertListEquals(
                ListUtility.addAndGet(stringList, "bat"),
                new String[] {"cat", "dog", "bird", "lizard", "fish", "bat"});
        TestUtils.assertListEquals(
                ListUtility.addAndGet(stringList, 6, "bug"),
                new String[] {"cat", "dog", "bird", "lizard", "fish", "bat", "bug"});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        TestUtils.assertListEquals(
                ListUtility.addAndGet(objectList, 't'),
                new Object[] {"", 54, objectArray[2], objectArray[3], objectArray[4], 't'});
        TestUtils.assertListEquals(
                ListUtility.addAndGet(objectList, 3, 15.99f),
                new Object[] {"", 54, objectArray[2], 15.99f, objectArray[3], objectArray[4], 't'});
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.addAndGet(null, 10));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.addAndGet(null, 0, 10));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index: 4, Size: 3", () ->
                ListUtility.addAndGet(ListUtility.listOf(6, 7, 3), 4, 10));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index: -1, Size: 3", () ->
                ListUtility.addAndGet(ListUtility.listOf(6, 7, 3), -1, 10));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                ListUtility.addAndGet(List.of(6, 7, 3), 10));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                ListUtility.addAndGet(List.of(6, 7, 3), 0, 10));
    }
    
    /**
     * JUnit test of addAllAndGet.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#addAllAndGet(List, Collection)
     * @see ListUtility#addAllAndGet(List, int, Collection)
     */
    @Test
    public void testAddAllAndGet() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(booleanList, List.of(true, true, false)),
                new Boolean[] {true, false, false, true, false, true, true, false});
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(booleanList, 0, List.of(false, false)),
                new Boolean[] {false, false, true, false, false, true, false, true, true, false});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(integerList, List.of(18)),
                new Integer[] {15, 312, 48, 5, -4, -9, 6, 18});
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(integerList, 3, List.of(-99, -98, -97)),
                new Integer[] {15, 312, 48, -99, -98, -97, 5, -4, -9, 6, 18});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(floatList, List.of(6.0034f, 81.9f)),
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f, 6.0034f, 81.9f});
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(floatList, 6, List.of(-0.77f, 0.0f, 896.7f, 1.8f)),
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, -0.77f, 0.0f, 896.7f, 1.8f, 6.99f, 19776.4f, 6.0034f, 81.9f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(doubleList, List.of(111.78946044d, 6.794044d, 77.742390d)),
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d, 111.78946044d, 6.794044d, 77.742390d});
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(doubleList, 1, List.of(-6.0897044603524d)),
                new Double[] {15.104564d, -6.0897044603524d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d, 111.78946044d, 6.794044d, 77.742390d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(longList, List.of(890454440238L, 187044L)),
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L, 890454440238L, 187044L});
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(longList, 5, List.of(10000642399L, 40987062699L, 9807890169L, 160355L)),
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, 10000642399L, 40987062699L, 9807890169L, 160355L, -970487745L, 699546101L, 890454440238L, 187044L});
        
        //string
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        List<String> stringList = ListUtility.toList(stringArray);
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(stringList, List.of("bat", "rat", "mouse")),
                new String[] {"cat", "dog", "bird", "lizard", "fish", "bat", "rat", "mouse"});
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(stringList, 8, List.of("bug", "turtle")),
                new String[] {"cat", "dog", "bird", "lizard", "fish", "bat", "rat", "mouse", "bug", "turtle"});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(objectList, List.of('t', 'e', "st")),
                new Object[] {"", 54, objectArray[2], objectArray[3], objectArray[4], 't', 'e', "st"});
        TestUtils.assertListEquals(
                ListUtility.addAllAndGet(objectList, 3, List.of(15.99f, 1904.5d, 98045L)),
                new Object[] {"", 54, objectArray[2], 15.99f, 1904.5d, 98045L, objectArray[3], objectArray[4], 't', 'e', "st"});
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.addAllAndGet(null, ListUtility.emptyList()));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.addAllAndGet(null, 0, ListUtility.emptyList()));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.addAllAndGet(ListUtility.listOf(6, 7, 3), null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.addAllAndGet(ListUtility.listOf(6, 7, 3), 0, null));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index: 4, Size: 3", () ->
                ListUtility.addAllAndGet(ListUtility.listOf(6, 7, 3), 4, ListUtility.emptyList()));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index: -1, Size: 3", () ->
                ListUtility.addAllAndGet(ListUtility.listOf(6, 7, 3), -1, ListUtility.emptyList()));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                ListUtility.addAllAndGet(List.of(6, 7, 3), ListUtility.emptyList()));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                ListUtility.addAllAndGet(List.of(6, 7, 3), 0, ListUtility.emptyList()));
    }
    
    /**
     * JUnit test of setAndGet.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#setAndGet(List, int, Object)
     */
    @Test
    public void testSetAndGet() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        TestUtils.assertListEquals(
                ListUtility.setAndGet(booleanList, 1, true),
                new Boolean[] {true, true, false, true, false});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        TestUtils.assertListEquals(
                ListUtility.setAndGet(integerList, 0, 18),
                new Integer[] {18, 312, 48, 5, -4, -9, 6});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        TestUtils.assertListEquals(
                ListUtility.setAndGet(floatList, 6, 6.0034f),
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.0034f, 19776.4f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        TestUtils.assertListEquals(
                ListUtility.setAndGet(doubleList, 3, 111.78946044d),
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 111.78946044d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        TestUtils.assertListEquals(
                ListUtility.setAndGet(longList, 5, 890454440238L),
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, 890454440238L, 699546101L});
        
        //string
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        List<String> stringList = ListUtility.toList(stringArray);
        TestUtils.assertListEquals(
                ListUtility.setAndGet(stringList, 4, "bat"),
                new String[] {"cat", "dog", "bird", "lizard", "bat"});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        TestUtils.assertListEquals(
                ListUtility.setAndGet(objectList, 3, 't'),
                new Object[] {"", 54, objectArray[2], 't', objectArray[4]});
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.setAndGet(null, 0, 10));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index 3 out of bounds for length 3", () ->
                ListUtility.setAndGet(ListUtility.listOf(6, 7, 3), 3, 10));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index -1 out of bounds for length 3", () ->
                ListUtility.setAndGet(ListUtility.listOf(6, 7, 3), -1, 10));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                ListUtility.setAndGet(List.of(6, 7, 3), 0, 10));
    }
    
    /**
     * JUnit test of removeAndGet.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#removeAndGet(List, Object)
     * @see ListUtility#removeAndGet(List, int)
     */
    @Test
    public void testRemoveAndGet() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(booleanList, true),
                new Boolean[] {false, false, true, false});
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(booleanList, 0),
                new Boolean[] {false, true, false});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(integerList, (Integer) 5),
                new Integer[] {15, 312, 48, -4, -9, 6});
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(integerList, 3),
                new Integer[] {15, 312, 48, -9, 6});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(floatList, 19776.4f),
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f});
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(floatList, 5),
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, 6.99f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(doubleList, 312.9113874d),
                new Double[] {15.104564d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d});
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(doubleList, 1),
                new Double[] {15.104564d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(longList, 15104564L),
                new Long[] {3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L});
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(longList, 3),
                new Long[] {3129113874L, 4800000015L, 5457894511L, -970487745L, 699546101L});
        
        //string
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        List<String> stringList = ListUtility.toList(stringArray);
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(stringList, "bat"),
                new String[] {"cat", "dog", "bird", "lizard", "fish"});
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(stringList, 3),
                new String[] {"cat", "dog", "bird", "fish"});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(objectList, objectArray[2]),
                new Object[] {"", 54, objectArray[3], objectArray[4]});
        TestUtils.assertListEquals(
                ListUtility.removeAndGet(objectList, 3),
                new Object[] {"", 54, objectArray[3]});
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.removeAndGet(null, (Object) 10));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.removeAndGet(null, 0));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index 4 out of bounds for length 3", () ->
                ListUtility.removeAndGet(ListUtility.listOf(6, 7, 3), 4));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index -1 out of bounds for length 3", () ->
                ListUtility.removeAndGet(ListUtility.listOf(6, 7, 3), -1));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                ListUtility.removeAndGet(List.of(6, 7, 3), (Object) 7));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                ListUtility.removeAndGet(List.of(6, 7, 3), 0));
    }
    
    /**
     * JUnit test of removeAllAndGet.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#removeAllAndGet(List, Collection)
     */
    @Test
    public void testRemoveAllAndGet() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        TestUtils.assertListEquals(
                ListUtility.removeAllAndGet(booleanList, List.of(true)),
                new Boolean[] {false, false, false});
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        TestUtils.assertListEquals(
                ListUtility.removeAllAndGet(integerList, List.of(5, 312, 48)),
                new Integer[] {15, -4, -9, 6});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        TestUtils.assertListEquals(
                ListUtility.removeAllAndGet(floatList, List.of(19776.4f, 5.45f, 15.1f, 6.99f, 8045.5f)),
                new Float[] {312.91f, 48.0f, -4.006f, -9.7f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        TestUtils.assertListEquals(
                ListUtility.removeAllAndGet(doubleList, List.of(312.9113874d, -4.006005001d)),
                new Double[] {15.104564d, 48.00000015d, 5.457894511d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        TestUtils.assertListEquals(
                ListUtility.removeAllAndGet(longList, List.of(98119846L, 116543033L, 809809877L)),
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L});
        
        //string
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        List<String> stringList = ListUtility.toList(stringArray);
        TestUtils.assertListEquals(
                ListUtility.removeAllAndGet(stringList, List.of("cat", "dog", "bird")),
                new String[] {"lizard", "fish"});
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        TestUtils.assertListEquals(
                ListUtility.removeAllAndGet(objectList, List.of(objectArray[2], objectArray[3], objectArray[4])),
                new Object[] {"", 54});
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.removeAllAndGet(null, ListUtility.emptyList()));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.removeAllAndGet(null, ListUtility.emptyList()));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.removeAllAndGet(ListUtility.listOf(6, 7, 3), null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.removeAllAndGet(ListUtility.listOf(6, 7, 3), null));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                ListUtility.removeAllAndGet(List.of(6, 7, 3), List.of(6, 3)));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                ListUtility.removeAllAndGet(List.of(6, 7, 3), ListUtility.emptyList()));
    }
    
    /**
     * JUnit test of contains.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#contains(List, Object)
     */
    @Test
    public void testContains() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Assert.assertTrue(ListUtility.contains(booleanList, true));
        Assert.assertTrue(ListUtility.contains(booleanList, false));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Assert.assertTrue(ListUtility.contains(integerList, 5));
        Assert.assertTrue(ListUtility.contains(integerList, -9));
        Assert.assertFalse(ListUtility.contains(integerList, 10));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Assert.assertTrue(ListUtility.contains(floatList, 312.91f));
        Assert.assertTrue(ListUtility.contains(floatList, -9.7f));
        Assert.assertFalse(ListUtility.contains(floatList, 123.8f));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Assert.assertTrue(ListUtility.contains(doubleList, 15.104564d));
        Assert.assertTrue(ListUtility.contains(doubleList, -4.006005001d));
        Assert.assertFalse(ListUtility.contains(doubleList, 8.6451001211d));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Assert.assertTrue(ListUtility.contains(longList, 3129113874L));
        Assert.assertTrue(ListUtility.contains(longList, 699546101L));
        Assert.assertFalse(ListUtility.contains(longList, 8465115960L));
        
        //string
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        List<String> stringList = ListUtility.toList(stringArray);
        Assert.assertTrue(ListUtility.contains(stringList, "cat"));
        Assert.assertTrue(ListUtility.contains(stringList, "lizard"));
        Assert.assertFalse(ListUtility.contains(stringList, "DOG"));
        Assert.assertFalse(ListUtility.contains(stringList, "rat"));
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArrayWithNull = new Object[] {"", 54, new ArithmeticException(), null};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectListWithNull = ListUtility.toList(objectArrayWithNull);
        Assert.assertTrue(ListUtility.contains(objectList, objectArray[2]));
        Assert.assertTrue(ListUtility.contains(objectList, objectArray[4]));
        Assert.assertFalse(ListUtility.contains(objectList, new ArrayList<>()));
        Assert.assertFalse(ListUtility.contains(objectList, null));
        Assert.assertTrue(ListUtility.contains(objectListWithNull, null));
        
        //invalid
        Assert.assertFalse(ListUtility.contains(null, new Object()));
        Assert.assertFalse(ListUtility.contains(null, null));
    }
    
    /**
     * JUnit test of containsIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#containsIgnoreCase(List, String)
     */
    @Test
    public void testContainsIgnoreCase() throws Exception {
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        String[] stringArrayWithNull = new String[] {"cat", null, "bird"};
        List<String> stringList = ListUtility.toList(stringArray);
        List<String> stringListWithNull = ListUtility.toList(stringArrayWithNull);
        
        //standard
        Assert.assertTrue(ListUtility.containsIgnoreCase(stringList, "cat"));
        Assert.assertTrue(ListUtility.containsIgnoreCase(stringList, "lizard"));
        Assert.assertTrue(ListUtility.containsIgnoreCase(stringList, "dog"));
        Assert.assertFalse(ListUtility.containsIgnoreCase(stringList, "rat"));
        
        //case
        Assert.assertTrue(ListUtility.containsIgnoreCase(stringList, "CAT"));
        Assert.assertTrue(ListUtility.containsIgnoreCase(stringList, "LIzArD"));
        Assert.assertTrue(ListUtility.containsIgnoreCase(stringList, "doG"));
        Assert.assertFalse(ListUtility.containsIgnoreCase(stringList, "rAt"));
        
        //null
        Assert.assertTrue(ListUtility.containsIgnoreCase(stringListWithNull, "birD"));
        Assert.assertTrue(ListUtility.containsIgnoreCase(stringListWithNull, null));
        
        //invalid
        Assert.assertFalse(ListUtility.containsIgnoreCase(null, ""));
        Assert.assertFalse(ListUtility.containsIgnoreCase(null, null));
    }
    
    /**
     * JUnit test of numberOfOccurrences.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#numberOfOccurrences(List, Object)
     */
    @Test
    public void testNumberOfOccurrences() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Assert.assertEquals(2, ListUtility.numberOfOccurrences(booleanList, true));
        Assert.assertEquals(3, ListUtility.numberOfOccurrences(booleanList, false));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -4, -4, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Assert.assertEquals(1, ListUtility.numberOfOccurrences(integerList, 15));
        Assert.assertEquals(4, ListUtility.numberOfOccurrences(integerList, -4));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Assert.assertEquals(1, ListUtility.numberOfOccurrences(floatList, 312.91f));
        Assert.assertEquals(0, ListUtility.numberOfOccurrences(floatList, 6.9999f));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Assert.assertEquals(1, ListUtility.numberOfOccurrences(doubleList, 312.9113874d));
        Assert.assertEquals(0, ListUtility.numberOfOccurrences(doubleList, 6.99d));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Assert.assertEquals(1, ListUtility.numberOfOccurrences(longList, 699546101L));
        Assert.assertEquals(0, ListUtility.numberOfOccurrences(longList, 0L));
        
        //string
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        List<String> stringList = ListUtility.toList(stringArray);
        Assert.assertEquals(1, ListUtility.numberOfOccurrences(stringList, "cat"));
        Assert.assertEquals(0, ListUtility.numberOfOccurrences(stringList, "CAT"));
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArrayWithNull = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object(), null, null};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectListWithNull = ListUtility.toList(objectArrayWithNull);
        Assert.assertEquals(1, ListUtility.numberOfOccurrences(objectList, ""));
        Assert.assertEquals(1, ListUtility.numberOfOccurrences(objectList, 54));
        Assert.assertEquals(0, ListUtility.numberOfOccurrences(objectList, new ArithmeticException()));
        Assert.assertEquals(1, ListUtility.numberOfOccurrences(objectList, new HashMap<>()));
        Assert.assertEquals(0, ListUtility.numberOfOccurrences(objectList, new Object()));
        Assert.assertEquals(0, ListUtility.numberOfOccurrences(objectList, null));
        Assert.assertEquals(2, ListUtility.numberOfOccurrences(objectListWithNull, null));
        
        //invalid
        Assert.assertEquals(0, ListUtility.numberOfOccurrences(null, null));
    }
    
    /**
     * JUnit test of numberOfOccurrencesIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#numberOfOccurrencesIgnoreCase(List, String)
     */
    @Test
    public void testNumberOfOccurrencesIgnoreCase() throws Exception {
        String[] stringArray = new String[] {"cat", "dog", "DOG", "bird", "lizard", "fish", "Fish", "fISh"};
        String[] stringArrayWithNull = new String[] {"cat", null, "bird"};
        List<String> stringList = ListUtility.toList(stringArray);
        List<String> stringListWithNull = ListUtility.toList(stringArrayWithNull);
        
        //standard
        Assert.assertEquals(1, ListUtility.numberOfOccurrencesIgnoreCase(stringList, "cat"));
        Assert.assertEquals(2, ListUtility.numberOfOccurrencesIgnoreCase(stringList, "dog"));
        Assert.assertEquals(3, ListUtility.numberOfOccurrencesIgnoreCase(stringList, "fish"));
        
        //case
        Assert.assertEquals(1, ListUtility.numberOfOccurrencesIgnoreCase(stringList, "CAT"));
        Assert.assertEquals(2, ListUtility.numberOfOccurrencesIgnoreCase(stringList, "dOg"));
        Assert.assertEquals(3, ListUtility.numberOfOccurrencesIgnoreCase(stringList, "fISH"));
        
        //null
        Assert.assertEquals(0, ListUtility.numberOfOccurrencesIgnoreCase(stringListWithNull, "LizARD"));
        Assert.assertEquals(1, ListUtility.numberOfOccurrencesIgnoreCase(stringListWithNull, null));
        
        //invalid
        Assert.assertEquals(0, ListUtility.numberOfOccurrencesIgnoreCase(null, ""));
        Assert.assertEquals(0, ListUtility.numberOfOccurrencesIgnoreCase(null, null));
    }
    
    /**
     * JUnit test of indexOf.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#indexOf(List, Object)
     */
    @Test
    public void testIndexOf() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Assert.assertEquals(0, ListUtility.indexOf(booleanList, true));
        Assert.assertEquals(1, ListUtility.indexOf(booleanList, false));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Assert.assertEquals(3, ListUtility.indexOf(integerList, 5));
        Assert.assertEquals(5, ListUtility.indexOf(integerList, -9));
        Assert.assertEquals(-1, ListUtility.indexOf(integerList, 10));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Assert.assertEquals(1, ListUtility.indexOf(floatList, 312.91f));
        Assert.assertEquals(5, ListUtility.indexOf(floatList, -9.7f));
        Assert.assertEquals(-1, ListUtility.indexOf(floatList, 123.8f));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Assert.assertEquals(0, ListUtility.indexOf(doubleList, 15.104564d));
        Assert.assertEquals(4, ListUtility.indexOf(doubleList, -4.006005001d));
        Assert.assertEquals(-1, ListUtility.indexOf(doubleList, 8.6451001211d));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Assert.assertEquals(1, ListUtility.indexOf(longList, 3129113874L));
        Assert.assertEquals(6, ListUtility.indexOf(longList, 699546101L));
        Assert.assertEquals(-1, ListUtility.indexOf(longList, 8465115960L));
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        Assert.assertEquals(2, ListUtility.indexOf(objectList, objectArray[2]));
        Assert.assertEquals(4, ListUtility.indexOf(objectList, objectArray[4]));
        Assert.assertEquals(-1, ListUtility.indexOf(objectList, new ArrayList<>()));
        
        //invalid
        Assert.assertEquals(-1, ListUtility.indexOf(null, new Object()));
        Assert.assertEquals(-1, ListUtility.indexOf(null, null));
    }
    
    /**
     * JUnit test of indexOfIgnoreCase.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#indexOfIgnoreCase(List, String)
     */
    @Test
    public void testIndexOfIgnoreCase() throws Exception {
        String[] stringArray = new String[] {"cat", "dog", "bird", "lizard", "fish"};
        String[] stringArrayWithNull = new String[] {"cat", null, "bird"};
        List<String> stringList = ListUtility.toList(stringArray);
        List<String> stringListWithNull = ListUtility.toList(stringArrayWithNull);
        
        //standard
        Assert.assertEquals(0, ListUtility.indexOfIgnoreCase(stringList, "cat"));
        Assert.assertEquals(3, ListUtility.indexOfIgnoreCase(stringList, "lizard"));
        Assert.assertEquals(1, ListUtility.indexOfIgnoreCase(stringList, "dog"));
        Assert.assertEquals(-1, ListUtility.indexOfIgnoreCase(stringList, "rat"));
        
        //case
        Assert.assertEquals(0, ListUtility.indexOfIgnoreCase(stringList, "CAT"));
        Assert.assertEquals(3, ListUtility.indexOfIgnoreCase(stringList, "LIzArD"));
        Assert.assertEquals(1, ListUtility.indexOfIgnoreCase(stringList, "doG"));
        Assert.assertEquals(-1, ListUtility.indexOfIgnoreCase(stringList, "rAt"));
        
        //null
        Assert.assertEquals(2, ListUtility.indexOfIgnoreCase(stringListWithNull, "birD"));
        Assert.assertEquals(1, ListUtility.indexOfIgnoreCase(stringListWithNull, null));
        
        //invalid
        Assert.assertEquals(-1, ListUtility.indexOfIgnoreCase(null, ""));
        Assert.assertEquals(-1, ListUtility.indexOfIgnoreCase(null, null));
    }
    
    /**
     * JUnit test of getOrDefault.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#getOrDefault(List, int, Object)
     */
    @Test
    public void testGetOrDefault() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Assert.assertEquals(false, ListUtility.getOrDefault(booleanList, 1, true));
        Assert.assertEquals(true, ListUtility.getOrDefault(booleanList, 3, false));
        Assert.assertEquals(true, ListUtility.getOrDefault(booleanList, 12, true));
        Assert.assertEquals(true, ListUtility.getOrDefault(booleanList, -1, true));
        Assert.assertEquals(true, ListUtility.getOrDefault(booleanList, 3, null));
        Assert.assertNull(ListUtility.getOrDefault(booleanList, 12, null));
        Assert.assertEquals(true, ListUtility.getOrDefault(null, 2, true));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Assert.assertEquals(312, ListUtility.getOrDefault(integerList, 1, 100).intValue());
        Assert.assertEquals(5, ListUtility.getOrDefault(integerList, 3, 100).intValue());
        Assert.assertEquals(100, ListUtility.getOrDefault(integerList, 12, 100).intValue());
        Assert.assertEquals(100, ListUtility.getOrDefault(integerList, -1, 100).intValue());
        Assert.assertEquals(5, ListUtility.getOrDefault(integerList, 3, null).intValue());
        Assert.assertNull(ListUtility.getOrDefault(integerList, 12, null));
        Assert.assertEquals(100, ListUtility.getOrDefault(null, 2, 100).intValue());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Assert.assertEquals(312.91f, ListUtility.getOrDefault(floatList, 1, 100.0f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(5.45f, ListUtility.getOrDefault(floatList, 3, 100.0f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(100.0f, ListUtility.getOrDefault(floatList, 12, 100.0f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(100.0f, ListUtility.getOrDefault(floatList, -1, 100.0f), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(5.45f, ListUtility.getOrDefault(floatList, 3, null), TestUtils.DELTA_FLOAT);
        Assert.assertNull(ListUtility.getOrDefault(floatList, 12, null));
        Assert.assertEquals(100.0f, ListUtility.getOrDefault(null, 2, 100.0f), TestUtils.DELTA_FLOAT);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Assert.assertEquals(312.9113874d, ListUtility.getOrDefault(doubleList, 1, 100.0d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(5.457894511d, ListUtility.getOrDefault(doubleList, 3, 100.0d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(100.0d, ListUtility.getOrDefault(doubleList, 12, 100.0d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(100.0d, ListUtility.getOrDefault(doubleList, -1, 100.0d), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(5.457894511d, ListUtility.getOrDefault(doubleList, 3, null), TestUtils.DELTA_DOUBLE);
        Assert.assertNull(ListUtility.getOrDefault(doubleList, 12, null));
        Assert.assertEquals(100.0d, ListUtility.getOrDefault(null, 2, 100.0d), TestUtils.DELTA_DOUBLE);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Assert.assertEquals(3129113874L, ListUtility.getOrDefault(longList, 1, 100L).longValue());
        Assert.assertEquals(5457894511L, ListUtility.getOrDefault(longList, 3, 100L).longValue());
        Assert.assertEquals(100L, ListUtility.getOrDefault(longList, 12, 100L).longValue());
        Assert.assertEquals(100L, ListUtility.getOrDefault(longList, -1, 100L).longValue());
        Assert.assertEquals(5457894511L, ListUtility.getOrDefault(longList, 3, null).longValue());
        Assert.assertNull(ListUtility.getOrDefault(longList, 12, null));
        Assert.assertEquals(100L, ListUtility.getOrDefault(null, 2, 100L).longValue());
        
        //object
        final Object testObject = new HashMap<>();
        final Object testObject2 = new JSONObject();
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), testObject, new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        Assert.assertEquals(54, ListUtility.getOrDefault(objectList, 1, testObject2));
        Assert.assertEquals(testObject, ListUtility.getOrDefault(objectList, 3, testObject2));
        Assert.assertEquals(testObject2, ListUtility.getOrDefault(objectList, 12, testObject2));
        Assert.assertEquals(testObject2, ListUtility.getOrDefault(objectList, -1, testObject2));
        Assert.assertEquals(testObject, ListUtility.getOrDefault(objectList, 3, null));
        Assert.assertNull(ListUtility.getOrDefault(objectList, 12, null));
        Assert.assertEquals(testObject2, ListUtility.getOrDefault(null, 2, testObject2));
    }
    
    /**
     * JUnit test of getOrNull.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#getOrNull(List, int)
     */
    @Test
    public void testGetOrNull() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Assert.assertEquals(false, ListUtility.getOrNull(booleanList, 1));
        Assert.assertEquals(true, ListUtility.getOrNull(booleanList, 3));
        Assert.assertNull(ListUtility.getOrNull(booleanList, 12));
        Assert.assertNull(ListUtility.getOrNull(booleanList, -1));
        Assert.assertNull(ListUtility.getOrNull(null, 2));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Assert.assertEquals(312, ListUtility.getOrNull(integerList, 1).intValue());
        Assert.assertEquals(5, ListUtility.getOrNull(integerList, 3).intValue());
        Assert.assertNull(ListUtility.getOrNull(integerList, 12));
        Assert.assertNull(ListUtility.getOrNull(integerList, -1));
        Assert.assertNull(ListUtility.getOrNull(null, 2));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Assert.assertEquals(312.91f, ListUtility.getOrNull(floatList, 1), TestUtils.DELTA_FLOAT);
        Assert.assertEquals(5.45f, ListUtility.getOrNull(floatList, 3), TestUtils.DELTA_FLOAT);
        Assert.assertNull(ListUtility.getOrNull(floatList, 12));
        Assert.assertNull(ListUtility.getOrNull(floatList, -1));
        Assert.assertNull(ListUtility.getOrNull(null, 2));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Assert.assertEquals(312.9113874d, ListUtility.getOrNull(doubleList, 1), TestUtils.DELTA_DOUBLE);
        Assert.assertEquals(5.457894511d, ListUtility.getOrNull(doubleList, 3), TestUtils.DELTA_DOUBLE);
        Assert.assertNull(ListUtility.getOrNull(doubleList, 12));
        Assert.assertNull(ListUtility.getOrNull(doubleList, -1));
        Assert.assertNull(ListUtility.getOrNull(null, 2));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Assert.assertEquals(3129113874L, ListUtility.getOrNull(longList, 1).longValue());
        Assert.assertEquals(5457894511L, ListUtility.getOrNull(longList, 3).longValue());
        Assert.assertNull(ListUtility.getOrNull(longList, 12));
        Assert.assertNull(ListUtility.getOrNull(longList, -1));
        Assert.assertNull(ListUtility.getOrNull(null, 2));
        
        //object
        final Object testObject = new HashMap<>();
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), testObject, new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        Assert.assertEquals(54, ListUtility.getOrNull(objectList, 1));
        Assert.assertEquals(testObject, ListUtility.getOrNull(objectList, 3));
        Assert.assertNull(ListUtility.getOrNull(objectList, 12));
        Assert.assertNull(ListUtility.getOrNull(objectList, -1));
        Assert.assertNull(ListUtility.getOrNull(null, 2));
    }
    
    /**
     * JUnit test of anyNull.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#anyNull(List)
     * @see ListUtility#anyNull(Object...)
     */
    @Test
    public void testAnyNull() throws Exception {
        Boolean a = true;
        Boolean b = null;
        Integer c = 5;
        Integer d = null;
        Float e = 3.6f;
        Float f = null;
        Double g = 48.56423004d;
        Double h = null;
        Long i = 1579843046840984L;
        Long j = null;
        String k = "something";
        String l = null;
        Object m = new Object();
        Object n = null;
        List<Object> list;
        Object[] array;
        
        //all
        array = new Object[] {a, b, c, d, e, f, g, h, i, j, k, l, m, n};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, b, c, d, e, f, g, h, i, j, k, l, m, n));
        
        //all not null
        array = new Object[] {a, c, e, g, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertFalse(ListUtility.anyNull(list));
        Assert.assertFalse(ListUtility.anyNull(array));
        Assert.assertFalse(ListUtility.anyNull(a, c, e, g, i, k, m));
        
        //all null
        array = new Object[] {b, d, f, h, j, l, n};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(b, d, f, h, j, l, n));
        
        //none
        array = new Object[] {};
        list = ListUtility.emptyList();
        Assert.assertFalse(ListUtility.anyNull(list));
        Assert.assertFalse(ListUtility.anyNull(array));
        Assert.assertFalse(ListUtility.anyNull());
        
        //boolean
        array = new Object[] {a, b, c, e, g, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, b, c, e, g, i, k, m));
        
        //int
        array = new Object[] {a, c, d, e, g, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, b, c, d, e, g, i, k, m));
        
        //float
        array = new Object[] {a, c, e, f, g, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, f, g, i, k, m));
        
        //double
        array = new Object[] {a, c, e, g, h, i, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, h, i, k, m));
        
        //long
        array = new Object[] {a, c, e, g, i, j, k, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, i, j, k, m));
        
        //string
        array = new Object[] {a, c, e, g, i, k, l, m};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, i, k, l, m));
        
        //object
        array = new Object[] {a, c, e, g, i, k, m, n};
        list = ListUtility.toList(array);
        Assert.assertTrue(ListUtility.anyNull(list));
        Assert.assertTrue(ListUtility.anyNull(array));
        Assert.assertTrue(ListUtility.anyNull(a, c, e, g, i, k, m, n));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.anyNull((List<Object>) null));
    }
    
    /**
     * JUnit test of removeNull.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#removeNull(List)
     */
    @SuppressWarnings("WrapperTypeMayBePrimitive")
    @Test
    public void testRemoveNull() throws Exception {
        Boolean a = true;
        Boolean b = null;
        Integer c = 5;
        Integer d = null;
        Float e = 3.6f;
        Float f = null;
        Double g = 48.56423004d;
        Double h = null;
        Long i = 1579843046840984L;
        Long j = null;
        String k = "something";
        String l = null;
        Object m = new Object();
        Object n = null;
        List<Object> list;
        Object[] array;
        
        //all
        array = new Object[] {a, b, c, d, e, f, g, h, i, j, k, l, m, n};
        list = ListUtility.toList(array);
        Assert.assertTrue(list instanceof ArrayList);
        TestUtils.assertListEquals(
                ListUtility.removeNull(list),
                new Object[] {a, c, e, g, i, k, m});
        
        //all not null
        array = new Object[] {a, c, e, g, i, k, m};
        list = ListUtility.toList(array, LinkedList.class);
        Assert.assertTrue(list instanceof LinkedList);
        TestUtils.assertListEquals(
                ListUtility.removeNull(list),
                new Object[] {a, c, e, g, i, k, m});
        
        //all null
        array = new Object[] {b, d, f, h, j, l, n};
        list = ListUtility.toList(array, Stack.class);
        Assert.assertTrue(list instanceof Stack);
        TestUtils.assertListEquals(
                ListUtility.removeNull(list),
                new Object[] {});
        
        //none
        list = ListUtility.cast(ListUtility.emptyList(), Vector.class);
        Assert.assertTrue(list instanceof Vector);
        TestUtils.assertListEquals(
                ListUtility.removeNull(list),
                new Object[] {});
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.removeNull(null));
    }
    
    /**
     * JUnit test of removeDuplicates.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#removeDuplicates(List)
     */
    @SuppressWarnings("ThrowableNotThrown")
    @Test
    public void testRemoveDuplicates() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanCleanList = ListUtility.removeDuplicates(booleanList);
        Assert.assertTrue(booleanCleanList instanceof ArrayList);
        TestUtils.assertListEquals(
                booleanCleanList,
                new Boolean[] {true, false});
        
        //int
        Integer[] integerArray = new Integer[] {15, 15, 312, 48, 5, 5, -4, -9, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerCleanList = ListUtility.removeDuplicates(integerList);
        Assert.assertTrue(integerCleanList instanceof ArrayList);
        TestUtils.assertListEquals(
                integerCleanList,
                new Integer[] {15, 312, 48, 5, -4, -9, 6});
        
        //float
        Float[] floatArray = new Float[] {15.1f, 15.1f, 15.1f, 312.91f, 312.91f, 48.0f, 5.45f, -4.006f, -4.006f, -9.7f, 6.99f, 6.99f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatCleanList = ListUtility.removeDuplicates(floatList);
        Assert.assertTrue(floatCleanList instanceof LinkedList);
        TestUtils.assertListEquals(
                floatCleanList,
                new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f});
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 15.104564d, 15.104564d, 312.9113874d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -4.006005001d, -9.70487745d, 6.99546101d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleCleanList = ListUtility.removeDuplicates(doubleList);
        Assert.assertTrue(doubleCleanList instanceof Stack);
        TestUtils.assertListEquals(
                doubleCleanList,
                new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d});
        
        //long
        Long[] longArray = new Long[] {15104564L, 15104564L, 15104564L, 3129113874L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -4006005001L, -970487745L, 699546101L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longCleanList = ListUtility.removeDuplicates(longList);
        Assert.assertTrue(longCleanList instanceof Vector);
        TestUtils.assertListEquals(
                longCleanList,
                new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L});
        
        //object
        Object[] testObjects = new Object[] {new ArithmeticException(), new HashMap<>(), new Object()};
        Object[] objectArray = new Object[] {"", "", "", 54, 54, testObjects[0], testObjects[0], testObjects[1], testObjects[1], testObjects[1], testObjects[2], testObjects[2]};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectCleanList = ListUtility.removeDuplicates(objectList);
        Assert.assertTrue(objectCleanList instanceof ArrayList);
        TestUtils.assertListEquals(
                objectCleanList,
                new Object[] {"", 54, testObjects[0], testObjects[1], testObjects[2]});
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.removeDuplicates(null));
    }
    
    /**
     * JUnit test of selectRandom.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#selectRandom(List)
     */
    @Test
    public void testSelectRandom() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        for (int i = 0; i < 100; i++) {
            Boolean booleanSelected = ListUtility.selectRandom(booleanList);
            Assert.assertNotNull(booleanSelected);
            Assert.assertTrue(booleanList.contains(booleanSelected));
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        for (int i = 0; i < 100; i++) {
            Integer integerSelected = ListUtility.selectRandom(integerList);
            Assert.assertNotNull(integerSelected);
            Assert.assertTrue(integerList.contains(integerSelected));
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        for (int i = 0; i < 100; i++) {
            Float floatSelected = ListUtility.selectRandom(floatList);
            Assert.assertNotNull(floatSelected);
            Assert.assertTrue(floatList.contains(floatSelected));
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        for (int i = 0; i < 100; i++) {
            Double doubleSelected = ListUtility.selectRandom(doubleList);
            Assert.assertNotNull(doubleSelected);
            Assert.assertTrue(doubleList.contains(doubleSelected));
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        for (int i = 0; i < 100; i++) {
            Long longSelected = ListUtility.selectRandom(longList);
            Assert.assertNotNull(longSelected);
            Assert.assertTrue(longList.contains(longSelected));
        }
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        for (int i = 0; i < 100; i++) {
            Object objectSelected = ListUtility.selectRandom(objectList);
            Assert.assertNotNull(objectSelected);
            Assert.assertTrue(objectList.contains(objectSelected));
        }
        
        //empty list
        Assert.assertNull(ListUtility.selectRandom(ListUtility.emptyList()));
        
        //invalid
        Assert.assertNull(ListUtility.selectRandom(null));
    }
    
    /**
     * JUnit test of selectN.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#selectN(List, int)
     */
    @Test
    public void testSelectN() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanSelected = ListUtility.selectN(booleanList, 1);
        Assert.assertTrue(booleanSelected instanceof ArrayList);
        Assert.assertEquals(1, booleanSelected.size());
        for (Boolean b : booleanSelected) {
            Assert.assertTrue(booleanList.contains(b));
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerSelected = ListUtility.selectN(integerList, 5);
        Assert.assertTrue(integerSelected instanceof ArrayList);
        Assert.assertEquals(5, integerSelected.size());
        for (Integer i : integerSelected) {
            Assert.assertTrue(integerList.contains(i));
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatSelected = ListUtility.selectN(floatList, 7);
        Assert.assertTrue(floatSelected instanceof LinkedList);
        Assert.assertEquals(7, floatSelected.size());
        for (Float f : floatSelected) {
            Assert.assertTrue(floatList.contains(f));
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleSelected = ListUtility.selectN(doubleList, 3);
        Assert.assertTrue(doubleSelected instanceof Stack);
        Assert.assertEquals(3, doubleSelected.size());
        for (Double d : doubleSelected) {
            Assert.assertTrue(doubleList.contains(d));
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longSelected = ListUtility.selectN(longList, 4);
        Assert.assertTrue(longSelected instanceof Vector);
        Assert.assertEquals(4, longSelected.size());
        for (Long l : longSelected) {
            Assert.assertTrue(longList.contains(l));
        }
        
        //object
        
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray, Vector.class);
        List<Object> objectSelected = ListUtility.selectN(objectList, 3);
        Assert.assertTrue(objectSelected instanceof Vector);
        Assert.assertEquals(3, objectSelected.size());
        for (Object o : objectSelected) {
            Assert.assertTrue(objectList.contains(o));
        }
        
        List<Object> overSizeObjectSelected = ListUtility.selectN(objectList, 999);
        Assert.assertTrue(objectSelected instanceof Vector);
        Assert.assertEquals(objectList.size(), overSizeObjectSelected.size());
        for (Object o : overSizeObjectSelected) {
            Assert.assertTrue(objectList.contains(o));
        }
        
        //invalid
        
        List<Object> underSizeObjectSelected = ListUtility.selectN(objectList, -1);
        Assert.assertTrue(underSizeObjectSelected.isEmpty());
        
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.selectN(null, 4));
    }
    
    /**
     * JUnit test of duplicateInOrder.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#duplicateInOrder(List, int)
     * @see ListUtility#duplicateInOrder(List)
     */
    @Test
    public void testDuplicateInOrder() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> duplicatedBooleanList = ListUtility.duplicateInOrder(booleanList);
        Assert.assertTrue(duplicatedBooleanList instanceof ArrayList);
        TestUtils.assertListEquals(
                duplicatedBooleanList,
                Collections.nCopies(2, booleanList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        duplicatedBooleanList = ListUtility.duplicateInOrder(booleanList, 6);
        Assert.assertTrue(duplicatedBooleanList instanceof ArrayList);
        TestUtils.assertListEquals(
                duplicatedBooleanList,
                Collections.nCopies(6, booleanList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> duplicatedIntegerList = ListUtility.duplicateInOrder(integerList);
        Assert.assertTrue(duplicatedIntegerList instanceof ArrayList);
        TestUtils.assertListEquals(
                duplicatedIntegerList,
                Collections.nCopies(2, integerList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        duplicatedIntegerList = ListUtility.duplicateInOrder(integerList, 150);
        Assert.assertTrue(duplicatedIntegerList instanceof ArrayList);
        TestUtils.assertListEquals(
                duplicatedIntegerList,
                Collections.nCopies(150, integerList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> duplicatedFloatList = ListUtility.duplicateInOrder(floatList);
        Assert.assertTrue(duplicatedFloatList instanceof LinkedList);
        TestUtils.assertListEquals(
                duplicatedFloatList,
                Collections.nCopies(2, floatList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        duplicatedFloatList = ListUtility.duplicateInOrder(floatList, 51);
        Assert.assertTrue(duplicatedFloatList instanceof LinkedList);
        TestUtils.assertListEquals(
                duplicatedFloatList,
                Collections.nCopies(51, floatList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> duplicatedDoubleList = ListUtility.duplicateInOrder(doubleList);
        Assert.assertTrue(duplicatedDoubleList instanceof Stack);
        TestUtils.assertListEquals(
                duplicatedDoubleList,
                Collections.nCopies(2, doubleList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        duplicatedDoubleList = ListUtility.duplicateInOrder(doubleList, 8);
        Assert.assertTrue(duplicatedDoubleList instanceof Stack);
        TestUtils.assertListEquals(
                duplicatedDoubleList,
                Collections.nCopies(8, doubleList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> duplicatedLongList = ListUtility.duplicateInOrder(longList);
        Assert.assertTrue(duplicatedLongList instanceof Vector);
        TestUtils.assertListEquals(
                duplicatedLongList,
                Collections.nCopies(2, longList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        duplicatedLongList = ListUtility.duplicateInOrder(longList, 10);
        Assert.assertTrue(duplicatedLongList instanceof Vector);
        TestUtils.assertListEquals(
                duplicatedLongList,
                Collections.nCopies(10, longList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> duplicatedObjectList = ListUtility.duplicateInOrder(objectList);
        Assert.assertTrue(duplicatedObjectList instanceof ArrayList);
        TestUtils.assertListEquals(
                duplicatedObjectList,
                Collections.nCopies(2, objectList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, 12);
        Assert.assertTrue(duplicatedObjectList instanceof ArrayList);
        TestUtils.assertListEquals(
                duplicatedObjectList,
                Collections.nCopies(12, objectList).stream().flatMap(Collection::stream).collect(Collectors.toList()));
        
        //edge case
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, 1);
        Assert.assertTrue(duplicatedObjectList instanceof ArrayList);
        TestUtils.assertListEquals(duplicatedObjectList, objectList);
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, 0);
        Assert.assertTrue(duplicatedObjectList.isEmpty());
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, -1);
        Assert.assertTrue(duplicatedObjectList.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.duplicateInOrder(null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.duplicateInOrder(null, 4));
    }
    
    /**
     * JUnit test of sortByNumberOfOccurrences.
     *
     * @throws Exception When there is an exception.
     * @see ListUtility#sortByNumberOfOccurrences(List, boolean)
     * @see ListUtility#sortByNumberOfOccurrences(List)
     */
    @Test
    public void testSortByNumberOfOccurrences() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> sortedBooleanList = ListUtility.sortByNumberOfOccurrences(booleanList);
        Assert.assertTrue(sortedBooleanList instanceof ArrayList);
        TestUtils.assertListEquals(
                sortedBooleanList,
                new Boolean[] {false, false, false, true, true});
        sortedBooleanList = ListUtility.sortByNumberOfOccurrences(booleanList, false);
        Assert.assertTrue(sortedBooleanList instanceof ArrayList);
        TestUtils.assertListEquals(
                sortedBooleanList,
                new Boolean[] {false, false, false, true, true});
        sortedBooleanList = ListUtility.sortByNumberOfOccurrences(booleanList, true);
        Assert.assertTrue(sortedBooleanList instanceof ArrayList);
        TestUtils.assertListEquals(
                sortedBooleanList,
                new Boolean[] {true, true, false, false, false});
        
        //int
        Integer[] integerArray = new Integer[] {1, 3, -5, 8, 4, 1, 1, 1, 3, 3, 1, 1, 4};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> sortedIntegerList = ListUtility.sortByNumberOfOccurrences(integerList);
        Assert.assertTrue(sortedIntegerList instanceof ArrayList);
        TestUtils.assertListEquals(
                sortedIntegerList,
                new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8});
        sortedIntegerList = ListUtility.sortByNumberOfOccurrences(integerList, false);
        Assert.assertTrue(sortedIntegerList instanceof ArrayList);
        TestUtils.assertListEquals(
                sortedIntegerList,
                new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8});
        sortedIntegerList = ListUtility.sortByNumberOfOccurrences(integerList, true);
        Assert.assertTrue(sortedIntegerList instanceof ArrayList);
        TestUtils.assertListEquals(
                sortedIntegerList,
                new Integer[] {-5, 8, 4, 4, 3, 3, 3, 1, 1, 1, 1, 1, 1});
        
        //float
        Float[] floatArray = new Float[] {1.1f, 3.9f, -5.0f, 8.44f, 4.7f, 1.1f, 1.1f, 1.1f, 3.8f, 3.8f, 1.1f, 1.1f, 4.7f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> sortedFloatList = ListUtility.sortByNumberOfOccurrences(floatList);
        Assert.assertTrue(sortedFloatList instanceof LinkedList);
        TestUtils.assertListEquals(
                sortedFloatList,
                new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f});
        sortedFloatList = ListUtility.sortByNumberOfOccurrences(floatList, false);
        Assert.assertTrue(sortedFloatList instanceof LinkedList);
        TestUtils.assertListEquals(
                sortedFloatList,
                new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f});
        sortedFloatList = ListUtility.sortByNumberOfOccurrences(floatList, true);
        Assert.assertTrue(sortedFloatList instanceof LinkedList);
        TestUtils.assertListEquals(
                sortedFloatList,
                new Float[] {3.9f, -5.0f, 8.44f, 4.7f, 4.7f, 3.8f, 3.8f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f});
        
        //double
        Double[] doubleArray = new Double[] {1.1d, 3.9d, -5.0d, 8.44d, 4.7d, 1.1d, 1.1d, 1.1d, 3.8d, 3.8d, 1.1d, 1.1d, 4.7d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> sortedDoubleList = ListUtility.sortByNumberOfOccurrences(doubleList);
        Assert.assertTrue(sortedDoubleList instanceof Stack);
        TestUtils.assertListEquals(
                sortedDoubleList,
                new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d});
        sortedDoubleList = ListUtility.sortByNumberOfOccurrences(doubleList, false);
        Assert.assertTrue(sortedDoubleList instanceof Stack);
        TestUtils.assertListEquals(
                sortedDoubleList,
                new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d});
        sortedDoubleList = ListUtility.sortByNumberOfOccurrences(doubleList, true);
        Assert.assertTrue(sortedDoubleList instanceof Stack);
        TestUtils.assertListEquals(
                sortedDoubleList,
                new Double[] {3.9d, -5.0d, 8.44d, 4.7d, 4.7d, 3.8d, 3.8d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d});
        
        //long
        Long[] longArray = new Long[] {1000L, 3000L, -5000L, 8000L, 4000L, 1000L, 1000L, 1000L, 3000L, 3000L, 1000L, 1000L, 4000L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> sortedLongList = ListUtility.sortByNumberOfOccurrences(longList);
        Assert.assertTrue(sortedLongList instanceof Vector);
        TestUtils.assertListEquals(
                sortedLongList,
                new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L});
        sortedLongList = ListUtility.sortByNumberOfOccurrences(longList, false);
        Assert.assertTrue(sortedLongList instanceof Vector);
        TestUtils.assertListEquals(
                sortedLongList,
                new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L});
        sortedLongList = ListUtility.sortByNumberOfOccurrences(longList, true);
        Assert.assertTrue(sortedLongList instanceof Vector);
        TestUtils.assertListEquals(
                sortedLongList,
                new Long[] {-5000L, 8000L, 4000L, 4000L, 3000L, 3000L, 3000L, 1000L, 1000L, 1000L, 1000L, 1000L, 1000L});
        
        //object
        Object a = new Object();
        Object b = "";
        Object c = new ArithmeticException();
        Object d = new HashMap<>();
        Object[] objectArray = new Object[] {a, b, a, a, a, c, d, c, d, c};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> sortedObjectList = ListUtility.sortByNumberOfOccurrences(objectList);
        Assert.assertTrue(sortedObjectList instanceof ArrayList);
        TestUtils.assertListEquals(
                sortedObjectList,
                new Object[] {a, a, a, a, c, c, c, d, d, b});
        sortedObjectList = ListUtility.sortByNumberOfOccurrences(objectList, false);
        Assert.assertTrue(sortedObjectList instanceof ArrayList);
        TestUtils.assertListEquals(
                sortedObjectList,
                new Object[] {a, a, a, a, c, c, c, d, d, b});
        sortedObjectList = ListUtility.sortByNumberOfOccurrences(objectList, true);
        Assert.assertTrue(sortedObjectList instanceof ArrayList);
        TestUtils.assertListEquals(
                sortedObjectList,
                new Object[] {b, d, d, c, c, c, a, a, a, a});
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.sortByNumberOfOccurrences(null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.sortByNumberOfOccurrences(null, true));
    }
    
}
