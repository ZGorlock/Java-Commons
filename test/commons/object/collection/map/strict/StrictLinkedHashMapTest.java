/*
 * File:    StrictLinkedHashMapTest.java
 * Package: commons.object.collection.map.strict
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.map.strict;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
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
 * JUnit test of StrictLinkedHashMap.
 *
 * @see StrictLinkedHashMap
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({StrictLinkedHashMap.class})
public class StrictLinkedHashMapTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(StrictLinkedHashMapTest.class);
    
    
    //Static Fields
    
    /**
     * A list of keys to use for testing.
     */
    private static final List<String> testKeys = StringUtility.stringStream(StringUtility.CONSONANT_CHARS.toUpperCase()).collect(Collectors.toList());
    
    /**
     * A list of values to use for testing.
     */
    private static final List<Integer> testValues = IntStream.range(0, testKeys.size()).boxed().collect(Collectors.toList());
    
    /**
     * The UnmodifiableSet class.
     */
    private static final Class<?> UnmodifiableSet = TestAccess.getClass(Collections.class, "UnmodifiableSet");
    
    /**
     * The UnmodifiableCollection class.
     */
    private static final Class<?> UnmodifiableCollection = TestAccess.getClass(Collections.class, "UnmodifiableCollection");
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private StrictLinkedHashMap<String, Integer> sut;
    
    
    //Functions
    
    /**
     * Validates the immutability of a view of the system under test.
     */
    private final Consumer<Collection<?>> immutableViewValidator = (Collection<?> view) -> {
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.add(null));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.addAll(Collections.nCopies(3, null)));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.remove(null));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.removeAll(Collections.nCopies(3, null)));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.retainAll(Collections.nCopies(3, null)));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.removeIf(Objects::nonNull));
        TestUtils.assertException(UnsupportedOperationException.class, view::clear);
    };
    
    /**
     * Validates the mutability of a view of the system under test.
     */
    private final Consumer<Collection<?>> mutableViewValidator = (Collection<?> view) -> {
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.add(null));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.addAll(Collections.nCopies(3, null)));
        TestUtils.assertNoException(() ->
                view.remove(null));
        TestUtils.assertNoException(() ->
                view.removeAll(Collections.nCopies(3, null)));
        TestUtils.assertNoException(() ->
                view.retainAll(Collections.nCopies(3, null)));
        TestUtils.assertNoException(() ->
                view.removeIf(Objects::nonNull));
        TestUtils.assertNoException(view::clear);
    };
    
    
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
    @Before
    public void setup() throws Exception {
        sut = new StrictLinkedHashMap<>(MapUtility.mapOf(LinkedHashMap.class, testKeys, testValues));
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
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see StrictLinkedHashMap#StrictLinkedHashMap(Map)
     * @see StrictLinkedHashMap#StrictLinkedHashMap()
     */
    @Test
    public void testConstructors() throws Exception {
        final Map<String, Integer> sourceMap = MapUtility.mapOf(LinkedHashMap.class, testKeys, testValues);
        StrictLinkedHashMap<String, Integer> map1;
        StrictLinkedHashMap<String, Integer> map2;
        
        //map
        map1 = new StrictLinkedHashMap<>(sourceMap);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof LinkedHashMap);
        TestUtils.assertMapEquals(map1, sourceMap);
        
        //empty
        map2 = new StrictLinkedHashMap<>();
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof LinkedHashMap);
        Assert.assertTrue(map2.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new StrictLinkedHashMap<>((Map<?, ?>) null));
    }
    
    /**
     * JUnit test of exposedEntrySet.
     *
     * @throws Exception When there is an exception.
     * @see StrictLinkedHashMap#exposedEntrySet()
     */
    @Test
    public void testExposedEntrySet() throws Exception {
        final Set<Map.Entry<String, Integer>> entrySet = sut.exposedEntrySet();
        
        //standard
        Assert.assertNotNull(entrySet);
        Assert.assertNotEquals(UnmodifiableSet, entrySet.getClass());
        Assert.assertEquals(sut.size(), entrySet.size());
        entrySet.forEach(entry -> {
            Assert.assertTrue(sut.containsKey(entry.getKey()));
            Assert.assertTrue(sut.containsValue(entry.getValue()));
            Assert.assertEquals(entry.getValue(), sut.get(entry.getKey()));
        });
        
        //immutability
        mutableViewValidator.accept(entrySet);
        
        //equality
        TestUtils.assertListEquals(
                ListUtility.toList(sut.exposedEntrySet()),
                sut.exposedEntrySet(), true);
        Assert.assertSame(sut.exposedEntrySet(), sut.exposedEntrySet());
        
        //empty
        Assert.assertTrue(new StrictLinkedHashMap<>().exposedEntrySet().isEmpty());
    }
    
    /**
     * JUnit test of entrySet.
     *
     * @throws Exception When there is an exception.
     * @see StrictLinkedHashMap#entrySet()
     */
    @Test
    public void testEntrySet() throws Exception {
        final Set<Map.Entry<String, Integer>> entrySet = sut.entrySet();
        
        //standard
        Assert.assertNotNull(entrySet);
        Assert.assertEquals(UnmodifiableSet, entrySet.getClass());
        Assert.assertEquals(sut.size(), entrySet.size());
        entrySet.forEach(entry -> {
            Assert.assertTrue(sut.containsKey(entry.getKey()));
            Assert.assertTrue(sut.containsValue(entry.getValue()));
            Assert.assertEquals(entry.getValue(), sut.get(entry.getKey()));
        });
        
        //immutability
        immutableViewValidator.accept(entrySet);
        
        //equality
        TestUtils.assertListEquals(
                ListUtility.toList(sut.entrySet()),
                sut.entrySet(), true);
        Assert.assertNotSame(sut.entrySet(), sut.entrySet());
        
        //empty
        Assert.assertTrue(new StrictLinkedHashMap<>().entrySet().isEmpty());
    }
    
    /**
     * JUnit test of exposedKeySet.
     *
     * @throws Exception When there is an exception.
     * @see StrictLinkedHashMap#exposedKeySet()
     */
    @Test
    public void testExposedKeySet() throws Exception {
        final Set<String> keySet = sut.exposedKeySet();
        
        //standard
        Assert.assertNotNull(keySet);
        Assert.assertNotEquals(UnmodifiableSet, keySet.getClass());
        Assert.assertEquals(sut.size(), keySet.size());
        keySet.forEach(key ->
                Assert.assertTrue(sut.containsKey(key)));
        
        //immutability
        mutableViewValidator.accept(keySet);
        
        //equality
        TestUtils.assertListEquals(
                ListUtility.toList(sut.exposedKeySet()),
                sut.exposedKeySet(), true);
        Assert.assertSame(sut.exposedKeySet(), sut.exposedKeySet());
        
        //empty
        Assert.assertTrue(new StrictLinkedHashMap<>().exposedKeySet().isEmpty());
    }
    
    /**
     * JUnit test of keySet.
     *
     * @throws Exception When there is an exception.
     * @see StrictLinkedHashMap#keySet()
     */
    @Test
    public void testKeySet() throws Exception {
        final Set<String> keySet = sut.keySet();
        
        //standard
        Assert.assertNotNull(keySet);
        Assert.assertEquals(UnmodifiableSet, keySet.getClass());
        Assert.assertEquals(sut.size(), keySet.size());
        keySet.forEach(key ->
                Assert.assertTrue(sut.containsKey(key)));
        
        //immutability
        immutableViewValidator.accept(keySet);
        
        //equality
        TestUtils.assertListEquals(
                ListUtility.toList(sut.keySet()),
                sut.keySet(), true);
        Assert.assertNotSame(sut.keySet(), sut.keySet());
        
        //empty
        Assert.assertTrue(new StrictLinkedHashMap<>().keySet().isEmpty());
    }
    
    /**
     * JUnit test of exposedValues.
     *
     * @throws Exception When there is an exception.
     * @see StrictLinkedHashMap#exposedValues()
     */
    @Test
    public void testExposedValues() throws Exception {
        final Collection<Integer> valueSet = sut.exposedValues();
        
        //standard
        Assert.assertNotNull(valueSet);
        Assert.assertNotEquals(UnmodifiableCollection, valueSet.getClass());
        Assert.assertEquals(sut.size(), valueSet.size());
        valueSet.forEach(value ->
                Assert.assertTrue(sut.containsValue(value)));
        
        //immutability
        mutableViewValidator.accept(valueSet);
        
        //equality
        TestUtils.assertListEquals(
                ListUtility.toList(sut.exposedValues()),
                sut.exposedValues(), true);
        Assert.assertSame(sut.exposedValues(), sut.exposedValues());
        
        //empty
        Assert.assertTrue(new StrictLinkedHashMap<>().exposedValues().isEmpty());
    }
    
    /**
     * JUnit test of values.
     *
     * @throws Exception When there is an exception.
     * @see StrictLinkedHashMap#values()
     */
    @Test
    public void testValues() throws Exception {
        final Collection<Integer> valueSet = sut.values();
        
        //standard
        Assert.assertNotNull(valueSet);
        Assert.assertEquals(UnmodifiableCollection, valueSet.getClass());
        Assert.assertEquals(sut.size(), valueSet.size());
        valueSet.forEach(value ->
                Assert.assertTrue(sut.containsValue(value)));
        
        //immutability
        immutableViewValidator.accept(valueSet);
        
        //equality
        TestUtils.assertListEquals(
                ListUtility.toList(sut.values()),
                sut.values(), true);
        Assert.assertNotSame(sut.values(), sut.values());
        
        //empty
        Assert.assertTrue(new StrictLinkedHashMap<>().values().isEmpty());
    }
    
}
