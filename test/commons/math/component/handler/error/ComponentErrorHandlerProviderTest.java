/*
 * File:    ComponentErrorHandlerProviderTest.java
 * Package: commons.math.component.handler.error
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.error;

import commons.math.component.ComponentInterface;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ComponentErrorHandlerProvider.
 *
 * @see ComponentErrorHandlerProvider
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ComponentErrorHandlerProvider.class})
public class ComponentErrorHandlerProviderTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ComponentErrorHandlerProviderTest.class);
    
    
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
     * JUnit test of getErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#getErrorHandler()
     */
    @Test
    public void testGetErrorHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of setErrorHandler.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#setErrorHandler(ComponentErrorHandlerInterface)
     */
    @Test
    public void testSetErrorHandler() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of assertDimensionalitySame.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#assertDimensionalitySame(ComponentInterface, ComponentInterface)
     */
    @Test
    public void testAssertDimensionalitySame() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of assertDimensionalityEqual.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#assertDimensionalityEqual(ComponentInterface, int)
     */
    @Test
    public void testAssertDimensionalityEqual() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of assertIndexInBounds.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerProvider#assertIndexInBounds(ComponentInterface, int)
     */
    @Test
    public void testAssertIndexInBounds() throws Exception {
        //TODO
    }
    
}
