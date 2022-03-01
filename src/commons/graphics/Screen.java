/*
 * File:    Screen.java
 * Package: commons.graphics
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.graphics;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JPanel;

import commons.object.collection.ListUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A resource class that provides details about the screen.
 */
public final class Screen {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Screen.class);
    
    
    //Enums
    
    /**
     * An enumeration of screen Dimensions.
     */
    public enum Dimension {
        
        //Values
        
        MONITOR_WIDTH,  //The width of the screen
        MONITOR_HEIGHT, //The height of the screen
        TASKBAR_WIDTH,  //The width of the taskbar
        TASKBAR_HEIGHT, //The height of the taskbar
        SCREEN_WIDTH,   //The width of the screen excluding the taskbar
        SCREEN_HEIGHT,  //The height of the screen excluding the taskbar
        BORDER_WIDTH,   //The width of the window border
        BORDER_HEIGHT,  //The height of the window border
        DISPLAY_WIDTH,  //The width of the screen excluding the taskbar and window border
        DISPLAY_HEIGHT  //The height of the screen excluding the taskbars and window border
        
    }
    
    
    //Static Fields
    
    /**
     * The available screens.
     */
    private static final List<GraphicsDevice> screens = new ArrayList<>();
    
    /**
     * The dimensions of the screens.
     */
    private static final Map<Dimension, Map<Integer, Integer>> dimensions = new LinkedHashMap<>(); //TODO
    
    //Calculate the dimensions of the screens
    static {
        recalculate();
    }
    
    
    //Static Methods
    
    /**
     * Calculates the dimensions of the screens.
     */
    public static void recalculate() {
        final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        screens.clear();
        Arrays.stream(Dimension.values()).forEach(e -> dimensions.put(e, new HashMap<>()));
        
        IntStream.range(0, graphicsEnvironment.getScreenDevices().length).forEach(i -> {
            final GraphicsDevice graphicsDevice = graphicsEnvironment.getScreenDevices()[i];
            screens.add(graphicsDevice);
            
            dimensions.get(Dimension.MONITOR_WIDTH).put(i, graphicsDevice.getDisplayMode().getWidth());
            dimensions.get(Dimension.MONITOR_HEIGHT).put(i, graphicsDevice.getDisplayMode().getHeight());
            
            final Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(graphicsDevice.getDefaultConfiguration());
            dimensions.get(Dimension.TASKBAR_WIDTH).put(i, Math.abs(screenInsets.right - screenInsets.left));
            dimensions.get(Dimension.TASKBAR_HEIGHT).put(i, Math.abs(screenInsets.bottom - screenInsets.top));
            
            dimensions.get(Dimension.SCREEN_WIDTH).put(i, (dimensions.get(Dimension.MONITOR_WIDTH).get(i) - dimensions.get(Dimension.TASKBAR_WIDTH).get(i)));
            dimensions.get(Dimension.SCREEN_HEIGHT).put(i, (dimensions.get(Dimension.MONITOR_HEIGHT).get(i) - dimensions.get(Dimension.TASKBAR_HEIGHT).get(i)));
            
            final JFrame tmpFrame = new JFrame();
            tmpFrame.setLocation(graphicsDevice.getDefaultConfiguration().getBounds().x, tmpFrame.getY());
            tmpFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            final JPanel tmpPanel = new JPanel();
            tmpPanel.setSize(new java.awt.Dimension(500, 500));
            tmpPanel.setPreferredSize(new java.awt.Dimension(500, 500));
            tmpFrame.getContentPane().add(tmpPanel);
            tmpFrame.pack();
            dimensions.get(Dimension.BORDER_WIDTH).put(i, (tmpFrame.getWidth() - 500));
            dimensions.get(Dimension.BORDER_HEIGHT).put(i, (tmpFrame.getHeight() - 500));
            tmpFrame.dispose();
            
            dimensions.get(Dimension.DISPLAY_WIDTH).put(i, (dimensions.get(Dimension.SCREEN_WIDTH).get(i) - dimensions.get(Dimension.BORDER_WIDTH).get(i)));
            dimensions.get(Dimension.DISPLAY_HEIGHT).put(i, (dimensions.get(Dimension.SCREEN_HEIGHT).get(i) - dimensions.get(Dimension.BORDER_HEIGHT).get(i)));
        });
    }
    
    /**
     * Returns the number of screens available.
     *
     * @return The number of screens available.
     */
    public static int getScreenCount() {
        return screens.size();
    }
    
    /**
     * Returns a screen.
     *
     * @param screen The screen index.
     * @return The screen, or null if the screen doesn't exist.
     */
    public static GraphicsDevice getScreen(int screen) {
        return ListUtility.getOrNull(screens, screen);
    }
    
    /**
     * Returns the default screen.
     *
     * @return The default screen, or null if the screen doesn't exist.
     * @see #getScreen(int)
     */
    public static GraphicsDevice getScreen() {
        return getScreen(0);
    }
    
    /**
     * Returns a dimension of a screen.
     *
     * @param dimension The dimension.
     * @param screen    The screen index.
     * @return The specified dimension of the specified screen, or null if the screen doesn't exist.
     */
    public static Integer getDimension(Dimension dimension, int screen) {
        return dimensions.get(dimension).get(screen);
    }
    
    /**
     * Returns a dimension of the default screen.
     *
     * @param dimension The dimension.
     * @return The specified dimension of the default screen, or null if the screen doesn't exist.
     * @see #getDimension(Dimension, int)
     */
    public static Integer getDimension(Dimension dimension) {
        return getDimension(dimension, 0);
    }
    
}
