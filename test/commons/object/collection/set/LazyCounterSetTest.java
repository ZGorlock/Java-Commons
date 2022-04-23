/*
 * File:    LazyCounterSetTest.java
 * Package: commons.object.collection.set
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.set;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import commons.lambda.stream.collector.ArrayCollectors;
import commons.lambda.stream.collector.MapCollectors;
import commons.math.MathUtility;
import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
import commons.object.string.StringUtility;
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
 * JUnit test of LazyCounterSet.
 *
 * @see LazyCounterSet
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({LazyCounterSet.class})
public class LazyCounterSetTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(LazyCounterSetTest.class);
    
    
    //Static Fields
    
    /**
     * A list of element sets to use for testing.
     */
    private static final List<String>[] elementSets = Stream.of(StringUtility.VOWEL_CHARS, StringUtility.CONSONANT_CHARS)
            .flatMap(elementSetSource -> Stream.of(elementSetSource.toUpperCase(), elementSetSource.toLowerCase()))
            .map(elementSetSeed -> StringUtility.stringStream(elementSetSeed).collect(Collectors.toUnmodifiableList()))
            .toArray(ArrayCollectors.generator(List.class));
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private LazyCounterSet<String> sut;
    
    /**
     * The map of elements and associated counters of the system under test.
     */
    private Map<String, Integer> counters;
    
    
    //Functions
    
    /**
     * Validates the content of the system under test.
     */
    private final Runnable sutValidator = () -> {
        Assert.assertTrue(sut.containsAll(counters.keySet()));
        Assert.assertEquals(counters.size(), sut.size());
        Assert.assertTrue(sut.stream().map(sut::get).allMatch(Objects::nonNull));
        Assert.assertTrue(sut.stream().allMatch(e -> Objects.equals(counters.get(e), sut.get(e))));
    };
    
    /**
     * Generates a comparable counter map from a LazyCounterSet.
     */
    private final Function<CounterSet<String>, Map<String, Integer>> counterMapGenerator = (CounterSet<String> set) ->
            set.stream().collect(MapCollectors.toHashMap(Function.identity(), set::get));
    
    
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
        counters = elementSets[2].stream().collect(MapCollectors.mapEachTo(() -> MathUtility.random(100)));
        sut = new LazyCounterSet<>(counters);
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
     * @see LazyCounterSet#LazyCounterSet(Collection)
     * @see LazyCounterSet#LazyCounterSet(Map)
     * @see LazyCounterSet#LazyCounterSet(CounterSet)
     * @see LazyCounterSet#LazyCounterSet()
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testConstructors() throws Exception {
        final List<String> testList1 = elementSets[2];
        final List<String> testList2 = ListUtility.duplicateInOrder(testList1, 5);
        final Map<String, Integer> testMap = MapUtility.mapOf(
                testList1,
                IntStream.range(0, testList1.size()).mapToObj(i -> MathUtility.random(100)).collect(Collectors.toList()));
        LazyCounterSet<String> set1;
        LazyCounterSet<String> set2;
        LazyCounterSet<String> set3;
        LazyCounterSet<String> set4;
        LazyCounterSet<String> set5;
        
        //distinct collection
        set1 = new LazyCounterSet<>(testList1);
        Assert.assertNotNull(set1);
        TestUtils.assertListEquals(
                ListUtility.toList(set1),
                testList1, false);
        Assert.assertTrue(set1.stream().allMatch(element -> (set1.get(element) == 1)));
        
        //collection
        set2 = new LazyCounterSet<>(testList2);
        Assert.assertNotNull(set2);
        TestUtils.assertListEquals(
                ListUtility.toList(set2),
                testList1, false);
        Assert.assertTrue(set2.stream().allMatch(element -> (set2.get(element) == 5)));
        
        //map
        set3 = new LazyCounterSet<>(testMap);
        Assert.assertNotNull(set3);
        TestUtils.assertListEquals(
                ListUtility.toList(set3),
                testList1, false);
        Assert.assertTrue(set3.stream().allMatch(element -> (set3.get(element).intValue() == testMap.get(element).intValue())));
        
        //iterable map
        set4 = new LazyCounterSet<>(set3);
        Assert.assertNotNull(set4);
        TestUtils.assertListEquals(
                ListUtility.toList(set4),
                ListUtility.toList(set3), false);
        Assert.assertTrue(set4.stream().allMatch(element -> (set4.get(element).intValue() == set3.get(element).intValue())));
        
        //empty
        set5 = new LazyCounterSet<>();
        Assert.assertNotNull(set5);
        Assert.assertTrue(set5.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new LazyCounterSet<>((Collection<?>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new LazyCounterSet<>((Map<?, Integer>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new LazyCounterSet<>((LazyCounterSet<?>) null));
    }
    
    /**
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#add(Object)
     */
    @Test
    public void testAdd() throws Exception {
        //standard
        elementSets[0].forEach(element -> {
            Assert.assertTrue(
                    sut.add(element));
            Assert.assertTrue(sut.contains(element));
            Assert.assertEquals(0, sut.get(element).intValue());
            counters.put(element, 0);
        });
        sutValidator.run();
        
        //present
        elementSets[2].forEach(element -> {
            sut.set(element, 1);
            counters.replace(element, 1);
            Assert.assertFalse(
                    sut.add(element));
            Assert.assertTrue(sut.contains(element));
            Assert.assertEquals(1, sut.get(element).intValue());
        });
        sutValidator.run();
        
        //invalid
        Assert.assertTrue(sut.add(null));
        Assert.assertTrue(sut.remove(null));
    }
    
    /**
     * JUnit test of addAll.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#addAll(Collection)
     */
    @Test
    public void testAddAll() throws Exception {
        //standard
        Assert.assertTrue(
                sut.addAll(elementSets[0]));
        elementSets[0].forEach(element -> {
            Assert.assertTrue(sut.contains(element));
            Assert.assertEquals(0, sut.get(element).intValue());
            counters.put(element, 0);
        });
        sutValidator.run();
        
        //partial
        elementSets[2].forEach(element -> {
            sut.set(element, 1);
            counters.replace(element, 1);
        });
        Assert.assertTrue(
                sut.addAll(ListUtility.merge(elementSets[2], elementSets[1])));
        elementSets[2].forEach(element -> {
            Assert.assertTrue(sut.contains(element));
            Assert.assertEquals(1, sut.get(element).intValue());
        });
        elementSets[1].forEach(element -> {
            Assert.assertTrue(sut.contains(element));
            Assert.assertEquals(0, sut.get(element).intValue());
            counters.putIfAbsent(element, 0);
        });
        sutValidator.run();
        
        //present
        elementSets[2].forEach(element -> {
            sut.set(element, 1);
            counters.replace(element, 1);
        });
        Assert.assertFalse(
                sut.addAll(elementSets[2]));
        elementSets[2].forEach(element -> {
            Assert.assertTrue(sut.contains(element));
            Assert.assertEquals(1, sut.get(element).intValue());
        });
        sutValidator.run();
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.addAll(null));
    }
    
    /**
     * JUnit test of remove.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#remove(Object)
     */
    @Test
    public void testRemove() throws Exception {
        //standard
        ListUtility.selectN(elementSets[2], 3).forEach(element -> {
            Assert.assertTrue(
                    sut.remove(element));
            Assert.assertFalse(sut.contains(element));
            Assert.assertNull(sut.get(element));
            counters.remove(element);
        });
        sutValidator.run();
        
        //absent
        ListUtility.selectN(elementSets[0], 3).forEach(element -> {
            Assert.assertFalse(
                    sut.remove(element));
            Assert.assertFalse(sut.contains(element));
            Assert.assertNull(sut.get(element));
        });
        sutValidator.run();
        
        //invalid
        Assert.assertTrue(sut.add(null));
        Assert.assertTrue(sut.remove(null));
    }
    
    /**
     * JUnit test of removeAll.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#removeAll(Collection)
     */
    @SuppressWarnings("SlowAbstractSetRemoveAll")
    @Test
    public void testRemoveAll() throws Exception {
        List<String> remove;
        
        //standard
        remove = ListUtility.selectN(ListUtility.toList(counters.keySet()), 3);
        Assert.assertTrue(
                sut.removeAll(remove));
        remove.forEach(element -> {
            Assert.assertFalse(sut.contains(element));
            Assert.assertNull(sut.get(element));
            counters.remove(element);
        });
        sutValidator.run();
        
        //partial
        remove = ListUtility.selectN(ListUtility.toList(counters.keySet()), 3);
        Assert.assertTrue(
                sut.removeAll(ListUtility.merge(elementSets[1], remove)));
        remove.forEach(element -> {
            Assert.assertFalse(sut.contains(element));
            Assert.assertNull(sut.get(element));
            counters.remove(element);
        });
        sutValidator.run();
        
        //absent
        remove = elementSets[0];
        Assert.assertFalse(
                sut.removeAll(remove));
        remove.forEach(element -> {
            Assert.assertFalse(sut.contains(element));
            Assert.assertNull(sut.get(element));
        });
        sutValidator.run();
        
        //all
        remove = ListUtility.toList(counters.keySet());
        Assert.assertTrue(
                sut.removeAll(remove));
        Assert.assertTrue(sut.isEmpty());
        counters.clear();
        sutValidator.run();
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.removeAll(null));
    }
    
    /**
     * JUnit test of removeIf.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#removeIf(Predicate)
     */
    @Test
    public void testRemoveIf() throws Exception {
        //standard
        Assert.assertTrue(
                sut.removeIf(e -> (e.charAt(0) > 'N')));
        elementSets[2].stream().filter(e -> (e.charAt(0) > 'N')).forEach(element -> {
            Assert.assertFalse(sut.contains(element));
            Assert.assertNull(sut.get(element));
            counters.remove(element);
        });
        sutValidator.run();
        
        //none
        Assert.assertFalse(
                sut.removeIf(e -> false));
        sutValidator.run();
        
        //all
        Assert.assertTrue(
                sut.removeIf(e -> true));
        Assert.assertTrue(sut.isEmpty());
        counters.clear();
        sutValidator.run();
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.removeIf(null));
    }
    
    /**
     * JUnit test of retainAll.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#retainAll(Collection)
     */
    @Test
    public void testRetainAll() throws Exception {
        List<String> retain;
        
        //standard
        retain = ListUtility.selectN(ListUtility.toList(counters.keySet()), 18);
        Assert.assertTrue(
                sut.retainAll(retain));
        ListUtility.removeAllAndGet(ListUtility.toList(counters.keySet()), retain).forEach(element -> {
            Assert.assertFalse(sut.contains(element));
            Assert.assertNull(sut.get(element));
            counters.remove(element);
        });
        sutValidator.run();
        
        //all
        retain = ListUtility.toList(counters.keySet());
        Assert.assertFalse(
                sut.retainAll(retain));
        sutValidator.run();
        
        //partial
        retain = ListUtility.selectN(ListUtility.toList(counters.keySet()), 15);
        Assert.assertTrue(
                sut.retainAll(ListUtility.merge(elementSets[1], retain)));
        ListUtility.removeAllAndGet(ListUtility.toList(counters.keySet()), retain).forEach(element -> {
            Assert.assertFalse(sut.contains(element));
            Assert.assertNull(sut.get(element));
            counters.remove(element);
        });
        sutValidator.run();
        
        //absent
        retain = elementSets[0];
        Assert.assertTrue(
                sut.retainAll(retain));
        Assert.assertTrue(sut.isEmpty());
        counters.clear();
        sutValidator.run();
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.retainAll(null));
    }
    
    /**
     * JUnit test of clear.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#clear()
     */
    @Test
    public void testClear() throws Exception {
        //standard
        sut.clear();
        Assert.assertTrue(sut.isEmpty());
        counters.clear();
        sutValidator.run();
    }
    
    /**
     * JUnit test of resetAll.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#resetAll()
     */
    @Test
    public void testResetAll() throws Exception {
        //standard
        sut.resetAll();
        Assert.assertFalse(sut.isEmpty());
        counters.keySet().forEach(element -> counters.replace(element, 0));
        sutValidator.run();
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#get(Object)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        elementSets[2].forEach(element ->
                Assert.assertEquals(counters.get(element), sut.get(element)));
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element ->
                Assert.assertNull(sut.get(element)));
        sutValidator.run();
        
        //invalid
        Assert.assertNull(sut.get(null));
    }
    
    /**
     * JUnit test of getAndSet.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#getAndSet(Object, int)
     */
    @Test
    public void testGetAndSet() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndSet(element, -1));
            Assert.assertEquals(-1, sut.get(element).intValue());
            counters.replace(element, -1);
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndSet(element, -1));
            Assert.assertEquals(-1, sut.get(element).intValue());
            counters.put(element, -1);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertNull(sut.getAndSet(null, 0));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#set(Object, int)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.set(element, -1);
            Assert.assertEquals(-1, sut.get(element).intValue());
            counters.replace(element, -1);
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            sut.set(element, -1);
            Assert.assertEquals(-1, sut.get(element).intValue());
            counters.put(element, -1);
        });
        sutValidator.run();
        
        //invalid
        sut.set(null, 0);
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of getAndReset.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#getAndReset(Object)
     */
    @Test
    public void testGetAndReset() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndReset(element));
            Assert.assertEquals(0, sut.get(element).intValue());
            counters.replace(element, 0);
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndReset(element));
            Assert.assertEquals(0, sut.get(element).intValue());
            counters.put(element, 0);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertNull(sut.getAndReset(null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of reset.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#reset(Object)
     */
    @Test
    public void testReset() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.reset(element);
            Assert.assertEquals(0, sut.get(element).intValue());
            counters.replace(element, 0);
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            sut.reset(element);
            Assert.assertEquals(0, sut.get(element).intValue());
            counters.put(element, 0);
        });
        sutValidator.run();
        
        //invalid
        sut.reset(null);
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of getAndStep.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#getAndStep(Object, int)
     */
    @Test
    public void testGetAndStep() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndStep(element, 10));
            Assert.assertEquals((counters.get(element) + 10), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 10));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndStep(element, -6));
            Assert.assertEquals(-6, sut.get(element).intValue());
            counters.put(element, -6);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertNull(sut.getAndStep(null, 0));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of stepAndGet.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#stepAndGet(Object, int)
     */
    @Test
    public void testStepAndGet() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals((counters.get(element) + 10), sut.stepAndGet(element, 10).intValue());
            Assert.assertEquals((counters.get(element) + 10), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 10));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertEquals(-6, sut.stepAndGet(element, -6).intValue());
            Assert.assertEquals(-6, sut.get(element).intValue());
            counters.put(element, -6);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertEquals(0, sut.stepAndGet(null, 0).intValue());
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of step.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#step(Object, int)
     */
    @Test
    public void testStep() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.step(element, 10);
            Assert.assertEquals((counters.get(element) + 10), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 10));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            sut.step(element, -6);
            Assert.assertEquals(-6, sut.get(element).intValue());
            counters.put(element, -6);
        });
        sutValidator.run();
        
        //invalid
        sut.step(null, 0);
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of getAndIncrement.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#getAndIncrement(Object)
     */
    @Test
    public void testGetAndIncrement() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndIncrement(element));
            Assert.assertEquals((counters.get(element) + 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 1));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndIncrement(element));
            Assert.assertEquals(1, sut.get(element).intValue());
            counters.put(element, 1);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertNull(sut.getAndIncrement(null));
        counters.put(null, 1);
    }
    
    /**
     * JUnit test of incrementAndGet.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#incrementAndGet(Object)
     */
    @Test
    public void testIncrementAndGet() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals((counters.get(element) + 1), sut.incrementAndGet(element).intValue());
            Assert.assertEquals((counters.get(element) + 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 1));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertEquals(1, sut.incrementAndGet(element).intValue());
            Assert.assertEquals(1, sut.get(element).intValue());
            counters.put(element, 1);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertEquals(1, sut.incrementAndGet(null).intValue());
        counters.put(null, 1);
    }
    
    /**
     * JUnit test of increment.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#increment(Object)
     */
    @Test
    public void testIncrement() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.increment(element);
            Assert.assertEquals((counters.get(element) + 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 1));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            sut.increment(element);
            Assert.assertEquals(1, sut.get(element).intValue());
            counters.put(element, 1);
        });
        sutValidator.run();
        
        //invalid
        sut.increment(null);
        counters.put(null, 1);
    }
    
    /**
     * JUnit test of getAndDecrement.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#getAndDecrement(Object)
     */
    @Test
    public void testGetAndDecrement() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndDecrement(element));
            Assert.assertEquals((counters.get(element) - 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) - 1));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndDecrement(element));
            Assert.assertEquals(-1, sut.get(element).intValue());
            counters.put(element, -1);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertNull(sut.getAndDecrement(null));
        counters.put(null, -1);
    }
    
    /**
     * JUnit test of decrementAndGet.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#decrementAndGet(Object)
     */
    @Test
    public void testDecrementAndGet() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals((counters.get(element) - 1), sut.decrementAndGet(element).intValue());
            Assert.assertEquals((counters.get(element) - 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) - 1));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertEquals(-1, sut.decrementAndGet(element).intValue());
            Assert.assertEquals(-1, sut.get(element).intValue());
            counters.put(element, -1);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertEquals(-1, sut.decrementAndGet(null).intValue());
        counters.put(null, -1);
    }
    
    /**
     * JUnit test of decrement.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#decrement(Object)
     */
    @Test
    public void testDecrement() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.decrement(element);
            Assert.assertEquals((counters.get(element) - 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) - 1));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            sut.decrement(element);
            Assert.assertEquals(-1, sut.get(element).intValue());
            counters.put(element, -1);
        });
        sutValidator.run();
        
        //invalid
        sut.decrement(null);
        counters.put(null, -1);
    }
    
    /**
     * JUnit test of getAndUpdate.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#getAndUpdate(Object, IntUnaryOperator)
     */
    @Test
    public void testGetAndUpdate() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndUpdate(element, (i -> -i)));
            Assert.assertEquals(-counters.get(element), sut.get(element).intValue());
            counters.replace(element, -counters.get(element));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndUpdate(element, (i -> ((i + 2) * 2))));
            Assert.assertEquals(4, sut.get(element).intValue());
            counters.put(element, 4);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertNull(sut.getAndUpdate(null, (i -> 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndUpdate("B", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndUpdate(null, null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of updateAndGet.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#updateAndGet(Object, IntUnaryOperator)
     */
    @Test
    public void testUpdateAndGet() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(-counters.get(element), sut.updateAndGet(element, (i -> -i)).intValue());
            Assert.assertEquals(-counters.get(element), sut.get(element).intValue());
            counters.replace(element, -counters.get(element));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertEquals(4, sut.updateAndGet(element, (i -> ((i + 2) * 2))).intValue());
            Assert.assertEquals(4, sut.get(element).intValue());
            counters.put(element, 4);
        });
        sutValidator.run();
        
        //invalid
        Assert.assertEquals(0, sut.updateAndGet(null, (i -> 0)).intValue());
        TestUtils.assertException(NullPointerException.class, () ->
                sut.updateAndGet("B", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.updateAndGet(null, null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of update.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#update(Object, IntUnaryOperator)
     */
    @Test
    public void testUpdate() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.update(element, (i -> -i));
            Assert.assertEquals(-counters.get(element), sut.get(element).intValue());
            counters.replace(element, -counters.get(element));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            sut.update(element, (i -> ((i + 2) * 2)));
            Assert.assertEquals(4, sut.get(element).intValue());
            counters.put(element, 4);
        });
        sutValidator.run();
        
        //invalid
        sut.update(null, (i -> 0));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.update("B", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.update(null, null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of getAndAccumulate.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#getAndAccumulate(Object, int, IntBinaryOperator)
     */
    @Test
    public void testGetAndAccumulate() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndAccumulate(element, elementSets[2].indexOf(element), ((i, x) -> (-i + x))));
            Assert.assertEquals((-counters.get(element) + elementSets[2].indexOf(element)), sut.get(element).intValue());
            counters.replace(element, (-counters.get(element) + elementSets[2].indexOf(element)));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndAccumulate(element, elementSets[3].indexOf(element), ((i, x) -> ((i + 2) * x))));
            Assert.assertEquals((elementSets[3].indexOf(element) * 2), sut.get(element).intValue());
            counters.put(element, (elementSets[3].indexOf(element) * 2));
        });
        sutValidator.run();
        
        //invalid
        Assert.assertNull(sut.getAndAccumulate(null, 0, ((i, x) -> 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndAccumulate("B", 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndAccumulate(null, 0, null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of accumulateAndGet.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#accumulateAndGet(Object, int, IntBinaryOperator)
     */
    @Test
    public void testAccumulateAndGet() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals((-counters.get(element) + elementSets[2].indexOf(element)), sut.accumulateAndGet(element, elementSets[2].indexOf(element), ((i, x) -> (-i + x))).intValue());
            Assert.assertEquals((-counters.get(element) + elementSets[2].indexOf(element)), sut.get(element).intValue());
            counters.replace(element, (-counters.get(element) + elementSets[2].indexOf(element)));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertEquals((elementSets[3].indexOf(element) * 2), sut.accumulateAndGet(element, elementSets[3].indexOf(element), ((i, x) -> ((i + 2) * x))).intValue());
            Assert.assertEquals((elementSets[3].indexOf(element) * 2), sut.get(element).intValue());
            counters.put(element, (elementSets[3].indexOf(element) * 2));
        });
        sutValidator.run();
        
        //invalid
        Assert.assertEquals(0, sut.accumulateAndGet(null, 0, ((i, x) -> 0)).intValue());
        TestUtils.assertException(NullPointerException.class, () ->
                sut.accumulateAndGet("B", 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.accumulateAndGet(null, 0, null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of accumulate.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#accumulate(Object, int, IntBinaryOperator)
     */
    @Test
    public void testAccumulate() throws Exception {
        //standard
        elementSets[2].forEach(element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.accumulate(element, elementSets[2].indexOf(element), ((i, x) -> (-i + x)));
            Assert.assertEquals((-counters.get(element) + elementSets[2].indexOf(element)), sut.get(element).intValue());
            counters.replace(element, (-counters.get(element) + elementSets[2].indexOf(element)));
        });
        sutValidator.run();
        
        //absent
        elementSets[3].forEach(element -> {
            Assert.assertNull(sut.get(element));
            sut.accumulate(element, elementSets[3].indexOf(element), ((i, x) -> ((i + 2) * x)));
            Assert.assertEquals((elementSets[3].indexOf(element) * 2), sut.get(element).intValue());
            counters.put(element, (elementSets[3].indexOf(element) * 2));
        });
        sutValidator.run();
        
        //invalid
        sut.accumulate(null, 0, ((i, x) -> 0));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.accumulate("B", 0, null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.accumulate(null, 0, null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of getAndModify.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#getAndModify(Object, Function)
     */
    @Test
    public void testGetAndModify() throws Exception {
        final BiConsumer<Object[], Function<AtomicInteger, Integer>> getAndModifyInvoker = (Object[] params, Function<AtomicInteger, Integer> counterFunction) -> {
            final Integer elementIndex = (Integer) params[0];
            final Integer value = (Integer) params[1];
            final Integer postValue = (Integer) params[2];
            Assert.assertEquals(value, sut.getAndModify(elementSets[2].get(elementIndex), counterFunction));
            Assert.assertEquals(postValue, sut.get(elementSets[2].get(elementIndex)));
            counters.replace(elementSets[2].get(elementIndex), postValue);
        };
        
        //standard
        sut.forEach(element -> sut.set(element, 1));
        counters.keySet().forEach(element -> counters.replace(element, 1));
        getAndModifyInvoker.accept(new Object[] {0, 1, 1}, AtomicInteger::get);
        getAndModifyInvoker.accept(new Object[] {1, 1, 5}, (counter -> counter.getAndSet(5)));
        getAndModifyInvoker.accept(new Object[] {2, 1, 0}, (counter -> counter.getAndSet(0)));
        getAndModifyInvoker.accept(new Object[] {3, 1, 8}, (counter -> counter.getAndAdd(7)));
        getAndModifyInvoker.accept(new Object[] {4, 1, 8}, (counter -> counter.addAndGet(7)));
        getAndModifyInvoker.accept(new Object[] {5, 1, 2}, AtomicInteger::getAndIncrement);
        getAndModifyInvoker.accept(new Object[] {6, 1, 2}, AtomicInteger::incrementAndGet);
        getAndModifyInvoker.accept(new Object[] {7, 1, 0}, AtomicInteger::getAndDecrement);
        getAndModifyInvoker.accept(new Object[] {8, 1, 0}, AtomicInteger::decrementAndGet);
        getAndModifyInvoker.accept(new Object[] {9, 1, -1}, (counter -> counter.getAndUpdate(i -> -i)));
        getAndModifyInvoker.accept(new Object[] {10, 1, -1}, (counter -> counter.updateAndGet(i -> -i)));
        getAndModifyInvoker.accept(new Object[] {11, 1, -3}, (counter -> counter.getAndAccumulate(3, ((i, x) -> (-i * x)))));
        getAndModifyInvoker.accept(new Object[] {12, 1, -3}, (counter -> counter.accumulateAndGet(3, ((i, x) -> (-i * x)))));
        sutValidator.run();
        
        //invalid
        Assert.assertNull(sut.getAndModify(null, AtomicInteger::get));
        TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index 50 out of bounds for length {}", (sut.size() - 1)), () ->
                sut.getAndModify("B", (e -> ListUtility.toList(counters.values()).get(50))));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndModify("B", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndModify(null, null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of modifyAndGet.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#modifyAndGet(Object, Function)
     */
    @Test
    public void testModifyAndGet() throws Exception {
        final BiConsumer<Object[], Function<AtomicInteger, Integer>> modifyAndGetInvoker = (Object[] params, Function<AtomicInteger, Integer> counterFunction) -> {
            final Integer elementIndex = (Integer) params[0];
            final Integer value = (Integer) params[1];
            final Integer postValue = (Integer) params[2];
            Assert.assertEquals(value, sut.modifyAndGet(elementSets[2].get(elementIndex), counterFunction));
            Assert.assertEquals(postValue, sut.get(elementSets[2].get(elementIndex)));
            counters.replace(elementSets[2].get(elementIndex), postValue);
        };
        
        //standard
        sut.forEach(element -> sut.set(element, 1));
        counters.keySet().forEach(element -> counters.replace(element, 1));
        modifyAndGetInvoker.accept(new Object[] {0, 1, 1}, AtomicInteger::get);
        modifyAndGetInvoker.accept(new Object[] {1, 1, 5}, (counter -> counter.getAndSet(5)));
        modifyAndGetInvoker.accept(new Object[] {2, 1, 0}, (counter -> counter.getAndSet(0)));
        modifyAndGetInvoker.accept(new Object[] {3, 1, 8}, (counter -> counter.getAndAdd(7)));
        modifyAndGetInvoker.accept(new Object[] {4, 8, 8}, (counter -> counter.addAndGet(7)));
        modifyAndGetInvoker.accept(new Object[] {5, 1, 2}, AtomicInteger::getAndIncrement);
        modifyAndGetInvoker.accept(new Object[] {6, 2, 2}, AtomicInteger::incrementAndGet);
        modifyAndGetInvoker.accept(new Object[] {7, 1, 0}, AtomicInteger::getAndDecrement);
        modifyAndGetInvoker.accept(new Object[] {8, 0, 0}, AtomicInteger::decrementAndGet);
        modifyAndGetInvoker.accept(new Object[] {9, 1, -1}, (counter -> counter.getAndUpdate(i -> -i)));
        modifyAndGetInvoker.accept(new Object[] {10, -1, -1}, (counter -> counter.updateAndGet(i -> -i)));
        modifyAndGetInvoker.accept(new Object[] {11, 1, -3}, (counter -> counter.getAndAccumulate(3, ((i, x) -> (-i * x)))));
        modifyAndGetInvoker.accept(new Object[] {12, -3, -3}, (counter -> counter.accumulateAndGet(3, ((i, x) -> (-i * x)))));
        sutValidator.run();
        
        //invalid
        Assert.assertEquals(0, sut.modifyAndGet(null, AtomicInteger::get).intValue());
        TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index 50 out of bounds for length {}", (sut.size() - 1)), () ->
                sut.modifyAndGet("B", (e -> ListUtility.toList(counters.values()).get(50))));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.modifyAndGet("B", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.modifyAndGet(null, null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of modify.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#modify(Object, Function)
     */
    @Test
    public void testModify() throws Exception {
        final BiConsumer<Object[], Function<AtomicInteger, Integer>> modifyInvoker = (Object[] params, Function<AtomicInteger, Integer> counterFunction) -> {
            final Integer elementIndex = (Integer) params[0];
            final Integer postValue = (Integer) params[1];
            sut.getAndModify(elementSets[2].get(elementIndex), counterFunction);
            Assert.assertEquals(postValue, sut.get(elementSets[2].get(elementIndex)));
            counters.replace(elementSets[2].get(elementIndex), postValue);
        };
        
        //standard
        sut.forEach(element -> sut.set(element, 1));
        counters.keySet().forEach(element -> counters.replace(element, 1));
        modifyInvoker.accept(new Object[] {0, 1}, AtomicInteger::get);
        modifyInvoker.accept(new Object[] {1, 5}, (counter -> counter.getAndSet(5)));
        modifyInvoker.accept(new Object[] {2, 0}, (counter -> counter.getAndSet(0)));
        modifyInvoker.accept(new Object[] {3, 8}, (counter -> counter.getAndAdd(7)));
        modifyInvoker.accept(new Object[] {4, 8}, (counter -> counter.addAndGet(7)));
        modifyInvoker.accept(new Object[] {5, 2}, AtomicInteger::getAndIncrement);
        modifyInvoker.accept(new Object[] {6, 2}, AtomicInteger::incrementAndGet);
        modifyInvoker.accept(new Object[] {7, 0}, AtomicInteger::getAndDecrement);
        modifyInvoker.accept(new Object[] {8, 0}, AtomicInteger::decrementAndGet);
        modifyInvoker.accept(new Object[] {9, -1}, (counter -> counter.getAndUpdate(i -> -i)));
        modifyInvoker.accept(new Object[] {10, -1}, (counter -> counter.updateAndGet(i -> -i)));
        modifyInvoker.accept(new Object[] {11, -3}, (counter -> counter.getAndAccumulate(3, ((i, x) -> (-i * x)))));
        modifyInvoker.accept(new Object[] {12, -3}, (counter -> counter.accumulateAndGet(3, ((i, x) -> (-i * x)))));
        sutValidator.run();
        
        //invalid
        sut.modify(null, AtomicInteger::get);
        TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index 50 out of bounds for length {}", (sut.size() - 1)), () ->
                sut.modify("B", (e -> ListUtility.toList(counters.values()).get(50))));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.modify("B", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.modify(null, null));
        counters.put(null, 0);
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#clone()
     */
    @Test
    public void testClone() throws Exception {
        LazyCounterSet<String> clone;
        
        //standard
        clone = sut.clone();
        Assert.assertNotNull(clone);
        Assert.assertTrue(clone instanceof LazyCounterSet);
        TestUtils.assertMapEquals(
                counterMapGenerator.apply(clone),
                counterMapGenerator.apply(sut));
        Assert.assertEquals(sut, clone);
        Assert.assertNotSame(sut, clone);
    }
    
    /**
     * JUnit test of equals.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals() throws Exception {
        Set<String> other;
        
        //standard
        other = new LazyCounterSet<>(counters);
        TestUtils.assertMapEquals(
                counterMapGenerator.apply((CounterSet<String>) other),
                counterMapGenerator.apply(sut));
        Assert.assertTrue(sut.equals(other));
        Assert.assertNotSame(sut, other);
        
        //normal set
        other = counters.keySet();
        TestUtils.assertListEquals(
                ListUtility.toList(other),
                sut, false);
        Assert.assertFalse(sut.equals(other));
        Assert.assertNotSame(sut, other);
        
        //empty
        Assert.assertTrue(new LazyCounterSet<>().equals(new LazyCounterSet<>()));
        TestUtils.assertListEquals(
                ListUtility.toList(new LazyCounterSet<>()),
                new LazyCounterSet<>(), false);
        Assert.assertNotSame(new LazyCounterSet<>(), new LazyCounterSet<>());
        Assert.assertFalse(new LazyCounterSet<>().equals(Collections.emptySet()));
        TestUtils.assertListEquals(
                ListUtility.toList(new LazyCounterSet<>()),
                Collections.emptySet(), false);
        Assert.assertNotSame(new LazyCounterSet<>(), Collections.emptySet());
        
        //invalid
        Assert.assertFalse(sut.equals(""));
        Assert.assertFalse(sut.equals(ListUtility.emptyList()));
        Assert.assertFalse(sut.equals(new File(".")));
        Assert.assertFalse(sut.equals(null));
    }
    
    /**
     * JUnit test of iterator.
     *
     * @throws Exception When there is an exception.
     * @see LazyCounterSet#iterator()
     */
    @Test
    public void testIterator() throws Exception {
        Iterator<String> iterator;
        
        //standard
        iterator = sut.iterator();
        TestUtils.assertException(IllegalStateException.class, iterator::remove);
        while (iterator.hasNext()) {
            final String element = iterator.next();
            Assert.assertTrue(sut.contains(element));
            iterator.remove();
            counters.remove(element);
            sutValidator.run();
        }
        Assert.assertFalse(iterator.hasNext());
        TestUtils.assertException(NoSuchElementException.class, iterator::next);
        TestUtils.assertException(IllegalStateException.class, iterator::remove);
        Assert.assertTrue(sut.isEmpty());
        sutValidator.run();
        
        //equality
        Assert.assertNotSame(sut.iterator(), sut.iterator());
    }
    
}
