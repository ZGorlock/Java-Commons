/*
 * File:    Matrix2.java
 * Package: math.matrix
 * Author:  Zachary Gill
 */

package math.matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a 2D Matrix.
 */
public class Matrix2 {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Matrix2.class);
    
    
    //Fields
    
    /**
     * The elements of the matrix.
     */
    public double[] values;
    
    
    //Constructors
    
    /**
     * The constructor for a 2D Matrix.
     *
     * @param values The elements of the matrix.
     */
    public Matrix2(double[] values) {
        this.values = values;
    }
    
    
    //Methods
    
    /**
     * Determines the determinant of the matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        return (values[0] * values[3]) - (values[1] * values[2]);
    }
    
}
