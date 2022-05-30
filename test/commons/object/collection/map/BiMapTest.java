/*
 * File:    BiMapTest.java
 * Package: commons.object.collection.map
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.map;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import commons.lambda.stream.collector.ArrayCollectors;
import commons.lambda.stream.collector.MapCollectors;
import commons.math.number.BoundUtility;
import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
import commons.object.collection.map.strict.StrictHashMap;
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
 * JUnit test of BiMap.
 *
 * @see BiMap
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({BiMap.class})
public class BiMapTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BiMapTest.class);
    
    
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
     * The UnmodifiableCollection class.
     */
    private static final Class<?> UnmodifiableCollection = TestAccess.getClass(Collections.class, "UnmodifiableCollection");
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private BiMap<String, Integer> sut;
    
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
        sut = new BiMap<>(MapUtility.mapOf(keys, values));
    };
    
    /**
     * Validates the content of the system under test.
     */
    private final Runnable sutValidator = () -> {
        final Map<Integer, String> inverseMap = TestAccess.getFieldValue(sut, Map.class, "inverseMap");
        
        Assert.assertTrue(keys.stream().allMatch(key -> sut.containsKey(key)));
        Assert.assertTrue(values.stream().allMatch(value -> sut.containsValue(value)));
        List.of(keys, values,
                sut.keySet(), sut.values(), sut.entrySet(), sut.inverseEntrySet(),
                inverseMap.keySet(), inverseMap.values(), inverseMap.entrySet()
        ).forEach(components ->
                Assert.assertEquals(sut.size(), components.size()));
        
        IntStream.range(0, sut.size()).forEach(i -> {
            Assert.assertEquals(values.get(i), sut.get(keys.get(i)));
            Assert.assertEquals(keys.get(i), sut.inverseGet(values.get(i)));
            Assert.assertEquals(keys.get(i), inverseMap.get(values.get(i)));
        });
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
                view.remove(null));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.addAll(ListUtility.emptyList()));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.removeAll(ListUtility.emptyList()));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.retainAll(ListUtility.emptyList()));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                view.removeIf(Objects::nonNull));
        TestUtils.assertException(UnsupportedOperationException.class, view::clear);
        sutValidator.run();
    };
    
    /**
     * Sets a key value pair in the list of keys and list of value of the system under test.
     */
    private final BiConsumer<String, Integer> keyValueBiSetter = (String key, Integer value) -> {
        if (values.contains(value)) {
            keys.remove(values.indexOf(value));
            values.remove(value);
        }
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(keys.indexOf(key), value);
        }
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
     * @see BiMap#BiMap(Map)
     * @see BiMap#BiMap()
     */
    @Test
    public void testConstructors() throws Exception {
        final Map<String, Integer> sourceMap = MapUtility.mapOf(keys, values);
        BiMap<String, Integer> map1;
        BiMap<String, Integer> map2;
        
        //map
        map1 = new BiMap<>(sourceMap);
        Assert.assertNotNull(map1);
        Assert.assertTrue(map1 instanceof StrictHashMap);
        TestUtils.assertMapEquals(map1, sourceMap);
        TestUtils.assertMapEquals(
                TestAccess.getFieldValue(map1, Map.class, "inverseMap"),
                MapUtility.mapOf(values, keys));
        
        //empty
        map2 = new BiMap<>();
        Assert.assertNotNull(map2);
        Assert.assertTrue(map2 instanceof StrictHashMap);
        Assert.assertTrue(map2.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new BiMap<>((Map<?, ?>) null));
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#get(Object)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.get(key)));
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.get(key)));
        
        //invalid
        Assert.assertNull(sut.get(null));
    }
    
    /**
     * JUnit test of getOrDefault.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#getOrDefault(Object, Object)
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
     * JUnit test of inverseGet.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseGet(Object)
     */
    @Test
    public void testInverseGet() throws Exception {
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(key,
                        sut.inverseGet(values.get(keys.indexOf(key)))));
        
        //absent
        sutTester.accept(keySets[2], key ->
                Assert.assertNull(
                        sut.inverseGet(-values.get(keys.indexOf(key)))));
        
        //invalid
        Assert.assertNull(sut.inverseGet(null));
    }
    
    /**
     * JUnit test of inverseGetOrDefault.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseGetOrDefault(Object, Object)
     */
    @Test
    public void testInverseGetOrDefault() throws Exception {
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(key,
                        sut.inverseGetOrDefault(values.get(keys.indexOf(key)), "~")));
        
        //absent
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals("~",
                        sut.inverseGetOrDefault(-values.get(keys.indexOf(key)), "~")));
        
        //invalid
        Assert.assertEquals("~", sut.inverseGetOrDefault(null, "~"));
        Assert.assertNull(sut.inverseGetOrDefault(-1, null));
        Assert.assertNull(sut.inverseGetOrDefault(null, null));
    }
    
    /**
     * JUnit test of containsKey.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#containsKey(Object)
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
     * @see BiMap#containsValue(Object)
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
     * JUnit test of entrySet.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#entrySet()
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
        TestUtils.assertListEquals(
                ListUtility.toList(sut.entrySet()),
                sut.entrySet(), false);
        Assert.assertNotSame(sut.entrySet(), sut.entrySet());
        
        //empty
        Assert.assertTrue(new BiMap<>().entrySet().isEmpty());
    }
    
    /**
     * JUnit test of inverseEntrySet.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseEntrySet()
     */
    @Test
    public void testInverseEntrySet() throws Exception {
        final Set<Map.Entry<Integer, String>> inverseEntrySet = sut.inverseEntrySet();
        
        //standard
        Assert.assertNotNull(inverseEntrySet);
        Assert.assertEquals(UnmodifiableSet, inverseEntrySet.getClass());
        Assert.assertEquals(sut.size(), inverseEntrySet.size());
        TestUtils.assertListEquals(
                inverseEntrySet.stream().map(Map.Entry::getKey).collect(Collectors.toList()),
                values, false);
        TestUtils.assertListEquals(
                inverseEntrySet.stream().map(Map.Entry::getValue).collect(Collectors.toList()),
                keys, false);
        
        //immutability
        immutableViewValidator.accept(inverseEntrySet);
        
        //equality
        TestUtils.assertListEquals(
                ListUtility.toList(sut.inverseEntrySet()),
                sut.inverseEntrySet(), false);
        Assert.assertNotSame(sut.inverseEntrySet(), sut.inverseEntrySet());
        
        //empty
        Assert.assertTrue(new BiMap<>().inverseEntrySet().isEmpty());
    }
    
    /**
     * JUnit test of keySet.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#keySet()
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
        Assert.assertTrue(new BiMap<>().keySet().isEmpty());
    }
    
    /**
     * JUnit test of values.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#values()
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
        Assert.assertTrue(new BiMap<>().values().isEmpty());
    }
    
    /**
     * JUnit test of put.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#put(Object, Object, boolean)
     * @see BiMap#put(Object, Object)
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
        
        //force
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.put(key, nextValue.incrementAndGet(), true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //conflict
        sutTester.accept(keySets[3], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", (nextValue.get() - 1)), () ->
                        sut.put(key, nextValue.decrementAndGet())));
        
        //conflict, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.put(key, nextValue.decrementAndGet(), true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //conflict, present
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                (values.get(keys.indexOf(key)) != (nextValue.get() - 1)),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", (nextValue.get() - 1)), () ->
                                sut.put(key, nextValue.decrementAndGet())));
        
        //conflict, present, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((keys.contains(key) ? values.get(keys.indexOf(key)) : null),
                    sut.put(key, nextValue.decrementAndGet(), true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //present
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.put(key, nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //present, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.put(key, nextValue.incrementAndGet(), true));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //invalid
        Assert.assertEquals(sut.put(null, -1, false), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.put("~", null, false), sut.remove("~"));
        Assert.assertEquals(sut.put(null, null, false), sut.remove(null));
        Assert.assertEquals(sut.put(null, -1), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.put("~", null), sut.remove("~"));
        Assert.assertEquals(sut.put(null, null), sut.remove(null));
    }
    
    /**
     * JUnit test of inversePut.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inversePut(Object, Object, boolean)
     * @see BiMap#inversePut(Object, Object)
     */
    @Test
    public void testInversePut() throws Exception {
        //standard
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.inversePut(nextValue.incrementAndGet(), key));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //force
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.inversePut(nextValue.incrementAndGet(), key, true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //conflict
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inversePut(nextValue.decrementAndGet(), key));
            keys.set(values.indexOf(nextValue.get()), key);
        });
        
        //conflict, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inversePut(nextValue.decrementAndGet(), key, true));
            keys.set(values.indexOf(nextValue.get()), key);
        });
        
        //conflict, present
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                (values.get(keys.indexOf(key)) != (nextValue.get() - 1)),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", key), () ->
                                sut.inversePut(nextValue.decrementAndGet(), key)));
        
        //conflict, present, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((values.contains(nextValue.get() - 1) ? keys.get(values.indexOf(nextValue.get() - 1)) : null),
                    sut.inversePut(nextValue.decrementAndGet(), key, true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //present
        sutTester.accept(keySets[2], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", key), () ->
                        sut.inversePut(nextValue.incrementAndGet(), key)));
        
        //present, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertNull(
                    sut.inversePut(nextValue.incrementAndGet(), key, true));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //invalid
        Assert.assertEquals(sut.inversePut(null, "~", false), ((sut.inverseRemove(null).equals("~")) ? null : "~"));
        Assert.assertEquals(sut.inversePut(-1, null, false), sut.inverseRemove(-1));
        Assert.assertEquals(sut.inversePut(null, null, false), sut.inverseRemove(null));
        Assert.assertEquals(sut.inversePut(null, "~"), ((sut.inverseRemove(null).equals("~")) ? null : "~"));
        Assert.assertEquals(sut.inversePut(-1, null), sut.inverseRemove(-1));
        Assert.assertEquals(sut.inversePut(null, null), sut.inverseRemove(null));
    }
    
    /**
     * JUnit test of putIfAbsent.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#putIfAbsent(Object, Object, boolean)
     * @see BiMap#putIfAbsent(Object, Object)
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
        
        //force
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.putIfAbsent(key, nextValue.incrementAndGet(), true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //conflict
        sutTester.accept(keySets[3], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", (nextValue.get() - 1)), () ->
                        sut.putIfAbsent(key, nextValue.decrementAndGet())));
        
        //conflict, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.putIfAbsent(key, nextValue.decrementAndGet(), true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //conflict, present
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.putIfAbsent(key, nextValue.decrementAndGet())));
        
        //conflict, present, force
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.putIfAbsent(key, nextValue.decrementAndGet(), true)));
        
        //present
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.putIfAbsent(key, nextValue.incrementAndGet())));
        
        //present, force
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.putIfAbsent(key, nextValue.incrementAndGet(), true)));
        
        //invalid
        Assert.assertEquals(sut.putIfAbsent(null, -1, false), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.putIfAbsent("~", null, false), sut.remove("~"));
        Assert.assertEquals(sut.putIfAbsent(null, null, false), sut.remove(null));
        Assert.assertEquals(sut.putIfAbsent(null, -1), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.putIfAbsent("~", null), sut.remove("~"));
        Assert.assertEquals(sut.putIfAbsent(null, null), sut.remove(null));
    }
    
    /**
     * JUnit test of inversePutIfAbsent.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inversePutIfAbsent(Object, Object, boolean)
     * @see BiMap#inversePutIfAbsent(Object, Object)
     */
    @Test
    public void testInversePutIfAbsent() throws Exception {
        //standard
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.inversePutIfAbsent(nextValue.incrementAndGet(), key));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //force
        sutTester.accept(keySets[1], key -> {
            Assert.assertNull(
                    sut.inversePutIfAbsent(nextValue.incrementAndGet(), key, true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //conflict
        sutTester.accept(keySets[3], key ->
                Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                        sut.inversePutIfAbsent(nextValue.decrementAndGet(), key)));
        
        //conflict, force
        sutTester.accept(keySets[3], key ->
                Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                        sut.inversePutIfAbsent(nextValue.decrementAndGet(), key, true)));
        
        //conflict, present
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                        sut.inversePutIfAbsent(nextValue.decrementAndGet(), key)));
        
        //conflict, present, force
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                        sut.inversePutIfAbsent(nextValue.decrementAndGet(), key, true)));
        
        //present
        sutTester.accept(keySets[2], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", key), () ->
                        sut.inversePutIfAbsent(nextValue.incrementAndGet(), key)));
        
        //present, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertNull(
                    sut.inversePutIfAbsent(nextValue.incrementAndGet(), key, true));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //invalid
        Assert.assertEquals(sut.inversePutIfAbsent(null, "~", false), ((sut.inverseRemove(null).equals("~")) ? null : "~"));
        Assert.assertEquals(sut.inversePutIfAbsent(-1, null, false), sut.inverseRemove(-1));
        Assert.assertEquals(sut.inversePutIfAbsent(null, null, false), sut.inverseRemove(null));
        Assert.assertEquals(sut.inversePutIfAbsent(null, "~"), ((sut.inverseRemove(null).equals("~")) ? null : "~"));
        Assert.assertEquals(sut.inversePutIfAbsent(-1, null), sut.inverseRemove(-1));
        Assert.assertEquals(sut.inversePutIfAbsent(null, null), sut.inverseRemove(null));
    }
    
    /**
     * JUnit test of putAll.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#putAll(Map, boolean)
     * @see BiMap#putAll(Map)
     */
    @Test
    public void testPutAll() throws Exception {
        final Map<String, Integer> testMap = MapUtility.emptyMap(LinkedHashMap.class);
        
        final BiConsumer<List<String>, Boolean> testMapInitializer = (List<String> keySet, Boolean decrement) ->
                MapUtility.clearAndGet(testMap).putAll(MapUtility.mapOf(LinkedHashMap.class,
                        keySet,
                        keySet.stream().mapToInt(key -> nextValue.addAndGet(decrement ? -1 : 1)).boxed().collect(Collectors.toList())));
        
        //standard
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[1], false);
            sut.putAll(testMap);
            keys.addAll(testMap.keySet());
            values.addAll(testMap.values());
        });
        
        //force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[1], false);
            sut.putAll(testMap, true);
            keys.addAll(testMap.keySet());
            values.addAll(testMap.values());
        });
        
        //conflict
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[3], true);
            TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", new ArrayList<>(testMap.values()).get(0)), () ->
                    sut.putAll(testMap));
        });
        
        //conflict, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[3], true);
            sut.putAll(testMap, true);
            testMap.forEach(keyValueBiSetter);
        });
        
        //conflict, present
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2], true);
            TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", testMap.entrySet().stream().filter(e -> !Objects.equals(values.get(keys.indexOf(e.getKey())), e.getValue())).findFirst().map(Map.Entry::getValue).orElseThrow()), () ->
                    sut.putAll(testMap));
        });
        
        //conflict, present, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2], true);
            sut.putAll(testMap, true);
            testMap.forEach(keyValueBiSetter);
        });
        
        //present
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2], false);
            sut.putAll(testMap);
            testMap.forEach((key, value) ->
                    values.set(keys.indexOf(key), value));
        });
        
        //present, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2], false);
            sut.putAll(testMap, true);
            testMap.forEach((key, value) ->
                    values.set(keys.indexOf(key), value));
        });
        
        //mixed
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(ListUtility.selectN(keySets[2], 10), keySets[1]), false);
            sut.putAll(testMap);
            keys.addAll(keySets[1]);
            values.addAll(Collections.nCopies(keySets[1].size(), 0));
            testMap.forEach((key, value) ->
                    values.set(keys.indexOf(key), value));
        });
        
        //mixed, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(ListUtility.selectN(keySets[2], 10), keySets[1]), false);
            sut.putAll(testMap, true);
            testMap.forEach(keyValueBiSetter);
        });
        
        //mixed, conflict
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(ListUtility.selectN(keySets[2], 10), keySets[1]), true);
            TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", testMap.entrySet().stream().filter(e -> !Objects.equals(values.get(keys.indexOf(e.getKey())), e.getValue())).findFirst().map(Map.Entry::getValue).orElseThrow()), () ->
                    sut.putAll(testMap));
        });
        
        //mixed, conflict, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(ListUtility.selectN(keySets[2], 10), keySets[1]), true);
            sut.putAll(testMap, true);
            testMap.forEach(keyValueBiSetter);
        });
        
        //empty
        sutTestRunner.accept(() -> {
            sut.putAll(MapUtility.emptyMap(), false);
            sut.putAll(MapUtility.emptyMap(), true);
            sut.putAll(MapUtility.emptyMap());
        });
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.putAll(null, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.putAll(null));
    }
    
    /**
     * JUnit test of inversePutAll.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inversePutAll(Map, boolean)
     * @see BiMap#inversePutAll(Map)
     */
    @Test
    public void testInversePutAll() throws Exception {
        final Map<Integer, String> testMap = MapUtility.emptyMap(LinkedHashMap.class);
        
        final BiConsumer<List<String>, Boolean> testMapInitializer = (List<String> keySet, Boolean decrement) ->
                MapUtility.clearAndGet(testMap).putAll(MapUtility.mapOf(LinkedHashMap.class,
                        keySet.stream().mapToInt(key -> nextValue.addAndGet(decrement ? -1 : 1)).boxed().collect(Collectors.toList()),
                        keySet));
        
        //standard
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[1], false);
            sut.inversePutAll(testMap);
            keys.addAll(testMap.values());
            values.addAll(testMap.keySet());
        });
        
        //force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[1], false);
            sut.inversePutAll(testMap, true);
            keys.addAll(testMap.values());
            values.addAll(testMap.keySet());
        });
        
        //conflict
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[3], true);
            sut.inversePutAll(testMap);
            testMap.forEach((value, key) ->
                    keys.set(values.indexOf(value), key));
        });
        
        //conflict, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[3], true);
            sut.inversePutAll(testMap, true);
            testMap.forEach((value, key) ->
                    keys.set(values.indexOf(value), key));
        });
        
        //conflict, present
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2], true);
            TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", testMap.entrySet().stream().filter(e -> !Objects.equals(values.get(keys.indexOf(e.getValue())), e.getKey())).findFirst().map(Map.Entry::getValue).orElseThrow()), () ->
                    sut.inversePutAll(testMap));
        });
        
        //conflict, present, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2], true);
            sut.inversePutAll(testMap, true);
            testMap.forEach((value, key) ->
                    keyValueBiSetter.accept(key, value));
        });
        
        //present
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2], false);
            TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", new ArrayList<>(testMap.values()).get(0)), () ->
                    sut.inversePutAll(testMap));
        });
        
        //present, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(keySets[2], false);
            sut.inversePutAll(testMap, true);
            testMap.forEach((value, key) ->
                    values.set(keys.indexOf(key), value));
        });
        
        //mixed
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(ListUtility.selectN(keySets[2], 10), keySets[1]), false);
            TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", new ArrayList<>(testMap.values()).get(0)), () ->
                    sut.inversePutAll(testMap));
        });
        
        //mixed, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(ListUtility.selectN(keySets[2], 10), keySets[1]), false);
            sut.inversePutAll(testMap, true);
            testMap.forEach((value, key) ->
                    keyValueBiSetter.accept(key, value));
        });
        
        //mixed, conflict
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(ListUtility.selectN(keySets[2], 10), keySets[1]), true);
            TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", testMap.entrySet().stream().filter(e -> keys.contains(e.getValue())).filter(e -> !Objects.equals(keys.get(values.indexOf(e.getKey())), e.getValue())).findFirst().map(Map.Entry::getValue).orElseThrow()), () ->
                    sut.inversePutAll(testMap));
        });
        
        //mixed, conflict, force
        sutTestRunner.accept(() -> {
            testMapInitializer.accept(ListUtility.merge(ListUtility.selectN(keySets[2], 10), keySets[1]), true);
            sut.inversePutAll(testMap, true);
            testMap.forEach((value, key) ->
                    keyValueBiSetter.accept(key, value));
        });
        
        //empty
        sutTestRunner.accept(() -> {
            sut.inversePutAll(MapUtility.emptyMap(), false);
            sut.inversePutAll(MapUtility.emptyMap(), true);
            sut.inversePutAll(MapUtility.emptyMap());
        });
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inversePutAll(null, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inversePutAll(null));
    }
    
    /**
     * JUnit test of replace.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#replace(Object, Object, boolean)
     * @see BiMap#replace(Object, Object)
     * @see BiMap#replace(Object, Object, Object, boolean)
     * @see BiMap#replace(Object, Object, Object)
     */
    @Test
    public void testReplace() throws Exception {
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.replace(key, nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.replace(key, nextValue.incrementAndGet(), true));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //expected
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.replace(key, values.get(keys.indexOf(key)), nextValue.incrementAndGet()));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //expected, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.replace(key, values.get(keys.indexOf(key)), nextValue.incrementAndGet(), true));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //unexpected
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.incrementAndGet())));
        
        //unexpected, force
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.incrementAndGet(), true)));
        
        //conflict
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                (values.get(keys.indexOf(key)) != (nextValue.get() - 1)),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", (nextValue.get() - 1)), () ->
                                sut.replace(key, nextValue.decrementAndGet())));
        
        //conflict, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((keys.contains(key) ? values.get(keys.indexOf(key)) : null),
                    sut.replace(key, nextValue.decrementAndGet(), true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //conflict, expected
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                (values.get(keys.indexOf(key)) != (nextValue.get() - 1)),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", (nextValue.get() - 1)), () ->
                                sut.replace(key, values.get(keys.indexOf(key)), nextValue.decrementAndGet())));
        
        //conflict, expected, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.replace(key, (keys.contains(key) ? values.get(keys.indexOf(key)) : null), nextValue.decrementAndGet(), true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //conflict, unexpected
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.decrementAndGet())));
        
        //conflict, unexpected, force
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.decrementAndGet(), true)));
        
        //absent
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.replace(key, nextValue.incrementAndGet()));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.replace(key, nextValue.incrementAndGet(), true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, expected
        sutTester.accept(keySets[3], key -> {
            Assert.assertTrue(
                    sut.replace(key, null, nextValue.incrementAndGet()));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, expected, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertTrue(
                    sut.replace(key, null, nextValue.incrementAndGet(), true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, unexpected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.incrementAndGet())));
        
        //absent, unexpected, force
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.incrementAndGet(), true)));
        
        //absent, conflict
        sutTester.accept(keySets[3], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", (nextValue.get() - 1)), () ->
                        sut.replace(key, nextValue.decrementAndGet())));
        
        //absent, conflict, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.replace(key, nextValue.decrementAndGet(), true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //absent, conflict, expected
        sutTester.accept(keySets[3], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", (nextValue.get() - 1)), () ->
                        sut.replace(key, null, nextValue.decrementAndGet())));
        
        //absent, conflict, expected, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertTrue(
                    sut.replace(key, null, nextValue.decrementAndGet(), true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //absent, conflict, unexpected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.decrementAndGet())));
        
        //absent, conflict, unexpected, force
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.replace(key, -1, nextValue.decrementAndGet(), true)));
        
        //invalid
        Assert.assertEquals(sut.replace(null, -1, -1, false), (sut.remove(null) != null));
        Assert.assertEquals(sut.replace("~", null, -1, false), (sut.remove("~") == -1));
        Assert.assertEquals(sut.replace(null, null, -1, false), (sut.remove(null) == -1));
        Assert.assertEquals(sut.replace(null, -1, -1), (sut.remove(null) != null));
        Assert.assertEquals(sut.replace("~", null, -1), (sut.remove("~") == -1));
        Assert.assertEquals(sut.replace(null, null, -1), (sut.remove(null) == -1));
        Assert.assertEquals(sut.replace(null, -1, false), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.replace("~", null, false), sut.remove("~"));
        Assert.assertEquals(sut.replace(null, null, false), sut.remove(null));
        Assert.assertEquals(sut.replace(null, -1), ((sut.remove(null) == -1) ? null : -1));
        Assert.assertEquals(sut.replace("~", null), sut.remove("~"));
        Assert.assertEquals(sut.replace(null, null), sut.remove(null));
    }
    
    /**
     * JUnit test of inverseReplace.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseReplace(Object, Object, boolean)
     * @see BiMap#inverseReplace(Object, Object)
     * @see BiMap#inverseReplace(Object, Object, Object, boolean)
     * @see BiMap#inverseReplace(Object, Object, Object)
     */
    @Test
    public void testInverseReplace() throws Exception {
        //standard
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inverseReplace(nextValue.decrementAndGet(), key));
            keys.set(values.indexOf(nextValue.get()), key);
        });
        
        //force
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inverseReplace(nextValue.decrementAndGet(), key, true));
            keys.set(values.indexOf(nextValue.get()), key);
        });
        
        //expected
        sutTester.accept(keySets[3], key -> {
            Assert.assertTrue(
                    sut.inverseReplace(nextValue.decrementAndGet(), keys.get(values.indexOf(nextValue.get())), key));
            keys.set(values.indexOf(nextValue.get()), key);
        });
        
        //expected, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertTrue(
                    sut.inverseReplace(nextValue.decrementAndGet(), keys.get(values.indexOf(nextValue.get())), key, true));
            keys.set(values.indexOf(nextValue.get()), key);
        });
        
        //unexpected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.inverseReplace(nextValue.decrementAndGet(), "~", key)));
        
        //unexpected, force
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.inverseReplace(nextValue.decrementAndGet(), "~", key, true)));
        
        //new
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.inverseReplace(nextValue.incrementAndGet(), key));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //new, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.inverseReplace(nextValue.incrementAndGet(), key, true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //new, expected
        sutTester.accept(keySets[3], key -> {
            Assert.assertTrue(
                    sut.inverseReplace(nextValue.incrementAndGet(), null, key));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //new, expected, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertTrue(
                    sut.inverseReplace(nextValue.incrementAndGet(), null, key, true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //new, unexpected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.inverseReplace(nextValue.incrementAndGet(), "~", key)));
        
        //new, unexpected, force
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.inverseReplace(nextValue.incrementAndGet(), "~", key, true)));
        
        //present
        sutTester.accept(keySets[2], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", key), () ->
                        sut.inverseReplace(nextValue.incrementAndGet(), key)));
        
        //present, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertNull(
                    sut.inverseReplace(nextValue.incrementAndGet(), key, true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //present, expected
        sutTester.accept(keySets[2], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", key), () ->
                        sut.inverseReplace(nextValue.incrementAndGet(), null, key)));
        
        //present, expected, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.inverseReplace(nextValue.incrementAndGet(), null, key, true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //present, unexpected
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.inverseReplace(nextValue.incrementAndGet(), "~", key)));
        
        //present, unexpected, force
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.inverseReplace(nextValue.incrementAndGet(), "~", key, true)));
        
        //present, conflict
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                (values.get(keys.indexOf(key)) != (nextValue.get() - 1)),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", key), () ->
                                sut.inverseReplace(nextValue.decrementAndGet(), key)));
        
        //present, conflict, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((values.contains(nextValue.get() - 1) ? keys.get(values.indexOf(nextValue.get() - 1)) : null),
                    sut.inverseReplace(nextValue.decrementAndGet(), key, true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //present, conflict, expected
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                (values.get(keys.indexOf(key)) != (nextValue.get() - 1)),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", key), () ->
                                sut.inverseReplace(nextValue.decrementAndGet(), keys.get(values.indexOf(nextValue.get())), key)));
        
        //present, conflict, expected, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.inverseReplace(nextValue.decrementAndGet(), (values.contains(nextValue.get()) ? keys.get(values.indexOf(nextValue.get())) : null), key, true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //present, conflict, unexpected
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.inverseReplace(nextValue.decrementAndGet(), "~", key)));
        
        //present, conflict, unexpected, force
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.inverseReplace(nextValue.decrementAndGet(), "~", key, true)));
        
        //invalid
        Assert.assertEquals(sut.inverseReplace(null, "~", "~", false), (sut.inverseRemove(null) != null));
        Assert.assertEquals(sut.inverseReplace(-1, null, "~", false), sut.inverseRemove(-1).equals("~"));
        Assert.assertEquals(sut.inverseReplace(null, null, "~", false), sut.inverseRemove(null).equals("~"));
        Assert.assertEquals(sut.inverseReplace(null, "~", "~"), (sut.inverseRemove(null) != null));
        Assert.assertEquals(sut.inverseReplace(-1, null, "~"), sut.inverseRemove(-1).equals("~"));
        Assert.assertEquals(sut.inverseReplace(null, null, "~"), sut.inverseRemove(null).equals("~"));
        Assert.assertEquals(sut.inverseReplace(null, "~", false), (sut.inverseRemove(null).equals("~") ? null : "~"));
        Assert.assertEquals(sut.inverseReplace(-1, null, false), sut.inverseRemove(-1));
        Assert.assertEquals(sut.inverseReplace(null, null, false), sut.inverseRemove(null));
        Assert.assertEquals(sut.inverseReplace(null, "~"), (sut.inverseRemove(null).equals("~") ? null : "~"));
        Assert.assertEquals(sut.inverseReplace(-1, null), sut.inverseRemove(-1));
        Assert.assertEquals(sut.inverseReplace(null, null), sut.inverseRemove(null));
    }
    
    /**
     * JUnit test of replaceAll.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#replaceAll(BiFunction, boolean)
     * @see BiMap#replaceAll(BiFunction)
     */
    @Test
    public void testReplaceAll() throws Exception {
        final BiFunction<String, Integer, Integer> mapper = (String key, Integer oldValue) -> -oldValue;
        final BiFunction<String, Integer, Integer> conflictMapper = (String key, Integer oldValue) -> BoundUtility.truncate((oldValue / 2), 1, nextValue.get());
        
        //standard
        sutTestRunner.accept(() -> {
            sut.replaceAll(mapper);
            values.replaceAll(value -> mapper.apply(keys.get(values.indexOf(value)), value));
        });
        
        //standard, force
        sutTestRunner.accept(() -> {
            sut.replaceAll(mapper, true);
            values.replaceAll(value -> mapper.apply(keys.get(values.indexOf(value)), value));
        });
        
        //conflict
        sutTestRunner.accept(() ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", sut.entrySet().stream().filter(e -> !Objects.equals(e.getValue(), conflictMapper.apply(e.getKey(), e.getValue()))).findFirst().map(e -> conflictMapper.apply(e.getKey(), e.getValue())).orElseThrow()), () ->
                        sut.replaceAll(conflictMapper)));
        
        //conflict, force
        sutTestRunner.accept(() -> {
            final Set<Map.Entry<String, Integer>> oldEntrySet = sut.entrySet();
            sut.replaceAll(conflictMapper, true);
            oldEntrySet.forEach(entry ->
                    keyValueBiSetter.accept(entry.getKey(), conflictMapper.apply(entry.getKey(), entry.getValue())));
        });
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.replaceAll(null, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.replaceAll(null));
    }
    
    /**
     * JUnit test of inverseReplaceAll.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseReplaceAll(BiFunction, boolean)
     * @see BiMap#inverseReplaceAll(BiFunction)
     */
    @Test
    public void testInverseReplaceAll() throws Exception {
        final BiFunction<Integer, String, String> mapper = (Integer value, String oldKey) -> oldKey.toLowerCase();
        final BiFunction<Integer, String, String> conflictMapper = (Integer value, String oldKey) -> String.valueOf((char) (int) BoundUtility.truncate((oldKey.charAt(0) - 5), (int) 'A', (int) 'Z'));
        
        //standard
        sutTestRunner.accept(() -> {
            sut.inverseReplaceAll(mapper);
            keys.replaceAll(key -> mapper.apply(values.get(keys.indexOf(key)), key));
        });
        
        //standard, force
        sutTestRunner.accept(() -> {
            sut.inverseReplaceAll(mapper, true);
            keys.replaceAll(key -> mapper.apply(values.get(keys.indexOf(key)), key));
        });
        
        //present
        sutTestRunner.accept(() ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", sut.entrySet().stream().filter(e -> !Objects.equals(e.getKey(), conflictMapper.apply(e.getValue(), e.getKey()))).findFirst().map(e -> conflictMapper.apply(e.getValue(), e.getKey())).orElseThrow()), () ->
                        sut.inverseReplaceAll(conflictMapper)));
        
        //present, force
        sutTestRunner.accept(() -> {
            final Set<Map.Entry<String, Integer>> oldEntrySet = sut.entrySet();
            sut.inverseReplaceAll(conflictMapper, true);
            oldEntrySet.forEach(entry ->
                    keyValueBiSetter.accept(conflictMapper.apply(entry.getValue(), entry.getKey()), entry.getValue()));
        });
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseReplaceAll(null, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseReplaceAll(null));
    }
    
    /**
     * JUnit test of remove.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#remove(Object)
     * @see BiMap#remove(Object, Object)
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
        
        //expected
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.remove(key, values.get(keys.indexOf(key))));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //unexpected
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.remove(key, -1)));
        
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
        Assert.assertFalse(sut.remove(null, -1));
        Assert.assertFalse(sut.remove("~", null));
        Assert.assertFalse(sut.remove(null, null));
        Assert.assertNull(sut.remove(null));
    }
    
    /**
     * JUnit test of inverseRemove.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseRemove(Object)
     * @see BiMap#inverseRemove(Object, Object)
     */
    @Test
    public void testInverseRemove() throws Exception {
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inverseRemove(nextValue.decrementAndGet()));
            keys.remove(values.indexOf(nextValue.get()));
            values.remove((Integer) nextValue.get());
        });
        
        //expected
        sutTester.accept(keySets[2], key -> {
            Assert.assertTrue(
                    sut.inverseRemove(nextValue.decrementAndGet(), keys.get(values.indexOf(nextValue.get()))));
            keys.remove(values.indexOf(nextValue.get()));
            values.remove((Integer) nextValue.get());
        });
        
        //unexpected
        sutTester.accept(keySets[2], key ->
                Assert.assertFalse(
                        sut.inverseRemove(nextValue.decrementAndGet(), "~")));
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.inverseRemove(nextValue.incrementAndGet())));
        
        //absent, expected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.inverseRemove(nextValue.incrementAndGet(), null)));
        
        //absent, unexpected
        sutTester.accept(keySets[3], key ->
                Assert.assertFalse(
                        sut.inverseRemove(nextValue.incrementAndGet(), "~")));
        
        //invalid
        Assert.assertFalse(sut.inverseRemove(null, "~"));
        Assert.assertFalse(sut.inverseRemove(-1, null));
        Assert.assertFalse(sut.inverseRemove(null, null));
        Assert.assertNull(sut.inverseRemove(null));
    }
    
    /**
     * JUnit test of clear.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#clear()
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
     * @see BiMap#size()
     */
    @Test
    public void testSize() throws Exception {
        //standard
        sutTestRunner.accept(() ->
                Stream.of(keys, values,
                        sut.entrySet(), sut.inverseEntrySet(), sut.exposedEntrySet(), sut.immutableEntrySet(),
                        sut.keySet(), sut.exposedKeySet(), sut.immutableKeySet(),
                        sut.values(), sut.exposedValues(), sut.immutableValues()
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
     * @see BiMap#isEmpty()
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
     * @see BiMap#clone()
     */
    @Test
    public void testClone() throws Exception {
        BiMap<String, Integer> clone;
        
        //standard
        clone = sut.clone();
        Assert.assertNotNull(clone);
        Assert.assertTrue(clone instanceof BiMap);
        TestUtils.assertMapEquals(clone, sut);
        Assert.assertEquals(sut, clone);
        Assert.assertNotSame(sut, clone);
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals() throws Exception {
        Map<String, Integer> other;
        
        //standard
        other = IntStream.range(0, sut.size()).boxed().collect(
                MapCollectors.toMap(BiMap::new, keys::get, values::get));
        TestUtils.assertMapEquals(other, sut);
        Assert.assertTrue(sut.equals(other));
        Assert.assertNotSame(sut, other);
        
        //normal map
        other = MapUtility.mapOf(LinkedHashMap.class, keys, values);
        TestUtils.assertMapEquals(other, sut);
        Assert.assertTrue(sut.equals(other));
        Assert.assertNotSame(sut, other);
        
        //empty
        Assert.assertTrue(new BiMap<>().equals(new BiMap<>()));
        TestUtils.assertMapEquals(new BiMap<>(), new BiMap<>());
        Assert.assertNotSame(new BiMap<>(), new BiMap<>());
        Assert.assertTrue(new BiMap<>().equals(MapUtility.emptyMap()));
        TestUtils.assertMapEquals(new BiMap<>(), MapUtility.emptyMap());
        Assert.assertNotSame(new BiMap<>(), MapUtility.emptyMap());
        
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
     * @see BiMap#compute(Object, BiFunction, boolean)
     * @see BiMap#compute(Object, BiFunction)
     */
    @Test
    public void testCompute() throws Exception {
        final BiFunction<String, Integer, Integer> mapper = (String key, Integer oldValue) -> Optional.ofNullable(oldValue).map(value -> -value).orElse(nextValue.incrementAndGet());
        final BiFunction<String, Integer, Integer> conflictMapper = (String key, Integer oldValue) -> Optional.ofNullable(oldValue).map(value -> BoundUtility.truncate((value / 2), 1, nextValue.get())).orElse(nextValue.get());
        final BiFunction<String, Integer, Integer> nullMapper = (String key, Integer oldValue) -> null;
        final BiFunction<String, Integer, Integer> mockMapper = (String key, Integer oldValue) -> -1;
        
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(key, mapper));
            values.set(keys.indexOf(key), mapper.apply(key, values.get(keys.indexOf(key))));
        });
        
        //standard, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(key, mapper, true));
            values.set(keys.indexOf(key), mapper.apply(key, values.get(keys.indexOf(key))));
        });
        
        //conflict
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                (values.get(keys.indexOf(key)) != conflictMapper.apply(key, values.get(keys.indexOf(key))).intValue()),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", conflictMapper.apply(key, values.get(keys.indexOf(key)))), () ->
                                sut.compute(key, conflictMapper)));
        
        //conflict, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((keys.contains(key) ? values.get(keys.indexOf(key)) : null),
                    sut.compute(key, conflictMapper, true));
            keyValueBiSetter.accept(key, conflictMapper.apply(key, (keys.contains(key) ? values.get(keys.indexOf(key)) : null)));
        });
        
        //absent
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.compute(key, mapper));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.compute(key, mapper, true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, conflict
        sutTester.accept(keySets[3], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", conflictMapper.apply(key, (keys.contains(key) ? values.get(keys.indexOf(key)) : null))), () ->
                        sut.compute(key, conflictMapper)));
        
        //absent, conflict, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.compute(key, conflictMapper, true));
            keyValueBiSetter.accept(key, conflictMapper.apply(key, (keys.contains(key) ? values.get(keys.indexOf(key)) : null)));
        });
        
        //removal
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(key, nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.compute(key, nullMapper, true));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //invalid
        Assert.assertNull(sut.compute(null, mockMapper, false));
        Assert.assertEquals(sut.compute(null, mockMapper, false), sut.compute(null, nullMapper, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute("~", null, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute(null, null, false));
        Assert.assertNull(sut.compute(null, mockMapper));
        Assert.assertEquals(sut.compute(null, mockMapper), sut.compute(null, nullMapper));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute("~", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.compute(null, null));
    }
    
    /**
     * JUnit test of inverseCompute.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseCompute(Object, BiFunction, boolean)
     * @see BiMap#inverseCompute(Object, BiFunction)
     */
    @Test
    public void testInverseCompute() throws Exception {
        final BiFunction<Integer, String, String> mapper = (Integer value, String oldKey) -> Optional.ofNullable(oldKey).map(String::toLowerCase).orElse(String.valueOf(value));
        final BiFunction<Integer, String, String> presentMapper = (Integer value, String oldKey) -> Optional.ofNullable(oldKey).map(key -> String.valueOf((char) (int) BoundUtility.truncate((key.charAt(0) - 5), (int) 'A', (int) 'Z'))).orElse(keys.get(0));
        final BiFunction<Integer, String, String> nullMapper = (Integer value, String oldKey) -> null;
        final BiFunction<Integer, String, String> mockMapper = (Integer value, String oldKey) -> "~";
        
        //standard
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inverseCompute(nextValue.decrementAndGet(), mapper));
            keys.set(values.indexOf(nextValue.get()), mapper.apply(nextValue.get(), keys.get(values.indexOf(nextValue.get()))));
        });
        
        //standard, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inverseCompute(nextValue.decrementAndGet(), mapper, true));
            keys.set(values.indexOf(nextValue.get()), mapper.apply(nextValue.get(), keys.get(values.indexOf(nextValue.get()))));
        });
        
        //new
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.inverseCompute(nextValue.incrementAndGet(), mapper));
            keys.add(String.valueOf(nextValue.get()));
            values.add(nextValue.get());
        });
        
        //new, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.inverseCompute(nextValue.incrementAndGet(), mapper, true));
            keys.add(String.valueOf(nextValue.get()));
            values.add(nextValue.get());
        });
        
        //present
        sutTester.accept(keySets[2], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", presentMapper.apply((nextValue.get() + 1), null)), () ->
                        sut.inverseCompute(nextValue.incrementAndGet(), presentMapper)));
        
        //present, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertNull(
                    sut.inverseCompute(nextValue.incrementAndGet(), presentMapper, true));
            keyValueBiSetter.accept(presentMapper.apply(nextValue.get(), null), nextValue.get());
        });
        
        //present, conflict
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                !keys.get(values.indexOf(nextValue.get() - 1)).equals(presentMapper.apply((nextValue.get() - 1), keys.get(values.indexOf(nextValue.get() - 1)))),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", presentMapper.apply((nextValue.get() - 1), keys.get(values.indexOf(nextValue.get() - 1)))), () ->
                                sut.inverseCompute(nextValue.decrementAndGet(), presentMapper)));
        
        //present, conflict, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((values.contains(nextValue.get() - 1) ? keys.get(values.indexOf(nextValue.get() - 1)) : null),
                    sut.inverseCompute(nextValue.decrementAndGet(), presentMapper, true));
            keyValueBiSetter.accept(presentMapper.apply(nextValue.get(), (values.contains(nextValue.get()) ? keys.get(values.indexOf(nextValue.get())) : null)), nextValue.get());
        });
        
        //removal
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(key,
                    sut.inverseCompute(values.get(keys.indexOf(key)), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(key,
                    sut.inverseCompute(values.get(keys.indexOf(key)), nullMapper, true));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //invalid
        Assert.assertNull(sut.inverseCompute(null, mockMapper, false));
        Assert.assertEquals(sut.inverseCompute(null, mockMapper, false), sut.inverseCompute(null, nullMapper, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseCompute(-1, null, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseCompute(null, null, false));
        Assert.assertNull(sut.inverseCompute(null, mockMapper));
        Assert.assertEquals(sut.inverseCompute(null, mockMapper), sut.inverseCompute(null, nullMapper));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseCompute(-1, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseCompute(null, null));
    }
    
    /**
     * JUnit test of computeIfAbsent.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#computeIfAbsent(Object, Function, boolean)
     * @see BiMap#computeIfAbsent(Object, Function)
     */
    @Test
    public void testComputeIfAbsent() throws Exception {
        final Function<String, Integer> mapper = (String key) -> nextValue.incrementAndGet();
        final Function<String, Integer> conflictMapper = (String key) -> nextValue.get();
        final Function<String, Integer> nullMapper = (String key) -> null;
        final Function<String, Integer> mockMapper = (String key) -> -1;
        
        //standard
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(key, mapper)));
        
        //standard, force
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(key, mapper, true)));
        
        //conflict
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(key, conflictMapper)));
        
        //conflict, force
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(key, conflictMapper, true)));
        
        //absent
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.computeIfAbsent(key, mapper));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.computeIfAbsent(key, mapper, true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, conflict
        sutTester.accept(keySets[3], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", conflictMapper.apply(key)), () ->
                        sut.computeIfAbsent(key, conflictMapper)));
        
        //absent, conflict, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.computeIfAbsent(key, conflictMapper, true));
            keyValueBiSetter.accept(key, conflictMapper.apply(key));
        });
        
        //removal
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(key, nullMapper)));
        
        //removal, force
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(values.get(keys.indexOf(key)),
                        sut.computeIfAbsent(key, nullMapper, true)));
        
        //invalid
        Assert.assertNull(sut.computeIfAbsent(null, mockMapper, false));
        Assert.assertEquals(sut.computeIfAbsent(null, mockMapper, false), sut.remove(null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent("~", null, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent(null, null, false));
        Assert.assertNull(sut.computeIfAbsent(null, mockMapper));
        Assert.assertEquals(sut.computeIfAbsent(null, mockMapper), sut.remove(null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent("~", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.computeIfAbsent(null, null));
    }
    
    /**
     * JUnit test of inverseComputeIfAbsent.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseComputeIfAbsent(Object, Function, boolean)
     * @see BiMap#inverseComputeIfAbsent(Object, Function)
     */
    @Test
    public void testInverseComputeIfAbsent() throws Exception {
        final Function<Integer, String> mapper = String::valueOf;
        final Function<Integer, String> presentMapper = (Integer value) -> keys.get(0);
        final Function<Integer, String> nullMapper = (Integer value) -> null;
        final Function<Integer, String> mockMapper = (Integer value) -> "~";
        
        //standard
        sutTester.accept(keySets[3], key ->
                Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                        sut.inverseComputeIfAbsent(nextValue.decrementAndGet(), mapper)));
        
        //standard, force
        sutTester.accept(keySets[3], key ->
                Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                        sut.inverseComputeIfAbsent(nextValue.decrementAndGet(), mapper, true)));
        
        //new
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.inverseComputeIfAbsent(nextValue.incrementAndGet(), mapper));
            keys.add(String.valueOf(nextValue.get()));
            values.add(nextValue.get());
        });
        
        //new, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.inverseComputeIfAbsent(nextValue.incrementAndGet(), mapper, true));
            keys.add(String.valueOf(nextValue.get()));
            values.add(nextValue.get());
        });
        
        //present
        sutTester.accept(keySets[2], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", presentMapper.apply((nextValue.get() + 1))), () ->
                        sut.inverseComputeIfAbsent(nextValue.incrementAndGet(), presentMapper)));
        
        //present, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertNull(
                    sut.inverseComputeIfAbsent(nextValue.incrementAndGet(), presentMapper, true));
            keyValueBiSetter.accept(presentMapper.apply(nextValue.get()), nextValue.get());
        });
        
        //present, conflict
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                        sut.inverseComputeIfAbsent(nextValue.decrementAndGet(), mapper)));
        
        //present, conflict, force
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                        sut.inverseComputeIfAbsent(nextValue.decrementAndGet(), mapper, true)));
        
        //removal
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(key,
                        sut.inverseComputeIfAbsent(values.get(keys.indexOf(key)), nullMapper)));
        
        //removal, force
        sutTester.accept(keySets[2], key ->
                Assert.assertEquals(key,
                        sut.inverseComputeIfAbsent(values.get(keys.indexOf(key)), nullMapper, true)));
        
        //invalid
        Assert.assertNull(sut.inverseComputeIfAbsent(null, mockMapper, false));
        Assert.assertEquals(sut.inverseComputeIfAbsent(null, mockMapper, false), sut.inverseRemove(null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseComputeIfAbsent(-1, null, false));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseComputeIfAbsent(null, null, false));
        Assert.assertNull(sut.inverseComputeIfAbsent(null, mockMapper));
        Assert.assertEquals(sut.inverseComputeIfAbsent(null, mockMapper), sut.inverseRemove(null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseComputeIfAbsent(-1, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseComputeIfAbsent(null, null));
    }
    
    /**
     * JUnit test of computeIfPresent.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#computeIfPresent(Object, BiFunction, boolean)
     * @see BiMap#computeIfPresent(Object, BiFunction)
     */
    @Test
    public void testComputeIfPresent() throws Exception {
        final BiFunction<String, Integer, Integer> mapper = (String key, Integer oldValue) -> -oldValue;
        final BiFunction<String, Integer, Integer> conflictMapper = (String key, Integer oldValue) -> BoundUtility.truncate((oldValue / 2), 1, nextValue.get());
        final BiFunction<String, Integer, Integer> nullMapper = (String key, Integer oldValue) -> null;
        final BiFunction<String, Integer, Integer> mockMapper = (String key, Integer oldValue) -> -1;
        
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.computeIfPresent(key, mapper));
            values.set(keys.indexOf(key), mapper.apply(key, values.get(keys.indexOf(key))));
        });
        
        //standard, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.computeIfPresent(key, mapper, true));
            values.set(keys.indexOf(key), mapper.apply(key, values.get(keys.indexOf(key))));
        });
        
        //conflict
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                (values.get(keys.indexOf(key)) != conflictMapper.apply(key, values.get(keys.indexOf(key))).intValue()),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", conflictMapper.apply(key, values.get(keys.indexOf(key)))), () ->
                                sut.computeIfPresent(key, conflictMapper)));
        
        //conflict, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((keys.contains(key) ? values.get(keys.indexOf(key)) : null),
                    sut.computeIfPresent(key, conflictMapper, true));
            if (keys.contains(key)) {
                keyValueBiSetter.accept(key, conflictMapper.apply(key, values.get(keys.indexOf(key))));
            }
        });
        
        //absent
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.computeIfPresent(key, mapper)));
        
        //absent, force
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.computeIfPresent(key, mapper, true)));
        
        //absent, conflict
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.computeIfPresent(key, conflictMapper)));
        
        //absent, conflict, force
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.computeIfPresent(key, conflictMapper, true)));
        
        //removal
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.computeIfPresent(key, nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.computeIfPresent(key, nullMapper, true));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //invalid
        Assert.assertNull(sut.computeIfPresent(null, mockMapper, false));
        Assert.assertNull(sut.computeIfPresent("~", null, false));
        Assert.assertNull(sut.computeIfPresent(null, null, false));
        Assert.assertNull(sut.computeIfPresent(null, mockMapper));
        Assert.assertNull(sut.computeIfPresent("~", null));
        Assert.assertNull(sut.computeIfPresent(null, null));
    }
    
    /**
     * JUnit test of inverseComputeIfPresent.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseComputeIfPresent(Object, BiFunction, boolean)
     * @see BiMap#inverseComputeIfPresent(Object, BiFunction)
     */
    @Test
    public void testInverseComputeIfPresent() throws Exception {
        final BiFunction<Integer, String, String> mapper = (Integer value, String oldKey) -> oldKey.toLowerCase();
        final BiFunction<Integer, String, String> presentMapper = (Integer value, String oldKey) -> String.valueOf((char) (int) BoundUtility.truncate((oldKey.charAt(0) - 5), (int) 'A', (int) 'Z'));
        final BiFunction<Integer, String, String> nullMapper = (Integer value, String oldKey) -> null;
        final BiFunction<Integer, String, String> mockMapper = (Integer value, String oldKey) -> "~";
        
        //standard
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inverseComputeIfPresent(nextValue.decrementAndGet(), mapper));
            keys.set(values.indexOf(nextValue.get()), mapper.apply(nextValue.get(), keys.get(values.indexOf(nextValue.get()))));
        });
        
        //standard, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inverseComputeIfPresent(nextValue.decrementAndGet(), mapper, true));
            keys.set(values.indexOf(nextValue.get()), mapper.apply(nextValue.get(), keys.get(values.indexOf(nextValue.get()))));
        });
        
        //new
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.inverseComputeIfPresent(nextValue.incrementAndGet(), mapper)));
        
        //new, force
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.inverseComputeIfPresent(nextValue.incrementAndGet(), mapper, true)));
        
        //present
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.inverseComputeIfPresent(nextValue.incrementAndGet(), presentMapper)));
        
        //present, force
        sutTester.accept(keySets[3], key ->
                Assert.assertNull(
                        sut.inverseComputeIfPresent(nextValue.incrementAndGet(), presentMapper, true)));
        
        //present, conflict
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                !keys.get(values.indexOf(nextValue.get() - 1)).equals(presentMapper.apply((nextValue.get() - 1), keys.get(values.indexOf(nextValue.get() - 1)))),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", presentMapper.apply((nextValue.get() - 1), keys.get(values.indexOf(nextValue.get() - 1)))), () ->
                                sut.inverseComputeIfPresent(nextValue.decrementAndGet(), presentMapper)));
        
        //present, conflict, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((values.contains(nextValue.get() - 1) ? keys.get(values.indexOf(nextValue.get() - 1)) : null),
                    sut.inverseComputeIfPresent(nextValue.decrementAndGet(), presentMapper, true));
            if (values.contains(nextValue.get())) {
                keyValueBiSetter.accept(presentMapper.apply(nextValue.get(), (values.contains(nextValue.get()) ? keys.get(values.indexOf(nextValue.get())) : null)), nextValue.get());
            }
        });
        
        //removal
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(key,
                    sut.inverseComputeIfPresent(values.get(keys.indexOf(key)), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(key,
                    sut.inverseComputeIfPresent(values.get(keys.indexOf(key)), nullMapper, true));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //invalid
        Assert.assertNull(sut.inverseComputeIfPresent(null, mockMapper, false));
        Assert.assertNull(sut.inverseComputeIfPresent(-1, null, false));
        Assert.assertNull(sut.inverseComputeIfPresent(null, null, false));
        Assert.assertNull(sut.inverseComputeIfPresent(null, mockMapper));
        Assert.assertNull(sut.inverseComputeIfPresent(-1, null));
        Assert.assertNull(sut.inverseComputeIfPresent(null, null));
    }
    
    /**
     * JUnit test of merge.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#merge(Object, Object, BiFunction, boolean)
     * @see BiMap#merge(Object, Object, BiFunction)
     */
    @Test
    public void testMerge() throws Exception {
        final BiFunction<Integer, Integer, Integer> mapper = (Integer oldValue, Integer merge) -> merge;
        final BiFunction<Integer, Integer, Integer> nullMapper = (Integer oldValue, Integer merge) -> null;
        
        //standard
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(key, nextValue.incrementAndGet(), mapper));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //standard, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(key, nextValue.incrementAndGet(), mapper, true));
            values.set(keys.indexOf(key), nextValue.get());
        });
        
        //conflict
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                (values.get(keys.indexOf(key)) != (nextValue.get() - 1)),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", (nextValue.get() - 1)), () ->
                                sut.merge(key, nextValue.decrementAndGet(), mapper)));
        
        //conflict, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((keys.contains(key) ? values.get(keys.indexOf(key)) : null),
                    sut.merge(key, nextValue.decrementAndGet(), mapper, true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //absent
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.merge(key, nextValue.incrementAndGet(), mapper));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.merge(key, nextValue.incrementAndGet(), mapper, true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //absent, conflict
        sutTester.accept(keySets[3], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the value: {}", (nextValue.get() - 1)), () ->
                        sut.merge(key, nextValue.decrementAndGet(), mapper)));
        
        //absent, conflict, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.merge(key, nextValue.decrementAndGet(), mapper, true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //removal
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(key, nextValue.get(), nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(values.get(keys.indexOf(key)),
                    sut.merge(key, nextValue.get(), nullMapper, true));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //invalid
        Assert.assertNull(sut.merge(null, -1, Integer::sum, false));
        Assert.assertEquals(-1, sut.merge(null, -1, nullMapper, false).intValue());
        Assert.assertNull(sut.merge("~", null, Integer::sum, false));
        Assert.assertNull(sut.merge("~", -1, null, false));
        Assert.assertEquals(-1, sut.merge("~", null, nullMapper, false).intValue());
        Assert.assertNull(sut.merge(null, null, null, false));
        Assert.assertNull(sut.merge(null, -1, Integer::sum));
        Assert.assertEquals(-1, sut.merge(null, -1, nullMapper).intValue());
        Assert.assertNull(sut.merge("~", null, Integer::sum));
        Assert.assertNull(sut.merge("~", -1, null));
        Assert.assertEquals(-1, sut.merge("~", null, nullMapper).intValue());
        Assert.assertNull(sut.merge(null, null, null));
    }
    
    /**
     * JUnit test of inverseMerge.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseMerge(Object, Object, BiFunction, boolean)
     * @see BiMap#inverseMerge(Object, Object, BiFunction)
     */
    @Test
    public void testInverseMerge() throws Exception {
        final BiFunction<String, String, String> mapper = (String oldKey, String merge) -> merge;
        final BiFunction<String, String, String> nullMapper = (String oldKey, String merge) -> null;
        
        //standard
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inverseMerge(nextValue.decrementAndGet(), key, mapper));
            keys.set(values.indexOf(nextValue.get()), key);
        });
        
        //standard, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertEquals(keys.get(values.indexOf(nextValue.get() - 1)),
                    sut.inverseMerge(nextValue.decrementAndGet(), key, mapper, true));
            keys.set(values.indexOf(nextValue.get()), key);
        });
        
        //new
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.inverseMerge(nextValue.incrementAndGet(), key, mapper));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //new, force
        sutTester.accept(keySets[3], key -> {
            Assert.assertNull(
                    sut.inverseMerge(nextValue.incrementAndGet(), key, mapper, true));
            keys.add(key);
            values.add(nextValue.get());
        });
        
        //present
        sutTester.accept(keySets[2], key ->
                TestUtils.assertException(IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", key), () ->
                        sut.inverseMerge(nextValue.incrementAndGet(), key, mapper)));
        
        //present, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertNull(
                    sut.inverseMerge(nextValue.incrementAndGet(), key, mapper, true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //present, conflict
        sutTester.accept(keySets[2], key ->
                TestUtils.assertExceptionIf(() ->
                                !keys.get(values.indexOf(nextValue.get() - 1)).equals(key),
                        IllegalArgumentException.class, StringUtility.format("The map already contains the key: {}", key), () ->
                                sut.inverseMerge(nextValue.decrementAndGet(), key, mapper)));
        
        //present, conflict, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals((values.contains(nextValue.get() - 1) ? keys.get(values.indexOf(nextValue.get() - 1)) : null),
                    sut.inverseMerge(nextValue.decrementAndGet(), key, mapper, true));
            keyValueBiSetter.accept(key, nextValue.get());
        });
        
        //removal
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(key,
                    sut.inverseMerge(values.get(keys.indexOf(key)), key, nullMapper));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //removal, force
        sutTester.accept(keySets[2], key -> {
            Assert.assertEquals(key,
                    sut.inverseMerge(values.get(keys.indexOf(key)), key, nullMapper, true));
            values.remove(keys.indexOf(key));
            keys.remove(key);
        });
        
        //invalid
        Assert.assertNull(sut.inverseMerge(null, "~", String::concat, false));
        Assert.assertEquals("~", sut.inverseMerge(null, "~", nullMapper, false));
        Assert.assertNull(sut.inverseMerge(-1, null, String::concat, false));
        Assert.assertNull(sut.inverseMerge(-1, "~", null, false));
        Assert.assertEquals("~", sut.inverseMerge(-1, null, nullMapper, false));
        Assert.assertNull(sut.inverseMerge(null, null, null, false));
        Assert.assertNull(sut.inverseMerge(null, "~", String::concat));
        Assert.assertEquals("~", sut.inverseMerge(null, "~", nullMapper));
        Assert.assertNull(sut.inverseMerge(-1, null, String::concat));
        Assert.assertNull(sut.inverseMerge(-1, "~", null));
        Assert.assertEquals("~", sut.inverseMerge(-1, null, nullMapper));
        Assert.assertNull(sut.inverseMerge(null, null, null));
    }
    
    /**
     * JUnit test of forEach.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#forEach(BiConsumer)
     */
    @Test
    public void testForEach() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            sut.forEach((key, value) ->
                    sut.replace(key, -value));
            values.replaceAll(value -> -value);
        });
        
        //modification
        sutTestRunner.accept(() ->
                TestUtils.assertException(ConcurrentModificationException.class, () ->
                        sut.forEach((key, value) -> {
                            sut.remove(key);
                            sut.put(key, value);
                        })));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.forEach(null));
    }
    
    /**
     * JUnit test of inverseForEach.
     *
     * @throws Exception When there is an exception.
     * @see BiMap#inverseForEach(BiConsumer)
     */
    @Test
    public void testInverseForEach() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            TestUtils.assertException(ConcurrentModificationException.class, () ->
                    sut.inverseForEach((value, key) -> sut.replace(key, -value)));
            values.replaceAll(value -> -value);
        });
        
        //modification
        sutTestRunner.accept(() ->
                TestUtils.assertException(ConcurrentModificationException.class, () ->
                        sut.inverseForEach((value, key) -> {
                            sut.remove(key);
                            sut.put(key, value);
                        })));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.inverseForEach(null));
    }
    
}
