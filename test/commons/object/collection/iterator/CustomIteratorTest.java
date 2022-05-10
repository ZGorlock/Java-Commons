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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    
    
    //Functions
    
    /**
     * Verifies that the index of the system under test is equal to the expected value.
     */
    private final Consumer<Integer> sutIndexAsserter = (Integer expected) ->
            Assert.assertEquals(expected, TestAccess.getFieldValue(sut, int.class, "index"));
    
    /**
     * Verifies that the canModify flag of the system under test is equal to the expected value.
     */
    private final Consumer<Boolean> sutCanModifyAsserter = (Boolean expected) ->
            Assert.assertEquals(expected, TestAccess.getFieldValue(sut, boolean.class, "canModify"));
    
    /**
     * Sets the value of the canModify flag of the system under test.
     */
    private final Consumer<Boolean> sutCanModifySetter = (Boolean value) ->
            Assert.assertTrue(TestAccess.setFieldValue(sut, "canModify", value));
    
    
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
     * @see CustomIterator#CustomIterator(Collection, BiConsumer)
     * @see CustomIterator#CustomIterator(Collection)
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testConstructors() throws Exception {
        final BiConsumer<List<Integer>, Boolean> sutInstanceValidator = (List<Integer> elements, Boolean modifiable) -> {
            Assert.assertNotNull(sut);
            Assert.assertTrue(sut instanceof CustomIterator);
            TestUtils.assertListEquals(
                    TestAccess.getFieldValue(sut, List.class, "iteration"),
                    elements);
            sutIndexAsserter.accept(-1);
            sutCanModifyAsserter.accept(false);
            Assert.assertEquals(modifiable, (TestAccess.getFieldValue(sut, "remover") != null));
        };
        
        //standard
        sut = new CustomIterator<>(testElements,
                (index, element) -> testElements.remove(element));
        sutInstanceValidator.accept(testElements, true);
        
        //unmodifiable
        sut = new CustomIterator<>(testElements);
        sutInstanceValidator.accept(testElements, false);
        
        //empty
        sut = new CustomIterator<>(ListUtility.emptyList());
        sutInstanceValidator.accept(ListUtility.emptyList(), false);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new CustomIterator<>(null,
                        (index, element) -> testElements.remove(element)));
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
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        IntStream.range(0, testElements.size()).forEach(i -> {
            Assert.assertEquals(testElements.get(i), sut.next());
            sutIndexAsserter.accept(i);
            sutCanModifyAsserter.accept(true);
        });
        sutIndexAsserter.accept(testElements.size() - 1);
        sutCanModifySetter.accept(false);
        TestUtils.assertException(NoSuchElementException.class, sut::next);
        sutIndexAsserter.accept(testElements.size() - 1);
        sutCanModifyAsserter.accept(false);
        
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
        final AtomicReference<List<Integer>> elements = new AtomicReference<>(null);
        
        final BiConsumer<Integer, Integer> removeByValue = (Integer index, Integer element) ->
                elements.get().remove(element);
        final BiConsumer<Integer, Integer> removeByIndex = (Integer index, Integer element) ->
                elements.get().remove((int) index);
        
        //standard
        Stream.of(removeByValue, removeByIndex).forEach(remover -> {
            elements.set(ListUtility.clone(testElements));
            sut = new CustomIterator<>(testElements, remover);
            sutIndexAsserter.accept(-1);
            sutCanModifyAsserter.accept(false);
            TestUtils.assertException(IllegalStateException.class, sut::remove);
            sutIndexAsserter.accept(-1);
            sutCanModifyAsserter.accept(false);
            IntStream.range(0, testElements.size()).forEach(i -> {
                sut.next();
                sutIndexAsserter.accept(0);
                sutCanModifyAsserter.accept(true);
                sut.remove();
                Assert.assertFalse(elements.get().contains(i));
                sutIndexAsserter.accept(-1);
                sutCanModifyAsserter.accept(false);
            });
            TestUtils.assertException(IllegalStateException.class, sut::remove);
            sutIndexAsserter.accept(-1);
            sutCanModifyAsserter.accept(false);
            Assert.assertTrue(elements.get().isEmpty());
            Assert.assertEquals(10, testElements.size());
        });
        
        //unmodifable
        elements.set(ListUtility.clone(testElements));
        sut = new CustomIterator<>(testElements);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        TestUtils.assertException(UnsupportedOperationException.class, sut::remove);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        IntStream.range(0, testElements.size()).forEach(i -> {
            sut.next();
            sutIndexAsserter.accept(i);
            sutCanModifyAsserter.accept(true);
            TestUtils.assertException(UnsupportedOperationException.class, sut::remove);
            sutIndexAsserter.accept(i);
            sutCanModifyAsserter.accept(true);
        });
        TestUtils.assertException(UnsupportedOperationException.class, sut::remove);
        sutIndexAsserter.accept(testElements.size() - 1);
        sutCanModifyAsserter.accept(true);
        TestUtils.assertListEquals(elements.get(), testElements);
        Assert.assertEquals(10, testElements.size());
        
        //empty
        elements.set(ListUtility.emptyList());
        sut = new CustomIterator<>(ListUtility.emptyList(), removeByValue);
        TestUtils.assertException(IllegalStateException.class, sut::remove);
        
        //exception
        elements.set(ListUtility.unmodifiableList(testElements));
        sut = new CustomIterator<>(testElements, removeByValue);
        sut.next();
        TestUtils.assertException(UnsupportedOperationException.class, sut::remove);
    }
    
    /**
     * JUnit test of forEachRemaining.
     *
     * @throws Exception When there is an exception.
     * @see CustomIterator#forEachRemaining(Consumer)
     */
    @Test
    public void testForEachRemaining() throws Exception {
        final AtomicInteger sum = new AtomicInteger(0);
        
        //standard
        sut = new CustomIterator<>(testElements);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        sut.forEachRemaining(element -> {
            sum.addAndGet(element);
            sutCanModifyAsserter.accept(true);
        });
        sutIndexAsserter.accept(testElements.size() - 1);
        sutCanModifyAsserter.accept(true);
        Assert.assertEquals(45, sum.getAndSet(0));
        Assert.assertFalse(sut.hasNext());
        
        //empty
        sut = new CustomIterator<>(ListUtility.emptyList());
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        sut.forEachRemaining(element -> {
            sum.addAndGet(element);
            sutCanModifyAsserter.accept(true);
        });
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        Assert.assertEquals(0, sum.getAndSet(0));
        Assert.assertFalse(sut.hasNext());
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new CustomIterator<>(testElements).forEachRemaining(null));
    }
    
}
