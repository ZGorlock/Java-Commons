/*
 * File:    CustomListIteratorTest.java
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

import commons.lambda.stream.action.Actions;
import commons.lambda.stream.sorter.Sorters;
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
 * JUnit test of CustomListIterator.
 *
 * @see CustomListIterator
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CustomListIterator.class})
public class CustomListIteratorTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CustomListIteratorTest.class);
    
    
    //Static Fields
    
    /**
     * A list of test elements.
     */
    private static final List<Integer> testElements = IntStream.range(0, 10).boxed().collect(Collectors.toList());
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private CustomListIterator<Integer> sut;
    
    
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
     * @see CustomListIterator#CustomListIterator(Collection, BiConsumer, BiConsumer, BiConsumer)
     * @see CustomListIterator#CustomListIterator(Collection)
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testConstructors() throws Exception {
        final BiConsumer<List<Integer>, Boolean> sutInstanceValidator = (List<Integer> elements, Boolean modifiable) -> {
            Assert.assertNotNull(sut);
            Assert.assertTrue(sut instanceof CustomListIterator);
            Assert.assertTrue(sut instanceof CustomIterator);
            TestUtils.assertListEquals(
                    TestAccess.getFieldValue(sut, List.class, "iteration"),
                    elements);
            sutIndexAsserter.accept(-1);
            sutCanModifyAsserter.accept(false);
            Stream.of("remover", "replacer", "inserter").forEach(modifierFieldName ->
                    Assert.assertEquals(modifiable, (TestAccess.getFieldValue(sut, modifierFieldName) != null)));
        };
        
        //standard
        sut = new CustomListIterator<>(testElements,
                (index, element) -> testElements.remove(element),
                testElements::set,
                testElements::add);
        sutInstanceValidator.accept(testElements, true);
        
        //unmodifiable
        sut = new CustomListIterator<>(testElements);
        sutInstanceValidator.accept(testElements, false);
        
        //empty
        sut = new CustomListIterator<>(ListUtility.emptyList());
        sutInstanceValidator.accept(ListUtility.emptyList(), false);
        
        //invalid
        TestUtils.assertException(NullPointerException.class, () ->
                new CustomListIterator<>(null,
                        (index, element) -> testElements.remove(element),
                        testElements::set,
                        testElements::add));
        TestUtils.assertNoException(() ->
                new CustomListIterator<>(ListUtility.emptyList(), null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                new CustomListIterator<>(null));
    }
    
    /**
     * JUnit test of hasNext.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#hasNext()
     */
    @Test
    public void testHasNext() throws Exception {
        //standard
        sut = new CustomListIterator<>(testElements);
        IntStream.range(0, testElements.size()).forEach(i -> {
            Assert.assertTrue(sut.hasNext());
            sut.next();
        });
        Assert.assertFalse(sut.hasNext());
        
        //empty
        sut = new CustomListIterator<>(ListUtility.emptyList());
        Assert.assertFalse(sut.hasNext());
    }
    
    /**
     * JUnit test of next.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#next()
     */
    @Test
    public void testNext() throws Exception {
        //standard
        sut = new CustomListIterator<>(testElements);
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
        sut = new CustomListIterator<>(ListUtility.emptyList());
        TestUtils.assertException(NoSuchElementException.class, sut::next);
    }
    
    /**
     * JUnit test of hasPrevious.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#hasPrevious()
     */
    @Test
    public void testHasPrevious() throws Exception {
        //standard
        sut = new CustomListIterator<>(testElements);
        IntStream.range(0, testElements.size()).forEach(i -> {
            Assert.assertEquals((i > 1), sut.hasPrevious());
            sut.next();
        });
        Assert.assertTrue(sut.hasPrevious());
        
        //empty
        sut = new CustomListIterator<>(ListUtility.emptyList());
        Assert.assertFalse(sut.hasPrevious());
    }
    
    /**
     * JUnit test of previous.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#previous()
     */
    @Test
    public void testPrevious() throws Exception {
        //standard
        sut = new CustomListIterator<>(testElements);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        TestUtils.assertException(NoSuchElementException.class, sut::previous);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        sut.next();
        TestUtils.assertException(NoSuchElementException.class, sut::previous);
        sut.forEachRemaining(Actions::doNothing);
        IntStream.range(1, testElements.size()).boxed().sorted(Sorters.reverse()).forEach(i -> {
            sutIndexAsserter.accept(i);
            Assert.assertEquals(testElements.get(i - 1), sut.previous());
            sutCanModifyAsserter.accept(true);
        });
        sutIndexAsserter.accept(0);
        sutCanModifySetter.accept(false);
        TestUtils.assertException(NoSuchElementException.class, sut::previous);
        sutIndexAsserter.accept(0);
        sutCanModifyAsserter.accept(false);
        
        //empty
        sut = new CustomListIterator<>(ListUtility.emptyList());
        TestUtils.assertException(NoSuchElementException.class, sut::previous);
    }
    
    /**
     * JUnit test of nextIndex.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#nextIndex()
     */
    @Test
    public void testNextIndex() throws Exception {
        //standard
        sut = new CustomListIterator<>(testElements);
        sutIndexAsserter.accept(-1);
        Assert.assertEquals(0, sut.nextIndex());
        IntStream.range(0, testElements.size()).forEach(i -> {
            sut.next();
            sutIndexAsserter.accept(i);
            Assert.assertEquals((i + 1), sut.nextIndex());
        });
        sutIndexAsserter.accept(testElements.size() - 1);
        Assert.assertEquals(testElements.size(), sut.nextIndex());
        
        //empty
        sut = new CustomListIterator<>(ListUtility.emptyList());
        Assert.assertEquals(0, sut.nextIndex());
    }
    
    /**
     * JUnit test of previousIndex.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#previousIndex()
     */
    @Test
    public void testPreviousIndex() throws Exception {
        //standard
        sut = new CustomListIterator<>(testElements);
        sutIndexAsserter.accept(-1);
        Assert.assertEquals(-1, sut.previousIndex());
        sut.next();
        sutIndexAsserter.accept(0);
        Assert.assertEquals(-1, sut.previousIndex());
        sut.forEachRemaining(Actions::doNothing);
        IntStream.range(1, testElements.size()).boxed().sorted(Sorters.reverse()).forEach(i -> {
            sutIndexAsserter.accept(i);
            Assert.assertEquals((i - 1), sut.previousIndex());
            sut.previous();
        });
        sutIndexAsserter.accept(0);
        Assert.assertEquals(-1, sut.previousIndex());
        
        //empty
        sut = new CustomListIterator<>(ListUtility.emptyList());
        Assert.assertEquals(-1, sut.previousIndex());
    }
    
    /**
     * JUnit test of remove.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#remove()
     */
    @Test
    public void testRemove() throws Exception {
        final AtomicReference<List<Integer>> elements = new AtomicReference<>(null);
        
        final BiConsumer<Integer, Integer> valueRemover = (Integer index, Integer element) ->
                elements.get().remove(element);
        final BiConsumer<Integer, Integer> indexRemover = (Integer index, Integer element) ->
                elements.get().remove((int) index);
        final BiConsumer<Integer, Integer> modifier = (Integer index, Integer element) ->
                elements.get().set(index, element);
        final BiConsumer<Integer, Integer> inserter = (Integer index, Integer element) ->
                elements.get().add(index, element);
        
        //standard
        Stream.of(valueRemover, indexRemover).forEach(remover -> {
            elements.set(ListUtility.clone(testElements));
            sut = new CustomListIterator<>(testElements, remover, modifier, inserter);
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
        
        //advanced
        Stream.of(valueRemover, indexRemover).forEach(remover -> {
            elements.set(ListUtility.clone(testElements));
            sut = new CustomListIterator<>(testElements, remover, modifier, inserter);
            IntStream.range(0, 4).forEach(i ->
                    sut.next());
            sut.remove();
            Assert.assertFalse(elements.get().contains(3));
            sut.previous();
            sut.remove();
            Assert.assertFalse(elements.get().contains(1));
            sut.next();
            sut.set(-1);
            TestUtils.assertException(IllegalStateException.class, sut::remove);
            sut.next();
            sut.remove();
            Assert.assertFalse(elements.get().contains(4));
            sut.previous();
            sut.add(-2);
            TestUtils.assertException(IllegalStateException.class, sut::remove);
            sut.previous();
            sut.remove();
            Assert.assertFalse(elements.get().contains(-2));
            sutIndexAsserter.accept(-1);
            TestUtils.assertListEquals(
                    elements.get(),
                    new Integer[] {0, -1, 5, 6, 7, 8, 9});
            Assert.assertEquals(10, testElements.size());
        });
        
        //unmodifable
        elements.set(ListUtility.clone(testElements));
        sut = new CustomListIterator<>(testElements);
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
        sut = new CustomListIterator<>(ListUtility.emptyList(), valueRemover, modifier, inserter);
        TestUtils.assertException(IllegalStateException.class, sut::remove);
        
        //exception
        elements.set(ListUtility.unmodifiableList(testElements));
        sut = new CustomListIterator<>(testElements, valueRemover, modifier, inserter);
        sut.next();
        TestUtils.assertException(UnsupportedOperationException.class, sut::remove);
    }
    
    /**
     * JUnit test of set.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#set(Object)
     */
    @Test
    public void testSet() throws Exception {
        final AtomicReference<List<Integer>> elements = new AtomicReference<>(null);
        
        final BiConsumer<Integer, Integer> remover = (Integer index, Integer element) ->
                elements.get().remove(element);
        final BiConsumer<Integer, Integer> modifier = (Integer index, Integer element) ->
                elements.get().set(index, element);
        final BiConsumer<Integer, Integer> inserter = (Integer index, Integer element) ->
                elements.get().add(index, element);
        
        //standard
        elements.set(ListUtility.clone(testElements));
        sut = new CustomListIterator<>(testElements, remover, modifier, inserter);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        TestUtils.assertException(IllegalStateException.class, () ->
                sut.set(-1));
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        IntStream.range(0, testElements.size()).forEach(i -> {
            sut.next();
            sutIndexAsserter.accept(i);
            sutCanModifyAsserter.accept(true);
            sut.set(-i);
            sutIndexAsserter.accept(i);
            sutCanModifyAsserter.accept(false);
        });
        TestUtils.assertException(IllegalStateException.class, () ->
                sut.set(-1));
        sutIndexAsserter.accept(testElements.size() - 1);
        sutCanModifyAsserter.accept(false);
        TestUtils.assertListEquals(
                elements.get(),
                new Integer[] {0, -1, -2, -3, -4, -5, -6, -7, -8, -9});
        Assert.assertEquals(10, testElements.size());
        
        //advanced
        elements.set(ListUtility.clone(testElements));
        sut = new CustomListIterator<>(testElements, remover, modifier, inserter);
        IntStream.range(0, 3).forEach(i ->
                sut.next());
        sut.set(-2);
        Assert.assertEquals(-2, elements.get().get(2).intValue());
        sut.next();
        sut.set(-3);
        Assert.assertEquals(-3, elements.get().get(3).intValue());
        sut.previous();
        sut.previous();
        sut.remove();
        TestUtils.assertException(IllegalStateException.class, () ->
                sut.set(-1));
        sut.add(10);
        TestUtils.assertException(IllegalStateException.class, () ->
                sut.set(-1));
        sut.previous();
        sut.next();
        sut.set(-1);
        Assert.assertEquals(-1, elements.get().get(1).intValue());
        sut.previous();
        sut.set(0);
        Assert.assertEquals(0, elements.get().get(0).intValue());
        sutIndexAsserter.accept(0);
        TestUtils.assertListEquals(
                elements.get(),
                new Integer[] {0, -1, -2, -3, 4, 5, 6, 7, 8, 9});
        Assert.assertEquals(10, testElements.size());
        
        //unmodifiable
        elements.set(ListUtility.clone(testElements));
        sut = new CustomListIterator<>(testElements);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                sut.set(-1));
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        IntStream.range(0, testElements.size()).forEach(i -> {
            sut.next();
            sutIndexAsserter.accept(i);
            sutCanModifyAsserter.accept(true);
            TestUtils.assertException(UnsupportedOperationException.class, () ->
                    sut.set(-i));
            sutIndexAsserter.accept(i);
            sutCanModifyAsserter.accept(true);
        });
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                sut.set(-1));
        sutIndexAsserter.accept(testElements.size() - 1);
        sutCanModifyAsserter.accept(true);
        TestUtils.assertListEquals(elements.get(), testElements);
        Assert.assertEquals(10, testElements.size());
        
        //empty
        elements.set(ListUtility.emptyList());
        sut = new CustomListIterator<>(ListUtility.emptyList(), remover, modifier, inserter);
        TestUtils.assertException(IllegalStateException.class, sut::remove);
        
        //exception
        elements.set(ListUtility.unmodifiableList(testElements));
        sut = new CustomListIterator<>(testElements, remover, modifier, inserter);
        sut.next();
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                sut.set(-1));
    }
    
    /**
     * JUnit test of add.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#add(Object)
     */
    @Test
    public void testAdd() throws Exception {
        final AtomicReference<List<Integer>> elements = new AtomicReference<>(null);
        
        final BiConsumer<Integer, Integer> remover = (Integer index, Integer element) ->
                elements.get().remove(element);
        final BiConsumer<Integer, Integer> modifier = (Integer index, Integer element) ->
                elements.get().set(index, element);
        final BiConsumer<Integer, Integer> inserter = (Integer index, Integer element) ->
                elements.get().add(index, element);
        
        //standard
        elements.set(ListUtility.clone(testElements));
        sut = new CustomListIterator<>(testElements, remover, modifier, inserter);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        sut.add(-1);
        sutIndexAsserter.accept(0);
        sutCanModifyAsserter.accept(false);
        IntStream.range(0, testElements.size()).forEach(i -> {
            sut.next();
            sutIndexAsserter.accept((2 * i) + 1);
            sutCanModifyAsserter.accept(true);
            sut.add(-i);
            sutIndexAsserter.accept((2 * i) + 2);
            sutCanModifyAsserter.accept(false);
        });
        sut.add(-1);
        sutIndexAsserter.accept((2 * testElements.size()) + 1);
        sutCanModifyAsserter.accept(false);
        TestUtils.assertListEquals(
                elements.get(),
                new Integer[] {-1, 0, 0, -1, 1, -2, 2, -3, 3, -4, 4, -5, 5, -6, 6, -7, 7, -8, 8, -9, -1, 9});
        Assert.assertEquals(10, testElements.size());
        
        //advanced
        elements.set(ListUtility.clone(testElements));
        sut = new CustomListIterator<>(testElements, remover, modifier, inserter);
        IntStream.range(0, 3).forEach(i ->
                sut.next());
        sut.add(-2);
        Assert.assertTrue(elements.get().containsAll(List.of(2, -2)));
        sut.next();
        sut.next();
        sut.add(-3);
        Assert.assertTrue(elements.get().containsAll(List.of(3, -3)));
        sut.previous();
        sut.previous();
        sut.previous();
        sut.remove();
        sut.add(2);
        Assert.assertTrue(elements.get().containsAll(List.of(2, -2)));
        sut.previous();
        sut.previous();
        sut.set(-1);
        sut.add(1);
        Assert.assertTrue(elements.get().containsAll(List.of(1, -1)));
        sut.previous();
        sut.previous();
        sut.add(0);
        sut.add(0);
        Assert.assertEquals(3, ListUtility.numberOfOccurrences(elements.get(), 0));
        sutIndexAsserter.accept(2);
        TestUtils.assertListEquals(
                elements.get(),
                new Integer[] {0, 0, 0, 1, -1, 2, -2, 3, -3, 4, 5, 6, 7, 8, 9});
        Assert.assertEquals(10, testElements.size());
        
        //unmodifable
        elements.set(ListUtility.clone(testElements));
        sut = new CustomListIterator<>(testElements);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                sut.add(-1));
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        IntStream.range(0, testElements.size()).forEach(i -> {
            sut.next();
            sutIndexAsserter.accept(i);
            sutCanModifyAsserter.accept(true);
            TestUtils.assertException(UnsupportedOperationException.class, () ->
                    sut.add(-i));
            sutIndexAsserter.accept(i);
            sutCanModifyAsserter.accept(true);
        });
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                sut.add(-1));
        sutIndexAsserter.accept(testElements.size() - 1);
        sutCanModifyAsserter.accept(true);
        TestUtils.assertListEquals(elements.get(), testElements);
        Assert.assertEquals(10, testElements.size());
        
        //empty
        elements.set(ListUtility.emptyList());
        sut = new CustomListIterator<>(ListUtility.emptyList(), remover, modifier, inserter);
        sutIndexAsserter.accept(-1);
        sutCanModifyAsserter.accept(false);
        sut.add(-1);
        sutIndexAsserter.accept(0);
        sutCanModifyAsserter.accept(false);
        TestUtils.assertListEquals(
                elements.get(),
                new Integer[] {-1});
        Assert.assertEquals(10, testElements.size());
        
        //exception
        elements.set(ListUtility.unmodifiableList(testElements));
        sut = new CustomListIterator<>(testElements, remover, modifier, inserter);
        sut.next();
        TestUtils.assertException(UnsupportedOperationException.class, () ->
                sut.add(-1));
    }
    
    /**
     * JUnit test of forEachRemaining.
     *
     * @throws Exception When there is an exception.
     * @see CustomListIterator#forEachRemaining(Consumer)
     */
    @Test
    public void testForEachRemaining() throws Exception {
        final AtomicInteger sum = new AtomicInteger(0);
        
        //standard
        sut = new CustomListIterator<>(testElements);
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
        sut = new CustomListIterator<>(ListUtility.emptyList());
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
                new CustomListIterator<>(testElements).forEachRemaining(null));
    }
    
}
