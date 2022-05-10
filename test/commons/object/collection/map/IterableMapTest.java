/*
 * File:    IterableMapTest.java
 * Package: commons.object.collection.map
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.map;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import commons.lambda.stream.collector.ArrayCollectors;
import commons.lambda.stream.collector.MapCollectors;
import commons.math.NumberUtility;
import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
import commons.object.collection.map.strict.StrictHashMap;
import commons.object.string.StringUtility;
import commons.test.TestAccess;
import commons.test.TestUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
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
 * JUnit test of IterableMap.
 *
 * @see IterableMap
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({IterableMap.class})
public class IterableMapTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(IterableMapTest.class);
    
    
    //Static Fields
    
    /**
     * A list of key sets to use for testing.
     */
    private static final List<String>[] keySets = Stream.of(StringUtility.VOWEL_CHARS, StringUtility.CONSONANT_CHARS)
            .flatMap(keySetSource -> Stream.of(keySetSource.toUpperCase(), keySetSource.toLowerCase()))
            .map(keySetSeed -> StringUtility.stringStream(keySetSeed).collect(Collectors.toUnmodifiableList()))
            .toArray(ArrayCollectors.generator(List.class));
    
    /**
     * The UnmodifiableSet class.
     */
    private static final Class<?> UnmodifiableSet = TestAccess.getClass(Collections.class, "UnmodifiableSet");
    
    /**
     * The UnmodifiableRandomAccessList class.
     */
    private static final Class<?> UnmodifiableRandomAccessList = TestAccess.getClass(Collections.class, "UnmodifiableRandomAccessList");
    
    /**
     * The UnmodifiableCollection class.
     */
    private static final Class<?> UnmodifiableCollection = TestAccess.getClass(Collections.class, "UnmodifiableCollection");
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private IterableMap<String, Integer> sut;
    
    /**
     * The list of keys of the system under test.
     */
    private List<String> keys;
    
    /**
     * The list of values of the system under test.
     */
    private List<Integer> values;
    
    /**
     * The generator of the values to use for testing.
     */
    private final AtomicInteger nextValue = new AtomicInteger(0);
    
    
    //Functions
    
    /**
     * Initializes the content of system under test.
     */
    private final Runnable sutInitializer = () -> {
        nextValue.set(0);
        keys = ListUtility.shuffle(ListUtility.merge(keySets[2], keySets[0]));
        values = IntStream.range(0, keys.size()).mapToObj(i -> nextValue.incrementAndGet()).collect(Collectors.toList());
        sut = new IterableMap<>(MapUtility.mapOf(LinkedHashMap.class, keys, values));
    };
    
    /**
     * Validates the content and order of the system under test.
     */
    private final Runnable sutValidator = () -> {
        final List<Map.Entry<String, Integer>> entrySetOrdered = sut.orderedEntrySet();
        final List<String> keySetOrdered = sut.orderedKeySet();
        final List<Integer> valueSetOrdered = sut.orderedValues();
        
        Assert.assertTrue(keys.stream().allMatch(key -> sut.containsKey(key)));
        Assert.assertTrue(values.stream().allMatch(value -> sut.containsValue(value)));
        List.of(keys, values,
                sut.keySet(), sut.values(), sut.entrySet(),
                keySetOrdered, valueSetOrdered, entrySetOrdered
        ).forEach(components ->
                Assert.assertEquals(sut.size(), components.size()));
        
        IntStream.range(0, sut.size()).forEach(i ->
                MapUtility.mapOf(
                        new ImmutablePair<>(keys.get(i), List.of(
                                sut.getKey(i),
                                sut.getEntry(keys.get(i)).getKey(),
                                sut.getEntry(i).getKey(),
                                keySetOrdered.get(i),
                                entrySetOrdered.get(i).getKey())),
                        new ImmutablePair<>(values.get(i), List.of(
                                sut.get(keys.get(i)),
                                sut.get(i),
                                sut.getEntry(keys.get(i)).getValue(),
                                sut.getEntry(i).getValue(),
                                valueSetOrdered.get(i),
                                entrySetOrdered.get(i).getValue()))
                ).forEach((component, comparisons) ->
                        comparisons.forEach(comparison ->
                                Assert.assertEquals(component, comparison))));
    };
    
    /**
     * Performs a test on the system under test.
     */
    private final Consumer<Runnable> sutTestRunner = (Runnable test) -> {
        sutInitializer.run();
        test.run();
        sutValidator.run();
    };
    
    /**
     * Performs a test on the system under test for a list of test keys.
     */
    private final BiConsumer<List<String>, Consumer<String>> sutTester = (List<String> testKeys, Consumer<String> test) ->
            sutTestRunner.accept(() ->
                    ListUtility.selectN(testKeys, Math.min(testKeys.size(), 10)).forEach(test));
    
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
        sutValidator.run();
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
     * @see #sutInitializer
     */
    @Before
    public void setup() throws Exception {
        sutInitializer.run();
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     * @see #sutValidator
     */
    @After
    public void cleanup() throws Exception {
        sutValidator.run();
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
     * @see IterableMap#IterableMap(Map)
     * @see IterableMap#IterableMap(IterableMap)
     * @see IterableMap#IterableMap()
     */
    @Test
    public void testConstructors() throws Exception {
        final Map<String, Integer> sourceMap = MapUtility.mapOf(keys, values);
        IterableMap<String, Integer> map1;
        IterableMap<String, Integer> map2;
        IterableMap<String, Integer> map3;
        
        //map
        map1 = new IterableMap<>(sourceMap);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof StrictHashMap);
        TestUtils.assertMapEquals(map1, sourceMap);
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(map1, List.class, "keyList"),
                keys, false);
        
        //iterable map
        map2 = new IterableMap<>(map1);
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof StrictHashMap);
        TestUtils.assertMapEquals(map2, map1);
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(map2, List.class, "keyList"),
                TestAccess.getFieldValue(map1, List.class, "keyList"), true);
        
        //empty
        map3 = new IterableMap<>();
        Assert.assertNotNull(map3);
        Assert.assertTrue(map3 instanceof StrictHashMap);
        Assert.assertTrue(map3.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new IterableMap<>((Map<?, ?>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new IterableMap<>((IterableMap<?, ?>) null));
    }
    
    /**
     * JUnit test of getKey.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#getKey(int)
     */
    @Test
    public void testGetKey() throws Exception {
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(key,
                        sut.getKey(keys.indexOf(key))));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds ->
                TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                        sut.getKey(outOfBounds)));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#get(int)
     * @see IterableMap#get(Object)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.get(key)));
        
        //index
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.get(keys.indexOf(key))));
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.get(key)));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds ->
                TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                        sut.get(outOfBounds)));
        Assert.assertNull(sut.get(null));
    }
    
    /**
     * JUnit test of getOrDefault.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#getOrDefault(Object, Object)
     */
    @Test
    public void testGetOrDefault() throws Exception {
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.getOrDefault(key, -1)));
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertEquals(-1,
                        sut.getOrDefault(key, -1).intValue()));
        
        //invalid
        Assert.assertEquals(-1, sut.getOrDefault(null, -1).intValue());
    }
    
    /**
     * JUnit test of getEntry.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#getEntry(Object)
     * @see IterableMap#getEntry(int)
     */
    @Test
    public void testGetEntry() throws Exception {
        //standard
        sutTester.accept(keySets[2], key -> {
            final Map.Entry<String, Integer> entry = sut.getEntry(key);
            Assert.assertEquals(key, entry.getKey());
            Assert.assertEquals(values.get(keys.indexOf(key)), entry.getValue());
        });
        
        //index
        sutTester.accept(keySets[2], key -> {
            final Map.Entry<String, Integer> entry = sut.getEntry(keys.indexOf(key));
            Assert.assertEquals(key, entry.getKey());
            Assert.assertEquals(values.get(keys.indexOf(key)), entry.getValue());
        });
        
        //immutability
        sutTestRunner.accept(() ->
                TestUtils.assertException(UnsupportedOperationException.class, () ->
                        sut.getEntry(3).setValue(-1)));
        
        //equality
        Assert.assertEquals(sut.getEntry(0), sut.getEntry(0));
        Assert.assertNotSame(sut.getEntry(0), sut.getEntry(0));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds ->
                TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                        sut.getEntry(outOfBounds)));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getEntry(null));
    }
    
    /**
     * JUnit test of containsKey.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#containsKey(Object)
     */
    @Test
    public void testContainsKey() throws Exception {
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertTrue(
                        sut.containsKey(key)));
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.containsKey(key)));
        
        //invalid
        Assert.assertFalse(sut.containsKey(null));
    }
    
    /**
     * JUnit test of containsValue.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#containsValue(Object)
     */
    @Test
    public void testContainsValue() throws Exception {
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertTrue(
                        sut.containsValue(values.get(keys.indexOf(key)))));
        
        //absent
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.containsValue(-values.get(keys.indexOf(key)))));
        
        //invalid
        Assert.assertFalse(sut.containsValue(null));
    }
    
    /**
     * JUnit test of indexOf.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#indexOf(Object)
     */
    @Test
    public void testIndexOf() throws Exception {
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(keys.indexOf(key),
                        sut.indexOf(key)));
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertEquals(-1,
                        sut.indexOf(key)));
        
        //invalid
        Assert.assertEquals(-1, sut.indexOf(null));
    }
    
    /**
     * JUnit test of entrySet.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#entrySet)
     */
    @Test
    public void testEntrySet() throws Exception {
        final Set<Map.Entry<String, Integer>> entrySet = sut.entrySet();
        
        //standard
        Assert.assertNotNull(entrySet);
        Assert.assertEquals(UnmodifiableSet, entrySet.getClass());
        Assert.assertEquals(sut.size(), entrySet.size());
        TestUtils.assertListEquals(
                entrySet.stream().map(Map.Entry::getKey).collect(Collectors.toList()),
                keys, false);
        TestUtils.assertListEquals(
                entrySet.stream().map(Map.Entry::getValue).collect(Collectors.toList()),
                values, false);
        
        //immutability
        immutableViewValidator.accept(entrySet);
        
        //equality
        TestUtils.assertListEquals(ListUtility.toList(sut.entrySet()), sut.entrySet());
        Assert.assertNotSame(sut.entrySet(), sut.entrySet());
        
        //empty
        Assert.assertTrue(new IterableMap<>().orderedEntrySet().isEmpty());
    }
    
    /**
     * JUnit test of orderedEntrySet.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#orderedEntrySet()
     */
    @Test
    public void testOrderedEntrySet() throws Exception {
        final List<Map.Entry<String, Integer>> entrySet = sut.orderedEntrySet();
        
        //standard
        Assert.assertNotNull(entrySet);
        Assert.assertEquals(UnmodifiableRandomAccessList, entrySet.getClass());
        Assert.assertEquals(sut.size(), entrySet.size());
        TestUtils.assertListEquals(
                entrySet.stream().map(Map.Entry::getKey).collect(Collectors.toList()),
                keys);
        TestUtils.assertListEquals(
                entrySet.stream().map(Map.Entry::getValue).collect(Collectors.toList()),
                values);
        
        //immutability
        immutableViewValidator.accept(entrySet);
        
        //equality
        TestUtils.assertListEquals(sut.orderedEntrySet(), sut.orderedEntrySet());
        Assert.assertNotSame(sut.orderedEntrySet(), sut.orderedEntrySet());
        
        //empty
        Assert.assertTrue(new IterableMap<>().orderedEntrySet().isEmpty());
    }
    
    /**
     * JUnit test of keySet.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#keySet()
     */
    @Test
    public void testKeySet() throws Exception {
        final Set<String> keySet = sut.keySet();
        
        //standard
        Assert.assertNotNull(keySet);
        Assert.assertEquals(UnmodifiableSet, keySet.getClass());
        Assert.assertEquals(sut.size(), keySet.size());
        TestUtils.assertListEquals(
                ListUtility.toList(keySet),
                keys, false);
        
        //immutability
        immutableViewValidator.accept(keySet);
        
        //equality
        TestUtils.assertListEquals(ListUtility.toList(sut.keySet()), sut.keySet());
        Assert.assertNotSame(sut.keySet(), sut.keySet());
        
        //empty
        Assert.assertTrue(new IterableMap<>().keySet().isEmpty());
    }
    
    /**
     * JUnit test of orderedKeySet.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#orderedKeySet()
     */
    @Test
    public void testOrderedKeySet() throws Exception {
        final List<String> keySet = sut.orderedKeySet();
        
        //standard
        Assert.assertNotNull(keySet);
        Assert.assertEquals(UnmodifiableRandomAccessList, keySet.getClass());
        Assert.assertEquals(sut.size(), keySet.size());
        TestUtils.assertListEquals(keySet, keys);
        
        //immutability
        immutableViewValidator.accept(keySet);
        
        //equality
        TestUtils.assertListEquals(sut.orderedKeySet(), sut.orderedKeySet());
        Assert.assertNotSame(sut.orderedKeySet(), sut.orderedKeySet());
        
        //empty
        Assert.assertTrue(new IterableMap<>().orderedKeySet().isEmpty());
    }
    
    /**
     * JUnit test of values.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#values()
     */
    @Test
    public void testValues() throws Exception {
        final Collection<Integer> valueSet = sut.values();
        
        //standard
        Assert.assertNotNull(valueSet);
        Assert.assertEquals(UnmodifiableCollection, valueSet.getClass());
        Assert.assertEquals(sut.size(), valueSet.size());
        TestUtils.assertListEquals(
                ListUtility.toList(valueSet),
                values, false);
        
        //immutability
        immutableViewValidator.accept(valueSet);
        
        //equality
        TestUtils.assertListEquals(ListUtility.toList(sut.values()), sut.values());
        Assert.assertNotSame(sut.values(), sut.values());
        
        //empty
        Assert.assertTrue(new IterableMap<>().values().isEmpty());
    }
    
    /**
     * JUnit test of orderedValues.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#orderedValues()
     */
    @Test
    public void testOrderedValues() throws Exception {
        final List<Integer> valueSet = sut.orderedValues();
        
        //standard
        Assert.assertNotNull(valueSet);
        Assert.assertEquals(UnmodifiableRandomAccessList, valueSet.getClass());
        Assert.assertEquals(sut.size(), valueSet.size());
        TestUtils.assertListEquals(valueSet, values);
        
        //immutability
        immutableViewValidator.accept(valueSet);
        
        //equality
        TestUtils.assertListEquals(sut.orderedValues(), sut.orderedValues());
        Assert.assertNotSame(sut.orderedValues(), sut.orderedValues());
        
        //empty
        Assert.assertTrue(new IterableMap<>().orderedValues().isEmpty());
    }
    
    /**
     * JUnit test of put.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#put(int, Object, Object)
     * @see IterableMap#put(Object, Object)
     */
    @Test
    public void testPut() throws Exception {
        //standard
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.put(key, nextValue.incrementAndGet()));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //index
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.put(3, key, nextValue.incrementAndGet()));
            keys.add(3, key);
            values.add(3, nextValue.get());
        });
        
        //present
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.put(key, nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //present, index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.put(4, key, nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertNoException(() ->
                    sut.put(outOfBounds, "B", values.get(keys.indexOf("B"))));
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index: {}, Size: {}", outOfBounds, sut.size()), () ->
                    sut.put(outOfBounds, "~", -1));
        });
        Assert.assertEquals(sut.put(0, null, -1), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.put(0, "~", null), sut.remove("~"));
        Assert.assertEquals(sut.put(0, null, null), sut.remove(null));
        Assert.assertEquals(sut.put(null, -1), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.put("~", null), sut.remove("~"));
        Assert.assertEquals(sut.put(null, null), sut.remove(null));
    }
    
    /**
     * JUnit test of putIfAbsent.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#putIfAbsent(int, Object, Object)
     * @see IterableMap#putIfAbsent(Object, Object)
     */
    @Test
    public void testPutIfAbsent() throws Exception {
        //standard
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.putIfAbsent(key, nextValue.incrementAndGet()));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //index
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.putIfAbsent(3, key, nextValue.incrementAndGet()));
            keys.add(3, key);
            values.add(3, nextValue.get());
        });
        
        //present
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.putIfAbsent(key, nextValue.incrementAndGet())));
        
        //present, index
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.putIfAbsent(10, key, nextValue.incrementAndGet())));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertNoException(() ->
                    sut.putIfAbsent(outOfBounds, "B", -1));
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index: {}, Size: {}", outOfBounds, sut.size()), () ->
                    sut.putIfAbsent(outOfBounds, "~", -1));
        });
        Assert.assertEquals(sut.putIfAbsent(0, null, -1), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.putIfAbsent(0, "~", null), sut.remove("~"));
        Assert.assertEquals(sut.putIfAbsent(0, null, null), sut.remove(null));
        Assert.assertEquals(sut.putIfAbsent(null, -1), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.putIfAbsent("~", null), sut.remove("~"));
        Assert.assertEquals(sut.putIfAbsent(null, null), sut.remove(null));
    }
    
    /**
     * JUnit test of putAll.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#putAll(int, Map)
     * @see IterableMap#putAll(Map)
     */
    @Test
    public void testPutAll() throws Exception {
        final Map<String, Integer> testMap = MapUtility.emptyMap(LinkedHashMap.class);
        
        final Consumer<List<String>> testMapInitializer = (List<String> keySet) ->
                MapUtility.clearAndGet(testMap).putAll(MapUtility.mapOf(LinkedHashMap.class,
                        keySet,
                        keySet.stream().mapToInt(key -> nextValue.incrementAndGet()).boxed().collect(Collectors.toList())));
        
        //standard
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[1]);
            sut.putAll(testMap);
            keys.addAll(testMap.keySet());
            values.addAll(testMap.values());
        });
        
        //index
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[1]);
            sut.putAll(3, testMap);
            keys.addAll(3, testMap.keySet());
            values.addAll(3, testMap.values());
        });
        
        //present
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2]);
            sut.putAll(testMap);
            testMap.forEach((key, value) ->
                    values.set(keys.indexOf(key), value));
        });
        
        //present, index
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2]);
            sut.putAll(10, testMap);
            testMap.forEach((key, value) ->
                    values.set(keys.indexOf(key), value));
        });
        
        //mixed
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(keySets[2], keySets[1]));
            sut.putAll(testMap);
            keys.addAll(keySets[1]);
            values.addAll(Collections.nCopies(keySets[1].size(), 0));
            testMap.forEach((key, value) ->
                    values.set(keys.indexOf(key), value));
        });
        
        //mixed, index
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(keySets[2], keySets[1]));
            sut.putAll(13, testMap);
            keys.addAll(13, keySets[1]);
            values.addAll(13, Collections.nCopies(keySets[1].size(), 0));
            testMap.forEach((key, value) ->
                    values.set(keys.indexOf(key), value));
        });
        
        //empty
        sutTestRunner.accept(() -> {
            sut.putAll(MapUtility.emptyMap());
            sut.putAll(0, MapUtility.emptyMap());
        });
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertNoException(() ->
                    sut.putAll(outOfBounds, MapUtility.emptyMap()));
            TestUtils.assertNoException(() ->
                    sut.putAll(outOfBounds, MapUtility.clone(sut)));
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index: {}, Size: {}", outOfBounds, sut.size()), () ->
                    sut.putAll(outOfBounds, MapUtility.mapOf(new String[] {"b"}, new Integer[] {-1})));
        });
        TestUtils.assertException(NullPointerException.class, () ->
                sut.putAll(0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.putAll(null));
    }
    
    /**
     * JUnit test of replace.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#replace(int, Object)
     * @see IterableMap#replace(int, Object, Object)
     * @see IterableMap#replace(Object, Object)
     * @see IterableMap#replace(Object, Object, Object)
     */
    @Test
    public void testReplace() throws Exception {
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.replace(key, nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.replace(keys.indexOf(key), nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //expected
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.replace(key, values.get(keys.indexOf(key)), nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //expected, index
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.replace(keys.indexOf(key), values.get(keys.indexOf(key)), nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //unexpected
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.incrementAndGet())));
        
        //unexpected, index
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.replace(keys.indexOf(key), -1, nextValue.incrementAndGet())));
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.replace(key, nextValue.incrementAndGet())));
        
        //absent, expected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.replace(key, null, nextValue.incrementAndGet())));
        
        //absent, unexpected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.incrementAndGet())));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                    sut.replace(outOfBounds, -1));
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                    sut.replace(outOfBounds, -1, -1));
        });
        Assert.assertEquals(values.get(0), sut.replace(0, null));
        Assert.assertNull(sut.replace(0, values.get(0)));
        Assert.assertFalse(sut.replace(0, null, -1));
        Assert.assertFalse(sut.replace(0, -1, null));
        Assert.assertFalse(sut.replace(0, null, null));
        Assert.assertFalse(sut.replace(null, -1, -2));
        Assert.assertFalse(sut.replace("~", null, -2));
        Assert.assertFalse(sut.replace("~", -1, null));
        Assert.assertFalse(sut.replace(null, null, null));
        Assert.assertNull(sut.replace(null, -1));
        Assert.assertNull(sut.replace("~", null));
        Assert.assertNull(sut.replace(null, null));
    }
    
    /**
     * JUnit test of replaceAll.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#replaceAll(BiFunction)
     */
    @Test
    public void testReplaceAll() throws Exception {
        final BiFunction<String, Integer, Integer> mapper = (String key, Integer oldValue) -> -oldValue;
        
        //standard
        sutTestRunner.accept(() -> {
            sut.replaceAll(mapper);
            values.replaceAll(value -> mapper.apply(keys.get(values.indexOf(value)), value));
        });
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.replaceAll(null));
    }
    
    /**
     * JUnit test of remove.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#remove(int)
     * @see IterableMap#remove(int, Object)
     * @see IterableMap#remove(Object)
     * @see IterableMap#remove(Object, Object)
     */
    @Test
    public void testRemove() throws Exception {
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.remove(key));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.remove(keys.indexOf(key)));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //expected
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.remove(key, values.get(keys.indexOf(key))));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //expected, index
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.remove(keys.indexOf(key), values.get(keys.indexOf(key))));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //unexpected
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.remove(key, -1)));
        
        //unexpected, index
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.remove(keys.indexOf(key), Integer.valueOf(-1))));
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.remove(key)));
        
        //absent, expected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.remove(key, null)));
        
        //absent, unexpected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.remove(key, -1)));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                    sut.remove(outOfBounds));
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                    sut.remove(outOfBounds, Integer.valueOf(-1)));
        });
        Assert.assertFalse(sut.remove(0, null));
        Assert.assertFalse(sut.remove(null, -1));
        Assert.assertFalse(sut.remove("~", null));
        Assert.assertFalse(sut.remove(null, null));
        Assert.assertNull(sut.remove(null));
    }
    
    /**
     * JUnit test of clear.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#clear()
     */
    @Test
    public void testClear() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            sut.clear();
            keys.clear();
            values.clear();
        });
    }
    
    /**
     * JUnit test of size.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#size()
     */
    @Test
    public void testSize() throws Exception {
        //standard
        sutTestRunner.accept(() ->
                Stream.of(keys, values,
                        sut.entrySet(), sut.orderedEntrySet(), sut.exposedEntrySet(), sut.immutableEntrySet(),
                        sut.keySet(), sut.orderedKeySet(), sut.exposedKeySet(), sut.immutableKeySet(),
                        sut.values(), sut.orderedValues(), sut.exposedValues(), sut.immutableValues()
                ).forEach(components ->
                        Assert.assertEquals(components.size(), sut.size())));
        
        //empty
        sutTestRunner.accept(() -> {
            sut.clear();
            Assert.assertEquals(0, sut.size());
            keys.clear();
            values.clear();
        });
    }
    
    /**
     * JUnit test of isEmpty.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#isEmpty()
     */
    @Test
    public void testIsEmpty() throws Exception {
        //standard
        sutTestRunner.accept(() ->
                Assert.assertFalse(sut.isEmpty()));
        
        //empty
        sutTestRunner.accept(() -> {
            sut.clear();
            Assert.assertTrue(sut.isEmpty());
            keys.clear();
            values.clear();
        });
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#clone()
     */
    @Test
    public void testClone() throws Exception {
        IterableMap<String, Integer> clone;
        
        //standard
        clone = sut.clone();
        Assert.assertNotNull(clone);
        Assert.assertTrue(clone instanceof IterableMap);
        TestUtils.assertMapEquals(clone, sut);
        Assert.assertEquals(sut, clone);
        Assert.assertNotSame(sut, clone);
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals() throws Exception {
        Map<String, Integer> other;
        
        //standard
        other = IntStream.range(0, sut.size()).boxed().collect(
                MapCollectors.toMap(IterableMap::new, keys::get, values::get));
        TestUtils.assertMapEquals(other, sut);
        Assert.assertTrue(sut.equals(other));
        Assert.assertNotSame(sut, other);
        
        //wrong order
        other = IntStream.range(0, sut.size()).boxed().collect(Collectors.collectingAndThen(
                MapCollectors.toMap(IterableMap::new, keys::get, values::get),
                map -> {
                    IntStream.range(0, 10).map(i -> 10 - i - 1).forEach(i -> {
                        map.remove(sut.getKey(i));
                        map.put(sut.getKey(i), sut.get(i));
                    });
                    return map;
                }));
        TestUtils.assertMapEquals(other, sut);
        Assert.assertFalse(sut.equals(other));
        Assert.assertNotSame(sut, other);
        
        //normal map
        other = MapUtility.mapOf(LinkedHashMap.class, keys, values);
        TestUtils.assertMapEquals(other, sut);
        Assert.assertFalse(sut.equals(other));
        Assert.assertNotSame(sut, other);
        
        //empty
        Assert.assertTrue(new IterableMap<>().equals(new IterableMap<>()));
        TestUtils.assertMapEquals(new IterableMap<>(), new IterableMap<>());
        Assert.assertNotSame(new IterableMap<>(), new IterableMap<>());
        Assert.assertFalse(new IterableMap<>().equals(MapUtility.emptyMap()));
        TestUtils.assertMapEquals(new IterableMap<>(), MapUtility.emptyMap());
        Assert.assertNotSame(new IterableMap<>(), MapUtility.emptyMap());
        
        //invalid
        Assert.assertFalse(sut.equals(""));
        Assert.assertFalse(sut.equals(ListUtility.emptyList()));
        Assert.assertFalse(sut.equals(new File(".")));
        Assert.assertFalse(sut.equals(null));
    }
    
    /**
     * JUnit test of compute.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#compute(int, Object, BiFunction)
     * @see IterableMap#compute(int, BiFunction)
     * @see IterableMap#compute(Object, BiFunction)
     */
    @Test
    public void testCompute() throws Exception {
        final BiFunction<String, Integer, Integer> mapper = (String key, Integer oldValue) -> (Optional.ofNullable(oldValue).orElse(0) + nextValue.incrementAndGet() + key.charAt(0));
        final BiFunction<String, Boolean, Integer> mapperTest = (String key, Boolean isAdd) -> (mapper.apply(key, (isAdd ? 0 : values.get(keys.indexOf(key)))) - 1);
        final BiFunction<String, Integer, Integer> nullMapper = (String key, Integer oldValue) -> null;
        final BiFunction<String, Integer, Integer> mockMapper = (String key, Integer oldValue) -> -1;
        
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(key, mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        
        //index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(keys.indexOf(key), mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        
        //put index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(5, key, mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        
        //absent
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.compute(key, mapper));
            keys.add(key);
            values.add(mapperTest.apply(key, true));
        });
        
        //absent, put index
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.compute(5, key, mapper));
            keys.add(5, key);
            values.add(5, mapperTest.apply(key, true));
        });
        
        //removal
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(key, nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(keys.indexOf(key), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, put index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(5, key, nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index: {}, Size: {}", outOfBounds, sut.size()), () ->
                    sut.compute(outOfBounds, "~", mockMapper));
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                    sut.compute(outOfBounds, mockMapper));
        });
        Assert.assertNull(sut.compute(0, null, mockMapper));
        Assert.assertEquals(sut.compute(0, null, mockMapper), sut.compute(0, null, nullMapper));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute(0, "~", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute(0, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute(0, null));
        Assert.assertNull(sut.compute(null, mockMapper));
        Assert.assertEquals(sut.compute(null, mockMapper), sut.compute(null, nullMapper));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute("~", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute(null, null));
    }
    
    /**
     * JUnit test of computeIfAbsent.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#computeIfAbsent(int, Object, Function)
     * @see IterableMap#computeIfAbsent(Object, Function)
     */
    @Test
    public void testComputeIfAbsent() throws Exception {
        final Function<String, Integer> mapper = (String key) -> (nextValue.incrementAndGet() + key.charAt(0));
        final Function<String, Integer> mapperTest = (String key) -> (mapper.apply(key) - 1);
        final Function<String, Integer> nullMapper = (String key) -> null;
        final Function<String, Integer> mockMapper = (String key) -> -1;
        
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(key, mapper)));
        
        //put index
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(5, key, mapper)));
        
        //absent
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.computeIfAbsent(key, mapper));
            keys.add(key);
            values.add(mapperTest.apply(key));
        });
        
        //absent, put index
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.computeIfAbsent(5, key, mapper));
            keys.add(5, key);
            values.add(5, mapperTest.apply(key));
        });
        
        //removal
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(key, nullMapper)));
        
        //removal, put index
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(5, key, nullMapper)));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds ->
                TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index: {}, Size: {}", outOfBounds, sut.size()), () ->
                        sut.computeIfAbsent(outOfBounds, "~", mockMapper)));
        Assert.assertNull(sut.computeIfAbsent(0, null, mockMapper));
        Assert.assertEquals(sut.computeIfAbsent(0, null, mockMapper), sut.remove(null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent(0, "~", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent(0, null, null));
        Assert.assertNull(sut.computeIfAbsent(null, mockMapper));
        Assert.assertEquals(sut.computeIfAbsent(null, mockMapper), sut.remove(null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent("~", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent(null, null));
    }
    
    /**
     * JUnit test of computeIfPresent.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#computeIfPresent(int, BiFunction)
     * @see IterableMap#computeIfPresent(Object, BiFunction)
     */
    @Test
    public void testComputeIfPresent() throws Exception {
        final BiFunction<String, Integer, Integer> mapper = (String key, Integer oldValue) -> (Optional.ofNullable(oldValue).orElse(0) + nextValue.incrementAndGet() + key.charAt(0));
        final BiFunction<String, Boolean, Integer> mapperTest = (String key, Boolean isAdd) -> (mapper.apply(key, (isAdd ? 0 : values.get(keys.indexOf(key)))) - 1);
        final BiFunction<String, Integer, Integer> nullMapper = (String key, Integer oldValue) -> null;
        final BiFunction<String, Integer, Integer> mockMapper = (String key, Integer oldValue) -> -1;
        
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.computeIfPresent(key, mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        
        //index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.computeIfPresent(keys.indexOf(key), mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.computeIfPresent(key, mapper)));
        
        //removal
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.computeIfPresent(key, nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.computeIfPresent(keys.indexOf(key), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds ->
                TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                        sut.computeIfPresent(outOfBounds, mockMapper)));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfPresent(0, null));
        Assert.assertNull(sut.computeIfPresent(null, mockMapper));
        Assert.assertNull(sut.computeIfPresent("~", null));
        Assert.assertNull(sut.computeIfPresent(null, null));
    }
    
    /**
     * JUnit test of merge.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#merge(int, Object, Object, BiFunction)
     * @see IterableMap#merge(int, Object, BiFunction)
     * @see IterableMap#merge(Object, Object, BiFunction)
     */
    @Test
    public void testMerge() throws Exception {
        final BiFunction<Integer, Integer, Integer> nullMapper = (Integer oldValue, Integer merge) -> null;
        
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(key, nextValue.incrementAndGet(), Integer::sum));
            values.set(keys.indexOf(key), (values.get(keys.indexOf(key)) + nextValue.get()));
        });
        
        //index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(keys.indexOf(key), nextValue.incrementAndGet(), Integer::sum));
            values.set(keys.indexOf(key), (values.get(keys.indexOf(key)) + nextValue.get()));
        });
        
        //put index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(5, key, nextValue.incrementAndGet(), Integer::sum));
            values.set(keys.indexOf(key), (values.get(keys.indexOf(key)) + nextValue.get()));
        });
        
        //absent
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.merge(key, nextValue.incrementAndGet(), Integer::sum));
            keys.add(key);
            values.add(keys.indexOf(key), nextValue.get());
        });
        
        //absent, put index
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.merge(5, key, nextValue.incrementAndGet(), Integer::sum));
            keys.add(5, key);
            values.add(5, nextValue.get());
        });
        
        //removal
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(key, nextValue.get(), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(keys.indexOf(key), nextValue.get(), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, put index
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(5, key, nextValue.get(), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index: {}, Size: {}", outOfBounds, sut.size()), () ->
                    sut.merge(outOfBounds, "~", -1, Integer::sum));
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                    sut.merge(outOfBounds, -1, Integer::sum));
        });
        Assert.assertNull(sut.merge(0, null, -1, Integer::sum));
        Assert.assertEquals(-1, sut.merge(0, null, -1, nullMapper).intValue());
        Assert.assertNull(sut.merge(0, "~", null, Integer::sum));
        Assert.assertNull(sut.merge(0, "~", -1, null));
        Assert.assertEquals(-1, sut.merge(0, "~", null, nullMapper).intValue());
        Assert.assertNull(sut.merge(0, null, null, Integer::sum));
        Assert.assertNull(sut.merge(0, null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.merge(0, null, Integer::sum));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.merge(0, -1, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.merge(0, null, null));
        Assert.assertNull(sut.merge(null, -1, Integer::sum));
        Assert.assertEquals(-1, sut.merge(null, -1, nullMapper).intValue());
        Assert.assertNull(sut.merge("~", null, Integer::sum));
        Assert.assertNull(sut.merge("~", -1, null));
        Assert.assertEquals(-1, sut.merge("~", null, nullMapper).intValue());
        Assert.assertNull(sut.merge(null, null, null));
    }
    
    /**
     * JUnit test of forEach.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#forEach(BiConsumer)
     * @see IterableMap#forEach(Consumer)
     */
    @Test
    public void testForEach() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            sut.forEach((key, value) ->
                    sut.replace(key, -value));
            values.replaceAll(value -> -value);
        });
        
        //entries
        sutTestRunner.accept(() -> {
            sut.forEach(entry ->
                    sut.replace(entry.getKey(), -entry.getValue()));
            values.replaceAll(value -> -value);
        });
        
        //order
        sutTestRunner.accept(() -> {
            sut.forEach((key, value) ->
                    sut.replace(key, nextValue.incrementAndGet()));
            values.replaceAll(value -> (nextValue.get() - sut.size() + values.indexOf(value) + 1));
        });
        
        //modification
        sutTestRunner.accept(() ->
                TestUtils.assertNoException(() ->
                        sut.forEach((key, value) -> {
                            sut.remove(key);
                            sut.put(key, value);
                        })));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.forEach((BiConsumer<String, Integer>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.forEach((Consumer<Map.Entry<String, Integer>>) null));
    }
    
    /**
     * JUnit test of indexedForEach.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#indexedForEach(BiConsumer)
     */
    @Test
    public void testIndexedForEach() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            sut.indexedForEach((entry, i) ->
                    sut.replace(entry.getKey(), (entry.getValue() * (NumberUtility.isEven(i) ? -1 : 1))));
            values.replaceAll(value -> (value * (NumberUtility.isEven(values.indexOf(value)) ? -1 : 1)));
        });
        
        //order
        sutTestRunner.accept(() -> {
            sut.forEach((key, value) ->
                    sut.replace(key, nextValue.incrementAndGet()));
            values.replaceAll(value -> (nextValue.get() - sut.size() + values.indexOf(value) + 1));
        });
        
        //modification
        sutTestRunner.accept(() ->
                TestUtils.assertNoException(() ->
                        sut.indexedForEach((entry, i) -> {
                            sut.remove(entry.getKey());
                            sut.put(entry.getKey(), entry.getValue());
                        })));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.indexedForEach(null));
    }
    
    /**
     * JUnit test of iterator.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#iterator()
     */
    @Test
    public void testIterator() throws Exception {
        final AtomicReference<Iterator<Map.Entry<String, Integer>>> iterator = new AtomicReference<>(null);
        
        final Consumer<Runnable> iteratorTestRunner = (Runnable test) ->
                sutTestRunner.accept(() -> {
                    iterator.set(sut.iterator());
                    Assert.assertNotNull(iterator.get());
                    test.run();
                    Assert.assertFalse(iterator.get().hasNext());
                    TestUtils.assertException(NoSuchElementException.class, () ->
                            iterator.get().next());
                });
        
        //standard
        iteratorTestRunner.accept(() ->
                IntStream.range(0, sut.size()).forEach(i -> {
                    Assert.assertTrue(iterator.get().hasNext());
                    Assert.assertEquals(Map.entry(keys.get(i), values.get(i)), iterator.get().next());
                }));
        
        //for each
        iteratorTestRunner.accept(() ->
                iterator.get().forEachRemaining(entry -> {
                    Assert.assertTrue(keys.contains(entry.getKey()));
                    Assert.assertTrue(values.contains(entry.getValue()));
                    Assert.assertEquals(values.get(keys.indexOf(entry.getKey())), entry.getValue());
                }));
        
        //edit
        iteratorTestRunner.accept(() ->
                IntStream.range(0, sut.size()).forEach(i -> {
                    Assert.assertEquals(values.get(i), iterator.get().next().setValue(-1));
                    values.set(i, -1);
                }));
        
        //remove
        iteratorTestRunner.accept(() -> {
            TestUtils.assertException(IllegalStateException.class, () ->
                    iterator.get().remove());
            IntStream.range(0, sut.size()).forEach(i -> {
                final Map.Entry<String, Integer> entry = iterator.get().next();
                iterator.get().remove();
                keys.remove(entry.getKey());
                values.remove(entry.getValue());
            });
            TestUtils.assertException(IllegalStateException.class, () ->
                    iterator.get().remove());
        });
        
        //equality
        Assert.assertNotSame(sut.iterator(), sut.iterator());
    }
    
    /**
     * JUnit test of spliterator.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#spliterator()
     */
    @Test
    public void testSpliterator() throws Exception {
        final AtomicReference<Spliterator<Map.Entry<String, Integer>>> spliterator1 = new AtomicReference<>(null);
        final AtomicReference<Spliterator<Map.Entry<String, Integer>>> spliterator2 = new AtomicReference<>(null);
        
        final Runnable spliteratorInitializer = () -> {
            spliterator1.set(sut.spliterator());
            Assert.assertNotNull(spliterator1.get());
            spliterator2.set(spliterator1.get().trySplit());
            Assert.assertNotNull(spliterator2.get());
        };
        final Consumer<Map.Entry<String, Integer>> iteratorEntryValidator = (Map.Entry<String, Integer> entry) -> {
            Assert.assertTrue(keys.contains(entry.getKey()));
            Assert.assertTrue(values.contains(entry.getValue()));
            Assert.assertEquals(values.get(keys.indexOf(entry.getKey())), entry.getValue());
        };
        
        //standard
        sutTestRunner.accept(() -> {
            spliteratorInitializer.run();
            IntStream.range(0, sut.size()).forEach(i ->
                    Assert.assertTrue(
                            Stream.of(spliterator1, spliterator2).map(AtomicReference::get).anyMatch(spliterator ->
                                    spliterator.tryAdvance(entry ->
                                            Assert.assertEquals(Map.entry(keys.get(i), values.get(i)), entry)))));
            Assert.assertFalse(spliterator1.get().tryAdvance(Objects::nonNull));
            Assert.assertFalse(spliterator2.get().tryAdvance(Objects::nonNull));
        });
        
        //for each
        sutTestRunner.accept(() -> {
            spliteratorInitializer.run();
            Stream.of(spliterator1, spliterator2).map(AtomicReference::get).forEach(spliterator -> {
                spliterator.forEachRemaining(iteratorEntryValidator);
                Assert.assertFalse(spliterator.tryAdvance(Objects::nonNull));
            });
        });
        
        //edit
        sutTestRunner.accept(() -> {
            spliteratorInitializer.run();
            Stream.of(spliterator1, spliterator2).map(AtomicReference::get).forEach(spliterator ->
                    spliterator.tryAdvance(entry -> {
                        Assert.assertEquals(values.get(keys.indexOf(entry.getKey())), entry.setValue(-1));
                        values.set(keys.indexOf(entry.getKey()), -1);
                    }));
        });
        
        //equality
        Assert.assertNotSame(sut.spliterator(), sut.spliterator());
    }
    
}
