/*
 * File:    ScreenSizeTest.java
 * Package: commons.graphics
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.graphics;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import commons.object.collection.ListUtility;
import commons.object.collection.MapUtility;
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
 * JUnit test of ScreenSize.
 *
 * @see ScreenSize
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({ScreenSize.class})
public class ScreenSizeTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ScreenSizeTest.class);
    
    
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
        PowerMockito.spy(ScreenSize.class);
        
        ScreenSize.recalculate();
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
     * @see ScreenSize#screens
     * @see ScreenSize#dimensions
     */
    @Test
    public void testConstants() throws Exception {
        final List<GraphicsDevice> screens = TestUtils.getFieldValue(ScreenSize.class, List.class, "screens");
        final Map<ScreenSize.Dimension, Map<Integer, Integer>> dimensions = TestUtils.getFieldValue(ScreenSize.class, Map.class, "dimensions");
        
        //static
        Assert.assertNotNull(screens);
        Assert.assertEquals(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length, screens.size());
        Assert.assertNotNull(dimensions);
        Assert.assertEquals(ScreenSize.Dimension.values().length, dimensions.size());
        Arrays.stream(ScreenSize.Dimension.values()).forEach(e -> {
            Assert.assertTrue(dimensions.containsKey(e));
            Assert.assertNotNull(dimensions.get(e));
            Assert.assertEquals(screens.size(), dimensions.get(e).size());
        });
        
        //verify
        for (int i = 0; i < screens.size(); i++) {
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.MONITOR_WIDTH).get(i) > 0);
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.MONITOR_HEIGHT).get(i) > 0);
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.TASKBAR_WIDTH).get(i) >= 0);
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.TASKBAR_HEIGHT).get(i) >= 0);
            Assert.assertEquals(
                    (dimensions.get(ScreenSize.Dimension.MONITOR_WIDTH).get(i) - dimensions.get(ScreenSize.Dimension.TASKBAR_WIDTH).get(i)),
                    dimensions.get(ScreenSize.Dimension.SCREEN_WIDTH).get(i).intValue());
            Assert.assertEquals(
                    (dimensions.get(ScreenSize.Dimension.MONITOR_HEIGHT).get(i) - dimensions.get(ScreenSize.Dimension.TASKBAR_HEIGHT).get(i)),
                    dimensions.get(ScreenSize.Dimension.SCREEN_HEIGHT).get(i).intValue());
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.BORDER_WIDTH).get(i) >= 0);
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.BORDER_HEIGHT).get(i) >= 0);
            Assert.assertEquals(
                    (dimensions.get(ScreenSize.Dimension.SCREEN_WIDTH).get(i) - dimensions.get(ScreenSize.Dimension.BORDER_WIDTH).get(i)),
                    dimensions.get(ScreenSize.Dimension.DISPLAY_WIDTH).get(i).intValue());
            Assert.assertEquals(
                    (dimensions.get(ScreenSize.Dimension.SCREEN_HEIGHT).get(i) - dimensions.get(ScreenSize.Dimension.BORDER_HEIGHT).get(i)),
                    dimensions.get(ScreenSize.Dimension.DISPLAY_HEIGHT).get(i).intValue());
        }
    }
    
    /**
     * JUnit test of Dimension.
     *
     * @throws Exception When there is an exception.
     * @see ScreenSize.Dimension
     */
    @Test
    public void testDimension() throws Exception {
        final Class<?> Dimension = ScreenSize.Dimension.class;
        final Object[] dimensions = Dimension.getEnumConstants();
        
        //values
        Assert.assertEquals(10, dimensions.length);
        Assert.assertEquals(ScreenSize.Dimension.MONITOR_WIDTH, dimensions[0]);
        Assert.assertEquals(ScreenSize.Dimension.MONITOR_HEIGHT, dimensions[1]);
        Assert.assertEquals(ScreenSize.Dimension.TASKBAR_WIDTH, dimensions[2]);
        Assert.assertEquals(ScreenSize.Dimension.TASKBAR_HEIGHT, dimensions[3]);
        Assert.assertEquals(ScreenSize.Dimension.SCREEN_WIDTH, dimensions[4]);
        Assert.assertEquals(ScreenSize.Dimension.SCREEN_HEIGHT, dimensions[5]);
        Assert.assertEquals(ScreenSize.Dimension.BORDER_WIDTH, dimensions[6]);
        Assert.assertEquals(ScreenSize.Dimension.BORDER_HEIGHT, dimensions[7]);
        Assert.assertEquals(ScreenSize.Dimension.DISPLAY_WIDTH, dimensions[8]);
        Assert.assertEquals(ScreenSize.Dimension.DISPLAY_HEIGHT, dimensions[9]);
    }
    
    /**
     * JUnit test of recalculate.
     *
     * @throws Exception When there is an exception.
     * @see ScreenSize#recalculate()
     */
    @Test
    public void testRecalculate() throws Exception {
        final List<GraphicsDevice> screens = TestUtils.getFieldValue(ScreenSize.class, List.class, "screens");
        final Map<ScreenSize.Dimension, Map<Integer, Integer>> dimensions = TestUtils.getFieldValue(ScreenSize.class, Map.class, "dimensions");
        
        //initial
        screens.clear();
        dimensions.clear();
        Assert.assertTrue(screens.isEmpty());
        Assert.assertTrue(dimensions.isEmpty());
        
        //standard
        ScreenSize.recalculate();
        Assert.assertNotNull(screens);
        Assert.assertEquals(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length, screens.size());
        Assert.assertNotNull(dimensions);
        Assert.assertEquals(ScreenSize.Dimension.values().length, dimensions.size());
        Arrays.stream(ScreenSize.Dimension.values()).forEach(e -> {
            Assert.assertTrue(dimensions.containsKey(e));
            Assert.assertNotNull(dimensions.get(e));
            Assert.assertEquals(screens.size(), dimensions.get(e).size());
        });
        for (int i = 0; i < screens.size(); i++) {
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.MONITOR_WIDTH).get(i) > 0);
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.MONITOR_HEIGHT).get(i) > 0);
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.TASKBAR_WIDTH).get(i) >= 0);
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.TASKBAR_HEIGHT).get(i) >= 0);
            Assert.assertEquals(
                    (dimensions.get(ScreenSize.Dimension.MONITOR_WIDTH).get(i) - dimensions.get(ScreenSize.Dimension.TASKBAR_WIDTH).get(i)),
                    dimensions.get(ScreenSize.Dimension.SCREEN_WIDTH).get(i).intValue());
            Assert.assertEquals(
                    (dimensions.get(ScreenSize.Dimension.MONITOR_HEIGHT).get(i) - dimensions.get(ScreenSize.Dimension.TASKBAR_HEIGHT).get(i)),
                    dimensions.get(ScreenSize.Dimension.SCREEN_HEIGHT).get(i).intValue());
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.BORDER_WIDTH).get(i) >= 0);
            Assert.assertTrue(dimensions.get(ScreenSize.Dimension.BORDER_HEIGHT).get(i) >= 0);
            Assert.assertEquals(
                    (dimensions.get(ScreenSize.Dimension.SCREEN_WIDTH).get(i) - dimensions.get(ScreenSize.Dimension.BORDER_WIDTH).get(i)),
                    dimensions.get(ScreenSize.Dimension.DISPLAY_WIDTH).get(i).intValue());
            Assert.assertEquals(
                    (dimensions.get(ScreenSize.Dimension.SCREEN_HEIGHT).get(i) - dimensions.get(ScreenSize.Dimension.BORDER_HEIGHT).get(i)),
                    dimensions.get(ScreenSize.Dimension.DISPLAY_HEIGHT).get(i).intValue());
        }
    }
    
    /**
     * JUnit test of getScreenCount.
     *
     * @throws Exception When there is an exception.
     * @see ScreenSize#getScreenCount()
     */
    @Test
    public void testGetScreenCount() throws Exception {
        final List<GraphicsDevice> screens = TestUtils.getFieldValue(ScreenSize.class, List.class, "screens");
        
        //standard
        Assert.assertEquals(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length, ScreenSize.getScreenCount());
        
        //not initialized
        screens.clear();
        Assert.assertEquals(0, ScreenSize.getScreenCount());
    }
    
    /**
     * JUnit test of getScreen.
     *
     * @throws Exception When there is an exception.
     * @see ScreenSize#getScreen(int)
     * @see ScreenSize#getScreen()
     */
    @Test
    public void testGetScreen() throws Exception {
        final List<GraphicsDevice> mockScreens = ListUtility.listOf(Mockito.mock(GraphicsDevice.class), Mockito.mock(GraphicsDevice.class), Mockito.mock(GraphicsDevice.class));
        
        //standard
        Assert.assertTrue(TestUtils.setFieldValue(ScreenSize.class, "screens", mockScreens));
        IntStream.range(0, mockScreens.size()).forEach(i ->
                Assert.assertEquals(mockScreens.get(i), ScreenSize.getScreen(i)));
        
        //default screen
        Assert.assertEquals(mockScreens.get(0), ScreenSize.getScreen());
        
        //invalid
        Assert.assertNull(ScreenSize.getScreen(5));
        Assert.assertNull(ScreenSize.getScreen(-1));
    }
    
    /**
     * JUnit test of getDimension.
     *
     * @throws Exception When there is an exception.
     * @see ScreenSize#getDimension(ScreenSize.Dimension, int)
     * @see ScreenSize#getDimension(ScreenSize.Dimension)
     */
    @Test
    public void testGetDimension() throws Exception {
        final Map<ScreenSize.Dimension, Map<Integer, Integer>> mockDimensions = Arrays.stream(ScreenSize.Dimension.values())
                .collect(HashMap::new, (m, e) -> m.put(e, MapUtility.mapOf(
                        new Integer[] {0, 1, 2},
                        new Integer[] {e.name().hashCode(), (e.name().hashCode() + 1), (e.name().hashCode() + 2)})
                ), HashMap::putAll);
        
        //standard
        Assert.assertTrue(TestUtils.setFieldValue(ScreenSize.class, "dimensions", mockDimensions));
        Arrays.stream(ScreenSize.Dimension.values()).forEach(e ->
                IntStream.range(0, 3).forEach(i ->
                        Assert.assertEquals((e.name().hashCode() + i), ScreenSize.getDimension(e, i).intValue())));
        
        //default screen
        Arrays.stream(ScreenSize.Dimension.values()).forEach(e ->
                Assert.assertEquals(e.name().hashCode(), ScreenSize.getDimension(e).intValue()));
        
        //invalid
        Arrays.stream(ScreenSize.Dimension.values()).forEach(e -> {
            Assert.assertNull(ScreenSize.getDimension(e, 5));
            Assert.assertNull(ScreenSize.getDimension(e, -1));
        });
        TestUtils.assertException(NullPointerException.class, () ->
                ScreenSize.getDimension(null, 0));
    }
    
}
