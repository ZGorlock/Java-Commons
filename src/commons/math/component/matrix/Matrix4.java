/*
 * File:    Matrix4.java
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
 * Defines a 4-Dimensional Matrix.
 */
public class Matrix4 extends Matrix {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Matrix4.class);
    
    
    //Constants
    
    /**
     * The dimensionality of a 4D Matrix.
     */
    public static final int DIMENSIONALITY = 4;
    
    
    //Constructors
    
    /**
     * The constructor for a 4D Matrix.
     *
     * @param components The components that define the Matrix.
     * @throws ArithmeticException When the number of components that define the Matrix is not the expected amount.
     * @see Matrix#Matrix(double...)
     */
    public Matrix4(double... components) throws ArithmeticException {
        super(components);
        ComponentErrorHandlerProvider.assertDimensionalityEqual(this, DIMENSIONALITY);
    }
    
    /**
     * The constructor for an empty 4D Matrix.
     *
     * @see Matrix#Matrix(int)
     */
    public Matrix4() {
        super(DIMENSIONALITY);
    }
    
    
    //Methods
    
    /**
     * Creates a cloned copy of the Matrix.
     *
     * @return The cloned Matrix.
     * @see #Matrix4(double...)
     */
    @Override
    public Matrix4 cloned() {
        Matrix4 clone = new Matrix4(Arrays.stream(getComponents())
                .mapToDouble(e -> e).toArray());
        copyMeta(clone);
        return clone;
    }
    
    /**
     * Creates an empty copy of the Matrix.
     *
     * @return The empty copy of the Matrix.
     * @see #Matrix4()
     */
    @Override
    public Matrix4 emptyCopy() {
        return new Matrix4();
    }
    
    /**
     * Creates a new Matrix instance.
     *
     * @param dim *Ignored for Matrix4*
     * @return The new Matrix.
     * @see #createInstance(int)
     */
    @Override
    public Matrix4 createNewInstance(int dim) {
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
        return "4D Matrix";
    }
    
    /**
     * Returns the plural name of the type of the Component.
     *
     * @return The plural name of the type of the Component.
     */
    @Override
    public String getNamePlural() {
        return "4D Matrices";
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
     * Creates a new 4D Matrix instance.
     *
     * @param dim *Ignored for Matrix4*
     * @return The new Matrix.
     * @see #Matrix4()
     */
    public static Matrix4 createInstance(int dim) {
        return new Matrix4();
    }
    
    /**
     * Creates a 4D identity Matrix.
     *
     * @param dim *Ignored for Matrix4*
     * @return The identity Matrix.
     * @see MatrixInterface#identity(int, Class)
     */
    public static Matrix4 identity(int dim) {
        return MatrixInterface.identity(DIMENSIONALITY, Matrix4.class);
    }
    
    /**
     * Creates a 4D origin Matrix.
     *
     * @param dim *Ignored for Matrix4*
     * @return The origin Matrix.
     * @see MatrixInterface#origin(int, Class)
     */
    public static Matrix4 origin(int dim) {
        return MatrixInterface.origin(DIMENSIONALITY, Matrix4.class);
    }
    
    /**
     * Creates a 4D sign chart Matrix.
     *
     * @param dim *Ignored for Matrix4*
     * @return The sign chart Matrix.
     * @see MatrixInterface#signChart(int, Class)
     */
    public static Matrix4 signChart(int dim) {
        return MatrixInterface.signChart(DIMENSIONALITY, Matrix4.class);
    }
    
}
