/*
 * File:    MappersTest.java
 * Package: commons.lambda.stream.mapper
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.mapper;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import commons.lambda.function.checked.CheckedConsumer;
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
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
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
     * JUnit test of modify.
     *
     * @throws Exception When there is an exception.
     * @see Mappers#modify(CheckedConsumer)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCollect() throws Exception {
        final List<String> testElements1 = ListUtility.clone((List<String>) testStreamElements[0]);
        final List<Integer> testElements2 = ListUtility.clone((List<Integer>) testStreamElements[1]);
        final List<Double> testElements3 = ListUtility.clone((List<Double>) testStreamElements[2]);
        final List<StringBuilder> testMapped1 = testElements1.stream().map(StringBuilder::new).collect(Collectors.toList());
        final List<AtomicInteger> testMapped2 = testElements2.stream().map(AtomicInteger::new).collect(Collectors.toList());
        final List<List<Double>> testMapped3 = testElements3.stream().map(ListUtility::listOf).collect(Collectors.toList());
        List<StringBuilder> mapped1;
        List<AtomicInteger> mapped2;
        List<List<Double>> mapped3;
        List<List<Double>> mapped4;
        
        //standard
        mapped1 = testMapped1.stream().map(Mappers.modify(e -> e.append('s'))).collect(Collectors.toList());
        Assert.assertNotNull(mapped1);
        TestUtils.assertListEquals(mapped1, testMapped1);
        TestUtils.assertListEquals(
                mapped1.stream().map(StringBuilder::toString).collect(Collectors.toList()),
                testElements1.stream().map(e -> (e + 's')).collect(Collectors.toList()));
        mapped2 = testMapped2.stream().map(Mappers.modify(AtomicInteger::incrementAndGet)).collect(Collectors.toList());
        Assert.assertNotNull(mapped2);
        TestUtils.assertListEquals(mapped2, testMapped2);
        TestUtils.assertListEquals(
                mapped2.stream().map(AtomicInteger::get).collect(Collectors.toList()),
                testElements2.stream().map(e -> (e + 1)).collect(Collectors.toList()));
        mapped3 = testMapped3.stream().map(Mappers.modify(e -> e.add(0.0))).collect(Collectors.toList());
        Assert.assertNotNull(mapped3);
        IntStream.range(0, mapped3.size()).forEach(i -> {
            TestUtils.assertListEquals(mapped3.get(i), testMapped3.get(i));
            TestUtils.assertListEquals(
                    mapped3.get(i),
                    List.of(testElements3.get(i), 0.0));
        });
        testMapped2.forEach(AtomicInteger::decrementAndGet);
        testMapped1.forEach(e -> e.setLength(e.length() - 1));
        testMapped3.forEach(e -> e.remove(1));
        
        //null
        List.of(testElements1, testElements2, testElements3).forEach(testElements ->
                testElements.add(2, null));
        List.of(testMapped1, testMapped2, testMapped3).forEach(testMapped ->
                testMapped.add(2, null));
        mapped1 = testMapped1.stream().map(Mappers.modify(e -> e.append('s'))).collect(Collectors.toList());
        Assert.assertNotNull(mapped1);
        TestUtils.assertListEquals(mapped1, testMapped1);
        TestUtils.assertListEquals(
                mapped1.stream().map(e -> ((e != null) ? e.toString() : null)).collect(Collectors.toList()),
                testElements1.stream().map(e -> ((e != null) ? (e + 's') : null)).collect(Collectors.toList()));
        mapped2 = testMapped2.stream().map(Mappers.modify(AtomicInteger::incrementAndGet)).collect(Collectors.toList());
        Assert.assertNotNull(mapped2);
        TestUtils.assertListEquals(mapped2, testMapped2);
        TestUtils.assertListEquals(
                mapped2.stream().map(e -> ((e != null) ? e.get() : null)).collect(Collectors.toList()),
                testElements2.stream().map(e -> ((e != null) ? (e + 1) : null)).collect(Collectors.toList()));
        mapped4 = testMapped3.stream().map(Mappers.modify(e -> e.add(0.0))).collect(Collectors.toList());
        Assert.assertNotNull(mapped4);
        IntStream.range(0, mapped4.size()).forEach(i -> {
            if (mapped4.get(i) == null) {
                Assert.assertNull(testMapped3.get(i));
                Assert.assertNull(testElements3.get(i));
            } else {
                TestUtils.assertListEquals(mapped4.get(i), testMapped3.get(i));
                TestUtils.assertListEquals(
                        mapped4.get(i),
                        List.of(testElements3.get(i), 0.0));
            }
        });
        List.of(testElements1, testElements2, testElements3).forEach(testElements ->
                testElements.removeIf(Objects::isNull));
        List.of(testMapped1, testMapped2, testMapped3).forEach(testMapped ->
                testMapped.removeIf(Objects::isNull));
        testMapped2.forEach(AtomicInteger::decrementAndGet);
        testMapped1.forEach(e -> e.setLength(e.length() - 1));
        testMapped3.forEach(e -> e.remove(1));
        
        //uniqueness
        Assert.assertNotSame(Mappers.modify(Objects::requireNonNull), Mappers.modify(Objects::requireNonNull));
        
        //invalid
        TestUtils.assertNoException(() ->
                Mappers.modify(null));
        TestUtils.assertException(NullPointerException.class, () ->
                Stream.of(1, 2, 3).map(Mappers.modify(null)).collect(Collectors.toList()));
    }
    
}
