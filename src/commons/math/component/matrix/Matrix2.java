/*
 * File:    Matrix2.java
 * Package: commons.math.component.matrix
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math.component.matrix;

import java.util.Arrays;

import commons.math.component.handler.error.ComponentErrorHandlerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a 2-Dimensional Matrix.
 */
public class Matrix2 extends Matrix {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Matrix2.class);
    
    
    //Constants
    
    /**
     * The dimensionality of a 2D Matrix.
     */
    public static final int DIMENSIONALITY = 2;
    
    
    //Constructors
    
    /**
     * The constructor for a 2D Matrix.
     *
     * @param components The components that define the Matrix.
     * @throws ArithmeticException When the number of components that define the Matrix is not the expected amount.
     * @see Matrix#Matrix(double...)
     */
    public Matrix2(double... components) throws ArithmeticException {
        super(components);
        ComponentErrorHandlerProvider.assertDimensionalityEqual(this, DIMENSIONALITY);
    }
    
    /**
     * The constructor for an empty 2D Matrix.
     *
     * @see Matrix#Matrix(int)
     */
    public Matrix2() {
        super(DIMENSIONALITY);
    }
    
    
    //Methods
    
    /**
     * Creates a cloned copy of the Matrix.
     *
     * @return The cloned Matrix.
     * @see #Matrix2(double...)
     */
    @Override
    public Matrix2 cloned() {
        Matrix2 clone = new Matrix2(Arrays.stream(getComponents())
                .mapToDouble(e -> e).toArray());
        copyMeta(clone);
        return clone;
    }
    
    /**
     * Creates an empty copy of the Matrix.
     *
     * @return The empty copy of the Matrix.
     * @see #Matrix2()
     */
    @Override
    public Matrix2 emptyCopy() {
        return new Matrix2();
    }
    
    /**
     * Creates a new Matrix instance.
     *
     * @param dim *Ignored for Matrix2*
     * @return The new Matrix.
     * @see #createInstance(int)
     */
    @Override
    public Matrix2 createNewInstance(int dim) {
        return createInstance(dim);
    }
    
    
    //Getters
    
    /**
     * Returns the name of the type of Component.
     *
     * @return The name of the type of Component.
     */
    @Override
    public String getName() {
        return "2D Matrix";
    }
    
    /**
     * Returns the plural name of the type of the Component.
     *
     * @return The plural name of the type of the Component.
     */
    @Override
    public String getNamePlural() {
        return "2D Matrices";
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
     * Creates a new 2D Matrix instance.
     *
     * @param dim *Ignored for Matrix2*
     * @return The new Matrix.
     * @see #Matrix2()
     */
    public static Matrix2 createInstance(int dim) {
        return new Matrix2();
    }
    
    /**
     * Creates a 2D identity Matrix.
     *
     * @param dim *Ignored for Matrix2*
     * @return The identity Matrix.
     * @see MatrixInterface#identity(int, Class)
     */
    public static Matrix2 identity(int dim) {
        return MatrixInterface.identity(DIMENSIONALITY, Matrix2.class);
    }
    
    /**
     * Creates a 2D origin Matrix.
     *
     * @param dim *Ignored for Matrix2*
     * @return The origin Matrix.
     * @see MatrixInterface#origin(int, Class)
     */
    public static Matrix2 origin(int dim) {
        return MatrixInterface.origin(DIMENSIONALITY, Matrix2.class);
    }
    
    /**
     * Creates a 2D sign chart Matrix.
     *
     * @param dim *Ignored for Matrix2*
     * @return The sign chart Matrix.
     * @see MatrixInterface#signChart(int, Class)
     */
    public static Matrix2 signChart(int dim) {
        return MatrixInterface.signChart(DIMENSIONALITY, Matrix2.class);
    }
    
}
