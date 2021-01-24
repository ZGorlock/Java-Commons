/*
 * File:    Matrix.java
 * Package: commons.math.matrix
 * Author:  Zachary Gill
 */

package commons.math.matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a Matrix.
 */
public class Matrix {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Matrix.class);
    
    
    //Fields
    
    /**
     * The elements of the matrix.
     */
    public double[] values;
    
    
    //Constructors
    
    /**
     * The constructor for a Matrix.
     *
     * @param values The elements of the matrix.
     */
    public Matrix(double[] values) {
        this.values = values;
    }
    
    
    //Getters
    
    /**
     * Returns the dimensionality of the Matrix.
     *
     * @return The dimensionality of the Matrix.
     */
    public int getDimensionality() {
        return (int) Math.sqrt(values.length);
    }
    
    /**
     * Returns the values of the Matrix.
     *
     * @return The values of the Matrix.
     */
    public double[] getValues() {
        return values;
    }
    
}
