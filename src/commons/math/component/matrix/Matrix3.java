/*
 * File:    Matrix3.java
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
 * Defines a 3-Dimensional Matrix.
 */
public class Matrix3 extends Matrix {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Matrix3.class);
    
    
    //Constants
    
    /**
     * The dimensionality of a 3D Matrix.
     */
    public static final int DIMENSIONALITY = 3;
    
    
    //Constructors
    
    /**
     * The constructor for a 3D Matrix.
     *
     * @param components The components that define the Matrix.
     * @throws ArithmeticException When the number of components that define the Matrix is not the expected amount.
     * @see Matrix#Matrix(double...)
     */
    public Matrix3(double... components) throws ArithmeticException {
        super(components);
        ComponentErrorHandlerProvider.assertDimensionalityEqual(this, DIMENSIONALITY);
    }
    
    /**
     * The constructor for an empty 3D Matrix.
     *
     * @see Matrix#Matrix(int)
     */
    public Matrix3() {
        super(DIMENSIONALITY);
    }
    
    
    //Methods
    
    /**
     * Creates a cloned copy of the Matrix.
     *
     * @return The cloned Matrix.
     * @see #Matrix3(double...)
     */
    @Override
    public Matrix3 cloned() {
        Matrix3 clone = new Matrix3(Arrays.stream(getComponents())
                .mapToDouble(e -> e).toArray());
        copyMeta(clone);
        return clone;
    }
    
    /**
     * Creates an empty copy of the Matrix.
     *
     * @return The empty copy of the Matrix.
     * @see #Matrix3()
     */
    @Override
    public Matrix3 emptyCopy() {
        return new Matrix3();
    }
    
    /**
     * Creates a new Matrix instance.
     *
     * @param dim *Ignored for Matrix3*
     * @return The new Matrix.
     * @see #createInstance(int)
     */
    @Override
    public Matrix3 createNewInstance(int dim) {
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
        return "3D Matrix";
    }
    
    /**
     * Returns the plural name of the type of the Component.
     *
     * @return The plural name of the type of the Component.
     */
    @Override
    public String getNamePlural() {
        return "3D Matrices";
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
     * Creates a new 3D Matrix instance.
     *
     * @param dim *Ignored for Matrix3*
     * @return The new Matrix.
     * @see #Matrix3()
     */
    public static Matrix3 createInstance(int dim) {
        return new Matrix3();
    }
    
    /**
     * Creates a 3D identity Matrix.
     *
     * @param dim *Ignored for Matrix3*
     * @return The identity Matrix.
     * @see MatrixInterface#identity(int, Class)
     */
    public static Matrix3 identity(int dim) {
        return MatrixInterface.identity(DIMENSIONALITY, Matrix3.class);
    }
    
    /**
     * Creates a 3D origin Matrix.
     *
     * @param dim *Ignored for Matrix3*
     * @return The origin Matrix.
     * @see MatrixInterface#origin(int, Class)
     */
    public static Matrix3 origin(int dim) {
        return MatrixInterface.origin(DIMENSIONALITY, Matrix3.class);
    }
    
    /**
     * Creates a 3D sign chart Matrix.
     *
     * @param dim *Ignored for Matrix3*
     * @return The sign chart Matrix.
     * @see MatrixInterface#signChart(int, Class)
     */
    public static Matrix3 signChart(int dim) {
        return MatrixInterface.signChart(DIMENSIONALITY, Matrix3.class);
    }
    
}