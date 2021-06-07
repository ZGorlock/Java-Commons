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
        TestUtils.setField(SingletonInputHandler.class, "interrupt", (Runnable) () -> interrupt.set(true));
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
        //static
        Assert.assertEquals("", TestUtils.getField(SingletonInputHandler.class, "owner"));
        Assert.assertEquals("", TestUtils.getField(SingletonInputHandler.class, "defaultOwner"));
    }
    
    /**
     * JUnit test of owns.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#owns(Class)
     * @see SingletonInputHandler#owns(Object)
     */
    @Test
    public void testOwns() throws Exception {
        //default
        Assert.assertFalse(SingletonInputHandler.owns(SingletonInputHandlerTest.class));
        
        //owns
        TestUtils.setField(SingletonInputHandler.class, "owner", SingletonInputHandlerTest.class.getCanonicalName());
        Assert.assertTrue(SingletonInputHandler.owns(SingletonInputHandlerTest.class));
        Assert.assertTrue(SingletonInputHandler.owns(new SingletonInputHandlerTest()));
        
        //does not own
        Assert.assertFalse(SingletonInputHandler.owns(SystemInTest.class));
        
        //reset
        TestUtils.setField(SingletonInputHandler.class, "owner", "");
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", "");
    }
    
    /**
     * JUnit test of own.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#own(Class)
     * @see SingletonInputHandler#own(Object)
     */
    @Test
    public void testOwn() throws Exception {
        //own
        Assert.assertTrue(SingletonInputHandler.own(new SingletonInputHandlerTest()));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(SingletonInputHandler.class, "owner"));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(SingletonInputHandler.class, "owner", "");
        Assert.assertTrue(SingletonInputHandler.own(SingletonInputHandlerTest.class));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(SingletonInputHandler.class, "owner"));
        Assert.assertTrue(interrupt.getAndSet(false));
        
        //already owns
        Assert.assertFalse(SingletonInputHandler.own(SingletonInputHandlerTest.class));
        Assert.assertFalse(SingletonInputHandler.own(new SingletonInputHandlerTest()));
        
        //not owner
        Assert.assertFalse(SingletonInputHandler.own(SystemInTest.class));
        Assert.assertFalse(SingletonInputHandler.own(new SystemInTest()));
        TestUtils.setField(SingletonInputHandler.class, "owner", "");
        
        //default own
        Assert.assertTrue(SingletonInputHandler.own(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", SingletonInputHandler.class.getCanonicalName());
        Assert.assertFalse(SingletonInputHandler.own(SingletonInputHandlerTest.class));
        
        //reset
        TestUtils.setField(SingletonInputHandler.class, "owner", "");
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", "");
    }
    
    /**
     * JUnit test of defaultOwn.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#defaultOwn(Class)
     * @see SingletonInputHandler#defaultOwn(Object)
     */
    @Test
    public void testDefaultOwn() throws Exception {
        //default own
        Assert.assertTrue(SingletonInputHandler.defaultOwn(new SingletonInputHandlerTest()));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(SingletonInputHandler.class, "defaultOwner"));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(SingletonInputHandler.class, "owner"));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", "");
        Assert.assertTrue(SingletonInputHandler.defaultOwn(SingletonInputHandlerTest.class));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(SingletonInputHandler.class, "defaultOwner"));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(SingletonInputHandler.class, "owner"));
        Assert.assertTrue(interrupt.getAndSet(false));
        
        //already default own
        Assert.assertFalse(SingletonInputHandler.defaultOwn(SingletonInputHandlerTest.class));
        Assert.assertFalse(SingletonInputHandler.defaultOwn(new SingletonInputHandlerTest()));
        
        //not default owner
        Assert.assertFalse(SingletonInputHandler.defaultOwn(SystemInTest.class));
        Assert.assertFalse(SingletonInputHandler.defaultOwn(new SystemInTest()));
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", "");
        TestUtils.setField(SingletonInputHandler.class, "owner", "");
        
        //own override
        Assert.assertTrue(SingletonInputHandler.defaultOwn(SingletonInputHandlerTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(SingletonInputHandler.own(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(SingletonInputHandler.own(SingletonInputHandlerTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", "");
        TestUtils.setField(SingletonInputHandler.class, "owner", "");
        
        //own default
        Assert.assertTrue(SingletonInputHandler.defaultOwn(SingletonInputHandlerTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(SingletonInputHandler.own(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(SingletonInputHandler.relinquish(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(SingletonInputHandler.class, "owner"));
        
        //reset
        TestUtils.setField(SingletonInputHandler.class, "owner", "");
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", "");
    }
    
    /**
     * JUnit test of relinquish.
     *
     * @throws Exception When there is an exception.
     * @see SingletonInputHandler#relinquish(Class)
     * @see SingletonInputHandler#relinquish(Object)
     */
    @Test
    public void testRelinquish() throws Exception {
        //not owned
        Assert.assertFalse(SingletonInputHandler.relinquish(SingletonInputHandlerTest.class));
        Assert.assertFalse(SingletonInputHandler.relinquish(new SingletonInputHandlerTest()));
        
        //owned
        TestUtils.setField(SingletonInputHandler.class, "owner", SingletonInputHandlerTest.class.getCanonicalName());
        Assert.assertTrue(SingletonInputHandler.relinquish(SingletonInputHandlerTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        TestUtils.setField(SingletonInputHandler.class, "owner", SingletonInputHandlerTest.class.getCanonicalName());
        Assert.assertTrue(SingletonInputHandler.relinquish(new SingletonInputHandlerTest()));
        Assert.assertTrue(interrupt.getAndSet(false));
        
        //default owned
        TestUtils.setField(SingletonInputHandler.class, "owner", SingletonInputHandlerTest.class.getCanonicalName());
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", SingletonInputHandlerTest.class.getCanonicalName());
        Assert.assertTrue(SingletonInputHandler.relinquish(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertTrue(SingletonInputHandler.relinquish(new SystemInTest()));
        Assert.assertTrue(interrupt.getAndSet(false));
        
        //reset owner
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", SingletonInputHandlerTest.class.getCanonicalName());
        TestUtils.setField(SingletonInputHandler.class, "owner", SystemInTest.class.getCanonicalName());
        Assert.assertTrue(SingletonInputHandler.relinquish(SystemInTest.class));
        Assert.assertTrue(interrupt.getAndSet(false));
        Assert.assertEquals(SingletonInputHandlerTest.class.getCanonicalName(), TestUtils.getField(SingletonInputHandler.class, "owner"));
        
        //reset
        TestUtils.setField(SingletonInputHandler.class, "owner", "");
        TestUtils.setField(SingletonInputHandler.class, "defaultOwner", "");
    }
    
}
