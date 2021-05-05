/*
 * File:    ComponentErrorHandlerInterfaceTest.java
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
 * JUnit test of ComponentErrorHandlerInterface.
 *
 * @see ComponentErrorHandlerInterface
 */
@SuppressWarnings({"RedundantSuppression", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ComponentErrorHandlerInterface.class})
public class ComponentErrorHandlerInterfaceTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ComponentErrorHandlerInterfaceTest.class);
    
    
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
     * @see ComponentErrorHandlerInterface#dimensionalityNotSameErrorMessage(ComponentInterface, ComponentInterface)
     */
    @Test
    public void testDimensionalityNotSameErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of dimensionalityNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#dimensionalityNotEqualErrorMessage(ComponentInterface, int)
     */
    @Test
    public void testDimensionalityNotEqualErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of componentTypeNotSameErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#componentTypeNotSameErrorMessage(ComponentInterface, ComponentInterface)
     */
    @Test
    public void testComponentTypeNotSameErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of componentTypeNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#componentTypeNotEqualErrorMessage(ComponentInterface, Number)
     */
    @Test
    public void testComponentTypeNotEqualErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of dimensionalityMinimumNotMetErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#dimensionalityMinimumNotMetErrorMessage(ComponentInterface, int)
     */
    @Test
    public void testDimensionalityMinimumNotMetErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of componentLengthNotEqualErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#componentLengthNotEqualErrorMessage(Number[], int)
     */
    @Test
    public void testComponentLengthNotEqualErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of componentLengthNotSquareErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#componentLengthNotSquareErrorMessage(Number[])
     */
    @Test
    public void testComponentLengthNotSquareErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of componentIndexOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#componentIndexOutOfBoundsErrorMessage(ComponentInterface, int)
     */
    @Test
    public void testComponentIndexOutOfBoundsErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of componentCoordinateOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#componentCoordinateOutOfBoundsErrorMessage(ComponentInterface, int, int)
     */
    @Test
    public void testComponentCoordinateOutOfBoundsErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of componentRangeOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#componentRangeOutOfBoundsErrorMessage(ComponentInterface, int, int)
     */
    @Test
    public void testComponentRangeOutOfBoundsErrorMessage() throws Exception {
    }
    
    /**
     * JUnit test of componentCoordinateRangeOutOfBoundsErrorMessage.
     *
     * @throws Exception When there is an exception.
     * @see ComponentErrorHandlerInterface#componentCoordinateRangeOutOfBoundsErrorMessage(ComponentInterface, int, int, int, int)
     */
    @Test
    public void testComponentCoordinateRangeOutOfBoundsErrorMessage() throws Exception {
    }
    
}
