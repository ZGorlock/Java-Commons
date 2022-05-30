/*
 * File:    BigComponentMathHandler.java
 * Package: commons.math.component.handler.math
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.handler.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.IntFunction;

import commons.math.MathUtility;
import commons.math.big.BigMathUtility;
import commons.test.TestUtils;
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
    public static final BigDecimal PRECISION = TestUtils.DELTA_BIG;
    
    /**
     * The number of significant figures of the precision used for comparisons.
     */
    public static final int SIGNIFICANT_FIGURES = 36;
    
    /**
     * The default value of the precision of a Big Component Math Context.
     */
    public static final int DEFAULT_MATH_PRECISION = BigMathUtility.DEFAULT_MATH_PRECISION;
    
    /**
     * The default rounding mode of a Big Component Math Context.
     */
    public static final RoundingMode DEFAULT_ROUNDING_MODE = BigMathUtility.DEFAULT_ROUNDING_MODE;
    
    
    //Fields
    
    /**
     * The Math Context to use when doing math.
     */
    protected MathContext mathContext = new MathContext(DEFAULT_MATH_PRECISION, DEFAULT_ROUNDING_MODE);
    
    
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
        return BigMathUtility.ZERO;
    }
    
    /**
     * Returns one.
     *
     * @return One.
     */
    @Override
    public BigDecimal one() {
        return BigMathUtility.ONE;
    }
    
    /**
     * Returns negative one.
     *
     * @return Negative one.
     */
    @Override
    public BigDecimal negativeOne() {
        return BigMathUtility.NEGATIVE_ONE;
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
     * Returns an array generator.
     *
     * @return An array generator.
     */
    @Override
    public IntFunction<BigDecimal[]> arrayGenerator() {
        return BigDecimal[]::new;
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
        return clean(BigMathUtility.add(a, b, mathContext.getPrecision()));
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
        return clean(BigMathUtility.subtract(a, b, mathContext.getPrecision()));
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
        return clean(BigMathUtility.multiply(a, b, mathContext.getPrecision()));
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
        if (isZero(b)) {
            throw new ArithmeticException("Attempted to divide by zero");
        }
        return clean(BigMathUtility.divide(a, b, mathContext.getPrecision()));
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
        return clean(BigMathUtility.power(a, n, mathContext.getPrecision()));
    }
    
    /**
     * Defines the operation of the nth root of a component.
     *
     * @param a The component.
     * @param n The root.
     * @return The result of the root operation of the component.
     * @throws ArithmeticException When the result is imaginary, or when the degree of the root is divided by zero.
     */
    @Override
    public BigDecimal root(BigDecimal a, BigDecimal n) throws ArithmeticException {
        if (isZero(n)) {
            throw new ArithmeticException("Attempted to divide by zero");
        }
        if (compare(a, zero()) < 0) {
            throw new ArithmeticException("Result of root is imaginary");
        }
        return clean(BigMathUtility.root(a, n, mathContext.getPrecision()));
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
        if (compare(a, zero()) < 0) {
            throw new ArithmeticException("Result of square root is imaginary");
        }
        return clean(BigMathUtility.sqrt(a, mathContext.getPrecision()));
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
        return clean(BigMathUtility.reciprocal(a, mathContext.getPrecision()));
    }
    
    /**
     * Defines the absolute value operation of a component.
     *
     * @param a The component.
     * @return The absolute value of the component.
     */
    @Override
    public BigDecimal abs(BigDecimal a) {
        return clean(BigMathUtility.abs(a, mathContext.getPrecision()));
    }
    
    /**
     * Defines the negative value operation of a component.
     *
     * @param a The component.
     * @return The negative value of the component.
     */
    @Override
    public BigDecimal negate(BigDecimal a) {
        return clean(BigMathUtility.negate(a, mathContext.getPrecision()));
    }
    
    /**
     * Defines the round operation of a component.
     *
     * @param a The component.
     * @return The round value of the component.
     */
    @Override
    public BigDecimal round(BigDecimal a) {
        return clean(BigMathUtility.round(a, 0));
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
        return (compare(abs(subtract(b, a)), PRECISION) <= 0);
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
        return (clean(a).compareTo(zero()) == 0);
    }
    
    /**
     * Cleans a component.
     *
     * @param a The component.
     * @return The cleaned component.
     */
    @Override
    public BigDecimal clean(BigDecimal a) {
        return MathUtility.roundWithPrecision(a, mathContext.getPrecision(), DEFAULT_ROUNDING_MODE);
    }
    
    
    //Getters
    
    /**
     * Returns the comparison precision of the Component Math Handler.
     *
     * @return The comparison precision of the Component Math Handler.
     */
    public BigDecimal getPrecision() {
        return PRECISION;
    }
    
    /**
     * Returns the significant figures used for comparisons of the Component Math Handler.
     *
     * @return The significant figures used for comparisons of the Component Math Handler.
     */
    public int getSignificantFigures() {
        return SIGNIFICANT_FIGURES;
    }
    
    /**
     * Returns the math precision of the Component Math Handler.
     *
     * @return The math precision of the Component Math Handler.
     */
    public int getMathPrecision() {
        return this.mathContext.getPrecision();
    }
    
    
    //Setters
    
    /**
     * Sets the math precision of the Component Math Handler.
     *
     * @param mathPrecision The math precision of the Component Math Handler.
     */
    public void setMathPrecision(int mathPrecision) {
        this.mathContext = new MathContext(mathPrecision, DEFAULT_ROUNDING_MODE);
    }
    
}
