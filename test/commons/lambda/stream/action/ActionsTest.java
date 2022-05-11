/*
 * File:    ActionsTest.java
 * Package: commons.lambda.stream.action
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.action;

import java.util.List;
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
 * JUnit test of Actions.
 *
 * @see Actions
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Actions.class})
public class ActionsTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ActionsTest.class);
    
    
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
     * JUnit test of doNothing.
     *
     * @throws Exception When there is an exception.
     * @see Actions#doNothing(Object)
     */
    @SuppressWarnings("SimplifyStreamApiCallChains")
    @Test
    public void testDoNothing() throws Exception {
        final List<String> testElements1 = ListUtility.clone((List<String>) testStreamElements[0]);
        final List<Integer> testElements2 = ListUtility.clone((List<Integer>) testStreamElements[1]);
        final List<Double> testElements3 = ListUtility.clone((List<Double>) testStreamElements[2]);
        final List<String> acted1 = ListUtility.clone(testElements1);
        final List<Integer> acted2 = ListUtility.clone(testElements2);
        final List<Double> acted3 = ListUtility.clone(testElements3);
        
        //standard
        acted1.stream().forEach(Actions::doNothing);
        TestUtils.assertListEquals(acted1, testElements1);
        acted2.stream().forEach(Actions::doNothing);
        TestUtils.assertListEquals(acted2, testElements2);
        acted3.stream().forEach(Actions::doNothing);
        TestUtils.assertListEquals(acted3, testElements3);
        
        //no stream
        acted1.forEach(Actions::doNothing);
        TestUtils.assertListEquals(acted1, testElements1);
        acted2.forEach(Actions::doNothing);
        TestUtils.assertListEquals(acted2, testElements2);
        acted3.forEach(Actions::doNothing);
        TestUtils.assertListEquals(acted3, testElements3);
        
        //uniqueness
        Assert.assertSame(
                Actions.doNothing(0),
                Actions.doNothing(0));
        
        //invalid
        TestUtils.assertNoException(() ->
                Actions.doNothing(null));
        TestUtils.assertNoException(() ->
                Stream.of(1, 2, 3).forEach(Actions::doNothing));
    }
    
}
