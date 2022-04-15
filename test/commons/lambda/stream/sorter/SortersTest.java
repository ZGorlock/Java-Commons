/*
 * File:    SortersTest.java
 * Package: commons.lambda.stream.sorters
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.sorter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
 * JUnit test of Sorters.
 *
 * @see Sorters
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Sorters.class})
public class SortersTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SortersTest.class);
    
    
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
     * JUnit test of sort.
     *
     * @throws Exception When there is an exception.
     * @see Sorters#sort()
     */
    @Test
    public void testSort() throws Exception {
        final List<String> testElements1 = ListUtility.clone((List<String>) testStreamElements[0]);
        final List<Integer> testElements2 = ListUtility.clone((List<Integer>) testStreamElements[1]);
        final List<Double> testElements3 = ListUtility.clone((List<Double>) testStreamElements[2]);
        final List<String> testSorted1 = testElements1.stream().sorted().collect(Collectors.toList());
        final List<Integer> testSorted2 = testElements2.stream().sorted().collect(Collectors.toList());
        final List<Double> testSorted3 = testElements3.stream().sorted().collect(Collectors.toList());
        final List<List<?>> testElementSets = List.of(testElements1, testElements2, testElements3);
        final List<List<?>> testSortedSets = List.of(testSorted1, testSorted2, testSorted3);
        List<String> sorted1;
        List<Integer> sorted2;
        List<Double> sorted3;
        
        //standard
        sorted1 = testElements1.stream().sorted(Sorters.sort()).collect(Collectors.toList());
        Assert.assertNotNull(sorted1);
        TestUtils.assertListEquals(sorted1, testSorted1);
        sorted2 = testElements2.stream().sorted(Sorters.sort()).collect(Collectors.toList());
        Assert.assertNotNull(sorted2);
        TestUtils.assertListEquals(sorted2, testSorted2);
        sorted3 = testElements3.stream().sorted(Sorters.sort()).collect(Collectors.toList());
        Assert.assertNotNull(sorted3);
        TestUtils.assertListEquals(sorted3, testSorted3);
        
        //null
        testElementSets.forEach(testSet -> testSet.add(2, null));
        testSortedSets.forEach(testSet -> testSet.add(0, null));
        sorted1 = testElements1.stream().sorted(Sorters.sort()).collect(Collectors.toList());
        Assert.assertNotNull(sorted1);
        TestUtils.assertListEquals(sorted1, testSorted1);
        sorted2 = testElements2.stream().sorted(Sorters.sort()).collect(Collectors.toList());
        Assert.assertNotNull(sorted2);
        TestUtils.assertListEquals(sorted2, testSorted2);
        sorted3 = testElements3.stream().sorted(Sorters.sort()).collect(Collectors.toList());
        Assert.assertNotNull(sorted3);
        TestUtils.assertListEquals(sorted3, testSorted3);
        Stream.concat(testElementSets.stream(), testSortedSets.stream()).forEach(testSet ->
                testSet.removeIf(Objects::isNull));
        
        //uniqueness
        Assert.assertSame(
                Sorters.sort(),
                Sorters.sort());
    }
    
    /**
     * JUnit test of preserve.
     *
     * @throws Exception When there is an exception.
     * @see Sorters#preserve()
     */
    @Test
    public void testPreserve() throws Exception {
        final List<String> testElements1 = ListUtility.clone((List<String>) testStreamElements[0]);
        final List<Integer> testElements2 = ListUtility.clone((List<Integer>) testStreamElements[1]);
        final List<Double> testElements3 = ListUtility.clone((List<Double>) testStreamElements[2]);
        final List<String> testSorted1 = ListUtility.clone(testElements1);
        final List<Integer> testSorted2 = ListUtility.clone(testElements2);
        final List<Double> testSorted3 = ListUtility.clone(testElements3);
        final List<List<?>> testElementSets = List.of(testElements1, testElements2, testElements3);
        final List<List<?>> testSortedSets = List.of(testSorted1, testSorted2, testSorted3);
        List<String> sorted1;
        List<Integer> sorted2;
        List<Double> sorted3;
        
        //standard
        sorted1 = testElements1.stream().sorted(Sorters.preserve()).collect(Collectors.toList());
        Assert.assertNotNull(sorted1);
        TestUtils.assertListEquals(sorted1, testSorted1);
        sorted2 = testElements2.stream().sorted(Sorters.preserve()).collect(Collectors.toList());
        Assert.assertNotNull(sorted2);
        TestUtils.assertListEquals(sorted2, testSorted2);
        sorted3 = testElements3.stream().sorted(Sorters.preserve()).collect(Collectors.toList());
        Assert.assertNotNull(sorted3);
        TestUtils.assertListEquals(sorted3, testSorted3);
        
        //null
        Stream.concat(testElementSets.stream(), testSortedSets.stream()).forEach(testSet ->
                testSet.add(2, null));
        sorted1 = testElements1.stream().sorted(Sorters.preserve()).collect(Collectors.toList());
        Assert.assertNotNull(sorted1);
        TestUtils.assertListEquals(sorted1, testSorted1);
        sorted2 = testElements2.stream().sorted(Sorters.preserve()).collect(Collectors.toList());
        Assert.assertNotNull(sorted2);
        TestUtils.assertListEquals(sorted2, testSorted2);
        sorted3 = testElements3.stream().sorted(Sorters.preserve()).collect(Collectors.toList());
        Assert.assertNotNull(sorted3);
        TestUtils.assertListEquals(sorted3, testSorted3);
        Stream.concat(testElementSets.stream(), testSortedSets.stream()).forEach(testSet ->
                testSet.removeIf(Objects::isNull));
        
        //uniqueness
        Assert.assertNotSame(
                Sorters.preserve(),
                Sorters.preserve());
    }
    
    /**
     * JUnit test of reverse.
     *
     * @throws Exception When there is an exception.
     * @see Sorters#reverse()
     */
    @Test
    public void testReverse() throws Exception {
        final List<String> testElements1 = ListUtility.clone((List<String>) testStreamElements[0]);
        final List<Integer> testElements2 = ListUtility.clone((List<Integer>) testStreamElements[1]);
        final List<Double> testElements3 = ListUtility.clone((List<Double>) testStreamElements[2]);
        final List<String> testSorted1 = IntStream.range(0, testElements1.size()).mapToObj(i -> testElements1.get(testElements1.size() - i - 1)).collect(Collectors.toList());
        final List<Integer> testSorted2 = IntStream.range(0, testElements2.size()).mapToObj(i -> testElements2.get(testElements2.size() - i - 1)).collect(Collectors.toList());
        final List<Double> testSorted3 = IntStream.range(0, testElements3.size()).mapToObj(i -> testElements3.get(testElements3.size() - i - 1)).collect(Collectors.toList());
        final List<List<?>> testElementSets = List.of(testElements1, testElements2, testElements3);
        final List<List<?>> testSortedSets = List.of(testSorted1, testSorted2, testSorted3);
        List<String> sorted1;
        List<Integer> sorted2;
        List<Double> sorted3;
        
        //standard
        sorted1 = testElements1.stream().sorted(Sorters.reverse()).collect(Collectors.toList());
        Assert.assertNotNull(sorted1);
        TestUtils.assertListEquals(sorted1, testSorted1);
        sorted2 = testElements2.stream().sorted(Sorters.reverse()).collect(Collectors.toList());
        Assert.assertNotNull(sorted2);
        TestUtils.assertListEquals(sorted2, testSorted2);
        sorted3 = testElements3.stream().sorted(Sorters.reverse()).collect(Collectors.toList());
        Assert.assertNotNull(sorted3);
        TestUtils.assertListEquals(sorted3, testSorted3);
        
        //null
        testElementSets.forEach(testSet -> testSet.add(2, null));
        testSortedSets.forEach(testSet -> testSet.add((testSet.size() - 2), null));
        sorted1 = testElements1.stream().sorted(Sorters.reverse()).collect(Collectors.toList());
        Assert.assertNotNull(sorted1);
        TestUtils.assertListEquals(sorted1, testSorted1);
        sorted2 = testElements2.stream().sorted(Sorters.reverse()).collect(Collectors.toList());
        Assert.assertNotNull(sorted2);
        TestUtils.assertListEquals(sorted2, testSorted2);
        sorted3 = testElements3.stream().sorted(Sorters.reverse()).collect(Collectors.toList());
        Assert.assertNotNull(sorted3);
        TestUtils.assertListEquals(sorted3, testSorted3);
        Stream.concat(testElementSets.stream(), testSortedSets.stream()).forEach(testSet ->
                testSet.removeIf(Objects::isNull));
        
        //uniqueness
        Assert.assertNotSame(
                Sorters.reverse(),
                Sorters.reverse());
    }
    
    /**
     * JUnit test of shuffle.
     *
     * @throws Exception When there is an exception.
     * @see Sorters#shuffle()
     */
    @Test
    public void testShuffle() throws Exception {
        final List<String> testElements1 = ListUtility.duplicateInOrder((List<String>) testStreamElements[0], 10);
        final List<Integer> testElements2 = ListUtility.duplicateInOrder((List<Integer>) testStreamElements[1], 10);
        final List<Double> testElements3 = ListUtility.duplicateInOrder((List<Double>) testStreamElements[2], 10);
        final List<String> testSorted1 = ListUtility.clone(testElements1);
        final List<Integer> testSorted2 = ListUtility.clone(testElements2);
        final List<Double> testSorted3 = ListUtility.clone(testElements3);
        final List<List<?>> testElementSets = List.of(testElements1, testElements2, testElements3);
        final List<List<?>> testSortedSets = List.of(testSorted1, testSorted2, testSorted3);
        List<String> sorted1;
        List<Integer> sorted2;
        List<Double> sorted3;
        
        //standard
        sorted1 = testElements1.stream().sorted(Sorters.shuffle()).collect(Collectors.toList());
        Assert.assertNotNull(sorted1);
        TestUtils.assertListEquals(sorted1, testSorted1, false);
        TestUtils.assertListNotEquals(sorted1, testSorted1, true);
        sorted2 = testElements2.stream().sorted(Sorters.shuffle()).collect(Collectors.toList());
        Assert.assertNotNull(sorted2);
        TestUtils.assertListEquals(sorted2, testSorted2, false);
        TestUtils.assertListNotEquals(sorted2, testSorted2, true);
        sorted3 = testElements3.stream().sorted(Sorters.shuffle()).collect(Collectors.toList());
        Assert.assertNotNull(sorted3);
        TestUtils.assertListEquals(sorted3, testSorted3, false);
        TestUtils.assertListNotEquals(sorted3, testSorted3, true);
        
        //null
        Stream.concat(testElementSets.stream(), testSortedSets.stream()).forEach(testSet ->
                testSet.add(2, null));
        sorted1 = testElements1.stream().sorted(Sorters.shuffle()).collect(Collectors.toList());
        Assert.assertNotNull(sorted1);
        TestUtils.assertListEquals(sorted1, testSorted1, false);
        TestUtils.assertListNotEquals(sorted1, testSorted1, true);
        sorted2 = testElements2.stream().sorted(Sorters.shuffle()).collect(Collectors.toList());
        Assert.assertNotNull(sorted2);
        TestUtils.assertListEquals(sorted2, testSorted2, false);
        TestUtils.assertListNotEquals(sorted2, testSorted2, true);
        sorted3 = testElements3.stream().sorted(Sorters.shuffle()).collect(Collectors.toList());
        Assert.assertNotNull(sorted3);
        TestUtils.assertListEquals(sorted3, testSorted3, false);
        TestUtils.assertListNotEquals(sorted3, testSorted3, true);
        Stream.concat(testElementSets.stream(), testSortedSets.stream()).forEach(testSet ->
                testSet.removeIf(Objects::isNull));
        
        //uniqueness
        Assert.assertNotSame(
                Sorters.shuffle(),
                Sorters.shuffle());
    }
    
}
