/*
 * File:    RotationUtility.java
 * Package: commons.math
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.math;

import commons.math.matrix.Matrix3;
import commons.math.vector.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles rotations operations.
 */
public final class RotationUtility {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RotationUtility.class);
    
    
    //Functions
    
    /**
     * Calculates a rotation transformation matrix.
     *
     * @param roll  The roll angle to rotate by.
     * @param pitch The pitch angle to rotate by.
     * @param yaw   The yaw angle to rotate by.
     * @return The rotation transformation matrix.
     */
    public static Matrix3 getRotationMatrix(double roll, double pitch, double yaw) {
        Matrix3 rollRotation = new Matrix3(new double[] {
                1, 0, 0,
                0, Math.cos(roll), -Math.sin(roll),
                0, Math.sin(roll), Math.cos(roll)
        });
        Matrix3 pitchRotation = new Matrix3(new double[] {
                Math.cos(pitch), 0, Math.sin(pitch),
                0, 1, 0,
                -Math.sin(pitch), 0, Math.cos(pitch)
        });
        Matrix3 yawRotation = new Matrix3(new double[] {
                Math.cos(yaw), -Math.sin(yaw), 0,
                Math.sin(yaw), Math.cos(yaw), 0,
                0, 0, 1
        });
        return (Matrix3) rollRotation.multiply(pitchRotation).multiply(yawRotation);
    }
    
    /**
     * Performs a rotation transformation on a Vector.
     *
     * @param vector         The Vector to rotate.
     * @param rotationMatrix The rotation transformation matrix to apply.
     * @param center         The center point to rotate about.
     * @return The rotated Vector.
     */
    public static Vector performRotation(Vector vector, Matrix3 rotationMatrix, Vector center) {
        Vector justifiedCenter = center.justify();
        
        Vector result = vector.minus(center);
        result = rotationMatrix.transform(result);
        result = result.plus(center);
        return result;
    }
    
}
