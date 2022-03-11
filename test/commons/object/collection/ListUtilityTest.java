/*
 * File:    ListUtilityTest.java
 * Package: commons.object.collection
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.Vector;

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
        List<Boolean> booleanList = ListUtility.create(LinkedList.class, Boolean.class, 5);
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
        List<Integer> integerList = ListUtility.create(LinkedList.class, Integer.class, 7);
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
        List<Float> floatList = ListUtility.create(LinkedList.class, Float.class, 8);
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
        List<Double> doubleList = ListUtility.create(LinkedList.class, Double.class, 8);
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
        List<Long> longList = ListUtility.create(LinkedList.class, Long.class, 7);
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
        List<Object> objectList = ListUtility.create(LinkedList.class, Object.class, 5);
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
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create(Object.class, -1));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create(ArrayList.class, Object.class, -1));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create(Vector.class, Object.class, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create(LinkedList.class, Object.class, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create(Stack.class, Object.class, -1));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
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
        List<List<Boolean>> booleanList = ListUtility.create2D(LinkedList.class, Boolean.class, 5, 4);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof LinkedList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        booleanList = ListUtility.create2D(Stack.class, true, 5, 4);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof Stack);
        Assert.assertEquals(5, booleanList.size());
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
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        booleanList = ListUtility.create2D(true, 5, 4);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create2D(Boolean.class);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(0, booleanList.size());
        
        //int
        List<List<Integer>> integerList = ListUtility.create2D(LinkedList.class, Integer.class, 7, 6);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof LinkedList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        integerList = ListUtility.create2D(Stack.class, 18, 7, 6);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof Stack);
        Assert.assertEquals(7, integerList.size());
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
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        integerList = ListUtility.create2D(18, 7, 6);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create2D(Integer.class);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(0, integerList.size());
        
        //float
        List<List<Float>> floatList = ListUtility.create2D(LinkedList.class, Float.class, 8, 7);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof LinkedList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        floatList = ListUtility.create2D(Stack.class, 6.847f, 8, 7);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof Stack);
        Assert.assertEquals(8, floatList.size());
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
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        floatList = ListUtility.create2D(6.847f, 8, 7);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create2D(Float.class);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(0, floatList.size());
        
        //double
        List<List<Double>> doubleList = ListUtility.create2D(LinkedList.class, Double.class, 8, 7);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof LinkedList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        doubleList = ListUtility.create2D(Stack.class, 117.4984560456d, 8, 7);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof Stack);
        Assert.assertEquals(8, doubleList.size());
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
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        doubleList = ListUtility.create2D(117.4984560456d, 8, 7);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create2D(Double.class);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(0, doubleList.size());
        
        //long
        List<List<Long>> longList = ListUtility.create2D(LinkedList.class, Long.class, 7, 6);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof LinkedList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        longList = ListUtility.create2D(Stack.class, 178984654231545L, 7, 6);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof Stack);
        Assert.assertEquals(7, longList.size());
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
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        longList = ListUtility.create2D(178984654231545L, 7, 6);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        Assert.assertTrue(longList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create2D(Long.class);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(0, longList.size());
        
        //object
        final Object testObject = new StringBuilder();
        List<List<Object>> objectList = ListUtility.create2D(LinkedList.class, Object.class, 5, 4);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof LinkedList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        objectList = ListUtility.create2D(Stack.class, testObject, 5, 4);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof Stack);
        Assert.assertEquals(5, objectList.size());
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
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(Objects::isNull));
        objectList = ListUtility.create2D(testObject, 5, 4);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create2D(Object.class);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(0, objectList.size());
        
        //invalid
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create2D(Object.class, -1, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create2D(Object.class, 5, -1));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create2D(ArrayList.class, Object.class, -1, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create2D(ArrayList.class, Object.class, 5, -1));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create2D(Vector.class, Object.class, -1, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create2D(Vector.class, Object.class, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(LinkedList.class, Object.class, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(LinkedList.class, Object.class, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(Stack.class, Object.class, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create2D(Stack.class, Object.class, 5, -1));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create2D(18, -1, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
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
        List<List<List<Boolean>>> booleanList = ListUtility.create3D(LinkedList.class, Boolean.class, 5, 4, 3);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof LinkedList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        booleanList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        booleanList = ListUtility.create3D(Stack.class, true, 5, 4, 3);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof Stack);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        booleanList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create3D(Vector.class, Boolean.class);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof Vector);
        Assert.assertEquals(0, booleanList.size());
        booleanList = ListUtility.create3D(Boolean.class, 5, 4, 3);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        booleanList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        booleanList = ListUtility.create3D(true, 5, 4, 3);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(5, booleanList.size());
        Assert.assertTrue(booleanList.stream().allMatch(e -> (e.size() == 4)));
        booleanList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3));
        Assert.assertTrue(booleanList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, true))));
        booleanList = ListUtility.create3D(Boolean.class);
        Assert.assertNotNull(booleanList);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(0, booleanList.size());
        
        //int
        List<List<List<Integer>>> integerList = ListUtility.create3D(LinkedList.class, Integer.class, 7, 6, 5);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof LinkedList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        integerList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        integerList = ListUtility.create3D(Stack.class, 18, 7, 6, 5);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof Stack);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        integerList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create3D(Vector.class, Integer.class);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof Vector);
        Assert.assertEquals(0, integerList.size());
        integerList = ListUtility.create3D(Integer.class, 7, 6, 5);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        integerList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        integerList = ListUtility.create3D(18, 7, 6, 5);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(7, integerList.size());
        Assert.assertTrue(integerList.stream().allMatch(e -> (e.size() == 6)));
        integerList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(integerList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 18))));
        integerList = ListUtility.create3D(Integer.class);
        Assert.assertNotNull(integerList);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(0, integerList.size());
        
        //float
        List<List<List<Float>>> floatList = ListUtility.create3D(LinkedList.class, Float.class, 8, 7, 6);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof LinkedList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        floatList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        floatList = ListUtility.create3D(Stack.class, 6.847f, 8, 7, 6);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof Stack);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        floatList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create3D(Vector.class, Float.class);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof Vector);
        Assert.assertEquals(0, floatList.size());
        floatList = ListUtility.create3D(Float.class, 8, 7, 6);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        floatList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        floatList = ListUtility.create3D(6.847f, 8, 7, 6);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(8, floatList.size());
        Assert.assertTrue(floatList.stream().allMatch(e -> (e.size() == 7)));
        floatList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6));
        Assert.assertTrue(floatList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 6.847f))));
        floatList = ListUtility.create3D(Float.class);
        Assert.assertNotNull(floatList);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(0, floatList.size());
        
        //double
        List<List<List<Double>>> doubleList = ListUtility.create3D(LinkedList.class, Double.class, 8, 7, 6);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof LinkedList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        doubleList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        doubleList = ListUtility.create3D(Stack.class, 117.4984560456d, 8, 7, 6);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof Stack);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        doubleList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create3D(Vector.class, Double.class);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof Vector);
        Assert.assertEquals(0, doubleList.size());
        doubleList = ListUtility.create3D(Double.class, 8, 7, 6);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        doubleList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        doubleList = ListUtility.create3D(117.4984560456d, 8, 7, 6);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(8, doubleList.size());
        Assert.assertTrue(doubleList.stream().allMatch(e -> (e.size() == 7)));
        doubleList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 6));
        Assert.assertTrue(doubleList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 117.4984560456d))));
        doubleList = ListUtility.create3D(Double.class);
        Assert.assertNotNull(doubleList);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(0, doubleList.size());
        
        //long
        List<List<List<Long>>> longList = ListUtility.create3D(LinkedList.class, Long.class, 7, 6, 5);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof LinkedList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        longList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(longList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        longList = ListUtility.create3D(Stack.class, 178984654231545L, 7, 6, 5);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof Stack);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        longList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(longList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create3D(Vector.class, Long.class);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof Vector);
        Assert.assertEquals(0, longList.size());
        longList = ListUtility.create3D(Long.class, 7, 6, 5);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        longList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(longList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        longList = ListUtility.create3D(178984654231545L, 7, 6, 5);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(7, longList.size());
        Assert.assertTrue(longList.stream().allMatch(e -> (e.size() == 6)));
        longList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(longList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, 178984654231545L))));
        longList = ListUtility.create3D(Long.class);
        Assert.assertNotNull(longList);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(0, longList.size());
        
        //object
        final Object testObject = new StringBuilder();
        List<List<List<Object>>> objectList = ListUtility.create3D(LinkedList.class, Object.class, 5, 4, 3);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof LinkedList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        objectList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        objectList = ListUtility.create3D(Stack.class, testObject, 7, 6, 5);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof Stack);
        Assert.assertEquals(7, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 6)));
        objectList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create3D(Vector.class, Object.class);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof Vector);
        Assert.assertEquals(0, objectList.size());
        objectList = ListUtility.create3D(Object.class, 5, 4, 3);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(5, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 4)));
        objectList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 3));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(Objects::isNull));
        objectList = ListUtility.create3D(testObject, 7, 6, 5);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(7, objectList.size());
        Assert.assertTrue(objectList.stream().allMatch(e -> (e.size() == 6)));
        objectList.stream().flatMap(List::stream).allMatch(e -> (e.size() == 5));
        Assert.assertTrue(objectList.stream().flatMap(List::stream).flatMap(List::stream).allMatch(e -> (Objects.equals(e, testObject))));
        objectList = ListUtility.create3D(Object.class);
        Assert.assertNotNull(objectList);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(0, objectList.size());
        
        //invalid
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(Object.class, -1, 5, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(Object.class, 5, -1, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(Object.class, 5, 5, -1));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(ArrayList.class, Object.class, -1, 5, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(ArrayList.class, Object.class, 5, -1, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(ArrayList.class, Object.class, 5, 5, -1));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(Vector.class, Object.class, -1, 5, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(Vector.class, Object.class, 5, -1, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(Vector.class, Object.class, 5, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(LinkedList.class, Object.class, -1, 5, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(LinkedList.class, Object.class, 5, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(LinkedList.class, Object.class, 5, 5, -1));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Stack.class, Object.class, -1, 5, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Stack.class, Object.class, 5, -1, 5));
        TestUtils.assertNoException(() ->
                ListUtility.create3D(Stack.class, Object.class, 5, 5, -1));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(18, -1, 5, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
                ListUtility.create3D(18, 5, -1, 5));
        TestUtils.assertException(IllegalArgumentException.class, "Illegal Capacity: -1", () ->
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
        List<Boolean> booleanList = ListUtility.listOf(true, false, false, true, false);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(booleanArray.length, booleanList.size());
        Assert.assertArrayEquals(booleanArray, booleanList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.listOf(ArrayList.class, 15, 312, 48, 5, -4, -9, 6);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(integerArray.length, integerList.size());
        Assert.assertArrayEquals(integerArray, integerList.toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.listOf(LinkedList.class, 15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f);
        Assert.assertTrue(floatList instanceof LinkedList);
        Assert.assertEquals(floatArray.length, floatList.size());
        Assert.assertArrayEquals(floatArray, floatList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.listOf(Stack.class, 15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d);
        Assert.assertTrue(doubleList instanceof Stack);
        Assert.assertEquals(doubleArray.length, doubleList.size());
        Assert.assertArrayEquals(doubleArray, doubleList.toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.listOf(Vector.class, 15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L);
        Assert.assertTrue(longList instanceof Vector);
        Assert.assertEquals(longArray.length, longList.size());
        Assert.assertArrayEquals(longArray, longList.toArray());
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.listOf(objectArray[0], objectArray[1], objectArray[2], objectArray[3], objectArray[4]);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(objectArray.length, objectList.size());
        Assert.assertArrayEquals(objectArray, objectList.toArray());
        
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
     */
    @Test
    public void testToList() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(booleanArray.length, booleanList.size());
        Assert.assertArrayEquals(booleanArray, booleanList.toArray());
        booleanList = ListUtility.toList(booleanArray, ArrayList.class);
        Assert.assertTrue(booleanList instanceof ArrayList);
        Assert.assertEquals(booleanArray.length, booleanList.size());
        Assert.assertArrayEquals(booleanArray, booleanList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        Assert.assertTrue(integerList instanceof ArrayList);
        Assert.assertEquals(integerArray.length, integerList.size());
        Assert.assertArrayEquals(integerArray, integerList.toArray());
        integerList = ListUtility.toList(integerArray, LinkedList.class);
        Assert.assertTrue(integerList instanceof LinkedList);
        Assert.assertEquals(integerArray.length, integerList.size());
        Assert.assertArrayEquals(integerArray, integerList.toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        Assert.assertTrue(floatList instanceof ArrayList);
        Assert.assertEquals(floatArray.length, floatList.size());
        Assert.assertArrayEquals(floatArray, floatList.toArray());
        floatList = ListUtility.toList(floatArray, Vector.class);
        Assert.assertTrue(floatList instanceof Vector);
        Assert.assertEquals(floatArray.length, floatList.size());
        Assert.assertArrayEquals(floatArray, floatList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        Assert.assertTrue(doubleList instanceof ArrayList);
        Assert.assertEquals(doubleArray.length, doubleList.size());
        Assert.assertArrayEquals(doubleArray, doubleList.toArray());
        doubleList = ListUtility.toList(doubleArray, Stack.class);
        Assert.assertTrue(doubleList instanceof Stack);
        Assert.assertEquals(doubleArray.length, doubleList.size());
        Assert.assertArrayEquals(doubleArray, doubleList.toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        Assert.assertTrue(longList instanceof ArrayList);
        Assert.assertEquals(longArray.length, longList.size());
        Assert.assertArrayEquals(longArray, longList.toArray());
        longList = ListUtility.toList(longArray, Vector.class);
        Assert.assertTrue(longList instanceof Vector);
        Assert.assertEquals(longArray.length, longList.size());
        Assert.assertArrayEquals(longArray, longList.toArray());
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        Assert.assertTrue(objectList instanceof ArrayList);
        Assert.assertEquals(objectArray.length, objectList.size());
        Assert.assertArrayEquals(objectArray, objectList.toArray());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList(objectArray, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList(null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.toList(null));
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
        Assert.assertEquals(booleanList.size(), booleanClone.size());
        Assert.assertArrayEquals(booleanList.toArray(), booleanClone.toArray());
        Assert.assertNotSame(booleanList, booleanClone);
        booleanList = ListUtility.toList(booleanArray, ArrayList.class);
        booleanClone = ListUtility.clone(booleanList);
        Assert.assertTrue(booleanClone instanceof ArrayList);
        Assert.assertEquals(booleanList.size(), booleanClone.size());
        Assert.assertArrayEquals(booleanList.toArray(), booleanClone.toArray());
        Assert.assertNotSame(booleanList, booleanClone);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray);
        List<Integer> integerClone = ListUtility.clone(integerList);
        Assert.assertTrue(integerClone instanceof ArrayList);
        Assert.assertEquals(integerList.size(), integerClone.size());
        Assert.assertArrayEquals(integerList.toArray(), integerClone.toArray());
        Assert.assertNotSame(integerList, integerClone);
        integerList = ListUtility.toList(integerArray, LinkedList.class);
        integerClone = ListUtility.clone(integerList);
        Assert.assertTrue(integerClone instanceof LinkedList);
        Assert.assertEquals(integerList.size(), integerClone.size());
        Assert.assertArrayEquals(integerList.toArray(), integerClone.toArray());
        Assert.assertNotSame(integerList, integerClone);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray);
        List<Float> floatClone = ListUtility.clone(floatList);
        Assert.assertTrue(floatClone instanceof ArrayList);
        Assert.assertEquals(floatList.size(), floatClone.size());
        Assert.assertArrayEquals(floatList.toArray(), floatClone.toArray());
        Assert.assertNotSame(floatList, floatClone);
        floatList = ListUtility.toList(floatArray, Vector.class);
        floatClone = ListUtility.clone(floatList);
        Assert.assertTrue(floatClone instanceof Vector);
        Assert.assertEquals(floatList.size(), floatClone.size());
        Assert.assertArrayEquals(floatList.toArray(), floatClone.toArray());
        Assert.assertNotSame(floatList, floatClone);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray);
        List<Double> doubleClone = ListUtility.clone(doubleList);
        Assert.assertTrue(doubleClone instanceof ArrayList);
        Assert.assertEquals(doubleList.size(), doubleClone.size());
        Assert.assertArrayEquals(doubleList.toArray(), doubleClone.toArray());
        Assert.assertNotSame(doubleList, doubleClone);
        doubleList = ListUtility.toList(doubleArray, Stack.class);
        doubleClone = ListUtility.clone(doubleList);
        Assert.assertTrue(doubleClone instanceof Stack);
        Assert.assertEquals(doubleList.size(), doubleClone.size());
        Assert.assertArrayEquals(doubleList.toArray(), doubleClone.toArray());
        Assert.assertNotSame(doubleList, doubleClone);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray);
        List<Long> longClone = ListUtility.clone(longList);
        Assert.assertTrue(longClone instanceof ArrayList);
        Assert.assertEquals(longList.size(), longClone.size());
        Assert.assertArrayEquals(longList.toArray(), longClone.toArray());
        Assert.assertNotSame(longList, longClone);
        longList = ListUtility.toList(longArray, Vector.class);
        longClone = ListUtility.clone(longList);
        Assert.assertTrue(longClone instanceof Vector);
        Assert.assertEquals(longList.size(), longClone.size());
        Assert.assertArrayEquals(longList.toArray(), longClone.toArray());
        Assert.assertNotSame(longList, longClone);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectClone = ListUtility.clone(objectList);
        Assert.assertTrue(objectClone instanceof ArrayList);
        Assert.assertEquals(objectList.size(), objectClone.size());
        Assert.assertArrayEquals(objectList.toArray(), objectClone.toArray());
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
        Assert.assertEquals(booleanList.size(), booleanConverted.size());
        Assert.assertArrayEquals(booleanList.toArray(), booleanConverted.toArray());
        Assert.assertSame(booleanList, booleanConverted);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerConverted = ListUtility.cast(integerList, ArrayList.class);
        Assert.assertTrue(integerConverted instanceof ArrayList);
        Assert.assertEquals(integerList.size(), integerConverted.size());
        Assert.assertArrayEquals(integerList.toArray(), integerConverted.toArray());
        Assert.assertSame(integerList, integerConverted);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, ArrayList.class);
        List<Float> floatConverted = ListUtility.cast(floatList, LinkedList.class);
        Assert.assertTrue(floatConverted instanceof LinkedList);
        Assert.assertEquals(floatList.size(), floatConverted.size());
        Assert.assertArrayEquals(floatList.toArray(), floatConverted.toArray());
        Assert.assertNotSame(floatList, floatConverted);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, LinkedList.class);
        List<Double> doubleConverted = ListUtility.cast(doubleList, Stack.class);
        Assert.assertTrue(doubleConverted instanceof Stack);
        Assert.assertEquals(doubleList.size(), doubleConverted.size());
        Assert.assertArrayEquals(doubleList.toArray(), doubleConverted.toArray());
        Assert.assertNotSame(doubleList, doubleConverted);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longConverted = ListUtility.cast(longList, LinkedList.class);
        Assert.assertTrue(longConverted instanceof LinkedList);
        Assert.assertEquals(longList.size(), longConverted.size());
        Assert.assertArrayEquals(longList.toArray(), longConverted.toArray());
        Assert.assertNotSame(longList, longConverted);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray, Stack.class);
        List<Object> objectConverted = ListUtility.cast(objectList, Vector.class);
        Assert.assertTrue(objectConverted instanceof Vector);
        Assert.assertEquals(objectList.size(), objectConverted.size());
        Assert.assertArrayEquals(objectList.toArray(), objectConverted.toArray());
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
        Assert.assertEquals(2, booleanSubList.size());
        Assert.assertArrayEquals(new Boolean[] {false, true}, booleanSubList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerSubList = ListUtility.subList(integerList, 2);
        Assert.assertTrue(integerSubList instanceof ArrayList);
        Assert.assertEquals(5, integerSubList.size());
        Assert.assertArrayEquals(new Integer[] {48, 5, -4, -9, 6}, integerSubList.toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatSubList = ListUtility.subList(floatList, 0, 4);
        Assert.assertTrue(floatSubList instanceof LinkedList);
        Assert.assertEquals(4, floatSubList.size());
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f}, floatSubList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleSubList = ListUtility.subList(doubleList, 0);
        Assert.assertTrue(doubleSubList instanceof Stack);
        Assert.assertEquals(7, doubleSubList.size());
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleSubList.toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longSubList = ListUtility.subList(longList, 6, 7);
        Assert.assertTrue(longSubList instanceof Vector);
        Assert.assertEquals(1, longSubList.size());
        Assert.assertArrayEquals(new Long[] {699546101L}, longSubList.toArray());
        
        //object
        Object b = new ArithmeticException();
        Object[] objectArray = new Object[] {"", 54, b, new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectSubList = ListUtility.subList(objectList, 1, 3);
        Assert.assertTrue(objectSubList instanceof ArrayList);
        Assert.assertEquals(2, objectSubList.size());
        Assert.assertArrayEquals(new Object[] {54, b}, objectSubList.toArray());
        
        //empty
        List<Object> subList = ListUtility.subList(objectList, 0, 0);
        Assert.assertEquals(0, subList.size());
        
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
        Assert.assertEquals(7, booleanMergeList.size());
        Assert.assertArrayEquals(new Boolean[] {true, false, false, true, false, true, false}, booleanMergeList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        Integer[] integerArray2 = new Integer[] {15, 312, 48};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerList2 = ListUtility.toList(integerArray2, ArrayList.class);
        List<Integer> integerMergeList = ListUtility.merge(integerList, integerList2);
        Assert.assertTrue(integerMergeList instanceof ArrayList);
        Assert.assertEquals(10, integerMergeList.size());
        Assert.assertArrayEquals(new Integer[] {15, 312, 48, 5, -4, -9, 6, 15, 312, 48}, integerMergeList.toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        Float[] floatArray2 = new Float[] {15.1f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatList2 = ListUtility.toList(floatArray2, ArrayList.class);
        List<Float> floatMergeList = ListUtility.merge(floatList, floatList2);
        Assert.assertTrue(floatMergeList instanceof LinkedList);
        Assert.assertEquals(9, floatMergeList.size());
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f, 15.1f}, floatMergeList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        Double[] doubleArray2 = new Double[] {-4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleList2 = ListUtility.toList(doubleArray2, Vector.class);
        List<Double> doubleMergeList = ListUtility.merge(doubleList, doubleList2);
        Assert.assertTrue(doubleMergeList instanceof Stack);
        Assert.assertEquals(10, doubleMergeList.size());
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleMergeList.toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        Long[] longArray2 = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longList2 = ListUtility.toList(longArray2);
        List<Long> longMergeList = ListUtility.merge(longList, longList2);
        Assert.assertTrue(longMergeList instanceof Vector);
        Assert.assertEquals(14, longMergeList.size());
        Assert.assertArrayEquals(new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L, 15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L}, longMergeList.toArray());
        
        //object
        Object a = "";
        Object b = 54;
        Object c = new ArithmeticException();
        Object d = new HashMap<>();
        Object e = new Object();
        Object[] objectArray = new Object[] {a, b, c, d, e};
        Object[] objectArray2 = new Object[] {b, c, e};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectList2 = ListUtility.toList(objectArray2);
        List<Object> objectMergeList = ListUtility.merge(objectList, objectList2);
        Assert.assertTrue(objectMergeList instanceof ArrayList);
        Assert.assertEquals(8, objectMergeList.size());
        Assert.assertArrayEquals(new Object[] {a, b, c, d, e, b, c, e}, objectMergeList.toArray());
        
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
        Assert.assertArrayEquals(new Boolean[] {true, false, false}, booleanSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Boolean[] {true, false, null}, booleanSplitList.get(1).toArray());
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<List<Integer>> integerSplitList = ListUtility.split(integerList, 2);
        Assert.assertTrue(integerSplitList instanceof ArrayList);
        Assert.assertTrue(integerSplitList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertEquals(4, integerSplitList.size());
        Assert.assertArrayEquals(new Integer[] {15, 312}, integerSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Integer[] {48, 5}, integerSplitList.get(1).toArray());
        Assert.assertArrayEquals(new Integer[] {-4, -9}, integerSplitList.get(2).toArray());
        Assert.assertArrayEquals(new Integer[] {6, null}, integerSplitList.get(3).toArray());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<List<Float>> floatSplitList = ListUtility.split(floatList, 4);
        Assert.assertTrue(floatSplitList instanceof LinkedList);
        Assert.assertTrue(floatSplitList.stream().allMatch(LinkedList.class::isInstance));
        Assert.assertEquals(2, floatSplitList.size());
        Assert.assertArrayEquals(new Float[] {15.1f, 312.91f, 48.0f, 5.45f}, floatSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Float[] {-4.006f, -9.7f, 6.99f, 19776.4f}, floatSplitList.get(1).toArray());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<List<Double>> doubleSplitList = ListUtility.split(doubleList, 7);
        Assert.assertTrue(doubleSplitList instanceof Stack);
        Assert.assertTrue(doubleSplitList.stream().allMatch(Stack.class::isInstance));
        Assert.assertEquals(1, doubleSplitList.size());
        Assert.assertArrayEquals(new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d}, doubleSplitList.get(0).toArray());
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<List<Long>> longSplitList = ListUtility.split(longList, 1);
        Assert.assertTrue(longSplitList instanceof Vector);
        Assert.assertTrue(longSplitList.stream().allMatch(Vector.class::isInstance));
        Assert.assertEquals(7, longSplitList.size());
        Assert.assertArrayEquals(new Long[] {15104564L}, longSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Long[] {3129113874L}, longSplitList.get(1).toArray());
        Assert.assertArrayEquals(new Long[] {4800000015L}, longSplitList.get(2).toArray());
        Assert.assertArrayEquals(new Long[] {5457894511L}, longSplitList.get(3).toArray());
        Assert.assertArrayEquals(new Long[] {-4006005001L}, longSplitList.get(4).toArray());
        Assert.assertArrayEquals(new Long[] {-970487745L}, longSplitList.get(5).toArray());
        Assert.assertArrayEquals(new Long[] {699546101L}, longSplitList.get(6).toArray());
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<List<Object>> objectSplitList = ListUtility.split(objectList, 3);
        Assert.assertTrue(objectSplitList instanceof ArrayList);
        Assert.assertTrue(objectSplitList.stream().allMatch(ArrayList.class::isInstance));
        Assert.assertEquals(2, objectSplitList.size());
        Assert.assertArrayEquals(new Object[] {"", 54, objectArray[2]}, objectSplitList.get(0).toArray());
        Assert.assertArrayEquals(new Object[] {objectArray[3], objectArray[4], null}, objectSplitList.get(1).toArray());
        
        //invalid
        
        objectSplitList = ListUtility.split(objectList, 0);
        Assert.assertEquals(objectArray.length, objectSplitList.size());
        
        objectSplitList = ListUtility.split(objectList, -1);
        Assert.assertEquals(objectArray.length, objectSplitList.size());
        
        objectSplitList = ListUtility.split(objectList, 15613);
        Assert.assertEquals(1, objectSplitList.size());
        
        objectSplitList = ListUtility.split(Collections.emptyList(), 3);
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
        Assert.assertArrayEquals(new Boolean[] {false, true, false, false, true}, booleanReversedList.toArray());
        Assert.assertNotEquals(booleanList, booleanReversedList);
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerReversedList = ListUtility.reverse(integerList);
        Assert.assertTrue(integerReversedList instanceof ArrayList);
        Assert.assertArrayEquals(new Integer[] {6, -9, -4, 5, 48, 312, 15}, integerReversedList.toArray());
        Assert.assertNotEquals(integerList, integerReversedList);
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatReversedList = ListUtility.reverse(floatList);
        Assert.assertTrue(floatReversedList instanceof LinkedList);
        Assert.assertArrayEquals(new Float[] {19776.4f, 6.99f, -9.7f, -4.006f, 5.45f, 48.0f, 312.91f, 15.1f}, floatReversedList.toArray());
        Assert.assertNotEquals(floatList, floatReversedList);
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleReversedList = ListUtility.reverse(doubleList);
        Assert.assertTrue(doubleReversedList instanceof Stack);
        Assert.assertArrayEquals(new Double[] {6.99546101d, -9.70487745d, -4.006005001d, 5.457894511d, 48.00000015d, 312.9113874d, 15.104564d}, doubleReversedList.toArray());
        Assert.assertNotEquals(doubleList, doubleReversedList);
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longReversedList = ListUtility.reverse(longList);
        Assert.assertTrue(longReversedList instanceof Vector);
        Assert.assertArrayEquals(new Long[] {699546101L, -970487745L, -4006005001L, 5457894511L, 4800000015L, 3129113874L, 15104564L}, longReversedList.toArray());
        Assert.assertNotEquals(longList, longReversedList);
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectReversedList = ListUtility.reverse(objectList);
        Assert.assertTrue(objectReversedList instanceof ArrayList);
        Assert.assertArrayEquals(new Object[] {objectArray[4], objectArray[3], objectArray[2], 54, ""}, objectReversedList.toArray());
        Assert.assertNotEquals(objectList, objectReversedList);
        
        //invalid
        
        List<Object> emptyReversedList = ListUtility.reverse(Collections.emptyList());
        Assert.assertArrayEquals(new Object[] {}, emptyReversedList.toArray());
        
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.reverse(null));
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
        Assert.assertFalse(ListUtility.isNullOrEmpty(Arrays.asList("test", "list")));
        
        //empty
        Assert.assertTrue(ListUtility.isNullOrEmpty(new ArrayList<>()));
        
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
        list = Collections.emptyList();
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
        //noinspection ResultOfMethodCallIgnored
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
        List<Object> denulled;
        Object[] array;
        
        //all
        array = new Object[] {a, b, c, d, e, f, g, h, i, j, k, l, m, n};
        list = ListUtility.toList(array);
        denulled = ListUtility.removeNull(list);
        Assert.assertTrue(list instanceof ArrayList);
        Assert.assertArrayEquals(new Object[] {a, c, e, g, i, k, m}, denulled.toArray());
        
        //all not null
        array = new Object[] {a, c, e, g, i, k, m};
        list = ListUtility.toList(array, LinkedList.class);
        denulled = ListUtility.removeNull(list);
        Assert.assertTrue(list instanceof LinkedList);
        Assert.assertArrayEquals(new Object[] {a, c, e, g, i, k, m}, denulled.toArray());
        
        //all null
        array = new Object[] {b, d, f, h, j, l, n};
        list = ListUtility.toList(array, Stack.class);
        denulled = ListUtility.removeNull(list);
        Assert.assertTrue(list instanceof Stack);
        Assert.assertArrayEquals(new Object[] {}, denulled.toArray());
        
        //none
        list = ListUtility.cast(Collections.emptyList(), Vector.class);
        denulled = ListUtility.removeNull(list);
        Assert.assertTrue(list instanceof Vector);
        Assert.assertArrayEquals(new Object[] {}, denulled.toArray());
        
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
    @Test
    public void testRemoveDuplicates() throws Exception {
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> booleanCleanList = ListUtility.removeDuplicates(booleanList);
        Assert.assertTrue(booleanCleanList instanceof ArrayList);
        Assert.assertEquals(2, booleanCleanList.size());
        
        //int
        Integer[] integerArray = new Integer[] {15, 15, 312, 48, 5, 5, -4, -9, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> integerCleanList = ListUtility.removeDuplicates(integerList);
        Assert.assertTrue(integerCleanList instanceof ArrayList);
        Assert.assertEquals(7, integerCleanList.size());
        
        //float
        Float[] floatArray = new Float[] {15.1f, 15.1f, 15.1f, 312.91f, 312.91f, 48.0f, 5.45f, -4.006f, -4.006f, -9.7f, 6.99f, 6.99f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> floatCleanList = ListUtility.removeDuplicates(floatList);
        Assert.assertTrue(floatCleanList instanceof LinkedList);
        Assert.assertEquals(7, floatCleanList.size());
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 15.104564d, 15.104564d, 312.9113874d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -4.006005001d, -9.70487745d, 6.99546101d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> doubleCleanList = ListUtility.removeDuplicates(doubleList);
        Assert.assertTrue(doubleCleanList instanceof Stack);
        Assert.assertEquals(7, doubleCleanList.size());
        
        //long
        Long[] longArray = new Long[] {15104564L, 15104564L, 15104564L, 3129113874L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -4006005001L, -970487745L, 699546101L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> longCleanList = ListUtility.removeDuplicates(longList);
        Assert.assertTrue(longCleanList instanceof Vector);
        Assert.assertEquals(7, longCleanList.size());
        
        //object
        String s = "";
        ArithmeticException ae = new ArithmeticException();
        HashMap<Object, Object> hm = new HashMap<>();
        Object o = new Object();
        Object[] objectArray = new Object[] {s, s, s, 54, 54, ae, ae, hm, hm, hm, o, o};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> objectCleanList = ListUtility.removeDuplicates(objectList);
        Assert.assertTrue(objectCleanList instanceof ArrayList);
        Assert.assertEquals(5, objectCleanList.size());
        
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
        Assert.assertNull(ListUtility.selectRandom(new ArrayList<>()));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.selectRandom(null));
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
        Assert.assertEquals(0, underSizeObjectSelected.size());
        
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
        int x;
        
        //boolean
        Boolean[] booleanArray = new Boolean[] {true, false, false, true, false};
        List<Boolean> booleanList = ListUtility.toList(booleanArray);
        List<Boolean> duplicatedBooleanList = ListUtility.duplicateInOrder(booleanList);
        Assert.assertTrue(duplicatedBooleanList instanceof ArrayList);
        Assert.assertEquals(booleanList.size() * 2, duplicatedBooleanList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Boolean b : booleanList) {
                Assert.assertEquals(b, duplicatedBooleanList.get(x));
                x++;
            }
        }
        duplicatedBooleanList = ListUtility.duplicateInOrder(booleanList, 6);
        Assert.assertTrue(duplicatedBooleanList instanceof ArrayList);
        Assert.assertEquals(booleanList.size() * 6, duplicatedBooleanList.size());
        x = 0;
        for (int y = 0; y < 6; y++) {
            for (Boolean b : booleanList) {
                Assert.assertEquals(b, duplicatedBooleanList.get(x));
                x++;
            }
        }
        
        //int
        Integer[] integerArray = new Integer[] {15, 312, 48, 5, -4, -9, 6};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> duplicatedIntegerList = ListUtility.duplicateInOrder(integerList);
        Assert.assertTrue(duplicatedIntegerList instanceof ArrayList);
        Assert.assertEquals(integerList.size() * 2, duplicatedIntegerList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Integer i : integerList) {
                Assert.assertEquals(i, duplicatedIntegerList.get(x));
                x++;
            }
        }
        duplicatedIntegerList = ListUtility.duplicateInOrder(integerList, 150);
        Assert.assertTrue(duplicatedIntegerList instanceof ArrayList);
        Assert.assertEquals(integerList.size() * 150, duplicatedIntegerList.size());
        x = 0;
        for (int y = 0; y < 150; y++) {
            for (Integer i : integerList) {
                Assert.assertEquals(i, duplicatedIntegerList.get(x));
                x++;
            }
        }
        
        //float
        Float[] floatArray = new Float[] {15.1f, 312.91f, 48.0f, 5.45f, -4.006f, -9.7f, 6.99f, 19776.4f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> duplicatedFloatList = ListUtility.duplicateInOrder(floatList);
        Assert.assertTrue(duplicatedFloatList instanceof LinkedList);
        Assert.assertEquals(floatList.size() * 2, duplicatedFloatList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Float f : floatList) {
                Assert.assertEquals(f, duplicatedFloatList.get(x));
                x++;
            }
        }
        duplicatedFloatList = ListUtility.duplicateInOrder(floatList, 150);
        Assert.assertTrue(duplicatedFloatList instanceof LinkedList);
        Assert.assertEquals(floatList.size() * 150, duplicatedFloatList.size());
        x = 0;
        for (int y = 0; y < 150; y++) {
            for (Float f : floatList) {
                Assert.assertEquals(f, duplicatedFloatList.get(x));
                x++;
            }
        }
        
        //double
        Double[] doubleArray = new Double[] {15.104564d, 312.9113874d, 48.00000015d, 5.457894511d, -4.006005001d, -9.70487745d, 6.99546101d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> duplicatedDoubleList = ListUtility.duplicateInOrder(doubleList);
        Assert.assertTrue(duplicatedDoubleList instanceof Stack);
        Assert.assertEquals(doubleList.size() * 2, duplicatedDoubleList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Double d : doubleList) {
                Assert.assertEquals(d, duplicatedDoubleList.get(x));
                x++;
            }
        }
        duplicatedDoubleList = ListUtility.duplicateInOrder(doubleList, 8);
        Assert.assertTrue(duplicatedDoubleList instanceof Stack);
        Assert.assertEquals(doubleList.size() * 8, duplicatedDoubleList.size());
        x = 0;
        for (int y = 0; y < 8; y++) {
            for (Double d : doubleList) {
                Assert.assertEquals(d, duplicatedDoubleList.get(x));
                x++;
            }
        }
        
        //long
        Long[] longArray = new Long[] {15104564L, 3129113874L, 4800000015L, 5457894511L, -4006005001L, -970487745L, 699546101L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> duplicatedLongList = ListUtility.duplicateInOrder(longList);
        Assert.assertTrue(duplicatedLongList instanceof Vector);
        Assert.assertEquals(longList.size() * 2, duplicatedLongList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Long l : longList) {
                Assert.assertEquals(l, duplicatedLongList.get(x));
                x++;
            }
        }
        duplicatedLongList = ListUtility.duplicateInOrder(longList, 10);
        Assert.assertTrue(duplicatedLongList instanceof Vector);
        Assert.assertEquals(longList.size() * 10, duplicatedLongList.size());
        x = 0;
        for (int y = 0; y < 10; y++) {
            for (Long l : longList) {
                Assert.assertEquals(l, duplicatedLongList.get(x));
                x++;
            }
        }
        
        //object
        Object[] objectArray = new Object[] {"", 54, new ArithmeticException(), new HashMap<>(), new Object()};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> duplicatedObjectList = ListUtility.duplicateInOrder(objectList);
        Assert.assertTrue(duplicatedObjectList instanceof ArrayList);
        Assert.assertEquals(objectList.size() * 2, duplicatedObjectList.size());
        x = 0;
        for (int y = 0; y < 2; y++) {
            for (Object o : objectList) {
                Assert.assertEquals(o, duplicatedObjectList.get(x));
                x++;
            }
        }
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, 10);
        Assert.assertTrue(duplicatedObjectList instanceof ArrayList);
        Assert.assertEquals(objectList.size() * 10, duplicatedObjectList.size());
        x = 0;
        for (int y = 0; y < 10; y++) {
            for (Object o : objectList) {
                Assert.assertEquals(o, duplicatedObjectList.get(x));
                x++;
            }
        }
        
        //edge case
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, 1);
        Assert.assertEquals(objectList.size(), duplicatedObjectList.size());
        x = 0;
        for (int y = 0; y < 1; y++) {
            for (Object o : objectList) {
                Assert.assertEquals(o, duplicatedObjectList.get(x));
                x++;
            }
        }
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, 0);
        Assert.assertEquals(0, duplicatedObjectList.size());
        duplicatedObjectList = ListUtility.duplicateInOrder(objectList, -1);
        Assert.assertEquals(0, duplicatedObjectList.size());
        
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
        Assert.assertArrayEquals(new Boolean[] {false, false, false, true, true}, sortedBooleanList.toArray());
        sortedBooleanList = ListUtility.sortByNumberOfOccurrences(booleanList, false);
        Assert.assertTrue(sortedBooleanList instanceof ArrayList);
        Assert.assertArrayEquals(new Boolean[] {false, false, false, true, true}, sortedBooleanList.toArray());
        sortedBooleanList = ListUtility.sortByNumberOfOccurrences(booleanList, true);
        Assert.assertTrue(sortedBooleanList instanceof ArrayList);
        Assert.assertArrayEquals(new Boolean[] {true, true, false, false, false}, sortedBooleanList.toArray());
        
        //int
        Integer[] integerArray = new Integer[] {1, 3, -5, 8, 4, 1, 1, 1, 3, 3, 1, 1, 4};
        List<Integer> integerList = ListUtility.toList(integerArray, ArrayList.class);
        List<Integer> sortedIntegerList = ListUtility.sortByNumberOfOccurrences(integerList);
        Assert.assertTrue(sortedIntegerList instanceof ArrayList);
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8}, sortedIntegerList.toArray());
        sortedIntegerList = ListUtility.sortByNumberOfOccurrences(integerList, false);
        Assert.assertTrue(sortedIntegerList instanceof ArrayList);
        Assert.assertArrayEquals(new Integer[] {1, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, -5, 8}, sortedIntegerList.toArray());
        sortedIntegerList = ListUtility.sortByNumberOfOccurrences(integerList, true);
        Assert.assertTrue(sortedIntegerList instanceof ArrayList);
        Assert.assertArrayEquals(new Integer[] {-5, 8, 4, 4, 3, 3, 3, 1, 1, 1, 1, 1, 1}, sortedIntegerList.toArray());
        
        //float
        Float[] floatArray = new Float[] {1.1f, 3.9f, -5.0f, 8.44f, 4.7f, 1.1f, 1.1f, 1.1f, 3.8f, 3.8f, 1.1f, 1.1f, 4.7f};
        List<Float> floatList = ListUtility.toList(floatArray, LinkedList.class);
        List<Float> sortedFloatList = ListUtility.sortByNumberOfOccurrences(floatList);
        Assert.assertTrue(sortedFloatList instanceof LinkedList);
        Assert.assertArrayEquals(new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f}, sortedFloatList.toArray());
        sortedFloatList = ListUtility.sortByNumberOfOccurrences(floatList, false);
        Assert.assertTrue(sortedFloatList instanceof LinkedList);
        Assert.assertArrayEquals(new Float[] {1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 4.7f, 4.7f, 3.8f, 3.8f, 3.9f, -5.0f, 8.44f}, sortedFloatList.toArray());
        sortedFloatList = ListUtility.sortByNumberOfOccurrences(floatList, true);
        Assert.assertTrue(sortedFloatList instanceof LinkedList);
        Assert.assertArrayEquals(new Float[] {3.9f, -5.0f, 8.44f, 4.7f, 4.7f, 3.8f, 3.8f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f}, sortedFloatList.toArray());
        
        //double
        Double[] doubleArray = new Double[] {1.1d, 3.9d, -5.0d, 8.44d, 4.7d, 1.1d, 1.1d, 1.1d, 3.8d, 3.8d, 1.1d, 1.1d, 4.7d};
        List<Double> doubleList = ListUtility.toList(doubleArray, Stack.class);
        List<Double> sortedDoubleList = ListUtility.sortByNumberOfOccurrences(doubleList);
        Assert.assertTrue(sortedDoubleList instanceof Stack);
        Assert.assertArrayEquals(new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d}, sortedDoubleList.toArray());
        sortedDoubleList = ListUtility.sortByNumberOfOccurrences(doubleList, false);
        Assert.assertTrue(sortedDoubleList instanceof Stack);
        Assert.assertArrayEquals(new Double[] {1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 4.7d, 4.7d, 3.8d, 3.8d, 3.9d, -5.0d, 8.44d}, sortedDoubleList.toArray());
        sortedDoubleList = ListUtility.sortByNumberOfOccurrences(doubleList, true);
        Assert.assertTrue(sortedDoubleList instanceof Stack);
        Assert.assertArrayEquals(new Double[] {3.9d, -5.0d, 8.44d, 4.7d, 4.7d, 3.8d, 3.8d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d, 1.1d}, sortedDoubleList.toArray());
        
        //long
        Long[] longArray = new Long[] {1000L, 3000L, -5000L, 8000L, 4000L, 1000L, 1000L, 1000L, 3000L, 3000L, 1000L, 1000L, 4000L};
        List<Long> longList = ListUtility.toList(longArray, Vector.class);
        List<Long> sortedLongList = ListUtility.sortByNumberOfOccurrences(longList);
        Assert.assertTrue(sortedLongList instanceof Vector);
        Assert.assertArrayEquals(new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L}, sortedLongList.toArray());
        sortedLongList = ListUtility.sortByNumberOfOccurrences(longList, false);
        Assert.assertTrue(sortedLongList instanceof Vector);
        Assert.assertArrayEquals(new Long[] {1000L, 1000L, 1000L, 1000L, 1000L, 1000L, 3000L, 3000L, 3000L, 4000L, 4000L, -5000L, 8000L}, sortedLongList.toArray());
        sortedLongList = ListUtility.sortByNumberOfOccurrences(longList, true);
        Assert.assertTrue(sortedLongList instanceof Vector);
        Assert.assertArrayEquals(new Long[] {-5000L, 8000L, 4000L, 4000L, 3000L, 3000L, 3000L, 1000L, 1000L, 1000L, 1000L, 1000L, 1000L}, sortedLongList.toArray());
        
        //object
        Object a = new Object();
        Object b = "";
        Object c = new ArithmeticException();
        Object d = new HashMap<>();
        Object[] objectArray = new Object[] {a, b, a, a, a, c, d, c, d, c};
        List<Object> objectList = ListUtility.toList(objectArray);
        List<Object> sortedObjectList = ListUtility.sortByNumberOfOccurrences(objectList);
        Assert.assertTrue(sortedObjectList instanceof ArrayList);
        Assert.assertArrayEquals(new Object[] {a, a, a, a, c, c, c, d, d, b}, sortedObjectList.toArray());
        sortedObjectList = ListUtility.sortByNumberOfOccurrences(objectList, false);
        Assert.assertTrue(sortedObjectList instanceof ArrayList);
        Assert.assertArrayEquals(new Object[] {a, a, a, a, c, c, c, d, d, b}, sortedObjectList.toArray());
        sortedObjectList = ListUtility.sortByNumberOfOccurrences(objectList, true);
        Assert.assertTrue(sortedObjectList instanceof ArrayList);
        Assert.assertArrayEquals(new Object[] {b, d, d, c, c, c, a, a, a, a}, sortedObjectList.toArray());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.sortByNumberOfOccurrences(null));
        TestUtils.assertException(NullPointerException.class, () ->
                ListUtility.sortByNumberOfOccurrences(null, true));
    }
    
}
