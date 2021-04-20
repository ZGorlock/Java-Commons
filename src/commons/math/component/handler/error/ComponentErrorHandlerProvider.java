/*
 * File:    ComponentErrorHandlerProvider.java
 * Package: commons.math.component.handler.error
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.error;

import commons.math.BoundUtility;
import commons.math.component.ComponentInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides the Component Error Handler.
 */
public final class ComponentErrorHandlerProvider {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ComponentErrorHandlerProvider.class);
    
    
    //Static Fields
    
    /**
     * The Component Error Handler.
     */
    private static ComponentErrorHandlerInterface errorHandler = new ComponentErrorHandler();
    
    
    //Getters
    
    /**
     * Returns the Component Error Handler.
     *
     * @return The Component Error Handler.
     */
    public static ComponentErrorHandlerInterface getErrorHandler() {
        return ComponentErrorHandlerProvider.errorHandler;
    }
    
    
    //Setters
    
    /**
     * Sets the Component Error Handler.
     *
     * @param errorHandler The Component Error Handler.
     */
    public static void setErrorHandler(ComponentErrorHandlerInterface errorHandler) {
        ComponentErrorHandlerProvider.errorHandler = errorHandler;
    }
    
    
    //Functions
    
    /**
     * Checks if the dimensionality of two Components is the same or not.
     *
     * @param component1 The first Component.
     * @param component2 The second Component.
     * @throws ArithmeticException When the Components do not have the same dimensionality.
     */
    public static void assertDimensionalitySame(ComponentInterface<?, ?, ?> component1, ComponentInterface<?, ?, ?> component2) throws ArithmeticException {
        if (!component1.dimensionalityEqual(component2)) {
            throw new ArithmeticException(component1.getErrorHandler().dimensionalityNotSameErrorMessage(component1, component2));
        }
    }
    
    /**
     * Checks if a Components has the expected dimensionality.
     *
     * @param component      The Component.
     * @param dimensionality The dimensionality.
     * @throws ArithmeticException When the Components do not have the same dimensionality.
     */
    public static void assertDimensionalityEqual(ComponentInterface<?, ?, ?> component, int dimensionality) throws ArithmeticException {
        if (component.getDimensionality() != dimensionality) {
            throw new ArithmeticException(component.getErrorHandler().dimensionalityNotEqualErrorMessage(component, dimensionality));
        }
    }
    
    /**
     * Checks if an index is within the bounds of a Component or not.
     *
     * @param component The Component.
     * @param index     The index of the component.
     * @throws IndexOutOfBoundsException When the Component does not contain a component at the specified index.
     */
    public static void assertIndexInBounds(ComponentInterface<?, ?, ?> component, int index) throws IndexOutOfBoundsException {
        if (!BoundUtility.inBounds(index, 0, component.getLength(), true, false)) {
            throw new IndexOutOfBoundsException(component.getErrorHandler().componentIndexOutOfBoundsErrorMessage(component, index));
        }
    }
    
}
