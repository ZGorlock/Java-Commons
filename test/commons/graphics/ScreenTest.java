/*
 * File:    ScreenTest.java
 * Package: commons.graphics
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.graphics;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import commons.lambda.stream.collector.MapCollectors;
import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
import commons.test.TestAccess;
import commons.test.TestUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test of Screen.
 *
 * @see Screen
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({Screen.class})
public class ScreenTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ScreenTest.class);
    
    
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
    @Before
    public void setup() throws Exception {
        PowerMockito.spy(Screen.class);
        
        Screen.recalculate();
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
     * JUnit test of constants.
     *
     * @throws Exception When there is an exception.
     * @see Screen#screens
     * @see Screen#dimensions
     */
    @Test
    public void testConstants() throws Exception {
        final List<GraphicsDevice> screens = TestAccess.getFieldValue(Screen.class, List.class, "screens");
        final Map<Screen.Dimension, Map<Integer, Integer>> dimensions = TestAccess.getFieldValue(Screen.class, Map.class, "dimensions");
        
        //static
        Assert.assertNotNull(screens);
        Assert.assertEquals(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length, screens.size());
        Assert.assertNotNull(dimensions);
        Assert.assertEquals(Screen.Dimension.values().length, dimensions.size());
        Arrays.stream(Screen.Dimension.values()).forEach(e -> {
            Assert.assertTrue(dimensions.containsKey(e));
            Assert.assertNotNull(dimensions.get(e));
            Assert.assertEquals(screens.size(), dimensions.get(e).size());
        });
        
        //verify
        for (int i = 0; i < screens.size(); i++) {
            Assert.assertTrue(dimensions.get(Screen.Dimension.MONITOR_WIDTH).get(i) > 0);
            Assert.assertTrue(dimensions.get(Screen.Dimension.MONITOR_HEIGHT).get(i) > 0);
            Assert.assertTrue(dimensions.get(Screen.Dimension.TASKBAR_WIDTH).get(i) >= 0);
            Assert.assertTrue(dimensions.get(Screen.Dimension.TASKBAR_HEIGHT).get(i) >= 0);
            Assert.assertEquals(
                    (dimensions.get(Screen.Dimension.MONITOR_WIDTH).get(i) - dimensions.get(Screen.Dimension.TASKBAR_WIDTH).get(i)),
                    dimensions.get(Screen.Dimension.SCREEN_WIDTH).get(i).intValue());
            Assert.assertEquals(
                    (dimensions.get(Screen.Dimension.MONITOR_HEIGHT).get(i) - dimensions.get(Screen.Dimension.TASKBAR_HEIGHT).get(i)),
                    dimensions.get(Screen.Dimension.SCREEN_HEIGHT).get(i).intValue());
            Assert.assertTrue(dimensions.get(Screen.Dimension.BORDER_WIDTH).get(i) >= 0);
            Assert.assertTrue(dimensions.get(Screen.Dimension.BORDER_HEIGHT).get(i) >= 0);
            Assert.assertEquals(
                    (dimensions.get(Screen.Dimension.SCREEN_WIDTH).get(i) - dimensions.get(Screen.Dimension.BORDER_WIDTH).get(i)),
                    dimensions.get(Screen.Dimension.DISPLAY_WIDTH).get(i).intValue());
            Assert.assertEquals(
                    (dimensions.get(Screen.Dimension.SCREEN_HEIGHT).get(i) - dimensions.get(Screen.Dimension.BORDER_HEIGHT).get(i)),
                    dimensions.get(Screen.Dimension.DISPLAY_HEIGHT).get(i).intValue());
        }
    }
    
    /**
     * JUnit test of Dimension.
     *
     * @throws Exception When there is an exception.
     * @see Screen.Dimension
     */
    @Test
    public void testDimension() throws Exception {
        final Class<?> Dimension = Screen.Dimension.class;
        final Object[] dimensions = Dimension.getEnumConstants();
        
        //values
        Assert.assertEquals(10, dimensions.length);
        Assert.assertEquals(Screen.Dimension.MONITOR_WIDTH, dimensions[0]);
        Assert.assertEquals(Screen.Dimension.MONITOR_HEIGHT, dimensions[1]);
        Assert.assertEquals(Screen.Dimension.TASKBAR_WIDTH, dimensions[2]);
        Assert.assertEquals(Screen.Dimension.TASKBAR_HEIGHT, dimensions[3]);
        Assert.assertEquals(Screen.Dimension.SCREEN_WIDTH, dimensions[4]);
        Assert.assertEquals(Screen.Dimension.SCREEN_HEIGHT, dimensions[5]);
        Assert.assertEquals(Screen.Dimension.BORDER_WIDTH, dimensions[6]);
        Assert.assertEquals(Screen.Dimension.BORDER_HEIGHT, dimensions[7]);
        Assert.assertEquals(Screen.Dimension.DISPLAY_WIDTH, dimensions[8]);
        Assert.assertEquals(Screen.Dimension.DISPLAY_HEIGHT, dimensions[9]);
    }
    
    /**
     * JUnit test of recalculate.
     *
     * @throws Exception When there is an exception.
     * @see Screen#recalculate()
     */
    @Test
    public void testRecalculate() throws Exception {
        final List<GraphicsDevice> screens = TestAccess.getFieldValue(Screen.class, List.class, "screens");
        final Map<Screen.Dimension, Map<Integer, Integer>> dimensions = TestAccess.getFieldValue(Screen.class, Map.class, "dimensions");
        
        //initial
        screens.clear();
        dimensions.clear();
        Assert.assertTrue(screens.isEmpty());
        Assert.assertTrue(dimensions.isEmpty());
        
        //standard
        Screen.recalculate();
        Assert.assertNotNull(screens);
        Assert.assertEquals(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length, screens.size());
        Assert.assertNotNull(dimensions);
        Assert.assertEquals(Screen.Dimension.values().length, dimensions.size());
        Arrays.stream(Screen.Dimension.values()).forEach(e -> {
            Assert.assertTrue(dimensions.containsKey(e));
            Assert.assertNotNull(dimensions.get(e));
            Assert.assertEquals(screens.size(), dimensions.get(e).size());
        });
        for (int i = 0; i < screens.size(); i++) {
            Assert.assertTrue(dimensions.get(Screen.Dimension.MONITOR_WIDTH).get(i) > 0);
            Assert.assertTrue(dimensions.get(Screen.Dimension.MONITOR_HEIGHT).get(i) > 0);
            Assert.assertTrue(dimensions.get(Screen.Dimension.TASKBAR_WIDTH).get(i) >= 0);
            Assert.assertTrue(dimensions.get(Screen.Dimension.TASKBAR_HEIGHT).get(i) >= 0);
            Assert.assertEquals(
                    (dimensions.get(Screen.Dimension.MONITOR_WIDTH).get(i) - dimensions.get(Screen.Dimension.TASKBAR_WIDTH).get(i)),
                    dimensions.get(Screen.Dimension.SCREEN_WIDTH).get(i).intValue());
            Assert.assertEquals(
                    (dimensions.get(Screen.Dimension.MONITOR_HEIGHT).get(i) - dimensions.get(Screen.Dimension.TASKBAR_HEIGHT).get(i)),
                    dimensions.get(Screen.Dimension.SCREEN_HEIGHT).get(i).intValue());
            Assert.assertTrue(dimensions.get(Screen.Dimension.BORDER_WIDTH).get(i) >= 0);
            Assert.assertTrue(dimensions.get(Screen.Dimension.BORDER_HEIGHT).get(i) >= 0);
            Assert.assertEquals(
                    (dimensions.get(Screen.Dimension.SCREEN_WIDTH).get(i) - dimensions.get(Screen.Dimension.BORDER_WIDTH).get(i)),
                    dimensions.get(Screen.Dimension.DISPLAY_WIDTH).get(i).intValue());
            Assert.assertEquals(
                    (dimensions.get(Screen.Dimension.SCREEN_HEIGHT).get(i) - dimensions.get(Screen.Dimension.BORDER_HEIGHT).get(i)),
                    dimensions.get(Screen.Dimension.DISPLAY_HEIGHT).get(i).intValue());
        }
    }
    
    /**
     * JUnit test of getScreenCount.
     *
     * @throws Exception When there is an exception.
     * @see Screen#getScreenCount()
     */
    @Test
    public void testGetScreenCount() throws Exception {
        final List<GraphicsDevice> screens = TestAccess.getFieldValue(Screen.class, List.class, "screens");
        
        //standard
        Assert.assertEquals(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length, Screen.getScreenCount());
        
        //not initialized
        screens.clear();
        Assert.assertEquals(0, Screen.getScreenCount());
    }
    
    /**
     * JUnit test of getScreen.
     *
     * @throws Exception When there is an exception.
     * @see Screen#getScreen(int)
     * @see Screen#getScreen()
     */
    @Test
    public void testGetScreen() throws Exception {
        final List<GraphicsDevice> mockScreens = ListUtility.listOf(Mockito.mock(GraphicsDevice.class), Mockito.mock(GraphicsDevice.class), Mockito.mock(GraphicsDevice.class));
        
        //standard
        Assert.assertTrue(TestAccess.setFieldValue(Screen.class, "screens", mockScreens));
        IntStream.range(0, mockScreens.size()).forEach(i ->
                Assert.assertEquals(mockScreens.get(i), Screen.getScreen(i)));
        
        //default screen
        Assert.assertEquals(mockScreens.get(0), Screen.getScreen());
        
        //invalid
        Assert.assertNull(Screen.getScreen(5));
        Assert.assertNull(Screen.getScreen(-1));
    }
    
    /**
     * JUnit test of getDimension.
     *
     * @throws Exception When there is an exception.
     * @see Screen#getDimension(Screen.Dimension, int)
     * @see Screen#getDimension(Screen.Dimension)
     */
    @Test
    public void testGetDimension() throws Exception {
        final Map<Screen.Dimension, Map<Integer, Integer>> mockDimensions = Arrays.stream(Screen.Dimension.values())
                .collect(MapCollectors.mapEachTo(e -> MapUtility.mapOf(
                        new Integer[] {0, 1, 2},
                        new Integer[] {e.name().hashCode(), (e.name().hashCode() + 1), (e.name().hashCode() + 2)})));
        
        //standard
        Assert.assertTrue(TestAccess.setFieldValue(Screen.class, "dimensions", mockDimensions));
        Arrays.stream(Screen.Dimension.values()).forEach(e ->
                IntStream.range(0, 3).forEach(i ->
                        Assert.assertEquals((e.name().hashCode() + i), Screen.getDimension(e, i).intValue())));
        
        //default screen
        Arrays.stream(Screen.Dimension.values()).forEach(e ->
                Assert.assertEquals(e.name().hashCode(), Screen.getDimension(e).intValue()));
        
        //invalid
        Arrays.stream(Screen.Dimension.values()).forEach(e -> {
            Assert.assertNull(Screen.getDimension(e, 5));
            Assert.assertNull(Screen.getDimension(e, -1));
        });
        TestUtils.assertException(NullPointerException.class, () ->
                Screen.getDimension(null, 0));
    }
    
}
