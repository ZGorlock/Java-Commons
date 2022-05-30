/*
 * File:    SingletonInputHandlerTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Stream;

import commons.io.console.SystemInTest;
import commons.test.TestAccess;
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
 * JUnit test of SingletonInputHandler.
 *
 * @see SingletonInputHandler
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "ResultOfMethodCallIgnored", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({SingletonInputHandler.class})
public class SingletonInputHandlerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SingletonInputHandlerTest.class);
    
    
    //Static Fields
    
    /**
     * A list of test callers.
     */
    private static final Object[] callers = new Object[] {SingletonInputHandlerTest.class, new SingletonInputHandlerTest()};
    
    /**
     * A flag indicating whether the interrupt was activated or not.
     */
    private static final AtomicBoolean interrupted = new AtomicBoolean(false);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private SingletonInputHandler sut;
    
    
    //Functions
    
    /**
     * Validates the state of the system under test.
     */
    private final Consumer<Object[]> stateValidator = (Object[] params) -> {
        final Object owner = params[0];
        final Object manager = params[1];
        final boolean expectInterruptted = (boolean) params[2];
        Stream.of(callers, new Object[] {new SingletonInputHandlerTest()}).flatMap(Arrays::stream).forEach(e -> {
            Assert.assertEquals(((owner == e) || (!(e instanceof Class<?>) && (owner == e.getClass()))), sut.isOwner(e));
            Assert.assertEquals((manager == e), sut.isManager(e));
        });
        Assert.assertFalse(sut.isManager(new SingletonInputHandlerTest()));
        Assert.assertEquals(owner, Optional.ofNullable((AtomicReference<Object>)
                TestAccess.getFieldValue(sut, "owner")).map(AtomicReference::get).orElse(null));
        Assert.assertEquals(manager, Optional.ofNullable((AtomicReference<Object>)
                TestAccess.getFieldValue(sut, "manager")).map(AtomicReference::get).orElse(null));
        Assert.assertEquals(expectInterruptted, interrupted.getAndSet(false));
    };
    
    
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
        sut = new TestInputHandler();
        interrupted.set(false);
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
     * @see SingletonInputHandler#SingletonInputHandler(Runnable)
     */
    @Test
    public void testConstructors() throws Exception {
        SingletonInputHandler sut;
        
        //standard
        sut = new TestInputHandler();
        Assert.assertNotNull(sut);
        Assert.assertTrue(sut instanceof SingletonInputHandler);
        Assert.assertNull(sut.owner.get());
        Assert.assertNull(sut.manager.get());
        Assert.assertNotNull(TestAccess.getFieldValue(sut, "interrupt"));
        Assert.assertTrue(Runtime.getRuntime().removeShutdownHook(TestAccess.getFieldValue(sut, Thread.class, "interrupt")));
    }
    
    /**
     * JUnit test of isOwner.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#isOwner(Object)
     */
    @Test
    public void testIsOwner() throws Exception {
        //owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertTrue(sut.isOwner(e));
            stateValidator.accept(new Object[] {e, null, false});
        });
        
        //does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.isOwner(e));
            stateValidator.accept(new Object[] {SystemInTest.class, null, false});
        });
        
        //no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.isOwner(e));
            stateValidator.accept(new Object[] {null, null, false});
        });
        
        //manages, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertTrue(sut.isOwner(e));
            stateValidator.accept(new Object[] {e, SingletonInputHandlerTest.class, false});
        });
        
        //manages, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.isOwner(e));
            stateValidator.accept(new Object[] {SystemInTest.class, SingletonInputHandlerTest.class, false});
        });
        
        //manages, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.isOwner(e));
            stateValidator.accept(new Object[] {null, SingletonInputHandlerTest.class, false});
        });
        
        //does not manage, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertTrue(sut.isOwner(e));
            stateValidator.accept(new Object[] {e, SystemInTest.class, false});
        });
        
        //does not manage, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.isOwner(e));
            stateValidator.accept(new Object[] {SystemInTest.class, SystemInTest.class, false});
        });
        
        //does not manage, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.isOwner(e));
            stateValidator.accept(new Object[] {null, SystemInTest.class, false});
        });
        
        //invalid
        Assert.assertFalse(sut.isOwner(null));
    }
    
    /**
     * JUnit test of isManager.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#isManager(Object)
     */
    @Test
    public void testIsManager() throws Exception {
        //owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertFalse(sut.isManager(e));
            stateValidator.accept(new Object[] {e, null, false});
        });
        
        //does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.isManager(e));
            stateValidator.accept(new Object[] {SystemInTest.class, null, false});
        });
        
        //no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.isManager(e));
            stateValidator.accept(new Object[] {null, null, false});
        });
        
        //manages, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertEquals((e instanceof Class<?>), sut.isManager(e));
            stateValidator.accept(new Object[] {e, SingletonInputHandlerTest.class, false});
        });
        
        //manages, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertEquals((e instanceof Class<?>), sut.isManager(e));
            stateValidator.accept(new Object[] {SystemInTest.class, SingletonInputHandlerTest.class, false});
        });
        
        //manages, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertEquals((e instanceof Class<?>), sut.isManager(e));
            stateValidator.accept(new Object[] {null, SingletonInputHandlerTest.class, false});
        });
        
        //does not manage, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertFalse(sut.isManager(e));
            stateValidator.accept(new Object[] {e, SystemInTest.class, false});
        });
        
        //does not manage, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.isManager(e));
            stateValidator.accept(new Object[] {SystemInTest.class, SystemInTest.class, false});
        });
        
        //does not manage, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.isManager(e));
            stateValidator.accept(new Object[] {null, SystemInTest.class, false});
        });
        
        //invalid
        Assert.assertFalse(sut.isManager(null));
    }
    
    /**
     * JUnit test of acquireOwnership.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#acquireOwnership(Object)
     */
    @Test
    public void testAcquireOwnership() throws Exception {
        //owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertFalse(sut.acquireOwnership(e));
            stateValidator.accept(new Object[] {e, null, false});
        });
        
        //does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.acquireOwnership(e));
            stateValidator.accept(new Object[] {SystemInTest.class, null, false});
        });
        
        //no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertTrue(sut.acquireOwnership(e));
            stateValidator.accept(new Object[] {e, null, true});
        });
        
        //manages, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertFalse(sut.acquireOwnership(e));
            stateValidator.accept(new Object[] {e, SingletonInputHandlerTest.class, false});
        });
        
        //manages, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertEquals((e instanceof Class<?>), sut.acquireOwnership(e));
            stateValidator.accept(new Object[] {((e instanceof Class<?>) ? e : SystemInTest.class), SingletonInputHandlerTest.class, (e instanceof Class<?>)});
        });
        
        //manages, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertTrue(sut.acquireOwnership(e));
            stateValidator.accept(new Object[] {e, SingletonInputHandlerTest.class, true});
        });
        
        //does not manage, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertFalse(sut.acquireOwnership(e));
            stateValidator.accept(new Object[] {e, SystemInTest.class, false});
        });
        
        //does not manage, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.acquireOwnership(e));
            stateValidator.accept(new Object[] {SystemInTest.class, SystemInTest.class, false});
        });
        
        //does not manage, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertTrue(sut.acquireOwnership(e));
            stateValidator.accept(new Object[] {e, SystemInTest.class, true});
        });
        
        //invalid
        Assert.assertFalse(sut.acquireOwnership(null));
    }
    
    /**
     * JUnit test of releaseOwnership.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#releaseOwnership(Object)
     */
    @Test
    public void testReleaseOwnership() throws Exception {
        //owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertTrue(sut.releaseOwnership(e));
            stateValidator.accept(new Object[] {null, null, true});
        });
        
        //does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.releaseOwnership(e));
            stateValidator.accept(new Object[] {SystemInTest.class, null, false});
        });
        
        //no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.releaseOwnership(e));
            stateValidator.accept(new Object[] {null, null, false});
        });
        
        //manages, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertTrue(sut.releaseOwnership(e));
            stateValidator.accept(new Object[] {null, SingletonInputHandlerTest.class, true});
        });
        
        //manages, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertEquals((e instanceof Class<?>), sut.releaseOwnership(e));
            stateValidator.accept(new Object[] {((e instanceof Class<?>) ? null : SystemInTest.class), SingletonInputHandlerTest.class, (e instanceof Class<?>)});
        });
        
        //manages, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SingletonInputHandlerTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.releaseOwnership(e));
            stateValidator.accept(new Object[] {null, SingletonInputHandlerTest.class, false});
        });
        
        //does not manage, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertTrue(sut.releaseOwnership(e));
            stateValidator.accept(new Object[] {null, SystemInTest.class, true});
        });
        
        //does not manage, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.releaseOwnership(e));
            stateValidator.accept(new Object[] {SystemInTest.class, SystemInTest.class, false});
        });
        
        //does not manage, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.releaseOwnership(e));
            stateValidator.accept(new Object[] {null, SystemInTest.class, false});
        });
        
        //invalid
        Assert.assertFalse(sut.releaseOwnership(null));
    }
    
    /**
     * JUnit test of acquireManagement.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#acquireManagement(Object)
     */
    @Test
    public void testAcquireManagement() throws Exception {
        //owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertTrue(sut.acquireManagement(e));
            stateValidator.accept(new Object[] {e, e, false});
        });
        
        //does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertTrue(sut.acquireManagement(e));
            stateValidator.accept(new Object[] {SystemInTest.class, e, false});
        });
        
        //no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertTrue(sut.acquireManagement(e));
            stateValidator.accept(new Object[] {null, e, false});
        });
        
        //manages, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(e));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertFalse(sut.acquireManagement(e));
            stateValidator.accept(new Object[] {e, e, false});
        });
        
        //manages, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(e));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.acquireManagement(e));
            stateValidator.accept(new Object[] {SystemInTest.class, e, false});
        });
        
        //manages, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(e));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.acquireManagement(e));
            stateValidator.accept(new Object[] {null, e, false});
        });
        
        //does not manage, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertFalse(sut.acquireManagement(e));
            stateValidator.accept(new Object[] {e, SystemInTest.class, false});
        });
        
        //does not manage, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.acquireManagement(e));
            stateValidator.accept(new Object[] {SystemInTest.class, SystemInTest.class, false});
        });
        
        //does not manage, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.acquireManagement(e));
            stateValidator.accept(new Object[] {null, SystemInTest.class, false});
        });
        
        //invalid
        Assert.assertFalse(sut.acquireManagement(null));
    }
    
    /**
     * JUnit test of releaseManagement.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#releaseManagement(Object)
     */
    @Test
    public void testReleaseManagement() throws Exception {
        //owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertFalse(sut.releaseManagement(e));
            stateValidator.accept(new Object[] {e, null, false});
        });
        
        //does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.releaseManagement(e));
            stateValidator.accept(new Object[] {SystemInTest.class, null, false});
        });
        
        //no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(null));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.releaseManagement(e));
            stateValidator.accept(new Object[] {null, null, false});
        });
        
        //manages, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(e));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertTrue(sut.releaseManagement(e));
            stateValidator.accept(new Object[] {e, null, false});
        });
        
        //manages, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(e));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertTrue(sut.releaseManagement(e));
            stateValidator.accept(new Object[] {SystemInTest.class, null, false});
        });
        
        //manages, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(e));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertTrue(sut.releaseManagement(e));
            stateValidator.accept(new Object[] {null, null, false});
        });
        
        //does not manage, owns
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(e));
            Assert.assertFalse(sut.releaseManagement(e));
            stateValidator.accept(new Object[] {e, SystemInTest.class, false});
        });
        
        //does not manage, does not own
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(SystemInTest.class));
            Assert.assertFalse(sut.releaseManagement(e));
            stateValidator.accept(new Object[] {SystemInTest.class, SystemInTest.class, false});
        });
        
        //does not manage, no owner
        Arrays.stream(callers).forEach(e -> {
            TestAccess.setFieldValue(sut, "manager", new AtomicReference<>(SystemInTest.class));
            TestAccess.setFieldValue(sut, "owner", new AtomicReference<>(null));
            Assert.assertFalse(sut.releaseManagement(e));
            stateValidator.accept(new Object[] {null, SystemInTest.class, false});
        });
        
        //invalid
        Assert.assertFalse(sut.releaseManagement(null));
    }
    
    /**
     * JUnit test of interrupt.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#interrupt()
     */
    @Test
    public void testInterrupt() throws Exception {
        //standard
        TestAccess.invokeMethod(sut, "interrupt");
        Assert.assertTrue(interrupted.getAndSet(false));
        
        //no interrupt
        TestAccess.setFieldValue(sut, "interrupt", null);
        TestAccess.invokeMethod(sut, "interrupt");
        Assert.assertFalse(interrupted.get());
    }
    
    
    //Inner Classes
    
    /**
     * An implementation of the system under test.
     */
    private static class TestInputHandler extends SingletonInputHandler {
        
        //Constructors
        
        /**
         * The private constructor for SystemIn.
         *
         * @see SingletonInputHandler#SingletonInputHandler(Runnable)
         */
        private TestInputHandler() {
            super(() -> interrupted.set(true));
        }
        
    }
    
}
