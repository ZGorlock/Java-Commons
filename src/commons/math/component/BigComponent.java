/*
 * File:    BigComponent.java
 * Package: commons.math.component
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import commons.math.BigMathUtility;
import commons.math.component.handler.math.BigComponentMathHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines the properties of a Big Component.
 */
public abstract class BigComponent<I extends BigComponent<?>> extends BaseComponent<BigDecimal, I> implements BigComponentInterface<I> {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigComponent.class);
    
    
    //Constants
    
    /**
     * The precision to use in comparisons.
     */
    public static final BigDecimal BIG_PRECISION = new BigDecimal("0.000000000000000000000000000000000001");
    
    /**
     * The default value of the precision of a Big Component math context.
     */
    public static final int DEFAULT_MATH_PRECISION = BigMathUtility.DEFAULT_MATH_PRECISION / 2;
    
    /**
     * The default rounding mode of a Big Component math context.
     */
    public static final RoundingMode DEFAULT_ROUNDING_MODE = BigMathUtility.DEFAULT_ROUNDING_MODE;
    
    
    //Fields
    
    /**
     * The MathContext to use when doing Big Component math.
     */
    public MathContext mathContext = new MathContext(DEFAULT_MATH_PRECISION, DEFAULT_ROUNDING_MODE);
    
    
    //Constructors
    
    /**
     * The protected no-argument constructor for a Big Component.
     */
    protected BigComponent() {
        super(BigDecimal.class, new BigComponentMathHandler());
    }
    
    
    //Methods
    
    /**
     * Copies this Big Component's metadata to another Big Component.
     *
     * @param to The Big Component to copy the metadata to.
     */
    @Override
    public void copyMeta(I to) {
        to.setMathContext(new MathContext(mathContext.getPrecision(), mathContext.getRoundingMode()));
    }
    
    
    //Getters
    
    /**
     * Returns the components that define the Big Component.
     *
     * @return The components that define the Big Component.
     */
    @Override
    public final BigDecimal[] getComponents() {
        return components;
    }
    
    /**
     * Returns the primitive components that define the Component.
     *
     * @return The primitive components that define the Component.
     */
    public final BigDecimal[] getPrimitiveComponents() {
        return getComponents();
    }
    
    /**
     * Returns the name of the type of Component.
     *
     * @return The name of the type of Component.
     */
    @Override
    public String getName() {
        return "Big Component";
    }
    
    /**
     * Returns the precision to use in comparisons.
     *
     * @return The precision to use in comparisons.
     */
    @Override
    public BigDecimal getPrecision() {
        return BIG_PRECISION;
    }
    
    /**
     * Returns the Math Context used when doing Big Component math.
     *
     * @return The Math Context used when doing Big Component math.
     */
    @Override
    public final MathContext getMathContext() {
        return mathContext;
    }
    
    
    //Setters
    
    /**
     * Sets the Math Context used when doing Big Component math.
     *
     * @param mathContext The Math Context used when doing BigDecimal math.
     */
    @Override
    public final void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
        ((BigComponentMathHandler) this.getHandler()).setMathContext(mathContext);
    }
    
}
