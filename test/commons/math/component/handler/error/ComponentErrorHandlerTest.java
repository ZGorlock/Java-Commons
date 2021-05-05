/*
 * File:    ComponentErrorHandlerTest.java
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
 * JUnit test of ComponentErrorHandler.
 *
 * @see ComponentErrorHandler
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ComponentErrorHandler.class})
public class ComponentErrorHandlerTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ComponentErrorHandlerTest.class);
    
    
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
     * JUnit test of dimensionalityNotSameErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#dimensionalityNotSameErrorMessage(ComponentInterface, ComponentInterface)
     */
    @Test
    public void testDimensionalityNotSameErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#dimensionalityNotEqualErrorMessage(ComponentInterface, int)
     */
    @Test
    public void testDimensionalityNotEqualErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentTypeNotSameErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentTypeNotSameErrorMessage(ComponentInterface, ComponentInterface)
     */
    @Test
    public void testComponentTypeNotSameErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentTypeNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentTypeNotEqualErrorMessage(ComponentInterface, Number)
     */
    @Test
    public void testComponentTypeNotEqualErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of dimensionalityMinimumNotMetErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#dimensionalityMinimumNotMetErrorMessage(ComponentInterface, int)
     */
    @Test
    public void testDimensionalityMinimumNotMetErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentLengthNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentLengthNotEqualErrorMessage(Number[], int)
     */
    @Test
    public void testComponentLengthNotEqualErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentLengthNotSquareErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentLengthNotSquareErrorMessage(Number[])
     */
    @Test
    public void testComponentLengthNotSquareErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentIndexOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentIndexOutOfBoundsErrorMessage(ComponentInterface, int)
     */
    @Test
    public void testComponentIndexOutOfBoundsErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentCoordinateOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentCoordinateOutOfBoundsErrorMessage(ComponentInterface, int, int)
     */
    @Test
    public void testComponentCoordinateOutOfBoundsErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentRangeOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentRangeOutOfBoundsErrorMessage(ComponentInterface, int, int)
     */
    @Test
    public void testComponentRangeOutOfBoundsErrorMessage() throws Exception {
        //TODO
    }
    
    /**
     * JUnit test of componentCoordinateRangeOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandler#componentCoordinateRangeOutOfBoundsErrorMessage(ComponentInterface, int, int, int, int)
     */
    @Test
    public void testComponentCoordinateRangeOutOfBoundsErrorMessage() throws Exception {
        //TODO
    }
    
}
