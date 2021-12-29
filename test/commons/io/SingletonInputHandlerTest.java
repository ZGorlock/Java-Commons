/*
 * File:    SingletonInputHandlerTest.java
 * Package: commons.io
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.io;

import java.util.concurrent.atomic.AtomicBoolean;

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
 * JUnit test of SingletonInputHandler.
 *
 * @see SingletonInputHandler
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({SingletonInputHandler.class})
public class SingletonInputHandlerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SingletonInputHandlerTest.class);
    
    
    //Fields
    
    /**
     * The system under test.
     */
    private SingletonInputHandler sut;
    
    /**
     * A flag indicating whether the interrupt was activated or not.
     */
    private final AtomicBoolean interrupt = new AtomicBoolean(false);
    
    
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
        sut = new TestInputHandler();
        TestUtils.setField(sut, "interrupt", (Runnable) () -> interrupt.set(true));
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @After
    public void cleanup() throws Exception {
        interrupt.set(false);
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
     * @see SingletonInputHandler
     */
    @Test
    public void testConstructors() throws Exception {
        //default fields
        Assert.assertEquals("", TestUtils.getField(sut, "owner"));
        Assert.assertEquals("", TestUtils.getField(sut, "defaultOwner"));
    }
    
    /**
     * JUnit test of isOwner.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#isOwner(Class)
     * @see SingletonInputHandler#isOwner(Object)
     */
    @Test
    public void testIsOwner() throws Exception {
        //default
        Assert.assertFalse(sut.isOwner(SingletonInputHandlerTest.class));
        
        //owns
        TestUtils.setField(sut, "owner", SingletonInputHandlerTest.class.getCanonicalName());
        Assert.assertTrue(sut.isOwner(SingletonInputHandlerTest.class));
        Assert.assertTrue(sut.isOwner(new SingletonInputHandlerTest()));
        
        //does not own
        Assert.assertFalse(sut.isOwner(SystemInTest.class));
        
        //invalid
        Assert.assertFalse(sut.isOwner(null));
        Assert.assertFalse(sut.isOwner((Object) null));
    }
    
    /**
     * JUnit test of claimOwnership.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#claimOwnership(Class)
     * @see SingletonInputHandler#claimOwnership(Object)
     */
    @Test
    public void testClaimOwnership() throws Exception {
        //own
        Assert.assertTrue(sut.claimOwnership(new SingletonInputHandlerTest()));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(sut, "owner"));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(sut, "owner", "");
        Assert.assertTrue(sut.claimOwnership(SingletonInputHandlerTest.class));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(sut, "owner"));
        Assert.assertTrue(interrupt.getAndSet(false));
        
        //already owns
        Assert.assertFalse(sut.claimOwnership(SingletonInputHandlerTest.class));
        Assert.assertFalse(sut.claimOwnership(new SingletonInputHandlerTest()));
        
        //not owner
        Assert.assertFalse(sut.claimOwnership(SystemInTest.class));
        Assert.assertFalse(sut.claimOwnership(new SystemInTest()));
        TestUtils.setField(sut, "owner", "");
        
        //default own
        Assert.assertTrue(sut.claimOwnership(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(sut, "defaultOwner", SingletonInputHandler.class.getCanonicalName());
        Assert.assertFalse(sut.claimOwnership(SingletonInputHandlerTest.class));
        TestUtils.setField(sut, "owner", "");
        
        //invalid
        Assert.assertFalse(sut.claimOwnership(null));
        Assert.assertFalse(sut.claimOwnership((Object) null));
    }
    
    /**
     * JUnit test of claimDefaultOwnership.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#claimDefaultOwnership(Class)
     * @see SingletonInputHandler#claimDefaultOwnership(Object)
     */
    @Test
    public void testClaimDefaultOwnership() throws Exception {
        //default own
        Assert.assertTrue(sut.claimDefaultOwnership(new SingletonInputHandlerTest()));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(sut, "defaultOwner"));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(sut, "owner"));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(sut, "defaultOwner", "");
        Assert.assertTrue(sut.claimDefaultOwnership(SingletonInputHandlerTest.class));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(sut, "defaultOwner"));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(sut, "owner"));
        Assert.assertTrue(interrupt.getAndSet(false));
        
        //already default own
        Assert.assertFalse(sut.claimDefaultOwnership(SingletonInputHandlerTest.class));
        Assert.assertFalse(sut.claimDefaultOwnership(new SingletonInputHandlerTest()));
        
        //not default owner
        Assert.assertFalse(sut.claimDefaultOwnership(SystemInTest.class));
        Assert.assertFalse(sut.claimDefaultOwnership(new SystemInTest()));
        TestUtils.setField(sut, "defaultOwner", "");
        TestUtils.setField(sut, "owner", "");
        
        //own override
        Assert.assertTrue(sut.claimDefaultOwnership(SingletonInputHandlerTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(sut.claimOwnership(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(sut.claimOwnership(SingletonInputHandlerTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(sut, "defaultOwner", "");
        TestUtils.setField(sut, "owner", "");
        
        //own default
        Assert.assertTrue(sut.claimDefaultOwnership(SingletonInputHandlerTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(sut.claimOwnership(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(sut.relinquishOwnership(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(sut, "owner"));
        TestUtils.setField(sut, "defaultOwner", "");
        TestUtils.setField(sut, "owner", "");
        
        //invalid
        Assert.assertFalse(sut.claimDefaultOwnership(null));
        Assert.assertFalse(sut.claimDefaultOwnership((Object) null));
    }
    
    /**
     * JUnit test of relinquishOwnership.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#relinquishOwnership(Class)
     * @see SingletonInputHandler#relinquishOwnership(Object)
     */
    @Test
    public void testRelinquishOwnership() throws Exception {
        //not owned
        Assert.assertFalse(sut.relinquishOwnership(SingletonInputHandlerTest.class));
        Assert.assertFalse(sut.relinquishOwnership(new SingletonInputHandlerTest()));
        
        //owned
        TestUtils.setField(sut, "owner", SingletonInputHandlerTest.class.getCanonicalName());
        Assert.assertTrue(sut.relinquishOwnership(SingletonInputHandlerTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(sut, "owner", SingletonInputHandlerTest.class.getCanonicalName());
        Assert.assertTrue(sut.relinquishOwnership(new SingletonInputHandlerTest()));
        Assert.assertTrue(interrupt.getAndSet(false));
        
        //default owned
        TestUtils.setField(sut, "owner", SingletonInputHandlerTest.class.getCanonicalName());
        TestUtils.setField(sut, "defaultOwner", SingletonInputHandlerTest.class.getCanonicalName());
        Assert.assertTrue(sut.relinquishOwnership(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(sut.relinquishOwnership(new SystemInTest()));
        Assert.assertTrue(interrupt.getAndSet(false));
        
        //reset owner
        TestUtils.setField(sut, "defaultOwner", SingletonInputHandlerTest.class.getCanonicalName());
        TestUtils.setField(sut, "owner", SystemInTest.class.getCanonicalName());
        Assert.assertTrue(sut.relinquishOwnership(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(sut, "owner"));
        TestUtils.setField(sut, "owner", SingletonInputHandlerTest.class.getCanonicalName());
        TestUtils.setField(sut, "defaultOwner", SingletonInputHandlerTest.class.getCanonicalName());
        
        //invalid
        Assert.assertFalse(sut.relinquishOwnership(null));
        Assert.assertFalse(sut.relinquishOwnership((Object) null));
    }
    
    
    //Inner Classes
    
    /**
     * An implementation of the system under test.
     */
    private static class TestInputHandler extends SingletonInputHandler {
        
    }
    
}
