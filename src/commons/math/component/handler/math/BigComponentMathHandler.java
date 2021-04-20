/*
 * File:    BigComponentMathHandler.java
 * Package: commons.math.component.handler.math
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.math;

import java.math.BigDecimal;
import java.math.MathContext;

import ch.obermuhlner.math.big.BigDecimalMath;
import commons.math.component.BigComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines the Component Math Handler for performing math operations for Big Components.
 */
public class BigComponentMathHandler implements ComponentMathHandlerInterface<BigDecimal> {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(BigComponentMathHandler.class);
    
    
    //Constants
    
    /**
     * The precision to use in comparisons.
     */
    public static final BigDecimal PRECISION = BigComponent.BIG_PRECISION;
    
    
    //Fields
    
    /**
     * The MathContext to use when doing math.
     */
    protected MathContext mathContext = new MathContext(BigComponent.DEFAULT_MATH_PRECISION, BigComponent.DEFAULT_ROUNDING_MODE);
    
    
    //Constructors
    
    /**
     * The default no-argument constructor for a Big Component Math Handler.
     */
    public BigComponentMathHandler() {
    }
    
    
    //Methods
    
    /**
     * Returns zero.
     *
     * @return Zero.
     */
    @Override
    public BigDecimal zero() {
        return BigDecimal.ZERO;
    }
    
    /**
     * Returns one.
     *
     * @return One.
     */
    @Override
    public BigDecimal one() {
        return BigDecimal.ZERO;
    }
    
    /**
     * Returns negative one.
     *
     * @return Negative one.
     */
    @Override
    public BigDecimal negativeOne() {
        return BigDecimal.ONE.negate();
    }
    
    /**
     * Returns the value of a number.
     *
     * @param n The number.
     * @return The value of the specified number.
     */
    @Override
    public BigDecimal valueOf(Number n) {
        return new BigDecimal(n.toString());
    }
    
    /**
     * Returns an empty array.
     *
     * @param length The length of the array.
     * @return An empty array of the specified length.
     */
    @Override
    public BigDecimal[] array(int length) {
        return new BigDecimal[length];
    }
    
    /**
     * Defines the addition of one component with another.
     *
     * @param a The first component.
     * @param b The second component.
     * @return The result of the addition of the components.
     */
    @Override
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b, mathContext);
    }
    
    /**
     * Defines the subtraction of one component from another.
     *
     * @param a The first component.
     * @param b The second component.
     * @return The result of the subtraction of the components.
     */
    @Override
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b, mathContext);
    }
    
    /**
     * Defines the multiplication of one component with another.
     *
     * @param a The first component.
     * @param b The second component.
     * @return The result of the multiplication of the components.
     */
    @Override
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b, mathContext);
    }
    
    /**
     * Defines the division of one component by another.
     *
     * @param a The first component.
     * @param b The second component.
     * @return The result of the division of the components.
     * @throws ArithmeticException When the divisor is zero.
     */
    @Override
    public BigDecimal divide(BigDecimal a, BigDecimal b) throws ArithmeticException {
        return a.divide(b, mathContext);
    }
    
    /**
     * Defines the operation of the nth power of a component.
     *
     * @param a The component.
     * @param n The power.
     * @return The result of the power operation of the component.
     * @throws ArithmeticException When the result is imaginary.
     */
    @Override
    public BigDecimal power(BigDecimal a, BigDecimal n) throws ArithmeticException {
        return BigDecimalMath.pow(a, n, mathContext);
    }
    
    /**
     * Defines the operation of the nth root of a component.
     *
     * @param a The component.
     * @param n The root.
     * @return The result of the root operation of the component.
     * @throws ArithmeticException When the result is imaginary.
     */
    @Override
    public BigDecimal root(BigDecimal a, BigDecimal n) throws ArithmeticException {
        return BigDecimalMath.root(a, n, mathContext);
    }
    
    /**
     * Defines the square root of a component.
     *
     * @param a The component.
     * @return The square root of the component.
     * @throws ArithmeticException When the result is imaginary.
     */
    @Override
    public BigDecimal sqrt(BigDecimal a) throws ArithmeticException {
        return BigDecimalMath.sqrt(a, mathContext);
    }
    
    /**
     * Defines the reciprocal of a component.
     *
     * @param a The component.
     * @return The reciprocal of the component.
     * @throws ArithmeticException When the component is zero.
     */
    @Override
    public BigDecimal reciprocal(BigDecimal a) throws ArithmeticException {
        if (isZero(a)) {
            throw new ArithmeticException("Attempted to divide by zero");
        }
        
        return BigDecimalMath.reciprocal(a, mathContext);
    }
    
    /**
     * Defines the absolute value operation of a component.
     *
     * @param a The component.
     * @return The absolute value of the component.
     */
    @Override
    public BigDecimal abs(BigDecimal a) {
        return a.abs(mathContext);
    }
    
    /**
     * Defines the negative value operation of a component.
     *
     * @param a The component.
     * @return The negative value of the component.
     */
    @Override
    public BigDecimal negate(BigDecimal a) {
        return a.negate(mathContext);
    }
    
    /**
     * Defines the round operation of a component.
     *
     * @param a The component.
     * @return The round value of the component.
     */
    @Override
    public BigDecimal round(BigDecimal a) {
        return BigDecimalMath.round(a, mathContext);
    }
    
    /**
     * Defines the comparison of one component with another.
     *
     * @param a The first component.
     * @param b The second component.
     * @return The result of the comparison of the components.
     */
    @Override
    public int compare(BigDecimal a, BigDecimal b) {
        return a.compareTo(b);
    }
    
    /**
     * Defines the is equal operation of one component with another.
     *
     * @param a The first component.
     * @param b The second component.
     * @return The result of the is equal operation of the components.
     */
    @Override
    public boolean isEqual(BigDecimal a, BigDecimal b) {
        return (compare(abs(subtract(b, a)), PRECISION) < 0);
    }
    
    /**
     * Defines the is zero operation of a component.
     *
     * @param a The component.
     * @return Whether the component is zero or not.
     * @see #isEqual(BigDecimal, BigDecimal)
     */
    @Override
    public boolean isZero(BigDecimal a) {
        return a.equals(zero());
    }
    
    
    //Setters
    
    /**
     * Sets the MathContext to use when doing math.
     *
     * @param mathContext The MathContext to use when doing math.
     */
    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
    }
    
}
