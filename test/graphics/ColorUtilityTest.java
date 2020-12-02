/*
 * File:    ColorUtilityTest.java
 * Package: graphics
 * Author:  Zachary Gill
 */

package graphics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import console.Console;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of ColorUtility.
 *
 * @see ColorUtility
 */
public class ColorUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ColorUtilityTest.class);
    
    
    //Initialization
    
    /**
     * The JUnit class setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @BeforeClass
    public static void setupClass() throws Exception {
    }
    
    /**
     * The JUnit class cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @AfterClass
    public static void cleanupClass() throws Exception {
    }
    
    /**
     * The JUnit setup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @Before
    public void setup() throws Exception {
    }
    
    /**
     * The JUnit cleanup operations.
     *
     * @throws Exception When there is an exception.
     */
    @SuppressWarnings("EmptyMethod")
    @After
    public void cleanup() throws Exception {
    }
    
    
    //Tests
    
    /**
     * JUnit test of getRandomColor.
     *
     * @throws Exception When there is an exception.
     * @see ColorUtility#getRandomColor()
     */
    @Test
    public void testGetRandomColor() throws Exception {
        try {
            //valid color
            Color color = ColorUtility.getRandomColor();
            Assert.assertTrue((color.getRed() >= 0) && (color.getRed() <= 255) &&
                    (color.getGreen() >= 0) && (color.getGreen() <= 255) &&
                    (color.getBlue() >= 0) && (color.getBlue() <= 255) &&
                    (color.getAlpha() == 255)
            );
            
            //correct alpha
            color = ColorUtility.getRandomColor(167);
            Assert.assertEquals(167, color.getAlpha());
            
            //randomness
            long sampleSize = 10000L;
            double error = 0.005;
            Set<Color> colorSet = new HashSet<>();
            for (long i = 0; i < sampleSize; i++) {
                colorSet.add(ColorUtility.getRandomColor());
            }
            Assert.assertTrue(((sampleSize - colorSet.size()) / (double) sampleSize) < error);
            
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of invertColor.
     *
     * @throws Exception When there is an exception.
     * @see ColorUtility#invertColor(Color)
     */
    @Test
    public void testInvertColor() throws Exception {
        try {
            Assert.assertEquals(new Color(0, 0, 0), ColorUtility.invertColor(new Color(255, 255, 255)));
            Assert.assertEquals(new Color(255, 255, 255), ColorUtility.invertColor(new Color(0, 0, 0)));
            Assert.assertEquals(new Color(127, 127, 127), ColorUtility.invertColor(new Color(128, 128, 128)));
            Assert.assertEquals(new Color(128, 128, 128), ColorUtility.invertColor(new Color(127, 127, 127)));
            Assert.assertEquals(new Color(55, 55, 55), ColorUtility.invertColor(new Color(200, 200, 200)));
            Assert.assertEquals(new Color(200, 200, 200), ColorUtility.invertColor(new Color(55, 55, 55)));
            Assert.assertEquals(new Color(0, 0, 255), ColorUtility.invertColor(new Color(255, 255, 0)));
            Assert.assertEquals(new Color(164, 21, 209), ColorUtility.invertColor(new Color(91, 234, 46)));
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getColorByHue.
     *
     * @throws Exception When there is an exception.
     * @see ColorUtility#getColorByHue(float)
     */
    @Test
    public void testGetColorByHue() throws Exception {
        try {
            Assert.assertEquals(Color.getHSBColor(0.0f, 1, 1), ColorUtility.getColorByHue(0.0f));
            Assert.assertEquals(Color.getHSBColor(0.00001f, 1, 1), ColorUtility.getColorByHue(0.00001f));
            Assert.assertEquals(Color.getHSBColor(0.1f, 1, 1), ColorUtility.getColorByHue(0.1f));
            Assert.assertEquals(Color.getHSBColor(0.5f, 1, 1), ColorUtility.getColorByHue(0.5f));
            Assert.assertEquals(Color.getHSBColor(0.9f, 1, 1), ColorUtility.getColorByHue(0.9f));
            Assert.assertEquals(Color.getHSBColor(1.0f, 1, 1), ColorUtility.getColorByHue(1.0f));
            Assert.assertEquals(Color.getHSBColor(1.1f, 1, 1), ColorUtility.getColorByHue(1.1f));
            Assert.assertEquals(Color.getHSBColor(5.9f, 1, 1), ColorUtility.getColorByHue(5.9f));
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getColorByWavelength.
     *
     * @throws Exception When there is an exception.
     * @see ColorUtility#getColorByWavelength(double)
     */
    @Test
    public void testGetColorByWavelength() throws Exception {
        try {
            Assert.assertEquals(new Color(97, 0, 97), ColorUtility.getColorByWavelength(380));
            Assert.assertEquals(new Color(131, 0, 181), ColorUtility.getColorByWavelength(400));
            Assert.assertEquals(new Color(84, 0, 255), ColorUtility.getColorByWavelength(425));
            Assert.assertEquals(new Color(0, 169, 255), ColorUtility.getColorByWavelength(470));
            Assert.assertEquals(new Color(0, 255, 84), ColorUtility.getColorByWavelength(505));
            Assert.assertEquals(new Color(129, 255, 0), ColorUtility.getColorByWavelength(540));
            Assert.assertEquals(new Color(240, 255, 0), ColorUtility.getColorByWavelength(575));
            Assert.assertEquals(new Color(255, 148, 0), ColorUtility.getColorByWavelength(612));
            Assert.assertEquals(new Color(255, 99, 0), ColorUtility.getColorByWavelength(625));
            Assert.assertEquals(new Color(255, 0, 0), ColorUtility.getColorByWavelength(700));
            Assert.assertEquals(new Color(181, 0, 0), ColorUtility.getColorByWavelength(740));
            Assert.assertEquals(new Color(97, 0, 0), ColorUtility.getColorByWavelength(780));
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    /**
     * JUnit test of getRandomColorExcludeConsoleEffects.
     *
     * @throws Exception When there is an exception.
     * @see ColorUtility#getRandomColorExcludeConsoleEffects()
     */
    @Test
    public void testGetRandomColorExcludeConsoleEffect() throws Exception {
        List<Integer> consoleEffects = new ArrayList<>();
        for (Console.ConsoleEffect effect : Console.ConsoleEffect.values()) {
            consoleEffects.add(effect.getCode());
        }
        
        try {
            for (long i = 0; i < 10000L; i++) {
                Color color = ColorUtility.getRandomColorExcludeConsoleEffects();
                Assert.assertFalse(consoleEffects.contains(color.getRed()));
                Assert.assertFalse(consoleEffects.contains(color.getGreen()));
                Assert.assertFalse(consoleEffects.contains(color.getBlue()));
            }
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
}
