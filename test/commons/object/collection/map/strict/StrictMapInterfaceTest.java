/*
 * File:    StrictMapInterfaceTest.java
 * Package: commons.object.collection.map.strict
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.object.collection.map.strict;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
import commons.test.TestAccess;
import commons.test.TestUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of StrictMapInterface.
 *
 * @see StrictMapInterface
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({StrictMapInterface.class})
public class StrictMapInterfaceTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(StrictMapInterfaceTest.class);
    
    
    //Static Fields
    
    /**
     * The UnmodifiableSet class.
     */
    private static final Class<?> UnmodifiableSet = TestAccess.getClass(Collections.class, "UnmodifiableSet");
    
    /**
     * The UnmodifiableCollection class.
     */
    private static final Class<?> UnmodifiableCollection = TestAccess.getClass(Collections.class, "UnmodifiableCollection");
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private StrictMapInterface<String, Integer> sut;
    
    
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
    @Before
    public void setup() throws Exception {
        sut = Mockito.spy(StrictMapInterface.class);
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
     * JUnit test of exposedEntrySet.
     *
     * @throws Exception When there is an exception.
     * @see StrictMapInterface#exposedEntrySet()
     */
    @Test
    public void testExposedEntrySet() throws Exception {
        //default
        TestUtils.assertMethodExists(
                StrictMapInterface.class, "exposedEntrySet");
    }
    
    /**
     * JUnit test of immutableEntrySet.
     *
     * @throws Exception When there is an exception.
     * @see StrictMapInterface#immutableEntrySet()
     */
    @Test
    public void testImmutableEntrySet() throws Exception {
        final Set<Map.Entry<String, Integer>> entrySet = MapUtility.mapOf(
                new ImmutablePair<>("A", 1),
                new ImmutablePair<>("B", 2),
                new ImmutablePair<>("C", 3)
        ).entrySet();
        Set<Map.Entry<String, Integer>> immutableEntrySet;
        
        //default
        TestUtils.assertMethodExists(
                StrictMapInterface.class, "immutableEntrySet");
        
        //standard
        Mockito.when(sut.exposedEntrySet()).thenReturn(entrySet);
        immutableEntrySet = sut.immutableEntrySet();
        Assert.assertNotNull(immutableEntrySet);
        Assert.assertEquals(UnmodifiableSet, immutableEntrySet.getClass());
        Assert.assertEquals(entrySet.size(), immutableEntrySet.size());
        TestUtils.assertListEquals(
                ListUtility.toList(entrySet),
                immutableEntrySet, false);
        Assert.assertNotSame(entrySet, immutableEntrySet);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .exposedEntrySet();
    }
    
    /**
     * JUnit test of exposedKeySet.
     *
     * @throws Exception When there is an exception.
     * @see StrictMapInterface#exposedKeySet()
     */
    @Test
    public void testExposedKeySet() throws Exception {
        TestUtils.assertMethodExists(
                StrictMapInterface.class, "exposedKeySet");
    }
    
    /**
     * JUnit test of immutableKeySet.
     *
     * @throws Exception When there is an exception.
     * @see StrictMapInterface#immutableKeySet()
     */
    @Test
    public void testImmutableKeySet() throws Exception {
        final Set<String> keySet = new HashSet<>(ListUtility.listOf("A", "B", "C"));
        Set<String> immutableKeySet;
        
        //default
        TestUtils.assertMethodExists(
                StrictMapInterface.class, "immutableKeySet");
        
        //standard
        Mockito.when(sut.exposedKeySet()).thenReturn(keySet);
        immutableKeySet = sut.immutableKeySet();
        Assert.assertNotNull(immutableKeySet);
        Assert.assertEquals(UnmodifiableSet, immutableKeySet.getClass());
        Assert.assertEquals(keySet.size(), immutableKeySet.size());
        TestUtils.assertListEquals(
                ListUtility.toList(keySet),
                immutableKeySet, false);
        Assert.assertNotSame(keySet, immutableKeySet);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .exposedKeySet();
    }
    
    /**
     * JUnit test of exposedValues.
     *
     * @throws Exception When there is an exception.
     * @see StrictMapInterface#exposedValues()
     */
    @Test
    public void testExposedValues() throws Exception {
        TestUtils.assertMethodExists(
                StrictMapInterface.class, "exposedValues");
    }
    
    /**
     * JUnit test of immutableValues.
     *
     * @throws Exception When there is an exception.
     * @see StrictMapInterface#immutableValues()
     */
    @Test
    public void testImmutableValues() throws Exception {
        final Collection<Integer> valueSet = new HashSet<>(ListUtility.listOf(1, 2, 3));
        Collection<Integer> immutableValueSet;
        
        //default
        TestUtils.assertMethodExists(
                StrictMapInterface.class, "immutableValues");
        
        //standard
        Mockito.when(sut.exposedValues()).thenReturn(valueSet);
        immutableValueSet = sut.immutableValues();
        Assert.assertNotNull(immutableValueSet);
        Assert.assertEquals(UnmodifiableCollection, immutableValueSet.getClass());
        Assert.assertEquals(valueSet.size(), immutableValueSet.size());
        TestUtils.assertListEquals(
                ListUtility.toList(valueSet),
                immutableValueSet, false);
        Assert.assertNotSame(valueSet, immutableValueSet);
        Mockito.verify(sut, VerificationModeFactory.times(1))
                .exposedValues();
    }
    
}
