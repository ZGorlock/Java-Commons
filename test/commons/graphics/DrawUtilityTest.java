/*
 * File:    DrawUtilityTest.java
 * Package: commons.graphics
 * Author:  Zachary Gill
 * Repo:    https://github.com/ZGorlock/Java-Commons
 */

package commons.graphics;

import commons.math.CoordinateUtility;
import commons.math.component.vector.BigVector;
import commons.math.component.vector.IntVector;
import commons.math.component.vector.Vector;
import commons.math.component.vector.VectorInterface;
import commons.test.TestUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JUnit test of DrawUtility.
 *
 * @see DrawUtility
 */
@SuppressWarnings({"RedundantSuppression", "ConstantConditions", "unchecked", "SpellCheckingInspection"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({DrawUtility.class, Graphics2D.class})
public class DrawUtilityTest {
    
    //Logger
    
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(DrawUtilityTest.class);
    
    
    //Fields
    
    /**
     * The mock 2D Graphics context.
     */
    private Graphics2D graphics;
    
    
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
        graphics = Mockito.mock(Graphics2D.class);
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
     */
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testConstants() throws Exception {
    }
    
    /**
     * JUnit test of drawPoint.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawPoint(Graphics2D, VectorInterface)
     */
    @Test
    public void testDrawPoint() throws Exception {
        //standard
        
        DrawUtility.drawPoint(graphics, new IntVector(80, 95));
        DrawUtility.drawPoint(graphics, new Vector(80.7, 95.1));
        DrawUtility.drawPoint(graphics, new BigVector("80.00007841023645474", "95.790546054243401"));
        DrawUtility.drawPoint(graphics, new IntVector(80, 95, 12));
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .drawRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(1), ArgumentMatchers.eq(1));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawPoint(null, new IntVector(80, 95)));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawPoint(graphics, null));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawPoint(graphics, new Vector(133.0)));
    }
    
    /**
     * JUnit test of drawLine.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawLine(Graphics2D, VectorInterface, VectorInterface)
     */
    @Test
    public void testDrawLine() throws Exception {
        //standard
        
        DrawUtility.drawLine(graphics, new IntVector(80, 95), new IntVector(120, 135));
        DrawUtility.drawLine(graphics, new Vector(80.7, 95.1), new Vector(120.2, 135.08));
        DrawUtility.drawLine(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("120.7905602359048474", "135.987041603054510217"));
        DrawUtility.drawLine(graphics, new IntVector(80, 95, 12), new Vector(120.2, 135.08, 17.9));
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .drawLine(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(120), ArgumentMatchers.eq(135));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawLine(null, new IntVector(80, 95), new IntVector(120, 135)));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawLine(graphics, new IntVector(80, 95), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawLine(graphics, null, new IntVector(120, 135)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawLine(graphics, null, null));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawLine(graphics, new Vector(80.0, 95.0), new Vector(120.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawLine(graphics, new Vector(80.0), new Vector(120.0, 135.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawLine(graphics, new Vector(80.0), new Vector(120.0)));
    }
    
    /**
     * JUnit test of drawRect.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawRect(Graphics2D, VectorInterface, VectorInterface)
     * @see DrawUtility#drawRect(Graphics2D, VectorInterface, int, int)
     */
    @Test
    public void testDrawRect() throws Exception {
        //standard
        
        DrawUtility.drawRect(graphics, new IntVector(80, 95), new IntVector(120, 135));
        DrawUtility.drawRect(graphics, new Vector(80.7, 95.1), new Vector(120.2, 135.08));
        DrawUtility.drawRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("120.7905602359048474", "135.987041603054510217"));
        DrawUtility.drawRect(graphics, new IntVector(80, 95, 12), new Vector(120.2, 135.08, 17.9));
        
        DrawUtility.drawRect(graphics, new IntVector(80, 95), 40, 40);
        DrawUtility.drawRect(graphics, new Vector(80.7, 95.1), 40, 40);
        DrawUtility.drawRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 40, 40);
        DrawUtility.drawRect(graphics, new IntVector(80, 95, 12), 40, 40);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .drawRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRect(null, new IntVector(80, 95), new IntVector(120, 135)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRect(null, new IntVector(80, 95), 40, 40));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRect(graphics, new IntVector(80, 95), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRect(graphics, null, new IntVector(120, 135)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRect(graphics, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRect(graphics, null, 40, 40));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawRect(graphics, new Vector(80.0, 95.0), new Vector(120.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawRect(graphics, new Vector(80.0), new Vector(120.0, 135.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawRect(graphics, new Vector(80.0), new Vector(120.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawRect(graphics, new Vector(80.0), 40, 40));
    }
    
    /**
     * JUnit test of drawCircle.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawCircle(Graphics2D, VectorInterface, int)
     */
    @Test
    public void testDrawCircle() throws Exception {
        //standard
        
        DrawUtility.drawCircle(graphics, new IntVector(80, 95), 25);
        DrawUtility.drawCircle(graphics, new Vector(80.7, 95.1), 25);
        DrawUtility.drawCircle(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 25);
        DrawUtility.drawCircle(graphics, new IntVector(80, 95, 12), 25);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .drawOval(ArgumentMatchers.eq(55), ArgumentMatchers.eq(70), ArgumentMatchers.eq(50), ArgumentMatchers.eq(50));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawCircle(null, new IntVector(80, 95), 50));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawCircle(graphics, null, 50));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawCircle(graphics, new Vector(133.0), 50));
    }
    
    /**
     * JUnit test of drawOval.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawOval(Graphics2D, VectorInterface, VectorInterface)
     * @see DrawUtility#drawOval(Graphics2D, VectorInterface, int, int)
     */
    @Test
    public void testDrawOval() throws Exception {
        //standard
        
        DrawUtility.drawOval(graphics, new IntVector(80, 95), new IntVector(140, 125));
        DrawUtility.drawOval(graphics, new Vector(80.7, 95.1), new Vector(140.08, 125.2));
        DrawUtility.drawOval(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("140.987041603054510217", "125.7905602359048474"));
        DrawUtility.drawOval(graphics, new IntVector(80, 95, 12), new Vector(140.08, 125.2, 17.9));
        
        DrawUtility.drawOval(graphics, new IntVector(110, 110), 60, 30);
        DrawUtility.drawOval(graphics, new Vector(110.7, 110.1), 60, 30);
        DrawUtility.drawOval(graphics, new BigVector("110.00007841023645474", "110.790546054243401"), 60, 30);
        DrawUtility.drawOval(graphics, new IntVector(110, 110, 12), 60, 30);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .drawOval(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(60), ArgumentMatchers.eq(30));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawOval(null, new IntVector(80, 95), new IntVector(140, 125)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawOval(null, new IntVector(80, 95), 60, 30));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawOval(graphics, new IntVector(80, 95), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawOval(graphics, null, new IntVector(140, 125)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawOval(graphics, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawOval(graphics, null, 60, 30));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawOval(graphics, new Vector(80.0, 95.0), new Vector(140.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawOval(graphics, new Vector(80.0), new Vector(140.0, 125.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawOval(graphics, new Vector(80.0), new Vector(140.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawOval(graphics, new Vector(110.0), 60, 30));
    }
    
    /**
     * JUnit test of drawPolygon.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawPolygon(Graphics2D, Polygon)
     * @see DrawUtility#drawPolygon(Graphics2D, List)
     */
    @Test
    public void testDrawPolygon() throws Exception {
        //standard
        
        List<Vector> points = new ArrayList<>();
        List<IntVector> intPoints = new ArrayList<>();
        List<BigVector> bigPoints = new ArrayList<>();
        List<Vector> longPoints = new ArrayList<>();
        for (double theta = 0; theta < (9 * Math.PI / 5); theta += (2 * Math.PI / 5)) {
            Vector point = CoordinateUtility.polarToCartesian(200, theta);
            points.add(point);
            intPoints.add(new IntVector(point));
            bigPoints.add(new BigVector(point));
            longPoints.add(new Vector(point, 8.0));
        }
        
        Polygon polygon = new Polygon(
                points.stream().mapToInt(e -> e.getRawComponents()[0].intValue()).toArray(),
                points.stream().mapToInt(e -> e.getRawComponents()[1].intValue()).toArray(),
                points.size());
        Polygon intPolygon = new Polygon(
                intPoints.stream().mapToInt(e -> e.getRawComponents()[0]).toArray(),
                intPoints.stream().mapToInt(e -> e.getRawComponents()[1]).toArray(),
                intPoints.size());
        Polygon bigPolygon = new Polygon(
                bigPoints.stream().mapToInt(e -> e.getRawComponents()[0].intValue()).toArray(),
                bigPoints.stream().mapToInt(e -> e.getRawComponents()[1].intValue()).toArray(),
                bigPoints.size());
        
        
        DrawUtility.drawPolygon(graphics, polygon);
        DrawUtility.drawPolygon(graphics, intPolygon);
        DrawUtility.drawPolygon(graphics, bigPolygon);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .drawPolygon(ArgumentMatchers.eq(polygon));
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .drawPolygon(ArgumentMatchers.eq(intPolygon));
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .drawPolygon(ArgumentMatchers.eq(bigPolygon));
        
        DrawUtility.drawPolygon(graphics, points);
        DrawUtility.drawPolygon(graphics, intPoints);
        DrawUtility.drawPolygon(graphics, bigPoints);
        DrawUtility.drawPolygon(graphics, longPoints);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .drawPolygon(
                        ArgumentMatchers.eq(new int[] {200, 61, -161, -161, 61}),
                        ArgumentMatchers.eq(new int[] {0, 190, 117, -117, -190}),
                        ArgumentMatchers.eq(5)
                );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawPolygon(null, polygon));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawPolygon(null, points));
        
        TestUtils.assertNoException(() ->
                DrawUtility.drawPolygon(graphics, (Polygon) null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawPolygon(graphics, (List<Vector>) null));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawPolygon(graphics, Arrays.asList(new Vector(80.0, 95.0), new Vector(140.0))));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawPolygon(graphics, Arrays.asList(new Vector(80.0), new Vector(140.0, 135.0))));
    }
    
    /**
     * JUnit test of drawShape.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawShape(Graphics2D, Shape)
     */
    @Test
    public void testDrawShape() throws Exception {
        //standard
        
        Shape shape = Mockito.mock(Shape.class);
        
        DrawUtility.drawShape(graphics, shape);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .draw(ArgumentMatchers.eq(shape));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawShape(null, shape));
        
        TestUtils.assertNoException(() ->
                DrawUtility.drawShape(graphics, null));
    }
    
    /**
     * JUnit test of drawArc.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawArc(Graphics2D, VectorInterface, VectorInterface, double, double)
     * @see DrawUtility#drawArc(Graphics2D, VectorInterface, int, int, double, double)
     */
    @Test
    public void testDrawArc() throws Exception {
        //standard
        
        double startAngle = (3 * Math.PI / 4);
        double endAngle = (7 * Math.PI / 8);
        
        DrawUtility.drawArc(graphics, new IntVector(80, 95), new IntVector(140, 125), startAngle, endAngle);
        DrawUtility.drawArc(graphics, new Vector(80.7, 95.1), new Vector(140.08, 125.2), startAngle, endAngle);
        DrawUtility.drawArc(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("140.987041603054510217", "125.7905602359048474"), startAngle, endAngle);
        DrawUtility.drawArc(graphics, new IntVector(80, 95, 12), new Vector(140.08, 125.2, 17.9), startAngle, endAngle);
        
        DrawUtility.drawArc(graphics, new IntVector(110, 110), 60, 30, startAngle, endAngle);
        DrawUtility.drawArc(graphics, new Vector(110.7, 110.1), 60, 30, startAngle, endAngle);
        DrawUtility.drawArc(graphics, new BigVector("110.00007841023645474", "110.790546054243401"), 60, 30, startAngle, endAngle);
        DrawUtility.drawArc(graphics, new IntVector(110, 110, 12), 60, 30, startAngle, endAngle);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .drawArc(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(60), ArgumentMatchers.eq(30),
                        ArgumentMatchers.eq((int) Math.toDegrees(startAngle)), ArgumentMatchers.eq((int) Math.toDegrees(endAngle - startAngle)));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawArc(null, new IntVector(80, 95), new IntVector(120, 135), startAngle, endAngle));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawArc(null, new IntVector(80, 95), 40, 40, startAngle, endAngle));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawArc(graphics, null, new IntVector(120, 135), startAngle, endAngle));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawArc(graphics, new IntVector(80, 95), null, startAngle, endAngle));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawArc(graphics, null, null, startAngle, endAngle));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawArc(graphics, new Vector(80.0, 95.0), new Vector(140.0), startAngle, endAngle));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawArc(graphics, new Vector(80.0), new Vector(140.0, 125.0), startAngle, endAngle));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawArc(graphics, new Vector(80.0), new Vector(140.0), startAngle, endAngle));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawArc(graphics, new Vector(110.0), 60, 30, startAngle, endAngle));
    }
    
    /**
     * JUnit test of draw3DRect.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#draw3DRect(Graphics2D, VectorInterface, VectorInterface, boolean)
     * @see DrawUtility#draw3DRect(Graphics2D, VectorInterface, int, int, boolean)
     */
    @Test
    public void testDraw3DRect() throws Exception {
        //standard
        
        DrawUtility.draw3DRect(graphics, new IntVector(80, 95), new IntVector(120, 135), true);
        DrawUtility.draw3DRect(graphics, new Vector(80.7, 95.1), new Vector(120.2, 135.08), true);
        DrawUtility.draw3DRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("120.7905602359048474", "135.987041603054510217"), true);
        DrawUtility.draw3DRect(graphics, new IntVector(80, 95, 12), new Vector(120.2, 135.08, 17.9), true);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .draw3DRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40), ArgumentMatchers.eq(true));
        
        DrawUtility.draw3DRect(graphics, new IntVector(80, 95), 40, 40, false);
        DrawUtility.draw3DRect(graphics, new Vector(80.7, 95.1), 40, 40, false);
        DrawUtility.draw3DRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 40, 40, false);
        DrawUtility.draw3DRect(graphics, new IntVector(80, 95, 12), 40, 40, false);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .draw3DRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40), ArgumentMatchers.eq(false));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.draw3DRect(null, new IntVector(80, 95), new IntVector(120, 135), true));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.draw3DRect(null, new IntVector(80, 95), 40, 40, true));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.draw3DRect(graphics, new IntVector(80, 95), null, true));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.draw3DRect(graphics, null, new IntVector(120, 135), true));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.draw3DRect(graphics, null, null, true));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.draw3DRect(graphics, null, 40, 40, true));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.draw3DRect(graphics, new Vector(80.0, 95.0), new Vector(120.0), true));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.draw3DRect(graphics, new Vector(80.0), new Vector(120.0, 135.0), true));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.draw3DRect(graphics, new Vector(80.0), new Vector(120.0), true));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.draw3DRect(graphics, new Vector(80.0), 40, 40, true));
    }
    
    /**
     * JUnit test of drawRoundRect.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawRoundRect(Graphics2D, VectorInterface, VectorInterface, int, int)
     * @see DrawUtility#drawRoundRect(Graphics2D, VectorInterface, int, int, int, int)
     */
    @Test
    public void testDrawRoundRect() throws Exception {
        //standard
        
        DrawUtility.drawRoundRect(graphics, new IntVector(80, 95), new IntVector(120, 135), 10, 15);
        DrawUtility.drawRoundRect(graphics, new Vector(80.7, 95.1), new Vector(120.2, 135.08), 10, 15);
        DrawUtility.drawRoundRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("120.7905602359048474", "135.987041603054510217"), 10, 15);
        DrawUtility.drawRoundRect(graphics, new IntVector(80, 95, 12), new Vector(120.2, 135.08, 17.9), 10, 15);
        
        DrawUtility.drawRoundRect(graphics, new IntVector(80, 95), 40, 40, 10, 15);
        DrawUtility.drawRoundRect(graphics, new Vector(80.7, 95.1), 40, 40, 10, 15);
        DrawUtility.drawRoundRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 40, 40, 10, 15);
        DrawUtility.drawRoundRect(graphics, new IntVector(80, 95, 12), 40, 40, 10, 15);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .drawRoundRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40), ArgumentMatchers.eq(10), ArgumentMatchers.eq(15));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRoundRect(null, new IntVector(80, 95), new IntVector(120, 135), 10, 15));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRoundRect(null, new IntVector(80, 95), 40, 40, 10, 15));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRoundRect(graphics, new IntVector(80, 95), null, 10, 15));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRoundRect(graphics, null, new IntVector(120, 135), 10, 15));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRoundRect(graphics, null, null, 10, 15));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawRoundRect(graphics, null, 40, 40, 10, 15));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawRoundRect(graphics, new Vector(80.0, 95.0), new Vector(120.0), 10, 15));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawRoundRect(graphics, new Vector(80.0), new Vector(120.0, 135.0), 10, 15));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawRoundRect(graphics, new Vector(80.0), new Vector(120.0), 10, 15));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawRoundRect(graphics, new Vector(80.0), 40, 40, 10, 15));
    }
    
    /**
     * JUnit test of fillRect.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#fillRect(Graphics2D, VectorInterface, VectorInterface)
     * @see DrawUtility#fillRect(Graphics2D, VectorInterface, int, int)
     */
    @Test
    public void testFillRect() throws Exception {
        //standard
        
        DrawUtility.fillRect(graphics, new IntVector(80, 95), new IntVector(120, 135));
        DrawUtility.fillRect(graphics, new Vector(80.7, 95.1), new Vector(120.2, 135.08));
        DrawUtility.fillRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("120.7905602359048474", "135.987041603054510217"));
        DrawUtility.fillRect(graphics, new IntVector(80, 95, 12), new Vector(120.2, 135.08, 17.9));
        
        DrawUtility.fillRect(graphics, new IntVector(80, 95), 40, 40);
        DrawUtility.fillRect(graphics, new Vector(80.7, 95.1), 40, 40);
        DrawUtility.fillRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 40, 40);
        DrawUtility.fillRect(graphics, new IntVector(80, 95, 12), 40, 40);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .fillRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRect(null, new IntVector(80, 95), new IntVector(120, 135)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRect(null, new IntVector(80, 95), 40, 40));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRect(graphics, new IntVector(80, 95), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRect(graphics, null, new IntVector(120, 135)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRect(graphics, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRect(graphics, null, 40, 40));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillRect(graphics, new Vector(80.0, 95.0), new Vector(120.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillRect(graphics, new Vector(80.0), new Vector(120.0, 135.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillRect(graphics, new Vector(80.0), new Vector(120.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillRect(graphics, new Vector(80.0), 40, 40));
    }
    
    /**
     * JUnit test of fillCircle.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#fillCircle(Graphics2D, VectorInterface, int)
     */
    @Test
    public void testFillCircle() throws Exception {
        //standard
        
        DrawUtility.fillCircle(graphics, new IntVector(80, 95), 25);
        DrawUtility.fillCircle(graphics, new Vector(80.7, 95.1), 25);
        DrawUtility.fillCircle(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 25);
        DrawUtility.fillCircle(graphics, new IntVector(80, 95, 12), 25);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .fillOval(ArgumentMatchers.eq(55), ArgumentMatchers.eq(70), ArgumentMatchers.eq(50), ArgumentMatchers.eq(50));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillCircle(null, new IntVector(80, 95), 50));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillCircle(graphics, null, 50));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillCircle(graphics, new Vector(133.0), 50));
    }
    
    /**
     * JUnit test of fillOval.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#fillOval(Graphics2D, VectorInterface, VectorInterface)
     * @see DrawUtility#fillOval(Graphics2D, VectorInterface, int, int)
     */
    @Test
    public void testFillOval() throws Exception {
        //standard
        
        DrawUtility.fillOval(graphics, new IntVector(80, 95), new IntVector(140, 125));
        DrawUtility.fillOval(graphics, new Vector(80.7, 95.1), new Vector(140.08, 125.2));
        DrawUtility.fillOval(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("140.987041603054510217", "125.7905602359048474"));
        DrawUtility.fillOval(graphics, new IntVector(80, 95, 12), new Vector(140.08, 125.2, 17.9));
        
        DrawUtility.fillOval(graphics, new IntVector(110, 110), 60, 30);
        DrawUtility.fillOval(graphics, new Vector(110.7, 110.1), 60, 30);
        DrawUtility.fillOval(graphics, new BigVector("110.00007841023645474", "110.790546054243401"), 60, 30);
        DrawUtility.fillOval(graphics, new IntVector(110, 110, 12), 60, 30);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .fillOval(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(60), ArgumentMatchers.eq(30));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillOval(null, new IntVector(80, 95), new IntVector(140, 125)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillOval(null, new IntVector(80, 95), 60, 30));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillOval(graphics, new IntVector(80, 95), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillOval(graphics, null, new IntVector(140, 125)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillOval(graphics, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillOval(graphics, null, 60, 30));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillOval(graphics, new Vector(80.0, 95.0), new Vector(140.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillOval(graphics, new Vector(80.0), new Vector(140.0, 125.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillOval(graphics, new Vector(80.0), new Vector(140.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillOval(graphics, new Vector(110.0), 60, 30));
    }
    
    /**
     * JUnit test of fillPolygon.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#fillPolygon(Graphics2D, Polygon)
     * @see DrawUtility#fillPolygon(Graphics2D, List)
     */
    @Test
    public void testFillPolygon() throws Exception {
        //standard
        
        List<Vector> points = new ArrayList<>();
        List<IntVector> intPoints = new ArrayList<>();
        List<BigVector> bigPoints = new ArrayList<>();
        List<Vector> longPoints = new ArrayList<>();
        for (double theta = 0; theta < (9 * Math.PI / 5); theta += (2 * Math.PI / 5)) {
            Vector point = CoordinateUtility.polarToCartesian(200, theta);
            points.add(point);
            intPoints.add(new IntVector(point));
            bigPoints.add(new BigVector(point));
            longPoints.add(new Vector(point, 8.0));
        }
        
        Polygon polygon = new Polygon(
                points.stream().mapToInt(e -> e.getRawComponents()[0].intValue()).toArray(),
                points.stream().mapToInt(e -> e.getRawComponents()[1].intValue()).toArray(),
                points.size());
        Polygon intPolygon = new Polygon(
                intPoints.stream().mapToInt(e -> e.getRawComponents()[0]).toArray(),
                intPoints.stream().mapToInt(e -> e.getRawComponents()[1]).toArray(),
                intPoints.size());
        Polygon bigPolygon = new Polygon(
                bigPoints.stream().mapToInt(e -> e.getRawComponents()[0].intValue()).toArray(),
                bigPoints.stream().mapToInt(e -> e.getRawComponents()[1].intValue()).toArray(),
                bigPoints.size());
        
        
        DrawUtility.fillPolygon(graphics, polygon);
        DrawUtility.fillPolygon(graphics, intPolygon);
        DrawUtility.fillPolygon(graphics, bigPolygon);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .fillPolygon(ArgumentMatchers.eq(polygon));
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .fillPolygon(ArgumentMatchers.eq(intPolygon));
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .fillPolygon(ArgumentMatchers.eq(bigPolygon));
        
        DrawUtility.fillPolygon(graphics, points);
        DrawUtility.fillPolygon(graphics, intPoints);
        DrawUtility.fillPolygon(graphics, bigPoints);
        DrawUtility.fillPolygon(graphics, longPoints);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .fillPolygon(
                        ArgumentMatchers.eq(new int[] {200, 61, -161, -161, 61}),
                        ArgumentMatchers.eq(new int[] {0, 190, 117, -117, -190}),
                        ArgumentMatchers.eq(5)
                );
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillPolygon(null, polygon));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillPolygon(null, points));
        
        TestUtils.assertNoException(() ->
                DrawUtility.fillPolygon(graphics, (Polygon) null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillPolygon(graphics, (List<Vector>) null));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillPolygon(graphics, Arrays.asList(new Vector(80.0, 95.0), new Vector(140.0))));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillPolygon(graphics, Arrays.asList(new Vector(80.0), new Vector(140.0, 135.0))));
    }
    
    /**
     * JUnit test of fillShape.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#fillShape(Graphics2D, Shape)
     */
    @Test
    public void testFillShape() throws Exception {
        //standard
        
        Shape shape = Mockito.mock(Shape.class);
        
        DrawUtility.fillShape(graphics, shape);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .fill(ArgumentMatchers.eq(shape));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillShape(null, shape));
        
        TestUtils.assertNoException(() ->
                DrawUtility.fillShape(graphics, null));
    }
    
    /**
     * JUnit test of fillArc.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#fillArc(Graphics2D, VectorInterface, VectorInterface, double, double)
     * @see DrawUtility#fillArc(Graphics2D, VectorInterface, int, int, double, double)
     */
    @Test
    public void testFillArc() throws Exception {
        //standard
        
        double startAngle = (3 * Math.PI / 4);
        double endAngle = (7 * Math.PI / 8);
        
        DrawUtility.fillArc(graphics, new IntVector(80, 95), new IntVector(140, 125), startAngle, endAngle);
        DrawUtility.fillArc(graphics, new Vector(80.7, 95.1), new Vector(140.08, 125.2), startAngle, endAngle);
        DrawUtility.fillArc(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("140.987041603054510217", "125.7905602359048474"), startAngle, endAngle);
        DrawUtility.fillArc(graphics, new IntVector(80, 95, 12), new Vector(140.08, 125.2, 17.9), startAngle, endAngle);
        
        DrawUtility.fillArc(graphics, new IntVector(110, 110), 60, 30, startAngle, endAngle);
        DrawUtility.fillArc(graphics, new Vector(110.7, 110.1), 60, 30, startAngle, endAngle);
        DrawUtility.fillArc(graphics, new BigVector("110.00007841023645474", "110.790546054243401"), 60, 30, startAngle, endAngle);
        DrawUtility.fillArc(graphics, new IntVector(110, 110, 12), 60, 30, startAngle, endAngle);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .fillArc(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(60), ArgumentMatchers.eq(30),
                        ArgumentMatchers.eq((int) Math.toDegrees(startAngle)), ArgumentMatchers.eq((int) Math.toDegrees(endAngle - startAngle)));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillArc(null, new IntVector(80, 95), new IntVector(120, 135), startAngle, endAngle));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillArc(null, new IntVector(80, 95), 40, 40, startAngle, endAngle));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillArc(graphics, null, new IntVector(120, 135), startAngle, endAngle));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillArc(graphics, new IntVector(80, 95), null, startAngle, endAngle));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillArc(graphics, null, null, startAngle, endAngle));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillArc(graphics, new Vector(80.0, 95.0), new Vector(140.0), startAngle, endAngle));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillArc(graphics, new Vector(80.0), new Vector(140.0, 125.0), startAngle, endAngle));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillArc(graphics, new Vector(80.0), new Vector(140.0), startAngle, endAngle));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillArc(graphics, new Vector(110.0), 60, 30, startAngle, endAngle));
    }
    
    /**
     * JUnit test of fill3DRect.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#fill3DRect(Graphics2D, VectorInterface, VectorInterface, boolean)
     * @see DrawUtility#fill3DRect(Graphics2D, VectorInterface, int, int, boolean)
     */
    @Test
    public void testFill3DRect() throws Exception {
        //standard
        
        DrawUtility.fill3DRect(graphics, new IntVector(80, 95), new IntVector(120, 135), true);
        DrawUtility.fill3DRect(graphics, new Vector(80.7, 95.1), new Vector(120.2, 135.08), true);
        DrawUtility.fill3DRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("120.7905602359048474", "135.987041603054510217"), true);
        DrawUtility.fill3DRect(graphics, new IntVector(80, 95, 12), new Vector(120.2, 135.08, 17.9), true);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .fill3DRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40), ArgumentMatchers.eq(true));
        
        DrawUtility.fill3DRect(graphics, new IntVector(80, 95), 40, 40, false);
        DrawUtility.fill3DRect(graphics, new Vector(80.7, 95.1), 40, 40, false);
        DrawUtility.fill3DRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 40, 40, false);
        DrawUtility.fill3DRect(graphics, new IntVector(80, 95, 12), 40, 40, false);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .fill3DRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40), ArgumentMatchers.eq(false));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fill3DRect(null, new IntVector(80, 95), new IntVector(120, 135), true));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fill3DRect(null, new IntVector(80, 95), 40, 40, true));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fill3DRect(graphics, new IntVector(80, 95), null, true));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fill3DRect(graphics, null, new IntVector(120, 135), true));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fill3DRect(graphics, null, null, true));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fill3DRect(graphics, null, 40, 40, true));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fill3DRect(graphics, new Vector(80.0, 95.0), new Vector(120.0), true));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fill3DRect(graphics, new Vector(80.0), new Vector(120.0, 135.0), true));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fill3DRect(graphics, new Vector(80.0), new Vector(120.0), true));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fill3DRect(graphics, new Vector(80.0), 40, 40, true));
    }
    
    /**
     * JUnit test of fillRoundRect.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#fillRoundRect(Graphics2D, VectorInterface, VectorInterface, int, int)
     * @see DrawUtility#fillRoundRect(Graphics2D, VectorInterface, int, int, int, int)
     */
    @Test
    public void testFillRoundRect() throws Exception {
        //standard
        
        DrawUtility.fillRoundRect(graphics, new IntVector(80, 95), new IntVector(120, 135), 10, 15);
        DrawUtility.fillRoundRect(graphics, new Vector(80.7, 95.1), new Vector(120.2, 135.08), 10, 15);
        DrawUtility.fillRoundRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("120.7905602359048474", "135.987041603054510217"), 10, 15);
        DrawUtility.fillRoundRect(graphics, new IntVector(80, 95, 12), new Vector(120.2, 135.08, 17.9), 10, 15);
        
        DrawUtility.fillRoundRect(graphics, new IntVector(80, 95), 40, 40, 10, 15);
        DrawUtility.fillRoundRect(graphics, new Vector(80.7, 95.1), 40, 40, 10, 15);
        DrawUtility.fillRoundRect(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 40, 40, 10, 15);
        DrawUtility.fillRoundRect(graphics, new IntVector(80, 95, 12), 40, 40, 10, 15);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .fillRoundRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40), ArgumentMatchers.eq(10), ArgumentMatchers.eq(15));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRoundRect(null, new IntVector(80, 95), new IntVector(120, 135), 10, 15));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRoundRect(null, new IntVector(80, 95), 40, 40, 10, 15));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRoundRect(graphics, new IntVector(80, 95), null, 10, 15));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRoundRect(graphics, null, new IntVector(120, 135), 10, 15));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRoundRect(graphics, null, null, 10, 15));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.fillRoundRect(graphics, null, 40, 40, 10, 15));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillRoundRect(graphics, new Vector(80.0, 95.0), new Vector(120.0), 10, 15));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillRoundRect(graphics, new Vector(80.0), new Vector(120.0, 135.0), 10, 15));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillRoundRect(graphics, new Vector(80.0), new Vector(120.0), 10, 15));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.fillRoundRect(graphics, new Vector(80.0), 40, 40, 10, 15));
    }
    
    /**
     * JUnit test of drawImage.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawImage(Graphics2D, BufferedImage, VectorInterface, VectorInterface, VectorInterface, VectorInterface)
     * @see DrawUtility#drawImage(Graphics2D, BufferedImage, VectorInterface, int, int, VectorInterface, int, int)
     * @see DrawUtility#drawImage(Graphics2D, BufferedImage, VectorInterface, VectorInterface)
     * @see DrawUtility#drawImage(Graphics2D, BufferedImage, VectorInterface, int, int)
     * @see DrawUtility#drawImage(Graphics2D, BufferedImage, VectorInterface)
     * @see DrawUtility#drawImage(Graphics2D, BufferedImage)
     */
    @Test
    public void testDrawImage() throws Exception {
        BufferedImage image = Mockito.mock(BufferedImage.class);
        
        //source and dest vector area
        
        DrawUtility.drawImage(graphics, image, new IntVector(50, 200), new IntVector(250, 400), new IntVector(10, 10), new IntVector(210, 210));
        DrawUtility.drawImage(graphics, image, new Vector(50.71, 200.06), new Vector(250.4, 400.61), new Vector(10.3, 10.34), new Vector(210.8, 210.29));
        DrawUtility.drawImage(graphics, image, new BigVector("50.0748912", "200.7950065"), new BigVector("250.9054", "400.435065"), new BigVector("10.5513099", "10.04987"), new BigVector("210.305948", "210.48970589"));
        DrawUtility.drawImage(graphics, image, new IntVector(50, 200, 84), new Vector(250.4, 400.61, 36.04), new IntVector(10, 10, 901), new BigVector("210.305948", "210.48970589", "45.489045613"));
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .drawImage(ArgumentMatchers.eq(image),
                        ArgumentMatchers.eq(10), ArgumentMatchers.eq(10), ArgumentMatchers.eq(210), ArgumentMatchers.eq(210),
                        ArgumentMatchers.eq(50), ArgumentMatchers.eq(200), ArgumentMatchers.eq(250), ArgumentMatchers.eq(400),
                        ArgumentMatchers.isNull());
        
        //source and dest vector, width, and height
        
        DrawUtility.drawImage(graphics, image, new IntVector(50, 200), 200, 200, new IntVector(10, 10), 200, 200);
        DrawUtility.drawImage(graphics, image, new Vector(50.71, 200.06), 200, 200, new Vector(10.3, 10.34), 200, 200);
        DrawUtility.drawImage(graphics, image, new BigVector("50.0748912", "200.7950065"), 200, 200, new BigVector("10.5513099", "10.04987"), 200, 200);
        DrawUtility.drawImage(graphics, image, new IntVector(50, 200, 84), 200, 200, new IntVector(10, 10, 901), 200, 200);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4 + 4))
                .drawImage(ArgumentMatchers.eq(image),
                        ArgumentMatchers.eq(10), ArgumentMatchers.eq(10), ArgumentMatchers.eq(210), ArgumentMatchers.eq(210),
                        ArgumentMatchers.eq(50), ArgumentMatchers.eq(200), ArgumentMatchers.eq(250), ArgumentMatchers.eq(400),
                        ArgumentMatchers.isNull());
        
        //dest vector area
        
        DrawUtility.drawImage(graphics, image, new IntVector(10, 10), new IntVector(210, 210));
        DrawUtility.drawImage(graphics, image, new Vector(10.3, 10.34), new Vector(210.8, 210.29));
        DrawUtility.drawImage(graphics, image, new BigVector("10.5513099", "10.04987"), new BigVector("210.305948", "210.48970589"));
        DrawUtility.drawImage(graphics, image, new IntVector(10, 10, 901), new BigVector("210.305948", "210.48970589", "45.489045613"));
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .drawImage(ArgumentMatchers.eq(image),
                        ArgumentMatchers.eq(10), ArgumentMatchers.eq(10), ArgumentMatchers.eq(200), ArgumentMatchers.eq(200),
                        ArgumentMatchers.isNull());
        
        //dest vector, width, and height
        
        DrawUtility.drawImage(graphics, image, new IntVector(10, 10), 200, 200);
        DrawUtility.drawImage(graphics, image, new Vector(10.3, 10.34), 200, 200);
        DrawUtility.drawImage(graphics, image, new BigVector("10.5513099", "10.04987"), 200, 200);
        DrawUtility.drawImage(graphics, image, new IntVector(10, 10, 901), 200, 200);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4 + 4))
                .drawImage(ArgumentMatchers.eq(image),
                        ArgumentMatchers.eq(10), ArgumentMatchers.eq(10), ArgumentMatchers.eq(200), ArgumentMatchers.eq(200),
                        ArgumentMatchers.isNull());
        
        //dest vector
        
        DrawUtility.drawImage(graphics, image, new IntVector(10, 10));
        DrawUtility.drawImage(graphics, image, new Vector(10.3, 10.34));
        DrawUtility.drawImage(graphics, image, new BigVector("10.5513099", "10.04987"));
        DrawUtility.drawImage(graphics, image, new IntVector(10, 10, 901));
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .drawImage(ArgumentMatchers.eq(image),
                        ArgumentMatchers.eq(10), ArgumentMatchers.eq(10),
                        ArgumentMatchers.isNull());
        
        //default
        
        DrawUtility.drawImage(graphics, image);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .drawImage(ArgumentMatchers.eq(image),
                        ArgumentMatchers.eq(0), ArgumentMatchers.eq(0),
                        ArgumentMatchers.isNull());
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(null, image, new IntVector(50, 200), new IntVector(250, 400), new IntVector(10, 10), new IntVector(210, 210)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(null, image, new IntVector(50, 200), 200, 200, new IntVector(10, 10), 200, 200));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(null, image, new IntVector(10, 10), new IntVector(210, 210)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(null, image, new IntVector(10, 10), 200, 200));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(null, image, new IntVector(10, 10)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(null, image));
        
        TestUtils.assertNoException(() ->
                DrawUtility.drawImage(graphics, null, new IntVector(50, 200), new IntVector(250, 400), new IntVector(10, 10), new IntVector(210, 210)));
        TestUtils.assertNoException(() ->
                DrawUtility.drawImage(graphics, null, new IntVector(50, 200), 200, 200, new IntVector(10, 10), 200, 200));
        TestUtils.assertNoException(() ->
                DrawUtility.drawImage(graphics, null, new IntVector(10, 10), new IntVector(210, 210)));
        TestUtils.assertNoException(() ->
                DrawUtility.drawImage(graphics, null, new IntVector(10, 10), 200, 200));
        TestUtils.assertNoException(() ->
                DrawUtility.drawImage(graphics, null, new IntVector(10, 10)));
        TestUtils.assertNoException(() ->
                DrawUtility.drawImage(graphics, null));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, null, new IntVector(250, 400), new IntVector(10, 10), new IntVector(210, 210)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, new IntVector(50, 200), null, new IntVector(10, 10), new IntVector(210, 210)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, new IntVector(50, 200), new IntVector(250, 400), null, new IntVector(210, 210)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, new IntVector(50, 200), new IntVector(250, 400), new IntVector(10, 10), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, null, null, null, null));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, null, 200, 200, new IntVector(10, 10), 200, 200));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, new IntVector(50, 200), 200, 200, null, 200, 200));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, null, 200, 200, null, 200, 200));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, null, new IntVector(210, 210)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, new IntVector(10, 10), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, null, null));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, null, 200, 200));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawImage(graphics, image, null));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(50.0), new Vector(250.0, 400.0), new Vector(10.0, 10.0), new Vector(210.0, 210.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(50.0, 500.0), new Vector(250.0), new Vector(10.0, 10.0), new Vector(210.0, 210.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(50.0, 500.0), new Vector(250.0, 400.0), new Vector(10.0), new Vector(210.0, 210.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(50.0, 500.0), new Vector(250.0, 400.0), new Vector(10, 10.0), new Vector(210.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(50.0), new Vector(250.0), new Vector(10.0), new Vector(210.0)));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(50.0), 200, 200, new Vector(10.0, 10.0), 200, 200));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(50.0, 200.0), 200, 200, new Vector(10.0), 200, 200));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(50.0), 200, 200, new Vector(10.0), 200, 200));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(10.0), new Vector(210.0, 210.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(10.0, 10.0), new Vector(210.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(10.0), new Vector(210.0)));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(10.0), 200, 200));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawImage(graphics, image, new Vector(10.0)));
    }
    
    /**
     * JUnit test of drawString.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#drawString(Graphics2D, String, VectorInterface)
     */
    @Test
    public void testDrawString() throws Exception {
        //standard
        
        DrawUtility.drawString(graphics, "test", new IntVector(80, 95));
        DrawUtility.drawString(graphics, "test", new Vector(80.7, 95.1));
        DrawUtility.drawString(graphics, "test", new BigVector("80.00007841023645474", "95.790546054243401"));
        DrawUtility.drawString(graphics, "test", new IntVector(80, 95, 12));
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .drawString(ArgumentMatchers.eq("test"), ArgumentMatchers.eq(80), ArgumentMatchers.eq(95));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawString(null, "test", new IntVector(80, 95)));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.drawString(graphics, "test", null));
        
        TestUtils.assertNoException(() ->
                DrawUtility.drawString(graphics, null, new IntVector(80, 95)));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.drawString(graphics, "test", new Vector(133.0)));
    }
    
    /**
     * JUnit test of copyArea.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#copyArea(Graphics2D, VectorInterface, VectorInterface, VectorInterface)
     * @see DrawUtility#copyArea(Graphics2D, VectorInterface, int, int, VectorInterface)
     */
    @Test
    public void testCopyArea() throws Exception {
        //standard
        
        DrawUtility.copyArea(graphics, new IntVector(50, 200), new IntVector(250, 400), new IntVector(10, 10));
        DrawUtility.copyArea(graphics, new Vector(50.71, 200.06), new Vector(250.4, 400.61), new Vector(10.3, 10.34));
        DrawUtility.copyArea(graphics, new BigVector("50.0748912", "200.7950065"), new BigVector("250.9054", "400.435065"), new BigVector("10.5513099", "10.04987"));
        DrawUtility.copyArea(graphics, new IntVector(50, 200, 84), new Vector(250.4, 400.61, 36.04), new IntVector(10, 10, 901));
        
        DrawUtility.copyArea(graphics, new IntVector(50, 200), 200, 200, new IntVector(10, 10));
        DrawUtility.copyArea(graphics, new Vector(50.71, 200.06), 200, 200, new Vector(10.3, 10.34));
        DrawUtility.copyArea(graphics, new BigVector("50.0748912", "200.7950065"), 200, 200, new BigVector("10.5513099", "10.04987"));
        DrawUtility.copyArea(graphics, new IntVector(50, 200, 84), 200, 200, new IntVector(10, 10, 901));
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .copyArea(ArgumentMatchers.eq(50), ArgumentMatchers.eq(200), ArgumentMatchers.eq(200), ArgumentMatchers.eq(200), ArgumentMatchers.eq(-40), ArgumentMatchers.eq(-190));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.copyArea(null, new IntVector(50, 200), new IntVector(250, 400), new IntVector(10, 10)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.copyArea(null, new IntVector(50, 200), 200, 200, new IntVector(10, 10)));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.copyArea(graphics, null, new IntVector(250, 400), new IntVector(10, 10)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.copyArea(graphics, new IntVector(50, 200), null, new IntVector(10, 10)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.copyArea(graphics, new IntVector(50, 200), new IntVector(250, 400), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.copyArea(graphics, null, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.copyArea(graphics, null, 200, 200, new IntVector(10, 10)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.copyArea(graphics, new IntVector(50, 200), 200, 200, null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.copyArea(graphics, null, 200, 200, null));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.copyArea(graphics, new Vector(50.0), new Vector(250.0, 400.0), new Vector(10.0, 10.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.copyArea(graphics, new Vector(50.0, 200.0), new Vector(250.0), new Vector(10.0, 10.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.copyArea(graphics, new Vector(50.0, 200.0), new Vector(250.0, 400.0), new Vector(10.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.copyArea(graphics, new Vector(50.0), new Vector(250.0), new Vector(10.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.copyArea(graphics, new Vector(50.0), 200, 200, new Vector(10.0, 10.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.copyArea(graphics, new Vector(50.0, 200.0), 200, 200, new Vector(10.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.copyArea(graphics, new Vector(50.0), 200, 200, new Vector(10.0)));
    }
    
    /**
     * JUnit test of clearArea.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#clearArea(Graphics2D, VectorInterface, VectorInterface)
     * @see DrawUtility#clearArea(Graphics2D, VectorInterface, int, int)
     */
    @Test
    public void testClearArea() throws Exception {
        //standard
        
        DrawUtility.clearArea(graphics, new IntVector(80, 95), new IntVector(120, 135));
        DrawUtility.clearArea(graphics, new Vector(80.7, 95.1), new Vector(120.2, 135.08));
        DrawUtility.clearArea(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("120.7905602359048474", "135.987041603054510217"));
        DrawUtility.clearArea(graphics, new IntVector(80, 95, 12), new Vector(120.2, 135.08, 17.9));
        
        DrawUtility.clearArea(graphics, new IntVector(80, 95), 40, 40);
        DrawUtility.clearArea(graphics, new Vector(80.7, 95.1), 40, 40);
        DrawUtility.clearArea(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 40, 40);
        DrawUtility.clearArea(graphics, new IntVector(80, 95, 12), 40, 40);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .clearRect(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.clearArea(null, new IntVector(80, 95), new IntVector(120, 135)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.clearArea(null, new IntVector(80, 95), 40, 40));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.clearArea(graphics, new IntVector(80, 95), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.clearArea(graphics, null, new IntVector(120, 135)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.clearArea(graphics, null, null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.clearArea(graphics, null, 40, 40));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.clearArea(graphics, new Vector(80.0, 95.0), new Vector(120.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.clearArea(graphics, new Vector(80.0), new Vector(120.0, 135.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.clearArea(graphics, new Vector(80.0), new Vector(120.0)));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.clearArea(graphics, new Vector(80.0), 40, 40));
    }
    
    /**
     * JUnit test of setColor.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#setColor(Graphics2D, Color)
     */
    @Test
    public void testSetColor() throws Exception {
        final Color color = new Color(192, 192, 192);
        
        //standard
        
        DrawUtility.setColor(graphics, color);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .setColor(ArgumentMatchers.eq(color));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setColor(null, color));
        
        TestUtils.assertNoException(() ->
                DrawUtility.setColor(graphics, null));
    }
    
    /**
     * JUnit test of setBackground.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#setBackground(Graphics2D, Color)
     */
    @Test
    public void testSetBackground() throws Exception {
        final Color color = new Color(192, 192, 192);
        
        //standard
        
        DrawUtility.setBackground(graphics, color);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .setBackground(ArgumentMatchers.eq(color));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setBackground(null, color));
        
        TestUtils.assertNoException(() ->
                DrawUtility.setBackground(graphics, null));
    }
    
    /**
     * JUnit test of setFont.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#setFont(Graphics2D, Font)
     */
    @Test
    public void testSetFont() throws Exception {
        final Font font = Mockito.mock(Font.class);
        
        //standard
        
        DrawUtility.setFont(graphics, font);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .setFont(ArgumentMatchers.eq(font));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setFont(null, font));
        
        TestUtils.assertNoException(() ->
                DrawUtility.setFont(graphics, null));
    }
    
    /**
     * JUnit test of setClip.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#setClip(Graphics2D, Shape)
     * @see DrawUtility#setClip(Graphics2D, List)
     * @see DrawUtility#setClip(Graphics2D, VectorInterface, VectorInterface)
     * @see DrawUtility#setClip(Graphics2D, VectorInterface, int, int)
     */
    @Test
    public void testSetClip() throws Exception {
        //shape
        
        final Shape shape = Mockito.mock(Shape.class);
        
        DrawUtility.setClip(graphics, shape);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .setClip(ArgumentMatchers.eq(shape));
        
        
        //points
        
        List<Vector> points = new ArrayList<>();
        List<IntVector> intPoints = new ArrayList<>();
        List<BigVector> bigPoints = new ArrayList<>();
        List<Vector> longPoints = new ArrayList<>();
        for (double theta = 0; theta < (9 * Math.PI / 5); theta += (2 * Math.PI / 5)) {
            Vector point = CoordinateUtility.polarToCartesian(200, theta);
            points.add(point);
            intPoints.add(new IntVector(point));
            bigPoints.add(new BigVector(point));
            longPoints.add(new Vector(point, 8.0));
        }
        
        DrawUtility.setClip(graphics, points);
        DrawUtility.setClip(graphics, intPoints);
        DrawUtility.setClip(graphics, bigPoints);
        DrawUtility.setClip(graphics, longPoints);
        
        Mockito.verify(graphics, VerificationModeFactory.times(4))
                .setClip(ArgumentMatchers.any(Polygon.class));
        
        //standard
        
        DrawUtility.setClip(graphics, new IntVector(80, 95), new IntVector(120, 135));
        DrawUtility.setClip(graphics, new Vector(80.7, 95.1), new Vector(120.2, 135.08));
        DrawUtility.setClip(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), new BigVector("120.7905602359048474", "135.987041603054510217"));
        DrawUtility.setClip(graphics, new IntVector(80, 95, 12), new Vector(120.2, 135.08, 17.9));
        
        DrawUtility.setClip(graphics, new IntVector(80, 95), 40, 40);
        DrawUtility.setClip(graphics, new Vector(80.7, 95.1), 40, 40);
        DrawUtility.setClip(graphics, new BigVector("80.00007841023645474", "95.790546054243401"), 40, 40);
        DrawUtility.setClip(graphics, new IntVector(80, 95, 12), 40, 40);
        
        Mockito.verify(graphics, VerificationModeFactory.times(8))
                .setClip(ArgumentMatchers.eq(80), ArgumentMatchers.eq(95), ArgumentMatchers.eq(40), ArgumentMatchers.eq(40));
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setClip(null, shape));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setClip(null, points));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setClip(null, new IntVector(80, 95), new IntVector(120, 135)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setClip(null, new IntVector(80, 95), 40, 40));
        
        TestUtils.assertNoException(() ->
                DrawUtility.setClip(graphics, (Shape) null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setClip(graphics, (List<Vector>) null));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setClip(graphics, null, new IntVector(120, 135)));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setClip(graphics, new IntVector(80, 95), null));
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setClip(graphics, null, null));
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.setClip(graphics, null, 40, 40));
        
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.setClip(graphics, Arrays.asList(new Vector(80.0, 95.0), new Vector(140.0))));
        TestUtils.assertException(ArrayIndexOutOfBoundsException.class, "Index 1 out of bounds for length 1", () ->
                DrawUtility.setClip(graphics, Arrays.asList(new Vector(80.0), new Vector(140.0, 135.0))));
    }
    
    /**
     * JUnit test of getColor.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#getColor(Graphics2D)
     */
    @Test
    public void testGetColor() throws Exception {
        //standard
        
        DrawUtility.getColor(graphics);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .getColor();
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.getColor(null));
    }
    
    /**
     * JUnit test of getBackground.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#getBackground(Graphics2D)
     */
    @Test
    public void testGetBackground() throws Exception {
        //standard
        
        DrawUtility.getBackground(graphics);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .getBackground();
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.getBackground(null));
    }
    
    /**
     * JUnit test of getFont.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#getFont(Graphics2D)
     */
    @Test
    public void testGetFont() throws Exception {
        //standard
        
        DrawUtility.getFont(graphics);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .getFont();
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.getFont(null));
    }
    
    /**
     * JUnit test of getClip.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#getClip(Graphics2D)
     */
    @Test
    public void testGetClip() throws Exception {
        //standard
        
        DrawUtility.getClip(graphics);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .getClip();
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.getClip(null));
    }
    
    /**
     * JUnit test of dispose.
     *
     * @throws Exception When there is an exception.
     * @see DrawUtility#dispose(Graphics2D)
     */
    @Test
    public void testDispose() throws Exception {
        //standard
        
        DrawUtility.dispose(graphics);
        
        Mockito.verify(graphics, VerificationModeFactory.times(1))
                .dispose();
        
        //invalid
        
        TestUtils.assertException(NullPointerException.class, () ->
                DrawUtility.dispose(null));
    }
    
}
