/*
 * File:    IntComponent.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import java.util.Arrays;

import commons.math.component.handler.math.IntComponentMathHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines the properties of a Int Component.
 *
 * @param <I> The type of the Component.
 */
public abstract class IntComponent<I extends IntComponent<?>> extends BaseComponent<Integer, I> {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(IntComponent.class);
    
    
    //Constants
    
    /**
     * The precision to use in comparisons.
     */
    public static final Integer INT_PRECISION = 1;
    
    
    //Constructors
    
    /**
     * The protected no-argument constructor for a Component.
     */
    protected IntComponent() {
        super(Integer.class, new IntComponentMathHandler());
    }
    
    
    //Methods
    
    /**
     * Copies this Component's metadata to another Component.
     *
     * @param to The Component to copy the metadata to.
     */
    @Override
    public void copyMeta(I to) {
    }
    
    
    //Getters
    
    /**
     * Returns the components that define the Component.
     *
     * @return The components that define the Component.
     */
    @Override
    public final Integer[] getComponents() {
        return components;
    }
    
    /**
     * Returns the primitive components that define the Component.
     *
     * @return The primitive components that define the Component.
     */
    public final int[] getPrimitiveComponents() {
        return Arrays.stream(getComponents()).mapToInt(e -> e).toArray();
    }
    
    /**
     * Returns the precision to use in comparisons.
     *
     * @return The precision to use in comparisons.
     */
    @Override
    public Integer getPrecision() {
        return INT_PRECISION;
    }
    
}
