/*
 * File:    MappersTest.java
 * Package: commons.lambda.stream.mapper
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import commons.lambda.function.checked.CheckedConsumer;
import commons.lambda.stream.collector.ListCollectors;
import commons.object.collection.ListUtility;
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
 * JUnit test of Mappers.
 *
 * @see Mappers
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Mappers.class})
public class MappersTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MappersTest.class);
    
    
    //Static Fields
    
    /**
     * A set of lists containing the elements of the streams to use for testing.
     */
    private static final List<?>[] testStreamElements = new List<?>[] {
            List.of("cat", "dog", "lizard", "rat", "bird", "horse"),
            List.of(1, 8, 9733, 5, -77, 0, 156),
            List.of(4.3302, 9.0644, 66743.016, 5.9070466, -60.9, 0.13144, 751.641)};
    
    
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
     * JUnit test of perform.
     *
     * @throws Exception When there is an exception.
     * @see Mappers#perform(Object, Consumer)
     */
    @Test
    public void testPerform() throws Exception {
        final List<String> testElements1 = ListUtility.clone((List<String>) testStreamElements[0]);
        final List<Integer> testElements2 = ListUtility.clone((List<Integer>) testStreamElements[1]);
        final List<Double> testElements3 = ListUtility.clone((List<Double>) testStreamElements[2]);
        final List<StringBuilder> testMapped1 = testElements1.stream().map(StringBuilder::new).collect(Collectors.toList());
        final List<AtomicInteger> testMapped2 = testElements2.stream().map(AtomicInteger::new).collect(Collectors.toList());
        final List<List<Double>> testMapped3 = testElements3.stream().map(ListUtility::listOf).collect(Collectors.toList());
        
        //standard
        IntStream.range(0, testMapped1.size()).forEach(i ->
                Assert.assertEquals((testElements1.get(i) + 's'), Mappers.perform(testMapped1.get(i), (e -> e.append('s'))).toString()));
        IntStream.range(0, testMapped2.size()).forEach(i ->
                Assert.assertEquals((testElements2.get(i) + 1), Mappers.perform(testMapped2.get(i), AtomicInteger::incrementAndGet).get()));
        IntStream.range(0, testMapped3.size()).forEach(i ->
                TestUtils.assertListEquals(
                        Mappers.perform(testMapped3.get(i), (e -> e.add(0.0))),
                        List.of(testElements3.get(i), 0.0)));
        
        //error
        TestUtils.assertException(NullPointerException.class, () ->
                Mappers.perform(null, (StringBuilder e) -> e.append('s')));
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index 10 out of bounds for length 0", () ->
                Mappers.perform(ListUtility.emptyList(), (List<AtomicInteger> e) -> e.get(10).incrementAndGet()));
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                Mappers.perform(ListUtility.unmodifiableList(), (List<Double> e) -> e.add(0.0)));
        
        //invalid
        TestUtils.assertNoException(() ->
                Mappers.perform(null, (e -> Assert.assertTrue(true))));
        TestUtils.assertException(NullPointerException.class, () ->
                Mappers.perform(null, Objects::requireNonNull));
        TestUtils.assertException(NullPointerException.class, () ->
                Mappers.perform(new Object(), null));
        TestUtils.assertException(NullPointerException.class, () ->
                Mappers.perform(null, null));
    }
    
    /**
     * JUnit test of tryPerform.
     *
     * @throws Exception When there is an exception.
     * @see Mappers#tryPerform(Object, CheckedConsumer)
     */
    @Test
    public void testTryPerform() throws Exception {
        final List<String> testElements1 = ListUtility.clone((List<String>) testStreamElements[0]);
        final List<Integer> testElements2 = ListUtility.clone((List<Integer>) testStreamElements[1]);
        final List<Double> testElements3 = ListUtility.clone((List<Double>) testStreamElements[2]);
        final List<StringBuilder> testMapped1 = testElements1.stream().map(StringBuilder::new).collect(Collectors.toList());
        final List<AtomicInteger> testMapped2 = testElements2.stream().map(AtomicInteger::new).collect(Collectors.toList());
        final List<List<Double>> testMapped3 = testElements3.stream().map(ListUtility::listOf).collect(Collectors.toList());
        
        //standard
        IntStream.range(0, testMapped1.size()).forEach(i ->
                Assert.assertEquals((testElements1.get(i) + 's'), Mappers.tryPerform(testMapped1.get(i), (e -> e.append('s'))).toString()));
        IntStream.range(0, testMapped2.size()).forEach(i ->
                Assert.assertEquals((testElements2.get(i) + 1), Mappers.tryPerform(testMapped2.get(i), AtomicInteger::incrementAndGet).get()));
        IntStream.range(0, testMapped3.size()).forEach(i ->
                TestUtils.assertListEquals(
                        Mappers.tryPerform(testMapped3.get(i), (e -> e.add(0.0))),
                        List.of(testElements3.get(i), 0.0)));
        
        //error
        Assert.assertNull(Mappers.tryPerform(null, (StringBuilder e) -> e.append('s')));
        TestUtils.assertListEquals(
                Mappers.tryPerform(ListUtility.emptyList(), (List<AtomicInteger> e) -> e.get(10).incrementAndGet()),
                ListUtility.emptyList());
        TestUtils.assertListEquals(
                Mappers.tryPerform(ListUtility.unmodifiableList(), (List<Double> e) -> e.add(0.0)),
                ListUtility.unmodifiableList());
        
        //invalid
        TestUtils.assertNoException(() ->
                Mappers.tryPerform(null, (e -> Assert.assertTrue(true))));
        TestUtils.assertNoException(() ->
                Mappers.tryPerform(null, Objects::requireNonNull));
        TestUtils.assertException(NullPointerException.class, () ->
                Mappers.tryPerform(new Object(), null));
        TestUtils.assertException(NullPointerException.class, () ->
                Mappers.tryPerform(null, null));
    }
    
    /**
     * JUnit test of forEach.
     *
     * @throws Exception When there is an exception.
     * @see Mappers#forEach(Consumer)
     */
    @Test
    public void testForEach() throws Exception {
        final List<String> testElements1 = ListUtility.clone((List<String>) testStreamElements[0]);
        final List<Integer> testElements2 = ListUtility.clone((List<Integer>) testStreamElements[1]);
        final List<Double> testElements3 = ListUtility.clone((List<Double>) testStreamElements[2]);
        final List<StringBuilder> testMapped1 = testElements1.stream().map(StringBuilder::new).collect(Collectors.toList());
        final List<AtomicInteger> testMapped2 = testElements2.stream().map(AtomicInteger::new).collect(Collectors.toList());
        final List<List<Double>> testMapped3 = testElements3.stream().map(ListUtility::listOf).collect(Collectors.toList());
        final List<StringBuilder> mapped1 = ListUtility.emptyList();
        final List<AtomicInteger> mapped2 = ListUtility.emptyList();
        final List<List<Double>> mapped3 = ListUtility.emptyList();
        final List<List<?>> testElementSets = List.of(testElements1, testElements2, testElements3);
        final List<List<?>> testMappedSets = List.of(testMapped1, testMapped2, testMapped3);
        final List<List<?>> mappedSets = List.of(mapped1, mapped2, mapped3);
        
        //standard
        testMapped1.stream().map(Mappers.forEach(e ->
                        e.append('s')))
                .collect(ListCollectors.addTo(mapped1));
        Assert.assertNotNull(mapped1);
        TestUtils.assertListEquals(mapped1, testMapped1);
        TestUtils.assertListEquals(
                mapped1.stream().map(StringBuilder::toString).collect(Collectors.toList()),
                testElements1.stream().map(e -> (e + 's')).collect(Collectors.toList()));
        testMapped2.stream().map(Mappers.forEach(AtomicInteger::incrementAndGet))
                .collect(ListCollectors.addTo(mapped2));
        Assert.assertNotNull(mapped2);
        TestUtils.assertListEquals(mapped2, testMapped2);
        TestUtils.assertListEquals(
                mapped2.stream().map(AtomicInteger::get).collect(Collectors.toList()),
                testElements2.stream().map(e -> (e + 1)).collect(Collectors.toList()));
        testMapped3.stream().map(Mappers.forEach(e ->
                        e.add(0.0)))
                .collect(ListCollectors.addTo(mapped3));
        Assert.assertNotNull(mapped3);
        IntStream.range(0, mapped3.size()).forEach(i -> {
            TestUtils.assertListEquals(mapped3.get(i), testMapped3.get(i));
            TestUtils.assertListEquals(
                    mapped3.get(i),
                    List.of(testElements3.get(i), 0.0));
        });
        testMapped1.forEach(e -> e.setLength(e.length() - 1));
        testMapped2.forEach(AtomicInteger::decrementAndGet);
        testMapped3.forEach(e -> e.remove(1));
        mappedSets.forEach(List::clear);
        
        //error
        Stream.concat(testElementSets.stream(), testMappedSets.stream()).forEach(testSet ->
                testSet.add(2, null));
        TestUtils.assertException(NullPointerException.class, () ->
                testMapped1.stream().map(Mappers.forEach(e ->
                                e.append('s')))
                        .collect(ListCollectors.addTo(mapped1)));
        Assert.assertNotNull(mapped1);
        Assert.assertTrue(mapped1.isEmpty());
        TestUtils.assertException(IndexOutOfBoundsException.class, "Index 10 out of bounds for length 8", () ->
                testMapped2.stream().map(Mappers.forEach(e ->
                                Optional.ofNullable(e).orElseGet(() -> testMapped2.get(10)).incrementAndGet()))
                        .collect(ListCollectors.addTo(mapped2)));
        Assert.assertNotNull(mapped2);
        Assert.assertTrue(mapped2.isEmpty());
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                testMapped3.stream().map(Mappers.forEach(e ->
                                Optional.ofNullable(e).orElse(ListUtility.unmodifiableList()).add(0.0)))
                        .collect(ListCollectors.addTo(mapped3)));
        Assert.assertNotNull(mapped3);
        Assert.assertTrue(mapped3.isEmpty());
        Stream.concat(testElementSets.stream(), testMappedSets.stream()).forEach(ListUtility::removeNull);
        testMapped1.subList(0, 2).forEach(e -> e.setLength(e.length() - 1));
        testMapped2.subList(0, 2).forEach(AtomicInteger::decrementAndGet);
        testMapped3.subList(0, 2).forEach(e -> e.remove(1));
        mappedSets.forEach(List::clear);
        
        //uniqueness
        Assert.assertNotSame(
                Mappers.forEach(Objects::requireNonNull),
                Mappers.forEach(Objects::requireNonNull));
        
        //invalid
        TestUtils.assertNoException(() ->
                Mappers.forEach(null));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).map(Mappers.forEach(null)).collect(Collectors.toList()));
    }
    
    /**
     * JUnit test of tryForEach.
     *
     * @throws Exception When there is an exception.
     * @see Mappers#tryForEach(CheckedConsumer)
     */
    @Test
    public void testTryForEach() throws Exception {
        final List<String> testElements1 = ListUtility.clone((List<String>) testStreamElements[0]);
        final List<Integer> testElements2 = ListUtility.clone((List<Integer>) testStreamElements[1]);
        final List<Double> testElements3 = ListUtility.clone((List<Double>) testStreamElements[2]);
        final List<StringBuilder> testMapped1 = testElements1.stream().map(StringBuilder::new).collect(Collectors.toList());
        final List<AtomicInteger> testMapped2 = testElements2.stream().map(AtomicInteger::new).collect(Collectors.toList());
        final List<List<Double>> testMapped3 = testElements3.stream().map(ListUtility::listOf).collect(Collectors.toList());
        final List<StringBuilder> mapped1 = ListUtility.emptyList();
        final List<AtomicInteger> mapped2 = ListUtility.emptyList();
        final List<List<Double>> mapped3 = ListUtility.emptyList();
        final List<List<?>> testElementSets = List.of(testElements1, testElements2, testElements3);
        final List<List<?>> testMappedSets = List.of(testMapped1, testMapped2, testMapped3);
        final List<List<?>> mappedSets = List.of(mapped1, mapped2, mapped3);
        
        //standard
        testMapped1.stream().map(Mappers.tryForEach(e ->
                        e.append('s')))
                .collect(ListCollectors.addTo(mapped1));
        Assert.assertNotNull(mapped1);
        TestUtils.assertListEquals(mapped1, testMapped1);
        TestUtils.assertListEquals(
                mapped1.stream().map(StringBuilder::toString).collect(Collectors.toList()),
                testElements1.stream().map(e -> (e + 's')).collect(Collectors.toList()));
        testMapped2.stream().map(Mappers.tryForEach(AtomicInteger::incrementAndGet))
                .collect(ListCollectors.addTo(mapped2));
        Assert.assertNotNull(mapped2);
        TestUtils.assertListEquals(mapped2, testMapped2);
        TestUtils.assertListEquals(
                mapped2.stream().map(AtomicInteger::get).collect(Collectors.toList()),
                testElements2.stream().map(e -> (e + 1)).collect(Collectors.toList()));
        testMapped3.stream().map(Mappers.tryForEach(e ->
                        e.add(0.0)))
                .collect(ListCollectors.addTo(mapped3));
        Assert.assertNotNull(mapped3);
        IntStream.range(0, mapped3.size()).forEach(i -> {
            TestUtils.assertListEquals(mapped3.get(i), testMapped3.get(i));
            TestUtils.assertListEquals(
                    mapped3.get(i),
                    List.of(testElements3.get(i), 0.0));
        });
        testMapped1.forEach(e -> e.setLength(e.length() - 1));
        testMapped2.forEach(AtomicInteger::decrementAndGet);
        testMapped3.forEach(e -> e.remove(1));
        mappedSets.forEach(List::clear);
        
        //error
        Stream.concat(testElementSets.stream(), testMappedSets.stream()).forEach(testSet ->
                testSet.add(2, null));
        testMapped1.stream().map(Mappers.tryForEach(e ->
                        e.append('s')))
                .collect(ListCollectors.addTo(mapped1));
        Assert.assertNotNull(mapped1);
        TestUtils.assertListEquals(mapped1, testMapped1);
        TestUtils.assertListEquals(
                mapped1.stream().map(e -> Optional.ofNullable(e).map(StringBuilder::toString).orElse(null)).collect(Collectors.toList()),
                testElements1.stream().map(e -> Optional.ofNullable(e).map(e2 -> (e2 + 's')).orElse(null)).collect(Collectors.toList()));
        testMapped2.stream().map(Mappers.tryForEach(e ->
                        Optional.ofNullable(e).orElseGet(() -> testMapped2.get(10)).incrementAndGet()))
                .collect(ListCollectors.addTo(mapped2));
        Assert.assertNotNull(mapped2);
        TestUtils.assertListEquals(mapped2, testMapped2);
        TestUtils.assertListEquals(
                mapped2.stream().map(e -> Optional.ofNullable(e).map(AtomicInteger::get).orElse(null)).collect(Collectors.toList()),
                testElements2.stream().map(e -> Optional.ofNullable(e).map(e2 -> (e2 + 1)).orElse(null)).collect(Collectors.toList()));
        testMapped3.stream().map(Mappers.tryForEach(e ->
                        Optional.ofNullable(e).orElse(ListUtility.unmodifiableList()).add(0.0)))
                .collect(ListCollectors.addTo(mapped3));
        Assert.assertNotNull(mapped3);
        IntStream.range(0, mapped3.size()).forEach(i ->
                Optional.ofNullable(mapped3.get(i)).ifPresentOrElse(e -> {
                    TestUtils.assertListEquals(mapped3.get(i), testMapped3.get(i));
                    TestUtils.assertListEquals(
                            mapped3.get(i),
                            List.of(testElements3.get(i), 0.0));
                }, () -> {
                    Assert.assertNull(testMapped3.get(i));
                    Assert.assertNull(testElements3.get(i));
                }));
        Stream.concat(testElementSets.stream(), testMappedSets.stream()).forEach(ListUtility::removeNull);
        testMapped1.forEach(e -> e.setLength(e.length() - 1));
        testMapped2.forEach(AtomicInteger::decrementAndGet);
        testMapped3.forEach(e -> e.remove(1));
        mappedSets.forEach(List::clear);
        
        //uniqueness
        Assert.assertNotSame(
                Mappers.tryForEach(Objects::requireNonNull),
                Mappers.tryForEach(Objects::requireNonNull));
        
        //invalid
        TestUtils.assertNoException(() ->
                Mappers.tryForEach(null));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).map(Mappers.tryForEach(null)).collect(Collectors.toList()));
    }
    
}
