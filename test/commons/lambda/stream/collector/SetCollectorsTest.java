/*
 * File:    SetCollectorsTest.java
 * Package: commons.lambda.stream.collector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.collector;

import java.util.List;

import commons.object.collection.ListUtility;
import commons.object.collection.set.CounterSet;
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
 * JUnit test of SetCollectors.
 *
 * @see SetCollectors
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({SetCollectors.class})
public class SetCollectorsTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SetCollectorsTest.class);
    
    
    //Static Fields
    
    /**
     * A set of lists containing the elements of the streams to use for testing.
     */
    private static final List<?>[] testStreamElements = new List<?>[] {
            List.of("hello", "world", "test"),
            List.of("test:test", "key:value", "other:another", "test:else"),
            List.of(1, 4, 11),
            List.of(List.of(0, "test"), List.of(9, "value"), List.of(-4, "another"), List.of(10, "else"))};
    
    
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
     * JUnit test of toCounterSet.
     *
     * @throws Exception When there is an exception.
     * @see SetCollectors#toCounterSet()
     */
    @Test
    public void testToCounterSet() throws Exception {
        final List<String> testElements1 = (List<String>) testStreamElements[0];
        final List<String> testElements2 = (List<String>) testStreamElements[1];
        final List<Integer> testElements3 = (List<Integer>) testStreamElements[2];
        final List<List<Object>> testElements4 = (List<List<Object>>) testStreamElements[3];
        CounterSet<String> set1;
        CounterSet<String> set2;
        CounterSet<Integer> set3;
        CounterSet<List<Object>> set4;
        
        //standard
        set1 = testElements1.stream().collect(SetCollectors.toCounterSet());
        Assert.assertNotNull(set1);
        Assert.assertTrue(set1 instanceof CounterSet);
        TestUtils.assertListEquals(
                ListUtility.toList(set1),
                testElements1, false);
        Assert.assertTrue(set1.stream().map(set1::get).allMatch(counter -> (counter == 0)));
        set2 = testElements2.stream().collect(SetCollectors.toCounterSet());
        Assert.assertNotNull(set2);
        Assert.assertTrue(set2 instanceof CounterSet);
        TestUtils.assertListEquals(
                ListUtility.toList(set2),
                testElements2, false);
        Assert.assertTrue(set2.stream().map(set2::get).allMatch(counter -> (counter == 0)));
        set3 = testElements3.stream().collect(SetCollectors.toCounterSet());
        Assert.assertNotNull(set3);
        Assert.assertTrue(set3 instanceof CounterSet);
        TestUtils.assertListEquals(
                ListUtility.toList(set3),
                testElements3, false);
        Assert.assertTrue(set3.stream().map(set3::get).allMatch(counter -> (counter == 0)));
        set4 = testElements4.stream().collect(SetCollectors.toCounterSet());
        Assert.assertNotNull(set4);
        Assert.assertTrue(set4 instanceof CounterSet);
        TestUtils.assertListEquals(
                ListUtility.toList(set4),
                testElements4, false);
        Assert.assertTrue(set4.stream().map(set4::get).allMatch(counter -> (counter == 0)));
        
        //uniqueness
        Assert.assertNotSame(
                SetCollectors.toCounterSet(),
                SetCollectors.toCounterSet());
    }
    
}
