/*
 * File:    CustomCollectorsTest.java
 * Package: commons.lambda.stream.collector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.lambda.stream.collector;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import commons.object.collection.ArrayUtility;
import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
import commons.test.TestAccess;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of CustomCollectors.
 *
 * @see CustomCollectors
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CustomCollectors.class})
public class CustomCollectorsTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(CustomCollectorsTest.class);
    
    
    //Static Fields
    
    /**
     * The CustomCollector class.
     */
    private static final Class<?> CustomCollector = TestAccess.getClass(CustomCollectors.class, "CustomCollector");
    
    
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
     * JUnit test of collect.
     *
     * @throws Exception When there is an exception.
     * @see CustomCollectors#collect(Supplier, BiConsumer, BinaryOperator, Function, Set)
     * @see CustomCollectors#collect(Supplier, BiConsumer, BinaryOperator, Function)
     * @see CustomCollectors#collect(Supplier, BiConsumer, BinaryOperator)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testCollect() throws Exception {
        final Supplier<List<?>> mockSupplier = Mockito.mock(Supplier.class);
        final BiConsumer<List<?>, Object> mockAccumulator = Mockito.mock(BiConsumer.class);
        final BinaryOperator<List<?>> mockCombiner = Mockito.mock(BinaryOperator.class);
        final Function<List<?>, String> mockFinisher = Mockito.mock(Function.class);
        final Set<Collector.Characteristics> characteristics = new HashSet<>(Arrays.asList(Collector.Characteristics.UNORDERED, Collector.Characteristics.CONCURRENT));
        final Map<String, Object> testCollectorFields = MapUtility.mapOf(
                new String[] {"supplier", "accumulator", "combiner", "finisher", "characteristics"},
                new Object[] {mockSupplier, mockAccumulator, mockCombiner, mockFinisher, characteristics});
        Collector<Object, List<?>, String> testCollector;
        Collector<Object, List<?>, List<?>> testCollectorIdentity;
        
        final Consumer<Collector<?, ?, ?>> collectorValidator = (Collector<?, ?, ?> collector) -> {
            Assert.assertNotNull(collector);
            Assert.assertEquals(CustomCollector, collector.getClass());
            testCollectorFields.forEach((fieldName, testFieldValue) -> {
                final Object fieldValue = TestAccess.getFieldValue(collector, fieldName);
                if (testFieldValue != null) {
                    if (fieldValue instanceof Collection) {
                        TestUtils.assertListEquals(
                                ListUtility.toList((Collection<Object>) fieldValue),
                                (Collection<Object>) testFieldValue);
                    } else {
                        Assert.assertEquals(testFieldValue, fieldValue);
                    }
                } else {
                    Assert.assertNotNull(fieldValue);
                }
            });
        };
        
        //standard
        testCollector = CustomCollectors.collect(mockSupplier, mockAccumulator, mockCombiner, mockFinisher, characteristics);
        collectorValidator.accept(testCollector);
        
        //default characteristics
        testCollector = CustomCollectors.collect(mockSupplier, mockAccumulator, mockCombiner, mockFinisher);
        testCollectorFields.replace("characteristics", Set.of());
        collectorValidator.accept(testCollector);
        testCollectorFields.replace("characteristics", characteristics);
        
        //default finisher
        testCollectorIdentity = CustomCollectors.collect(mockSupplier, mockAccumulator, mockCombiner);
        testCollectorFields.replace("finisher", null);
        testCollectorFields.replace("characteristics", Set.of(Collector.Characteristics.IDENTITY_FINISH));
        collectorValidator.accept(testCollectorIdentity);
        testCollectorFields.replace("finisher", mockFinisher);
        testCollectorFields.replace("characteristics", characteristics);
        
        //uniqueness
        Assert.assertNotSame(
                CustomCollectors.collect(mockSupplier, mockAccumulator, mockCombiner, mockFinisher, characteristics),
                CustomCollectors.collect(mockSupplier, mockAccumulator, mockCombiner, mockFinisher, characteristics));
        Assert.assertNotSame(
                CustomCollectors.collect(mockSupplier, mockAccumulator, mockCombiner, mockFinisher),
                CustomCollectors.collect(mockSupplier, mockAccumulator, mockCombiner, mockFinisher));
        Assert.assertNotSame(
                CustomCollectors.collect(mockSupplier, mockAccumulator, mockCombiner),
                CustomCollectors.collect(mockSupplier, mockAccumulator, mockCombiner));
        
        //invalid
        TestUtils.assertNoException(() ->
                CustomCollectors.collect(null, null, null, null, null));
        TestUtils.assertNoException(() ->
                CustomCollectors.collect(null, null, null, null));
        TestUtils.assertNoException(() ->
                CustomCollectors.collect(null, null, null));
    }
    
    /**
     * JUnit test of CustomCollector.
     *
     * @throws Exception When there is an exception.
     * @see CustomCollectors.CustomCollector
     */
    @Test
    public void testCustomCollector() throws Exception {
        //class
        Assert.assertNotNull(CustomCollector);
        Assert.assertTrue(ArrayUtility.contains(CustomCollector.getInterfaces(), Collector.class));
        
        //fields
        List.of("supplier", "accumulator", "combiner", "finisher", "characteristics").forEach(fieldName ->
                TestUtils.assertFieldExists(CustomCollector, fieldName));
        
        //constructors
        TestUtils.assertConstructorExists(CustomCollector, Supplier.class, BiConsumer.class, BinaryOperator.class, Function.class, Set.class);
        
        //methods
        List.of("supplier", "accumulator", "combiner", "finisher", "characteristics").forEach(fieldName ->
                TestUtils.assertMethodExists(CustomCollector, fieldName));
    }
    
}
