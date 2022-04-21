/*
 * File:    IterableMapTest.java
 * Package: commons.object.collection.map
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.map;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
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
     * A value to use for testing.
     */
    private static final AtomicInteger nextValue = new AtomicInteger(0);
    
    
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
    
    
    //Functions
    
    /**
     * Validates the content and order of the system under test.
     */
    private final Runnable sutValidator = () -> {
        final List<Map.Entry<String, Integer>> entrySetOrdered = sut.entrySetOrdered();
        final List<String> keySetOrdered = sut.keySetOrdered();
        final List<Integer> valueSetOrdered = sut.valuesOrdered();
        
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
     * Gets a value of the system under test given a key.
     */
    private final Function<String, Integer> valueGetter = (String key) -> values.get(keys.indexOf(key));
    
    
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
        keys = ListUtility.clone(keySets[2]);
        values = IntStream.range(0, keys.size()).mapToObj(i -> nextValue.getAndIncrement()).collect(Collectors.toList());
        sut = new IterableMap<>(MapUtility.mapOf(LinkedHashMap.class, keys, values));
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
        TestUtils.assertMapEquals(map1, sourceMap);
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(map1, List.class, "keyList"),
                keys, false);
        
        //iterable map
        map2 = new IterableMap<>(map1);
        Assert.assertNotNull(map2);
        TestUtils.assertMapEquals(map2, map1);
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(map2, List.class, "keyList"),
                TestAccess.getFieldValue(map1, List.class, "keyList"), true);
        
        //empty
        map3 = new IterableMap<>();
        Assert.assertNotNull(map3);
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
        IntStream.range(0, sut.size()).forEach(i ->
                Assert.assertEquals(keys.get(i), sut.getKey(i)));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds ->
                TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                        sut.getKey(outOfBounds)));
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
        keySets[2].forEach(key ->
                Assert.assertEquals(keys.indexOf(key), sut.indexOf(key)));
        
        //absent
        keySets[3].forEach(key ->
                Assert.assertEquals(-1, sut.indexOf(key)));
        
        //invalid
        Assert.assertEquals(-1, sut.indexOf(null));
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
        IntStream.range(0, sut.size()).forEach(i ->
                Assert.assertEquals(values.get(i), sut.get(keys.get(i))));
        
        //index
        IntStream.range(0, sut.size()).forEach(i ->
                Assert.assertEquals(values.get(i), sut.get(i)));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds ->
                TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                        sut.get(outOfBounds)));
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
        IntStream.range(0, sut.size()).forEach(i -> {
            final Map.Entry<String, Integer> entry = sut.getEntry(keys.get(i));
            Assert.assertEquals(keys.get(i), entry.getKey());
            Assert.assertEquals(values.get(i), entry.getValue());
        });
        
        //index
        IntStream.range(0, sut.size()).forEach(i -> {
            final Map.Entry<String, Integer> entry = sut.getEntry(i);
            Assert.assertEquals(keys.get(i), entry.getKey());
            Assert.assertEquals(values.get(i), entry.getValue());
        });
        
        //edit
        Assert.assertEquals(values.get(3),
                sut.getEntry(3).setValue(-1));
        values.set(3, -1);
        sutValidator.run();
        
        //equality
        Assert.assertEquals(sut.getEntry(0), sut.getEntry(0));
        Assert.assertSame(sut.getEntry(0), sut.getEntry(0));
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds ->
                TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                        sut.getEntry(outOfBounds)));
        Assert.assertNull(sut.getEntry(null));
    }
    
    /**
     * JUnit test of entrySetOrdered.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#entrySetOrdered()
     */
    @Test
    public void testEntrySetOrdered() throws Exception {
        final List<Map.Entry<String, Integer>> entrySet = sut.entrySetOrdered();
        
        //standard
        Assert.assertEquals(sut.size(), entrySet.size());
        TestUtils.assertListEquals(
                entrySet.stream().map(Map.Entry::getKey).collect(Collectors.toList()),
                keys);
        TestUtils.assertListEquals(
                entrySet.stream().map(Map.Entry::getValue).collect(Collectors.toList()),
                values);
        
        //edit
        Assert.assertEquals(values.get(3),
                entrySet.get(3).setValue(-1));
        values.set(3, -1);
        sutValidator.run();
        
        //equality
        TestUtils.assertListEquals(sut.entrySetOrdered(), sut.entrySetOrdered());
        Assert.assertNotSame(sut.entrySetOrdered(), sut.entrySetOrdered());
        
        //empty
        Assert.assertTrue(new IterableMap<>().entrySetOrdered().isEmpty());
    }
    
    /**
     * JUnit test of keySetOrdered.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#keySetOrdered()
     */
    @Test
    public void testKeySetOrdered() throws Exception {
        final List<String> keySet = sut.keySetOrdered();
        
        //standard
        Assert.assertEquals(sut.size(), keySet.size());
        TestUtils.assertListEquals(keySet, keys);
        
        //equality
        TestUtils.assertListEquals(sut.keySetOrdered(), sut.keySetOrdered());
        Assert.assertNotSame(sut.keySetOrdered(), sut.keySetOrdered());
        
        //empty
        Assert.assertTrue(new IterableMap<>().keySetOrdered().isEmpty());
    }
    
    /**
     * JUnit test of valuesOrdered.
     *
     * @throws Exception When there is an exception.
     * @see IterableMap#valuesOrdered()
     */
    @Test
    public void testValuesOrdered() throws Exception {
        final List<Integer> valueSet = sut.valuesOrdered();
        
        //standard
        Assert.assertEquals(sut.size(), valueSet.size());
        TestUtils.assertListEquals(valueSet, values);
        
        //equality
        TestUtils.assertListEquals(sut.valuesOrdered(), sut.valuesOrdered());
        Assert.assertNotSame(sut.valuesOrdered(), sut.valuesOrdered());
        
        //empty
        Assert.assertTrue(new IterableMap<>().valuesOrdered().isEmpty());
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
        keySets[0].forEach(key -> {
            Assert.assertNull(
                    sut.put(key, nextValue.incrementAndGet()));
            keys.add(key);
            values.add(nextValue.get());
        });
        sutValidator.run();
        
        //index
        keySets[1].forEach(key -> {
            Assert.assertNull(
                    sut.put(3, key, nextValue.incrementAndGet()));
            keys.add(3, key);
            values.add(3, nextValue.get());
        });
        sutValidator.run();
        
        //present
        keySets[0].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.put(key, nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        sutValidator.run();
        
        //present, index
        keySets[1].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.put(4, key, nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        sutValidator.run();
        
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
        keySets[0].forEach(key -> {
            Assert.assertNull(
                    sut.putIfAbsent(key, nextValue.incrementAndGet()));
            keys.add(key);
            values.add(nextValue.get());
        });
        sutValidator.run();
        
        //index
        keySets[1].forEach(key -> {
            Assert.assertNull(
                    sut.putIfAbsent(3, key, nextValue.incrementAndGet()));
            keys.add(3, key);
            values.add(3, nextValue.get());
        });
        sutValidator.run();
        
        //present
        keySets[0].forEach(key ->
                Assert.assertEquals(valueGetter.apply(key),
                        sut.putIfAbsent(key, nextValue.incrementAndGet())));
        sutValidator.run();
        
        //present, index
        keySets[1].forEach(key ->
                Assert.assertEquals(valueGetter.apply(key),
                        sut.putIfAbsent(10, key, nextValue.incrementAndGet())));
        sutValidator.run();
        
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
        final AtomicReference<Map<String, Integer>> testMap = new AtomicReference<>(null);
        
        final Consumer<List<String>> testMapInitializer = (List<String> keySet) ->
                testMap.set(MapUtility.mapOf(LinkedHashMap.class,
                        keySet,
                        Collections.nCopies(keySet.size(), nextValue.incrementAndGet())));
        
        //standard
        testMapInitializer.accept(keySets[0]);
        sut.putAll(testMap.get());
        keys.addAll(testMap.get().keySet());
        values.addAll(testMap.get().values());
        sutValidator.run();
        
        //index
        testMapInitializer.accept(keySets[1]);
        sut.putAll(3, testMap.get());
        keys.addAll(3, testMap.get().keySet());
        values.addAll(3, testMap.get().values());
        sutValidator.run();
        
        //present
        testMapInitializer.accept(keySets[0]);
        sut.putAll(testMap.get());
        testMap.get().forEach((key, value) ->
                values.set(keys.indexOf(key), value));
        sutValidator.run();
        
        //present, index
        testMapInitializer.accept(keySets[1]);
        sut.putAll(10, testMap.get());
        testMap.get().forEach((key, value) ->
                values.set(keys.indexOf(key), value));
        sutValidator.run();
        
        //edge case
        testMapInitializer.accept(ListUtility.addAndGet(ListUtility.clone(keySets[0]), "."));
        sut.putAll(testMap.get());
        keys.add(".");
        values.add(testMap.get().get("."));
        testMap.get().forEach((key, value) ->
                values.set(keys.indexOf(key), value));
        sutValidator.run();
        
        //edge case, index
        testMapInitializer.accept(ListUtility.addAndGet(ListUtility.clone(keySets[1]), "?"));
        sut.putAll(13, testMap.get());
        keys.add(13, "?");
        values.add(13, testMap.get().get("?"));
        testMap.get().forEach((key, value) ->
                values.set(keys.indexOf(key), value));
        sutValidator.run();
        
        //empty
        sut.putAll(0, MapUtility.emptyMap());
        sut.putAll(MapUtility.emptyMap());
        sutValidator.run();
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertNoException(() ->
                    sut.putAll(outOfBounds, MapUtility.emptyMap()));
            TestUtils.assertNoException(() ->
                    sut.putAll(outOfBounds, testMap.get()));
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
        ListUtility.selectN(keySets[2], 3).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.replace(key, nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        sutValidator.run();
        
        //index
        ListUtility.selectN(keySets[2], 3).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.replace(keys.indexOf(key), nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        sutValidator.run();
        
        //expected
        ListUtility.selectN(keySets[2], 3).forEach(key -> {
            Assert.assertTrue(
                    sut.replace(key, valueGetter.apply(key), nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        sutValidator.run();
        
        //expected, index
        ListUtility.selectN(keySets[2], 3).forEach(key -> {
            Assert.assertTrue(
                    sut.replace(keys.indexOf(key), valueGetter.apply(key), nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        sutValidator.run();
        
        //unexpected
        ListUtility.selectN(keySets[2], 3).forEach(key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.incrementAndGet())));
        sutValidator.run();
        
        //unexpected, index
        ListUtility.selectN(keySets[2], 3).forEach(key ->
                Assert.assertFalse(
                        sut.replace(keys.indexOf(key), -1, nextValue.incrementAndGet())));
        sutValidator.run();
        
        //absent
        ListUtility.selectN(keySets[3], 3).forEach(key ->
                Assert.assertNull(
                        sut.replace(key, nextValue.incrementAndGet())));
        sutValidator.run();
        
        //absent, expecting
        ListUtility.selectN(keySets[3], 3).forEach(key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.incrementAndGet())));
        sutValidator.run();
        
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
        //standard
        sut.replaceAll((key, value) -> -value);
        values.replaceAll(i -> -i);
        sutValidator.run();
        
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
        ListUtility.selectN(keys, 3).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.remove(key));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
        //index
        ListUtility.selectN(keys, 3).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.remove(keys.indexOf(key)));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
        //expected
        ListUtility.selectN(keys, 3).forEach(key -> {
            Assert.assertTrue(
                    sut.remove(key, valueGetter.apply(key)));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
        //expected, index
        ListUtility.selectN(keys, 3).forEach(key -> {
            Assert.assertTrue(
                    sut.remove(keys.indexOf(key), valueGetter.apply(key)));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
        //unexpected
        ListUtility.selectN(keys, 3).forEach(key ->
                Assert.assertFalse(
                        sut.remove(key, -1)));
        sutValidator.run();
        
        //unexpected, index
        ListUtility.selectN(keys, 3).forEach(key ->
                Assert.assertFalse(
                        sut.remove(keys.indexOf(key), Integer.valueOf(-1))));
        sutValidator.run();
        
        //absent
        ListUtility.selectN(keySets[3], 3).forEach(key ->
                Assert.assertNull(
                        sut.remove(key)));
        sutValidator.run();
        
        //absent, expecting
        ListUtility.selectN(keySets[3], 3).forEach(key ->
                Assert.assertFalse(
                        sut.remove(key, -1)));
        sutValidator.run();
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                    sut.remove(outOfBounds));
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                    sut.remove(outOfBounds, Integer.valueOf(-1)));
        });
        Assert.assertFalse(sut.remove(0, null));
        Assert.assertNull(sut.remove(null));
        Assert.assertFalse(sut.remove(null, -1));
        Assert.assertFalse(sut.remove("~", null));
        Assert.assertFalse(sut.remove(null, null));
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
        sut.clear();
        Assert.assertTrue(sut.isEmpty());
        keys.clear();
        values.clear();
        sutValidator.run();
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
                MapCollectors.toMap(IterableMap::new, (i -> keys.get(i)), (i -> values.get(i))));
        TestUtils.assertMapEquals(other, sut);
        Assert.assertTrue(sut.equals(other));
        Assert.assertNotSame(sut, other);
        
        //wrong order
        other = IntStream.range(0, sut.size()).boxed().collect(Collectors.collectingAndThen(
                MapCollectors.toMap(IterableMap::new, (i -> keys.get(i)), (i -> values.get(i))),
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
        final BiFunction<String, Boolean, Integer> mapperTest = (String key, Boolean isAdd) -> (mapper.apply(key, (isAdd ? 0 : valueGetter.apply(key))) - 1);
        final BiFunction<String, Integer, Integer> nullMapper = (String key, Integer oldValue) -> null;
        final BiFunction<String, Integer, Integer> mockMapper = (String key, Integer oldValue) -> -1;
        
        //standard
        keySets[2].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.compute(key, mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        sutValidator.run();
        
        //index
        keySets[2].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.compute(keys.indexOf(key), mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        sutValidator.run();
        
        //put index
        keySets[2].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.compute(5, key, mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        sutValidator.run();
        
        //absent
        keySets[0].forEach(key -> {
            Assert.assertNull(
                    sut.compute(key, mapper));
            keys.add(key);
            values.add(mapperTest.apply(key, true));
        });
        sutValidator.run();
        
        //removal
        keySets[0].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.compute(key, nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
        //removal, index
        ListUtility.selectN(keys, 4).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.compute(keys.indexOf(key), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
        //absent, put index
        keySets[3].forEach(key -> {
            Assert.assertNull(
                    sut.compute(5, key, mapper));
            keys.add(5, key);
            values.add(5, mapperTest.apply(key, true));
        });
        sutValidator.run();
        
        //removal, put index
        ListUtility.reverse(keySets[3]).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.compute(5, key, nullMapper));
            values.remove(5);
            keys.remove(5);
        });
        sutValidator.run();
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds -> {
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index: {}, Size: {}", outOfBounds, sut.size()), () ->
                    sut.compute(outOfBounds, "~", mockMapper));
            TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index {} out of bounds for length {}", outOfBounds, sut.size()), () ->
                    sut.compute(outOfBounds, mockMapper));
        });
        Assert.assertNull(sut.compute(0, null, mockMapper));
        Assert.assertEquals(-1, sut.compute(0, null, mockMapper).intValue());
        Assert.assertEquals(-1, sut.compute(0, null, nullMapper).intValue());
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute(0, "~", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute(0, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute(0, null));
        Assert.assertNull(sut.compute(null, mockMapper));
        Assert.assertEquals(-1, sut.compute(null, mockMapper).intValue());
        Assert.assertEquals(-1, sut.compute(null, nullMapper).intValue());
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
        keySets[2].forEach(key ->
                Assert.assertEquals(valueGetter.apply(key),
                        sut.computeIfAbsent(key, mapper)));
        sutValidator.run();
        
        //put index
        keySets[2].forEach(key ->
                Assert.assertEquals(valueGetter.apply(key),
                        sut.computeIfAbsent(5, key, mapper)));
        sutValidator.run();
        
        //absent
        keySets[0].forEach(key -> {
            Assert.assertNull(
                    sut.computeIfAbsent(key, mapper));
            keys.add(key);
            values.add(mapperTest.apply(key));
        });
        sutValidator.run();
        
        //removal
        keySets[0].forEach(key ->
                Assert.assertEquals(valueGetter.apply(key),
                        sut.computeIfAbsent(key, nullMapper)));
        sutValidator.run();
        
        //absent, put index
        keySets[3].forEach(key -> {
            Assert.assertNull(
                    sut.computeIfAbsent(5, key, mapper));
            keys.add(5, key);
            values.add(5, mapperTest.apply(key));
        });
        sutValidator.run();
        
        //removal, put index
        ListUtility.reverse(keySets[3]).forEach(key ->
                Assert.assertEquals(valueGetter.apply(key),
                        sut.computeIfAbsent(5, key, nullMapper)));
        sutValidator.run();
        
        //invalid
        IntStream.of((sut.size() + 1), -1).forEach(outOfBounds ->
                TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index: {}, Size: {}", outOfBounds, sut.size()), () ->
                        sut.computeIfAbsent(outOfBounds, "~", mockMapper)));
        Assert.assertNull(sut.computeIfAbsent(0, null, mockMapper));
        Assert.assertEquals(-1, sut.computeIfAbsent(0, null, mockMapper).intValue());
        Assert.assertEquals(-1, sut.remove(null).intValue());
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent(0, "~", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent(0, null, null));
        Assert.assertNull(sut.computeIfAbsent(null, mockMapper));
        Assert.assertEquals(-1, sut.computeIfAbsent(null, mockMapper).intValue());
        Assert.assertEquals(-1, sut.remove(null).intValue());
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
        final BiFunction<String, Boolean, Integer> mapperTest = (String key, Boolean isAdd) -> (mapper.apply(key, (isAdd ? 0 : valueGetter.apply(key))) - 1);
        final BiFunction<String, Integer, Integer> nullMapper = (String key, Integer oldValue) -> null;
        final BiFunction<String, Integer, Integer> mockMapper = (String key, Integer oldValue) -> -1;
        
        //standard
        keySets[2].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.computeIfPresent(key, mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        sutValidator.run();
        
        //index
        keySets[2].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.computeIfPresent(keys.indexOf(key), mapper));
            values.set(keys.indexOf(key), mapperTest.apply(key, false));
        });
        sutValidator.run();
        
        //absent
        keySets[0].forEach(key ->
                Assert.assertNull(
                        sut.computeIfPresent(key, mapper)));
        sutValidator.run();
        
        //removal
        ListUtility.selectN(keys, 4).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.computeIfPresent(key, nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
        //removal, index
        ListUtility.selectN(keys, 4).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.computeIfPresent(keys.indexOf(key), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
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
        keySets[2].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.merge(key, nextValue.incrementAndGet(), Integer::sum));
            values.set(keys.indexOf(key), (valueGetter.apply(key) + nextValue.get()));
        });
        sutValidator.run();
        
        //index
        keySets[2].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.merge(keys.indexOf(key), nextValue.incrementAndGet(), Integer::sum));
            values.set(keys.indexOf(key), (valueGetter.apply(key) + nextValue.get()));
        });
        sutValidator.run();
        
        //put index
        keySets[2].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.merge(5, key, nextValue.incrementAndGet(), Integer::sum));
            values.set(keys.indexOf(key), (valueGetter.apply(key) + nextValue.get()));
        });
        sutValidator.run();
        
        //absent
        keySets[0].forEach(key -> {
            Assert.assertNull(
                    sut.merge(key, nextValue.incrementAndGet(), Integer::sum));
            keys.add(key);
            values.add(keys.indexOf(key), nextValue.get());
        });
        sutValidator.run();
        
        //removal
        keySets[0].forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.merge(key, nextValue.incrementAndGet(), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
        //removal, index
        ListUtility.selectN(keys, 4).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.merge(keys.indexOf(key), nextValue.incrementAndGet(), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        sutValidator.run();
        
        //absent, put index
        keySets[3].forEach(key -> {
            Assert.assertNull(
                    sut.merge(5, key, nextValue.incrementAndGet(), Integer::sum));
            keys.add(5, key);
            values.add(5, nextValue.get());
        });
        sutValidator.run();
        
        //removal, put index
        ListUtility.reverse(keySets[3]).forEach(key -> {
            Assert.assertEquals(valueGetter.apply(key),
                    sut.merge(5, key, nextValue.incrementAndGet(), nullMapper));
            values.remove(5);
            keys.remove(5);
        });
        sutValidator.run();
        
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
        sut.forEach((key, value) -> sut.replace(key, -value));
        values.replaceAll(value -> -value);
        sutValidator.run();
        
        //entries
        sut.forEach(entry -> sut.replace(entry.getKey(), -entry.getValue()));
        values.replaceAll(value -> -value);
        sutValidator.run();
        
        //order
        sut.forEach((key, value) -> sut.replace(key, nextValue.incrementAndGet()));
        values.replaceAll(value -> (nextValue.get() - sut.size() + values.indexOf(value) + 1));
        sutValidator.run();
        
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
        sut.indexedForEach((entry, i) -> sut.replace(entry.getKey(), (entry.getValue() * (NumberUtility.isEven(i) ? -1 : 1))));
        values.replaceAll(value -> (value * (NumberUtility.isEven(values.indexOf(value)) ? -1 : 1)));
        sutValidator.run();
        
        //order
        sut.forEach((key, value) -> sut.replace(key, nextValue.incrementAndGet()));
        values.replaceAll(value -> (nextValue.get() - sut.size() + values.indexOf(value) + 1));
        sutValidator.run();
        
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
        
        //standard
        iterator.set(sut.iterator());
        IntStream.range(0, sut.size()).forEach(i -> {
            Assert.assertTrue(iterator.get().hasNext());
            Assert.assertEquals(Map.entry(keys.get(i), values.get(i)), iterator.get().next());
        });
        Assert.assertFalse(iterator.get().hasNext());
        TestUtils.assertException(NoSuchElementException.class, () ->
                iterator.get().next());
        sutValidator.run();
        
        //edit
        iterator.set(sut.iterator());
        IntStream.range(0, sut.size()).forEach(i -> {
            Assert.assertEquals(values.get(i), iterator.get().next().setValue(-1));
            values.set(i, -1);
        });
        sutValidator.run();
        
        //remove
        iterator.set(sut.iterator());
        TestUtils.assertException(IllegalStateException.class, () ->
                iterator.get().remove());
        IntStream.range(0, sut.size()).forEach(i -> {
            iterator.get().next();
            iterator.get().remove();
            keys.remove(0);
            values.remove(0);
        });
        TestUtils.assertException(IllegalStateException.class, () ->
                iterator.get().remove());
        Assert.assertTrue(sut.isEmpty());
        sutValidator.run();
        
        //equality
        Assert.assertNotSame(sut.iterator(), sut.iterator());
    }
    
}
