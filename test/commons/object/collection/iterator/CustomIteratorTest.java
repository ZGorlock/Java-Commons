/*
 * File:    CustomIteratorTest.java
 * Package: commons.object.collection.iterator
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.iterator;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import commons.object.collection.ListUtility;
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
 * JUnit test of CustomIterator.
 *
 * @see CustomIterator
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CustomIterator.class})
public class CustomIteratorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CustomIteratorTest.class);
    
    
    //Static Fields
    
    /**
     * A list of test elements.
     */
    private static final List<Integer> testElements = IntStream.range(0, 10).boxed().collect(Collectors.toList());
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private CustomIterator<Integer> sut;
    
    
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
     * JUnit test of constructors.
     *
     * @throws Exception When there is an exception.
     * @see CustomIterator#CustomIterator(Collection, Consumer)
     * @see CustomIterator#CustomIterator(Collection)
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testConstructors() throws Exception {
        //standard
        sut = new CustomIterator<>(testElements, testElements::remove);
        Assert.assertNotNull(sut);
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(sut, List.class, "iteration"),
                testElements);
        Assert.assertEquals(-1, TestAccess.getFieldValue(sut, int.class, "current").intValue());
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "canRemove"));
        Assert.assertNotNull(TestAccess.getFieldValue(sut, "remover"));
        
        //unremovable
        sut = new CustomIterator<>(testElements);
        Assert.assertNotNull(sut);
        TestUtils.assertListEquals(
                TestAccess.getFieldValue(sut, List.class, "iteration"),
                testElements);
        Assert.assertEquals(-1, TestAccess.getFieldValue(sut, int.class, "current").intValue());
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "canRemove"));
        Assert.assertNull(TestAccess.getFieldValue(sut, "remover"));
        
        //empty
        sut = new CustomIterator<>(ListUtility.emptyList());
        Assert.assertNotNull(sut);
        Assert.assertTrue(TestAccess.getFieldValue(sut, List.class, "iteration").isEmpty());
        Assert.assertEquals(-1, TestAccess.getFieldValue(sut, int.class, "current").intValue());
        Assert.assertFalse(TestAccess.getFieldValue(sut, boolean.class, "canRemove"));
        Assert.assertNull(TestAccess.getFieldValue(sut, "remover"));
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new CustomIterator<>(null, testElements::remove));
        TestUtils.assertNoException(() ->
                new CustomIterator<>(ListUtility.emptyList(), null));
        TestUtils.assertException(NullPointerException.class, () ->
                new CustomIterator<>(null));
    }
    
    /**
     * JUnit test of hasNext.
     *
     * @throws Exception When there is an exception.
     * @see CustomIterator#hasNext()
     */
    @Test
    public void testHasNext() throws Exception {
        //standard
        sut = new CustomIterator<>(testElements);
        IntStream.range(0, testElements.size()).forEach(i -> {
            Assert.assertTrue(sut.hasNext());
            sut.next();
        });
        Assert.assertFalse(sut.hasNext());
        
        //empty
        sut = new CustomIterator<>(ListUtility.emptyList());
        Assert.assertFalse(sut.hasNext());
    }
    
    /**
     * JUnit test of next.
     *
     * @throws Exception When there is an exception.
     * @see CustomIterator#next()
     */
    @Test
    public void testNext() throws Exception {
        //standard
        sut = new CustomIterator<>(testElements);
        IntStream.range(0, testElements.size()).forEach(i ->
                Assert.assertEquals(testElements.get(i), sut.next()));
        TestUtils.assertException(NoSuchElementException.class, sut::next);
        
        //empty
        sut = new CustomIterator<>(ListUtility.emptyList());
        TestUtils.assertException(NoSuchElementException.class, sut::next);
    }
    
    /**
     * JUnit test of remove.
     *
     * @throws Exception When there is an exception.
     * @see CustomIterator#remove()
     */
    @Test
    public void testRemove() throws Exception {
        List<Integer> elements;
        
        final Consumer<Boolean> canRemoveAsserter = (Boolean expected) ->
                Assert.assertEquals(expected, TestAccess.getFieldValue(sut, boolean.class, "canRemove"));
        
        //standard
        elements = ListUtility.clone(testElements);
        sut = new CustomIterator<>(testElements, elements::remove);
        canRemoveAsserter.accept(false);
        TestUtils.assertException(IllegalStateException.class, sut::remove);
        IntStream.range(0, testElements.size()).forEach(i -> {
            final int element = sut.next();
            canRemoveAsserter.accept(true);
            sut.remove();
            canRemoveAsserter.accept(false);
        });
        TestUtils.assertException(IllegalStateException.class, sut::remove);
        Assert.assertTrue(elements.isEmpty());
        Assert.assertEquals(10, testElements.size());
        
        //unremovable
        elements = ListUtility.clone(testElements);
        sut = new CustomIterator<>(testElements);
        canRemoveAsserter.accept(false);
        TestUtils.assertException(IllegalStateException.class, sut::remove);
        IntStream.range(0, testElements.size()).forEach(i -> {
            final int element = sut.next();
            canRemoveAsserter.accept(true);
            TestUtils.assertException(IllegalStateException.class, sut::remove);
            canRemoveAsserter.accept(true);
        });
        TestUtils.assertException(IllegalStateException.class, sut::remove);
        Assert.assertEquals(10, elements.size());
        Assert.assertEquals(10, testElements.size());
        
        //empty
        sut = new CustomIterator<>(ListUtility.emptyList());
        TestUtils.assertException(IllegalStateException.class, sut::remove);
        
        //invalid
        sut = new CustomIterator<>(testElements, (e -> ListUtility.unmodifiableList().add(e)));
        sut.next();
        TestUtils.assertException(UnsupportedOperationException.class, sut::remove);
    }
    
}
