/*
 * File:    Vector2.java
 * Package: commons.math.component.vector
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.vector;

import commons.math.component.handler.error.ComponentErrorHandlerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a 2-Dimensional Vector.
 */
public class Vector2 extends Vector {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Vector2.class);
    
    
    //Constants
    
    /**
     * The dimensionality of a 2D Vector.
     */
    public static final int DIMENSIONALITY = 2;
    
    
    //Constructors
    
    /**
     * The constructor for a 2D Vector from components.
     *
     * @param x The x component of the Vector.
     * @param y The y component of the Vector.
     * @see Vector#Vector(double...)
     */
    public Vector2(double x, double y) {
        super(x, y);
    }
    
    /**
     * Constructs a 2D Vector from a Vector.
     *
     * @param vector The Vector.
     * @see #Vector2(double, double)
     */
    public Vector2(Vector vector) {
        this(vector.getX(), vector.getY());
    }
    
    /**
     * The constructor for an empty 2D Vector.
     *
     * @see Vector#Vector(int)
     */
    public Vector2() {
        super(DIMENSIONALITY);
    }
    
    
    //Methods
    
    /**
     * Creates a cloned copy of the Vector.
     *
     * @return The cloned Vector.
     * @see #Vector2(Vector)
     */
    @Override
    public Vector2 cloned() {
        Vector2 clone = new Vector2(this);
        copyMeta(clone);
        return clone;
    }
    
    /**
     * Creates an empty copy of the Vector.
     *
     * @return The empty copy of the Vector.
     * @see #Vector2()
     */
    @Override
    public Vector2 emptyCopy() {
        return new Vector2();
    }
    
    /**
     * Creates a new Vector instance.
     *
     * @param dim *Ignored for Vector2*
     * @return The new Vector.
     * @see #Vector2()
     */
    @Override
    public Vector2 createNewInstance(int dim) {
        return createInstance(DIMENSIONALITY);
    }
    
    /**
     * Calculates the square difference of this Vector.
     *
     * @return The square difference of the Vector.
     * @see #squareDifference(Vector)
     */
    public Double squareDifference() {
        return squareDifference(this);
    }
    
    /**
     * Calculates the dot flop between this Vector with another 2D Vector.
     *
     * @param other The other Vector.
     * @return The dot flop between the Vectors.
     * @throws ArithmeticException When the other Vector does not have a dimensionality of 2.
     * @see #dotFlop(Vector, Vector)
     */
    public Vector dotFlop(Vector other) throws ArithmeticException {
        return dotFlop(this, other);
    }
    
    /**
     * Calculates the negative dot flop between this Vector with another 2D Vector.
     *
     * @param other The other Vector.
     * @return The negative dot flop between the Vectors.
     * @throws ArithmeticException When the other Vector does not have a dimensionality of 2.
     * @see #dotFlopNegative(Vector, Vector)
     */
    public Vector dotFlopNegative(Vector other) throws ArithmeticException {
        return dotFlopNegative(this, other);
    }
    
    
    //Getters
    
    /**
     * Returns the name of the type of Component.
     *
     * @return The name of the type of Component.
     */
    @Override
    public String getName() {
        return "2D Vector";
    }
    
    /**
     * Returns whether this Component is resizeable or not.
     *
     * @return Whether this Component is resizeable or not.
     */
    @Override
    public boolean isResizeable() {
        return false;
    }
    
    
    //Functions
    
    /**
     * Creates a new 2D Vector instance.
     *
     * @param dim *Ignored for Vector2*
     * @return The new Vector.
     * @see #Vector2()
     */
    public static Vector2 createInstance(int dim) {
        return new Vector2();
    }
    
    /**
     * Creates a 2D identity Vector.
     *
     * @param dim *Ignored for Vector2*
     * @return The identity Vector.
     * @see VectorInterface#identity(int, Class)
     */
    public static Vector2 identity(int dim) {
        return VectorInterface.identity(DIMENSIONALITY, Vector2.class);
    }
    
    /**
     * Creates a 2D origin Vector.
     *
     * @param dim *Ignored for Vector2*
     * @return The origin Vector.
     * @see VectorInterface#origin(int, Class)
     */
    public static Vector2 origin(int dim) {
        return VectorInterface.origin(DIMENSIONALITY, Vector2.class);
    }
    
    /**
     * Calculates the square sum of a 2D Vector.
     *
     * @param vector The Vector.
     * @return The square sum of the Vector.
     * @throws ArithmeticException When the Vector does not have a dimensionality of 2.
     * @see Vector#squareSum()
     */
    public static Double squareSum(Vector vector) {
        ComponentErrorHandlerProvider.assertDimensionalityEqual(vector, DIMENSIONALITY);
        return vector.squareSum();
    }
    
    /**
     * Calculates the square difference of a 2D Vector.
     *
     * @param vector The Vector.
     * @return The square difference of the Vector.
     * @throws ArithmeticException When the Vector does not have a dimensionality of 2.
     */
    public static Double squareDifference(Vector vector) throws ArithmeticException {
        ComponentErrorHandlerProvider.assertDimensionalityEqual(vector, DIMENSIONALITY);
        return Math.pow(vector.getComponents()[0], 2) - Math.pow(vector.getComponents()[1], 2);
    }
    
    /**
     * Calculates the dot flop between two 2D Vectors.
     *
     * @param vector1 The first Vector.
     * @param vector2 The second Vector.
     * @return The dot flop of between Vectors.
     * @throws ArithmeticException When either of the Vectors do not have a dimensionality of 2.
     */
    public static Vector dotFlop(Vector vector1, Vector vector2) throws ArithmeticException {
        ComponentErrorHandlerProvider.assertDimensionalityEqual(vector1, DIMENSIONALITY);
        ComponentErrorHandlerProvider.assertDimensionalityEqual(vector2, DIMENSIONALITY);
        return new Vector2(
                (vector1.getComponents()[0] * vector2.getComponents()[0]) - (vector1.getComponents()[1] * vector2.getComponents()[1]),
                (vector1.getComponents()[0] * vector2.getComponents()[1]) + (vector1.getComponents()[1] * vector2.getComponents()[0])
        );
    }
    
    /**
     * Calculates the negative dot flop between two 2D Vectors.
     *
     * @param vector1 The first Vector.
     * @param vector2 The second Vector.
     * @return The negative dot flop between the Vectors.
     * @throws ArithmeticException When either of the Vectors do not have a dimensionality of 2.
     */
    public static Vector dotFlopNegative(Vector vector1, Vector vector2) throws ArithmeticException {
        ComponentErrorHandlerProvider.assertDimensionalityEqual(vector1, DIMENSIONALITY);
        ComponentErrorHandlerProvider.assertDimensionalityEqual(vector2, DIMENSIONALITY);
        return new Vector2(
                (vector1.getComponents()[0] * vector2.getComponents()[0]) + (vector1.getComponents()[1] * vector2.getComponents()[1]),
                (vector1.getComponents()[0] * vector2.getComponents()[1]) - (vector1.getComponents()[1] * vector2.getComponents()[0])
        );
    }
    
}
