/*
 * File:    CounterSetTest.java
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
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import commons.lambda.stream.collector.ArrayCollectors;
import commons.lambda.stream.collector.MapCollectors;
import commons.math.MathUtility;
import commons.object.collection.ArrayUtility;
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
 * JUnit test of CounterSet.
 *
 * @see CounterSet
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CounterSet.class})
public class CounterSetTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CounterSetTest.class);
    
    
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
    private CounterSet<String> sut;
    
    /**
     * The map of elements and associated counters of the system under test.
     */
    private Map<String, Integer> counters;
    
    
    //Functions
    
    /**
     * Initializes the content of system under test.
     */
    private final Runnable sutInitializer = () -> {
        counters = ListUtility.shuffle(ListUtility.merge(elementSets[2], elementSets[0])).stream()
                .collect(MapCollectors.mapEachTo(MathUtility::randomInt));
        sut = new CounterSet<>(counters);
    };
    
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
     * Performs a test on the system under test.
     */
    private final Consumer<Runnable> sutTestRunner = (Runnable test) -> {
        sutInitializer.run();
        test.run();
        sutValidator.run();
    };
    
    /**
     * Performs a test on the system under test for a list of test elements.
     */
    private final BiConsumer<List<String>, Consumer<String>> sutTester = (List<String> testElements, Consumer<String> test) ->
            sutTestRunner.accept(() ->
                    ListUtility.selectN(testElements, Math.min(testElements.size(), 10)).forEach(test));
    
    /**
     * Generates a comparable counter map from a CounterSet.
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
     * @see CounterSet#CounterSet(Collection, boolean)
     * @see CounterSet#CounterSet(Collection)
     * @see CounterSet#CounterSet(Object[])
     * @see CounterSet#CounterSet(Map)
     * @see CounterSet#CounterSet(CounterSet)
     * @see CounterSet#CounterSet()
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testConstructors() throws Exception {
        final List<String> testList1 = elementSets[2];
        final List<String> testList2 = ListUtility.duplicateInOrder(testList1, 5);
        final Map<String, Integer> testMap = MapUtility.mapOf(
                testList1,
                IntStream.range(0, testList1.size()).mapToObj(i -> MathUtility.randomInt()).collect(Collectors.toList()));
        CounterSet<String> set1;
        CounterSet<String> set2;
        CounterSet<String> set3;
        CounterSet<String> set4;
        CounterSet<String> set5;
        CounterSet<String> set6;
        CounterSet<String> set7;
        CounterSet<String> set8;
        
        //distinct collection
        set1 = new CounterSet<>(testList1);
        Assert.assertNotNull(set1);
        TestUtils.assertListEquals(
                ListUtility.toList(set1),
                testList1, false);
        Assert.assertTrue(set1.stream().allMatch(element -> (set1.get(element) == 0)));
        
        //distinct collection, initialize
        set2 = new CounterSet<>(testList1, true);
        Assert.assertNotNull(set2);
        TestUtils.assertListEquals(
                ListUtility.toList(set2),
                testList1, false);
        Assert.assertTrue(set2.stream().allMatch(element -> (set2.get(element) == 1)));
        
        //collection
        set3 = new CounterSet<>(testList2);
        Assert.assertNotNull(set3);
        TestUtils.assertListEquals(
                ListUtility.toList(set3),
                testList1, false);
        Assert.assertTrue(set3.stream().allMatch(element -> (set3.get(element) == 0)));
        
        //collection, initialize
        set4 = new CounterSet<>(testList2, true);
        Assert.assertNotNull(set4);
        TestUtils.assertListEquals(
                ListUtility.toList(set4),
                testList1, false);
        Assert.assertTrue(set4.stream().allMatch(element -> (set4.get(element) == 5)));
        
        //set
        set5 = new CounterSet<>(ArrayUtility.toArray(testList1, String.class));
        Assert.assertNotNull(set5);
        TestUtils.assertListEquals(
                ListUtility.toList(set5),
                testList1, false);
        Assert.assertTrue(set5.stream().allMatch(element -> (set5.get(element) == 0)));
        
        //map
        set6 = new CounterSet<>(testMap);
        Assert.assertNotNull(set6);
        TestUtils.assertListEquals(
                ListUtility.toList(set6),
                testList1, false);
        Assert.assertTrue(set6.stream().allMatch(element -> (set6.get(element).intValue() == testMap.get(element).intValue())));
        
        //counter set
        set7 = new CounterSet<>(set6);
        Assert.assertNotNull(set7);
        TestUtils.assertListEquals(
                ListUtility.toList(set7),
                ListUtility.toList(set6), false);
        Assert.assertTrue(set7.stream().allMatch(element -> (set7.get(element).intValue() == set6.get(element).intValue())));
        
        //empty
        set8 = new CounterSet<>();
        Assert.assertNotNull(set8);
        Assert.assertTrue(set8.isEmpty());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new CounterSet<>((Collection<?>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new CounterSet<>((Map<?, Integer>) null));
        TestUtils.assertException(NullPointerException.class, () ->
                new CounterSet<>((CounterSet<?>) null));
    }
    
    /**
     * JUnit test of contains.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#contains(Object)
     */
    @Test
    public void testContains() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element ->
                Assert.assertTrue(
                        sut.contains(element)));
        
        //absent
        sutTester.accept(elementSets[3], element ->
                Assert.assertFalse(
                        sut.contains(element)));
        
        //invalid
        Assert.assertFalse(sut.contains(null));
    }
    
    /**
     * JUnit test of containsAll.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#containsAll(Collection)
     */
    @Test
    public void testContainsAll() throws Exception {
        //standard
        sutTestRunner.accept(() ->
                Assert.assertTrue(
                        sut.containsAll(elementSets[2])));
        
        //absent
        sutTestRunner.accept(() ->
                Assert.assertFalse(
                        sut.containsAll(elementSets[3])));
        
        //mixed
        sutTestRunner.accept(() ->
                Assert.assertFalse(
                        sut.containsAll(ListUtility.merge(elementSets[2], elementSets[1]))));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.containsAll(null));
    }
    
    /**
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#add(Object)
     */
    @Test
    public void testAdd() throws Exception {
        //standard
        sutTester.accept(elementSets[1], element -> {
            Assert.assertTrue(
                    sut.add(element));
            counters.put(element, 0);
        });
        
        //present
        sutTester.accept(elementSets[2], element ->
                Assert.assertFalse(
                        sut.add(element)));
        
        //invalid
        Assert.assertTrue(sut.add(null));
        Assert.assertTrue(sut.remove(null));
    }
    
    /**
     * JUnit test of addAll.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#addAll(Collection)
     */
    @Test
    public void testAddAll() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.addAll(elementSets[1]));
            elementSets[1].forEach(element ->
                    counters.put(element, 0));
        });
        
        //present
        sutTestRunner.accept(() ->
                Assert.assertFalse(
                        sut.addAll(elementSets[2])));
        
        //partial
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.addAll(ListUtility.merge(elementSets[2], elementSets[1])));
            elementSets[1].forEach(element ->
                    counters.putIfAbsent(element, 0));
        });
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.addAll(null));
    }
    
    /**
     * JUnit test of remove.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#remove(Object)
     */
    @Test
    public void testRemove() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertTrue(
                    sut.remove(element));
            counters.remove(element);
        });
        
        //absent
        sutTester.accept(elementSets[3], element ->
                Assert.assertFalse(
                        sut.remove(element)));
        
        //invalid
        Assert.assertTrue(sut.add(null));
        Assert.assertTrue(sut.remove(null));
    }
    
    /**
     * JUnit test of removeAll.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#removeAll(Collection)
     */
    @SuppressWarnings("SlowAbstractSetRemoveAll")
    @Test
    public void testRemoveAll() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.removeAll(elementSets[0]));
            elementSets[0].forEach(element ->
                    counters.remove(element));
        });
        
        //absent
        sutTestRunner.accept(() ->
                Assert.assertFalse(
                        sut.removeAll(elementSets[3])));
        
        //partial
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.removeAll(ListUtility.merge(elementSets[3], elementSets[0])));
            elementSets[0].forEach(element ->
                    counters.remove(element));
        });
        
        //all
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.removeAll(counters.keySet()));
            counters.clear();
        });
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.removeAll(null));
    }
    
    /**
     * JUnit test of removeIf.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#removeIf(Predicate)
     */
    @Test
    public void testRemoveIf() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.removeIf(e -> (elementSets[2].contains(e) && (e.charAt(0) > 'N'))));
            elementSets[2].stream().filter(e -> (e.charAt(0) > 'N')).forEach(element ->
                    counters.remove(element));
        });
        
        //none
        sutTestRunner.accept(() ->
                Assert.assertFalse(
                        sut.removeIf(e -> false)));
        
        //all
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.removeIf(e -> true));
            counters.clear();
        });
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.removeIf(null));
    }
    
    /**
     * JUnit test of retainAll.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#retainAll(Collection)
     */
    @Test
    public void testRetainAll() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.retainAll(elementSets[2]));
            elementSets[0].forEach(element ->
                    counters.remove(element));
        });
        
        //absent
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.retainAll(elementSets[1]));
            counters.clear();
        });
        
        //partial
        sutTestRunner.accept(() -> {
            Assert.assertTrue(
                    sut.retainAll(ListUtility.merge(elementSets[3], elementSets[0])));
            elementSets[2].forEach(element ->
                    counters.remove(element));
        });
        
        //all
        sutTestRunner.accept(() ->
                Assert.assertFalse(
                        sut.retainAll(counters.keySet())));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.retainAll(null));
    }
    
    /**
     * JUnit test of clear.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#clear()
     */
    @Test
    public void testClear() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            sut.clear();
            counters.clear();
        });
    }
    
    /**
     * JUnit test of resetAll.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#resetAll()
     */
    @Test
    public void testResetAll() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            sut.resetAll();
            counters.keySet().forEach(element ->
                    counters.replace(element, 0));
        });
    }
    
    /**
     * JUnit test of get.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#get(Object)
     */
    @Test
    public void testGet() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element ->
                Assert.assertEquals(counters.get(element), sut.get(element)));
        
        //absent
        sutTester.accept(elementSets[3], element ->
                Assert.assertNull(sut.get(element)));
        
        //invalid
        Assert.assertNull(sut.get(null));
    }
    
    /**
     * JUnit test of getAndSet.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#getAndSet(Object, int)
     */
    @Test
    public void testGetAndSet() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndSet(element, -1));
            Assert.assertEquals(-1, sut.get(element).intValue());
            counters.replace(element, -1);
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndSet(element, -1));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.getAndSet(null, 0));
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#set(Object, int)
     */
    @Test
    public void testSet() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.set(element, -1);
            Assert.assertEquals(-1, sut.get(element).intValue());
            counters.replace(element, -1);
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            sut.set(element, -1);
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        sut.set(null, 0);
    }
    
    /**
     * JUnit test of getAndReset.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#getAndReset(Object)
     */
    @Test
    public void testGetAndReset() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndReset(element));
            Assert.assertEquals(0, sut.get(element).intValue());
            counters.replace(element, 0);
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndReset(element));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.getAndReset(null));
    }
    
    /**
     * JUnit test of reset.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#reset(Object)
     */
    @Test
    public void testReset() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.reset(element);
            Assert.assertEquals(0, sut.get(element).intValue());
            counters.replace(element, 0);
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            sut.reset(element);
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        sut.reset(null);
    }
    
    /**
     * JUnit test of getAndStep.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#getAndStep(Object, int)
     */
    @Test
    public void testGetAndStep() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndStep(element, 10));
            Assert.assertEquals((counters.get(element) + 10), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 10));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndStep(element, -6));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.getAndStep(null, 0));
    }
    
    /**
     * JUnit test of stepAndGet.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#stepAndGet(Object, int)
     */
    @Test
    public void testStepAndGet() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals((counters.get(element) + 10), sut.stepAndGet(element, 10).intValue());
            Assert.assertEquals((counters.get(element) + 10), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 10));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.stepAndGet(element, -6));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.stepAndGet(null, 0));
    }
    
    /**
     * JUnit test of step.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#step(Object, int)
     */
    @Test
    public void testStep() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.step(element, 10);
            Assert.assertEquals((counters.get(element) + 10), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 10));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            sut.step(element, -6);
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        sut.step(null, 0);
    }
    
    /**
     * JUnit test of getAndIncrement.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#getAndIncrement(Object)
     */
    @Test
    public void testGetAndIncrement() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndIncrement(element));
            Assert.assertEquals((counters.get(element) + 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 1));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndIncrement(element));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.getAndIncrement(null));
    }
    
    /**
     * JUnit test of incrementAndGet.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#incrementAndGet(Object)
     */
    @Test
    public void testIncrementAndGet() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals((counters.get(element) + 1), sut.incrementAndGet(element).intValue());
            Assert.assertEquals((counters.get(element) + 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 1));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.incrementAndGet(element));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.incrementAndGet(null));
    }
    
    /**
     * JUnit test of increment.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#increment(Object)
     */
    @Test
    public void testIncrement() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.increment(element);
            Assert.assertEquals((counters.get(element) + 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) + 1));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            sut.increment(element);
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        sut.increment(null);
    }
    
    /**
     * JUnit test of getAndDecrement.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#getAndDecrement(Object)
     */
    @Test
    public void testGetAndDecrement() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndDecrement(element));
            Assert.assertEquals((counters.get(element) - 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) - 1));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndDecrement(element));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.getAndDecrement(null));
    }
    
    /**
     * JUnit test of decrementAndGet.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#decrementAndGet(Object)
     */
    @Test
    public void testDecrementAndGet() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals((counters.get(element) - 1), sut.decrementAndGet(element).intValue());
            Assert.assertEquals((counters.get(element) - 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) - 1));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.decrementAndGet(element));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.decrementAndGet(null));
    }
    
    /**
     * JUnit test of decrement.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#decrement(Object)
     */
    @Test
    public void testDecrement() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.decrement(element);
            Assert.assertEquals((counters.get(element) - 1), sut.get(element).intValue());
            counters.replace(element, (counters.get(element) - 1));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            sut.decrement(element);
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        sut.decrement(null);
    }
    
    /**
     * JUnit test of getAndUpdate.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#getAndUpdate(Object, IntUnaryOperator)
     */
    @Test
    public void testGetAndUpdate() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndUpdate(element, (i -> -i)));
            Assert.assertEquals(-counters.get(element), sut.get(element).intValue());
            counters.replace(element, -counters.get(element));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndUpdate(element, (i -> ((i + 2) * 2))));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.getAndUpdate(null, (i -> 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndUpdate("B", null));
        Assert.assertNull(sut.getAndUpdate(null, null));
    }
    
    /**
     * JUnit test of updateAndGet.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#updateAndGet(Object, IntUnaryOperator)
     */
    @Test
    public void testUpdateAndGet() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(-counters.get(element), sut.updateAndGet(element, (i -> -i)).intValue());
            Assert.assertEquals(-counters.get(element), sut.get(element).intValue());
            counters.replace(element, -counters.get(element));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.updateAndGet(element, (i -> ((i + 2) * 2))));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.updateAndGet(null, (i -> 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.updateAndGet("B", null));
        Assert.assertNull(sut.updateAndGet(null, null));
    }
    
    /**
     * JUnit test of update.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#update(Object, IntUnaryOperator)
     */
    @Test
    public void testUpdate() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.update(element, (i -> -i));
            Assert.assertEquals(-counters.get(element), sut.get(element).intValue());
            counters.replace(element, -counters.get(element));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            sut.update(element, (i -> ((i + 2) * 2)));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        sut.update(null, (i -> 0));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.update("B", null));
        sut.update(null, null);
    }
    
    /**
     * JUnit test of getAndAccumulate.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#getAndAccumulate(Object, int, IntBinaryOperator)
     */
    @Test
    public void testGetAndAccumulate() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals(counters.get(element), sut.getAndAccumulate(element, elementSets[2].indexOf(element), ((i, x) -> (-i + x))));
            Assert.assertEquals((-counters.get(element) + elementSets[2].indexOf(element)), sut.get(element).intValue());
            counters.replace(element, (-counters.get(element) + elementSets[2].indexOf(element)));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.getAndAccumulate(element, elementSets[3].indexOf(element), ((i, x) -> ((i + 2) * x))));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.getAndAccumulate(null, 0, ((i, x) -> 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndAccumulate("B", 0, null));
        Assert.assertNull(sut.getAndAccumulate(null, 0, null));
    }
    
    /**
     * JUnit test of accumulateAndGet.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#accumulateAndGet(Object, int, IntBinaryOperator)
     */
    @Test
    public void testAccumulateAndGet() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            Assert.assertEquals((-counters.get(element) + elementSets[2].indexOf(element)), sut.accumulateAndGet(element, elementSets[2].indexOf(element), ((i, x) -> (-i + x))).intValue());
            Assert.assertEquals((-counters.get(element) + elementSets[2].indexOf(element)), sut.get(element).intValue());
            counters.replace(element, (-counters.get(element) + elementSets[2].indexOf(element)));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            Assert.assertNull(sut.accumulateAndGet(element, elementSets[3].indexOf(element), ((i, x) -> ((i + 2) * x))));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        Assert.assertNull(sut.accumulateAndGet(null, 0, ((i, x) -> 0)));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.accumulateAndGet("B", 0, null));
        Assert.assertNull(sut.accumulateAndGet(null, 0, null));
    }
    
    /**
     * JUnit test of accumulate.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#accumulate(Object, int, IntBinaryOperator)
     */
    @Test
    public void testAccumulate() throws Exception {
        //standard
        sutTester.accept(elementSets[2], element -> {
            Assert.assertEquals(counters.get(element), sut.get(element));
            sut.accumulate(element, elementSets[2].indexOf(element), ((i, x) -> (-i + x)));
            Assert.assertEquals((-counters.get(element) + elementSets[2].indexOf(element)), sut.get(element).intValue());
            counters.replace(element, (-counters.get(element) + elementSets[2].indexOf(element)));
        });
        
        //absent
        sutTester.accept(elementSets[3], element -> {
            Assert.assertNull(sut.get(element));
            sut.accumulate(element, elementSets[3].indexOf(element), ((i, x) -> ((i + 2) * x)));
            Assert.assertNull(sut.get(element));
        });
        
        //invalid
        sut.accumulate(null, 0, ((i, x) -> 0));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.accumulate("B", 0, null));
        sut.accumulate(null, 0, null);
    }
    
    /**
     * JUnit test of getAndModify.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#getAndModify(Object, Function)
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
        sutTestRunner.accept(() -> {
            counters.keySet().forEach(element -> {
                sut.set(element, 1);
                counters.replace(element, 1);
            });
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
        });
        
        //invalid
        Assert.assertNull(sut.getAndModify(null, AtomicInteger::get));
        TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index 50 out of bounds for length {}", sut.size()), () ->
                sut.getAndModify("B", (e -> ListUtility.toList(counters.values()).get(50))));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndModify("B", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.getAndModify(null, null));
    }
    
    /**
     * JUnit test of modifyAndGet.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#modifyAndGet(Object, Function)
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
        sutTestRunner.accept(() -> {
            counters.keySet().forEach(element -> {
                sut.set(element, 1);
                counters.replace(element, 1);
            });
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
        });
        
        //invalid
        Assert.assertNull(sut.modifyAndGet(null, AtomicInteger::get));
        TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index 50 out of bounds for length {}", sut.size()), () ->
                sut.modifyAndGet("B", (e -> ListUtility.toList(counters.values()).get(50))));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.modifyAndGet("B", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.modifyAndGet(null, null));
    }
    
    /**
     * JUnit test of modify.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#modify(Object, Function)
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
        sutTestRunner.accept(() -> {
            counters.keySet().forEach(element -> {
                sut.set(element, 1);
                counters.replace(element, 1);
            });
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
        });
        
        //invalid
        sut.modify(null, AtomicInteger::get);
        TestUtils.assertException(IndexOutOfBoundsException.class, StringUtility.format("Index 50 out of bounds for length {}", sut.size()), () ->
                sut.modify("B", (e -> ListUtility.toList(counters.values()).get(50))));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.modify("B", null));
        TestUtils.assertException(NullPointerException.class, () ->
                sut.modify(null, null));
    }
    
    /**
     * JUnit test of size.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#size()
     */
    @Test
    public void testSize() throws Exception {
        //standard
        Assert.assertEquals(counters.size(), sut.size());
        
        //empty
        sut.clear();
        Assert.assertEquals(0, sut.size());
        counters.clear();
    }
    
    /**
     * JUnit test of isEmpty.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#isEmpty()
     */
    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertFalse(sut.isEmpty());
        
        //empty
        sut.clear();
        Assert.assertTrue(sut.isEmpty());
        counters.clear();
    }
    
    /**
     * JUnit test of clone.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#clone()
     */
    @Test
    public void testClone() throws Exception {
        CounterSet<String> clone;
        
        //standard
        clone = sut.clone();
        Assert.assertNotNull(clone);
        Assert.assertTrue(clone instanceof CounterSet);
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
     * @see CounterSet#equals(Object)
     */
    @SuppressWarnings({"SimplifiableAssertion", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void testEquals() throws Exception {
        Set<String> other;
        
        //standard
        other = new CounterSet<>(counters);
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
        Assert.assertTrue(new CounterSet<>().equals(new CounterSet<>()));
        TestUtils.assertListEquals(
                ListUtility.toList(new CounterSet<>()),
                new CounterSet<>(), false);
        Assert.assertNotSame(new CounterSet<>(), new CounterSet<>());
        Assert.assertFalse(new CounterSet<>().equals(Collections.emptySet()));
        TestUtils.assertListEquals(
                ListUtility.toList(new CounterSet<>()),
                Collections.emptySet(), false);
        Assert.assertNotSame(new CounterSet<>(), Collections.emptySet());
        
        //invalid
        Assert.assertFalse(sut.equals(""));
        Assert.assertFalse(sut.equals(ListUtility.emptyList()));
        Assert.assertFalse(sut.equals(new File(".")));
        Assert.assertFalse(sut.equals(null));
    }
    
    /**
     * JUnit test of toArray.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#toArray(IntFunction)
     * @see CounterSet#toArray(Object[])
     * @see CounterSet#toArray()
     */
    @Test
    public void testToArray() throws Exception {
        Object[] array;
        
        //standard
        array = sut.toArray();
        Assert.assertNotNull(array);
        TestUtils.assertArrayEquals(array, counters.keySet(), false);
        
        //array
        array = sut.toArray(ArrayUtility.create(String.class));
        Assert.assertNotNull(array);
        TestUtils.assertArrayEquals(array, counters.keySet(), false);
        
        //generator
        array = sut.toArray(String[]::new);
        Assert.assertNotNull(array);
        TestUtils.assertArrayEquals(array, counters.keySet(), false);
        
        //empty
        array = new CounterSet<String>().toArray();
        Assert.assertNotNull(array);
        Assert.assertEquals(0, array.length);
    }
    
    /**
     * JUnit test of forEach.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#forEach(Consumer)
     */
    @Test
    public void testForEach() throws Exception {
        //standard
        sutTestRunner.accept(() -> {
            sut.forEach(element ->
                    sut.update(element, (i -> -i)));
            counters.forEach((element, counter) ->
                    counters.replace(element, -counter));
        });
        
        //modification
        sutTestRunner.accept(() ->
                TestUtils.assertNoException(() ->
                        sut.forEach(element -> {
                            sut.remove(element);
                            sut.add(element);
                            sut.set(element, counters.get(element));
                        })));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                sut.forEach(null));
    }
    
    /**
     * JUnit test of iterator.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#iterator()
     */
    @Test
    public void testIterator() throws Exception {
        final AtomicReference<Iterator<String>> iterator = new AtomicReference<>(null);
        
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
                    Assert.assertTrue(counters.containsKey(iterator.get().next()));
                }));
        
        //for each
        iteratorTestRunner.accept(() ->
                iterator.get().forEachRemaining(element ->
                        Assert.assertTrue(counters.containsKey(element))));
        
        //remove
        iteratorTestRunner.accept(() -> {
            TestUtils.assertException(IllegalStateException.class, () ->
                    iterator.get().remove());
            IntStream.range(0, sut.size()).forEach(i -> {
                final String element = iterator.get().next();
                iterator.get().remove();
                counters.remove(element);
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
     * @see CounterSet#spliterator()
     */
    @Test
    public void testSpliterator() throws Exception {
        final AtomicReference<Spliterator<String>> spliterator1 = new AtomicReference<>(null);
        final AtomicReference<Spliterator<String>> spliterator2 = new AtomicReference<>(null);
        
        final Runnable spliteratorInitializer = () -> {
            spliterator1.set(sut.spliterator());
            Assert.assertNotNull(spliterator1.get());
            spliterator2.set(spliterator1.get().trySplit());
            Assert.assertNotNull(spliterator2.get());
        };
        
        //standard
        sutTestRunner.accept(() -> {
            spliteratorInitializer.run();
            IntStream.range(0, sut.size()).forEach(i ->
                    Assert.assertTrue(
                            Stream.of(spliterator1, spliterator2).map(AtomicReference::get).anyMatch(spliterator ->
                                    spliterator.tryAdvance(element ->
                                            Assert.assertTrue(counters.containsKey(element))))));
            Assert.assertFalse(spliterator1.get().tryAdvance(Objects::nonNull));
            Assert.assertFalse(spliterator2.get().tryAdvance(Objects::nonNull));
        });
        
        //for each
        sutTestRunner.accept(() -> {
            spliteratorInitializer.run();
            Stream.of(spliterator1, spliterator2).map(AtomicReference::get).forEach(spliterator -> {
                spliterator.forEachRemaining(element ->
                        Assert.assertTrue(counters.containsKey(element)));
                Assert.assertFalse(spliterator.tryAdvance(Objects::nonNull));
            });
        });
        
        //equality
        Assert.assertNotSame(sut.spliterator(), sut.spliterator());
    }
    
    /**
     * JUnit test of stream.
     *
     * @throws Exception When there is an exception.
     * @see CounterSet#stream()
     */
    @Test
    public void testStream() throws Exception {
        final AtomicReference<Stream<String>> stream = new AtomicReference<>(null);
        
        //standard
        sutTestRunner.accept(() -> {
            stream.set(sut.stream());
            TestUtils.assertListEquals(
                    stream.get().collect(Collectors.toList()),
                    counters.keySet(), false);
        });
        
        //equality
        Assert.assertNotSame(sut.stream(), sut.stream());
    }
    
}
